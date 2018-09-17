package com.macys.sdt.projects.Marketing.Loyalty.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Marketing.Loyalty.utils.db.LoyaltyService;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyallistAssociation;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyaltyEnrollment;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.utils.CheckoutUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.HashMap;
import java.util.Map;

import static com.macys.sdt.framework.runner.WebDriverManager.getWebDriver;
import static com.macys.sdt.framework.utils.StepUtils.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoyaltySteps {
    LoyaltyEnrollment loyaltyEnrollment = new LoyaltyEnrollment();
    MyAccountSteps myAccountSteps = new MyAccountSteps();
    PageNavigation pageNavigation = new PageNavigation();
    LoyalistDetails loyallistDetailsFromJson = new LoyalistDetails();
    CheckoutSteps checkoutSteps = new CheckoutSteps();
    LoyaltyService loyaltyService = new LoyaltyService();

    @When("^I enroll into the Loyalty program using existing profile$")
    public void iEnrollIntoTheLoyaltyProgramUsingExistingProfile() throws Throwable {
//        loyaltyEnrollment.signedInUserLoyaltyEnrollment(userProfile);
        myAccountSteps.iNavigateToMyAccountPage();
        Clicks.click("my_account.myaccount_loyalty_become_a_loyallist_link");
        Wait.forPageReady();
        myAccountSteps.iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser("signed_in");
    }

    @And("^I navigate to the loyalty account summary page$")
    public void iNavigateToTheLoyaltyAccountSummaryPage() {
        myAccountSteps.iNavigateToMyAccountPage();
        Clicks.click("my_account.view_my_loyalllist_account");
        StepUtils.shouldBeOnPage("loyallist_account_summary");
    }

    @Then("^I verify the account summary page for the loyallist$")
    public void iVerifyTheAccountSummaryPageForTheLoyallist() {
        Wait.secondsUntilElementPresent("loyallist_account_summary.loyalty_number",10);
        assertTrue("Loayallist Account Number is not displayed on Account Summary page.", Elements.getText("loyallist_account_summary.loyalty_number").contains("L"));
        assertTrue("Loyalty Points is not displayed on Account Summary page.", Integer.parseInt(Elements.getText("loyallist_account_summary.loyallist_earned_reward_points").replace(",",""))>=0);

        assertTrue("Loyalty badge is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.loyalty_badge"));
        assertTrue("Loyalty Points Overlay is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.loyalty_points_overlay"));
//        assertTrue("Loyalty Customer First Name is not displayed on Account Summary page.", Elements.getText("loyallist_account_summary.loyalty_header_name").replace(",", "").toUpperCase().contains(loyallistDetailsFromJson.getLastName().toUpperCase()));
        assertTrue("Loyalty Dog image is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.loyalty_dog"));
        assertTrue("Loyalty Information section is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.account_info"));
        assertTrue("Remove button is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.remove_button"));
        assertTrue("Edit button is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.edit_icon"));
        assertTrue("'Loyallist Number' header text is not displayed on Account Summary page.", Elements.getText("loyallist_account_summary.loyallist_number_header").equals("LOYALLIST NUMBER"));
//        assertTrue("No Pending Points text is not displayed on Account Summary page.", Elements.getText("loyallist_account_summary.no_pending_points").equals("Note: You currently do not have any pending points."));
//        assertTrue("Text 'Question? We're here to help! Please call us at 1-800-600-5402 for information on the Bloomingdale's Loyallist Program or to update your Loyallist account information' is not displayed", Elements.elementPresent("loyallist_account_summary.help_text"));
        assertTrue("Terms and Conditions Text is not displayed on Account Summary page.", Elements.elementPresent("loyallist_account_summary.terms_conditions"));
    }

    @When("^I enroll into the Loyalty program by creating a new profile$")
    public void iEnrollIntoTheLoyaltyProgramByCreatingANewProfile() throws Throwable {
        myAccountSteps.iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser("guest");

    }

    @And("^I navigate to the My Profile Page$")
    public void iNavigateToMyProfilePage() throws Throwable {
        myAccountSteps.iNavigateToMyAccountPage();
        Clicks.click("my_account.goto_my_profile");
        Wait.secondsUntilElementPresent("my_profile.verify_page", 20);
        if (!onPage("my_profile")) {
            Assert.fail("Not navigated to the my profile page");
        }
    }

    @Then("^I should see TEXT MESSAGE PREFERENCES section is not pre populated with any phone number$")
    public void iShouldSeeTextMessagePreferencesSectionIsNotPrePopulatedWithAnyNumber() {
        assertTrue("Text Message Preferences Phone Number Part 1 is pre populated", Elements.getElementAttribute("my_profile.text_message_preference_phone_part1", "value").equals(""));
        assertTrue("Text Message Preferences Phone Number Part 2 is pre populated", Elements.getElementAttribute("my_profile.text_message_preference_phone_part2", "value").equals(""));
        assertTrue("Text Message Preferences Phone Number Part 3 is pre populated", Elements.getElementAttribute("my_profile.text_message_preference_phone_part3", "value").equals(""));
    }

    @Given("^I visit the web site as a loyallist$")
    public void iVisitTheWebSiteAsALoyallist() throws Throwable {
//        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        myAccountSteps.iCreateANewProfile();
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails("base_tier", (loyallistDetails) -> loyallistDetails.getPoints() == 2100);
        associateLoyallistID(loyallistDetailsFromJson);
    }

    @Given("^I visit the web site as a registered user without loyalty$")
    public void iVisitTheWebSiteAsARegisteredUserWithoutLoyalty() throws Throwable {
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        myAccountSteps.iCreateANewProfile();
//        myAccountSteps.iNavigateToMyAccountPage();
    }

    @When("I navigate to the loyalty associate page")
    public void iNavigateToTheLoyaltyAssociatePage() throws Throwable {
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
    }

    @Given("^I visit the web site as a loyallist using \"reward\"$")
    public void iVisitTheWebSiteAsALoyallistWithRewardCard() throws Throwable {
//        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        myAccountSteps.iCreateANewProfile();
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails("reward", (loyallistDetails) -> loyallistDetails.getPoints() == 5100);
        associateLoyallistID(loyallistDetailsFromJson);
    }

    @And("^I checkout to reach the \"([^\"]*)\" page as a \"([^\"]*)\" user$")
    public void iCheckoutUntilIReachThePage(String pageName, String userType) throws Throwable {
        String country = "United States";
        userType = userType.toLowerCase();
        boolean bops = userType.contains("bops");
        boolean signIn = userType.contains("signed in") || signedIn();
        HashMap<String, String> opts = new HashMap<>();

        boolean iship = userType.contains("iship") || !country.equalsIgnoreCase("United States");
        opts.put("country", country);
        opts.put("checkout_eligible", "true");

        if (!(onPage("responsive_checkout", "checkout"))) {
            new CheckoutUtils().navigateToCheckout(signIn, iship);
        }

        // checkout can take some time, page hang watchdog can safely be paused
        pausePageHangWatchDog();
        Checkout checkout = new Checkout(opts, bops);
        if (iship) {
            checkout.ishipCheckout(pageName);
        } else {
            CheckoutUtils.RCPage page = CheckoutUtils.RCPage.fromString(pageName);
            if (signIn) {
                checkout.rcSignedIn(page, opts);
            } else {
                checkout.rcGuest(page, opts);
            }
        }
        resumePageHangWatchDog();
    }

    @Then("^I should see loyalty summary section on Order Confirmation page$")
    public void iShouldSeeLoyaltySummarySectionOnOrderConfirmationPage() throws Throwable {
        checkoutSteps.I_should_see_loyalty_points_section_on_order_conformation_page();
    }

    public void associateLoyallistID(LoyalistDetails loyallistDetails) throws Throwable {
        loyaltyService.removeLoyallistIDAssociation(loyallistDetails.getLoyaltyId());
        LoyallistAssociation.loyaltyAssociation(loyallistDetails);
        Wait.untilElementPresent("loyallist_account_summary.verify_page");
        StepUtils.shouldBeOnPage("loyallist_account_summary");
    }

    @And("I add \"([^\"]*)\" Loyalty Id Through \"Loyallist\" tab from Shipping And Payment page")
    public void iAddLoyaltyIDThroughTabFromShippingAndPaymentPage(String loyallistType) throws Throwable {
        int pointsExpected;
        if (loyallistType.equals("base_tier")) {
            pointsExpected = 2100;
        } else
            pointsExpected = 5100;
        Thread.sleep(3000);
        Wait.forPageReady();
        Clicks.click("responsive_checkout_signed_in.loyallist_section_header");
        Wait.ajaxDone();
        Thread.sleep(1000);
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyallistType, (loyallistDetails) -> loyallistDetails.getPoints() == pointsExpected);
        loyaltyService.removeLoyallistIDAssociation(loyallistDetailsFromJson.getLoyaltyId());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_number", loyallistDetailsFromJson.getLoyaltyId());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_last_name", loyallistDetailsFromJson.getLastName());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_zip_code", loyallistDetailsFromJson.getZipCode());
        Clicks.click("checkout_loyallist_section.save_loyallist_to_my_account");
        Thread.sleep(500);
        Clicks.click("checkout_loyallist_section.apply_button");
        Wait.ajaxDone();
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("checkout_loyallist_section.loyallist_number_value", 15);
        assertTrue("Loyallist ID information entered is not accepted. Error message: " + Elements.getText("checkout_loyallist_section.error_message"), Elements.elementPresent("checkout_loyallist_section.loyallist_number_value"));

    }

    @And("^I add \"([^\"]*)\" Loyalty Id Through \"Loyallist\" tab from Guest Payment page$")
    public void iAddLoyaltyIDThroughTabFromGuestPaymentPage(String loyallistType) throws Throwable {
        int pointsExpected;
        if (loyallistType.equals("base_tier")) {
            pointsExpected = 2100;
        } else
            pointsExpected = 5100;
        Thread.sleep(4000);
        Clicks.click("responsive_checkout.loyallist_section_header");
        Wait.ajaxDone();
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyallistType, (loyallistDetails) -> loyallistDetails.getPoints() == pointsExpected);
        loyaltyService.removeLoyallistIDAssociation(loyallistDetailsFromJson.getLoyaltyId());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_number", loyallistDetailsFromJson.getLoyaltyId());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_last_name", loyallistDetailsFromJson.getLastName());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_zip_code", loyallistDetailsFromJson.getZipCode());
