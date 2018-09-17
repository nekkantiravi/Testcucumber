package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Admin on 10/07/17.
 */
public class Registry extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(SearchCheckout.class);

    @When("^I add the product to registry using mobile website$")
    public void i_add_the_product_to_registry_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        Clicks.clickIfPresent("home.close_app_banner");
        Clicks.click("registry_category.add_to_registry");
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_category.registry_page",30);
        if(Elements.elementPresent("registry_category.registry_page")){
            registrysignIn();

        }
    }

    public static void registrysignIn() {
        Wait.forPageReady();
        typeEmailTextbox();
        typePassTextbox();
        Wait.secondsUntilElementPresent("registry_category.registry_signIn",20);
        Clicks.click("registry_category.registry_signIn");
        resumePageHangWatchDog();
    }

    public static void typeEmailTextbox() {
        Wait.secondsUntilElementPresent("registry_category.login_email",30);
        String value = Elements.getValues("login.email").get(0).toString();
        TextBoxes.typeTextbox("registry_category.login_email", value);

    }

    public static void typePassTextbox() {
        Wait.secondsUntilElementPresent("registry_category.login_pass", 30);
        String value = Elements.getValues("login.password").get(0).toString();
        TextBoxes.typeTextbox("registry_category.login_pass", value);
    }


    @Then("^I navigate to manage your registry page and I sign in with registry credentials using mobile website$")
    public void i_navigate_to_manage_your_registry_page_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_category.registry_managelink",20);
        Clicks.click("registry_category.registry_managelink");
        Wait.forPageReady();
        registrysignIn();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_category.registry_manager",30);
        Assert.assertTrue("registry page is not displayed",Elements.elementPresent("registry_category.registry_manager"));
    }

    @And("^I select category from registry manager page using mobile website$")
    public void i_select_category_from_registry_manager_page_using_mobile_website() throws Throwable {
        Wait.secondsUntilElementPresent("registry_category.selct_registrycategory",20);
        Clicks.click("registry_category.selct_registrycategory");
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_category.select_product",20);
        Clicks.click("registry_category.select_productt");
        Clicks.click("registry_category.select_productt");
    }

    @Then("^I add the product to a registry and navigate to bvr using mobile website$")
    public void i_add_the_product_to_a_registry_and_navigate_to_bvr_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_category.add_registry", 20);
        Clicks.click("registry_category.add_registry");
        Wait.forPageReady();
        Wait.secondsUntilElementPresentAndClick("add_to_registry_overlay.view_registry", 10);
        Wait.forPageReady();
        Wait.untilElementPresent("registry_bvr.add_to_bag_btn");
        Clicks.click("registry_bvr.add_to_bag_btn");
        Wait.secondsUntilElementPresentAndClick("registry_add_to_bag.registry_chkout_button", 10);
        shouldBeOnPage("shopping_bag");
    }

    @When("^I select random product$")
    public void i_select_random_product() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_category.select_product", 20);
        Clicks.click("registry_category.select_product");
    }

    @When("^I navigate wedding registry page$")
    public void I_navigate_wedding_registry_page() throws Throwable {
        GlobalNav.openGlobalNav();
        Wait.secondsUntilElementPresent("registry_category.registry_wedding_button", 20);
        Clicks.click("registry_category.registry_wedding_button");
        Wait.forPageReady();
        GlobalNav.closeGlobalNav();
        Assert.assertTrue("ERROR-ENV: Unable to navigate wedding registry page", Elements.elementPresent(Elements.element("registry_home.goto_create_registry")));
    }
}
