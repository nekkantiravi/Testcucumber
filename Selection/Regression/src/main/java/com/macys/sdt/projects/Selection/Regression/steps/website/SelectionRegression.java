package com.macys.sdt.projects.Selection.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rnekkanti on 9/20/2017.
 */
public class SelectionRegression {
    private static final Logger logger = LoggerFactory.getLogger(SelectionRegression.class);

    @Then("^I should see updated redirection URL link$")
    public void iShouldSeeUpdatedRedirectionURLLink() throws Throwable {
        String brand_URL= Elements.findElement("product_display.goto_brand_name").getAttribute("href");
        Assert.assertTrue("ERROR - APP: Url does not contains /shop/",brand_URL.contains("/shop/"));
        logger.info("Brand contains /shop/ url");
    }

    @Then("^I should redirect to the updated URL$")
    public void iShouldRedirectToTheUpdatedURL() throws Throwable {
        Assert.assertTrue("ERROR - APP: Current url does not contains /shop/", WebDriverManager.getCurrentUrl().contains("/shop/"));
        logger.info("Current url contains /shop/");
    }

}
