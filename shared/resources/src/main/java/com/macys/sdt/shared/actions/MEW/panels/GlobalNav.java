package com.macys.sdt.shared.actions.MEW.panels;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GlobalNav extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(GlobalNav.class);
    /**
     * Opens global navigation menu on any page
     */
    public static void openGlobalNav() {
        Clicks.clickIfPresent("home.close_app_banner");
        if (onPage("registry_home", "registry_manager", "edit_registry", "registry_homepage")) {
            if ((Elements.findElement("home.global_nav_button_icon").getAttribute("aria-expanded") == null) ||
                    (Elements.findElement("home.global_nav_button_icon").getAttribute("aria-expanded").equals("false"))) {
                Clicks.click("home.global_nav_button");
                Clicks.clickIfPresent("home.global_nav_not_visible");
                if (bloomingdales())
                    Wait.attributeChanged(Elements.findElement("home.global_nav_button"), "aria-expanded", "true");
                Assert.assertTrue("ERROR-ENV: Global Nav bar is not visible", macys() ? Wait.untilElementPresent("home.global_nav_visible") :
                        Elements.findElement("home.global_nav_button").getAttribute("aria-expanded").equals("true"));
            }
        } else if (StepUtils.bloomingdales() && onPage("my_account")) {
            if (Elements.findElement("home.global_nav_visible_myaccount").getCssValue("opacity").equals("0")) {
                Clicks.click("home.global_nav_button");
                Clicks.clickIfPresent("home.global_nav_not_visible");
                Assert.assertTrue("ERROR-ENV: Global Nav bar is not visible",
                        !Elements.findElement("home.global_nav_visible_myaccount").getCssValue("opacity").equals("0"));
            }
        } else {
            if (!Elements.elementPresent("home.global_nav_visible")) {
                Navigate.browserRefresh();
                JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
                jse.executeScript("arguments[0].scrollIntoView(true);", Elements.findElement("home.global_nav_button"));
                Clicks.click("home.global_nav_button");
                Clicks.clickIfPresent("home.global_nav_not_visible");
                Assert.assertTrue("ERROR-ENV: Global Nav bar is not visible",
                        Wait.untilElementPresent("home.global_nav_visible"));
            }
        }
    }

    /**
     * Closes global navigation menu on any page
     */
    public static void closeGlobalNav() {
        // sometimes the GN is in the process of closing (from a previous navigation)
        // but still mid-animation. Give it a moment.
        Utils.threadSleep(500, null);
        if (macys() && onPage("registry_home", "registry_manager")) {
            if (Elements.elementPresent("home.registry_global_nav_visible")) {
                Clicks.click("home.global_nav_button");
                Assert.assertTrue("ERROR-ENV: Global Nav bar is still visible",
                        Wait.untilElementNotPresent("home.registry_global_nav_visible"));
            }
        } else if (StepUtils.bloomingdales() && onPage("my_account")) {
            if (!Elements.getElementCSSAttribute("home.global_nav_visible_myaccount", "opacity").equals("0")) {
                Clicks.click("home.global_nav_button");
                Assert.assertTrue("ERROR-ENV: Global Nav bar is still visible",
                        Elements.getElementCSSAttribute("home.global_nav_visible_myaccount", "opacity").equals("0"));
            }
        } else {
            if (Elements.elementPresent("home.global_nav_visible")) {
                // close global nav by clicking on search text field
                Clicks.javascriptClick("home.search_field");
            }
        }
    }

    /**
     * Selects given menu item from global navigation menu
     *
     * @param gn_name menu item to select
     */
    public static void navigateOnGnByName(String gn_name) {
        Wait.secondsUntilElementPresent("home.nav_menu_list",5);
        for (String aGnName : gn_name.split(" or ")) {
            logger.info("Navigating to " + aGnName + " element in nav menu...");
            String selector = null;
            boolean is_bcom_mew_myaccount_page = (StepUtils.bloomingdales() && onPage("my_account"));
            if (is_bcom_mew_myaccount_page)  {
                selector = "home.current_nav_myaccount";
            } else {
                selector = (RunConfig.useAppium ? "home.current_nav_link" : "home.current_nav");
                selector = WebDriverManager.getCurrentUrl().contains("registry") ?
                        (bloomingdales() && Elements.findElements("home.registry_nav_link").stream().anyMatch(e -> e.findElement(By.xpath("..")).getAttribute("class").contains("current")) ? "home.registry_sub_nav_link" : (macys() ? selector : "home.registry_nav_link")) : selector;
            }
            Wait.untilElementPresent(selector);
            List<WebElement> elements = Elements.findElements(selector);
            for (WebElement el : elements) {
                if (el.getText().equalsIgnoreCase(aGnName)) {
                    Wait.forPageReady();
                    if (bloomingdales() && RunConfig.useAppium && !iOS()) {
                        // For Android
                        Clicks.javascriptClick(el);
                    } else {
                        Elements.elementInView(el);
                        Clicks.javascriptClick(el);
                    }
                    try {
                        if (!is_bcom_mew_myaccount_page && el.isDisplayed()) {
                            Wait.attributeChanged(el, "aria-expanded", "true");
                        }
                    } catch (Exception e) {
                        // GN can be closed by click & cause this exception, nothing to worry about
                    }
                    return;
                }
            }
        }
        Assert.fail("ERROR - ENV : Could not find \"" + gn_name + "\" on global nav");
    }

    /**
     * Clicks on first product from recently viewed panel
     */
    public static void navigateToRecentlyViewedProduct() {
        Wait.untilElementPresent(Elements.element("product_display.recently_viewed_product_container"));
        List<WebElement> recently_viewed_prods = Elements.findElements(Elements.element("product_display.recently_viewed_products"));
        if (recently_viewed_prods == null || recently_viewed_prods.size() == 0) {
            Assert.fail("ERROR-DATA: Unable to find recently viewed products");
        }
        Clicks.click(recently_viewed_prods.get(0).findElement(By.xpath("a")));
    }

    public static String doContinousGlobalNavigationUntilProductsDisplayed() throws Throwable{
        int low = 0,
            high = 0;
        String randomSubCategory="";
        List<WebElement> elements = new ArrayList<WebElement>();
        Random r=null;

        Thread.sleep(1000);
        try{
            elements = Elements.findElements("main_left_nav.global_nav_child_list");
            if(elements != null){
                r = new Random();
                high = elements.size();
            }
        }catch(Exception e) {
            logger.info("Exception raised while doing category navigation.");
        }
        if(high != 0){
            int result = r.nextInt(high-low) + low;
            randomSubCategory = (elements.get(result)).getText();
            logger.info("New random sub category is "+ randomSubCategory);
        }
        return randomSubCategory;
    }
}