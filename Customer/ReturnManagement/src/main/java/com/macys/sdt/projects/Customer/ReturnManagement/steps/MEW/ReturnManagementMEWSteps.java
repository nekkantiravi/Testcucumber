package com.macys.sdt.projects.Customer.ReturnManagement.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.ReturnManagement.actions.ReturnsActions;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cchandragiri on 9/4/2017.
 */
public class ReturnManagementMEWSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReturnManagementMEWSteps.class);
    private WebElement vrLineItem = null;
    private List<HashMap> lineItemsOnSelectionPageBeforeMovingToRefundPage = null;
    public static List<HashMap> selectedLineItems = null;
    public static HashMap<String, String> refundDetails = null;
    public static HashMap<String, String> returnMethodDetails = null;
    public static List<HashMap> confimationLineItems = null;
    public static HashMap<String, Object> confirmationDetails = null;
    private List<HashMap> unSelectedLineItems = null;
    public static WebElement lineItemValidating = null;
    private String orderNumber = null;
    public WebElement orderContainer = null;


    @When("^I select items \"([^\"]*)\" to return on page")
    public void iSelectItemsOnSelectionPage(String numberOfItemsToReturn) throws Throwable {
        shouldBeOnPage("return_selection_page");
        ReturnsActions returnActions = new ReturnsActions();
        int itemCountToReturn = Integer.parseInt(numberOfItemsToReturn);
        returnActions.selectItemsToReturn(itemCountToReturn);
        logger.info("Selected Items to return on selection page");
    }

    @Then("^I should see unselected line item reason for return as default$")
    public void iShouldSeeUnselectedLineItemReasonForReturnAsDefault() throws Throwable {
        Select select = new Select(ReturnsActions.unSelectedLineItems().get(0).findElement(Elements.element("return_selection_page.reason_for_return")));
        Assert.assertEquals("Reason for return code not default one", select.getFirstSelectedOption().getText(), "Select reason for return");
        logger.info("Reason for return code is displaying as expected by default");
    }

    @And("^I should see unselected line item quantity as one$")
    public void iShouldSeeUnselectedLineItemQuantityAsOne() throws Throwable {
        Select select = new Select(ReturnsActions.unSelectedLineItems().get(0).findElement(Elements.element("return_selection_page.quantity")));
        Assert.assertEquals("Quantity is not default value", select.getFirstSelectedOption().getText(), "Qty: 1");
        logger.info("Quantity is displaying as expected by default");
    }

    @And("^I should be able to toggle between line items$")
    public void iShouldBeAbleToToggleBetweenLineItems() throws Throwable {
        Clicks.click("return_selection_page.toggle_line_item");
        Assert.assertTrue("Check box not selected properly", Elements.findElement("return_selection_page.toggle_line_item").isSelected());
        logger.info("Able to select line item");
    }

    @Then("^I should see popup with yes or no buttons with below text$")
    public void iShouldSeePopupWithYesOrNoButtonsWithBelowText(DataTable table) throws Throwable {
        String headerText = table.asList(String.class).get(0);
        String message = table.asList(String.class).get(1);
        Assert.assertTrue("Yes option is not displaying on Cancel popup", Elements.anyPresent("returns_cancel_popup.cancel_yes"));
        Assert.assertTrue("No option is not displaying on Cancel popup", Elements.anyPresent("returns_cancel_popup.cancel_no"));
        Assert.assertEquals("Cancel model header text does not match", headerText, Elements.getText("returns_cancel_popup.cancel_overlay_title"));
        Assert.assertEquals("Cancel model text does not match", message, Elements.getText("returns_cancel_popup.cancel_overlay_content"));
        logger.info("Cancel options are displaying as expected");

    }

    @And("^I tap on \"([^\"]*)\" button on cancel popup$")
    public void iTapOnButtonOnCancelPopup(String buttonName) throws Throwable {
        switch (buttonName) {
            case "no":
                Clicks.click("returns_cancel_popup.cancel_no");
                break;
            case "yes":
                Clicks.click("returns_cancel_popup.cancel_yes");
                break;
        }
        logger.info("Tapped on button " + buttonName);
    }

    @Then("^I should see cancel button popup is disappeared$")
    public void iShouldSeeCancelButtonPopupIsDisapperared() throws Throwable {
        Assert.assertFalse("Yes option is not displaying on Cancel popup", Elements.anyPresent("returns_cancel_popup.cancel_yes"));
        Assert.assertFalse("No option is not displaying on Cancel popup", Elements.anyPresent("returns_cancel_popup.cancel_no"));
        logger.info("cancel button popup is disappeared on selection page");
    }

    @Then("^I should see below error messages$")
    public void iShouldSeeBelowErrorMessages(DataTable table) throws Throwable {
        Map<String, String> errorMessages = table.asMap(String.class, String.class);
        String globalExpected = errorMessages.get("global");
        String fieldExpected = errorMessages.get("field");
        String globalActualErrorMsg = Elements.getText("return_selection_page.return_global_error");
        String fieldActualErrorMsg = Elements.getText("return_selection_page.reason_field_error");
        Assert.assertEquals("Global error message is not matching", globalExpected, globalActualErrorMsg);
        Assert.assertEquals("Field error message is not matching", fieldExpected, fieldActualErrorMsg);
        logger.info("Error messages are matching in return selection page");
    }

    @And("^I select checkbox to return item$")
    public void iSelectCheckboxToReturnItem() throws Throwable {
        lineItemValidating = ReturnsActions.unSelectedLineItems().get(0);
        Clicks.click(lineItemValidating.findElement(Elements.element("return_selection_page.toggle_line_item")));
        logger.info("Selected line item check box to return");
    }

    @When("^I select reason for return$")
    public void iSelectReasonForReturn() throws Throwable {
        Select select = new Select(lineItemValidating.findElement(Elements.element("return_selection_page.reason_for_return")));
        String reasonSelected = select.getFirstSelectedOption().getText();
        logger.info("reason selected : " + reasonSelected);
        select.selectByIndex(1);
        logger.info("Selected reason for return");
    }

    @And("^I select checkbox to return item again$")
    public void iSelectCheckboxToReturnItemAgain() throws Throwable {
        Clicks.click(lineItemValidating.findElement(Elements.element("return_selection_page.reason_for_return")));
        logger.info("Un Selected line item to return");
    }

    @Then("^I should see reason for return as default one$")
    public void iShouldSeeReasonForReturnAsDefaultOne() throws Throwable {
        Select select = new Select(lineItemValidating.findElement(Elements.element("return_selection_page.reason_for_return")));
        String reasonSelected = select.getFirstSelectedOption().getText();
        Assert.assertEquals("Reason for return code not default one", reasonSelected, "Select reason for return");
        logger.info("Reason for return code now set to default");
    }

    @And("^I tap on return items button for \"([^\"]*)\" order$")
    public void iTapOnButtonForOrder(String shipmentStatus) throws Throwable {
        Clicks.click("order_history.return_items_button");
        logger.info("Tapped on Return Items button on OH page for order with shipment " + shipmentStatus);
    }


    @Given("^I tap on order with \"([^\"]*)\" status$")
    public void i_tap_on_order_with_status(String shipmentStatus) throws Throwable {
        ReturnsActions ract = new ReturnsActions();
        String orderNum = Utils.getOrderNumber(shipmentStatus);
        Clicks.click(ract.orderContainerToSelect(orderNum));
        logger.info("View More Order Details button is not available, we have less than three orders on page");
    }


    @And("^I should see returns button displaying on top of order$")
    public void iShouldSeeReturnsButtonDisplayingOnTopOfOrder() throws Throwable {
        Assert.assertTrue("Return Items button is not displaying", Elements.anyPresent("order_history.return_items_button"));
        logger.info("Returns Items button displaying as expected");
    }


    @And("^I should see virtual return line item is pre selected$")
    public void iShouldSeeVirtualReturnLineItemIsPreSelected() throws Throwable {
        WebElement vrLineItem = Elements.getRandomElement("return_selection_page.virtual_line_item");
        Assert.assertFalse("Virtual Line item is not preselected", vrLineItem.isSelected());
        logger.info("Virtual Line item is pre selected");
    }

    @And("^I should see virtual return line item is grayed out$")
    public void iShouldSeeVirtualReturnLineItemIsNotGrayedOut() throws Throwable {
        Assert.assertTrue("Virtual Line item is not grayed out", vrLineItem.getAttribute("colour").contains("TBD"));
        logger.info("Virtual line item is grayed out");
    }

    @And("^I should see reason for return pre populated$")
    public void iShouldSeeReasonForReturnPrePopulated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String reasonSelected = vrLineItem.findElement(Elements.element("return_selection_page.reason_for_return")).getText();
        Assert.assertNotEquals("By Default reason for return for Virtual return items is unexpected.", "select reason for return", reasonSelected);
        logger.info("Reason for return is pre populated for VR item");
    }

    @And("^I should see reason for return not editable$")
    public void iShouldSeeReasonForReturnNotEditable() throws Throwable {
        Assert.assertFalse("Reason for return is editable", vrLineItem.isEnabled());
        logger.info("Reason for return is not editable for VR item");
    }

    @And("^I should see quantity dropdown not editable$")
    public void iShouldSeeQuantityDropdownNotEditable() throws Throwable {
        Assert.assertFalse(vrLineItem.findElement(Elements.element("return_selection_page.quantity")).isEnabled());
        logger.info("Quantity dropDown is not editable for VR item");
    }



    @And("^I should see \"([^\"]*)\" field highlighted with red color boarder$")
    public void iShouldSeeFieldHighlightedWithRedColorBoarder(String arg0) throws Throwable {
        Assert.assertTrue("Not highlighted with red color ", Elements.findElement("return_selection_page.toggle_line_item").getAttribute("class").contains("error alert"));
    }

    @And("^I should see quantity is one by default$")
    public void iShouldSeeQuantityIsOneByDefault() throws Throwable {
        //TODO : Write code here that turns the phrase above into concrete actions
        logger.info("Work in progress");
    }

    @When("^I have selected mandate fields to continue to refund page$")
    public void iHaveSelectedMandateFieldsToContinueToRefundPage() throws Throwable {
        WebElement lineItems = Elements.getRandomElement("return_selection_page.return_products_container");
        Clicks.click(lineItems.findElement(Elements.element("return_selection_page.toggle_line_item")));
        DropDowns.selectRandomValue(Elements.element("return_selection_page.reason_for_return"));
        Clicks.click("return_selection_page.continue_return");
    }

    @And("^I should see all the return options on page$")
    public void iShouldSeeReturnOptionsOnPage() throws Throwable {
        Assert.assertTrue("macys store option is not displayed", Elements.findElement("return_method_page.macys_store").isDisplayed());
        Assert.assertTrue("ups dropbox option is not displayed", Elements.findElement("return_method_page.ups_dropbox").isDisplayed());
        Assert.assertTrue("return pickup option is not dispalyed", Elements.findElement("return_method_page.return_pickup").isDisplayed());

    }

    @And("^I should see \"([^\"]*)\" button as pre-selected by default$")
    public void iShouldSeeButtonAsPreSelectedByDefault(String Option) throws Throwable {
        boolean isSelected = Elements.findElement("return_method_page.macys_store").isSelected();
        Assert.assertTrue(Option + " option is not selected", isSelected);
        logger.info(Option + " option is selected by default");
    }

    @And("^navigate to \"([^\"]*)\" page using \"([^\"]*)\" order$")
    public void navigateToPageUsingOrder(String pageName, String shipmentStatus) throws Throwable {
        shouldBeOnPage("my_account");
        if (macys()) {
            Clicks.clickIfPresent("my_account.my_order");
            Clicks.javascriptClick("my_account.view_order_history_link");
        } else {
            Clicks.click("site_menu.site_menu_title");
            Clicks.clickIfPresent("site_menu.order_status");
        }
        ReturnsActions ract = new ReturnsActions();
        String orderNum = Utils.getOrderNumber(shipmentStatus);
        Clicks.click(ract.orderContainerToSelect(orderNum));
        logger.info("View More Order Details button is not available, we have less than three orders on page");
        Clicks.click("order_history.return_items_button");
        logger.info("Tapped on Return Items button on OH page for order with shipment " + shipmentStatus);
        switch (pageName.toLowerCase()) {
            case "selection":
                Clicks.clickIfPresent("order_history.return_items_button");
                logger.info("Tapped on Return Items button on OH page for order with shipment " + shipmentStatus);
                break;
        }
    }


    @And("^I should see 'Gift Card' and 'Original Form of Payment' buttons are displayed$")
    public void iShouldSeeGiftCardAndOriginalFormOfPaymentButtonsAreDisplayed() throws Throwable {
        Assert.assertTrue("'Original Payment Displayed", Elements.findElement("refund_method_page.original_payment").isDisplayed());
        Assert.assertTrue("'Gift Card Payment Displayed", Elements.findElement("refund_method_page.gift_card_payment").isDisplayed());
    }



    @Then("^I should see shipping address as pickup address by default$")
    public void iShouldSeeShippingAddressAsPickupAddressByDefault() throws Throwable {
        Assert.assertTrue("return pickup address is not displayed", Elements.findElement("return_method_page.return_pickup_address").isDisplayed());
    }

    @And("^I should see alternate address link$")
    public void iShouldSeeAlternateAddressLink() throws Throwable {
        Assert.assertTrue("Check an Alternate Address is not displayed", Elements.findElement("return_method_page.check_alt_address_link").isDisplayed());
    }




    @And("^I should see original payment and gift card options$")
    public void iShouldSeeOriginalPaymentAndGiftCardOptions() throws Throwable {
        try {
            Assert.assertTrue("Original Payment not Displayed", Elements.findElement("refund_method_page.original_payment").isDisplayed());
            Assert.assertTrue("Gift Card Payment not Displayed", Elements.findElement("refund_method_page.gift_card_payment").isDisplayed());
        } catch (NullPointerException e) {
            Assert.fail("element extracted is null so can't check for display.");
        }
    }

    @And("^I should see default payment option as original payment$")
    public void iShouldSeeDefaultPaymentOptionAsOriginalPayment() throws Throwable {
        Assert.assertTrue("'Original Payment Displayed", Elements.findElement("refund_method_page.original_payment").isSelected());

    }

    @When("^I tap on back on \"([^\"]*)\" page$")
    public void iTapOnBackOnPage(String page) throws Throwable {
        Wait.untilElementPresent("return_selection_page.back_button");
        Clicks.click("return_selection_page.back_button");
        logger.info("Taped on back on " + page);
    }


    @Then("^I should see below error message$")
    public void iShouldSeeBelowErrorMessage(DataTable table) throws Throwable {
        String expectedErrorMessage = table.asList(String.class).get(0);
        String actualErrorMsg = Elements.getText("return_method_page.return_global_error");
        Assert.assertEquals("Error message is not matching", expectedErrorMessage, actualErrorMsg);
        logger.info("Error message is matching");
    }

    @And("^I should see remaining characters as (\\d+) below special instructions field$")
    public void iShouldSeeRemainingCharactersAsBelowSpecialInstructionsField(int charNum) throws Throwable {
        Wait.untilElementPresent("return_method_page.reurn_pickup_char_limit");
        Assert.assertTrue("Remaining character count is invalid", Elements.findElement("return_method_page.reurn_pickup_char_limit").getText().contains(Integer.toString(charNum)));
        logger.info("I should see: " + Elements.findElement("return_method_page.reurn_pickup_char_limit").getText());
    }

    @When("^I should be able to enter \"([^\"]*)\" characters in special instructions field$")
    public void iShouldBeAbleToEnterFewCharactersInSpecialInstructionsField(String charSeq) throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_instruction");
        TextBoxes.typeTextbox("return_method_page.return_pickup_instruction", charSeq);
        logger.info("Special Instructions Entered: " + charSeq);
    }

    @When("^I enter (\\d+) characters in special instructions field$")
    public void iEnterCharactersInSpecialInstructionsField(int charNum, DataTable table) throws Throwable {
        String expectedText = table.asList(String.class).get(0);
        Wait.untilElementPresent("return_method_page.return_pickup_instruction");
        TextBoxes.typeTextbox("return_method_page.return_pickup_instruction", expectedText);
        logger.info("In Special Instructions 200 characters entered: " + expectedText);
    }

    @And("^I should not be able to enter few characters in special instructions field$")
    public void iShouldNotBeAbleToEnterFewCharactersInSpecialInstructionsField() throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_instruction");
        Elements.findElement("return_method_page.return_pickup_instruction").sendKeys("above 200");
        Assert.assertFalse("Entered above 200 characters", Elements.findElement("return_method_page.return_pickup_instruction").getText().endsWith("above 200"));
        logger.info("Not able to enter more than 200 characters in Special Instruction field");
    }

    @And("^I should see original payment option only$")
    public void iShouldSeeOriginalPaymentOptionOnly() throws Throwable {
        Assert.assertFalse("Original Payment not Displayed", Elements.elementPresent("refund_method_page.original_payment"));
        Assert.assertFalse("Gift Card Payment Displayed", Elements.elementPresent("refund_method_page.gift_card_payment"));
    }

    @Then("^I should see global error message and field error disappeared$")
    public void iShouldSeeGlobalErrorMessageAndFieldErrorDisappeared() throws Throwable {
        Assert.assertFalse("Global Error Message displayed", Elements.elementPresent("return_method_page.return_global_error"));
        Assert.assertFalse("Global Error Message displayed", Elements.elementPresent("return_method_page.return_field_error"));
    }


    @And("^I select date \"([^\"]*)\" in return pickup$")
    public void iSelectDateInReturnPickup(String date) throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_day");
        DropDowns.selectByValue("return_method_page.return_pickup_day",date);
        logger.info("Date selected: " + date);
    }

    @And("^I select time \"([^\"]*)\" in return pickup$")
    public void iSelectTimeInReturnPickup(String time) throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_time");
        DropDowns.selectByText("return_method_page.return_pickup_time",time);
        logger.info("Time selected :" + time);
    }

    @Then("^I should see date selected as \"([^\"]*)\" in return pickup$")
    public void iShouldSeeDateSelectedAsInReturnPickup(String date) throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_day");
        String date_value = DropDowns.getSelectedValue(Elements.element("return_method_page.return_pickup_day"));
        Assert.assertTrue("Invalid date selected", date_value.contains(date.trim()));
        logger.info("Date value " + date_value);
    }

    @Then("^I should see time selected as \"([^\"]*)\" in return pickup$")
    public void iShouldSeeTimeSelectedAsInReturnPickup(String time) throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_time");
        String time_value = DropDowns.getSelectedValue(Elements.element("return_method_page.return_pickup_day"));
        Assert.assertTrue("Invalid time selected", time_value.contains(time.trim()));
        logger.info("Time value " + time_value);
    }

    @Then("^I shoudd see \"([^\"]*)\" in the special instruction field$")
    public void iShouddSeeInTheSpecialInstructionField(String instructionsExpected) throws Throwable {
        Wait.untilElementPresent("return_method_page.return_pickup_instruction");
        String instructionsActual = DropDowns.getSelectedValue(Elements.element("return_method_page.return_pickup_day"));
        Assert.assertEquals("Invalid instructions displayed", instructionsExpected, instructionsActual);
        logger.info("I can see Special instructions :" + instructionsActual);
    }

    @And("^I should be on \"([^\"]*)\" page$")
    public void iShouldBeOnReturnsSubCategoryPage(String page) throws Throwable {
        page = page.toLowerCase();
        Wait.forPageReady();
        switch (page) {
            case "return selection":
                Assert.assertTrue("Not on return selection page", onPage("return_selection_page"));
                break;
            case "refund method":
                Assert.assertTrue("Not on return refund page", onPage("refund_method_page"));
                break;
            case "return method":
                Assert.assertTrue("Not on return method page", onPage("return_method_page"));
                break;
            case "return summary":
                Assert.assertTrue("Not on return summary page", onPage("return_summary_page"));
                break;
            case "order details":
                Assert.assertTrue("Not on order details page", onPage("order_details"));
                break;
            default:
                logger.warn("Improper Return method chosen");
                Assert.fail("Improper Return method chosen");
        }
        logger.info("on " + page + " page");
    }

    @And("^I should navigate to \"([^\"]*)\" page$")
    public void iShouldNavigateToPage(String page) throws Throwable {
        page = page.toLowerCase();
        Wait.forPageReady();
        Thread.sleep(3000);
        switch (page) {
            case "return selection":
                Assert.assertTrue("Not Navigated to return selection page", onPage("return_selection_page"));
                break;
            case "refund method":
                Assert.assertTrue("Not Navigated to Refund Method Page", onPage("refund_method_page"));
                break;
            case "return method":
                Assert.assertTrue("Not navigated to return method page", onPage("return_method_page"));
                break;
            case "return summary":
                Assert.assertTrue("Not Navigated to Return Summary Page", onPage("return_summary_page"));
                break;
            case "return confirmation":
                Assert.assertTrue("Not Navigated to Return Conformation Page", onPage("return_confirmation_page"));
                break;
            case "order details":
                Assert.assertTrue("Not Navigated to Order Details Page", onPage("order_details"));
                break;
            case "order history":
                Assert.assertTrue("Not Navigated to Order History Page", onPage("order_status"));
                break;
            case "home":
                Assert.assertTrue("Not Navigated to Home Page", onPage("home"));
                break;
            default:
                logger.warn("Improper Page chosen");
                Assert.fail("Improper Page chosen");
        }
        logger.info("Navigated to " + page + " page");
    }

    @Then("^I should be navigated to order history page$")
    public void iShouldBeNavigatedToOrderHistoryPage() throws Throwable {
        Wait.forPageReady();
        shouldBeOnPage("order_status");
        logger.info("Navigated to Order History Page");
    }

    @Then("^I should see \"([^\"]*)\" field on return method page$")
    public void iShouldSeeFieldOnReturnMethodPage(String options) throws Throwable {
        switch (options) {
            case "pickup day":
                Assert.assertTrue("pickup day option is not displayed", Elements.findElement("return_method_page.return_pickup_day").isDisplayed());
                DropDowns.selectRandomValue("return_method_page.return_pickup_day");
                break;
            case "pickup time":
                Assert.assertTrue("pickup time option is not displayed", Elements.findElement("return_method_page.return_pickup_time").isDisplayed());
                DropDowns.selectRandomValue("return_method_page.return_pickup_time");
                break;
            case "special instructions":
                Assert.assertTrue("pickup instruction field is not displayed", Elements.findElement("return_method_page.return_pickup_instruction").isDisplayed());
                break;
        }
    }

    @When("^I select \"([^\"]*)\" as refund method$")
    public void iSelectAsRefundMethod(String paymentMode) throws Throwable {
        paymentMode= paymentMode.toLowerCase();
        switch (paymentMode) {
            case "original tender":
                Clicks.clickIfPresent("refund_method_page.original_payment");
                break;
            case "gift card":
                Clicks.clickIfPresent("refund_method_page.gift_card_payment");
                break;
        }
        logger.info("Selected Refund method: " + paymentMode);
        Wait.forPageReady();
    }

    @And("^I should see \"([^\"]*)\" button on return method page$")
    public void iShouldSeeButtonOnReturnMethodPage(String option) throws Throwable {
        option = option.toLowerCase();
        switch (option) {
            case "store drop":
                Assert.assertTrue("macys store option is not displayed", Elements.findElement("return_method_page.macys_store").isDisplayed());
                break;
            case "ups drop box":
                Assert.assertTrue("ups dropbox option is not displayed", Elements.findElement("return_method_page.ups_dropbox").isDisplayed());
                break;
            case "return pickup":
                Assert.assertTrue("return pickup option is not dispalyed", Elements.findElement("return_method_page.return_pickup").isDisplayed());
                break;
            default:
                logger.warn("Improper Return method chosen");
                Assert.fail();
        }
        logger.info(option + " option is displayed");
    }


    @And("^Only 'original form of payment' verbiage is displayed$")
    public void onlyOriginalFormOfPaymentVerbiageIsDisplayed() throws Throwable {
        Assert.assertTrue("'Original form of payment' verbiage is not displayed", Elements.findElement("refund_method_page.original_pay_verbiage").isDisplayed());
    }


    @And("^only 'original form of payment' verbiage is displayed by default$")
    public void onlyOriginalFormOfPaymentVerbiageIsDisplayedByDefault() throws Throwable {
        Assert.assertTrue(" Original Payment verbiage not Displayed", Elements.findElement("refund_method_page.original_pay_verbiage").isDisplayed());
        Assert.assertFalse(" Original Payment option is displayed ", Elements.findElement("refund_method_page.original_payment").isDisplayed());
        Assert.assertFalse("Gift Card Payment option is displayed ", Elements.findElement("refund_method_page.gift_card_payment").isDisplayed());
    }

    @And("^I should see \"([^\"]*)\" verbiage as it was selected by default$")
    public void iShouldSeeVerbiageAsItWasSelectedByDefault(String arg0) throws Throwable {
        Assert.assertTrue("Original Payment verbiage not Displayed", Elements.findElement("refund_method_page.original_pay_verbiage").isDisplayed());
    }


    @And("^I should see \"([^\"]*)\" verbiage as below$")
    public void iShouldSeeVerbiageAsBelow(String option, DataTable table) throws Throwable {
        option = option.toLowerCase();
        WebElement actualVerbiageElement;
        Wait.forPageReady();
        String expectedVerbiageText = table.asList(String.class).get(0).trim();
        logger.info("expected verbiage text : " + expectedVerbiageText);
        expectedVerbiageText = expectedVerbiageText.replace(" ","");
        switch (option) {
            case "original tender":
                actualVerbiageElement = Elements.findElement("refund_method_page.original_pay_verbiage");
                logger.info("Original Payment Verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertTrue("Original Payment Verbiage did not match", actualVerbiageElement.getText().replace(" ","").contains(expectedVerbiageText));
                break;
            case "gift card":
                actualVerbiageElement = Elements.findElement("refund_method_page.gift_card_pay_verbiage");
                logger.info("Macys Gift card Verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertTrue("Macys Gift card Verbiage did not match", actualVerbiageElement.getText().replace(" ","").contains(expectedVerbiageText));
                break;
            case "store drop":
                actualVerbiageElement = Elements.findElement("return_method_page.macys_store_verbiage");
                logger.info("Macy's Store Verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("Macys Store dropoff verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText().replace(" ",""));
                break;
            case "ups drop box":
                actualVerbiageElement = Elements.findElement("return_method_page.ups_dropbox_verbiage");
                logger.info("UPS drop box Verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertTrue("UPS dropoff verbiage did not match", actualVerbiageElement.getText().replace(" ","").contains(expectedVerbiageText));
                break;
            case "return pickup":
                actualVerbiageElement = Elements.findElement("return_method_page.return_pickup_verbiage");
                logger.info("Return Pickup Verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("Return Pickup verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText().replace(" ",""));
                break;
            default:
                logger.warn("Improper Verbiage type chosen");
                Assert.fail("Improper Verbiage type chosen");
        }
        logger.info(option + " option verbiage is displayed and expected");
    }


    @And("^I should see \"([^\"]*)\" page title verbiage as below$")
    public void iShouldSeePageTitleVerbiageAsBelow(DataTable table, String option) throws Throwable {
        option = option.toLowerCase();
        Wait.forPageReady();
        WebElement actualVerbiageElement;
        String expectedVerbiageText = table.asList(String.class).get(0).trim();
        logger.info("expected page title text : " + expectedVerbiageText);
        switch (option) {
            case "selection page":
                actualVerbiageElement = Elements.findElement("return_selection_page.verify_page");
                logger.info("Return selection page title verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("Selection page title verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText());
                break;
            case "refund method page":
                actualVerbiageElement = Elements.findElement("refund_method_page.verify_page");
                logger.info("Refund method page title verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("Refund method page title verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText());
                break;
            case "return method page":
                actualVerbiageElement = Elements.findElement("return_method_page.verify_page");
                logger.info("Return method page title verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("return method page title verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText());
                break;
            case "summary page":
                actualVerbiageElement = Elements.findElement("return_method_page.verify_page");
                logger.info("Return summary page title verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("Summary page title verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText());
                break;
            case "confirmation page":
                actualVerbiageElement = Elements.findElement("return_method_page.verify_page");
                logger.info("Return Conformation page title verbiage is: " + actualVerbiageElement.getText().trim());
                Assert.assertEquals("Conformation page title verbiage did not match", expectedVerbiageText, actualVerbiageElement.getText());
                break;
            default:
                logger.warn("Improper Page chosen");
                Assert.fail("Improper Page chosen");
        }
        logger.info(option + "title verbiage is displayed and expected");
    }


    @When("^I select \"([^\"]*)\" button on page$")
    public void iSelectButtonOnPage(String buttonName) throws Throwable {
        Wait.forPageReady();
        switch (buttonName) {
            case "cancel":
                Clicks.click("return_selection_page.cancel_button");
                break;
            case "continue":
                if(onPage("refund_method_page"))
                {
                    if(Elements.findElement("refund_method_page.contact_email").getAttribute("value").equals("")) TextBoxes.typeTextbox("refund_method_page.contact_email", "Test@test.com");
                    if(Elements.findElement("refund_method_page.contact_phone").getAttribute("value").equals("")) TextBoxes.typeTextbox("refund_method_page.contact_phone", "1234567890");
                    Clicks.click("return_selection_page.continue_button");
                }
                else if(onPage("return_method_page")){
                    Clicks.click("return_selection_page.continue_button");
                    Thread.sleep(5000);
                }
                else Clicks.click("return_selection_page.continue_button");
                break;
            case "submit":
                Clicks.click("return_summary_page.submit_button");
                Thread.sleep(5000);
                break;
            case "continue shopping":
                Clicks.click("return_confirmation_page.continue_shopping");
                break;
            default:
                logger.warn("Improper Button chosen");
                Assert.fail();
        }
        logger.info("Tapped on button " + buttonName);
        Wait.forPageReady();
    }

    @When("^I select \"([^\"]*)\" as return method$")
    public void iSelectAsReturnMehtod(String returnOption) throws Throwable {
        returnOption = returnOption.toLowerCase();
        switch (returnOption) {
            case "store drop":
                Clicks.clickIfPresent("return_method_page.macys_store");
                break;
            case "ups drop box":
                Clicks.clickIfPresent("return_method_page.ups_dropbox");
                break;
            case "return pickup":
                Clicks.clickIfPresent("return_method_page.return_pickup");
                break;
            default:
                logger.warn("Improper Return method chosen");
                Assert.fail();
        }
        logger.info("Selected return method: " + returnOption);
        Thread.sleep(2000);
        Wait.forPageReady();
    }

    @And("^I navigate to order history page on mobile$")
    public void iNavigateToOrderHistoryPageOnMobile() throws Throwable {
        Navigate.visit(RunConfig.url + "/service/order-status");
        Wait.forPageReady();
        logger.info("Tapped on Order History Link");
        Cookies.addCookie("QE_RESPONSIVE_RETURNS","true");
        Navigate.browserRefresh();
        Wait.forPageReady();
        logger.info("Added Cookie QE_RESPONSIVE_RETURNS = : "+Cookies.getCookieValue("QE_RESPONSIVE_RETURNS"));

    }

    @And("^I select return items button in mobile \"([^\"]*)\" page$")
    public void iSelectReturnItemsButtonInMobilePage(String page) throws Throwable {
        page = page.toLowerCase();
        Wait.forPageReady();
        switch (page) {
            case "oh":
                Wait.untilElementPresent("order_status.return_items_button");
                Clicks.click("order_status.return_items_button");
                break;
            case "od":
                Wait.untilElementPresent("order_details.return_items_button");
                Clicks.click("order_details.return_items_button");
                break;
            default:
                logger.warn("Improper page chosen");
                Assert.fail();
        }
        Wait.forPageReady();
        Thread.sleep(3000);
        logger.info("Selected Return Items button for Order " + orderNumber + " in " + page + " page");
    }

    @And("^I tap order \"([^\"]*)\" on history page$")
    public void I_select_order_on_oh_page(String orderType) throws Throwable {
        ReturnsActions ra = new ReturnsActions();
        orderNumber = Utils.getOrderNumber(orderType);
        Assert.assertNotNull("desired order type in record absent", orderNumber);
        logger.info(String.format("order %s exist in record for order type %s", orderNumber, orderType));
        Clicks.click(ra.orderContainerToSelect(orderNumber));
        logger.info("tapped on order " + orderType + " to verify add to bag button");
        Wait.secondsUntilElementPresent("order_details.line_Items_container", 10);
        logger.info("Navigate to Order details page");
    }

    @When("^I expand order by tapping on \"([^\"]*)\" order container$")
    public void iExpandOrderByTappingOnOrderContainer(String orderType) throws Throwable {
        ReturnsActions ra = new ReturnsActions();
        orderNumber = Utils.getOrderNumber(orderType);
        WebElement orderEle = ra.orderContainerToSelect(orderNumber);
        Clicks.click(orderEle);
        logger.info("Tapped on order " + orderNumber + " and it got expanded");
    }

    @When("^I navigate to return review summary page using \"([^\"]*)\" and \"([^\"]*)\" as return params with \"([^\"]*)\" order$")
    public void iNavigateToReturnReviewSummaryPageUsingAndAsReturnParams(String refundMethod, String returnMethod, String orderType) throws Throwable {

        iNavigateToOrderHistoryPageOnMobile();
        iExpandOrderByTappingOnOrderContainer(orderType);
        if(macys()) iSelectReturnItemsButtonInMobilePage("OH");
        else if(bloomingdales()) iSelectReturnItemsButtonInMobilePage("OD");
        iShouldNavigateToPage("return selection");
        iSelectItemsOnSelectionPage("1");
        iSelectButtonOnPage("continue");
        iShouldNavigateToPage("refund method");
        iSelectAsRefundMethod(refundMethod);
        iSelectButtonOnPage("continue");
        iShouldNavigateToPage("return method");
        if(returnMethod.equalsIgnoreCase("return pickup")){
            iSelectAsReturnMehtod(returnMethod);
            DropDowns.selectByIndex("return_method_page.return_pickup_day",1);
            Thread.sleep(1000);
            DropDowns.selectByIndex("return_method_page.return_pickup_time",1);
            TextBoxes.typeTextbox("return_method_page.return_pickup_instruction","Test Special Instructions");
        }
        else iSelectAsReturnMehtod(returnMethod);
        iSelectButtonOnPage("continue");
        iShouldNavigateToPage("return summary");

    }

    @And("^I should see short description for each item$")
    public void iShouldSeeShortDescriptionForEachItem() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("return_summary_page.item_description");
        Assert.assertTrue("",Elements.anyPresent("return_summary_page.item_description"));
        logger.info("Product Short description: "+Elements.findElement("return_summary_page.item_description").getText() +"is displayed");
    }

    @And("^I should see \"([^\"]*)\" on summary page$")
    public void iShouldSeeOnSummaryPage(String option) throws Throwable {
        option = option.toLowerCase();
        Wait.forPageReady();
        Thread.sleep(3000);
        switch (option) {
            case "merchandise total":
                Assert.assertTrue("Merchandise total not found in Summary page",Elements.elementInView("return_summary_page.merchandise_total"));
                break;
            case "tax":
                Assert.assertTrue("Tax not found in Summary page",Elements.elementInView("return_summary_page.tax_total"));
                break;
            case "return delivery fee":
                Assert.assertTrue("Return delivery fee not found in Summary page",Elements.elementInView("return_summary_page.delivery_fee"));
                break;
            case "total estimated credit":
                Assert.assertTrue("Estimated credit not found in Summary page",Elements.elementInView("return_summary_page.estimated_credit"));
                break;
            default:
                logger.warn("Improper option chosen");
                Assert.fail();
        }
        logger.info("I Can see : " + option + "On Summary page");
    }

    @And("^I should see \"([^\"]*)\" on confirmation page$")
    public void iShouldSeeOnConfirmationPage(String option) throws Throwable {
        option = option.toLowerCase();
        Wait.forPageReady();
        switch (option) {
            case "original card details":
                Assert.assertTrue("Original card details not found in Confirmation page",Elements.elementInView(ReturnsActions.getConforamtionItem("Refund To:")));
                break;
            case "gift card mailing address":
                Assert.assertTrue("Gift card mailing address not found in Confirmation page",Elements.elementInView("return_confirmation_page.gift_card_address"));
                break;
            case "qr code":
                Assert.assertTrue("QR code not found in Confirmation page",Elements.elementInView("return_confirmation_page.qr_code"));
                break;
            case "reservation number":
                Assert.assertTrue("Reservation number not found in Confirmation page",Elements.elementInView("return_confirmation_page.reservation_num"));
                break;
            case "returned date":
                Assert.assertTrue("Returned date not found in Confirmation page",Elements.elementInView("return_confirmation_page.returned_date"));
                break;
            case "estimated credit":
                Assert.assertTrue("Estimated credit not found in Confirmation page",Elements.elementInView("return_confirmation_page.estimated_credit"));
                break;
            case "order number":
                Assert.assertTrue("Order number not found in Confirmation page",Elements.elementInView("return_confirmation_page.order_number"));
                break;
            case "ups tracking number":
                Assert.assertTrue("UPS tracking number not found in Confirmation page",Elements.elementInView(ReturnsActions.getConforamtionItem("UPS Tracking #:")));
                break;
            case "customer email":
                Assert.assertTrue("Customer email not found in Confirmation page", Elements.elementInView(ReturnsActions.getConforamtionItem("Email:")));
                break;
            case "send to another email link":
                Assert.assertTrue("Send to another email link not found in Confirmation page",Elements.elementInView("return_confirmation_page.another_email"));
                break;
            case "find the nearest ups store field":
                Assert.assertTrue("Find the nearest UPS Store field not found in Confirmation page",Elements.elementInView("return_confirmation_page.near_ups_store"));
                break;
            case "pickup address":
                Assert.assertTrue("Pickup address not found in Confirmation page",Elements.elementInView("return_confirmation_pickup_address"));
                break;
            case "pickup date":
                Assert.assertTrue("Pickup date not found in Confirmation page", Elements.elementInView(ReturnsActions.getConforamtionPickupItem("Date:")));
                break;
            case "pickup time":
                Assert.assertTrue("Pickup time not found in Confirmation page", Elements.elementInView(ReturnsActions.getConforamtionPickupItem("Time:")));
                break;
            case "special instructions":
                Assert.assertTrue("Special instructions not found in Confirmation page", Elements.elementInView(ReturnsActions.getConforamtionPickupItem("Special Instructions:")));
                break;
            case "store locator link":
                Assert.assertTrue("Special instructions not found in Confirmation page", Elements.elementPresent(By.partialLinkText("store locator")));
                break;
            default:
                logger.warn("Improper option chosen");
                Assert.fail();
        }
        logger.info("I Can see : " + option + "On Confirmation page");
    }

    @And("^I should see refund payment type as \"([^\"]*)\" on summary page$")
    public void iShouldSeeRefundPaymentTypeAsMacySGiftCardOnThePage(String returnOption) throws Throwable {
        returnOption = returnOption.toLowerCase();
        Wait.forPageReady();
        Wait.untilElementPresent("return_summary_page.refund_payment");
        WebElement refundMethodValue = Elements.findElement("return_summary_page.refund_payment");
        switch (returnOption) {
            case "original tender":
                Assert.assertTrue("Original pament not found in Summary page",!refundMethodValue.getText().contains("Macy's Gift Card"));
                break;
            case "gift card":
                Assert.assertTrue("Gift card refund not found in Summary page",refundMethodValue.getText().contains("Macy's Gift Card"));
                break;
            default:
                logger.warn("Improper Refund method chosen");
                Assert.fail();
        }
        logger.info("Refund method displayed as: " + returnOption + "On Summary page");
    }

    @And("^I should see return method as \"([^\"]*)\" on summary page$")
    public void iShouldSeeReturnMethodAsDropOffAtMacySOnThePage(String returnOption) throws Throwable {
        returnOption = returnOption.toLowerCase();
        Wait.forPageReady();
        Wait.untilElementPresent("return_summary_page.return_method");
        WebElement returnMethodValue = Elements.findElement("return_summary_page.return_method");
        switch (returnOption) {
            case "store drop":
                Assert.assertTrue("Store drop off not found in Summary page", returnMethodValue.getText().contains("Store"));
                break;
            case "ups drop box":
                Assert.assertTrue("UPS drop off not found in Summary page",returnMethodValue.getText().contains("Drop off at UPS/dropbox"));
                break;
            case "return pickup":
                Assert.assertTrue("Return pickup not found in Summary page",returnMethodValue.getText().contains("Return Pickup"));
                break;
            default:
                logger.warn("Improper Return method chosen");
                Assert.fail();
        }
        logger.info("Return method displayed as:  " + returnOption + "On Summary page");
    }

    @And("^I should see 'continue shopping' button on page$")
    public void iShouldSeeContinueShoppingButtonOnPage() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("return_confirmation_page.continue_shopping");
        Assert.assertTrue("Continue shopping button missing on page",Elements.anyPresent("return_confirmation_page.continue_shopping"));
        logger.info("Continue Shopping button present on Conformation page");
    }

    @And("^I should see below print mailing label with \"([^\"]*)\"$")
    public void iShouldSeeBelowPrintMailingLabel(String returnMethod, DataTable table) throws Throwable {
        returnMethod = returnMethod.toLowerCase();
        Wait.forPageReady();
        WebElement actualPrintLable = null;
        String expectedPrintLable = table.asList(String.class).get(0).trim();
        logger.info("expected print label : " + expectedPrintLable);
        switch (returnMethod) {
            case "store drop":
                actualPrintLable = Elements.findElement("return_confirmation_page.print_lable_store");
                logger.info("Store drop off print lable is: " + actualPrintLable.getText().trim());
                Assert.assertTrue("Store drop off print lable did not match", actualPrintLable.getText().contains(expectedPrintLable));
                break;
            case "ups drop box":
                actualPrintLable = Elements.findElement("return_confirmation_page.print_lable_ups");
                logger.info("UPS drop off print lable is: " + actualPrintLable.getText().trim());
                Assert.assertTrue("UPS drop off print lable did not match", actualPrintLable.getText().contains(expectedPrintLable));
                break;
            case "return pickup":
                actualPrintLable = Elements.findElement("return_confirmation_page.print_lable_pickup");
                logger.info("Pickup print label is: " + actualPrintLable.getText().trim());
                Assert.assertTrue("Pickup print label did not match", actualPrintLable.getText().contains(expectedPrintLable));
                break;
            default:
                logger.warn("Improper return method chosen");
                Assert.fail("Improper return method chosen");
        }
        logger.info(returnMethod + "print lable is displayed and expected");

    }

    @And("^I should see below note on confirmation page$")
    public void iShouldSeeBelowNoteOnConfirmationPage(DataTable table) throws Throwable {
        Wait.forPageReady();
        WebElement actualNote = Elements.findElement("return_confirmation_page.print_lable_store");
        String expectedNote = table.asList(String.class).get(0).trim();
        logger.info("expected print label : " + expectedNote);
        logger.info("Pickup Note is: " + actualNote.getText().trim());
        Assert.assertTrue("Pickup Note did not match", actualNote.getText().contains(expectedNote));
    }

    @Then("^I should validate \"([^\"]*)\" order with \"([^\"]*)\" and \"([^\"]*)\" on conformation page$")
    public void iShouldValidateOnConformationPage(String orderType, String refundMethod, String returnMethod) throws Throwable {
        logger.info("----------------------------------------------------------------------------------------------");
        logger.info("Conformation Line Items: "+confimationLineItems);
        logger.info("Conformation Details: "+confirmationDetails);
        logger.info("Selected Line Items: "+selectedLineItems);
        logger.info("Refund Details: "+refundDetails);
        logger.info("Return method details: "+returnMethodDetails);
        logger.info("----------------------------------------------------------------------------------------------");
    }

    @Then("^I should get all the values on \"([^\"]*)\"$")
    public void iShouldGetAllTheValuesOnPage(String page) throws Throwable {
        page = page.toLowerCase();
        Wait.forPageReady();
        ReturnsActions ra = new ReturnsActions();
        switch (page) {
            case "selection page":
                selectedLineItems = ra.eligibleItemsToReturn();
                break;
            case "refund method page":
                refundDetails = ra.getRefudndDetails();
                break;
            case "return method page":
                returnMethodDetails = ra.getReturnMethodDetails();
                break;
            case "summary page":
                break;
            case "confirmation page":
                confimationLineItems = ra.confirmationItemDetails();
                confirmationDetails = ra.returnConfirmationHeaderDetails();
                 break;
            default:
                logger.warn("Improper Page chosen");
                Assert.fail("Improper Page chosen");
        }
        logger.info(page + "Information retrieved..");
    }
}
