package com.macys.sdt.projects.Stores.Omniclient.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Omniclient.steps.MEW.mcom.OmniClientMobileSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static com.macys.sdt.framework.interactions.Elements.elementShouldBePresent;
import static com.macys.sdt.framework.interactions.Elements.findElement;
import static com.macys.sdt.projects.Stores.Omniclient.utils.OmniclientUtils.setWindowToiPhoneView;


public class omniclient_mobile_steps extends StepUtils {


    @Given("^I launch the bloomingdales's omniclient page on mobile$")
    public void iLaunchTheBloomingdalesSOmniclientPageOnMobile() throws Throwable {
        Navigate.visit("login_page");
        Wait.forPageReady();
        shouldBeOnPage("login_page");
        setWindowToiPhoneView();
    }

    @When("^I enter BLM \"([^\"]*)\" in username field on OmniClient mobile login page$")
    public void iEnterBLMInUsernameFieldOnOmniClientMobileLoginPage(String associateID) throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", associateID);
    }

    @Then("^I enter BLM \"([^\"]*)\" in password field on OmniClient mobile login page$")
    public void iEnterBLMInPasswordFieldOnOmniClientMobileLoginPage(String password) throws Throwable {
        TextBoxes.typeTextbox("login_page.password_textbox", password);

    }


    @When("^I sign into OmniClient BLM mobile application as Associate$")
    public void iSignIntoOmniClientBLMMobileApplicationAsAssociate() throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", "10000083");
        TextBoxes.typeTextbox("login_page.password_textbox", "Temp$Pass83");
        Clicks.click("login_page.login_button");
        Thread.sleep(8000);
        Wait.untilElementNotPresent("dashboard_page.loading_page");
//        Wait.untilElementPresent("dashboard_page.associate_homepage");
//        elementShouldBePresent("dashboard_page.associate_homepage");
    }

    @When("^I sign into OmniClient BLM mobile application as Selling Manager")
    public void iSignIntoOmniClientBLMMobileApplicationAsSellingManager() throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", "10000089");
        TextBoxes.typeTextbox("login_page.password_textbox", "Temp$Pass89");
        Clicks.click("login_page.login_button");
        Thread.sleep(8000);
        Wait.untilElementNotPresent("dashboard_page.loading_page");
