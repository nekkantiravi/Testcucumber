package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.my_account.SignIn;
import com.macys.sdt.projects.PurchaseAndDelivery.Regression.actions.website.CheckoutActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Checkout extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Checkout.class);

    @When("^I cancel and return to shopping bag page from Paypal review page$")
    public void iCancelAndReturnToShoppingBagPageFromPaypalReviewPage() throws Throwable {
        Wait.forPageReady();
        Clicks.click("paypal_login.cancel_link");
        Wait.forPageReady("shopping_bag");
        logger.info("Cancelled in paypal page and returned to shopping bag page");
    }

    /**
     * Clicks "continue" on the paypal login page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I continue checkout from paypal review page$")
    public void I_continue_checkout_from_paypal_review_page() throws Throwable {
        Wait.untilElementNotPresent("paypal_login.loading_spinner");
        Clicks.javascriptClick("paypal_login.continue");
        logger.info("Continued checkout in paypal review page.");
    }

    @Then("^I should see Paypal option is selected as payment type$")
    public void iShouldSeePaypalOptionIsSelectedAsPaymentType() throws Throwable {
        Wait.untilTextPresent("PayPal can't be used with Gift Cards", 30);
        Wait.untilTextPresent("Contact Information", 30);

        Assert.assertTrue("ERROR - APP : Paypal is not selected in payment section",
                Elements.elementPresent("responsive_order_review_section.paypal_icon")
                        || (Elements.getElementAttribute("responsive_order_review_section.paypal_radio_button", "checked").equals("true")));
        logger.info("Verified that paypal is selected in payment section");
    }

    @Then("^I verify store location is (\\d+)$")
    public void iVerifyStoreLocation(String zipCode) {
        Wait.forPageReady();
        Assert.assertTrue("EEROR:- Store zip code not Match", Navigate.get(Home.class).verifyStore.getText().contains(zipCode));
    }

    @And("^I sign in to my existing registry production profile$")
    public void isSignInToMyExistingRegistryProductionProfile() {
        JSONObject emailJson = CheckoutActions.getProdUserDetails();
        Navigate.get(SignIn.class).login(emailJson.getString("email"), emailJson.getString("password"));
    }

    @Then("^I should see new banner for Sign Up for Emails on International Context Page$")
    public void i_should_see_new_banner_for_Sign_Up_for_Emails_on_International_Context_Page() {
        Assert.assertTrue("ERROR-ENV: Sign Up for Email banner is not displayed on International Context page", Elements.findElement("international_shipping.sign_up_for_emails_image").isDisplayed());
        logger.info("Sign Up for Emails banner is displaying on International Context Page");
    }

    @And("^I select Exclusions Apply link on International Context Page$")
    public void i_select_Exclusions_Apply_Link_on_International_Context_Page() {
        Clicks.javascriptClick("international_shipping.goto_exclusions_apply");
        logger.info("Selected Exclusions Apply link on International Context Page");
    }

    @Then("^I should navigate to exclusions apply page from the link on International Context Page$")
    public void i_should_navigate_to_exclusions_apply_page_from_the_link_on_International_Context_Page() throws Throwable {
        Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("/cms/slp/3/112616doiwelcomeexclusions"));
        logger.info("User is correctly navigated to Email Opt-in Offer Exclusions Page");
    }

    @When("^I update the first and last name with single quotes in payment section$")
    public void i_update_the_first_and_last_name_with_single_quotes_in_payment_section() throws Throwable {

        TextBoxes.typeTextbox("responsive_payment_guest_section.first_name", "Jam'es");
        TextBoxes.typeTextbox("responsive_payment_guest_section.last_name", "Dea'n");
        TextBoxes.typeTextbox("responsive_payment_guest_section.security_code", "204");
        Clicks.click("responsive_payment_guest_section.continue_payment_checkout_button");

        logger.info("Updated the first and last name with single quotes");
    }

    @Then("^I should not see single quotes in billing address first name and last name$")
    public void iVerifyThatSingleQuotesInBllingAddressFirstLastNameIsRemoved() throws Throwable {
        Wait.untilElementPresent("responsive_payment_guest_section.edit_payment_section");
        Clicks.javascriptClick("responsive_payment_guest_section.edit_payment_section");
        Assert.assertFalse("ERROR - APP : First name contains single quote", Elements.getText("responsive_payment_guest_section.first_name").contains("'"));
        Assert.assertFalse("ERROR - APP : Last name contains single quote", Elements.getText("responsive_payment_guest_section.last_name").contains("'"));

        logger.info("Verified that single wuotes are not present in first and last names of billing section");
    }

    @And("^I uncheck use my shipping address checkbox on payment page$")
    public void iUncheckUseMyShippingAddressCheckboxOnPaymentPage() throws Throwable {
        if (macys()) {
            if (Elements.elementPresent("responsive_payment_guest_section.use_shipping_address")) {
                Clicks.unSelectCheckbox("responsive_payment_guest_section.use_shipping_address");
            }
        } else if (Elements.elementPresent("payment_guest.use_shipping_address")) {
            Clicks.unSelectCheckbox("payment_guest.use_shipping_address");
        } else {
            Clicks.click("responsive_payment_guest_section.use_shipping_address");
        }

        logger.info("Unchecked the shipping address checkbox in payment page.");
    }

    @And("^I enter following message in respective gift message field$")
    public void iEnterFollowingMessageInRespectiveGiftMessageField(List<HashMap<String, String>> giftMessages) throws Throwable {
        pausePageHangWatchDog();
        Wait.secondsUntilElementPresent("responsive_checkout.gift_option", 15);
        Wait.secondsUntilElementNotPresent("responsive_checkout.loader_mask", 15);
        Clicks.selectCheckbox("responsive_checkout.gift_option");
        Clicks.selectCheckbox("responsive_checkout.gift_message");
        resumePageHangWatchDog();
        for (Map giftMessage : giftMessages) {
            TextBoxes.typeTextbox("responsive_checkout."+giftMessage.get("field_name").toString(), giftMessage.get("gift_message").toString());
        }
        logger.info("Successfully entered the given gift message in the specified field.");
    }

    @Then("^I should see given messages in respective fields$")
    public void iShouldSeeGivenMessagesInRespectiveFields(List<HashMap<String, String>> giftMessages) throws Throwable {
        for (Map giftMessage : giftMessages) {
            String actual = Elements.findElement("responsive_checkout."+giftMessage.get("field_name").toString()).getAttribute("value");
            String expected = giftMessage.get("gift_message").toString();
            Assert.assertTrue("ERROR - APP : Expected message is not displayed." ,actual.contains(expected));
        }
        logger.info("Successfully verified that given gift message is present in the specified field.");
    }

    @And("^I expand gift options section$")
    public void iExpandGiftOptionsSection() throws Throwable {
        pausePageHangWatchDog();
        Wait.forPageReady();
        Wait.untilElementPresent(signedIn() ? "responsive_checkout_signed_in.change_shipping_address" : "responsive_checkout.gift_option_set");
        Clicks.click((signedIn() ? "responsive_checkout_signed_in.gift_option_expand" : "responsive_shipping_options.gift_option"));
        resumePageHangWatchDog();
        logger.info("Successfully expanded gift options section.");
    }

}