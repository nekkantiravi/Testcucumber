package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by YC05165 on 6/7/2017.
 */
public class SearchCheckout extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(SearchCheckout.class);
    public static Product recentProduct;

    @When("^I navigate to home page from checkout$")
    public void i_navigate_to_home_page_from_checkout() throws Throwable {
        Clicks.click("search_checkout.back_button");
        Navigate.visit("home");
    }



    @And("^I add product to my bag from PDP(?: Page)?$")
    public void I_add_product_to_my_bag_from_PDP_Page() throws Throwable {
        Wait.forPageReady();
        boolean addedToBag = false;
        Assert.assertFalse("ERROR - DATA : Product ( " + (recentProduct == null ? "" : String.valueOf(recentProduct.id)) + " ) is unavailable on product display page!!", !Elements.elementPresent("product_display.add_to_bag_button") && Elements.elementPresent("product_display.availability_error"));
        try {
            Wait.forPageReady();
            int retries = 5;
            for (int count = 0; count < retries && !addedToBag; count++) {
                try {
                    ProductDisplay.selectRandomColor();
                    ProductDisplay.selectRandomSize();
                    Thread.sleep(500);
                    Clicks.click("product_display.add_to_bag_button");
                    if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                        Clicks.clickIfPresent("product_display.add_to_bag_button");
                    }
                    if (MEW() && macys() && !onPage("add_to_bag")) {
                        Clicks.clickIfPresent("home.close_app_banner");
                        Clicks.click("home.my_bag");
                        return;
                    }
                    addedToBag = ProductDisplay.addedToBag();
                    if (RunConfig.debugMode) {
                        logger.info("IsProductAddedToBag:" + addedToBag + ", Add to bag retry count:" + (count + 1));
                    }
                } catch (Exception e) {
                    logger.error("Exception while adding product:" + e.getMessage());
                }
            }
            Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
            if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                Clicks.clickIfPresent("product_display.technical_error");
            }
            if (isErrorPaneVisible()) {
                Clicks.click("home.popup_close");
            }
        } catch (IllegalArgumentException | NoSuchElementException e) {
            logger.error("Error while adding to bag: " + e);
        }
    }

    @Then("^I should see category in (Dinnerware|Bakeware) view on browse page$")
    public void i_should_see_category_in_view_on_browse_page(String viewType) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("search_checkout." + viewType + "_view_category",30);
                Assert.assertTrue("category is not displayed in " + viewType + " view on browse page", Elements.elementPresent("search_checkout." + viewType + "_view_category"));
    }

    @Then("^I verify Buy Online Pickup$")
    public void I_verify_Buy_Online_Pickup() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
        if (Elements.elementPresent("pdp.sizes_array")) {
            Clicks.click(product_sizes.get(0));
        }
        Navigate.browserRefresh();
        Navigate.scrollPage(0,200);
        Clicks.clickIfPresent(Elements.element("search_checkout.availability_title"));
        softly.assertThat(Elements.elementPresent("search_checkout.availability_title")).as("availability_title").isEqualTo(true);
        String availability_instock_text = Elements.findElement(Elements.element("search_checkout.availability_title")).getText();
        softly.assertThat(availability_instock_text).as("availability_instock_text").isEqualTo("In stock");
        String pickup_in_store_text = Elements.findElement(Elements.element("search_checkout.pickup_in_store_text")).getText();
        softly.assertThat(pickup_in_store_text).as("pickup_in_store_text").isEqualTo("Pick up in-store");
        Wait.secondsUntilElementPresent("search_checkout.pickup_in_store_link", 10);
        if(Elements.elementPresent("search_checkout.pickup_in_store_link")) {
            Thread.sleep(1000);
            Clicks.click(Elements.element("search_checkout.pickup_in_store_link"));
        }else{
            Clicks.click("search_checkout.pickup_in_other_store_link");
        }
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("search_checkout.availability_check_zip", 10);
        softly.assertThat(Elements.elementPresent("search_checkout.availability_check_zip")).as("availability_check_zip").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("search_checkout.availability_check_search_btn")).as("availability_check_search_btn").isEqualTo(true);

        if(Elements.elementPresent("search_checkout.select_Store")) {
            Thread.sleep(1000);
            Clicks.click(Elements.element("search_checkout.select_Store"));
            Thread.sleep(1000);
        }else{
            Assert.assertTrue("product is not displayed for bops",Elements.elementPresent("search_checkout.bops_error"));
        }

    }

    @Then("^I verify BOPS section on checkout page$")
    public void i_verify_BOPS_section_on_checkout_page() throws Throwable {
        Thread.sleep(1000);
        Navigate.browserRefresh();
        Wait.forPageReady();
        Wait.secondsUntilElementPresentAndClick("add_to_bag.checkout",30);
        Wait.forPageReady();
        if(Elements.elementPresent(By.id("guest-checkout")))
            Clicks.click(By.id("guest-checkout"));
        Wait.untilElementPresent("search_checkout.bops_Section");
        Assert.assertTrue("BOPS section is not displayed", Elements.elementPresent("search_checkout.bops_Section"));
    }

}


