package com.macys.sdt.projects.Discovery.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom.SearchAndBrowse;
import com.macys.sdt.shared.actions.MEW.panels.FacetAccordionModel;
import com.macys.sdt.shared.actions.MEW.panels.Pagination;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.WSSGBrowseService;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.WSSGSearchService;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;
import static com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom.SearchAndBrowse.search_term;
import static com.macys.sdt.shared.actions.MEW.panels.FacetAccordionModel.selectedFacetValues;
import static com.macys.sdt.shared.steps.MEW.PageNavigation.runMode;

public class FacetSelections {

    private static final Logger log = LoggerFactory.getLogger(FacetSelections.class);
    public static String selected_subfacet;
    public static String facetName;
    public static String selectedFacet;

    @And("^I select \"([^\"]*)\" facet from panel$")
    public void iSelectFacetFromPanel(String filter_type) throws Throwable {
        Wait.untilElementPresent("search_result.facet_select");
        facetName = filter_type;
        if (Elements.findElements("search_result.facet_select").stream().map(WebElement::getText).collect(Collectors.toList()).contains(filter_type)) {
            Clicks.clickElementByText("search_result.facet_select", filter_type);
            log.info(filter_type + "facet is selected from panel");
        } else {
            Assert.fail("Filter type is not displayed in the panel");
        }
    }

    @And("^I tap on show more for all filters$")
    public void iTapOnShowMoreForAllFilters() throws Throwable {
        Clicks.clickWhenPresent("left_facet.show_more");
        log.info("Clicked on show more");
        if (Wait.untilElementPresent("left_facet.hidden_facets")) {
            Elements.elementPresent("search_result.facet_select");
            log.info("Verified the filters display by clicking on show more");
        } else {
            Assert.fail("All filters are not displayed");
        }
    }

    @And("^I select \"([^\"]*)\" facet from the filters section$")
    public void iSelectFacetFromTheFiltersSection(String size) throws Throwable {
        Wait.forPageReady();
        facetName = size;
        Clicks.clickElementByText("search_result.facet_select", size);
        log.info("Size facet is selected from panel");
        List<WebElement> sizes = Elements.findElement("left_facet.sizes").findElements(By.className("wrapped-size-name"));
        WebElement randomSize = sizes.get(new Random().nextInt(sizes.size()));
        selectedFacet = randomSize.getText();
        Clicks.click(randomSize);
        Assert.assertTrue("Size category not expanded", randomSize.getAttribute("aria-expanded").equals("true"));
        log.info("Selected" + randomSize.getText() + "facet from size");
    }

    @And("^I reselect \"([^\"]*)\" facet from the filters section$")
    public void iReSelectFacetFromTheFiltersSection(String size) throws Throwable {
        Wait.forPageReady();
        Clicks.clickElementByText("search_result.facet_select", size);
        log.info("Size facet is selected from panel");
        WebElement selectedSize = Elements.findElement("left_facet.sizes").findElements(By.className("wrapped-size-name")).stream()
                .filter(webElement -> webElement.getText().equals(selectedFacet)).collect(Collectors.toList()).get(0);
        Clicks.click(selectedSize);
        Assert.assertTrue("Size category not expanded", selectedSize.getAttribute("aria-expanded").equals("true"));
        log.info("Selected" + selectedSize.getText() + "facet from size");
    }

    @And("^I click apply for the facet selected$")
    public void iClickApplyForTheFacetSelected() throws Throwable {
        FacetAccordionModel.apply();
        log.info("Selected apply button");
    }

    @Then("^I should see a facet values \"([^\"]*)\" page breadcrumb$")
    public void iShouldSeeAFacetValuesDLPPageBreadcrumb(String page) throws Throwable {
        if (page.equals("DLP")) {
            shouldBeOnPage("dynamic_landing");
            List<String> breadcrumbs = Elements.findElements("search_result.chip").stream().map(WebElement::getText).collect(Collectors.toList());
            if (selected_subfacet.contains("\n")) {
                Assert.assertTrue("Selected breadcrumbs are displayed", breadcrumbs.contains(selected_subfacet.trim().replaceAll("\n", ", ")));
            } else {
                Assert.assertTrue("Selected breadcrumbs are displayed", breadcrumbs.contains(selected_subfacet.trim()));
            }
        }
    }

