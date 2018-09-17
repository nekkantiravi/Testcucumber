package com.macys.sdt.projects.Stores.Omniclient.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static com.macys.sdt.framework.interactions.Elements.*;
import static com.macys.sdt.projects.Stores.Omniclient.utils.OmniclientUtils.setWindowToiPhoneView;


public class OmniClientMobileSteps extends StepUtils {


    @Given("^I launch the macy's omniclient page on mobile$")
    public void iLaunchTheMacySOmniclientPageOnMobile() throws Throwable {
        Navigate.visit("login_page");
        Wait.forPageReady();
        shouldBeOnPage("login_page");
        setWindowToiPhoneView();
    }

    @When("^I enter \"([^\"]*)\" in username field on OmniClient mobile login page$")
    public void iEnterInUsernameFieldOnOmniClientMobileLoginPage(String associateID) throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", associateID);
    }

    @Then("^I enter \"([^\"]*)\" in password field on OmniClient mobile login page$")
    public void iEnterInPasswordFieldOnOmniClientMobileLoginPage(String password) throws Throwable {
        TextBoxes.typeTextbox("login_page.password_textbox", password);
    }

    @And("^I click Log In button on OmniClient mobile login page$")
    public void iClickLogInButtonOnOmniClientMobileLoginPage() throws Throwable {
        Wait.untilElementPresent("login_page.login_button");
        Clicks.click("login_page.login_button");
        Thread.sleep(8000); //hardcoding sleep because application is taking too long for response
        Wait.untilElementPresent("dashboard_page.hamburger");
    }

    @Then("^I should be logged in as \"(associate|sales manager|general manager|corp admin|corp store exec)\" and see the OmniClient mobile landing page$")
    public void iShouldBeLoggedInAsAndSeeTheOmniClientMobileLandingPage(String role) throws Throwable {
        switch (role.toLowerCase()) {
            case "associate":
                elementShouldBePresent("dashboard_page.associate_homepage");
                break;
            case "sales manager":
                elementShouldBePresent("dashboard_page.sales_manager_homepage");
                break;
        }
    }

    @When("^I sign into OmniClient mobile application as My Shop Selling Manager$")
    public void iSignIntoOmniClientMobileApplicationAsMyShopSellingManager() throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", "10000057");
        TextBoxes.typeTextbox("login_page.password_textbox", "Temp$Pass7");
        Clicks.click("login_page.login_button");
        Thread.sleep(8000);
        Wait.untilElementPresent("dashboard_page.yesterdays_summary");
        elementShouldBePresent("dashboard_page.yesterdays_summary");
    }

    @When("^I sign into OmniClient mobile application as General Manager$")
    public void i_sign_into_omniclient_mobile_application_as_general_manager() throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", "10000059");
        TextBoxes.typeTextbox("login_page.password_textbox", "Temp$Pass9");
        Clicks.click("login_page.login_button");
        Thread.sleep(8000);
        // Assertion step that it landed on the correct page
    }

    @Then("^I should see Selling Manager HomePage$")
    public void iShouldSeeSellingManagerHomePage() throws Throwable {
//        Wait.untilElementPresent("dashboard_page.list_total");
//        String list_total = Elements.findElement("dashboard_page.list_total").getText();
//        System.out.println("The List count is before change --- > " +list_total);
        Wait.untilElementPresent("dashboard_page.yesterdays_summary");
        elementShouldBePresent("dashboard_page.yesterdays_summary");
    }

    @And("^I should see the Switch user input box$")
    public void iShouldSeeTheSwitchUserInputBox() throws Throwable {
        Wait.untilElementPresent("dashboard_page.switch_user_input_box");
        elementShouldBePresent("dashboard_page.switch_user_input_box");
    }

    @When("^I select the switch user input box$")
    public void iSelectTheSwitchUserInputBox() throws Throwable {
        Wait.untilElementPresent("dashboard_page.switch_user_input_box");
        Clicks.click("dashboard_page.switch_user_input_box");
        Wait.forPageReady();
    }

    @Then("^I should see switch user overlay$")
    public void iShouldSeeSwitchUserOverlay() throws Throwable {
        Wait.untilElementPresent("dashboard_page.switch_user_overlay");
        elementShouldBePresent("dashboard_page.switch_user_overlay");
        Thread.sleep(2000);
    }

    @When("^I expand a selling area from My Selling Associates section$")
    public void i_expand_a_selling_area_from_my_selling_associates_section() throws Throwable {
        Wait.untilElementPresent("dashboard_page.first_sm_selling_area_plus_sign");
        Clicks.click("dashboard_page.first_sm_selling_area_plus_sign");
        elementShouldBePresent("dashboard_page.first_sa_in_expanded_selling_area");

    }

    @And("^I click on an associate$")
    public void iClickOnAnAssociate() throws Throwable {
        Wait.untilElementPresent("dashboard_page.first_sa_in_expanded_selling_area");
        Clicks.click("dashboard_page.first_sa_in_expanded_selling_area");
    }


    @When("^I click on the Cancel button on the Switch User overlay$")
    public void iClickOnTheCancelButtonOnTheSwitchUserOverlay() throws Throwable {
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("dashboard_page.cancel_button", 30);
        Clicks.hoverForSelection("dashboard_page.cancel_button");
        Clicks.click("dashboard_page.cancel_button");
        Wait.forPageReady();
    }

    @Then("^I should see a list of associates who are in that staffing zone$")
    public void iShouldSeeAListOfAssociatesWhoAreInThatStaffingZone() throws Throwable {
        Wait.untilElementPresent("dashboard_page.first_sa_in_expanded_selling_area");
        elementShouldBePresent("dashboard_page.first_sa_in_expanded_selling_area");

    }

    @And("^I should see List totals change for selected associate$")
    public void iShouldSeeListTotalsChangeForSelectedAssociate() throws Throwable {
        Wait.untilElementPresent("dashboard_page.list_total");
        elementShouldBePresent("dashboard_page.list_total");
        String list_total = Elements.findElement("dashboard_page.list_total").getText();
        System.out.println("The List count is after change --- > " + list_total);
    }

    @When("^I click the Summary icon on the mobile device$")
    public void iClickTheSummaryIconOnTheMobileDevice() throws Throwable {
        Wait.untilElementPresent("dashboard_page.yesterdays_summary");
        Clicks.click("dashboard_page.yesterdays_summary");
        Wait.forPageReady();
    }

    @Then("^I should see the No Selling Area selected error message$")
    public void iShouldSeeTheNoSellingAreaSelectedErrorMessage() throws Throwable {
        Wait.untilElementPresent("dashboard_page.selling_area_error_popup");
        elementShouldBePresent("dashboard_page.selling_area_error_popup");
    }

    @When("^I click on the OK button on the No Selling Area selected error message$")
    public void iClickOnTheOKButtonOnTheNoSellingAreaSelectedErrorMessage() throws Throwable {
        Wait.untilElementPresent("dashboard_page.selling_area_error_popup");
        Clicks.click("dashboard_page.selling_area_error_ok");
        elementShouldBePresent("dashboard_page.yesterdays_summary");
    }

    @Then("^I should see Yesterday's Summary page$")
    public void iShouldSeeYesterdaySSummaryPage() throws Throwable {
        Wait.untilElementPresent("dashboard_page.yesterdays_summary_title");
        elementShouldBePresent("dashboard_page.yesterdays_summary_title");
        String message = Elements.findElement("dashboard_page.yesterdays_summary_title").getText();
        Assert.assertTrue(message.contains("Yesterday's Summary"));
    }


    @And("^I should see totals for the following under Yesterday's summary:$")
    public void iShouldSeeTotalsForTheFollowingUnderYesterdaySSummary(List<String> section) throws Throwable {
        for (String aTab : section) {
            Wait.untilElementPresent("dashboard_page.yesterdays_summary_title");
            String message = Elements.findElement("dashboard_page.yesterdays_summary_title").getText();
            switch (aTab) {
                case "Client Sales":
                    Assert.assertTrue(message.contains("Client Sales"));
                    break;
                case "Clients Shopped":
                    Assert.assertTrue(message.contains("Clients Shopped"));
                    break;
                case "Prospects to New Customers":
                    Assert.assertTrue(message.contains("Prospects to New Customers"));
                    break;
                case "New to Book":
                    Assert.assertTrue(message.contains("New to Book"));
                    break;
                default:
                    Assert.fail("invalid test example");
            }
        }

    }

    @And("^I should see a list of clients on the Yesterday's summary page$")
    public void iShouldSeeAListOfClientsOnTheYesterdaySSummaryPage() throws Throwable {
        Wait.untilElementPresent("dashboard_page.yesterdays_summary_client_list");
        elementShouldBePresent("dashboard_page.yesterdays_summary_client_list");

    }

    @When("^I select the hamburger icon$")
    public void i_select_the_hamburger_icon() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("dashboard_page.hamburger");
        Clicks.click("dashboard_page.hamburger");
        Wait.forPageReady();
    }

    @And("^I click the switch back button on mobile$")
    public void i_click_the_switch_back_button_on_mobile() throws Throwable {
        Wait.untilElementPresent("dashboard_page.switch_back_button");
        Clicks.click("dashboard_page.switch_back_button");
        Wait.forPageReady();
    }

    @And("^I navigate to My Clients on mobile$")
    public void i_navigate_to_my_clients_on_mobile() throws Throwable {
        Wait.untilElementPresent("dashboard_page.my_clients_button");
        Clicks.click("dashboard_page.my_clients_button");
        Wait.forPageReady();
    }

    @And("^I click the Home button on mobile$")
    public void i_click_the_home_button_on_mobile() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("dashboard_page.home_button");
        Clicks.click("dashboard_page.home_button");
        Wait.forPageReady();
    }

    @Then("^I should see the My Clients page on mobile$")
    public void i_should_see_the_my_clients_page_on_mobile() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("dashboard_page.associate_homepage");
        elementShouldBePresent("dashboard_page.associate_homepage");
        String myclients = findElement("dashboard_page.associate_homepage").getText().trim();
        System.out.println(myclients);
        Assert.assertTrue(myclients.contains("My Clients"));
    }

    @When("^I sign into OmniClient mobile application as Associate$")
    public void iSignIntoOmniClientMobileApplicationAsAssociate() throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", "10000051");
        TextBoxes.typeTextbox("login_page.password_textbox", "Temp$Pass1");
        Clicks.click("login_page.login_button");
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("dashboard_page.associate_homepage");
        elementShouldBePresent("dashboard_page.associate_homepage");
    }

    @When("^I sign into OmniClient mobile application as Selling Manager$")
    public void iSignIntoOmniClientMobileApplicationAsSellingManager() throws Throwable {
        TextBoxes.typeTextbox("login_page.associateID_textbox", "10000058");
        TextBoxes.typeTextbox("login_page.password_textbox", "Temp$Pass8");
        Clicks.click("login_page.login_button");
        Thread.sleep(8000);
        Wait.untilElementPresent("dashboard_page.yesterdays_summary");
        elementShouldBePresent("dashboard_page.yesterdays_summary");
    }

    @Then("^I should see the list of options on the menu$")
    public void iShouldSeeTheListOfOptionsOnTheMenu() throws Throwable {
        Wait.untilElementPresent("dashboard_page.more_options_panel");
        elementShouldBePresent("dashboard_page.more_options_panel");
    }

    @When("^I click on My Macys To Dos tab$")
    public void iClickOnMyMacysToDosTab() throws Throwable {
        Wait.untilElementPresent("dashboard_page.more_options_panel");
        Clicks.click("dashboard_page.my_macys_to_dos_text");
        Wait.untilElementPresent("dashboard_page.my_macys_to_dos_page");
        elementShouldBePresent("dashboard_page.my_macys_to_dos_page");

    }

    @And("^I click on the Search All Clients button$")
    public static void i_click_on_the_search_all_clients_button() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("search.search_all_clients_btn");
        WebElement search = findElement(By.id("srch-all-btn"));
        search.click();
        Clicks.click("search.search_all_clients_btn");
    }

    @Then("^I should see the Search All Clients page$")
    public void i_should_see_the_search_all_clients_page() throws Throwable {
        String Url = WebDriverManager.getWebDriver().getCurrentUrl();
        if (Url.contains("mcy")) {
            Wait.untilElementPresent("search.search_all_clients_page");
            elementShouldBePresent("search.search_all_clients_page");
        } else {
            Wait.untilElementPresent("search.search_all_clients_page_BLM");
            elementShouldBePresent("search.search_all_clients_page_BLM");
        }
    }

    @When("^I select FirstLastNameZip button from the Search All Clients page$")
    public void i_select_firstlastnamezip_button_from_the_search_all_clients_page() throws Throwable {
        Wait.untilElementPresent("search.search_by_nm_zip_btn");
        Clicks.click("search.search_by_nm_zip_btn");
    }

    @When("^I select Telephone button from the Search All Clients page$")
    public static void i_select_telephone_button_from_the_search_all_clients_page() throws Throwable {
        Wait.untilElementPresent("search.search_by_phone_btn");
        Clicks.click("search.search_by_phone_btn");
    }

    @Then("^I should see the FirstLastNameZip search page$")
    public void i_should_see_the_firstlastnamezip_search_page() throws Throwable {
        Wait.untilElementPresent("search.nm_zip_search_page");
        elementShouldBePresent("search.nm_zip_search_page");
    }

    @Then("^I should see the Telephone Number search page$")
    public void i_should_see_the_telephone_number_search_page() throws Throwable {
        Wait.untilElementPresent("search.telephone_search_page");
        elementShouldBePresent("search.telephone_search_page");
    }

    @When("^I input first name \"([^\"]*)\" on the FirstLastNameZip search page$")
    public void i_input_first_name_something_on_the_firstlastnamezip_search_page(String frstNm) throws Throwable {
        Wait.untilElementPresent("search.search_frst_nm_input");
        TextBoxes.typeTextbox("search.search_frst_nm_input", frstNm);
    }

    @And("^I input last name \"([^\"]*)\" on the FirstLastNameZip search page$")
    public void i_input_last_name_something_on_the_firstlastnamezip_search_page(String lstNm) throws Throwable {
        Wait.untilElementPresent("search.search_lst_nm_input");
        TextBoxes.typeTextbox("search.search_lst_nm_input", lstNm);
    }

    @And("^I input the zip code \"([^\"]*)\" on the FirstLastNameZip search page$")
    public void i_input_the_zip_code_something_on_the_firstlastnamezip_search_page(String zip) throws Throwable {
        Wait.untilElementPresent("search.search_zip_input");
        TextBoxes.typeTextbox("search.search_zip_input", zip);
    }

    @When("^I input telephone number \"([^\"]*)\" on the Telephone Number search page$")
    public static void i_input_telephone_number_something_on_the_telephone_number_search_page(String phoneNr) throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("search.search_phone_input");
        Clicks.hoverForSelection("search.search_phone_input");
        TextBoxes.typeTextbox("search.search_phone_input", phoneNr);
    }

    @And("^I click the Search button on the Search All Clients page$")
    public static void i_click_the_search_button_on_the_search_all_clients_page() throws Throwable {
        Wait.untilElementPresent("search.search_btn");
        Clicks.click("search.search_btn");
        Thread.sleep(2000);
    }

    @Then("^I should see the number of search results found on the Results page$")
    public void i_should_see_the_number_of_search_results_found_on_the_results_page() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("search.search_results_found_hdr");
        String noResFound = findElement("search.search_results_found_hdr").getText().toLowerCase();
        Assert.assertTrue(noResFound.contains("result found") || noResFound.contains("results found"));
    }

    @And("^I should see the Customer information on the Results page:$")
    public void i_should_see_the_customer_information_on_the_results_page(List<String> infoList) throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("search.search_results_found_hdr");
        for (String detail : infoList) {
            switch (detail) {
                case "Name":
                    Wait.untilElementPresent("search.search_results_first_client");
                    String Name = findElement("search.search_results_first_client").getText();
                    if (Name.isEmpty()) {
                        Assert.fail("Client Name is not populated");
                    }
                    break;
                case "AddressLine1":
                    Wait.untilElementPresent("search.search_results_addr1");
                    String Addr1 = findElement("search.search_results_addr1").getText();
                    if (Addr1.isEmpty()) {
                        Assert.fail("AddressLine1 is not populated");
                    }
                    break;
                case "AddressLine2":
                    try {
                        findElement("search.search_results_addr2").isDisplayed();
                        String Addr2 = findElement("search.search_results_addr2").getText();
                        if (Addr2.isEmpty()) {
                            Assert.fail("AddressLine2 is not populated");
                        }
                    } catch (Exception e) {
                        System.out.println("[INFO] Address Line 2 is not applicable for Client");
                    }
                    break;
                case "City":
                    Wait.untilElementPresent("search.search_results_city");
                    String City = findElement("search.search_results_city").getText();
                    if (City.isEmpty()) {
                        Assert.fail("City is not populated");
                    }
                    break;
                case "State":
                    Wait.untilElementPresent("search.search_results_state");
                    String State = findElement("search.search_results_state").getText();
                    if (State.isEmpty()) {
                        Assert.fail("State is not populated");
                    }
                    break;
                case "Zip":
                    Wait.untilElementPresent("search.search_results_zip");
                    String Zip = findElement("search.search_results_zip").getText();
                    if (Zip.isEmpty()) {
                        Assert.fail("Zip is not populated");
                    }
                    break;
                case "Country":
                    Wait.untilElementPresent("search.search_results_country");
                    String Country = findElement("search.search_results_country").getText();
                    if (Country.isEmpty()) {
                        Assert.fail("Country is not populated");
                    }
                    break;
            }
        }
    }

    @And("^I click the Search Again button on the Search Results page$")
    public void i_click_the_search_again_button_on_the_search_results_page() throws Throwable {
        Wait.untilElementPresent("search.search_again_btn");
        Clicks.click("search.search_again_btn");
    }

    @Then("^I should see No Results Found on the Results page$")
    public void i_should_see_no_results_found_on_the_results_page() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("search.search_results_found_hdr");
        String noResFound = findElement("search.search_results_found_hdr").getText().toLowerCase();
        Assert.assertTrue(noResFound.contains("no results found"));
    }

    @And("^I should not see the previous search results$")
    public void i_should_not_see_the_previous_search_results() throws Throwable {
        try {
            findElement("search.search_results_first_client").isDisplayed();
            Assert.fail("[ERROR] Previous Search Results ARE displayed");
        } catch (Exception e) {
            System.out.println("[INFO] Previous Search Results are not displayed");
        }
    }

    @When("^I click on the Client on the Results page$")
    public void i_click_on_the_client_on_the_results_page() throws Throwable {
        Wait.secondsUntilElementPresent("search.search_results_first_client", 30);
        Clicks.hoverForSelection("search.search_results_first_client");
        Clicks.click("search.search_results_first_client");
        Wait.untilElementNotPresent("dashboard_page.loading_page");

    }

    @Then("^I should see the Client Profile page$")
    public void i_should_see_the_client_profile_page() throws Throwable {
        Thread.sleep(2000);
        String Url = WebDriverManager.getWebDriver().getCurrentUrl();
        if (Url.contains("mcy")) {
            Wait.untilElementNotPresent("dashboard_page.loading_page");
            Wait.untilElementPresent("client.client_profile_page");
            elementShouldBePresent("client.client_profile_page");
        } else {
            Wait.untilElementNotPresent("dashboard_page.loading_page");
            Wait.untilElementPresent("client.client_profile_page_BLM");
            elementShouldBePresent("client.client_profile_page_BLM");
        }
    }

    @When("^I click the Back button on the Client Profile page$")
    public void i_click_the_back_button_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_back_btn");
        Clicks.hoverForSelection("client.client_profile_back_btn");
        Clicks.click("client.client_profile_back_btn");
    }

    @And("^I should see the first name \"([^\"]*)\", last name \"([^\"]*)\", zip \"([^\"]*)\" search criteria displayed$")
    public void i_should_see_the_first_name_something_last_name_something_zip_something_search_criteria_displayed(String frtNm, String lstNm, String zip) throws Throwable {
        Wait.untilElementPresent("search.search_results_search_box");
        Assert.assertTrue(findElement("search.search_frst_nm_input").getAttribute("value").contains(frtNm));
        Assert.assertTrue(findElement("search.search_lst_nm_input").getAttribute("value").contains(lstNm));
        Assert.assertTrue(findElement("search.search_zip_input").getAttribute("value").contains(zip));
    }

    @And("^I should see the telephone number \"([^\"]*)\" search criteria displayed$")
    public void i_should_see_the_telephone_number_something_search_criteria_displayed(String telNr) throws Throwable {
        Wait.untilElementPresent("search.search_results_search_box");
        Assert.assertTrue(findElement("search.search_phone_input").getAttribute("value").contains(telNr));
    }


    @And("^I tap on Create Client button$")
    public static void i_tap_on_create_client_button() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("client.create_contact_button");
        Clicks.hoverForSelection("client.create_contact_button");
        Clicks.click("client.create_contact_button");

    }

    @And("^I write the first name \"([^\"]*)\" in the First Name field$")
    public static void i_write_the_first_name_something_in_the_first_name_field(String firstName) throws Throwable {
        Wait.untilElementPresent("client.first_name_create_contact");
        TextBoxes.typeTextbox("client.first_name_create_contact", firstName);

    }

    @And("^I write the last name \"([^\"]*)\" in the Last Name Field$")
    public static void i_write_the_last_name_something_in_the_last_name_field(String lastName) throws Throwable {

        Wait.untilElementPresent("client.last_name_create_contact");
        TextBoxes.typeTextbox("client.last_name_create_contact", lastName);
    }

    @And("^I write the preferred name \"([^\"]*)\" in the Preferred Name field$")
    public static void i_write_the_preferred_name_something_in_the_preferred_name_field(String prefName) throws Throwable {
        Wait.untilElementPresent("client.pref_name_create_contact");
        TextBoxes.typeTextbox("client.pref_name_create_contact", prefName);

    }

    @And("^I select the Phone Type \"([^\"]*)\" from Phone Type dropdown$")
    public static void i_select_the_phone_type_from_phone_type_dropdown(String type) throws Throwable {
        Wait.untilElementPresent("client.phone_type_create_contact");
        DropDowns.selectByText("client.phone_type_create_contact", type);

    }

    @And("^I write the required Address \"([^\"]*)\"$")
    public static void i_write_the_required_address_something(String reqAddress) throws Throwable {
        Wait.untilElementPresent("client.address_create_contact_1");
        TextBoxes.typeTextbox("client.address_create_contact_1", reqAddress);

    }

    @And("^I write the second address \"([^\"]*)\"$")
    public static void i_write_the_second_address_something(String additionalAddress) throws Throwable {
        Clicks.hoverForSelection("client.address_create_contact_2");
        Wait.untilElementPresent("client.address_create_contact_2");
        TextBoxes.typeTextbox("client.address_create_contact_2", additionalAddress);

    }

    @And("^I write the city in the City field \"([^\"]*)\"$")
    public static void i_write_the_city_in_the_city_field_something(String city) throws Throwable {
        Clicks.hoverForSelection("client.city_create_contact");
        Wait.untilElementPresent("client.city_create_contact");
        TextBoxes.typeTextbox("client.city_create_contact", city);
    }

    @And("^I write the zip code \"([^\"]*)\" in the Zip code field$")
    public static void i_write_the_zip_code_something_in_the_zip_code_field(String zip) throws Throwable {
        Clicks.hoverForSelection("client.zip_create_contact");
        Wait.untilElementPresent("client.zip_create_contact");
        TextBoxes.typeTextbox("client.zip_create_contact", zip);
    }

    @And("^I select random state from State dropdown mobile$")
    public static void i_select_random_state_from_state_dropdown_mobile() throws Throwable {
        Clicks.hoverForSelection("client.state_create_contact");
        DropDowns.selectRandomValue("client.state_create_contact");

    }

    @And("^I tap on Save button from Add Client page$")
    public static void i_tap_on_save_button_from_add_client_page() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("client.save_button_add_contact");
        WebElement search = findElement(By.id("save-btn"));
        search.click();
