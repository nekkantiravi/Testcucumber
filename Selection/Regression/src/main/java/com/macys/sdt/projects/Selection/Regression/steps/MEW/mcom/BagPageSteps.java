package com.macys.sdt.projects.Selection.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class BagPageSteps {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    private String productQty = "";
    private String productName = "";
    private String productPrice = "";
    private String productColor = "";
    private String productSize = "";
    private String productBrand = "";

    @When("^I store product details information$")
    public void I_store_product_information() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.pdp_product_name", 20);

        productName = Elements.findElement(Elements.element("addToBag.pdp_product_name")).getText();
        if(Elements.elementPresent("addToBag.pdp_product_color")) {
            productColor = Elements.findElement(Elements.element("addToBag.pdp_product_color")).getText();
        }
        if(Elements.elementPresent("addToBag.pdp_product_size")) {
            productSize = Elements.findElement(Elements.element("addToBag.pdp_product_size")).getText();
        }
        productQty = Elements.findElement(Elements.element("addToBag.pdp_product_qty")).getText();
        productBrand = Elements.findElement(Elements.element("addToBag.pdp_product_brand")).getText();
        productPrice = Elements.findElement(Elements.element("addToBag.pdp_product_price")).getText().replace("$", "").trim().split("\n")[0];
        logger.info("productQty "+productQty);
        logger.info("productName "+productName);
        logger.info("product Color "+productColor);
        logger.info("product Size "+productSize);
        logger.info("product Price "+productPrice);
        logger.info("product Brand "+productBrand);
        logger.info("Successfully store product details information");
    }

    @When("^I verify pdp product values on add to bag modal$")
    public void I_verify_pdp_product_values_on_add_to_bag_modal() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.pdp_product_qty", 20);
        String bagProductName = Elements.findElement(Elements.element("addToBag.bag_product_name")).getText();
        String bagProductColor = Elements.findElement(Elements.element("addToBag.bag_product_color")).getText();
        String bagProductSize = Elements.findElement(Elements.element("addToBag.bag_product_size")).getText();
        String bagProductQty = Elements.findElement(Elements.element(
                "addToBag.bag_product_qty")).getText().split(" ")[0];
        Assert.assertTrue(bagProductName.trim().contains(productName.trim()));
        Assert.assertEquals(productColor.trim(),bagProductColor.trim());
        Assert.assertEquals(productSize.trim(), bagProductSize.trim());
        Assert.assertEquals(productQty.trim(), bagProductQty.trim());
        logger.info("Successfully verify the product detail on bag page");
    }

    @When("^I click on image of product and zoom in$")
    public void I_zoom_the_product_image() throws Throwable {
        Wait.secondsUntilElementPresent("addToBag.product_imge", 20);
        Assert.assertTrue(Elements.elementPresent("addToBag.product_imge"));
        Clicks.click("addToBag.product_imge");
        logger.info("Successfully zoom in the image");
    }

    @When("^I click on zoom out button$")
    public void I_click_on_zoom_out_button() throws Throwable {
        Assert.assertTrue(Elements.elementPresent("addToBag.zoom_out_img"));
        Clicks.click("addToBag.zoom_out_img");
        logger.info("Successfully zoom out the image");
    }
}