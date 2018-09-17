package com.macys.sdt.projects.PurchaseAndDelivery.ResponsiveCheckout.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.OrderServices;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import java.util.*;

public class DatabaseSteps {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSteps.class);
    public static final String STANDARD_FEE = StepUtils.macys() ? "9.95" : "8.00";
    public static String orderNumber = null;
    public static String orderTotal = null;
    public static String orderConfirmationStatus = null;

    @And("^I get order details$")
    public void iGetOrderDetails() {
        StepUtils.pausePageHangWatchDog();
        OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class);
        Assert.assertTrue("Not on Order confirmation page", Wait.until(orderConfirmation.orderNumber::exists, 30));
        orderNumber = orderConfirmation.getNumber();
        orderTotal = orderConfirmation.getTotal();
        Assert.assertNotNull("Order number is not displayed or empty", orderNumber);
        Assert.assertNotNull("Order total is not displayed or empty", orderTotal);
        Assert.assertNotNull("Contact email is not displayed or empty", orderConfirmation.getEmail());
        logger.info("Order Details:: Order Number : " + orderNumber + ", Order Total : " + orderTotal);
        StepUtils.resumePageHangWatchDog();
    }

    @Then("^I verify the order details in database$")
    public void iVerifyTheOrderDetailsInDatabase() {
        Utils.threadSleep(3000, "Waiting for the order to be processed");
        Map orderDetails = new OrderServices().getOrderDetails(orderNumber);
        Assert.assertTrue("No order found with number " + orderNumber, orderDetails.size() != 0);
        Assert.assertEquals("User ID is not matched", orderDetails.get("USER_ID"), Cookies.getCurrentUserId());
        String orderStatus = orderDetails.get("ORDER_STATUS").toString();
        String inventoryDownMsg = Elements.getText("responsive_order_confirmation.rc_inventory_down_message");
        Assert.assertEquals(orderStatus, inventoryDownMsg.contains("unable to verify availability") ? "BPEND" : "RCMPL");
        logger.info("Order: " + orderNumber + " placed in " + orderStatus + " mode");
        orderConfirmationStatus = new OrderServices().getOrderConfirmationMessage(orderNumber);
        if (orderStatus.equals("RCMPL")) {
            Assert.assertNotNull("Order confirmation status is not available for order: " + orderNumber, orderConfirmationStatus);
            if (orderConfirmationStatus.equalsIgnoreCase("SUCCESS") || orderConfirmationStatus.equalsIgnoreCase("RCMPL")) {
                Assert.assertNotNull("Reservation ID is not generated", orderDetails.get("RESERVATION_ID"));
                Assert.assertEquals("Reservation status is not APPROVED", orderDetails.get("RESERVATION_STATUS"), "APPROVED");
            }
            logger.info("Order confirmation status of the order: " + orderNumber + " is: " + orderConfirmationStatus);
        }
        String site = TestUsers.getSiteType().toUpperCase();
        Map<String, String> orderAttributes = new OrderServices().getAllOrderAttributes(orderNumber);
        Assert.assertTrue("User type mismatch in OrderAttributes table", orderAttributes.get("USER").equals(StepUtils.signedIn() ? "SIGNIN" : "GUEST"));
        Assert.assertTrue("Order source type mismatch in OrderAttributes table", orderAttributes.get("ORDER_SOURCE").equals(site));
        Assert.assertTrue("Channel source type mismatch in OrderAttributes table", orderAttributes.get("CHANNEL_SOURCE").equals(StepUtils.MEW() ? "MEW" : "DESKTOP"));
        Assert.assertNotNull("Customer IP address is Null", orderAttributes.get("CUSTOMER_IP_ADDRESS"));
        logger.info("Order attribute values verification is successful");
        Map<String, String> deviceInfo = new OrderServices().getOrderContextInfo(orderNumber);
        Assert.assertEquals("Client ID is not as Expected", deviceInfo.get("CLIENT_ID"), site);
        Assert.assertEquals("Sub client ID is not as Expected", deviceInfo.get("SUB_CLIENT_ID"), (StepUtils.MEW() ? (site.equals("MCOM") ? "MMEW" : "BMEW") : "Site"));
        logger.info("Order context info details verification is successful");
    }

    @And("^I should see a total of (\\d+) shipment(?:s)? in database$")
    public void iShouldSeeATotalOfShipmentsInDatabase(int shipmentCount) {
        List<HashMap> shipments = new OrderServices().getShipments(orderNumber);
        Assert.assertEquals("Mismatch in shipments count", shipmentCount, shipments.size());
        logger.info("Total shipment count verification is successful");
    }

    @And("^I should see (\\d+) shipment(?:s)? with ship method type as ([G2OEBSW]) in database$")
    public void iShouldSeeShipmentWithShipMethodTypeAsGInDatabase(int shipmentCount, String shipType) {
        List<String> shipmentsMethods = new OrderServices().getShipMethodCode(orderNumber);
        Assert.assertEquals("Mismatch in shipment type count", shipmentCount, Collections.frequency(shipmentsMethods, shipType));
        logger.info("Shipping method verification is successful");
    }

    @And("^I validate \"([^\"]*)\" payment type in eps authorization calls$")
    public void iValidatePaymentTypeInEpsAuthorizationCalls(String paymentType) {
        if (orderConfirmationStatus != null && (orderConfirmationStatus.equals("SUCCESS") || orderConfirmationStatus.equals("RCMPL"))) {
            Element request = new OrderServices().getEpsAuthorizationRequest(orderNumber, paymentType);
            String amount = request.getElementsByTagName("amount").item(0).getTextContent();
            Assert.assertEquals("EPS Log: Amount charged is not same as in the Confirmation page", amount, orderTotal.replace(".", ""));
            Element response = new OrderServices().getEpsAuthorizationResponse(orderNumber, paymentType);
            String result = response.getElementsByTagName("result").item(0).getTextContent();
            Assert.assertEquals("EPS Log: EPS Authorization Failed", result, "APPROVED");
            logger.info("EPS authorization log verification is successful");
        } else {
            logger.warn("Order is placed in Batch mode and order confirmation status is " + orderConfirmationStatus + ". Hence skipped EPS log verification");
        }
    }

    @And("^I verify device info details in database$")
    public void iVerifyDeviceInfoDetailsInDatabase() {
        Map<String, String> deviceInfo = new OrderServices().getOrderContextInfo(orderNumber);
        String site = TestUsers.getSiteType().toUpperCase();
        Assert.assertNotNull("Device info is Null", deviceInfo.get("DEVICE_INFO"));
        Assert.assertEquals("Client ID is not as Expected", deviceInfo.get("CLIENT_ID"), site);
        Assert.assertEquals("Sub client ID is not as Expected", deviceInfo.get("SUB_CLIENT_ID"), (StepUtils.MEW() ? (site.equals("MCOM") ? "MMEW" : "BMEW") : "Site"));
        logger.info("Device info details verification is successful");
    }

    @Then("^I should see (free|standard) base and adjusted base fee in DB$")
    public void iShouldSeeFreeBaseAndAdjustedBaseFeeInDB(String feeType) {
        HashMap orderDetails = new OrderServices().getOrderDetails(orderNumber);
        String baseFee = StepUtils.macys() && feeType.equals("free") ? "0.00" : STANDARD_FEE;
        String adjustedBaseFee = feeType.equals("free") ? "0.00" : STANDARD_FEE;
        Assert.assertEquals(baseFee, orderDetails.get("BASE_FEE"));
        Assert.assertEquals(adjustedBaseFee, orderDetails.get("ADJUSTED_BASE_FEE"));
    }
}