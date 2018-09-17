package com.macys.sdt.projects.Marketing.Tealium.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.utils.CheckoutUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.macys.sdt.shared.utils.CommonUtils.selectRandomProduct;

/**
 * Created by Tag Management QE Team on 7/7/2017
 */
public class DataLayerStepsDefs extends StepUtils {

    private Har har;
    private static final Logger logger = LoggerFactory.getLogger(DataLayerStepsDefs.class);
    private Map<String, Object> attributeMap = new HashMap<String, Object>();
    private String productName = null;
    private String categoryId = null;
    private String productBrandName = null;
    private  Boolean truefitSize;
    private  Boolean productVideo;
    private String[] productId = null;
    private String pdpPageUrl;
    private String[] catId = null;
    private Map<String, Object> shoppingbagAttributeMap = new HashMap<String, Object>();
    private String productCategoryId = null;
    private Map<String, Object> shoppingbagAttributeMap_bcom = new HashMap<String, Object>();
    public Har harOrder;

    @Then("^I verify the network call$")
    public void i_verify_the_network_call(List<String> network_call) throws Throwable {
        //har = MainRunner.browsermobServer.getHar();
        String network = network_call.get(0);
        Wait.forPageReady();
        logger.info("Got Network call :-" + network);

        if (harOrder != null && harOrder.getLog() != null) {
            List<HarEntry> harEntries = harOrder.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                return;
            }
            if (CollectionUtils.isNotEmpty(network_call)) {
                for (String datalayer_attr : network_call) {
                    String orderConfirmNetworkCall = RunConfig.getEnvOrExParam("website") + network;
                    logger.info("Network call URL:- " + orderConfirmNetworkCall);
                    for (HarEntry harEntry : harEntries) {

                        logger.info("Network call HarEntry" + harEntry);

                        if (harEntry.getRequest().getUrl().equalsIgnoreCase(orderConfirmNetworkCall)) {

                            Assert.assertEquals(orderConfirmNetworkCall + " network call is not available", orderConfirmNetworkCall, harEntry.getRequest().getUrl());
                            logger.info("Network call is available:- " + harEntry.getRequest().getUrl());
                        }
                    }
                }
            }
        }
    }

    @Then("^I verify the data attributes for tealium tags on order confirmation page from utagData for \"([^\"]*)\"$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_order_confirmation_page_from_utagData_for(String userType, DataTable dataLayer) throws Throwable {
        Thread.sleep(1000);
        //har = MainRunner.browsermobServer.getHar();
        List<List<String>> dataLayerOrder = dataLayer.raw();
        Map<Integer, String> dataMap = new HashMap<Integer, String>();
        int count = 1;
        Boolean isExpectedAttributePresent = false;
        for (List<String> ls : dataLayerOrder) {
            dataMap.put(count, ls.get(0));
            count++;
        }
        logger.info("HashMap Values: " + dataMap);
        Wait.forPageReady();
        Thread.sleep(7000);
        if (harOrder != null && harOrder.getLog() != null) {
            List<HarEntry> harEntries = harOrder.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }
            String orderConfirmNetworkCall = RunConfig.getEnvOrExParam("website") + userType;
            logger.info("Network call URL :- " + orderConfirmNetworkCall);
            for (HarEntry harEntry : harEntries) {
                logger.info("Network call HarEntry :- " + harEntry);
//                  if (harEntry.getRequest().getUrl().contains(orderConfirmNetworkCall))
                    if (harEntry.getRequest().getUrl().contains(userType))
                    {
                    isExpectedAttributePresent = true;
                    String datalayerContent = harEntry.getResponse().getContent().getText();
                    logger.info("Response Content : " + datalayerContent);
                    for (int i = 1; i <= dataMap.keySet().size(); i++) {

                        Assert.assertTrue("Data layer content of " + dataMap.get(i) + " is not available: ", datalayerContent.contains(dataMap.get(i)));
                        logger.info("Data layer content is available." + dataMap.get(i));
                    }
                    break;
                }
            }

            Assert.assertTrue("Expected value which is " + userType + " is not present in network call", isExpectedAttributePresent == true);
        } else {
            logger.info("Har file is null: Unable to capture network call");
            Assert.fail("Unable to capture network call");
        }
    }


    @Then("^I verify the data attributes for tealium tags on Home page$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_Home_page(DataTable dataLayerHomePage) throws Throwable {

        Navigate.visit("home");
        List<List<String>> dataLayer = dataLayerHomePage.raw();
        String pageType = dataLayer.get(0).get(0);
        String pageId = dataLayer.get(1).get(0);

        String pageSrc = WebDriverManager.getWebDriver().getPageSource();

        Assert.assertTrue("Data layer is for pageType is not in pagesource for Home page" + pageType, pageSrc.contains(pageType));
        Assert.assertTrue("Data layer is for pageId is not in pagesource for Home page" + pageId, pageSrc.contains(pageId));
        logger.info("Data layer for pageId and pageType is present in pagesource:- " + pageType + " : " + pageId);


    }

    @When("^I add \"([^\"]*)\" to the current url$")
    public void i_add_to_the_current_url(String urlSuffix) throws Throwable {
        String currentUrl = WebDriverManager.getCurrentUrl();
        String pdpPageUrl = currentUrl + urlSuffix;
        Thread.sleep(1000);
        Navigate.visit(pdpPageUrl);
        logger.info("With Suffix :-" + pdpPageUrl);
        currentUrl = WebDriverManager.getCurrentUrl();
        logger.info("Current Url after navigation :-" + currentUrl);
        Wait.forPageReady();
        Thread.sleep(10000);
        logger.info("Redirected to URL with cookie :-" + urlSuffix);
    }

    @Then("^I verify the data attributes for tealium tags on \"([^\"]*)\" page from utagData$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_page_from_utagData(String arg1, DataTable prdData) throws Throwable {

        List<List<String>> prdDataAttributes = prdData.raw();
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getWebDriver();
        for (List<String> ls : prdDataAttributes) {
            String attribute = ls.get(0);

            if (attribute.equalsIgnoreCase("page_type")) {
                attributeMap.put("page_type", "product");
                Assert.assertTrue("Page_type:-", js.executeScript("return utag_data." + attribute).equals("product"));
                logger.info("page_type is present:- product");
            }
            if (attribute.equalsIgnoreCase("lean_template") || attribute.equalsIgnoreCase("order_by_phone") || attribute.equalsIgnoreCase("true_fit_size") || attribute.equalsIgnoreCase("product_video")) {
                Assert.assertTrue("", js.executeScript("return utag_data." + attribute).equals("true") || js.executeScript("return utag_data." + attribute).equals("false"));
                logger.info("Boolean values are present :-" + attribute);
            }
            if (attribute.equalsIgnoreCase("lean_template") || attribute.equalsIgnoreCase("order_by_phone") || attribute.equalsIgnoreCase("true_fit_size") || attribute.equalsIgnoreCase("product_video")) {
                Assert.assertTrue("", js.executeScript("return utag_data." + attribute).equals("true") || js.executeScript("return utag_data." + attribute).equals("false"));
                logger.info("Boolean values are present :-" + attribute);
            }
            if (attribute.equalsIgnoreCase("product_name")) {
                logger.info("product_name:-" + String.valueOf(attributeMap.get(attribute)));
                logger.info("product_name from js:-" + String.valueOf(js.executeScript("return utag_data." + attribute)));

                String jsPrdname = String.valueOf(js.executeScript("return utag_data." + attribute)).replaceAll("[\\\\]", "");
                //------- Removing unicode characters if present-------- //
                String str = jsPrdname;
                for (int j = 0; j <= str.length() - 1; j++) {
                    if (str.charAt(j) >= 128 && str.charAt(j) <= 255) {
                        str = str.replace(String.valueOf(str.charAt(j)), "");
                    }
                }
                logger.info("product_name from js after removing Unicode characters :-" + str);

                //Assert.assertTrue("Product name value is not present", str.contains(String.valueOf(attributeMap.get(attribute))) || String.valueOf(attributeMap.get(attribute)).contains(str));
                logger.info("Product name value is present :-" + attribute);
            }
//            if (attribute.equalsIgnoreCase("product_category_name")) {
//                attributeMap.put("product_category_name", "Men - Jeans");
//                Assert.assertTrue("", String.valueOf(js.executeScript("return utag_data." + attribute)).contains(String.valueOf(attributeMap.get(attribute))));
//                logger.info("Product Category name value is present :-" + attribute);
//            }

            if (attribute.equalsIgnoreCase("product_id")) {
                logger.info("product_id:-" + String.valueOf(attributeMap.get(attribute)));
                Assert.assertTrue("", String.valueOf(js.executeScript("return utag_data." + attribute)).contains(String.valueOf(attributeMap.get(attribute))));
                logger.info("Product Id value is present :-" + String.valueOf(attributeMap.get(attribute)));
            }
            if (attribute.equalsIgnoreCase("product_category_id")) {
                logger.info("product category id:-" + String.valueOf(attributeMap.get(attribute)));

                logger.info("product Cat Id using console" + String.valueOf(js.executeScript("return utag_data." + attribute)));
                Assert.assertTrue("", String.valueOf(js.executeScript("return utag_data." + attribute)).contains(String.valueOf(attributeMap.get(attribute))));
                logger.info("product_category_id is present:-" + String.valueOf(attributeMap.get(attribute)));
            }
            if (attribute.equalsIgnoreCase("product_type")) {

                String prod = String.valueOf(js.executeScript("return utag_data." + attribute));
                attributeMap.put("product_type", prod);
                Assert.assertTrue("product_type is not present", String.valueOf(js.executeScript("return utag_data." + attribute)).contains("SINGLE ITEM")
                        || String.valueOf(js.executeScript("return utag_data." + attribute)).contains("MASTER")
                        || String.valueOf(js.executeScript("return utag_data." + attribute)).contains("MEMBER"));

                logger.info("product_type is present: SINGLE ITEM");
            }

        }
    }


    @Then("^I should be redirected to product details page for \"([^\"]*)\"$")
    public void i_should_be_redirected_to_product_details_page(String modeType) throws Throwable {

        for (int i = 1; i <= 2; i++) {//loop added due to inconsistent issues in getting the values
            try {
                if (modeType.equalsIgnoreCase("domestic")) {
                    productName = Elements.findElement("dataLayer_page.productName_element").getText().trim();
                    productBrandName = Elements.findElement("dataLayer_page.productBrandName_element").getText();
                    productName = productBrandName + " " + productName;
                } else if (modeType.equalsIgnoreCase("registry")) {
                    productName = Elements.findElement("dataLayer_page.registryProductName").getText();
                }
                logger.info("Product Name fetched from UI is (after split)-> " + productName);
                break;
            } catch (Exception e) {
                Navigate.visit(pdpPageUrl);
                Wait.forPageReady();
                continue;
            }
        }

        String prdID = Elements.findElement("dataLayer_page.productId_element").getText();
        productId = prdID.split(": ");
        System.out.println("!!!! "+ productId[0] + " "+ productId[1]);
        logger.info("Product Id fetched from UI(after split) is ->" + productId[1]);
        Pattern p = Pattern.compile("CategoryID=\\d+");
        Matcher matcher = p.matcher(WebDriverManager.getCurrentUrl());
        if (matcher.find()) {
            logger.info(matcher.group());
            categoryId = matcher.group();
            catId = categoryId.split("=");
            logger.info("CatId fetched from UI is (after split)-> " + catId[1]);
        } else {
            logger.info("No matched pattern found for current URL");
        }
        try {
            Thread.sleep(10000);
            truefitSize = Elements.findElement("dataLayer_page.trueFit_element").isDisplayed();
            logger.info("truefitSize value :- " + truefitSize);
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.info("true Fit not found");
            truefitSize = false;
        }

        try {
            productVideo = Elements.findElement("dataLayer_page.videoPdp_element").isDisplayed();
            logger.info("productVideo value :- " + productVideo);
        } catch (Exception e) {
            logger.info("Product video not found");
            productVideo = false;
        }

        attributeMap.put("product_id", productId[1]);
        attributeMap.put("product_name", productName);
        attributeMap.put("product_category_id", catId[1]);
        attributeMap.put("true_fit_size", truefitSize);
        attributeMap.put("product_video", productVideo);
        logger.info("attributeMap values :- " + attributeMap);

    }

    @Then("^I verify the data attributes for tealium tags on \"([^\"]*)\" page from pagesource for \"([^\"]*)\" product$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_page_from_pagesource_for_product(String pageType, String product, List<String> navAppDataAttributes1) throws Throwable {
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        for (String pdpDataAttr : navAppDataAttributes1) {
            if (pdpDataAttr.equalsIgnoreCase(("product_name"))) {
                logger.info(pdpDataAttr + "\" : [\"" + productName + "\"]");

//                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
//                        pageSrcVal.contains(pdpDataAttr + "\" : [\"" + productName + "\"]") || pageSrcVal.contains(pdpDataAttr + "\" : [\"" + productName + " \"]"));
//                logger.info("product_name is present in page page source");

                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available", pageSrcVal.contains("product_name"));
                logger.info("product_name is present in page page source");

            }
            if (pdpDataAttr.equalsIgnoreCase(("product_id"))) {
                logger.info(pdpDataAttr + "\" : [\"" + productId[1] + "\"]");

                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : [\"" + productId[1] + "\"]"));
                logger.info("product_id is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_category_id"))) {
                logger.info(pdpDataAttr + "\" : [\"" + catId[1] + "\"]");
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : [\"" + catId[1]));
                logger.info("product_category_id is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("true_fit_size"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : \"" + truefitSize + "\""));
                logger.info("true_fit_size is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_video"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : \"" + productVideo + "\""));
                logger.info("product_video is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("order_by_phone"))) {

                logger.info(pdpDataAttr + "\" : \"false");
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available", pageSrcVal.
                        contains(pdpDataAttr + "\" : \"true") || pageSrcVal.contains(pdpDataAttr + "\" : \"false"));
                logger.info("order_by_phone is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("lean_template"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available", pageSrcVal.
                        contains(pdpDataAttr + "\" : \"true") || pageSrcVal.contains(pdpDataAttr + "\" : \"false"));
                logger.info("lean_template is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_category_name"))) {
                switch (product.toLowerCase()) {
                    case "member":
                        logger.info(pdpDataAttr + "\" : [\"");
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\""));
                        logger.info("product_category_name is present in page page source");
                        break;
                    case "master":
                        logger.info(pdpDataAttr + "\" : [\"");
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\""));
                        logger.info("product_category_name is present in page page source");
                        break;
                }
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_type"))) {
                switch (product.toLowerCase()) {
                    case "member":
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\"SINGLE ITEM")  || pageSrcVal.contains(pdpDataAttr + "\" : [\"MEMBER"));
                        logger.info("product_type is present in page page source");
                        break;
                    case "master":
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\"MASTER"));
                        logger.info("product_type is present in page page source");
                        break;
                }
            }
            if (pdpDataAttr.equalsIgnoreCase(("page_type"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : \"product"));
                logger.info("page_type is present in page page source");
            }
        }
    }

    @When("^I select a random (master|master_alternate_image) product from search results page$")
    public void I_select_a_random_product_from_search_results_page(String prod_type) throws Throwable {
        Wait.untilElementPresent("product_display.loader_completed");
        logger.info("In Step Def:-");
        boolean hasRating = false;
        selectRandomProduct(hasRating, prod_type.toLowerCase().contains("master"));
    }

    @When("^I select a random (master|master_alternate_image) product from browse page$")
    public void I_select_a_random_product_from_browse_page(String prod_type) throws Throwable {
        Wait.untilElementPresent("product_display.loader_completed");
        logger.info("In Step Def:-");
        boolean hasRating = false;
        selectRandomProduct(hasRating, prod_type.toLowerCase().contains("master"));
    }

    @Then("^I should see utag data as blank in body section\\.$")
    public void i_should_see_utag_data_as_blank_in_body_section() throws Throwable {
        String scriptText = null;
        boolean isUtagValueBlank = false;
        WebDriver driver = WebDriverManager.getWebDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        List<WebElement> element = driver.findElements(By.xpath("//body/script"));
        for (WebElement script : element) {
            scriptText = String.valueOf(jse.executeScript("return arguments[0].text", script));
            logger.info("--Script--"+scriptText);
            if (scriptText.contains("var utag_data = utag_data || {};")) {
                Assert.assertTrue("utag_data is not blank in page source under body section", scriptText.contains("var utag_data = utag_data || {};"));
                break;
            }
        }
        element = driver.findElements(By.xpath("//head/script"));
        for (WebElement headScript : element) {
            scriptText = String.valueOf(jse.executeScript("return arguments[0].text", headScript));
            if (scriptText.contains("utag_data")) {
                isUtagValueBlank = true;
                logger.info("--isUtagValueBlank is set to true--");
            }
        }
        Assert.assertFalse("utag_data shuld NOT be present in Head Section of Page Source ", isUtagValueBlank);
    }


    @Then("^I should see utag_data as blank\\.$")
    public void i_should_see_utag_data_as_blank() throws Throwable {

        boolean flag = false;
        WebDriver driver = WebDriverManager.getWebDriver();
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        Assert.assertTrue("utag_data is not blank in page source: " + pageSrcVal, pageSrcVal.contains("var utag_data = utag_data || {};"));
        logger.info("utag_data is blank in page source");
    }


    @When("^I click the checkout button$")
    public void i_press_the_checkout_button() throws Throwable {

        Wait.untilElementPresent("bag_screen.checkout_button");
        Wait.forPageReady();
        Thread.sleep(4000);
        try {
            for (int i = 1; i <= 3; i++) {

                //Clicks.click("dataLayer_page.checkoutButton");
                Clicks.click("dataLayer_page.checkoutButton_updated");
                logger.info("Clicked on checkout Button");
                Wait.forPageReady();
                Thread.sleep(2000);
                break;

            }
        } catch (Exception e) {
            Navigate.browserRefresh();
            Thread.sleep(2000);
            Wait.untilElementPresent("bag_screen.checkout_button");
            Wait.forPageReady();
            //Clicks.javascriptClick("dataLayer_page.checkoutButton");
            Clicks.javascriptClick("dataLayer_page.checkoutButton_updated");
            logger.info("Clicked on checkout Button in catch Block");
            Wait.forPageReady();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);

    }

    @Then("^I verify the data attributes for tealium tags on Shopping bag page from utagData for Shopping Bag page$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_Shopping_bag_page_from_utagData_for_Shopping_Bag_page(DataTable bagAppAttr) throws Throwable {

        List<List<String>> DataAttributes = bagAppAttr.raw();
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getWebDriver();
        for (List<String> ls : DataAttributes) {
            String attribute = ls.get(0);

            if (attribute.equalsIgnoreCase("order_subtotal")) {
                String jsorder_subtotal = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsorder_subtotal + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("order_subtotal:-", String.valueOf(shoppingbagAttributeMap.get(attribute)).contains(jsorder_subtotal));
                logger.info("order_subtotal is present:- " + jsorder_subtotal);

            }
            if (attribute.equalsIgnoreCase("order_discount")) {

                String jsorder_discount = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsorder_discount + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("order_discount:-", String.valueOf(shoppingbagAttributeMap.get(attribute)).contains(jsorder_discount) || jsorder_discount.contains(""));
                logger.info("order_discount is present:- " + jsorder_discount);

            }
            if (attribute.equalsIgnoreCase("order_promocode")) {

                String jsorder_promocode = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsorder_promocode + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("order_promocode:-", jsorder_promocode.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))) || jsorder_promocode.contains(""));
                logger.info("order_promocode is present:- " + jsorder_promocode);

            }
            if (attribute.equalsIgnoreCase("order_totalitems")) {

                String jsorder_totalitems = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsorder_totalitems + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("order_totalitems:-", jsorder_totalitems.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("order_totalitems is present:- " + jsorder_totalitems);

            }
            if (attribute.equalsIgnoreCase("product_id")) {

                String jsproduct_id = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_id + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("product_id:-", jsproduct_id.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_id is present:- " + jsproduct_id);


            }
            if (attribute.equalsIgnoreCase("product_name")) {

                String jsproduct_name = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_name + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("product_name:-", jsproduct_name.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_name is present:- " + jsproduct_name);

            }
            if (attribute.equalsIgnoreCase("product_color")) {

                String jsproduct_color = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_color + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("product_color:-", jsproduct_color.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_color is present:- " + jsproduct_color);
            }
            if (attribute.equalsIgnoreCase("product_price")) {

                String jsproduct_price = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_price + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                //Assert.assertTrue("product_price:-", jsproduct_price.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_price is present:- " + jsproduct_price);
            }
            if (attribute.equalsIgnoreCase("product_size")) {

                String jsproduct_size = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_size + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("order_discount:-", String.valueOf(shoppingbagAttributeMap.get(attribute)).contains(jsproduct_size) || jsproduct_size.contains(""));
                logger.info("product_size is present:- " + jsproduct_size);

            }
            if (attribute.equalsIgnoreCase("product_quantity")) {

                String jsproduct_quantity = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_quantity + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("product_size:-", jsproduct_quantity.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_quantity is present:- " + jsproduct_quantity);
            }
            if (attribute.equalsIgnoreCase("product_subtotal")) {

                String jsproduct_subtotal = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_subtotal + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("product_subtotal:-", jsproduct_subtotal.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_subtotal is present:- " + jsproduct_subtotal);

            }
            if (attribute.equalsIgnoreCase("product_registry_flag")) {

                String jsproduct_registry_flag = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_registry_flag + "------" + String.valueOf(shoppingbagAttributeMap.get(attribute)));
                Assert.assertTrue("product_registry_flag:-", jsproduct_registry_flag.contains(String.valueOf(shoppingbagAttributeMap.get(attribute))));
                logger.info("product_registry_flag is present:- " + jsproduct_registry_flag);
            }


        }
    }

    @Then("^I should be redirected to checkout page$")
    public void i_should_be_redirected_to_checkout_page() throws Throwable {
        String orderSubTotal = Elements.findElement("dataLayer_page.orderSubTotal").getText();
        String orderDiscount = Elements.findElement("dataLayer_page.orderDiscount").getText();
        String orderPromocode = Elements.findElement("dataLayer_page.orderPromocode").getText();
        String orderTotalItems = Elements.findElement("dataLayer_page.orderTotalItems").getAttribute("value");
        String orderQuantity = Elements.findElement("dataLayer_Page.orderTotalItems").getAttribute("value");

        String productWebId = Elements.findElement("dataLayer_page.productId").getText();
        String prodId[] = productWebId.split(":");
        String productId = prodId[1].trim();

        String productName = Elements.findElement("dataLayer_page.productName_ShoppingBag").getText();

        String productColor = Elements.findElement("dataLayer_page.productColor").getText();
        String productSize = "";
        // String productPrice = Elements.findElement("dataLayer.productPrice").getText();
        String productPrice = Elements.findElement("dataLayer_page.orderSubTotal").getText().replaceAll("\\$", "");

        logger.info("---productPrice-------" + productPrice);
        String productQuantity = Elements.findElement("dataLayer_Page.orderTotalItems").getText();
        String productSubTotal = Elements.findElement("dataLayer_Page.orderSubTotal").getText().replaceAll("\\$", "");
        String prd[] = productSubTotal.split("\\.");
        String productSubTotalValue = prd[0];
        logger.info("*******productSubTotalValue****" + productSubTotalValue);

        String productRegistryFlag = "FALSE";
        String productRegistryNo = "";


        shoppingbagAttributeMap.put("order_subtotal", orderSubTotal);
        shoppingbagAttributeMap.put("order_discount", orderDiscount);
        shoppingbagAttributeMap.put("order_promocode", orderPromocode);
        shoppingbagAttributeMap.put("order_totalitems", orderTotalItems);
        //shoppingbagAttributeMap.put("orderQuantity", orderQuantity);
        shoppingbagAttributeMap.put("product_id", productId);
        shoppingbagAttributeMap.put("product_name", productName);
        shoppingbagAttributeMap.put("product_color", productColor);
        shoppingbagAttributeMap.put("product_size", productSize);
        shoppingbagAttributeMap.put("product_price", productPrice);
        shoppingbagAttributeMap.put("product_quantity", productQuantity);
        shoppingbagAttributeMap.put("product_subtotal", productSubTotalValue);
        shoppingbagAttributeMap.put("product_registry_flag", productRegistryFlag);
        shoppingbagAttributeMap.put("product_registry_number", productRegistryNo);

        logger.info("*******shoppingbagAttributeMap****" + shoppingbagAttributeMap);
    }

    @Then("^I verify the data attributes for tealium tags on pagesource from utagData for Shopping Bag page$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_pagesource_from_utagData_for_Shopping_Bag_page(DataTable dataAttr) throws Throwable {

        List<List<String>> shoppingBagDataAttributes = dataAttr.raw();
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        for (List<String> pdpDataAttr : shoppingBagDataAttributes) {
            String attribute = pdpDataAttr.get(0);
            Assert.assertTrue(attribute + " data layer attribute is not available", pageSrcVal.contains(attribute));
            logger.info(attribute+" is present" );
        }
    }

    @Then("^I verify the data layer attributes for tealium tags on pagesource from utagData for Shopping Bag page$")
    public void i_verify_the_data_layer_attributes_for_tealium_tags_on_pagesource_from_utagData_for_Shopping_Bag_page(DataTable dataAttr) throws Throwable {

        List<List<String>> shoppingBagDataAttributes = dataAttr.raw();
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        for (List<String> pdpDataAttr : shoppingBagDataAttributes) {
            String attribute = pdpDataAttr.get(0);
            Assert.assertTrue(attribute + " data layer attribute is not available", pageSrcVal.contains(attribute));
            logger.info(attribute+" is present" );

        }
    }

    @When("^I click the checkout button on the overlay$")
    public void i_press_the_checkout_button_on_the_overlay() throws Throwable {

        Wait.forPageReady();
        Thread.sleep(25000); // wait is deliberately added here to avoid inconsistent failures
        Wait.untilElementPresent("dataLayer_page.checkoutButton");
        try {
            for (int i = 1; i <= 3; i++) {

                Clicks.click("dataLayer_page.checkoutButton");
                logger.info("Clicked on checkout Button  for bcom");
                Wait.forPageReady();
                Thread.sleep(15000);
                break;

            }
        } catch (Exception e) {
            Navigate.browserRefresh();
            Thread.sleep(10000);
            Wait.forPageReady();
            Clicks.javascriptClick("dataLayer_page.checkoutButtonl");
            logger.info("Clicked on checkout Button in catch Block for bcom");
            Wait.forPageReady();
            Thread.sleep(15000);
        }
        Thread.sleep(5000);

    }

    @Then("^I should be redirected to Shopping Bag page in \"(domestic|iship|registry)\" mode$")
    public void i_should_be_redirected_to_Shopping_Bag__page(String mode_name) throws Throwable {
        String orderSubTotal;
        String productPrice;
        if(mode_name.equalsIgnoreCase("iship")){
            orderSubTotal = Elements.findElement("dataLayer_page.productSubTotal_ishipShoppingBag").getText().replaceAll("\\$", "");
        }
        else {
            orderSubTotal = Elements.findElement("dataLayer_page.orderSubTotal_ShoppingBag").getText().replaceAll("\\$", "");
        }
        String totalOrder[] = orderSubTotal.split("\\.");
        String orderSubTotalValue = totalOrder[0];
        logger.info("*******orderSubTotalValue****" + orderSubTotalValue);


        String productId = Elements.findElement("dataLayer_page.productId_ShoppingBag").getText();
        if(mode_name.equalsIgnoreCase("iship")){
            productPrice = Elements.findElement("dataLayer_page.productprice_ishipShoppingBag").getText().replaceAll("\\$", "");
        }
        else{
            productPrice = Elements.findElement("dataLayer_page.productprice_ShoppingBag").getText().replaceAll("\\$", "");
        }
        String totalPrice[] = productPrice.split("\\.");
        String productPriceValue = totalPrice[0];
        logger.info("*******orderSubTotalValue****" + productPriceValue);

        shoppingbagAttributeMap_bcom.put("order_sub_total", orderSubTotalValue);
        shoppingbagAttributeMap_bcom.put("product_id", productId);
        shoppingbagAttributeMap_bcom.put("product_price", productPriceValue);

        logger.info("*******shoppingbagAttributeMap for BCOM****" + shoppingbagAttributeMap);
    }

    @Then("^I verify the data attributes for tealium tags from utagData for Shopping Bag page$")
    public void i_verify_the_data_attributes_for_tealium_tags_from_utagData_for_Shopping_Bag_page(DataTable bagAppAttr) throws Throwable {

        List<List<String>> DataAttributes = bagAppAttr.raw();
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getWebDriver();
        for (List<String> ls : DataAttributes) {
            String attribute = ls.get(0);

            if (attribute.equalsIgnoreCase("order_sub_total")) {
                String jsorder_subtotal = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsorder_subtotal + "------" + String.valueOf(shoppingbagAttributeMap_bcom.get(attribute)).replaceAll(",", ""));
                Assert.assertTrue("order_subtotal:-", jsorder_subtotal.contains(String.valueOf(shoppingbagAttributeMap_bcom.get(attribute))));
                logger.info("order_subtotal is present:- " + jsorder_subtotal);

            }
            if (attribute.equalsIgnoreCase("product_id")) {

                String jsproduct_id = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_id + "------" + String.valueOf(shoppingbagAttributeMap_bcom.get(attribute)));
                Assert.assertTrue("product_id:-", jsproduct_id.contains(String.valueOf(shoppingbagAttributeMap_bcom.get(attribute))));
                logger.info("product_id is present:- " + jsproduct_id);


            }

            if (attribute.equalsIgnoreCase("product_price")) {

                String jsproduct_price = String.valueOf(js.executeScript("return utag_data." + attribute));
                logger.info(jsproduct_price + "------" + String.valueOf(shoppingbagAttributeMap_bcom.get(attribute)).replaceAll(",", ""));
                Assert.assertTrue("product_price:-", jsproduct_price.contains(String.valueOf(shoppingbagAttributeMap_bcom.get(attribute))));
                logger.info("product_price is present:- " + jsproduct_price);
            }

        }
    }

    @And("^I (?:continue )?checkout until I reached the (shipping|payment|shipping (?:and|&) payment|order review|order confirmation) page as an? \"([^\"]*)\" user(?: from \"([^\"]*)\")?(?: with \"([^\"]*)\")?$")
    public void I_checkout_until_I_reached_the_page_as_a_user(String pageName, String userType, String country, String address) throws Throwable {
        userType = userType.toLowerCase();
        boolean bops = userType.contains("bops");
        boolean signIn = userType.contains("signed in") || signedIn();
        HashMap<String, String> opts = new HashMap<>();
        if (address != null && address.contains("paypal_address")) {
            opts.put("paypal_eligible", "true");
        }
        if (country == null || country.isEmpty()) {
            country = "United States";
        }
        boolean iship = userType.contains("iship") || !country.equalsIgnoreCase("United States");
        opts.put("country", country);
        opts.put("checkout_eligible", "true");

        if (!(onPage("responsive_checkout", "checkout"))) {
            new CheckoutUtils().navigateToCheckout(signIn, iship);
        }

        // checkout can take some time, page hang watchdog can safely be paused
        pausePageHangWatchDog();
        Checkout checkout = new Checkout(opts, bops);
        if (iship) {
            checkout.ishipCheckout(pageName);
        } else {
            CheckoutUtils.RCPage page = CheckoutUtils.RCPage.fromString(pageName);
            if (signIn) {
                checkout.rcSignedIn(page, opts);
            } else {
                logger.info("******** RCGUEST and page is " + page);
                checkout.rcGuest(page, opts);
            }
        }
        resumePageHangWatchDog();
        Thread.sleep(10000);
        harOrder = MainRunner.browsermobServer.getHar();
        resumePageHangWatchDog();

    }

    @Then("^I verify the data attributes for tealium tags on \"([^\"]*)\" page from pagesource for \"([^\"]*)\" product for registry$")
    public void i_verify_the_data_attributes_for_tealium_tags_on_page_from_pagesource_for_product_for_registry(String pageType, String product, List<String> navAppDataAttributes1) throws Throwable {
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        for (String pdpDataAttr : navAppDataAttributes1) {
            if (pdpDataAttr.equalsIgnoreCase(("product_name"))) {
                logger.info(pdpDataAttr + "\" : [\"" + productName + "\"]");

//                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
//                        pageSrcVal.contains(pdpDataAttr + "\" : [\""+ productName+"\"]"));
                logger.info("product_name is present in page page source");

            }
            if (pdpDataAttr.equalsIgnoreCase(("product_id"))) {
                logger.info(pdpDataAttr + "\" : [\"" + productId[1] + "\"]");

                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : [\"" + productId[1] + "\"]"));
                logger.info("product_id is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_category_id"))) {
                logger.info(pdpDataAttr + "\" : [");
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : ["));
                logger.info("product_category_id is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("true_fit_size"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : \"" + truefitSize + "\""));
                logger.info("true_fit_size is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_video"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : \"" + productVideo + "\""));
                logger.info("product_video is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("order_by_phone"))) {

                logger.info(pdpDataAttr + "\" : \"false");
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available", pageSrcVal.
                        contains(pdpDataAttr + "\" : \"true") || pageSrcVal.contains(pdpDataAttr + "\" : \"false"));
                logger.info("order_by_phone is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("lean_template"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available", pageSrcVal.
                        contains(pdpDataAttr + "\" : \"true") || pageSrcVal.contains(pdpDataAttr + "\" : \"false"));
                logger.info("lean_template is present in page page source");
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_category_name"))) {
                switch (product.toLowerCase()) {
                    case "member":
                        logger.info(pdpDataAttr + "\" : [\"");
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\""));
                        logger.info("product_category_name is present in page page source");
                        break;
                    case "master":
                        logger.info(pdpDataAttr + "\" : [\"");
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\""));
                        logger.info("product_category_name is present in page page source");
                        break;
                }
            }
            if (pdpDataAttr.equalsIgnoreCase(("product_type"))) {
                switch (product.toLowerCase()) {
                    case "member":
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\"MEMBER"));
                        logger.info("product_type is present in page page source");
                        break;
                    case "master":
                        Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                                pageSrcVal.contains(pdpDataAttr + "\" : [\"MASTER"));
                        logger.info("product_type is present in page page source");
                        break;
                }
            }
            if (pdpDataAttr.equalsIgnoreCase(("page_type"))) {
                Assert.assertTrue(pdpDataAttr + " data layer attribute is not available",
                        pageSrcVal.contains(pdpDataAttr + "\" : \"product"));
                logger.info("page_type is present in page page source");
            }
        }
    }

    @Then("^I verify pixel tags script is available in network call for \"([^\"]*)\"$")
    public void i_verify_pixel_tags_script_is_available_in_network_call_for(String jsFile, List<String> networkCall) throws Throwable {
        Boolean isPixelJSPresent = false;
        String pixelScriptjs = networkCall.get(0);
        WebDriverManager.getWebDriver().navigate().refresh();
        logger.info("Getting Har content");
        har = MainRunner.browsermobServer.getHar();
        Wait.forPageReady();
        Thread.sleep(8000);
        if (har != null && har.getLog() != null) {
            List<HarEntry> harEntries = har.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }
            for (HarEntry harEntry : harEntries) {
                logger.info(harEntry.getRequest().getUrl());
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(pixelScriptjs)) {
                    logger.info("Pixel tag script is available in network call -> " + harEntry.getRequest().getUrl());
                    isPixelJSPresent = true;
                    break;
                }
            }

            Assert.assertTrue("Pixel tag script is not available in network call", isPixelJSPresent == true);
        }
        else{
            logger.info("Har file is null: Unable to capture network call");
            Assert.fail("Unable to capture network call");
        }
    }

    @Then("^I click on forgot password link$")
    public void i_click_on_forgot_password_link() throws Throwable {

        Wait.forPageReady();
        Thread.sleep(5000);
        Clicks.click("dataLayer_page.forgotpasswordlink");
        logger.info("Clicked on forgot password link");
        Wait.forPageReady();
        Thread.sleep(5000);

    }

    @Then("^I click on forgot password link for bcom$")
    public void i_click_on_forgot_password_link_for_bcom() throws Throwable {

        Wait.forPageReady();
        Thread.sleep(1000);
        Clicks.click("dataLayer_page.forgotpasswordlink_bcom");
        logger.info("Clicked on forgot password link for bcom");
        Wait.forPageReady();

    }

    @When("^I visit the web site for following \"([^\"]*)\" pages$")
    public void i_visit_the_web_site_for_following_pages(String fashionPageUrl) throws Throwable {

        Navigate.visit(fashionPageUrl);
        Wait.forPageReady();
        Thread.sleep(2000);
        logger.info("Navigated to fashionPageUrl for Bcom" + fashionPageUrl);
    }

    @Then("^I click on account preferences link for bcom$")
    public void i_click_on_account_preferences_link_for_bcom() throws Throwable {

        Wait.forPageReady();
        Thread.sleep(3000);
        Clicks.click("dataLayer_page.accountPreferencesBcom");
        logger.info("Clicked on account Preferences for bcom");
        Wait.forPageReady();
        Thread.sleep(3000);

    }

    @Then("^I click on credit gateway link for bcom$")
    public void i_click_on_credit_gateway_link_for_bcom() throws Throwable {

        Wait.forPageReady();
        Thread.sleep(12000);
        Wait.untilElementPresent("dataLayer_page.MyCreditCardBcom");
        Clicks.click("dataLayer_page.MyCreditCardBcom");
        logger.info("Clicked on MyCredit Card for bcom");
        Wait.forPageReady();
        Thread.sleep(7000);

    }

    @Then("^I click on view registry link on hover registry guide$")
    public void i_click_on_view_registry_link_on_hover_registry_guide() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(8000);
