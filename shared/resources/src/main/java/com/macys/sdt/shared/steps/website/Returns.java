package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.ReturnService;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Element;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.macys.sdt.framework.interactions.Elements.getText;
import static com.macys.sdt.framework.interactions.Navigate.switchWindow;
import static com.macys.sdt.framework.interactions.Navigate.switchWindowClose;
import static com.macys.sdt.shared.utils.CommonUtils.closeIECertError;


public class Returns extends StepUtils {

    private static final Logger log = LoggerFactory.getLogger(Returns.class);
    public int maxTries = 10;
    public static JSONObject returnOrderDetails;
    String orderNum = null;
    String cntrlNumber;
    String barCodeSource;
    Map orderHeaderDetails_fromODPage = new HashedMap();
    public static String emailID = null;



    /**
     * Navigates to order return confirmation page using given order and user type data
     * <p>
     * Order details come from "return_orders.json" resource file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @param userType  "guest" or "signed in"
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to confirmation page using \"([^\"]*)\" order as a \"(guest|signed in)\" user$")
    public void I_navigate_to_confirmation_page_using_order_as_a_user(String orderType, String userType) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        returnsPage.navigateToSelectionPage(orderType, userType.equals("guest"));
        returnsPage.selectItemsAndContinueToSubmitPage();
        log.info("Click on returns submit button");
        Clicks.click("return_submit.submit_return");
        Wait.secondsUntilElementPresent("return_confirmation.crl_number", 30);
        Wait.forPageReady();
        if (!onPage("return_confirmation")) {
            Assert.fail("User is not navigated to return confirmation page!!");
        }
        Clicks.clickIfPresent("home.popup_close");
    }

    /**
     * Navigates to order return confirmation page using given order and user type data
     * <p>
     * Order details come from "return_orders.json" resource file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @param userType  "guest" or "signed in"
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to return selection page using \"([^\"]*)\" order as a \"(guest|signed in)\" user$")
    public void I_navigate_to_return_selection_page_using_order_as_a_user(String orderType, String userType) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        returnsPage.navigateToSelectionPage(orderType, userType.equals("guest"));
    }


    /**
     * Navigates to order details page using given order and user type data
     * <p>
     * Order details come from "return_orders.json" resource file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @param userType  "guest" or "signed in"
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to OD page using \"([^\"]*)\" order as a \"(guest|signed in)\" user$")
    public void I_navigate_OD_page_using_order_as_a_user(String orderType, String userType) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        returnsPage.navigateToODPage(orderType, userType.equals("guest"));
    }

    /**
     * Verifies that barcode and crl number are visible on return confirmation page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see barcode and crl number$")
    public void I_should_see_barcode_and_crl_number() throws Throwable {
        if (!Elements.elementPresent("return_confirmation.crl_number")) {
            Assert.fail("crl number not displayed on return confirmation page!!");
        }
        if (!Elements.elementPresent("return_confirmation.barcode")) {
            Assert.fail("barcode not displayed on return confirmation page!!");
        }
    }

    /**
     * Navigates to guest order history page
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit order history page as a guest user$")
    public void I_visit_order_history_page_as_a_guest_user() throws Throwable {
        new PageNavigation().I_visit_the_web_site_as_a_guest_user();
        Wait.untilElementPresent("return_selection.order_number");
        Clicks.click("home.goto_order_status");
        Wait.forPageReady();
        if (!onPage("order_status")) {
            Assert.fail("User is not redirected to order status page!!");
        }
    }

    /**
     * Navigates to an order page using given order and user type data
     * <p>
     * Order details come from "return_orders.json" resource file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @param userType  "guest" or "signed in"
     * @throws Throwable if any exception occurs
     */
    @When("^I select \"([^\"]*)\" order as a \"([^\"]*)\" user$")
    public void I_select_order_as_as_user(String orderType, String userType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        String password = "";
        String EmailID = "";
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
        if (userType.equals("signed")) {
            if (returns.orderExistsByOrderNumber(orderNumber)) {
                returns.deleteOrderRecord(orderNumber);
            }
            CommonUtils.signInOrCreateAccount();
            log.info("Email = " + TestUsers.currentEmail);
            returns.insertOrderByOrderNumber(orderNumber, TestUsers.currentEmail);
        }
    }

    /**
     * Associated an order with the and existing user account
     * <p>
     * Data comes from "order_mods_data.json" data file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @throws Throwable if any exception occurs
     */
    @When("^I associate \"([^\"]*)\" order in db$")
    public void I_associate_order_in_db(String orderType) throws Throwable {
        orderNum = Utils.getOrderNumber(orderType);
        log.info(String.format("Extracted order number %s for order type %s", orderNum, orderType));
        ReturnService returnService = new ReturnService();
        returnService.deleteReturnRecord(orderNum);
        returnService.deleteOrderRecord(orderNum);
        if (returnService.orderExistsByOrderNumber(orderNum)) {
            Map userData = returnService.getUserDetails(orderNum);
            String passWord = Utils.decryptPassword(userData.get("password").toString());
            if (safari()) {
                Wait.secondsUntilElementPresent("home.goto_my_account_link", 15);
            }
            if (MEW()) {
                GlobalNav.openGlobalNav();
                GlobalNav.navigateOnGnByName("My Account");
                GlobalNav.closeGlobalNav();
            } else {
                Clicks.click("home.goto_my_account_link");
            }
            if (safari()) {
                Wait.secondsUntilElementPresent("sign_in.email", 15);
            }
            TextBoxes.typeTextbox("sign_in.email", userData.get("email").toString());
            TextBoxes.typeTextbox("sign_in.password", passWord);
            Clicks.click("sign_in.verify_page");
            if(Elements.elementPresent("sign_in.cancel_btn")){
                Wait.secondsUntilElementPresent("sign_in.close_overlay", 7);
                Clicks.click("sign_in.close_overlay");
            }
            if (Elements.elementPresent("sign_in.promo_alerts_checkbox")) {
                Clicks.selectCheckbox(Elements.element("sign_in.promo_alerts_checkbox"));
            }
            if (Elements.elementPresent("sign_in.security_alerts_checkbox")) {
                Clicks.selectCheckbox(Elements.element("sign_in.security_alerts_checkbox"));
            }
            if (Elements.elementPresent("sign_in.submit_sms_form")) {
                Clicks.click("sign_in.submit_sms_form");
            }
            Wait.secondsUntilElementNotPresent("sign_in.verify_page", 5);
        } else {
            CommonUtils.signInOrCreateAccount();
            UserProfile customer = TestUsers.getCustomer(null);
            String email = customer.getUser().getProfileAddress().getEmail();
            returnService.insertOrderByOrderNumber(orderNum, email);
        }
    }

    /**
     * Looks up an order number and email address on given page
     *
     * @param pageName "OH" or "EasyReturns"
     * @throws Throwable if any exception occurs
     */
    @And("^I lookup with order number and emailaddress in (OH|EasyReturns) page$")
    public void I_lookup_with_order_number_and_emailaddress_in_page(String pageName) throws Throwable {
        log.info("On order history page");
        if (pageName.equals("OH")) {
            if (!onPage("order_status")) {
                Assert.fail("User not redirected to order status page!!");
            }
        } else {
            if (!onPage("easy_returns")) {
                Assert.fail("User not redirected to easy_returns page!!");
            }
        }
        ReturnsPage returnsPage = new ReturnsPage();
        log.info("Look for order by order id and email");
        for (int index = 0; index < maxTries; index++) {
            returnsPage.lookupOrderByEmail(returnOrderDetails);
            if (title().contains("Order Details") || title().contains("Order Status")) {
                break;
            }
        }
    }


    /**
     * Looks up an order number and zip code on given page
     *
     * @param pageName "OH" or "EasyReturns"
     * @throws Throwable if any exception occurs
     */

    @And("^I lookup with order number with zipcode in (OH|EasyReturns) page$")
    public void iLookupWithOrderNumberAndZipcodeInOHPage(String pageName) throws Throwable {
        if (pageName.equals("OH")) {
            if (!onPage("order_status")) {
                Assert.fail("User not redirected to order status page!!");
            }
        } else {
            if (!onPage("easy_returns")) {
                Assert.fail("User not redirected to easy_returns page!!");
            }
        }
        ReturnsPage returnsPage = new ReturnsPage();
        for (int index = 0; index < maxTries; index++) {
            returnsPage.lookupOrderByZip(returnOrderDetails);
            if (title().contains("Order Details") || title().contains("Order Status")) {
                break;
            }
        }
    }

