package com.macys.sdt.shared.steps.website;


import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.framework.utils.db.models.UserService;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyallistAssociation;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyaltyEnrollment;
import com.macys.sdt.shared.actions.website.bcom.pages.my_account.MyAccountBCOM;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.*;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class MyAccountSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyAccountSteps.class);
    public boolean userProfileHasCheckoutEligibleAddress = false;
    private String simplePwd = "1234567";
    ProfileAddress addressObject = new ProfileAddress();
    ProfileAddress newAddressObject = new ProfileAddress();
    String uid;
    public static String newUslId;


    /**
     * Navigates to the my wallet page from the my account page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to My Wallet page from My Account page$")
    public void iNavigateToMyWalletPageFromMyAccountPage() throws Throwable {
        logger.info("Go to My Wallet page!!");
        if (!onPage("my_account")) {
            iNavigateToMyAccountPage();
        }
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            CreateProfile.closeSecurityAlertPopUp();
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
        }

        try {
            Wait.untilElementPresent("my_account.goto_my_wallet_link");
            Clicks.click("my_account.goto_my_wallet_link");
        } catch (NoSuchElementException ex) {
            /* By Davinder - 3/7
            try block will throw an exception in case of new responsive MyAccount. This try-catch block is temporary.
            Once responsive MyAccount is scaled 100%, try-catch block should be removed.
             */
            Clicks.click("new_my_account.wallet_payment_method");
        }

        if (bloomingdales() && Elements.elementPresent("my_account.new_offer_popup")) {
            Clicks.click("my_account.close_new_offer_popup");
        }
    }

    /**
     * Removes an offer from My Wallet if one is present
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I remove an offer from my wallet$")
    public void iRemoveAnOfferFromMyWallet() throws Throwable {
        if (Elements.elementPresent("oc_my_wallet.deleteOffers")) {
            Clicks.clickRandomElement("oc_my_wallet.deleteOffers");
            Clicks.click("oc_my_wallet.yes_delete_offer");
        } else {
            logger.info("No offers to delete");
        }
    }

    /**
     * Selects an offer on the My Wallet page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click on add offer on wallet page$")
    public void iClickOnAddOfferOnWalletPage() throws Throwable {
        Wait.untilElementPresent(((macys() ? "oc_my_wallet" : "my_bwallet") + ".add_offer_pass"));
        Clicks.click((macys() ? "oc_my_wallet" : "my_bwallet") + ".add_offer_pass");
    }

    /**
     * Adds a random offer from the promotions page to my wallet
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I provide an offer to my wallet$")
    public void iProvideAnOfferToMyWallet() throws Throwable {
        Clicks.click("my_account.goto_deals_promotions");
        if (Elements.elementPresent("my_offers.add_to_wallet")) {
            Clicks.click("my_offers.add_to_wallet");
        } else {
            logger.info("No offers available to add to wallet");
        }
    }

    /**
     * Adds a credit card to my wallet on my wallet page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add credit card to my wallet$")
    public void i_add_credit_card_to_my_wallet() throws Throwable {
        logger.info("Add credit card to wallet!!");
        if (macys()) {
            Wait.untilElementPresent("oc_my_wallet.add_credit_card");
            Clicks.click("oc_my_wallet.add_credit_card");
            Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
            CreditCard visa_card = TestUsers.getValidVisaCreditCard();
            String phoneNum = TestUsers.generateRandomPhoneNumber();
            DropDowns.selectByText("oc_my_wallet.credit_card_type", "Visa");
            TextBoxes.typeTextbox("oc_my_wallet.card_number", visa_card.getCardNumber());
            DropDowns.selectByText("oc_my_wallet.exp_month", visa_card.getExpiryMonthIndex() + " - " + visa_card.getExpiryMonth());
            DropDowns.selectByText("oc_my_wallet.exp_year", visa_card.getExpiryYear());
            Clicks.clickIfPresent("oc_my_wallet.use_my_shipping_address");
            TextBoxes.typeTextbox("oc_my_wallet.phone_area_code", phoneNum.substring(0, 3));
            TextBoxes.typeTextbox("oc_my_wallet.phone_ex_code", phoneNum.substring(3, 6));
            TextBoxes.typeTextbox("oc_my_wallet.phone_sub_code", phoneNum.substring(6));
            TextBoxes.typeTextbox("oc_my_wallet.credit_card_email", "test@macys.com");
            Clicks.click("oc_my_wallet.save_card");
            Wait.forPageReady();
        } else {
            CreditCard visaCreditCard = TestUsers.getValidVisaCreditCard();
            MyWallet.addCard(visaCreditCard);
            Wait.forPageReady();
        }
    }

    /**
     * Navigates to the my account page
     */
    @When("^I navigate to my account page$")
    public void iNavigateToMyAccountPage() {
        Utils.threadSleep(4000, null);
        logger.info("Go to My Account page!!");
        pausePageHangWatchDog();
        Wait.secondsUntilElementPresent("home.goto_my_account_link", 10);
        if (!onPage("my_account") || Elements.elementPresent("my_account.add_card_overlay_no_thanks_button")) {
            if (ie()) {
                Clicks.click(Elements.element("home.goto_my_account_link"),
                        () -> Wait.untilElementPresentWithRefreshAndClick(
                                Elements.element("my_account.csr_add_card_to_my_account_button"),
                                Elements.element("home.goto_my_account_link")));

            } else {
                if (StepUtils.safari()) {
                    Utils.threadSleep(3000, null);
                }
                Clicks.click("home.goto_my_account_link");
                //yc03673 2017-06-08 For macys added code to click on My Account link after expanding the My Account list
                if (macys()) Clicks.clickIfPresent("header.goto_my_account_second_link");
                Wait.forPageReady();
            }
            if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
                CreateProfile.closeSecurityAlertPopUp();
                Clicks.click("my_account.add_card_overlay_no_thanks_button");
                Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
            }
            if (StepUtils.safari()) {
                Utils.threadSleep(3000, null);
            }
            shouldBeOnPage(signedIn() ? "my_account" : "sign_in");
        }
        resumePageHangWatchDog();
    }

    /**
     * Navigates to the my profile page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to my profile(?: page)?$")
    public void iNavigateToMyProfilePage() throws Throwable {
        logger.info("-> Navigation to profile page!");
        if (macys()) {
            Clicks.hoverForSelection(Elements.findElement("header.goto_my_account_link"));
            Wait.secondsUntilElementPresentAndClick("header.goto_myprofile", 10);
            Wait.secondsUntilElementPresent("my_profile.verify_page", 20);
        } else {
            Elements.elementPresent("my_account.goto_my_profile");
            Clicks.click(("my_account.goto_my_profile"));
        }
        if (!onPage("my_profile")) {
            Assert.fail("Not navigated to the my profile page");
        }
        }

    /**
     * Signs in to existing profile or creates a new one
     */
    @When("^I sign in to my existing profile$")
    public void iSignInToMyExistingProfile() {
        CommonUtils.signInOrCreateAccount();
        iNavigateToMyAccountPage();
    }

    @And("^I create a profile$")
    public void newProfile() throws Throwable {
        logger.info("Fill data on profile creation page to create new profile");
        TestUsers.clearCustomer();
        CreateProfile.createProfile(TestUsers.getCustomer(null));
        Wait.forPageReady();
        CreateProfile.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        logger.info("email=" + TestUsers.currentEmail);
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        logger.info("Password=" + TestUsers.currentPassword);
    }

    @When("^I sign in to my profile$")
    public void iSignInToMyProfile() throws Throwable {
        CommonUtils.signIn();
        Thread.sleep(2000);
    }

    /**
     * Creates a new profile
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new profile$")
    public void iCreateANewProfile() throws Throwable {
        /*if (prodEnv()) {
            throw new ProductionException("Cannot create profiles in production");
        }*/

        logger.info("Fill data on profile creation page to create new profile");
        TestUsers.clearCustomer();
        CreateProfile.createProfile(TestUsers.getCustomer(null));
        if (!prodEnv() && !onPage("my_account")) {
            Assert.fail("New Profile is not created");
        }
        Wait.forPageReady();
        CreateProfile.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        logger.info("email=" + TestUsers.currentEmail);
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        logger.info("Password=" + TestUsers.currentPassword);
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            CreateProfile.closeSecurityAlertPopUp();
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
        }
        CreateProfile.closeSecurityAlertPopUp();
        Utils.threadSleep(9000, null);
    }

    /**
     * Creates a new profile with a simple password (1234567)
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new profile with simple password$")
    public void createNewProfileWithSimplePwd() throws Throwable {
        /*if (prodEnv()) {
            throw new ProductionException("Cannot create profiles in production");
        }*/
        TestUsers.clearCustomer();

        UserProfile customer = TestUsers.getCustomer(null);
        User user = customer.getUser();
        user.getLoginCredentials().setPassword(simplePwd);
        CreateProfile.createProfile(customer);
        Wait.forPageReady();
    }

    /**
     * Creates a profile with an age under 13 years old
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I create a new profile and my age is less that 13 years$")
    public void createNewProfileWithAgeLessThanThirteen() throws Throwable {
        logger.info("Create profile with less than 13 years of age!!");
        String targetDOB = "-12-31";//signify Dec 31st.
        int yearsToDeduct = 13;
       /* if (prodEnv()) {
            throw new ProductionException("Cannot create profiles in production");
        }*/
        TestUsers.clearCustomer();

        UserProfile customer = TestUsers.getCustomer(null);
        User user = customer.getUser();

        //From current year, if we subtract yearsToDeduct, we will get target year.
        String targetYear = String.valueOf((Calendar.getInstance().get(Calendar.YEAR)) - yearsToDeduct);
        targetDOB = targetYear + targetDOB;
        user.setDateOfBirth(targetDOB);
        CreateProfile.createProfile(customer);
        Wait.forPageReady();
    }

    /**
     * Creates a profile with missing name, email, and phone number data
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I create a new profile with missing first_name, last_name, email and primary_phone_number$")
    public void createNewProfileWithMissingData() throws Throwable {
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        User user = customer.getUser();
        ProfileAddress profileAddress = user.getProfileAddress();
        profileAddress.setFirstName("");
        profileAddress.setLastName("");
        profileAddress.setEmail("");
        /*
        Parameters:
        1. false - suggests to use valid dob.
        2. true - suggests to input EMPTY phone field so as to validate phone inline error msg.
         */
        CreateProfile.createProfile(customer, false, true);
        Wait.forPageReady();
    }

    /**
     * Attempts to create a profile with invalid data
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I try to create a new account with invalid data$")
    public void createNewProfileWithInvalidData() throws Throwable {
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        User user = customer.getUser();
        ProfileAddress profileAddress = user.getProfileAddress();
        profileAddress.setFirstName("10");
        profileAddress.setLastName("20");
        profileAddress.setEmail("davsin@gmailcom");
        /*
        Parameters:
        1. true  - suggests to use invalid dob.
        2. false - suggests not to input EMPTY phone field.
        3. true  - suggests not to input invalid/incomplete phone field.
         */
        //true parameter in below method suggests entering invalid date while creating new profile.
        CreateProfile.createProfile(customer, true, false, true);
        Wait.forPageReady();
    }

    /**
     * Attempts to create an account with the same digit for the entire phone number
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I create a new account with all same digits for phone$")
    public void createNewProfileWithAllSameDigitsForPhone() throws Throwable {

        /*if (prodEnv()) {
            throw new exceptions.ProductionException("Cannot create profiles in production");
        }*/
        logger.info("Create new profile with phone having same digits!!");
        TestUsers.clearCustomer();

        UserProfile customer = TestUsers.getCustomer(null);
        /*
        Parameters:
        1. true  - suggests to use invalid dob.
        2. false - suggests not to input EMPTY phone field.
        3. false - suggests not to input invalid/incomplete phone field.
        4. true  - suggests to input all same digits for phone.
         */
        CreateProfile.createProfile(customer, false, false, false, true);
        Wait.forPageReady();
    }

    /**
     * Navigates to the my plenti page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to my plenti page$")
    public void iNavigateToMyPlentiPage() throws Throwable {
        newUslId = Elements.getText("usl_confirmation.usl_id");
        Clicks.hoverForSelection("my_account.goto_my_account");
        Wait.untilElementPresent("my_account.goto_my_plenti");
        Clicks.click("my_account.goto_my_plenti");
        CreateProfile.closeSecurityAlertPopUp();
    }

    /**
     * Click the join plenti button on macys USL page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on the 'Join For Free' button$")
    public void iClickOnTheJoinForFreeButton() throws Throwable {
        Wait.untilElementPresent("usl_home.plenti_index_content");
        Clicks.click("usl_home.enroll_today_button");
    }

    /**
     * Clicks the join plenti button on plenti page (external)
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click again on the 'Join For Free' button$")
    public void iClickAgainOnTheJoinForFreeButton() throws Throwable {
        Assert.assertTrue("Unable to click on \"JOIN FOR FREE\" button", Clicks.clickWhenPresent("usl_home.join_free"));
    }

    /**
     * Clicks the "join now" button on plenti page (external)
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click on the 'Join Now' button$")
    public void iClickOnTheJoinNowButton() throws Throwable {
        Wait.untilElementPresent("usl_join_for_free.join_now");
        if (ie()) {
            Clicks.click(Elements.element("usl_join_for_free.join_now"),
                    () -> Wait.untilElementPresentWithRefreshAndClick(
                            Elements.element("usl_enrollment.continue_button"),
                            Elements.element("usl_join_for_free.join_now")));
        } else {
            if (firefox()) {
                Clicks.javascriptClick("usl_join_for_free.join_now");
            } else {
                Clicks.click("usl_join_for_free.join_now");
            }
        }
    }

    /**
     * Directly visits the plenti (external) enrollment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I goto plenty enroll page directly")
    public void iGotoPlentyEnrollPageDirectly() throws Throwable {
        Navigate.visit("usl_enrollment");
    }

    /**
     * Clicks the cancel button during plenti enrollment (external)
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on the 'Cancel' button$")
    public void iClickOnTheCancelButton() throws Throwable {
        Wait.untilElementPresent("usl_enrollment.continue_button");
        Clicks.click("usl_enrollment.cancel_button");
    }

    /**
     * Confirms the cancel on the plenti enrollment (external)
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on the 'YES, CANCEL' button$")
    public void iClickOnTheYESCANCELButton() throws Throwable {
        Wait.untilElementPresent("usl_cancel_dialog.cancel_dialog");
        Clicks.click("usl_cancel_dialog.yes_cancel_button");
    }

    /**
     * Adds phone number during plenti enrollment (external)
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I enter the 'Phone number'$")
    public void iEnterThePhoneNumber() throws Throwable {
        Wait.untilElementPresent("usl_enrollment.continue_button");
        String phone_no = TestUsers.generateRandomPhoneNumber();
        try {
            TextBoxes.typeTextbox("usl_enrollment.phone_number", phone_no);
            Clicks.click("usl_enrollment.continue_button");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Cannot continue:" + e);
        }
    }

    /**
     * Opts for guest user enrollment on USL page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I opt for guest user enrollment from USL sign in page$")
    public void iOptForGuestUserEnrollmentFromUSLSignInPage() throws Throwable {
        try {
            Clicks.click(Elements.findElement(By.className("fsrCloseBtn")));
        } catch (NoSuchElementException e) {
            //ignore exception
        }
        USLEnrollment.enroll(TestUsers.getuslCustomer(null));
    }

    /**
     * Signs out from currently signed in profile
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I sign out from my current profile$")
    public void iSignOutFromMyCurrentProfile() throws Throwable {
        if(onPage("registry_manager")&&bloomingdales()){
            Wait.secondsUntilElementPresent("registry_manager.goto_my_account_link", 20);
            Clicks.hoverForSelection("registry_manager.goto_my_account_link");
            Wait.secondsUntilElementPresent("registry_manager.goto_sign_out_link", 20);
            Clicks.click("registry_manager.goto_sign_out_link");
            Wait.forPageReady();
        }
        else {
            if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
                Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
            }
            Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
            if (bloomingdales()) {
                logger.info("-> Signout from current  profile!!");
                Clicks.clickIfPresent(By.className("container-close"));
                Wait.forPageReady();
                Clicks.hoverForSelection("header.goto_my_account_link");
            }
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("header.goto_sign_out_link", 20);
            Clicks.click("header.goto_sign_out_link");
            if (safari()) {
                Wait.secondsUntilElementNotPresent("header.goto_sign_out_link", 10);
            }

            closeBcomPopup();
        }

        logger.info("Checked SignedIn cookie should be set to 0 for  user not signed in");
        Assert.assertEquals("SignedIn cookie value is not 0 for user not signed in", "0", Cookies.getCookieValue("SignedIn"));

    }

    /**
     * Navigates to loyalty enrollment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to the loyalty enrollment page$")
    public void iNavigateToTheLoyaltyEnrollmentPage() throws Throwable {
        pausePageHangWatchDog();
        Clicks.click("loyalty_home.create_profile_enroll_button");
        shouldBeOnPage("loyalty_enrollment");
        resumePageHangWatchDog();
    }

    /**
     * Navigates to the loyalty page
     *
     * @param user_type signed in or guest
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the loyalty landing page as a \"([^\"]*)\" user$")
    public void iNavigateToTheLoyaltyLandingPageAsAUser(String user_type) throws Throwable {
        // Before landing to the Loyalty enrollment page check whether the loyalty account already associated to the signed in account
        if (signedIn() && Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            logger.info("--> User is already enrolled in Loyalty!!");
            // No need to remove and create Loyalty, instead we can use the same.
            //            click("my_account.view_my_loyalllist_account");
            //            forPageReady();
            //            shouldBeOnPage("loyallist_account_summary");
            //            click("loyallist_account_summary.remove_button");
            //            untilElementPresent("loyallist_account_summary.lty_account_panel");
            //            click("loyallist_account_summary.remove_confirmation_btn");
            //            shouldBeOnPage("loyalty_association");
        } else {
            Clicks.click("home.goto_loyallist");
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
     * Navigates to the loyalist account association page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the loyallist account association page$")
    public void iNavigateToTheLoyallistAccountAssociationPage() throws Throwable {
        // Before landing to the Loyalty association page check whether the loyalty account already associated to the signed in account
        if (Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            Clicks.click("my_account.view_my_loyalllist_account");
            shouldBeOnPage("loyallist_account_summary");
            Clicks.click("loyallist_account_summary.remove_button");
            Wait.untilElementPresent("loyallist_account_summary.lty_account_panel");
            Clicks.click("loyallist_account_summary.remove_confirmation_btn");
        } else {
            if (Elements.elementPresent("my_account.goto_my_loyallist"))
                Clicks.click("my_account.goto_my_loyallist");
            else if(Elements.elementPresent("my_account.myaccount_add_loyalty_link"))
                Clicks.click("my_account.myaccount_add_loyalty_link");
        }
        Wait.untilElementPresent("loyalty_association.verify_page");
        shouldBeOnPage("loyalty_association");
    }

    /**
     * Adds a usl ID to current profile on my account page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add fully_enrolled_usl id on my account page$")
    public void iAddFullyEnrolledUslIdOnMyAccountPage() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("iAddFullyEnrolledUslIdOnMyAccountPage()");
        }
        pausePageHangWatchDog();
        String plenti_id = TestUsers.getEnrolledUslId().getPlentiId();
        Clicks.click("my_account.plenti_lookup_tab");
        UserService.removeUslIdFromAllUsers(plenti_id);
        Clicks.click("my_account.plenti_number_link");
        TextBoxes.typeTextbox("my_account.plenti_number", plenti_id);
        if (safari()) {
            Clicks.javascriptClick("my_account.add_plenti_account");
        } else {
            Clicks.click("my_account.add_plenti_account");
        }
        Assert.assertFalse("ERROR - ENV : Unable to look up Plenti ID!!", Wait.untilElementPresent("my_account.error_message"));
        Assert.assertTrue("ERROR - APP : Added USL ID is not displayed in my account!!", Wait.secondsUntilElementPresent("my_account.go_to_my_XXXXXX", (safari() ? 20 : 5)));
        resumePageHangWatchDog();
    }

    /**
     * Removes USL ID on checkout shipping and payment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I remove USL ID from shipping and payment page$")
    public void iRemoveUSLIDFromShippingAndPaymentPage() throws Throwable {
        Wait.untilElementPresent("shipping_payment_signed_in.remove_usl_button");
        if (ie()) {
            Clicks.click(Elements.element("shipping_payment_signed_in.remove_usl_button"),
                    () -> Wait.untilElementPresentWithRefreshAndClick(
                            Elements.element("shipping_payment_signed_in.apply_usl_id_button"),
                            Elements.element("shipping_payment_signed_in.remove_usl_button")));
        } else {
            Clicks.click("shipping_payment_signed_in.remove_usl_button");
        }
        Wait.untilElementPresent("shipping_payment_signed_in.apply_usl_id_button");
    }

    /**
     * Verifies a loyallist number can be associated with a user account
     *
     * @param loyallist_type type of loyallist ID to use from "loyalty.json" data file
     * @throws Throwable if any exception occurs
     */
    @And("^I should be able to associate my account by loyallist number using \"([^\"]*)\" details$")
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
     * @param user_type guest or signed in
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be able to enroll in to the loyalty program as a \"([^\"]*)\" user$")
    public void iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser(String user_type) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser()");
        }
        String pageName;
        if (signedIn() && Elements.elementPresent("my_account.view_my_loyalllist_account")) {
            Clicks.click("my_account.view_my_loyalllist_account");
            Wait.forPageReady();
            logger.info("--> User is already enrolled in Loyalty, navigating to loyalty_enrollment_confirmation!!");
            pageName = "loyallist_account_summary";
        } else {
            LoyaltyEnrollment enrollmentPage = new LoyaltyEnrollment();
            switch (user_type.toLowerCase()) {
                case "guest":
                    Clicks.click("header.footer_loyallist");
                    Clicks.click("loyalty_home.create_profile_enroll_button");
                    enrollmentPage.guestUserLoyaltyEnrollment(TestUsers.getCustomer(null));
                    break;
                case "signed_in":
                    enrollmentPage.signedInUserLoyaltyEnrollment(TestUsers.getCustomer(null));
                    break;
            }
            pageName = "loyalty_enrollment_confirmation";
        }
        if (edge()) {
            Wait.secondsUntilElementPresent(By.className("extole-js-widget-close-button"), 5);
            Clicks.javascriptClick(By.className("extole-js-widget-close-button"));
        }
        Wait.untilElementPresent(pageName + ".loyalty_number");
        if (!Elements.elementPresent(pageName + ".loyalty_number")) {
            Assert.fail("Loyalty Enrollment Confirmation Page Not Loaded Properly");
        } else {
            logger.info("Loyalty Enrollment Confirmation Page Loaded Successfully!!!");
        }
    }

    /**
     * Verifies the display of the my account pages
     *
     * @param pageNames pages to verify (in page_name format)
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the My Account Pages are rendered properly$")
    public void iVerifyTheMyAccountPagesAreRenderedProperly(List<String> pageNames) throws Throwable {
        logger.info("-> Verify left navigation links on my account page!!");
        MyAccount wmp = new MyAccount();
        for (String pageName : pageNames) {
            if (!macys() && pageName.matches("gift card balance")) continue;
            wmp.navigateToLeftNavigationPage(pageName);
            if (!macys() && pageName.matches("My Bloomingdale's Credit Card")) continue;
            if (!macys() && pageName.matches("My Points")) continue;
            if (!macys() && pageName.matches("FAQs")){ Navigate.browserBack(); continue;}
            if (!macys() && pageName.matches("Wish List")){ Navigate.browserBack(); continue;}
            if(bloomingdales()) {
                if (!Elements.findElements("navigation.lightbox_popup").isEmpty() && Elements.anyPresent("navigation.lightbox_popup"))
                    Clicks.click("navigation.goto_my_profile");
            }
            if (!wmp.navigatedToExpectedPage(pageName)) {
                Assert.fail("Not navigated to the " + pageName);
            }
        }
    }

    /**
     * Verifies that login was successful
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see user logged in to account successfully$")
    public void iShouldSeeUserLoggedInToAccountSuccessfully() throws Throwable {
        logger.info("-> Verify user is logged in to account!!");
        logger.info("Checked SignedIn cookie should be set to 1 for signed in user");
        Assert.assertEquals("SignedIn cookie value is not 1 for signed user", "1", Cookies.getCookieValue("SignedIn"));
        if (!signedIn()) {
            Assert.fail("User is not logged into to the profile!!");
        }
    }

    /**
     * Updates profile with random new details
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I update profile details on my profile$")
    public void iUpdateAndOnMyProfile() throws Throwable {
        logger.info("-> update user profile!!");
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        TextBoxes.typeTextbox("my_profile.verify_page", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("my_profile.last_name", customer.getUser().getProfileAddress().getLastName());
        if (Elements.getText("my_profile.address_line_1").isEmpty()) {
            User user = customer.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();
            TextBoxes.typeTextbox("my_profile.address_line_1", profileAddress.getAddressLine1());
            TextBoxes.typeTextbox("my_profile.address_city", profileAddress.getCity());
            if (macys() || MEW()) {
                DropDowns.selectByText("my_profile.address_state", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
            } else {
                DropDowns.selectByText("create_profile.address_state_list", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
                //DropDowns.selectCustomText("create_profile.address_state_list", "create_profile.state_options", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
                Clicks.clickIfPresent("my_profile.gender_female");
            }
            TextBoxes.typeTextbox("my_profile.address_zip_code", String.valueOf(profileAddress.getZipCode()));
            if (macys()) {
                DropDowns.selectByText("my_profile.gender", user.getGender());
                DropDowns.selectByText("create_profile.security_question", user.getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("create_profile.security_answer", user.getUserPasswordHint().getAnswer());
            }
        }
        if (edge()) {
            Clicks.javascriptClick("my_profile.update_profile_button");
        } else {
            //Clicks.javascriptClick("my_profile.update_profile_button");
            Clicks.click("my_profile.update_profile_button");

        }
        if (macys()) {
            Assert.assertTrue("Profile not updated", Wait.untilElementPresent("my_profile.update_message"));
        } else {
            Assert.assertTrue("Could not update profile", Wait.until(() -> onPage("my_account"), 10));
        }
    }

    /**
     * Verifies that profile details have changed
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify my profile is updated$")
    public void iVerifyMyProfileIsUpdated() throws Throwable {
        logger.info("-> Verify profile is updated!!");
        if (bloomingdales()) {
            Clicks.click("my_account.goto_my_profile");
        }
        UserProfile customer = TestUsers.getCustomer(null);
        String capturedFirstName = customer.getUser().getProfileAddress().getFirstName();
        String capturedLastName = customer.getUser().getProfileAddress().getLastName();
        try {
            String updatedFirstName = Elements.getElementAttribute("my_profile.verify_page", "value");
            String updatedLastName = Elements.getElementAttribute("my_profile.last_name", "value");
            if (!(capturedFirstName.equals(updatedFirstName) && capturedLastName.equals(updatedLastName))) {
                Assert.fail("Profile is not Updated");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Cannot continue:" + e);
        }
    }

    /**
     * Clicks on the "get started" button on usl page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on the 'Get Started' button$")
    public void iClickOnGetStartedButton() throws Throwable {
        if (!signedIn()) {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("usl_sign_in.goto_create_profile", 30);
            Clicks.click("usl_sign_in.goto_create_profile");
        }
    }

    /**
     * Enrolls current user in usl program
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I enroll into the USL program from loyalty home page$")
    public void iEnrollIntoTheUSLProgramFromLoyaltyHomePage() throws Throwable {
        // Now we have new USL home page in qa environment which is pointing to production, So we are directly visit USL sign in page instead of USL home.
        //        iClickOnTheJoinForFreeButton();
        //        iClickOnTheJoinNowButton();
        if (!signedIn()) {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("usl_sign_in.goto_create_profile", 30);
            Clicks.click("usl_sign_in.goto_create_profile");
        }
        TestUsers.clearCustomer();
        pausePageHangWatchDog();
        USLEnrollment.enroll(TestUsers.getuslCustomer(null));
        USLEnrollment.enrollStep1(TestUsers.getuslCustomer(null));
        USLEnrollment.completeEnrollment();
        USLEnrollment.linkCreditCardAndSetPreferences();
        Wait.forPageReady();
        resumePageHangWatchDog();
        shouldBeOnPage("usl_confirmation");
    }

    /**
     * Verifies that USL enrollment confirmation page is showing
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see USL enrollment confirmation page$")
    public void iShouldSeeUSLEnrollmentConfirmationPage() throws Throwable {
        if (!onPage("usl_confirmation")) {
            Assert.fail("Not navigated to USL enrollment confirmation page");
        }
    }

    /**
     * Verifies USL enrollment confirmation page is displayed correctly
     *
     * @param elements elements that should be present
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see attributes on USL enrollment confirmation page:$")
    public void iShouldSeeAttributesOnUSLEnrollmentConfirmationPage(List<String> elements) throws Throwable {
        for (String el : elements) {
            el = "usl_confirmation." + el;
            if (!Elements.elementPresent(el)) {
                Assert.fail(el + "attribute is not displayed on USL enrollment confirmation page");
            }
        }
        int lengthOfUslId = Elements.getText("usl_confirmation.usl_id").length();
        if (lengthOfUslId != 16) {
            Assert.fail("Length of USL ID on USL enrollment confirmation page is not equal to 16");
        }
    }

    /**
     * Navigates to USL account summary page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to USL account summary page$")
    public void iNavigateToUSLAccountSummaryPage() throws Throwable {
        Clicks.click("my_account.goto_my_plenti");
        if (Wait.secondsUntilElementPresent("usl_account_summary.verify_page", (safari() ? 20 : 5))) {
            Navigate.browserRefresh();
        }
        Wait.forPageReady();
        shouldBeOnPage("usl_account_summary");
    }

    /**
     * Verifies USL account attributes on USL account summary page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see USL basic attributes on USL account summary page$")
    public void iShouldSeeUSLBasicAttributesOnUSLAccountSummaryPage() throws Throwable {
        USLAccountSummary.verifyUSLAccountSummaryPageInformation("fully enrolled", null);
    }

    /**
     * Verifies that credit card section does not appear on create profile page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should not see credit card section on create profile page$")
    public void iShouldNotSeeCreditCardSectionOnCreateProfilePage() throws Throwable {
        if (Elements.elementPresent("create_profile.add_card_number") && Elements.elementPresent("create_profile.ssn_number")) {
            Assert.fail("Credit card section is displayed on create profile page!!");
        }
    }

    /**
     * Creates a new profile and DOES NOT close the add credit card dialog
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new profile without closing the add card overlay$")
    public void iCreateANewProfileWithoutClosingTheAddCardOverlay() throws Throwable {
        TestUsers.clearCustomer();
        Clicks.click("home.goto_my_account_link");
        if (safari()) {
            Wait.secondsUntilElementPresent("sign_in.create_profile", 10);
        }
        Clicks.click("sign_in.create_profile");
        CreateProfile.createProfile(TestUsers.getCustomer(null));
        if (safari()) {
            Wait.secondsUntilElementPresent("my_account.verify_page", 10);
        }
        if (!prodEnv() && !onPage("my_account")) {
            Assert.fail("New Profile is not created");
        }
        Wait.forPageReady();
        CreateProfile.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            CreateProfile.closeSecurityAlertPopUp();
        }
        CreateProfile.closeSecurityAlertPopUp();
        Utils.threadSleep(9000, null);
    }

    /**
     * Verifies that the add credit card dialog is showing or not
     *
     * @param condition should or should not
     * @throws Throwable if any exception occurs
     */
    @Then("^I (should|should not) see one time add card overlay and its components$")
    public void iShouldSeeOneTimeAddCardOverlayAndItsComponents(String condition) throws Throwable {
        String add_card_elements[] = {"one_time_add_card_overlay", "add_card_overlay_add_card_button", "add_card_overlay_close_button", "add_card_overlay_apply_today_link"};
        pausePageHangWatchDog();
        Wait.secondsUntilElementPresent("my_account.one_time_add_card_overlay", 5);
        if (condition.equals("should")) {
            for (String element : add_card_elements)
                Assert.assertTrue(element + " element is not displayed on add_card_overlay!!", Elements.elementPresent("my_account." + element));
        } else {
            Assert.assertFalse("Add credit card overlay is displayed on my account page!!", Elements.elementPresent("my_account.one_time_add_card_overlay"));
        }

        resumePageHangWatchDog();
    }

    /**
     * Selects the given element on add credit card overlay
     *
     * @param element_name identifier of element to click (from my_account json file)
     * @throws Throwable if any exception occurs
     */
    @When("^I select \"([^\"]*)\" on add credit card overlay$")
    public void iSelectFieldOnAddCreditCardOverlay(String element_name) throws Throwable {
        pausePageHangWatchDog();
        Wait.untilElementPresent("my_account." + element_name);
        Clicks.click("my_account." + element_name);
        resumePageHangWatchDog();
    }

    /**
     * Verifies that you are on given page
     *
     * @param expected_page name of expected page in json page_name format
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to \"([^\"]*)\" page$")
    public void iShouldBeRedirectedToExpectedPage(String expected_page) throws Throwable {
        Wait.secondsUntilElementPresent(expected_page + ".verify_page", 10);
        if (!onPage(expected_page)) {
            Assert.fail("User is not redirected to " + expected_page + " page");
        }
    }

    /**
     * Verifies that the given elements are displayed on credit services gateway page
     *
     * @param elements elements that should be present
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see below fields on credit service gateway page:$")
    public void iShouldSeeBelowFieldsOnCreditServiceGatewayPage(List<String> elements) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        if (macys()) {
            Clicks.click(page + ".other_ways_to_pay_link");
            Clicks.click(page + ".other_ways_to_pay_link");
            if (macys()) {
                Wait.secondsUntilElementPresent(page + ".closeButton_otherway", 15);
                Clicks.click(page + ".closeButton_otherway");
                Wait.secondsUntilElementPresent(page + ".plus_Button", 15);
                Clicks.click(page + ".plus_Button");
            } else {
                Clicks.click(page + ".other_ways_to_pay_link");
            }
            Assert.assertFalse("ERROR - ENV : CITI services are down!!", Elements.elementPresent(By.className("infoMessages")));
            elements.forEach(element ->
                    Assert.assertTrue(element + " is not displayed on credit service gateway guest page",
                            Elements.elementPresent(page + "." + element)));
        }
    }

    /**
     * Verifies that the given links are present in the credit footer
     *
     * @param credit_footer_links links to check for (link text)
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see below footer credit links:$")
    public void iShouldSeeBelowFooterCreditLinks(List<HashMap<String, String>> credit_footer_links) throws Throwable {
        ArrayList<String> failed_elements = new ArrayList<>();
        for (Map set : credit_footer_links) {
            try {
                if (!Elements.elementPresent("home." + set.get("credit_link"))) {
                    failed_elements.add("home." + set.get("credit_link"));
                }
            } catch (NoSuchElementException e) {
                logger.info(set.get("credit_link").toString() + " element not present!!");
            }
        }
        if (failed_elements.size() > 0) {
            Assert.fail("Following Elements are not displayed in footer section:" + failed_elements.toString() + "!!");
        }
    }

    /**
     * Verifies the user is navigated to the correct page when clicking on the given footer links
     *
     * @param credit_footer_links links to click on (link text)
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to below respective credit services pages:$")
    public void iShouldBeNavigatedToBelowRespectiveCreditServicesPages(List<HashMap<String, String>> credit_footer_links) throws Throwable {
        ArrayList<String> failed_elements = new ArrayList<>();
        for (Map set : credit_footer_links) {
            Clicks.click("home." + set.get("credit_link"));
            if (Elements.getValues(set.get("landing_page").toString() + ".verify_page").isEmpty()) {
                Wait.secondsUntilElementPresent(set.get("landing_page").toString() + ".verify_page", 20);
            } else {
                Utils.threadSleep(5000, "Waiting for page (without verify_page element) to load..");
            }
            try {
                if (!onPage(set.get("landing_page").toString())) {
                    failed_elements.add(set.get("landing_page").toString());
                }
            } catch (NoSuchElementException e) {
                logger.info(set.get("landing_page") + " page not displayed!!");
            } finally {
                Navigate.browserBack();
                Wait.secondsUntilElementPresent("footer" + ".goto_customer_service", 20);
            }
        }
        if (failed_elements.size() > 0) {
            Assert.fail("Following pages are not displayed:" + failed_elements.toString() + "!!");
        }
    }

    /**
     * Verifies that the given citi pages are reachable from the citi gateway page
     *
     * @param citiPages pages that should be reachable
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to below citi pages from citi gateway page:$")
    public void iShouldBeNavigatedToBelowCitiPagesFromCitiGatewayPage(List<String> citiPages) throws Throwable {
        ArrayList<String> failedPagesList = new ArrayList<>();
        //        Assert.assertFalse("ERROR - ENV : CITI Credit services are down!!", Wait.untilElementPresent("credit_service_gateway_signedin.credit_services_down_message"));
        if (safari()) {
            Wait.secondsUntilElementPresent("credit_service_gateway_signedin.verify_page", 20);
        }
        Wait.forPageReady();
        Wait.untilElementPresent("credit_service_gateway_signedin.verify_page");
        Assert.assertTrue("credit services page is not displayed!!", onPage("credit_service_gateway_signedin"));
        for (String pageName : citiPages) {
            String elementName = (pageName.equals("apply_credit_card") ? "apply_now_button" : (pageName.equals("fusion_activate_card") ? "activate_card" : "add_card_button"));
            Wait.secondsUntilElementPresent("credit_service_gateway_signedin." + elementName, 35);
            if (Elements.elementPresent("credit_service_gateway_signedin." + elementName)) {
                Clicks.click("credit_service_gateway_signedin." + elementName);
            } else {
                Assert.fail(elementName + " is not found in the credit services page");
            }
            Wait.secondsUntilElementNotPresent(By.className("loading"), 30);
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("credit_service_gateway_signedin.speed_bump_continue_button", (safari() ? 20 : 5));
            if (Elements.elementPresent("credit_service_gateway_signedin.speed_bump_continue_button")) {
                Clicks.click("credit_service_gateway_signedin.speed_bump_continue_button");
            }
            try {
                Assert.assertFalse("ERROR - APP : Credit services are down!!", (bloomingdales() && Wait.untilElementPresent("credit_service_gateway_signedin.credit_service_down_message")));
                Wait.secondsUntilElementPresent((pageName + ".verify_page"), (safari() ? 25 : 20));
                // Added run after navigation as we are redirecting to citi site which takes more time to redirect.
                Navigate.runAfterNavigation();
                if (!onPage(pageName)) {
                    failedPagesList.add(pageName);
                }
            } catch (NoSuchElementException e) {
                logger.info(pageName + " page not displayed!!");
            }
            Navigate.browserBack();
            if (safari() || ie()) {
                new PageNavigation().I_navigate_to_the_page_from_footer("credit services");
            }
        }
        if (failedPagesList.size() > 0) {
            Assert.fail("Following pages are not displayed:" + failedPagesList.toString() + "!!");
        }
    }

    /**
     * Clears all browser cookies and refreshes the browser
     */
    @And("^I clear all the cookies$")
    public static void iClearAllTheCookies() {
        try {
            Cookies.deleteAllCookies();
            Navigate.browserRefresh();
        } catch (Exception e) {
            //ignore, need to continue test
        }
    }

    /**
     * Verifies that an order can be cancelled from order details page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the ability to cancel the order in (order details|order history) page$")
    public void iVerifyTheAbilityToCancelTheOrderInOrderDetailsPage(String orderPage) throws Throwable {
        if (orderPage.equals("order history")) {
            Wait.secondsUntilElementPresent("order_status.cancel_order_button", 5);
            Clicks.click("order_status.cancel_order_button");
            if (safari()) {
                Wait.secondsUntilElementPresent("order_status.order_cancel_yes_button", 10);
            }
            Clicks.click("order_status.order_cancel_yes_button");
            Wait.secondsUntilElementNotPresent("order_status.order_cancel_yes_button", 10);
            String cancelText = macys() ? (MEW() ? "Canceled" : "canceled") : "CANCELLED";
            if (!Elements.getText("order_status.order_status_text").contains(cancelText) &&
                    !Elements.getText("order_status.order_total_amount").replace("$", "").equals("0.00")) {
                Assert.fail("Order not cancelled successfully");
            }
        }else if (orderPage.equals("order details")) {
            Wait.untilElementPresent("order_status.view_order_detail");
            Clicks.click("order_status.view_order_detail");
            Wait.secondsUntilElementPresent("order_status.cancel_order_button", 5);
            Clicks.click("order_status.cancel_order_button");
            Wait.secondsUntilElementPresent("order_status.order_cancel_yes_button", 5);
            Clicks.click("order_status.order_cancel_yes_button");
            String cancelText = macys() ? "canceled" : "CANCELLED";
            if (!Elements.getText("order_status.order_status_text").contains(cancelText) &&
                    !Elements.getText("order_status.order_total_amount").replace("$", "").equals("0.00")) {
                Assert.fail("Order not cancelled successfully");
            }
        }
    }

    /**
     * Adds an offer to my wallet page
     *
     * @param validity   valid/invalid
     * @param promo_code promo code to add
     * @throws Throwable if any exception occurs
     */
    @And("^I provide (valid|invalid) offer \"([^\"]*)\" to my wallet$")
    public void I_provide_valid_offer_to_my_wallet(String validity, String promo_code) throws Throwable {
        try {
            if (prodEnv()) {
                Wait.untilElementPresent("add_offer_dialog.promo_code");
                TextBoxes.typeTextbox("add_offer_dialog.promo_code", promo_code);
                Clicks.click("add_offer_dialog.save_offer");
            } else {
                new MyWalletSteps().I_saved_omnichannel_offer_having_more_than_one_promo_code_in_wallet();
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Element is not visible on page: " + e);
        }
        if (validity.equals("valid")) {
            Assert.assertTrue("ERROR-DATA: Not a valid promo code", Elements.elementPresent("oc_my_wallet.delete_offers"));
        } else {
            Assert.assertTrue("ERROR-DATA: Not an invalid promo code", Elements.elementPresent("add_offer_dialog.offer_error"));
        }
    }

    /**
     * Adds a valid checkout address to address book
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add checkout eligible address on my address book page$")
    public void iAddCheckoutEligibleAddressOnMyAddressBookPage() throws Throwable {
        iNavigateToMyAccountPage();
        // Add CC overlay will display to user only when user visit my account for first time.
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        Wait.forPageReady();
        Wait.secondsUntilElementNotPresent("my_account.goto_my_address_book_link",30);
        Clicks.click("my_account.goto_my_address_book_link");
        //new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        if (MyAddressBook.isAddressAdded()) {
            addressObject = new MyAddressBook().updateAddress(0, opts);
        } else {
            new MyAddressBook().addAddress(opts);
        }
        logger.info("-> Added Checkout eligible address in address book page!!");
    }

    /**
     * Visits the website, creates or logs into an account, and adds an address to the profile
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a registered user with checkout eligible address$")
    public void iVisitTheWebSiteAsARegisteredUserWithCheckoutEligibleAddress() throws Throwable {
        new PageNavigation().I_visit_the_web_site_as_a_registered_user();
        if (!userProfileHasCheckoutEligibleAddress) {
            iAddCheckoutEligibleAddressOnMyAddressBookPage();
            userProfileHasCheckoutEligibleAddress = true;
        }
        ShoppingBag.emptyCurrentShoppingBag();
        iNavigateToMyAccountPage();
    }

    /**
     * Clicks the given link on my account page
     *
     * @param link link to click on (link text)
     * @throws Throwable if any exception occurs
     */
    @And("^I click on \"([^\"]*)\" link in my account page$")
    public void I_click_on_link_in_my_account_page(String link) throws Throwable {
        new MyAccountBCOM().navigateToLeftNavigationPage(link);
        Wait.forPageReady();
    }

    /**
     * Scrolls to the top of the page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I navigate to top of the list page$")
    public void iNavigateToTopOfTheListPage() throws Throwable {
        By menuEl = Elements.paramElement("navigation.goto_top_of_the_list", "Top of the List");
        if (Elements.elementPresent(menuEl)) {
            Clicks.click(menuEl);
        } else {
            logger.info("Unable to find top of the list page");
        }
        shouldBeOnPage("loyalty_benefits");
    }

    /**
     * Selects view all lists on wishlist page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select view all lists on my account page using mobile website$")
    public void iSelectViewAllListsOnMyAccountPage() throws Throwable {
        if (!onPage("my_account")) {
            Navigate.visit("my_account");
        }
        Elements.elementInView("my_account.my_lists");
        Clicks.click("my_account.my_lists");
        if (Elements.elementPresent("my_account.view_all_link")) {
            Clicks.click("my_account.view_all_link");
        }
    }

    /**
     * Verifies that DOB is filled in correctly
     */
    @Then("^I should see date of birth auto-populated$")
    public void validateDateOfBirth() {
        Assert.assertTrue("Error - DOB entered while creating new profile do not match with DOB on My Profile page.",
                new MyProfile().getDobFromMyProfilePage());
    }

    /**
     * Takes user to signIn Page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on signIn link$")
    public void iClickOnSignInLink() throws Throwable {
        logger.info("-> Go to signin page!!");
        CommonUtils.gotoSignInPage();
    }

    /**
     * Verifies that user is on signIn Page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^SignIn page should get loaded$")
    public void iVerifyUserIsOnSignInPage() throws Throwable {
        logger.info("-> Verify signin page is loaded!!");
        Assert.assertTrue("User is not on signIn Page", Elements.elementPresent("sign_in.email"));
        Assert.assertTrue("User is not on signIn Page", Elements.elementPresent("sign_in.password"));
        Assert.assertTrue("User is not on signIn Page", Elements.elementPresent("sign_in.verify_page"));
        logger.info("-> Sign In page gets loaded successfully!!");
    }

    /**
     * Verifies that user is able to signin with created profile
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I sign in with created profile$")
    public void iVerifyUserCanSignInWithCreatedProfile() throws Throwable {
        logger.info("-> SignIn to profile!!");
        CommonUtils.gotoSignInPage();
        CommonUtils.signInToCreatedProfile();
        Wait.forPageReady();
        Wait.secondsUntilElementNotPresent("sign_in.email", 30);
        Wait.secondsUntilElementPresent("header.goto_sign_out_link", 30);

    }


    /**
     * Verifies that the given elements are displayed on myaccount page
     *
     * @param elements elements that should be present
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see below fields on my account page:$")
    public void iShouldSeeBelowFieldsOnMyAccountsPage(List<String> elements) throws Throwable {
        logger.info("-> Verify all sections on my account page!!");
        elements.forEach(element ->
                Assert.assertTrue(element + " is not displayed on my account page",
                        Elements.elementPresent("my_account" + "." + element)));
    }

    /**
     * Verifies the left navigation links of the my account pages
     *
     * @param pageNames pages to verify (in page_name format)
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify other left navigation links on myaccounts page$")
    public void iVerifyOtherMyAccountPagesAreRenderedProperly(List<String> pageNames) throws Throwable {
        logger.info("-> Verify left navigation links on my account page!!");
        MyAccount wmp = new MyAccount();
        for (String pageName : pageNames) {
            wmp.navigateToOtherLeftNavigationPage(pageName);
            Wait.forPageReady();
            if (pageName.equalsIgnoreCase("my plenti")) {
                Wait.secondsUntilElementPresent("navigation.my_plenti", 30);
                Assert.assertTrue("My plenti page is not loaded", Elements.elementPresent("navigation.my_plenti"));
            } else if (pageName.equalsIgnoreCase("reward card balance")) {
                Wait.secondsUntilElementPresent("loyalty_association.verify_page", 30);
                Assert.assertTrue("My reward card balance page is not loaded", Elements.elementPresent("loyalty_association.verify_page"));
            } else if (pageName.equalsIgnoreCase("loyalty benefits")) {
                Wait.secondsUntilElementPresent("navigation.verify_perks_page", 30);
                Assert.assertTrue("loyalty benefits  page is not loaded", Elements.elementPresent("navigation.verify_perks_page"));
            } else if (pageName.equalsIgnoreCase("faqs")) {
                Wait.secondsUntilElementPresent("navigation.verify_faq_page", 30);
                Assert.assertTrue("faqs page is not loaded", Elements.elementPresent("navigation.verify_faq_page"));
            } else {
                if (!wmp.navigatedToExpectedPage(pageName)) {
                    Assert.fail("Not navigated to the " + pageName);
                }
                if (pageName.equalsIgnoreCase("my offers")) {
                    Navigate.browserBack();
                }
            }
        }
    }

    /**
     * Navigates to the my prefrences page from the my account page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to My Preferences page from My Account page$")
    public void iNavigateToMyPreferencesPageFromMyAccountPage() throws Throwable {
        logger.info("Go to My Preferences link Page!!");
        if (!onPage("my_account")) {
            iNavigateToMyAccountPage();
        }
        Clicks.click("navigation.goto_my_preferences_link");
    }

    /**
     * Verifies update my prefrences page
     *
     * @param phoneNums new phone number
     */
    @When("^I update phone in my Preferences page$")
    public void updateMyPreferences(List<String> phoneNums) {
        logger.info("-> update phone number in my preferences!!");
        for (String phoneNum : phoneNums) {
            Clicks.click("my_preferences.text_me");
            TextBoxes.typeTextbox("my_preferences.phone_area_code", phoneNum.substring(0, 3));
            TextBoxes.typeTextbox("my_preferences.phone_exchange_number", phoneNum.substring(3, 6));
            TextBoxes.typeTextbox("my_preferences.phone_subscriber_number", phoneNum.substring(6));
        }
        if (chrome()) {
            Clicks.click("my_preferences.update");
        } else {
            Clicks.javascriptClick("my_preferences.update");
        }
    }


    /**
     * Verifies that my preferences is updated
     *
     * @param phoneNums expected phone number
     * @throws Throwable if any exception occurs
     */
    @Then("^My Preferences should be updated$")
    public void verifyMyPreferencesUpdated(List<String> phoneNums) throws Throwable {
        logger.info("-> Verify phone number is updated in preferences!");
        String actualPhoneNum = "";
        for (String phoneNum : phoneNums) {
            actualPhoneNum = Elements.getElementAttribute("my_preferences.phone_area_code", "value");
            actualPhoneNum += Elements.getElementAttribute("my_preferences.phone_exchange_number", "value");
            actualPhoneNum += Elements.getElementAttribute("my_preferences.phone_subscriber_number", "value");
            logger.info("actualPhoneNum=" + actualPhoneNum);
            logger.info("expectedPhoneNum=" + phoneNum);
            Assert.assertEquals(phoneNum.trim(), actualPhoneNum.trim());
        }
    }

    /**
     * Verifies the user is navigated to the correct page when clicking on the given footer links
     *
     * @param customer_footer_links footer link to check
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to below respective customer pages:$")
    public void iShouldBeNavigatedToBelowRespectiveCustomerServicesPages(List<String> customer_footer_links) throws Throwable {
        logger.info("-> Verify customer service footer links!!");
        for (String linkName : customer_footer_links) {
            String footer_link = "footer";
            logger.info(linkName);
            switch (linkName.toLowerCase()) {
                case "order tracking":
                    logger.info(linkName);
                    footer_link += ".goto_order_status";
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("order_status.verify_page", 20);
                    Assert.assertTrue("Not navigated to order status page", Elements.elementPresent("order_status.verify_page"));
                    break;
                case "customer service":
                    logger.info(linkName);
                    footer_link += ".goto_customer_service";
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("customer_service.verifyPage", 30);
                    Assert.assertTrue("Not navigated to customer service page", Elements.elementPresent("customer_service.verifyPage"));
                    Navigate.browserBack();
                    break;

                case "returns":
                    footer_link += ".goto_returns";
                    logger.info(footer_link);
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("footer.verifyPage", 30);
                    Assert.assertTrue("Not navigated to returns page", Elements.elementPresent("footer.verifyPage"));
                    if (macys()) {
                        if (!StringUtils.equalsIgnoreCase(Elements.getText("footer.verifyPage").trim(), "Returns & Exchanges")) {
                            Assert.fail("Not on navigated to returns page");
                        }
                    } else {
                        if (!StringUtils.equalsIgnoreCase(Elements.getText("footer.verifyPage").trim(), "What is the return and exchange policy?")) {
                            Assert.fail("Not on navigated to returns page");
                        }
                    }
                    Navigate.browserBack();
                    break;

                case "shipping & delivery":
                    footer_link += ".goto_shipping_and_delivery";
                    logger.info(footer_link);
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("footer.verifyPage", 30);
                    Assert.assertTrue("Not navigated to shipping & delivery page", Elements.elementPresent("footer.verifyPage"));
                    if (macys()) {
                        if (!StringUtils.equalsIgnoreCase(Elements.getText("footer.verifyPage").trim(), "Shipping & Delivery")) {
                            Assert.fail("Not on navigated to Shipping & Delivery page");
                        }
                    } else {
                        if (!StringUtils.equalsIgnoreCase(Elements.getText("footer.verifyPage").trim(), "What is the shipping policy?")) {
                            Assert.fail("Not on navigated to Shipping & Delivery page");
                        }
                    }
                    Navigate.browserBack();
                    break;

                case "contact us":
                    logger.info(linkName);
                    footer_link += ".goto_contact_us_page";
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("footer.verifyContactUsPage", 30);
                    Assert.assertTrue("Not navigated to contact us page", Elements.elementPresent("footer.verifyContactUsPage"));
                    Navigate.browserBack();
                    break;

                case "para ayuda":
                    logger.info(linkName);
                    footer_link += ".goto_para_ayuda_page";
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("footer.verifyPage", 30);
                    Assert.assertTrue("Not navigated to para ayuda page", Elements.elementPresent("footer.verifyPage"));
                    if (macys()) {
                        if (!StringUtils.equalsIgnoreCase(Elements.getText("footer.verifyPage").trim(), "Para Ayuda")) {
                            Assert.fail("Not on navigated to para ayuda page");
                        }
                    }
                    break;
                case "international":
                    footer_link += ".goto_international";
                    Wait.untilElementPresent(footer_link);
                    Clicks.click(footer_link);
                    Wait.secondsUntilElementPresent("footer.verifyPage", 30);
                    Assert.assertTrue("Not navigated to international page", Elements.elementPresent("footer.verifyPage"));
                    Assert.assertTrue("not navigated to correct page", StringUtils.containsIgnoreCase(Elements.getText("footer.verifyPage"), "Bloomingdale's International"));
                    break;
            }

        }
    }

    /**
     * Signs in to existing production profile
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I sign in to my existing production profile$")
    public void iSignInToMyExistingProductionProfile() throws Throwable {
        logger.info("-> login to existing production profile!!");
        CommonUtils.signInWithExistingProfileToProdServer("prod_user");
        iNavigateToMyAccountPage();
    }

    /**
     * Verifies that user is able to add shipping address
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^address should get added to profile$")
    public void verifyAddressAddedToProfile() throws Throwable {
        logger.info("-> Verify address can be added to profile from my address book!!");
        Assert.assertTrue("Address did not get added to profile", Elements.elementPresent("my_address_book.addedAddressList"));
        if (macys()) {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.addedAddress_primary").trim(), "Primary Address")) {
                Assert.assertTrue("Address did not get added as primary address", false);
            }

            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.status_message").trim(), "Your entry has been successfully saved")) {
                Assert.assertTrue("Did not get address addition status message", false);
            }
        } else {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.primary_address").trim(), "Primary Shipping Address")) {
                Assert.assertTrue("Address did not get added as primary address", false);
            }
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.success_message").trim(), "The entry was successfully saved")) {
                Assert.assertTrue("Did not get address addition status message", false);
            }

        }
    }

    /**
     * Verifies that user is able to edit shipping address
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I edit address$")
    public void iEditShippingAddress() throws Throwable {
        CommonUtils.editAddressDetails();
    }

    /**
     * Verifies that user is able to edit shipping address
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^address should get updated$")
    public void verifyAddressGetsUpdated() throws Throwable {
        logger.info("-> Verify updation of address!!");
        if (macys()) {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.status_message").trim(), "Your entry has been successfully saved")) {
                Assert.assertTrue("Did not get address addition status message", false);
            }

            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.updated_first_line").trim(), "884 Walnut St")) {
                Assert.assertTrue("Did not update address", false);
            }
        } else {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.success_message").trim(), "The entry was successfully saved")) {
                Assert.assertTrue("Did not get address addition status message", false);
            }
            if (!StringUtils.equalsIgnoreCase(Elements.getElementAttribute("my_address_book.updated_first_line", "value").trim(), "884 Walnut St")) {
                Assert.assertTrue("Did not update address", false);
            }
        }

    }

    /**
     * Verifies that another shipping address gets added to profile as non primary
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^another shipping address should be added to profile$")
    public void verifyAnotherAddressGetsAdded() throws Throwable {
        logger.info("-> Add second shipping address to profile!!");
        Assert.assertTrue("Another address did not get added to profile as non primary address", Elements.elementPresent("my_address_book.make_primary"));
        if (macys()) {
            if (StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.second_address").trim(), "884 Walnut St")) {
                Assert.assertTrue("Second Address did not get added", false);
            }
        } else {

            logger.info("Prachi!!" + Elements.getElementAttribute("my_address_book.second_address", "value"));
            if (StringUtils.equalsIgnoreCase(Elements.getElementAttribute("my_address_book.second_address", "value").trim(), "884 Walnut St")) {
                Assert.assertTrue("Second Address did not get added", false);
            }
        }
    }

    /**
     * Verifies that another shipping address can be made primary
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^second address should be made primary$")
    public void verifyAnotherAddressCanBePrimary() throws Throwable {
        logger.info("-> Verify second address is now primary address!!");
        if (macys()) {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.status_message").trim(), "Your entry has been successfully saved as default Shipping Address.")) {
                Assert.assertTrue("Did not get correct status message on updating primary address", false);
            }

            if (StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.updated_first_line").trim(), "884 Walnut St")) {
                Assert.assertTrue("Did not update second address as primary address", false);
            }
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.second_address").trim(), "884 Walnut St")) {
                Assert.assertTrue("Did not update second address as primary address", false);
            }
        } else {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.success_message").trim(), "The entry was successfully saved as default shipping address.")) {
                Assert.assertTrue("Did not get correct status message on updating primary address", false);
            }
            if (StringUtils.equalsIgnoreCase(Elements.getElementAttribute("my_address_book.updated_first_line", "value").trim(), "884 Walnut St")) {
                Assert.assertTrue("Did not update second address as primary address", false);
            }
            if (!StringUtils.equalsIgnoreCase(Elements.getElementAttribute("my_address_book.second_address", "value").trim(), "884 Walnut St")) {
                Assert.assertTrue("Did not update second address as primary address", false);
            }
        }
    }

    /**
     * Verifies that another shipping address should get deleted
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^address should get deleted$")
    public void verifyShippingAddressDeleted() throws Throwable {
        logger.info("-> Verify address is deleted from profile!!");
        if (macys()) {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.status_message").trim(), "The entry has been deleted from your Address Book.")) {
                Assert.assertTrue("Did not delete address", false);
            }
        } else {
            if (!StringUtils.equalsIgnoreCase(Elements.getText("my_address_book.success_message").trim(), "An entry has been deleted from your address book.")) {
                Assert.assertTrue("Did not delete address", false);
            }
        }
    }

    @Then("^I should receive \"([^\"]*)\" same phone number error message$")
    public void samePhoneNumberErrorMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText("create_profile.error_server_side").trim();
        Assert.assertEquals("This error message is not expected in the case of all same digits for phone.", errorMsg, errorMsgFromUI);
    }

    @Then("^I should receive \"([^\"]*)\" a weak pwd error message$")
    public void weakPasswordErrorMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText("create_profile.error_server_side").trim();
        Assert.assertEquals("This error message is not expected in the case of simple password.", errorMsg, errorMsgFromUI);
    }

    @Then("^I should receive \"([^\"]*)\" error message$")
    public void weakPa2sswordErrorMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText(Elements.element("create_profile.error_server_side"));
        Assert.assertEquals("This error message is not expected in the case of dob less than 13.", errorMsg, errorMsgFromUI);
    }


    @When("^I navigate to shipping address page$")
    public void iGoToShippingPage() throws Throwable {
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
    }

    /**
     * Verifies update my phone in my account page
     *
     * @param phoneNums phone number to type in
     */
    @When("^I update phone number$")
    public void updatePhoneNumber(List<String> phoneNums) {
        for (String phoneNum : phoneNums) {
            TextBoxes.typeTextbox("create_profile.phone_number", phoneNum);
        }
    }

    /**
     * Verifies update my phone in my account page
     */
    @When("^I update password with simple password$")
    public void updatePasswordToSimplePasswrd() {
        logger.info("-> update password to simple password!!");
        TextBoxes.typeTextbox("create_profile.password", simplePwd);
        if (chrome()) {
            Clicks.click("create_profile.create_profile_button");
        } else {
            Clicks.javascriptClick("create_profile.create_profile_button");
        }

    }

    /**
     * Verify signIn Page links
     *
     * @param elements list of elements to verify
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the sign in page links:$")
    public void iverifySignInPageLinks(List<String> elements) throws Throwable {
        logger.info("-> verify my accounts links!!");
        elements.forEach(element ->
                Assert.assertTrue(element + " is not displayed on signIn page",
                        Elements.elementPresent("sign_in" + "." + element)));
    }

    /**
     * Verifies the display of the signIn pages
     *
     * @param pageNames pages to verify (in page_name format)
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to below respective sign in pages:$")
    public void iVerifySignInPagesAreRenderedProperly(List<String> pageNames) throws Throwable {
        logger.info("-> Verify links on signIn page!!");
        MyAccount wmp = new MyAccount();
        for (String pageName : pageNames) {
            wmp.navigateToSignInPageLinks(pageName);
            if (!wmp.navigatedToExpectedPage(pageName)) {
                Assert.fail("Not navigated to the " + pageName);
            }
            if (macys()) {
                if (!StringUtils.equalsIgnoreCase("create profile benefits", pageName)) {
                    Navigate.browserBack();
                    Wait.secondsUntilElementPresent("sign_in.forgot_password_link", 20);
                }
            } else {
                if (!StringUtils.equalsIgnoreCase("Privacy Practices", pageName)) {
                    Navigate.browserBack();
                    Wait.secondsUntilElementPresent("sign_in.forgot_password_link", 20);
                }
            }
        }

    }

    /**
     * Verifies that cookies for guest user on home/signIn page
     *
     * @param pageName name of expected page - "home" or "sign in"
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify cookies for guest user in \"([^\"]*)\" page$")
    public void iVerifyGuestUserCookiesOnHomeSignInPage(String pageName) throws Throwable {
        if (StringUtils.equalsIgnoreCase(pageName, "home")) {
            logger.info("-> Verify home  page cookies!!");
            String signedInUserCookies = Cookies.getCookieValue("SignedIn");
            logger.info("-> Signed in cookie for guest user on home page is " + signedInUserCookies);
        }
        if (StringUtils.equalsIgnoreCase(pageName, "sign in")) {
            logger.info("-> Verify sign in page cookies!!");
            String signedInUserCookies = Cookies.getCookieValue("SignedIn");
            logger.info("-> Signed in cookie for guest user on sign in page is " + signedInUserCookies);
            if (!StringUtils.equalsIgnoreCase(signedInUserCookies, "0")) {
                Assert.fail("SignedIn cookie value is not zero for guest user");
                String secureUserTokenCookies = Cookies.getCookieValue("secure_user_token");
                if (!secureUserTokenCookies.isEmpty()) {
                    Assert.fail("secure_user_token cookie value is not empty on signin page for guest user");
                }

            }
        }
    }

    /**
     * Captures userId cookies
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I capture the UserID$")
    public void iCaptureUserId() throws Throwable {
        logger.info("-> Capture UserIdCookies!!");
        uid = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        logger.info("User id: " + uid);
    }

    /**
     * Verify secure user token and cookies after signin
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify secure user token and cookie values after sign in$")
    public void iVerifyTokenAndCokoiesAfterSignIn() throws Throwable {
        logger.info("-> Capture UserIdCookies after sign in!!");
        String user_id = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        logger.info("User id after sign in: " + user_id);
        Assert.assertNotNull(user_id);
        String db_user_id = CommonUtils.getUserDetailsByEmail(TestUsers.currentEmail);
        logger.info("User id from db: " + db_user_id);
        Assert.assertEquals("MACYS_ONLINE_UID is not same as user id in DB for the newly created user", db_user_id, user_id);
        logger.info("User id before creating profile should not be equal to user id after creating profile.");
        Assert.assertNotEquals(uid, user_id);
        Assert.assertEquals("SignedIn cookie value is not 1 for signed user", "1", Cookies.getCookieValue("SignedIn"));
        Assert.assertNotNull("Secure_user_token cookie is null", Cookies.getCookieValue("secure_user_token"));
        Assert.assertTrue("", StringUtils.containsIgnoreCase(Cookies.getCookieValue("SNSGCs"), "last_access_token"));
        logger.info("Verified cookies after user is Signed in to profile");
    }

    /**
     * Verify secure user token and cookies after signout
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify secure user token and signed in cookie value after signed out from profile$")
    public void iVerifyTokenAndCokoiesAfterSignOut() throws Throwable {
        logger.info("-> Capture UserIdCookies after sign out!!");
        logger.info("secure" + Cookies.getCookieValue("secure_user_token"));
        Assert.assertEquals("SignedIn cookie value is not 0 for signed user", "0", Cookies.getCookieValue("SignedIn"));
        // Assert.assertNotNull("Secure_user_token cookie is null",Cookies.getCookieValue("secure_user_token"));
        logger.info("Verified Secure User Token and Signed in cookie after user is Signed Out from profile");
    }


    /* Takes user to Paybill Page
    *
    * @throws Throwable if any exception occurs
    */
    @When("^I click on pay bill from footer$")
    public void iClickOnPayBillLink() throws Throwable {
        logger.info("-> Go to PayBill page!!");
        Clicks.click("home.goto_credit_pay_bill_online");

    }

    @Then("^I should get popup to add card$")
    public void iGetPopupToAddCard() throws Throwable {
        logger.info("-> Verify popup to add card!!");
        if (macys()) {
            Wait.secondsUntilElementPresent("credit_service_gateway_signedin.add_card_on_overlay", 20);
            Assert.assertTrue("ERROR - Popup to add card did not open!!", Elements.elementPresent("credit_service_gateway_signedin.add_card_on_overlay"));
        } else {
            Wait.secondsUntilElementPresent("credit_service_gateway_signedin.speed_bump_close_button", 20);
            Assert.assertTrue("ERROR - Popup to add card did not open!!", Elements.elementPresent("credit_service_gateway_signedin.speed_bump_close_button"));
        }
    }

    @When("^I close add card popup$")
    public void iClickCloseAddCardPopup() throws Throwable {
        logger.info("-> Close Add card popup!!");
        if (macys()) {
            if (Elements.elementPresent("credit_service_gateway_signedin.add_card_on_overlay")) {
                Clicks.click("credit_service_gateway_signedin.close_popup");
                Clicks.hoverForSelection("credit_service_gateway_signedin.activate_card");
            }
        } else {
            if (Elements.elementPresent("credit_service_gateway_signedin.speed_bump_close_button")) {
                Clicks.click("credit_service_gateway_signedin.speed_bump_close_button");
                Clicks.hoverForSelection("credit_service_gateway_signedin.activate_card");
            }
        }
    }

    @Then("^I should land on credit service page$")
    public void iLandOnCreditServicePage() throws Throwable {
        logger.info("-> Verify credit service page!!");
        Wait.secondsUntilElementPresent("credit_service_gateway_signedin.verify_page", 20);
        try {
            if (!onPage("credit_service_gateway_signedin")) {
                Assert.fail("credit_service_gateway_signedin page is not displayed!!");
            }
        } catch (NoSuchElementException e) {
            logger.info("credit_service_gateway_signedin page not displayed!!");
        }
    }

    @Then("^I verify first name is displayed on the home page$")
    public void iVerifyFirstNameIsDisplayedOnTheHomePage() throws Throwable {
        logger.info("-> Verify first name in header!!");
        Clicks.click("header.logo");
        Wait.secondsUntilElementPresent("header.user_first_name", 20);
        String actual_user_name = Elements.findElement("header.user_first_name").getText().split("HI, ")[1];
        UserProfile customer = TestUsers.getCustomer(null);
        String expected_user_name = customer.getUser().getProfileAddress().getFirstName();
        logger.info("-> actual name " + actual_user_name);
        logger.info("-> expected name " + expected_user_name);
        Assert.assertTrue("first name is not displayed in header", StringUtils.equalsIgnoreCase(actual_user_name, expected_user_name));
    }

    @When("^I create a new profile using services$")
    public void iCreateANewProfileUsingServices() throws Throwable {
        try {
            // create profile REST service is only for MCOM.
            UserProfile userProfile = new UserProfile();
            if (!prodEnv())
                userProfile = CommonUtils.createNewUserProfile();
            UserProfileService.createUserProfile(userProfile, true);
        } catch (AssertionError e) {
            logger.info("Service failed. Falling back on UI.");
        }
        CommonUtils.signInOrCreateAccount();
        CreateProfile.closeSecurityAlertPopUp();
        iAddCheckoutEligibleAddressOnMyAddressBookPage();
        iNavigateToMyWalletPageFromMyAccountPage();
        i_add_credit_card_to_my_wallet();

    }

    @And("^I add (\\d+) shipping address(?:es)? to the address book page$")
    public void iAddShippingAddressesToTheAddressBookPage(int count) throws Throwable {
        if (!onPage("my_account")) {
            iNavigateToMyAccountPage();
        }
        if (count > 0) {
            iNavigateToMyAddressBookPage();

        }
        MyAddressBook addressBook = new MyAddressBook();
        for (int i = 0; i < count; i++) {
            newAddressObject = addressBook.addAddress();
            logger.info("-> Added Checkout eligible address in address book page!!");

        }

    }

    @Given("^I leave the site and return later$")
    public void iLeaveTheSiteAndReturnLater() throws Throwable {
        iSignOutFromMyCurrentProfile();
        Navigate.browserReset();
        Navigate.visit("home");
    }

    @When("^I close and reopen the browser$")
    public void iCloseAndReopenTheBrowser() throws Throwable {
        Navigate.browserReset();
        Navigate.visit("home");
    }

    @Then("^I verify that Email Address field is not pre-populated in the (Regular|checkout) SignIn Page$")
    public void iVerifyThatEmailAddressFieldIsNotPrePopulatedInTheRegularSignInPage(String page) throws Throwable {
        String current_page = (page.equals("Regular") ? "sign_in" : "checkout");
        Assert.assertTrue("ERROR - ENV: Email address is pre-populated on sign in page", Elements.findElement(current_page + ".email").getText().equals(""));
        logger.info("Verified that email address is not pre-populated on sign in page");
    }

    @Given("^I verify the basic attributes of the my profile section on My Account Page$")
    public void iVerifyTheBasicAttributesOfTheMyProfileSectionOnMyAccountPage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Profile link is not displayed in My Profile section", Elements.findElement("my_account.goto_my_profile").isDisplayed());
        if (macys())
            Assert.assertTrue("ERROR - ENV: Welcome message is not displayed in My Profile section", Elements.findElement("header.user_first_name").getText().contains("HI"));
        else
            Assert.assertTrue("ERROR - ENV: Profile description is not displayed in My Profile section", Elements.findElement("header.Welcome_message").getText().contains("WELCOME"));
    }

    @Then("^I should be redirected to home page with SIGN OUT link on header$")
    public void iShouldBeRedirectedToHomePageWithSIGNOUTLinkOnHeader() throws Throwable {
        if (bloomingdales()) {
            logger.info("-> Signout from current  profile!!");
            Clicks.clickIfPresent(By.className("container-close"));
            if (bloomingdales()) {
                Clicks.hoverForSelection("home.goto_my_account_link");
            }
        }
        Wait.untilElementPresent("header.goto_sign_out_link");
        Assert.assertTrue("ERROR - ENV: User is not sigined in to profile", Elements.elementPresent("header.goto_sign_out_link"));
        iVerifyTokenAndCokoiesAfterSignIn();
    }

    @Then("^I should see welcome message$")
    public void iShouldSeeWelcomeMessage() throws Throwable {
        if (macys())
            Assert.assertTrue("ERROR - ENV: HI message is not displayed in My Profile section", Elements.findElement("header.user_first_name").getText().contains("HI"));
        else {
            if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
                CreateProfile.closeSecurityAlertPopUp();
                Clicks.click("my_account.add_card_overlay_no_thanks_button");
            }
            Clicks.hoverForSelection("header.goto_my_account_link");
            Wait.forPageReady();
            Wait.untilElementPresent("header.Welcome_message");
            Assert.assertTrue("ERROR - ENV: Profile description is not displayed in My Profile section", Elements.findElement("header.Welcome_message").getText().contains("HELLO,"));
        }
    }

    @When("^I sign out and sign back in$")
    public void iSignOutAndSignBackIn() throws Throwable {
        String mail = TestUsers.currentEmail;
        String password = TestUsers.currentPassword;
        iSignOutFromMyCurrentProfile();
        Wait.forPageReady();
        String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
        Wait.untilElementPresent(elementName);
        Clicks.click(elementName);
        if (macys())
            CommonUtils.closeIECertError();
        TextBoxes.typeTextbox("sign_in.email", mail);
        TextBoxes.typeTextbox("sign_in.password", password);
        Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
        new MyAccount().setSecurityQuestion();
        resumePageHangWatchDog();
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
    }

    @Then("^I should see \"([^\"]*)\" page$")
    public void iShouldSeePage(String arg1) throws Throwable {
        Assert.assertTrue("User is not on My Account Page", onPage("my_account"));
    }

    @When("^I sign out and navigate to Create Profile page$")
    public void iSignOutAndNavigateToCreateProfilePage() throws Throwable {
        iSignOutFromMyCurrentProfile();
        Wait.forPageReady();
        if (macys())
            CommonUtils.closeIECertError();
        if (!onPage("create_profile")) {
            Navigate.visit("create_profile");
        }
        pausePageHangWatchDog();
    }

    @Given("^I enter an existing email id$")
    public void iEnterAnExistingEmailId() throws Throwable {
        Wait.forPageReady();
        TextBoxes.typeTextbox("create_profile.email", TestUsers.currentEmail);
        CreateProfile.typeTextBoxIfPresent("create_profile.email_verify", TestUsers.currentEmail);
        TextBoxes.typeTextbox("create_profile.password", "");
    }

    @Then("^I should see existing email id error message$")
    public void iShouldSeeExistingEmailIdErrorMessage(List<String> arg1) throws Throwable {
        Wait.untilElementPresent("create_profile.error_existing_email");
        Assert.assertTrue(Elements.findElement("create_profile.error_existing_email").getText().equals(arg1.get(0)));
    }

    @When("^I select sign in link from email error message$")
    public void iSelectSignInLinkFromEmailErrorMessage() throws Throwable {
        Wait.forPageReady();
        Clicks.click("create_profile.error_existing_email_sign_in");
        Wait.forPageReady();
    }

    @Then("^I should see inline error messages$")
    public void iShouldSeeInlineErrorMessages(DataTable table) throws Throwable {
        List<WebElement> actualErrorMessage = Elements.findElements("create_profile.all_error_message");
        List<List<String>> dataLayer = table.raw();
        if (macys() || table.raw().size() <= 5) {
            for (int i = 1, j = 0; i < table.raw().size(); i++, j++) {
                String expectedErrorMessage = dataLayer.get(i).get(1);
                Assert.assertTrue("Error message mismatched", actualErrorMessage.get(j).getText().equals(expectedErrorMessage));
            }
        } else {
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_first_name").getText().equals(dataLayer.get(1).get(1)));
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_last_name").getText().equals(dataLayer.get(2).get(1)));
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_email").getText().equals(dataLayer.get(3).get(1)));
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_dob").getText().equals(dataLayer.get(4).get(1)));
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_phone_number").getText().equals(dataLayer.get(5).get(1)));
        }
    }

    @Then("^I should receive same phone number error message$")
    public void iShouldReceiveSamePhoneNumberErrorMessage(List<String> arg1) throws Throwable {
        if (macys())
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_server_side").getText().equals(arg1.get(0)));
        else
            Assert.assertTrue("Error message mismatched", Elements.findElement("create_profile.error_message").getText().equals(arg1.get(0)));
    }

    @When("^I update weak pwd with complex pwd$")
    public void updatePasswordToComplexPasswrd() {
        logger.info("-> update password to complex password!!");
        String pass = TestUsers.currentPassword;
        TextBoxes.typeTextbox("create_profile.password", TestUsers.currentPassword);
        if (chrome()) {
            Clicks.click("create_profile.create_profile_button");
        } else {
            Clicks.javascriptClick("create_profile.create_profile_button");
        }

    }

    @And("^I navigate to my address book page$")
    public void iNavigateToMyAddressBookPage() throws Throwable {
        if(onPage("my_account")){
        Clicks.click("my_account.goto_my_address_book_link");
        }else {
            new MyAccount().navigateToLeftNavigationPage("my address book");
        }
        Wait.forPageReady();
        Wait.untilElementPresent("my_address_book.verify_page");
    }

    @Then("^I verify address book details in DB$")
    public void iVerifyAddressBookDetailsInDB() throws Throwable {
        logger.info("email=" + TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail());
        CommonUtils.validateAddressAssociatedToUserFromDb(TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail());
    }

    @Then("^I should see the message:$")
    public void iShouldSeeTheMessage(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        if (macys()) {
            String expectedMsg = values.get("MCOM");
            String actualMsg = Elements.findElement("my_address_book.status_message").getText();
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.trim().equals(actualMsg.trim()));
        } else {
            String expectedMsg = values.get("BCOM");
            String actualMsg = Elements.findElement("my_address_book.success_message").getText();
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.trim().equals(actualMsg.trim()));

        }
    }

    @And("^I select a random store with zip code in Choose a Preferred Store section$")
    public void iSelectARandomStoreWithZipCodeInChooseAPreferredStoreSection() throws Throwable {
        String zipcode = TestUsers.getCustomerInformation().getUser().getProfileAddress().getZipCode();
        Clicks.click("my_account.choose_store");
        Wait.untilElementPresent("change_preferred_store.zipcode");
        TextBoxes.typeTextbox("change_preferred_store.zipcode", zipcode);
        Clicks.click("change_preferred_store.search");
        Wait.secondsUntilElementPresent("change_preferred_store.make_my_store_links", 10);
        Assert.assertTrue("There are no stores availble with the zipcode:" + zipcode, Elements.elementPresent("change_preferred_store.make_my_store_links"));
        Clicks.clickRandomElement("change_preferred_store.make_my_store_links");
    }

    @Then("^I receive an error message when attempting to add more than (\\d+) addresses$")
    public void iReceiveAnErrorMessageWhenAttemptingToAddMoreThanAddresses(int count) throws Throwable {
        if (!onPage("my_account")) {
            iNavigateToMyAccountPage();
        }
        if (count > 0) {
            iNavigateToMyAddressBookPage();
        }
        MyAddressBook addressBook = new MyAddressBook();
        for (int i = 0; i < count; i++) {
            addressBook.addAddress();
            logger.info("-> Added Checkout eligible address in address book page!!");
        }
        Assert.assertTrue("error message is not correct when more than 10 addresses are added to my address book page", StringUtils.equalsIgnoreCase((Elements.getText("my_address_book.errorMessage")), "We're sorry. You already have 10 addresses saved in your address book. You will need to remove one before adding a new address."));
    }

    @Then("^I should verify that address book page is displayed$")
    public void iShouldVerifyThatAddressBookPageIsDisplayed() throws Throwable {
        if (!onPage("my_address_book")) {
            Assert.fail("Not on my address book page");
        }
    }

    @And("^I click on make primary link$")
    public void iClickOnMakePrimaryLink() throws Throwable {
        CommonUtils.makeSecondAddressPrimary();
    }

    @And("^I update the address in my address book with invalid phone_area_code and phone_exchange and phone_subscriber fields$")
    public void iUpdateTheAddressInMyAddressBookWithInvalidPhone_area_codeAndPhone_exchangeAndPhone_subscriberFields() throws Throwable {
        String pageName = "my_address_book";
        List<WebElement> allAddress = Elements.findElements(pageName + ".addresses");
        Clicks.click(allAddress.get(0).findElement((macys() ? (Elements.elementPresent(By.linkText("edit")) ? By.linkText("edit") : By.linkText("Edit")) : By.className("account_addressLink"))));
        TextBoxes.typeTextbox(pageName + ".phone_area_code", "&#%");
        TextBoxes.typeTextbox(pageName + ".phone_exchange", "&#%");
        TextBoxes.typeTextbox(pageName + ".phone_subscriber", "&#%!");
        Clicks.click(pageName + ".add_address_button");
        Wait.forPageReady();
        logger.info("->editAddress(): Address updated!!");
    }

    @Then("^I should see the error:$")
    public void iShouldSeeTheError(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        if (macys()) {
            String expectedMsg = values.get("MCOM");
            String actualMsg = Elements.findElement("my_address_book.error_message").getText();
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.trim().equals(actualMsg.trim()));
        } else {
            String expectedMsg = values.get("BCOM");
            String actualMsg = Elements.findElement("my_address_book.areaCodeError").getText();
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + expectedMsg, expectedMsg.trim().equals(actualMsg.trim()));

        }
    }

    @Then("^I should verify the address field attributes should be empty$")
    public void iShouldVerifyTheAddressFieldAttributesShouldBeEmpty() throws Throwable {
        List<WebElement> myAddressBookFields = Elements.findElements(Elements.element("my_address_book.myAdressInputFields"));
        for (int i = 0; i < myAddressBookFields.size(); i++) {
            String actualValue = myAddressBookFields.get(i).getAttribute("value").trim();
            logger.info(actualValue);
            Assert.assertTrue("ERROR - APP: my address book fields are not empty", "".trim().equals(actualValue.trim()));
        }
    }

    @Then("^I should not see the deleted address in shipping & payment page$")
    public void iShouldNotSeeTheDeletedAddressInShippingPaymentPage() throws Throwable {
        Wait.secondsUntilElementPresent("responsive_checkout_signed_in.shipping_method_info", 30);
        logger.info(Elements.getText("responsive_checkout_signed_in.added_shipping_address").replace("\n", " "));
        String addressNotPresent = "";
        String addressPresent = "";
        addressNotPresent = addressObject.getFirstName() + " " + addressObject.getLastName() + " " + addressObject.getAddressLine1() + " " + addressObject.getAddressLine2() + " " + addressObject.getCity() + ", " + addressObject.getState() + " " + addressObject.getZipCode();
        addressNotPresent = addressNotPresent.trim().replaceAll("\\s+", " ");
        addressPresent = newAddressObject.getFirstName() + " " + newAddressObject.getLastName() + " " + newAddressObject.getAddressLine1() + " " + newAddressObject.getAddressLine2() + " " + newAddressObject.getCity() + ", " + newAddressObject.getState() + " " + newAddressObject.getZipCode();
        addressPresent = addressPresent.trim().replaceAll("\\s+", " ");
        logger.info("address that should be present on shipping page =" + addressPresent);
        logger.info("address that should not be present on shipping page =" + addressNotPresent);
        Assert.assertTrue("ERROR - Address on shipping page is not correct and is not in sync with my address book page", StringUtils.equalsIgnoreCase((Elements.getText("responsive_checkout_signed_in.added_shipping_address").replace("\n", " ")).trim(), addressPresent.trim()));
        Assert.assertFalse("ERROR - Address on shipping page is not correct and is not in sync with my address book page", StringUtils.equalsIgnoreCase((Elements.getText("responsive_checkout_signed_in.added_shipping_address").replace("\n", " ")).trim(), addressNotPresent.trim()));
    }

    @Given("^I verify the error message on sign in page$")
    public void iVerifyTheErrorMessageOnSignInPage(DataTable table) throws Throwable {
        int s = table.raw().size();
        for (int i = 1; i < table.raw().size(); i++) {
            TextBoxes.typeTextbox("sign_in.email", table.raw().get(i).get(0));
            Thread.sleep(5000);
            TextBoxes.typeTextbox("sign_in.password", table.raw().get(i).get(1));
            Clicks.javascriptClick("sign_in.verify_page");
            Thread.sleep(5000);
            String errorMessage = Elements.findElement("sign_in.error_message").getText().replaceAll(System.getProperty("line.separator"), "");
            Assert.assertTrue("Error - ENV: Error message is not displayed properly", errorMessage.equals(table.raw().get(i).get(2)));
        }
    }

    @Given("^I navigate from \"([^\"]*)\" page to sign in page$")
    public void i_navigate_from_page_to_sign_in_page(String name) throws Throwable {
        Wait.untilElementPresent("home.goto_guest_my_account");
        Clicks.hoverForSelection("home.goto_guest_my_account");
        Wait.forPageReady();
        switch (name) {
            case "My Wallet": {
                Clicks.click("home.my_account_dropdown_wallet");
                Wait.forPageReady();
                break;
            }
            case "My Lists": {
                Clicks.click("home.my_account_dropdown_lists");
                Wait.untilElementPresent("wish_list.goto_signin_link");
                Clicks.click("wish_list.goto_signin_link");
                break;
            }
            case "My Order History": {
                Clicks.click("home.my_account_dropdown_order_history");
                Wait.untilElementPresent("order_confirmation.sign_in_button");
                Clicks.click("order_confirmation.sign_in_button");
                break;
            }
            case "My Macy's Credit Card": {
                Clicks.click("home.my_account_dropdown_credit_card");
                Wait.untilElementPresent("credit_service_gateway_guest.credit_sign_in_button");
                Clicks.click("credit_service_gateway_guest.credit_sign_in_button");
                break;
            }
        }
        logger.info("successfully navigated to sign in page from" + name + " page");
    }

    @When("^I sign with missing password fields on USL enrollment sign in page$")
    public void iSignWithMissingFieldsOnUSLEnrollmentSignInPage(DataTable arg1) throws Throwable {
        Navigate.visit(WebDriverManager.getWebDriver().getCurrentUrl() + "/account/signin?program=usl");
        logger.info("Navigating to" + WebDriverManager.getWebDriver().getCurrentUrl() + "/account/signin?program=usl" + " url");
        Wait.forPageReady();
        iVerifyTheErrorMessageOnSignInPage(arg1);
    }


    @When("^I sign in with created profile and proceed till security question$")
    public void iVerifyUserCanSignInWithCreatedProfileTillSecurityQuestion() throws Throwable {
        logger.info("-> SignIn to profile!!");
        CommonUtils.gotoSignInPage();
        UserProfile customer = TestUsers.getCustomer(null);
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
        Wait.forPageReady();
        Wait.secondsUntilElementNotPresent("sign_in.email", 30);
        Wait.secondsUntilElementPresent("header.goto_sign_out_link", 30);
    }

    @Then("^I should see security Q&A setup$")
    public void iShouldSeeSecurityQASetup() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("sign_in.security_question");
        Assert.assertTrue("Security Q&A setup overlay is not displayed", Elements.elementPresent("sign_in.security_question"));
        logger.info("Security Q&A setup overlay is displayed");
    }

    @Given("^I should see security answer field type as password on security Q&A overlay$")
    public void iShouldSeeSecurityAnswerFieldTypeAsPasswordOnSecurityQAOverlay() throws Throwable {
        Assert.assertTrue("Security Answer field type is not password on Security Q&A overlay", Elements.getElementAttribute("sign_in.security_answer", "type").equals("password"));
        logger.info("Security Answer field type is password on Security Q&A overlay");
    }

    @Given("^I update the first_name and last_name in My Profile Page$")
    public void iUpdateTheFirstNameAndLastNameInMyProfilePage() throws Throwable {
        iNavigateToMyProfilePage();
        iUpdateMyProfileWithoutClickingOnUpdateButton();
    }

    @When("^I navigate to address book page from update profile page$")
    public void iNavigateToAddressBookPageFromUpdateProfilePage() throws Throwable {
        Clicks.click("my_account.my_address_book");
        Wait.forPageReady();
    }

    @And("^I update profile details on my profile and do not click on update button$")
    public void iUpdateMyProfileWithoutClickingOnUpdateButton() throws Throwable {
        logger.info("-> update user profile!!");
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        TextBoxes.typeTextbox("my_profile.verify_page", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("my_profile.last_name", customer.getUser().getProfileAddress().getLastName());
        if (Elements.getText("my_profile.address_line_1").isEmpty()) {
            User user = customer.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();
            TextBoxes.typeTextbox("my_profile.address_line_1", profileAddress.getAddressLine1());
            TextBoxes.typeTextbox("my_profile.address_city", profileAddress.getCity());
            if (macys() || MEW()) {
                DropDowns.selectByText("my_profile.address_state", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
            } else {
                DropDowns.selectCustomText("create_profile.address_state_list", "create_profile.state_options", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
                Clicks.clickIfPresent("my_profile.gender_female");
            }
            TextBoxes.typeTextbox("my_profile.address_zip_code", String.valueOf(profileAddress.getZipCode()));
            if (macys()) {
                DropDowns.selectByText("my_profile.gender", user.getGender());
                DropDowns.selectByText("create_profile.security_question", user.getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("create_profile.security_answer", user.getUserPasswordHint().getAnswer());
            }
        }
    }

    @Then("^I should see the Save Updates Overlay in My Profile page$")
    public void iShouldSeeTheSaveUpdatesOverlayInMyProfilePage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("The Save Updates Overlay is not visible", Elements.elementPresent("my_profile.unsaved_ChangesPanel_overlay"));
        logger.info("The Save Updates Overlay is displayed successfully");
    }

    @When("^I select \"([^\"]*)\" in Save Updates Overlay on my profile page$")
    public void iSelectInSaveUpdatesOverlayOnMyProfilePage(String option) throws Throwable {
        switch (option) {
            case "Yes": {
                Clicks.click("my_profile.yes_button_unsaved_ChangesPanel_overlay");
                break;
            }
            case "No": {
                Clicks.click("my_profile.no_button_unsaved_ChangesPanel_overlay");
                break;
            }
            case "Close": {
                Clicks.click("my_profile.close_button_unsaved_ChangesPanel_overlay");
                break;
            }
        }
        logger.info("selected " + option + " in Save Updates Overlay on my profile page");
    }

    @Given("^I should see the profile is not updated$")
    public void iShouldSeeTheProfileIsNotUpdated() throws Throwable {
        iNavigateToMyProfilePage();
        iVerifyMyProfileIsNotUpdated();
    }

    @Then("^I verify my profile is not updated$")
    public void iVerifyMyProfileIsNotUpdated() throws Throwable {
        logger.info("-> Verify profile is updated!!");
        if (bloomingdales()) {
            Clicks.click("my_account.goto_my_profile");
        }
        UserProfile customer = TestUsers.getCustomer(null);
        String capturedFirstName = customer.getUser().getProfileAddress().getFirstName();
        String capturedLastName = customer.getUser().getProfileAddress().getLastName();
        try {
            String updatedFirstName = Elements.getElementAttribute("my_profile.verify_page", "value");
            String updatedLastName = Elements.getElementAttribute("my_profile.last_name", "value");
            Assert.assertTrue("Profile is not Updated", (!(capturedFirstName.equals(updatedFirstName) && capturedLastName.equals(updatedLastName))));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Cannot continue:" + e);
        }
    }

    @Given("^I edit \"([^\"]*)\" field in My Profile Page$")
    public void iEditFieldInMyProfilePage(String arg1) throws Throwable {
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        switch (arg1) {
            case "first_name": {
                TextBoxes.typeTextbox("my_profile.verify_page", customer.getUser().getProfileAddress().getFirstName());
                break;
            }
            case "Last_name": {
                TextBoxes.typeTextbox("my_profile.last_name", customer.getUser().getProfileAddress().getLastName());
                break;
            }
        }
        logger.info("First Name of the MyProfile page is updated");
    }

    @Then("^I should see text \"([^\"]*)\" on my profile page$")
    public void iShouldSeeTextOnMyProfilePage(String message) throws Throwable {
        Wait.untilElementPresent("my_profile.profile_message");
        Assert.assertTrue("Text " + message + " is not displayed on update profile page", Elements.getText("my_profile.profile_message").equals(message));
        logger.info("Text " + message + " is displayed on update profile page");
    }

    @Given("^I should see label (Mailing Address line 1|Mailing Address line 2) on my profile page$")
    public void iShouldSeeLabelMailingAddressLineOnMyProfilePage(String address_label) throws Throwable {
        Wait.forPageReady();
        switch (address_label) {
            case "Mailing Address line 1": {
                Assert.assertTrue("Text " + address_label + " is not displayed on update profile page", Elements.elementPresent("my_profile.address_line_1"));
                break;
            }
            case "Mailing Address line 2": {
                Assert.assertTrue("Text " + address_label + " is not displayed on update profile page", Elements.elementPresent("my_profile.address_line_2"));
                break;
            }
        }
        logger.info("Text " + address_label + " is displayed on update profile page");
    }

    @Given("^I update \"([^\"]*)\" on my profile$")
    public void iUpdateOnMyProfile(String field) throws Throwable {
        iNavigateToMyProfilePage();
        UserProfile customer = TestUsers.getExistingRegistryUser();
        switch (field) {
            case "first_name": {
                customer.getUser().getProfileAddress().setFirstName(TestUsers.generateRandomFirstName());
                break;
            }
            case "last_name": {
                customer.getUser().getProfileAddress().setLastName(TestUsers.generateRandomLastName());
                break;
            }
            case "first_name and last_name": {
                customer.getUser().getProfileAddress().setFirstName(TestUsers.generateRandomFirstName());
                customer.getUser().getProfileAddress().setLastName(TestUsers.generateRandomLastName());
                break;
            }
            case "address_city": {
                customer.getUser().getProfileAddress().setCity(TestUsers.generateRandomString(7));
                break;
            }
        }
        TextBoxes.typeTextbox("my_profile.verify_page", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("my_profile.last_name", customer.getUser().getProfileAddress().getLastName());
        if (Elements.getText("my_profile.address_line_1").isEmpty()) {
            User user = customer.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();
            TextBoxes.typeTextbox("my_profile.address_line_1", profileAddress.getAddressLine1());
            TextBoxes.typeTextbox("my_profile.address_city", profileAddress.getCity());
            if (macys() || MEW()) {
                DropDowns.selectByText("my_profile.address_state", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
            } else {
                DropDowns.selectCustomText("create_profile.address_state_list", "create_profile.state_options", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
                Clicks.clickIfPresent("my_profile.gender_female");
            }
            TextBoxes.typeTextbox("my_profile.address_zip_code", String.valueOf(profileAddress.getZipCode()));
            if (macys()) {
                DropDowns.selectByText("my_profile.gender", user.getGender());
                DropDowns.selectByText("create_profile.security_question", user.getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("create_profile.security_answer", user.getUserPasswordHint().getAnswer());
            }
        }
        if (edge()) {
            Clicks.javascriptClick("my_profile.update_profile_button");
        } else {
            Clicks.click("my_profile.update_profile_button");
        }
        if (macys()) {
            Assert.assertTrue("Profile not updated", Wait.untilElementPresent("my_profile.update_message"));
        } else {
            Assert.assertTrue("Could not update profile", Wait.until(() -> onPage("my_account"), 10));
        }
    }


    @Then("^I (should|should not) see update billing address pop up window$")
    public void iShouldNotSeeUpdateBillingAddressPopUpWindow(String display_type) throws Throwable {
        switch (display_type) {
            case "should": {
                Assert.assertTrue("Update billing address pop up window is not displayed", Elements.elementPresent("my_profile.update_billing_address_dialog"));
                logger.info("Update billing address pop up window is displayed");
                break;
            }
            case "should not": {
                Assert.assertTrue("Update billing address pop up window is displayed", !(Elements.elementPresent("my_profile.update_billing_address_dialog")));
                logger.info("Update billing address pop up window is not displayed");
                break;
            }
        }
    }

    @Given("^I verify my profile$")
    public void iVerifyMyProfile() throws Throwable {
        logger.info("Matching Profile information from customer object and UI!");
        UserProfile customer = TestUsers.getExistingRegistryUser();
        Assert.assertTrue("First Name is mismatched", Elements.getElementAttribute("my_profile.verify_page", "value").equals(customer.getUser().getProfileAddress().getFirstName()));
        Assert.assertTrue("Last Name is mismatched", Elements.getElementAttribute("my_profile.last_name", "value").equals(customer.getUser().getProfileAddress().getLastName()));
        Assert.assertTrue("Address Line 1 is mismatched", Elements.getElementAttribute("my_profile.address_line_1", "value").equals(customer.getUser().getProfileAddress().getAddressLine1()));
        Assert.assertTrue("Address Line 2 is mismatched", Elements.getElementAttribute("my_profile.address_city", "value").equals(customer.getUser().getProfileAddress().getCity()));
    }

    @Then("^I validate the my account page title \"([^\"]*)\"$")
    public void iValidateTheMyAccountPageTitle(String expectedTitle) throws Throwable {
        Assert.assertEquals(PageNavigation.title() + "is not equal to " + expectedTitle, PageNavigation.title(), expectedTitle);
        logger.info(PageNavigation.title() + " is displayed as title for My Account page");
    }

    @Given("^I navigate to registry signin page$")
    public void iNavigateToRegistrySigninPage() throws Throwable {
        Wait.forPageReady();
        Clicks.click("home.goto_wedding_registry");
        Wait.forPageReady();
        Wait.untilElementPresent("home.manage_registry");
        Clicks.click("home.manage_registry");
    }

    @Then("^I verify the attributes of the Registry SignIn Page$")
    public void iVerifyTheAttributesOfTheRegistrySignInPage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Existing user email address field is not displaying", Elements.elementPresent("sign_in.email"));
        Assert.assertTrue("Existing user password field is not displaying", Elements.elementPresent("sign_in.password"));
        if (macys())
            Assert.assertTrue("Existing user sign in button is not displaying", Elements.elementPresent("sign_in.verify_page"));
        else
            Assert.assertTrue("Existing user sign in button is not displaying", Elements.elementPresent("sign_in.sign_in_button"));
        logger.info("Basic attributes of registry sign in page is verified successfully");
    }


    @Then("^I verify the error message on checkout sign in page$")
    public void iVerifyTheErrorMessageOnCheckoutSignInPage(DataTable table) throws Throwable {
        int s = table.raw().size();
        for (int i = 1; i < table.raw().size(); i++) {
            TextBoxes.typeTextbox("checkout.email", table.raw().get(i).get(0));
            Thread.sleep(5000);
            TextBoxes.typeTextbox("checkout.password", table.raw().get(i).get(1));
            Clicks.javascriptClick("checkout.checkout_button");
            Thread.sleep(5000);
            Assert.assertTrue("Error - ENV: Error message is not displayed properly", Elements.findElement("sign_in.error_message").getText().equals(table.raw().get(i).get(2)));
        }
    }

    @Then("^I see the top reviewer badge$")
    public void iSeeTheTopReviewerBadge() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("The top reviewer batch is not visible", Elements.elementPresent("home.top_reviewer_parent"));
        logger.info("Checking the top reviewer badge");
    }

    @When("^I mouse hover on the gamification Top Reviewer badge$")
    public void iMouseHoverOnTheGamificationTopReviewerBadge() throws Throwable {
        Wait.untilElementPresent("home.top_reviewer_span");
        Clicks.click("home.top_reviewer_span");
        logger.info("Perform the mouse hover on gamification Top reviewer badge");
    }

    @Then("^I verify the message for (TopReviewer) badge$")
    public void iVerifyTheMessageForTopReviewerBadge(String text) throws Throwable {
        if (text.equalsIgnoreCase("TopReviewer")) {
            Wait.untilElementPresent("home.top_reviewer_parent");
            Clicks.hoverForSelection("home.top_reviewer_parent");
            Wait.untilElementPresent("home.top_reviewer_child");
            Assert.assertTrue(Elements.getText("home.top_reviewer_child").contains("Write 5 more reviews to become a top reviewer! Get Started"));
        } else {
            Assert.assertTrue(Elements.getText("home.top_reviewer_child").contains("Congrats, you're a top reviewer on macys.com!"));
        }
    }

    @Then("^I should see date of birth auto-populated with valid data$")
    public void validateDateOfBirthFieldvalues() {
        Assert.assertTrue("Error - DOB entered while creating new profile do not match with DOB on My Profile page.",
                new MyProfile().getMonthDayYearFromMyProfilePage());
    }

    @Then("^I verify my preference receive emails \"([^\"]*)\" value should be displayed as \"([^\"]*)\" in DB$")
    public void iVerifyMyPreferenceReceiveEmailsValueShouldBeDisplayedAsInDB(String name, String enabled) throws Throwable {
        String actualValue = CommonUtils.getSubscriptionNameEnabledValue(TestUsers.getExistingRegistryUser().getUser().getProfileAddress().getEmail(), name);
        Assert.assertTrue("User subscription value is NOT correctly displayed in DB", actualValue.equals(enabled));
        logger.info("User subscription value is NOT correctly displayed in DB {" + name + "::" + enabled);
    }

    @Then("^I verify my preference text me \"([^\"]*)\" value should be displayed as \"([^\"]*)\" in DB$")
    public void iVerifyMyPreferenceTextMeValueShouldBeDisplayedAsInDB(String name, String enabled) throws Throwable {
        String actualValue = CommonUtils.getSubscriptionNameEnabledValue(TestUsers.getExistingRegistryUser().getUser().getProfileAddress().getEmail(), name);
        Assert.assertTrue("User subscription value is NOT correctly displayed in DB", actualValue.equals(enabled));
        logger.info("User subscription value is NOT correctly displayed in DB {" + name + "::" + enabled);
    }

    @Then("^I verify send me macys emails preference as \"([^\"]*)\" and text me preference as \"([^\"]*)\" in Notification Preferences section$")
    public void iVerifySendMeMacysEmailsPreferenceAsAndTextMePreferenceAsInNotificationPreferencesSection(String arg1, String arg2) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("navigation.notification_Preferences");
        Clicks.click("navigation.notification_Preferences");
        Wait.untilElementPresent("navigation.email_preference");
        logger.info("Email me preference is set to # {" + Elements.getText("navigation.email_preference"));
        Assert.assertTrue("Email me preference value is not correct", Elements.getText("navigation.email_preference").equals(arg1));
        logger.info("Text me preference is set to # {" + Elements.getText("navigation.text_preference"));
        Assert.assertTrue("Text me preference value is not correct", Elements.getText("navigation.email_preference").equals(arg1));
    }

    @When("^I create a profile by deselecting receive me emails and selecting text me options$")
    public void iCreateAProfileByDeselectingReceiveMeEmailsAndSelectingTextMeOptions() throws Throwable {
        logger.info("Fill data on profile creation page to create new profile");
        TestUsers.clearCustomer();
        CreateProfile.createProfile(TestUsers.getCustomer(null), false, false, false, false, true, false);
        if (!prodEnv() && !onPage("my_account")) {
            Assert.fail("New Profile is not created");
        }
        Wait.forPageReady();
        CreateProfile.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        logger.info("email=" + TestUsers.currentEmail);
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        logger.info("Password=" + TestUsers.currentPassword);
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            CreateProfile.closeSecurityAlertPopUp();
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
        }
        CreateProfile.closeSecurityAlertPopUp();
        Utils.threadSleep(9000, null);
    }

    @When("^I create a profile by de-selecting receive me emails and text me options$")
    public void iCreateAProfileByDeSelectingReceiveMeEmailsAndTextMeOptions() throws Throwable {
        logger.info("Fill data on profile creation page to create new profile");
        TestUsers.clearCustomer();
        CreateProfile.createProfile(TestUsers.getCustomer(null), false, false, false, false, true, true);
        if (!prodEnv() && !onPage("my_account")) {
            Assert.fail("New Profile is not created");
        }
        Wait.forPageReady();
        CreateProfile.closeSecurityAlertPopUp();
        TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail();
        logger.info("email=" + TestUsers.currentEmail);
        TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword();
        logger.info("Password=" + TestUsers.currentPassword);
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            CreateProfile.closeSecurityAlertPopUp();
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
        }
        CreateProfile.closeSecurityAlertPopUp();
        Utils.threadSleep(9000, null);
    }

    @Given("^I verify create profile details in DB$")
    public void iVerifyCreateProfileDetailsInDB() throws Throwable {
        String userId = CommonUtils.getUserDetailsByEmail(TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail());
        Map dbValues = CommonUtils.getUserFirstAndLastAndDOBByUserID(userId);
        Assert.assertTrue("First Name is not matched", dbValues.get("firstNameDB").equals(TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName()));
        Assert.assertTrue("Last Name is not matched", dbValues.get("lastNameDB").equals(TestUsers.getCustomerInformation().getUser().getProfileAddress().getLastName()));
        Assert.assertTrue("Date of Birth is not matched", dbValues.get("DOBDB").equals(TestUsers.getCustomerInformation().getUser().getDateOfBirth()));
        logger.info("Profile information is correctly stored in database");
    }

    @Given("^I sign in with invalid password on registry sign in page$")
    public void iSignInWithInvalidPasswordOnRegistrySignInPage() throws Throwable {
        TextBoxes.typeTextbox("sign_in.email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("sign_in.password", "#$%!@");
        Clicks.javascriptClick("sign_in.verify_page");
    }

    @Then("^I go to the Registry Forgot Password page$")
    public void iGoToTheRegistryForgotPasswordPage() throws Throwable {
        Wait.untilElementPresent("sign.in.forgot_password_link_registry");
        Assert.assertTrue("Forgot password Link is not present", Elements.elementPresent("sign.in.forgot_password_link_registry"));
    }

    @Given("^I update the valid address gender and security answer$")
    public void iUpdateTheValidAddressGenderAndSecurityAnswer() throws Throwable {
        logger.info("-> update user profile!!");
        TestUsers.getCustomer(null).getUser().getProfileAddress();
        TextBoxes.typeTextbox("my_profile.address_line_1", TestUsers.getCustomer(null).getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("my_profile.address_city", TestUsers.getCustomer(null).getUser().getProfileAddress().getCity());
        if (macys() || MEW()) {
            DropDowns.selectByText("my_profile.address_state", ((TestUsers.getCustomer(null).getUser().getProfileAddress().getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(TestUsers.getCustomer(null).getUser().getProfileAddress().getState()) : TestUsers.getCustomer(null).getUser().getProfileAddress().getState()));
        } else {
            DropDowns.selectCustomText("create_profile.address_state_list", "create_profile.state_options", ((TestUsers.getCustomer(null).getUser().getProfileAddress().getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(TestUsers.getCustomer(null).getUser().getProfileAddress().getState()) : TestUsers.getCustomer(null).getUser().getProfileAddress().getState()));
            Clicks.clickIfPresent("my_profile.gender_female");
        }
        TextBoxes.typeTextbox("my_profile.address_zip_code", String.valueOf(TestUsers.getCustomer(null).getUser().getProfileAddress().getZipCode()));
        if (macys()) {
            DropDowns.selectByText("my_profile.gender", TestUsers.getCustomer(null).getUser().getGender());
            DropDowns.selectByText("create_profile.security_question", TestUsers.getCustomer(null).getUser().getUserPasswordHint().getQuestion());
            TextBoxes.typeTextbox("create_profile.security_answer", TestUsers.getCustomer(null).getUser().getUserPasswordHint().getAnswer());
        }
        if (edge()) {
            Clicks.javascriptClick("my_profile.update_profile_button");
        } else {
            Clicks.click("my_profile.update_profile_button");
        }
        if (macys()) {
            Assert.assertTrue("Profile not updated", Wait.untilElementPresent("my_profile.update_message"));
        } else {
            Assert.assertTrue("Could not update profile", Wait.until(() -> onPage("my_account"), 10));
        }
    }

    @Given("^I delete user password hint from DB$")
    public void iDeleteUserPasswordHintFromDB() throws Throwable {
        String userId = CommonUtils.getUserDetailsByEmail(TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail());
        CommonUtils.deletePasswordHintByUserID(userId);
        logger.info("Password hint is deleted for the user with the email: " + TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail());
    }

    @When("^I update the valid security answer$")
    public void iUpdateTheValidSecurityAnswer() throws Throwable {
        Wait.untilElementPresent("sign_in.security_question");
        TestUsers.clearCustomer();
        new MyAccount().setSecurityQuestion();
    }

    @Then("^I should see security Q&A updated in profile$")
    public void iShouldSeeSecurityQAUpdatedInProfile() throws Throwable {
        Wait.forPageReady();
        iNavigateToMyAccountPage();
        iNavigateToMyProfilePage();
        Assert.assertTrue("security Q&A is not updated in profile", Elements.getText("create_profile.security_question_text").equals(TestUsers.getCustomer(null).getUser().getUserPasswordHint().getQuestion()));
        logger.info("Successfully verified security Q&A updated in profile");
    }

    @When("^I sign in with previously credentials generated$")
    public void iVerifyUserCanSignInWithPreviousCredentials() throws Throwable {
        logger.info("-> SignIn to profile!!");
        CommonUtils.gotoSignInPage();
        UserProfile customer = TestUsers.getCustomer(null);
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
    }

    @When("^I click on 'My Preferences' option from left navigation menu$")
    public void I_Click_On_My_Preferences_Option_From_Left_Navigation_Menu()  {
        Wait.secondsUntilElementPresent("customer_pref.left_nav_preferences", 50);
        Clicks.click("customer_pref.left_nav_preferences");
        Wait.forPageReady();
        logger.info("Clicked on the My Preference option form the left navigation menu");
    }

    @Then("^I should see \"([^\"]*)\" as page title$")
    public void I_Should_See_AS_Page_Title(String PageTitle)  {
        Wait.secondsUntilElementPresent("customer_pref.my_preferences_title", 50);
        Assert.assertTrue("Page title of My Preferences landing page is not displayed", Elements.getText("customer_pref.my_preferences_title").contains(PageTitle));
        logger.info("Page title got displayed on the My Preferences landing page");
    }

    @When("^I click on 'Add a Store' option of the Preferred Store$")
    public void I_Click_On_Add_A_Store_Option_Of_The_Preferred_Store()  {
        Wait.secondsUntilElementPresent("customer_pref.add_a_store_option", 50);
        Clicks.click("customer_pref.add_a_store_option");
        logger.info("Clicked on Add a Store option");
    }

    @Then("^I should be navigated to Preferred store page$")
    public void I_Should_Be_Navigated_To_Preferred_Store_Page()  {
        Wait.secondsUntilElementPresent("customer_pref.preferred_store_title", 50);
        Assert.assertTrue("Preferred Store page is not displayed", Elements.elementPresent("customer_pref.preferred_store_title"));
        logger.info("Navigated to Preferred Store page");
    }

    @When("^I enter the ZIP Code into text field$")
    public void I_Enter_The_ZIP_Code_Into_Text_Field() {
        Wait.secondsUntilElementPresent("customer_pref.zip_code_text_field",10);
        TextBoxes.typeTextbox("customer_pref.zip_code_text_field", "10022");
        logger.info("Entered ZIP Code into the text field");
    }

    @And("^I click on the 'Search' button$")
    public void I_Click_On_The_Search_Button()  {
        Clicks.click("customer_pref.search_button_preferred_store");
        Wait.forPageReady();
        logger.info("Clicked on Search Button");
    }

    @And("^I select any store from search results of Preferred store$")
    public void I_Select_Any_Store_From_The_Search_Results_Of_Preferred_Store()  {
        Wait.secondsUntilElementPresent("customer_pref.store_results_first_store", 50);
        Clicks.click("customer_pref.store_results_first_store");
        logger.info("Selected a store from the search results of Preferred store");
    }

    @Then("^I should be navigated to summary state of Preferred store$")
    public void I_Should_Be_Navigated_To_The_Summary_State_Of_Preferred_Store(){
        Wait.secondsUntilElementPresent("customer_pref.store_summary_change_store_option", 50);
        Assert.assertTrue("Summary state of Preferred Store page is not displayed", Elements.elementPresent("customer_pref.store_summary_change_store_option"));
        logger.info("Navigated to Summary state of Preferred store");
    }

    @And("^I should see 'Change Store' option on the summary state of Preferred store$")
    public void I_Should_See_The_Change_Store_Option_On_The_Summary_State_Of_Preferred_Store() {
        Assert.assertTrue("Summary state of Preferred Store page is not displayed", Elements.elementPresent("customer_pref.store_summary_change_store_option"));
        logger.info("Change store option is displayed on the summary state of Preferred store");
    }

    @When("^I click on 'Change Store' option in summary state$")
    public void I_Click_On_Change_Store_Option_In_The_SummaryState() {
        Clicks.click("customer_pref.store_summary_change_store_option");
        logger.info("Clicked on Change Store option in the summary state");
    }

    @Then("^I should navigate to edit state of the Preferred store page$")
    public void I_Should_Navigate_To_Edit_State_Of_Preferred_Store_Page()  {
        Wait.secondsUntilElementPresent("customer_pref.store_edit_selected_store_text", 50);
        Assert.assertTrue("Edit state of Preferred Store page is not displayed", Elements.elementPresent("customer_pref.store_edit_selected_store_text"));
        logger.info("Navigated to edit state of Preferred store page");
    }

    @And("^I should see 'Selected' store on the page$")
    public void I_Should_See_The_Selected_Store() {
        Assert.assertTrue("Edit state of Preferred Store page is not displayed", Elements.elementPresent("customer_pref.store_edit_selected_store_text"));
        logger.info("Selected store is displayed");
    }

    @When("^I sign in to my existing profile and close security question$")
    public void iSignInToMyExistingProfileAndCloseSecurityQuestion() {
        Wait.forPageReady();
        TextBoxes.typeTextbox("sign_in.email", TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", TestUsers.getCustomer(null).getUser().getLoginCredentials().getPassword());
        Clicks.javascriptClick("sign_in.sign_in_button");
        Utils.threadSleep(9000,"Waiting for Complete your profile overlay");
        Clicks.clickIfPresent("sign_in.close_overlay");
        resumePageHangWatchDog();
        new MyAccount().setSecurityQuestion();
    }

    @Then("^I should be able to sign in and view my profile$")
    public void iShouldBeAbleToSignInAndViewMyProfile() throws Throwable {
        String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
        Wait.untilElementPresent(elementName);
        Clicks.click(elementName);
        iSignInToMyExistingProfileAndCloseSecurityQuestion();
        resumePageHangWatchDog();
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        Wait.untilElementPresent("navigation.goto_my_account");
        if (!onPage("create_profile")) {
            Wait.forPageReady();
            Thread.sleep(5000);
            if(bloomingdales())
            {
                Clicks.click("navigation.goto_my_profile");
            }
            else {
                Clicks.hoverForSelection("navigation.goto_my_account");
                Wait.untilElementPresent("navigation.goto_my_profile");
                Clicks.click("navigation.goto_my_profile");
            }
            Wait.untilElementPresent("my_profile.verify_page");
            shouldBeOnPage("my_profile");
        }
        UserProfile customer = TestUsers.getCustomer(null);
        Wait.untilElementPresent("my_profile.verify_page");
        Assert.assertTrue("First name is not same", Elements.getElementAttribute("create_profile.verify_page","value").equals( customer.getUser().getProfileAddress().getFirstName().toString()));
        logger.info("First name is: "+ Elements.getElementAttribute("create_profile.verify_page","value"));
        Assert.assertTrue("Last name is not same", Elements.getElementAttribute("create_profile.last_name","value").equals( customer.getUser().getProfileAddress().getLastName().toString()));
        logger.info("Last name is: "+ Elements.getElementAttribute("create_profile.last_name","value"));
        Assert.assertTrue("Email address is not same", Elements.getElementAttribute("create_profile.email","value").equals( customer.getUser().getProfileAddress().getEmail().toString()));
        logger.info("Email name is: "+ Elements.getElementAttribute("create_profile.email","value"));
        logger.info("Successfully verified the profile data of existing customer");

    }

    @And("^I select (add card to account|apply now|Cardholder Benefits|Apply & Learn More|exclusion and details apply) option$")
    public void iSelectOption(String option) throws Throwable {
        Wait.forPageReady();
        switch(option)
        {
            case "add card to account":
                Clicks.click("my_account.csr_add_card_to_my_account_button");
                break;
            case "apply now":
                Clicks.click("my_account.csr_apply_today_link");
                break;
            case "Cardholder Benefits":
                Clicks.click("my_account.csr_add_card_to_my_account_button");
                break;
            case "Apply & Learn More":
                Clicks.click("my_account.csr_learn_more_link");
                break;
            case "exclusion and details apply":
                Clicks.click("my_account.exclusions_and_details_link");
                break;
        }
    }

    @Then("^I should reach on \"([^\"]*)\" page$")
    public void iShouldReachOnPage(String page) throws Throwable {
        switch(page)
        {
            case "add card fusion":
                Thread.sleep(30000);
                Wait.untilElementPresent("my_account.fusion_card");
                Assert.assertTrue("User is not redirected to "+page, Elements.elementPresent("my_account.fusion_card"));
                break;
        }
    }

    @When("^I \"([^\"]*)\" from the add card fusion page$")
    public void iFromTheAddCardFusionPage(String action) throws Throwable {
        switch(action)
        {
            case "Cancel":
                Clicks.click("credit_service_gateway_signedin.cancel_button");
                break;
            case "Sign Off":
                Clicks.click("credit_service_gateway_signedin.sign_out");
                Wait.untilElementPresent("credit_service_gateway_signedin.sign_off");
                Thread.sleep(5000);
                Clicks.click("credit_service_gateway_signedin.sign_off");
                break;
        }
    }

    @Then("^I should go to \"([^\"]*)\" page$")
    public void iShouldGoToPage(String target_page) throws Throwable {
        Wait.forPageReady();
        switch(target_page)
        {
            case "credit gateway":
                Assert.assertTrue("User is not redirected to Credit Gateway page.",WebDriverManager.getWebDriver().getCurrentUrl().contains("/my-credit/gateway"));
                break;
            case "home page":
                Thread.sleep(5000);
                Wait.untilElementPresent("my_account.home_page_logo");
                Assert.assertTrue("User is not redirected to home page.",Elements.elementPresent("my_account.home_page_logo"));
                break;
        }
    }
}