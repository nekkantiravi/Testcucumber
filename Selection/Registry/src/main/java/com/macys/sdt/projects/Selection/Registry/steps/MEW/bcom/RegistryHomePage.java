package com.macys.sdt.projects.Selection.Registry.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Selection.Registry.actions.MEW.RegistryActions;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Created by U063689 on 2/22/2017.
 */
public class RegistryHomePage extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);
    static RegistryActions RegistryActions = new RegistryActions();

    @Given("^I navigate to mobile registry home page$")
    public void navigate_to_mobile_registry_homepage() throws Throwable{
        new PageNavigation().I_visit_the_mobile_web_site_as_a_registered_user("guest");
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName("THE REGISTRY");
        GlobalNav.shouldBeOnPage("registry_home");
    }

    @When("^I select \"([^\"]*)\" tab in the left nav$")
    public void iSelectTabInTheLeftNav(String tab_name) throws Throwable {
        if(tab_name.equalsIgnoreCase("registry manager")){
            GlobalNav.openGlobalNav();
            Clicks.click(Elements.findElement(By.linkText("Manage Registry")));
            GlobalNav.closeGlobalNav();
            GlobalNav.navigateOnGnByName("Registry Manager");
        }
        else {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName(tab_name);
        }
    }

    @Then("^I should land on the registry mobile home page$")
    public void iShouldLandOnTheRegistryMobileHomePage() throws Throwable {
        GlobalNav.shouldBeOnPage("registry_home");
    }

    @And("^I should verify that the domain is secure-m$")
    public void verify_domain_is_secure_m() throws Throwable {
        WebDriverManager.getWebDriver().getCurrentUrl().contains("secure-m");
    }

    @When("^I click on create registry$")
    public void i_click_on_create_registry(){
        Clicks.click("registry_home.goto_create_registry");
    }

    @Then("^I should verify that I land on create registry page$")
    public void land_on_create_registry_page() throws Throwable {
        GlobalNav.shouldBeOnPage("registry_capture_email_page");
    }


    @When("^I sign in with invalid credentials in Manage section on mobile registry home page page$")
    public void sign_in_invalid_account_manage_section() throws Throwable {
        TextBoxes.typeTextbox("registry_home.sign_in_email", "someemail@gmail.com");
        TextBoxes.typeTextbox("registry_home.sign_in_password", "password");
        Clicks.click("registry_home.sign_in_button");
    }

    @Then("^I should land on Let's Get Started page$")
    public void verify_on_lets_get_started_page() throws Throwable {
        Wait.forPageReady();
        GlobalNav.onPage("registry_capture_email_page");
    }


    @And("^I click Manage button$")
    public void click_manage_button() throws Throwable {
        Clicks.click("registry_home.sign_in_button");
    }

    @Then("^I should land on Registry Manager page$")
    public void verify_on_registry_manager_page() throws Throwable {
        GlobalNav.shouldBeOnPage("registry_manager");
    }


    @And("^I should see the error message displayed:$")
    public void iShouldSeeTheErrorMessageDisplayed(final String fileContent) throws Throwable {
        if(Elements.findElement(By.xpath("//div[@class='registry-redesign alert error']")).getText().toLowerCase().contains(fileContent.toLowerCase())){
            logger.info("Error message for invalid login is verified");
        }
        else {
            Assert.fail("Error message for invalid login is not verified");
        }
    }

    @And("^I go to claim registry account online$")
    public void claim_registry_account_online() throws Throwable {
        if(onPage("registry_home")){
           Clicks.click("registry_home.goto_start_here");
        }
        else {
            Assert.fail("User is currently not in Mobile Registry Home Page");
        }
    }

    @Then("^I should land on Locating Your Registry page$")
    public void verify_on_registry_claim_page() throws Throwable {
        Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("/registry/wedding/claim"));
    }

    @And("^I navigate to \"([^\"]*)\" page from registry mobile home page$")
    public void iNavigateToPageFromRegistryMobileHomePage(String link) throws Throwable {
        switch (link.toLowerCase()) {
            case "benefits and perks":  Clicks.click("registry_home.benefits_and_perks_link");
                break;
            case "where to start":  Clicks.click("registry_home.where_to_start_link");
                break;
            case "little registry guide":  Clicks.click("registry_home.vow_to_wow_link");
                break;
        }
    }

    @And("^I should land on \"([^\"]*)\" page$")
    public void iShouldLandOnPage(String page_name) throws Throwable {
        switch (page_name.toLowerCase()) {
            case "benefits and perks":  WebDriverManager.getWebDriver().getCurrentUrl().toLowerCase().contains("/registry/wedding/benefits-perks");
                break;
            case "where to start":  WebDriverManager.getWebDriver().getCurrentUrl().toLowerCase().contains("/registry/wedding/checklist");
                break;
            case "little registry guide":  WebDriverManager.getWebDriver().getCurrentUrl().toLowerCase().contains("little-registry-guide");
                break;
        }
    }

    @When("^I search for non-existing couple's registry using mobile site$")
    public void iSearchForNonExistingCoupleSRegistryUsingMobileSite() throws Throwable {
        TextBoxes.typeTextbox("registry_home.search_first_name", "somename");
        TextBoxes.typeTextbox("registry_home.search_last_name", "somelastname");
        Clicks.click("registry_home.find_button");
    }

    @Then("^I should land on find registry page with search results$")
    public void iShouldBeNavigatedToRegistrySearchResultsPage() throws Throwable {
        GlobalNav.shouldBeOnPage("find_registry");
        Assert.assertTrue("Search results are not present on the page", Elements.elementPresent("find_registry.search_results"));
    }

    @Then("^I should verify the title tag is:$")
    public void iShouldVerifyTheTitleTagIs(String title_tag) throws Throwable {
        WebDriverManager.getWebDriver().getTitle().toLowerCase().contains(title_tag);
    }

    @Then("^I should land on registry log in page$")
    public void iShouldLandOnRegistryLogInPage() throws Throwable {
        Wait.forPageReady();
        GlobalNav.onPage("registry_log_in");
    }

    @When("^I sign in with valid registry credentials in Manage section on mobile registry home page$")
    public void iSignInWithValidCredentialsInManageSectionOnMobileRegistryHomePagePage() throws Throwable {
        Wait.forPageReady();
        Clicks.clickIfPresent("home.goto_sign_out_link");
        RegistryActions.createRegistryAccountAndSignOut();
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName("THE REGISTRY");
        GlobalNav.closeGlobalNav();
        GlobalNav.shouldBeOnPage("registry_home");
        TextBoxes.typeTextbox("registry_home.sign_in_email", RegistryActions.getEmail());
        TextBoxes.typeTextbox("registry_home.sign_in_password", RegistryActions.getPassword());
        Elements.findElement("registry_home.sign_in_button").click();
    }


    @When("^I search for the existing couple's registry using mobile registry homepage$")
    public void iSearchForExistingRegistryMobileRegistryHP() throws Throwable{
        RegistryActions.createRegistryAccountAndSignOut();
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName("THE REGISTRY");
        GlobalNav.closeGlobalNav();
        GlobalNav.shouldBeOnPage("registry_homepage");
        TextBoxes.typeTextbox("registry_homepage.find_first_name", RegistryActions.getRegistrantFN());
        TextBoxes.typeTextbox("registry_homepage.find_last_name", RegistryActions.getRegistrantLN());
        WebDriverManager.getWebDriver().findElement(By.id("regSearchNameBtn")).click();
    }

    @And("^I should see searched name in the result set$")
    public void iShouldSeeSearchedNameInResults() throws Throwable {
       // Elements.elementPresent("registry_homepage.find_first_name");
        if (onPage("find_registry")) {
            Clicks.clickIfPresent("find_registry.show_all_button");
            List<WebElement> registrantNames = Elements.findElements(By.className("registrant-name"));
            Iterator<WebElement> itr = registrantNames.iterator();
            for (WebElement element : registrantNames) {
                if (element.getText().equalsIgnoreCase(RegistryActions.getRegistrantFN()) && itr.next().getText().toLowerCase().contains(RegistryActions.getRegistrantLN().toLowerCase())) {
                    Assert.assertTrue(element.getText().toLowerCase().contains(RegistryActions.getCoregistrantFN().toLowerCase()));
                    Assert.assertTrue(element.getText().toLowerCase().contains(RegistryActions.getCoregistrantLN().toLowerCase()));
                    break;
                }
                registrantNames.listIterator().next();
            }
        }
        else {
            logger.info("Not on Search Result page");
        }
    }

    @Then("^I should land on registry search results page$")
    public void iShouldLandOnRegistrySearchResultsPage() throws Throwable {
        onPage("find_registry");
    }

    @Given("^I select registry manager tab in the left nav$")
    public void iSelectRegistryManagerTabInTheLeftNav() throws Throwable {
            GlobalNav.openGlobalNav();
            Clicks.click(Elements.findElement(By.linkText("Manage Registry")));
            GlobalNav.closeGlobalNav();
            Clicks.click("registry_log_in.registry_manage");
    }
}


