package com.macys.sdt.projects.Marketing.SEOImprovements.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.projects.Marketing.SEOImprovements.actions.website.SEO;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper.getProductIds;

public class SEOImprovements extends StepUtils {

    public List<String>  brightTagTopProducts = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(SEOImprovements.class);

    @Then("^I should see the new search url pattern$")
    public void iShouldSeeTheNewSearchUrlPattern() throws Throwable {
        Assert.assertTrue("ERROR APP: Old search URL is displaying", WebDriverManager.getCurrentUrl().contains("/shop/featured/"));
    }

    @Then("^I should see the new url pattern appended with the facet type and value details$")
    public void iShouldSeeTheNewUrlPatternAppendedWithTheFacetTypeAndValueDetails() throws Throwable {
        String expectedURL = (LeftFacet.facetName+"_normal"+"/"+LeftFacet.facetValue).toLowerCase();
        Assert.assertTrue("ERROR APP: New url pattern is not appended with the facet type and facet value details", WebDriverManager.getCurrentUrl().toLowerCase().contains(expectedURL));

    }

    @And("^I navigate to the previous page using browser back button$")
    public void iNavigateToThePreviousPageUsingBrowserBackButton() throws Throwable {
        Navigate.browserBack();
    }

    @Then("^I should see search keywords encoded as \"([^\"]*)\" in search base url$")
    public void iShouldSeeSearchKeywordsEncodedAsInSearchBaseUrl(String expectedURL) throws Throwable {
        Assert.assertTrue("ERROR APP: New search URL is not displaying as expected", WebDriverManager.getCurrentUrl().contains(expectedURL));
    }

    @When("^I navigate to old search \"([^\"]*)\"$")
    public void iNavigateToOldSearch(String oldSearchURL) throws Throwable {
        Navigate.visit(RunConfig.url +oldSearchURL );
    }

    @Then("^I should not see \"([^\"]*)\" in the search base url$")
    public void iShouldNotSeeInTheSearchBaseUrl(String keyword) throws Throwable {
        Assert.assertFalse("ERROR APP: Keyword text is displaying in search URL", WebDriverManager.getCurrentUrl().toLowerCase().contains(keyword));
    }

    @And("^I should see the above message displayed in \"([^\"]*)\" tag instead of h three tag$")
    public void iShouldSeeTheAboveMessageDisplayedInTagInsteadOfHThreeTag(String tagName) throws Throwable {
        Assert.assertTrue("ERROR APP: H1 tag is not displaying", Elements.findElement(By.id("resultsFoundMessage")).getTagName().equals(tagName));
    }

    @Then("^I should see it is redirected to browse category$")
    public void iShouldSeeItIsRedirectedToBrowseCategory() throws Throwable {
        shouldBeOnPage("category_browse");
    }

    @And("^I should not see robots meta tag in view page source$")
    public void iShouldNotSeeRobotsMetaTagInViewPageSource() throws Throwable {
        Assert.assertFalse("ERROR APP: Robots meta tag is displaying on UI" , Elements.elementPresent("search_result.metaName"));

    }

    @Then("^I verify that old search url for registry is displayed$")
    public void iVerifyThatOldSearchUrlForRegistryIsDisplayed() throws Throwable {
        Assert.assertTrue("ERROR APP: New search URL is displaying", WebDriverManager.getCurrentUrl().toLowerCase().contains("/wedding/search?keyword"));
    }

    @Then("^I should see product IDs under category in topProducts field of bright tag$")
    public void iShouldSeeProductIDsUnderCategoryInTopProductsFieldOfBrightTag() throws Throwable {
        brightTagTopProducts = new SEO().getTopProductsList();
        Assert.assertTrue("ERROR - APP: topProducts is not displaying in Bright Tag",brightTagTopProducts.size()==6);
    }

    @And("^I should see products under bright tag should match with UI top products$")
    public void iShouldSeeProductsUnderBrightTagShouldMatchWithUITopProducts() throws Throwable {
        List<String> productsIds = getProductIds();
        for (int i = 0; i < 6; i++) {
            Assert.assertTrue("ERROR - APP: UI top 6 product Ids are not matching with top 6 products in bright tag ", productsIds.get(i).equals(brightTagTopProducts.get(i)));
        }
    }

    @Then("^I should not see the product IDs under category in topProducts field of bright tag$")
    public void iShouldNotSeeTheProductIDsUnderCategoryInTopProductsFieldOfBrightTag() throws Throwable {
        Pattern p = Pattern.compile("(, topProducts=)(), ");
        Matcher m = p.matcher(Navigate.execJavascript("return MACYS.brightTag").toString());
        Assert.assertTrue("ERROR - APP: topProducts is not displaying in Bright Tag",m.find());
    }

    @Then("^I should not see any change in product IDs under category of search keywords in bright tag$")
    public void iShouldNotSeeAnyChangeInProductIDsUnderCategoryOfSearchKeywordsInBrightTag() throws Throwable {
        Assert.assertTrue("ERROR - APP: product ids are changed in bright tag ", (new SEO().getTopProductsList()).equals(brightTagTopProducts));
    }

    @When("^I navigates in BRANDS normal DLP pages$")
    public void iNavigatesInBRANDSNormalDLPPages() throws Throwable {
        String dlpURL = macys() ? "/shop/featured/august-hats" : "/buy/converse%20kids%20sneakers";
        Navigate.visit(RunConfig.url + dlpURL);
        logger.info("Navigated to DLP page sucessfully");
    }

    @And("^I should have search keyword as \"([^\"]*)\"$")
    public void iShouldHaveSearchKeywordAs(String expectedKeyword) throws Throwable {
        String displayedKeyword = Elements.findElement("home.search_field").getAttribute("value").trim();
        Assert.assertTrue("Expected keyword is not displayed in search box,  Actual displayed keyword: " + displayedKeyword,
                displayedKeyword.equals(expectedKeyword));
    }

    @Then("^I should see \"([^\"]*)\" message of product count$")
    public void iShouldSeeMessageOfProductCount(String message) throws Throwable {
        String expectedSearchText= message.replaceAll("xxxx", PageNavigation.slectedBrandName).replaceAll("'n'", String.valueOf(DiscoveryHelper.getProductCount()));
        String resultText =  Elements.findElement(Elements.element("search_result.product_count_breadcrumb")).getText();
        Assert.assertTrue("Expected text: " + expectedSearchText + " Actual text displayed: " + resultText,
                expectedSearchText.trim().equalsIgnoreCase(resultText.trim()));
    }

    @When("^I select one facet value in facet panel with crawlable facet$")
    public void iSelectOneFacetValueInfacetPanelWithCrawlablefacet() throws InterruptedException {
        int index;
        Random randomGenerator = new Random();
        List<String> facetItems = DiscoveryHelper.getAllFacetValues("Color");
        index = randomGenerator.nextInt(facetItems.size());
        Clicks.click(Elements.paramElement("left_facet.select_color_facet", facetItems.get(index)));
    }

    // we already have this step in SearchBrowseFacetNavigation.java but we don't need the componentization which is refreshing the page.
    // We have to verify our scenario without the page refresh as page refresh will upfate the top 6 products in the bright tag
    @When("^I navigate to the next page$")
    public void iNavigateToTheNextPage() throws Throwable {
        Wait.forPageReady();
        Clicks.click("pagination.goto_next_page_via_arrow");
        Wait.forPageReady();
        Wait.forLoading("left_facet.loading");
        //DiscoveryHelper.navigateToComponentizationURL();
        Wait.forPageReady();
    }
}