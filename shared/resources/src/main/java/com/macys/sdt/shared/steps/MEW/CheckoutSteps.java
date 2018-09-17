package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.MEW.pages.Checkout;
import com.macys.sdt.shared.actions.MEW.pages.CreateProfileMEW;
import com.macys.sdt.shared.actions.website.bcom.pages.PaypalLogin;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.utils.CheckoutUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

public class CheckoutSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutSteps.class);
    /**
     * This is the master step for going through the checkout flow
     * <p>
     * Options for Responsive pages:<br>
     * <code>shipping, payment, order review, order confirmation</code><br>
     * Options for international and legacy checkout pages:<br>
     * <code>shipping and payment, order review, order confirmation</code><br>
     * Options for user type:<br>
     * <code>guest, signed in</code><br>
     * For international or bops users include "iship" and "bops" respectively in the user type<br>
     * </p>
     *
     * @param pageName name of the page you want to end up on
     * @param userType either signed in or guest user
     * @param country  country the shipping address should be from (default US)
     * @throws Throwable if any exception occurs
     */
    @And("^I checkout until I reach the (shipping|payment|shipping\\s*&\\s*payment|order\\s*review|order\\s*confirmation) page using mobile website as an? \"([^\"]*)\" user(?: from \"([^\"]*)\")?$")
    public void I_checkout_until_I_reach_the_page_using_mobile_website_as_a_user(String pageName, String userType, String country) throws Throwable {
        boolean bops = userType.contains("bops");
        boolean signIn = userType.contains("signed in");
        HashMap<String, String> opts = new HashMap<>();
        if (country == null) {
            country = "United States";
        }

        boolean iship = userType.contains("iship") || !country.equalsIgnoreCase("United States");
        opts.put("country", country);

        if (!onPage("responsive_checkout") && !iship) {
            new CheckoutUtils().navigateToCheckout(signIn, false);
        }

        if (iship) {
            new Checkout().ishipCheckout(pageName, opts);
        } else if (signIn) {
            Wait.until(() -> onPage("responsive_checkout_signed_in"), 10);
            shouldBeOnPage("responsive_checkout_signed_in");
            new Checkout().rcSignedIn(pageName, opts, bops);
        } else {
            shouldBeOnPage("responsive_checkout");
            opts.put("checkout_eligible", "true");
            new Checkout().rcGuest(CheckoutUtils.RCPage.fromString(pageName), opts, bops);
        }
    }

    /**
     * Follow standard checkout flow but only if batch mode is enabled on environment
     * <p>
     * If batch mode is enabled, include the environment variable "batch_mode" with value "true"
     * <p>
     * Options for Responsive pages:<br>
     * <code>shipping, payment, order review, order confirmation</code><br>
     * Options for international and legacy checkout pages:<br>
     * <code>shipping and payment, order review, order confirmation</code><br>
     * Options for user type:<br>
     * <code>guest, signed in</code><br>
     * For international or bops users include "iship" and "bops" respectively in the user type<br>
     * </p>
     *
     * @param pageName name of the page you want to end up on
     * @param userType either signed in or guest user
     * @param country  country the shipping address should be from (default US)
     * @throws Throwable if any exception occurs
     */
    @And("^I checkout on batch mode until I reach the (shipping|payment|shipping\\s*&\\s*payment|order\\s*review|order\\s*confirmation) page using mobile website as an? \"([^\"]*)\" user(?: from \"([^\"]*)\")?$")
    public void I_checkout_on_batch_mode_until_I_reach_the_page_using_mobile_website_as_a_user(String pageName, String userType, String country) throws Throwable {
        // run this step only on Batch Mode enabled QA environment
        if (RunConfig.batchMode) {
            I_checkout_until_I_reach_the_page_using_mobile_website_as_a_user(pageName, userType, country);
        } else {
            logger.error("Environment not in batch mode, unable to checkout due to product unavailability.");
        }
    }

    /**
     * Fills in all shipping data on guest shipping page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I enter shipping address on guest shipping page using mobile website$")
    public void I_enter_shipping_address_on_guest_shipping_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("responsive_checkout");
        HashMap<String, String> addressOptions = new HashMap<>();
        addressOptions.put("checkout_eligible", "true");
        new Checkout().fillShippingData(true, false, addressOptions);
    }

    /**
     * Clicks "continue" on the responsive guest shipping page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select continue button on guest shipping page using mobile website$")
    public void I_select_continue_button_on_guest_shipping_page_using_mobile_website() throws Throwable {
        Clicks.click("responsive_checkout.continue_shipping_checkout_button");
        // some emulator devices fail here, no idea why
        Wait.untilElementNotPresent("responsive_checkout.continue_shipping_checkout_button");
        if (MEW() && Elements.elementPresent("responsive_checkout.continue_shipping_checkout_button")) {
            Utils.threadSleep(1000, null);
            Clicks.click("responsive_checkout.continue_shipping_checkout_button");
        }
    }

    /**
     * Fills in all payment data on guest payment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I fill in payment information on guest payment page using mobile website$")
    public void I_fill_in_payment_information_on_guest_payment_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("responsive_checkout");
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        new Checkout().fillPaymentData(true, false, opts);
    }

    /**
     * Click the "continue" button on guest payment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select continue button on guest payment page using mobile website$")
    public void I_select_continue_button_on_guest_payment_page_using_mobile_website() throws Throwable {
        // run this step only on Batch Mode enabled QA environment
        if (RunConfig.batchMode) {
            if (onPage("responsive_checkout")) {
                Clicks.click("responsive_checkout.continue_payment_checkout_button");
            } else {
                Clicks.click("payment_guest.continue_checkout_button");
            }
        } else {
            logger.error("Environment not in batch mode, unable to checkout due to product unavailability.");
        }
    }

    /**
     * Fills out sign in information on the checkout sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I sign in during checkout using mobile website$")
    public void I_sign_in_during_checkout_using_mobile_website() throws Throwable {
        User user = prodEnv() ? TestUsers.getProdCustomer().getUser() : TestUsers.getCustomer(null).getUser();
        if (!onPage("shipping_payment_signed_in")) {
            Navigate.visit("shopping_bag");
            Clicks.click("shopping_bag.continue_checkout_image");
        }
        if (Wait.until(()->onPage("checkout_sign_in"))) {
            TextBoxes.typeTextbox("checkout_sign_in.email", user.getProfileAddress().getEmail());
            TextBoxes.typeTextbox("checkout_sign_in.password", user.getLoginCredentials().getPassword());

            Assert.assertTrue("ERROR-ENV: CheckoutSteps button is not visible", Elements.elementPresent("checkout_sign_in.checkout_button"));
            Clicks.click("checkout_sign_in.checkout_button");
            Assert.assertFalse("ERROR - APP : Unable to sign in during checkout!!", Wait.untilElementPresent("checkout_sign_in.error_message"));
        } else {
            logger.info("Already signed in");
        }
    }

    /**
     * Adds the specified type of shipping address on the given checkout shipping page
     *
     * @param sdd_eligible sdd eligible or sdd ineligible
     * @param signedIn     guest or signedIn user
     * @throws Throwable if any exception occurs
     */
    @And("^I enter (sdd_eligible|sdd_ineligible) address on shipping page using mobile website for (guest|signed in) user$")
    public void I_enter_sdd_eligible_address_on_shipping_page_using_mobile_website_for_signed_in_user(String sdd_eligible, String signedIn) throws Throwable {
        boolean sdd = sdd_eligible.equals("sdd_eligible");
        HashMap<String, String> opts = new HashMap<>();
        opts.put(sdd_eligible, Boolean.toString(sdd));

        boolean signIn = signedIn.equals("signed in");
        String page = signIn ? "responsive_checkout_signed_in" : "responsive_checkout";
        if (prodEnv()) {
            page = "shipping_payment_signed_in";
        }

        if (signIn) {
            if (!Elements.elementPresent(page + ".select_sdd")) {
                if (prodEnv()) {
                    if (Elements.elementPresent(page + ".address_edit_link")) {
                        Clicks.click(page + ".address_edit_link");
                        Clicks.click(page + ".address_delete_button");
                        Clicks.click(page + ".confirm_delete");
                    }
                    Clicks.click(Elements.element(page + ".add_shipping_address_button"));
                    CreateProfileMEW.addNewAddress();
                } else {
                    shouldBeOnPage(page);
                    if (Elements.elementPresent(page + ".add_shipping_address_button")) {
                        Clicks.click(page + ".add_shipping_address_button");
                        Clicks.clickIfPresent(page + ".address_edit_link");
                    } else {
                        Clicks.click(page + ".save_shipping_address_button");
                    }
                    CreateProfileMEW.removeAddress();
                    //        click(page + ".add_shipping_address_button");
                    CreateProfileMEW.addNewAddress();
                    //  untilElementPresent(page + ".address_edit_link");
                    Wait.untilElementPresent(page + ".message");
                    if (!onPage("responsive_checkout_signed_in")) {
                        new CheckoutUtils().navigateToCheckout(true, false);
                    }
                }
            } else {
                Assert.fail("DATA ERROR : SDD address not added.");
            }
        } else {
            if (bloomingdales()) {
                new Checkout().fillShippingData(true, false, opts);
            } else {
                new com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout(opts, false).fillShippingData(false);
            }
        }
    }

    /**
     * Selects SDD shipping option if available
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select sdd_shipping in shipping methods using mobile website$")
    public void I_select_sdd_shipping_in_shipping_methods_using_mobile_website() throws Throwable {
        int i = 0;
        if (onPage("shipping_payment_signed_in")) {
            int num_tiles = Elements.findElements("shipping_payment_signed_in.shipping_address_tiles").size();
            while (!Elements.elementPresent("shipping_payment_signed_in.select_sdd") && i++ < num_tiles)
                Clicks.clickRandomElement("shipping_payment_signed_in.shipping_address_tiles");

            if (Elements.elementPresent("shipping_payment_signed_in.select_sdd")) {
                Clicks.click(Elements.element("shipping_payment_signed_in.select_sdd"));
            } else {
                Assert.fail("ERROR-DATA: Doesn't display sdd shipping method");
            }
        } else {
            Clicks.click("responsive_checkout_signed_in.change_shipping_method");
            if (Elements.elementPresent("responsive_checkout_signed_in.sdd_shipping")) {
                Clicks.click(Elements.element("responsive_checkout_signed_in.sdd_shipping"));
            } else {
                Assert.fail("ERROR-DATA: Doesn't display sdd shipping method");

            }
        }
    }

    /**
     * Selects an available store for bops item and selects apply button
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select pick up option for bops item using mobile website$")
    public void I_select_pick_up_option_for_bops_item_using_mobile_website() throws Throwable {
        Clicks.click("shopping_bag.bops_available");
        if (Elements.elementPresent("shopping_bag.bops_stores")) {
            Clicks.click("shopping_bag.bops_stores");
            Clicks.click("shopping_bag.select_bops");
            Clicks.click("shopping_bag.apply");
            Wait.untilElementPresent("shopping_bag.bag_items");
        } else {
            Assert.fail("ERROR-DATA: BOPS stores not available");
        }
    }

    /**
     * Clicks the "place order" button on the order review page and verify successfully navigated to order confirmation page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I place an Order using mobile site$")
    public void I_place_an_order() throws Throwable {
        pausePageHangWatchDog();
        Boolean responsive = !onPage("order_review");
        Wait.untilElementPresent((responsive ? "responsive_order_summary" : "order_review") + ".place_order");
        new com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout(new HashMap<>(), false).placeOrder();
        Wait.secondsUntilElementNotPresent((responsive ? "responsive_order_summary" : "order_review") + ".place_order", 10);
        Wait.secondsUntilElementNotPresent((responsive ? "responsive_order_summary" : "order_review") + ".mask", 10);
        Wait.secondsUntilElementPresent((responsive ? "responsive_order_confirmation" : "order_confirmation") + ".verify_page", 20);
        Assert.assertTrue("Order not placed successfully!!", onPage("responsive_order_confirmation"));
        resumePageHangWatchDog();
        logger.info("sucessfuly placed an order");
    }

    /**
     * Verify the promo code validation error message on the shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the promo code validation error message appeared in mobile website$")
    public void I_verify_the_promo_code_validation_error_message_appeared_in_mobile_website() throws Throwable {
        try {
            Elements.elementPresent(Elements.element("shopping_bag.promo_error"));
        } catch (NoSuchElementException e) {
            Assert.fail("Error message is not present on page");
        }
    }

    /**
     * Selects the "checkout with paypal" button on the shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select checkout with paypal in mobile site$")
    public void I_select_checkout_with_paypal() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("I_select_checkout_with_paypal()");
        }

        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        Wait.untilElementPresent("shopping_bag.checkout_with_paypal");
        Elements.elementInView("shopping_bag.checkout_with_paypal");
        Clicks.click("shopping_bag.checkout_with_paypal");
    }

    /**
     * Logs in to a stored paypal account
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I login into Paypal account using mobile site$")
    public void I_login_into_paypal_account() throws Throwable {
        new PaypalLogin().login();
    }

    /**
     * Clicks "continue" on the paypal login page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I checkout from Paypal review page using mobile site$")
    public void I_checkout_from_paypal_review_page() throws Throwable {
        Clicks.click("paypal_login.continue");
        Wait.secondsUntilElementNotPresent("paypal_login.continue", (safari() ? 15 : 5));
        if (safari() || ie()) {
            Thread.sleep(5000);
        }
    }

    /**
     * Removes the promo code from the shopping bag
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I remove the promo code using mobile website$")
    public void I_remove_the_promo_code_using_mobile_website() throws Throwable {
        try {
            Clicks.click(Elements.element("shopping_bag.promocode_remove"));
            Wait.forPageReady();
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not present on page");
        }
    }

    /**
     * Adds the given promo code to the shopping bag
     *
     * @param validity   valid or invalid expected
     * @param promo_code promo code to add
     * @throws Throwable if any exception occurs
     */
    @And("^I apply (valid|invalid) promo code \"([^\"]*)\" using mobile website$")
    public void I_apply_promo_code_using_mobile_website(String validity, String promo_code) throws Throwable {
        try {
            Assert.assertTrue("ERROR: promo code field is not present", Wait.untilElementPresent(Elements.element("shopping_bag.promocode_area")));
            Clicks.click(Elements.element("shopping_bag.promocode_area"));
            if (prodEnv() || (validity.equals("invalid"))) {
                TextBoxes.typeTextbox(Elements.element("shopping_bag.text_promocode"), promo_code);
            } else {
                TextBoxes.typeTextbox(Elements.element("shopping_bag.text_promocode"), promo_code);
            }
            Clicks.click(Elements.element("shopping_bag.btn_promocode_apply"));
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not visible on page: " + e);
        }

        List<Product> all = ShoppingBag.getAllProductDetails();
        if (all.size() == 1 && all.get(0).tuxItem)
            validity = "invalid";
        if (validity.equals("valid")) {
            Assert.assertTrue("ERROR-DATA: Not a valid promo code", Wait.untilElementPresent("shopping_bag.promo_code_remove"));
        } else {
            Assert.assertTrue("ERROR-DATA: Not an invalid promo code", Wait.untilElementPresent("shopping_bag.error_message"));
        }
    }

    /**
     * Adds the fully_enrolled_usl id on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add fully_enrolled_usl id on shopping bag page using mobile website$")
    public void I_add_fully_enrolled_usl_id_on_shopping_bag_page_using_mobile_website() throws Throwable {
        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        shouldBeOnPage("shopping_bag");
        if (Elements.getText("shopping_bag.plenti_id").contains("Add your Plenti # to earn points on qualifying purchases")) {
            String plenti_id = TestUsers.getEnrolledUslId().getPlentiId();
            Clicks.click("shopping_bag.plenti_container");
            Wait.untilElementPresent("shopping_bag.have_plenti_no");
            Clicks.click("shopping_bag.have_plenti_no");
            if (RunConfig.useAppium) {
                TextBoxes.typeTextbox("shopping_bag.plenti_id_textbox", plenti_id);
                Clicks.click("shopping_bag.have_plenti_no");
            } else {
                TextBoxes.typeTextNEnter("shopping_bag.plenti_id_textbox", plenti_id);
            }
            Assert.assertTrue("ERROR - ENV : Unable to add plenti id to shopping bag", Wait.secondsUntilElementPresent("shopping_bag.added_usl_id", 15));
            Clicks.click("shopping_bag.plenti_apply");
        }
    }

    /**
     * Verify merged message is displayed on merged_bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the functionality of merge bag using mobile website$")
    public void I_verify_the_functionality_of_merge_bag_using_mobile_website() throws Throwable {
        shouldBeOnPage("merged_bag");
        if (macys()) {
            String expected_message = "One or more items from your previous shopping session have been added to your Shopping Bag.";
            Assert.assertEquals(expected_message, Elements.getText("merged_bag.merged_message"));
        }
    }

    /**
     * Adds the shipping address if not present on shipping page for signed in user
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add shipping address if not present on shipping page using mobile website for signed in user$")
    public void I_add_shipping_address_if_not_present_on_shipping_page_using_mobile_website_for_signed_in_user() throws Throwable {
        Wait.until(() -> (Elements.elementPresent("responsive_checkout_signed_in.change_shipping_address") || Elements.elementPresent("responsive_checkout_signed_in.save_shipping_address_button")), 10);
        if (!Elements.elementPresent("responsive_checkout_signed_in.change_shipping_address")) {
            //Clicks.click("responsive_checkout_signed_in.add_shipping_address_button");
            CreateProfileMEW.addNewAddress();
            if (onPage("my_address_book")) {
                new CheckoutUtils().navigateToCheckout(true, false);
            }
            Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
        }
    }

    /**
     * Removes one item of the given item type from the shopping bag
     *
     * @param itemType registry or normal
     * @throws Throwable if any exception occurs
     */
    @When("^I remove (registry|normal) item from mobile shopping bag page$")
    public void I_remove__item_from_mobile_shopping_bag_page(String itemType) throws Throwable {
        shouldBeOnPage("shopping_bag");
        new ShoppingBag().removeBonusItemsFromBag();
        List<WebElement> items = Elements.findElements("shopping_bag.line_items");
        if (items == null || items.size() == 0) {
            Assert.fail("No products in shopping bag");
        } else {
            for (int i = 0; i < items.size(); i++) {
                boolean registryItem = !items.get(i).findElements(Elements.element("shopping_bag.registrant_name_details")).isEmpty();
                if (registryItem == (itemType.equals("registry"))) {
                    Clicks.click(Elements.findElements("shopping_bag.remove_item").get(i));
                    break;
                }
            }
        }
    }

    /**
     * Checks that shopping bag only contains given type of item
     *
     * @param itemType registry or normal
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see only (registry|normal) item is present in mobile shopping bag page$")
    public void I_should_see_only_item_is_present_in_mobile_shopping_bag_page(String itemType) throws Throwable {
        Navigate.browserRefresh();
        ShoppingBag.removeBonusItemsFromBag();
        shouldBeOnPage("shopping_bag");
        List<WebElement> items = Elements.findElements("shopping_bag.line_items");
        if (items == null || items.size() == 0) {
            Assert.fail("No products in shopping bag");
        } else {
            boolean registryItem = itemType.equals("registry");
            items.forEach(el ->
                    Assert.assertEquals((registryItem ? "Normal" : "Registry") + " item is present in shopping bag page",
                            registryItem, !el.findElements(Elements.element("shopping_bag.registrant_name_details")).isEmpty()));
        }
    }

    /**
     * Verifies the display of loyalty points earned on the order confirmation page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see loyalty points section on mobile order conformation page$")
    public void I_should_see_loyalty_points_section_on_mobile_order_conformation_page() throws Throwable {
        shouldBeOnPage("responsive_order_confirmation");
        Assert.assertTrue("Loyalty points section not displayed on order confirmation page",
                Wait.untilElementPresent("responsive_order_confirmation.loyalty_points_section"));
    }
    @When("^I select gift options on shipping page using mobile website$")
    public void i_select_gift_options_on_shipping_page_using_mobile_website() throws Throwable{
        String page_name = onPage("responsive_checkout")? "responsive_shipping_options": "shipping_guest";
        Wait.secondsUntilElementPresent(page_name +".gift_option", 20);
        WebElement gift_option = Elements.findElement(page_name+".gift_option");
        WebElement gift_message = Elements.findElement(page_name+".gift_message");
        if(gift_option!=null && !gift_option.isSelected())
            Clicks.click(page_name+".gift_option_check");
        if(gift_message!=null && !gift_message.isSelected())
            Clicks.click(page_name+".gift_message_check");
        TextBoxes.typeTextbox(page_name+".gift_message_field1", "test message");
        if (page_name.equals("shipping_guest"))
            Clicks.selectCheckbox("shipping_guest.gift_box");
    }

    @And("^I select use my shipping address checkbox on payment page using mobile website$")
    public void iSelectUseMyShippingAddressCheckboxOnPaymentPageUsingMobileWebsite() throws Throwable {
        Clicks.click("responsive_payment_guest_section.use_shipping_address");
    }

    @Then("^I should see SDD on order confirmation page$")
    public void iShouldSeeSDDOnOrderConfirmationPage() throws Throwable {
       Wait.untilElementPresent("responsive_order_confirmation.shipping_method");
       String  shippingMethod = macys() ? "Same-Day Delivery" : "Same Day Delivery";
       Assert.assertTrue("ERROR - APP: SDD shipping method not displayed on order confirmation page",
               Elements.getText("responsive_order_confirmation.shipping_method").contains(shippingMethod));
        if (macys()) {
            Elements.elementShouldBePresent("responsive_order_confirmation.sdd_message");
            Elements.elementShouldBePresent("responsive_order_confirmation.sdd_shipping_note");
        }
    }

    @Then("^I should see (m|b)Money (earn|redeem) section in shopping bag page using mobile website$")
    public void iShouldSeeMMoneyEarnedSectionInShoppingBagPageUsingMobileWebsite(String mb, String sectionType) throws Throwable {
        shouldBeOnPage("shopping_bag");
        Assert.assertTrue(mb + "Money " + sectionType + "section not displayed on shopping bag page", Wait.untilElementPresent("shopping_bag." + mb + "money_" + sectionType + "_section"));
    }

    @And("^I increment product quantity on shopping bage page using mobile website$")
    public void iIncrementProductQuantityOnShopingBagePageUsingMobileWebsite() throws Throwable {
        Clicks.click("shopping_bag.item_title");
        Wait.untilElementPresent("product_details.qty_value");
        int qty = Integer.parseInt(Elements.getText("product_details.qty_value"));
        for (int index = 0; index < qty + 2; index++)
            Clicks.click("product_details.increment_qty");
        Assert.assertEquals("Product quantity is not increment on shoping bage page", qty + 3, Integer.parseInt(Elements.getText("product_details.qty_value")));
        Clicks.click("product_details.apply_button");
        Wait.untilElementPresent("shopping_bag.item_title");
    }

    @And("^I should see estimated (m|b)Money amount on (shopping bag|checkout) page using mobile website$")
    public void iShouldSeeEstimatedMMoneyAmountOnShoppingBagPageUsingMobileWebsite(String mb, String page) throws Throwable {
        page = page.equals("shopping bag") ? "shopping_bag" : "responsive_order_summary";
        Wait.secondsUntilElementPresent(page + "." + mb + "money_estimated_earn_amount", 10);
        Elements.elementShouldBePresent(page + "." + mb + "money_estimated_earn_amount");
        String pageName = page;
        Wait.until(() -> Integer.parseInt(Elements.getText(pageName + "." + mb + "money_estimated_earn_amount").replaceAll("[^0-9]", "")) > 0);
        int estimatedAmount = Integer.parseInt(Elements.getText(page + "." + mb + "money_estimated_earn_amount").replaceAll("[^0-9]", ""));
        Assert.assertTrue("ERROR - DATA: Estimated "+ mb +"Money amount is 0 on " + page + " page", estimatedAmount > 0);
    }

    @And("^I apply promo code on shopping bag page using mobile website$")
    public void iApplyPromoCodeOnShoppingBagPageUsingMobileWebsite() throws Throwable {
        Assert.assertNotNull("ERROR-DATA: No promo code to apply", ProductDisplayPage.mew_promotion_code);
        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        Wait.untilElementPresent("shopping_bag.promocode_area");
        if (bloomingdales()) {
            Clicks.clickIfPresent("shopping_bag.apply_offer_link");
            Wait.secondsUntilElementPresentAndClick("shopping_bag.promocode_area", 5);
        }
        Clicks.clickIfPresent("shopping_bag.use_different_promo_code");
        TextBoxes.typeTextbox("shopping_bag.text_promocode", ProductDisplayPage.mew_promotion_code);
        Clicks.click("shopping_bag.btn_promocode_apply");
        Wait.secondsUntilElementNotPresent("shopping_bag.btn_promocode_apply", 10);
        Assert.assertTrue("ERROR-DATA: " + ProductDisplayPage.mew_promotion_code + " promo code is not applied in shopping bag page",
                macys() ? Wait.secondsUntilElementPresent("shopping_bag.promocode_remove", 10) :
                        Wait.until(() -> Elements.getText("shopping_bag.promocode_area").contains(ProductDisplayPage.mew_promotion_code)));
    }

    @Then("^I should see wallet offer details on shoping bag page using mobile website$")
    public void iShouldSeeWalletOfferDetailsOnShopingBagPageUsingMobileWebsite() throws Throwable {
        shouldBeOnPage("shopping_bag");
        Wait.untilElementPresent("shopping_bag.promocode_area");
        Clicks.click("shopping_bag.promocode_area");
        Wait.untilElementPresent("shopping_bag.bwallet_offer_details");
        Clicks.clickIfPresent("shopping_bag.expand_not_eligible_offers");
        Wait.untilElementPresent("shopping_bag.bwallet_offer_details");
        Elements.elementShouldBePresent("shopping_bag.bwallet_offer_details");
        Elements.elementShouldBePresent("shopping_bag.bwallet_offer_name");
        Elements.elementShouldBePresent("shopping_bag.bwallet_offer_exp_date");
    }

    @Then("^I should be on responsive checkout page$")
    public void iShouldBeOnCheckoutPage() throws Throwable {
        Wait.forPageReady();
        if (onPage("responsive_checkout_signed_in")) {
            Assert.assertTrue("User isn't on responsive checkout sign in page",
                    Elements.findElement("responsive_checkout_signed_in.place_order").isDisplayed());
            logger.info("User is on responsive checkout sign in page...");

        } else if (onPage("responsive_checkout")) {
            Assert.assertTrue("User isn't on responsive checkout page",
                    Elements.findElement("responsive_checkout.place_order").isDisplayed());
            logger.info("User is on responsive checkout page...");
        }
          else {
            Assert.fail("User isn't on checkout Page");
        }
    }

    @When("^I select Pick Up In Store facet$")
    public void iSelectPickUpInStoreFacet() throws Throwable {
        Clicks.javascriptClick("shopping_bag.bops_change_store_button");
        Wait.untilElementPresent("change_pickup_store_dialog.search_distance");
    }

    @When("^I search for the zipcode \"([^\"]*)\" in the Pick Up In Store accordion$")
    public void iSearchForTheZipcodeInThePickUpInStoreAccordion(String zipcode) throws Throwable {
        TextBoxes.typeTextbox("change_pickup_store_dialog.address_zip_code", zipcode);
        DropDowns.selectByText("change_pickup_store_dialog.search_distance", "100 miles");
        Clicks.click("change_pickup_store_dialog.search_button");

    }
    @When("^I select random facets$")
    public void iSelectRandomFacets() throws Throwable {
        Wait.secondsUntilElementPresent("change_pickup_store_dialog.pick_it_up_button", 10);
        Clicks.javascriptClick("change_pickup_store_dialog.pick_it_up_button");
    }

    @When("^I tap apply button in the accordion$")
    public void iTapApplyButtonInTheAccordion() throws Throwable {
        Wait.secondsUntilElementPresent("change_pickup_store_dialog.pick_up_in_store_apply", 10);
        Clicks.javascriptClick("change_pickup_store_dialog.pick_up_button");
        Wait.forPageReady();
        Wait.untilElementPresent("change_pickup_store_dialog.save");
        WebElement ele=Elements.findElement("change_pickup_store_dialog.save");
        Navigate.execJavascript("arguments[0].click();", ele);
        Navigate.execJavascript("location.reload()");
        }

    @Then("^I verify facet breadcrumbs$")
    public void iVerifyFacetBreadcrumbs() throws Throwable {
        Assert.assertTrue("Pagination is not visible for more then 48 products",
                Elements.findElement("shopping_bag.bops_shipping").isDisplayed());
        logger.info("Verify facet breadcrumbs");
    }
}