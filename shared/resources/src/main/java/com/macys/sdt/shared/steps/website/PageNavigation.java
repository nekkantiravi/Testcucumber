package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.CampaignService;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategoryBrowse;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategorySplash;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.HeaderActions;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.utils.CommonUtils;
import com.macys.sdt.shared.utils.DataVault;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.macys.sdt.shared.steps.website.Registry.registryMode;

public class PageNavigation extends StepUtils {

    public enum NavigateMode {
        Site("site"),
        Iship("iship"),
        Registry("registry");

        String val = null;

        NavigateMode(String _val) {
            this.val = _val;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(PageNavigation.class);
    public static String searchKeyword;
    public static String shoppingMode;
    private CategoryBrowse categoryBrowse = new CategoryBrowse();
    private CategorySplash categorySplash = new CategorySplash();
    private String ISHIPCountry = "Canada";
    private String ISHIPCountryCode = "CA";
    private String selectedSubcategory;
    public static String slectedBrandName;
    public static boolean preSelectedFacet;

    @And("^I select \"([^\"]*)\" brand in (?:all beauty|all) brands page$")
    public static void iSelectInAllBeautyBrandsPage(String brandName) throws Throwable {
        pausePageHangWatchDog();
        slectedBrandName = brandName;
        Clicks.clickElementByText("designer_static.brands", brandName);
        logger.info("Successfully navigated to " + brandName + " brand page");
        resumePageHangWatchDog();
    }

    /**
     * Navigates to the home page, clears cookies, and closes all popups that appear
     */
    @Given("^I visit the web ?site as a guest user$")
    public void I_visit_the_web_site_as_a_guest_user() {
//        System.out.println("-->>>> Vault data example param 'please' = " + new DataVault("m526092").getParam("please"));
        Navigate.visit("home");
        MyAccountSteps.iClearAllTheCookies();
        Clicks.clickIfPresent("home.popup_close");
        Clicks.clickIfPresent("home.close_edit_popup");
        Clicks.clickIfPresent("home.close_signup_for_email_popup");
        closeBcomLoyaltyPromotionVideoOverlay();
        closeBcomPopup();
        Cookies.disableForeseeSurvey();
        closeChatAlert();
        if (Elements.elementPresent("home.lorem_popup")) {
            Clicks.click("home.test_element");
        }
    }

    /**
     * Follows the same steps as "I visit the web site as a guest user" and then logs into an account or creates one
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a (?:registered|signed in) user$")
    public void I_visit_the_web_site_as_a_registered_user() throws Throwable {
        I_visit_the_web_site_as_a_guest_user();
        try {
            // create profile REST service is only for MCOM.
            if (macys() && !prodEnv())
                UserProfileService.createRandomUserProfile();
        } catch (AssertionError e) {
            logger.info("Service failed. Falling back on UI.");
        }
        CommonUtils.signInOrCreateAccount();
        CreateProfile.closeSecurityAlertPopUp();
    }

    /**
     * Navigates to the home page
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I goto Home page$")
    public void i_goto_home_page() throws Throwable {
        Navigate.visit("home");
    }

    /**
     * Checks that the browser is on the given page
     *
     * @param page name of expected page
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see the \"([^\"]*)\" page$")
    public void I_should_see_the_page(String page) throws Throwable {
        shouldBeOnPage(page.replace(" ", "_"));
    }

    @When("^I mouse hover on any category$")
    public void iHoverOnAnyCategory() throws Throwable {
        Wait.forPageReady();
        List<WebElement> elements = Elements.findElements(Elements.element("home.category"))
                .stream()
                .filter(e -> !e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        List<String> fobList = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        if(bloomingdales() && HeaderActions.getSiteMode().equalsIgnoreCase("registry")) {
            fobList.remove("SALE");
        } else if(bloomingdales() && HeaderActions.getSiteMode().equalsIgnoreCase("iship")){
            fobList.remove("THE REGISTRY");
        }
        Random randomGenerator = new Random();
        int randomFobIndex = elements.size() <= 1 ? 0 : randomGenerator.nextInt(elements.size() - 1);
        String randomFob = fobList.get(randomFobIndex);
        Clicks.hoverForSelection(Elements.findElement(By.linkText(randomFob)));
        logger.info("Hovered on random category");
    }

    /**
     * Mouses over the given category in the top navigation bar
     *
     * @param menu category to mouse over
     * @throws Throwable if any exception occurs
     */
    @When("^I mouse over \"([^\"]*)\" category from top navigation$")
    public void I_mouse_over_category_from_top_navigation(String menu) throws Throwable {
        Navigate.scrollPage(0, 1000);
        Navigate.scrollPage(0, -1000);
        if (tablet()) {
            new Home().selectMainCategory(menu);
        }
        CreateProfile.closeSecurityAlertPopUp();
        try {
            By menuEl = Elements.paramElement("header.flyout_category", menu.toUpperCase());
            Clicks.hoverForSelection(menuEl);
        } catch (Exception e) {
            Clicks.hoverForSelection(Elements.findElement(By.linkText(menu)));
        }
        Utils.threadSleep(2000, null);
    }

    /**
     * Mouses over a random category in the top navigation bar
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I mouse over random category from top navigation$")
    public void I_mouse_over_random_category_from_top_navigation() throws Throwable {
        List<String> categories = new Home().getAllMainCategoryNames();
        I_mouse_over_category_from_top_navigation(categories.get(new Random().nextInt(categories.size())));
    }

    /**
     * Selects the given subcategory from a visible flyout
     * <p>
     * This step requires a visible flyout BEFORE calling it
     * </p>
     *
     * @param subCategory subcategory to select
     */
    @When("^I select \"([^\"]*)\" subcategory from flyout menu")
    public void iSelectSubcategoryFromFlyoutMenu(String subCategory) {
        if (tablet()) {
            new Home().selectSubCategory(subCategory);
        }
        if (ie()) {
            Clicks.javascriptClick(By.linkText(subCategory));
        } else {
            pausePageHangWatchDog();
            if (edge() || firefox()) {
                Elements.findElement("header.open_flyout").findElements(By.tagName("a")).stream()
                        .filter(a -> a.isDisplayed() && a.getText().toLowerCase().contains(subCategory.toLowerCase()))
                        .findFirst().get().click();
            } else {
                Clicks.javascriptClick(By.linkText(subCategory));
            }
            resumePageHangWatchDog();
        }
    }

    /**
     * Clicks the "create your registry" button on registry home page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select \"Create Your Registry\"$")
    public void iSelectCreateRegistry() throws Throwable {
        if (chrome() && macys() && !Elements.elementPresent("registry_home.goto_create_registry")) {
            Clicks.clickArea("alt", "create your registry");
        } else if (edge() || firefox()) {
            Clicks.javascriptClick("registry_home.goto_create_registry");
        } else {
            Clicks.click("registry_home.goto_create_registry");
        }
        Wait.forPageReady();
    }

    /**
     * Visits the deals and promotions page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I visit the deals and promotions page$")
    public void I_visit_the_deals_and_promotions_page() throws Throwable {
        i_goto_home_page();
        Clicks.click("my_account.goto_deals_promotions");
        CreateProfile.closeSecurityAlertPopUp();
        Clicks.clickIfPresent("home.close_signup_for_email_popup");
    }

    /**
     * Navigates to the given sign in page
     *
     * @param mode "site" or "registry" (case insensitive)
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to signin page of \"([^\"]*)\" mode$")
    public void I_navigate_to_signin_page_of_mode(String mode) throws Throwable {
        switch (mode.toLowerCase()) {
            case "site":
                Clicks.click("home.goto_sign_in_link");
                CommonUtils.closeIECertError();
                break;
            case "registry":
                Clicks.click("home.goto_wedding_registry");
                Clicks.click("registry_home.manage_box");
                Wait.untilElementPresent("registry_home.sign_in_email");
                break;
        }
    }

    /**
     * Navigates to the given footer link
     * <p>
     * options:<br>
     * <code>ways to shop<br>
     * stores<br>
     * outlet<br>
     * order status<br>
     * pay bill<br>
     * apply &amp; learn more<br>
     * credit services</code>
     * </p>
     *
     * @param footer_link link text of footer link
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the \"([^\"]*)\" page from footer$")
    public void I_navigate_to_the_page_from_footer(String footer_link) throws Throwable {
        switch (footer_link.toLowerCase()) {
            case "ways to shop":
                if (Elements.elementPresent("home.goto_ways_to_shop")) {
                    Clicks.click("home.goto_ways_to_shop");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "stores":
                if (Elements.elementPresent("home.goto_our_stores")) {
                    Clicks.click("home.goto_our_stores");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "outlet":
                if (Elements.elementPresent("home.goto_outlets")) {
                    Clicks.click("home.goto_outlets");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "order status":
                logger.info("Click on order status link from footer");
                if (Elements.elementPresent("home.goto_order_status")) {
                    Clicks.javascriptClick("home.goto_order_status");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "pay bill":
                if (Elements.elementPresent("home.goto_credit_pay_bill_online")) {
                    Clicks.click("home.goto_credit_pay_bill_online");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "apply & learn more":
                if (Elements.elementPresent("home.goto_credit_apply_now")) {
                    Clicks.click("home.goto_credit_apply_now");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "credit services":
                if (Elements.elementPresent("home.goto_credit_services")) {
                    Clicks.click("home.goto_credit_services");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
            case "cardholder benefits":
                if (Elements.elementPresent("home.goto_credit_cardholder_benefits")) {
                    Clicks.click("home.goto_credit_cardholder_benefits");
                } else {
                    Assert.fail(footer_link + " is not found in the footer section");
                }
                break;
        }
    }

    /**
     * Navigates to the shopping pag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to shopping bag page$")
    public void I_navigate_to_shopping_bag_page() throws Throwable {
        if (safari()) {
            Wait.secondsUntilElementPresent("header.quick_bag", 15);
        }
        Clicks.click("header.quick_bag");
    }

    /**
     * Navigates to the given FOB
     *
     * @param fob FOB to select
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to \"([^\"]*)\" FOB on the left navigation$")
    public void I_navigate_to_FOB_on_the_left_navigation(String fob) throws Throwable {
        if (Elements.elementPresent("category_splash.brand_links")) {
            int link_size = Elements.findElements("category_splash.brand_links").size();
            for (int i = 0; i < link_size; i++) {
                boolean is_link = Elements.findElements("category_splash.brand_links").get(i).getText().equalsIgnoreCase(fob);
                if (is_link) {
                    Clicks.click(Elements.findElements("category_splash.brand_links").get(i));
                    break;
                }
            }
        } else {
            logger.info("Unable to find left navigation links");
        }
    }

    /**
     * Verifies the browser is on the PDP
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to PDP(?: page)?$")
    public void I_should_be_redirected_to_PDP_page() throws Throwable {
        if (safari()) {
            Wait.secondsUntilElementPresent("product_display.verify_page", 20);
        }
        pausePageHangWatchDog();
        shouldBeOnPage("product_display");
        resumePageHangWatchDog();
    }

    /**
     * Verifies the browser is on the add to bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to ATB page$")
    public void I_should_be_redirected_to_ATB_page() throws Throwable {
        if (!onPage("add_to_bag")) {
            if (!Elements.elementPresent("product_display.add_to_bag_dialog") &&
                    !Elements.elementPresent("product_display.master_add_to_bag_dialog")) {
                Assert.fail("Not on ATB page OR panel");
            }
        }
    }

    /**
     * Verifies the browser is on the domestic home page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to domestic home page as a sign in user$")
    public void I_should_be_navigated_to_domestic_home_page_as_a_sign_in_user() throws Throwable {
        if (!onPage("home")) {
            Assert.fail("Not navigated to the domestic home page");
        }
    }

    /**
     * Verifies the browser is on the search results
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be in (?:Search Landing|Dynamic Landing) page$")
    public void I_should_be_in_Search_Landing_page() throws Throwable {
        shouldBeOnPage("search_result");
    }

    @Then("^I should be in Search Landing or redirected Browse Page page$")
    public void iShouldBeInSearchLandingOrRedirectedBrowsePagePage() throws Throwable {
        shouldBeOnPage("search_result", "category_browse");
    }

    /**
     * Navigates to the change country page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to international context page$")
    public void I_navigate_to_international_context_page() throws Throwable {
        Wait.secondsUntilElementPresent("header_and_footer.goto_change_country", 10);
        Clicks.javascriptClick("header_and_footer.goto_change_country");
        Wait.forPageReady();
    }

    /**
     * Clicks the find out more link on header panel
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click find out more link on header panel$")
    public void I_click_find_out_more_link_on_header_panel() throws Throwable {
        Wait.untilElementPresent("home.find_out_more");
        Clicks.click("home.find_out_more");
    }

    /**
     * Navigates to the given category page
     *
     * @param arg1 category to visit
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to \"([^\"]*)\" category page$")
    public void I_navigate_to_category_page(String arg1) throws Throwable {
        new Home().selectMainCategory(arg1);
        //   shouldBeOnPage("category_splash");
    }

    /**
     * Selects the given featured category
     *
     * @param category category to select
     * @throws Throwable if any exception occurs
     */
    @And("^I click on thumbnail \"([^\"]*)\" on featured categories$")
    public void I_click_on_thumbnail_on_featured_categories(String category) throws Throwable {
        Wait.forPageReady();
        if (!Elements.elementPresent("category_splash.featured_categories")) {
            Clicks.click(CategorySplash.selectFeaturedCategory(category));
        } else {
            Assert.fail("ERROR - ENV: featured categories panel is not visible..... ");
        }
    }

    /**
     * Selects the given link in the header
     *
     * @param link link text of header link
     * @throws Throwable if any exception occurs
     */
    @When("^I click on \"([^\"]*)\" link in the header$")
    public void I_click_on_link_in_the_header(String link) throws Throwable {
        switch (link.toLowerCase()) {
            case "sign in":
                Clicks.click("home.goto_sign_in_link");
                CommonUtils.closeIECertError();
                break;
            case "my account":
                logger.info("Click on my account link in header!!");
                Clicks.click("home.goto_guest_my_account");
                CommonUtils.closeIECertError();
                break;
            case "stores":
                Clicks.click("home.stores");
                CommonUtils.closeIECertError();
                break;
            case "logo":
                Clicks.click("home.logo");
                CommonUtils.closeIECertError();
                break;
            default:
                Clicks.click(By.linkText(link));
                break;
        }
    }

    /**
     * Navigates to sign in page by clicking the link in the header
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to sign in page$")
    public void I_navigate_to_sign_in_page() throws Throwable {
        Clicks.click("home.goto_sign_in_link");
        CommonUtils.closeIECertError();
        shouldBeOnPage("sign_in");
        logger.info("Performed Action : Navigated to sign in page");
    }

    /**
     * Navigates the browser back one page and expects the given page to be visible
     *
     * @param page_type expected page
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate back to \"([^\"]*)\" page$")
    public void I_navigate_back_to_page(String page_type) throws Throwable {
        if (page_type.equalsIgnoreCase("home")) {
            Navigate.visit("home");
        } else {
            Navigate.browserBack();
            if (safari() || ie()) {
                if (page_type.equals("OH")) {
                    Clicks.click("home.goto_order_status");
                }
            }
            page_type = page_type.replace(" ", "_");
            if (Elements.element(page_type + ".url") != null) {
                shouldBeOnPage(page_type);
            }
        }
    }

    /**
     * Navigates to the given category and subcategory
     *
     * @param subcategory subcategory to visit
     * @param category    category to visit
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to the \"([^\"]*)\" (?:browse|sub splash|catsplash) page under \"([^\"]*)\"$")
    public void I_navigate_to_the_browse_page_under(String subcategory, String category) throws Throwable {
        Home homePage = new Home();
        pausePageHangWatchDog();
        homePage.selectMainCategory(category);
        shouldBeOnPage("category_splash");
        homePage.selectSubCategory(subcategory);
        resumePageHangWatchDog();
        Wait.forPageReady();
    }

    /**
     * Navigates to the given category and subcategory
     *
     * @param subcategory subcategory to visit
     * @param category    category to visit
     * @throws Throwable if any exception occurs
     */
    @When("^I am on Browse Page for \"([^\"]*)\" under \"([^\"]*)\"$")
    public void I_am_on_browse_page_for_under(String subcategory, String category) throws Throwable {
        I_visit_the_web_site_as_a_guest_user();
        I_navigate_to_the_browse_page_under(subcategory, category);
    }

    /**
     * Navigates to the given footer link
     *
     * @param link link text
     * @throws Throwable if any exception occurs
     */
    @When("^I Navigate to \"([^\"]*)\" footer links$")
    public void I_Navigate_to_footer_links(String link) throws Throwable {
        if (link.equalsIgnoreCase("eSSENTIAL Accessibility")) {
            Clicks.click(Elements.findElement("footer.goto_essential_accessible").findElement(By.tagName("a")));
        } else {
            Elements.elementInView(By.linkText(link));
            Clicks.click(By.linkText(link));
            Wait.forPageReady();
        if (link.equals("catalogs")) {
            Wait.secondsUntilElementPresent("home.verify_page", 15);
            Clicks.clickWhenPresent("home.verify_page");
        }
        }
    }

    /**
     * Clicks the given store from store search results
     *
     * @param link store to click
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" link in a store from store results$")
    public void I_click_link_in_a_store_from_store_results(String link) throws Throwable {
        if (link.equalsIgnoreCase("more arrow")) {
            Clicks.clickWhenPresent("stores.more");
        } else {
            Assert.fail("This method does not yet support " + link);
        }
    }

    /**
     * Clicks the given link on map popup
     * <p>
     * The only supported option is "directions"
     * </p>
     *
     * @param link text of link to click on
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" link from a map popup$")
    public void I_click_link_from_a_map_popup(String link) throws Throwable {
        if (link.equalsIgnoreCase("directions")) {
            Clicks.click("stores.directions");
            Navigate.switchWindow(1);
            Navigate.switchWindowClose();
        }
    }

    /**
     * Navigates to the create profile page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to create profile page$")
    public void I_navigate_to_create_profile_page() throws Throwable {
        if (!onPage("sign_in")) {
            Navigate.visit("home.sign_in");
        }
        logger.info("Go to profile creation page!!");
        Wait.secondsUntilElementPresent("sign_in.create_profile", 15);
        Clicks.click("sign_in.create_profile");
        Wait.secondsUntilElementPresent("create_profile.verify_page", 15);
        if (!onPage("create_profile")) {
            Assert.fail("Not navigated to the create profile page");
        }
    }

    /**
     * Selects "continue checkout" on add to bag page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to shopping bag page from add to bag page$")
    public void I_navigate_to_shopping_bag_page_from_add_to_bag_page() throws Throwable {
        CreateProfile.closeSecurityAlertPopUp();
        if (Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
            Clicks.javascriptClick("add_to_bag_dialog.add_to_bag_checkout");
        } else if (Elements.elementPresent("add_to_bag_dialog.master_add_to_bag_dialog")) {
            Clicks.click("add_to_bag_dialog.master_add_to_bag_checkout");
        } else {
            Clicks.click("add_to_bag.checkout");
        }
    }

    /**
     * Verifies that the browser is on the my account page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to My Account Page$")
    public void I_should_be_navigated_to_My_Account_Page() throws Throwable {
        logger.info("Verify user is on my account page!!");
        shouldBeOnPage("my_account");
    }

    /**
     * Verifies that the browser is on the USL loyalty home page
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I am on the USL loyalty home page$")
    public void I_am_on_the_USL_loyalty_home_page() throws Throwable {
        // Now we have new USL home page in qa environment which is pointing to production, So we are directly visit USL sign in page instead of USL home.
        if (!signedIn()) {
            Navigate.visit("usl_sign_in");
        } else {
            Clicks.hoverForSelection("my_account.goto_my_account");
            Wait.untilElementPresent("my_account.goto_my_plenti");
            Clicks.click("my_account.goto_my_plenti");
        }
        if (!onPage("usl_home, usl_sign_in".split(", "))) {
            Assert.fail("Not navigated to USL home page");
        }
    }

    /**
     * Verifies the browser is on the order review page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be on order review page$")
    public void I_should_be_on_order_review_page() throws Throwable {
        Wait.secondsUntilElementPresent("order_review.verify_page", 15);
        shouldBeOnPage("order_review, responsive_order_review_section".split(", "));
    }

    /**
     * Visits the web site then goes to the specified mode
     *
     * @param mode_name domestic|iship|registry
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a guest user in \"([^\"]*)\" mode$")
    public void I_visit_web_site_as_a_guest_user_in_mode(String mode_name) throws Throwable {
        I_visit_the_web_site_as_a_guest_user();
        switch (mode_name.toLowerCase()) {
            case "domestic":
                break;
            case "iship":
                pausePageHangWatchDog();
                I_navigate_to_international_context_page();
                ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
                shopAndBrowse.I_change_country_to("a random country");
                shopAndBrowse.I_close_the_welcome_mat_if_it_s_visible();
                resumePageHangWatchDog();
                break;
            case "registry":
                new Registry().I_navigate_to_registry_home_page();
                break;
        }
        logger.info("Guest User visited web site in " + mode_name);

    }

    @Given("^I visit the web site as a registered user in \"(domestic|iship|registry)\" mode$")
    public void I_visit_web_site_as_a_registered_user_in_mode(String mode_name) throws Throwable {
        I_visit_the_web_site_as_a_registered_user();
        switch (mode_name.toLowerCase()) {
            case "domestic":
                break;
            case "iship":
                I_navigate_to_international_context_page();
                new ShopAndBrowse().I_change_country_to("a random country");
                break;
            case "registry":
                new Registry().I_navigate_to_registry_home_page();
                break;
        }
        logger.info("Registered User visited web site in " + mode_name + "");
    }

    /**
     * Navigates to the given page in the given site mode
     *
     * @param page_name "search results" or "dynamic landing" or "browse" or "brand index"
     * @param mode_name "domestic" or "iship" or "registry"
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to (search results|dynamic landing|browse|brand index|Designer Index) page in \"(domestic|iship|registry)\" mode$")
    public void I_navigate_to_page_in_mode(String page_name, String mode_name) throws Throwable {
        Home home = new Home();
        switch (page_name.toLowerCase()) {
            case "search results":
                String searchItemText = mode_name.equalsIgnoreCase("registry") ? "plates" : "jeans";
                categoryBrowse.searchForAnItem(searchItemText);
                Wait.forPageReady();
                break;
            case "dynamic landing":
                if (mode_name.equalsIgnoreCase("iship") || (bloomingdales() && mode_name.equalsIgnoreCase("registry"))) {
                    //Home home = new Home();
                    home.selectMainCategory(macys() || (macys() && mode_name.equalsIgnoreCase("registry")) ? "BRANDS" : "DESIGNERS");
                    Clicks.clickRandomElement("designer_static.brands", ele -> ele.getAttribute("href").contains((macys() ? "/featured/" : "/buy/")));
                    Wait.forPageReady();
                } else {
                    categorySplash.navigateToRandomCategoryWithPopularSearchLink(8);
                }
                break;
            case "browse":
                categorySplash.navigateToRandomCategoryBrowsePage(8);
                break;
            case "brand index":
                //Home home = new Home();
                home.selectMainCategory(macys() || (macys() && mode_name.equalsIgnoreCase("registry")) ? "BRANDS" : "DESIGNERS");
                break;
            case "Designer Index":
                //Home home = new Home();
                home.selectMainCategory("DEISGNERS");
        }
        closeAlert();
    }

    /**
     * Verifies that the given sort by options are visible in the sort by drop down
     *
     * @param sortByList list of expected sort by options
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see sort by functionality with below options:$")
    public void I_should_see_sort_by_functionality_with_below_options(List<String> sortByList) throws Throwable {
        List<String> sortByOptions;
        pausePageHangWatchDog();
        if (safari()) {
            Wait.secondsUntilElementPresent("pagination.sort_by", 15);
        }
        if (bloomingdales()) {
            String defaultText = Elements.getText("pagination.sort_by");
            sortByOptions = DropDowns.getAllCustomValues("pagination.sort_by", "pagination.sort_by_options");
            sortByOptions.add(0, defaultText);
        } else {
            sortByOptions = DropDowns.getAllValues("pagination.sort_by");
        }
        if (!(sortByOptions.size() == sortByList.size())) {
            Assert.fail("sort by list options are not displayed correctly!!");
        }

        boolean foundMatch;
        for (String option : sortByList) {
            if (bloomingdales() && option.equalsIgnoreCase("Our Top Picks") && WebDriverManager.getCurrentUrl().contains("mode=wedding")) {
                option = "Sort by";
            }
            //Observed that, In website, Sort by options values are varying for products and thus failing assertions
            //Example: Feature file has "Price: Low to High", where as in Website, it is listed as "Price: (low to high)"
            foundMatch = CommonUtils.matchSimilarSortBy(sortByOptions, option);
            if (!foundMatch) {
                Assert.fail("sort by (" + option + ") option is not displayed in page!!");
            }
        }
        int productCount = categoryBrowse.getProductCount();
        if (bloomingdales()) {
            sortByOptions.remove(0);
        }
        categoryBrowse.sortByValue(sortByOptions.get(new Random().nextInt(sortByOptions.size())));
        if (!(productCount == categoryBrowse.getProductCount())) {
            Assert.fail("Sort by functionality is not working properly!!");
        }
        resumePageHangWatchDog();
    }

    /**
     * Verifies that the search page navigation buttons work
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be able to navigate using pagination functionality$")
    public void I_should_be_able_to_navigate_using_pagination_functionality() throws Throwable {
        int pageCount = categoryBrowse.getPageCount();
        if (pageCount > 1) {
            int currentPageNumber = categoryBrowse.getCurrentPageNumber();
            categoryBrowse.gotoPageNumber(currentPageNumber + 1);
            if (categoryBrowse.getCurrentPageNumber() != (currentPageNumber + 1)) {
                Assert.fail("Pagination functionality is not working properly!!");
            }
            currentPageNumber = categoryBrowse.getCurrentPageNumber();
            categoryBrowse.gotoPageNumber(currentPageNumber - 1);
            if (categoryBrowse.getCurrentPageNumber() != (currentPageNumber - 1)) {
                Assert.fail("Pagination functionality is not working properly!!");
            }
        }
    }

    /**
     * Verifies that the browser is on the brand index page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to brand index page$")
    public void I_should_be_navigated_to_brand_index_page() throws Throwable {
        if (!onPage("designer_static")) {
            Assert.fail("User is not navigated to brand index page!!");
        }
    }

    /**
     * Navigates to the order details page using footer link
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to order details page from footer$")
    public void I_navigate_to_order_details_page() throws Throwable {
        if (!Elements.elementPresent("footer.goto_order_status")) {
            Navigate.visit("home");
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("footer.goto_order_status", safari() ? 20 : 10);
        }
        Wait.forPageReady();
        Clicks.javascriptClick("footer.goto_order_status");
        if (safari()) {
            Wait.secondsUntilElementPresent("order_status.verify_page", 10);
        }
        Assert.assertTrue("Not Navigate to Order Details Page", onPage("order_status"));
    }

    /**
     * Visits the web site and starts a loyalty campaign based on given data
     *
     * @param userType     "guest" or "registered"
     * @param moneyType    "mMoney", "bMoney", or "ICWMoney"
     * @param campaignType "earn" or "burn"
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a (guest|registered) user in (mMoney|bMoney|ICWMoney) (earn|burn) period$")
    public void I_visit_the_web_site_as_a_guest_user_in_mMoney_earn_period(String userType, String moneyType, String campaignType) throws Throwable {
        pausePageHangWatchDog();
        I_visit_the_web_site_as_a_guest_user();
        CampaignService.setCampaignName(moneyType);
        Map<String, Boolean> mbMoneyCampaignDetails = CampaignService.getAllCampaignPeriods();
        if (campaignType.equals("earn") && mbMoneyCampaignDetails.get("earn").equals(false)) {
            CampaignService.updateCampaignDetailsInDatabase(moneyType.contains("ICW") ? "ICWMEarn" : (macys() ? "MEarn" : "BEarn"));
        } else if (campaignType.equals("burn") && mbMoneyCampaignDetails.get("redeem").equals(false)) {
            CampaignService.updateCampaignDetailsInDatabase(moneyType.contains("ICW") ? "ICWMRedeem" : (macys() ? "MRedeem" : "BRedeem"));
        } else if (mbMoneyCampaignDetails.get(campaignType).equals(false)) {
            CampaignService.updateCampaignDetailsInDatabase(campaignType);
        }
        CampaignService.clearAllMbmoneyRelatedCaches();
        resumePageHangWatchDog();
        if (!userType.equals("guest")) {
            new MyAccountSteps().iVisitTheWebSiteAsARegisteredUserWithCheckoutEligibleAddress();
        }
    }

    /**
     * Selects a random recently viewed product on PDP
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select a recently viewed product in product display page$")
    public void I_select_a_recently_viewed_product_in_product_display_page() throws Throwable {
        Clicks.clickRandomElement("recently_viewed_items.item");
    }

    /**
     * Navigates to a random product
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to a random product$")
    public void iNavigateToARandomProduct() throws Throwable {
        CommonUtils.navigateToRandomProduct();
    }

    /**
     * Navigates to a random category splash page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to random category splash page$")
    public void I_navigate_to_random_category_splash_page() throws Throwable {
        Home.selectRandomCategory();
    }

    @And("^I navigate to sub categories from Left hand nav links$")
    public void i_navigate_to_sub_categories_from_left_hand_nav_links() throws Throwable {
        selectedSubcategory = CommonUtils.sanitizeString(Home.selectRandomSubCategory());
    }

    @And("^I navigate to \"([^\"]*)\" (?:browse|subsplash) page from cat splash page$")
    public void i_navigate_to_cat_browse_page_from_cat_splash_page(String Cat_browse) throws Throwable {
        Wait.forPageReady();
        if(Cat_browse.equalsIgnoreCase("random")){
            WebElement selected = null;
            if(Elements.elementPresent("category_splash.subcategory")) {
                selected = Elements.getRandomElement(Elements.element("category_splash.subcategory"), (el) -> el.isDisplayed() && !el.getText().matches("(.*?)(ACTIVE|Brands|Impulse|DESIGNERS|WEDDING REGISTRY)(.*?)"));
            }else if (Wait.secondsUntilElementPresent("left_navigation_category.discovery_subcategory", 30) && Elements.elementPresent("left_navigation_category.discovery_subcategory")){
                selected = Elements.getRandomElement(Elements.element("left_navigation_category.discovery_subcategory"), (el) -> el.isDisplayed() && !el.getText().matches("(.*?)(ACTIVE|Brands|Impulse|DESIGNERS|WEDDING REGISTRY)(.*?)"));
            }
            selectedSubcategory = selected.getText();
            logger.info("SubCategory text is: " + selectedSubcategory);
            Clicks.click(selected);
            Wait.forPageReady();
            // Safari is not waiting for page load after clicking on subcategory
            if (safari()) {
                Utils.threadSleep(1000, null);
                Wait.forPageReady();
            }
        }else{
            selectedSubcategory = Cat_browse;
            new Home().selectSubCategory(Cat_browse);
        }

    }

    @Then("^I verify the correct subcategory page is loaded$")
    public void i_verify_the_correct_subcategory_page_is_loaded() throws Throwable {
        if (Elements.elementPresent("category_browse.current_category")) {
            if (Elements.elementPresent("left_facet.clearAllButton")) {
                Navigate.browserBack();
                i_navigate_to_sub_categories_from_left_hand_nav_links();
            }
            String[] selectedSubcategoryTest = selectedSubcategory.toLowerCase().split(" ");
            logger.info(selectedSubcategoryTest[0]);
            String subcategoryText = CommonUtils.sanitizeString(Elements.getText("category_browse.current_category")).replaceAll("-", " ");
            Assert.assertTrue("Category title \"" + subcategoryText + "\" does not match expected: \"" + selectedSubcategory.toLowerCase() + "\"",
            subcategoryText.toLowerCase().contains(selectedSubcategory.replaceAll("-", " ").toLowerCase()) || selectedSubcategory.replaceAll("-", " ").toLowerCase().contains(subcategoryText.toLowerCase()) || subcategoryText.toLowerCase().contains(selectedSubcategory.toLowerCase()) || WebDriverManager.getCurrentUrl().contains(selectedSubcategory.replaceAll("-", "").toLowerCase()) || WebDriverManager.getCurrentUrl().contains(selectedSubcategoryTest[0]));
        } else {
            Assert.assertTrue("Not on expected category page",
                    WebDriverManager.getCurrentUrl().contains(selectedSubcategory));
        }
    }

    @Given("^I am on CatSplash Page for \"([^\"]*)\" on \"(domestic|iship|registry)\" mode$")
    public void iAmOnCatSplashPageForOnMode(String category, String mode) throws Throwable {
        I_visit_web_site_as_a_guest_user_in_mode(mode);
        new Home().selectMainCategory(category);
    }

    @Then("^I verify the Description section for \"([^\"]*)\" on category splash page$")
    public void iVerifyTheDescriptionSectionForOnCategorySplashPage(String category) throws Throwable {
        category = category.toLowerCase();
        Assert.assertTrue("ERROR - DATA: Should have Description section",
                Elements.elementPresent("category_splash.description_title"));
        String title = Elements.getText("category_splash.description_title").toLowerCase();
        Assert.assertTrue("Title did not contain category name", title.contains(category));

        String description = Elements.getText("category_splash.description");
        Assert.assertTrue("Description should be more than 50 characters long", description.length() > 50);
    }

    /**
     * Follows the same steps as "I visit the web site as a guest user" and then logs into production an account
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the production web site as a \"([^\"]*)\" registered user$")
    public void I_visit_the_production_web_site_as_a_registered_user(String userType) throws Throwable {
        logger.info("Visit website as registered user!!");
        if (prodEnv() && macys()) {
            Cookies.deleteCookie("SEGMENT");
            Cookies.addSegment("2252");
        }
        I_visit_the_web_site_as_a_guest_user();

        String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
        Wait.untilElementPresent(elementName);
        Clicks.click(elementName);
        CommonUtils.signInWithExistingProfileToProdServer(userType);
        CreateProfile.closeSecurityAlertPopUp();
        Cookies.disableForeseeSurvey();
    }

    @Given("^I am on SearchResultsPage for \"([^\"]*)\" in ([^\"]*) mode$")
    public void iAmOnSearchResultsPageForOnMode(String keyword, String mode) throws Throwable {
        searchKeyword = keyword;
        shoppingMode = mode;
        try {
            //Declaration
            ShopAndBrowse browse = new ShopAndBrowse();
            PageNavigation pageNavigation = new PageNavigation();

            pageNavigation.I_visit_the_web_site_as_a_guest_user();
            if (mode.equalsIgnoreCase("ISHIP")) {
                pageNavigation.I_navigate_to_international_context_page();
                browse.I_change_country_to(ISHIPCountry);
                browse.I_close_the_welcome_mat_if_it_s_visible();
            }
            if (mode.equalsIgnoreCase("REGISTRY")) {
                Clicks.click("home.goto_wedding_registry");
            }
            System.out.println("searchKeyword ::" + searchKeyword);
            ShopAndBrowse.I_search_for(searchKeyword);
            Wait.forPageReady();
            shouldBeOnPage("search_result");
            String snbcExp = RunConfig.getEnvOrExParam("snbc_exp");
            if (snbcExp == null || snbcExp.equalsIgnoreCase("true")) {
                String componentizationUrl;
                String currentURL = WebDriverManager.getCurrentUrl();
                String efcKeyValue = macys() ? "2245" : "2265";
                if (currentURL.contains("EFCKEY"))
                    componentizationUrl = currentURL.replaceAll("EFCKEY(.*)", "EFCKEY=%7B%22EXPERIMENT%22%3A%5B" + efcKeyValue + "%5D%7D");
                else if (currentURL.contains("?")) {
                    componentizationUrl = currentURL.concat("&EFCKEY=%7B%22EXPERIMENT%22%3A%5B" + efcKeyValue + "%5D%7D");
                } else {
                    componentizationUrl = currentURL.concat("?EFCKEY=%7B%22EXPERIMENT%22%3A%5B" + efcKeyValue + "%5D%7D");
                }
                Navigate.visit(componentizationUrl);
                shouldBeOnPage("search_result");
            }
        } catch (Exception e) {
            Assert.fail("Error Navigating to " + searchKeyword + " in " + mode + "\r\n" + e.getMessage());
        }
        logger.info("Navigated to Search Results Page for " + searchKeyword + " on " + shoppingMode + " mode");
    }

    @And("^I select \"([^\"]*)\" category from left nav$")
    public void iSelectCategoryFromLeftNav(String subCategory) throws Throwable {
        Home homePage = new Home();
        homePage.selectSubCategory(subCategory);
        resumePageHangWatchDog();
        Wait.forPageReady();
        logger.info("Navigated to Category Page :" + subCategory);
    }

    @When("^I navigate to \"([^\"]*)\" category for static landing page$")
    public void iNavigateToCategoryForStaticLandingPage(String childCategory) throws Throwable {
        String categoryURL = RunConfig.url + "/shop/?id=" + childCategory;
        Navigate.visit(categoryURL);
        Wait.forPageReady();
        closePopup();
    }

    @When("^I open a new tab in the same browser$")
    public void iOpenANewTabInTheSameBrowser() throws Throwable {
        ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.open();");
    }

    @Given("^I close the existing tab and navigate to home page in new tab$")
    public void iCloseTheExistingTabAndNavigateToHomePageInNewTab() throws Throwable {
        if (macys()) {
            WebDriverManager.getWebDriver().close();
            Navigate.switchWindow(0);
            Navigate.visit("home");
            Clicks.clickIfPresent("home.popup_close");
        } else {
            ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.open();");
            WebDriverManager.getWebDriver().close();
            Navigate.switchWindow(0);
            Navigate.visit("home");
            Clicks.clickIfPresent("home.popup_close");
            closeBcomLoyaltyPromotionVideoOverlay();
            closeBcomPopup();
        }
    }

    @When("^I navigate to the (?:static|bookmarked) url:$")
    public void INavigateToTheStaticUrl(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        String expectedUrl;
        if (macys()) {
            expectedUrl = values.get("MCOM");
        } else {
            expectedUrl = values.get("BCOM");

        }
        String staticUrl = RunConfig.url + expectedUrl;
        Navigate.visit(staticUrl);
        Wait.forPageReady();
        closePopup();
    }

    @Given("^I am on DynamicLandingPage in \"([^\"]*)\" mode(?: with (color facet|brand facet|bops facet|size facet|pagination|department facet|designer facet|item type|price facet|sales & offers facet|customer rating facet))?$")
    public void iAmOnDynamicLandingPageInShopping_modeMode(String mode, String expectation) throws Throwable {
        shoppingMode = mode;
        pausePageHangWatchDog();
        try {
            //Declaration
            ShopAndBrowse browse = new ShopAndBrowse();
            PageNavigation pageNavigation = new PageNavigation();

            pageNavigation.I_visit_the_web_site_as_a_guest_user();
            if (mode.equalsIgnoreCase("ISHIP")) {
                pageNavigation.I_navigate_to_international_context_page();
                browse.I_change_country_to(ISHIPCountry);
                browse.I_close_the_welcome_mat_if_it_s_visible();
            }
            String selectedFOB = "";
            if (mode.equalsIgnoreCase("REGISTRY")) {
                Clicks.click("home.goto_wedding_registry");
//                Updated below step as we don't have See All Brands Subcategory in Bed and Bath FOB for BCOM brand in registry mode
//                I_navigate_to_the_browse_page_under("See All Brands", "BED & BATH");
                I_navigate_to_page_in_mode("brand index","registry");
                new BrowsePageSteps().iSelectAnyBrandFromDesignerPage();
            }else if (mode.equalsIgnoreCase("ISHIP")) {
                pausePageHangWatchDog();
                I_navigate_to_page_in_mode("brand index", mode);
                iSelectInAllBeautyBrandsPage("City Chic");
                resumePageHangWatchDog();
            } else {
                pausePageHangWatchDog();
                if(macys()) {
                    for (int i = 0; i <= 3; i++) {
                        selectedFOB = new Home().selectRandomCategory();
                        logger.info("Verifying popular searches availability on FOB:" + selectedFOB);
                        if (CategorySplash.popularSearchLinksAvailable()) {
                            break;
                        }
                    }
                    Assert.assertTrue("ERROR - DATA: Popular searches are not available on FOB:" + selectedFOB, CategorySplash.popularSearchLinksAvailable());
                    searchKeyword = CategorySplash.selectOnePopularSearchLink();
                } else {
                    I_navigate_to_page_in_mode("brand index", mode);
                    new BrowsePageSteps().iSelectAnyBrandFromDesignerPage();
                }
                resumePageHangWatchDog();
            }
            if (macys() && expectation != null && (expectation.equalsIgnoreCase("color facet") || expectation.equalsIgnoreCase("brand facet") || expectation.equalsIgnoreCase("bops facet") || expectation.equalsIgnoreCase("size facet") || expectation.equalsIgnoreCase("pagination") || expectation.equalsIgnoreCase("customer rating facet"))) {
                String facetName = expectation.split(" ")[0];
                List<String> facetNames = LeftFacet.getAllFacetName().stream().filter(f -> f.equalsIgnoreCase(facetName)).collect(Collectors.toList());
                if (facetNames.isEmpty() || expectation.equalsIgnoreCase("pagination")) {
                    String dlpURL = RunConfig.url + "/shop/featured/adrianna-papell?cm_sp=shop_by_brand-_-All%20Brands-_-Adrianna%20Papell";
                    if (expectation.equalsIgnoreCase("brand facet") || expectation.equalsIgnoreCase("bops facet") || expectation.equalsIgnoreCase("customer rating facet")) {
                        dlpURL = RunConfig.url +"/shop/featured/dress";
                    }
                    if (mode.equalsIgnoreCase("REGISTRY")) {
                        dlpURL = RunConfig.url + "/shop/featured/dress?mode=wedding";
                    }
                    Navigate.visit(dlpURL);
                }
            } else if(expectation != null && bloomingdales() && (expectation.equalsIgnoreCase("pagination") ||expectation.equalsIgnoreCase("designer facet") || expectation.equalsIgnoreCase("department facet") || expectation.equalsIgnoreCase("item type")|| expectation.equalsIgnoreCase("price facet")||expectation.equalsIgnoreCase("sales & offers facet")|| expectation.equalsIgnoreCase("color facet") || expectation.equalsIgnoreCase("size facet"))){
                preSelectedFacet = true;
                if(mode.equalsIgnoreCase("registry")){
                    String dlpURL = RunConfig.url + "/buy/ralph-lauren/Productsperpage,Sortby/90,ORIGINAL?id=1001352&brand=Ralph+Lauren&mode=wedding&cm_sp=shop_by_brand-_-ALL%20BRANDS-_-Ralph%20Lauren";
                    if(expectation.equalsIgnoreCase("designer facet")){
                        dlpURL = RunConfig.url + "/buy/plates?mode=wedding";
                    }
                    Navigate.visit(dlpURL);
                }else {
                    String dlpURL = RunConfig.url + "/buy/adidas?id=1001351&brand=Adidas&cm_sp=shop_by_brand-_-ALL%20DESIGNERS-_-Adidas";
                    if(expectation.equalsIgnoreCase("size facet")){
                        dlpURL = RunConfig.url + "/buy/levis";
                        searchKeyword = "levis";
                    }
                    Navigate.visit(dlpURL);
                }
             }
        } catch (Exception e) {
            Assert.fail("Error Navigating to DLP page in " + mode + "\r\n" + e.getMessage());
        }
        resumePageHangWatchDog();
        logger.info("Navigated to Dynamic Landing Page on " + shoppingMode + " mode");
    }


    @Then("^I verify left navigation link names shown as following:$")
    public void iVerifyLeftNavigationLinkNamesShownAsFollowing(List<HashMap<String, String>> left_nav) throws Throwable {
        List<HashMap<String, String>> failedData = new ArrayList<>();
        for (Map left_nav_link : left_nav) {
            Wait.forPageReady();
            Clicks.click("navigation." + left_nav_link.get("link_name").toString());
            Wait.forPageReady();
            List<String> expectedLinks = Arrays.asList(left_nav_link.get("left_nav_links").toString().split(", "));
            String[] all_left_nav_elements;
            if (left_nav_link.get("link_name").equals("goto_order_status"))
                all_left_nav_elements = Elements.findElement("navigation.left_nav_container_order_history").getText().split("\n");
            else
                all_left_nav_elements = Elements.findElement("navigation.left_nav_container").getText().split("\n");

            List<String> exclusion_list = new ArrayList<String>(Arrays.asList("ORDERS", "Furniture & Mattress Status", "MACY'S CREDIT CARD",
                    "Cardholder Benefits", "GIFT CARDS", "Gift Card Balance", "Learn More & Apply", "My Account", "MACY'S CARD", "my account", "Registry", "Reviews", "Subscriptions", "Star Rewards"));
            List<String> actualLeftNav = new ArrayList<String>(Arrays.asList(all_left_nav_elements));

            actualLeftNav.removeAll(exclusion_list);

            Collections.sort(actualLeftNav);
            Collections.sort(expectedLinks);
            if (!actualLeftNav.equals(expectedLinks)) {
                HashMap<String, String> one = new HashMap<>();
                one.put("pageName", left_nav_link.get("link_name").toString());
                one.put("actualLeftLinks", actualLeftNav.toString());
                failedData.add(one);
            }
        }
        Assert.assertTrue("ERROR - APP : Left navigation does not match with given data", failedData.isEmpty());
        logger.info("Left navigation link names are shown without my text");
    }

    @Then("^I should see bread crumb is updated in following pages as following:$")
    public void iShouldSeeBreadCrumbIsUpdatedInFollowingPagesAsFollowing(List<HashMap<String, String>> pages_and_breadcrumbs) throws Throwable {
        List<HashMap<String, String>> failedData = new ArrayList<>();
        for (Map page_breadcrumb_data : pages_and_breadcrumbs) {
            Wait.forPageReady();
            Clicks.click("navigation." + page_breadcrumb_data.get("link_name"));
            Wait.forPageReady();

            String actualText = Elements.getText("my_profile.breadcrumb").replaceAll("\n", "");
            String expectedText = (String) page_breadcrumb_data.get("bread_crumb");
            if (!actualText.equals(expectedText)) {
                HashMap<String, String> one = new HashMap<>();
                one.put("pageName", (String) page_breadcrumb_data.get("link_name"));
                one.put("actualText", actualText);
                failedData.add(one);
            }
        }
        Assert.assertTrue("ERROR - APP : Breadcrumb does not match in few pages", failedData.isEmpty());
        logger.info("Verified that breadcrumb is updated in given pages.");
    }

    public static void navigateToHNFRedesign() {
        String extraParam = RunConfig.getEnvOrExParam("hnf_redesign");
        if (extraParam != null && extraParam.equalsIgnoreCase("true")) {
            try {
                HeaderAndFooter.iNavigateToViewtypeInNewHeaderFooterExperience("HnF Clean");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Refreshes the page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I refresh current page$")
    public void iRefreshCurrentPage() throws Throwable {
        Navigate.browserRefresh();
        Wait.forPageReady();
    }

    @When("^I navigate with order history legacy url$")
    public void iNavigateWithOrderHistoryLegacyUrl() throws Throwable {
        String host=WebDriverManager.getCurrentUrl();
        if(host.contains("/?utm_campaign=disable_all"))
            host=host.replaceAll(Pattern.quote("/?utm_campaign=disable_all"), "");
        String shopapp_redirect_url=host+"/service/order/index.ognc";
        Navigate.visit(shopapp_redirect_url);
        Wait.forPageReady();
        logger.info("redirected to "+shopapp_redirect_url);
    }

    @When("^I navigate with legacy url using order number \"([^\"]*)\", email address \"([^\"]*)\", area code \"([^\"]*)\", exchange number \"([^\"]*)\", and subscriber number \"([^\"]*)\"$")
    public void iNavigateWithLegacyUrlUsingOrderNumberEmailAddressAreaCodeExchangeNumberAndSubscriberNumber(String order_number, String email, String area, String exchange, String subscriber) throws Throwable {
        order_number= Utils.getOrderNumber(order_number);
        String host=WebDriverManager.getCurrentUrl();
        if(host.contains("/?utm_campaign=disable_all"))
            host=host.replaceAll(Pattern.quote("?utm_campaign=disable_all"), "");
        String redirect_url = host+"service/order/index.ognc?QueryType=history";
        if(!order_number.equals(""))
            redirect_url += "&OrderNumber=" + order_number;
        if(!email.equals("")) {
            redirect_url += "&EmailAddress=" + email + "&isEmailSelected=Y";
        }
        else {
            redirect_url += "&areacode=" + area + "&exchangeNbr=" + exchange + "&subscriberNbr=" + subscriber + "&isEmailSelected=N";
        }
        Navigate.visit(redirect_url);
        logger.info("Navigated to "+redirect_url);
    }

    @When("^I click registry star rewards from left menu$")
    public void iClickRegistryStarRewardsFromLeftMenu() throws Throwable {
        Wait.forPageReady();
        Clicks.click("dsv_registry.registry_starreward");
    }
}
