package com.macys.sdt.projects.PurchaseAndDelivery.BigTicket.steps.website;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.exceptions.UserException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;

import static com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay.*;
import static com.sun.jna.platform.win32.COM.tlb.TlbImp.logInfo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;

import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.db.models.OrderServices;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.projects.PurchaseAndDelivery.BigTicket.actions.website.BTGenericMethods;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.GuestCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.OrderConfirmation;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.OrderStatus;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.DeliverySchedulingSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.OrderSummarySection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.PaymentSignedInSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.PaymentGuestSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ServicesAndFeesSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website.CommonSteps;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website.ServiceSteps;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.Orders;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.ShopNServeService;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.ProductDisplay;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CheckoutUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigTicketSteps {
    private static final Logger logger = LoggerFactory.getLogger(CommonSteps.class);

    @Then("^I verify below button on (PDP page|QV overlay):$")
    public void iVerifyBelowButtonOnPDPPage(Map<String, Boolean> pdpButtons) {
        for (Map.Entry<String, Boolean> entry : pdpButtons.entrySet()) {
            boolean expectedValue = Elements.findElement("product_display" + "." + entry.getKey()).isEnabled();
            if (expectedValue != entry.getValue()) {
                Assert.fail("Expected button " + entry.getKey() + " is not displayed on BT PDP page");
            }
        }
    }

    //TODO
    @Then("^I verify below button on PDP page for all member products:$")
    public void iVerifyBelowButtonOnPDPPageForAllMemberProducts(Map<String, Boolean> pdpButtons) {
        List<String> memberProductIds = BTGenericMethods.getMemberProductIds();
        for (Map.Entry<String, Boolean> entry : pdpButtons.entrySet()) {
            boolean expectedValue = Elements.findElement("product_display" + "." + entry.getKey()).isEnabled();
            if (expectedValue != entry.getValue()) {
                Assert.fail("Expected button " + entry.getKey() + " is not displayed on BT PDP page");
            }
        }
    }

    @When("^I enter a zipcode with (\\d+) digits$")
    public void iEnterAZipcodeWithDigits(int count) {
        String randomNum = BTGenericMethods.generateRandomNumber(count);
        TextBoxes.typeTextbox("product_display.bt_zipcode", randomNum);
    }

    @Then("^I should see error message related to zipcode:$")
    public void iShouldSeeErrorMessageRelatedToZipcode(List<String> expectedMsg) {
        String actualMessage = Elements.getText("product_display.bt_zipcode_field_error_msg");
        Assert.assertEquals("Expected Error message is not displayed on BT PDP page for invalid zipcode", expectedMsg.get(0), actualMessage);
    }

    @Then("^I should see zipcode text field on (PDP page|QV overlay) for BT products$")
    public void iShouldSeeZipcodeTextFieldOnPDPPageForBTProducts(String type) {
        Elements.elementShouldBePresent("product_display.bt_zipcode_field");
        logInfo("Zipcode text field is displayed");
    }

    @Then("^I should see the message saying item is (available|not available) for the zip code:$")
    public void iShouldSeeTheMessageSayingItemIsAvailableForTheZipCode(String condition, List<String> expectedMsg) {
        String actualMessage = Elements.getText("product_display.bt_product_availability_msg");
        Assert.assertEquals("Selected Big ticket item is not available for the given zipcode", expectedMsg.get(0), actualMessage);
    }

    @Then("^I should see the message saying item is on-order for the zip code:$")
    public void iShouldSeeTheMessageSayingItemIsOnOrderForTheZipCode(List<String> expectedMsg) {
        String actualMessage = Elements.getText("product_display.bt_product_availability_msg");
        Assert.assertEquals("Selected Big ticket item is not an on-order for the given zipcode", expectedMsg.get(0), actualMessage);
    }

    @Then("^I should see the message saying item is added to list$")
    public void iShouldSeeTheMessageSayingItemIsAddedToList() {
        Wait.untilElementPresent("product_display.wishlist_overlay");
        String itemAddedToWishlistMsg = Elements.getText("product_display.wishlist_overlay");
        Assert.assertFalse("Item added to wishlist message is not displayed on overlay", itemAddedToWishlistMsg.isEmpty());
    }

    @Then("^I should check a different zipcode link on BT PDP page$")
    public void iShouldCheckADifferentZipcodeLinkOnBTPDPPage() {
        Wait.untilElementPresent("product_display.check_another_zipcode");
        Assert.assertTrue("Zipcode link is not displayed below add to bag button", Elements.findElement("product_display.another_zipcode_link").getCssValue("zip-code-link").equalsIgnoreCase("underline"));
        logInfo("Select another zipcode is displayed on PDP page for BT products");
    }

    @Then("^I should see empty zipcode text field on pdp page$")
    public void iShouldSeeEmptyZipcodeTextFieldOnPdpPage() {
        Wait.untilElementPresent("product_display.bt_zipcode");
        Assert.assertNull(Elements.getText("product_display.bt_zipcode"));
        logInfo("Empty Zipcode text field is displayed");
    }

    @When("^I click on the Check a different zip code link on the page$")
    public void iClickOnTheCheckADifferentZipCodeLinkOnThePage() {
        Wait.untilElementPresent("product_display.check_another_zipcode");
        Clicks.click("product_display.check_another_zipcode");
        logInfo("Clicked on check another zipcode on PDP page for BT products");
    }

    @When("^I click on \"([^\"]*)\" link$")
    public void iClickOnLink(String type) {
        if (type.equals("Find in Store")) {
            Wait.untilElementPresent("product_display.find_in_store");
            Clicks.click("product_display.find_in_store");
            logInfo("Clicked on find in store link displayed on PDP page for BT products");
        } else {
            Wait.untilElementPresent("product_display.talk_to_experts");
            Clicks.click("product_display.talk_to_experts");
            logInfo("Clicked on Talk to experts displayed on PDP page for BT products");
        }
    }

    @Then("^I should see store overlay with no change to current FIS functionality$")
    public void iShouldSeeStoreOverlayWithNoChangeToCurrentFISFunctionality() {
        Elements.elementShouldBePresent("product_display.find_in_store_overlay");
        Elements.elementShouldBePresent("product_display.store_zipcode_field");
        Elements.elementShouldBePresent("product_display.store_search_btn");
        Elements.elementShouldBePresent("product_display.store_miles_select");
        Elements.elementShouldBePresent("product_display.store_my_location");
        logInfo("Verified all the attributes in Find in store overlay");
    }

    @Then("^I should see talk to experts overlay with no change to current FIS functionality$")
    public void iShouldSeeTalkToExpertsOverlayWithNoChangeToCurrentFISFunctionality() {
        Elements.elementShouldBePresent("product_display.talk_to_experts_overlay");
        Elements.elementShouldBePresent("product_display.experts_country_drop_down");
        Elements.elementShouldBePresent("product_display.user_phone_number");
        Elements.elementShouldBePresent("product_display.user_call_me");
        Elements.elementShouldBePresent("product_display.talk_by_phone");
        logInfo("Verified all the attributes on Talk to experts overlay");
    }

    @Then("^I should see that BT product is successfully added to (bag|wishlist)$")
    public void iShouldSeeThatBTProductIsSuccessfullyAddedToBag(String type) {
        if (type.equals("wishlist")) {
            Wait.untilElementPresent("product_display.wishlist_overlay");
            String itemAddedToWishlistMsg = Elements.getText("product_display.wishlist_overlay");
            Assert.assertFalse("Item added to wishlist message is not displayed on overlay", itemAddedToWishlistMsg.isEmpty());
        } else if (!onPage("add_to_bag")) {
            if (!Elements.elementPresent("product_display.add_to_bag_dialog") &&
                    !Elements.elementPresent("product_display.master_add_to_bag_dialog")) {
                Assert.fail("Not on ATB page OR panel");
            }
        }
        logInfo("Big ticket product is successfully added to bag/wishlist");
    }

    @Then("^I should see error message as \"([^\"]*)\"$")
    public void iShouldSeeErrorMessageAs(String expectedMsg) {
        String actualMessage = Elements.getText("product_display.tool_tip_err_msg");
        Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualMessage);
    }

    @Then("^I should see existing BOPS functionality suppressed on the PDP for BT items$")
    public void iShouldSeeExistingBOPSFunctionalitySuppressedOnThePDPForBTItems() {
        Elements.elementShouldBePresent("product_display.bops_availability_header");
        logInfo("Bops section is not displayed on PDP page for BT items");
    }

    //TODO
    @Then("^I verify error message when user selects the color which is not available:$")
    public void iVerifyErrorMessageWhenUserSelectsTheColorWhichIsNotAvailable() {
        //TBD
        throw new PendingException();
    }

    @Then("^I quick view a random product on browse page$")
    public void iQuickViewARandomProductOnBrowsePage() {
        if (macys()) {
            if (Elements.elementPresent("category_browse.quick_view_button")) {
                Clicks.clickRandomElement("category_browse.quick_view_button");
            } else {
                WebElement thumbnail = Elements.getRandomElement("category_browse.product_thumbnails_image");
                Clicks.hoverForSelection(thumbnail);
                if (!Wait.untilElementPresent("category_browse.quick_view_button")) {
                    Clicks.click(thumbnail);
                    return;
                }
                Clicks.click("category_browse.quick_view_button");
                logInfo("QV overlay is displayed");
            }
        }
    }

    @Then("^I should see PayPal Checkout button \"([^\"]*)\" in shopping bag$")
    public void iShouldSeeButtonSuppressedInShoppingBag(String text) {
        if (text.equals("displayed")) {
            Elements.elementShouldBePresent("shopping_bag.checkout_with_paypal");
        } else {
            Assert.assertFalse("Paypal button should be suppressed ", Elements.elementPresent("product_display.bops_availability_header"));
        }
    }

    @Then("^I verify the link \"([^\"]*)\" on quick view overlay$")
    public void iVerifyTheLinkOnQuickViewOverlay() {
        Elements.elementShouldBePresent("quick_view.quick_view_see_full_details");
    }

    @Then("^I add the product to bag$")
    public void IaddProducttobag() {
        Wait.forPageReady();
        Clicks.click(Elements.element("pdp.add_to_bag"));
    }

    @Then("^I should see go back and checkout button$")
    public void iShouldSeeAGoBackAndCheckoutButton() {
        Elements.elementShouldBePresent("add_to_bag.keep_shopping_button");
        Elements.elementShouldBePresent("add_to_bag.checkout");
    }

    @Then("^I should \"([^\"]*)\" pick up in store section in bag$")
    public void iShouldPickUpInStoreSectionInBag(String text) {
        if (text.equals("see")) {
            Elements.elementShouldBePresent("shopping_bag.bops_pick_up_section");
        } else {
            Elements.elementShouldBePresent("shopping_bag.bops_pick_up_section");
        }
    }

    @Then("^I (should|should not) see the \"([^\"]*)\" text in pick up in store section$")
    public void iShouldSeeTheTextInPickUpInStoreSection(String expectedMsg, String condition) {
        if (condition.equals("should")) {
            String actualMessage = Elements.getText("shopping_bag.bt_exclusion_msg");
            Assert.assertEquals("Mattresses and furniture exclusion text in pick-up in store section", expectedMsg, actualMessage);
        } else {
            Elements.elementShouldBePresent("shopping_bag.bt_exclusion_msg");
        }
    }

    @When("^I click on \"([^\"]*)\" link for \"([^\"]*)\" item$")
    public void iClickOnLinkForItem(String linkType, String product_type) {
        shouldBeOnPage("shopping_bag");
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        shoppingBag.removeBonusItemsFromBag();
        Navigate.browserRefresh();
        List<Product> bagItems = shoppingBag.getAllProductDetails();
        int index = shoppingBag.findItemIndexByType(bagItems, product_type);
        if (linkType.equals("remove"))
            shoppingBag.removeItem(index);
        else
            shoppingBag.moveItemToWishlist(index);

    }

    @When("^I add a big ticket \"([^\"]*)\" product to bag$")
    public void iAddAProductToBag(String productType) {
        createSegmentCookie();
        Product productDetails = CommonUtils.navigateToRandomProduct(productType, null);
        ProductDisplay productDisplay = Navigate.get(ProductDisplay.class);

        int retries = 5;
        boolean addedToBag = false;
        for (int i = 0; i < retries && !addedToBag; i++) {
            selectRandomColor();
            selectRandomSize();
            selectSwatches();
            productDisplay.btZipcodeField.sendKeys(productDetails.zipCode);
            productDisplay.btZipcodeSubmitBtn.click();

            if (Wait.until(productDisplay.btAvailabilityInfo::isDisplayed, 90)) {
                Assert.assertThat(String.format("Big Ticket availability check failed: %s", productDisplay.btAvailabilityInfo.getText()),
                        productDisplay.btAvailabilityInfo.getAttribute("class"),
                        not(containsString("notification-error")));
            }
            productDisplay.addToBagButton.click();
            addedToBag = addedToBag();
        }

        if (!Wait.until(productDisplay.addToBagdialog().addToBagDialog::isDisplayed)) {
            productDisplay.clickTechnicalerror();
        }
    }

    @Then("^I should be on ATB page with go back and checkout button$")
    public void iShouldBeOnATBPageWithGoBackAndCheckoutButton() {
        Elements.elementShouldBePresent("add_to_bag.keep_shopping_button");
        Elements.elementShouldBePresent("add_to_bag.checkout");
    }

    @Then("^I should be on product details page on clicking go back button$")
    public void iShouldBeOnProductDetailsPageOnClickingGoBackButton() {
        Wait.untilElementPresent("add_to_bag.keep_shopping_button");
        Clicks.click("add_to_bag.keep_shopping_button");
        shouldBeOnPage("Pdp");
        logInfo("Navigated to PDP page from ATB page");

    }

    @Then("^I should be on shopping bag page on clicking checkout button$")
    public void iShouldBeOnShoppingBagPageOnClickingCheckoutButton() {
        Wait.untilElementPresent("add_to_bag.checkout");
        Clicks.click("add_to_bag.checkout");
        shouldBeOnPage("shopping_bag");
        logInfo("Navigated to shopping bag page from ATB page");
    }

    @Then("^I add random member product from PDP$")
    public void iAddRandomMemberProductFromPDP() {
        addRandomMemberProductOnMasterPDP();
        Elements.elementShouldBePresent("add_to_bag_dialog.master_add_to_bag_dialog");
    }

    @Then("^I should see the confirmation popup is closed on clicking add more button$")
    public void iShouldSeeTheConfirmationPopupIsClosedOnClickingAddMoreButton() {
        Clicks.clickWhenPresent("add_to_bag_dialog.master_add_to_bag_checkout");
        shouldBeOnPage("pdp");
        logInfo("Product confirmation popup is closed");
    }

    @Then("^I should see the shopping bag page on clicking checkout button$")
    public void iShouldSeeTheShoppingBagPageOnClickingCheckoutButton() {
        Clicks.click("add_to_bag_dialog.master_add_to_bag_continue_shopping");
        shouldBeOnPage("shopping_bag");
        logInfo("Navigated to shopping bag page on clicking checkout page on member product overlay");
    }

    @Then("^I should see \"([^\"]*)\" message on quickview overlay$")
    public void iShouldSeeMessageOnQuickviewOverlay() {
        Elements.elementShouldBePresent("add_to_bag.keep_shopping_button");
    }

    @Then("^I should see add to Bag confirmation message on quickview overlay$")
    public void iShouldSeeAddToBagConfirmationMessageOnQuickviewOverlay() {
        Elements.elementShouldBePresent("quick_view.validation_msg");
        Elements.elementShouldBePresent("quick_view.checkout_now");
    }

    @And("^I select default WNM plan$")
    public void iSelectWNMPlan() {
        Assert.assertThat("ERROR - default WNM plan is not visible",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).defaultWorryNoMoresPlan::isDisplayed),
                is(Boolean.TRUE));
        Navigate.get(ServicesAndFeesSection.class).defaultWorryNoMoresPlan.click();
    }

    @And("^I select default Mattress plan$")
    public void iSelectMattressPlan() {
        Navigate.get(ServicesAndFeesSection.class).selectMattressesFeeCheckbox();
    }

    @And("^I select \"Delivery to a military base\"$")
    public void iSelectDeliveryToMilitaryBase() {
        Assert.assertThat("ERROR - 'Delivery to a military base' option is not visible",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).deliveryMilitaryBase::isDisplayed),
                is(Boolean.TRUE));
        Navigate.get(ServicesAndFeesSection.class).deliveryMilitaryBase.click();
    }

    @And("^I select \"Delivery through a security gate\"$")
    public void iSelectDeliveryThroughtSecGate() {
        Assert.assertThat("ERROR - 'Delivery through a security gate' option is not visible",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).deliverySecurityGate::isDisplayed),
                is(Boolean.TRUE));
        Navigate.get(ServicesAndFeesSection.class).deliverySecurityGate.click();
    }

    @And("^I select \"Delivery up more than 3 flights of stairs\"$")
    public void iSelectDeliveryFlightStairs() {
        Assert.assertThat("ERROR - 'Delivery Stairs Flights' option is not visible",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).deliveryFlightStairs::isDisplayed),
                is(Boolean.TRUE));
        Navigate.get(ServicesAndFeesSection.class).deliveryFlightStairs.click();
    }

    @And("^I select \"none of the above\"$")
    public void iSelectDeliveryNoneAbove() {
        Assert.assertThat("ERROR - 'None of the above' option is not visible",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).deliveryNone::isDisplayed),
                is(Boolean.TRUE));
        Navigate.get(ServicesAndFeesSection.class).deliveryNone.click();
    }

    @And("^I select \"Building requires Certificate of Insurance for deliveries\"$")
    public void iSelectCOI() {
        Assert.assertThat("ERROR - 'Building requires Certificate of Insurance for deliveries' option is not visible",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).deliveryCoi::isDisplayed),
                is(Boolean.TRUE));
        Navigate.get(ServicesAndFeesSection.class).deliveryCoi.set(true);
    }


    @Then("^I see \"Call to Schedule\" message in schedule delivery section$")
    public void iSeeCallToScheduleMessage() {

        DeliverySchedulingSection deliverySchedulingSection = Navigate.get(DeliverySchedulingSection.class);
        Assert.assertThat("ERROR - 'Call to Schedule message is not displayed'",
                Wait.until(deliverySchedulingSection.deliverySectionActiveMessage::isDisplayed),
                is(Boolean.TRUE));
        Assert.assertThat("ERROR - 'Call to Schedule message is different'",
                deliverySchedulingSection.getActiveMessageText(),
                containsString("Call to schedule delivery after checkout:1-888-822-6229")); //TODO issue in whitespace
        Assert.assertThat("ERROR - Month selector is displayed",
//                deliverySchedulingSection.rcScheduleMonth.isDisplayed(),
                Elements.elementPresent("responsive_order_confirmation.rc_schedule_month"),
                is(Boolean.FALSE));
        Assert.assertThat("ERROR - Date selector is displayed",
                Elements.elementPresent("responsive_order_confirmation.rc_schedule_date"),
                is(Boolean.FALSE));
    }

    /**
     * Step selects the date of delivery and clicks Continue button
     *
     * @param targetDate number of days we need to add to current date, so we will select that one
     */
    @And("^I schedule delivery (?:in|for) \"(closest|\\d+)(?: days?)?\" date$")
    public void scheduleDeliveryForDate(String targetDate) {
        DeliverySchedulingSection deliverySchedulingSection = Navigate.get(DeliverySchedulingSection.class);
        if (targetDate.equals("closest")) {
            deliverySchedulingSection.scheduleClosestAvailableDate();
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, Integer.valueOf(targetDate));
            deliverySchedulingSection.scheduleAvailableDeliveryDates(c.getTime());
        }
        //click continue
        deliverySchedulingSection.clickScheduleDeliverySubmitButton();
    }

    @Then("^I verify big ticket order summary:$")
    public void checkOrderSummary(Map<String, Boolean> orderVerificationOptions) {
        JSONObject orderSummaryServiceInfo = ShopNServeService.getOrderSummary();
        List<String> orderSummarySectionInfo = new ArrayList<>();
        Navigate.get(OrderSummarySection.class).getRcOrderCostSummary()
                .forEach(elem -> orderSummarySectionInfo.add(elem.getText()));

        //Order total
        if (orderVerificationOptions != null) {
            if (orderVerificationOptions.keySet().contains("WNM")) {
                Assert.assertThat("ERROR - ",
                        orderSummaryServiceInfo.getString("wnmPlanCost").equalsIgnoreCase("$0.00"),
                        is(!orderVerificationOptions.get("WNM"))); //check value
                Assert.assertThat("ERROR - Worrynomore record is not present in order summary or has different label",
                        orderSummarySectionInfo.stream().anyMatch(s -> s.contains("WORRYNOMORE®")),
                        is(orderVerificationOptions.get("WNM")));
            }

            if (orderVerificationOptions.keySet().contains("delivery")) {
                Assert.assertThat("ERROR - 'Delivery fee' record is not present in order summary or has different label",
                        orderSummarySectionInfo.stream().anyMatch(s -> s.contains("White Glove delivery")),
                        is(orderVerificationOptions.get("delivery")));
            }

            if (orderVerificationOptions.keySet().contains("removalFee")) {
                Assert.assertThat("ERROR - 'Bedding removal fee' record is not present in order summary or has different label",
                        orderSummarySectionInfo.stream().anyMatch(s -> s.contains("Bedding removal fee")),
                        is(orderVerificationOptions.get("removalFee")));
                Assert.assertThat("ERROR - 'Bedding removal fee' is absent in service order summary",
                        orderSummaryServiceInfo.getString("bedRemovalFee").equalsIgnoreCase("$0.00"),
                        is(!orderVerificationOptions.get("removalFee")));
            }

            if (orderVerificationOptions.keySet().contains("recyclingFee")) {
                Assert.assertThat("ERROR - 'Bedding recycling fee' record is not present in order summary or has different label",
                        orderSummarySectionInfo.stream().anyMatch(s -> s.contains("Bedding recycling fee")),
                        is(orderVerificationOptions.get("recyclingFee")));
                Assert.assertThat("ERROR - 'Bedding recycling fee' is absent in service order summary",
                        orderSummaryServiceInfo.getString("recyclingFee").equalsIgnoreCase("$0.00"),
                        is(!orderVerificationOptions.get("recyclingFee")));
            }
        }
    }

    @Then("^I should see big ticket order confirmation page with order details$")
    public void checkBTOrderConfirmed() {

        //check order table record
        OrderConfirmation orderConfirmationPage = Navigate.get(OrderConfirmation.class);
        Wait.until(orderConfirmationPage.btOrderNumber::isDisplayed, 30);
        String orderNumber = orderConfirmationPage.getBtOrderNumber();
        OrderServices orderServices = new OrderServices();
        Assert.assertThat("ERROR - No order records found",
                orderServices.getOrderDetails(orderNumber).size(),
                greaterThan(0));
        //check BT_CREDIT_CARD_PAYMENT
        Map<String, String> btCreditCardPayment = orderServices.getBTCreditcardPayment(orderNumber);
        Assert.assertThat("ERROR - No credit card payment record found",
                btCreditCardPayment.size(),
                greaterThan(0));
        //check BT_SALESCHECK table
        List<Map<String, String>> salesCheck = orderServices.getBTSalescheck(orderNumber);
        Assert.assertThat("ERROR - No sales check records found",
                salesCheck.size(),
                greaterThan(0));
        Double orderTotal = salesCheck.stream().map(e -> e.get("TOTAL_AMT")).mapToDouble(Double::parseDouble).sum();
        logger.info("OrderTotal is ", orderTotal);
        Assert.assertThat("ERROR - total amount doesn't match",
                orderTotal.toString(),
                equalToIgnoringCase(orderConfirmationPage.getBtOrderTotal()));
        Assert.assertThat("ERROR - delivery method is not White Glove",
                orderConfirmationPage.getDeliveryMethod(),
                containsString("White Glove delivery"));
        Assert.assertThat("ERROR - zip code of order doesn't match product zip code",
                orderConfirmationPage.getShippingAddressZipCode(),
                containsString(TestUsers.getCurrentProduct().zipCode));
    }

    @And("^I add registry item to my bag using rest service$")
    public void addRegistryItemToBagService() throws ProductionException, UserException {
        RegistryService.createRandomRegistry(null);
        ServiceSteps.iAddAProductToMyBagUsingRestService("registrable and orderable", "available_bops");
    }

    @Then("^I verify user sees error message for added (registry|VGC|EGC) item in a bag$")
    public void verifyItemLevelErrorMessage(String productType) {
        String expectedErrorMsg = "We're sorry, %s cannot be included in this order. Please remove them to proceed.";
        switch (productType) {
            case "registry":
                expectedErrorMsg = String.format(expectedErrorMsg, "items from a wedding registry");
                break;
            case "EGC":
                expectedErrorMsg = String.format(expectedErrorMsg, "Gift Cards");
                break;
            case "VGC":
                expectedErrorMsg = String.format(expectedErrorMsg, "Gift Cards");
                break;
        }
        ShoppingBag shoppingBagPage = Navigate.get(ShoppingBag.class, true);
        Assert.assertThat("ERROR - expected error message is not displayed",
                shoppingBagPage.itemLevelErrorMessage().isDisplayed(),
                is(Boolean.TRUE));
        String errorMSG = shoppingBagPage.itemLevelErrorMessageText();
        Assert.assertThat("ERROR - item level error message text",
                errorMSG, equalToIgnoringCase(expectedErrorMsg));
    }

    @When("^I navigate to checkout page as an? \"(.*)\" user$")
    public void navigateToCheckoutPage(String userType) {
        boolean signIn = userType.contains("signed in") || signedIn();
        if (!(onPage("responsive_checkout", "checkout"))) {
            new CheckoutUtils().navigateToCheckout(signIn, false, false);
        }
    }

    @Then("^I verify user able to pay with card only$")
    public void verifyPayPalGCPlentiSupression() {
        HtmlElement paypalRadioButton;
        Button addGiftCardButton;
        if (signedIn()) {
            PaymentSignedInSection paymentSignedInSection = Navigate.get(SignedInCheckout.class).paymentSection();
            paypalRadioButton = paymentSignedInSection.paypalRadioButton;
            addGiftCardButton = paymentSignedInSection.addGiftCardButton;
        } else {
            PaymentGuestSection paymentGuestSection = Navigate.get(GuestCheckout.class).paymentSection();
            paypalRadioButton = paymentGuestSection.paypalRadioButton;
            addGiftCardButton = paymentGuestSection.addGiftCardButton;
        }
        Assert.assertThat("ERROR - PayPal payment option is available for user",
                Wait.until(paypalRadioButton::isDisplayed),
                is(Boolean.FALSE));

        Assert.assertThat("ERROR - Gift or redeem payment option is available for user",
                Wait.until(addGiftCardButton::isDisplayed),
                is(Boolean.FALSE));
    }

    //Creating a segment cookie sometimes its not getting dropped in QA environment
    void createSegmentCookie() {
        String segmentCookie = Cookies.getCookieValue("SEGMENT");
//        boolean isSegmentCookiePresent = Arrays.asList(cookies).contains(segmentCookie);
        if (segmentCookie.isEmpty()) {
            Cookies.addCookie("SEGMENT", "2630");
            logger.info("Added segment cookie");
        } else if (segmentCookie.contains("2629")) {
            Cookies.removeSegment("2629");
            Cookies.addSegment("2630");
            logger.info("Updated segment cookie");
        } else if (!segmentCookie.contains("2630") && !segmentCookie.contains("2629")) {
            Cookies.addSegment("2630");
        } else {
            logger.info("Big ticket segment cookie is already present");
        }
    }

    @And("^I verify the schedule delivery information in schedule delivery section:$")
    public void iVerifyTheScheduleDeliveryInformationOnConfirmationPage(List<String> msg) throws Exception {
        String actualMsg = Navigate.get(DeliverySchedulingSection.class).rcBtScheduleDelivery.getText();
        if (actualMsg.contains("Call to schedule delivery")) {
            Assert.assertThat("ERROR - Expected schedule delivery information is not displayed", actualMsg, equalToIgnoringCase("Call to schedule delivery after checkout:1-888-822-6229"));
        } else if (Checkout.selectedScheduleDate == null && actualMsg.matches(".*\\w{1,9}\\s\\d{2},\\s\\d{4}.*")) {
            Assert.assertThat("ERROR - Backordered expected schedule message is not displayed", actualMsg, containsString(msg.get(0)));
        } else if (Checkout.selectedScheduleDate != null && actualMsg.contains("In stock")) {
            Date st = (new SimpleDateFormat("MM/dd/yy")).parse(Checkout.selectedScheduleDate);
            String expScheduleDate = new SimpleDateFormat("MMMMM dd, yyyy").format(st);
            Assert.assertThat("ERROR - Expected schedule delivery is not displayed", actualMsg, equalToIgnoringCase(msg.get(0).replace("<schedule_date>", expScheduleDate)));
        } else {
            Assert.fail("Expected schedule delivery message is not displayed on checkout page");
        }
    }

    @Then("^I verify wnm protection plans are not displayed in service & fees section$")
    public void iVerifyWnmProtectionPlansAreNotDisplayedInServiceFeesSection() {
        Assert.assertThat("ERROR - 'WNM plans are displayed",
                Wait.until(Navigate.get(ServicesAndFeesSection.class).rcBigticketProtectionPlan::isDisplayed),
                is(Boolean.FALSE));
    }

    @And("^I lookup with order number and emailAddress in OrderHistory page after navigating to OH page$")
    public void iLookupWithOrderNumberAndEmailAddressInOrderHistoryPageAfterNavigatingToOHPage() {
        Home home = Navigate.to(Home.class);
        OrderStatus orderStatus = home.orderStatus();
        orderStatus.lookupByEmail(Orders.last().getNumber(), Orders.last().getEmail());
    }

    @And("^I enter a zipcode on BT PDP page$")
    public void iEnterAZipcodeOnBTPDPPage() {
        Wait.until(Navigate.get(ProductDisplay.class).btZipcodeField::isDisplayed, 30);
        Navigate.get(ProductDisplay.class).btZipcodeField.sendKeys(TestUsers.getCurrentProduct().zipCode);
    }


    @Then("^I should see \"([^\"]*)\" availability message on BT PDP page as:$")
    public void iShouldSeeAvailabilityMessageOnBTPDPPageAs(String type, List<String> msg) {
        ProductDisplay btPDP = Navigate.get(ProductDisplay.class);
        Wait.until(btPDP.btProductAvailabilityMsg::isDisplayed, 30);
        if (type.equals("BACKORDER")) {
            Assert.assertTrue("BT Backorder message is not seen as expected on PDP page",
                    btPDP.btProductAvailabilityMsg.getText().contains(msg.get(0)));
        } else if (type.equals("NOT_AVAILABLE")) {
            Assert.assertTrue("BT unavailable error message is not seen as expected on PDP page",
                    btPDP.btProductAvailabilityMsg.getText().contains(msg.get(0)));
        } else if (type.equals("NOT_ELIGIBLE_STATE")) {
            Assert.assertTrue("For availability and pricing in Hawaii and Puerto Rico, please call 1-800-289-6229.",
                    btPDP.btProductAvailabilityMsg.getText().contains(msg.get(0)));
        } else if (type.equals("ONHAND")) {
            Assert.assertTrue("BT available message is not seen as expected on PDP page",
                    btPDP.btProductAvailabilityMsg.getText().contains(msg.get(0).replace("<zip_code>", TestUsers.getCurrentProduct().zipCode)));
        }
    }
    
    @And("^I enter a valid \"([^\"]*)\" starting with zero's$")
    public void iEnterAValidStartingWithZeroS(String btZipcode) throws Throwable {
        Wait.until(Navigate.get(ProductDisplay.class).btZipcodeField::isDisplayed, 30);
        Navigate.get(ProductDisplay.class).btZipcodeField.sendKeys(btZipcode);
    }

    @Then("^I should see talk to experts copy message as:$")
    public void iShouldSeeTalkToExpertsCopyMessageAs(List<String> expectedMsg) throws Throwable {
        Wait.until(Navigate.get(ProductDisplay.class).bigticketTalkToExperts::isDisplayed, 30);
        Assert.assertEquals("Expected Talk to Experts copy message is not displayed", expectedMsg.get(0), Navigate.get(ProductDisplay.class).bigticketTalkToExperts.getText());
    }
}
