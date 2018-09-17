package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
  Created by Pawan on 6/1/2017.
  Verify basic atttributes of PDP page
*/

public class PDPsteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PDPsteps.class);

    @Given("^I verify all attributes of PDP in \"([^\"]*)\" mode$")
    public void i_verify_all_attributes_of_PDP_in_mode(String mode) throws Throwable {

        logger.info("PDP Url::" + WebDriverManager.getCurrentUrl());

        //common verification for bcom & mcom
        Wait.forPageReady();
        logger.info(Elements.getText("product_display.quantity"));
        Assert.assertTrue("product quantity is not displayed",
                Elements.elementPresent("product_display.quantity"));
        logger.info("Quantity is displayed");
        Assert.assertTrue("Add to bag button is not visible",
                Elements.elementPresent("product_display.add_to_bag_button"));
        logger.info("Add to bag button is visible");

        if (macys()) {
            if (!(mode.equalsIgnoreCase("domestic"))) {
                Assert.assertTrue("Product Title is not displayed",
                        Elements.elementPresent("product_display.product_title"));
                logger.info("Product Title is displayed");
                Assert.assertTrue("Price Box is not displayed",
                        Elements.elementPresent("product_display.price_box"));
                logger.info("Price Box is displayed");
                Clicks.click("product_display.expand_shipping_and_returns");
                logger.info("Shipping and Returns is present");
                Assert.assertTrue("Product Image is not displayed",
                        Elements.elementPresent("product_display.product_image"));
                logger.info("Product Image is displayed");

                //to check add to registry button which is only valid for Registry mode
                if (mode.equalsIgnoreCase("registry")) {

                    if (Elements.elementPresent(By.id("viewMemberItems_img"))) {
                        logger.info("Master product opened");
                        logger.info("single::" + Elements.elementPresent("product_display.add_to_registry"));
                        logger.info("mutli::" + Elements.findElements("product_display.add_to_registry"));
                    }

                    Assert.assertTrue("Add to registry button is not present",
                            Elements.elementPresent("product_display.add_to_registry")
                                    || (!Elements.findElements("product_display.add_to_registry").isEmpty()));
                    logger.info("Add to registry is present");

                    Assert.assertTrue("Add to wish list is not present",
                            Elements.elementPresent("product_display.add_to_list_" + mode)
                                    || (!Elements.findElements("product_display.add_to_list_" + mode).isEmpty()));
                    logger.info("Add to wish list is present");

                    Assert.assertTrue("Add to bag button is not visible",
                            Elements.elementPresent("product_display.add_to_bag_button") ||
                                    (!Elements.findElements("product_display.add_to_bag_button").isEmpty()));
                }
            }

            //domestic mode
            else {
                Elements.elementShouldBePresent("product_display.product_title_" + mode);
                logger.info("Product Title is displayed");
                Elements.elementShouldBePresent("product_display.product_image");
                logger.info("Product Image is displayed");
                Elements.elementShouldBePresent("product_display.price_box_" + mode);
                logger.info("Price box is displayed");
                Clicks.click("product_display.expand_shipping_and_returns");
                logger.info("Shipping and Returns is present");
                Assert.assertTrue("Add to wish list is not present",
                        Elements.elementPresent("product_display.add_to_list_" + mode));
                logger.info("Add to wish list is present");
            }

            //bloomingdales verification
        } else {
            Elements.elementShouldBePresent("product_display.product_title");
            logger.info("Product Title is displayed");
            Elements.elementShouldBePresent("product_display.price_box");
            logger.info("price Box Image is displayed");
            Elements.elementShouldBePresent("product_display.product_image");
            logger.info("Product Image is displayed");
            Elements.elementShouldBePresent("product_display.product_description_block");
            logger.info("Product description is displayed");

            if (mode.equalsIgnoreCase("domestic")) {
                Assert.assertTrue("Add to wish list is not present",
                        Elements.elementPresent("product_display.add_to_list"));
                logger.info("Add to wish list is present");
            }

            String[] tabs = new String[]{"PRODUCT DETAILS", "Reviews", "Shipping & Returns"};
            for (String tab : tabs) {
                Clicks.click(By.xpath("//*[contains(text(),'" + tab + "')]"));
            }


            //to check add to registry button. Only applicable for registry mode
            if (mode.equalsIgnoreCase("registry")) {
                Assert.assertTrue("Add to registry button is not present",
                        Elements.elementPresent("product_display.add_to_registry"));
                logger.info("Add to registry is present");
                Assert.assertTrue("Add to wish list is not present",
                        Elements.elementPresent("product_display.add_to_list"));
                logger.info("Add to wish list is present");
            }

        }

    }

    @Given("^I should see vertical recommendation is displayed on PDP page$")
    public void i_should_see_vertical_recommendation_is_displayed_on_PDP_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue("",
                Elements.elementPresent("recommendations.vertical_recommendations"));
        logger.info("Vertical recommendations are displayed");

    }

}