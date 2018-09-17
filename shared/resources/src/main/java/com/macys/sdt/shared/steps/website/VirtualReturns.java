package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.ReturnService;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class VirtualReturns extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(VirtualReturns.class);
    public int maxTries = 10;
    public JSONObject returnVirtualOrderDetails;
    public Map<String, Object> selectedLineItem, orderItems;
    public Map<String, Object> shipmentFromPage=null;

    @Given("^I have \"([^\"]*)\" order for shipment type \"([^\"]*)\" with \"([^\"]*)\" status$")
    public void i_have_arg_order_for_shipment_type_arg_with_arg_status(String orderType, String shipmentType, String shipmentStatus) throws Throwable {
        JSONArray orders = null;
        JSONArray ordersByBrand;
        File returnsFile = getResourceFile("virtual_returns.json");
        String jsonTxt = Utils.readTextFile(returnsFile);
        JSONObject json = new JSONObject(jsonTxt);
        if (dsg()) {
            logger.info("Data center is DSG");
            ordersByBrand = (JSONArray) json.get("dsg");
            for (int i = 0; i < ordersByBrand.length(); i++) {
                JSONObject obj = (JSONObject) ordersByBrand.get(i);
                orders = (JSONArray) obj.get(orderType);
                if (orders != null) { break; }
            }
        } else if (s2b()) {
            logger.info("Data center is S2B");
            ordersByBrand = (JSONArray) json.get("s2b");
            for (int i = 0; i < ordersByBrand.length(); i++) {
                JSONObject obj = (JSONObject) ordersByBrand.get(i);
                orders = (JSONArray) obj.get(orderType);
                if (orders != null) { break; }
            }
        }
        for (int i = 0; i < orders.length(); i++) {
            if(orders.getJSONObject(i).keySet().contains("shipment_type") && orders.getJSONObject(i).keySet().contains("shipment_status")) {
                if ((StringUtils.equalsIgnoreCase(orders.getJSONObject(i).get("shipment_type").toString().trim(), shipmentType.trim())) && (StringUtils.equalsIgnoreCase(orders.getJSONObject(i).get("shipment_status").toString().trim(), shipmentStatus.trim()))) {
                    returnVirtualOrderDetails = orders.getJSONObject(i);
                    break;
                }
            }
        }
        if (returnVirtualOrderDetails == null) {
            Assert.fail("No order found in JSON File");
        }
        String orderNumber = returnVirtualOrderDetails.get("order_number").toString();
        logger.info("order number=" + orderNumber);
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);

    }


    @When("^I navigated to \"([^\"]*)\" page as a \"([^\"]*)\" user$")
    public void i_navigated_to_arg_page_as_a_arg_user(String pageName, String userType) throws Throwable {
        String orderNumber = returnVirtualOrderDetails.getString("order_number");
        if (userType.equals("signed")) {
            ISelectOrderAsUser("signed");
        }
        if (Elements.elementPresent("home.goto_order_status")) {
            Clicks.click("home.goto_order_status");
        }
        if (!onPage("order_status")) {
            Assert.fail("User not redirected to order status page!!");
        }
        if (userType.equals("signed")) {
            ReturnsPage returnsPage = new ReturnsPage();
            returnsPage.findOrderInDateRange(orderNumber);
            Clicks.clickIfPresent("home.popup_close");
            Wait.forPageReady();
            //returnsPage.returnOrder(orderNumber);
        } else {
            logger.info("Look for order by order id and email");
            ReturnsPage returnsPage = new ReturnsPage();
            for (int index = 0; index < maxTries; index++) {
                returnsPage.lookupOrderByEmail(returnVirtualOrderDetails);
                if (title().contains("Order Details") || title().contains("Order Status")) {
                    break;
                }
            }
        }
        String shipment_type = returnVirtualOrderDetails.getString("shipment_type");
        if(pageName.equals("OD"))
        {
            shouldBeOnPage("order_details");
        }else
            if(pageName.equals("OH"))
            {
                shouldBeOnPage("order_status");
            }
        if(!shipment_type.equals("return_submit"))
            getOrdersDetail(pageName);

    }

    public void getOrdersDetail(String pageName) {
        String shipment_type = returnVirtualOrderDetails.getString("shipment_type").toLowerCase();
        String shipment_status = returnVirtualOrderDetails.getString("shipment_status").toLowerCase();
        ReturnsPage returnsPage = new ReturnsPage();
        if (pageName.equals("OH")) {
            if (shipment_type.equals("replacement") && shipment_status.equals("completed"))
                shipment_type = "replaced";
            if (shipment_type.equals("return") && shipment_status.equals("completed"))
                shipment_type = "returned";
            orderItems = getOrderDetailsfromOHSignedUser();
            List itemsData = (List<Map>) orderItems.get("order_details");
            if (itemsData.size() > 0)
                for (int i = 0; i < itemsData.size(); i++) {
                    List<Map> map = (List<Map>) itemsData.get(i);
                    if (map.size() > 0) {
                        for (int j = 0; j < map.size(); j++)
                        {
                            if (shipment_status.equals("credited"))
                            {
                                if (map.get(j).get("header_status").toString().contains("credited"))
                                {
                                    shipmentFromPage = map.get(j);
                                    return;
                                }
                            }
                            else if (shipment_status.equals("completed"))
                            {
                                if (map.get(j).get("header_status").toString().contains(shipment_type.toLowerCase()))
                                {
                                    shipmentFromPage = map.get(j);
                                    return;
                                }
                            } else {
                                    if (map.get(j).get("header_status").toString().contains(shipment_type.toLowerCase()) && map.get(j).get("header_status").toString().contains(shipment_status.toLowerCase())) {
                                        shipmentFromPage = map.get(j);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
        else {
            orderItems = getOrderDetailsfromOD();
            List itemsData = (List<Map>) orderItems.get("order_details");
                    for (int i = 0; i < itemsData.size(); i++)
                    {
                        List<Map> map = (List<Map>) itemsData.get(i);
                        if (map.size() > 0) {
                            for (int j = 0; j < map.size(); j++) {
                                if (shipment_status.equals("credited"))
                                {
                                    if(map.get(j).get("header_status").toString().contains(shipment_status.toLowerCase()))
                                    {
                                        shipmentFromPage = map.get(j);
                                        return;
                                    }
                                }
                                else if (map.get(j).get("header_status").toString().contains(shipment_type.toLowerCase())&& map.get(j).get("header_status").toString().contains(shipment_status.toLowerCase())) {
                                    shipmentFromPage = map.get(j);
                                    return;
                                }
                            }
                        }
                    }
        }
    }

    @Then("^I (should|should not) see call to action as below on header \"([^\"]*)\"$")
    public void i_arg_see_call_to_action_as_below_on_header(String visibility, String headerMessage) throws Throwable {
        switch (visibility) {
            case "should":
            Assert.assertEquals(headerMessage.toLowerCase(), Elements.getText("order_details.cta_on_header").toLowerCase());
            break;
            case "should not":
                Assert.assertTrue(!(Elements.elementPresent("order_details.cta_on_header")));
                break;
        }
    }

    @And("^I should see reason code for virtual line item$")
    public void i_should_see_reason_code_for_virtual_line_item() throws Throwable {
            Assert.assertTrue("Reason code is not " + returnVirtualOrderDetails.get("reason_code").toString() + " on order details page",StringUtils.containsIgnoreCase(shipmentFromPage.get("item_action").toString(), returnVirtualOrderDetails.get("reason_code").toString().trim()));
    }

    @And("^I should see shipment level call to action as below$")
    public void i_should_see_shipment_level_call_to_action_as_below() throws Throwable {
        if (!StringUtils.containsIgnoreCase(shipmentFromPage.get("item_action").toString().replaceAll("\\n", ""), returnVirtualOrderDetails.get("call_to_action").toString().trim())) {
            Assert.fail("Call to action is message: " + returnVirtualOrderDetails.get("call_to_action").toString() + ": is not verified on order details page");
        }
        logger.info("Reminder call to action message is verified successfuly");
    }

    @And("^I have \"([^\"]*)\" order for shipment type \"([^\"]*)\" with \"([^\"]*)\" for selection page$")
    public void iHaveOrderForShipmentTypeWithForSelectionPage(String orderType, String shipmentType, String exchangeType) throws Throwable {
  /*
        json file, java file to map with getters and setters.
         */
        JSONArray orders = null;
        JSONArray ordersByBrand;
        File returnsFile = getResourceFile("virtual_returns.json");
        String jsonTxt = Utils.readTextFile(returnsFile);
        JSONObject json = new JSONObject(jsonTxt);

        if (dsg()) {
            logger.info("Data center is DSG");
            ordersByBrand = (JSONArray) json.get("dsg");
            for (int i = 0; i < ordersByBrand.length(); i++) {
                JSONObject obj = (JSONObject) ordersByBrand.get(i);
                orders = (JSONArray) obj.get(orderType);
                if (orders != null) { break; }
            }
        } else if (s2b()) {
            logger.info("Data center is S2B");
            ordersByBrand = (JSONArray) json.get("s2b");
            for (int i = 0; i < ordersByBrand.length(); i++) {
                JSONObject obj = (JSONObject) ordersByBrand.get(i);
                orders = (JSONArray) obj.get(orderType);
                if (orders != null) { break; }
            }
        }
        for (int i = 0; i < orders.length(); i++) {
            if(orders.getJSONObject(i).keySet().contains("shipment_type") && orders.getJSONObject(i).keySet().contains("exchange_type")) {
                if ((StringUtils.equalsIgnoreCase(orders.getJSONObject(i).get("shipment_type").toString().trim(), shipmentType.trim())) && (StringUtils.equalsIgnoreCase(orders.getJSONObject(i).get("exchange_type").toString().trim(), exchangeType.trim()))) {
                    returnVirtualOrderDetails = orders.getJSONObject(i);
                    break;
                }
            }
        }
        if (returnVirtualOrderDetails == null) {
            Assert.fail("No order found in JSON File");
        }
        String orderNumber = returnVirtualOrderDetails.get("order_number").toString();
        logger.info("order number=" + orderNumber);
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
    }

    @And("^I navigated to \"([^\"]*)\" page with \"([^\"]*)\" items in selection$")
    public void iNavigatedToPageWithItemsInSelection(String page_type, String return_type) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        orderItems = returnsPage.orderItemDetails();
        ArrayList lineItems = (ArrayList) orderItems.get("orderLineItemList");
        ArrayList<HashMap> pr_items = new ArrayList<HashMap>();
        if (lineItems.size() >= 1)
            for (int i = 0; i < lineItems.size(); i++) {
                HashMap map = (HashMap) lineItems.get(i);
                if (!(map.get("vrFlag").toString().equals("true"))) {
                    pr_items.add(map);
                }
            }
        if (return_type.equals("with_pr")) {
            Clicks.javascriptClick(By.name("returnDetails.returnShipment.lineItemList[0].itemSelected"));
            DropDowns.selectRandomValue("return_selection.tuxedo_dropdown");
        }
        else
        {
            logger.info("Navigating to Submit page without PR items");
        }

        enterEmailOnReturnSelectionPage();
        Clicks.click(Elements.element("return_selection.submit_return"));
        logger.info("Click on returns submit button");
        Wait.secondsUntilElementPresent("return_submit.submit_return", 30);
        if (page_type.equals("confirmation")) {
            Clicks.click("return_submit.submit_return");
            Wait.secondsUntilElementPresent("return_confirmation.crl_number", 30);
            Wait.forPageReady();
            if (!onPage("return_confirmation")) {
                Assert.fail("User is not navigated to return confirmation page!!");
            }
            Clicks.clickIfPresent("home.popup_close");
        }
    }

    public void enterEmailOnReturnSelectionPage() {
        Wait.untilElementPresent("return_selection.email");
        TextBoxes.typeTextbox(Elements.element("return_selection.email"), returnVirtualOrderDetails.get("email").toString());
        TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), returnVirtualOrderDetails.get("email").toString());
    }

    @Then("^I (should|should not) see previous credit on (submit|confirmation) page header$")
    public void iSeePreviousCreditOnPageHeader(String visibility, String page) throws Throwable {
        String pageName;
        if(page.equals("submit")){
            pageName = "return_submit";
        }
        else
            pageName = "return_confirmation";
        switch (visibility) {
            case "should":
                Wait.untilElementPresent(pageName+".previous_credit_header");
                Assert.assertTrue("ERROR-APP :- Previous Credit Amount Label not displaying", Elements.elementPresent(pageName+".previous_credit_header"));
                break;
            case "should not":
                Wait.untilElementPresent(pageName+".previous_credit_header");
                Assert.assertTrue("ERROR-APP :- Previous Credit Amount Label is displaying", !(Elements.elementPresent(pageName+".previous_credit_header")));
                break;
        }
        logger.info("Prevoius Credit Labels are displaying as expected");
    }


    @And("^I (should|should not) see estimated credit on (submit|confirmation) page header$")
    public void iSeeEstimatedCreditOnPageHeader(String visibility, String page) throws Throwable {
        String pageName;
        if(page.equals("submit")){
            pageName = "return_submit";
        }
        else
            pageName = "return_confirmation";
        switch (visibility) {
            case "should":
                Wait.untilElementPresent(pageName+".estimated_credit_header");
                Assert.assertTrue("ERROR-APP :- Estimated Credit Amount Label not displaying", Elements.elementPresent(pageName+".estimated_credit_header"));
                break;
            case "should not":
                Wait.untilElementPresent(pageName+".estimated_credit_header");
                Assert.assertTrue("ERROR-APP :- Estimated Credit Amount Label is displaying", !(Elements.elementPresent(pageName+".estimated_credit_header")));
                break;
        }
        logger.info("Estimated Credit Labels are displaying as expected");
    }

    @And("^I (should|should not) see previous credit on (submit|confirmation) page payment section$")
    public void iSeePreviousCreditOnPagePaymentSection(String visibility, String page) throws Throwable {
        String pageName;
        if(page.equals("submit")){
            pageName = "return_submit";
        }
        else
            pageName = "return_confirmation";
        switch (visibility) {
            case "should":
                Wait.untilElementPresent(pageName+".previous_credit_info");
                Assert.assertTrue("ERROR-APP:- Prevoius Credit Info Label not displaying at payment information", Elements.elementPresent(pageName+".previous_credit_info"));
                break;
            case "should not":
                Wait.untilElementPresent(pageName+".previous_credit_info");
                Assert.assertTrue("ERROR-APP:- Prevoius Credit Info Label is displaying at payment information", !(Elements.elementPresent(pageName+".previous_credit_info")));
                break;
        }
        logger.info("Prevoius Credit Info Label not displaying correclty in payment information");
    }

    @And("^I (should|should not) see estimated credit on (submit|confirmation) page payment section$")
    public void iSeeEstimatedCreditOnPagePaymentSection(String visibility, String page) throws Throwable {
        String pageName;
        if(page.equals("submit")){
            pageName = "return_submit";
        }
        else
            pageName = "return_confirmation";
        switch (visibility) {
            case "should":
                Wait.untilElementPresent(pageName+".estimated_credit_info");
                Assert.assertTrue("ERROR-APP :- Estimated Credit Info Label is not displaying at payment information", Elements.elementPresent(pageName+".estimated_credit_info"));
                break;
            case "should not":
                Wait.untilElementPresent(pageName+".estimated_credit_info");
                Assert.assertTrue("ERROR-APP :- Estimated Credit Info Label is displaying at payment information", !(Elements.elementPresent(pageName+".estimated_credit_info")));
                break;
        }
        logger.info("Estimated Credit Info Label not displaying correclty in payment information");
    }

    @Then("^I should not see return items button for order$")
    public void iShouldNotSeeReturnItemsButtonForOrder() throws Throwable {
        Assert.assertTrue("ERROR-APP: Return Items button is displaying for Single Item in MOS-Y Order ",!(Elements.elementPresent("order_details.return_items")));
        logger.info("Return Items button is working fine");
    }

    public void ISelectOrderAsUser(String userType) throws Throwable {
        if (returnVirtualOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnVirtualOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnVirtualOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        returns.deleteReturnRecord(orderNumber);
        if (userType.equals("signed")) {
            if (returns.orderExistsByOrderNumber(orderNumber)) {
                returns.deleteOrderRecord(orderNumber);
            }
            CommonUtils.signInOrCreateAccount();
            logger.info("Email = " + TestUsers.currentEmail);
            returns.insertOrderByOrderNumber(orderNumber, TestUsers.currentEmail);
        }
    }

    @When("^I select link on cta in header$")
    public void iSelectLinkOnCtaInHeader() throws Throwable {
        Assert.assertTrue("Header Message not exists", Elements.elementPresent("order_status.header_message"));
        Clicks.click("order_status.order_header_link");
    }

    @And("^I select link on shipment header$")
    public void iSelectLinkOnShipmentHeader() throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.findOrderInDateRange(returnVirtualOrderDetails.getString("order_number"));
        Wait.forPageReady();
        Wait.untilElementPresent("order_status.vr_remainder_link");
        Clicks.click("order_status.vr_remainder_link");
        logger.info("Selected order link at shipment level");
    }

    @Then("^I should see (shipment date|reason code) with status$")
    public void iShouldSeeWithStatus(String opt) throws Throwable {
        Wait.forPageReady();
        switch (opt) {
            case "shipment date":
                Assert.assertTrue("Shipment dates are not displaying expectdly", shipmentFromPage.get("item_action").toString().contains(returnVirtualOrderDetails.getString("shipment_date")));
                logger.info("Shipment dates are displaying expectdly");
                break;
            case "reason code":
                Assert.assertTrue("Reason codes are not displaying expectdly", shipmentFromPage.get("item_action").toString().contains(returnVirtualOrderDetails.getString("reason_code")));
                logger.info("Reason codes are displaying expectdly");
                break;
        }
    }

    @And("^I should see total is updated to reflect the credit$")
    public void iShouldSeeTotalIsUpdatedToReflectTheCredit() throws Throwable {
        Wait.untilElementPresent("order_status.order_total_amount");
        String actualTotalOrder = orderItems.get("order_total").toString().replaceAll("\\$", "");
        Assert.assertTrue("ERROR-APP : Expected " + returnVirtualOrderDetails.getString("order_total") + " Actual " + actualTotalOrder, actualTotalOrder.equals(returnVirtualOrderDetails.getString("order_total")));
        logger.info("Verified order total and SDP response total is equal to UI total");
    }

    @Then("^I should see shipment on page with status on header with expected date for shipment$")
    public void iShouldSeeShipmentOnPageWithStatusOnHeaderWithExpectedDateForShipment() throws Throwable {
        SimpleDateFormat fileFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = fileFormatter.parse(returnVirtualOrderDetails.get("shipment_date").toString());
        SimpleDateFormat mcomFormat = new SimpleDateFormat("MMMM dd, YYYY");
        logger.info("Date expected is: Desired Date from file " + date + " date expected on UI " + mcomFormat.format(date));
        logger.info("shipmentFromPage: " + shipmentFromPage.toString());
        if (macys()){
            Assert.assertTrue("Date is not displaying as expected on shipment header", shipmentFromPage.get("header_status").toString().contains(mcomFormat.format(date)));
        }else
        {
            Assert.assertTrue("Date is not displaying as expected on shipment header", shipmentFromPage.get("item_action").toString().contains(mcomFormat.format(date))||shipmentFromPage.get("item_action").toString().contains(returnVirtualOrderDetails.get("shipment_date").toString()));
        }
        logger.info("Date is displaying as expected on shipment header");
    }


    @And("^I should see shipment address as expected$")
    public void iShouldSeeShipmentAddressAsExpected() throws Throwable {
        if(returnVirtualOrderDetails.get("address_validation").toString().equals("true")) {
            Assert.assertTrue("FirstName should match", shipmentFromPage.get("warehouseName").toString().contains(returnVirtualOrderDetails.get("first_name_last_name").toString()));
            Assert.assertTrue("Address should match", shipmentFromPage.get("warehouseAddress").toString().contains(returnVirtualOrderDetails.get("address_line1").toString()));
            Assert.assertTrue("Address city should match", shipmentFromPage.get("warehouseCity").toString().contains(returnVirtualOrderDetails.get("city").toString()));
            Assert.assertTrue("Address state should match", shipmentFromPage.get("warehouseState").toString().contains(returnVirtualOrderDetails.get("state").toString()));
            Assert.assertTrue("Address zipcode should match", shipmentFromPage.get("warehouseZipCode").toString().contains(returnVirtualOrderDetails.get("zip_code").toString()));
        }else
        {
            Assert.assertTrue("FirstName should match", shipmentFromPage.get("warehouseName").toString().equals(""));
            Assert.assertTrue("Address should match", shipmentFromPage.get("warehouseAddress").toString().equals(""));
            Assert.assertTrue("Address city should match", shipmentFromPage.get("warehouseCity").toString().equals(""));
            Assert.assertTrue("Address state should match", shipmentFromPage.get("warehouseState").toString().equals(""));
            Assert.assertTrue("Address zipcode should match", shipmentFromPage.get("warehouseZipCode").toString().equals(""));
        }
        logger.info("Shipment address fields are validated successfully.");
    }

    public void getShipmentLineItem() {
        String shipment_type = returnVirtualOrderDetails.getString("shipment_type");
        String shipment_status = returnVirtualOrderDetails.getString("shipment_status");
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map<String, Object>> orderDetails = returnsPage.getOrderDetails();
        for (Map<String, Object> map : orderDetails) {
            if( map.get("headerStatus").toString().contains(shipment_type) && map.get("headerStatus").toString().contains(shipment_status))
            {
                shipmentFromPage =map;
                logger.info("Desired shipment line details is :"+shipmentFromPage);
                return;
            }
        }
    }


    @And("^I should see label for amount at for shipment as expected$")
    public void iShouldSeeLabelForAmountAtForShipmentAsExpected() throws Throwable {
        String expectedLabel = (String) returnVirtualOrderDetails.get("payment_label");
        Assert.assertTrue(((shipmentFromPage.get("orderTotalDetails")).toString()).contains(expectedLabel));
    }

    @Then("^I (should|should not) see virtual return line item checkbox, also see reason reason for return and expected call to action on selection page$")
    public void iShouldSeeVirtualReturnLineItemCheckboxOnSelectionPage(String visibility) throws Throwable {
        String checkboxVal = visibility.equals("should") ? "isItemSelected" : "is_line_item_check_box_hidden";
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        for (int i = 0; i < lineItems.size(); i++) {
            if (lineItems.get(i).get("vrFlag").equals(true) && (lineItems.get(i).get(checkboxVal).equals(true))) {
                logger.info("VR line item checkbox is visible");
                Assert.assertEquals(lineItems.get(i).get("callToAction"), returnVirtualOrderDetails.get("call_to_action").toString().trim());
                Assert.assertEquals(lineItems.get(i).get("reasonSelected"), returnVirtualOrderDetails.get("reason_code").toString().trim());
                return;
            }
        }
    }

    @Then("^I should see below call to action on (OH page|shipment) header$")
    public void iShouldSeeBelowCallToActionOnHeader(String page, DataTable table) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map> lineItems = returnsPage.getOrderLineItemList();
        String message = table.asList(String.class).get(0);
        logger.info("List Values: " + message);
        switch (page) {
            case "OH page": {
                Assert.assertTrue("Header Message not exists", Elements.elementPresent("order_status.header_message"));
                Assert.assertEquals(message,Elements.elementPresent("order_status.header_message"));
                break;
            }
            case "shipment": {
                for (int i = 0; i < lineItems.size(); i++) {
                    Assert.assertEquals(lineItems.get(i).get("callToAction"),message);
                }
                break;
            }
        }
    }

    @When("^I continue to submit page using \"([^\"]*)\" address$")
    public void iContinueToSubmitPageUsingAddress(String address_type) throws Throwable {
        if (address_type.equals("other")) {
            Wait.untilElementPresent("return_selection.edit_pick_up_address");
            Clicks.click("return_selection.edit_pick_up_address");
            TextBoxes.typeTextbox("return_selection.pickup_zipcode", returnVirtualOrderDetails.get("other_pickup_zipcode").toString());
            Clicks.click("return_selection.zipcode_eligibility");
            if (Elements.getText("return_selection.pickup_message").contains("Sorry")) {
                Assert.fail("ERROR - APP: Zip code " + returnVirtualOrderDetails.get("other_pickup_zipcode").toString() + " Entered is not valid");
            }
            TextBoxes.typeTextbox("return_selection.address_line_one", returnVirtualOrderDetails.get("address_line_one").toString());
        } else {
            logger.info("Pickup Address is default");
        }
       // TextBoxes.typeTextbox("return_selection.special_instructions", returnVirtualOrderDetails.get("special_instructions").toString());
        ;
        TextBoxes.typeTextbox("return_selection.phone_number", returnVirtualOrderDetails.get("phone_number").toString());
        returnVirtualOrderDetails.put("scheduled_date", DropDowns.getSelectedValue(Elements.element("return_selection.scheduled_date")));
        returnVirtualOrderDetails.put("scheduled_time", DropDowns.getSelectedValue(Elements.element("return_selection.scheduled_time")));
        logger.info("Picked up the Address to verify in Submit/Confirmation page.");

        try {
            TextBoxes.typeTextbox(Elements.element("return_selection.email"), returnVirtualOrderDetails.get("email").toString());
            TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), returnVirtualOrderDetails.get("email").toString());
        } catch (JSONException e) {
            Assert.fail("selectItemsAndContinue(): " + e);
        }

        Clicks.click(Elements.element("return_selection.submit_return"));
        Wait.forPageReady();
    }

    @Then("^I should see credit section as expected$")
    public void iShouldSeeCreditSectionAsExpected() throws Throwable {
        ReturnsPage returns = new ReturnsPage();
        List<Map<String, Object>> orderMap = returns.getOrderDetails();
        logger.info("Order map details :" + orderMap);
        for (Map<String, Object> lineItems : orderMap) {
            if (lineItems.get("headerStatus").toString().contains("return") && lineItems.get("headerStatus").toString().contains("submitted")) {
                logger.info("Orderdetails : " + lineItems.get("orderTotalDetails").toString().toLowerCase());
                Assert.assertTrue("Estimated charge should be present in order details", lineItems.get("orderTotalDetails").toString().toLowerCase().contains("estimated charge"));
            }
        }
    }

    @And("^I should see instruction message as below$")
    public void iShouldSeeInstructionMessageAsBelow(List<String> message) throws Throwable {
        Wait.untilElementPresent("return_submit.information_message1");
        String message1="";
        if(macys()) {
            String text = (Elements.getText("return_submit.information_message2").equals("null") ? " " : (Elements.getText("return_submit.information_message2"))).trim();
            message1 = (Elements.getText("return_submit.information_message1") + " " + text).trim();
        }
        else
        {
            message1 = (Elements.getText("return_submit.information_message1")).trim().split("\\$")[0];
        }
        String message2 = Elements.getText("return_submit.information_message3");
        logger.info("Instruction Message on UI :" + message1 + " \n Expected Message from Example : " + message.get(0));
        Assert.assertTrue("Instruction message is not same", message.get(0).contains(message1));
        if (!message2.equals("null")) {
            Assert.assertTrue("Credit amount in Instruction message is not same.", message2.contains(returnVirtualOrderDetails.get("amount_credited").toString()));
        }
    }

    @Then("^I should see \"([^\"]*)\" \"([^\"]*)\" address as dc address$")
    public void iShouldSeeInitiatedAddressAsDcAddress(String shipmentType, String shipmentStatus) throws Throwable {
        Map<String,String> defaultLocation = new HashMap<String,String>();
        defaultLocation.put("fill_division_cd",returnVirtualOrderDetails.get("fill_division_cd").toString());
        defaultLocation.put("fill_store_nbr",returnVirtualOrderDetails.get("fill_store_nbr").toString());
        Map<String,String> dbMap = CommonUtils.getAddressMap(returnVirtualOrderDetails.get("order_number").toString(),defaultLocation);
        logger.info("AddressMap from UI:"+shipmentFromPage);
        logger.info("db address map:"+dbMap.toString());
        Assert.assertTrue("WarehouseAddress should match",shipmentFromPage.get("warehouseAddress").toString().equals(dbMap.get("address_line1")));
        Assert.assertTrue("ZipCode should match",shipmentFromPage.get("warehouseZipCode").toString().equals(dbMap.get("postal_code")));
        Assert.assertTrue("WarehouseState should match",shipmentFromPage.get("warehouseState").toString().equals(dbMap.get("state_abbrev")));
        Assert.assertTrue("City should match",shipmentFromPage.get("warehouseCity").toString().equals(dbMap.get("city")));
    }

    public Map<String,String> convertToMap(String mapInString){

        Map<String,String> map = new HashMap<>();
        String value = StringUtils.substringBetween(mapInString, "{", "}"); //remove curly brackets
        String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
        for(String pair : keyValuePairs)                        //iterate over the pais
        {
            if(pair.contains("=")) {
                String[] entry = pair.split("=");                   //split the pairs to get key and value
                if (entry.length==2 && entry[0] != null && entry[1] != null && entry[0].trim() != null && entry[1].trim() != null ) {
                    map.put(entry[0].trim().replaceAll("[\"]", ""), entry[1].trim().replaceAll("[\"]", ""));          //add them to the hashmap
                }
            }
        }
        return map;
    }
    /*
   This method will return all the data of orders present in Order History page(After signIn). when user is signed in then we need to call this method to fetch all order related data.
    */
    public Map<String, Object> getOrderDetailsfromOHSignedUser() {
        logger.info("Get order details for all status which order went through");
        Map<String, Object> mainmap = new HashMap<>();
        ArrayList container = new ArrayList();
        if(bloomingdales())
        {
            List<Map> listofMap = new ArrayList<>();
            //The below 3 lines will fetch the data from header.
            mainmap.put("order_number", Elements.getText(Elements.element("order_status.order_history_order_text")));
            mainmap.put("order_date", Elements.getText(Elements.element("order_status.order_history_order_date")));
            mainmap.put("order_total", Elements.getText(Elements.element("order_status.order_total_amount")));
            List<WebElement> alllineItems = Elements.findElements(Elements.element("order_status.all_line_items"));
            //Whatever the orderItems are present, the following loop will run and will save data for each item.
            for (int i = 0; i < alllineItems.size(); i++) {
                Map shipmentDetails = new HashedMap();
                try {
                    shipmentDetails.put("header_status", alllineItems.get(i).findElement(By.xpath("(//div[@class='orStatus_trackingHeader']/div)[" + (i + 1) + "]")).getText().toLowerCase());
                    shipmentDetails.put("product_img_src", alllineItems.get(i).findElement(By.xpath("(//div[@class='orHist_itemImage']//a)[" + (i + 1) + "]")).getAttribute("href"));
                    shipmentDetails.put("item_description", alllineItems.get(i).findElement(By.xpath("//div[@class='orStatus_orderSection'][" + (i + 1) + "]//span/a")).getText());
                    //Sometimes item_action values comes in 2 seprate line and we need both line's text. To cater this condition, following loop is created.
                    List<WebElement> action = WebDriverManager.getWebDriver().findElements(By.xpath("//div[@class='orStatus_orderSection'][" + (i + 1) + "]//div[starts-with(@class, 'orHist_statusContainer')]/span"));
                    String item_action = "";
                    for (WebElement e : action) {
                        item_action += e.getText();
                    }
                    shipmentDetails.put("item_action", item_action);
                    shipmentDetails.put("item_qty", alllineItems.get(i).findElement(By.xpath("//div[@class='orStatus_orderSection'][" + (i + 1) + "]//div[@class='orHist_itemQty']")).getText());
                    shipmentDetails.put("item_price", alllineItems.get(i).findElement(By.xpath("//div[@class='orStatus_orderSection'][" + (i + 1) + "]//div[@class='orHist_itemPrice']")).getText());
                    shipmentDetails.put("link_to_pdp_page", alllineItems.get(i).findElement(By.xpath("//div[@class='orStatus_orderSection'][" + (i + 1) + "]//span/a")).getAttribute("href"));
                }catch(Exception e){}
                listofMap.add(shipmentDetails);
            }
            container.add(listofMap);
        }
        else {
            mainmap.put("order_number", Elements.getText(Elements.element("order_status.order_number_detail")));
            mainmap.put("order_date", Elements.getText(Elements.element("order_status.order_history_order_date")));
            mainmap.put("order_total", Elements.getText(Elements.element("order_status.order_total_amount")));
            List<WebElement> alllineItems = Elements.findElements(Elements.element("order_status.all_line_items"));
            //Whatever the orderItems are present, the following loop will run and will save data for each item.
            for (int i = 0; i < alllineItems.size(); i++) {
                List<Map> listofMap = new ArrayList<>();
                List<WebElement> items = alllineItems.get(i).findElements(By.xpath("//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails']"));
                //for some orders, there are 2 or more sub line items present. So to save all the sub line items, the following loop is created.
                for (int j = 0; j < items.size(); j++) {
                    Map shipmentDetails = new HashedMap();
                    try {
                        shipmentDetails.put("header_status", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus']/h2)[" + (i + 1) + "]")).getText().toLowerCase());
                        shipmentDetails.put("product_img_src", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails'])[" + (j + 1) + "]/li[@class='productImage']/a")).getAttribute("href"));
                        shipmentDetails.put("item_description", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails'])[" + (j + 1) + "]/li[starts-with(@class, 'description')]/p/a[not (@class)]")).getText());
                        shipmentDetails.put("item_action", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails'])[" + (j + 1) + "]//li[3]")).getText());
                        shipmentDetails.put("item_qty", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails'])[" + (j + 1) + "]/li[4]/span")).getText());
                        shipmentDetails.put("item_price", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails'])[" + (j + 1) + "][1]/li[5]/span")).getText());
                        shipmentDetails.put("link_to_pdp_page", alllineItems.get(i).findElement(By.xpath("(//div[@class='orderStatus'][" + (i + 1) + "]//ul[@class='orderStatusDetails'])[" + (j + 1) + "]/li[2]/p/a[not (@class)]")).getAttribute("href"));
                    }catch(Exception e){}
                    listofMap.add(shipmentDetails);
                }
                container.add(listofMap);
            }
        }
        mainmap.put("order_details", container);
        return mainmap;
    }

    /*
   This method will return all the data of orders present in Order Details page(Without sighIn). when user is not signedIn then we need to call this method to fetch all order related data.
    */
    public Map<String, Object> getOrderDetailsfromOD() {
        logger.info("Get order details for all status which order went through");
        Map<String, Object> mainmap = new HashMap<>();
        ArrayList container = new ArrayList();
        if(bloomingdales())
        {
            mainmap.put("order_number", Elements.getText(Elements.element("order_details.order_number")));
            mainmap.put("order_date", Elements.getText(Elements.element("order_details.order_date")));
            mainmap.put("order_total", Elements.getText(Elements.element("order_details.order_total_amount")));
            List<WebElement> alllineItems = Elements.findElements(Elements.element("order_status.all_line_items"));
            for (int i = 0; i < alllineItems.size(); i++) {
                List<Map> listofMap = new ArrayList<>();
                List<WebElement> items = alllineItems.get(i).findElements(By.xpath("(//div[@class='orStatus_container_content'])["+(i+1)+"]//div[contains(@class,'orDetails_orderBox')]"));
                for (int j = 0; j < items.size(); j++) {
                    Map shipmentDetails = new HashedMap();
                    //The following warehouse related data is for saving the Address. The Address field comes on in order Details page. Not on Order History page.
                    try {

                        shipmentDetails.put("header_status", alllineItems.get(i).findElement(By.xpath("(//div[@class='orStatus_trackingHeader']/div[starts-with(@class, 'orStatus_trackingStatus')])["+(i+1)+"]")).getText().toLowerCase());
                        //Sometimes item_action values comes in 2 seprate line and we need both line's text. To cater this condition, following loop is created.
                        List<WebElement> action = WebDriverManager.getWebDriver().findElements(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//div[starts-with(@class, 'orDetails_statusContainer')]/span"));
                        String item_action = "";
                        for (WebElement e : action) {
                            item_action += e.getText();
                        }
                        try {
                            item_action+=";"+alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])["+(i+1)+"]//div[contains(@class,'orDetails_orderBox')])["+(j+1)+"]//div[starts-with(@class, 'orStatus_infoIcon')]")).getText();
                        } catch (Exception e) {
                            item_action+= ";write a review";
                        }
                        shipmentDetails.put("item_action", item_action);
                        shipmentDetails.put("item_description", alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//a")).getText());
                        shipmentDetails.put("gift_box", alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//div[starts-with(@class, 'orDetails_giftContainer')]")).getText());
                        shipmentDetails.put("item_qty", alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//div[starts-with(@class, 'orDetails_qtyContainer')]")).getText());
                        shipmentDetails.put("item_price", alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//div[starts-with(@class, 'orDetails_priceContainer')]")).getText());
                        shipmentDetails.put("item_total", alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//div[starts-with(@class, 'orDetails_totalContainer')]")).getText());
                        shipmentDetails.put("link_to_pdp_page", alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//a")).getAttribute("href"));
                        String total_details = alllineItems.get(i).findElement(By.xpath("(//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[@class='orDetails_shipmentTotalHeader']")).getText();
                        total_details += alllineItems.get(i).findElement(By.xpath("(//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[@id='shipmentTotalAmount']")).getText();
                        shipmentDetails.put("orderTotalDetails", total_details);
                        String warehouseName="";
                        try{
                            warehouseName=alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[starts-with(@class, 'orDetails_shipContainer')]/div)[1]")).getText();
                        }catch(Exception e){}
                        shipmentDetails.put("warehouseName", warehouseName);
                        String warehouseAddress="";
                        try{
                            warehouseAddress=alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[starts-with(@class, 'orDetails_shipContainer')]/div)[2]")).getText();
                        }catch(Exception e){}
                        shipmentDetails.put("warehouseAddress", warehouseAddress);
                        try {
                            String address[] = alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[starts-with(@class, 'orDetails_shipContainer')]/div)[3]")).getText().split(",");
                            shipmentDetails.put("warehouseCity", address[0].trim());
                            shipmentDetails.put("warehouseState", address[1].trim());
                        }catch(Exception e){
                            shipmentDetails.put("warehouseCity", "");
                            shipmentDetails.put("warehouseState", "");
                        }
                        String warehouseZipCode="";
                        try {
                            warehouseZipCode = alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[starts-with(@class, 'orDetails_shipContainer')]/div)[4]")).getText();
                        }catch(Exception e){}
                        shipmentDetails.put("warehouseZipCode", warehouseZipCode);
                        String product_img_src="";
                        try{
                            product_img_src=alllineItems.get(i).findElement(By.xpath("((//div[@class='orStatus_container_content'])[" + (i + 1) + "]//div[contains(@class,'orDetails_orderBox')])[" + (j + 1) + "]//a")).getAttribute("href");
                        }catch(Exception e){}
                        shipmentDetails.put("product_img_src", product_img_src);
                    }catch(Exception e){}
                    listofMap.add(shipmentDetails);
                }
                container.add(listofMap);
            }
        }
        else {
            mainmap.put("order_number", Elements.getText(Elements.element("order_details.order_number")));
            mainmap.put("order_date", Elements.getText(Elements.element("order_details.order_date1")));
            mainmap.put("order_total", Elements.getText(Elements.element("order_details.order_total_amount")));
            List<WebElement> alllineItems = Elements.findElements(Elements.element("order_details.order_details_container"));
            for (int i = 0; i < alllineItems.size(); i++) {
                List<Map> listofMap = new ArrayList<>();
                List<WebElement> items = alllineItems.get(i).findElements(By.xpath("//div[starts-with(@class, 'shipmentWrapper')]["+(i+1)+"]//li[@class='info']"));
                for (int j = 0; j < items.size(); j++) {
                    Map shipmentDetails = new HashedMap();
                    try {
                        shipmentDetails.put("header_status", alllineItems.get(i).findElement(By.xpath("(//div[starts-with(@class, 'shipmentWrapper')][" + (i + 1) + "]//span)[1]")).getText());
                        try {
                            shipmentDetails.put("item_action", alllineItems.get(i).findElement(By.xpath("(//div[starts-with(@class, 'shipmentWrapper')][" + (i + 1) + "]//li[@class='returnReminderInfo'])[" + (j + 1) + "]")).getText());
                        } catch (Exception e) {
                            shipmentDetails.put("item_action", "write a review");
                        }
                        shipmentDetails.put("item_description", alllineItems.get(i).findElement(By.xpath("(//div[starts-with(@class, 'shipmentWrapper')][" + (i + 1) + "]//li[@class='info'])[" + (j + 1) + "]/p/a")).getText());
                        shipmentDetails.put("gift_box", alllineItems.get(i).findElement(By.xpath("((//div[@id='shippingDetailsInfoDiv'])[" + (i + 1) + "]//li[@class='info'])[" + (j + 1) + "]//following-sibling::li[1]")).getText());
                        shipmentDetails.put("item_qty", alllineItems.get(i).findElement(By.xpath("((//div[@id='shippingDetailsInfoDiv'])[" + (i + 1) + "]//li[@class='info'])[" + (j + 1) + "]//following-sibling::li[2]")).getText());
                        shipmentDetails.put("item_price", alllineItems.get(i).findElement(By.xpath("((//div[@id='shippingDetailsInfoDiv'])[" + (i + 1) + "]//li[@class='info'])[" + (j + 1) + "]//following-sibling::li[3]")).getText());
                        if(WebDriverManager.getWebDriver().findElements(By.xpath("//div[starts-with(@class, 'shipmentWrapper')]["+(i+1)+"]//button[@class='trackShipmentBtn']")).size()>0)
                           shipmentDetails.put("track_my_return", "true");
                       else
                           shipmentDetails.put("track_my_return", "false");
                        shipmentDetails.put("link_to_pdp_page", alllineItems.get(i).findElement(By.xpath("(//div[starts-with(@class, 'shipmentWrapper')][" + (i + 1) + "]//li[@class='info'])[" + (j + 1) + "]/p/a")).getAttribute("href"));
                        shipmentDetails.put("orderTotalDetails", alllineItems.get(i).findElement(By.xpath("(//div[starts-with(@class, 'shipmentWrapper')])[" + (i + 1) + "]//div[@class='grandTotal expandArrow']")).getText());
                        String warehouseName="";
                        try{
                            warehouseName=alllineItems.get(i).findElement(By.xpath("((//div[starts-with(@class, 'shipmentWrapper')])[" + (i + 1) + "]//div[@class='shippingAddress']//li)[1]")).getText();
                        }catch (Exception e){}
                        shipmentDetails.put("warehouseName", warehouseName);
                        String warehouseAddress="";
                        try{
                            warehouseAddress= alllineItems.get(i).findElement(By.xpath("((//div[starts-with(@class, 'shipmentWrapper')])[" + (i + 1) + "]//div[@class='shippingAddress']//li)[2]")).getText();
                        }catch(Exception e){}
                        shipmentDetails.put("warehouseAddress", warehouseAddress);
                        try {
                            String address[] = alllineItems.get(i).findElement(By.xpath("((//div[starts-with(@class, 'shipmentWrapper')])[" + (i + 1) + "]//div[@class='shippingAddress']//li)[3]")).getText().split(",");
                            String state[] = address[1].trim().split(" ");
                            shipmentDetails.put("warehouseCity", address[0]);
                            shipmentDetails.put("warehouseState", state[0]);
                            shipmentDetails.put("warehouseZipCode", state[1]);
                        }catch(Exception e){
                            shipmentDetails.put("warehouseCity", "");
                            shipmentDetails.put("warehouseState", "");
                            shipmentDetails.put("warehouseZipCode", "");
                        }
                        shipmentDetails.put("product_img_src", alllineItems.get(i).findElement(By.xpath("(//div[starts-with(@class, 'shipmentWrapper')][" + (i + 1) + "]//li[@class='info'])[" + (j + 1) + "]/p/a")).getAttribute("href"));
                         }catch (Exception e){}
                    listofMap.add(shipmentDetails);
                }
                container.add(listofMap);
            }
        }
        mainmap.put("order_details", container);
        return mainmap;
    }

    @Then("^I should see below call to action on \"([^\"]*)\" header$")
    public void iShouldSeeBelowCallToActionOnHeader(String pageName, List<String> message) throws Throwable {
        switch (pageName) {
            case "OH page":
                Wait.untilElementPresent("order_details.call_to_action_page");
                Assert.assertTrue("Call to action is not displaying as expectedly on Order History page.", (Elements.getText("order_details.call_to_action_page").contains(message.get(0))));
                break;
            case "shipment":
                Wait.untilElementPresent("order_details.call_to_action_shipment");
                Assert.assertTrue("Call to action is not displaying as expectedly on Shipment header", (Elements.getText("order_details.call_to_action_shipment").contains(message.get(0))));
                break;
        }
        logger.info("Call to action is displaying as expectedly");
    }

    @And("^I should see updated order total for shipment$")
    public void iShouldSeeUpdatedOrderTotalForShipment() throws Throwable {
        Assert.assertTrue("Order Totals is mismatched",shipmentFromPage.get("orderTotalDetails").toString().contains(returnVirtualOrderDetails.get("order_total").toString()));
        logger.info("Order Totals are displaying correctly");
    }

    @And("^I should not see call at action at line item level$")
    public void iShouldNotSeeCallAtActionAtLineItemLevel() throws Throwable {
        Assert.assertTrue("ERROR-APP :  Call to action is displaying at line item level which is not expected for completed shipments",shipmentFromPage.get("item_action").toString().contains("write a review"));
        logger.info("Verified Replacement Processing, intrasit delivered shipment line items call to actions and Reason codes");
    }

    @And("^I should see track shipment button on shipment$")
    public void iShouldSeeTrackShipmentButtonOnShipment() throws Throwable {
        Assert.assertTrue("ERROR-APP :  Call to action is displaying at line item level which is not expected for completed shipments",shipmentFromPage.get("track_my_return").toString().equals("true"));
        logger.info("Track my return displaying as expectdly");
    }

    @And("^I should see the return initiated date on submit page header$")
    public void iShouldSeeTheReturnInitiatedDateOnSubmitPageHeader() throws Throwable {
        String locator;
        if(macys())
            locator="return_submit.credit_to_credit_card";
        else
            locator= "return_submit.refund_to";
        Wait.untilElementPresent(locator);
        Assert.assertTrue("ERROR-APP :- Initiated Date is dislaying incorrectly",Elements.getText(locator).contains(returnVirtualOrderDetails.get("replacement_initiated_on").toString()));
        logger.info("Initiated Date is dislaying correctly");
    }
}






