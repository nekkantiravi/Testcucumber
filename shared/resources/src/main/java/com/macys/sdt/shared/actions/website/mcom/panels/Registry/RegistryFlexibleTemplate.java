package com.macys.sdt.shared.actions.website.mcom.panels.Registry;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegistryFlexibleTemplate {

    public static boolean imageCarouselExists(WebElement row) {
        return !row.findElements(By.className("flexCarousel")).isEmpty();
    }

    public static boolean jspExists(WebElement row) {
        return !row.findElements(By.className("reg-home-main-wrapper")).isEmpty()
                || !row.findElements(By.id("iw-vpreview")).isEmpty()
                || !row.findElements(By.cssSelector("style")).isEmpty();
    }

    public static boolean customPopUpExists(WebElement row) {
        return !row.findElements(By.className("adLinkPopUp")).isEmpty();
    }

    public static boolean catIconsExists(WebElement row) {
        return !row.findElements(By.className("adCatIcon")).isEmpty();
    }

    public static boolean imageMapExists(WebElement row) {
        return !row.findElements(By.cssSelector("area")).isEmpty();
    }

    public static boolean imageExists(WebElement row) {
        return !row.findElements(By.cssSelector("img")).isEmpty();
    }

    public static boolean slideShowExists(WebElement row) {
        return !row.findElements(By.className("slideContainer")).isEmpty();
    }

    public static boolean videoExists(WebElement row) {
        return !row.findElements(By.className("overlayVideo")).isEmpty()
                || !row.findElements(By.cssSelector("[id~='vid_']")).isEmpty();
    }

    public static boolean simpleTextExists(WebElement row) {
        return !row.findElements(By.cssSelector(".notCatIcon>.textComponent")).isEmpty();
    }

    public static List<String> getMediaTypeInRow(WebElement row) {
        ArrayList<String> mediaTypes = new ArrayList<>();
        if (imageCarouselExists(row)) {
            mediaTypes.add("image_carousel");
        }
        if (slideShowExists(row)) {
            mediaTypes.add("slideshow");
        }
        if (videoExists(row)) {
            mediaTypes.add("video");
        }
        if (jspExists(row)) {
            mediaTypes.add("jsp");
        }
        if (customPopUpExists(row)) {
            mediaTypes.add("custom_popup");
        }
        if (imageMapExists(row)) {
            mediaTypes.add("image_map");
        }
        if (catIconsExists(row)) {
            mediaTypes.add("category_icon");
        }
        if (imageExists(row)) {
            mediaTypes.add("image");
        }
        if (simpleTextExists(row)) {
            mediaTypes.add("text");
        }
        return mediaTypes;
    }

}
