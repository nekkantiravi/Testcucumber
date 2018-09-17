package com.macys.sdt.projects.Selection.PDP.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import static com.macys.sdt.framework.utils.StepUtils.scrollToLazyLoadElement;

public class UnavailablePDPSteps {
    private static final Logger logger = Logger.getLogger(UnavailablePDPSteps.class.getName());
    private static String expected_brand_name, relative_url;

    @Given("^I visit any random unavailable pdp using mobile website$")
    public void Ivisitanyrandomunavailablepdpusingmobilewebsite(DataTable items) throws Throwable {
        List<List<String>> data = items.raw();
        int randomIndex = new Random().nextInt(data.size());
        relative_url = String.valueOf(data.get(randomIndex));
        System.out.println(relative_url);
        Navigate.visit(RunConfig.url+relative_url.substring(1, relative_url.length()-1));
    }

    @Then("^I should be able to see the not available banner$")
    public void iShouldBeAbleToSeeTheNotAvailableBanner() throws Throwable {
        Wait.untilElementPresent("category_browse.updp_error_msg");
        String actual_banner_msg = Elements.findElement("category_browse.updp_error_msg").getText();
        String expected_banner_msg = "This item is no longer available.";
        Assert.assertEquals(expected_banner_msg, actual_banner_msg);
        logger.info("Verified that unavailable banner is displayed");

    }

    @When("^I click on the product accordion button$")
    public void iClickOnTheProductAccordionButton() throws Throwable {
        Wait.untilElementPresent("category_browse.product_details_accordion");
        Clicks.click("category_browse.product_details_accordion");
    }

    @Then("^I should be able to see product id$")
    public void iShouldBeAbleToSeeProductId() throws Throwable {
        Wait.untilElementPresent("product_display_master.collection_details_container");
        String web_id = Elements.findElement("category_browse.web_id_details").getText();
        String expected_id = relative_url.replaceAll("\\D+","");
        String actual_id = web_id.replaceAll("\\D+","");
        Assert.assertEquals(expected_id, actual_id);
        logger.info("Verified that product ID is displayed and it matches");
    }

    @When("^I click on the review accordion button$")
    public void iClickOnTheReviewAccordionButton() throws Throwable {
        Wait.untilElementPresent("category_browse.review_accordion");
        Clicks.click("category_browse.review_accordion");
    }

    @Then("^I should be able to write a review$")
    public void iShouldBeAbleToWriteAReview() throws Throwable {
        Wait.untilElementPresent("product_display.write_a_review");
        Assert.assertTrue(Elements.elementPresent("product_display.write_a_review"));
        logger.info("Verified that review is displayed");

    }

    @When("^I click on shop similar items button$")
    public void iClickOnShopSimilarItemsButton() throws Throwable {
        Wait.untilElementPresent("category_browse.shop_similar_items_button");
        Clicks.click("category_browse.shop_similar_items_button");
    }

    @Then("^I should be navigated to search results page$")
    public void iShouldBeNavigatedToSearchResultsPage() throws Throwable {
        Wait.untilElementPresent("category_browse.results_text");
        String expected_text = Elements.findElement("category_browse.results_text").getText();
        Assert.assertEquals(expected_text.substring(0, expected_text.length() - 1), "Results");
        logger.info("Verified that search results are displayed");
    }

    @When("^I see an image displayed on PDP$")
    public void iSeeAnImageDisplayedOnPdp() throws Throwable {
        if (Elements.elementPresent("product_display.product_image")){
            Wait.untilElementPresent("product_display.product_image");
        }
    }

    @Then("^I verify that shop all button contains the brand name$")
    public void iVerifyThatShopAllButtonContainsTheBrandName() throws Throwable {
        if (Elements.elementPresent("category_browse.pdp_brand_name")){
            expected_brand_name = Elements.findElement("category_browse.pdp_brand_name").getText();
        }
    }

    @And("^I verify that the button will take me to the search page$")
    public void iVerifyThatTheButtonWillTakeMeToTheSearchPage() throws Throwable {
        if (Elements.elementPresent("product_display.product_image")){
            scrollToLazyLoadElement("category_browse.shop_all_items_button");
            Elements.findElement("category_browse.shop_all_items_button").click();
            Wait.untilElementPresent("category_browse.browse_category_name");
            String brand_name = Elements.findElement("category_browse.browse_category_name").getText();
            String actual_brand_name = brand_name.substring(0, brand_name.length() - 1);
            Assert.assertEquals(expected_brand_name, actual_brand_name);
            logger.info("Verified that brand search results are displayed");
        }
    }
}
