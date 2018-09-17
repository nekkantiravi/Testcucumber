package com.macys.sdt.projects.Marketing.CustomerPreferences.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerPreference extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(CustomerPreference.class);
    String currentEmail = TestUsers.currentEmail;

    @When("^I navigate to Preferences page from My Account page$")
    public void i_Navigate_To_Preferences_Page_From_MyAccountPage() {
        logger.info("Go to My Preferences link Page!!");
        if (!onPage("my_account")) {
            MyAccountSteps f = new MyAccountSteps();
            f.iNavigateToMyAccountPage();
        }
    }

    @Then("^I should see the below three preferences cards display on landing page$")
    public void I_should_see_the_below_three_preferences_cards_display_on_landing_page(List<String> cards) {
        pausePageHangWatchDog();
        for (int i = 0; i < cards.size(); i++) {
            Wait.untilElementPresent(Elements.element("customer_preferences" + cards.get(i)));
            Assert.assertTrue("preferences cards are not displaying", Elements.elementPresent("customer_preferences" + cards.get(i)));
            // Elements.elementPresent("customer_preferences.goto_style_fit"));

        }
        resumePageHangWatchDog();
        logger.info("All the three preferences cardds got displayed on the landing page");
    }

    @And("^I navigate to Notifications preferences sub page$")
    public void I_navigate_to_Notifications_preferences_sub_page() {
        onPage("customer_preferences");
        Wait.untilElementPresent(Elements.element("customer_preferences.goto_notifications_view"));
        Clicks.click("customer_preferences.goto_notifications_view");
        logger.info("Navigated to Notification preferences sub page");
    }


    @Then("^I should see the below preferences on notification page$")
    public void I_should_see_the_below_Preferences_On_NotificationPage(List<String> elements) {
        pausePageHangWatchDog();
        for (String el : elements) {
            el = "customer_preferences." + el;
            Wait.untilElementPresent(Elements.element("customer_preferences." + el));
            if (!Elements.elementPresent(el)) {
                Assert.fail(el + " preferences cards are not displaying on notification page");
            }
        }
        logger.info("Email and Text Preferences got displayed on the Notification page");
    }


    @When("^I navigate to Preferences page directly from My Account page$")
    public static void I_navigate_to_Preferences_page_directly_from_My_Account_page() {
        String i = RunConfig.url;
        String pre_url = i + "/account/preferences";
        if (pre_url.contains("http:")) {
            pre_url = pre_url.replace("http", "https");
        }
        Navigate.visit(pre_url);
        logger.info("Navigated to Preferences landing page");
    }

    @Then("^I should see the \"([^\"]*)\" text$")
    public void I_Should_See_The_Text(String Email) {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_label"));
        Assert.assertTrue("Email text is not displaying", Elements.elementPresent("customer_preferences.email_label"));
        logger.info("Email Preference label got displayed on the page");
    }

    @When("^I click on 'EDIT' link next to email icon$")
    public void I_Click_On_EDIT_Link_Next_To_Email_Icon() {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_edit"));
        Wait.untilElementPresent(Elements.element("customer_preferences.text_edit"));
        Wait.secondsUntilElementPresent("customer_preferences.email_edit", 50);
        Clicks.click("customer_preferences.email_edit");
        logger.info("Clicked on the edit option of Email Preference");
    }

    @Then("^I should see edit mode for email preference$")
    public void I_Should_See_Edit_Mode_For_Email_Preference() {
        Assert.assertTrue("Email preference is not displayed in edit mode", Elements.elementPresent("customer_preferences.email_edit_in_profile"));
        logger.info("Edit state of Email preference got displayed");
    }

    @And("^I should see the email address$")
    public void I_Should_See_The_Email_Address() {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_address_edit_state"));
        Assert.assertTrue("Email address is not displayed", Elements.getText("customer_preferences.email_address_edit_state").contains(currentEmail));
        logger.info("Email address got displayed in the Edit state of Email Preference");
    }

    @And("^I should see text 'Your current email address is' with user email address$")
    public void I_Should_See_Text_Your_Current_Email_Address_Is_With_User_Email_Address() {
        Assert.assertTrue("Text beside email address in edit state is not displayed", Elements.elementPresent("customer_preferences.text_in_email_edit_state"));
        Assert.assertTrue("Email address in edit state is not displayed", Elements.getText("customer_preferences.email_address_edit_state").contains(currentEmail));
        logger.info("Text beside the email address and the Email address got displayed in th edit state of Email Preference");
    }


    @And("^I should see the placeholder static text \"([^\"]*)\" display$")
    public void I_Should_See_The_Place_Holder_Static_Text_Display(String PlaceHolderText) {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_edit_place_holder_text"));
        Assert.assertTrue("Place holder text is not displayed in edit state", Elements.getText("customer_preferences.email_edit_place_holder_text").contains(PlaceHolderText));
        logger.info("Place holder text got displayed  in the edit state of Email Preference");
    }


    @And("^I should see the three radial buttons$")
    public void I_Should_See_The_Three_Radial_Buttons(List<String> RadialButtons) {
        pausePageHangWatchDog();
        Assert.assertTrue("First radio option is  not displayed", Elements.getText("customer_preferences.email_edit_first_radio_option").contains(RadialButtons.get(0)));
        Assert.assertTrue("Second radio option is  not displayed", Elements.getText("customer_preferences.email_edit_second_radio_option").contains(RadialButtons.get(1)));
        Assert.assertTrue("Third radio option is  not displayed", Elements.getText("customer_preferences.email_edit_third_radio_option").contains(RadialButtons.get(2)));
        resumePageHangWatchDog();
        logger.info("All the three radio options got displayed in the edit state of Email Preference");
    }


    @And("^I should see the 'Save' and 'Cancel' button$")
    public void I_Should_See_The_Save_And_Cancel_Button() {
        Assert.assertTrue("Save button is not displayed", Elements.elementPresent("customer_preferences.email_edit_save_button"));
        Assert.assertTrue("Cancel button is not displayed", Elements.elementPresent("customer_preferences.email_edit_cancel_button"));
        logger.info("Save and Cancel buttons got displayed in the edit state of Email Preference");
    }


    @And("^I should see the \"([^\"]*)\" text with user email address$")
    public void I_Should_See_The_Text_With_User_Email_Address(String arg0) {
        Wait.untilElementPresent(Elements.element("customer_preferences.enabled_text"));
        Assert.assertTrue("Email is in disabled state", Elements.elementPresent("customer_preferences.enabled_text"));
        Assert.assertTrue("Email address is not displayed", Elements.elementPresent("customer_preferences.email_address"));
        logger.info("Email got displayed in Enabled state along with the Email address");
    }

    @And("^I click on \"([^\"]*)\" button$")
    public void I_Click_On_Save_Button(String arg0) {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_edit_save_button"));
        Clicks.click("customer_preferences.email_edit_save_button");
        logger.info("Clicked on the Save button in the edit state of Email Preference");
    }


    @And("^I select the 'Sure, but I only want the highlights' radio option$")
    public void I_Select_The_Sure_But_I_Only_Want_The_Highlights_Radio_Option() {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_edit_second_radio_button"));
        Clicks.click("customer_preferences.email_edit_second_radio_button");
        logger.info("Selected 'Sure, but I only want the highlights' radio option");
    }


    @And("^I select the 'Yes, I don't want to miss a thing!' radio option$")
    public void I_Select_The_Yes_I_DonT_Want_To_Miss_A_Thing_Radio_Option() {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_edit_first_radio_button"));
        Clicks.click("customer_preferences.email_edit_first_radio_button");
        logger.info("Selected 'Yes, I don't want to miss a thing! radio option' ");
    }

    @And("^I select the 'No, thanks! I'm good for now' radio option$")
    public void I_Select_The_No_Thanks_IMGoodForNow_Radio_Option() {
        Wait.untilElementPresent(Elements.element("customer_preferences.email_edit_third_radio_button"));
        Clicks.click("customer_preferences.email_edit_third_radio_button");
        logger.info("Selected 'No, thanks! I'm good for now' radio option");
    }


    @And("^I should see the 'Disabled' text without user email address$")
    public void I_Should_See_The_Disabled_Text_Without_User_Email_Address() {
        Wait.untilElementPresent(Elements.element("customer_preferences.disabled_text"));
        Assert.assertTrue("Email is in enabled state", Elements.elementPresent("customer_preferences.disabled_text"));
        Assert.assertFalse("Email address is displayed", Elements.elementPresent("customer_preferences.email_address"));
        logger.info("Email got displayed in disabled state and Email address is not displayed as expected");
    }

    @Then("^I click on edit link next to text preference$")
    public void I_Click_On_Edit_Link_Next_To_Text_Preference() {
        Wait.untilElementPresent(Elements.element("customer_preferences.text_edit"));
        Clicks.click("customer_preferences.text_edit");
        logger.info("Clicked on the Edit option of Notification preference");
    }

    @And("^I should see the text notification edit state$")
    public void I_Should_See_The_Text_Notification_Edit_State() {
        Assert.assertTrue("Text preference is not displayed in edit mode", Elements.elementPresent("customer_preferences.text_edit_mobile_number"));
        logger.info("Edit  state of Notification preference got displayed");
    }

    @Then("^I should see the 'Text' text$")
    public void I_Should_See_The_Text_Text() {
        Wait.untilElementPresent(Elements.element("customer_preferences.text_edit"));
        Assert.assertTrue("Text text is not displaying", Elements.elementPresent("customer_preferences.text_label"));
        logger.info("Text Preference label got displayed");
    }

    @And("^I should see the 'Enabled' text with user mobile number$")
    public void I_Should_See_The_Enabled_Text_With_User_Mobile_Number() {
        Wait.untilElementPresent(Elements.element("customer_preferences.text_enabled_text"));
        Assert.assertTrue("Text is in disabled state", Elements.elementPresent("customer_preferences.text_enabled_text"));
        Assert.assertTrue("Phone number is not displayed", Elements.elementPresent("customer_preferences.phone_number"));
        logger.info("Text Preference got displayed in the disabled state along with the Phone number");
    }

    @And("^I should see the copy text \"([^\"]*)\" display$")
    public void I_Should_See_The_Copy_Text_Display(String PlaceHolderText) {
        Wait.untilElementPresent(Elements.element("customer_preferences.text_edit_place_holder_text"));
        Assert.assertTrue("Place holder text is not displayed in edit state", Elements.getText("customer_preferences.text_edit_place_holder_text").contains(PlaceHolderText));
        logger.info("Place holder text got displayed in edit state of Notification Preference");
    }


    @And("^I should see a 'Yes, text me' radio button with 'Phone number' text box$")
    public void I_Should_See_A_Yes_Text_Me_Radio_Button_With_Phone_Number_Text_Box() {
        Wait.untilElementPresent(Elements.element("customer_preferences.text_edit_yes_radio_option"));
        Assert.assertTrue("Yes, text me - radio option is not displayed", Elements.elementPresent("customer_preferences.text_edit_yes_radio_option"));
        Assert.assertTrue("Phone number text box is not displayed", Elements.elementPresent("customer_preferences.text_edit_mobile_number"));
        logger.info(" 'Yes, text me ..' radio option got displayed along with the phone number");
    }

    @And("^I should see 'Maybe later I'd prefer not to receive texts at this time' radio button$")
    public void I_Should_See_Maybe_Later_ID_Prefer_Not_To_Receive_Texts_At_This_Time_Radio_Button() {
        Assert.assertTrue("Maybe later - radio option is not displayed", Elements.elementPresent("customer_preferences.text_edit_no_radio_option"));
        logger.info(" 'Maybe later ..' radio option got displayed ");
    }


    @When("^I select 'Maybe later I'd prefer not to receive texts at this time' radio button$")
    public void I_Select_Maybe_Later_ID_Prefer_Not_To_Receive_Texts_At_This_Time_Radio_Button() {
        Wait.untilElementPresent(Elements.element("customer_preferences.text_edit_second_radio_button"));
        Clicks.click("customer_preferences.text_edit_second_radio_button");
        logger.info("Selected 'Maybe later' radio option");
    }

    @And("^I navigate to Categories page from Preferences Landing page$")
    public void I_Navigate_To_Categories_Page_From_Preferences_Landing_Page() {
        onPage("customer_preferences");
        Wait.secondsUntilElementPresent("customer_preferences.goto_category_view", 30);
        Clicks.click("customer_preferences.goto_category_view");
        logger.info("Navigated to Category Preferences page");
    }

    @Then("^I should see the Add state of Categories$")
    public void I_Should_See_The_Add_State_Of_Categories() {
        Wait.untilElementPresent(Elements.element("customer_preferences.categories_add_state_dropdown"));
        Wait.untilElementPresent(Elements.element("customer_preferences.categories_add_state_subheading"));
        Wait.secondsUntilElementPresent("customer_preferences.categories_add_state_subheading", 50);
        Assert.assertTrue("Add state is not displayed", Elements.elementPresent("customer_preferences.categories_add_state_subheading"));
        logger.info("Add state of Category Preferences page got displayed");
    }

    @And("^I should see the \"([^\"]*)\" page title$")
    public void I_Should_See_The_Page_Title_As(String PageTitle) {
        Assert.assertTrue("Shopping Preferences page title is not displayed", Elements.getText("customer_preferences.categories_add_state_title").contains(PageTitle));
        logger.info("Shopping Preferences page title got displayed");
    }


    @And("^I should see the 'Save' button in disabled state$")
    public void I_Should_See_The_Save_Button_In_Disabled_State() {
        Navigate.scrollPage(150000, 200);
        Assert.assertTrue("Save button is displayed in enabled state", Elements.elementPresent("customer_preferences.categories_add_state_disabled_save"));
        logger.info("Save button got displayed in disabled state as expected");
    }


    @And("^I should see the \"([^\"]*)\" text on brands page$")
    public void I_Should_See_The_Text_In_Add_State(String Text) {
        Assert.assertTrue("Text under the page title is not displayed", Elements.getText("customer_preferences.categories_add_state_text_under_title").contains(Text));
        logger.info("Text under the page title got displayed");
    }

    @And("^I should see the \"([^\"]*)\" tab heading$")
    public void I_Should_See_The_Tab_Heading(String TabHeading) {
        Assert.assertTrue("Categories tab heading is not displayed", Elements.getText("customer_preferences.categories_add_state_tab_heading").contains(TabHeading));
        logger.info("Categories tab heading got displayed");
    }


    @And("^I should see the sub heading \"([^\"]*)\"$")
    public void I_Should_See_The_Sub_Heading(String SubHeading) {
        Assert.assertTrue("Sub heading is not displayed in add state", Elements.getText("customer_preferences.categories_add_state_sub_heading").contains(SubHeading));
        logger.info("Sub heading got displayed in the add state of Category Preferences page");
    }

    @And("^I should see the caption text \"([^\"]*)\"$")
    public void I_Should_See_The_Caption_Text(String CaptionText) {
        Assert.assertTrue("Caption Text is not displayed in add state", Elements.getText("customer_preferences.categories_add_state_caption_text").contains(CaptionText));
        logger.info("Caption text got displayed in the add state of Category Preferences");
    }

    @And("^I should see the dropdown field$")
    public void I_Should_See_The_Dropdown_Field() {
        Assert.assertTrue("Dropdown field is not displayed", Elements.elementPresent("customer_preferences.categories_add_state_dropdown"));
        logger.info("Dropdown field got displayed in the Add state of Category Preferences");
    }

    @And("^I should see the 'Select All' checkbox option$")
    public void I_Should_See_The_Select_All_Checkbox_Option() {
        Assert.assertTrue("Select All Check box option is not displayed", Elements.elementPresent("customer_preferences.categories_add_state_checkbox_empty"));
        logger.info(" 'Select All' checkbox option got displayed in the Add state of Category Preferences");
    }


    @When("^I select 'Select All' checkbox option$")
    public void I_Select_Select_All_Checkbox_Option() {
        Clicks.click("customer_preferences.categories_add_state_checkbox");
        logger.info("Selected 'Select All' checkbox option in the Add state of Category Preferences page");
    }


    @And("^I click on 'Save' button$")
    public void I_Click_On_Save_Button() {
        Clicks.click("customer_preferences.categories_add_state_save_button");
        logger.info("Clicked on Save Button");
    }

    @Then("^I should see the Summary state of Categories$")
    public void I_Should_See_The_Summary_State_Of_Categories() {
        Wait.untilElementPresent(Elements.element("customer_preferences.categories_summary_state_edit_option"));
        Wait.untilElementPresent(Elements.element("customer_preferences.categories_summary_state_categories"));
        Wait.secondsUntilElementPresent("customer_preferences.categories_summary_state_edit_option", 50);
        Assert.assertTrue("Summary state is not displayed", Elements.elementPresent("customer_preferences.categories_summary_state_edit_option"));
        logger.info("Summary state of Category Preferences page got displayed");
    }

    @And("^I should see the text \"([^\"]*)\" in summary state$")
    public void I_Should_See_The_Text_In_Summary_State(String Text) {
        Assert.assertTrue("Text under the page title is not displayed", Elements.getText("customer_preferences.categories_summary_state_text_under_title").contains(Text));
        logger.info("Text under the page title got displayed in the summary state of Category Preferences");
    }

    @And("^I should see the 'Edit' option$")
    public void I_Should_See_The_Edit_Option() {
        Assert.assertTrue("Edit option is not displayed", Elements.elementPresent("customer_preferences.categories_summary_state_edit_option"));
        logger.info("Edit option is available in the Summary state of Categories Preferences");
    }

    @And("^I should see the caption text \"([^\"]*)\" in summary state$")
    public void I_Should_See_The_Caption_Text_In_Summary_State(String CaptionText) {
        Assert.assertTrue("Caption Text is not displayed", Elements.getText("customer_preferences.categories_summary_state_caption_text").contains(CaptionText));
        logger.info("Caption text is displayed in the Summary state of Categories Preferences");
    }


    @And("^I should see the categories count sub heading$")
    public void I_Should_See_The_Categories_Count_SubHeading() {
        Assert.assertTrue("Subheading is not displayed", Elements.elementPresent("customer_preferences.categories_summary_state_subheading"));
        logger.info("Subheading is displayed in the Summary state of Categories Preferences");
    }


    @And("^I should see the categories in Summary state$")
    public void I_Should_See_The_Categories_In_Summary_State() {
        Assert.assertTrue("Categories are not displayed", Elements.elementPresent("customer_preferences.categories_summary_state_categories"));
        logger.info("Categories are displayed in the Summary state of Categories Preferences page");
    }

    @When("^I click on 'Edit' option in Summary state$")
    public void I_Click_On_Edit_Option_In_Summary_State() {
        Clicks.click("customer_preferences.categories_summary_state_edit_option");
        logger.info("Clicked on the Edit option");
    }

    @Then("^I should see the Edit state of Categories$")
    public void I_Should_See_The_Edit_State_Of_Categories() {
        Wait.untilElementPresent(Elements.element("customer_preferences.categories_edit_state_cancel_button"));
        Assert.assertTrue("Edit state of Categories is not displayed", Elements.elementPresent("customer_preferences.categories_edit_state_cancel_button"));
        logger.info("Edit state of Categories page got displayed");
    }


    @And("^I should see the text \"([^\"]*)\" in edit state$")
    public void I_Should_See_The_Text_In_Edit_State(String Text) {
        Assert.assertTrue("Text under the page title is not displayed", Elements.getText("customer_preferences.categories_edit_state_text_under_title").contains(Text));
        logger.info("Text under the page title is displayed");
    }


    @And("^I should see the sub heading \"([^\"]*)\" in edit state$")
    public void I_Should_See_The_SubHeading_In_Edit_State(String SubHeading) {
        Assert.assertTrue("Sub heading is not displayed in edit state", Elements.getText("customer_preferences.categories_edit_state_sub_heading").contains(SubHeading));
        logger.info("Subheading is displayed in the edit state of Category Preferences page");
    }


    @And("^I should see the caption text \"([^\"]*)\" in edit state$")
    public void I_Should_See_The_Caption_Text_In_Edit_State(String CaptionText) {
        Assert.assertTrue("Caption Text is not displayed", Elements.getText("customer_preferences.categories_edit_state_caption_text").contains(CaptionText));
        logger.info("Caption Text is displayed");
    }


    @And("^I should see the 'Deselect All' checkbox option$")
    public void I_Should_See_The_DeselectAll_Checkbox_Option() {
        Assert.assertTrue("Deselect All Check box option is not displayed", Elements.elementPresent("customer_preferences.categories_edit_state_checkbox_selected"));
        logger.info("'Deselect All' check box option is displayed");
    }

    @And("^I should see 'Save' and 'Cancel' buttons$")
    public void I_Should_See_Save_And_Cancel_Buttons() {
        Assert.assertTrue("Save button is not displayed", Elements.elementPresent("customer_preferences.categories_edit_state_save_button"));
        Assert.assertTrue("Cancel button is not displayed", Elements.elementPresent("customer_preferences.categories_edit_state_cancel_button"));
        logger.info("Save and Cancel buttons are displayed");
    }


    @When("^I deselect a category in Edit state$")
    public void I_Deselect_A_Category_In_Edit_State() {
        Clicks.click("customer_preferences.categories_edit_state_first_category");
        logger.info("Deselected a category in the edit state of Category Preferences");
    }

    @And("^I should see \"([^\"]*)\" tab heading$")
    public void I_Should_See_TabHeading(String TabHeading) {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_tab_heading"));
        Assert.assertTrue("Sizes tab heading is not displayed", Elements.getText("customer_preferences.sizes_tab_heading").contains(TabHeading));
        logger.info("Sizes tab heading is displayed");
    }

    @When("^I click on 'Sizes' tab option$")
    public void I_Click_On_Sizes_Tab_Option() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_tab_heading"));
        Clicks.click("customer_preferences.sizes_tab_heading");
        logger.info("Clicked on the 'Sizes' tab option");
    }


    @Then("^I should see the Sizes sub heading \"([^\"]*)\"$")
    public void I_Should_See_The_Sizes_Sub_Heading(String SubHeading) throws Throwable {
        Thread.sleep(10000);
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_add_sizes_title"));
        Wait.secondsUntilElementPresent("customer_preferences.sizes_add_sizes_title", 50);
        Assert.assertTrue("Add State of Sizes is not displayed", Elements.getText("customer_preferences.sizes_add_sizes_title").contains(SubHeading));
        logger.info("Add state of Sizes page is displayed");
    }

    @And("^I should see the Sizes caption text \"([^\"]*)\"$")
    public void I_Should_See_The_Sizes_Caption_Text(String CaptionText) {
        Assert.assertTrue("Add Sizes Caption Text is not displayed", Elements.getText("customer_preferences.sizes_add_sizes_caption_text").contains(CaptionText));
        logger.info("Caption text is displayed");
    }

    @And("^I should see the Sizes dropdown field$")
    public void I_Should_See_The_Sizes_Dropdown_Field() {
        Assert.assertTrue("Dropdown field is not displayed", Elements.elementPresent("customer_preferences.sizes_add_sizes_dropdown_field"));
        logger.info("Dropdown field is displayed");
    }

    @When("^I click on accordion$")
    public void I_Click_On_Accordion() {
        Clicks.click("customer_preferences.sizes_add_sizes_first_accordion");
        logger.info("Clicked on the Accordion");
    }

    @Then("^I should see that the accordion as expanded$")
    public void I_Should_See_That_The_Accordion_As_Expanded() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_add_sizes_first_accordion"));
        Assert.assertTrue("Accordion is not expanded", Elements.elementPresent("customer_preferences.sizes_add_sizes_expanded_accordion"));
        logger.info("Accordion got expanded");
    }


    @When("^I select any Size tile$")
    public void I_Select_Any_Size_Tile() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_add_sizes_first_size_tile"));
        Clicks.click("customer_preferences.sizes_add_sizes_first_size_tile");
        logger.info("Selected a size tile");
    }

    @Then("^I should see the Summary state of Sizes$")
    public void I_Should_See_The_Summary_State_Of_Sizes() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_summary_sizes_edit_option"));
        Wait.secondsUntilElementPresent("customer_preferences.sizes_summary_sizes_edit_option", 50);
        Assert.assertTrue("Summary state of Sizes is not displayed", Elements.elementPresent("customer_preferences.sizes_summary_sizes_edit_option"));
        logger.info("Summary state of Sizes page is displayed");
    }

    @And("^I should see the sizes in Summary state$")
    public void I_Should_See_The_Sizes_In_Summary_State() {
        //Wait.untilElementPresent(Elements.element("customer_preferences.sizes_summary_sizes_edit_option"));
        Wait.secondsUntilElementPresent("customer_preferences.sizes_summary_sizes_edit_option", 50);
        Assert.assertTrue("Size tile is not displayed", Elements.elementPresent("customer_preferences.sizes_summary_size_tile"));
        logger.info("Size tile got displayed in the summary state of Sizes");
    }

    @When("^I click on 'Edit' option$")
    public void I_Click_On_Edit_Option() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_summary_sizes_edit_option"));
        Clicks.click("customer_preferences.sizes_summary_sizes_edit_option");
        logger.info("Clicked on the Edit option");
    }

    @Then("^I should see the Edit state of Sizes$")
    public void I_Should_See_The_Edit_State_Of_Sizes() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_edit_sizes_title"));
        Wait.secondsUntilElementPresent("customer_preferences.sizes_edit_sizes_title", 50);
        Assert.assertTrue("Edit Sizes state is not displayed", Elements.elementPresent("customer_preferences.sizes_edit_sizes_title"));
        logger.info("Edit state of Sizes page is displayed");
    }

    @And("^I should see the 'Cancel' button$")
    public void I_Should_See_The_Cancel_Button() {
        Assert.assertTrue("Cancel button is not displayed in Edit Sizes state", Elements.elementPresent("customer_preferences.sizes_edit_sizes_cancel_button"));
        logger.info("Cancel button is available");
    }

    @And("^I should see the Edit Sizes sub heading \"([^\"]*)\"$")
    public void I_Should_See_The_Edit_Sizes_SubHeading(String SubHeading) {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_edit_sizes_title"));
        Assert.assertTrue("Edit Sizes sub heading is not displayed", Elements.getText("customer_preferences.sizes_edit_sizes_title").contains(SubHeading));
        logger.info("Edit Sizes sub heading is displayed");
    }

    @Then("^I should see the Add state of Sizes$")
    public void I_Should_See_The_Add_State_Of_Sizes() {
        Wait.untilElementPresent(Elements.element("customer_preferences.sizes_add_sizes_title"));
        Assert.assertTrue("Add Sizes state is not displayed", Elements.elementPresent("customer_preferences.sizes_add_sizes_title"));
        logger.info("Add  state of Sizes is displayed");
    }

    @And("^I should see the text \"([^\"]*)\" in Edit State$")
    public void I_Should_See_The_Container_Text_In_Edit_State(String Text) {
        Assert.assertTrue("Text under the sizes summary page title is not displayed", Elements.getText("customer_preferences.categories_summary_state_text_under_title").contains(Text));
        logger.info("Text under the sizes summary state is displayed");
    }

    @And("^I should see the edit sizes caption text \"([^\"]*)\"$")
    public void I_Should_See_The_Edit_Sizes_Caption_Text(String CaptionText) {
        Assert.assertTrue("Caption Text in the edit sizes page is not displayed", Elements.getText("customer_preferences.sizes_edit_state_caption_text").contains(CaptionText));
        logger.info("Caption Text is displayed in the edit sizes page");
    }

    @And("^I should see the dropdown field in edit sizes page$")
    public void I_Should_See_The_Dropdown_Field_In_Edit_Sizes_Page() {
        Assert.assertTrue("Dropdown field is not displayed in Edit sizes page", Elements.elementPresent("customer_preferences.sizes_edit_state_dropdown_field"));
        logger.info("Dropdown field is displayed in the Edit Sizes page");
    }


    @Then("^I should see Preferences card$")
    public void I_Should_See_Preferences_Card() {
        Assert.assertTrue("Preferences card is not displayed", Elements.elementPresent("customer_preferences.my_account_preferences_card"));
        logger.info("Preferences card is displayed in the My Account page");
    }

    @When("^I click on View Preference option$")
    public void I_Click_On_View_Preference_Option() {
        Clicks.click("customer_preferences.preferences_card_view_preferences");
        logger.info("Clicked on the View Preference option");
    }

    @Then("^I should navigate to Preferences landing page$")
    public void I_Should_Navigate_To_Preferences_Landing_Page() {
        Wait.untilElementPresent(Elements.element("customer_preferences.verify_landing_page"));
        Assert.assertTrue("Preferences landing page is not displayed", Elements.elementPresent("customer_preferences.verify_landing_page"));
        logger.info("Navigated to teh Preferences landing page");
    }

    @When("^I click on the Preferred store card$")
    public void I_Click_On_The_Preferred_Store_Card() {
        Wait.untilElementPresent(Elements.element("customer_preferences.goto_preferred_store"));
        Clicks.click("customer_preferences.goto_preferred_store");
        logger.info("Clicked on the Preferred store card");
    }

    @Then("^I should get navigated to Preferred store page$")
    public void I_Should_Get_Navigated_To_Preferred_Store_Page() {
        Wait.secondsUntilElementPresent("customer_preferences.preferred_store_heading", 50);
        Assert.assertTrue("Preferred Store page is not displayed", Elements.elementPresent("customer_preferences.preferred_store_heading"));
        logger.info("Navigated to the Preferred store page");
    }

    @And("^I should see the text \"([^\"]*)\" below the heading$")
    public void I_Should_See_The_Text_Below_The_Heading(String CopyText) {
        Assert.assertTrue("Copy Text is not displayed in the preferred store page", Elements.getText("customer_preferences.preferred_store_copy_text").contains(CopyText));
        logger.info("Copy text is displayed in the Preferred store page");
    }

    @And("^I should see text box field of ZIP Code$")
    public void I_Should_See_Text_Box_Field_Of_ZIP_Code() {
        Assert.assertTrue("Preferred Store ZIP Code text field is not displayed", Elements.elementPresent("customer_preferences.preferred_store_text_field"));
        logger.info("Text field of Zip code is displayed");
    }

    @And("^I should see the Search icon option$")
    public void I_Should_See_The_Search_Icon_Option() {
        Assert.assertTrue("Preferred Store ZIP Code Search icon is not displayed", Elements.elementPresent("customer_preferences.preferred_store_search_icon"));
        logger.info("Search icon option is displayed");
    }

    @When("^I select any store from the search results$")
    public void I_Select_Any_Store_From_The_Search_Results() {
        Wait.untilElementPresent(Elements.element("customer_preferences.preferred_store_first_button"));
        Wait.secondsUntilElementPresent("customer_preferences.preferred_store_first_button", 50);
        Clicks.click("customer_preferences.preferred_store_first_button");
        logger.info("Selected a store from the Search Results");
    }

    @Then("^I should navigate to the summary state of Preferred store$")
    public void I_Should_Navigate_To_The_Summary_State_Of_Preferred_Store() {
        Wait.untilElementPresent(Elements.element("customer_preferences.preferred_store_edit_option"));
        Assert.assertTrue("Summary state of Preferred store is not displayed", Elements.elementPresent("customer_preferences.preferred_store_edit_option"));
        logger.info("Navigated to the summary state of Preferred store");
    }

    @And("^I should see the Edit option on the page$")
    public void I_Should_See_The_Edit_Option_On_The_Page() {
        Assert.assertTrue("Edit option is not displayed on the page", Elements.elementPresent("customer_preferences.preferred_store_edit_option"));
        logger.info("Edit option is available");
    }

    @When("^I click on the Edit option on the page$")
    public void I_Click_On_The_Edit_Option_On_The_Page() {
        Clicks.click("customer_preferences.preferred_store_edit_option");
        logger.info("Clicked on the Edit option");
    }

    @Then("^I should navigate to edit state of Preferred store$")
    public void I_Should_Navigate_To_Edit_State_Of_Preferred_Store() {
        Wait.secondsUntilElementPresent("customer_preferences.preferred_store_cancel_button", 50);
        Assert.assertTrue("Edit state of Preferred store page is not displayed", Elements.elementPresent("customer_preferences.preferred_store_cancel_button"));
        logger.info("Navigated to the edit state of Preferred store");
    }

    @And("^I should see the Cancel button on the page$")
    public void I_Should_See_The_Cancel_Button_On_The_Page() {
        Assert.assertTrue("Cancel button is not displayed", Elements.elementPresent("customer_preferences.preferred_store_cancel_button"));
        logger.info("Cancel button is displayed");
    }


    @And("^I should see the selected store button in disabled state$")
    public void I_Should_See_The_Selected_Store_Button_In_Disabled_State() {
        Assert.assertTrue("Selected store button is not displayed in disabled state", Elements.elementPresent("customer_preferences.preferred_store_first_button_disabled"));
        logger.info("Selected store button is in disabled state as expected");
    }

    @And("^I should see the Text Notification status as Enabled$")
    public void I_Should_See_The_Text_Notification_Status_As_Enabled() {
        Wait.secondsUntilElementPresent("customer_preferences.text_enabled_state", 50);
        Assert.assertTrue("Text Notification is not displayed in enabled state", Elements.elementPresent("customer_preferences.text_enabled_state"));
        logger.info("Text Notification status is displayed as Enabled as expected");
    }


    @And("^I should see the Text Notification status as Disabled$")
    public void I_Should_See_The_Text_Notification_Status_As_Disabled() {
        Wait.untilElementPresent(Elements.element("customer_preferences.disabled_text"));
        Assert.assertTrue("Text Notification is not displayed in disabled state", Elements.elementPresent("customer_preferences.disabled_text"));
        logger.info("Text Notification status is displayed as Disabled as expected");
    }

    @And("^I should see \"([^\"]*)\" tab heading in the page$")
    public void I_Should_See_Tab_Heading_In_The_Page(String TabHeading) {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_tab_heading"));
        Assert.assertTrue("Brands tab heading is not displayed", Elements.getText("customer_preferences.brands_tab_heading").contains(TabHeading));
        logger.info("Brands tab heading is displayed");
    }

    @When("^I click on 'Brands' tab option$")
    public void I_Click_On_Brands_Tab_Option() {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_tab_heading"));
        Clicks.click("customer_preferences.brands_tab_heading");
        logger.info("Clicked on Brands tab option");
    }

    @Then("^I should see the \"([^\"]*)\" Brands sub heading$")
    public void I_Should_See_The_Brands_Sub_Heading(String SubHeading) {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_add_brands_title"));
        Assert.assertTrue("Add State of Brands is not displayed", Elements.getText("customer_preferences.brands_add_brands_title").contains(SubHeading));
        logger.info("Add state of Brands is displayed");
    }

    @And("^I should see the \"([^\"]*)\" Brands caption text$")
    public void I_Should_See_The_Brands_Caption_Text(String CaptionText) {
        Assert.assertTrue("Add Brands Caption Text is not displayed", Elements.getText("customer_preferences.brands_add_brands_caption_text").contains(CaptionText));
        logger.info("Caption Text is displayed the Add Brands page");
    }

    @And("^I should see the Brands dropdown field$")
    public void I_Should_See_The_Brands_Dropdown_Field() {
        Assert.assertTrue("Dropdown field is not displayed", Elements.elementPresent("customer_preferences.brands_add_brands_dropdown_field"));
        logger.info("Dropdown field is displayed");
    }

    @When("^I select any Brands tile$")
    public void I_Select_Any_Brands_Tile() {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_add_brands_first_brand_tile"));
        Wait.secondsUntilElementPresent("customer_preferences.brands_add_brands_first_brand_tile", 50);
        Clicks.click("customer_preferences.brands_add_brands_first_brand_tile");
        logger.info("Selected a Brand tile");
    }

    @Then("^I should see the Summary state of Brands$")
    public void I_Should_See_The_Summary_State_Of_Brands() {
        Wait.secondsUntilElementPresent("customer_preferences.brands_summary_brands_edit_option", 50);
        Assert.assertTrue("Summary state of Brands is not displayed", Elements.elementPresent("customer_preferences.brands_summary_brands_edit_option"));
        Assert.assertTrue("Sub heading is not displayed in summary state of brands", Elements.elementPresent("customer_preferences.brands_summary_sub_heading"));
        logger.info("Summary state of Brands page got displayed");
    }

    @And("^I should see the 'Save' button below the brands in disabled state$")
    public void I_Should_See_The_Save_Button_Below_The_Brands_In_Disabled_State() {
        Assert.assertTrue("Save button is displayed in enabled state", Elements.elementPresent("customer_preferences.categories_add_state_disabled_save"));
        logger.info("Save button is displayed in disabled state as expected");
    }

    @And("^I should see the brands in Summary state$")
    public void I_Should_See_The_Brands_In_Summary_State() {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_summary_brands_edit_option"));
        Assert.assertTrue("Size brand is not displayed in summary state of brands", Elements.elementPresent("customer_preferences.brands_summary_selected_brand"));
        logger.info("Size brand is displayed in summary state of brands");
    }

    @When("^I click on 'Edit' option in summary state of brands$")
    public void I_Click_On_Edit_Option_In_Summary_State_Of_Brands() {
        Clicks.click("customer_preferences.brands_summary_brands_edit_option");
        logger.info("Clicked on the Edit option in the summary state of Brands");
    }


    @Then("^I should see the Edit state of Brands$")
    public void I_Should_See_The_Edit_State_Of_Brands() {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_edit_sub_heading"));
        Assert.assertTrue("Edit state of Brands is not displayed", Elements.elementPresent("customer_preferences.brands_edit_sub_heading"));
        logger.info("Edit state of Brands page is displayed");
    }

    @And("^I should see the \"([^\"]*)\" sub heading on Edit Brands$")
    public void I_Should_See_The_Edit_Brands_SubHeading(String SubHeading) {
        Wait.untilElementPresent(Elements.element("customer_preferences.brands_edit_sub_heading"));
        Assert.assertTrue("Edit Brands sub heading is not displayed", Elements.getText("customer_preferences.brands_edit_sub_heading").contains(SubHeading));
        logger.info("Edit Brands sub heading is displayed");
    }

    @And("^I should see the \"([^\"]*)\" edit brands caption text$")
    public void I_Should_See_The_Edit_Brands_Caption_Text(String CaptionText) {
        Assert.assertTrue("Caption Text in the edit brands page is not displayed", Elements.getText("customer_preferences.brands_edit_caption_text").contains(CaptionText));
        logger.info("Caption text in the edit state of brands page is displayed");
    }

    @When("^I deselect Brands tile$")
    public void I_Deselect_Brands_Tile() {
        Clicks.click("customer_preferences.brands_edit_brands_tile");
        logger.info("Deselected the Brands tile");
    }

    @Then("^I should see the Add state of Brands$")
    public void I_Should_See_The_Add_State_Of_Brands() {
        Wait.secondsUntilElementPresent("customer_preferences.brands_add_brands_title", 50);
        Assert.assertTrue("Add State of Brands is not displayed", Elements.getText("customer_preferences.brands_add_brands_title").contains("Add Brands"));
        logger.info("Add state of Brands page is displayed");
    }

    @And("^I enter the ZIP Code into the field$")
    public void I_Enter_The_ZIP_Code_Into_The_Field() {
        TextBoxes.typeTextbox("customer_preferences.zip_code_text_field", "22102");
        logger.info("Entered ZIP Code into the text field");
    }

    @And("^I click the 'Search' button$")
    public void I_Click_The_Search_Button() {
        Clicks.click("customer_preferences.search_button_preferred_store");
        logger.info("Clicked on Search Button");
    }


    @And("^I should see the \"([^\"]*)\" link in left nav$")
    public void iShouldSeeTheLinkInLeftNav(String Preferenceslabel) throws Throwable {
        Wait.untilElementPresent(Elements.element("customer_preferences.preferences_leftnav"));
        Assert.assertTrue("preferences link is not displayed on leftnav ", Elements.getText("customer_preferences.preferences_leftnav").contains(Preferenceslabel));
    }


    @When("^I select \"([^\"]*)\" link on preferences page$")
    public void iSelectLinkOnApplyAndLearnMorePage(String link) throws Throwable {
        Clicks.click("customer_preferences." + link);
    }


    @And("^I should see the preferences subnav links$")
    public void iShouldSeeThePreferencesSubnavLinks(List<String>Preferenceslabel) throws Throwable {
        pausePageHangWatchDog();
        Wait.untilElementPresent(Elements.element("customer_preferencs.preferences_leftnav_shopping"));
        Assert.assertTrue("preferences Shopping sublink is not displayed on leftnav ", Elements.getText("customer_preferences.preferences_leftnav_shopping").contains(Preferenceslabel.get(0)));

        Wait.untilElementPresent(Elements.element("customer_preferencs.preferences_leftnav_notification"));
        Assert.assertTrue("preferences notification sublink is not displayed on leftnav ", Elements.getText("customer_preferences.preferences_leftnav_notification").contains(Preferenceslabel.get(1)));

        Wait.untilElementPresent(Elements.element("customer_preferencs.preferenaces_leftnav_preferredstore"));
        Assert.assertTrue("preferences Storepreferences sublink is not displayed on leftnav ", Elements.getText("customer_preferences.preferenaces_leftnav_preferredstore").contains(Preferenceslabel.get(2)));
        resumePageHangWatchDog();
            logger.info("All the three preferences subnav links got displayed in the leftnav ");
        }
    @Then("^I should see the sizes categories in drop down$")
    public void iShouldSeeTheCategoriesInDropDown(List<String>Sizecategorylabel) throws Throwable {
        Wait.untilElementPresent("customer_preferences.select_categories_dropdown");
        Assert.assertTrue("Women sizes category is displayed", Elements.getText("customer_preferences.select_categories_dropdown").contains(Sizecategorylabel.get(1)));
        logger.info("All the three preferences sizes category is displayed in drop down ");

    }


    @And("^I select \"([^\"]*)\" category in the dropdown$")
    public void iSelectCategoryInTheDropdown(String value) throws Throwable {
        Wait.secondsUntilElementPresent("customer_preferences.select_categories_dropdown", 20);
        Assert.assertTrue(Elements.elementPresent("customer_preferences.select_categories_dropdown"));
        Elements.elementPresent("customer_preferences.select_categories_dropdown"); {
            Clicks.click("customer_preferences.select_categories_dropdown");
            DropDowns.selectByText("customer_preferences.select_categories_dropdown", value);
            logger.info("Size categories are displayed in dropdown");

        }
    }

    @Then("^I should see \"([^\"]*)\" sizes categories loaded on selection$")
    public void iShouldSizesCategoriesLoadedOnSelection(String selected_size_category_loaded) throws Throwable {
        Wait.forPageReady();
        switch (selected_size_category_loaded) {
            case "Kids": {
                Wait.untilElementPresent(Elements.element("customer_preferences.kids_category_label"));
                Assert.assertTrue("Baby dress sizes are displayed ", Elements.getText("customer_preferences.kids_category_label").contains(selected_size_category_loaded));
                logger.info("Kids category sizes are loaded");
                break;
            }
            case "Women": {
                Assert.assertTrue("Mens sizes are displayed ", Elements.getText("customer_preferences.mens_category_label").contains(selected_size_category_loaded));
                logger.info("Mens category sizes are loaded");
                break;
            }
            case "Mens": {
                Assert.assertTrue("women sizes are displayed ", Elements.getText("customer_preferences.womens_category_label").contains(selected_size_category_loaded));
                logger.info("Womens category sizes are loaded");
            }
        }

    }
}




