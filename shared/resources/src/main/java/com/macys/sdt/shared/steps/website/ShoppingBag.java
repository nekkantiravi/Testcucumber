package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.HashMap;


public class ShoppingBag extends StepUtils {

    /**
     * Verifies that payment badge shows on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see payment badge$")
    public void I_should_see_payment_badge() throws Throwable {

        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }

        if (!Elements.elementPresent("shopping_bag.payment_badge")) {
            Assert.fail("Payment badge not displayed");
        }
    }

    /**
     * Verifies that promo code inline error message shows on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see promo inline error message$")
    public void I_should_see_promo_inline_error_message() throws Throwable {
        Wait.untilElementPresent(Elements.element("shopping_bag.promo_inline_error_message"));
    }

    /**
     * Verifies the top of page promo error code
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see promo error message on the top of bag page$")
    public void I_should_see_promo_error_message_on_the_top_of_bag_page() throws Throwable {
        Wait.untilElementPresent(Elements.element("shopping_bag.error_message"));
    }

    @When("^I  click on continue checkout button on shoppping bag page$")
    public void iClickOnContinueCheckoutButtonOnShopppingBagPage() throws Throwable {
        Clicks.click("shopping_bag.continue_checkout_image");
    }

    @Then("^I should see Shopping Bag Page$")
    public void i_should_see_Shopping_Bag_Page() throws Throwable {
        if(onPage("add_to_bag")){
            Clicks.click("add_to_bag.checkout");
        }
        Assert.assertTrue("Shopping bag page could not be verified", onPage("shopping_bag"));
    }

}
