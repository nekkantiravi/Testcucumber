package com.macys.sdt.projects.Marketing.UFT.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyallistAssociation;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class UFTMarketing extends StepUtils {

    private String currentEmail = TestUsers.currentEmail;
    private String currentPwd = TestUsers.currentPassword;
    private static final Logger LOGGER = LoggerFactory.getLogger(UFTMarketing.class);

    @When("^I navigate to Plenti Sign in page by using direct url$")
    public void iNavigateToPlentiSignInPageByUsingDirectUrl() {
        String plenti_url = RunConfig.url + "/account/signin?program=usl";
        Navigate.visit(plenti_url);
        shouldBeOnPage("plenti_sign_in");
        LOGGER.info("Successfully naviated to plenti Sign in page");
    }

    @Then("^I verify UI changes on Plenti Sign in page$")
    public void iVerifyUIChangesOnPlentiSignInPage() {
        Assert.assertTrue("Join Plenti using your Macy's account text is not present in Sign in page", Elements.getText("plenti_sign_in.join_plenti_text").equals("Join Plenti using your Macy's account"));
        Assert.assertTrue("Email address label text is not present in Sign in page", Elements.getText("plenti_sign_in.email_address_label").equals("Email address"));
        Assert.assertTrue("Password label text is not present in Sign in page", Elements.getText("plenti_sign_in.password_label").equals("Password"));
        Assert.assertTrue("Password is case sensitive. text is not present in Sign in page", Elements.getText("plenti_sign_in.password_info_text").equals("Password is case sensitive."));
        Assert.assertTrue("Create a Macy's account & join Plenti today! text is not present in Sign in page", Elements.getText("plenti_sign_in.create_macys_plenti_account_text").equals("Create a Macy's account & join Plenti today!"));
        Assert.assertTrue("Privacy Practices text is not present in Sign in page", Elements.getText("plenti_sign_in.privacy_practices").equals("Macy's Privacy Practices"));
        LOGGER.info("UI changes verification success on Plenti Sign in page");
    }

    @And("^I signin with existing profile from my account dropdown to navigate to \"([^\"]*)\" page$")
    public void iSigninWithExistingProfileFromMyAccountDropdownToNavigateToPage(String page) {
        LOGGER.info("Selecting " + page + " link from My Account dropdown");
        Clicks.hoverForSelection("header.goto_my_account_link");
        Wait.untilElementPresent("my_account_dialog.myaccount_dropdown");
        Elements.findElement("my_account_dialog." + page).click();
        TextBoxes.typeTextbox("sign_in.email", currentEmail);
        TextBoxes.typeTextbox("sign_in.password", currentPwd);
        Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
        new MyAccount().setSecurityQuestion();
        switch (page) {
            case "my_wallet":
                shouldBeOnPage("oc_my_wallet");
                break;
        }
        LOGGER.info("Successfully signed with valid existing profile and navigated to " + page + " page");
    }

    @Then("^I should see hashed email address hE and hE2 tag in bright tag$")
    public void iShouldSeeHashedEmailAddressHEAndHE2TagInBrightTag() {
        Assert.assertTrue("ERROR - APP: Hashed Email hE key is not populated in Bright Tag",
                ((Map) Navigate.execJavascript("return MACYS.brightTag")).containsKey("hE"));
        Assert.assertTrue("ERROR - APP: Hashed Email hE2 key is not populated in Bright Tag",
                ((Map) Navigate.execJavascript("return MACYS.brightTag")).containsKey("hE2"));

        LOGGER.info("Hashed Email address hE and hE2 tags are populated in Bright Tag");
    }

    @And("^I signin with existing profile from \"([^\"]*)\" page$")
    public void iSigninWithExistingProfileFromPage(String page) {
        Clicks.click("header.goto_sign_in_link");
        TextBoxes.typeTextbox("sign_in.email", currentEmail);
        TextBoxes.typeTextbox("sign_in.password", currentPwd);
        Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
        new MyAccount().setSecurityQuestion();
        switch (page) {
            case "search results":
                shouldBeOnPage("search_result");
                break;
            case "stores":
                shouldBeOnPage("stores");
                break;
            case "browse":
                shouldBeOnPage("category_browse");
                break;
            case "splash":
                shouldBeOnPage("category_splash");
                break;
        }
        LOGGER.info("Successfully signed with valid existing profile and navigated to " + page + " page");
    }

    @And("^I look up with invalid loyalty type \"([^\"]*)\" in guest checkout page$")
    public void iLookUpWithInvalidLoyaltyTypeInGuestCheckoutPage(String loyalty_type) throws Throwable {
        Wait.untilElementPresent("responsive_checkout.go_to_loyalist");
        Clicks.clickLazyElement("responsive_checkout.go_to_loyalist");
//        LoyalistDetails loyalistDetails = TestUsers.getLoyallistDetails(loyalty_type);
        LoyalistDetails loyalistDetails = LoyallistAssociation.getLoyallistDetails(loyalty_type);
        StringBuilder loyaltyNumber = new StringBuilder(loyalistDetails.getLoyaltyId());
        StringBuilder invalidLoyaltyNumber = loyaltyNumber.replace(2, 3, "9");
        TextBoxes.typeTextbox("responsive_checkout.go_to_loyalist_number", invalidLoyaltyNumber.toString());
        TextBoxes.typeTextbox("responsive_checkout.go_to_loyalist_last_name", loyalistDetails.getLastName());
        TextBoxes.typeTextbox("responsive_checkout.go_to_loyalist_zip_code", loyalistDetails.getZipCode());
        Clicks.click("responsive_checkout.go_to_apply");
        LOGGER.info("Guest loylaty lookup with invalid loyalty details completed");

    }

    @Then("^I should see \"([^\"]*)\" error message on guest RC checkout payment page$")
    public void iShouldSeeErrorMessageOnGuestRCCheckoutPaymentPage(String errMsg) throws Throwable {
        Wait.untilElementPresent("responsive_checkout.loyalty_err_msg");
        Assert.assertTrue("Loyalty error element not found", Elements.findElement("responsive_checkout.loyalty_err_msg").getText().equals(errMsg));
    }
    @Then("^I look up with invalid loyalty type \"([^\"]*)\" in Signed checkout page$")
    public void iLookUpWithInvalidLoyaltyTypeInSignedCheckoutPage(String loyalty_type) throws Throwable {
        Clicks.clickLazyElement("responsive_checkout_signed_in.go_to_loyalist_signed_user");
//        LoyalistDetails loyalistDetails = TestUsers.getLoyallistDetails(loyalty_type);
        LoyalistDetails loyalistDetails = LoyallistAssociation.getLoyallistDetails(loyalty_type);
        StringBuilder loyaltyNumber = new StringBuilder(loyalistDetails.getLoyaltyId());
        StringBuilder invalidLoyaltyNumber = loyaltyNumber.replace(2, 3, "9");
        TextBoxes.typeTextbox("responsive_checkout_signed_in.go_to_loyalist_number_signed_user", invalidLoyaltyNumber.toString());
        TextBoxes.typeTextbox("responsive_checkout_signed_in.go_to_loyalist_last_name_singed_user", loyalistDetails.getLastName());
        TextBoxes.typeTextbox("responsive_checkout_signed_in.go_to_loyalist_zip_code_signed_user", loyalistDetails.getZipCode());
        Clicks.click("responsive_checkout_signed_in.go_to_apply_signed_user");
        LOGGER.info("Signed loylaty lookup with invalid loyalty details completed");
    }

    @Then("^I should see \"([^\"]*)\" error message on Signed RC checkout payment page$")
    public void iShouldSeeErrorMessageOnSignedRCCheckoutPaymentPage(String errMessage) throws Throwable {
        Wait.untilElementPresent("responsive_checkout_signed_in.loyalty_err_msg");
        Assert.assertTrue("Loyalty error element not found", Elements.findElement("responsive_checkout_signed_in.loyalty_err_msg").getText().equals(errMessage));
    }

   /* @And("^I verify the new legal notice text in the footer:$")
    public void i_verify_the_new_legal_notice_text_in_the_footer(List<String> expectedLegalCopy) {
        expectedLegalCopy.forEach(legalCopy ->
                Assert.assertTrue("ERROR - APP: Legal notice copy is not updated in footer ", Elements.getText("footer.legal_disclaimer_text").contains(legalCopy)));
        LOGGER.info("Legal notice copy is updated in footer");
    } */

    @When("^I enter a zip code and search on our Stores Events Page$")
    public void iEnterAZipCodeAndSearchOnOurStoresEventsPage() throws Throwable {
        String zipcode= "94105";
        Wait.secondsUntilElementPresent("store_events.store_events_search", 20);
        TextBoxes.typeTextbox("store_events.store_events_search", zipcode);
        Clicks.javascriptClick("store_events.search_event");
        Wait.secondsUntilElementPresent("store_events.store_error_msg", 20);
        Assert.assertFalse("Store event results are not displayed with zipcode:" + zipcode, Elements.elementPresent("store_events.store_error_msg"));
    }

    @Then("^I verify date and timing of the event should be displayed in sequential pattern$")
    public void iVerifyDateAndTimingOfTheEventShouldBeDisplayedInSequentialPattern() throws Throwable {
        Wait.secondsUntilElementPresent("store_events.sort_date",20);
        Clicks.javascriptClick("store_events.sort_date");
        List<Date> sortedList;
        List<Date> listStores = new ArrayList<Date>();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
        Wait.secondsUntilElementPresent("store_events.store_event_list",20);
        listStores = Elements.findElements("store_events.store_event_list").stream()
                .map(e -> e.getText()).map(e -> {
                    try {
                        return dateFormatter.parse(e);
                    } catch (java.text.ParseException exp) {
                        exp.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        sortedList=listStores.stream().collect(Collectors.toList());
        Collections.sort(sortedList);
        Assert.assertTrue("Dates are not displaying in sequential pattern", sortedList.equals(listStores));
    }

    @Then("^I verify the url in canonical tag contains expected value \"([^\"]*)\"$")
    public void iVerifyTheUrlInCanonicalTagContainsExpectedValue(String expVal) throws Throwable {
        Assert.assertTrue("ERROR - APP: The url in canonical tag doesnot contains the expected value",
                (Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href")).contains(expVal));
        LOGGER.info("Verified the url in canonical tag and it contains the expected value as " + expVal);
    }

    @When("^I click apply button without entering promo code$")
    public void iClickApplyButtonWithoutEnteringPromoCode() {
        Clicks.click("shopping_bag.apply_promo_code_button");
        LOGGER.info("Clicked on Apply now button on Shopping Bag ");
    }

    @Then("^I should see the error message \"([^\"]*)\" on shopping bag page$")
    public void iShouldSeeTheErrorMessage(String errorMessage) {
        String promoInlineErrorMessage = "ERROR - APP: Promo Inline error message" + errorMessage + " is not displayed on Bag Page";
        String globalErrorMessage = "ERROR - APP: Global error message" + errorMessage + " is not displayed on Bag Page";
        Wait.untilElementPresent(Elements.element("shopping_bag.error_message"));
        Assert.assertTrue(promoInlineErrorMessage, Elements.getText("shopping_bag.promo_inline_error_message").equals(errorMessage));
        Assert.assertTrue(globalErrorMessage, Elements.getText("shopping_bag.error_message").equals(errorMessage));
        LOGGER.info("Verified promo inline error message and global error message on bag page");
    }


}