    @And("^I should not see selected facet values in facet selection screen$")
    public void iShouldNotSeeSelectedFacetValuesInFacetSelectionScreen() throws Throwable {
        Assert.assertFalse("Selected facets are removed", Elements.findElements("search_result.chip").stream().
                map(WebElement::getText).collect(Collectors.toList()).containsAll(selectedFacetValues));
        log.info("Selected facets are removed");
    }

    @And("^I select random \"([^\"]*)\" facet from the selected category$")
    public void iSelectRandomFacetFromTheSelectedCategory(String facet) throws Throwable {
        if(facet.equals("Size")){
            List<WebElement> unselected_facets;
            selectedFacetValues = new ArrayList<>();
            List<WebElement> expanded_size_values = Elements.findElement("left_facet.size_type").findElements(By.tagName("li"));
                unselected_facets = expanded_size_values.stream().filter(ele -> ele.isDisplayed() && !ele.getText().isEmpty()
                        && !ele.isSelected()).collect(Collectors.toList());
                if (unselected_facets.isEmpty())
                    Assert.fail("Error Data:- facet values not available");
            unselected_facets.removeAll(expanded_size_values.stream().filter(element -> element.getAttribute("class")
                    .contains("facet-value-selected")).collect(Collectors.toList()));
            WebElement random_size_type = unselected_facets.get(new Random().nextInt(unselected_facets.size()));
            selectedFacetValues.add(random_size_type.getText().replaceAll("\n", ", ").trim());
            selected_subfacet=random_size_type.getText().replaceAll("\\[\\d+\\]", "");
            Clicks.click(random_size_type);
        }else {
            List<WebElement> subfacet = Elements.findElement("search_result.sub_facets").findElements(By.tagName("li")).stream()
                    .filter(ele -> !ele.getAttribute("class").contains("facet-value-none")).collect(Collectors.toList());
            WebElement randomSubFacet = subfacet.get(new Random().nextInt(subfacet.size()));
            Clicks.click(randomSubFacet);
            selected_subfacet = randomSubFacet.getText().replaceAll("\\[\\d+\\]", "");
            log.info("Selected" + selected_subfacet + "facet from the filter");
        }
    }

    @And("^I select multiple facets within a single facet category$")
    public void iSelectMultipleFacetsWithinASingleFacetCategory() throws Throwable {
        FacetAccordionModel.multipleFacets(facetName);
        log.info("Clicked on multiple facets");
    }

    @And("^I should see the breadcrumbs displayed$")
    public void iShouldSeeTheBreadcrumbsDisplayed() throws Throwable {
        if (Elements.findElement("pagination.loading_mask").isDisplayed())
            Wait.untilElementNotPresent("pagination.loading_mask");
        Utils.threadSleep(5000, "ERROR - APP: Unable to view the breadcrumbs selected");
        List<String> breadcrumbs = Elements.findElements("search_result.chip").stream().map(WebElement::getText).collect(Collectors.toList());
        for (String facetElement : selectedFacetValues) {
            Assert.assertTrue("ERROR - APP :: Selected facet values not shown as breadcrumbs", breadcrumbs.contains(facetElement.trim()));
        }
        log.info("Verified the selection of multiple breadcrumbs");
    }

    @When("^I clear the breadcrumbs selected$")
    public void iClearTheBreadcrumbsSelected() throws Throwable {
        FacetAccordionModel.removeFacet();
    }

