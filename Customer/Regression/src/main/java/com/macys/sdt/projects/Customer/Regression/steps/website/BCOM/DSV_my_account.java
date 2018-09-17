package com.macys.sdt.projects.Customer.Regression.steps.website.BCOM;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import static com.macys.sdt.framework.utils.StepUtils.prodEnv;

/**
 * Created by u065629 on 5/25/2017.
 */
public class DSV_my_account{

    @And("^I change location on my preferred store Section on the My Account Page$")
    public void I_change_location_on_prefered_store_section_on_my_account_page(){
      Clicks.click("my_account.change_location");
        Assert.assertTrue("Overlay is not diplayed", Elements.anyPresent("my_account.change_store_overlay"));
    }

    @And("^I select a store from the drop down on the Choose a Preferred Store Overlay$")
    public void I_select_store_from_drop_down(){
       DropDowns.selectCustomValue("preferred_store_overlay.store_selector","preferred_store_overlay.store_list",7);
    }

    @Then("^I verify that at-least one store is displayed in the results$")
    public void I_verify_that_at_least_one_store_displayed_in_results(){
        Wait.untilElementPresent("preferred_store_overlay.store_container");
        int store_count = Elements.findElements("preferred_store_overlay.store_container").size();
        Assert.assertTrue("Stores are less than 1",store_count>=1);
    }

    @When("^I sign-in to my existing profile if in prod else I create a new profile$")
        public void I_sign_in_to_my_existing_profile_if_in_production_else_I_create_a_new_profile() throws Throwable {
        PageNavigation nav = new PageNavigation();
        MyAccountSteps account = new MyAccountSteps();
        if (prodEnv()) {

                nav.I_visit_the_web_site_as_a_guest_user();
                account.iClickOnSignInLink();
                account.iVerifyUserIsOnSignInPage();
                account.iSignInToMyExistingProductionProfile();
       }
        else
                nav.I_visit_the_web_site_as_a_registered_user();
    }

    @And("^I find by zipcode on the Choose a Preferred Store Overlay$")
    public void I_find_by_zipcode_on_the_Choose_a_Preferred_Store_Overlay(){
        TextBoxes.typeTextbox("preferred_store_overlay.zip_code","10022");
        Clicks.click("preferred_store_overlay.search_button");
    }

    @And("^I select the first store$")
    public void I_select_the_first_store(){
        Wait.untilElementPresent("preferred_store_overlay.store_container");
        Clicks.click("preferred_store_overlay.select_button_store");
    }

    @Then("^I (should|should not) see Choose a Preferred Store overlay$")
    public void I_should_see_Choose_preferred_store_overlay(String condition){
        switch(condition){
            case "should": Elements.elementShouldBePresent("my_account.change_store_overlay"); break;
            case "should not": Assert.assertTrue("Overlay still exists",!Elements.isElementInView(Elements.findElement("my_account.change_store_overlay"))); break;
            }
    }

    @And("^I should verify that the selected store details are displayed in the my preferred store Section on My Account Page$")
    public void I_should_verify_that_the_selected_store_details_are_displayed_my_preferred_store() throws Throwable{
        Wait.secondsUntilElementPresent("my_account.my_account_store_name", 20);
        Assert.assertTrue("Store details are not displayed", Elements.elementPresent("my_account.my_account_store_name"));
    }

    @And("^I select the other store$")
    public void I_select_the_other_store(){
        Wait.secondsUntilElementPresent("preferred_store_overlay.select_store",20);
        Clicks.javascriptClick("preferred_store_overlay.select_store");
    }

    @Then("^I verify that My Account dashboard is rendered properly$")
    public void iVerifyThatMyAccountDashboardIsRenderedProperly()  {
        Assert.assertTrue("Bloomingdale's Credit Card header is not displayed",Elements.elementPresent("new_my_account.credit_card_header"));
        Assert.assertTrue("Orders header is not displayed", Elements.elementPresent("new_my_account.orders_header"));
        Assert.assertTrue("ORDER STATUS & HISTORY button is not displayed", Elements.elementPresent("new_my_account.orders_status_n_history"));
        Assert.assertTrue("Loyallist header is not displayed", Elements.elementPresent("new_my_account.loyallist_header"));
        Assert.assertTrue("bWallet header is not displayed", Elements.elementPresent("new_my_account.wallet_header"));
        Assert.assertTrue("Orders header is not displayed", Elements.elementPresent("new_my_account.orders_header"));
        Assert.assertTrue("MY bWALLET button is not displayed", Elements.elementPresent("new_my_account.wallet_footer_wallet_link"));
        Assert.assertTrue("Wish Lists header is not displayed", Elements.elementPresent("new_my_account.lists_header"));
        Assert.assertTrue("CREATE AND MANAGE LISTS button is not displayed", Elements.elementPresent("new_my_account.create_lists_link"));
    }
}
