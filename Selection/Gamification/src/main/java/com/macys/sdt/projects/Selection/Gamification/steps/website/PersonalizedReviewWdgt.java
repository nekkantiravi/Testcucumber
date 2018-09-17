package com.macys.sdt.projects.Selection.Gamification.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class PersonalizedReviewWdgt extends StepUtils {

	@Then("^I navigate to home page$")
	public static void I_navigate_to_home_page() throws Throwable {
		boolean mHome = Elements.elementPresent("olapic.macys_home_page");
		if (mHome) {
			try {
				Thread.sleep(5000);
				Clicks.click("olapic.macys_home_page");
				Thread.sleep(5000);
				Assert.assertTrue("Able to redirect successfully to home page", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Failed to load home page" + e);
			}
		}
	}

	@Then("^I scroll down the Michael Kors Handbags search results$")
	public static void iScrollDowntheMKHSearchResults() throws Throwable {
		Thread.sleep(10000);
		if (Elements.elementPresent("olapic.personalized_search_landing")) {
			scrollToLazyLoadElement("olapic.personalized_search_landing");
			Assert.assertTrue("Scrolldown the Polarized Sunglasses for Women search page successfully", true);
			Thread.sleep(5000);
		} else {
			Assert.fail("Polarized Sunglasses for Women search page element did not find");
		}
	}

	@When("^I see personalized review widget$")
	public static void iSeePersonalizedReviewWidget() throws Throwable {
		boolean pRewviWidgContainer = Elements.elementPresent("olapic.personalized_Widget_Containerclas");
		if (pRewviWidgContainer) {
			Assert.assertTrue("Personalized review widget container has been found", true);
		} else {
			Assert.fail("Personalized review widget container not been found");
		}
	}

	@And("^I click on write a review link$")
	public static void iClickOnWriteaReviewLink() throws Throwable {

		boolean ePWL = Elements.elementPresent("olapic.personalized_writearevlink");

		if (ePWL) {
			Clicks.click("olapic.personalized_writearevlink");
			Assert.assertTrue("Able to click on Write a Review Link on PRWContainer", true);
			Thread.sleep(10000);
		} else {
			Assert.fail("Unable to identify write a review link on PRW");
		}
	}

	@Then("^I see order history link on myaccount page$")
	public static void iSeeOrderHistoryLink() throws Throwable {
		Elements.elementPresent("olapic.personalized_view_order_histy");
		Assert.assertTrue("Able to see order history link on myaccount page", true);
		Thread.sleep(8000);
	}

	@When("^I search for handbags$")
	public static void iSearchForHandbags() throws Throwable {
		Thread.sleep(10000);
		WebElement element = Elements.findElement("olapic.global_search_input_field");
		element.sendKeys("michael kors coats");
		Thread.sleep(15000);
		Clicks.click("olapic.sub_nav_search_button");
		Thread.sleep(15000);

	}

}
