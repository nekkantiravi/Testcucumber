package com.macys.sdt.projects.Marketing.CustomerPreferences.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.CustomerPreferences.steps.website.mcom.CustomerPreference;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerPreferences extends StepUtils{

    private static final Logger logger = LoggerFactory.getLogger(CustomerPreference.class);
    String currentEmail = TestUsers.currentEmail;

    @Then("^I should see \"([^\"]*)\" page title$")
    public void I_Should_See_Page_Title(String PageTitle)  {
        Wait.secondsUntilElementPresent("customer_preference.my_preferences_title", 50);
        Assert.assertTrue("Page title of My Preferences landing page is not displayed", Elements.getText("customer_preference.my_preferences_title").contains(PageTitle));
        logger.info("Page title got displayed on the My Preferences landing page");
    }

    @And("^I should see \"([^\"]*)\" card$")
    public void I_Should_See_Communication_Preferences_Card(String CardText)  {
        Wait.secondsUntilElementPresent("customer_preference.communication_preferences_card", 50);
        Assert.assertTrue("Communication preferences card is not displayed", Elements.getText("customer_preference.communication_preferences_card").contains(CardText));
        logger.info("Communication Preferences card is displayed");
    }

    @And("^I should see the \"([^\"]*)\" card$")
    public void I_Should_See_The_Preferred_Store_Card(String CardText)  {
        Wait.secondsUntilElementPresent("customer_preference.preferred_store_card", 50);
        Assert.assertTrue("Preferred Store card is not displayed", Elements.getText("customer_preference.preferred_store_card").contains(CardText));
        logger.info("Preferred Store card is displayed");
    }

    @And("^I should see \"([^\"]*)\" label on the Communication Preferences card$")
    public void I_Should_See_Email_Label_On_The_Communication_Preferences_Card(String LabelText) throws Throwable {
        Assert.assertTrue("Email label is not displayed on the Communication card ", Elements.getText("customer_preference.communication_preferences_email_label").contains(LabelText));
        logger.info("Email label is displayed on the communication card");
    }

    @And("^I should see the \"([^\"]*)\" label on the Communication Preferences card$")
    public void I_Should_See_The_Text_Label_On_The_Communication_Preferences_Card(String LabelText) {
        Assert.assertTrue("Text label is not displayed on the Communication card ", Elements.getText("customer_preference.communication_preferences_text_label").contains(LabelText));
        logger.info("Text label is displayed on the communication card");
    }

    @And("^I should see \"([^\"]*)\" status for the Email Preference$")
    public void I_Should_See_Enabled_Status_For_The_Email_Preference(String Status) {
        Wait.secondsUntilElementPresent("customer_preference.email_flag", 50);
        Assert.assertTrue("Email status is not displayed correctly", Elements.getText("customer_preference.email_flag").contains(Status));
        logger.info("Email status is displayed correctly");
    }

    @And("^I should see the \"([^\"]*)\" status for the Text Preference$")
    public void I_Should_See_The_Disabled_Status_For_The_Text_Preference(String Status) {
        Assert.assertTrue("Text status is not displayed correctly", Elements.getText("customer_preference.text_flag").contains(Status));
        logger.info("Text status is displayed correctly");
    }

    @And("^I should see the 'Add a Store' option$")
    public void I_Should_See_The_Add_A_Store_Option()  {
        Assert.assertTrue("Add a Store option is not displayed", Elements.elementPresent("customer_preference.add_a_store_option"));
        logger.info("Add a Store option is displayed");
    }

    @When("^I click on 'My Preferences' option from the left navigation menu$")
    public void I_Click_On_My_Preferences_Option_From_The_Left_Navigation_Menu()  {
        Wait.secondsUntilElementPresent("customer_preference.left_nav_preferences", 50);
        Clicks.click("customer_preference.left_nav_preferences");
        logger.info("Clicked on the My Preference option form the left navigation menu");
    }

    @When("^I select Enabled text for Email preference$")
    public void I_Select_Enabled_Text_For_Email_Preference() {
        Wait.secondsUntilElementPresent("customer_preference.my_preferences_title", 50);
        Clicks.click("customer_preference.email_flag");
        logger.info("Selected Enabled text of Email Preference");
    }

    @Then("^I should get navigated to a page having \"([^\"]*)\" page title$")
    public void I_Should_Get_Navigated_To_A_Page_Having_Page_Title(String PageTitle)  {
        Wait.secondsUntilElementPresent("customer_preference.communication_preferences_title", 50);
        Assert.assertTrue("Page title of Communication Preferences page is not displayed", Elements.getText("customer_preference.communication_preferences_title").contains(PageTitle));
        logger.info("Page title of Communication Preferences page is displayed");
    }

    @And("^I should see \"([^\"]*)\" section$")
    public void I_Should_See_Email_Section(String EmailLabel) {
        Assert.assertTrue("Email section label is not displayed", Elements.getText("customer_preference.email_section_label").contains(EmailLabel));
        logger.info("Email section label is displayed");
    }

    @And("^I should see an Email address$")
    public void I_Should_See_An_Email_Address()  {
        Assert.assertTrue("Email address is not displayed", Elements.elementPresent("customer_preference.email_address"));
        logger.info("Email address is displayed");
    }

    @When("^I click on 'Edit' option for Email$")
    public void I_Click_On_Edit_Option_For_Email() {
        Clicks.click("customer_preference.email_edit_option");
        logger.info("Clicked on the Edit option of Email Preference");
    }

    @Then("^I should see the Email section in edit state$")
    public void I_Should_See_The_Email_Section_In_Edit_State() {
        Wait.secondsUntilElementPresent("customer_preference.email_edit_in_profile_option", 50);
        Assert.assertTrue("Edit in profile option is not displayed", Elements.elementPresent("customer_preference.email_edit_in_profile_option"));
        logger.info("Email section is displayed in edit state");
    }

    @And("^I should see the two radial buttons$")
    public void I_Should_See_The_Two_Radial_Buttons(List<String> RadialButtons) {
        pausePageHangWatchDog();
        Assert.assertTrue("First radio option is  not displayed", Elements.getText("customer_preference.email_radio_option_one_text_label").contains(RadialButtons.get(0)));
        Assert.assertTrue("Second radio option is  not displayed", Elements.getText("customer_preference.email_radio_option_two_text_label").contains(RadialButtons.get(1)));
        resumePageHangWatchDog();
        logger.info("Radio options of the Email section are displayed");
    }

    @And("^I should see the 'Save' and 'Cancel' button in Email section$")
    public void I_Should_See_The_Save_And_Cancel_Button_In_Email_Section() {
        Assert.assertTrue("Save button is not displayed", Elements.elementPresent("customer_preference.email_save_button_edit_state"));
        Assert.assertTrue("Cancel button is not displayed", Elements.elementPresent("customer_preference.email_cancel_button_edit_state"));
        logger.info("Save and Cancel buttons on the Email section are displayed");
    }

    @When("^I select 'Maybe later No thanks' radio option in edit state$")
    public void I_Select_May_be_Later_No_Thanks_Radio_Option_In_Edit_State()  {
        Clicks.click("customer_preference.email_radio_button_option_two");
        logger.info("Selected Maybe later no thanks radio option");
    }

    @And("^I click on 'Save' button in edit state of Email Preference$")
    public void I_Click_On_Save_Button_In_Edit_State_Of_Email_Preference()  {
        Clicks.click("customer_preference.email_save_button_edit_state");
        logger.info("Clicked on the save button");
    }

    @When("^I select Enabled text for Text preference$")
    public void I_Select_Enabled_Text_For_Text_Preference() {
        Wait.secondsUntilElementPresent("customer_preference.my_preferences_title", 50);
        Clicks.click("customer_preference.text_flag");
        logger.info("Enabled text is displayed for Text Preference");
    }

    @And("^I should see \"([^\"]*)\" section in edit state$")
    public void I_Should_See_Text_Section_In_Edit_State(String TextLabel) {
        Assert.assertTrue("Text section label is not displayed", Elements.getText("customer_preference.text_section_label").contains(TextLabel));
        logger.info("Text section lable is displayed");
    }

    @And("^I should see \"([^\"]*)\" status for the Text Preference$")
    public void I_Should_See_Disabled_Status_For_The_Text_Preference(String Status) {
        Wait.secondsUntilElementPresent("customer_preference.text_flag", 50);
        Assert.assertTrue("Text status is not displayed correctly", Elements.getText("customer_preference.text_flag_disabled").contains(Status));
        logger.info("Text status is displayed correctly");
    }

    @When("^I click on 'Edit' option for Text Preference$")
    public void I_Click_On_Edit_Option_For_Text_Preference()  {
        Clicks.click("customer_preference.text_edit_option");
        logger.info("Clicked on the Edit option of Text Preference");
    }

    @Then("^I should see the Text section in edit state$")
    public void I_Should_See_The_Text_Section_In_Edit_State() {
        Wait.secondsUntilElementPresent("customer_preference.text_save_button_edit_state", 50);
        Assert.assertTrue("Save button is not displayed", Elements.elementPresent("customer_preference.text_save_button_edit_state"));
        logger.info("Text section is displayed  in edit state");
    }

    @And("^I should see the two radial buttons in edit state of text preference$")
    public void I_Should_See_The_Two_Radial_Buttons_In_Edit_State_Of_Text_Preference(List<String> RadialButtons)  {
        pausePageHangWatchDog();
        Assert.assertTrue("First radio option is  not displayed", Elements.getText("customer_preference.text_radio_option_one_text_label").contains(RadialButtons.get(0)));
        Assert.assertTrue("Second radio option is  not displayed", Elements.getText("customer_preference.text_radio_option_two_text_label").contains(RadialButtons.get(1)));
        resumePageHangWatchDog();
        logger.info("Radio options in the text section are displayed");
    }

    @And("^I should see the 'Save' and 'Cancel' button in Text section$")
    public void I_Should_See_The_Save_And_Cancel_Button_In_Text_Section() {
        Assert.assertTrue("Save button is not displayed", Elements.elementPresent("customer_preference.text_save_button_edit_state"));
        Assert.assertTrue("Cancel button is not displayed", Elements.elementPresent("customer_preference.text_cancel_button_edit_state"));
        logger.info("Save and Cancel buttons in Text section are displayed");
    }

    @When("^I select 'I would prefer not' radio option in text edit state$")
    public void I_Select_I_would_prefer_not_Radio_Option_In_Text_Edit_State()  {
        Clicks.click("customer_preference.text_radio_button_option_two");
        logger.info("Selected I'd prefer not to receive texts at this time radio option in text section");
    }

    @And("^I provide mobile number into the Cell phone number text field$")
    public void I_Provide_Mobile_Number_Into_The_Cell_Phone_Number_Text_Field()  {
        TextBoxes.typeTextbox("customer_preference.cell_phone_number_text_field", "3434343434");
        logger.info("Provided mobile number into the cell phone number text field");
    }

    @And("^I click on 'Save' button in edit state of Text Preference$")
    public void I_Click_On_Save_Button_In_Edit_State_Of_Text_Preference()  {
        Clicks.click("customer_preference.text_save_button_edit_state");
        logger.info("Clicked on Save button");
    }

    @And("^I should see the mobile number in the text preference section$")
    public void I_Should_See_The_Mobile_Number_In_The_Text_Preference_Section(){
        Assert.assertTrue("Mobile number is not displayed", Elements.elementPresent("customer_preference.mobile_number_text_notification"));
        logger.info("Mobile number is displayed in the text preference section");
    }

    @When("^I click on 'Add a Store' option of Preferred Store$")
    public void I_Click_On_Add_A_Store_Option_Of_Preferred_Store()  {
        Wait.secondsUntilElementPresent("customer_preference.my_preferences_title", 50);
        Clicks.click("customer_preference.add_a_store_option");
        logger.info("Clicked on Add a Store option");
    }


    @Then("^I should be navigated to the Preferred store page$")
    public void I_Should_Be_Navigated_To_The_Preferred_Store_Page()  {
        Wait.secondsUntilElementPresent("customer_preference.preferred_store_title", 50);
        Assert.assertTrue("Preferred Store page is not displayed", Elements.elementPresent("customer_preference.preferred_store_title"));
        logger.info("Navigated to Preferred Store page");
    }

    @And("^I should see the ZIP code text box field$")
    public void I_Should_See_The_ZIP_Code_Text_Box_Field() {
        Assert.assertTrue("ZIP Code text field is not displayed", Elements.elementPresent("customer_preference.zip_code_text_field"));
        logger.info("ZIP Code text box field is displayed");
    }

    @And("^I should see 'Search' button$")
    public void I_Should_See_Search_Button()  {
        Assert.assertTrue("Search button is not displayed", Elements.elementPresent("customer_preference.search_button_preferred_store"));
        logger.info("Search button is displayed");
    }

    @When("^I enter the ZIP Code into the text field$")
    public void I_Enter_The_ZIP_Code_Into_The_Text_Field() {
        TextBoxes.typeTextbox("customer_preference.zip_code_text_field", "22102");
        logger.info("Entered ZIP Code into the text field");
    }

    @And("^I click on 'Search' button$")
    public void I_Click_On_Search_Button()  {
        Clicks.click("customer_preference.search_button_preferred_store");
        logger.info("Clicked on Search Button");
    }

    @And("^I select any store from the search results of Preferred store$")
    public void I_Select_Any_Store_From_The_Search_Results_Of_Preferred_Store()  {
        Wait.secondsUntilElementPresent("customer_preference.store_results_first_store", 50);
        Clicks.click("customer_preference.store_results_first_store");
        logger.info("Selected a store from the search results of Preferred store");
    }

    @Then("^I should be navigated to the summary state of Preferred store$")
    public void I_Should_Be_Navigated_To_The_Summary_State_Of_Preferred_Store(){
        Wait.secondsUntilElementPresent("customer_preference.store_summary_change_store_option", 50);
        Assert.assertTrue("Summary state of Preferred Store page is not displayed", Elements.elementPresent("customer_preference.store_summary_change_store_option"));
        logger.info("Navigated to Summary state of Preferred store");
    }

    @And("^I should see the 'Change Store' option on the summary state of Preferred store$")
    public void I_Should_See_The_Change_Store_Option_On_The_Summary_State_Of_Preferred_Store() {
        Assert.assertTrue("Summary state of Preferred Store page is not displayed", Elements.elementPresent("customer_preference.store_summary_change_store_option"));
        logger.info("Change store option is displayed on the summary state of Preferred store");
    }

    @When("^I click on 'Change Store' option in the summary state$")
    public void I_Click_On_Change_Store_Option_In_The_SummaryState() {
        Clicks.click("customer_preference.store_summary_change_store_option");
        logger.info("Clicked on Change Store option in the summary state");
    }

    @Then("^I should navigate to edit state of Preferred store page$")
    public void I_Should_Navigate_To_Edit_State_Of_Preferred_Store_Page()  {
        Wait.secondsUntilElementPresent("customer_preference.store_edit_selected_store_text", 50);
        Assert.assertTrue("Edit state of Preferred Store page is not displayed", Elements.elementPresent("customer_preference.store_edit_selected_store_text"));
        logger.info("Navigated to edit state of Preferred store page");
    }

    @And("^I should see the 'Selected' store$")
    public void I_Should_See_The_Selected_Store() {
        Assert.assertTrue("Edit state of Preferred Store page is not displayed", Elements.elementPresent("customer_preference.store_edit_selected_store_text"));
        logger.info("Selected store is displayed");
    }
       /* BCOM Preferences card Step defs*/

    @And("^I should see \"([^\"]*)\" label on the Preferences card$")
    public void iShouldSeeLabelOnThePreferencesCard(String EmailLabel) throws Throwable {
        Assert.assertTrue("Email label is not displayed on preferences card ", Elements.getText("my_account.email_label").contains(EmailLabel));
        logger.info("Email label is displayed on the preferences card");
    }


    @And("^I should see \"([^\"]*)\" label on Preferences card$")
    public void iShouldSeeLabelOnPreferencesCard(String TextLabel) throws Throwable {
        Assert.assertTrue("Text label is not displayed on preferences card ", Elements.getText("my_account.text_label").contains(TextLabel));
        logger.info("Text label is displayed on the preferences card");
    }

    @And("^I should see the Email status as \"([^\"]*)\" Preferences card$")
    public void iShouldSeeTheEmailStatusAsPreferencesCard(String preferencesStatus) throws Throwable {
            Wait.secondsUntilElementPresent("customer_preference.email_flag", 50);
            Assert.assertTrue("Email status is not displayed correctly", Elements.getText("my_account.email_flag").contains(preferencesStatus));
            logger.info("Email status is displayed correctly");
        }

    @And("^I should see the Text status as \"([^\"]*)\" Preferences card$")
    public void iShouldSeeTheTextStatusAsPreferencesCard(String preferencesStatus) throws Throwable {
        Wait.secondsUntilElementPresent("customer_preference.text_flag", 50);
        Assert.assertTrue("Text status is not displayed correctly", Elements.getText("my_account.text_flag").contains(preferencesStatus));
        logger.info("Email status is displayed correctly");

    }

    @And("^I should see the \"([^\"]*)\" link on preferences card$")
    public void iShouldSeeTheLinkOnPreferencesCard(String addaStoretext) throws Throwable {
       Wait.untilElementPresent(Elements.element("my_account.add_a_store_label"));
        Assert.assertTrue("Add a Store link text is not displayed ", Elements.getText("my_account.add_a_store_label").contains(addaStoretext));
        logger.info("Add a store link is displayed ");
    }

    @When("^i click on Add a Store link on preferences card$")
    public void iClickOnAddAStoreLinkOnPreferencesCard() throws Throwable {
       Clicks.click("my_account.add_a_store");
       logger.info("Add a store link is clicked");
    }


    @Then("^I should see store 'change' option on preferences card$")
    public void iShouldSeeStoreChangeOptionOnPreferencesCard() throws Throwable {
        Wait.untilElementPresent(By.linkText("my_account.change_storetext"));
        logger.info("Change store link text is displayed");
        Clicks.click("my_account.change_storetext");
        logger.info("change store link is clickable ");
    }

}
