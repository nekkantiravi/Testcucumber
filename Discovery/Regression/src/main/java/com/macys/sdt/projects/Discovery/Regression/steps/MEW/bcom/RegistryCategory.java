package com.macys.sdt.projects.Discovery.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.projects.Discovery.Regression.utils.Login;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.steps.MEW.MyAccount;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;


/**
 * Created by YC05165 on 6/7/2017.
 */
public class RegistryCategory extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(RegistryCategory.class);
    private List<WebElement> regCategory;

    /**
     * Visits the home page, closes all popups that appear, and sign in or create account if specified type is registered
     *
     * @param registered user type "guest" or "registered"
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the mobile web site as a (registered) user with registry account$")
    public void I_visit_the_mobile_web_site_as_a_registered_user_registry_account(String registered) throws Throwable {
        Navigate.visit("home");
        Clicks.clickIfPresent("home.close_app_banner");
        if (StepUtils.bloomingdales() && StepUtils.mobile()) {
            Navigate.browserRefresh();
        }
        pausePageHangWatchDog();
        // close popup
        Clicks.clickIfPresent("home.popup_close");

        closeMewTutorial();
        Thread.sleep(5000);
        if (registered.equals("registered")) {
            Login.signIn();
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

    @Then("^I navigate to registry categories using mobile website$")
    public void i_navigate_to_registry_categories_using_mobile_website() throws Throwable {
        Wait.forPageReady();
/*        Wait.secondsUntilElementPresent("registry_category.site_menu",20);
        Clicks.click("registry_category.site_menu");
        Wait.secondsUntilElementPresent("registry_category.shop_all_product",30);
        Clicks.click("registry_category.shop_all_product");
        Navigate.scrollPage(0,200);*/
        Wait.secondsUntilElementPresent("registry_category.registry_menu",10);
        Clicks.click("registry_category.registry_menu");
        Wait.secondsUntilElementPresent("registry_category.manage_registry",10);
        Clicks.click("registry_category.manage_registry");
        Wait.secondsUntilElementPresent("registry_category.add_gifts_to_resgistry",10);
        Clicks.click("registry_category.add_gifts_to_resgistry");

    }

    @When("^I navigate to \"([^\"]*)\" category page and verified using mobile website$")
    public void I_navigate_to_category_page_and_verified_using_mobile_website(String arg1) throws Throwable {
        selectCategory(arg1);
        Wait.forPageReady();
    }


    @When("^I navigate the registry from global navigate menu$")
    public void i_navigate_the_registry_from_global_navigation_menu() throws Throwable {
        GlobalNav.openGlobalNav();
        Clicks.click("registry_category.the_registry");
    }

    @Then("^I login with manage registry user on mobile website$")
    public void i_login_with_manage_registry_user_on_mobile_website() throws Throwable {
        Wait.forPageReady();
        registryManageSignIn();
        Wait.forPageReady();
        Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("registrymanager"));

    }

    public static void registryManageSignIn() {
        Wait.forPageReady();
        typeEmailTextbox();
        typePassTextbox();
        Wait.secondsUntilElementPresentAndClick("registry_category.manage_button", 20);
        resumePageHangWatchDog();
    }

    public static void typeEmailTextbox() {
        Wait.secondsUntilElementPresent("registry_category.manage_email",30);
        String value = Elements.getValues("login.email").get(0).toString();
        TextBoxes.typeTextbox("registry_category.manage_email", value);

    }

    public static void typePassTextbox() {
        Wait.secondsUntilElementPresent("registry_category.manage_Pass", 30);
        String value = Elements.getValues("login.password").get(0).toString();
        TextBoxes.typeTextbox("registry_category.manage_Pass", value);
    }

    public void selectCategory(String category) throws Throwable {
        Wait.untilElementPresent("registry_category.registry_categoryList");
        Clicks.clickElementByText("registry_category.registry_categoryList", category);
        GlobalNav.closeGlobalNav();
        if (category.equals("Dining")) {
            Wait.secondsUntilElementPresent("registry_category.registry_categoryList", 20);
            Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Dining page", Wait.until(() -> Elements.getText("registry_category.cat_page").equals("Dining & Entertaining")));
            Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to correct page", Elements.getText("registry_category.cat_page").contains("Dining"));
            Assert.assertTrue("Response Code is not valid",DsvActions.responseCode(WebDriverManager.getCurrentUrl()));

        }else if (category.equals("Sale")) {
            Wait.secondsUntilElementPresent("registry_category.registry_categoryList", 20);
            Assert.assertTrue("ERROR - APP: " + category + " FOB link not navigated to Sale page", Wait.until(() -> Elements.getText("registry_category.cat_sale_page").equals("Sale")));
            Assert.assertTrue("ERROR - APP: Sale list is not present on page",Elements.elementPresent("registry_category.cat_sale"));
            Assert.assertTrue("Response Code is not valid",DsvActions.responseCode(WebDriverManager.getCurrentUrl()));

        }else {
            Wait.secondsUntilElementPresent("registry_category.registry_categoryList", 10);
            Assert.assertEquals("ERROR - APP: " + category + " FOB link not navigated to correct page", category, Elements.getText("registry_category.cat_page"));
            Assert.assertTrue("Response Code is not valid",DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
        }
    }
}



