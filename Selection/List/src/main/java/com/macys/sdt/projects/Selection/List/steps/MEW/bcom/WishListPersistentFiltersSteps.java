package com.macys.sdt.projects.Selection.List.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.projects.Selection.List.utils.MEW.ApiResponse.getJsonAPIService;


public class WishListPersistentFiltersSteps {
    private static final Logger logger = LoggerFactory.getLogger(WishListPersistentFiltersSteps.class);
    private String sizeSelected;
    private String prodId;
    private String colorNormal = null;
    private String colorSelected = null;

    @When("^I select random size from filters$")
    public void iSelectRandomSizeFromFilters(){
        Wait.secondsUntilElementPresent("left_facet.show_more_facets", 10);
        WebElement allFilters = Elements.findElement("left_facet.show_more_facets");
        Clicks.javascriptClick(allFilters);
        Wait.secondsUntilElementPresent("search_result.size_facet_filter", 10);
        WebElement sizeAccordion = Elements.findElement("search_result.size_facet_filter");
        Clicks.javascriptClick(sizeAccordion);
        Wait.secondsUntilElementPresent("search_result.facet_list", 10);
        List<WebElement> sizeFacets = Elements.findElement("search_result.facet_list").findElements(By.className("wrapped-size-section"));
        WebElement mensSizeAccordion = sizeFacets.get(0).findElement(By.className("wrapped-size-name"));
        Clicks.javascriptClick(mensSizeAccordion);
        Wait.secondsUntilElementPresent("left_facet.filter_size_container", 10);
        WebElement sizeFacetContainer = Elements.findElement("left_facet.filter_size_container");
        List<WebElement> sizeList = sizeFacetContainer.findElements(By.className("facet-value"))
                .stream()
                .filter(e -> e.getAttribute("aria-checked").equals("false"))
                .collect(Collectors.toList());
        int randomIndex = new Random().nextInt(sizeList.size());
        logger.info("Number of sizes to choose from: " + sizeList.size());
        WebElement sizeItem = sizeList.get(randomIndex);
        sizeSelected = sizeItem.getText();
        logger.info("size selected is: "+sizeSelected);
        sizeItem.click();
    }

    @When("^I select random color from filters$")
    public void iSelectRandomColorFromFilters() {
        Wait.secondsUntilElementPresent("left_facet.show_more_facets", 10);
        WebElement allFilters = Elements.findElement("left_facet.show_more_facets");
        Clicks.javascriptClick(allFilters);
        Wait.secondsUntilElementPresent("search_result.color_facet_filter", 10);
        WebElement colorAccordion = Elements.findElement("search_result.color_facet_filter");
        Clicks.javascriptClick(colorAccordion);
        Wait.secondsUntilElementPresent("left_facet.filter_size_container", 10);
        WebElement colorFacetContainer = Elements.findElement("left_facet.filter_size_container");
        List<WebElement> colorFacets = colorFacetContainer.findElements(By.className("facet-value"))
                .stream()
                .filter(this::colorElements)
                .collect(Collectors.toList());
        int randomIndex = new Random().nextInt(colorFacets.size());
        logger.info("no of colors to choose from "+colorFacets.size());
        WebElement colorItem = colorFacets.get(randomIndex);
        colorSelected = colorItem.findElement(By.className("display-name")).getText();
        logger.info("Color selected: "+colorSelected);
        Clicks.javascriptClick(colorItem);
    }

    @And("^I apply the filters$")
    public void iApplyTheFilters() {
        Wait.secondsUntilElementPresent("search_result.apply_button", 10);
        WebElement applyButton = Elements.findElement("search_result.apply_button");
        Clicks.click(applyButton);
        //Waiting for the product rating icon element as it works best to wait for the page to load after applying filters.
        Wait.secondsUntilElementPresent("search_result.rating_icon",10);
    }

    @And("^I click on Wish List modal$")
    public void iClickOnWishListModal() {
        List<WebElement> products = Elements.findElements("category_browse.product_name_without_brand_name")
                .stream()
                .filter(e -> !e.findElements(By.className("b-j-add-to-wishlist-btn")).isEmpty() && !e.findElements(By.className("b-more-colors")).isEmpty())
                .collect(Collectors.toList());
        logger.info("Number of products: "+products.size());
        int random = new Random().nextInt(products.size());
        WebElement selectedItem = products.get(random);
        prodId = selectedItem.getAttribute("data-product_id");
        logger.info("Product selected: "+prodId);
        WebElement wishListElement = selectedItem.findElement(By.className("b-j-add-to-wishlist-btn"));
        Clicks.javascriptClick(wishListElement);
    }

    @Then("^I verify that color on modal matches with filters$")
    public void iVerifyThatColorOnModalMatchesWithFilters() {
        Wait.secondsUntilElementPresent("category_browse.wish_list_color",10);
        WebElement colorElement = Elements.findElement("category_browse.wish_list_color");
        String modalColorName = colorElement.getText();
        logger.info("modal color name: "+modalColorName);
        JSONObject rootJson = getJsonAPIService(prodId);
        JSONArray colorArray = rootJson.getJSONArray("colors");
        logger.info("API::Number of colors for the product: "+colorArray.length());
        if(colorArray.length()>1){
            for(int i=0;i<colorArray.length();i++)
            {
                JSONObject colorArrayJSONObject = colorArray.getJSONObject(i);
                String colorName = colorArrayJSONObject.optString("name");
                if(colorName.equalsIgnoreCase(modalColorName)){
                    colorNormal = colorArrayJSONObject.optString("colornormal");
                    logger.info("API::ColorNormal: "+colorNormal);
                    break;
                }
            }
        } else {
            logger.info("Single Color");
            return;
        }
        Assert.assertEquals(colorSelected, colorNormal);
        logger.info("ColorNormal matches with API");
    }

    @Then("^I verify that size on modal matches with filters$")
    public void iVerifyThatSizeOnModalMatchesWithFilters(){
        String expectedSize = "Select a Size";
        Wait.untilElementPresent("category_browse.add_to_wish_list");
        WebElement dropDown = Elements.findElement("product_display.select_size_dropdown");
        Select select = new Select(dropDown);
        String modalSize = select.getFirstSelectedOption().getText();
        logger.info("modal size is: "+modalSize);
        if(modalSize.equals(expectedSize)){
            List<WebElement> allSizes = select.getOptions();
            for(WebElement e :allSizes){
                Assert.assertTrue(!e.getText().equals(sizeSelected));
            }
            logger.info("size does not match as it is not in the list");
        }
        else{
            Assert.assertEquals(sizeSelected,modalSize);
            logger.info("size matches on Wish List modal");
        }

    }

    @Then("^I verify that no size is displayed on Wish List$")
    public void iVerifyThatNoSizeIsDisplayedOnWishList(){
        Wait.untilElementPresent("category_browse.add_to_wish_list");
        WebElement dropDown = Elements.findElement("product_display.select_size_dropdown");
        Select select = new Select(dropDown);
        String modalSize = select.getFirstSelectedOption().getText();
        Assert.assertEquals("Select a Size", modalSize);
        logger.info("Size is not selected");
    }

    private boolean colorElements(WebElement element) {
        return element.getAttribute("aria-checked").equals("false")
                && !element.findElement(By.className("display-name")).getText().equals("Tan/Beige")
                && !element.findElement(By.className("display-name")).getText().equals("Ivory/Cream");
    }


}
