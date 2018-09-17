package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class CustomerSteps extends StepUtils {

	public static final String EXPECTED_FIND_CUSTOMER_TEXT = "Find Customer" ;
	public static final String EXPECTED_SWIPE_CUSTOMER_CARD_TEXT = "Swipe customer's card to identify best offers.";

	@Then("^I see the Customer Identification Screen$")
	public void i_see_the_Customer_Identification_Screen() throws Throwable {
		shouldBeOnPage("customer");
		Elements.elementShouldBePresent("customer.tooltips_icon");
		Elements.elementShouldBePresent("customer.findcustomer_title");
		Elements.elementShouldBePresent("customer.customer_lookupfield");
		Elements.elementShouldBePresent("customer.findcustomer_button");
		Elements.elementShouldBePresent("customer.camerascanner_title");
		Elements.elementShouldBePresent("customer.cardlookup_title");
		Elements.elementShouldBePresent("customer.scanner_icon");

		String findcustomer_title = Elements.findElement("customer.findcustomer_title").getText();
		Assert.assertEquals("Find Customer", findcustomer_title);

		String customer_lookupfield = Elements.findElement("customer.customer_lookupfield").getAttribute("placeholder");
		Assert.assertEquals("Phone Number or Email", customer_lookupfield);

		String camerascanner_title = Elements.findElement("customer.camerascanner_title").getText();
		Assert.assertEquals("Camera Scanner", camerascanner_title);

	}

	@Then("^I can see the tool tips icon$")
	public void i_can_see_the_tool_tip_icon() throws Throwable {
		Elements.elementShouldBePresent("customer.tooltips_icon");
	}

	@Then("^I can see the Find Customer button$")
	public void i_can_see_the_Find_Customer_button() throws Throwable {
		Elements.elementShouldBePresent("customer.findcustomer_button");
	}

	@Then("^I can see the Customer Lookup Field$")
	public void i_can_see_the_Customer_Lookup_Field() throws Throwable {
		Elements.elementShouldBePresent("customer.customer_lookupfield");
	}


	@When("^I press the tool tips icon$")
	public void i_press_the_tool_tips_icon() throws Throwable {
		Clicks.click("customer.tooltips_icon");
	}

	@Then("^I can see the associate tool tips$")
	public void i_can_see_the_associate_tool_tips() throws Throwable {
		Wait.untilElementPresent("customer.tooltips_popup");
		Elements.elementShouldBePresent("customer.tooltips_popup");
		//X close has been removed from the tooltips overlay
		Elements.elementShouldBePresent("customer.tooltips_message");

		String text = Elements.findElement("customer.cardlookup_title").getText();

		if (text.equalsIgnoreCase("Bloomingdale's Account Lookup"))
		{
			Elements.elementShouldBePresent("customer.tooltips_message");
			String tooltips_message = Elements.findElement("customer.tooltips_message").getText();
			Assert.assertTrue(tooltips_message.contains("Bloomingdale's or other bank card"));
			Assert.assertTrue(tooltips_message.contains("Loyallist Card"));
			Assert.assertTrue(tooltips_message.contains("Bloomingdale's Account Lookup"));
		}
		else
		{
			Elements.elementShouldBePresent("customer.tooltips_message");
			String tooltips_message = Elements.findElement("customer.tooltips_message").getText();
			Assert.assertTrue(tooltips_message.contains("Macy's or other bank card"));
			Assert.assertTrue(tooltips_message.contains("Plenti Card"));
			Assert.assertTrue(tooltips_message.contains("Macy's Account Lookup"));
		}
	}

	@When("^I press the close button$")
	public void i_press_the_close_button() throws Throwable {
		Wait.untilElementPresent("customer.scanner_icon");
		Clicks.click("customer.scanner_icon");
	}

	@Then("^the associate tool tips closes$")
	public void the_associate_tool_tips_closes() throws Throwable {
		Wait.untilElementNotPresent("customer.tooltips_popup");

	}

	@When("^Customer icon is displayed to the left of bag in the header$")
	public void customerIconIsDisplayedToTheLeftOfBagInTheHeader() throws Throwable {
		Wait.untilElementPresent("home.customer_icon_header");
		Assert.assertTrue("Customer icon is not properly displayed.", Elements.findElement("home.customer_icon_header").isDisplayed());
	}

	@And("^User taps customer icon in the header$")
	public void userTapsCustomerIconInTheHeader() throws Throwable {
		Wait.untilElementNotPresent("bag_screen.toast_body");
		Wait.secondsUntilElementPresent("home.customer_icon_header",10);
		Elements.findElement("home.customer_icon_header").click();
	}

	@And("^'Find Customer' is properly displayed$")
	public void findCustomerIsProperlyDisplayed() throws Throwable {
		Wait.untilElementPresent("home.find_customer_text");
		Assert.assertTrue("Find Customer text is not properly displayed.", Elements.findElement("home.find_customer_text").isDisplayed());
		String actualFindCustomerText = Elements.findElement("home.find_customer_text").getText();
		Assert.assertTrue("The actual text is  not properly displayed.Unexpected text :" + actualFindCustomerText, actualFindCustomerText.equals(EXPECTED_FIND_CUSTOMER_TEXT));
	}

	@And("^'Swipe customer's card to identify best offers.' is properly displayed$")
	public void swipeCardTextIsProperlyDisplayed() throws Throwable {
		Assert.assertTrue("Customer swipe card text is not properly displayed.", Elements.findElement("home.customer_swipe_card_text").isDisplayed());
		String actualSwipeCustomerCardText = Elements.findElement("home.customer_swipe_card_text").getText();
		Assert.assertTrue("Customer card text is not properly displayed.Unexpected text : " + actualSwipeCustomerCardText,actualSwipeCustomerCardText.equals(EXPECTED_SWIPE_CUSTOMER_CARD_TEXT));
	}


}
