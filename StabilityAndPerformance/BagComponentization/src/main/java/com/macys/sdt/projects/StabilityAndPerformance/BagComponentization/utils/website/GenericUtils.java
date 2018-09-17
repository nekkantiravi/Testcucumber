package com.macys.sdt.projects.StabilityAndPerformance.BagComponentization.utils.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * @author YC22D1M
 *
 */
public class GenericUtils extends StepUtils {

    /**
     * Method to click on random product thumbnail in browse page
     *
     * @param selector
     */
    public static void clickRandomProductThumbnail(String selector) {
        try {
            List<WebElement> thumbnail_container = Elements.findElement(selector).findElements(
                    By.xpath("//ul/li[@class='productThumbnail borderless']//a[@class='productThumbnailLink']"));
            Random r = new Random();
            if (thumbnail_container.size() != 0) {
                int randomValue = r.nextInt(thumbnail_container.size());
                thumbnail_container.get(randomValue).click();
            }
        } catch (Exception e) {
            System.out.println("Unable to click on random product thumbnail");
            e.printStackTrace();
        }
    }

    /**
     * Method to select random color in PDP page
     *
     * @return
     */
    public static String selectedRandomColorInStandardPDP() {
        WebElement webEl = null;
        try {
            webEl = Elements.findElement("product_display.default_color_selected");
        } catch (Exception e) {
            System.out.println("Unable to select color in PDP page");
            e.printStackTrace();
        }
        return (webEl.getAttribute("data-title"));

    }

    /**
     * Method to select product title in PDP page
     *
     * @param selector
     * @return
     */
    public static WebElement selectedProductTitleElementInPDP(String selector) {
        WebElement webEl = null;
        try {
            webEl = Elements.findElement(selector);
        } catch (Exception e) {
            System.out.println("Unable to select product title/or not visible in PDP page");
            e.printStackTrace();
        }
        return webEl;
    }

    public static void clickAddToBagButtonInPDPPage() {
        try {
            try {
                Clicks.click("product_display.add_to_bag_button");
            } catch (Exception e) {
                System.out.println("'Add To Bag' button is not visible or available");
                e.printStackTrace();
            }
            Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_summary_panel");
            if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_summary_panel"))
                Clicks.clickIfPresent("product_display.technical_error");
            if (isErrorPaneVisible())
                Clicks.click("home.popup_close");
        } catch (NoSuchElementException e) {
            System.out.println("Add to bag summary panel not visible or page not redirected correctly");
            e.printStackTrace();
        }
    }

    public static void selectRandomSizeInPDPPage(String selector) {
        Select size = new Select(Elements.findElement(selector));
        List<WebElement> sizeOptions = size.getOptions();
        Random rand = new Random();
        int selectSizeIndex = rand.nextInt(sizeOptions.size());
        size.selectByIndex(selectSizeIndex);
    }

    public static String selectedSizeInPDPPage(String selector) {
        Select size = new Select(Elements.findElement(selector));
        WebElement sizeEl = size.getFirstSelectedOption();
        return sizeEl.getText();
    }