//        Clicks.click("client.save_button_add_contact");
    }

    @And("^I write a hint in the Hint field \"([^\"]*)\"$")
    public static void i_write_a_hint_in_the_hint_field_something(String hint) throws Throwable {
        Wait.untilElementPresent("client.hint_field");
        TextBoxes.typeTextbox("client.hint_field", hint);
    }

    @And("^I select Preferred Contact method as phone from dropdown$")
    public static void i_select_preferred_contact_method_as_phone_from_dropdown() throws Throwable {
        Wait.untilElementPresent("client.pref_contact_add_to_book");
        WebElement mySelectElement = findElement(By.xpath("//*[@id='addToBook']/div[1]/div/div[6]/div/span/select"));
        Select dropdown = new Select(mySelectElement);
        dropdown.selectByVisibleText("Phone");

    }

    @And("^I tap on Continue button from Add to Book page$")
    public static void i_tap_on_continue_button_from_add_to_book_page() throws Throwable {
        Wait.untilElementPresent("client.continue_button_add_to_book");
        WebElement ok = findElement(By.id("cntn-btn"));
        ok.click();
//        Clicks.click("client.continue_button_add_to_book");
    }


    @And("^I input the signature omniclient$")
    public void i_input_the_signature_omniclient() throws Throwable {
        Wait.untilElementPresent("client.signature_box");
        WebElement signature = Elements.findElement("client.signature_box");
        WebDriver driver = WebDriverManager.getWebDriver();
        Actions action;
        action = new Actions(driver);
        System.out.println(signature.getSize());
        action.clickAndHold(signature).perform();
        action.moveByOffset(100, 100);
        action.moveByOffset(-150, 0);
        action.moveByOffset(0, -200);
        action.moveByOffset(100, 0);
        action.moveByOffset(100, 100);
        action.release().perform();
        System.out.println("Done");
    }

    @And("^I click on I Agree from signature box omniclient$")
    public void i_click_on_i_agree_from_signature_box_omniclient() throws Throwable {
        Wait.untilElementPresent("client.i_agree_signature_box");
        Clicks.click("client.i_agree_signature_box");
    }


    @And("^I tap OK in the add confirmation popup$")
    public static void i_tap_ok_in_the_add_confirmation_popup() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("client.ok_add_confirm_popup");
        Clicks.click("client.ok_add_confirm_popup");
    }


    @And("^I tap Save button from Add to Book page$")
    public static void i_tap_save_button_from_add_to_book_page() throws Throwable {
        Wait.untilElementPresent("client.save_button_add_contact");
        WebElement save = findElement(By.id("save-btn"));
        save.click();
//        Clicks.click("client.save_button_add_contact");
        Wait.untilElementNotPresent("dashboard_page.loading_page");

    }

    @And("^I tap OK in the Successfully Added client popup$")
    public static void i_tap_ok_in_the_successfully_added_client_popup() throws Throwable {
        Wait.untilElementPresent("client.ok_add_confirm_popup");
        Clicks.click("client.ok_add_confirm_popup");

    }

    @Then("^I check that the phone number \"([^\"]*)\" is marked as preferred$")
    public void i_check_that_the_phone_number_something_is_marked_as_preferred(String PrefNb) throws Throwable {
        Wait.untilElementPresent("client.client_profile_phone");
        String prefNumber = findElement("client.client_profile_phone").getText().trim();
        System.out.println(prefNumber);
        Assert.assertTrue(prefNumber.contains(PrefNb));


    }

