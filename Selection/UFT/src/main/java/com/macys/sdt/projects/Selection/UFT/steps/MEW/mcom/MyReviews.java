package com.macys.sdt.projects.Selection.UFT.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyReviews {
    private static final Logger log = LoggerFactory.getLogger(MyReviews.class);

    /**
     * Method to confirm the user on My Reviews page
     */
    @And("^I verify that I am on my reviews page")
    public void confirmUser_on_myReviewPage() throws Throwable {
        Wait.untilElementPresent("myreviews.review_headline");
        Assert.assertTrue("The user is not on My Reviews page ", Elements.elementPresent("myreviews.review_headline"));
        log.info("Verified that the user is navigated to My Reviews page");
    }
    /**
     * Method to enter Review Headline, Description and Nickname
     */
    @Then("^I enter the Review Headline Review Text & Review NickName$")
    public void enterReviewDetails(DataTable table) throws Throwable {
        List<List<String>> data = table.raw();
        String reviewTitle = data.get(1).get(0);
        String reviewDescription = data.get(2).get(0);
        String reviewNickname = data.get(3).get(0);
        WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        TextBoxes.typeTextNEnter("myreviews.review_headline", reviewTitle);
        TextBoxes.typeTextNEnter("myreviews.review_texts", reviewDescription);
        TextBoxes.typeTextNEnter("myreviews.review_nickname", reviewNickname);
        log.info("Verified that Review details are filled successfully");
    }

    /**
     * Method to select Review Recommendation option
     */
    @Then("^I select the recommendation option$")
    public void select_the_Recommendation() throws Throwable {
        Wait.untilElementPresent("myreviews.review_yes");
        Clicks.click("myreviews.review_yes");
        log.info("Verified that the user clicked on the 'yes' radio button");
    }

    /**
     * Method to select Review Rating
     */
    @And("^I select an average rating rating value")
    public void selectRatingValue() throws Throwable {
        Wait.untilElementPresent("myreviews.review_rating");
        Clicks.click("myreviews.review_rating");
        log.info("Verified that review ratings are filled successfully");
    }

    /**
     * Method to click on Review Submit button
     */
    @And("^I click on the Review Submit button$")
    public void I_click_on_Review_Submit_button() throws Throwable {
        Wait.untilElementPresent("myreviews.review_submit");
        Clicks.click("myreviews.review_submit");
        log.info("Verified that the user clicked on the Submit button");
    }

    /**
     * Method to validate the submission of Review
     */
    @Then("^I should see the message contains \"([^\"]*)\" on my reviews page$")
    public void I_should_see_message_on_myReviewsPage(String expectedMsg) throws Throwable
    {
        Assert.assertTrue("Review did not submitted successfully", Elements.getText("myreviews.review_submitted").contains(expectedMsg));
        log.info("Verified that the customer review got submitted successfully");
    }
}

