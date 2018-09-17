package com.macys.sdt.projects.Selection.Regression.actions.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.utils.CommonUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Random;

public class DSVPDPActions {

    private static CheckoutSteps checkout = new CheckoutSteps();
    private static PageNavigation pageNavigation = new PageNavigation();
    private static final Logger logger = LoggerFactory.getLogger(DSVPDPActions.class);

    public static void closePopUp(){
        String popUpSelector = "pdp.close_popup";
        Clicks.clickIfPresent(popUpSelector);
        Wait.untilElementNotPresent(popUpSelector);
    }

    public static void addRandomEGiftToBag() throws Throwable{
        navigateToEgiftPDP();
        Wait.untilElementPresent("product_display.egift_amount");
        TextBoxes.typeTextNEnter("product_display.egift_amount", "500");
        TextBoxes.typeTextNEnter("product_display.egift_recipient_email", "test@test.com");
        Clicks.click("product_display.add_to_bag_button");
        Wait.secondsUntilElementPresentAndClick("quick_view.quick_view_add_to_bag_checkout", 7);
    }

    private static void selectRandomProduct() {
        Wait.untilElementPresent("search_result.product_thumbnail_link");
        Clicks.randomJavascriptClick("search_result.product_thumbnail_link");
        StepUtils.shouldBeOnPage("product_display");
    }

    public static void addAnotherProduct() {
        Clicks.click("product_display.add_another_to_bag");
        Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
    }

    public static void NavigateToPDPFromShoppingBag() {
        Wait.secondsUntilElementNotPresent("shopping_bag.product_image", 7);
        Clicks.click("shopping_bag.product_image");
        Wait.untilElementPresent("product_display.add_another_to_bag");
    }

    public static void continueToShoppingBag() throws Throwable {
        checkout.I_checkout_on_add_to_bag_overlay();
        Wait.untilElementPresent("shopping_bag.verify_page");
    }

    public static void changeCountryTo(String country) {
        boolean newDropDownEnabled;
        StepUtils.pausePageHangWatchDog();
        if (Wait.secondsUntilElementPresent("international_shipping.country", (StepUtils.safari() ? 20 : 5))) {
            Assert.assertTrue("Not on international context page.", Wait.secondsUntilElementPresent("international_shipping.country", (StepUtils.safari() ? 20 : 5)));
            newDropDownEnabled = false;
        } else {
            Assert.assertTrue("Not on international context page.", Wait.secondsUntilElementPresent("international_shipping.country_options", (StepUtils.safari() ? 20 : 5)));
            newDropDownEnabled = true;
        }
        List<String> values;
        Random random = new Random();
        if (StepUtils.bloomingdales() && newDropDownEnabled) {
            values = DropDowns.getAllCustomValues("international_shipping.country_options", "international_shipping.country_list");
        } else {
            values = (StepUtils.edge() ? DropDowns.getAllValuesUsingJavaScript("international_shipping.country") : DropDowns.getAllValues("international_shipping.country"));
        }
        String selectCountry = (country.equals("a random country") ? values.get(random.nextInt(values.size())) : country);
        if (newDropDownEnabled) {
            DropDowns.selectCustomText("international_shipping.country_options", "international_shipping.country_list", selectCountry);
        } else {
            DropDowns.selectByText("international_shipping.country", selectCountry);
        }
        Clicks.click("international_shipping.save_continue");
        Wait.forPageReady();
        StepUtils.resumePageHangWatchDog();
        Clicks.clickIfPresent("home.close_overlay_country");
        Wait.forPageReady();
        Clicks.clickIfPresent("home.close_welcome");
        StepUtils.closeBcomLoyaltyPromotionVideoOverlay();
    }

    public static int getItemsCount() {
        Wait.secondsUntilElementPresent("home.shopping_bag_item_count" , 10);
        String productsCount = Elements.getText("home.shopping_bag_item_count");
        productsCount = productsCount.replaceAll("\\D+", "");
        return Integer.parseInt(productsCount);
    }

    public static void navigateToCoachPDP() {
        Clicks.hoverForSelection(Elements.findElement(By.linkText("HANDBAGS")));
        pageNavigation.iSelectSubcategoryFromFlyoutMenu("COACH");
        selectRandomProduct();
        StepUtils.shouldBeOnPage("product_display");
    }

    public static void navigateToChanelPDP() throws Throwable {
        Clicks.hoverForSelection(Elements.findElement(By.linkText("BEAUTY")));
        pageNavigation.iSelectSubcategoryFromFlyoutMenu("CHANEL");
        Clicks.clickWhenPresent(By.linkText("FRAGRANCE"));
        selectRandomProduct();
        StepUtils.shouldBeOnPage("product_display");
    }

    public static void navigateToEgiftPDP() {
        Clicks.hoverForSelection(Elements.findElement(By.linkText("GIFTS")));
        pageNavigation.iSelectSubcategoryFromFlyoutMenu("E-Gift Cards");
        Wait.untilElementPresent("category_browse.verify_page");
        closePopUp();
        selectRandomProduct();
    }

    public static boolean verifyMouseHoveronPDPImage() {
        boolean status=false;
        try {
            Wait.untilElementPresent("pdp.pdp_product_image");
            Clicks.hoverForSelection("pdp.pdp_product_image");
            Clicks.click("pdp.pdp_product_image");
            Wait.forPageReady();
            Wait.forLoading("pdp.product_image_zoom");
            status= Elements.elementPresent("pdp.product_image_zoom");
           return status;
        } catch (Exception e) {
            return status;
        }
    }

    public static void facebookVerification() {
        Assert.assertTrue("Facebook social icon is missing",
                Elements.findElement("pdp.facebook").isDisplayed());
        try {
            Clicks.click("pdp.facebook");
        } catch (Exception e) {
            Assert.fail("Unable to click facebook social link " + e.getMessage());
        }
        Navigate.switchWindow(1);
        Wait.forPageReady();
        logger.info("facebook::" + WebDriverManager.getCurrentUrl());
        Assert.assertTrue("Not navigated to Facebook login page",
                WebDriverManager.getCurrentUrl().contains("facebook"));
        Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
        Assert.assertTrue("Facebook login Page is not loaded properly", Elements.findElement("pdp.facebook_login").isDisplayed());
        Navigate.switchWindowClose();
        logger.info("Facebook social icon verification completed..!!");
    }

    public static void pinterestVerification() {
        Assert.assertTrue("Pinterest social icon is missing", Elements.elementPresent("pdp.pinterest"));
        Clicks.javascriptClick("pdp.pinterest");
        Navigate.switchWindow(1);
        Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 20);
        Assert.assertTrue("Not navigated to Pinterest login page",
                WebDriverManager.getCurrentUrl().contains("pinterest"));
        Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
        Navigate.switchWindowClose();
        logger.info("Pinterest social icon verification completed..!!");
    }

    public static void twitterVerification() {
        Assert.assertTrue("Twitter social icon is missing",
                Elements.findElement("pdp.twitter").isDisplayed());
        try {
            Clicks.click("pdp.twitter");
        } catch (Exception e) {
            Assert.fail("Unable to click twitter social link");
        }
        Navigate.switchWindow(1);
        Wait.forPageReady();
        logger.info("twitter::" + WebDriverManager.getCurrentUrl());
        Assert.assertTrue("Not navigated to twitter login page",
                WebDriverManager.getCurrentUrl().contains("twitter"));
        Assert.assertTrue("Twitter page doesn't loaded properly",Elements.findElement("pdp.twitter_login").isDisplayed());
        Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
        Navigate.switchWindowClose();
        logger.info("Twitter social icon verification completed..!!");
    }
}