// TODO: remove deprecated methods and clean-up the json files + FFs where they are used

//    @And("^I check that the phone number \"([^\"]*)\" is marked as primary$")
//    public void i_check_that_the_phone_number_something_is_marked_as_primary(String PrimNb) throws Throwable {
//        String prefNumber = findElement("dashboard_page.primary_phone_number").getText().trim();
//        System.out.println(prefNumber);
//        Assert.assertTrue(prefNumber.contains(PrimNb));
//    }
//
//    @And("^I check that primary phone is the same as pref phone$")
//    public void i_check_that_primary_phone_is_the_same_as_pref_phone() throws Throwable {
//        String prefNumber = findElement("dashboard_page.pref_phone_number").getText();
//        String primNumber = findElement("dashboard_page.primary_phone_number").getText();
//
//        if (prefNumber.equals(primNumber)) {
//
//            System.out.println("Primary number is the same as the preferred number");
//        } else {
//            Assert.fail("Primary and preferred numbers do not match");
//        }
//
//    }

    @And("^I should see the My Relationship column on the Results page$")
    public void iShouldSeeTheMyRelationshipColumnOnTheResultsPage() throws Throwable {
        Wait.untilElementPresent("search.affiliated_with_me");
        elementShouldBePresent("search.affiliated_with_me");
    }

    @And("^I should see the affiliatedToMe check box if there is a relationship: \"([^\"]*)\"$")
    public void iShouldSeeTheAffiliatedToMeCheckBoxIfThereIsARelationship(String affiliated) throws Throwable {
        if (affiliated.contains("yes")) {
            Wait.untilElementPresent("search.affiliated_with_me_chkbox");
            elementShouldBePresent("search.affiliated_with_me_chkbox");
        } else {
            try {
                findElement("search.affiliated_with_me_chkbox").isDisplayed();
                Assert.fail("i should not see the affiliated with me checkbox");
            } catch (Exception e) {
                System.out.println("The affiliated with me checkbox is not displayed");
            }
        }
    }

    @Then("^I should not see the affiliatedToOthers check box$")
    public void i_should_not_see_the_affiliatedToOthers_check_box() throws Throwable {
        try {
            findElement("search.affiliated_with_others_chkbox").isDisplayed();
            Assert.fail("i should not see the affiliated with others checkbox");
        } catch (Exception e) {
            System.out.println("The affiliated with others checkbox is not displayed");
        }
    }

    @And("^we add a new client on mobile$")
    public void we_add_a_new_client_on_mobile(List<String> aDetail) throws Throwable {
        String phone = aDetail.get(0);
        String Fname = aDetail.get(2);
        String Lname = aDetail.get(4);
        String prefName = aDetail.get(6);
        String reqAddress = aDetail.get(8);
        String additionalAddress = aDetail.get(10);
        String city = aDetail.get(12);
        String zip = aDetail.get(14);
        String hint = aDetail.get(16);
        i_click_on_the_search_all_clients_button();
        i_select_telephone_button_from_the_search_all_clients_page();
        i_input_telephone_number_something_on_the_telephone_number_search_page(phone);
        i_click_the_search_button_on_the_search_all_clients_page();
        i_tap_on_create_client_button();
        i_write_the_first_name_something_in_the_first_name_field(Fname);
        i_write_the_last_name_something_in_the_last_name_field(Lname);
        i_write_the_preferred_name_something_in_the_preferred_name_field(prefName);
        i_select_the_phone_type_from_phone_type_dropdown("MOBILE");
        i_write_the_required_address_something(reqAddress);
        i_write_the_second_address_something(additionalAddress);
        i_write_the_city_in_the_city_field_something(city);
        i_write_the_zip_code_something_in_the_zip_code_field(zip);
        i_select_random_state_from_state_dropdown_mobile();
        i_tap_on_save_button_from_add_client_page();
        i_write_a_hint_in_the_hint_field_something(hint);
        i_select_preferred_contact_method_as_phone_from_dropdown();
        i_tap_on_continue_button_from_add_to_book_page();
//        i_input_the_signature_omniclient();
//        i_click_on_i_agree_from_signature_box_omniclient();
        i_tap_ok_in_the_add_confirmation_popup();
        i_tap_save_button_from_add_to_book_page();
        i_tap_ok_in_the_successfully_added_client_popup();


    }

    @Then("^existing phone information is displayed$")
    public void existing_phone_information_is_displayed(List<String> row) throws Throwable {
        Wait.untilElementPresent("client.profile_summary_details");

        for (String aRow : row) {
            switch (aRow) {
                case "Phone number":
                    Elements.elementShouldBePresent("client.just_phone_number_client_profile");
                    break;
                case "Phone number type":
                    Elements.elementShouldBePresent("client.just_phone_type_client_profile");
            }


        }
    }

    @When("^I tap on Edit button from Contact Profile page$")
    public static void i_tap_on_edit_button_from_contact_profile_page() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Thread.sleep(2000);
        Wait.untilElementPresent("client.client_profile_manage_client_btn");
        Clicks.click("client.client_profile_manage_client_btn");
    }

    @Then("^Save button is displayed in Edit Phone page$")
    public void save_button_is_displayed_in_edit_phone_page() throws Throwable {
        Wait.untilElementPresent("client.save_button_edit_phone_page");
        elementShouldBePresent("client.save_button_edit_phone_page");
    }

    @And("^I tap on Edit Phone from Edit Client page$")
    public void i_tap_on_edit_phone_from_edit_client_page() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("client.edit_phone_btn_edit_client_page");
        Clicks.click("client.edit_phone_btn_edit_client_page");
    }

    @And("^Cancel button is displayed in Edit Phone page$")
    public void cancel_button_is_displayed_in_edit_phone_page() throws Throwable {
        Wait.untilElementPresent("client.cancel_button_edit_phone_page");
        elementShouldBePresent("client.cancel_button_edit_phone_page");
    }

    @And("^the preferred information radio buttons should be preselected in Edit Phone page$")
    public void the_preferred_information_radio_buttons_should_be_preselected_in_edit_phone_page() throws Throwable {
        elementShouldBePresent("client.first_checkbox_edit_phone_page");
        String checkedAtrib = findElement("client.first_checkbox_edit_phone_page").getAttribute("checked");
        System.out.println(checkedAtrib);
        Assert.assertTrue(checkedAtrib.contains("true"));

    }


    @Then("^the new first secondary number \"([^\"]*)\" is displayed with dash symbols and proper formatting in edit mode$")
    public void the_new_first_secondary_number_something_is_displayed_with_dash_symbols_and_proper_formatting_in_edit_mode(String phoneFormat) throws Throwable {
        Wait.untilElementPresent("client.second_phone_field_edit_phone");
        elementShouldBePresent("client.second_phone_field_edit_phone");
        String phone = Elements.findElement("client.second_phone_field_edit_phone").getText();
        System.out.println(phone);
        Assert.assertTrue(phoneFormat.contains(phone));

    }

    @And("^I add a new first secondary phone number \"([^\"]*)\"$")
    public void i_add_a_new_first_secondary_phone_number_something(String secPhoneNb) throws Throwable {
        Wait.untilElementPresent("client.second_phone_field_edit_phone");
        TextBoxes.typeTextbox("client.second_phone_field_edit_phone", secPhoneNb);


    }

    @And("^I select phone type from dropdown mobile$")
    public void i_select_phone_type_from_dropdown_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("client.second_phone_drop_down_edit_phone");
        DropDowns.selectByText("client.second_phone_drop_down_edit_phone", "Work");
    }

    @When("^we tap on Save button from Edit Phone page$")
    public void we_tap_on_save_button_from_edit_phone_page() throws Throwable {
        Thread.sleep(2000);
        Clicks.click("client.save_button_edit_phone_page");
    }

    @Then("^the new added first secondary number \"([^\"]*)\" is displayed with proper formatting$")
    public void the_new_added_first_secondary_number_something_is_displayed_with_proper_formatting(String phoneReadOnly) throws Throwable {
        Wait.untilElementPresent("client.client_profile_phone");
        elementShouldBePresent("client.client_profile_phone");
        String phone = Elements.findElement("client.client_profile_phone").getText();
        System.out.println(phone);
        Assert.assertTrue(phoneReadOnly.contains(phone));
    }

    @Then("^I check that Address \"([^\"]*)\" is marked as preferred$")
    public void i_check_that_address_something_is_marked_as_preferred(String prefAddress) throws Throwable {
        Wait.untilElementPresent("client.address_section_contact_read_only_mcy");
        Thread.sleep(2000);
        String prefADR = findElement("client.address_section_contact_read_only_mcy").getText();
        System.out.println(prefADR);
        Assert.assertTrue(prefADR.contains(prefAddress));
    }

    @And("^I check that Address \"([^\"]*)\" is marked as primary")
    public void i_check_that_address_something_is_marked_as_primary(String primAddress) throws Throwable {
        String primADR = findElement("client.address_section_contact_read_only_mcy").getText();
        System.out.println(primADR);
        Assert.assertTrue(primADR.contains(primAddress));

    }

    @Then("^I should see the Unable to View Profile popup$")
    public void iShouldSeeTheUnableToViewProfilePopUp() throws Throwable {
        Wait.untilElementPresent("dashboard_page.add_to_book_overlay");
        elementShouldBePresent("dashboard_page.add_to_book_overlay");
    }

    @When("^I select the Add to Book button on Unable to View Profile popup$")
    public void iSelectTheAddToBookButtonOnUnableToViewProfilePopUp() throws Throwable {
        Clicks.click("dashboard_page.add_to_book_overlay_add_to_book_button");
    }

    @When("^I select the Add to Book button$")
    public void iSelectTheAddToBookButton() throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("dashboard_page.add_to_book_button_profile");
        Clicks.click("dashboard_page.add_to_book_button_profile");
        Wait.untilElementPresent("client.profile_summary_details");

    }

    @Then("^an error popup for required address is displayed mobile$")
    public void an_error_popup_for_required_address_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("Address is required"));
    }

    @And("^I click on OK button from required field error popup mobile$")
    public void i_click_on_ok_button_from_required_field_error_popup_mobile() throws Throwable {
        Wait.untilElementPresent("dashboard_page.ok_add_error_popup_required_field");
        Clicks.click("dashboard_page.ok_add_error_popup_required_field");
    }

    @Then("^an error popup for required city is displayed mobile$")
    public void an_error_popup_for_required_city_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("City is required"));
    }

    @Then("^an error popup for required zip is displayed mobile$")
    public void an_error_popup_for_required_zip_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("Zip Code is required"));

    }

    @Then("^an error popup for required state is displayed mobile$")
    public void an_error_popup_for_required_state_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("State is required"));

    }

    @Then("^an error popup for zip code min characters is displayed mobile$")
    public void an_error_popup_for_required_zip_code_min_characters_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.ok_add_error_popup_required_field");
        elementShouldBePresent("dashboard_page.ok_add_error_popup_required_field");


    }

    @And("^I clear the city field from add client mobile page$")
    public void i_clear_the_city_field_from_add_client_mobile_page() throws Throwable {
        findElement("client.city_create_contact").clear();
    }

    @And("^I clear the zip field from add client mobile page$")
    public void i_clear_the_zip_field_from_add_client_mobile_page() throws Throwable {
        findElement("client.zip_create_contact").clear();
    }

    @And("^I select empty option from state dropdown mobile create client$")
    public void i_select_empty_option_from_state_dropdown_mobile_create_client() throws Throwable {
        Wait.untilElementPresent("client.state_create_contact");
        DropDowns.selectByIndex("client.state_create_contact", 0);

    }

    @And("^we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE Mobile$")
    public void we_add_additional_address_line_address_line_1_and_2_remaining_mandatory_fields_and_click_save__mobile(List<String> addressline1and2) throws Throwable {
        for (String line : addressline1and2) {
            Thread.sleep(2000);
            i_write_the_required_address_something(line);
            i_write_the_second_address_something(line);
            i_tap_on_save_button_from_add_client_page();
            i_click_on_ok_button_from_required_field_error_popup_mobile();


        }
    }

    @When("^I clear the first name field from create client mobile$")
    public void i_clear_the_first_name_field_from_create_client_mobile() throws Throwable {
        Wait.untilElementPresent("client.first_name_create_contact");
        elementShouldBePresent("client.first_name_create_contact");
        findElement("client.first_name_create_contact").clear();

    }


    @Then("^an error popup for required Last Name is displayed mobile$")
    public void an_error_popup_for_required_last_name_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("Last Name is required"));

    }

    @Then("^an error popup for required First Name is displayed mobile$")
    public void an_error_popup_for_required_first_name_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("First Name is required"));
    }

    @And("^I clear the Phone Number field from create client mobile$")
    public void i_clear_the_phone_number_field_from_create_client_mobile() throws Throwable {
        findElement("client.phone_number_create_contact").clear();
    }

    @Then("^an error popup for required Phone Number is displayed mobile$")
    public void an_error_popup_for_required_phone_number_is_displayed_mobile() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        elementShouldBePresent("dashboard_page.error_popup_client_info");
        Wait.untilElementPresent("dashboard_page.error_popup_client_info");
        String requiredAddress = findElement("dashboard_page.error_popup_client_info").getText();
        System.out.println(requiredAddress);
        Assert.assertTrue(requiredAddress.contains("Phone Number"));
    }

    @Then("^I should see the Add Client screen prepopulated with the clients info \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_should_see_the_add_client_screen_prepopulated_with_the_clients_info(String FN, String LN, String ZIP, String PHN) throws Throwable {
        Thread.sleep(2000);
        List<String> row = Arrays.asList("First Name", "Last Name", "Zip", "Phone");
        for (String aRow : row) {
            switch (aRow) {
                case "First Name":
                    Thread.sleep(2000);
                    Elements.elementShouldBePresent("client.first_name_create_contact");
                    getValues("client.first_name_create_contact");
                    String FrstName = findElement("client.first_name_create_contact").getAttribute("value");
                    System.out.println(FrstName);
                    Assert.assertTrue(FrstName.contains(FN));
                    break;
                case "Last Name":
                    Thread.sleep(2000);
                    Elements.elementShouldBePresent("client.last_name_create_contact");
                    String LastName = findElement("client.last_name_create_contact").getAttribute("value");
                    System.out.println(LastName);
                    Assert.assertTrue(LastName.contains(LN));
                    break;
                case "Zip":
                    Thread.sleep(2000);
                    Clicks.hoverForSelection("client.zip_create_contact");
                    Elements.elementShouldBePresent("client.zip_create_contact");
                    String zip = findElement("client.zip_create_contact").getAttribute("value");
                    System.out.println(zip);
                    Assert.assertTrue(zip.contains(ZIP));
                    break;
                case "Phone":
                    Thread.sleep(2000);
                    Clicks.hoverForSelection("client.phone_number_create_contact");
                    Elements.elementShouldBePresent("client.phone_number_create_contact");
                    String phone = findElement("client.phone_number_create_contact").getAttribute("value");
                    System.out.println(phone);
                    Assert.assertTrue(phone.contains(PHN));
            }
        }

    }

    @Then("^I should see the error popup from CLIENT INFO section Macys mobile$")
    public void i_should_see_the_error_popup_from_client_info_section_blm_mobile() throws Throwable {
        Wait.untilElementPresent("dashboard_page.error_popup_client_info_macys");
        elementShouldBePresent("dashboard_page.error_popup_client_info_macys");
        String errorMsg = findElement("dashboard_page.error_popup_client_info_macys").getText();
        Assert.assertTrue(errorMsg.contains("Error"));
    }

    @And("^I click on the OK button on the error popup from CLIENT INFO section Macys mobile$")
    public void i_click_on_the_ok_button_on_the_error_popup_from_client_info_section_blm_mobile() throws Throwable {
        Wait.untilElementPresent("dashboard_page.error_popup_client_info_ok_btn_macys");
        Clicks.click("dashboard_page.error_popup_client_info_ok_btn_macys");
    }

    @And("^I write the phone number in the Phone number field mobile\"([^\"]*)\"$")
    public void i_write_the_phone_number_in_the_phone_number_field_mobilesomething(String phnNbr) throws Throwable {
        Wait.untilElementPresent("client.phone_number_create_contact");
        TextBoxes.typeTextbox("client.phone_number_create_contact", phnNbr);
    }

    @And("^I should see the Name Information locked down and not editable$")
    public void i_should_see_the_name_information_locked_down_and_not_editable() throws Throwable {
        Thread.sleep(2000);
        Elements.elementShouldBePresent("client.first_name_create_contact");
        String FirstName = findElement("client.first_name_create_contact").getAttribute("readonly");
        System.out.println(FirstName);
        Assert.assertTrue(FirstName.contains("true"));

        Elements.elementShouldBePresent("client.last_name_create_contact");
        String LastName = findElement("client.last_name_create_contact").getAttribute("readonly");
        System.out.println(LastName);
        Assert.assertTrue(LastName.contains("true"));
    }

    @And("^I should see the Required Information editable$")
    public void i_should_see_the_required_information_editable(List<String> FieldsList) throws Throwable {
        for (String aRow : FieldsList) {
            switch (aRow) {
                case "Phone Nbr":
                    Elements.elementShouldBePresent("client.phone_number_create_contact");
                    String FrstName = findElement("client.phone_number_create_contact").getAttribute("required");
                    System.out.println(FrstName);
                    Assert.assertTrue(FrstName.contains("true"));
                    break;

                case "Addr Line 1":
                    Elements.elementShouldBePresent("client.address_create_contact_1");
                    String LastName = findElement("client.address_create_contact_1").getAttribute("required");
                    System.out.println(LastName);
                    Assert.assertTrue(LastName.contains("true"));
                    TextBoxes.typeTextbox("client.phone_number_create_contact", "Test ADDRESS");
                    break;

                case "City":
                    Clicks.hoverForSelection("client.city_create_contact");
                    Elements.elementShouldBePresent("client.city_create_contact");
                    String zip = findElement("client.city_create_contact").getAttribute("required");
                    System.out.println(zip);
                    Assert.assertTrue(zip.contains("true"));
                    TextBoxes.typeTextbox("client.phone_number_create_contact", "Test CITY");
                    break;

                case "State":
//                    the required filed/dropdown validation is covered in another test
//                    we validate that is selectable here
                    Clicks.hoverForSelection("client.state_create_contact");
                    Elements.elementShouldBePresent("client.state_create_contact");
                    DropDowns.selectRandomValue("client.state_create_contact");
                    break;

                case "Zip":
                    Clicks.hoverForSelection("client.zip_create_contact");
                    Elements.elementShouldBePresent("client.zip_create_contact");
                    String Zip = findElement("client.zip_create_contact").getAttribute("required");
                    System.out.println(Zip);
                    Assert.assertTrue(Zip.contains("true"));
                    TextBoxes.typeTextbox("client.zip_create_contact", "22334");

            }
        }
    }

    @And("^I should see the Optional Information editable$")
    public void i_should_see_the_optional_information_editable(List<String> OptInfo) throws Throwable {
        for (String aRow : OptInfo) {
            switch (aRow) {

                case "Pref Name":
                    TextBoxes.typeTextbox("client.pref_name_create_contact", "PREF NAME");
                    break;

                case "Addr Line 2":
                    Clicks.hoverForSelection("client.address_create_contact_2");
                    TextBoxes.typeTextbox("client.address_create_contact_2", "ADDRESS LINE 2 test");


            }

        }
    }

    @Then("^I should see the following fields on the Add to Book page$")
    public void i_should_see_the_following_fields_on_the_add_to_book_page(List<String> AddToBookFields) throws Throwable {
        for (String aRow : AddToBookFields) {
            switch (aRow) {

                case "Hint":
                    Thread.sleep(2000);
                    Wait.untilElementPresent("client.hint_field");
                    elementShouldBePresent("client.hint_field");
                    break;

                case "Notes":
                    Wait.untilElementPresent("client.note_field");
                    elementShouldBePresent("client.note_field");
                    break;

                case "Pref Contact Method":
                    Wait.untilElementPresent("client.pref_contact_add_to_book");
                    elementShouldBePresent("client.pref_contact_add_to_book");

            }
        }
    }

    @And("^I should see the following details on the Profile Summary$")
    public void i_should_see_the_following_details_on_the_profile_summary(List<String> infoDetail) throws Throwable {
        for (String aDet : infoDetail) {
            switch (aDet) {
                case "Spent With Me":
                    Wait.untilElementPresent("client.client_profile_spent_with_me");
                    elementShouldBePresent("client.client_profile_spent_with_me");
                    break;
                case "Last Visit":
                    Wait.untilElementPresent("client.client_profile_last_visit");
                    elementShouldBePresent("client.client_profile_last_visit");
                    break;
                case "Total Visits":
                    Wait.untilElementPresent("client.client_profile_total_visits");
                    elementShouldBePresent("client.client_profile_total_visits");
                    break;
                case "Relationship Date":
                    Wait.untilElementPresent("client.client_profile_total_visits");
                    elementShouldBePresent("client.client_profile_total_visits");
                    break;
                case "Birthday":
                    Wait.untilElementPresent("client.client_profile_total_visits");
                    elementShouldBePresent("client.client_profile_total_visits");
                    break;
                case "Pref Contact Method":
                    Wait.untilElementPresent("client.client_profile_pref_contact_method");
                    elementShouldBePresent("client.client_profile_pref_contact_method");
                    break;
            }
        }
    }

    @And("^I should see the preferred Customer Information$")
    public void i_should_see_the_preferred_customer_information(List<String> prefInfo) throws Throwable {
        for (String aInfo : prefInfo) {
            switch (aInfo) {
                case "Name":
                    Wait.untilElementPresent("client.client_profile_name");
                    elementShouldBePresent("client.client_profile_name");
                    break;
                case "Pref Name":
                    Wait.untilElementPresent("client.client_profile_pref_name");
                    elementShouldBePresent("client.client_profile_pref_name");
                    break;
                case "Pref Address":
                    Wait.untilElementPresent("client.client_profile_address");
                    elementShouldBePresent("client.client_profile_address");
                    break;
                case "Pref Phone":
                    Wait.untilElementPresent("client.client_profile_phone");
                    elementShouldBePresent("client.client_profile_phone");
                    break;
                case "Pref Email":
                    Wait.untilElementPresent("client.client_profile_email");
                    elementShouldBePresent("client.client_profile_email");
                    break;
            }
        }
    }

    @And("^I should see the Hints and Notes link on the Client Profile page$")
    public void i_should_see_the_hints_and_notes_link_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_hints_notes");
        elementShouldBePresent("client.client_profile_hints_notes");
        WebElement hintsAndNotes = findElement("client.client_profile_hints_notes");
        Assert.assertTrue(hintsAndNotes.getText().contains("Hints and Notes"));
    }

    @And("^I should see the Wallet link on the Client Profile page$")
    public void i_should_see_the_wallet_link_on_the_client_profile_page() throws Throwable {
        // check for Wallet Link
        Clicks.hoverForSelection("client.client_profile_wallet_link");
        Wait.untilElementPresent("client.client_profile_wallet_link");
        elementShouldBePresent("client.client_profile_wallet_link");
        WebElement hintsAndNotes = findElement("client.client_profile_wallet_link");
        Assert.assertTrue(hintsAndNotes.getText().contains("Wallet"));
        //check for Wallet Icon
        Wait.untilElementPresent("client.client_profile_wallet_icon");
        elementShouldBePresent("client.client_profile_wallet_icon");
    }

    @When("^I click the Hints and Notes link on the Client Profile page$")
    public void i_click_the_hints_and_notes_link_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_hints_notes");
        Clicks.click("client.client_profile_hints_notes");
    }

    @Then("^I should see the Hints and Notes page$")
    public void i_should_see_the_hints_and_notes_page() throws Throwable {
        Wait.untilElementPresent("client.hints_and_notes_page_title");
        elementShouldBePresent("client.hints_and_notes_page_assoc_field");
        elementShouldBePresent("client.hints_and_notes_page_hints_field");
        elementShouldBePresent("client.hints_and_notes_page_notes_field");
        elementShouldBePresent("client.hints_and_notes_page_edit_btn");
    }

    @When("^I click the back button from the mobile top bar Hints and Notes$")
    public void i_click_the_back_button_from_the_mobile_top_bar_hints_and_notes() throws Throwable {
        Wait.untilElementPresent("client.hints_and_notes_page_top_bar_back_btn");
        Clicks.click("client.hints_and_notes_page_top_bar_back_btn");
    }

    @When("^I click the back button from the mobile top bar Wallet$")
    public void i_click_the_back_button_from_the_mobile_top_bar_wallet() throws Throwable {
        Wait.untilElementPresent("client.wallet_page_top_bar_back_btn");
        Clicks.click("client.wallet_page_top_bar_back_btn");
    }

    @When("^I click the Wallet link on the Client Profile page$")
    public void i_click_the_wallet_link_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_wallet_link");
        Clicks.click("client.client_profile_wallet_link");
    }

    @Then("^I should see the Wallet page$")
    public void i_should_see_the_wallet_page() throws Throwable {
        Wait.untilElementPresent("client.wallet_page_title");
        elementShouldBePresent("client.wallet_page_title");
        elementShouldBePresent("client.wallet_page_assoc_field");
        elementShouldBePresent("client.wallet_page_card_list");
    }

    @And("^I should see the Create To Do button on the Client Profile page$")
    public void i_should_see_the_create_to_do_button_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_create_to_do_btn");
        elementShouldBePresent("client.client_profile_create_to_do_btn");
    }

    @When("^I click the Create To Do button on the Client Profile page$")
    public void i_click_the_create_to_do_button_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_create_to_do_btn");
        Clicks.click("client.client_profile_create_to_do_btn");
    }

    @Then("^I should see the Create To Do page$")
    public void i_should_see_the_create_to_do_page() throws Throwable {
        Wait.untilElementPresent("client.create_to_do_page_title");
        elementShouldBePresent("client.create_to_do_page_title");
        elementShouldBePresent("client.create_to_do_page_title_field");
        elementShouldBePresent("client.create_to_do_page_duedate_field");
        elementShouldBePresent("client.create_to_do_page_description_field");
    }

    @When("^I click the back button from the mobile top bar Create To Do$")
    public void i_click_the_back_button_from_the_mobile_top_bar_create_to_do() throws Throwable {
        Wait.untilElementPresent("client.create_to_do_page_top_bar_back_btn");
        Thread.sleep(1000);
        Clicks.click("client.create_to_do_page_top_bar_back_btn");
    }

    @And("^I should see the Manage Client button on the Client Profile page$")
    public void i_should_see_the_manage_client_button_on_the_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_manage_client_btn");
        elementShouldBePresent("client.client_profile_manage_client_btn");
    }

    @When("^I click the Manage Client button from Client Profile page$")
    public void i_click_the_manage_client_button_from_client_profile_page() throws Throwable {
        Wait.untilElementPresent("client.client_profile_manage_client_btn");
        Clicks.click("client.client_profile_manage_client_btn");
    }

    @Then("^I should see the Manage Client page$")
    public void i_should_see_the_manage_client_page() throws Throwable {
        Wait.untilElementPresent("client.manage_client_page_title");
        elementShouldBePresent("client.manage_client_page_title");
        elementShouldBePresent("client.manage_client_page_assoc_field");
        elementShouldBePresent("client.manage_client_page_phone_field");
        elementShouldBePresent("client.manage_client_page_address_field");
        elementShouldBePresent("client.manage_client_page_pref_cont_field");
    }

    @And("^I select \"([^\"]*)\" state from State dropdown mobile$")
    public void i_select_something_state_from_state_dropdown_mobile(String state) throws Throwable {
        Clicks.hoverForSelection("client.state_create_contact");
        DropDowns.selectByText("client.state_create_contact", state);
    }

    @And("^I write a note in the Notes field \"([^\"]*)\"$")
    public void i_write_a_note_in_the_notes_field_something(String note) throws Throwable {
        Wait.untilElementPresent("client.note_field");
        TextBoxes.typeTextbox("client.note_field", note);
    }

    @Then("^I should see the modified Customer Information$")
    public void i_should_see_the_modified_customer_information(DataTable info) throws Throwable {
        Thread.sleep(2000);
        for (List<String> list : info.raw()) {
            String infoType = list.get(0);
            String infoValue = list.get(1);
            switch (infoType) {
                case "Pref Name":
                    Wait.untilElementPresent("client.client_profile_pref_name");
                    String prefName = findElement("client.client_profile_pref_name").getText();
                    Assert.assertTrue(prefName.contains(infoValue));
                    break;
                case "Phone Nbr":
                    Wait.untilElementPresent("client.client_profile_phone");
                    String phoneNbr = findElement("client.client_profile_phone").getText();
                    Assert.assertTrue(phoneNbr.contains(infoValue));
                    break;
                case "Phone Type":
                    Wait.untilElementPresent("client.client_profile_phone");
                    String phoneType = findElement("client.client_profile_phone").getText();
                    Assert.assertTrue(phoneType.contains(infoValue));
                    break;
                case "Addr Line 1":
                    Wait.untilElementPresent("client.client_profile_address");
                    String addr1 = findElement("client.client_profile_address").getText();
                    Assert.assertTrue(addr1.contains(infoValue));
                    break;
                case "Addr Line 2":
                    Wait.untilElementPresent("client.client_profile_address");
                    String addr2 = findElement("client.client_profile_address").getText();
                    System.out.println("Actual address 2" + addr2);
                    System.out.println("Expected address 2" + infoValue);
                    Assert.assertTrue(addr2.contains(infoValue));
                    break;
                case "City":
                    Wait.untilElementPresent("client.client_profile_address");
                    String city = findElement("client.client_profile_address").getText();
                    Assert.assertTrue(city.contains(infoValue));
                    break;
                case "State":
                    Wait.untilElementPresent("client.client_profile_address");
                    String state = findElement("client.client_profile_address").getText();
                    Assert.assertTrue(state.contains(infoValue));
                    break;
                case "Zip":
                    Wait.untilElementPresent("client.client_profile_address");
                    String zip = findElement("client.client_profile_address").getText();
                    Assert.assertTrue(zip.contains(infoValue));
                    break;
                case "Email":
                    Wait.untilElementPresent("client.client_profile_email");
                    String email = findElement("client.client_profile_email").getText();
                    Assert.assertTrue(email.contains(infoValue));
                    break;
            }
        }
    }

    @And("^I should see the Preferred Contact method as \"([^\"]*)\"$")
    public void i_should_see_the_preferred_contact_method_as_something(String method) throws Throwable {
        switch (method) {
            case "phone":
                Wait.untilElementPresent("client.client_profile_pref_contact_method");
                String prefMeth1 = findElement("client.client_profile_pref_contact_method").getText();
                Assert.assertTrue(prefMeth1.contains("Phone"));
                elementShouldBePresent("client.client_profile_pref_phone_icon");
                break;
            case "email":
                Wait.untilElementPresent("client.client_profile_pref_contact_method");
                String prefMeth2 = findElement("client.client_profile_pref_contact_method").getText();
                Assert.assertTrue(prefMeth2.contains("Email"));
                elementShouldBePresent("client.client_profile_pref_email_icon");
                break;
            case "text":
                Wait.untilElementPresent("client.client_profile_pref_contact_method");
                String prefMeth3 = findElement("client.client_profile_pref_contact_method").getText();
                Assert.assertTrue(prefMeth3.contains("Text"));
                elementShouldBePresent("client.client_profile_pref_text_icon");
                break;
        }
    }

    @And("^I should see the Hint field populated with \"([^\"]*)\"$")
    public void i_should_see_the_hint_field_populated_with_something(String hint) throws Throwable {
        i_click_the_hints_and_notes_link_on_the_client_profile_page();
        Thread.sleep(2000);
        Wait.untilElementPresent("client.hints_and_notes_page_hints_field");
        String actualHint = findElement("client.hints_and_notes_page_hints_xpath").getText();
        System.out.println(actualHint);
        Assert.assertTrue(actualHint.contains(hint));
        i_click_the_back_button_from_the_mobile_top_bar_hints_and_notes();
        Wait.untilElementNotPresent("dashboard_page.loading_page");
    }

    @And("^I should see the Notes field populated with \"([^\"]*)\"$")
    public void i_should_see_the_notes_field_populated_with_something(String note) throws Throwable {
        i_click_the_hints_and_notes_link_on_the_client_profile_page();
        Thread.sleep(2000);
        Wait.untilElementPresent("client.hints_and_notes_page_notes_field");
        String actualNote = findElement("client.hints_and_notes_page_notes_xpath").getText();
        System.out.println(actualNote);
        Assert.assertTrue(actualNote.contains(note));
        i_click_the_back_button_from_the_mobile_top_bar_hints_and_notes();
        Wait.untilElementNotPresent("dashboard_page.loading_page");
    }

    @And("^I select the \"([^\"]*)\" preferred radio button from edit phone page mobile$")
    public void i_select_the_something_preferred_radio_button_from_edit_phone_page_mobile(String option) throws Throwable {
        switch (option) {
            case "1":
                Clicks.click("client.first_checkbox_edit_phone_page");
                break;
            case "2":
                Clicks.click("client.second_checkbox_edit_phone_page");
                break;
            case "3":
                Clicks.click("client.third_checkbox_edit_phone_page");
                break;
        }
    }


    @Then("^I check that Phone Type is marked as \"([^\"]*)\"$")
    public void iCheckThatPhoneTypeIsMarkedAs(String phoneType) throws Throwable {
        Wait.untilElementNotPresent("dashboard_page.loading_page");
        Wait.untilElementPresent("client.client_profile_phone");
        Thread.sleep(2000);

        WebElement phone = findElement(By.id("phoneNumber"));
        phone.getText();
        System.out.println(phone);
        Assert.assertTrue(phone.getText().contains(phoneType));
    }


    @Then("^I should see the Text dropdown entry as  \"([^\"]*)\" \"([^\"]*)\" on Create Client mobile$")
    public void iShouldSeeTheTextDropdownEntryAsOnCreateClientMobile(String attribute, String status) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("client.phone_option_pref_contact_add_to_book");
        String mySelectElement = findElement(By.xpath("//span/select/option[last()]")).getAttribute(attribute);
        System.out.println(mySelectElement);
        boolean expectedStatus = Boolean.valueOf(mySelectElement);
        System.out.println(expectedStatus);
        Assert.assertEquals(expectedStatus, Boolean.valueOf(status));
    }

    @Then("^I should see the Phone Type required error popup on the Create Client mobile$")
    public void iShouldSeeThePhoneTypeRequiredErrorPopupOnTheCreateClientMobile() throws Throwable {
        Wait.untilElementPresent("client.phone_type_required_popup");
        String popupMsg = findElement("client.phone_type_required_popup").getText();
        System.out.println(popupMsg);
        Assert.assertTrue(popupMsg.contains("Phone Type selection is required"));
    }

    @And("^I select Preferred Contact method as text from dropdown$")
    public void iSelectPreferredContactMethodAsTextFromDropdown() throws Throwable {
        Wait.untilElementPresent("client.pref_contact_add_to_book");
        DropDowns.selectByText("client.pref_contact_add_to_book", "Text");
    }
}