package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.shared.actions.MEW.panels.FacetAccordionModel;
import com.macys.sdt.shared.actions.MEW.panels.Pagination;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.WSSGBrowseService;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.WSSGSearchService;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.XAPIBrowseService;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.XAPISearchService;
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

public class FacetSelections {

    private static final Logger log = LoggerFactory.getLogger(FacetSelections.class);
    private static String selectedFacetValue;

    @When("^I filter the products by price \"([^\"]*)\"$")
    public void iFilterTheProductsByPrice(String value) throws Throwable {
        Wait.forPageReady();
        FacetAccordionModel.selectFacetByName("Price");
        FacetAccordionModel.selectFacetValueByName(value);
        FacetAccordionModel.apply();
        log.info("filtered the products based on price range "+value);
    }

    @And("^I select \"([^\"]*)\" sort order on facet accordion model$")
    public void iSelectSortOrderOnfacetAccordionModel(String toSelect) throws Throwable {
        Wait.forPageReady();
        DropDowns.selectByText("search_result.sort_by_select", toSelect);
        Wait.forLoading(By.id("loading_mask"));
        Wait.forPageReady();
        log.info(toSelect + " sort order is selected");
        Clicks.click("search_result.apply");
        log.info("filtered the products based on price range "+toSelect);
    }

