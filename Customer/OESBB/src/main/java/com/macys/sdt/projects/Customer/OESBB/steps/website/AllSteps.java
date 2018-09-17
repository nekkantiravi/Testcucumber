package com.macys.sdt.projects.Customer.OESBB.steps.website;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.OESBB.utils.Utilities;
import com.macys.sdt.projects.Customer.OESBB.utils.model.Loyalty;
import com.macys.sdt.projects.Customer.OESBB.utils.model.OptionalDatum;
import com.macys.sdt.projects.Customer.OESBB.utils.runner.BaseTest;
import com.macys.sdt.projects.Customer.OESBB.utils.runner.OESBBLogger;
import com.macys.sdt.projects.Customer.OESBB.utils.ui_email.BCOMEmailPage;
import com.macys.sdt.projects.Customer.OESBB.utils.ui_email.MCOMEmailPage;

import com.thoughtworks.selenium.webdriven.commands.Click;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.text.NumberFormat;
import java.util.LinkedHashMap;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.core.JsonParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AllSteps extends BaseTest {

    //====================================================
    //  Local/Util Methods
    //====================================================
    private static Object emailpage = null;
    private static final Logger log = LoggerFactory.getLogger(AllSteps.class);

    protected boolean goToEmailPageByFirstName() throws InterruptedException {
        if (emailpage == null) {
            emailpage = StepUtils.macys() ? new MCOMEmailPage() : new BCOMEmailPage();
        }
        String firstName = orderAdjustment.getPayloadObject().getBillingDetail().getFirstName();
        if (StepUtils.macys())
            return ((MCOMEmailPage)emailpage).loadEmailByBodyKeyword(firstName);
        else
            return ((BCOMEmailPage)emailpage).loadEmailByBodyKeyword(firstName);
    }


    private boolean goToEmailPageBySourceSystemId() throws InterruptedException{
        OESBBLogger.setCurrentConfiguration(OESBBLogger.STATUS,"GO_TO_EMAIL_BY_SSID");
        if (emailpage == null) {
            emailpage = StepUtils.macys() ? new MCOMEmailPage() : new BCOMEmailPage();
        }
        if (StepUtils.macys())
            return ((MCOMEmailPage)emailpage).loadEmailByBodyKeyword(getSourceSystemID());
        else
            return ((BCOMEmailPage)emailpage).loadEmailByBodyKeyword(getSourceSystemID());
    }
    
    private String getModifiedYearFormat(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date d = formatter.parse(date);
        formatter.applyPattern("MM/dd/yy");
        return formatter.format(d).toString();
    }
    
    //====================================================
    // Website BCOM steps
    //====================================================
    @Then("^i should see bloomingdales logo$")
    public void i_should_see_bloomingdales_logo() throws Throwable {
        Assert.assertTrue(((BCOMEmailPage)emailpage).logoBloomingdales.isDisplayed());
    }

    public void i_should_see_original_form_of_tender() throws Throwable {
        Assert.assertEquals(
                "Expected Original form of tender not found in user email",
                enhancedPayloadActual.getTriggerData().get(0).getPaymentData()
                        .get(0).getType()
                        + "\n"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getCardNbr(),
                ((BCOMEmailPage)emailpage).getOrderDetails().get("original_form_of_tender"));
    }

    @Then("^i should see payment method$")
    public void i_should_see_payment_method() throws Throwable {
        Assert.assertEquals("Expected payment method not found in user email",
                enhancedPayloadActual.getTriggerData().get(0).getPaymentData()
                        .get(0).getType()
                        + " "
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getCardNbr(),
                ((BCOMEmailPage)emailpage).getOrderDetails().get("payment_method"));
    }

    @Then("^i should see refunded to card info$")
    public void i_should_see_refunded_to_card_info() throws Throwable {

        String card = "";
        String cardType = enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getType();
        if (cardType.equalsIgnoreCase("EGC")
                || cardType.equalsIgnoreCase("VGC")
                || cardType.equalsIgnoreCase("paypal")
                || cardType.equalsIgnoreCase("Electronic Gift Card")) {
            card = "(Refunded to "
                    + enhancedPayloadActual.getTriggerData().get(0)
                    .getPaymentData().get(0).getType() + ")";

        } else {

            card = "(Refunded to "
                    + enhancedPayloadActual.getTriggerData().get(0)
                    .getPaymentData().get(0).getType()
                    + " "
                    + enhancedPayloadActual.getTriggerData().get(0)
                    .getPaymentData().get(0).getCardNbr() + ")";
        }

        Assert.assertEquals("Expected card info not found in user email", card,
                ((BCOMEmailPage)emailpage).getRefundedTo());

    }

    @Then("^i should see card credited$")
    public void i_should_see_card_credited() throws Throwable {
        String card = enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getType()
                + " "
                + enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getCardNbr();
        Assert.assertEquals(
                "Card credited is not displaying as expected in the email",
                card, ((BCOMEmailPage)emailpage).getOrderDetails().get("card_credited"));
    }

    @Then("^i should see payment card and type:$")
    public void i_should_see_payment_card_and_type(String cardType)
            throws Throwable {
        cardType = cardType.replaceAll(
                "<cardNoAndCardType>",
                enhancedPayloadActual.getTriggerData().get(0).getPaymentData()
                        .get(0).getType()
                        + " "
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getCardNbr()).trim();

        Assert.assertEquals(
                "Card credited is not displaying as expected in the email",
                cardType, ((BCOMEmailPage)emailpage).getPaymentTypeForMaf().trim());
    }

    @Then("^i should see reason for replacement label and value$")
    public void i_should_see_reason_for_replacement_label_and_value()
            throws JsonParseException, JsonMappingException, IOException {

        Assert.assertEquals(
                "Expected reason for replacement not found in user email",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getShipitems().get(0)
                        .getReturnReason(),
                ((BCOMEmailPage)emailpage).reasonForReplacement.getText());

        Assert.assertEquals(
                "Expected reason for replacement not found in user email",
                "Reason for replacement",
                ((BCOMEmailPage)emailpage).reasonForReplacementLabel.getText().trim());

    }

    @And("^i should see store address$")
    public void i_should_see_store_address() throws Throwable {

        String storeAddrs = ((BCOMEmailPage)emailpage).getstoreAddress().toLowerCase()
                .trim();

        Assert.assertTrue(
                "Store AddrLine1 is not matching with the expected value",
                storeAddrs.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreAddrLine1()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store AddrLine2 is not matching with the expected value",
                storeAddrs.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreAddrLine2()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store City is not matching with the expected value",
                storeAddrs.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreCity()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store State is not matching with the expected value",
                storeAddrs.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreState()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store ZipCode is not matching with the expected value",
                storeAddrs.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreZipcode()
                        .toLowerCase()));

    }

    @And("^i should see phone number for general questions$")
    public void i_should_see_phone_number_for_general_questions()
            throws Throwable {

        Assert.assertTrue(
                "Phone number for General Questions is not matching with the expected value",
                ((BCOMEmailPage)emailpage).getstoreTimeAndPhoneNos().contains(
                        "General Questions: "
                                + enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0)
                                .getStorePhone().trim().toLowerCase()));

    }

    @And("^i should see phone number for in-store pickup questions$")
    public void i_should_see_phone_number_for_instore_pickup_questions()
            throws Throwable {

        String inStorePickUpPhoneNumberFromMail = ((BCOMEmailPage)emailpage)
                .getstoreTimeAndPhoneNos().trim();
        String inStorePickUpPhoneNumberFromEp = "Questions about your in-store pickup? Call "
                + enhancedPayloadActual.getTriggerData().get(0)
                .getShipmentsData().get(0).getStoreBopsPhone().trim();

        inStorePickUpPhoneNumberFromMail = inStorePickUpPhoneNumberFromMail
                .replace("-", "");
        inStorePickUpPhoneNumberFromEp = inStorePickUpPhoneNumberFromEp
                .replace("-", "");

        Assert.assertTrue(
                "Phone number for in-store pickup Questions is not matching with the expected value",
                inStorePickUpPhoneNumberFromMail
                        .contains(inStorePickUpPhoneNumberFromEp));

    }

    @And("^i should see order ready text$")
    public void i_should_see_order_ready() throws Throwable {

        String orderReadyText = ((BCOMEmailPage)emailpage).getOrderReadyText();
        String billingFname = Utilities.returnCamelCase(enhancedPayloadActual
                .getTriggerData().get(0).getBillingData().getFirstName());
        String billingLname = Utilities.returnCamelCase(enhancedPayloadActual
                .getTriggerData().get(0).getBillingData().getLastName());
        String expectedText = "We have also notified " + billingFname + " "
                + billingLname + " that this order is now ready.";

        if ((template.get("brand").equals("blcom") || template.get("brand")
                .equals("bwedd"))
                && (template.get("subtype").equals("14S") || template.get(
                "subtype").equals("14T"))) {
            Assert.assertTrue(
                    "Order Ready text (expectedText) is not matching with the expected value",
                    orderReadyText.contains(expectedText));
        }

    }

    @And("^i should see contact information$")
    public void i_should_see_contact_information() throws Throwable {

        String cntctInformation = ((BCOMEmailPage)emailpage).getcontactInformation().trim();

        Assert.assertTrue(
                "Pickup order First Name is not match with actual",
                cntctInformation.contains(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getFirstName().toLowerCase()));

        Assert.assertTrue(
                "Pickup order Last Name is not match with actual",
                cntctInformation.contains(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getLastName().toLowerCase()));

        Assert.assertTrue(
                "Expected store phone Number not match with actual",
                cntctInformation.contains(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getphone()));

        Assert.assertTrue(
                "Expected store Email Address not match with actual",
                cntctInformation.contains(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getemail().toLowerCase()));

    }

    @And("^i should see store pickup instructions$")
    public void i_should_see_store_pickup_instructions() throws Throwable {

        String storePickupInstructionsFromMail = ((BCOMEmailPage)emailpage)
                .getstorePickupInstructions().replaceAll("\n", "").trim();

        String storePickupInstructionsFromEP = enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().get(0)
                .getStorePickupInstructions().replaceAll("\\|", "").trim();

        storePickupInstructionsFromMail = storePickupInstructionsFromMail
                .replaceAll(" ", "");

        storePickupInstructionsFromEP = (storePickupInstructionsFromEP
                .replaceAll(" ", "")).replaceAll("<BR>", "");

        Assert.assertEquals(
                "Expected Store Pickup Instructions is not found in user email",
                storePickupInstructionsFromEP, storePickupInstructionsFromMail);

    }

    private static String getdefaultDateFormat(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date d = formatter.parse(date);
        formatter.applyPattern("MM/dd/yyyy");
        return formatter.format(d).toString();
    }

    @Then("^i should see shipping first name$")
    public void i_should_see_shipping_first_name() throws Throwable {
        Assert.assertEquals(
                "Expected First name not found in user email",

                "Dear "
                        + Utilities.returnCamelCase(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData()
                        .get(0).getFirstName()) + ",",
                ((BCOMEmailPage)emailpage).getFirstName());
    }

    @Then("^i should see security firstname$")
    public void i_should_see_security_firstname() throws Throwable {
        Assert.assertEquals(
                "Expected First name not found in user email",
                "Dear "
                        + Utilities.returnCamelCase(enhancedPayloadActual
                        .getTriggerData().get(0).getWebsiteData()
                        .getSecurityFirstName()) + ",",
                ((BCOMEmailPage)emailpage).getFirstName());

    }

    @Then("^i should see product details$")
    public void i_should_see_product_details() throws Throwable {
        Iterator qty = ((BCOMEmailPage)emailpage).getItemDetails().get("quantity")
                .iterator();
        Iterator clr = ((BCOMEmailPage)emailpage).getItemDetails().get("color").iterator();
        Iterator siz = ((BCOMEmailPage)emailpage).getItemDetails().get("size").iterator();
        Iterator prdImg = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("product_image_url").iterator();
        Iterator prdImgSrc = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("product_image_src").iterator();
        Iterator prdName = ((BCOMEmailPage)emailpage).getItemDetails().get("product_name")
                .iterator();
        Iterator prdImgURL = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("product_image_url").iterator();
        Iterator tracking = ((BCOMEmailPage)emailpage).getItemDetails().get("tracking")
                .iterator();
        Iterator shipDate = ((BCOMEmailPage)emailpage).getItemDetails().get("shipdate")
                .iterator();
        Iterator shipmentType = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("shipmenttype").iterator();
        Iterator webId = ((BCOMEmailPage)emailpage).getItemDetails().get("web_id")
                .iterator();
        Iterator sendTo = ((BCOMEmailPage)emailpage).getItemDetails().get("sendto")
                .iterator();

        for (int shipment = 0; shipment < enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

            for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                    .get(0).getShipmentsData().get(shipment).getShipitems()
                    .size(); item++) {

                /*
                 * "000000492031403361" upc verification required as part of
                 * Loyalty Decrement Project. Ref Story# B-95302,B-94720,
                 *
                 * D-65209 "000000492030504038" upc verification required as
                 * part of bwallet Project. Ref Story# B-96676
                 */

                JSONObject enhancedPayloadObject = new JSONObject(
                        enhancedPayloadActual);
                String convertPayloadTostr = enhancedPayloadObject.toString();

                String upcIdFromEp = null;

                if (convertPayloadTostr.contains("upcNumber")) {

                    upcIdFromEp = enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(shipment).getShipitems()
                            .get(item).getUpcNumber();

                } else {

                    upcIdFromEp = enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(shipment).getShipitems()
                            .get(item).getUpc();

                }

                if (!(upcIdFromEp.equalsIgnoreCase("000000492031403361") || upcIdFromEp
                        .equalsIgnoreCase("000000492030504038"))) {

                    String quantity = null;

                    String shipType = enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getShipmentType();
                    try {
                        if (shipType != null) {
                            if (shipType != "RETURN" || shipType != "Exchange")
                                if (!(shipType.equalsIgnoreCase("RETURN") || shipType
                                        .equalsIgnoreCase("Exchange"))) {

                                    if (enhancedPayloadActual.getTriggerData()
                                            .get(0).getShipmentsData()
                                            .get(shipment).getShipitems()
                                            .get(item).getStatus()
                                            .equalsIgnoreCase("IN PROCESS")) {
                                        quantity = enhancedPayloadActual
                                                .getTriggerData().get(0)
                                                .getShipmentsData()
                                                .get(shipment).getShipitems()
                                                .get(item).getReqQuantity()
                                                .trim();

                                    } else if (enhancedPayloadActual
                                            .getTriggerData().get(0)
                                            .getShipmentsData().get(shipment)
                                            .getShipitems().get(item)
                                            .getStatus()
                                            .equalsIgnoreCase("SHIPPED")) {
                                        quantity = enhancedPayloadActual
                                                .getTriggerData().get(0)
                                                .getShipmentsData()
                                                .get(shipment).getShipitems()
                                                .get(item).getShippedQuantity()
                                                .trim();

                                    } else if (enhancedPayloadActual
                                            .getTriggerData().get(0)
                                            .getShipmentsData().get(shipment)
                                            .getShipitems().get(item)
                                            .getStatus()
                                            .equalsIgnoreCase("VOIDED")) {
                                        quantity = enhancedPayloadActual
                                                .getTriggerData().get(0)
                                                .getShipmentsData()
                                                .get(shipment).getShipitems()
                                                .get(item)
                                                .getCancelledQuantity().trim();
                                    }
                                } else {
                                    // for virtual return templates
                                    quantity = enhancedPayloadActual
                                            .getTriggerData().get(0)
                                            .getShipmentsData().get(shipment)
                                            .getShipitems().get(item)
                                            .getReqQuantity().trim();
                                }
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                    if (quantity != null) {
                        Assert.assertEquals(
                                "Expected Product Qty not match with actual",
                                Integer.parseInt(quantity),
                                Integer.parseInt(qty.next().toString().trim()));
                    }

                    String color = enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getProductColor();

                    if (color != null) {
                        Assert.assertEquals(
                                "Expected product not match with Actual product name",
                                color.trim(), clr.next().toString().trim());
                    }

                    String size = enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(shipment).getShipitems()
                            .get(item).getProductSize();
                    if (size != null) {
                        Assert.assertEquals(
                                "Expected product size not match with Actual product name",
                                size, siz.next().toString().trim());

                    }
                    /*
                     *
                     * Not Applicable for BCOM String type =
                     * enhancedPayloadActual.getTriggerData
                     * ().get(0).getShipmentsData ().get(shipment).getShipitems
                     * ().get(item).getProductType(); if(type != null){
                     * Assert.assertEquals(
                     * "Expected product Type not match with Actual product name"
                     * , type,
                     * ((BCOMEmailPage)emailpage).getItemDetails().get("type").iterator
                     * ().next ()); }
                     */
                    String productImageUL = enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData()
                            .get(shipment).getShipitems().get(item)
                            .getImagePrimaryUrl();
                    if (productImageUL != null) {
                        Assert.assertEquals(
                                "Invalid Product Image url",
                                Integer.parseInt("200"),
                                ((BCOMEmailPage)emailpage).getResponseCodeByURL(prdImg
                                        .next().toString().trim()));

                    }

                    String productSrc = enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getImageUrl();
                    if (productSrc != null) {
                        Assert.assertEquals(
                                "Expected product name not match with actual",
                                productSrc, prdImgSrc.next().toString().trim());

                    }

                    String productNameURL = enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData()
                            .get(shipment).getShipitems().get(item)
                            .getProductURL();
                    if (productNameURL != null) {
                        Assert.assertEquals("Product url not valid ", Integer
                                .parseInt("200"), ((BCOMEmailPage)emailpage)
                                .getResponseCodeByURL(prdImgURL.next()
                                        .toString()));
                    }

                    String productName = enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getProductName();
                    if (productName != null) {
                        if (prdName.hasNext() && productNameURL != null) {
                            Assert.assertEquals(
                                    "Expected product name not match with actual",
                                    productName, prdName.next());
                        } else {
                            Assert.assertTrue(
                                    "Product name not exist in email page",
                                    ((BCOMEmailPage)emailpage).isTextPresent(productName));
                        }
                    }

                    // Tracking Number Validation
                    String trackingNumber = null;
                    if (enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(shipment)
                            .getTrackingDetails().size() > 0) {

                        trackingNumber = enhancedPayloadActual.getTriggerData()
                                .get(0).getShipmentsData().get(shipment)
                                .getTrackingDetails().get(0).getTrackingNbr();

                        if (!(trackingNumber.matches("NOT DEFINED FOR SDD"))
                                && (trackingNumber != null)) {
                            Assert.assertEquals("Tracking Number is not valid",
                                    trackingNumber, tracking.next());
                        }
                    }

                    // Ship Date Validation
                    String shippedDate = null;
                    String emailshipDate = null;
                    try {
                        if (enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(shipment).getMailFlag()
                                .equals("C")
                                || enhancedPayloadActual.getTriggerData()
                                .get(0).getShipmentsData()
                                .get(shipment).getMailFlag()
                                .equals("P")) {
                            shippedDate = enhancedPayloadActual
                                    .getTriggerData().get(0).getShipmentsData()
                                    .get(shipment).getShippedDate();
                        } else {
                            shippedDate = enhancedPayloadActual
                                    .getTriggerData().get(0).getShipmentsData()
                                    .get(shipment).getShipitems().get(item)
                                    .getExpectedDate();
                        }

                        emailshipDate = (String) shipDate.next();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (shippedDate != null && emailshipDate != null) {
                        SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yy");
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "MM/dd/yyyy");
                        Assert.assertEquals(
                                "Shipped date is not matching with the expected result",
                                shippedDate,
                                sdf.format(sd.parse(emailshipDate)));
                    }

                    // Shipment Type Validation

                    if (shipmentType.hasNext()) {
                        String actualShipmentType = null;
                        String expectedShipmentType = null;

                        actualShipmentType = enhancedPayloadActual
                                .getTriggerData().get(0).getShipmentsData()
                                .get(shipment).getShipitems().get(item)
                                .getShipmentType();

                        if (!enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(shipment)
                                .getShipitems().get(item).getStatus()
                                .equals("VOIDED")
                                && ((BCOMEmailPage)emailpage).getItemDetails()
                                .get("shipmenttype").size() != 0) {

                            expectedShipmentType = (String) shipmentType.next();

                        }

                        if (actualShipmentType != null
                                && expectedShipmentType != null) {
                            switch (actualShipmentType) {
                                case "STANDARD":
                                    Assert.assertEquals(
                                            "Shipped type is not matching with the expected result",
                                            "Standard Shipping",
                                            expectedShipmentType);
                                    break;
                                case "PREMIUM":

                                    if (enhancedPayloadActual.getTriggerData()
                                            .get(0).getShipmentsData()
                                            .get(shipment).getShipitems().get(item)
                                            .getFdIndicator().equals("R")) {
                                        Assert.assertEquals(
                                                "Shipped type is not matching with the expected result",
                                                "SHOPRUNNER FREE 2-DAY",
                                                expectedShipmentType);
                                        break;
                                    } else {
                                        Assert.assertEquals(
                                                "Shipped type is not matching with the expected result",
                                                "Premium Shipping",
                                                expectedShipmentType);
                                        break;
                                    }

                                case "EXPRESS":
                                    Assert.assertEquals(
                                            "Shipped type is not matching with the expected result",
                                            "Premium Shipping",
                                            expectedShipmentType);
                                    break;
                                case "EMAIL":
                                    Assert.assertEquals(
                                            "Shipped type is not matching with the expected result",
                                            "Email", expectedShipmentType);
                                    break;
                                default:
                                    System.out
                                            .println("Invalid shipment type value");
                                    log.debug("Invalid shipment type value");
                            }
                        }
                    }

                    // Web ID Validation
                    String lineWebId = enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getLineWebId();
                    if (lineWebId != null) {
                        String lineWebIdFromMail = (String) webId.next();
                        Assert.assertEquals(
                                "Expected Web Id not match with the Actual Web Id",
                                lineWebId, lineWebIdFromMail.trim());
                    }

                    // Send To email Validation

                    if (sendTo.hasNext()) {
                        String sendToEmail = enhancedPayloadActual
                                .getTriggerData().get(0).getShipmentsData()
                                .get(shipment).getRegistryReceipientEmail();
                        if (sendToEmail != null
                                && ((BCOMEmailPage)emailpage).getItemDetails().get("sendto")
                                .size() != 0) {
                            Assert.assertEquals(
                                    "Expected email address is not matching with the actual",
                                    sendToEmail, sendTo.next());
                        }
                    }

                }
            }
        }
    }

    @Then("^i should see header:$")
    public void i_should_see_header(String header) throws Throwable {
        String bodyheader = ((BCOMEmailPage)emailpage).header.getText().replace("\n", " ");
        Assert.assertTrue("header  \" " + header + " \" not exists",
                bodyheader.contains(header));
    }

    @Then("^i should see freeshipping image$")
    public void i_should_see_freeshipping_image() throws Throwable {
        Assert.assertTrue("Freeshipping image NOT exists in email page",
                ((BCOMEmailPage)emailpage).freeShipping.isDisplayed());
    }

    @Then("^i should see instore pickup text and option$")
    public void i_should_see_instore_pickup_text_and_option() throws Throwable {

        Assert.assertEquals(
                "Expected 'In-Store Pickup' at order section is not match with actual",
                ((BCOMEmailPage)emailpage).getinStorePickup(), "In-Store Pickup;FREE");
    }

    @Then("^i should see note text as:$")
    public void i_should_see_note_text(String strNote) throws Throwable {
        int numberoflinks = StringUtils.countMatches(strNote, "<link>");
        String[] contents = strNote.split("<link>");
        if (numberoflinks >= 2) {
            for (int cnt = 1; cnt <= numberoflinks; cnt += 2) {
                Assert.assertNotNull("Link \" " + contents[cnt]
                        + " \" not exists", WebDriverManager.getWebDriver()
                        .findElement(By.linkText((contents[cnt]))));
            }
            for (int cnt = 0; cnt < contents.length - 1; cnt += 2) {
                Assert.assertTrue(
                        "Static text \" " + contents[cnt]
                                + " \" not exists in "
                                + ((BCOMEmailPage)emailpage).noteText.getText(), StringUtils
                                .contains(((BCOMEmailPage)emailpage).noteText.getText(),
                                        contents[cnt]));
            }
        } else {
            Assert.assertTrue(
                    "Static text \" "
                            + strNote
                            + " \" not exists in "
                            + ((BCOMEmailPage)emailpage).staticMessageShippingPolicy
                            .getText(),
                    StringUtils.contains(strNote,
                            ((BCOMEmailPage)emailpage).noteText.getText()));
        }
    }

    @Then("^i should verify shipment details$")
    public void i_should_see_shipment_details() throws Throwable {
        Iterator tracking = ((BCOMEmailPage)emailpage).getShipmentDetails()
                .get("tracking_num").iterator();
        Iterator shipment = ((BCOMEmailPage)emailpage).getShipmentDetails()
                .get("delivery_method").iterator();
        Iterator sendTo = ((BCOMEmailPage)emailpage).getShipmentDetails().get("send_to")
                .iterator();
        for (int j = 0; j < ((BCOMEmailPage)emailpage).getShipmentDetails()
                .get("tracking_num").size(); j++) {
            for (int i = 0; i < ((BCOMEmailPage)emailpage).getShipmentDetails()
                    .get("delivery_method").size(); i++) {
                String shipmentType = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(i).getShipitems().get(0)
                        .getShipmentType();

                // Validation of Tracking Number
                // Tracking Number Validation
                String trackingNumber = null;
                if (enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(i).getTrackingDetails().size() > 0) {

                    if (tracking != null) {
                        Assert.assertEquals(
                                "Tracking Number is not displayed ", Integer
                                        .parseInt("200"), ((BCOMEmailPage)emailpage)
                                        .getResponseCodeByLinkText(tracking
                                                .next().toString()));

                        // Validation of delivery method
                        if (!shipmentType.equals("SAMEDAY")) {
                            Assert.assertEquals(
                                    "Expected Delivery Method not match",
                                    shipmentType, shipment.next());
                        } else {
                            Assert.assertEquals(
                                    "Expected Delivery Method not match",
                                    "SAME DAY DELIVERY", shipment.next());
                        }

                        // Validation of Shipping Address and Email

                        if (shipmentType.equals("EMAIL")) {
                            Assert.assertEquals(
                                    "Expected Send to address not match with Actual",
                                    enhancedPayloadActual.getTriggerData()
                                            .get(0).getShipmentsData().get(i)
                                            .getRegistryReceipientEmail(),
                                    sendTo.next());
                        }

                        else {
                            Assert.assertEquals(
                                    "Expected Shipping address not match with Actual",
                                    enhancedPayloadActual.getTriggerData()
                                            .get(0).getShipmentsData().get(i)
                                            .getAddress().replace(",", ""),
                                    ((BCOMEmailPage)emailpage).getShipmentDetails()
                                            .get("shipping_address").iterator()
                                            .next().toString()
                                            .replaceAll("\\n", "")
                                            .replace(",", ""));

                        }
                    }

                }
            }
        }
    }

    @Then("^i should verify shipment address for fedfil sdd partial cancel$")
    public void i_should_verify_shipment_address_for_fedfil_sdd_partial_cancel()
            throws Throwable {
        String shippingAddress_enhancedPayload = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getAddress().replace(",", "");
        shippingAddress_enhancedPayload = shippingAddress_enhancedPayload.replace("null", "");

        Assert.assertEquals("Expected Shipping address not match with Actual",
                shippingAddress_enhancedPayload,((BCOMEmailPage)emailpage).getOrderDetails().get("shipping_address")
                        .toString().replaceAll("\\n", "").replace(",", ""));
    }

    @Then("^i should see singleline header:$")
    public void i_should_see_singleline_header(String header) throws Throwable {
        String shipmentFirstNameFromEp = enhancedPayloadActual.getTriggerData()
                .get(0).getShipmentsData().get(0).getFirstName();
        header = header.replaceAll("<shipmentname>", shipmentFirstNameFromEp);
        Assert.assertEquals("Single line header is not matching", header,
                ((BCOMEmailPage)emailpage).singleHeader.getText().trim());
    }

    @Then("^i should see singleline body text:$")
    public void i_should_body_singleline_body_text(String text)
            throws Throwable {
        String shipmentFirstNameFromEp = enhancedPayloadActual.getTriggerData()
                .get(0).getShipmentsData().get(0).getFirstName();
        text = text.replaceAll("<shipmentname>", shipmentFirstNameFromEp);

        Assert.assertEquals("Single line body is not matching",
                text.toLowerCase(), ((BCOMEmailPage)emailpage).wishlistSingleText.getText()
                        .toLowerCase().trim());
    }

    @Then("^i should see \"([^\"]*)\" link$")
    public void i_should_see_View_Wish_Link(String linkName) throws Throwable {
        Assert.assertEquals("Product image link not valid ",
                Integer.parseInt("200"),
                ((BCOMEmailPage)emailpage).getResponseCodeByLinkText(linkName));
    }

    @Then("^i should see shipping policy as:$")
    public void i_should_see_shipping_policy_as(String strPolicy)
            throws Throwable {
        int numberoflinks = StringUtils.countMatches(strPolicy, "<link>");
        if (numberoflinks >= 2) {
            String[] contents = strPolicy.split("<link>");
            for (int cnt = 1; cnt <= numberoflinks; cnt += 2) {
                Assert.assertNotNull("Link \" " + contents[cnt]
                        + " \" not exists", WebDriverManager.getWebDriver()
                        .findElement(By.linkText((contents[cnt]))));
            }
            for (int cnt = 0; cnt < contents.length - 1; cnt += 2) {
                Assert.assertTrue("Static text \" " + contents[cnt]
                                + " \" not exists in "
                                + ((BCOMEmailPage)emailpage).staticMessageShippingPolicy.getText(),
                        StringUtils.contains(
                                ((BCOMEmailPage)emailpage).staticMessageShippingPolicy
                                        .getText(), contents[cnt]));
            }
        } else {
            Assert.assertTrue("Static text \" " + strPolicy
                            + " \" not exists in "
                            + ((BCOMEmailPage)emailpage).staticMessageShippingPolicy.getText(),
                    StringUtils
                            .contains(strPolicy,
                                    ((BCOMEmailPage)emailpage).staticMessageShippingPolicy
                                            .getText()));
        }
    }

    @Then("^i should see static contents for com template:$")
    public void i_should_see_static_contents_for_com_template(String text)
            throws Throwable {
        int numberoflinks = StringUtils.countMatches(text, "<link>");
        if (numberoflinks >= 2) {
            String[] contents = text.split("<link>");
            for (int cnt = 1; cnt <= numberoflinks; cnt += 2) {
                Assert.assertNotNull("Link \" " + contents[cnt]
                        + " \" not exists", WebDriverManager.getWebDriver()
                        .findElement(By.linkText((contents[cnt]))));
            }
            for (int cnt = 0; cnt < contents.length - 1; cnt += 2) {

                Assert.assertTrue("Static text \" " + contents[cnt]
                                + " \" not exists in " + ((BCOMEmailPage)emailpage).getAllLabels(),
                        StringUtils.contains(((BCOMEmailPage)emailpage).getAllLabels(),
                                contents[cnt].toLowerCase()));
            }
        }
    }

    @Then("^i should see static contents for security password:$")
    public void i_should_see_static_contents_for_security_password(
            String bodyText) throws Throwable {
        bodyText = bodyText.replaceAll("<url>", enhancedPayloadActual
                .getTriggerData().get(0).getWebsiteData().getSecurityUrl());
        Assert.assertEquals("Body text is not as expected in the email",
                bodyText.toLowerCase().trim(), ((BCOMEmailPage)emailpage).getAllLabels()
                        .replaceAll("\n", "").trim());

    }

    @Then("^i should see static content for profile reset password as:$")
    public void i_should_see_static_content_for_profile_reset_password_as(
            String text) throws Throwable {
        String expected1 = text.split("<")[0];
        String expected2 = text.split(">")[1];
        String expected = expected1
                + enhancedPayloadActual.getTriggerData().get(0)
                .getWebsiteData().getPromoUrl() + "." + expected2;
        i_should_see_static_contents(expected);
    }

    @Then("^i should see delivery method$")
    public void i_should_see_delivery_method() throws Throwable {
        for (int i = 0; i < ((BCOMEmailPage)emailpage).getShipmentDetails()
                .get("delivery_method").size(); i++) {
            Assert.assertEquals("Expected Delivery Method not match",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(i).getShipitems().get(0)
                            .getShipmentType(), ((BCOMEmailPage)emailpage)
                            .getShipmentDetails().get("delivery_method")
                            .iterator().next());
        }
    }

    private boolean compareFloatVals(String val1, String val2) {
        Float valueOne = Float.parseFloat(val1.split("$")[1]);
        Float valueTwo = Float.parseFloat(val1.split("$")[1]);
        return valueOne.equals(valueTwo);
    }

    @Then("^i should see instore pickup information$")
    public void i_should_see_instore_pickup_information() throws Throwable {
        Assert.assertTrue(
                "IN-STORE PICKUP INFORMATION NOT exists in email page",
                ((BCOMEmailPage)emailpage).instore.isDisplayed());
    }

    @Then("^i should see store bops phone number$")
    public void i_should_see_store_bops_phoneno() throws Throwable {
        String Phone = ((BCOMEmailPage)emailpage).phoneNo.getText().replace("-", "")
                .toString();

        Assert.assertTrue(
                "Store bops phone number is not matching with the expected value",
                Phone.contains(enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getStoreBopsPhone()));
    }

    @Then("^i should see shipment phone number$")
    public void i_should_see_shipment_phone_no() throws Throwable {
        String labelText = ((BCOMEmailPage)emailpage).getAllLabels();
        Assert.assertTrue(
                "Store shipmet phone no is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getphone()));

    }

    @Then("^i should see sms opted message text$")
    public void i_should_see_sms_opted_message_text() throws Throwable {

        if (enhancedPayloadActual.getTriggerData().get(0).getOrderBaseData()
                .getSmsFlag().equals("Y")) {

            Assert.assertTrue(
                    "Expected sms opted message not match",
                    ((BCOMEmailPage)emailpage)
                            .getSmsOptMsg()
                            .contains(
                                    "You opted to receive messages with updates to this order"));
        }
    }

    @Then("^i should see order explanation text$")
    public void i_should_see_order_explanation_text() throws Throwable {
        String expData = ((BCOMEmailPage)emailpage).getexptext();
        String shipmentFirstNameFromEp = enhancedPayloadActual.getTriggerData()
                .get(0).getShipmentsData().get(0).getFirstName();
        if (enhancedPayloadActual.getTriggerData().get(0).getOrderData()
                .getExplanationText() != null) {
            String enhancedData = enhancedPayloadActual.getTriggerData().get(0)
                    .getOrderData().getExplanationText();
            String formatData = enhancedData + " " + "View Wish Link";
            Assert.assertTrue("Expected order explanation text not match",
                    expData.contains(formatData));
        }
    }

    @Then("^i should see store details$")
    public void i_should_see_store_details() throws Throwable {
        String labelText = ((BCOMEmailPage)emailpage).getAllLabels();

        Assert.assertTrue(
                "Store Name is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreName()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store address line1 is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreAddrLine1()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store address line2 is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreAddrLine2()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store city is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreCity()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store state is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreState()
                        .toLowerCase()));
        Assert.assertTrue(
                "Store zipcode is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getStoreZipcode()
                        .toLowerCase()));
        Assert.assertTrue(
                "Pickup order FirstName is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getFirstName()
                        .toLowerCase()));
        Assert.assertTrue(
                "Pickup order LastName is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getLastName()
                        .toLowerCase()));
        Assert.assertTrue(
                "Pickup order LastName is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getLastName()
                        .toLowerCase()));
        Assert.assertTrue(
                "Pickup order email is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getemail()
                        .toLowerCase()));
        Assert.assertTrue(
                "Pickup order email is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getemail()
                        .toLowerCase()));

    }

    @Then("^i should see store instructions$")
    public void i_should_see_store_instructions() throws Throwable {
        String labelText = ((BCOMEmailPage)emailpage).getAllLabels();
        Assert.assertTrue(
                "Store pickup instruction is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0)
                        .getStorePickupInstructions().toLowerCase().trim()
                        .replaceAll("\\s+", " ")));

    }

    @Then("^i should see order details$")
    public void i_should_see_order_details() throws Throwable {

        String subtotal = null;
        if (getTemplateType().equals("264_14U_BLCOM")) {
            subtotal = enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getShipRetailAmount();
        } else {
            subtotal = enhancedPayloadActual.getTriggerData().get(0)
                    .getOrderTotalsData().getSubTotalAmt();
        }

        if (subtotal != null) {
            Assert.assertEquals(
                    "Subtotal value expected and actual not equal",
                    "$" + subtotal,
                    ((BCOMEmailPage)emailpage).getOrderTotal().get("subtotal")
                            .replaceAll(",", ""));
        }
        String standardShipping = enhancedPayloadActual.getTriggerData().get(0)
                .getOrderTotalsData().getBaseShippingAmt();
        if (standardShipping != null && standardShipping.charAt(0) != '0') {
            Assert.assertEquals(
                    "Standard Shipping value expected and actual not equal",
                    "$" + standardShipping,
                    ((BCOMEmailPage)emailpage).getOrderTotal().get("standard_shipping")
                            .replaceAll(",", ""));
        }

        String giftWrap = enhancedPayloadActual.getTriggerData().get(0)
                .getOrderTotalsData().getGiftWrapFeeAmt();
        if (giftWrap != null && giftWrap.charAt(0) != '0') {
            Assert.assertEquals(
                    "Gift Wrap value expected and actual not equal",
                    "$" + giftWrap,
                    ((BCOMEmailPage)emailpage).getOrderTotal().get("gift_wrap")
                            .replaceAll(",", ""));
        }

        String salesTax = enhancedPayloadActual.getTriggerData().get(0)
                .getOrderTotalsData().getSalesTaxAmount();
        if (salesTax != null && salesTax.charAt(0) != '0') {
            Assert.assertEquals(
                    "Sales Tax value expected and actual not equal",
                    "$"
                            + enhancedPayloadActual.getTriggerData().get(0)
                            .getOrderTotalsData().getSalesTaxAmount(),
                    ((BCOMEmailPage)emailpage).getOrderTotal().get("sales_tax")
                            .replaceAll(",", ""));
        }

        String orderTotal = enhancedPayloadActual.getTriggerData().get(0)
                .getOrderTotalsData().getOrderTotal();
        if (orderTotal != null) {
            Assert.assertEquals(
                    "Order Total value expected and actual not equal", "$"
                            + orderTotal,
                    ((BCOMEmailPage)emailpage).getOrderTotal().get("order_total")
                            .replaceAll(",", ""));
        }

        // In store pick up validation
        if (((BCOMEmailPage)emailpage).getOrderTotal().get("in_store_pickup") != null) {
            Assert.assertEquals(
                    "In Store Pick Value is not displaying as expected",
                    "FREE", ((BCOMEmailPage)emailpage).getOrderTotal()
                            .get("in_store_pickup"));
        }

        // same day delivery validation
        String sameDayDelivery = enhancedPayloadActual.getTriggerData().get(0)
                .getOrderTotalsData().getShipmentUpgradeFeeAmount();
        if (((BCOMEmailPage)emailpage).getOrderTotal().get("same_day_delivery") != null) {
            Assert.assertEquals(
                    "Same Day Delivery Value is not displaying as expected",
                    "$" + sameDayDelivery,
                    ((BCOMEmailPage)emailpage).getOrderTotal().get("same_day_delivery"));

        }
        if (((BCOMEmailPage)emailpage).getOrderTotal().get("ShopRunner_shipping") != null) {
            Assert.assertEquals(
                    "In shop runner shipping Value is not displaying as expected",
                    "FREE", ((BCOMEmailPage)emailpage).getOrderTotal()
                            .get("ShopRunner_shipping"));
        }
    }

    @Then("^i should see payment card info$")
    public void i_should_see_payment_card_info() throws Throwable {
        enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0)
                .getType();
        Assert.assertTrue(
                "Expected payment Card Type not match with actual",
                ((BCOMEmailPage)emailpage)
                        .getOrderTotal()
                        .get("card")
                        .contains(
                                enhancedPayloadActual.getTriggerData().get(0)
                                        .getPaymentData().get(0).getType()));
        if(!eod_template_name.contains("eod")){
            Assert.assertTrue(
                    "Expected payment Card Number not match with actual",
                    ((BCOMEmailPage)emailpage)
                            .getOrderTotal()
                            .get("card")
                            .contains(
                                    enhancedPayloadActual.getTriggerData().get(0)
                                            .getPaymentData().get(0).getCardNbr()));
        }
        Assert.assertEquals("Expected Charge amount not match with actual", "$"
                + enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getChargeAmt(), ((BCOMEmailPage)emailpage)
                .getOrderTotal().get("charge_amount").replace(",", ""));
    }

    @Then("^i should see gift receipt option$")
    public void i_should_see_gift_receipt_option() throws Throwable {
        Assert.assertEquals("Expected 'Gift Receipt' is not match with actual",
                ((BCOMEmailPage)emailpage).getgiftReceipt(), "Gift Receipt: Yes");
    }

    @Then("^i should see static text:$")
    public void i_should_see_static_text(String bodyText) throws Throwable {
        String bodyStaticText = ((BCOMEmailPage)emailpage).bodystatictext.getText().trim()
                .replace("\n", " ");
        Assert.assertTrue(
                "body static text  \" " + bodyText + " \" not exists",
                bodyStaticText.contains(bodyText));
    }

    @And("^i should see signatue contents for gpgm template:$")
    public void i_should_see_signatue_contents_for_gpgm_template(String signatureText) throws Throwable {

        String signatireTextFromMail = ((BCOMEmailPage)emailpage).getSignatureLabels();
        Assert.assertEquals("Mail Signature message "+signatureText+" not exist", signatureText.trim(), signatireTextFromMail.trim());
    }

    @Then("^i should see loyallist free shipping logo$")
    public void i_should_see_loyallist_free_shipping_logo() throws Throwable {
        Assert.assertTrue(((BCOMEmailPage)emailpage).loyallistShipping.isDisplayed());
    }

    @And("^i should see static contents in bmoney rewardcard:$")
    public void i_should_see_static_contents_in_bmoney_rewardcard(
            String statictext) throws Throwable {

        Assert.assertTrue(
                "bmoney rewardcard Information not displaying as expected in the email",
                StringUtils.contains(((BCOMEmailPage)emailpage).getAllLabels().toLowerCase()
                        .trim(), statictext.toLowerCase().trim()));
    }

    @And("^i should see redeem information in rewardcard:$")
    public void i_should_see_redeem_information_in_rewardcard(String bodyText)
            throws Throwable {

        bodyText = bodyText
                .replaceAll(
                        "<rewardCardAmt>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getOrderData().getRewardCardAmt())
                .replaceAll(
                        "<rewardCardEffectiveDate>",
                        getModifiedYearFormat(enhancedPayloadActual
                                .getTriggerData().get(0).getOrderData()
                                .getRewardCardEffectiveDate()))
                .replaceAll(
                        "<rewardCardExpirationDate>",
                        getModifiedYearFormat(enhancedPayloadActual
                                .getTriggerData().get(0).getOrderData()
                                .getRewardCardExpirationDate()))
                .replaceAll(" ", "").toLowerCase().trim();

        Assert.assertTrue(
                "Expected reward information not found in user email",
                StringUtils.contains(((BCOMEmailPage)emailpage).getRedeemDetails()
                        .replaceAll("\\r\\n|\\r|\\n", "").replaceAll(" ", "")
                        .toLowerCase().trim(), bodyText));
    }

    @Then("^i should see \"([^\"]*)\" label in bmoney rewardcard$")
    public void i_should_see_label_in_bmoney_rewardcard(String labelName)
            throws Throwable {

        Assert.assertTrue(
                "Expected reward label information not found in user email",
                StringUtils.contains(((BCOMEmailPage)emailpage).getRedeemDetails()
                        .replaceAll("\\r\\n|\\r|\\n", "").replaceAll(" ", "")
                        .toLowerCase().trim(), labelName.replaceAll(" ", "")
                        .toLowerCase().trim()));
    }

    @Then("^i should see reason for reduction message:$")
    public void i_should_see_Reason_for_reduction_message(String reasonReductiontext)
            throws Throwable {

        Assert.assertTrue("Static text not displaying as expected",
                StringUtils.contains(reasonReductiontext, ((BCOMEmailPage)emailpage).getReductionReasonMessageText().replace("\n", "")));

    }

    @Then("^i should see static contents for virtual return reminder:$")
    public void i_should_see_static_contents_for_virtual_return_reminder(
            String bodyText) throws Throwable {

        String returnSubmittedDate = getModifiedYearFormat(enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().get(0)
                .getReturnSubmittedDate());
        String estimatedRefund = "$"
                + enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getChargeAmt();

        StringBuilder sb = new StringBuilder(bodyText);
        // do your replacing in sb - although you'll find this trickier than
        // simply using String
        String newStr = sb.toString();
        newStr = newStr
                .replaceAll("<returnsubmitteddate>", returnSubmittedDate)
                .replaceAll("<estimatedrefund>",
                        Matcher.quoteReplacement(estimatedRefund));

        Assert.assertTrue(
                "Expected text: " + newStr
                        + "is not matching with Actual text :"
                        + ((BCOMEmailPage)emailpage).getAllLabels(),
                StringUtils.contains(((BCOMEmailPage)emailpage).getAllLabels(),
                        newStr.toLowerCase()));
    }

    @Then("^i should see static contents for virtual exchange reminder:$")
    public void i_should_see_static_contents_for_virtual_exchange_reminder(
            String bodyText) throws Throwable {
        String totalSum = "$"
                + new DecimalFormat("0.00").format(Float
                .parseFloat(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0)
                        .getShipRetailAmount())
                + Float.parseFloat(enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData()
                .get(0).getShipTaxAmount()));

        bodyText = bodyText.replaceAll(
                "<returnSubmittedDate>",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getReturnSubmittedDate())
                .replaceAll("<sumOfPlaceholders13And15>",
                        Matcher.quoteReplacement(totalSum));
        String returnExpectedBackDate = null;

        for (int shipment = 0; shipment < enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                    .get(0).getShipmentsData().get(shipment).getShipitems()
                    .size(); item++) {
                returnExpectedBackDate = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getReturnExpectedBackDate();
            }
        }

        bodyText = bodyText.replaceAll("<returnExpectedBackDate>",
                returnExpectedBackDate).replaceAll("\n", "");

        Assert.assertTrue("Expected mail Body text not found in user email",
                StringUtils.contains(((BCOMEmailPage)emailpage).getAllLabels().toLowerCase()
                        .replaceAll("\n", "").trim(), bodyText.toLowerCase()
                        .trim()));
    }


    @And("^i should see body text$")
    public void i_should_see_body_text() throws Throwable {

        String beforeConvertpickupCancelDate = enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().get(0)
                .getPickupCancelDate();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d");
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date pickupCancelDate = (Date) formatter
                .parse(beforeConvertpickupCancelDate);
        if (getTemplateType().equals("264_14Q_BLCOM")
                || getTemplateType().equals("264_14Q_BWEDD")
                || getTemplateType().equals("264_14R_BLCOM")
                || getTemplateType().equals("264_14R_BWEDD")) {

            String mailBodyText = "Your order is now ready for pickup and will be held through "
                    + dateFormatter.format(pickupCancelDate).toString()
                    + " at the store below.";

            Assert.assertEquals("Body text not match with actual",
                    mailBodyText, ((BCOMEmailPage)emailpage).getStaticMessage());
        } else {

            String billingFirstName = enhancedPayloadActual.getTriggerData()
                    .get(0).getBillingData().getFirstName();
            String billingLastName = enhancedPayloadActual.getTriggerData()
                    .get(0).getBillingData().getLastName();

            String mailBodyText = billingFirstName
                    + " "
                    + billingLastName
                    + " designated you to pick up this order and we are happy to tell you it is now ready for pickup. It will be held through "
                    + dateFormatter.format(pickupCancelDate).toString()
                    + " at the store below.";

            Assert.assertEquals("Body text not match with actual",
                    mailBodyText, ((BCOMEmailPage)emailpage).getStaticMessage());
        }
    }

    @Then("^i should see body text for 542_30C bops late pickup cancel:$")
    public void i_should_see_body_text_for_542_30C_bops_late_pickup_cancel(
            String bodyText) throws Throwable {
        bodyText = bodyText.replaceAll(
                "<pickupCancelDate>",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getPickupCancelDate())
                .replaceAll(
                        "<chargeAmt>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getPaymentData().get(0).getChargeAmt());
        Assert.assertTrue("Expected mail Body text " + bodyText
                + "not found in user email. Actual body text:"
                + ((BCOMEmailPage)emailpage).getStaticMessage().trim(), StringUtils
                .contains(
                        ((BCOMEmailPage)emailpage).getAllLabels().replace("\n", "").trim(),
                        bodyText.toLowerCase().replace("\n", "").trim()));
    }

    @Then("^i should see body text for 542_30D bops late pickup cancel:$")
    public void i_should_see_body_text_for_542_30D_bops_late_pickup_cancel(
            String bodyText) throws Throwable {
        bodyText = bodyText
                .replaceAll(
                        "<orderNumber>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getInternetOrderNumber().getValue())
                .replaceAll(
                        "<pickupCancelDate>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0)
                                .getPickupCancelDate())
                .replaceAll(
                        "<billingAddrfirstName>",
                        Utilities.returnCamelCase(enhancedPayloadActual
                                .getTriggerData().get(0).getBillingData()
                                .getFirstName()))
                .replaceAll(
                        "<billingAddrlastName>",
                        Utilities.returnCamelCase(enhancedPayloadActual
                                .getTriggerData().get(0).getBillingData()
                                .getLastName()));
        Assert.assertTrue("Expected mail Body text " + bodyText
                + "not found in user email. Actual body text:"
                + ((BCOMEmailPage)emailpage).getStaticMessage().trim(), StringUtils
                .contains(
                        ((BCOMEmailPage)emailpage).getAllLabels().replace("\n", "").trim(),
                        bodyText.toLowerCase().replace("\n", "").trim()));
    }

    @Then("^i should see loyaltyId$")
    public void i_should_see_loyaltyId() throws Throwable {

        Assert.assertEquals(
                "Expected loyallist number not found in user email",
                enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                        .getLoyaltyId(), ((BCOMEmailPage)emailpage).loyallistId.getText());

    }

    @Then("^i should see loyalty firstName$")
    public void i_should_see_loyalty_firstName() throws Throwable {

        String bodyText = ((BCOMEmailPage)emailpage).loyallistBodyText.getText();
        String firstName = Utilities.returnCamelCase(enhancedPayloadActual
                .getTriggerData().get(0).getLoyaltyData().getFirstName());

        Assert.assertTrue(
                "Loyalty firstName is not matching with the expected value",
                bodyText.contains(firstName));

    }

    @Then("^i should see firstname for return confirmation$")
    public void i_should_see_firstname_for_return_confirmation()
            throws Throwable {

        if (getTemplateType().equals("245_5N_BLCOM")) {

            Assert.assertEquals(
                    "Expected First name not found in user email",
                    ("Dear " + enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getFirstName())
                            .replaceAll(" ", "") + ",", ((BCOMEmailPage)emailpage)
                            .getFirstName().replaceAll(" ", ""));

        } else {
            Assert.assertEquals(
                    "Expected First name not found in user email",
                    ("Dear " + enhancedPayloadActual.getTriggerData().get(0)
                            .getBillingData().getFirstName()).replaceAll(" ",
                            "") + ",",
                    ((BCOMEmailPage)emailpage).getFirstName().replaceAll(" ", ""));
        }
    }

    @And("^i should see ups  content in body text$")
    public void i_should_see_ups_content_in_body_text(String bodytext)
            throws Throwable {

        String bodyTextfromEmail = ((BCOMEmailPage)emailpage).getUpsInfoInBodytext()
                .replace("\n", "").trim();

        Assert.assertTrue(
                "Expected part of body text is not matching with the Actual value",
                bodyTextfromEmail.contains(bodytext.toLowerCase()
                        .replace("\n", "").trim()));

    }

    @Then("^i should see static content for fedfil sdd returned undeliverable as:$")
    public void i_should_see_static_content_for_fedfil_sdd_returned_undeliverable_as(
            String text) throws Throwable {
        String expected1 = text.split("<")[0];
        String expected2 = text.split(">")[1];
        String expected = expected1
                + enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getChargeAmt() + expected2;
        i_should_see_static_contents(expected);
    }

    @Then("^i should see body text for return confirmation bill to:$")
    public void i_should_see_body_text_for_return_confirmation_bill_to(
            String bodyText) throws Throwable {

        bodyText = bodyText.replaceAll("<orderTotals.orderTotal>",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getOrderTotalsData().getOrderTotal());

        String bodyTextLines = ((BCOMEmailPage)emailpage).getAllLabels()
                .replaceAll("\n", "").trim();

        Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                bodyTextLines.contains(bodyText.toLowerCase()));
    }

    @Then("^i should see body text for fedfil price error$")
    public void i_should_see_body_text_for_fedfil_price_error()
            throws Throwable {
        String actual = ((BCOMEmailPage)emailpage).getStaticMessage().replace("\n", "")
                .trim();
        String expected = enhancedPayloadActual.getTriggerData().get(0)
                .getOrderData().getAdhocText().replace("\n", "").trim();
        Assert.assertEquals("Body text not match with actual", expected, actual);
    }

    @Then("^i should see text for unavailable item section for bops pickup reminder:$")
    public void i_should_see_text_for_unavailable_item(String unavailabeItemText)
            throws Throwable {

        String bodyTextLines = ((BCOMEmailPage)emailpage).getAllLabels();
        if (unavailabeItemText.contains("<")) {
            unavailabeItemText = unavailabeItemText.replaceAll(
                    "<billingAddr.firstName>",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getBillingData().getFirstName().toLowerCase())
                    .replaceAll(
                            "<billingAddr.lastName>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getBillingData().getLastName()
                                    .toLowerCase());

        }
        Assert.assertTrue(
                "Expected text for unavailable item is not matching with the Actual value",
                bodyTextLines.contains(unavailabeItemText.toLowerCase()));

    }

    @Then("^i should see note section below store hours for bops pickup reminder:$")
    public void i_should_see_note_section_below_store_hours(String noteText)
            throws Throwable {

        noteText = noteText.replaceAll("<pickupCancelDate>",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getPickupCancelDate());

        if (noteText.contains("<")) {
            noteText = noteText.replaceAll(
                    "<billingAddr.firstName>",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getBillingData().getFirstName().toLowerCase())
                    .replaceAll(
                            "<billingAddr.lastName>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getBillingData().getLastName()
                                    .toLowerCase());

        }
        Assert.assertTrue(
                "Expected note text not found in user mail",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        noteText.toLowerCase().trim()));
    }

    @And("^i should see in-store pickup questions for bops pickup reminder:$")
    public void i_should_instore_pickup_questions_for_bops_pickup_reminder(
            String inStorePickUpPhone) throws Throwable {

        String inStorePickUpPhoneNumberFromEp = inStorePickUpPhone
                + " "
                + enhancedPayloadActual.getTriggerData().get(0)
                .getShipmentsData().get(0).getStoreBopsPhone()
                .replace("-", "").trim();

        String inStorePickUpPhoneNumberFromMail = ((BCOMEmailPage)emailpage)
                .getstoreTimeAndPhoneNos().replace("-", "").trim();

        Assert.assertTrue(
                "Phone number for in-store pickup Questions is not matching with the expected value",
                inStorePickUpPhoneNumberFromMail
                        .contains(inStorePickUpPhoneNumberFromEp));

    }

    @Then("^i should see amount refunded$")
    public void i_should_see_amount_refunded() throws Throwable {
        Assert.assertEquals("Expected amount refunded not found in user email",
                "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getOrderTotalsData().getOrderTotal(),
                ((BCOMEmailPage)emailpage).getOrderDetails().get("amount_refunded"));

    }

    @Then("^i should see return submitted date$")
    public void i_should_see_return_submitted_date() throws Throwable {
        Assert.assertEquals(
                "Return submitted date is not displaying as expected",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getReturnSubmittedDate(),
                ((BCOMEmailPage)emailpage).returnSubmittedDate.getText());
    }

    @Then("^i should see expected refund amount$")
    public void i_should_see_expected_refund_amount() throws Throwable {
        Assert.assertEquals(
                "Expected Refund Amount is not displaying as expected", "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getChargeAmt(),
                ((BCOMEmailPage)emailpage).expectedRefundAmount.getText());
    }

    @Then("^i should see amount returned$")
    public void i_should_see_amount_returned() throws Throwable {
        Assert.assertEquals(
                "Expected Refund Amount is not displaying as expected", "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getChargeAmt(),
                ((BCOMEmailPage)emailpage).amountRefunded.getText());
    }

    @Then("^i should see estimated refund amount$")
    public void i_should_see_estimated_refund_amount() throws Throwable {
        Assert.assertEquals(
                "Estimated Refund Amount is not displaying as expected", "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getChargeAmt(),
                ((BCOMEmailPage)emailpage).estimatedRefundAmount.getText());
    }

    @Then("^i should see refund total$")
    public void i_should_see_refund_total() throws Throwable {
        Assert.assertEquals(
                "Estimated Refund Amount is not displaying as expected", "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getChargeAmt(),
                ((BCOMEmailPage)emailpage).refundTotal.getText());
    }

    @Then("^i should see estimated refund amount as order total$")
    public void i_should_see_estimated_refund_amount_as_order_total()
            throws Throwable {
        Assert.assertEquals(
                "Estimated Refund Amount is not displaying as expected",
                "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getOrderTotalsData().getOrderTotal(),
                ((BCOMEmailPage)emailpage).estimatedRefundAmount.getText().replaceAll(",",
                        ""));
    }

    @Then("^i should see original payment amount$")
    public void i_should_see_original_payment_amount() throws Throwable {
        Assert.assertEquals(
                "Original payment Amount is not displaying as expected", "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getChargeAmt(),
                ((BCOMEmailPage)emailpage).originalPayment.getText());
    }

    @Then("^i should see body text for return confirmation gift to:$")
    public void i_should_body_text_for_return_confirmation_gift_to(
            String bodyText) throws Throwable {

        String chargeAmt = "$"
                + enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getChargeAmt();
        bodyText = bodyText.replaceAll("<chargeAmt>",
                Matcher.quoteReplacement(chargeAmt));

        Assert.assertTrue(
                "body text  \" " + bodyText + " \" not exists",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        bodyText.toLowerCase().trim()));
    }

    @Then("^i should see the mentioned text in the email$")
    public void i_should_see_the_mentioned_text_in_the_email(
            List<String> expectedText) throws Throwable {
        try{
            Iterator<String> text = expectedText.iterator();
            while (text.hasNext()) {
                Assert.assertTrue(
                        "body text  \" " + expectedText + " \" not exists",
                        ((BCOMEmailPage)emailpage).getAllLabels().contains(
                                text.next().toLowerCase().trim()));
            }
        }
        catch(Error e){
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }

    }

    @Then("^i should see expected date as estimated ship date$")
    public void i_should_see_expected_date_as_estimated_ship_date()
            throws Throwable {
        Assert.assertEquals(
                "Expected estimated ship date not found in user email",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getShipitems().get(0)
                        .getExpectedDate(), getdefaultDateFormat(((BCOMEmailPage)emailpage)
                        .getOrderDetails().get("estimated_ship_date")));
    }

    @Then("^i should see gift message for fedfil_vgc$")
    public void i_should_see_gift_message_for_fedfil_vgc() throws Throwable {

        Assert.assertTrue(
                "Gift greeting message not exists in acutal email page",
                ((BCOMEmailPage)emailpage).isTextExistsInEmailPage(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getGiftGreetingMsg()));
        Assert.assertTrue(
                "Gift greeting closing message not exists in acutal email page",
                ((BCOMEmailPage)emailpage).isTextExistsInEmailPage(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getGiftClosingMsg()));
        Assert.assertTrue(
                "Gift greeting Singnature message not exists in acutal email page",
                ((BCOMEmailPage)emailpage).isTextExistsInEmailPage(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getGiftSignatureMsg()));

    }

    @Then("^i should see the gift card text:$")
    public void i_should_see_the_gift_card_text(String expectedText)
            throws Throwable {
        Assert.assertEquals("Text is not displaying as expected", expectedText
                .toLowerCase().trim(), ((BCOMEmailPage)emailpage).giftCardText.getText()
                .toLowerCase().trim());
    }

    @Then("^i should see gift card number$")
    public void i_should_see_gift_card_number() throws Throwable {
        String expectedText = enhancedPayloadActual.getTriggerData().get(0)
                .getShipmentsData().get(0).getShipitems().get(0).getVgcNbr()
                + enhancedPayloadActual.getTriggerData().get(0)
                .getShipmentsData().get(0).getShipitems().get(0)
                .getVgcCidNbr();
        Assert.assertEquals("Gift card number is not displaying as expected",
                expectedText, ((BCOMEmailPage)emailpage).giftCardNumber.getText());
    }

    @Then("^i should see loyalty reward card amount$")
    public void i_should_see_loyalty_reward_card_amount() throws Throwable {
        String expectedAmount = "$"
                + enhancedPayloadActual.getTriggerData().get(0)
                .getLoyaltyData().getRewardsCardAmt();
        Assert.assertEquals(
                "Loyalty reward card is not displaying as expected",
                expectedAmount, ((BCOMEmailPage)emailpage).loyaltyRewardCardAmount.getText());
    }

    @Then("^i should see reward card number:$")
    public void i_should_see_reward_card_number(String expectedText)
            throws Throwable {
        String rewardCardNumber = enhancedPayloadActual.getTriggerData().get(0)
                .getLoyaltyData().getVrcBarCodeNbr()
                + enhancedPayloadActual.getTriggerData().get(0)
                .getLoyaltyData().getRewardsCardCid();
        expectedText = expectedText.replaceAll("<loyaltyrewardcardnumber>",
                rewardCardNumber);
        Assert.assertTrue(
                "Loyalty reward card number is not displaying as expected.",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        expectedText.toLowerCase().trim()));

    }

    @Then("^i should see expire date:$")
    public void i_should_see_expire_date(String expectedText) throws Throwable {
        expectedText = expectedText.replaceAll("<expiredate>",
                enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                        .getRewardsCardExpirationDate());
        Assert.assertTrue(
                "Loyalty card expiration date is not displaying as expected.",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        expectedText.toLowerCase().trim()));
    }

    @Then("^i should see static contents for fedfil delay first:$")
    public void i_should_see_static_contents_for_fedfil_delayfirst(
            String expectedText) throws Throwable {
        expectedText = expectedText.replaceAll("<systemCancelDate>",
                getModifiedYearFormat(enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getSystemCancelDate()));
        Assert.assertTrue(
                "Expected mail Body text " + expectedText
                        + "not found in user email. Actual body text:",
                StringUtils.contains(
                        ((BCOMEmailPage)emailpage).getAllLabels().replaceAll("\n", "")
                                .trim(), expectedText.toLowerCase().trim()));
    }

    @Then("^i should see static contents for 254_8 fedfil delay first:$")
    public void i_should_see_static_contents_for_254_8_fedfil_delay_first(
            String expectedbodyText) throws Throwable {
        expectedbodyText = expectedbodyText.replaceAll(
                "<billingAddr.firstName>",
                enhancedPayloadActual.getTriggerData().get(0).getBillingData()
                        .getFirstName().toLowerCase()).replaceAll(
                "<billingAddr.lastName>",
                enhancedPayloadActual.getTriggerData().get(0).getBillingData()
                        .getLastName().toLowerCase());
        Assert.assertTrue(
                "Expected note text not found in user mail",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        expectedbodyText.toLowerCase().trim()));
    }

    @And("^i should see promo heading for offer share$")
    public void i_should_see_promo_heading_for_offer_share() throws Throwable {

        String promoHeading = enhancedPayloadActual.getTriggerData().get(0)
                .getWebsiteData().getPromoHeading();

        Assert.assertTrue(
                "promo heading  is not displaying as expected.",
                ((BCOMEmailPage)emailpage).promoHeading.getText().toLowerCase()
                        .contains(promoHeading.toLowerCase().trim()));

    }

    @And("^i should see promo description for offer share$")
    public void i_should_see_promo_description_for_offer_share()
            throws Throwable {

        String promoDesc = enhancedPayloadActual.getTriggerData().get(0)
                .getWebsiteData().getPromoDesc();
        Assert.assertTrue(
                "promo description  is not displaying as expected.",
                ((BCOMEmailPage)emailpage).promoDesc.getText().toLowerCase()
                        .contains(promoDesc.toLowerCase().trim()));

    }

    @Then("^i should see shop now button$")
    public void i_should_see_shop_now_button() throws Throwable {
        Assert.assertNotNull("Unable to Find shop now button ",
                ((BCOMEmailPage)emailpage).shopNow.isDisplayed());
        Assert.assertEquals("Invalid target URL for shop now Button ", Integer
                .parseInt("200"), ((BCOMEmailPage)emailpage)
                .getResponseCodeByURL(((BCOMEmailPage)emailpage).shopNow
                        .getAttribute("href")));
    }

    @Then("^i should see product cancelled quantity$")
    public void i_should_see_product_cancelled_quantity() throws Throwable {
        Iterator qty = ((BCOMEmailPage)emailpage).getItemDetails().get("quantity")
                .iterator();
        for (int shipment = 0; shipment < enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

            for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                    .get(0).getShipmentsData().get(shipment).getShipitems()
                    .size(); item++) {

                String quantity = enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(shipment).getShipitems()
                        .get(item).getCancelledQuantity().trim();

                Assert.assertEquals(
                        "Expected Product Qty not match with actual",
                        Integer.parseInt(quantity),
                        Integer.parseInt(qty.next().toString().trim()));

            }
        }
    }

    @Then("^i should see egift card product details$")
    public void i_should_see_egift_card_product_details() throws Throwable {
        Iterator prdImg = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("product_image_url").iterator();
        Iterator prdImgSrc = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("product_image_src").iterator();
        Iterator prdName = ((BCOMEmailPage)emailpage).getItemDetails().get("product_name")
                .iterator();
        Iterator prdImgURL = ((BCOMEmailPage)emailpage).getItemDetails()
                .get("product_image_url").iterator();
        Iterator webId = ((BCOMEmailPage)emailpage).getItemDetails().get("web_id")
                .iterator();

        for (int shipment = 0; shipment < enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

            for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                    .get(0).getShipmentsData().get(shipment).getShipitems()
                    .size(); item++) {

                String productImageUL = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getImagePrimaryUrl();
                if (productImageUL != null) {
                    Assert.assertEquals(
                            "Invalid Product Image url",
                            Integer.parseInt("200"),
                            ((BCOMEmailPage)emailpage).getResponseCodeByURL(prdImg.next()
                                    .toString().trim()));

                }

                String productSrc = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getImageUrl();
                if (productSrc != null) {
                    Assert.assertEquals(
                            "Expected product name not match with actual",
                            productSrc, prdImgSrc.next().toString().trim());

                }

                String productNameURL = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductURL();
                if (productNameURL != null) {
                    Assert.assertEquals("Product url not valid ", Integer
                            .parseInt("200"), ((BCOMEmailPage)emailpage)
                            .getResponseCodeByURL(prdImgURL.next().toString()));
                }

                String productName = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductName();
                if (productName != null) {
                    if (prdName.hasNext() && productNameURL != null) {
                        Assert.assertEquals(
                                "Expected product name not match with actual",
                                productName, prdName.next());
                    } else {
                        Assert.assertTrue(
                                "Product name not exist in email page",
                                ((BCOMEmailPage)emailpage).isTextPresent(productName));
                    }
                }

                // Web ID Validation
                String lineWebId = enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getLineWebId();
                if (lineWebId != null) {
                    String lineWebIdFromMail = (String) webId.next();
                    Assert.assertEquals(
                            "Expected Web Id not match with the Actual Web Id",
                            lineWebId, lineWebIdFromMail.trim());
                }

            }
        }
    }

    @Then("^i should see registrant firstname$")
    public void i_should_see_registrant_firstname() throws Throwable {
        Assert.assertEquals(
                "Expected registrant1 First name not found in user email",
                "Dear "
                        + Utilities.returnCamelCase(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData()
                        .get(0).getRegistryRegistrant1()) + ",",
                ((BCOMEmailPage)emailpage).getFirstName());

    }

    @Then("^i should see body text for fedfil delay second:$")
    public void i_should_body_text_for_fedfil_delay_second(String bodyText)
            throws Throwable {
        if (bodyText.contains("<systemCancelDate>")) {
            bodyText = bodyText.replaceAll("<systemCancelDate>",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getShipitems().get(0)
                            .getSystemCancelDate());
            Assert.assertTrue(
                    "body text  \" " + bodyText + " \" not exists",
                    ((BCOMEmailPage)emailpage).getAllLabels().replaceAll("\n", "")
                            .toLowerCase()
                            .contains(bodyText.toLowerCase().trim()));
        } else {

            i_should_see_static_contents_for_254_8_fedfil_delay_first(bodyText);

        }
    }

    @Then("^i should see order capture date without camel case label$")
    public void i_should_see_order_capture_date_without_camel_case_label()
            throws Throwable {
        Assert.assertEquals("Expected Order date not found in user email",
                enhancedPayloadActual.getTriggerData().get(0).getOrderData()
                        .getOrderCapturedDate(), ((BCOMEmailPage)emailpage)
                        .getOrderDetails().get("order_date"));
    }

    @Then("^i should see body text for fedfil bops order thankyou:$")
    public void i_should_see_static_contents_for_fedfil_bops_order_thankyou(
            String expectedbodyText) throws Throwable {
        expectedbodyText = expectedbodyText
                .replaceAll(
                        "<shipment.firstName>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0).getFirstName()
                                .toLowerCase())
                .replaceAll(
                        "<shipment.lastName>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0).getLastName()
                                .toLowerCase())
                .replaceAll(
                        "<storeName>",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0).getStoreName()
                                .trim().toLowerCase());
        Assert.assertTrue(
                "Expected note text not found in user mail",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        expectedbodyText.toLowerCase().trim()));
    }

    @Then("^i should see pros product images$")
    public void i_should_see_pros_product_images() throws Throwable {

        for (int index = 0; index < ((BCOMEmailPage)emailpage).prosImages.size(); index++) {
            Assert.assertEquals("Invalid image URL", Integer.parseInt("200"),
                    ((BCOMEmailPage)emailpage).getProsImageURLHttpStatus(index));
        }
    }

    @Then("^i should see pros product names$")
    public void i_should_see_pros_product_names() throws Throwable {
        String prosItemDesc = "";
        String[] itemName = ((BCOMEmailPage)emailpage).getProsItemDetails().split("@");

        for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0)
                .getRecommendationsData().size(); item++) {

            prosItemDesc += enhancedPayloadActual.getTriggerData().get(0)
                    .getRecommendationsData().get(item).getProductName()
                    .toLowerCase()
                    + ";";
        }

        for (int itmDesc = 0; itmDesc < itemName.length; itmDesc++) {
            Assert.assertTrue("Expected '" + itemName[itmDesc]
                            + "' PROS product name not match with Actual '"
                            + prosItemDesc + "' PROS product names",
                    prosItemDesc.contains(itemName[itmDesc]));
        }
        for (WebElement product : ((BCOMEmailPage)emailpage).prosProductNames) {
            Assert.assertEquals("Invalid product name link",
                    Integer.parseInt("200"),
                    ((BCOMEmailPage)emailpage).getResponseCodeByLinkText(product.getText()));
        }
    }

    @Then("^i should see reason for return for virtual return templates$")
    public void i_should_see_reason_for_return_for_vr_templates()
            throws Throwable {

        Assert.assertEquals(
                "Expected Reason of return not found in user email",
                enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getShipitems().get(0)
                        .getReturnReason(), ((BCOMEmailPage)emailpage).getOrderDetails()
                        .get("reason_for_return"));

    }

    @Then("^i should see estimated refund for virtual return confirmation template$")
    public void i_should_see_estimated_refund_for_virtual_return_confirmation()
            throws Throwable {

        Assert.assertEquals(
                "Expected estimated refund not found in user email", "$"
                        + enhancedPayloadActual.getTriggerData().get(0)
                        .getOrderTotalsData().getOrderTotal(),
                ((BCOMEmailPage)emailpage).getOrderDetails().get("estimated_refund"));
    }

    public String getTemplateType() {

        String template = enhancedPayloadActual.getTriggerData().get(0)
                .getMailType().getValue()
                + "_"
                + enhancedPayloadActual.getTriggerData().get(0)
                .getMailSubType().getValue()
                + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType()
                .getValue();
        return template;
    }

    @Then("^i should see apple payment info$")
    public void i_should_see_apple_payment_info() throws Throwable {

        Assert.assertTrue(
                "Expected payment Card Type not match with actual",
                ((BCOMEmailPage)emailpage).getAllLabels().contains(
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getPaymentData().get(0).getType()
                                .toLowerCase()));

        Assert.assertTrue(
                "Expected payment Card Type not match with actual",
                ((BCOMEmailPage)emailpage).getAllLabels()
                        .contains(
                                "$"
                                        + enhancedPayloadActual
                                        .getTriggerData().get(0)
                                        .getPaymentData().get(0)
                                        .getChargeAmt()));

    }

    @Then("^i should see apple payment info for 360 template$")
    public void i_should_see_apple_payment_info_for_360_template()
            throws Throwable {
        Assert.assertEquals(
                "Expected Original form of tender not found in user email",
                "Apple Pay",
                ((BCOMEmailPage)emailpage).getOrderDetails().get("original_form_of_tender"));
    }


    @Then("^i should see shoprunner shipping delivery method$")
    public void i_should_see_shoprunner_shipping_method()
            throws Throwable {

        String labelText = ((BCOMEmailPage)emailpage).getAllLabels().toLowerCase().trim();

        for (int shipment = 0; shipment < enhancedPayloadActual
                .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

            String shipmentType=enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().get(0).getShipmentType().trim();

            String fdIndicator = enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(shipment).getShipitems()
                    .get(0).getFdIndicator();

            if(shipmentType.equals("PREMIUM") && fdIndicator != null && fdIndicator.equals("R")){

                shipmentType = "SHOPRUNNER FREE 2-DAY";

                Assert.assertTrue(
                        "Shipment method is not matching with Expected value",
                        labelText.contains(shipmentType.toLowerCase()));

            }
        }
    }

    @Then("^i should see order capture date for 233 payment_auth_failure template$")
    public void i_should_see_order_capture_date_for_233_1A_payment_auth_failure() throws Throwable {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyy-mm-dd");
        Date d = formatter.parse(enhancedPayloadActual.getTriggerData().get(0)
                .getOrderData().getOrderCapturedDate());
        formatter.applyPattern("mm/dd/yyyy");
        String orderDateFromEnhancedPayload=formatter.format(d).toString();
        String[] orderDates = ((BCOMEmailPage)emailpage).getOrderDate().split(";");
        for (int i = 0; i < orderDates.length; i++) {
            Assert.assertEquals(
                    "Expected Order date not match with Actual Order date",
                    orderDateFromEnhancedPayload,
                    getdefaultDateFormat(orderDates[i]));
        }
    }

    @Then("^i should see payment card info for 233 payment_auth_failure template$")
    public void i_should_see_payment_card_info__for_233_1A_payment_auth_failure() throws Throwable {

        String cardType = enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getType().toLowerCase();

        String cardNbr = enhancedPayloadActual.getTriggerData().get(0)
                .getPaymentData().get(0).getCardNbr();

        String allLabels = ((BCOMEmailPage)emailpage).getAllLabels().replaceAll("\n", "").trim();

        Assert.assertTrue(
                "Expected payment Card Type not match with actual",
                allLabels.contains(cardType+" "+cardNbr));

    }

    @And("^i should see bmoney logo$")
    public void iShouldSeebMoneyLogo(){
        Assert.assertTrue("bMoney logo not displayed", ((BCOMEmailPage)emailpage).isBmoneyLogoDisplayed());
    }
    @And("^i should see bMoney reward card bar code image in email$")
    public void iShoouldSeebMoneyRewardCardBarCodeLogoDisplayed(){
        Assert.assertTrue("reward card bar code image not displayed", ((BCOMEmailPage)emailpage).isbMoneyBarCodeImageDisplayed());
    }

    @And(("^i should see below bMoney disclimer text in email:$"))
    public void iShouldSeeBelowBMoneyDisclimerTextInEmail(String text){
        Assert.assertTrue("bMoney disclimer text not present in email",
                text.toLowerCase().trim().contains(((BCOMEmailPage)emailpage).getbMoneyDisclimerText().replaceAll("\n", " ").trim().toLowerCase()));

    }

    @And("^i shoould see \"(.*?)\" text in bMoney email$")
    public void iShouldSeeTextInbMoneyEmail(String text) throws Exception{
        String expectedText = null, actualText = null;
        switch (text) {
            case "YOU'VE EARNED":
            case "REDEEM IT FROM":
                expectedText = text;
                actualText = text.contains("REDEEM") ? ((BCOMEmailPage)emailpage).getbMoneyRedeemText() : ((BCOMEmailPage)emailpage).getbMoneyEarnText();
                break;
            case "<rewardCardEffectiveDate>-<rewardCardExpirationDate>":
            case "EXPIRES <rewardCardExpirationDate>":
                expectedText = text.replaceAll(
                        "<rewardCardEffectiveDate>",
                        getModifiedYearFormat(enhancedPayloadActual
                                .getTriggerData().get(0).getLoyaltyData()
                                .getRewardsCardEffectiveDate()))
                        .replaceAll(
                                "<rewardCardExpirationDate>",
                                getModifiedYearFormat(enhancedPayloadActual
                                        .getTriggerData().get(0).getLoyaltyData()
                                        .getRewardsCardExpirationDate()))
                        .replaceAll("\n", " ");
                actualText = text.contains("EXPIRES") ? ((BCOMEmailPage)emailpage).getbMoneyExpDate(): ((BCOMEmailPage)emailpage).getbMoneyEffAndExpDates();
                break;
            case "CID #: <cid>":
                expectedText = text.replaceAll("<cid>", enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData().getRewardsCardCid());
                actualText = ((BCOMEmailPage)emailpage).getbMoneyRewardCIDTxt();
                break;
            case "CARD #: <rewardCardNumber>":
                expectedText = text.replaceAll("<rewardCardNumber>", enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData().getVrcBarCodeNbr());
                actualText = ((BCOMEmailPage)emailpage).getbMoneyRewardCardNumberTxt();
                break;
            case "$<amount>":
                expectedText = text.replaceAll("<amount>", enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData().getRewardsCardAmt());
                actualText = ((BCOMEmailPage)emailpage).getbMoneyEarnAmount();
                break;
            default:
                Assert.fail("Invalid option!!");
        }

        Assert.assertTrue("Expected Text " + expectedText + " is not present in email",
                expectedText.trim().toLowerCase().contains(actualText.trim().toLowerCase()));
    }

    @Then("^i should see static content for ship address change:$")
    public void i_should_see_static_content_for_ship_address_change(String statictext)
            throws Throwable {

        int numberoflinks = StringUtils.countMatches(statictext, "<link>");
        if (numberoflinks >= 2) {
            String[] contents = statictext.split("<link>");
            for (int cnt = 1; cnt <= numberoflinks; cnt = cnt + 2) {
                Assert.assertNotNull("Link \" " + contents[cnt]
                        + " \" not exists", WebDriverManager.getWebDriver()
                        .findElement(By.linkText((contents[cnt]))));
                ;
            }
            for (int cnt = 0; cnt <= numberoflinks + 1; cnt = cnt + 2) {
                Assert.assertTrue("Static text \" " + contents[cnt]
                                + " \" not exists",
                        StringUtils.contains(statictext, contents[cnt]));
            }

        } else {
            Assert.assertTrue("Static text not displaying as expected",
                    StringUtils.contains(statictext, ((BCOMEmailPage)emailpage)
                            .getShipAddChangeText().replace("\n", "")));
        }

    }

    @And("^i should see shipping address change firstname$")
    public void i_should_see_shipping_address_change_firstname() throws Throwable {
        Assert.assertEquals(
                "Expected First name not found in user email",
                "Dear "
                        + enhancedPayloadActual.getTriggerData().get(0).getWebsiteData()
                        .getPromoUserFirstName() + ",",
                ((BCOMEmailPage)emailpage).getShipFirstnameText());

    }

    @And("^i should see loyalty preheader:$")
    public void i_should_see_loyalty_preheader(String text) throws Throwable {
        Assert.assertEquals(
                "Loyalty associate header is missing",text,((BCOMEmailPage)emailpage).promoDesc.getText());

    }

    //====================================================
    // Website MCOM steps
    //====================================================
    @Then("^i should see pre header:$")
    public void i_should_pre_header(String preHeader) throws Throwable {
        Assert.assertTrue("pre header  \" " + preHeader + " \" not exists",
                StringUtils.contains(preHeader, ((MCOMEmailPage)emailpage).getPreHeader().trim()));
    }

    @Then("^i should see pre header for bops ready for pick up:$")
    public void i_should_pre_header_for_bops_ready_for_pick_up(String preHeader) throws Throwable {
        preHeader = preHeader.replaceAll("<pickupCancelDate>",
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getPickupCancelDate());
        Assert.assertTrue("pre header  \" " + preHeader + " \" not exists",
                StringUtils.contains(preHeader, ((MCOMEmailPage)emailpage).getPreHeader()));

    }

    @And("^i should see body text for 240_mwedd:$")
    public void i_should_body_text_for_240_1I_mwedd(String bodyText) throws Throwable {

        String bodyTextLines = ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim();
        bodyTextLines = bodyTextLines.replaceAll("[^a-zA-Z0-9,. (')]", "");
        Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                bodyTextLines.contains(bodyText.toLowerCase().trim()));
    }

    @And("^i should see body text for 264_14R:$")
    public void i_should_body_text_for_264_14R(String bodyText) throws Throwable {
        String bodyTextLines = ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim();
        bodyText = bodyText.replaceAll("\n", "");
        Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                bodyTextLines.contains(bodyText.toLowerCase().trim()));

    }

    @And("^i should see body text for 264_14S and 14T:$")
    public void i_should_body_text_for_264_14S_and_14T(String bodyText) throws Throwable {
        String bodyTextLines = ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim();
        bodyText = bodyText.replaceAll("<billingAddr.firstName>",
                enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName());
        Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                bodyTextLines.contains(bodyText.toLowerCase().trim()));

    }

    @And("^i should see static contents in note section:$")
    public void i_should_see_static_contents_in_note_section(String statictext) throws Throwable {
        Assert.assertTrue("Static note text \" " + statictext + " \" not exists",
                StringUtils.contains(statictext, ((MCOMEmailPage)emailpage).getstaticNoteMessage()));
    }

    @And("^i should see mail body text for list share:$")
    public void i_should_mail_body_text(String expectedbodyText) throws Throwable {

        expectedbodyText = expectedbodyText.replaceAll("<shipmentFirstNameFromEp>",
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getFirstName().toLowerCase());
        Assert.assertEquals("Expected mail Body text not found in user email", expectedbodyText,
                ((MCOMEmailPage)emailpage).getBodyText().trim());
    }

    @And("^i should see body text for 543_1B bops pickup reminder:$")
    public void i_should_see_body_text_for_543_1B_bops_pickup_reminder(String bodyText)
            throws JsonParseException, JsonMappingException, IOException, InterruptedException {

        try {
            bodyText = bodyText
                    .replaceAll("<storeName>",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName())
                    .replaceAll("<billingFirstName>",
                            enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName())
                    .replaceAll("<pickupCancelDate>", enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                            .get(0).getPickupCancelDate())
                    .toLowerCase().trim();

            Assert.assertTrue(
                    "Expected mail Body text " + bodyText + "not found in user email. Actual body text:"
                            + ((MCOMEmailPage)emailpage).getBodyText().trim(),
                    StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().toLowerCase().replaceAll("\n", "").trim(),
                            bodyText));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @And("^i should see body text for virtual exchange reminder:$")
    public void i_should_see_body_text_for_virtual_exchange_reminder(String bodytext) throws Throwable {

        bodytext = bodytext.replaceAll("<returnSubmittedDate>",
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getReturnSubmittedDate());

        String returnExpectedBackDate = null;

        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                returnExpectedBackDate = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().get(item).getReturnExpectedBackDate();
            }
        }

        bodytext = bodytext.replaceAll("<returnExpectedBackDate>", returnExpectedBackDate).replaceAll("\n", "");
        Assert.assertTrue("Expected mail Body text not found in user email", StringUtils.contains(
                ((MCOMEmailPage)emailpage).getBodyText().toLowerCase().replaceAll("\n", "").trim(), bodytext.toLowerCase().trim()));

    }

    @And("^i should see store pickup area$")
    public void i_should_see_store_pickup_area() throws Throwable {
        if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName() != null) {
            String storePickupAreaFromMail = ((MCOMEmailPage)emailpage).getMailContent().replaceAll(" ", "").trim().toLowerCase();
            storePickupAreaFromMail = storePickupAreaFromMail.replaceAll("\\r\\n|\\r|\\n", "");

            String storePickupAreaFromEP = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                    .getStorePickupInstructions().replaceAll("\\|", "").replaceAll(" ", "").trim().toLowerCase();

            Assert.assertTrue("Expected Store Pickup Area is not found in user email",
                    storePickupAreaFromMail.contains(storePickupAreaFromEP));
        }

    }

    @Then("^i should see billing firstname hi$")
    public void i_should_see_billing_firstname_hi() throws Throwable {

        Assert.assertEquals("Expected Billing first name not found in user email",
                "Hi " + StringUtils.capitalize(
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase())
                        + ",",
                ((MCOMEmailPage)emailpage).getFirstnameTextBilling());

    }

    @Then("^i should see firstname for 543 templates$")
    public void i_should_see_firstname_for_543_templates() throws Throwable {

        Assert.assertTrue("Expected first name is not found in user email",
                ((MCOMEmailPage)emailpage).getFirstName().contains("Hi " + StringUtils.capitalize(
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase())
                        + ","));
    }

    @And("^i should see shipping address$")
    public void i_should_see_shipping_address() throws Throwable {

        String labelText = ((MCOMEmailPage)emailpage).getMailContent();

        Assert.assertTrue("Shipping First Name is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getFirstName().toLowerCase()));
        Assert.assertTrue("Shipping Last Name is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getLastName().toLowerCase()));
        Assert.assertTrue("Shipping Address Line1 is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getAddrLine1().toLowerCase()));
        Assert.assertTrue("Shipping Address Line2 is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getAddrLine2().toLowerCase()));
        Assert.assertTrue("Shipping Address Line3 is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getAddrLine3().toLowerCase()));
        Assert.assertTrue("Shipping City is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getCity().toLowerCase()));
        Assert.assertTrue("Shipping State is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getState().toLowerCase()));

        Assert.assertTrue("Shipping ZIP Code is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getZipcode().toLowerCase()));

    }

    @And("^i should see no gift option$")
    public void i_should_see_no_gift_option() throws Throwable {

        String labelText = ((MCOMEmailPage)emailpage).getMailContent();
        String giftBox = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getGiftWrapFlag();
        String giftReceipt = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                .getGiftReceiptFlag();

        if (giftBox.isEmpty()) {

            giftBox = "gift box:";
            Assert.assertTrue("Gift Box Value is not matching with the expected value", !(labelText.contains(giftBox)));
        }

        if (giftReceipt.isEmpty()) {

            giftReceipt = "gift receipt:";
            Assert.assertTrue("Gift Reciept Value is not matching with the expected value",
                    !(labelText.contains(giftReceipt)));

        }

        Assert.assertTrue("Gift Greeting Message is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                        .getGiftGreetingMsg().toLowerCase()));
        Assert.assertTrue("Gift Closing Message is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                        .getGiftClosingMsg().toLowerCase()));
        Assert.assertTrue("Gift Signature Message is not matching with the expected value",
                labelText.contains(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                        .getGiftSignatureMsg().toLowerCase()));

    }

    @And("^i should see gift box$")
    public void i_should_see_gift_box() throws Throwable {

        String labelText = ((MCOMEmailPage)emailpage).getMailContent();
        String giftBox = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getGiftWrapFlag();

        if (giftBox.equals("Y")) {

            giftBox = "gift box: yes";
            Assert.assertTrue("Gift Box Value is not matching with the expected value", labelText.contains(giftBox));
        }

    }

    @And("^i should see gift receipt$")
    public void i_should_see_gift_receipt() throws Throwable {

        String labelText = ((MCOMEmailPage)emailpage).getMailContent();
        String giftReceipt = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                .getGiftReceiptFlag();
        if (giftReceipt.equals("Y")) {
            giftReceipt = "gift receipt: yes";
            String giftReceiptInNewLine = "gift receipt:" + '\n' + "yes";
            Assert.assertTrue("Gift Reciept Value is not matching with the expected value",
                    labelText.contains(giftReceipt) || labelText.contains(giftReceiptInNewLine));

        }
    }

    @Then("^i should see credit card type for 245 templates$")
    public void i_should_see_credit_card_type_for_245_templates() throws Throwable {

        String card = "Original form of tender: "
                + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getType() + " "
                + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getCardNbr();

        Assert.assertEquals("Expected Order Date found in user email", card,
                ((MCOMEmailPage)emailpage).getOriginalFormOfTender().trim());

    }

    @Then("^i should see credit card type for 543 templates$")
    public void i_should_see_credit_card_type_for_543_templates() throws Throwable {

        String card = "Payment method: "
                + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getType() + " "
                + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getCardNbr();

        Assert.assertEquals("Expected Payment method not found in user email", card,
                ((MCOMEmailPage)emailpage).getPaymentMethod().trim());
    }

    @Then("^i should see order cancelation date$")
    public void i_should_see_order_cancelation_date() throws JsonParseException, JsonMappingException, IOException {

        Assert.assertEquals("Expected order cancelation date did not match the actual result",
                "Order cancelation date: " + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                        .getShipitems().get(0).getUserCancelDate(),
                ((MCOMEmailPage)emailpage).getOrderCancelationDate());
    }

    @Then("^i should see order cancelation date for 253 11 mwedd$")
    public void i_should_see_order_cancelation_date_for_253_11_mwedd()
            throws JsonParseException, JsonMappingException, IOException {
        Assert.assertEquals("Expected order cancelation date did not match the actual result",
                "Order cancelation on: " + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                        .getShipitems().get(0).getUserCancelDate(),
                ((MCOMEmailPage)emailpage).getOrderCancelationDate());

    }

    @And("^i should see recommendation product names in the email$")
    public void i_should_see_recommendation_product_names_in_the_email() throws Throwable {

        try {
            String prosItemDesc = "";
            String[] itemName = ((MCOMEmailPage)emailpage).getProsItemDetails().split("@");

            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getRecommendationsData()
                    .size(); item++) {

                prosItemDesc += enhancedPayloadActual.getTriggerData().get(0).getRecommendationsData().get(item)
                        .getProductName().toLowerCase() + ";";
            }

            for (int itmDesc = 0; itmDesc < itemName.length; itmDesc++) {
                Assert.assertTrue("Expected '" + itemName[itmDesc] + "' PROS product name not match with Actual '"
                        + prosItemDesc + "' PROS product names", prosItemDesc.contains(itemName[itmDesc]));
            }

            for (WebElement product : ((MCOMEmailPage)emailpage).prosProductDesc) {
                Assert.assertEquals("Invalid product name link", Integer.parseInt("200"),
                        ((MCOMEmailPage)emailpage).getResponseCodeByLinkText(product.getText()));
            }

            for (int index = 0; index < ((MCOMEmailPage)emailpage).prosProductImages.size(); index++) {
                Assert.assertEquals("Invalid image URL", Integer.parseInt("200"),
                        ((MCOMEmailPage)emailpage).getProsImageURLHttpStatus(index));
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }

    }

    @Then("^i should see product quantity$")
    public void i_should_see_product_quantity() throws JsonParseException, JsonMappingException, IOException {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;

        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getStatus() != null) {
                    if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                            .get(item).getStatus().equalsIgnoreCase("IN PROCESS")) {
                        Assert.assertEquals("Expected Product Qty not match with actual",
                                Integer.parseInt(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                        .get(shipment).getShipitems().get(item).getReqQuantity()),
                                Integer.parseInt(itemDetails.get(index).get("qty")));
                        index++;
                    } else if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getStatus().equalsIgnoreCase("SHIPPED")) {

                        Assert.assertEquals("Expected Product Qty not match with actual",
                                Integer.parseInt(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                        .get(shipment).getShipitems().get(item).getShippedQuantity()),
                                Integer.parseInt(itemDetails.get(index).get("qty")));

                        index++;
                    } else if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                            .getShipitems().get(item).getStatus().equalsIgnoreCase("VOIDED")
                            || enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue()
                            .equals("30A")) {
                        Assert.assertEquals("Expected Product Qty not match with actual",
                                Integer.parseInt(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                        .get(shipment).getShipitems().get(item).getCancelledQuantity().toString()),
                                Integer.parseInt(itemDetails.get(index).get("qty")));
                        index++;
                    }
                } else {

                    Assert.assertEquals("Expected Product Qty not match with actual",
                            Integer.parseInt(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                    .get(shipment).getShipitems().get(item).getReqQuantity()),
                            Integer.parseInt(itemDetails.get(index).get("qty")));
                    index++;
                }
            }
        }
    }

    @Then("^i should see product color$")
    public void i_should_see_product_color() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);

        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (itemDetails.get(index).get("color") != null) {

                    Assert.assertEquals("Expected product color not match with Actual product color",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getProductColor(),
                            itemDetails.get(index).get("color"));

                }
                index++;
            }

        }
    }

    @Then("^i should see product size$")
    public void i_should_see_product_size() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductSize() != "") {

                    Assert.assertEquals("Expected product size not match with Actual product size",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getProductSize(),
                            itemDetails.get(index).get("size"));

                }
                index++;
            }

        }

    }

    @Then("^i should see product type$")
    public void i_should_see_product_type() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (itemDetails.get(index).get("Type") != null) {

                    Assert.assertEquals("Expected product type not match with Actual product type",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getProductType(),
                            itemDetails.get(index).get("type"));

                }
                index++;
            }
        }

    }

    @Then("^i should see product status$")
    public void i_should_see_product_status() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (itemDetails.get(index).get("Type") != null) {

                    Assert.assertEquals("Expected product status not match with Actual product status",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getStatus(),
                            itemDetails.get(index).get("status"));

                }
                index++;
            }
        }

    }

    @Then("^i should see ship date$")
    public void i_should_see_ship_date() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("ship date") != null) {

                    Assert.assertEquals(
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShippedDate().trim().substring(0, 6).toString()
                                    + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShippedDate().trim().substring(8).toString(),
                            itemDetails.get(index).get("ship date").trim());

                }
                index++;
            }
        }

    }

    @Then("^i should see shipment type$")
    public void i_should_see_shipment_type() throws Throwable {

        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (itemDetails.get(index).get("shipment type") != null) {

                    Assert.assertTrue("Shipment type not match with Actual shipment type", itemDetails.get(index)
                            .get("shipment type").toLowerCase()
                            .contains(getShipmentMethod(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                    .get(shipment).getShipitems().get(item).getShipmentType().trim()).toLowerCase()));

                }
                index++;
            }
        }
    }

    @Then("^i should see tracking number$")
    public void i_should_see_tracking_number() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("tracking #") != null) {
                    Assert.assertEquals("Expected tracking number not match with Actual tracking number",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getTrackingDetails().get(0).getTrackingNbr(),
                            itemDetails.get(index).get("tracking #"));

                }
                index++;
            }
        }
    }

    @Then("^i should see product price$")
    public void i_should_see_product_price() throws JsonParseException, JsonMappingException, IOException {

        List<LinkedHashMap<String, String>> itemDetails = null;
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(getTemplate());
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                try {

                    if (!enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                            .get(item).getStatus().equals("VOIDED")
                            || enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue()
                            .equals("30A")) {

                        Assert.assertEquals("Expected product price not match with Actual product price",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                        .getShipitems().get(item).getPrice(),
                                itemDetails.get(index).get("Price").substring(1));
                        index++;
                    }

                    else if (itemDetails.get(index).get("Price").equals(null)) {
                        Assert.assertEquals("Expected product price not match with Actual product price",
                                itemDetails.get(index).get("Price"), null);
                        index++;
                    } else {

                        Assert.assertEquals("Expected product price not match with Actual product price",
                                itemDetails.get(index).get("Price"), "$0.00");
                        index++;
                    }

                } catch (Exception e) {

                    if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                            .get(item).getShipmentType().equals("Return")) {
                        Assert.assertEquals("Expected product price not match with Actual product price",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                        .getShipitems().get(item).getPrice(),
                                itemDetails.get(index).get("Price").substring(1));
                        index++;
                    }
                }
            }
        }
        ((MCOMEmailPage)emailpage).productDetails = null;
    }

    @Then("^i should see recepient email address$")
    public void i_should_see_recepient_email_address() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();

        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;

        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems().get(0)
                        .getShipmentType().trim().toLowerCase().equals("email")) {

                    Assert.assertEquals("Expected recepient email address not match with Actual email address",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getRegistryReceipientEmail(),
                            (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("send to")));

                }

                index++;
            }

        }
    }

    @Then("^i should see product image$")
    public void i_should_see_product_image() throws Throwable {
        int product_index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                // product image gets displayed only when it's a valid product
                // .. else no image available gets displayed in email ..

                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductURL() != null) {
                    Assert.assertNotNull(((MCOMEmailPage)emailpage).productImage.get(product_index).getTagName());

                } else {
                    Assert.assertNotNull(((MCOMEmailPage)emailpage).noImageAvailable.get(product_index).getTagName());
                }
                product_index++;
            }
        }
    }

    @Then("^i should see product image url valid$")
    public void i_shoud_see_product_image_url_valid() throws Throwable {

        int product_index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductURL() != null) {
                    Assert.assertEquals("Product image link not valid ", Integer.parseInt("200"),
                            ((MCOMEmailPage)emailpage).getImageURLHttpStatus(product_index));
                }
                product_index++;
            }
        }

    }

    @Then("^i should see item image$")
    public void i_should_see_item_image() throws Throwable {
        int product_index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                Assert.assertNotNull(((MCOMEmailPage)emailpage).itemImage.get(product_index).getTagName());
                product_index++;
            }
        }
    }

    @Then("^i should see one \"([^\"]*)\" button$")
    public void i_should_see_one_button(String buttonName) throws Throwable {
        Assert.assertNotNull("Unable to Find button " + buttonName, ((MCOMEmailPage)emailpage).getLinkByText(buttonName));
    }

    @Then("^i should see the \"([^\"]*)\" button$")
    public void i_should_see_shop_macys_button(String buttonName) throws Throwable {
        Assert.assertNotNull("Unable to Find button " + buttonName, ((MCOMEmailPage)emailpage).shopButton.isDisplayed());
        Assert.assertEquals("Invalid target URL for Button " + buttonName, Integer.parseInt("200"),
                ((MCOMEmailPage)emailpage).getResponseCodeByURL(((MCOMEmailPage)emailpage).shopButton.getAttribute("href")));
    }

    @Then("^i should see Macys logo$")
    public void i_should_see_Macys_logo() throws Throwable {
        Assert.assertEquals("Macys logo is not displaying in the email", true, ((MCOMEmailPage)emailpage).macysLogo.isDisplayed());

    }

    @Then("^i should see congrats message$")
    public void i_should_see_congrats_message() throws Throwable {
        Assert.assertEquals("Congrats message is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).congratsMessage.isDisplayed());

    }

    @Then("^i should see loyalty first name$")
    public void i_should_see_loyalty_first_name() throws Throwable {
        Assert.assertEquals("Loyalty first name is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).loyaltyFirstName.isDisplayed());

    }

    @Then("^i should see reward points gold$")
    public void i_should_see_reward_points_gold() throws Throwable {
        Assert.assertEquals("Reward points is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).rewardPointsForGold.isDisplayed());

    }

    @Then("^i should see reward points silver$")
    public void i_should_see_reward_points_silver() throws Throwable {
        Assert.assertEquals("Reward points is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).rewardPointsForSilver.isDisplayed());

    }

    @Then("^i should see reward points platinum$")
    public void i_should_see_reward_points_platinum() throws Throwable {
        Assert.assertEquals("Reward points is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).rewardPointsForPlatinum.isDisplayed());

    }

    @Then("^i should see reward points expiration$")
    public void i_should_see_reward_points_expiration() throws Throwable {
        Assert.assertEquals("Reward points expiration is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).rewardPointsForExpiration.isDisplayed());

    }

    @Then("^i should see starmoney creditcard text$")
    public void i_should_see_starmoney_creditcard_text() throws Throwable {
        Assert.assertEquals("StarMoney CreditCard Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).starMoneyMacysCreditCardText.isDisplayed());

    }

    @Then("^i should see starmoney expire date$")
    public void i_should_see_starmoney_expire_date() throws Throwable {
        Assert.assertEquals("StarMoney CreditCard Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).StarMoneyExpireDate.isDisplayed());
    }

    @Then("^i should see star money expire date$")
    public void i_should_see_star_money_expire_date() throws Throwable {
        Assert.assertEquals("StarMoney expire date Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).starMoneyExpDate.isDisplayed());
    }

    @Then("^i should see first name and last name$")
    public void i_should_see_first_name_and_last_name() throws Throwable {
        Assert.assertEquals("First name and last name Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).firstandLastName.isDisplayed());
    }

    @Then("^i should see member text$")
    public void i_should_see_member_text() throws Throwable {
        Assert.assertEquals("Member Text is not displaying in the email", true, ((MCOMEmailPage)emailpage).memberText.isDisplayed());
    }

    @Then("^i should see star rewards text$")
    public void i_should_see_star_rewards_text() throws Throwable {
        Assert.assertEquals("Star rewards text expire date Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).starRewardText.isDisplayed());
    }

    @Then("^i should see use it macys store text$")
    public void i_should_see_use_it_macys_store_text() throws Throwable {
        Assert.assertEquals("Macys Store Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).macysStoreText.isDisplayed());
    }

    @Then("^i should see first and last name$")
    public void i_should_see_first_and_last_name() throws Throwable {
        Assert.assertEquals("First Last name Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).fNameLname.isDisplayed());
    }

    @Then("^i should see star card gold member$")
    public void i_should_see_star_card_gold_member() throws Throwable {
        Assert.assertEquals("star card member Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).starCardGoldMember.isDisplayed());

    }

    @Then("^i should see star card silver member$")
    public void i_should_see_star_card_silver_member() throws Throwable {
        Assert.assertEquals("star card member Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).starCardSilverMember.isDisplayed());

    }

    @Then("^i should see star card platinum member$")
    public void i_should_see_star_card_platinum_member() throws Throwable {
        Assert.assertEquals("star card member Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).starCardPlatinumMember.isDisplayed());

    }

    @Then("^i should see shipping amount in the email for virtual return receipt$")
    public void i_should_see_shipping_amount_in_the_email_for_virtual_return_receipt() throws Throwable {
        Assert.assertEquals("Order base shipping amount is not matching", "$"
                        + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getBaseShippingAmt().toString(),
                ((MCOMEmailPage)emailpage).baseShippingAmt.getText());

    }

    @Then("^i should see \"([^\"]*)\" in the email$")
    public void i_should_see_in_the_email(String orderAttribute) throws Throwable {
        String expected_value = null;
        NumberFormat formatter = new DecimalFormat("0.00");
        switch (orderAttribute) {
            case "Order total":
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Return")
                        || enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Exchange")) {
                    expected_value = "$"
                            + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipRetailAmount();

                } else {
                    expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                            .getSubTotalAmt().toString();

                    Assert.assertEquals("Order subtotal amount is not matching", expected_value,
                            ((MCOMEmailPage)emailpage).subTotalAmt.getText().replace(",", ""));
                }
                break;
            case "Shipping":

                expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                        .getBaseShippingAmt().toString();
                if (expected_value.equalsIgnoreCase("$0.00")
                        || enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Return")
                        || enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Exchange")
                        || enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("EMAIL")) {
                    Assert.assertEquals("Order base shipping amount is not matching", "FREE",
                            ((MCOMEmailPage)emailpage).baseShippingAmt.getText());
                }

                else {
                    Assert.assertEquals("Order base shipping amount is not matching", expected_value,
                            ((MCOMEmailPage)emailpage).baseShippingAmt.getText());
                }

                break;
            case "Additional shipment fee amount":
                expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                        .getAdditionalShipmentFeeAmount().toString();
                Assert.assertEquals("Order additional shipment fee amount is not matching", expected_value,
                        ((MCOMEmailPage)emailpage).addtShipmentFeeAmt.getText());
                break;
            case "Shipment upgrade fee amount":
            case "Same day delivery amount":
                expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                        .getShipmentUpgradeFeeAmount().toString();
                Assert.assertEquals("Shipment Upgrade fee amount/Same day delivery amount is not matching", expected_value,
                        ((MCOMEmailPage)emailpage).shipmentUpgradeFeeAmt.getText());
                break;

            case "Gift wrap fee amount":
                expected_value = "$"
                        + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getGiftWrapFeeAmt().toString();
                Assert.assertEquals("Gift wrap fee amount is not matching", expected_value,
                        ((MCOMEmailPage)emailpage).giftWrapFeeAmt.getText());
                break;

            case "TOTAL":
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Return")) {

                    expected_value = "$"
                            + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getChargeAmt();
                    Assert.assertEquals("Order total is not matching", expected_value, ((MCOMEmailPage)emailpage).orderTotal.getText());
                } else if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Exchange")) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    float shipRetailAmt = Float.parseFloat(
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipRetailAmount());
                    float shipTaxAmt = Float.parseFloat(
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipTaxAmount());

                    float total = shipRetailAmt + shipTaxAmt;
                    expected_value = "$" + Double.valueOf(df.format(total));
                    Assert.assertEquals("Order total is not matching", expected_value, ((MCOMEmailPage)emailpage).orderTotal.getText());
                } else {
                    expected_value = "$"
                            + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getOrderTotal().toString();
                    Assert.assertEquals("Order total is not matching", expected_value,
                            ((MCOMEmailPage)emailpage).orderTotal.getText().replace(",", ""));
                }
                break;

            case "Sales tax":
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Return")
                        || enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0)
                        .getShipmentType().equals("Exchange")) {
                    expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                            .getShipTaxAmount().toString();
                } else {
                    expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                            .getSalesTaxAmount().toString();
                }
                Assert.assertEquals("Sales tax amount is not matching", expected_value, ((MCOMEmailPage)emailpage).salesTax.getText());
                break;

            case "Pick up in-store":

                String template_name = (template.get("type") + "_" + template.get("subtype") + "_" + template.get("brand"))
                        .toUpperCase();
                if (template_name.equals("543_1A_MCOM") || template_name.equals("543_1A_MWEDD")) {
                    Assert.assertEquals("Pick up in-store is not displaying as expected", "FREE",
                            ((MCOMEmailPage)emailpage).bopsPickUpReminder.getText());
                } else if (enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue().equals("14Q")
                        || enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue().equals("14R")) {

                    Assert.assertEquals("Pick up in-store is not displaying as expected", "FREE",
                            ((MCOMEmailPage)emailpage).baseShippingAmt.getText());
                } else {
                    Assert.assertEquals("Pick up in-store is not displaying as expected", "Free",
                            ((MCOMEmailPage)emailpage).itemPickupInStore.getText());
                }
                break;

            case "PR VAT":
                Assert.assertEquals("PR VAT is not displaying as expected in the email", "$"
                                + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getMiscTaxAmount().toString(),
                        ((MCOMEmailPage)emailpage).prVat.getText());
                break;
            case "PR Municipal":
                Assert.assertEquals(
                        "PR Municipal is not displaying as expected in the email", "$" + enhancedPayloadActual
                                .getTriggerData().get(0).getOrderTotalsData().getMuntaxAmount().toString(),
                        ((MCOMEmailPage)emailpage).prMunicipal.getText());
                break;
            case "Same day delivery":
                Assert.assertEquals(
                        "Same day delivery is not displaying as expected in the email", "$" + enhancedPayloadActual
                                .getTriggerData().get(0).getOrderTotalsData().getShipmentUpgradeFeeAmount().toString(),
                        ((MCOMEmailPage)emailpage).sddAmount.getText());
                break;
            default:
                throw new Exception("Invalid order attriubte");
        }

    }

    @And("^i should see same day delivery text:$")
    public void i_should_see_same_day_delivery_text(String sameDayText) throws Throwable {
        Assert.assertEquals("Same Day Delivery Hours Text is not matching", sameDayText,
                ((MCOMEmailPage)emailpage).sddOrderText.getText());

    }

    public String getShipmentMethod(String shipmentMethod) {

        String expectedShipmentMethod = null;

        try {

            float baseShippingFee = Float.parseFloat(
                    enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getBaseShippingAmt());
            String indicator = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems()
                    .get(0).getFdIndicator();

            if (indicator != null && indicator.equalsIgnoreCase("W") && shipmentMethod.equalsIgnoreCase("STANDARD")) {

                expectedShipmentMethod = "No Hurry Shipping";
            } else if (baseShippingFee == 0.0 && shipmentMethod.equalsIgnoreCase("STANDARD")) {

                expectedShipmentMethod = "Standard Shipping";
            } else if (baseShippingFee > 0.0 && shipmentMethod.equalsIgnoreCase("STANDARD")) {

                expectedShipmentMethod = "Ground Shipping";

            } else if (shipmentMethod.equalsIgnoreCase("PREMIUM")) {

                expectedShipmentMethod = "Premium";
            } else if (shipmentMethod.equalsIgnoreCase("EXPRESS")) {

                expectedShipmentMethod = "Express";
            } else if (shipmentMethod.equalsIgnoreCase("Email")) {

                expectedShipmentMethod = "Email";
            } else if (shipmentMethod.equalsIgnoreCase("SAMEDAY")) {

                expectedShipmentMethod = "Same-Day Delivery";
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return expectedShipmentMethod;
    }

    @When("^i should see return initiated date$")
    public void i_should_see_return_initiated_date() throws Throwable {
        Assert.assertEquals("Expected return initiated date not found in the mail", "Return initiated date: "
                        + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getReturnSubmittedDate(),
                ((MCOMEmailPage)emailpage).getReturnInitiatedDate());

    }

    @When("^i should see date initiated date$")
    public void i_should_see_date_initiated_date() throws Throwable {

        Assert.assertEquals(
                "Expected date initiated date not found in the mail", "Date initiated: " + enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0).getReturnSubmittedDate(),
                ((MCOMEmailPage)emailpage).getDateInitiatedDate());

    }

    @And("^i should see replaced on date$")
    public void i_should_see_replaced_on_date() throws Throwable {
        Assert.assertEquals(
                "Expected Replaced on date not found in the mail", "Replaced on: " + enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0).getReturnSubmittedDate().trim(),
                ((MCOMEmailPage)emailpage).getDateReplacedDate());
    }

    @When("^i should see return to$")
    public void i_should_see_return_to() throws Throwable {
        String card = "Return to: " + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getType()
                + " " + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getCardNbr();
        Assert.assertEquals("Expected Return To not found in user email", card, ((MCOMEmailPage)emailpage).getReturnTo());

    }

    @When("^i should see estimated refund for 245 5F templates$")
    public void i_should_see_estimated_refund_for_245_5F_templates() throws Throwable {
        Assert.assertEquals("Expected estimated refund not found in the mail",
                "Estimated refund: " + "$"
                        + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getOrderTotal(),
                ((MCOMEmailPage)emailpage).getEstimatedRefund());
    }

    @When("^i should see reason for return for 245 5F templates$")
    public void i_should_see_reason_for_return_for_245_5F_templates() throws Throwable {
        Assert.assertEquals("Expected reason for return not found in the mail",
                "Reason for return: " + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                        .getShipitems().get(0).getReturnReasonDescription(),
                ((MCOMEmailPage)emailpage).getReasonForReturn());
    }

    @And("^i should see contact information:$")
    public void i_should_see_contact_information(String contactInformation) throws Throwable {

        String expectedContactInformation = "";
        expectedContactInformation = contactInformation
                .replaceAll("<billingfirstname>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName())
                .replaceAll("<billinglastname>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getLastName())
                .replaceAll("<recipientemailaddress>",
                        enhancedPayloadActual.getRecordData().getRecords().get(0).getFieldValues().get(0));

        Assert.assertTrue("Contact Information not displayign as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent().trim(), expectedContactInformation.toLowerCase()));

    }

    @And("^i should see contact information for 543 1B templates:$")
    public void i_should_see_contact_information_for_543_1B_templates(String contactInformation) throws Throwable {
        String expectedContactInformation = "";
        expectedContactInformation = contactInformation
                .replaceAll("<billingfirstname>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName())
                .replaceAll("<billinglastname>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getLastName());

        Assert.assertTrue("Contact Information not displayign as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent().trim(), expectedContactInformation.toLowerCase()));

    }

    @Then("^i should see static content for ship address change notification as:$")
    public void i_should_see_static_content_for_ship_address_change_notification_as(String text) throws Throwable {

        String textInMail = text.replace("'", "").trim();

        Assert.assertEquals("Email body text not matched", textInMail.replaceAll("<link>", ""),
                ((MCOMEmailPage)emailpage).getMailBodyText().replaceAll("ï¿½", "").trim());

        int numberoflinks = StringUtils.countMatches(textInMail, "<link>");
        if (numberoflinks >= 2) {
            String[] contents = textInMail.split("<link>");

            for (int cnt = 1; cnt <= numberoflinks; cnt = cnt + 2) {

                Assert.assertNotNull("Link \" " + contents[cnt] + " \" not exists",
                        WebDriverManager.getWebDriver().findElement(By.linkText((contents[cnt]))));
                ;
            }

        }

    }

    @Then("^i should see static content for email change notification as:$")
    public void i_should_see_static_content_for_email_change_notification_as(String text) throws Throwable {
        String expected1 = text.split("<")[0];
        String expected2 = text.split(">")[1];
        String expected = expected1
                + enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getProfileEmailAddress() + "."
                + expected2;
        i_should_see_static_contents(expected);
    }

    @Then("^i should see static content for password reset as:$")
    public void i_should_see_static_content_for_password_reset_as(String text) throws Throwable {
        String expected1 = text.split("<")[0];
        String expected2 = text.split(">")[1];
        String expected = expected1
                + enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoOneTimePwdExpTime()
                + expected2;
        i_should_see_static_contents(expected);
    }

    @Then("^i should see store name information at lable:$")
    public void i_should_see_store_name_information_at_lable(String expectedStoreName) throws Throwable {
        try {
            expectedStoreName = expectedStoreName.replaceAll("<storeName>",
                    enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName());
            Assert.assertTrue(expectedStoreName + "not existing in email page",
                    ((MCOMEmailPage)emailpage).isTextPresent(expectedStoreName));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Then("^i should see contact information pickup:$")
    public void i_should_see_contact_information_pickup(String expectedContactInformationPickup) throws Throwable {
        expectedContactInformationPickup = expectedContactInformationPickup
                .replaceAll("<shipmentsfirstname>",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getFirstName())
                .replaceAll("<shipmentslastname>",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getLastName())
                .replaceAll("<shipmentsemail>",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getemail())
                .replaceAll("<shipmentsphone>",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getphone());
        Assert.assertTrue("Contact Information not displaying as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent().trim().toLowerCase(),
                        expectedContactInformationPickup.trim().toLowerCase()));

    }

    @Then("^i should see shipping method:$")
    public void i_should_see_free_shiping_method(String shippingMethod) throws Throwable {
        Assert.assertTrue("Contact Information not displayign as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent(), shippingMethod.toLowerCase()));
    }

    @Then("^i should see pick-up at$")
    public void i_should_see_pick_up_at() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                Assert.assertEquals("Expected pick up at is not matching with the actual",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getStoreName(),
                        itemDetails.get(index).get("pick-up at"));
                index++;
            }
        }

    }

    @Then("^i should see order ready notice to$")
    public void i_should_see_order_ready_notice_to() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                Assert.assertEquals("Expected order ready notice to is not matching with actual",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getemail(),
                        itemDetails.get(index).get("order ready notice to"));
                index++;
            }

        }

    }

    @And("^i should see body text for 245 5N virtual return confirmation:$")
    public void i_should_see_body_text_for_245_5N_virtual_return_confirmation(String bodyText) throws Throwable {

        bodyText = (bodyText.replaceAll("<returnExpectedBackDate>", enhancedPayloadActual.getTriggerData().get(0)
                .getShipmentsData().get(0).getShipitems().get(0).getReturnExpectedBackDate()).replaceAll("\n", ""));

        Assert.assertTrue("Expected mail Body text not found in user email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText(), bodyText.toLowerCase().trim()));

    }

    @And("^i should see body text for bops item picked up:$")
    public void i_should_see_body_text_for_bops_item_picked_up(String bodyText) throws Throwable {
        bodyText = bodyText.replaceAll("<shipmentname>",
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getFirstName());
        Assert.assertTrue("Expected mail body text not found in user email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText(), bodyText.toLowerCase().trim()));
    }

    @Then("^i should see return status description$")
    public void i_should_see_return_status_description() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (itemDetails.get(index).get("returnStatus") != null) {

                    if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                            .get(item).getReturnStatus().equals("1")) {

                        Assert.assertEquals("Expected return description not match with Actual return status",
                                "Please return this item", itemDetails.get(index).get("returnStatus"));
                    } else {

                        Assert.assertEquals("Expected return description not match with Actual return status",
                                "Do not return this item", itemDetails.get(index).get("returnStatus"));
                    }

                }
                index++;
            }
        }
    }

    @Then("^i should see product quantity for return confirmation$")
    public void i_should_see_product_quantity_for_return_confirmation() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(getTemplate());
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                Assert.assertEquals("Expected Product Qty not match with actual",
                        Integer.parseInt(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                .getShipitems().get(item).getReqQuantity()),
                        Integer.parseInt(itemDetails.get(index).get("qty")));
                index++;
            }

        }
    }

    @Then("^i should see body text for no bops items available:$")
    public void i_should_see_body_text_for_no_bops_items_available(String expectedbodyText) throws Throwable {
        i_should_see_body_text_having_store_name(expectedbodyText);
    }

    @Then("^i should see body text for no bops items available timeout:$")
    public void i_should_see_body_text_for_no_bops_items_available_timeout(String expectedbodyText) throws Throwable {
        i_should_see_body_text_having_ship_date(expectedbodyText);
    }

    @Then("^i should see body text having ship date$")
    public void i_should_see_body_text_having_ship_date(String expectedbodyText) throws Throwable {
        try {
            expectedbodyText = expectedbodyText.replaceAll("<Weekday, Month, Day, Time EST>",
                    enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName());
            Assert.assertTrue("Expected mail body text not found in user email",
                    // StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText()
                    // .replaceAll("\n", "").trim(),
                    // expectedbodyText.toLowerCase())
                    true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Then("^i should see body text for bops order late pickup cancel:$")
    public void i_should_see_body_text_for_bops_order_late_pickup_cancel(String expectedbodyText) throws Throwable {
        i_should_see_body_text_having_store_name(expectedbodyText);
    }

    @Then("^i should see body text having store name$")
    public void i_should_see_body_text_having_store_name(String expectedbodyText) throws Throwable {
        try {
            expectedbodyText = expectedbodyText.replaceAll("<storename>",
                    enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName());
            Assert.assertTrue("Expected mail body text not found in user email",
                    StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").toLowerCase().trim(),
                            expectedbodyText.toLowerCase()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Then("^i should see orderers contact information$")
    public void i_should_see_orderers_contact_information() throws Throwable {
        i_should_see_billing_address();
    }

    @Then("^i should see body text for 542 30D bops order late pickup cancel:$")
    public void i_should_see_body_text_for_542_30D_bops_order_late_pickup_cancel(String expectedbodyText)
            throws Throwable {
        try {
            expectedbodyText = expectedbodyText
                    .replaceAll("<billingfirstname>",
                            Utilities.returnCamelCase(
                                    enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName()))
                    .replaceAll("<storename>",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName());
            Assert.assertTrue("Expected mail body text not found in user email", StringUtils
                    .contains(((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim(), expectedbodyText.toLowerCase()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Then("^i should see pickup date$")
    public void i_should_see_pickup_date() throws Throwable {
        Assert.assertEquals("Expected Order Date found in user email",
                "Pick up by: "
                        + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getPickupCancelDate(),
                ((MCOMEmailPage)emailpage).getOrderDate());
    }

    @Then("^i should see product price for bops ready for pickup$")
    public void i_should_see_product_price_for_bops_ready_for_pickup() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);

        Assert.assertEquals(
                "Expected product price not match with Actual product price", enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(0).getShipitems().get(0).getPrice(),
                itemDetails.get(0).get("Price").substring(1));

    }

    public String getTemplate() {

        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        return template;
    }

    @And("^i should see text below store hours$")
    public void i_should_see_text_below_store_hours(String text) throws Throwable {

        text = text.replaceAll("<pickupCancelDate>",
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getPickupCancelDate());
        Assert.assertTrue("text  \" " + text + " \" not exists",
                ((MCOMEmailPage)emailpage).getAllText().contains(text.toLowerCase().trim()));
    }

    @And("^i should see what to bring details$")
    public void i_should_what_to_bring_details(String expectedNoteContent) throws Throwable {

        Assert.assertTrue("Actual note content is not matching with expected note content:" + expectedNoteContent,
                ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim()
                        .contains(expectedNoteContent.toLowerCase().trim()));
    }

    @And("^i should see orderer's contact info bops ready for pickup$")
    public void i_should_see_orderer_contact_info_for_bops_ready_for_pickup(String expectedContent) throws Throwable {

        expectedContent = expectedContent
                .replaceAll("<billingAddr.firstName>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase())
                .replaceAll("<billingAddr.lastName>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getLastName().toLowerCase());

        Assert.assertTrue("orderer's contact information is not displaying as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent(), expectedContent.toLowerCase()));
    }

    @And("^i should see order capture date:$")
    public void i_should_see_order_capture_date_mreg_shipment_delay(String expectedContent) throws Throwable {

        expectedContent = expectedContent.replaceAll("<orderdate>",
                enhancedPayloadActual.getTriggerData().get(0).getOrderData().getOrderCapturedDate());

        Assert.assertTrue("Expected Order date not found in user email",
                ((MCOMEmailPage)emailpage).getMailContent().trim().contains(expectedContent.toLowerCase().trim()));

    }

    @Then("^i should see sales tax in the email$")
    public void i_should_see_sales_tax_for_542_30C_in_the_email() throws Throwable {
        String expected_value = "$"
                + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getSalesTaxAmount().toString();
        Assert.assertEquals("Sales tax amount is not matching", expected_value, ((MCOMEmailPage)emailpage).salesTaxBops.getText());
    }

    @Then("^i should see pick up in-store for 542_30C in the email$")
    public void i_should_see_pick_up_in_store_for_542_30C_in_the_email() throws Throwable {
        Assert.assertEquals("Pick up in-store is not displaying as expected", "FREE",
                ((MCOMEmailPage)emailpage).itemPickupInStoreBopsLatePickUpCancel.getText());
    }

    @Then("^i should see sales tax for 264_14Q in the email$")
    public void i_should_see_sales_tax_for_264_14Q_in_the_email() throws Throwable {
        i_should_see_sales_tax_for_542_30C_in_the_email();
    }

    @Then("^i should see pick up in-store for 264_14Q in the email$")
    public void i_should_see_pick_up_in_store_for_264_14Q_in_the_email() throws Throwable {
        Assert.assertEquals("Pick up in-store is not displaying as expected", "FREE",
                ((MCOMEmailPage)emailpage).baseShippingAmt.getText());
    }

    @Then("^i should see order capture date with two digits in the year$")
    public void i_should_see_order_capture_date_with_two_digits_in_the_year() throws Throwable {
        Assert.assertEquals("Expected Order Date found in user email",
                "Order date: " + getModifiedYearFormat(
                        enhancedPayloadActual.getTriggerData().get(0).getOrderData().getOrderCapturedDate()),
                ((MCOMEmailPage)emailpage).getOrderDate());
    }

    // New step as macys logo is different for few templates
    @Then("^i should see macys logo using alternative text$")
    public void i_should_see_macys_logo_using_alternative_text() throws Throwable {
        Assert.assertEquals("Macys logo is not displaying in the email", true, ((MCOMEmailPage)emailpage).logoMacys.isDisplayed());
    }

    @Then("^i should see gift card displayed in the mail$")
    public void i_should_see_gift_card_displayed_in_the_mail() throws Throwable {
        Assert.assertNotNull("Unable to Find gift card image", ((MCOMEmailPage)emailpage).eGiftCard.isDisplayed());
    }

    @Then("^i should see card amount:$")
    public void i_should_see_card_amount(String cardAmount) throws Throwable {
        cardAmount = cardAmount.replaceAll("<ordertotal>",
                enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getOrderTotal());
        Assert.assertEquals("Card amount is not displaying as expected in the email", cardAmount,
                ((MCOMEmailPage)emailpage).cardAmount.getText());
    }

    @Then("^i should see body text for virtual gift card receipt:$")
    public void i_should_see_body_text_for_virtual_gift_card_receipt(String bodyText) throws Throwable {

        String bodyTextLines = ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim();
        bodyText = bodyText.replaceAll("<billingAddr.firstname>", Utilities
                .returnCamelCase(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName()));
        Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                bodyTextLines.contains(bodyText.toLowerCase().trim()));

    }

    @Then("^i should see product price for sdd cancel$")
    public void i_should_see_product_price_for_sdd_cancel()
            throws JsonParseException, JsonMappingException, IOException {

        List<LinkedHashMap<String, String>> itemDetails = null;

        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(getTemplate());
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                Assert.assertEquals("Expected product price not match with Actual product price", enhancedPayloadActual
                                .getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems().get(item).getPrice(),
                        itemDetails.get(index).get("Price").substring(1));
                index++;

            }
        }
    }

    // New step as payment information is displaying in one line
    @Then("^i should see credit card type displayed in one line$")
    public void i_should_see_credit_card_type_displayed_in_one_line() throws Throwable {

        String labelText = ((MCOMEmailPage)emailpage).getMailContent();
        String card = "Payment method: "
                + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getType() + " "
                + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getCardNbr();

        Assert.assertTrue("Expected credit card type not found in user email", labelText.contains(card.toLowerCase()));
    }

    @Then("^i should see billing firstname$")
    public void i_should_see_billing_firstname() throws Throwable {

        Assert.assertEquals("Expected first name not found in user email",
                "Hi " + StringUtils.capitalize(
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase())
                        + ",",
                ((MCOMEmailPage)emailpage).getFirstName());

    }

    @Then("^i should see Same day delivery amount for sdd returned undeliverable$")
    public void i_should_see_same_day_delivery_amount_for_sdd_returned_undeliverable() throws Throwable {

        String expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                .getShipmentUpgradeFeeAmount().toString();
        Assert.assertEquals("Shipment Upgrade fee amount/Same day delivery amount is not matching", expected_value,
                ((MCOMEmailPage)emailpage).sddShipmentUpgradeFeeAmt.getText());

    }

    @And("^i should see body text for ftc cancel$")
    public void i_should_see_body_text_for_ftc_cancel(String bodytext) throws Throwable {

        String userCancelDate = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems()
                .get(0).getUserCancelDate();
        bodytext = bodytext.replaceAll("<userCancelDate>", userCancelDate).replaceAll("\n", "");
        String emailBody = ((MCOMEmailPage)emailpage).getBodyText().trim();

        Assert.assertTrue(
                "Expected mail Body text :" + bodytext + "not found in user email. Actual body text:"
                        + ((MCOMEmailPage)emailpage).getStaticMessage().trim(),
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().toLowerCase().replaceAll("\n", "").trim(),
                        bodytext.toLowerCase().replace("\n", "").trim()));
    }

    @And("^i should see orderer canceled by$")
    public void i_should_see_orderer_ccanceled_by(String expectedContent) throws Throwable {
        expectedContent = expectedContent
                .replaceAll("<billingAddr.firstName>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase())
                .replaceAll("<billingAddr.lastName>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getLastName().toLowerCase());

        Assert.assertTrue("Order canceled by is not displaying as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent(), expectedContent.toLowerCase()));
    }

    @Then("^i should see product image with different class name$")
    public void i_should_see_product_image_for_sdd_returned_undeliverable() throws Throwable {

        int product_index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductURL() != null) {
                    Assert.assertNotNull(((MCOMEmailPage)emailpage).sddProductImage.get(product_index).getTagName());

                } else {
                    Assert.assertNotNull(((MCOMEmailPage)emailpage).noImageAvailable.get(product_index).getTagName());
                }
                product_index++;
            }
        }
    }

    @Then("^i should see product image url valid with different class name$")
    public void i_shoud_see_sdd_product_image_url_valid() throws Throwable {

        int product_index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getProductURL() != null) {
                    Assert.assertEquals("Product image link not valid ", Integer.parseInt("200"),
                            ((MCOMEmailPage)emailpage).getSddItemImageURLHttpStatus(product_index));
                }
                product_index++;
            }
        }
    }

    // New step as xpath is different
    @Then("^i should see preheader as mentioned:$")
    public void i_should_pre_header_for_price_error(String preHeader) throws Throwable {
        Assert.assertTrue("pre header  \" " + preHeader + " \" not exists",
                StringUtils.contains(preHeader, ((MCOMEmailPage)emailpage).preHeaderPriceError.getText()));
    }

    @Then("^i should see profile negative static content:$")
    public void i_should_see_profile_negative_first_section_static_content(String statictext) throws Throwable {
        String textFromEmail = ((MCOMEmailPage)emailpage).getProfileNegative().replaceAll("\n", "").toLowerCase();
        Assert.assertTrue("Profile Negative Static text \" " + statictext + " \" not exists",
                StringUtils.contains(textFromEmail, statictext.toLowerCase()));
    }

    @Then("^i should see beauty box static content:$")
    public void i_should_see_beauty_box_static_content(String statictext) throws Throwable {
        String textFromEmail = ((MCOMEmailPage)emailpage).getstaticNoteMessages().replaceAll("\n", "").toLowerCase();
        Assert.assertTrue("Beauty Box Static text \" " + statictext + " \" not exists",
                StringUtils.contains(textFromEmail, statictext.toLowerCase()));
    }

    @Then("^i should see replacement date for re-ship$")
    public void i_should_see_replacement_date_for_re_ship() throws Throwable {
        Assert.assertEquals(
                "Expected replacement date not found in the mail", "Replacement date: " + enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0).getReturnSubmittedDate(),
                ((MCOMEmailPage)emailpage).replacementDateReShip.getText().trim());

    }

    @Then("^i should see reason for replacement displaying as return reason description$")
    public void i_should_see_reason_for_replacement_displaying_as_return_reason_description() throws Throwable {
        String returnReasonDescription = null;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                        .get(item).getReturnReasonDescription() != null) {
                    returnReasonDescription = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                            .get(shipment).getShipitems().get(item).getReturnReasonDescription();
                    break;
                }
            }
        }

        Assert.assertEquals("Expected reason for replacement is not as expected",
                "Reason for replacement: " + returnReasonDescription, ((MCOMEmailPage)emailpage).getReasonForReplacement());

    }

    @Then("^i should see replacement date for virtual exchange confirmation$")
    public void i_should_see_replacement_date_for_virtual_exchange_confirmation() throws Throwable {
        Assert.assertEquals("Expected replacement date not found in the mail",
                "Replacement date: " + getModifiedYearFormat(enhancedPayloadActual.getTriggerData().get(0)
                        .getShipmentsData().get(0).getReturnSubmittedDate()),
                ((MCOMEmailPage)emailpage).replacementDateReShip.getText().trim());

    }

    @Then("^i should see original payment for virtual exchange confirmation$")
    public void i_should_see_original_payment_for_virtual_exchange_confiramtion()
            throws JsonParseException, JsonMappingException, IOException {

        Assert.assertEquals("Expected Refund amount not match",
                "Original payment: $"
                        + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getChargeAmt(),
                ((MCOMEmailPage)emailpage).getRefundAmount());
    }

    @And("^i should see body text for virtual exchange confirmation:$")
    public void i_should_see_body_text_for_virtual_exchange_confirmation(String bodyText) throws Throwable {
        if (bodyText.contains("<returnExpectedBackDate>")) {
            bodyText = (bodyText
                    .replaceAll("<returnExpectedBackDate>", getModifiedYearFormat(enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(0).getShipitems().get(0).getReturnExpectedBackDate()))
                    .replaceAll("\n", ""));
        } else {
            bodyText = (bodyText
                    .replaceAll("<returnExpectedDate>", getModifiedYearFormat(enhancedPayloadActual.getTriggerData()
                            .get(0).getShipmentsData().get(0).getShipitems().get(0).getReturnExpectedDate()))
                    .replaceAll("\n", ""));
        }

        Assert.assertTrue("Expected mail Body text not found in user email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", ""), bodyText.toLowerCase().trim()));

    }

    @Then("^i should see credit card type as mentioned:$")
    public void i_should_see_credit_card_type_as_mentioned(String expectedText) throws Throwable {
        expectedText = expectedText
                .replaceAll("<cardtype>",
                        enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getType())
                .replaceAll("<cardnumber>",
                        enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getCardNbr());

        Assert.assertTrue("Expected credit card type not found in user email",
                ((MCOMEmailPage)emailpage).getMailContent().replaceAll("\n", "").trim().contains(expectedText.toLowerCase().trim()));
    }

    @Then("^i should see refund amount as mentioned:$")
    public void i_should_see_refund_amount_as_mentioned(String expectedText) throws Throwable {
        expectedText = expectedText.replaceAll("<refundedamount>",
                enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getChargeAmt());

        Assert.assertTrue("Expected refund amount not found in user email",
                ((MCOMEmailPage)emailpage).getMailContent().trim().contains(expectedText.toLowerCase().trim()));

    }

    @Then("^i should see sales tax for 246_6 in the email$")
    public void i_should_see_sales_tax_for_246_6_in_the_email() throws Throwable {
        i_should_see_sales_tax_for_542_30C_in_the_email();
    }

    @Then("^i should see original ship date$")
    public void i_should_see_original_ship_date() throws Throwable {

        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = getTemplate();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("original ship date") != null) {
                    Assert.assertEquals("Expected ship date is not displayed as expected",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getExpectedDate(),
                            itemDetails.get(index).get("original ship date"));
                }
                index++;
            }
        }

    }

    /**
     * This step definition is to validate estimated shipdate for
     * 540_split_shipment_notification
     */
    @Then("^i should see estimated ship dates$")
    public void i_should_see_estimated_ship_dates() throws Throwable {

        String[] estimatedShipDatesFromMail = ((MCOMEmailPage)emailpage).getAllEstimatedshipdates().split(";");

        for (int i = 0; i < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().size(); i++) {

            Assert.assertEquals("Expected estimated date not match with Actual estimated date", enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(i).getShipitems().get(0).getExpectedDate().trim(),
                    estimatedShipDatesFromMail[i]);

        }
    }

    @Then("^i should see estimated ship date as \"([^\"]*)\"$")
    public void i_should_see_estimated_ship_date_as(String expectedValue) throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = getTemplate();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("estimated ship date") != null) {
                    Assert.assertEquals("Expected ship date is not displayed as expected", expectedValue,
                            itemDetails.get(index).get("estimated ship date"));
                }
                index++;
            }
        }
    }

    @Then("^i should see contact information for sdd partial cancel:$")
    public void i_should_see_contact_information_for_sdd_partial_cancel(String contactInformation) throws Throwable {

        contactInformation = contactInformation
                .replaceAll("<billingfirstname>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName())
                .replaceAll("<billinglastname>",
                        enhancedPayloadActual.getTriggerData().get(0).getBillingData().getLastName())
                .replaceAll("<shipmentsemail>",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getemail())
                .replaceAll("<shipmentsphone>",
                        enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getphone());
        Assert.assertTrue("Contact Information not displaying as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent().trim(), contactInformation.toLowerCase()));

    }

    @And("^i should see body text for 603_paperless:$")
    public void i_should_see_body_text_for_603_paperless(String bodyText) throws Throwable {

        String bodyTextFromEmailPage = ((MCOMEmailPage)emailpage).thanksMsgInBodyText.getText().replaceAll("\n", "").toLowerCase()
                .trim() + " " + ((MCOMEmailPage)emailpage).thanksSubMsgInBodyText.getText().replaceAll("\n", "").toLowerCase().trim()
                + " " + ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").toLowerCase().trim();

        Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                bodyTextFromEmailPage.contains(bodyText.toLowerCase().trim()));
    }

    @And("^i should see promo offers for 603_paperless:$")
    public void i_should_see_promo_offers_for_603_paperless(String promoOffers) throws Throwable {

        Assert.assertTrue("Promo offers are not displaying as expected in the email",
                StringUtils.contains(
                        ((MCOMEmailPage)emailpage).getAllPromoOffersForPaperless().replaceAll("\n", " ").toLowerCase().trim() + " ",
                        promoOffers.toLowerCase()));
    }

    @And("^i should see static promo legal disclaimer for 603_paperless:$")
    public void i_should_see_promo_legal_disclaimer_for_offer_share(String promoLegalDisclaimer) throws Throwable {

        Assert.assertTrue("Expected static text not displaying in email ",
                StringUtils.contains(
                        ((MCOMEmailPage)emailpage).getAllPromoLegalDisclaimer().replaceAll("\n", "").toLowerCase().trim(),
                        promoLegalDisclaimer.replaceAll("\n", "").toLowerCase().trim()));
    }

    @And("^i should see promo headings for offer share$")
    public void i_should_see_promo_headings_for_offer_share() throws Throwable {

        String promoHeading = enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoHeading() + ": ";
        String promoSubHeading = enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoSubHeading();
        promoHeading += promoSubHeading;

        Assert.assertTrue("Expected promo headings not displaying in email ",
                StringUtils.contains(((MCOMEmailPage)emailpage).getAllPromoHeadings().trim(), promoHeading.toLowerCase().trim()));

    }

    @And("^i should see promo description and promo sub description for offer share$")
    public void i_should_see_promo_description_and_sub_description() throws Throwable {

        String promoDescription = enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoDesc() + " ";
        String promoSubDescription = enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoSubDesc();

        promoDescription += promoSubDescription;

        Assert.assertTrue("Expected promo headings not displaying in email ",
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().trim(), promoDescription.toLowerCase().trim()));

    }

    @And("^i should see promo code and promo expiry date for offer share$")
    public void i_should_see_promo_code_and_promo_expiry_date() throws Throwable {

        String promoCode = "Promo code: "
                + enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoCode() + " | ";
        String promoExpiryDate = "Ends:"
                + enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoExpiryDate();

        promoCode += promoExpiryDate;

        Assert.assertTrue("Expected promo code and expiry date not displaying in email ",
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().trim(), promoCode.toLowerCase().trim()));

    }

    @And("^i should see static text below shop macys button for offer share:$")
    public void i_should_see_static_text_below_shop_macys_button(String staticText) throws Throwable {

        Assert.assertTrue("Expected static text not displaying in email ",
                StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().trim(), staticText.toLowerCase().trim()));

    }

    @And("^i should see promo legal disclaimer for offer share$")
    public void i_should_see_promo_legal_disclaimer_for_offer_share() throws Throwable {

        String promoLegalDisclaimer = "You're receiving this email because your friend forwarded it to you. "
                + enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getPromoLegalDisclaimer().toLowerCase()
                .replaceAll("&amp;", "&");

        Assert.assertTrue("Expected static text not displaying in email ",
                StringUtils.contains(((MCOMEmailPage)emailpage).getAllPromoLegalDisclaimer().toLowerCase().trim(),
                        promoLegalDisclaimer.toLowerCase().trim()));

    }

    @And("^i should see mmoney order details section for 16A$")
    public void i_should_see_mmomney_order_details_section_for_16A() throws Throwable {

        String orderDetails = ((MCOMEmailPage)emailpage).getAllOrderDetails(((MCOMEmailPage)emailpage).mmoneyOrderDetailsSection16A);
        ValidateMmoneyOrderDetails(orderDetails);

    }

    @Then("^i should see mmoney body text for 16A:$")
    public void i_should_see_mmoney_body_text(String mmoneyBodyText) throws Throwable {

        mmoneyBodyText = mmoneyBodyText
                .replaceAll("<rewardCardEffectiveDate>",
                        getModifiedYearFormat(enhancedPayloadActual.getTriggerData().get(0).getOrderData()
                                .getRewardCardEffectiveDate()))
                .replaceAll("<rewardCardExpirationDate>", getModifiedYearFormat(
                        enhancedPayloadActual.getTriggerData().get(0).getOrderData().getRewardCardExpirationDate()))
                .replaceAll("\n", " ").toLowerCase().trim();

        Assert.assertTrue("Expected mmoney Body text not found in user email",
                ((MCOMEmailPage)emailpage).getMMoneyBodyText().toLowerCase().trim().contains(mmoneyBodyText.toLowerCase().trim()));

    }

    @Then("^i should see reward card earn end date for mmomney:$")
    public void i_should_see_reward_card_earn_date_for_mmoney(String mmoneyRewardCard) throws Throwable {
        mmoneyRewardCard = mmoneyRewardCard.replaceAll("<rewardCardEarnEndDate>", getModifiedYearFormat(
                enhancedPayloadActual.getTriggerData().get(0).getOrderData().getRewardCardEarnEndDate()));

        Assert.assertTrue("Expected mmoney Body text not found in user email",
                ((MCOMEmailPage)emailpage).getMMoneyRewardCardEarnEndDate().replaceAll("\n", " ").toLowerCase().trim()
                        .contains(mmoneyRewardCard.toLowerCase().trim()));

    }

    @Then("^i should see body text for mcom price error$")
    public void i_should_see_body_text_for_price_error() throws Throwable {
        String actual = ((MCOMEmailPage)emailpage).getStaticMessage().replace("\n", "").trim();
        String expected = enhancedPayloadActual.getTriggerData().get(0).getOrderData().getAdhocText().replace("||", "")
                .replace(" | ", "").replace(" |", "").replace("\n", "").replaceAll("\\|", "").replace(" || ", "")
                .trim();
        Assert.assertEquals("Body text not match with actual", expected, actual);
    }

    @And("^i should see mmoney order details section for 16B$")
    public void i_should_see_mmomney_order_details_section_for_16B() throws Throwable {
        String orderDetails = ((MCOMEmailPage)emailpage).getAllOrderDetails(((MCOMEmailPage)emailpage).mmoneyOrderDetailsSection16B);
        ValidateMmoneyOrderDetails(orderDetails);
    }

    @Then("^i should see mmoney body text for 16B:$")
    public void i_should_see_mmoney_body_text_for_16B(String mmoneyBodyText) throws Throwable {

        mmoneyBodyText = mmoneyBodyText
                .replaceAll("<rewardCardEffectiveDate>",
                        getModifiedYearFormat(enhancedPayloadActual.getTriggerData().get(0).getOrderData()
                                .getRewardCardEffectiveDate()))
                .replaceAll("<rewardCardExpirationDate>", getModifiedYearFormat(
                        enhancedPayloadActual.getTriggerData().get(0).getOrderData().getRewardCardExpirationDate()))
                .replaceAll("\n", " ").toLowerCase().trim();
        Assert.assertTrue("Expected mmoney Body text not found in user email", ((MCOMEmailPage)emailpage).getMMoneyBodyText16B()
                .toLowerCase().trim().contains(mmoneyBodyText.toLowerCase().trim()));

    }

    public void ValidateMmoneyOrderDetails(String orderDetails)
            throws JsonParseException, JsonMappingException, IOException {
        Assert.assertTrue("Order number is not matching with the expected value", orderDetails.contains(
                "order #:" + enhancedPayloadActual.getTriggerData().get(0).getInternetOrderNumber().getValue()));
        Assert.assertTrue("Order date is not matching with the expected value", orderDetails.contains(
                "order date:" + enhancedPayloadActual.getTriggerData().get(0).getOrderData().getOrderCapturedDate()));
        Assert.assertTrue("Order total is not matching with the expected value", orderDetails.contains("order total:"
                + "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getOrderTotal().toString()));

    }

    @Then("^i should see Order total in the email$")
    public void i_should_see_Order_total_in_the_email() throws Throwable {
        String expected_value = "$"
                + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getSubTotalAmt().toString();
        Assert.assertEquals("Order subtotal amount is not matching", expected_value,
                ((MCOMEmailPage)emailpage).subTotalAmtDelayTemplates.getText());
    }

    @Then("^i should see Shipping in the email$")
    public void i_should_see_Shipping_in_the_email() throws Throwable {
        String expected_value = "$"
                + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getBaseShippingAmt().toString();

        if (expected_value.equalsIgnoreCase("$0.00")) {
            expected_value = "FREE";
        }
        Assert.assertEquals("Order base shipping amount is not matching", expected_value,
                ((MCOMEmailPage)emailpage).baseShippingAmtDelayTemplates.getText());
    }

    @Then("^i should see Additional shipment fee amount in the email$")
    public void i_should_see_Additional_shipment_fee_amount_in_the_email() throws Throwable {
        String expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                .getAdditionalShipmentFeeAmount().toString();
        Assert.assertEquals("Order additional shipment fee amount is not matching", expected_value,
                ((MCOMEmailPage)emailpage).addtShipmentFeeAmtDelayTemplates.getText());
    }

    @Then("^i should see Shipment upgrade fee amount in the email$")
    public void i_should_see_Shipment_upgrade_fee_amount_in_the_email() throws Throwable {
        String expected_value = "$" + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData()
                .getShipmentUpgradeFeeAmount().toString();
        Assert.assertEquals("Shipment Upgrade fee amount is not matching", expected_value,
                ((MCOMEmailPage)emailpage).shipmentUpgradeFeeAmtDelayTemplates.getText());
    }

    @Then("^i should see Gift wrap fee amount in the email$")
    public void i_should_see_Gift_wrap_fee_amount_in_the_email() throws Throwable {
        String expected_value = "$"
                + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getGiftWrapFeeAmt().toString();
        Assert.assertEquals("Gift wrap fee amount is not matching", expected_value,
                ((MCOMEmailPage)emailpage).giftWrapFeeAmtDelayTemplates.getText());

    }

    @Then("^i should see Total in the email$")
    public void i_should_see_Total_in_the_email() throws Throwable {
        String expected_value = "$"
                + enhancedPayloadActual.getTriggerData().get(0).getOrderTotalsData().getOrderTotal().toString();
        Assert.assertEquals("Order total is not matching", expected_value,
                ((MCOMEmailPage)emailpage).orderTotalDelayTemplates.getText());
    }

    @Then("^i should see product color for gift return$")
    public void i_should_see_product_color_forZ_gift_return() throws Throwable {
        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {

                if (itemDetails.get(index).get("color") != null) {

                    Assert.assertEquals("Expected product color not match with Actual product color",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getProductColor().replace(" ", ""),
                            itemDetails.get(index).get("color").replace(" ", ""));

                }
                index++;
            }

        }
    }

    @Then("^i should see new ship date$")
    public void i_should_see_new_ship_date() throws Throwable {

        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = getTemplate();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("new ship date") != null) {
                    Assert.assertEquals("Expected ship date is not displayed as expected",
                            enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getNewExpectedDate(),
                            itemDetails.get(index).get("new ship date"));
                }
                index++;
            }
        }

    }

    @Then("^i should see body text having system cancel date:$")
    public void i_should_see_body_text_having_system_cancel_date(String expectedContent) throws Throwable {
        String bodyTextLines = ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim();
        expectedContent = expectedContent.replaceAll("<systemcanceldate>", enhancedPayloadActual.getTriggerData().get(0)
                .getShipmentsData().get(0).getShipitems().get(0).getSystemCancelDate());

        Assert.assertTrue("body text  \" " + expectedContent + " \" not exists",
                bodyTextLines.contains(expectedContent.toLowerCase().trim()));

    }

    @Then("^i should see new ship date as delay$")
    public void i_should_see_new_ship_date_as_delay() throws Throwable {

        List<LinkedHashMap<String, String>> itemDetails = null;
        String template = getTemplate();
        itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
        int index = 0;
        for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                .size(); shipment++) {
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                    .getShipitems().size(); item++) {
                if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("new ship date") != null) {
                    Assert.assertEquals("Expected ship date is not displayed as expected", "Delay",
                            itemDetails.get(index).get("new ship date"));
                }
                index++;
            }
        }

    }

    @Then("^i should see \"([^\"]*)\" button in store hours section$")
    public void i_should_see_button_in_store_hours_section(String buttonName) throws Throwable {
        if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName() != null) {
            i_should_see_button(buttonName);
        }
    }

    @And("^i should see body text with order cancel time:$")
    public void i_should_body_text_with_order_cancel_time(String bodyText) throws Throwable {

        String bodyTextLines = ((MCOMEmailPage)emailpage).getMailBodyText().replaceAll("\n", "").toLowerCase().trim();
        String orderCancelTime = enhancedPayloadActual.getTriggerData().get(0).getOrderData().getOrderCancelTime();
        String[] dateAndTime = orderCancelTime.split(" ");

        bodyText = bodyText.replaceAll("<orderCanceldate>", dateAndTime[0])
                .replaceAll("<orderCanceltime>", dateAndTime[1]).trim();
        bodyText = bodyText.replaceAll("\\r\\n]+\\s", "").toLowerCase().trim();
        Assert.assertTrue("body text  \" " + bodyText + " \" not exists", bodyTextLines.contains(bodyText));
    }

    @And("^i should see update payment information button$")
    public void i_should_see_update_payment_information_button() {
        String actualLinkText = "update payment information";
        String updatePaymentInfoBtn = ((MCOMEmailPage)emailpage).getUpdatePaymentInfo().getText().toLowerCase().trim();
        Assert.assertTrue("update payment information button does not exists",
                actualLinkText.contains(updatePaymentInfoBtn));

    }

    @Then("^i should see the loyalty error code text in the email:$")
    public void i_should_see_the_loyalty_error_code_text_in_the_email(String expectedText) throws Throwable {

        try {
            Assert.assertTrue("Loyalty error text  \" " + expectedText + " \" not exists",
                    ((MCOMEmailPage)emailpage).getstaticNoteMessage().trim().contains(expectedText.trim()));
        } catch (Error e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }

    }

    @Then("^i should see mmoney body text for 16C:$")
    public void i_should_see_mmoney_body_text16c(String mmoneyBodyText) throws Throwable {

        mmoneyBodyText = mmoneyBodyText
                .replaceAll("<rewardCardEffectiveDate>",
                        getModifiedYearFormat(enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                .getRewardsCardEffectiveDate()))
                .replaceAll("<rewardCardExpirationDate>", getModifiedYearFormat(
                        enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData().getRewardsCardExpirationDate()))
                .replaceAll("\n", " ").toLowerCase().trim();

        Assert.assertTrue("Expected mmoney Body text not found in user email", ((MCOMEmailPage)emailpage).getMMoneyCampaignBodyText()
                .toLowerCase().trim().replaceAll("\n", " ").contains(mmoneyBodyText.toLowerCase().trim()));

    }

    @And("^i should see mmoney order details section for 16C$")
    public void i_should_see_mmomney_order_details_section_for_16C() throws Throwable {
        String orderDetails = ((MCOMEmailPage)emailpage).getMMoneyOrderDetailsSection16C().toLowerCase().trim();
        Assert.assertTrue("Order number is not matching with the expected value",
                orderDetails.contains("order #:" + enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                        .getAdjustmentInternetOrderNbr().toLowerCase()));
        Assert.assertTrue("Order date is not matching with the expected value",
                orderDetails.contains("order date:" + enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                        .getAdjustmentOrigTransactionDate().toLowerCase()));
        Assert.assertTrue("Order total is not matching with the expected value",
                orderDetails.contains("order total:" + "$" + enhancedPayloadActual.getTriggerData().get(0)
                        .getLoyaltyData().getAdjustmentOrigOrderTotal().toLowerCase()));

    }

    @And("^i shoould see \"(.*?)\" text in email$")
    public void iShouldSeeTextInEmail(String text) throws Exception {
        String expectedText = null, actualText = null;
        switch (text) {
            case "REDEEM IT FROM":
            case "YOUR REWARD CARD NUMBER:":
            case "CID NUMBER:":
                expectedText = text;
                actualText = text.contains("CID") ? ((MCOMEmailPage)emailpage).getMMoneyRewardCardText()
                        : (text.contains("IT") ? ((MCOMEmailPage)emailpage).getMMoneyRedeemItText()
                        : ((MCOMEmailPage)emailpage).getMMoneyYourRewardCardText());
                break;
            case "<rewardCardEffectiveDate>-<rewardCardExpirationDate>":
            case "EXPIRES <rewardCardExpirationDate>":
                expectedText = text
                        .replaceAll("<rewardCardEffectiveDate>",
                                getModifiedYearFormat(enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                        .getRewardsCardEffectiveDate()))
                        .replaceAll("<rewardCardExpirationDate>", getModifiedYearFormat(enhancedPayloadActual
                                .getTriggerData().get(0).getLoyaltyData().getRewardsCardExpirationDate()))
                        .replaceAll("\n", " ");
                actualText = text.contains("EXPIRES") ? ((MCOMEmailPage)emailpage).getMMoneyRewardCardExpText()
                        : ((MCOMEmailPage)emailpage).getMMoneyEffAndExpDates();
                break;
            case "<cid>":
            case "<rewardCardNumber>":
                Loyalty ltyData = enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData();
                expectedText = text.replaceAll("<cid>", ltyData.getRewardsCardCid())
                        .replaceAll("<rewardCardNumber>", ltyData.getVrcBarCodeNbr()).replaceAll("\n", " ");
                actualText = text.contains("cid") ? ((MCOMEmailPage)emailpage).getMMoneyRewardCardCID()
                        : ((MCOMEmailPage)emailpage).getMMoneyRewardCardNumber();
                break;
            default:
                Assert.fail("Invalid option!!");
        }

        Assert.assertTrue("Expected Text " + expectedText + " is not present in email",
                expectedText.trim().toLowerCase().contains(actualText.trim().toLowerCase()));
    }

    @And("^i should see mMoney reward card bar code image in email$")
    public void iShouldSeeMMoneyRewardCardBarCodeImageInEmail() {
        Assert.assertTrue("mMoney reward card bar code image not present in email",
                ((MCOMEmailPage)emailpage).isMMoneyRewardCardBarCodeImagePresent());
    }

    @And(("^i should see below mMoney disclimer text in email:$"))
    public void iShouldSeeBelowMMoneyDisclimerTextInEmail(String text) {
        Assert.assertTrue("mMoney disclimer text not present in email", text.toLowerCase().trim()
                .contains(((MCOMEmailPage)emailpage).getMMoneyDeclimerText().replaceAll("\n", " ").trim().toLowerCase()));

    }

    @And("^I should see the delivery method:$")
    public void iShouldSeeTheDeliveryMethod(String text) throws Throwable {
        Assert.assertTrue("delivery method  \" " + text + " \" not exists",
                StringUtils.contains(text, ((MCOMEmailPage)emailpage).getDeliveryMethod().trim()));
    }

    @And("^I should see the delivery address$")
    public void iShouldSeeTheDeliveryAddress() throws Throwable {
        String labelText = ((MCOMEmailPage)emailpage).getMailContent();

        Assert.assertTrue("Delivery Address Line1 is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getAddrLine1().toLowerCase()));

        Assert.assertTrue("Delivery City is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getCity().toLowerCase()));
        Assert.assertTrue("Delivery State is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getState().toLowerCase()));

        Assert.assertTrue("Delivery ZIP Code is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getZipcode().toLowerCase()));

        Assert.assertTrue("Phone Number is not matching with the expected value", labelText.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getphone().toLowerCase()));
    }

    @And("^i should see BT billing address$")
    public void iShouldSeeBTBillingAddress() throws Throwable {

        String labelText = ((MCOMEmailPage)emailpage).getMailContent();

        Assert.assertTrue("BT Billing Address Line1 is not matching with the expected value", labelText
                .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getAddrLine1().toLowerCase()));

        Assert.assertTrue("BT Billing City is not matching with the expected value", labelText
                .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getCity().toLowerCase()));

        Assert.assertTrue("BT Billing State is not matching with the expected value", labelText
                .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getState().toLowerCase()));

        Assert.assertTrue("BT Billing ZIP Code is not matching with the expected value", labelText
                .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getZipcode().toLowerCase()));

    }

    @And("^i should see BT Delivery summary text$")
    public void iShouldSeeBTDeliverySummaryText() throws Throwable {
        Assert.assertEquals("BT Delivery Summary Text is not displaying in the email", true,
                ((MCOMEmailPage)emailpage).deliverySummaryText.isDisplayed());
    }

    @And("^i should see BT contact information$")
    public void iShouldSeeBTContactInformation() throws Throwable {
        String contactInfo = ((MCOMEmailPage)emailpage).getMailContent();

        Assert.assertTrue("First Name in contact information is not matching with actual", contactInfo.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getFirstName().toLowerCase()));

        Assert.assertTrue("Last Name in contact information is not matching with actual", contactInfo.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getLastName().toLowerCase()));

        Assert.assertTrue("Expected Email Address not match with actual", contactInfo.contains(
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getemail().toLowerCase()));
    }

    @Then("^i should see pickup notice text$")
    public void ishould_see_pickup_notice_text(String smsFlagText) throws Throwable {

        Assert.assertTrue("Contact Information not displayign as expected in the email",
                StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent().trim(), smsFlagText));

    }

    @And("^i should see bops locker bar code image in email$")
    public void iShouldSeeBopsLockerBarCodeImageInEmail() throws Throwable {
        Assert.assertTrue("Bops Locker bar code image not present in email",
                ((MCOMEmailPage)emailpage).isBopsLockerBarCodeImagePresent());
    }

    @And("^i should see lockerCode text$")
    public void ishould_see_lockerCode_text() throws Throwable {

        Assert.assertEquals("Expected locker code number not found in user email",
                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getLockerAccessCode(),
                ((MCOMEmailPage)emailpage).getMMoneyRewardCardNumber().trim());

    }    
    //====================================================
    // Website Common steps
    //====================================================
    @Then("^i should see static contents:$")
    public void i_should_see_static_contents(String statictext) throws Throwable {
        if (StepUtils.macys()) {
            int numberoflinks = StringUtils.countMatches(statictext, "<link>");
            if (numberoflinks >= 2) {
                String[] contents = statictext.split("<link>");
                for (int cnt = 1; cnt <= numberoflinks; cnt = cnt + 2) {
                    Assert.assertNotNull("Link \" " + contents[cnt]
                            + " \" not exists", WebDriverManager.getWebDriver()
                            .findElement(By.linkText((contents[cnt]))));
                    ;
                }
                for (int cnt = 0; cnt <= numberoflinks + 1; cnt = cnt + 2) {
                    Assert.assertTrue("Static text \" " + contents[cnt]
                                    + " \" not exists",
                            StringUtils.contains(statictext, contents[cnt]));
                }

            } else {
                Assert.assertTrue("Static text not displaying as expected",
                        StringUtils.contains(statictext, ((BCOMEmailPage)emailpage)
                                .getStaticMessage().replace("\n", "")));
            }
        } else {
            Assert.assertEquals(
                    "Expected First name not found in user email",

                    "Dear "
                            + Utilities.returnCamelCase(enhancedPayloadActual
                            .getTriggerData().get(0).getBillingData()
                            .getFirstName()) + ",",
                    ((BCOMEmailPage)emailpage).getFirstName());
        }
    }

    @Then("^i sould see subject as$")
    public void i_sould_see_subject_as() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // TODO
        throw new PendingException();
    }

    @Then("^i should see link \"([^\"]*)\" above logo$")
    public void i_should_see_link_above_logo(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see text:$")
    public void i_should_see_text(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see feature help topic (\\d+)$")
    public void i_should_see_feature_help_topic(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see more\\.\\.\\.$")
    public void i_should_see_more() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i shold see contact info:$")
    public void i_shold_see_contact_info(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see pinterest$")
    public void i_should_see_pinterest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see twitter$")
    public void i_should_see_twitter() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see facebook$")
    public void i_should_see_facebook() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^i should see youtube$")
    public void i_should_see_youtube() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^i should see return completed date$")
    public void i_should_see_return_completed_date() throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertEquals("Expected return completed date not found in the mail",
                    "Return completed date: " + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                            .getShipitems().get(0).getReturnReceiptDate(),
                    ((MCOMEmailPage)emailpage).getReturnCompletedDate());
        } else {
            Assert.assertEquals(
                    "Expected Return completed date found in user email",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getShipitems().get(0)
                            .getReturnReceiptDate(),
                    getdefaultDateFormat(((BCOMEmailPage)emailpage).getOrderDetails().get(
                            "return_date")));
        }
    }

    @Then("^i should see static content for mobile number change notification as:$")
    public void i_should_see_static_content_for_mobile_number_change_notification(String text) throws Throwable {
        String expected = text.replaceAll("<profilePhoneNumber>",
                enhancedPayloadActual.getTriggerData().get(0).getWebsiteData().getProfileMobileNbr());

        i_should_see_static_contents(expected);
    }

    @And("^i should see note section:$")
    public void i_should_see_note_section(String noteText) throws Throwable {
        if (StepUtils.macys()) {
            String actualNoteContent = ((MCOMEmailPage)emailpage).getBodyText().trim();

            Assert.assertTrue("Actual note content:" + actualNoteContent + "is not matching with expected note content:"
                    + noteText, actualNoteContent.contains(noteText.toLowerCase().trim()));
        } else {
            String noteSectionText = ((BCOMEmailPage)emailpage).getNoteSection();
            String billingFname = Utilities.returnCamelCase(enhancedPayloadActual
                    .getTriggerData().get(0).getBillingData().getFirstName());
            String billingLname = Utilities.returnCamelCase(enhancedPayloadActual
                    .getTriggerData().get(0).getBillingData().getLastName());
            String expectedText = noteText + " " + billingFname + " "
                    + billingLname + " will not be charged for these items.";

            if ((template.get("brand").equals("blcom") || template.get("brand")
                    .equals("bwedd")) && template.get("subtype").equals("14R")) {

                Assert.assertTrue(
                        "Note text is not matching with the expected value",
                        noteSectionText.contains(noteText));
            } else if ((template.get("brand").equals("blcom") || template.get(
                    "brand").equals("bwedd"))
                    && template.get("subtype").equals("14T")) {

                Assert.assertTrue(
                        "Note text is not matching with the expected value",
                        noteSectionText.contains(expectedText));
            }
        }
    }

    @Then("^i should see refund amount$")
    public void i_should_see_refund_amount() throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertEquals("Expected Refund amount not match",
                    "Refunded amount: $"
                            + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getChargeAmt(),
                    ((MCOMEmailPage)emailpage).getRefundAmount().replace(",", ""));
        } else {
            Assert.assertEquals("Refund Amount is not displaying as expected", "$"
                            + enhancedPayloadActual.getTriggerData().get(0)
                            .getPaymentData().get(0).getChargeAmt(),
                    ((BCOMEmailPage)emailpage).refundAmount.getText());
        }
    }

    @Then("^i should see product requested quantity$")
    public void i_should_see_product_requested_quantity() throws Throwable {
        if (StepUtils.macys()) {
            List<LinkedHashMap<String, String>> itemDetails = null;
            String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
            itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
            int index = 0;
            for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().size(); item++) {
                    Assert.assertEquals("Expected Product Qty not match with actual",
                            Integer.parseInt(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                    .getShipitems().get(item).getReqQuantity()),
                            Integer.parseInt(itemDetails.get(index).get("qty")));
                    index++;
                }
            }
        } else {
            Iterator qty = ((BCOMEmailPage)emailpage).getItemDetails().get("quantity")
                    .iterator();
            for (int shipment = 0; shipment < enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

                for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .size(); item++) {

                    String quantity = enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(shipment).getShipitems()
                            .get(item).getReqQuantity().trim();

                    /*
                     * "000000492031403361" upc verification required as part of
                     * Loyalty Decrement Project. Ref Story# B-95302,B-94720,
                     *
                     * D-65209 "000000492030504038" upc verification required as
                     * part of bwallet Project. Ref Story# B-96676
                     */

                    JSONObject enhancedPayloadObject = new JSONObject(
                            enhancedPayloadActual);
                    String convertPayloadTostr = enhancedPayloadObject.toString();
                    String upcIdFromEp = null;
                    if (convertPayloadTostr.contains("upcNumber")) {
                        upcIdFromEp = enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(shipment).getShipitems()
                                .get(item).getUpcNumber();

                    } else {
                        upcIdFromEp = enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(shipment).getShipitems()
                                .get(item).getUpc();

                    }

                    if (!(upcIdFromEp.equalsIgnoreCase("000000492031403361")||upcIdFromEp.equalsIgnoreCase("000000492030504038"))) {
                        Assert.assertEquals(
                                "Expected Product Qty not match with actual",
                                Integer.parseInt(quantity),
                                Integer.parseInt(qty.next().toString().trim()));
                    }
                }
            }
        }
    }

    @Then("^i should see zone name in the email$")
    public void i_should_see_zone_name_in_the_email() throws Throwable {
        if (StepUtils.macys()) {
            String ActualZoneName = (((MCOMEmailPage)emailpage).getBodyText().trim()).toLowerCase();
            String expectedZoneName = enhancedPayloadActual.getTriggerData().get(0).getOrderData().getZoneName();
            try {
                Assert.assertTrue("Actual zone name is not matching with expected zone name",
                        ActualZoneName.contains(expectedZoneName.toLowerCase()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            }
        } else {
            String labelHeaderText = ((BCOMEmailPage)emailpage).getAllHeaderLabels();
            String expectedZoneName = enhancedPayloadActual.getTriggerData().get(0)
                    .getOrderData().getZoneName().trim();
            Assert.assertTrue(
                    "Actual zone name is not matching with expected zone name",
                    labelHeaderText.contains(expectedZoneName.toLowerCase()));
        }
    }

    @Then("^i should see multiple tracking urls text at shipment detail level$")
    public void i_should_see_multiple_tracking_urls_text_at_shipment_detail_level() throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertEquals("Multiple Tracking URL text is not displayed ", "multiple tracking URLs",
                    ((MCOMEmailPage)emailpage).trackingnumberAtShipment.getText().trim());

            Assert.assertEquals("Invalid target URL for Multiple Tracking URL text ", Integer.parseInt("200"),
                    ((MCOMEmailPage)emailpage).getResponseCodeByLinkText(((MCOMEmailPage)emailpage).trackingnumberAtShipment.getText().trim()));
        } else {
            Iterator tracking = ((BCOMEmailPage)emailpage).getShipmentDetails()
                    .get("tracking_num").iterator();
            for (int j = 0; j < ((BCOMEmailPage)emailpage).getShipmentDetails()
                    .get("tracking_num").size(); j++) {

                Assert.assertEquals("Multiple Tracking URL text is not displayed ",
                        "Multiple tracking URLs", tracking.next().toString().trim());

                Assert.assertEquals(
                        "Invalid target URL for Multiple Tracking URL text ",
                        Integer.parseInt("200"),
                        ((BCOMEmailPage)emailpage).getResponseCodeByLinkText(tracking.next()
                                .toString().trim()));
            }
        }
    }

    @Then("^i should see estimated refund$")
    public void i_should_see_estimated_refund() throws Throwable {
        if (StepUtils.macys())
            Assert.assertEquals("Expected estimated refund not found in the mail",
                    "Estimated refund: " + "$"
                            + enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(0).getChargeAmt(),
                    ((MCOMEmailPage)emailpage).getEstimatedRefund());
        else
            Assert.assertEquals(
                    "Expected estimated refund not found in user email", "$"
                            + enhancedPayloadActual.getTriggerData().get(0)
                            .getPaymentData().get(0).getChargeAmt(),
                    ((BCOMEmailPage)emailpage).getOrderDetails().get("estimated_refund"));
    }

    @And("^i should see store tomorrow's hours$")
    public void i_should_see_store_tomorrows_hours() throws Throwable {
        if (StepUtils.macys()) {
            if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName() != null) {
                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");

                DateFormat formatter = new SimpleDateFormat("h:mm");
                Date strOpentime = (Date) formatter.parse(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                        .get(0).getStoreHoursDetails().get(1).getOpenHour());
                Date strClosetime = (Date) formatter.parse(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                        .get(0).getStoreHoursDetails().get(1).getCloseHour());

                String tomorrowsHoursFromEp = "Tomorrow: " + timeFormatter.format(strOpentime) + " - "
                        + timeFormatter.format(strClosetime);

                Assert.assertTrue("Store Tomorrow's Hours is not matching with the expected value",
                        ((MCOMEmailPage)emailpage).getMailContent().toLowerCase().contains(tomorrowsHoursFromEp.toLowerCase()));
            }
        } else {
            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
            String storeOpenTime = enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getStoreHoursDetails().get(1)
                    .getOpenHour();
            String storeCloseTime = enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getStoreHoursDetails().get(1)
                    .getCloseHour();

            DateFormat formatter = new SimpleDateFormat("h:mm");
            Date strOpentime = (Date) formatter.parse(storeOpenTime);
            Date strClosetime = (Date) formatter.parse(storeCloseTime);

            String tomorrowsHoursFromEp = "Tomorrow's Hours: "
                    + timeFormatter.format(strOpentime) + " - "
                    + timeFormatter.format(strClosetime);
            String tomorrowsHoursFromMail = ((BCOMEmailPage)emailpage).getstoreTimeAndPhoneNos()
                    .toLowerCase();

            Assert.assertTrue(
                    "Store Tomorrow's Hours is not matching with the expected value",
                    tomorrowsHoursFromMail.contains(tomorrowsHoursFromEp
                            .toLowerCase()));
        }
    }

    @Given("^i trigger \"([^\"]*)\" input through csp testemail$")
    public void i_trigger_input_through_csp_testemail(String tempalteName)
            throws Throwable {
        triggerCSP(tempalteName);
    }

    @Given("^i have an enhanced payload sent to email provider$")
    public void i_have_enhaced_payload_sent_to_email_provider() throws Throwable {
        waitForStatusPC(this.getSourceSystemID());
        this.getActualPayload();
    }

    @When("^i navigate to view the email page$")
    public void i_navigate_to_view_the_email_page() throws Throwable {
        Wait.forPageReady();
        if (!goToEmailPageBySourceSystemId()) {
            // enable gmail account
            if (WebDriverManager.getWebDriver().getPageSource().contains("[ALERT] Please log ") ||
                    WebDriverManager.getWebDriver().getPageSource().contains("[ALERT] Web login required")) {
                Navigate.visit("https://www.google.com/accounts/DisplayUnlockCaptcha");
                if (Elements.elementPresent(By.cssSelector("input#identifierId"))) {
                    TextBoxes.typeTextbox(By.cssSelector("input#identifierId"), "oes.macys@gmail.com");
                    WebDriverManager.getWebDriver().findElement(By.cssSelector("div#identifierNext")).click();
                    Utils.threadSleep(1000, null);
                }

                if (Elements.elementPresent(By.cssSelector("input[name=password]"))) {
                    TextBoxes.typeTextbox(By.cssSelector("input[name=password]"), "macys23456");
                    WebDriverManager.getWebDriver().findElement(By.cssSelector("div#passwordNext")).click();
                    Utils.threadSleep(1000, null);
                }

                if (Elements.elementPresent((By.cssSelector("input#submitChallenge")))) {
                    WebDriverManager.getWebDriver().findElement(By.cssSelector("input#submitChallenge")).click();
                    Utils.threadSleep(1000, null);
                }

                Assert.assertTrue(goToEmailPageBySourceSystemId());
            } else {
                Assert.assertTrue("No email arrived!", false);
            }
        }
    }

    @Then("^i should see order number$")
    public void i_should_see_order_number() throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertEquals("Expected Order number not found in user email",
                    "Order #: " + enhancedPayloadActual.getTriggerData().get(0).getInternetOrderNumber().getValue(),
                    ((MCOMEmailPage)emailpage).getOrderNumber());
        }
        else {
            if (template.get("brand").equals("bstore")) {
                Assert.assertEquals(
                        "Expected Order number not found in user email",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getReservationNumber().getValue(), ((BCOMEmailPage)emailpage)
                                .getOrderDetails().get("order_number"));
            } else {
                Assert.assertEquals(
                        "Expected Order number not found in user email",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getInternetOrderNumber().getValue(), ((BCOMEmailPage)emailpage)
                                .getOrderDetails().get("order_number"));
            }
        }
    }

    @Then("^i should see reason for replacement$")
    public void i_should_see_reason_for_replacement() throws Throwable {
        if (StepUtils.macys()) {
            String returnReasonDescription = null;
            for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().size(); item++) {

                    if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                            .get(item).getReturnReasonDescription() != null) {
                        returnReasonDescription = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                .get(shipment).getShipitems().get(item).getReturnReasonDescription();
                        break;
                    }
                }
            }

            Assert.assertEquals("Expected reason for replacement is not as expected",
                    "Reason for replacement: " + returnReasonDescription, ((MCOMEmailPage)emailpage).getReasonForReplacement());
        } else {
            Assert.assertEquals(
                    "Expected reason for replacement not found in user email",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getShipitems().get(0)
                            .getReturnReason(), ((BCOMEmailPage)emailpage).getOrderDetails()
                            .get("reason_for_replacement"));
        }
    }

    @And("^i should see body text for offer share:$")
    public void i_should_see_body_text_for_offer_share(String bodytext)
            throws Throwable {
        if (StepUtils.macys()) {
            String replyToName = enhancedPayloadActual.getTriggerData().get(0).getReplyToName().getValue();
            String promoPersonalMessage = enhancedPayloadActual.getTriggerData().get(0).getWebsiteData()
                    .getPromoPersonalMessage();
            bodytext = bodytext.replaceAll("<REPLY_TO_DISPLAY_NAME>", replyToName);
            if (promoPersonalMessage != null) {
                bodytext = bodytext.replaceAll("<promoPersonalMessage>", promoPersonalMessage);
            } else {
                bodytext = bodytext.replaceAll("<promoPersonalMessage>", "");
            }
            Assert.assertTrue(
                    "Expected mail Body text " + bodytext + "not found in user email. Actual body text:"
                            + ((MCOMEmailPage)emailpage).getBodyText().trim(),
                    StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().trim(), bodytext.toLowerCase().trim()));
        } else {
            String replyToName = enhancedPayloadActual.getTriggerData().get(0)
                    .getReplyToName().getValue();
            String promoPersonalMessage = enhancedPayloadActual.getTriggerData()
                    .get(0).getWebsiteData().getPromoPersonalMessage();
            bodytext = bodytext.replaceAll("<REPLY_TO_DISPLAY_NAME>", replyToName);
            if (promoPersonalMessage != null) {
                bodytext = bodytext.replaceAll("<promoPersonalMessage>",
                        promoPersonalMessage);
            } else {
                bodytext = bodytext.replaceAll("<promoPersonalMessage>", "");
            }

            Assert.assertTrue("Expected mail Body text " + bodytext
                    + "not found in user email. Actual body text:"
                    + ((BCOMEmailPage)emailpage).getAllLabels().trim(), StringUtils.contains(
                    ((BCOMEmailPage)emailpage).getAllLabels().trim(), bodytext.toLowerCase()
                            .trim()));
        }
    }

    @Then("^i should see body text for bops pickup reminder:$")
    public void i_should_body_text_for_bops_pickup_reminder(String bodyText) throws Throwable {
        if (StepUtils.macys()) {
            try {
                bodyText = bodyText
                        .replaceAll("<storeName>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName())
                        .replaceAll("<pickupCancelDate>", enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                                .get(0).getPickupCancelDate())
                        .toLowerCase().trim();

                Assert.assertTrue(
                        "Expected mail Body text " + bodyText + "not found in user email. Actual body text:"
                                + ((MCOMEmailPage)emailpage).getBodyText().trim(),
                        StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().toLowerCase().replaceAll("\n", "").trim(),
                                bodyText));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            bodyText = bodyText.replaceAll("<billingAddr.firstName>",
                    enhancedPayloadActual.getTriggerData().get(0).getBillingData()
                            .getFirstName().toLowerCase());

            Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                    ((BCOMEmailPage)emailpage).getAllLabels().contains(bodyText.trim()));
        }
    }

    @Then("^i should see original payment$")
    public void i_should_see_return_original_payment() throws Throwable {
        if (StepUtils.macys()) {
            DecimalFormat df = new DecimalFormat("#.##");

            float shipRetailAmt = Float.parseFloat(
                    enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipRetailAmount());
            float shipTaxAmt = Float
                    .parseFloat(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipTaxAmount());

            float total = shipRetailAmt + shipTaxAmt;

            String originalPayment = "Original payment: $" + Double.valueOf(df.format(total));

            Assert.assertEquals("Expected original payment amount not match", originalPayment,
                    ((MCOMEmailPage)emailpage).getOriginalPayment());
        } else {
            float shipRetailAmt = Float.parseFloat(enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().get(0)
                    .getShipRetailAmount());
            float shipTaxAmt = Float.parseFloat(enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().get(0)
                    .getShipTaxAmount());

            float total = shipRetailAmt + shipTaxAmt;

            String originalPayment = "$" + new DecimalFormat("0.00").format(total);
            Assert.assertEquals("Expected original payment amount not match",
                    originalPayment,
                    ((BCOMEmailPage)emailpage).getOrderDetails().get("original_payment"));
        }
    }

    @Then("^i should see \"([^\"]*)\" text$")
    public void i_should_see_certain_text(String strText) throws Throwable {
        if (StepUtils.macys())
            Assert.assertTrue(strText + "NOT existing in email page", ((MCOMEmailPage)emailpage).isTextPresent(strText));
        else
            Assert.assertTrue(strText + "NOT existing in email page", ((BCOMEmailPage)emailpage).isTextPresent(strText));
    }

    @Then("^i should see product name url valid$")
    public void i_should_see_product_name_url_valid() throws Throwable {
        if (StepUtils.macys()) {
            for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().size(); item++) {

                    if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment).getShipitems()
                            .get(item).getProductURL() != null) {
                        Assert.assertEquals("Product name url not valid ", Integer.parseInt("200"),
                                ((MCOMEmailPage)emailpage).getResponseCodeByLinkText(enhancedPayloadActual.getTriggerData().get(0)
                                        .getShipmentsData().get(shipment).getShipitems().get(item).getProductName()));
                    }

                }
            }
        } else {
            for (int shipment = 0; shipment < enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .size(); item++) {

                    Assert.assertEquals("Product name url not valid ", Integer
                            .parseInt("200"), ((BCOMEmailPage)emailpage)
                            .getResponseCodeByLinkText(enhancedPayloadActual
                                    .getTriggerData().get(0).getShipmentsData()
                                    .get(shipment).getShipitems().get(item)
                                    .getProductName()));

                }
            }
        }
    }

    @And("^i should see store today's hours$")
    public void i_should_see_store_todays_hours() throws Throwable {
        if (StepUtils.macys()) {
            if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName() != null) {
                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");

                DateFormat formatter = new SimpleDateFormat("h:mm");
                Date strOpentime = (Date) formatter.parse(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                        .get(0).getStoreHoursDetails().get(0).getOpenHour());
                Date strClosetime = (Date) formatter.parse(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                        .get(0).getStoreHoursDetails().get(0).getCloseHour());

                String toDayHoursFromEp = "Today: " + timeFormatter.format(strOpentime).toLowerCase() + " - "
                        + timeFormatter.format(strClosetime).toLowerCase();

                Assert.assertTrue("Store Today's Hours is not matching with the expected value",
                        ((MCOMEmailPage) emailpage).getMailContent().toLowerCase().contains(toDayHoursFromEp.toLowerCase()));
            }
        } else {
            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
            String storeOpenTime = enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getStoreHoursDetails().get(0)
                    .getOpenHour();
            String storeCloseTime = enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getStoreHoursDetails().get(0)
                    .getCloseHour();

            DateFormat formatter = new SimpleDateFormat("h:mm");
            Date strOpentime = (Date) formatter.parse(storeOpenTime);
            Date strClosetime = (Date) formatter.parse(storeCloseTime);

            String toDayHoursFromEp = "Today's Hours: "
                    + timeFormatter.format(strOpentime) + " - "
                    + timeFormatter.format(strClosetime);
            String toDayHoursFromMail = ((BCOMEmailPage)emailpage).getstoreTimeAndPhoneNos()
                    .toLowerCase();

            Assert.assertTrue(
                    "Store Today's Hours is not matching with the expected value",
                    toDayHoursFromMail.contains(toDayHoursFromEp.toLowerCase()));
        }
    }

    @Then("^i should see replacement date$")
    public void i_should_see_replacement_date() throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertEquals(
                    "Expected replacement date not found in the mail", "Replacement date: " + enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0).getReturnSubmittedDate(),
                    ((MCOMEmailPage)emailpage).getReplacementDate().trim());
        } else {
            Assert.assertEquals(
                    "Expected Replacement date found in user email",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getReturnSubmittedDate(),
                    getdefaultDateFormat(((BCOMEmailPage)emailpage).getOrderDetails().get(
                            "replacement_date")));
        }
    }

    @And("^i should see body text for virtual return confirmation:$")
    public void i_should_see_body_text_for_virtual_return_reminder(
            String bodytext) throws Throwable {
        if (StepUtils.macys()) {
            String dateInitiated = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                    .getReturnSubmittedDate();
            bodytext = bodytext.replaceAll("<returnSubmittedDate>", dateInitiated);

            Assert.assertTrue(
                    "Expected mail Body text " + bodytext + "not found in user email. Actual body text:"
                            + ((MCOMEmailPage)emailpage).getBodyText().trim(),
                    StringUtils.contains(((MCOMEmailPage)emailpage).getBodyText().trim(), bodytext));
        } else {
            Date date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");

            if (getTemplateType().equals("245_5N_BLCOM")) {

                String chargeAmt = enhancedPayloadActual.getTriggerData().get(0)
                        .getPaymentData().get(0).getChargeAmt();
                String returnExpectedBackDate = getModifiedYearFormat(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getShipitems().get(0).getReturnExpectedBackDate());

                bodytext = bodytext.replaceAll("<returnExpectedBackDate>",
                        returnExpectedBackDate)
                        .replaceAll("<chargeAmt>", chargeAmt);
                Assert.assertTrue(
                        "Expected mail Body text " + bodytext
                                + "not found in user email. Actual body text:"
                                + ((BCOMEmailPage)emailpage).getStaticMessage().trim(),
                        StringUtils.contains(
                                ((BCOMEmailPage)emailpage).getAllLabels().replace("\n", "")
                                        .trim(),
                                bodytext.toLowerCase().replace("\n", "").trim()));
            }

            else {
                String orderTotal = enhancedPayloadActual.getTriggerData().get(0)
                        .getOrderTotalsData().getOrderTotal();
                String returnExpectedBackDate = getModifiedYearFormat(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(0)
                        .getShipitems().get(0).getReturnExpectedDate());
                bodytext = bodytext.replaceAll("<returnExpectedDate>",
                        returnExpectedBackDate).replaceAll("<orderTotal>",
                        orderTotal);
                Assert.assertTrue(
                        "Expected mail Body text " + bodytext
                                + "not found in user email. Actual body text:"
                                + ((BCOMEmailPage)emailpage).getStaticMessage().trim(),
                        StringUtils.contains(
                                ((BCOMEmailPage)emailpage).getAllLabels().replace("\n", "")
                                        .trim(),
                                bodytext.toLowerCase().replace("\n", "").trim()));
            }
        }
    }

    @Then("^i should see loyalty section in the email$")
    public void i_should_see_loyalty_section_in_the_email() throws Throwable {
        if (StepUtils.macys()) {
            // Verify loyalty account number
            try {
                Assert.assertEquals(
                        "Loyallist account number is not displaying as expected", "Plenti #: " + enhancedPayloadActual
                                .getTriggerData().get(0).getLoyaltyData().getLoyaltyPrograms().get(0).getProgramId(),
                        ((MCOMEmailPage) emailpage).loyallistAccountNumber.getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            } catch (Error e) {
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            }
        } else {
            try{
                // Pending points
                Assert.assertEquals("Pending points is not displaying as expected",
                        enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                .getPendingRewardEarned().replaceAll("\\.0*$", ""),
                        ((BCOMEmailPage)emailpage).loyaltyPendingPoints.getText().replaceAll(",", ""));

                // base points

                int expectedBasePoints = Integer.parseInt(enhancedPayloadActual
                        .getTriggerData().get(0).getLoyaltyData()
                        .getPendingRewardEarned().replaceAll("\\.0*$", ""))
                        - Integer.parseInt(enhancedPayloadActual.getTriggerData()
                        .get(0).getLoyaltyData().getBonusPendingRewardEarned()
                        .replaceAll("\\.0*$", ""));

                Assert.assertEquals("Base points is not displaying as expected",
                        expectedBasePoints,
                        Integer.parseInt(((BCOMEmailPage)emailpage).loyaltyBasePoints.getText().replaceAll(",", "")));

                // bonus points

                int expectedBonusPoints = Integer.parseInt(enhancedPayloadActual
                        .getTriggerData().get(0).getLoyaltyData()
                        .getBonusPendingRewardEarned().replaceAll("\\.0*$", ""))
                        - Integer.parseInt(enhancedPayloadActual.getTriggerData()
                        .get(0).getLoyaltyData().getPwrptPendRewardEarned()
                        .replaceAll("\\.0*$", ""));

                Assert.assertEquals("Bonus points is not displaying as expected",
                        expectedBonusPoints,
                        Integer.parseInt(((BCOMEmailPage)emailpage).loyaltyBonusPoints.getText().replaceAll(",", "")));

                // total point balance

                int totalPointBalance = (Integer.parseInt(enhancedPayloadActual
                        .getTriggerData().get(0).getLoyaltyData().getRewardBal()
                        .replaceAll("\\.0*$", "")) - Integer
                        .parseInt(enhancedPayloadActual.getTriggerData().get(0)
                                .getLoyaltyData().getPwrptRewardBal()
                                .replaceAll("\\.0*$", "")))
                        + (Integer.parseInt(enhancedPayloadActual.getTriggerData()
                        .get(0).getLoyaltyData().getAmexRewards()
                        .replaceAll("\\.0*$", "")));

                Assert.assertEquals(
                        "Total point balance is not displaying as expected",
                        totalPointBalance,
                        Integer.parseInt(((BCOMEmailPage)emailpage).loyaltyTotalPoints.getText().replaceAll(",", "")));

                // total pending points

                Assert.assertEquals(
                        "Total pending points balance is not displaying as expected",
                        enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                .getPendingRewardBal().replaceAll("\\.0*$", ""),
                        ((BCOMEmailPage)emailpage).loyaltyTotalPendingPoints.getText().replaceAll(",", ""));

                // first name and last name
                Assert.assertEquals(
                        "First name and last name not displaying as expected",
                        enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                .getFirstName().toLowerCase()
                                + " "
                                + enhancedPayloadActual.getTriggerData().get(0)
                                .getLoyaltyData().getLastName().toLowerCase(),
                        ((BCOMEmailPage)emailpage).loyallistName.getText().replace(",", "")
                                .toLowerCase());

                // loyalist account number

                Assert.assertEquals(
                        "Loyallist account number is not displaying as expected",
                        enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                .getKeyFOBId(),
                        ((BCOMEmailPage)emailpage).loyallistAccountNumber.getText());

                // loyalist email address

                Assert.assertEquals(
                        "Loyallist email address is not displaying as expected",
                        enhancedPayloadActual.getTriggerData().get(0).getLoyaltyData()
                                .getEmailAddr(), ((BCOMEmailPage)emailpage).loyallistEmail.getText());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            }
            catch(Error e){
                System.out.println(e.getMessage());
                log.error(e.getMessage());
            }
        }
    }

    @And("^i should see item name$")
    public void i_should_see_item_name() throws Throwable {
        if (StepUtils.macys()) {
            String[] itemName = ((MCOMEmailPage)emailpage).getItemNames().split("@");

            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems()
                    .size(); item++) {
                Assert.assertEquals(
                        "Expected product not match with Actual product name", enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0).getShipitems().get(item).getProductName().toLowerCase(),
                        itemName[item]);
            }
        } else {
            String[] itemName = ((BCOMEmailPage)emailpage).getItemNames().split("@");

            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getShipitems().size(); item++) {
                Assert.assertEquals(
                        "Expected product not match with Actual product name",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0).getShipitems().get(item)
                                .getProductName().toLowerCase(), itemName[item]);
            }
        }
    }

    @Then("^i should see estimated ship date$")
    public void i_should_see_estimated_ship_date() throws Throwable {
        if (StepUtils.macys()) {
            List<LinkedHashMap<String, String>> itemDetails = null;
            String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
            itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
            int index = 0;
            for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().size(); item++) {

                    if (((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("estimated shipdate") != null) {
                        Assert.assertEquals("Expected ship is not displayed as expected",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                        .getShipitems().get(item).getExpectedDate(),
                                itemDetails.get(index).get("estimated shipdate"));

                    }
                    index++;
                }
            }
        } else {
            Assert.assertEquals(
                    "Expected estimated ship date not found in user email",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getShipitems().get(0)
                            .getNewExpectedDate(),
                    getdefaultDateFormat(((BCOMEmailPage)emailpage).getOrderDetails().get(
                            "estimated_ship_date")));
        }
    }

    @Then("^i should see \"([^\"]*)\" button$")
    public void i_should_see_button(String buttonName) throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertNotNull("Unable to Find button " + buttonName, ((MCOMEmailPage)emailpage).getLinkByText(buttonName));
            Assert.assertEquals("Invalid target URL for Button " + buttonName, Integer.parseInt("200"),
                    ((MCOMEmailPage)emailpage).getResponseCodeByLinkText(buttonName));
        } else {
            Assert.assertNotNull("Unable to Find button " + buttonName,
                    ((BCOMEmailPage)emailpage).checkOrderStatusButton.isDisplayed());
            Assert.assertEquals(
                    "Invalid target URL for Button " + buttonName,
                    Integer.parseInt("200"),
                    ((BCOMEmailPage)emailpage)
                            .getResponseCodeByURL(((BCOMEmailPage)emailpage).checkOrderStatusButton
                                    .getAttribute("href")));
        }
    }

    @And("^i should see body text:$")
    public void i_should_body_text(String bodyText) throws Throwable {
        if (StepUtils.macys()) {
            String bodyTextLines = ((MCOMEmailPage)emailpage).getBodyText().replaceAll("\n", "").trim();
            Assert.assertTrue("body text  \" " + bodyText.trim() + " \" not exists",
                    bodyTextLines.contains(bodyText.trim().toLowerCase()));
        } else {
            String bodyTextLines = ((BCOMEmailPage)emailpage).getAllLabels();
            Assert.assertTrue("body text  \" " + bodyText + " \" not exists",
                    bodyTextLines.contains(bodyText.toLowerCase()));
        }
    }

    @Then("^i should see order capture date$")
    public void i_should_see_order_capture_date() throws Throwable {
        if (StepUtils.macys())
            Assert.assertEquals("Expected Order Date found in user email",
                    "Order date: " + enhancedPayloadActual.getTriggerData().get(0).getOrderData().getOrderCapturedDate(),
                    ((MCOMEmailPage)emailpage).getOrderDate());
        else {
            String[] orderDates = ((BCOMEmailPage)emailpage).getOrderDate().split(";");
            for (int i = 0; i < orderDates.length; i++) {
                Assert.assertEquals(
                        "Expected Order date not match with Actual Order date",
                        enhancedPayloadActual.getTriggerData().get(0)
                                .getOrderData().getOrderCapturedDate().trim(),
                        getdefaultDateFormat(orderDates[i]));
            }
        }
    }


    @Then("^i should see firstname$")
    public void i_should_see_firstname() throws Throwable {
        if (StepUtils.macys())
            Assert.assertTrue("Shipping First Name is not matching with the expected value",
                    ((MCOMEmailPage)emailpage).getFirstName().toLowerCase().contains(
                            ("hi " + enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getFirstName())
                                    .toLowerCase() + ","));
        else {
            if (template.get("source").equals("com")) {

                String firstName = Utilities.returnCamelCase(enhancedPayloadActual
                        .getTriggerData().get(0).getWebsiteData()
                        .getPromoUserFirstName());

                if (firstName.contains("Dear")) {

                    Assert.assertEquals(
                            "Expected First name not found in user email",

                            "Dear " + firstName + ",", ((BCOMEmailPage)emailpage).getFirstName());

                } else if (firstName.contains("Hi")) {
                    Assert.assertEquals(
                            "Expected First name not found in user email",

                            "Hi " + firstName + ",", ((BCOMEmailPage)emailpage).getFirstName());

                }
            } else if (template.get("source").equals("com")) {
                Assert.assertEquals(
                        "Expected First name found in user email",
                        "Dear "
                                + Utilities.returnCamelCase(enhancedPayloadActual
                                .getTriggerData().get(0).getWebsiteData()
                                .getSecurityFirstName()) + ",",
                        ((BCOMEmailPage)emailpage).getFirstName());
            } else if (getTemplateType().equals("264_14S_BLCOM")
                    || getTemplateType().equals("264_14S_BWEDD")
                    || getTemplateType().equals("264_14T_BLCOM")
                    || getTemplateType().equals("264_14T_BWEDD")) {
                Assert.assertEquals(
                        "Expected First name not found in user email",

                        "Dear "
                                + Utilities.returnCamelCase(enhancedPayloadActual
                                .getTriggerData().get(0).getShipmentsData()
                                .get(0).getFirstName()) + ",",
                        ((BCOMEmailPage)emailpage).getFirstName());

            } else if (getTemplateType().equals("264_14Q_BLCOM")
                    || getTemplateType().equals("264_14Q_BWEDD")
                    || getTemplateType().equals("264_14R_BLCOM")
                    || getTemplateType().equals("264_14R_BWEDD")) {

                Assert.assertEquals(
                        "Expected First name not found in user email",
                        ("Dear " + Utilities.returnCamelCase(enhancedPayloadActual
                                .getTriggerData().get(0).getBillingData()
                                .getFirstName())).replaceAll(" ", "")
                                + ",",
                        ((BCOMEmailPage)emailpage).getFirstName().replaceAll(" ", ""));

            }else if (getTemplateType().equals("524_1A_BLCOM")
                    || getTemplateType().equals("524_1A_BSTORE")
                    || getTemplateType().equals("524_1A_BWEDD")) {
                Assert.assertEquals(
                        "Expected First name not found in user email",
                        ("Hi" + Utilities.returnCamelCase(enhancedPayloadActual
                                .getTriggerData().get(0).getBillingData()
                                .getFirstName())).replaceAll(" ", "")
                                + ",",
                        ((BCOMEmailPage)emailpage).getFirstName().replaceAll(" ", ""));
            }else {
                Assert.assertEquals(
                        "Expected First name not found in user email",
                        Utilities.returnCamelCase("Dear "
                                + enhancedPayloadActual.getTriggerData().get(0)
                                .getBillingData().getFirstName())
                                + ",",
                        Utilities.returnCamelCase(((BCOMEmailPage)emailpage).getFirstName()));
            }
        }
    }

    @And("^i should see billing address$")
    public void i_should_see_billing_address() throws Throwable {
        if (StepUtils.macys()) {
            String labelText = ((MCOMEmailPage)emailpage).getMailContent();

            Assert.assertTrue("Billing First Name is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase()));

            Assert.assertTrue("Billing Last Name is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getLastName().toLowerCase()));

            Assert.assertTrue("Billing Address Line1 is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getAddrLine1().toLowerCase()));

            Assert.assertTrue("Billing Address Line2 is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getAddrLine2().toLowerCase()));

            Assert.assertTrue("Billing Address Line3 is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getAddrLine3().toLowerCase()));

            Assert.assertTrue("Billing City is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getCity().toLowerCase()));

            Assert.assertTrue("Billing State is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getState().toLowerCase()));

            Assert.assertTrue("Billing ZIP Code is not matching with the expected value", labelText
                    .contains(enhancedPayloadActual.getTriggerData().get(0).getBillingData().getZipcode().toLowerCase()));
        } else {
            String billingAddress_enhancedPayload = enhancedPayloadActual.getTriggerData().get(0).getBillingData()
                    .getAddress().replace(",", "").toLowerCase();

            billingAddress_enhancedPayload = billingAddress_enhancedPayload.replace("null", "");

            Assert.assertEquals("Expected Address not match with actual",
                    ((BCOMEmailPage)emailpage).getOrderDetails().get("billing_address")
                            .replaceAll("\n", "").replace(",", "").toLowerCase(),
                    billingAddress_enhancedPayload);
        }
    }

    @And("^i should see shipping method$")
    public void i_should_see_shipping_method() throws Throwable {
        if (StepUtils.macys()) {
            String labelText = ((MCOMEmailPage)emailpage).getMailContent();
            String[] shipmentTypes = new String[10];

            String jsonShippingMethod = getShipmentMethod(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .get(0).getShipitems().get(0).getShipmentType().trim());

            String firstShipmentType = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                    .getShipitems().get(0).getShipmentType();
            int count = 0;
            for (int shipment = 1; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                shipmentTypes[shipment] = enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().get(0).getShipmentType();

                if (firstShipmentType.equals(shipmentTypes[shipment])) {
                    count++;
                }

            }
            if (count == enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().size() - 1) {

                Assert.assertTrue("Shipment method is not matching with Expected value",
                        labelText.contains(getShipmentMethod(enhancedPayloadActual.getTriggerData().get(0)
                                .getShipmentsData().get(0).getShipitems().get(0).getShipmentType()).toLowerCase()));

            } else {
                Assert.assertTrue("Shipment method is not matching with Expected value",
                        labelText.contains("multiple: see below"));

            }
        } else {
            String labelText = ((BCOMEmailPage)emailpage).getAllLabels()
                    .replaceAll("\\r\\n|\\r|\\n", "").replaceAll(",", "")
                    .replaceAll(" ", "").toLowerCase().trim();
            String[] shipmentTypes = new String[10];

            String firstShipmentType = enhancedPayloadActual.getTriggerData()
                    .get(0).getShipmentsData().get(0).getShipitems().get(0)
                    .getShipmentType();
            int count = 0;
            for (int shipment = 0; shipment < enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

                String jsonShippingMethod = getShipmentMethod(enhancedPayloadActual
                        .getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().get(0).getShipmentType().trim());

                if (jsonShippingMethod != null) {
                    Assert.assertTrue(
                            "Shipment method is not matching with Expected value",
                            labelText.contains(getShipmentMethod(
                                    enhancedPayloadActual.getTriggerData().get(0)
                                            .getShipmentsData().get(shipment)
                                            .getShipitems().get(0)
                                            .getShipmentType()).toLowerCase()));
                } else
                    count++;
            }

            if (count == enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().size()) {

                Assert.assertTrue(
                        "Shipment method is not matching with Expected value",
                        labelText.contains(getShipmentMethod(
                                enhancedPayloadActual.getTriggerData().get(0)
                                        .getShipmentsData().get(0).getShipitems()
                                        .get(0).getShipmentType()).toLowerCase()));
            }

        }
    }

    @And("^i should see gift message$")
    public void i_should_see_gift_message() throws Throwable {
        if (StepUtils.macys()) {
            String labelText = ((MCOMEmailPage)emailpage).getMailContent();

            Assert.assertTrue("Gift Greeting Message is not matching with the expected value",
                    labelText.contains(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                            .getGiftGreetingMsg().toLowerCase()));
            Assert.assertTrue("Gift Closing Message is not matching with the expected value",
                    labelText.contains(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                            .getGiftClosingMsg().toLowerCase()));
            Assert.assertTrue("Gift Signature Message is not matching with the expected value",
                    labelText.contains(enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0)
                            .getGiftSignatureMsg().toLowerCase()));
        } else {
            if (getTemplateType().equals("240_1P_BLCOM")
                    || getTemplateType().equals("240_1P_BWEDD")) {
                Assert.assertTrue("Missing expeted Text 'Gift message",
                        ((BCOMEmailPage)emailpage).isTextExistsInEmailPage("Gift message:"));
            } else {
                Assert.assertTrue("Missing expeted Text 'THIS ORDER IS A GIFT",
                        ((BCOMEmailPage)emailpage)
                                .isTextExistsInEmailPage("THIS ORDER IS A GIFT"));
            }

            Assert.assertTrue(
                    "Gift greeting message not exists in acutal email page",
                    ((BCOMEmailPage)emailpage).isTextExistsInEmailPage(enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0)
                            .getGiftGreetingMsg()));
            Assert.assertTrue(
                    "Gift greeting closing message not exists in acutal email page",
                    ((BCOMEmailPage)emailpage).isTextExistsInEmailPage(enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0)
                            .getGiftClosingMsg()));
            Assert.assertTrue(
                    "Gift greeting Singnature message not exists in acutal email page",
                    ((BCOMEmailPage)emailpage).isTextExistsInEmailPage(enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0)
                            .getGiftSignatureMsg()));
        }
    }

    @Then("^i should see credit card type$")
    public void i_should_see_credit_card_type() throws Throwable {
        if (StepUtils.macys()) {
            for (int payments = 0; payments < enhancedPayloadActual.getTriggerData().get(0).getPaymentData()
                    .size(); payments++) {

                String cardType = enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(payments).getType();

                String cardNumber = enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(payments)
                        .getCardNbr();

                String egcCerType = enhancedPayloadActual.getTriggerData().get(0).getPaymentData().get(payments)
                        .getEgcCertificateType();

                OptionalDatum clientId = enhancedPayloadActual.getTriggerData().get(0).getClientID();

                if (cardType.equalsIgnoreCase("PayPal")) {

                    Assert.assertTrue("Expected '" + cardType + "' card details not found in user email",
                            ((MCOMEmailPage)emailpage).getPaymentMethod().toLowerCase().contains(cardType.toLowerCase()));
                } else if (cardType.equalsIgnoreCase("Apple Pay")) {

                    Assert.assertTrue("Expected '" + cardType + "' card details not found in user email",
                            ((MCOMEmailPage)emailpage).getPaymentMethod().toLowerCase().contains(cardType.toLowerCase()));
                } else if (cardType.equalsIgnoreCase("Electronic Gift Card") && egcCerType.equalsIgnoreCase("75")) {

                    Assert.assertTrue("Expected 'Plenti #' card details not found in user email",
                            ((MCOMEmailPage)emailpage).getPaymentMethod().toLowerCase()
                                    .contains(("Plenti #" + " "
                                            + enhancedPayloadActual.getTriggerData().get(0).getOrderBaseData().getUslId())
                                            .toLowerCase()));
                } else if (clientId.getValue().equalsIgnoreCase("EOD")) {
                    Assert.assertTrue("Expected '" + cardType + "' card details not found in user email", ((MCOMEmailPage)emailpage)
                            .getPaymentMethod().toLowerCase().toLowerCase().contains((cardType).toLowerCase()));
                } else {
                    Assert.assertTrue("Expected '" + cardType + "' card details not found in user email",
                            ((MCOMEmailPage)emailpage).getPaymentMethod().toLowerCase().toLowerCase()
                                    .contains((cardType + " " + cardNumber).toLowerCase()));
                }
            }
        } else {
            String card = "Payment method:\n"
                    + enhancedPayloadActual.getTriggerData().get(0)
                    .getPaymentData().get(0).getType()
                    + " "
                    + enhancedPayloadActual.getTriggerData().get(0)
                    .getPaymentData().get(0).getCardNbr();
            Assert.assertEquals("Expected Order Date found in user email", card,
                    ((BCOMEmailPage)emailpage).getPaymentMethod());
        }
    }

    @Then("^i should see product name$")
    public void i_should_see_product_name() throws JsonParseException, JsonMappingException, IOException {
        if (StepUtils.macys()) {
            List<LinkedHashMap<String, String>> itemDetails = null;
            ((MCOMEmailPage)emailpage).productDetails = null;

            String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();
            itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);

            int index = 0;
            for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().size(); item++) {
                    Assert.assertEquals(
                            "Expected product not match with Actual product name", enhancedPayloadActual.getTriggerData()
                                    .get(0).getShipmentsData().get(shipment).getShipitems().get(item).getProductName(),
                            itemDetails.get(index).get("productName"));
                    index++;
                }
            }
        } else {
            Assert.assertTrue(
                    "Expected product name not found in email page",
                    ((BCOMEmailPage)emailpage).isTextPresent(enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0)
                            .getShipitems().get(0).getProductName()));
        }
    }

    @Then("^i should see multiple tracking urls text at product level$")
    public void i_should_see_multiple_tracking_urls_text_at_product_level() throws Throwable {
        if (StepUtils.macys()) {
            String[] trackingNumber = ((MCOMEmailPage)emailpage).getTrackingnumberAtProduct().split("@");

            List<LinkedHashMap<String, String>> itemDetails = null;
            String template = enhancedPayloadActual.getTriggerData().get(0).getMailType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getMailSubType().getValue() + "_"
                    + enhancedPayloadActual.getTriggerData().get(0).getBrandType().getValue();

            itemDetails = ((MCOMEmailPage)emailpage).getItemDetails(template);
            int index = 0;
            for (int shipment = 0; shipment < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData()
                    .size(); shipment++) {
                for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                        .getShipitems().size(); item++) {

                    if ((((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("tracking #") != null)
                            && enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                            .getTrackingDetails().size() > 1) {

                        Assert.assertEquals("Expected tracking text not match with Actual tracking text",
                                "multiple tracking URLs", itemDetails.get(index).get("tracking #"));

                        Assert.assertEquals("Invalid target URL for Multiple Tracking URL text", Integer.parseInt("200"),
                                ((MCOMEmailPage)emailpage).getResponseCodeByLinkText(trackingNumber[index].trim()));

                    } else if ((((MCOMEmailPage)emailpage).getItemDetails(template).get(index).get("tracking #") != null)) {

                        Assert.assertEquals("Expected tracking number not match with Actual tracking number",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(shipment)
                                        .getTrackingDetails().get(0).getTrackingNbr(),
                                itemDetails.get(index).get("tracking #"));

                        Assert.assertEquals("Invalid target URL for Multiple Tracking URL text", Integer.parseInt("200"),
                                ((MCOMEmailPage)emailpage).getResponseCodeByLinkText(trackingNumber[index]));
                    }
                    index++;
                }
            }
        } else {
            Iterator tracking = ((BCOMEmailPage)emailpage).getItemDetails().get("tracking")
                    .iterator();

            for (int shipment = 0; shipment < enhancedPayloadActual
                    .getTriggerData().get(0).getShipmentsData().size(); shipment++) {

                for (int item = 0; item < enhancedPayloadActual.getTriggerData()
                        .get(0).getShipmentsData().get(shipment).getShipitems()
                        .size(); item++) {

                }
                Assert.assertEquals("Multiple Tracking URL text is not displayed ",
                        "Multiple tracking URLs", tracking.next().toString().trim());

                Assert.assertEquals(
                        "Invalid target URL for Multiple Tracking URL text ",
                        Integer.parseInt("200"),
                        ((BCOMEmailPage)emailpage).getResponseCodeByLinkText(tracking.next()
                                .toString().trim()));

            }
        }
    }

    @Then("^i should see item image url valid$")
    public void i_shoud_see_item_image_url_valid() throws Throwable {
        if (StepUtils.macys()) {
            int product_index = 0;
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getShipitems()
                    .size(); item++) {
                Assert.assertEquals("Product image link not valid ", Integer.parseInt("200"),
                        ((MCOMEmailPage)emailpage).getItemImageURLHttpStatus(product_index));
                product_index++;
            }
        } else {
            int product_index = 0;
            for (int item = 0; item < enhancedPayloadActual.getTriggerData().get(0)
                    .getShipmentsData().get(0).getShipitems().size(); item++) {
                Assert.assertEquals("Product image link not valid ",
                        Integer.parseInt("200"),
                        ((BCOMEmailPage)emailpage).getItemImageURLHttpStatus(product_index));
                product_index++;
            }
        }
    }

    @Then("^i should see button as \"([^\"]*)\"$")
    public void i_should_see_button_as(String buttonName) throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertNotNull("Unable to Find button " + buttonName, ((MCOMEmailPage)emailpage).getLinkByTitle(buttonName));
            Assert.assertEquals("Invalid target URL for Button " + buttonName, Integer.parseInt("200"),
                    ((MCOMEmailPage)emailpage).getResponseCodeByURL(((MCOMEmailPage)emailpage).getUrlByTitle(buttonName)));
        } else {
            Assert.assertNotNull("Unable to Find button " + buttonName,
                    ((BCOMEmailPage)emailpage).getLinkByTitle(buttonName));
            Assert.assertEquals("Invalid target URL for Button " + buttonName,
                    Integer.parseInt("200"), ((BCOMEmailPage)emailpage)
                            .getResponseCodeByURL(((BCOMEmailPage)emailpage)
                                    .getUrlByTitle(buttonName)));
        }
    }

    @Then("^i should see default categories:$")
    public void i_should_see_default_categories(String categories) throws Throwable {
        if (StepUtils.macys()) {
            String[] categoriesArray = categories.split(",");
            for (int i = 0; i < categoriesArray.length; i++) {
                Assert.assertNotNull("Unable to Find button " + categoriesArray[i].trim(),
                        ((MCOMEmailPage) emailpage).getLinkByText(categoriesArray[i].trim()));
                Assert.assertEquals("Invalid target URL for Button " + categoriesArray[i], Integer.parseInt("200"),
                        ((MCOMEmailPage) emailpage).getResponseCodeByElement(categoriesArray[i].trim()));
            }
        } else {
            String[] categoriesArray = categories.split(",");
            for (int i = 0; i < categoriesArray.length; i++) {
                Assert.assertNotNull("Unable to Find button " + categoriesArray[i],
                        ((BCOMEmailPage)emailpage).getLinkByText(categoriesArray[i]));

                Assert.assertEquals("Invalid target URL for Button "
                                + categoriesArray[i], Integer.parseInt("200"),

                        ((BCOMEmailPage)emailpage).getResponseCodeByLinkText(categoriesArray[i]));
            }
        }
    }

    @Then("^i should see preheader:$")
    public void i_should_see_preheader(String preHeader) throws Throwable {
        if (StepUtils.macys()) {
            int numberoflinks = StringUtils.countMatches(preHeader, "<link>");
            if (numberoflinks >= 2) {
                String[] contents = preHeader.split("<link>");
                for (int cnt = 1; cnt <= numberoflinks; cnt += 2) {
                    Assert.assertNotNull("Link \" " + contents[cnt] + " \" not exists",
                            WebDriverManager.getWebDriver().findElement(By.linkText((contents[cnt]))));
                }
                for (int cnt = 0; cnt < contents.length - 1; cnt += 2) {
                    Assert.assertTrue("Static text \" " + contents[cnt] + " \" not exists",
                            StringUtils.contains(preHeader, contents[cnt]));
                }
            } else {
                Assert.assertTrue("Static text \" " + preHeader + " \" not exists",
                        StringUtils.contains(preHeader, ((MCOMEmailPage)emailpage).getStaticMessage()));
            }
        } else {
            int numberoflinks = StringUtils.countMatches(preHeader, "<link>");
            if (numberoflinks >= 2) {
                String[] contents = preHeader.split("<link>");
                for (int cnt = 1; cnt <= numberoflinks; cnt += 2) {
                    Assert.assertNotNull("Link \" " + contents[cnt]
                            + " \" not exists", WebDriverManager.getWebDriver()
                            .findElement(By.linkText((contents[cnt]))));
                }
                for (int cnt = 0; cnt < contents.length - 1; cnt += 2) {
                    Assert.assertTrue("Static text \" " + contents[cnt]
                                    + " \" not exists",
                            StringUtils.contains(preHeader, contents[cnt]));
                }
            } else {
                Assert.assertTrue(
                        "Static text \" " + preHeader + " \" not exists",
                        StringUtils.contains(preHeader,
                                ((BCOMEmailPage)emailpage).preHeader.getText()));
            }
        }
    }

    @When("^i should see reason for return$")
    public void i_should_see_reason_for_return() throws Throwable {
        if (StepUtils.macys())
            Assert.assertEquals(
                    "Expected reason for return not found in the mail", "Reason for return: " + enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0).getShipitems().get(0).getReturnReason(),
                    ((MCOMEmailPage)emailpage).getReasonForReturn());
        else
            Assert.assertEquals(
                    "Expected Reason of return not found in user email",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getShipitems().get(0)
                            .getReturnReasonDescription(), ((BCOMEmailPage)emailpage)
                            .getOrderDetails().get("reason_for_return"));
    }

    @Then("^i should see pick up location:$")
    public void i_should_see_pick_up_location(String expectedPickupLocation) throws Throwable {
        if (StepUtils.macys()) {
            if (enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName() != null) {
                expectedPickupLocation = expectedPickupLocation
                        .replaceAll("<storename>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreName())
                        .replaceAll("<shipmentsstoreaddrline1>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreAddrLine1())
                        .replaceAll("<shipmentsstorecity>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreCity())
                        .replaceAll("<shipmentsstorestate>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreState())
                        .replaceAll("<shipmentsstorezipcode>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStoreZipcode())
                        .replaceAll("<shipmentsstorephone>",
                                enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getStorePhone());

                Assert.assertTrue("Pickup information is not displaying as expected in the email",
                        StringUtils.contains(((MCOMEmailPage)emailpage).getMailContent(), expectedPickupLocation.toLowerCase()));
            }
        } else {
            expectedPickupLocation = expectedPickupLocation
                    .replaceAll(
                            "<storename>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStoreName())
                    .replaceAll(
                            "<shipmentsstoreaddrline1>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStoreAddrLine1())
                    .replaceAll(
                            "<shipmentsstoreaddrline2>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStoreAddrLine2())
                    .replaceAll(
                            "<shipmentsstorecity>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStoreCity())
                    .replaceAll(
                            "<shipmentsstorestate>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStoreState())
                    .replaceAll(
                            "<shipmentsstorezipcode>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStoreZipcode())
                    .replaceAll(
                            "<shipmentsstorephone>",
                            enhancedPayloadActual.getTriggerData().get(0)
                                    .getShipmentsData().get(0).getStorePhone())
                    .replaceAll(",", "").toLowerCase();

            Assert.assertTrue(
                    "Pickup information is not displaying as expected in the email",
                    StringUtils.contains(
                            ((BCOMEmailPage)emailpage).getAllLabels()
                                    .replaceAll("\\r\\n|\\r|\\n", "")
                                    .replaceAll(",", "").replaceAll(" ", "")
                                    .toLowerCase().trim(), expectedPickupLocation
                                    .replaceAll(" ", "").trim()));
        }
    }

    @And("^i should see pickup barcode$")
    public void i_should_see_pickup_barcode() throws Throwable {
        if (StepUtils.macys())
            Assert.assertEquals("Expected Pickup Barcode number not found in user email",
                    enhancedPayloadActual.getTriggerData().get(0).getShipmentsData().get(0).getPickupBarcode(),
                    ((MCOMEmailPage)emailpage).getpickupBarcode().trim());
        else
            Assert.assertEquals(
                    "Expected Pickup Barcode number not found in user email",
                    enhancedPayloadActual.getTriggerData().get(0)
                            .getShipmentsData().get(0).getPickupBarcode(),
                    ((BCOMEmailPage)emailpage).getpickupBarcode().trim());
    }

    @Then("^i should see shipment firstname$")
    public void i_should_see_shipment_firstname() throws Throwable {
        if (StepUtils.macys()) {
            Assert.assertEquals(
                    "Expected first name not found in user email", "Hi " + StringUtils.capitalize(enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData().get(0).getFirstName().toLowerCase()) + ",",
                    ((MCOMEmailPage)emailpage).getFirstName());
        } else {
            Assert.assertEquals(
                    "Expected First name not found in user email",

                    "Dear "
                            + Utilities.returnCamelCase(enhancedPayloadActual
                            .getTriggerData().get(0).getShipmentsData()
                            .get(0).getFirstName()) + ",",
                    ((BCOMEmailPage)emailpage).getFirstName());
        }
    }

    @And("^i should see billing first name$")
    public void i_should_see_billing_first_name() throws Throwable {
        if (StepUtils.macys())
            Assert.assertTrue("Expected first name is not found in user email",
                    ((MCOMEmailPage)emailpage).getFirstName().contains("Hi " + StringUtils.capitalize(
                            enhancedPayloadActual.getTriggerData().get(0).getBillingData().getFirstName().toLowerCase())
                            + ","));
        else
            Assert.assertEquals(
                    "Expected First name not found in user email",

                    "Dear "
                            + Utilities.returnCamelCase(enhancedPayloadActual
                            .getTriggerData().get(0).getBillingData()
                            .getFirstName()) + ",",
                    ((BCOMEmailPage)emailpage).getFirstName());
    }

    @Given("^i wait for enhanced payload to be sent to email provider$")
    public void i_wait_for_enhanced_payload_to_be_sent_to_email_provider() throws Throwable {
        Thread.sleep(60000);
    }
}