//        Clicks.clickWhenPresent("checkout_loyallist_section.save_loyallist_to_my_account");
        Thread.sleep(500);
        Clicks.click("checkout_loyallist_section.apply_button");
        Wait.ajaxDone();
        Wait.secondsUntilElementPresent("checkout_loyallist_section.loyallist_edit", 60);
        assertTrue("Loyallist ID information entered is not accepted. Error message: " + Elements.getText("checkout_loyallist_section.error_message"), Elements.elementPresent("checkout_loyallist_section.loyallist_number_value"));

    }

    @Then("I should able to see added Loyalty Id on Shipping And Payment page")
    public void iShouldSeeAddedLoyalityIDOnShippingAndPaymentPage() {
        Wait.secondsUntilElementPresent("checkout_loyallist_section.loyallist_number_value", 15);
        assertTrue("Not able to see added Loyallist Number on Shipping and Payment page.", Elements.getText("checkout_loyallist_section.loyallist_number_value").contains("Loyallist Number\nL"));
    }

    @Then("I should able to see added Loyalty Id on Guest Payment page")
    public void iShouldSeeAddedLoyaltyIDOnGuestPaymentPage() {
        assertTrue("Not able to see added Loyallist Number on Shipping and Payment page.", Elements.getText("checkout_loyallist_section.loyallist_number_value").equals("Loyallist Number\n" + loyallistDetailsFromJson.getLoyaltyId()));
    }

    @And("^I navigate to Loyallist Account summary Page from Order Confirmation Page$")
    public void iNavigateToLoyallistAccountSummaryPageFromOrderConfirmationPage() {
        Clicks.click("responsive_order_confirmation.back_to_bag_icon");
        Wait.forPageReady();
        myAccountSteps.iNavigateToMyAccountPage();
        Clicks.click("my_account.view_my_loyalllist_account");
        Wait.forPageReady();
        shouldBeOnPage("loyallist_account_summary");
    }

    @And("^I add \"([^\"]*)\" Loyalty Id Through \"LookupByBCC\" tab from Shipping And Payment page$")
    public void iAddLoyaltyIDThroughLookupBCCTabFromShippingAndPaymentsPage(String loyallistType) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("responsive_checkout.loyallist_section_header", 5);
        Clicks.click("responsive_checkout.loyallist_section_header");
        Wait.ajaxDone();
        Wait.secondsUntilElementPresent("checkout_loyallist_section.bloomingdales_card_radio_button", 60);
        Clicks.click("checkout_loyallist_section.bloomingdales_card_radio_button");
        Wait.ajaxDone();
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyallistType, (loyallistDetails) -> loyallistDetails.getPoints() == 5100);
        loyaltyService.removeLoyallistIDAssociation(loyallistDetailsFromJson.getLoyaltyId());
        TextBoxes.typeTextbox("checkout_loyallist_section.bloomingdales_credit_card_number", loyallistDetailsFromJson.getCardNumber());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_last_name", loyallistDetailsFromJson.getLastName());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_zip_code", loyallistDetailsFromJson.getZipCode());
        Clicks.clickWhenPresent("checkout_loyallist_section.save_loyallist_to_my_account");
        Thread.sleep(500);
        Clicks.click("checkout_loyallist_section.apply_button");
        Wait.ajaxDone();
        Thread.sleep(1000);
        Wait.secondsUntilElementPresent("checkout_loyallist_section.loyallist_edit", 10);
        assertTrue("Loyallist ID information entered is not accepted. Error message: " + Elements.getText("checkout_loyallist_section.error_message"), Elements.elementPresent("checkout_loyallist_section.loyallist_number_value"));

    }

    @Then("^I should see loyalty id on Order Review page$")
    public void iShouldSeeLoyaltyIDOnOrderReviewPage() {
        assertTrue("Added Loyallist Number is not displayed on Responsive Order Review page.", Elements.getText("checkout_loyallist_section.loyallist_number_value").contains("Loyallist Number\nL"));
    }

    @When("^I navigate to Loyallist Account Association page from My Account$")
    public void iNavigateToLoyallistAccountAssociationPage() throws Throwable {
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
    }

    @And("^I can associate my account by loyallist number using \"([^\"]*)\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsingTopTier(String loyallistType) throws Throwable {
        int pointsExpected;
        if (loyallistType.equals("base_tier")) {
            pointsExpected = 2100;
        } else
            pointsExpected = 5100;
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyallistType, (loyallistDetails) -> loyallistDetails.getPoints() == pointsExpected);
        associateLoyallistID(loyallistDetailsFromJson);
    }

    @Then("^I should see Top Tier Free Shipping rebate in \"shopping bag\" page$")
    public void iShouldSeeTopTierFreeShippingRebateInShoppingBagPage() {
        assertTrue("Loyallists get free shipping for all orders!' message is not displayed for Top Tier Loyallist customer on Shopping Bag page.", Elements.elementPresent("shopping_bag.loyallists_get_free_shipping"));
    }

    @Then("^I should see Top Tier Free Shipping rebate in \"shipping & payment\" page$")
    public void iShouldSeeTopTierFreeShippingRebateInShippingAndPaymentPage() {
        Wait.secondsUntilElementPresent("responsive_order_summary.loyallists_get_free_shipping", 60);
        assertTrue("Loyallists get free shipping for all orders!' message is not displayed for Top Tier Loyallist customer on Shipping and Payment page.", Elements.elementPresent("responsive_order_summary.loyallists_get_free_shipping"));
    }

    @Then("^I should see Top Tier Free Shipping rebate in \"order review\" page$")
    public void iShouldSeeTopTierFreeShippingRebateInOrderReviewPage() {
        assertTrue("Loyallists get free shipping for all orders!' message is not displayed for Top Tier Loyallist customer on Order Review page.", Elements.elementPresent("responsive_order_summary.loyallists_get_free_shipping"));
    }


    /**
     * Step to select shipping method on guest responsive checkout page
     **/
    @And("^I select (premium|express) shipping method in shipping and payment page$")
    public void I_select_method_in_shipping_methods(String shippingMethod) throws Throwable {
        String page = onPage("responsive_checkout") ? "responsive_checkout" : "shipping_guest";
        Clicks.clickWhenPresent(page + ".edit_shipping_info");
        Wait.secondsUntilElementPresent(page + ".express_shipping", 60);
        switch (shippingMethod) {
            case "premium":
                Clicks.click(Elements.element(page + ".premium_shipping"));
                break;
            case "express":
                Clicks.click(Elements.element(page + ".express_shipping"));
                break;
        }
        Clicks.clickWhenPresent(page + ".save");
    }


    /**
     * Step to verify shipping method cost on responsive checkout pages when FST promotion is applied
     **/
    @Then("^I should see (premium|express|sdd) shipping price in \"([^\"]*)\" page$")
    public void I_should_see_shipping_cost_in_order_summary_section(String methodCost, String page) throws Throwable {
        Wait.untilElementPresent("responsive_checkout.shipping_fee");
        Utils.threadSleep(2000, "Waiting for page load");
        switch (methodCost) {
            case "premium":
                Assert.assertTrue("Premium shipping method cost is not displaying on " + page, Elements.getText("responsive_order_summary.shipping_fee").contains("$10.00"));
                break;
            case "express":
                Assert.assertTrue("Express shipping method cost is not displaying on " + page, Elements.getText("responsive_order_summary.shipping_fee").contains("$15.00"));
                break;
            case "sdd":
                Assert.assertTrue("SDD shipping method cost is not displaying on " + page, Elements.getText("responsive_order_summary.shipping_fee").contains("$5.00"));
                break;
        }
    }

    @When("^I navigate to the My Wallet page$")
    public void iNavigateToTheMyWalletPage() {
        myAccountSteps.iNavigateToMyAccountPage();
        Wait.secondsUntilElementPresent("navigation.goto_my_wallet_link", 60);
        Clicks.click("navigation.goto_my_wallet_link");
    }

    @Then("^I should see the rewards card section is displayed with no active reward cards$")
    public void iShouldSeeTheRewardsCardSectionWithNoCards() {
        assertTrue("My Reward Cards section is not displayed for the Loyallist customer.", Elements.elementPresent("my_bwallet.reward_cards_container"));
        assertTrue("No Active Reward cards displayed message is not displayed for the Loyallist customer with less than 5000 points.", Elements.elementPresent("my_bwallet.no_active_reward_card_message"));
    }

    @Then("^I should see the rewards card section is displayed properly for the loyallist$")
    public void iShouldSeeTheRewardCardsSectionIsDisplayedWithCards() {
        assertTrue("My Reward Cards section is not displayed for the Loyallist customer.", Elements.elementPresent("my_bwallet.reward_cards_container"));
        assertFalse("No Active Reward cards displayed message is displayed for the Loyallist customer with more than 5000 points.", Elements.elementPresent("my_bwallet.no_active_reward_card_message"));
        assertTrue("Reward Card is not displayed for the Loyallist customer with more than 5000 points.", Elements.elementPresent("my_bwallet.reward_card_numbers"));
    }

    @Then("^I can remove my loyallist number$")
    public void iCanRemoveMyLoyallistNumber() {

        Clicks.click("loyallist_account_summary.remove_button");
        Wait.secondsUntilElementPresent("loyallist_account_summary.remove_confirmation_btn", 60);
        Clicks.click("loyallist_account_summary.remove_confirmation_btn");
        Wait.forPageReady();
        assertTrue("Loyallist Number is not removed successfully.", onPage("loyalty_association"));
    }

    @Then("^I can edit my loyallist account and verify the changes$")
    public void iCanEditMyLoyallistAccountAndVerifyTheChanges() throws Throwable {
        String updatedFirstName = "LTYFN";
        String newAddress1 = "5010 OWENS";
        String newEmail = "UPDATEDEMAIL@BLACKHOLE.MACYS.COM";
        Clicks.click("loyallist_account_summary.edit_icon");
        Wait.untilElementPresent("loyallist_account_summary.edit_loyallist_info_overlay");
        String OrgFirstName = "Dev";
        String OrgAddress1 = "680 Folsom St";
        String OrgEmail = TestUsers.currentEmail;
        TextBoxes.typeTextbox("edit_loyallist_info.edit_address_line1", newAddress1);
        TextBoxes.typeTextbox("edit_loyallist_info.edit_address_email", newEmail);
        TextBoxes.typeTextbox("edit_loyallist_info.edit_first_name", updatedFirstName);
        TextBoxes.typeTextbox("edit_loyallist_info.edit_address_line1", newAddress1);
        TextBoxes.typeTextbox("edit_loyallist_info.edit_address_email", newEmail);
        Clicks.click("edit_loyallist_info.update");
        Thread.sleep(8000);
        Wait.secondsUntilElementPresent("loyallist_account_summary.edit_icon", 60);
        assertTrue("Loyallist First Name is not updated after editing it", Elements.getText("loyallist_account_summary.loyallist_name").split(" ")[0].toUpperCase().equals(updatedFirstName));
        assertTrue("Loyallist Address Line1 is not updated after editing it", Elements.getText("loyallist_account_summary.loyallist_address_line1").toUpperCase().equals(newAddress1));
        assertTrue("Loyallist Email is not updated after editing it", Elements.getText("loyallist_account_summary.loyallist_email").toUpperCase().equals(newEmail));
        Clicks.click("loyallist_account_summary.edit_icon");
        Wait.untilElementPresent("loyallist_account_summary.edit_loyallist_info_overlay");
        TextBoxes.typeTextbox("edit_loyallist_info.edit_first_name", OrgFirstName);
        TextBoxes.typeTextbox("edit_loyallist_info.edit_address_line1", OrgAddress1);
        TextBoxes.typeTextbox("edit_loyallist_info.edit_address_email", OrgEmail);
        Clicks.click("edit_loyallist_info.update");
        Thread.sleep(8000);
        Wait.secondsUntilElementPresent("loyallist_account_summary.edit_icon", 60);
    }

    @Then("^I click the loyallist footer link as a (guest|registered) user$")
    public void iClickTheLoyallistFooteLinkAsUser(String userType) {
        Clicks.click("footer.goto_loyallist");
        Wait.forPageReady();
        shouldBeOnPage(userType.equals("guest") ? "loyalty_home" : "loyalty_enrollment");

    }

    @Then("^I click the loyallist footer link as a loyallist$")
    public void iClickTheLoyallistFooteLinkAsALoyallist() {
        Clicks.click("footer.goto_loyallist");
        Wait.forPageReady();
        shouldBeOnPage("loyallist_account_summary");
    }

    @Then("^I verify the loyalty enrollment confirmation page link (goto_apply_now|continue_shopping)$")
    public void iVerifyTheLoyaltyEnrollmentConfirmationPageAndTheLinks(String linksToTest) throws Throwable {
        Wait.forPageReady();
        Clicks.click("loyalty_enrollment_confirmation." + linksToTest);
        Wait.forPageReady();
        Thread.sleep(1000);

        if (linksToTest.equals("goto_apply_now")) {
            assertTrue("Apply for Credit Card Gateway page is not displayed.", onPage("credit_service_marketing"));
        }
        if (linksToTest.equals("continue_shopping")) {
            assertTrue("Home Page is not displayed.", WebDriverManager.getCurrentUrl().split("com")[1].equals("/"));
        }
    }

    @When("I navigate to the loyalty top of the list page")
    public void iNavigateToTheLoyaltyTopOfTheListPage() {
        Clicks.click("home.goto_my_account_link");
        Wait.forPageReady();
        Clicks.click("navigation.goto_my_perks");
        Wait.secondsUntilElementPresent("navigation.goto_top_of_the_list", 60);
        Clicks.click("navigation.goto_top_of_the_list");
        Wait.forPageReady();

    }

    @Then("I can verify the benefits for being top of the list")
    public void iCanVerifyTheBenefitsForBeingTopOfTheList() throws Throwable {
        shouldBeOnPage("top_of_the_list");
        Clicks.clickWhenPresent("top_of_the_list.foresee_no_thanks");
        assertTrue("'Top of the List' image is not displayed.", Elements.elementPresent("top_of_the_list.top_of_the_list_image"));
        assertTrue("Header Title 'IT’S EVEN BETTER AT THE' is not displayed.", Elements.findElement("top_of_the_list.header_title1").getAttribute("alt").equals("It's even better at the top of the list"));
        assertTrue("Header text 'SPEND $3,500 PER YEAR ON YOUR BLOOMINGDALE’S CREDIT CARD AND ENJOY THE BEST OF LOYALLIST.' is not displayed.", Elements.getText("top_of_the_list.header_text").equals("SPEND $3,500 PER YEAR ON YOUR BLOOMINGDALE’S CREDIT CARD AND ENJOY THE BEST OF LOYALLIST. 1"));
        assertTrue("Content of 'Top Of the List' section is not displayed correctly.", Elements.getText("top_of_the_list.top_of_the_list_content").equals("POINTS FOR EVERY DOLLAR YOU SPEND\n" +
                "on bloomingdales.com and in our stores and outlets with your Bloomingdale's Credit Card. 2\n" +
                "POINTS FOR EVERY DOLLAR YOU SPEND\n" +
                "on all shoes, cosmetics and fragrances at Bloomingdale's and bloomingdales.com with your Bloomingdale's Credit Card. That's Double Points every day! 3\n" +
                "CHOOSE YOUR OWN TRIPLE POINTS DAYS\n" +
                "Pick four days throughout the year to get Triple Points on your purchases in store at Bloomingdale's and online at bloomingdales.com. Register at least one day in advance by calling 866-404-8787 or see a sales associate in store to sign up the same day. 4\n" +
                "UNLIMITED COMPLIMENTARY GIFT WRAP\n" +
                "Top of the List Loyallists deserve the total package—including free gift wrap for all in-store purchases. 5\n" +
                "TOP OF THE LIST ONLY OFFERS\n" +
                "Surprise and delight: Take advantage of special offers exclusively for you.\n" +
                "DINING CLUB\n" +
                "Enjoy an entrée and non-alcoholic beverage on the house (up to $19) every time you spend $100 on your Bloomingdale's Credit Card at Bloomingdale's restaurants. 6\n" +
                "FREE SHIPPING & RETURNS\n" +
                "Shipping’s on us for all online orders every day. Plus, we can ship your in-store purchases home so you don’t have to carry them. 7\n" +
                "5,000 POINTS = $25 REWARD CARD"));
        assertTrue("Content of 'Loyallist logo' is not displayed correctly.", Elements.elementPresent("top_of_the_list.loyallist_logo"));
        assertTrue("Content of 'Bloomingdales Credit Card' image is not displayed correctly.", Elements.elementPresent("top_of_the_list.bloomingdales_credit_card_img"));
        assertTrue("Content of 'Legal Notice and Disclaimer' expand link is not displayed correctly.", Elements.elementPresent("top_of_the_list.legal_notice_and_disclaimer_link"));
        Clicks.click("top_of_the_list.legal_notice_and_disclaimer_link");
        Wait.forPageReady();
        Thread.sleep(3000);
        assertTrue("Content of 'Legal Notice and Disclaimer' is not displayed correctly.", Elements.getText("top_of_the_list.legal_notice_and_disclaimer_content").equals("Legal Notice and Disclaimers\n" +
                "1. Spending $3,500 in net purchases total on your Bloomingdale’s Credit Card in our stores, in our outlets or at bloomingdales.com in a calendar year qualifies you for the Bloomingdale’s Card at the Top of the List that is issued within 60 days after meeting the spend requirement, whereupon points will be earned at the higher earn rate. Subject to credit approval.\n" +
                "2. Points are not earned at Nespresso shops, or on sales tax, store services, restaurants in Bloomingdale’s stores, or when redeeming Bloomingdale’s Gift and Reward Cards. On December 31 of each year, 75% of all remaining points on your account will be forfeited. Reward Cards cannot be exchanged for cash or used to purchase Bloomingdale's Gift Cards or to pay your Bloomingdale's account balance(s). See Loyallist Terms & Conditions.\n" +
                "3. Not available at all stores, Bloomingdale's The Outlet Store/Merchandise or bloomingdales.com. Bloomingdale's reserves the right to terminate these benefits at any time without notice.\n" +
                "4. U.S. Bloomingdale’s stores only. Select leased brands excluded. Shoe, cosmetic and fragrance purchases earn 12 points per dollar. Cannot be combined with other multiplier point offers. Four triple point days of your choice per account, per calendar year. Employees of Bloomingdale's and its affiliates are not eligible.\n" +
                "5. Not available at all stores, bloomingdales.com or at Bloomingdale's The Outlet Store. Purchases must be made with your Bloomingdale's Credit Card at Bloomingdale's. Bloomingdale's may terminate this benefit at any time without notice.\n" +
                "6. Tax and gratuity are excluded. Restaurants in select stores only. Bloomingdale's may terminate this benefit at any time without notice.\n" +
                "7. Purchases must be made with your Bloomingdale’s Credit Card or Loyallist ID. Free shipping for all Loyallists on online orders every day. Valid only on shipments to a single address within the US. See bloomingdales.com/shipping for details. Excludes gift cards, furniture and mattresses."));
        assertTrue("'Terms and Conditions' link is not displayed.", Elements.getText("top_of_the_list.terms_and_conditions").equals("Loyallist Terms & Conditions."));
//        assertTrue("Content of 'Bloomingdales Shipping' link is not displayed correctly.", Elements.getText("top_of_the_list.bloomingdales_shipping").equals("bloomingdales.com/shipping"));
        //        assertTrue("Text 'Use your Top of the List Bloomingdale’s Credit Card and receive four points for every dollar you spend on bloomingdales.com and at our stores and outlets.' is not displayed.", Elements.getText("top_of_the_list.four_points_per_dollar_text").equals("Use your Top of the List Bloomingdale’s Credit Card and receive four points for every dollar you spend on bloomingdales.com and at our stores and outlets.2"));
//        assertTrue("Header 'UNLIMITED COMPLIMENTARY GIFT WRAP' is not displayed.", Elements.getText("top_of_the_list.unlimited_complimentary_gift_wrap_title").equals("UNLIMITED COMPLIMENTARY GIFT WRAP"));
//        assertTrue("Text 'Treat your friends and family to the best presentation for purchases made in store.' is not displayed.", Elements.getText("top_of_the_list.unlimited_complimentary_gift_wrap_text").equals("Treat your friends and family to the best presentation for purchases made in store.3"));
//        assertTrue("Header 'FREE SHIPPING EVERY DAY' is not displayed.", Elements.getText("top_of_the_list.free_shipping_every_day_title").equals("FREE SHIPPING EVERY DAY"));
//        assertTrue("text 'We’ll get it to you for free—whether you’re shopping online or in store.' is not displayed.", Elements.getText("top_of_the_list.free_shipping_every_day_text").equals("We’ll get it to you for free—whether you’re shopping online or in store.4"));
//        assertTrue("Header 'EXCLUSIVE BIRTHDAY OFFER' is not displayed.", Elements.getText("top_of_the_list.exclusive_birthday_offer_title").equals("EXCLUSIVE BIRTHDAY OFFER"));
//        assertTrue("Header Text 'Celebrate your birthday in style with a special offer.' is not displayed.", Elements.getText("top_of_the_list.exclusive_birthday_offer_text").equals("Celebrate your birthday in style with a special offer."));
//        assertTrue("Header 'PERSONAL TRIPLE POINTS DAY' is not displayed.", Elements.getText("top_of_the_list.personal_triple_points_day_title").equals("PERSONAL TRIPLE POINTS DAY"));
//        assertTrue("Header Text 'Choose any day of the year to get Triple Points on all of your purchases (online and in store). Simply call 1-866-404-8787 to schedule it at least one day in advance, or go to any Bloomingdale’s store to activate the offer the same day.' is not displayed.", Elements.getText("top_of_the_list.personal_triple_points_day_text").equals("Choose any day of the year to get Triple Points on all of your purchases (online and in store). Simply call 1-866-404-8787 to schedule it at least one day in advance, or go to any Bloomingdale’s store to activate the offer the same day.5"));
//        assertTrue("Header 'DOUBLE POINTS IN\n" + "BEAUTY & SHOES' is not displayed.", Elements.getText("top_of_the_list.double_points_beauty_shoes_title").equals("DOUBLE POINTS IN\n" + "BEAUTY & SHOES"));
//        assertTrue("Header Text 'Get 8 points for every dollar you spend on shoes, cosmetics & fragrances at Bloomingdale’s with your Bloomingdale's Credit Card.' is not displayed.", Elements.getText("top_of_the_list.double_points_beauty_shoes_text").equals("Get 8 points for every dollar you spend on shoes, cosmetics & fragrances at Bloomingdale’s with your Bloomingdale's Credit Card.6"));
//        assertTrue("Header 'DINING CLUB' is not displayed.", Elements.getText("top_of_the_list.dining_club_title").equals("DINING CLUB"));
//        assertTrue("Header Text 'Get an entrée and non-alcoholic beverage totaling $19 or less on the house every time you spend $100 dining at Bloomingdale’s with your Bloomingdale’s Credit Card.' is not displayed.", Elements.getText("top_of_the_list.dining_club_text").equals("Get an entrée and non-alcoholic beverage totaling $19 or less on the house every time you spend $100 dining at Bloomingdale’s with your Bloomingdale’s Credit Card.7"));
//        assertTrue("Banner '5,000 POINTS = $25 REWARD CARD' is not displayed.", Elements.getText("top_of_the_list.reward_card_banner_text").equals("5,000 POINTS = $25 REWARD CARD 8"));
    }

    @When("^I navigate to the loyalty member benefits page$")
    public void iNavigateToTheLoyaltyMemberBenefitsPage() {
        Clicks.click("home.goto_my_account_link");
        Wait.forPageReady();
        Clicks.click("navigation.goto_my_perks");
        Wait.forPageReady();
    }

    @Then("I verify the My Perks page and the links")
    public void iVerifyTheMyPerksPageAndTheLinks() throws Throwable {
        shouldBeOnPage("my_perks");
//        assertTrue("'Info Exclusions' link is not displayed.", Elements.elementPresent("my_perks.info_exclusions"));
//        assertTrue("Loyallist Terms and Conditions' link is not displayed.", Elements.elementPresent("my_perks.loyallist_terms_and_conditions"));
//        assertTrue("'Apply Now!' link is not displayed.", Elements.elementPresent("my_perks.apply_now"));
//        assertTrue("Terms & Conditions' link is not displayed.", Elements.elementPresent("my_perks.terms_and_conditions"));
//        assertTrue("'Learn More' link is not displayed.", Elements.elementPresent("my_perks.learn_more_link"));
//        assertTrue("Learn More & Apply' link is not displayed.", Elements.elementPresent("my_perks.learn_more_and_apply"));
//        // content of this page changes every release. Hence not feasible to validate the content
//        assertTrue("Header image 'Great Style Should Be Rewarded' is not displayed.", Elements.elementPresent("my_perks.great_style_should_be_rewarded_img"));
//        assertTrue("'My Perks' is not displayed.", Elements.elementPresent("my_perks.my_perks_img"));
//        assertTrue("Header 'THE PERKS' is not displayed.", Elements.getText("my_perks.the_perks_header").equals("THE PERKS"));
//        assertTrue("Header 'ALL LOYALLISTS GET:' is not displayed.", Elements.getText("my_perks.all_loyallists_get").equals("ALL LOYALLISTS GET:"));
//        assertTrue("'AT LEAST ONE POINT FOR EVERY DOLLAR' title is not displayed.", Elements.getText("my_perks.at_least_one_point_for_every_dollar_title").equals("AT LEAST ONE POINT FOR EVERY DOLLAR"));
//        assertTrue("'spent at our stores, outlets and bloomingdales.com1' text is not displayed.", Elements.getText("my_perks.at_least_one_point_for_every_dollar_text").equals("AT LEAST ONE POINT FOR EVERY DOLLAR spent at our stores, outlets and bloomingdales.com1"));
//        assertTrue("'DOUBLE POINTS' title is not displayed.", Elements.getText("my_perks.double_points_title").equals("DOUBLE POINTS"));
//        assertTrue("Text 'on all shoe, cosmetic and fragrance purchases at Bloomingdale's every day1' is not displayed.", Elements.getText("my_perks.double_points_text").equals("DOUBLE POINTS on all shoe, cosmetic and fragrance purchases at Bloomingdale's every day1"));
//        assertTrue("'A $25 REWARD CARD' title is not displayed.", Elements.getText("my_perks.a_25_reward_card_title").equals("A $25 REWARD CARD"));
//        assertTrue("Text ' every time you reach 5,000 points2' is not displayed.", Elements.getText("my_perks.a_25_reward_card_text").equals("A $25 REWARD CARD every time you reach 5,000 points2"));
//        assertTrue("'A PERK OF THE MONTH:' title is not displayed.", Elements.getText("my_perks.a_perk_of_the_month_title").equals("A PERK OF THE MONTH:"));
//        assertTrue("Text ' A special offer sent to your inbox each month3' is not displayed.", Elements.getText("my_perks.a_perk_of_the_month_text").equals("A PERK OF THE MONTH: A special offer sent to your inbox each month3"));
//        assertTrue("'FREE SHIPPING' title is not displayed.", Elements.getText("my_perks.free_shipping_title").equals("FREE SHIPPING"));
//        assertTrue("Text ' every day with no minimum purchase required4' is not displayed.", Elements.getText("my_perks.free_shipping_text").equals("FREE SHIPPING every day with no minimum purchase required4"));
//
//        assertTrue("'Terms and Conditions' link is not displayed.", Elements.getText("my_perks.terms_and_conditions").equals("Terms & Conditions"));
//        Wait.secondsUntilElementPresent("my_perks.terms_and_conditions", 60);
//        Navigate.scrollPage(0, 550);
//        String primaryWinHandle = WebDriverManager.getWebDriver().getWindowHandle();
//        Elements.findElement("my_perks.terms_and_conditions").sendKeys(Keys.ENTER);
//        Wait.forPageReady();
////        WebDriver driver = WebDriverManager.getWebDriver();
////        for (String winHandle : driver.getWindowHandles()) {
////            driver.switchTo().window(winHandle);
////        }
//        Navigate.switchWindow(1);
//        Wait.secondsUntilElementPresent("loyallist_terms_and_conditions.terms_and_conditions_header", 60);
//        assertTrue("Failed to open Terms and Conditions page", onPage("loyallist_terms_and_conditions"));
//        Navigate.switchWindowClose();
//        assertTrue("'FAQs' link is not displayed.", Elements.getText("my_perks.faq").equals("FAQs"));
//        Elements.findElement("my_perks.faq").sendKeys(Keys.ENTER);
//        Wait.forPageReady();
//
//        assertTrue("Doesn't navigate to FAQ page", WebDriverManager.getCurrentUrl().contains("cm_sp=my_account-_-loyallist-_-faqs"));
//        Navigate.browserBack();
//        Wait.forPageReady();
//        assertTrue("'TOP OF THE LIST' title is not displayed.", Elements.getText("my_perks.top_of_the_list_title").equals("TOP OF THE LIST"));
//        assertTrue("Text 'The most points. The most perks. The very best of Loyallist. See why it’s better at the top and find out how to get there.' is not displayed", Elements.getText("my_perks.top_of_the_list_text").equals("The most points. The most perks.\n" +
//                "The very best of Loyallist. See why it’s better at the top and find out how to get there."));
//        assertTrue("'LEARN MORE' link is not displayed.", Elements.getText("my_perks.learn_more_link").equals("LEARN MORE"));
//        Navigate.scrollPage(0, 250);
//        Clicks.click("my_perks.learn_more_link");
//        Wait.forPageReady();
//        Wait.secondsUntilElementPresent("top_of_the_list.top_of_the_list_image", 60);
//        assertTrue("'Top of The List' page is not displayed", onPage("top_of_the_list"));
//        Navigate.browserBack();
//        Wait.secondsUntilElementPresent("my_perks.verify_page", 120);
//        Wait.forPageReady();
//        assertTrue("'WANT EVEN MORE—EVEN FASTER?' title is not displayed.", Elements.getText("my_perks.want_even_more_even_faster_title").equals("WANT EVEN MORE—EVEN FASTER?"));
//        assertTrue("Text 'Shop with a Bloomingdale’s Credit Card and get three points per dollar1 at Bloomingdale’s, exclusive cardholder offers and more. Subject to credit approval.' is not displayed", Elements.getText("my_perks.want_even_more_even_faster_text").equals("Shop with a Bloomingdale’s Credit Card and get three points per dollar1 at Bloomingdale’s, exclusive cardholder offers and more. Subject to credit approval."));
//        assertTrue("'LEARN MORE & APPLY' link is not displayed.", Elements.getText("my_perks.learn_more_and_apply").equals("LEARN MORE & APPLY"));
//        Clicks.click("my_perks.learn_more_and_apply");
//        Wait.forPageReady();
//        assertTrue("'Apply Credit Card' launch page is not displayed", WebDriverManager.getCurrentUrl().contains("/CRS/acq/launch"));
//        Navigate.browserBack();
//        Wait.forPageReady("my_perks");
//        assertTrue("'1. U.S. Bloomingdale's stores only..' term/condition is not displayed.", Elements.getText("my_perks.tcs_bloomingdales_stores_only").equals("1. U.S. Bloomingdale's stores only. Double Points are not earned on shoe purchases from Bloomingdale's the Outlet Store or the outlet store page of bloomingdales.com. Points are not earned in Nespresso shops, at restaurants in Bloomingdale's, on store services, sales tax, or when redeeming Bloomingdale's Gift and Reward Cards. On December 31 of each year, 75% of all remaining points on your account will be forfeited. See Bloomingdale's Loyallist Program Terms and Conditions for complete details at bloomingdales.com/loyallist/termsandconditions"));
//        assertTrue("'2. Reward Cards cannot be redeemed in Nespresso shops..' term/condition is not displayed.", Elements.getText("my_perks.tcs_reward_cards_cannot_be_redeemed").equals("2. Reward Cards cannot be redeemed in Nespresso shops, exchanged for cash, used to purchase Bloomingdale's Gift Cards, or used as payment on Bloomingdale's account balance(s). Your Total Points Balance must equal at least 5,000 points to receive a Reward Card."));
//        assertTrue("'4. Purchases must be made with your Bloomingdale's Credit Card or Loyallist ID..' term/condition is not displayed.", Elements.getText("my_perks.tcs_must_have_an_email_address").equals("3. Must have an email address in Loyallist profile. One offer per account per month. Bloomingdale's is not responsible for undeliverable emails. Add your email address at bloomingdales.com/loyallist"));
//        assertTrue("'4. Purchases must be made with your Bloomingdale's Credit Card or Loyallist ID..' term/condition is not displayed.", Elements.getText("my_perks.tcs_purchages_must_be_made_with_your_bloomingdales").equals("4. Purchases must be made with your Bloomingdale's Credit Card or Loyallist ID. Free standard shipping on bloomingdales.com purchases shipped to a single U.S. address, excluding Bloomingdale's Gift Cards, furniture, and mattresses. Offer not valid on prior purchases. In-store shipping offer only applies to merchandise that is not available in the store where the order is placed, but is available at another Bloomingdale's Department Store location. Additional delivery charges may apply for expedited shipping. Bloomingdale's may terminate this benefit at any time without notice. Free return shipping everyday on online orders. Offer excludes gift cards, furniture, mattresses, mirrors, lamps and wall art. Only valid when shipping within the U.S.\n" +
//                "Bloomingdale’s may terminate these benefits at any time without notice."));
//        assertTrue("Link to Bloomingdales Loyallist page is not displayed", Elements.getElementAttribute("my_perks.bloomingdales_loyallist_link", "href").equals("http://www.bloomingdales.com/loyallist"));
    }

    @When("^I navigate to the loyalty landing page$")
    public void iNavigateToTheLoyaltyLandingPage() {
        Clicks.click("footer.goto_loyallist");
        Wait.forPageReady();
        shouldBeOnPage("loyalty_home");
    }

    @Then("I verify the loyalty landing page and the links")
    public void iVerifyTheLoyaltyLandingPageAndTheLinks() {
        assertTrue("'Loyallist' logo is not displayed", Elements.findElement("loyalty_home.loyallist_logo").getAttribute("alt").equals("Bloomingdale's Loyallist"));
        assertTrue("'Everyone's invited' banner is not displayed", Elements.findElement("loyalty_home.everyone_is_invited").getAttribute("alt").equals("Everyone's invited"));
        assertTrue("'The most rewarding way to shop ...' text is not displayed", Elements.getText("loyalty_home.the_most_rewarding_way").equals("The most rewarding way to shop is just a few clicks away. Sign up in seconds (it's free!), and you'll be on your way to earning rewards and enjoying some pretty amazing perks—no matter how you pay!"));
        assertTrue("'Already Have a Bloomingdales.com Account?' banner is not displayed", Elements.getText("loyalty_home.already_have_a_bloomingdales_account").equals("ALREADY HAVE A BLOOMINGDALES.COM ACCOUNT?"));
        assertTrue("'Email' textbox is not displayed", Elements.findElement("loyalty_home.email").getAttribute("name").equals("email"));
        assertTrue("'Password' text is not displayed", Elements.findElement("loyalty_home.password").getAttribute("name").equals("password"));
        assertTrue("'Please Sign In' text is not displayed", Elements.getText("loyalty_home.please_sign_in").equals("Please sign in."));
        assertTrue("'Sign in' button is not displayed", Elements.findElement("loyalty_home.sign_in").getAttribute("value").equals("Sign In"));
        assertTrue("'Forgot Your Password?' link is not displayed", Elements.getText("loyalty_home.goto_forgot_password").equals("Forgot Your Password?"));

        assertTrue("'DON'T HAVE A BLOOMINGDALES.COM ACCOUNT?' banner is not displayed", Elements.getText("loyalty_home.dont_have_a_bloomingdales_account").equals("DON'T HAVE A BLOOMINGDALES.COM ACCOUNT?"));
        assertTrue("'Create one now and sign up to become a Loyallist.' text is not displayed", Elements.getText("loyalty_home.create_one_now_to_become_a_loyallist").equals("Create one now and sign up to become a Loyallist."));
        assertTrue("'Create Account & Enroll Now' button is not displayed", Elements.getText("loyalty_home.create_account_enroll_now").equals("CREATE ACCOUNT & ENROLL NOW"));
        assertTrue("Bloomingdales Credit Card image is not displayed", Elements.elementPresent("loyalty_home.bloomingdales_card_img"));
        assertTrue("Bloomingdales Dog image is not displayed", Elements.elementPresent("loyalty_home.bloomingdales_dog_img"));
        assertTrue("'Have a Bloomingdale's Credit Card or Loyallist number?' text is not displayed", Elements.getText("loyalty_home.have_a_bloomingdales_credit_card").equals("Have a Bloomingdale's Credit Card or Loyallist number? Then you're already a Loyallist. Create a bloomingdales.com account now to manage your Loyallist account online."));
        assertTrue("'Create Account' link is not displayed", Elements.getText("loyalty_home.create_account").equals("CREATE ACCOUNT"));
        assertTrue("'JOIN TODAY & GET:' section content is not displayed", Elements.getText("loyalty_home.join_today_and_you_get_content").equals("JOIN TODAY & GET:\n" +
                "AT LEAST ONE POINT for every dollar spent at our stores, outlets and bloomingdales.com1\n" +
                "DOUBLE POINTS on all shoes, cosmetics and fragrances every day2\n" +
                "A $25 REWARD CARD every time you reach 5,000 points3\n" +
                "A PERK OF THE MONTH offer sent to your inbox each month4\n" +
                "DOUBLE, TRIPLE AND POWER POINTS during exclusive events5\n" +
                "FREE SHIPPING every day with no minimum purchase required6\n" +
                "TERMS & CONDITIONS FAQs"));


        assertTrue("' Want even more—Even Faster?' banner is not displayed", Elements.getText("loyalty_home.want_even_more_even_faster").equals("WANT EVEN MORE—EVEN FASTER?"));
        assertTrue("'Shop with a Bloomingdale's Credit Card and get three points per dollar, exclusive cardholder offers and more.1 Subject to credit approval.' text is not displayed", Elements.getText("loyalty_home.shop_with_bloomingdale_card").equals("Shop with a Bloomingdale's Credit Card and get three points per dollar, exclusive cardholder offers and more.1"));
        assertTrue("'Bloomingdales Double Credit Cards' image is not displayed", Elements.findElement("loyalty_home.bloomingdales_double_cards_img").getAttribute("alt").equals("Bloomingdale's Credit Card"));
        assertTrue("'Terms & Conditions' link is not displayed", Elements.getText("loyalty_home.terms_and_conditions").equals("TERMS & CONDITIONS"));
        assertTrue("'FAQs' link is not displayed", Elements.getText("loyalty_home.faq").equals("FAQs"));
        assertTrue("'Learn More & Apply Now' link is not displayed", Elements.getText("loyalty_home.learn_more_apply_now").equals("LEARN MORE & APPLY NOW"));
    }

    @Then("^I attempt to navigate to the loyalty account summary page as a guest user$")
    public void iAttemptToNavigateToTheLoyaltyAccountSummaryPageAsAGuestUser() {
        Clicks.click("home.goto_my_account_link");
        Wait.forPageReady();
        Clicks.click("navigation.goto_my_points");
        shouldBeOnPage("sign_in");
    }

    @When("^I sign out from profile$")
    public void iSignOutFromProfile() throws Throwable {
        Clicks.hoverForSelection("header.goto_my_account_link");
        Thread.sleep(1000);
        Clicks.click("header.goto_sign_out_link");
    }

    @Then("^I sign in from the loyalty landing page$")
    public void iSignInFromTheLoyaltyLandingPage() {
        TextBoxes.typeTextbox("loyalty_home.email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("loyalty_home.password", TestUsers.currentPassword);
        Clicks.click("loyalty_home.sign_in");
        shouldBeOnPage("loyalty_enrollment");
    }

    @And("^I should see \"You are already enrolled in Bloomingdale's Loyallist program. You can view your rewards below.\" text in account summary page$")
    public void iShouldSeeYouAreAlreadyEnrolledMessage() {

        assertTrue("Message 'You are already enrolled in Bloomingdale's Loyallist program. You can view your rewards below.' is not displayed", Elements.getText("loyallist_account_summary.error_message").equals("You are already enrolled in Bloomingdale's Loyallist program. You can view your rewards below."));

    }

    @And("^I navigate to loyallist landing page from footer link$")
    public void iNavigateToLoyallistLandingPageFromFooterLink() {
        Clicks.click("footer.goto_loyallist");
        Wait.forPageReady();
        shouldBeOnPage("loyalty_home");

    }

    @And("^I signed in with existing loyallist profile from sign in & enroll option$")
    public void iSignedInWithExistingLoyallistProfileFromSignInEnrollOptions() {
        TextBoxes.typeTextbox("loyalty_home.email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("loyalty_home.password", TestUsers.currentPassword);
        Clicks.click("loyalty_home.sign_in");
        Wait.forPageReady();
    }

    @Then("^I should be navigated to Loyalty account summary page$")
    public void iShouldBeNavigatedToLoyaltyAccountSummaryPage() {
        shouldBeOnPage("loyallist_account_summary");
    }


    @And("^I open create profile and enroll option from new loyallist landing page$")
    public void iOpenCreateProfileAndEnrollOptionFromNewLoyallistLandingPage() {
        Clicks.click("loyalty_home.create_account_enroll_now");
        Wait.forPageReady();
    }

    @Then("^I verify that the \"Loyallist Enrollment Page\" page is rendered$")
    public void iVerifyThatTheLoyallistEnrollmentPageIsRendered() {
        assertTrue("When clickedo on 'Create Account Enroll Now' button, Loyallist Enrollment page is not displayed", onPage("loyalty_enrollment"));

    }

    @And("^I should see question mark after phone number present in the loyalty enrollment page$")
    public void iShouldSeeQuestionMarkAfterPhoneNumber() {
        assertTrue("Question Mark symbol next to Phone Number is not displayed", Elements.elementPresent("loyalty_enrollment.phone_hint_guest"));
    }

    @And("^I should see link for terms and conditions text present in the loyalty enrollment page$")
    public void iShouldSeeLinkForTermsAndCondtionsTextPresentInTheLoyaltyEnrollmentPage() {
        assertTrue("'Terms & Conditions' link is not displayed", Elements.getText("loyalty_enrollment.goto_terms_and_conditions").equals("Terms & Conditions"));

    }

    @And("I navigate to loyalty bonus offers page")
    public void iNavigateToLoyaltyBonusOffersPage() {
        Clicks.click("navigation.goto_bonus_offers");
        Wait.forPageReady();
    }

    @Then("^I should be in Sales & promotions page$")
    public void iShouldBeInSalesAndPromotionsPage() {
        shouldBeOnPage("my_offers");
    }

    @And("^I should see loyalty offers$")
    public void iShouldSeeLoyaltyOffers() {
        assertTrue("Loyallist header for loyallist offers section is not displayed", Elements.getText("my_offers.loyallist_offers_header").equals("LOYALLIST"));
        assertTrue("'Free Shipping for Loyallists' offer under loyallist offers section is not displayed", Elements.getText("my_offers.free_shipping_offer_header").equals("FREE SHIPPING FOR LOYALLISTS"));
    }

    @Then("^I can verify the loyalty edit account field error messages:$")
    public void iCanVerifyTheLoyaltyEditAccountFieldErrorMessages(DataTable errorValidations) throws Throwable {

        Thread.sleep(3000);
        for (Map<String, String> validationMap : errorValidations.asMaps(String.class, String.class)) {
            String field = validationMap.get("field");
            String input = validationMap.get("invalid_data");
            String expError = validationMap.get("error");
            Wait.forPageReady();
            System.out.println("field: "+field+"data: "+input);
            getWebDriver().findElement(Elements.element("loyallist_account_summary.edit_icon")).click();
            Wait.secondsUntilElementPresent("loyallist_account_summary.edit_loyallist_info_overlay", 20);
            if (field.equals("edit_address_state")) {
                Thread.sleep(2000);
                getWebDriver().findElement(Elements.element("edit_loyallist_info.edit_address_state_dropdwon")).click();
                Wait.ajaxDone();
                Elements.findElements("edit_loyallist_info.edit_address_state_list").stream().filter(a -> a.getText().equals("State")).findFirst().get().click();
                Wait.ajaxDone();
            } else {
                Elements.findElement("edit_loyallist_info." + field).click();
                Elements.findElement("edit_loyallist_info." + field).sendKeys(Keys.CONTROL + "a");
                Elements.findElement("edit_loyallist_info." + field).sendKeys(Keys.DELETE);
                Elements.findElement("edit_loyallist_info." + field).sendKeys(input);
            }

            getWebDriver().findElement(Elements.element("edit_loyallist_info.update")).click();
            Wait.ajaxDone();
            String actErrorElement = field + "_error";
            assertTrue("Expected error message " + expError + " is not displayed for field " + field, Elements.getText("edit_loyallist_info." + actErrorElement).equals(expError));
            getWebDriver().findElement(Elements.element("edit_loyallist_info.cancel")).click();
            Thread.sleep(1000);
        }

    }


    @Then("^I can associate my account by credit card number using \"([^\"]*)\"$")
    public void iCanAssociateMyAccountByCreditCardNumberUsing(String loyalist_type) throws Throwable {
        int pointsExpected;
        if (loyalist_type.equals("base_tier")) {
            pointsExpected = 2100;
        } else
            pointsExpected = 5100;
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyalist_type, (loyallistDetails) -> loyallistDetails.getPoints() == pointsExpected);
        loyaltyService.removeLoyallistIDAssociation(loyallistDetailsFromJson.getLoyaltyId());
        myAccountSteps.iShouldBeAbleToAssociateMyAccountByLoyallistNumberUsingDetails(loyalist_type);
    }


    @And("^I add \"([^\"]*)\" Loyalty Id Through \"LookupByBCC\" tab from Guest Payment page$")
    public void iAddLoyaltyIDThroughLookupByBCCTabFromGuestPaymentPage(String loyallistType) throws Throwable {
        Wait.secondsUntilElementPresent("responsive_checkout.loyallist_section_header", 60);
        Clicks.click("responsive_checkout.loyallist_section_header");
        Wait.ajaxDone();

        Wait.secondsUntilElementPresent("checkout_loyallist_section.bloomingdales_card_radio_button", 60);
        Clicks.click("checkout_loyallist_section.bloomingdales_card_radio_button");
        Wait.ajaxDone();

        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyallistType, (loyallistDetails) -> loyallistDetails.getPoints() == 5100);
        loyaltyService.removeLoyallistIDAssociation(loyallistDetailsFromJson.getLoyaltyId());
        TextBoxes.typeTextbox("checkout_loyallist_section.bloomingdales_credit_card_number", loyallistDetailsFromJson.getCardNumber());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_last_name", loyallistDetailsFromJson.getLastName());
        TextBoxes.typeTextbox("checkout_loyallist_section.loyallist_zip_code", loyallistDetailsFromJson.getZipCode());

        Clicks.click("checkout_loyallist_section.apply_button");
        Wait.ajaxDone();

        Wait.secondsUntilElementPresent("checkout_loyallist_section.loyallist_edit", 60);
        assertTrue("Error lookup BCC card: " + Elements.getText("checkout_loyallist_section.error_message"), Elements.elementPresent("checkout_loyallist_section.loyallist_number_value"));

    }

    @And("^Lookup LoyaltyID overlay should be displayed when edited$")
    public void lookupLoyaltyIDOverlayShouldBeDisplayedWhenEdited() throws Throwable {
        // click on Edit
        assertTrue("Edit link doesn't exist", Elements.elementPresent("checkout_loyallist_section.loyallist_edit"));
        Wait.forPageReady();
        Clicks.click("checkout_loyallist_section.loyallist_edit");
        Wait.ajaxDone();

        //Verify lookup overlay displayed
        assertTrue("Loyalist number textbox not displayed", Elements.elementPresent("checkout_loyallist_section.loyallist_number"));
        assertTrue("Loyalist last name not displayed", Elements.elementPresent("checkout_loyallist_section.loyallist_last_name"));
        assertTrue("Loyalist zipcode not displayed", Elements.elementPresent("checkout_loyallist_section.loyallist_zip_code"));
        assertTrue("Loyalist Number radio buttion not displayed", Elements.elementPresent("checkout_loyallist_section.loyallist_radio_button"));
        assertTrue("BCC radio button not displayed", Elements.elementPresent("checkout_loyallist_section.bloomingdales_card_radio_button"));

        //click on Cancel button
        Clicks.click("checkout_loyallist_section.cancel_button");
        Wait.ajaxDone();

        //verify Loyalist ID displayed
        assertTrue("Loyalist ID failed to displayed", Elements.elementPresent("checkout_loyallist_section.loyallist_number_value"));
    }

