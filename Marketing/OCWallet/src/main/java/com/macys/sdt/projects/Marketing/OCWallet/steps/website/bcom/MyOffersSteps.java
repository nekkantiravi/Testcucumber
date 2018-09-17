package com.macys.sdt.projects.Marketing.OCWallet.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class MyOffersSteps extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
    private static final Logger logger = LoggerFactory.getLogger(MyOffersSteps.class);

    @When("^I navigate to sales and promotions page$")
    public void iNavigateToDealsAndPromotionspage() {
        onPage("home");
        Clicks.hoverForSelection(Elements.element("panel.header.goto_sales"));
        Wait.ajaxDone();
        pageNavigation.iSelectSubcategoryFromFlyoutMenu("See All Promotions");
        Wait.forPageReady();
    }


    @Then("^I verify the sales and promotions page$")
    public void iShouldSeeThePromotionsPage() {
        shouldBeOnPage("my_offers");
        Assert.assertTrue("URL does not include shop keyword",
                WebDriverManager.getCurrentUrl().contains("shop"));
        Assert.assertTrue("Page is not served from NavApp",
                EnvironmentDetails.getAppServer().contains("navapp"));
    }

   /* @When("^I hover over any random category$")
    public void iHoverOverAnyRandomCategory() {
            WebElement element = Elements.getRandomElement(Elements.element("category_menu.category"), (el) -> el.isDisplayed() &&
                    !el.getText().matches("BRANDS|DESIGNERS|THE REGISTRY|GETTING STARTED|WEDDING REGISTRY"));
            Clicks.hoverForSelection(element);
        }
    @Then("^I should see the flyout menu$")
    public void iShouldSeeTheFlyoutMenu() {
        assertTrue("Flyout menu was not opened even after mouse hover on category on Sales and Promotions page.",Elements.elementPresent("category_menu.flyout_menu"));
        logger.info("Verified: Flyout menu is displayed when mouse hover on a category on Sales and Promotions page.");
    }*/

    @When("^I click on 'info/exclusions' link on offer/coupon$")
    public void iClickOnInfoExclusionsLinkOnOfferCoupon() {
        List<WebElement> offers = Elements.findElements("my_offers.offers_container");
        Random ran = new Random();
        int index = ran.nextInt(offers.size());
        Elements.findElements("my_offers.info_exclusions_link").get(index).click();
        Wait.ajaxDone();
    }

    @Then("^I should be able to see shop now link$")
    public void iShouldBeAbleToSeeShopNowLink() {
        assertTrue("'SHOP NOW' link is displayed after clicking on info/exclusions link.",Elements.elementPresent("my_offers.shop_from_info_exclusions"));
        logger.info("Verified: 'SHOP NOW' link is displayed after clicking on info/exclusions link.");
    }

    @Then("^I verify \"Free Shipping\" link$")
    public void iVerifyFreeShippingLink() throws Throwable {
        Elements.findElement("my_offers.free_shipping_image").click();
        Wait.forPageReady();
        Thread.sleep(2000);
        Navigate.switchWindow(1);
        assertTrue("Free Sihpping popup is not displayed when clicked on Free Shipping image on Sales and Promotions page.",onPage("free_shipping_popup"));
        Navigate.switchWindowClose();
        logger.info("Verified: 'Free Shipping' link opens 'Free Shipping' disclaimer popup.");
    }

    @Then("^I verify \"Sign up for email\" link$")
    public void iVerifySignUpForEmailLink() throws Throwable{
        Elements.findElement("my_offers.signup_for_email_image").click();
        Wait.forPageReady();
        assertTrue("Sign up for email page is not displayed when clicked on 'Sign Up For Email' image on Sales and Promotions page.",WebDriverManager.getCurrentUrl().contains("cheetahmail"));
        logger.info("Verified: 'Sign up for email' link navigates to 'Sign Up For Email' page.");
    }

    @Then("^I verify \"Get on the list\" link$")
    public void iVerifyGetOnTheListLink() throws Throwable{
        Elements.findElement("my_offers.get_on_the_list_image").click();
        Wait.forPageReady();
        assertTrue("'Loyallist Sign In' page is not displayed when clicked on 'Get on the list' image on Sales and Promotions page.",WebDriverManager.getCurrentUrl().contains("account/signin?fromLoyalty=loyaltySignIn"));
        logger.info("Verified: 'Get on the list' link navigates to 'Loyallist Sign In' page.");
    }

    @Then("^I verify \"Shop on the go\" link$")
    public void iVerifyShopOnTheGoLink() throws Throwable{
        Elements.findElement("my_offers.shop_on_the_go_image").click();
        Wait.forPageReady();
        assertTrue("Bloomingdales 'mobile-shopping-online' page is not displayed when clicked on 'Shop on the go' image on Sales and Promotions page.",WebDriverManager.getCurrentUrl().contains("mobile-shopping-online"));
        logger.info("Verified: 'Shop on the go' link navigates to 'mobile-shopping-online' page.");
    }

    @When("^I hover over any of the random category$")
    public void iHoverOverAnyOfTheRandomCategory() throws Throwable {
        WebElement element = Elements.getRandomElement(Elements.element("category_menu.category"), (el) -> el.isDisplayed() &&
                !el.getText().matches("BRANDS|DESIGNERS|THE REGISTRY|GETTING STARTED|WEDDING REGISTRY"));
        Clicks.hoverForSelection(element);
    }

    @Then("^I should be able to see the flyout menu$")
    public void iShouldBeAbleToSeeTheFlyoutMenu() throws Throwable {
        assertTrue("Flyout menu was not opened even after mouse hover on category on Sales and Promotions page.",Elements.elementPresent("category_menu.flyout_menu"));
        logger.info("Verified: Flyout menu is displayed when mouse hover on a category on Sales and Promotions page.");
    }
}