    @Then("^I should see only products in (browse|search|DLP) page with selected facet values$")
    public void iShouldSeeOnlyProductsInSearchPageWithSelectedFacetValues(String type) throws Throwable {
        Map<String, String> filters = new HashMap<>();
        filters.put("pathname", WebDriverManager.getWebDriver().getCurrentUrl().split("\\.com")[1].split("\\?")[0]);
        Wait.untilElementPresent("search_result.total_item_count");
        int count = type.equals("search")  || type.equals("DLP")? XAPISearchService.getItemCount(SearchAndBrowse.search_term,filters) :
                XAPIBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()), filters);
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ", count,
                SearchAndBrowseActions.getItemCount());
        Assert.assertTrue("ERROR - App: breadcrumb value is not displayed correctly",
                Elements.findElement("search_result.facet_breadcrumb").getText().equals(FacetAccordionModel.selectedFacetValue));
        log.info("Verified products are displayed for the selected facet values only");
    }

    @And("^I select multiple facets$")
    public void iSelectMutlipleFacets() throws Throwable {
        FacetAccordionModel.selectMultipleFacets();
    }

    @Then("^I should see only products in (browse|search|DLP) page with selected multiple facet values$")
    public void iShouldSeeOnlyProductsInSearchPageWithSelectedMultipleFacetValues(String type) throws Throwable {
        Map<String, String> filters = new HashMap<>();
        filters.put("pathname", WebDriverManager.getWebDriver().getCurrentUrl().split("\\.com")[1].split("\\?")[0]);
        Wait.untilElementPresent("search_result.total_item_count");
        int count = type.equals("search") || type.equals("DLP") ? XAPISearchService.getItemCount(SearchAndBrowse.search_term,filters) :
                XAPIBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()), filters);
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        List<String> breadcrumb_facets = Elements.findElements("search_result.facet_breadcrumb").stream().map(WebElement::getText).collect(Collectors.toList());
        Collections.sort(breadcrumb_facets);
        Collections.sort(FacetAccordionModel.selectedFacetValues);
        Assert.assertEquals("ERROR - App: breadcrumb values are not displayed correctly",
                breadcrumb_facets,FacetAccordionModel.selectedFacetValues);
        log.info("products are displayed for the selected facet values only");
    }

    @When("^I refresh the page$")
    public void iRefreshThePage() throws Throwable {
        Navigate.browserRefresh();
    }

    @When("^I remove the facets from the breadcrumb$")
    public void iRemoveTheFacetsFromTheBreadcrumb() throws Throwable {
        FacetAccordionModel.removeFacets();
    }

    @Then("^I should see the products without any facet values in (browse|search|DLP) page$")
    public void iShouldSeeTheProductsWithoutAnyFacetValues(String type) throws Throwable {
        Map<String, String> filters = new HashMap<>();
        filters.put("pathname", WebDriverManager.getWebDriver().getCurrentUrl().split("\\.com")[1].split("\\?")[0]);
        int count = type.equals("search") || type.equals("DLP")? XAPISearchService.getItemCount(SearchAndBrowse.search_term,filters) :
                XAPIBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()), filters);
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        Assert.assertFalse("ERROR - App: breadcrumb value is not cleared", Elements.elementPresent("search_result.facet_breadcrumb"));
        log.info("Verified no facet values available in breadcrumb and products are displayed according to service response");
    }

    @When("^I click on back button$")
    public void iClickOnBackButton() throws Throwable {
        FacetAccordionModel.backButton();
        log.info("Successfully clicked back button on facet accordion model");
    }

    @Then("^I should see the facet values selected$")
    public void iShouldSeeTheFacetValuesSelected() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Facet values is not selected after tapping on it",FacetAccordionModel.checkFacetValues());
        log.info("Verified facet value is getting selected after tapping on it");
    }

    @When("^I click the clear all button$")
    public void iClickTheClearAllButton() throws Throwable {
        FacetAccordionModel.clearAll();
        log.info("Successfully clicked on clear all button");
    }

    @When("^I remove the facet from the breadcrumb$")
    public void iRemoveTheFacetFromTheBreadcrumb() throws Throwable {
        FacetAccordionModel.removeFacet();
    }

    @Then("^I should see the apply selection overlay with apply and cancel buttons$")
    public void iShouldSeeTheApplySelectionOverlayWithApplyAndCancelButtons() throws Throwable {
        Assert.assertTrue("ERROR - APP: Apply selection window is not displayed",Elements.findElement("search_result.apply_cancel_popup").isDisplayed());
        Assert.assertTrue("ERROR - APP: Cancel button missing on apply section overlay",Elements.elementPresent("left_facet.cancel"));
        Assert.assertTrue("ERROR - APP: Cancel button missing on apply section overlay",Elements.elementPresent("left_facet.apply"));
        log.info("Verified apply selection overlay consists of both apply and cancel buttons");
    }

    @When("^I click the cancel button$")
    public void iClickTheCancelButton() throws Throwable {
        Clicks.click("left_facet.cancel");
        log.info("Successfully clicked on cancel button");
    }

    @Then("^I should see \"([^\"]*)\" in breadcrumb$")
    public void iShouldSeeInBreadcrumb(String facetValue) throws Throwable {
        Assert.assertTrue("Error - App: Selected facet value "+facetValue+ " is not available in breadcrumb", SearchAndBrowseActions.breadCrumbValues().contains(facetValue));
        log.info("Verified selected facet value in breadcrumb");
    }

    @And("^I select random facet value on facet accordion model$")
    public void iSelectRandomFacetValueOnFacetAccordionModel() throws Throwable {
        selectedFacetValue = FacetAccordionModel.selectRandomFacetValue();
        log.info("Selected random facet value is " +selectedFacetValue);
    }

    @Then("^I should see selected facet value in breadcrumb$")
    public void iShouldSeeSelectedFacetValueInBreadcrumb() throws Throwable {
        Assert.assertTrue("Error - App: Selected facet value "+selectedFacetValue+ " is not available in breadcrumb",SearchAndBrowseActions.breadCrumbValues().contains(selectedFacetValue));
        log.info("Verified selected facet value in breadcrumb");
    }

    @Then("^I should see only products in (browse|search) page with selected facet values in registry mode$")
    public void iShouldSeeOnlyProductsInSearchPageWithSelectedFacetValuesInRegistryMode(String type) throws Throwable {
        Wait.untilElementPresent("search_result.total_item_count");
        int count = type.equals("search")? WSSGSearchService.getItemCount(SearchAndBrowse.search_term, "registry",null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()),"registry", null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues());
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        Assert.assertTrue("ERROR - App: breadcrumb value is not displayed correctly",
                Elements.findElement("search_result.facet_breadcrumb").getText().equals(FacetAccordionModel.selectedFacetValue));
        log.info("Verified products are displayed for the selected facet values only");
    }

    @Then("^I should see the products without any facet values in (browse|search) page in registry mode$")
    public void iShouldSeeTheProductsWithoutAnyFacetValuesInSearchPageInRegistryMode(String type) throws Throwable {
        Wait.untilElementPresent("search_result.total_item_count");
        int count =  type.equals("search")? WSSGSearchService.getItemCount(SearchAndBrowse.search_term, "registry",null,String.valueOf(Pagination.currentPageNumber()),null)
                : WSSGBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()),"registry", null,String.valueOf(Pagination.currentPageNumber()),null);
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        Assert.assertFalse("ERROR - App: breadcrumb value is not cleared", Elements.elementPresent("search_result.facet_breadcrumb"));
        log.info("Verified no facet values available in breadcrumb and products are displayed according to service response");
    }

    @Then("^I should see only products in (browse|search) page with selected multiple facet values in registry mode$")
    public void iShouldSeeOnlyProductsInSearchPageWithSelectedMultipleFacetValuesInRegistryMode(String type) throws Throwable {
        Wait.untilElementPresent("search_result.total_item_count");
        List<Integer> prodIds = type.equals("search")? WSSGSearchService.getProductIds(SearchAndBrowse.search_term, "registry",null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getProductIds(Integer.parseInt(new Browse().getCategoryId()),"registry", null, String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues());
        int count =  type.equals("search")? WSSGSearchService.getItemCount(SearchAndBrowse.search_term, "registry",null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()),"registry", null, String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues());
        Assert.assertEquals("ERROR - App:prouduts count is not updated with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                prodIds, SearchAndBrowseActions.getProductIds());
        List<String> breadcrumb_facets = Elements.findElements("search_result.facet_breadcrumb").stream().map(WebElement::getText).collect(Collectors.toList());
        Collections.sort(breadcrumb_facets);
        Collections.sort(FacetAccordionModel.selectedFacetValues);
        Assert.assertEquals("ERROR - App: breadcrumb values are not displayed correctly",
                breadcrumb_facets,FacetAccordionModel.selectedFacetValues);
        log.info("products are displayed for the selected facet values only");
    }

    @Then("^I should see facet headers and values updated based on previous selection for (browse|search) page$")
    public void iShouldSeeFacetHeadersAndValuesUpdatedBasedOnPreviousSelection(String type) throws Throwable {
        Map<String, String> filters = new HashMap<>();
        filters.put("pathname", WebDriverManager.getWebDriver().getCurrentUrl().split("\\.com")[1].split("\\?")[0]);
        Map<String, List<Map<String, Integer>>> xapiFacets = type.equals("search") ?
                XAPISearchService.getAllFacetValuesWithProdCount(SearchAndBrowse.search_term, filters) :
                XAPIBrowseService.getAllFacetValuesWithProdCount(Integer.parseInt(new Browse().getCategoryId()), filters);
        xapiFacets = xapiFacets.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Map<String, List<Map<String, Integer>>> facetsWithValues = FacetAccordionModel.getAllFacetsWithValues();
        facetsWithValues = facetsWithValues.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Assert.assertEquals("ERROR - App: facet headers and values are not updated based on selection",
                xapiFacets, facetsWithValues);
        log.info("facet headers and values are updated based on selection");
    }

    @Then("^I should see facet headers and values updated based on previous selection in (browse|search) page for registry mode$")
    public void iShouldSeeFacetHeadersAndValuesUpdatedBasedOnPreviousSelectionInBrowsePageForRegistryMode(String type) throws Throwable {
        Map<String, List<Map<String, Integer>>> xapiFacets = type.equals("search") ?
                WSSGSearchService.getAllFacetValuesWithProdCount(SearchAndBrowse.search_term, "registry", null, String.valueOf(Pagination.currentPageNumber()), SearchAndBrowseActions.selectedFacetValues()) :
                WSSGBrowseService.getAllFacetValuesWithProdCount(Integer.parseInt(new Browse().getCategoryId()), "registry", null, String.valueOf(Pagination.currentPageNumber()), SearchAndBrowseActions.selectedFacetValues());
        xapiFacets = xapiFacets.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Map<String, List<Map<String, Integer>>> facetsWithValues = FacetAccordionModel.getAllFacetsWithValues();
        facetsWithValues = facetsWithValues.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Assert.assertEquals("ERROR - App: facet headers and values are not updated based on selection",
                xapiFacets, facetsWithValues);
        log.info("facet headers and values are updated based on selection");
    }

    @When("^I select random facet on facet accordion model$")
    public void iSelectRandomFacetOnFacetAccordionModel() throws Throwable {
        Thread.sleep(2000);
        List<String> facets = FacetAccordionModel.getAllFacets();
        String randomFacet = facets.get(new Random().nextInt(facets.size() - 1));
        FacetAccordionModel.selectFacetByName(randomFacet);
    }

    @And("^I deselect all selected size facet values from accordion model$")
    public void iDeselectAllSelectedSizeFacetValuesFromAccordionModel() throws Throwable {
        List<WebElement> selectedSizeFacetValues = Elements.findElements(By.className("m-facet-accordion-selected-size-remove"));
        for (int i = 0; i < selectedSizeFacetValues.size(); i++) {
            Clicks.clickWhenPresent(By.className("m-facet-accordion-selected-size-remove"));
        }
    }

    @Then("^I should not see any size facet value as selected$")
    public void iShouldNotSeeAnySizeFacetValueAsSelected() throws Throwable {
        Assert.assertFalse("Error - App: Selected size facet value is not removed", Elements.elementPresent("left_facet.selected_size"));
    }

    @And("^I type \"([^\"]*)\" into brand search text field on brand facet values screen$")
    public void iTypeIntoBrandSearchTextFieldOnBrandFacetValuesScreen(String keyword) throws Throwable {
        Clicks.clickWhenPresent("left_facet.brand_search");
        TextBoxes.typeTextbox(By.id("m-facet-accordion-brand-search"),keyword);
        log.info("'" + keyword + "' entered in brand search box");
    }

    @And("^I should see facet values refined with typed keyword \"([^\"]*)\"$")
    public void iShouldSeeFacetValuesRefinedWithTypedKeyword(String keyword) throws Throwable {
        List<String> facetValues = Elements.findElements(By.xpath("//*[@id=\"m-accordion-brand-search\"]/li/input")).stream().map(webElement -> webElement.getAttribute("value")).collect(Collectors.toList());
        for(String brand: facetValues) {
            Assert.assertTrue("Error - App:Brands are not refined with typed keyword '" +keyword + "'",brand.toLowerCase().startsWith(keyword.toLowerCase()));
        }
        log.info("Brands are refined based on alphabet entered on brand facet search");
    }

    @Then("^I should see X button in the search input field$")
    public void iShouldSeeXButtonInTheSearchInputField() throws Throwable {
        Assert.assertTrue("Error - App: Clear icon X is not showing after entering keyword in search box",Elements.elementPresent("left_facet.brand_search_clear_icon"));
    }

    @When("^I tap on X button in facet values selection screen$")
    public void iTapOnXButtonInFacetValuesSelectionScreen() throws Throwable {
        Clicks.clickWhenPresent("left_facet.brand_search_clear_icon");
        log.info("Tapped on clear icon X in brand facet search");
    }

    @Then("^I should see empty search input field$")
    public void iShouldSeeEmptySearchInputField() throws Throwable {
        Assert.assertTrue("Error - App: Search keyword is not cleared after tapping on clear icon", !Elements.elementPresent("left_facet.brand_search_clear_icon") &&
                Elements.findElement("left_facet.brand_search").getAttribute("value").isEmpty());
    }

    @Then("^I should see (We found 0 results for \"Brand\") message in brand facet$")
    public void iShouldSeeWeFoundResultsForMessageInBrandFacet(String message) throws Throwable {
        Assert.assertEquals("Error - App: Invalid zero results message is showing", Elements.getText(By.className("m-facet-accordion-no-brand")),message);
        log.info("Verified zero results message in brand facet search");
    }

    @Then("^I click on Apply button on apply selection overlay$")
    public void iClickOnApplyButtonOnApplySelectionOverlay() throws Throwable {
        Clicks.clickWhenPresent("left_facet.confirm_apply");
    }

    @And("^I select single facet value from each available facets$")
    public void iSelectSingleFacetValueFromEachAvailableFacets() throws Throwable {
        List<String> facets = FacetAccordionModel.getAllFacets();
        FacetAccordionModel.selectedFacetValues = new ArrayList<>();
        for (int i = 0; i < facets.size(); i++) {
            String facet = facets.get(i);
            if (FacetAccordionModel.isFacetAvailable(facet)) {
                FacetAccordionModel.selectFacetByName(facet);
                FacetAccordionModel.waitForLoading();
                if (!Elements.elementPresent(By.className("m-facet-accordion-unavailable")))
                    FacetAccordionModel.selectedFacetValues.add(FacetAccordionModel.selectRandomFacetValue());
            }
        }
    }

    @And("^I select 4,S sub facet in Women's Regular$")
    public void iSelectSubFacetInWomenSRegular() throws Throwable {
        Clicks.clickElementByText(By.xpath("//*[@id=\"WOMEN_REGULAR_SIZE_T\"]/li/center/label"), "4\nS");
    }
}
