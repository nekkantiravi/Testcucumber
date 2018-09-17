package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.XAPIBrowseService;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GiftCard extends StepUtils {

    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);

    @Then("^I should be redirected to current Gift Card Cat splash page$")
    public void iShouldBeRedirectedToCurrentGiftCardCatSplashPage() throws Throwable {
        shouldBeOnPage("gift_card_splash");
        log.info("Verified gift card splash pages");
    }

    @And("^I tap the e-gift card button$")
    public void iTapTheEGiftCardButton() throws Throwable {
        Wait.untilElementPresent("gift_card_splash.e_gift_card");
        Clicks.javascriptClick(Elements.findElement("gift_card_splash.e_gift_card").findElement(By.tagName("a")));
        Wait.forPageReady();
    }

    @Then("^I should be on VGC Browse page$")
    public void iShouldBeOnVGCBrowsePage() throws Throwable {
        shouldBeOnPage("category_browse");
        Assert.assertTrue("Error - App: Page is not navigating VGC browse", WebDriverManager.getCurrentUrl().contains("/shop/gift-cards"));
        log.info("Navigated to VGC browse page");
    }

    @And("^I should see Category Name with total quantity$")
    public void iShouldSeeCategoryNameWithTotalQuantity() throws Throwable {
        Assert.assertTrue(Elements.getText("category_browse.browse_header").equalsIgnoreCase("all occasions"));
        int count = XAPIBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()), null);
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        log.info("Verified category name with total product count on gift card browse page");
    }

    @And("^I should see (filter|sort) button$")
    public void iShouldSeeButton(String buttonName) throws Throwable {
        switch (buttonName.toLowerCase()) {
            case "sort":
                Assert.assertTrue("Error - Env: Sort button is missing", Elements.elementPresent(By.id("m-browse-buttons-sortby")));
                break;
            case "filter":
                Assert.assertTrue("Error - Env: Filter button is missing", Elements.elementPresent("search_result.filter_by_select"));
                break;
        }
        log.info(buttonName + " button is displayed");
    }

    @And("^I should see only product name associated to the product$")
    public void iShouldSeeOnlyProductNameAssociatedToTheProduct() throws Throwable {
        List<WebElement> products = Elements.findElements("search_result.product_thumbnail");
        for (int i = 0; i < products.size(); i++) {
            String productName = Elements.findElements("search_result.product_thumbnail").get(i).getText();
            Assert.assertTrue("Error - App:- product: " + productName + " is not a gift card", productName.toLowerCase().contains("card"));
        }
    }

    @Then("^I should see the following form fields for purchasing a gift card$")
    public void theIShouldSeeTheFollowingFormFieldsForPurchasingAGiftCard(List<String> table) throws Throwable {
        Wait.untilElementPresent("product_display.m_gift_card_container");
        for (String name : table) {
            Assert.assertTrue(name + " filed is missing on gift card PDP page", Elements.getText("product_display.m_gift_card_container").contains(name));
        }
        log.info("Verified form fileds on gift card PDP page");
    }

}
