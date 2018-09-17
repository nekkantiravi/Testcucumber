package com.macys.sdt.projects.Selection.UFT.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Registry extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(Registry.class);

    /**
     * URL should contains "registry/wedding/registrymanager" on registrymanager url post clicking on wedding registry
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^URL should contains \"([^\"]*)\" on registry manager page URL$")
    public void urlShouldContain(String expectedValue) throws Throwable {
        Assert.assertTrue("URL" + "not" + "contains" + expectedValue, WebDriverManager.getCurrentUrl().contains(expectedValue));
        logger.info("URL contains the expected value");
    }

    /**
     * user should see Registry Guide category
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see \"([^\"]*)\" category under Gift Category$")
    public void I_should_see_category_under_gift_category(String expectedMsg) throws Throwable {
        String actualMessage = Elements.getText("registry_home.registry_guide");
        Assert.assertEquals("Error Registry Guide category is not seeing", expectedMsg, actualMessage);
        logger.info("successfully see Registry Guide category");
    }

    /**
     * user should navigated to Registry Guide Page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" tab in the left nav$")
    public void I_select_tab_in_the_left_nav(String tab_name) throws Throwable {
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName(tab_name);
        logger.info("successfully navigated to Registry Guide Page");
    }

    /**
     * user should see correct URL
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see correct URL \"([^\"]*)\" on Registry Guide page$")
    public void iShouldSeeCorrectURLOnRegistryGuidePage(String expectedUrl) throws Throwable {
        String currentUrl = WebDriverManager.getCurrentUrl();
        Assert.assertEquals("Error expected URL is not seeing", expectedUrl, currentUrl);
        logger.info("successfully see correct URL");
    }

    @Then("^I should see 'link your in-store registry' on registry home page$")
    public void iShouldSeeLinkinRegistryClaimPage() throws Throwable {
        String actualHeader = Elements.findElement(Elements.element("registry_home.link_instore_registry")).getText();
        logger.info("Link is displayed as:"+ actualHeader);
        Assert.assertTrue("Link name is incorrect:", actualHeader.equals("Link Your In-Store Registry"));
    }

    @When("^I click on instore registry from registry home page$")
    public void iClickonLinkInstoreRegistry(){
         Clicks.click("registry_home.link_instore_registry");
    }

    @When("^I should see \"Link \" in the header and subtitle for instore registry page$")
    public void iShouldSeeLinkInHeaderAndTextBelow(){
        String actualHeader = Elements.findElement(Elements.element("registry_claim.link_registry_header")).getText();
        logger.info("Header is displayed as:"+ actualHeader);
        Assert.assertTrue("Header is incorrect:", actualHeader.equals("Link registry"));
        String actualHeader2 = Elements.findElement(Elements.element("registry_claim.link_registry_header_2")).getText();
        logger.info("Header2 is displayed as:"+ actualHeader2);
        Assert.assertTrue("Header2 is incorrect:", actualHeader2.contains("Link registry online"));
        String actualSubtitle = Elements.findElement(Elements.element("registry_claim.link_registry_subtitle")).getText();
        logger.info("Subtitle is displayed as:"+ actualSubtitle);
        Assert.assertTrue("Subtitle is incorrect:", actualSubtitle.contains("Enter your email address and registry ID to link and manage your registry online."));
    }

}
