package com.macys.sdt.projects.Customer.Rebuy.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.ReturnService;
import com.macys.sdt.projects.Customer.Rebuy.actions.website.RebuyOrderStatusPage;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class RebuySteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(RebuySteps.class);

    private JSONObject returnOrderDetails;
    private int bagCount = 0;

    @And("^I should see repurchase dialog opens on \"([^\"]*)\"$")
    public void iShouldSeeRepurchaseDialogOpens(String pageName) throws Throwable {
        boolean isAddToBagButtonPresent;
        switch (pageName) {
            case "OH":
                onPage("order_status");
                isAddToBagButtonPresent = Wait.untilElementPresent("order_details.qv_add_to_bag");
                if (!isAddToBagButtonPresent) {
                    Clicks.clickIfPresent("order_status.buy_again");
                    Assert.assertTrue("add to bag button not present", Wait.untilElementPresent("order_status.qv_add_to_bag"));
                }
            case "OD":
                onPage("order_details");
                isAddToBagButtonPresent = Wait.untilElementPresent("order_details.qv_add_to_bag");
                if (!isAddToBagButtonPresent) {
                    Clicks.clickIfPresent("order_details.buy_again");
                    Assert.assertTrue("add to bag button not present", Wait.untilElementPresent("order_details.qv_add_to_bag"));
                }
        }
    }

    @Then("^I should see \"([^\"]*)\" button on \"([^\"]*)\"$")
    public void iShouldSeeButtonOn(String rebuy_button_text, String pageName) throws Throwable {
        //rebuy_button_text : will be use to compare text in rebuy button or link

        boolean visible;
        String page = null;
        switch (pageName) {
            case "OH":
                onPage("order_status");
                page = "order_status.buy_again";
                break;
            case "OD":
                onPage("order_details");
                page = "order_details.buy_again";
                break;
            default:
                Assert.fail("Invalid page name: " + pageName);
        }
        visible = Elements.findElement(page) != null && Elements.findElement(page).isDisplayed();
        Assert.assertTrue("Buy again button is not displaying", visible);
    }

    @Then("^I should not see buy again button for \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void i_should_not_see_buy_again_button_for_on_page(String shipmentStatus, String pageName) throws Throwable {
        List<WebElement> lineItems;
        List<WebElement> ohLineItems;
        WebElement lineItem;
        switch (shipmentStatus) {
            case "rebuy_processing":
                switch (pageName) {
                    case "OH":
                        onPage("order_status");
                        lineItems = Elements.findElements("order_status.order_history_container");
                        for (WebElement e : lineItems) {
                            ohLineItems = e.findElements(Elements.element("order_status.line_item_in_shipment"));
                            for (WebElement ohLineItem : ohLineItems) {
                                try {
                                    if (ohLineItem.getText().contains("processing")) {
                                        Assert.assertFalse("Buy again Button is displaying, which is unexpected", ohLineItem.findElement(Elements.element("order_status.buy_again")).isDisplayed());
                                        break;
                                    }
                                } catch (NoSuchElementException ignored) {
                                    // no action needed
                                    // this is to handle cases where order container does not have buy again button
                                    logger.info("Add to bag button not displayed as expected");
                                    break;
                                }
                            }
                        }
                        break;
                    case "OD":
                        onPage("order_details");
                        lineItems = Elements.findElements("order_details.order_details_container");
                        for (WebElement e : lineItems) {
                            try {
                                lineItem = e.findElement(Elements.element("order_details.line_item_process"));
                                if (!(lineItem.getText().isEmpty()) && lineItem.getText().contains("processing")) {
                                    Assert.assertFalse("Buy again Button is displaying, which is unexpected", lineItem.findElement(Elements.element("order_details.buy_again")).isDisplayed());
                                    break;
                                }
                            } catch (NoSuchElementException ignored) {
                                // no action needed
                                // this is to handle cases where order container does not have buy again button
                                logger.info("Add to bag button not displayed as expected");
                                break;
                            }
                        }
                        break;
                    default:
                        Assert.fail("Invalid page name: " + pageName);
                        break;
                }
                break;

            case "rebuy_vr_delivered":
                switch (pageName) {
                    case "OH":
                        onPage("order_status");
                        lineItems = Elements.findElements("order_status.order_history_container");
                        for (WebElement ele : lineItems) {
                            ohLineItems = ele.findElements(Elements.element("order_status.line_item_in_shipment"));
                            for (WebElement ohLineItem : ohLineItems) {
                                try {
                                    if (ohLineItem.getText().contains("replacement")) {
                                        Assert.assertFalse("Buy again Button is displaying, which is unexpected", ohLineItem.findElement(Elements.element("order_status.buy_again")).isDisplayed());
                                        break;
                                    }
                                } catch (NoSuchElementException ignored) {
                                    // no action needed
                                    // this is to handle cases where order container does not have buy again button
                                    logger.info("Add to bag button not displayed as expected");
                                    break;
                                }
                            }
                        }

                        break;
                    case "OD":
                        onPage("order_details");
                        lineItems = Elements.findElements("order_status.line_item_in_shipment");
                        for (WebElement e : lineItems) {
                            try {
                                lineItem = e.findElement(Elements.element("order_details.check_shipment_vr_delivered"));
                                if (!(lineItem.getText().isEmpty())) {
                                    Assert.assertFalse("Buy again Button is displaying, which is unexpected", lineItem.findElement(Elements.element("order_details.buy_again")).isDisplayed());
                                    break;
                                }
                            } catch (NoSuchElementException ignored) {
                                // no action needed
                                // this is to handle cases where order container does not have buy again button
                                logger.info("Add to bag button not displayed as expected");
                                break;
                            }
                        }
                        break;
                    default:
                        Assert.fail("Invalid page name: " + pageName);
                        break;
                }
                break;

            case "rebuy_gwp":
                switch (pageName) {
                    case "OH":
                        onPage("order_status");
                        lineItems = Elements.findElements("order_status.order_history_container");
                        Assert.assertNotNull("Line items  are missing..", lineItems);
                        for (WebElement e : lineItems) {
                            ohLineItems = e.findElements(Elements.element("order_status.line_item_in_shipment"));
                            for (WebElement ohLineItem : ohLineItems) {
                                try {
                                    if (ohLineItem.getText().contains("$0.00")) {
                                        Assert.assertFalse("Buy again Button is displaying, which is unexpected", ohLineItem.findElement(Elements.element("order_status.buy_again")).isDisplayed());
                                        break;
                                    }
                                } catch (NoSuchElementException ignored) {
                                    // no action needed
                                    // this is to handle cases where order container does not have buy again button
                                    logger.info("Add to bag button not displayed as expected");
                                    break;
                                }
                            }
                        }
                        break;
                    case "OD":
                        onPage("order_details");
                        lineItems = Elements.findElements("order_details.order_details_lineitem").stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
                        Assert.assertNotNull("Line items  are missing..", lineItems);
                        for (WebElement e : lineItems) {
                            try {
                                if (e.getText().contains("$0.00")) {
                                    lineItem = e;
                                    if (!(lineItem.getText().isEmpty())) {
                                        Assert.assertFalse("Buy again Button is displaying, which is unexpected", lineItem.findElement(Elements.element("order_details.buy_again")).isDisplayed());
                                        break;
                                    }
                                }
                            } catch (NoSuchElementException | NullPointerException ex) {
                                // no action needed
                                // this is to handle cases where order container does not have buy again button
                                logger.info("Add to bag button not displayed as expected");
                                break;
                            }
                        }
                        break;
                    default:
                        Assert.fail("Invalid page name: " + pageName);
                        break;
                }
                break;
            case "rebuy_egc":
                switch (pageName) {
                    case "OH":
                        onPage("order_status");
                        lineItems = Elements.findElements("order_status.order_history_container");
                        for (WebElement e : lineItems) {
                            ohLineItems = e.findElements(Elements.element("order_status.line_item_in_shipment"));
                            for (WebElement ohLineItem : ohLineItems) {
                                try {
                                    if (ohLineItem.getText().contains("Gift Card")) {
                                        Assert.assertFalse("Buy again Button is displaying, which is unexpected", ohLineItem.findElement(Elements.element("order_status.buy_again")).isDisplayed());
                                        break;
                                    }
                                } catch (NoSuchElementException ignored) {
                                    // no action needed
                                    // this is to handle cases where order container does not have buy again button
                                    logger.info("Add to bag button not displayed as expected");
                                    break;
                                }
                            }
                        }
                        break;
                    case "OD":
                        onPage("order_details");
                        lineItems = Elements.findElements("order_details.order_details_container");
                        for (WebElement e : lineItems) {
                            try {
                                lineItem = e.findElement(Elements.element("order_details.line_item_process"));
                                if (!(lineItem.getText().isEmpty()) && lineItem.getText().contains("Gift Card")) {
                                    Assert.assertFalse("Buy again Button is displaying, which is unexpected", lineItem.findElement(Elements.element("order_details.buy_again")).isDisplayed());
                                    break;
                                }
                            } catch (NoSuchElementException ignored) {
                                // no action needed
                                // this is to handle cases where order container does not have buy again button
                                logger.info("Add to bag button not displayed as expected");
                                break;
                            }
                        }
                        break;
                    default:
                        Assert.fail("Invalid page name: " + pageName);
                        break;
                }
                break;

            default:
                switch (pageName) {
                    case "OH":
                        onPage("order_status");
                        try {
                            if (Elements.anyPresent("order_status.buy_again")) {
                                Assert.fail("Buy again button is visible");
                            }
                        } catch (NoSuchElementException ignored) {
                            // no action needed
                            // this is to handle cases where order container does not have buy again button
                            logger.info("Add to bag button not displayed as expected");
                            break;
                        }
                        break;
                    case "OD":
                        onPage("order_details");
                        try {
                            if (Elements.anyPresent("order_details.buy_again")) {
                                Assert.fail("Buy again button is visible");
                            }
                        } catch (NoSuchElementException ignored) {
                            // no action needed
                            // this is to handle cases where order container does not have buy again button
                            logger.info("Add to bag button not displayed as expected");
                            break;
                        }
                        break;
                    default:
                        Assert.fail("Invalid page name: " + pageName);
                        break;
                }
                break;
        }
    }

    @And("^I should see quick_view on page$")
    public void should_be_on_quick_view_page() {
        shouldBeOnPage("quick_view");
    }

    @Then("^I verify product attribute on quick view with line item detail on \"([^\"]*)\" page")
    public void iVerifyProductAttributeInQuickViewWithGridThumbnail(String page) throws Throwable {
        String onPageQuantity = "";
        String onPageDesc = "";
        String onPageSize = "";
        String onPageColor = "";
        ReturnsPage rp = new ReturnsPage();
        WebElement rebuyShipmentSelected = RebuyOrderStatusPage.getRebuyShipment(page);
        Assert.assertNotNull("don't have shipment with rebuy button on page", rebuyShipmentSelected);
        switch (page) {
            case "OD":
                List<Map> lineItemsDetails = rp.getLineItemsDetails(rebuyShipmentSelected);
                try {
                    onPageQuantity = (String) lineItemsDetails.get(0).get("itemQty");
                    onPageDesc = (String) lineItemsDetails.get(0).get("itemDescription");
                    onPageSize = rebuyShipmentSelected.findElement(Elements.element("order_details.on_page_size")).getText();
                    onPageColor = rebuyShipmentSelected.findElement(Elements.element("order_details.on_page_color")).getText();
                    if (bloomingdales()) {
                        onPageSize = onPageSize.trim().split("\\s", 2)[1];
                        onPageColor = onPageColor.trim().split("\\s", 2)[1];
                    }
                } catch (Exception e) {
                    Assert.fail("Unable to get the line item details from Page due to : " + e);
                }
                if (bloomingdales()) Clicks.click("order_details.buy_again");
                break;
            case "OH":
                try {
                    onPageQuantity = rebuyShipmentSelected.findElement(Elements.element("order_status.on_page_quantity")).getText().replaceAll("\\D+", "");
                    onPageDesc = rebuyShipmentSelected.findElement(Elements.element("order_status.on_page_desc")).getText();
                    onPageSize = rebuyShipmentSelected.findElement(Elements.element("order_status.on_page_size")).getText();
                    onPageColor = rebuyShipmentSelected.findElement(Elements.element("order_status.on_page_color")).getText();
                } catch (Exception e) {
                    Assert.fail("Unable to get the line item details from Page due to : " + e);
                }
                if (bloomingdales()) Clicks.click("order_status.buy_again");
                break;
            default:
                Assert.fail("Invalid page name: " + page);
                break;
        }

        logger.info("on page description : " + onPageDesc);
        logger.info("on page color : " + onPageColor);
        logger.info("on page size : " + onPageSize);
        logger.info("on page quantity : " + onPageQuantity);
        RebuyOrderStatusPage.openQuickView();
        String qvPdtDesc = Elements.findElement("order_details.qv_description").getText();
        String qvPdtColor = Elements.findElement("order_details.qv_color").getText();
        String qvPdtSize = "";
        String qvPdtQty = "";
        if (macys()) {
            qvPdtSize = DropDowns.getSelectedValue(Elements.element("order_details.qv_size"));
            qvPdtQty = DropDowns.getSelectedValue(Elements.element("order_details.qv_quantity"));
        } else if (bloomingdales()) {
            qvPdtSize = Elements.findElement("order_details.qv_size").getText();
            qvPdtQty = Elements.findElement("order_details.qv_quantity_selected").getText();
        }

        logger.info("quick view product description : " + qvPdtDesc);
        logger.info("quick view product color : " + qvPdtColor);
        logger.info("quick view product size : " + qvPdtSize);
        logger.info("quick view product quantity : " + qvPdtQty);
        Assert.assertTrue("Color are mismatching from QV to Page", onPageColor.equalsIgnoreCase(qvPdtColor));
        Assert.assertTrue("Quantities are mismatching from QV to Page", qvPdtQty.equalsIgnoreCase("1"));
        Assert.assertTrue("Sizes are mismatching from QV to Page", onPageSize.equalsIgnoreCase(qvPdtSize));
//        Wait.untilElementPresent(By.className("qvProductTitle"));
//        // Execution Engine needs more wait time for color to update to upc specific color. Hence increasing time wait.
//        Thread.sleep(3000);
//        // product description in QV is different than the one present in order history page
////        Assert.assertEquals("Descriptions are mismatching from QV to Page", onPageDesc, Elements.findElement("order_details.qv_description").getText());
//        try {
//            Assert.assertEquals("Color are mismatching from QV to Page", onPageColor, Elements.findElement("order_details.qv_color").getText());
//        }catch (Exception e)
//        {
//            Clicks.click("order_details.buy_again");
//            Wait.untilElementPresent(By.className("qvProductTitle"));
//            Thread.sleep(7000);
//            Assert.assertEquals("Color are mismatching from QV to Page", onPageColor, Elements.findElement("order_details.qv_color").getText());
//        }
    }

    // Returns the shipment container which contains BuyAgain button


    @And("^I select rebuy button on \"([^\"]*)\" page$")
    public void select_rebuy_button(String page) {
        switch (page) {
            case "OD":
                bagCount = RebuyOrderStatusPage.getBagCount();
                logger.info("current bag count : " + bagCount);
                Clicks.click("order_details.buy_again");
                Wait.secondsUntilElementPresent("order_details.qv_add_to_bag", 20);
                break;
            case "OH":
                bagCount = RebuyOrderStatusPage.getBagCount();
                logger.info("current bag count : " + bagCount);
                Clicks.click("order_status.buy_again");
                Wait.secondsUntilElementPresent("order_status.qv_add_to_bag", 20);
                break;
            default:
                Assert.fail("Invalid page name: " + page);
                break;
        }
    }

    @And("^I select add to bag button on quick view page$")
    public void select_add_to_bag_on_quickview_button() {
        boolean addedToBag;
        Wait.forPageReady();
        Wait.untilElementPresent("order_details.qv_add_to_bag");
        Clicks.click("order_details.qv_add_to_bag");
        Wait.secondsUntilElementPresent("order_details.qv_add_to_bag_message", 15);
        String validationMessage = Elements.getText("order_details.qv_add_to_bag_message");
        logger.info("add to bag confirmation message : " + validationMessage);
        if (macys()) {
            addedToBag = validationMessage.contains("added to your bag");
        } else {
            addedToBag = validationMessage.contains("item(s) added to your Brown Bag");
        }
        Assert.assertTrue("added to bag confirmation is not shown", addedToBag);
        Clicks.click("order_details.qv_close");
    }

    @And("^I should see item getting added to bag successfully$")
    public void item_added_to_bag_successfully() {
        int updatedBagCount;
        updatedBagCount = RebuyOrderStatusPage.getBagCount();
        logger.info("updated bag count : " + updatedBagCount);
        Assert.assertTrue("updated bag count is not greater than the previous bag count", bagCount < updatedBagCount);
    }

    @And("^I should \"([^\"]*)\" rebuy button on page$")
    public void check_visibility_Of_rebuy_button_on_page(String visibility) {
        switch (visibility) {
            case "see":
                Assert.assertTrue("Buy Again button is not visible", Elements.elementPresent("order_details.buy_again"));
                break;
            case "not see":
                Assert.assertFalse("Buy Again button is visible", Elements.elementPresent("order_details.buy_again"));
                break;
            default:
                Assert.fail("Please provide proper visibility check or verify Feature file step. Visibility to check is : " + visibility);
                break;
        }
    }

    @And("^I go to \"([^\"]*)\" order using page navigation in oh page$")
    public void I_go_to_order_using_page_navigation_in_oh_page(String orderType) {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        ReturnsPage returnObj = new ReturnsPage();
        pausePageHangWatchDog();
        JSONObject returnOrderDetails = Utils.getReturnOrders(order);
        String orderNumber = returnOrderDetails.getString("order_number");
        logger.info(String.format("order number retrieved for order type %s is %s", orderType, orderNumber));
        returnObj.findOrderInDateRange(orderNumber);
        resumePageHangWatchDog();
    }

    @When("^I close the quick view dailog$")
    public void iCloseTheQuickViewDialog() {
        try {
            Wait.untilElementPresent("order_details.qv_close");
            Clicks.click("order_details.qv_close");
        } catch (Exception exe) {
            logger.info("Issue while close the Popup");
        }
    }

    @When("^I select second rebuy button on \"([^\"]*)\" page$")
    public void iSelectSecondRebuyButtonOnPage(String page) {
        switch (page) {
            case "OD":
                bagCount = RebuyOrderStatusPage.getBagCount();
                logger.info("current bag count : " + bagCount);
                List<WebElement> buyAgainListOD = Elements.findElements("order_details.buy_again");
                Assert.assertTrue("ERROR:- TEST DATA: Second Buy Again button missing in OD", buyAgainListOD.size() >= 2);
                Clicks.clickRandomElement("order_details.buy_again");
                Wait.secondsUntilElementPresent("order_details.qv_add_to_bag", 15);
                break;
            case "OH":
                bagCount = RebuyOrderStatusPage.getBagCount();
                logger.info("current bag count : " + bagCount);
                List<WebElement> buyAgainListOH = Elements.findElements("order_status.buy_again");
                Assert.assertTrue("ERROR:- TEST DATA- Second Buy Again button missing in OH", buyAgainListOH.size() >= 2);
                Clicks.clickRandomElement("order_status.buy_again");
                Wait.secondsUntilElementPresent("order_status.qv_add_to_bag", 15);
                break;
            default:
                Assert.fail("Invalid page name: " + page);
                break;
        }

    }


    @When("^I modify product attributes on quick view$")
    public void iModifyProductAttributesOnQuickView() throws InterruptedException {
        if (macys()) {
            Wait.secondsUntilElementPresent("quick_view.product_title", 5);
            RebuyOrderStatusPage.modifyProductAttributesInQuickView();
            logger.info("Modified attributes of product");
        } else if (bloomingdales()) {
            Wait.secondsUntilElementPresent("order_details.qv_colors", 5);
            List<WebElement> qvColorList = Elements.findElements("order_details.qv_colors");
            logger.info("The number of colors are: " + qvColorList.size());
            Clicks.click(qvColorList.get(qvColorList.size() - 1));
            Wait.secondsUntilElementPresent("order_details.qv_sizes", 5);
            List<WebElement> qvSizeList = Elements.findElements("order_details.qv_sizes");
            logger.info("The number os sizes are: " + qvSizeList.size());
            Clicks.click(qvSizeList.get(qvSizeList.size() - 1));
            Thread.sleep(5000);
        }
    }


    /**
     * Associated an order with the and existing user account
     * <p>
     * Data comes from "return_orders.json" data file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @throws Throwable if any exception occurs
     */
    @When("^I associate rebuy \"([^\"]*)\" order in db$")
    public void I_associate_rebuy_order_in_db(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        String orderNumber = returnOrderDetails != null ? returnOrderDetails.getString("order_number") : null;
        Assert.assertNotNull("Desired order type absent in record", orderNumber);
        logger.info(String.format("order %s present in record for order type %s", orderNumber, orderType));
        ReturnService returnService = new ReturnService();
        if (returnService.orderExistsByOrderNumber(orderNumber)) {  // order exists
            logger.info("extracting profile associated with the order and sign in to the application");
            Map userData = returnService.getUserDetails(orderNumber);
            String passWord = Utils.decryptPassword(userData.get("password").toString());
            if (safari()) {
                Wait.secondsUntilElementPresent("home.goto_my_account_link", 15);
            }
            if (MEW()) {
                GlobalNav.openGlobalNav();
                GlobalNav.navigateOnGnByName("My Account");
                GlobalNav.closeGlobalNav();
            } else {
                if (macys())
                    Clicks.click("home.goto_sign_in_link");
                else
                    Clicks.click("footer.goto_my_account");
            }
            if (safari()) {
                Wait.secondsUntilElementPresent("sign_in.email", 15);
            }
            TextBoxes.typeTextbox("sign_in.email", userData.get("email").toString());
            TextBoxes.typeTextbox("sign_in.password", passWord);
            Clicks.click("sign_in.verify_page");
            Clicks.clickIfPresent("sign_in.close_overlay");
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
            // order does not exist hence inserting in database
            logger.info("create profile, associate order and sign in to the application");
            CommonUtils.signInOrCreateAccount();
            UserProfile customer = TestUsers.getCustomer(null);
            String email = customer.getUser().getProfileAddress().getEmail();
            returnService.insertOrderByOrderNumber(orderNumber, email);
        }
    }

    /**
     * Navigates to order details page as given user type
     *
     * @param userType "guest" or "signed in"
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to \"([^\"]*)\" order details page as a \"([^\"]*)\" user$")
    public void I_navigate_to_order_details_page_as_user(String orderType, String userType) throws Throwable {
        Clicks.javascriptClick("home.goto_order_status");
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("order_status.verify_page", 10);
            Wait.forPageReady();
        }
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        String orderNumber = returnOrderDetails.getString("order_number");
        if (userType.equals("signed")) {
            returnsPage.findOrderInDateRange(orderNumber);
            returnsPage.clickOrderDetailsButton(orderNumber);
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
     *  Returns the shipment container which contains BuyAgain button
     *  @param page Order History (OH) or Order Details (OD) page
     *
     *  @return shipment container containing Buy Again button
     */
    private WebElement getRebuyShipment(String page) {
        WebElement rebuy_ln;
        switch (page) {
            case "OD":
                List<WebElement> shipments = Elements.findElements("order_details.order_details_container");
                for (WebElement e : shipments) {
                    try {
                        rebuy_ln = e.findElement(Elements.element("order_details.buy_again"));
                        if (!(rebuy_ln.getText().isEmpty())) {
                            return e;
                        }
                    } catch (NoSuchElementException ignored) {
                        // no action needed
                        // this is to handle cases where order container does not have buy again button
                        logger.debug("element not found : " + ignored.getMessage());
                    }
                }
                break;
            case "OH":
                List<WebElement> line_items = Elements.findElements("order_status.line_item_in_shipment");
                for (WebElement e : line_items) {
                    try {
                        rebuy_ln = e.findElement(Elements.element("order_status.buy_again"));
                        if (!(rebuy_ln.getText().isEmpty())) {
                            return e;
                        }
                    } catch (NoSuchElementException ignored) {
                        // no action needed
                        // this is to handle cases where order container does not have buy again button
                        logger.debug("element not found : " + ignored.getMessage());
                    }
                }
                break;
            default:
                Assert.fail("Invalid page name: " + page);
                break;
        }
        return null;
    }

    @Then("^I should able to navigate to PDP page by clicking on \"([^\"]*)\" on \"([^\"]*)\" page for rebuy line item$")
    public void ishouldnavigatetoPDPbyclickingsproductlinksonOHandODpage(String linkType, String pageName) throws Throwable {
        WebElement rebuy_shipment = Optional.ofNullable(getRebuyShipment(pageName)).orElseThrow(NullPointerException::new);
        WebElement nameLink;
        WebElement imageLink;
        switch (pageName) {
            case "OH":
                switch (linkType) {
                    case "product_image":
                        rebuy_shipment.findElement(Elements.element("order_status.product_image")).click();
                        Wait.forPageReady();
                        break;
                    case "product_name":
                        rebuy_shipment.findElement(Elements.element("order_status.product_name")).click();
                        Wait.forPageReady();
                        break;
                }
                break;
            case "OD":
                switch (linkType) {
                    case "product_image":
                        if (macys()) {
                            imageLink = rebuy_shipment.findElement(Elements.element("order_details.product_info")).findElement(Elements.element("order_details.product_image"));
                            Clicks.click(imageLink);
                        } else if (bloomingdales()) {
                            imageLink = rebuy_shipment.findElement(Elements.element("order_details.product_image"));
                            Clicks.click(imageLink);
                        }
                        break;
                    case "product_name":
                        if (macys()) {
                            nameLink = rebuy_shipment.findElement(Elements.element("order_details.product_info")).findElement(Elements.element("order_details.product_name"));
                            Clicks.click(nameLink);
                        } else if (bloomingdales()) {
                            nameLink = rebuy_shipment.findElement(Elements.element("order_details.product_name"));
                            Clicks.click(nameLink);
                        }
                        break;
                }
                break;

            default:
                Assert.fail("Invalid page name: " + pageName);
                break;
        }
        Wait.forPageReady();
        Set<String> windowSet = WebDriverManager.getWebDriver().getWindowHandles();
        Assert.assertTrue("The new tab has not opened.", windowSet.size() > 1);
        Navigate.switchWindow(1);
        Wait.forPageReady();
        new PageNavigation().I_should_be_redirected_to_PDP_page();
        logger.info("Navigated to PDP page by clicking on", linkType);
    }
}

