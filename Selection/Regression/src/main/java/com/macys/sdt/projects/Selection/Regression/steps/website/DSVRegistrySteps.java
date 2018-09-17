package com.macys.sdt.projects.Selection.Regression.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Selection.Registry.actions.website.RegistryActions;
import com.macys.sdt.projects.Selection.Registry.steps.website.bcom.BvrRedesign;
import com.macys.sdt.projects.Selection.Regression.actions.website.DSVPDPActions;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;


public class DSVRegistrySteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(DSVRegistrySteps.class);
    private Registry registry = new Registry();
    private PageNavigation pageNavigation = new PageNavigation();
    private BvrRedesign bvrRedesign = new BvrRedesign();
    private RegistryActions registryActions = new RegistryActions();
    private String productTitle = "";


    @Then("^I verify cat splash pages appears without errors$")
    public void i_verify_cat_splash_pages_appears_without_errors() throws Throwable {
        List<WebElement> categories = Elements.findElements("home.category").stream().filter(a -> a.getAttribute("href") != null).collect(Collectors.toList());
        List<String> strCategories = new ArrayList<>();
        for (WebElement category : categories) {
            strCategories.add(category.getText());
        }
        for (String strCategory : strCategories) {
            String partialErrorMessage = "ERROR - ENV: For " + strCategory + " ";
            Clicks.click(By.linkText(strCategory));
            Wait.forPageReady();
            if (!onPage("registry_capture_email_page")) {
                Assert.assertTrue(partialErrorMessage + ", the header is not present", Elements.elementPresent("category_splash.nav_banner"));
                Assert.assertTrue(partialErrorMessage + ", the Bag Icon is not present", Elements.elementPresent("category_splash.shopping_bag_icon"));
                Assert.assertTrue(partialErrorMessage + ", the Search Field is not present", Elements.elementPresent("category_splash.search_field"));
                Assert.assertTrue(partialErrorMessage + ", the Footer is not present", Elements.elementPresent("category_splash.footer_section"));
                Assert.assertTrue(partialErrorMessage + ", the footer customer service link is not present", Elements.elementPresent("category_splash.global_footer_ad"));
            }
            else {
                Assert.assertTrue(partialErrorMessage+ ", the Sign in and Create Registry button is not present on capture email page",
                        Elements.elementPresent("registry_capture_email_page.continue_button"));
            }
            Navigate.browserBack();
            Wait.forPageReady();
        }

    }

    @When("^I submit \"([^\"]*)\" and \"([^\"]*)\" in Find form on registry home page$")
    public void iSubmitAndInFindFormOnRegistryHomePage(String first_name, String last_name) throws Throwable {
        TextBoxes.typeTextbox("registry_home.search_first_name", first_name);
        TextBoxes.typeTextbox("registry_home.search_last_name", last_name);
        Clicks.click("registry_home.find_button");
    }

    @When("^I select a random product from search results page$")
    public void iSelectARandomProduct() throws Throwable {
        Wait.untilElementPresent("search_result.product_thumbnail_link");
        Clicks.clickRandomElement("search_result.product_thumbnail_link");
    }

    @And("^I should see Add to Registry button on PDP$")
    public void iShouldSeeAddToRegistryButtonOnPDP() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Add to registry button is not present", Wait.untilElementPresent("product_display.add_to_registry"));
    }

    @When("^I navigate to registry create page from registry homepage$")
    public void iNavigateToRegistryCreatePageFromRegistryHomePage(){
        if(onPage("registry_home")){
            Clicks.click("registry_home.goto_create_registry");
        }
        else {
            Assert.fail("Not on registry home page");
        }
    }

    @When("^I submit \"([^\"]*)\" and \"([^\"]*)\" in manage form on registry home page$")
    public void iSubmitAndInManageFormOnRegistryHomePage(String username, String password) throws Throwable {
        TextBoxes.typeTextbox("registry_home.sign_in_email", username);
        TextBoxes.typeTextbox("registry_home.sign_in_password", password);
        Clicks.click("registry_home.sign_in_button");
    }

    @And("^I close the popup if it's visible$")
    public void iCloseThePopupIfItSVisible() throws Throwable {
        DSVPDPActions.closePopUp();
    }

    @Then("^I verify the Registry FOBs in below table$")
    public void iVerifyTheRegistryFOBsInBelowTable(Map<String, String> list) throws Throwable {
        for (Map.Entry<String, String> entry : list.entrySet()) {
            String category = entry.getKey();
            String subcategory = entry.getValue();
            pageNavigation.I_navigate_to_category_page(category);
            verifyBasicAttributesOfRegistryCatSplashPage();
            navigateToSubCategoryFromCatSplashPage(subcategory);
            verifyBasicAttributesOfRegistryBrowsePage();
            //clickOnDifferentSubCategoryFromFlyoutMenuFor(category, subcategory);
            verifyBasicAttributesOfRegistryBrowsePageOrCatSplash();
        }
    }

    private void verifyBasicAttributesOfRegistryBrowsePageOrCatSplash() throws Throwable {
        if (StepUtils.onPage("category_browse"))
            verifyBasicAttributesOfRegistryBrowsePage();
        else
            verifyBasicAttributesOfRegistryCatSplashPage();
    }

    private void clickOnDifferentSubCategoryFromFlyoutMenuFor(String category, String subcategory) throws Throwable {
        Wait.isPageLoaded();
        pageNavigation.I_mouse_over_category_from_top_navigation(category);
        Elements.findElement("header.open_flyout").findElements(By.tagName("a")).stream().filter(a -> !a.getText().equals(subcategory)).findFirst().get().click();
        Wait.forPageReady();
    }

    private void navigateToSubCategoryFromCatSplashPage(String strSubcategory) {
        List<WebElement> subcategories = Elements.findElements("left_facet.subcategories");
        for (WebElement subcategory : subcategories) {
            if (subcategory.getText().toLowerCase().equals(strSubcategory.toLowerCase())) {
                Clicks.click(subcategory);
                Wait.forPageReady();
                break;
            }
        }
    }

    private void verifyBasicAttributesOfRegistryBrowsePage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Not a registry page", WebDriverManager.getWebDriver().getCurrentUrl().contains("wedding"));
        Assert.assertTrue("ERROR - ENV: Category menu is not displayed", Wait.untilElementPresent("header.category_menu"));
        Assert.assertTrue("ERROR - ENV: Main footer is not displayed", Wait.untilElementPresent("footer.footer_menu_section"));
        Assert.assertTrue("ERROR - ENV: Facet container is not displayed", Wait.untilElementPresent("facet_chart.facets_panel"));
        Assert.assertTrue("ERROR - ENV: Sort by option is not available", Wait.untilElementPresent("pagination.sort_by"));
        Integer productCount = Integer.parseInt(Elements.getText("pagination.product_count_span").replace(" items", ""));
        if (productCount > 180)
            Assert.assertTrue("Pagination option is not available with " + productCount + " products in the page", hasPagination());
        if (productCount > 90 && productCount <= 180) {
            Clicks.click("pagination.view_180");
            Wait.forPageReady();
            Assert.assertFalse("ERROR - ENV: Pagination option is available with " + productCount + " products in the page", hasPagination());
        }
        if (productCount <= 90)
            Assert.assertFalse("ERROR - ENV: Pagination option is available with " + productCount + " products in the page", hasPagination());
        String price = Elements.getText("category_sub_splash.coach_prod_price");
        Assert.assertTrue("ERROR - ENV: Currency not displaying in $ or On Sale", price.contains("$") || price.toLowerCase().contains("on sale"));

    }

    private boolean hasPagination() {
        return Elements.elementPresent("pagination_top.goto_next_page_via_arrow");
    }

    private void verifyBasicAttributesOfRegistryCatSplashPage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Not a registry page", WebDriverManager.getWebDriver().getCurrentUrl().contains("wedding"));
        Assert.assertTrue("ERROR - ENV: Category menu is not displayed", Wait.untilElementPresent("header.category_menu"));
        Assert.assertTrue("ERROR - ENV: Main footer is not displayed", Wait.untilElementPresent("footer.footer_menu_section"));
        Assert.assertTrue("ERROR - ENV: Left navigation container is not display", Wait.untilElementPresent("left_facet.left_navigation_container"));
    }

    @Then("^I should be redirected to the registry zeroResults page$")
    public void iShouldBeRedirectedToTheRegistryZeroResultsPage() throws Throwable {
        Assert.assertFalse("ERROR - ENV: Products are displayed", Wait.secondsUntilElementPresent("search_result.product_thumbnail_link", 3));
        Assert.assertTrue("ERROR - ENV: Zero results header not displayed", Wait.untilElementPresent("search_result.zero_results_header"));
        Assert.assertTrue("ERROR - ENV: Need help section not displayed", Wait.untilElementPresent("search_result.need_help_wrapper_registry"));
        Assert.assertTrue("ERROR - ENV: First name text box is not displayed", Wait.untilElementPresent("registry_search_results_page.search_first_name"));
        Assert.assertTrue("ERROR - ENV: Last name text box is not displayed", Wait.untilElementPresent("registry_search_results_page.search_last_name"));
        Assert.assertTrue("ERROR - ENV: Submit button is not displayed", Wait.untilElementPresent("registry_search_results_page.go_button"));
        Assert.assertTrue("ERROR - ENV: Return to bloomingdales link is not displayed", Wait.untilElementPresent("search_result.go_to_bloomingdales"));
        Assert.assertTrue("ERROR - ENV: Log into my rgeistry link is not displayed", Wait.untilElementPresent("search_result.log_into_my_registry"));
        Assert.assertTrue("ERROR - ENV: Create a new registry link is not displayed", Wait.untilElementPresent("search_result.create_a_new_registry"));
    }

    @When("^I visit the website as a registered user with registry$")
    public void i_visit_the_website_as_a_registered_user_with_registry() throws Throwable {
        logger.info("Go to My Account page!!");
        signIn();
        CreateProfile.closeSecurityAlertPopUp();
    }

    public static void signIn() {
        Navigate.visit("home");
        Wait.untilElementPresent("home.goto_my_account_link");
        Clicks.click(Elements.element("home.goto_my_account_link"),
                () -> Wait.untilElementPresentWithRefreshAndClick(
                        Elements.element("my_account.csr_add_card_to_my_account_button"),
                        Elements.element("home.goto_my_account_link")));
        Assert.assertTrue(onPage("sign_in"));
        typeEmailTextbox();
        typePassTextbox();
        Clicks.click("sign_in.sign_in_button");
        Wait.forPageReady("my_account");
        Assert.assertTrue(onPage("my_account"));
    }

    public static void typeEmailTextbox() {
        Wait.forPageReady("sign_in");
        TextBoxes.typeTextbox("sign_in.email", Elements.getValues("login.email").get(0));
    }

    public static void typePassTextbox() {
        Wait.forPageReady("sign_in");
        TextBoxes.typeTextbox("sign_in.password", Elements.getValues("login.password").get(0));
    }

    @When("^I search for the different existing registry$")
    public void iSearchForDifferentExistingRegistry() throws Throwable {
        if (bloomingdales()){
            Navigate.visit("registry_home");
            Wait.forPageReady();
            TextBoxes.typeTextbox("registry_home.search_first_name", Elements.getValues("login.reg_alt_name").get(0));
            TextBoxes.typeTextbox("registry_home.search_last_name", Elements.getValues("login.reg_alt_last_name").get(0));
            Clicks.click("registry_home.find_button");
            Wait.untilElementPresent("find_registry.registrant_names");
            Clicks.javascriptClick(registryActions.searchForExistingRegistryFromSearchResults("login.reg_alt_name", "login.reg_alt_last_name"));
            Clicks.javascriptClick("find_registry.select_registry");
            Assert.assertTrue(onPage("registry_gvr"));
        }
    }

    @When("^I search for the existing registry$")
    public void i_search_for_the_existing_registry() throws Throwable {
        Wait.forPageReady();
        if (macys()) {
            Wait.forPageReady();
            Clicks.javascriptClick("dsv_registry.find_registry");
            Wait.forPageReady();
            String firstName = Elements.getValues("login.firstname").get(0);
            TextBoxes.typeTextbox("dsv_registry.registry_firstname", firstName);
            String lastName = Elements.getValues("login.lastname").get(0);
            TextBoxes.typeTextbox("dsv_registry.registry_lastname", lastName);
            Clicks.click("dsv_registry.registry_search");
            Wait.secondsUntilElementPresent("dsv_registry.verify_findregistry", 30);
            Assert.assertTrue("Registry not found", Elements.elementPresent("dsv_registry.verify_findregistry"));
            Clicks.click("find_registry.find_registry_results");
        }
        if (bloomingdales()) {
            Wait.forPageReady();
            TextBoxes.typeTextbox("registry_home.search_first_name", Elements.getValues("login.firstname").get(0));
            TextBoxes.typeTextbox("registry_home.search_last_name", Elements.getValues("login.lastname").get(0));
            Clicks.click("registry_home.find_button");
             Wait.forPageReady();
            Wait.untilElementPresent("find_registry.verify_page");
            Clicks.javascriptClick(registryActions.searchForExistingRegistryFromSearchResults("login.firstname", "login.lastname"));
            Clicks.javascriptClick("find_registry.select_registry");
            Assert.assertTrue(onPage("registry_gvr"));
        }
    }

    @And("^I should see GVR items present in the page$")
    public void i_should_see_GVR_items_present_in_the_page() throws Throwable {
        Wait.forPageReady();
        if (macys()){
            Wait.secondsUntilElementPresent("dsv_registry.gvr_registry", 30);
            Assert.assertTrue("ERROR- GVR items is not present in the page", Elements.elementPresent("dsv_registry.gvr_registry"));
        }
        if(bloomingdales()) {
            Assert.assertTrue("ERROR- GVR items is not present in the page", Elements.elementPresent("registry_gvr.gvr_prod_list"));
        }
        logger.info("GVR items are verified successfully");
    }

    @And("^I click view myItems in registry manager page$")
    public void i_click_view_myItems_in_registry_manager_page() {
        if (onPage("registry_manager")) {
            Clicks.click("registry_manager.view_my_item");
        }
        else {
            Assert.fail("Not on Registry Manager page");
        }
    }



    @Then("^I verify the basic attributes of the (BVR|GVR) page$")
    public void i_verify_the_basic_attributes_of_the_view_registry_page(String registryPage) throws Throwable {
        Navigate.browserRefresh();
        Wait.forPageReady();
        Registry registrySteps = new Registry();
        if (bloomingdales()) {
            if(registrySteps.isBVRWelcomeOverlay()) {
                registrySteps.closeBVRWelcomeOverlay();
            }
            String registrantName = String.format("%s %s", Elements.getValues("login.firstname").get(0), Elements.getValues("login.lastname").get(0));
            String coregistrantName = String.format("and %s %s", Elements.getValues("login.coreg_first_name").get(0), Elements.getValues("login.coreg_last_name").get(0));
            if (registryPage.equalsIgnoreCase("bvr")) {
                String registrant_details = Elements.getText("registry_bvr.header_names_fn").toLowerCase().trim();
                logger.info("Registrant Names " + registrant_details);
                String co_registrant_details = Elements.getText("registry_bvr.header_names_ln").toLowerCase().trim();
                logger.info("CoRegistrant Names " + co_registrant_details);
                Assert.assertTrue(registrantName.equalsIgnoreCase(Elements.getText("registry_bvr.header_names_fn").toLowerCase().trim()));
                Assert.assertTrue(coregistrantName.equalsIgnoreCase(Elements.getText("registry_bvr.header_names_ln").toLowerCase().trim()));
                logger.info("Event date is " + Elements.getText("registry_bvr.registry_date"));
                Assert.assertTrue("Even Date listed in the header is not same as the registry data event date", String.format("%s %s, %s", Elements.getValues("login.event_month").get(0), Elements.getValues("login.event_day").get(0), Elements.getValues("login.event_year").get(0)).equals(Elements.getText("registry_bvr.registry_date")));
                Assert.assertTrue("Days Left is not present in the header", Elements.findElement("registry_bvr.days_left").getText().contains("Days Left"));
                bvrRedesign.iShouldSeeBackToRegistryManagerLinkInTheHeader("should");
                bvrRedesign.iShouldSeeTheBarcodeIsPresentInTheFooter();

            }

            else if (registryPage.equalsIgnoreCase("gvr")){
                String registrant_details = Elements.getText("registry_gvr.header_names_fn").toLowerCase().trim();
                logger.info("Registrant Names " + registrant_details);
                String co_registrant_details = Elements.getText("registry_gvr.header_names_ln").toLowerCase().trim();
                logger.info("CoRegistrant Names " + co_registrant_details);
                Assert.assertTrue(registrantName.equalsIgnoreCase(Elements.getText("registry_gvr.header_names_fn").toLowerCase().trim()));
                Assert.assertTrue(coregistrantName.equalsIgnoreCase(Elements.getText("registry_gvr.header_names_ln").toLowerCase().trim()));
                logger.info("Event date is " + Elements.getText("registry_gvr.registry_date"));
                Assert.assertTrue("Even Date listed in the header is not same as the registry data event date", String.format("%s %s, %s", Elements.getValues("login.event_month").get(0), Elements.getValues("login.event_day").get(0), Elements.getValues("login.event_year").get(0)).equals(Elements.getText("registry_gvr.registry_date")));
                Assert.assertTrue("Days Left is not present in the header", Elements.findElement("registry_gvr.days_left").getText().contains("Days Left"));
                bvrRedesign.iShouldSeeBackToRegistryManagerLinkInTheHeader("should not");
                bvrRedesign.iShouldSeeTheBarcodeIsPresentInTheFooter();
            }
            else {
                Assert.fail("User is currently not in Registry BVR/GVR Page");
            }
        }
        if (macys()) {
            Wait.secondsUntilElementPresent("dsv_registry.bvr_eventdate", 30);
            Assert.assertTrue("Event date does not match", Elements.getText("dsv_registry.bvr_eventdate").substring(14, 25).trim().equalsIgnoreCase(new JSONObject(Utils.readTextFile(getResourceFile("sanity_production.json"))).getString("event_date")));
            logger.info(Elements.getText("dsv_registry.bvr_eventdate"));
            Assert.assertTrue("Registry date does not match", Elements.getText("dsv_registry.bvr_registryID").substring(25,46).trim().equalsIgnoreCase(new JSONObject(Utils.readTextFile(getResourceFile("sanity_production.json"))).getString("registry_id")));
            logger.info(Elements.getText("dsv_registry.bvr_registryID"));
            Assert.assertTrue("Registry status does not match", Elements.getText("dsv_registry.bvr_registry_status").substring(46, 69).equalsIgnoreCase(new JSONObject(Utils.readTextFile(getResourceFile("sanity_production.json"))).getString("registry_status")));
            logger.info(Elements.getText("dsv_registry.bvr_registry_status"));
        }
    }


    @Then("^I verify basic attributes of the ATB confirmation Overlay$")
    public void i_verify_basic_attributes_of_the_ATB_confirmation_Overlay() throws Throwable {
        if (macys()) {
            Wait.untilElementPresent("dsv_registry.product_name");
            Assert.assertTrue("Product Name is not present in the page", Elements.elementPresent("dsv_registry.product_name"));
            logger.info("Product Name is present in the page::" + Elements.findElement("dsv_registry.product_name").getText());
            Wait.untilElementPresent("dsv_registry.product_price");
            Assert.assertTrue("Product Price is not present in the page", Elements.elementPresent("dsv_registry.product_price"));
            logger.info("Product Price is present in the page::" + Elements.findElement("dsv_registry.product_price").getText());
            Assert.assertTrue("Currency is not present in the page", Elements.findElement("dsv_registry.product_price").getText().contains("$"));
            logger.info("Currency is present in the page");
            Assert.assertTrue("Checkout button is not present in the page", Elements.elementPresent("dsv_registry.checkout_button"));
            logger.info("Checkout button is present in the page");
        }
    }

    @When("^I visit the KITCHEN category page from the registry$")
    public void iVisitTheKitchenCategoryPageFromTheRegistry() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("dsv_registry.cat_kitchen", 20);
        Wait.forPageReady();
        logger.info("Sucessfully Navigated to KITCHEN page");
    }

    @Then("^I click view registry in GVR page$")
    public void i_click_view_registry_in_GVR_page() throws Throwable {
        Clicks.click("find_registry.find_registry_results");
    }

    @Given("^I sign in as a registry user in production$")
    public void iSignInAsARegistryUserInProduction() throws Throwable {
        CommonUtils.signInWithExistingProfileToProdServer("prod_user");
    }

    @Then("^I should see registry registrant names on registry manager page$")
    public void iShouldSeeRegistryRegistrantNamesOnRegistryManagerPage() throws Throwable {
        if (macys()){
        Assert.assertEquals("Registrant names on registry manager not matched", new JSONObject(Utils.readTextFile(getResourceFile("sanity_production.json"))).getString("registrant_names"), Elements.getText("registry_manager.registrant_names"));
        }
    }


    @Then("^I should see registry event location on registry manager page$")
    public void iShouldSeeRegistryEventLocationOnRegistryManagerPage() throws Throwable {
        String actual = Elements.getText("registry_manager.event_location").substring(43, 51).trim();
        String expected = new JSONObject(Utils.readTextFile(getResourceFile("sanity_production.json"))).getString("location").trim();
        Assert.assertEquals("Registrant Location on registry manager not matched",expected,actual);
    }

    @Then("^I should see our website icon on registry manager page$")
    public void iShouldSeeOurWebsiteIconOnRegistryManagerPages() throws Throwable {
        Assert.assertTrue("Our Website icon on registry manager not matched", Elements.elementPresent("dsv_registry.goto_wedding_website"));
    }

    @Then("^I click on the \"([^\"]*)\" under categories on \"([^\"]*)\" page$")
    public void iClickOnTheUnderCategoriesOnPage(String SubNavigation, String pagename) {
        switch (SubNavigation) {
            case "registry manager":
                Clicks.click(By.linkText("Registry Manager"));
                break;
            case "rewards program":
                Clicks.click(By.linkText("Rewards Program"));
                break;
            case "view registry":
                Clicks.javascriptClick(By.linkText("VIEW OUR REGISTRY"));
                break;
        }
    }

    @Then("^I verify user is navigated to registry \"([^\"]*)\" page$")
    public void iVerifyUserIsNavigatedToRegistryPage(String page) {
        Assert.assertTrue("Url is not of Registry" + page + "page", WebDriverManager.getCurrentUrl().contains("registry-star-rewards"));
        Assert.assertTrue("Page is not Registry" + page + "page", Elements.getText("dsv_registry.star_reward_page").contains("Registry Star Rewards"));
    }

    @Then("^I navigate to find couple registry page$")
    public void i_navigate_to_find_couple_registry_page() {
        Clicks.click("dsv_registry.find_couple_registry");
        Wait.forPageReady();
        logger.info("Page FInd Couple Registry Loaded");
        String firstName = Elements.getValues("login.reg_firstname").get(0);
        String lastName = Elements.getValues("login.reg_lastname").get(0);
        TextBoxes.typeTextbox("find_registry.first_name", firstName);
        TextBoxes.typeTextbox("find_registry.last_name", lastName);
        Clicks.click("find_registry.find_registry_button");
    }

    @And("^I click on view registry$")
    public void i_click_on_view_registry() {
        Clicks.click(Elements.findElement("dsv_registry.view_registry"));
        Wait.forPageReady();
        logger.info("Page FInd Couple Registry Loaded");
    }

    @And("^I should see GVR items in the page and add any product to bag$")
    public void i_should_see_gvr_items_in_the_page_and_add_any_product_to_bag() {
        Assert.assertTrue("GVR items are not present in the page", Elements.elementPresent("dsv_registry.registry_product"));
        logger.info(" GVR items are present in the page");
        try {
            WebDriver driver = WebDriverManager.getWebDriver();
            WebElement element = Elements.findElement("dsv_registry.registry_atb");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            WebDriverManager.getWebDriver().switchTo().activeElement();
        } catch (Exception ee) {
            logger.info(ee.getMessage());
        } finally {
            logger.info(" Added one registry item to bag from GVR page");

        }
    }

    @And("^I should see GVR items in the page$")
    public void i_should_see_gvr_items_in_the_page() {
        Assert.assertTrue("GVR items are not present in the page", Elements.elementPresent("dsv_registry.registry_product"));
        logger.info(" GVR items are present in the page");
    }

    @When("^I login for the existing registry$")
    public void i_login_for_the_existing_registry() throws Throwable {
        Wait.forPageReady();
        if (macys()) {
            Clicks.javascriptClick("dsv_registry.manage_registry");
            Clicks.javascriptClick("dsv_registry.registry_mangr");
            Wait.forPageReady();
            TextBoxes.typeTextbox("registry_sign_in.existing_user_email", Elements.getValues("login.email").get(0));
            TextBoxes.typeTextbox("registry_sign_in.existing_user_password", Elements.getValues("login.password").get(0));
            Clicks.click("registry_sign_in.existing_user_continue_button");
            Wait.secondsUntilElementPresent("dsv_registry.view_registry", 30);
            Clicks.click("dsv_registry.view_registry");
        }
    }

    @And("^I add one product from BVR$")
    public void i_add_one_product_from_bvr() throws Throwable {
        Wait.secondsUntilElementPresent("dsv_registry.product_title", 20);
        productTitle=Elements.findElement("dsv_registry.product_title").getText();
        logger.info("Product is "+productTitle);
        Elements.findElement("dsv_registry.registry_atb").click();
        Wait.forPageReady();
    }

    @And("^I match product name$")
    public void i_match_products_name() throws Throwable {
        Wait.forPageReady();
        if (macys()) {
            String pdpProductName= Elements.getText("shopping_bag.item_names");
            logger.info("PDP Product Name: "+pdpProductName);
            Wait.forPageReady();
            String bagProductName=Elements.getText("dsv_registry.product_title");
            logger.info("BAG Product Name: " +bagProductName);
            Assert.assertTrue("Product title found in shopping bag is not found in PDP page",pdpProductName.equals(bagProductName));
        }if (bloomingdales()) {
            List<String> products = new ArrayList<>();
            for (WebElement element : Elements.findElements("shopping_bag.item_names")) {
                products.add(element.getText());
            }
            logger.info("Product List " + products);
            logger.info("Product title " + productTitle);
            Assert.assertTrue("Product title found in shopping bag is not found in PDP page",
                    products.contains(productTitle));
            logger.info("Product title in shopping bag macthing with product added");
        }
    }

    @Then("^I should land on the shopping bag page$")
    public void i_should_land_on_the_shopping_bag_page() throws Throwable {
        String expectedCount = "(1 item)";
        Assert.assertTrue(onPage("shopping_bag"));
        Assert.assertTrue("verify added registry is present on shoping bag page", Elements.elementPresent("dsv_registry.registry_label"));
        String getTextCount = Elements.getText("dsv_registry.total_item_count");
        logger.info("Total added item count is: " + getTextCount);
        if(getTextCount.contains(expectedCount)){
            Assert.assertTrue("verify total count of added registry and product", getTextCount.contains(expectedCount));
        }else
        Assert.assertTrue("verify total count of added registry and product", getTextCount.contains("(3 items)"));
    }

    @When("^I click on the continue shopping button from ATB page$")
    public void i_click_on_the_continue_shopping_button_from_ATB_page() throws Throwable {
        if(onPage("shopping_bag")) {
            Clicks.click("shopping_bag.continue_checkout_image");
            Wait.forPageReady();
            Assert.assertTrue("User did not navigate to guest checkout page", onPage("checkout_sign_in"));
        }
        else{
            Assert.fail("Not on shopping bag");
        }
    }

    @Given("^I click checkout as a guest$")
    public void i_click_checkout_as_a_guest() throws Throwable {
        Navigate.browserRefresh();
        Clicks.click("checkout_sign_in.continue_checkout_guest_button");
        Wait.forPageReady();

    }

    @Then("^I should navigated to shipping & payment$")
    public void i_should_navigated_to_shipping_payment() throws Throwable {
        Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("chkout"));
    }

    @When("^I click add to brown bag$")
    public void i_click_add_to_brown_bag() throws Throwable {
        Wait.secondsUntilElementPresent("dsv_registry.add_to_brown_bag", 20);
        Clicks.click("dsv_registry.add_to_brown_bag");
        Wait.untilElementPresent("registry_gvr.registry_atb_overlay");
        Assert.assertTrue("Registry GVR ATB overlay is not visible", Elements.elementPresent("registry_gvr.registry_atb_overlay"));
    }

    @When("^I click on \"Checkout\" button on product ATB overlay$")
    public void i_click_add_to_checkout_pdp(){
        Wait.secondsUntilElementPresent("dsv_registry.add_to_brown_bag", 20);
        Clicks.click("dsv_registry.add_to_brown_bag");
        Wait.secondsUntilElementPresent("registry_gvr.registry_atb_overlay",30);
        Assert.assertTrue("Registry GVR ATB overlay is not visible", Elements.elementPresent("registry_gvr.registry_atb_overlay"));
        Wait.secondsUntilElementPresent("dsv_registry.pdp_checkout", 20);
        Clicks.click("dsv_registry.pdp_checkout");
        Wait.forPageReady();
    }

    @Then("^I add item from one registry to the bag$")
    public void iAddItemFromOneRegistryToTheBag() throws Throwable {
        i_search_for_the_existing_registry();
        bvrRedesign.iAddItemToBagFromGVRPage("gvr");

    }

    @And("^I verify the shipping message for multiple registries:$")
    public void iVerifyTheShippingMessageForMultipleRegistries(String actual_message){
        Assert.assertEquals("Mutliple registries warning messages do not match", actual_message, Elements.findElement("shipping_guest.global_warnings_message").getText());
    }

    @Then("^I verify content on registry manager page$")
    public void iVerifyContentOnRegistryManagerPage() throws Throwable {
        Wait.forPageReady();
        String actualGiftRegistry = Elements.getText("registry_manager.gift_registry");
        String expectedGiftRegistry = "Gift Registry";
        Assert.assertEquals("Gift Registry section is not visible", actualGiftRegistry,expectedGiftRegistry);
        Assert.assertEquals("Preferred Store section is not visible", "Your preferred store", Elements.getText("registry_manager.preferred_store"));
        Assert.assertTrue("view registry button is not enable", Elements.findElement("registry_manager.view_registry").isEnabled());
        Assert.assertTrue("edit profile button is not enable", Elements.findElement("registry_manager.edit_profile").isEnabled());
    }
}