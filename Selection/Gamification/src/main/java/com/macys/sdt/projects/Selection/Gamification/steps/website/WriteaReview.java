package com.macys.sdt.projects.Selection.Gamification.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.NoSuchElementException;

public class WriteaReview extends StepUtils {
	public static String strProdName = null;
	public static String strRevCon = null;
	public static String strIsRecomm = null;
	public static String strProdidElement = null;
	public static String ProdIdFromPDP = null;

	@When("^I click on write a review on PDP$")
	public static void iclickOnwriteareviewonPDP() throws Throwable {

		Thread.sleep(2000);
		scrollToLazyLoadElement("writeareview.write_rev_whenexis_reviews");
		Wait.secondsUntilElementPresent("writeareview.myreviews_prodid", 20);
		String strProdidElement = Elements.findElement(Elements.element("writeareview.myreviews_prodid")).getText();
		System.out.println("product id **************" + strProdidElement);
		ProdIdFromPDP = strProdidElement.split(":")[1].trim();
		System.out.println("product id **************" + ProdIdFromPDP);
		boolean pdpWrb = Elements.elementPresent("writeareview.write_a_review_no_reviews");
		if (pdpWrb) {
			Clicks.click("writeareview.write_a_review_no_reviews");
			Assert.assertTrue("Able to find a write a review element", pdpWrb);
		} else if (Elements.elementPresent("writeareview.write_rev_whenexis_reviews")) {
			Wait.secondsUntilElementPresent("writeareview.write_rev_whenexis_reviews", 30);
			Clicks.click("writeareview.write_rev_whenexis_reviews");
			Assert.assertTrue("Able to find a write a review element", true);
		} else {
			Assert.fail("Doesn't find the write a review button");
		}
	}

	@And("^I select star rating and filled review form$")
	public static void ISelectStarRatingAndFilledReviewForm() throws Throwable {
		Wait.secondsUntilElementPresent("writeareview.myreview_war_prdname", 30);
		strProdName = Elements.findElement(Elements.element("writeareview.myreview_war_prdname")).getText();
		if (Elements.elementPresent("writeareview.write_a_review_starrating")) {
			Clicks.click("writeareview.write_a_review_starrating");
			Assert.assertTrue("Able to select star rating for review", true);
		} else {
			Assert.fail("Doesn't find the stars object on my reviews");
		}
		Assert.assertTrue("Error - Unable to identify the star rating element on my review page",
				Elements.elementPresent("writeareview.write_a_review_revhead"));
		TextBoxes.typeTextbox(Elements.element("writeareview.write_a_review_revhead"), "great product");
		Assert.assertTrue("Error - Unable to identify the review header element",
				Elements.elementPresent("writeareview.write_a_review_body"));
		TextBoxes.typeTextbox(Elements.element("writeareview.write_a_review_body"),
				"great product !@!#!@@#$#% %^&^*&&)&) <><?{}{| great fun to use recommended for all friends to use it with caution");
//		Assert.assertTrue("Error - Unable to identify the review nick name element",
//				elementPresent("writeareview.write_a_review_nickname"));
		if(Elements.elementPresent("writeareview.write_a_review_nickname")){
			TextBoxes.typeTextbox(Elements.element("writeareview.write_a_review_nickname"), "Rama");
		}
		
	}

	@And("^I select is recommended as yes$")
	public static void iSelectIsRecommendedYes() throws Throwable {
		Thread.sleep(3000);
		try{
			Clicks.click(Elements.paramElement("writeareview.myreviews_isrecommended_Yes", ProdIdFromPDP));
		}catch(NoSuchElementException ex){
			Assert.fail("Unable to click on Is recommended radio button YES" +ex);
			
		}
		
	}

	@And("^I select is recommended as no$")
	public static void iSelectIsRecommendedNo() throws Throwable {
		Thread.sleep(3000);
		try{
			Clicks.click(Elements.paramElement("writeareview.myreviews_isrecommended_No", ProdIdFromPDP));
		}catch(NoSuchElementException ex){
			Assert.fail("Unable to click on Is recommended radio button No" +ex);
		}
		
	}

	@And("^I click on review submit$")
	public static void iClickOnReviewSubmit() throws Throwable {
		Thread.sleep(2000);
		if (Elements.elementPresent("writeareview.write_a_review_submit")) {
			Clicks.click("writeareview.write_a_review_submit");
			Assert.assertTrue("Able to submit the review successfully", true);
			Thread.sleep(2000);
		} else if (Elements.elementPresent("writeareview.write_a_review_submit_Optional_Sliders")) {
			Clicks.click("writeareview.write_a_review_submit_Optional_Sliders");
		} else {
			Assert.fail("Doesn't find the submit button");
		}
	}

	@And("^I see confirmation message for myreviews$")
	public static void iSeeConfirmationMessageForMyreviews() throws Throwable {
		Wait.secondsUntilElementPresent("writeareview.review_confirmation", 30);
		boolean revConf = Elements.elementPresent("writeareview.review_confirmation");
		if (revConf) {
			Assert.assertTrue("Able to see reviews confirmation successfully", revConf);
			strRevCon = Elements.findElement(Elements.element("writeareview.review_confirmation")).getText();
			Assert.assertEquals("Thanks for your review of " + strProdName, strRevCon);
		}
	}

}
