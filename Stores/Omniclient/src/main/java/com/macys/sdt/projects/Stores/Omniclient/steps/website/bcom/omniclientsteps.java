package com.macys.sdt.projects.Stores.Omniclient.steps.website.bcom;


import com.google.common.collect.Lists;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Omniclient.utils.OmniclientUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.macys.sdt.framework.interactions.Clicks.hoverForSelection;
import static com.macys.sdt.framework.interactions.Elements.*;
import static org.junit.Assert.*;

public class omniclientsteps extends StepUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private Integer countToDos;
    private String topListPlatinumPosition = "4";

    //TODO: SPLIT THE STEP DEFINITION CLASS INTO MORE CLASSES BASED ON RESPONSIBILITY
    //TODO: SPLIT JSON ELEMENTS INTO SEPARATE PAGES (MOSTLY ARE IN HOMEPAGE.JSON NOW)

    @Given("^I launch the bloomingdales's omniclient page$")
    public void i_launch_the_bloomingdaless_omniclient_page() throws Throwable {
        Navigate.visit("ocl");
        Wait.forPageReady();
        shouldBeOnPage("ocl");
    }

    @When("^I enter BLM \"([^\"]*)\" in username field of Omniclient login page$")
    public void i_enter_blm_something_in_username_field_of_omniclient_login_page(String associateID) throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", associateID);
    }

    @Then("^I enter BLM \"([^\"]*)\" in password field of Omniclient login page$")
    public void i_enter_blm_something_in_password_field_of_omniclient_login_page(String password) throws Throwable {
        TextBoxes.typeTextbox("logon_pg.password_textbox", password);
    }

    @Then("^I should be logged in \"([^\"]*)\" and see the BLM omniclient landing page$")
    public void i_should_be_logged_in_something_and_see_the_blm_omniclient_landing_page(String role) throws Throwable {
        switch (role.toLowerCase()) {
            case "associate":
                Wait.untilElementPresent("homepage.associate_homepage");
                elementShouldBePresent("homepage.associate_homepage");
                break;
            case "sales manager":
                Wait.untilElementPresent("homepage.sales_manager_homepage");
                elementShouldBePresent("homepage.sales_manager_homepage");
                break;
            case "general manager":
                Wait.untilElementPresent("homepage.general_manager_homepage");
                elementShouldBePresent("homepage.general_manager_homepage");
                break;
            case "corp admin":
                Wait.untilElementPresent("homepage.corp_admin_homepage");
                elementShouldBePresent("homepage.corp_admin_homepage");
                break;
            case "corp store exec":
                Thread.sleep(1000);
                Wait.untilElementPresent("homepage.mycustomers_link");
                Clicks.click("homepage.mycustomers_link");
                Wait.untilElementPresent("homepage.attention_header");
                elementShouldBePresent("homepage.attention_header");
                break;
        }
    }

    @And("^I click Sign In button of BLM Omniclient login page$")
    public void i_click_sign_in_button_of_blm_omniclient_login_page() throws Throwable {
        Clicks.click("logon_pg.signin_button");
        Wait.untilElementPresent("homepage.homePage_link");
    }

    @When("^I sign into Omniclient BLM application as associate$")
    public void i_sign_into_Omniclient_BLM_application_as_associate() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000083");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass83");
        Clicks.click("logon_pg.signin_button");
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.associate_homepage");
        elementShouldBePresent("homepage.associate_homepage");
    }

    @And("^I select the Search button on bloomingdale's home screen$")
    public void iSelectTheSearchButtonOnBloomingdaleSHomeScreen() throws Throwable {
        Clicks.click("homepage.search_button");
        Wait.untilElementPresent("search_results.searchresults_header");
    }

    @Then("^the No Search results screen is displayed in bconnected search results page$")
    public void theNoSearchResultsScreenIsDisplayedInBconnectedSearchResultsPage() throws Throwable {
        String message = Elements.findElement("search_results.noresults_verbiage").getText();
        assertEquals("Your search found no results for...", message);
    }

    //TODO: REMOVE THE 8000 MILLIS SLEEP FROM ALL LOGIN METHODS - FIND A BETTER APPROACH

    @When("^I sign into Omniclient BLM application as corporate admin$")
    public void i_sign_into_Omniclient_BLM_application_as_corporate_admin() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000102");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass102");
        Clicks.click("logon_pg.signin_button");
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.corp_admin_homepage");
        elementShouldBePresent("homepage.corp_admin_homepage");
    }

    @When("^I sign into Omniclient BLM application as General Manager$")
    public void i_sign_into_Omniclient_BLM_application_as_General_Manager() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000091");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass91");
        Clicks.click("logon_pg.signin_button");
        Thread.sleep(8000);
//        Wait.untilElementPresent("homepage.general_manager_homepage");
//        elementShouldBePresent("homepage.general_manager_homepage");
    }

    @When("^I sign into Omniclient BLM application as Corporate Store Executive$")
    public void i_sign_into_Omniclient_BLM_application_as_Corporate_Store_Executive() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000115");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass115");
        Clicks.click("logon_pg.signin_button");
        Thread.sleep(8000);
//        Clicks.click("homepage.mycustomers_link");
//        Wait.untilElementPresent("homepage.attention_header");
//        elementShouldBePresent("homepage.attention_header");
//        Clicks.click("homepage.ok_button_attention_header");
//        Thread.sleep(1000);

    }

    @When("^I sign into Omniclient BLM application as Selling Manager$")
    public void i_sign_into_omniclient_BLM_application_as_selling_manager() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000089");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass89");
        Clicks.click("logon_pg.signin_button");
        Thread.sleep(8000);
