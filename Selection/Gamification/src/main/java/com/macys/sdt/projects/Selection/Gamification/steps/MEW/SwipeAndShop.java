package com.macys.sdt.projects.Selection.Gamification.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeAndShop extends StepUtils {

	@And("^I tab on swipe$")
	public static void iTabOnSwipe() {

		if (Elements.elementPresent("swipeandshop.mew_swipe_click")) {
			Clicks.click("swipeandshop.mew_swipe_click");
		}
		if (Elements.elementPresent("swipeandshop.mew_swipe_click")) {
			Clicks.click("swipeandshop.mew_swipe_click");
		}
	}

	@Then("^I see Swipe and Shop experience to select like and dislike products$")
	public static void iSeeSwipeAndShopExperienceToSelectLikeAndDislikeProducts() throws Throwable {
		Thread.sleep(15000);
		if (Elements.elementPresent("swipeandshop.mew_left_notmystyle")) {
			Clicks.click("swipeandshop.mew_left_notmystyle");
		}
		List<String> productIdList = new ArrayList<>();
		Map<String, String> productidMap = new HashMap<>();
		for (int i = 0; i < 2; i++) {
			if (Elements.elementPresent("swipeandshop.mew_right_mystyle")) {
				productIdList
						.add(Elements.findElement("swipeandshop.mew_swipe_shop_firstid" + 1).getAttribute("data-product_id"));
				Clicks.click("swipeandshop.mew_right_mystyle");
			}
		}
		if (Elements.elementPresent("swipeandshop.mew_swipe_mylist")) {
			Clicks.click("swipeandshop.mew_swipe_mylist");
		}
		if (Elements.elementPresent("swipeandshop.mew_list_element")) {
			List<WebElement> listWebElements = Elements.findElements("swipeandshop.mew_list_element");
			for (WebElement listWebElement : listWebElements) {
				String productIdStr = listWebElement.getText();
				String productId = productIdStr.split(":")[1].trim();
				productidMap.put(productId, productId);
				System.out.println("######:" + productId);
			}
		}
		/*
		 * for(String productIdFromList:productIdList){ System.out.println(
		 * "product id in list:"+ productIdFromList ); } for(String
		 * key:productidMap.keySet()){ System.out.println("product in map: "
		 * +key); }
		 */
		for (String productIdFromList : productIdList) {
			Assert.assertTrue(productidMap.containsKey(productIdFromList));
			Assert.assertTrue("Products from Swipe and List are matching", true);
			System.out.println("From S & s:" + productIdFromList);
			System.out.println("From list: " + productidMap);
		}
	}

	@And("^I tab on back button to see Swipe$")
	public static void iTabOnMyListBackButtonToSeeSwipe() throws Throwable {

		if (Elements.elementPresent("swipeandshop.mew_back_button")) {
			Clicks.click("swipeandshop.mew_back_button");
			// Assert.assertTrue("ERROR-ENV: Doesn't work back button on my
			// list/swipe",
			// untilElementPresent("swipeandshop.mew_pdp_info"));

			if (Elements.elementPresent("swipeandshop.mew_pdp_info")) {
				Assert.assertTrue("Successfully tab on back button and navigate to swipe", true);
			} else if ((Elements.elementPresent("swipeandshop.mew_swipe_click"))) {
				Assert.assertTrue("Able to redirect to Browse/Search page by tabbing back button on swipe", true);
			} else {
				Assert.fail("Doesn't find the back button");

			}
		}
	}
	@When("^I tab on info icon and I see PDP$")
	public static void iTabOnInfoIconAndISeePDP() throws Throwable {

		if (Elements.elementPresent("swipeandshop.mew_pdp_info")) {
			Clicks.click("swipeandshop.mew_pdp_info");
			Assert.assertTrue("Able to tab on info button on swipe", true);

		} else {
			Assert.fail("Doesn't find the info button on Swipe ");
		}
		scrollToLazyLoadElement("swipeandshop.mew_add_to_bag");
		Wait.secondsUntilElementPresent("swipeandshop.mew_add_to_bag", 10);
		if (Elements.elementPresent("swipeandshop.mew_add_to_bag")) {
			Assert.assertTrue("Able to see add to bag on PDP successfully", true);
			System.out.println("Able to see add to bag on PDP successfully");
		}
	}
	
	@Then("^I navigate to see swipe and I tab back button on swipe$")
	public static void iNavigateToSeeSwipeAndITabBackButtonOnSwipe() throws Throwable{
		WebDriverManager.getWebDriver().navigate().back();
		iTabOnMyListBackButtonToSeeSwipe();
	}

}
