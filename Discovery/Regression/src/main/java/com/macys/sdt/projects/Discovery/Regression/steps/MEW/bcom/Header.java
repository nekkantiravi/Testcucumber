package com.macys.sdt.projects.Discovery.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Header extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Header.class);

    @And("^I verify all elements of left navigation in Header Menu$")
    public void iVerifyallElementsOfLeftNavigationInHeaderMenu(List<String> menuItems) throws Throwable {
        List<String>globalMenu= Elements.findElement("main_left_nav.nav_menu_list").findElements(By.tagName("li")).stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("Error:- All elements are not displayed on the panel", globalMenu.containsAll(menuItems));
        logger.info("Verified all the elements on left navigation");
    }

    @And("^I should not see global navigation panel if I click on global navigation$")
    public void iShouldNotSeeGlobalNavigationPanelIfIClickOnGlobalNavigation() throws Throwable {
        Clicks.click("main_left_nav.close_global_navigation");
        Assert.assertTrue("I still see Global nav menu panel", Elements.findElement("main_left_nav.nav_menu_list").isDisplayed());
        logger.info("global navigation is closed");
    }

    @Then("^I verify left navigation expands in \"([^\"]*)\" mode$")
    public void iVerifyLeftNavigationExpandsInMode(String mode) throws Throwable {
        switch (mode) {
            case "domestic":
                Assert.assertTrue("Domestic Header GN is not showing", Elements.findElement("main_left_nav.nav_menu_list").isDisplayed());
                break;
            case "registry":
                Assert.assertTrue("Registry Header GN is not showing", Elements.findElement("main_left_nav.global_nav_button").isDisplayed());                break;
            case "iship":
                Assert.assertTrue("Iship Header GN is not showing", Elements.findElement("main_left_nav.nav_menu_list").isDisplayed());                break;
        }
        logger.info("verified the navigation button expand in all modes");
    }

    @Then("^I verify bloomies Logo is displayed in Header$")
    public void iVerifyBloomiesLogoIsDisplayedInHeader() throws Throwable {
        Assert.assertTrue("bloomies logo is displayed",Elements.findElement("header.header_image").isDisplayed());
        logger.info("verified the display of bloomies logo");
    }

    @Then("^I should see the bloomingdales logo on unified login page header$")
    public void iShouldSeeBloomingdalesLogoOnCreateRegistryLoginPage() throws Throwable {
        Assert.assertTrue("bloomies logo is displayed",Elements.findElement("header.header_image").isDisplayed());
        logger.info("verified the display of bloomies logo");
    }

    @And("^I should see shopping bag icon is displayed in Header$")
    public void iShouldSeeShoppingBagIconIsDisplayedInHeader() throws Throwable {
        Assert.assertTrue("Shopping bag icon is displayed in Header",Elements.findElement("header.shopping_bag_icon").isDisplayed());
        logger.info("verified the display of shopping bag icon");
    }

    @And("^I should see the wishlist wrapper in Header$")
    public void iShouldSeeTheWishlistWrapperInHeader() throws Throwable {
        Assert.assertTrue("Shopping bag icon is displayed in Header",Elements.findElement("header.top_nav_wish_list_icon").isDisplayed());
        logger.info("verified the wishlist wrapper in header");
    }

    @And("^I should not see the wishlist wrapper in Header$")
    public void iShouldNotSeeTheWishlistWrapperInHeader() throws Throwable {
        Wait.secondsUntilElementNotPresent("header.top_nav_wish_list_icon",20);
        logger.info("verifed the wishlist wrapper display in iship mode");
    }

    @When("^I click on bloomies logo in header$")
    public void iClickOnBloomiesLogoInHeader() throws Throwable {
        Clicks.click("header.header_image");
        logger.info("clicked on bloomies logo");
    }

    @Then("^I verify logo will be redirect to the homepage$")
    public void iVerifyLogoWillBeRedirectToTheHomepage() throws Throwable {
        shouldBeOnPage("home");
        logger.info("verified the display of home page");
    }

    @Then("^I should see the global navigation button displayed$")
    public void iShouldSeeTheGlobalNavigationButtonDisplayed() throws Throwable {
        Wait.forPageReady();
        Utils.threadSleep(5000,"ERROR - APP: Timed out while GN display");
        Assert.assertTrue("Global navigation button displayed",Elements.findElement("main_left_nav.global_nav_button").isDisplayed());
        logger.info("verified the display of global navigation button");
    }

    @And("^I verify Registry title is displayed in Header$")
    public void iVerifyRegistryTitleIsDisplayedInHeader() throws Throwable {
        Assert.assertTrue("Registry title displayed in Header",Elements.findElement("header.registry_title").isDisplayed());
        logger.info("Verified the display of Registry title in Header");
    }
}
