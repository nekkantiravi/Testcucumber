package com.macys.sdt.projects.Customer.Rebuy.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.Rebuy.actions.MEW.OrderMods;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cchandragiri on 7/6/2017.
 */
public class MewRebuySteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MewRebuySteps.class);
    private int bagCount = 0;
    private String addToBagMessage = "";
    private WebElement orderContainer;
    private String upcId;
    private String productName = "";
    private WebElement lineItemToSelect;


    @Then("^I should be navigate to order details page$")
    public void I_should_be_navigate_to_order_details_page() throws Throwable {
        onPage("order_details");
        logger.info("Navigated to order details page");
    }

    @And("^I should see add to bag button for \"([^\"]*)\" line item on \"([^\"]*)\" page$")
    public void I_should_see_add_to_bag_button_on_page(String lineItemStatus, String pageName) throws Throwable {
        logger.info(String.format("Checking for order type %s in page %s", lineItemStatus, pageName));
        Wait.forPageReady();
        WebElement addToBag = null;
        OrderMods om = new OrderMods();
        WebElement line_item = null;
        switch (pageName) {
            case "OD":
                line_item = om.getLineitemodODPage(productName, lineItemStatus);
                break;
            case "OH":
                line_item = om.getLineitemodOHPage(orderContainer, lineItemStatus);
                break;
        }
        if (line_item == null) {
            Assert.fail("ERROR: TEST DATA:- Line item not available");
        }
        if (macys()) {
            try {
                addToBag = Elements.findElement(By.xpath("//button[contains(@data-upcid,'" + upcId + "')]"));
            } catch (Exception ignore) {
                logger.info("rebuy Button not available for that line item");
            }
        } else if (bloomingdales()) {
            addToBag = Elements.findElement("order_details.add_to_bag_button");
        }
        Assert.assertNotNull("Buy Again button is not displaying", addToBag);
        logger.info("Buy Again button displaying as expected.");
    }

    @When("^I tap on add to bag button on \"([^\"]*)\" page$")
    public void I_select_add_to_bag_button_on_page(String pageName) throws Throwable {
        OrderMods om = new OrderMods();
        switch (pageName) {
            case "OD":
                do {
                    Navigate.scrollPage(0, 500);
                } while (!Elements.findElement("order_details.add_to_bag_button").isDisplayed());
                Clicks.click("order_details.add_to_bag_button");
                break;
            case "OH":
                Clicks.click(lineItemToSelect.findElement(Elements.element("order_status.add_to_bag_button")));
                break;
        }
        Wait.secondsUntilElementPresent("order_status.added_to_bag_message", 10);
        addToBagMessage = Elements.getText("order_status.added_to_bag_message");
        logger.info("Add to bag conformation message is: "+addToBagMessage);
        if (pageName.equals("OD")) {
            Navigate.browserBack();
        }
        Wait.animationDone();
        logger.info("Selected Buy Again button on Page.");
        Clicks.click("header.my_bag");
    }

    @Then("^I tap order details button for selected order$")
    public void tap_on_order_to_navigate_to_page() throws Throwable {
        // View order details button is usually not in the screen and need to scroll down for normal click.
        // Code to adjust the button to the screen seems overkill when compare to javascript click.
        Clicks.javascriptClick("order_status.view_order_detail");
        Wait.forPageReady();
        Wait.untilElementPresent("order_details.order_details_container");
        logger.info("Selected More detail button to navigate to OD page");
    }

    @Then("^I should not see add to bag button for \"([^\"]*)\" line item on \"([^\"]*)\" page$")
    public void i_should_not_see_add_to_bag_on_page(String lineItemStatus, String pageName) throws Throwable {
        OrderMods om = new OrderMods();
        WebElement historyButtons;
        boolean visibility = false;
        logger.info("Line item details to be validated" + lineItemToSelect);
        logger.info("Line item with product name to be validated " + productName);
        if (pageName.equals("OH")) {
            try {
                visibility = lineItemToSelect.findElement(Elements.element("order_status.add_to_bag_button")).isDisplayed();
            } catch (NoSuchElementException nse) {
                logger.info("Rebuy Button is not displaying or issue with element identification");
            }
            Assert.assertFalse("Rebuy Button displaying which is  unexpected", visibility);
            logger.info("Rebuy Button is not displaying which is expected.");
        } else {
            List<WebElement> lineItems = Elements.findElement("order_details.products_block").findElements(By.className("m-order-details-product"));
            WebElement lineItem = null;
            for (WebElement e : lineItems) {
                if (e.getText().contains(productName)) {
                    lineItem = e;
                    break;
                }
            }
            try {
                historyButtons = lineItem.findElement(Elements.element("order_details.history_button"));
                Assert.assertFalse(historyButtons.findElement(Elements.element("order_details.add_to_bag_button")).isDisplayed());
            } catch (NullPointerException | NoSuchElementException nse) {
                logger.info("ERROR: DATA - Line item not found with product name " + productName);
            }
        }

    }

    @Then("^I should see add to bag successful message on page$")
    public void i_should_see_add_to_bag_successful_message_on_page() throws Throwable {
        boolean addedToBag = false;
        if (macys())
            addedToBag = addToBagMessage.contains("was added to bag");
        else if (bloomingdales())
            addedToBag = addToBagMessage.contains("added to your Brown Bag");
        Assert.assertTrue("added to bag confirmation is not shown", addedToBag);
        logger.info("Add to bag message displaying as expected");
    }

    @Then("^I should see add to bag successful message on page for each item$")
    public void i_should_see_add_to_bag_successful_message_on_page_for_each_item() throws Throwable {
        boolean addedToBag = false;
        if (macys()) {
            addedToBag = addToBagMessage.contains("was added to bag");
        } else if (bloomingdales()) {
            addedToBag = addToBagMessage.contains("added to your Brown Bag");
        }
        Assert.assertTrue("added to bag confirmation is not shown", addedToBag);
        logger.info("Add to bag message displaying as expected");
    }


    @When("^I navigate to oh page from my account page using mobile website$")
    public void navigate_to_mobile_oh_page() throws InterruptedException {
        logger.debug("Navigating to bag using URL");
        Navigate.visit(RunConfig.url + "/bag/index.ognc");
        logger.debug("Navigating to bag to remove items from bag");
        OrderMods.emptyBag();
        logger.debug("Removed all items in bag");
        if (Elements.anyPresent("header.bag_count")) {
            bagCount = OrderMods.getBagCount();
        } else if (Elements.anyPresent("order_details.my_bag")) {
            bagCount = Integer.parseInt(Elements.findElement("order_details.my_bag").getText().replaceAll("\\D+", ""));
        } else {
            bagCount = 0;
        }
        logger.debug("Bag Count before adding item to bag " + bagCount);
        try {
            Navigate.visit(RunConfig.url + "/service/order-status");
        } catch (Exception e) {
            Clicks.clickIfPresent("my_account.my_order");
            Clicks.javascriptClick("my_account.view_order_history_link");
        }
    }

    @And("I tap on add to bag button on \"([^\"]*)\" page for multiple line items$")
    public void tap_add_to_bag_for_different_line_items(String pageName) throws Throwable {
        Wait.forPageReady();
        List<WebElement> list = null;
        if (Elements.elementInView("header.bag_count")) {
            bagCount = OrderMods.getBagCount();
        } else {
            bagCount = 0;
        }
        logger.info("Bag Count before adding items to bag " + bagCount);
        switch (pageName) {
            case "OH":
                list = Elements.findElements("order_status.add_to_bag_button").stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
                break;
            case "OD":
                list = Elements.findElements("order_details.add_to_bag_button");
                break;
        }
        if (list == null) return;
        for (WebElement e : list) {
            try {
                Wait.animationDone();
                Clicks.click(e);
                Wait.secondsUntilElementPresent("order_status.added_to_bag_message", 10);
                addToBagMessage = Elements.getText("order_status.added_to_bag_message");
            } catch (NullPointerException ignore) {
                logger.info("No add to bag buttons present on page now");
            }
        }
        Assert.assertTrue("Bag count not updated properly", bagCount < OrderMods.getBagCount());
        logger.info("Selected all items and validated bag count on shopping bag page");
    }

    @And("^I tap order \"([^\"]*)\" on history page$")
    public void I_select_order_on_oh_page(String orderType) throws Throwable {
        String orderNumber = Utils.getOrderNumber(orderType);
        Assert.assertNotNull("desired order type in record absent", orderNumber);
        logger.info(String.format("order %s exist in record for order type %s", orderNumber, orderType));
        OrderMods om = new OrderMods();
        orderContainer = om.getOrderContainer(orderNumber);
        orderContainer.click();
        logger.info("tapped on order " + orderType + " to verify add to bag button");
        if (macys()) {
            lineItemToSelect = om.getLineitemodOHPage(orderContainer, orderType);
            productName = lineItemToSelect.findElement(Elements.element("order_status.product_name")).getText();
            logger.info("Product Name to be verified is: "+productName);
            try {
                upcId = lineItemToSelect.findElement(Elements.element("order_status.add_to_bag_button")).getAttribute("data-upcid");
                logger.info("upc id : " + upcId);
            } catch (Exception ignore) {
                logger.info("rebuy Button not available for that line item");
            }
        } else {
            Wait.secondsUntilElementPresent("order_details.line_Items_container", 10);
            logger.info("Navigate to Order details page");
        }
    }

    @And("^I tap order \"([^\"]*)\" on history page to validate add to bag selection$")
    public void I_select_multiple_order_on_oh_page(String orderType) throws Throwable {
        // Work in progress
        String orderNumber = Utils.getOrderNumber(orderType);
        Assert.assertNotNull("desired order record absent", orderNumber);
        OrderMods om = new OrderMods();
        orderContainer = om.getOrderContainer(orderNumber);
        orderContainer.click();
        logger.info("tapped on order " + orderType + " to verify add to bag button");
    }

    @When("^I tap on add to bag button of \"([^\"]*)\" on \"([^\"]*)\" page for maximum item availability$")
    public void i_tap_on_add_to_bag_button_of_on_page_for_maximum_item_availability(String lineItem, String pageName) throws Throwable {
        do {
            if (pageName.equals("OH")) {
                Clicks.click("order_status.add_to_bag_button");
                logger.info("Selected Buy Again button");
            } else {
                Clicks.click("order_details.add_to_bag_button");
                logger.info("Selected Buy Again button");
            }
        } while (Elements.getText("order_details.added_to_bag_message").contains("was added to bag"));
        logger.info("Items are added to until limit messages displayed");
    }

    @Then("^I should see error message on page$")
    public void validate_error_message() {
        if (macys()) {
            Assert.assertTrue(Elements.getText("order_details.added_to_bag_message").contains("this item."));
        } else if (bloomingdales()) {
            Assert.assertTrue(addToBagMessage.contains("this item."));
        }
        logger.info("Error Message displaying as expected.");
    }

    /**
     * Navigates to an order page using given order and user type data
     * <p>
     * Order details come from "return_orders.json" resource file in shared data
     * </p>
     * <p>
     * //     * @param orderType "submitted", "intransit", or "transit"
     * //     * @param userType  "guest" or "signed in"
     * //     * @throws Throwable if any exception occurs
     * //
     */
