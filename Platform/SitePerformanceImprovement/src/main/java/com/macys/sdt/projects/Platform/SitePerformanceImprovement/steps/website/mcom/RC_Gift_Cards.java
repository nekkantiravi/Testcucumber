package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.utils.CheckoutUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
//import org.assertj.core.api.SoftAssertions;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Created by YC04026 on 8/10/2017.
 */
public class RC_Gift_Cards extends StepUtils {

    //SoftAssertions softly = new SoftAssertions();

    @And("^I (should|should not) see Gift card section in responsive checkout page$")
    public void iShouldSeeGiftCardSection(String giftSection) {
        if(giftSection.equals("should")) {
            Wait.untilElementPresent("checkout.rc_gift_cards_section");
            Assert.assertTrue("Gift card section is not displaying", Elements.elementPresent("checkout.rc_gift_cards_section"));
        }
        else
            Assert.assertTrue("Gift card section should not displaying",!Elements.elementPresent("checkout.rc_gift_cards_section"));
    }

    @And("^I verify Apply Gift Cards header is displaying in Gift Cards section for \"([^\"]*)\" user$")
    public void iVerifyApplyGiftCardsHeader(String userType){
        String user = userType.toLowerCase();
        if(user.equals("signed in")) {
            Wait.untilElementPresent("checkout.rc_gift_cards_header");
            Assert.assertTrue("Gift card header is not displaying", Elements.elementPresent("checkout.rc_gift_cards_header"));
            Assert.assertTrue("Gift card header Text is not as expected", Elements.findElement("checkout.rc_gift_cards_header").getText().equals("Apply Gift Cards"));
        }else
            Wait.secondsUntilElementPresent("checkout.rc_guest_gift_card_section", 20);
            Assert.assertTrue("Gift card header for guest is not displaying", Elements.elementPresent("checkout.rc_guest_section_header"));
            Assert.assertTrue("Gift card header Text for guest is not as expected", Elements.findElement("checkout.rc_guest_section_header").getText().equals("Apply Gift & Rewards Cards"));
    }

    @And("^I expand gift cards section$")
    public void iExpandGiftCardSection(){
       Assert.assertTrue("Gift card section should not displaying", Elements.elementPresent("checkout.gift_card_expand"));
        Clicks.click("checkout.rc_gift_cards_section");

    }

    @And("^I verify the dispaly of (card number|cid|captcha input|captcha image) field$")
    public void i_verify_gift_card_fields(String field) throws Throwable {
        String fieldType = field.toLowerCase();
        Wait.untilElementPresent("checkout.gift_card_cancel_button");

        switch (fieldType) {
            case "card number":
                if (Elements.elementPresent("checkout.rc_gift_card_field")) {
                    Assert.assertTrue("Card number field is not enabled", Elements.findElement("checkout.rc_gift_card_field").isEnabled());
                }
                break;
            case "cid":
                if (Elements.elementPresent("checkout.rc_gift_card_CID")) {
                    Assert.assertTrue("CID number field is not enabled", Elements.findElement("checkout.rc_gift_card_CID").isEnabled());
                }
                break;
            case "captcha image":
                Assert.assertTrue("Captcha field is not displaying", Elements.elementPresent("checkout.gift_card_captcha_image"));
                break;
            case "captcha input":
                if (Elements.elementPresent("checkout.rc_gift_card_captcha_input")) {
                    Assert.assertTrue("Captcha input field is not enabled", Elements.findElement("checkout.rc_gift_card_captcha_input").isEnabled());
                }
                break;
            default:
                Assert.fail("Filed is not displaying in Gift Card Section: " + field);
                break;
        }
    }

