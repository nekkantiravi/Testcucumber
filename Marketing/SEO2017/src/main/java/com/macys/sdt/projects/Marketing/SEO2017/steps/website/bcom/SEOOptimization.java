package com.macys.sdt.projects.Marketing.SEO2017.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

/**
 * Created by dbodige on 11/10/2017.
 */
public class SEOOptimization extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(SEOOptimization.class);

    @Then("^I should see updated seo page elements as below in pdp page$")
    public void iShouldSeeUpdatedSeoPageElementsAsBelowInPdpPage(List<String> newElements) throws Throwable {
        List<String> oldElements = newElements.stream()
                .filter(element -> !Elements.elementPresent("seo_product_details." + element))
                .collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP Few old elements are displayed" + oldElements.toString(), oldElements.isEmpty());
        logger.info("All the new elements are updated");
    }

    @Then("^I should see seo page elements are updated as below$")
    public void iShouldSeeSeoPageElementsAreUpdatedAsBelow(List<String> newElements) throws Throwable {
        List<String> oldElements = newElements.stream()
                .filter(element -> !Elements.elementPresent("seo_tag_cloud." + element))
                .collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP Few old elements are displayed" + oldElements.toString(), oldElements.isEmpty());
        logger.info("All the new elements are updated");
    }

    @When("^I navigate to left nav \"([^\"]*)\" category$")
    public void iNavigateToLeftNavCategory(String category) throws Throwable {
        Clicks.clickElementByText("designer_static.brand_menu_items", category);
        logger.info("Navigated to kids left nav category");
    }

    @And("^I navigate to \"([^\"]*)\" DLP page$")
    public void iNavigateToDLPPage(String keyword) throws Throwable {
        Clicks.clickElementByText("designer_static.brands_list", keyword);
        logger.info("Navigated to respective keyword dlp page");
    }

    @When("^I navigate to the keyword with \"([^\"]*)\" on the page$")
    public void iNavigateToTheKeywordWithOnThePage(String number_of_products) throws Throwable {
        JSONObject dlpCategories = getFileDataInJson(getResourceFile("dlp_keywords.json"));
        String dlpCategory = dlpCategories.getString("keyword_with_" + number_of_products);
        Navigate.visit(RunConfig.url + dlpCategory);
        logger.info("Navigated to DLP category:" + dlpCategory);
    }

    @And("^I should see six links in the tag cloud$")
    public void iShouldSeeSixLinksInTheTagCloud() throws Throwable {
        List<WebElement> popularSearchLinks = Elements.findElements("category_splash.tag_cloud");
        if (bloomingdales()) {
            Assert.assertTrue("Links displaying in tag cloud are " + popularSearchLinks.size(), popularSearchLinks.size() >= 6);
            logger.info("Links are displaying in tag cloud");
        } else {
            Assert.assertTrue("Links displaying in tag cloud are " + popularSearchLinks.size(), popularSearchLinks.size() >= 6 && popularSearchLinks.size() <= 10);
            logger.info("Links are displaying in tag cloud");
        }
    }

    @Then("^should see \"([^\"]*)\" query parameter$")
    public void shouldSeeQueryParameter(String quaryParameter) throws Throwable {
        String[] linkmodulelink = url().split("-_-");
        String actualvalue = quaryParameter.replace("{link text}", linkmodulelink[2]);
        Assert.assertTrue("Query parameter is not appending", url().contains(actualvalue));
        logger.info("current url contains query parameter");
    }

    @Then("^I should not see link module displayed on UI$")
    public void iShouldNotSeeLinkModuleDisplayedOnUI() throws Throwable {
        Assert.assertFalse("ERROR APP: Link Module Section is displaying on UI for Chanel Pages", Elements.elementPresent("seo_tag_cloud.new_tag_cloud"));
    }

    @And("^I should not see link module displayed on view source$")
    public void iShouldNotSeeLinkModuleDisplayedOnViewSource(List<String> tagCloudElements) throws Throwable {
        List<String> viewSourceElements = tagCloudElements.stream().filter(element -> Elements.elementPresent("seo_tag_cloud." + element)).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP DOM has these link module elements" + viewSourceElements.toString(), viewSourceElements.isEmpty());
        logger.info("Link module elements are not displaying in DOM");
    }

    @When("^I select one random product from chanel page$")
    public void iSelectOneRandomProductFromChanelPage() throws Throwable {
        Wait.secondsUntilElementPresent("product_thumbnails.product_thumbnail_link", 60);
        Clicks.click("product_thumbnails.product_thumbnail_link");
        shouldBeOnPage("product_display");
        logger.info("Navigated to PDP page");
    }
}
