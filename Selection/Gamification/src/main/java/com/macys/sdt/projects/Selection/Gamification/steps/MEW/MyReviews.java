package com.macys.sdt.projects.Selection.Gamification.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class MyReviews extends StepUtils {

	public static String strMeWProdtName = null;
	public static String strRevConfirmation = null;

	@Then("^I see write a review button on PDP page and click on it$")
	public static void I_see_write_a_review_button_on_PDP_page() throws Throwable {
		Thread.sleep(2000);
		scrollToLazyLoadElement("myreviews.mew_write_review_xpath");
		Thread.sleep(3000);
		if (Elements.elementPresent("myreviews.mew_write_review_xpath")) {
			Clicks.click(Elements.element("myreviews.mew_write_review_xpath"));
		} else if (Elements.elementPresent("myreviews.mew_write_review_xpath2")) {
			Clicks.click(Elements.element("myreviews.mew_write_review_xpath2"));
		} else if (Elements.elementPresent("myreviews.mew_write_review_xpath3")) {
			Clicks.click(Elements.element("myreviews.mew_write_review_xpath3"));
		} else {
			Assert.fail("Doesn't find write a review button");
		}
	}

	@When("^I see my reviews page and fill the form$")
	public static void iSeeWriteaReviewButtonOnPDPandClickOnIt() throws Throwable {
		Thread.sleep(2000);
		strMeWProdtName = Elements.findElement(Elements.element("myreviews.mew_review_confirmation")).getText();
		System.out.println("Product name: " + strMeWProdtName);
		Assert.assertTrue("Error - Env: Unable to identify the star rating element on my review page",
				Elements.elementPresent("myreviews.mew_review_star"));
		Clicks.click("myreviews.mew_review_star");
		Assert.assertTrue("Error - Env: Unable to identify the review header textbox on my review page",
				Elements.elementPresent("myreviews.mew_reviewtitle"));
		TextBoxes.typeTextbox(Elements.element("myreviews.mew_reviewtitle"), "great product");
		Assert.assertTrue("Error - Env: Unable to identify the review body textbox on my review page",
				Elements.elementPresent("myreviews.mew_reviewbody"));
		TextBoxes.typeTextbox(Elements.element("myreviews.mew_reviewbody"),
				"great product great fun to use recommended for all friends to use it with caution");
		Assert.assertTrue("Error - Env: Unable to identify the review nick name textbox on my reviews page",
				Elements.elementPresent("myreviews.mew_reviewnick_name"));
		TextBoxes.typeTextbox(Elements.element("myreviews.mew_reviewnick_name"), "Rama");
	}

	@And("^I tab on submit button to submit the review$")
	public static void iTabOnSubmitButtonToSubmitTheReview() throws Throwable {
		Clicks.click("myreviews.mew_submit_review");
	}

	@Then("^I see successfull confirmation message$")
	public static void iSeeSuccessfullUserDefinedMessage() throws Throwable {
		boolean revConfMsg = Elements.elementPresent("myreviews.mew_review_confirmation");

		if (revConfMsg) {
			Assert.assertTrue("Able to see reviews confirmation successfully", revConfMsg);
			strRevConfirmation = Elements.findElement("myreviews.mew_review_confirmation").getText();
			Assert.assertEquals("Thanks for your review of " + strMeWProdtName, strRevConfirmation);
		}
	}

	@When("^I click on back button$")
	// @And("^I click on back button$")
	public static void iClickOnBackButtonAfterReviewSubmition() throws Throwable {

		if (Elements.elementPresent("myreviews.mew_review_submit_back")) {
			Clicks.click("myreviews.mew_review_submit_back");
			Assert.assertTrue("Able to click on back button successfully", true);
		} else {
			Assert.fail("Doesn't find the back button after review submission");
		}
	}

	@Then("^I see add to bag and write a review button$")
	public static void iSeeAddToBagAndWriteaReviewButton() throws Throwable {

		if (Elements.elementPresent("myreviews.mew_afterback_addtobag")) {
			Assert.assertTrue(
					"Able to find addtobag element when user click on back button after successful review submission",
					true);
		} else {
			Assert.fail("Doesn't find addtobag on PDP page");
		}
		I_see_write_a_review_button_on_PDP_page();
	}
}
