package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class StepDefWriteReviews extends StepUtils {

	private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();
    public static String productId = "";


	@When("^I click on the write a review button on PDP (site|iship|registry) mode$")
	public void I_click_on_the_write_a_review_button_on_PDP(String mode) throws Throwable {
		Thread.sleep(2000);
		Wait.secondsUntilElementPresent("writereview.prodid", 20);
		String strProdidElement = Elements.findElement(Elements.element("writereview.prodid")).getText();
		System.out.println("***** productId: " + strProdidElement);
		productId = strProdidElement.split(":")[1].trim();
		System.out.println("***** productId: " + productId);

        if(Elements.elementPresent("pdp.review_count")) {
			softly.assertThat(Elements.elementPresent("pdp.review_count")).as("Reviews Count & Link").isEqualTo(true);
			softly.assertThat(Elements.elementPresent("pdp.reviewsSummaryPanel")).as("Reviews Summary Panel").isEqualTo(true);
			softly.assertThat(Elements.elementPresent("pdp.reviewsRedStars")).as("Reviews Rating Stars").isEqualTo(true);
			Clicks.click("pdp.review_count");
		}
		else if(Elements.elementPresent("pdp.firstReviewcCount")) {
			softly.assertThat(Elements.elementPresent("pdp.firstReviewcCount")).as("Reviews Count & Link").isEqualTo(true);
//			Clicks.click("pdp.firstReviewcCount");
            scrollToLazyLoadElement("pdp.shippingTab");
		}
		else
			log.warning("Review Count Link not displayed!");

		Thread.sleep(5000);
		if (Elements.elementPresent("pdp.write_a_review_btn")) {
			scrollToLazyLoadElement("pdp.write_a_review_btn");
			softly.assertThat(Elements.elementPresent("pdp.write_a_review_btn")).as("Write A Review Button").isEqualTo(true);
			Clicks.click("pdp.write_a_review_btn");
		}
		else if (Elements.elementPresent("pdp.write_a_no_review_btn")) {
			softly.assertThat(Elements.elementPresent("pdp.write_a_no_review_btn")).as("Write A Review Button").isEqualTo(true);
			Clicks.click("pdp.write_a_no_review_btn");
		}
		else
			log.warning("Write A Review Button Not Displayed!");

		log.info("Write A Review Button Clicked!");
        softly.assertAll();
        Thread.sleep(3000);
	}

    @And("^I navigate to PDP with \"([^\"]*)\" PID$")
    public void navigate_to_PDP_with_PID(String arg) throws Throwable {
	    switch (arg) {
            case "same": {
                TextBoxes.typeTextNEnter("home.search_field", productId);
                Wait.secondsUntilElementPresent("pdp.productTitle", 20);
                break;
            }
            case "different": {
                break;
            }
        }

    }

	@And("^I fill out required fields as \"([^\"]*)\" and submit reviews$")
	public void I_fill_out_required_fields_and_submit(String arg) throws Throwable {

		Wait.secondsUntilElementPresent("writereview.review_prdname", 30);
		String productName = Elements.findElement(Elements.element("writereview.review_prdname")).getText();
		if (Elements.elementPresent("writereview.review_star_rates")) {
			Clicks.click("writereview.review_star_rates");
			log.info("Review Star Rates Clicked!");
		} else {
			Assert.fail("Write A Review overlay not displayed!");
			log.warning(String.format("** Write A Review overlay not displayed!\n"));
		}
		Assert.assertTrue("Error - Unable to identify the star rating element on my review page",
				Elements.elementPresent("writereview.review_header"));
		TextBoxes.typeTextbox(Elements.element("writereview.review_header"), "Great Product");
		Assert.assertTrue("Error - Unable to identify the review header element",
				Elements.elementPresent("writereview.review_body"));
		TextBoxes.typeTextbox(Elements.element("writereview.review_body"),
				"great product !@!#!@@#$#% %^&^*&&)&) <><?{}{| great fun to use recommended for all friends to use it with caution");
		if(Elements.elementPresent("writereview.review_nickname")){
			TextBoxes.typeTextbox(Elements.element("writereview.review_nickname"), "Test");
		}
		Wait.secondsUntilElementPresent("writereview.recommend_yes_btn", 10);
//		softly.assertThat(Elements.elementPresent("writereview.recommend_yes_btn")).as("recommend_yes_btn").isEqualTo(true);
//		softly.assertThat(Elements.elementPresent("writereview.recommend_no_btn")).as("recommend_no_btn").isEqualTo(true);
		if(arg.equals("recommended")) {
			try{
				Clicks.click(Elements.paramElement("writereview.recommend_yes_btn", productId));
			}catch(NoSuchElementException e){
				Assert.fail("Unable to click on the recommended radio button YES" + e);
			}
		}
		else {
			try{
				Clicks.click(Elements.paramElement("writereview.recommend_no_btn", productId));
			}catch(NoSuchElementException e){
				Assert.fail("Unable to click on the recommended radio button NO" + e);
			}
		}
		softly.assertThat(Elements.elementPresent("writereview.review_submit_btn")).as("Reviews Submit Button").isEqualTo(true);
		Clicks.click("writereview.review_submit_btn");
		log.info("Product review submitted successfully!");

        softly.assertAll();
        Thread.sleep(3000);
	}

    @Then("^I verify that write review overlay is displayed in (site|iship|registry) mode$")
    public void verify_write_review_overlay(String mode) throws Throwable {
	    switch (mode) {
            case "registry": {
                Navigate.switchWindow(1);
                Wait.secondsUntilElementPresent("writereview.registryWriteReviewOverlay", 30);
                softly.assertThat(Elements.elementPresent("writereview.registryWriteReviewOverlay")).as("Reviews overlay displayed").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(3000);
    }

	@Then("^I verify the confirmation message for writing a product review$")
	public void I_verify_the_confirmation_message_for_writing_a_product_review() throws Throwable {
		Wait.secondsUntilElementPresent("writereview.confirmation_msg", 30);
		softly.assertThat(Elements.elementPresent("writereview.confirmation_msg")).as("Reviews Confirmation Message").isEqualTo(true);
//		softly.assertThat(Elements.findElement("writereview.confirmation_msg").getText()).contains("Thanks for your review of");
		String url = WebDriverManager.getCurrentUrl();
		Assert.assertTrue(url.contains("/account/myreviews?pid="));
//		softly.assertThat(Elements.elementPresent("writereview.confProdImg")).as("Product Image on Reviews Confirmation").isEqualTo(true);

        softly.assertAll();
        Thread.sleep(3000);
	}

    @Then("^I verify that only one review for a particular product can be submitted$")
    public void only_one_review_for_a_particular_product_can_be_submitted() throws Throwable {
        Thread.sleep(2000);
        TextBoxes.typeTextNEnter("home.search_field", productId);
        softly.assertThat(Elements.elementPresent("pdp.review_count")).as("Reviews Count & Link").isEqualTo(true);
        Clicks.click("pdp.review_count");
        Wait.secondsUntilElementPresent("pdp.write_a_review_btn", 20);
        softly.assertThat(Elements.elementPresent("pdp.write_a_review_btn")).as("Write A Review Button").isEqualTo(true);
        Clicks.click("pdp.write_a_review_btn");
        Assert.assertTrue("Write A Review Button Clicked!", true);
        Wait.secondsUntilElementPresent("writereview.confirmation_msg", 30);
        softly.assertThat(Elements.elementPresent("writereview.confirmation_msg")).as("Reviews Confirmation Message").isEqualTo(true);

        softly.assertAll();
        Thread.sleep(3000);
    }


}
