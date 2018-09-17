package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Created by yh00119 on 2/8/17.
 */
public class PathToPurchaseFull extends StepUtils {

    public static String sItem = null;

    @Then("^I navigate to ptp full size products to validate products and description by passing \"([^\"]*)\" and \"([^\"]*)\"$")
    public void I_navigate_to_ptp_full_size_products(String year, String month) throws NoSuchElementException {

        Response resp = RESTOperations
                .doGET("http://"+EnvironmentDetails
                        .otherApp("SubscriptionXApi")
                        .ipAddress+":8080/xapi/subscription/v1/content/monthlybox?year=" + year + "&month=" + month + "&_expand=products", null);
        System.out.println("Response::" + resp);
        String ptpServiceOut = resp.readEntity(String.class);
        System.out.println("Service Response:" + ptpServiceOut);
        JSONObject jsonObj = new JSONObject(ptpServiceOut);

        System.out.println("_______:" + jsonObj);
        String productResult = jsonObj.getJSONArray("products").toString();
        System.out.println("product Result::: =>  " + productResult);
        // List<WebElement> listOfProd = Elements.findElements(By.cssSelector(".reg-price"));
        // List<WebElement> listOfPrices = WebDriverManager.getWebDriver().findElements(By.cssSelector(".reg-price"));
        /*if (productResult != null) {
            for (int i = 0; i < productResult.length(); i++) {
                JSONObject product = productResult.getJSONObject(i);
                System.out.println("&********************" + product);
                JSONObject productPriceObj = product.getJSONObject("price");
                Double originalPrice = productPriceObj.getDouble("originalPrice");
               *//**//* boolean uiOriginalPriceToService = compareUiPriceWithService(originalPrice, listOfPrices);
                if (uiOriginalPriceToService) {
                    continue;
                }
                Assert.fail("false");
                break;*//*/
            }*/
        Integer counter = 0;
        try {
            boolean ptpProd = Elements.elementPresent("path_to_purchase_full.ptp_full_products");
            if (ptpProd) {
                //Commented below 3 lines of code as objects changed and add to bag functionality removed.
               /* List<WebElement> listOfProd = Elements.findElements(By.cssSelector(".small-7.large-16.columns>a>img"));
                List<WebElement> listOfAddBags = Elements.findElements(By.cssSelector(".addToBag.add-to-bag"));
                Assert.assertEquals(listOfProd.size(), listOfAddBags.size());*/
                List<WebElement> listOfProd = Elements.findElements(By.cssSelector(".product-title"));
                //comparing original prices from service with UI
                System.out.println("listOfProd::::"+listOfProd.size());

                for (WebElement fullProdList : listOfProd) {
                    if (fullProdList.isDisplayed()) {
                        counter++;
                        //boolean isContains = productResult.contains(fullProdList.getAttribute("alt"));
                        System.out.println("fullProdList.getCssValue::::"+fullProdList.getCssValue(".product-title"));
                        boolean isContains = productResult.contains(fullProdList.getCssValue(".product-title"));
                        System.out.println("isContains ::: " + isContains);
                    }
                }
                Navigate.browserBack();
            } else {
                Assert.assertFalse("No products available for the selected month:" + month + "and year" + year, false);
            }
        } catch (NoSuchElementException ex) {
            System.out.println("No Images on monthlybox page:" + ex);
            Assert.assertFalse("No Images/products available for the selected month:" +ex, false);
        }

    }

    /*else {
            System.out.println("Service is returning empty response - Please check the service for respective month" + resp);
        }*/


    /**
     * Compares ServiceOriginal price with List of UI product original prices
     *
     * @param originalPrice
     * @param listOfPrices
     * @return true if service original price is present in UI products else false
     */
    private boolean compareUiPriceWithService(Double originalPrice, List<WebElement> listOfPrices) {

        if (null == originalPrice) {
            return false;
        }
        String strOriginalPrice = String.valueOf(originalPrice);
        java.util.Iterator<WebElement> itr = listOfPrices.iterator();
        while (itr.hasNext()) {
            String valueOrigPrice = itr.next().getText();
            String uiOrigPrice = valueOrigPrice.replaceAll("[^0-9.]", "");
            if (null != uiOrigPrice && strOriginalPrice.equals(uiOrigPrice)) {
                System.out.println("uiOrigPrice uiOrigPrice ::->::"+uiOrigPrice);
                Assert.assertEquals(strOriginalPrice.equals(uiOrigPrice), true);
                return true;
            }
        }

        return false;
    }


