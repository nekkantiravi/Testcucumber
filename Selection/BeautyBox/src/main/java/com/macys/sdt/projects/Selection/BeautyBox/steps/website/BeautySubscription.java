package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Selection.BeautyBox.utils.website.GenericUtilsBeauty;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

import java.util.List;
import java.util.Map;



/**
 * Created by yh00119 on 4/17/17.
 */
public class BeautySubscription extends StepUtils {

    private static String cardtype;
    private static String addressType;
    private static String strEmailId = "rama.madamanchi@macys.com";


    @When("^I click on add new to add shipping address on checkout$")
    public void I_click_on_add_new_to_add_shipping_address() throws Throwable {

        Thread.sleep(15000);
        if (Elements.elementPresent("beauty_subscription.add_new_shipping_address")){
            Clicks.click("beauty_subscription.add_new_shipping_address");
            Thread.sleep(4000);
        } else {
            Assert.fail("Unable to identify shipping address button");
        }

    }

    @Then("^I check the checkbox status on create subscription$")
    public static void validatingTheCheckboxStatus() throws Throwable {
        boolean chkboxStatus = Elements.findElement("beauty_subscription.create_sub_checkbox_status").isEnabled();
        if(chkboxStatus){
            Assert.fail("Checkbox on create subscription is enabled");
            System.out.println("Checkbox on create subscription is enabled");
        }else{
            Assert.assertTrue("Checkbox on createSubscription is disabled",true);
            System.out.println("Checkbox on createSubscription is disabled");
        }
    }

    @When("^I click on add new to add creditcard on checkout$")
    public void I_click_on_add_new_to_add_creditcard() throws Throwable {

        Thread.sleep(10000);
        if (Elements.elementPresent("beauty_subscription.add_payment_btn")) {
            Clicks.click("beauty_subscription.add_payment_btn");
        } else {
            Assert.fail("Unable to identify credit card button");
        }
    }

    @Then("^I click on save to save the shipping address$")
    public void I_click_on_save_to_save_the_shipping_address_credit_card() throws Throwable {

        Assert.assertTrue("Error - Unable to identify shipping address save button",
                Elements.elementPresent("beauty_subscription.add_shipping_save"));
        Clicks.click("beauty_subscription.add_shipping_save");
    }

    @And("^I click on save to save the updated shipping address$")
    public void I_click_on_save_to_save_the_updated_shipping_address() throws Throwable{
        Assert.assertTrue("Error - Unable to identify save shipping address button to update the default shipping",
                Elements.elementPresent("beauty_subscription.shipping_update_save"));
        Clicks.click("beauty_subscription.shipping_update_save");
    }

    @And("^I click on save to save the updated shipping address on cs$")
    public void I_click_on_save_to_save_the_updated_shipping_address_on_cs() throws Throwable{
        Assert.assertTrue("Error - Unable to identify save shipping address button to update the default shipping",
                Elements.elementPresent("beauty_subscription.shipping_update_cs_save"));
        Clicks.click("beauty_subscription.shipping_update_cs_save");
    }

    @Then("^I click on save to save the credit card address$")
    public void I_click_on_save_to_save_the_credit_card() throws Throwable {

        Assert.assertTrue("Error - Unable to identify shipping address save button",
                Elements.elementPresent("beauty_subscription.add_btn_payment"));
        Clicks.click("beauty_subscription.add_btn_payment");

    }

    @Then("^I click on save to save the credit card address on cs$")
    public void I_click_on_save_to_save_the_credit_card_on_cs() throws Throwable {

        Assert.assertTrue("Error - Unable to identify shipping address save button",
                Elements.elementPresent("beauty_subscription.payment_card_save"));
        Clicks.click("beauty_subscription.payment_card_save");
    }

