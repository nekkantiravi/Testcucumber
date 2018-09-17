package com.macys.sdt.projects.Marketing.CustomerPreferences.steps.mew.bcom;

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

public class CustomerPref extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CustomerPreference.class);
    String currentEmail = TestUsers.currentEmail;

    @When("^I navigate to My Preferences page$")
    public void I_Navigate_To_My_Preferences_Page(){
        Navigate.browserBack();
        Wait.secondsUntilElementPresent("customer_pref.my_preferences_option", 50);
        Clicks.click("customer_pref.my_preferences_option");
        logger.info("Clicked on My Preferences on the My Account page");
    }

    @Then("^I should see \"([^\"]*)\" page title on the page$")
    public void I_Should_See_Page_Title_On_The_Page(String PageTitle){
        Wait.secondsUntilElementPresent("customer_pref.my_preferences_title", 50);
        Assert.assertTrue("Page title of My Preferences landing page is not displayed", Elements.getText("customer_pref.my_preferences_title").contains(PageTitle));
        logger.info("Page title got displayed on the My Preferences landing page");
    }

    @And("^I should see \"([^\"]*)\" card on the page$")
    public void I_Should_See_Card_On_The_Page(String CardText){
        Wait.secondsUntilElementPresent("customer_pref.communication_preferences_card", 50);
        Assert.assertTrue("Communication preferences card is not displayed", Elements.getText("customer_pref.communication_preferences_card").contains(CardText));
        logger.info("Communication Preferences card is displayed");
    }

    @And("^I should see the \"([^\"]*)\" card on the page$")
    public void I_Should_See_The_Preferred_Store_Card_On_The_Page(String CardText){
        Wait.secondsUntilElementPresent("customer_pref.preferred_store_card", 50);
        Assert.assertTrue("Preferred Store card is not displayed", Elements.getText("customer_pref.preferred_store_card").contains(CardText));
        logger.info("Preferred Store card is displayed");
    }


    @And("^I select Enabled text for Email preference on the page$")
    public void I_Select_Enabled_Text_For_Email_Preference_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_pref.my_preferences_title", 50);
        Clicks.click("customer_pref.email_flag");
        logger.info("Selected Enabled text of Email Preference");
    }

    @Then("^I should get navigated to a page having \"([^\"]*)\" as page title$")
    public void I_Should_Get_Navigated_To_A_Page_Having_As_PageTitle(String PageTitle){
        Wait.secondsUntilElementPresent("customer_pref.communication_preferences_title", 50);
        Assert.assertTrue("Page title of Communication Preferences page is not displayed", Elements.getText("customer_pref.communication_preferences_title").contains(PageTitle));
        logger.info("Page title of Communication Preferences page is displayed");
    }

    @When("^I click on 'Edit' option of Email Preferences$")
    public void I_Click_On_Edit_Option_Of_Email_Preferences(){
        Clicks.click("customer_pref.email_edit_option");
        logger.info("Clicked on the Edit option of Email Preference");
    }

    @Then("^I should see the Email section in edit state on the page$")
    public void I_Should_See_The_Email_Section_In_Edit_State_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_pref.email_edit_in_profile_option", 50);
        Assert.assertTrue("Edit in profile option is not displayed", Elements.elementPresent("customer_pref.email_edit_in_profile_option"));
        logger.info("Email section is displayed in edit state");
    }

    @When("^I select 'Maybe later No thanks' radio option in the edit state of Email section$")
    public void I_Select_May_be_Later_No_Thanks_Radio_Option_In_The_Edit_State_Of_Email_Section(){
        Clicks.click("customer_pref.email_radio_button_option_two");
        logger.info("Selected Maybe later no thanks radio option");
    }

    @And("^I click on 'Save' button in the edit state of Email Section$")
    public void I_Click_On_Save_Button_In_The_Edit_State_Of_Email_Section() {
        Clicks.click("customer_pref.email_save_button_edit_state");
        logger.info("Clicked on the save button");
    }

    @Then("^I should see \"([^\"]*)\" status for the Email Preference on the page$")
    public void I_Should_See_Status_For_The_Email_Preference_On_The_Page(String Status)  {
        Wait.secondsUntilElementPresent("customer_pref.email_flag", 50);
        Assert.assertTrue("Email status is not displayed correctly", Elements.getText("customer_pref.email_flag").contains(Status));
        logger.info("Email status is displayed correctly");
    }

    @And("^I select Enabled text for Text preference on the page$")
    public void I_Select_Enabled_Text_For_Text_Preference_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_pref.my_preferences_title", 50);
        Clicks.click("customer_pref.text_flag");
        logger.info("Enabled text is displayed for Text Preference");
    }

    @When("^I click on 'Edit' option of Text Preference$")
    public void I_Click_On_Edit_Option_Of_Text_Preference()  {
        Clicks.click("customer_pref.text_edit_option");
        logger.info("Clicked on the Edit option of Text Preference");
    }

    @Then("^I should see the Text section in edit state on the page$")
    public void I_Should_See_The_Text_Section_In_Edit_State_On_The_Page() {
        Wait.secondsUntilElementPresent("customer_pref.text_save_button_edit_state", 50);
        Assert.assertTrue("Save button is not displayed", Elements.elementPresent("customer_pref.text_save_button_edit_state"));
        logger.info("Text section is displayed  in edit state");
    }

    @When("^I select 'I would prefer not' radio option in the edit state of Text section$")
    public void I_Select_I_Would_Prefer_Not_Radio_Option_In_The_Edit_State_Of_Text_Section() {
        Clicks.click("customer_pref.text_radio_button_option_two");
        logger.info("Selected I'd prefer not to receive texts at this time radio option in text section");
    }

    @And("^I click on 'Cancel' button in the edit state of Text Section$")
    public void I_Click_On_Cancel_Button_In_The_Edit_State_Of_Text_Section()  {
        Clicks.click("customer_pref.text_cancel_button_edit_state");
        logger.info("Clicked on Cancel button");
    }

    @Then("^I should see \"([^\"]*)\" status for the Text Preference on the page$")
    public void I_Should_See_Disabled_Status_For_The_Text_Preference_On_The_Page(String Status)  {
        Wait.secondsUntilElementPresent("customer_pref.text_flag_disabled", 50);
        Assert.assertTrue("Text status is not displayed correctly", Elements.getText("customer_pref.text_flag_disabled").contains(Status));
        logger.info("Text status is displayed correctly");
    }

    @When("^I click on 'Add a Store' option in the Preferred Store section$")
    public void I_Click_On_Add_A_Store_Option_In_The_Preferred_Store_Section(){
        Wait.secondsUntilElementPresent("customer_pref.my_preferences_title", 50);
        Clicks.click("customer_pref.add_a_store_option");
        logger.info("Clicked on Add a Store option");
    }

    @Then("^I should be navigated to the mobile view of Preferred Store page$")
    public void I_Should_Be_Navigated_To_The_Mobile_View_Of_Preferred_Store_Page() {
        Wait.secondsUntilElementPresent("customer_pref.preferred_store_title", 50);
        Assert.assertTrue("Preferred Store page is not displayed", Elements.elementPresent("customer_pref.preferred_store_title"));
        logger.info("Navigated to Preferred Store page");
    }

    @When("^I enter the ZIP Code into the text field on the page$")
    public void I_Enter_The_ZIP_Code_Into_The_Text_Field_On_The_Page() {
        TextBoxes.typeTextbox("customer_pref.zip_code_text_field", "22102");
        logger.info("Entered ZIP Code into the text field");
    }

    @And("^I click on 'Search' button on the page$")
    public void I_Click_On_Search_Button_On_The_Page()  {
        Clicks.click("customer_pref.search_button_preferred_store");
        logger.info("Clicked on Search Button");
    }

    @And("^I select any store from the displayed search results$")
    public void I_Select_Any_Store_From_The_Displayed_Search_Results() {
        Wait.untilElementPresent(Elements.element("customer_pref.preferred_store_first_button"));
        Wait.secondsUntilElementPresent("customer_pref.preferred_store_first_button", 50);
        Clicks.click("customer_pref.preferred_store_first_button");
        logger.info("Selected a store from the Search Results");
    }

    @Then("^I should be navigated to the summary page of Preferred store$")
    public void I_Should_Be_Navigated_To_The_Summary_Page_Of_Preferred_Store(){
        Wait.secondsUntilElementPresent("customer_pref.store_summary_change_store_option", 50);
        Assert.assertTrue("Summary state of Preferred Store page is not displayed", Elements.elementPresent("customer_pref.store_summary_change_store_option"));
        logger.info("Navigated to Summary state of Preferred store");
    }

    @When("^I click on 'Change Store' option in the summary page$")
    public void I_Click_On_Change_Store_Option_In_The_Summary_Page()  {
        Clicks.click("customer_pref.store_summary_change_store_option");
        logger.info("Clicked on Change Store option in the summary state");
    }

    @Then("^I should navigate to the edit state of Preferred store page$")
    public void I_Should_Navigate_To_The_Edit_State_Of_Preferred_Store_Page() {
        Wait.secondsUntilElementPresent("customer_pref.store_edit_selected_store_text", 50);
        Assert.assertTrue("Edit state of Preferred store page is not displayed", Elements.elementPresent("customer_pref.store_edit_selected_store_text"));
        logger.info("Navigated to the edit state of Preferred store");
    }
}
