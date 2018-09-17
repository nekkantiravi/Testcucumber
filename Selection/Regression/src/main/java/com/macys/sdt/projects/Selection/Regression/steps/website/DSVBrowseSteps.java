package com.macys.sdt.projects.Selection.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by YH03402 on 8/8/2017.
 */
public class DSVBrowseSteps {

    private String expectedValue;
    private Date expectedExpiryDate;

    private static final Logger logger = LoggerFactory.getLogger(DSVBrowseSteps.class);

    @Then("^I verify registry left facet list$")
    public void iVerifyRegistryLeftFacetList() throws Throwable {
        int element_preset = 0;
        List<WebElement> ele = Elements.findElements("left_facet.facet_names");

        String elements[] = {"Registry Recommended", "Categories", "Brand", "Top Gifts", "Customers Top Rated", "Registry Guide", "Price"};
        for (WebElement element : ele) {
            logger.info("Elements displayed are::" + element.getText());
            if (Arrays.asList(elements).contains(element.getText()))
                element_preset++;
        }
        Assert.assertTrue("Fewer than three expected elements present in registry left nav.", element_preset >= 3);

    }

    @Then("^I verify left nav links of all brands page$")
    public void iVerifyLeftNavLinksOfAllBrandsPage() {
        List<String> linktext = new ArrayList<>();
        List<WebElement> brands = Elements.findElements("category_browse.left_nav_Brand_list");
        for (WebElement brand : brands) {
            linktext.add(brand.getText());
        }
        for (String brandtext : linktext) {
            logger.info("brandtext is::" + brandtext);
            Elements.findElement(By.linkText(brandtext)).click();
            Wait.forPageReady();
            CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl());
            Navigate.browserBack();
        }
    }

    @Then("^I fetch the \"([^\"]*)\" value & expiration$")
    public void i_fetch_the_macys_online_value_expiration(String cookieName) throws Throwable {
        expectedValue = WebDriverManager.getWebDriver().manage().getCookieNamed(cookieName).getValue();
        expectedExpiryDate = WebDriverManager.getWebDriver().manage().getCookieNamed(cookieName).getExpiry();

    }

    @Then("^I verify the \"([^\"]*)\" value & expiration is the same as Sign In page$")
    public void i_verify_the_macys_online_value_expiration_is_the_same_as_browse_page(String cookieName) throws Throwable {
        String actualValue = WebDriverManager.getWebDriver().manage().getCookieNamed(cookieName).getValue();
        Date actualExpiryDate = WebDriverManager.getWebDriver().manage().getCookieNamed(cookieName).getExpiry();
        Assert.assertEquals("Value of Cookie::" + cookieName + "not matched", expectedValue, actualValue);
        Assert.assertEquals("ExpiryDate of Cookie::" + cookieName + "not matched", expectedExpiryDate, actualExpiryDate);
    }

    @Then("^I navigate to \"([^\"]*)\" sub category from left nav links$")
    public void i_navigate_to_sub_category_from_left_nav_links(String subCategory) throws Throwable {
        try {
            Wait.secondsUntilElementPresent("category_browse.sub_category", 15);
            Clicks.clickElementByText("category_browse.sub_category", subCategory);
        } catch (Exception e) {
            Assert.fail("ERROR - TIMEOUT: Could not browse to " + subCategory + ": " + e.getMessage());
        }
    }

    @Then("^I should see \"([^\"]*)\" facet value selected is included in the title tag$")
    public void i_should_see_facet_value_selected_is_included_in_the_title_tag(String value) throws Throwable {

        try {
            Wait.forLoading("left_facet.loading");
            String selectedFacet = Elements.getText("left_facet.facetBreadcrumbs");
            Assert.assertTrue("Error facet value doesn't matches with actual facet applied",
                    selectedFacet.equalsIgnoreCase(value));
        } catch (Exception e) {
            Assert.fail("Error Applied facet value is not displayed" + e.getMessage());
        }
        Assert.assertTrue("Facet value is not included in the title tag",
                WebDriverManager.getCurrentUrl().contains(value));
        logger.info("Got Selected Facet value");
    }

    @When("^I click on Clear All Selections$")
    public void i_click_on_Clear_All_Selections() throws Throwable {
        try {
            Wait.untilElementPresent("category_browse.clear_all_facet_selections");
            Clicks.javascriptClick("category_browse.clear_all_facet_selections");
            Thread.sleep(500);
        } catch (Exception e) {
            Assert.fail("Error clicking Clear All button" + e.getMessage());
        }
    }

    @Then("^I should not see selected facet values in facets selection screen$")
    public void i_should_not_see_selected_facet_values_in_facets_selection_screen() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Facet values are not cleared",
                Elements.findElements("category_browse.facetBreadcrumbs").isEmpty());
    }

    @Then("^I verify the price details in browse page continues to PDP$")
    public void iVerifyThePriceDetailsInBrowsePageContinuesToPDP() throws Throwable {
        //fetching Price from first Product on browse page
        String browsePage_price;
        String pdpPage_price;
        browsePage_price = Elements.getText("category_browse.product_price").replace("-","");
        logger.info("Browse page Price::"+browsePage_price);
        //navigating to first product PDP page
        Clicks.click("category_browse.product_thumbnail");
        //fetching Price from PDP page
        pdpPage_price = Elements.getText("pdp.product_price").replace("Sale $","");
        logger.info("PDP page Price ::"+pdpPage_price);
        //verifying that the price on PDP is same as on Browse page
        Assert.assertTrue("Product Price and Currency symbol is not same for browse and PDP page", browsePage_price.contains(pdpPage_price));
    }


}