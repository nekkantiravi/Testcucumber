package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.website.mcom;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Created by yc05165 on 01/08/17.
 */
public class Shoppingbag {

    @And("^I click checkout button on add to bag overlay$")
    public void iClickCheckoutButtonOnAddToBagOverlay() {
        Home home = Navigate.get(Home.class);
        Wait.until(home.checkoutButton::isDisplayed);
        home.checkoutButton.click();
    }

    @And("^I remove an item from the bag$")
    public void iRemoveAnItemFromTheBag() {
        Wait.forPageReady();
        Wait.secondsUntilElementPresentAndClick("home.remove_button_bag",20);
    }

    @Then("^I can see empty bag view$")
    public void iCanSeeEmptyBagView() {
        Wait.forPageReady();
        Home home = Navigate.get(Home.class);
        Assert.assertTrue("EEROR:- Empty bag element is displayed", Wait.until(home.bagEmpty::isDisplayed));
        Assert.assertTrue("Empty bag message", home.bagEmpty.getText().contains("Your Current Shopping Bag is empty."));

    }
}
