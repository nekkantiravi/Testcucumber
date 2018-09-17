package com.macys.sdt.projects.Customer.UnifiedLogin.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoginCredentials;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.shared.steps.MEW.Home;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.*;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategorySplash;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.steps.MEW.MyAccount;
import com.macys.sdt.shared.steps.MEW.ShopAndBrowse;
import com.macys.sdt.shared.steps.website.OrderConfirmation;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by QE on 9/15/2017.
 */
public class UnifiedLoginSteps extends StepUtils {

    String forward_key;
    String expectedUrl;
    String expectedCookie;
    String url = RunConfig.url;

    private static final Logger logger = LoggerFactory.getLogger(UnifiedLoginSteps.class);

    @Given("^I navigate to the url \"([^\"]*)\" as (guest|signed-in) user$")
    public void iNavigateToTheUrlAsGuestUser(String custom_url, String user_type) throws Throwable {
        if (user_type.equalsIgnoreCase("guest")) {
            Navigate.visit(RunConfig.url + custom_url);
        } else if (user_type.equalsIgnoreCase("signed-in")) {
            Assert.assertTrue("User is not signed in.",
                    Cookies.getCookieValue("SignedIn").contains("1"));
            Navigate.visit(RunConfig.url + custom_url);
        }
        Wait.forPageReady();
        logger.info("Successfully navigated to " + custom_url);
    }

    @When("^I sign in with valid credentials$")
    public void iSignInWithValidCredentials() throws Throwable {
        //clear the the customer object if it tries to create the existing profile again
        TestUsers.clearCustomer();

        //Now create anew profile and login with it
        UserProfileService.createRandomUserProfile();
        CommonUtils.signInToCreatedProfile();
        Wait.forPageReady();
        Wait.secondsUntilElementNotPresent("sign_in.email", 30);
        logger.info("Singed in with the credentials");
    }

