package com.macys.sdt.projects.Selection.List.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by m785440 on 12/5/17.
 */
public class Hearts extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(Hearts.class);
    SoftAssertions softly = new SoftAssertions();
    String productName="";
    String productName1="";
    @And("^I navigate to mew \"([^\"]*)\" page by url \"([^\"]*)\"$")
    public void I_navigate_to_browse_page_by_url(String mode, String url) throws Throwable {
        Navigate.visit(RunConfig.url + url);
        Wait.forPageReady();
        if (mode.equals("browse")) {
            shouldBeOnPage("category_browse");
        } else {
            shouldBeOnPage("reg_browse");
        }
        logger.info("Browse page loaded successfully");
    }

    @And("^I click HEART icon to add product to list$")
    public void i_click_HEART_icon_to_add_product_to_list() throws Throwable {
        Wait.forPageReady();
        Elements.elementInView("browsepage.first_product");
        productName=Elements.findElement(Elements.element("browsepage.first_product_name")).getText();
        Clicks.click("browsepage.first_product");
        Thread.sleep(2000);

        softly.assertThat(Elements.findElement(Elements.element("browsepage.add_heart_confirmation")).getText()).isEqualTo("Added to list");
        softly.assertAll();
    }

    @And("^I click UNHEART icon to add product to list$")
    public void i_click_UNHEART_icon_to_add_product_to_list() throws Throwable {
        Wait.forPageReady();
        Elements.elementInView("browsepage.first_product");
        Clicks.click("browsepage.first_product");
        Thread.sleep(2000);

       // softly.assertThat(Elements.findElement(Elements.element("browsepage.add_heart_confirmation")).getText()).isEqualTo("Added to list");
       // softly.assertAll();
    }

    @When("^I navigate to list page on MEW$")
    public void i_navigate_to_list_page_on_MEW() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/home?experience=responsive");
        Wait.forPageReady();
        //shouldBeOnPage("browsepage");
        logger.info("list page loaded successfully");
    }

    @When("^I click on Product and verify the Product Details$")
    public void i_click_on_Product_and_verify_the_Product_Details() throws Throwable {
        Elements.elementInView("listhome.first_product_landing");
        Clicks.click("listhome.first_product_landing");
        Thread.sleep(3000);
        productName1=Elements.findElement(Elements.element("listpage.product_name")).getText();
        softly.assertThat(productName).isEqualTo(productName1);


    }



}
