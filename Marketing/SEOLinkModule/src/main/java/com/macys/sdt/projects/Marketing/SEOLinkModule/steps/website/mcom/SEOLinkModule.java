package com.macys.sdt.projects.Marketing.SEOLinkModule.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SEOLinkModule extends StepUtils {

    List<String> expectedFontFamily = Arrays.asList("\"Helvetica Neue\", Helvetica, Arial, sans-serif","\"Helvetica Neue\", Helvetica, Helvetica, Arial, sans-serif");
    private static final Logger logger = LoggerFactory.getLogger(SEOLinkModule.class);
    public String expectedUrl;

    @And("^I should see keywords in the seo tag cloud section one after the another$")
    public void iShouldSeeKeywordsInTheSeoTagCloudSectionOneAfterTheAnother() throws Throwable {
        List<WebElement> seo_tag_links = Elements.findElements("seo_tag_cloud.tag_cloud_links", WebElement::isDisplayed);
        Assert.assertFalse("ERROR - APP : Keywords in the seo tag cloud section not displaying one after the another", seo_tag_links.isEmpty());
        logger.info("Popular Search is displaying" + seo_tag_links);
    }

    @Then("^I should see the tag cloud$")
    public void iShouldSeeTheTagCloud() throws Throwable {
        Elements.elementShouldBePresent("seo_tag_cloud.tag_cloud");
        logger.info("Popular Search is displaying");
    }

    @And("^I should see all the keywords are underlined$")
    public void iShouldSeeAllTheKeywordsAreUnderlined() throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !e.getCssValue("text-decoration-line").equals("underline"))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Underline is not displaying for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Underline is displaying for all keywords");
    }

    @And("^I should see tag cloud links font family is Helvetica Neue LT Std$")
    public void iShouldSeeTagCloudLinksFontFamilyIsHelveticaNeueLTStd() throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !expectedFontFamily.contains(e.getCssValue("font-family")))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Font family is not displaying for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Tag cloud links font family is Helvetica Neue LT Std");
    }

    @Then("^I should see tag cloud links font size is (\\d+)px$")
    public void i_should_see_tag_cloud_links_font_size_is_px(int sizeofpixels) throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !e.getCssValue("font-size").equals(sizeofpixels + "px"))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Font size is not 18 pixels for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Tag cloud links font size is 14px");
    }

    @And("^I should see tag cloud title font family is Helvetica Neue LT Std$")
    public void iShouldSeeTagCloudTitleFontFamilyIsHelveticaNeueLTStd() throws Throwable {
        Assert.assertTrue("ERROR - APP : Font family is not expected",
                expectedFontFamily.contains(Elements.getElementCSSAttribute("seo_tag_cloud.tag_cloud", "font-family")));
        logger.info("Tag cloud title font family is Helvetica Neue LT Std");
    }

    @And("^I should see tag cloud title font weight is Bold$")
    public void iShouldSeeTagCloudTitleFontWeightIsBold() throws Throwable {
        Assert.assertEquals("ERROR - APP : Font weight is not expected",
                Elements.getElementCSSAttribute("seo_tag_cloud.tag_cloud", "font-weight"), "700");
        logger.info("Tag cloud title font weight is Bold");
    }

    @When("^I click on any keyword in the link module$")
    public void iClickOnAnyKeywordInTheLinkModule() throws Throwable {
        WebElement linkElement = Elements.getRandomElement("seo_tag_cloud.tag_cloud_links", (e) -> e.getAttribute("href").contains((macys() ? "/featured/" : "/buy/")));
        expectedUrl = linkElement.getAttribute("href");
        Clicks.click(linkElement);
        logger.info("Selected keyword from popular search");
    }

    @Then("^I should see the relevant page is displayed$")
    public void iShouldSeeTheRelevantPageIsDisplayed() throws Throwable {
        Assert.assertTrue("ERROR - APP : Search results for " + url() + " should be displayed on URL",
                url().contains(expectedUrl));
        logger.info("Relevant page is displayed");
    }

    @And("^I should see tag cloud title font size is forteen pixels$")
    public void iShouldSeeTagCloudTitleFontSizeIsForteenPixels() throws Throwable {
        String titleFont = macys()? "18px":"12px";
        Assert.assertEquals("ERROR - APP : Font size is not forteen pixels",
                Elements.getElementCSSAttribute("seo_tag_cloud.tag_cloud", "font-size"), titleFont);
        logger.info("Tag cloud title font size is 14px");
    }

    @Then("^I should see the tag cloud above the footer$")
    public void iShouldSeeTheTagCloudAboveTheFooter() throws Throwable {
        Wait.secondsUntilElementPresent("seo_tag_cloud.popular_search_before_bloomies_footer", 60);
        Assert.assertTrue("ERROR - APP : Popular search is not displaying above the Footer", Elements.elementPresent("seo_tag_cloud.popular_search_before_bloomies_footer"));
        logger.info("Popular search is displaying above the Footer");
    }

    @And("^I should see the tag cloud below the copy block$")
    public void iShouldSeeTheTagCloudBelowTheCopyBlock() throws Throwable {
        Wait.secondsUntilElementPresent("seo_tag_cloud.copy_block_before_popular_search", 60);
        Assert.assertTrue("ERROR - APP : Copyblock is not displaying above the Popular search", Elements.elementPresent("seo_tag_cloud.copy_block_before_popular_search"));
        logger.info("Copyblock is displaying above the Popular search");
    }

    @And("^I should see all the links are underlined$")
    public void iShouldSeeAllTheLinksAreUnderlined() throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !e.getCssValue("text-decoration-line").equals("underline"))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Underline is not displaying for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Underline is displaying for all keywords");
    }

    @And("^I should see tag cloud links font size is twelve pixels$")
    public void iShouldSeeTagCloudLinksFontSizeIsTwelvePixels() throws Throwable {
        List<String> failedLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links").stream()
                .filter((e) -> !e.getCssValue("font-size").equals("12px"))
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Font size is not 12 pixels for links: " + failedLinks, failedLinks.isEmpty());
        logger.info("Tag cloud links font size is 12px");
    }

    @And("^I should see tag cloud title is in uppercase$")
    public void iShouldSeeTagCloudTitleIsInUppercase() throws Throwable {
        String actualText = Elements.getText("seo_tag_cloud.tag_cloud");
        Assert.assertTrue("Cloud Title is not displaying in uppercase", actualText.equals(actualText.toUpperCase()));
        logger.info("Cloud Title is displaying in uppercase");

    }

    @And("^I should see (six|ten) links in the tag cloud$")
    public void iShouldSeeSixLinksInTheTagCloud() throws Throwable {
        List<WebElement> popularSearchLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links");
        Assert.assertTrue("Links displaying in tag cloud are "+popularSearchLinks.size(),
                popularSearchLinks.size() >= 6 && popularSearchLinks.size() <= 10);
        logger.info("Links are displaying in tag cloud");
    }


}