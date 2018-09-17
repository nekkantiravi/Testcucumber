package com.macys.sdt.shared.steps.MEW;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.steps.MEW.Home;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Home extends StepUtils {
    public String searchTerm = null;

    private static final Logger logger = LoggerFactory.getLogger(Home.class);

    /**
     *  Visits the mobile web home page, handling various popups
     *  (Used when converting MEW/QAA features)
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the mobile web home page$")
    public static void iVisitTheMobileWebHomePage() throws Throwable {
        Navigate.visit("home");
        closeChatAlert();
        Clicks.clickIfPresent("home.close_app_banner");
        Clicks.clickIfPresent("home.tutorial_close");
        if (StepUtils.bloomingdales() && StepUtils.mobile()) {
            Navigate.browserRefresh();
        }

        pausePageHangWatchDog();

        Clicks.clickIfPresent("home.popup_close");  //close popup
        closeMewTutorial(); //close mew tutorial
        closeChatAlert();// closes chat alert
        Thread.sleep(5000);
    }

    /**
     * Searches for given text in the top search box
     *
     * @param value text to search for
     * @throws Throwable if any exception occurs
     */
    @When("^I search using mobile website for \"([^\"]*)\"$")
    public void I_search_using_mobile_webitefor(String value) throws Throwable {
        Assert.assertTrue("ERROR-ENV: Search text field is not visible", Wait.untilElementPresent("home.search_field"));
        TextBoxes.typeTextNEnter("home.search_field", value);
        String page = (value.contains("brands") ? "brand_index" : "search_result");
        Wait.untilElementPresent(page + ".verify_page");
        shouldBeOnPage(page);
    }

    /**
     * Type given text in the top search box
     *
     * @param autokey text to type in search box
     * @throws Throwable if any exception occurs
     */
    @When("^I type \"([^\"]*)\" in mew search box$")
    public void I_type_in_mew_search_box(String autokey) throws Throwable {
        searchTerm = autokey;
        Assert.assertTrue("ERROR-ENV: Search text field is not visible", Wait.untilElementPresent("home.search_field"));
        TextBoxes.typeTextbox("home.search_field", searchTerm);
    }

    /**
     * Selects the given text from autocomplete suggestions
     *
     * @param select_term text to select
     * @throws Throwable if any exception occurs
     */
    @Then("^I select \"([^\"]*)\" from mew autocomplete suggestions$")
    public void I_select_from_mew_autocomplete_suggestions(String select_term) throws Throwable {
        Wait.untilElementPresent("header.search_suggestions");
        if (!Elements.elementPresent("header.search_suggestions_container")) {
            I_type_in_mew_search_box(searchTerm);
        }
        Clicks.clickElementByText("header.search_suggestions", select_term);
        Wait.untilElementPresent("search_result.verify_page");
        shouldBeOnPage("search_result");
    }

    /**
     * Verifies that the given option is visible in autocomplete suggestions
     *
     * @param text expected option
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see \"([^\"]*)\" in mew autocomplete suggestions$")
    public void I_should_see_in_mew_autocomplete_suggestions(String text) throws Throwable {
        Wait.secondsUntilElementPresent("header.search_suggestions", 15);
        List<WebElement> list = Elements.findElements("header.search_suggestions");
        if (list == null || list.size() == 0) {
            Assert.fail("Auto completion has no results");
        } else {
            list.forEach(el ->
                    Assert.assertTrue("Search word not found in auto complete",
                            el.getText().toLowerCase().contains(text.toLowerCase())));
        }
    }

    /**
     * Verifies that autocomplete suggestions are not currently visible
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should not see mew autocomplete suggestions$")
    public void I_should_see_mew_autocomplete_suggestions() throws Throwable {
        if (Elements.elementPresent("header.search_suggestions")) {
            Assert.fail("Wedding registry should not have autocomplete results");
        }
    }

    @Then("^I verify all categories have text and photo icons on home page$")
    public void iVerifyAllCategoriesHaveTextAndPhotoIconsOnHomePage() throws Throwable {
        List<WebElement> categoriesList = Elements.findElements("home.categories_list");
        Assert.assertFalse("Categories not displayed on home page", categoriesList.isEmpty());
        for (WebElement category : categoriesList) {
            WebElement categoryLink = category.findElement(By.className("m-j-cm-link"));
            Assert.assertFalse("Category link not present", categoryLink.getAttribute("href").isEmpty());
            Assert.assertFalse("Category icon not present", categoryLink.findElement(By.className("m-category-img")).getAttribute("src").isEmpty());
            Assert.assertFalse("Category text not present", categoryLink.findElement(By.className("m-category-title")).getText().isEmpty());
        }
    }

    @Then("^I verify FOB links on home page are clickable and navigate to the correct pages$")
    public void iVerifyFOBLinksOnHomePageAreClickableAndNavigateToTheCorrectPages() throws Throwable {
        List<String> fobNames = Elements.findElements("home.fob_links").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertFalse("ERROR - ENV: FOB Links are not displayed on Home page", fobNames.isEmpty());
        for (String fobName : fobNames) {
            Wait.untilElementPresent("home.fob_links");
            Clicks.clickElementByText("home.fob_links", fobName);
            if (fobName.equals("DESIGNERS")) {
                Wait.secondsUntilElementPresent("brand_index.designers_label", 10);
                Assert.assertTrue("ERROR - APP: " + fobName + " FOB link not navigated to Designers page", Wait.until(() -> Elements.getText("brand_index.designers_label").equalsIgnoreCase("Designers")));
            } else {
                Wait.secondsUntilElementPresent("category_splash.category_name", 10);
                Assert.assertEquals("ERROR - APP: " + fobName + " FOB link not navigated to correct page", fobName, Elements.getText("category_splash.category_name"));
            }
            Navigate.visit("home");
        }
    }

    @Then("^I verify header and footer appear on the Home page$")
    public void iVerifyHeaderAndFooterAppearOnTheHomePage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Header doesn't appear on Home page", Elements.elementPresent("home.header_container"));
        List<String> header = Arrays.asList("global_nav_button", "header_image", "shopping_bag_icon", "search_field")
                .stream().filter(e -> !Elements.elementPresent("home." + e)).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP: Doesn't appear in header: " + header, header.isEmpty());
        Assert.assertTrue("ERROR - ENV: Footer doesn't appear on Home page", Elements.elementPresent("home.footer_container"));
        List<String> footer = Arrays.asList("goto_sign_in_link", "goto_email_text_signup", "change_country_link", "become_loyallist", "footer_bottom_links", "footer_social_links")
                .stream().filter(e -> !Elements.elementPresent("home." + e)).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP: Doesn't appear in footer: " + footer, footer.isEmpty());
    }

    @When("I click on \"([^\"]*)\" sub category under \"([^\"]*)\"$")
    public void i_Click_On_Any_Random_Sub_Category_Under(String fob, String category){
        logger.info("FOB under test is: " + fob);
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName(category);
        GlobalNav.navigateOnGnByName(fob);
    }

    @And("I navigate to any sub category that lists products$")
    public void i_Navigate_To_Any_Sub_Category_That_Lists_Products() throws Throwable{
        boolean productsListed = false;
        do{
            String randomSubCategory = GlobalNav.doContinousGlobalNavigationUntilProductsDisplayed();
            System.out.println("Random sub category selected is --->" + randomSubCategory);
            if(randomSubCategory.length() > 0)
                GlobalNav.navigateOnGnByName(randomSubCategory);
            else
                productsListed = true;
        }while(productsListed == false);
    }


    @Then("^I verify following categories are clickable on hamburger navigation:$")
    public void iVerifyFollowingCategoriesAreClickableOnHamburgerNavigation(List<String> categories) throws Throwable {
        for (String cat : categories) {
            GlobalNav.openGlobalNav();
            if (macys()){
                GlobalNav.navigateOnGnByName("Shop");
            }
            GlobalNav.navigateOnGnByName(cat);
            GlobalNav.closeGlobalNav();
            Assert.assertTrue("Not navigated to " + cat + " page", Wait.until(()->onPage("category_splash", "brand_index", "category_browse")));
            Navigate.visit("home");
        }
    }

    @Then("^I verify bestsellers products on home page using mobile website$")
    public void iVerifyBestsellersProductsOnHomePageUsingMobileWebsite() throws Throwable {
        Assert.assertTrue("ERROR - DATA: Bestsellers products are not displayed on home page", Elements.elementPresent("home.recommended_product"));
        new Actions(WebDriverManager.getWebDriver()).moveToElement(Elements.findElement("home.recommended_product")).build().perform();
        Utils.threadSleep(5000, "Let the page scroll to Bestsellers Products");
        AppiumDriver appiumDriver = WebDriverManager.getAppiumDriver();
        if (appiumDriver != null && Elements.findElements("home.recommended_product").size() > 3) {
            if (iOS()) {
                // TODO: Find a better way to calculate coordinates
                appiumDriver.swipe(250, 100, 100, 0, 2);
            } else {
                // For Android
                int elementHeight = Elements.findElement(By.id("m-product-recommended-products")).getSize().getHeight();
                String originalContext = appiumDriver.getContext();
                appiumDriver.context("NATIVE_APP");
                int windowHeight = WebDriverManager.getAndroidDriver().manage().window().getSize().getHeight();
                int elementPosition = windowHeight - (elementHeight/2);
                appiumDriver.swipe(250, elementPosition, 150, elementPosition, 2);
                appiumDriver.context(originalContext);
            }
            Assert.assertFalse("Bestsellers products carousel not working on home page", Wait.untilElementPresent("home.recommended_product"));
        }
        Assert.assertFalse("ERROR - Product got clicked instead of swipe", onPage("product_display"));
        Clicks.clickRandomElement("home.recommended_product", WebElement::isDisplayed);
        Assert.assertTrue("ERROR - ENV: Bestsellers products are not clickable.", Wait.until(()->onPage("product_display")));
    }

    @Then("^I verify ads are clickable on home page$")
    public void iVerifyAdsAreClickableOnHomePage() throws Throwable {
        shouldBeOnPage("home");
        Wait.untilElementPresent("home.ad_banners");
        List<WebElement> ads = Elements.findElements("home.ad_banners");
        Assert.assertFalse("ERROR - DATA: Ads are not displayed on home page", ads.isEmpty());
        // BCOM dummy ad banner is not clickable
        if (bloomingdales() && Elements.elementPresent("home.single_ad_banner"))
            logger.info("Dummy banner is displayed in BCOM home page!!");
        else {
            long count = ads.stream().filter((ad) -> !ad.isDisplayed() ||
                    ad.getAttribute("src").isEmpty() ||
                    ad.findElement(By.xpath("..")).getAttribute("href") == null).
                    count();
            Assert.assertEquals("ERROR - ENV: " + count + " ads are not clickable on home page", 0, count);
        }
    }

    @Then("^I verify banner images are displayed on category splash page$")
    public void iVerifyBannerImagesAreDisplayedoncategorysplashpage() throws Throwable {
        Wait.secondsUntilElementPresent("category_splash.category_banner_ads", 10);
        List<WebElement> bannerads = Elements.findElements("category_splash.category_banner_ads");
        Assert.assertFalse("Banner ads are not displayed", bannerads.isEmpty());
        for (WebElement banner : bannerads) {
            WebElement bannerLink = banner.findElement(By.className("m-j-cm-link"));
            Assert.assertFalse("Banner link not present", bannerLink.getAttribute("href").isEmpty());
            Assert.assertFalse("banner image not present", bannerLink.findElement(By.tagName("img")).getAttribute("src").isEmpty());
        }
    }

    @Then("^I select a random banner image on category splash page$")
    public void iSelectARandomBannerImageoncategorysplashpage() throws Throwable {
        shouldBeOnPage("category_splash");
        Wait.untilElementPresent("category_splash.category_banner_ads_link");
        Clicks.clickRandomElement("category_splash.category_banner_ads_link");
    }

    @And("^I open the global navigation$")
    public void i_open_the_global_navigation() throws Throwable {
        Wait.forPageReady();
        Clicks.click("main_left_nav.global_nav_button");
    }

    @And("^I navigate on menu item \"([^\"]*)\"$")
    public void i_navigate_on_menu_item(String menuItem) throws Throwable {
        Wait.forPageReady();
        GlobalNav.navigateOnGnByName(menuItem);
    }

    @Then("^I should be navigated to Macys Store page$")
    public void i_should_be_navigated_to_macys_store_page() throws Throwable {
        if (!onPage("stores")) {
            Assert.fail("User is not navigated to Stores page");
        }
    }

    @And("^I verify the below search page elements$")
    public void i_verify_the_below_search_page_elements(List<HashMap<String, String>> pageElements) throws Throwable {
        CommonUtils.verifyPageElementsWithText("stores", pageElements);
    }

    @Then("^I should see the footer elements displayed at the bottom of the page$")
    public void i_should_see_the_footer_elements_displayed_at_the_bottom_of_the_page(List<HashMap<String, String>> footerElements) throws Throwable {
        CommonUtils.verifyPageElementsWithText("footer", footerElements);
    }

    @When("^I click on \"([^\"]*)\" on \"([^\"]*)\" (page|panel)$")
    public void iClickOnLinkOnPage(String link, String page, String pageOrPanel) throws Throwable {
        if (pageOrPanel.equals("page")) {
            shouldBeOnPage(page);
        }
        Elements.elementShouldBePresent(page + "." + link);
        Clicks.click(page + "." + link);
        //replace 'click' by 'javascriptClick' as it is not working most of the time
//        Clicks.javascriptClick(page + "." + link);
    }

    @Then("^I should see following elements on \"([^\"]*)\" (page|panel):$")
    public void iShouldSeeFollowingElementsOnPage(String page, String pageOrPanel, List<String> elements) throws Throwable {
        if (onPage("wish_list_home")){
            Wait.secondsUntilElementPresentAndClick("wish_list_home.goto_list", 5);
            Wait.forPageReady();
        }
        if (pageOrPanel.equals("page")) {
            shouldBeOnPage(page);
        }
        Wait.untilElementPresent(page + "." + elements.get(0));
        List<String> failedElements = elements.stream().filter(element ->
                Arrays.stream(element.split(" or ")).noneMatch(e -> Elements.elementPresent(page + "." + e))
        ).collect(Collectors.toList());
        Assert.assertTrue("ERROR - ENV: Doesn't displayed in " + page + " " + pageOrPanel +": " + failedElements, failedElements.isEmpty());
    }

    @Then("^I should see \"([^\"]*)\" on \"([^\"]*)\" (page|panel)$")
    public void iShouldSeeElementOnPage(String element, String page, String pageOrPanel) throws Throwable {
        if (pageOrPanel.equals("page")) {
            shouldBeOnPage(page);
        }
        Elements.elementShouldBePresent(page + "." + element);
    }
    /**
     * Navigates to the home page
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I goto Mobile Home page$")
    public void i_goto_home_page() throws Throwable {
        Navigate.visit("home");
    }

    /**
     * Method to click on search go button
     */
    @And("^I click on search go button$")
    public void I_click_on_GoButton() throws Throwable {
        Wait.untilElementPresent("home.go");
        Clicks.click("home.go");
        logger.info("Verified that Search go button is clicked");
    }
}

