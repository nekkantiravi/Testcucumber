package com.macys.sdt.projects.Selection.Gamification.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.NoSuchElementException;

public class StatsTray extends StepUtils {
	@Then("^I see stats tray on myaccount page$")
	public static void I_see_stats_tray_on_myaccount_page() throws Throwable {
		if (Elements.elementPresent("olapic.stats_tray_on_myacc")) {
			Assert.assertTrue("Stats Tray has been found successfully", true);
		} else {
			Assert.fail("Stats Tray has not been found on myaccount page");
		}
	}

	@And("^I see statstray info about stars$")
	public static void iSeeStatstrayInfoAboutStars() throws Throwable {
		if (Elements.elementPresent("olapic.macys_infostar"))
			try {
				Assert.assertTrue("Able to see macys stars info symbol successfully", true);
				// click("olapic.macys_infostar");
			} catch (NoSuchElementException ex) {
				Assert.fail("Doesn't find macys starts info on stats try:" + ex);
			}
		else {
			System.out.println("info symbol returning false");
			Assert.fail("Macys stars info symbol not been found");
		}
	}

	/*
	 * click("olapic.macys_infostar_close"); Thread.sleep(5000);
	 */
	@When("^I click on Write a review$")
	public static void iClickOnWriteAReviewLin() throws Throwable {

		boolean statsTray = Elements.elementPresent("olapic.stats_tray_writeareview");
		if (statsTray) {
			try {
				Assert.assertTrue("WriteaReview link has ben found successfully", true);
				Clicks.click("olapic.stats_tray_writeareview");
				Assert.assertTrue("Able to click WriteaReview link successfually", true);
			} catch (NoSuchElementException ex) {
				Assert.fail("WriteAReview link has not been found" + ex);
			}
		} else {
			Assert.fail("WriteAReview link has not been found");
		}
	}

	@Then("^I see myreviews page$")
	public static void iSeeMyreviewsPage() throws Throwable {

		Assert.assertTrue("Able to see myreviews page successfully", Elements.elementPresent("olapic.my_reviews_for_wrtrev"));
		String myRevMsg = Elements.findElement("olapic.my_Reviews_noprods_to_reviews").getText();
		String myRevActMsg = "You have no items to review. Shop great deals now!";
		if (myRevMsg.equals(myRevActMsg)) {
			System.out.println("myreviews msg: " + myRevMsg);
			Assert.assertTrue(myRevMsg, true);
		} else {
			Assert.fail("Not displaying user defined message - You have no items to review. Shop great deals now!");
		}
	}

	@When("^I click on myaccount$")
	public static void iClickOnMyAccount() throws Throwable {
		Navigate.visit("home");
		boolean retnOrderHist = Elements.elementPresent("olapic.ststray_myaccount_link");
		if (retnOrderHist) {
			Clicks.click("olapic.ststray_myaccount_link");
			Assert.assertTrue("Able to click on myaccount link successfully", true);
		} else {
			Assert.fail("Did not find myaccount link to click");
		}
	}

	@Then("^I see order history or Myreviews page$")
	public static void iSeeOrderHistoryMyReviewsPage() throws Throwable {
		Thread.sleep(3000);
		if (Elements.elementPresent("olapic.stats_order_histy")) {
			Assert.assertTrue("Able to see order history page successfully", true);
		} else if (Elements.elementPresent("olapic.stats_myreviews")) {
			Assert.assertTrue("Able to see myreviews page successfully", true);
			if (Elements.elementPresent("writeareview.write_a_review_starrating")){
				Assert.assertTrue("Able to see star rating for review", true);
			}else{
				Assert.fail("Doesn't find stars object on my reviews page");
			}
		} else {
			Assert.fail("Doesn't find myreviews page");
		}
	}

}
