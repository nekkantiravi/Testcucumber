package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.utils.GlobalNavigationServices;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GlobalNavigation extends StepUtils {

    public static final Logger logger = LoggerFactory.getLogger(GlobalNavigation.class);

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

    @Then("^I verify the below \"([^\"]*)\" under registry menu from registry home page$")
    public void i_verify_the_child_categories_under_rgistry_menu(String option) throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        shouldBeOnPage("registry_gn");
        Assert.assertTrue("ERROR - ENV :: Invalid response code received while navigating to registry home page", responseCode());
        logger.info("On registry global navigation");
        List<WebElement> listOpt = Elements.findElements("registry_gn.reg_gn_child_category");
        Assert.assertTrue("ERROR - APP :: No options found on registry global navigation", !listOpt.isEmpty());
        logger.info("Options found on registry global navigation");
        List<String> listStr = new ArrayList<>();
        for (WebElement Opt : listOpt) {
            if (!Opt.getText().equals(null)) {
                listStr.add(Opt.getText());
            } else {
                Assert.fail("ERROR - APP :: " + option + " not found on registry global navigation");
            }
        }
        Assert.assertTrue("ERROR - DATA :: Required option " + option + " not found in registry gn ", listStr.contains(option));
        logger.info(option + " verification successful on registry global navigation");
    }

    @Then("^I verify following \"([^\"]*)\" from global navigation$")
    public void i_verify_following_categories_or_options_from_global_navigation(String category) throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (category.contains("Wedding Registry") || category.contains("THE REGISTRY")) {
            GlobalNav.navigateOnGnByName(category);
            logger.info("Navigating to wedding registry from global navigation");
            Wait.forPageReady();
            shouldBeOnPage("registry_home");
            Utils.threadSleep(5000,"ERROR - APP : Registry page load not succesful");
            Assert.assertTrue("ERROR - APP :: Registry home page load is not succesful", WebDriverManager.getCurrentTitle().equalsIgnoreCase("Wedding Registry & Gift Registry at Bloomingdale's"));
            logger.info("Navigated to registry home page");
        } else if (category.contains("Active & Wellness")) {
            GlobalNav.navigateOnGnByName(category);
            closePopup();
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
        } else if (category.contains("PROMOTIONS")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.untilElementPresent("home.promotions");
            logger.info("Navigating to promotions from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - APP :: Promotions Page load is not succesful",WebDriverManager.getCurrentTitle().equalsIgnoreCase("Shop Bloomingdale's | Designer Dresses, Clothes, Shoes, Handbags, Cosmetics, Home and More"));
            Assert.assertTrue("ERROR - TIMEOUT :: Unable to navigate to promotions page from GN", Elements.findElement("deals_and_promotions.select_category").isDisplayed());
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
        } else if (category.contains("MY ACCOUNT")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to my account page from global navigation");
            Thread.sleep(2000);
            Assert.assertTrue("ERROR - APP :: Unable to navigate to my account from GN", Elements.findElement("sign_in.sign_in_button").isDisplayed());
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
            logger.info("Successfully navigated to my account from GN");
            Wait.untilElementPresent("my_account.back_to_home");
            Clicks.click(Elements.findElement("my_account.back_to_home"));
            logger.info("Navigating back to home page from global navigation");
            shouldBeOnPage("home");
            logger.info("Successfully navigated to home page from my account overlay");
        } else if (category.contains("MY bWALLET") || category.contains("Wallet")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to my wallet page from global navigation");
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
            String url = WebDriverManager.getCurrentUrl();
            shouldBeOnPage("sign_in");
            Assert.assertTrue("ERROR - APP :: Wallet Page load is not succesful",WebDriverManager.getCurrentTitle().equalsIgnoreCase("Sign In - My Account - Bloomingdale's"));
            logger.info("Successfully navigated to sign in page for my wallet");
        } else if (category.contains("MY LOYALLIST")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
            Wait.untilElementPresent("home.gn_become_loyallist");
            Clicks.click(Elements.findElement("home.gn_become_loyallist"));
            logger.info("Navigating to my loyalist page from global navigation");
            Wait.forPageReady();
            String url = WebDriverManager.getCurrentUrl();
            shouldBeOnPage("loyalty_home");
            Assert.assertTrue("ERROR - APP :: Wallet Page load is not succesful",WebDriverManager.getCurrentTitle().equalsIgnoreCase("Sign In - Bloomingdales"));
            logger.info("Successfully navigated to loyalty sign in page for become a loyalist");
        } else if (category.contains("WISH LIST")) {
            GlobalNav.navigateOnGnByName(category);
            logger.info("Navigating wishlist from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
            String url = WebDriverManager.getCurrentUrl();
            Assert.assertTrue("ERROR - APP :: Wishlist Page load is not succesful",WebDriverManager.getCurrentTitle().equalsIgnoreCase("Shop Bloomingdale's | Designer Dresses, Clothes, Shoes, Handbags, Cosmetics, Home and More"));
            Assert.assertTrue("ERROR - APP :: Did not navigate to wish list page from GN", url.contains("wishlist/mylist?"));
            Assert.assertTrue("ERROR - APP :: Unable to navigate to wish list page from GN", Elements.findElement("wish_list.wishlist_title").isDisplayed());
            logger.info("Successfully navigated to wish list from GN");
        } else if (category.contains("RECENT ACTIVITY")) {
            GlobalNav.navigateOnGnByName(category);
            logger.info("Navigating recent activity from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
            String url = WebDriverManager.getCurrentUrl();
            Assert.assertTrue("ERROR - APP :: Recent activity Page load is not succesful",WebDriverManager.getCurrentTitle().equalsIgnoreCase("Recent Activity - Shop Bloomingdale's"));
            Assert.assertTrue("ERROR - APP :: Did not navigate to recent activity page", url.contains("/shop/recent-activity?"));
            Assert.assertTrue("ERROR - APP :: Unable to navigate to wish list page from GN", Elements.findElement("recently_viewed.recently_viewed_label").isDisplayed());
            logger.info("Successfully navigated to recent activity page from GN");
        } else {
            GlobalNav.navigateOnGnByName(category);
            logger.info("Navigating to " + category + " from main menu from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
            GlobalNav.closeGlobalNav();
            logger.info("Global Navigation closed");
        }
    }

    @Then("^I verify child categories of below parent \"([^\"]*)\" are visible$")
    public void i_verify_child_categories_of_below_parent_categories_are_visible(String category) throws Throwable {
        if (category.equals("Add Gifts to Registry")) {
            Elements.findElement("registry_gn.gift_categories");
            Clicks.click("registry_gn.gift_categories");
        }
        List<WebElement> childCategories = Elements.findElements("home.gn_child_categories");
        logger.info("Fetching child categores from current category " + category);
        Thread.sleep(2000);
        Assert.assertTrue("ERROR - APP :: " + category + " category has no child categories", !childCategories.isEmpty());
        logger.info("Child categories found for " + category);
        GlobalNav.closeGlobalNav();
    }

    @Then("^I verify the following child categories (for Registry )?are not visible:$")
    public void iVerifyTheFollowingChildCategoriesAreNotVisible(String mode, List<String> shouldNotBeVisible) {
        String childCategoriesSelector = "";
        if (mode == null) {
            childCategoriesSelector = "home.gn_child_categories";
        } else if (mode.trim().equalsIgnoreCase("for Registry")) {
            childCategoriesSelector = "registry_gn.reg_gn_child_category";
        }
        GlobalNav.openGlobalNav();
        Wait.untilElementPresent(childCategoriesSelector);
        List<WebElement> childCategories = Elements.findElements(childCategoriesSelector);
        for (WebElement childCategory : childCategories) {
            if (shouldNotBeVisible.contains(childCategory.getText())) {
                Assert.fail("ERROR: \"" + childCategory.getText() + "\" was visible in the Global Nav");
            }
        }
        logger.info("Verified the child categories are not visible");
        GlobalNav.closeGlobalNav();
    }

    @Then("^I verify the page navigation to cat splash pages for \"([^\"]*)\"$")
    public void i_verify_page_navigation_to_cat_splsh_pages(String category) throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        GlobalNav.navigateOnGnByName(category);
        logger.info("Navigating to " + category + " category from main menu from global navigation");
        Wait.forPageReady();
        closeAlert();
        Wait.untilElementPresent("category_splash.close_global_navigation");
        Clicks.click(Elements.findElement("category_splash.close_global_navigation"));
        if (category.equalsIgnoreCase("DESIGNERS")) {
            shouldBeOnPage("brand_index");
            Assert.assertTrue("ERROR - APP :: brand index page load is not succesful", WebDriverManager.getCurrentTitle().equalsIgnoreCase("Shop Bloomingdale's | Designer Dresses, Clothes, Shoes, Handbags, Cosmetics, Home and More"));
            logger.info("Successfully navigated to " + category);
        } else {
            shouldBeOnPage("category_splash");
            logger.info("Global navigation closed for " + category);
            Assert.assertTrue("ERROR - APP :: Cat splash page load failed OR Navigated to wrong category", Elements.findElement("category_splash.category_name").getText().equalsIgnoreCase(category));
            Assert.assertTrue("ERROR - APP :: Cat splash page load is not succesful", WebDriverManager.getCurrentTitle().equalsIgnoreCase("Designer " + category + " | Bloomingdales's"));
            if (category.equalsIgnoreCase("EDITORIAL") || category.equalsIgnoreCase("GIFTS")) {
                List<WebElement> banners = Elements.findElements("category_splash.banner_images");
                for (WebElement banner : banners) {
                    Assert.assertTrue("ERROR - DATA :: No banner items found on " + category + " splash page", banner.isDisplayed());
                    logger.info("Banners are visible for " + category);
                }
            } else {
                List<WebElement> splashItems = Elements.findElements("category_splash.splash_items_container");
                Assert.assertTrue("No splash items found on " + category + " splash page", !splashItems.isEmpty());
                for (WebElement splashItem : splashItems) {
                    Assert.assertTrue("ERROR - DATA :: No banner items found on " + category + " splash page", splashItem.isDisplayed());
                    logger.info("Splash items are visible for " + category);
                }
            }
        }
    }

    @Then("^I also verify the UI data with service response data for \"([^\"]*)\" in \"([^\"]*)\" global navigation$")
    public void i_verify_the_ui_data_with_service_call_data(String category, String mode) throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        GlobalNav.navigateOnGnByName(category);
        logger.info("Navigating to " + category + " category from main menu from global navigation");
        String categoryId = Elements.findElement("registry_gn.reg_gn_current_category").getAttribute("id").replace("0000", "");
        List<Integer> productIdsFromService = GlobalNavigationServices.getCategoryIds(mode, categoryId);
        if (productIdsFromService.isEmpty() && responseCode()) {
            logger.info("No categories returned from service");
            Wait.untilElementPresent("registry_gn.reg_gn_current_category");
            Clicks.click("registry_gn.reg_gn_current_category");
            Wait.forPageReady();
            shouldBeOnPage("reg_browse");
        } else {
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
        }
        List<WebElement> productsFromGNUI = Elements.findElements("registry_gn.reg_gn_child_category");
        Assert.assertTrue("Did not find any sub categories under " + category, !productsFromGNUI.isEmpty());
        for (WebElement productidUI : productsFromGNUI) {
            String catId = productidUI.getAttribute("id").replace("0000", "");
            Assert.assertTrue("ERROR - APP :: Comparision failed: " + catId + " category not found under " + category, productIdsFromService.contains(Integer.parseInt(catId)));
        }
        logger.info("GN service to UI comparison is successful for all categories" + category);
    }

    @Then("^I randomly navigate to any registry browse page by clicking on child categories of below \"([^\"]*)\"$")
    public void i_randomly_navigate_to_any_registry_browse_page_from_child_categories(String category) throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        closeAlert();
        Assert.assertTrue("ERROR - DATA :: " + category + " not visible under registry gn", Elements.findElement("registry_gn.reg_gn_current_category").isDisplayed());
        Assert.assertTrue("ERROR - DATA :: Diffrent category found than expected " + category, Elements.findElement("registry_gn.reg_gn_current_category").getText().equals(category));
        logger.info("Expected option " + category + " seen in regstry gn");
        while (Elements.findElement("home.main_navigation_button").getAttribute("tabindex").equalsIgnoreCase("0")) {
            List<WebElement> childCategories = Elements.findElements("registry_gn.reg_gn_child_category");
            logger.info("Fetching child categories from current category " + category);
            if (childCategories.isEmpty() && Elements.findElement("registry_gn.reg_gn_current_category").isDisplayed()) {
                logger.info("No child categories found under " + category + "-->" + Elements.findElement("registry_gn.reg_gn_current_category").getText());
                Wait.untilElementPresent("registry_gn.reg_gn_current_category");
                Clicks.click("registry_gn.reg_gn_current_category");
            } else if (!childCategories.isEmpty() && Elements.findElement("registry_gn.reg_gn_current_category").isDisplayed()) {
                logger.info("Child categories found under " + category + "-->" + Elements.findElement("registry_gn.reg_gn_current_category").getText());
                Wait.untilElementPresent("registry_gn.reg_gn_child_category");
                Clicks.clickRandomElement("registry_gn.reg_gn_child_category");
            }
        }
        if (Elements.findElement("registry_gn.reg_gn_current_category").getText().equals("All Brands")) {
            shouldBeOnPage("brand_index");
            Assert.assertTrue("ERROR - APP :: brand index page load is not succesful", WebDriverManager.getCurrentTitle().equalsIgnoreCase("Shop Bloomingdale's | Designer Dresses, Clothes, Shoes, Handbags, Cosmetics, Home and More"));
            logger.info("On brand index page");
            Assert.assertTrue("ERROR - DATA :: Child categories are visible for brands in registry gn", Elements.findElement("registry_gn.reg_gn_child_category").isDisplayed());
            GlobalNav.closeGlobalNav();
            Assert.assertTrue("ERROR - DATA :: Brand links are not visible on registry brand index page", Elements.findElement("brand_index.brand_links").isDisplayed());
        } else {
            shouldBeOnPage("reg_browse");
            logger.info("Successfully navigated to registry browse page from random child category " + category);
            Assert.assertTrue("ERROR - APP :: Sort By option not visible", Elements.findElement("category_browse.sort_by_select").isDisplayed());
            Assert.assertTrue("ERROR - APP :: Breadbrumb wrapper not visible", Elements.findElement("category_browse.bread_crumb_wrapper").isDisplayed());
            Assert.assertTrue("ERROR - APP :: Facet accordion not visible", Elements.findElement("category_browse.facet_accordion_panel").isDisplayed());
            Assert.assertTrue("ERROR - DATA :: Products are not visible", Elements.findElement("category_browse.product_list").isDisplayed());
            logger.info("Browse page loaded for " + category + " category");
        }
    }

    @Then("^I randomly navigate to any browse page by clicking on child categories of below \"([^\"]*)\"$")
    public void i_randomly_navigate_to_any_browse_page_from_child_categories(String category) throws Throwable {
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName(category);
        logger.info("Navigating to " + category + " category from main menu from global navigation");
        Wait.forPageReady();
        GlobalNav.openGlobalNav();
        while (Elements.findElement("home.main_navigation_button").getAttribute("tabindex").equalsIgnoreCase("0")) {
            List<WebElement> childCategories = Elements.findElements("home.gn_child_categories");
            logger.info("Fetching child categores from current category " + category);
            if (childCategories.isEmpty() && Elements.findElement("home.gn_current_category").isDisplayed()) {
                logger.info("No child categories found under " + category + "-->" + Elements.findElement("home.gn_current_category").getText());
                Wait.untilElementPresent("home.gn_current_category");
                Clicks.click("home.gn_current_category");
            } else if (!childCategories.isEmpty() && Elements.findElement("home.gn_current_category").isDisplayed()) {
                logger.info("Child categories found under " + category + "-->" + Elements.findElement("home.gn_current_category").getText());
                Wait.untilElementPresent("home.gn_child_categories");
                Clicks.clickRandomElement("home.gn_child_categories");
            }
        }
        shouldBeOnPage("category_browse");
        logger.info("Successfully navigated to browse page from random child category from " + category);
        Assert.assertTrue("ERROR - APP :: Sort By option not visible", Elements.findElement("category_browse.sort_by_select").isDisplayed());
        Assert.assertTrue("ERROR - APP :: Breadbrumb wrapper not visible", Elements.findElement("category_browse.bread_crumb_wrapper").isDisplayed());
        Assert.assertTrue("ERROR - APP :: Facet accordion not visible", Elements.findElement("category_browse.facet_accordion_panel").isDisplayed());
        Assert.assertTrue("ERROR - DATA :: Products are not visible", Elements.findElement("category_browse.product_list").isDisplayed());
        logger.info("Browse page loaded for " + category + " category");
    }

    @Then("^I verify the catid or product id falls under right GN tree for \"([^\"]*)\"$")
    public void i_verify_catid_or_prod_id_falls_under_right_gn_tree(String category) throws Throwable {
        Browse browse = new Browse();
        String catId = browse.getCategoryId();
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        String catIDGN = Elements.findElement("home.gn_current_category").getAttribute("id");
        String userProvidedCat = category.split("id=")[1];
        if ((userProvidedCat.equals(catId) && userProvidedCat.equals(catIDGN))) {
            logger.info("Deeplinking successful");
        } else if (!userProvidedCat.equals(catId) && !userProvidedCat.equals(catIDGN) && Elements.findElement("category_browse.sort_by_select").isDisplayed()) {
            logger.info(userProvidedCat + " Navigated to diffrent category " + catId);
        } else {
            Assert.fail("ERROR - APP :: Deepinking is not successful for category " + userProvidedCat);
        }
    }

    @Then("^I verify child categories of below parent \"([^\"]*)\" from backend service call( in registry mode)?$")
    public void i_verify_child_categories_of_below_parent_categories_from_backend_service_call(String category,String mode) throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (macys() && mode == null) {
            GlobalNav.navigateOnGnByName("Shop");
        } else if (macys() && mode.contains("registry")) {
            GlobalNav.navigateOnGnByName("Gift Categories");
        }
        GlobalNav.navigateOnGnByName(category);
        Wait.secondsUntilElementPresent("home.gn_current_category",10);
        String categoryId = Elements.findElement("home.gn_current_category").getAttribute("id");
        List<Integer> productIdsFromService = GlobalNavigationServices.getCategoryIds(mode, categoryId);
        if (productIdsFromService.isEmpty() && responseCode()) {
            logger.info("No categories returned from service");
            Wait.untilElementPresent("home.gn_current_category");
            Clicks.click("home.gn_current_category");
            Wait.forPageReady();
            shouldBeOnPage("category_browse");
        } else {
            Assert.assertTrue("ERROR - ENV :: Invalid response code received", responseCode());
        }
        List<Integer> productsFromGNUI = Elements.findElements("home.gn_child_categories").stream().map(ele -> Integer.parseInt(ele.getAttribute("id"))).collect(Collectors.toList());
        Assert.assertTrue("Did not find any sub categories under " + category, !productsFromGNUI.isEmpty());
        Assert.assertTrue("ERROR - APP: Sub categories are mismatched when compared to service for " + category, productIdsFromService.equals(productsFromGNUI));
        logger.info("GN service to UI comparison is successful for all categories");
    }

    @When("^I navigate to random mobile (category browse|category splash|pdp member|pdp master|search result|dynamic landing) page$")
    public void navigateToAssignedPage(String page) throws Throwable{
        Home home = new Home();
        switch (page){
            case "search result": {
                home.I_search_using_mobile_webitefor("jeans");
                break;
            }
            case "category splash": {
                navigateToRandomCatSplash();
                break;
            }
            case "category browse": {
                navigateToRandomBrowse();
                break;
            }
            case "dynamic landing": {
                navigateToRandomDLP();
                break;
            }
        }


    }


    private WebElement selectRandomMenuItem(){
        List<WebElement> list = Elements.findElements("main_left_nav.nav_menu_list_items");
        Random randomizer = new Random();
        int randInt = randomizer.nextInt( 8 );
        if(randInt==0 || randInt==1){
            randInt++;
            return list.get(randInt);
        }
        return list.get(randInt);
    }

    private WebElement selectRandomPopularRelatedSearchItem(){
        List<WebElement> list = Elements.findElements("category_splash.popular_related_searches");
        Random randomizer = new Random();
        int randInt = randomizer.nextInt( 4 );
        if(randInt==0){
            randInt++;
        }
        return list.get(randInt);
    }

    private void navigateToRandomCatSplash(){
        GlobalNav.openGlobalNav();
        selectRandomMenuItem().click();
        GlobalNav.closeGlobalNav();
        if(!onPage("category_splash")){
            logger.info("Current URL is: "+ MainRunner.currentURL);
            GlobalNav.openGlobalNav();
            Clicks.click("main_left_nav.global_nav_top_menu_item");
            GlobalNav.closeGlobalNav();
            GlobalNav.openGlobalNav();
            selectRandomMenuItem().click();
            GlobalNav.closeGlobalNav();
        }
        logger.info("Current URL is: "+ MainRunner.currentURL);
    }

    private void navigateToRandomDLP(){
        navigateToRandomCatSplash();
        Navigate.scrollPage(2000,5);
        if(!Elements.elementPresent("category_splash.tag_cloud")){
            logger.info("Current URL is: "+ MainRunner.currentURL);
            GlobalNav.openGlobalNav();
            Clicks.click("main_left_nav.global_nav_top_menu_item");
            GlobalNav.closeGlobalNav();
            navigateToRandomDLP();
        }
        logger.info("Current URL: " + MainRunner.currentURL);
        selectRandomPopularRelatedSearchItem().click();
        logger.info("Current URL: " + MainRunner.currentURL);
    }

    private void navigateToRandomBrowse(){
        navigateToRandomCatSplash();
        GlobalNav.openGlobalNav();
        selectRandomMenuItem().click();
        GlobalNav.closeGlobalNav();
        if(!onPage("category_browse")){
            logger.info("Current URL is: "+ MainRunner.currentURL);
            GlobalNav.openGlobalNav();
            Clicks.click("main_left_nav.global_nav_top_menu_item");
            GlobalNav.closeGlobalNav();
        }
        logger.info("Current URL is: "+ MainRunner.currentURL);
    }

}