    @Then("^I navigate to ptp full size products to validate products original price$")
    public void I_navigate_to_ptp_full_size_products_to_validate_products_original_price(String year, int month) throws NoSuchElementException {
        Response resp = RESTOperations
                .doGET("http://"+EnvironmentDetails
                        .otherApp("SubscriptionXApi")
                        .ipAddress+":8080/xapi/subscription/v1/content/monthlybox?year=" + 2017 + "&month=" + month + "&_expand=products", null);
        System.out.println("Response::" + resp);
        String ptpServiceOut = resp.readEntity(String.class);
        System.out.println("Service Response:" + ptpServiceOut);
        JSONObject jsonObj = new JSONObject(ptpServiceOut);
        if (jsonObj.has("products")) {
            JSONArray productResult = jsonObj.getJSONArray("products");
            System.out.println("productResult productResult:::::::" + productResult);
            if (productResult != null) {
                List<WebElement> listOfPrices = Elements.findElements(By.cssSelector(".reg-price"));
                System.out.println("List of original price:" + listOfPrices);
                for (int i = 0; i < productResult.length(); i++) {
                    JSONObject product = productResult.getJSONObject(i);
                    System.out.println("&********************" + product);
                    JSONObject productPriceObj = product.getJSONObject("price");
                    Double originalPrice = productPriceObj.getDouble("originalPrice");
                    boolean uiOriginalPriceToService = compareUiPriceWithService(originalPrice, listOfPrices);
                    if (uiOriginalPriceToService) {
                        continue;
                    }
                    Assert.fail("false");
                    break;
                }
                Navigate.browserBack();

         /*else {
            System.out.println("Service is returning empty response - Please check the service for respective month" + resp);
        }*/

            }
        }
        Navigate.browserBack();
    }


    @Then("^I navigate to ptp full size products to validate products retail price$")
    public void I_navigate_to_ptp_full_size_products_to_validate_products_retail_price(String year, int month) throws NoSuchElementException {
        Response resp = RESTOperations
                .doGET("http://"+EnvironmentDetails
                        .otherApp("SubscriptionXApi")
                        .ipAddress+":8080/xapi/subscription/v1/content/monthlybox?year=" + year + "&month=" + month + "&_expand=products", null);
        System.out.println("Response::" + resp);
        String ptpServiceOut = resp.readEntity(String.class);
        System.out.println("Service Response:" + ptpServiceOut);
        JSONObject jsonObj = new JSONObject(ptpServiceOut);
        JSONArray productResult = jsonObj.getJSONArray("products");
        List<WebElement> listOfPrices = Elements.findElements(By.cssSelector(".sale-price"));
        System.out.println("List of original price:" + listOfPrices);
        if (productResult == null) {
            Navigate.browserBack();
        }
        for (int i = 0; i < productResult.length(); i++) {
            JSONObject product = productResult.getJSONObject(i);
            System.out.println("product********************" + product);
            JSONObject productPriceObj = product.getJSONObject("price");
            Double originalPrice = productPriceObj.getDouble("retailPrice");
            System.out.println("originalPrice********************" + originalPrice);
            boolean uiOriginalPriceToService = compareUiPriceWithService(originalPrice, listOfPrices);
            if (uiOriginalPriceToService) {
                continue;
            }
        }
        Navigate.browserBack();

    } /*else {
            System.out.println("Service is returning empty response - Please check the service for respective month" + resp);
        }*/


    @And("^I should see \"([^\"]*)\" on ptp page$")
    public void I_should_see_error_on_ptp_page(String errorMsg) throws Throwable {
        Thread.sleep(3000);
        scrollToLazyLoadElement("path_to_purchase_full.ptp_manage_error");
        String manageSubError = Elements.findElement(Elements.element("path_to_purchase_full.ptp_manage_error")).getText();
        if (errorMsg.equals(manageSubError)){
            Assert.assertTrue("Displaying valid error message when guest user click on Manage Subscription::"+manageSubError, true);
        } else{
            Assert.fail("Its not throwing valid error message if guest user click on Manage Subscription::"+manageSubError);
        }
    }


