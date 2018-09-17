package com.macys.sdt.projects.Customer.MyAccountRedesign.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Customer.MyAccountRedesign.actions.website.NewMyAccount;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.projects.Customer.MyAccountRedesign.utils.TestData;
import com.macys.sdt.projects.Customer.MyAccountRedesign.utils.MyAccountUtils;

public class PlentiValidations extends StepUtils {

    @Given("^I have same plenti account associated to more than (\\d+) user accounts$")
    public void i_have_same_plenti_account_associated_to_more_than_user_accounts(int numberOfAccounts) throws Throwable {
        for(int i=0;i<numberOfAccounts;i++) {
            UserProfile user = new UserProfile();
            user = UserProfile.getDefaultProfile();
            UserProfileService.createUserProfile(user);
            new PageNavigation().i_goto_home_page();
            MyAccountUtils.signInAsExistingAccount(user);
            new MyAccountSteps().iNavigateToMyAccountPage();
            this.i_add_plenti_using_valid_plenti_card_number();
            this.i_should_see_card_info_in_the_plenti_widget();
            new MyAccountSteps().iSignOutFromMyCurrentProfile();
        }
    }

    @Then("^I should see plenti card elements$")
    public void i_should_see_plenti_card_elements() throws Throwable {
        NewMyAccount.verfiyDefaulUnlinkedtPlentiCardExists();
    }

    @Then("^I should see default message as:$")
    public void i_should_see_default_message_as(String expectedGuideMsg) throws Throwable {
        Assert.assertEquals("Expected Plenti guide message not match with Actual",expectedGuideMsg,NewMyAccount.getDefaultPlentiInstruction());
    }

    @Then("^I should see join reward program message:$")
    public void i_should_see_join_reward_program_message(String txt) throws Throwable {
        Assert.assertEquals("Expected plenti join reward text not exists",txt,NewMyAccount.getRewardsText());
    }

    @When("^I add plenti using valid plenti card number$")
    public void i_add_plenti_using_valid_plenti_card_number() throws Throwable {
        NewMyAccount.addPlentiByCardNumber(new TestData().getPlentiTestData().getRandomPartiallyEnrolled().getUslid());
    }

    @When("^I add plenti using valid phone number$")
    public void I_add_plenti_using_valid_phone_number() throws Throwable {
        NewMyAccount.addPlentiByPhoneNumber("1234567890");
    }

    @Then("^I should see card info in the plenti widget$")
    public void i_should_see_card_info_in_the_plenti_widget() throws Throwable {
        NewMyAccount.verifyPlentiEnrolled();
    }

    @When("^I add plenti using invalid phone (.*?)$")
    public void i_add_plenti_using_invalid_phone(String number) throws Throwable {
        NewMyAccount.addPlentiByPhoneNumber(number);
    }

    @When("^I add plenti using invalid card (.*?)$")
    public void i_add_plenti_using_invalid_card(String number) throws Throwable {
        NewMyAccount.addPlentiByCardNumber(number);
    }

    @Then("^I should see plenti error message as (.*?)$")
    public void i_should_see(String message) throws Throwable {
        Assert.assertEquals("Expected Error message NOT equal to actual error message",message,NewMyAccount.getErrorMessage());
    }

    @Then("^I should see error message as:$")
    public void i_should_see_error_message_as(String errorMsg) throws Throwable {
        Wait.until(() ->NewMyAccount.getErrorMessage().equalsIgnoreCase(errorMsg),10);
        Assert.assertEquals("Expected Error message NOT equal to actual error message",errorMsg,NewMyAccount.getErrorMessage());
    }

    @When("^I add plenti (\\d+) times using invalid phone number:$")
    public void i_add_plenti_times_using_invalid_phone_number(int count, String data) throws Throwable {
        for(int i=0;i<count;i++){
            NewMyAccount.addPlentiByPhoneNumber(data);
        }
    }

    @Then("^I should see add plenti button disabled$")
    public void i_should_see_add_plenti_button_disabled() throws Throwable {
        Assert.assertFalse("Add plenti button not disabled", Elements.findElement("new_my_account.plenti_link_account").getAttribute("class").contains("disabled"));
    }

}