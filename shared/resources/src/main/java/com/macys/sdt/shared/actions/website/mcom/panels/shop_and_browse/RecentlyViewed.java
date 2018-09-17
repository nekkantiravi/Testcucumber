package com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RecentlyViewed extends StepUtils {

    private static List<String> recentlyViewed;

    public static boolean isDisplayed() {
        pausePageHangWatchDog();
        scrollToLazyLoadElement("recently_viewed_items.thumbnail_wrapper");
        Wait.untilElementPresent("recently_viewed_items.thumbnail_wrapper");
        List<WebElement> rviProductLinks = rviProductThumbnails().stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());
        recentlyViewed = rviProductLinks.stream()
                .map(e -> e.getAttribute("id"))
                .collect(Collectors.toList());
        resumePageHangWatchDog();
        return Elements.elementPresent("recently_viewed_items.thumbnail_wrapper");
    }

    public static boolean isEditVisible() {
        closePopup();
        Wait.untilElementPresent("recently_viewed_items.edit_button");
        return Elements.findElement("recently_viewed_items.edit_button").getText().toLowerCase().contains("edit");
    }

    public static boolean isDoneVisible() {
        Wait.untilElementPresent("recently_viewed_items.edit_button");
        return Elements.findElement("recently_viewed_items.edit_button").getText().toLowerCase().contains("done");
    }

    public static List<String> getRecentlyViewed() {
        List<WebElement> rviProductLinks = rviProductThumbnails().stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());
        return rviProductLinks.stream()
                .map(e -> e.getAttribute("id"))
                .collect(Collectors.toList());
    }

    public static List<String> getOldRecentlyViewed() {
        return recentlyViewed;
    }

    public static void updateProducts() {
        recentlyViewed = getRecentlyViewed();
    }

    public static void selectLeftRightArrows(String arrowDirection) {
        closePopup();
        Wait.untilElementPresent("recently_viewed_items.thumbnail_wrapper");
        Elements.findElement("recently_viewed_items.rvi_container").findElement(By.className("icon-ui-chevron-" + arrowDirection + "-bk-huge")).click();
    }

    public static String selectRandomRVIProduct(){
        pausePageHangWatchDog();
        Wait.untilElementPresent("recently_viewed_items.thumbnail_wrapper");
        List<WebElement> rviProductLinks = rviProductThumbnails().stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());;
        int index = rviProductLinks.size() == 1 ? 0 : new Random().nextInt(rviProductLinks.size() - 1);
        WebElement rviLinkEle = rviProductLinks.get(index);
        String productId = rviLinkEle.getAttribute("id");
        Clicks.hoverForSelection(rviLinkEle);
        if(StepUtils.safari()){
            Clicks.javascriptClick(rviLinkEle.findElement(By.tagName("a")));
        }else{
            Clicks.click(rviLinkEle.findElement(By.tagName("a")));
        }
        resumePageHangWatchDog();
        return productId;
    }

    public static List<WebElement> rviProductThumbnails(){
        pausePageHangWatchDog();
        Wait.untilElementPresent("recently_viewed_items.thumbnail_wrapper");
        List<WebElement> rviProductLinks = Elements.findElement("recently_viewed_items.rvi_container").findElements(By.className("recommendationLink")).stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());;
        if(rviProductLinks.isEmpty()){
            rviProductLinks = Elements.findElement("recently_viewed_items.rvi_container").findElements(By.className("rviProductThumbnail")).stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());;
        }
        if(rviProductLinks.isEmpty()){
            rviProductLinks = Elements.findElement("recently_viewed_items.rvi_container").findElements(By.className("productThumbnail")).stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());;
        }
        resumePageHangWatchDog();
        return rviProductLinks;
    }

    public static List<String> getRVIProductPriceValues(){
        pausePageHangWatchDog();
        Wait.untilElementPresent("recently_viewed_items.rvi_container");
        List<WebElement> rviProductPriceSections = Elements.findElement("recently_viewed_items.rvi_container").findElements(By.className("prices")).stream().filter(f-> f.isDisplayed()).collect(Collectors.toList());;
        resumePageHangWatchDog();
        return rviProductPriceSections.stream().map(m -> m.getText()).collect(Collectors.toList());
    }
}
