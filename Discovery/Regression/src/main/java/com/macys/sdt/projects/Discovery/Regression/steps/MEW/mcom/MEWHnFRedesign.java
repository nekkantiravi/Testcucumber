package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by yh00756 on 9/29/2017.
 */
public class MEWHnFRedesign extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(MEWHNFComponentization.class);
    private static String search_keyword = null;

    @Then("I should see page is navigated to home page$")
    public void i_shouldseepage_is_navigated_to_homepage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("'ERROR - APP: Page not navigated to Homepage'", WebDriverManager.getCurrentUrl().contains("cm_sp=mew_navigation-_-menu-_-shop"));
    }

    @When("^I click on sale clearance category from body of the home page$")
    public void i_click_on_sale_clearance_category() throws Throwable {
        Wait.forPageReady();
        Clicks.click("mew_hnf.sale_clearance_category");
    }

    @When("^I click on sale last act category from body of the home page$")
    public void i_click_on_sale_last_act_category() throws Throwable {
        Wait.forPageReady();
        Clicks.click("mew_hnf.sale_lastact_category");
    }

    @When("^I click on \"([^\"]*)\" category in registry home page$")
    public void iClickOnCategoryInRegistryHomePage(String categoryName) throws Throwable {
        Assert.assertTrue("Error - Env: " + categoryName + " category is not available on home page", Elements.elementPresent(By.linkText(categoryName)));
        Clicks.click(By.linkText(categoryName));
        Wait.forPageReady();
    }

    @And("^I should see global navigation panel is expanded$")
    public void iShouldSeeGlobalNavigationPanelIsExpanded() throws Throwable {
        Wait.untilElementPresent("home.global_nav_visible");
        Assert.assertTrue("Error - App: GN is not expanded", Elements.elementPresent("home.global_nav_visible"));
    }
}
