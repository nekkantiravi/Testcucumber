package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategorySplash;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import static java.net.URLDecoder.decode;

public class CategorySplashSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(BrowsePageSteps.class);
    CategorySplash splash = new CategorySplash();
    private String searchSelected = null;

    @When("^I navigate directly to category splash page$")
    public void iNavigateDirectlyToCategorySplashPage() throws Throwable {
        Home.selectRandomCategory();
    }

    @Then("^I should see the Popular Searches at the bottom$")
    public void iShouldSeeThePopularSearchesAtTheBottom() throws Throwable {
        if (macys()) {
            int i = 0;
            scrollToLazyLoadElement("category_splash.popular_searches_container");
            while (!Elements.elementPresent("category_splash.popular_searches_container") && i++ < 5) {
                iNavigateDirectlyToCategorySplashPage();
            }
        }
        Assert.assertTrue("Error - ENV: Tag clouds links are not displayed", splash.popularSearchLinksAvailable());
    }

    @And("^I click on any keyword in the Popular Searches$")
    public void iClickOnAnyKeywordInThePopularSearches() throws Throwable {
        searchSelected = CommonUtils.sanitizeString(splash.selectOnePopularSearchLink());
    }

    @Then("^I should see the relevant page displayed$")
    public void iShouldSeeTheRelevantPageDisplayed() throws Throwable {
        // TODO(): Check if this step is used by Macys and remove the macys() block if it is not needed.
        if (macys()) {
            if (Elements.elementPresent("category_browse.current_category")) {
                String category = CommonUtils.sanitizeString(Elements.getText("category_browse.current_category"));
                Assert.assertTrue("ERROR - ENV: Search results for " + searchSelected + " should be displayed",
                        category.contains(searchSelected));
            } else {
                verifySearchSelectedByUrl();
            }
        } else {
            if (Elements.elementPresent("category_browse.search_keyword")) {
                String category = CommonUtils.sanitizeString(Elements.getText("category_browse.search_keyword")).replace("%", "");
                Assert.assertTrue("ERROR - ENV: Search results for " + searchSelected + " should be displayed",
                        category.contains(searchSelected.replace("%", "")));
            } else {
                verifySearchSelectedByUrl();
            }
        }
    }

    private void verifySearchSelectedByUrl() {
        String url = CommonUtils.sanitizeString(WebDriverManager.getCurrentUrl()).replace("the", "").replaceAll("'", "").replaceAll(" ", "-");;
        String urlCompare = searchSelected.replace("the", "").replaceAll("'", "").replaceAll(" ", "-");
        Assert.assertTrue("ERROR - ENV: Search results for '" + urlCompare + "' should be displayed on URL",
                url.contains(urlCompare));
    }

    @Then("^I verify red link present in LNA$")
    public void iVerifyRedLinkPresentInLNA() throws Throwable {
        String href = null;
        Wait.untilElementPresent("left_navigation_category.red_link_categories");
        List<WebElement> redCategoryLink = Elements.findElements("left_navigation_category.red_link_categories");
        Assert.assertFalse("ERROR - DATA: Red link category are not exists on page:" + WebDriverManager.getCurrentUrl(), redCategoryLink.isEmpty());
        for (WebElement link : redCategoryLink) {
            if (link.isDisplayed() && !link.getText().equalsIgnoreCase("")) {
                href = link.getAttribute("href");
                int responseCode = RESTOperations.doGET(href, null).getStatus();
                Assert.assertTrue("ERROR - APP: Red link category url:" + href + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
            }
        }
        logger.info("Verified red link categories feature");
    }

    @And("^I verify the title for \"([^\"]*)\" category page$")
    public void iVerifyTheTitleForCategoryPage(String categoryName) throws Throwable {
        if (categoryName.equalsIgnoreCase("dining")) {
            Assert.assertTrue("ERROR - APP: Page title:" + title().toLowerCase() + " is not included category name:" + categoryName.toLowerCase(), title().toLowerCase().contains("dinnerware"));
        } else {
            Assert.assertTrue("ERROR - APP: Page title:" + title().toLowerCase() + " is not included category name:" + categoryName.toLowerCase(), title().toLowerCase().contains(categoryName.toLowerCase()));
        }
        logger.info("Verified title for category:" + categoryName);
    }

    @Then("^I verify the \"canonical\" tag in HTML view source code$")
    public void iVerifyTheTagInHTMLViewSourceCode() throws Throwable {
        Utils.threadSleep(10000, "Page is taking longer time to load");
        String afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href"), "UTF-8"), "UTF-8").replace("|", "-");
        Assert.assertFalse("ERROR - APP: Canonical tag not exists in view source", afterTagDecode.equals(""));
        Assert.assertTrue("ERROR - APP: Canonical tag url:" + afterTagDecode + " is not matched with navigated category.", WebDriverManager.getCurrentUrl().toLowerCase().contains(afterTagDecode.toLowerCase()));
        logger.info("Verified canonical tag in page view source");
    }

    @Then("^I verify MBOX in the page source$")
    public void iVerifyMBOXInThePageSource() throws Throwable {
        WebElement mboxElement = Elements.findElement(By.className("mboxDefault"));
        Assert.assertFalse("ERROR - APP: MBOX element is not exists on page:" + WebDriverManager.getCurrentUrl(), mboxElement == null);
        logger.info("Verified MBOX element in page view source");
    }

    @Then("^I verify Customer's Top Rated section$")
    public void iVerifyCustomerSTopRatedSection() throws Throwable {
        WebElement topRatedProduct = Elements.findElement("category_splash.customer_top_rated");
        Assert.assertFalse("ERROR - DATA: Customer's Top Rated section media data not available", topRatedProduct == null);
        Assert.assertTrue("ERROR - APP: Customer's Top Rated products are not displaying on Category Splash Page:", topRatedProduct.isDisplayed());
        Clicks.click(topRatedProduct);
        Wait.forPageReady();
        Assert.assertTrue("ERROR - APP: Customer's Top Rated product link click is not navigate to PDP page", onPage("product_display"));
        logger.info("Verified Customer's Top Rated products on Category Splash Page");
    }

    @Then("^I verify Left Nav section for \"([^\"]*)\"$")
    public void iVerifyLeftNavSection(String category) throws Throwable {
        Assert.assertTrue("ERROR - ENV: Left Nav section is not displayed in " + category + " CatSplash Page", Elements.elementPresent("left_navigation_category.left_navigation_container"));
        Assert.assertTrue("ERROR - ENV: Left Nav links are not displayed in " + category + " CatSplash Page", Elements.findElements("left_navigation_category.subcategory").size() > 0);
        logger.info("Verified Left Nav section in " + category + " CatSplash Page");
    }

    @Then("^I verify Left Nav section match with production$")
    public void iVerifyLeftNavSectionMatchWithProduction() throws Throwable {
        List<String> leftNavCatNameListFromQA = Elements.findElements("left_navigation_category.subcategory").stream().map(m-> m.getText()).collect(Collectors.toList());
        leftNavCatNameListFromQA.remove("");
        leftNavCatNameListFromQA.remove(null);
        String currentURL = WebDriverManager.getCurrentUrl();
        String productionURL = " ";
        if (currentURL.contains("macys")){
            productionURL = "https://www.macys.com/"+ currentURL.split(".com/")[1];
        }
        else{
            productionURL = "https://www.bloomingdales.com/"+ currentURL.split(".com/")[1];
        }
        Navigate.visit(productionURL);
        Wait.forPageReady();
        List<String> leftNavCatNameListFromProd = Elements.findElements("left_navigation_category.subcategory").stream().map(m-> m.getText()).collect(Collectors.toList());
        leftNavCatNameListFromProd.remove("");
        leftNavCatNameListFromProd.remove(null);
        Assert.assertTrue("Left Nav category names are not matched with production site. QA list:"+leftNavCatNameListFromQA+" PROD List:"+leftNavCatNameListFromProd, leftNavCatNameListFromQA.toString().equals(leftNavCatNameListFromProd.toString()));
    }

    @Then("^I verify the server name \"([^\"]*)\" display in page source$")
    public void i_verify_the_server_name_display_in_page_source(String server) throws Throwable {
        Set<Cookie> cookies = Cookies.getCookies();
        String cookieStr = cookies == null ? null :
                Utils.listToString(cookies.stream()
                                .map(c -> c.getName() + "=" + c.getValue())
                                .collect(Collectors.toList()),
                        ";", null);
        HashMap<String, String> headers = new HashMap<>();
        if (cookieStr != null) {
            headers.put("cookie", cookieStr);
        }
        String html = RESTOperations.doGET(WebDriverManager.getCurrentUrl(), headers).readEntity(String.class);
        Document doc = Jsoup.parse(html);
        Element siteInfo = doc.select("div#soasta_pageinfo").last();
        String appServer = siteInfo.select("appserver").html();
        switch (server.toLowerCase()) {
            case "shopapp":
                Wait.forPageReady();
                logger.info("shop app server::" + appServer);
                Assert.assertTrue("Page is not served from ShopApp", appServer.contains("shopapp"));
                break;
            case "navapp":
                Wait.forPageReady();
                logger.info("nav app server::" + appServer);
                Assert.assertTrue("Page is not served from NavApp", appServer.contains("navapp"));
                break;
            case "navapp or discovery_page":
                Wait.forPageReady();
                logger.info("nav app server::" + appServer);
                boolean discovery = WebDriverManager.getWebDriver().getPageSource().contains("discovery-pages.common.css");
                Assert.assertTrue("Page is not served from " + server, appServer.contains("navapp") || discovery);
                break;
        }
    }

    @Then("^I should be redirected to category id \"([^\"]*)\"$")
    public void iShouldBeRedirectedToCategoryId(String categoryId) throws Throwable {
       Assert.assertTrue("Not navigate to category id: " + categoryId, WebDriverManager.getWebDriver().getCurrentUrl().contains(categoryId));
    }

    /**
     * Verifies that SEO tag cloud is displayed
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I verify that SEO tag clouds are displayed$")
    public void iVerifyThatTagCloudsAreDisplayed() throws Throwable {
        CategorySplash splash = new CategorySplash();
        Wait.untilElementPresent("category_browse.seo_tag_links");
        Assert.assertTrue("ERROR - APP: SEO tag clouds are not displayed",
                splash.popularSearchLinksAvailable());
    }
}