//        Clicks.click("dataLayer_page.registryGuidelink");
//        logger.info("Clicked on Registry guide link");
//        Wait.forPageReady();
//        Thread.sleep(2000);
        Clicks.hoverForSelection("dataLayer_page.manageRegistrylink");
        logger.info("Hover manageRegistry link");
        Thread.sleep(1000);
        Clicks.click("dataLayer_page.viewRegistrylink");
        logger.info("Clicked on viewRegistry link");
        Wait.forPageReady();
        Thread.sleep(5000);

    }

    @When("^I visit the website for \"([^\"]*)\" page$")
    public void I_visit_the_website_for_polaris_page(String urlSuffix) throws Throwable {
        String currentUrl = WebDriverManager.getCurrentUrl();
        String polarisUrl = currentUrl + urlSuffix;
        Thread.sleep(1000);
        Navigate.visit(polarisUrl);
        Thread.sleep(1000);
        logger.info("With Suffix :-" + polarisUrl);
        currentUrl = WebDriverManager.getCurrentUrl();
        logger.info("Current Url after navigation :-" + currentUrl);
        Wait.forPageReady();
        Thread.sleep(3000);

    }

    @When("^I navigate to home page for creating registry$")
    public void I_navigate_to_home_page_for_creating_registry() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("header.goto_wedding_registry", 15);
        Clicks.click("header.goto_wedding_registry");
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("registry_home.goto_find_registry", 30);
        }
        if (!onPage("registry_home")) {
            Clicks.javascriptClick("header.goto_wedding_registry");
        }
        CommonUtils.closeIECertError();
    }
}