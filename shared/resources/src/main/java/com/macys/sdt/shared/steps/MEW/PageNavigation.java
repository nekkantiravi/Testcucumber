package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import com.macys.sdt.shared.actions.MEW.pages.ProductDisplay;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PageNavigation extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PageNavigation.class);
    public static String runMode;
    public static String category;

    /**
     * Verifies the browser is on the search results page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be in Search Landing page using mobile website$")
    public void I_should_be_in_Search_Landing_page_using_mobile_website() throws Throwable {
        Navigate.browserRefresh();
        shouldBeOnPage("search_result");
    }

    /**
     * Verifies that the browser is on the given page
     *
     * @param page name of expected page
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see the \"([^\"]*)\" Page$")
    public void I_should_see_the_page(String page) throws Throwable {
        shouldBeOnPage(page);
    }

    /**
     * Verifies the browser is on the PDP
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to PDP page using mobile website$")
    public void I_should_be_redirected_to_PDP_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("product_display");
    }

    /**
     * Verifies that the current url contains "/shop/product/?ID"
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to wishlist PDP using mobile website$")
    public void I_should_be_redirected_to_wishlist_PDP_using_mobile_website() throws Throwable {
        Assert.assertTrue("Not on MEW wishlist PDP", url().contains("/shop/product/?ID"));
    }

    /**
     * Verifies the browser is on the add to bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to ATB page using mobile website$")
    public void I_should_be_redirected_to_ATB_page_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        shouldBeOnPage("add_to_bag", "shopping_bag");
    }

    /**
     * Selects "continue checkout" on add to bag page and then verifies the browser is on the shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to shopping bag page from add to bag page using mobile website$")
    public void I_navigate_to_shopping_bag_page_from_add_to_bag_page_using_mobile_website() throws Throwable {
        Assert.assertTrue("ERROR-ENV: Checkout button is not visible in add to bag panel", Elements.elementPresent("add_to_bag.checkout"));
        Clicks.click("add_to_bag.checkout");
        shouldBeOnPage("shopping_bag");
    }

    /**
     * Selects the given menu items from global navigation menu, in the given sequence
     *
     * @param table list of menu items to select
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate the global navigation menu as follows:$")
    public void I_navigate_the_global_navigation_menu_as_follows(List<String> table) throws Throwable {
        if (onPage("registry_home", "registry_manager")) {
            GlobalNav.openGlobalNav();
            table.forEach(GlobalNav::navigateOnGnByName);
        } else {
            table.forEach(gnName -> {
                GlobalNav.openGlobalNav();
                GlobalNav.navigateOnGnByName(gnName);
                closePopup();
            });
        }
        GlobalNav.closeGlobalNav();
        CommonUtils.closeStylistPopup();
    }

    /**
     * Selects "back" button on change_pickup_store and then verifies the browser is on the PDP
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate back to pdp page from pick up store page$")
    public void I_navigate_back_to_pdp_page_from_pick_up_store_page() throws Throwable {
        Wait.untilElementPresent("change_pickup_store.back_button");
        Clicks.click("change_pickup_store.back_button");
        Assert.assertTrue("ERROR-ENV: Not navigated to the PDP", onPage("product_display"));
    }

    /**
     * Verifies the browser is on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be in mobile shopping bag$")
    public void I_should_be_in_mobile_shopping_bag() throws Throwable {
        shouldBeOnPage("shopping_bag");
    }

    /**
     * Navigates to the sign-in page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the sign-in page$")
    public void I_navigate_to_the_sign_in_page() throws Throwable {
        scrollToLazyLoadElement("home.goto_sign_in_link");
        Clicks.clickWhenPresent("home.goto_sign_in_link");
        Assert.assertTrue("ERROR-ENV: Not able to navigate to the sign_in page", Wait.untilElementPresent("sign_in.verify_page"));
        // shouldBeOnPage("sign_in");
    }

    /**
     * Navigates to the create profile page from sign-in page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to the create profile page$")
    public void I_navigate_to_the_create_profile_page() throws Throwable {
        GlobalNav.closeGlobalNav();
        Wait.forPageReady();
        Assert.assertTrue("ERROR - ENV : Create Account element is not visible", Wait.secondsUntilElementPresent("sign_in.create_account", 10));
        Clicks.javascriptClick("sign_in.create_account");
        shouldBeOnPage("create_profile");
    }

    /**
     * Selects a recently viewed product
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select a recently viewed product using mobile website$")
    public void I_select_a_recently_viewed_product_using_mobile_website() throws Throwable {
        GlobalNav.navigateToRecentlyViewedProduct();
    }

    /**
     * Verifies the browser is on master PDP
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to master PDP page in mobile website$")
    public void I_should_be_redirected_to_master_PDP_page_in_mobile_website() throws Throwable {
        shouldBeOnPage("product_display_master");
        Assert.assertTrue("ERROR-ENV: Unable to navigate product display page", ProductDisplay.isMasterMemberPage());
    }

    /**
     * Navigates to create registry page from registry home page or from global nav
     *
     * @param mode "create your registry" or "create"
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" from mobile registry home page$")
    public void I_select_from_mobile_registry_home_page(String mode) throws Throwable {
        GlobalNav.closeGlobalNav();
        if (onPage("registry_home")) {
            switch (mode) {
                case "create your registry":
                case "create":
                    Clicks.javascriptClick(Elements.element("registry_home.goto_create_registry"));
                    break;


            }
        }
    }

    /**
     * Verifies the browser is on my account page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to the mobile my account page$")
    public void I_should_be_navigated_to_the_mobile_my_account_page() throws Throwable {
        shouldBeOnPage("my_account");
    }

    /**
     * Navigates back to the given page
     *
     * @param page_type home, my account, category browse or category splash
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate back to \"([^\"]*)\" page using mobile website$")
    public void I_navigate_back_to_page_using_mobile_website(String page_type) throws Throwable {
        switch (page_type.toLowerCase()) {
            case "home":
                Navigate.visit("home");
                break;
            case "my account":
                Navigate.visit("my_account");
                break;
            case "category browse":
                Navigate.browserBack();
                // Assert.assertTrue("ERROR-ENV: Not able to navigate to the " + page_type + ".", onPage("category_browse"));
                break;
            case "category splash":
                Navigate.browserBack();
                //   Assert.assertTrue("ERROR-ENV: Not able to navigate to the " + page_type + ".", onPage("category_splash"));
                break;
        }
    }

    @And("^I close the global navigation menu$")
    public void I_close_the_global_navigation_menu() throws Throwable {
        GlobalNav.closeGlobalNav();
    }

    /**
     * Navigates to category browse page from category splash page. Step is only for "women's Heel"
     *
     * @param category_type women's Heel
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to \"([^\"]*)\" category browse page using mobile website$")
    public void I_navigate_to_category_browse_page_using_mobile_website(String category_type) throws Throwable {
        switch (category_type) {
            case "women's Heel":
                Clicks.clickWhenPresent("category_splash.banner_link");
        }
    }

    /**
     * Navigates back to home page from any page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate back to home page using mobile website$")
    public void I_navigate_back_to_home_page_using_mobile_website() throws Throwable {
        if (Elements.elementPresent("home.back")) {
            Clicks.click("home.back");
            shouldBeOnPage("home");
        } else {
            Navigate.visit("home");
        }
        logger.info("Successfully navigated to the Home Page");
    }

    /**
     * Selects the given link from the footer
     *
     * @param link_text "customer service", "emails or texts" or "find a store"
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to \"([^\"]*)\" footer links using mobile website$")
    public void I_navigate_to_footer_links_using_mobile_website(String link_text) throws Throwable {
        if (Elements.elementPresent((Elements.element("home.footer")))) {
            switch (link_text) {
                case "customer service":
                    Clicks.javascriptClick(Elements.element("home.goto_contact_us"));
                    Navigate.switchWindow(1);
                    Navigate.switchWindowClose();
                    break;
                case "emails or texts":
                    Clicks.click(Elements.element("home.goto_email_text_signup"));
                    break;
                case "find a store":
                    Clicks.javascriptClick(Elements.element("home.find_a_store"));
                    break;
            }
        } else {
            Assert.fail("Footer is not visible");
        }
    }

    /**
     * Visits the home page, closes all popups that appear, and sign in or create account if specified type is registered
     *
     * @param userType user type "guest" or "registered"
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the mobile web site as a (guest|registered) user$")
    public void I_visit_the_mobile_web_site_as_a_registered_user(String userType) throws Throwable {
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

        if (userType.equals("registered")) {
            CommonUtils.signInOrCreateAccount();
            if (!prodEnv()) {
                HashMap<String, String> options = new HashMap<>();
                options.put("checkout_eligible", "true");
                ProfileAddress profileAddress = new ProfileAddress();
                TestUsers.getRandomValidAddress(options, profileAddress);
                TestUsers.getCustomer(null).getUser().setProfileAddress(profileAddress);
                new MyAccount().I_add_a_credit_card_to_my_wallet_if_not_present_using_mobile_website();
            }
            if (safari()) {
                new com.macys.sdt.shared.steps.website.ShopAndBrowse().i_remove_all_items_from_the_shopping_bag();
            }
            Navigate.visit("home");
        }
        Cookies.disableForeseeSurvey();
    }

    /**
     * Verifies the browser is on deals and promotions page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to deals page using mobile website$")
    public void I_should_be_redirected_to_deals_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("deals_and_promotions");
    }

    /**
     * Navigates to the change country page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to change country page using mobile website$")
    public void I_navigate_to_change_country_page_using_mobile_website() throws Throwable {
        if (Elements.elementPresent(Elements.element("home.change_country_link"))) {
            Clicks.javascriptClick(Elements.element("home.change_country_link"));
            Wait.untilElementPresent(Elements.element("change_country.header"));
        } else {
            Assert.fail("Change country link is not visible");
        }
    }

    /**
     * Navigates to the shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to shopping bag page using mobile website$")
    public void I_navigate_to_shopping_bag_page_using_mobile_website() throws Throwable {
        try {
            Wait.secondsUntilElementPresent("header.my_bag", 15);
            Clicks.javascriptClick("header.my_bag");
        } catch (NoSuchElementException e) {
            logger.info(e.getMessage());
            Assert.fail("Element is not visible on page");
        }
    }

    /**
     * Visits the web site then goes to the specified mode
     *
     * @param mode domestic or registry or iship
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the mobile web site as a guest user in (domestic|iship|registry) mode$")
    public void I_visit_the_mobile_web_site_as_a_guest_user_in_mode(String mode) throws Throwable {
        runMode = mode;
        I_visit_the_mobile_web_site_as_a_registered_user("guest");
        switch (mode.toLowerCase()) {
            case "domestic":
                break;
            case "registry":
                I_navigate_the_global_navigation_menu_as_follows(Collections.singletonList("The Registry or Wedding Registry"));
                shouldBeOnPage("registry_home");
                break;
            case "iship":
                I_navigate_to_change_country_page_using_mobile_website();
                Iship iship = new Iship();
                iship.I_change_country_to_using_mobile_website("India");
                iship.I_close_the_welcome_mat_if_it_s_visible_using_mobile_website();
                Clicks.clickIfPresent("home.close_app_banner");
                break;
        }
    }

    /**
     * Verifies that the pagination functionality is working if pagination is present
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be able to navigate using pagination functionality using mobile website$")
    public void I_should_be_able_to_navigate_using_pagination_functionality_using_mobile_website() throws Throwable {
        if (macys()) {
            Browse browse = new Browse();
            int currentPageNumber = browse.getCurrentPageNumber();
            if (currentPageNumber != 0) {
                Clicks.click("pagination.goto_next_page");
                Wait.forLoading("pagination.loading_mask");
                Wait.secondsUntilElementPresent("pagination.goto_previous_page", 10);
                Assert.assertEquals("Pagination functionality is not working properly!!", browse.getCurrentPageNumber(), (currentPageNumber + 1));
                currentPageNumber = browse.getCurrentPageNumber();
                Clicks.click("pagination.goto_previous_page");
                Wait.forLoading("pagination.loading_mask");
                Wait.secondsUntilElementPresent("pagination.goto_next_page", 10);
                Assert.assertEquals("Pagination functionality is not working properly!!", browse.getCurrentPageNumber(), (currentPageNumber - 1));
            }
        } else {
            if (Wait.secondsUntilElementPresent("pagination.goto_current_page_number", 5)) {
                int currentPageNumber = Integer.parseInt(Elements.getText("pagination.goto_current_page_number").trim());
                if (currentPageNumber != 0) {
                    Clicks.click("pagination.goto_next_page_via_arrow");
                    Wait.forLoading("pagination.loading_mask");
                    Assert.assertEquals("Pagination functionality is not working properly!!", Integer.parseInt(Elements.getText("pagination.goto_current_page_number").trim()), (currentPageNumber + 1));
                    currentPageNumber = Integer.parseInt(Elements.getText("pagination.goto_current_page_number").trim());
                    Clicks.click("pagination.goto_previous_page_via_arrow");
                    Wait.forLoading("pagination.loading_mask");
                    Assert.assertEquals("Pagination functionality is not working properly!!", Integer.parseInt(Elements.getText("pagination.goto_current_page_number").trim()), (currentPageNumber - 1));
                }
            } else {
                if (Wait.untilElementPresent("pagination.select_page_no")) {
                    String selectedValue = DropDowns.getSelectedValue(Elements.element("pagination.select_page_no"));
                    Assert.assertNotNull("Not able to get page count", selectedValue);
                    String pageCount = selectedValue.replace("page 1 of ", "");
                    scrollToLazyLoadElement("pagination.next_page");
                    Clicks.click("pagination.next_page");
                    Wait.forLoading("pagination.loading_mask");
                    Assert.assertEquals("Not navigated to next page.", "page 2 of " + pageCount, DropDowns.getSelectedValue(Elements.element("pagination.select_page_no")));
                    scrollToLazyLoadElement("pagination.previous_page");
                    Clicks.click("pagination.previous_page");
                    Wait.forLoading("pagination.loading_mask");
                    Assert.assertEquals("Not navigated to previous page.", "page 1 of " + pageCount, DropDowns.getSelectedValue(Elements.element("pagination.select_page_no")));
                }
            }
        }
    }

    /**
     * Navigates to brand index page in the specified mode
     *
     * @param mode registry or iship or domestic
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to brand index page in (registry|iship|domestic) mode using mobile website$")
    public void I_navigate_to_brand_index_page_using_mobile_website(String mode) throws Throwable {
        switch (mode) {
            case "registry":
                I_navigate_the_global_navigation_menu_as_follows(bloomingdales() ? Arrays.asList("Add Gifts to Registry", "All BRANDS") :
                        Arrays.asList("SHOP", "BRANDS"));
                break;
            case "domestic":
                I_navigate_the_global_navigation_menu_as_follows(bloomingdales() ? Arrays.asList("ALL DESIGNERS") :
                        Arrays.asList("Shop","Beauty", "Beauty Brands", "All Beauty Brands"));
                break;
            case "iship":
                I_navigate_the_global_navigation_menu_as_follows(bloomingdales() ? Arrays.asList("ALL DESIGNERS") :
                        Arrays.asList("Shop","For The Home", "See All Brands"));
        }
        shouldBeOnPage("brand_index");
    }

    /**
     * Navigates to random dynamic landing page in the specified mode
     *
     * @param mode registry or iship or domestic
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to dynamic landing page in (registry|iship|domestic) mode using mobile website$")
    public void I_navigate_to_dynamic_landing_page_using_mobile_website(String mode) throws Throwable {
        I_navigate_to_brand_index_page_using_mobile_website(mode);
        for (int i = 0; i < 5; i++) {
            Clicks.randomJavascriptClick("brand_index.brand_links");
            if (Wait.until(() -> onPage("dynamic_landing"))) {
                break;
            }
            Navigate.browserBack();
            shouldBeOnPage("brand_index");
        }
        shouldBeOnPage("dynamic_landing");
    }

    /**
     * Verifies the browser is on brand index page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to designer page using mobile website$")
    public void I_should_be_redirected_to_designer_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("brand_index");
    }

    /**
     * Selects given category from brand index page
     *
     * @param categoryType target category name
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to \"([^\"]*)\" designer category browse page using mobile website$")
    public void I_navigate_to_designer_category_browse_page_using_mobile_website(String categoryType) throws Throwable {
        shouldBeOnPage("brand_index");
        List<WebElement> list = Elements.findElements("brand_index.brand_links", el -> el.isDisplayed() && el.getText().equalsIgnoreCase(categoryType));
        Elements.elementInView(list.get(0));
        Clicks.click(list.get(0));
    }

    @And("^I navigate to \"([^\"]*)\" page by url \"([^\"]*)\"$")
    public void I_navigate_to_browse_page_by_url(String mode, String url) throws Throwable {
        Navigate.visit(RunConfig.url + url);
        Wait.forPageReady();
        if (mode.equals("browse")) {
            shouldBeOnPage("category_browse");
        } else {
            shouldBeOnPage("reg_browse");
        }
        logger.info("Successfully navigated to browse page using url");
        Assert.assertTrue("ERROR - APP :: Sort By option not visible", Elements.findElement("category_browse.sort_by_select").isDisplayed());
        Assert.assertTrue("ERROR - APP :: Breadbrumb wrapper not visible", Elements.findElement("category_browse.bread_crumb_wrapper").isDisplayed());
        Assert.assertTrue("ERROR - APP :: Facet accordion not visible", Elements.findElement("category_browse.facet_accordion_panel").isDisplayed());
        Assert.assertTrue("ERROR - DATA :: Products are not visible", Elements.findElement("category_browse.product_list").isDisplayed());
        logger.info("Browse page loaded successfully");
    }

    @When("^I navigate to splash page with the url$")
    public void navigateDirectlyToSplashPage() {
        String strSplash = macys() ? "/shop/womens-clothing?id=118" : "/shop/womens-apparel?id=2910";
        Navigate.visit(RunConfig.url + strSplash);
    }

    @When("^I navigate to global navigation menu as follows:$")
    public void I_navigate_to_global_navigation_menu_as_follows(List<String> table) throws Throwable {
        if (onPage("registry_home", "registry_manager")) {
            GlobalNav.openGlobalNav();
            table.forEach(GlobalNav::navigateOnGnByName);
        } else {
            table.forEach(gnName -> {
                GlobalNav.openGlobalNav();
                GlobalNav.navigateOnGnByName(gnName);
            });
        }
        CommonUtils.closeStylistPopup();
    }

    @Then("^URL should contains \"([^\"]*)\" in store page URL$")
    public void urlShouldContain(String expectedValue) throws Throwable {
        Assert.assertTrue("URL does not contains " + expectedValue, WebDriverManager.getCurrentUrl().contains(expectedValue));
        logger.info("URL contains the expected value..");
    }

    @And("^I navigate to \"([^\"]*)\" category browse page$")
    public void iNavigateToPageCategoryBrowsePage(String cat) throws Throwable {
        category = cat;
        List<String> navList = new ArrayList<>();
        switch (category) {
            case "what's new":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL", category.toUpperCase()));
                break;
            case "gifts":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL", category.toUpperCase()));
                break;
            case "chanel from nav":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL"));
                break;
            case "women's fragrance":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL", category.toUpperCase()));
                break;
            case "men's fragrance":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL", category.toUpperCase()));
                break;
            case "makeup":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL", category.toUpperCase()));
                break;
            case "skincare":
                navList.addAll(Arrays.asList("SHOP", "BEAUTY", "BEAUTY BRANDS", "CHANEL", category.toUpperCase()));
                break;
            default:
                Assert.fail("invalid option");
        }
        I_navigate_the_global_navigation_menu_as_follows(navList);
    }
}
