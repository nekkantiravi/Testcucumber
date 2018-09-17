package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicLandingPage extends StepUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicLandingPage.class);
    PageNavigation pageNavigation = new PageNavigation();
    ShopAndBrowse sb = new ShopAndBrowse();

    @Then("^I verify that Page Title should have between (\\d+) - (\\d+) characters$")
    public void iVerifyThatPageTitleShouldHaveBetweenCharacters(int low, int high) throws Throwable {
        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        Assert.assertTrue("page title size should be less than or equal to " + high , pageTitle.length() <=high);
        Assert.assertTrue("page title size should be greater than or equal to " + low, pageTitle.length() >= low);
    }

    @Then("^I verify that Meta Description should have between (\\d+) - (\\d+) characters$")
    public void iVerifyThatMetaDescriptionShouldHaveBetweenCharacters(int low, int high) throws Throwable {
        String metaDescription = Elements.findElement("header.meta_description").getAttribute("content");
        Assert.assertTrue("page title size should be less than or equal to " + high , metaDescription.length() <=high);
        Assert.assertTrue("page title size should be greater than or equal to " + low, metaDescription.length() >= low);
    }
    @And("^I verify that the page title contains \"([^\"]*)\"$")
    public void iVerifyThatThePageTitleContainsPageName(String title){
        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        Assert.assertTrue("page title is not containing Bloomingdale's", pageTitle.contains(title));
            }

    @Given("^I am on DynamicLandingPage in \"([^\"]*)\" mode by using direct url$")
    public void i_am_on_DynamicLandingPage_in_mode_by_using_direct_url(String mode) throws Throwable {
        String dynamic_landing_page_url;
        switch (mode) {
            case "domestic":
                dynamic_landing_page_url = RunConfig.url + "/buy/dkny/Brand/DKNY?id=1001351&brand=DKNY&cm_sp=shop_by_brand-_-ALL%20DESIGNERS-_-DKNY";
                Navigate.visit(dynamic_landing_page_url);
                break;
        }
        LOGGER.info("Successfully naviated to dynamic landing page");

    }

    @Then("^I should see \"([^\"]*)\" attribute is appended in robots meta tag in view page source$")
    public void iShouldSeeAttributeIsAppendedInRobotsMetaTagInViewPageSource(String expectedValues) throws Throwable {
        Assert.assertTrue("ERROR APP: Expected Meta content is not displaying", Elements.findElement("search_result.metaName").getAttribute("content").equals(expectedValues));
    }

}
