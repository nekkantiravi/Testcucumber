package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.RecentlyViewed;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
/*
  Created by Lokendar Pullagurla on 7/5/2017.
  Verify basic atttributes of RVI panel page
*/

public class RecentlyViewedPanelSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(RecentlyViewedPanelSteps.class);
    private String selectedRVIProductID = null;
    private static int totalProductInRvi;

    @Then("^I verify the display of RVI in \"(search result|category browse|product display|category Splash|category subsplash)\" page$")
    public void iVerifyTheDisplayOfRVIInPage(String pageName) throws Throwable {
        Wait.forPageReady();
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        if(pageName.equals("search result")){
            Assert.assertTrue("ERROR - APP: Expected page:"+pageName+" url pattern not matched.", currentUrl.contains("/shop/featured") || currentUrl.contains("/shop/registry/wedding/search") || currentUrl.contains("/shop/search"));
        }else if(pageName.equalsIgnoreCase("category browse")  || pageName.equalsIgnoreCase("category Splash") || pageName.equalsIgnoreCase("category subsplash")){
            Assert.assertTrue("ERROR - APP: Expected page:"+pageName+" url pattern not matched.", currentUrl.contains("/shop/") && currentUrl.contains("?id="));
        }else if(pageName.equals("product display")){
            Assert.assertTrue("ERROR - APP: Expected page:"+pageName+" url pattern not matched.", currentUrl.contains("/shop/product/") || currentUrl.contains("shop/registry/wedding/product"));
        }
        Utils.threadSleep(5000, "ERROR - ENV: Waiting for RVI panel products");
        Assert.assertTrue("ERROR - APP: RVI panel is not displayed on page: "+pageName, RecentlyViewed.isDisplayed());
        logger.info("Verified Recently Viewed Panel display on page: "+pageName);
    }

    @When("^I click (right|left) navigation button in rvi panel$")
    public void iClickRightNavigationButtonInRviPanel(String arrowDirection) throws Throwable {
        RecentlyViewed.updateProducts();
        RecentlyViewed.selectLeftRightArrows(arrowDirection);
        logger.info("Selected '"+arrowDirection+"' arrow from Recently Viewed Panel");
        Wait.untilElementPresent("recently_viewed_items.thumbnail_wrapper");
    }

    @When("^I select a product from RVI panel$")
    public void iSelectAProductFromRVIPanel() throws Throwable {
        selectedRVIProductID = RecentlyViewed.selectRandomRVIProduct();
        Wait.forPageReady();
        logger.info("Selected product:'"+selectedRVIProductID+"' from Recently Viewed Panel");
    }

    @Then("^I should be navigated to respective PDP page from RVI panel$")
    public void iShouldBeNavigatedToRespectivePDPPageFromRVIPanel() throws Throwable {
        shouldBeOnPage("product_display");
        Assert.assertTrue("ERROR - APP: RVI product selection is not navigated to respective PDP page", Elements.findElement("product_display.product_id_div").getText().toLowerCase().replace("web id:", "").trim().equalsIgnoreCase(selectedRVIProductID));
        logger.info("Verified PDP for product:'"+selectedRVIProductID+"' for selection of Recently Viewed Panel");
    }

    @Then("^I remove all Recently viewed items$")
    public void iRemoveAllRecentlyViewedItems() throws Throwable {
        List<WebElement> recentlyViewedItems = RecentlyViewed.rviProductThumbnails();
        pausePageHangWatchDog();
        totalProductInRvi = recentlyViewedItems.size();
        for (int i = 0; i < totalProductInRvi; i++) {
            if (RecentlyViewed.isEditVisible()) {
                Clicks.javascriptHover(Elements.findElement("recently_viewed_items.edit_button"));
                Clicks.javascriptClick(Elements.findElement("recently_viewed_items.edit_button"));
            }
            if (RecentlyViewed.isDoneVisible()) {
                Clicks.javascriptHover(Elements.findElement("recently_viewed_items.remove_button"));
                Clicks.click("recently_viewed_items.remove_button");
            } else {
                Assert.fail("ERROR -APP: Removing RVI product failed.");
            }
        }
        Clicks.click("recently_viewed_items.rvi_edit_done");
        Wait.forPageReady();
        logger.info("Removed all Recently viewed items");
        resumePageHangWatchDog();
    }

    @Then("^I (should not|should) see recently viewed items section$")
    public void iShouldNotSeeSection(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should not")) {
            Assert.assertFalse("Error Env:- RVI is displaying", Elements.elementPresent("recently_viewed_items.rvi_container"));
        } else {
            Assert.assertTrue("Error Env:- RVI is not displaying", Elements.elementPresent("recently_viewed_items.rvi_container"));
        }
        logger.info("Verified RVI section " +condition + "display");
    }

    @And("^I verify the currency is \"([^\"]*)\" on the RVI$")
    public void iVerifyTheCurrencyIsOnTheRVI(String currency) throws Throwable {
        List<String> rviProductPriceList = RecentlyViewed.getRVIProductPriceValues();
        Assert.assertFalse("ERROR - APP: RVI products are not displaying with ISHIP currency:'"+currency+"' and Actual prices are:"+rviProductPriceList,
                rviProductPriceList.stream().filter(f -> f.contains(currency)).collect(Collectors.toList()).isEmpty());
    }
}