//    @When("^I select order \"([^\"]*)\" to do lookup as (signed in|guest) user$")
//    public void I_select_order_as_specified_user(String orderType, String userType) throws Throwable {
//        HashMap<String, String> order = new HashMap<>();
//        order.put("return_order", orderType);
//        returnOrderDetails = Utils.getReturnOrders(order);
//    }

//    @And("^I lookup with order number and \"([^\"]*)\" on oh page$")
//    public void look_up_order_on_oh_page(String lookupUsing) throws Throwable {
//        logger.info("On Order History Page");
//        Wait.secondsUntilElementPresent("order_status.order_number_lookup", 10);
//        logger.info("Entering order number for order lookup " + returnOrderDetails.get("order_number").toString());
//        TextBoxes.typeTextbox(Elements.element("order_status.order_number_lookup"), returnOrderDetails.get("order_number").toString());
//        switch (lookupUsing) {
//            case "phone number":
//                TextBoxes.typeTextbox(Elements.element("order_status.phone_number"), returnOrderDetails.get("phone_number").toString());
//                break;
//            case "email":
//                TextBoxes.typeTextbox(Elements.element("order_status.email"), returnOrderDetails.get("email").toString());
//                break;
//        }
//        Clicks.click(Elements.element("order_status.view_order_details"));
//        logger.info("Tapped on View Order Details button");
//    }
    @Then("^I should \"([^\"]*)\" add to bag button for \"([^\"]*)\" line item$")
    public void i_should_see_add_to_bag_button_for_line_item(String visibility, String lineItemStatus) throws Throwable {
        logger.info("On Details page");
        WebElement addToBagButton = null;
        OrderMods om = new OrderMods();
        WebElement shipment = om.getShipmentFromOrderDetails(lineItemStatus);
        switch (visibility) {
            case "not see":
                if (lineItemStatus.equalsIgnoreCase("rebuy_gwp")) {
                    List<WebElement> list = shipment.findElements(Elements.element("order_details.line_items"));
                    for (WebElement e : list) {
                        if (e.getText().contains("$0.00")) {
                            try{
                            addToBagButton = e.findElement(Elements.element("order_details.add_to_bag_button"));
                            Assert.assertFalse("Rebuy Button displaying, which is not expected",addToBagButton.isDisplayed());
                            } catch (NoSuchElementException IgnoreElement) {
                                //Ignoring as Add to bag button missing is expected
                                logger.info("Rebuy Button missing, with is expected.");
                            }
                        }
                    }
                } else {
                    try {
                        addToBagButton = shipment.findElement(Elements.element("order_details.add_to_bag_button"));
                        Assert.assertFalse("Rebuy Button displaying, which is not expected", addToBagButton.isDisplayed());
                    } catch (NoSuchElementException IgnoreElement) {
                        //Ignoring as Add to bag button missing is expected
                        logger.info("Rebuy Button missing, with is expected.");
                    }
                }
                logger.info("Rebuy Button not displaying, which is expected");
                break;

            case "see":
                Wait.secondsUntilElementPresent("order_details.add_to_bag_button", 10);
                Assert.assertTrue(shipment.findElement(Elements.element("order_details.add_to_bag_button")).isDisplayed());
                logger.info("Rebuy Button displaying as expected");
                break;
        }
    }

    @Then("^I should be navigated to order details page$")
    public void i_should_be_navigated_to_order_details_page() throws Throwable {
        Wait.secondsUntilElementPresent("order_details.order_details_barcode", 10);
        Assert.assertTrue(Elements.findElement("order_details.order_details_barcode").isDisplayed());
        logger.info("Navigated to Order Details page");
    }

    @When("^I tap on add to bag button on page for multiple line items$")
    public void i_tap_on_all_add_to_brown_bag_button_on_page() throws Throwable {
        Wait.forPageReady();
        List<WebElement> list = null;
        list = Elements.findElements("order_details.add_to_bag_button");
        Assert.assertNotNull("Add to bag buttons missing", list);
        for (WebElement e : list) {
            try {
                Wait.animationDone();
                if (macys()) Clicks.click(e);
                else if (bloomingdales()) Clicks.javascriptClick(e);
                Wait.animationDone();
                Wait.secondsUntilElementPresent("order_status.added_to_bag_message", 10);
                addToBagMessage = Elements.getText("order_status.added_to_bag_message");
            } catch (NullPointerException ignore) {
                logger.info("No add to bag buttons present on page now");
            }
        }
        Assert.assertTrue("Bag count not updated properly", bagCount < OrderMods.getBagCount());
        logger.info("Selected all items and validated bag count on shopping bag page");
    }

    @When("^I tap on add to bag button of \"([^\"]*)\" on page$")
    public void i_tap_on_add_to_brown_bag_button(String lineItemStatus) throws Throwable {
        Wait.secondsUntilElementPresent("order_details.add_to_bag_button", 10);
        Assert.assertNotNull("Add to bag button is missing", Elements.findElement("order_details.add_to_bag_button"));
        Clicks.javascriptClick("order_details.add_to_bag_button");
        Wait.animationDone();
        Wait.secondsUntilElementPresent("order_status.added_to_bag_message", 10);
        addToBagMessage = Elements.getText("order_status.added_to_bag_message");
        Clicks.click("order_details.qv_close");
        Wait.forPageReady();
        Assert.assertTrue("Bag count not updated properly", bagCount < OrderMods.getBagCount());
        logger.info("validated bag count on shopping bag page");
    }

    @When("^I tap on add to bag button of \"([^\"]*)\" on OD page for maximum item availability$")
    public void iTapOnAddToBagButtonOfOnODPageForMaximumItemAvailability(String lineItem) throws Throwable {
        do {
            if(Elements.elementInView("order_details.qv_close")) Clicks.click("order_details.qv_close");
            Wait.secondsUntilElementPresent("order_details.add_to_bag_button",5);
            Clicks.javascriptClick("order_details.add_to_bag_button");
            Wait.secondsUntilElementPresent("order_status.added_to_bag_message", 2);
            if (!Elements.elementInView("order_status.added_to_bag_message")) break;
            logger.info("Selected Buy Again button");
        } while (Elements.getText("order_details.added_to_bag_message").contains("added to your Brown Bag"));
        Wait.secondsUntilElementPresent("order_details.error_messeage", 5);
        addToBagMessage = Elements.getText("order_details.error_messeage");
        logger.info("Items are added to until limit messages displayed");
    }

    @When("^I select product name on \"([^\"]*)\" for rebuy line item$")
    public void IselectLinkonPage(String pageName) throws Throwable {
        if (macys()) {
            Clicks.javascriptClick(lineItemToSelect.findElement(Elements.element("order_status.product_name")));
        } else {
            Clicks.javascriptClick(Elements.findElement("order_status.product_name"));
        }
        Wait.secondsUntilElementPresent("product_details.product_name", 30);
        logger.info("Selected product name link on page");
    }
}