package com.macys.sdt.projects.Selection.UFT.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UFTSelection extends StepUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UFTSelection.class);

    @Then("^I should see add to registry dialog on PDP page$")
    public void iShouldSeeAddToRegistryDialogOnPDPPage() throws Throwable {
        Assert.assertTrue("ERROR-APP: Add to Registry dailog is not visible",
                Wait.untilElementPresent("add_to_registry_dialog.add_to_bag_view_registry"));
        LOGGER.info("Add to Registry dailog is displayed on PDP page");
    }

    @And("^I navigate to gifts with completion page using left nav link$")
    public void iNavigateToGiftsWithCompletionPageUsingLeftNavLink() throws Throwable {
        Clicks.click("registry_navigation.goto_gifts_with_registry");
        LOGGER.info("Navigated to Gift with completion page using left nav link");
    }

    @And("^I select Access Your Registry link on (gifts with completion|registry benefits) page$")
    public void iSelectAccessYourRegistryLinkOnGiftsWithCompletionPage() throws Throwable {
        Clicks.click("wedding_gifts_with_registry.access_your_registry");
        LOGGER.info("Selected Access Your Registry link");
    }

    @Then("^I should be navigated to registry manager page$")
    public void iShouldBeNavigatedToRegistryManagerPage() throws Throwable {
        shouldBeOnPage("registry_manager");
        LOGGER.info("Successfully Navigated to Registry Manager page");
    }

    @And("^I shuffle between left and right arrow icons in 'May We Suggest' recommendation panel$")
    public void iShuffleBetweenLeftAndRightArrowIconsInMayWeSuggestRecommendationPanel() {
        String errorMessage = "ERROR - DATA: No recommendations are displayed on wishlist page";
        Assert.assertTrue(errorMessage, Wait.untilElementPresent("wish_list.wishlist_recommendations"));
        // Click on right arrow icon in recommendation panel until it is disabled
        while (Wait.untilElementPresent("wishlist_recommendations.right_nav_icon")) {
            Clicks.click("wishlist_recommendations.right_nav_icon");
        }
        // Click on left arrow icon in recommendation panel until it is disabled
        while (Wait.untilElementPresent("wishlist_recommendations.left_nav_icon")) {
            Clicks.click("wishlist_recommendations.left_nav_icon");
        }
        LOGGER.info("Clicked on right and left arrow icons in recommendation panel");
    }

    @Then("^I navigate to registry claim Page$")
    public void iNavigateToRegistryClaimPage() throws Throwable {
        Navigate.visit(RunConfig.url + "/registry/wedding/claim");
        LOGGER.info("Navigated to registry claim page");
    }

    @Then("^I should see 'link your in-store registry' header on registry claim page$")
    public void iShouldSeeLinkinRegistryClaimPage() throws Throwable {
        String actualHeader = Elements.findElement(Elements.element("registry_claim.registry_claim_header")).getText();
        Assert.assertTrue("Header is incorrect:", actualHeader.equals("link your in-store registry"));
        LOGGER.info("Header is displayed as:" + actualHeader);
    }

    @Then("^I should see 'Enter your email address & registry ID to link and start managing your registry online' text below to header$")
    public void iShouldSeeCorrectTextInRegistryClaimPage() throws Throwable {
        String actualText = Elements.findElement(Elements.element("registry_claim.registry_claim_text")).getText();
        Assert.assertTrue("Header is incorrect:", actualText.contains("Enter your email address & registry ID to link"));
        LOGGER.info("Text is displayed as:" + actualText);
    }

}