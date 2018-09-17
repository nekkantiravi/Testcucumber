package com.macys.sdt.projects.Marketing.SEO2017.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dbodige on 11/10/2017.
 */
public class SEOOptimization extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(SEOOptimization.class);

    @Then("^I should see updated seo page elements as below$")
    public void iShouldSeeUpdatedSeoPageElementsAsBelow(List<String> newElements) throws Throwable {
        List<String> oldElements = newElements.stream().filter(element -> !Elements.elementPresent("seo_tag_cloud." + element)).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP Few old elements are displayed" + oldElements.toString(), oldElements.isEmpty());
        logger.info("All the new elements are updated");
    }

    @Then("^I should see \"([^\"]*)\" query parameter$")
    public void iShouldSeeQueryParameter(String expectedUrl) throws Throwable {
        String[] linkmodulelink = url().split("-_-");
        Assert.assertTrue("Query parameter is not appending", url().contains(expectedUrl.replace("{link text}", linkmodulelink[2])));
    }

    @Then("^I verify that SEO tag clouds are displayed in the navigated page$")
    public void iVerifyThatSEOTagCloudsAreDisplayedInTheNavigatedPage() throws Throwable {
        List<WebElement> popularSearchLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links");
        if (bloomingdales()) {
            Assert.assertTrue("Links displaying in tag cloud are " + popularSearchLinks.size(), popularSearchLinks.size() == 6);
        } else {
            Assert.assertTrue("Links displaying in tag cloud are " + popularSearchLinks.size(), popularSearchLinks.size() >= 6 && popularSearchLinks.size() <= 10);

        }
        logger.info("Links are displaying in tag cloud");
    }
    @Then("^I should be landing on DLP Page$")
    public void iShouldBeLandingOnDLPPage() throws Throwable {
        Assert.assertTrue("Page is redirected to DLP", url().contains("/buy/"));
    }

    @And("^I should see the link module on UI$")
    public void iShouldSeeTheLinkModuleOnUI() throws Throwable {
        Elements.elementShouldBePresent("seo_tag_cloud.tag_cloud");
        logger.info("Popular Search is displaying");
    }

    @Then("^I should not see the meta name tag created in DOM$")
    public void iShouldNotSeeTheMetaNameTagCreatedInDOM() throws Throwable {
        Assert.assertFalse("ERROR APP: Robots meta tag is displaying in DOM", Elements.elementPresent("dynamic_landing.metaName"));
    }

    @Then("^I should see the meta name tag created in DOM$")
    public void iShouldSeeTheMetaNameTagCreatedInDOM() throws Throwable {
        Utils.threadSleep(3000, "ERROR ENV: Meta element is not loaded");
        String actualText = Elements.getElementAttribute("dynamic_landing.metaName", "content");
        Assert.assertTrue("ERROR APP: Expected Meta content is not displaying in DOM", actualText.contains("noindex, nofollow"));
    }

    @Then("^I should not see link module section displayed on UI$")
    public void iShouldNotSeeLinkModuleSectionDisplayedOnUI() throws Throwable {
        Assert.assertFalse("ERROR APP: Link Module Section is displaying on UI for Chanel Pages", Elements.elementPresent("seo_tag_cloud.new_tag_cloud"));
    }

    @And("^I should not see link module section displayed on view source$")
    public void iShouldNotSeeLinkModuleSectionDisplayedOnViewSource(List<String> tagCloudElements) throws Throwable {
        List<String> viewSourceElements = tagCloudElements.stream().filter(element -> Elements.elementPresent("seo_tag_cloud." + element)).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP DOM has these link module elements" + viewSourceElements.toString(), viewSourceElements.isEmpty());
        logger.info("Link module elements are not displaying in DOM");
    }

    @When("^I select a random product from chanel page$")
    public void iSelectARandomProductFromChanelPage() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse_product_thumbnails.product_thumbnail_list", 60);
        Clicks.click("category_browse_product_thumbnails.product_thumbnail_list");
        shouldBeOnPage("product_display");
        logger.info("Navigated to pdp page");
    }

    @Then("^I should see (\\d+) links in the link module section$")
    public void iShouldSeeLinksInTheLinkModuleSection(int expected_links_count) throws Throwable {
        List<WebElement> actualLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links");
        Assert.assertEquals("ERROR - APP : Expected and actual links count is not matching", expected_links_count, actualLinks.size());
        logger.info("Links displaying in tag cloud are  " +actualLinks.size());
    }
    }

