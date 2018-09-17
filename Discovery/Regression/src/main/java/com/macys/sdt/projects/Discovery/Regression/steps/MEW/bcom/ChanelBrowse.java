package com.macys.sdt.projects.Discovery.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ChanelBrowse {

    private static final Logger log = LoggerFactory.getLogger(ChanelBrowse.class);

    @Then("^I should see the chanel in white and in black background$")
    public void iShouldSeeTheChanelInWhiteAndInBlackBackground() throws Throwable {
        List<WebElement> navMenu = Elements.findElement("category_splash.nav_menu_list").findElements(By.tagName("li"))
                .stream().filter(ele -> ele.getAttribute("innerText").split("\n")[0].equals("CHANEL")).collect(Collectors.toList());
        Assert.assertTrue("ERROR - App: chanel in white color displayed correctly",
                navMenu.get(0).getCssValue("color").equals("rgba(255, 255, 255, 1)"));
        log.info("background style displayed correctly");
    }

    @Then("^I should see a chanel banner$")
    public void iShouldSeeAChanelBanner() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse.chanel_banner",10);
        Assert.assertTrue("ERROR - App: chanel banner is not displayed",
                Elements.findElement("category_browse.chanel_banner").isDisplayed());
        log.info("chanel banner is displayed");
    }

    @And("^I navigate to \"([^\"]*)\" chanel category page$")
    public void iNavigateToChanelCategoryPage(String page) throws Throwable {
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName(page);
        Wait.forPageReady();
        log.info("Navigated to chanel category");
    }

    @Then("^I should see the chanel sub categories in white and in black background when selected$")
    public void iShouldSeeTheChanelSubCategoriesInWhiteAndInBlackBackgroundWhenSelected() throws Throwable {
        List<WebElement> navMenu = Elements.findElement("category_splash.nav_menu_list").findElements(By.className("child"));
        for (WebElement subMenu : navMenu) {
            Assert.assertTrue("ERROR - App: chanel sub cagtegories' background style not displayed correctly",
                    subMenu.getCssValue("background").contains("rgb(255, 255, 255)"));
            Assert.assertTrue("ERROR - App: chanel sub cagtegories in white color displayed correctly",
                    subMenu.getCssValue("color").equals("rgba(17, 17, 17, 1)"));
        }
        log.info("chanel sub categories in white colors and in black background displayed correctly");
    }
}