    @Then("^I should be navigated to post sign in page$")
    public void iShouldBeNavigatedToPostSignInPage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR - ENV:Not navigated to post sign in page.",
                Elements.elementPresent("header.goto_sign_out_link"));
    }

    @And("^I should verify below cookies to be present$")
    public void iShouldVerifyBelowCookiesToBePresent(DataTable cookies) throws Throwable {
        int size = cookies.raw().size() - 1;
        ArrayList<String> actualCookieNames = new ArrayList<String>();
        List<String> expectedCookieNames = cookies.raw().stream().map(m -> m.get(0)).collect(Collectors.toList());

        //Get all the cookie from page and store in an arraylist.
        Iterator<Cookie> cookieSet = Cookies.getCookies().iterator();
        while (cookieSet.hasNext()) {
            String cookieFromPage = cookieSet.next().getName().toLowerCase();
            actualCookieNames.add(cookieFromPage);
        }
        //verify the desired cookie in arraylist
        while (size >= 0) {
            Assert.assertTrue("ERROR - APP: Cookie verification is failed.", actualCookieNames.contains(expectedCookieNames.get(size).toLowerCase()));
            logger.info("Cookie " + cookies.raw().get(size) + " is available on page.");
            size -= 1;
        }
    }

    @Then("^I should be on signin page$")
    public void iShouldBeOnSignInPage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Not able to navigate to the sign_in page",
                Wait.secondsUntilElementPresent("sign_in.verify_page", 70));
        logger.info("Navigated to signin page");
    }

    @And("^I verify the forwardpage key cookie is generated$")
    public void iVerifyTheForwardpageKeyCookieIsGenerated() throws Throwable {
        forward_key = Cookies.getCookieValue("FORWARDPAGE_KEY");
        Assert.assertFalse(forward_key.isEmpty());
        logger.info("forwardpage_key cookie is generated");
    }

    @Then("^I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie$")
    public void iShouldRedirectToThePageCorrespondingToTheValueStoredInFORWARDPAGE_KEYCookie() throws Throwable {
        String actualUrl = url();

        Pattern pattern = Pattern.compile("\\/[a-z]+\\/[a-z]+[?]");
        Matcher matcherUrl = pattern.matcher(actualUrl);
        Matcher matcherCookie = pattern.matcher(forward_key);

        if (matcherUrl.find()) {
            expectedUrl = matcherUrl.group();
        }
        if (matcherCookie.find()) {
            expectedCookie = matcherUrl.group();
        }
        Assert.assertTrue("ERROR - APP: User is not redirected to the correspoding page ",
                expectedUrl.equalsIgnoreCase(expectedCookie));
        logger.info("User is redirected to the corresponding page as per the cookie value");
    }

    @When("^I select \"([^\"]*)\" link from my account dropdown$")
    public void iSelectLinkFromMyAccountDropdown(String element) throws Throwable {
        resumePageHangWatchDog();
        logger.info("Selecting " + element + " link from My Account dropdown");
        Clicks.hoverForSelection("home.goto_my_account_link");
        Wait.untilElementPresent("home.myaccount_dropdown");
        Clicks.clickWhenPresent("home." + element);
        logger.info("selected" + element);
    }

    @Then("^I should be navigated to checkout signin page$")
    public void iShouldBeNavigatedToCheckoutSigninPage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR - ENV : Not navigated to Checkout Sign In page.",
                Elements.elementPresent("checkout_sign_in.checkout_as_guest"));
        logger.info("Navigated to checkout signin page");
    }

    @And("^I should verify below fields on \"([^\"]*)\" page$")
    public void iShouldVerifyBelowFieldsOnRegistrySigninInPage(String page, List<String> elements) throws Throwable {
        elements.forEach(element ->
                Assert.assertTrue("ERROR - APP : " + element + " is not displayed on " + page + " page",
                        Elements.elementPresent(page + "." + element)));
        logger.info("Element is displayed" + page);
    }

    @And("^I click on checkout as guest button$")
    public void iClickOnCheckoutAsGuestButton() throws Throwable {
        Clicks.click("checkout_sign_in.continue_checkout_guest_button");
        Wait.forPageReady();
        if (Elements.elementPresent("checkout_sign_in.continue_checkout_guest_button")) {
            Clicks.click("checkout_sign_in.continue_checkout_guest_button");
            Wait.forPageReady();
        }
        logger.info("Clicked on checkout as guest button");
    }

    @And("^I should see typed password using show button in \"([^\"]*)\" page$")
    public void iShouldSeeTypedPasswordUsingShowButtonInPage(String page) throws Throwable {
        Wait.forPageReady();
        if (page.contains("registry")) {
            TextBoxes.typeTextbox(page + ".existing_user_password", TestUsers.getCustomer(null).getUser().getLoginCredentials().getPassword());
            Clicks.javascriptClick(page + ".pwd_show_button");
            Assert.assertTrue("ERROR - APP : Password text is not displaying", Elements.findElement(page + ".existing_user_password").getAttribute("value").equals(TestUsers.getCustomer(null).getUser().getLoginCredentials().getPassword()));
        } else {
            TextBoxes.typeTextbox(page + ".password", TestUsers.getCustomer(null).getUser().getLoginCredentials().getPassword());
            Clicks.javascriptClick(page + ".pwd_show_button");
            Assert.assertTrue("ERROR - APP : Password text is not displaying", Elements.findElement(page + ".password").getAttribute("value").equals(TestUsers.getCustomer(null).getUser().getLoginCredentials().getPassword()));
        }
        logger.info("Password is displayed as plain text by clicking on show button");
    }

    @And("^I should see hide button on \"([^\"]*)\" page$")
    public void iShouldSeeHideButtonInCheckoutSigninPage(String page) throws Throwable {
        Assert.assertTrue("ERROR - APP : Hide button is not changed", Elements.getText(page + ".pwd_show_button").equals("HIDE"));
        logger.info("Hide button is displayed");
    }

    @When("^I click on \"([^\"]*)\" in \"([^\"]*)\" page$")
    public void iClickOnElement(String element, String page) throws Throwable {
        Wait.untilElementPresent(page + "." + element);
        if (page.contains("product_display")) {
            ProductDisplay.selectRandomColor();
            ProductDisplay.selectRandomSize();
            Assert.assertTrue(element + " is not present or clickable on " + page,
                    Clicks.clickWhenPresent(page + "." + element));
        } else if (StepUtils.macys()) {
            if (element.contains("goto_wedding_registry")) {
                Clicks.hoverForSelection("registry_home.goto_wedding_registry");
            } else {
                Clicks.javascriptClick(page + "." + element);
                Wait.forPageReady();
                if (Elements.elementPresent(page + "." + element)) {
                    Clicks.click(page + "." + element);
                    Wait.forPageReady();
                }
            }
        } else if (element.contains("header")) {
            Clicks.hoverForSelection("registry_home.getting_started");
        } else {
            Navigate.browserRefresh();
            Clicks.click(page + "." + element);
        }
        logger.info("Clicked on " + element + "in" + page);
    }

    @Then("^I should see \"([^\"]*)\" validation message on \"([^\"]*)\" page$")
    public void iShouldSeeValidationMessageOnPage(String error_message, String page) throws Throwable {
        if (StepUtils.macys()) {
            if (error_message.contains("email")) {
                Wait.untilElementPresent(page + ".email_err_msg");
                Assert.assertTrue("ERROR - APP : Error message is not shown", Elements.getText(page + ".email_err_msg").equals(error_message));
            } else if (error_message.contains("password")) {
                Wait.untilElementPresent(page + ".pwd_err_msg");
                Assert.assertTrue("ERROR - APP : Error message is not shown", Elements.getText(page + ".pwd_err_msg").equals(error_message));
            } else {
                Wait.untilElementPresent(page + ".error_message");
                Assert.assertTrue("ERROR - APP : Error message is not shown", Elements.getText(page + ".error_message").equals(error_message));
            }
        } else {
            if (error_message.contains("email")) {
                Wait.untilElementPresent(page + ".email_err_msg");
                Assert.assertTrue("ERROR - APP : Error message is not shown", Elements.getText(page + ".email_err_msg").equals(error_message));
            } else if (error_message.contains("password")) {
                Wait.untilElementPresent(page + ".pwd_err_msg");
                Assert.assertTrue("ERROR - APP : Error message is not shown", Elements.getText(page + ".pwd_err_msg").equals(error_message));
            } else {
                Wait.untilElementPresent(page + ".error_message");
                Assert.assertTrue("ERROR - APP : Error message is not shown", Elements.getText(page + ".error_message").equals(error_message));
            }
        }
        logger.info("Error message is displayed");
    }

    @And("^I should see Wish List confirmation overlay displays$")
    public void I_should_see_whishlist_confirmation_overlay_displays() throws Throwable {
        Wait.untilElementPresent("product_display.wishlist_overlay");
        Clicks.hoverForSelection("product_display.wishlist_overlay");
        Assert.assertTrue("ERROR - APP : Wish List confirmation overlay was not displayed", Elements.elementPresent("product_display.wishlist_overlay"));
        logger.info("Wish list overlay is displayed");
    }

    @And("^I sign in with existing credentials from \"([^\"]*)\" page$")
    public void iSignInWithExistingCredentailsFromCheckoutSignInPage(String page) throws Throwable {
        if (page.contains("checkout_sign_in")) {
            TestUsers.clearCustomer();
            UserProfile customer = TestUsers.getCustomer(null);
            UserProfileService.createUserProfile(customer);
            TextBoxes.typeTextbox(page + ".email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox(page + ".password", customer.getUser().getLoginCredentials().getPassword());
            Clicks.click("checkout_sign_in.checkout_button");
        } else {
            UserProfile customer = TestUsers.getCustomer(null);
            TextBoxes.typeTextbox(page + ".email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox(page + ".password", customer.getUser().getLoginCredentials().getPassword());
            if (page.contains("registry_sign_in")) {
                Clicks.click("registry_sign_in.reg_sign_in_button");
            } else if (page.contains("new_registry_sign_in")) {
                Clicks.click("new_registry_sign_in.sign_in_button");
            }
        }
        Wait.untilElementNotPresent(page + ".email");
        Assert.assertFalse("Not able to sign in with existing credential.",
                Elements.elementPresent(page + ".email"));
    }

    @When("^I navigate to PDP Page which has reviews$")
    public void iNavigateToPDPPageWhichHasReviews() throws Throwable {
        HashMap<String, Object> productHash = new HashMap<String, Object>() {{
            put("available", true);
            put("has_reviews", true);
        }};
        Product p = TestUsers.getRandomProduct(productHash);
        int productId = p.id;
        TextBoxes.typeTextNEnter("header.search_field", Integer.toString(productId));
        StepUtils.shouldBeOnPage("product_display");
    }

    @When("^I add all products to current bag$")
    public void iAddAllProductsToCurrentBag() throws Throwable {
        ShoppingBag.mergeSavedBag();
    }

    @And("^I navigate to browse page from ATB Page$")
    public void iNavigateToBrowsePageFromATBPage() throws Throwable {
        CategorySplash categorySplash = new CategorySplash();
        categorySplash.navigateToRandomCategoryBrowsePage(8);
        Assert.assertTrue("User did not navigate to browse page", onPage("category_splash"));
        logger.info("User is navigated to browse page");
    }

    @Given("^I set the unifiedLoginEnabled header$")
    public void iSetTheUnifiedLoginEnabledHeader() throws Throwable {
        WebDriver driver = WebDriverManager.getWebDriver();
        driver.get("chrome-extension://idgpnmonknjnojddfkpgkljpfnnfcklj/icon.png");
        ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('profiles', JSON.stringify([{                " +
                        "  title: 'Selenium', hideComment: true, appendMode: '',           " +
                        "  headers: [                                                      " +
                        "    {enabled: true, name: 'unifiedLoginEnabled', value: 'true', comment: ''} " +
                        "  ],                                                              " +
                        "  respHeaders: [],                                                " +
                        "  filters: []                                                     " +
                        "}]));                                                             ");

    }


    @When("^I hover on getting started link$")
    public void iHoverOnGettingStartedLink() throws Throwable {
        Wait.forPageReady();
        Clicks.hoverForSelection("registry_home.getting_started");
    }

    @And("^I select \"([^\"]*)\" in dropdown$")
    public void iSelectInDropdown(String option) throws Throwable {
        Wait.secondsUntilElementPresent("registry_home." + option, 10);
        Clicks.click("registry_home." + option);
    }

    @When("^I select \"([^\"]*)\" on standard PDP$")
    public void iSelectOnStandardPDP(String option) throws Throwable {
        Wait.secondsUntilElementPresent("product_display." + option, 15);
        Clicks.click("product_display." + option);
    }

    @Then("^I should be navigated to \"([^\"]*)\" url$")
    public void iShouldBeNavigatedToUrl(String expected_url) throws Throwable {
        Assert.assertTrue("Error - Env: Could not navigate to the " + expected_url + "url",
                StepUtils.url().toString().contains(expected_url));
    }

    @When("^I try to login with invalid credential for more then allowed attempts$")
    public void iTryToLoginWithInvalidCredentialForMoreThenAllowdAttempts() throws Throwable {
        Wait.secondsUntilElementPresent("sign_in.email", 10);
        int allowed_attempt = 8;
        TestUsers.clearCustomer();
        UserProfileService.createRandomUserProfile();
        String email = TestUsers.currentEmail;
        for (int i = 0; i < allowed_attempt; i++) {
            TextBoxes.typeTextbox("sign_in.email", email);
            TextBoxes.typeTextbox("sign_in.password", "abc$5d");
            Clicks.click("sign_in.verify_page");
            Wait.forPageReady();
        }
    }

    @And("^I get the registry user detail$")
    public void iGetTheRegistryUserDetails() throws Throwable {
        Wait.secondsUntilElementPresent(macys() ? "home.weddingregistry_fob"
                : "home.theregistry_fob", 15);
        new Registry().I_navigate_to_registry_home_page();
        new PageNavigation().iSelectCreateRegistry();
        new Registry().iCreateRegistryForExistingUser();
        new Registry().I_should_see_the_registry_created_successfully();
    }

    @When("^I select \"([^\"]*)\" from \"([^\"]*)\"$")
    public void iSelectFrom(String link, String link_area) throws Throwable {
        Wait.forPageReady();
        if (link_area.toString().equalsIgnoreCase("wedding_reg_dropdown")) {
            Clicks.hoverForSelection("registry_home.wedding_registry_fob");
            Clicks.clickWhenPresent("registry_home." + link);
        } else if (link_area.toString().equalsIgnoreCase("manage_dropdown")) {
            Clicks.hoverForSelection("registry_home.manage_registry_dropdown");
            Clicks.clickWhenPresent("registry_home." + link);
        } else if (link_area.toString().equalsIgnoreCase("view_registry")) {
            Clicks.hoverForSelection("registry_home." + link);
            Clicks.clickWhenPresent("registry_home." + link);
        } else if (link_area.toString().equalsIgnoreCase("manage_header")) {
            Clicks.hoverForSelection("registry_home.wedding_registry_fob");
            Clicks.clickWhenPresent("registry_home." + link);
        } else {
            Clicks.click("registry_home." + link);
        }
        logger.info("Successfully clicked on " + link);
    }

    @When("^I enter user detail for existing account and click continue$")
    public void iEnterUserDetailForExistingAccountAndClickContinue() throws Throwable {
        TestUsers.clearCustomer();
        UserProfile customer = UserProfileService.createRandomUserProfile();

        if (!Wait.secondsUntilElementPresent("new_create_registry.new_user_email", 6)) {
            Navigate.browserRefresh();
        }
        Wait.forPageReady();
        TextBoxes.typeTextbox("new_create_registry.new_user_email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("new_create_registry.new_user_password", customer.getUser().getLoginCredentials().getPassword());
        TextBoxes.typeTextbox("new_create_registry.new_user_password_verify", customer.getUser().getLoginCredentials().getPassword());
        DropDowns.selectByText("new_create_registry.security_question", customer.getUser().getUserPasswordHint().getQuestion());
        TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
        if (bloomingdales()) {
            Clicks.click("new_create_registry.register_button");
        } else
            Clicks.click("new_create_registry.continue_button");
        Wait.forPageReady();

    }

    @When("^I enter existing credential and hit sign in button$")
    public void iEnterExistingCredentialAndHitSignInButton() throws Throwable {
        Wait.forPageReady();
        TestUsers.clearCustomer();
        UserProfile customer = UserProfileService.createRandomUserProfile();
        TextBoxes.typeTextbox("new_registry_sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("new_registry_sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("new_registry_sign_in.sign_in_button");
    }

    @When("^I select Holiday gift guide from Gift dropdown$")
    public void iSelectHolidayGiftGuideFromGiftDropdown() throws Throwable {
        Clicks.hoverForSelection("home.gift_dropdown");
        Wait.untilElementPresent("home.gift_dropdown");
        Clicks.clickWhenPresent("home.holiday_giftguide");
        logger.info("selected Holiday Gift Guide");
    }

    @And("^I navigated to \"([^\"]*)\" page through \"([^\"]*)\" FOB$")
    public void iNavigatedToPageThroughFOB(String page, String fob) throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("home.beauty_fob")) {
            Clicks.hoverForSelection("home.beauty_fob");
            Navigate.scrollPage(0, 100);
            Assert.assertTrue("ERROR - DATA: Beauty box link is not available.", Clicks.clickIfPresent("home.beauty_box_cat"));
            Wait.untilElementPresent(page + ".beauty_subscription_button");
        } else {
            Assert.fail("ERROR - DATA: " + fob + " is not present in FOB section.");
        }

        logger.info("Navigated to the " + page);
    }

    @And("^I should verify \"([^\"]*)\" url for \"([^\"]*)\" link$")
    public void iShouldVerifyUrlForLink(String sign_in_status, String link) throws Throwable {
        String actualUrl = url();
        boolean isUrlverified = false;
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(sign_in_status + ".json"))));
        String[] expectedUrls = json.get(link).toString().split("\\|\\|");
        for (String expectedUrl : expectedUrls) {
            isUrlverified = actualUrl.contains(expectedUrl);
            if (isUrlverified)
                break;
        }
        Assert.assertTrue("Url validation unsuccessful", isUrlverified);
        logger.info("Url validation successful.");
    }

    @Then("^I verify the order number on orders layout$")
    public void iVerifyTheOrderNumberOnOrdersLayout() throws Throwable {
        String orderNumber = OrderConfirmation.orderNumber;
        Assert.assertTrue("Order number is not displayed",
                orderNumber.equalsIgnoreCase(Elements.getText("my_account.order_number")));
        logger.info("order number is displayed");
    }

    @When("^I click on \"([^\"]*)\" in \"([^\"]*)\" mobile page$")
    public void iClickOnInMobilePage(String element, String page) throws Throwable {
        Wait.secondsUntilElementPresent(page + "." + element, 10);
        Clicks.click(page + "." + element);
        logger.info("Clicked on " + element + "in" + page);
    }

    @And("^I navigate to mobile \"([^\"]*)\" page from global nav$")
    public void iNavigateToMobilePageFromGlobalNav(String page) throws Throwable {
        if (page.equals("gateway")) {
            page = macys() ? "Macy's Credit Card" : "MY CREDIT CARD";
        } else if (page.equals("my_account")) {
            page = "My Account";
        } else if (page.equals("oc_my_wallet")) {
            page = macys() ? "Wallet" : "MY bWALLET";
        } else if (page.equals("wishlist")) {
            page = "LISTS";
        } else if (page.equals("registry")) {
            page = macys() ? "Wedding Registry" : "The Registry";
        }

        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName(page);
        Wait.forPageReady();
    }

    @When("^I select signIn link on wishlist overlay in PDP page$")
    public void iSelectSignInLinkOnWishlistOverlayInPDPPage() throws Throwable {
        Navigate.scrollPage(0, 100);
        Wait.secondsUntilElementPresentAndClick("product_display.wishlist_signin", 5);
        logger.info("clicked on signin link on wishlist overlay");
    }

    @When("^I add a random product to bag in mobile$")
    public void iAddARandomProductToBagInMobile() throws Throwable {
        List<String> categories = new ArrayList<>();
        categories.add("Shop");
        categories.add("Men");
        categories.add("All Clothing");
        categories.add("BASICS");
        new com.macys.sdt.shared.steps.MEW.PageNavigation().I_navigate_the_global_navigation_menu_as_follows(categories);
        new ShopAndBrowse().I_select_a_random_product_using_mobile_website("member", "null");
        new com.macys.sdt.shared.steps.MEW.PageNavigation().I_should_be_redirected_to_PDP_page_using_mobile_website();
        new ShopAndBrowse().I_add_product_to_my_bag_from_standard_PDP_page();
        logger.info("Product added to mobile site.");
    }

    @And("^I navigate to mobile shopping bag page$")
    public void iNavigateToMobileShoppingBagPage() throws Throwable {
        com.macys.sdt.shared.steps.MEW.PageNavigation page = new com.macys.sdt.shared.steps.MEW.PageNavigation();
        page.I_should_be_redirected_to_ATB_page_using_mobile_website();
        page.I_navigate_to_shopping_bag_page_from_add_to_bag_page_using_mobile_website();
    }

    @When("^I click on signIn link in footer$")
    public void iClickOnSignInLinkInFooter() throws Throwable {
        scrollToLazyLoadElement("home.goto_sign_in_link");
        Clicks.clickWhenPresent("home.goto_sign_in_link");
        Assert.assertTrue("ERROR-ENV: Not able to navigate to the sign_in page", Wait.untilElementPresent("sign_in.verify_page"));
    }

    @When("^I navigate to the global navigation menu and click on SignIn$")
    public void iNavigateToTheGlobalNavigationMenuAndClickOnSignIn() throws Throwable {
        String category = "Sign In";
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (category.contains("Sign In")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to sign In page from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR-ENV: Not able to navigate to the sign_in page", Wait.untilElementPresent("sign_in.verify_page"));
            // Clicks.clickWhenPresent("home.Global signIn");
        }
    }

    @When("^I navigate to the global navigation menu and click on My Account$")
    public void iNavigateToTheGlobalNavigationMenuAndClickOnMyAccount() throws Throwable {
        String category = "MY ACCOUNT";
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (category.contains("MY ACCOUNT")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to my account page from global navigation");
            Thread.sleep(2000);
            Assert.assertTrue("ERROR - APP :: Unable to navigate to my account from GN", Wait.untilElementPresent("sign_in.verify_page"));
            //Clicks.clickWhenPresent("home.My Account");
        }
    }

    @When("^I navigate to the global navigation menu and click on My Credit Card$")
    public void iNavigateToTheGlobalNavigationMenuAndClickOnMyCreditCard() throws Throwable {
        String category = "MY CREDIT CARD";
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (category.contains("MY CREDIT CARD")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to credit gateway page from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - APP :: Unable to land on gateway page", Clicks.clickWhenPresent("credit_service_gateway_guest.sign_in_button"));
            Wait.forPageReady();
            Assert.assertTrue("ERROR - APP :: Unable to navigate to gateway page from GN", Wait.untilElementPresent("sign_in.verify_page"));
            //  Clicks.clickWhenPresent("home.My Credit Card");
        }
    }

    @When("^I navigate to the global navigation menu and click on My bWallet$")
    public void iNavigateToTheGlobalNavigationMenuAndClickOnMyBWallet() throws Throwable {
        String category = "MY bWALLET";
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (category.contains("MY bWALLET")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to wallet page from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - APP :: Unable to navigate to gateway page from GN", Wait.untilElementPresent("sign_in.verify_page"));
            // Clicks.clickWhenPresent("home.My bWallet");
        }
    }

    @When("^I navigate to the global navigation and signin on wishlist$")
    public void iNavigateToTheGlobalNavigationAndSigninOnWishlist() throws Throwable {
        String category = "WISH LIST";
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        GlobalNav.navigateOnGnByName(category);
        if (category.contains("WISH LIST")) {
            Wait.forPageReady();
            logger.info("Navigating to wish list page from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR - APP :: Unable to navigate to gateway page from GN", Wait.untilElementPresent("sign_in.verify_page"));
            //Clicks.clickWhenPresent("home.Wish List");
        }
    }

    @When("^I login with created profile through \"([^\"]*)\" page$")
    public void iLoginWithCreatedProfileThroughPage(String page) throws Throwable {
        UserProfile customer = TestUsers.getCustomer(null);
        TextBoxes.typeTextbox(page + ".email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox(page + ".password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click(page + ".verify_page");
        Wait.secondsUntilElementNotPresent(page + ".email", 10);
    }

    @Given("^I visit the mobile website as a (registered) user$")
    public void iVisitTheMobileWebsiteAsARegisteredUser(String userType) throws Throwable {
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
            Navigate.visit("home");
        }
        Cookies.disableForeseeSurvey();
    }

    @And("^I navigate to my account page from order confirmation page$")
    public void iNavigateToMyAccountPageFromOrderConfirmationPage() throws Throwable {
        Navigate.visit(url + "/account/myaccount");
        logger.info("Navigated to my account page as signed in user ");
    }

    @And("^I should verify \"([^\"]*)\" url for \"([^\"]*)\" footer link$")
    public void iShouldVerifyUrlForFooterLink(String sign_in_status, String link) throws Throwable {
        String actualUrl = url();
        boolean isUrlverified = false;
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(sign_in_status + ".json"))));
        String[] expectedUrls = json.get(link).toString().split("\\|\\|");
        for (String expectedUrl : expectedUrls) {
            isUrlverified = actualUrl.contains(expectedUrl);
            if (isUrlverified)
                break;
        }
        Assert.assertTrue("Url validation unsuccessful", isUrlverified);
        logger.info("Url validation successful.");
    }

    @And("^I should verify \"([^\"]*)\" url for the \"([^\"]*)\" footer link$")
    public void iShouldVerifyUrlForTheFooterLink(String sign_in_status, String link) throws Throwable {
        String actualUrl = url();
        boolean isUrlverified = false;
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(sign_in_status + ".json"))));
        String[] expectedUrls = json.get(link).toString().split("\\|\\|");
        for (String expectedUrl : expectedUrls) {
            isUrlverified = actualUrl.contains(expectedUrl);
            if (isUrlverified)
                break;
        }
        Assert.assertTrue("Url validation unsuccessful", isUrlverified);
        logger.info("Url validation successful.");
    }

    @Then("^I should be on unified signin page$")
    public void iShouldBeOnUnifiedSigninPage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Not able to navigate to the sign_in page",
                Wait.secondsUntilElementPresent("sign_in.verify_page", 7));
        logger.info("Navigated to signin page");
    }

    @Then("^I verify item count is updating with \"([^\"]*)\" items in bag$")
    public void iVerifyItemCountIsUpdatingWithItemsInBag(int arg) throws Throwable {
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/20)");
        Thread.sleep(2000);
        Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
        Clicks.hoverForSelection("quick_bag.quickbag_hover");
        Assert.assertTrue(Elements.elementPresent(Elements.element("quick_bag.quickbag_hover")));
        String qbItemCount = Elements.findElement(Elements.element("quick_bag.quickbag_hover")).getText();
        String qbCount = qbItemCount.replace("BROWN BAG: (", "");
        qbCount = qbCount.replace(")", "");
        int qtyCount = Integer.parseInt(qbCount);
        Assert.assertTrue(qtyCount >= arg);
        if (arg == 0) {
            Assert.assertTrue(qtyCount == arg);
        } else {
            logger.info(String.format("** There are " + qtyCount + " items shown in QB!\n"));
        }
    }

    @When("^I navigate to the global navigation menu and click on The Registry$")
    public void iNavigateToTheGlobalNavigationMenuAndClickOnTheRegistry() throws Throwable {
        String category = "THE REGISTRY";
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
        if (category.contains("THE REGISTRY")) {
            GlobalNav.navigateOnGnByName(category);
            Wait.forPageReady();
            logger.info("Navigating to sign In page from global navigation");
            Wait.forPageReady();
            //Assert.assertTrue("ERROR-ENV: Not able to navigate to the sign_in page", Wait.untilElementPresent("sign_in.verify_page"));
            // Clicks.clickWhenPresent("home.Global signIn");
        }
    }

    @Then("^I should land on registry home page and click on manage registry$")
    public void iShouldLandOnRegistryHomePageAndClickOnManageRegistry() throws Throwable {
        GlobalNav.shouldBeOnPage("registry_home");
        Clicks.click("registry_home.goto_create_registry");
//        Clicks.click(Elements.findElement(By.linkText("Manage Registry")));
//        GlobalNav.navigateOnGnByName("THE REGISTRY");
//        GlobalNav.closeGlobalNav();
//        //  Wait.secondsUntilElementPresent(Elements.element("registry_home.create_registry"), MainRunner.timeout);
        Assert.assertTrue("ERROR-ENV: Unable to navigate wedding registry page", Elements.elementPresent(Elements.element("registry_home.goto_create_registry")));
    }

    @And("^I should verify \"([^\"]*)\" url for \"([^\"]*)\" global registry link$")
    public void iShouldVerifyUrlForGlobalRegistryLink(String sign_in_status, String link) throws Throwable {
        String actualUrl = url();
        boolean isUrlverified = false;
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(sign_in_status + ".json"))));
        String[] expectedUrls = json.get(link).toString().split("\\|\\|");
        for (String expectedUrl : expectedUrls) {
            isUrlverified = actualUrl.contains(expectedUrl);
            if (isUrlverified)
                break;
        }
        Assert.assertTrue("Url validation unsuccessful", isUrlverified);
        logger.info("Url validation successful.");
    }

    @And("^I should verify \"([^\"]*)\" url for \"([^\"]*)\" global link$")
    public void iShouldVerifyUrlForGlobalLink(String sign_in_status, String link) throws Throwable {
        String actualUrl = url();
        boolean isUrlverified = false;
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(sign_in_status + ".json"))));
        String[] expectedUrls = json.get(link).toString().split("\\|\\|");
        for (String expectedUrl : expectedUrls) {
            isUrlverified = actualUrl.contains(expectedUrl);
            if (isUrlverified)
                break;
        }
        Assert.assertTrue("Url validation unsuccessful", isUrlverified);
        logger.info("Url validation successful.");
    }

    @Then("^I should land on registry home page$")
    public void iShouldLandOnRegistryHomePage() throws Throwable {
        GlobalNav.shouldBeOnPage("registry_home");
//        Home.closePopup();
        Wait.forPageReady();
        closePopup();
    }

    @Then("^I should land on registry home page and click on create registry$")
    public void iShouldLandOnRegistryHomePageAndClickOnCreateRegistry() throws Throwable {
        GlobalNav.shouldBeOnPage("registry_home");
        closeBcomPopup();
//      Assert.assertTrue("ERROR - APP :: Unable to navigate to gateway page from GN", Wait.untilElementPresent("sign_in.verify_page"));
        Assert.assertTrue("ERROR-ENV: Unable to navigate wedding registry page", Elements.elementPresent(Elements.element("registry_home.goto_create_registry")));
        Clicks.click("registry_home.goto_create_registry");
    }

    @And("^I select \"([^\"]*)\" tab in the left nav$")
    public void iSelectTabInTheLeftNav(String tab_name) throws Throwable {
//        String tab_name = "Manage Registry";
        // Wait.forPageReady();
        GlobalNav.openGlobalNav();
        Wait.forPageReady();
       /* if (tab_name.contains("Manage Registry")) {
            GlobalNav.navigateOnGnByName(tab_name);
            Wait.forPageReady();
            logger.info("Navigating to sign In page from global navigation");
            Wait.forPageReady();
            Assert.assertTrue("ERROR-ENV: Not able to navigate to the sign_in page", Wait.untilElementPresent("sign_in.verify_page"));
            // Clicks.clickWhenPresent("home.Global signIn");
       }*/
        if (tab_name.equalsIgnoreCase("Manage Registry")) {
            GlobalNav.openGlobalNav();
            // Wait.forPageReady();
            Clicks.click(Elements.findElement(By.linkText("Manage Registry")));
            // GlobalNav.navigateOnGnByName("Manage Registry");
        }
        if (tab_name.equalsIgnoreCase("View Registry")) {
            GlobalNav.openGlobalNav();
            // Wait.forPageReady();
            Clicks.click(Elements.findElement(By.linkText("View Registry")));
            // GlobalNav.navigateOnGnByName("Manage Registry");
        }
        /*else {
           Wait.forPageReady();
           GlobalNav.openGlobalNav();
           Wait.forPageReady();
          GlobalNav.navigateOnGnByName(tab_name);
           Wait.forPageReady();
       }*/
    }

    @Then("^I click on add to registry button on shopping bag page$")
    public void iClickOnAddToRegistryButtonOnShoppingBagPage() throws Throwable {
        Wait.forPageReady();
        Clicks.clickWhenPresent("registry_add_to_bag.add_to_registry_button");
    }

    @And("^I click on \"([^\"]*)\" tab in left nav$")
    public void iClickOnTabInLeftNav(String arg0) throws Throwable {
        Clicks.clickWhenPresent("registry_bvr.view_registry");
    }

    @When("^I sign in with valid existing registry user credentials$")
    public void iSignInWithValidExistingRegistryUserCredentials() throws Throwable {

        TextBoxes.typeTextbox("sign_in.email", "salab01@gmail.com");
        TextBoxes.typeTextbox("sign_in.password", "test123");
        Wait.secondsUntilElementPresent("sign_in.sign_in_button", 5);
        Clicks.click("sign_in.sign_in_button");
        closeBcomPopup();
        closePopup();
        Wait.secondsUntilElementPresent("home.search_field", 6);
        closeAlert();
        closeMewTutorial();
        closeChatAlert();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);
        Wait.secondsUntilElementPresent("monetate_banners.monetate_lightbox", 15);
