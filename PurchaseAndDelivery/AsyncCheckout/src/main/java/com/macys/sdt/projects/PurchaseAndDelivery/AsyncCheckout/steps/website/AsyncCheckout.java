package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.steps.website;

import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.utils.Generic;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ContactInfoSection;
import com.macys.sdt.shared.actions.website.bcom.pages.my_account.MyAccount;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAddressBook;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.utils.db.AddressService;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.framework.utils.StepUtils.signedIn;

public class AsyncCheckout {

    @When("^I submit the (shipping|payment) section with missing apt address$")
    public void enterMissingApt_Floor_Suite_Address(String page) throws Throwable {
        HashMap<String, String> opts = new HashMap<>();
        opts.put("missing_apt_address", "true");
        Generic.fillData(page, opts);
    }

    @Then("^I should see following (error|warning) message:$")
    public void verifyErrorWarningMessage(String message_type, List<String> message) throws Throwable {
        switch (message_type) {
            case "error":
                String actual_error_message = Elements.getText("responsive_checkout.error_container");
                Assert.assertTrue("ERROR:: Expected error message is not displayed", actual_error_message.equals(message.get(0)));
                break;
            case "warning":
                String actual_warning_message = Elements.getText("responsive_checkout.warning_container");
                Assert.assertTrue("ERROR:: Expected warning message is not displayed", actual_warning_message.contains(message.get(0)));
                break;
        }

    }

    @Then("^I should see the corrected city for (shipping|billing) address$")
    public void iShouldSeeTheCorrectedCityInAddress(String address) throws Throwable {
        String user_id = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        String expected_city = new AddressService().getAddressLogByAttribute(user_id, true, false, false);
        switch (address) {
            case "shipping":
                String actual_shipping_address = Elements.findElement("responsive_shipping_section.shipping_address_display").getText();
                Assert.assertTrue("ERROR:: City is not auto corrected", actual_shipping_address.contains(expected_city));
                break;
            case "billing":
                String actual_billing_city = "";
                if (signedIn()) {
                    Clicks.click("responsive_checkout_signed_in.change_credit_card_button");
                    Clicks.click("responsive_checkout_signed_in.edit_credit_card");
                    actual_billing_city = Elements.findElement("responsive_payment_signin_section.address_city").getAttribute("value");
                } else {
                    Clicks.click("responsive_payment_guest_section.edit_payment_info");
                    actual_billing_city = Elements.findElement("responsive_payment_guest_section.address_city").getAttribute("value");
                }
                Assert.assertTrue("ERROR:: City is not auto corrected", actual_billing_city.equals(expected_city));
                break;
        }
    }


    @Then("^I should see the corrected state for (shipping|billing) address$")
    public void iShouldSeeTheCorrectedStateInAddress(String address) throws Throwable {
        String user_id = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        String expected_state = new AddressService().getAddressLogByAttribute(user_id, false, true, false);
        switch (address) {
            case "shipping":
                String actual_shipping_address = Elements.findElement("responsive_shipping_section.shipping_address_display").getText();
                Assert.assertTrue("ERROR:: State is not auto corrected", actual_shipping_address.contains(expected_state));
                break;
            case "billing":
                String actual_billing_state = "";
                if (signedIn()) {
                    Clicks.click("responsive_checkout_signed_in.change_credit_card_button");
                    Clicks.click("responsive_checkout_signed_in.edit_credit_card");
                    actual_billing_state = Elements.findElement("responsive_payment_signin_section.address_state").getAttribute("value");
                } else {
                    Clicks.click("responsive_payment_guest_section.edit_payment_info");
                    actual_billing_state = Elements.findElement("responsive_payment_guest_section.address_state").getAttribute("value");
                }
                Assert.assertTrue("ERROR:: State is not auto corrected", actual_billing_state.equals(expected_state));
                break;
        }
    }

    @When("^I submit the (shipping|payment) section with invalid \"([^\"]*)\"$")
    public void iSubmitTheSectionWithInvalidData(String page, String data) throws Throwable {
        HashMap<String, String> opts = new HashMap<>();
        switch (data) {
            case ("zip code"):
                opts.put("invalid_ZIP_code", "true");
                break;
            case ("state"):
                opts.put("invalid_state_name", "true");
                break;
            case ("city"):
                opts.put("invalid_city_name", "true");
                break;
        }
        Generic.fillData(page, opts);
    }

