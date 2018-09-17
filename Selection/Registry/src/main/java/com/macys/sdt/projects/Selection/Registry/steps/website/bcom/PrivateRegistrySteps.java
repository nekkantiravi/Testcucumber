package com.macys.sdt.projects.Selection.Registry.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.addresses.CurrentAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.CreditCardService;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website.ServiceSteps;
import com.macys.sdt.projects.Selection.Registry.actions.website.RegistryActions;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.framework.utils.StepUtils.pausePageHangWatchDog;
import static com.macys.sdt.framework.utils.StepUtils.resumePageHangWatchDog;

public class PrivateRegistrySteps {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSteps.class);

    private final RegistryActions registryActions = new RegistryActions();
    private final Registry registry = new Registry();


    @When("^I navigate to registry create page from registry home page$")
    public void I_Navigate_To_Registry_Create_Page_From_Registry_HomePage(){
        if(onPage("registry_home")){
            Clicks.click("registry_home.goto_create_registry");
            Wait.forPageReady();
            Clicks.clickIfPresent ("new_registry_sign_in.create_registry");
        }
        else {
            Assert.fail("Not on registry home page");
        }
    }


    @And("^I fill in Create Form up to Event Details page$")
    public void I_Fill_In_Create_Form_Up_To_Event_Details_Page() throws Throwable {
        registryActions.fillCreateFormUntilEventDetails();
        Wait.forPageReady();
        Assert.assertTrue("ERROR: Event Details page is not displayed.", Elements.elementPresent("new_create_registry.section_event_details"));
    }


    @Then("^I verify that the \"Make Registry Public\" checkbox (is|is not) checked$")
    public void I_verify_that_the_make_registry_public_checkbox_is_checked(String checkboxStatus) throws Throwable{
        if(checkboxStatus.equals("is not"))
            Assert.assertFalse("ERROR: Checkbox 'Make Registry Public' is checked", Elements.getElementAttribute("new_create_registry.private_profile", "class").contains("checked"));
        else//Is checked
            Assert.assertTrue("ERROR: Checkbox 'Make Registry Public' is not checked", Elements.getElementAttribute("new_create_registry.private_profile", "class").contains("checked"));
    }


    @And("^I click on tooltip icon$")
    public void I_click_on_tooltip() throws Throwable{
        Assert.assertTrue("ERROR: Tooltip icon isn't present to be clicked",Clicks.clickIfPresent("new_create_registry.private_profile_tooltip"));
    }


    @And("^I should see the tooltip overlay$")
    public void I_Should_See_The_Tooltip_Overlay() throws Throwable{
        Assert.assertTrue("ERROR: Private profile information overlay is not visible", Elements.elementPresent("new_create_registry.private_profile_info_overlay"));
    }


    @And("^I navigate to Event Details card of Create Registry form as a (guest|registered) user$")
    public void I_Navigate_To_Event_Details_Card_Of_Create_Registry_Form_as_User(String type_user) throws Throwable{
        I_Navigate_To_Registry_Create_Page_From_Registry_HomePage();
        if(type_user.equals("guest"))
            I_Fill_In_Create_Form_Up_To_Event_Details_Page();
        else {
            Wait.forPageReady();
            Assert.assertTrue("ERROR: Event Details page is not displayed.", Elements.elementPresent("new_create_registry.section_event_details"));
        }
    }

    @And("^I (check|uncheck) the \"Make Registry Public\" checkbox on Event Detail Card$")
    public void I_check_the_make_registry_public_checkbox_on_event_detail_card(String checkboxStatus) throws Throwable{
        Wait.forPageReady();
        registryActions.selectPublicRegistryCheckbox();

        if (checkboxStatus.equals("uncheck"))
            Assert.assertFalse("ERROR: Checkbox 'Make Registry Public' is checked", registryActions.isPrivateProfileChecked());
        else
            Assert.assertTrue("ERROR: Checkbox 'Make Registry Public' is not checked", registryActions.isPrivateProfileChecked());

    }

    @Given("^I visit the website as a bvr user with (private|public) registry using rest services$")
    public void I_Visit_The_Website_As_User_With_Private_Registry_Using_Rest_Services(String registryType) throws Throwable {
        registryActions.createRegistryBVR(registryType);
    }



    @When("^I click on \"Make It Public\" link on \"Make Registry Public\" banner$")
    public void I_click_on_make_it_public_link_on_make_registry_public_banner() throws Throwable{
        Assert.assertTrue("ERROR: \"Make It Public\" link is not present", Clicks.clickIfPresent("registry_bvr.alert_make_it_public_link"));
    }

    @And ("^I verify that \"Make Registry Public\" banner is displayed on the BVR page with the following text:$")
    public void I_verify_that_make_registry_public_banner_is_displayed_on_the_BVR_page_with_the_following_text(String message) throws Throwable{
        registry.iNavigateToBvrPage();
        Assert.assertTrue("ERROR: Banner text is not the expected", Elements.findElement("registry_bvr.alert_make_it_public_banner").getText().contains(message));
    }

    @Then ("^I should land on Edit Registry Profile page$")
    public void I_should_land_on_edit_registry_profile_page() throws Throwable{
        Assert.assertTrue("Error: Edit Registry Profile page is not displayed ", onPage("edit_registry_redesign"));
    }

    @And ("^I verify that registry is (private|public) by making a REST call$")
    public void I_verify_that_registry_is_public_by_making_a_rest_call(String regType) throws Throwable{
        if(regType.equals("public"))
            Assert.assertTrue("ERROR: Registry is not public.", registryActions.isPublicRegistry());
        else
            Assert.assertFalse("ERROR: Registry is not private.", registryActions.isPublicRegistry());
    }

    @And("^I continue to fill in create form until I reach Registry Manager page$")
    public void I_continue_to_fill_in_create_form_until_i_reach_registry_manager_page() throws Throwable{
        registryActions.continueToFillInCreateForm();

        Wait.secondsUntilElementPresent("new_create_registry.manage_registry_button", 10);
        Clicks.clickIfPresent("new_create_registry.manage_registry_button");

        Wait.forPageReady();

        Assert.assertTrue("Error: Registry Manager page is not displayed ", onPage("registry_manager"));
    }
}