    /**
     * Selects return for a stored product on given page
     *
     * @param pageName "OH" or "EasyReturns"
     * @throws Throwable if any exception occurs
     */
    @And("^I select return items button in \"(OH|OD)\" page$")
    public void I_select_return_items_button_in_page(String pageName) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        switch (pageName) {
            case "OH":
                Clicks.click("home.goto_order_status");
                returnsPage.findOrderInDateRange(returnOrderDetails.getString("order_number"));
                returnsPage.returnOrder(returnOrderDetails.getString("order_number"));
                break;
            case "OD":
                Assert.assertFalse("ERROR - DATA : Order test data is not present in environment!!", Elements.elementPresent("order_status.error_message"));
                Clicks.click("order_details.return_items");
                break;
            default:
                Assert.fail("Invalid page name: " + pageName);
        }
        Wait.untilElementPresent("return_selection.order_number");
        if (!onPage("return_selection")) {
            Assert.fail("User not redirected to return selection page!!");
        }
    }

    /**
     * Select Items in order return selection page and navigate to return submit page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select items and continue to submit page$")
    public void I_select_items_and_continue_to_submit_page() throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.returnOrderDetails = returnOrderDetails;
        returnsPage.selectItemsAndContinueToSubmitPage();
    }

    /**
     * Verifies that the browser is on the order history page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to OH page$")
    public void I_should_be_navigated_to_OH_page() throws Throwable {
        shouldBeOnPage("order_status");
    }

    /**
     * Sets the return status for a given order type in the DB
     *
     * @param returnStatus "intransit", "received", "incomplete", "incomplete_with_items_missing",
     *                     "incomplete_with_items_not_received", or "complete"
     * @param orderType    "submitted", "intransit", or "transit"
     * @throws Throwable if any exception occurs
     */
    @And("^I set the return \"([^\"]*)\" status for \"([^\"]*)\" order in db$")
    public void I_set_the_return_status_for_order_in_db(String returnStatus, String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        ReturnService returnService = new ReturnService();
        CommonUtils.deleteReturnRecord(returnStatus, returnOrderDetails);
        returnService.updateReturnStatus(returnStatus, returnOrderDetails);
        CommonUtils.updateReturnStatus(returnStatus, returnOrderDetails);
    }

    /**
     * Verifies the line item quantity for the specified status
     *
     * @param quantityType   expected quantity
     * @param shipmentStatus shipment status type
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see line item qty as \"([^\"]*)\" for \"([^\"]*)\" shipment$")
    public void I_should_see_line_item_qty_as_for_shipment(String quantityType, String shipmentStatus) throws Throwable {
        if (!onPage("order_details")) {
            Assert.fail("User is not in order details page!!");
        }
        String statusHeader = (macys() ? (shipmentStatus.split("\\|")[0]) : (shipmentStatus.split("\\|")[shipmentStatus.split("\\|").length - 1]));
        ReturnsPage returnsPage = new ReturnsPage();
        Map shipment = new HashMap<>();
        for (Map ship : returnsPage.getOrderDetails())
            if (ship.get("headerStatus").toString().contains(statusHeader)) {
                shipment = ship;
            }
        ReturnService returnService = new ReturnService();
        Map returnDetails = returnService.getReturnInitiatedOrder(returnOrderDetails.getString("order_number"));
        String expectedQuantity = null;
        switch (quantityType) {
            case "return_credited_qty of return_initiated_qty":
                expectedQuantity = quantityType.replace("return_initiated_qty", returnDetails.get("quantity").toString()).replace("return_credited_qty", returnOrderDetails.getString("credited_qty"));
                break;
            case "0 of return_initiated_qty":
                expectedQuantity = quantityType.replace("return_initiated_qty", returnDetails.get("quantity").toString());
                break;
            case "return_credited_qty":
                expectedQuantity = returnOrderDetails.getString("credited_qty");
                break;
            case "return_qty":
                expectedQuantity = returnDetails.get("quantity").toString();
        }
        String actualQuantity;
        if (((List) shipment.get("lineItems")).size() > 1) {
            Map lineItemDetails = new HashMap<>();
            for (Map lineItem : ((List<Map>) shipment.get("lineItems")))
                lineItemDetails = lineItem;
            if (lineItemDetails == null) {
                Assert.fail("line item not found");
            }
            actualQuantity = lineItemDetails.get("itemQty").toString();
        } else {
            actualQuantity = ((List<Map>) shipment.get("lineItems")).get(0).get("itemQty").toString().trim();
        }
        if (!actualQuantity.equals(expectedQuantity)) {
            Assert.fail(actualQuantity + " is not equal to " + expectedQuantity);
        }
    }

    /**
     * Verifies the details of all items with the given status on the order history page
     *
     * @param status status to check
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify order details for \"([^\"]*)\" items in OH page$")
    public void I_verify_order_details_for_items_in_OH_page(String status) throws Throwable {
        ReturnsPage returnObj = new ReturnsPage();
        pausePageHangWatchDog();
        returnObj.findOrderInDateRange(orderNum);
        Map orderHash = returnObj.getOrder(orderNum);
        if (orderHash.get("orderNumber").toString().isEmpty()) {
            Assert.fail("Order number is empty");
        }
        if (orderHash.get("orderDate").toString().isEmpty()) {
            Assert.fail("Order date is empty");
        }
        if (orderHash.get("orderTotal").toString().isEmpty()) {
            Assert.fail("Order total is empty");
        }
        String statusHeader = macys() ? status.split("\\|")[0] : status.split("\\|")[1];
        String shipmentStatus = null;
        if (status.equals("backordered|BACKORDERED")) {
            shipmentStatus = Elements.getText("order_details.order_status_text");
        } else {
            for (int index = 0; index < ((List<Map>) orderHash.get("orderDetails")).size(); index++) {
                if (((((List<Map>) orderHash.get("orderDetails")).get(index).keySet())).iterator().next().toString().replace("\n", " ").contains(statusHeader)) {
                    shipmentStatus = ((((List<Map>) orderHash.get("orderDetails")).get(index).keySet())).iterator().next().toString().replace("\n", " ");
                }
            }
        }
        if (shipmentStatus == null) {
            Assert.fail("ERROR - DATA: No order found in page with order status: " + statusHeader);
        }
        if (!shipmentStatus.contains(statusHeader)) {
            Assert.fail("ERROR - DATA: MST related Data Issue");
        }
        if (!status.equals("backordered|BACKORDERED")) {
            for (Map orderDetails : ((List<Map>) orderHash.get("orderDetails"))) {
                orderDetails.keySet().forEach(orderDetailsKey -> {
                    if (orderDetailsKey.equals(statusHeader)) {
                        for (Map item : (List<Map>) orderDetails.get(statusHeader)) {
                            if (item.get("itemDescription").toString().isEmpty()) {
                                Assert.fail("Item description is empty");
                            }
                            if (item.get("itemAction").toString().isEmpty()) {
                                Assert.fail("Item action is empty");
                            }
                            if (item.get("itemPrice").toString().isEmpty()) {
                                Assert.fail("item price is empty");
                            }
                            if (item.get("itemQty").toString().isEmpty()) {
                                Assert.fail("item quantity is empty");
                            }
                        }
                    }
                });
            }
        } else {
            if (bloomingdales()) {
                if (!Elements.getText("order_details.backordered_text").replace("/n", " ").trim().contains("The item(s) below is on backorder and delivery will be delayed. To continue to wait, you must call 1-800-777-0000. Otherwise, the item(s) will be removed from your order.")) {
                    Assert.fail("backordered_text messaage is not correct!!");
                }
                if (!Elements.getText("order_details.backordered_subtitle").replace("/n", " ").trim().contains("Please note, you do not have to call if:")) {
                    Assert.fail("backordered_subtitle messaage is not correct!!");
                }
                if (!Elements.getText("order_details.backordered_submessage").replace("/n", " ").trim().contains("You already have called. The item(s) was already on backorder when you placed the order. The delivery delay is less than 30 days.")) {
                    Assert.fail("backordered_submessage messaage is not correct!!");
                }
            }
            Elements.getText("order_details.backordered_text").trim().contains("This item is on backorder and the delivery date is delayed. Please contact Customer Service at 1-800-BUY-MACY(289-6229) ???forAssistance???");
        }
        resumePageHangWatchDog();
    }

    /**
     * Verifies the display of the order details page and status of orders
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I verify order details in OD page$")
    public void I_verify_order_details_in_OD_page() throws Throwable {
        List<String> shippingMethod = new ArrayList<>();
        ReturnsPage returnObj = new ReturnsPage();
        if (!onPage("order_details")) {
            returnObj.clickOrderDetailsButton(orderNum);
        }
        List<Map<String, Object>> orderDetails = returnObj.getOrderDetails();
        for (Map itemDetails : orderDetails) {
            if (itemDetails.get("headerStatus").toString().isEmpty()) {
                Assert.fail("header is empty");
            }
            if (itemDetails.get("address") == null) {
                Assert.fail("address is empty");
            }
            if (((Map) itemDetails.get("address")).size() > 0) {
                if (((Map) itemDetails.get("address")).containsKey("shippingMethod")) {
                    shippingMethod.add(((Map) itemDetails.get("address")).get("shippingMethod").toString());
                }
            }
            for (Map itemList : ((List<Map>) itemDetails.get("lineItems"))) {
                if (macys()) {
                    if (itemList.get("itemDescription").toString().isEmpty()) {
                        Assert.fail("Item description is empty");
                    }
                    if (itemList.get("giftBox").toString().isEmpty()) {
                        Assert.fail("gift box is not opted");
                    }
                    if (itemList.get("itemQty").toString().isEmpty()) {
                        Assert.fail("item quantity is empty");
                    }
                    if (itemList.get("itemPrice").toString().isEmpty()) {
                        Assert.fail("item price is empty");
                    }

                } else {
                    if (itemList.get("itemDescription").toString().isEmpty()) {
                        Assert.fail("Item description is empty");
                    }
                    if (itemList.get("giftBox").toString().isEmpty() && !itemList.get("status").toString().contains("Submitted")) {
                        Assert.fail("gift box is not opted");
                    }
                    if (itemList.get("itemQty").toString().isEmpty()) {
                        Assert.fail("item quantity is empty");
                    }
                    if (itemList.get("itemPrice").toString().isEmpty()) {
                        Assert.fail("item price is empty");
                    }
                    if (itemList.get("status").toString().isEmpty()) {
                        Assert.fail("item status is empty");
                    }
                    if (itemList.get("total").toString().isEmpty()) {
                        Assert.fail("item total is empty");
                    }
                }
                if (!itemDetails.get("orderTotalDetails").toString().isEmpty()) {
                    if (((Map) itemDetails.get("orderTotalDetails")).keySet().size() < 0) {
                        Assert.fail("Order total details is empty");
                    }
                }
            }
        }
    }

    /**
     * Navigates to order details page as given user type
     *
     * @param userType "guest" or "signed in"
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to order details page as a \"([^\"]*)\" user$")
    public void I_navigate_to_order_details_page_as_user(String userType) throws Throwable {
        if (prodEnv()) {
            returnOrderDetails = CommonUtils.getProdUser(userType);
        }
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        Wait.secondsUntilElementPresent("home.goto_order_status", 20);
        Clicks.click("home.goto_order_status");
        if (safari()) {
            Wait.secondsUntilElementPresent("order_status.verify_page", 10);
            Wait.forPageReady();
        }
        ReturnsPage returnsPage = new ReturnsPage();
        if (userType.equals("signed") || userType.equals("prod_user_with_orders")) {
            returnsPage.findOrderInDateRange(returnOrderDetails.getString("order_number"));
            returnsPage.selectOrder(returnOrderDetails.getString("order_number"));
        } else {
            if (returnOrderDetails.has("email")) {
                returnsPage.lookupOrderByEmail(returnOrderDetails);
            } else {
                returnsPage.lookupOrderByPhone(returnOrderDetails);
            }
        }
        if (safari()) {
            Wait.secondsUntilElementPresent("order_details.verify_page", 10);
            Wait.forPageReady();
        }
        if (!onPage("order_details")) {
            Assert.fail("User is not navigated to order details page!!");
        }
    }

    /**
     * Verifies that return status is as expected
     *
     * @param returnStatus submitted|in transit|delivered|received|incomplete|complete
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see return status as \"(submitted|in transit|delivered|received|incomplete|complete)\" with updated date$")
    public void I_should_see_return_status_as_with_updated_data(String returnStatus) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        if (returnStatus.equals("delivered")) {
            returnStatus = "received";
        }
        String returnState = (macys() ? ("return : " + returnStatus) : ("return status " + returnStatus));
        List<Map<String, Object>> orderDetails = returnsPage.getOrderDetails();
        Map shipment = new HashMap<>();
        for (Map ship : orderDetails)
            if (ship.get("headerStatus").toString().toLowerCase().contains(returnState)) {
                log.info("ship  =" + ship.get("headerStatus").toString());
                shipment = ship;
            }
        String statusCode = null;
        switch (returnStatus) {
            case "submitted":
                statusCode = "RETURN STATUS Submitted";
                break;
            case "in transit":
                statusCode = "RETURN STATUS Intransit";
                break;
            case "received":
                statusCode = "RETURN STATUS Received";
                break;
            case "incomplete":
                statusCode = "RETURN STATUS Incomplete";
                break;
            case "complete":
                statusCode = "RETURN STATUS Complete";
        }
        ReturnService returnService = new ReturnService();
        String updatedDate;
        if (prodEnv()) {
            DateFormat mcomFormat = new SimpleDateFormat("MM/dd/YYYY");
            DateFormat bcomFormat = new SimpleDateFormat("MMMM dd, YYYY");
            Date today = Calendar.getInstance().getTime();
            if (macys()) {
                updatedDate = mcomFormat.format(today);
            } else {
                updatedDate = bcomFormat.format(today);
            }
            log.info("Expected return initiation date for production = " + updatedDate);
        } else {
            // updatedDate = (returnStatus.equals("complete") ? returnOrderDetails.getString("item_credited_date") : (returnService.getStatusUpdatedDate(statusCode, returnOrderDetails.getString("order_number"))));
            updatedDate = ((returnStatus.equals("complete") || returnStatus.equals("incomplete") || returnStatus.equals("in transit") || returnStatus.equals("received")) ? (CommonUtils.getStatusUpdatedDate(statusCode, returnOrderDetails.getString("order_number"))) : (returnService.getStatusUpdatedDate(statusCode, returnOrderDetails.getString("order_number"))));
            log.info("Expected return initiation date = " + updatedDate);
        }
        if (macys()) {
            if (!shipment.get("headerStatus").toString().contains(updatedDate)) {
                Assert.fail("updated date is not matching in header status!!");
            }
        } else {
            for (Map line : (List<Map>) shipment.get("lineItems"))
                if (!line.get("status").toString().contains(updatedDate)) {
                    Assert.fail("updated date is not matching in status!!");
                }
        }
    }

    public static List<Product> tux_productDetailsODPage() {
        List<Product> productDetails = new ArrayList<>();
        Wait.forPageReady();
        ReturnsPage returnPage = new ReturnsPage();
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_details.order_details_header"));
        Product p = null;
        for (int i = 0; i < orderContainers.size(); i++) {

            if (Elements.findElements("order_details.item_names").get(i).getText().contains("Tuxedo")) {
                p.reservation_id = Long.parseLong(Elements.findElement("return_selection.reservation_number").getText());
                p.quantity = Integer.parseInt(Elements.findElement("return_selection.tux_quantity").getText());

            } else {
                Assert.fail("Tuxedo items are not displayed");
            }

        }

        return productDetails;
    }

    @Then("^I should see contact information section$")
    public void iShouldSeeContactInformationSection() throws Throwable {
        if (macys()) {
            Assert.assertTrue("contact information section not displayed on return selection page!!", Elements.elementPresent("return_selection.email"));
        } else {
            Assert.assertTrue("contact information section not displayed on return selection page!!", Elements.elementPresent("return_selection.email"));
        }
    }

    @Then("^I should see email address fields are pre-filled$")
    public void I_should_see_email_address_fields_are_prefilled() throws Throwable {
        UserProfile customer = TestUsers.getCustomer(null);
        Assert.assertTrue("email address field not visible on return selection page", Elements.elementPresent("return_selection.email"));
        Assert.assertTrue("conform email address field not visible on return selection page", Elements.elementPresent("return_selection.confirm_email"));

        String email = Elements.getElementAttribute("return_selection.email", "value");
        String confirmEmail = Elements.getElementAttribute("return_selection.confirm_email", "value");

        Assert.assertFalse("Email id is  not present", email.isEmpty());
        Assert.assertFalse("conffrm Email id is  not present", confirmEmail.isEmpty());

        Assert.assertEquals("Email id's should match", customer.getUser().getProfileAddress().getEmail(), email);
        Assert.assertEquals("Confirm Email id's should match", customer.getUser().getProfileAddress().getEmail(), confirmEmail);

    }

    @Then("^I verify email address fields are blank$")
    public void I_verify_email_address_fields_are_blank() throws Throwable {

        Assert.assertTrue("email address field not visible on return selection page", Elements.elementPresent("return_selection.email"));
        Assert.assertTrue("conform email address field not visible on return selection page", Elements.elementPresent("return_selection.confirm_email"));

        String email = Elements.getElementAttribute("return_selection.email", "value");
        String confirmEmail = Elements.getElementAttribute("return_selection.confirm_email", "value");

        Assert.assertTrue("Email id is present", email.isEmpty());
        Assert.assertTrue("confirm Email id is  present", confirmEmail.isEmpty());

    }

    @Then("^I should see print mailing label buttons$")
    public void I_should_see_print_mailing_label_buttons() throws Throwable {
        if (macys()) {
            Assert.assertTrue("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_shipping_label_top"));
            Assert.assertTrue("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_shipping_label_bottom"));
        } else {
            Assert.assertTrue("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_mailing_button"));
        }
    }

    @And("^I click print button$")
    public void I_click_print_button() throws Throwable {
        Elements.findElement("return_confirmation.print_shipping_label_top").click();
    }

    @Then("^I should navigate to return submit page$")
    public void I_should_nabvigate_to_return_submit_page() throws Throwable {
        Assert.assertTrue(onPage("return_submit"));
    }

    @Then("^I navigate to return confirmation page$")
    public void I_navigate_to_return_confirmation_page() throws Throwable {
        if (onPage("return_submit")) {
            Elements.findElement("return_submit.submit_return").click();
        }
    }

    @Then("^I should see estimated credit as per the credit section$")
    public void i_should_see_estimated_credit_as_per_the_credit_section() throws Throwable {
        if (macys()) {
            Wait.untilElementPresent("return_confirmation.estimated_credit_header");
            Assert.assertTrue(Elements.getText("return_confirmation.estimated_credit_header").equals(Elements.getText("return_confirmation.estimated_credit_info")));
        } else {
            Wait.untilElementPresent("return_confirmation.estimated_credit_header");
            Assert.assertTrue(Elements.getText("return_confirmation.estimated_credit_header").equals(Elements.getText("return_confirmation.estimated_credit_info")));
        }
    }


    @Then("^I should not see print mailing button$")
    public void I_should_not_see_print_mailing_button() throws Throwable {

        if (macys()) {
            Assert.assertFalse("Print mailing button top should not be visible", Elements.elementPresent("return_confirmation.print_shipping_label_top"));
            Assert.assertFalse("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_shipping_label_bottom"));
        } else {
            Assert.assertFalse("Print mailing button top should not be visible", Elements.elementPresent("return_confirmation.print_mailing_button"));
        }
    }

/*    @Then("^I should see Print Shipping label on page$")
    public void I_should_see_print_shipping_label_on_page() throws Throwable {

    }*/


    @When("^I navigate to easy return using url$")
    public void iNavigateToEasyReturnUsingUrl() throws Throwable {
        Navigate.visit("easy_returns");
        shouldBeOnPage("easy_returns");
        log.info("Navigate to " + url());
    }

    @Then("^I should be directed to customer service answer page$")
    public void iShouldBeDirectedToCustomerServiceAnswerPage() throws Throwable {
        shouldBeOnPage("easy_returns");
        log.info("In easy returns page");
        Navigate.browserBack();
    }

    @Then("^I should see print_customs_button on return confirmation page$")
    public void i_should_see_print_customs_button_on_return_confirmation_page() throws Throwable {
        Assert.assertTrue("ERROR - APP: Print Customs button under header not displaying", Elements.elementPresent("return_confirmation.print_customs_button"));
        log.info("Print Customs Button(s) Displaying Properly");
    }

    @When("^I select print_customs_button on confirmation page$")
    public void i_select_print_customs_button_on_confirmation_page() throws Throwable {
        shouldBeOnPage("return_confirmation");
        Elements.findElement("return_confirmation.print_customs_button").click();
        switchWindow(2);
        Thread.sleep(10000);
    }

    @Then("^I should be navigate to below url:$")
    public void i_should_be_navigate_to_below_url(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        switchWindow(1);
        for (String key : values.keySet()) {
            String expectedUrl = values.get(key);
            ExpectedConditions.urlToBe(expectedUrl);
        }
        onPage("usps");
        switchWindowClose();
    }

    @Then("^I should see the below mailing label instructions under confirmation page header$")
    public void i_should_see_the_below_mailing_label_instruction_under_confirmation_page_header(DataTable table) {
        Map<String, String> values = table.asMap(String.class, String.class);
        if (macys()) {
            String expectedMsg = values.get("MCOM");
            String actualMsg = Elements.findElement("return_confirmation.confirmation_instruction").getText();
            expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
            actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
        } else {
            String expectedMsg = values.get("BCOM");
            String actualMsg = Elements.findElement("return_confirmation.confirmation_instruction").getText();
            expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
            expectedMsg = expectedMsg.replace("<email>", returnOrderDetails.get("email").toString());
            actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));

        }
    }

    @Then("^I should see reference number one as reservation number on confirmation page$")
    public void i_should_see_reference_number_one_as_reservation_number_on_confirmation_page() {
        Wait.secondsUntilElementPresent("return_confirmation.crl_number", 30);
        String reservationNumber = Elements.findElement("return_confirmation.reservation_number").getText();
        String refrenceNum = Elements.findElement("return_confirmation.reference_number_one").getText();
        Assert.assertTrue("ERROR - APP:- reference number " + refrenceNum.split(":")[1].trim() + " is not equal to reservation number " + reservationNumber.split(":")[1].trim(), refrenceNum.split(":")[1].trim().equals(reservationNumber.split(":")[1].trim()));
        log.debug("Reference Number is Equal to Reservation Number");
    }

    @And("^I should see reference number two contains refund method$")
    public void i_should_see_reference_number_two_contains_refund_method() {
        String refrenceNum = Elements.findElement("return_confirmation.reference_number_two").getText();
        Assert.assertTrue("ERROR - APP:- Refund mehtod displayed like " + refrenceNum.split(":")[1].trim() + " which is not expected ", refrenceNum.split(":")[1].trim().matches("^RETURNS-[B,S]"));
        log.debug("Reference Number 2 contains Refund Method");
    }

    @Then("^I should see the below confirmation_instruction at bottom of confirmation page$")
    public void i_should_see_the_below_confirmation_instruction_at_bottom_of_confirmation_page(DataTable table) {
        Map<String, String> values = table.asMap(String.class, String.class);
        if (macys()) {
            String expectedMsg = values.get("MCOM");
            String actualMsg = Elements.findElement("return_confirmation.mailing_label_instructions").getText();
            expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
            actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
        } else {
            String expectedMsg = values.get("BCOM");
            String actualMsg = Elements.findElement("return_confirmation.mailing_label_instructions").getText();
            expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
            actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
        }
    }

    @And("^I select print mailing label on order details page$")
    public void i_select_print_mailing_label_on_order_details_page() throws Throwable {
        shouldBeOnPage("order_details");
        Wait.secondsUntilElementPresent("order_details.print_mailing_label", 20);
        CommonUtils.switchToPrintShippingLabel();
    }

    @Then("^I should be navigate to below url from order confirmation page:$")
    public void i_should_be_navigate_to_below_url_from_order_confirmation_page(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        for (String key : values.keySet()) {
            String expectedUrl = values.get(key);
            ExpectedConditions.urlToBe(expectedUrl);
        }
        onPage("usps");
        switchWindowClose();
    }

    @Then("^I should see the below mailing_label_instructions under confirmation page header$")
    public void iShouldSeeTheBelowMailing_label_instructionsUnderConfirmationPageHeader(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        if (macys()) {
            String expectedMsg = values.get("MCOM");
            String actualMsg = Elements.findElement("return_confirmation.confirmation_instruction").getText();
            expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
            actualMsg = actualMsg.replaceAll("\n+", " ");
            actualMsg = actualMsg.replaceAll("\\s+", " ").trim();
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
        } else {
            String expectedMsg = values.get("BCOM");
            String actualMsg = Elements.findElement("return_confirmation.mailing_label_instructions").getText();
            expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
            actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
        }
    }

    @Then("^I should not see any print_customs_button on confirmation page$")
    public void iShouldNotSeeAnyPrint_customs_buttonOnConfirmationPage() throws Throwable {
        Assert.assertFalse("print customs button is present at top on returns confirmation page", Elements.elementPresent("return_confirmation.print_customs_button"));
        Assert.assertFalse("print customs button is present at bottom on returns confirmation page", Elements.elementPresent("return_confirmation.print_customs_button_bottom"));
    }

    @Then("^I should see return address as mentioned below$")
    public void iShouldSeeReturnAddressAsMentionedBelow(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        if (macys()) {
            String actualAdderss = Elements.findElement("order_details.shipping_address").getText();
            log.debug("address on order details page is " + actualAdderss);
            String expectedMsg = values.get("MCOM");
            log.debug("expected address is " + expectedMsg);
            Assert.assertTrue(actualAdderss + " does not contain " + expectedMsg, StringUtils.containsIgnoreCase(actualAdderss, expectedMsg));
        } else {
            String actualAdderss = Elements.findElement("order_details.shipping_address").getText();
            log.debug("address on order details page is " + actualAdderss);
            String expectedMsg = values.get("BCOM");
            log.debug("expected address is " + expectedMsg);
            Assert.assertTrue(actualAdderss + " does not contain " + expectedMsg, StringUtils.containsIgnoreCase(actualAdderss, expectedMsg));

        }

    }

    @Then("^I should not see print mailing button for intransit status$")
    public void iShouldNotSeePrintMailingButtonForIntransitStatus() throws Throwable {
        Assert.assertFalse("print mailing label button is present for intransit status on order details page", Elements.elementPresent("order_details.print_mailing_label_for_intransit_status"));
    }

    @Then("^I see return order status as \"(submitted|in transit|delivered|received|incomplete|complete)\" on page$")
    public void iSeeReturnOrderStatusAsOnPage(String returnStatus) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        if (returnStatus.equals("delivered")) {
            returnStatus = "received";
        }
        // String returnState = (macys() ? ("return : " + returnStatus) : ("return status " + returnStatus));
        List<Map<String, Object>> orderDetails = returnsPage.getOrderDetails();
        log.info("Shipping status is " + orderDetails.get(orderDetails.size() - 1).get("headerStatus").toString());
        if (macys()) {
            if (StringUtils.equalsIgnoreCase(returnStatus, "submitted")) {
                Assert.assertTrue("return status is not submitted on order details page for new return initiated", StringUtils.containsIgnoreCase(orderDetails.get(orderDetails.size() - 1).get("headerStatus").toString(), "return : submitted"));
            }
        }

    }

    @And("^I should see Print Shipping label on page for submitted status$")
    public void iShouldSeePrintShippingLabelOnPageForSubmittedStatus() throws Throwable {
        Assert.assertTrue("print mailing label button is absent for submitted status on order details page", Elements.elementPresent("order_details.print_mailing_label_for_submitted_status"));
    }

    @And("^I should see item_description on the page for returns items$")
    public void iShouldSeeItem_descriptionOnThePageForReturnsItems() throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map<String, Object>> orderDetails = returnsPage.getOrderDetails();
        Map shipment = new HashMap<>();
        for (Map ship : orderDetails) {
            String itemDescription = ship.get("lineItems").toString().split("itemDescription")[1].replace("=", "").trim();
            log.info("Line item detail is " + itemDescription);
            Assert.assertNotNull("line item is null", itemDescription);
        }
    }

    @And("^I should not see write_review button under return section$")
    public void iShouldNotSeeWrite_reviewButtonUnderReturnSection() throws Throwable {
        Assert.assertFalse("write a review button is present for returned line item on order details page", Elements.elementPresent("order_details.write_a_reivew_for_returned_line_item"));

    }

    @Then("^I should see print_customs_button on return confirmation page on clicking print mailing label on order details page$")
    public void iShouldSeePrint_customs_buttonOnReturnConfirmationPageOnClickingPrintMailingLabelOnOrderDetailsPage() throws Throwable {
        if (bloomingdales()) {
            CommonUtils.switchToPrintShippingLabel();
        }
        Assert.assertTrue("ERROR - APP: Print Customs button under header not displaying", Elements.elementPresent("return_confirmation.print_customs_button"));
        log.info("Print Customs Button(s) Displaying Properly");
    }

    @When("^I select print_customs_button on confirmation page on clicking print mailing label on order details page$")
    public void iSelectPrint_customs_buttonOnConfirmationPageOnClickingPrintMailingLabelOnOrderDetailsPage() throws Throwable {
        if (bloomingdales()) {
            CommonUtils.switchToPrintShippingLabel();
        }
        Elements.findElement("return_confirmation.print_customs_button").click();
        switchWindow(2);
        Thread.sleep(10000);
    }

    @Then("^I should see the below mailing_label_instructions under confirmation page header on clicking print mailing label on order details page$")
    public void iShouldSeeTheBelowMailing_label_instructionsUnderConfirmationPageHeaderOnClickingPrintMailingLabelOnOrderDetailsPage(DataTable table) throws Throwable {
        if (bloomingdales()) {
            CommonUtils.switchToPrintShippingLabel();
        }
        Map<String, String> values = table.asMap(String.class, String.class);
        String expectedMsg = values.get("BCOM");
        String actualMsg = Elements.findElement("return_confirmation.confirmation_instruction").getText();
        expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
        expectedMsg = expectedMsg.replace("<email>", returnOrderDetails.get("email").toString());
        actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
        Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
    }

    @Then("^I should see reference number one as reservation number on confirmation page on clicking print mailing label on order details page$")
    public void iShouldSeeReferenceNumberOneAsReservationNumberOnConfirmationPageOnClickingPrintMailingLabelOnOrderDetailsPage() throws Throwable {
        if (bloomingdales()) {
            CommonUtils.switchToPrintShippingLabel();
        }
        Wait.secondsUntilElementPresent("return_confirmation.crl_number", 30);
        String reservationNumber = Elements.findElement("return_confirmation.reservation_number").getText();
        String refrenceNum = Elements.findElement("return_confirmation.reference_number_one").getText();
        Assert.assertTrue("ERROR - APP:- reference number " + refrenceNum.split(":")[1].trim() + " is not equal to reservation number " + reservationNumber.split(":")[1].trim(), refrenceNum.split(":")[1].trim().equals(reservationNumber.split(":")[1].trim()));
        log.debug("Reference Number is Equal to Reservation Number");
    }

    @And("^I should see reference number two contains refund method on clicking print mailing label on order details page$")
    public void iShouldSeeReferenceNumberTwoContainsRefundMethodOnClickingPrintMailingLabelOnOrderDetailsPage() throws Throwable {
        if (bloomingdales()) {
            CommonUtils.switchToPrintShippingLabel();
        }
        String refrenceNum = Elements.findElement("return_confirmation.reference_number_two").getText();
        Assert.assertTrue("ERROR - APP:- Refund mehtod displayed like " + refrenceNum.split(":")[1].trim() + " which is not expected ", refrenceNum.split(":")[1].trim().matches("^RETURNS-[B,S]"));
        log.debug("Reference Number 2 contains Refund Method");
    }

    @Then("^I should see the below confirmation_instruction at bottom of confirmation page on clicking print mailing label on order details page$")
    public void iShouldSeeTheBelowConfirmation_instructionAtBottomOfConfirmationPageOnClickingPrintMailingLabelOnOrderDetailsPage(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        CommonUtils.switchToPrintShippingLabel();
        String expectedMsg = values.get("BCOM");
        String actualMsg = Elements.findElement("return_confirmation.mailing_label_instructions").getText();
        expectedMsg = expectedMsg.trim().replaceAll("\\s+", " ");
        expectedMsg = expectedMsg.replace("<email>", returnOrderDetails.get("email").toString());
        actualMsg = actualMsg.trim().replaceAll("\\s+", " ");
        Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.equals(actualMsg));
    }

    @Then("^I should \"([^\"]*)\" Track My Return button with details for \"([^\"]*)\" order$")
    public void iShouldTrackMyReturnButtonWithDetailsForOrder(String visibility, String order_status) throws Throwable {
        if ((StringUtils.equalsIgnoreCase(visibility, "see")) && (StringUtils.equalsIgnoreCase(order_status, "in transit"))) {
            if (!Elements.elementPresent("order_details.track_return_in_transit")) {
                Assert.fail("track return button is not present for intransit order!!");
            }

        } else if ((StringUtils.equalsIgnoreCase(visibility, "see")) && (StringUtils.equalsIgnoreCase(order_status, "received"))) {
            if (!Elements.elementPresent("order_details.track_return_received")) {
                Assert.fail("track return button is not present for received order!!");
            }
        } else if ((StringUtils.equalsIgnoreCase(visibility, "not see")) && (StringUtils.equalsIgnoreCase(order_status, "received"))) {
            if (Elements.elementPresent("order_details.track_return_received")) {
                Assert.fail("track return button is present for received order!!");
            }
        } else if ((StringUtils.equalsIgnoreCase(visibility, "not see")) && (StringUtils.equalsIgnoreCase(order_status, "completed"))) {
            if (Elements.elementPresent("order_details.track_return_completed")) {
                Assert.fail("track return button is present for completed order!!");
            }
        } else if ((StringUtils.equalsIgnoreCase(visibility, "see")) && (StringUtils.equalsIgnoreCase(order_status, "incomplete"))) {
            if (!Elements.elementPresent("order_details.track_return_incomplete")) {
                Assert.fail("track return button is not present for incomplete order!!");
            }
        } else if ((StringUtils.equalsIgnoreCase(visibility, "not see")) && (StringUtils.equalsIgnoreCase(order_status, "incomplete"))) {
            if (Elements.elementPresent("order_details.track_return_incomplete")) {
                Assert.fail("track return button is  present for incomplete order!!");
            }
        }
    }

    @When("^I set Track My Return after (\\d+) days of return creation for \"([^\"]*)\" order$")
    public void iSetTrackMyReturnAfterDaysOfReturnCreationForOrder(int days, String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        CommonUtils.updateTrackingDate(days, returnOrderDetails);
    }

    @When("^I navigate with return \"(.*?)\" url$")
    public void I_navigate_with_return_url(String CurrentUrl) throws Throwable {
        CurrentUrl = Home.url();
        log.info("Current Url is " + CurrentUrl);
        Navigate.visit("" + CurrentUrl + "service/return/confirmation");
        log.info("navigated to confirmation page");
    }

    @When("^I lookup with \"([^\"]*)\" order with gift return$")
    public void iLookupWithOrderWithGiftReturn(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
        ReturnsPage returnsPage = new ReturnsPage();
        if (onPage("order_status")) {
            returnsPage.lookupOrderByZip(returnOrderDetails);
            Wait.secondsUntilElementPresent("order_details.order_details_header", 30);
        }
        else if (onPage("easy_returns")) {
            returnsPage.lookupOrderByZipEasyReturnsPage(returnOrderDetails);
            Wait.secondsUntilElementPresent("order_details.order_details_header", 30);
        }

    }

    @Then("^I navigate to order history page from order details page$")
    public void iNavigateToOrderHistoryPageFromOrderDetailsPage() throws Throwable {
        Wait.forPageReady();
        shouldBeOnPage("order_details");
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        Wait.secondsUntilElementPresent("home.goto_order_status", 20);
        Clicks.click("home.goto_order_status");
        shouldBeOnPage("order_status");
    }

    @When("^I lookup with valid \"([^\"]*)\" and incorrect \"([^\"]*)\" in OH page$")
    public void iLookupWithValidAndIncorrectInOHPage(String orderNumber, String email) throws Throwable {
        log.info("Look for order by order id and email");
        Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
        log.info("Entering order number for order lookup " + orderNumber.trim());
        TextBoxes.typeTextbox(Elements.element("order_status.order_number"), orderNumber.trim());
        log.info("Entering email for order lookup " + email.trim());
        TextBoxes.typeTextbox(Elements.element("order_status.email"), email.trim());
        Clicks.click(Elements.element("order_status.view_order_details"));
    }

    @Then("^I verify \"([^\"]*)\" upto \"([^\"]*)\" attempts for \"([^\"]*)\" and incorrect \"([^\"]*)\"$")
    public void iVerifyUptoAttemptsForAndIncorrect(String expectedMsg, String count, String order, String email) throws Throwable {
        Wait.secondsUntilElementPresent("order_status.error_message", 30);
        if (!Elements.elementPresent("order_status.error_message")) {
            Assert.fail("Error message is not present while searching order with invalid email!!");
        } else {
            String actualMsg = Elements.findElement("order_status.error_message").getText();
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.trim().equals(actualMsg.trim()));
        }
        int counter = Integer.valueOf(count) - 1;
        for (int index = 0; index < counter; index++) {
            iNavigateToOrderHistoryPageFromOrderDetailsPage();
            iLookupWithValidAndIncorrectInOHPage(order, email);
            Wait.secondsUntilElementPresent("order_status.error_message", 30);
            if (!Elements.elementPresent("order_status.error_message")) {
                Assert.fail("Error message is not present while searching order with invalid email!!");
            } else {
                String actualMsg = Elements.findElement("order_status.error_message").getText();
                Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.trim().equals(actualMsg.trim()));
            }
        }
    }

    @Then("^I verify return button as per shipment details in the order \"([^\"]*)\" page$")
    public void iVerifyReturnButtonAsPerShipmentDetailsInTheOrderPage(String page) throws Throwable {
        if (StringUtils.equalsIgnoreCase(page, "details")) {
            Wait.secondsUntilElementPresent("order_details.verify_page", 30);
            if (Elements.elementPresent("order_details.check_shipment")) {
                log.debug("Returns button should be present");
                if (!Elements.elementPresent("order_details.return_items")) {
                    Assert.fail("Return button is not  present for when it should be!!");
                }
            } else {
                if (Elements.elementPresent("order_details.return_items")) {
                    Assert.fail("Return button is present for when it should not be!!");
                }
            }

        } else if (StringUtils.equalsIgnoreCase(page, "history")) {
            ReturnsPage returnsPage = new ReturnsPage();
            returnsPage.findOrderInDateRange(returnOrderDetails.getString("order_number"));
            if (Elements.elementPresent("order_status.check_shipment")) {
                log.debug("Returns button should be present");
                if (!Elements.elementPresent("order_status.return_items")) {
                    Assert.fail("Return button is not  present for when it should be!!");
                }
            } else {
                if (Elements.elementPresent("order_status.return_items")) {
                    Assert.fail("Return button is present for when it should not be!!");
                }
            }
        }
    }

    @And("^I navigate to order history page$")
    public void iNavigateToOrderHistoryPage() throws Throwable {
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        Wait.secondsUntilElementPresent("home.goto_order_status", 20);
        log.info("Goto order status page");
        Clicks.click(Elements.element("home.goto_order_status"));
        Wait.forPageReady();
    }

    @Then("^I should see print_mailing_label buttons$")
    public void iShouldSeePrint_mailing_labelButtons() throws Throwable {
        if (macys()) {
            if (!Elements.elementPresent("return_confirmation.print_shipping_label_top")) {
                Assert.fail("Print mailing label is not present on return confirmation page!!");
            }
        } else {
            if (!Elements.elementPresent("return_confirmation.print_mailing_button")) {
                Assert.fail("Print mailing label is not present on return confirmation page!!");
            }
        }
    }

    @And("^I get crl number and barcode$")
    public void iGetCrlNumberAndBarcode() throws Throwable {
        if (macys()) {
            if (!Elements.elementPresent("order_details.bar_code_ctl_num")) {
                Assert.fail("crl number not displayed on order detail page page!!");
            }

            cntrlNumber = Elements.getText("order_details.bar_code_ctl_num").split("CRL")[1].trim();
            log.info("control number=" + cntrlNumber);
            if (!Elements.elementPresent("order_details.barcode")) {
                Assert.fail("barcode not displayed on order details page!!");
            }
            barCodeSource = Elements.getElementAttribute("order_details.barcode", "src");
            log.info("bar code source=" + barCodeSource);
        } else {
            if (!Elements.elementPresent("order_details.bar_code_number")) {
                Assert.fail("crl number not displayed on order detail page page!!");
            }
            cntrlNumber = Elements.getText("order_details.bar_code_number").trim();
            log.info("control number=" + cntrlNumber);
            if (!Elements.elementPresent("order_details.barcode")) {
                Assert.fail("barcode not displayed on order details page!!");
            }
            barCodeSource = Elements.getElementAttribute("order_details.barcode", "src");
            log.info("bar code source=" + barCodeSource);
        }
    }

    @When("^I navigate to confirmation page$")
    public void iNavigateToConfirmationPage() throws Throwable {
        log.info("Click on returns submit button");
        Clicks.click("return_submit.submit_return");
        Wait.secondsUntilElementPresent("return_confirmation.crl_number", 30);
        Wait.forPageReady();
        if (!onPage("return_confirmation")) {
            Assert.fail("User is not navigated to return confirmation page!!");
        }
    }

    @Then("^I should see barcode in the header which is same as the instore return barcode shown on the Order Details Page$")
    public void iShouldSeeBarcodeInTheHeaderWhichIsSameAsTheInstoreReturnBarcodeShownOnTheOrderDetailsPage() throws Throwable {
        if (!Elements.elementPresent("return_confirmation.barcode")) {
            Assert.fail("barcode not displayed on returns confirmation page!!");
        }
        String barCodeSrc = Elements.getElementAttribute("return_confirmation.barcode", "src").replace("}", "").replace("{", "");
        log.info("bar code source on returns confirmation page =" + barCodeSrc);
        Assert.assertTrue("barcode in header on order detail page is not same as that on returns confirmation page", StringUtils.equalsIgnoreCase(barCodeSrc, barCodeSource));
    }

    @And("^I should see crl number as fedfil reservation number$")
    public void iShouldSeeCrlNumberAsFedfilReservationNumber() throws Throwable {
        if (!Elements.elementPresent("return_confirmation.crl_number")) {
            Assert.fail("crl number not displayed on return confirmation page!!");
        }
        String cntrlNum = Elements.getText("return_confirmation.crl_number").split(":")[1].trim();
        ;
        log.info("control number on retuurns confirmation page =" + cntrlNum);
        Assert.assertTrue("control number on order detail page is not same as that on order confirmation page", StringUtils.equalsIgnoreCase(cntrlNumber, cntrlNum));
    }

    @Then("^I should see same email address on submit page which user has provided in the return selection page$")
    public void iShouldSeeSameEmailAddressOnSubmitPageWhichUserHasProvidedInTheReturnSelectionPage() throws Throwable {
        if (macys()) {
            Assert.assertTrue("email address on return submit page is not same as that of return selection page", StringUtils.equalsIgnoreCase(returnOrderDetails.get("email").toString().trim(), Elements.getText("return_submit.email_id")));
        } else {
            Assert.assertTrue("email address on return submit page is not same as that of return selection page", StringUtils.equalsIgnoreCase(returnOrderDetails.get("email").toString().trim(), Elements.getText("return_submit.order_email").replace("EMAIL ADDRESS", "").trim()));
        }
    }

    @Then("^I should see same email address on confirmation page which user has provided in the return selection page$")
    public void iShouldSeeSameEmailAddressOnConfirmationPageWhichUserHasProvidedInTheReturnSelectionPage() throws Throwable {
        String expectedEmail = returnOrderDetails.get("email").toString();
        Assert.assertTrue("email address on return confirmation page is not same as that of return selection page", StringUtils.equalsIgnoreCase(expectedEmail, Elements.getText("return_confirmation.return_conf_email").replace("EMAIL ADDRESS", "").trim()));
        Assert.assertTrue("email address on return confirmation page is not same as that of return selection page in return confirmation instruction", StringUtils.containsIgnoreCase(Elements.getText("return_confirmation.confirmation_instruction"), expectedEmail));
    }

    @Then("^I verify return credit section$")
    public void iVerifyReturnCreditSection() throws Throwable {
        if (macys()) {
            Assert.assertTrue("estimated credit is not present on return confirmation page", StringUtils.containsIgnoreCase(Elements.getText("return_confirmation.info_credit_estimated").trim(), "Estimated Credit Remaining:"));
            Assert.assertNotNull("estimated credit is null on returns confirmation page", Elements.getText("return_confirmation.esitmated_credit_amount_header"));
            float creditAmountHeader = Float.valueOf(Elements.getText("return_confirmation.esitmated_credit_amount_header").split("\\$")[1].trim());
            log.info("credit amount in header= " + creditAmountHeader);
            float merchandiseAmount = Float.valueOf(Elements.getText("return_confirmation.merchandise_amount").split("\\$")[1].trim());
            log.info("merchandise amount = " + merchandiseAmount);
            float taxAmount = Float.valueOf(Elements.getText("return_confirmation.tax_amount").split("\\$")[1].trim());
            log.info("tax amount = " + taxAmount);
            String returnDelivery = Elements.getText("return_confirmation.return_delivery_amount");
            float refundAmnt = 0;
            if (StringUtils.equalsIgnoreCase(returnDelivery.trim(), "FREE")) {
                refundAmnt = merchandiseAmount + taxAmount;
            } else {
                refundAmnt = merchandiseAmount + taxAmount - (Float.valueOf(returnDelivery.split("\\$")[1].trim()));
            }
            float creditAmount = Float.valueOf(Elements.getText("return_confirmation.estimated_refund_amnt").split("\\$")[1].trim());
            Assert.assertEquals("refund amount is not correct.Expected reund amount is = " + refundAmnt + " .Actual refund amout is = " + creditAmount, Double.doubleToLongBits(refundAmnt), Double.doubleToLongBits(creditAmount));
            Assert.assertEquals("refund amount does not match in header and bottom.Rfeund amount at top is = " + creditAmountHeader + " .Refund amout at bottom is = " + creditAmount, Double.doubleToLongBits(creditAmountHeader), Double.doubleToLongBits(creditAmount));
        } else {
            Assert.assertTrue("estimated credit is not present on return confirmation page", StringUtils.containsIgnoreCase(Elements.getText("return_confirmation.esitmated_credit_amount").trim(), "Expected Refund Amount"));
            Assert.assertNotNull("estimated credit is null on returns confirmation page", Elements.getText("return_confirmation.esitmated_credit_amount_header"));
            float creditAmountHeader = Float.valueOf(Elements.getText("return_confirmation.esitmated_credit_amount_header").split("\\$")[1].trim());
            log.info("credit amount in header= " + creditAmountHeader);
            float merchandiseAmount = Float.valueOf(Elements.getText("return_confirmation.merchandise_amount").split("\\$")[1].trim());
            log.info("merchandise amount = " + merchandiseAmount);
            float taxAmount = Float.valueOf(Elements.getText("return_confirmation.tax_amount").split("\\$")[1].trim());
            log.info("tax amount = " + taxAmount);
            String returnDelivery = Elements.getText("return_confirmation.return_delivery_amount");
            float refundAmnt = 0;
            if (StringUtils.equalsIgnoreCase(returnDelivery.trim(), "FREE")) {
                refundAmnt = merchandiseAmount + taxAmount;
            } else {
                refundAmnt = merchandiseAmount + taxAmount - (Float.valueOf(returnDelivery.split("\\$")[1].trim()));
            }
            float creditAmount = Float.valueOf(Elements.getText("return_confirmation.estimated_refund_amnt").split("\\$")[1].trim());
            Assert.assertEquals("refund amount is not correct.Expected reund amount is = " + refundAmnt + " .Actual refund amout is = " + creditAmount, Double.doubleToLongBits(refundAmnt), Double.doubleToLongBits(creditAmount));
            Assert.assertEquals("refund amount does not match in header and bottom.Rfeund amount at top is = " + creditAmountHeader + " .Refund amout at bottom is = " + creditAmount, Double.doubleToLongBits(creditAmountHeader), Double.doubleToLongBits(creditAmount));


        }
    }

    @Then("^I verify total price for \"([^\"]*)\" shipment$")
    public void iVerifyTotalPriceForShipment(String returnStatus) throws Throwable {
        int qty = 0;
        float price = 0, totalPrice = 0;
        switch (returnStatus.toLowerCase().trim()) {
            case "return status received":
                if (!Elements.elementPresent("order_details.received_shipment")) {
                    Assert.fail("There is no shipment with return status received on order details page!!");
                }
                qty = Integer.valueOf(Elements.getText("order_details.received_shipment_qty").split("of")[0].trim());
                price = Float.valueOf(Elements.getText("order_details.received_shipment_price").split("\\$")[1].trim());
                totalPrice = Float.valueOf(Elements.getText("order_details.received_shipment_total_price").split("\\$")[1].trim());
                break;
            case "return status submitted":
                if (!Elements.elementPresent("order_details.submitted_shipment")) {
                    Assert.fail("There is no shipment with return status submitted on order details page!!");
                }
                qty = Integer.valueOf(Elements.getText("order_details.submitted_shipment_qty").split("of")[0].trim());
                price = Float.valueOf(Elements.getText("order_details.submitted_shipment_price").split("\\$")[1].trim());
                totalPrice = Float.valueOf(Elements.getText("order_details.submitted_shipment_total_price").split("\\$")[1].trim());
                break;
            case "return status incomplete":
                if (!Elements.elementPresent("order_details.incomplete_shipment")) {
                    Assert.fail("There is no shipment with return status incomplete on order details page!!");
                }
                qty = Integer.valueOf(Elements.getText("order_details.incomplete_shipment_qty").split("of")[0].trim());
                price = Float.valueOf(Elements.getText("order_details.incomplete_shipment_price").split("\\$")[1].trim());
                totalPrice = Float.valueOf(Elements.getText("order_details.incomplete_shipment_total_price").split("\\$")[1].trim());
                break;

            case "return status in transit":
                if (!Elements.elementPresent("order_details.intransit_shipment")) {
                    Assert.fail("There is no shipment with return status in transit on order details page!!");
                }
                qty = Integer.valueOf(Elements.getText("order_details.intransit_shipment_qty").split("of")[0].trim());
                price = Float.valueOf(Elements.getText("order_details.intransit_shipment_price").split("\\$")[1].trim());
                totalPrice = Float.valueOf(Elements.getText("order_details.intransit_shipment_total_price").split("\\$")[1].trim());
                break;
            case "return status completed":
                if (!Elements.elementPresent("order_details.completed_shipment")) {
                    Assert.fail("There is no shipment with return status completed on order details page!!");
                }
                qty = Integer.valueOf(Elements.getText("order_details.completed_shipment_qty").split("of")[0].trim());
                price = Float.valueOf(Elements.getText("order_details.completed_shipment_price").split("\\$")[1].trim());
                totalPrice = Float.valueOf(Elements.getText("order_details.completed_shipment_total_price").split("\\$")[1].trim());
                break;

        }
        log.info("qty=" + qty);
        log.info("price=" + price);
        log.info("total price=" + totalPrice);
        if ((price * qty) != totalPrice) {
            Assert.fail("Total price is not correct on order details page for received shipment!");
            log.info("Expected total price=" + totalPrice);
            log.info("Actual total price=" + (price * qty));
        }
    }

    @Then("^I should see line item text as \"([^\"]*)\" for \"([^\"]*)\" shipment$")
    public void iShouldSeeLineItemTextAsForShipment(String expectedStatus, String status) throws Throwable {
        switch (status.toLowerCase().trim()) {
            case "return status received":
                expectedStatus = expectedStatus.replace("<line_item_date>", CommonUtils.getLineItemDetails("received", returnOrderDetails));
                log.info("expectedStatus=" + expectedStatus);
                String actualStatus = Elements.getText("order_details.received_shipment_status").replace("\n", " ");
                log.info("Actual Status=" + actualStatus);
                Assert.assertTrue("shipment status is not correct on order details page", StringUtils.equals(expectedStatus, actualStatus));
                break;

            case "return status submitted":
                expectedStatus = expectedStatus.replace("<line_item_date>", CommonUtils.getLineItemDetails("submitted", returnOrderDetails));
                log.info("expectedStatus=" + expectedStatus);
                actualStatus = Elements.getText("order_details.submitted_shipment_status").replace("\n", " ");
                log.info("Actual Status=" + actualStatus);
                Assert.assertTrue("shipment status is not correct on order details page", StringUtils.equals(expectedStatus, actualStatus));
                break;
            case "return status incomplete":
                expectedStatus = expectedStatus.replace("<line_item_date>", CommonUtils.getLineItemDetails("incomplete", returnOrderDetails));
                log.info("expectedStatus=" + expectedStatus);
                actualStatus = Elements.getText("order_details.incomplete_shipment_status").replace("\n", " ");
                log.info("Actual Status=" + actualStatus);
                Assert.assertTrue("shipment status is not correct on order details page", StringUtils.equals(expectedStatus, actualStatus));
                break;

        }
    }

    @Then("^I fetch order details from OD page$")
    public void iFetchOrderDetailsFromODPage() throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();

        orderNum = returnOrderDetails.get("order_number").toString();
        log.info("OrderNum:" + orderNum);
        orderHeaderDetails_fromODPage = returnsPage.getOrder(orderNum);
        Iterator iterator = orderHeaderDetails_fromODPage.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            Object value = orderHeaderDetails_fromODPage.get(key);
            log.info(key + " " + value);
        }

        log.info("Got orderDetails map");
        if (orderHeaderDetails_fromODPage.get("orderNumber").toString().isEmpty()) {
            Assert.fail("Order number is empty");
        }
        if (orderHeaderDetails_fromODPage.get("orderDate").toString().isEmpty()) {
            Assert.fail("Order date is empty");
        }
        if (orderHeaderDetails_fromODPage.get("orderTotal").toString().isEmpty()) {
            Assert.fail("Order total is empty");
        }

    }

    @Then("^I should navigate to return selection page$")
    public void IShouldNavigateToReturnSelectionPage() throws Throwable {
        Assert.assertTrue(onPage("return_selection"));
    }

    @And("^I verify order header in return selection page$")
    public void I_verify_order_header_in_return_selection_page() throws Throwable {
        ReturnsPage returnPage = new ReturnsPage();
        Map<String, String> orderHeader = returnPage.getOrderHeader();

        log.info("ORDER HEADER DETAILS FROM RETURN SELECTION");
        Iterator iterator = orderHeader.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            Object value = orderHeader.get(key);
            log.info(key + " " + value);
        }

        log.info("orderHeaderDetails_fromODPage.get(\"orderNumber\")" + orderHeaderDetails_fromODPage.get("orderNumber"));
        log.info("orderHeaderDetails_fromODPage.get(\"orderDate\")" + orderHeaderDetails_fromODPage.get("orderDate"));

        Assert.assertTrue(orderHeader.get("orderNumber").toString().trim().equals(orderHeaderDetails_fromODPage.get("orderNumber")));
        Assert.assertTrue(orderHeader.get("orderDate").toString().trim().equals(orderHeaderDetails_fromODPage.get("orderDate")));
        Assert.assertTrue(orderHeader.get("orderTotal").toString().equals(orderHeaderDetails_fromODPage.get("orderTotal")));
    }

    @And("^I should see below in the btag section$")
    public void iShouldSeeTypeTextInTheBtagSection(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        for (String value : values.keySet()) {
            if (value.equals("heading_btag_text")) {
                Assert.assertTrue(Elements.findElement(Elements.element("return_selection.btag_header_text")).getText().equals(values.get(value)));
            } else if (value.equals("instruction_btag_text")) {
                Assert.assertTrue(Elements.findElement(Elements.element("return_selection.btag_instruction_text")).getText().equals(values.get(value)));
            }
        }
    }

    @When("^I select return items button$")
    public void iSelectReturnItemsButton() throws Throwable {
        orderNum = returnOrderDetails.get("order_number").toString();
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.returnOrder(orderNum);
    }

    @And("^I should not see model on selecting close$")
    public void iShouldNotSeeModelOnSelectingClose() throws Throwable {
        Elements.findElement(Elements.element("return_selection.btag_model_close")).click();
        Wait.forPageReady();
        Assert.assertFalse(Elements.findElement(Elements.element("return_selection.btag_model")).isDisplayed());

    }

    @And("^I should see model text in the model on clicking help icon in return selection page$")
    public void iShouldSeeModelTextInTheModelOnClickingHelpIconInReturnSelectionPage(DataTable table) throws Throwable {

        //Click help icon
        Elements.findElement(Elements.element("return_selection.btag_help")).click();
        Map<String, String> values = table.asMap(String.class, String.class);
        for (String value : values.keySet()) {
            if (value.equals("model_text")) {
                Assert.assertTrue(Elements.findElement(Elements.element("return_selection.btag_model_text")).getText().equals(values.get(value)));
            }
        }
    }

    @Then("^I verify return address on Order details$")
    public void iVerifyReturnAddressOnOrderDetails() throws Throwable {
        ProfileAddress address = new ProfileAddress();
        address = CommonUtils.getReturnAddressFromDb(returnOrderDetails);
        String actualAddress = Elements.getText("order_details.submitted_address_order_detail").replace("\n", " ");
        log.info("Actual address = " + actualAddress);
        String expected_address = address.getAddressLine1() + " " + address.getCity() + ", " + address.getState() + " " + address.getZipCode();
        log.info("Expected address = " + expected_address);
        Assert.assertTrue("Actual address is not same as exoected address on order details page", StringUtils.equalsIgnoreCase(actualAddress.trim(), expected_address.trim()));

    }

    @Then("^I \"([^\"]*)\" see pickup section on return selection page$")
    public void iSeePickupSectionOnReturnSelectionPage(String visibility) throws Throwable {
        if (visibility.equals("should not")) {
            Assert.assertFalse(Elements.elementPresent(Elements.element("return_selection.pick_up_container")));
        } else if (visibility.equals("should")) {
            Assert.assertTrue(Elements.elementPresent(Elements.element("return_selection.pick_up_container")));
        }
    }

    @And("^I click on \"([^\"]*)\" button to schedule return pickup$")
    public void iClickOnButtonToScheduleReturnPickup(String option) throws Throwable {
        if (option.equals("yes")) {
            Wait.untilElementPresent(Elements.element("return_selection.pickup_yes"));
            Clicks.click("return_selection.pickup_yes");
        } else if (option.equals("no")) {
            Wait.untilElementPresent(Elements.element("return_selection.pickup_no"));
            Clicks.click("return_selection.pickup_no");
        }
    }

    @And("^I select \"([^\"]*)\" address for return pickup$")
    public void iSelectAddressForReturnPickup(String option) throws Throwable {
        Wait.secondsUntilElementPresent(Elements.element("return_selection.edit_pick_up_address"),30);
        Clicks.click("return_selection.edit_pick_up_address");
    }

    @Then("^I should see option to validate zipcode$")
    public void iShouldSeeOptionToValidateZipcode() throws Throwable {
        Wait.secondsUntilElementPresent(Elements.element("return_selection.pickup_zipcode"), 10);
        Assert.assertTrue(Elements.elementPresent(Elements.element("return_selection.pickup_zipcode")));
    }

    @And("^I \"([^\"]*)\" see schedule your pickup section now$")
    public void iSeeScheduleYourPickupSectionNow(String visibility) throws Throwable {
        Wait.forPageReady();
        if (visibility.equals("should not")) {
            Assert.assertFalse(Elements.elementPresent(Elements.element("return_selection.address_line_one")));
        } else if (visibility.equals("should")) {
            Wait.secondsUntilElementPresent(Elements.element("return_selection.address_line_one"), 20);
            Assert.assertTrue(Elements.elementPresent(Elements.element("return_selection.address_line_one")));
        }

    }

    @And("^I verify column names$")
    public void I_verify_column_names(List<String> items) throws Throwable {
        log.info("Items: " + items);
        List<WebElement> lineItems;
        if (macys())
            lineItems = Elements.findElements("return_confirmation.items_header_text");

        else
            lineItems = Elements.findElements("return_confirmation.items_header_text");

        List<String> list_Items = new ArrayList<>();
        for (WebElement ele : lineItems) {
            list_Items.add(ele.getText().trim());
        }
        Collections.sort(list_Items);
        Collections.sort(items);
        log.info("List items: " + list_Items);
        for(int i=0;i<list_Items.size();i++) {
            list_Items.get(i).equals(items.get(i));
        }
    }

    @Then("^I should see items selected in selection page in \"(.*?)\" page$")
    public void I_should_see_items_selected_in_selection_page_in_page(String confirmation) throws Throwable {
        String actualProductName;
        if (macys())
            actualProductName = Elements.findElement("return_confirmation.product_name").getText();
        else
            actualProductName = Elements.findElement("return_confirmation.product_name").getText();
        String pName = ReturnsPage.productName.toString();
        Assert.assertTrue("", actualProductName.contains(pName));

    }

    @Then("^I should see all line items should be unchecked by default$")
    public void I_should_see_all_line_items_should_be_unchecked_by_default() {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        for (int i = 0; i < lineItems.size(); i++) {

            System.out.println("isItemSelected for item " + i + " is : " + lineItems.get(i).get("isItemSelected"));
            Assert.assertTrue("Currently checkbox is checked", lineItems.get(i).get("isItemSelected").equals(false));
        }
    }

    @And("^I should see reason for return should be disabled with default value$")
    public void I_should_see_reason_for_return_should_be_displayed_with_default_value() {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        for (int i = 0; i < lineItems.size(); i++) {

            Assert.assertTrue("Currently reason is not disabled", lineItems.get(i).get("isReasonDisabled").equals("true"));
        }
    }

    @And("^I should see reason for return should contain all options as specified in reasons$")
    public void I_should_see_reason_for_return_should_cotain_all_options_as_specified_in_reason(List<String> items) {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        for (int i = 0; i < lineItems.size(); i++) {

            lineItems.get(i).put("reasonSelected", lineItems.get(i).get("reasonSelected").toString().replace("Select reason for return", ""));
            System.out.println(lineItems.get(i).get("reasonSelected"));
            for (int j = 0; j < items.size(); j++) {
                Assert.assertTrue((lineItems.get(i).get("reasonSelected").toString().contains(items.get(j))));
            }
        }
    }

    @And("^I should see page is displayed with all the quantity fields with default value one for item having more than one quantity$")
    public void I_should_see_page_is_displayed_with_all_the_quantity_fields_with_default_value_one_for_item_having_more_than_one_quantity() {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        for (int i = 0; i < lineItems.size(); i++) {
            Assert.assertNull("Quantity is not disabled", lineItems.get(i).get("isQuantityDisabled"));
        }
    }

    @And("^I should not see write a review option for any line item$")
    public void I_should_not_see_write_a_review_option_for_any_line_item() {
        Assert.assertTrue("write review option is present", !Elements.isElementInView(Elements.findElement("return_selection.write_review")));
    }

    @And("^I should see all line items upcs should be unique$")
    public void I_should_see_all_line_items_upcs_should_be_unique() {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        Set upcIDValue = new HashSet();
        for (int i = 0; i < lineItems.size(); i++) {
            String upc = lineItems.get(i).get("upcId").toString();
            upcIDValue.add(upc);
        }
        Assert.assertTrue("upcId is not unique", upcIDValue.size() == lineItems.size());
    }

    @And("^I select return submit from submit page$")
    public void iSelectReturnSubmitFromSubmitPage() throws Throwable {
        Wait.secondsUntilElementPresent("return_submit.submit_return", 15);
        Clicks.click("return_submit.submit_return");
    }

    @Then("^I should see no warning should be displayed$")
    public void iShouldSeeNoWarningShouldBeDisplayed() throws Throwable {
       boolean isAlertPresent =true;
        try {
            WebDriverManager.getWebDriver().switchTo().alert();
        }
        catch(Exception e)
        {
            isAlertPresent = false;
        }
        Assert.assertFalse(isAlertPresent);
    }



    @And("^I should be navigated to return confirmation page$")
    public void iShouldBeNavigatedToReturnConfirmationPage() throws Throwable {
        onPage("return_confirmation");
    }

    @Then("^I should see same email address which user has provided in the return selection page$")
    public void iShouldSeeSameEmailAddressWhichUserHasProvidedInTheReturnSelectionPage() throws Throwable {

        log.info("Return submit page email id :" + Elements.findElement("return_submit.email_id").getText());

        ReturnsPage page = new ReturnsPage();
        page.returnOrderDetails.get("email").toString().equals(Elements.findElement("return_submit.email_id").getText());
    }

    @Then("^I should see two print_mailing_label buttons$")
    public void iShouldSeeTwoPrint_mailing_labelButtons() throws Throwable {
        if (macys()) {
            Wait.untilElementPresent("return_confirmation.print_shipping_label_top");
            Assert.assertTrue("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_shipping_label_top"));
            Assert.assertTrue("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_shipping_label_bottom"));
        } else {
            Wait.untilElementPresent("return_confirmation.print_mailing_button");
            Assert.assertTrue("Print mailing button top should be visible", Elements.elementPresent("return_confirmation.print_mailing_button"));
        }

    }

    @When("^I navigate to following customer \"([^\"]*)\" page url$")
    public void i_navigate_to_following_customer_page_url(String arg1) throws Throwable {
        Navigate.visit(arg1);
    }

    @Then("^I verify customer page url navigation with \"([^\"]*)\" page$")
    public void i_verify_customer_page_url_navigation_with_page(String arg1) throws Throwable {
        switch (arg1) {
            case "app_answers_list":
                if (macys()) {
                    Clicks.click("return_selection.return_for_easy_for_app_answers_list");
                    Wait.forPageReady();
                    Assert.assertTrue("Could not reach on easyReturns Page.", WebDriverManager.getCurrentUrl().contains("easyreturn"));
                } else {
                    Clicks.click("return_selection.return_for_easy_for_app_answers_list");
                    Wait.forPageReady();
                    Assert.assertTrue("Could not reach on easyReturns Page.", WebDriverManager.getCurrentUrl().contains("easyreturn"));
                }
                break;
            case "app_mail":
                if (macys()) {
                    Clicks.click("return_selection.return_for_easy_for_app_mail");
                    Wait.forPageReady();
                    Navigate.switchWindow(1);
                    Assert.assertTrue("Could not reach on easyReturns Page.", WebDriverManager.getCurrentUrl().contains("easyreturn"));
                    Navigate.switchWindowClose();
                    Navigate.switchWindow(0);
                } else {
                    Clicks.click("return_selection.return_for_easy_for_app_mail");
                    Wait.forPageReady();
                    Navigate.switchWindow(1);
                    Assert.assertTrue("Could not reach on easyReturns Page.", WebDriverManager.getCurrentUrl().contains("easyreturn"));
                    Navigate.switchWindowClose();
                    Navigate.switchWindow(0);
                }
                break;
            case "app_init_online":
                if (macys()) {
                    Clicks.click("return_selection.return_for_easy_for_app_init_online");
                    Wait.forPageReady();
                    Assert.assertTrue("Could not reach on easyReturns Page.", WebDriverManager.getCurrentUrl().contains("easyreturn"));
                } else {
                    Clicks.click("return_selection.return_for_easy_for_app_init_online");
                    Wait.forPageReady();
                    Assert.assertTrue("Could not reach on easyReturns Page.", WebDriverManager.getCurrentUrl().contains("easyreturn"));
                }
                break;

            case "app": {
                Clicks.click("return_selection.easy_return_link");
                Wait.forPageReady();
                break;
            }

            default:
                Assert.fail("unrecognized page" + arg1);
        }
    }


    @And("^I should see \"([^\"]*)\" same as in the return selection page$")
    public void iShouldSeeSameAsInTheReturnSelectionPage(String headerElement) throws Throwable {

        ReturnsPage returnPage = new ReturnsPage();
        Map<String, String> orderHeader = returnPage.getOrderHeader();

        log.info("ORDER HEADER DETAILS FROM RETURN SELECTION");
        Iterator iterator = orderHeader.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            Object value = orderHeader.get(key);
            log.info("KEY: " + key + " " + "VALUE: " + value);
        }

        if (headerElement.equals("order number")) {
            log.info("Order number from return_submit page" + Elements.findElement("return_submit.order_number").getText());
            Assert.assertTrue("Order number did not match", orderHeader.get("orderNumber").equalsIgnoreCase(Elements.findElement("return_submit.order_number").getText()));
        } else if (headerElement.equals("order date")) {
            String orderDate = null;
            if (bloomingdales()) {
                orderDate = Elements.findElement("return_submit.order_date").getText().split(":")[1].trim();
            }
            log.info("Order date from return_submit page" + orderDate);
            Assert.assertTrue("Order date did not match", orderHeader.get("orderDate").equalsIgnoreCase(orderDate));

        }
    }

    @When("^I lookup I lookup production order with \"([^\"]*)\" status using email$")
    public void iLookupILookupProductionOrderWithStatusUsingEmail(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = CommonUtils.getProductionOrder(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        if (!onPage("order_status")) {
            Assert.fail("User not redirected to order status page!!");
        }
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.lookupOrderByEmail(returnOrderDetails);
        Wait.secondsUntilElementPresent("order_details.order_details_header", 30);
        if (!onPage("order_details")) {
            Assert.fail("User not redirected to order details page!!");
        }
    }

    @When("^I enter \"([^\"]*)\" for return pickup$")
    public void iEnterForReturnPickup(String zipCodeType) throws Throwable {
        String pickup_zipcode = ((zipCodeType.equals("valid_zipcode") ? (String) returnOrderDetails.get("other_pickup_zipcode") : (String) returnOrderDetails.get("invalid_zip_code")).toString());
        log.info("pickup_zipcode: " + pickup_zipcode);
        TextBoxes.typeTextbox(Elements.element("return_selection.pickup_zipcode"), pickup_zipcode);
        Clicks.click("return_selection.zipcode_eligibility");
    }

    @And("^I should see address fields to update pickup address with success message:$")
    public void iShouldSeeAddressFieldsToUpdatePickupAddressWithSuccessMessage(DataTable table) throws Throwable {
        String value = table.asList(String.class).get(0);

        log.info("List Values: " + value);

        Assert.assertTrue(Elements.elementPresent("return_selection.address_line_two"));
        String messageText = Elements.findElement(Elements.element("return_selection.pickup_zip_code_message")).getText();
        log.info("Message text is:" + messageText);

        Assert.assertTrue("Message alert didnt match", messageText.equals(value.toString()));

    }

    @Then("^I navigate to \"([^\"]*)\" window page using (let us help|customer service|contact us|drop off|ups content) link in return (selection|submit|confirmation) page$")
    public void iNavigateToWindowPageUsingDropOffLinkInReturnConfirmationPage(String arg1, String arg2, String arg3) throws Throwable {
        switch (arg1) {
            case "let us help": {
                Clicks.click("return_confirmation.goto_let_us_help");
                Navigate.switchWindow(1);
                Wait.forPageReady();
                log.info("Navigated to returns help page successfully");
                break;
            }
            case "contact us": {
                Clicks.click("return_confirmation.goto_contact_us");
                Navigate.switchWindow(1);
                Wait.forPageReady();
                log.info("Navigated to contact us page successfully");
                break;
            }
            case "ups drop off": {
                Clicks.click("return_confirmation.ups_drop_off");
                Navigate.switchWindow(1);
                Wait.forPageReady();
                log.info("Navigated to Ups drop off page successfully");
                break;
            }
            case "ups content": {
                Clicks.click("return_confirmation.ups_contact");
                Navigate.switchWindow(1);
                Wait.forPageReady();
                log.info("Navigated to Ups content page successfully");
                break;
            }
            default: {
                Assert.fail("Unrecognised parameter" + arg1);
            }
        }
        Navigate.switchWindowClose();
        Navigate.switchWindow(0);

        log.info("Navigated to first window and closed second window successfully");
    }

    @Then("^I see \"([^\"]*)\" is not a link in breadcrumb in \"([^\"]*)\" page$")
    public void iSeeIsNotALinkInBreadcrumbInPage(String breadcrumb_text, String page) throws Throwable {
        List<WebElement> items = Elements.findElements("return_confirmation.breadcrumb_link");
        List nameOfItems = new ArrayList<String>();
        for (WebElement e : items) {
            nameOfItems.add(e.getText());
        }
        switch (page) {
            case "OH": {
                Assert.assertTrue("Breadcrumb item" + breadcrumb_text + " does not exist in breadcrumb", !(nameOfItems.contains(breadcrumb_text)));
                log.info("Verifying links in breadcrumb for order status page");
                break;
            }
            case "OD": {
                Assert.assertTrue("Breadcrumb item" + breadcrumb_text + " does not exist in breadcrumb", !(nameOfItems.contains(breadcrumb_text)));
                log.info("Verifying links in breadcrumb for order details page");
                break;
            }
            case "SELECTION": {
                Assert.assertTrue("Breadcrumb item" + breadcrumb_text + " does not exist in breadcrumb", !(nameOfItems.contains(breadcrumb_text)));
                log.info("Verifying links in breadcrumb for return selection page");
                break;
            }
            case "SUBMIT": {
                Assert.assertTrue("Breadcrumb item" + breadcrumb_text + " does not exist in breadcrumb", !(nameOfItems.contains(breadcrumb_text)));
                log.info("Verifying links in breadcrumb for return submit page");
                break;
            }
            case "CONFIRMATION": {
                Assert.assertTrue("Breadcrumb item" + breadcrumb_text + " does not exist in breadcrumb", !(nameOfItems.contains(breadcrumb_text)));
                log.info("Verifying links in breadcrumb for return confirmation page");
                break;
            }
        }
    }


    @Given("^I should see order history page using \"([^\"]*)\" link in breadcrumb in \"([^\"]*)\" page$")
    public void iShouldSeeOrderHistoryPageUsingLinkInBreadcrumbInPage(String link_name, String page) throws Throwable {
        List<WebElement> items = Elements.findElements("return_confirmation.breadcrumb_link");
        List nameOfItems = new ArrayList<String>();
        for (WebElement e : items) {
            nameOfItems.add(e.getText());
        }
        String[] link = link_name.split("\\|");
        if (macys())
            link_name = link[0];
        else
            link_name = link[1];

        switch (page) {
            case "OD": {
                Assert.assertTrue("Breadcrumb link" + link_name + " does not exist in order details page", (nameOfItems.contains(link_name)));
                Clicks.click("return_confirmation.order_history");
                log.info("Selected breadcrumb link " + link_name + " in order details page");
                break;
            }
            case "SELECTION": {
                Assert.assertTrue("Breadcrumb link" + link_name + " does not exist in return selection page", (nameOfItems.contains(link_name)));
                Clicks.click("return_confirmation.order_history");
                log.info("Selected breadcrumb link " + link_name + " in return selection page");
                break;
            }
            case "SUBMIT": {
                Assert.assertTrue("Breadcrumb link" + link_name + " does not exist in return submit page", (nameOfItems.contains(link_name)));
                Clicks.click("return_confirmation.order_history");
                log.info("Selected breadcrumb link " + link_name + " in return submit page");
                break;
            }
            case "CONFIRMATION": {
                Assert.assertTrue("Breadcrumb link" + link_name + " does not exist in order confirmation page", (nameOfItems.contains(link_name)));
                Clicks.click("return_confirmation.order_history");
                log.info("Selected breadcrumb link " + link_name + " in order confirmation page");
                break;
            }
        }
        Wait.forPageReady();
        if (!onPage("order_status")) {
            Assert.fail("User is not redirected to order status page!!");
        }
    }

    @Then("^I should see the \"([^\"]*)\" link on the bottom of the page$")
    public void iShouldSeeTheLinkOnTheBottomOfThePage(String link) throws Throwable {
        if (link.equals("customer service"))
            link = "let us help";
        switch (link) {
            case "let us help": {
                Assert.assertTrue("let us help link is not present.", Elements.elementPresent("return_confirmation.goto_let_us_help"));
                log.info("let us help link is present.");
            }
            case "contact us": {
                Assert.assertTrue("contact us link is not present.", Elements.elementPresent("return_confirmation.goto_contact_us"));
                log.info("contact us link is present.");
                break;
            }
            default:
                Assert.fail("Link not valid.");
        }
    }

    @Then("^I navigate to previous page from existing page$")
    public void iNavigateToPreviousPageFromExistingPage() throws Throwable {
        Navigate.browserBack();
        Wait.forPageReady();
        log.info("Selected browser back button");
    }

    @Then("^I should see overlay after selecting help icon in credit section$")
    public void iShouldSeeOverlayAfterSelectingHelpIconInCreditSection() throws Throwable {
        Wait.forPageReady();
        Clicks.click("return_confirmation.help_icon");
        log.info("Clicked on help icon");
        Assert.assertTrue("Help overlay is not displayed", Elements.elementPresent("return_confirmation.help_overlay"));
        log.info("Help overlay is displayed");
    }

    @Given("^I should see help model shall disappear once user clicks on anywhere outside of the overlay$")
    public void iShouldSeeHelpModelShallDisappearOnceUserClicksOnAnywhereOutsideOfTheOverlay() throws Throwable {
        Clicks.click("return_confirmation.order_number");
        Assert.assertTrue("Help overlay is still displaying", !Elements.anyPresent("return_confirmation.help_overlay"));
        log.info("Help overlay is closed");
    }

    @Then("^I should see following return instruction in confirmation page on each site:$")
    public void iShouldSeeFollowingReturnInstructionInConfirmationPageOnEachSite(DataTable table) throws Throwable {
        String text = table.asList(String.class).get(0);
        String expectedEmail = returnOrderDetails.get("email").toString();
        String expectedText = text.replaceAll("<email_address>", expectedEmail);
        String actualText = Elements.getText("return_confirmation.confirmation_instruction");
        Assert.assertTrue("Text is not same.", expectedText.equals(actualText));
        log.info(expectedText + " is equal to " + actualText);
    }

    @Then("^I should see return \"([^\"]*)\" page title$")
    public void iShouldSeeReturnPageTitle(String page) throws Throwable {
        switch (page) {
            case "SELECTION": {
                Assert.assertTrue("Could not verify return selection page title", WebDriverManager.getWebDriver().getCurrentUrl().contains("/service/return/selection"));
                log.info("Verifying return selection page title");
                break;
            }
            case "SUBMIT": {
                Assert.assertTrue("Could not verify return submit page title", WebDriverManager.getWebDriver().getCurrentUrl().contains("/service/return/submit"));
                log.info("Verifying return submit page title");
                break;
            }
            case "CONFIRMATION": {
                Assert.assertTrue("Could not verify return confirmation page title", WebDriverManager.getWebDriver().getCurrentUrl().contains("/service/return/confirmation"));
                log.info("Verifying return confirmation page title");
                break;
            }
            default:
                Assert.fail("page is not available");
        }
    }

    @Given("^I should see the meta tag on Return \"([^\"]*)\" page$")
    public void iShouldSeeTheMetaTagOnReturnPage(String arg1) throws Throwable {
        if (bloomingdales()) {
            String actualText = Elements.getElementAttribute("return_confirmation.meta_content", "content");
            Assert.assertTrue("Meta data is not matched.", actualText.equals("Shop online. Bloomingdale's. Like no other store in the world."));
            log.info("Comparing meta data content");
        }
    }

    @Given("^I should see new header and footer elements in section$")
    public void iShouldSeeNewHeaderAndFooterElementsInSection() throws Throwable {
        //Commenting the below line because search_menu_container xpath is not known from QAA framework.
        // Assert.assertTrue("user menu container is not present", Elements.elementPresent("return_confirmation.user_menu_container"));
        Assert.assertTrue("search menu container is not present", Elements.elementPresent("return_confirmation.search_menu_container"));
        Assert.assertTrue("category_menu_container is not present", Elements.elementPresent("return_confirmation.category_menu_container"));
        Assert.assertTrue("footer_menu_section is not present", Elements.elementPresent("return_confirmation.footer_menu_container"));
        log.info("Header and footer sections are displayed same as My account page");
    }

    @Given("^I should verify that refund method section is not visible$")
    public void iShouldVerifyThatRefundMethodSectionIsNotVisible() throws Throwable {
        Assert.assertTrue("Refund Method section is visible", !(Elements.elementPresent("return_selection.refund_method_section")));
        log.info("Refund Method section is invisible");
    }

    @Given("^I verify order total in order header should not be displayed on the return selection page\\.$")
    public void iVerifyOrderTotalInOrderHeaderShouldNotBeDisplayedOnTheReturnSelectionPage() throws Throwable {
        Assert.assertTrue("Order total in order header is visible", !(Elements.elementPresent("return_selection.order_total")));
        log.info("Order total in order header section is invisible");
    }

    @Given("^I navigate to privacy policy window page using privacy policy link in return selection page$")
    public void iNavigateToPrivacyPolicyWindowPageUsingPrivacyPolicyLinkInReturnSelectionPage() throws Throwable {
        Clicks.click("return_selection.privacy_policy_link");
        Navigate.switchWindow(1);
        log.info("Navigated to privacy policy page");
        Navigate.switchWindowClose();
        Navigate.switchWindow(0);
        log.info("Closed popup window");
    }

    @Given("^I should see text \"([^\"]*)\" in return instruction section$")
    public void iShouldSeeTextInReturnInstructionSection(String return_instruction_text) throws Throwable {
        Assert.assertTrue("", Elements.getText("return_selection.return_instruction_text").equals(return_instruction_text));
        log.info(Elements.getText("return_selection.return_instruction_text") + " is equal to " + return_instruction_text);
    }

    @Given("^I should verify information text in contact information section$")
    public void iShouldVerifyInformationTextInContactInformationSection(List<String> expected_info_text) throws Throwable {
        Assert.assertTrue("", Elements.getText("return_selection.contact_info_text").equals(expected_info_text.get(0)));
        log.info(Elements.getText("return_selection.contact_info_text") + " is equal to " + expected_info_text.get(0));
    }

    @Given("^I should see \"([^\"]*)\" in modal overlay when I click on refund help icon$")
    public void iShouldSeeInModalOverlayWhenIClickOnRefundHelpIcon(String modal_text) throws Throwable {
        Clicks.click("return_selection.refund_help_icon");
        Assert.assertTrue("modal text is not same", Elements.getText("return_selection.refund_modal_text").equals(modal_text));
        log.info(Elements.getText("return_selection.refund_modal_text") + " is equal to " + modal_text);
    }

    @Then("^I should not see modal overlay when I click on outside of overlay$")
    public void iShouldNotSeeModalOverlayWhenIClickOnOutsideOfOverlay() throws Throwable {
        Clicks.click("return_selection.refund_method_section");
        Assert.assertTrue("Refund modal overlay is invisible", !Elements.elementPresent("return_selection.refund_modal_text"));
    }

    @Given("^I should see refund options in unselected state$")
    public void iShouldSeeRefundOptionsInUnselectedState() throws Throwable {
        WebElement checkBox = Elements.findElement("return_selection.credit_refund");
        Assert.assertTrue("Original refund option is seleted, it should not be selected", !(checkBox.isSelected()));
        checkBox = Elements.findElement("return_selection.gift_card_refund");
        Assert.assertTrue("Gift card refund option is seleted, it should not be selected", !(checkBox.isSelected()));
        log.info("Both options in Refund Method section are not selected");
    }

    @Then("^I should see the (orignal_payment|giftcard_payment) refund option is cleared$")
    public void iShouldSeeTheRefundOptionIsCleared(String option) throws Throwable {
        WebElement checkBox;
        switch (option) {
            case "orignal_payment":
                checkBox = Elements.findElement("return_selection.credit_refund");
                Assert.assertTrue("Original refund option is seleted, it should not be selected", !(checkBox.isSelected()));
                log.info("Refund option status is Unselected");
                break;
            case "giftcard_payment":
                checkBox = Elements.findElement("return_selection.gift_card_refund");
                Assert.assertTrue("Gift card refund option is seleted, it should not be selected", !(checkBox.isSelected()));
                log.info("Refund option status is Unselected");
                break;
        }
    }

    @Given("^I should see the text displayed \"([^\"]*)\" on gray bar on the Return selection Page$")
    public void i_should_see_the_text_displayed_on_gray_bar_on_the_Return_selection_Page(String refund_header) throws Throwable {
        Assert.assertTrue("Refund header Text is not same", Elements.getText("return_selection.refund_section_header").equals(refund_header));
    }


    @Then("^I should see header section in return CONFIRMATION page which is same as site home page$")
    public void iShouldSeeHeaderSectionInReturnCNFIRMATIONRageWhichIsSameAsSiteHomePage() throws Throwable {
        Assert.assertTrue("Header is not present", Elements.elementPresent("return_confirmation.global_header"));
        Assert.assertTrue("Search menu is not present", Elements.elementPresent("return_confirmation.search_menu_container"));
    }

    @Then("^I should see default address as pickup address$")
    public void i_should_see_default_address_as_pickup_address() throws Throwable {
        WebElement checkBox = Elements.findElement("return_selection.default_pick_up_address");
        Assert.assertTrue("ERROR - APP: By Default Default address in not selected", (checkBox.isSelected()));
    }

    @When("^I continue to submit page using \"([^\"]*)\" address by entering valid pickup information$")
    public void iContinueToSubmitPageUsingAddressByEnteringValidPickupInformation(String address_type) throws Throwable {
        if (address_type.equals("other")) {
            Wait.untilElementPresent("return_selection.edit_pick_up_address");
            Clicks.click("return_selection.edit_pick_up_address");
            TextBoxes.typeTextbox("return_selection.pickup_zipcode", returnOrderDetails.get("other_pickup_zipcode").toString());
            Clicks.click("return_selection.zipcode_eligibility");
            if (Elements.getText("return_selection.pickup_message").contains("Sorry")) {
                Assert.fail("ERROR - APP: Zip code " + returnOrderDetails.get("other_pickup_zipcode").toString() + " Entered is not valid");
            }
            TextBoxes.typeTextbox("return_selection.address_line_one", returnOrderDetails.get("address_line_one").toString());
        } else {
            log.info("Pickup Address is default");
        }
        TextBoxes.typeTextbox("return_selection.special_instructions", returnOrderDetails.get("special_instructions").toString());
        ;
        TextBoxes.typeTextbox("return_selection.phone_number", returnOrderDetails.get("phone_number").toString());
        returnOrderDetails.put("scheduled_date", DropDowns.getSelectedValue(Elements.element("return_selection.scheduled_date")));
        returnOrderDetails.put("scheduled_time", DropDowns.getSelectedValue(Elements.element("return_selection.scheduled_time")));
        log.info("Picked up the Address to verify in Submit/Confirmation page.");
        I_select_items_and_continue_to_submit_page();
    }

    @Then("^I should see pick up information on \"([^\"]*)\" as specified in selection page$")
    public void iShouldSeePickUpInformationOnAsSpecifiedInSelectionPage(String return_page) throws Throwable {
        switch (return_page) {
            case "submit page": {
                Assert.assertTrue("Pickup Date is different on " + return_page, Elements.getText("return_submit.pickup_date").equals(returnOrderDetails.get("scheduled_date").toString()));
                log.info("pickup date on " + return_page + " is " + Elements.getText("return_submit.pickup_date"));
                Assert.assertTrue("Pickup Time is different on " + return_page, Elements.getText("return_submit.pickup_time").equalsIgnoreCase(returnOrderDetails.get("scheduled_time").toString()));
                log.info("pickup time on " + return_page + " is " + Elements.getText("return_submit.pickup_time"));
                Assert.assertTrue("Address line 1 is different on " + return_page, Elements.getText("return_submit.pickup_address_line_01").equalsIgnoreCase(returnOrderDetails.getString("address_line_one")));
                log.info("Address line 1 on " + return_page + " is " + Elements.getText("return_submit.pickup_address_line_01"));
                Assert.assertTrue("Address line 2 is different on " + return_page, Elements.getText("return_submit.pickup_address_city").equalsIgnoreCase(returnOrderDetails.getString("pickup_city")));
                log.info("Address city on " + return_page + " is " + Elements.getText("return_submit.pickup_address_city"));
                Assert.assertTrue("Address line 2 is different on " + return_page, Elements.getText("return_submit.pickup_address_state").equalsIgnoreCase(returnOrderDetails.getString("pickup_state")));
                log.info("Address state on " + return_page + " is " + Elements.getText("return_submit.pickup_address_state"));
                Assert.assertTrue("Address line 2 is different on " + return_page, Elements.getText("return_submit.pickup_address_zipcode").trim().equals(returnOrderDetails.getString("shipping_zip_code")));
                log.info("zipcode on " + return_page + " is " + Elements.getText("return_submit.pickup_address_zipcode"));
                if (returnOrderDetails.getString("special_instructions").equals(""))
                    Assert.assertTrue("Special Instruction is different on " + return_page, Elements.getText("return_submit.special_instructions").replace("Special Instructions:\n", "").equalsIgnoreCase("N/A"));
                else
                    Assert.assertTrue("Special Instruction is different on " + return_page, Elements.getText("return_submit.special_instructions").replace("Special Instructions:\n", "").equalsIgnoreCase(returnOrderDetails.getString("special_instructions")));
                log.info("Special Instruction on " + return_page + " is " + Elements.getText("return_submit.special_instructions").replace("Special Instructions:\n", ""));
                log.info("Pickup location was properly updated on " + return_page + " page");
                break;
            }
            case "confirmation page": {
                Map direction_notations = new HashedMap();
                Map address_notations = new HashedMap();
                direction_notations.put("w", "West");
                direction_notations.put("s", "South");
                direction_notations.put("n", "North");
                direction_notations.put("e", "East");
                address_notations.put("ave", "Avenue");
                address_notations.put("st", "Street");
                address_notations.put("cmn", "Common");
                Assert.assertTrue("Pickup Date is different on " + return_page, Elements.getText("return_confirmation.pickup_date").equals(returnOrderDetails.get("scheduled_date").toString()));
                log.info("pickup date on " + return_page + " is " + Elements.getText("return_confirmation.pickup_date"));
                Assert.assertTrue("Pickup Time is different on " + return_page, Elements.getText("return_confirmation.pickup_time").equalsIgnoreCase(returnOrderDetails.get("scheduled_time").toString()));
                log.info("pickup time on " + return_page + " is " + Elements.getText("return_confirmation.pickup_time"));

                String addressOne[] = returnOrderDetails.getString("address_line_one").split(" ");
                String expectedAddressLineOne = "";
                for (int i = 0; i < addressOne.length; i++) {
                    if (addressOne[i].equalsIgnoreCase("w") || addressOne[i].equalsIgnoreCase("s") || addressOne[i].equalsIgnoreCase("n") || addressOne[i].equalsIgnoreCase("e")) {
                        expectedAddressLineOne += direction_notations.get(addressOne[i].toLowerCase());
                    } else {
                        if (addressOne[i].equalsIgnoreCase("ave") || addressOne[i].equalsIgnoreCase("st") || addressOne[i].equalsIgnoreCase("cmn")) {
                            expectedAddressLineOne += address_notations.get(addressOne[i].toLowerCase());
                        } else {
                            expectedAddressLineOne += addressOne[i];
                        }
                    }
                    expectedAddressLineOne += " ";
                }
                expectedAddressLineOne = expectedAddressLineOne.trim();
                Assert.assertTrue("Address line 1 is different on " + return_page, Elements.getText("return_confirmation.pickup_address_line_01").equalsIgnoreCase(expectedAddressLineOne));
                log.info("Address line 1 on " + return_page + " is " + Elements.getText("return_confirmation.pickup_address_line_01"));
                Assert.assertTrue("Address line 2 is different on " + return_page, Elements.getText("return_confirmation.pickup_address_city").equalsIgnoreCase(returnOrderDetails.getString("pickup_city")));
                log.info("Address city on " + return_page + " is " + Elements.getText("return_confirmation.pickup_address_city"));
                Assert.assertTrue("Address line 2 is different on " + return_page, Elements.getText("return_confirmation.pickup_address_state").equalsIgnoreCase(returnOrderDetails.getString("pickup_state")));
                log.info("Address state on " + return_page + " is " + Elements.getText("return_confirmation.pickup_address_state"));
                Assert.assertTrue("Address line 2 is different on " + return_page, Elements.getText("return_confirmation.pickup_address_zipcode").equals(returnOrderDetails.getString("shipping_zip_code")));
                log.info("zipcode on " + return_page + " is " + Elements.getText("return_confirmation.pickup_address_zipcode"));
                if (returnOrderDetails.getString("special_instructions").equals(""))
                    Assert.assertTrue("Special Instruction is different on " + return_page, Elements.getText("return_confirmation.special_instructions").replace("Special Instructions:\n", "").equalsIgnoreCase("N/A"));
                else
                    Assert.assertTrue("Special Instruction is different on " + return_page, Elements.getText("return_confirmation.special_instructions").replace("Special Instructions:\n", "").equalsIgnoreCase(returnOrderDetails.getString("special_instructions")));
                log.info("Special Instruction on " + return_page + " is " + Elements.getText("return_confirmation.special_instructions").replace("Special Instructions:\n", ""));
                log.info("Pickup location was properly updated on " + return_page + " page");
                break;
            }
        }
    }

    @Given("^I navigate to new window after selecting print shipping label in order details page$")
    public void iNavigateToNewWindowAfterSelectingPrintShippingLabelInOrderDetailsPage() throws Throwable {
        Wait.untilElementPresent( "order_details.print_mailing_label");
        Clicks.click("order_details.print_mailing_label");
        Navigate.switchWindow(1);
        Wait.forPageReady();
        shouldBeOnPage("return_confirmation");
        log.info("In return confirmation page");
    }

    @Then("^I should see button to reprint the confirmation page$")
    public void iShouldSeeButtonToReprintTheConfirmationPage() throws Throwable {
        Wait.untilElementPresent("order.confirmation.print_shipping_label");
        Assert.assertTrue("print shipping label is not visible", Elements.elementPresent("return_confirmation.print_shipping_label"));
        log.info("print shipping label is visible");
    }

    @When("^I lookup with \"([^\"]*)\" order$")
    public void iLookupWithOrder(String orderType) throws Throwable {
            HashMap<String, String> order = new HashMap<>();
            order.put("return_order", orderType);
            returnOrderDetails = Utils.getReturnOrders(order);
            orderNum = returnOrderDetails.getString("order_number");
            TextBoxes.typeTextbox(Elements.element("order_status.order_number"), orderNum);
            log.info("Entered order number is: " + orderNum);
    }


    @When("^I delete the order number as guest user$")
    public void iDeleteTheOrderNumberAsGuestUser() throws Throwable {
        TextBoxes.typeTextNEnter(Elements.element("order_status.order_number"), "");
        log.info("Cleared order number field");
    }

    @And("^I see email address or phone number fields and gift return section is collapsed$")
    public void iSeeEmailAddressOrPhoneNumberFieldsAndGiftReturnSectionIsCollapsed() throws Throwable {
        Assert.assertTrue("Order verification section should not be visible", Elements.getElementAttribute("order_status.order_verification_section", "style").contains("none"));
        log.info("Order verification section is not visible");
    }


    @When("^I lookup \"([^\"]*)\" order with gift return$")
    public void iLookupOrderWithGiftReturn(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
        if (!onPage("order_status")) {
            Assert.fail("User not redirected to order status page!!");
        }
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.lookupOrderByZip(returnOrderDetails);
    }

    @And("^I should see error message on top$")
    public void iShouldSeeErrorMessageOnTop(List<String> errorMessage) throws Throwable {
        Assert.assertTrue("", Elements.getText("order_status.error_message").equals(errorMessage.get(0)));
        log.info(errorMessage + " matches " + Elements.getText("order_status.error_message"));
    }

    @When("^I navigate to \"([^\"]*)\" using \"([^\"]*)\" order as a (guest|signed in) user using \"([^\"]*)\" address$")
    public void iNavigateToUsingOrderAsAGuestUserUsingAddress(String page_name, String order_type, String userType, String pickup_address) throws Throwable {
        switch (page_name) {
            case "submit page":
                I_navigate_to_return_selection_page_using_order_as_a_user(order_type, userType);
                iClickOnButtonToScheduleReturnPickup("yes");
                iContinueToSubmitPageUsingAddressByEnteringValidPickupInformation(pickup_address);
                break;
            case "confirmation page":
                I_navigate_to_return_selection_page_using_order_as_a_user(order_type, userType);
                iClickOnButtonToScheduleReturnPickup("yes");
                iContinueToSubmitPageUsingAddressByEnteringValidPickupInformation(pickup_address);
                I_navigate_to_return_confirmation_page();
                break;
        }
    }

    @And("^I should see pickup note on return page:$")
    public void iShouldSeePickupNoteOnReturnPage(DataTable note) throws Throwable {
        String expectedNote = note.asList(String.class).get(0);
        String actualNote = Elements.getText("return_submit.please_note").replace("/n", " ").trim();
        Assert.assertTrue("Please_note text is not same.", expectedNote.equals(actualNote));
        log.info("please_note text is " + actualNote);
    }

    @And("^I should see pickup_instruction \"([^\"]*)\" on page as mentioned in title$")
    public void iShouldSeePickup_instructionOnPageAsMentionedInTitle(String instruction) throws Throwable {
        String actualInstruction = Elements.getText("return_submit.pickup_step_title");
        Assert.assertTrue("pickup_step_title text is not same.", instruction.equals(actualInstruction));
        log.info("pickup_step_title text is " + actualInstruction);
    }

    @And("^I should see pickup_instruction \"([^\"]*)\" on page as mentioned in instruction_one$")
    public void iShouldSeePickup_instructionOnPageAsMentionedInInstruction_one(String instructionOne) throws Throwable {
        String actualInstructionOne = Elements.getText("return_submit.pickup_step_one").replace("/n", " ").trim();
        Assert.assertTrue("pickup_instruction_one text is not same.", instructionOne.equals(actualInstructionOne));
        log.info("pickup_step_one text is " + actualInstructionOne);
    }

    @And("^I should see pickup_instruction \"([^\"]*)\" on page as below:$")
    public void iShouldSeePickup_instructionOnPageAsBelow(String page_instruction, DataTable instruction) throws Throwable {
        String expectedInstructionTwo = instruction.asList(String.class).get(0);
        switch (page_instruction) {
            case "two":
                String actualInstructionTwo = Elements.getText("return_submit.pickup_step_two").replace("/n", " ").trim();
                actualInstructionTwo = actualInstructionTwo.replaceAll("—", "-");
                Assert.assertTrue("pickup_instruction_two text is not same.", expectedInstructionTwo.equals(actualInstructionTwo));
                log.info("pickup_step_two text is " + actualInstructionTwo);
                break;
            case "three":
                String actualInstructionThress = Elements.getText("return_submit.pickup_step_three").replace("/n", " ").trim();
                actualInstructionThress = actualInstructionThress.replaceAll("—", "-");
                Assert.assertTrue("pickup_instruction_three text is not same.", expectedInstructionTwo.equals(actualInstructionThress));
                log.info("pickup_step_three text is " + actualInstructionThress);
                break;
        }
    }

    @And("^I should see label on \"([^\"]*)\" as below:$")
    public void iShouldSeeLabelOnAsBelow(String pageName, DataTable table) throws Throwable {
        if (pageName.equals("confirmation page")) {
            String expectedText = table.asList(String.class).get(0);
            String actualText = Elements.getText("return_confirmation.pickup_add_to_calender");
            Assert.assertTrue("Label-: Verified Add to calendar exists text is not same.", expectedText.equals(actualText));
            log.info("Verified Add to calendar exists");
        } else
            log.info("Submit Page Verification completed.");
    }

    @And("^I should see header section in return \"([^\"]*)\" page which is same as site home page$")
    public void iShouldSeeHeaderSectionInReturnPageWhichIsSameAsSiteHomePage(String page_name) throws Throwable {
        Assert.assertTrue("Header is not present", Elements.elementPresent("return_confirmation.global_header"));
        Assert.assertTrue("Search menu is not present", Elements.elementPresent("return_confirmation.search_menu_container"));
        switch (page_name) {
            case "SELECTION":
                CommonUtils.navigateToRandomProduct();
                log.info("Selected random category in return selection page");
                Navigate.browserBack();
                break;
            case "SUBMIT":
                CommonUtils.navigateToRandomProduct();
                log.info("Selected random category in return selection page");
                Navigate.browserBack();
                break;
            case "CONFIRMATION":
                CommonUtils.navigateToRandomProduct();
                log.info("Selected random category in return selection page");
                Navigate.browserBack();
                break;
        }
        log.info("Navigated to order status page");
    }

    @And("^I initiate return for (\\d+) times and navigate to order details page as a \"([^\"]*)\" use$")
    public void iInitiateReturnForTimesAndNavigateToOrderDetailsPageAsAUse(int count, String user) throws Throwable {
        for (int i = 1; i <= count; i++) {
            System.out.print("");
            I_navigate_to_confirmation_page_using_order_as_a_user(returnOrderDetails.getString("return_order"), user);
            Clicks.click(Elements.element("footer.goto_order_status"));
            switch (user) {
                case "guest":
                    new ReturnsPage().lookupOrderByEmail(returnOrderDetails);
                    log.info("Performing order look up using email");
                    break;
                case "signed":
                    new ReturnsPage().findOrderInDateRange(returnOrderDetails.get("order_number").toString());
                    Clicks.clickIfPresent("home.popup_close");
                    Wait.forPageReady();
                    log.info("Selected order" + orderNum);
                    break;
            }
        }
    }

    @Given("^I visit order history page as a signed user$")
    public void iVisitOrderHistoryPageAsASignedUser() throws Throwable {
        new PageNavigation().I_visit_the_web_site_as_a_registered_user();
        Clicks.click("home.goto_order_status");
        Wait.forPageReady();
        if (!onPage("order_status")) {
            Assert.fail("User is not redirected to order status page!!");
        }
    }

    @And("^I verify details related to payment and billing address should not be displayed$")
    public void iVerifyDetailsRelatedToPaymentAndBillingAddressShouldNotBeDisplayed() throws Throwable {
        Assert.assertFalse("payment_details- Card data should not be present", Elements.elementPresent("order_details.card_type"));
        Assert.assertFalse("payment_details- Amount Charge data should not be present", Elements.elementPresent("order_details.amount_charged"));
        Assert.assertFalse("Order Total data should not be present", Elements.elementPresent("order_details.order_total_amount"));
        Assert.assertFalse("Billing Address name should not be present", Elements.elementPresent("order_details.billing_address_name"));
        Assert.assertFalse("Billing Address add1 should not be present", Elements.elementPresent("order_details.billing_address_add1"));
        Assert.assertFalse("Billing Address add2 should not be present", Elements.elementPresent("order_details.billing_address_add2"));
        Assert.assertFalse("Billing Address add3 should not be present", Elements.elementPresent("order_details.billing_address_add3"));
        Assert.assertFalse("Billing Address phone should not be present", Elements.elementPresent("order_details.billing_address_phone"));
        Assert.assertFalse("Billing Address email should not be present", Elements.elementPresent("order_details.billing_address_email"));
        log.info("Details in header are invisible");
    }

    @Then("^I navigate to previous page from existing page after selecting any fob from header$")
    public void iNavigateToPreviousPageFromExistingPageAfterSelectingAnyFobFromHeader() throws Throwable {
        Home.selectRandomCategory();
        log.info("Selected random category in return selection page");
        Wait.forPageReady();
        Navigate.browserBack();
    }

    @And("^I set valid tracking number in db$")
    public void iSetValidTrackingNumberInDb() throws Throwable {
        CommonUtils.setTrackingNumberinDB(returnOrderDetails);
        log.info("Tracking number got updated");
    }


    @And("^I should not see status field in tracking details for below status$")
    public void iShouldNotSeeStatusFieldInTrackingDetailsForBelowStatus(List<String> message) throws Throwable {
        System.out.print("Need to implement this step as Tracking service is Down");
        Assert.fail();
    }

    @And("^I should see delivery date$")
    public void iShouldSeeDeliveryDate(List<String> message) throws Throwable {
        System.out.print("Need to implement this step as Tracking service is Down");
        Assert.fail();
    }

    @Then("^I verify order date in (OD|OH) page should not be out of range date$")
    public void iVerifyOrderDateInODPageShouldNotBeOutOfRangeDate(String pageType) throws Throwable {
        DateFormat mcomFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        String date = Elements.getText(Elements.element("order_status.order_date"));
        Date orderDate = mcomFormat.parse(date);
        String ordYear = orderDate.toString().split(" ")[orderDate.toString().split(" ").length - 1];
        if (!ordYear.equals("9999"))
            return;
        else
            Assert.fail("Order Date is not correct");
    }

    @Then("^I should see shipping charge as free$")
    public void iShouldSeeShippingChargeAsFree() throws Throwable {
        ReturnsPage returnObj = new ReturnsPage();
        returnObj.clickOrderDetailsButton(orderNum);
        List<Map<String, Object>> orderDetails = returnObj.getOrderDetails();
        for (Map itemDetails : orderDetails) {
            if (itemDetails.get("orderTotalDetails").toString().isEmpty() && !itemDetails.get("orderTotalDetails").toString().contains("FREE")) {
                Assert.fail("Free shipping charge is not displayed");
            }
        }
    }

    @Then("^I verify validations for order number \"([^\"]*)\", email address \"([^\"]*)\", area code \"([^\"]*)\", exchange number \"([^\"]*)\", subscriber number \"([^\"]*)\", error message \"([^\"]*)\" and combination \"([^\"]*)\"$")
    public void iVerifyValidationsErrorMessage(String order_tpye, String email_add, String area, String exchange, String subscriber, String error_message, String order_email_combination) throws Throwable {
        String order_number= Utils.getOrderNumber(order_tpye);
        HashMap<String, String> data = new HashMap<>();
        data.put("order_number", order_number);
        data.put("email", email_add);
        data.put("area_code", area);
        data.put("exchange_code", exchange);
        data.put("subscriber_code", subscriber);

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            String key = e.getKey();
            String value = e.getValue();
            if (key.contains("")) {
                key = " ";
            }
        }
        if (order_email_combination.equals("Y")) {
            Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
            log.info("Entering order number for order lookup " + data.get("order_number"));
            TextBoxes.typeTextbox(Elements.element("order_status.order_number"), data.get("order_number"));
            log.info("Entering email for order lookup " + data.get("email"));
            TextBoxes.typeTextbox(Elements.element("order_status.email"), data.get("email"));
            Clicks.click(Elements.element("order_status.view_order_details"));
        } else {
            Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
            TextBoxes.typeTextbox(Elements.element("order_status.order_number"), data.get("order_number"));
            TextBoxes.typeTextbox(Elements.element("order_status.phone_area_code"), data.get("area_code"));
            TextBoxes.typeTextbox(Elements.element("order_status.phone_exchange"), data.get("exchange_code"));
            TextBoxes.typeTextbox(Elements.element("order_status.phone_subscriber"), data.get("subscriber_code"));
            Clicks.click(Elements.element("order_status.view_order_details"));
        }
        Assert.assertTrue("Error Message should be displayed", Elements.elementPresent("order_status.error_message"));
        Assert.assertTrue(Elements.findElement("order_status.error_message").getText().equals(error_message));
        log.info(Elements.findElement("order_status.error_message").getText() + "is equal to" + error_message);
    }

    @And("^I search with below order number and email in order status page:$")
    public void iSearchWithBelowOrderNumberAndEmailInOrderStatusPage(DataTable table) throws Throwable {
        String orderType = table.getGherkinRows().get(1).getCells().get(0);
        String orderNum=  Utils.getOrderNumber(orderType);
        String email = table.getGherkinRows().get(1).getCells().get(1);
        HashMap<String, String> data = new HashMap<>();
        data.put("order_number", orderNum);
        data.put("email", email);
        Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
        log.info("Entering order number for order lookup " + data.get("order_number"));
        TextBoxes.typeTextbox(Elements.element("order_status.order_number"), data.get("order_number"));
        log.info("Entering email for order lookup " + data.get("email"));
        TextBoxes.typeTextbox(Elements.element("order_status.email"), data.get("email"));
        Clicks.click(Elements.element("order_status.view_order_details"));
    }

    @Then("^I verify backordered text for backordered items in OD page$")
    public void iVerifyBackorderedTextForBackorderedItemsInODPage() throws Throwable {
        String backOrderMsg = "If you would like to make changes to your orders,Please contact customer service at 1-800-BUY-MACY(289-6229) for assistance";
        Assert.assertTrue("Could not verified backordered text for backordered items in OD page", Elements.getText("order_details.backordered_text").trim().contains(backOrderMsg));
    }

    @Then("^I verify the page header in OH page$")
    public void iVerifyThePageHeaderInOHPage() throws Throwable {
        Clicks.hoverForSelection("header.goto_my_account_link");
        Assert.assertTrue("Sign Out Link could not be found",
                Wait.untilElementPresent("header.goto_sign_out_link"));
        String actual_header_text = Elements.findElement("order_status.page_header").getText();
        String additional_text = macys() ? "'s order status & history" : "'s Order Status & Order History";
        UserProfile customer = TestUsers.getCustomer(null);
        String first_name = customer.getUser().getProfileAddress().getFirstName();
        String headerText = first_name + additional_text;
        log.info("Text displayed :" + headerText);
        Assert.assertTrue("Header text is verified", actual_header_text.equals(headerText));
    }