//        Wait.untilElementPresent("homepage.sales_manager_homepage");
//        elementShouldBePresent("homepage.sales_manager_homepage");
    }

    @Then("^I log out from the application$")
    public void i_log_out_from_the_application() throws Throwable {
        Thread.sleep(4000);
        Clicks.click("homepage.log_out_button");
        //Thread.sleep(2000);
        Wait.untilElementPresent("homepage.yes_button_logout_popup");
        Clicks.click("homepage.yes_button_logout_popup");
        Wait.untilElementPresent("homepage.login_page");
        elementShouldBePresent("homepage.login_page");

    }

    @Then("^I should be switched into the selected \"([^\"]*)\" from BLM$")
    public void i_should_be_switched_into_the_selected_something_from_blm(String role) throws Throwable {
        switch (role.toLowerCase()) {

            case "associate":
                Wait.untilElementPresent("homepage.associate_homepage");
                Elements.elementShouldBePresent("homepage.associate_homepage");
                Thread.sleep(2000);
                break;

            case "sales manager":
                Wait.untilElementPresent("homepage.sales_manager_homepage");
                Elements.elementShouldBePresent("homepage.sales_manager_homepage");
                Thread.sleep(2000);
                break;

            case "corporate admin":
                Wait.untilElementPresent("homepage.corp_admin_homepage");
                Elements.elementShouldBePresent("homepage.corp_admin_homepage");
                Thread.sleep(2000);
                break;

            case "corporate store exec":
                Wait.untilElementPresent("homepage.mycustomers_link");
                Clicks.click("homepage.mycustomers_link");
                Wait.untilElementPresent("homepage.attention_header");
                elementShouldBePresent("homepage.attention_header");
                Clicks.click("homepage.ok_button_attention_header");
                Thread.sleep(1000);
                break;

            case "general manager":
                Wait.untilElementPresent("homepage.general_manager_homepage");
                Elements.elementShouldBePresent("homepage.general_manager_homepage");
                Thread.sleep(2000);
                break;

            default:
                fail("Role does not match a valid one");
        }
    }

    @When("^I sign in as another selling manager in BLM$")
    public void i_sign_in_as_another_selling_manager_in_blm() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000090");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass90");
        Clicks.click("logon_pg.signin_button");
        Thread.sleep(8000);
        Wait.untilElementPresent("homepage.sales_manager_homepage");
        elementShouldBePresent("homepage.sales_manager_homepage");
    }

    @When("^I navigate to MY TASKS page$")
    public void i_navigate_to_my_tasks_page() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_tasks_tab");
        Clicks.click("homepage.my_tasks_tab");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I click on LISTS tab from BLM$")
    public void i_click_on_lists_tab_from_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.lists_tab");
        Clicks.click("homepage.lists_tab");
        Wait.untilElementPresent("homepage.lists_page");
        elementShouldBePresent("homepage.lists_page");

    }

    //TODO: BREAK METHOD IN SMALLER ONES AND ENCAPSULATE THEM

    @And("^I create a TO DO from CREATE LIST page$")
    public void i_create_a_to_do_from_create_list_page(List<String> tab) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        for (String aTab : tab) {
            switch (aTab) {
                case "PROFILE":
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.continue_button_profile_tab");
                    Clicks.click("homepage.continue_button_profile_tab");
                    Wait.forPageReady();
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", "PROFILE01");
                    LocalDate date = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    date = date.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "DELETE LIST":
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.continue_button_profile_tab");
                    Clicks.click("homepage.continue_button_profile_tab");
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", aTab);
                    date = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    date = date.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "COMPLETE LIST":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.target_text_blm");
                    Clicks.click("homepage.target_text_blm");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.limit_custnr_todo");
                    TextBoxes.typeTextbox("homepage.limit_custnr_todo", "1");
                    Wait.untilElementPresent("homepage.continue_button_target_page");
                    Clicks.click("homepage.continue_button_target_page");
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", aTab);
                    date = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    date = date.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "TARGET GM":
                    Wait.forPageReady();
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementPresent("homepage.target_text_blm");
                    Clicks.click("homepage.target_text_blm");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_send_toDos_within_specific");
                    Clicks.click("homepage.gm_send_toDos_within_specific");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_gmm_select");
                    Clicks.click("homepage.gm_gmm_select");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_div_select");
                    Clicks.click("homepage.gm_div_select");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_mgm_select");
                    Clicks.click("homepage.gm_mgm_select");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_dep_select");
                    Clicks.click("homepage.gm_dep_select");
                    Wait.forPageReady();
                    Clicks.click("homepage.continue_button_select_SAs_by_section");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.forPageReady();
                    TextBoxes.typeTextbox("homepage.limit_custnr_todo", "10");
                    Clicks.click("homepage.continue_button_target_page");
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", "TITLE-BLM2");
                    date = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    date = date.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "TARGET CORP STORE EXEC":
                    Wait.forPageReady();
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementPresent("homepage.target_text_blm");
                    Clicks.click("homepage.target_text_blm");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.send_todo_specific_target_tab");
                    Clicks.click("homepage.send_todo_specific_target_tab");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_gmm_select");
                    Clicks.click("homepage.gm_gmm_select");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_div_select");
                    Clicks.click("homepage.gm_div_select");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_mgm_select");
                    Clicks.click("homepage.gm_mgm_select");
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_dep_select");
                    Clicks.click("homepage.gm_dep_select");
                    Wait.forPageReady();
                    Clicks.click("homepage.continue_button_select_SAs_by_section");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.forPageReady();
                    TextBoxes.typeTextbox("homepage.limit_custnr_todo", "10");
                    Clicks.click("homepage.continue_button_target_page");
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", "TITLE-BLM1");
                    date = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    date = date.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "TARGET SA":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.target_text_blm");
                    Clicks.click("homepage.target_text_blm");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.continue_button_target_page");
                    Clicks.click("homepage.continue_button_target_page");
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", "TARGET SA");
                    date = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    date = date.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "TRANSACTIONS":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementPresent("homepage.trans_text");
                    Clicks.click("homepage.trans_text");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.continue_button_profile_tab");
                    Clicks.click("homepage.continue_button_profile_tab");
                    Wait.forPageReady();
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", "TRANSBLM01");
                    LocalDate date1 = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date1));
                    date = date1.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
                    break;

                case "LOYALIST":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Clicks.click("homepage.create_list_tab");
                    Wait.untilElementPresent("homepage.loyal_text");
                    Clicks.click("homepage.loyal_text");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.continue_button_profile_tab");
                    Clicks.click("homepage.continue_button_profile_tab");
                    Wait.forPageReady();
                    TextBoxes.typeTextbox("homepage.title_field_create_todo", "LOYALIST01");
                    LocalDate date2 = LocalDate.now();
                    TextBoxes.typeTextbox("homepage.start_date_field_create_todo", DATE_TIME_FORMATTER.format(date2));
                    date = date2.plusDays(1);
                    TextBoxes.typeTextbox("homepage.end_date_field_create_todo", DATE_TIME_FORMATTER.format(date));
                    Clicks.click("homepage.description_field_create_todo");
                    TextBoxes.typeTextbox("homepage.description_field_create_todo", "My To Do list-BLM");
                    Clicks.click("homepage.create_my_list_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.ok_button_attention_popup_after_create_todo");
                    Clicks.click("homepage.ok_button_attention_popup_after_create_todo");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheListToBeSuccessfullyProcessed();
            }
        }

    }

    @When("^I navigate to Bloomingdales Homepage$")
    public void i_navigate_to_bloomingdales_homepage() throws Throwable {
        Wait.untilElementPresent("homepage.dashboard_tab");
        Clicks.click("homepage.dashboard_tab");
        Wait.untilElementNotPresent("homepage.loading_page");

    }

    @Then("^I should see the list of TO DOS on BLM$")
    public void i_should_see_the_list_of_to_dos_on_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.todos_list_container_blm");
        elementShouldBePresent("homepage.todos_list_container_blm");
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the General Manager on BLM$")
    public void i_should_see_the_new_to_do_something_created_by_the_general_manager_on_blm(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        String listText = findElement("homepage.last_todo_in_list_blm").getText().trim();
        Assert.assertEquals(name, listText);
    }


    @Then("^I should see the TO DO \"([^\"]*)\" on the BLM dashboard$")
    public void i_should_see_the_to_do_something_on_the_blm_dashboard(String todo) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        findElement("homepage.blm_todos_homepage_dashboard");
        elementShouldBePresent("homepage.blm_todos_homepage_dashboard");
        String todoText = findElement("homepage.last_todo_in_homepage").getAttribute("title");
        Assert.assertEquals(todo, todoText);
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the Corporate Store Executive on BLM$")
    public void i_should_see_the_new_to_do_something_created_by_the_corporate_store_executive_on_blm(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        String listText = findElement("homepage.last_todo_in_list_blm").getText().trim();
        Assert.assertEquals(name, listText);
    }


    @And("^I should see the new TO DO created by another selling manager$")
    public void i_should_see_the_new_to_do_created_by_another_selling_manager() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        String listText = findElement("homepage.last_todo_in_list_blm").getText().trim();
        Assert.assertEquals("TITLE-BLM1", listText);
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the Corporate Admin on BLM$")
    public void i_should_see_the_new_to_do_something_created_by_the_corporate_admin_on_blm(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        String listText = findElement("homepage.last_todo_in_list_blm").getText().trim();
        Assert.assertEquals(name, listText);
    }

    @When("^I sign into Omniclient BLM application as District Manager$")
    public void i_sign_into_Omniclient_BLM_application_as_district_manager() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000093");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass93");
        Clicks.click("logon_pg.signin_button");
        Thread.sleep(8000);
    }


    @And("^I should see the Created By name on BLM:$")
    public void i_should_see_the_created_by_name_on_blm(List<String> name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        String createdByExpected = name.get(0);
        String createdByActual = findElement("homepage.last_toDo_createdby").getText().trim();
        System.out.println("Expected Name: " + createdByExpected + " | " + "Actual Name: " + createdByActual);
        Assert.assertEquals(createdByExpected, createdByActual);

    }

    @When("^I click on the newly created Bloomingdales To Do$")
    public void i_click_on_the_newly_created_bloomingdales_to_do() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.last_todo_in_list_blm", 30);
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        Clicks.hoverForSelection("homepage.last_todo_in_list_blm");
        Clicks.click("homepage.last_todo_in_list_blm");

    }

    @And("^I should see a list of Bloomingdales associates who received the To Do$")
    public void i_should_see_a_list_of_bloomingdales_associates_who_received_the_to_do() throws Throwable {
        Wait.untilElementPresent("homepage.event_user_container");
        Elements.elementShouldBePresent("homepage.event_user_container");
    }

    @And("^I add a new BLM Client: \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_add_a_new_blm_client(String fname, String lname, String address, String city, String zip, String hint, String phone, String state, String email) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementPresent("homepage.telephone_textbox");
        TextBoxes.typeTextbox("homepage.telephone_textbox", phone);
        Thread.sleep(1000);
        Wait.secondsUntilElementPresent("homepage.search_button_homepage", 10);
        Clicks.hoverForSelection("homepage.search_button_homepage");
        Clicks.click("homepage.search_button_homepage");
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.searchresults_header", 10);
        Wait.untilElementPresent("homepage.add_new_client_link");
        Clicks.click("homepage.add_new_client_link");
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.add_new_client_page");
        TextBoxes.typeTextbox("homepage.add_new_client_fname", fname);
        TextBoxes.typeTextbox("homepage.add_new_client_lname", lname);
        DropDowns.selectByText("homepage.add_new_client_phone_type", "MOBILE");
        TextBoxes.typeTextbox("homepage.email_input_field_new_customer", email);
        TextBoxes.typeTextbox("homepage.add_new_client_address", address);
        TextBoxes.typeTextbox("homepage.add_new_client_city", city);
        DropDowns.selectByText("homepage.add_new_client_state", state);
        TextBoxes.typeTextbox("homepage.add_new_client_zip", zip);
        TextBoxes.typeTextbox("homepage.add_new_client_hint", hint);
        Clicks.click("homepage.add_new_client_preff_phone");
        Clicks.click("homepage.add_new_client_save");
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.add_new_client_ok");
        Clicks.click("homepage.add_new_client_ok");
        Wait.forPageReady();
    }

    @And("^I click yes button on the credit card required attention popup$")
    public void i_click_yes_button_on_the_credit_card_required_attention_popup() throws Throwable {
        Wait.untilElementPresent("homepage.yes_button_credit_card_attention_popup");
        elementShouldBePresent("homepage.yes_button_credit_card_attention_popup");
        Clicks.click("homepage.yes_button_credit_card_attention_popup");
    }

    @And("^I click yes on ok button from credit card notification popup$")
    public void i_click_yes_on_ok_button_from_credit_card_notification_popup() throws Throwable {
        Wait.untilElementPresent("homepage.ok_button_credit_card_notification_popup");
        elementShouldBePresent("homepage.ok_button_credit_card_notification_popup");
        Clicks.click("homepage.ok_button_credit_card_notification_popup");
    }

    @And("^I navigate to Manage Customer tab$")
    public void i_navigate_to_manage_customer_tab() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.manage_customers_tab");
        Clicks.click("homepage.manage_customers_tab");
    }

    @Then("^the following information should be displayed in Preferred Information section BLM:$")
    public void the_following_information_should_be_displayed_in_preferred_information_section_blm(List<String> row) throws Throwable {
        Wait.untilElementPresent("homepage.manage_customers_tabs_section");
        Wait.untilElementPresent("homepage.preferred_information_section");
        String preferredText = findElement("homepage.preferred_information_section").getText();
        System.out.println(preferredText);
        assertTrue(preferredText.contains("Preferred Information:"));
        OmniclientUtils.waitForAngularLoad();
        for (String aRow : row) {
            switch (aRow) {
                case "Name":
                    Wait.untilElementPresent("homepage.pref_name_row_manage_clients");
                    Elements.elementShouldBePresent("homepage.pref_name_row_manage_clients");
                    break;
                case "Preferred address":
                    Wait.untilElementPresent("homepage.pref_address_row_manage_customers");
                    Elements.elementShouldBePresent("homepage.pref_address_row_manage_customers");
                    break;
                case "Preferred phone number":
                    Wait.untilElementPresent("homepage.pref_phone_row_manage_customers_blm");
                    Elements.elementShouldBePresent("homepage.pref_phone_row_manage_customers_blm");
                    break;
                case "Preferred Email":
                    Wait.untilElementPresent("homepage.pref_email_manage_custom_blm");
                    Elements.elementShouldBePresent("homepage.pref_email_manage_custom_blm");
            }

        }
    }

    @And("^the following information should be displayed in Primary Information section BLM:$")
    public void the_following_information_should_be_displayed_in_primary_information_section_blm(List<String> row) throws Throwable {
        Wait.untilElementPresent("homepage.manage_customers_tabs_section");
        Wait.untilElementPresent("homepage.primary_information_section");
        String primaryText = findElement("homepage.primary_information_section").getText();
        System.out.println(primaryText);
        assertTrue(primaryText.contains("Primary Information:"));
        for (String aRow : row) {
            switch (aRow) {
                case "Name":
                    Elements.elementShouldBePresent("homepage.primary_name_row_manage_customers");
                    break;
                case "Primary address":
                    Elements.elementShouldBePresent("homepage.primary_address_row_manage_customers");
                    break;
                case "Primary phone number":
                    Elements.elementShouldBePresent("homepage.primary_phone_row_manage_customers");
                    break;
            }

        }
    }

    @And("^the Additional Information section is displayed with the following sections BLM:$")
    public void the_additional_information_section_is_displayed_with_the_following_sections_blm(List<String> row) throws Throwable {
        Wait.untilElementPresent("homepage.manage_customers_tabs_section");
        Wait.untilElementPresent("homepage.additional_information_section");
        String additionalText = findElement("homepage.additional_information_section").getText();
        assertTrue(additionalText.contains("Additional Information:"));
        for (String aRow : row) {
            switch (aRow) {
                case "Address(es)":
                    Elements.elementShouldBePresent("homepage.additional_addresses_row_blm");
                    break;
                case "Phone(s)":
                    Elements.elementShouldBePresent("homepage.additional_phones_row_blm");
                    break;
                case "Email(s)":
                    Elements.elementShouldBePresent("homepage.additional_email_manage_customers");

            }
        }
    }

    @When("^I click on the Edit button from the CUSTOMER INFO section BLM$")
    public void i_click_on_the_edit_button_from_the_customer_info_section_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.edit_button_manage_customers");
        Clicks.click("homepage.edit_button_manage_customers");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^the client information fields become editable BLM$")
    public void the_client_information_fields_become_editable_blm() throws Throwable {
        Wait.untilElementPresent("homepage.pref_name_input_field_blm");
        elementShouldBePresent("homepage.pref_name_input_field_blm");
    }

    @When("^we update data \"([^\"]*)\" into Additional information fields BLM$")
    public void we_update_data_something_into_additional_information_fields_blm(String name) throws Throwable {
        TextBoxes.typeTextbox("homepage.pref_name_input_field_blm", name);
    }


    @Then("^updated data is displayed for the Additional information section BLM$")
    public void updated_data_is_displayed_for_the_additional_information_section_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.preferred_name_added_blm");
        elementShouldBePresent("homepage.preferred_name_added_blm");
        String prefName = findElement("homepage.preferred_name_added_blm").getText();
        assertTrue(prefName.contains("BLM"));
    }

    @And("^we save the changes BLM$")
    public void we_save_the_changes_blm() throws Throwable {
        Wait.untilElementPresent("homepage.save_button_manage_customer");
        elementShouldBePresent("homepage.save_button_manage_customer");
        Clicks.click("homepage.save_button_manage_customer");
    }

    @And("^I click on My Book radio button$")
    public void i_click_on_my_book_radio_button() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementPresent("homepage.my_book_radio_button");
        Clicks.click("homepage.my_book_radio_button");
    }

    @And("^I type the name of a customer \"([^\"]*)\" in the search box$")
    public void i_type_the_name_of_a_customer_something_in_the_search_box(String name) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.search_word_text_box");
        TextBoxes.typeTextbox("homepage.search_word_text_box", name);
    }

    @And("^I click on the omniclient search button$")
    public void i_click_on_the_omniclient_search_button() throws Throwable {
        Wait.untilElementPresent("homepage.search_button_homepage");
        Clicks.click("homepage.search_button_homepage");
    }

    @And("^I click on the searched client from the customers results list$")
    public void i_click_on_the_searched_client_from_the_customers_results_list() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.first_result_in_search_customer_list");
        Clicks.click("homepage.first_result_in_search_customer_list");

    }

    @And("^I click on the CANCEL button from CUSTOMER INFO section BLM$")
    public void i_click_on_the_cancel_button_from_customer_info_section_blm() throws Throwable {
        Wait.untilElementPresent("homepage.cancel_button_customer_info");
        Clicks.click("homepage.cancel_button_customer_info");
    }

    @Then("^I should see the information popup from CUSTOMER INFO section BLM$")
    public void i_should_see_the_information_popup_from_customer_info_section_blm() throws Throwable {
        Wait.untilElementPresent("homepage.cancel_popup_customer_info");
        elementShouldBePresent("homepage.cancel_popup_customer_info");

    }

    @When("^I click on the ok button on the popup from CUSTOMER INFO section$")
    public void i_click_on_the_ok_button_on_the_popup_from_customer_info_section() throws Throwable {
        Wait.untilElementPresent("homepage.ok_button_cancel_popup_cust_info");
        elementShouldBePresent("homepage.ok_button_cancel_popup_cust_info");
        Clicks.click("homepage.ok_button_cancel_popup_cust_info");
    }


    @Then("^the popup from CUSTOMER INFO section is no longer displayed$")
    public void the_popup_from_customer_info_section_is_no_longer_displayed() throws Throwable {
        Wait.untilElementNotPresent("homepage.cancel_popup_customer_info");
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @When("^click on ADD button from Address section BLM$")
    public void click_on_add_button_from_address_section_blm() throws Throwable {
        Thread.sleep(2000);
        WebElement addBtn = findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='address']"));
        Clicks.click(addBtn);
//        Wait.untilElementPresent("homepage.edit_customer_add_addr_button");
//        Clicks.click("homepage.edit_customer_add_addr_button");
    }


    @And("^we add additional address \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" BLM$")
    public void we_add_additional_address_blm(String addr, String city, String zip, String state) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_blm", addr);
        DropDowns.selectByText("homepage.address_type_dropdown_blm", "BUSINESS");
        TextBoxes.typeTextbox("homepage.city_first_additional_address_blm", city);
        DropDowns.selectByText("homepage.state_dropdown_manage_client_blm", state);
        TextBoxes.typeTextbox("homepage.zipcode_field_first_additional_address_blm", zip);
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^we click on ADD button from Phones section BLM$")
    public void we_click_on_add_button_from_phones_section_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        WebElement addBtn = findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='phone']"));
        Clicks.click(addBtn);
