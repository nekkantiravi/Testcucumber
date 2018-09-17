package com.macys.sdt.projects.Selection.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.MEW.pages.ProductDisplay;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by yc03pk6 on 11/03/2017.
 */
public class BagPageSteps extends Utils {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    private String productName = "";
    private String productPrice = "";
    private String productColor = "";
    private String productBrand = "";

    @When("^I verify see Add to Bag button on PDP page$")
    public void I_should_see_Add_to_Bag_button_on_PDP_page() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.add_to_bag_button", 20);
        Assert.assertTrue(Elements.elementPresent("product_display.add_to_bag_button"));
    }

    @When("^I click on add to bag button$")
    public void iClickOnAddToBagButton() throws Throwable {

        Wait.secondsUntilElementPresent("product_display.add_to_bag_button", 20);
        Assert.assertTrue(Elements.elementPresent("product_display.add_to_bag_button"));
         Clicks.click("product_display.add_to_bag_button");
    }

    @When("^I redirect to Add To Bag modal page$")
    public void I_should_see_Add_To_Bag_modal() throws Throwable {
        Wait.secondsUntilElementPresent("add_to_bag.checkout", 20);
        Wait.forPageReady();
        Assert.assertTrue(Elements.elementPresent("add_to_bag.checkout"));
        Clicks.click("add_to_bag.checkout");
    }

    @And("^I click available size of a product$")
    public void iClickAvailableSize() throws Throwable {
        ProductDisplay.selectRandomSize();
    }

    @When("^I add two quantity of product items$")
    public void I_select_quantity_of_product_items() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.quantity", 20);
        ProductDisplay.selectQuantity("2");
    }

    @When("^I store expected product information$")
    public void storeExpectedProductInformation() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("product_display.product_name", 20);
        productName = Elements.findElement(Elements.element("product_display.product_name")).getText();
        if(Elements.elementPresent("product_display.product_color_name")) {
            productColor = Elements.findElement(Elements.element("product_display.product_color_name")).getText();
        }
        productBrand = Elements.findElement(Elements.element("product_display.product_brand")).getText();
        productPrice = Elements.findElement(Elements.element("product_display.product_sale_price")).getText();
        productPrice = productPrice.replace("$", "").trim().split("\n")[0];
        logger.info("product Name "+productName);
        logger.info("product color: "+productColor);
        logger.info("product Brand "+productBrand);
        logger.info("product price:"+productPrice);
        logger.info(" Successfully store product values of the product ");
    }

    @When("^I verify correct values on add to bag modal$")
    public void I_should_see_correct_values_on_add_to_bag_modal() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.qty", 20);
        String bagProductName = Elements.findElement(Elements.element("addToBag.brand")).getText().toUpperCase();
        String bagProductColor = Elements.findElement(Elements.element("addToBag.color")).getText();
        String price = Elements.findElement(Elements.element("addToBag.price")).getText();
        String bagproductPrice = price.replace("$", "").trim().split("\n")[0];
        Assert.assertEquals(productPrice.trim(),bagproductPrice.trim());
        Assert.assertTrue(bagProductName.toUpperCase().trim().contains(productName.toUpperCase().trim()));
        Assert.assertTrue(bagProductName.toUpperCase().trim().contains(productBrand.toUpperCase().trim()));
        Assert.assertTrue(bagProductColor.toUpperCase().trim().contains(productColor.toUpperCase().trim()));
        logger.info("verified the values on the bag of added product");
    }

    @When("^I click on the product image and zoom the image$")
    public void I_zoom_the_product_image_bcom() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.product_image", 20);
        Assert.assertTrue(Elements.elementPresent("product_display.product_image"));
        Clicks.click("product_display.product_image");
        Wait.secondsUntilElementPresent("product_display.zoomed_image", 20);
        Assert.assertTrue(Elements.elementPresent("product_display.zoomed_image"));
    }

    @When("^I verify see Product details is away from page$")
    public void I_verify_see_Product_details() throws Throwable {
        Assert.assertTrue(!Elements.elementInView("product_display.product_details_tab"));
        logger.info("Product details  button is away from page");
    }
}