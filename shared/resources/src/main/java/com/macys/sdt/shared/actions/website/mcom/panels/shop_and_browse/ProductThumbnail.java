package com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse;

import com.macys.sdt.framework.interactions.Elements;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.macys.sdt.framework.utils.StepUtils.bloomingdales;

public class ProductThumbnail {

    private static final Logger logger = LoggerFactory.getLogger(ProductThumbnail.class);

    public static JSONArray getProductThumbnailDetails() throws JSONException {
        JSONArray product_thumbnail_list = new JSONArray();
        JSONObject product_thumbnail = null;
        List<WebElement> thumbnails = Elements.findElements("product_thumbnails.thumbnail_wrapper");
        for (WebElement thumbnail : thumbnails) {
            try {
                if (!thumbnail.getAttribute("class").contains("thumbnailGridMedia")) {
                    product_thumbnail = new JSONObject();
                    product_thumbnail.put("product_id", thumbnail.getAttribute("id"));
                    if (!thumbnail.findElements(By.className("shortDescription")).isEmpty()) {
                        product_thumbnail.put("product_name", thumbnail.findElement(By.className("shortDescription")).getText());
                    } else if(!thumbnail.findElements(By.id("chanelProductDescription")).isEmpty()){
                        product_thumbnail.put("product_name", thumbnail.findElement(By.id("chanelProductDescription")).getText());
                    }else{
                        product_thumbnail.put("product_name", thumbnail.findElement(By.className("productDescription")).getText());
                    }
                    product_thumbnail.put("product_prices", thumbnail.findElement(By.className("prices")).getText().split("\n"));
                    product_thumbnail_list.put(product_thumbnail);
                }
            } catch (StaleElementReferenceException stre) {
                logger.debug("Found exception while reading product details from thumbanil grid. Exception message:"+stre.getMessage());
                logger.info("Completed reading only product count:'"+product_thumbnail_list.length()+"' out of '"+thumbnails.size()+"' products");
                break;
            }
        }
        return product_thumbnail_list;
    }

    public static List<String> getProductIds(){
        List<String> productIds =  new ArrayList();
        List<WebElement> thumbnails = Elements.findElements(Elements.element("product_thumbnails.thumbnail_wrapper"));
        for (WebElement thumbnail : thumbnails) {
            if (!thumbnail.getAttribute("class").contains("thumbnailGridMedia")) {
                productIds.add(thumbnail.getAttribute("id"));
            }
        }
        return productIds;
    }

    public static List<WebElement> getChanelProductElementsByType(String productType){
        List<WebElement> chanelMemberProdElements =  new ArrayList();
        List<WebElement> thumbnails = Elements.findElements(Elements.element("product_thumbnails.thumbnail_wrapper"));
        for (WebElement thumbnail : thumbnails) {
            if (!thumbnail.getAttribute("class").contains("thumbnailGridMedia")) {
                if ((productType.equalsIgnoreCase("member") || productType.equalsIgnoreCase("standard")) && !thumbnail.findElement(By.className("prices")).getText().contains("-")){
                    chanelMemberProdElements.add(thumbnail);
                }else if(productType.equalsIgnoreCase("master") && thumbnail.findElement(By.className("prices")).getText().contains("-")){
                    chanelMemberProdElements.add(thumbnail);
                }else if(productType.equalsIgnoreCase("any")){
                    chanelMemberProdElements.add(thumbnail);
                }
            }
        }
        return chanelMemberProdElements;
    }

    public static Map<String, HashMap> getProdutThumnailWebElements() {
        HashMap<String, WebElement> thumbnailWebElements = null;
        HashMap<String, HashMap> productThumbnails = new HashMap<>();
        WebElement color_swatches;
        WebElement price_events;
        boolean batch_text;
        boolean customer_ratings;
        List<WebElement> thumbnails = Elements.findElements(Elements.element("product_thumbnails.thumbnail_wrapper"));

        for (WebElement thumbnail : thumbnails) {
            if (!thumbnail.getAttribute("class").contains("thumbnailGridMedia")) {
                thumbnailWebElements = new HashMap<>();
                try {
                    color_swatches = thumbnail.findElements(By.className("morecolorswrapper")).isEmpty()
                            ? thumbnail.findElement(By.className("carouselWrapper")) : thumbnail.findElement(By.className("morecolorswrapper"));
                } catch (NoSuchElementException e) {
                    logger.info("Color swatches are not available for Product ID " + thumbnail.getAttribute("id"));
                    color_swatches = null;
                }

                try {
                    price_events = thumbnail.findElements(By.id("priceEventsDiv")).isEmpty()
                            ? thumbnail.findElement(By.id("priceTypeText")) : thumbnail.findElement(By.id("priceEventsDiv"));
                } catch (NoSuchElementException e) {
                    logger.info("Price events are not available for Product ID " + thumbnail.getAttribute("id"));
                    price_events = null;
                }

                try {
                    batch_text = thumbnail.findElement(By.className("badgeHeader")).isDisplayed();
                } catch (NoSuchElementException e) {
                    logger.info("Batch Texts are not available for Product ID " + thumbnail.getAttribute("id"));
                    batch_text = false;
                }

                try {
                    customer_ratings = thumbnail.findElement(By.className("pdpreviews")).isDisplayed();
                } catch (NoSuchElementException e) {
                    logger.info("Customer Ratings not available for Product ID " + thumbnail.getAttribute("id"));
                    customer_ratings = false;
                }

                thumbnailWebElements.put("elm_product_id", thumbnail);
                thumbnailWebElements.put("elm_image_link", thumbnail.findElements(By.className("imageLink")).isEmpty()
                        ? thumbnail.findElement(By.className("productDescLink")) : thumbnail.findElement(By.className("imageLink")));
                thumbnailWebElements.put("elm_color_swatches", color_swatches);
                thumbnailWebElements.put("elm_product_name", thumbnail.findElements(By.className("shortDescription")).isEmpty()
                        ? thumbnail.findElement(By.className("productDescLink")) : thumbnail.findElement(By.className("shortDescription")));
                thumbnailWebElements.put("elm_price_event", price_events);
                thumbnailWebElements.put("elm_prices", thumbnail.findElement(By.className("prices")));
                thumbnailWebElements.put("elm_batch_text", batch_text ? thumbnail.findElement(By.className("badgeHeader")) : null);
                thumbnailWebElements.put("elm_customer_ratings", customer_ratings ? thumbnail.findElement(By.className("pdpreviews")) : null);
                productThumbnails.put(thumbnail.getAttribute("id"), thumbnailWebElements);
            }
        }

        return productThumbnails;
    }

    public static int productThumbnailColumns() {
        // no choice on bcom, always 3
        if (bloomingdales()) {
            return 3;
        }

        WebElement gridView = Elements.findElement(Elements.element("search_result.grid_view"));
        return gridView.getAttribute("class").contains("three") ? 3 : 4;
    }

    public static int getproductThumbnailColumns() {
        // no choice on bcom, always 3
        if (bloomingdales()) {
            return 3;
        }

        WebElement gridView = Elements.findElement(Elements.element("pagination.three_column_items_section"));
        if (gridView == null) {
            return 4;
        } else {
            return 3;
        }
    }
}
