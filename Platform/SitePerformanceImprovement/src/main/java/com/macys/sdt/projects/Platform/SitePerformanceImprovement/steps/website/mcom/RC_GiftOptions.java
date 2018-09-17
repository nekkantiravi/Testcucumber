package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config.RcHelper;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.OrderConfirmation;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ContactInfoSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ShippingOptions;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ShippingSection;
import com.macys.sdt.shared.actions.website.bcom.pages.CheckoutPageBcom;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static com.macys.sdt.framework.interactions.Elements.elementPresent;
import static com.macys.sdt.framework.runner.WebDriverManager.*;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config.RcHelper.validatingDataField;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config.RcHelper.validatingEmptyData;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config.RcHelper.verifySpecialCharOrNumber;

/**
 * Created by yc04026 on 8/1/2017.
 */
public class RC_GiftOptions extends StepUtils {

    public  static Map<String, String> sdDetails;
    public static String PaypalEmail;
    public static String ShippingMethod;

    @Then("^I should see selected gift option indicators in (checkout|order confirmation) page$")
    public void iShouldSeeSelectedGiftOptionIndicatorsInOrderConfirmationPage(String pageName) {
        boolean isDisplayed;
        if (pageName.equals("checkout")) {
            ShippingSection shippingSection = Navigate.get(ShippingSection.class);
            isDisplayed = shippingSection.giftMessageLine1Lable.exists() && shippingSection.hidePricesIndicator.exists();
            Assert.assertEquals("Gift message is not as expected", shippingSection.giftMessageLine1Lable.getText(), "test message");
        } else {
            OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class, true);
            isDisplayed = orderConfirmation.giftMessageLine1Lable.exists() && orderConfirmation.hidePricesIndicator.exists();
            Assert.assertEquals("Gift message is not as expected", orderConfirmation.giftMessageLine1Lable.getText(), "test message");
        }
        Assert.assertTrue("Gift option indicators are not displaying in " + pageName + " page", isDisplayed);
    }


    @Then("^I should see selected gift option indicators in (checkout|order confirmation) page for SDD product$")
    public void iShouldSeeSelectedGiftOptionIndicatorsInOrderConfirmationPageForSddProduct(String pageName) {
        boolean isDisplayed;
        if (pageName.equals("checkout")) {
            ShippingSection shippingSection = Navigate.get(ShippingSection.class);
            isDisplayed = shippingSection.hidePricesIndicator.exists();
            Assert.assertTrue("Hide price gift option should be visible",isDisplayed);
            Assert.assertTrue("Gift message should not be displaying", !(shippingSection.giftMessageLine1Lable.exists()));
        } else {
            OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class, true);
            isDisplayed = orderConfirmation.hidePricesIndicator.exists();
            Assert.assertTrue("Gift message should not be displaying", !(orderConfirmation.giftMessageLine1Lable.exists()));
        }
        Assert.assertTrue("Gift option indicators are not displaying in " + pageName + " page", isDisplayed);
    }



    @And("^I expand select gift options for signed in user$")
    public void iExpandSelectGiftOptionsForSignedInUser() {
        pausePageHangWatchDog();
        ShippingOptions shippingOptions = Navigate.get(SignedInCheckout.class).shippingSection().shippingOptions();
        Assert.assertTrue(Wait.until(shippingOptions.giftOptionsExpandSignedIn::exists));
        shippingOptions.giftOptionsExpandSignedIn.click();
        Assert.assertTrue(Wait.until(shippingOptions.giftMessage::exists));
        shippingOptions.giftMessage.click();
        Assert.assertTrue(Wait.until(shippingOptions.giftMessageField1::exists));
        shippingOptions.giftMessageField1.sendKeys("test message");
        //shippingOptions.giftBox.set(true);
        shippingOptions.giftOptionsSaveSignedIn.click();
        Wait.forLoading(By.id("rc-mask"));
        resumePageHangWatchDog();
    }



    @And("I should see Go Green \"([^\"]*)\" on checkout page")
    public void I_should_see_go_green_message_on_checkout_page(String message) {
        Wait.secondsUntilElementPresent("checkout.go_green", 15);
        Assert.assertTrue("Go Green! Message is not displaying on Responsive Checkout page", Elements.getText("checkout.go_green").equals(message));
    }

    @And("^I expand gift options for signed in user$")
    public void iExpandGiftOptionsForSignedInUser() {
        pausePageHangWatchDog();
        ShippingOptions shippingOptions = Navigate.get(SignedInCheckout.class).shippingSection().shippingOptions();
        Assert.assertTrue(Wait.until(shippingOptions.giftOptionsExpandSignedIn::exists));
        shippingOptions.giftOptionsExpandSignedIn.click();

    }

    @And("^I select gift options and gift box for signed in user$")
    public void iSelectGiftOptionsAndGiftBoxForSignedInUser() {
        pausePageHangWatchDog();
        ShippingOptions shippingOptions = Navigate.get(SignedInCheckout.class).shippingSection().shippingOptions();
        Assert.assertTrue(Wait.until(shippingOptions.giftMessage::exists));
        shippingOptions.giftMessage.click();
        Assert.assertTrue(Wait.until(shippingOptions.giftMessageField1::exists));
        shippingOptions.giftMessageField1.sendKeys("test message");
        shippingOptions.giftBox.set(true);
        shippingOptions.giftOptionsSaveSignedIn.click();
        Wait.forLoading(By.id("rc-mask"));
        resumePageHangWatchDog();
    }

    @Then("^I should see below global warning \"([^\"]*)\" on the page$")
    public void iShouldSeeBelowMessageOnPage(String message) throws Throwable {
        Wait.secondsUntilElementPresent("checkout.global_warning_messages", 15);
        String warning_message = Elements.getText("checkout.gift_wrappable_warning");
        System.out.println(warning_message);
        Assert.assertTrue("Gift Wrap message is not displayed on page", warning_message.contains(message));
    }

    @Then("^I verify (shipping address|shipping method|gift options) section for Signed In user should not present")
    public void iVerifyShippingAddressSectionNotPresentForSignedInUser(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "shipping address":
                Assert.assertTrue(Wait.until(() -> signedInCheckout.shippingAddressInfo.exists()));
                Assert.assertTrue("Shipping Address secion is displaying", !(elementPresent("responsive_shipping_section.rc-at-shipping-address")));
                break;
            case "shipping method":
                Assert.assertTrue(Wait.until(() -> signedInCheckout.shippingMethodInfo.exists()));
                Assert.assertTrue("Shipping Method secion is displaying",!(elementPresent("responsive_shipping_section.change_shipping_method")));
                break;
            case "gift options":
                Assert.assertTrue(Wait.until(() -> signedInCheckout.giftOptionsInfo.exists()));
                Assert.assertTrue("Gift Options secion is displaying",!(elementPresent("shippingOptions.giftOptionsExpandSignedIn")));
                break;
            case "contact info":
                Assert.assertTrue(Navigate.get(ContactInfoSection.class).contactInfoSection.exists());
        }
    }


    @Then("^I verify (shipping address|shipping method|gift options|contact info) section not present")
    public void iVerifyShippingAddressSectionNotPresent(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "shipping address":
                Assert.assertTrue("Shipping Address section is displaying", !elementPresent("responsive-checkout_signed_in.shipping_address_info"));
                break;
            case "shipping method":
                Assert.assertTrue("Shipping method section is displaying", !elementPresent("responsive-checkout_signed_in.shipping_method_info"));
                break;
            case "gift options":
                Assert.assertTrue("Gift Option section is displaying", !elementPresent("responsive-checkout_signed_in.gift_options_info"));
                break;
            case "contact info":
                Assert.assertFalse(Navigate.get(ContactInfoSection.class).contactInfoSection.isDisplayed());
        }
    }

    @Then("^I verify (shipping address|shipping method|gift options|contact info) section should present")
    public void iVerifyGiftOptionsSectionShouldPresent(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "shipping address":
                Assert.assertTrue("Shipping Address section is displaying", elementPresent("responsive-checkout_signed_in.shipping_address_info"));
                break;
            case "shipping method":
                Assert.assertTrue("Shipping method section is displaying", elementPresent("responsive-checkout_signed_in.shipping_method_info"));
                break;
            case "gift options":
                Assert.assertTrue("Gift Option section is displaying", elementPresent("responsive-checkout_signed_in.gift_options_info"));
                break;
            case "contact info":
                Assert.assertFalse(Navigate.get(ContactInfoSection.class).contactInfoSection.isDisplayed());
        }
    }
    /**
     * Fills in all shipping data on any responsive checkout page
     */
    @When("^I enter Same Day Delivery shipping address on guest shipping page$")
    public void I_enter_shipping_address_on_guest_shipping_page() {
        HashMap<String, String> opts = new HashMap<>();
        pausePageHangWatchDog();
        opts.put("sdd_eligible", "true");
        if (macys()) {
            new Checkout(opts, false).fillShippingData(false);
        } else {
            new CheckoutPageBcom(opts, false).fillGuestShippingData(false);
        }
        resumePageHangWatchDog();
    }

    /**
     * Validate that Gift Box options is disabled for Same Day Delivery eligible product
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I verify Gift Box option and Gift message options are disabled for Same day delivery eligible product$")
    public void i_verify_gift_box_options_is_disabled_on_shipping_page() throws Throwable {
        pausePageHangWatchDog();
        if (onPage("responsive_checkout")) {
            Wait.secondsUntilElementPresent("responsive_shipping_options.gift_box", 15);
            Assert.assertTrue("Gift Box option should not be enabled for SDD product", !(Elements.findElement("responsive_shipping_options.gift_box").isEnabled()));

            Wait.secondsUntilElementNotPresent("responsive_checkout.loader_mask", 15);
            Assert.assertTrue("Gift message option should not be enabled for SDD product", !Elements.findElement("responsive_shipping_options.gift_message").isEnabled());

            Wait.secondsUntilElementPresent("responsive_shipping_options.gift_option", 15);
            Clicks.javascriptClick("responsive_shipping_options.gift_option");
        } else {
            Clicks.click("shipping_guest.gift_option");
            Clicks.selectCheckbox("shipping_guest.gift_message");
            TextBoxes.typeTextbox("shipping_guest.gift_message_field1", "test message");
            Clicks.selectCheckbox("shipping_guest.gift_box");
        }
        resumePageHangWatchDog();
    }

    @And("^I click on (add new address|edit|cancel|save) in shipping address section$")
    public void iClickEditInShippingAddressSection(String button){
         switch (button) {
            case "add new address":
               /* Wait.secondsUntilElementPresent("");
                Clicks.click(signedInCheckout.addNewAddress.getWrappedElement());*/
                break;
            case "edit":
                Wait.secondsUntilElementPresent("responsive_shipping_section.edit_shipping_section", 15);
                Clicks.click("responsive_shipping_section.edit_shipping_section");
                break;
            case "cancel":
               /* Clicks.click(signedInCheckout.cancelShippingChangeButton.getWrappedElement());
                break;*/
            case "save":
                /*Clicks.click(signedInCheckout.saveNewAddress.getWrappedElement());*/
        }
    }

    @And("^I select \"(express|premium|standard)\" in shipping method$")
     public void iSelectShippingMethod(String method) throws Throwable {
        // the button doesn't activate until the second click in automation only. Why? Not a clue.
        Clicks.click("responsive_shipping_options." + method + "_shipping");
        Clicks.click("responsive_shipping_options." + method + "_shipping");
        Clicks.click("responsive_shipping_options." + method + "_shipping");
    }

   @And("^I verify Gift Box option and Gift message options are enabled$")
    public void i_verify_gift_box_options_is_enabled_on_shipping_page(){
       pausePageHangWatchDog();
       if (onPage("responsive_checkout")) {
           Wait.secondsUntilElementPresent("responsive_shipping_options.gift_box", 15);
           Assert.assertTrue("Gift Box option should be enabled", (Elements.findElement("responsive_shipping_options.gift_box").isEnabled()));

           Wait.secondsUntilElementNotPresent("responsive_checkout.loader_mask", 15);
           Assert.assertTrue("Gift message option should be enabled", Elements.findElement("responsive_shipping_options.gift_message").isEnabled());

           Wait.secondsUntilElementPresent("responsive_shipping_options.gift_option", 15);
       }
       resumePageHangWatchDog();

   }

   @And("^I verify Hide prices option is selected by default in Gift Options section$")
    public void iVerifyHidePricesOptionSelectedByDefault(){
       Assert.assertTrue("Hide price option is not selected by default", Elements.findElement("checkout.hide_price_checkbox").isEnabled());
   }

    @Then("^I validate Cancel and Save buttons are enabled$")
    public void iValidateCancelbuttonOfGiftOptionsSection(){

        Wait.secondsUntilElementPresent("checkout.cancel_button", 15);
        Assert.assertTrue("Cancel button is not present", elementPresent("chckout.cancel_button"));

        Wait.secondsUntilElementPresent("responsive_shipping_options.gift_options_save_signed_in", 15);
        Assert.assertTrue("Save button is not present in the Gift options section", elementPresent("responsive_shipping_options.gift_options_save_signed_in"));

    }

    @When("^I click on Cancel button$")
    public void iClickOnCancelButton() {
        Wait.untilElementPresent("checkout.cancel_button");
        Clicks.click("checkout.cancel_button");
    }

    @And("^I enter more than 45 characters$")
    public void iEnter47characters(){
        TextBoxes.typeTextbox("responsive_shipping_options.gift_message_field2", "I'm entering message more than 45 characters.");

    }

    @And("^I enter numbers and special characters$")
    public void iEnterSpecialcharacters(){
        TextBoxes.typeTextbox("responsive_shipping_options.gift_message_field3", "!@#$%^&*()_+}{:>?<|[];',./~`1234567890-=asz");
        }

    @Then("^I should see gift message on review gift option page$")
    public void iShouldSeeMessageOnReviewPage(){

        Assert.assertEquals("Gift message is not as expected", Elements.findElement("responsive_shipping_section.gift_message_line1_lable").getText(), "test message");
        Assert.assertEquals("Gift message is not as expected", Elements.findElement("responsive_shipping_section.gift_message_line2_lable").getText(), "I'm entering message more than 45 characters.");
        Assert.assertEquals("Gift message is not as expected", Elements.findElement("responsive_shipping_section.gift_message_line3_lable").getText(), "!@#$%^&*()_+}{:>?<|[];',./~`1234567890-=asz");
    }

    @And("^I validatig the empty message field$")
    public void iValidateEmptyMessageField(){
        Assert.assertTrue("Gift message is Displaying", !elementPresent("responsive_shipping_section.gift_message_line1_lable"));
    }

    @And("^I select gift options with no message$")
    public void iSelectGiftOptionsWithoutMessage(){
        Wait.secondsUntilElementPresent("responsive_shipping_options.gift_option", 15);
        Wait.secondsUntilElementNotPresent("responsive_checkout.loader_mask", 15);
        Clicks.javascriptClick("responsive_shipping_options.gift_option");
        Clicks.selectCheckbox("responsive_shipping_options.gift_box");
    }

    @And("^I validate no gift message is displaying on Review gift options page$")
    public void iValidateNoMessageDisplayingInReviewPage(){
        Assert.assertTrue("Gift message is Displaying", !elementPresent("responsive_shipping_section.gift_message_line1_lable"));
    }

    @And("^I enter \"(empty_data|data_with_value)\" into the text fields of shipping info$")
    public void iEnterEmptyDataIntoTheTextFieldsOfShippingInfo(String textValues) throws Throwable {
        if(textValues.equalsIgnoreCase("empty_data"))
            Elements.findElement(By.id("rc-shipping-info")).findElement(By.tagName("button")).click();
        else{
            new CheckoutSteps().I_enter_shipping_address_on_guest_shipping_page();
            Elements.findElement("responsive_shipping_options.shipping_phone_number").sendKeys(Keys.TAB);
        }
    }

    @Then("^I verify that \"(error message|fields accepting inputs)\" for corresponding fields in shipping info section$")
    public void iVerifyTheErrorMessageForCorrespondingFieldsInShippingInfoSection(String fieldVerifyType) throws Throwable {
        if(fieldVerifyType.equalsIgnoreCase("error message")){
            for (Map.Entry<String, Boolean> fields : validatingEmptyData().entrySet()) {
                Assert.assertTrue("Error message not displayed", fields.getValue().equals(true));
            }
        }
        else {
            for (Map.Entry<String, Boolean> fields : validatingDataField().entrySet()) {
                Assert.assertTrue("Error message displayed", fields.getValue().equals(true));
            }
        }

    }


    @And("^I enter special character in \"([^\"]*)\" text field$")
    public void iEnterSpecialCharacterInTextField(String textField) throws Throwable {
        Wait.secondsUntilElementNotPresent("responsive_shipping_options.shipping_first_name", 30);
        try {
            if (!elementPresent("responsive_shipping_options.shipping_first_name"))
                getWebDriver().navigate().refresh();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }
        switch (textField){
            case "firstname":
                Elements.findElement(By.id("rc-shipping-firstName")).sendKeys(RcHelper.getSpecialCharacterOrNumber("special"));
                break;
            case "lastname":
                Elements.findElement(By.id("rc-shipping-lastName")).sendKeys(RcHelper.getSpecialCharacterOrNumber("special"));
                break;
            case "addressfield1":
                Elements.findElement(By.id("rc-shipping-line1")).sendKeys(RcHelper.getSpecialCharacterOrNumber("special"));
                break;
            case "addressfield2":
                Elements.findElement(By.id("rc-shipping-line2")).sendKeys(RcHelper.getSpecialCharacterOrNumber("special"));
                break;
            case "city":
                Elements.findElement(By.id("rc-shipping-city")).sendKeys(RcHelper.getSpecialCharacterOrNumber("special"));
                break;
            case "zipcode":
                Elements.findElement(By.id("rc-shipping-postal-code")).sendKeys(RcHelper.getSpecialCharacterOrNumber("number"));
                break;
            case "phonenumber":
                Elements.findElement(By.id("rc-shipping-phone")).sendKeys("4156");
                break;
            default:
                Assert.fail("Give the correct field option");
                break;
        }
    }

    @Then("^I verify the corresponding error message for \"([^\"]*)\" field$")
    public void iVerifyTheCorrespondingErrorMessageForField(String textField) throws Throwable {
        switch (textField){
            case "firstname":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("firstname"));
                break;
            case "lastname":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("lastname"));
                break;
            case "addressfield1":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("addressfield1"));
                break;
            case "addressfield2":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("addressfield2"));
                break;
            case "city":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("city"));
                break;
            case "zipcode":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("zipcode"));
                break;
            case "phonenumber":
                Assert.assertTrue("Error message is not available or mismatches", verifySpecialCharOrNumber("phonenumber"));
                break;
            default:
                Assert.fail("Enter the correct option field");
                break;
        }
    }


    @Then("^I verify the \"([^\"]*)\" of the text fields apply gift card and rewards section$")
    public void iVerifyTheValidationOfTheTextFieldsApplyGiftCardAndRewardsSection(String action) throws Throwable {
        if(action.equalsIgnoreCase("validation")){
            Assert.assertTrue("card number has error", Elements.findElement(By.cssSelector("#rc-giftcard-number-row > div > div > div")).isDisplayed());
            Assert.assertTrue("card number has error", Elements.findElement(By.cssSelector("##rc-giftcard-cid-row > div > div > div")).isDisplayed());
        }
        else if(action.equalsIgnoreCase("error")){
            Assert.assertTrue("card number has error", Elements.findElement(By.cssSelector("#rc-giftcard-number-row > div > div > div")).isDisplayed());
        }

    }

    @And("^I \"([^\"]*)\" the card details in gift card and reward section text fields$")
    public void iTheCardDetailsInGiftCardAndRewardSectionTextFields(String action) throws Throwable {
        if(action.equalsIgnoreCase("enter")){
            RcHelper.giftCardApply();
        }
        else if(action.equalsIgnoreCase("not enter")){
            Elements.findElement(By.id("rc-gift-card")).click();
            Elements.findElement(By.id("rc-giftcard-apply")).click();
        }
    }

    @Then("^I verify payment option section is expanded$")
    public void iVerifyPaymentOptionSectionIsExpanded() throws Throwable {
        Wait.until(() -> Elements.findElement(By.id("rc-payment-info")).isDisplayed(), 20);
        Assert.assertTrue("Payment section is not expanded", Elements.findElement(By.id("rc-payment-info")).isDisplayed());
    }

    @Then("^I verify that credit card option is selected and paypal option is not selected by default$")
    public void iVerifyThatCreditCardOptionIsSelectedAndPaypalOptionIsNotSelectedByDefault() throws Throwable {
        Assert.assertTrue("Credit card in payment section not selected", Elements.findElement(By.id("rc-creditcard")).isSelected());
        Assert.assertFalse("Paypal option is selected", Elements.findElement(By.id("rc-paypal")).isSelected());
    }

    @And("^I verify the default fields are enabled$")
    public void iVerifyTheDefaultFieldsAreEnabledByDefault() throws Throwable {
        Assert.assertTrue("SecurehelpLink is not available", Elements.findElement(By.className("rc-secure-info")).isDisplayed());
        Assert.assertTrue("Billing section is not displayed", Elements.findElement(By.id("rc-billing-address-container")).isEnabled());
        Assert.assertTrue("Select card type option is not enabled", Elements.findElement(By.id("rc-payment-card-type")).isEnabled());
        Assert.assertTrue("Card number field is enabled", Elements.findElement(By.id("rc-payment-card-number")).isEnabled());
        Assert.assertTrue("Expiration date selection is disabled", Elements.findElement(By.id("rc-payment-card-month")).isEnabled());
        Assert.assertTrue("Expiration year selection is disabled", Elements.findElement(By.id("rc-payment-card-year")).isEnabled());
        Assert.assertTrue("Secure code field is not enabled", Elements.findElement(By.id("rc-payment-scode")).isEnabled());
    }

    @And("^I select paypal option in payment section$")
    public void iSelectPaypalOptionInPaymentSection() throws Throwable {
        Wait.untilElementPresent("responsive_payment_signin_section.paypal_radio_button");
        Elements.findElement(By.id("rc-paypal")).click();
        Wait.untilElementPresent("responsive_payment_signin_section.paypal_radio_button");
    }

    @Then("^I verify the alert message displayed with note:$")
    public void iVerifyTheAlertMessageDisplayedWithNote() throws Throwable {
        Wait.untilElementPresent("responsive_payment_signin_section.paypal_disclaimer");
        String alertMessage = "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal.";
        Assert.assertTrue("Alert message is not displayed", Elements.findElement("responsive_payment_signin_section.paypal_disclaimer").getText().equalsIgnoreCase(alertMessage));
    }

    @And("^I verify that Continue to Paypal button is available for (guest|registered) user$")
    public void iVerifyThatButtonIsAvailable(String userType) throws Throwable {
        if (userType.equalsIgnoreCase("guest")){
            Assert.assertTrue("paypal checkout button is not available", Elements.findElement(By.id("rc-paypal-continue")).isEnabled());
        }
        else {
            Assert.assertTrue("paypal checkout button is not available", elementPresent("responsive_payment_signin_section.continue_with_paypal"));
        }
    }


    @When("^I select \"([^\"]*)\" from card type field$")
    public void iSelectFromCardTypeField(String cardType) throws Throwable {
        RcHelper.selecCreditCardType(cardType);
    }

    @Then("^I verify corresponding information against \"([^\"]*)\"$")
    public void iVerifyCorrespondingInformationAgainst(String cardType) throws Throwable {
        Wait.forPageReady();
        Thread.sleep(10000);
        switch (cardType){
            case "Macy's":
            case "Employee Card":
                Assert.assertFalse("Month and year section is displaying", RcHelper.ismacysAndEmployeeCard());
                break;
            case "Visa":
            case "MasterCard":
                Assert.assertTrue("Month and year section is not displaying", RcHelper.otherCards());
                Assert.assertTrue("Amex card needed 4-digits cvv number is displaying", RcHelper.nonAmexCards());
                break;
            case "American Express":
            case "Macy's American Express":
                Assert.assertTrue("Month and year section is not displaying", RcHelper.otherCards());
                Assert.assertTrue("Not a Amex card needed 3-digits cvv is displaying", RcHelper.amexCards());
                break;
            default:
                Assert.fail("Given not in select field");
        }
    }

    @And("^I enter details on the shipping section for \"([^\"]*)\" and continue from shipping section$")
    public void iEnterDetailsOnTheShippingSectionforAndContinueFromShippingSection(String userType) throws Throwable {
        if(userType.equalsIgnoreCase("guest")){
            try {
                if (!elementPresent("responsive_shipping_options.shipping_first_name"))
                    getWebDriver().navigate().refresh();
            } catch (DriverNotInitializedException e) {
                e.printStackTrace();
            }
            new CheckoutSteps().I_enter_shipping_address_on_guest_shipping_page();
            Elements.findElement(By.id("rc-shipping-continue")).click();
        }
        else {
            // Wait.until(()->Elements.findElement(By.id("rc-paypal")).isDisplayed(), 20);
            Wait.untilElementPresent("responsive_payment_signin_section.paypal_radio_button");
            if(!elementPresent("responsive_payment_signin_section.paypal_radio_button"))
                getWebDriver().navigate().refresh();
        }
        Wait.untilElementPresent("responsive_payment_signin_section.paypal_radio_button");
    }

    // GCE steps to validate on Direct pages

    @And("^I Should see Continue shopping button$")
    public void iShouldSeeCountinueButton()throws Throwable{
        Assert.assertTrue("Continue shopping button is not present", Elements.elementPresent("checkout.rc_test_element"));
        Clicks.click("checkout.rc_test_element");
        getWebDriver().navigate().back();
    }

    @And("^I should see shopping bag icon on the page$")
    public void iShouldSeeShoppingBagIcon()throws Throwable{
        Assert.assertTrue("Shopping bag button is not present", Elements.elementPresent("checkout.rc_quickBag_icon"));
        Clicks.click("checkout.rc_quickBag_icon");
        getWebDriver().navigate().back();
    }

    @And("^I visit the GCE site as a guest user$")
        public void iNaviate()throws Throwable{
        String url = "http://uchk-mcom-spo-0822.c4d.devops.fds.com/cxui/reship/confirmtest2/confirm";
           // Navigate.visit("http://uchk-mcom-spo-0822.c4d.devops.fds.com/cxui/reship/confirmtest2/confirm");
        WebDriverManager.getWebDriver().get(url);
        Wait.secondsUntilElementPresent("checkout.rc_test_element", 25);
        }

    // End GCE verification

    @Then("^I verify the \"([^\"]*)\" button displays on the shipping & delivery section$")
    public void iVerifyTheButtonDisplaysOnTheShippingDeliverySection(String buttonName) throws Throwable {
        switch (buttonName){
            case "change":
                Assert.assertTrue("Change button is not displayed", Elements.findElement("checkout.change_button_guest").isDisplayed());
                break;
            case "edit":
                Elements.findElement("checkout.change_button_guest").click();
                Wait.untilElementPresent("checkout.edit_button_guest");
                Assert.assertTrue("Edit button is not available", RcHelper.isSDsectionButtons().get("Editbutton"));
                break;
            case "Save":
                Wait.untilElementPresent("checkout.edit_button_guest");
                Assert.assertTrue("Save button is not available", RcHelper.isSDsectionButtons().get("SaveButton"));
                break;
            case "Add New":
                Wait.untilElementPresent("checkout.edit_button_guest");
                Assert.assertTrue("Add new button is not available", RcHelper.isSDsectionButtons().get("AddNew"));
                break;
            case "cancel":
                Wait.untilElementPresent("checkout.cancel_button_guest");
                Assert.assertTrue("cancel button is not available", RcHelper.isSDsectionButtons().get("CancelButton"));
                break;
            default:
                Assert.fail("Not in shipping & delivery option");
        }
    }


    @And("^I click the change button in \"([^\"]*)\" section$")
    public void iClickTheChangeButtonInSection(String sectionType) throws Throwable {
        sdDetails=RcHelper.getAddressInSdSection();
        if(sectionType.equalsIgnoreCase("shipping & delivery")){
            Clicks.click(Elements.findElement("checkout.change_button_guest"));
        }
    }

    @Then("^I verify that Edited address is displaying in shipping & delivery section$")
    public void iVerifyThatEditedAddressIsDisplayingInShippingDeliverySection() throws Throwable {
        Map<String, String > sdDetailsAfterEdit;
        sdDetailsAfterEdit=RcHelper.getHighlightedAddress();
        Assert.assertNotEquals(sdDetails.get("firstname"), sdDetailsAfterEdit.get("firstname"));
        Assert.assertNotEquals(sdDetails.get("secondname"), sdDetailsAfterEdit.get("secondname"));
        Assert.assertEquals(sdDetails.get("address1"), sdDetailsAfterEdit.get("address1"));
        Assert.assertEquals(sdDetails.get("address2"), sdDetailsAfterEdit.get("address2"));
        Assert.assertEquals(sdDetails.get("city"), sdDetailsAfterEdit.get("city"));
        Assert.assertEquals(sdDetails.get("state"), sdDetailsAfterEdit.get("state"));
        Assert.assertEquals(sdDetails.get("zipcode"), sdDetailsAfterEdit.get("zipcode"));
    }

    @And("^I \"([^\"]*)\" in the shipping & delivery section$")
    public void iInTheShippingDeliverySection(String type) throws Throwable {
        if(type.equalsIgnoreCase("edit"))
            RcHelper.editHighlightedAddress();
        else {
            RcHelper.addNewInfo();
        }
    }

    @Then("^I verify that \"([^\"]*)\" address is displaying in shipping & delivery section$")
    public void iVerifyThatAddressIsDisplayingInShippingDeliverySection(String verifytype) throws Throwable {
        if(verifytype.equalsIgnoreCase("edited")){
            Map<String, String > sdDetailsAfterEdit;
            sdDetailsAfterEdit=RcHelper.getAddressInSdSection();
            Assert.assertNotEquals(sdDetails.get("firstname"), sdDetailsAfterEdit.get("firstname"));
            Assert.assertNotEquals(sdDetails.get("secondname"), sdDetailsAfterEdit.get("secondname"));
            Assert.assertEquals(sdDetails.get("address1"), sdDetailsAfterEdit.get("address1"));
            Assert.assertEquals(sdDetails.get("address2"), sdDetailsAfterEdit.get("address2"));
            Assert.assertEquals(sdDetails.get("city"), sdDetailsAfterEdit.get("city"));
            Assert.assertEquals(sdDetails.get("state"), sdDetailsAfterEdit.get("state"));
            Assert.assertEquals(sdDetails.get("zipcode"), sdDetailsAfterEdit.get("zipcode"));
        }
        else {
            Map<String, String > sdDetailsAfteradd;
            sdDetailsAfteradd=RcHelper.getHighlightedAddress();
            Assert.assertNotEquals(sdDetails.get("firstname"), sdDetailsAfteradd.get("firstname"));
            Assert.assertNotEquals(sdDetails.get("secondname"), sdDetailsAfteradd.get("secondname"));
            Assert.assertNotEquals(sdDetails.get("address1"), sdDetailsAfteradd.get("address1"));
            Assert.assertNotEquals(sdDetails.get("address2"), sdDetailsAfteradd.get("address2"));
            Assert.assertNotEquals(sdDetails.get("city"), sdDetailsAfteradd.get("city"));
            Assert.assertNotEquals(sdDetails.get("state"), sdDetailsAfteradd.get("state"));
            Assert.assertNotEquals(sdDetails.get("zipcode"), sdDetailsAfteradd.get("zipcode"));
        }
    }

    @When("^I continue to paypal checkout as a \"([^\"]*)\" user$")
    public void iContinueToPaypalCheckoutAsAUser(String userType) throws Throwable {
        if(userType.equalsIgnoreCase("registered")){
            Wait.untilElementPresent("checkout.signed_in_paypal");
            Clicks.click("checkout.signed_in_paypal");
            Wait.untilElementPresent("checkout.payapal_logo");
        }
        else {
            Elements.findElement("checkout.rc_email_text_field").sendKeys(TestUsers.getPayPalInformation().get("email"));
            Clicks.click("checkout.rc_paypal_continue_button");
            Wait.untilElementPresent("checkout.payapal_logo");
        }
    }

    @Then("^I verify that page navigated to paypal login$")
    public void iVerifyThatPageNavigatedToPaypalLogin() throws Throwable {
        Assert.assertTrue("Not in paypal login page", getWebDriver().getCurrentUrl().contains("//www.sandbox.paypal.com/checkoutnow?"));
    }

    @When("^I enter the valid paypal credentials and continue from paypal page$")
    public void iEnterTheValidPaypalCredentials() throws Throwable {
        Wait.untilElementPresent("paypal_login.login_iframe");
        switchToFrame("paypal_login.login_iframe");
        Clicks.click("paypal_login.paypal_email");
        PaypalEmail=TestUsers.getPayPalInformation().get("email");
        TextBoxes.typeTextbox("paypal_login.paypal_email", PaypalEmail);
        Clicks.click("paypal_login.paypal_password");
        TextBoxes.typeTextbox("paypal_login.paypal_password", TestUsers.getPayPalInformation().get("password"));
        Clicks.click("paypal_login.paypal_login");
        Thread.sleep(10000);
        Wait.untilElementPresent("paypal_login.continue");
    }

    @Then("^I verify the paypal account is mapped to checkout page for \"([^\"]*)\" paypal account$")
    public void iVerifyThePaypalAccountIsMappedToCheckoutPageForPaypalAccount(String paypalType) throws Throwable {
        Wait.untilElementNotPresent(By.id("spinner"));
        Wait.untilElementPresent("paypal_login.continue");
        Clicks.click("paypal_login.continue");
        Wait.untilElementNotPresent("checkout.spinner_paypal");
        if(paypalType.equalsIgnoreCase("registered")){
            Thread.sleep(10000);
            Wait.untilElementPresent("checkout.rc_paypal_change");
            Assert.assertTrue("Not a paypal account in payment section", Elements.findElement("checkout.rc_paypal_header").isDisplayed());
            Clicks.click(By.id("rc-paypal-change"));
            WebElement ele=Elements.findElement(By.cssSelector("#rc-paypal-info > div > div > div > div > div > p > span"));
            String alertMsg="This is the email address associated to the PayPal account you are using.";
            Assert.assertTrue("email not mapped to paypal", ele.getText().equalsIgnoreCase(alertMsg));
        }
        else {
            Wait.untilElementPresent(By.id("rc-payment-info"));
            WebElement ele=Elements.findElement(By.id("rc-payment-info")).findElements(By.className("rc-section")).get(0);
            Assert.assertTrue("Paypal icon is missing in payment info section", ele.findElement(By.className("rc-paypalicon")).isDisplayed());
            Assert.assertTrue("Paypal email is not avaialable below paypal icon", ele.findElements(By.tagName("p")).get(1).getText().equalsIgnoreCase(PaypalEmail));
        }
    }

    @Then("^I get the existing card details in payment section$")
    public void iGetTheExistingCardDetailsInPaymentSection() throws Throwable {
        String CCType=RcHelper.getCCDetails().get("cardType");
        String CCExpiryDetails=RcHelper.getCCDetails().get("expDetails");
    }

    @And("^I \"([^\"]*)\" the card details in payment section$")
    public void iChangeTheCardDetailsInPaymentSection(String optionType) throws Throwable {
        if(optionType.equalsIgnoreCase("change"))
            RcHelper.changeCcTypeNumber();
        else
            RcHelper.AddNewCard("Macy's");
    }

    @Then("^I verify the see the edited details of the card is updated in payment section$")
    public void iVerifyTheSeeTheEditedDetailsOfTheCardIsUpdatedInPaymentSection() throws Throwable {
        Assert.assertTrue("Card type mismatches", RcHelper.getCCDetails().get("cardType").equalsIgnoreCase(RcHelper.changeCcTypeNumber().get("CardType")));
        Assert.assertTrue("", RcHelper.getCCDetails().get("expDetails").contains(RcHelper.changeCcTypeNumber().get("ExpiryDetails")));
    }

    @Then("^I verify that another card is available in card list$")
    public void iVerifyThatAnotherCardIsAvailableInCardList() throws Throwable {
        Assert.assertTrue("Newly added card details not available", RcHelper.getCCDetails().get("cardType").equals("Macy's"));
    }

    @Then("^I verify the buttons available for change the card details$")
    public void iVerifyTheButtonsAvailableForChangeTheCardDetails() throws Throwable {
        RcHelper.ccButtons().forEach((k,v)->Assert.assertTrue(v));
    }

    @Then("^I verify the alert in payment section$")
    public void iVerifyTheAlertInPaymentSection() throws Throwable {
        Assert.assertTrue("Alert not present", RcHelper.isAlertAvailable());
    }


    @When("^I re-enter card number in payment section$")
    public void iReEnterCardNumberInPaymentSection() throws Throwable {
        RcHelper.applyCcNumber();
    }

    @Then("^I verify that thank you message after enter the required details$")
    public void iVerifyThatThankYouMessageAfterEnterTheRequiredDetails() throws Throwable {
        Assert.assertTrue("Thank you message not available", Elements.findElement("checkout.thankyou_alert_message").isDisplayed());
    }

    @When("^I verify that user able to enter security code in payment section$")
    public void iEnterSecurityCodeInPaymentSection() throws Throwable {
        RcHelper.sendCcSecurityCode();
    }

    @Then("^I verify the buttons available for change the contact details$")
    public void iVerifyTheButtonsAvailableForChangeTheContactDetails() throws Throwable {
        RcHelper.buttonsInContact().forEach((k,v)-> Assert.assertTrue(v));
    }

    @And("^I update the phone number in contact details$")
    public void iUpdateThePhoneNumberInContactDetails() throws Throwable {
        RcHelper.updatePhoneNum();
    }

    @Then("^I verify the phone number is updated$")
    public void iVerifyThePhoneNumberIsUpdated() throws Throwable {
        Assert.assertTrue("Updated phone number is not available", (RcHelper.PhoneNum).contains(Elements.findElement(By.id("rc-at-bc-contact")).findElements(By.className("rc-text-ellipsis")).get(0).getText()));
    }

    @And("^I select the shipping method in shipping method section for \"([^\"]*)\" user$")
    public void iSelectTheShippingMethodInShippingMethodSectionForUser(String userType) throws Throwable {
        if(userType.equalsIgnoreCase("guest"))
            ShippingMethod=RcHelper.selectShippingMethodsGuest();
        else
            ShippingMethod=RcHelper.selectShippingMethodsSignedIn();
    }

    @Then("^I verify that the choosen shipping method displayed in shipping & delivery section for \"([^\"]*)\" user$")
    public void iVerifyThatTheChoosenShippingMethodDisplayedInShippingDeliverySectionForUser(String userType) throws Throwable {
        if (userType.equalsIgnoreCase("guest"))
            Assert.assertTrue("Selected shipping method is not available in shipping & delivery section", ShippingMethod.equalsIgnoreCase(RcHelper.getSelectedShippingMethod()));
        else
            Assert.assertTrue("Selected shipping method mismatches", ShippingMethod.equalsIgnoreCase(RcHelper.getSelectedShippingMethodSignedIn()));
    }

    @Then("^I verify that first shipping method is selected by default for \"([^\"]*)\" user$")
    public void iVerifyThatFirstShippingMethodIsSelectedByDefaultForUser(String userType) throws Throwable {
        if(userType.equalsIgnoreCase("guest"))
            Assert.assertTrue("First shipping method not selected", RcHelper.isFirstShippingSelectedGuest());
        else
            Assert.assertTrue("First shipping method not selected", RcHelper.isFirstShippingSelectedSignedIn());
    }

    @And("^I verify selected shipping method displays in order section for \"([^\"]*)\" user$")
    public void iVerifySelectedShippingMethodDisplaysInOrderSectionForUser(String userType) throws Throwable {
        if(userType.equalsIgnoreCase("guest")){
            Assert.assertTrue("Shipping method mismatches", RcHelper.getSelectedShippingMethodPriceGuest().get("shippingMethod").equalsIgnoreCase(RcHelper.getShippingMethodFromOrderSection().split("\n")[0]));
            Assert.assertTrue("Shipping method mismatches", RcHelper.getSelectedShippingMethodPriceGuest().get("shippingPrice").equalsIgnoreCase(RcHelper.getShippingMethodFromOrderSection().split("\n")[1]));
        }
        else {
            Assert.assertTrue("Shipping method mismatches", RcHelper.getSelectedShippingMethodPriceSignedIn().get("shippingMethod").equalsIgnoreCase(RcHelper.getShippingMethodFromOrderSection().split("\n")[0]));
            Assert.assertTrue("Shipping method mismatches", RcHelper.getSelectedShippingMethodPriceSignedIn().get("shippingPrice").equalsIgnoreCase(RcHelper.getShippingMethodFromOrderSection().split("\n")[1]));
        }
    }
}