    public static List<QuickbagProduct> getAllQuickbagProductDetails() {
        List<QuickbagProduct> productDetails = new ArrayList<>();
        int noOfProducts = Elements.findElements("quickbag.product_title").size();
        QuickbagProduct p = new QuickbagProduct(Integer.parseInt(String.valueOf(Elements.findElements("quickbag.product_title").size())));
        for (int i = 0; i < noOfProducts; i++) {
            p.name = Elements.findElements("quickbag.product_title").get(i).getText().split("\n")[0];
            String price = Elements.findElements("quickbag.price_details").get(i).getText();
            if (price.contains("FREE")) {
                String gift_price = "FREE";
                p.retail_price = Double.parseDouble(gift_price);
                p.quantity = 1;
            } else if (price.contains("NOW") || price.contains("SALE")) {
                String qb_retail_price = Elements.findElements("quickbag.price_details").get(i).findElement(By.className("salePrice")).getText().replaceAll("[a-zA-Z:$ ]", "");
                p.retail_price = Double.parseDouble(qb_retail_price);
            } else {
                String qb_retail_price = price.replaceAll("[a-zA-Z$: ]", "");
                p.retail_price = Double.parseDouble(qb_retail_price);
            }
            p.color_size = Elements.findElements("quickbag.product_title").get(i).getText().split("\n")[1];
            try {
                Clicks.hoverForSelection(By.id("itemInfo"));
                p.product_promoDesc = Elements.findElements("quickbag.promo_desc").get(i).getText();
                System.out.println(p.product_promoDesc);
            } catch (Exception e) {
                p.product_promoDesc = "null";
            }
            try {
                Clicks.hoverForSelection(By.id("itemInfo"));
                p.badges = Elements.findElements("quickbag.gift_badge").get(i).getText();
                System.out.println(p.badges);
            } catch (Exception e) {
                p.badges = "null";
            }
            if (price.contains("Qty"))
                p.quantity = Integer.parseInt(price.replace("x Qty. ", ""));
            else
                p.quantity = 1;
        }
        String qb_bagTotal = Elements.findElement("quickbag.bag_subtotal").getText();
        p.bagTotal = Double.parseDouble(qb_bagTotal.replaceAll("[a-zA-Z$: ]",""));
        productDetails.add(p);
        System.out.println(p);
        return productDetails;
    }

    public static String selectRandomSize(String mode){
        String size = null;
        if (mode.equalsIgnoreCase("iship") || mode.equalsIgnoreCase("registry")) {
            if (Elements.findElement("product_display.pdp_size") == null) {
                size = "No Size";
            } else {
                int size_count = Elements.findElement("product_display.pdp_size").findElements(By.className("size")).size();
                int number = (int) (Math.random() * size_count);
                size = Elements.findElement("product_display.pdp_size").findElements(By.className("size")).get(number).getText();
                Clicks.click(Elements.findElement("product_display.pdp_size").findElements(By.className("size")).get(number));
            }
        }else{
            if(Elements.findElement("product_display.size_section") == null){
                size = "No size";
            }else{
                int size_count = Elements.findElement("product_display.size_section").findElements(By.className("swatch")).size();
                int number = (int) (Math.random() * size_count);
                size = Elements.findElement("product_display.size_section").findElements(By.className("swatch")).get(number).getText();
                Clicks.click(Elements.findElement("product_display.size_section").findElements(By.className("swatch")).get(number));
            }
        }
        return size;
    }

    public static String selectRandomColor(String mode){
        String color = null;
        int color_count;
        int number;
        if (mode.equalsIgnoreCase("iship") || mode.equalsIgnoreCase("registry")) {
            if (Elements.findElement(By.className("productColor")) == null) {
                color = "No Color";
            }else {
                if (Elements.findElement(By.className("colorSwatch")) == null) {
                    color = Elements.findElement(By.className("productColor")).getText();
                } else {
                    color_count = Elements.findElements(By.className("colorSwatch")).size();
                    number = (int) (Math.random() * color_count);
                    color = Elements.findElements(By.className("colorSwatch")).get(number).getText();
                    Elements.findElements(By.className("colorSwatch")).get(number).click();
                }
            }
        }else{
            if(Elements.findElement("product_display.total_color_swatches") == null){
                if(Elements.findElement(By.className("selectedColorName")) == null){
                    color = "No Color";
                }else{
                    color = Elements.findElement(By.className("selectedColorName")).getText();
                }
            }else{
                color_count = Elements.findElements("product_display.select_default_color").size();
                number = (int) (Math.random() * color_count);
                color = Elements.findElements("product_display.select_default_color").get(number).getAttribute("aria-label");
                Elements.findElements("product_display.select_default_color").get(number).click();
            }
        }
        return color;
    }
}