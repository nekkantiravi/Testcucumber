package com.macys.sdt.projects.Selection.Regression.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchAndBrowse {

    private static final Logger logger = LoggerFactory.getLogger(SearchAndBrowse.class);

    @When("^I select color swatch product$")
    public void iSelectColorSwatchProduct() throws Throwable {
       // Wait.forPageReady();
        Wait.secondsUntilElementPresent("search_and_browse.color_swatch_product", 20);
        Clicks.click("search_and_browse.color_swatch_product");
        logger.info("Successfully clicked on product");
    }
    @When("^I select beauty product with more than one color$")
    public void iSelectBeautyProduct() throws Throwable {
        Wait.forPageReady();
        Clicks.click("search_and_browse.beauty_product_with_color");
        logger.info("Successfully clicked on beauty product");
    }

    @When("^I select first member product of master product$")
    public void I_select_first_member_product() throws Throwable {
        Wait.secondsUntilElementPresent("search_and_browse.member_product", 15);
        Clicks.click("search_and_browse.member_product");
        Wait.forPageReady();
        logger.info("Successfully clicked on beauty product");
    }
}
