package com.macys.sdt.projects.Discovery.Regression.steps.website.searchandsend;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
  Created by Lokendar Pullagurla on 7/4/2017.
  Verify basic atttributes of SearchAndSend page
*/

public class SearchAndSend extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(SearchAndSend.class);

    @Given("^I am on SNS POS simulator page$")
    public void iAmOnSNSPOSSimulatorPage() throws Throwable {
        Navigate.visit("sns_home");
        try{
            shouldBeOnPage("sns_home");
        }catch (Exception e){
            Assert.fail("ERROR - ENV: POS/SNS environments are not accessible");
        }
        logger.debug("Navigate to Search And Send Home Page Successfully");
    }

    @When("^I search for \"([^\"]*)\" as keyword in SNS POS$")
    public void iSearchForAsKeywordInSNSPOS(String keyword) throws Throwable {
        TextBoxes.typeTextNEnter("sns_home.search_term", keyword);
        logger.debug("Search for '"+keyword+"' keyword");
    }

    @Then("^I should see page title \"([^\"]*)\" in SNS POS \"([^\"]*)\" page$")
    public void iShouldSeePageTitleInSNSPOSPage(String title, String pageName) throws Throwable {
        switch (pageName){
            case "search results":
                Assert.assertTrue("ERROR - APP: Expected title:'"+title+"' is not displayed on page:"+pageName, Elements.findElement("sns_search_results.header_title").getText().equalsIgnoreCase(title));
        }
        logger.debug("Validated page title:'"+title+"' for '"+pageName+"' page");
    }

    @And("^I should see products on POS search results page$")
    public void iShouldSeeProductsOnPOSSearchResultsPage() throws Throwable {
        Assert.assertTrue("ERROR - APP: Search results are not displayed on POS environment", Elements.findElements("sns_search_results.products").size() > 0);
        logger.debug("Validated product visibility on search reulsts page");
    }
}