//        Wait.untilElementPresent("dashboard_page.associate_homepage");
//        elementShouldBePresent("dashboard_page.associate_homepage");
    }

    @Then("^I click on My Lists tab$")
    public void iClickOnMyListsTab() throws Throwable {
        Wait.untilElementPresent("dashboard_page.more_options_panel");
        Clicks.click("dashboard_page.my_macys_to_dos_text");
        Wait.untilElementPresent("dashboard_page.my_macys_to_dos_page");
        elementShouldBePresent("dashboard_page.my_macys_to_dos_page");
    }

    @And("^I write the email in the Email field \"([^\"]*)\"$")
    public void i_write_the_email_in_the_email_field_something(String email) throws Throwable {
        Wait.untilElementPresent("client.email_address_create_contact");
        TextBoxes.typeTextbox("client.email_address_create_contact", email);
    }

    @Then("^I check that the phone number \"([^\"]*)\" is marked as preferred BLM$")
    public void i_check_that_the_phone_number_something_is_marked_as_preferred_blm(String PrefNb) throws Throwable {
        Wait.untilElementPresent("client.pref_phone_number_blm");
        String prefNumber = findElement("client.pref_phone_number_blm").getText().trim();
        System.out.println(prefNumber);
        Assert.assertTrue(prefNumber.contains(PrefNb));


    }

    @And("^I check that the phone number \"([^\"]*)\" is marked as primary BLM$")
    public void i_check_that_the_phone_number_something_is_marked_as_primary_blm(String PrimNb) throws Throwable {
        String prefNumber = findElement("dashboard_page.primary_phone_number_blm").getText().trim();
        System.out.println(prefNumber);
        Assert.assertTrue(prefNumber.contains(PrimNb));

    }

    @And("^I check that primary phone is the same as pref phone BLM$")
    public void i_check_that_primary_phone_is_the_same_as_pref_phone_blm() throws Throwable {
        String prefNumber = findElement("dashboard_page.pref_phone_number_blm").getText();
        String primNumber = findElement("dashboard_page.primary_phone_number_blm").getText();

        if (prefNumber.equals(primNumber)) {

            System.out.println("Primary number is the same as the preferred number BLM");
        } else {
            Assert.fail("Primary and preferred numbers do not match BLM");
        }

    }

    @And("^I should see the Affiliated with Me column on the Results page$")
    public void iShouldSeeTheAffiliatedWithMeColumnOnTheResultsPage() throws Throwable {
        Wait.untilElementPresent("search.affiliated_with_me");
        elementShouldBePresent("search.affiliated_with_me");
    }

    @And("^we add a new client on mobile BLM$")
    public void we_add_a_new_client_on_mobile_blm(List<String> aDetail) throws Throwable {
        String phone = aDetail.get(0);
        String Fname = aDetail.get(2);
        String Lname = aDetail.get(4);
        String prefName = aDetail.get(6);
        String reqAddress = aDetail.get(8);
        String additionalAddress = aDetail.get(10);
        String city = aDetail.get(12);
        String zip = aDetail.get(14);
        String hint = aDetail.get(16);
        String email = aDetail.get(18);
        OmniClientMobileSteps.i_click_on_the_search_all_clients_button();
        OmniClientMobileSteps.i_select_telephone_button_from_the_search_all_clients_page();
        OmniClientMobileSteps.i_input_telephone_number_something_on_the_telephone_number_search_page(phone);
        OmniClientMobileSteps.i_click_the_search_button_on_the_search_all_clients_page();
        OmniClientMobileSteps.i_tap_on_create_client_button();
        OmniClientMobileSteps.i_write_the_first_name_something_in_the_first_name_field(Fname);
        OmniClientMobileSteps.i_write_the_last_name_something_in_the_last_name_field(Lname);
        OmniClientMobileSteps.i_write_the_preferred_name_something_in_the_preferred_name_field(prefName);
//        OmniClientMobileSteps.i_select_the_phone_type_from_phone_type_dropdown();
        OmniClientMobileSteps.i_write_the_required_address_something(reqAddress);
        OmniClientMobileSteps.i_write_the_second_address_something(additionalAddress);
        i_write_the_email_in_the_email_field_something(email);
        OmniClientMobileSteps.i_write_the_city_in_the_city_field_something(city);
        OmniClientMobileSteps.i_write_the_zip_code_something_in_the_zip_code_field(zip);
        OmniClientMobileSteps.i_select_random_state_from_state_dropdown_mobile();
        OmniClientMobileSteps.i_tap_on_save_button_from_add_client_page();
        OmniClientMobileSteps.i_write_a_hint_in_the_hint_field_something(hint);
        OmniClientMobileSteps.i_select_preferred_contact_method_as_phone_from_dropdown();
        OmniClientMobileSteps.i_tap_on_save_button_from_add_client_page();
        OmniClientMobileSteps.i_tap_ok_in_the_successfully_added_client_popup();

    }

    @Then("^existing email \"([^\"]*)\" information is displayed$")
    public void existing_email_something_information_is_displayed(String email) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("client.contact_profile_email");
        String emailAddress = findElement("client.contact_profile_email").getText().trim();
        System.out.println(emailAddress);
        Assert.assertTrue(emailAddress.contains(email));
        Assert.assertTrue(emailAddress.contains("Home"));
    }

    @And("^I tap on Edit Email from Edit Contact page$")
    public void i_tap_on_edit_email_from_edit_contact_page() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("client.edit_email_btn_edit_client_page");
        Clicks.click("client.edit_email_btn_edit_client_page");
    }

    @Then("^Save button is displayed in Edit Email page$")
    public void save_button_is_displayed_in_edit_email_page() throws Throwable {
        Wait.untilElementPresent("client.save_button_edit_email_page");
        Elements.elementShouldBePresent("client.save_button_edit_email_page");
    }

    @And("^Cancel button is displayed in Edit Email page$")
    public void cancel_button_is_displayed_in_edit_email_page() throws Throwable {
        Wait.untilElementPresent("client.cancel_button_edit_email_page");
        Elements.elementShouldBePresent("client.cancel_button_edit_email_page");
    }


    @And("^I tap on Edit Phone from Edit Contact page BLM$")
    public void i_tap_on_edit_phone_from_edit_contact_page_blm() throws Throwable {
        Wait.untilElementPresent("client.edit_phone_btn_edit_client_page");
        Thread.sleep(2000);
        Clicks.click("client.edit_phone_btn_edit_client_page");
    }

    @And("^I click on the Search My Book button$")
    public void i_click_on_the_search_my_book_button() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("search.search_my_book_btn");
        Clicks.click("search.search_my_book_btn");
    }

    @Then("^I should see the Search My Book page$")
    public void i_should_see_the_search_my_book_page() throws Throwable {
        Wait.untilElementPresent("search.search_my_book_page");
        elementShouldBePresent("search.search_my_book_page");
    }

    @When("^I select By Name button from the Search My Book page$")
    public void i_select_by_name_button_from_the_search_my_book_page() throws Throwable {
        Wait.untilElementPresent("search.search_by_nm_btn");
        Clicks.click("search.search_by_nm_btn");
    }

    @Then("^I should see the By Name search page$")
    public void i_should_see_the_by_name_search_page() throws Throwable {
        Wait.untilElementPresent("search.nm_search_page");
        elementShouldBePresent("search.nm_search_page");
    }

    @When("^I input the name \"([^\"]*)\" on the By Name search page$")
    public void i_input_the_name_something_on_the_by_name_search_page(String name) throws Throwable {
        Wait.untilElementPresent("search.search_name_input");
        TextBoxes.typeTextbox("search.search_name_input", name);
    }

    @And("^I click the Search button on the Search My Book page$")
    public void i_click_the_search_button_on_the_search_my_book_page() throws Throwable {
        Wait.untilElementPresent("search.search_name_btn");
        Clicks.click("search.search_name_btn");
        Wait.untilElementNotPresent("dashboard_page.loading_page");
    }

    @Then("^I should see the search results on the Search My Book$")
    public void i_should_see_the_search_results_on_the_search_my_book() throws Throwable {
        Wait.untilElementPresent("search.search_my_book_results");
        elementShouldBePresent("search.search_my_book_results");
    }

    @When("^I click on the Client on the My Book Results page$")
    public void i_click_on_the_client_on_the_my_book_results_page() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("search.search_my_book_first_client");
        Clicks.hoverForSelection("search.search_my_book_first_client");
        Clicks.click("search.search_my_book_first_client");
    }

    @Then("^I should see the Contact Profile page$")
    public void i_should_see_the_contact_profile_page() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("client.contact_profile_page_BLM");
        elementShouldBePresent("client.contact_profile_page_BLM");
    }

    @And("^I update the existing email to \"([^\"]*)\"$")
    public void i_update_the_existing_email_to_something(String mail) throws Throwable {
        Wait.untilElementPresent("client.edit_email_page");
        Wait.untilElementPresent("client.edit_email_input_1st");
        TextBoxes.typeTextbox("client.edit_email_input_1st", mail);
    }

    @And("^I click on the Save button in Edit Email page$")
    public void i_click_on_the_save_button_in_edit_email_page() throws Throwable {
        Wait.untilElementPresent("client.edit_email_save_btn");
        Clicks.click("client.edit_email_save_btn");
    }

    @And("^I click OK on the save confirmation popup$")
    public void i_click_ok_on_the_save_confirmation_popup() throws Throwable {
        Wait.untilElementPresent("client.edit_email_confirmation_ok");
        Clicks.click("client.edit_email_confirmation_ok");
    }

    @And("^the preferred information radio buttons should be preselected in Edit Email page$")
    public void the_preferred_information_radio_buttons_should_be_preselected_in_edit_email_page() throws Throwable {
        Wait.untilElementPresent("client.edit_email_first_radio");
        String checkedAtrib = findElement("client.edit_email_first_radio").getAttribute("checked");
        System.out.println(checkedAtrib);
        Assert.assertTrue(checkedAtrib.contains("true"));
    }

    @Then("^I check that Address \"([^\"]*)\" is marked as preferred BLM$")
    public void i_check_that_address_something_is_marked_as_preferred_blm(String prefAddress) throws Throwable {
        Wait.untilElementPresent("client.address_section_contact_read_only");
        Thread.sleep(4000);
        String prefADR = findElement("client.address_section_contact_read_only").getText();
        System.out.println(prefADR);
        Assert.assertTrue(prefADR.contains(prefAddress));
    }
