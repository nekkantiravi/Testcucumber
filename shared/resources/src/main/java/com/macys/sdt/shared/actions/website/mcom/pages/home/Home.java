package com.macys.sdt.shared.actions.website.mcom.pages.home;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Home extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Home.class);

    public static String selectRandomSubCategory() {
        String text = null;
        Wait.forPageReady();
        if (!onPage("category_splash") || !WebDriverManager.getCurrentUrl().contains("?id=")) {
            selectRandomCategory();
        }

        while (!onPage("category_browse")) {
            WebElement selected = null;
            if(Elements.elementPresent("category_splash.subcategory")) {
                selected = Elements.getRandomElement(Elements.element("category_splash.subcategory"), (el) -> el.isDisplayed() && !el.getText().matches("(.*?)(ACTIVE|Brands|Impulse|DESIGNERS|WEDDING REGISTRY)(.*?)"));
            }else if (Elements.elementPresent("category_splash.discovery_subcategory")){
                selected = Elements.getRandomElement(Elements.element("category_splash.discovery_subcategory"), (el) -> el.isDisplayed() && !el.getText().matches("(.*?)(ACTIVE|Brands|Impulse|DESIGNERS|WEDDING REGISTRY)(.*?)"));
            }
            text = selected.getText();
            Clicks.click(selected);

            // Safari is not waiting for page load after clicking on subcategory
            if (safari()) {
                Utils.threadSleep(1000, null);
                Wait.forPageReady();
            }

            if (!onPage("category_browse") && !onPage("category_splash")) {
                Navigate.browserBack();
            }
        }
        if (text == null) {
            text = Elements.getText("category_browse.current_category");
        }
        if(!Elements.elementPresent("left_facet.facets_panel")){
            Navigate.browserBack();
            text = selectRandomSubCategory();
        }
        return text;
    }

    public static String selectRandomCategory() {
        Navigate.scrollPage(0, 100);
        Navigate.scrollPage(0, -100);
        WebElement element = Elements.getRandomElement(Elements.element("category_menu.category"), (el) -> el.isDisplayed() &&
                !el.getText().matches("BRANDS|DESIGNERS|THE REGISTRY|GETTING STARTED|WEDDING REGISTRY|ACTIVE|GIFTS|WHAT'S NEW"));
        String category = element.getText();
        Clicks.click(element);
        return category;
    }

    public void selectMainCategory(String category) {
        Navigate.scrollPage(0, 100);
        Navigate.scrollPage(0, -100);
        if(!onPage("registry_home") && WebDriverManager.getCurrentUrl().contains("registry/wedding/registryhome")){
            Navigate.browserRefresh();
            Wait.forPageReady();
            try{Thread.sleep(20000);}catch (Exception e){logger.info(e.getMessage());}
        }
        Wait.secondsUntilElementPresent("home.category", 20);
        if (!Elements.elementPresent("home.category")) {
            scrollToLazyLoadElement("home.category");
            Elements.elementInView("home.category");
        }
        Assert.assertTrue("ERROR - APP : '" + category + "' is not displayed in UI!!",
                Elements.findElements("home.category").stream().anyMatch(el -> el.isDisplayed() && el.getText().trim().equalsIgnoreCase(category)));
        Clicks.clickRandomElement("home.category", el -> el.isDisplayed() && el.getText().trim().equalsIgnoreCase(category));
        Wait.forPageReady();
        Clicks.clickIfPresent("home.close_signup_for_email_popup");
        try {
            // Try selecting FOB one more time using link text, if user not navigated to category_splash page.
            if (!Wait.untilElementPresent(Elements.element("category_splash.left_navigation_container"))) {
                Clicks.click(By.linkText(category));
                Wait.forPageReady();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectSubCategory(String subCategory) {
        if (safari()) {
            Clicks.clickRandomElement("category_splash.subcategory", el -> el.isDisplayed() && el.getText().equalsIgnoreCase(subCategory));
        } else {
            if (macys()) {
                if (edge()) {
                    Clicks.javascriptClick(Elements.findElements(Elements.element("category_splash.subcategory")).stream().filter(el -> el.isDisplayed() && el.getText().equalsIgnoreCase(subCategory)).findFirst().get());
                } else {
                    Wait.secondsUntilElementPresent("category_splash.subcategory", 15);
                    Clicks.clickElementByText("category_splash.subcategory", subCategory);
                }
            } else {
                if (Elements.elementPresent("category_splash.discovery_subcategory")) {
                    Clicks.clickElementByText("category_splash.discovery_subcategory", subCategory);
                } else {
                    Wait.untilElementPresent(Elements.element("category_splash.subcategory"));
                    Clicks.clickElementByText("category_splash.subcategory", subCategory);
                }
            }
        }
        try {
            Wait.untilElementPresent(Elements.element("category_browse.facets_panel"));
        } catch (Exception e) {
            Assert.fail("ERROR - TIMEOUT: Could not browse to " + subCategory + ": " + e);
        }
    }

    public List<String> getAllMainCategoryNames() {
        List<String> categories = Elements.findElements(Elements.element("home.category")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        categories = categories.stream().distinct().filter(m-> !m.equalsIgnoreCase("")).collect(Collectors.toList());
        return categories;
    }

    public List<String> getExpectedMainCategories(String mode) {
        if (macys()) {
            if (mode.equalsIgnoreCase("registry")) {
                return Arrays.asList("WEDDING REGISTRY", "STARTER IDEAS","DINING", "KITCHEN", "BED & BATH", "HOME DECOR", "LUGGAGE", "CLEANING & ORGANIZING", "BRANDS", "WEDDING SHOP");
            }
            else if(mode.equalsIgnoreCase("iship")){
                return Arrays.asList("WOMEN", "MEN", "HOME", "BED & BATH", "SHOES", "HANDBAGS & ACCESSORIES", "KIDS", "JUNIORS", "JEWELRY", "WATCHES", "BRANDS");

            }
            else {
                return Arrays.asList("WOMEN", "MEN", "HOME", "BED & BATH", "SHOES", "HANDBAGS", "BEAUTY", "KIDS", "JUNIORS", "JEWELRY", "WATCHES", "ACTIVE", "BRANDS");
            }
        } else {
            if (mode.equalsIgnoreCase("registry")) {
                return Arrays.asList("GETTING STARTED", "DESIGNERS", "DINING & ENTERTAINING", "KITCHEN", "HOME DECOR", "BED & BATH", "LUGGAGE", "HOME CARE & TECH", "SALE");
            }
            else if(mode.equalsIgnoreCase("iship")){
                return Arrays.asList("DESIGNERS", "EDITORIAL", "WOMEN", "SHOES", "HANDBAGS", "JEWELRY & ACCESSORIES", "MEN", "KIDS", "HOME", "THE REGISTRY", "SALE");
            }
            else
                {
                return Arrays.asList("DESIGNERS", "WHAT'S NEW", "WOMEN", "SHOES", "HANDBAGS", "JEWELRY & ACCESSORIES", "BEAUTY", "MEN", "KIDS", "HOME", "GIFTS", "THE REGISTRY", "SALE");
            }
        }
    }

    public List<String> getAllSubCategoryNames() {
        List<WebElement> linkCategories = macys() ? Elements.findElement(Elements.element("category_splash.left_navigation_container")).findElements(By.xpath("//*[@id=\"firstNavSubCat\"]/li//a"))
                : Elements.findElements(Elements.element("category_splash.subcategories"));
        return linkCategories.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void verifyTopFOBs() throws Throwable {

        List<WebElement> topFobs = Elements.findElements("home.top_fobs");
        Assert.assertTrue("Total FOBs could not be found, Actual:" + topFobs.size() + " Expected:" + "13",
                topFobs.size() == 13);
        for (WebElement elem : topFobs) {

            switch (elem.getText()) {
                case "BRANDS":
                    if (bloomingdales())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "ACTIVE":
                    if (bloomingdales())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "WOMEN":
                    break;
                case "SHOES":
                    break;
                case "HANDBAGS":
                    break;
                case "JEWELRY":
                    if (bloomingdales())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "BEAUTY":
                    break;
                case "MEN":
                    break;
                case "KIDS":
                    break;
                case "HOME":
                    break;
                case "BED & BATH":
                    if (bloomingdales())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "JUNIORS":
                    if (bloomingdales())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "WATCHES":
                    if (bloomingdales())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "DESIGNERS":
                    if (macys())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "WHAT'S NEW":
                    if (macys())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "THE REGISTRY":
                    if (macys())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "SALE":
                    if (macys())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "JEWELRY & ACCESSORIES":
                    if (macys())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                case "GIFTS":
                    if (macys())
                        Assert.fail("Incorrect FOB found:" + elem.getText());
                    break;
                default:
                    Assert.fail("Incorrect FOB found:" + elem.getText());
            }
        }
    }

    public void verifySubNavs() throws Throwable {

        List<WebElement> subNavs = Elements.findElements("home.sub_nav_headers");

        for (WebElement elem : subNavs) {

            switch (elem.getText()) {
                case "Trend Report":
                    break;
                case "The Gift Guide":
                    break;
                case "Lists":
                    break;
                case "Deals & Promotions":
                    break;
                case "Gift Cards":
                    break;
                case "Wedding Registry":
                    break;
                // This case is added for 'Trend Report' and 'The Gift Guide', gets verified in Sub Nav Media
                case "":
                    break;
                default:
                    Assert.fail("Incorrect Sub Nav found:" + elem.getText());
            }
        }

        List<WebElement> subNavMedia = Elements.findElements("home.sub_nav_media");

        for (WebElement elem : subNavMedia) {

            switch (elem.getAttribute("alt")) {
                case "Trend Report":
                    break;
                case "The Gift Guide":
                    break;
                default:
                    Assert.fail("Incorrect Sub Nav media found:" + elem.getText());
            }
        }
    }
}
