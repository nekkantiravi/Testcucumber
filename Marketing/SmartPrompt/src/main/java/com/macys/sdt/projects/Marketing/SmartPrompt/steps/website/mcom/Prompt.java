package com.macys.sdt.projects.Marketing.SmartPrompt.steps.website.mcom;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.SmartPrompt.utils.SmartPromptUtils;
import com.macys.sdt.shared.steps.website.*;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.junit.Assert;


public class Prompt extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(Prompt.class);
    public String user_id;
    public String user_email;


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Prompt.class);
    public ProfileValidation cp = new ProfileValidation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    String url = RunConfig.url;

    @Given("^my online uid having Marketing Emails set to \"([^\"]*)\" is available in user_prompt table$")
    public void addUserIdToPromtTable(String marketingEmailState) throws Throwable {
        cp.createProfile();
        user_id = CommonUtils.getUserDetailsByEmail(cp.user_email);
        if (marketingEmailState.equalsIgnoreCase("Yes")) {
            SmartPromptUtils.setUserAsPrompt(user_id, true);
            log.info("user_id " + user_id + " inserted in to user prompt table with Email Subscription set to Y");
        } else if (marketingEmailState.equalsIgnoreCase("No")) {
            SmartPromptUtils.setUserAsPrompt(user_id, false);
            log.info("user_id " + user_id + " inserted in to user prompt table with Email Subscription set to N");
        }
    }

        @And("^I sign in with the same profile$")
    public void iSignInWithSameProfile() throws Throwable {
        Navigate.visit(url + "/account/signin?");
        TextBoxes.typeTextbox("sign_in.email", TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", TestUsers.getCustomerInformation().getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.sign_in_button");
        if (StepUtils.macys()) {
            if (Elements.elementPresent("sign_in.security_question")) {
                UserProfile customer = TestUsers.getCustomer(null);
                DropDowns.selectByText("sign_in.security_question", customer.getUser().getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("sign_in.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
                Clicks.click("sign_in.save_and_continue");
            }
        }
    }

    @Then("^I should see Smart Prompt popup$")
    public void iShouldSeeSmartPromptPopup() throws Throwable {
        Wait.secondsUntilElementPresent("userprompt.userprompt_overlay_popup", 120);
        Assert.assertTrue("user prompt popup is not displayed", Elements.elementPresent("userprompt.userprompt_overlay_popup"));
        log.info("User Prompt overlay is displayed");

    }

    @And("^I should see background screen \"([^\"]*)\"$")
    public void iShouldSeeBackgroundScreen(String disabled) throws Throwable {
        Wait.untilElementPresent(Elements.element("userprompt.prompt_background_freezed"));
        Assert.assertTrue("Prompt background screen is in disabled state", Elements.elementPresent("userprompt.userprompt_background_freezed"));
        logger.info("Background screen is disabled");

    }

    @And("^I should see pre-populated with the current email address in email field on popup$")
    public void iShouldSeePrePopulatedWithTheCurrentEmailAddressInEmailFieldOnPopup() throws Throwable {
        Wait.untilElementPresent(Elements.element("userprompt.populated_email_address"));
        Assert.assertTrue("Email address is not displayed", Elements.findElement("userprompt.populated_email_address").getAttribute("placeholder").contains(cp.user_email));
        logger.info("Email address prepopulated on smartprompt overlay");
    }


    @When("^I click on \"([^\"]*)\" button$")
    public void iClickOnButton(String select_yes_no_option) throws Throwable {
        Wait.forPageReady();
        switch (select_yes_no_option) {
            case "submit": {
                Assert.assertTrue("Email text field is not displayed", Elements.elementPresent("Userprompt.userprompt_email_textfield"));
                TextBoxes.typeTextbox("Userprompt.userprompt_email_textfield", "5nphismlgn060458@blackhole.macys.com");
                Assert.assertTrue("user prompt popup is displayed with submit button", Elements.elementPresent("Userprompt.userprompt_submit_button"));
                Clicks.click("Userprompt.userprompt_submit_button");
                log.info("Submit button is clicked on user prompt popup");
                break;
            }
            case "X": {
                Assert.assertTrue("user prompt popup is not displayed with X mark", Elements.elementPresent("Userprompt.userprompt_cancel_x_button"));
                Clicks.click("userprompt.userprompt_cancel_x_button");
                log.info("X button is clicked on user prompt popup");
                break;
            }
        }
    }


    @Then("^I should see the pop up disappear$")
    public void iShouldSeeThePopUpDisappear() throws Throwable {
        Assert.assertTrue("smart prompt popup not showing up", Wait.untilElementNotPresent("Userprompt.userprompt_overlay_popup"));
        log.info("Smartprompt popup is disapered");
    }

    @When("^I click on X close option at the top of the popup$")
    public void iClickOnXCloseOptionAtTheTopOfThePopup() throws Throwable {
        Wait.untilElementPresent("Userprompt.userprompt_cancel_x_button");
        Assert.assertTrue("Smartprompt X mark to close popup is not displayed", Elements.elementPresent("Userprompt.userprompt_cancel_x_button"));
        Clicks.click("Userprompt.userprompt_cancel_x_button");

    }
}