    @And("^I verify \"([^\"]*)\" button is enabled$")
    public void i_verify_gift_section_buttons(String button){
        String buttonType = button.toLowerCase();

        switch (buttonType){
            case "captcha refresh":
                Assert.assertTrue("Captcha refresh button is not enabled", Elements.findElement("checkout.gift_card_captcha_refresh").isEnabled());
                break;
            case "apply gift card":

                Assert.assertTrue("Apply gift card button is not enabled", Elements.findElement("checkout.rc_apply_gift_card_button").isEnabled());
                break;
            case "cancel":
                Assert.assertTrue("Captcha refresh button is not enabled", Elements.findElement("checkout.gift_card_cancel_button").isEnabled());
                break;
            case "remove":
                Assert.assertTrue("Remove button is not enabled", Elements.findElement("checkout.gift_card_remove_button").isEnabled());
                break;
            case "apply another card":
                Assert.assertTrue("APPLY ANOTHER CARD is enabled", !Elements.findElement("checkout.rc_apply_another_card_button").isEnabled());
                break;
            case "apply card":
                Assert.assertTrue("Apply Card button is not enabled", Elements.findElement("responsive_payment_guest_section.add_gift_card_button").isEnabled());
                break;
            case "apply":
                Assert.assertTrue("Apply button is not enabled", Elements.findElement("checkout.rc_guest_apply_button").isEnabled());
                break;
            case "edit":
                Assert.assertTrue("Edit button is not enabled", Elements.findElement("responsive_payment_guest_section.edit_payment_info").isEnabled());
            default:
                Assert.fail("Button is not displayin on RC page: "+ button);
        }
    }

    @And("^I verify \"([^\"]*)\" button is disabled$")
    public void iVerifyApplyAnotherCardstate(String buttonType)
    {
        String button = buttonType.toLowerCase();
        switch (button) {
            case "apply gift card":
                Assert.assertTrue("APPLY ANOTHER CARD is enabled", !Elements.findElement("checkout.rc_apply_another_card_button").isEnabled());
                break;
            case "expand":
                Assert.assertTrue("Expand button is enabled", !Elements.elementPresent("checkout.gift_card_expand"));
                break;
            case "apply card":
                Assert.assertTrue("Apply Card button is enabled", !Elements.elementPresent("responsive_payment_guest_section.add_gift_card_button"));
            default:
                System.out.println("Button name is not existed: "+ button);

        }
    }

    @And("^I click Cancel and verify Gift card section is collapsed for (registered|guest) user$")
    public void iverifyGiftCardSectionIsCollapsed(String user) {

        if(user.toLowerCase().equals("registered")) {
            Clicks.click("checkout.gift_card_cancel_button");
            Wait.untilElementPresent("checkout.rc_gift_cards_header");
            Assert.assertTrue("GiftCard Section is not collapsed", Elements.elementPresent("checkout.rc_gift_cards_header"));
        }
        else {
            Clicks.click("checkout.rc_guest_cancel_button");
            Wait.untilElementPresent("checkout.rc_guest_gift_card_section");
            Assert.assertTrue("Gift card header is not displaying", Elements.elementPresent("checkout.rc_guest_gift_card_section"));
        }
    }

    @And("^I should see Gift Card disclaimer text in Gift Card section as \"([^\"]*)\"$")
    public void iVerifyGiftCardDisclaimer(String expectedNote) {
        Assert.assertTrue("Paypal disclaimer text not displayed", Elements.elementPresent("responsive_gift_cards_section.gc_paypal_note"));
        Assert.assertTrue("Expected Paypal disclaimer text is not displayed", Elements.findElement("responsive_gift_cards_section.gc_paypal_note").getText().contains(expectedNote));
    }

    @Then("^I validate the applied Gift card amount in Confirmation page$")
    public void iValidateAppliedGiftCardAmount() {
        Wait.untilElementPresent("checkout.gift_card_confirmation");
        Assert.assertTrue("Applied Gift Card amount is not displaying", Elements.elementPresent("checkout.gift_card_confirmation"));
    }

    @And("^I verify masked card number and value on checkout page$")
    public void iVerifyMaskedGiftCardNumber(){
        Assert.assertTrue("Card number is not displaying", Elements.elementPresent("checkout.masked_gift_card_number"));
    }

    @And("^I verify \"([^\"]*)\" text displaying for \"([^\"]*)\" user$")
    public void iVerifyTextInGiftCardSection(String displayedText, String userType){
        String user = userType.toLowerCase();
        if(user.equals("registered"))
            Assert.assertTrue("Displayed text is not match", Elements.findElement("checkout.gift_card_limit_text").getText().equals(displayedText));
        else
        System.out.println("text is displaying ***************"+Elements.findElement("checkout.gift_card_limit_text_guest").getText());
            Assert.assertTrue("Displayed text is not matched", Elements.findElement("checkout.gift_card_limit_text_guest").getText().equals(displayedText));
    }