//    TODO: remove deprecated method + json elements and update FFs

//    @And("^I check that \"([^\"]*)\" is marked as primary address BLM$")
//    public void i_check_that_something_is_marked_as_primary_address_blm(String primAddress) throws Throwable {
//        String primADR = findElement("client.address_section_contact_read_only").getText();
//        System.out.println(primADR);
//        Assert.assertTrue(primADR.contains(primAddress));
//
//    }

    @Then("^I should see the error popup from CLIENT INFO section BLM mobile$")
    public void i_should_see_the_error_popup_from_client_info_section_blm_mobile() throws Throwable {
        Wait.untilElementPresent("client.error_popup_client_info");
        elementShouldBePresent("client.error_popup_client_info");
        String errorMsg = findElement("client.error_popup_client_info").getText();
        Assert.assertTrue(errorMsg.contains("Error"));
    }

    @And("^I click on the OK button on the error popup from CLIENT INFO section BLM mobile$")
    public void i_click_on_the_ok_button_on_the_error_popup_from_client_info_section_blm_mobile() throws Throwable {
        Wait.untilElementPresent("client.error_popup_client_info_ok_btn");
        Clicks.click("client.error_popup_client_info_ok_btn");
    }

    @Then("^I should see the \"([^\"]*)\" mail on Client Profile$")
    public void i_should_see_the_something_mail_on_client_profile(String correctedemail) throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("client.contact_profile_email");
        Thread.sleep(1000);
        String prefInfo = findElement("client.contact_profile_email").getText().trim();
        System.out.println(prefInfo);
        Assert.assertTrue(prefInfo.contains(correctedemail));
    }

    @And("^I verify that each incorrect email is rejected with an ERROR message mobile BLM:$")
    public void i_verify_that_each_incorrect_email_is_rejected_with_an_error_message_mobile_blm(DataTable emails) throws Throwable {
        for (List<String> aEmail : emails.raw()) {
            String emailItem = aEmail.get(0);
            i_update_the_existing_email_to_something(emailItem);
            i_click_on_the_save_button_in_edit_email_page();
            i_should_see_the_error_popup_from_client_info_section_blm_mobile();
            i_click_on_the_ok_button_on_the_error_popup_from_client_info_section_blm_mobile();
        }
    }

    @And("^I verify that each incorrect email is autocorrected mobile BLM:$")
    public void i_verify_that_each_incorrect_email_is_autocorrected_mobile_blm(DataTable emails) throws Throwable {
        for (List<String> list : emails.raw()) {
            String incorrect = list.get(0);
            String corrected = list.get(1);
            i_tap_on_edit_button_from_contact_profile_page_blm();
            i_tap_on_edit_email_from_edit_contact_page();
            i_update_the_existing_email_to_something(incorrect);
            i_click_on_the_save_button_in_edit_email_page();
            OmniClientMobileSteps.i_tap_ok_in_the_add_confirmation_popup();
            i_should_see_the_something_mail_on_client_profile(corrected);
        }
    }

    @And("^I select Preferred Contact method as email from dropdown$")
    public void i_select_preferred_contact_method_as_email_from_dropdown() throws Throwable {
        Wait.untilElementPresent("client.pref_contact_add_to_book");
        DropDowns.selectByText("client.pref_contact_add_to_book", "Email");
    }

    @Then("^I should see the email address required attention popup$")
    public void i_should_see_the_email_address_required_attention_popup() throws Throwable {
        Wait.untilElementPresent("client.error_popup_client_info");
        elementShouldBePresent("client.error_popup_client_info");
        String errorMsg = findElement("client.error_popup_client_info").getText();
        Assert.assertTrue(errorMsg.contains("Attention"));
        Assert.assertTrue(errorMsg.contains("Please select a preferred email"));
    }

    @Then("^I should see the Loyallist Level$")
    public void i_should_see_the_Loyallist_Level() throws Throwable {
        Wait.untilElementPresent("client.customer_profile_loyallist_icon");
        elementShouldBePresent("client.customer_profile_loyallist_icon");
    }

    @And("^I should see the Loyallist ID$")
    public void i_should_see_the_loyallist_id() throws Throwable {
        Wait.untilElementPresent("client.customer_profile_loyallist_id");
        elementShouldBePresent("client.customer_profile_loyallist_id");
    }

    @And("^I should see the Manage Customer button on the Client Profile page$")
    public void i_should_see_the_manage_customer_button_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.customer_profile_manage_customer_btn");
        elementShouldBePresent("client.customer_profile_manage_customer_btn");
    }

    @When("^I click the Manage Customer button from Client Profile page$")
    public void i_click_the_manage_customer_button_from_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.customer_profile_manage_customer_btn");
        Clicks.click("client.customer_profile_manage_customer_btn");
    }

    @Then("^I should see the Manage Customer page$")
    public void i_should_see_the_manage_customer_page() throws Throwable {
        Wait.untilElementPresent("client.manage_customer_page_title");
        elementShouldBePresent("client.manage_customer_page_title");
        elementShouldBePresent("client.manage_customer_page_assoc_field");
        elementShouldBePresent("client.manage_customer_page_phone_field");
        elementShouldBePresent("client.manage_customer_page_email_field");
        elementShouldBePresent("client.manage_customer_page_address_field");
        elementShouldBePresent("client.manage_customer_page_pref_cont_field");
    }

    @When("^I tap on Edit button from Contact Profile page BLM$")
    public void i_tap_on_edit_button_from_contact_profile_page_blm() throws Throwable {
        Wait.untilElementPresent("client.contact_profile_manage_customer_btn");
        Thread.sleep(1000);
        Clicks.click("client.contact_profile_manage_customer_btn");
    }


}