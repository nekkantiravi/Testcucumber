package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.google.common.base.CharMatcher;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ssanapathi on 11/16/17.
 */

public class ChanelBrowse {

    private static final Logger log = LoggerFactory.getLogger(ChanelBrowse.class);

    @And("^I (should not|should) see sub chanel banners$")
    public void iShouldNotSeeAChanelBanner(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should not")) {
            Assert.assertTrue("Error - App: Sub chanel banner is displayed",
                    Elements.findElements("category_browse.chanel_banner").size() == 1);
            log.info("Sub chanel banner is not displayed");
        } else {
            Assert.assertTrue("Error - App: Sub chanel banner is not displayed",
                    Elements.findElements("category_browse.chanel_banner").size() > 1 &&
                            Elements.findElements("category_browse.chanel_banner").get(1).findElement(By.tagName("img")).getAttribute("src").contains("CHANEL"));
            log.info("Sub chanel banner is displayed");
        }
    }

    @Then("^I should see the chanel category capitalized$")
    public void iShouldSeeTheChanelCategoryCapitalized() throws Throwable {
        List<String> navMenu = Elements.findElement("category_splash.nav_menu_list").findElements(By.tagName("li"))
                .stream().map(ele -> ele.getAttribute("innerText").split("\n")[0]).collect(Collectors.toList());
        Assert.assertTrue("ERROR - App: chanel category is not capitalized",navMenu.contains("CHANEL"));
        log.info("chanel category is capitalized");
    }

    @Then("^I should see the chanel in white words and in black background$")
    public void iShouldSeeTheChanelInWhiteWordsAndInBlackBackground() throws Throwable {
        List<WebElement> navMenu = Elements.findElement("category_splash.nav_menu_list").findElements(By.tagName("li"))
                .stream().filter(ele -> ele.getAttribute("innerText").split("\n")[0].equals("CHANEL")).collect(Collectors.toList());
        Assert.assertTrue("ERROR - App: background style not displayed correctly",
                navMenu.get(0).getAttribute("style").contains("background-color: black"));
        Assert.assertTrue("ERROR - App: chanel in white color displayed correctly",
                navMenu.get(0).getCssValue("color").equals("rgba(0, 0, 0, 1)"));
        log.info("background style displayed correctly");

    }

    @Then("^I should see the chanel sub categories capitalized$")
    public void iShouldSeeTheChanelSubCategoriesCapitalized() throws Throwable {
        List<String> navMenu = Elements.findElement("category_splash.nav_menu_list").findElements(By.className("child"))
                .stream().map(ele -> ele.getAttribute("innerText").split("\n")[0]).collect(Collectors.toList());
        for(String subMenu : navMenu) {
            Assert.assertTrue("ERROR - App: chanel sub categories not capitalized",
                    CharMatcher.JAVA_UPPER_CASE.matchesAnyOf(subMenu));
        }
        log.info("chanel sub categories capitalized");
    }

    @Then("^I should see the chanel sub categories in white colors and in black background when selected$")
    public void iShouldSeeTheChanelSubCategoriesInWhiteColorsAndInBlackBackgroundWhenSelected() throws Throwable {
        List<WebElement> navMenu = Elements.findElement("category_splash.nav_menu_list").findElements(By.className("child"));
        for(WebElement subMenu : navMenu) {
            Assert.assertTrue("ERROR - App: chanel sub cagtegories' background style not displayed correctly",
                    subMenu.getCssValue("background").contains("rgb(255, 255, 255)"));
            Assert.assertTrue("ERROR - App: chanel sub cagtegories in white color displayed correctly",
                    subMenu.getCssValue("color").equals("rgba(0, 0, 0, 1)"));
        }
        log.info("chanel sub categories in white colors and in black background displayed correctly");
    }

    @Then("^I should see only sort button and not filter by$")
    public void iShouldSeeOnlySortButtonAndNotFilterBy() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.channel_options", 10);
        Assert.assertNotNull("ERROR - App: chanel options not displayed ",
                Elements.findElement("search_result.channel_options"));
    }

    @Then("^I should not see any bonus or promotion offer$")
    public void iShouldNotSeeAnyBonusOrPromotionOffer() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.promotion_grid", 10);
        Assert.assertTrue("ERROR - App: bonus or promotions available",
                Elements.findElement("search_result.promotion_grid").getAttribute("innerText").equals("") &&
                        Elements.findElement("search_result.promotion_grid").getText().equals(""));
        log.info("bonus/promtions are not available");
    }

    @Then("^I should see the start summary beneath the product thumbnails$")
    public void iShouldSeeTheStartSummaryBeneathTheProductThumbnails() throws Throwable {
        Assert.assertTrue("ratings are displayed correctly", Elements.findElements("search_result.ratings").size() > 0);
    }

    @Then("^I should be in the chanel default category$")
    public void iShouldBeInTheChanelDefaultCategory() throws Throwable {
        GlobalNav.openGlobalNav();
        Assert.assertFalse("Error - App: Page is not redirected chanel category", Elements.findElement("category_browse.nav_menu_list").findElements(By.tagName("li")).stream().
                filter(webElement -> webElement.getText().equalsIgnoreCase("chanel")).collect(Collectors.toList()).isEmpty());
    }

    @Then("^I should see main chenel banner$")
    public void iShouldSeeMainChenelBanner() throws Throwable {
        Assert.assertTrue("Error - App: chanel banner is not displayed", Wait.untilElementPresent("category_browse.chanel_banner"));
        Assert.assertTrue("Error App: Chanel page containing non chanel banner image",Elements.findElements("category_browse.chanel_banner").get(0)
                .findElement(By.tagName("img")).getAttribute("src").contains("Chanel_Logo"));
    }
}