    @And("^I should verify the Gift Card and CID global error$")
    public void iVerifyGiftCardGlobalError(){
        String item = Elements.findElement(Elements.element("my_subscription.chk_global_error")).getText();
        // softly.assertThat(item.contains("Please check your card number and CID and try again.")).as("chk_global_error").isEqualTo(true);
    }

    @When("^I click on Apply card in gift card section$")
    public void i_click_on_apply_card_button(){
        CheckoutUtils.RCPage page = CheckoutUtils.RCPage.PAYMENT;
        Wait.untilElementPresent(page + ".add_gift_card_button");
        Clicks.click(page + ".add_gift_card_button");
        Wait.forPageReady();
    }

    //------------------------------Guest user scenarios ---------------------------------



    @And("^I should see Gift Card disclaimer text in Gift Card section for guest user as \"([^\"]*)\"$")
    public void iVerifyGiftCardDisclaimerForGuest(String expectedNote) {
        Assert.assertTrue("Paypal disclaimer text not displayed", Elements.elementPresent("checkout.rc_paypal_disclaimer_guest"));
        Assert.assertTrue("Expected Paypal disclaimer text is not displayed", Elements.findElement("checkout.rc_paypal_disclaimer_guest").getText().contains(expectedNote));
    }
    @Then("^I (should|should not) see Gift Card section is displaying$")
    public void i_validate_guest_gift_card_section(String giftSection){
        if(giftSection.toLowerCase().equals("should")){
            Assert.assertTrue("Gift card header is not displaying", Elements.elementPresent("checkout.rc_guest_gift_card_section"));
        }
        else
            Assert.assertTrue("Gift card header should not displaying", !Elements.elementPresent("checkout.rc_guest_gift_card_section"));
    }

    @And("^I should see the paypal icon$")
    public void i_should_see_paypal_icon(){
        Assert.assertTrue("Paypal icon is not displaying", Elements.elementPresent("responsive_order_review_section.paypal_icon"));
    }

    @And("^I should see \"([^\"]*)\" field$")
    public void i_validate_paypal_phoneNumber_field(String field){
        String fieldType = field.toLowerCase();
        switch (fieldType) {
            case "phone number":
                Assert.assertTrue("Phone number field is not displayig", Elements.findElement("responsive_payment_signin_section.phone_number").isEnabled());
                Assert.assertTrue("Phone number field is empty", Elements.findElement("responsive_payment_signin_section.phone_number").findElement(By.className("error_msg")).getText().equals("Please enter a phone number."));
                break;
            case "email address":
                Assert.assertTrue("Email address field is not displayig", Elements.findElement("responsive_payment_signin_section.payment_email").isEnabled());
               // Assert.assertTrue("Email address field is empty", Elements.findElement(By.className("error_msg")).getText().equals("Please enter a phone number."));
                break;
        }
    }

    @And("^I validate Payement section is dispalying$")
    public void i_validate_paypal_payment(){
        Wait.secondsUntilElementPresent("checkout.rc_guest_gift_card_section", 20);
        Assert.assertTrue("Payment section is not displaying", Elements.elementPresent("checkout.rc_guest_gift_card_section"));
    }

    @Then("^I should see paypal email is displaying under paypal icon$")
    public void i_should_see_email_address(){
        pausePageHangWatchDog();
        Assert.assertTrue("Paypal section is not visible on order confirmation page",
                Wait.untilElementPresent("responsive_order_confirmation.paypal_section"));
        Assert.assertEquals("Paypal Email is not correct on order confirmation page",
                TestUsers.getPayPalInformation().get("email"), Elements.getText("responsive_order_confirmation.paypal_email_text"));
        resumePageHangWatchDog();

    }

    @And("^I should see macys email and phone number is displaying under Contact info header$")
    public void i_should_see_macys_email_and_phoneNumber(){
        Assert.assertTrue("Contact email is not dispaying", Elements.elementPresent("checkout.rc_paypal_contact_email"));
        Assert.assertTrue("Contact phone number is not displaying", Elements.findElement("checkout.rc_paypal_contact_phoneNumber").getText().contains("/d"));
    }

}
