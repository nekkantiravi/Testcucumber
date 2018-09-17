package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Regression.actions.website.FacetSelections;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Elements.findElement;
import static com.macys.sdt.framework.interactions.Elements.findElements;


public class BrowseFacetSelection extends StepUtils {

    private List<String> oldAPRIds = null;
    public String aprKeyword = null;

    @Then("^I select random facet value from any facet section in browse page$")
    public void I_select_random_facet_value_from_any_facet_section_in_browse_page() throws Throwable {
        FacetSelections.randomFacetSelectionBrowsePage();
    }

    @And("^I select the random value in the \"([^\"]*)\" facet$")
    public void iSelectTheRandomValueInTheFacet(String facetName) throws Throwable {
        FacetSelections.randomFacetValueSelection(facetName);

    }

    @And("^I set the \"([^\"]*)\" cookie$")
    public void iSetTheCookie(String setsegmentCookie) throws Throwable {
        String segmentCookie = Cookies.getCookieValue("SEGMENT");
        //If experiment cookie is not already added.
        if (!segmentCookie.contains(setsegmentCookie)) {
            Cookies.addSegment(setsegmentCookie);
            Navigate.browserRefresh();
            Wait.forPageReady();
        }
    }

    @And("^I (should|should not) see APR panel with \"([^\"]*)\" header and \"([^\"]*)\" APR at max$")
    public void iShouldSeeAPRPanelWithHeaderAndAPRAtMax(String condition, String aprTitle, int aprSize) throws Throwable {
        if (condition.equalsIgnoreCase("should")) {
            WebElement autoSuggestionElement = Elements.findElements(Elements.element("header.suggestions_list"))
                    .stream()
                    .filter(e -> e.isDisplayed()).collect(Collectors.toList()).get(0);
            Clicks.hoverForSelection(autoSuggestionElement);
            Assert.assertTrue("ERROR - ENV: The product recommendations list did not load", Wait.secondsUntilElementPresent("header.apr_panel_title", 30));
            Assert.assertTrue("ERROR - APP: APR panel Title is not matching with " + aprTitle, findElement("header.apr_panel_title").getText().equals(aprTitle));
            List<WebElement> actualAPRIdElements = findElements("header.apr_panel_products");
            oldAPRIds = actualAPRIdElements.stream().map(ele -> ele.getAttribute("data-product-id")).collect(Collectors.toList());
            Assert.assertTrue("ERROR - APP: More than " + aprSize, oldAPRIds.size() <= aprSize);
        } else {
            Assert.assertNull("ERROR - APP: APR panel Title is displaying ", findElement("header.apr_panel_title"));
            Assert.assertNull("ERROR - APP: Auto product recommendations are displaying ", findElement("header.apr_panel_products"));
        }
    }

    @When("^I select random APR and navigate to PDP$")
    public void iSelectRandomAPRAndNavigateToPDP() throws Throwable {
        List<WebElement> aprProductEles = Elements.findElements("header.apr_panel_products").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList());
        if (aprProductEles.isEmpty()) {
            WebElement autoSuggestionElement = Elements.findElements(Elements.element("header.suggestions_list"))
                    .stream()
                    .filter(e -> e.getText().equalsIgnoreCase(aprKeyword)).collect(Collectors.toList()).get(0);
            Clicks.hoverForSelection(autoSuggestionElement);
            aprProductEles = Elements.findElements("header.apr_panel_products").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList());
        }
        Wait.secondsUntilElementPresent("header.apr_panel_products", 10);
        Assert.assertFalse("ERROR - DATA: Auto Product Recommendations are not available for auto suggestion text:"+aprKeyword, aprProductEles.isEmpty());
        int index = aprProductEles.size() > 0 ? new Random().nextInt(aprProductEles.size() - 1) : 0;
        Clicks.click(aprProductEles.get(index));
        shouldBeOnPage("product_display");
    }

    @When("^I mouse over on the random auto suggestion text$")
    public void iMouseOverOnTheRandomAutoSuggestionText() throws Throwable {
        Wait.untilElementPresent("header.suggestions_list");
        List<WebElement> autoSuggestionElements = Elements.findElements(Elements.element("header.suggestions_list"))
                .stream()
                .filter(e -> !e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        Random randomGenerator = new Random();
        int randomAutoSuggestion = randomGenerator.nextInt(autoSuggestionElements.size());
        WebElement randomHoverElement = autoSuggestionElements.get(randomAutoSuggestion);
        aprKeyword = randomHoverElement.getText();
        Clicks.hoverForSelection(randomHoverElement);
        DiscoveryHelper.logInfo("Hovered on random auto suggestion text");
    }

    @And("^I click on search icon$")
    public void iClickOnSearchIcon() throws Throwable {
        Clicks.click("home.search_button");
        DiscoveryHelper.logInfo("Clicked on Search button");
    }

    @Then("^I should see APR panel updated for different suggestion text$")
    public void iShouldSeeAPRPanelUpdated() throws Throwable {
        Wait.secondsUntilElementPresent("header.apr_panel_title", 30);
        List<String> currentAPRIds = findElements("header.apr_panel_products").stream().map(ele -> ele.getAttribute("data-product-id")).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP: APR panel is not updated for hovering on different suggestion", currentAPRIds != oldAPRIds);
    }
}

