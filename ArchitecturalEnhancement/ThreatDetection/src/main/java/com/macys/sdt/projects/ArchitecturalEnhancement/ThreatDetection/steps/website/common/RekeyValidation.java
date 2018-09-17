package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.steps.website.common;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.actions.website.Generic;
import com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.actions.website.Payment;
import com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.utils.db.Service;
import com.macys.sdt.shared.steps.website.*;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.regex.Pattern;


public class RekeyValidation extends StepUtils {

    public String user_id;
    public String user_email;
    public int bcom_pid = 67858;
    public int mcom_pid = 1877013;
    public String mvgc = "351.00";
    public String bvgc = "401.00";

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RekeyValidation.class);
    public TaggedProfileValidation cp = new TaggedProfileValidation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    private static ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
    String url = RunConfig.url;
    HashMap<String, String> cardNo = null;


    @Given("^my online uid is available in fraud_user table$")
    public void addUserIdToFraudTable() throws Throwable {
        cp.createProfile();
        user_id = CommonUtils.getUserDetailsByEmail(cp.user_email);
        Service.setUserAsFraud(user_id);
        log.info("user_id " + user_id + " inserted in fraud table");
    }

    @And("^I sign in with fraud_user$")
    public void iSignInWithFraudUser() throws Throwable {
        Navigate.visit(url + "/account/signin?");
        TextBoxes.typeTextbox("sign_in.password", cp.user_pwd);
        TextBoxes.typeTextbox("sign_in.email", cp.user_email);
        //bcom
        //TextBoxes.typeTextbox("sign_in.password", "Macys12345");
        // TextBoxes.typeTextbox("sign_in.email","5nphismlgn060458@blackhole.macys.com");
        Clicks.click("sign_in.sign_in_button");

    }


    @And("^I add (\\d+) shipping address and credit card added in profile$")
    public void addShipAddAndCreditToProfile(int count) throws Throwable {
        Generic.addCreditToWallet("VISA");
        Generic.addAddressToProfile(count);

    }

    @And("^I sign out$")
    public void iSignOut() throws Throwable{
        Navigate.visit(RunConfig.url);
        Clicks.hoverForSelection("header.goto_my_account_link");
        Clicks.click("header.goto_sign_out_link");
    }

    @And("^I visit the site as signed in user with same uid$")
    public void i_visit_site_as_signed_in_user_with_same_uid() throws Throwable {
        myAccountSteps.iSignInToMyExistingProfile();
        log.info("Signed in with "+user_email);
        //Cookie logic will be removed post holiday and deleting MISCGCs since it is not getting removed on sign out
        Cookies.deleteCookie("MISCGCs");
    }

    @And("^I add normal item to the bag and navigate to checkout page$")
    public void iAddItemToBagAndCheckout() throws Throwable {
        int prod_id = StepUtils.macys() ? mcom_pid : bcom_pid;
        CommonUtils.navigateDirectlyToProduct(prod_id);
        shopAndBrowse.I_add_product_to_my_bag_from_standard_PDP_Page();
        log.info(Cookies.getCookieValue("MISCGCs"));
        Clicks.click("add_to_bag.checkout");
        Wait.untilElementPresent("shopping_bag.continue_checkout_image");
        Clicks.click("shopping_bag.continue_checkout_image");
    }

    @Then("^I should see the rekey on payment section$")
    public void i_should_see_rekey() throws Throwable {
        String msg_bcom = "Please re-enter your Card number.\n" +
                "This step keeps your account secure by confirming your information.";
        String msg_mcom = "Please re-enter your card number.\n" +
                "We want to make sure this is an authorized use of your account.";
        Assert.assertTrue("ERROR - APP: No global error", Elements.findElement("responsive_checkout_signed_in.cc_rekey_message").isDisplayed());
        Assert.assertTrue("ERROR - APP: rekey global message on responsive checkout page is not displaying",
                Elements.findElement("responsive_checkout_signed_in.cc_rekey_message").getText()
                        .contains(StepUtils.macys() ? msg_mcom : msg_bcom));
    }


    @When("^I enter the valid card number again$")
    public void iEnterCCNumber() throws Throwable{
        String cc_no = TestUsers.getValidVisaCreditCard().getCardNumber();
        TextBoxes.typeTextbox("responsive_checkout_signed_in.re_enter_card_number",cc_no);
        Clicks.click("responsive_checkout_signed_in.re_enter_card_number_confirm");
    }

    @And("^I should see the card number is verified$")
    public void iShouldSeeCardNumberVerified(){
        Assert.assertTrue("ERROR - APP: Unable to verify the card",
                Elements.findElement("responsive_checkout_signed_in.credit_card_rekey_success").isDisplayed());
    }

    @Then("^I should not see the rekey on payment section$")
    public void iShouldNotSeeRekeySection() throws Throwable{
        Assert.assertFalse("ERROR - APP: Rekey should not be displayed on checkout for this user",
                Elements.elementPresent("responsive_checkout_signed_in.cc_rekey_message"));
    }

    @And("^I navigate directly to checkout page$")
    public void iNavigateDirectlyToCheckout() throws Throwable{
        Navigate.visit(url + "/chkout/rcsignedin?perfectProxy=true");
        Wait.forPageReady();
        // Wait.untilElementPresent("responsive_checkout_signin_in.")
    }

    @And("^I add \"([^\"]*)\" credit card to wallet$$")
    public  void iAddAnotherCreditCardOnWallet(String cardType) throws Throwable {
        Generic.addCreditToWallet(cardType);
    }

    @Then("^I should not see the rekey on payment section for \"([^\"]*)\"$")
    public void iShouldNotSeeRekeyForOtherCard(String cardType) throws Throwable {
        Payment.changeCreditCard();
        Payment.selectCreditCardType(cardType);
        Payment.saveCard();
        // Payment.addCreditOnCheckout("Macy's");
        Assert.assertFalse("ERROR - APP: Rekey should not be displayed on checkout for " + cardType,
                Elements.elementPresent("responsive_checkout_signed_in.cc_rekey_message"));


    }

    @And("^I select the other card$")
    public void iShouldOtherCard() throws Throwable
    {
        Payment.changeCreditCard();
        Payment.selectOtherCardType();
        Payment.saveCard();
    }

    @When("^I add VGC item to bag$")
    public void iAddVGCItemToBag() throws Throwable{
        shopAndBrowse.iNavigateToPdp("virtual_gift_card", "orderable");
        TextBoxes.typeTextbox("product_display.egift_amount", StepUtils.macys() ? mvgc : bvgc);
        TextBoxes.typeTextbox("product_display.egift_recipient_email", TestUsers.generateRandomEmail(10));
        Clicks.click("product_display.add_to_bag_button");
        Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
    }


    @And("^I sign in with same profile$")
    public void iSignInWithSameProfile() throws Throwable{
        Navigate.visit(url + "/account/signin?");
        TextBoxes.typeTextbox("sign_in.email",TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password",TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.sign_in_button");
        if (StepUtils.macys()){
            if (Elements.elementPresent("sign_in.security_question")) {
                UserProfile customer = TestUsers.getCustomer(null);
                DropDowns.selectByText("sign_in.security_question", customer.getUser().getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("sign_in.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
                Clicks.click("sign_in.save_and_continue");
            }
        }
    }

    @And("^I should see the rekey on payment section for the all shipping address added on previous session$")
    public void iShouldSeeRekeyForAllShipAddress() throws Throwable{
        i_should_see_rekey();
        Payment.changeShipAdr();
        Payment.selectOtherShipAdr();
        Payment.saveShipAdr();
        i_should_see_rekey();
    }

    @When("^I update the timestamp day -1 for any shipping address$")
    public void iUpdateShippingAddressTimeStamp() throws Throwable{
        Service.updateShipAddTimeStamp(CommonUtils.getUserDetailsByEmail(TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail()));

    }

    @And("^I should not see rekey for the cards on selecting the shipping address which was modified$")
    public void iShouldNotSeeRekeyForCardsOnSelectingTheShippingAddressWhichWasModified() throws Throwable{
        HashMap<String,String> ship;
        ship = Service.getShipAddId(TestUsers.getCustomerInformation().getUser().getProfileAddress().getEmail());
        String add_id = ship.get("ADDRESS_ID");
        String add_line1 = ship.get("ADDRESS_LINE_1");
        Service.updateShipAddTimeStamp(add_id);
        Payment.changeShipAdr();
        Payment.selectModifiedShipAdd(add_line1);
        Payment.saveShipAdr();
        iShouldNotSeeRekeySection();

    }

    @When("^I enter wrong credit card number (\\d+) times$")
    public void iEnterInvalidCCNumber(int count) throws Throwable{
        //String cardno = Elements.findElement(By.cssSelector("payment_credit_card_summary")).getText();
        //Pattern pattern = Pattern.compile("\\d+");
        // String cardno = Elements.findElement("responsive_payment_signin_section.");
        // cardNo.put("cardnumber", cardno);

        for(int i=1; i<=count; i++ ){
            TextBoxes.typeTextbox("responsive_checkout_signed_in.re_enter_card_number","456756765456765");
            Elements.findElement("responsive_checkout_signed_in.re_enter_card_number").sendKeys(Keys.TAB);
            Clicks.click("responsive_checkout_signed_in.re_enter_card_number_confirm");
            log.info("Rekeyed 5 invalid attempts");
        }
    }

    @Then("^I should see the locked message$")
    public void iShouldNotSeeErrorMessage() throws Throwable{
        String ml_msg = "You have reached a maximum number of attempts.";
        String bl_msg = "Maximum number of attempts reached. Please use a different form of payment.";
        Assert.assertTrue("ERROR - APP: Error message not matching or missing for rekey success",
                Elements.findElement("responsive_payment_signin_section.rekey_success_message").getText().equalsIgnoreCase(StepUtils.macys() ? ml_msg : bl_msg));

    }

    @When("^I check the credit card list$")
    public void iCheckCreditCardList() throws Throwable{
        Payment.changeCreditCard();
    }

    @Then("^I should see the locked credit card is greyed out$")
    public void iShouldSeeTheLockedCreditCardIsGreyedOut() throws Throwable{
        Payment.checkGreyedOutCreditCard(cardNo.get("cardnumber"));
    }
}