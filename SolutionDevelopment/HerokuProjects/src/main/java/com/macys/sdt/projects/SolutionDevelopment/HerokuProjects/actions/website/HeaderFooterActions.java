package com.macys.sdt.projects.SolutionDevelopment.HerokuProjects.actions.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Clicks.clickIfPresent;
import static com.macys.sdt.framework.interactions.Elements.findElement;


public class HeaderFooterActions extends StepUtils {

    public static boolean validateLogoAvailable(String mode) {
        if (mode.equalsIgnoreCase("domestic")) {
            return findElement("heroku_homepage.bloomies_logo").isDisplayed();
        } else {
            //TO DO
            return false;
        }
    }

    public static List<String> getAllMainCategoryNames() {
        return Elements.findElements(Elements.element("heroku_homepage.category_fob")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    public static void storesAndEvents() {
        clickIfPresent("header.goto_stores");
    }

    public static void countryChange() {
        clickIfPresent("header_and_footer.header_international_container");
    }

    public static void myAccount() {
        clickIfPresent("header.goto_my_account_link");
    }

    public static void wishList() {
        clickIfPresent("header.goto_wishlist");
    }

    public static void brownBag() {
        Clicks.click("header.quickbag_hover");
    }

    public static boolean Facebook() {
        return Elements.elementPresent("footer.facebook_icon");
    }

    public static boolean Twitter() {
        return Elements.elementPresent("footer.twitter_icon");
    }

    public static boolean Pinterest() {
        return Elements.elementPresent("footer.pinterest_icon");
    }

    public static boolean Instagram() {
        return Elements.elementPresent("footer.instagram_icon");
    }

    public static boolean Mobile() {
        return Elements.elementPresent("footer.mobile_icon");
    }

    private static String fob;

    public static boolean HoveOverRandomFlyout(String section) {
        List<WebElement> tempcategoryList = Elements.findElements("heroku_homepage.category_fob");
        List<WebElement> categoryList = new ArrayList<>();
        List<String> strCatList;
        switch (section) {
            case "regular":
                strCatList = Arrays.asList("WOMEN", "SHOES", "HANDBAGS", "JEWELRY & ACCESSORIES", "BEAUTY", "MEN", "KIDS", "HOME", "GIFTS");
                break;
            case "custom":
                strCatList = Arrays.asList("DESIGNERS", "WHAT'S NEW", "THE REGISTRY", "SALE");
                break;
            default:
                return false;
        }
        for (WebElement element : tempcategoryList) {
            if (strCatList.contains(element.getText())) {
                categoryList.add(element);
            }
        }
        WebElement randomCategory = categoryList.get(new Random().nextInt(categoryList.size()));
        fob = randomCategory.getText();
        System.out.println("FOB chosen :" + fob);
        Clicks.hoverForSelection(randomCategory);
        return Elements.elementPresent("heroku_homepage.fob_flyout_opened");
    }


    public static String clickOnRandomFlyOutLink() throws Exception {
        List<WebElement> tempSubCategories = Elements.findElements("heroku_homepage.subcategory");
        List<WebElement> subCategories = new ArrayList<WebElement>();
        for (WebElement subCategory : tempSubCategories) {
            switch (fob) {
                case "DESIGNERS":
                    if (subCategory.isDisplayed() && subCategory.getText().equalsIgnoreCase("BURBERRY")) {
                        subCategories.add(subCategory);
                    }
                    break;
                case "WHAT'S NEW":
                    if (subCategory.isDisplayed() && subCategory.getText().equalsIgnoreCase("THE WORK SHOP")) {
                        subCategories.add(subCategory);
                    }
                    break;
                case "THE REGISTRY":
                    if (subCategory.isDisplayed() && subCategory.getText().equalsIgnoreCase("KITCHEN")) {
                        subCategories.add(subCategory);
                    }
                    break;
                case "SALE":
                    if (subCategory.isDisplayed() && subCategory.getText().equalsIgnoreCase("WOMEN")) {
                        subCategories.add(subCategory);
                    }
                    break;
                default:
                    if (subCategory.isDisplayed()) {
                        subCategories.add(subCategory);
                    }
                    break;
            }
        }
        WebElement randomSubCategory = subCategories.get(new Random().nextInt(subCategories.size()));
        String subCategory = randomSubCategory.getText();
        System.out.println("SubCategory chosen:" + subCategory);
        Clicks.click(randomSubCategory);
        return subCategory;
    }

}


