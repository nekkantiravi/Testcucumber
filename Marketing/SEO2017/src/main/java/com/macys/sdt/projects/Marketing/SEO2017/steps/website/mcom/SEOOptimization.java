package com.macys.sdt.projects.Marketing.SEO2017.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;


public class SEOOptimization {

    private static final Logger logger = LoggerFactory.getLogger(SEOOptimization.class);

    @When("^I select the random product from chanel page$")
    public void iSelectOneRandomProductFromChanelPage() throws Throwable {
        Wait.secondsUntilElementPresent("product_thumbnails.product_thumbnails", 60);
        Clicks.click("product_thumbnails.product_thumbnails");
        shouldBeOnPage("product_display");
        logger.info("Navigated to PDP page");
    }

    @Then("^I should see (\\d+) links in the link module$")
    public void iShouldSeeLinksInTheLinkModule(int expected_links_count) throws Throwable {
        List<WebElement> actualLinks = Elements.findElements("seo_tag_cloud.tag_cloud_links");
        Assert.assertEquals("ERROR - APP : Expected and actual links count is not matching", expected_links_count, actualLinks.size());
        logger.info("Links displaying in tag cloud are  " +actualLinks.size());
    }
}
