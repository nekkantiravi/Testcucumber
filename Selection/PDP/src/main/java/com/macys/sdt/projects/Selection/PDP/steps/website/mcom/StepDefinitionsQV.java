package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class StepDefinitionsQV extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();
    private static String webId = "";


    @And("^I hover over a random product image and click on QuickView button on \"([^\"]*)\" page (site|iship|registry) mode$")
    public void hovering_over_thumbnails_for_random_QuickLook_button(String pg, String mode) throws Throwable {

        Wait.forPageReady();
        if(pg.contains("Search") || pg.contains("ZSR")) {
            String PDPSource = WebDriverManager.getWebDriver().getPageSource();
//            PDPSource = PDPSource.split("<type>")[1].split("</type>")[0];
            softly.assertThat(PDPSource.contains("searchResults")).as("searchResults PageSource").isEqualTo(true);
            logger.info(String.format("Search Page V/V."));
        }
        else if(pg.equalsIgnoreCase("Browse")) {
            String PDPSource = WebDriverManager.getWebDriver().getPageSource();
//            PDPSource = PDPSource.split("<type>")[1].split("</type>")[0];
            softly.assertThat(PDPSource.contains("catalog - browse")).as("Browse PageSource").isEqualTo(true);
            logger.info(String.format("Browse Page V/V."));
        }
        Random rand = new Random();
        if(pg.contains("ProsHR")) {
            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/2)");
            List<WebElement> prosImgs;
            int prosImgOpt = 0;
            Thread.sleep(2000);
            if(pg.contains("ShoppingBag")) {
                Wait.secondsUntilElementPresent("quick_view.ProsShoppingBagImgs", 20);
                prosImgs = Elements.findElements(Elements.element("quick_view.ProsShoppingBagImgs"));
                prosImgOpt = rand.nextInt(prosImgs.size());
                Clicks.hoverForSelection(prosImgs.get(prosImgOpt));
                logger.info("** There are " + prosImgs.size() + " images on " + pg + " page " + mode + " mode!");
            }
            else if(pg.contains("A2B")) {
                Wait.secondsUntilElementPresent("quick_view.ProsBagImgs", 20);
                prosImgs = Elements.findElements(Elements.element("quick_view.ProsBagImgs"));
                prosImgOpt = rand.nextInt(prosImgs.size());
                Clicks.hoverForSelection(prosImgs.get(prosImgOpt));
                logger.info("** There are " + prosImgs.size() + " images on " + pg + " page " + mode + " mode!");
            }
            else if(pg.contains("ZSR")) {
                Wait.secondsUntilElementPresent("quick_view.ProsZSRThumbnails", 15);
                scrollToLazyLoadElement("quick_view.ProsZSRThumbnails");
                prosImgs = Elements.findElements(Elements.element("quick_view.ProsZSRThumbnails"));
                prosImgOpt = rand.nextInt(prosImgs.size());
                logger.info("There are " + prosImgs.size() + " images on " + pg + " page " + mode + " mode!");
                logger.info("Randomly selected image:: " + prosImgOpt);
//                int cnt = prosImgOpt / 5;
//                if(prosImgOpt % 5 == 0 && Elements.elementPresent(Elements.element("quick_view.thumbnailsRightArrow"))) {
//                    for(int i = 1; i < cnt -1; i++) {
//                        Thread.sleep(1000);
//                        Clicks.click(Elements.element("quick_view.thumbnailsRightArrow"));
//                    }
//                }
//                else if(prosImgOpt % 5 > 0 && Elements.elementPresent(Elements.element("quick_view.thumbnailsRightArrow"))) {
//                    for(int i = 1; i < cnt; i++) {
//                        Thread.sleep(1000);
//                        Clicks.click(Elements.element("quick_view.thumbnailsRightArrow"));
//                    }
//                }
//                logger.info("Number of clicks to reach the randomly selected image:: " + cnt);
                Clicks.hoverForSelection(prosImgs.get(prosImgOpt));
            }
            Thread.sleep(3000);
            try {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("quick_view.qvBtn", 15);
                List<WebElement> qv_button = Elements.findElements(Elements.element("quick_view.qvBtn"));
                Clicks.hoverForSelection(qv_button.get(prosImgOpt));
                Clicks.click(qv_button.get(prosImgOpt));
//                Clicks.click(Elements.element("quick_view.qvBtn"));
            } catch (Exception e) {
                logger.warning(String.format("Clicking the old QuickView button as the new button is not displayed on " + pg + " page in " + mode + " mode!"));
                e.printStackTrace();
                if((Wait.secondsUntilElementPresent("quick_view.qvOldBtn", 5)))
                    Clicks.click(Elements.element("quick_view.qvOldBtn"));
                else {
                    Clicks.hoverForSelection(Elements.findElement(By.xpath(".//*[@class='thumbnailImage recommendationImage qvEnabled']")));
                    Clicks.click(Elements.findElement(By.xpath(".//*[@id='quickViewLauncher']")));
                }
            }
        }
        else {
            Wait.secondsUntilElementPresent("quick_view.productThumbnails", 20);
            List<WebElement> productImgs = Elements.findElements(Elements.element("quick_view.productThumbnails"));
            int imgOpt = rand.nextInt(productImgs.size());
            Clicks.hoverForSelection(productImgs.get(imgOpt));
            logger.info("** There are " + productImgs.size() + " images on " + pg + " page " + mode + " mode!");
            Thread.sleep(3000);
            Clicks.click(Elements.element("quick_view.qvBtn"));
        }
        logger.info("A random Quick_View button is clicked!");
        Wait.secondsUntilElementPresent("quick_view.productId", 10);
        webId = Elements.findElement(Elements.element("quick_view.productId")).getText();

        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I select a (random|maximum) (quantity|size|color) on QuickView overlay in (site|iship|registry) mode$")
    public void selecting_QtySizeColor_on_QuickView_overlay(String arg, String attr, String mode) throws Throwable {

        Random rand = new Random();
        switch(attr) {
            case "quantity" : {
                Wait.secondsUntilElementPresent("quick_view.qvQuantity", 10);
                Clicks.click(Elements.element("quick_view.qvQuantity"));
                List<WebElement> qtyOptions = Elements.findElements(Elements.element("quick_view.qvQuantityOpt"));
                logger.info("*** Number of Quantity Options: " + qtyOptions.size());
                int qtyOpt;

                if(arg.equalsIgnoreCase("random")) {
                    qtyOpt = rand.nextInt(qtyOptions.size());
                    qtyOptions.get(qtyOpt).click();
                }
                else if(arg.equalsIgnoreCase("maximum"))
                    qtyOptions.get(qtyOptions.size() -1).click();
                else {
                    qtyOptions.get((qtyOptions.size()) - (qtyOptions.size())).click();
                    logger.info("Default Quantity Selected!");
                }
                break;
            }
            case "size" : {
                Clicks.click(Elements.element("quick_view.qvSize"));
                Thread.sleep(2000);
                List<WebElement> sizes = Elements.findElements(Elements.element("quick_view.qvSizeOpt"));
                logger.info("*** Number of Size Options: " + sizes.size());
                int sizeOpt = rand.nextInt(sizes.size());
                Thread.sleep(2000);
                sizes.get(sizeOpt).click();
                break;
            }
            case "color" : {
                Wait.secondsUntilElementPresent("quick_view.qvColorOpt", 10);
                if(Elements.elementPresent(Elements.element("quick_view.qvColorOpt"))) {
                    List<WebElement> colors = Elements.findElements(Elements.element("quick_view.qvColorOpt"));
                    logger.info("*** Number of Color Options: " + colors.size());
                    int colorOpt = rand.nextInt(colors.size());
                    Thread.sleep(2000);
                    colors.get(colorOpt).click();
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I click \"([^\"]*)\" on QuickView overlay in (site|iship|registry) mode$")
    public void clicking_buttons_on_QuickView_overlay(String arg, String mode) throws Throwable {

        Thread.sleep(2000);
        switch(arg) {
            case "A2B": {
                Wait.secondsUntilElementPresent("quick_view.qvA2BBtn", 10);
                Clicks.click(Elements.element("quick_view.qvA2BBtn"));
                break;
            }
            case "A2L": {
                Wait.secondsUntilElementPresent("quick_view.qvA2LBtn", 10);
                Clicks.click(Elements.element("quick_view.qvA2LBtn"));
                break;
            }
            case "A2R": {
                Wait.secondsUntilElementPresent("quick_view.qvA2RBtn", 10);
                Clicks.click(Elements.element("quick_view.qvA2RBtn"));
                break;
            }
            case "A2R Link": {
                Wait.secondsUntilElementPresent("quick_view.qvA2RLink", 10);
                Clicks.click(Elements.element("quick_view.qvA2RLink"));
                break;
            }
            case "Checkout": {
                Wait.secondsUntilElementPresent("quick_view.qvCheckoutBtn", 10);
                Clicks.click(Elements.element("quick_view.qvCheckoutBtn"));
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^I verify the (all|basic) elements on QuickView overlay in (site|iship|registry) mode$")
    public void verifying_elements_on_QuickView_overlay(String arg, String mode) throws Throwable {

        Wait.forPageReady();
        Wait.secondsUntilElementPresent("quick_view.productTitle", 10);
        softly.assertThat(Elements.elementPresent("quick_view.productTitle")).as("Quick_View productTitle").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("quick_view.qvQuantity")).as("Quick_View qvQuantity").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("quick_view.qvA2BBtn")).as("Quick_View qvA2BBtn").isEqualTo(true);
        if(mode.equalsIgnoreCase("site"))
            softly.assertThat(Elements.elementPresent("quick_view.qvA2LBtn")).as("Quick_View qvA2LBtn").isEqualTo(true);
        else if(mode.equalsIgnoreCase("registry"))
            softly.assertThat(Elements.elementPresent("quick_view.qvA2RBtn")).as("Quick_View qvA2RBtn").isEqualTo(true);
        else
            Assert.assertFalse(Elements.elementPresent("quick_view.qvA2LBtn"));
        softly.assertThat(Elements.elementPresent("quick_view.productDetails")).as("Quick_View productDetails").isEqualTo(true);

        if(arg.equalsIgnoreCase("all")) {
            if(Elements.elementPresent("quick_view.qvColorsNextArrow")) {
                softly.assertThat(Elements.elementPresent("quick_view.qvColorsNextArrow")).as("Quick_View qvColorsNextArrow").isEqualTo(true);
                Clicks.click(Elements.element("quick_view.qvColorsNextArrow"));
                Thread.sleep(1000);
                softly.assertThat(Elements.elementPresent("quick_view.qvColorsPrevArrow")).as("Quick_View qvColorsPrevArrow").isEqualTo(true);
                Clicks.click(Elements.element("quick_view.qvColorsPrevArrow"));
                Thread.sleep(1000);
            }
            softly.assertThat(Elements.elementPresent("quick_view.productImg")).as("Quick_View productImg").isEqualTo(true);
            if(Elements.elementPresent("quick_view.AltImgsNextArrow")) {
                softly.assertThat(Elements.elementPresent("quick_view.AltImgsNextArrow")).as("Quick_View AltImgsNextArrow").isEqualTo(true);
                Clicks.click(Elements.element("quick_view.AltImgsNextArrow"));
                Thread.sleep(1000);
                softly.assertThat(Elements.elementPresent("quick_view.AltImgsPrevArrow")).as("Quick_View AltImgsPrevArrow").isEqualTo(true);
                Clicks.click(Elements.element("quick_view.AltImgsPrevArrow"));
                Thread.sleep(1000);
            }
            softly.assertThat(Elements.elementPresent("quick_view.zoomInBtn")).as("Quick_View zoomInBtn").isEqualTo(true);
            Clicks.click(Elements.element("quick_view.zoomInBtn"));
            Thread.sleep(1000);
            softly.assertThat(Elements.elementPresent("quick_view.zoomOutBtn")).as("Quick_View zoomOutBtn").isEqualTo(true);
            Clicks.click(Elements.element("quick_view.zoomOutBtn"));
            Thread.sleep(1000);
            softly.assertThat(Elements.elementPresent("quick_view.zoomInBtn")).as("Quick_View zoomInBtn").isEqualTo(true);
            Clicks.click(Elements.element("quick_view.zoomInBtn"));
            Thread.sleep(1000);
            softly.assertThat(Elements.elementPresent("quick_view.zoomResetBtn")).as("Quick_View zoomResetBtn").isEqualTo(true);
            Clicks.click(Elements.element("quick_view.zoomResetBtn"));
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify \"([^\"]*)\" message on QuickView overlay in (site|iship|registry) mode$")
    public void verifying_messages_on_QuickView_overlay(String arg, String mode) throws Throwable {

        Thread.sleep(2000);
        switch(arg) {
            case "in stock": {
                softly.assertThat(Elements.elementPresent("quick_view.productAvailMsg")).as("Quick_View productAvailMsg").isEqualTo(true);
                break;
            }
            case "x items added to your bag.": {
                softly.assertThat(Elements.elementPresent("quick_view.A2BSuccessMsg")).as("Quick_View A2BSuccessMsg").isEqualTo(true);
                break;
            }
            case "Added to your guest List.": {
                String expectedMsg = arg + " Sign In to save it to your account.";
                softly.assertThat(Elements.elementPresent("quick_view.A2LSuccessMsg")).as("Quick_View A2LSuccessMsg").isEqualTo(true);
                softly.assertThat((Elements.findElement("quick_view.A2LSuccessMsg").getText()).replace("\n"," ").equalsIgnoreCase(expectedMsg)).as("Quick_View A2LSuccessMsg Text").isEqualTo(true);
                logger.info("Actual Message: " + (Elements.findElement("quick_view.A2LSuccessMsg").getText()).replace("\n"," "));
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify that the product is added to shopping bag$")
    public void verifying_productId_addedToBag() throws Throwable {

        Wait.secondsUntilElementPresent("shopping_bag.shoppingbagWebId", 15);
        List<WebElement> bagWebIds = Elements.findElements(Elements.element("shopping_bag.shoppingbagWebId"));
        Boolean verify = false;
        for (WebElement ID: bagWebIds){
            if(ID.getText().equals(webId)){
                softly.assertThat(webId.equals(ID.getText())).as("Shopping Bag Web ID").isEqualTo(true);
                verify = true;
                logger.info("Expected product added to shopping bag. "+webId+" product id found in shopping bag");
            }
        }
        if(!verify){
            softly.assertThat(false).as("Shopping Bag Web ID").isEqualTo(true);
            logger.warning("Expected Product not added to shopping bag. "+webId+" product id not found in shopping bag" );
        }
        softly.assertAll();
    }

    @Then("^I verify zoomer and altImages on QuickView overlay in (site|iship|registry) mode$")
    public void verifying_zoomer_elements(String mode) throws Throwable {

        List<WebElement> altImgs = Elements.findElements(Elements.element("quick_view.productAltImgs"));
        logger.info(altImgs.size() + " altImages displayed!");
        int cnt = 1;
        for(WebElement img: altImgs) {
            Clicks.click(img);
            Clicks.click(Elements.element("quick_view.zoomInBtn"));
            Thread.sleep(1000);
            Clicks.click(Elements.element("quick_view.zoomOutBtn"));
            Thread.sleep(1000);
            cnt++;
            if(cnt >= (altImgs.size()/2)) {
                logger.info(cnt + " out of " + altImgs.size() + " altImages were clicked & verified on QV overlay on " + mode + " mode!");
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I verify that the customer reviews are displayed in quick peek for chanel product$")
    public void iVerifyThatTheCustomerReviewsAreDisplayedInQuickPeekForChanelProduct() throws Throwable {
        Wait.untilElementPresent("quick_view.quick_view_reviews");
        Assert.assertTrue("Customer review is not displayed",
                Elements.elementPresent("quick_view.quick_view_reviews"));
        logger.info("Verified that the customer review is displayed in quick peek on chanel browse page");
    }

}