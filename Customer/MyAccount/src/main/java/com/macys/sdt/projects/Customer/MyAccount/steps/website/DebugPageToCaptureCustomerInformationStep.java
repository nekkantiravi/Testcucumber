package com.macys.sdt.projects.Customer.MyAccount.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DebugPageToCaptureCustomerInformationStep extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(DebugPageToCaptureCustomerInformationStep.class);

    @Then("^I should see feedback page is as expected$")
    public void iShouldSeeFeedbackPageIsAsExpected() throws Throwable {
        onPage("customer_feedback");
        logger.info("Successfully verified the feedback page");
    }

    @When("^I enter the below text in the feedback text area$")
    public void iEnterTheBelowTextInTheFeedbackTextArea(List<String> feedback) throws Throwable {
        TextBoxes.typeTextbox("customer_feedback.feedback", feedback.get(0));
        logger.info("Entered the given text in the feedback text form.");
    }

    @When("^I submit the feedback in the customer feedback page$")
    public void iSubmitTheFeedbackInTheCustomerFeedbackPage() throws Throwable {
        Clicks.click("customer_feedback.submit_feedback");
        logger.info("Successfully submitted the feedback in the customer feedback page.");
    }

    @Then("^I should see the following customer thank you message$")
    public void iShouldSeeTheFollowingCustomerThankYouMessage(List<String> message) throws Throwable {
        Wait.secondsUntilElementPresent("customer_feedback.success_message", 30);
        logger.info("Success message is: " + Elements.getText("customer_feedback.success_message"));
        Assert.assertTrue("", Elements.getText("customer_feedback.success_message").equals(message.get(0)));
        logger.info("Successfully verified the thanks you message in the feedback page.");
    }

    @And("^I should see comment is cleared$")
    public void iShouldSeeCommentIsCleared() throws Throwable {
        logger.info("Text in feexback text area after submitting the feexback is: " + Elements.getText("customer_feedback.feedback"));
        Assert.assertTrue("", Elements.getText("customer_feedback.feedback").equals(""));
        logger.info("Feexback text area Successfully cleared in the feedback page.");
    }

    @When("^I cancel the comment on customer feedback page$")
    public void iCancelTheCommentOnCustomerFeedbackPage() throws Throwable {
        Clicks.click("customer_feedback.cancel");
        logger.info("Comment successfully cancelled in the feedback page.");
    }

    @And("^I should see feedback text area$")
    public void iShouldSeeFeedbackTextArea() throws Throwable {
        Assert.assertTrue("ERROR - ENV: feedback text box is not visible.", Elements.elementPresent("customer_feedback.feedback"));
        logger.info("Successfully verified the feedback text box.");
    }
}