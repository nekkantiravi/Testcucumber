package com.macys.sdt.projects.Architecture.MSH.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.*;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.steps.website.*;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.TestUsers.getRandomProduct;
import static com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.getAllProductDetails;
import static com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.updateQuantity;


public class MSHSmoke extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
    private Registry registry = new Registry();
    private List<String> productIds;
    public static int productCount;
    private String debugValue = LocalTime.now().toString().replace(":","").replace(".","");
    private String userEmail = "mshgslb@test.com";
    private String userPassword = "Msh@1234";
    public static String facetName;
    public static String selectedFacetItem;
    public static WebElement facetItem;
    private static final Logger log = LoggerFactory.getLogger(MSHSmoke.class);

    /**
     *  Void method used to create dca cookie
     *  @param cookieValue - value of the cookie like DAL or RTP
     */
    public void createDcaCookie(String cookieValue) {
        Cookies.setDcaCookie(cookieValue);
        Assert.assertTrue("ERROR - ENV: DCA cookie is not set as " + cookieValue,
                Cookies.getCookieValue("dca").equals(cookieValue));
        Clicks.clickIfPresent("home.close_edit_popup");
    }

    /**
     *    To check whether page is served from given data center
     *    @param siteName - Name of the data center like DAL or RTP
     */
    @Then("^I verify that page is served from \"([^\"]*)\" DC$")
    public void iVerifyThatPageIsServedFromDC(String siteName) throws Throwable {
        Wait.isPageLoaded();
        if(WebDriverManager.getCurrentUrl().contains("chkout/internationalShipping")) {
            switchToFrame("default");
        }
        String siteNameDisplayed = WebDriverManager.getWebDriver().getPageSource().split("<site>")[1].split("</site>")[0];
        log.info("Page served from: " + siteNameDisplayed + " data center");
        if (!(WebDriverManager.getWebDriver().getPageSource().contains(siteName) || WebDriverManager.getCurrentUrl().contains("debug"))) {
            String value = WebDriverManager.getCurrentUrl().contains("?") ?  "&debug=" : "?&debug=";
            Navigate.visit(WebDriverManager.getCurrentUrl() + value + debugValue);
        }
        Assert.assertTrue("ERROR - APP: Page is not served from " + siteName + " data center",
                WebDriverManager.getWebDriver().getPageSource().contains(siteName));
    }

    /**
     * Visits the web site then creates or logs into a registry
     * or create a registered user
     * for the given cookie Value
     * @param userType - either registry or registered user
     * @param cookieValue - value of the cookie like DAL or RTP
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a (registry|registered) user (with dca cookie as (DAL|RTP)|without dca cookie)$")
    public void iVisitTheWebSiteAsARegisteredUserWithDcaCookieAsDAL(String userType, String condition, String cookieValue) throws Throwable {
        switch (userType) {
            case "registry":
                if (prodEnv()) {
                    loginAsRegistryUserWithGivenUserName(userEmail, userPassword);
                } else {
                    registry.I_visit_the_web_site_as_a_registry_user();
                }
                break;
            case "registered":
                if (prodEnv()) {
                    loginAsRegisteredUserWithGivenUserName(userEmail, userPassword);
                } else {
                    pageNavigation.I_visit_the_web_site_as_a_registered_user();
                }
                break;
        }
        if (condition.contains("with dca cookie as"))
            createDcaCookie(cookieValue);
    }

    /**
     * Navigates to the given page in the given site mode with the given cookie value
     *
     * @param pageType "search results" or "dynamic landing" or "browse" or "brand index"
     * @param mode "domestic" or "iship" or "registry"
     * @param cookieValue - value of the cookie like DAL or RTP
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to (browse|search results|dynamic landing|category splash|pdp) page in (domestic|registry|iship) mode (with DCA cookie as (DAL|RTP)|without dca cookie)$")
    public void iNavigateToCategoryBrowsePageInRootModeWithDCACookieAsDAL(String pageType, String mode, String condition, String cookieValue) throws Throwable {
        switch (mode.toLowerCase()) {
            case "domestic":
                break;
            case "iship":
                pageNavigation.I_navigate_to_international_context_page();
                new ShopAndBrowse().I_change_country_to("a random country");
                break;
            case "registry":
                registry.I_navigate_to_registry_home_page();
                break;
        }
        switch (pageType.toLowerCase()) {
            case "category splash":
                Clicks.clickRandomElement("category_menu.category",
                        (el) -> el.isDisplayed() && !el.getText().matches("BRANDS|DESIGNERS|ACTIVE|THE REGISTRY|GETTING STARTED|WEDDING REGISTRY"));
                break;
            case "pdp":
                new ShopAndBrowse().iNavigateToPdp("member_product and available", null);
                break;
            default:
                pageNavigation.I_navigate_to_page_in_mode(pageType, mode);
                break;
        }
        if (condition.contains("with DCA cookie as"))
            createDcaCookie(cookieValue);
    }

    /**
     * Navigates to PDP page having Product Recommendation panel with the given cookie value
     *
     * @param cookieValue - value of the cookie like DAL or RTP
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate PDP page have PROS panel (with DCA cookie as (DAL|RTP)|without dca cookie)$")
    public void iNavigatePDPPageHavePROSPanelInRootModeWithDCACookieAsDAL(String condition, String cookieValue) throws Throwable {
        new ShopAndBrowse().iNavigateToPdp("with_recommendations", "prod_available");
        if (condition.contains("with DCA cookie as"))
            createDcaCookie(cookieValue);
    }


    /**
     * Selects random facet from the facet panel listed on left navigation
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select any random facet in facet panel$")
    public void iSelectAnyRandomFacetInFacetPanel() throws Throwable {
        productCount = getProductCount();
        productIds = getProductIds();
        Navigate.browserRefresh();
        List<String> facetNames = getAllFacetName();
        facetNames.remove("Pick Up In Store");
        facetNames.remove("Free Pick Up In Store");
        facetNames.remove("Sales & Offers");
        facetNames.remove("Category");
        facetNames.remove("Price");
        facetNames.remove("Size");
        facetNames.remove("Color");
        facetNames = facetNames.stream()
                .filter(e -> !e.contains("Customer"))
                .collect(Collectors.toList());
        Random ran = new Random();
        int index = ran.nextInt(facetNames.size());
        facetName = facetNames.get(index);
        Navigate.browserRefresh();
        if (!LeftFacet.isExpanded(facetName)) {
            LeftFacet.expandFacet(facetName);
        }

        By facetItems = LeftFacet.getFacetItems(facetName);
        List<WebElement> facetvalues=Elements.findElements(facetItems);
        int ItemIndex=ran.nextInt(facetvalues.size());
        facetItem=facetvalues.get(ItemIndex);
        selectedFacetItem=facetItem.getText();
        Clicks.click(facetItem);
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);


        // Clicks.clickRandomElement(LeftFacet.getFacetItems(facetName));
    }

    /**
     * Verified the products are updated as per the selected facet
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify products are filtered per the selected facet$")
    public void iVerifyProductsAreFilteredPerTheSelectedFacet() throws Throwable {
        Wait.isPageLoaded();
        Wait.forLoading("left_facet.loading");
        int currentProductCount = getProductCount();
        Assert.assertTrue("ERROR - APP: Products are not filtered as per the selected facet ",
                currentProductCount <= productCount);
    }

    /**
     * Verified that the products order are modified after performing sort by option
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify that product display order modified$")
    public void iVerifyThatProductDisplayOrderModified() throws Throwable {
        Wait.isPageLoaded();
        Wait.forLoading("left_facet.loading");
        List<String> currentProductIds = getProductIds();
        Assert.assertFalse("ERROR - APP: Product display order not modified ",
                currentProductIds.equals(productIds));
    }

    /**
     * Verify that quick view dialogue is displayed
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see quick peek is displayed$")
    public void iShouldSeeQuickPeekIsDisplayed() throws Throwable {
        Wait.untilElementPresent("quick_view.quick_view_product_dialog");
        Assert.assertTrue("ERROR - APP: Quick peek is not displayed",
                Elements.elementPresent("quick_view.quick_view_product_dialog"));
    }

    /**
     * Method to get the product count on browse/search or dlp page
     *
     * @return product count in page
     */
    private static int getProductCountSearch() {
        Wait.untilElementPresent("category_browse.product_count");
        String productCountText = Elements.getText("category_browse.product_count");
        int prodCount=9;
        Assert.assertFalse("ERROR - APP : Product count element is not displayed on page!!",productCountText.equals("null"));
        if (!(macys())) {
           prodCount = (((productCountText.split(" ")[1]).equals("couldn't")) ? 0 : 99);
        }

        else {
            prodCount=  Integer.parseInt(productCountText.trim());
        }
        return prodCount;
    }

    /**
     Returns All the product ids displayed in the page
     @Example -
     List<String> prodIds = getProductIds();
     prodId #=> "12345",
     "76894874",
     "322323"
     @return List<String> of product ids on the page
     */
    private static List<String> getProductIds() {
        List<String> productIds;
        productIds = new ArrayList<>();
        try {
            Wait.untilElementPresent("search_result.product_thumbnail_wrapper");
            List<WebElement> thumbnails = Elements
                    .findElements(Elements.element("search_result.product_thumbnail_wrapper"));
            productIds = thumbnails
                    .stream()
                    .map(e -> e.getAttribute("id"))
                    .collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            Assert.fail("ERROR - ENV: Product thumbnails are not displayed" + e);
        }
        return productIds;
    }

    /**
     Returns list of all Facet Names displayed.
     @Example:
     List<String> facetNames = getAllFacetName()
     facetNames   #=>    "Special Offers",
     "Size",
     "Brand",
     "Color",
     "Price",
     "Customers' Top Rated"
     @return List<String> a list of facet names displayed on the page.
     */
    public static List<String> getAllFacetName() {
        List<WebElement> facetNames;
        List<String> availableFacetNames = null;
        if (Elements.elementPresent(Elements.element("left_facet.facet_names"))) {
            facetNames = Elements.findElements(Elements.element("left_facet.facet_names"));
            facetNames = facetNames.stream()
                    .filter(el -> !el.getText()
                            .equalsIgnoreCase(""))
                    .collect(Collectors.toList());
            availableFacetNames = facetNames.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } else {
            Assert.fail("ERROR - ENV: Facets are not displayed on left navigation");
        }
        return availableFacetNames;
    }

    /**
     * Navigates directly to the given product id in registry mode
     *
     * @param productID - product id to navigate
     */
    private void navigateDirectlyToRegistryProduct(String productID) {
        String product_url = RunConfig.url + "/shop/registry/wedding/product/?ID=" + productID;
        Navigate.visit(product_url);
        closePopup();
    }

    /**
     * Navigates to the home page with the given cookie value
     *
     * @param cookieValue - value of the cookie like DAL or RTP
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a guest user in (domestic|registry|iship) mode with dca cookie as (DAL|RTP)$")
    public void iVisitTheWebSiteAsAGuestUserInRootModeWithDcaCookieAsDAL(String mode, String cookieValue) throws Throwable {
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        switch (mode.toLowerCase()) {
            case "domestic":
                break;
            case "iship":
                pageNavigation.I_navigate_to_international_context_page();
                new ShopAndBrowse().I_change_country_to("a random country");
                break;
            case "registry":
                registry.I_navigate_to_registry_home_page();
                break;
        }
        createDcaCookie(cookieValue);
    }

    /**
     * Adds a product with the given attributes to bag and selects checkout if elected
     *
     * <p>
     * example: I add "orderable and available and bops_available" product from domestic mode to my bag and select checkout<br>
     * this would add a bops product attribute
     * </p>
     *
     * @param productTrue  Properties expected to be true separated by either a space or the word "and"
     * @param mode "domestic" or "iship" or "registry"
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add \"([^\"]*)\" product from (domestic|registry|iship) mode to my bag and select checkout$")
    public void iAddProductFromRootModeToMyBagAndSelectCheckout(String productTrue, String mode) throws Throwable {
        productTrue = mode.equalsIgnoreCase("iship") ? productTrue + " and iship_eligible" : productTrue;
        HashMap<String, Object> opts = new HashMap<>();
        opts.putAll(CommonUtils.extractOptions(productTrue, null));
        pausePageHangWatchDog();
        Product p = getRandomProduct(opts);
        resumePageHangWatchDog();
        Assert.assertNotNull("ERROR - DATA:  No " + productTrue + " product was found in products list.", p);

        ShopAndBrowse shop = new ShopAndBrowse();
        switch (mode) {
            case "domestic":
            case "iship":
                CommonUtils.navigateDirectlyToProduct(p.id);
                break;
            case "registry":
                navigateDirectlyToRegistryProduct("" + p.id);
                break;
        }
        shop.I_add_product_to_my_bag_from_standard_PDP_Page();

        if (onPage("add_to_bag")) {
            Clicks.click("add_to_bag.checkout");
        } else if (Elements.elementPresent("add_to_bag_dialog.add_to_bag_checkout")) {
            Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
        } else {
            Clicks.click("product_display.member_atb_checkout");
        }

    }

    /**
     * Navigates to the home page and crated an account and then navigate to given mode
     *
     * @param mode "domestic" or "registry"
     * @param cookieValue - value of the cookie like DAL or RTP
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a registered user in (domestic|registry) mode with dca cookie as (DAL|RTP)$")
    public void iVisitTheWebSiteAsARegisteredUserInRootWithCheckoutEligibleAddressWithDcaCookieAsDAL(String mode, String cookieValue) throws Throwable {
        if (prodEnv()) {
            loginAsRegisteredUserWithGivenUserName(userEmail, userPassword);
        } else {
            pageNavigation.I_visit_the_web_site_as_a_guest_user();

            createDcaCookie(cookieValue);

            try {
                // create profile REST service is only for MCOM.
                if (macys() && !prodEnv())
                    UserProfileService.createRandomUserProfile();
            } catch (AssertionError e) {
                System.out.println("Service failed. Falling back on UI.");
            }
            CommonUtils.signInOrCreateAccount();
            CreateProfile.closeSecurityAlertPopUp();

            MyAccountSteps myAccountSteps = new MyAccountSteps();

            if (!myAccountSteps.userProfileHasCheckoutEligibleAddress) {
                myAccountSteps.iAddCheckoutEligibleAddressOnMyAddressBookPage();
                myAccountSteps.userProfileHasCheckoutEligibleAddress = true;
            }
            myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
            myAccountSteps.i_add_credit_card_to_my_wallet();

            ShoppingBag.emptyCurrentShoppingBag();
        }

        if (mode.equalsIgnoreCase("registry")) {
            Wait.forPageReady();
            Clicks.click("home.goto_wedding_registry");
            Wait.forPageReady();
        }

    }

    /**
     * Add and Electronic gift card to bag and continue to shopping bag page with amount and email id
     *
     * @param amount - amount to be filled on EGC page
     * @param email - email to be filled on EGC page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add EGC item with \"([^\"]*)\" and \"([^\"]*)\" and continue checkout till shopping bag page$")
    public void iAddVGCItemWithAndAndContinueCheckoutTillShoppingBagPage(String amount, String email) throws Throwable {
        TextBoxes.typeTextbox("product_display.vgc_amount", amount);
        TextBoxes.typeTextbox("product_display.vgc_email", email);
        Clicks.click("product_display.add_to_bag_button");
        Wait.until(() -> Elements.elementPresent("add_to_bag_dialog.add_to_bag_checkout"));
        if (onPage("add_to_bag")) {
            Clicks.click("add_to_bag.checkout");
        } else if (Elements.elementPresent("add_to_bag_dialog.add_to_bag_checkout")) {
            Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
        } else {
            Clicks.click("product_display.member_atb_checkout");
        }
    }

    /**
     * Verifies that RVI panel is displayed on pdp page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify that RVI panel is displayed on pdp page$")
    public void iVerifyThatRVIPanelIsDisplayedOnPdpPage() throws Throwable {
        scrollToLazyLoadElement("recently_viewed_items.rvi_container");
        Assert.assertTrue("ERROR - APP: RVI panel is not displayed on PDP page",
                Elements.elementPresent("recently_viewed_items.rvi_container"));
    }


    /**
     * Verifies that Zero Search Result page is displayed
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify that zero search result page is displayed$")
    public void iVerifyThatZeroSearchResultPageIsDisplayed() throws Throwable {
        String value = WebDriverManager.getCurrentUrl().contains("?") ?  "&debug=" : "?&debug=";
        Navigate.visit(WebDriverManager.getCurrentUrl() + value + debugValue);
        int productCount = getProductCountSearch();
        Assert.assertTrue("ERROR - APP: Not on zero search result page",
                productCount == 0);
    }

    /**
     *  Login to registry account for the given user name and password
     *
     * @param userName - Registry user name
     * @param password - Registry password
     * @throws Throwable if any exception occurs
     */
    private void loginAsRegistryUserWithGivenUserName(String userName, String password) throws Throwable {
        Navigate.visit("home");
        registry.I_navigate_to_registry_home_page();
        Clicks.javascriptClick("registry_homepage.goto_manage_registry");
        TextBoxes.typeTextbox("registry_sign_in.existing_user_email", userName);
        TextBoxes.typeTextbox("registry_sign_in.existing_user_password", password);
        Clicks.javascriptClick("registry_sign_in.existing_user_continue_button");
        Wait.untilElementPresent("registry_manager.verify_page");
    }

    /**
     * Login as a registered used for the given user name and password
     *
     * @param userName - user name to login
     * @param password - password to login
     * @throws Throwable if any exception occurs
     */
    private void loginAsRegisteredUserWithGivenUserName(String userName, String password) throws Throwable {
        new PageNavigation().I_visit_the_web_site_as_a_guest_user();
        String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
        Wait.untilElementPresent(elementName);
        Clicks.click(elementName);
        TextBoxes.typeTextbox("sign_in.email", userName);
        TextBoxes.typeTextbox("sign_in.password", password);
        Clicks.click("sign_in.verify_page");
    }

    /**
     * Searches for an existing registry using existing couple's registry
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I search for an existing couple's registry$")
    public void iSearchForAnExistingCoupleSRegistry() throws Throwable {
        if (prodEnv()) {

            TextBoxes.typeTextbox("find_registry.first_name", "msh");
            TextBoxes.typeTextbox("find_registry.last_name", "gslb");
            Clicks.click("find_registry.find_registry_button");
        } else {
            registry.i_search_for_the_existing_couple_s_registry();
        }
    }

    /**
     * Verifies that the correct registry is displayed
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see couple's registry is displayed$")
    public void iShouldSeeCoupleSRegistryIsDisplayed() throws Throwable {
        if (prodEnv()) {
            Wait.forPageReady();
            boolean found = false;
            List<WebElement> elementList = Elements.findElements("find_registry.find_registry_results");
            for (WebElement we : elementList) {
                String str = we.getText();
                if (str.equalsIgnoreCase("msh glsb")) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail("Registry not found");
            }
        } else {
            registry.i_should_find_the_couple_s_registry();
        }
    }

    @And("^I sign in during checkout with existing username and password$")
    public void iSignInDuringCheckoutWithExistingUsernameAndPassword() throws Throwable {
        if (prodEnv()) {
            if (!onPage("shipping_payment_signed_in")) {
                Navigate.visit("shopping_bag");
                Clicks.click("shopping_bag.continue_checkout_image");
            }
            TextBoxes.typeTextbox("checkout_sign_in.email", userEmail);
            TextBoxes.typeTextbox("checkout_sign_in.password", userPassword);
            Clicks.click("checkout_sign_in.checkout_button");
        } else  {
            new CheckoutSteps().I_sign_in_during_checkout();
        }
    }

    @When("^I select a quick view of random product$")
    public void iSelectAQuickViewOfRandomProduct() throws Throwable {
        WebElement thumbnail = Elements.getRandomElement("search_result.product_thumbnail_wrapper");
        Clicks.hoverForSelection(thumbnail.findElement(By.tagName("img")));
        if (Elements.elementPresent("search_result.product_thumbnail_quickview"))
            Clicks.click("search_result.product_thumbnail_quickview");
    }

    @And("^I remove bonus item product from shopping bag$")
    public void iRemoveBonusItemProduct() throws Throwable {
        ShoppingBag.removeBonusItemsFromBag();
        Navigate.browserRefresh();
    }

    public static int getProductCount() {
        Wait.untilElementPresent("category_browse.product_count");
        String productCountText = Elements.getText("category_browse.product_count");
        Assert.assertFalse("ERROR - APP : Product count is not displayed on page!!", productCountText.isEmpty());
        return Integer.parseInt((macys() ? productCountText.trim() : productCountText.split(" ")[0]));
    }

    @Then("^I verify that \"([^\"]*)\" recommendation panel is displayed on \"([^\"]*)\" page$")
    public void iVerifyThatRecommendationPanelIsDisplayedOnPage(String panel_position, String page) throws Throwable {
        switch (page) {
            case "registry PDP":
                shouldBeOnPage("registry_pdp");
                break;
            case "pdp":
                shouldBeOnPage("product_display");
                break;
        }
        if (panel_position.equalsIgnoreCase("horizontal")) {
            scrollToLazyLoadElement("recommendations." + panel_position + "_recommendations");
        }
        Elements.elementShouldBePresent("recommendations." + panel_position + "_recommendations");
    }

    @And("^I login for the existing registry$")
    public void i_login_for_the_existing_registry() throws Throwable {
        Wait.forPageReady();
        if (macys()) {
            Wait.secondsUntilElementPresentAndClick("registry_home.goto_registry_manager", 20);
            Wait.forPageReady();
            TextBoxes.typeTextbox("registry_sign_in.existing_user_email", Elements.getValues("login.email").get(0));
            TextBoxes.typeTextbox("registry_sign_in.existing_user_password", Elements.getValues("login.password").get(0));
            Clicks.click("registry_sign_in.existing_user_continue_button");
            Wait.secondsUntilElementPresent("registry_manager.view_our_registry", 30);
            Clicks.javascriptClick("registry_manager.view_our_registry");
        }
    }

    @Then("^I navigate to registry claim Page$")
    public void iNavigateToRegistryClaimPage() throws Throwable {
        Navigate.visit(RunConfig.url + "/registry/wedding/claim");
        log.info("Navigated to registry claim page");
    }

    @When("^I select \"([^\"]*)\" link from MyAccount page$")
    public void iSelectLinkFromDashboard(String myAccountLink) throws Throwable {
        switch (myAccountLink) {
            case "Order Status & History":
                Clicks.click("navigation.goto_order_status");
                Wait.forPageReady();
                break;
            case "My Points":
                Clicks.click("navigation.goto_my_loyallist");
                Wait.forPageReady();
                break;
            case "Furniture Mattress Status":
                Clicks.click("navigation.goto_furniture_mattress_status");
                Wait.forPageReady();
                break;
            case "Cardholder Benefits":
                Clicks.click("navigation.goto_cardholder_benefits");
                Wait.forPageReady();
                break;
            case "My Wish List":
                Clicks.click("navigation.goto_my_wish_list");
                Wait.forPageReady();
                break;
        }
    }

    @When("^I close the addtobag dialog$")
    public void I_close_the_addtobag_dialog() throws Throwable {
        Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
        Clicks.click("add_to_bag_dialog.close_add_to_bag_dialog");
    }

    @And("^I update quantity to (\\d+) on shopping bag page$")
    public void iUpdateShoppingBagItemsWithQuantity(int quantity) {
        List<Product> bagItems = getAllProductDetails();
        bagItems.forEach(item -> updateQuantity(item, Integer.toString(quantity)));
    }
    @And("^I should see the added products in quick bag$")
    public void i_should_see_the_added_products_and_click_checkout_on_quick_bag() throws Throwable {
        Wait.secondsUntilElementPresent("quick_bag.quickbag_checkout",20);
//        //Verify Bag Link in header & footer
//        Assert.assertTrue(Elements.findElement(By.className("qb_myBagLink")).getAttribute("href").contains("/bag/index.ognc"));
//        Assert.assertTrue(Elements.findElement(By.id("qb_quickBagBottomContent")).findElement(By.className("qb_myBagLink")).getAttribute("href").contains("/bag/index.ognc"));
//
//        //Verify QuickBag Text
//        Assert.assertTrue(Elements.findElement(By.className("qb_myBagLink")).getText().equals("MY BAG: 1 ITEM"));
//        Assert.assertTrue(Elements.findElement(By.id("qb_quickBagBottomContent")).findElement(By.className("qb_myBagLink")).getText().equals("MY BAG: 1 ITEM"));
//
//       //verify Checkout Button:
//        Assert.assertTrue(Elements.findElement(By.id("qb_checkoutButton")).isDisplayed());
//
//        Thread.sleep(3000);
//        Clicks.click("quick_bag.quickbag_checkout");
    }
}