    @And("^I launch ptp page url$")
    public void I_launch_ptp_page_url() throws Throwable {
        Navigate.visit(RunConfig.url + "/subscription/monthlybox");
    }

    @Then("^I see \"([^\"]*)\" on ptp page$")
    public void I_see_subscribe_on_ptp_page(String status) throws Throwable {
        scrollToLazyLoadElement("path_to_purchase_full.ptp_sub_button");
        Thread.sleep(1000);
        if (Elements.elementPresent("path_to_purchase_full.ptp_sub_button")) {
            scrollToLazyLoadElement("path_to_purchase_full.ptp_sub_button");
            sItem = Elements.findElement(Elements.element("path_to_purchase_full.ptp_sub_button")).getText();
        } else if (Elements.elementPresent("path_to_purchase_full.ptp_wl_button")) {
            scrollToLazyLoadElement("path_to_purchase_full.ptp_wl_button");
            sItem = Elements.findElement(Elements.element("path_to_purchase_full.ptp_wl_button")).getText();
        } else if (Elements.elementPresent("path_to_purchase_full.ptp_sub_full")) {
            scrollToLazyLoadElement("path_to_purchase_full.ptp_sub_full");
            sItem = Elements.findElement(Elements.element("path_to_purchase_full.ptp_sub_full")).getText();
        } else {
            Assert.fail("Unable to find the subscription status on ptp page");
        }
        if (sItem.contains(status)) {
            System.out.println("User is able to see the subscription status as:" + sItem);
            Assert.assertTrue("User is able to see the subscription status as:" + sItem, true);
        } else {
            Assert.fail("Subscription status is not displaying as expected:" + sItem);
        }
    }

    @And("^I should see manage subscription link on ptp page$")
    public void I_should_see_manage_subscription_link_on_ptp_page() throws Throwable {
        if (Elements.elementPresent("path_to_purchase_full.ptp_manage_link")){
            Assert.assertTrue("manage subscription link is not displayed in PTP Page", true);
        } else {
            Assert.fail("Error - manage subscription link is displaying on ptp page");
        }
    }

    @And("^I click manage subscription link on ptp page$")
    public void I_click_manage_subscription_link_on_ptp_page() throws Throwable {
        scrollToLazyLoadElement("path_to_purchase_full.ptp_manage_link");
        Clicks.click("path_to_purchase_full.ptp_manage_link");
    }

    @Then("I should see subscription month box \"([^\"]*)\" on ptp page")
    public void I_should_see_subscription_month_box_on_ptp_page(String date) throws Throwable{
        scrollToLazyLoadElement("path_to_purchase_full.ptp_monthly_box");
        Calendar cal = Calendar.getInstance();
        Integer currentDay = cal.get(Calendar.DAY_OF_MONTH);
        String month;
        if(currentDay > Integer.parseInt(date)){
            month = getMonth(cal, 2);
        } else {
            month = getMonth(cal, 1);
        }

        String monthlyBoxMessage = Elements.findElement(Elements.element("path_to_purchase_full.ptp_monthly_box")).getText();
        String message = "To get your "+month+" Beauty Box:";
        if (message.equals(monthlyBoxMessage)){
            Assert.assertTrue("Displaying valid monthly box subscription message:: "+monthlyBoxMessage, true);
        } else{
            Assert.fail("Not Displaying valid monthly box subscription message:: "+monthlyBoxMessage);
        }
    }

    private String getMonth(Calendar cal, Integer month) {
        cal.add(Calendar.MONTH, month);
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

    @Then("I should not see subscription month box on ptp page")
    public void I_should_not_see_subscription_month_box_on_ptp_page() {
        if (Elements.elementPresent("path_to_purchase_full.ptp_monthly_box")){
            Assert.fail("Error - Subscription monthly box label is displaying on ptp page");
        } else {
            Assert.assertTrue("Subscription monthly box label is not displayed in ptp Page", true);
        }
    }

}