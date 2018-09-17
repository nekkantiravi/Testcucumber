package com.macys.sdt.projects.Stores.STAT.steps.app;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Stores.STAT.actions.app.GlobalNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AppiumTest extends StepUtils {

    @Given("^I am on Home screen$")
    public void iSelectContinueOnTheWelcomeScreen() throws Throwable {
        // close popup
        if (Elements.elementPresent("global_nav.store_market_popup_close"))
            Clicks.click("global_nav.store_market_popup_close");

        if (Wait.untilElementPresent("home.get_started_button")) {
            Clicks.click("home.get_started_button");
            Clicks.click("location_notification.not_now_button");
            Clicks.click("push_notification.not_now_button");
        }
    }

    @When("^I go to menu$")
    public void iGoToMenu() throws Throwable {
        new GlobalNavigation().openGlobalNav();
    }

    @Then("^Menu screen is opened$")
    public void menuScreenIsOpened() throws Throwable {
        Elements.elementShouldBePresent("global_nav.tier_1_categories");
    }

    @And("^The following elements are displayed$")
    public void theFollowingElementsAreDisplayed(List<String> elements) throws Throwable {
        List<String> visibleElements = Elements.findElements("global_nav.left_items").stream()
                .map(WebElement::getText).collect(Collectors.toList());

        for (int i = 0; i < elements.size(); i++) {
            Assert.assertTrue("Unable to find item: " + elements.get(i), elements.contains(visibleElements.get(i)));
        }
    }

    @Given("^I navigate to the first StoreDetailsPage for the search term \"([^\"]*)\"$")
    public void iNavigateToTheFirstStoreDetailsPageForTheSearchTerm(String search) throws Throwable {
        Clicks.click("home.come_see_us_message");
        TextBoxes.typeTextNEnter("stores.search_bar", search);
    }

    @Then("^I verify I can search for a store and see its details$")
    public void iVerifyICanSearchForAStoreAndSeeItsDetails() throws Throwable {
        List<WebElement> stores = Elements.findElements("stores.stores");
        Assert.assertTrue("Unable to search for stores!", !stores.isEmpty());
    }

    @And("^I navigate the app global navigation menu as follows:$")
    public void iNavigateTheAppGlobalNavigationMenuAsFollows(List<String> path) throws Throwable {
        new GlobalNavigation().navigateGlobalNav(path);
    }

    @And("^I select a random product$")
    public void iSelectARandomProduct() throws Throwable {
        Clicks.clickRandomElement("category_browse.products");
        Utils.threadSleep(5000, null);
    }
}
