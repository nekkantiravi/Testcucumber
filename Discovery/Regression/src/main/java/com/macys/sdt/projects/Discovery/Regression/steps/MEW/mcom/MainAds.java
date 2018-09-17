package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by Pawan on 6/26/2017.
 */
public class MainAds extends StepUtils {

    public static final Logger logger = LoggerFactory.getLogger(MainAds.class);
    // private List<WebElement> mainAds = Elements.findElements("home.main_ads");
    private List<WebElement> mainAds = Elements.findElements(By.xpath("//div[@class='vertical-home-banner']//a"));

    @Then("^I verify that main ads are displayed on mobile Home Page$")
    public void i_verify_that_main_ads_are_displayed_on_Home_Page() throws Throwable {
        shouldBeOnPage("home");
        Assert.assertTrue("Main ads are missing on Home Page", mainAdsCount());
        //Iterating through each main ad
        for (WebElement eachAd : mainAds) {
            Assert.assertTrue("Main Ads are missing on Home Page", eachAd.isDisplayed());
        }
    }

    @Then("^I verify that ads are clickable and should not navigate to error page$")
    public void i_verify_that_ads_are_clickable_and_should_not_navigate_to_error_page() throws Throwable {

        if (macys()) {

            int numberOfMainAds = mainAds.size();
            for (int pos = 0; pos < numberOfMainAds; pos++) {
                shouldBeOnPage("home");
                mainAds = Elements.findElements(By.xpath("//div[@class='vertical-home-banner']//a"));
                mainAds.get(pos).click();
                Assert.assertTrue("Invalid response code received", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Navigate.browserBack();
                Wait.forPageReady();
            }
        } else {

            List<WebElement> mainAds = Elements.findElements("home.ad_banners");
            int numberOfMainAds = Elements.findElements("home.ad_banners").size();
            logger.info("no.of Main ads in BCOM ::" + numberOfMainAds);
            for (int pos = 0; pos < numberOfMainAds; pos++) {
                shouldBeOnPage("home");
                mainAds = Elements.findElements("home.ad_banners");
                mainAds.get(pos).click();
                Assert.assertTrue("Invalid response code received", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Navigate.browserBack();
                Wait.forPageReady();

            }
        }
    }

    //return true if main ads count is 4
    private boolean mainAdsCount() {
        if (mainAds.size() == 4 || mainAds.size() == 2) {
            logger.info(" Main ads Count is::" + mainAds.size());
            return true;
        } else {
            logger.info(" Main ads Count is::" + mainAds.size());
            return false;
        }
    }

    //return true if response code is 200
    private boolean responseCode() {

        String url = WebDriverManager.getCurrentUrl();
        int responseCode = RESTOperations.doGET(url, null).getStatus();
        logger.info("Got response code::" + responseCode + " for link::" + url);
        if (responseCode == 200) {
            logger.info("Got correct Response code:: " + responseCode);
            return true;
        } else {
            logger.info("Response code is not 200:: " + responseCode);
            return false;
        }
    }

    @Then("^I verify that ads are clickable and should not navigate to error page for bcom$")
    public void iVerifyAdsAreClickableAndShouldNotNavigateToErrorPageForBcom() throws Throwable {
        shouldBeOnPage("home");
        Wait.untilElementPresent("home.ad_banners");
        List<WebElement> ads = Elements.findElements("home.ad_banners");
        Assert.assertFalse("ERROR - DATA: Ads are not displayed on home page", ads.isEmpty());
        if (bloomingdales() && Elements.elementPresent("home.single_ad_banner"))
            logger.info("Dummy banner is displayed in BCOM home page!!");
        else {
            long count = ads.stream().filter((ad) -> !ad.isDisplayed() ||
                    ad.getAttribute("src").isEmpty() ||
                    ad.findElement(By.xpath("..")).getAttribute("href").isEmpty()).
                    count();
            ads.get(Integer.parseInt(String.valueOf(count))).click();
            Assert.assertTrue("Invalid response code received", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
            Assert.assertEquals("ERROR - ENV: " + count + " ads are not clickable on home page", 0, count);
        }
    }

    @Then("^I navigate to featured \"([^\"]*)\" category page and verified using mobile website$")
    public void i_navigate_to_featured_category_page_and_verified_using_mobile_website(String category) throws
            Throwable {
        selectCategory(category);
    }

    public void selectCategory(String category) throws Throwable {
        Wait.untilElementPresent("home.home_featurecategories");
        Clicks.clickElementByText("home.home_featurecategories", category);
        switch (category) {
            case "Gift Cards":
                Wait.secondsUntilElementPresent("category_splash.gift_label", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Gift Card page", Wait.until(() -> Elements.getText("category_splash.gift_label").equals("Gift Cards")));
                Assert.assertEquals("ERROR - APP: " + category + " FOB link not navigated to correct page", category, Elements.getText("category_splash.gift_label"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                break;

            case "Handbags & Accessories":
                Wait.secondsUntilElementPresent("category_splash.cat_name", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Handbags page", Wait.until(() -> Elements.getText("category_splash.cat_name").equals("Handbags & Sunglasses")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_name").contains("Handbags"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page banners are missing ", Elements.elementPresent("category_splash.cat_banners"));
                break;

            case "Kids":
                Wait.secondsUntilElementPresent("category_splash.cat_name", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Kids page", Wait.until(() -> Elements.getText("category_splash.cat_name").equals("Kids & Baby")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_name").contains("Kids"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page banners are missing ", Elements.elementPresent("category_splash.cat_banners"));
                break;

            case "Plus Sizes & Petites":
                Wait.secondsUntilElementPresent("category_splash.cat_Plus_Petites", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Plus Sizes & Petites page", Wait.until(() -> Elements.getText("category_splash.cat_Plus_Petites").equals("Plus & Petite")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_Plus_Petites").contains("Plus"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + " page product list is Empty ", Elements.elementPresent("category_splash.browse_products"));
                break;

            case "Registry":
                Wait.secondsUntilElementPresent("category_splash.cat_registry", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Registry page", Wait.until(() -> Elements.getText("category_splash.cat_registry").equals("Macy's wedding registry")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_registry").contains("wedding registry"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                break;

            case "Gift Guide":
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("category_splash.cat_registry", 20);
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                break;

            case "Home":
                Wait.secondsUntilElementPresent("category_splash.cat_home", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Home page", Wait.until(() -> Elements.getText("category_splash.cat_home").equals("For The Home")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_home").contains("For The Home"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page banners are missing ", Elements.elementPresent("category_splash.cat_banners"));
                break;

            case "Juniors":
                Wait.secondsUntilElementPresent("category_splash.cat_name", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Juniors page", Wait.until(() -> Elements.getText("category_splash.cat_name").equals("Juniors & Guys")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_name").contains("Juniors & Guys"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page banners are missing ", Elements.elementPresent("category_splash.cat_banners"));
                break;

            case "Clearance":
                Wait.secondsUntilElementPresent("category_splash.cat_clearance", 20);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Clearance page", Wait.until(() -> Elements.getText("category_splash.cat_clearance").equals("Clearance & Closeout")));
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("category_splash.cat_clearance").contains("Clearance"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page products are missing ", Elements.elementPresent("category_splash.browse_products"));
                break;

            case "Active & Wellness":
                Wait.secondsUntilElementPresent("category_splash.cat_clearance", 20);
                Thread.sleep(20000);
                Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Active & Wellness page", Wait.until(() -> WebDriverManager.getCurrentUrl().contains("Active")));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page is not loaded ", Elements.elementPresent("category_splash.active_leftnav"));
                break;

            default:
                Wait.secondsUntilElementPresent("category_splash.cat_name", 10);
                Assert.assertEquals("ERROR - APP: " + category + " FOB link not navigated to correct page", category, Elements.getText("category_splash.cat_name"));
                Assert.assertTrue("Response Code is not valid", DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                Assert.assertTrue("ERROR - APP: " + category + "Page banners are missing ", Elements.elementPresent("category_splash.cat_banners"));
                break;
        }
        logger.info("Clicked on" + category + " link on home page");
    }
}