//        Wait.untilElementPresent("homepage.edit_customer_add_phone_button");
//        Clicks.click("homepage.edit_customer_add_phone_button");
    }

    @And("^we add additional phone number \"([^\"]*)\" BLM$")
    public void we_add_additional_phone_number_something_blm(String additionalphone) throws Throwable {
        TextBoxes.typeTextbox("homepage.phone_second_field_additional_blm", additionalphone);
        DropDowns.selectByText("homepage.phone_type_dropdown_2_blm", "MOBILE");
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^we click on ADD button from Emails section BLM$")
    public void we_click_on_add_button_from_emails_section_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        WebElement addBtn = findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='email']"));
        Clicks.click(addBtn);
//        Wait.untilElementPresent("homepage.edit_customer_add_email_button");
//        Clicks.click("homepage.edit_customer_add_email_button");
    }

    @And("^we add additional email \"([^\"]*)\" BLM$")
    public void we_add_additional_email_something_blm(String additionalemail) throws Throwable {
        Wait.untilElementPresent("homepage.additional_email_input");
        TextBoxes.typeTextbox("homepage.additional_email_input", additionalemail);

    }

    @And("^we click on SAVE button from Customer Info tab BLM$")
    public void we_click_on_save_button_from_customer_info_tab_blm() throws Throwable {
        Thread.sleep(1000);
        Elements.elementInView("homepage.save_button_manage_customer");
        Clicks.click("homepage.save_button_manage_customer");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^data is displayed for the Additional Information section BLM$")
    public void data_is_displayed_for_the_additional_information_section_blm(DataTable info) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (List<String> list : info.raw()) {
            String infoType = list.get(1);
            String infoValue = list.get(0);
            switch (infoType) {
                case "ADDRESS":
                    String additionaladdress = findElement("homepage.after_save_additional_address_blm").getText().toUpperCase();
                    System.out.println("print" + additionaladdress);
                    Assert.assertTrue(additionaladdress.contains(infoValue));
                    break;
                case "CITY":
                    String additionalcity = findElement("homepage.after_save_additional_city_blm").getText().toUpperCase();
                    System.out.println("print" + additionalcity);
                    Assert.assertTrue(additionalcity.contains(infoValue));
                    break;
                case "ZIP":
                    String additionalzip = findElement("homepage.after_save_additional_zip_blm").getText();
                    System.out.println("print" + additionalzip);
                    Assert.assertTrue(additionalzip.contains(infoValue));
                    break;
                case "PHONE":
                    String additionalphone = findElement("homepage.after_save_additional_phone_blm").getText();
                    System.out.println("phone" + additionalphone);
                    Assert.assertTrue(additionalphone.contains(infoValue));
                    break;
                case "EMAIL":
                    String additionalemail = findElement("homepage.after_save_additional_email").getText();
                    System.out.println("email" + additionalemail);
                    Assert.assertTrue(additionalemail.contains(infoValue));
                    break;
            }
        }

    }

    @And("^I change the preferred address by selecting the radio button from the new added address BLM$")
    public void i_change_the_preferred_address_by_selecting_the_radio_button_from_the_new_added_address_blm() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.first_additional_address_radio_button_blm");
        elementShouldBePresent("homepage.first_additional_address_radio_button_blm");
        Clicks.click("homepage.first_additional_address_radio_button_blm");
    }

    @And("^I change the preferred number by selecting the radio button from the new added number BLM$")
    public void i_change_the_preferred_number_by_selecting_the_radio_button_from_the_new_added_number_blm() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.second_additional_phone_number_blm");
        elementShouldBePresent("homepage.second_additional_phone_number_blm");
        Clicks.click("homepage.second_additional_phone_number_blm");
    }

    @And("^I change the preferred email by selecting the radio button from the new added email BLM$")
    public void i_change_the_preferred_email_by_selecting_the_radio_button_from_the_new_added_email_blm() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.first_additional_email_blm");
        Clicks.click("homepage.first_additional_email_blm");
    }

    @Then("^the new preferred information is displayed BLM$")
    public void the_new_preferred_information_is_displayed_blm(DataTable info) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (List<String> list : info.raw()) {
            String infoType = list.get(1);
            String infoValue = list.get(0);
            switch (infoType) {
                case "ADDRESS":
                    String updateprefaddress = findElement("homepage.updated_pref_address_blm").getText().toUpperCase();
                    System.out.println("address" + updateprefaddress);
                    Assert.assertTrue(updateprefaddress.contains(infoValue));
                    break;
                case "CITY":
                    String updateprefcity = findElement("homepage.updated_pref_city_blm").getText().toUpperCase();
                    System.out.println("city" + updateprefcity);
                    Assert.assertTrue(updateprefcity.contains(infoValue));
                    break;
                case "ZIP":
                    String updateprefzip = findElement("homepage.updated_pref_zip_blm").getText();
                    System.out.println("zip" + updateprefzip);
                    Assert.assertTrue(updateprefzip.contains(infoValue));
                    break;
                case "PHONE":
                    String updateprefphone = findElement("homepage.updated_pref_phone_blm").getText();
                    System.out.println("phone" + updateprefphone);
                    Assert.assertTrue(updateprefphone.contains(infoValue));
                    break;
                case "EMAIL":
                    String updateprefemail = findElement("homepage.update_pref_email_blm").getText();
                    System.out.println("email" + updateprefemail);
                    Assert.assertTrue(updateprefemail.contains(infoValue));
                    break;
            }
        }
    }

    @Then("^I should see the error popup from CUSTOMER INFO section BLM$")
    public void i_should_see_the_error_popup_from_customer_info_section_blm() throws Throwable {
        Wait.untilElementPresent("homepage.error_pop_customer_info");
        elementShouldBePresent("homepage.error_pop_customer_info");
        Wait.untilElementPresent("homepage.error_msg_customer_info");
        String errorMsg = findElement("homepage.error_msg_customer_info").getText().trim();
        Assert.assertTrue(errorMsg.contains("Error"));
        Assert.assertTrue(errorMsg.contains("Please correct these errors and try saving again."));
    }

    @And("^we update the existing email to \"([^\"]*)\" BLM$")
    public void we_update_the_existing_email_to_something_blm(String email) throws Throwable {
        try {
            findElement("homepage.existing_email_input").isDisplayed();
            findElement("homepage.existing_email_input").clear();
            we_click_on_add_button_from_emails_section_blm();
            Wait.untilElementPresent("homepage.existing_email_input");
            TextBoxes.typeTextbox("homepage.existing_email_input", email);
        } catch (Exception e) {
            we_click_on_add_button_from_emails_section_blm();
            Wait.untilElementPresent("homepage.existing_email_input");
            TextBoxes.typeTextbox("homepage.existing_email_input", email);
        }

    }

    @Then("^I should see the \"([^\"]*)\" on CUSTOMER INFO section$")
    public void i_should_see_the_something_on_customer_info_section(String correctedemail) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.updated_pref_address_blm");
        String prefInfo = findElement("homepage.updated_pref_address_blm").getText().trim();
        Assert.assertTrue(prefInfo.contains(correctedemail));
    }

    @And("^I click on the OK button on the error popup from CUSTOMER INFO section BLM$")
    public void i_click_on_the_ok_button_on_the_error_popup_from_customer_info_section_blm() throws Throwable {
        elementShouldBePresent("homepage.ok_button_manage_client_popup");
        Clicks.click("homepage.ok_button_manage_client_popup");
    }

    @When("^I add \"([^\"]*)\" in Street Address 1 field BLM$")
    public void i_add_something_in_street_address_1_field_blm(String address2) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_blm", address2);
    }

    @And("^I add city \"([^\"]*)\" in the City input field BLM$")
    public void i_add_city_something_in_the_city_input_field_blm(String city1) throws Throwable {
        TextBoxes.typeTextbox("homepage.city_first_additional_address_blm", city1);
    }

    @And("^I select random state from State dropdown BLM$")
    public void i_select_random_state_from_state_dropdown_blm() throws Throwable {
        DropDowns.selectRandomValue("homepage.state_dropdown_manage_client_blm");
    }

    @And("^I select random type from TYPE dropdown BLM$")
    public void i_select_random_type_from_type_dropdown_blm() throws Throwable {
        DropDowns.selectRandomValue("homepage.address_type_dropdown_blm");
    }

    @And("^I enter zip code \"([^\"]*)\" in the Zip Code input field BLM$")
    public void i_enter_zip_code_something_in_the_zip_code_input_field_blm(String zip1) throws Throwable {
        TextBoxes.typeTextbox("homepage.zipcode_field_first_additional_address_blm", zip1);
    }

    @Then("^data is successfully saved BLM Manage Customer$")
    public void data_is_successfully_saved_blm_manage_customer() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.edit_button_manage_customers");
        elementShouldBePresent("homepage.edit_button_manage_customers");
    }

    @And("^we add incomplete additional phone number \"([^\"]*)\" BLM$")
    public void we_add_incomplete_additional_phone_number_something_blm(String BLMincompleteadditionalphone) throws Throwable {
        TextBoxes.typeTextbox("homepage.phone_second_field_additional_blm", BLMincompleteadditionalphone);
        DropDowns.selectByText("homepage.phone_type_dropdown_2_blm", "MOBILE");
    }

    @And("^we add additional Address Line 1 \"([^\"]*)\" BLM$")
    public void we_add_additional_address_line_1_something_blm(String addressline1) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_blm", addressline1);
    }

    @And("^we add additional Address Line 2 \"([^\"]*)\" BLM$")
    public void we_add_additional_address_line_2_something_blm(String addressline2) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_field_2", addressline2);
    }

    @And("^we add remaining address mandatory fields BLM$")
    public void we_add_remaining_address_mandatory_fields_blm() throws Throwable {
        DropDowns.selectByText("homepage.address_type_dropdown_blm", "BUSINESS");
        TextBoxes.typeTextbox("homepage.city_first_additional_address_blm", "random");
        DropDowns.selectByText("homepage.state_dropdown_manage_client_blm", "AL");
        TextBoxes.typeTextbox("homepage.zipcode_field_first_additional_address_blm", "12345");
    }

    @And("^we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE blm$")
    public void we_add_additional_address_line_address_line_1_and_2_remaining_mandatory_fields_and_click_save_blm(List<String> addressline1and2) throws Throwable {
        for (String line : addressline1and2) {
            we_add_additional_address_line_1_something_blm(line);
            we_add_additional_address_line_2_something_blm(line);
            we_add_remaining_address_mandatory_fields_blm();
            we_click_on_save_button_from_customer_info_tab_blm();
            i_should_see_the_error_popup_from_customer_info_section_blm();
            i_click_on_the_ok_button_on_the_error_popup_from_customer_info_section_blm();


        }

    }

    @Then("^the search result page is displayed with the following columns BLM:$")
    public void the_search_result_page_is_displayed_with_the_following_columns_blm(List<String> column) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.search_results_page");
        elementShouldBePresent("homepage.search_results_page");
        String column1 = findElement("homepage.search_results_page").getText();
        for (String aColumn : column) {
            switch (aColumn) {
                case "Name":
                    assertTrue(column1.contains(aColumn));
                    break;
                case "Address":
                    assertTrue(column1.contains(aColumn));
                    break;
                case "Phone Number":
                    assertTrue(column1.contains(aColumn));
                    break;
                case "Affiliated with Me":
                    assertTrue(column1.contains(aColumn));
                    break;
                case "Affiliated to Other Sales Professional(s)":
                    assertTrue(column1.contains(aColumn));
                    break;
                default:
                    fail("NOT a valid Column name");
            }
        }
    }

    @And("^I verify that each incorrect email is rejected with an ERROR message BLM:$")
    public void i_verify_that_each_incorrect_email_is_rejected_with_an_error_message_blm(DataTable emails) throws Throwable {
        for (List<String> aEmail : emails.raw()) {
            String emailItem = aEmail.get(0);
            we_add_additional_email_something_blm(emailItem);
            we_click_on_save_button_from_customer_info_tab_blm();
            i_should_see_the_error_popup_from_customer_info_section_blm();
            i_click_on_the_ok_button_on_the_popup_from_customer_info_section();
            the_popup_from_customer_info_section_is_no_longer_displayed();
        }
    }

    @And("^I verify that each incorrect email is autocorrected BLM:$")
    public void i_verify_that_each_incorrect_email_is_autocorrected_blm(DataTable emails) throws Throwable {
        for (List<String> list : emails.raw()) {
            String incorrect = list.get(0);
            String corrected = list.get(1);
            i_click_on_the_edit_button_from_the_customer_info_section_blm();
            we_update_the_existing_email_to_something_blm(incorrect);
            we_click_on_save_button_from_customer_info_tab_blm();
            i_should_see_the_something_on_customer_info_section(corrected);

        }
    }

    @And("^I successfully update the Address Information from CUSTOMER INFO:$")
    public void i_successfully_update_the_address_information_from_customer_info(DataTable addressinfo) throws Throwable {
        for (List<String> list : addressinfo.raw()) {
            String addr1 = list.get(0);
            String city = list.get(1);
            String state = list.get(2);
            String zip = list.get(3);
            String country = list.get(4);
            Wait.untilElementNotPresent("homepage.loading_page");
            Thread.sleep(2000);
            Wait.untilElementPresent("homepage.edit_button_manage_customers");
            Clicks.click("homepage.edit_button_manage_customers");
            Wait.untilElementPresent("homepage.exist_address1");
            findElement("homepage.exist_address1").clear();
            TextBoxes.typeTextbox("homepage.exist_address1", addr1);
            if (country.isEmpty()) {
                DropDowns.selectByIndex("homepage.exist_country_dropdown", 0);
            } else {
                DropDowns.selectByValue("homepage.exist_country_dropdown", country);
            }
            if (!city.isEmpty()) {
                TextBoxes.typeTextbox("homepage.exist_city", city);
            } else {
                findElement("homepage.exist_city").clear();
            }
            if (country.equals("US") || country.equals("CA")) {
                if (!state.isEmpty()) {
                    DropDowns.selectByText("homepage.exist_state_dropdown", state);
                } else {
                    DropDowns.selectByIndex("homepage.exist_state_dropdown", 0);
                }
            } else if (!state.isEmpty()) {
                TextBoxes.typeTextbox("homepage.exist_state_free_form", state);
            } else {
                findElement("homepage.exist_state_free_form").clear();
            }
            if (!zip.isEmpty()) {
                TextBoxes.typeTextbox("homepage.exist_zip", zip);
            } else {
                findElement("homepage.exist_zip").clear();
            }
            DropDowns.selectByIndex("homepage.exist_addr_type", 1);
            we_click_on_save_button_from_customer_info_tab_blm();
            data_is_successfully_saved_blm_manage_customer();
        }
    }

    @Then("^the Create new Customer page is displayed BLM$")
    public void the_create_new_customer_page_is_displayed_blm() throws Throwable {
        elementShouldBePresent("homepage.create_new_customer_page");
    }

    @And("^I change the preferred number by selecting the radio button from the third added number BLM$")
    public void i_change_the_preferred_number_by_selecting_the_radio_button_from_the_third_added_number_blm() throws Throwable {
        Wait.untilElementPresent("homepage.third_additional_phone_number_blm");
        elementShouldBePresent("homepage.third_additional_phone_number_blm");
        Clicks.click("homepage.third_additional_phone_number_blm");
    }

    @And("^we add additional third phone number \"([^\"]*)\" BLM$")
    public void we_add_additional_third_phone_number_something_blm(String additionalphone) throws Throwable {
        TextBoxes.typeTextbox("homepage.phone_third_field_additional_blm", additionalphone);
        DropDowns.selectByText("homepage.phone_type_dropdown_3_blm", "MOBILE");
    }

    @And("^I click on the My Customers radio button BLM$")
    public void i_click_on_the_my_customers_radio_button_blm() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        findElement("homepage.all_customers_radio_button");
        Thread.sleep(4000);
        Clicks.click("homepage.all_customers_radio_button");
    }

    @And("^I clear the third added phone number$")
    public void i_clear_the_third_added_phone_number() throws Throwable {
        findElement("homepage.phone_third_field_additional_blm").clear();
    }

    @And("^I select the second number as preferred$")
    public void i_select_the_second_number_as_preferred() throws Throwable {
        Wait.untilElementPresent("homepage.second_additional_phone_number_blm");
        elementShouldBePresent("homepage.second_additional_phone_number_blm");
        Clicks.click("homepage.second_additional_phone_number_blm");
    }

    @And("^I remove the Phone Number from the CUSTOMER INFO section BLM$")
    public void i_remove_the_phone_number_from_the_customer_info_section_blm() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.phone_first_field_additional_blm");
        findElement("homepage.phone_first_field_additional_blm").clear();
    }

    @And("^I remove the Email from the CUSTOMER INFO section BLM$")
    public void i_remove_the_email_from_the_customer_info_section_blm() throws Throwable {
        Wait.untilElementPresent("homepage.existing_email_input");
        findElement("homepage.existing_email_input").clear();
    }

    @And("^I select as preferred method of contact \"([^\"]*)\"$")
    public void i_select_as_preferred_method_of_contact_something(String strArg1) throws Throwable {
        Wait.untilElementPresent("homepage.email_preffered_radio_btn");
        Clicks.click("homepage.email_preffered_radio_btn");
    }

    @And("^we add additional phone number \"([^\"]*)\" and do not select a Phone Type BLM$")
    public void we_add_additional_phone_number_something_and_do_not_select_a_phone_type_blm(String phone) throws Throwable {
        Wait.untilElementPresent("homepage.phone_second_field_additional_blm");
        TextBoxes.typeTextbox("homepage.phone_second_field_additional_blm", phone);
    }

    @And("^I select a country \"([^\"]*)\" from the Country Dropdown$")
    public void i_select_a_country_something_from_the_country_dropdown(String country) throws Throwable {
        if (country.equals("default")) {
            DropDowns.selectByIndex("homepage.exist_country_dropdown", 0);
        } else {
            Wait.untilElementPresent("homepage.exist_country_dropdown");
            DropDowns.selectByValue("homepage.exist_country_dropdown", country);
        }
    }

    @And("^I add a state \"([^\"]*)\" in the State input field$")
    public void i_add_a_state_something_in_the_state_input_field(String state) throws Throwable {
        Wait.untilElementPresent("homepage.exist_state_free_form");
        TextBoxes.typeTextbox("homepage.exist_state_free_form", state);
    }

    @When("^I enter zip code \"([^\"]*)\" in the existing Zip Code input field BLM$")
    public void i_enter_zip_code_something_in_the_existing_zip_code_input_field_blm(String zip) throws Throwable {
        TextBoxes.typeTextbox("homepage.exist_zip", zip);
    }

    @And("^we add additional second phone number \"([^\"]*)\" BLM$")
    public void we_add_additional_second_phone_number_something_blm(String additionalphone) throws Throwable {
        TextBoxes.typeTextbox("homepage.phone_second_field_additional_blm", additionalphone);
        DropDowns.selectByText("homepage.phone_type_dropdown_2_blm", "MOBILE");
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the Corporate Admin BLM$")
    public void i_should_see_the_new_to_do_something_created_by_the_corporate_admin_BLM(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        String listText = findElement("homepage.last_todo_in_list_blm").getText().trim();
        Assert.assertEquals(name, listText);
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the General Manager BLM$")
    public void i_should_see_the_new_to_do_something_created_by_the_general_manager_BLM(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_todo_in_list_blm");
        elementShouldBePresent("homepage.last_todo_in_list_blm");
        String listText = findElement("homepage.last_todo_in_list_blm").getText().trim();
        Assert.assertEquals(name, listText);
    }

    @And("^I select a Bloomingdales client from OCL website Homepage$")
    public void iSelectABloomingdalesClientFromOCLWebsiteHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.client_list_homepage");
        Wait.untilElementPresent("homepage.first_client_on_homepage");
        Clicks.hoverForSelection("homepage.first_client_on_homepage");
        Clicks.click("homepage.first_client_on_homepage");
    }

    @Then("^I should see the Customer Profile website page$")
    public void iShouldSeeTheCustomerProfileWebsitePage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.client_info_page");
        Elements.elementShouldBePresent("homepage.client_info_page");
    }


    @Then("^the To Do \"([^\"]*)\" is saved on the Associates MY TASKS tab$")
    public void theToDoIsSavedOnTheAssociatesMYTASKSTab(String todo) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.first_in_list_todo_title_my_to_dos");
        String ToDoTitle = findElement("homepage.first_in_list_todo_title_my_to_dos").getText();
        System.out.println(ToDoTitle);
        Assert.assertEquals(todo, ToDoTitle);
    }

    @And("^Star Rewards section contains the following components BLM$")
    public void starRewardsSectionContainsTheFollowingComponentsBLM(List<String> column) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.star_rewards_info_table_blm");
        elementShouldBePresent("homepage.star_rewards_info_table_blm");
        String loyalList = findElement("homepage.star_rewards_info_table_blm").getText();
        System.out.println(loyalList);
        for (String columnSection : column) {
            switch (columnSection) {
                case "Loyalty ID":
                    Wait.untilElementPresent("homepage.loyalty_id_star_rewards_section");
                    Elements.elementShouldBePresent("homepage.loyalty_id_star_rewards_section");
                    break;
                case "Current Points":
                    Wait.untilElementPresent("homepage.current_points_star_rewards_section");
                    Elements.elementShouldBePresent("homepage.current_points_star_rewards_section");
                    break;
                case "Pending Points":
                    Wait.untilElementPresent("homepage.pending_points_star_rewards_section");
                    Elements.elementShouldBePresent("homepage.pending_points_star_rewards_section");
                    break;
                case "Deferred Points":
                    Wait.untilElementPresent("homepage.deferred_points_star_rewards_section");
                    Elements.elementShouldBePresent("homepage.deferred_points_star_rewards_section");
                    break;
                case "Points needed to get to next Reward":
                    Wait.untilElementPresent("homepage.points_to_next_reward_star_rewards_section");
                    Elements.elementShouldBePresent("homepage.points_to_next_reward_star_rewards_section");
                    break;
                default:
                    fail("Section is missing");
            }
        }
    }

    @Then("^Star Rewards section is displayed in main customer profile page BLM$")
    public void starRewardsSectionIsDisplayedInMainCustomerProfilePageBLM() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.stars_rewards_section");
        elementShouldBePresent("homepage.stars_rewards_section");
    }

    @And("^the plus sign is displayed in the left of Star Rewards section BLM$")
    public void thePlusSignIsDisplayedInTheLeftOfStarRewardsSectionBLM() throws Throwable {
        Wait.untilElementPresent("homepage.plus_sign_star_rewards_section");
        elementShouldBePresent("homepage.plus_sign_star_rewards_section");
    }

    @And("^I mark all TO DOs as completed TO DOS tab BLM$")
    public void iMarkAllTODOsAsCompletedTODOSTabBLM(List<String> option) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.status_dropdown_todos_tab");
        for (String dropdownOption : option) {
            switch (dropdownOption) {

                case "EMAIL":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "EMAIL");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;

                case "MAIL":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "MAIL");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;

                case "TEXT":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "TEXT");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;

                case "IN PERSON":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "IN PERSON");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;

                case "PHONE LEFT MSG":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "PHONE LEFT MSG");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;

                case "NO ACTION":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "NO ACTION");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;

                case "PHONE COMPLETE":
                    try {
                        while (findElement("homepage.status_dropdown_todos_tab").isDisplayed()) {
                            DropDowns.selectByText("homepage.status_dropdown_todos_tab", "PHONE COMPLETE");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Clicks.click("homepage.todos_tab_save_button");
                            Wait.untilElementNotPresent("homepage.loading_page");
                            Navigate.browserRefresh();
                            Wait.untilElementNotPresent("homepage.loading_page");
                        }
                    } catch (Exception e) {
                        System.out.println("All TODOs are marked as completed");
                    }
                    break;
                default:
                    fail("NOT a valid option");
            }
        }
    }

    @Then("^I remove the newly add BLM Client$")
    public void iRemoveTheNewlyAddBLMClient() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.remove_client_from_book", 5);
        Clicks.click("homepage.remove_client_from_book");
        Wait.untilElementPresent("homepage.add_new_client_ok");
        Clicks.click("homepage.add_new_client_ok");
        Wait.forPageReady();
    }

    @And("^I navigate to BLM Loyallist tab$")
    public void iNavigateToBLMLoyallistTab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.loyallist_tab");
        elementShouldBePresent("homepage.loyallist_tab");
        Clicks.click("homepage.loyallist_tab");
        Wait.untilElementPresent("homepage.top_left_membership_label");
        elementShouldBePresent("homepage.top_left_membership_label");
    }

    @Then("^the following information should be displayed on the Top Left Section of the loyalty page:$")
    public void theFollowingInformationShouldBeDisplayedOnTheTopLeftSectionOfTheLoyaltyPage(List<String> column) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.top_box_section");
        elementShouldBePresent("homepage.top_box_section");
        for (String columnSection : column) {
            switch (columnSection) {
                case "MEMBERSHIP":
                    Wait.untilElementPresent("homepage.top_left_membership_label");
                    Elements.elementShouldBePresent("homepage.top_left_membership_label");
                    break;
                case "ID#:":
                    Wait.untilElementPresent("homepage.top_left_loyallist_id");
                    Elements.elementShouldBePresent("homepage.top_left_loyallist_id");
                    break;
                case "Level:":
                    Wait.untilElementPresent("homepage.top_left_level_label");
                    Elements.elementShouldBePresent("homepage.top_left_level_label");
                    break;
                case "Spend to Upgrade: $":
                    Wait.untilElementPresent("homepage.top_left_spend_to_upgrade");
                    Elements.elementShouldBePresent("homepage.top_left_spend_to_upgrade");
                    break;
                case "Last Reward Earned:":
                    Wait.untilElementPresent("homepage.top_left_last_reward_earned");
                    Elements.elementShouldBePresent("homepage.top_left_last_reward_earned");
                    break;
                case "UNREDEEMED REWARDS CARDS:":
                    Wait.untilElementPresent("homepage.top_left_unredeemed_rewards");
                    Elements.elementShouldBePresent("homepage.top_left_unredeemed_rewards");
                    break;

                case "Rewards Card Data unavailable":
                    Wait.untilElementPresent("homepage.top_left_reward_card_data_unavail");
                    Elements.elementShouldBePresent("homepage.top_left_reward_card_data_unavail");
                    break;


                case "Star Money Data unavailable":
                    Wait.untilElementPresent("homepage.top_left_star_money_data_unavailable");
                    Elements.elementShouldBePresent("homepage.top_left_star_money_data_unavailable");
                    break;

                default:
                    fail("Section is missing");

            }
        }

    }

    @And("^the following information should be displayed on the Top Right Section of the loyalty page:$")
    public void theFollowingInformationShouldBeDisplayedOnTheTopRightSectionOfTheLoyaltyPage(List<String> column) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.top_box_section");
        elementShouldBePresent("homepage.top_box_section");
        for (String columnSection : column) {
            switch (columnSection) {
                case "POINTS SUMMARY":
                    Wait.untilElementPresent("homepage.top_right_points_summary_label");
                    Elements.elementShouldBePresent("homepage.top_right_points_summary_label");
                    break;
                case "Lifetime Points Earned:":
                    Wait.untilElementPresent("homepage.top_right_lifetime_points_earned");
                    Elements.elementShouldBePresent("homepage.top_right_lifetime_points_earned");
                    break;
                case "Current Points:":
                    Wait.untilElementPresent("homepage.top_right_current_points");
                    Elements.elementShouldBePresent("homepage.top_right_current_points");
                    break;
                case "Pending Points:":
                    Wait.untilElementPresent("homepage.top_right_pending_points");
                    Elements.elementShouldBePresent("homepage.top_right_pending_points");
                    break;
                case "Deferred Points:":
                    Wait.untilElementPresent("homepage.top_right_deferred_points");
                    Elements.elementShouldBePresent("homepage.top_right_deferred_points");
                    break;
                case "Points Needed to Next Reward Card:":
                    Wait.untilElementPresent("homepage.top_right_points_needed_to_next_reward_card");
                    Elements.elementShouldBePresent("homepage.top_right_points_needed_to_next_reward_card");
                    break;

                case "Points to Next Reward:":
                    Wait.untilElementPresent("homepage.top_right_points_to_next_rewards");
                    Elements.elementShouldBePresent("homepage.top_right_points_to_next_rewards");
                    break;

                default:
                    fail("Section is missing");
            }
        }
    }

    @And("^I will validate that the Offers are displayed with the nearest expiration date first$")
    public void iWillValidateThatTheOffersAreDisplayedWithTheNearestExpirationDateFirst() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        List<WebElement> elements = findElements("homepage.dates_list_loyallist_offers_table");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        assertTrue(elements.size() > 0);
        elements.remove(0);
        List<Long> dates = Lists.newArrayList();
        for (WebElement webElement : elements) {
            long time = simpleDateFormat.parse(webElement.getText()).getTime();
            dates.add(time);
        }

        List<Long> newDates = Lists.newArrayList(dates);
        Collections.sort(newDates);
        assertEquals(dates, newDates);

        /*
        String date1 = findElement("homepage.first_expire_date_loyallist_ofers_table").getText();
        System.out.println(date1);
        String date2 = findElement("homepage.second_expire_date_loyallist_ofers_table").getText();
        System.out.println(date2);

        Date date1Parse1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
        Date date1Parse2 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);

        date1Parse1.compareTo(date1Parse2);
        if(date1Parse2.after(date1Parse1)){
            assertTrue("Offers are displayed with the nearest expiration date first",true);
        }
        else {
            Assert.fail("Offers are NOT displayed with the nearest expiration date first");
        }
        */
    }

    @Then("^the following information should be displayed on the Bottom Section of the loyalty page:$")
    public void theFollowingInformationShouldBeDisplayedOnTheBottomSectionOfTheLoyaltyPage(List<String> columnBottom) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.main_container_loyallist_page");
        elementShouldBePresent("homepage.main_container_loyallist_page");
        for (String columnSection : columnBottom) {
            switch (columnSection) {
                case "OFFERS ()":
                    Wait.untilElementPresent("homepage.loyallist_table_offers_title_bottom");
                    Elements.elementShouldBePresent("homepage.loyallist_table_offers_title_bottom");
                    String offers = findElement("homepage.loyallist_table_offers_title_bottom").getText();
                    System.out.println(offers);
                    Assert.assertTrue(offers.contains("OFFERS"));
                    break;
                case "EFFECTIVE":
                    Wait.untilElementPresent("homepage.loyallist_table_effective_column_title");
                    Elements.elementShouldBePresent("homepage.loyallist_table_effective_column_title");
                    String effective = findElement("homepage.loyallist_table_effective_column_title").getText();
                    System.out.println(effective);
                    Assert.assertTrue(effective.contains("EFFECTIVE"));
                    break;
                case "EXPIRES":
                    Wait.untilElementPresent("homepage.loyallist_table_expires_column_title");
                    Elements.elementShouldBePresent("homepage.loyallist_table_expires_column_title");
                    String expires = findElement("homepage.loyallist_table_expires_column_title").getText();
                    System.out.println(expires);
                    Assert.assertTrue(expires.contains("EXPIRES"));
                    break;
                case "TRIPLE POINTS":
                    Wait.untilElementPresent("homepage.loyallist_table_triple_points_title_bottom");
                    Elements.elementShouldBePresent("homepage.loyallist_table_triple_points_title_bottom");
                    String tripPoints = findElement("homepage.loyallist_table_triple_points_title_bottom").getText();
                    System.out.println(tripPoints);
                    Assert.assertTrue(tripPoints.contains("TRIPLE POINTS DAYS"));
                    break;
                default:
                    fail("Section is missing");
            }
        }
    }

    @And("^I check the My Lists count$")
    public void iCheckTheMyListsCount() throws Throwable {
        Wait.untilElementPresent("homepage.blm_list_count_homepage");
        elementShouldBePresent("homepage.blm_list_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.blm_list_count_homepage"), " ");
        countToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]);
        System.out.println(countToDos);
    }

    @Then("^I will validate that the My Lists count incremented by 1")
    public void iWillValidateThatTheMyListsCountIncrementedBy() throws Throwable {
        Wait.untilElementPresent("homepage.blm_list_count_homepage");
        elementShouldBePresent("homepage.blm_list_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.blm_list_count_homepage"), " ");
        int newcountToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]); //grab the element
        Assert.assertEquals(countToDos + 1, newcountToDos);
        System.out.println(newcountToDos);
    }

    @And("^I wait to the List to be successfully processed$")
    public void iWaitToTheListToBeSuccessfullyProcessed() throws Throwable {
        String Url = WebDriverManager.getWebDriver().getCurrentUrl();
        System.out.println("Current URL is: " + Url);
        Navigate.browserRefresh();
        Wait.untilElementNotPresent("homepage.loading_page");
        Boolean inProgress = true;
        while (inProgress) {
            try {
                Wait.untilElementPresent("homepage.list_content_lists_tab");
                Wait.secondsUntilElementPresent("homepage.last_todo_in_list_blm", 10);
                WebElement toDoInProg = findElement("homepage.blm_list_in_progress");
                if (toDoInProg.isDisplayed()) {
                    Thread.sleep(2000);
                    Navigate.browserRefresh();
                    Wait.untilElementNotPresent("homepage.loading_page");
                }
            } catch (Exception e) {
                inProgress = false;
                Wait.untilElementNotPresent("homepage.loading_page");
            }
        }
    }

    @And("^I click on the searched phone number from the customers results list$")
    public void iClickOnTheSearchedPhoneNumberFromTheCustomersResultsList() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.blm_first_phone_number_search_results");
        Clicks.click("homepage.blm_first_phone_number_search_results");
    }

    @Then("^I will validate that the My Lists count decreased by 1$")
    public void iWillValidateThatTheMyListsCountDecreasedBy() throws Throwable {
        Wait.untilElementPresent("homepage.my_macys_to_dos_count_homepage");
        elementShouldBePresent("homepage.my_macys_to_dos_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.my_macys_to_dos_count_homepage"), " ");
        int newcountToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]); //grab the element
        Assert.assertEquals(countToDos - 1, newcountToDos);
        System.out.println(newcountToDos);
    }

    @Then("^I mark all TO DOs as completed LISTS tab BLM$")
    public void iMarkAllTODOsAsCompletedLISTSTabBLM() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.status_dropdown_lists_tab");
        try {
            while (findElement("homepage.status_dropdown_lists_tab").isDisplayed()) {
                DropDowns.selectByText("homepage.status_dropdown_lists_tab", "PHONE COMPLETE");
                Wait.untilElementNotPresent("homepage.loading_page");
                Clicks.click("homepage.lists_tab_save_button");
                Wait.untilElementNotPresent("homepage.loading_page");
                Navigate.browserRefresh();
                Wait.untilElementNotPresent("homepage.loading_page");
            }
        } catch (Exception e) {
            System.out.println("All TODOs are marked as completed");
        }
    }

    @And("^I click cancel on the remove from book popup$")
    public void iClickCancelOnTheRemoveFromBookPopup() throws Throwable {
        elementShouldBePresent("homepage.remove_from_book_cancel");
        Clicks.click("homepage.remove_from_book_cancel");

    }

    @When("^I click on TRIPLE POINTS link from the BLM Loyallist tab$")
    public void iClickOnTRIPLEPOINTSLinkFromTheBLMLoyallistTab() throws Throwable {
        Wait.untilElementPresent("homepage.loyallist_table_triple_points_title_bottom");
        Clicks.click("homepage.loyallist_table_triple_points_title_bottom");
    }

    @Then("^the following information should be displayed on the TRIPLE POINTS Section:$")
    public void theFollowingInformationShouldBeDisplayedOnTheTRIPLEPOINTSSection(List<String> triplePoints) throws Throwable {
        Thread.sleep(1000);
        for (String aTriplePoint : triplePoints) {
            switch (aTriplePoint.toUpperCase()) {
                case "ELIGIBLE DAYS REMAINING":
                    Wait.untilElementPresent("homepage.loyallist_table_tp_eliglible_days");
                    Elements.elementShouldBePresent("homepage.loyallist_table_tp_eliglible_days");
                    break;
                case "SCHEDULED DAYS":
                    Wait.untilElementPresent("homepage.loyallist_table_tp_scheduled_days");
                    Elements.elementShouldBePresent("homepage.loyallist_table_tp_scheduled_days");
                    break;
                case "USED DAYS":
                    Wait.untilElementPresent("homepage.loyallist_table_tp_used_days");
                    Elements.elementShouldBePresent("homepage.loyallist_table_tp_used_days");
                    break;
                default:
                    fail("Section is missing");
            }
        }
    }

    @When("^I click on OFFERS link from the BLM Loyallist tab$")
    public void iClickOnOFFERSLinkFromTheBLMLoyallistTab() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.loyallist_table_offers_link", 2);
        Clicks.click("homepage.loyallist_table_offers_link");
    }

    @Then("^the BLM homepage is displayed website$")
    public void theBLMHomepageIsDisplayedWebsite() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.associate_homepage");
        elementShouldBePresent("homepage.associate_homepage");
    }

    @Then("^I should be on the BLM homepage$")
    public void iShouldBeOnTheBLMHomepage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.homepage_tab");
    }

    @And("^I navigate on Create List tab$")
    public void iNavigateOnCreateListTab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.create_list_tab");
        Clicks.click("homepage.create_list_tab");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^only Credit Cards with Shopping Passes will display on Wallet Screen BLM$")
    public void onlyCreditCardsWithShoppingPassesWillDisplayOnWalletScreenBLM() throws Throwable {
        Wait.untilElementPresent("my_clients.shopping_pass_cards_wallet");
        Elements.elementShouldBePresent("my_clients.shopping_pass_cards_wallet");
    }

    @Then("^LISTS section is displayed$")
    public void listsSectionIsDisplayed() throws Throwable {
        Wait.forPageReady();
        elementShouldBePresent("homepage.list_title_header_blm");
        String message = Elements.findElement("homepage.list_title_header_blm").getText().trim();
        assertEquals("LIST TITLE", message);
        elementShouldBePresent("homepage.uncalled_clients_header_blm");
        String message1 = Elements.findElement("homepage.uncalled_clients_header_blm").getText().trim();
        assertEquals("# UNCALLED CUSTOMERS", message1);
        elementShouldBePresent("homepage.due_by_header_blm");
        String message2 = Elements.findElement("homepage.due_by_header_blm").getText().trim();
        assertEquals("DUE BY", message2);
    }

    @And("^the List \"([^\"]*)\" is displayed on the LISTS tab$")
    public void theListIsDisplayedOnTheLISTSTab(String listTitle) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
