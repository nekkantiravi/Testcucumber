package com.macys.sdt.projects.Marketing.SEOLinkModule.steps.mew.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Marketing.SEOLinkModule.actions.SEOFacet;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class SEOLinkModule extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(SEOLinkModule.class);
    public String expectedUrl;

    @And("^I should see copy block without the header Popular Related Searches$")
    public void iShouldSeeCopyBlockWithoutTheHeaderPopularRelatedSearches() throws Throwable {
        Assert.assertFalse("ERROR - APP : Copy block header is displayed on Mobile UI", Elements.elementPresent("flexible_template.copy_block_header"));
        logger.info("copy block header is not displaying on UI");
    }

    @Then("^I should see the tag cloud above the footer in the navigated page$")
    public void iShouldSeeTheTagCloudAboveTheFooterInTheNavigatedPage() throws Throwable {
        Wait.secondsUntilElementPresent("seo_tag_cloud.popular_search_before_footer", 60);
        Assert.assertTrue("ERROR - APP : Popular search is not displaying above the Footer", Elements.elementPresent("seo_tag_cloud.popular_search_before_footer"));
        logger.info("Popular search is displaying above the Footer");
    }

    @And("^I should see keywords in the seo tag cloud section one after the another in the navigated page$")
    public void iShouldSeeKeywordsInTheSeoTagCloudSectionOneAfterTheAnotherInTheNavigatedPage() throws Throwable {
        List<WebElement> seoTagLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links", WebElement::isDisplayed);
        Assert.assertFalse("ERROR - APP : Keywords in the seo tag cloud section not displaying one after the another", seoTagLinks.isEmpty());
        logger.info("Popular Search is displaying" + seoTagLinks);
    }

    @And("^I should see six links in the tag cloud in the navigated page$")
    public void iShouldSeeSixLinksInTheTagCloudInTheNavigatedPage() throws Throwable {
        List<WebElement> popularSearchLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links");
        Assert.assertTrue("Links displaying in tag cloud are " + popularSearchLinks.size(),
                popularSearchLinks.size() >= 6 && popularSearchLinks.size() <= 10);
        logger.info("Links are displaying in tag cloud");
    }

    @And("^I should see all the keywords are underlined in the navigated page$")
    public void iShouldSeeAllTheKeywordsAreUnderlinedInTheNavigatedPage() throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !e.getCssValue("text-decoration-line").equals("underline"))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Underline is not displaying for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Underline is displaying for all keywords");
    }

    @And("^I should see tag cloud links font size is (\\d+)px in the navigated page$")
    public void iShouldSeeTagCloudLinksFontSizeIsPxInTheNavigatedPage(int sizeofpixels) throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !e.getCssValue("font-size").equals(sizeofpixels + "px"))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Font size is not 18 pixels for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Tag cloud links font size is 14px");
    }

    @And("^I should see tag cloud title font size is eighteen pixels in the navigated page$")
    public void iShouldSeeTagCloudTitleFontSizeIsEighteenPixelsInTheNavigatedPage() throws Throwable {
        String titleFont = macys() ? "18px" : "14px";
        Assert.assertEquals("ERROR - APP : Font size is not forteen pixels",
                Elements.getElementCSSAttribute("seo_tag_cloud.tag_cloud", "font-size"), titleFont);
        logger.info("Tag cloud title font size is 18px");
    }

    @And("^I should see tag cloud title font weight is Bold in the navigated page$")
    public void iShouldSeeTagCloudTitleFontWeightIsBoldInTheNavigatedPage() throws Throwable {
        Assert.assertEquals("ERROR - APP : Font weight is not expected",
                Elements.getElementCSSAttribute("seo_tag_cloud.tag_cloud", "font-weight"), "700");
        logger.info("Tag cloud title font weight is Bold");
    }

    @When("^I click on any keyword in the link module in the navigated page$")
    public void iClickOnAnyKeywordInTheLinkModuleInTheNavigatedPage() throws Throwable {
        WebElement linkElement = Elements.getRandomElement("seo_tag_cloud.tag_cloud_links", (e) -> e.getAttribute("href").contains((macys() ? "/featured/" : "/buy/")));
        expectedUrl = linkElement.getAttribute("href");
        Clicks.click(linkElement);
        logger.info("Selected keyword from popular search");
    }

    @Then("^I should see the correct page is displayed$")
    public void iShouldSeeTheCorrectPageIsDisplayed() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("ERROR - APP : Search results for " + url + " should be displayed on URL",
                url.contains(expectedUrl));
        logger.info("Relevant page is displayed");
    }

    @When("^I navigate to category splash page$")
    public void iNavigateToCategorySplashPage() throws Throwable {
        JSONObject categoriesList = getFileDataInJson(getResourceFile("category.json"));
        JSONArray categories = categoriesList.getJSONArray("catsplash_category_list");
        Random random = new Random();
        int number = random.nextInt(categories.length());
        String category = (String) categories.get(number);
        Navigate.visit(RunConfig.url + "/shop/?id=" + category);
        logger.info("Navigated to category splash " + category);
    }

    @And("^I should see the tag cloud below the copy block in the navigated page$")
    public void iShouldSeeTheTagCloudBelowTheCopyBlockInTheNavigatedPage() throws Throwable {
        Wait.secondsUntilElementPresent("seo_tag_cloud.copy_block_before_popular_search", 60);
        Assert.assertTrue("ERROR - APP : Copyblock is not displaying above the Popular search", Elements.elementPresent("seo_tag_cloud.copy_block_before_popular_search"));
        logger.info("Copyblock is displaying above the Popular search");
    }

    @And("^I should see tag cloud title is in uppercase in the navigated page$")
    public void iShouldSeeTagCloudTitleIsInUppercaseInTheNavigatedPage() throws Throwable {
        String actualText = Elements.getText("seo_tag_cloud.tag_cloud");
        Assert.assertTrue("Cloud Title is not displaying in uppercase", actualText.equals(actualText.toUpperCase()));
        logger.info("Cloud Title is displaying in uppercase");
    }

    @When("^I select a random product$")
    public void i_select_a_random_product() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse_product_thumbnails.product_thumbnail_link", 60);
        Clicks.click(Elements.getRandomElement("category_browse_product_thumbnails.product_thumbnail_link"));
        logger.info("Random Product is selected ");
    }


    @And("^I should see container for the structured data should be inside of the <head> element in DOM$")
    public void iShouldSeeContainerForTheStructuredDataShouldBeInsideOfTheHeadElementInDOM(List<String> pdpStrucutreTags) throws Throwable {
        String productData = Elements.findElement("product_details.seo_product").getAttribute("innerHTML");
        JSONObject jsonObject = new JSONObject(productData);
        for (String e : pdpStrucutreTags) {
            logger.info("Available Tags in PDP Structure Data are " + jsonObject.keySet());
            Assert.assertTrue("ERROR - APP : PDP Structure" + e + "is not displaying or not available", jsonObject.keySet().contains(e));
            SEOFacet.pdpStrutureDataForBrand(jsonObject);
            SEOFacet.pdpStrutureDataForOffers(jsonObject);
            SEOFacet.pdpStrutureDataForAggregateRating(jsonObject);
            SEOFacet.pdpStrutureDataForReviews(jsonObject);
            logger.info("All Tags in PDP Structure Data are displaying as exected ");
        }
    }
}