//    @Given("^I sign in with user having \"([^\"]*)\" added in profile$")
//    public void iSignInWithUserHavingAddedInProfile(String accountType) throws Throwable {
//        iSignInWithUser(accountType, "profile");
//    }

    @Given("^I sign in with user having \"([^\"]*)\" added in (wallet|profile)$")
    public void iSignInWithUser(String accountType, String pagename) throws Throwable {
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials = getCityUserCredentials(accountType);
        PageNavigation pageNavigationObj = new PageNavigation();
        pageNavigationObj.I_visit_the_web_site_as_a_guest_user();
        MyAccountSteps myAccountObj = new MyAccountSteps();
        myAccountObj.iNavigateToMyAccountPage();
        TextBoxes.typeTextbox("sign_in.email", loginCredentials.get("email").toString());
        TextBoxes.typeTextbox("sign_in.password", loginCredentials.get("password").toString());
        Clicks.click("sign_in.verify_page");
        Wait.untilElementNotPresent("sign_in.verify_page");
//        Wait.forPageReady();
    }

    public static Map getCityUserCredentials(String cardType) {
        HashMap<String, String> credentials = new HashMap<>();
        String env;

        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("user_data.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(cardType);
            credentials.put("email", json3.get("email").toString());
            credentials.put("password", json3.get("password").toString());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return credentials;
    }
}