//        THIS IS A TEMPORARY WORKAROUND TO REFRESH THE BROWSER, LIST SHOULD APPEAR WITHOUT REFRESH
        Navigate.browserRefresh();
        OmniclientUtils.waitForAngularLoad();
        WebElement list = findElement(By.xpath("//span[contains(text(), '" + listTitle + "')]"));
        Elements.elementShouldBePresent(list);
    }

    @And("^the customer count will display on the My Top of List Customers Create List dashboard$")
    public void theCustomerCountWillDisplayOnTheMyTopOfListCustomersCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement topListCount = findElement(By.id("count" + topListPlatinumPosition));
        elementShouldBePresent(topListCount);
        String countNumber = topListCount.getText().trim();
        Matcher matcher = Pattern.compile("\\(([0-9]+)\\)").matcher(countNumber);
        if (matcher.find()) {
            assertTrue("Customer count is displayed", true);
        } else {
            Assert.fail("No customer count is displayed");
        }
    }

    @And("^I should see the title My Top of List Customers on the Create List dashboard$")
    public void iShouldSeeTheTitleMyTopOfListCustomersOnTheCreateListDashboard() throws Throwable {
        WebElement topList = findElement(By.id("description" + topListPlatinumPosition));
        elementShouldBePresent(topList);
        String topListTitle = topList.getText();
        System.out.println(topListTitle);
        assertTrue(topListTitle.contains("My Top of List Customers"));
    }

    @When("^I select the title My Top of List Customers on the Create List dashboard$")
    public void iSelectTheTitleMyTopOfListCustomersOnTheCreateListDashboard() throws Throwable {
        WebElement topList = findElement(By.id("description" + topListPlatinumPosition));
        elementShouldBePresent(topList);
        Clicks.click(topList);
    }

    @When("^navigate to MY CUSTOMERS page$")
    public void navigateToMYCUSTOMERSPage() throws Throwable {
        elementShouldBePresent("homepage.mycustomers_link");
        Clicks.click("homepage.mycustomers_link");
    }

    @When("^I click on the the clients name \"([^\"]*)\" from the MY CUSTOMERS List$")
    public void iClickOnTheTheClientsNameFromTheMYCUSTOMERSList(String name) throws Throwable {
        hoverForSelection("homepage.test_test_account");
        WebElement clientName = findElement(By.linkText(name));
        Clicks.click(clientName);
        Thread.sleep(5000);
    }

    @When("^I click on the BLM preferred method of contact$")
    public void iClickOnTheBLMPreferredMethodOfContact() throws Throwable {
        Elements.elementShouldBePresent("homepage.preferred_contact_method");
        Clicks.click("homepage.preferred_contact_method");
    }
}