    @And("^I accept terms and conditions on subscription checkout$")
    public void I_accept_terms_and_conditions_on_subscription_checkout() throws Throwable {

        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the terms and conditions checkbox on subscription checkout page",
                Elements.elementPresent("beauty_subscription.terms_n_conditions_chk"));
        Clicks.click("beauty_subscription.terms_n_conditions_chk");
    }

    @And("^I click on continue button on create subscription$")
    public void I_click_on_continue_button_on_create_subscription() throws Throwable {

        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the continue button on create subscription checkout page",
                Elements.elementPresent("beauty_subscription.chk_continue_btn"));
        Clicks.click("beauty_subscription.chk_continue_btn");
    }

    @And("^I click on subscribe button to create subscription$")
    public void I_click_on_subscribe_button_to_create_subscription() throws Throwable {

        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the continue button on create subscription checkout page",
                Elements.elementPresent("beauty_subscription.create_subscription_btn"));
        Clicks.click("beauty_subscription.create_subscription_btn");
    }

    @And("^I see user successfully subscribed for beautybox$")
    public void subscriptionConfirmation() throws Throwable{
        Thread.sleep(6000);
        Assert.assertTrue("Error - User is unable to subscribe for beautybox",
                Elements.elementPresent("beauty_subscription.subscription_confirmation"));
    }

    @And("^I should(.*) see subscriptionDate on MS page$")
    public void subscriptionDateOnMSPage(String negate) throws  Exception{
        if(StringUtils.isEmpty(negate)){
            WebDriverManager.getWebDriver().navigate().refresh();
            Thread.sleep(2000);
            Assert.assertTrue("Error - Should show Subscription Date", isLabelExists("my_subscription.subscriptionDate", "Subscription Date:"));
        } else {
            WebDriverManager.getWebDriver().navigate().refresh();
            Thread.sleep(2000);
            Assert.assertFalse("Error - Should not show Subscription Date", isLabelExists("my_subscription.subscriptionDate", "Subscription Date:"));
        }

    }

    private boolean isLabelExists(String element, String label) {
        if(Elements.elementPresent(Elements.element(element))) {
            String item = Elements.findElement(Elements.element(element)).getText();
            return item.contains(label);
        }
        return false;
    }

    @And("^I should(.*) see cancellationDate on MS page$")
    public void cancellationDateOnMSPage(String negate) throws  Exception{
        if(StringUtils.isEmpty(negate)){
            WebDriverManager.getWebDriver().navigate().refresh();
            Thread.sleep(2000);
            Assert.assertTrue("Error - Should show Cancellation Date", isLabelExists("my_subscription.cancellationDate", "Cancellation Date:"));
        } else {
            WebDriverManager.getWebDriver().navigate().refresh();
            Thread.sleep(2000);
            Assert.assertFalse("Error - Should not show Cancellation Date", isLabelExists("my_subscription.cancellationDate", "Cancellation Date:"));
        }
    }


    @When("^I click on cancel subscription to cancel the beautybox subscription$")
    public void I_click_on_cancel_subscription_to_cancel_the_beautybox_subscription() throws Throwable {

        Thread.sleep(3000);
        Navigate.browserRefresh();
        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the cancel subscription button on manage subscription page",
                Elements.elementPresent("beauty_subscription.cancel_subscription"));
        Clicks.click("beauty_subscription.cancel_subscription");


    }

    @And("^I click on yes for cancel subscription confirmation$")
    public void I_click_on_yes_for_cancel_subscription_confirmation() throws Throwable {

        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the cancel subscription button on manage subscription page",
                Elements.elementPresent("beauty_subscription.cancel_btn_accept"));
        Clicks.click("beauty_subscription.cancel_btn_accept");

    }

     @Then ("^I capture subscription status after canceling the subscription$")
    public void I_capture_subscription_status_after_canceling_the_subscription() throws Throwable{
        Thread.sleep(5000);
        String strSubScriptionStatus =  Elements.findElement(Elements.element("beauty_subscription.cancel_subscription_status")).getText();

         if(strSubScriptionStatus.contains("CANCELLED")){
             System.out.println("Subscription Status after cancellation is: "+ strSubScriptionStatus);
         }else{
             Assert.fail("Subscription status is not CANCELLED_PENDING");
         }

         //commented this code as per latest change on cancelled policy

//         if ((GenericUtilsBeauty.use_date_time()) >= 11){
//              if(strSubScriptionStatus.contains("CANCELLED")){
//             System.out.println("Subscription Status after cancellation is: "+ strSubScriptionStatus);
//           }else{
//                  Assert.fail("Subscription status is not CANCELLED_PENDING");
//              }
//         }else if(strSubScriptionStatus.contains("CANCELLED")) {
//             System.out.println("Subscription Status after cancellation is: "+ strSubScriptionStatus);
//         }else{
//             Assert.fail("Subscription status is not CANCELLED");
//         }
    }

    @Then("^I Enter \"([^\"]*)\" Information$")
    public static void iEnterInformationForCard(String cardtype) throws Throwable {
        Map<String, String> creditCardDetails = new HashMap<>();
        creditCardDetails = GenericUtilsBeauty.getCreditCardDetails(cardtype);
        DropDowns.selectByValue("beauty_subscription.select_card", creditCardDetails.get("card_type").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_card", creditCardDetails.get("card_number").toString());
        DropDowns.selectByValue("beauty_subscription.select_month", creditCardDetails.get("expiration_date").toString());
        DropDowns.selectByValue("beauty_subscription.select_year", creditCardDetails.get("expiration_year").toString());

    }


    @And("^I enter new payment \"([^\"]*)\" and billing information")
    public static void iEnterNewInformationForCard(String cardtype2) throws Throwable {
        Map<String, String> newCreditCardDetails = new HashMap<>();
        newCreditCardDetails = GenericUtilsBeauty.getNewCreditCardDetails(cardtype2);
        DropDowns.selectByValue("beauty_subscription.select_card", newCreditCardDetails.get("card_type").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_card", newCreditCardDetails.get("card_number").toString());
        DropDowns.selectByValue("beauty_subscription.select_month", newCreditCardDetails.get("expiration_date").toString());
        DropDowns.selectByValue("beauty_subscription.select_year", newCreditCardDetails.get("expiration_year").toString());

    }



    @Then("^I enter address \"([^\"]*)\" Information$")
    public static void iEnterInformationForAddress(String shippingaddress) throws Throwable {
        Map<String, String> subscriptionAddressDetails = new HashMap<>();
        subscriptionAddressDetails = GenericUtilsBeauty.getAddressDetailsForSubscription(shippingaddress);
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_address_fname", subscriptionAddressDetails.get("first_name").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_address_lname", subscriptionAddressDetails.get("last_name").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_address1", subscriptionAddressDetails.get("address_line1").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_addr_city", subscriptionAddressDetails.get("sub_city").toString());
        DropDowns.selectByValue("beauty_subscription.add_shipping_addr_state", subscriptionAddressDetails.get("sub_state").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_addr_zip", subscriptionAddressDetails.get("zip_code").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_addr_phno", subscriptionAddressDetails.get("phone_no").toString());

        //Providing hardcode value as there is no functionality behind this email
         if (Elements.elementPresent("beauty_subscription.select_email")) {
             TextBoxes.typeTextbox("beauty_subscription.select_email", strEmailId);
            }
        }

    @And("^I enter add new \"([^\"]*)\" Information$")
    public static void iEnterAddNewShippingAddress(String shippingAddress2) throws Throwable {
        Map<String, String> subscriptionAddNewAddressDetails = new HashMap<>();
        subscriptionAddNewAddressDetails = GenericUtilsBeauty.getAddNewShippingAddress(shippingAddress2);
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_address_fname", subscriptionAddNewAddressDetails.get("first_name").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_address_lname", subscriptionAddNewAddressDetails.get("last_name").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_address1", subscriptionAddNewAddressDetails.get("address_line1").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_addr_city", subscriptionAddNewAddressDetails.get("sub_city").toString());
        DropDowns.selectByValue("beauty_subscription.add_shipping_addr_state", subscriptionAddNewAddressDetails.get("sub_state").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_addr_zip", subscriptionAddNewAddressDetails.get("zip_code").toString());
        TextBoxes.typeTextbox("beauty_subscription.add_shipping_addr_phno", subscriptionAddNewAddressDetails.get("phone_no").toString());

        //Providing hardcode value as there is no functionality behind this email
        if (Elements.elementPresent("beauty_subscription.select_email")) {
            TextBoxes.typeTextbox("beauty_subscription.select_email", strEmailId);
        }
    }

    @And ("^I click on change button to add new shipping address$")
    public void I_click_on_change_button_to_add_new_shipping_address() throws Throwable{
        Navigate.browserRefresh();
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify change button to add new shipping address on create/manage subscription",
                Elements.elementPresent("beauty_subscription.create_shipping_change_btn"));
        Clicks.click("beauty_subscription.create_shipping_change_btn");
    }


    @And ("^I click on change button or add new to add new shipping address$")
    public void I_click_on_change_button_or_add_new_to_add_new_shipping_address() throws Throwable{
        Navigate.browserRefresh();
        Thread.sleep(2000);

        if (Elements.elementPresent("beauty_subscription.add_new_shipping_address")) {
            Clicks.click("beauty_subscription.add_new_shipping_address");
        } else if(Elements.elementPresent("beauty_subscription.create_shipping_change_btn")) {
            Clicks.click("beauty_subscription.create_shipping_change_btn");
        }else{
            Assert.fail("Unable to identify shipping address button");
        }

//        Assert.assertTrue("Error - Unable to identify change button to add new shipping address on create/manage subscription",
//                Elements.elementPresent("beauty_subscription.create_shipping_change_btn"));
//        Clicks.click("beauty_subscription.create_shipping_change_btn");
    }

    @And ("^I click on change button to add new payment card$")
    public void I_click_on_change_button_to_add_new_payment_card() throws Throwable{
        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the change button on create/manage subscription",
                Elements.elementPresent("beauty_subscription.create_payment_change_btn"));
        Clicks.click("beauty_subscription.create_payment_change_btn");
    }


    @Then ("^I click on edit payment card on beauty ms$")
    public void I_click_on_edit_payment_card_on_beauty_ms() throws Throwable{
        Clicks.click("beauty_subscription.create_payment_change_btn");
        Thread.sleep(3000);
        Assert.assertTrue("Error - Unable to identify the edit button on MS for Payment Card",
                Elements.elementPresent("beauty_subscription.ms_payment_card_edit"));
        Thread.sleep(2000);
        Clicks.click("beauty_subscription.ms_payment_card_edit");
        List<String> strPaymentMonths = DropDowns.getAllValues("beauty_subscription.ms_edit_payment_month");
        System.out.println("strPaymentMonths:::::"+strPaymentMonths);
                strPaymentMonths.forEach(strMonths -> {
                    if (strMonths != null && !strMonths.equalsIgnoreCase("Month"))
                        DropDowns.selectByText("beauty_subscription.ms_edit_payment_month", strMonths);
                        System.out.println("strMonths:::" + strMonths);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Clicks.click("beauty_subscription.ms_payment_save");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Clicks.click("beauty_subscription.create_payment_change_btn");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Clicks.click("beauty_subscription.ms_payment_card_edit");


                    });

            }


    public static String strErrMsg = "The shipping address entered isn't valid. Please try again.";
    public static String strErrMsgFname = "Please remove any special characters.";
    public static String strContiguousError = "We're sorry, Macy's Beauty Box is currently only available in the 48 contiguous United States. We cannot ship to Alaska or Hawaii at this time.";
    public static String strPOboxError = "We're sorry, Macy's Beauty Box cannot ship to PO, APO, or GPO boxes at this time.";
    public static String strNoAptNum = "This address requires a valid apartment, floor or suite number.";
    public static String strAddressFixError="We noticed your shipping address was incorrect so we ﬁxed it. Please verify your address below.";

    @And ("^I see error message to the user$")
    public void I_see_error_message_to_the_user() throws Throwable{
        Thread.sleep(3000);
        if(Elements.elementPresent("beauty_subscription.shipping_address_invalid_err")){
            String strErrorMessage =  Elements.findElement(Elements.element("beauty_subscription.shipping_address_invalid_err")).getText();
            if (strErrorMessage.equals(strErrMsg)){
                Assert.assertTrue("Displaying valid error message when user enter invalid address::"+strErrorMessage, true);
                System.out.println("strErrorMessage::"+strErrorMessage);
            }else if(strErrorMessage.equals(strContiguousError)) {
                Assert.assertTrue("Displaying valid error message when user enter invalid address::" + strErrorMessage, true);
                System.out.println("strErrorMessage::" + strErrorMessage);
            }else if(strErrorMessage.equals(strPOboxError)) {
                Assert.assertTrue("Displaying valid error message when user enter invalid address::" + strErrorMessage, true);
                System.out.println("strErrorMessage::" + strErrorMessage);
            }else if(strErrorMessage.equals(strNoAptNum)) {
                Assert.assertTrue("Displaying valid error message when user enter invalid address::" + strErrorMessage, true);
                System.out.println("strErrorMessage::" + strErrorMessage);
            }else if(strErrorMessage.equals(strAddressFixError)) {
                Assert.assertTrue("Displaying valid error message when user enter invalid address::" + strErrorMessage, true);
                System.out.println("strErrorMessage::" + strErrorMessage);
            }else{
                Assert.fail("Its not throwing valid error message if it is invalid address::"+strErrorMessage);
            }
        }
        if(Elements.elementPresent("beauty_subscription.error_message_fname")){
            String strErrorMessageFname =  Elements.findElement(Elements.element("beauty_subscription.error_message_fname")).getText();
             if(strErrorMessageFname.equals(strErrMsgFname)){
            Assert.assertTrue("Displaying valid error message when user enter invalid address::"+strErrorMessageFname, true);
                 System.out.println("strErrorMessageFname::"+strErrorMessageFname);
            }
             else{
                 Assert.fail("Its not throwing valid error message if it is invalid address::"+strErrorMessageFname);
                }
        }

    }

    @Then("^I select address2 as my default address for subscription$")
     public void I_select_address2_as_my_default_address_for_subscription() throws Throwable {

        Thread.sleep(2000);
        List<WebElement> list = Elements.findElements("beauty_subscription.radio_shipping_address");
        Boolean is_selected = list.get(0).isSelected();
        if (is_selected == true) {
            list.get(1).click();
        } else {
            list.get(0).click();
        }

    }

    @Then("^I select payment card2 as my default payment for subscription$")
    public void I_select_payment_card2_as_my_default_payment_for_subscription() throws Throwable{
        Thread.sleep(3000);
        List<WebElement> list = Elements.findElements("beauty_subscription.radio_payment_card_update");
        Boolean is_selected = list.get(0).isSelected();
        if (is_selected == true) {
            list.get(1).click();
        } else {
            list.get(0).click();
        }
    }
    @And ("^I click on save to save the updated payment card$")
    public void I_click_on_save_to_save_the_updated_payment_card() throws Throwable{
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify the save button on manage/create subscription for updating the default payment card",
                Elements.elementPresent("beauty_subscription.payment_card_update_save"));
        Clicks.click("beauty_subscription.payment_card_update_save");
    }


    @Then("^I validate the updated shipping address information$")
    public void I_validate_the_updated_shipping_address_information() throws Throwable{

        String strPaymentCardInfo =  Elements.findElement(Elements.element("beauty_subscription.shipping_address_info")).getText();

        if(strPaymentCardInfo != null && strPaymentCardInfo.contains("scott")){
            Assert.assertTrue("Updated the payment card selected as::"+strPaymentCardInfo, true);
            System.out.println("Updated the payment card selected as::"+strPaymentCardInfo);
        }else{
            Assert.fail("Updated wrong payment card type for beauty subscription");
        }

    }

    @Then("^I validate the updated card information$")
    public void I_validate_the_updated_card_information() throws Throwable{

        Thread.sleep(4000);
     String strPaymentCardInfo =  Elements.findElement(Elements.element("beauty_subscription.payment_card_info")).getText();

        if(strPaymentCardInfo != null && strPaymentCardInfo.contains("MasterCard")){
            Assert.assertTrue("Updated the payment card selected as::"+strPaymentCardInfo, true);
            System.out.println("Updated the payment card selected as::"+strPaymentCardInfo);
        }else{
            Assert.fail("Updated wrong payment card type for beauty subscription");
        }

    }



}
