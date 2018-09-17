package com.macys.sdt.projects.Discovery.Regression.utils.config.MEW;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchAndBrowseActions extends StepUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchAndBrowseActions.class);

    public static int getProductCount() {
        return Integer.parseInt(Elements.findElement("search_result.product_count").getText().replace("(", "").replace("(", ""));
    }

    public static List<Integer> getProductIds() {
        List<Integer> productIds = new ArrayList<>();
        List<WebElement> prroductElements = Elements.findElements("search_result.product_thumbnail");
        prroductElements.forEach(product -> {
            productIds.add(Integer.parseInt(product.getAttribute("data-product_id")));
        });
        return productIds;
    }

    public static ArrayList getPricesList(String value) {
        Wait.secondsUntilElementPresent("search_result.prices_grid", 10);
        List<WebElement> prices = Elements.findElements("search_result.prices_grid");
        Double sale_max_min_price = null, org_max_min_price = null;
        ArrayList<Double> sale_prices = new ArrayList<>();
        List<WebElement> pricesList;
        String formatPrice;
        switch (value) {
            case "Maximum":
            case "Price: High to Low":
                for (int i = 0; i < prices.size(); i++) {
                    pricesList = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li"));
                    Wait.forPageReady();
                    if (pricesList.size() > 1) {
                        formatPrice = pricesList.get(1).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                        sale_max_min_price = formatPrice.contains("-") ? Double.valueOf(formatPrice.split("-")[1])
                                : Double.valueOf(formatPrice);
                    }
                    formatPrice = pricesList.get(0).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                    org_max_min_price = formatPrice.contains("-") ? Double.valueOf(formatPrice.split("-")[1])
                            : Double.valueOf(formatPrice);
                    sale_prices.add(pricesList.size() > 1 ? sale_max_min_price : org_max_min_price);
                }
                break;
            case "Minimum":
            case "Price: Low to High":
                for (int i = 0; i < prices.size(); i++) {
                    pricesList = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li"));
                    if (pricesList.size() > 1) {
                        formatPrice = pricesList.get(1).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                        sale_max_min_price = formatPrice.contains("-") ? Double.valueOf(formatPrice.split("-")[0])
                                : Double.valueOf(formatPrice);
                    }
                    formatPrice = pricesList.get(0).getText().replaceAll("[a-zA-Z]|[$,]", "");
                    org_max_min_price = formatPrice.contains("-") ? Double.valueOf(formatPrice.split("-")[0])
                            : Double.valueOf(formatPrice);
                    sale_prices.add(pricesList.size() > 1 ? sale_max_min_price : org_max_min_price);
                }
                break;
        }

        return sale_prices;
    }

    public static ArrayList getPricesFromPage(String value) {
        Wait.untilElementPresent("search_result.prices_grid");
        Double sale_max_min_price = null, org_max_min_price = null;
        ArrayList<Double> sale_prices = new ArrayList<>();
        List<WebElement> prices = Elements.findElements("search_result.prices_grid");
        List<WebElement> priceList = new ArrayList<>();
        switch (value) {
            case "Maximum":
            case "Price: High to Low":
                for (int i = 0; i < prices.size(); i++) {
                    Wait.secondsUntilElementPresent("left_facet.price_list", 5);
                    priceList = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li"));
                    if ((priceList.size() > 1) && (priceList.get(1).getText().contains("$") || priceList.get(1).getText().contains("INR"))) {
                        Wait.secondsUntilElementPresent("search_result.prices_grid", 10);
                        String formatPrice = Elements.findElements("search_result.prices_grid").
                                get(i).findElements(By.tagName("li")).get(1).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                        String newFormatPrice = formatPrice.split("\\(")[0];
                        org_max_min_price = newFormatPrice.contains("-") ? Double.valueOf(newFormatPrice.split("-")[1])
                                : Double.valueOf(newFormatPrice);
                        sale_prices.add(org_max_min_price);
                    } else {
                        Wait.secondsUntilElementPresent("search_result.prices_grid", 10);
                        String formatPrice = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li")).get(0).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                        String newFormatPrice = formatPrice.split("\\(")[0];
                        sale_max_min_price = newFormatPrice.contains("-") ? Double.valueOf(newFormatPrice.split("-")[1])
                                : Double.valueOf(newFormatPrice);
                        sale_prices.add(sale_max_min_price);
                    }
                }
                break;
            case "Minimum":
            case "Price: Low to High":
                for (int i = 0; i < prices.size(); i++) {
                    Wait.secondsUntilElementPresent("left_facet.price_list", 5);
                    priceList = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li"));
                    if ((priceList.size() > 1) && ((priceList.get(1).getText().contains("$") || priceList.get(1).getText().contains("INR")))) {
                        Wait.secondsUntilElementPresent("search_result.prices_grid", 10);
                        String formatPrice = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li")).get(1).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                        String newFormatPrice = formatPrice.split("\\(")[0];
                        org_max_min_price = newFormatPrice.contains("-") ? Double.valueOf(newFormatPrice.split("-")[0])
                                : Double.valueOf(newFormatPrice);
                        sale_prices.add(org_max_min_price);
                    } else {
                        Wait.secondsUntilElementPresent("search_result.prices_grid", 10);
                        String formatPrice = Elements.findElements("search_result.prices_grid").get(i).findElements(By.tagName("li")).get(0).getText().replaceAll("[a-zA-Z]|[$, ]", "");
                        String newFormatPrice = formatPrice.split("\\(")[0];
                        sale_max_min_price = newFormatPrice.contains("-") ? Double.valueOf(newFormatPrice.split("-")[0])
                                : Double.valueOf(newFormatPrice);
                        sale_prices.add(sale_max_min_price);
                    }
                }
                break;
        }
        return sale_prices;
    }

    public static List<String> breadCrumbValues() {
        List<String> breadCrumbs = new ArrayList<>();
        if (!Elements.elementPresent("category_browse.facet_section")) {
            Assert.fail("Error - App: Breadcrumbs are not availabale");
        } else {
            String selector = macys() ? "m-breadcrumb" : "b-breadcrumb";
            breadCrumbs = Elements.findElements(By.className(selector)).stream().map(webElement -> webElement.getAttribute("data-breadcrumb-value")).collect(Collectors.toList());
        }
        return breadCrumbs;
    }

    public static int getItemCount() {
        int count;
        String selector = macys() ? "search_result.product_count" : "search_result.total_products";
        Wait.secondsUntilElementPresent(selector, 10);
        if (Elements.findElement(selector).getText().contains("of")) {
            count = Integer.parseInt(Elements.findElement(selector).getText().split(" ")[2]);
        } else if (Elements.getText(selector).contains(" in ")) {
            // This condition applicable for MCOM iship mode
            count = Integer.parseInt(Elements.getText(selector).split(" in ")[0].replaceAll("\\D+", ""));
        } else {
            count = Integer.parseInt(Elements.findElement(selector).getText().replaceAll("\\D+", ""));
        }
        return count;
    }

    public static Map<String, List<String>> selectedFacetValues() throws Throwable {
        Map<String, List<String>> filters = new HashMap<>();
        String selector = macys() ? "m-breadcrumb" : "b-breadcrumb";
        Utils.threadSleep(5000,"ERROR - APP: Issue in collecting selected facet values");
        List<WebElement> breadcrumbs = Elements.findElements(By.className(selector));
        List<String> dataFacets = breadcrumbs.stream()
                .map(ele -> ele.getAttribute("data-facet")).collect(Collectors.toList());
        dataFacets = dataFacets.stream().distinct().collect(Collectors.toList());
        for (String facetKey : dataFacets) {
            List<WebElement> facetValueElements = breadcrumbs.stream()
                    .filter(ele -> ele.getAttribute("data-facet").equals(facetKey)).collect(Collectors.toList());
            List<String> facetValues = facetValueElements.stream().map(ele -> ele.getAttribute("data-breadcrumb-value")).collect(Collectors.toList());
            filters.put(facetKey, facetValues);
        }
        return filters;
    }

    public static List<String> getAllCatSplashMedialLinks() {
        return Elements.findElements("category_splash.category_banner_ads_link").stream().map(webElement -> webElement.getAttribute("href")).collect(Collectors.toList());
    }

    public static List<String> getAllCatSplashMediaImgLinks() {
        return Elements.findElements("category_splash.category_banner_ads_link").stream().map(webElement -> webElement.findElement(By.tagName("img")))
                .map(ele -> ele.getAttribute("src")).collect(Collectors.toList());
    }

    public static List<String> getAllProductImages() {
        return Elements.findElements("search_result.product_thumbnail").stream().filter(webElement -> webElement.findElement(By.tagName("img")).isDisplayed())
                .map(webElement -> webElement.findElement(By.tagName("img")))
                .map(ele -> ele.getAttribute("src")).collect(Collectors.toList());
    }

    /*method to get all the catsplash image links displayed for bcom */
    public static List<String> getAllCatsplashMediaImageLinks(){
       return Elements.findElements("category_splash.banner_images").stream().map(webElement -> webElement.getAttribute("src")).collect(Collectors.toList());
    }
}