    @Then("^I should not see the facets on breadcrumb panel$")
    public void iShouldNotSeeTheFacetsOnBreadCrumbPanel() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.chip", 10);
        Assert.assertTrue("ERROR - APP:: Facets are visible in bread crumb panel", Elements.findElements("search_result.chip").isEmpty());
        log.info("Verified the facets de-selection in the bread crumb panel");
    }

    @Then("^I should not see the facets as selected$")
    public void iShouldNotSeeTheFacetsAsSelected() throws Throwable {
        List<WebElement> selectedFacetValues = Elements.findElement("search_result.sub_facets").findElements(By.tagName("li")).stream()
                .filter(webElement -> webElement.getAttribute("aria-checked").equals("true")).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP:: Facet values still showing as selected", selectedFacetValues.isEmpty());
        log.info("Verified the facets de-selection in the category");
    }

    @Then("^I should not see the price facets as selected$")
    public void iShouldNotSeeThePriceFacetsAsSelected() throws Throwable {
        List<WebElement> prices = Elements.findElement("search_result.sub_facets").findElements(By.tagName("li")).stream().
                map(webElement -> webElement.findElement(By.className("display-name"))).collect(Collectors.toList());
        for (WebElement price : prices) {
            Assert.assertTrue("ERROR - APP :: " + price.getText() + " is showing as selected", !price.isSelected());
        }
        log.info("Verified the facets de-selection in the category");
    }

    @When("^I tap on clear all button$")
    public void iTapOnClearAllButton() throws Throwable {
        Clicks.click("search_result.clearall_button");
        log.info("Clicked on CLEAR ALL button");
    }

    @And("^I should see that selected facet and breadcrumb facet is same$")
    public void iShouldSeeOnlyProductsInBcomBrowsePageWithSelectedFacetValues() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.chip", 10);
        Assert.assertTrue("ERROR - App: breadcrumb value is not displayed correctly",
                Elements.findElement("search_result.facet_breadcrumb").getText().equals(selected_subfacet.trim()));
        log.info("Verified products are displayed for the selected facet values only");
    }

    @And("^I should see that selected facets and breadcrumb facets are same$")
    public void iShouldSeeOnlyProductsInBcomBrowsePageWithSelectedMultipleFacetValues() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.chip", 10);
        List<String> breadcrumb_facets = Elements.findElements("search_result.chip").stream().map(WebElement::getText).collect(Collectors.toList());
        Collections.sort(breadcrumb_facets);
        Collections.sort(FacetAccordionModel.selectedFacetValues);
        Assert.assertEquals("ERROR - App: breadcrumb values are not displayed correctly",
                breadcrumb_facets, FacetAccordionModel.selectedFacetValues);
        log.info("products are displayed for the selected facet values only");
    }

    @Then("^I should see the products without any facet values in bcom (browse|search|DLP) page$")
    public void iShouldSeeTheProductsWithoutAnyFacetValuesInBcomSearchPage(String type) throws Throwable {
        Wait.untilElementPresent("search_result.total_products");
        int count = type.equals("search") || type.equals("DLP") ? WSSGSearchService.getItemCount(search_term, runMode, null, String.valueOf(Pagination.currentPageNumber()), null)
                : WSSGBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()), runMode, null, null, null);
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        Assert.assertFalse("ERROR - App: breadcrumb value is not cleared", Elements.elementPresent("search_result.facet_breadcrumb"));
        log.info("Verified no facet values available in breadcrumb and products are displayed according to service response");
    }

    @And("^I expand the facets$")
    public void iExpandTheFacets() throws Throwable {
        Wait.secondsUntilElementPresent(By.className("show-more"), 10);
        Elements.findElement(By.className("show-more")).click();
    }

    @When("^I clear all the breadcrumb values$")
    public void iClearAllTheBreadcrumbValues() throws Throwable {
        FacetAccordionModel.removeFacets();
    }

    @Then("^I should see facet values updated based on previous selection for (browse|search) page$")
    public void iShouldSeeFacetValuesUpdatedBasedOnPreviousSelectionForBrowsePage(String type) throws Throwable {
        Map<String, List<Map<String, Integer>>> wssgFacets = type.equals("search") ?
                WSSGSearchService.getAllFacetValuesWithProdCount(search_term, runMode, null, null, SearchAndBrowseActions.selectedFacetValues()) :
                WSSGBrowseService.getAllFacetValuesWithProdCount(Integer.parseInt(new Browse().getCategoryId()), runMode, null, null, SearchAndBrowseActions.selectedFacetValues());
        wssgFacets = wssgFacets.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Map<String, List<Map<String, Integer>>> facetsWithValues = FacetAccordionModel.getAllFacetsWithValues();
        facetsWithValues = facetsWithValues.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Assert.assertEquals("ERROR - App: facet headers and values are not updated based on selection",
                wssgFacets, facetsWithValues);
        log.info("facet headers and values are updated based on selection");
    }
}
