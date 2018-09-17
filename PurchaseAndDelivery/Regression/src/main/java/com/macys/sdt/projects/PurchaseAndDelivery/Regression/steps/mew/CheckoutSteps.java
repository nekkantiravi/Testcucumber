package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.mew;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pawan on 7/13/2017.
 */
public class CheckoutSteps extends StepUtils{

    private static final Logger logger = LoggerFactory.getLogger(CheckoutSteps.class);

    @And("^I navigate to shopping bag from Home Page using mobile site$")
    public void I_navigate_to_shopping_bag() throws Throwable {
        if (chrome()) {
            Wait.secondsUntilElementPresent("checkout.quick_bag", 15);
        }
        Clicks.click("checkout.quick_bag");
    }

    /**
     * click on continue checkout button
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click on continue checkout button on shoppping bag page$")
    public void I_click_on_continue_checkout_button_on_shoppping_bag_page() throws Throwable {
        Wait.untilElementPresent("shopping_bag.continue_checkout_image");
        Clicks.click("shopping_bag.continue_checkout_image");
    }


    /*
     * Verify user see expected word on sign in checkout page
     */
    @Then("^I should see expected word \"([^\"]*)\" on sign in checkout page$")
    public void verifyExpectedWordOnSignInCheckoutPage(String expectedValue) throws Throwable
    {
        WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertTrue("Expected word: " + expectedValue + " is not displayed.", Elements.getText("sign_in.have_an_account").contains(expectedValue));
        logger.info("Expected word: " + expectedValue + " is displayed");
        Assert.assertTrue("Expected word " + expectedValue + " is not displayed.",Elements.getText("sign_in.not_have_an_account").contains(expectedValue));
        logger.info("Expected word: " + expectedValue + " is displayed.");
    }
}