//    @Then("^I see \"([^\"]*)\" is not a link in breadcrumb in \"([^\"]*)\" page$")
//    public void iSeeIsNotALinkInBreadcrumbInPage(String breadcrumbText, String pageName) throws Throwable {
//        ReturnsPage returnObj = new ReturnsPage();
//        switch (pageName) {
//            case "OH":
//                List<String> breadcrumbLinks = returnObj.breadcrumbLinks();
//                Assert.assertFalse("Order Status & History is not a link",breadcrumbLinks.contains(breadcrumbText));
//                log.info("Verifying links in breadcrumb for order status page");
//                break;
//            case "OD":
//                Clicks.click("order_details.return_items");
//                break;
//            default:
//                Assert.fail("Invalid page name: " + pageName);
//        }
//    }

    @When("^I navigate to my account page using \"([^\"]*)\" link in breadcrumb in \"([^\"]*)\" page for \"([^\"]*)\" user$")
    public void iNavigateToMyAccountPageUsingLinkInBreadcrumbInPageForUser(String linkName, String pageName, String user) throws Throwable {
        ReturnsPage returnObj = new ReturnsPage();
        switch (pageName) {
            case "OH":
                returnObj.selectBreadcrumb(linkName);
                log.info("Selected link " + linkName + " from breadcrumb in order status page");
                break;
            case "OD":
                returnObj.selectBreadcrumb(linkName);
                log.info("Selected link " + linkName + " from breadcrumb in order status page");
                break;
            default:
                Assert.fail("Invalid page name: " + pageName);
        }
        switch (user) {
            case "guest":
                shouldBeOnPage("sign_in");
                break;
            case "signin":
                shouldBeOnPage("my_account");
                break;

        }

    }

    @When("^I lookup with incorrect order number and valid(phonenumber|emailaddress) in \"([^\"]*)\" page$")
    public void iLookupWithIncorrectOrderNumberAndValidInPage(String input, String pageName, DataTable table) throws Throwable {
        String pageClass = String.valueOf(pageName.equals("OH") ? onPage("order_status") : onPage("easy_returns"));
        Map<String, String> values = table.asMap(String.class, String.class);
        ReturnsPage returnsPage = new ReturnsPage();
        while (onPage(pageClass)) {
            for (String value : values.keySet()) {
                if (value.equals("emailaddress")) {
                    returnsPage.lookupOrderByEmail(returnOrderDetails);
                }
            }
        }
    }

    @And("^I should verify that refund method section \"([^\"]*)\" in page$")
    public void iShouldVerifyThatRefundMethodSectionInPage(String visibility) throws Throwable {
        if (visibility.equals("visible")) {
            Wait.untilElementPresent("return_selection.refund_method_section");
            Assert.assertTrue("Refund method section should be visible", Elements.elementPresent("return_selection.refund_method_section"));
            Assert.assertTrue("Refund method header should be visible", Elements.elementPresent("return_selection.refund_section_header"));
            log.info("Refund method section and Refund method header are visible");
        } else {
            Wait.forPageReady();
            Assert.assertFalse("Refund method section should not be visible", Elements.elementPresent("return_selection.refund_method_section"));
            Assert.assertFalse("Refund method header should not be visible", Elements.anyPresent("return_selection.refund_section_header"));
            log.info("Refund method section and Refund method header are invisible");
        }
    }

    @Then("^I verify order of displaying order level details section for \"([^\"]*)\" orders in OH page$")
    public void iVerifyOrderOfDisplayingOrderLevelDetailsSectionForOrdersInOHPage(String orderType) throws Throwable {

        ReturnsPage returnObj = new ReturnsPage();
        pausePageHangWatchDog();
        returnObj.findOrderInDateRange(orderNum);
        Map orderHash = returnObj.getOrder(orderNum);
        String[] expectedHierarchy = new String[0];
        LinkedHashMap<String, Integer> expect = new LinkedHashMap<>();
        int i = 1;
        if (orderType.equals("normal")) {
            if (macys()) {
                expectedHierarchy = new String[]{"processing", "in transit", "delivered", "returned", "canceled"};
                for (int start = 0; start < expectedHierarchy.length; start++) {
                    expect.put(expectedHierarchy[start], i);
                }
            } else {
                expectedHierarchy = new String[]{"PROCESSING", "IN TRANSIT", "DELIVERED", "RETURNED", "CANCELLED"};
                for (int start = 0; start < expectedHierarchy.length; start++) {
                    expect.put(expectedHierarchy[start], i);
                }
            }
        } else if (orderType.equals("bops")) {
            if (macys()) {
                expectedHierarchy = new String[]{"processing for store pick-up", "ready for pick-up", "Backordered", "Processing", "Shipped", "picked Up", "Delivered", "Returned", "refunded by store", "CANCELLED"};
                for (int start = 0; start < expectedHierarchy.length; start++) {
                    expect.put(expectedHierarchy[start], i);
                }
            } else {
                expectedHierarchy = new String[]{"IN-STORE PICK UP Processing", "IN-STORE PICK UP Ready for pick up", "BACKORDERED", "PROCESSING", "Shipped", "IN-STORE PICK UP Items Picked Up", "Delivered", "Returned", "IN-STORE PICK UP Cancelled", "CANCELLED"};
                for (int start = 0; start < expectedHierarchy.length; start++) {
                    expect.put(expectedHierarchy[start], i);
                }
            }
        }

        String text;

        LinkedHashMap<String, Integer> actualHierarchy = new LinkedHashMap<>();
        List<WebElement> element = Elements.findElements("order_status.order_status_text");
        for (int index = 0; index < element.size(); index++) {
            text = macys() ? element.get(index).getText().split("\\ |:")[0] : element.get(index).getText().split("\\|")[1];
            actualHierarchy.put(text, i);
        }


        Iterator<Map.Entry<String, Integer>> itr = actualHierarchy.entrySet().iterator();
        Iterator<Map.Entry<String, Integer>> ite = expect.entrySet().iterator();
        boolean flag = false;
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            Map.Entry<String, Integer> entry1 = ite.next();


            if (entry.getKey().equals(entry1.getKey())) {

                flag = true;
            } else {

                flag = false;
            }
        }
        if (flag) {
            log.info("Hiereachy Matched");
        } else {
            Assert.fail("Hierarchy not matched");
        }

    }

    @Then("^I should see misc links navigate to corresponding pages$")
    public void iShouldSeeMiscLinksNavigateToCorrespondingPages() throws Throwable {
        Clicks.click("order_status.track_international_order");
        Wait.secondsUntilElementPresent("customer_service.verifyPage", 30);
        log.info("Selected track_international order");
        Clicks.click("footer.goto_order_status");
        Wait.secondsUntilElementPresent("order_status.verify_page", 20);
        Clicks.click("order_status.check_delivery_status");
        Wait.secondsUntilElementPresent("furniture_mattress_status_page.verifyPage", 30);
        shouldBeOnPage("furniture_mattress_status_page");
        log.info("Selected FurnitureMatresspage");
    }

    @When("^I navigate to easy returns page$")
    public void iNavigateToEasyReturnsPage() {
        Navigate.visit(RunConfig.url + "/service/easyreturn");
        shouldBeOnPage("easy_returns");
        log.info("Navigated Successfully to easy returns Page");
    }

    @Then("^I verify lockout error message after (\\d+) attempts$")
    public void iVerifyLockoutErrorMessageAfterAttempts(int number, List<String> errorMessage) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        for (int i = 0; i <= number; i++) {
            Wait.forPageReady();
            log.info("Error message " + i + " is " + Elements.getText("order_status.error_message"));
            if (i >= 4) {
                Wait.forPageReady();
                if (onPage("order_status")) {
                    Assert.assertTrue("Error message should match", Elements.getText("order_status.error_message").equals(errorMessage.get(0)));
                } else if (onPage("easy_returns")) {
                    Assert.assertTrue("Error message should match", Elements.getText("easy_returns.error_message").equals(errorMessage.get(0)));
                }

                break;
            }
            if (onPage("order_status")) {
                Utils.threadSleep(1000, "Waiting for click.");
                Wait.untilElementPresent("order_status.view_order_details");
                Clicks.click(Elements.element("order_status.view_order_details"));
            } else if (onPage("easy_returns")) {
                Utils.threadSleep(1000, "Waiting for click.");
                Wait.untilElementPresent("easy_returns.view_order_details");
                Clicks.click(Elements.element("easy_returns.view_order_details"));
            }


        }
    }

    @Then("^I should see \"International Returns\" header and \"International Returns Policy\" link$")
    public void VerifyInternationReturnsHeaderandLinkText() {
        Assert.assertTrue("International Returns text is not displayed in easy returns page", getText("easy_returns.international_returns_text").equals("International Returns"));
        Assert.assertTrue("International Returns link is not displayed in easy returns page", getText("easy_returns.international_returns_policy_link").equals("International Returns Policy"));
        log.info("International returns displayed in easy returns page");
    }

    @And("^\"International Returns Policy\" link should be navigated to Customer service International Returns Page$")
    public void VerifyInternationReturnsPolicyLink() {
        Clicks.click("easy_returns.international_returns_policy_link");
        try {
            WebDriver driver = WebDriverManager.getWebDriver();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            Assert.assertTrue("International Returns policy link did not navigate to the expected page", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/5362"));
        } catch (DriverNotInitializedException e) {
            log.error("Error in getWebDriver: " + e);
        }
        log.info("Successfully navigated to International Returns Policy Page");
    }


    @And("^I select return items button on \"(OH|OD)\" page$")
    public void I_select_return_items_button_on_page(String pageName) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        byte var4 = -1;
        switch (pageName.hashCode()) {
            case 2517:
                if (pageName.equals("OD")) {
                    var4 = 1;
                }
                break;
            case 2521:
                if (pageName.equals("OH")) {
                    var4 = 0;
                }
        }

        switch (var4) {
            case 0:
                Clicks.click("home.goto_order_status");
                returnsPage.findOrderInDateRange(this.returnOrderDetails.getString("order_number"));
                returnsPage.returnOrder(this.returnOrderDetails.getString("order_number"));
                break;
            case 1:
                Assert.assertFalse("ERROR - DATA : Order test data is not present in environment!!", Elements.elementPresent("order_status.error_message"));
                Clicks.click("order_details.return_items");
                break;
            default:
                Assert.fail("Invalid page name: " + pageName);
        }
    }

    @Then("^I should see \"([^\"]*)\" return error message$")
    public void iShouldSeeReturnErrorMessage(String expectedReturnErrorCopy) {
        Wait.untilElementPresent("return_error.return_error_copy");
        shouldBeOnPage("return_error");
        Assert.assertTrue("ERROR - APP: Return error copy is not updated ", Elements.getText("return_error.return_error_copy").equals(expectedReturnErrorCopy));
    }

    @And("^I should see following return instruction in submit page$")
    public void iShouldSeeFollowingReturnInstructionInSubmitPage(List<String> message) throws Throwable {
        Assert.assertTrue("Return Instruction message could not matched.", Elements.getText("return_submit.return_instruction_header").equals(message.get(0)));

    }

    @And("^I select any fob from the header$")
    public void iSelectAnyFobFromTheHeader() throws Throwable {
        Home.selectRandomCategory();
        log.error("Selected fob from header");
    }

    @Then("^I should be navigated to corresponding page$")
    public void iShouldBeNavigatedToCorrespondingPage() throws Throwable {
        Assert.assertFalse("should not be on Return confirmation page", WebDriverManager.getCurrentUrl().contains("/service/return/confirmation"));
        log.error(WebDriverManager.getCurrentUrl() + " does not contains /service/return/confirmation Url");
    }

    @Then("^I should see footer section which is same as my_account page$")
    public void iShouldSeeFooterSectionWhichIsSameAsMy_accountPage() throws Throwable {
        Assert.assertTrue("Footer is not same as my account page", Elements.elementPresent("return_confirmation.footer_menu_container"));
        log.error("Footer is same as my account page");
    }

    @And("^I keep the order details from OD page$")
    public void iKeepTheOrderDetailsFromODPage() throws Throwable {
        Wait.untilElementPresent("order_details.order_number");
        orderHeaderDetails_fromODPage.put("orderNumber", Elements.findElement("order_details.order_number").getText().toString());
        if (macys())
            orderHeaderDetails_fromODPage.put("orderDate", Elements.findElement("order_details.date").getText().toString());
        else
            orderHeaderDetails_fromODPage.put("orderDate", Elements.findElement("order_details.order_date").getText().toString());
    }

    @Then("^I verify order header in return \"(submit|confirmation)\" page$")
    public void iVerifyOrderHeaderInReturnPage(String pageName) throws Throwable {
        Assert.assertTrue(Elements.findElement("return_submit.order_number").getText().equals(orderHeaderDetails_fromODPage.get("orderNumber")));
        Assert.assertTrue(Elements.findElement("return_submit.order_date").getText().contains(orderHeaderDetails_fromODPage.get("orderDate").toString()));
        log.info("order details page header is equal to current page header");
    }

    @And("^I should see error message as zipcode not valid:$")
    public void iShouldSeeErrorMessageAsZipcodeNotValid(List<String> errorMessage) throws Throwable {
        Wait.untilElementPresent("return_selection.pickup_zip_code_message");
        String actualText=Elements.getText("return_selection.pickup_zip_code_message").replace("\n", "");
        Assert.assertTrue("Zip code error message is not validated.",actualText.equals(errorMessage.get(0)));
    }

    @Then("^I should see label as how_it_works$")
    public void iShouldSeeLabelAsHow_it_works() throws Throwable {
        Assert.assertTrue("", Elements.elementPresent("return_selection.how_it_work"));
        Wait.untilElementPresent("return_selection.how_it_work");
        Clicks.click("return_selection.how_it_work");
    }

    @And("^I click on visit_our_FAQ_ page$")
    public void iClickOnVisit_our_FAQ_Page() throws Throwable {
        Wait.untilElementPresent("return_selection.visit_our_faq_page_link");
        Clicks.click("return_selection.visit_our_faq_page_link");
        Wait.forPageReady();
        switchWindow(1);
        if (macys())
            Assert.assertTrue("User should be directed to Pick up page-FAQ", WebDriverManager.getWebDriver().getCurrentUrl().contains("app/answers/detail/a_id/5162"));
        else
            Assert.assertTrue("User should be directed to Pick up page-FAQ", WebDriverManager.getWebDriver().getCurrentUrl().contains("app/answers/detail/a_id/5163"));
        log.info("Return Pick up page-FAQ");
    }

    @Then("^I should see pick address as default address by default with success message$")
    public void iShouldSeePickAddressAsDefaultAddressByDefaultWithSuccessMessage(List<String> message) throws Throwable {
        Wait.untilElementPresent("return_selection.pickup_message");
        Assert.assertTrue("ERROR - APP: Pickup Message for default address is not as expected", Elements.getText("return_selection.pickup_message").equals(message.get(0)));
        log.info("Pickup Message for default address is correct");
    }

    @And("^I should see time and date options to schedule pickup$")
    public void iShouldSeeTimeAndDateOptionsToSchedulePickup() throws Throwable {
        Wait.untilElementPresent("return_selection.scheduled_date");
        Assert.assertTrue("ERROR - APP: In Schedule pickup info section, schedule date field is missing", Elements.elementPresent("return_selection.scheduled_date"));
        Assert.assertTrue("ERROR - APP: In Schedule pickup info section , schedule time field is missing", Elements.elementPresent("return_selection.scheduled_time"));
    }

    /*@And("^I should see fields below should not exceed character length specified$")
    public void iShouldSeeFieldsBelowShouldNotExceedCharacterLengthSpecified(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        String length;
        for (int i = 1; i<table.getGherkinRows().size(); i++) {
            String fieldName = table.getGherkinRows().get(i).getCells().get(0);
            switch (fieldName) {
                case "phone_number": {
                    length = table.getGherkinRows().get(i).getCells().get(1);
                    String maxLength= Elements.getElementAttribute("return_selection.phone_number","maxlength");
                    Assert.assertTrue("Expected length of "+fieldName+" is not same with actual length",maxLength.equals(length));
                    log.info(fieldName+" Text box length is "+maxLength);
                    break;
                }
                case "special_instructions": {
                    length = table.getGherkinRows().get(i).getCells().get(1);
                    String maxLength= Elements.getElementAttribute("return_selection.special_instructions","maxlength");
                    Assert.assertTrue("Expected length of "+fieldName+" is not same with actual length",maxLength.equals(length));
                    log.info(fieldName+" Text box length is "+maxLength);
                    break;
                }
                case  "pickup_zipcode":
                {
                    length = table.getGherkinRows().get(i).getCells().get(1);
                    String maxLength= Elements.getElementAttribute("return_selection.pickup_zipcode","maxlength");
                    Assert.assertTrue("Expected length of "+fieldName+" is not same with actual length",maxLength.equals(length));
                    log.info(fieldName+" Text box length is "+maxLength);
                    break;
                }
                case "address_line_one":
                {
                    length = table.getGherkinRows().get(i).getCells().get(1);
                    String maxLength= Elements.getElementAttribute("return_selection.address_line_one","maxlength");
                    Assert.assertTrue("Expected length of "+fieldName+" is not same with actual length",maxLength.equals(length));
                    log.info(fieldName+" Text box length is "+maxLength);
                    break;
                }
                case "address_line_two":
                {
                    length = table.getGherkinRows().get(i).getCells().get(1);
                    String maxLength= Elements.getElementAttribute("return_selection.address_line_two","maxlength");
                    Assert.assertTrue("Expected length of "+fieldName+" is not same with actual length",maxLength.equals(length));
                    log.info(fieldName+" Text box length is "+maxLength);
                    break;
                }
            }
        }
    }*/

    @When("^I navigate to easy return page$")
    public void iNavigateToEasyReturnPage() throws Throwable {
        if(!onPage("easy_returns")) {
            String host = WebDriverManager.getCurrentUrl();
            if (host.contains("/?utm_campaign=disable_all"))
                host = host.replaceAll(Pattern.quote("/?utm_campaign=disable_all"), "/");
            String return_to_url = host + "service/easyreturn";
            Navigate.visit(return_to_url);
            log.info("Navigated to " + return_to_url);
            Wait.forPageReady();
            Utils.threadSleep(7000, "Waiting for Easy Return page loading");
            shouldBeOnPage("easy_returns");
        }
        else {
            Navigate.browserRefresh();
        }
    }

    @When("^I lookup with \"([^\"]*)\" order with gift return on OH page$")
    public void iLookupWithOrderWithGiftReturnonOHPage(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
        if (!onPage("order_status")) {
            Assert.fail("User not redirected to order status page!!");
        }
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.lookupOrderByZip(returnOrderDetails);
        Wait.forPageReady();
    }


    @Then("^I should see unit price for line item$")
    public void iShouldSeeUnitPriceForLineItem() throws Throwable {
        String price =Elements.getText("order_details.price_lineItem").split("\n")[1].replace("$","");
        String Quantity=Elements.getText("order_details.quantity_lineItem").split("\n")[1];
        float f= Float.parseFloat(price);
        int i= (int)f;
        int itemQuantity=Integer.parseInt(Quantity);
        int actual_SubTotal=i * itemQuantity;
        Clicks.click("order_details.expand_orderSubTotal");
        Wait.untilElementPresent("order_details.merchandise_total");
        String merchandiseTotal=Elements.findElement("order_details.merchandise_total").getText().replace("$","");
        float ff= Float.parseFloat(merchandiseTotal);
        int expectedTotal= (int)ff;
        Assert.assertTrue("Merchandise Total and Actual subtotal is not matching",expectedTotal == actual_SubTotal);
        log.info("Verified Merchandise Total and Actual subtotal is matching");

    }

    @Then("^I see No radio button is selected by default in Gift Return section$")
    public void iSeeNoRadioButtonIsSelectedByDefaultInGiftReturnSection() throws Throwable {
        Wait.untilElementPresent("order_status.no_radio_button");
        WebElement giftRadioButtonNo=null;
        WebElement giftRadioButtonYes=null;
        if(macys()) {
            giftRadioButtonNo = Elements.findElement("order_status.no_radio_button");
            giftRadioButtonYes = Elements.findElement("order_status.yes_radio_button");
        } else
        {
            giftRadioButtonNo = Elements.findElement("order_status.no_radio_button_selection");
            giftRadioButtonYes = Elements.findElement("order_status.yes_radio_button_selection");
        }
        Assert.assertTrue("No Radio button should be selected by default", giftRadioButtonNo.isSelected());
        Assert.assertFalse("Yes Radio button should not be selected by default", giftRadioButtonYes.isSelected());
        log.info("Verified that the No Radio button for gift returns is selected by default");
    }

    @And("^I should see (No|Yes) radio button is selected$")
    public void iShouldSeeRadioButtonIsSelected(String gift_option) throws Throwable {
        WebElement giftRadioButtonNo=null;
        WebElement giftRadioButtonYes=null;
        switch(gift_option)
        {
            case "No":
                if(macys())
                    giftRadioButtonNo = Elements.findElement("order_status.no_radio_button");
                else
                    giftRadioButtonNo = Elements.findElement("order_status.no_radio_button_selection");
                Assert.assertTrue("No Radio button should be selected", giftRadioButtonNo.isSelected());
                log.info("No radio button is selected");
                break;
            case "Yes":
                if(macys())
                    giftRadioButtonYes = Elements.findElement("order_status.yes_radio_button");
                else
                    giftRadioButtonYes = Elements.findElement("order_status.yes_radio_button_selection");
                Assert.assertTrue("Yes Radio button should be selected", giftRadioButtonYes.isSelected());
                log.info("Yes radio button is selected");
                break;
        }
    }

    @Then("^I select gift option from Gift Return Section$")
    public void iSelectGiftOptionFromGiftReturnSection() throws Throwable {
        Wait.untilElementPresent("order_status.yes_radio_button");
        Clicks.click("order_status.yes_radio_button");
        log.info("Selected yes radio button");
    }

    @When("^I navigate to confirmation page using \"([^\"]*)\" order with gift return$")
    public void iNavigateToConfirmationPageUsingOrderWithGiftReturn(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
        if (!onPage("order_status")) {
            Assert.fail("User not redirected to order status page!!");
        }
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.lookupOrderByZip(returnOrderDetails);
        Wait.forPageReady();
        Wait.untilElementPresent("order_details.return_items");
        Clicks.click("order_details.return_items");
        I_select_items_and_continue_to_submit_page();
        Wait.forPageReady();
        I_navigate_to_return_confirmation_page();
        if (!onPage("return_confirmation")) {
            Assert.fail("User not redirected to order status page!!");
        }
    }

    @And("^I navigate to order details page with gift return$")
    public void iNavigateToOrderDetailsPageWithGiftReturn() throws Throwable {
        Clicks.click("home.goto_order_status");
        Wait.forPageReady();
        if (!onPage("order_status")) {
            Assert.fail("User is not redirected to order status page!!");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        new ReturnService().deleteReturnRecord(orderNumber);
        if (!onPage("order_status")) {
            Assert.fail("User not redirected to order status page!!");
        }
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.lookupOrderByZip(returnOrderDetails);
        Wait.forPageReady();
    }

    @Then("^I should see the order history help modal$")
    public void iShouldSeeTheOrderHistoryHelpModal() throws Throwable {
      Assert.assertTrue("Help modal element should be visible",Elements.elementPresent("order_status.help_modal")  );
      log.info("Help modal element is visible");
    }

    @And("^I verify the following text on the Order history page modal$")
    public void iVerifyTheFollowingTextOnTheOrderHistoryPageModal(List<String> message) throws Throwable {
       Clicks.click("order_status.help_modal");
       log.info("Clicked on Modal");
       Wait.untilElementPresent("order_status.help_modal_text");
       Assert.assertTrue("help modal text should be same", Elements.getText("order_status.help_modal_text").equals(message.get(0)));
       log.info(Elements.getText("order_status.help_modal_text")+" is equal to "+message);
    }

    @Then("^I should see \"([^\"]*)\" same as in the order details page$")
    public void iShouldSeeSameAsInTheOrderDetailsPage(String header_element) throws Throwable {
       switch(header_element)
       {
           case "order number":
               Assert.assertTrue(Elements.findElement("return_submit.order_number").getText().equals(orderHeaderDetails_fromODPage.get("orderNumber")));
               log.info("order date in confirmation page "+Elements.getText("return_submit.order_number")+" is matched with selection page order date "+orderHeaderDetails_fromODPage.get("orderNumber").toString());
               break;
           case "order date":
               Assert.assertTrue(Elements.getText("return_submit.order_date").contains(orderHeaderDetails_fromODPage.get("orderDate").toString()));
               log.info("order date in confirmation page "+Elements.getText("return_submit.order_date")+" is matched with selection page order date "+orderHeaderDetails_fromODPage.get("orderDate").toString());
               break;
       }
    }

    @Then("^I verify error messages upto (\\d+) attempts$")
    public void iVerifyErrorMessagesUptoAttempts(int number, List<String> errorMessage) throws Throwable {
        if(number>1)
            number--;
        for (int i = 1; i <= number; i++) {
            Wait.forPageReady();
            if (onPage("order_status")) {
                Wait.untilElementPresent("order_status.error_message");
                Assert.assertTrue("Error message should match", Elements.getText("order_status.error_message").equals(errorMessage.get(0)));
                log.info("Actual error message is " + Elements.getText("order_status.error_message"));
                Clicks.click(Elements.element("order_status.view_order_details"));
            }
            else if(onPage("easy_returns")){
                Wait.untilElementPresent("easy_returns.error_message");
                Assert.assertTrue("Error message should match", Elements.getText("easy_returns.error_message").equals(errorMessage.get(0)));
                Clicks.click(Elements.element("easy_returns.view_order_details"));
            }
        }
    }

    @When("^I lookup with incorrect order number and valid (emailaddress|phonenumber) in (OH|EasyReturns) page$")
    public void iLookupWithIncorrectOrderNumberAndValidPhonenumberInPage(String emailPhone, String page_name, DataTable table) throws Throwable {
        String page="";
        String orderNum= "";
        switch (emailPhone) {
            case "phonenumber":
                for (int i = 1; i < table.raw().size(); i++)
                {
                    List<List<String>> dataLayer = table.raw();
                    String orderType = dataLayer.get(i).get(0);
                    if(page_name.equals("OH"))
                    {   page="order_status";
                        orderNum = Utils.getOrderNumber(orderType);
                    }
                    else
                    {
                        page="easy_returns";
                        HashMap<String, String> order = new HashMap<>();
                        order.put("return_order", orderType);
                        returnOrderDetails = Utils.getReturnOrders(order);
                        if (returnOrderDetails == null) {
                            Assert.fail("No order details found");
                        }
                        if (!returnOrderDetails.has("order_number")) {
                            Assert.fail("Could not find order number");
                        }
                        orderNum = returnOrderDetails.getString("order_number");
                    }


                    String phoneAreaCode = dataLayer.get(i).get(1);
                    String phoneExchange = dataLayer.get(i).get(2);
                    String phoneSubscriber = dataLayer.get(i).get(3);
                    Wait.untilElementPresent(Elements.element(page+".view_order_details"));
                    TextBoxes.typeTextbox(Elements.element(page+".order_number"), orderNum);
                    TextBoxes.typeTextbox(Elements.element(page+".phone_area_code"), phoneAreaCode);
                    TextBoxes.typeTextbox(Elements.element(page+".phone_exchange"), phoneExchange);
                    TextBoxes.typeTextbox(Elements.element(page+".phone_subscriber"), phoneSubscriber);
                    Clicks.click(Elements.element(page+".view_order_details"));
                }
                break;
            case "emailaddress":
                for (int i = 1; i < table.raw().size(); i++)
                {
                    List<List<String>> dataLayer = table.raw();
                    String orderType = dataLayer.get(i).get(0);
                    if(page_name.equals("OH"))
                    {   page="order_status";
                        orderNum = Utils.getOrderNumber(orderType);
                    }
                    else
                    {
                        page="easy_returns";
                        HashMap<String, String> order = new HashMap<>();
                        order.put("return_order", orderType);
                        returnOrderDetails = Utils.getReturnOrders(order);
                        if (returnOrderDetails == null) {
                            Assert.fail("No order details found");
                        }
                        if (!returnOrderDetails.has("order_number")) {
                            Assert.fail("Could not find order number");
                        }
                        orderNum = returnOrderDetails.getString("order_number");
                    }
                    String email = dataLayer.get(i).get(1);
                    log.info("Look for order by order id and email");
                    Wait.untilElementPresent(Elements.element(page+".view_order_details"));
                    TextBoxes.typeTextbox(Elements.element(page+".order_number"), orderNum);
                    TextBoxes.typeTextbox(Elements.element(page+".email"), email);
                    Clicks.click(Elements.element(page+".view_order_details"));
                }
        }
    }

    @When("^I lookup with valid order number and incorrect (emailaddress|phonenumber) in (OH|EasyReturns) page$")
    public void iLookupWithValidOrderNumberAndIncorrectEmailaddressInPage(String emailPhone, String page_name, DataTable table) throws Throwable {
        String orderNum= "";
        String orderType="";
        String page="";
        switch (emailPhone) {
            case "phonenumber":
                for (int i = 1; i < table.raw().size(); i++)
                {
                    List<List<String>> dataLayer = table.raw();
                    orderType = dataLayer.get(i).get(0);
                    if(page_name.equals("OH"))
                    {   page="order_status";
                        orderNum = Utils.getOrderNumber(orderType);
                    }
                    else
                    {
                        page="easy_returns";
                        HashMap<String, String> order = new HashMap<>();
                        order.put("return_order", orderType);
                        returnOrderDetails = Utils.getReturnOrders(order);
                        if (returnOrderDetails == null) {
                                Assert.fail("No order details found");
                            }
                        if (!returnOrderDetails.has("order_number")) {
                                Assert.fail("Could not find order number");
                            }
                        orderNum = returnOrderDetails.getString("order_number");
                    }
                    String phoneAreaCode = dataLayer.get(i).get(1);
                    String phoneExchange = dataLayer.get(i).get(2);
                    String phoneSubscriber = dataLayer.get(i).get(3);
                    Wait.untilElementPresent(Elements.element(page+".order_number"));
                    TextBoxes.typeTextbox(Elements.element(page+".order_number"), orderNum);
                    TextBoxes.typeTextbox(Elements.element(page+".phone_area_code"), phoneAreaCode);
                    TextBoxes.typeTextbox(Elements.element(page+".phone_exchange"), phoneExchange);
                    TextBoxes.typeTextbox(Elements.element(page+".phone_subscriber"), phoneSubscriber);
                    Clicks.click(Elements.element(page+".view_order_details"));
                }
                break;
            case "emailaddress":
                for (int i = 1; i < table.raw().size(); i++)
                {
                    List<List<String>> dataLayer = table.raw();
                    orderType = dataLayer.get(i).get(0);
                    if(page_name.equals("OH"))
                    {
                        page="order_status";
                        orderNum = Utils.getOrderNumber(orderType);
                    }
                    else
                    {
                        page="easy_returns";
                        HashMap<String, String> order = new HashMap<>();
                        order.put("return_order", orderType);
                        returnOrderDetails = Utils.getReturnOrders(order);
                        if (returnOrderDetails == null) {
                            Assert.fail("No order details found");
                        }
                        if (!returnOrderDetails.has("order_number")) {
                            Assert.fail("Could not find order number");
                        }
                        orderNum = returnOrderDetails.getString("order_number");
                    }
                    String email = dataLayer.get(i).get(1);
                    log.info("Look for order by order id and email");
                    Wait.untilElementPresent(Elements.element(page+".order_number"));
                    TextBoxes.typeTextbox(Elements.element(page+".order_number"), orderNum);
                    TextBoxes.typeTextbox(Elements.element(page+".email"), email);
                    Clicks.click(Elements.element(page+".view_order_details"));
                }
        }
    }

    @Then("^I should see FAQ widget with below content:$")
    public void iShouldSeeFAQWidgetWithBelowContent(List<String> faqLinks) throws Throwable {
        Wait.forPageReady();
        if(macys()) {
            Wait.untilElementPresent("order_status.stillhavequestions_link");
            Assert.assertTrue("Featured help topics link is not displaying on Order History page", Elements.getText("order_status.featuredhelpedtopics_link").trim().equals(faqLinks.get(0)));
            log.info("FAQ widget text is "+Elements.getText("order_status.featuredhelpedtopics_link").trim());
            Assert.assertTrue("", Elements.getText("order_status.stillhavequestions_link").trim().contains(faqLinks.get(1)));
            log.info("FAQ widget text is "+Elements.getText("order_status.stillhavequestions_link").trim());
        }
        else
        {
            Wait.untilElementPresent("order_status.popular_order_question");
            Assert.assertTrue("POPULAR ORDER QUESTIONS is not displaying on Order History page", Elements.getText("order_status.popular_order_question").trim().equals(faqLinks.get(0)));
            log.info("FAQ widget text is "+faqLinks.get(0));
            Assert.assertTrue("STILL HAVE A QUESTION? is not displaying on Order History page", Elements.getText("order_status.still_have_question").trim().contains(faqLinks.get(1)));
            log.info("FAQ widget text is "+faqLinks.get(1));
            Assert.assertTrue(" and we'll be happy to assist you with questions about your order is not displaying on Order History page", Elements.getText("order_status.happy_to_assist_you").trim().contains(faqLinks.get(2)));
            log.info("FAQ widget text is "+faqLinks.get(2));
        }
        log.info("Verified faq widget in order status page");
    }

    @And("^I verify the faq links in FAQ widget$")
    public void iVerifyTheFaqLinksInFAQWidget() throws Throwable {
        Wait.untilElementPresent("order_status.see_all");
        Clicks.click("order_status.see_all");
        log.info("Selected see all link");
        Wait.forPageReady();
        Navigate.browserBack();
        log.info("Selected browser back button");
        Wait.forPageReady();
        Wait.untilElementPresent("order_status.contact_us");
        Clicks.click("order_status.contact_us");
        log.info("Selected contact us link");
        Wait.forPageReady();
        Navigate.browserBack();
        log.info("Selected browser back button");
        Wait.forPageReady();
    }

    @When("^I lookup with valid order number and valid emailaddress in (OH|EasyReturns) page$")
    public void iLookupWithValidOrderNumberAndValidEmailaddressInPage(String page_name,DataTable table) throws Throwable {
        String page="";
        String orderType="";
        String orderNum="";
        for (int i = 1; i < table.raw().size(); i++)
        {
            List<List<String>> dataLayer = table.raw();
            orderType = dataLayer.get(i).get(0);
            if(page_name.equals("OH"))
            {
                page="order_status";
                orderNum = Utils.getOrderNumber(orderType);
            }
            else
            {
                page="easy_returns";
                HashMap<String, String> order = new HashMap<>();
                order.put("return_order", orderType);
                returnOrderDetails = Utils.getReturnOrders(order);
                if (returnOrderDetails == null) {
                    Assert.fail("No order details found");
                }
                if (!returnOrderDetails.has("order_number")) {
                    Assert.fail("Could not find order number");
                }
                orderNum = returnOrderDetails.getString("order_number");
            }

            String email = dataLayer.get(i).get(1);
            Wait.untilElementPresent(Elements.element(page+".order_number"));
            TextBoxes.typeTextbox(Elements.element(page+".order_number"), orderNum);
            TextBoxes.typeTextbox(Elements.element(page+".email"), email);
            Clicks.click(Elements.element(page+".view_order_details"));
            log.info("Clicked on order details button");
            Wait.forPageReady();
        }
    }

    @Then("^I should navigate to shopapp \"([^\"]*)\" page$")
    public void iShouldNavigateToShopappPage(String redirect_page) throws Throwable {
        if(redirect_page.equals("OD"))
            redirect_page="order_details";
        if(redirect_page.equals("OH"))
            redirect_page="order_status";
        shouldBeOnPage(redirect_page);
    }

    @Then("^I navigate to order history page using link in breadcrumb in OD page$")
    public void iNavigateToOrderHistoryPageUsingLinkInBreadcrumbInODPage() throws Throwable {
        Wait.untilElementPresent("order_status.order_history_breadcrumb_link");
        Clicks.click(Elements.element("order_status.order_history_breadcrumb_link"));
        log.info("Clicked on order history from  breadcrumb link");
        Wait.forPageReady();
    }


    @And("^I navigate to the order details page as a signed in user$")
    public void iNavigateToTheOrderDetailsPageAsASignedInUser() throws Throwable {
        iNavigateToOrderHistoryPage();
        ReturnsPage returnObj = new ReturnsPage();
        pausePageHangWatchDog();
        returnObj.findOrderInDateRange(orderNum);
        Clicks.click("order_status.order_details_button");
    }

    @Then("^I should see additional fee$")
    public void iShouldSeeAdditionalFee() throws Throwable {
        shouldBeOnPage("order_details");
        Wait.untilElementPresent("order_details.tux_subtotal_section");
        Clicks.click("order_details.tux_subtotal_section");
        Wait.forPageReady();
        Assert.assertTrue("ERROR - DATA: Additional fee is not displayed",Elements.elementPresent("order_details.additional_fee_text"));
    }

    @And("^I navigate to OH page by creating a new profile$")
    public void iNavigateToOHPageByCreatingANewProfile() throws Throwable {
        MyAccountSteps myaccountsteps=new MyAccountSteps();
        myaccountsteps.iCreateANewProfile();
        Clicks.click("home.goto_order_status");
        Wait.forPageReady();
        log.info("Navigated to Order History page");
    }

    @Then("^I should verify orders displayed for \"([^\"]*)\" date range$")
    public void iShouldVerifyOrdersDisplayedForDateRange(String period) throws Throwable {
        List<String> dropDownValues = (macys() ? DropDowns.getAllValues("order_status.order_date_range") : DropDowns.getAllCustomValues("order_status.order_date_range_list", "order_status.order_date_range_options"));
        if (dropDownValues == null) {
            Assert.fail("Not able to fetch drop down values");
        }
        for (int index = 1; index < dropDownValues.size(); index++) {
            if (macys()) {
                DropDowns.selectByText("order_status.order_date_range", dropDownValues.get(index));
                log.info("Dropdown value selected");
                break;
            } else {
                DropDowns.selectCustomText("order_status.order_date_range_list", "order_status.order_date_range_options", dropDownValues.get(index));
                log.info("Dropdown value selected");
                break;
            }
        }
            Wait.secondsUntilElementNotPresent("order_status.period_selected_label", 20);
            Assert.assertTrue("Period selected label is not displaying as expecspecifiedted", Elements.elementPresent("order_status.order_period_title"));
            Assert.assertTrue("Order Status label is not displaying as expected", Elements.elementPresent("order_status.order_status_text"));
            log.info("Verified Cancelled order for data range ");
    }


    @And("^I associate following orders to the profile$")
    public void iAssociateFollowingOrdersToTheProfile(List<String> orders) throws Throwable {
        for (String profileOrder : orders) {
            orderNum = Utils.getOrderNumber(profileOrder);
            ReturnService returns = new ReturnService();
            if (returns.orderExistsByOrderNumber(orderNum)) {
                returns.deleteOrderRecord(orderNum);
            }
            returns.insertOrderByOrderNumber(orderNum, emailID);
        }
    }

    @When("^I sign In with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public static UserProfile iSignInWithEmailAndPassword(String email, String password) throws Throwable {
        UserProfile customer = null;
        emailID = email;
        Navigate.visit(RunConfig.url + "/account/signin");
        Wait.forPageReady();
        if (macys()) {
            closeIECertError();
        }
        if (!onPage("my_account") && !signedIn()) {
            customer = TestUsers.getCustomer(null);
            customer.getUser().getProfileAddress().setEmail(emailID);
            customer.getUser().getLoginCredentials().setPassword(password);
            pausePageHangWatchDog();
            TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
            Clicks.javascriptClick("sign_in.sign_in_button");
            Clicks.clickIfPresent("sign_in.close_overlay");
            resumePageHangWatchDog();
            // if this account works, we're good to go
            if (!Wait.secondsUntilElementPresent("sign_in.error_message", safari() || ie() ? 40 : 20)) {
                new MyAccount().setSecurityQuestion();
                if (!MEW()) {
                    Wait.secondsUntilElementPresent("header.goto_my_account_link", 20);
                    Clicks.click("header.goto_my_account_link");
                    if (macys()) {
                        Clicks.clickIfPresent("header.goto_my_account_second_link");
                    }
                }
                //shouldBeOnPage("my_account");
                Thread.sleep(8000);
                CommonUtils.isNewProfileCreated = false;
                log.info("No new profile created");
                return null;
            }
            CreateProfile.createProfile(customer);
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("my_account.add_card_overlay_no_thanks_button", 20);
            Assert.assertTrue("New Profile could not be created", onPage("my_account") || onPage("my_profile"));
            Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
            CommonUtils.isNewProfileCreated = true;
            log.info("New profile created in desktop");
        }
        return customer;
    }

    @Then("^I verify \"([^\"]*)\" tracking details in \"([^\"]*)\" page$")
    public void iVerifyTrackingDetailsInPage(String tracking, String page) throws Throwable {
        ReturnsPage returnObj = new ReturnsPage();
        pausePageHangWatchDog();
        Map trackOrder;
        returnObj.findOrderInDateRange(orderNum);
        Map orderHash = returnObj.getOrder(orderNum);
        Wait.forPageReady();
        switch (page) {
            case "OH":
                shouldBeOnPage("order_status");
                break;
            case "OD":
                if (!onPage("order_details")) {
                    returnObj.clickOrderDetailsButton(orderNum);
                }
                shouldBeOnPage("order_details");
                break;
        }
        trackOrder = returnObj.getTrackInfo(page);

        int count = trackOrder.keySet().size();
        if (count == 0) {
            Assert.fail("There are no trackings");
        }
        if (count == 1) {
            Set list = trackOrder.entrySet();

            Iterator it = list.iterator();
            while (it.hasNext()) {
                log.info("The track values are " + it.next());

            }
        }
    }

    @And("^I should see my account page using \"([^\"]*)\" link in breadcrumb in (OH|OD|SELECTION|SUBMIT|CONFIRMATION) page for signed user$")
    public void iShouldSeeMyAccountPageUsingLinkInBreadcrumbInPageForSignedUser(String link_name, String page) throws Throwable {
            Wait.untilElementPresent("return_confirmation.breadcrumb_link");
            switch (page) {
                case "OD": {
                    Clicks.click(Elements.paramElement("return_confirmation.breadcurmb_link_element",link_name));
                    log.info("Selected breadcrumb link " + link_name + " in order details page");
                    break;
                }
                case "SELECTION": {
                    Clicks.click(Elements.paramElement("return_confirmation.breadcurmb_link_element",link_name));
                    log.info("Selected breadcrumb link " + link_name + " in return selection page");
                    break;
                }
                case "SUBMIT": {
                    Clicks.click(Elements.paramElement("return_confirmation.breadcurmb_link_element",link_name));
                    log.info("Selected breadcrumb link " + link_name + " in return submit page");
                    break;
                }
                case "CONFIRMATION": {
                    Thread.sleep(5000);
                    Clicks.hoverForSelection("navigation.goto_my_account");
                    Clicks.click(Elements.paramElement("return_confirmation.breadcurmb_link_element",link_name));
                    log.info("Selected breadcrumb link " + link_name + " in order confirmation page");
                    break;
                }
            }
            Wait.forPageReady();
            if (!onPage("my_account")) {
                Assert.fail("User is not redirected to order status page!!");
            }

    }

    @And("^I initiate return and navigate to order details page for guest user$")
    public void iInitiateReturnAndNavigateToOrderDetailsPageForGuestUser() throws Throwable {
        I_select_return_items_button_in_page("OD");
        IShouldNavigateToReturnSelectionPage();
        I_select_items_and_continue_to_submit_page();
        I_should_nabvigate_to_return_submit_page();
        iSelectReturnSubmitFromSubmitPage();
        Wait.untilElementPresent("home.goto_order_status");
        Clicks.click("home.goto_order_status");
        Wait.forPageReady();
        if (!onPage("order_status")) {
            Assert.fail("User is not redirected to order status page!!");
        }
        new ReturnsPage().lookupOrderByEmail(returnOrderDetails);
        log.info("Navigated to details page using look up using email address");
    }

    @And("^I set all the return status in db$")
    public void iSetAllTheReturnStatusInDb() throws Throwable {
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returnService = new ReturnService();
        Map return_shipment_details = returnService.getReturnInitiatedOrder(orderNumber);
        log.info("Return Shipment details :"+return_shipment_details);
        CommonUtils.updateAllReturnStatus(orderNumber);
    }

    @And("^I verify that return status items should not contain gift box$")
    public void iVerifyThatReturnStatusItemsShouldNotContainGiftBox() throws Throwable {
        ReturnsPage returns = new ReturnsPage();
        List<Map<String, Object>> orderDetails = returns.getOrderDetails();
        log.info(orderDetails.toString());
        for(Map<String,Object> order : orderDetails){
            if(order.get("headerStatus").toString().toLowerCase().contains("return")){
                ArrayList<Map<String,Object>> lineItemDetails =  (ArrayList<Map<String,Object>>)order.get("lineItems");
                if(!lineItemDetails.isEmpty() && lineItemDetails.size()>0){
                    for(Map<String,Object> map : lineItemDetails){
                        Assert.assertTrue(map.get("giftBox").equals("No"));
                    }
                }
            }
        }
    }

    @When("^I navigate to return selection page using \"([^\"]*)\" order as a \"([^\"]*)\" user using gift lookup$")
    public void iNavigateToReturnSelectionPageUsingOrderAsAUserUsingGiftLookup(String orderType, String userType) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        returnsPage.navigateToSelectionPageForZipCodeLookup(orderType, userType.equals("guest"));
    }

    @And("^I see deeplink is clicked in email$")
    public void iSeeDeeplinkIsClickedInEmail() throws Throwable {
        Assert.fail("Step definition not completed yet due to email dependency");
    }

    @Then("^I should see pick address as default address by default with success message:$")
    public void iShouldSeePickAddressAsDefaultAddressByDefaultWithSuccessMessage(DataTable table) throws Throwable {
        String value = table.asList(String.class).get(0);

        Wait.secondsUntilElementPresent("return_selection.pickup_message", 10);
        Assert.assertTrue("ERROR - APP: Pickup Message for default address in not as expected",Elements.findElement("return_selection.pickup_message").getText().equals(value.toString()));

    }


    @And("^I should see fields below should not exceed character length specified$")
    public void iShouldSeeFieldsBelowShouldNotExceedCharacterLengthSpecified(DataTable table) throws Throwable {
        Map<String, String> valueMap = table.asMap(String.class, String.class);
        for(String field : valueMap.keySet()){
            if(field.equals("field_name")){
                continue;
            }
            log.info("Field maxlength:"+Elements.findElement("return_selection."+field).getAttribute("maxlength"));
            Assert.assertTrue(Elements.findElement("return_selection."+field).getAttribute("maxlength").equals(valueMap.get(field)));
        }
    }

    @And("^I associate (\\d+) orders to the profile$")
    public void iAssociateOrdersToTheProfile(int no_of_orders) throws Throwable {
        try {
            JSONArray orders = null;
            JSONArray ordersByBrand;
            File returnsFile = Utils.getResourceFile("return_orders.json");
            String jsonTxt = Utils.readTextFile(returnsFile);
            JSONObject json = new JSONObject(jsonTxt);
            if (macys()) {
                ordersByBrand = (JSONArray) json.get("macys");
            } else {
                ordersByBrand = (JSONArray) json.get("bloomingdales");
            }

            if (dsg()) {
                log.info("Data center is DSG");
                for (int i = 0; i < ordersByBrand.length(); i++) {
                    JSONObject obj = (JSONObject) ordersByBrand.get(i);
                    orders = (JSONArray) obj.get("dsg");
                    if (orders != null) { break; }
                }
            } else if (s2b()) {
                log.info("Data center is S2B");
                for (int i = 0; i < ordersByBrand.length(); i++) {
                    JSONObject obj = (JSONObject) ordersByBrand.get(i);
                    orders = (JSONArray) obj.get("s2b");
                    if (orders != null) { break; }
                }
            }
            Set ordersNumber = new HashSet();
            for (int i = 0; i < (orders != null ? orders.length() : 0); i++) {
                JSONObject order = orders.getJSONObject(i);
               String orderType =  order.getString("order_number");
                ordersNumber.add(orderType);
                if(ordersNumber.size()==no_of_orders)
                    break;
            }
            emailID=  TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail();
                Iterator itr = ordersNumber.iterator();
                while(itr.hasNext())
                {
                    ReturnService returns = new ReturnService();
                    orderNum=itr.next().toString();
                    if (returns.orderExistsByOrderNumber(orderNum)) {
                        returns.deleteOrderRecord(orderNum);
                    }
                    returns.insertOrderByOrderNumber(orderNum, emailID);
            }
        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
        }
    }

    @Then("^I verify the order link redirection in my account page$")
    public void iVerifyTheOrderLinkRedirectionInMyAccountPage() throws Throwable {
        iNavigateToOrderHistoryPage();
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.findOrderInDateRange(orderNum);
        I_verify_order_details_in_OD_page();
    }

    @And("^I select appropriate date range$")
    public void iSelectAppropriateDateRange() throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.findOrderInDateRange(orderNum);
    }

    @Then("^I verify drop down text on the order history page$")
    public void iVerifyDropDownTextOnTheOrderHistoryPage() throws Throwable {
        String expectedText;
        if(macys())
        {
            Wait.untilElementPresent("order_status.order_date_range");
            expectedText =DropDowns.getSelectedValue(Elements.element("order_status.order_date_range"));
        }
        else
        {
            Wait.untilElementPresent("order_status.order_date_range_list");
            expectedText = Elements.getText("order_status.order_date_range_list");
        }
        Assert.assertTrue("Drop down text on order hisgory page is not correct.", expectedText.equals("last 60 days"));
    }

    @Then("^I should see Gift Return Section$")
    public void iShouldSeeGiftReturnSection() throws Throwable {
        Wait.untilElementPresent("easy_returns.gift_return_section");
        Assert.assertTrue("Return Gift Section should be visible on Easy Return page.",Elements.elementPresent("easy_returns.gift_return_section"));
        log.info("Return Gift Section is visible");
    }

    @And("^I should see email address or phone number text fields$")
    public void iShouldSeeEmailAddressOrPhoneNumberTextFields() throws Throwable {
        Wait.untilElementPresent("easy_returns.email");
        Assert.assertTrue("Return Gift Email address field should be visible on Easy Return page.",Elements.elementPresent("easy_returns.email"));
        Assert.assertTrue("Return Gift Phone number fields should be visible on Easy Return page.",Elements.elementPresent("easy_returns.phone_number"));
        log.info("email address and phone number fields are visible");
    }

    @And("^I verify order field is limitied to (\\d+) characters$")
    public void iVerifyOrderFieldIsLimitiedToCharacters(int lenght) throws Throwable {
        Wait.untilElementPresent("easy_returns.order_number");
        Assert.assertTrue(Elements.findElement("easy_returns.order_number").getAttribute("maxlength").trim().equals(lenght+""));
        log.info("Order number filed is limited to 20 characters");
    }

    @And("^I should see order field only accepts alphanumeric characters$")
    public void iShouldSeeOrderFieldOnlyAcceptsAlphanumericCharacters() throws Throwable {
        Wait.untilElementPresent("easy_returns.order_number");
        String orderMaxLenght = "asd12121244ggs21212121ssasa2";
        TextBoxes.typeTextbox("easy_returns.order_number",orderMaxLenght);
        Assert.assertTrue("ERROR: APP: Text field is not accepting 20 cahracters", Elements.getElementAttribute("easy_returns.order_number","value").trim().length()==(20));
        Assert.assertTrue("ERROR: APP: Text field is not accepting alpha numeric characters", orderMaxLenght.contains(Elements.getElementAttribute("easy_returns.order_number","value")));
        log.info("Order number filed is limited to 20 characters and accepts alphanumeric characters");
    }

    @And("^I enter \"([^\"]*)\" invalid shipped to zipcode$")
    public void iEnterInvalidShippedToZipcode(String invalid_zipcode) throws Throwable {
        Wait.untilElementPresent("easy_returns.zip_code");
        TextBoxes.typeTextbox("easy_returns.zip_code", invalid_zipcode);
        Clicks.click("easy_returns.view_order_details");
    }

    @Then("^I verify length of zipcode$")
    public void iVerifyLengthOfZipcode() throws Throwable {
        Wait.untilElementPresent("easy_returns.zip_code");
        Assert.assertTrue("length of zip code field could not validated.", Elements.getText("easy_returns.zip_code").length()==5);
        log.info("Validated length of zip code field");
    }

    @And("^I should see \"([^\"]*)\" error message on top$")
    public void iShouldSeeErrorMessageOnTop(String gift_return_error_msg) throws Throwable {
        Wait.untilElementPresent("easy_returns.error_message");
        Assert.assertTrue(Elements.getText("easy_returns.error_message")+" is not equal to "+gift_return_error_msg, Elements.getText("easy_returns.error_message").equals(gift_return_error_msg));
        log.info(Elements.getText("easy_returns.error_message")+" is equal to "+gift_return_error_msg);
    }

    @When("^I lookup with \"([^\"]*)\" order on EasyReturns page$")
    public void iLookupWithOrderOnEasyReturnsPage(String orderType) throws Throwable {
        Navigate.browserReset();
        Navigate.visit("easy_returns");
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        orderNum = returnOrderDetails.getString("order_number");
        TextBoxes.typeTextbox(Elements.element("easy_returns.order_number"), orderNum);
        log.info("Entered order number is: " + orderNum);
    }
}
