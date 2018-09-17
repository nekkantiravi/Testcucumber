package com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.bcom.panels;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.macys.sdt.framework.interactions.Clicks.clickIfPresent;
import static com.macys.sdt.framework.interactions.Elements.findElement;

/**
 * Created by yc04026 on 10/19/2016.
 */
public class HeaderFooterActions extends StepUtils {

    public static boolean validateLogoAvailable(String mode){
        if(!mode.equalsIgnoreCase("registry")){
            return findElement("home.verify_page").isDisplayed();
        }
        else{
            return findElement("bcom_pages.goto_back_to_bloomingdales").isDisplayed();
        }
    }

    public static void clickBloomingdalesIcon(String mode){
        if(!mode.equalsIgnoreCase("registry")){
            clickIfPresent("home.verify_page");
        }
        else{
            clickIfPresent("bcom_pages.goto_back_to_bloomingdales");
        }
    }

    public static void clickSeasonalWrapper(){
        Clicks.click("header.goto_stores");
    }

    public static List<String> getAllMainCategoryNames(){
        Home home = new Home();
        List<String> namesOfMainCategories = home.getAllMainCategoryNames();
        return namesOfMainCategories;
    }

    public static void storesAndEvents(){
        clickIfPresent("header.goto_stores");
    }

    public static void countryChange(){
        clickIfPresent("header_and_footer.header_international_container");
    }

    public static void myAccount(){
        clickIfPresent("header.goto_my_account_link");
    }

    public static void wishList(){
        clickIfPresent("header.goto_wishlist");
    }

    public static void brownBag(){
        Clicks.click("header.quickbag_hover");
    }

    //Getting the contents of auto suggestion list
    public static List<String> getAutoSuggestionWords()throws  Throwable{
        ArrayList<String> getWords = new ArrayList<String>();
        Wait.untilElementPresent("header.suggestions_list");
        List<WebElement> ele = Elements.findElement("header.suggestions_list").findElements(By.className("suggestion"));
        for (WebElement e: ele) {
            getWords.add(e.getText());
        }
        return getWords;
    }

    //scrolling to bottom of the page and clicking specific social icon
    public static void clickSocialIcon(String socialLink){
        scrollToLazyLoadElement("footer.social_icons");
        List<WebElement> social_icons = Elements.findElement(By.className("socialLinks")).findElements(By.tagName("area"));
        for(WebElement si: social_icons) {
            if (si.getAttribute("title").equalsIgnoreCase(socialLink)) {
                si.click();
            }
        }

    }
}
