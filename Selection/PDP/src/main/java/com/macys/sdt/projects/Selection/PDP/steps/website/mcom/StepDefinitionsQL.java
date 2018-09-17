package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class StepDefinitionsQL extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();

    @And("^I hover over a random product thumbnail and click on the QuickLook button on \"([^\"]*)\" (site|iship|registry) mode$")
    public void hovering_over_thumbnails_for_random_QuickLook_button(String pg, String mode) throws Throwable {

        Random rand = new Random();
        int thumbnailOpt;
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.masterProductMemberListImg", 20);
        switch (pg) {
            case "PDP": {
                List<WebElement> thumbnails = Elements.findElements(Elements.element("pdp.masterProductMemberListImg"));
                List<WebElement> qlBtn = Elements.findElements(Elements.element("quick_look.qlBtn"));

                logger.info("*** Number of qlBtn: " + qlBtn.size() + ", and thumbnails: " + thumbnails.size());
                for(int i = 0; i < thumbnails.size(); i++) {
                    thumbnailOpt = rand.nextInt(thumbnails.size());
                    Clicks.hoverForSelection(thumbnails.get(thumbnailOpt));
                }
                Thread.sleep(2000);
                thumbnailOpt = rand.nextInt(thumbnails.size());
                Clicks.hoverForSelection(thumbnails.get(thumbnailOpt));
                Thread.sleep(2000);
                Clicks.click(Elements.element("quick_look.qlBtn"));
                break;
            }
        }
        Thread.sleep(2000);
    }

    @And("^I click (A2B|Checkout|ProductDetails) button on QuickLook overlay on \"([^\"]*)\" (site|iship|registry) mode$")
    public void clicking_buttons_on_QuickLook_overlay(String arg, String pg, String mode) throws Throwable {

        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.quick_look.qlA2BBtn", 15);
        switch (pg) {
            case "Master PDP": {
                if(arg.equalsIgnoreCase("A2B")) {
                    Thread.sleep(3000);
//                    String Id[] = Elements.findElement(Elements.element("quick_look.qlWebId")).getText().split(":", 0);
//                    productId = Id[1].trim();
                    Clicks.click(Elements.element("quick_look.qlA2BBtn"));
                    Wait.secondsUntilElementPresent("pdp.quick_look.qlA2B_Msg", 5);
                    softly.assertThat(Elements.elementPresent("quick_look.qlA2B_Msg")).as("quick_look.qlA2B_Msg").isEqualTo(true);
                }
                else if(arg.equalsIgnoreCase("Checkout")) {
                    Wait.secondsUntilElementPresent("pdp.quick_look.qlCheckoutNowBtn", 5);
                    Clicks.click(Elements.element("quick_look.qlCheckoutNowBtn"));
                }
                else if(arg.equalsIgnoreCase("ProductDetails")) {
                    Wait.secondsUntilElementPresent("pdp.quick_look.qlPDPLink", 5);
                    Clicks.click(Elements.element("quick_look.qlPDPLink"));
                }
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^I verify the basic elements of QuickLook overlay on \"([^\"]*)\" (site|iship|registry) mode$")
    public void verifying_QuickLook_overlay(String pg, String mode) throws Throwable {

        Wait.forPageReady();
        Wait.secondsUntilElementPresent("quick_look.qlA2BBtn", 10);
        switch (pg) {
            case "PDP": {
                softly.assertThat(Elements.elementPresent("quick_look.qlBrandName")).as("quick_look.qlBrandName").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("quick_look.qlProductName")).as("quick_look.qlProductName").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("quick_look.qlProductPrice")).as("quick_look.qlProductPrice").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("quick_look.qlQuantity")).as("quick_look.qlQuantity").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("quick_look.qlMainImg")).as("quick_look.qlMainImg").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("quick_look.qlWebId")).as("quick_look.qlWebId").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("quick_look.qlPDPLink")).as("quick_look.qlPDPLink").isEqualTo(true);
                if(!(mode.equalsIgnoreCase("registry")))
                    softly.assertThat(Elements.elementPresent("quick_look.qlA2BBtn")).as("quick_look.qlA2BBtn").isEqualTo(true);
                else
                    softly.assertThat(Elements.elementPresent("quick_look.qlA2RBtn")).as("quick_look.qlA2RBtn").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify navigation to (member|master) PDP in (site|iship|registry) mode$")
    public void verifying_navigation_to_PDP(String pg, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(pg + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
        String productTitle = Elements.findElement(Elements.element("pdp.productTitle")).getText();
        softly.assertThat(productTitle.isEmpty()).as("productTitle").isEqualTo(false);
        softly.assertThat(Elements.elementPresent(Elements.element("pdp.web_id"))).as("productId").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.productQuantity")).as("productQuantity").isEqualTo(true);
        if(pg.equalsIgnoreCase("member")) {
            if(mode.equalsIgnoreCase("iship"))
                Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

            scrollToLazyLoadElement("pdp.bottomTabs");
            Thread.sleep(3000);
            softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
            logger.info(String.format("** " + pg + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
        }
        softly.assertAll();
        Thread.sleep(2000);
    }




}