//        Clicks.click("monetate_banners.monetate_lightbox_close");
        Clicks.clickWhenPresent("monetage_banners.monetate_lightbox");
        Wait.untilElementPresent("footer.goto_sign_out_link");
    }

    @And("^I click on add to bag button$")
    public void iClickOnAddToBagButton() throws Throwable {
        Wait.forPageReady();
        Clicks.clickWhenPresent("product_display.add_to_bag_button");
    }

    @When("^I search for \"([^\"]*)\" in mobile global Search Input Field$")
    public void iSearchForInMobileGlobalSearchInputField(String search_str) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("home.search_field", 20);
        Elements.findElement(Elements.element("home.search_field")).sendKeys(search_str);
        Elements.findElement(Elements.element("home.search_field")).sendKeys(Keys.ENTER);
        Wait.secondsUntilElementPresent("home.search_field", 5);
        // Clicks.click(Elements.element("home.search_field"));
        Wait.forPageReady();
    }

    @When("^I signin as a (loyalty|non-loyalty) user$")
    public void iSigninAsALoyaltyUser(String user_type) throws Throwable {
        Wait.forPageReady();
        if (user_type.equalsIgnoreCase("loyalty")) {
            //checking for old profile works or not
            TextBoxes.typeTextbox("loyalty_sign_in.email", "testreg@qa10.com");
            TextBoxes.typeTextbox("loyalty_sign_in.password", "test123");
            Clicks.click("loyalty_sign_in.submit");

            //create new loyalty profile, if fails.
            if (Wait.secondsUntilElementPresent("loyalty_sign_in.login_error_msg", 10)) {
                logger.info("Creating new loyalty profile.");

                Clicks.click("loyalty_sign_in.loyalty_sign_up");
                Wait.forPageReady();
                String email = TestUsers.generateRandomEmail(0);
                TextBoxes.typeTextbox("loyalty_enrollment.first_name", TestUsers.generateRandomFirstName());
                TextBoxes.typeTextbox("loyalty_enrollment.last_name", TestUsers.generateRandomFirstName());
                TextBoxes.typeTextbox("loyalty_enrollment.address_line_1", "Hyderabad");
                TextBoxes.typeTextbox("loyalty_enrollment.address_line_2", "apt no 23");
                TextBoxes.typeTextbox("loyalty_enrollment.address_city", "San Fransisco");
                DropDowns.selectByText("loyalty_enrollment.address_state", "California - CA");
                TextBoxes.typeTextbox("loyalty_enrollment.address_zip_code", "94107");
                TextBoxes.typeTextbox("loyalty_enrollment.email", email);
                TextBoxes.typeTextbox("loyalty_enrollment.verify_email", email);
                TextBoxes.typeTextbox("loyalty_enrollment.phone_number", "4545256898");
                DropDowns.selectRandomValue("loyalty_enrollment.dob_month");
                DropDowns.selectRandomValue("loyalty_enrollment.dob_day");
                DropDowns.selectRandomValue("loyalty_enrollment.dob_year");
                TextBoxes.typeTextbox("loyalty_enrollment.password", "test123");
                TextBoxes.typeTextbox("loyalty_enrollment.verify_password", "test123");
                Clicks.click("loyalty_enrollment.terms_conditions");
                Clicks.clickWhenPresent("loyalty_enrollment.accept_terms_conditions");
                Clicks.clickWhenPresent("loyalty_enrollment.submit_btn");
                Wait.forPageReady("loyalty_enrollment_confirmation");

                Navigate.visit(RunConfig.url+"/account/signin?fromLoyalty=loyaltySignIn");
                Wait.forPageReady();
                Clicks.click("loyalty_home.become_a_loyallist_button");
            }
        } else {
            new UnifiedLoginSteps().iSignInWithValidCredentials();
        }
    }
}