    @Then("^I should see the corrected zip code for (shipping|billing) address$")
    public void iShouldSeeTheCorrectedZipCodeInAddress(String address) throws Throwable {
        String user_id = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        String expected_zip_code = new AddressService().getAddressLogByAttribute(user_id, false, false, true);
        switch (address) {
            case "shipping":
                String actual_shipping_address = Elements.findElement("responsive_shipping_section.shipping_address_display").getText();
                Assert.assertTrue("ERROR:: Zip Code is not auto corrected", actual_shipping_address.contains(expected_zip_code));
                break;
            case "billing":
                String actual_billing_zip_code = "";
                if (signedIn()) {
                    Clicks.click("responsive_checkout_signed_in.change_credit_card_button");
                    Clicks.click("responsive_checkout_signed_in.edit_credit_card");
                    actual_billing_zip_code = Elements.findElement("responsive_payment_signin_section.address_zip_code").getAttribute("value");
                } else {
                    Clicks.click("responsive_payment_guest_section.edit_payment_info");
                    actual_billing_zip_code = Elements.findElement("responsive_payment_guest_section.address_zip_code").getAttribute("value");
                }
                Assert.assertTrue("ERROR:: Zip Code is not auto corrected", actual_billing_zip_code.equals(expected_zip_code));
                break;
        }
    }

    @When("^I submit the (shipping|payment) section with auto correction address having zero prefix in zip code$")
    public void iSubmitTheShippingSectionWithAutoCorrectionAddressHavingZeroPrefixInZipCode(String page) throws Throwable {
        HashMap<String, String> opts = new HashMap<>();
        opts.put("invalid_ZIP_code_0_prefix", "true");
        Generic.fillData(page, opts);
    }

    @When("^I add address with Invalid \"([^\"]*)\" on my address book page$")
    public void iAddAddressWithInvalidOnMyAddressBookPage(String data) throws Throwable {
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        Wait.forPageReady();
        //adding account dashboard experiment
        if(onPage("account_dashboard")){
            Clicks.click("account_dashboard.address_book");
        }
        else {
            new MyAccount().navigateToLeftNavigationPage("my address book");
        }
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        switch (data) {
            case ("zip code"):
                opts.put("invalid_ZIP_code", "true");
                break;
            case ("state"):
                opts.put("invalid_state_name", "true");
                break;
            case ("city"):
                opts.put("invalid_city_name", "true");
                break;
        }
        new MyAddressBook().addAddress(opts);
    }

    @When("^I submit the (shipping|payment) section with DPV code \"([^\"]*)\" address$")
    public void iSubmitTheShippingSectionWithDPVCodeAddress(String page, String code) throws Throwable {
        HashMap<String, String> opts = new HashMap<>();
        switch (code) {
            case ("Y"):
                opts.put("address_with_dpv_y", "true");
                break;
            case ("S"):
                opts.put("address_with_dpv_s", "true");
                break;
            case ("U"):
                opts.put("address_with_dpv_u", "true");
                break;
            case ("N"):
                opts.put("address_with_dpv_n", "true");
                break;
            case ("M"):
                opts.put("address_with_dpv_m", "true");
                break;
            case ("D"):
                opts.put("address_with_dpv_d", "true");
                break;
        }
        Generic.fillData(page, opts);
    }

    @Then("^I should see DPV code \"([^\"]*)\" from address response$")
    public void iShouldSeeDVPCodeFromAddressResponse(String code) throws Throwable {
        String user_id = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        String actual_dvp_code = new AddressService().getDPV(user_id);
        Assert.assertTrue("ERROR:: Expected DPV is not returned for the given address. Expected code is " + code + " but got  " + actual_dvp_code, actual_dvp_code.equals(code));
    }

    @When("^I add address with address having zero prefix in zip code on my address book$")
    public void iAddAddressWithAddressHavingZeroPrefixInZipCodeOnMyAddressBook() throws Throwable {
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        Wait.forPageReady();
        if(onPage("account_dashboard")){
            Clicks.click("account_dashboard.address_book");
        }
        else {
            new MyAccount().navigateToLeftNavigationPage("my address book");
        }
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("invalid_ZIP_code_0_prefix", "true");
        new MyAddressBook().addAddress(opts);
    }

    @When("^I enter contact info on checkout$")
    public void fillContactInfo() {
        ProfileAddress address = new ProfileAddress();
        TestUsers.getRandomValidAddress(null, address);
        Navigate.get(ContactInfoSection.class).addContactInfo(address.getBestPhone());
    }
}