package com.macys.sdt.projects.Marketing.CustomerPreferences.steps.mew.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.CustomerPreferences.steps.website.mcom.CustomerPreference;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerPrefs extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CustomerPreference.class);
    String currentEmail = TestUsers.currentEmail;

    @When("^I click on View Preference option in my account page$")
    public void I_Click_On_View_Preference_Option_In_My_Account_page() {
        Navigate.browserBack();
        Wait.secondsUntilElementPresent("customer_prefs.preferences_card_view_preferences", 50);
        Clicks.click("customer_prefs.preferences_card_view_preferences");
        logger.info("Clicked on the View Preference option");
    }


    @Then("^I should navigate to Preferences landing mobile page$")
    public void I_Should_Navigate_To_Preferences_Landing_Mobile_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.preferences_landing_page_heading", 50);
        Assert.assertTrue("Preferences landing page is not displayed", Elements.elementPresent("customer_prefs.preferences_landing_page_heading"));
        logger.info("Navigated to the Preferences landing page");
}

    @When("^I navigate to Categories page from Landing page$")
    public void I_Navigate_To_Categories_Page_From_Landing_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.goto_category_view", 50);
        Clicks.click("customer_prefs.goto_category_view");
        logger.info("Navigated to Category Preferences page");
}

    @Then("^I should see the Add state of Categories page$")
    public void I_Should_See_The_Add_State_Of_Categories_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.categories_add_state_subheading", 50);
        Assert.assertTrue("Add state is not displayed", Elements.elementPresent("customer_prefs.categories_add_state_subheading"));
        logger.info("Add state of Category Preferences page got displayed");
    }

    @When("^I select 'Select All' checkbox option in the add state$")
    public void I_Select_Select_All_Checkbox_Option_In_The_Add_State()  {
        Clicks.click("customer_prefs.categories_add_state_checkbox");
        logger.info("Selected 'Select All' checkbox option in the Add state of Category Preferences page");
    }

    @And("^I click on 'Save' button on the page$")
    public void I_Click_On_Save_Button_On_The_Page()  {
        Clicks.click("customer_prefs.categories_add_state_save_button");
        logger.info("Clicked on Save Button");
    }

    @Then("^I should see the Summary state of Categories page$")
    public void I_Should_See_The_Summary_State_Of_Categories_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.categories_summary_state_counter", 50);
        Assert.assertTrue("Summary state is not displayed", Elements.elementPresent("customer_prefs.categories_summary_state_counter"));
        logger.info("Summary state of Category Preferences page got displayed");
    }

    @When("^I click on 'Edit' option in Summary state of Categories$")
    public void I_Click_On_Edit_Option_In_Summary_State_Of_Categories() {
        Clicks.click("customer_prefs.categories_summary_state_edit_option");
        logger.info("Clicked on the Edit option");
    }

    @Then("^I should see the Edit state of Categories page$")
    public void I_Should_See_The_Edit_State_Of_Categories_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.categories_edit_state_subheading", 50);
        Assert.assertTrue("Edit state of Categories is not displayed", Elements.elementPresent("customer_prefs.categories_edit_state_subheading"));
        logger.info("Edit state of Categories page got displayed");
    }

    @And("^I click on 'Brands' tab option on the page$")
    public void I_Click_On_Brands_Tab_Option_On_The_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.brands_tab_heading", 50);
        Clicks.click("customer_prefs.brands_tab_heading");
        logger.info("Clicked on Brands tab option");
    }

    @Then("^I should see the \"([^\"]*)\" Brands sub heading on the page$")
    public void I_Should_See_The_Brands_Sub_Heading_On_The_Page(String SubHeading) {
        Wait.secondsUntilElementPresent("customer_prefs.brands_add_brands_title", 50);
        Assert.assertTrue("Add State of Brands is not displayed", Elements.getText("customer_prefs.brands_add_brands_title").contains(SubHeading));
        logger.info("Add state of Brands is displayed");
    }

    @When("^I select any Brands tile on the page$")
    public void I_Select_Any_Brands_Tile_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.brands_add_brands_first_brand_tile", 50);
        Clicks.click("customer_prefs.brands_add_brands_first_brand_tile");
        logger.info("Selected a Brand tile");
    }

    @And("^I click on 'Save' button in add state$")
    public void I_Click_On_Save_Button_In_Add_State() {
        Clicks.click("customer_prefs.brands_add_state_save_button");
        logger.info("Clicked on Save Button");
    }

    @Then("^I should see the Summary state of Brands page$")
    public void I_Should_See_The_Summary_State_Of_Brands_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.brands_summary_brands_edit_option", 50);
        Assert.assertTrue("Summary state of Brands is not displayed", Elements.elementPresent("customer_prefs.brands_summary_brands_edit_option"));
        logger.info("Summary state of Brands page got displayed");
    }

    @When("^I click on 'Edit' option in summary state of brands page$")
    public void I_Click_On_Edit_Option_In_Summary_State_Of_Brands_Page()  {
        Clicks.click("customer_prefs.brands_summary_brands_edit_option");
        logger.info("Clicked on the Edit option in the summary state of Brands");
    }

    @Then("^I should see the Edit state of Brands page$")
    public void I_Should_See_The_Edit_State_Of_Brands_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.brands_edit_sub_heading", 50);
        Assert.assertTrue("Edit state of Brands is not displayed", Elements.elementPresent("customer_prefs.brands_edit_sub_heading"));
        logger.info("Edit state of Brands page is displayed");
    }

    @And("^I click on 'Sizes' tab option on the page$")
    public void I_Click_On_Sizes_Tab_Option_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.sizes_tab_heading", 50);
        Clicks.click("customer_prefs.sizes_tab_heading");
        logger.info("Clicked on the 'Sizes' tab option");
    }

    @Then("^I should see the Sizes sub heading \"([^\"]*)\" on the page$")
    public void I_Should_See_The_Sizes_Sub_Heading_On_The_Page(String SubHeading) {
        Wait.secondsUntilElementPresent("customer_prefs.sizes_add_sizes_title", 50);
        Assert.assertTrue("Add State of Sizes is not displayed", Elements.getText("customer_prefs.sizes_add_sizes_title").contains(SubHeading));
        logger.info("Add state of Sizes page is displayed");
    }

    @When("^I click on first accordion$")
    public void I_Click_On_First_Accordion() {
        Clicks.click("customer_prefs.sizes_add_sizes_first_accordion");
        logger.info("Clicked on the Accordion");
    }

    @And("^I select any Size tile in the expanded accordion$")
    public void I_Select_Any_Size_Tile_In_The_Expanded_Accordion() {
        Wait.secondsUntilElementPresent("customer_prefs.sizes_add_sizes_first_size_tile", 50);
        Clicks.click("customer_prefs.sizes_add_sizes_first_size_tile");
        logger.info("Selected a size tile");
    }

    @And("^I click on 'Save' button in add state of Sizes page$")
    public void I_Click_On_Save_Button_In_Add_State_Of_Sizes_Page() {
        Clicks.click("customer_prefs.sizes_add_state_save_button");
        logger.info("Clicked on Save Button");
    }

    @Then("^I should see the Summary state of Sizes page$")
    public void I_Should_See_The_Summary_State_Of_Sizes_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.sizes_summary_sizes_edit_option", 50);
        Assert.assertTrue("Summary state of Sizes is not displayed", Elements.elementPresent("customer_prefs.sizes_summary_sizes_edit_option"));
        logger.info("Summary state of Sizes page is displayed");
    }

    @When("^I click on 'Edit' option in summary state of sizes$")
    public void I_Click_On_Edit_Option_In_Summary_State_Of_Sizes() {
        Clicks.click("customer_prefs.sizes_summary_sizes_edit_option");
        logger.info("Clicked on the Edit option");
    }

    @Then("^I should see the Edit state of Sizes page$")
    public void I_Should_See_The_Edit_State_Of_Sizes_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.sizes_edit_sizes_title", 50);
        Assert.assertTrue("Edit Sizes state is not displayed", Elements.elementPresent("customer_prefs.sizes_edit_sizes_title"));
        logger.info("Edit state of Sizes page is displayed");
    }

    @When("^I navigate to Notifications preferences sub page from landing page$")
    public void I_Navigate_To_Notifications_Preferences_Sub_Page_From_Landing_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.goto_notifications_view", 50);
        Clicks.click("customer_prefs.goto_notifications_view");
        logger.info("Navigated to Notification preferences sub page");
    }

    @And("^I click on 'EDIT' link next to email icon on the page$")
    public void I_Click_On_EDIT_Link_Next_To_Email_Icon_On_The_Page(){
        Wait.secondsUntilElementPresent("customer_prefs.email_edit", 50);
        Clicks.click("customer_prefs.email_edit");
        logger.info("Clicked on the edit option of Email Preference");
    }

    @And("^I select 'Sure, but I only want the highlights' radio option in edit state$")
    public void I_Select_Sure_But_I_Only_Want_The_Highlights_Radio_Option_In_Edit_State()  {
        Wait.secondsUntilElementPresent("customer_prefs.email_edit_second_radio_button", 50);
        Clicks.click("customer_prefs.email_edit_second_radio_button");
        logger.info("Selected 'Sure, but I only want the highlights' radio option");
    }

    @And("^I click on 'Save' button in edit state of Email$")
    public void I_Click_On_Save_Button_In_Edit_State_Of_Email() {
        Wait.secondsUntilElementPresent("customer_prefs.email_edit_save_button", 50);
        Clicks.click("customer_prefs.email_edit_save_button");
        logger.info("Clicked on the Save button in the edit state of Email Preference");
    }

    @Then("^I should see the \"([^\"]*)\" text in the summary state of Email$")
    public void I_Should_See_The_Enabled_Text_In_The_Summary_State_Of_Email(String Text) {
        Wait.secondsUntilElementPresent("customer_prefs.enabled_text", 50);
        Assert.assertTrue("Email is in disabled state", Elements.elementPresent("customer_prefs.enabled_text"));
        logger.info("Enabled state is displayed in Email section");
    }

    @And("^I click on edit link next to text preference on the page$")
    public void I_Click_On_Edit_Link_Next_To_Text_Preference_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_prefs.text_edit", 50);
        Clicks.click("customer_prefs.text_edit");
        logger.info("Clicked on the Edit option of Notification preference");
    }

    @Then("^I should see the Text Preference section in Edit state$")
    public void I_Should_See_The_Text_Preference_Section_In_Edit_State() {
        Wait.secondsUntilElementPresent("customer_prefs.text_edit_mobile_number", 50);
        Assert.assertTrue("Mobile number is not displayed in edit state of Text Preference", Elements.elementPresent("customer_prefs.text_edit_mobile_number"));
        logger.info("Mobile number is displayed in edit state of Text Preference");
    }

    @When("^I navigate to Preferred store page from the landing page$")
    public void I_Navigate_To_Preferred_Store_Page_From_The_Landing_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.goto_prefered_store_view", 50);
        Clicks.click("customer_prefs.goto_prefered_store_view");
        logger.info("Navigated to Preferred store sub page");
    }

    @And("^I enter the ZIP Code into the field on the Preferred store page$")
    public void I_Enter_The_ZIP_Code_Into_The_Field_On_The_Preferred_Store_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.zip_code_text_field", 50);
        TextBoxes.typeTextbox("customer_prefs.zip_code_text_field", "22102");
        logger.info("Entered ZIP Code into the text field");
    }

    @And("^I click the 'Search' button on the page$")
    public void I_Click_The_Search_Button_On_The_Page() {
        Clicks.click("customer_prefs.search_button_preferred_store");
        logger.info("Clicked on Search Button");
    }

    @And("^I select any store from the search results on the page$")
    public void I_Select_Any_Store_From_The_Search_Results_On_The_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.preferred_store_first_button", 50);
        Clicks.click("customer_prefs.preferred_store_first_button");
        logger.info("Selected a store from the Search Results");
    }

    @Then("^I should navigate to the summary state of Preferred store page$")
    public void I_Should_Navigate_To_The_Summary_State_Of_Preferred_Store_Page() {
        Wait.untilElementPresent(Elements.element("customer_prefs.preferred_store_edit_option"));
        Assert.assertTrue("Summary state of Preferred store is not displayed", Elements.elementPresent("customer_prefs.preferred_store_edit_option"));
        logger.info("Navigated to the summary state of Preferred store");
    }

    @Then("^I should navigate the edit state of the Preferred store page$")
    public void I_Should_Navigate_The_Edit_State_Of_The_Preferred_Store_Page()  {
        Wait.secondsUntilElementPresent("customer_prefs.preferred_store_my_store_button", 50);
        Assert.assertTrue("Edit state of Preferred Store page is not displayed", Elements.elementPresent("customer_prefs.preferred_store_my_store_button"));
        logger.info("Navigated to edit state of Preferred store page");
    }

    @When("^I click on the Edit option on the summary state of Preferred store page$")
    public void I_Click_On_The_Edit_Option_On_The_Summary_State_Of_Preferred_Store_Page()  {
        Wait.untilElementPresent(Elements.element("customer_prefs.preferred_store_edit_option"));
        Clicks.click("customer_prefs.preferred_store_edit_option");
        logger.info("Clicked on the Edit option");
    }
}
