package com.macys.sdt.projects.Selection.Regression.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
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

/**
 * Created by m630215 on 7/19/2017.
 */
public class AddToBagSteps {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());

    private String productQty = "";
    private String productName = "";
    private String productPrice = "";
    private String productColor = "";
    private String productSize = "";
    private String productBrand = "";
    private String webId = "";




    @When("^I should see Add to Bag button on PDP page$")
    public void I_should_see_Add_to_Bag_button_on_PDP_page() throws Throwable {
        //Navigate.visit("https://m.qa15codemacys.fds.com/shop/product/?ID=297826");

        Wait.secondsUntilElementPresent("addToBag.add_to_bag_button", 20);
        Assert.assertTrue(Elements.elementPresent("addToBag.add_to_bag_button"));
    }

    @When("^I select available color and size$")
    public void I_select_available_color_and_size() throws Throwable {
        WebDriverManager.getWebDriver().manage().deleteAllCookies();
        Wait.secondsUntilElementPresent("addToBag.pdp_available_sizes", 20);
        List<WebElement> availableSizesList = Elements.findElements(Elements.element("addToBag.pdp_available_sizes"));
        Clicks.click(availableSizesList.get(0));
    }


    @When("^I select quantity of product items$")
    public void I_select_quantity_of_product_items() throws Throwable {
        Wait.secondsUntilElementPresent("addToBag.pdp_qty_increment_button", 20);
        Clicks.click(Elements.element("addToBag.pdp_qty_increment_button"));
    }

    @When("^I store product information$")
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
        webId = Elements.findElement(Elements.element("addToBag.pdp_web_id")).getText().split("WEB ID:")[1].trim();
        productBrand = Elements.findElement(Elements.element("addToBag.pdp_product_brand")).getText();

        logger.info("1 QTY "+productQty);
        logger.info("2 "+productName);
        logger.info("3 "+productColor);
        logger.info("4 "+productSize);

        Thread.sleep(5000);

        try {
            Wait.forPageReady();
            productPrice = Elements.findElement(Elements.element("addToBag.pdp_product_price")).getText();
        } catch (Exception e) {
            logger.warning(String.format("------------------> can't find price"));
            e.printStackTrace();
        }

        productPrice = productPrice.replace("$", "").trim().split("\n")[0];
        logger.info("5 "+productPrice);
        logger.info("6 webId "+webId);
        logger.info("7 brand "+productBrand);

    }


    @When("^I tap on add to bag button$")
    public void I_tap_on_add_to_bag_button() throws Throwable {
        Wait.secondsUntilElementPresent("addToBag.add_to_bag_button", 20);
        Clicks.click(Elements.element("addToBag.add_to_bag_button"));
    }

    @When("^I should see Add To Bag modal$")
    public void I_should_see_Add_To_Bag_modal() throws Throwable {
        Wait.secondsUntilElementPresent("addToBag.bag_checkout_button", 20);
        Wait.forPageReady();
        Assert.assertTrue(Elements.elementPresent("addToBag.bag_checkout_button"));
    }


    @When("^I should see correct values on add to bag modal$")
    public void I_should_see_correct_values_on_add_to_bag_modal() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.pdp_product_qty", 20);

        String bagProductName = Elements.findElement(Elements.element("addToBag.bag_product_name")).getText();
        String bagProductColor = Elements.findElement(Elements.element("addToBag.bag_product_color")).getText();
        String bagProductSize = Elements.findElement(Elements.element("addToBag.bag_product_size")).getText();
        String bagProductQty = Elements.findElement(Elements.element("addToBag.bag_product_qty")).getText().split(" ")[0];
        Thread.sleep(3000);


        Assert.assertTrue(bagProductName.trim().contains(productName.trim()));
        Assert.assertEquals(productColor.trim(),bagProductColor.trim());
        Assert.assertEquals(productSize.trim(), bagProductSize.trim());
        Assert.assertEquals(productQty.trim(), bagProductQty.trim());
    }

    @When("^I should see subtotal price on modal$")
    public void I_should_see_subtotal_price_on_modal() throws Throwable {
        Wait.forPageReady();
        Clicks.click(Elements.element("addToBag.your_bag_link"));
        Wait.secondsUntilElementPresent("addToBag.bag_subtotal", 20);
        String bagProductSubtotalPrice = Elements.findElement(Elements.element("addToBag.bag_subtotal")).getText();
        bagProductSubtotalPrice = bagProductSubtotalPrice.split("\\$")[1].trim();

        Float prPrice = Float.parseFloat(productPrice);
        Float bag_prPrice = Float.parseFloat(bagProductSubtotalPrice);
        Assert.assertTrue((prPrice * 2 - bag_prPrice) == 0);

    }

    @When("^I tap \"([^\"]*)\" button on the Add to Bag modal$")
    public void I_tap_button_on_the_Add_to_Bag_modal(String action) throws Throwable {
        if(action == "Continue Shopping" )
            Wait.secondsUntilElementPresent("addToBag.bag_continue_shopping_button", 20);
        Clicks.click(Elements.element("addToBag.bag_continue_shopping_button"));
    }

    @When("^I should navigate back to PDP page$")
    public void I_should_navigate_back_to_PDP_page() throws Throwable {
        Wait.secondsUntilElementPresent("addToBag.add_to_bag_button", 20);
        Wait.forPageReady();
        Assert.assertTrue(Elements.elementPresent("addToBag.add_to_bag_button"));
    }

    @When("^I call pdp service and verify product info$")
    public void I_call_pdp_service() throws Throwable {

        JSONObject rootJson = getJsonPDPService(webId);
        //to drill down to name attribute
        String product_name = rootJson.getJSONObject("product").get("productDescription").toString();

        JSONObject fcc_rootJson = getJsonFCCProductDetailsService(webId);
        String fcc_name = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("name").toString();
        Assert.assertTrue(product_name.equals(fcc_name));
        Assert.assertTrue(fcc_name.contains(productName));
    }

    @When("^I visit mobile pdp page with \"([^\"]*)\" product$")
    public void I_visit_mobile_pdp_page_with_product(String prod_id) throws Throwable {
        String host = RunConfig.getEnvOrExParam("website");
        Navigate.visit(host + "shop/product/?ID=" + prod_id);
    }

    @When("^I verify pdp service for \"([^\"]*)\" rug product$")
    public void I_verify_pdp_service_for_product(String prod_id) throws Throwable {
        //call PDP service
        JSONObject rootJson = getJsonPDPService(prod_id);
        String pdp_product_name = rootJson.getJSONObject("product").get("productDescription").toString();
        String pdp_brand = rootJson.getJSONObject("product").get("brand").toString();
        String pdp_availability = rootJson.getJSONObject("product").get("available").toString();

        //call FCC service for verification
        JSONObject fcc_rootJson = getJsonFCCProductDetailsService(prod_id);
        String fcc_name = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("name").toString();
        String fcc_brand = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONObject("brand").get("brandName").toString();
        String fcc_availability = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("available").toString();

        Assert.assertTrue(pdp_product_name.endsWith(productName.split("CLOSEOUT!")[1].trim()));
        Assert.assertTrue(pdp_brand.contains(productBrand));

        Assert.assertTrue(fcc_name.contains(productName.split("CLOSEOUT!")[1].trim()));
        Assert.assertTrue(fcc_brand.contains(productBrand));
        Assert.assertTrue(pdp_availability.equals(fcc_availability));

    }

    @When("^select master product using mobile website$")
     public void select_master_product_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        String masterProductID = masterProduct();
        Elements.findElement(By.cssSelector("li[data-product_id='"+masterProductID+"'] div.mb-j-browse-image")).click();
    }

    @When("^I select available color and size for that member$")
    public void I_select_available_color_and_size_for_that_member() throws Throwable {
        Wait.forPageReady();

        //Wait.secondsUntilElementPresent("addToBag.member_product_size_selection", 20);
        //Clicks.click(Elements.element("addToBag.member_product_size_selection"));
        System.out.println("###############");
        System.out.println(Elements.elementPresent("addToBag.test"));

        List<WebElement> availableSizesList = Elements.findElements(Elements.element("addToBag.test"));
        System.out.println(availableSizesList);
        Clicks.click(availableSizesList.get(0));

    }

    @When("^I select quantity of product items for that member$")
    public void I_select_quantity_of_product_items_for_that_member() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.member_product_qty_select", 20);
        Clicks.click(Elements.element("addToBag.member_product_qty_select"));
        Thread.sleep(300000);
    }

    //use for mew search page;
    // collect product ids from search page and call fcc product detail service to determine if master;
    // will return first found master product;
    public static String masterProduct() {
        Wait.forPageReady();
        List<WebElement> searchProductList = Elements.findElements(Elements.element("addToBag.search_result_list"));
        String masterProduct="";
        int i = 0;
        while (i < searchProductList.size()) {

            try {
                JSONObject fcc_rootJson = getJsonFCCProductDetailsService(searchProductList.get(i).getAttribute("data-product_id"));
                fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("memberProductIds");
                masterProduct = searchProductList.get(i).getAttribute("data-product_id");
                break;
            } catch (Exception e) {
                logger.warning(String.format("------------------> member product"));
                e.printStackTrace();
            }

            i++;
        }

        return masterProduct;
    }


    public static JSONObject getJsonPDPService(String prodID) throws IOException {
        //need to remove hardcoded environment
        //PDP service call and store product description

        String url = "http://qa16-pdp-servicecodemacys.herokuapp.com/pdp/";
        int status = RESTOperations.doGET(url+prodID, null).getStatus();
        Assert.assertTrue(status==200);
        Response response = RESTOperations.doGET(url+prodID, null);
        String finalJsonStr = response.readEntity(String.class);
        return new JSONObject(finalJsonStr);

    }

    public static JSONObject getJsonFCCProductDetailsService(String prodID) throws IOException {
        String envIP = "http://" + EnvironmentDetails.otherApp("FCC").ipAddress;

        //FCC call
        //delete fcc_url with hardcoded env and uncomment line above with dynamic env_url
        String fcc_url = envIP +":8080/api/catalog/v3/products/";
        //String fcc_url = "http://fcc-dpx-17x.tbe.zeus.fds.com:8080/api/catalog/v3/products/";
        Response fcc_response = RESTOperations.doGET(fcc_url + prodID, null);
        String fcc_finalJsonStr = fcc_response.readEntity(String.class);
        return new JSONObject(fcc_finalJsonStr);
    }



}//end class AddToBagSteps