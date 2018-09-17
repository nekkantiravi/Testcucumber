package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.UserService;
import com.macys.sdt.shared.actions.MEW.pages.*;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.TypeElement;
import java.util.*;


public class MyAccount extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyAccount.class);
    /**
     * Creates a new profile
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new profile in mobile site$")
    public void I_create_a_new_profile_in_mobile_site() throws Throwable {
        TestUsers.clearCustomer();
        CreateProfileMEW.createProfile(TestUsers.getCustomer(null));
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("my_account.verify_page", 5);
        shouldBeOnPage("my_account");
        CreateProfileMEW.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile.closeSecurityAlertPopUp();
            Clicks.javascriptClick(Elements.element("my_account.add_card_overlay_no_thanks_button"));
        }
        if(prodEnv()){
            logger.info("Locking credit card: Production customer");
        }
        else {
            I_add_a_credit_card_from_my_account_page_using_mobile_website();
            if (onPage("my_account")) {
                Navigate.visit("my_account");
            } else {
                Navigate.browserBack();
            }
        }
    }

    @And("^I create a new profile in mobile site without CC added$")
    public void iCreateANewProfileInMobileSiteWithoutCCAdded() {
        TestUsers.clearCustomer();
        CreateProfileMEW.createProfile(TestUsers.getCustomer(null));
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("my_account.verify_page", 5);
        shouldBeOnPage("my_account");
        CreateProfileMEW.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
    }

    /**
     * Signs out from currently signed in profile
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I sign out from my current mobile site profile$")
    public void I_sign_out_from_my_current_mobile_site_profile() throws Throwable {
        Elements.elementInView("my_account.goto_sign_out_link");
        Assert.assertTrue("ERROR - ENV: Sign out link is not present.", Wait.untilElementPresent("my_account.goto_sign_out_link"));
        Clicks.click("my_account.goto_sign_out_link");
        Wait.untilElementNotPresent("my_account.goto_sign_out_link");
        // sign out doesn't like to stick
        if (Elements.elementPresent("my_account.goto_sign_out_link")) {
            Elements.elementInView(("my_account.goto_sign_out_link"));
            Clicks.javascriptClick("my_account.goto_sign_out_link");
        }
        Assert.assertTrue("ERROR - ENV: Unable to sign out.", Wait.secondsUntilElementPresent("home.goto_sign_in_link",10));
    }

    /**
     * Signs in to existing profile or creates a new one
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I sign in to my existing profile using mobile website$")
    public void I_sign_in_to_my_existing_profile_using_mobile_website() throws Throwable {
        CommonUtils.signInOrCreateAccount();
        Navigate.visit("home");
    }

    /**
     * Navigates to the my profile page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to my profile page using mobile website$")
    public void I_navigate_to_my_profile_page_using_mobile_website() throws Throwable {
        Clicks.click("my_account.my_profile");
        shouldBeOnPage("my_profile");
        resumePageHangWatchDog();
    }

    /**
     * Adds a credit card to my wallet on my wallet page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add a credit card from my wallet page using mobile website$")
    public void I_add_a_credit_card_from_my_account_page_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        if ((!onPage("my_account")) && (!onPage("my_bwallet"))) {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
            GlobalNav.closeGlobalNav();
        }
        I_navigate_to_the_wallet_page_using_mobile_website();
        //Check before attempt to delete a CC
        if (Elements.elementPresent("oc_my_wallet.cc_container")) {
            MyWallet.deleteCreditCard();
        }

        CommonUtils.addCreditCardFromBWallet(null, null);
    }

    /**
     * Adds a credit card to my wallet if not present
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add a credit card to my wallet if not present using mobile website$")
    public void I_add_a_credit_card_to_my_wallet_if_not_present_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        if ((!onPage("my_account")) && (!onPage("my_bwallet"))) {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
            GlobalNav.closeGlobalNav();
        }
        I_navigate_to_the_wallet_page_using_mobile_website();
        if (!Elements.elementPresent("oc_my_wallet.credit_cards")) {
            CommonUtils.addCreditCardFromBWallet(null, null);
        }
    }

    /**
     * Verifies that you are on store page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to store page using mobile website$")
    public void I_should_be_redirected_to_store_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("stores");
    }

    /**
     * Searches for store, by given search input and criteria in stores page
     *
     * @param search_input    to search for
     * @param search_criteria to search by
     * @throws Throwable if any exception occurs
     */
    @When("^I search for \"([^\"]*)\" as a \"([^\"]*)\" in stores page using mobile website$")
    public void I_search_using_as_a_in_stores_page_using_mobile_website(String search_input, String search_criteria) throws Throwable {
        switch (search_criteria) {
            case "zipcode":
                TextBoxes.typeTextbox(Elements.element("stores.search_box"), search_input);
                Clicks.click("stores.search_near_me");
                break;
        }
    }

    /**
     * Type given text into the search box of the stores pag
     *
     * @param search_input text to type in search box
     */
    @When("^I type \"([^\"]*)\" into the search bar of the stores page using mobile website$")
    public void I_type_into_the_search_bar_of_stores_page_using_mobile_website(String search_input) {
        TextBoxes.typeTextbox(Elements.element("stores.search_box"), search_input);
    }

    /**
     * Verifies store auto complete suggestions are visible
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see auto-complete suggestion store names$")
    public void I_should_see_auto_complete_suggestion_store_names() throws Throwable {
        if (Elements.elementPresent(Elements.element("stores.autocomplete_container"))) {
            logger.info("Autocomplete suggestion store names displayed");
        } else {
            Assert.fail("Autocomplete suggestion store names not displayed");
        }
    }

    /**
     * Selects random option from store auto complete suggestions
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select a auto-complete suggestion store name$")
    public void I_select_a_auto_complete_suggestion_store_name() throws Throwable {
        if (Wait.untilElementPresent(Elements.element("stores.autocomplete_text"))) {
            Clicks.clickRandomElement("stores.autocomplete_text");
            Wait.untilElementPresent(Elements.element("stores.store_list"));
            logger.info("All the stores loaded successfully");
        } else {
            Assert.fail("Autocomplete suggestion store names not displayed");
        }
    }

    /**
     * Selects given store from stores list
     *
     * @param store_name store to select
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" store name$")
    public void I_select_store_name(String store_name) throws Throwable {
        if (Wait.untilElementPresent(Elements.element("stores.store_list"))) {
            store_name = '"' + store_name + '"';
            Clicks.click(Elements.paramElement("stores.select_store", store_name));
        } else {
            Assert.fail("Facet container not loaded");
        }
    }

    /**
     * Verifies store details panel is visible
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to store details panel using mobile website$")
    public void I_should_be_redirected_to_store_details_panel_using_mobile_website() throws Throwable {
        if (Elements.elementPresent(Elements.element("stores.store_address"))) {
            logger.info("Store Details panel loaded successfully");
        } else {
            Assert.fail("Store Details panel not loaded");
        }
    }

    /**
     * Selects given element on stores details page
     *
     * @param element element to select
     * @throws Throwable if any exception occurs
     */
    @When("^I select \"([^\"]*)\" from store details page using mobile website$")
    public void I_select_from_store_details_page_using_mobile_website(String element) throws Throwable {
        switch (element) {
            case "Directions":
                Clicks.click("stores.directions");
                Navigate.switchWindow(1);
                Navigate.switchWindowClose();
                break;
        }
    }

    /**
     * Navigates to the my wallet page from the my account page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to the wallet page using mobile website$")
    public void I_navigate_to_the_wallet_page_using_mobile_website() throws Throwable {
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        if (Wait.secondsUntilElementPresent("my_account.mywallet_link", 5)) {
            Clicks.javascriptClick("my_account.mywallet_link");
            Wait.forPageReady();
            shouldBeOnPage("oc_my_wallet");
        }
    }

    /**
     * Selects a random deal of given type from deals and promotion page
     *
     * @param deal offer or coupon
     * @throws Throwable if any exception occurs
     */
    @When("^I select a random \"([^\"]*)\" from deals & promotions page using mobile website$")
    public void I_select_a_random_from_deals_promotions_page_using_mobile_website(String deal) throws Throwable {
        boolean expected = false;
        List<WebElement> offer = Elements.findElements("offer_details.offers_container");
        int size = offer.size();
        if (size > 0 && (expected == false)) {
            for (WebElement anOffer : offer) {
                Clicks.click(anOffer);
                switch (deal) {
                    case "offer":
                        if (Wait.untilElementPresent("offer_details.shop_now") && !Elements.findElement("offer_details.offer_promocode").getText().contains("no promo code")) {
                            expected = true;
                        }
                        break;
                    case "coupon":
                        if (Wait.untilElementPresent("offer_details.add_to_wallet")) {
                            expected = true;
                        }
                }
                if (expected) {
                    break;
                } else {
                    Clicks.clickWhenPresent("offer_details.back");
                }
            }
        } else {
            Assert.fail("ERROR-DATA: Cannot find offers and coupons");
        }
    }

    /**
     * Selects given link from offers and details panel
     *
     * @param offer_link shop now or Add To Wallet
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" link from offers and details panel using mobile website$")
    public void I_select_link_from_offers_and_details_panel_using_mobile_website(String offer_link) throws Throwable {
        switch (offer_link) {
            case "shop now":
                if (Wait.untilElementPresent("deals_and_promotions.shop_now")) {
                    Clicks.click("offer_details.shop_now");
                    Clicks.clickIfPresent("offer_details.back");
                } else {
                    Assert.fail("ERROR-DATA: shop now button is not visible");
                }
                break;
            case "Add To Wallet":
                if (Wait.untilElementPresent("deals_and_promotions.add_to_wallet")) {
                    Clicks.click("offer_details.add_to_wallet");
                    Clicks.click("offer_details.back");
                } else {
                    Assert.fail("ERROR-DATA: add to wallet button is not visible");
                }
        }
    }

    /**
     * Adds a fully enrolled usl ID to current profile on my account page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add fully_enrolled_usl id on my account page using mobile website$")
    public void I_add_fully_enrolled_usl_id_on_my_account_page_using_mobile_website() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("I_add_fully_enrolled_usl_id_on_my_account_page_using_mobile_website()");
        }

        Clicks.clickIfPresent("my_account.plenti_id");
        String plentiId = TestUsers.getEnrolledUslId().getPlentiId();
        UserService.removeUslIdFromAllUsers(plentiId);
        Clicks.javascriptClick("my_account.usl_id_tab");
        TextBoxes.typeTextbox("my_account.usl_id", plentiId);
        Clicks.javascriptClick("my_account.apply_usl_id_button");
        Assert.assertTrue("ERROR - ENV : Unable to add usl id to profile", Wait.secondsUntilElementPresent("my_account.plenti_available_points", 5));
        Navigate.visit("my_account");
    }

    /**
     * Removes USL ID on checkout shipping and payment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I remove USL ID from shipping and payment page using mobile website$")
    public void I_remove_USL_ID_from_shipping_and_payment_page_using_mobile_website() throws Throwable {
        Wait.untilElementPresent("shipping_payment_signed_in.remove_usl_button");
        Assert.assertTrue("ERROR-ENV: No USL ID added to the profile", Elements.elementPresent("shipping_payment_signed_in.remove_usl_button"));
        Clicks.click("shipping_payment_signed_in.remove_usl_button");
        Wait.untilElementPresent("shipping_payment_signed_in.lookUpLoyaltyId");
        Assert.assertTrue("ERROR-ENV: Unable to remove USL ID from shipping and payment page", Elements.elementPresent("shipping_payment_signed_in.lookup_link"));

    }

    /**
     * Remove all existing offers from wallet and adds a valid offer to wallet
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add a valid offer to my wallet using mobile website$")
    public void I_add_a_valid_offer_to_my_wallet_using_mobile_website() throws Throwable {
        if (Elements.elementPresent("oc_my_wallet.available_offers")) {
            MyOffers.deleteOffers();
            MyOffers.addOffers();
        } else {
            MyOffers.addOffers();
        }
    }

    /**
     * Navigates to loyalty landing page as a given user type
     *
     * @param user_type guest or signed_in
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the loyalty landing page as a \"([^\"]*)\" user using mobile website$")
    public void iNavigateToTheLoyaltyLandingPageAsAUser(String user_type) throws Throwable {
        // Before landing to the Loyalty enrollment page check whether the loyalty account already associated to the signed in account
        if (signedIn() && Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            logger.info("--> User is already enrolled in Loyalty!!");
        } else {
            Clicks.click("home.become_guest_loyallist");
            switch (user_type.toLowerCase()) {
                case "guest":
                    shouldBeOnPage("loyalty_home");
                    break;
                case "signed_in":
                    shouldBeOnPage("loyalty_enrollment");
                    break;
            }
        }
    }

    /**
     * Navigates to loyalty enrollment page from loyalty home page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to the loyalty enrollment page using mobile website$")
    public void iNavigateToTheLoyaltyEnrollmentPage() throws Throwable {
        Clicks.click("loyalty_home.create_profile_enroll_button");
        shouldBeOnPage("loyalty_enrollment");
    }

    /**
     * Navigates to the loyalist account association page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the loyallist account association page using mobile website$")
    public void iNavigateToTheLoyallistAccountAssociationPage() throws Throwable {
        // Before landing to the Loyalty association page check whether the loyalty account already associated to the signed in account
        if (Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            Clicks.click("my_account.view_my_loyalllist_account");
            shouldBeOnPage("loyallist_account_summary");
            Clicks.click("loyallist_account_summary.remove_button");
            Wait.untilElementPresent("loyallist_account_summary.lty_account_panel");
            Clicks.click("loyallist_account_summary.remove_confirmation_btn");
        } else {
            Elements.elementInView("my_account.goto_my_loyallist");
            Clicks.clickWhenPresent("my_account.goto_my_loyallist");
        }
        Wait.untilElementPresent("loyalty_association.verify_page");
        shouldBeOnPage("loyalty_association");
    }

    /**
     * Clicks on add offer on wallet page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click on add offer on wallet page using mobile website$")
    public void iClickOnAddOfferOnWalletPage() throws Throwable {
        Clicks.click((macys() ? "oc_my_wallet" : "my_bwallet") + ".add_offer_btn");
    }

    /**
     * Visits the website as a given user type and DOES NOT add new credit card to profile
     *
     * @param registered user type guest or registered
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the mobile web site as a (guest|registered) user without add CC$")
    public void I_visit_the_mobile_web_site_as_a_registered_user(String registered) throws Throwable {
        Navigate.visit("home");
        pausePageHangWatchDog();
        // close popup
        Clicks.clickIfPresent("home.popup_close");

        closeMewTutorial();
        closeChatAlert();
        Thread.sleep(5000);
        if (registered.equals("registered")) {
            CommonUtils.signInOrCreateAccount();
            // close CC popup
            Clicks.clickIfPresent("my_account.add_card_overlay_close_button");
            Navigate.visit("home");
        }
        Cookies.disableForeseeSurvey();
    }

    /**
     * Verifies a loyallist number can be associated with a user account
     *
     * @param loyallist_type type of loyallist ID to use from "loyalty.json" data file
     * @throws Throwable if any exception occurs
     */
    @And("^I should be able to associate my account by loyallist number using \"([^\"]*)\" details on mobile website$")
    public void iShouldBeAbleToAssociateMyAccountByLoyallistNumberUsingDetails(String loyallist_type) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("iShouldBeAbleToAssociateMyAccountByLoyallistNumberUsingDetails()");
        }

        LoyallistAssociation.loyaltyAssociation(TestUsers.getLoyallistInformation(loyallist_type));
        Wait.untilElementPresent("loyallist_account_summary.verify_page");
        shouldBeOnPage("loyallist_account_summary");
    }

    /**
     * Enrolls in the loyalty program as given user type
     *
     * @param user_type guest or signed_in
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be able to enroll in to the loyalty program as a \"([^\"]*)\" user using mobile website$")
    public void iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser(String user_type) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser()");
        }
        String pageName = null;
        if (signedIn() && Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            Clicks.click("my_account.view_my_loyalllist_account");
            Wait.forPageReady();
            logger.info("--> User is already enrolled in Loyalty, navigating to loyalty_enrollment_confirmation!!");
            pageName = "loyallist_account_summary";
        } else {
            //Elements.elementInView("loyalty_home.create_profile_enroll_button");
            Clicks.clickIfPresent("loyalty_home.create_profile_enroll_button");
            LoyaltyEnrollment enrollmentPage = new LoyaltyEnrollment();
            switch (user_type.toLowerCase()) {
                case "guest":
                    TestUsers.clearCustomer();
                    enrollmentPage.guestUserLoyaltyEnrollmentMobileWebsite(TestUsers.getCustomer(null));
                    break;
                case "signed_in":
                    enrollmentPage.signedInUserLoyaltyEnrollment(TestUsers.getCustomer(null));
                    break;
            }
            pageName = "loyalty_enrollment_confirmation";
        }
        Assert.assertTrue("Loyalty Enrollment Confirmation Page Not Loaded Properly", Wait.untilElementPresent(pageName + ".loyalty_number"));
        logger.info("Loyalty Enrollment Confirmation Page Loaded Successfully!!!");
    }

    /**
     * Remove all existing offers from wallet and adds the given offer to wallet
     *
     * @param code offer code to add
     * @throws Throwable if any exception occurs
     */
    @And("^I add a offer \"([^\"]*)\" to my wallet using mobile website$")
    public void I_add_a_offer_to_my_wallet_using_mobile_website(String code) throws Throwable {
        if (Elements.elementPresent("oc_my_wallet.available_offers")) {
            MyOffers.deleteOffers();
            MyOffers.addValidOffers(code);
        } else {
            MyOffers.addValidOffers(code);
        }
    }

    /**
     * Add an offer to wallet if no offer is present and then removes all offers from wallet
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I remove a valid offer to my wallet using mobile website$")
    public void I_remove_a_valid_offer_to_my_wallet_using_mobile_website() throws Throwable {
        if (Elements.elementPresent("oc_my_wallet.available_offers")) {
            MyOffers.deleteOffers();
        } else {
            MyOffers.addOffers();
            MyOffers.deleteOffers();
        }
    }

    /**
     * Looks up a plenti ID using valid usl phone number on payment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I lookup plenti id using valid usl phone number on payment page using mobile website$")
    public void I_lookup_plenti_id_using_valid_usl_phone_number_on_payment_page_using_mobile_website() throws Throwable {
        String phone_no = TestUsers.getEnrolledUslId().getUslPhone();
        Wait.untilElementPresent("shipping_payment_signed_in.lookup_link");
        Clicks.click("shipping_payment_signed_in.lookup_link");
        TextBoxes.typeTextbox("shipping_payment_signed_in.usl_phone_number", phone_no);
        Clicks.click("shipping_payment_signed_in.usl_search_phone");
        Wait.untilElementPresent("shipping_payment_signed_in.remove_usl_button");
        Assert.assertTrue("ERROR-ENV: Unable to lookup plenti id usign phone number", Elements.elementPresent("shipping_payment_signed_in.remove_usl_button"));
    }

    /**
     * Signs in to current existing profile
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I sign in to my same profile using mobile website$")
    public void I_sign_in_to_same_profile_using_mobile_website() throws Throwable {
        if (!prodEnv()) {
            Clicks.click("home.goto_sign_in_link");
            TextBoxes.typeTextbox("sign_in.email", TestUsers.currentEmail);
            TextBoxes.typeTextbox("sign_in.password", TestUsers.currentPassword);
            //Clicks.click("sign_in.sign_in_button");
            Clicks.javascriptClick("sign_in.sign_in_button");
            Assert.assertTrue("ERROR - ENV: Unable to sign in to the application", Wait.untilElementPresent("footer.goto_sign_out_link"));
        }
    }

    /**
     * Creates a new profile and DOES NOT close the add credit card dialog
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I create a new profile in mobile site without closing the add card overlay$")
    public void I_create_a_new_profile_in_mobile_site_without_closing_the_add_card_overlay() throws Throwable {
        TestUsers.clearCustomer();
        CreateProfileMEW.createProfile(TestUsers.getCustomer(null));
        if (!onPage("my_account")) {
            Assert.fail("New Profile is not created");
        }
        CreateProfileMEW.closeSecurityAlertPopUp();
        Wait.untilElementPresent("my_account.one_time_add_card_overlay");
    }

    /**
     * Verifies the display of the my account pages
     *
     * @param pageNames list of pages to verify
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the My Account Pages are rendered properly using mobile website$")
    public void I_verify_the_My_Account_Pages_are_rendered_properly_using_mobile_website(List<String> pageNames) throws Throwable {
        shouldBeOnPage("my_account");
        for (String pageName : pageNames) {
            I_navigate_to_page_from_my_account_page_using_mobile_website(pageName);
            shouldBeOnPage(pageName.replace(" ", "_"));
            Navigate.visit("my_account");
        }
    }

    /**
     * Navigates to given page from my account page
     * <p>
     * Options for page name:<br>
     * <code>my profile, my preferences, my address book, oc my wallet, wish list, order status, furniture mattress status, gift card balance</code><br>
     * </p>
     *
     * @param pageName target page name
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to \"([^\"]*)\" page from my account page using mobile website$")
    public void I_navigate_to_page_from_my_account_page_using_mobile_website(String pageName) throws Throwable {
        shouldBeOnPage("my_account");
        switch (pageName.toLowerCase()) {
            case "my profile":
                Clicks.click("my_account.my_profile");
                break;
            case "my preferences":
                Clicks.javascriptClick("my_account.my_preferences");
                break;
            case "my address book":
                Clicks.click("my_account.my_address_book");
                break;
            case "oc my wallet":
                Clicks.javascriptClick("my_account.mywallet_link");
                break;
            case "wish list":
                if (macys()) {
                    Clicks.javascriptClick("my_account.my_lists");
                    Clicks.clickIfPresent("my_account.my_list");
                } else {
                    GlobalNav.openGlobalNav();
                    GlobalNav.navigateOnGnByName("MENU");
                    GlobalNav.navigateOnGnByName("WISH LIST");
                    GlobalNav.closeGlobalNav();
                }
                break;
            case "order status":
                if (macys()) {
                    Clicks.clickIfPresent("my_account.my_order");
                    Clicks.javascriptClick("my_account.view_order_history_link");
                } else {
                    Clicks.clickIfPresent("site_menu.site_menu_title");
                    Clicks.javascriptClick("site_menu.order_status");
                }
                break;
            case "furniture mattress status":
                Clicks.clickIfPresent("my_account.my_order");
                Clicks.javascriptClick("my_account.furniture_mattress_status");
                break;
            case "gift card balance":
                Clicks.click("my_account.gift_card_balance");
                break;
        }
    }

    /**
     * Verifies that the given credit services links displayed on my account page
     *
     * @param credit_links list of credit services links
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see below credit services links in my account page:$")
    public void iShouldSeeBelowCreditServicesLinksInMobileMyaccountPage(List<String> credit_links) throws Throwable {
        shouldBeOnPage("my_account");
        Assert.assertFalse("ERROR - ENV : CITI services are down!!", Elements.elementPresent("my_account.citi_service_down_message"));
        for (String credit_link : credit_links) {
            Assert.assertTrue(credit_link + " not present on my account page.", Wait.untilElementPresent("my_account." + credit_link));
        }
    }

    /**
     * Verifies that the given credit services links navigates to given respective pages
     *
     * @param list of link and respective page
     * @throws Throwable if any exception occurs
     */
    @And("^I should be navigated to below respective credit services pages using mobile website:$")
    public void iShouldBeNavigatedToBelowRespectiveCreditServicesPagesUsingMobileWebsite(List<HashMap<String, String>> list) throws Throwable {
        for (Map set : list) {
            Assert.assertFalse("ERROR - ENV : Credit Services are down!!", Elements.getText("my_account.page_notifications").contains("Maintenance Alert"));
            shouldBeOnPage("my_account");
            Elements.elementShouldBePresent("my_account." + set.get("credit_link"));
            Clicks.click("my_account." + set.get("credit_link"));
            Wait.secondsUntilElementNotPresent(set.get("landing_page").toString() + ".verify_page", 30);
            shouldBeOnPage(set.get("landing_page").toString());
            Navigate.visit("home");
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
        }
    }

    /**
     * Enrolls in to the loyalty program as a signed in user
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I enroll in to the loyalty program using mobile website as a signed in user$")
    public void I_enroll_in_to_the_loyalty_program_using_mobile_website_as_a_user() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("iEnrollInToTheLoyaltyProgramAsAUserUsingMobileWebsite()");
        }
        Clicks.click("loyalty_home.create_profile_enroll_button");
        new LoyaltyEnrollment().signedInUserLoyaltyEnrollment(TestUsers.getCustomer(null));
        shouldBeOnPage("loyalty_enrollment_confirmation");
    }

    /**
     * Verifies that the add credit card overlay and its components are displayed or not
     *
     * @param condition should or should not
     * @throws Throwable if any exception occurs
     */
    @Then("^I (should|should not) see one time add card overlay and its components using mobile website$")
    public void iShouldSeeOneTimeAddCardOverlayAndItsComponentsUsingMobileWebsite(String condition) throws Throwable {
        String add_card_elements[] = {"one_time_add_card_overlay", "add_card_overlay_add_card_button", "add_card_overlay_close_button"};
        Wait.untilElementPresent("my_account.one_time_add_card_overlay");
        if (condition.equals("should")) {
            for (String element : add_card_elements) {
                Assert.assertTrue(element + " element is not displayed on my account page!!", Elements.elementPresent("my_account." + element));
            }
        } else {
            Assert.assertFalse("Add credit card overlay is displayed on my account page", Elements.elementPresent("my_account.one_time_add_card_overlay"));
        }
    }

    /**
     * Navigates to order details page from order status page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to order details page using mobile website$")
    public void I_navigate_to_order_details_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("order_status");
        if (macys()){
            List<String> all = DropDowns.getAllValues("order_status.order_date_range");
            for (String a : all) {
                DropDowns.selectByText("order_status.order_date_range", a);
                if (Wait.untilElementPresent("order_status.order_details"))
                    break;
            }
        }
        Clicks.click("order_status.order_details");
    }

    /**
     * Verifies that an order can be cancelled from order details page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the ability to cancel the order in order details page using mobile website$")
    public void I_verify_the_ability_to_cancel_the_order_in_order_details_page_using_mobile_website() throws Throwable {
        Wait.untilElementPresent("order_details.cancel_order_button");
        Clicks.click("order_details.cancel_order_button");
        Wait.untilElementPresent("order_details.order_cancel_yes_button");
        Clicks.click("order_details.order_cancel_yes_button");
        Wait.untilElementNotPresent("order_details.order_cancel_yes_button");
        String cancelText = macys() ? "canceled" : "CANCELLED";
        if (!Elements.getText("order_details.order_status_text").contains(cancelText) &&
                !Elements.getText("order_details.order_total_amount").replace("$", "").equals("0.00")) {
            Assert.fail("Order not cancelled successfully");
        }
    }

    /**
     * Navigates to order return confirmation page using given order data
     * <p>
     * Order details come from "return_orders.json" resource file in shared data
     * </p>
     *
     * @param orderType "submitted", "intransit", or "transit"
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to order details page for \"([^\"]*)\" order using mobile website$")
    public void I_navigate_to_order_details_page_for_order_using_mobile_website(String orderType) throws Throwable {
        pausePageHangWatchDog();
        String orderNum = Utils.getOrderNumber(orderType);
        resumePageHangWatchDog();
        Boolean orderFound = false;
        shouldBeOnPage("order_status");
        for (String dataRange : DropDowns.getAllValues("order_status.order_date_range")) {
            DropDowns.selectByText("order_status.order_date_range", dataRange);
            Wait.forPageReady();
            Clicks.clickIfPresent("order_status.view_more_orders");
            Wait.forPageReady();
            for (WebElement order : Elements.findElements("order_status.order_number")) {
                if (order.getText().replaceAll("[a-zA-Z]|[#: ]", "").equals(orderNum)) {
                    Clicks.click(order);
                    orderFound = true;
                    break;
                }
            }
            if (orderFound) {
                break;
            }
        }
        Assert.assertTrue("ERROR - DATA : Order " + orderNum + " not found in data range", orderFound);
    }

    /**
     * Verifies the details of order with the given status on the order details page
     *
     * @param orderType status to check
     * @throws Throwable if any exception occurs
     */
    @And("^I verify order details in OD page for \"([^\"]*)\" using mobile website$")
    public void I_verify_order_details_in_OD_page_for_using_mobile_website(String orderType) throws Throwable {
        shouldBeOnPage("order_details");
        Elements.elementShouldBePresent("order_details.order_status_text");
        Assert.assertTrue("Header staus is not displaying", Elements.getText("order_details.order_status_text").contains(orderType));
        Elements.elementShouldBePresent("order_details.shipping_address_container");
        for (WebElement items : Elements.findElements("order_details.shipping_address_container")) {
            Elements.elementShouldBePresent(items.findElement(Elements.element("order_details.shipping_address")));
            Elements.elementShouldBePresent(items.findElement(Elements.element("order_details.shipping_method")));
            Elements.elementShouldBePresent(items.findElement(Elements.element("order_details.shipping_status")));
        }
        Elements.elementShouldBePresent("order_details.item_container");
        for (WebElement itemList : Elements.findElements("order_details.item_container")) {
            Elements.elementShouldBePresent(itemList.findElement(Elements.element("order_details.item_description")));
            Elements.elementShouldBePresent(itemList.findElement(Elements.element("order_details.gift_box")));
            Elements.elementShouldBePresent(itemList.findElement(Elements.element("order_details.item_qty")));
            Elements.elementShouldBePresent(itemList.findElement(Elements.element("order_details.total")));
        }
        Elements.elementShouldBePresent("order_details.order_total_details");
    }

    /**
     * Navigates to loyalty enrollment page as a registered user
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the loyallist enrollment page as a registered user using mobile website$")
    public void I_navigate_to_the_loyallist_enrollment_page_as_a_registered_user_using_mobil_website() throws Throwable {
        // Before landing to the Loyalty enrollment page check whether the loyalty account already associated to the signed in account
        if (signedIn() && Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            logger.info("--> User is already enrolled in Loyalty!!");
        } else {
            Clicks.click("my_account.become_a_loyallist");
            shouldBeOnPage("loyalty_enrollment");
        }
    }

    public static List<Product> tux_productDetailsODPage() {
        List<Product> productDetails = new ArrayList<>();
        Wait.forPageReady();
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_details.order_details_header"));
        Product p = null;
        for (int i = 0; i < orderContainers.size(); i++) {
            if (Elements.findElements("order_details.item_names").get(i).getText().contains("Tuxedo")) {
                p.reservation_id = Long.parseLong(Elements.findElement("order_details.tux_reservation_number").getText());
                p.quantity = Integer.parseInt(Elements.findElement("order_details.tux_quantity").getText());
            } else {
                Assert.fail("Tuxedo items are not displayed");
            }

        }

        return productDetails;
    }

    @And("^I verify order details in OD page for \"([^\"]*)\" order using mobile website$")
    public void I_verify_order_details_in_OD_page_for_order_using_mobile_website(String orderType) throws Throwable {
        pausePageHangWatchDog();
        Assert.assertFalse("ERROR - DATA : Order does not have order status information in order details page!!", Elements.findElements("order_details.header_status").isEmpty());
        Elements.elementShouldBePresent(Elements.findElement("order_details.header_status"));
        Assert.assertTrue("Header staus is not displaying", Elements.getText("order_details.header_status").equalsIgnoreCase(orderType));
        Assert.assertTrue("Header staus is not displaying", Elements.findElements("order_details.order_history").size() > 0);
        for (WebElement items : Elements.findElements("order_details.order_history")) {
            if (items.isDisplayed()){
                Elements.elementShouldBePresent(items.findElements(Elements.element("order_details.product_name")));
                Elements.elementShouldBePresent(items.findElements(Elements.element("order_details.product_size")));
                Elements.elementShouldBePresent(items.findElements(Elements.element("order_details.product_color")));
                Elements.elementShouldBePresent(items.findElements(Elements.element("order_details.product_qty")));
                Elements.elementShouldBePresent(items.findElements(Elements.element("order_details.product_price")));
                Elements.elementShouldBePresent(items.findElements(Elements.element("order_details.product_ship_status")));
            }
        }
        resumePageHangWatchDog();
    }

    @When("^I click on \"([^\"]*)\" button on footer using mobile website$")
    public void I_click_on_Become_a_loyallist_button_on_footer_using_mobile_website(String link) throws Throwable {
        shouldBeOnPage("home");
        switch (link) {
            case "Become a loyallist":
                Clicks.click("home.become_loyallist");
                break;
            case "Promotions":
                Clicks.click("home.promotions_link");
                break;
        }
    }

    @Then("^I navigate to loyallist enrollment page from become a loyallist page$")
    public void I_navigate_to_loyallist_enrollment_page_from_become_a_loyallist_page() throws Throwable {
        shouldBeOnPage("become_loyallist");
        Clicks.click("become_loyallist.create_account_enroll");
        shouldBeOnPage("loyalty_enrollment");
    }

    @Then("^I verify loaylty points and reward card information displayed on loyallist dashboard$")
    public void I_verify_loaylty_points_and_reward_card_information_displayed_on_loyallist_dashboard() throws Throwable {
        Assert.assertTrue("Loyallist reward cards summary is not displaying", Elements.elementPresent("loyallist_account_summary.reward_cards_summary"));
        Assert.assertTrue("Loyallist reward card balance is not displaying", Elements.elementPresent("loyallist_account_summary.reward_card_balance"));
        Assert.assertTrue("Loyallist available points are not displaying", Elements.elementPresent("loyallist_account_summary.available_points"));
        Assert.assertTrue("Loyallist required points are not displaying", Elements.elementPresent("loyallist_account_summary.required_points"));
        Assert.assertTrue("Loyallist pending points are not displaying", Elements.elementPresent("loyallist_account_summary.pending_points"));
    }

    @And("^I change my state from Signed In to Soft Signed In$")
    public void iChangeMyStateFromSignedInToSoftSignedIn() throws Throwable {
        // http://confluence5/pages/viewpage.action?spaceKey=CAPMD&title=Soft+Sign+In+Implementation
        Assert.assertTrue("ERROR: User is not in Signed In state", Cookies.editCookie("SignedIn", "1", "0"));
        Navigate.browserRefresh();
        Assert.assertEquals("ERROR: Unable to change state from Signed In to Soft Signed In", "0", Cookies.getCookieValue("SignedIn"));
    }

    @And("^I navigate to My Account mobile page$")
    public void iNavigateToMyAccountMobilePage(){
        Navigate.visit("my_account");
    }

    @Then("^I navigate to the MyAccount Profile page$")
    public void iNavigateToTheMyAccountProfilePage() throws Throwable {
        if (onPage("registry_home", "registry_manager")) {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
        } else {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
            closePopup();
        }
        GlobalNav.closeGlobalNav();
        CommonUtils.closeStylistPopup();
        Wait.untilElementPresent("home.profile_link");
        Clicks.click("home.profile_link");
    }


    @Then("^I should see the following fields on the Profile page:$")
    public void iShouldSeeTheFollowingFieldsOnTheProfilePage(DataTable table) throws Throwable {
        for (int i = 0; i < table.raw().size(); i++) {
            List<List<String>> dataLayer = table.raw();
            String type = dataLayer.get(i).get(1);
            String fieldName = dataLayer.get(i).get(0);
            String fieldDetail = dataLayer.get(i).get(2);
            switch(fieldName) {
                case "First name":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.first_name").getAttribute("type")));
                    break;
                case "Last name":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.last_name").getAttribute("type")));
                    break;
                case "Mailing address line 1":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_line_1").getAttribute("type")));
                    break;
                case "Mailing address line 2":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_line_2").getAttribute("type")));
                    break;
                case "City":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_city").getAttribute("type")));
                    break;
                case "State":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.state"));
                    break;
                case "Zip Code":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_zip_code").getAttribute("type")));
                    break;
                case "Gender":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.gender_select"));
                    break;
                case "Month":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.birth_month"));
                    break;
                case "Date":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.birth_day"));
                    break;
                case "Year":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.birth_year"));
                    break;
                case "Email":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.email").getAttribute("type")));
                    break;
                case "Verify Email":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.verify_email").getAttribute("type")));
                    break;
                case "Password":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.password").getAttribute("type")));
                    break;
                case "Verify password":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.verify_password").getAttribute("type")));
                    break;
                case "Security Question":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.security_question_select"));
                    break;
                case "Answer":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.security_answer").getAttribute("type")));
                    break;
            }
            logger.info("Profile field "+fieldName+ " validated" );
        }
    }

    @Then("^I should see an Update preferences link$")
    public void iShouldSeeAnUpdatePreferencesLink() throws Throwable {
        Wait.untilElementPresent("my_profile.profile_view_preferences");
        Assert.assertTrue("ERROR - Profile view preferences link is not visible", Elements.elementPresent("my_profile.profile_view_preferences"));
        logger.info("Profile view preferences link is visible");
    }

    @Then("^I should see the disclaimer text under the update profile button$")
    public void iShouldSeeTheDisclaimerTextUnderTheUpdateProfileButton(List<String> expectedElements) throws Throwable {
        List<WebElement> actualElements= new ArrayList();
        Wait.untilElementPresent("my_profile.profile_footer_links");
        actualElements=Elements.findElements("my_profile.profile_footer_links");
        List actualElementText= new ArrayList();
        for(WebElement e: actualElements)
        {
            actualElementText.add(e.getText());
        }
        if(expectedElements.size()==actualElementText.size()) {
            for (int i = 0; i < expectedElements.size(); i++)
            {
                String actual=actualElementText.get(i).toString();
                String expected=expectedElements.get(i).toString();
                Assert.assertTrue("Error -" +actual + " Profile footer links are not same", actual.contains(expected));
            }
            logger.info("Profile footer links text is  verified");
        }
        else
        {
            Assert.fail("Profile footer links are not same");
        }
    }

    @And("^I should see the legal notice links in the disclaimer$")
    public void iShouldSeeTheLegalNoticeLinksInTheDisclaimer(DataTable table) throws Throwable {
        List<WebElement> actualElements= new ArrayList();
        ArrayList<String> actualValues = new ArrayList();
        Wait.untilElementPresent("my_profile.profile_footer_links");
        actualElements=Elements.findElements("my_profile.profile_footer_links");
        for(WebElement e: actualElements)
        {
            actualValues.add(e.getAttribute("href"));
        }
        for (int i = 0; i < table.raw().size(); i++) {
            List<List<String>> dataLayer = table.raw();
            String name = dataLayer.get(i).get(0);
            String value = dataLayer.get(i).get(2);
            switch (name) {
                case "FAQs":
                    Assert.assertTrue("Error - "+name+" footer link href value is different",actualValues.get(i).equals(value));
                    break;
                case "About Security":
                    Assert.assertTrue("Error - "+name+" footer link href value is different",actualValues.get(i).equals(value));
                    break;
                case "About Privacy":
                    Assert.assertTrue("Error - "+name+" footer link href value is different",actualValues.get(i).equals(value));
                    break;
                case "About Credit Card Privacy":
                    Assert.assertTrue("Error - "+name+" footer link href value is different",actualValues.get(i).contains(value));
                    break;
            }
            logger.info("Profile footer links Href Attribute is verified");
    }
}

    @When("^I enter the following profile data$")
    public void iEnterTheFollowingProfileData(DataTable table) throws Throwable {
        UserProfile customer = TestUsers.getExistingRegistryUser();
        String email=TestUsers.generateRandomEmail(10);
        for (int i = 0; i < table.raw().size(); i++) {
            List<List<String>> dataLayer = table.raw();
            String fieldName = dataLayer.get(i).get(0);
            String type = dataLayer.get(i).get(1);
            String value = dataLayer.get(i).get(2);
            switch(fieldName)
            {
                case "First name":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.first_name").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.first_name",value);
                    customer.getUser().getProfileAddress().setFirstName(value);
                    break;
                case "Last name":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.last_name").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.last_name",value);
                    customer.getUser().getProfileAddress().setLastName(value);
                    break;
                case "Mailing address line 1":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_line_1").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.address_line_1",value);
                    customer.getUser().getProfileAddress().setAddressLine1(value);
                    break;
                case "Mailing address line 2":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_line_2").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.address_line_2",value);
                    customer.getUser().getProfileAddress().setAddressLine2(value);
                    break;
                case "City":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_city").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.address_city",value);
                    customer.getUser().getProfileAddress().setCity(value);
                    break;
                case "State":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.state"));
                    DropDowns.selectByText("my_profile.state",value);
                    customer.getUser().getProfileAddress().setState(value);
                    break;
                case "Zip Code":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.address_zip_code").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.address_zip_code",value);;
                    customer.getUser().getProfileAddress().setZipCode(value);
                    break;
                case "Gender":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.gender_select"));
                    DropDowns.selectByText("my_profile.gender_select",value);
                    customer.getUser().setGender(value);
                    break;
                case "Month":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.birth_month"));
                    DropDowns.selectByText("my_profile.birth_month",value);
                    customer.getUser().setDateOfBirth(value);
                    break;
                case "Date":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.birth_day"));
                    DropDowns.selectByText("my_profile.birth_day",value);
                    customer.getUser().getProfileAddress().setAttention(value);
                    break;
                case "Year":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.birth_year"));
                    DropDowns.selectByText("my_profile.birth_year",value);
                    customer.getUser().getProfileAddress().setProvince(value);
                    break;
                case "Email":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.email").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.email",email);
                    customer.getUser().getProfileAddress().setEmail(email);
                    logger.info("New Email id is "+email);
                    break;
                case "Verify Email":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.verify_email").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.verify_email",email);
                    break;
                case "Password":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.password").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.password",value);
                    TestUsers.currentPassword=value;
                    logger.info("New password is "+value);
                    break;
                case "Verify password":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.verify_password").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.verify_password",value);
                    break;
                case "Security Question":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, Elements.elementPresent("my_profile.security_question_select"));
                    DropDowns.selectByIndex("my_profile.security_question_select",Integer.parseInt(value));
                    break;
                case "Answer":
                    Assert.assertTrue("Field Name: " + fieldName + " type should be " + type, type.equals(Elements.findElement("my_profile.security_answer").getAttribute("type")));
                    TextBoxes.typeTextbox("my_profile.security_answer",value);
                    break;
            }
            logger.info("Profile data inserted successfully");
        }
    }

    @And("^I click the update preferences button$")
    public void iClickTheUpdatePreferencesButton() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("my_profile.update_profile_button");
        Clicks.click("my_profile.update_profile_button");
        logger.info("UPDATE button is clicked successfully");
    }

    @And("^I refresh the screen$")
    public void iRefreshTheScreen() throws Throwable {
       Navigate.browserRefresh();
        logger.info("Browser refreshed successfully");
    }

    @Then("^I should see the data is updated$")
    public void iShouldSeeTheDataIsUpdated() throws Throwable {
        UserProfile customer = TestUsers.getExistingRegistryUser();
        Assert.assertTrue("First name is mismatched",Elements.findElement("my_profile.first_name").getAttribute("value").equals(customer.getUser().getProfileAddress().getFirstName().toString()));
        Assert.assertTrue("Last name is mismatched",Elements.findElement("my_profile.last_name").getAttribute("value").equals(customer.getUser().getProfileAddress().getLastName().toString()));
        Assert.assertTrue("Mailing Address 1 is mismatched",Elements.findElement("my_profile.address_line_1").getAttribute("value").equals(customer.getUser().getProfileAddress().getAddressLine1().toString()));
        Assert.assertTrue("Mailing Address 2 is mismatched",Elements.findElement("my_profile.address_line_2").getAttribute("value").equals(customer.getUser().getProfileAddress().getAddressLine2().toString()));
        Assert.assertTrue("City is mismatched",Elements.findElement("my_profile.address_city").getAttribute("value").equals(customer.getUser().getProfileAddress().getCity().toString()));
        Assert.assertTrue("State is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.state")).equals(customer.getUser().getProfileAddress().getState().toString()));
        Assert.assertTrue("Zipcode is mismatched",Elements.findElement("my_profile.address_zip_code").getAttribute("value").equals(customer.getUser().getProfileAddress().getZipCode().toString()));
        Assert.assertTrue("Gender is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.gender_select")).equals(customer.getUser().getGender().toString()));
        Assert.assertTrue("Month is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.birth_month")).equals(customer.getUser().getDateOfBirth()));
        Assert.assertTrue("Day is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.birth_day")).equals(customer.getUser().getProfileAddress().getAttention()));
        Assert.assertTrue("Year is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.birth_year")).equals(customer.getUser().getProfileAddress().getProvince()));
        Assert.assertTrue("Email is mismatched",Elements.findElement("my_profile.email").getAttribute("value").equals(customer.getUser().getProfileAddress().getEmail().toString()));
        Assert.assertTrue("Verify Email is mismatched",Elements.findElement("my_profile.verify_email").getAttribute("value").equals(customer.getUser().getProfileAddress().getEmail().toString()));
        logger.info("Updated Profile data is verified successfully");
    }

    @And("^I should be able to login with the new email address and password$")
    public void iShouldBeAbleToLoginWithTheNewEmailAddressAndPassword() throws Throwable {
        I_sign_out_from_my_current_mobile_site_profile();
        if (MEW()) {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account or SIGN IN / SIGN UP");
            GlobalNav.closeGlobalNav();

        } else {
            String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
            Wait.untilElementPresent(elementName);
            Clicks.click(elementName);
        }
        if (macys()) {
            CommonUtils.closeIECertError();
        }
        Wait.untilElementPresent("sign_in.email");
        TextBoxes.typeTextbox("sign_in.email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("sign_in.password", TestUsers.currentPassword);
        Wait.untilElementPresent("sign_in.sign_in_button");
        Clicks.click("sign_in.sign_in_button");
        Utils.threadSleep(3000, "");
        Clicks.clickIfPresent("sign_in.close_overlay");
        resumePageHangWatchDog();
        shouldBeOnPage("my_account");
    }

    @Then("^I verify Update Profile Billing Information button is not visible$")
    public void iVerifyUpdateProfileBillingInformationButtonIsNotVisible() throws Throwable {
        Assert.assertTrue("Update Billing Information button should not exists", !Elements.elementPresent("my_profile.update_billing_info"));
        logger.info("Updated Profile data is verified successfully");
    }

    @Then("^I should see the following data is updated$")
    public void iShouldSeeTheFollowingDataIsUpdated(List<String> elementName) throws Throwable {
        UserProfile customer = TestUsers.getExistingRegistryUser();
        for(int i=0;i<elementName.size();i++)
        {
            String fieldName= elementName.get(i);
            switch(fieldName)
            {
                case "Month":
                    Assert.assertTrue("Month is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.birth_month")).equals(customer.getUser().getDateOfBirth()));
                    break;
                case "Date":
                    Assert.assertTrue("Day is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.birth_day")).equals(customer.getUser().getProfileAddress().getAttention()));
                    break;
                case "Year":
                    Assert.assertTrue("Year is mismatched",DropDowns.getSelectedValue(Elements.element("my_profile.birth_year")).equals(customer.getUser().getProfileAddress().getProvince()));
                    break;
            }

        }
        logger.info("Fields verified successfully");
    }

    @And("^I am on the footer secure-m sign in page$")
    public void iAmOnTheFooterSecureMSignInPage() throws Throwable {
        new Home().iVisitTheMobileWebHomePage();
        Wait.untilElementPresent("footer.goto_sign_in_link");
        Clicks.click("footer.goto_sign_in_link");
        Wait.untilElementPresent("sign_in.secure_sign_in_message");
        shouldBeOnPage("sign_in");
    }

    @And("^I sign in using profile which has macys credit card associated$")
    public void iSignInUsingProfileWhichHasMacysCreditCardAssociated() throws Throwable {
        TestUsers.currentEmail="mac102@gmail.com";
        TestUsers.currentPassword="password123";
    }

    @Then("^I should see secure-m sign in page$")
    public void iShouldSeeSecureMSignInPage() throws Throwable {
        Wait.untilElementPresent("sign_in.secure_sign_in_message");
        Assert.assertTrue("Error  - not on Secure sign in page", Elements.elementPresent("sign_in.secure_sign_in_message"));
    }

    @And("^I should see a password error message for the following usernames for secure-m$")
    public void iShouldSeeAPasswordErrorMessageForTheFollowingUsernamesForSecureM(DataTable table) throws Throwable {
        for (int i = 1; i < table.raw().size(); i++)
        {
            List<List<String>> dataLayer = table.raw();
            String userName = dataLayer.get(i).get(0);
            String errorMessage = dataLayer.get(i).get(1);
            Wait.untilElementPresent("sign_in.email");
            TextBoxes.typeTextbox("sign_in.email", userName);
            Wait.untilElementPresent("sign_in.password");
            Clicks.click("sign_in.password");
            Utils.threadSleep(1000,"Waiting for error message.");
            Assert.assertTrue("", Elements.getText("sign_in.error_message").equals(errorMessage));
        }
    }

    @And("^I should see a password error message for the following passwords for secure-m$")
    public void iShouldSeeAPasswordErrorMessageForTheFollowingPasswordsForSecureM(DataTable table) throws Throwable {
        for (int i = 1; i < table.raw().size(); i++)
        {
            List<List<String>> dataLayer = table.raw();
            String passowrd = dataLayer.get(i).get(0);
            String errorMessage = dataLayer.get(i).get(1);
            Wait.untilElementPresent("sign_in.password");
            TextBoxes.typeTextbox("sign_in.password", passowrd);
            Wait.untilElementPresent("sign_in.sign_in_button");
            Clicks.clickRandomElement("sign_in.secure_sign_in_message");
            Utils.threadSleep(1000,"Waiting for error message");
            Assert.assertTrue("", Elements.getText("sign_in.error_message").equals(errorMessage));
        }
    }

    @When("^I fail to login (\\d+) times$")
    public void iFailToLoginTimes(int number) throws Throwable {
        for(int i=1;i<=number;i++)
        {
            Wait.untilElementPresent("sign_in.email");
            TextBoxes.typeTextbox("sign_in.email", "jane@macys.com");
            Wait.untilElementPresent("sign_in.password");
            TextBoxes.typeTextbox("sign_in.password", TestUsers.generateRandomString(9));
            Clicks.click("sign_in.sign_in_button");
        }
    }

    @Then("^forgot password overlay is presented$")
    public void forgotPasswordOverlayIsPresented() throws Throwable {
        Wait.untilElementPresent("sign_in.error_container");
        Assert.assertTrue("Forgot password overlay should be present",Elements.elementPresent("sign_in.error_container"));
    }

    @When("^I login from modal with no profile$")
    public void iLoginFromModalWithNoProfile() throws Throwable {
        Wait.untilElementPresent("sign_in.email");
        TextBoxes.typeTextbox("sign_in.email", TestUsers.generateRandomEmail(10));
        TextBoxes.typeTextbox("sign_in.password", TestUsers.generateRandomString(10));
        Wait.untilElementPresent("sign_in.sign_in_button");
        Clicks.click("sign_in.sign_in_button");
    }

    @Then("^an alert for incorrect user with the message:$")
    public void anAlertForIncorrectUserWithTheMessage(List<String> errorMessage) throws Throwable {
        Wait.untilElementPresent("sign_in.error_container");
      Assert.assertTrue("Error message should be shown", Elements.getText("sign_in.error_container").contains(errorMessage.get(0)));
    }

    @When("^I touch secure-m \"([^\"]*)\" Button$")
    public void iTouchSecureMButton(String arg0) throws Throwable {
        Clicks.click("sign_in.forgot_your_password");
    }

    @Then("^I'm on the forgot password page$")
    public void iMOnTheForgotPasswordPage() throws Throwable {
        shouldBeOnPage("forgot_password");
    }

    @Then("^I should see sign in link is displayed in the footer section$")
    public void iShouldSeeSignInLinkIsDisplayedInTheFooterSection() throws Throwable {
        Assert.assertTrue("Sign In element should be present",Elements.elementPresent("footer.goto_sign_in_link"));
    }

    @Then("^login modal is opened with header sign in$")
    public void iShouldSeeloginModalMewSignInPage() throws Throwable {
        Wait.untilElementPresent("sign_in.sign_in_header");
        Assert.assertTrue("Error  - login in modal is not opened", Elements.elementPresent("sign_in.sign_in_header"));
    }

    @Then("^login modal page items are displayed:$")
    public void iShouldSeeLoginModalPageItems(List<String> expectedElements) throws Throwable {
        List<WebElement> actualElements= new ArrayList();
        Wait.untilElementPresent("sign_in.sign_in_header");
        logger.info("-> Verify all sections on sign in page!!");
        for(int i=0;i<expectedElements.size();i++)
        {
            switch(expectedElements.get(i).toLowerCase().trim()){
                case "email address":
                    Assert.assertTrue("Email address is not present on signin page", Elements.elementPresent("sign_in.email_address"));
                    Assert.assertTrue("email address is not present", Elements.getText("sign_in.email_address").equals(expectedElements.get(i).trim()));
                    break;

                case "password":
                    Assert.assertTrue("Password is not present on signin page", Elements.elementPresent("sign_in.password_label"));
                    Assert.assertTrue("Password is not present", Elements.getText("sign_in.password_label").split("\\n")[0].trim().equals(expectedElements.get(i).trim()));
                    break;

                case "passwords are case sensitive":
                    Assert.assertTrue("Password are case sensitive is not present on signin page", Elements.elementPresent("sign_in.password_label"));
                    logger.info(Elements.getText("sign_in.password_label"));
                    logger.info((Elements.getText("sign_in.password_label")).split("\\n")[1]);
                    Assert.assertTrue("Password are case sensitive is not present", Elements.getText("sign_in.password_label").split("\\n")[1].trim().equals(expectedElements.get(i).trim()));
                    break;

                case "sign in":
                    Assert.assertTrue("sign in button is not present on signin page", Elements.elementPresent("sign_in.sign_in_button"));
                    Assert.assertTrue("Sign in button is not present", Elements.getText("sign_in.sign_in_button").trim().equals(expectedElements.get(i).trim()));
                    break;

                case "forgot your password?":
                    Assert.assertTrue("forgot your password? link is not present on signin page", Elements.elementPresent("sign_in.forgot_password"));
                    Assert.assertTrue("forgot your password? link is not present", Elements.getText("sign_in.forgot_password").trim().equals(expectedElements.get(i).trim()));
                    break;

                case "create an account":
                    Assert.assertTrue("create an account button is not present on signin page", Elements.elementPresent("sign_in.create_account"));
                    Assert.assertTrue("create an account button is not present", Elements.getText("sign_in.create_account").trim().equals(expectedElements.get(i).trim()));
                    break;

                case "secure site. learn more":
                    Assert.assertTrue("Secure site. Learn More link is not present on signin page", Elements.elementPresent("sign_in.secure_text"));
                    Assert.assertTrue("Secure site. Learn More link is not present", Elements.getText("sign_in.secure_text").trim().equals(expectedElements.get(i).trim()));
                    break;

                case "privacy practices  ":
                    Assert.assertTrue("Privacy Practices link is not present on signin page", Elements.elementPresent("footer.privacy_practices"));
                    Assert.assertTrue("Privacy Practices  link is not present", Elements.getText("footer.privacy_practices").trim().equals(expectedElements.get(i).trim()));
                    break;
            }

        }

    }


    @Then("^modal messages appear for the following usernames$")
    public void modalMessagesAppearForTheFollowingUsernames(DataTable table) throws Throwable {
        for (int i = 1; i < table.raw().size(); i++)
        {
            List<List<String>> dataLayer = table.raw();
            String userName = dataLayer.get(i).get(0);
            String errorMessage = dataLayer.get(i).get(1);
            Wait.untilElementPresent("sign_in.email");
            TextBoxes.typeTextbox("sign_in.email", userName);
            Wait.untilElementPresent("sign_in.password");
            Clicks.click("sign_in.password");
            Utils.threadSleep(1000,"Waiting for error message.");
            Assert.assertTrue("", Elements.getText("sign_in.error_message").equals(errorMessage));
        }
    }

}
