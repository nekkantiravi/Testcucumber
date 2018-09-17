package com.macys.sdt.projects.Stores.Omniclient.steps.website.mcom;


import com.google.common.collect.Lists;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Omniclient.utils.OmniclientUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.macys.sdt.framework.interactions.Clicks.hoverForSelection;
import static com.macys.sdt.framework.interactions.Elements.*;
import static org.junit.Assert.*;

public class omniclientsteps extends StepUtils {

    private static final Pattern STARTS_WITH_NUMBER = Pattern.compile("([0-9]*) .*");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private Integer countToDos;
    private Integer countLists;
    private Integer countMemos;

    private String customerPosition = "0";
    private String upcomingBirthPosition = "1";
    private String newCreditAccPosition = "2";
    private String topClientsPosition = "3";
    private String topListPlatinumPosition = "4";

    private Integer countCustomersList;

    private String firstClientHomepage;
    private static final String DISREGARD_CHANGES = "Disregard Changes?\n" +
            "Are you sure you want to navigate away from this page?\n" +
            "Your changes have not been saved.\n" +
            "If you leave this page without saving, all changes will be lost.\n" +
            "Press OK to continue, or Cancel to stay on the current page.";

    //TODO: SPLIT THE STEP DEFINITION CLASS INTO MORE CLASSES BASED ON RESPONSIBILITY
    //TODO: SPLIT JSON ELEMENTS INTO SEPARATE PAGES (MOSTLY ARE IN HOMEPAGE.JSON NOW)

    @Given("^I launch the macy's omniclient page$")
    public void i_launch_the_macy_s_omniclient_page() throws Throwable {
        Navigate.visit("ocl");
        Wait.forPageReady();
//        shouldBeOnPage("ocl");
    }


    @Then("^I should be able to view the myClient logo$")
    public void i_should_be_able_to_view_the_myClient_logo() throws Throwable {
        elementShouldBePresent("ocl.omniclient_logo");
    }

    @When("^I sign into Omniclient application$")
    public void i_sign_into_Omniclient_application() throws Throwable {
        TextBoxes.typeTextbox("ocl.associateID_textbox", "10000051");
        TextBoxes.typeTextbox("ocl.password_textbox", "Temp$Pass1");
        Clicks.click("ocl.signin_button");
        Wait.untilElementPresent("ocl.homePage_link");
    }

    @Then("^I should see omniclient landing page$")
    public void i_should_see_omniclient_landing_page() throws Throwable {
        elementShouldBePresent("ocl.homePage_link");
    }

    @When("^I select the All To Dos tab$")
    public void i_select_the_All_To_Dos_tab() throws Throwable {
        Wait.untilElementPresent("ocl.alltodos_tab");
        Clicks.click("ocl.alltodos_tab");
        Wait.untilElementPresent("ocl.todoPage_link");
    }

    @Then("^I should see the To Dos screen$")
    public void i_should_see_the_To_Dos_screen() throws Throwable {
        elementShouldBePresent("ocl.todoPage_link");

    }

    @Then("^I select the My Macys To Dos link$")
    public void i_select_the_My_Macys_To_Dos_link() throws Throwable {
        elementShouldBePresent("ocl.mymacystodos_link");
        Clicks.click("ocl.mymacystodos_link");
    }

    @When("^I enter \"([^\"]*)\" in username field of Omniclient login page$")
    public void i_enter_in_username_field(String associateID) throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", associateID);
    }

    @When("^I enter \"([^\"]*)\" in password field of Omniclient login page$")
    public void i_enter_in_password_field(String password) throws Throwable {
        TextBoxes.typeTextbox("logon_pg.password_textbox", password);
    }

    @Then("^I click Sign In button of Omniclient login page$")
    public void i_click_Sign_In_button() throws Throwable {
        Clicks.click("logon_pg.signin_button");
        Wait.untilElementPresent("homepage.homePage_link");
        Thread.sleep(5000);
    }

    @Then("^I should be logged in \"([^\"]*)\" and see the omniclient landing page$")
    public void i_should_be_logged_in_something_and_see_the_omniclient_landing_page(String role) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        switch (role.toLowerCase().trim()) {
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
                Wait.untilElementPresent("homepage.createevent_link");
                elementShouldBePresent("homepage.createevent_link");
                break;
            case "corp store exec":
                Wait.untilElementPresent("homepage.homePage_link");
                elementShouldBePresent("homepage.homePage_link");
                break;
            case "district manager":
                Wait.untilElementPresent("homepage.my_stores_tab_district_manager");
                elementShouldBePresent("homepage.my_stores_tab_district_manager");
                break;
            default:
                fail("Incorrect Role");
        }
    }

    //TODO: REMOVE THE 8000 MILLIS SLEEP FROM ALL LOGIN METHODS - FIND A BETTER APPROACH

    @When("^I sign into Omniclient application as associate$")
    public void i_sign_into_Omniclient_application_as_associate() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000051");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass1");
        Clicks.click("logon_pg.signin_button");
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.associate_homepage", 10);
        elementShouldBePresent("homepage.associate_homepage");
    }

    @When("^I sign into Omniclient application as corporate admin$")
    public void i_sign_into_Omniclient_application_as_corporate_admin() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000069");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass19");
        Clicks.click("logon_pg.signin_button");
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.createevent_link", 10);
        elementShouldBePresent("homepage.createevent_link");
    }

    @When("^I sign into Omniclient application as general manager$")
    public void i_sign_into_Omniclient_application_as_general_manager() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000059");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass9");
        Clicks.click("logon_pg.signin_button");
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.general_manager_homepage", 10);
        elementShouldBePresent("homepage.general_manager_homepage");
    }

    @When("^I sign into Omniclient application as corporate store executive$")
    public void i_sign_into_Omniclient_application_as_corporate_store_executive() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000117");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass117");
        Clicks.click("logon_pg.signin_button");
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.homePage_link");
        Wait.untilElementNotPresent("homepage.loading_page");

    }


    @When("^I click on the Search Radio button$")
    public void i_click_on_the_Search_Radio_button() throws Throwable {
        Clicks.click("homepage.firstnamezip_radiobutton");
        Wait.untilElementPresent("homepage.firstName_textbox");

    }

    @When("^I enter \"([^\"]*)\" in the \"(phone|first name|last name|zip code)\" textbox$")
    public void i_enter_in_the_textbox(String value, String field) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        switch (field) {
            case "phone":
                TextBoxes.typeTextbox("homepage.telephone_textbox", value);
                break;
            case "first name":
                TextBoxes.typeTextbox("homepage.firstName_textbox", value);
                break;
            case "last name":
                TextBoxes.typeTextbox("homepage.lastName_textbox", value);
                break;
            default:
                TextBoxes.typeTextbox("homepage.zip_textbox", value);
                break;
        }
    }


    @When("^I select the Search button in Omniclient home screen$")
    public void i_select_the_Search_button() throws Throwable {
        Wait.untilElementPresent("homepage.search_button");
        Clicks.click("homepage.search_button");
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.searchresults_header");
    }

    @Then("^the No Search results screen is displayed in Omniclient search results page$")
    public void the_No_Search_results_screen_is_displayed() throws Throwable {
        String message = Elements.findElement("homepage.noresults_verbiage").getText();
        System.out.println(message);
        assertEquals("Your search found no results for...", message);
    }

    @When("^I sign into Omniclient application as Selling Manager$")
    public void i_sign_into_omniclient_application_as_selling_manager() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000057");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass7");
        Clicks.click("logon_pg.signin_button");
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.sales_manager_homepage", 20);
        elementShouldBePresent("homepage.sales_manager_homepage");
    }

    // *********************************************************************************************************

    @When("^I sign into Omniclient application as District Manager$")
    public void i_sign_into_omniclient_application_as_district_manager() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000061");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass11");
        Clicks.click("logon_pg.signin_button");
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.district_section");
        elementShouldBePresent("homepage.district_section");
    }

    @Then("^My Store tab is displayed in dashboard section$")
    public void my_store_tab_is_displayed_in_dashboard_section() throws Throwable {
        elementShouldBePresent("homepage.my_store_tab");
        String message = Elements.findElement("homepage.my_store_xpath").getText();
        assertEquals("My Store", message);
    }

    @And("^My Customers tab is displayed in dashboard section$")
    public void my_customers_tab_is_displayed_in_dashboard_section() throws Throwable {
        elementShouldBePresent("homepage.my_customers_tab");
        String message = Elements.findElement("homepage.my_customers_xpath").getText();
        assertEquals("My Customers", message);
    }

    @Then("^I should see the name of the district$")
    public void i_should_see_the_name_of_the_district() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        elementShouldBePresent("homepage.district_section");
        String message = Elements.findElement("homepage.district_title").getText();
        assertEquals("District:", message);
    }

    @And("^I should see the store names displayed in dashboard$")
    public void i_should_see_the_store_names_displayed_in_dashboard() throws Throwable {
        elementShouldBePresent("homepage.store_table_title");
        String message = Elements.findElement("homepage.store_table_title").getText();
        assertEquals("Store:", message);
        elementShouldBePresent("homepage.stores_list");
    }

    @And("^I should see the list of stores displayed in numerical order$")
    public void i_should_see_the_list_of_stores_displayed_in_numerical_order() throws Throwable {
        elementShouldBePresent("homepage.stores_list");
        List<WebElement> elements = findElements("homepage.store_list_items");
        List<Integer> storeNumbers = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText();
            Matcher matcher = STARTS_WITH_NUMBER.matcher(text.trim());
            if (matcher.find()) {
                String group = matcher.group(1);
                storeNumbers.add(Integer.parseInt(group));
            }
        }
        ArrayList<Integer> sortedStoreNumbers = new ArrayList<>(storeNumbers);
        Collections.sort(sortedStoreNumbers);
        assertEquals(sortedStoreNumbers, storeNumbers);
    }

    @And("^I should see a store number next to each store$")
    public void i_should_see_a_store_number_next_to_each_store() throws Throwable {
        elementShouldBePresent("homepage.stores_list");
        List<WebElement> elements = findElements("homepage.store_list_items");
        for (WebElement element : elements) {
            String text = element.getText().trim();
            Matcher matcher = STARTS_WITH_NUMBER.matcher(text);
            if (!matcher.find()) {
                if (!text.equals("")) {
                    fail("Cannot find number in front of the store");
                }
            }
        }
    }

    @When("^I click ok button on the error message$")
    public void i_click_ok_button_on_the_error_message() throws Throwable {
        Wait.untilElementPresent("homepage.ok_button_feat_not_available");
        Clicks.click("homepage.ok_button_feat_not_available");
        Wait.untilElementPresent("homepage.homePage_link");
        elementShouldBePresent("homepage.homePage_link");
    }

    @Then("^feature not available error is displayed$")
    public void feature_not_available_error_is_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.popup_error_not_available");
        elementShouldBePresent("homepage.popup_error_not_available");
        String message = findElement("homepage.popup_error_not_available").getText().trim();
        Assert.assertEquals(message, "This feature is not available for the District Manager role.");
    }

    @And("^I click on My Clients from top navigation bar$")
    public void i_click_on_my_clients_from_top_navigation_bar() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.my_clients_navigation_bar", 10);
        Clicks.hoverForSelection("homepage.my_clients_navigation_bar");
        Clicks.click("homepage.my_clients_navigation_bar");
        Wait.untilElementNotPresent("homepage.loading_page");

    }

    @When("^I click on Create To Dos from navigation bar$")
    public void i_click_on_create_to_dos_from_navigation_bar() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_navigation_bar");
        Clicks.click("homepage.create_to_dos_navigation_bar");
        Wait.forPageReady();
    }

    // *********************************************************************************************************

    @When("^I click on the Switch User button$")
    public void i_click_on_the_switch_user_button() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.switch_drop");
        elementShouldBePresent("homepage.switch_drop");
        Clicks.hoverForSelection("homepage.switch_drop");
        Thread.sleep(1000);
        Clicks.hoverForSelection("homepage.switch_button");
        Wait.untilElementPresent("homepage.switch_button");
        Clicks.click("homepage.switch_button");
        Wait.forPageReady();
    }

    @Then("^I should see the Switch User search popup$")
    public void i_should_see_the_switch_user_search_popup() throws Throwable {
        Wait.untilElementPresent("homepage.switch_popup");
        elementShouldBePresent("homepage.switch_popup");
    }

    @When("^I select search by \"([^\"]*)\"$")
    public void i_select_search_by_something(String searchoption) throws Throwable {
        switch (searchoption) {
            case "name":
                Clicks.click("homepage.search_name");
                break;
            case "id":
                Clicks.click("homepage.search_id");
                break;
            default:
                fail("Search option not ID or NAME");
        }
    }

    @And("^I enter \"([^\"]*)\" credentials$")
    public void i_enter_something_credentials(String partial) throws Throwable {
        if (MEW()) {
            Wait.untilElementPresent("dashboard_page.switch_user_input_box");
            TextBoxes.typeTextbox("dashboard_page.switch_user_input_box", partial);
            Thread.sleep(1000);
        } else {
            Wait.untilElementPresent("homepage.switch_text_box");
            TextBoxes.typeTextbox("homepage.switch_text_box", partial);
            Thread.sleep(1000);
        }
    }

    @Then("^I should see a list with \"([^\"]*)\" credentials in the dropdown$")
    public void i_should_see_a_list_with_something_credentials_in_the_dropdown(String full) throws Throwable {
        if (MEW()) {
            elementShouldBePresent("dashboard_page.switch_user_list");
        } else {
            WebElement el = Elements.findElement(By.partialLinkText(full));
            elementShouldBePresent(el);
        }
    }

    @When("^I select the \"([^\"]*)\" credentials from the dropdown$")
    public void i_select_the_something_from_the_dropdown(String full) throws Throwable {
        if (MEW()) {
            Wait.untilElementPresent("dashboard_page.switch_user_list");
            elementShouldBePresent("dashboard_page.switch_user_list");
            Clicks.click("dashboard_page.switch_user_list");
            Wait.forPageReady();
        } else {
            Thread.sleep(2000);
            Wait.secondsUntilElementPresent(By.partialLinkText(full), 60);
            WebElement el = Elements.findElement(By.partialLinkText(full));
            Clicks.hoverForSelection(el);
            Clicks.click(el);
            Wait.forPageReady();
        }
    }

    @And("^click the Switch button$")
    public void click_the_switch_button() throws Throwable {
        if (MEW()) {
            Thread.sleep(2000);
            Wait.secondsUntilElementPresent("dashboard_page.switch_button", 30);
            Clicks.hoverForSelection("dashboard_page.switch_button");
            Clicks.click("dashboard_page.switch_button");
            Wait.untilElementNotPresent("dashboard_page.loading_page");
            Thread.sleep(1000);
            Wait.untilElementNotPresent("dashboard_page.loading_page");
        } else {
            Wait.untilElementPresent("homepage.confirm_button");
            Clicks.click("homepage.confirm_button");
            Wait.forPageReady();
            Thread.sleep(5000);
        }
    }

    @Then("^I should be switched into the selected \"([^\"]*)\"$")
    public void i_should_be_switched_into_the_selected_something(String role) throws Throwable {
        switch (role.toLowerCase()) {
            case "associate":
                if (MEW()) {
                    Wait.forPageReady();
                    Wait.untilElementPresent("dashboard_page.associate_homepage");
                    Elements.elementShouldBePresent("dashboard_page.associate_homepage");
                    break;

                } else {
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.associate_homepage");
                    Elements.elementShouldBePresent("homepage.associate_homepage");
                }
                break;
            case "sales manager":
                if (MEW()) {
                    Wait.forPageReady();
                    Wait.untilElementPresent("dashboard_page.sales_manager_homepage");
                    Elements.elementShouldBePresent("dashboard_page.sales_manager_homepage");
                    break;

                } else {
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.sales_manager_homepage");
                    Elements.elementShouldBePresent("homepage.sales_manager_homepage");
                }
                break;
            case "sales manager myshop off":
                Wait.forPageReady();
                Wait.untilElementNotPresent("homepage.loading_page");
                Wait.untilElementPresent("homepage.sales_manager_myshopoff");
                Elements.elementShouldBePresent("homepage.sales_manager_myshopoff");
                break;
            case "general manager":
                Wait.forPageReady();
                Wait.untilElementNotPresent("homepage.loading_page");
                Wait.untilElementPresent("homepage.general_manager_homepage");
                Elements.elementShouldBePresent("homepage.general_manager_homepage");
                break;
            case "corp admin":
                Wait.forPageReady();
                Wait.untilElementNotPresent("homepage.loading_page");
                Wait.untilElementPresent("homepage.createevent_link");
                Elements.elementShouldBePresent("homepage.createevent_link");
                break;
            case "corp store exec":
                Wait.forPageReady();
                Wait.untilElementNotPresent("homepage.loading_page");
                Wait.untilElementPresent("homepage.myclients_link");
                Clicks.click("homepage.myclients_link");
                Wait.untilElementPresent("homepage.attention_header");
                elementShouldBePresent("homepage.attention_header");
                Clicks.click("homepage.yes_button_logout_popup");
                Thread.sleep(1000);
                break;
            case "district manager":
                Wait.forPageReady();
                Wait.untilElementNotPresent("homepage.loading_page");
                Wait.untilElementPresent("homepage.district_text");
                Elements.elementShouldBePresent("homepage.district_text");
                break;
            default:
                fail("Role does not match a valid one");
        }
    }


    @And("^I expand a Store$")
    public void i_expand_a_store() throws Throwable {
        Wait.untilElementPresent("homepage.exp_store");
        Clicks.click("homepage.exp_store");
        Wait.forPageReady();
    }

    @Then("^I should see the assigned General Manager$")
    public void i_should_see_the_assigned_general_manager() throws Throwable {
        Wait.untilElementPresent("homepage.dm_container");
        elementShouldBePresent("homepage.dm_container");
    }

    @When("^I click on the General Manager$")
    public void i_click_on_the_general_manager() throws Throwable {
        elementShouldBePresent("homepage.dm_link");
        Clicks.click("homepage.dm_link");
        Wait.forPageReady();
    }

    // *********************************************************************************************************

    @And("^the logged in sales manager name is displayed on top of dashboard section$")
    public void the_logged_in_sales_manager_name_is_displayed_on_top_of_dashboard_section() throws Throwable {
        Wait.untilElementPresent("homepage.name_on_dash");
        Elements.elementShouldBePresent("homepage.name_on_dash");
        String nameDrop = Elements.findElement("homepage.name_on_dropdown").getText().trim();
        String nameDash = Elements.findElement("homepage.name_on_dash").getText().trim();
        Assert.assertEquals(nameDrop, nameDash);
    }

    @Then("^I should see a list of Selling managers in my store$")
    public void i_should_see_a_list_of_selling_managers_in_my_store() throws Throwable {
        if (MEW()) {
            Wait.untilElementPresent("dashboard_page.sales_manager_page");
            Elements.elementShouldBePresent("dashboard_page.sales_manager_page");
        } else {
            Wait.untilElementNotPresent("homepage.loading_page");
            Wait.secondsUntilElementPresent("homepage.sales_manager_list", 30);
            Elements.elementShouldBePresent("homepage.sales_manager_list");
        }
    }

    @Then("^I click the plus sign next to a Selling Manager name$")
    public void i_click_the_plus_sign_next_to_a_selling_manager_name() throws Throwable {
        Thread.sleep(3000);
        elementShouldBePresent("homepage.collapse_staffing_zones");
        Clicks.click("homepage.collapse_staffing_zones");
        Wait.forPageReady();
        Clicks.click("homepage.expand_staffing_zones");
        Wait.forPageReady();

    }

    @Then("^I should see a list of staffing zones of the Selling Manager$")
    public void i_should_see_a_list_of_staffing_zones_of_the_selling_manager() throws Throwable {
        if (MEW()) {
            Wait.forPageReady();
            elementShouldBePresent("dashboard_page.first_selling_area_of_logged_on_sm");
            Assert.assertTrue(elementPresent("dashboard_page.logged_on_sm_selling_areas"));
        } else {
            Wait.forPageReady();
            elementShouldBePresent("homepage.expand_staffing_zones");
            Assert.assertTrue(elementPresent("homepage.staffing_zones_list"));
        }
    }

    @And("^I should see my name at the top$")
    public void i_should_see_my_name_at_the_top() throws Throwable {
        if (MEW()) {
            Wait.untilElementPresent("dashboard_page.name_on_list");
            Elements.elementShouldBePresent("dashboard_page.name_on_list");
        } else {
            Wait.untilElementPresent("homepage.name_on_list");
            Elements.elementShouldBePresent("homepage.name_on_list");
            String nameDrop = Elements.findElement("homepage.name_on_dropdown").getText().trim();
            String nameList = Elements.findElement("homepage.name_on_list").getText().trim();
            Assert.assertEquals(nameDrop, nameList);
        }
    }

    @And("^the list of my selling areas is expanded under my name$")
    public void the_list_of_my_selling_areas_is_expanded_under_my_name() throws Throwable {
        Elements.elementShouldBePresent("homepage.selling_area1");
        Elements.elementShouldBePresent("homepage.selling_area2");
    }

    @And("^I click on the staffing zone$")
    public void i_click_on_the_staffing_zone() throws Throwable {
        if (MEW()) {
            Wait.untilElementPresent("dashboard_page.first_selling_area_of_logged_on_sm");
            Elements.elementShouldBePresent("dashboard_page.first_selling_area_of_logged_on_sm");
            Wait.untilElementPresent("dashboard_page.first_sm_selling_area_plus_sign");
            Clicks.click("dashboard_page.first_sm_selling_area_plus_sign");
            Wait.forPageReady();
        } else {
            Wait.untilElementPresent("homepage.selling_area1");
            Elements.elementShouldBePresent("homepage.selling_area1");
            Wait.untilElementPresent("homepage.selling_area_name");
            Clicks.click("homepage.selling_area_name");
            Wait.forPageReady();
        }
    }

    @Then("^I should see the assigned Selling Associate$")
    public void i_should_see_the_assigned_selling_associate() throws Throwable {
        Wait.untilElementPresent("homepage.first_associate_in_list");
        Elements.elementShouldBePresent("homepage.first_associate_in_list");
    }

    @When("^I click on the Selling Associate$")
    public void i_click_on_the_selling_associate() throws Throwable {
        Wait.untilElementPresent("homepage.first_associate_in_list");
        Clicks.click("homepage.first_associate_in_list");
        Wait.untilElementNotPresent("homepage.loading_page");

    }

    @Then("^My Stores tab is displayed in dashboard section$")
    public void my_Stores_tab_is_displayed_in_dashboard_section() throws Throwable {
        Wait.untilElementPresent("homepage.my_stores_tab_district_manager");
        Elements.elementShouldBePresent("homepage.my_stores_tab_district_manager");

    }

    @And("^My Direct Reports is displayed in dashboard section$")
    public void my_direct_reports_is_displayed_in_dashboard_section() throws Throwable {
        Wait.untilElementPresent("homepage.my_direct_rep_district_manager");
        Elements.elementShouldBePresent("homepage.my_direct_rep_district_manager");
    }

    @And("^I navigate to My Stores tab$")
    public void i_navigate_to_my_stores_tab() throws Throwable {
        Clicks.click("homepage.my_stores_tab_district_manager");
        Wait.forPageReady();
    }

    @And("^I navigate to My Direct Reports tab$")
    public void i_navigate_to_my_direct_reports_tab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_direct_rep_district_manager");
        Clicks.click("homepage.my_direct_rep_district_manager");
        Wait.forPageReady();
    }

    @Then("^I should see a list of associates who report to me$")
    public void i_should_see_a_list_of_associates_who_report_to_me() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_direct_rep_drilldown");
        elementShouldBePresent("homepage.my_direct_rep_drilldown");
        elementShouldBePresent("homepage.my_direct_rep_list");
    }

    @When("^I click on the GM from My Direct Reports tab$")
    public void i_click_on_the_associate_from_my_direct_reports_tab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_direct_rep_assoc");
        Clicks.click("homepage.my_direct_rep_assoc");
        Wait.forPageReady();
    }

    @And("^I navigate on Create To Dos tab$")
    public void i_navigate_on_Create_to_dos_tab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.create_to_do_tabs");
        Clicks.click("homepage.create_to_do_tabs");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^PROFILE tab is displayed in omniclient view$")
    public void profile_tab_is_displayed_in_omniclient_view() throws Throwable {
        Wait.untilElementPresent("homepage.profile_tab");
        elementShouldBePresent("homepage.profile_tab");
        String message = Elements.findElement("homepage.profile_tab").getText();
        assertEquals("PROFILE", message);

    }

    @And("^TRANSACTIONS tab is displayed in omniclient view$")
    public void transactions_tab_is_displayed_in_omniclient_view() throws Throwable {
        Wait.untilElementPresent("homepage.transactions_tab");
        elementShouldBePresent("homepage.transactions_tab");
        String message = Elements.findElement("homepage.transactions_tab").getText();
        assertEquals("TRANSACTIONS", message);
    }

    @And("^TARGET tab is displayed in omniclient view$")
    public void target_tab_is_displayed_in_omniclient_view() throws Throwable {
        Wait.untilElementPresent("homepage.target_tab");
        elementShouldBePresent("homepage.target_tab");
        String message = Elements.findElement("homepage.target_tab").getText();
        assertEquals("TARGET", message);
    }

    @When("^I navigate to TARGET tab$")
    public void i_navigate_to_target_tab() throws Throwable {
        Clicks.click("homepage.target_tab");
        Wait.untilElementPresent("homepage.target_section");
        elementShouldBePresent("homepage.target_section");

    }

    @Then("^PROFILE tab is displayed by default$")
    public void profile_tab_is_displayed_by_default() throws Throwable {
        Wait.untilElementPresent("homepage.profile_section");
        elementShouldBePresent("homepage.profile_section");
    }


    @And("^I select default Send To Do's to SAs that report to me radio button$")
    public void i_select_default_send_to_dos_to_sas_that_report_to_me_radio_button() throws Throwable {
        Clicks.click("homepage.radio_button_SAs_report_to_me");

    }

    @And("^I select default Send To Do's to SAs within my store corp store exec radio button$")
    public void i_select_default_send_to_dos_to_sas_within_my_store_corp_store_exec_radio_button() throws Throwable {
        Clicks.click("homepage.radio_button_SAs_report_to_me");

    }

    @And("^I select default Exclude International Customers radio button$")
    public void i_select_default_exclude_international_customers_radio_button() throws Throwable {
        Clicks.click("homepage.radio_button_excl_interant_custom");
    }

    @And("^I select Default to max 250 tasks per SA radio button$")
    public void i_select_default_to_max_250_tasks_per_sa_radio_button() throws Throwable {
        Clicks.click("homepage.radio_button_default_tasks");
    }

    @And("^I click on Continue button from TARGET tab page$")
    public void i_click_on_continue_button_from_target_tab_page() throws Throwable {
        Wait.forPageReady();
        elementShouldBePresent("homepage.continue_button_target_page");
        Clicks.click("homepage.continue_button_target_page");

    }

    @Then("^CREATE LIST SEND TO DOS section is displayed$")
    public void create_list_send_to_dos_section_is_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.create_list_send_toDo_section");
        elementShouldBePresent("homepage.create_list_send_toDo_section");
    }

    @When("^I enter \"([^\"]*)\" in the Title checkbox$")
    public void i_enter_something_in_the_title_checkbox(String title) throws Throwable {
        Wait.untilElementPresent("homepage.title_checkbox_create_list");
        TextBoxes.typeTextbox("homepage.title_checkbox_create_list", title);
    }

    @And("^I select valid Start Date in Create List Send to Dos view$")
    public void i_select_valid_start_date_in_Create_List_Send_to_Dos_view() throws Throwable {
        LocalDate date = LocalDate.now();
        TextBoxes.typeTextbox("homepage.start_date_field_create_list", DATE_TIME_FORMATTER.format(date));
    }

    @And("^I select valid End Date in Create List Send to Dos view$")
    public void i_select_valid_end_date_in_Create_List_Send_to_Dos_view() throws Throwable {
        LocalDate date = LocalDate.now().plusDays(1);
        TextBoxes.typeTextbox("homepage.end_date_field_create_list", DATE_TIME_FORMATTER.format(date));
    }

    @Then("^Attention popup is displayed in Create List Send to Dos view$")
    public void attention_popup_is_displayed_in_Create_List_Send_to_Dos_view() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.attention_popup_create_list");
        elementShouldBePresent("homepage.attention_popup_create_list");
        String message = findElement("homepage.attention_popup_message_create_list").getText().trim();
        Assert.assertEquals(message, "Generating To Dos for your list. This process will run in the background, please check back later.");
    }

    @And("^I add a description \"([^\"]*)\" in Create List Send to Dos view$")
    public void i_add_a_description_in_Create_List_Send_to_Dos_view(String desc) throws Throwable {
        Wait.untilElementPresent("homepage.description_field_create_list");
        Clicks.click("homepage.description_field_create_list");
        TextBoxes.typeTextbox("homepage.description_field_create_list", desc);
    }

    @And("^I click on CREATE MY LIST button$")
    public void i_click_on_create_my_list_button() throws Throwable {
        findElement("homepage.create_my_list_button");
        elementShouldBePresent("homepage.create_my_list_button");
        Clicks.click("homepage.create_my_list_button");
    }

    @When("^I click on OK button in omniclient attention popup$")
    public void i_click_on_ok_button_in_omniclient_attention_popup() throws Throwable {
        Thread.sleep(2000);
        findElement("homepage.ok_button_attention_popup_create_list");
        elementShouldBePresent("homepage.ok_button_attention_popup_create_list");
        Clicks.click("homepage.ok_button_attention_popup_create_list");
    }

    @When("^I click on the newly created My Macys To Do$")
    public void i_click_on_the_newly_created_my_macys_to_do() throws Throwable {
        Navigate.browserRefresh();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.last_toDo_in_list", 30);
        elementShouldBePresent("homepage.last_toDo_in_list");
        Clicks.click("homepage.last_toDo_in_list");

    }

    @Then("^MY MACYS TO DOS section is displayed$")
    public void my_macys_to_dos_section_is_displayed() throws Throwable {
        Wait.forPageReady();
        elementShouldBePresent("homepage.list_title_header");
        String message = Elements.findElement("homepage.list_title_header").getText().trim();
        assertEquals("LIST TITLE", message);
        elementShouldBePresent("homepage.uncalled_clients_header");
        String message1 = Elements.findElement("homepage.uncalled_clients_header").getText().trim();
        assertEquals("# UNCALLED CLIENTS", message1);
        elementShouldBePresent("homepage.due_by_header");
        String message2 = Elements.findElement("homepage.due_by_header").getText().trim();
        assertEquals("DUE BY", message2);

    }

    @Then("^EVENT INFORMATION page is displayed$")
    public void event_information_page_is_displayed() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.event_information_page");
        elementShouldBePresent("homepage.event_information_page");
        String message = Elements.findElement("homepage.event_information_page").getText().trim();
        assertEquals("EVENT INFORMATION", message);
    }


    @Then("^Send To Do's to SAs that report to me is checked by default$")
    public void send_to_dos_to_sas_that_report_to_me_is_checked_by_default() throws Throwable {
        elementShouldBePresent("homepage.sas_that_report_to_me_radio_button_default");

    }

    @And("^Exclude International Customers is checked by default$")
    public void exclude_international_customers_is_checked_by_default() throws Throwable {
        elementShouldBePresent("homepage.exclude_internat_cust_radio_button_default");
    }

    @And("^Default to max 250 tasks per SA is checked by default$")
    public void default_to_max_250_tasks_per_sa_is_checked_by_default() throws Throwable {
        elementShouldBePresent("homepage.default_to_max_250_radio_button_default");
    }

    @And("^I select Send To Do's to SAs within specific Divisions, MGMs, and or Departments radio button$")
    public void i_select_send_to_dos_to_sas_within_specific_divisions_mgms_and_or_departments_radio_button() throws Throwable {
        elementShouldBePresent("homepage.send_toDos_within_specific");
        Clicks.click("homepage.send_toDos_within_specific");
    }

    @Then("^Select SAs by section is displayed$")
    public void select_sas_by_section_is_displayed() throws Throwable {
        Thread.sleep(4000);
        elementShouldBePresent("homepage.select_SAs_by_section");
        String message = Elements.findElement("homepage.select_SAs_by_section").getText().trim();
        assertEquals("Select SAs by...", message);

    }


    @When("^I click on Select all from DIVISION list$")
    public void i_click_on_select_all_from_division_list() throws Throwable {
        Wait.forPageReady();
        elementShouldBePresent("homepage.select_all_division_section");
        Clicks.click("homepage.select_all_division_section");
        Thread.sleep(4000);
    }

    @When("^I click on Select all from MGM list$")
    public void i_click_on_select_all_from_mgm_list() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.select_all_mgm_section");
        elementShouldBePresent("homepage.select_all_mgm_section");
        Clicks.click("homepage.select_all_mgm_section");
        Thread.sleep(4000);
    }

    @When("^I click on Select all from DEPARTMENT list$")
    public void i_click_on_select_all_from_department_list() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.select_all_department_section");
        //Thread.sleep(4000);
        elementShouldBePresent("homepage.select_all_department_section");
        Clicks.click("homepage.select_all_department_section");
    }


    @Then("^MGM list is displayed$")
    public void mgm_list_is_displayed() throws Throwable {
        elementShouldBePresent("homepage.mgm_section");
        String message = Elements.findElement("homepage.mgm_section").getText().trim();
        assertEquals("MGM:", message);
    }

    @Then("^DEPARTMENT list is displayed$")
    public void department_list_is_displayed() throws Throwable {
        elementShouldBePresent("homepage.department_section");
        String message = Elements.findElement("homepage.department_section").getText().trim();
        assertEquals("DEPARTMENT:", message);
    }


    @And("^DIVISIONS list is displayed$")
    public void divisions_list_is_displayed() throws Throwable {
        elementShouldBePresent("homepage.divisions_section");
        String message = Elements.findElement("homepage.divisions_section").getText().trim();
        assertEquals("DIVISIONS:", message);

    }

    @Then("^I click on Continue button from Select SAs by section$")
    public void i_click_on_continue_button_from_select_sas_by_section() throws Throwable {
        elementShouldBePresent("homepage.continue_button_select_SAs_by_section");
        Clicks.click("homepage.continue_button_select_SAs_by_section");
    }

    @When("^I click on one division from DIVISION list$")
    public void i_click_on_one_division_from_division_list() throws Throwable {
        Wait.forPageReady();
        elementShouldBePresent("homepage.first_element_in_divisions_list");
        Clicks.click("homepage.first_element_in_divisions_list");

    }

    @When("^I click on one option from MGM list$")
    public void i_click_on_one_option_from_mgm_list() throws Throwable {
        Wait.forPageReady();
        //Thread.sleep(4000);
        Wait.untilElementPresent("homepage.first_element_in_mgm_list");
        elementShouldBePresent("homepage.first_element_in_mgm_list");
        Clicks.click("homepage.first_element_in_mgm_list");
    }

    @When("^I click on one option from DEPARTMENT list$")
    public void i_click_on_one_option_from_department_list() throws Throwable {
        Wait.forPageReady();
        //Thread.sleep(4000);
        Wait.untilElementPresent("homepage.first_element_in_department_list");
        elementShouldBePresent("homepage.first_element_in_department_list");
        Clicks.click("homepage.first_element_in_department_list");
    }

    @And("^I select International Customers Only radio button$")
    public void i_select_international_customers_only_radio_button() throws Throwable {
        elementShouldBePresent("homepage.radio_button_internat_custom");
        Clicks.click("homepage.radio_button_internat_custom");
    }

    @And("^I enter value in Limit Number of Customers in List field \"([^\"]*)\"$")
    public void i_enter_value_in_limit_number_of_customers_in_list_field(String limit) throws Throwable {
        elementShouldBePresent("homepage.limit_nb_of_customers_input_field");
        TextBoxes.typeTextbox("homepage.limit_nb_of_customers_input_field", limit);
    }

    @And("^I select All Customers Customers radio button$")
    public void i_select_all_customers_customers_radio_button() throws Throwable {
        Wait.forPageReady();
        elementShouldBePresent("homepage.radio_button_all_customers");
        Clicks.click("homepage.radio_button_all_customers");
    }

    @And("^I select Limit tasks delivered to each SA radio button$")
    public void i_select_limit_tasks_delivered_to_each_sa_radio_button() throws Throwable {
        elementShouldBePresent("homepage.radio_button_limit_tasks_delivered");
        Clicks.click("homepage.radio_button_limit_tasks_delivered");
    }

    @And("^I enter value in Limit tasks delivered to each SA field$")
    public void i_enter_value_in_limit_tasks_delivered_to_each_sa_field() throws Throwable {
        elementShouldBePresent("homepage.limit_tasks_delivered_input_field");
        TextBoxes.typeTextbox("homepage.limit_tasks_delivered_input_field", "10");
    }
    // *********************************************************************************************************

    @Then("^I should see the 'No Results Found' message$")
    public void i_should_see_the_no_results_found_message() throws Throwable {
        Wait.untilElementPresent("homepage.no_results");
        String norslt = Elements.findElement("homepage.no_results").getText().trim();
        String norslt_att = Elements.findElement("homepage.no_results").getAttribute("ng-show").trim();
        System.out.println("No Result text = " + norslt);
        System.out.println("No Result attribute = " + norslt_att);
        Assert.assertEquals("No Results Found", norslt);
        Assert.assertEquals("noResults", norslt_att);
    }

    @When("^I click on the Selling Manager$")
    public void i_click_on_the_selling_manager() throws Throwable {
        Wait.untilElementPresent("homepage.sm_link");
        Clicks.click("homepage.sm_link");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^I should see the Yesterday's Summary header$")
    public void i_should_see_the_yesterdays_summary_header() throws Throwable {
        Wait.untilElementPresent("homepage.yesterdays_list");
        elementShouldBePresent("homepage.yesterdays_header");
        String yesterdaysHeader = findElement("homepage.yesterdays_header").getText();
        if (yesterdaysHeader.contains("YESTERDAY'S SUMMARY")) {
            System.out.println("[INFO] Yesterday's Header is correctly populated: " + yesterdaysHeader);
        } else Assert.fail("[ERROR] Yesterday's Header is incorrect/empty: " + yesterdaysHeader);
    }

    @And("^I should see the Client Sales field$")
    public void i_should_see_the_client_sales_field() throws Throwable {
        Wait.untilElementPresent("homepage.yesterdays_list");
        elementShouldBePresent("homepage.client_sales_text");
        Assert.assertEquals("Client Sales", findElement("homepage.client_sales_text").getText().trim());
        String fieldValue = findElement("homepage.client_sales_value").getText().trim();
        if (fieldValue.isEmpty()) {
            Assert.fail("[ERROR] Client Sales field is not populated");
        }
    }

    @And("^I should see the Clients Shopped field$")
    public void i_should_see_the_clients_shopped_field() throws Throwable {
        Wait.untilElementPresent("homepage.yesterdays_list");
        elementShouldBePresent("homepage.clients_shopped_text");
        Assert.assertEquals("Clients Shopped", findElement("homepage.clients_shopped_text").getText().trim());
        String fieldValue = findElement("homepage.clients_shopped_value").getText().trim();
        if (fieldValue.isEmpty()) {
            Assert.fail("[ERROR] Clients Shopped field is not populated");
        }
    }

    @And("^I should see the Potentials to Clients field$")
    public void i_should_see_the_potentials_to_clients_field() throws Throwable {
        Wait.untilElementPresent("homepage.yesterdays_list");
        elementShouldBePresent("homepage.potential_clients_text");
        Assert.assertEquals("Potentials to Clients", findElement("homepage.potential_clients_text").getText().trim());
        String fieldValue = findElement("homepage.potential_clients_value").getText().trim();
        if (fieldValue.isEmpty()) {
            Assert.fail("[ERROR] Potentials to Clients field is not populated");
        }
    }

    @And("^I should see the New to Book field$")
    public void i_should_see_the_new_to_book_field() throws Throwable {
        Wait.untilElementPresent("homepage.yesterdays_list");
        elementShouldBePresent("homepage.new_book_text");
        Assert.assertEquals("New to Book", findElement("homepage.new_book_text").getText().trim());
        String fieldValue = findElement("homepage.new_book_value").getText().trim();
        if (fieldValue.isEmpty()) {
            Assert.fail("[ERROR] New to Book field is not populated");
        }
    }

    @Then("^I should see the Recent Customer List$")
    public void i_should_see_the_recent_customer_list() throws Throwable {
        Wait.untilElementPresent("homepage.recent_customer_list");
        elementShouldBePresent("homepage.recent_customer_list");
    }

    @And("^I should see the Customer Name field$")
    public void i_should_see_the_customer_name_field() throws Throwable {
        Wait.untilElementPresent("homepage.recent_customer_list");
        elementShouldBePresent("homepage.customer_name_field");
        Assert.assertEquals("Customer Name", findElement("homepage.customer_name_field").getText().trim());
    }

    @And("^I should see the SA field$")
    public void i_should_see_the_sa_field() throws Throwable {
        Wait.untilElementPresent("homepage.recent_customer_list");
        elementShouldBePresent("homepage.sa_field");
        Assert.assertEquals("SA", findElement("homepage.sa_field").getText().trim());
    }

    @And("^I should see the Last Visit field$")
    public void i_should_see_the_last_visit_field() throws Throwable {
        Wait.untilElementPresent("homepage.recent_customer_list");
        elementShouldBePresent("homepage.last_visit_field");
        Assert.assertEquals("Last Visit", findElement("homepage.last_visit_field").getText().trim());
    }

    @And("^I should see the Visits field$")
    public void i_should_see_the_visits_field() throws Throwable {
        Wait.untilElementPresent("homepage.recent_customer_list");
        elementShouldBePresent("homepage.visits_field");
        Assert.assertEquals("Visits", findElement("homepage.visits_field").getText().trim());
    }

    @And("^I should see the dollars per SA field$")
    public void i_should_see_the_dollars_per_sa_field() throws Throwable {
        Wait.untilElementPresent("homepage.recent_customer_list");
        elementShouldBePresent("homepage.dollars_SA_field");
        Assert.assertEquals("$/SA", findElement("homepage.dollars_SA_field").getText().trim());
    }

    @And("^the Unassigned Area is displayed at the bottom of SM list$")
    public void the_unassigned_area_is_displayed_at_the_bottom_of_sm_list() throws Throwable {
        if (MEW()) {
            Thread.sleep(2000);
            elementShouldBePresent("dashboard_page.sm_list");
            Clicks.hoverForSelection("dashboard_page.last_item");
            String message = Elements.findElement("dashboard_page.sm_list").getText();
            System.out.println("Text from the sm list: " + message);
            Assert.assertTrue(message.contains("UNASSIGNED"));

        } else {
            Wait.untilElementNotPresent("homepage.loading_page");
            Thread.sleep(2000);
            Clicks.hoverForSelection("homepage.unassigned_area");
            Wait.untilElementPresent("homepage.unassigned_area");
            elementShouldBePresent("homepage.unassigned_area");
            String message = Elements.findElement("homepage.unassigned_area").getText().trim();
            assertEquals("UNASSIGNED", message);
        }
    }

    @And("^I click on the Unassigned Area bar$")
    public void i_click_on_the_unassigned_area_bar() throws Throwable {
        Clicks.hoverForSelection("homepage.unassigned_area_expand_button");
        Wait.untilElementPresent("homepage.unassigned_area_expand_button");
        Clicks.click("homepage.unassigned_area_expand_button");
        Thread.sleep(1000);
    }

    @And("^I close the Unassigned Area bar$")
    public void i_close_the_unassigned_area_bar() throws Throwable {
        Clicks.hoverForSelection("homepage.unassigned_area_expand_button");
        Wait.untilElementPresent("homepage.unassigned_area_expand_button");
        Clicks.click("homepage.unassigned_area_expand_button");
    }

    @Then("^a list of Unassigned Selling Areas are displayed$")
    public void a_list_of_unassigned_selling_areas_are_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.unassigned_selling_area_list");
        elementShouldBePresent("homepage.unassigned_selling_area_list");
        Thread.sleep(1000);
        Clicks.hoverForSelection("homepage.first_unassigned_selling_area_in_list");

    }

    @When("^I click on a Unassigned Selling Area$")
    public void i_click_on_a_unassigned_selling_area() throws Throwable {
        Clicks.hoverForSelection("homepage.first_unassigned_selling_area_in_list");
        Wait.untilElementPresent("homepage.first_unassigned_selling_area_in_list");
        Clicks.click("homepage.first_unassigned_selling_area_in_list");
        Thread.sleep(1000);
    }

    @Then("^the Selling Associates List is expanded$")
    public void the_selling_associates_list_is_expanded() throws Throwable {
        Wait.untilElementPresent("homepage.unassigned_selling_associates_list");
        elementShouldBePresent("homepage.unassigned_selling_associates_list");
    }

    @And("^I click on a selling associate$")
    public void i_click_on_a_selling_associate() throws Throwable {
        Clicks.hoverForSelection("homepage.unassigned_selling_associate");
        Wait.untilElementPresent("homepage.unassigned_selling_associate");
        Clicks.click("homepage.unassigned_selling_associate");
        Wait.untilElementPresent("homepage.associate_homepage");

    }

    //TODO: BREAK METHOD IN SMALLER ONES AND ENCAPSULATE THEM (THIS IS PARTIALLY DONE)

    @And("^I create a TO DO from CREATE TO DOS page$")
    public void i_create_a_to_do_from_create_to_dos_page(List<String> tab) throws Throwable {
        for (String aTab : tab) {
            switch (aTab) {
                case "PROFILE":
                    Wait.forPageReady();
                    i_navigate_on_Create_to_dos_tab();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.profile_text");
                    Clicks.click("homepage.profile_text");
                    Clicks.click("homepage.continue_button_target_page");
                    i_enter_something_in_the_title_checkbox("PROFILE01");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "DELETE LIST":
                    i_navigate_on_Create_to_dos_tab();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.profile_text");
                    Clicks.click("homepage.profile_text");
                    Clicks.click("homepage.continue_button_target_page");
                    i_enter_something_in_the_title_checkbox(aTab);
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    Wait.forPageReady();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "COMPLETE LIST":
                    i_navigate_on_Create_to_dos_tab();
                    i_navigate_to_target_tab();
                    i_enter_value_in_limit_number_of_customers_in_list_field("5");
                    i_click_on_continue_button_from_target_tab_page();
                    i_enter_something_in_the_title_checkbox(aTab);
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    my_macys_to_dos_section_is_displayed();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;


                case "TARGET SM":
                    i_navigate_on_Create_to_dos_tab();
                    i_navigate_to_target_tab();
                    i_select_default_send_to_dos_to_sas_that_report_to_me_radio_button();
                    i_click_on_continue_button_from_target_tab_page();
                    i_enter_something_in_the_title_checkbox("TARGET TODO1");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    my_macys_to_dos_section_is_displayed();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TARGET SM SPECIFIC":
                    i_navigate_on_Create_to_dos_tab();
                    i_navigate_to_target_tab();
                    i_select_send_to_dos_to_sas_within_specific_divisions_mgms_and_or_departments_radio_button();
                    i_click_on_one_division_from_division_list();
                    mgm_list_is_displayed();
                    i_click_on_one_option_from_mgm_list();
                    department_list_is_displayed();
                    i_click_on_one_option_from_department_list();
                    i_click_on_continue_button_from_select_sas_by_section();
                    i_enter_value_in_limit_number_of_customers_in_list_field("10");
                    i_click_on_continue_button_from_target_tab_page();
                    i_enter_something_in_the_title_checkbox("TARGET SPECIFIC");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    my_macys_to_dos_section_is_displayed();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TARGET SA":
                    i_navigate_on_Create_to_dos_tab();
                    i_navigate_to_target_tab();
                    i_click_on_continue_button_from_target_tab_page();
                    i_enter_something_in_the_title_checkbox("TARGET SA");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    my_macys_to_dos_section_is_displayed();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TARGET GM":
                    i_navigate_on_Create_to_dos_tab();
                    i_navigate_to_target_tab();
                    i_select_send_to_dos_to_sas_within_my_store_GM_radion_button();
                    i_enter_value_in_limit_number_of_customers_in_list_field("10");
                    i_click_on_continue_button_from_target_tab_page();
                    i_enter_something_in_the_title_checkbox("TARGET TODO2 GM");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    my_macys_to_dos_section_is_displayed();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TARGET CORP STORE EXEC":
                    i_navigate_on_Create_to_dos_tab();
                    i_navigate_to_target_tab();
                    i_select_default_send_to_dos_to_sas_within_my_store_corp_store_exec_radio_button();
                    i_enter_value_in_limit_number_of_customers_in_list_field("10");
                    i_click_on_continue_button_from_target_tab_page();
                    i_enter_something_in_the_title_checkbox("TARGET TODO1");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    my_macys_to_dos_section_is_displayed();
                    i_navigate_to_all_to_dos_page();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TRANSACTIONS":
                    Wait.forPageReady();
                    i_navigate_on_Create_to_dos_tab();
                    Wait.untilElementPresent("homepage.trans_text");
                    Clicks.click("homepage.trans_text");
                    Clicks.click("homepage.continue_button_target_page");
                    i_enter_something_in_the_title_checkbox("TRANS01");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    Wait.forPageReady();
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TARGET GM ALL":
                    Wait.forPageReady();
                    i_navigate_on_Create_to_dos_tab();
                    Wait.untilElementPresent("homepage.target_text");
                    Clicks.click("homepage.target_text");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_send_toDos_within_specific");
                    Clicks.click("homepage.gm_send_toDos_within_specific");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_to_do_gmm_all");
                    Clicks.click("homepage.corp_store_exec_send_to_do_gmm_all");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_to_do_divisions_all");
                    Clicks.click("homepage.corp_store_exec_send_to_do_divisions_all");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_mgm_select");
                    Clicks.click("homepage.gm_mgm_select");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.gm_dep_select");
                    Clicks.click("homepage.gm_dep_select");
                    i_click_on_continue_button_from_select_sas_by_section();
                    Clicks.click("homepage.continue_button_target_page");
                    i_enter_something_in_the_title_checkbox("TODONOTDISPLAYED");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;

                case "TARGET CORP STORE EXEC SPECIFIC":
                    Wait.forPageReady();
                    i_navigate_on_Create_to_dos_tab();
                    Wait.untilElementPresent("homepage.target_text");
                    Clicks.click("homepage.target_text");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_toDos_within_specific");
                    Clicks.click("homepage.corp_store_exec_send_toDos_within_specific");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_to_do_gmm_all");
                    Clicks.click("homepage.corp_store_exec_send_to_do_gmm_all");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_to_do_divisions_all");
                    Clicks.click("homepage.corp_store_exec_send_to_do_divisions_all");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_to_do_mgm_all");
                    Clicks.click("homepage.corp_store_exec_send_to_do_mgm_all");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.corp_store_exec_send_to_do_department_all");
                    Clicks.click("homepage.corp_store_exec_send_to_do_department_all");
                    i_click_on_continue_button_from_select_sas_by_section();
                    Clicks.click("homepage.continue_button_target_page");
                    i_enter_something_in_the_title_checkbox("TARGET01");
                    i_select_valid_start_date_in_Create_List_Send_to_Dos_view();
                    i_select_valid_end_date_in_Create_List_Send_to_Dos_view();
                    i_add_a_description_in_Create_List_Send_to_Dos_view("TEST My To Do list");
                    i_click_on_create_my_list_button();
                    Wait.untilElementNotPresent("homepage.loading_page");
                    i_click_on_ok_button_in_omniclient_attention_popup();
                    iWaitToTheToDoToBeSuccessfullyProcessed();
                    break;
                default:
                    fail("NOT a valid To Dos TAB");
            }
        }

    }

    @And("^I select Send To Do's to SAs within my Store GM radion button $")
    public void i_select_send_to_dos_to_sas_within_my_store_GM_radion_button() throws Throwable {
        Clicks.click("homepage.radio_button_to_SAs_within_my_store");
    }

    @When("^I navigate to ALL TO DOS page$")
    public void i_navigate_to_all_to_dos_page() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.all_to_dos_tab");
        Clicks.click("homepage.all_to_dos_tab");
        //Wait.untilElementPresent("homepage.my_macys_toDos_tab");
        //elementShouldBePresent("homepage.my_macys_toDos_tab");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I click on MY MACYS TO DOS tab$")
    public void i_click_on_my_macys_to_dos_tab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_macys_toDos_tab");
        Thread.sleep(1000);
        Clicks.click("homepage.my_macys_toDos_tab");
        Wait.untilElementPresent("homepage.my_macys_toDo_section");
        elementShouldBePresent("homepage.my_macys_toDo_section");

    }

    @When("^I expand random Selling Manager name$")
    public void iExpandRandomSellingManagerName() throws Throwable {
        if (MEW()) {
            Wait.untilElementPresent("dashboard_page.first_sm_plus_sign_on_list");
            Clicks.click("dashboard_page.first_sm_plus_sign_on_list");
            Wait.untilElementPresent("dashboard_page.expanded_sm_from_list");
        } else {
            Clicks.hoverForSelection("homepage.second_plus_sign_on_list");
            Wait.untilElementPresent("homepage.second_plus_sign_on_list");
            Clicks.click("homepage.second_plus_sign_on_list");
            Wait.untilElementPresent("homepage.sales_manager_all_selling_areas");
        }

    }

    @Then("^I should see a list of staffing zones of the random Selling Manager$")
    public void iShouldSeeAListOfStaffingZonesOfTheRandomSellingManager() throws Throwable {
        Wait.untilElementPresent("homepage.sales_manager_all_selling_areas");
        elementShouldBePresent("homepage.sales_manager_all_selling_areas");

    }

    @And("^navigate to Unassigned Clients tab$")
    public void navigate_to_unassigned_clients_tab() throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_clients_tab");
        Clicks.click("my_clients.unassigned_clients_tab");
    }

    @And("^I should see a list of Selling Areas on the Unassigned Clients tab$")
    public void i_should_see_a_list_of_selling_areas_on_the_unsassigned_clients_tab() throws Throwable {
        Wait.untilElementPresent("my_clients.selling_areas_list");
        elementShouldBePresent("homepage.selling_area1");
        elementShouldBePresent("homepage.selling_area2");
    }

    @And("^I should see an Unassigned Area section on the Unassigned Clients tab$")
    public void i_should_see_an_unassigned_area_section_on_the_unsassigned_clients_tab() throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_area_section");
        String areaText = findElement("my_clients.unassigned_area_section").getText().trim();
        Assert.assertEquals("UNASSIGNED", areaText);
    }

    @And("^I click on the Unassigned Area section from My Clients$")
    public void i_click_on_the_unassigned_area_section_from_my_clients() throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_area_section");
        Clicks.click("my_clients.unassigned_area_section");
        Wait.untilElementPresent("my_clients.unassigned_area_1");
        Wait.untilElementPresent("my_clients.unassigned_area_2");
    }

    @And("^I click on a specific Unassigned Area from My Clients$")
    public void i_click_on_a_specific_unassigned_area_from_my_clients() throws Throwable {
        Wait.untilElementPresent("my_clients.expand_unassigned_area_1");
        Clicks.click("my_clients.expand_unassigned_area_1");
        Wait.forPageReady();
    }

    @Then("^a list of Unassigned Clients from that specific area is displayed$")
    public void a_list_of_unassigned_clients_from_that_specific_area_is_displayed() throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_clients_list");
        Elements.elementShouldBePresent("my_clients.unassigned_clients_list");

    }

    @And("^the following columns should be populated for the Unassigned Clients:$")
    public void the_following_columns_should_be_populated_for_the_unassigned_clients(List<String> column) throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_clients_list");
        for (String aColumn : column) {
            switch (aColumn) {
                case "Customer":
                    Elements.elementShouldBePresent("my_clients.customer_column");
                    break;
                case "Selling Associate":
                    Elements.elementShouldBePresent("my_clients.selling_associate_column");
                    break;
                case "Relationship Date":
                    Elements.elementShouldBePresent("my_clients.relationship_date_column");
                    break;
                case "Last Visit":
                    Elements.elementShouldBePresent("my_clients.last_visit_column");
                    break;
                case "Removed Date":
                    Elements.elementShouldBePresent("my_clients.removed_data_column");
                    break;
                case "Removed By":
                    Elements.elementShouldBePresent("my_clients.removed_by_column");
                    break;
                default:
                    fail("NOT a valid Column name");
            }
        }
    }

    // TODO: BREAK METHOD IN SMALLER ONES AND ENCAPSULATE THEM

    @And("^I add a new Macys Client: \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_add_a_new_macys_client(String fname, String lname, String address, String city, String zip, String hint, String phone, String state) throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementNotPresent("homepage.loading_page");
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
        iSelectFromThePhoneTypeDropdownOnCreateClientPage("MOBILE");
        TextBoxes.typeTextbox("homepage.add_new_client_address", address);
        TextBoxes.typeTextbox("homepage.add_new_client_address2", "ADDRESSLINE2");
        TextBoxes.typeTextbox("homepage.add_new_client_city", city);
        DropDowns.selectByText("homepage.add_new_client_state", state);
        TextBoxes.typeTextbox("homepage.add_new_client_zip", zip);
        TextBoxes.typeTextbox("homepage.add_new_client_hint", hint);
        TextBoxes.typeTextbox("homepage.add_new_client_note", "TESTNOTE");
        Clicks.click("homepage.add_new_client_preff_phone");
        iClickOnPersonalizeClearToDORadioButton();
        Clicks.click("homepage.add_new_client_save");
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.add_new_client_ok");
        Clicks.click("homepage.add_new_client_ok");
        Wait.forPageReady();
    }

    @And("^I remove the newly added Macys Client$")
    public void i_remove_the_newly_added_macys_client() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.remove_client_from_book", 5);
        Clicks.click("homepage.remove_client_from_book");
        Wait.untilElementPresent("homepage.add_new_client_ok");
        Clicks.click("homepage.add_new_client_ok");
        Wait.forPageReady();
    }

    @And("^I switch user back to the original login$")
    public void i_switch_user_back_to_the_original_login() throws Throwable {
        Thread.sleep(2000);
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.switch_drop");
        elementShouldBePresent("homepage.switch_drop");
        Clicks.hoverForSelection("homepage.switch_drop");
        Thread.sleep(1000);
        Wait.untilElementPresent("homepage.switch_back_button");
        Clicks.click("homepage.switch_back_button");
        Wait.forPageReady();
    }

    @When("^I select specific client from the list: \"([^\"]*)\"$")
    public void i_select_specific_client_from_the_list_something(String client) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent(By.partialLinkText(client));
        Clicks.click("my_clients.first_unassigned_client");
    }

    @And("^I select the \"([^\"]*)\" associate from Reassign to Selling Associate drop down$")
    public void i_select_the_something_associate_from_reassign_to_selling_associate_drop_down(int index) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("my_clients.assoc_dropdown_unass");
        DropDowns.selectByIndex("my_clients.assoc_dropdown_unass", index);

    }

    @And("^I click SAVE button from Unassigned Clients tab$")
    public void i_click_save_button_from_unassigned_clients_tab() throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_clients_save");
        Clicks.click("my_clients.unassigned_clients_save");
    }

    @Then("^the previously selected client \"([^\"]*)\" is no longer displayed in the Unassigned area$")
    public void the_previously_selected_client_something_is_no_longer_displayed_in_the_unassigned_area(String client) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(2000);
        String clientList = findElement("my_clients.unassigned_client_1").getText();
        System.out.println(clientList);
        if (clientList.contains(client)) {
            Assert.fail("Client was not removed from Unassigned area");
        }
    }


    @Then("^Manage Relationships page is displayed$")
    public void manage_relationships_page_is_displayed() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("my_clients.manage_relationships_page", 10);
        elementShouldBePresent("my_clients.manage_relationships_page");
    }

    @And("^Unassigned area section is displayed$")
    public void unassigned_area_section_is_displayed() throws Throwable {
        Wait.secondsUntilElementPresent("my_clients.unassigned_area_manage_relationships", 10);
        elementShouldBePresent("my_clients.unassigned_area_manage_relationships");
    }

    @Then("^a list of unassigned selling areas is displayed$")
    public void a_list_of_unassigned_selling_areas_is_displayed() throws Throwable {
        Wait.untilElementPresent("my_clients.first_unassigned_area_manage_relationships");
        elementShouldBePresent("my_clients.first_unassigned_area_manage_relationships");
        Wait.untilElementPresent("my_clients.second_unassigned_area_manage_relationships");
        elementShouldBePresent("my_clients.second_unassigned_area_manage_relationships");
    }

    @And("^I click on the Unassigned Area section from Manage Relationships$")
    public void i_click_on_the_unassigned_area_section_from_manage_relationships() throws Throwable {
        Wait.secondsUntilElementPresent("my_clients.unassigned_area_manage_relationships", 20);
        Clicks.click("my_clients.unassigned_area_manage_relationships");
        Wait.forPageReady();
    }

    @And("^I click on a specific Unassigned Area from Manage Relationships$")
    public void i_click_on_a_specific_unassigned_area_from_manage_relationships() throws Throwable {
        Wait.untilElementPresent("my_clients.click_first_unassigned_area_in_list");
        Clicks.click("my_clients.click_first_unassigned_area_in_list");
        Wait.forPageReady();

    }

    @Then("^a drop down with associates who are in that Unassigned Selling area is displayed$")
    public void a_drop_down_with_associates_who_are_in_that_unassigned_selling_area_is_displayed() throws Throwable {
        Wait.untilElementPresent("my_clients.selling_associate_dropdown");
        elementShouldBePresent("my_clients.selling_associate_dropdown");
        Clicks.click("my_clients.selling_associate_dropdown");
        Wait.untilElementPresent("my_clients.first_associate_in_dropdown");
        elementShouldBePresent("my_clients.first_associate_in_dropdown");

    }

    @And("^I select an associate \"([^\"]*)\" from Select Selling Associate drop down$")
    public void i_select_an_associate_from_select_selling_associate_drop_down(String assoc) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(2000);
        Wait.untilElementPresent("my_clients.selling_associate_dropdown");
        DropDowns.selectByText("my_clients.selling_associate_dropdown", assoc);
        Wait.forPageReady();

    }

    @Then("^a list of Unassigned Clients from that specific area is displayed in Manage Relationships page$")
    public void a_list_of_unassigned_clients_from_that_specific_area_is_displayed_in_manage_relationships_page() throws Throwable {
        Wait.untilElementPresent("my_clients.unassigned_clients_list_manage_relationships");
        elementShouldBePresent("my_clients.unassigned_clients_list_manage_relationships");
    }

    @When("^I select specific client from the list in Manage Relationships view: \"([^\"]*)\"$")
    public void i_select_specific_client_from_the_list_in_manage_relationships_view_something(String client) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent(By.partialLinkText(client));
        Wait.untilElementPresent("my_clients.first_unassigned_client_manage_relationships");
        Clicks.click("my_clients.first_unassigned_client_manage_relationships");
    }

    @And("^I click SAVE button from Manage Relationships page$")
    public void i_click_save_button_from_manage_relationships_page() throws Throwable {
        Wait.secondsUntilElementPresent("my_clients.ok_button_manage_relationships", 10);
        Clicks.click("my_clients.ok_button_manage_relationships");
    }

    @Then("^the previously selected client \"([^\"]*)\" is no longer displayed in the Unassigned area Manage Relationships$")
    public void the_previously_selected_client_something_is_no_longer_displayed_in_the_unassigned_area_manage_relationships(String client1) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementNotPresent("homepage.loading_page");
        String clientList1 = findElement("my_clients.unassigned_clients_list_manage_relationships").getText();
        if (clientList1.contains(client1)) {
            Assert.fail("Client was not removed from Unassigned area");
        }

    }

    @When("^I navigate to Macys Homepage$")
    public void i_navigate_to_macys_homepage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.homepage_tab");
        Clicks.click("homepage.homepage_tab");
        Wait.untilElementNotPresent("homepage.loading_page");

    }

    @Then("^I should see the list of TO DOS$")
    public void i_should_see_the_list_of_to_dos() throws Throwable {
        Wait.untilElementPresent("homepage.list_of_todos_my_macys_todo_tab");
        elementShouldBePresent("homepage.list_of_todos_my_macys_todo_tab");
    }

    @Then("^I should see the list of TO DOS for corp store exec$")
    public void i_should_see_the_list_of_to_dos_for_corp_store_exec() throws Throwable {
        Wait.untilElementPresent("homepage.list_of_to_dos_corp_store_exec");
        elementShouldBePresent("homepage.list_of_to_dos_corp_store_exec");
    }

    @Then("^I should see the TO DOs on the dashboard$")
    public void i_should_see_the_to_dos_on_the_dashboard() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        findElement("homepage.macys_to_dos_homepage_dashboard");
        elementShouldBePresent("homepage.macys_to_dos_homepage_dashboard");
        String todoText = findElement("homepage.last_todo_in_homepage_dashboard_todo_list").getAttribute("title");
        Assert.assertEquals("PROFILE01", todoText);


    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the General Manager$")
    public void i_should_see_the_new_to_do_something_created_by_the_general_manager(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.hoverForSelection("homepage.last_toDo_in_list");
        Wait.untilElementPresent("homepage.last_toDo_in_list");
        elementShouldBePresent("homepage.last_toDo_in_list");
        String listText = findElement("homepage.last_toDo_in_list").getText().trim();
        System.out.println(listText);
        Assert.assertTrue(listText.contains(name));

    }

    @And("^I click on MY TO DOS tab$")
    public void i_click_on_my_to_dos_tab() throws Throwable {
        Clicks.click("homepage.my_toDos_tab");
        Wait.untilElementPresent("homepage.my_to_dos_page");
        Thread.sleep(4000);
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the Corporate Store Executive$")
    public void i_should_see_the_new_to_do_something_created_by_the_corporate_store_executive(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_toDo_in_list");
        elementShouldBePresent("homepage.last_toDo_in_list");
        String listText = findElement("homepage.last_toDo_in_list").getText().trim();
        Assert.assertEquals(name, listText);

    }

    @Then("^I log out from the mcom aplication$")
    public void i_log_out_from_the_mcom_aplication() throws Throwable {
        Clicks.click("homepage.log_out_button");

        Thread.sleep(2000);

        Clicks.click("homepage.yes_button_logout_popup");
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.login_page");
        elementShouldBePresent("homepage.login_page");

    }

    @And("^I should see the list information$")
    public void i_should_see_the_list_information(List<String> column) throws Throwable {
        Wait.untilElementPresent("homepage.list_of_todos_my_macys_todo_tab");
        for (String nameColumn : column) {
            switch (nameColumn) {
                case "Created by":
                    Elements.elementShouldBePresent("homepage.created_by_header_my_macys_todo_tab");
                    break;
                case "List Title":
                    Elements.elementShouldBePresent("homepage.list_title_my_macys_todo_tab");
                    break;
                case "Uncalled clients":
                    Elements.elementShouldBePresent("homepage.uncalled_customers_my_macys_todo_tab");
                    break;
                case "Due by":
                    Elements.elementShouldBePresent("homepage.due_by_my_macys_todo_tab");
                    break;
                default:
                    fail("NOT a valid Column name");


            }
        }
    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the selling manager$")
    public void i_should_see_the_new_to_do_something_created_by_the_selling_manager(String todo) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.hoverForSelection("homepage.last_toDo_in_list");
        Wait.untilElementPresent("homepage.last_toDo_in_list");
        elementShouldBePresent("homepage.last_toDo_in_list");
        String listText = findElement("homepage.last_toDo_in_list").getText().trim();
        Assert.assertEquals(todo, listText);

    }

    @When("^I click to expand the chevron$")
    public void i_click_to_expand_the_chevron() throws Throwable {
        Elements.findElement("homepage.chevron_last_in_list");
        Clicks.click("homepage.chevron_last_in_list");
    }


    @Then("^List description will be displayed$")
    public void list_description_will_be_displayed() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("homepage.last_todo_in_list_description");
        elementShouldBePresent("homepage.last_todo_in_list_description");

    }

    @And("^I should see the new TO DO \"([^\"]*)\" created by the Corporate Admin$")
    public void i_should_see_the_new_to_do_something_created_by_the_corporate_admin(String name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.last_toDo_in_list");
        elementShouldBePresent("homepage.last_toDo_in_list");
        String listText = findElement("homepage.last_toDo_in_list").getText().trim();
        Assert.assertEquals(name, listText);

    }

    @And("^I should see the Created By name:$")
    public void i_should_see_the_created_by_name(List<String> name) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.hoverForSelection("homepage.last_toDo_in_list");
        Wait.untilElementPresent("homepage.last_toDo_in_list");
        String createdByExpected = name.get(0);
        String createdByActual = findElement("homepage.last_toDo_createdby").getText().trim();
        System.out.println("Expected Name: " + createdByExpected + " | " + "Actual Name: " + createdByActual);
        Assert.assertEquals(createdByExpected, createdByActual);

    }

    @And("^I click on LISTS tab$")
    public void i_click_on_lists_tab() throws Throwable {
        Wait.untilElementPresent("homepage.lists_tab");
        elementShouldBePresent("homepage.lists_tab");
        Clicks.click("homepage.lists_tab");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I should see the Title, Description and Dates$")
    public void i_should_see_the_title_description_and_dates() throws Throwable {
        Wait.untilElementPresent("homepage.event_containter");
        String eventContainer = findElement("homepage.event_containter").getText();
        Assert.assertTrue(eventContainer.contains("Title"));
        Assert.assertTrue(eventContainer.contains("Description"));
        Assert.assertTrue(eventContainer.contains("Complete By"));
        Assert.assertTrue(eventContainer.contains("Event Start Date"));
        Assert.assertTrue(eventContainer.contains("Event End Date"));
        Thread.sleep(2000);
    }

    @And("^I should see a list of Macys associates who received the To Do$")
    public void i_should_see_a_list_of_macys_associates_who_received_the_to_do() throws Throwable {
        Wait.untilElementPresent("homepage.event_user_container");
        Elements.elementShouldBePresent("homepage.event_user_container");
    }

    @Then("^I should not see the pending todo displayed on the dashboard$")
    public void i_should_not_see_the_pending_todo_displayed_on_the_dashboard() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.recent_customer_list");
        String todoText = findElement("homepage.recent_customer_list").getText();
        System.out.println(todoText);
        Assert.assertFalse(todoText.contains("TODONOTDISPLAYED"));
    }

    @When("^I sign into Omniclient application as Admin User$")
    public void iSignIntoOmniclientApplicationAsAdminUser() throws Throwable {
        TextBoxes.typeTextbox("logon_pg.associateID_textbox", "10000052");
        TextBoxes.typeTextbox("logon_pg.password_textbox", "Temp$Pass2");
        Clicks.click("logon_pg.adminuser_checkbox");
        Clicks.click("logon_pg.signin_button");
        Thread.sleep(8000);
        Wait.untilElementPresent("logon_pg.verify_changeuser_page");
        elementShouldBePresent("logon_pg.verify_changeuser_page");

    }

    @And("^change user into \"([^\"]*)\" from Admin interface$")
    public void changeUserIntoFromAdminInterface(String assocID) throws Throwable {
        TextBoxes.typeTextbox("logon_pg.changeuser_textbox", assocID);
        Clicks.click("logon_pg.changeuser_button");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I should see NO Unassigned Area bar at the bottom of SM list$")
    public void i_should_see_no_unassigned_area_bar_at_the_bottom_of_sm_list() throws Throwable {
        Thread.sleep(4000);
        String a = "document.getElementById('listConleft')";
        JavascriptExecutor jsExec = (JavascriptExecutor) WebDriverManager.getWebDriver();
        jsExec.executeScript(a + ".scrollTop = " + a + ".scrollHeight");
        Wait.untilElementPresent("homepage.sales_manager_list");
        Elements.elementShouldBePresent("homepage.sales_manager_list");
        Thread.sleep(2000);
        try {
            findElement("homepage.unassigned_area").isDisplayed();
            Assert.fail("[ERROR] Unassigned Area is displayed");
        } catch (Exception e) {
            System.out.println("[INFO] Unassigned Area is not displayed");
        }
    }

    @Then("^the following information should be displayed in Preferred Information section:$")
    public void the_following_information_should_be_displayed_in_preferred_information_section(List<String> row) throws Throwable {
        Wait.untilElementPresent("homepage.manage_customers_tabs_section");
        Wait.untilElementPresent("homepage.preferred_information_section");
        String preferredText = findElement("homepage.preferred_information_section").getText();
        assertTrue(preferredText.contains("Preferred Information:"));
        OmniclientUtils.waitForAngularLoad();
        for (String aRow : row) {
            switch (aRow) {
                case "Name":
                    Wait.untilElementPresent("homepage.pref_name_row_manage_clients");
                    Elements.elementShouldBePresent("homepage.pref_name_row_manage_clients");
                    break;
                case "Preferred address":
                    Wait.untilElementPresent("homepage.pref_address_row_manage_clients");
                    Elements.elementShouldBePresent("homepage.pref_address_row_manage_clients");
                    break;
                case "Preferred phone number":
                    Wait.untilElementPresent("homepage.pref_phone_row_manage_clients");
                    Elements.elementShouldBePresent("homepage.pref_phone_row_manage_clients");
                    break;
//                    TODO: investigate if the case below is needed or remove it (commented for now)
//                case "Preferred phone number gm":
//                    Thread.sleep(4000);
//                    Wait.untilElementPresent("homepage.pref_phone_row_manage_clients_gm");
//                    Elements.elementShouldBePresent("homepage.pref_phone_row_manage_clients_gm");
//                    break;
            }

        }
    }

    @And("^I navigate to Manage Client tab$")
    public void i_navigate_to_manage_client_tab() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.manage_clients_tab");
        Clicks.click("homepage.manage_clients_tab");
    }

    @And("^the following information should be displayed in Primary Information section:$")
    public void the_following_information_should_be_displayed_in_primary_information_section(List<String> row) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.manage_customers_tabs_section");
        Wait.untilElementPresent("homepage.primary_information_section");
        String primaryText = findElement("homepage.primary_information_section").getText();
        assertTrue(primaryText.contains("Primary Information:"));
        for (String aRow : row) {
            switch (aRow) {
                case "Name":
                    Elements.elementShouldBePresent("homepage.primary_name_row_manage_clients");
                    break;
                case "Primary address":
                    Elements.elementShouldBePresent("homepage.primary_address_row_manage_clients");
                    break;
                case "Primary phone number":
                    Elements.elementShouldBePresent("homepage.primary_phone_row_manage_clients");
                    break;
            }

        }
    }

    @And("^the Additional Information section is displayed with the following sections MACYs:$")
    public void the_additional_information_section_is_displayed_with_the_following_sections_macys(List<String> row) throws Throwable {
        Wait.untilElementPresent("homepage.manage_customers_tabs_section");
        Wait.untilElementPresent("homepage.additional_information_section");
        String additionalText = findElement("homepage.additional_information_section").getText();
        assertTrue(additionalText.contains("Additional Information:"));
        OmniclientUtils.waitForAngularLoad();
        for (String aRow : row) {
            switch (aRow) {
                case "Address(es)":
                    Elements.elementShouldBePresent("homepage.additional_addresses_row");
                    break;
                case "Phone(s)":
                    Elements.elementShouldBePresent("homepage.additional_phones_row");
                    break;
            }
        }
    }

    @When("^I click on the Edit button from the CLIENT INFO section MACYS$")
    public void i_click_on_the_edit_button_from_the_client_info_section_macys() throws Throwable {
        Thread.sleep(4000);
        Wait.untilElementPresent("homepage.edit_button_manage_clients");
        Clicks.click("homepage.edit_button_manage_clients");
    }

    @Then("^the client information fields become editable MACYS$")
    public void the_client_information_fields_become_editable_macys() throws Throwable {
        Wait.untilElementPresent("homepage.pref_name_input_field");
        elementShouldBePresent("homepage.pref_name_input_field");

    }

    @When("^we update data \"([^\"]*)\" into Additional information fields MACYS$")
    public void we_update_data_something_into_additional_information_fields_macys(String name) throws Throwable {
        TextBoxes.typeTextbox("homepage.pref_name_input_field", name);

    }

    @And("^we save the changes MACYS$")
    public void we_save_the_changes_macys() throws Throwable {
        Wait.untilElementPresent("homepage.save_button_manage_client");
        elementShouldBePresent("homepage.save_button_manage_client");
        Clicks.click("homepage.save_button_manage_client");
    }

    @Then("^updated data \"([^\"]*)\" is displayed for the Additional information section MACYS$")
    public void updated_data_something_is_displayed_for_the_additional_information_section_macys(String updatedPrefName) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.preferred_name_added");
        elementShouldBePresent("homepage.preferred_name_added");
        String prefName = findElement("homepage.preferred_name_added").getText();
        assertTrue(prefName.contains(updatedPrefName));

    }

    @And("^I search for an existing client by telephone \"([^\"]*)\"$")
    public void i_search_for_an_existing_client_by_telephone(String phone) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        TextBoxes.typeTextbox("homepage.telephone_textbox", phone);
        Clicks.click("homepage.search_button");
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.searchresults_header");
    }

    @And("^I click on an existing client from search results page MACYS$")
    public void i_click_on_an_existing_client_from_search_results_page_macys() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.click("homepage.first_client_in_search_results_list_by_phone");
        Wait.forPageReady();

    }

    @And("^I click on a client from my clients list$")
    public void i_click_on_a_client_from_my_clients_list() throws Throwable {
        Wait.untilElementPresent("homepage.first_client_in_my_clients_list_gm");
        Clicks.click("homepage.first_client_in_my_clients_list_gm");
        Wait.forPageReady();
    }


    @Then("^Existing profile information should be displayed$")
    public void existing_profile_information_should_be_displayed(List<String> section) throws Throwable {
        String name = section.get(0).trim();
        String addr = section.get(1).trim();
        String phon = section.get(2).trim();
        Wait.untilElementPresent("homepage.edit_client_add_info_section");
        Elements.elementShouldBePresent("homepage.edit_client_add_info_section");
        String infoSection = findElement("homepage.edit_client_add_info_section").getText().trim();
        Assert.assertTrue(infoSection.contains(name));
        Assert.assertTrue(infoSection.contains(addr));
        Assert.assertTrue(infoSection.contains(phon));
        if (section.size() > 3) {
            String email = section.get(3).trim();
            Assert.assertTrue(infoSection.contains(email));

        }

    }

    @And("^the Preferred information radio buttons should be preselected$")
    public void the_preferred_information_radio_buttons_should_be_preselected() throws Throwable {
        Wait.untilElementPresent("homepage.edit_client_add_info_section");
        elementShouldBePresent("homepage.edit_client_pref_addr_radio");
//        elementShouldBePresent("homepage.edit_client_pref_phon_radio");
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^the ADD button is displayed in address, phone number sections$")
    public void the_add_button_is_displayed_in_address_phone_number_sections() throws Throwable {
        Wait.untilElementPresent("homepage.edit_client_add_info_section");
        findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='address']")).isDisplayed();
        findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='phone']")).isDisplayed();
//        elementShouldBePresent("homepage.edit_client_add_addr_button");
//        elementShouldBePresent("homepage.edit_client_add_phon_button");
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^the SAVE button from Customer Info tab MACYS and BLM is displayed$")
    public void the_save_button_from_customer_info_tab_macys_and_blm_is_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.edit_client_add_info_section");
//        Wait.untilElementPresent("homepage.edit_client_save_button");
//        elementShouldBePresent("homepage.edit_client_save_button");
        findElement(By.cssSelector("button[ng-click*='saveCustInfo']")).isDisplayed();
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^CANCEL button from Customer Info tab MACYS and BLM is displayed$")
    public void cancel_button_from_customer_info_tab_macys_and_blm_is_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.edit_client_add_info_section");
//        Wait.untilElementPresent("homepage.edit_client_cancel_button");
//        elementShouldBePresent("homepage.edit_client_cancel_button");
        findElement(By.cssSelector("button[ng-click*='cancelUpdateCust']")).isDisplayed();
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^I click on the CANCEL button from CLIENT INFO section$")
    public void i_click_on_the_cancel_button_from_client_info_section() throws Throwable {
//        Wait.untilElementPresent("homepage.edit_client_cancel_button");
//        Clicks.click("homepage.edit_client_cancel_button");
        WebElement cancelBtn = findElement(By.cssSelector("button[ng-click*='cancelUpdateCust']"));
        Clicks.click(cancelBtn);
    }

    @Then("^I should see the information popup from CLIENT INFO section$")
    public void i_should_see_the_information_popup_from_client_info_section() throws Throwable {
        Wait.untilElementPresent("homepage.cancel_popup_client_info");
        elementShouldBePresent("homepage.cancel_popup_client_info");
    }

    @When("^I click on the ok button on the popup from CLIENT INFO section$")
    public void i_click_on_the_ok_button_on_the_popup_from_client_info_section() throws Throwable {
        Wait.untilElementPresent("homepage.ok_button_cancel_popup_client_info");
        elementShouldBePresent("homepage.ok_button_cancel_popup_client_info");
        Clicks.click("homepage.ok_button_cancel_popup_client_info");
    }


    @Then("^the popup from CLIENT INFO section is no longer displayed$")
    public void the_popup_from_client_info_section_is_no_longer_displayed() throws Throwable {
        //Wait.untilElementNotPresent("homepage.cancel_popup_client_info");
        Wait.secondsUntilElementNotPresent("homepage.cancel_popup_client_info", 3000);
    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @When("^click on ADD button from Address section$")
    public void click_on_add_button_from_address_section() throws Throwable {
        WebElement addBtn = findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='address']"));
        Clicks.click(addBtn);
//        Wait.untilElementPresent("homepage.edit_client_add_addr_button");
//        Clicks.click("homepage.edit_client_add_addr_button");
    }


    @And("^we add additional address \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" MACYS$")
    public void we_add_additional_address_macys(String additionaladd, String City, String Zip, String state) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_field_1", additionaladd);
        DropDowns.selectByText("homepage.address_type_dropdown", "BUSINESS");
        TextBoxes.typeTextbox("homepage.city_field_first_additional_address", City);
        DropDowns.selectByText("homepage.state_dropdown_manage_client", state);
        TextBoxes.typeTextbox("homepage.zipcode_field_first_additional_address", Zip);


    }

    //TODO: INVESTIGATE WHY ONLY NATIVE SELENIUM SOLUTION WORKS AND THE JSON ELEMENT APPROACH DOES NOT
    @And("^we click on ADD button from Phones section$")
    public void we_click_on_add_button_from_phones_section() throws Throwable {
        WebElement addBtn = findElement(By.cssSelector("[ng-click*='addNewIndex'][ng-click*='phone']"));
        Clicks.click(addBtn);
//        Wait.untilElementPresent("homepage.edit_client_add_phon_button");
//        Clicks.click("homepage.edit_client_add_phon_button");
    }

    @And("^we add additional phone number \"([^\"]*)\" MACYS$")
    public void we_add_additional_phone_number_something_macys(String additionalphone) throws Throwable {
        TextBoxes.typeTextbox("homepage.phone_first_field_additional", additionalphone);
        DropDowns.selectByText("homepage.phone_type_dropdown_2", "MOBILE");


    }

    @And("^we click on SAVE button from Client Info tab MACYS$")
    public void we_click_on_save_button_from_client_info_tab_macys() throws Throwable {
        Thread.sleep(1000);
        Elements.elementInView("homepage.edit_client_save_button");
        Clicks.click("homepage.edit_client_save_button");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^data is displayed for the Additional Information section$")
    public void data_is_displayed_for_the_additional_information_section(DataTable info) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(2000);
        for (List<String> list : info.raw()) {
            String infoType = list.get(1);
            String infoValue = list.get(0);
            switch (infoType) {
                case "ADDRESS":
                    String additionaladdress = findElement("homepage.after_save_additional_address").getText().toUpperCase();
                    System.out.println("print: " + additionaladdress);
                    Assert.assertTrue(additionaladdress.contains(infoValue));
                    break;
                case "CITY":
                    String additionalcity = findElement("homepage.after_save_additional_city").getText().toUpperCase();
                    System.out.println("print: " + additionalcity);
                    Assert.assertTrue(additionalcity.contains(infoValue));
                    break;
                case "ZIP":
                    String additionalzip = findElement("homepage.after_save_additional_zip").getText();
                    System.out.println("print: " + additionalzip);
                    Assert.assertTrue(additionalzip.contains(infoValue));
                    break;
                case "PHONE":
                    String additionalphone = findElement("homepage.after_save_additional_phone").getText();
                    System.out.println("phone: " + additionalphone);
                    Assert.assertTrue(additionalphone.contains(infoValue));
                    break;
            }
        }
    }

    @And("^I change the preferred address by selecting the radio button from the new added address$")
    public void i_change_the_preferred_address_by_selecting_the_radio_button_from_the_new_added_address() throws
            Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.first_additional_address_radio_button_mcy");
        elementShouldBePresent("homepage.first_additional_address_radio_button_mcy");
        Clicks.click("homepage.first_additional_address_radio_button_mcy");

    }


    @And("^I change the preferred number by selecting the radio button from the new added phone$")
    public void i_change_the_preferred_number_by_selecting_the_radio_button_from_the_new_added_phone() throws
            Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.first_additional_phone_number_mcy_2");
        elementShouldBePresent("homepage.first_additional_phone_number_mcy_2");
        Clicks.click("homepage.first_additional_phone_number_mcy_2");

    }

    @Then("^the new preferred information is displayed$")
    public void the_new_preferred_information_is_displayed(DataTable info) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(2000);
        for (List<String> list : info.raw()) {
            String infoType = list.get(1);
            String infoValue = list.get(0);
            switch (infoType) {
                case "ADDRESS":
                    String updateprefaddress = findElement("homepage.updated_pref_address_mcy").getText().toUpperCase();
                    System.out.println("address" + updateprefaddress);
                    Assert.assertTrue(updateprefaddress.contains(infoValue));
                    break;
                case "CITY":
                    String updateprefcity = findElement("homepage.updated_pref_city_mcy").getText().toUpperCase();
                    System.out.println("city" + updateprefcity);
                    Assert.assertTrue(updateprefcity.contains(infoValue));
                    break;
                case "ZIP":
                    String updateprefzip = findElement("homepage.updated_pref_zip_mcy").getText();
                    System.out.println("zip" + updateprefzip);
                    Assert.assertTrue(updateprefzip.contains(infoValue));
                    break;
                case "PHONE":
                    String updateprefphone = findElement("homepage.updated_pref_phone_mcy").getText();
                    System.out.println("phone" + updateprefphone);
                    Assert.assertTrue(updateprefphone.contains(infoValue));
                    break;
            }
        }
    }

    @When("^I delete all created ToDos$")
    public void i_delete_all_created_todos() throws Throwable {
        Wait.untilElementPresent("homepage.delete_todo_icon");
        Boolean deleting = true;
        while (deleting) {
            try {
                Navigate.browserRefresh();
                Wait.untilElementNotPresent("homepage.loading_page");
                findElement("homepage.delete_todo_icon").isDisplayed();
                Wait.untilElementPresent("homepage.delete_todo_icon");
                Clicks.click("homepage.delete_todo_icon");
                Wait.untilElementPresent("homepage.delete_todo_popup");
                Wait.untilElementPresent("homepage.delete_todo_popup_yes");
                Clicks.click("homepage.delete_todo_popup_yes");
                Wait.untilElementNotPresent("homepage.loading_page");
            } catch (Exception e) {
                deleting = false;
                System.out.println("Done!");
            }
        }
    }

    @Then("^I should not see any ToDos that can be deleted$")
    public void i_should_not_see_any_todos_that_can_be_deleted() throws Throwable {
        try {
            Navigate.browserRefresh();
            Wait.untilElementNotPresent("homepage.loading_page");
            findElement("homepage.delete_todo_icon").isDisplayed();
            Assert.fail("[ERROR] There are still Todos that need to be deleted");
        } catch (Exception e) {
            System.out.println("[INFO] All good! Todos are cleaned up");
        }
    }

    @And("^I add \"([^\"]*)\" in Street Address 1 field$")
    public void i_add_something_in_street_address_1_field(String address1) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_field_1", address1);


    }

    @Then("^I should see the error popup from CLIENT INFO section MACYS$")
    public void i_should_see_the_error_popup_from_client_info_section_macys() throws Throwable {
        Wait.untilElementPresent("homepage.popup_error_manage_client");
        elementShouldBePresent("homepage.popup_error_manage_client");
    }

    @And("^I click on the OK button on the error popup from CLIENT INFO section MACYS$")
    public void i_click_on_the_ok_button_on_the_error_popup_from_client_info_section_macys() throws Throwable {
        elementShouldBePresent("homepage.ok_button_manage_client_popup");
        Clicks.click("homepage.ok_button_manage_client_popup");
    }

    @And("^I add city \"([^\"]*)\" in the City input field$")
    public void i_add_city_something_in_the_city_input_field(String city) throws Throwable {
        TextBoxes.typeTextbox("homepage.city_field_first_additional_address", city);
    }

    @And("^I select random state from State dropdown$")
    public void i_select_random_state_from_state_dropdown() throws Throwable {
        DropDowns.selectRandomValue("homepage.state_dropdown_manage_client");

    }

    @And("^I select random type from TYPE dropdown$")
    public void i_select_random_type_from_type_dropdown() throws Throwable {
        DropDowns.selectRandomValue("homepage.address_type_dropdown");

    }

    @And("^I enter zip code \"([^\"]*)\" in the Zip Code input field$")
    public void i_enter_invalid_zip_code_something_in_the_zip_code_input_field(String zip) throws Throwable {
        TextBoxes.typeTextbox("homepage.zipcode_field_first_additional_address", zip);
    }

    @Then("^data is successfully saved MACYS Manage Client$")
    public void data_is_successfully_saved_macys_manage_client() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.client_info_page");
        elementShouldBePresent("homepage.client_info_page");
    }

    @And("^we add additional Address Line 1 \"([^\"]*)\" MACYS$")
    public void we_add_additional_address_line_1_something_macys(String addressline1) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_field_1", addressline1);
    }

    @And("^we add additional Address Line 2 \"([^\"]*)\" MACYS$")
    public void we_add_additional_address_line_2_something_macys(String addressline2) throws Throwable {
        TextBoxes.typeTextbox("homepage.first_additional_address_input_field_2", addressline2);
    }

    @And("^we add remaining address mandatory fields MACYS$")
    public void we_add_remaining_address_mandatory_fields_macys() throws Throwable {
        DropDowns.selectByText("homepage.address_type_dropdown", "BUSINESS");
        TextBoxes.typeTextbox("homepage.city_field_first_additional_address", "random");
        DropDowns.selectByText("homepage.state_dropdown_manage_client", "AL");
        TextBoxes.typeTextbox("homepage.zipcode_field_first_additional_address", "12345");
    }

    @And("^we add incomplete additional phone number \"([^\"]*)\" MACYS$")
    public void we_add_incomplete_additional_phone_number_something_macys(String incompleteadditionalphone) throws
            Throwable {
        TextBoxes.typeTextbox("homepage.phone_first_field_additional", incompleteadditionalphone);
        DropDowns.selectByText("homepage.phone_type_dropdown_2", "MOBILE");

    }

    @And("^we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE MACYS$")
    public void we_add_additional_address_line_address_line_1_and_2_remaining_mandatory_fields_and_click_save_macys
            (List<String> addressline1and2) throws Throwable {
        for (String line : addressline1and2) {
            we_add_additional_address_line_1_something_macys(line);
            we_add_additional_address_line_2_something_macys(line);
            we_add_remaining_address_mandatory_fields_macys();
            we_click_on_save_button_from_client_info_tab_macys();
            i_should_see_the_error_popup_from_client_info_section_macys();
            i_click_on_the_ok_button_on_the_error_popup_from_client_info_section_macys();


        }
    }

    @And("^I click on the FIRST,LAST NAME & ZIP radio button$")
    public void i_click_on_the_firstlast_name_zip_radio_button() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        findElement("homepage.first_last_name_and_zip_radio_button");
        Thread.sleep(4000);
        Clicks.click("homepage.first_last_name_and_zip_radio_button");
    }

    @And("^I enter first name \"([^\"]*)\" in the input field$")
    public void i_enter_first_name_something_in_the_input_field(String firstname) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.first_name_input_homepage_search");
        elementShouldBePresent("homepage.first_name_input_homepage_search");
        TextBoxes.typeTextbox("homepage.first_name_input_homepage_search", firstname);
    }

    @Then("^error popup with required fields is received \"([^\"]*)\"$")
    public void error_popup_with_required_fields_is_received_something(String errormessage) throws Throwable {
        Wait.untilElementPresent("homepage.error_popup_search_client_homepage");
        elementShouldBePresent("homepage.error_popup_search_client_homepage");
        String errortext = findElement("homepage.error_popup_search_client_homepage").getText().trim();
        System.out.print(errortext);
        Assert.assertTrue(errortext.contains(errormessage));

    }

    @When("^I enter last name \"([^\"]*)\" in the input field$")
    public void i_enter_last_name_something_in_the_input_field(String lastname) throws Throwable {
        TextBoxes.typeTextbox("homepage.last_name_input_homepage_search", lastname);


    }

    @And("^I enter zip code \"([^\"]*)\" in the input field$")
    public void i_enter_zip_code_something_in_the_input_field(String zipcode) throws Throwable {
        TextBoxes.typeTextbox("homepage.zip_input_homepage_search", zipcode);

    }

    @And("^I erase first and last name$")
    public void i_erase_first_and_last_name() throws Throwable {
        findElement("homepage.first_name_input_homepage_search").clear();
        findElement("homepage.last_name_input_homepage_search").clear();
    }

    @When("^I click on ok button from error popup search client$")
    public void i_click_on_ok_button_from_error_popup_search_client() throws Throwable {
        Wait.untilElementPresent("homepage.ok_button_error_popup_search_client");
        Clicks.click("homepage.ok_button_error_popup_search_client");
    }

    @Then("^the search result page is displayed with the following columns:$")
    public void the_search_result_page_is_displayed_with_the_following_columns(List<String> column) throws
            Throwable {
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
                case "My Relationship":
                    assertTrue(column1.contains(aColumn));
                    break;
                case "Relationship w/other Associate":
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

    @And("^the searched client is displayed in the search results page \"([^\"]*)\"$")
    public void the_searched_client_is_displayed_in_the_search_results_page_something(String searchedclient) throws
            Throwable {
        Wait.untilElementPresent("homepage.search_results_page");
        elementShouldBePresent("homepage.search_results_page");
        String client = findElement("homepage.search_results_page").getText();
        System.out.println(client);
        Assert.assertTrue(client.contains(searchedclient));

    }

    @Then("^the search client no results page is displayed$")
    public void the_search_client_no_results_page_is_displayed() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.no_results_search_page");
        elementShouldBePresent("homepage.no_results_search_page");

    }


    @When("^I click on the searched client \"([^\"]*)\"$")
    public void i_click_on_the_searched_client_something(String name) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        WebElement clientName = findElement(By.linkText(name));
        Clicks.click(clientName);

    }

    @Then("^the Create new Client page is displayed$")
    public void the_create_new_client_page_is_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.create_new_client_page");
        elementShouldBePresent("homepage.create_new_client_page");

    }

    @Then("^error popup saying that phone number is invalid is displayed \"([^\"]*)\"$")
    public void error_popup_saying_that_phone_number_is_invalid_is_displayed_something(String errormessage) throws
            Throwable {
        Wait.untilElementPresent("homepage.error_popup_phone_homepage");
        elementShouldBePresent("homepage.error_popup_phone_homepage");
        String errortext = findElement("homepage.error_popup_phone_homepage").getText().trim();
        System.out.print(errortext);
        Assert.assertTrue(errortext.contains(errormessage));
    }

    @Then("^error popup saying that phone number is required is displayed \"([^\"]*)\"$")
    public void error_popup_saying_that_phone_number_is_required_is_displayed_something(String errormessage1) throws
            Throwable {
        Wait.untilElementPresent("homepage.error_popup_phone_homepage");
        elementShouldBePresent("homepage.error_popup_phone_homepage");
        String errortext = findElement("homepage.error_popup_phone_homepage").getText().trim();
        System.out.print(errortext);
        Assert.assertTrue(errortext.contains(errormessage1));
    }

    @And("^we input the preferred number \"([^\"]*)\" in the TELEPHONE field$")
    public void we_input_the_preferred_number_something_in_the_telephone_field(String phoneNumber) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        TextBoxes.typeTextbox("homepage.telephone_textbox", phoneNumber);

    }

    @When("^I hover over exclamation mark HINTS and NOTES links are displayed$")
    public void i_hover_over_exclamation_mark_hints_and_notes_links_are_displayed() throws Throwable {
        Clicks.hoverForSelection("homepage.exclamation_sign_client_profile");
        Thread.sleep(2000);
        String hints = findElement("homepage.hints_section_after_hover_exclamation").getText();
        System.out.println(hints);
        Assert.assertTrue(hints.contains("Hints"));
        String notes = findElement("homepage.notes_section_after_hover_exclamation").getText();
        System.out.println(notes);
        Assert.assertTrue(notes.contains("Notes"));

    }

    @When("^I click on HINTS and NOTES tab from Client Profile$")
    public void i_click_on_hints_and_notes_tab_from_client_profile() throws Throwable {
        Clicks.hoverForSelection("homepage.manage_clients_tab");
        Wait.untilElementPresent("homepage.hints_and_notes_tab");
        Clicks.click("homepage.hints_and_notes_tab");
    }

    @Then("^HINTS and NOTES page is displayed$")
    public void hints_and_notes_page_is_displayed() throws Throwable {
        Wait.untilElementPresent("homepage.hints_and_notes_page");
        elementShouldBePresent("homepage.hints_and_notes_page");
    }


    @And("^hints section has \"([^\"]*)\" title$")
    public void hints_section_has_something_title(String hintsTitle) throws Throwable {
        Wait.untilElementPresent("homepage.hints_section_title");
        String hints = findElement("homepage.hints_section_title").getText();
        System.out.print(hints);
        Assert.assertTrue(hints.contains(hintsTitle));


    }

    @And("^notes section has \"([^\"]*)\" title$")
    public void notes_section_has_something_title(String notesTitle) throws Throwable {
        Wait.untilElementPresent("homepage.notes_section_title");
        String notes = findElement("homepage.notes_section_title").getText();
        System.out.print(notes);
        Assert.assertTrue(notes.contains(notesTitle));
    }

    @When("^I enter \"([^\"]*)\" in the hint Textbox$")
    public void iEnterInTheTextbox(String hintText) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_hint");
        TextBoxes.typeTextbox("homepage.add_new_client_hint", hintText);
    }

    @And("^I select the phone radio button as the preferred contact method$")
    public void iSelectThePhoneRadioButtonAsThePreferredContactMethod() throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_preff_phone");
        Clicks.click("homepage.add_new_client_preff_phone");
    }

    @When("^I click the Add to Book button from search results$")
    public void i_click_the_add_to_book_button_from_search_results() throws Throwable {
        Wait.untilElementPresent("homepage.add_to_book");
        Clicks.click("homepage.add_to_book");
        Thread.sleep(1000);
    }

    @And("^I click save on the create new client page$")
    public void iClickSaveOnTheCreateNewClientPage() throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_save");
        Clicks.click("homepage.add_new_client_save");
        Wait.forPageReady();
    }

    @Then("^I click ok on the popup screen$")
    public void iClickOnThePopupScreen() throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_ok");
        Clicks.click("homepage.add_new_client_ok");
        Wait.forPageReady();
    }

    @And("^I click on the remove from book button$")
    public void iClickOnTheRemoveFromBookButton() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.secondsUntilElementPresent("homepage.remove_client_from_book", 10);
        Clicks.click("homepage.remove_client_from_book");
    }

    @Then("^I click ok on the remove client popup screen$")
    public void iClickOkOnTheRemoveClientPopupScreen() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.remove_from_book_ok", 10);
        Clicks.click("homepage.remove_from_book_ok");
        Wait.forPageReady();
    }

    @When("^I click here to create a new client$")
    public void iClickHereToCreateANewClient() throws Throwable {
        Wait.untilElementPresent("homepage.here_link");
        Clicks.click("homepage.here_link");
        Wait.forPageReady();
    }

    @And("^Preferred Information section is not displayed$")
    public void preferredInformationSectionIsNotDisplayed() throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_container");
        String sectionHeader = findElement("homepage.manage_client_container").getText();
        if (sectionHeader.contains("Preferred Information:")) {
            Assert.fail("Preferred info is displayed and it should not be");
        }
        assertTrue(sectionHeader.contains("Primary Information:"));
    }

    @And("^Additional Information section is not displayed$")
    public void additionalInformationSectionIsNotDisplayed() throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_container");
        String sectionHeader = findElement("homepage.manage_client_container").getText();
        if (sectionHeader.contains("Additional Information:")) {
            Assert.fail("Additional info is displayed and it should not be");
        }
    }


    @And("^the Add to Book button is displayed on the search results page$")
    public void theAddToBookButtonIsDisplayedOnTheSearchResultsPage() throws Throwable {
        Wait.untilElementPresent("homepage.add_to_book");
        elementShouldBePresent("homepage.add_to_book");

    }

    @And("^I should see the the following Address Information on the search results$")
    public void i_should_see_the_the_following_address_information_on_the_search_results(DataTable info) throws
            Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.search_results_page");
        elementShouldBePresent("homepage.search_results_page");
        String firstEntryInfo = findElement("homepage.search_results_page_first_client_entry").getText();
        System.out.println(firstEntryInfo);
        for (List<String> list : info.raw()) {
            String infoType = list.get(1);
            String infoValue = list.get(0);
            switch (infoType) {
                case "Address":
                    assertTrue(firstEntryInfo.contains(infoValue));
                    break;
                case "City":
                    assertTrue(firstEntryInfo.contains(infoValue));
                    break;
                case "State":
                    assertTrue(firstEntryInfo.contains(infoValue));
                    break;
                case "Zip":
                    assertTrue(firstEntryInfo.contains(infoValue));
                    break;
                case "Phone":
                    assertTrue(firstEntryInfo.contains(infoValue));
                    break;
                default:
                    fail("NOT a valid entry");
            }
        }
    }

    @And("^I should see the Create Client page prepopulated with the following Address Information$")
    public void i_should_see_the_create_client_page_prepopulated_with_the_following_address_information(DataTable
                                                                                                                info) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.create_new_client_page");
        elementShouldBePresent("homepage.create_new_client_page");
        for (List<String> list : info.raw()) {
            String infoType = list.get(1);
            String infoValue = list.get(0);
            switch (infoType) {
                case "Address":
                    String address = findElement("homepage.add_new_client_address").getAttribute("value");
                    System.out.println(address);
                    assertTrue(address.contains(infoValue));
                    break;
                case "City":
                    String city = findElement("homepage.add_new_client_city").getAttribute("value");
                    System.out.println(city);
                    assertTrue(city.contains(infoValue));
                    break;
                case "State":
                    String state = findElement("homepage.add_new_client_state").getAttribute("value");
                    System.out.println(state);
                    assertTrue(state.contains(infoValue));
                    break;
                case "Zip":
                    String zip = findElement("homepage.add_new_client_zip").getAttribute("value");
                    System.out.println(zip);
                    assertTrue(zip.contains(infoValue));
                    break;
                case "Phone":
                    String phone = findElement("homepage.add_new_client_phone").getAttribute("value");
                    System.out.println(phone);
                    assertTrue(phone.contains(infoValue));
                    break;
                default:
                    fail("NOT a valid entry");
            }
        }

    }

    @And("^I check the My To DOs count$")
    public void iCheckTheMyToDOsCount() throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_count_homepage");
        elementShouldBePresent("homepage.my_to_dos_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.my_to_dos_count_homepage"), " ");
        countToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]);
        System.out.println(countToDos);

    }

    @And("^I will validate that the My ToDos count incremented by 1$")
    public void i_will_validate_that_the_my_todos_count_incremented_by_1() throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_count_homepage");
        elementShouldBePresent("homepage.my_to_dos_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.my_to_dos_count_homepage"), " ");
        int newcountToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]); //grab the element
        Assert.assertEquals(countToDos + 1, newcountToDos);
        System.out.println(newcountToDos);


    }

    @When("^I click on the Create To Do button on MY TO DOS page$")
    public void iClickOnTheCreateToDoButtonOnMYTODOSPage() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.create_to_dos_my_to_dos");
        elementShouldBePresent("homepage.create_to_dos_my_to_dos");
        Clicks.click("homepage.create_to_dos_my_to_dos");
    }

    @And("^I select a valid date in Create To Dos View on MY TO DOS page$")
    public void iSelectAValidDateInCreateToDosViewOnMYTODOSPage() throws Throwable {

        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("d");
//        LocalDate date2 = LocalDate.now().plusDays(1);
        LocalDate date2 = LocalDate.now();
        String day = dateFormat2.format(date2);
        WebElement dateButton = findElement(By.id("datepicker"));
        Clicks.click(dateButton);

        WebElement dateWidget = findElement(By.id("ui-datepicker-div"));
        Thread.sleep(1000);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        //comparing the text of cell with today's date and clicking it.
        for (WebElement cell : columns) {
            if (cell.getText().equals(day)) {
                cell.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    @And("^I update the date in Create To Dos View on MY TO DOS page$")
    public void iUpdateTheDateInCreateToDosViewOnMYTODOSPage() throws Throwable {

        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("d");
        LocalDate date2 = LocalDate.now().plusDays(1);
        String day = dateFormat2.format(date2);


        WebElement dateButton = findElement(By.id("datepicker"));
        Clicks.click(dateButton);

        WebElement dateWidget = findElement(By.id("ui-datepicker-div"));
        Thread.sleep(1000);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        //comparing the text of cell with today's date and clicking it.
        for (WebElement cell : columns) {
            if (cell.getText().equals(day)) {
                cell.click();
                Thread.sleep(4000);
                break;
            }
        }
    }

    @Then("^the Primary Info section section is not displayed$")
    public void thePrimaryInfoSectionSectionIsNotDisplayed() throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_container");
        String sectionHeader = findElement("homepage.manage_client_container").getText();
        System.out.println(sectionHeader);
        if (sectionHeader.contains("Primary Information:")) {
            Assert.fail("Primary info is displayed and it should not be");
        }
    }

    @And("^I navigate until a employee client is displayed$")
    public void iNavigateUntilAEmployeeClientIsDisplayed() throws Throwable {

        if (!elementPresent("my_clients.is_employee_flag_my_clients")) {
            Clicks.click("my_clients.arrow_next_my_clients");
            iNavigateUntilAEmployeeClientIsDisplayed();
        } else {
            Assert.assertTrue("Employee flag is displayed", true);
            System.out.println("Employee flag is displayed");

        }
    }

    @Then("^I should see the MY TO DOS page$")
    public void iShouldSeeTheMYTODOSPage() throws Throwable {
//        the method is ok, but lets use in this kind of methods the "Wait.untilElementNotPresent" for the loading spinner, just to be sure that it doesn't try to perform an action before the loading spinner disappears
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_to_dos_page");
        elementShouldBePresent("homepage.my_to_dos_page");
    }

    @Then("^I should see the input boxes to create a to do$")
    public void iShouldSeeTheInputBoxesToCreateAToDo(List<String> field) throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_container_new");
        for (String afield : field) {
            switch (afield) {
                case "TITLE":
                    Elements.elementShouldBePresent("homepage.create_to_dos_title_field");
                    WebElement titleLabel = findElement("homepage.create_to_do_title_lable");
                    Assert.assertTrue(titleLabel.getText().contains("Title"));
                    break;
                case "DATE":
                    Elements.elementShouldBePresent("homepage.create_to_dos_date_field");
                    WebElement dateLabel = findElement("homepage.create_to_dos_date_label");
                    Assert.assertTrue(dateLabel.getText().contains("Due by Date"));
                    break;
                case "CLIENT":
                    Elements.elementShouldBePresent("homepage.create_to_dos_client_field");
                    WebElement clientLabel = findElement("homepage.create_to_dos_client_label");
                    Assert.assertTrue(clientLabel.getText().contains("To Customer"));
                    break;
                case "DESCRIPTION":
                    Elements.elementShouldBePresent("homepage.create_to_dos_description_field");
                    WebElement desctLabel = findElement("homepage.create_to_dos_description_label");
                    Assert.assertTrue(desctLabel.getText().contains("Description"));
                    break;
                default:
                    fail("NOT a valid field");
            }
        }
    }

    @And("^I enter a title \"([^\"]*)\" in the input field on MY TO DOS page$")
    public void iEnterATitleInTheInputFieldOnMYTODOSPage(String TITLE) throws Throwable {
//        before a single action lets use the "Wait.untilElementPresent" to be sure that the element that we want to click is loaded in the backend
        Wait.untilElementPresent("homepage.create_to_dos_title_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_title_field", TITLE);
    }

    @And("^I enter a description \"([^\"]*)\" in the input field on MY TO DOS page$")
    public void iEnterADescriptionInTheInputFieldOnMYTODOSPage(String description) throws Throwable {
//        before a single action lets use the "Wait.untilElementPresent" to be sure that the element that we want to click is loaded in the backend
        Wait.untilElementPresent("homepage.create_to_dos_description_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_description_field", description);
    }

    @And("^I click on the TO DO Save button$")
    public void iClickOnTheTODOSaveButton() throws Throwable {
//        before a single action lets use the "Wait.untilElementPresent" to be sure that the element that we want to click is loaded in the backend
        Wait.untilElementPresent("homepage.create_to_do_save_button");
        Clicks.click("homepage.create_to_do_save_button");
//        Wait.forPageReady(); -> we found out that this method actually does nothing, so we should not use it anymore :)
    }

    @And("^I wait to the ToDo to be successfully processed$")
    public void iWaitToTheToDoToBeSuccessfullyProcessed() throws Throwable {
        Navigate.browserRefresh();
        Wait.untilElementNotPresent("homepage.loading_page");
        String Url = WebDriverManager.getWebDriver().getCurrentUrl();
        System.out.println("Current URL is: " + Url);
        Boolean inProgress = true;
        while (inProgress) {
            try {
                Wait.untilElementPresent("homepage.list_of_todos_my_macys_todo_tab");
                Wait.secondsUntilElementPresent("homepage.last_toDo_in_list", 10);
                WebElement toDoInProg = findElement("homepage.to_do_in_progress");
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

    @Then("^I should see the Credit Card information in Description field error message$")
    public void iShouldSeeTheCreditCardInformationInDescriptionFieldErrorMessage() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_pci_error");
        elementShouldBePresent("homepage.create_to_do_pci_error");
        String message = findElement("homepage.create_to_do_pci_error").getText().trim();
        Assert.assertEquals(message, "Credit Card information may not be entered into the Description field. Please try again.");

    }

    @When("^I select the OK button on the error message$")
    public void iSelectTheOKButtonOnTheErrorMessage() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_ok_button_pci_error");
        Clicks.click("homepage.create_to_do_ok_button_pci_error");

    }

    @When("^I remove the Title in the input field on MY TO DOS page$")
    public void iRemoveTheTitleInTheInputFieldOnMYTODOSPage() throws Throwable {
        findElement("homepage.create_to_dos_title_field").clear();
    }

    @Then("^the Save button becomes disabled$")
    public void theSaveButtonBecomesDisabled() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_disabled_save_button");
        elementShouldBePresent("homepage.create_to_do_disabled_save_button");

    }

    @Then("^I should see the Credit Card information in Title field error message$")
    public void iShouldSeeTheCreditCardInformationInTitleFieldErrorMessage() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_pci_error");
        elementShouldBePresent("homepage.create_to_do_pci_error");
        String message = findElement("homepage.create_to_do_pci_error").getText().trim();
        Assert.assertEquals(message, "Credit Card information may not be entered into the Title field. Please try again.");

    }

    @And("^I click the X button located on the top right corner of the To Do page$")
    public void iClickTheXButtonLocatedOnTheTopRightCornerOfTheToDoPage() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_x_backbutton");
        Clicks.click("homepage.create_to_do_x_backbutton");
    }

    @Then("^I should see the Disregard Changes message$")
    public void iShouldSeeTheDisregardChangesMessage() throws Throwable {
        Wait.untilElementPresent("homepage.disregard_changes_message");
        elementShouldBePresent("homepage.disregard_changes_message");
        String message = findElement("homepage.disregard_changes_message").getText().trim();
        Assert.assertTrue(message.contains("Disregard Changes"));

    }

    @When("^I select the Cancel button on the Disregard Changes error message$")
    public void iSelectTheCancelButtonOnTheDisregardChangesErrorMessage() throws Throwable {
        Wait.untilElementPresent("homepage.disregard_cancel_button");
        elementShouldBePresent("homepage.disregard_cancel_button");
        Clicks.click("homepage.disregard_cancel_button");

    }

    @When("^I select the OK button on the Disregard Changes error message$")
    public void iSelectTheOKButtonOnTheDisregardChangesErrorMessage() throws Throwable {
        Wait.untilElementPresent("homepage.disregard_changes_ok_button");
        elementShouldBePresent("homepage.disregard_changes_ok_button");
        Clicks.click("homepage.disregard_changes_ok_button");
    }

    @Then("^I will see the associates HOMEPAGE$")
    public void iWillSeeTheAssociatesHOMEPAGE() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.associate_homepage");
        elementShouldBePresent("homepage.associate_homepage");
    }


    @When("^I click on the Create To Do button on HOMEPAGE$")
    public void iClickOnTheCreateToDoButtonOnHOMEPAGE() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_button_homepage");
        elementShouldBePresent("homepage.create_to_do_button_homepage");
        Clicks.click("homepage.create_to_do_button_homepage");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @When("^I click on the Create To Do button on Client PROFILE page$")
    public void iClickOnTheCreateToDoButtonOnClientPROFILEPage() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.create_to_do_button_profile");
        elementShouldBePresent("homepage.create_to_do_button_profile");
        Clicks.click("homepage.create_to_do_button_profile");
    }

    @When("^I click on the Create To Do button on MY CLIENTS page$")
    public void iClickOnTheCreateToDoButtonOnMYCLIENTSPage() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_do_button_my_clients");
        elementShouldBePresent("homepage.create_to_do_button_my_clients");
        Clicks.click("homepage.create_to_do_button_my_clients");
    }

    @And("^I select the full name \"([^\"]*)\" from client dropdown MY TO DOS page$")
    public void iSelectTheFullNameFromClientDropdownMYTODOSPage(String NameClient) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.secondsUntilElementPresent(By.partialLinkText(NameClient), 20);
        WebElement fullname = Elements.findElement(By.partialLinkText(NameClient));
        Clicks.hoverForSelection(fullname);
        Clicks.click(fullname);
    }

    @And("^I select a Macys client from OCL website Homepage$")
    public void iSelectAMacysClientFromOCLWebsiteHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.client_list_homepage");
        Wait.untilElementPresent("homepage.first_client_on_homepage");
        firstClientHomepage = findElement("homepage.first_client_on_homepage").getText().trim();
        System.out.println(firstClientHomepage);
        Clicks.hoverForSelection("homepage.first_client_on_homepage");
        Clicks.click("homepage.first_client_on_homepage");
    }

    @Then("^I should see the Client Profile website page$")
    public void iShouldSeeTheClientProfileWebsitePage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.client_info_page");
        Elements.elementShouldBePresent("homepage.client_info_page");

    }

    @When("^I select as preferred method of contact \"([^\"]*)\" from Client Info tab$")
    public void iSelectAsPreferredMethodOfContactFromClientInfoTab(String method) throws Throwable {
        switch (method) {
            case "phone":
                Wait.untilElementPresent("homepage.client_profile_cont_meth_phone_radio");
                Clicks.click("homepage.client_profile_cont_meth_phone_radio");
                break;
            case "text":
                Wait.untilElementPresent("homepage.client_profile_cont_meth_text_radio");
                Clicks.click("homepage.client_profile_cont_meth_text_radio");
                break;
            case "email":
                Wait.untilElementPresent("homepage.client_profile_cont_meth_mail_radio");
                Clicks.click("homepage.client_profile_cont_meth_mail_radio");
                break;
        }
    }

    @Then("^I should see the preferred method of contact as \"([^\"]*)\" on the Client Info page$")
    public void iShouldSeeThePreferredMethodOfContactAsOnTheClientInfoPage(String method) throws Throwable {
        Wait.untilElementPresent("homepage.client_profile_black_task_bar");
        switch (method) {
            case "phone":
                Wait.untilElementPresent("homepage.contact_method_phone_icon");
                Elements.elementShouldBePresent("homepage.contact_method_phone_icon");
                break;
            case "text":
                Wait.untilElementPresent("homepage.contact_method_text_icon");
                Elements.elementShouldBePresent("homepage.contact_method_text_icon");
                break;
            case "email":
                Wait.untilElementPresent("homepage.contact_method_email_icon");
                Elements.elementShouldBePresent("homepage.contact_method_email_icon");
                break;
        }
    }

    @Then("^I should see the preferred method of contact as \"([^\"]*)\" on the Homepage$")
    public void iShouldSeeThePreferredMethodOfContactAsOnTheHomepage(String method) throws Throwable {
        Wait.untilElementPresent("homepage.client_list_homepage");
        switch (method) {
            case "phone":
                Wait.untilElementPresent("homepage.contact_method_icon_homepage");
                Elements.elementShouldBePresent("homepage.contact_method_icon_homepage");
                String contMeth1 = findElement("homepage.contact_method_icon_homepage").getAttribute("src");
                System.out.println(contMeth1);
                Assert.assertTrue(contMeth1.contains("iconPhoneSmall"));
                break;
            case "text":
                Wait.untilElementPresent("homepage.contact_method_icon_homepage");
                Elements.elementShouldBePresent("homepage.contact_method_icon_homepage");
                String contMeth2 = findElement("homepage.contact_method_icon_homepage").getAttribute("src");
                System.out.println(contMeth2);
                Assert.assertTrue(contMeth2.contains("iconTextMsgSmall"));
                break;
            case "email":
                Wait.untilElementPresent("homepage.contact_method_icon_homepage");
                Elements.elementShouldBePresent("homepage.contact_method_icon_homepage");
                String contMeth3 = findElement("homepage.contact_method_icon_homepage").getAttribute("src");
                System.out.println(contMeth3);
                Assert.assertTrue(contMeth3.contains("iconEmailSmall"));
                break;
        }
    }

    @Then("^I should see the preferred method of contact as \"([^\"]*)\" on the My Clients page$")
    public void iShouldSeeThePreferredMethodOfContactAsOnTheMyClientsPage(String method) throws Throwable {
        Wait.untilElementPresent("homepage.my_clients_container_mc_page");
        switch (method) {
            case "phone":
                Wait.untilElementPresent("homepage.contact_method_icon_myclients");
                Elements.elementShouldBePresent("homepage.contact_method_icon_myclients");
                String contMeth1 = findElement("homepage.contact_method_icon_myclients").getAttribute("src");
                System.out.println(contMeth1);
                Assert.assertTrue(contMeth1.contains("iconPhoneSmall"));
                break;
            case "text":
                Wait.untilElementPresent("homepage.contact_method_icon_myclients");
                Elements.elementShouldBePresent("homepage.contact_method_icon_myclients");
                String contMeth2 = findElement("homepage.contact_method_icon_myclients").getAttribute("src");
                System.out.println(contMeth2);
                Assert.assertTrue(contMeth2.contains("iconTextMsgSmall"));
                break;
            case "email":
                Wait.untilElementPresent("homepage.contact_method_icon_myclients");
                Elements.elementShouldBePresent("homepage.contact_method_icon_myclients");
                String contMeth3 = findElement("homepage.contact_method_icon_myclients").getAttribute("src");
                System.out.println(contMeth3);
                Assert.assertTrue(contMeth3.contains("iconEmailSmall"));
                break;
        }
    }

    @And("^I input client name \"([^\"]*)\" in the input filed from MY TO DOS page$")
    public void iInputClientNameInTheInputFiledFromMYTODOSPage(String ClientName) throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_client_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_client_field", ClientName);
    }


    @And("^I mark all TO DOs as completed MY TO DOS page$")
    public void iMarkAllTODOsAsCompletedMYTODOSPage() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.open_status_check_my_to_dos_page", 20);
        try {
            while (findElement("homepage.open_status_check_my_to_dos_page").isDisplayed()) {
                Wait.untilElementPresent("homepage.open_status_check_my_to_dos_page");
                Clicks.click("homepage.open_status_check_my_to_dos_page");
                Wait.untilElementNotPresent("homepage.loading_page");
            }
        } catch (Exception e) {
            System.out.println("All TODOs are marked as completed");
        }

    }

    @Then("^the new created TODO for client \"([^\"]*)\" is displayed first in list$")
    public void theNewCreatedTODOForClientIsDisplayedFirstInList(String ClientName) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.client_name_first_position_my_todos");
        String name = findElement("homepage.client_name_first_position_my_todos").getText();
        System.out.println(name);
        Assert.assertTrue(name.contains(ClientName));

    }

    @And("^for the first TO DO in list the employee flag is displayed next to the client name$")
    public void forTheFirstTODOInListTheEmployeeFlagIsDisplayedNextToTheClientName() throws Throwable {
        Wait.untilElementPresent("homepage.employee_flag_first_position_my_todos");
        elementShouldBePresent("homepage.employee_flag_first_position_my_todos");

    }

    @And("^I click on Personalize Clear To DO radio button$")
    public void iClickOnPersonalizeClearToDORadioButton() throws Throwable {
        Wait.untilElementPresent("homepage.personalize_clear_to_do_radio_button");
        Clicks.click("homepage.personalize_clear_to_do_radio_button");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^the following columns are displayed in Contact History view$")
    public void theFollowingColumnsAreDisplayedInContactHistoryView(List<String> column) throws Throwable {
        Wait.untilElementPresent("homepage.contact_history_section");
        for (String nameColumn : column) {
            switch (nameColumn) {
                case "Status":
                    Wait.secondsUntilElementPresent("homepage.status_header_contact_history", 2000);
                    Elements.elementShouldBePresent("homepage.status_header_contact_history");
                    break;
                case "Date":
                    Wait.untilElementPresent("homepage.date_header_contact_history");
                    Elements.elementShouldBePresent("homepage.date_header_contact_history");
                    break;
                case "Title":
                    Wait.untilElementPresent("homepage.title_header_contact_history");
                    Elements.elementShouldBePresent("homepage.title_header_contact_history");
                    break;
                case "Selling Associate":
                    if (bloomingdales()) {
                        Wait.untilElementPresent("homepage.sp_header_contact_history_blm");
                        Elements.elementShouldBePresent("homepage.sp_header_contact_history_blm");
                    } else {
                        Wait.untilElementPresent("homepage.selling_asoc_header_contact_history");
                        Elements.elementShouldBePresent("homepage.selling_asoc_header_contact_history");
                    }
                    break;
                default:
                    fail("Column is missing");
            }
        }

    }

    @And("^I click on Contact History tab from Client Profile page$")
    public void iClickOnContactHistoryTabFromClientProfilePage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.contact_history_tab");
        Clicks.click("homepage.contact_history_tab");

    }


    @And("^I enter \"([^\"]*)\" in the phone textbox field$")
    public void iEnterInThePhoneTextboxField(String value) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_phone");
        TextBoxes.typeTextbox("homepage.add_new_client_phone", value);

    }

    @Then("^the client name \"([^\"]*)\" is displayed in Client Profile page$")
    public void theClientNameIsDisplayedInClientProfilePage(String ClientName) throws Throwable {
        Wait.untilElementPresent("my_clients.client_name_profile_info_page");
        String NameFromProfile = findElement("my_clients.client_name_profile_info_page").getText();
        System.out.println(NameFromProfile);
        Assert.assertTrue(NameFromProfile.contains(ClientName));

    }

    @And("^the employee flag is displayed next to the client name Client Profile$")
    public void theEmployeeFlagIsDisplayedNextToTheClientNameClientProfile() throws Throwable {
        try {
            Wait.untilElementPresent("my_clients.is_employee_flag_my_clients");
            elementShouldBePresent("my_clients.is_employee_flag_my_clients");
        } catch (Exception e) {
            System.out.println("Employee flag is not displayed");

        }
    }


    @Then("^the To Do \"([^\"]*)\" is saved on the Associates MY TO DOS tab$")
    public void theToDoIsSavedOnTheAssociatesMYTODOSTab(String todo) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.first_in_list_todo_title_my_to_dos");
        String ToDoTitle = findElement("homepage.first_in_list_todo_title_my_to_dos").getText();
        System.out.println(ToDoTitle);
        Assert.assertEquals(todo, ToDoTitle);
    }

    @And("^I select the checkbox of the first client from the HOMEPAGE clients list$")
    public void iSelectTheCheckboxOfTheFirstClientFromTheHOMEPAGEClientsList() throws Throwable {
        Wait.untilElementPresent("homepage.first_checkbox_client_list_homepage");
        Clicks.click("homepage.first_checkbox_client_list_homepage");
    }

    @And("^I select the checkbox of the first client from the MY CLIENTS page$")
    public void iSelectTheCheckboxOfTheFirstClientFromTheMYCLIENTSPage() throws Throwable {
        Wait.untilElementPresent("homepage.first_checkbox_client_my_clients_page");
        Clicks.click("homepage.first_checkbox_client_my_clients_page");
    }

    @When("^I click on the Chevron on the Associates MY TO DOS page$")
    public void iClickOnTheChevronOnTheAssociatesMYTODOSPage() throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_chevron");
        Clicks.click("homepage.my_to_dos_chevron");

    }

    @And("^I click on the Edit button from the TO DO$")
    public void iClickOnTheEditButtonFromTheTODO() throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_edit");
        Clicks.click("homepage.my_to_dos_edit");
    }

    @Then("^the To Do \"([^\"]*)\" is saved on the Clients MY TO DOS tab$")
    public void theToDoIsSavedOnTheClientsMYTODOSTab(String todo) throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("homepage.first_in_list_to_do_title_client_to_dos");
        String ToDoTitle = findElement("homepage.first_in_list_to_do_title_client_to_dos").getText();
        System.out.println(ToDoTitle);
        Assert.assertEquals(todo, ToDoTitle);
    }

    @And("^I select a valid date in Create To Dos View on Client MY TO DOS page$")
    public void iSelectAValidDateInCreateToDosViewOnClientMYTODOSPage() throws Throwable {
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("d");
        LocalDate date2 = LocalDate.now().plusDays(1);
        String day = dateFormat2.format(date2);


        WebElement dateButton = findElement(By.id("custdatepicker"));
        Clicks.click(dateButton);

        WebElement dateWidget = findElement(By.id("ui-datepicker-div"));
        Thread.sleep(1000);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        //comparing the text of cell with today's date and clicking it.
        for (WebElement cell : columns) {
            if (cell.getText().equals(day)) {
                cell.click();
                Thread.sleep(4000);
                break;
            }
        }
    }

    @Then("^I should see the input boxes to create a to do on Clients MY TO DOS tab$")
    public void iShouldSeeTheInputBoxesToCreateAToDoOnClientsMYTODOSTab(List<String> field) throws Throwable {
        Wait.untilElementPresent("homepage.client_create_to_do_form");
        for (String afield : field) {
            switch (afield) {
                case "TITLE":
                    Elements.elementShouldBePresent("homepage.create_to_dos_title_field");
                    break;
                case "DATE":
                    Elements.elementShouldBePresent("homepage.create_to_dos_date_field");
                    break;
                case "CLIENT":
                    Elements.elementShouldBePresent("homepage.create_to_dos_client_field");
                    break;
                case "DESCRIPTION":
                    Elements.elementShouldBePresent("homepage.create_to_dos_description_field");
                    break;
                default:
                    fail("NOT a valid field");
            }
        }
    }

    @And("^I click on the TO DO Save button on Clients MY TO DOS tab$")
    public void iClickOnTheTODOSaveButtonOnClientsMYTODOSTab() throws Throwable {
        Wait.untilElementPresent("homepage.client_create_to_do_form_save_button");
        Clicks.click("homepage.client_create_to_do_form_save_button");
    }


    @And("^I enter a title \"([^\"]*)\" in the input field on edit MY TO DOS page$")
    public void iEnterATitleInTheInputFieldOnEditMYTODOSPage(String TITLE) throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_edit_title");
        TextBoxes.typeTextbox("homepage.my_to_dos_edit_title", TITLE);
    }

    @And("^I enter a description \"([^\"]*)\" in the input field on edit MY TO DOS page$")
    public void iEnterADescriptionInTheInputFieldOnEditMYTODOSPage(String description) throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_edit_description");
        TextBoxes.typeTextbox("homepage.my_to_dos_edit_description", description);
    }

    @And("^I select a valid date in Edit To Dos View on MY TO DOS page$")
    public void iSelectAValidDateInEditToDosViewOnMYTODOSPage() throws Throwable {
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("d");
        LocalDate date2 = LocalDate.now().plusDays(1);
        String day = dateFormat2.format(date2);


        WebElement dateButton = findElement("homepage.my_do_dos_edit_calendar");
        Clicks.click(dateButton);

        WebElement dateWidget = findElement(By.id("ui-datepicker-div"));
        Thread.sleep(1000);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

        //comparing the text of cell with today's date and clicking it.
        for (WebElement cell : columns) {
            if (cell.getText().equals(day)) {
                cell.click();
                Thread.sleep(4000);
                break;
            }
        }
    }

    @And("^I click on the EDIT TO DO Save button$")
    public void iClickOnTheEDITTODOSaveButton() throws Throwable {
        Wait.untilElementPresent("homepage.my_to_dos_edit_save_button");
        Clicks.click("homepage.my_to_dos_edit_save_button");
    }

    @Then("^Star Rewards section is displayed in main customer profile page$")
    public void starRewardsSectionIsDisplayedInMainCustomerProfilePage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("my_clients.stars_rewards_section");
        elementShouldBePresent("my_clients.stars_rewards_section");
    }

    @And("^the plus sign is displayed in the left of Star Rewards section$")
    public void thePlusSignIsDisplayedInTheLeftOfStarRewardsSection() throws Throwable {
        Wait.untilElementPresent("my_clients.plus_sign_star_rewards_section");
        elementShouldBePresent("my_clients.plus_sign_star_rewards_section");
    }

    @And("^Star Rewards section contains the following components$")
    public void starRewardsSectionContainsTheFollowingComponents(List<String> column) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("my_clients.star_rewards_info_table");
        elementShouldBePresent("my_clients.star_rewards_info_table");
        String loyalList = findElement("my_clients.star_rewards_info_table").getText();
        System.out.println(loyalList);
        for (String columnSection : column) {
            switch (columnSection) {
                case "Loyalty Tier":
                    Wait.untilElementPresent("my_clients.loyalty_tier_star_rewards_section");
                    Elements.elementShouldBePresent("my_clients.loyalty_tier_star_rewards_section");
                    break;
                case "Loyalty ID":
                    Wait.untilElementPresent("my_clients.loyalty_id_star_rewards_section");
                    Elements.elementShouldBePresent("my_clients.loyalty_id_star_rewards_section");
                    break;
                case "Current Points":
                    Wait.untilElementPresent("my_clients.current_points_star_rewards_section");
                    Elements.elementShouldBePresent("my_clients.current_points_star_rewards_section");
                    break;
                case "Pending Points":
                    Wait.untilElementPresent("my_clients.pending_points_star_rewards_section");
                    Elements.elementShouldBePresent("my_clients.pending_points_star_rewards_section");
                    break;
                case "Points needed to get to next Reward":
                    Wait.untilElementPresent("my_clients.points_to_next_reward_star_rewards_section");
                    Elements.elementShouldBePresent("my_clients.points_to_next_reward_star_rewards_section");
                    break;
                default:
                    fail("Section is missing");
            }
        }
    }

    @Then("^the newly completed TO DO is displayed first in list CONTACT HISTORY \"([^\"]*)\"$")
    public void theNewlyCompletedTODOIsDisplayedFirstInListCONTACTHISTORY(String ToDoName) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.secondsUntilElementPresent("my_clients.title_of_the_first_todo_in_list_contact_history", 5000);
        elementShouldBePresent("my_clients.title_of_the_first_todo_in_list_contact_history");
        String ToDoTitle = findElement("my_clients.title_of_the_first_todo_in_list_contact_history").getText();
        System.out.println(ToDoTitle);
        assertTrue(ToDoName.contains(ToDoTitle));
    }


    @And("^the STATUS of the newly completed TO DO is displayed as COMPLETED$")
    public void theSTATUSOfTheNewlyCompletedTODOIsDisplayedAsCOMPLETED() throws Throwable {
        Wait.untilElementPresent("my_clients.complete_status_of_first_todo_contact_history");
        elementShouldBePresent("my_clients.complete_status_of_first_todo_contact_history");
        String completedStatus = findElement("my_clients.complete_status_of_first_todo_contact_history").getAttribute("title");
        System.out.println(completedStatus);
        assertTrue(completedStatus.contains("Complete"));


    }

    @And("^the DATE is displayed in the proper format MY TO DOS page$")
    public void theDATEIsDisplayedInTheProperFormatMYTODOSPage() throws Throwable {
        String dateFormat = findElement("my_clients.date_format_contact_history_first_in_list").getText();
        System.out.println(dateFormat);
        assertTrue(OmniclientUtils.isDateValid(dateFormat, DATE_TIME_FORMATTER));

    }

    @And("^I enter \"([^\"]*)\" in the Address Line 1 field$")
    public void iEnterInTheAddressLine1Field(String value) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_address");
        TextBoxes.typeTextbox("homepage.add_new_client_address", value);
    }

    @Then("^I should see the ToDos section on the Macys Homepage$")
    public void iShouldSeeTheToDosSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_todos_section");
        Elements.elementShouldBePresent("homepage.homepage_todos_section");
        String toDoheader = findElement("homepage.homepage_todos_section_header").getText();
        Assert.assertTrue(toDoheader.contains("TODAY'S TOP 15 TO DOS"));
    }

    @When("^I click the HERE button under the ToDos section on the Macys Homepage$")
    public void iClickTheHEREButtonUnderTheToDosSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_todos_here_button");
        Clicks.click("homepage.homepage_todos_here_button");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^I should see the list of ToDos under the ToDos section on the Macys Homepage$")
    public void iShouldSeeTheListOfToDosUnderTheToDosSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_todos_list");
        Elements.elementShouldBePresent("homepage.homepage_todos_list");
    }

    @When("^I select the first ToDo from the ToDos section on the Macys Homepage$")
    public void iSelectTheFirstToDoFromTheToDosSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_todos_first_todo_check");
        Clicks.click("homepage.homepage_todos_first_todo_check");
    }

    @And("^I click the Print button from the ToDos section on the Macys Homepage$")
    public void iClickThePrintButtonFrtomTheToDosSectionOnTheMacysHomepage() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("homepage.homepage_todos_print_button");
        Clicks.click("homepage.homepage_todos_print_button");
    }

    @Then("^I should see the ToDos Print Overlay$")
    public void iShouldSeeTheToDosPrintOverlay() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        ArrayList<String> tabs2 = new ArrayList<>(WebDriverManager.getWebDriver().getWindowHandles());
        WebDriverManager.getWebDriver().switchTo().window(tabs2.get(1));
        String URL = WebDriverManager.getCurrentUrl();
        System.out.println("Current URL is: " + URL);
        Wait.forPageReady();
        Assert.assertTrue(URL.contains("autoPrintToDos") || URL.contains("toDos"));
        Wait.untilElementPresent("homepage.print_todos_page");
        Elements.elementShouldBePresent("homepage.print_todos_page");
        Wait.untilElementPresent("homepage.print_todos_header");
        Elements.elementShouldBePresent("homepage.print_todos_header");

    }

    @When("^I select all ToDos from the ToDos section on the Macys Homepage$")
    public void iSelectAllToDosFromTheToDosSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_todos_all_todos_check");
        Clicks.click("homepage.homepage_todos_all_todos_check");
    }

    @When("^I close the Print Overlay window$")
    public void iCloseThePrintOverlayWindow() throws Throwable {
        WebDriverManager.getWebDriver().close();
        ArrayList<String> tabs2 = new ArrayList<>(WebDriverManager.getWebDriver().getWindowHandles());
        WebDriverManager.getWebDriver().switchTo().window(tabs2.get(0));
    }

    @And("^the TITLE is truncated to allow maximum twenty characters MY TO DOS page$")
    public void theTITLEIsTruncatedToAllowMaximumTwentyCharactersMYTODOSPage() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("my_clients.title_of_the_first_todo_in_list_contact_history");
        elementShouldBePresent("my_clients.title_of_the_first_todo_in_list_contact_history");
        String ToDoTitle = findElement("my_clients.title_of_the_first_todo_in_list_contact_history").getText();
        System.out.println(ToDoTitle);
        int lengthTitle = ToDoTitle.length();
        System.out.println(lengthTitle);
        assertEquals(lengthTitle, 20);


    }

    @And("^I select \"([^\"]*)\" from the state dropdown$")
    public void iSelectFromTheStateDropdown(String state) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_state");
        DropDowns.selectByText("homepage.add_new_client_state", state);
    }

    @And("^the NAME of the SA \"([^\"]*)\" that created the TO DO is displayed MY TO DOS page$")
    public void theNAMEOfTheSAThatCreatedTheTODOIsDisplayedMYTODOSPage(String saName) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("my_clients.sa_name_contact_history_tab_first_in_list");
        String saNameListed = findElement("my_clients.sa_name_contact_history_tab_first_in_list").getText();
        System.out.println(saNameListed);
        assertEquals(saName, saNameListed);

    }


    @Then("^maximum six TODOs are displayed in CONTACT HISTORY view$")
    public void maximumSixTODOsAreDisplayedInCONTACTHISTORYView() throws Throwable {
        List<WebElement> toDos = findElements("my_clients.total_list_of_todos_by_title_contact_history_tab");
        System.out.println(toDos.size());
        assertEquals(6, toDos.size());

    }


    @And("^I enter \"([^\"]*)\" as the hint$")
    public void iEnterAsTheHint(String value) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_hint");
        TextBoxes.typeTextbox("homepage.add_new_client_hint", value);
    }

    @And("^I select the text radio button as the preferred contact method$")
    public void iSelectTheTextRadioButtonAsThePreferredContactMethod() throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_preff_text");
        Clicks.click("homepage.add_new_client_preff_text");
    }

    @And("^I enter \"([^\"]*)\" as the zip code$")
    public void iEnterAsTheZipCode(String value) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_zip");
        TextBoxes.typeTextbox("homepage.add_new_client_zip", value);
    }

    @And("^I enter the city of \"([^\"]*)\" in the city field$")
    public void iEnterTheCityOfInTheCityField(String value) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_city");
        TextBoxes.typeTextbox("homepage.add_new_client_city", value);
    }

    @And("^I click on Top 100 tab$")
    public void iClickOnTopTab() throws Throwable {
        Wait.untilElementPresent("homepage.general_manager_homepage");
        Clicks.click("homepage.general_manager_homepage");
    }

    @Then("^I should see the Clients section on the Macys Homepage$")
    public void iShouldSeeTheClientsSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.client_list_homepage");
        Elements.elementShouldBePresent("homepage.client_list_homepage");
        String clientHeader = findElement("homepage.homepage_clients_section_header").getText();
        Assert.assertTrue(clientHeader.contains("MY CLIENTS"));
    }

    @Then("^I should see the Top 100 screen$")
    public void iShouldSeeTheTopScreen() throws Throwable {
        Wait.untilElementPresent("homepage.top_100_xpath");
        elementShouldBePresent("homepage.top_100_xpath");
    }

    @When("^I select the first Client from the Clients section on the Macys Homepage$")
    public void iSelectTheFirstClientFromTheClientsSectionOnTheMacysHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_clients_first_todo_check");
        Clicks.click("homepage.homepage_clients_first_todo_check");
    }

    @And("^I click the Print button from the Clients section on the Macys Homepage$")
    public void iClickThePrintButtonFromTheClientsSectionOnTheMacysHomepage() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("homepage.homepage_clients_print_button");
        Clicks.click("homepage.homepage_clients_print_button");
    }

    // The UI freezes here, the loading spinner gets stuck on screen, that's the reason for the sleep
    @Then("^I should see the Client Card Print Overlay$")
    public void iShouldSeeTheClientCardPrintOverlay() throws Throwable {
        ArrayList<String> tabs = new ArrayList<>(WebDriverManager.getWebDriver().getWindowHandles());
        while (tabs.size() < 2) {
            Thread.sleep(1000);
            tabs = new ArrayList<>(WebDriverManager.getWebDriver().getWindowHandles());
        }
        WebDriverManager.getWebDriver().switchTo().window(tabs.get(1));
        String URL = WebDriverManager.getCurrentUrl();
        System.out.println("Current URL is: " + URL);
        Assert.assertTrue(URL.contains("noteCard") || URL.contains("chrome://print/"));
        Wait.forPageReady();
        if (URL.contains("chrome://print/")) {
            WebElement chromePreview = findElement(By.id("print-preview"));
            Elements.elementShouldBePresent(chromePreview);
        } else {
            Wait.untilElementPresent("homepage.print_client_page");
            Elements.elementShouldBePresent("homepage.print_client_page");
            Wait.untilElementPresent("homepage.print_client_header");
            Elements.elementShouldBePresent("homepage.print_client_header");
        }
    }

    @And("^I select all Clients from the Clients section on Homepage$")
    public void iSelectAllClientsFromTheClientsSectionOnHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.homepage_clients_all_todos_check");
        Clicks.click("homepage.homepage_clients_all_todos_check");
    }

    @When("^I select the first Todo from the My ToDos tab$")
    public void iSelectTheFirstTodoFromTheMyToDosTab() throws Throwable {
        Wait.untilElementPresent("homepage.my_todos_first_todo_check");
        Clicks.click("homepage.my_todos_first_todo_check");
    }

    @And("^I click the Print button from the My ToDos tab$")
    public void iClickThePrintButtonFromTheMyToDosTab() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("homepage.my_todos_print_button");
        Clicks.click("homepage.my_todos_print_button");
    }

    @And("^I select all ToDos from the My ToDos tab$")
    public void iSelectAllToDosFromTheMyToDosTab() throws Throwable {
        Wait.untilElementPresent("homepage.my_todos_all_todos_check");
        Clicks.click("homepage.my_todos_all_todos_check");
        if (!findElement("homepage.my_todos_print_button").isEnabled()) {
            Clicks.click("homepage.my_todos_all_todos_check");
        }
    }

    @Then("^I should see the Macys My Clients page$")
    public void iShouldSeeTheMacysMyClientsPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_clients_container");
        Elements.elementShouldBePresent("homepage.my_clients_container");
    }

    @When("^I select the first Client from the Macys My Clients page$")
    public void iSelectTheFirstClientFromTheMacysMyClientsPage() throws Throwable {
        Wait.untilElementPresent("homepage.my_clients_first_todo_check");
        Clicks.click("homepage.my_clients_first_todo_check");
    }

    @And("^I click the Print button from the Macys My Clients page$")
    public void iClickThePrintButtonFromTheMacysMyClientsPage() throws Throwable {
        Wait.untilElementPresent("homepage.my_clients_print_button");
        Clicks.click("homepage.my_clients_print_button");
    }

    @And("^I select all Clients from the Macys My Clients page$")
    public void iSelectAllClientsFromTheMacysMyClientsPage() throws Throwable {
        Wait.untilElementPresent("homepage.my_clients_all_todos_check");
        Clicks.click("homepage.my_clients_all_todos_check");
    }

    @When("^I click the Print button from the Client Profile page$")
    public void iClickThePrintButtonFromTheClientProfilePage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_clients_print_button");
        Clicks.click("homepage.my_clients_print_button");
    }

    @And("^I should see the Star Rewards Tier Icons on Unassigned Clients screen$")
    public void iShouldSeeTheStarRewardsTierIconsOnUnassignedClientsScreen(DataTable loyal) throws Throwable {
        Wait.secondsUntilElementPresent("homepage.manage_rel_1st_sa_header", 10);
        Clicks.hoverForSelection("homepage.manage_rel_women_shoes_expand");
        Clicks.click("homepage.manage_rel_women_shoes_expand");
        Wait.untilElementNotPresent("homepage.loading_page");
        i_select_an_associate_from_select_selling_associate_drop_down("FIFTY ONE");
        Wait.untilElementNotPresent("homepage.loading_page");
        for (List<String> list : loyal.raw()) {
            String loyalIcon = list.get(0);
            String loyalClient = list.get(1);
            switch (loyalIcon) {
                case "gold":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_gold");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_gold");
                    break;
                case "silver":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_silver");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_silver");
                    break;
                case "platinum":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_platinum");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_platinum");
                    break;
                case "non-loyalty":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    try {
                        if (findElement("homepage.loyalty_any_icon").isDisplayed()) {
                            Assert.fail("NO Loyalty icon should be displayed for given client");
                        }
                    } catch (Exception e) {
                        System.out.println("No Loyalty icon was found, all good!");
                    }
                    break;
            }
        }
    }

    @And("^I navigate to Top100 tab$")
    public void iNavigateToTop100Tab() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.top_100_tab");
        Clicks.click("homepage.top_100_tab");
    }

    @Then("^I should see the Top100 screen$")
    public void iShouldSeeTheTop100Screen() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.top_100_page_content");
        elementShouldBePresent("homepage.top_100_page_content");
    }

    @And("^I should see the Star Rewards Level title on Top100 screen$")
    public void iShouldSeeTheStarRewardsLevelTitleOnTop100Screen() throws Throwable {
        Wait.untilElementPresent("homepage.top_100_star_rewards_column");
        String starRewardsColumn = findElement("homepage.top_100_star_rewards_column").getText();
        System.out.println(starRewardsColumn);
        assertEquals("Star Rewards Level", starRewardsColumn);

    }


    @And("^I should see the Star Rewards Tier Icons \"([^\"]*)\" on the Top100 screen$")
    public void iShouldSeeTheStarRewardsTierIconsOnTheTopScreen(String loyaltyIcon) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        switch (loyaltyIcon) {
            case "gold":
                seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(loyaltyIcon, "homepage.loyalty_icon_gold");

                break;
            case "platinum":
                seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(loyaltyIcon, "homepage.loyalty_icon_platinum");
                break;
            case "silver":
                seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(loyaltyIcon, "homepage.loyalty_icon_silver");
                break;
            case "non-loyalty":
                seeTheNoLoyaltyStarRewardsTierIconsOnTheTopScreen();
                break;
        }

    }

    private void seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(String loyaltyIcon, String icon) throws Throwable {

        if (!elementPresent(icon)) {
            navigateNextPageTop100MyClientsPages();
            iShouldSeeTheStarRewardsTierIconsOnTheTopScreen(loyaltyIcon);
        } else {
            Assert.assertTrue(loyaltyIcon + " tier is displayed", true);
            System.out.println(loyaltyIcon + " tier is displayed");

        }
    }

    private void navigateNextPageTop100MyClientsPages() {
        String pagination = findElement("homepage.bottom_pagination").getText();
        String[] ofs = StringUtils.split(pagination, "of");
        if (Integer.parseInt(ofs[0].trim()) < Integer.parseInt(ofs[1].trim())) {
            Clicks.click("homepage.arrow_next_page_top100_my_clients_page");
        } else {
            Assert.fail("No Star Rewards Level icon was found");
        }
    }

    private void seeTheNoLoyaltyStarRewardsTierIconsOnTheTopScreen() throws Throwable {
        try {
            findElement("homepage.top_100_and_MyClientsPage_no_loyalty_icon");
            Assert.assertTrue("Non loyalty tier is displayed", true);
            System.out.println("Non loyalty tier is displayed");
        } catch (Exception e) {
            navigateNextPageTop100MyClientsPages();
            seeTheNoLoyaltyStarRewardsTierIconsOnTheTopScreen();

        }
    }

    @And("^I should see the Star Rewards Level title on Manage Relationships screen$")
    public void iShouldSeeTheStarRewardsLevelTitleOnManageRelationshipsScreen() throws Throwable {
        Wait.untilElementPresent("homepage.loyalty_title_manage_rel");
        Elements.elementShouldBePresent("homepage.loyalty_title_manage_rel");
        String text = findElement("homepage.loyalty_title_manage_rel").getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("Star Rewards Level"));
    }

    @And("^I should see the Star Rewards Tier Icons on Manage Relationships screen$")
    public void iShouldSeeTheStarRewardsTierIconsOnManageRelationshipsScreen(DataTable loyal) throws Throwable {
        Wait.secondsUntilElementPresent("homepage.manage_rel_1st_sa_header", 10);
        Wait.untilElementPresent("homepage.manage_rel_1st_sa_expand");
        Clicks.click("homepage.manage_rel_1st_sa_expand");
        Wait.untilElementNotPresent("homepage.loading_page");
        for (List<String> list : loyal.raw()) {
            String loyalIcon = list.get(0);
            String loyalClient = list.get(1);
            switch (loyalIcon) {
                case "gold":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_gold");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_gold");
                    break;
                case "silver":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_silver");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_silver");
                    break;
                case "platinum":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_platinum");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_platinum");
                    break;
                case "non-loyalty":
                    Wait.untilElementPresent("homepage.manage_rel_find_cust_input");
                    TextBoxes.typeTextbox("homepage.manage_rel_find_cust_input", loyalClient);
                    Thread.sleep(500);
                    Clicks.click("homepage.manage_rel_search_button");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    try {
                        if (findElement("homepage.loyalty_any_icon").isDisplayed()) {
                            Assert.fail("NO Loyalty icon should be displayed for given client");
                        }
                    } catch (Exception e) {
                        System.out.println("No Loyalty icon was found, all good!");
                    }
                    break;
            }
        }
    }

    @And("^I should see the Star Rewards Level title on Unassigned Clients screen$")
    public void iShouldSeeTheStarRewardsLevelTitleOnUnassignedClientsScreen() throws Throwable {
        Wait.untilElementPresent("homepage.loyalty_title_unass_clients");
        Elements.elementShouldBePresent("homepage.loyalty_title_unass_clients");
        String text = findElement("homepage.loyalty_title_unass_clients").getText();
        Assert.assertTrue(text.contains("Star Rewards Level"));
    }

    @When("^I expand the first Selling Area from Manage Relationships screen$")
    public void iExpandTheFirstSellingAreaFromManageRelationshipsScreen() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.manage_rel_1st_sa_header", 10);
        Wait.untilElementPresent("homepage.manage_rel_1st_sa_expand");
        Clicks.click("homepage.manage_rel_1st_sa_expand");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I click on the Star Rewards Level title$")
    public void iClickOnTheStarRewardsLevelTitle() throws Throwable {
        Wait.untilElementPresent("homepage.manage_rel_client_list");
        Wait.untilElementPresent("homepage.loyalty_title_manage_rel");
        Clicks.click("homepage.loyalty_title_manage_rel");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^I should see the Loyalty icons sorted \"([^\"]*)\"$")
    public void iShouldSeeTheLoyaltyIconsSorted(String order) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        switch (order) {
            case "descending":
                Wait.untilElementPresent("homepage.loyalty_icon_first_in_list");
                WebElement loyaltyIcon = findElement("homepage.loyalty_icon_first_in_list");
                String srcAttribute = loyaltyIcon.getAttribute("src");
                System.out.println(srcAttribute);
                Assert.assertTrue(srcAttribute.contains("PLATINUM"));
                break;
            case "ascending":
                try {
                    OmniclientUtils.waitForAngularLoad();
                    if (elementPresent("homepage.loyalty_icon_first_in_list"))
//                    if (loyaltyIcon.isDisplayed())
                    {
                        Assert.fail("Sorting is wrong, first client in list is loyalist");
                    }
                } catch (Exception e) {
                    System.out.println("Sorting is correct, first client in list in non-loyalist");

                }
                break;
        }
    }

    @When("^I expand the first Selling Area from Unassigned Clients screen$")
    public void iExpandTheFirstSellingAreaFromUnassignedClientsScreen() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.manage_rel_1st_sa_header", 10);
        Wait.untilElementPresent("homepage.manage_rel_1st_sa_expand");
        Clicks.click("homepage.manage_rel_1st_sa_expand");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^I should see the My Clients screen$")
    public void iShouldSeeTheMyClientsScreen() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("my_clients.my_clients_customers_container");
        elementPresent("my_clients.my_clients_customers_container");
    }

    @And("^I should see the Star Rewards Level title on My Clients screen$")
    public void iShouldSeeTheStarRewardsLevelTitleOnMyClientsScreen() throws Throwable {
        Wait.untilElementPresent("my_clients.my_clients__star_rewards_column");
        elementPresent("my_clients.my_clients__star_rewards_column");
        String starRewardsColumn = findElement("my_clients.my_clients__star_rewards_column").getText();
        System.out.println(starRewardsColumn);
        assertEquals("Star Rewards Level", starRewardsColumn);
    }

    @And("^I should see the Star Rewards Tier Icons \"([^\"]*)\" on the My Clients screen$")
    public void iShouldSeeTheStarRewardsTierIconsOnTheMyClientsScreen(String levelIcon) throws Throwable {
        Wait.untilElementPresent("my_clients.my_clients_customers_container");
        iSelectTheStarRewardsColumnMyClients();
        switch (levelIcon) {
            case "gold":

                seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(levelIcon, "homepage.loyalty_icon_gold");
                break;
            case "platinum":

                seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(levelIcon, "homepage.loyalty_icon_platinum");
                break;
            case "silver":

                seeTheStarRewardsTierIconsOnTheTop100ScreenAndMyClientsScreen(levelIcon, "homepage.loyalty_icon_silver");
                break;


        }
    }

    @Then("^I should see the column sorted by Platinum, Gold, Silver and Non-Loyalty$")
    public void iShouldSeeTheColumnSortedByPlatinumGoldSilverAndNonLoyalty() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("my_clients.my_clients_customers_container");
        try {

            WebElement loyaltyIconPlatinum = findElement("my_clients.my_clients_loyalty_icon_first_in_list");
            String srcAttributePlatinum = loyaltyIconPlatinum.getAttribute("src");
            System.out.println(srcAttributePlatinum);


            WebElement loyaltyIconGold = findElement("my_clients.my_clients_loyalty_icon_second_in_list");
            String srcAttributeGold = loyaltyIconGold.getAttribute("src");
            System.out.println(srcAttributeGold);


            WebElement loyaltyIconSilver = findElement("my_clients.my_clients_loyalty_icon_third_in_list");
            String srcAttributeSilver = loyaltyIconSilver.getAttribute("src");
            System.out.println(srcAttributeSilver);


            Assert.assertTrue(srcAttributePlatinum.contains("PLATINUM"));
            Assert.assertTrue(srcAttributeGold.contains("GOLD"));
            Assert.assertTrue(srcAttributeSilver.contains("SILVER"));
        } catch (Exception e) {
            Assert.fail("Sorting in not properly done");
        }
    }

    @When("^I select the Star Rewards column My Clients$")
    public void iSelectTheStarRewardsColumnMyClients() throws Throwable {
        Wait.untilElementPresent("my_clients.my_clients_star_rewards_sorting");
        Clicks.click("my_clients.my_clients_star_rewards_sorting");
    }

    @Then("^I should see the Star Rewards Tier Icons on the Customer Info Bar$")
    public void iShouldSeeTheStarRewardsTierIconsOnTheCustomerInfoBar(DataTable loyal) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (List<String> list : loyal.raw()) {
            String loyalIcon = list.get(0);
            switch (loyalIcon) {
                case "gold":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_gold");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_gold");
                    break;
                case "silver":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_silver");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_silver");
                    break;
                case "platinum":
                    Wait.untilElementNotPresent("homepage.loading_page");
                    Wait.untilElementPresent("homepage.loyalty_icon_platinum");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_platinum");
                    break;

            }
        }
    }

    @And("^I navigate to PROFILE tab$")
    public void iNavigateToPROFILETab() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.profile_tab");
        elementShouldBePresent("homepage.profile_tab");
        Clicks.click("homepage.profile_tab");
    }

    @And("^I click on PREVIEW button from CREATE TODOS tabs$")
    public void iClickOnPREVIEWButtonFromCREATETODOSTabs() throws Throwable {
        Wait.untilElementPresent("homepage.preview_button_profile_tab");
        elementShouldBePresent("homepage.preview_button_profile_tab");
        Clicks.click("homepage.preview_button_profile_tab");
    }

    @Then("^the CREATE LIST PREVIEW page is displayed$")
    public void theCREATELISTPREVIEWPageIsDisplayed() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.create_list_preview_page");
        elementShouldBePresent("homepage.create_list_preview_page");
        Wait.untilElementPresent("homepage.create_list_preview_title");
        elementShouldBePresent("homepage.create_list_preview_title");
    }

    @And("^the column named Star Rewards Level is displayed LIST PREVIEW$")
    public void theColumnNamedStarRewardsLevelIsDisplayedLISTPREVIEW() throws Throwable {
        Wait.untilElementPresent("homepage.star_rewards_column_title_list_preview");
        String rewardsColumn = findElement("homepage.star_rewards_column_title_list_preview").getText();
        System.out.println(rewardsColumn);
        assertEquals("STAR REWARDS LEVEL", rewardsColumn);
    }

    @And("^I click on I want to include Customer Profile Dates in list radio button$")
    public void iClickOnIWantToIncludeCustomerProfileDatesInListRadioButton() throws Throwable {
        Wait.untilElementPresent("homepage.include_customers_profile_tab_radio_button");
        Clicks.click("homepage.include_customers_profile_tab_radio_button");
    }

    @And("^I select \"([^\"]*)\" in From Month dropdown Profile tab$")
    public void iSelectInFromMonthDropdownProfileTab(String fromMonth) throws Throwable {
        Wait.untilElementPresent("homepage.from_month_dropdown_profile_tab");
        DropDowns.selectByText("homepage.from_month_dropdown_profile_tab", fromMonth);

    }

    @And("^I select \"([^\"]*)\" in From Day dropdown Profile tab$")
    public void iSelectInFromDayDropdownProfileTab(String fromDay) throws Throwable {
        Wait.untilElementPresent("homepage.from_day_dropdown_profile_tab");
        DropDowns.selectByText("homepage.from_day_dropdown_profile_tab", fromDay);
    }

    @And("^I select \"([^\"]*)\" in To Month dropdown Profile tab$")
    public void iSelectInToMonthDropdownProfileTab(String toMonth) throws Throwable {
        Wait.untilElementPresent("homepage.to_month_dropdown_profile_tab");
        DropDowns.selectByText("homepage.to_month_dropdown_profile_tab", toMonth);
    }

    @And("^I select \"([^\"]*)\" in To Day dropdown Profile tab$")
    public void iSelectInToDayDropdownProfileTab(String toDay) throws Throwable {
        Wait.untilElementPresent("homepage.to_month_dropdown_profile_tab");
        DropDowns.selectByText("homepage.to_day_dropdown_profile_tab", toDay);
    }

    @And("^I should see the Star Rewards Tier Icons on the Create List preview screen$")
    public void iShouldSeeTheStarRewardsTierIconsOnTheCreateListPreviewScreen(List<String> Icon) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        for (String rewardsIcon : Icon) {
            switch (rewardsIcon) {
                case "gold":
                    Wait.untilElementPresent("homepage.loyalty_icon_gold");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_gold");
                    break;

                case "silver":
                    Wait.untilElementPresent("homepage.loyalty_icon_silver");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_silver");
                    break;

                case "platinum":
                    Wait.untilElementPresent("homepage.loyalty_icon_platinum");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_platinum");
                    break;

            }
        }
    }

    @And("^I navigate to TRANSACTIONS tab$")
    public void iNavigateToTRANSACTIONSTab() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.transactions_tab");
        elementShouldBePresent("homepage.transactions_tab");
        Clicks.click("homepage.transactions_tab");

    }

    @When("^I click on the searched client by phone from Search Results page$")
    public void iClickOnTheSearchedClientByPhoneFromSearchResultsPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        WebElement clientName = findElement(By.xpath("(//span[@class='highlight'])[1]"));
        Clicks.click(clientName);
    }

    @And("^I click on filter radio button from TRANSACTIONS tab$")
    public void iClickOnFilterRadioButtonFromTRANSACTIONSTab() throws Throwable {
        Wait.untilElementPresent("homepage.include_filters_transactions_tab");
        Clicks.click("homepage.include_filters_transactions_tab");
    }

    @And("^I select the I want to include transactions filters in my list radio button$")
    public void iSelectTheIWantToIncludeTransactionsFiltersInMyListRadioButton() throws Throwable {
        Wait.untilElementPresent("homepage.include_filters_transactions_tab");
        Clicks.click("homepage.include_filters_transactions_tab");


    }

    @And("^I should see the Star Rewards Level title on Event Information screen$")
    public void iShouldSeeTheStarRewardsLevelTitleOnEventInformationScreen() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.star_rewards_column_title_event_info");
        String rewardsColumn = findElement("homepage.star_rewards_column_title_event_info").getText();
        System.out.println(rewardsColumn);
        Assert.assertEquals("Star Rewards Level", rewardsColumn);
    }


    @And("^I should see the Star Rewards Tier Icons on the Event Information screen$")
    public void iShouldSeeTheStarRewardsTierIconsOnTheEventInformationScreen(List<String> Icon) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        for (String rewardsIcon : Icon) {
            switch (rewardsIcon) {
                case "gold":
                    Wait.untilElementPresent("homepage.loyalty_icon_gold");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_gold");
                    break;

                case "silver":
                    Wait.untilElementPresent("homepage.loyalty_icon_silver");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_silver");
                    break;

                case "platinum":
                    Wait.untilElementPresent("homepage.loyalty_icon_platinum");
                    Elements.elementShouldBePresent("homepage.loyalty_icon_platinum");
                    break;

            }
        }
    }

    @When("^I select the Star Rewards column from Event Information page$")
    public void iSelectTheStarRewardsColumnFromEventInformationPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.star_rewards_column_title_event_info");
        elementShouldBePresent("homepage.star_rewards_column_title_event_info");
        Clicks.click("homepage.star_rewards_column_title_event_info");
    }

    @And("^I should see the Star Rewards Tier Icons \"([^\"]*)\" on Homepage Dashboard My Clients table$")
    public void iShouldSeeTheStarRewardsTierIconsOnHomepageDashboardMyClientsTable(String loyaltyIcon) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.click("homepage.go_to_first_page_arrow_homepage");
        switch (loyaltyIcon) {

            case "gold":
                iNavigateUntilLoyaltyIconIsDisplayedHomepageDashboard(loyaltyIcon, "homepage.loyalty_icon_gold");
                break;

            case "silver":

                iNavigateUntilLoyaltyIconIsDisplayedHomepageDashboard(loyaltyIcon, "homepage.loyalty_icon_silver");
                break;

            case "platinum":

                iNavigateUntilLoyaltyIconIsDisplayedHomepageDashboard(loyaltyIcon, "homepage.loyalty_icon_platinum");
                break;

        }
    }

    private void iNavigateUntilLoyaltyIconIsDisplayedHomepageDashboard(String loyaltyIcon, String icon) {
        if (!elementPresent(icon)) {
            navigateNextPageHomepageDashboard();
            iNavigateUntilLoyaltyIconIsDisplayedHomepageDashboard(loyaltyIcon, icon);

        } else {
            Assert.assertTrue(loyaltyIcon + " tier is displayed", true);
            System.out.println(loyaltyIcon + " tier is displayed");

        }
    }

    private void navigateNextPageHomepageDashboard() {
        String pagination = findElement("homepage.bottom_pagination").getText();
        String[] ofs = StringUtils.split(pagination, "of");
        if (Integer.parseInt(ofs[0].trim()) < Integer.parseInt(ofs[1].trim())) {
            Clicks.click("homepage.next_arrow_homepage");
        } else {
            Assert.fail("No Star Rewards Level icon was found");
        }
    }

    @Then("^I should see the My Clients table from Homepage Dashboard page$")
    public void iShouldSeeTheMyClientsTableFromHomepageDashboardPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.client_list_homepage");
        elementPresent("homepage.client_list_homepage");
    }

    @When("^I close the CREATE LIST PREVIEW page$")
    public void iCloseTheCREATELISTPREVIEWPage() throws Throwable {
        Wait.untilElementPresent("homepage.create_list_preview_cancel_btn");
        Clicks.click("homepage.create_list_preview_cancel_btn");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I click on CONTINUE button from CREATE TODOS tabs$")
    public void iClickOnCONTINUEButtonFromCREATETODOSTabs() throws Throwable {
        Wait.untilElementPresent("homepage.continue_button_profile_tab");
        elementShouldBePresent("homepage.continue_button_profile_tab");
        Clicks.click("homepage.continue_button_profile_tab");
    }

    @Then("^the CREATE LIST screen is displayed$")
    public void theCREATELISTScreenIsDisplayed() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.create_list_page");
        elementShouldBePresent("homepage.create_list_page");
        Wait.untilElementPresent("homepage.create_list_title");
        elementShouldBePresent("homepage.create_list_title");
    }

    @Then("^I should see the ToDo TITLE remaining characters count as (\\d+)$")
    public void iShouldSeeTheToDoTITLERemainingCharactersCountAs(int charCount) throws Throwable {
//          TITLE is cleared beforehand to make sure the max char count can be validated
        findElement("homepage.title_checkbox_create_list").clear();
//          TITLE's remaining char count parsed into integer and compared to the max char count
        Wait.untilElementPresent("homepage.create_list_page_title_char_count");
        String charRemain = findElement("homepage.create_list_page_title_char_count").getText();
        String intValue = charRemain.replaceAll("[^0-9]", "").trim();
        System.out.println("TITLE Remaining Character Count is: " + intValue);
        int charCountActual = Integer.parseInt(intValue);
        Assert.assertEquals(charCountActual, charCount);
    }

    @Then("^I should see the ToDo DESCRIPTION remaining characters count as (\\d+)$")
    public void iShouldSeeTheToDoDESCRIPTIONRemainingCharactersCountAs(int charCount) throws Throwable {
//          DESCRIPTION is cleared beforehand to make sure the max char count can be validated
        findElement("homepage.description_field_create_list").clear();
//          DESCRIPTIONS's remaining char count parsed into integer and compared to the max char count
        Wait.untilElementPresent("homepage.create_list_page_descr_char_count");
        String charRemain = findElement("homepage.create_list_page_descr_char_count").getText();
        String intValue = charRemain.replaceAll("[^0-9]", "").trim();
        System.out.println("DESCRIPTION Remaining Character Count is: " + intValue);
        int charCountActual = Integer.parseInt(intValue);
        Assert.assertEquals(charCountActual, charCount);
    }

    @And("^current List details are retained Title \"([^\"]*)\" and Description \"([^\"]*)\"$")
    public void currentListDetailsAreRetainedTitleAndDescription(String title, String descr) throws Throwable {
        Wait.untilElementPresent("homepage.title_checkbox_create_list");
        String actualTitle = findElement("homepage.title_checkbox_create_list").getAttribute("value").trim();
        System.out.println("Actual title: " + actualTitle + "Expected title: " + title);
        Assert.assertEquals(actualTitle, title);
        Wait.untilElementPresent("homepage.description_field_create_list");
        String actualDescr = findElement("homepage.description_field_create_list").getAttribute("value").trim();
        System.out.println("Actual Description: " + actualDescr + "Expected Description: " + descr);
        Assert.assertEquals(actualDescr, descr);

    }

    @And("^I click the X button on CREATE LIST screen$")
    public void iClickTheXButtonOnCREATELISTScreen() throws Throwable {
        Wait.untilElementPresent("homepage.send_todo_screen_x_button");
        Clicks.click("homepage.send_todo_screen_x_button");
    }

    @When("^I click the CANCEL button on CREATE LIST screen$")
    public void iClickTheCANCELButtonOnCREATELISTScreen() throws Throwable {
        Wait.untilElementPresent("homepage.send_todo_screen_cancel_button");
        Clicks.click("homepage.send_todo_screen_cancel_button");
    }

    @And("^I check the My Macys To DOs count$")
    public void iCheckTheMyMacysToDOsCount() throws Throwable {
        Wait.untilElementPresent("homepage.my_macys_to_dos_count_homepage");
        elementShouldBePresent("homepage.my_macys_to_dos_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.my_macys_to_dos_count_homepage"), " ");
        countToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]);
        System.out.println(countToDos);

    }

    @And("^I will validate that the My Macys ToDos count incremented by 1$")
    public void i_will_validate_that_the_my_macys_todos_count_incremented_by_1() throws Throwable {
        Wait.untilElementPresent("homepage.my_macys_to_dos_count_homepage");
        elementShouldBePresent("homepage.my_macys_to_dos_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.my_macys_to_dos_count_homepage"), " ");
        int newcountToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]); //grab the element
        Assert.assertEquals(countToDos + 1, newcountToDos);
        System.out.println(newcountToDos);
    }

    @And("^I click on the SearchByID radio button$")
    public void iClickOnTheSearchByIDRadioButton() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.search_by_id_homepage_radio_button");
        Clicks.click("homepage.search_by_id_homepage_radio_button");
    }

    @And("^I input \"([^\"]*)\" SearchByID in the search box$")
    public void iInputSearchByIDInTheSearchBox(String id) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.search_by_id_field");
        elementShouldBePresent("homepage.search_by_id_field");
        TextBoxes.typeTextbox("homepage.search_by_id_field", id);
    }

    @Then("^error messaging for wrong or non existent Loyalty ID is displayed$")
    public void errorMessagingForWrongOrNonExistentLoyaltyIDIsDisplayed(List<String> errorMsg) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.invalid_id_message_popup");
        elementShouldBePresent("homepage.invalid_id_message_popup");
        for (String aMsg : errorMsg) {
            switch (aMsg) {
                case "Invalid Star Rewards ID":
                    String invalidID = findElement("homepage.invalid_id_message_popup").getText();
                    assertEquals("Invalid Star Rewards ID format.", invalidID);

                    break;
                case "Non-existent Star Rewards ID":
                    String nonExistent = findElement("homepage.invalid_id_message_popup").getText();
                    assertEquals("Star Rewards ID not found. Select a different search method.", nonExistent);
                    break;

                case "Invalid Loyallist ID BLM":
                    String invalidIDblm = findElement("homepage.invalid_id_message_popup").getText();
                    assertEquals("Invalid Loyallist ID format.", invalidIDblm);

                    break;
                case "Non-existent Loyallist ID BLM":
                    String nonExistentblm = findElement("homepage.invalid_id_message_popup").getText();
                    assertEquals("Loyallist ID not found. Select a different search method.", nonExistentblm);
                    break;
            }
        }
    }

    @When("^I click on delete ToDo icon from Lists page$")
    public void iClickOnDeleteToDoIconFromListsPage() throws Throwable {
        Wait.untilElementPresent("homepage.delete_todo_icon");
        Clicks.click("homepage.delete_todo_icon");
    }

    @Then("^I should see the delete ToDo popup$")
    public void iShouldSeeTheDeleteToDoPopup() throws Throwable {
        Wait.untilElementPresent("homepage.delete_todo_popup");
        Elements.elementShouldBePresent("homepage.delete_todo_popup");
        String deleteTodoText = findElement("homepage.delete_todo_popup").getText().trim();
        Assert.assertTrue(deleteTodoText.contains("You are about to delete")
                && deleteTodoText.contains("Are you sure you want to continue?"));
    }

    @When("^I click on YES on the delete ToDo popup$")
    public void iClickOnYESOnTheDeleteToDoPopup() throws Throwable {
        Wait.untilElementPresent("homepage.delete_todo_popup_yes");
        Clicks.click("homepage.delete_todo_popup_yes");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @When("^I click on NO on the delete ToDo popup$")
    public void iClickOnNOOnTheDeleteToDoPopup() throws Throwable {
        Wait.untilElementPresent("homepage.delete_todo_popup_no");
        Clicks.click("homepage.delete_todo_popup_no");
    }

    @Then("^I will validate that the My Macys ToDos count decreased by 1$")
    public void iWillValidateThatTheMyMacysToDosCountDecreasedBy() throws Throwable {
        Wait.untilElementPresent("homepage.blm_list_count_homepage");
        elementShouldBePresent("homepage.blm_list_count_homepage");
        String[] elementNeededs = StringUtils.split(getText("homepage.blm_list_count_homepage"), " ");
        int newcountToDos = Integer.parseInt(elementNeededs[elementNeededs.length - 1]); //grab the element
        Assert.assertEquals(countToDos - 1, newcountToDos);
        System.out.println(newcountToDos);
    }

    @Then("^I should no longer see the deleted todo \"([^\"]*)\" on the CLIENT PROFILE$")
    public void iShouldNoLongerSeeTheDeletedTodoOnTheClientProfile(String delTodo) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.client_profile_my_todos_container");
        Elements.elementShouldBePresent("homepage.client_profile_my_todos_container");
        String todoContainer = findElement("homepage.client_profile_my_todos_container").getText().trim();
        System.out.println(todoContainer);
        if (todoContainer.contains(delTodo)) {
            Assert.fail("Deleted ToDo is still displayed on the Client Profile");
        }
    }

    @Then("^I should no longer see the deleted todo \"([^\"]*)\" on the MY MACYS TO DOS$")
    public void iShouldNoLongerSeeTheDeletedTodoOnTheMYMACYSTODOS(String delTodo) throws Throwable {
        Navigate.browserRefresh();
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.my_macys_todos_container");
        Elements.elementShouldBePresent("homepage.my_macys_todos_container");
        String todoContainer = findElement("homepage.my_macys_todos_container").getText().trim();
        System.out.println(todoContainer);
        if (todoContainer.contains(delTodo)) {
            Assert.fail("Deleted ToDo is still displayed on the My Macys To Dos");
        }
    }


    @Then("^I should see the To Do marked as completed in the List$")
    public void iShouldSeeTheToDoMarkedAsCompletedInTheList() throws Throwable {
        String url = WebDriverManager.getWebDriver().getCurrentUrl();
        System.out.println(url);
        if (url.contains("mcy")) {
            Wait.untilElementNotPresent("homepage.loading_page");
            Wait.untilElementPresent("homepage.close_status_check_my_to_dos_page");
            Elements.elementShouldBePresent("homepage.close_status_check_my_to_dos_page");
            String completedAtt = findElement("homepage.close_status_check_my_to_dos_page").getAttribute("title");
            Assert.assertEquals(completedAtt, "Completed");
        } else {
            Wait.untilElementPresent("homepage.todo_closed_by_phone");
            Elements.elementShouldBePresent("homepage.todo_closed_by_phone");
        }
    }


    @And("^I navigate to Sales tab from Client Profile view$")
    public void iNavigateToSalesTabFromClientProfileView() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.click("my_clients.sales_tab_client_profile");
    }

    @Then("^By Division tab in displayed in Sales view$")
    public void byDivisionTabInDisplayedInSalesView() throws Throwable {
        Wait.untilElementPresent("my_clients.by_division_tab_client_profile_sales_page_view");
        String tabText = findElement("my_clients.by_division_tab_client_profile_sales_page_view").getText();
        System.out.println(tabText);
        assertEquals("By Division", tabText);
    }

    @And("^the My Selling Area tab is displayed in Sales view$")
    public void theMySellingAreaTabIsDisplayedInSalesView() throws Throwable {
        Wait.untilElementPresent("my_clients.my_selling_area_tab_client_profile_sales_page_view");
        String tabText = findElement("my_clients.my_selling_area_tab_client_profile_sales_page_view").getText();
        System.out.println(tabText);
        assertEquals("My Selling Area", tabText);
    }

    @And("^the following columns are displayed in By Division tab from Sales view$")
    public void theFollowingColumnsAreDisplayedInByDivisionTabFromSalesView(List<String> columns) throws Throwable {

        for (String aColumn : columns) {
            switch (aColumn) {
                case "Division":
                    Wait.untilElementPresent("my_clients.division_column_by_division_tab");
                    String divisionTxt = findElement("my_clients.division_column_by_division_tab").getText();
                    System.out.println(divisionTxt);
                    assertEquals("Division", divisionTxt);
                    break;

                case "Net Sales":
                    Wait.untilElementPresent("my_clients.net_sales_column_by_division_my_selling_area_tabs");
                    String divisionTxtSales = findElement("my_clients.net_sales_column_by_division_my_selling_area_tabs").getText();
                    System.out.println(divisionTxtSales);
                    assertEquals("Net Sales", divisionTxtSales);
                    break;

                case "Visits":
                    Wait.untilElementPresent("my_clients.visits_column_by_division_tab_my_selling_area_tabs");
                    String divisionTxtVisits = findElement("my_clients.visits_column_by_division_tab_my_selling_area_tabs").getText();
                    System.out.println(divisionTxtVisits);
                    assertEquals("Visits", divisionTxtVisits);
                    break;

            }
        }
    }

    @When("^I click on My Selling Are tab from Sales view$")
    public void iClickOnMySellingAreTabFromSalesView() throws Throwable {
        Wait.untilElementPresent("my_clients.my_selling_area_tab_client_profile_sales_page_view");
        elementShouldBePresent("my_clients.my_selling_area_tab_client_profile_sales_page_view");
        Clicks.click("my_clients.my_selling_area_tab_client_profile_sales_page_view");
    }

    @Then("^the following columns are displayed in My Selling Are tab from Sales view$")
    public void theFollowingColumnsAreDisplayedInMySellingAreTabFromSalesView(List<String> columns) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");

        for (String aColumn : columns) {
            switch (aColumn) {
                case "Department":
                    Wait.untilElementPresent("my_clients.department_column_my_selling_area_tab");
                    String departmentTxt = findElement("my_clients.department_column_my_selling_area_tab").getText();
                    System.out.println(departmentTxt);
                    assertEquals("Department", departmentTxt);
                    break;

                case "Net Sales":
                    Wait.untilElementPresent("my_clients.net_sales_column_by_division_my_selling_area_tabs");
                    String divisionTxtSales = findElement("my_clients.net_sales_column_by_division_my_selling_area_tabs").getText();
                    System.out.println(divisionTxtSales);
                    assertEquals("Net Sales", divisionTxtSales);
                    break;

                case "Visits":
                    Wait.untilElementPresent("my_clients.visits_column_by_division_tab_my_selling_area_tabs");
                    String divisionTxtVisits = findElement("my_clients.visits_column_by_division_tab_my_selling_area_tabs").getText();
                    System.out.println(divisionTxtVisits);
                    assertEquals("Visits", divisionTxtVisits);
                    break;

            }
        }
    }

    @Then("^the Sales By Area title is displayed in the left side of Sales tab$")
    public void theSalesByAreaTitleIsDisplayedInTheLeftSideOfSalesTab() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        String sectionTitle = findElement("my_clients.sales_by_area_title_section_sales_tab").getText();
        System.out.println(sectionTitle);
        assertEquals("SALES BY AREA", sectionTitle);

    }

    @And("^the piechart is displayed in the left side of Sales tab$")
    public void thePiechartIsDisplayedInTheLeftSideOfSalesTab() throws Throwable {
        Wait.untilElementPresent("my_clients.sales_piechart_sales_tab");
        elementShouldBePresent("my_clients.sales_piechart_sales_tab");
    }

    @Then("^the Star Rewards tab is displayed in tab navigation$")
    public void theStarRewardsTabIsDisplayedInTabNavigation() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("my_clients.star_rewards_tab");
        elementShouldBePresent("my_clients.star_rewards_tab");
        String tabName = findElement("my_clients.star_rewards_tab").getText();
        System.out.println(tabName);
        assertEquals("Star Rewards", tabName);


    }

    @When("^I click on the Star Rewards tab$")
    public void iClickOnTheStarRewardsTab() throws Throwable {
        Wait.untilElementPresent("my_clients.star_rewards_tab");
        Clicks.click("my_clients.star_rewards_tab");
    }

    @Then("^the following information should be displayed on the Top Left Section of the loyalty page MCY:$")
    public void theFollowingInformationShouldBeDisplayedOnTheTopLeftSectionOfTheLoyaltyPageMCY(List<String> column) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.top_box_section_mcy");
        elementShouldBePresent("homepage.top_box_section_mcy");
        for (String columnSection : column) {
            switch (columnSection) {
                case "MEMBERSHIP":
                    Wait.untilElementPresent("homepage.top_left_membership_label_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_membership_label_mcy");
                    break;
                case "ID#:":
                    Wait.untilElementPresent("homepage.top_left_loyallist_id_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_loyallist_id_mcy");
                    break;
                case "Level:":
                    Wait.untilElementPresent("homepage.top_left_level_label_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_level_label_mcy");
                    break;
                case "Spend to Upgrade: $":
                    Wait.untilElementPresent("homepage.top_left_spend_to_upgrade_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_spend_to_upgrade_mcy");
                    break;
                case "Last Reward Earned:":
                    Wait.untilElementPresent("homepage.top_left_last_reward_earned_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_last_reward_earned_mcy");
                    break;
                case "UNREDEEMED REWARDS CARDS:":
                    Wait.untilElementPresent("homepage.top_left_unredeemed_rewards_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_unredeemed_rewards_mcy");
                    break;

                case "Rewards Card Data unavailable":
                    Wait.untilElementPresent("homepage.top_left_reward_card_data_unavail_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_reward_card_data_unavail_mcy");
                    break;


                case "Star Money Data unavailable":
                    Wait.untilElementPresent("homepage.top_left_star_money_data_unavailable_mcy");
                    Elements.elementShouldBePresent("homepage.top_left_star_money_data_unavailable_mcy");
                    break;

                default:
                    fail("Section is missing");

            }
        }

    }

    @And("^the following information should be displayed on the Top Right Section of the loyalty page MCY:$")
    public void theFollowingInformationShouldBeDisplayedOnTheTopRightSectionOfTheLoyaltyPageMCY(List<String> column) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.top_box_section_mcy");
        elementShouldBePresent("homepage.top_box_section_mcy");
        for (String columnSection : column) {
            switch (columnSection) {
                case "POINTS SUMMARY":
                    Wait.untilElementPresent("homepage.top_right_points_summary_label_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_points_summary_label_mcy");
                    break;
                case "Lifetime Points Earned:":
                    Wait.untilElementPresent("homepage.top_right_lifetime_points_earned_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_lifetime_points_earned_mcy");
                    break;
                case "Current Points:":
                    Wait.untilElementPresent("homepage.top_right_current_points_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_current_points_mcy");
                    break;
                case "Pending Points:":
                    Wait.untilElementPresent("homepage.top_right_pending_points_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_pending_points_mcy");
                    break;
                case "Deferred Points:":
                    Wait.untilElementPresent("homepage.top_right_deferred_points_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_deferred_points_mcy");
                    break;
                case "Points Needed to Next Reward Card:":
                    Wait.untilElementPresent("homepage.top_right_points_needed_to_next_reward_card_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_points_needed_to_next_reward_card_mcy");
                    break;

                case "Points to Next Reward:":
                    Wait.untilElementPresent("homepage.top_right_points_to_next_rewards_mcy");
                    Elements.elementShouldBePresent("homepage.top_right_points_to_next_rewards_mcy");
                    break;

                default:
                    fail("Section is missing");
            }
        }
    }

    @And("^I will validate that the Offers are displayed with the nearest expiration date first MCY$")
    public void iWillValidateThatTheOffersAreDisplayedWithTheNearestExpirationDateFirstMCY() throws Throwable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<WebElement> elements = findElements("homepage.dates_list_loyallist_offers_table_mcy");
        compareSortedDates(simpleDateFormat, elements);
    }

    private void compareSortedDates(SimpleDateFormat simpleDateFormat, List<WebElement> elements) throws ParseException {
        compareSortedDates(simpleDateFormat, elements, false);
    }

    private void compareSortedDates(SimpleDateFormat simpleDateFormat, List<WebElement> elements, boolean descending) throws ParseException {
        OmniclientUtils.waitForAngularLoad();
        assertTrue(elements.size() > 0);
        elements.remove(0);
        List<Long> dates = Lists.newArrayList();
        for (WebElement webElement : elements) {
            String text = webElement.getText();
            if ("-".equals(text) || "".equals(text)) {
                continue;
            }
            long time = simpleDateFormat.parse(text).getTime();
            dates.add(time);
        }

        List<Long> newDates = Lists.newArrayList(dates);
        Collections.sort(newDates);
        if (descending) {
            Collections.reverse(newDates);
        }
        assertEquals(dates, newDates);
    }

    @Then("^the following information should be displayed on the Bottom Section of the loyalty page MCY:$")
    public void theFollowingInformationShouldBeDisplayedOnTheBottomSectionOfTheLoyaltyPageMCY(List<String> columnBottom) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.main_container_loyallist_page_mcy");
        elementShouldBePresent("homepage.main_container_loyallist_page_mcy");
        for (String columnSection : columnBottom) {
            switch (columnSection) {
                case "OFFERS ()":
                    Wait.untilElementPresent("homepage.loyallist_table_offers_title_bottom_mcy");
                    Elements.elementShouldBePresent("homepage.loyallist_table_offers_title_bottom_mcy");
                    String offers = findElement("homepage.loyallist_table_offers_title_bottom_mcy").getText();
                    System.out.println(offers);
                    Assert.assertTrue(offers.contains("OFFERS"));
                    break;
                case "EFFECTIVE":
                    Wait.untilElementPresent("homepage.loyallist_table_effective_column_title_mcy");
                    Elements.elementShouldBePresent("homepage.loyallist_table_effective_column_title_mcy");
                    String effective = findElement("homepage.loyallist_table_effective_column_title_mcy").getText();
                    System.out.println(effective);
                    Assert.assertTrue(effective.contains("EFFECTIVE"));
                    break;
                case "EXPIRES":
                    Wait.untilElementPresent("homepage.loyallist_table_expires_column_title_mcy");
                    Elements.elementShouldBePresent("homepage.loyallist_table_expires_column_title_mcy");
                    String expires = findElement("homepage.loyallist_table_expires_column_title_mcy").getText();
                    System.out.println(expires);
                    Assert.assertTrue(expires.contains("EXPIRES"));
                    break;

                default:
                    fail("Section is missing");
            }
        }
    }

    @Then("^the Star Rewards page is displayed$")
    public void theStarRewardsPageIsDisplayed() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.star_rewards_page");
        elementShouldBePresent("homepage.star_rewards_page");
        String starRewardsTabText = findElement("my_clients.star_rewards_tab").getText();
        System.out.println(starRewardsTabText);
        assertEquals("Star Rewards", starRewardsTabText);
    }

    @And("^I click on Edit button from \"([^\"]*)\" section website$")
    public void iClickOnEditButtonFromSectionWebsite(String section) throws Throwable {
        Wait.untilElementPresent("homepage.hints_and_notes_page");
        switch (section) {
            case "NOTES":
                Wait.untilElementPresent("my_clients.edit_button_notes_section");
                elementShouldBePresent("my_clients.edit_button_notes_section");
                Clicks.click("my_clients.edit_button_notes_section");
                break;
            case "HINTS":
                Wait.untilElementPresent("my_clients.edit_button_hints_section");
                elementShouldBePresent("my_clients.edit_button_hints_section");
                Clicks.click("my_clients.edit_button_hints_section");

        }
    }


    @Then("^upon hovering the exclamation mark (\\d+) character are displayed in the NOTES section$")
    public void uponHoveringTheExclamationMarkCharacterAreDisplayedInTheNOTESSection(int value) throws Throwable {
        int characters = findElement("my_clients.notes_section_upon_hovering_exclamation_mark").getText().length();
        System.out.println(characters);
        assertEquals(value, characters);

    }

    @And("^I enter (\\d+) characters in the NOTES section website$")
    public void iEnterCharactersInTheNOTESSectionWebsite(int value) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("my_clients.edit_notes_section");
        TextBoxes.typeTextbox("my_clients.edit_notes_section", RandomStringUtils.randomAlphabetic(value));
    }

    @And("^I click on SAVE button from \"([^\"]*)\" section website$")
    public void iClickOnSAVEButtonFromSectionWebsite(String section) throws Throwable {
        Wait.untilElementPresent("homepage.hints_and_notes_page");
        switch (section) {
            case "NOTES":
                Wait.untilElementPresent("my_clients.save_button_notes_section");
                Clicks.click("my_clients.save_button_notes_section");
                break;
            case "HINTS":
                Wait.untilElementPresent("my_clients.save_button_hints_section");
                Clicks.click("my_clients.save_button_hints_section");
                break;

        }
    }

    @And("^I should see the remove from book button on the client profile$")
    public void iShouldSeeTheRemoveFromBookButtonOnTheClientProfile() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.remove_client_from_book", 10);
        Elements.elementShouldBePresent("homepage.remove_client_from_book");
    }

    @Then("^I should see the \"([^\"]*)\" link in the My Book results list$")
    public void iShouldSeeTheLinkInTheMyBookResultsList(String keyword) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Thread.sleep(1000);
        OmniclientUtils.waitForAngularLoad();
        WebElement searchedKeyword = findElement(By.xpath("(//span[contains(text(),'" + keyword + "')])[1]"));
        String searchAtt = searchedKeyword.getAttribute("class");
        System.out.println("Searched Keyword " + keyword + " class= " + searchAtt);
        Assert.assertEquals("highlight", searchAtt);
    }

    @Then("^I should see the Search Again button on the My Client Search Results page$")
    public void iShouldSeeTheSearchAgainButtonOnTheMyClientSearchResultsPage() throws Throwable {
        Wait.untilElementPresent("homepage.search_results_page");
        elementShouldBePresent("homepage.search_again_button");
    }

    @And("^I should see the searched client on the My Client Search Results page$")
    public void iShouldSeeTheSearchedClientOnTheMyClientSearchResultsPage() throws Throwable {
        Wait.untilElementPresent("homepage.search_results_page");
        elementShouldBePresent("homepage.book_search");

    }


    @Then("^the following information should be displayed in the newly exposed Primary Info section:$")
    public void theFollowingInformationShouldBeDisplayedInTheNewlyExposedPrimaryInfoSection(DataTable info) throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_more_info_prim_info_section");
        OmniclientUtils.waitForAngularLoad();
        for (List<String> list : info.raw()) {
            String infoType = list.get(0);
            String infoValue = list.get(1);
            switch (infoType.toUpperCase()) {
                case "NAME":
                    String primName = findElement("homepage.primary_name_value_manage_clients").getText().toUpperCase();
                    System.out.println("print: " + primName);
                    Assert.assertTrue(primName.contains(infoValue));
                    break;
                case "PRIMARY ADDRESS":
                    String primAddr = findElement("homepage.primary_address_value_manage_clients").getText().toUpperCase();
                    System.out.println("print: " + primAddr);
                    Assert.assertTrue(primAddr.contains(infoValue));
                    break;
                case "PRIMARY PHONE NUMBER":
                    String primPhone = findElement("homepage.primary_phone_value_manage_clients").getText().toUpperCase();
                    System.out.println("print: " + primPhone);
                    Assert.assertTrue(primPhone.contains(infoValue));
                    break;
                case "PRIMARY EMAIL":
                    String primEmail = findElement("homepage.primary_email_value_manage_clients").getText().toUpperCase();
                    System.out.println("print: " + primEmail);
                    Assert.assertTrue(primEmail.contains(infoValue));
                    break;
                default:
                    Assert.fail("[ERROR] No valid INFO type specified");
            }
        }
    }

    @When("^I click the Expose Primary Data button from the Manage Client tab$")
    public void iClickTheExposePrimaryDataButtonFromTheManageClientTab() throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_more_info_link");

        Clicks.click("homepage.manage_client_more_info_link");
    }

    @And("^I click on the MEMOs button in the Communication Center$")
    public void iClickOnTheMEMOsButtonInTheCommunicationCenter() throws Throwable {
        elementShouldBePresent("homepage.memos_button");
        Clicks.click("homepage.memos_button");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @Then("^I should see the expanded Expose Primary Data section on the Manage Client tab$")
    public void iShouldSeeTheExpandedExposePrimaryDataSectionOnTheManageClientTab() throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_more_info_section");
        Wait.untilElementPresent("homepage.manage_client_more_info_prim_info_section");
        Elements.elementShouldBePresent("homepage.manage_client_more_info_section");
        Elements.elementShouldBePresent("homepage.manage_client_more_info_prim_info_section");
        String moreInfoText = findElement("homepage.manage_client_more_info_section").getText().trim();
        Assert.assertEquals(moreInfoText, "To edit the customer's address or phone number in your book only," +
                " update the Preferred Information in the section above. The \"More Info\" data is pulled from our " +
                "customer database for reference only, and cannot be changed.");
    }

    @And("^I click on the Create Memo button$")
    public void iClickOnTheCreateMemoButton() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.create_memo_button", 3);
        elementShouldBePresent("homepage.create_memo_button");
        Clicks.click("homepage.create_memo_button");
    }

    @Then("^I should not see the Expose Primary Data section on the Manage Client tab$")
    public void iShouldNotSeeTheExposePrimaryDataSectionOnTheManageClientTab() throws Throwable {
        Thread.sleep(1000);
        if (findElement("homepage.manage_client_more_info_prim_info_section").isDisplayed()) {
            Assert.fail("The Expose Primary Data section should not be displayed");
        }
    }

    @Then("^I should see the Expose Primary Data link on the Manage Client tab$")
    public void iShouldSeeTheExposePrimaryDataLinkOnTheManageClientTab() throws Throwable {
        Wait.untilElementPresent("homepage.manage_client_more_info_link");
        Wait.untilElementPresent("homepage.manage_client_more_info_chevron");
        Elements.elementShouldBePresent("homepage.manage_client_more_info_link");
        Elements.elementShouldBePresent("homepage.manage_client_more_info_chevron");
        String moreInfoText = findElement("homepage.manage_client_more_info_link").getText().trim().toUpperCase();
        System.out.println(moreInfoText);
        Assert.assertEquals(moreInfoText, "MORE INFO");
    }


    @And("^the link named Wallet is displayed in the header of the wallet section$")
    public void theLinkNamedWalletIsDisplayedInTheHeaderOfTheWalletSection() throws Throwable {
        Wait.untilElementPresent("my_clients.wallet_link_profile");
        Elements.elementShouldBePresent("my_clients.wallet_link_profile");
    }

    @And("^the Show Shopping Passes link is displayed in the header of the wallet section$")
    public void theShowShoppingPassesLinkIsDisplayedInTheHeaderOfTheWalletSection() throws Throwable {
        Wait.untilElementPresent("my_clients.show_shopping_passes_link_profile");
        Elements.elementShouldBePresent("my_clients.show_shopping_passes_link_profile");
        String showText = findElement("my_clients.show_shopping_passes_link_profile").getText();
        Assert.assertTrue(showText.contains("Show Shopping Passes"));
    }

    @When("^I click on the Show Shopping Passes link$")
    public void iClickOnTheShowShoppingPassesLink() throws Throwable {
        Wait.untilElementPresent("my_clients.show_shopping_passes_link_profile");
        Clicks.click("my_clients.show_shopping_passes_link_profile");
    }

    @Then("^the Wallet info is displayed in Client Profile view$")
    public void theWalletInfoIsDisplayedInClientProfileView() throws Throwable {
        Thread.sleep(1000);
        Wait.untilElementPresent("my_clients.expanded_wallet_profile");
        Elements.elementShouldBePresent("my_clients.expanded_wallet_profile");
    }

    @And("^the Show Shopping Passes button changes to Hide Shopping Passes$")
    public void theShowShoppingPassesButtonChangesToHideShoppingPasses() throws Throwable {
        Wait.untilElementPresent("my_clients.hide_shopping_passes_link_profile");
        Elements.elementShouldBePresent("my_clients.hide_shopping_passes_link_profile");
        String hideText = findElement("my_clients.hide_shopping_passes_link_profile").getText();
        Assert.assertTrue(hideText.contains("Hide Shopping Passes"));

    }


    @And("^Only Credits Cards with Shopping Passes will display$")
    public void onlyCreditsCardsWithShoppingPassesWillDisplay() throws Throwable {
        Wait.untilElementPresent("my_clients.shopping_pass_profile");
        Elements.elementShouldBePresent("my_clients.shopping_pass_profile");
    }

    @When("^I click on the Hide Shopping Passes button$")
    public void iClickOnTheHideShoppingPassesButton() throws Throwable {
        Elements.elementShouldBePresent("my_clients.hide_shopping_passes_link_profile");
        Clicks.click("my_clients.hide_shopping_passes_link_profile");
    }

    @Then("^the Wallet info is not displayed in Client Profile view$")
    public void theWalletInfoIsNotDisplayedInClientProfileView() throws Throwable {
        Wait.untilElementNotPresent("my_clients.expanded_wallet_profile");
        Elements.elementShouldBePresent("my_clients.show_shopping_passes_link_profile");
        String showText = findElement("my_clients.show_shopping_passes_link_profile").getText();
        Assert.assertTrue(showText.contains("Show Shopping Passes"));

    }

    @When("^I click on the Wallet link from the header of the wallet section$")
    public void iClickOnTheWalletLinkFromTheHeaderOfTheWalletSection() throws Throwable {
        Wait.untilElementPresent("my_clients.wallet_link_profile");
        Wait.untilElementNotPresent("homepage.loading_page");
        Clicks.click("my_clients.wallet_link_profile");
    }

    @Then("^the Wallet page is displayed$")
    public void theWalletPageIsDisplayed() throws Throwable {
        Wait.untilElementPresent("my_clients.clients_wallet_tab");
        Elements.elementShouldBePresent("my_clients.clients_wallet_tab");
    }


    @And("^Credit Card will not be displayed$")
    public void creditCardWillNotBeDisplayed() throws Throwable {
        String walletContent = findElement("my_clients.no_credit_cards_listed_profile").getText().trim();
        System.out.println("Wallet Size= " + walletContent.length());
        if (walletContent.length() > 4) {
            Assert.fail("Card there");
        }
    }

    @And("^I type \"([^\"]*)\" in the MEMOS textbox$")
    public void iTypeInTheMEMOSTextbox(String value) throws Throwable {
        Wait.secondsUntilElementPresent("homepage.create_memo_textbox", 3);
        elementShouldBePresent("homepage.create_memo_textbox");
        TextBoxes.typeTextbox("homepage.create_memo_textbox", value);

    }

    @And("^I click on the Memos Save button$")
    public void iClickOnTheMemosSaveButton() throws Throwable {
        Wait.untilElementPresent("homepage.save_memo_button");
        elementShouldBePresent("homepage.save_memo_button");
        Clicks.click("homepage.save_memo_button");
    }

    @And("^I click on the checkbox to select a memo$")
    public void iClickOnTheCheckboxToSelectAMemo() throws Throwable {
        Wait.untilElementPresent("homepage.memo_checkbox");
        elementShouldBePresent("homepage.memo_checkbox");
        Clicks.click("homepage.memo_checkbox");
        Thread.sleep(3000);
    }


    @Then("^I click the Delete Memo button$")
    public void iClickTheDeleteMemoButton() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.delete_memo_button", 2);
        elementShouldBePresent("homepage.delete_memo_button");
        Clicks.click("homepage.delete_memo_button");
    }

    @And("^I click YES on the delete memo popup$")
    public void iClickYESOnTheDeleteMemoPopup() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.delete_memo_popup_yes", 2);
        elementShouldBePresent("homepage.delete_memo_popup_yes");
        Clicks.click("homepage.delete_memo_popup_yes");
    }

    @Then("^I should see the Save button is disabled$")
    public void iShouldSeeTheSaveButtonIsDisabled() throws Throwable {
        elementShouldBePresent("homepage.save_memo_button");
        Assert.assertFalse("Button is not disabled", Elements.findElement("homepage.save_memo_button").isEnabled());
    }

    @Then("^I click the memo dropdown chevron of the newly created memo$")
    public void iClickTheMemoDropdownChevronOfTheNewlyCreatedMemo() throws Throwable {
        elementShouldBePresent("homepage.memo_dropdown_chevron");
        Clicks.click("homepage.memo_dropdown_chevron");
    }

    @And("^I see the memo with the following details \"([^\"]*)\"$")
    public void iSeeTheMemoWithTheFollowingDetails(String memo) throws Throwable {
        Wait.untilElementPresent("homepage.create_memo_textbox");
        elementShouldBePresent("homepage.create_memo_textbox");
        String Memo = findElement("homepage.create_memo_textbox").getAttribute("value");
        System.out.println("The memo description =" + Memo);
        Assert.assertEquals(memo, Memo);
    }

    @Then("^I will validate that the MEMOs count incremented by (\\d+)$")
    public void iWillValidateThatTheMEMOsCountIncrementedBy(int arg0) throws Throwable {
        Wait.untilElementPresent("homepage.memos_count");
        elementShouldBePresent("homepage.memos_count");
        String elementNeededs = findElement("homepage.memos_count").getText();
        int newcountMemos = Integer.parseInt(elementNeededs); //grab the element
        Assert.assertEquals(countMemos + 1, newcountMemos);
        System.out.println(newcountMemos);
    }

    @And("^I check the MEMOs count$")
    public void iCheckTheMEMOsCount() throws Throwable {
        Wait.untilElementPresent("homepage.memos_count");
        elementShouldBePresent("homepage.memos_count");
        String elementNeededs = findElement("homepage.memos_count").getText();
        countMemos = Integer.parseInt(elementNeededs);
        System.out.println(countMemos);
    }

    @Then("^I should be on the Macy's homepage$")
    public void iShouldBeOnTheMacySHomepage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.homepage_tab");
    }

    @And("^I click the Memos navigation X Icon$")
    public void iClickTheMemosNavigationXIcon() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.memo_navigation_x_icon", 2);
        elementShouldBePresent("homepage.memo_navigation_x_icon");
        Clicks.click("homepage.memo_navigation_x_icon");
    }

    @Then("^I will validate the Memos count decremented by (\\d+)$")
    public void iWillValidateTheMemosCountDecrementedBy(int arg0) throws Throwable {
        Wait.untilElementPresent("homepage.memos_count");
        elementShouldBePresent("homepage.memos_count");
        String elementNeededs = findElement("homepage.memos_count").getText();
        int newcountMemos = Integer.parseInt(elementNeededs); //grab the element
        Assert.assertEquals(countMemos - 1, newcountMemos);
        System.out.println(newcountMemos);
    }

    @And("^I validate that the Create To Do button from homepage is labeled as \"([^\"]*)\"$")
    public void iValidateThatTheCreateToDoButtonFromHomepageIsLabeledAs(String toDoLabel) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        String createToDoButton = findElement("homepage.create_to_do_button_homepage").getText();
        System.out.println(createToDoButton);
        assertEquals(toDoLabel, createToDoButton);
    }

    @And("^I select the checkbox of the second client from the HOMEPAGE clients list$")
    public void iSelectTheCheckboxOfTheSecondClientFromTheHOMEPAGEClientsList() throws Throwable {
        Wait.untilElementPresent("homepage.second_checkbox_client_list_homepage");
        Clicks.click("homepage.second_checkbox_client_list_homepage");
    }

    @And("^I select the checkbox of the third client from the HOMEPAGE clients list$")
    public void iSelectTheCheckboxOfTheThirdClientFromTheHOMEPAGEClientsList() throws Throwable {
        Wait.untilElementPresent("homepage.third_checkbox_client_list_homepage");
        Clicks.click("homepage.third_checkbox_client_list_homepage");
    }

    @Then("^the Create To Do button label will update to \"([^\"]*)\"$")
    public void theCreateToDoButtonLabelWillUpdateTo(String toDoLabel2) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        String createToDoButton = findElement("homepage.create_to_do_button_homepage").getText();
        System.out.println(createToDoButton);
        assertEquals(toDoLabel2, createToDoButton);
    }

    @And("^the CANCEL button should be displayed in the Create To Do view$")
    public void theCANCELButtonShouldBeDisplayedInTheCreateToDoView() throws Throwable {
        Wait.untilElementPresent("homepage.cancel_button_create_to_dos_page_new");
        elementShouldBePresent("homepage.cancel_button_create_to_dos_page_new");
    }

    @And("^the names of the selected customers are displayed next to the customer field \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theNamesOfTheSelectedCustomersAreDisplayedNextToTheCustomerField(String firstClient, String secondClient, String thirdClient) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.selected_clients_create_to_dos_page_new");
        elementShouldBePresent("homepage.selected_clients_create_to_dos_page_new");
        String clientList = findElement("homepage.selected_clients_create_to_dos_page_new").getText();
        System.out.println(clientList);
        Assert.assertTrue(clientList.contains(firstClient));
        Assert.assertTrue(clientList.contains(secondClient));
        Assert.assertTrue(clientList.contains(thirdClient));

    }

    @And("^I enter a title \"([^\"]*)\" in the input field on CREATE TO DOS page$")
    public void iEnterATitleInTheInputFieldOnCREATETODOSPage(String toDoTitle) throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_title_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_title_field", toDoTitle);
    }

    @And("^I select a valid date in Create To Dos View on CREATE TO DOS page$")
    public void iSelectAValidDateInCreateToDosViewOnCREATETODOSPage() throws Throwable {
        LocalDate date = LocalDate.now();
        TextBoxes.typeTextbox("homepage.create_to_dos_date_field", DATE_TIME_FORMATTER.format(date));
    }

    @And("^I enter a description \"([^\"]*)\" in the input field on CREATE TO DOS page$")
    public void iEnterADescriptionInTheInputFieldOnCREATETODOSPage(String descriptionToDo) throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_description_field");
        elementShouldBePresent("homepage.create_to_dos_description_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_description_field", descriptionToDo);
    }

    @And("^I click on CANCEL button from Create To Dos page$")
    public void iClickOnCANCELButtonFromCreateToDosPage() throws Throwable {
        Wait.untilElementPresent("homepage.cancel_button_create_to_dos_page_new");
        elementShouldBePresent("homepage.cancel_button_create_to_dos_page_new");
        Clicks.click("homepage.cancel_button_create_to_dos_page_new");
    }

    @Then("^disregard changes message is displayed$")
    public void disregardChangesMessageIsDisplayed() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.disregard_changes_message_create_to_dos_new");
        String disregardMsgCreateToDos = findElement("homepage.disregard_changes_message_create_to_dos_new").getText();
        System.out.println(disregardMsgCreateToDos);
        assertEquals(DISREGARD_CHANGES, disregardMsgCreateToDos);


    }

    @And("^I click OK on the disregard changes popup message Create To Dos page$")
    public void iClickOKOnTheDisregardChangesPopupMessageCreateToDosPage() throws Throwable {
        Wait.untilElementPresent("homepage.ok_button_disregard_changes_popup_create_to_dos_new");
        elementShouldBePresent("homepage.ok_button_disregard_changes_popup_create_to_dos_new");
        Clicks.click("homepage.ok_button_disregard_changes_popup_create_to_dos_new");
    }

    @Then("^the Macys homepage is displayed website$")
    public void theMacysHomepageIsDisplayedWebsite() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("homepage.associate_homepage");
        elementShouldBePresent("homepage.associate_homepage");
    }

    @And("^I click on X buttons next to the CUSTOMER field create to do from homepage$")
    public void iClickOnXButtonsNextToTheCUSTOMERFieldCreateToDoFromHomepage() throws Throwable {
        Wait.untilElementPresent("homepage.remove_customer_button_create_to_dos_new");
        Boolean remove = true;
        while (remove) {
            try {
                findElement("homepage.remove_customer_button_create_to_dos_new").isDisplayed();
                Clicks.click("homepage.remove_customer_button_create_to_dos_new");
            } catch (Exception e) {
                remove = false;
                System.out.println("Done!");
            }
        }
    }

    @Then("^the removed clients are no longer displayed in Create To Dos page$")
    public void theRemovedClientsAreNoLongerDisplayedInCreateToDosPage() throws Throwable {
        String clientList = findElement("homepage.selected_clients_create_to_dos_page_new").getText();
        System.out.println(clientList);
        Assert.assertTrue(clientList.isEmpty());
    }

    @And("^I select \"([^\"]*)\" from the phone type dropdown on Create Client page$")
    public void iSelectFromThePhoneTypeDropdownOnCreateClientPage(String phoneType) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_phone_type");
        DropDowns.selectByText("homepage.add_new_client_phone_type", phoneType);
    }

    @And("^I should see \"([^\"]*)\" as the phone type in the Preferred Information$")
    public void iShouldSeeAsThePhoneTypeInThePreferredInformation(String phoneType) throws Throwable {
        Wait.untilElementPresent("homepage.pref_phone_row_manage_clients");
        String phoneLine = findElement("homepage.pref_phone_row_manage_clients").getText();
        Assert.assertTrue(phoneLine.contains(phoneType));
    }

    @When("^I enter first name \"([^\"]*)\" in the first name field$")
    public void iEnterFirstNameInTheFirstNameField(String fname) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_fname");
        TextBoxes.typeTextbox("homepage.add_new_client_fname", fname);
    }

    @And("^I enter last name \"([^\"]*)\" in the last name field$")
    public void iEnterLastNameInTheLastNameField(String lname) throws Throwable {
        Wait.untilElementPresent("homepage.add_new_client_lname");
        TextBoxes.typeTextbox("homepage.add_new_client_lname", lname);
    }

    @Then("^I should see the text radio button \"([^\"]*)\" on Create Client page$")
    public void iShouldSeeTheTextRadioButtonOnCreateClientPage(String status) throws Throwable {
        WebElement textRadio = findElement("homepage.add_new_client_preff_text");
        switch (status) {
            case "disabled":
                Assert.assertTrue("Text radio button should be DISABLED", !textRadio.isEnabled());
                break;
            case "enabled":
                Assert.assertTrue("Text radio button should be ENABLED", textRadio.isEnabled());
                Clicks.click(textRadio);
                break;
        }
    }

    @Then("^I should see the Phone Type required error popup on the Create Client page$")
    public void iShouldSeeThePhoneTypeRequiredErrorPopupOnTheCreateClientPage() throws Throwable {
        Wait.untilElementPresent("homepage.error_popup_phone_homepage");
        Elements.elementShouldBePresent("homepage.error_popup_phone_homepage");
        String errorMsg = findElement("homepage.error_popup_phone_homepage").getText();
        Assert.assertTrue(errorMsg.contains("Phone Type is required."));
    }

    @And("^I type \"([^\"]*)\" in the texting Message textbox$")
    public void iTypeInTheTextingMessageTextbox(String message) throws Throwable {
        Wait.secondsUntilElementPresent("homepage.text_message_textbox", 3);
        Elements.elementShouldBePresent("homepage.text_message_textbox");
        TextBoxes.typeTextbox("homepage.text_message_textbox", message);
    }

    @Then("^I should see the Create List dashboard$")
    public void iShouldSeeTheCreateListDashboard() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.create_list_dashboard");
        elementShouldBePresent("todos.create_list_dashboard");
    }

    @And("^I click the text message send button$")
    public void iClickTheTextMessageSendButton() throws Throwable {
        Elements.elementShouldBePresent("homepage.text_message_send_button");
        Clicks.click("homepage.text_message_send_button");
        Thread.sleep(3000);
    }

    @When("^I click the Create Custom List button on the Create List dashboard$")
    public void iClickTheCreateCustomListButtonOnTheCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.my_lists_header");
        Clicks.click("todos.my_lists_header");
        Wait.untilElementPresent("todos.new_list_button");
        Clicks.click("todos.new_list_button");
    }

    @Then("^I should see \"([^\"]*)\" in the text message textbox$")
    public void iShouldSeeInTheTextMessageTextbox(String texting) throws Throwable {
        Wait.secondsUntilElementPresent("homepage.outbound_text_message", 3);
        Elements.elementShouldBePresent("homepage.outbound_text_message");
        String Text = findElement("homepage.outbound_text_message").getText();
        //String Text = findElement("homepage.outbound_text_message").getAttribute("value");
        System.out.println("The text description =" + Text);
        Assert.assertTrue(Text.contains(texting));
    }

    @And("^I click on the X icon on the texting screen$")
    public void iClickOnTheXIconOnTheTextingScreen() throws Throwable {
        Elements.elementShouldBePresent("homepage.text_message_x_icon");
        Clicks.click("homepage.text_message_x_icon");
    }

    @And("^I click on the text message icon$")
    public void iClickOnTheTextMessageIcon() throws Throwable {
        Wait.secondsUntilElementPresent("homepage.text_message_icon", 3);
        hoverForSelection("homepage.text_message_icon");
        Elements.elementShouldBePresent("homepage.text_message_icon");
        Clicks.click("homepage.text_message_icon");
    }

    @When("^I click on the the clients name \"([^\"]*)\" from the MY CLIENTS List$")
    public void iClickOnTheTheClientsNameFromTheMYCLIENTSList(String name) throws Throwable {
        hoverForSelection("homepage.kate_browns");
        WebElement clientName = findElement(By.linkText(name));
        Clicks.click(clientName);
    }

    @When("^I click on the preferred method of contact$")
    public void iClickOnThePreferredMethodOfContact() throws Throwable {
        Elements.elementShouldBePresent("homepage.pref_contact_method");
        Clicks.click("homepage.pref_contact_method");
    }


    @Then("^I should see the New List view$")
    public void iShouldSeeTheNewListView() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.create_list_dashboard");
        elementShouldBePresent("todos.create_list_dashboard");
        String listName = findElement("todos.list_name_input_field").getAttribute("placeholder");
        System.out.println(listName);
        Assert.assertTrue(listName.contains("List Name"));
    }

    @And("^I should see the List Title on the New List view$")
    public void iShouldSeeTheListTitleOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.list_name_input_field");
        String listName = findElement("todos.list_name_input_field").getAttribute("placeholder");
        System.out.println(listName);
        Assert.assertTrue(listName.contains("List Name"));
    }

    @And("^I should see the following sections on the New List view$")
    public void iShouldSeeTheFollowingSectionsOnTheNewListView(List<String> sections) throws Throwable {
        Wait.untilElementPresent("todos.new_list_dashboard");
        for (String newSection : sections) {
            switch (newSection) {
                case "STAR REWARDS":
                    String section1 = findElement("todos.first_section_new_list_view").getText();
                    System.out.println(section1);
                    Assert.assertTrue(section1.contains("STAR REWARDS"));
                    break;

                case "LOYALLIST":
                    String section11 = findElement("todos.first_section_new_list_view").getText();
                    System.out.println(section11);
                    Assert.assertTrue(section11.contains("LOYALLIST"));
                    break;

                case "TRANSACTIONS":
                    String section2 = findElement("todos.second_section_new_list_view").getText();
                    System.out.println(section2);
                    Assert.assertTrue(section2.contains("TRANSACTIONS"));
                    break;

                case "CUSTOMER":
                    String section3 = findElement("todos.third_section_new_list_view").getText();
                    System.out.println(section3);
                    Assert.assertTrue(section3.contains("CUSTOMER PROFILE"));
                    break;

            }
        }
    }

    @And("^I should see the Preview Pane on the New List view$")
    public void iShouldSeeThePreviewPaneOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.preview_pane_new_list");
        elementShouldBePresent("todos.preview_pane_new_list");
    }

    @When("^I click the chevron of the first section on the New List view$")
    public void iClickTheChevronOfTheFirstSectionOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.first_section_chevron");
        elementShouldBePresent("todos.first_section_chevron");
        Clicks.click("todos.first_section_chevron");
    }

    @Then("^I should see the first section collapsed on the New List view$")
    public void iShouldSeeTheFirstSectionCollapsedOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.first_section_new_list_view");
        elementShouldBePresent("todos.first_section_new_list_view");

        WebElement firstSection = findElement("todos.first_section_first_subsection");
        Assert.assertFalse("Section not collapsed", firstSection.isDisplayed());

        WebElement secondSection = findElement("todos.first_section_second_subsection");
        Assert.assertFalse("Section not collapsed", secondSection.isDisplayed());

        WebElement thirdSection = findElement("todos.first_section_third_subsection");
        Assert.assertFalse("Section not collapsed", thirdSection.isDisplayed());
    }

    @Then("^I should see the first section expanded on the New List view$")
    public void iShouldSeeTheFirstSectionExpandedOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.first_section_new_list_view");
        elementShouldBePresent("todos.first_section_new_list_view");

        WebElement firstSection = findElement("todos.first_section_first_subsection");
        Assert.assertTrue("Section is collapsed", firstSection.isDisplayed());

        WebElement secondSection = findElement("todos.first_section_second_subsection");
        Assert.assertTrue("Section is collapsed", secondSection.isDisplayed());

        WebElement thirdSection = findElement("todos.first_section_third_subsection");
        Assert.assertTrue("Section is collapsed", thirdSection.isDisplayed());
    }

    @When("^I select a filter criteria on the New List view$")
    public void iSelectAFilterCriteriaOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.first_section_first_subsection_first_filter_option");
        elementShouldBePresent("todos.first_section_first_subsection_first_filter_option");
        Clicks.click("todos.first_section_first_subsection_first_filter_option");
    }

    @Then("^I should see the filter criteria selected on the New List view$")
    public void iShouldSeeTheFilterCriteriaSelectedOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.close_first_section_first_subsection_first_filter_option");
        WebElement closeFilterX = findElement("todos.close_first_section_first_subsection_first_filter_option");
        Assert.assertTrue("Filter is selected", closeFilterX.isDisplayed());
    }

    @When("^I remove the filter criteria on the New List view$")
    public void iRemoveTheFilterCriteriaOnTheNewListView() throws Throwable {
        Clicks.click("todos.close_first_section_first_subsection_first_filter_option");
    }

    @Then("^I should no longer see the filter criteria selected on the New List view$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.create_list_dashboard");
        WebElement closeFilterX = findElement("todos.close_first_section_first_subsection_first_filter_option");
        Assert.assertFalse("Filter is not selected", closeFilterX.isDisplayed());
    }

    @And("^I should see Create Lists title on left side panel$")
    public void iShouldSeeCreateListsTitleOnLeftSidePanel() throws Throwable {
        Wait.untilElementPresent("todos.create_lists_header");
        elementShouldBePresent("todos.create_lists_header");
        String createListTitle = findElement("todos.create_lists_header").getText();
        System.out.println(createListTitle);
        Assert.assertTrue(createListTitle.contains("Create Lists"));

    }

    @And("^I should see the Back button on the New List view$")
    public void iShouldSeeTheBackButtonOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.back_button_create_list");
        elementShouldBePresent("todos.back_button_create_list");
    }

    @When("^I click the Back button on the New List view$")
    public void iClickTheBackButtonOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.back_button_create_list");
        Clicks.click("todos.back_button_create_list");
    }

    @And("^I input client name \"([^\"]*)\" in the input filed from Create ToDo page$")
    public void iInputClientNameInTheInputFiledFromCreateToDoPage(String ClientName) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("homepage.create_to_dos_client_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_client_field", ClientName);
    }

    @And("^I select the full name \"([^\"]*)\" from client dropdown Create ToDo page$")
    public void iSelectTheFullNameFromClientDropdownCreateToDoPage(String NameClient) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.secondsUntilElementPresent(By.partialLinkText(NameClient), 20);
        WebElement fullname = Elements.findElement(By.partialLinkText(NameClient));
        Clicks.hoverForSelection(fullname);
        Clicks.click(fullname);
    }

    @And("^user clicks on the ADD button form Create TO Dos page$")
    public void userClicksOnTheADDButtonFormCreateTODosPage() throws Throwable {
        Wait.untilElementPresent("todos.add_button_create_list");
        Clicks.click("todos.add_button_create_list");
    }

    @Then("^the selected user \"([^\"]*)\" is displayed in the customers list$")
    public void theSelectedUserIsDisplayedInTheCustomersList(String clientInList) throws Throwable {
        Wait.untilElementPresent("todos.customer_list_create_list_page");
        elementShouldBePresent("todos.customer_list_create_list_page");
        String clientsList = findElement("todos.customer_list_create_list_page").getText().trim();
        assertTrue(clientsList.contains(clientInList));
    }

    @Then("^the Show All link is displayed at the bottom of Customers list Create To Dos page$")
    public void theShowAllLinkIsDisplayedAtTheBottomOfCustomersListCreateToDosPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        elementShouldBePresent("todos.show_all_link_create_list_page");
    }

    @And("^user clicks on Show All link from Create To Dos page$")
    public void userClicksOnShowAllLinkFromCreateToDosPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        elementShouldBePresent("todos.show_all_link_create_list_page");
        Clicks.click("todos.show_all_link_create_list_page");
    }

    @Then("^the scroll bar is displayed in the left side of the customers list$")
    public void theScrollBarIsDisplayedInTheLeftSideOfTheCustomersList() throws Throwable {
        Wait.untilElementPresent("todos.customer_list_scroll_create_list_page");
        elementShouldBePresent("todos.customer_list_scroll_create_list_page");
    }

    @And("^the number of customers displayed in list is increased Create To Dos page$")
    public void theNumberOfCustomersDisplayedInListIsIncreasedCreateToDosPage() throws Throwable {
        Wait.untilElementPresent("todos.customer_list_create_list_page");
        elementShouldBePresent("todos.customer_list_create_list_page");
        String elementNeededs = findElement("todos.customer_list_create_list_page").getText();
        int newCustomersList = elementNeededs.length();
        System.out.println(newCustomersList);
        Assert.assertTrue(newCustomersList > countCustomersList);


    }

    @And("^user checks the number of customers displayed in Create List view$")
    public void userChecksTheNumberOfCustomersDisplayedInCreateListView() throws Throwable {
        Wait.untilElementPresent("todos.customer_list_create_list_page");
        elementShouldBePresent("todos.customer_list_create_list_page");
        String elementNeededs = findElement("todos.customer_list_create_list_page").getText();
        countCustomersList = elementNeededs.length();
        System.out.println(countCustomersList);
    }

    @Then("^ADD button is displayed as disabled Create List page$")
    public void addButtonIsDisplayedAsDisabledCreateListPage() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        WebElement addButton = findElement("todos.add_button_create_list");
        Assert.assertTrue("Add button should be disabled", !addButton.isEnabled());

    }

    @And("^I should see TO DO LISTS header$")
    public void iShouldSeeTODOLISTSHeader() throws Throwable {
        Wait.untilElementPresent("todos.to_do_list_header");
        elementShouldBePresent("todos.to_do_list_header");
        String toDoListTitle = findElement("todos.to_do_list_header").getText();
        System.out.println(toDoListTitle);
        Assert.assertTrue(toDoListTitle.contains("TO DO LISTS"));
    }

    @And("^I should see MY LISTS header$")
    public void iShouldSeeMYLISTSHeader() throws Throwable {
        Wait.untilElementPresent("todos.my_lists_header");
        elementShouldBePresent("todos.my_lists_header");
        String myListTitle = findElement("todos.my_lists_header").getText();
        System.out.println(myListTitle);
        Assert.assertTrue(myListTitle.contains("MY LISTS"));
    }

    @And("^I should see Create Custom List button on left side panel$")
    public void iShouldSeeCreateCustomListButtonOnLeftSidePanel() throws Throwable {
        Wait.untilElementPresent("todos.new_list_button");
        elementShouldBePresent("todos.new_list_button");
    }

    @And("^I should see Create To Dos button on right hand side panel$")
    public void iShouldSeeCreateToDosButtonOnRightHandSidePanel() throws Throwable {
        Wait.untilElementPresent("todos.create_to_dos_button");
        elementShouldBePresent("todos.create_to_dos_button");
    }

    @And("^I should not see the Search Options$")
    public void iShouldNotSeeTheSearchOptions() throws Throwable {
        Wait.untilElementPresent("todos.my_lists_header");
        assertFalse("Section is present", elementInView("left_nav.client_search_section"));
    }

    @Then("^I should not see the Communication Center$")
    public void i_should_not_see_the_Communication_Center() throws Throwable {
        Wait.untilElementPresent("todos.my_lists_header");
        assertFalse("Section is present", elementInView("left_nav.communication_center"));
    }

    @And("^the Dynamic Column \"([^\"]*)\" will be displayed$")
    public void theDynamicColumnWillBeDisplayed(String columnName) throws Throwable {
        Wait.untilElementPresent("todos.event_information_page_dynamic_column");
        Elements.elementShouldBePresent("todos.event_information_page_dynamic_column");
        String actualColumnName = findElement("todos.event_information_page_dynamic_column").getText();
        Assert.assertTrue(actualColumnName.contains(columnName));
    }

    @And("^I should see the title Upcoming Birthdays on the Create List dashboard$")
    public void iShouldSeeTheTitleUpcomingBirthdaysOnTheCreateListDashboard() throws Throwable {
        WebElement upcomBirth = findElement(By.id("description" + upcomingBirthPosition));
        elementShouldBePresent(upcomBirth);
        String upcomBirthTitle = upcomBirth.getText();
        System.out.println(upcomBirthTitle);
        assertTrue(upcomBirthTitle.contains("Upcoming Birthdays"));
    }

    @And("^the Dynamic Column will not be displayed$")
    public void theDynamicColumnWillNotBeDisplayed() throws Throwable {
        Wait.untilElementPresent("homepage.event_information_page");
        Assert.assertFalse("Dynamic Column is Present", elementInView("todos.event_information_page_dynamic_column"));
    }

    @And("^I should see the subtitle \"([^\"]*)\" for Upcoming Birthdays$")
    public void iShouldSeeTheSubtitleForUpcomingBirthdays(String days) throws Throwable {
        WebElement upcomBirth = findElement(By.xpath("//*[@id='options" + upcomingBirthPosition + "_select']/option[2]"));
        elementShouldBePresent(upcomBirth);
        Assert.assertTrue(upcomBirth.getAttribute("selected").equals("true"));
        String upcomBirthText = upcomBirth.getText();
        System.out.println(upcomBirthText);
        assertTrue(upcomBirthText.contains(days));
    }


    @When("^I expand the subtitle Next 30 Days for Upcoming Birthdays$")
    public void iExpandTheSubtitleNext30DaysForUpcomingBirthdays() throws Throwable {
        WebElement upcomBirth = findElement(By.xpath("//*[@id='options" + upcomingBirthPosition + "_select']"));
        elementShouldBePresent(upcomBirth);
        Clicks.click(upcomBirth);
    }

    @Then("^I should see the following values in the Birthdays dropdown on the Create List dashboard$")
    public void iShouldSeeTheFollowingValuesInTheBirthdaysDropdownOnTheCreateListDashboard(List<String> options) throws Throwable {
        Wait.untilElementPresent("todos.upcoming_birthdays_dropdown_create_list");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Next 14 Days":
                     WebElement option1 = findElement(By.xpath("//*[@id='options" + upcomingBirthPosition + "_select']/option[1]"));
                    elementShouldBePresent(option1);
                    String option1Text = option1.getText();
                    System.out.println(option1Text);
                    Assert.assertTrue(option1Text.contains(foundOption));
                    break;
                case "Next 30 Days":
                    WebElement option2 = findElement(By.xpath("//*[@id='options" + upcomingBirthPosition + "_select']/option[2]"));
                    elementShouldBePresent(option2);
                    String option2Text = option2.getText();
                    System.out.println(option2Text);
                    Assert.assertTrue(option2Text.contains(foundOption));
                    break;
                case "Next 60 Days":
                    WebElement option3 = findElement(By.xpath("//*[@id='options" + upcomingBirthPosition + "_select']/option[3]"));
                    elementShouldBePresent(option3);
                    String option3Text = option3.getText();
                    System.out.println(option3Text);
                    Assert.assertTrue(option3Text.contains(foundOption));
                    break;
            }
        }
    }

    @When("^I select the title Upcoming Birthdays on the Create List dashboard$")
    public void iSelectTheTitleUpcomingBirthdaysOnTheCreateListDashboard() throws Throwable {
        WebElement upcomBirth = findElement(By.id("description" + upcomingBirthPosition));
        elementShouldBePresent(upcomBirth);
        Clicks.click(upcomBirth);
    }

    @Then("^the selected pregenerated list will display on the Create List dashboard$")
    public void theSelectedPregeneratedListWillDisplayOnTheCreateListDashboard() throws Throwable {
        Wait.untilElementPresent("todos.pregenerated_list_content_create_list");
        elementShouldBePresent("todos.pregenerated_list_content_create_list");
    }

    @And("^the customer count will display on the Upcoming Birthday Create List dashboard$")
    public void theCustomerCountWillDisplayOnTheUpcomingBirthdayCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement custCount = findElement(By.id("count" + upcomingBirthPosition));
        elementShouldBePresent(custCount);
        String countNumber = custCount.getText().trim();
        Matcher matcher = Pattern.compile("\\(([0-9]+)\\)").matcher(countNumber);
        if (matcher.find()) {
            assertTrue("Customer count is displayed", true);
        } else {
            Assert.fail("No customer count is displayed");
        }
    }


    @And("^the list should be sorted by BIRTHDATE$")
    public void theListShouldBeSortedByBIRTHDATE() throws Throwable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        List<WebElement> elements = findElements("todos.birthday_column_create_list");
        compareSortedDates(simpleDateFormat, elements);
    }

    @And("^the following columns should be displayed on the Create List dashboard$")
    public void theFollowingColumnsShouldBeDisplayedOnTheCreateListDashboard(List<String> columns) throws Throwable {
        Wait.untilElementPresent("todos.all_columns_title_header");
        for (String displayedColumns : columns) {
            switch (displayedColumns) {
                case "Birthday":
                    elementShouldBePresent("todos.birthday_column_dynamic");
                    String column1 = findElement("todos.birthday_column_dynamic").getText();
                    System.out.println(column1);
                    Assert.assertTrue(column1.contains(displayedColumns));
                    break;
                case "$/All":
                    elementShouldBePresent("todos.all_column_create_list");
                    String column2 = findElement("todos.all_column_create_list").getText();
                    System.out.println(column2);
                    Assert.assertTrue(column2.contains(displayedColumns));
                    break;
                case "$/Me":
                    elementShouldBePresent("todos.me_column_create_list");
                    String column3 = findElement("todos.me_column_create_list").getText();
                    System.out.println(column3);
                    Assert.assertTrue(column3.contains(displayedColumns));
                    break;
                case "Visits":
                    elementShouldBePresent("todos.visits_column_create_list");
                    String column4 = findElement("todos.visits_column_create_list").getText();
                    System.out.println(column4);
                    Assert.assertTrue(column4.contains(displayedColumns));
                    break;
                case "Account Open Date":
                    elementShouldBePresent("todos.birthday_column_dynamic");
                    String column5 = findElement("todos.birthday_column_dynamic").getText();
                    System.out.println(column5);
                    Assert.assertTrue(column5.contains(displayedColumns));
                    break;
                case "Rank":
                    elementShouldBePresent("todos.rank_column_dynamic");
                    String columnRank = findElement("todos.rank_column_dynamic").getText();
                    System.out.println(columnRank);
                    Assert.assertTrue(columnRank.contains(displayedColumns));
                    break;
                case "Last Visit":
                    elementShouldBePresent("todos.birthday_column_dynamic");
                    String column6 = findElement("todos.birthday_column_dynamic").getText();
                    System.out.println(column6);
                    Assert.assertTrue(column6.contains(displayedColumns));
                    break;
                case "$/Query":
                    elementShouldBePresent("todos.trans_gmm_query_dollars_header");
                    String column7 = findElement("todos.trans_gmm_query_dollars_header").getText();
                    System.out.println(column7);
                    Assert.assertTrue(column7.contains(displayedColumns));
                    break;
                default: Assert.fail("Not a Valid Column");
            }
        }
    }

    @When("^I select the Create To Dos button from Create List dashboard$")
    public void iSelectTheCreateToDosButtonFromCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.create_todos_button_create_list_view");
        Clicks.click("todos.create_todos_button_create_list_view");
        Wait.untilElementNotPresent("homepage.loading_page");
    }

    @And("^I should see the title Opened a New Credit Account with Me on the Create List dashboard$")
    public void iShouldSeeTheTitleOpenedANewCreditAccountWithMeOnTheCreateListDashboard() throws Throwable {
        WebElement newAcc = findElement(By.id("description" + newCreditAccPosition));
        elementShouldBePresent(newAcc);
        String newAccTitle = newAcc.getText();
        System.out.println(newAccTitle);
        assertTrue(newAccTitle.contains("New Accounts Opened w/Me"));
    }

    @And("^I should see the subtitle \"([^\"]*)\" for Opened a New Credit Account with Me$")
    public void iShouldSeeTheSubtitleForOpenedANewCreditAccountWithMe(String days) throws Throwable {
        WebElement newAcc = findElement(By.xpath("//*[@id='options" + newCreditAccPosition + "_select']/option[3]"));
        elementShouldBePresent(newAcc);
        Assert.assertTrue(newAcc.getAttribute("selected").equals("true"));
        String newAccText = newAcc.getText();
        System.out.println(newAccText);
        assertTrue(newAccText.contains(days));
    }

    @When("^I expand the subtitle Last 90 Days for Opened a New Credit Account with Me$")
    public void iExpandTheSubtitleLast90DaysForOpenedANewCreditAccountWithMe() throws Throwable {
        WebElement newAcc = findElement(By.xpath("//*[@id='options" + newCreditAccPosition + "_select']"));
        elementShouldBePresent(newAcc);
        Clicks.click(newAcc);
    }

    @Then("^I should see the following values in the New Accounts dropdown on the Create List dashboard$")
    public void iShouldSeeTheFollowingValuesInTheNewAccountsDropdownOnTheCreateListDashboard(List<String> options) throws Throwable {
        Wait.untilElementPresent("todos.new_credit_acc_dropdown_create_list");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Last 30 Days":
                    WebElement option1 = findElement(By.xpath("//*[@id='options" + newCreditAccPosition + "_select']/option[1]"));
                    elementShouldBePresent(option1);
                    String option1Text = option1.getText();
                    System.out.println(option1Text);
                    Assert.assertTrue(option1Text.contains(foundOption));
                    break;
                case "Last 60 Days":
                    WebElement option2 = findElement(By.xpath("//*[@id='options" + newCreditAccPosition + "_select']/option[2]"));
                    elementShouldBePresent(option2);
                    String option2Text = option2.getText();
                    System.out.println(option2Text);
                    Assert.assertTrue(option2Text.contains(foundOption));
                    break;
                case "Last 90 Days":
                    WebElement option3 = findElement(By.xpath("//*[@id='options" + newCreditAccPosition + "_select']/option[3]"));
                    elementShouldBePresent(option3);
                    String option3Text = option3.getText();
                    System.out.println(option3Text);
                    Assert.assertTrue(option3Text.contains(foundOption));
                    break;
            }
        }
    }

    @When("^I select the title Opened a New Credit Account with Me on the Create List dashboard$")
    public void iSelectTheTitleOpenedANewCreditAccountWithMeOnTheCreateListDashboard() throws Throwable {
        WebElement newAcc = findElement(By.id("description" + newCreditAccPosition));
        elementShouldBePresent(newAcc);
        Clicks.click(newAcc);
    }

    @And("^the customer count will display on the Opened New Acc Create List dashboard$")
    public void theCustomerCountWillDisplayOnTheOpenedNewAccCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement custCount = findElement(By.id("count" + newCreditAccPosition));
        elementShouldBePresent(custCount);
        String countNumber = custCount.getText().trim();
        Matcher matcher = Pattern.compile("\\(([0-9]+)\\)").matcher(countNumber);
        if (matcher.find()) {
            assertTrue("Customer count is displayed", true);
        } else {
            Assert.fail("No customer count is displayed");
        }
    }

    @And("^the list should be sorted by AGE OF ACCOUNT$")
    public void theListShouldBeSortedByAGEOFACCOUNT() throws Throwable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<WebElement> elements = findElements("todos.account_open_date_create_list_column");
        compareSortedDates(simpleDateFormat, elements);
    }

    @And("^I should see the title My Top 25 Clients on the Create List dashboard$")
    public void iShouldSeeTheTitleMyTop25ClientsOnTheCreateListDashboard() throws Throwable {
        WebElement topClients = findElement(By.id("description" + topClientsPosition));
        elementShouldBePresent(topClients);
        String topClientsTitle = topClients.getText();
        System.out.println(topClientsTitle);
        assertTrue(topClientsTitle.contains("My Top 25 Customers"));

    }

    @And("^I should see the subtitle \"([^\"]*)\" for My Top 25 Clients$")
    public void iShouldSeeTheSubtitleForMyTop25Clients(String subtitleOption) throws Throwable {
        WebElement topClientsSubtitle = findElement(By.xpath("//*[@id='options" + topClientsPosition + "_select']/option[1]"));
        elementShouldBePresent(topClientsSubtitle);
        Assert.assertTrue(topClientsSubtitle.getAttribute("selected").equals("true"));
        String topClientsSubtitleText = topClientsSubtitle.getText();
        System.out.println(topClientsSubtitleText);
        assertTrue(topClientsSubtitleText.contains(subtitleOption));

    }

    @When("^I expand the subtitle Last 12 Months by Spend me for My Top 25 Clients$")
    public void iExpandTheSubtitleLastMonthsBySpendMeForMyTopClients() throws Throwable {
        WebElement topClientsSubtitle = findElement(By.xpath("//*[@id='options" + topClientsPosition + "_select']/option[1]"));
        elementShouldBePresent(topClientsSubtitle);
        Clicks.click(topClientsSubtitle);

    }

    @When("^I select the title My Top 25 Clients on the Create List dashboard$")
    public void iSelectTheTitleMyTop25ClientsOnTheCreateListDashboard() throws Throwable {
        WebElement topClients = findElement(By.id("description" + topClientsPosition));
        elementShouldBePresent(topClients);
        Clicks.click(topClients);
    }


    @And("^the customer count will display on the Top Clients Create List dashboard$")
    public void theCustomerCountWillDisplayOnTheTopClientsCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement topClientsCount = findElement(By.id("count" + topClientsPosition));
        elementShouldBePresent(topClientsCount);
        String countNumber = topClientsCount.getText().trim();
        Matcher matcher = Pattern.compile("\\(([0-9]+)\\)").matcher(countNumber);
        if (matcher.find()) {
            assertTrue("Customer count is displayed", true);
        } else {
            Assert.fail("No customer count is displayed");
        }
    }

    @And("^the list should be sorted by RANK$")
    public void theListShouldBeSortedByRANK() throws Throwable {
        elementShouldBePresent("todos.rank_column_top_clients");
        List<WebElement> elements = findElements("todos.rank_column_top_clients");
        List<Integer> storeNumbers = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText();
            Matcher matcher = STARTS_WITH_NUMBER.matcher(text.trim());
            if (matcher.find()) {
                String group = matcher.group(1);
                storeNumbers.add(Integer.parseInt(group));
            }
        }
        ArrayList<Integer> sortedStoreNumbers = new ArrayList<>(storeNumbers);
        Collections.sort(sortedStoreNumbers);
        assertEquals(sortedStoreNumbers, storeNumbers);
    }

    @And("^I should see the title Cust Who Have Not Shopped on the Create List dashboard$")
    public void iShouldSeeTheTitleCustWhoHaveNotShoppedOnTheCreateListDashboard() throws Throwable {
        WebElement custNot = findElement(By.id("description" + customerPosition));
        elementShouldBePresent(custNot);
        String custNotTitle = custNot.getText();
        System.out.println(custNotTitle);
        assertTrue(custNotTitle.contains("Did Not Shop"));
    }

    @And("^I should see the subtitle \"([^\"]*)\" for Cust Who Have Not Shopped$")
    public void iShouldSeeTheSubtitleForCustWhoHaveNotShopped(String days) throws Throwable {
        WebElement custNotSub = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']/option[3]"));
        elementShouldBePresent(custNotSub);
        Assert.assertTrue(custNotSub.getAttribute("selected").equals("true"));
        String custNotSubText = custNotSub.getText();
        System.out.println(custNotSubText);
        assertTrue(custNotSubText.contains(days));
    }

    @When("^I expand the subtitle Last 90 Days for Cust Who Have Not Shopped$")
    public void iExpandTheSubtitleLast90DaysForCustWhoHaveNotShopped() throws Throwable {
        WebElement custNotSub = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']"));
        elementShouldBePresent(custNotSub);
        Clicks.click(custNotSub);
    }

    @Then("^I should see the following values in the Cust Who Have Not Shopped dropdown on the Create List dashboard$")
    public void iShouldSeeTheFollowingValuesInTheCustWhoHaveNotShoppedDropdownOnTheCreateListDashboard(List<String> options) throws Throwable {
        for (String foundOption : options) {
            switch (foundOption) {
                case "Last 30 Days":
                    WebElement option1 = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']/option[1]"));
                    elementShouldBePresent(option1);
                    String option1Text = option1.getText();
                    System.out.println(option1Text);
                    Assert.assertTrue(option1Text.contains(foundOption));
                    break;
                case "Last 60 Days":
                    WebElement option2 = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']/option[2]"));
                    elementShouldBePresent(option2);
                    String option2Text = option2.getText();
                    System.out.println(option2Text);
                    Assert.assertTrue(option2Text.contains(foundOption));
                    break;
                case "Last 90 Days":
                    WebElement option3 = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']/option[3]"));
                    elementShouldBePresent(option3);
                    String option3Text = option3.getText();
                    System.out.println(option3Text);
                    Assert.assertTrue(option3Text.contains(foundOption));
                    break;
                case "Last 6 Months":
                    WebElement option4 = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']/option[4]"));
                    elementShouldBePresent(option4);
                    String option4Text = option4.getText();
                    System.out.println(option4Text);
                    Assert.assertTrue(option4Text.contains(foundOption));
                    break;
                case "Last 12 Months":
                    WebElement option5 = findElement(By.xpath("//*[@id='options" + customerPosition + "_select']/option[5]"));
                    elementShouldBePresent(option5);
                    String option5Text = option5.getText();
                    System.out.println(option5Text);
                    Assert.assertTrue(option5Text.contains(foundOption));
            }
        }
    }

    @When("^I select the title Cust Who Have Not Shopped on the Create List dashboard$")
    public void iSelectTheTitleCustWhoHaveNotShoppedOnTheCreateListDashboard() throws Throwable {
        WebElement custNot = findElement(By.id("description" + customerPosition));
        elementShouldBePresent(custNot);
        Clicks.click(custNot);
    }

    @And("^the customer count will display on the Cust Who Have Not Shopped Create List dashboard$")
    public void theCustomerCountWillDisplayOnTheCustWhoHaveNotShoppedCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement custCount = findElement(By.id("count" + customerPosition));
        elementShouldBePresent(custCount);
        String countNumber = custCount.getText().trim();
        Matcher matcher = Pattern.compile("\\(([0-9]+)\\)").matcher(countNumber);
        if (matcher.find()) {
            assertTrue("Customer count is displayed", true);
        } else {
            Assert.fail("No customer count is displayed");
        }
    }

    @And("^the list should be sorted by LAST VISIT DATE$")
    public void theListShouldBeSortedByLASTVISITDATE() throws Throwable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<WebElement> elements = findElements("todos.cust_not_shopped_dynamic_column_create_list");
        compareSortedDates(simpleDateFormat, elements, true);
    }

    @And("^the CREATE TO DO button should be displayed in the Create To Do view$")
    public void theCREATETODOButtonShouldBeDisplayedInTheCreateToDoView() throws Throwable {
        WebElement createButton = findElement("homepage.create_button_create_to_dos_page_new");
        elementShouldBePresent(createButton);
        Assert.assertEquals("Create To Do", createButton.getText());
    }

    @And("^the Required Fields note should be displayed in the Create To Do view$")
    public void theRequiredFieldsNoteShouldBeDisplayedInTheCreateToDoView() throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_req_fields_note");
        elementShouldBePresent("homepage.create_to_dos_req_fields_note");
    }

    @And("^the CREATE TO DO button should be disabled in the Create To Do view$")
    public void theCREATETODOButtonShouldBeDisabledInTheCreateToDoView() throws Throwable {
        WebElement createToDoButton = findElement("homepage.create_button_create_to_dos_page_new");
        Assert.assertFalse(createToDoButton.isEnabled());
    }

    @And("^the CREATE TO DO button should be enabled in the Create To Do view$")
    public void theCREATETODOButtonShouldBeEnabledInTheCreateToDoView() throws Throwable {
        WebElement createToDoButton = findElement("homepage.create_button_create_to_dos_page_new");
        Assert.assertTrue(createToDoButton.isEnabled());
    }

    @Then("^the CREATE TO DOS button should be displayed in the Create To Do view$")
    public void theCREATETODOSButtonShouldBeDisplayedInTheCreateToDoView() throws Throwable {
        WebElement createButton = findElement("homepage.create_button_create_to_dos_page_new");
        elementShouldBePresent(createButton);
        Assert.assertEquals("Create To Dos", createButton.getText());
    }

    @Then("^I should see the title \"([^\"]*)\" on CREATE TO DOS page$")
    public void iShouldSeeTheTitleOnCREATETODOSPage(String title) throws Throwable {
        WebElement titleField = findElement("homepage.create_to_dos_title_field");
        Assert.assertEquals(title, titleField.getAttribute("value"));
    }

    @When("^I enter (\\d+) characters in the Description input field on CREATE TO DOS page$")
    public void iEnterCharactersInTheDescriptionInputFieldOnCREATETODOSPage(int value) throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_description_field");
        TextBoxes.typeTextbox("homepage.create_to_dos_description_field", RandomStringUtils.randomAlphabetic(value));
    }

    @Then("^(\\d+) characters should be displayed in the Description input field on CREATE TO DOS page$")
    public void charactersShouldBeDisplayedInTheDescriptionInputFieldOnCREATETODOSPage(int count) throws Throwable {
        Wait.untilElementPresent("homepage.create_to_dos_description_field");
        Integer descrCount = findElement("homepage.create_to_dos_description_field").getAttribute("value").length();
        if (count != descrCount) {
            Assert.fail("Incorrect character count, should be 250");
        }
    }

    @And("^I click the CREATE TO DOS button in the Create To Do view$")
    public void iClickTheCREATETODOSButtonInTheCreateToDoView() throws Throwable {
        Wait.untilElementPresent("homepage.create_button_create_to_dos_page_new");
        Clicks.click("homepage.create_button_create_to_dos_page_new");
    }

    @And("^the To Do \"([^\"]*)\" is saved on the Associates MY TO DOS tab for client \"([^\"]*)\"$")
    public void theToDoIsSavedOnTheAssociatesMYTODOSTabForClient(String todo, String client) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement clientName = findElement(By.linkText(client));
        WebElement todoTitle = findElement(By.linkText(todo));
        Elements.elementShouldBePresent(clientName);
        Elements.elementShouldBePresent(todoTitle);
        System.out.println("CLIENT NAME= " + clientName.getText() + " TODO TILE= " + todoTitle.getText());
    }

    @And("^I should see the \"([^\"]*)\" filter criteria Header$")
    public void iShouldSeeTheFilterCriteriaHeader(String sectionName) throws Throwable {
        Wait.untilElementPresent("todos.customer_profile_section_todos");
        elementShouldBePresent("todos.customer_profile_section_todos");
        String dateSection = findElement("todos.date_section_cust_profile_todos").getText();
        System.out.println(dateSection);
        Assert.assertTrue(dateSection.contains(sectionName));

    }

    @And("^I should see the following options below Date filter$")
    public void iShouldSeeTheFollowingOptionsBelowDateFilter(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.date_section_cust_profile_todos");
        elementShouldBePresent("todos.date_section_cust_profile_todos");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Last 14 Days":
                    elementShouldBePresent("todos.14_days_date_section_cust_profile_todos");
                    String option14 = findElement("todos.14_days_date_section_cust_profile_todos").getText();
                    System.out.println(option14);
                    Assert.assertTrue(option14.contains("Last 14 Days"));
                    break;

                case "Last 30 Days":
                    elementShouldBePresent("todos.30_days_date_section_cust_profile_todos");
                    String option30 = findElement("todos.30_days_date_section_cust_profile_todos").getText();
                    System.out.println(option30);
                    Assert.assertTrue(option30.contains("Last 30 Days"));
                    break;

                case "Last 60 Days":
                    elementShouldBePresent("todos.60_days_date_section_cust_profile_todos");
                    String option60 = findElement("todos.60_days_date_section_cust_profile_todos").getText();
                    System.out.println(option60);
                    Assert.assertTrue(option60.contains("Last 60 Days"));
                    break;

                case "Last 90 Days":
                    elementShouldBePresent("todos.90_days_date_section_cust_profile_todos");
                    String option90 = findElement("todos.90_days_date_section_cust_profile_todos").getText();
                    System.out.println(option90);
                    Assert.assertTrue(option90.contains("Last 90 Days"));
                    break;
            }
        }
    }

    @When("^I click the chevron of Customer Profile filter criteria$")
    public void iClickTheChevronOfCustomerProfileFilterCriteria() throws Throwable {
        Wait.untilElementPresent("todos.third_section_chevron");
        elementShouldBePresent("todos.third_section_chevron");
        Clicks.click("todos.third_section_chevron");
    }

    @Then("^I should see the Customer Profile filter criteria collapsed$")
    public void iShouldSeeTheCustomerProfileFilterCriteriaCollapsed(List<String> sectionColapsed) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionColapsed) {
            switch (option) {
                case "Relationship Start Date":
                    WebElement section = findElement("todos.date_section_cust_profile_todos");
                    Assert.assertFalse("Section not collapsed", section.isDisplayed());
                    break;

                case "Affiliation Date":
                    WebElement section0 = findElement("todos.date_section_cust_profile_todos");
                    Assert.assertFalse("Section not collapsed", section0.isDisplayed());
                    break;

                case "Birthday":
                    WebElement section2 = findElement("todos.birthdays_section_cust_profile_todos");
                    Assert.assertFalse("Section not collapsed", section2.isDisplayed());
                    break;

                case "Customer Location":
                    WebElement section3 = findElement("todos.customer_location_section_cust_profile_todos");
                    Assert.assertFalse("Section not collapsed", section3.isDisplayed());
                    break;
            }
        }
    }

    @Then("^I should see the Customer Profile filter criteria expanded$")
    public void iShouldSeeTheCustomerProfileFilterCriteriaExpanded(List<String> sectionExpanded) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionExpanded) {
            switch (option) {
                case "Relationship Start Date":
                    WebElement section1 = findElement("todos.date_section_cust_profile_todos");
                    Assert.assertTrue(section1.isDisplayed());
                    break;

                case "Affiliation Date":
                    WebElement section0 = findElement("todos.date_section_cust_profile_todos");
                    Assert.assertTrue(section0.isDisplayed());
                    break;

                case "Birthday":
                    WebElement section2 = findElement("todos.birthdays_section_cust_profile_todos");
                    Assert.assertTrue(section2.isDisplayed());
                    break;

                case "Customer Location":
                    WebElement section3 = findElement("todos.customer_location_section_cust_profile_todos");
                    Assert.assertTrue(section3.isDisplayed());
                    break;
            }
        }
    }

    @When("^I select a filter criteria from Customer Profile Date$")
    public void iSelectAFilterCriteriaFromCustomerProfileDate(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Last 14 Days":
                    Wait.untilElementPresent("todos.14_days_date_section_cust_profile_todos");
                    elementShouldBePresent("todos.14_days_date_section_cust_profile_todos");
                    Clicks.click("todos.14_days_date_section_cust_profile_todos");
                    break;

                case "Last 30 Days":
                    Wait.untilElementPresent("todos.30_days_date_section_cust_profile_todos");
                    elementShouldBePresent("todos.30_days_date_section_cust_profile_todos");
                    Clicks.click("todos.30_days_date_section_cust_profile_todos");
                    break;

                case "Last 60 Days":
                    Wait.untilElementPresent("todos.60_days_date_section_cust_profile_todos");
                    elementShouldBePresent("todos.60_days_date_section_cust_profile_todos");
                    Clicks.click("todos.60_days_date_section_cust_profile_todos");
                    break;

                case "Last 90 Days":
                    Wait.untilElementPresent("todos.90_days_date_section_cust_profile_todos");
                    elementShouldBePresent("todos.90_days_date_section_cust_profile_todos");
                    Clicks.click("todos.90_days_date_section_cust_profile_todos");
                    break;
            }
        }

    }

    @Then("^I should see the filter criteria selected from Customer Profile Date$")
    public void iShouldSeeTheFilterCriteriaSelectedFromCustomerProfileDate(List<String> filterX) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "Last 14 Days":
                    Wait.untilElementPresent("todos.close_third_section_first_subsection_first_filter_option");
                    WebElement closeFilterX14 = findElement("todos.close_third_section_first_subsection_first_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX14.isDisplayed());
                    break;

                case "Last 30 Days":
                    Wait.untilElementPresent("todos.close_third_section_first_subsection_second_filter_option");
                    WebElement closeFilterX30 = findElement("todos.close_third_section_first_subsection_second_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX30.isDisplayed());
                    break;

                case "Last 60 Days":
                    Wait.untilElementPresent("todos.close_third_section_first_subsection_third_filter_option");
                    WebElement closeFilterX60 = findElement("todos.close_third_section_first_subsection_third_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX60.isDisplayed());
                    break;

                case "Last 90 Days":
                    Wait.untilElementPresent("todos.close_third_section_first_subsection_fourth_filter_option");
                    WebElement closeFilterX90 = findElement("todos.close_third_section_first_subsection_fourth_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX90.isDisplayed());
                    break;

            }
        }
    }

    @When("^I remove the filter criteria from Customer Profile Date$")
    public void iRemoveTheFilterCriteriaFromCustomerProfileDate(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "Last 14 Days":
                    Clicks.click("todos.close_third_section_first_subsection_first_filter_option");
                    break;

                case "Last 30 Days":
                    Clicks.click("todos.close_third_section_first_subsection_second_filter_option");
                    break;

                case "Last 60 Days":
                    Clicks.click("todos.close_third_section_first_subsection_third_filter_option");
                    break;

                case "Last 90 Days":
                    Clicks.click("todos.close_third_section_first_subsection_fourth_filter_option");
                    break;

            }
        }
    }

    @Then("^I should no longer see the filter criteria selected from Customer Profile Date$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedFromCustomerProfileDate(List<String> noFilter) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "Last 14 Days":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter14 = findElement("todos.close_third_section_first_subsection_first_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter14.isDisplayed());
                    break;

                case "Last 30 Days":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter30 = findElement("todos.close_third_section_first_subsection_third_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter30.isDisplayed());
                    break;

                case "Last 60 Days":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter60 = findElement("todos.close_third_section_first_subsection_first_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter60.isDisplayed());
                    break;

                case "Last 90 Days":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter90 = findElement("todos.close_third_section_first_subsection_fourth_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter90.isDisplayed());
                    break;
            }
        }
    }

    @And("^I should see the following options below Birthday filter$")
    public void iShouldSeeTheFollowingOptionsBelowBirthdayFilter(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.birthdays_section_cust_profile_todos");
        elementShouldBePresent("todos.birthdays_section_cust_profile_todos");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Next 15 Days":
                    elementShouldBePresent("todos.next_15_birthdays_section_cust_profile_todos");
                    String option14 = findElement("todos.next_15_birthdays_section_cust_profile_todos").getText();
                    System.out.println(option14);
                    Assert.assertTrue(option14.contains("Next 15 Days"));
                    break;

                case "Next 30 Days":
                    elementShouldBePresent("todos.next_30_birthdays_section_cust_profile_todos");
                    String option30 = findElement("todos.next_30_birthdays_section_cust_profile_todos").getText();
                    System.out.println(option30);
                    Assert.assertTrue(option30.contains("Next 30 Days"));
                    break;

            }
        }
    }

    @When("^I select a filter criteria from Customer Profile Birthday$")
    public void iSelectAFilterCriteriaFromCustomerProfileBirthday(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Next 15 Days":
                    Wait.untilElementPresent("todos.next_15_birthdays_section_cust_profile_todos");
                    elementShouldBePresent("todos.next_15_birthdays_section_cust_profile_todos");
                    Clicks.click("todos.next_15_birthdays_section_cust_profile_todos");
                    break;

                case "Next 30 Days":
                    Wait.untilElementPresent("todos.next_30_birthdays_section_cust_profile_todos");
                    elementShouldBePresent("todos.next_30_birthdays_section_cust_profile_todos");
                    Clicks.click("todos.next_30_birthdays_section_cust_profile_todos");
                    break;
            }
        }
    }

    @Then("^I should see the filter criteria selected from Customer Profile Birthday$")
    public void iShouldSeeTheFilterCriteriaSelectedFromCustomerProfileBirthday(List<String> filterX) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "Next 15 Days":
                    Wait.untilElementPresent("todos.close_third_section_second_subsection_first_filter_option");
                    WebElement closeFilterX14 = findElement("todos.close_third_section_second_subsection_first_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX14.isDisplayed());
                    break;

                case "Next 30 Days":
                    Wait.untilElementPresent("todos.close_third_section_second_subsection_second_filter_option");
                    WebElement closeFilterX30 = findElement("todos.close_third_section_second_subsection_second_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX30.isDisplayed());
                    break;
            }
        }
    }

    @When("^I remove the filter criteria from Customer Profile Birthday$")
    public void iRemoveTheFilterCriteriaFromCustomerProfileBirthday(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "Next 15 Days":
                    Clicks.click("todos.close_third_section_second_subsection_first_filter_option");
                    break;

                case "Next 30 Days":
                    Clicks.click("todos.close_third_section_second_subsection_second_filter_option");
                    break;
            }
        }
    }

    @Then("^I should no longer see the filter criteria selected from Customer Profile Birthday$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedFromCustomerProfileBirthday(List<String> noFilter) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "Next 15 Days":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter15 = findElement("todos.close_third_section_second_subsection_first_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter15.isDisplayed());
                    break;

                case "Next 30 Days":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter30 = findElement("todos.close_third_section_second_subsection_second_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter30.isDisplayed());
                    break;
            }
        }
    }

    @And("^I should see the \"([^\"]*)\" filter criteria section Header$")
    public void iShouldSeeTheFilterCriteriaSectionHeader(String sectionName) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.customer_profile_section_todos");
        elementShouldBePresent("todos.customer_profile_section_todos");
        String birthdaySection = findElement("todos.birthdays_section_cust_profile_todos").getText();
        System.out.println(birthdaySection);
        Assert.assertTrue(birthdaySection.contains(sectionName));
    }

    @And("^I should see the \"([^\"]*)\" filter criteria Header Loyalty$")
    public void iShouldSeeTheFilterCriteriaHeaderLoyalty(String sectionName) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_new_list_view");
        elementShouldBePresent("todos.first_section_new_list_view");
        String firstSection = findElement("todos.first_section_first_subsection").getText();
        System.out.println(firstSection);
        Assert.assertTrue(firstSection.contains(sectionName));
    }

    @And("^I should see the following options below Dollars to Platinum filter$")
    public void iShouldSeeTheFollowingOptionsBelowDollarsToPlatinumFilter(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_second_subsection");
        elementShouldBePresent("todos.first_section_second_subsection");
        for (String foundOption : options) {
            switch (foundOption) {
                case "$200 or Less":
                    elementShouldBePresent("todos.first_section_second_subsection_first_filter_option");
                    String option14 = findElement("todos.first_section_second_subsection_first_filter_option").getText();
                    System.out.println(option14);
                    Assert.assertTrue(option14.contains("$200 or Less"));
                    break;

                case "$500 or Less":
                    elementShouldBePresent("todos.first_section_second_subsection_second_filter_option");
                    String option30 = findElement("todos.first_section_second_subsection_second_filter_option").getText();
                    System.out.println(option30);
                    Assert.assertTrue(option30.contains("$500 or Less"));
                    break;

                case "$500 or Less BLM":
                    elementShouldBePresent("todos.first_section_second_subsection_first_filter_option");
                    String option500BLM = findElement("todos.first_section_second_subsection_first_filter_option").getText();
                    System.out.println(option500BLM);
                    Assert.assertTrue(option500BLM.contains("$500 or Less"));
                    break;

                case "$1000 or Less":
                    elementShouldBePresent("todos.first_section_second_subsection_second_filter_option");
                    String option1000BLM = findElement("todos.first_section_second_subsection_second_filter_option").getText();
                    System.out.println(option1000BLM);
                    Assert.assertTrue(option1000BLM.contains("$1,000 or Less"));
                    break;


                default:
                    Assert.fail("RANDOM FAIL");
                    break;

            }
        }
    }


    @Then("^I should see the Dollars to filter criteria collapsed$")
    public void iShouldSeeTheDollarsToFilterCriteriaCollapsed(List<String> sectionColapsed) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionColapsed) {
            switch (option) {
                case "$200 or Less":
                    WebElement section = findElement("todos.first_section_second_subsection_first_filter_option");
                    Assert.assertFalse("Section not collapsed", section.isDisplayed());
                    break;

                case "$500 or Less":
                    WebElement section0 = findElement("todos.first_section_second_subsection_second_filter_option");
                    Assert.assertFalse("Section not collapsed", section0.isDisplayed());
                    break;

                case "$500 or Less BLM":
                    WebElement section500BLM = findElement("todos.first_section_second_subsection_first_filter_option");
                    Assert.assertFalse("Section not collapsed", section500BLM.isDisplayed());
                    break;

                case "$1000 or Less":
                    WebElement section1000BLM = findElement("todos.first_section_second_subsection_second_filter_option");
                    Assert.assertFalse("Section not collapsed", section1000BLM.isDisplayed());
                    break;


                case "Points to Next Reward":
                    WebElement sectionNextR = findElement("todos.first_section_third_subsection");
                    Assert.assertFalse("Section not collapsed", sectionNextR.isDisplayed());
                    break;

                case "Star Rewards upgrade dates":
                    WebElement sectionStar = findElement("todos.first_section_fourth_subsection");
                    Assert.assertFalse("Section not collapsed", sectionStar.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;

            }
        }
    }

    @When("^I click the chevron of Loyalty section filter criteria$")
    public void iClickTheChevronOfLoyaltySectionFilterCriteria() throws Throwable {
        Wait.untilElementPresent("todos.first_section_chevron");
        elementShouldBePresent("todos.first_section_chevron");
        Clicks.click("todos.first_section_chevron");
    }

    @Then("^I should see the Dollars to filter criteria expanded$")
    public void iShouldSeeTheDollarsToFilterCriteriaExpanded(List<String> sectionExpanded) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionExpanded) {
            switch (option) {
                case "$200 or Less":
                    WebElement section1 = findElement("todos.first_section_second_subsection_first_filter_option");
                    Assert.assertTrue(section1.isDisplayed());
                    break;

                case "$500 or Less":
                    WebElement section0 = findElement("todos.first_section_second_subsection_second_filter_option");
                    Assert.assertTrue(section0.isDisplayed());
                    break;

                case "$500 or Less BLM":
                    WebElement section500BLM = findElement("todos.first_section_second_subsection_first_filter_option");
                    Assert.assertTrue(section500BLM.isDisplayed());
                    break;

                case "$1000 or Less":
                    WebElement section1000BLM = findElement("todos.first_section_second_subsection_second_filter_option");
                    Assert.assertTrue(section1000BLM.isDisplayed());
                    break;

                case "Points to Next Reward":
                    WebElement sectionNextR = findElement("todos.first_section_third_subsection");
                    Assert.assertTrue(sectionNextR.isDisplayed());
                    break;

                case "Star Rewards upgrade dates":
                    WebElement sectionStar = findElement("todos.first_section_fourth_subsection");
                    Assert.assertTrue(sectionStar.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @When("^I select a filter criteria from Loyalty Dollars to Top of List$")
    public void iSelectAFilterCriteriaFromLoyaltyDollarsToTopOfList(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "$200 or Less":
                    Wait.untilElementPresent("todos.first_section_second_subsection_first_filter_option");
                    elementShouldBePresent("todos.first_section_second_subsection_first_filter_option");
                    Clicks.click("todos.first_section_second_subsection_first_filter_option");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "$500 or Less":
                    Wait.untilElementPresent("todos.first_section_second_subsection_second_filter_option");
                    elementShouldBePresent("todos.first_section_second_subsection_second_filter_option");
                    Clicks.click("todos.first_section_second_subsection_second_filter_option");
                    break;

                case "$500 or Less BLM":
                    Wait.untilElementPresent("todos.first_section_second_subsection_first_filter_option");
                    elementShouldBePresent("todos.first_section_second_subsection_first_filter_option");
                    Clicks.click("todos.first_section_second_subsection_first_filter_option");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "$1000 or Less":
                    Wait.untilElementPresent("todos.first_section_second_subsection_second_filter_option");
                    elementShouldBePresent("todos.first_section_second_subsection_second_filter_option");
                    Clicks.click("todos.first_section_second_subsection_second_filter_option");
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @Then("^I should see the filter criteria selected from Loyalty Dollars to Top of List$")
    public void iShouldSeeTheFilterCriteriaSelectedFromLoyaltyDollarsToTopOfList(List<String> filterX) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "$200 or Less":
                    Wait.untilElementPresent("todos.close_first_section_second_subsection_first_filter_option");
                    WebElement closeFilterX14 = findElement("todos.close_first_section_second_subsection_first_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX14.isDisplayed());
                    break;

                case "$500 or Less":
                    Wait.untilElementPresent("todos.close_first_section_second_subsection_second_filter_option");
                    WebElement closeFilterX30 = findElement("todos.close_first_section_second_subsection_second_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX30.isDisplayed());
                    break;

                case "$500 or Less BLM":
                    Wait.untilElementPresent("todos.close_first_section_second_subsection_first_filter_option");
                    WebElement closeFilterX500BLM = findElement("todos.close_first_section_second_subsection_first_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX500BLM.isDisplayed());
                    break;

                case "$1000 or Less":
                    Wait.untilElementPresent("todos.close_first_section_second_subsection_second_filter_option");
                    WebElement closeFilterX1000BLM = findElement("todos.close_first_section_second_subsection_second_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterX1000BLM.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @When("^I remove the filter criteria from Loyalty Dollars to Top of List$")
    public void iRemoveTheFilterCriteriaFromLoyaltyDollarsToTopOfList(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "$200 or Less":
                    Clicks.click("todos.close_first_section_second_subsection_first_filter_option");
                    Thread.sleep(2000);
                    break;

                case "$500 or Less":
                    Clicks.click("todos.close_first_section_second_subsection_second_filter_option");
                    break;

                case "$500 or Less BLM":
                    Clicks.click("todos.close_first_section_second_subsection_first_filter_option");
                    Thread.sleep(2000);
                    break;

                case "$1000 or Less":
                    Clicks.click("todos.close_first_section_second_subsection_second_filter_option");
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @Then("^I should no longer see the filter criteria selected from Loyalty Dollars to Top of List$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedFromLoyaltyDollarsToTopOfList(List<String> noFilter) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "$200 or Less":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter200 = findElement("todos.close_first_section_second_subsection_first_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter200.isDisplayed());
                    break;

                case "$500 or Less":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter500 = findElement("todos.close_first_section_second_subsection_second_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter500.isDisplayed());
                    break;

                case "$500 or Less BLM":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter500BLM = findElement("todos.close_first_section_second_subsection_first_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter500BLM.isDisplayed());
                    break;

                case "$1000 or Less BLM":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter1000 = findElement("todos.close_first_section_second_subsection_second_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter1000.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @And("^I select the Wallet tab$")
    public void iSelectTheWalletTab() throws Throwable {
        Wait.untilElementPresent("my_clients.clients_wallet_tab");
        Elements.elementShouldBePresent("my_clients.clients_wallet_tab");
    }

    @And("^the Show Other Cards link is displayed in the header of the Wallet screen$")
    public void theShowOtherCardsLinkIsDisplayedInTheHeaderOfTheWalletScreen() throws Throwable {
        Wait.untilElementPresent("my_clients.show_other_cards_link_wallet");
        Elements.elementShouldBePresent("my_clients.show_other_cards_link_wallet");
        String showText = findElement("my_clients.show_other_cards_link_wallet").getText();
        Assert.assertTrue(showText.contains("Show Other Cards"));
    }

    @And("^Cash option is displayed on Wallet Screen MCY$")
    public void cashOptionIsDisplayedOnWalletScreenMCY() throws Throwable {
        Wait.untilElementPresent("my_clients.cash_option_wallet");
        Elements.elementShouldBePresent("my_clients.cash_option_wallet");
    }

    @And("^only Credit Cards with Shopping Passes will display on Wallet Screen$")
    public void onlyCreditCardsWithShoppingPassesWillDisplayOnWalletScreen() throws Throwable {
        Wait.untilElementPresent("my_clients.shopping_pass_icon");
        Elements.elementShouldBePresent("my_clients.shopping_pass_icon");
    }

    @When("^I click on the Show Other Cards link$")
    public void iClickOnTheShowOtherCardsLink() throws Throwable {
        Wait.untilElementPresent("my_clients.show_other_cards_link_wallet");
        Clicks.click("my_clients.show_other_cards_link_wallet");
    }

    @Then("^the other Credit Cards are displayed on Wallet screen$")
    public void theOtherCreditCardsAreDisplayedOnWalletScreen() throws Throwable {
        Wait.untilElementPresent("my_clients.other_cards_wallet");
        Elements.elementShouldBePresent("my_clients.other_cards_wallet");
    }

    @And("^the Show Other Cards link changes to Hide Other Cards$")
    public void theShowOtherCardsLinkChangesToHideOtherCards() throws Throwable {
        Wait.untilElementPresent("my_clients.hide_other_cards_link_wallet");
        Elements.elementShouldBePresent("my_clients.hide_other_cards_link_wallet");
        String hideText = findElement("my_clients.hide_other_cards_link_wallet").getText();
        Assert.assertTrue(hideText.contains("Hide Other Cards"));
    }

    @When("^I click on the Hide Other Cards link$")
    public void iClickOnTheHideOtherCardsLink() throws Throwable {
        Wait.untilElementPresent("my_clients.hide_other_cards_link_wallet");
        Clicks.click("my_clients.hide_other_cards_link_wallet");
    }

    @And("^no Credit Cards are displayed on Wallet Screen$")
    public void noCreditCardsAreDisplayedOnWalletScreen() throws Throwable {
        Wait.untilElementNotPresent("my_clients.shopping_pass_icon");
        Elements.elementShouldBePresent("my_clients.show_other_cards_link_wallet");
        String showText = findElement("my_clients.show_other_cards_link_wallet").getText();
        Assert.assertTrue(showText.contains("Show Other Cards"));
    }

    @And("^I should see the title My Platinum Customers on the Create List dashboard$")
    public void iShouldSeeTheTitleMyPlatinumCustomersOnTheCreateListDashboard() throws Throwable {
        WebElement plat = findElement(By.id("description" + topListPlatinumPosition));
        elementShouldBePresent(plat);
        String platTitle = plat.getText();
        System.out.println(platTitle);
        assertTrue(platTitle.contains("My Platinum Customers"));
    }

    @When("^I select the title My Platinum Customers on the Create List dashboard$")
    public void iSelectTheTitleMyPlatinumCustomersOnTheCreateListDashboard() throws Throwable {
        WebElement plat = findElement(By.id("description" + topListPlatinumPosition));
        elementShouldBePresent(plat);
        Clicks.click(plat);
    }

    @And("^the list should be sorted by LAST VISIT$")
    public void theListShouldBeSortedByLASTVISIT() throws Throwable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        List<WebElement> elements = findElements("todos.birthday_column_create_list");
        compareSortedDates(simpleDateFormat, elements);
    }

    @And("^I should see the Customer Location filter criteria Header$")
    public void iShouldSeeTheCustomerLocationFilterCriteriaHeader() throws Throwable {
        Wait.untilElementPresent("todos.customer_profile_section_todos");
        elementShouldBePresent("todos.customer_profile_section_todos");
        String locationSection = findElement("todos.customer_location_section_cust_profile_todos").getText();
        System.out.println(locationSection);
        Assert.assertTrue(locationSection.contains("Customer Location"));
    }

    @And("^I should see the following options below Customer Location filter$")
    public void iShouldSeeTheFollowingOptionsBelowCustomerLocationFilter(List<String> locationOptions) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.customer_location_section_cust_profile_todos");
        elementShouldBePresent("todos.customer_location_section_cust_profile_todos");
        for (String foundOption : locationOptions) {
            switch (foundOption) {
                case "Domestic Customers Only ":
                    elementShouldBePresent("todos.domestic_cust_section_cust_profile_todos");
                    String option24 = findElement("todos.domestic_cust_section_cust_profile_todos").getText();
                    System.out.println(option24);
                    Assert.assertTrue(option24.contains("Domestic Customers Only "));
                    break;

                case "International Customers Only ":
                    elementShouldBePresent("todos.international_cust_section_cust_profile_todos");
                    String option32 = findElement("todos.international_cust_section_cust_profile_todos").getText();
                    System.out.println(option32);
                    Assert.assertTrue(option32.contains("International Customers Only "));
                    break;

            }
        }
    }

    @When("^I select a filter criteria from Customer Location$")
    public void iSelectAFilterCriteriaFromCustomerLocation(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Domestic Customers Only":
                    Wait.untilElementPresent("todos.domestic_cust_section_cust_profile_todos");
                    elementShouldBePresent("todos.domestic_cust_section_cust_profile_todos");
                    Clicks.click("todos.domestic_cust_section_cust_profile_todos");
                    break;

                case "International Customers Only":
                    Wait.untilElementPresent("todos.international_cust_section_cust_profile_todos");
                    elementShouldBePresent("todos.international_cust_section_cust_profile_todos");
                    Clicks.click("todos.international_cust_section_cust_profile_todos");
                    break;

            }
        }

    }

    @Then("^I should see the filter criteria selected from Customer Location$")
    public void iShouldSeeTheFilterCriteriaSelectedFromCustomerLocation(List<String> filterX) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "Domestic Customers Only":
                    Wait.untilElementPresent("todos.domestic_cust_section_cust_profile_todos");
                    WebElement closeFilterX24 = findElement("todos.domestic_cust_section_cust_profile_todos");
                    Assert.assertTrue("Filter is selected", closeFilterX24.isDisplayed());
                    break;

                case "International Customers Only":
                    Wait.untilElementPresent("todos.international_cust_section_cust_profile_todos");
                    WebElement closeFilterX31 = findElement("todos.international_cust_section_cust_profile_todos");
                    Assert.assertTrue("Filter is selected", closeFilterX31.isDisplayed());
                    break;

            }
        }
    }

    @When("^I remove the filter criteria from Customer Location$")
    public void iRemoveTheFilterCriteriaFromCustomerLocation(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "Domestic Customers Only":
                    Clicks.click("todos.domestic_cust_x_icon");
                    break;

                case "International Customers Only":
                    Clicks.click("todos.international_cust_x_icon");
                    break;

            }
        }
    }

    @Then("^I should no longer see the filter criteria selected from Customer Location$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedFromCustomerLocation(List<String> noFilter) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "Domestic Customers Only":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter24 = findElement("todos.domestic_cust_x_icon");
                    Assert.assertFalse("Filter is not selected", noFilter24.isDisplayed());
                    break;

                case "International Customers Only":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter31 = findElement("todos.international_cust_x_icon");
                    Assert.assertFalse("Filter is not selected", noFilter31.isDisplayed());
                    break;
            }
        }
    }

    @Then("^the CREATE MY LIST Screen is displayed$")
    public void theCREATEMYLISTScreenIsDisplayed() throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_page");
        Elements.elementShouldBePresent("todos.create_my_list_page");
        String createListheader = findElement("todos.create_my_list_header").getText().trim();
        System.out.println("CREATE LIST HEADER = " + createListheader);
        Assert.assertEquals("CREATE MY LIST", createListheader);
    }

    @And("^I should see the input boxes to create a list$")
    public void iShouldSeeTheInputBoxesToCreateAList(List<String> field) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_page");
        for (String afield : field) {
            switch (afield) {
                case "TITLE":
                    Elements.elementShouldBePresent("todos.create_my_list_title_field");
                    WebElement titleLabel = findElement("todos.create_my_list_title_label");
                    Assert.assertTrue(titleLabel.getText().contains("Title"));
                    break;
                case "DATE":
                    Elements.elementShouldBePresent("todos.create_my_list_date_field");
                    WebElement dateLabel = findElement("todos.create_my_list_date_label");
                    Assert.assertTrue(dateLabel.getText().contains("Due by Date"));
                    break;
                case "DESCRIPTION":
                    Elements.elementShouldBePresent("todos.create_my_list_desc_field");
                    WebElement desctLabel = findElement("todos.create_my_list_desc_label");
                    Assert.assertTrue(desctLabel.getText().contains("Description"));
                    break;
                default:
                    fail("NOT a valid field");
            }
        }
    }

    @And("^the CREATE MY LIST button should be displayed in the Create List view$")
    public void theCREATEMYLISTButtonShouldBeDisplayedInTheCreateListView() throws Throwable {
        WebElement createButton = findElement("todos.create_my_list_create_button");
        elementShouldBePresent(createButton);
        Assert.assertEquals("Create My List", createButton.getText());
    }

    @And("^the CANCEL button should be displayed in the Create List view$")
    public void theCANCELButtonShouldBeDisplayedInTheCreateListView() throws Throwable {
        WebElement createButton = findElement("todos.create_my_list_cancel_button");
        elementShouldBePresent("todos.create_my_list_cancel_button");
        Assert.assertEquals("Cancel", createButton.getText());
    }

    @And("^the Required Fields note should be displayed in the Create List view$")
    public void theRequiredFieldsNoteShouldBeDisplayedInTheCreateListView() throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_req_fields_note");
        elementShouldBePresent("todos.create_my_list_req_fields_note");
    }

    @And("^I select a valid date in Create List View on CREATE LIST page$")
    public void iSelectAValidDateInCreateListViewOnCREATELISTPage() throws Throwable {
        LocalDate date = LocalDate.now().plusDays(1);
        TextBoxes.typeTextbox("todos.create_my_list_date_field", DATE_TIME_FORMATTER.format(date));
    }

    @Then("^the CREATE MY LIST button should be disabled in the Create List view$")
    public void theCREATEMYLISTButtonShouldBeDisabledInTheCreateListView() throws Throwable {
        WebElement createListButton = findElement("todos.create_my_list_create_button");
        Assert.assertFalse(createListButton.isEnabled());
    }

    @And("^I enter a title \"([^\"]*)\" in the input field in CREATE LIST page$")
    public void iEnterATitleInTheInputFieldInCREATELISTPage(String listitle) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_title_field");
        TextBoxes.typeTextbox("todos.create_my_list_title_field", listitle);
    }

    @Then("^the CREATE MY LIST button should be enabled in the Create List view$")
    public void theCREATEMYLISTButtonShouldBeEnabledInTheCreateListView() throws Throwable {
        WebElement createListButton = findElement("todos.create_my_list_create_button");
        Assert.assertTrue(createListButton.isEnabled());
    }

    @When("^I enter (\\d+) characters in the Description input field on CREATE LIST page$")
    public void iEnterCharactersInTheDescriptionInputFieldOnCREATELISTPage(int value) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_desc_field");
        TextBoxes.typeTextbox("todos.create_my_list_desc_field", RandomStringUtils.randomAlphabetic(value));
    }

    @Then("^(\\d+) characters should be displayed in the Description input field on CREATE LIST page$")
    public void charactersShouldBeDisplayedInTheDescriptionInputFieldOnCREATELISTPage(int count) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_desc_field");
        Integer descrCount = findElement("todos.create_my_list_desc_field").getAttribute("value").length();
        if (count != descrCount) {
            Assert.fail("Incorrect character count, should be 250");
        }
    }

    @And("^I click on CANCEL button from Create LIST page$")
    public void iClickOnCANCELButtonFromCreateLISTPage() throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_cancel_button");
        Clicks.click("todos.create_my_list_cancel_button");
    }

    @And("^I select the CREATE MY LIST button from Create List view$")
    public void iSelectTheCREATEMYLISTButtonFromCreateListView() throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_create_button");
        Clicks.click("todos.create_my_list_create_button");
    }

    @And("^I enter a description \"([^\"]*)\" in the input field on CREATE LIST page$")
    public void iEnterADescriptionInTheInputFieldOnCREATELISTPage(String desc) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_desc_field");
        TextBoxes.typeTextbox("todos.create_my_list_desc_field", desc);
    }

    @And("^the List \"([^\"]*)\" is displayed on the MY MACYS TO DOS tab$")
    public void theListIsDisplayedOnTheMYMACYSTODOSTab(String listTitle) throws Throwable {
//        THIS IS A TEMPORARY WORKAROUND TO REFRESH THE BROWSER, LIST SHOULD APPEAR WITHOUT REFRESH
        Navigate.browserRefresh();
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        try {
            WebElement list = findElement(By.xpath("//span[contains(text(), '" + listTitle + "')]"));
            Elements.elementShouldBePresent(list);
        } catch (Exception e) {
            theListIsDisplayedOnTheMYMACYSTODOSTab(listTitle);
        }
    }

    @And("^I should see the Visits in the last 12 Months filter criteria Header$")
    public void iShouldSeeTheVisitsInTheLast12MonthsFilterCriteriaHeader() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Clicks.hoverForSelection("todos.transactions_section_todos");
        Elements.elementShouldBePresent("todos.transactions_section_todos");
        String visitsSection = findElement("todos.visits_section_transactions_todos").getText();
        System.out.println(visitsSection);
        Assert.assertTrue(visitsSection.contains("Visits in the last 12 Months"));
    }

    @And("^I should see the following options below Visits in the Last 12 Months filter$")
    public void iShouldSeeTheFollowingOptionsBelowVisitsInTheLast12MonthsFilter(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.second_section_third_subsection");
        elementShouldBePresent("todos.second_section_third_subsection");
        for (String foundOption : options) {
            switch (foundOption) {
                case "0 Visits":
                    elementShouldBePresent("todos.second_section_third_subsection_first_filter_option");
                    String option0 = findElement("todos.second_section_third_subsection_first_filter_option").getText();
                    System.out.println(option0);
                    Assert.assertTrue(option0.contains(foundOption));
                    break;
                case "1 Visits":
                    elementShouldBePresent("todos.second_section_third_subsection_second_filter_option");
                    String option1 = findElement("todos.second_section_third_subsection_second_filter_option").getText();
                    System.out.println(option1);
                    Assert.assertTrue(option1.contains(foundOption));
                    break;
                case "2-3 Visits":
                    elementShouldBePresent("todos.second_section_third_subsection_third_filter_option");
                    String option3 = findElement("todos.second_section_third_subsection_third_filter_option").getText();
                    System.out.println(option3);
                    Assert.assertTrue(option3.contains(foundOption));
                    break;
                case "4+ Visits":
                    elementShouldBePresent("todos.second_section_third_subsection_fourth_filter_option");
                    String option4 = findElement("todos.second_section_third_subsection_fourth_filter_option").getText();
                    System.out.println(option4);
                    Assert.assertTrue(option4.contains(foundOption));
                    break;
                default:
                    Assert.fail("[ERROR] OPTION IS NOT VALID");
                    break;
            }
        }
    }

    @When("^I click the chevron of Transactions filter criteria$")
    public void iClickTheChevronOfTransactionsFilterCriteria() throws Throwable {
        Wait.untilElementPresent("todos.second_section_chevron");
        elementShouldBePresent("todos.second_section_chevron");
        Clicks.click("todos.second_section_chevron");
    }

    @Then("^I should see the Transactions filter criteria collapsed$")
    public void iShouldSeeTheTransactionsFilterCriteriaCollapsed(List<String> section) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String selectedSection : section) {
            switch (selectedSection) {
                case "Visits in the last 12 Months":
                    WebElement section0 = findElement("todos.second_section_third_subsection");
                    Assert.assertFalse("Section not collapsed", section0.isDisplayed());
                    break;
                default:
                    Assert.fail("[ERROR] INVALID SECTION");
                    break;
            }
        }
    }

    @Then("^I should see the Transactions filter criteria expanded$")
    public void iShouldSeeTheTransactionsFilterCriteriaExpanded(List<String> section) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String selectedSection : section) {
            switch (selectedSection) {
                case "Visits in the last 12 Months":
                    WebElement section1 = findElement("todos.second_section_third_subsection");
                    Assert.assertTrue(section1.isDisplayed());
                    break;
                default:
                    Assert.fail("[ERROR] OPTION IS NOT VALID");
                    break;
            }
        }
    }

    @When("^I select a filter criteria from Visits in the Last 12 Months$")
    public void iSelectAFilterCriteriaFromVisitsInTheLastMonths(List<String> option) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String selOption : option) {
            switch (selOption) {
                case "1 Visits":
                    Wait.untilElementPresent("todos.second_section_third_subsection_second_filter_option");
                    elementShouldBePresent("todos.second_section_third_subsection_second_filter_option");
                    Clicks.hoverForSelection("todos.second_section_third_subsection_second_filter_option");
                    Clicks.click("todos.second_section_third_subsection_second_filter_option");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;
                default:
                    Assert.fail("[ERROR] OPTION IS NOT VALID");
                    break;
            }
        }
    }

    @Then("^I should see the filter criteria selected from Visits in the Last 12 Months$")
    public void iShouldSeeTheFilterCriteriaSelectedFromVisitsInTheLastMonths(List<String> option) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String selOption : option) {
            switch (selOption) {
                case "1 Visits":
                    Wait.untilElementPresent("todos.close_second_section_third_subsection_second_filter_option");
                    WebElement closeFilter0 = findElement("todos.close_second_section_third_subsection_second_filter_option");
                    Clicks.hoverForSelection(closeFilter0);
                    Assert.assertTrue("Filter is selected", closeFilter0.isDisplayed());
                    break;
                default:
                    Assert.fail("[ERROR] OPTION IS NOT VALID");
                    break;
            }
        }
    }

    @When("^I remove the filter criteria from Visits in the Last 12 Months$")
    public void iRemoveTheFilterCriteriaFromVisitsInTheLastMonths(List<String> option) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String selOption : option) {
            switch (selOption) {
                case "1 Visits":
                    Clicks.hoverForSelection("todos.close_second_section_third_subsection_second_filter_option");
                    Clicks.click("todos.close_second_section_third_subsection_second_filter_option");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;
                default:
                    Assert.fail("[ERROR] OPTION IS NOT VALID");
                    break;
            }
        }
    }

    @Then("^I should no longer see the filter criteria selected from Visits in the Last 12 Months$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedFromVisitsInTheLastMonths(List<String> option) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String selOption : option) {
            switch (selOption) {
                case "1 Visits":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilter0 = findElement("todos.close_second_section_third_subsection_second_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilter0.isDisplayed());
                    break;
                default:
                    Assert.fail("[ERROR] OPTION IS NOT VALID");
                    break;
            }
        }
    }

    @And("^the list will not exceed (\\d+) Top Tier Customers$")
    public void theListWillNotExceedTopTierCustomers(int maxCount) throws Throwable {
        WebElement platCount = findElement(By.id("count" + topListPlatinumPosition));
        elementShouldBePresent(platCount);
        String countNumber = platCount.getText().trim();
        System.out.println("COUNT STRING = " + countNumber);
        countNumber = countNumber.replaceAll("[^\\d]", "");
        Integer countInt = Integer.parseInt(countNumber);
        System.out.println("CUSTOMER INTEGER COUNT = " + countInt);
        Assert.assertTrue(countInt <= maxCount);
    }

    @And("^the customer count will display on the My Platinum Customers Create List dashboard$")
    public void theCustomerCountWillDisplayOnTheMyPlatinumCustomersCreateListDashboard() throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        WebElement platCount = findElement(By.id("count" + topListPlatinumPosition));
        elementShouldBePresent(platCount);
        String countNumber = platCount.getText().trim();
        Matcher matcher = Pattern.compile("\\(([0-9]+)\\)").matcher(countNumber);
        if (matcher.find()) {
            assertTrue("Customer count is displayed", true);
        } else {
            Assert.fail("No customer count is displayed");
        }
    }

    @And("^I should see the following options below Loyalty Level filter$")
    public void iShouldSeeTheFollowingOptionsBelowLoyaltyLevelFilter(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_first_subsection");
        elementShouldBePresent("todos.first_section_first_subsection");
        for (String foundOption : options) {
            switch (foundOption) {
                case "All Star Rewards / All Loyallists":
                    if (macys()) {
                        elementShouldBePresent("todos.first_section_first_subsection_first_filter_option");
                        String allStar = findElement("todos.first_section_first_subsection_first_filter_option").getText();
                        System.out.println(allStar);
                        Assert.assertTrue(allStar.contains("All Star Rewards"));
                    } else {
                        elementShouldBePresent("todos.first_section_first_subsection_first_filter_option");
                        String allStar = findElement("todos.first_section_first_subsection_first_filter_option").getText();
                        System.out.println(allStar);
                        Assert.assertTrue(allStar.contains("All Loyallists"));
                        break;
                    }


                case "Platinum / Top of List":
                    if (macys()) {
                        elementShouldBePresent("todos.first_section_first_subsection_second_filter_option");
                        String platinum = findElement("todos.first_section_first_subsection_second_filter_option").getText();
                        System.out.println(platinum);
                        Assert.assertTrue(platinum.contains("Platinum"));
                        break;
                    } else {
                        elementShouldBePresent("todos.first_section_first_subsection_second_filter_option");
                        String platinum = findElement("todos.first_section_first_subsection_second_filter_option").getText();
                        System.out.println(platinum);
                        Assert.assertTrue(platinum.contains("Top of List"));
                        break;
                    }

                case "Gold / BCC Loyallist":
                    if (macys()) {
                        elementShouldBePresent("todos.first_section_first_subsection_third_filter_option");
                        String gold = findElement("todos.first_section_first_subsection_third_filter_option").getText();
                        System.out.println(gold);
                        Assert.assertTrue(gold.contains("Gold"));
                        break;
                    } else {
                        elementShouldBePresent("todos.first_section_first_subsection_third_filter_option");
                        String gold = findElement("todos.first_section_first_subsection_third_filter_option").getText();
                        System.out.println(gold);
                        Assert.assertTrue(gold.contains("BCC Loyallist"));
                        break;
                    }

                case "Silver / Non-BCC Loyallists":
                    if (macys()) {
                        elementShouldBePresent("todos.first_section_first_subsection_fourth_filter_option");
                        String silver = findElement("todos.first_section_first_subsection_fourth_filter_option").getText();
                        System.out.println(silver);
                        Assert.assertTrue(silver.contains("Silver"));
                        break;
                    } else {
                        elementShouldBePresent("todos.first_section_first_subsection_fourth_filter_option");
                        String silver = findElement("todos.first_section_first_subsection_fourth_filter_option").getText();
                        System.out.println(silver);
                        Assert.assertTrue(silver.contains("Non-BCC Loyallists"));
                        break;
                    }


                case "Non-Star Rewards / Non-Loyallists":
                    if (macys()) {
                        elementShouldBePresent("todos.first_section_first_subsection_fifth_filter_option");
                        String nonStar = findElement("todos.first_section_first_subsection_fifth_filter_option").getText();
                        System.out.println(nonStar);
                        Assert.assertTrue(nonStar.contains("Non-Star Rewards"));
                        break;
                    } else {
                        elementShouldBePresent("todos.first_section_first_subsection_fifth_filter_option");
                        String nonStar = findElement("todos.first_section_first_subsection_fifth_filter_option").getText();
                        System.out.println(nonStar);
                        Assert.assertTrue(nonStar.contains("Non-Loyallists"));
                        break;
                    }


                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @Then("^I should see the Loyalty Level filter criteria collapsed$")
    public void iShouldSeeTheLoyaltyLevelFilterCriteriaCollapsed(List<String> sectionColapsed) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionColapsed) {
            switch (option) {
                case "All Star Rewards / All Loyallists":
                    WebElement section = findElement("todos.first_section_first_subsection_first_filter_option");
                    Assert.assertFalse("Section not collapsed", section.isDisplayed());
                    break;

                case "Platinum / Top of List":
                    WebElement section0 = findElement("todos.first_section_first_subsection_second_filter_option");
                    Assert.assertFalse("Section not collapsed", section0.isDisplayed());
                    break;

                case "Gold / BCC Loyallist":
                    WebElement section1 = findElement("todos.first_section_first_subsection_third_filter_option");
                    Assert.assertFalse("Section not collapsed", section1.isDisplayed());
                    break;

                case "Silver / Non-BCC Loyallists":
                    WebElement section2 = findElement("todos.first_section_first_subsection_fourth_filter_option");
                    Assert.assertFalse("Section not collapsed", section2.isDisplayed());
                    break;

                case "Non-Star Rewards / Non-Loyallists":
                    WebElement section3 = findElement("todos.first_section_first_subsection_fifth_filter_option");
                    Assert.assertFalse("Section not collapsed", section3.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @Then("^I should see the Loyalty Level filter criteria expanded$")
    public void iShouldSeeTheLoyaltyLevelFilterCriteriaExpanded(List<String> sectionExpanded) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionExpanded) {
            switch (option) {
                case "All Star Rewards / All Loyallists":
                    WebElement section = findElement("todos.first_section_first_subsection_first_filter_option");
                    Assert.assertTrue(section.isDisplayed());
                    break;


                case "Platinum / Top of List":
                    WebElement section0 = findElement("todos.first_section_first_subsection_second_filter_option");
                    Assert.assertTrue(section0.isDisplayed());
                    break;

                case "Gold / BCC Loyallist":
                    WebElement section1 = findElement("todos.first_section_first_subsection_third_filter_option");
                    Assert.assertTrue(section1.isDisplayed());
                    break;

                case "Silver / Non-BCC Loyallists":
                    WebElement section2 = findElement("todos.first_section_first_subsection_fourth_filter_option");
                    Assert.assertTrue(section2.isDisplayed());
                    break;

                case "Non-Star Rewards / Non-Loyallists":
                    WebElement section3 = findElement("todos.first_section_first_subsection_fifth_filter_option");
                    Assert.assertTrue(section3.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @And("^I should see the \"([^\"]*)\" filter criteria Header Loyalty Dollar$")
    public void iShouldSeeTheFilterCriteriaHeaderLoyaltyDollar(String sectionName) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_new_list_view");
        elementShouldBePresent("todos.first_section_new_list_view");
        String firstSection = findElement("todos.first_section_second_subsection").getText();
        System.out.println(firstSection);
        Assert.assertTrue(firstSection.contains(sectionName));
    }

    @When("^I select a filter criteria from Loyalty Level section$")
    public void iSelectAFilterCriteriaFromLoyaltyLevelSection(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "All Star Rewards / All Loyallists":
                    Wait.untilElementPresent("todos.first_section_first_subsection_first_filter_option");
                    elementShouldBePresent("todos.first_section_first_subsection_first_filter_option");
                    Clicks.click("todos.first_section_first_subsection_first_filter_option");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "Platinum / Top of List":
                    Wait.untilElementPresent("todos.first_section_first_subsection_second_filter_option");
                    elementShouldBePresent("todos.first_section_first_subsection_second_filter_option");
                    Clicks.click("todos.first_section_first_subsection_second_filter_option");
                    break;

                case "Gold / BCC Loyallist":
                    Wait.untilElementPresent("todos.first_section_first_subsection_third_filter_option");
                    elementShouldBePresent("todos.first_section_first_subsection_third_filter_option");
                    Clicks.click("todos.first_section_first_subsection_third_filter_option");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "Silver / Non-BCC Loyallists":
                    Wait.untilElementPresent("todos.first_section_first_subsection_fourth_filter_option");
                    elementShouldBePresent("todos.first_section_first_subsection_fourth_filter_option");
                    Clicks.click("todos.first_section_first_subsection_fourth_filter_option");
                    break;

                case "Non-Star Rewards / Non-Loyallists":
                    Wait.untilElementPresent("todos.first_section_first_subsection_fifth_filter_option");
                    elementShouldBePresent("todos.first_section_first_subsection_fifth_filter_option");
                    Clicks.click("todos.first_section_first_subsection_fifth_filter_option");
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @Then("^I should see the filter criteria selected from Loyalty Level section$")
    public void iShouldSeeTheFilterCriteriaSelectedFromLoyaltyLevelSection(List<String> filterX) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "All Star Rewards / All Loyallists":
                    Wait.untilElementPresent("todos.close_first_section_first_subsection_first_filter_option");
                    WebElement closeFilterXAllStar = findElement("todos.close_first_section_first_subsection_first_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterXAllStar.isDisplayed());
                    break;

                case "Platinum / Top of List":
                    Wait.untilElementPresent("todos.close_first_section_first_subsection_second_filter_option");
                    WebElement closeFilterXplatinum = findElement("todos.close_first_section_first_subsection_second_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterXplatinum.isDisplayed());
                    break;

                case "Gold / BCC Loyallist":
                    Wait.untilElementPresent("todos.close_first_section_first_subsection_third_filter_option");
                    WebElement closeFilterXgold = findElement("todos.close_first_section_first_subsection_third_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterXgold.isDisplayed());
                    break;

                case "Silver / Non-BCC Loyallists":
                    Wait.untilElementPresent("todos.close_first_section_first_subsection_fourth_filter_option");
                    WebElement closeFilterXsilver = findElement("todos.close_first_section_first_subsection_fourth_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterXsilver.isDisplayed());
                    break;

                case "Non-Star Rewards / Non-Loyallists":
                    Wait.untilElementPresent("todos.close_first_section_second_subsection_fifth_filter_option");
                    WebElement closeFilterXnonStar = findElement("todos.close_first_section_first_subsection_fifth_filter_option");
                    Assert.assertTrue("Filter is selected", closeFilterXnonStar.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @When("^I remove the filter criteria from Loyalty Level section$")
    public void iRemoveTheFilterCriteriaFromLoyaltyLevelSection(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "All Star Rewards / All Loyallists":
                    Clicks.click("todos.close_first_section_first_subsection_first_filter_option");
                    Thread.sleep(2000);
                    break;

                case "Platinum / Top of List":
                    Clicks.click("todos.close_first_section_first_subsection_second_filter_option");
                    break;

                case "Gold / BCC Loyallist":
                    Clicks.click("todos.close_first_section_first_subsection_third_filter_option");
                    Thread.sleep(2000);
                    break;

                case "Silver / Non-BCC Loyallists":
                    Clicks.click("todos.close_first_section_first_subsection_fourth_filter_option");
                    break;

                case "Non-Star Rewards / Non-Loyallists":
                    Clicks.click("todos.close_first_section_first_subsection_fifth_filter_option");
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @Then("^I should no longer see the filter criteria selected from Loyalty Level section$")
    public void iShouldNoLongerSeeTheFilterCriteriaSelectedFromLoyaltyLevelSection(List<String> noFilter) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "All Star Rewards / All Loyallists":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterAllStar = findElement("todos.close_first_section_first_subsection_first_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilterAllStar.isDisplayed());
                    break;

                case "Platinum / Top of List":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterPlatinum = findElement("todos.close_first_section_first_subsection_second_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilterPlatinum.isDisplayed());
                    break;

                case "Gold / BCC Loyallist":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterGold = findElement("todos.close_first_section_first_subsection_third_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilterGold.isDisplayed());
                    break;

                case "Silver / Non-BCC Loyallists":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterSilver = findElement("todos.close_first_section_first_subsection_fourth_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilterSilver.isDisplayed());
                    break;

                case "Non-Star Rewards / Non-Loyallists":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterNonStar = findElement("todos.close_first_section_first_subsection_fifth_filter_option");
                    Assert.assertFalse("Filter is not selected", noFilterNonStar.isDisplayed());
                    break;

                default:
                    Assert.fail("RANDOM FAIL");
                    break;
            }
        }
    }

    @And("^I should see the \"([^\"]*)\" filter criteria Join Upgrade Header$")
    public void iShouldSeeTheFilterCriteriaJoinUpgradeHeader(String sectionName) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_new_list_view");
        elementShouldBePresent("todos.first_section_new_list_view");
        String firstSection = findElement("todos.first_section_fourth_subsection").getText();
        System.out.println(firstSection);
        Assert.assertTrue(firstSection.contains(sectionName));
    }

    @And("^I should see the following options below Join Upgrade filter$")
    public void iShouldSeeTheFollowingOptionsBelowJoinUpgradeFilter(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_fourth_subsection");
        elementShouldBePresent("todos.first_section_fourth_subsection");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Join Date":
                    Clicks.hoverForSelection("todos.first_section_fourth_subsection_first_filter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter");
                    String allStar = findElement("todos.first_section_fourth_subsection_first_filter").getText();
                    System.out.println(allStar);
                    Assert.assertTrue(allStar.contains("Join Date"));
                    break;

                case "Upgrade Date":
                    Clicks.hoverForSelection("todos.first_section_fourth_subsection_second_filter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter");
                    String platinum = findElement("todos.first_section_fourth_subsection_second_filter").getText();
                    System.out.println(platinum);
                    Assert.assertTrue(platinum.contains("Upgrade Date"));
                    break;

            }
        }
    }

    @When("^user selects the following option bellow Join Upgrade filter$")
    public void userSelectsTheFollowingOptionBellowJoinUpgradeFilter(List<String> selectedOption) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Join Date":
                    Wait.untilElementPresent("todos.first_section_fourth_subsection_first_filter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter");
                    Clicks.click("todos.first_section_fourth_subsection_first_filter");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "Upgrade Date":
                    Wait.untilElementPresent("todos.first_section_fourth_subsection_second_filter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter");
                    Clicks.click("todos.first_section_fourth_subsection_second_filter");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;
            }
        }
    }

    @Then("^the following options are displayed bellow the first secondary filter Join Upgrade$")
    public void theFollowingOptionsAreDisplayedBellowTheFirstSecondaryFilterJoinUpgrade(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_fourth_subsection");
        elementShouldBePresent("todos.first_section_fourth_subsection");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Last Month":
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter_first_subfilter");
                    String lastMonth = findElement("todos.first_section_fourth_subsection_first_filter_first_subfilter").getText();
                    System.out.println(lastMonth);
                    Assert.assertTrue(lastMonth.contains("Last Month"));
                    break;

                case "Last 3 Months":
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter_second_subfilter");
                    String last3M = findElement("todos.first_section_fourth_subsection_first_filter_second_subfilter").getText();
                    System.out.println(last3M);
                    Assert.assertTrue(last3M.contains("Last 3 Months"));
                    break;

                case "Last 6 Months":
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter_third_subfilter");
                    String last6M = findElement("todos.first_section_fourth_subsection_first_filter_third_subfilter").getText();
                    System.out.println(last6M);
                    Assert.assertTrue(last6M.contains("Last 6 Months"));
                    break;

                case "Last 12 Months":
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter_fourth_subfilter");
                    String last12M = findElement("todos.first_section_fourth_subsection_first_filter_fourth_subfilter").getText();
                    System.out.println(last12M);
                    Assert.assertTrue(last12M.contains("Last 12 Months"));
                    break;

            }
        }
    }

    @Then("^I should see the Join Upgrade section filter criteria collapsed$")
    public void iShouldSeeTheJoinUpgradeSectionFilterCriteriaCollapsed(List<String> sectionColapsed) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionColapsed) {
            switch (option) {
                case "Join Date":
                    WebElement section = findElement("todos.first_section_fourth_subsection_first_filter");
                    Assert.assertFalse("Section not collapsed", section.isDisplayed());
                    break;

                case "Upgrade Date":
                    WebElement section0 = findElement("todos.first_section_fourth_subsection_second_filter");
                    Assert.assertFalse("Section not collapsed", section0.isDisplayed());
                    break;
            }
        }
    }

    @Then("^I should see the Join Upgrade filter section filter criteria expanded$")
    public void iShouldSeeTheJoinUpgradeFilterSectionFilterCriteriaExpanded(List<String> sectionExpanded) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : sectionExpanded) {
            switch (option) {
                case "Join Date":
                    WebElement section = findElement("todos.first_section_fourth_subsection_first_filter");
                    Assert.assertTrue(section.isDisplayed());
                    break;

                case "Upgrade Date":
                    WebElement section0 = findElement("todos.first_section_fourth_subsection_second_filter");
                    Assert.assertTrue(section0.isDisplayed());
                    break;
            }
        }
    }


    @Then("^the following options are displayed bellow the second secondary filter Join Upgrade$")
    public void theFollowingOptionsAreDisplayedBellowTheSecondSecondaryFilterJoinUpgrade(List<String> options) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.first_section_fourth_subsection");
        elementShouldBePresent("todos.first_section_fourth_subsection");
        for (String foundOption : options) {
            switch (foundOption) {
                case "Last Month":
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter_first_subfilter");
                    String lastMonth = findElement("todos.first_section_fourth_subsection_second_filter_first_subfilter").getText();
                    System.out.println(lastMonth);
                    Assert.assertTrue(lastMonth.contains("Last Month"));
                    break;

                case "Last 3 Months":
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter_second_subfilter");
                    String last3M = findElement("todos.first_section_fourth_subsection_second_filter_second_subfilter").getText();
                    System.out.println(last3M);
                    Assert.assertTrue(last3M.contains("Last 3 Months"));
                    break;

                case "Last 6 Months":
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter_third_subfilter");
                    String last6M = findElement("todos.first_section_fourth_subsection_second_filter_third_subfilter").getText();
                    System.out.println(last6M);
                    Assert.assertTrue(last6M.contains("Last 6 Months"));
                    break;

                case "Last 12 Months":
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter_fourth_subfilter");
                    String last12M = findElement("todos.first_section_fourth_subsection_second_filter_fourth_subfilter").getText();
                    System.out.println(last12M);
                    Assert.assertTrue(last12M.contains("Last 12 Months"));
                    break;

            }
        }
    }

    @When("^I remove the filter from the following primary filter option Join Upgrade$")
    public void iRemoveTheFilterFromTheFollowingPrimaryFilterOptionJoinUpgrade(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "Join Date":
                    Clicks.click("todos.close_first_section_fourth_subsection_first_filter");
                    Thread.sleep(2000);
                    break;

                case "Upgrade Date":
                    Clicks.click("todos.close_first_section_fourth_subsection_second_filter");
                    break;

            }
        }

    }

    @And("^user selects a secondary filter criteria from second filter option Join Upgrade$")
    public void userSelectsASecondaryFilterCriteriaFromSecondFilterOptionJoinUpgrade(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Last Month":
                    Wait.untilElementPresent("todos.first_section_fourth_subsection_second_filter_first_subfilter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter_first_subfilter");
                    Clicks.click("todos.first_section_fourth_subsection_second_filter_first_subfilter");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "Last 3 Months":
                    Wait.untilElementPresent("todos.first_section_fourth_subsection_second_filter_second_subfilter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_second_filter_second_subfilter");
                    Clicks.click("todos.first_section_fourth_subsection_second_filter_second_subfilter");
                    break;
            }
        }
    }

    @Then("^I should see the secondary filter from the second filter option Join Upgrade$")
    public void iShouldSeeTheSecondaryFilterFromTheSecondFilterOptionJoinUpgrade(List<String> filterX) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "Last Month":
                    Wait.untilElementPresent("todos.close_first_section_fourth_subsection_second_filter_first_subfilter");
                    WebElement closeFilterXlastMonh = findElement("todos.close_first_section_fourth_subsection_second_filter_first_subfilter");
                    Assert.assertTrue("Filter is selected", closeFilterXlastMonh.isDisplayed());
                    break;

                case "Last 3 Months":
                    Wait.untilElementPresent("todos.close_first_section_fourth_subsection_second_filter_second_subfilter");
                    WebElement closeFilterXlast3 = findElement("todos.close_first_section_fourth_subsection_second_filter_second_subfilter");
                    Assert.assertTrue("Filter is selected", closeFilterXlast3.isDisplayed());
                    break;
            }
        }
    }

    @When("^I remove the secondary filter from the second filter option Join Upgrade$")
    public void iRemoveTheSecondaryFilterFromTheSecondFilterOptionJoinUpgrade(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "Last Month":
                    Clicks.click("todos.close_first_section_fourth_subsection_second_filter_first_subfilter");
                    Thread.sleep(2000);
                    break;

                case "Last 3 Months":
                    Clicks.click("todos.close_first_section_fourth_subsection_second_filter_second_subfilter");
                    break;

            }
        }
    }


    @Then("^I should no longer see the secondary filter from second filter selected Join Upgrade$")
    public void iShouldNoLongerSeeTheSecondaryFilterFromSecondFilterSelectedJoinUpgrade(List<String> noFilter) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "Last Month":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterLastMonth = findElement("todos.close_first_section_fourth_subsection_second_filter_first_subfilter");
                    Assert.assertFalse("Filter is not selected", noFilterLastMonth.isDisplayed());
                    break;

                case "Last 3 Months":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterLast3M = findElement("todos.close_first_section_fourth_subsection_second_filter_second_subfilter");
                    Assert.assertFalse("Filter is not selected", noFilterLast3M.isDisplayed());
                    break;
            }
        }
    }

    @And("^user selects a secondary filter criteria from first filter option Join Upgrade$")
    public void userSelectsASecondaryFilterCriteriaFromFirstFilterOptionJoinUpgrade(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Last Month":
                    Wait.untilElementPresent("todos.first_section_fourth_subsection_first_filter_first_subfilter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter_first_subfilter");
                    Clicks.click("todos.first_section_fourth_subsection_first_filter_first_subfilter");
                    Wait.untilElementNotPresent("homepage.loading_page");
                    break;

                case "Last 3 Months":
                    Wait.untilElementPresent("todos.first_section_fourth_subsection_first_filter_second_subfilter");
                    elementShouldBePresent("todos.first_section_fourth_subsection_first_filter_second_subfilter");
                    Clicks.click("todos.first_section_fourth_subsection_first_filter_second_subfilter");
                    break;
            }
        }
    }

    @And("^I should see the secondary filter from the first filter option Join Upgrade$")
    public void iShouldSeeTheSecondaryFilterFromTheFirstFilterOptionJoinUpgrade(List<String> filterX) throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        for (String option : filterX) {
            switch (option) {
                case "Last Month":
                    Wait.untilElementPresent("todos.close_first_section_fourth_subsection_first_filter_first_subfilter");
                    WebElement closeFilterXlastMonh = findElement("todos.close_first_section_fourth_subsection_first_filter_first_subfilter");
                    Assert.assertTrue("Filter is selected", closeFilterXlastMonh.isDisplayed());
                    break;

                case "Last 3 Months":
                    Wait.untilElementPresent("todos.close_first_section_fourth_subsection_first_filter_second_subfilter");
                    WebElement closeFilterXlast3 = findElement("todos.close_first_section_fourth_subsection_first_filter_second_subfilter");
                    Assert.assertTrue("Filter is selected", closeFilterXlast3.isDisplayed());
                    break;
            }
        }
    }

    @When("^I remove the secondary filter from the first filter option Join Upgrade$")
    public void iRemoveTheSecondaryFilterFromTheFirstFilterOptionJoinUpgrade(List<String> removeOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : removeOption) {
            switch (option) {
                case "Last Month":
                    Clicks.click("todos.close_first_section_fourth_subsection_first_filter_first_subfilter");
                    Thread.sleep(2000);
                    break;

                case "Last 3 Months":
                    Clicks.click("todos.close_first_section_fourth_subsection_first_filter_second_subfilter");
                    break;

            }
        }
    }

    @Then("^I should no longer see the secondary filter from first filter selected Join Upgrade$")
    public void iShouldNoLongerSeeTheSecondaryFilterFromFirstFilterSelectedJoinUpgrade(List<String> noFilter) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : noFilter) {
            switch (option) {
                case "Last Month":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterLastMonth = findElement("todos.close_first_section_fourth_subsection_first_filter_first_subfilter");
                    Assert.assertFalse("Filter is not selected", noFilterLastMonth.isDisplayed());
                    break;

                case "Last 3 Months":
                    Wait.untilElementPresent("todos.create_list_dashboard");
                    WebElement noFilterLast3M = findElement("todos.close_first_section_fourth_subsection_first_filter_second_subfilter");
                    Assert.assertFalse("Filter is not selected", noFilterLast3M.isDisplayed());
                    break;
            }
        }
    }
    @And("^I should see the following title in the title input box$")
    public void iShouldSeeTheFollowingTitleInTheTitleInputBox(List<String> titleList) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_title_field");
        String actualTitle = findElement("todos.create_my_list_title_field").getAttribute("value").trim();
        System.out.println("Expected Title = " + titleList.get(0) + " Actual Title = " + actualTitle);
        Assert.assertEquals(titleList.get(0), actualTitle);
    }

    @And("^I should see the Delete Button on the New List view$")
    public void iShouldSeeTheDeleteButtonOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.delete_link_create_list");
        elementShouldBePresent("todos.delete_link_create_list");
        Assert.assertEquals("Delete", findElement("todos.delete_link_create_list").getText());
    }

    @And("^I should see the Cancel Button on the New List view$")
    public void iShouldSeeTheCancelButtonOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.cancel_button_create_list");
        elementShouldBePresent("todos.cancel_button_create_list");
        Assert.assertEquals("Cancel", findElement("todos.cancel_button_create_list").getText());
    }

    @And("^I should see the Save Button on the New List view$")
    public void iShouldSeeTheSaveButtonOnTheNewListView() throws Throwable {
        Wait.untilElementPresent("todos.save_button_create_list");
        elementShouldBePresent("todos.save_button_create_list");
        Assert.assertEquals("Save", findElement("todos.save_button_create_list").getText());
    }

    @When("^I enter a title \"([^\"]*)\" in the List Name input field on the New List view$")
    public void iEnterATitleInTheListNameInputFieldOnTheNewListView(String title) throws Throwable {
        Wait.untilElementPresent("todos.list_name_input_field");
        TextBoxes.typeTextbox("todos.list_name_input_field", title);
    }

    @Then("^I should see the title \"([^\"]*)\" in the List Name input field on the New List view$")
    public void iShouldSeeTheTitleInTheListNameInputFieldOnTheNewListView(String title) throws Throwable {
        Wait.untilElementPresent("todos.list_name_input_field");
        String listName = findElement("todos.list_name_input_field").getAttribute("value");
        System.out.println(listName);
        Assert.assertEquals(title, listName);
    }

    @When("^I enter a title \"([^\"]*)\" in the input field on CREATE LISTS page$")
    public void iEnterATitleInTheInputFieldOnCREATELISTSPage(String title) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_title_field");
        TextBoxes.typeTextbox("todos.create_my_list_title_field", title);
    }

    @Then("^I should see the title \"([^\"]*)\" in the input field on CREATE LISTS page$")
    public void iShouldSeeTheTitleInTheInputFieldOnCREATELISTSPage(String title) throws Throwable {
        Wait.untilElementPresent("todos.create_my_list_title_field");
        String listName = findElement("todos.create_my_list_title_field").getAttribute("value");
        System.out.println(listName);
        Assert.assertEquals(title, listName);
    }

    @When("^I select a filter criteria from New Accounts Opened w/Me$")
    public void iSelectAFilterCriteriaFromNewAccountsOpenedWMe(List<String> selectedOption) throws Throwable {
        OmniclientUtils.waitForAngularLoad();
        for (String option : selectedOption) {
            switch (option) {
                case "Last 30 Days":
                    Wait.untilElementPresent("todos.new_credit_acc_dropdown_create_list");
                    DropDowns.selectByText("todos.new_credit_acc_dropdown_create_list", "Last 30 Days");
                    break;

                case "Last 60 Days":
                    Wait.untilElementPresent("todos.new_credit_acc_dropdown_create_list");
                    DropDowns.selectByText("todos.new_credit_acc_dropdown_create_list", "Last 60 Days");
                    break;

                case "Last 90 Days":
                    Wait.untilElementPresent("todos.new_credit_acc_dropdown_create_list");
                    DropDowns.selectByText("todos.new_credit_acc_dropdown_create_list", "Last 90 Days");
                    break;

            }
        }
    }

    @Then("^the Quick List no results message is displayed$")
    public void theQuickListNoResultsMessageIsDisplayed() throws Throwable {
        Wait.untilElementPresent("todos.preview_pane_new_list");
        elementShouldBePresent("todos.preview_pane_new_list");
        String message = Elements.findElement("todos.preview_pane_new_list").getText();
        System.out.println(message);
        assertEquals("Sorry, there are no results that match this list.", message);
    }

    @Then("^the Wizard Screen no results message is displayed$")
    public void theWizardScreenNoResultsMessageIsDisplayed() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        OmniclientUtils.waitForAngularLoad();
        Wait.untilElementPresent("todos.quick_list_wizard_no_results_msg");
        elementShouldBePresent("todos.quick_list_wizard_no_results_msg");
        String message = Elements.findElement("todos.quick_list_wizard_no_results_msg").getText();
        System.out.println(message);
        assertEquals("Sorry, there are no results that match your selections.", message);
    }

    @Then("^I should see the following values in the My Top 25 Clients dropdown on the Create List dashboard$")
    public void iShouldSeeTheFollowingValuesInTheMyTop25ClientsDropdownOnTheCreateListDashboard(List<String> filterOpt) throws Throwable {
        WebElement topClientsSubtitle = findElement(By.xpath("//*[@id='options" + topClientsPosition + "_select']/option[1]"));
        elementShouldBePresent(topClientsSubtitle);
        String option = topClientsSubtitle.getText();
        System.out.println(option);
        Assert.assertTrue(option.contains(filterOpt.get(0)));
    }


    @And("^I should not see the Query column on the Create List dashboard$")
    public void iShouldNotSeeTheQueryColumnOnTheCreateListDashboard() throws Throwable {
        Wait.untilElementNotPresent("homepage.loading_page");
        Wait.untilElementPresent("todos.all_columns_title_header");
        String sectionHeader = findElement("todos.all_columns_title_header").getText();
        System.out.println(sectionHeader);
        if (sectionHeader.contains("$/Query")) {
            Assert.fail("Query $ header is displayed and it should not be");
        }


    }

}

