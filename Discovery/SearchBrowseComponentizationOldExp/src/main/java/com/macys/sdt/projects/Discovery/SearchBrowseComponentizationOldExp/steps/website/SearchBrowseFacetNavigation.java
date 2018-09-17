package com.macys.sdt.projects.Discovery.SearchBrowseComponentizationOldExp.steps.website;

import com.google.gson.Gson;
import com.gargoylesoftware.htmlunit.util.UrlUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentizationOldExp.utils.config.DiscoveryHelper;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentizationOldExp.utils.config.SearchService;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ProductThumbnail;
import com.macys.sdt.shared.steps.website.Facets;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.ListUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.net.URLDecoder.decode;
import static java.util.stream.Collectors.toList;

public class SearchBrowseFacetNavigation extends StepUtils {

    //Global Declarations
    private DiscoveryHelper helper = new DiscoveryHelper();
    private String selectedProdId;
    private static int thumbnailColumns;
    private static ArrayList<String> selectedFacetValues = new ArrayList<>();
    private static ArrayList<String> selectedFacetNames = new ArrayList<>();
    private static List<String> firstSuggestionsList = new ArrayList<>();
    private static List<String> secondSuggestionsList = new ArrayList<>();
    private String ISHIPCountry = "Canada";
    private String ISHIPCountryCode = "CA";
    private static int productCount;
    private String facetItems;
    private String sortByValue;
    private static String strUtils;
    private int storeResultsCount;
    private String selectedFacet;
    private String selectedSortBy;
    private String selectedFacetName;
    private List<String> prodIds;
    private int totalPageCount;
    private static String shoppingMode;
    private static String itemPerPageCount;
    private String selectedColorSwatch;
    private static int totalThumbnailCount;
    private static String facetSelection;
    private static String brandFacet;
    private static int selectedPageNo;
    private static String searchKeyword;
    private static String zipCode;
    private static int totalProductInRvi;
    private Map<String, HashMap> productThumbnails;
    private ProductData productData;
    private static String locationId;


    @When("^I select 'single' facet value from 'any' facet section$")
    public void iSelectSingleFacetValueFromAnyFacetSection() throws Throwable {
        String facetName = null, facetValue = null;
        try {
            totalPageCount = (totalPageCount == 0) ? DiscoveryHelper.getTotalPageCount() : totalPageCount;
            productCount = (productCount == 0) ? DiscoveryHelper.getProductCount() : productCount;
            Wait.forLoading("left_facet.loading");
            Wait.forPageReady();
            facetName = DiscoveryHelper.getRandomFacetName();
            selectedFacetNames.add(facetName);
            System.out.println("FACET NAME " + facetName);
            if (selectedFacetValues.size() > 0) Navigate.browserRefresh();
            facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
            System.out.println("FACET VALUE " + facetValue);
            LeftFacet.selectItemFromFacet(facetValue, facetName);
            selectedFacetValues.add(facetValue);
            if (macys()) thumbnailColumns = ProductThumbnail.productThumbnailColumns();
        } catch (Exception e) {
            Assert.fail("Error selecting 'single' facet value from 'any' facet section" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected single facet value: " + facetValue + " from " + facetName + " facet section");
    }

    @Then("^I verify that facet section displaying with 'filter by' header text$")
    public void iVerifyThatFacetSectionDisplayingWithFilterByHeaderText() throws Throwable {
        WebElement element;
        try {
            Assert.assertTrue("Filter by element is not displayed", Elements.elementPresent("leftFacetNavigation_panel.facetPanelLabel"));
            element = Elements.findElement(Elements.element("leftFacetNavigation_panel.facetPanelLabel"));
            Assert.assertTrue("Filter by text is not displayed", element.getText().equals("filter by"));
        } catch (Exception e) {
            Assert.fail("Error Validating Filter By Label in Left Navigation Panel" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that facet section displaying with 'filter by' header text");
    }

    @Then("^I Should see proper product count$")
    public void iShouldSeeProperProductCount() throws Throwable {
        try {
            int currentProductCount = DiscoveryHelper.getProductCount();
            int totalThumbnailCount = helper.getTotalThumbnailCount();
            if (!(totalThumbnailCount == currentProductCount)) {
                Assert.fail("Product thumbnail count and total count mismatch Expected thumbnail count: " + currentProductCount + " Actual displayed count: " + totalThumbnailCount);
            }
        } catch (Exception e) {
            System.out.println("Mismatch in the displayed product count.\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified proper product count is displayed");
    }

    @Then("^I verify that products are filtered as per selected facet value(?:s)?$")
    public void iVerifyThatProductsAreFilteredAsPerSelectedFacetValue() throws Throwable {
        //Declaration
        Boolean breadCrumbVerification;
        Boolean clearAllButtonVerification;

        int currentProductCount = DiscoveryHelper.getProductCount();
        if (macys()) {
            int currentThumbnailCount = helper.getTotalThumbnailCount();
            Assert.assertTrue("Product thumbnail count and total count mismatch Expected thumbnail count: " + currentProductCount + " Actual thumbnail displayed: " + currentThumbnailCount,
                    currentThumbnailCount == currentProductCount);
        }

        Assert.assertTrue("Product count is same Expected count: " + currentProductCount + "Actual Count: " + productCount,
                currentProductCount <= productCount);

        int currentPageCount = DiscoveryHelper.getTotalPageCount();
        Assert.assertTrue("Current pagination count and previous pagination counts are same Expected Count:" + currentPageCount + "Actual Count: " + totalPageCount,
                currentPageCount <= totalPageCount);

        try {
            if (selectedFacetName == null) {
                breadCrumbVerification = DiscoveryHelper.breadCrumbVerification(selectedFacetValues);
                clearAllButtonVerification = helper.displayClearAllButton();
            } else {
                if (selectedFacetName.equals("Pick Up In Store")) {
                    breadCrumbVerification = true;
                    clearAllButtonVerification = true;
                } else  {
                    breadCrumbVerification = DiscoveryHelper.breadCrumbVerification(selectedFacetValues);
                    clearAllButtonVerification = helper.displayClearAllButton();
                }
            }


            if (bloomingdales() && selectedFacetValues.size() == 1) {
                clearAllButtonVerification = true;
            }

            if (!breadCrumbVerification || !clearAllButtonVerification) {
                Assert.fail("Unable to validate facet functionality. Actual Breadcrumb Validation " + breadCrumbVerification + " Actual clearAll Button Verification " + clearAllButtonVerification);
            }
        } catch (Exception e) {
            Assert.fail("Error Validating facet Functionality \r\n " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that products are filtered as per selected facet values");
    }

    @When("^I select browse 'back' button$")
    public void iSelectBrowseBackButton() throws Throwable {
        try {
            Navigate.browserBack();
        } catch (Exception e) {
            Assert.fail("Error performing Browser Back from PDP Page \r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected browser 'back' button");
    }

    @And("^I navigate to (top|bottom) of page$")
    public void iNavigateToTopOfPage(String type) throws Throwable {
        int height = WebDriverManager.getWebDriver().manage().window().getSize().getHeight();
        if (type.equals("top")) Navigate.scrollPage(0, -height);
        else Navigate.scrollPage(0, height);
        DiscoveryHelper.logInfo("Navigated to " + type + " of the page");
    }

    @Then("^I verify that facet section displaying as per service response in \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iVerifyThatFacetSectionDisplayingAsPerServiceResponse(String mode, String keyword) throws Throwable {
        try {
            List<WebElement> facetsList;
            List<String> facetName = new ArrayList<>();
            Map serviceResponse;

            String shoppingMode = mode.equalsIgnoreCase("REGISTRY") ? "WEDDING_REGISTRY" : "SITE";
            String regionCode = mode.equalsIgnoreCase("ISHIP") ? ISHIPCountryCode : "US";
            serviceResponse = SearchService.getSearchService(keyword, shoppingMode, regionCode, false, null);

            DiscoveryHelper.collapseAllExpandedFacets();
            facetsList = Elements.findElements(Elements.element("left_facet.facet_names"));

            facetName.addAll(facetsList.stream().filter(ele -> !ele.getText().equalsIgnoreCase(""))
                    .map(WebElement::getText).collect(Collectors.toList()));

            if (!SearchService.getFacetNameFromService(serviceResponse).containsAll(facetName)) {
                Assert.fail("There is a mismatch between Facet Service and UI");
            }
        } catch (Exception e) {
            Assert.fail("Error performing validation between service and UI \r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that facet section displaying as per service response in " + mode + " mode for keyword " + keyword);
    }

    @Then("^I verify that facet section expanded/collapsed as per service response in \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iVerifyThatFacetSectionExpandedCollapsedAsPerServiceResponseInFor(String mode, String keyword) throws Throwable {
        Boolean serviceFlag;
        try {
            serviceFlag = DiscoveryHelper.expandedCollapsedVerificationAgainstServiceAndUI(mode, keyword, ISHIPCountryCode);
            if (!serviceFlag) {
                Assert.fail("There is a mismatch between Service and UI");
            }
        } catch (Exception e) {
            Assert.fail("Error preforming validation between Service and UI \r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("verify that facet section expanded/collapsed as per service response in " + mode + " mode for the keyword " + keyword);
    }

    @Then("^I verify that the product count is displayed$")
    public void iVerifyThatTheProductCountIsDisplayed() throws Throwable {
        if (Elements.elementPresent("left_facet.search_results_count")) {
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            productCount = DiscoveryHelper.getProductCount();
            totalThumbnailCount = helper.getTotalThumbnailCount();
            if (macys()) {
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
                thumbnailColumns = ProductThumbnail.productThumbnailColumns();
            }
        } else
            Assert.fail("Product count is not displayed");
        DiscoveryHelper.logInfo("Verified product count is displayed");
    }

    @Then("^I verify that the product count is updated$")
    public void iVerifyThatTheProductCountIsUpdated() throws Throwable {
        try {
            Wait.forPageReady();
            int currentProductCount = DiscoveryHelper.getProductCount();
            Assert.assertTrue("Product count was not changed after applying facet Expected Count: " + productCount + " Actual Count: " + currentProductCount,
                    currentProductCount <= productCount);
        } catch (Exception e) {
            Assert.fail("Error fetching product count \r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that product count is updated");
    }

    @When("^I deselect the selected facet from the overlay$")
    public void iRemoveTheSelectedFacetFromTheOverlay() throws Throwable {
        Navigate.browserRefresh();
        for (String facet : selectedFacetValues) {
            LeftFacet.selectItemFromFacet(facet, selectedFacetName);
        }
        DiscoveryHelper.logInfo("Expected facet value "+selectedFacetValues.get(0)+" removed from the facet "+selectedFacetName);
    }

    @When("^I deselect the (Price|Brand|Color) from the overlay$")
    public void iDeselectThePriceFromTheOverlay(String facetName) throws Throwable {
        try {
            if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet(facetName);
            }
            List<WebElement> facetValues = Elements.findElement(LeftFacet.getFacetDiv(facetName)).findElements(By.tagName("li"));
            WebElement ele = facetValues.stream().filter(e->e.getAttribute("class").contains("selected")).findFirst().orElse(null);
            Clicks.click(ele);
            if (bloomingdales()) {
                Clicks.javascriptClick(LeftFacet.getFacetApply(facetName));
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
            }
        } catch (Exception e) {
            Assert.fail("Error performing facet deselection \r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Deselected the price from overlay");
    }

    @Then("^I verify that the product count returns to original$")
    public void iVerifyThatTheProductCountReturnsToOriginal() throws Throwable {
        int currentProductCount;
        try {
            currentProductCount = DiscoveryHelper.getProductCount();
            if (currentProductCount != productCount) {
                Assert.fail("Error displaying original product count after deselecting facet.Expected:" + productCount + " Actual: " + currentProductCount);
            }
        } catch (Exception e) {
            Assert.fail("Error verifying original product count after deselecting facet.\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the product count returns to original");
    }

    @And("^I click on clear all button$")
    public void iClickOnClearAllButton() throws Throwable {
        try {
            helper.clickClearAllButton();
        } catch (Exception e) {
            Assert.fail("Error clicking Clear All button.\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Clicked Clear All Button");
    }

    @And("^I select \"([^\"]*)\" facet value from \"([^\"]*)\" facet section$")
    public void iSelectFacetValueFromBrandFacetSection(String facetTime, String facetName) throws Throwable {
        int times = facetTime.equalsIgnoreCase("single") ? 1 : 2;
        selectedFacetName = facetName;
        selectedFacetValues = new ArrayList<>();
        try {
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            productCount = DiscoveryHelper.getProductCount();
            for (int i = 0; i < times; i++) {
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
                System.out.println("FACET NAME " + facetName);
                if (facetName.contains("customer") || facetName.contains("Customer")) {
                    //since customer top rated facet is displayed in three different ways like Customer Rating, Customers Top Rated & Customers' top rated
                    //to over come that used the below step
                    facetName = DiscoveryHelper.getAllFacetName().
                            stream().filter(e -> e.contains("Customer")).findFirst().orElse(null);
                }
                if (i > 0) {Navigate.browserRefresh();}
                selectedFacet = DiscoveryHelper.getRandomFacetValue(facetName);
                System.out.println("FACET ITEM " + selectedFacet);
                LeftFacet.selectItemFromFacet(selectedFacet, facetName);
                selectedFacetValues.add(selectedFacet);
            }
            if (macys()) thumbnailColumns = ProductThumbnail.productThumbnailColumns();
        } catch (Exception e) {
            Assert.fail("Error Selecting facet Item " + facetTime + " from facet " + facetName);
        }
        DiscoveryHelper.logInfo("Selected " + facetTime + " facet value from " + facetName + " facet section");
    }

    @Then("^I verify that previous sort by selection persist$")
    public void i_verify_that_previous_sort_by_selection_persist() throws Throwable {
        String currentSortBy = null;
        try {
            if (DiscoveryHelper.getProductIds().size() > 2) {
                currentSortBy = macys() ? DropDowns.getSelectedValue(Elements.element("search_result.sort_by_select")) :
                        Elements.findElement("search_result.sort_by_select").getText();
                if (!currentSortBy.equalsIgnoreCase(selectedSortBy)) {
                    Assert.fail("Selected value is not displayed");
                }
            } else {
                System.out.println("Drop Down will not be displayed when product count is less than 2");
            }
        } catch (Exception e) {
            Assert.fail("Previously selected value and current value are not same Expected SortBy: " + selectedSortBy + "Actual Sort by displayed: " + currentSortBy);
        }
        DiscoveryHelper.logInfo("Verified previous sort by selection persist");
    }

    @Then("^I verify that landed on SearchResultsPage on same product grid point$")
    public void iVerifyThatLandedOnPageOnSameProductGridPoint() throws Throwable {
        try {
            List<String> currentProductIds = DiscoveryHelper.getProductIds();
            if (!currentProductIds.equals(prodIds)) {
                Assert.fail("There is a mismatch in before Product ids Product ids Before displayed:" + prodIds + " After Browser Back: " + currentProductIds);
            }
        } catch (Exception e) {
            Assert.fail("Error fetching Browser Back URL \r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that landed on SearchResultsPage on same product grid point");
    }

    @When("^I select (zipcode|city name) link in pick-up in-store facet section$")
    public void i_select_zipcode_link_in_pick_up_in_store_facet_section(String name) throws Throwable {
        try {
            if (!LeftFacet.isExpanded("Pick Up In Store"))
                LeftFacet.expandFacet("Pick Up In Store");
            if (!Elements.elementPresent(Elements.element("left_facet.bops_location"))) {
                TextBoxes.typeTextNEnter("left_facet.bops_store_search_box", "94102");
                Wait.untilElementNotPresent("left_facet.bops_loading_mask");
            }
            Wait.untilElementPresent("left_facet.bops_location");
            Elements.findElement("left_facet.bops_location").click();
        } catch (Exception e) {
            Assert.fail("Error Selecting " + name + " link in pick-up in-store facet" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected " + name + " link in pick-up in-store facet section");
    }

    @Then("^I verify that bops overlay with map and stores list$")
    public void i_verify_bops_overlay_with_map_and_stores_list() throws Throwable {
        try {
            Wait.untilElementPresent(Elements.element("change_pickup_store_dialog.google_map_containers"));
            if (!Elements.elementPresent(Elements.element("change_pickup_store_dialog.google_map_containers")))
                Assert.fail("Error Store Map is not displayed on bops overlay");
            Wait.untilElementPresent(Elements.element("change_pickup_store_dialog.bops_stores"));
            if (!Elements.elementPresent(Elements.element("change_pickup_store_dialog.bops_stores")))
                Assert.fail("Error Store results is not displayed on bops overlay");
        } catch (Exception e) {
            Assert.fail("Error Selecting zipcode link in pick-up in-store facet" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified bops overlay with map and stores list");
    }

    @Then("^I verify that USERPC and USERLL are updated with \"([^\"]*)\" values in MISCGCs cookie$")
    public void i_verify_USERPC_and_USERLL_are_updated_with_values_in_MISCGCs_cookie(String zipcode) {
        try {
            String bopsCookie = Cookies.getCookieValue("MISCGCs");
            if (!bopsCookie.contains(zipcode))
                Assert.fail("Error Bops cookie is not updated with the zipcode" + zipcode);
        } catch (Exception e) {
            Assert.fail("Error MISCGS cookie is not found" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified USERPC and USERLL are updated with " + zipcode + " values in MISCGCs cookie");
    }

    @Then("^I verify that stores are (displayed|not displayed) in pick-up in-store facet section$")
    public void i_verify_that_stores_are_displayed_in_pick_up_in_store_facet_section(String condition) {
        Wait.untilElementNotPresent("left_facet.bops_loading_mask");
        if(condition.equals("displayed")) {
            Assert.assertFalse("Store results are not displayed in pick-up in store facet",
                    DiscoveryHelper.getRandomFacetValue("Pick Up In Store").isEmpty());
        } else {
            Assert.assertTrue("Store results are not displayed in pick-up in store facet",
                    DiscoveryHelper.getRandomFacetValue("Pick Up In Store").isEmpty());
        }
        DiscoveryHelper.logInfo("Verified that stores are displayed in pick-up in-store facet section");
    }

    @And("^I select multiple facet value from \"([^\"]*)\" facet section$")
    public void iSelectMultipleFacetValueFromFacetSection(String facetName) throws Throwable {
        selectedFacetValues = new ArrayList<>();
        try {
            if (macys()) {
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
                thumbnailColumns = ProductThumbnail.productThumbnailColumns();
            }
            productCount = DiscoveryHelper.getProductCount();
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            if (facetName.equalsIgnoreCase("Pick Up In Store")) {
                iSelectMultipleFacetValueOfBopsFacet();
            } else {
                for (int i = 0; i < 2; i++) {
                    if (facetName.contains("customer") || facetName.contains("Customer") && i == 0) {
                        //since customer top rated facet is displayed in three different ways like Customer Rating, Customers Top Rated & Customers' top rated
                        //to over come that used the below step
                        facetName = DiscoveryHelper.getAllFacetName().
                                stream().filter(e -> e.contains("Customer")).findFirst().orElse(null);
                    }
                    if (i > 0 ){Navigate.browserRefresh();}
                    String facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
                    System.out.println("FACET ITEM " + facetValue);
                    LeftFacet.selectItemFromFacet(facetValue, facetName);
                    selectedFacetValues.add(facetValue);
                    Wait.forLoading("left_facet.loading");
                    Wait.forPageReady();
                }
            }
        } catch (Exception e) {
            Assert.fail("Error fetching Multiple facet from " + facetName + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected multiple facet value from " + facetName + " facet section");
    }

    @When("^I select multiple facet value of bops facet$")
    public void iSelectMultipleFacetValueOfBopsFacet() throws Throwable {
        String facetName = "Pick Up In Store";
        Facets facet = new Facets();
        facet.I_should_see_facet_listed_on_left_nav(facetName);
        i_select_zipcode_link_in_pick_up_in_store_facet_section("zipcode");
        i_verify_bops_overlay_with_map_and_stores_list();
        Facets.I_search_for_zipcode_in_bops_facet("10021");
        ShopAndBrowse.I_save_close_the_bops_change_store_dialog();
        facet.I_select_any_bops_facet_value();
        iShouldGetAppliedFacetValue();
        Navigate.browserRefresh();
        Wait.forLoading("left_facet.loading");
        Wait.forPageReady();
        facet.I_should_see_facet_listed_on_left_nav(facetName);
        facet.I_the_facet_on_left_nav("expand", facetName);
        facet.I_select_any_bops_facet_value();
        String secondSelectedFacet = Elements.getText("left_facet.facetBreadcrumbs");
        selectedFacetValues.add(secondSelectedFacet);
        DiscoveryHelper.logInfo("Selected multiple facet value of bops facet");
    }

    @When("^I select random product from thumbnail grid$")
    public void iSelectRandomProductFromThumbnailGrid() throws Throwable {
        try {
            Wait.untilElementNotPresent("product_thumbnails.loading_div");
            prodIds = DiscoveryHelper.getProductIds();
            Random randomGenerator = new Random();
            int randomProdIndex = randomGenerator.nextInt(prodIds.size());
            System.out.println(prodIds.get(randomProdIndex));
            selectedSortBy = macys() ? DropDowns.getSelectedValue(Elements.element("search_result.sort_by_select")) :
                    Elements.findElement("search_result.sort_by_select").getText();
            selectedProdId = (prodIds.size() == 1) ?
                    prodIds.get(randomProdIndex) : prodIds.get(0);
            DiscoveryHelper.selectProductThumbnail(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Error selecting random product thumb of prod_id " + selectedProdId + " " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected random product " + selectedProdId + " from thumbnail grid");
    }

    @Then("^I verify that landed on respective product display page$")
    public void iVerifyThatLandedOnRespectiveProductDisplayPage() throws Throwable {
        try {
            Wait.untilElementPresent("product_display.product_id_div");
            if (Elements.elementPresent("product_display.more_details")) {
                Clicks.click("product_display.more_details");
            }
            String prodId = Elements.findElement(Elements.element("product_display.product_id_div")).getText().replace("Web ID: ", "");
            DiscoveryHelper.logInfo("Selected product id from grid: " + selectedProdId);
            DiscoveryHelper.logInfo("Displayed product in pdp page: " + prodId);
            if (!prodId.equals(selectedProdId)) {
                Assert.fail("Product id mismatch Expected: " + selectedProdId + " Actual prod displayed: " + prodId);
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Product display page is not displayed" + e);
        }
        DiscoveryHelper.logInfo("Verified that landed on respective product display page");
    }

    @Then("^I verify that previously selected facets persists in breadcrumb$")
    public static void iVerifyPreviouslySelectedFacetsPersistsInBreadcrumb() throws Throwable {
        try {
            Wait.forLoading("left_facet.loading");
            Assert.assertTrue("Selected facet and displayed breadcrumb varies",
                    DiscoveryHelper.breadCrumbVerification(selectedFacetValues));
        } catch (Exception e) {
            Assert.fail("Previously selected facets persists in breadcrumb" + e);
        }
        DiscoveryHelper.logInfo("Verified previously selected facets persists in breadcrumb");
    }

    @Then("^I verify that previous grid view selection persist$")
    public static void iVerifyThatPreviousGridViewsSelectionPersists() throws Throwable {
        try {
            int currentThumbnailColumns = ProductThumbnail.productThumbnailColumns();
            DiscoveryHelper.logInfo("Current thumbnail count: " + currentThumbnailColumns);
            DiscoveryHelper.logInfo("Previous thumbnail count: " + thumbnailColumns);
            if (!(currentThumbnailColumns == thumbnailColumns)) {
                Assert.fail("Expected thumbnail columns is not displayed, Expected: " + thumbnailColumns + " Actual: " + currentThumbnailColumns);
            }
        } catch (Exception e) {
            Assert.fail("Previous grid view selection not persist" + e);
        }
        DiscoveryHelper.logInfo("Verified that previous grid view selection persist");
    }

    @Then("^I verify that previous item count selection persist$")
    public static void iVerifyPreviousItemCountSelectionPersists() throws Throwable {
        try {
            String currentItemPerPageCount = DiscoveryHelper.getItemsPerPage();
            DiscoveryHelper.logInfo("Current items per page count: " + currentItemPerPageCount);
            DiscoveryHelper.logInfo("Previous items per page count: " + itemPerPageCount);
            if (!itemPerPageCount.equals(currentItemPerPageCount)) {
                Assert.fail("Expected item per page count is not displayed, Expected: " + itemPerPageCount + " Actual: " + currentItemPerPageCount);
            }
        } catch (Exception e) {
            Assert.fail("Previous grid view selection not persist" + e);
        }
        DiscoveryHelper.logInfo("Verified that previous item count selection persist");
    }

    @When("^I select multiple facet value from Brand facet section$")
    public void iSelectMultipleFacetValueFromBrandFacet() throws Throwable {
        List<String> brandFacetItems;
        String item;
        selectedFacetValues = new ArrayList<>();
        try {
            productCount = DiscoveryHelper.getProductCount();
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            if (macys()) {
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
                thumbnailColumns = ProductThumbnail.productThumbnailColumns();
            }
            String facetName = DiscoveryHelper.getAllFacetName().stream()
                    .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null);
            for (int i = 0; i < 2; i++) {
                if (macys()) {
                    Navigate.browserRefresh();
                    LeftFacet.expandFacet(facetName);
                    brandFacetItems = DiscoveryHelper.getAllBrandFacetName();
                    Random random = new Random();
                    int index = random.nextInt(brandFacetItems.size());
                    item = brandFacetItems.get(index);
                    System.out.println("Item to select\t" + item);
                    LeftFacet.expandFacet(facetName);
                    if ((Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed"))) {
                        LeftFacet.expandFacet(facetName);
                    }
                } else {
                    item = DiscoveryHelper.getRandomFacetValue(facetName);
                }
                LeftFacet.selectItemFromFacet(item, facetName);
                selectedFacetValues.add(item);
            }
        } catch (Exception e) {
            Assert.fail("Error Selecting Multiple Brand facet Items");
        }
        DiscoveryHelper.logInfo("Selected multiple facet values " + selectedFacetValues + " from Brand facet section");
    }

    @When("^I search for \"([^\"]*)\" keyword in brand facet section$")
    public void iSearchForBrandKeywordInBrandFacetSection(String brandName) throws Throwable {
        try {
            brandName = brandName.equals("unavailable brand") ? "qwert" : brandName;
            brandFacet = DiscoveryHelper.getAllFacetName().stream()
                    .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null);
            LeftFacet.expandFacet(brandFacet);
            TextBoxes.typeTextNEnter("left_facet.brand_search", brandName);
            Wait.forPageReady();
        } catch (Exception e) {
            Assert.fail("Unable to search for 'unavailable brand' keyword in brand facet section" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for " + brandName + " keyword in brand facet section");
    }

    @Then("^I verify that error message '(No brands match your search.|We couldn't find a match.)' is (displayed|not displayed) below search box$")
    public void iVerifyThatErrorMessageNoBrandsMatchYourSearchIsDisplayedBelowSearchBox(String expectedErrorMessage, String condition) throws Throwable {
        if (condition.equals("displayed")) {
            Wait.untilElementPresent("leftFacetNavigation_panel.error_highlight");
            String actualErrorMessage = Elements.getText("leftFacetNavigation_panel.error_highlight");
            System.out.println("Text of the ele" + actualErrorMessage);
            Assert.assertTrue("Expected error message is not displayed", actualErrorMessage.equals(expectedErrorMessage));
        } else {
            Assert.assertFalse("Expected error message is displayed", Elements.elementPresent("leftFacetNavigation_panel.error_highlight"));
        }
        DiscoveryHelper.logInfo("Verified error message " + expectedErrorMessage + " is " + condition + " below search box");
    }

    @Then("^I verify that previous store facet removed and applied new store facet$")
    public void i_verify_that_previous_store_facet_removed_and_applied_new_store_facet() {
        try {
            String newSelectedFacet = Elements.getText("leftFacetNavigation_panel.facetBreadcrumbs");
            if (selectedFacet.equalsIgnoreCase(newSelectedFacet))
                Assert.fail("Previous store facet is not removed");
        } catch (Exception e) {
            Assert.fail("Error Applied facet value is not displayed" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that previous store facet removed and applied new store facet");
    }

    @And("^I verify that applied facet value is displayed$")
    public void iShouldGetAppliedFacetValue() throws Throwable {
        selectedFacetNames = new ArrayList<>();
        try {
            selectedFacet = Elements.getText("leftFacetNavigation_panel.facetBreadcrumbs");
            selectedFacetValues.add(selectedFacet);
            if (selectedFacet.isEmpty())
                Assert.fail("Error Applied facet value is not displayed");
        } catch (Exception e) {
            Assert.fail("Error Applied facet value is not displayed" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Got applied facet value");
    }

    @Then("^I verify that products are updated with selected sort option$")
    public void iVerifyThatProductsAreUpdatedWithSelectedSortOption() throws Throwable {
        iVerifyTheSortByFunctionality(sortByValue);
        DiscoveryHelper.logInfo("Verified the product update based on " + sortByValue + " sort options");
    }

    @When("^I select another facet value from \"([^\"]*)\" facet$")
    public void iSelectAnotherFacetValueFromFacet(String facetName) throws Throwable {
        selectedFacetName = facetName;
        try {
            Navigate.browserRefresh();
            Wait.forLoading("left_facet.loading");
            Wait.forPageReady();
            if (selectedFacet == null) {
                selectedFacet = DiscoveryHelper.selectAlternateFacet(selectedFacetValues.get(0), facetName);
                selectedFacetValues.add(selectedFacet);
            } else {
                DiscoveryHelper.selectAlternateFacet(selectedFacet, facetName);
            }
        } catch (Exception e) {
            Assert.fail("Error on selecting multi facet" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected another facet value from " + facetName + " facet");
    }

    @And("^I verify that miles drop down with below option:$")
    public void iVerifyThatMilesDropDownWithBelowOption(List<String> miles) {
        if (bloomingdales()) {
            Clicks.click("left_facet.bops_dropdown_miles");
        }
        Wait.untilElementPresent("left_facet.bops_store_search_radius");
        String[] actual = Elements.getText("left_facet.bops_store_search_radius").split("\n");
        for (int i = 0; i < (miles.size() - 1); i++) {
            DiscoveryHelper.logInfo("Displayed miles in Drop Down: " + actual[i]);
            if ((!miles.get(i).equalsIgnoreCase(actual[i])) || (actual[i] == null))
                Assert.fail("Error Miles drop down is not listed" + miles.get(i));
        }
        DiscoveryHelper.logInfo("Verified that miles drop down with below option: " + miles);
    }

    @When("^I select random option from miles drop down$")
    public void iSelectRandomOptionFromMilesDropDown() {
        storeResultsCount = DiscoveryHelper.getAllFacetValues("Pick Up In Store").size();
        DiscoveryHelper.selectRandomStoreMiles();
        zipCode = DropDowns.getSelectedValue(Elements.element("left_facet.bops_store_search_radius"));
        DiscoveryHelper.logInfo("Selected random option from miles drop down");
    }

    @Then("^I verify that store facet values are updated with selected mile range$")
    public void iVerifyStoreFacetValuesAreUpdatedWithSelectedMileRange() throws Throwable {
        int zipCodeMile = Integer.parseInt(zipCode.replaceAll("\\D+", ""));
        int currentZipCodeMile = Integer.parseInt(DropDowns.getSelectedValue(Elements.element("left_facet.bops_store_search_radius"))
                .replaceAll("\\D+", ""));
        int currentStoreResultsCount = DiscoveryHelper.getAllFacetValues("Pick Up In Store").size();
        if (currentZipCodeMile < zipCodeMile)
            Assert.assertTrue("Error store facet values are not updated with selected miles range",
                    currentStoreResultsCount <= storeResultsCount);
        else
            Assert.assertTrue("Error store facet values are not updated with selected miles range",
                    currentStoreResultsCount >= storeResultsCount);
        DiscoveryHelper.logInfo("Verified store facet values are updated with selected mile range");
    }

    @When("^I (remove|add) \"([^\"]*)\" zipcode cookie (from|to) cookie list$")
    public void iRemoveZipcodeCookieFromCookieList(String condition, String cookieName, String fromTo) {
        zipCode = DiscoveryHelper.getRandomZipCode();
        try {
            if (condition.equals("add")) {
                DiscoveryHelper.createBopsCookie(zipCode);
            } else {
                String bopsCookie = Cookies.getCookieValue(cookieName);
                if (bopsCookie != null) {
                    String cookieValue = Cookies.getCookieValue(cookieName);
                    Cookies.editCookie(cookieName, cookieValue, "USERPC1_92_"); // to see the search box setting empty zipcode
                }
            }
            System.out.println("Cookie value before refresh: "+ Cookies.getCookieValue(cookieName));
            Navigate.browserRefresh();
            System.out.println("Cookie value after refresh: "+ Cookies.getCookieValue(cookieName));
        } catch (Exception e) {
            Assert.fail("Error Unable to " + condition + " " + cookieName + " cookie" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo(condition + "ed " + cookieName + " zipCode cookie " + fromTo + " cookie list");
    }

    @When("^I search for zipcode \"([^\"]*)\" in bops facet$")
    public void iSearchForZipcodeInBopsFacet(String zipcode) {
        try {
            if (macys()) {
                if (!LeftFacet.isExpanded("Pick Up In Store"))
                    LeftFacet.expandFacet("Pick Up In Store");
                TextBoxes.typeTextNEnter("left_facet.bops_store_search_box", zipcode);
            } else {
                TextBoxes.typeTextNEnter("bops_facet.zipcode_field", zipcode);
            }
        } catch (Exception e) {
            Assert.fail("Error zipCode search is not working in bops facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for zipCode " + zipcode + " in bops facet");
    }

    @And("^I select 'multiple' facet value from 'any' facet sections$")
    public void iSelectMultipleFacetValueFromAnyFacetSections() throws Throwable {
        selectedFacetValues = new ArrayList<>();
        try {
            productCount = DiscoveryHelper.getProductCount();
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            if (macys()) {
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
                thumbnailColumns = ProductThumbnail.productThumbnailColumns();
            }
            for (int i = 0; i < 2; i++) {
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
                String facetName = DiscoveryHelper.getRandomFacetName();
                selectedFacetNames.add(facetName);
                System.out.println("FACET NAME " + facetName);
                DiscoveryHelper.logInfo("FACET NAME " + facetName);
                if (i > 0) {
                    Navigate.browserRefresh();
                }
                String facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
                System.out.println("FACET VALUE " + facetValue);
                DiscoveryHelper.logInfo("FACET VALUE " + facetValue);
                LeftFacet.selectItemFromFacet(facetValue, facetName);
                selectedFacetValues.add(facetValue);
                strUtils = WebDriverManager.getCurrentUrl();
            }
        } catch (Exception e) {
            Assert.fail("Unable to select multiple facet value from any facet sections " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected 'multiple' facet value from 'any' facet sections");
    }

    @When("^I search for 'available brand' keyword in brand facet section$")
    public void iSearchForAvailableBrandKeywordInBrandFacetSection() throws Throwable {
        try {
            brandFacet = DiscoveryHelper.getAllFacetName().stream()
                    .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null);
            facetItems = macys() ? DiscoveryHelper.availableBrand() :
                    DiscoveryHelper.getRandomFacetValue(brandFacet);
            System.out.println("Available brand name is  \t" + facetItems);
            TextBoxes.typeTextNEnter("left_facet.brand_search", facetItems);
        } catch (Exception e) {
            Assert.fail("Error Selecting available brand facet item");
        }
        DiscoveryHelper.logInfo("Searched for 'available brand' keyword in brand facet section");
    }

    @Then("^I verify that 'X' icon is (displayed|not displayed) in search box under brand facet section$")
    public void iVerifyXIconInSearchBoxUnderBrandFacetSection(String condition) throws Throwable {
        try {
            WebElement ele = Elements.findElement(LeftFacet.getFacetDiv("Brand"));
            ele = ele.findElement(Elements.element("leftFacetNavigation_panel.x_icon"));
            if (condition.equals("displayed")) {
                Assert.assertTrue("X icon is not displayed under brand facet",
                        ele.isDisplayed());
            } else {
                Assert.assertFalse("X icon is displayed under brand facet",
                        ele.isDisplayed());
            }
        } catch (Exception e) {
            Assert.fail("Error verifying X icon under brand facet Item ");
        }
        DiscoveryHelper.logInfo("Verified 'X' icon in search box under brand facet section");
    }

    @When("^I select 'X' icon in search box$")
    public void iSelectXIconInSearchBox() throws Throwable {
        try {
            WebElement ele = Elements.findElement(LeftFacet.getFacetDiv("Brand"));
            ele = ele.findElement(Elements.element("leftFacetNavigation_panel.x_icon"));
            Clicks.click(ele);
        } catch (Exception e) {
            Assert.fail("Error Selecting X icon");
        }
        DiscoveryHelper.logInfo("Selected 'X' icon in search box");
    }

    @Then("^I verify that brand search box is empty$")
    public void iVerifyThatBrandSearchBoxIsEmpty() throws Throwable {
        try {
            Assert.assertTrue("Brand search box is not empty",
                    Elements.findElement("left_facet.brand_search").getText().isEmpty());
        } catch (Exception e) {
            Assert.fail("Error verifying whether the brand search box is empty");
        }
        DiscoveryHelper.logInfo("Verified that brand search box is empty");
    }

    @Then("^I verify that brands are filtered with entered search term or characters$")
    public void iVerifyBrandsAreFilteredWithEnteredSearchTermOrCharacters() throws Throwable {
        try {
            List<String> searchedFacetName;
            searchedFacetName = macys() ? DiscoveryHelper.getAllBrandFacetName() :
                    DiscoveryHelper.getAllFacetValues(brandFacet);
            String entered_search_term = facetItems;
            searchedFacetName.stream().filter(facet -> !facet.equals(""))
                    .filter(facet -> !facet.contains(entered_search_term))
                    .forEach(facet -> Assert.fail("Brand name is not displayed in suggestion" + entered_search_term));
        } catch (Exception e) {
            Assert.fail("Error verifying brand facets are filtered with search term");
        }
        DiscoveryHelper.logInfo("Verified brands are filtered with entered search term or characters");
    }

    @And("^I select (\\d+) for max option$")
    public void iChooseForMaxOption(int maxOption) throws Throwable {
        LeftFacet.expandFacet("Price");
        TextBoxes.typeTextNEnter("leftFacetNavigation_panel.custom_max_price",
                (String.valueOf(maxOption)));
        Clicks.click("leftFacetNavigation_panel.price_go");
        if (!Elements.elementPresent("leftFacetNavigation_panel.price_error"))
            Clicks.click("leftFacetNavigation_panel.price_go");
        DiscoveryHelper.logInfo("Chose " + maxOption + " for max option");
    }

    @Then("^I verify that the error message is shown$")
    public void iVerifyThatTheErrorMessageIsShown() throws Throwable {
        String expectedError = "please enter a range from 1 to 9999";
        Assert.assertTrue("Custom price error message is not displayed", Elements.elementPresent("leftFacetNavigation_panel.price_error"));
        String priceError = Elements.findElement(Elements.element("leftFacetNavigation_panel.price_error")).getText();
        Assert.assertTrue("Price error message varies Expected error message: " + expectedError + " Actual error displayed: " + priceError, priceError.equalsIgnoreCase(expectedError));
        System.out.println("Price error is " + priceError);
        DiscoveryHelper.logInfo("Verified that the error message is shown");
    }

    @Then("^I verify that multi level pricing is present in the results$")
    public void I_verify_that_multi_level_pricing_is_present_in_the_results() throws Throwable {
        Assert.assertTrue("Multi level pricing information like 'Was' 'Sale' 'Reg' 'Now' is not displayed for any product thumbnail"
                , DiscoveryHelper.verifyMultiLevelPricing());
        DiscoveryHelper.logInfo("Verified that multi level pricing is present in the results");
    }

    @Then("^I verify that the selected (.*?) appears on top$")
    public void I_verify_that_the_selected_color_appears_on_top(String facetName) throws Throwable {
        LeftFacet.expandFacet(facetName);
        List<String> elementAttributes = Elements.findElement(By.className("facetsOuterWrapper")).findElements(By.tagName("div"))
                .stream().map(e -> e.getAttribute("class")).collect(Collectors.toList());
        int selectedFacetValuesIndex = elementAttributes.indexOf("selectedFacetsSection");
        int otherFacetValuesIndex = elementAttributes.indexOf("facets_box");
        Assert.assertTrue("Selected facet values are not displayed at top of the facet section",
                selectedFacetValuesIndex < otherFacetValuesIndex);
        List<String> currentSelectedFacetValues = Elements.findElement(By.className("selectedFacetsSection")).findElements(By.className("item"))
                .stream().map(e -> e.getText().split("\\[")[0].trim()).collect(Collectors.toList());
        Assert.assertTrue("Selected facet section does not contain all the selected facet Expected facets: " + selectedFacetValues +
                " Actual displayed facets: " + currentSelectedFacetValues, selectedFacetValues.equals(currentSelectedFacetValues));
        DiscoveryHelper.logInfo("Verified that the selected " + facetName + " appears on top");
    }

    @When("^I select the (first|first two) (\\w+) in the (Color|Price|Brand|Customer Ratings) facet$")
    public void iSelectTheFirstColorInTheColorFacet(String count, String facetValue, String facetName) throws Throwable {
        int time = count.equals("first two") ? 2 : 1;
        selectedFacetValues = new ArrayList<>();
        try {
            for (int i = 0; i < time; i++) {
                if (i == 1) {
                    Navigate.browserRefresh();
                    if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                        LeftFacet.expandFacet(facetName);
                    }
                    Wait.forLoading("left_facet.loading");
                    Wait.forPageReady();
                }
                if (facetName.contains("Customer") && i == 0){
                    facetName = DiscoveryHelper.getAllFacetName().
                            stream().filter(e -> e.contains("Customer")).findFirst().orElse(null);
                }
                List<String> facetElement = DiscoveryHelper.getAllFacetValues(facetName);
                selectedFacet = facetElement.get(i);
                if (selectedFacet.equalsIgnoreCase("multi")) {
                    selectedFacet = facetElement.get(i + 1);
                }
                if (facetName.contains("Brand")) {
                    Navigate.browserRefresh();
                    List<WebElement> featuredBrandsList = Elements.findElement(Elements.element("leftFacetNavigation_panel.featured_brand_values")).findElements(By.tagName("li"));
                    Clicks.hoverForSelection(featuredBrandsList.get(time + 3));
                }
                LeftFacet.selectItemFromFacet(selectedFacet, facetName);
                selectedFacetValues.add(selectedFacet);
            }
        } catch (Exception e) {
            Assert.fail("ERROR DATA:: " + facetValue + " Facet may not be available in this search page" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the " + count + " " + facetName + " in the " + facetName + " facet");
    }

    @And("^I select a product having color swatches$")
    public void iSelectAProductHavingColorSwatches() throws Throwable {
        List<String> prodIds, prodIdsWithMoreLink;
        Random randomGenerator = new Random();
        int index;
        try {
            prodIds = DiscoveryHelper.getProductIdsWithColorSwatch();
            Assert.assertTrue("There is no product displayed with color swatch", prodIds.size() != 0);
            if (bloomingdales()) {
                prodIdsWithMoreLink = DiscoveryHelper.getProductIdsWithMoreColorSwatch();
                prodIds.removeAll(ListUtils.intersection(prodIds, prodIdsWithMoreLink));
                prodIds.removeAll(ListUtils.intersection(prodIds, DiscoveryHelper.getMasterProductIds()));
                Assert.assertTrue("There is no member product displayed with color swatch", prodIds.size() != 0);
            }
            index = randomGenerator.nextInt(prodIds.size());
            selectedProdId = prodIds.get(index);
            if (bloomingdales())
                selectedColorSwatch = DiscoveryHelper.getSelectedColorFamilyName(selectedProdId);
            else
                selectedColorSwatch = DiscoveryHelper.getSelectedColor(selectedProdId);
            System.out.println("Selected Color swatch of product id: " + prodIds + " " + selectedColorSwatch);
            System.out.println("Selected facet: " + selectedFacet);
        } catch (Exception e) {
            Assert.fail("Unable to select a product having color swatch");
        }
        DiscoveryHelper.logInfo("Selected a product " + selectedProdId + " having color swatches");
    }

    @Then("^I verify that the selected color in the color swatch is highlighted$")
    public void iVerifyThatTheSelectedColorInTheColorSwatchIsHighlighted() throws Throwable {
        DiscoveryHelper.logInfo("Selected color on product" + selectedColorSwatch);
        DiscoveryHelper.logInfo("Selected color on color facet" + selectedFacet);
        Assert.assertTrue("Expected color swatch to be selected: " + selectedFacet + " Actual color swatch selected: " + selectedColorSwatch,
                selectedFacet.equals(selectedColorSwatch));
        DiscoveryHelper.logInfo("Verified that the selected color in the color swatch is highlighted");
    }

    @Then("^I verify that the product thumbnails are displayed with the selected (color|colors)$")
    public void iVerifyThatTheProductThumbnailsAreDisplayedWithTheSelectedColor(String value) throws Throwable {
        List<String> prodIds, prodIdsWithMoreLink;
        prodIds = DiscoveryHelper.getProductIdsWithColorSwatch();
        if (bloomingdales()) {
            prodIdsWithMoreLink = DiscoveryHelper.getProductIdsWithMoreColorSwatch();
            prodIds.removeAll(ListUtils.intersection(prodIds, prodIdsWithMoreLink));
        }
        for (String id : prodIds) {
            selectedColorSwatch = macys() ?
                    DiscoveryHelper.getSelectedColor(id) : DiscoveryHelper.getSelectedColorFamilyName(id);
            DiscoveryHelper.logInfo("Selected color swatch of product id: " + id + " " + selectedColorSwatch);
            DiscoveryHelper.logInfo("Selected color from color facet: " + selectedFacet);
            if (value.equals("color")) {
                Assert.assertTrue("Product id: " + id + " is not displayed with expected colors swatch: " + selectedFacetValues + " Actual color swatch selected: "
                        + selectedColorSwatch, selectedFacetValues.get(0).equalsIgnoreCase(selectedColorSwatch));
            } else {
                if (!(selectedFacetValues.get(0).equalsIgnoreCase(selectedColorSwatch) || selectedFacetValues.get(1).equalsIgnoreCase(selectedColorSwatch)))
                    Assert.fail("Product id: " + id + " is not displayed with expected colors swatch: " + selectedFacetValues + " Actual color swatch selected: " + selectedColorSwatch);
            }
        }
        DiscoveryHelper.logInfo("Verified that the product thumbnails are displayed with the selected " + value);
    }

    @And("^I select \"([^\"]*)\" facet value from Brand facet section$")
    public void I_select_brand_value_from_brand_section(String facetItem) throws Throwable {
        try {
            facetItem = facetItem.equals("available brand") ? facetItems : facetItem;
            WebElement facetEle = Elements.findElement(LeftFacet.getFacetDiv(brandFacet));
            String finalFacetItem = facetItem;
            WebElement toSelect = facetEle.findElements(By.className("item"))
                    .stream()
                    .filter(e -> e.getText().equalsIgnoreCase(finalFacetItem))
                    .findFirst()
                    .orElse(null);
            Clicks.click(toSelect);
            selectedFacetValues.add(facetItem);
        } catch (Exception e) {
            Assert.fail("Error selecting brand facet");
        }
        DiscoveryHelper.logInfo("Selected " + facetItem + " facet value from Brand facet section");
    }

    @Then("^I verify that the selected colors in the color swatch is highlighted$")
    public void iVerifyThatTheSelectedColorsInTheColorSwatchIsHighlighted() throws Throwable {
        DiscoveryHelper.logInfo("Selected color on product" + selectedColorSwatch);
        DiscoveryHelper.logInfo("Selected colors on color facet" + selectedFacetValues);
        if (!(selectedFacetValues.get(0).equalsIgnoreCase(selectedColorSwatch) || selectedFacetValues.get(1).equalsIgnoreCase(selectedColorSwatch)))
            Assert.fail("Expected color swatch to be selected: " + selectedFacetValues + " Actual color swatch selected: " + selectedColorSwatch + " For the product id: " + selectedProdId);
        DiscoveryHelper.logInfo("Verified that the selected colors in the color swatch is highlighted");
    }

    @And("^I navigate to the last page$")
    public void iNavigateToTheLastPage() throws Throwable {
        try {
            if (DiscoveryHelper.paginationAvailable()) {
                DiscoveryHelper.navigateToLastPage();
            } else {
                System.out.println("No pagination is available");
            }
        } catch (Exception e) {
            Assert.fail("Unable to navigate to last page" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Navigated to the last page");
    }

    @When("^I remove the selected facet from the breadcrumb$")
    public void iRemoveTheSelectFacetFromTheBreadcrumb() throws Throwable {
        try {
            int size = Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").size();
            for (int i = 0; i < size; i++) {
                WebElement facetItemToRemove = Elements.findElements(Elements.element("leftFacetNavigation_panel.facetBreadcrumbs")).get(i);
                selectedFacetValues.remove(facetItemToRemove.getText());
                Clicks.click(Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").get(i));
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
            }
        } catch (Exception e) {
            Assert.fail("Unable to remove facet from breadcrumb");
        }
        DiscoveryHelper.logInfo("Removed the selected facet from the breadcrumb");
    }

    @When("^I select (\\d+) (\\w+) in the \"([^\"]*)\" facet$")
    public void iSelectColorsInTheColorFacet(int count, String facetValue, String facetName) throws Throwable {
        selectedFacetValues = new ArrayList<>();
        if (facetName.contains("Customer")){
            facetName = DiscoveryHelper.getAllFacetName().
                    stream().filter(e -> e.contains("Customer")).findFirst().orElse(null);
        }
        List<String> currentFacetItems = DiscoveryHelper.getAllFacetValues(facetName);
        if (facetName.contains("Brand")) {
            List<WebElement> featuredBrandsList = Elements.findElement(Elements.element("leftFacetNavigation_panel.featured_brand_values")).findElements(By.tagName("li"));
            Clicks.hoverForSelection(featuredBrandsList.get(count + 3));
        }
        for (int i = 0; i < count; i++) {
            selectedFacet = currentFacetItems.get(i);
            selectedFacetValues.add(selectedFacet);
            Thread.sleep(3000);
            Wait.forPageReady();
            if (i > 0) {
                Navigate.browserRefresh();
                if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                    LeftFacet.expandFacet(facetName);
                }
            }

            LeftFacet.selectItemFromFacet(selectedFacet, facetName);
            Thread.sleep(3000);
            Wait.forPageReady();
        }
        DiscoveryHelper.logInfo("Selected facet values " + selectedFacetValues + " from " + facetName + " facet");
        DiscoveryHelper.logInfo("Selected " + count + facetValue + " in the " + facetName + " facet");
    }

    @When("^I remove (first|second|last) (\\w+) facet from the breadcrumb$")
    public void iRemoveTheColorFacetFromTheBreadcrumb(String currentFacetSelection, String facetName) throws Throwable {
        facetSelection = currentFacetSelection;
        try {
            int size = Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").size();
            switch (facetSelection) {
                case "first":
                    Clicks.click(Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").get(0));
                    break;
                case "second":
                    Clicks.click(Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").get(1));
                    break;
                case "last":
                    Clicks.click(Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").get(size - 1));
                    break;
            }
        } catch (Exception e) {
            Assert.fail("Unable to remove " + facetName + " facet from breadcrumb");
        }
        DiscoveryHelper.logInfo("Removed " + currentFacetSelection + " selected " + facetName + " facet from the breadcrumb");
    }

    @And("^I verify that the product thumbnails are updated$")
    public void iVerifyThatTheProductThumbnailsAreUpdated() throws Throwable {
        int currentThumbnailCount = helper.getTotalThumbnailCount();
        switch (facetSelection) {
            case "first":
            case "second":
                Assert.assertTrue("Product thumbnails count is not updated Expected count: " + totalThumbnailCount
                        + " Actual Displayed Count: " + currentThumbnailCount, currentThumbnailCount <= totalThumbnailCount);
                break;
            case "last":
                Assert.assertTrue("Product thumbnails count is not updated Expected count: " + totalThumbnailCount
                        + " Actual Displayed Count: " + currentThumbnailCount, currentThumbnailCount == totalThumbnailCount);
                break;
            default:
                Assert.assertTrue("Product thumbnails count is not updated Expected count: " + totalThumbnailCount
                        + " Actual Displayed Count: " + currentThumbnailCount, currentThumbnailCount <= totalThumbnailCount);
                break;
        }
        DiscoveryHelper.logInfo("Verified that the product thumbnails are updated");
    }

    @Then("^I verify that all of the products are displayed$")
    public void iVerifyThatAllOfTheProductsAreDisplayed() throws Throwable {
        int currentProductCount = DiscoveryHelper.getProductCount();
        Assert.assertTrue("All products are not displayed Expected product count: " + productCount
                + " Actual count displayed: " + currentProductCount, productCount == currentProductCount);
        DiscoveryHelper.logInfo("Verified that all of the products are displayed");
    }

    @When("^I select the quick peek of that product$")
    public void iSelectTheQuickPeekOfThatProduct() throws Throwable {
        try {
            DiscoveryHelper.selectQuickView(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to select Quick view for the product id " + selectedProdId + " " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the quick peek of that product id: " + selectedProdId);
    }

    @Then("^I verify that the selected color is highlighted in the color swatches on (quick view|pdp)$")
    public void iVerifyThatTheSelectedColorIsHighlightedInTheColorSwatchesOnQuickView(String pageType) throws Throwable {
        String selectedColor = pageType.equals("quick view") ?
                DiscoveryHelper.getSelectedColorFromQV(selectedProdId) : DiscoveryHelper.getSelectedColorFromPdp(selectedProdId);
        DiscoveryHelper.logInfo("Selected color on " + pageType + ": " + selectedColor);
        DiscoveryHelper.logInfo("Selected color on color facet: " + selectedFacet);
        Assert.assertTrue("Selected color is not highlighted in the color swatch on quick view overlay " +
                "Expected Color: " + selectedFacet + " Actual Color Displayed " + selectedColor, selectedColor.equals(selectedFacet));
        DiscoveryHelper.logInfo("Verified that the selected color is highlighted in the color swatches on " + pageType);
    }

    @When("^I navigate to PDP of that product$")
    public void iNavigateToPDPOfThatProduct() throws Throwable {
        try {
            DiscoveryHelper.selectProductThumbnail(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to navigate to PDP page for the product id: " + selectedProdId + e.getMessage());
        }
        DiscoveryHelper.logInfo("Navigated to PDP of that product id: " + selectedProdId);
    }

    @Given("^I am on SearchResultsPage for \"([^\"]*)\" in ([^\"]*) mode$")
    public void iAmOnSearchResultsPageForOnMode(String keyword, String mode) throws Throwable {
        searchKeyword = keyword;
        shoppingMode = mode;
        try {
            //Declaration
            ShopAndBrowse browse = new ShopAndBrowse();
            PageNavigation pageNavigation = new PageNavigation();

            pageNavigation.I_visit_the_web_site_as_a_guest_user();
            if (mode.equalsIgnoreCase("ISHIP")) {
                pageNavigation.I_navigate_to_international_context_page();
                browse.I_change_country_to(ISHIPCountry);
            }
            if (mode.equalsIgnoreCase("REGISTRY")) {
                Clicks.click("home.goto_wedding_registry");
            }
            TextBoxes.typeTextNEnter("home.search_field", searchKeyword);
            Wait.forPageReady();
            shouldBeOnPage("search_result");
            DiscoveryHelper.navigateToComponentizationURL();
            shouldBeOnPage("search_result");
        } catch (Exception e) {
            Assert.fail("Error Navigating to " + searchKeyword + " in " + mode + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Navigated to Search Results Page for " + searchKeyword + " on " + mode + " mode");
    }

    @And("^I verify that products are filtered with selected price facet value(?:s)?$")
    public void iVerifyThatProductsAreFilteredAsPerSelectedPriceFacetValue() throws Throwable {
        boolean flag;
        List<String> prodIds = DiscoveryHelper.getMemberProdIds();
        prodIds = prodIds.subList(0, 5);
        for (String  id : prodIds) {
            Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailDetails(id);
            for (Map.Entry<String, HashMap> detail : details.entrySet()) {
                String price;
                WebElement priceEle = (WebElement) detail.getValue().get("elm_prices");
                price = priceEle.getText();
                WebElement productNameEle = (WebElement) detail.getValue().get("elm_product_name");
                String productName = productNameEle.getText();
                DiscoveryHelper.logInfo("Displayed price for the product name: " + productName + " is " + price);
                DiscoveryHelper.logInfo("Selected price in price facet: " + selectedFacetValues);
                flag = DiscoveryHelper.priceVerification(productName, selectedFacetValues, price);
                Assert.assertTrue("Product price and facet price mismatch Expected price should be between " + selectedFacetValues
                        + "Actual price displayed for product: " + price + " for product name: " + productName, flag);
            }
        }
        DiscoveryHelper.logInfo("Verified that products are filtered with selected price facet value");
    }

    @And("^I select minimum price as \"([^\"]*)\" and maximum price as \"([^\"]*)\" range$")
    public void iSelectMinimumPriceAndMaximumPrice(String min, String max) throws Throwable {
        selectedFacetValues = new ArrayList<>();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        productCount = DiscoveryHelper.getProductCount();
        thumbnailColumns = ProductThumbnail.productThumbnailColumns();
        try {
            Wait.untilElementNotPresent("category_browse.loading_mask");
            String facetName = "Price";
            if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet(facetName);
            }
            TextBoxes.typeTextbox("leftFacetNavigation_panel.customPriceFrom", min);
            TextBoxes.typeTextbox("leftFacetNavigation_panel.customPriceTo", max);
            String facetValue = "$" + min + "-" + "$" + max;
            selectedFacetValues.add(facetValue);
        } catch (Exception e) {
            Assert.fail("Unable to enter the Minimum and Maximum value" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected minimum price as " + min + " and maximum price as " + max);
    }

    @And("^I select 'GO' button from price facet$")
    public void iSelectGOButtonFromPriceFacet() throws Throwable {
        try {
            Clicks.click("leftFacetNavigation_panel.customPriceSubmit");
            Wait.untilElementPresent("category_browse.loading_mask");
        } catch (Exception e) {
            Assert.fail("Unable to select GO button" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected 'GO' button from price facet");
    }

    @And("^I verify that only custom price facet is selected from price facet section$")
    public void iVerifyOnlyCustomPriceFacetIsSelected() throws Throwable {
        List<WebElement> facetValues;
        facetValues = Elements.findElement(LeftFacet.getFacetDiv("Price")).findElements(By.tagName("a"));
        facetValues = facetValues.stream().filter(el -> el.getAttribute("class").contains("selected")).collect(Collectors.toList());
        if (facetValues.size() != 1) {
            Assert.fail("Custom price facet is not selected from price facet section");
        }
        DiscoveryHelper.logInfo("Verified that only custom price facet is selected from price facet section");
    }

    @And("^I verify that only pre-defined price facet is selected from price facet section$")
    public void iVerifyThatOnlyPreDefinedPriceFacetIsSelectedFromPriceFacetSection() throws Throwable {
        List<WebElement> facetValues;
        List<String> selectedFacetValues;
        LeftFacet.expandFacet("Price");
        facetValues = Elements.findElement(LeftFacet.getFacetDiv("Price")).findElements(By.tagName("a"));
        //facetValues = facetValues.stream().filter(el -> el.getAttribute("class").contains("selected")).collect(Collectors.toList());
        selectedFacetValues = facetValues.stream().filter(el -> el.getAttribute("class").contains("selected")).collect(Collectors.toList()).stream().map(WebElement::getText).collect(Collectors.toList());
        if (selectedFacetValues.contains("Custom:") || selectedFacetValues.size() < 2) {
            Assert.fail("Pre-defined price facet is not selected from price facet section");
        }
        DiscoveryHelper.logInfo("Verified that only pre-defined price facet is selected from price facet section");
    }

    @And("^I verify that resulting url with all selected facet values$")
    public void I_verify_that_resulting_url_with_all_selected_facet_values() throws Throwable {
        try {
            strUtils = WebDriverManager.getCurrentUrl();
            String afterURLDecode = decode(decode(strUtils, "UTF-8"), "UTF-8").replace("|", "-").replaceAll("_"," ");
            selectedFacetValues.stream().filter(facet -> !afterURLDecode.contains(facet.replace("\\$", "").trim()))
                    .forEach(facet -> Assert.fail("Selected facet not appended in url"));
            selectedFacetNames.stream().filter(facets -> !afterURLDecode.replace("_", " ").toLowerCase().contains(facets.toLowerCase()))
                    .forEach(facets -> Assert.fail("Selected facet Name not properly appended in url"));
        } catch (Exception e) {
            Assert.fail("Error selected facet not appended in url" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that resulting url with all selected facet values");
    }

    @And("^I verify that canonical tag contains facet value of same facet$")
    public void I_verify_that_canonical_tag_with_facet_value() throws Throwable {
        String afterTagDecode;
        List<WebElement> breadCrumbsList;
        try {
            afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href"), "UTF-8"), "UTF-8").replace("|", "-");
            breadCrumbsList = Elements.findElements(Elements.element("leftFacetNavigation_panel.facetBreadcrumbs"));
            breadCrumbsList.stream().filter(ele -> !afterTagDecode.contains(ele.getText().replaceAll("\\$", "").trim()))
                    .forEach(ele -> Assert.fail("canonical tag does not contains facet value of same facet"));
        } catch (Exception e) {
            Assert.fail("Error getting canonical tag" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified canonical tag contains facet value of same facet");
    }

    @And("^I verify that facet values in canonical tag with alpha order for \"([^\"]*)\"$")
    public void I_verify_that_facet_canonical_tag_alpha_order(String keyword) throws Throwable {
        String afterTagDecode;
        ArrayList<String> urlFacetName = new ArrayList<>();
        ArrayList<String> urlFacetValue = new ArrayList<>();
        try {
            afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href"), "UTF-8"), "UTF-8").replace("|", "-").replaceAll("_"," ");
            String urlFacet = afterTagDecode.split(keyword)[1].split("/")[1].replaceAll("Color normal","color");
            Collections.addAll(urlFacetName, urlFacet.split(","));
            String facetValue = afterTagDecode.split(keyword)[1].split("/")[2].replaceAll("0-49.99","Under 50");
            Collections.addAll(urlFacetValue, facetValue.split(","));
            Collections.sort(selectedFacetNames);
            Collections.sort(selectedFacetValues);
            for (String s:
                    selectedFacetValues) {
                Assert.assertTrue("Facet values are not in alpha order", urlFacetValue.toString().toLowerCase().contains(s.replaceAll("\\$","").toLowerCase()));
            }
            Assert.assertTrue("Facet names are not in alpha order", selectedFacetNames.toString().toLowerCase().equals(urlFacetName.toString().toLowerCase()));
        } catch (Exception e) {
            Assert.fail("Facet values in canonical tag are not displayed with alpha order" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified facet values in canonical tag with alpha order for " + keyword);
    }

    @When("^I select the quick peek of random product$")
    public void I_Select_Quick_peek_of_random_product() throws Throwable {
        Random randomGenerator;
        try {
            List<String> productIds = DiscoveryHelper.getProductIds();
            randomGenerator = new Random();
            int index = randomGenerator.nextInt(productIds.size() - 1);
            selectedProdId = productIds.get(index);
            DiscoveryHelper.selectQuickView(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to select the quick peek of random product" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the quick peek of random product id: " + selectedProdId);
    }

    @When("^I navigate to ((?i)Browse|Sub Splash|Category Splash) page in ((?i)SITE|REGISTRY|ISHIP) mode$")
    public void iNavigateToPageInMode(String pageType, String mode) throws Throwable {
        try {
            //Declaration
            com.macys.sdt.shared.steps.website.ShopAndBrowse browse = new com.macys.sdt.shared.steps.website.ShopAndBrowse();
            com.macys.sdt.shared.steps.website.PageNavigation pageNavigation = new com.macys.sdt.shared.steps.website.PageNavigation();

            pageNavigation.I_visit_the_web_site_as_a_guest_user();
            if (mode.equalsIgnoreCase("ISHIP")) {
                pageNavigation.I_navigate_to_international_context_page();
                browse.I_change_country_to(ISHIPCountry);
            }
            if (mode.equalsIgnoreCase("REGISTRY")) {
                Clicks.click("home.goto_wedding_registry");
            }
            if (pageType.equalsIgnoreCase("browse") || pageType.equalsIgnoreCase("sub splash"))
                Home.selectRandomSubCategory();
            else
                Home.selectRandomCategory();
        } catch (Exception e) {
            Assert.fail("Error Navigating to " + pageType + " in " + mode + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Navigate to " + pageType + " page in " + mode + " mode");
    }

    @When("^I verify that the product price falls in the selected price range on (quickview|pdp)$")
    public void I_verify_price_falls_in_selected_price_range(String pageType) throws Throwable {
        String price = null;
        try {
            Wait.forPageReady();
            if (pageType.equalsIgnoreCase("quickview"))
                price = Elements.findElement(Elements.element("quickViewOverlay.quickViewPrice")).getText();
            else {
                try {
                    if (Elements.elementPresent("productThumbnailPanel.product_price_pdp"))
                        price = Elements.findElement(Elements.element("productThumbnailPanel.product_price_pdp")).getText().split("\n")[0].replaceAll("   ", "\n").trim();
                    else {
                        price = Elements.findElement(By.className("colorway")).getText();
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("Unable to get price for product price element");
                }
            }
            DiscoveryHelper.logInfo("Displayed price for the product id: " + selectedProdId + " is " + price);
            DiscoveryHelper.logInfo("Selected price from price facet: " + selectedFacetValues);
            Boolean priceVerification = DiscoveryHelper.priceVerification(selectedProdId, selectedFacetValues, price);
            Assert.assertTrue("Selected product price is not in the selected price range", priceVerification);
        } catch (Exception e) {
            Assert.fail("Unable to verify selected price range" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the product price falls in the selected price range on " + pageType);
    }

    @Then("^I verify that remaining facets are updated its facet values and product count availability$")
    public void iVerifyThatRemainingFacetsAreUpdatedItsFacetValuesAndProductCountAvailability() throws Throwable {
        try {
            List<String> facetNames = DiscoveryHelper.getAllFacetName();
            facetNames.remove("Price");
            facetNames = facetNames.stream().filter(e -> !e.contains("Customer")).collect(Collectors.toList());
            if (bloomingdales()) Navigate.browserRefresh();
            for (String facetName : facetNames) {
                Map<String, Integer> facetDetails = DiscoveryHelper.getFacetDetail(facetName);
                for (Map.Entry<String, Integer> detail : facetDetails.entrySet()) {
                    Assert.assertTrue("Facet value count is displayed as zero for facet Name: " + facetName + " and Facet Value: " +
                            detail.getKey() + " Displayed as: " + detail.getValue(), detail.getValue() != 0);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that remaining facets are updated its facet values and product count availability");
    }

    @When("^I verify that product count for the selected (Price|Color|Brand|CUSTRATINGS) in the overlay and results match$")
    public void i_verify_that_product_count_for_the_selected_price_in_the_overlay_and_results_match(String facetName) throws Throwable {
        int facetCount = 0;
        List<WebElement> element;
        try {
            Wait.forLoading("left_facet.loading");
            Wait.forPageReady();
            if (facetName.contains("Brand")) {
                List<WebElement> allBrandsList = Elements.findElement(Elements.element("leftFacetNavigation_panel.all_brand_list")).findElements(By.tagName("a"));
                element = allBrandsList.stream().filter(ele -> ele.getAttribute("class").contains("selected")).collect(Collectors.toList());
            } else {
                element = Elements.findElement(LeftFacet.getFacetDiv(facetName)).findElements(By.tagName("a"));
                element = element.stream().filter(ele -> ele.getAttribute("class").contains("selected")).collect(Collectors.toList());
            }
            for (WebElement hoverElement :
                    element) {
                WebElement ele = hoverElement.findElement(By.className("item"));
                Clicks.javascriptHover(ele);
                int count = Integer.parseInt(ele.getAttribute("title").split("\\(")[1].replaceAll("\\D+", ""));
                facetCount = facetCount + count;
                System.out.println("error" + facetCount);
            }
            int currentProductCount = DiscoveryHelper.getProductCount();
            System.out.println("AfterSearchCount" + currentProductCount);
            // As per the comments in Feature file the product count can be off by 2
            Assert.assertTrue("Current product count and previous selected price in the overlay count is NOT same Expected Count: " + facetCount +
                    " Actual Displayed Count: " + currentProductCount, (facetCount == currentProductCount) || (facetCount == (currentProductCount - 2)));
        } catch (Exception e) {
            Assert.fail("Unable to get facet count" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified product count for the selected " + facetName + " in the overlay and results match");
    }

    @When("^I select 'single' facet value from 'each' facet section$")
    public void iSelectSingleFacetValueFromEachFacetSection() throws Throwable {
        selectedFacetValues = new ArrayList<>();
        try {
            List<String> facetNames = DiscoveryHelper.getAllFacetName().stream()
                    .filter(name -> !(name.contains("Customer") || name.equals("Promotions") || name.contains("Pick")))
                    .collect(Collectors.toList());
            String lastSelectedFacetName = null;
            for (int i = 0; i < facetNames.size(); i++) {
                String facetName;
                List<String> currentFacetName = DiscoveryHelper.getAllFacetName().stream()
                        .filter(name -> !(name.contains("Customer") || name.equals("Promotions") || name.contains("Pick")))
                        .collect(Collectors.toList());
                if (lastSelectedFacetName == null) {
                    facetName = currentFacetName.get(i);
                } else if (currentFacetName.indexOf(lastSelectedFacetName) < currentFacetName.size() - 1)
                    facetName = currentFacetName.get(currentFacetName.indexOf(lastSelectedFacetName) + 1);
                else
                    break;
                if (facetNames.indexOf(lastSelectedFacetName) == facetNames.size())
                    break;
                Random randomGenerator = new Random();
                int index;
                Navigate.browserRefresh();
                LeftFacet.expandFacet(facetName);
                List<String> facetValue = DiscoveryHelper.getAllFacetValues(facetName);
                if (facetValue.isEmpty()) {
                    lastSelectedFacetName = facetName;
                    continue;
                }
                if (facetValue.size() == 1)
                    index = 0;
                else
                    index = randomGenerator.nextInt(facetValue.size() - 1);
                selectedFacet = facetValue.get(index);
                DiscoveryHelper.logInfo("Facet Name: " + facetName);
                DiscoveryHelper.logInfo("Facet Value: " + selectedFacet);
                LeftFacet.selectItemFromFacet(selectedFacet, facetName);
                selectedFacetValues.add(selectedFacet);
                lastSelectedFacetName = facetName;
            }
        } catch (Exception e) {
            Assert.fail("Error Selecting on each facet item" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected 'single' facet value from 'each' facet section");
    }

    @When("^I select random bops facet value$")
    public void iSelectRandomBopsFacetValue() throws Throwable {
        LeftFacet.expandFacet("Pick Up In Store");
        Clicks.clickRandomElement(LeftFacet.getFacetItems("Pick Up In Store"));
        if (bloomingdales()) {
            Clicks.javascriptClick(LeftFacet.getFacetApply("Pick Up In Store"));
            Wait.forLoading("left_facet.loading");
            Wait.forPageReady();
        }
        selectedFacet = Elements.getText("left_facet.facet_breadcrumbs");
        selectedFacetValues.add(selectedFacet);
        DiscoveryHelper.logInfo("Selected random bops facet value: " + selectedFacet + " from bops facet");
    }

    @Then("^I verify that bops overlay is (not displayed|displayed) with stores list$")
    public void iVerifyBopsOverlayWithStoresList(String condition) throws Throwable {
        try {
            if (condition.equalsIgnoreCase("displayed")) {
                Assert.assertTrue("Error Store results is not displayed on bops overlay",
                        Elements.elementPresent(Elements.element("change_pickup_store_dialog.bops_stores")));
            }else
                Assert.assertFalse("Error Store results is displayed on bops overlay",
                        Elements.elementPresent(Elements.element("change_pickup_store_dialog.bops_stores")));
        } catch (Exception e) {
            Assert.fail("Error Selecting Zip Code link in pick-up in-store facet" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified bops overlay with stores list");
    }

    @Then("^I verify that facet breadcrumb are listed as per faceted url$")
    public void iShouldSeeFacetBreadcrumbAsPerFacetedUrl() throws Throwable {
        try {
            String currentPageURL = decode(WebDriverManager.getCurrentUrl(), "UTF-8");
            List<WebElement> ele = Elements.findElements("left_facet.facet_breadcrumbs");
            ele.stream().filter(e -> !currentPageURL.contains(e.getText())).
                    forEach(e -> Assert.fail("Facet selection in the breadcrumb mismatches the current URL"));
        } catch (Exception e) {
            System.out.println("Error to verify facet URL with facet breadcrumb" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified facet breadcrumb as per faceted url");
    }

    @And("^I verify that products are filtered as per faceted url$")
    public void iVerifyThatProductsAreFilteredAsPerFthumbnail_details() throws Throwable {
        int count = 0;
        try {
            String selectedFacet = Elements.findElement("left_facet.facet_breadcrumbs").getText();
            Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailsDetails();
            for (Map.Entry<String, HashMap> detail : details.entrySet()) {
                if (!detail.getValue().get("elm_product_name").toString().toLowerCase().contains(selectedFacet.toLowerCase())) {
                    Assert.fail("Products are not filtered as per the selected facet url" + detail.getValue().get("elm_product_name").toString());
                }else{
                    count = count + 1;
                }
                if (count == 4) break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that products are filtered as per faceted url");
    }

    @When("^I select 'random' page number from pagination$")
    public void iSelectRandomPageNumberFromPagination() throws Throwable {
        if (DiscoveryHelper.paginationAvailable()) {
            int pageCount = DiscoveryHelper.getTotalPageCount();
            if (pageCount > 2) {
                selectedPageNo = ThreadLocalRandom.current().nextInt(2, (pageCount - 1));
                System.out.println("Page Number: " + selectedPageNo);
                DiscoveryHelper.goToPageNumber(selectedPageNo);
            } else {
                DiscoveryHelper.goToPageNumber(pageCount);
                selectedPageNo = 2;
            }
        } else {
            selectedPageNo = 1;
        }
        DiscoveryHelper.logInfo("Selected 'random' page number from pagination " + selectedPageNo);
    }

    @And("^I verify that previous pagination selection persist$")
    public void iVerifyThatPreviousPaginationSelectionPersist() throws Throwable {
        int currentPageNo = DiscoveryHelper.paginationAvailable() ? DiscoveryHelper.getCurrentPageNumber() : 1;
        DiscoveryHelper.logInfo("Current page no: " + currentPageNo);
        DiscoveryHelper.logInfo("Previous page no: " + selectedPageNo);
        Assert.assertTrue("Current page selection previous page selection is not same Expected Page no: " + selectedPageNo +
                " Current page displayed: " + currentPageNo, currentPageNo == selectedPageNo);
        DiscoveryHelper.logInfo("Verified that previous pagination selection persist");
    }

    @And("^I select random member product from thumbnail grid$")
    public void iSelectRandomProductMemberProductFromThumbnailGrid() throws Throwable {
        try {
            Wait.untilElementNotPresent("product_thumbnails.loading_div");
            prodIds = DiscoveryHelper.getMemberProdIds();
            Random randomGenerator = new Random();
            int randomProdIndex = randomGenerator.nextInt(prodIds.size());
            System.out.println(prodIds.get(randomProdIndex));
            selectedSortBy = macys() ? DropDowns.getSelectedValue(Elements.element("search_result.sort_by_select")) :
                    Elements.findElement("search_result.sort_by_select").getText();
            selectedProdId = (prodIds.size() == 1) ?
                    prodIds.get(randomProdIndex) : prodIds.get(0);
            DiscoveryHelper.selectProductThumbnail(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Error selecting random product thumb of prod_id " + selectedProdId + " " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected random product member product from thumbnail grid");
    }

    @And("^I verify that main search field cleared$")
    public void iShouldSeeMainSearchFieldCleared() throws Throwable {
        Wait.forPageReady();
        System.out.println("search keyword" + searchKeyword);
        Assert.assertTrue("Search box is not empty: ", Elements.findElement("home.search_field").getAttribute("value").equals(""));
        DiscoveryHelper.logInfo("Verified main search field cleared");
    }

    @And("^I search for \"([^\"]*)\" in search box$")
    public void iSearchForInSearchBox(String value) throws Throwable {
        searchKeyword = value;
        TextBoxes.typeTextNEnter("home.search_field", searchKeyword);
        Wait.forPageReady();
        DiscoveryHelper.logInfo("Searched for " + searchKeyword + " in search box");
    }

    @And("^I verify that page have search keyword as \"([^\"]*)\"$")
    public void iVerifyThatPageHaveSearchKeywordAs(String keyword) throws Throwable {
        try {
            Wait.forPageReady();
            String displayedKeyword = Elements.findElement("search_result.search_keyword").getText().trim();
            Assert.assertTrue("Expected Page with keyword: " + keyword + " Actual displayed keyword: " + displayedKeyword,
                    keyword.equalsIgnoreCase(displayedKeyword));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + keyword + " is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified Page have search keyword as " + keyword);
    }

    @And("^I verify that \"([^\"]*)\" default message is displayed in search box$")
    public void iShouldSeeDefaultMessageInSearchBox(String keyword) throws Throwable {
        try {
            Wait.forPageReady();
            String displayedKeyword = Elements.findElement("home.search_field").getAttribute("value").trim();
            String keywordPlaceHolder = Elements.findElement("home.search_field").getAttribute("placeholder").trim();
            Assert.assertTrue("Expected default message in search box placeholder: " + keyword + " Actual displayed keyword place holder: " + keywordPlaceHolder,
                    keyword.equalsIgnoreCase(keywordPlaceHolder));
            Assert.assertTrue("Expected default message in search box is not displayed,  Actual displayed keyword: " + displayedKeyword,
                    keywordPlaceHolder.equals(""));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + keyword + " is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified default message in search box displayed as " + keyword);
    }

    @And("^I verify that (\\d+) error code is not displayed in search page$")
    public void iVerifyThatErrorCodeIsNotDispalyedInSearchPage(int errorCode) throws Throwable {
        WebElement contentContainer = Elements.findElement("home.globalContentContainer");
        List<String> linkElementsHref = contentContainer.findElements(By.tagName("a")).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
        for (String href : linkElementsHref) {
            int responseCode = DiscoveryHelper.getResponseCode(href);
            System.out.println("Url: " + href);
            System.out.println("Response code: " + responseCode);
            Assert.assertFalse("Link is served with " + errorCode + " error code",
                    responseCode == errorCode);
        }
        DiscoveryHelper.logInfo("Verified error code " + errorCode + " in search page is not displayed");
    }

    @Then("^I verify that Search Results contents are displayed$")
    public void iVerifyThatSearchResultsContentsAreDisplayed() throws Throwable {
        Assert.assertFalse("Previous page via arrow button is displayed",
                Elements.elementPresent("category_browse.goto_previous_page_via_arrow"));
        Assert.assertTrue("Next page via arrow button is displayed",
                Elements.elementPresent("category_browse.goto_next_page_via_arrow"));
        Assert.assertTrue("Left navigation facet panel is not displayed",
                Elements.elementPresent("left_facet.facets_panel"));
        Assert.assertTrue("Sort By options are not displayed",
                Elements.elementPresent("pagination.sort_by"));
        Assert.assertTrue("Products are not displayed",
                DiscoveryHelper.getProductIds().size() != 0);
        DiscoveryHelper.logInfo("Verified the Search Results contents");
    }

    @Then("^I verify that meta \"([^\"]*)\" tag as \"([^\"]*)\"$")
    public void iShouldSeeMetaTagAs(String metaTag, String expectedKeywords) throws Throwable {
        String displayedKeywords = Elements.findElement("search_result.metaKeyword").getAttribute("content");
        Assert.assertTrue("ERROR - APP: Expected Meta" + metaTag + "are not displayed, Expected Keywords: " + expectedKeywords
                + " Actual Keywords Displayed: " + displayedKeywords, displayedKeywords.equals(expectedKeywords));
        DiscoveryHelper.logInfo("Verified meta tags " + metaTag + " are displayed as " + expectedKeywords);
    }

    @And("^I verify that \"([^\"]*)\" products are displayed with (three levels of pricing information|price type with badge text)$")
    public void iVerifyThatProductsAreDisplayedWithThreeLevelsOfPricingInformation(String productType, String priceType) throws Throwable {
        List<String> productIds = productType.equals("Member") ? DiscoveryHelper.getMemberProdIds() :
                DiscoveryHelper.getMasterProductIds();
        List<String> productDisplayed = new ArrayList<>();
        for (String id : productIds) {
            Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailDetails(id);
            if (priceType.equals("three levels of pricing information")) {
                WebElement price = (WebElement) details.get(id).get("elm_prices");
                int priceLevel = price.findElements(Elements.element("productThumbnailPanel.prices")).size();
                if (priceLevel == 3) {
                    System.out.println("Displayed product price: " + price.getText());
                    productDisplayed.add(id);
                }
            } else {
                WebElement batchText = (WebElement) details.get(id).get("elm_batch_text");
                if (batchText != null) {
                    System.out.println("Displayed product batch text: " + batchText.getText());
                    productDisplayed.add(id);
                }
            }
        }
        Assert.assertFalse("No product is displayed with " + priceType + " in the page. Please retry search with different keyword",
                productDisplayed.size() == 0);
        DiscoveryHelper.logInfo("Verified " + productType + " products with " + priceType);
    }

    @When("^I navigate to Browse page with \"([^\"]*)\" facet$")
    public void iNavigateToBrowsePageWithFacet(String facetName) throws Throwable {
        int maxTries = 5;
        boolean pageFound = false;
        for (int i = 0; i <= maxTries; i++) {
            Home.selectRandomSubCategory();
            if (DiscoveryHelper.getAllFacetName().contains(facetName)) {
                pageFound = true;
                break;
            }
        }
        Assert.assertTrue("Unable to navigate to browse page having facet in "
                + maxTries + " attempts", pageFound);
        DiscoveryHelper.logInfo("Navigated to Browse page having " + facetName + " facet");
    }

    @And("^I verify that other facets excluding BOPS are displayed$")
    public void iShouldSeeOtherFacetsExcludingBOPS() throws Throwable {
        List<String> facetNames = DiscoveryHelper.getAllFacetName();
        facetNames.remove(facetNames.stream().filter(name -> (name.contains("In Store") || name.contains("In-Store")))
                .findFirst().orElse(null));
        Assert.assertFalse("No other facets is displayed in the page other than BOPS facet: ",
                facetNames.size() == 0);
        DiscoveryHelper.logInfo("Verified other facets excluding BOPS are displayed");
    }

    @Then("^I verify that \"([^\"]*)\" facet is (listed|not listed) on left nav$")
    public void I_verify_that_facet__is_listed_on_left_nav(String facetName, String condition) throws Throwable {
        List<String> faceNames = DiscoveryHelper.getAllFacetName();
        if (faceNames != null) {
            selectedFacetName = facetName.contains("In-Store") ? faceNames.stream()
                    .filter(name -> (name.contains("In Store") || name.contains("In-Store"))).findFirst().orElse(null)
                    : facetName;
            if (condition.equals("not listed"))
                Assert.assertFalse("Facet: " + selectedFacetName + " is displayed in left nav",
                        faceNames.contains(selectedFacetName));
            else
                Assert.assertTrue("Facet: " + selectedFacetName + " is not displayed in left nav",
                        faceNames.contains(selectedFacetName));
        } else {
            Assert.fail("No facet is listed in left nav");
        }
        DiscoveryHelper.logInfo("Verified " + facetName + " facet is " + condition + " displayed in left nav");
    }

    @When("^I navigate to random category browse page$")
    public void iNavigateToRandomBrowsePage() throws Throwable {
        Home.selectRandomSubCategory();
        DiscoveryHelper.logInfo("Navigated to random category browse page");
    }

    @Then("^I verify that the Sort By \"([^\"]*)\" functionality$")
    public void iVerifyTheSortByFunctionality(String sortBy) throws Throwable {
        Double firstProductPrice = 0.00, secondProductPrice = 0.00, lastProductPrice = 0.00;
        if (sortBy.contains("Price")) {
            List<String> memberProdIds = DiscoveryHelper.getMemberProdIds();
            List<Double> productPrices = new ArrayList<>();
            List<String> productIds = Arrays.asList(memberProdIds.get(0), memberProdIds.get(1), memberProdIds.get(memberProdIds.size() - 1));
            for (String id : productIds) {
                WebElement priceElement = (WebElement) DiscoveryHelper.getProductThumbnailDetails(id).get(id).get("elm_prices");
                productPrices.add(DiscoveryHelper.getProductPrice(priceElement.getText()).get("maxPrice"));
            }
            firstProductPrice = productPrices.get(0);
            secondProductPrice = productPrices.get(1);
            lastProductPrice = productPrices.get(2);
        }
        switch (sortBy) {
            case "Price: Low to High":
            case "Price (low-high)":
                Assert.assertTrue("First product price is not less than or equal to second product price",
                        firstProductPrice <= secondProductPrice);
                Assert.assertTrue("First product price is not less than last product price",
                        firstProductPrice < lastProductPrice);
                break;
            case "Price: High to Low":
            case "Price (high-low)":
                Assert.assertTrue("First product price is not greater than or equal to second product price",
                        firstProductPrice >= secondProductPrice);
                Assert.assertTrue("First product price is not greater than last product price",
                        firstProductPrice > lastProductPrice);
                break;
            default:
                int currentProductCount = DiscoveryHelper.getProductCount();
                Assert.assertTrue("Current Product count and previous count mismatch Expected Count: " + productCount + " Actual Count: "
                        + currentProductCount, currentProductCount == productCount);
                break;
        }
        DiscoveryHelper.logInfo("Verified Sort By " + sortBy + "functionality");
    }

    @And("^I verify that zipcode is displayed based on store cookie value under bops facet$")
    public void iVerifyThatZipcodeIsDisplayedBasedOnStoreCookieValueUnderBopsFacet() throws Throwable {
        LeftFacet.expandFacet(selectedFacetName);
        Wait.untilElementPresent("left_facet.bops_location");
        String currentBopsLocation = Elements.findElement("left_facet.bops_location").getText();
        Assert.assertTrue("Current Bops Location and zip code cookie value is different Expected BOPS location: "
                + zipCode + " Actual Displayed location: " + currentBopsLocation, currentBopsLocation.equals(zipCode));
        DiscoveryHelper.logInfo("Verified zipcode is displayed based on store cookie value under bops facet");
    }

    @When("^I select \"([^\"]*)\" Miles under bops facet$")
    public void iSelectMilesUnderBopsFacet(String miles) throws Throwable {
        LeftFacet.expandFacet("Pick Up In Store");
        if (macys()) {
            Wait.untilElementPresent("left_facet.bops_store_search_radius");
            int index = DropDowns.getAllValues("left_facet.bops_store_search_radius").indexOf(miles);
            Assert.assertFalse("Unable to find give miles under Bops Facet, Given miles: " + miles,
                    index == -1);
            DropDowns.selectByIndex("left_facet.bops_store_search_radius", index);
        } else {
            Wait.untilElementPresent("bops_facet.miles_select");
            Clicks.click(Elements.findElement("bops_facet.miles_select"));
            WebElement ele = Elements.findElements("bops_facet.available_miles")
                    .stream()
                    .filter(el -> el.getText().equals(miles))
                    .findFirst()
                    .orElse(null);
            Clicks.click(ele);
        }
        DiscoveryHelper.logInfo("Selected " + miles + " miles under Bops facet");
    }

    @Then("^I verify that store values are displayed under bops facet$")
    public void iShouldSeeStoreValuesUnderBopsFacet() throws Throwable {
        try {
            Assert.assertFalse("Facet values are not displayed under Bops facet",
                    DiscoveryHelper.getAllFacetValues(selectedFacetName).size() == 0);
        } catch (Exception e) {
            Assert.fail("BOPS facet is not displayed in laft nav" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified store values are displayed under bops facet");
    }

    @When("^I clear store facet from breadcrumb$")
    public void iClearStoreFacetFromBreadcrumb() throws Throwable {
        List<String> facets = Elements.findElements("leftFacetNavigation_panel.facetBreadcrumbs").stream()
                .map(WebElement::getText).collect(Collectors.toList());
        int index = facets.indexOf(selectedFacet);
        Assert.assertFalse("Given facet value is not displayed in bread crumbs",
                index == -1);
        Clicks.click(Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").get(index));
        DiscoveryHelper.logInfo("Cleared store facet from breadcrumb");
    }

    @When("^I search for zipcode \"([^\"]*)\" in Pick Up In Store facet$")
    public void iSearchForZipcodeInPickUpInStoreFacet(String zipcode) throws Throwable {
        try {
            if (Elements.findElement(LeftFacet.getFacetDiv("Pick Up In Store")).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet("Pick Up In Store");
            }
            TextBoxes.typeTextNEnter("left_facet.bops_store_search_box", zipcode);
        } catch (Exception e) {
            Assert.fail("Error Zipcode search is not working in bops facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for zipcode " + zipcode + " in Pick up In store facet");
    }

    @Then("^I verify that \"([^\"]*)\" error message is displayed under bops facet$")
    public void iShouldSeeErrorMessageUnderBopsFacet(String message) throws Throwable {
        String ele = macys() ? "left_facet.bops_no_store_message" : "change_pickup_store_dialog.errors";
        Wait.untilElementPresent(ele);
        String val = Elements.getText(ele);
        Assert.assertTrue("Expected error: " + message + " is not displayed under bops facet, Displayed message: " + val,
                val.equals(message));
        DiscoveryHelper.logInfo("Verified that error message " + message + " is displayed under bops facet");
    }

    @Then("^I verify that the Sort By displayed with all options$")
    public void iShouldSeeTheSortByDisplayedWithAllOptions() throws Throwable {
        List<String> sortOptions, sortList;
        if (macys()) {
            sortOptions = DropDowns.getAllValues("search_result.sort_by_select");
            sortList = Arrays.asList("Featured Items", "Price: Low to High", "Price: High to Low", "Customers' Top Rated", "Best Sellers", "New Arrivals");
        }else {
            sortList = Arrays.asList("New Arrivals", "Best Sellers", "Customer Top Rated", "Price (high-low)", "Price (low-high)");
            Clicks.clickWhenPresent("search_result.sort_by_select");
            Wait.forLoading("search_result.sort_by_option");
            List<WebElement> ele = Elements.findElements(By.className("sortByItens")).stream()
                    .filter(el -> !el.getText().equals("")).collect(Collectors.toList());
            sortOptions = ele.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        Assert.assertTrue("All the sort by options are not displayed", sortOptions.equals(sortList));
        DiscoveryHelper.logInfo("Verified Sort By displayed with all options");
    }

    @And("^I verify that the product count is \"([^\"]*)\" than \"([^\"]*)\"$")
    public void iVerifyTheProductCountIsThan(String condition, int count) throws Throwable {
        int productCount = DiscoveryHelper.getProductCount();
        if (condition.equals("more")) {
            Assert.assertTrue("Product count mismatch, Expected count: more than " + count + " Actual: " + productCount,
                    productCount >= count);
        } else {
            Assert.assertTrue("Product count mismatch, Expected count: less than " + count + " Actual: " + productCount,
                    productCount <= count);
        }
        DiscoveryHelper.logInfo("Verified product count is displayed " + condition + " than " + count);
    }

    @Then("^I verify that edit option inside Recently Viewed panel is displayed$")
    public void ishouldseeeditoptioninsideRecentlyViewedpanel() throws Throwable {
        try {
            Wait.untilElementPresent("footer_panel.recently_viewed");
            Wait.untilElementPresent("footer_panel.rviEdit");
            Assert.assertTrue("Recently viewed edit link is not displayed", Elements.elementPresent("footer_panel.rviEdit"));
        } catch (Exception e) {
            Assert.fail("Recently Viewed panel is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that edit option inside Recently Viewed panel is displayed");
    }

    @Then("^I click remove button on any Recently viewed items$")
    public void iremoveanyRecentlyvieweditems() throws Throwable {
        List<WebElement> recentlyViewedItems = Elements.findElements("footer_panel.rviThumbnails");
        totalProductInRvi = recentlyViewedItems.size();
        Wait.untilElementPresent("footer_panel.rviEdit");
        Clicks.click(Elements.findElement("footer_panel.rviEdit"));
        Wait.untilElementPresent("footer_panel.rviRemove");
        Clicks.click(Elements.findElement("footer_panel.rviRemove"));
        DiscoveryHelper.logInfo("Selected remove button on any rvi");
    }

    @Then("^I verify that the item is removed from Recently viewed items$")
    public void ishouldseetheitemisremovedfromRecentlyvieweditems() throws Throwable {
        List<WebElement> recentlyViewedItems = Elements.findElements("footer_panel.rviThumbnails");
        Assert.assertTrue("Expected Product count should be less than: " + totalProductInRvi + " Actual displayed products: " + recentlyViewedItems.size(),
                totalProductInRvi > recentlyViewedItems.size());
        DiscoveryHelper.logInfo("Verified that the item is removed from Recently viewed items");
    }

    @And("^I verify that number of Brands displayed under brand facet should be less than or equal to (\\d+)$")
    public void IVerifyThatNumberOfBrandsDisplayedUnderBrandFacetShouldBeLessThanOrEqualTo(int count) throws Throwable {
        try {
            List<String> brandFacetItems;
            String facetName = macys() ? "Brand" : "Designer";
            if (!LeftFacet.isExpanded(facetName)) {
                LeftFacet.expandFacet(facetName);
                Wait.forPageReady();
            }
            brandFacetItems = DiscoveryHelper.getFeaturedBrandNames();
            int facet_value_count = brandFacetItems.size();
            Assert.assertTrue("Expected count should be displayed less than: " + count + " Actual count displayed: " + facet_value_count,
                    facet_value_count <= count);
        } catch (Exception e) {
            Assert.fail("Number of brands under brand facet is more than 10" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that number of Brands displayed under brand facet should be less than or equal to " + count);
    }

    @And("^I verify that search box is not displayed under Brand facet$")
    public void iVerifyThatSeeSearchBoxIsNotDisplayedUnderBrandFacet() throws Throwable {
        try {
            Assert.assertFalse("Search box is not displayed", Elements.elementPresent("left_facet.brand_search"));
        } catch (Exception e) {
            Assert.fail("Search box is displayed under brand facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that search box is not displayed under Brand facet");
    }

    @When("^I enter \"([^\"]*)\" keyword in search field$")
    public void i_enter_keyword_in_search_field(String keyword) throws Throwable {
        try {
            TextBoxes.typeTextbox("header.search_field", keyword);
            Wait.untilElementPresent("header.suggestions_list");
        } catch (Exception e) {
            Assert.fail("Error to enter keyword in search field");
        }
        DiscoveryHelper.logInfo("Entered " + keyword + " keyword in search field");
    }

    @Then("^I verify that autocomplete suggestions (list is|list is not) populated$")
    public void I_verify_that_autocomplete_suggestions(String condition) throws Throwable {
        try {
            if (condition.equalsIgnoreCase("list is")) {
                Wait.untilElementPresent("header.suggestions_list");
                Assert.assertTrue("Autocomplete suggestions are displayed",
                        Elements.elementPresent("header.suggestions_list"));
            } else {
                Assert.assertFalse("Autocomplete suggestions are displayed",
                        Elements.elementPresent("header.suggestions"));
            }
        } catch (Exception e) {
            Assert.fail("Error to verify autocomplete suggestions list is populated");
        }
        DiscoveryHelper.logInfo("Verified the autocomplete suggestions " + condition + " populated");
    }

    @Then("^I verify that (\\d+) words or phrases Pertinent to the characters typed$")
    public void i_should_see_words_or_phrases_Pertinent_to_the_characters_typed(int value) throws Throwable {
        int suggestionsCount;
        suggestionsCount = DiscoveryHelper.getAutoSuggestionList().size();
        Assert.assertTrue("Auto suggestions exceeds " + value, suggestionsCount == value);
        DiscoveryHelper.logInfo("Verified that" + value + "words or phrases Pertinent to the characters typed ");
    }

    @Then("^I verify that characters of suggestions not more than (\\d+) characters$")
    public void I_should_see_the_characters_of_suggestions_not_more_than_characters(int value) throws Throwable {
        List<String> suggestionText = DiscoveryHelper.getAutoSuggestionList();
        for (String text : suggestionText) {
            Assert.assertTrue("suggestions should not contains more than " + value + " characters",
                    text.length() > value);
        }
        DiscoveryHelper.logInfo("Verified that the characters of suggestions not more than " + value + " characters");
    }

    @Then("^I verify that first letter of each word in autocomplete suggestions should be (capitalized|lower case)$")
    public void the_first_letter_of_each_word_in_autocomplete_suggestions_should_be_capitalized(String condition) throws Throwable {
        List<String> suggestion = DiscoveryHelper.getAutoSuggestionList();
        if (condition.equals("capitalized")) {
            suggestion.stream().filter(text -> !Character.isUpperCase(text.charAt(0)))
                    .forEach(text -> Assert.fail("First character is not uppercase"));
        } else {
            Arrays.stream(suggestion.toString().split("\n")).filter(text -> Character.isUpperCase(text.charAt(0)))
                    .forEach(text -> Assert.fail("First character is not lowercase"));
        }
        DiscoveryHelper.logInfo("Verified the first letter of each word in autocomplete suggestions should be " + condition);
    }

    @When("^I enter \"([^\"]*)\" keyword in search field and get the (first|second) suggestion lists$")
    public void i_enter_keyword_in_search_field_and_get_the_second_suggestion_lists(String keyword, String count) throws Throwable {
        i_enter_keyword_in_search_field(keyword);
        Wait.forPageReady();
        if (count.equals("second")) secondSuggestionsList = DiscoveryHelper.getAutoSuggestionList();
        else firstSuggestionsList = DiscoveryHelper.getAutoSuggestionList();
        DiscoveryHelper.logInfo("Getting the first and second list of suggestions");
    }

    @When("^I remove text from the search box$")
    public void I_clear_the_search_text_area() throws Throwable {
        Elements.findElement("header.search_field").clear();
        DiscoveryHelper.logInfo("Cleared text in search field");
    }

    @Then("^I verify that both first and second autocomplete suggestions are same$")
    public void I_should_see_both_autocomplete_suggestions_as_same() throws Throwable {
        Assert.assertEquals("Both the suggestions should be same", firstSuggestionsList, secondSuggestionsList);
        DiscoveryHelper.logInfo("Verified the first and second suggestion list are same");
    }

    @Then("^I verify that suggestions are not displayed jibberish$")
    public void I_should_not_see_suggestions_displayed_jibberish() throws Throwable {
        List<String> suggestionText = DiscoveryHelper.getAutoSuggestionList();
        suggestionText.stream().filter(text -> text.contains("<"))
                .forEach(text -> Assert.fail("Auto-suggestions should not contain jibberish character"));
        DiscoveryHelper.logInfo("Verified the suggestions are not displayed in jibberish");
    }

    @Then("^I verify that autocomplete suggestions displayed with letters that do not match the user typed term in bold$")
    public void The_autocomplete_suggestions_should_display_the_letters_that_do_not_match_the_user_typed_term_in_bold() throws Throwable {
        try {
            List<WebElement> highlightedText = Elements.findElements("header.highlighted_keywords");
            highlightedText.stream().filter(element -> !(Integer.parseInt(element.getCssValue("font-weight")) >= 700))
                    .forEach(element -> Assert.fail("Highlighted words should not contains typed texts"));
        } catch (Exception e) {
            Assert.fail("Error to verify suggestions displayed with letters that do not match the user typed term in bold ");
        }
        DiscoveryHelper.logInfo("Verified the autocomplete suggestions with the typed terms are not matching");
    }

    @When("^I collect total brands under brand facet$")
    public void iCollectTotalBrandsUnderBrandFacet() throws Throwable {
        String facetName = macys() ? "Brand" : "Designer";
        Navigate.browserRefresh();
        if (!LeftFacet.isExpanded(facetName)) {
            LeftFacet.expandFacet(facetName);
            Wait.forPageReady();
        }
        DiscoveryHelper.getAllBrandFacetName();
        DiscoveryHelper.logInfo("Collected total brands under brnad facet");
    }

    @Then("^I verify that the brands are (displayed|not displayed) under Featured Brands and All Brands$")
    public void iTheBrandsAreDisplayedUnderFeaturedBrandsAndAllBrands(String condition) throws Throwable {
        if (condition.equals("displayed")) {
            Assert.assertTrue("Brands are not displayed under All Brands",
                    DiscoveryHelper.getAllBrandNames().size() > 0);
            Assert.assertTrue("Brands are not displayed under Featured Brands",
                    DiscoveryHelper.getFeaturedBrandNames().size() > 0);
        } else {
            Assert.assertTrue("Brands are displayed under Featured Brands and All Brands",
                    (Elements.findElements(By.className("filter-match")).isEmpty()));
        }
        DiscoveryHelper.logInfo("Verified that brands are " + condition + " under Featured Brand and All Brands");
    }

    @And("^I verify that search filter is not applied$")
    public void theSearchFilterShouldNotBeApplied() throws Throwable {
        Assert.assertFalse("Search filter is applied under brand facet",
                (Elements.findElement(LeftFacet.getFacetDiv("Brand")).getAttribute("class").contains("filter-highlight")));
        DiscoveryHelper.logInfo("Verified that search filter is not applied");
    }

    @When("^I append first character in search box with other character\\(s\\) \"([^\"]*)\" under brand facet$")
    public void iAppendFirstCharacterInSearchBoxWithOtherCharacterSUnderBrandFacet(String searchTerm) throws Throwable {
        try {
            Wait.untilElementPresent("search_result.brand_search");
            Elements.findElement("search_result.brand_search").sendKeys(searchTerm);
            Wait.forPageReady();
        } catch (Exception e) {
            Assert.fail("Unable to append character in search box");
        }
        DiscoveryHelper.logInfo("Append first character in search box with other character " + searchTerm + " under brand facet");
    }

    @And("^I verify that default state of (All|Featured) Brands is (collapsed|expanded)$")
    public void iVerifyThatDefaultStateOfAllBrandsIsCollapsed(String brandName, String condition) throws Throwable {
        try {
            String facetName = macys() ? "Brand" : "Designer";
            if (!LeftFacet.isExpanded(facetName)) {
                LeftFacet.expandFacet(facetName);
                Wait.forPageReady();
            }
            if (brandName.equals("All"))
                Assert.assertTrue(brandName + "brands is not" + condition,
                        DiscoveryHelper.isAllBrandsCollapsed());
            else
                Assert.assertFalse(brandName + "brands is not" + condition,
                        DiscoveryHelper.isFeaturedBrandsCollapsed());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^I verify that brand facets is displayed according to the text contains \"([^\"]*)\" entered in brand search box$")
    public void iShouldSeeBrandFacetsDisplayedAccordingToTheTextContainsEnteredInBrandSearchBox(String searchTerm) throws Throwable {
        String facetName = macys() ? "Brand" : "Designer";
        List<String> allFacetValues = DiscoveryHelper.getAllFacetValues(facetName);
        for (String facetValue : allFacetValues) {
            Assert.assertTrue("Facet value is not displayed with the given search string Expected search string: " + searchTerm + " should be displayed in "
                    + facetValue, facetValue.contains(searchTerm));
        }
        DiscoveryHelper.logInfo("Verified that brand facets is displayed according to the text contains " + searchTerm + " entered in brand search box");
    }

    @And("^I verify that characters \"([^\"]*)\" is highlighted in the displayed brands under brand facet$")
    public void IVerifyThatCharactersIsHighlightedInTheDisplayedBrandsUnderBrandFacet(String searchTerm) throws Throwable {
        try {
            List<WebElement> filterMatch = Elements.findElements(By.className("filter-match"));
            for (WebElement e : filterMatch) {
                Assert.assertTrue("Search filter\t" + searchTerm + "\tis not applied under brand facet",
                        (e.getText().contains(searchTerm)));
            }
        } catch (Exception e) {
            Assert.fail("Unable to verify that characters are highlighted in the displayed brand under brand facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that characters " + searchTerm + " is highlighted in the displayed brands under brand facet");
    }

    @And("^I verify that search box should contain character\\(s\\) \"([^\"]*)\" under brand facet$")
    public void theSearchBoxShouldContainCharacterSUnderBrandFacet(String searchTerm) throws Throwable {
        try {
            Assert.assertTrue("Search box does not contain \t" + searchTerm + "\tunder brand facet",
                    (Elements.findElement("search_result.brand_search").getAttribute("value").contains(searchTerm)));
        } catch (Exception e) {
            Assert.fail("Unable to verify that search box is displayed with the given character under brand facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the search box should contain character " + searchTerm + " under brand facet");
    }

    @When("^I select \"([^\"]*)\" button on left of \"([^\"]*)\"$")
    public void optionIsISelectTheButtonPlacedToItsLeft(String brandState, String brandName) throws Throwable {
        if (brandName.equals("Featured Brands"))
            DiscoveryHelper.expandCollapseFeaturedBrand();
        else
            DiscoveryHelper.expandCollapseAllBrand();
        DiscoveryHelper.logInfo("Selected " + brandState + " button placed on left of " + brandName);
    }

    @Then("^I verify that \"([^\"]*)\" got \"([^\"]*)\"$")
    public void iVerifyThatGot(String brandName, String brandState) throws Throwable {
        switch (brandState) {
            case "expanded":
                if (brandName.equals("Featured Brands"))
                    Assert.assertFalse(brandName + "is not " + brandState, DiscoveryHelper.isFeaturedBrandsCollapsed());
                else
                    Assert.assertFalse(brandName + "is not " + brandState, DiscoveryHelper.isAllBrandsCollapsed());
                break;
            case "collapsed":
                if (brandName.equals("Featured Brands"))
                    Assert.assertTrue(brandName + "is not " + brandState, DiscoveryHelper.isFeaturedBrandsCollapsed());
                else
                    Assert.assertTrue(brandName + "is not " + brandState, DiscoveryHelper.isAllBrandsCollapsed());
                break;
        }
        DiscoveryHelper.logInfo("Verified that " + brandName + " got " + brandState);
    }

    @And("^I verify that brands are duplicated within Featured Brands$")
    public void iShouldSeeBrandsAreDuplicatedWithinFeaturedBrands() throws Throwable {
        List<String> facetNames, featuredBrandFacetItems, allBrandFacetItems;
        featuredBrandFacetItems = DiscoveryHelper.getFeaturedBrandNames();
        allBrandFacetItems = DiscoveryHelper.getAllBrandNames();
        facetNames = ListUtils.intersection(allBrandFacetItems, featuredBrandFacetItems);
        Assert.assertTrue("All brands are not duplicated within Featured brand", facetNames.size() == 10);
        DiscoveryHelper.logInfo("Verified that brands are duplicated within Featured Brands");
    }

    @And("^I verify that sequencing of all brand values under All Brands is alpha numeric$")
    public void iShouldSeeSequencingOfAllBrandValuesUnderAllBrandsIsAlphaNumeric() throws Throwable {
        List<String> allBrandFacetItems;
        allBrandFacetItems = DiscoveryHelper.getAllBrandNames();
        Collections.sort(allBrandFacetItems.stream().map(String::toLowerCase).collect(Collectors.toList()));
        Assert.assertEquals("Brand are not sorted Alpha numeric", allBrandFacetItems, DiscoveryHelper.getAllBrandNames());
        DiscoveryHelper.logInfo("Verified that sequencing of all brand values under All Brands is alpha numeric");
    }

    @Then("^I verify that search box is displayed under Brand facet$")
    public void iShouldSeeSearchBoxUnderBrandFacet() throws Throwable {
        try {
            String facetName = macys() ? "Brand" : "Designer";
            if (!LeftFacet.isExpanded(facetName)) {
                LeftFacet.expandFacet(facetName);
                Wait.forPageReady();
            }
            Assert.assertTrue("Search box is not displayed under brand facet",
                    (Elements.elementPresent("search_result.brand_search")));
        } catch (Exception e) {
            Assert.fail("Error verifying search box under brand facet " + e.getMessage());
        }
    }


    @Then("^I verify that the search results also list special characters$")
    public void iVerifyThatTheSearchResultsAlsoListSpecialCharacters() throws Throwable {
        List<Character> specialCharacters = Arrays.asList('é', 'è', 'ê', 'ñ', 'ó', 'ô', '²', 'ä', 'É', 'Ü', 'ü');
        List<String> allBrands = new ArrayList<>();
        allBrands.addAll(DiscoveryHelper.getAllBrandNames());
        allBrands.addAll(DiscoveryHelper.getFeaturedBrandNames());
        for (String brand : allBrands) {
            for (Character specialCharacter : specialCharacters) {
                int index = brand.indexOf(specialCharacter);
                /*If the given char is present in string, it returns the index(>=0). If not, it returns -1.
                 So, a non-negative return value means that given char is present in the string.*/
                if (index >= 0)
                    DiscoveryHelper.logInfo("Special Characters are listed in facets for selected character");
                else
                    DiscoveryHelper.logInfo("Special Characters are not listed in facets for selected character");
            }
        }
    }

    @Then("^I verify that \"([^\"]*)\" is displayed in the radius dropdown under bops facet$")
    public void iVerifyMilesRadiusDropDown(String miles) throws Throwable {
        try {
            LeftFacet.expandFacet("Pick Up In Store");
            String milesDropText = macys() ? DropDowns.getSelectedValue(Elements.element("left_facet.bops_store_search_radius")) :
                    Elements.findElement("bops_facet.miles_select").getText();
            Assert.assertEquals("radius dropdown under bops facet is not " + miles, milesDropText.trim(), miles);
        } catch (Exception e) {
            Assert.fail("radius dropdown under bops facet is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified radius dropdown under bops facet");
    }

    @When("^I verify that (city name|zipcode|Change Location) hyperlink is displayed under bops facet$")
    public void iVerifyCityNameHyperLinkedIsDisplayedUnderBopsFacet(String name) throws Throwable {
        LeftFacet.expandFacet("Pick Up In Store");
        Assert.assertTrue(name + " is not displayed under bops facet",
                Elements.findElement("left_facet.bops_location").getCssValue("text-decoration").equalsIgnoreCase("underline"));
        DiscoveryHelper.logInfo("Verified that " + name + " hyperlink is displayed under bops facet");
    }

    @And("^I verify that zipcode based on store cookie value under bops facet$")
    public void iShouldSeeZipcodeBasedOnStoreCookieValueUnderBopsFacet() throws Throwable {
        LeftFacet.expandFacet(selectedFacetName);
        if (macys()) Wait.untilElementPresent("left_facet.bops_location");
        String currentBopsLocation = macys() ? Elements.findElement("left_facet.bops_location").getText() :
                Elements.findElement(By.className("bopsSearchedAddress")).getText();
        Assert.assertTrue("Current Bops Location and zip code cookie value is different Expected BOPS location: "
                + zipCode + " Actual Displayed location: " + currentBopsLocation, currentBopsLocation.equals(zipCode));
        DiscoveryHelper.logInfo("Verified zipcode is displayed based on store cookie value under bops facet");
    }

    @When("^I search stores for valid (zipcode|city and state) within \"([^\"]*)\" miles in ISA overlay$")
    public void iSearchStoreForZipcodeAndCityInPickUpInStoreFacet(String searchField, String miles) throws Throwable {
        String city = macys() ? "Sacramento" : "Chicago";
        String state = macys() ? "California" : "Illinois";
        try {
            LeftFacet.expandFacet("Pick Up In Store");
            if (macys())
                TextBoxes.typeTextbox("change_pickup_store_dialog.address_zip_code",
                        searchField.contains("zipcode") ? zipCode : city);
            else {
                if (searchField.contains("zipcode"))
                    TextBoxes.typeTextNEnter("left_facet.bops_store_search_box", "10021");
                else {
                    TextBoxes.typeTextNEnter("change_pickup_store_dialog.address_city", city);
                    Clicks.click("change_pickup_store_dialog.bops_so_state");
                    List<WebElement> element = Elements.findElement("change_pickup_store_dialog.bops_so_state")
                            .findElements(By.tagName("option"))
                            .stream()
                            .filter(e -> e.getText().equalsIgnoreCase(state))
                            .collect(Collectors.toList());
                    Clicks.click(element.get(0));
                }
                Wait.forPageReady();
                Clicks.click("change_pickup_store_dialog.search_distance");
                WebElement element = Elements.findElement("change_pickup_store_dialog.search_distance")
                        .findElements(By.tagName("option"))
                        .stream()
                        .filter(e -> e.getText().equalsIgnoreCase(miles))
                        .findFirst().orElse(null);
                Clicks.click(element);
                Clicks.click("change_pickup_store_dialog.search_button");
            }
        } catch (Exception e) {
            Assert.fail("Error Zipcode search is not working in bops facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for for valid store " + searchField + " within " + miles + " miles in ISA overlay" );
    }

    @When("^I verify that Enter Zip Code by default in search box under bops facet$")
    public void iVerifyEnterZipCodeFieldUnderBopsFacet() throws Throwable {
        try {
            LeftFacet.expandFacet("Pick Up In Store");
            if (macys()) {
                Wait.untilElementPresent("left_facet.bops_store_search_box");
                Assert.assertTrue("Enter Zip Code is not default in search box under bops facet",
                        Elements.findElement("left_facet.bops_store_search_box").isDisplayed());
                Assert.assertTrue("Enter Zip Code search box under bops facet is not empty",
                        Elements.findElement("left_facet.bops_store_search_box").getText().isEmpty());
            } else {
                Wait.untilElementPresent("bops_facet.zipcode_field");
                Assert.assertTrue("Enter Zip Code is not default in search box under bops facet",
                        Elements.findElement("bops_facet.zipcode_field").getAttribute("Placeholder").equals("Enter ZIP code"));
            }
        } catch (Exception e) {
            Assert.fail("Error to verify zipcode field");
        }
        DiscoveryHelper.logInfo("Verified Enter Zip Code by default in search box under bops facet");
    }

    @When("^I search for (zipcode|city) \"([^\"]*)\" in bops facet overlay$")
    public void iSearchInvalidUnderBopsFacetOverlay(String code,String keyword) throws Throwable {
        TextBoxes.typeTextNEnter("change_pickup_store_dialog.address_zip_code", keyword);
        Clicks.click("change_pickup_store_dialog.search_button");
        Wait.forPageReady();
        DiscoveryHelper.logInfo("Search for "+ code + keyword + " in bops facet overlay");
    }

    @Then("^I verify that \"([^\"]*)\" error message is displayed under bops change store dialog$")
    public void iSelectErrorMessageUnderBopsFacet(String message) throws Throwable {
        String error = Elements.findElement(By.id("store-list")).findElement(By.className("error")).getText();
        Assert.assertTrue("Error message is not displayed",
                error.equalsIgnoreCase(message));
        DiscoveryHelper.logInfo("Verified error message "+ message + " is displayed under bops change store dialog");
    }

    @And("^I select random option in sort by drop down$")
    public void iSelectRandomOptionInSortByDropDown() throws Throwable {
        try {
            if (macys()) {
                List<String> sortByOptions = DropDowns.getAllValues("pagination.sort_by");
                Random randomGenerator = new Random();
                int randomSortIndex = randomGenerator.nextInt(sortByOptions.size() - 1);
                DiscoveryHelper.logInfo("Selected " + sortByOptions.get(randomSortIndex) + " from sort by drop down");
                DropDowns.selectByText("search_result.sort_by_select", sortByOptions.get(randomSortIndex));
            } else {
                Clicks.clickWhenPresent("search_result.sort_by_select");
                Wait.forLoading("search_result.sort_by_option");
                sortByValue = Elements.getRandomElement("search_result.sort_by_option").getText();
                Clicks.clickElementByText("search_result.sort_by_option", sortByValue);
            }
        } catch (Exception e) {
            Assert.fail("Error to select random sort by value" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected random sort by option");
    }

    @Then("^I verify that the product thumbnails are displayed with the selected brand$")
    public void iVerifyThatTheProductThumbnailsAreDisplayedWithTheSelectedBrand() throws Throwable {
        Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailsDetails();
        for (Map.Entry<String, HashMap> detail : details.entrySet()) {
            String productName = detail.getValue().get("elm_product_name").toString();
            DiscoveryHelper.logInfo("Product Name: "+ productName);
            System.out.println("Product Name: "+ productName);
            System.out.println("Selected Facet Values: "+ selectedFacetValues);
            DiscoveryHelper.logInfo("Selected Facet Values: "+ selectedFacetValues);
            List<Boolean> result = selectedFacetValues.stream()
                    .map(e->productName.toLowerCase().contains(e.toLowerCase()))
                    .collect(Collectors.toList());
            // if result does not contain true then product are not displayed with the selected brand name
            Assert.assertTrue("Products are not displayed with the selected brand, Expected Product name should contain any of the facet values: " + selectedFacetValues +
                    " Actual displayed product name " + productName, result.contains(true));
        }
        DiscoveryHelper.logInfo("Verified that the product thumbnails are displayed with the selected brand");
    }

    @When("^I verify that \"([^\"]*)\" cookie is not displayed$")
    public void VerifyCookie(String cookieValue) throws Throwable {
        try{
            Assert.assertTrue("Cookie " + cookieValue +" values is displayed ",
                    Cookies.getCookieValue(cookieValue).isEmpty());
        }catch (Exception e) {
            Assert.fail("Unable to verify "+ cookieValue+ " Cookie " +e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that " + cookieValue +" is not displayed");
    }

    @Then("^I verify that flyout menu for below fob's in fob section by mouse hover$")
    public void iMouseHoverOnFOB(List<String> fob) throws Throwable {
        List<WebElement> getCategoriesEle = Elements.findElements(Elements.element("home.category"))
                .stream()
                .filter(e->!e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        List<String> getCategories = getCategoriesEle.stream().map(WebElement::getText)
                .collect(Collectors.toList());
        Wait.forPageReady();
        for (String e : fob) {
            if (!getCategories.contains(e.toUpperCase())) Assert.fail("Mouse hover fob not available on the page");
            Clicks.hoverForSelection(By.linkText(e.toUpperCase()));


        }DiscoveryHelper.logInfo("Verified Flyout menu on search landing page");
    }

    @And("^I hover over on the below \"([^\"]*)\" fob's$")
    public void iHoverOverOnTheBelowFobS(String fob) throws Throwable {
        Clicks.hoverForSelection(By.linkText(fob.toUpperCase()));
        DiscoveryHelper.logInfo("Performed hover on the " + fob + " fob's");
    }

    @Then("^I verify that flyout menu is displayed$")
    public void iVerifyThatFlyoutMenuIsDisplayed() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Flyout menu is not displayed",
                Elements.elementPresent("header.open_flyout"));
        DiscoveryHelper.logInfo("Verified that flyout menu is displayed");
    }

    @When("^I hover on any category$")
    public void iHoverOnAnyCategory() throws Throwable {
        List<WebElement> elements = Elements.findElements(Elements.element("home.category"))
                .stream()
                .filter(e->!e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        Random randomGenerator = new Random();
        int randomFobIndex = randomGenerator.nextInt(elements.size());
        Clicks.hoverForSelection(elements.get(randomFobIndex));
        DiscoveryHelper.logInfo("Hovered on random category");
    }

    @Then("^I verify that the products are displayed with the selected rating$")
    public void iVerifyThatTheProductsAreDisplayedWithTheSelectedRating() throws Throwable {
        Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailsDetails();
        details.entrySet()
                .stream()
                .filter(detail -> !(detail.getValue().get("elm_customer_ratings_style") == null))
                .forEach(detail -> {
                    String starRating = detail.getValue().get("elm_customer_ratings_style").toString().replaceAll("\\D+", "");
                    int productRating = Integer.parseInt(starRating);
                    boolean ratingVerification = DiscoveryHelper.ratingVerification(productRating, selectedFacetValues);
                    Assert.assertTrue("Product rating does not fall under any selected rating for product name: " +
                            detail.getValue().get("elm_product_name"), ratingVerification);
                });
        DiscoveryHelper.logInfo("Verified that the products are displayed with the selected rating");
    }

    @Then("^I verify that the product rating in (quick peek|PDP)$")
    public void iVerifyThatTheProductRatingInQuickPeek(String pageType) throws Throwable {
        String starRating;
        try {
            // For New member pdp then the element for pdp star rating => "redStar",
            // other than new pdp start rating element => "rating", that was handled in catch.
            starRating = Elements.findElement("productThumbnailPanel.productStarRatingPdp").getAttribute("style").replaceAll("\\D+", "");
        }catch (Exception e){
            System.out.println("Other that New Product Display Page");
            starRating = Elements.findElement("productThumbnailPanel.masterProductReview")
                    .findElement(By.tagName("span")).getAttribute("style").replaceAll("\\D+", "");
        }
        starRating  = pageType.equals("PDP") ? starRating :
                Elements.findElement("quickViewOverlay.starRating").getAttribute("style").replaceAll("\\D+", "");
        int productRating = Integer.parseInt(starRating);
        boolean ratingVerification = DiscoveryHelper.ratingVerification(productRating, selectedFacetValues);
        Assert.assertTrue("Product rating does not fall under any selected rating for product name: " +
                selectedProdId, ratingVerification);
        DiscoveryHelper.logInfo("Verified that product rating in " + pageType);
    }

    @And("^I select x icon on bops change store dialog$")
    public void iSelectXIconOnBopsChangeStoreDialog() throws Throwable {
        Clicks.clickIfPresent("change_pickup_store_dialog.close");
        DiscoveryHelper.logInfo("Selected x icon on bops change store overlay");
    }

    @Then("I verify that item count buttons in page$")
    public void iVerifyThatitemcountbuttonsinpage() throws Throwable {
        if(!(Elements.findElement("pagination.item_count_section").getText().contains("Show"))) {
            Assert.fail("Item count buttons section not available");
        }
    }

    @When("^I navigate to next page of thumbnail grid$")
    public void Inavigatetonextpageofthumbnailgrid() throws Throwable {
        Wait.forPageReady();
        Clicks.click("pagination.goto_next_page_via_arrow");
        Wait.forPageReady();
    }

    @Then("^I verify that pagination works$")
    public void iVerifyThatPaginationWorks() throws Throwable {
        iSelectRandomPageNumberFromPagination();
        Assert.assertTrue(DiscoveryHelper.getProductIds().size() > 0);
        DiscoveryHelper.logInfo("Verified that pagination works");
    }

    @Then("^I verify that pagination is (not displayed|displayed)$")
    public void i_verify_that_pagination_is_not_displayed(String condition) throws Throwable {
        try {
            if (condition.equals("displayed"))
                Assert.assertTrue("Pagination is not displayed ", DiscoveryHelper.paginationAvailable());
            else
                Assert.assertFalse("Pagination is displayed ", DiscoveryHelper.paginationAvailable());

        } catch (Exception e) {
            Assert.fail("Pagination is available" + e.getMessage());
        }
        DiscoveryHelper.logInfo("No pagination is available");
    }

    @Given("^I click (\\d+) pagination number$")
    public void i_click_pagination_number(int pageNumber) throws Throwable {
        System.out.println(pageNumber);
        DiscoveryHelper.goToPageNumber(pageNumber);
        DiscoveryHelper.logInfo("Navigated to page " + pageNumber);
    }

    @When("^I click on next pagination button$")
    public void i_click_on_next_pagination_button() throws Throwable {
        if (Elements.elementPresent("pagination.goto_next_page_via_arrow")) {
            Clicks.click("pagination.goto_next_page_via_arrow");
        } else {
            Assert.fail("Next pagination button is not displayed");
        }
        DiscoveryHelper.logInfo("Successfully click on Next pagination button");
    }

    @When("^I click on previous pagination button$")
    public void i_click_on_previous_pagination_button() throws Throwable {
        if (Elements.elementPresent("pagination.goto_previous_page_via_arrow")) {
            Clicks.click("pagination.goto_previous_page_via_arrow");
        } else {
            Assert.fail("Previous pagination button is not displayed");
        }
        Wait.untilElementNotPresent("product_thumbnails.loading_div");
        DiscoveryHelper.logInfo("Successfully click on Previous pagination button");
    }

    @And("^I should be in Registry mode$")
    public void and_i_should_be_in_Registry_mode() throws Throwable {
        try {
            Assert.assertTrue("Verified User should be landing on Registered page", Elements.elementPresent("registry_home.verify_page"));
        } catch (Exception e) {
            Assert.fail("Verified User is not landing on Registered page" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that facet section displaying with 'filter by' header text");
    }

    @And("^I verify that all the product thumbnails displayed properly on the Search Landing page$")
    public void iVeriftyThatAllTheProductThumbnailsDisplayedProperlyOnTheSearchLandingPage() throws Throwable {
        int productCount = DiscoveryHelper.getProductIds().size();
        System.out.println(productCount);
        if (productCount>0) {
            if (productCount <= 60 || productCount <= 40) {
                DiscoveryHelper.logInfo("Product displayed properly");
            } else {
                Assert.fail("Product displayed greater than 40 or 60");
            }
        }
        else {
            Assert.fail("No product is displayed");
        }
        DiscoveryHelper.logInfo("Product thumbnails displayed properly");
    }

    @And("^I navigate to PDP of the first product$")
    public void iNavigateToPDPOfTheFirstProduct() throws Throwable {

        Wait.untilElementNotPresent("product_thumbnails.loading_div");
        String prodId1 = DiscoveryHelper.getProductIds().get(0);
        Clicks.click(prodId1);

    }

    @Then("^I verify that \"([^\"]*)\" item count option and respective number of products on page$")
    public void iVerifyThatItemCountOptionAndRespectiveNumberOfProductsOnPage(String itemCount) throws Throwable {
        try{
            int thumbnailCount;
            thumbnailCount = DiscoveryHelper.getProductIds().size();
            if(itemCount.equals("All")) {
                for(int i = 0; i <= 5; i++) {
                    Navigate.scrollPage(0, 800);
                }
                Wait.forPageReady();
                Assert.assertTrue("Show item is not selected as All", thumbnailCount > 60);
            }else{
                Assert.assertTrue("Show item is not selected as as per the item count" + itemCount,
                        Integer.parseInt(itemCount) == thumbnailCount);
            }
        }catch(Exception e){
            Assert.fail("Unable to verify item count option and respective number of products on page"+e.getMessage());
        }
    }

    @And("^I verify that search result page Facets displayed match with production$")
    public void iVerifySearchResultPageFacetsDisplayedMatchWithProduction() throws Throwable {
        String prodUrl;
        ShopAndBrowse browse = new ShopAndBrowse();
        PageNavigation pageNavigation = new PageNavigation();
        List<String> facetNames = DiscoveryHelper.getAllFacetName();
        String url = WebDriverManager.getCurrentUrl();
        if (macys())
            prodUrl = url.replace(url.split(".com/")[0], "http://www.macys");
        else
            prodUrl = url.replace(url.split(".com/")[0], "http://www.bloomingdales");
        DiscoveryHelper.logInfo("Navigated to production Site");
        Navigate.visit(prodUrl);
        if (shoppingMode.equalsIgnoreCase("iship")) {
            pageNavigation.I_navigate_to_international_context_page();
            browse.I_change_country_to(ISHIPCountry);
            Navigate.visit(prodUrl);
        }
        List<String> prodFacetNames = DiscoveryHelper.getAllFacetName();
        Assert.assertTrue("Facets displayed are not matching", facetNames.equals(prodFacetNames));
    }

    @And("^I verify that prices ,ratings & reviews are displayed under product thumbnail$")
    public void iVerifyThatPricesRatingsReviewsUnderProductThumbnail() throws Throwable {
        try{
            Map<String, HashMap> prodDetails = DiscoveryHelper.getProductThumbnailsDetails();
            prodDetails.entrySet()
                    .stream()
                    .filter(detail -> !(detail.getValue().get("elm_customer_ratings_style") == null))
                    .forEach(detail -> {
                        String ratings = detail.getValue().get("elm_customer_ratings_style").toString().replaceAll("\\D+", "");
                        String priceEle = (String) detail.getValue().get("elm_prices");
                        String productName = (String) detail.getValue().get("elm_product_name");
                        Assert.assertTrue("Eror verifying product prices,ratings and reviews",(!productName.isEmpty()));
                        DiscoveryHelper.logInfo("Product name" +productName+ " with prices" +priceEle+ "and ratings" +ratings+ "in search results page");
                    });
        }catch(Exception e){
            Assert.fail("Unable to verify prices, ratings and reviews under product thumbnail in search results page"+e.getMessage());
        }

    }

    @And("^I verify that (\\d+) product thumbnail in a row$")
    public void iVerifyThatProductThumbnailInARow(int thumbnailColumnCount) throws Throwable {
        try{
            int ThumbnailColumns = ProductThumbnail.productThumbnailColumns();
            if(thumbnailColumnCount == 4){
                Assert.assertTrue("Current page is not filtered as per the selected grid value"+thumbnailColumnCount, thumbnailColumnCount == ThumbnailColumns);
            }
        }catch(Exception e){
            Assert.fail("Unable to verify product thumbnail in a row"+e.getMessage());
        }
    }

    @Then ("^I verify that navigated to page (\\d+) on search result page$")
    public void iVerifyThatNavigatedToPageOnSearchResultPage(int pageNumber) throws Throwable {
        try {
            Wait.untilElementNotPresent("product_thumbnails.loading_div");
            int currentPage = DiscoveryHelper.getCurrentPageNumber();
            DiscoveryHelper.logInfo("Current page number" + currentPage);
            DiscoveryHelper.logInfo("Expected page number" + pageNumber);
            if (!(currentPage == pageNumber)) {
                Assert.fail("Expected page number is not displayed, Expected: "
                        + currentPage + " Actual: " + pageNumber);
            }
        } catch (Exception e) {
            Assert.fail("Expected page number is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Successfully landed on expected page");
    }

    @Then("^I verify that back to top button (is not displayed|is displayed) on page$")
    public void iVerifyThatBackToTopButtonIsNotDisplayedOnPage(String condition) throws Throwable {
        if (condition.equals("is not displayed"))
            Assert.assertFalse("back to top button is displayed", Elements.elementPresent("product_thumbnails.back_to_top"));
        else
            Assert.assertTrue("back to top button is not displayed", Elements.elementPresent("product_thumbnails.back_to_top"));
        DiscoveryHelper.logInfo("back to top button" + condition + "on page");
    }

    @When("^I navigate to bottom of left navigation panel$")
    public void iNavigateToBottomOfLeftNavigationPanel() throws Throwable {
        int bottom = Integer.parseInt(Elements.findElement(By.id("navigation")).getCssValue("height").split("\\.")[0]);
        Navigate.scrollPage(0, bottom);
        DiscoveryHelper.logInfo("Successfully navigated to bottom of the left navigation panel");
    }

    @And("^I verify that message \"([^\"]*)\" in search landing page header$")
    public void iverifythatmessageInSearchLandingPageHeader(String expectedSearchText) throws Throwable {
        productCount = DiscoveryHelper.getProductCount();
        String resultText = macys() ? Elements.findElement(Elements.element("search_result.product_count_breadcrumb")).getText().split("\n")[0] :
                Elements.findElement(Elements.element("search_result.search_result_text")).getText();
        expectedSearchText = expectedSearchText.replace("'n'" , String.valueOf(productCount));
        System.out.println("resultText : " + resultText);
        System.out.println("expectedSearchText : " + expectedSearchText);
        Assert.assertTrue("Expected text: " + expectedSearchText + " Actual text displayed: " + resultText,
                expectedSearchText.matches(resultText));
    }

    @And("^I verify that search message \"([^\"]*)\" is displayed$")
    public void iVerifyThatSearchMessage(String expectedSearchText) throws Throwable {
        String resultText = macys() ? Elements.findElement(Elements.element("search_result.product_count_breadcrumb")).getText().split("\n")[1] :
                Elements.findElement(Elements.element("search_result.search_match_result")).getText().replace("\n"," ");
        Assert.assertTrue("Expected text: " + expectedSearchText + " Actual text displayed: " + resultText,
                resultText.contains(expectedSearchText));
        DiscoveryHelper.logInfo("Expected search message exactly matched");
    }

    @When("^I select any search term link$")
    public void iSelectAnySearchTermLink() throws Throwable {
        List<WebElement> elements = Elements.findElement(
                Elements.element("search_result.search_breadcrumbtext_link")).findElements(By.tagName("a"));
        Random r = new Random();
        int elementToSelectIndex = r.nextInt(elements.size());
        Clicks.click(elements.get(elementToSelectIndex));
        DiscoveryHelper.logInfo("Successfully select the search term from breadcrumb link");
    }

    @And("^I verify that quotations are not displayed for search keyword in search message$")
    public void iVerifyThatQuotationsAreNotDisplayedForSearchKeywordInSearchMessage() throws Throwable {
        String resultText = Elements.findElement(Elements.element("search_result.product_count_breadcrumb")).getText();
        Assert.assertFalse("Expected quotations should not display on search message but Actual message displayed " + resultText
                 , resultText.contains("\""));
        DiscoveryHelper.logInfo("Verified that quotations are not displayed for search keyword in search message");
    }

    @Then ("^I verify that Zero Search Result page is displayed$")
    public void iVerifyThatZeroSearchResultPageIsDisplayed() throws Throwable {
        Assert.assertTrue("ERROR - APP: Not on zero Search results page",
                productCount == 0);
        DiscoveryHelper.logInfo("Verified that Zero Search results page is displayed");
    }

    @Then("^I verify that the search results should be in zero results page$")
    public void iVerifyTheSearchResultsShouldBeInZeroResultsPage() {
        try {
            String p = Elements.findElement(By.id("productCount")).getText();
            Assert.assertTrue("Page is showing products in zero results page",
                    p.equals("0"));
        } catch (Exception a) {
            Assert.fail(a.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the search results should be in zero results page");
    }

    @Then("^I verify that the message \"([^\"]*)\" in zero results page header$")
    public void iVerifyThatTheMessageInZeroResultsPageHeader(String searchText) throws Throwable {
        try{
            String message = Elements.findElement(By.id("weFoundMsg")).getText();
            Assert.assertEquals("Product listing page is not displayed", message,
                    searchText);
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the message " +searchText + " is displayed in zero results header" );
    }

    @Then("^I verify that the message \"([^\"]*)\" in zero results page$")
    public void iVerifyTheMessageInZeroResultsPage(String searchText){
        try{
            String display = Elements.findElement(Elements.element("Sample_page.Invalid_Search")).getText();
            Assert.assertTrue("Not in zero results page", searchText.contains(display));
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
        DiscoveryHelper.logInfo("Zero search results is verified");
    }

    @Then("^I verify that selected facet value in FORWARDPAGE_KEY cookie$")
    public void iVerifyThatSelectedFacetValueInFORWARDPAGE_KEYCookie() throws Throwable {
        Wait.untilElementPresent("leftFacetNavigation_panel.facetBreadcrumbs");
        String cookieValue = Cookies.getCookieValue("FORWARDPAGE_KEY");
        Assert.assertFalse("Cookie FORWARDPAGE_KEY value not present in site", cookieValue.isEmpty());
        Wait.forLoading("left_facet.loading");
        String url = PageNavigation.url();
        url = UrlUtils.decode(url);
        cookieValue = UrlUtils.encodeAnchor(cookieValue);
        Assert.assertTrue("FORWARDPAGE_KEY cookie value is not updated with selected facet Expected Cookie value: " + cookieValue + " in url: " + url ,
                url.contains(cookieValue));
        DiscoveryHelper.logInfo("Successfully verified FORWARDPAGE_KEY cookie value");
    }

    @When("^I select Sign In link from header and sign in from the current page$")
    public void iSelectSignInAndSignInToTheCurrentPage() throws Throwable {
        String url = PageNavigation.url();
        CommonUtils.signInOrCreateAccount();
        // After sign in navigating back to search or browse page
        Navigate.visit(url);
        shouldBeOnPage("search_result");
        DiscoveryHelper.logInfo("Successfully signed from current page");
    }

    @When("^I select (\\d+) facet value\\(s\\) from 'any' facet sections$")
    public void iSelectFacetValueSFromAnyFacetSections(int count) throws Throwable {
        selectedFacetValues = new ArrayList<>();
        try {
            productCount = DiscoveryHelper.getProductCount();
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            if (macys()) {
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
                thumbnailColumns = ProductThumbnail.productThumbnailColumns();
            }
            for (int i = 0; i < count; i++) {
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
                String facetName = DiscoveryHelper.getRandomFacetName();
                selectedFacetNames.add(facetName);
                System.out.println("FACET NAME " + facetName);
                DiscoveryHelper.logInfo("FACET NAME " + facetName);
                if (i > 0) {
                    Navigate.browserRefresh();
                }
                String facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
                System.out.println("FACET VALUE " + facetValue);
                DiscoveryHelper.logInfo("FACET VALUE " + facetValue);
                LeftFacet.selectItemFromFacet(facetValue, facetName);
                selectedFacetValues.add(facetValue);
                strUtils = WebDriverManager.getCurrentUrl();
            }
        } catch (Exception e) {
            Assert.fail("Unable to select multiple facet value from any facet sections " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected " + count + " facet value from 'any' facet sections");
    }

    @And("^I deselect (\\d+) facet value\\(s\\)$")
    public void iDeselectTheFacetValueS(int count) throws Throwable {
        try {
            Wait.forLoading("left_facet.loading");
            int size = Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").size();
            Assert.assertTrue("Only " + size + " facets are selected from left nav",  count <= size);
            for (int i = 0; i < count; i++) {
                WebElement facetItemToRemove = Elements.findElements(Elements.element("leftFacetNavigation_panel.facetBreadcrumbs")).get(i);
                selectedFacetValues.remove(facetItemToRemove.getText().replace(", ", "\n"));
                Clicks.click(Elements.findElements("leftFacetNavigation_panel.facetRemoveBreadcrumb").get(i));
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
            }
        } catch (Exception e) {
            Assert.fail("Unable to remove facet from breadcrumb");
        }
        DiscoveryHelper.logInfo("Removed " + count + " facet values");
    }

    @When("^I click on \"([^\"]*)\" facet header on left nav$")
    public void iClickOnFacetOnLeftNav(String facetName) throws Throwable {
        if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
            Clicks.javascriptClick(LeftFacet.getHeader(facetName));
        }
        DiscoveryHelper.logInfo("Clicked on " + facetName + " facet header on left nav");
    }

    @Then("^I verify that new size facet family is displayed$")
    public void iVerifyThatNewSizeFacetFamilyIsDisplayed() throws Throwable {
        Assert.assertFalse("New size facet family is not displayed" ,
                DiscoveryHelper.getAllSizeFacetHeaders().size() == 0);
        DiscoveryHelper.logInfo("Verified that new size facet family is displayed");
    }


    @When("^I select a random facet sub header$")
    public void iSelectARandomFacetHeader() throws Throwable {
        List<String> subFacetHeaders = DiscoveryHelper.getAllSizeFacetHeaders();
        Random randomGenerator = new Random();
        int randomSubFacetIndex = randomGenerator.nextInt(subFacetHeaders.size());
        selectedFacetName = subFacetHeaders.get(randomSubFacetIndex);
        DiscoveryHelper.logInfo("Selected Size facet Sub Header: " + selectedFacetName);
        DiscoveryHelper.expandCollapseSizeFacetSubHeader(selectedFacetName);
        DiscoveryHelper.logInfo("Selected a random facet sub header");
    }

    @Then("^I verify that facet sub header gets \"([^\"]*)\"$")
    public void iVerifyThatSubHeaderGets(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("expanded")) {
            Assert.assertFalse(selectedFacetName + "sub header is not expanded" ,
                    DiscoveryHelper.isSizeFacetSubHeaderCollapsed(selectedFacetName));
        } else {
            Assert.assertTrue(selectedFacetName + "sub header is not expanded" ,
                    DiscoveryHelper.isSizeFacetSubHeaderCollapsed(selectedFacetName));
        }
        DiscoveryHelper.logInfo("Verified that selected sub facet header is " + condition);
    }

    @And("^I verify that the facet values are (displayed|not displayed)$")
    public void iVerifyThatTheFacetItemsAreDisplayed(String condition) throws Throwable {
        if (condition.equals("not displayed")) {
            Assert.assertFalse("Facet values are displayed under " + selectedFacetName + " sub facet header",
                    DiscoveryHelper.isSizeFacetValuesDisplayed(selectedFacetName));
        } else {
            Assert.assertTrue("Facet values are not displayed under " + selectedFacetName + " sub facet header",
                    DiscoveryHelper.isSizeFacetValuesDisplayed(selectedFacetName));
        }
        DiscoveryHelper.logInfo("Verified that facet values are " + condition);
    }

    @When("^I select already selected facet sub header$")
    public void iSelectAlreadySelectedFacetSubHeader() throws Throwable {
        DiscoveryHelper.expandCollapseSizeFacetSubHeader(selectedFacetName);
        DiscoveryHelper.logInfo("Selected already selected facet sub header" + selectedFacetName);
    }

    @And("^I verify that clear all button (is|is not) displayed$")
    public void iVerifyThatClearAllButtonIsDisplayed(String buttonDisplay) throws Throwable {
        if(buttonDisplay.equals("is"))
            Assert.assertTrue("Clear All button is not displayed",
                    helper.displayClearAllButton());
        else
            Assert.assertFalse("Clear All button is not displayed",
                    helper.displayClearAllButton());


        DiscoveryHelper.logInfo("Verified clear all button " + buttonDisplay +" displayed");
    }

    @Then("^I verify that selected facet gets deselected$")
    public void iVerifyThatSelectedFacetGetsDeselected() throws Throwable {
        try {
            Assert.assertTrue("Selected facet and displayed breadcrumb varies",
                    DiscoveryHelper.breadCrumbVerification(selectedFacetValues));
        } catch (Exception e) {
            Assert.fail("Previously selected facets persists in breadcrumb" + e);
        }
        DiscoveryHelper.logInfo("Verified that selected facet gets deselected");
    }

    @Then("^I verify that product count from JSON response against UI are same$")
    public void iVerifyThatProductCountFromJSONResponseAgainstUIAreSame() throws Throwable {
        String facetLiName = DiscoveryHelper.getSelectedFacetName(selectedFacetName);
        System.out.println("Selected size facet name:" + facetLiName);
        int productCountFromResponse = SearchService.getProductCountForFacet(searchKeyword, facetLiName, selectedFacet.replace("\n",", "), "SITE", "US");
        DiscoveryHelper.logInfo("Product Count displayed from service " + productCountFromResponse + " for facet name " + selectedFacetName + " and facet value " + selectedFacet);
        int actualProductDisplayed = DiscoveryHelper.getProductCount();
        Assert.assertTrue("ERROR - APP: Expected and Displayed product count varies, Expected product count from service" +
                productCountFromResponse + " Actual Products displayed in UI: " + actualProductDisplayed, productCountFromResponse == actualProductDisplayed);
        DiscoveryHelper.logInfo("Verified that product count from JSON response against UI are same");
    }

    @And("^I should see below keys in the JSON response at (top|first|second)-level groups$")
    public void iShouldSeeBelowKeysInTheJSONResponseAtTopLevelGroups(String levelType, List<String> entries) throws Throwable {
        List<String> actualKeys = entries.stream().map(e->e.replace(" ", "")).collect(toList());
        if (levelType.equals("top")) {
            Set responseKeys = SearchService.getTopLevelResponse(searchKeyword, "SITE", "US").keySet();
            responseKeys.remove("facets");
            responseKeys.remove("values");
            Assert.assertTrue("Expected keys from Json: " + actualKeys + " Displayed keys: "+ responseKeys,
                    responseKeys.toString().equals(actualKeys.toString()));
        } else {
            List<Map> multiLevelResponse = levelType.equals("first") ? SearchService.getFirstLevelResponse(searchKeyword, "SITE", "US") :
                    SearchService.getSecondLevelResponse(searchKeyword, "SITE", "US");
            for (Map response : multiLevelResponse) {
                Set responseKeys = response.keySet();
                responseKeys.remove("facets");
                responseKeys.remove("values");
                Assert.assertTrue("Expected keys from Json: " + actualKeys + " Displayed keys: "+ responseKeys,
                        responseKeys.toString().equals(actualKeys.toString()));
            }
        }
    }

    @And("^I should see value \"([^\"]*)\" for the key \"([^\"]*)\" in the JSON response at (top|first|second)-level groups$")
    public void iShouldSeeValueForTheKeyInTheJSONResponseAtTopLevelGroups(String value, String key, String levelType) throws Throwable {
        if (levelType.equals("top")) {
            Map response = SearchService.getTopLevelResponse(searchKeyword, "SITE", "US");
            String jsonValue = response.get(key).toString();
            Assert.assertTrue("Expected value from Json: " + value + " Displayed value: "+ jsonValue,
                    value.equals(jsonValue));
        } else {
            List<Map> multiLevelResponse = levelType.equals("first") ? SearchService.getFirstLevelResponse(searchKeyword, "SITE", "US") :
                    SearchService.getSecondLevelResponse(searchKeyword, "SITE", "US");
            for (Map response : multiLevelResponse) {
                String jsonValue = response.get(key).toString();
                Assert.assertTrue("Expected value from Json: " + value + " Displayed value: "+ jsonValue,
                        value.equals(jsonValue));
            }
        }
    }

    @And("^I verify that the selected breadcrumbs not displayed in pdp$")
    public void iVerifyThatTheSelectedBreadcrumbsNotDisplayedInPdp() throws Throwable {
        Assert.assertFalse("Selected breadcrumb is displayed",
                Elements.elementPresent("leftFacetNavigation_panel.facetBreadcrumbs"));
        DiscoveryHelper.logInfo("Selected breadcrumb is not displayed in pdp");
    }

    @Then("^I verify that product (count|ids) from UI is same as given by response$")
    public void iVerifyThatProductCountFromUIIsSameAsGivenByResponse(String prodInfo) throws Throwable {
        String sortByResponseString;
        selectedSortBy = macys() ? DropDowns.getSelectedValue(Elements.element("search_result.sort_by_select")) :
                Elements.findElement("search_result.sort_by_select").getText();
        switch (selectedSortBy) {
            case "Price: Low to High":
            case "Price (low-high)":
                sortByResponseString = "PRICE_ASCENDING";
                break;
            case "Price: High to Low":
            case "Price (high-low)":
                sortByResponseString = "PRICE_DESCENDING";
                break;
            case "New Arrivals":
                sortByResponseString = "NEWNESS";
                break;
            case "Best Sellers":
                sortByResponseString = "BEST_SELLERS";
                break;
            case "Customer Top Rated":
            case "Customers' Top Rated":
                sortByResponseString = "CUSTOMER_RATING";
                break;
            default:
                sortByResponseString = "DEFAULT";
                break;
        }
        Map service = SearchService.getSearchService(searchKeyword, "SITE", "US", false, sortByResponseString);
        Map searchResponse = (Map) service.get("SearchResult");
        List<Map> searchResultGroups = (List<Map>) searchResponse.get("searchResultGroups");
        List<Double> prodIdsInDouble = (List<Double>) searchResultGroups.get(0).get("productIds");
        int totalProductCountFromResponse = Integer.parseInt(searchResultGroups.get(0).get("totalProductCount").toString().split("\\.")[0]);
        int totalProductCountFromUI = DiscoveryHelper.getProductCount();
        List<String> prodIdsFromResponse = prodIdsInDouble
                .stream()
                .map(id -> id.toString().split("\\.")[0])
                .collect(Collectors.toList());
        //Comparing first 40 product with response
        List<String> prodIdsFromUI = DiscoveryHelper.getProductIds().subList(0,40);
        if (prodInfo.equals("ids")) {
            Assert.assertTrue("ERROR - APP: Product ids from UI and Service are different, Expected Product ids: " + prodIdsFromResponse + " Actual displayed ids: " + prodIdsFromUI,
                    prodIdsFromResponse.equals(prodIdsFromUI));
        } else {
            Assert.assertTrue("ERROR - APP: Product count from UI and Service are different. Expected Count: " + totalProductCountFromResponse + " Actual product displayed: " + totalProductCountFromUI,
                    totalProductCountFromResponse == totalProductCountFromUI);
        }
    }

    @Then("^I verify that quick peek (is displayed|is not displayed)$")
    public void iVerifyThatQuickPeekIsDisplayed(String condition) throws Throwable {
        if (condition.equals("is displayed")) {
            Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            Assert.assertTrue("Quick peek is not displayed",
                    Elements.elementPresent("quick_view.quick_view_product_dialog"));
        } else {
            Assert.assertFalse("Quick peek is displayed",
                    Elements.elementPresent("quick_view.quick_view_product_dialog"));
        }
        DiscoveryHelper.logInfo("Verified that quick peek is displayed");
    }

    @And("^I verify that products are displayed with price$")
    public void iVerifyThatProductsAreDisplayedWithPrice() throws Throwable {
        Map<String, HashMap> productDetail = DiscoveryHelper.getProductThumbnailsDetails();
        for (Map.Entry<String, HashMap> detail : productDetail.entrySet()) {
            DiscoveryHelper.logInfo("Product id: " + detail.getValue().get("elm_product_id").toString() +
                    " is displayed with price " + detail.getValue().get("elm_prices").toString());
            Assert.assertFalse("Product price information is not displayed",
                    detail.getValue().get("elm_prices").toString().isEmpty());
        }
        DiscoveryHelper.logInfo("Verified that products are displayed with price");
    }

    @Then("^I verify that the product (title|image|price) appears$")
    public void iVerifyThatTheProductTitleAppears(String information) throws Throwable {
        Map<String, HashMap> productDetail = DiscoveryHelper.getProductThumbnailDetails(selectedProdId);
        for (Map.Entry<String, HashMap> detail : productDetail.entrySet()) {
            switch (information) {
                case "title":
                    Assert.assertTrue("Product title is not displayed",
                            ((WebElement) detail.getValue().get("elm_product_name")).isDisplayed());
                    break;
                case "image":
                    Assert.assertTrue("Product image is not displayed",
                            ((WebElement) detail.getValue().get("elm_image_link")).isDisplayed());
                    break;
                case "price":
                    Assert.assertTrue("Product price is not displayed",
                            ((WebElement) detail.getValue().get("elm_prices")).isDisplayed());
                    break;
            }
        }
        DiscoveryHelper.logInfo("Verified that the product " + information + " appears");
    }

    @And("^I verify that the QuickView label appears on hovering the thumbnail$")
    public void iVerifyThatTheQuickViewLabelAppearsOnHoveringTheThumbnail() throws Throwable {
        WebElement ele = DiscoveryHelper.getProductDiv(selectedProdId);
        Clicks.hoverForSelection(ele);
        Assert.assertTrue("Quick view label is not displayed after hovering on product thumbnail",
                Elements.elementPresent(By.id("quickViewLauncher")));
        DiscoveryHelper.logInfo("Verified that the QuickView label appears on hovering the thumbnail");
    }

    @When("^I select a product having (multi tier pricing|badge text)$")
    public void iSelectAProductHavingMultiTierPricing(String productType) throws Throwable {
        List<String> prodIds;
        Random randomGenerator = new Random();
        int index;
        prodIds = productType.contains("badge") ? DiscoveryHelper.getProductIdsHavingBadgeText() :
                DiscoveryHelper.getProductIdsHavingMultiLevelPricing();
        Assert.assertFalse("ERROR - DATA: No product is displayed with " + productType + " please search with different keyword",
                prodIds.size() == 0);
        index = randomGenerator.nextInt(prodIds.size());
        selectedProdId = prodIds.get(index);
        DiscoveryHelper.logInfo("Selected product " + selectedProdId + " having " + productType);
    }

    @And("^I verify that facets are listed on left nav$")
    public void iVerifyThatFacetsAreListedOnLeftNav() throws Throwable {
        Assert.assertTrue("Facets are not listed on left nav",
                Elements.elementPresent("left_facet.facets_panel"));
        DiscoveryHelper.logInfo("Verified that facets are listed on left nav");
    }

    @When("^I click on any \"([^\"]*)\" facet$")
    public void iClickOnAnyFacet(String facetState) throws Throwable {
        selectedFacetName = DiscoveryHelper.getRandomFacetName();
        if(facetState.equals("collapsed")) {
            LeftFacet.expandFacet(selectedFacetName);
        }
        else {
            LeftFacet.collapseFacet(selectedFacetName);
        }
        DiscoveryHelper.logInfo("Successfully clicked " + selectedFacetName + " facet");
    }

    @Then("^I verify that the selected facet is \"([^\"]*)\"$")
    public void iVerifyThatTheSelectedFacetIs(String facetState) throws Throwable {
        if(facetState.equals("expanded")) {
            Assert.assertTrue("Selected facet is not expanded", LeftFacet.isExpanded(selectedFacetName));
        }
        else {
            Assert.assertFalse("Selected facet is not collapsed", LeftFacet.isExpanded(selectedFacetName));
        }
        DiscoveryHelper.logInfo("Selected facet is " + facetState + " expanded properly");
    }

    @When("^I click on the same \"([^\"]*)\" facet$")
    public void iClickOnTheSameFacet(String facetState) throws Throwable {
        if(facetState.equals("expanded")) {
            LeftFacet.collapseFacet(selectedFacetName);
        }
        else {
            LeftFacet.expandFacet(selectedFacetName);
        }
        DiscoveryHelper.logInfo("Successfully clicked on the " + selectedFacetName + " facet");
    }

    @And("^I verify that the first value in price facet should (be|not be) displayed as \"([^\"]*)\"$")
    public void iVerifyThatTheFirstValueInPriceFacetShouldBeDisplayedAs(String valueDisplay, String facetValue) throws Throwable {
        String facetName = "Price";
        List<String> facetElement = DiscoveryHelper.getAllFacetValues(facetName);
        System.out.println(facetValue);
        if(facetValue.equals("Under $50"))
            Assert.assertTrue("First value is not displayed as", facetElement.get(0).equals(facetValue));
        else
            Assert.assertFalse("First value is displayed as", facetElement.get(0).equals(facetValue));
        DiscoveryHelper.logInfo("First value in price facet is" + valueDisplay);
    }

    @Then("^I verify that the \"([^\"]*)\" facet values (less|more) than (\\d+)$")
    public void i_should_see_facets_under_facet(String facetName, String facetSize, int facetValueCount) throws Throwable {
        List<String> facetValues = DiscoveryHelper.getAllFacetValues(facetName);
        if(facetSize.equalsIgnoreCase("less"))
            Assert.assertTrue("Facet values should not be greater than ", facetValues.size() <= facetValueCount);
        else
            Assert.assertTrue("Facet values should not be greater than ", facetValues.size() > facetValueCount);
        DiscoveryHelper.logInfo("Facet values should not be greater than " + facetValues.size());
    }

    @Then("^I verify that see default message \"([^\"]*)\" in \"([^\"]*)\" facet$")
    public void i_should_see_default_message_in_brand_search_box(String defaultMessage, String facetName) throws Throwable {
        Assert.assertTrue("Default message is not available",
                Elements.findElement(By.id("filter_brand")).getAttribute("placeholder").equalsIgnoreCase(defaultMessage));
        DiscoveryHelper.logInfo("Default message is not available in the text box of designer facet");
    }

    @When("^I delete all characters from designer search box$")
    public void i_delete_all_characters_from_brand_search_box() throws Throwable {
        Elements.findElement(By.id("filter_brand")).clear();
        DiscoveryHelper.logInfo("unable to clear the characters in designer serach box");
    }

    @Then("^I verify that facet values are assorted as per the text entered in brand search box$")
    public void iVerifyThatFacetValuesAreAssortedAsPerTheTextEnteredInBrandSearchBox() throws Throwable {
        String enteredText = ShopAndBrowse.brandSearch;
        List<String> DesignerFacetValues = DiscoveryHelper.getAllFacetValues("Designer");
        for (String brand : DesignerFacetValues) {
            Assert.assertTrue("Typed facet values and listed brand values are not relevant",
                    (brand.toLowerCase().contains(enteredText)));
        }
        DiscoveryHelper.logInfo("Typed facet values and listed brand values are relevant");
    }

    @When("^I select a \"([^\"]*)\" product \"([^\"]*)\" pricing of PriceTypeID = (\\d+) and PriceType = \"([^\"]*)\"$")
    public void i_select_a_product_pricing_of_PriceTypeID_and_PriceType(String product, String colorway, int priceId, String priceType) throws Throwable {

        // Loading product Data and selecting a product of given type
        loadProductData();

        selectedProdId = productData.getPriceTypesMap().get(priceType).get(colorway).get(product.toUpperCase());
        System.out.println("The Product Id selected to check for price Details is  " + selectedProdId);
        DiscoveryHelper.logInfo("The Product Id selected to check for price Details is " + selectedProdId);

    }

    private void loadProductData() throws IOException {

        // This method to facilitate data load from json
        // Data will be loaded only if productData == null for making sure one time data load

        if (productData == null) {
            String resPath = "/data/website/" + "ProductDataInfo" + ".json";
            File f = new File(RunConfig.projectResourceDirs.get(0) + resPath);
            String productTypeJson = Utils.readTextFile(f);
            Gson priceTypeMapGson = new Gson();
            productData = priceTypeMapGson.fromJson(productTypeJson, ProductData.class);
        }
    }

    @Then("^I verify that Product Thumbnail PriceTypeID = (\\d+) and PriceType = \"([^\"]*)\" is displayed$")
    public void i_verify_product_Thumbnail(int priceId,String priceType) throws Throwable {

        productThumbnails = DiscoveryHelper.getProductThumbnailDetails(selectedProdId);

        Map<String, WebElement> thumbnailWebElements = new HashMap<>();
        thumbnailWebElements = productThumbnails.get(selectedProdId);

        // Checks for all prices displayed correctly of given priceType
        DiscoveryHelper.VerifyPriceTypes(priceId,priceType,thumbnailWebElements);

        // Some priceID and PriceType are same but expects different priceTier
        // Inorder to check PriceEvent Label Trim the TYPE
        if (priceType.contains("Type")) {
            priceType = priceType.substring(0, priceType.length() - 7);
        }

        List<Integer> withPriceLabelList = new ArrayList<>();
        withPriceLabelList.addAll(Arrays.asList(17,15,18,20,21,22,23,24,25,26,19,16));

        Integer priceIdObj = priceId;
        if (withPriceLabelList.contains(priceIdObj)) {
            // Checks for all priceEventLabels displayed correctly of given priceType
            // checks only for price Types which has Price Event Labels
            DiscoveryHelper.logInfo( " Checking for Price Event Lables ");
            DiscoveryHelper.VerifyPriceEventLabels(priceType, thumbnailWebElements);
        }
        DiscoveryHelper.logInfo("Product Thumbnail for PriceType : "+ priceType + " PriceID : "+ priceId + " for the Product ID : "+selectedProdId+" Verified Successfully");
        DiscoveryHelper.logInfo("*******************************************************************************************************************************************");
        System.out.println("Product Thumbnail for PriceType : "+ priceType + " PriceID : "+ priceId + " for the Product ID : "+selectedProdId+" Verified Successfully");

    }

    @Then("^I verify that logo is displayed and returns a \"([^\"]*)\" OK$")
    public void iVerifyThatLogoIsDisplayedAndReturnsAOK(String arg0) throws Throwable {
        WebElement logo = (macys()) ? Elements.findElement("header.Header_Main_Logo") :
                Elements.findElement("header.Header_Logo");
        String src = logo.getAttribute("src");
        System.out.println(src);
        DiscoveryHelper.getResponseCode(src);
        DiscoveryHelper.logInfo("Response code is " + src);
    }

    @Then("^I verify that the (header|footer) elements are displayed$")
    public void iVerifyTheHeaderElementsAreDisplayed(String headerFooter) throws Throwable {
        if (headerFooter.equals("header")) {
            String headerEle = macys() ? "global_header" : "zeroNav";
            Assert.assertTrue(headerFooter + " elements is not displayed",
                    Elements.findElement(By.id(headerEle)).isDisplayed());
        } else {
            String footerEle = macys() ? "global_mcom_footer" : "global_footer";
            Assert.assertTrue(headerFooter + " elements is not displayed",
                    Elements.findElement("footer."+footerEle).isDisplayed());
        }
        DiscoveryHelper.logInfo("Header flag is displayed");
    }

    @Then("^I verify that the title tag of search results page$")
    public void iVerifyTheTitleTagOfSearchResultsPage(String result) throws Throwable {
        String title = WebDriverManager.getWebDriver().getTitle();
        System.out.println("title " + title);
        Assert.assertTrue(title.contains(result));
        DiscoveryHelper.logInfo("Verify that the tile tag of search result page");
    }

    @Then("^I verify that fobs are displayed and return a (\\d+) OK$")
    public void iVerifyAreDisplayedAndReturnAOK(int responseCode) throws Throwable {
        List<WebElement> fobs = Elements.findElement("productThumbnailPanel.test").findElements(By.tagName("a"));
        for (WebElement fob : fobs) {
            String responseURL = fob.getAttribute("href");
            Assert.assertTrue((DiscoveryHelper.getResponseCode(responseURL)) == responseCode);
        }
    }

    @And("^I verify that the URL should not contain x and y coordinates$")
    public void iVerifyTheURLShouldNotContainXAndYCoordinates() throws Throwable {
        String url = WebDriverManager.getWebDriver().getCurrentUrl();
        System.out.println("X and Y coordinates are not displayed in the url" + url);
        Assert.assertFalse("X coordinates are displayed in URL: ",
                url.contains("&x="));
        Assert.assertFalse("Y coordinates are displayed in URL: ",
                url.contains("&y="));
        DiscoveryHelper.logInfo("Verified that url should not contain x and y coordinates");
    }

    @When("^I select a product from section '(\\d+)' on thumbnail grid$")
    public void Iselectaproductfromsectiononthumbnailgrid(int section) throws Throwable {
        Wait.untilElementNotPresent("product_thumbnails.loading_div");
        String column_grid = Elements.findElement(Elements.element("search_result.grid_view")).getText();
        int height = Integer.parseInt(Elements.findElement(By.tagName("body")).getCssValue("height").split("\\.")[0]);
        for(int i = 0; i < section; i++) {
            Navigate.scrollPage(0, height - 900);
            Wait.forLoading(By.className("loader-container"));
        }
        prodIds = DiscoveryHelper.getProductIds();
        int thumbnailColumns = ProductThumbnail.productThumbnailColumns();
        System.out.println("prodIds.size(): " + prodIds.size());
        selectedProdId = prodIds.get(((thumbnailColumns == 4) ? section * 50 : section & 55) + 1);

        String url = WebDriverManager.getCurrentUrl();
        strUtils = url.split("Productsperpage/")[1].split(",")[0];

        DiscoveryHelper.selectProductThumbnail(selectedProdId);
        DiscoveryHelper.logInfo("Selected a product id from section " + section + " thumbnail grid");
    }

    @Then("^I verify that 'Show Previous Items' button is displayed at top of all product rows$")
    public void IshouldseeShowPreviousItemsbuttonattopofallproductrows() throws Throwable {
        Assert.assertTrue("Filter by element is not displayed",
                Elements.elementPresent("product_thumbnails.show_previous_items"));
        DiscoveryHelper.logInfo("Verified that 'Show Previous Items' button is displayed at top of all product rows");
    }

    @Then("^I verify that landed on SearchResultsPage on same section$")
    public void IverifythatlandedonSearchResultsPageonsamesection() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        String currentSection = url.split("Productsperpage/")[1].split(",")[0];
        System.out.println("currentSection: " + currentSection);
        Assert.assertTrue("Page dind't landed to the same section as before",
                currentSection.equals(strUtils));
        DiscoveryHelper.logInfo("Verified that landed on search result page on same section");
    }

    @Then("^I verify that previous section of products are loaded on thumbnail grid$")
    public void Ishouldseeprevioussectionofproductsareloadedonthumbnailgrid() throws Throwable {
        List<String> currentprodIds = DiscoveryHelper.getProductIds();
        System.out.println("currentprodIds.size"+ currentprodIds.size());
        if (currentprodIds.size() < prodIds.size()) {
            Assert.fail("Previous section products are not landed on thumbnail grid");
        }
        prodIds = DiscoveryHelper.getProductIds();
        DiscoveryHelper.logInfo("Verified that previous section of products are loaded on thumbnail grid");
    }

    @When("^I select 'Show Previous Items' button$")
    public void IselectShowPreviousItemsbutton() throws Throwable {
        prodIds = DiscoveryHelper.getProductIds();
        System.out.println("prodids.size"+ prodIds.size());
        Wait.untilElementPresent("product_thumbnails.show_previous_items");
        Elements.findElement("product_thumbnails.show_previous_items").click();
        DiscoveryHelper.logInfo("Selected 'Show Previous Items' button");
    }

    @And("^I verify that facets with product counts are displayed$")
    public void iVerifyThatFacetsWithProductCountsAreDisplayed() throws Throwable {
        List<String> facetNames = DiscoveryHelper.getAllFacetName();
        for (String facetName : facetNames) {
            Map<String, Integer> facetDetails = DiscoveryHelper.getFacetDetail(facetName);
            System.out.println(facetDetails);
            for (Map.Entry<String, Integer> detail : facetDetails.entrySet()) {
                Assert.assertTrue("Facet value count is displayed as zero for facet Name: " + facetName + " and Facet Value: " +
                        detail.getKey() + " Displayed as: " + detail.getValue(), detail.getValue() != 0);
            }

            DiscoveryHelper.logInfo("All facet values are displayed with product counts");
        }
    }

    @And("^I verify that each facet types have multifacet functionality$")
    public void iVerifyThatEachFacetTypesHaveMultifacetFunctionality() throws Throwable {
        List<String> facetNames = DiscoveryHelper.getAllFacetName();
        for (String facet : facetNames) {
            WebElement facetDiv = Elements.findElement(LeftFacet.getFacetDiv(facet));
            Assert.assertTrue("Facet " + facet + " does not have multifacet func",
                    facetDiv.getAttribute("data-uitype").contains("MULTISELECT"));
        }
        DiscoveryHelper.logInfo("Each facet types has multifacet functionality");
    }

    @Then("^I verify that the breadcrumb is not displayed in search result page$")
    public void iShouldNotSeeBreadcrumbInSearchResultPage() throws Throwable {
        Assert.assertFalse("Selected breadcrumb is displayed",
                Elements.elementPresent("leftFacetNavigation_panel.facetBreadcrumbs"));
        DiscoveryHelper.logInfo("Breadcrumb not displayed in search result page");
    }

    @And("^I verify that the quick peek icon on product image without hovering on product image$")
    public void iVerifyThatTheQuickPeekIconOnProductImageWithoutHoveringOnProductImage() throws Throwable {
        List<String> productIds = DiscoveryHelper.getProductIds();
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(productIds.size() - 1);
        WebElement prodElement = DiscoveryHelper.getProductDiv(selectedProdId);
        selectedProdId = productIds.get(index);
        Assert.assertTrue("Quick peek icon not displayed",
                prodElement.findElement(By.className("quickPeekIcon")).isDisplayed());
        DiscoveryHelper.logInfo("Quick Peek icon displayed as expected");
    }



    @And("^I verify that the default product thumbnail and alt image$")
    public void iVerifyThatTheDefaultProductThumbnailAndAltImage() throws Throwable {
        WebElement prodElement = DiscoveryHelper.getProductDiv(selectedProdId);
        String imageText = prodElement.findElement(By.className("thumbnailImage")).getAttribute("src");
        int count = imageText.split(".tif").length -1;
        Assert.assertTrue("Expected Alt image not displayed",count >= 2);
        DiscoveryHelper.logInfo("Product thumbnails and alt image verified successfully");
    }

    @And("^I verify that default attributes on new quick peek overlay$")
    public void iVerifyTheDefaultAttributesOnNewQuickPeekOverlay() throws Throwable {
        Assert.assertTrue("Add to bag button not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.quick_view_product_add_to_bag"));
        Assert.assertTrue("Product title not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.quick_view_product_title"));
        Assert.assertTrue("Price information not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.quick_view_price_list"));
        Assert.assertTrue("Close icon not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.quick_view_close_dialog"));
        Assert.assertTrue("Product info details not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.quick_view_see_full_details"));
        DiscoveryHelper.logInfo("Default attributes of the Quick Peek Verified");
    }

    @When("^I select X button on new quick peek overlay$")
    public void iSelectXButtonOnNewQuickPeekOverlay() throws Throwable {
        Clicks.click("quick_view.quick_view_close_dialog");
        DiscoveryHelper.logInfo("Selected X button on new quick peek overlay");
    }

    @When("^I select anywhere outside of new quick peek overlay$")
    public void iSelectAnywhereOutsideOfNewQuickPeekOverlay() throws Throwable {
        Actions action = new Actions(WebDriverManager.getWebDriver());
        action.moveToElement(Elements.findElement("quick_view.quick_view_product_dialog"),340,40).click();
        Wait.animationDone();
        if (Elements.elementPresent("quick_view.quick_view_product_dialog"))
            iSelectXButtonOnNewQuickPeekOverlay();
        DiscoveryHelper.logInfo("Selected anywhere outside of new quick peek overlay");
    }

    @Then("^I verify that \"([^\"]*)\" product thumbnails in search results page$")
    public void iShouldSeeProductThumbnailsInSearchResultsPage(int expectedItemsPerPageCount) throws Throwable {
        int itemsPerPage = Integer.parseInt(DiscoveryHelper.getItemsPerPage());
        System.out.println("current Selected Product per page" + itemsPerPage);
        int totalProductInCurrentPage = DiscoveryHelper.getProductIds().size();
        Assert.assertTrue("Expected thumbnail count and item per page count varies: "
                ,totalProductInCurrentPage == itemsPerPage);
        DiscoveryHelper.logInfo("Verified that " + expectedItemsPerPageCount + " product thumbnails in search results page");
    }

    @When("^I search for an available product ID or WebID having \"([^\"]*)\"$")
    public void iSearchForAnAvailableProductIDOrWebIDHaving(String productType) throws Throwable {
        HashMap<String, Object> productHash = new HashMap<>();
        productHash.put("available", true);
        int productId = TestUsers.getRandomProduct(productHash).id;
        DiscoveryHelper.logInfo("Available product from json: " + productId);
        String productIdToSearch = null;
        switch (productType) {
            case "productid only":
                productIdToSearch = Integer.toString(productId);
                break;
            case "productid with preceding zeros and space":
                productIdToSearch = "  00"+Integer.toString(productId)+"  ";
                break;
            case "product id with other characters":
                productIdToSearch = Integer.toString(productId)+"abas";
                break;
            case "invalid productid":
                productIdToSearch = Integer.toString(productId)+"80983483";
                break;
        }
        selectedProdId = productIdToSearch;
        TextBoxes.typeTextNEnter("home.search_field", productIdToSearch);
    }

    @When("^I search for a (PDP|URL|Category) redirect keyword$")
    public void iSearchForAPDPRedirectKeyword(String redirectType) throws Throwable {
        System.out.println("hi");
        if (redirectType.equals("PDP")) {
            iSearchForAnAvailableProductIDOrWebIDHaving("productid only");
            searchKeyword = selectedProdId;
        } else {
            searchKeyword = redirectType.equals("URL") ? "Customer Service" : "MAC";
            TextBoxes.typeTextNEnter("home.search_field", searchKeyword);
        }
        DiscoveryHelper.logInfo("Searched for a "+ redirectType + " redirect keyword with " + searchKeyword);
    }

    @When("^I collect product information from PDP page$")
    public void iCollectProductInformationFromPDPPage() throws Throwable {
        Wait.untilElementPresent("product_display.product_id_div");
        if (Elements.elementPresent("product_display.more_details")) {
            Clicks.click("product_display.more_details");
        }
        selectedProdId = Elements.findElement(Elements.element("product_display.product_id_div")).getText().replace("Web ID: ", "");
        DiscoveryHelper.logInfo("Displayed product id in PDP page: " + selectedProdId);
    }

    @And("^I collect (PDP|URL|Category) redirect information from SDP in \"([^\"]*)\" mode$")
    public void iCollectPDPRedirectInformationFromSDPInMode(String redirectType, String mode) throws Throwable {
        redirectType = redirectType.equals("URL") ? "ABSOLUTE_URL" : redirectType;
        mode = mode.equalsIgnoreCase("site") ? "SITE" : "WEDDING_REGISTRY";
        String regionCode = mode.equalsIgnoreCase("iship") ? "CA" : "US";
        Map response = SearchService.getSearchService(searchKeyword, mode, regionCode, false, null);
        Map searchResponse = (Map) response.get("SearchResult");
        LinkedTreeMap redirectDTO = (LinkedTreeMap) searchResponse.get("redirectDTO");
        Assert.assertTrue("Redirect information from SDP and UI is not same",
                redirectDTO.get("redirectType").toString().equalsIgnoreCase(redirectType));
        locationId = redirectDTO.get("locationId").toString();
        DiscoveryHelper.logInfo("Collected " + redirectType + " redirect information from SDP in " + mode + " mode");
    }

    @Then("^I verify that (PDP|URL|Category) redirect information should be same in both Navapp and SDP$")
    public void iVerifyThatPDPRedirectInformationShouldBeSameInBothNavappAndSDP(String redirectType) throws Throwable {
        String url = null;
        switch (redirectType) {
            case "PDP":
                Assert.assertTrue("ERROR - APP: Expected Product Id: " + locationId + " Displayed Product Id: " + selectedProdId,
                        selectedProdId.equals(locationId));
                break;
            case "URL":
                url = PageNavigation.url();
                Assert.assertTrue("ERROR - APP: Expected page URL : " + locationId + " But navigated page url: " + url,
                        url.toLowerCase().contains(searchKeyword.toLowerCase().replace(" ","%20")));
                break;
            case "Category":
                url = PageNavigation.url();
                Assert.assertTrue("ERROR - APP: Expected Category id: " + locationId + " in page url: " + url,
                        url.contains(locationId));
                break;
        }
        DiscoveryHelper.logInfo("Verified that " + redirectType + " redirect information is same in both Navapp and SDP ");
    }

    @Then ("^I verify that \"([^\"]*)\" page (is not displayed|is displayed)$")
    public void iVerifyThatPageIsDisplayed(String page, String condition) throws Throwable {
        try {
            if (condition.equals("is displayed"))
                shouldBeOnPage(page.replace(" ", "_"));
        } catch (EnvException e) {
            Assert.assertTrue(page + " is displayed " + e.getMessage(), condition.equals("is not displayed"));
        } catch (Exception e) {
            Assert.fail("Other exception" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that " + page + " page " + condition);
    }

    @And("^I save my location in ISA overlay$")
    public void iSaveMyLocationInISAOverlay() throws Throwable {
        Clicks.click("change_pickup_store_dialog.save");
        DiscoveryHelper.logInfo("Successfully clicked Save button in ISA overlay");
    }

    @When("^I add MISCGCs with zipcode \"([^\"]*)\" cookie to cookie list$")
    public void iAddWithZipcodeCookieFromCookieList(String cookieName, String zipcode) throws Throwable {
        zipCode = zipcode;
        DiscoveryHelper.createBopsCookie(zipCode);
        DiscoveryHelper.logInfo("Added MISCGCs with zipcode "+ zipcode);
    }

    @And("^I verify that default message \"([^\"]*)\" under bops facet$")
    public void iVerifyThatDefaultMessageUnderBopsFacet(String errorMessage) throws Throwable {
        String displayedMessage = Elements.getText("left_facet.bops_no_store_message");
        Assert.assertTrue("Expected error: " + errorMessage + " is not displayed under bops facet",
                displayedMessage.equalsIgnoreCase(errorMessage));
        DiscoveryHelper.logInfo("Verified that error message " + errorMessage + " is displayed under bops facet");
    }

    @And("^I verify that products with (\\d+) count displayed is in disabled state$")
    public void iVerifyThatProductsWithCountDisplayedIsInDisabledState(int count) throws Throwable {
        List<String> storeNames = Arrays.asList(Elements.findElement(By.className("facetsOuterWrapper")).findElement(By.tagName("div")).getText().split("\n"));
        List<String> unavailablestores = storeNames.stream().filter(elm -> elm.contains("[0]")).collect(Collectors.toList());
        System.out.println(unavailablestores);
        if (!unavailablestores.isEmpty()) {
            for (String store : unavailablestores) {
                Assert.assertTrue("Store does not contain 0", store.contains("[0]"));
            }
            DiscoveryHelper.logInfo("Stores with 0 product count are displayed in disabled state");
        }
    }

    @And("^I verify that SEARCH button is displayed under bops facet$")
    public void iVerifyThatSEARCHButtonUnderBopsFacet() throws Throwable {
        Assert.assertTrue("Search button is not displayed under bops facet",
                Elements.elementPresent("bops_facet.search_by_zipcode"));
        DiscoveryHelper.logInfo("Verified that Search button is displayed under bops facet");
    }

    @And("^I select Apply button in bops facet$")
    public void iSelectApplyButtonInBopsFacet() throws Throwable {
        Clicks.click("facet_chart.in_store_pickup_apply_btn");
        DiscoveryHelper.logInfo("Apply button is selected successfully");
    }

    @Then("^I verify that the store name is (displayed|not displayed) in bops messaging$")
    public void iVerifyThatTheStoreNameDisplayedInBopsMessaging(String nameDisplayed) throws Throwable {
        if(nameDisplayed.equals("not displayed"))
            Assert.assertTrue("Store name is displayed in bops messaging",
                    Elements.findElements("product_display.bops_availability_message_with_store").isEmpty());
        else {
            Assert.assertFalse("Store name is not displayed in bops messaging",
                    Elements.findElements("product_display.bops_availability_message_with_store").isEmpty());
        }
        DiscoveryHelper.logInfo("Store name is " + nameDisplayed +" in bops messaging in PDP");
    }

    @When("^I select a random UPC of the product$")
    public void iSelectARandomUPCOfTheProduct() throws Throwable {
        ProductDisplay.selectRandomColor();
        ProductDisplay.selectRandomSize();
        DiscoveryHelper.logInfo("Successfully selected Random UPC of the product in PDP");
    }

    @Then("^I verify that the checked value not appears on top$")
    public void iVerifyThatTheCheckedValueNotAppearsOnTop() throws Throwable {
        Assert.assertTrue("Selected facet values displayed",
                Elements.findElement(By.className("selectedFacetsSection")) == null);
        DiscoveryHelper.logInfo("Checked value not appears on top as expected");
    }

    @Then("^I verify that the error message \"([^\"]*)\" is displayed in bops facet$")
    public void iVerifyThatTheErrorMessageIsDisplayedInBopsFacet(String message) throws Throwable {
        Wait.untilElementPresent("bops_facet.error_message");
        Assert.assertTrue("Error message " + message + " not displayed",
                Elements.getText("bops_facet.error_message").contains(message));
        DiscoveryHelper.logInfo("Error message " + message + " is displayed");
    }

    @Then("^I verify that landed on \"([^\"]*)\" Page$")
    public void iVerifyLandedPage(String pageName) throws Throwable{

        shouldBeOnPage(pageName);

       //Assert.assertTrue("Page Haven't Landed on Expected page ",onPage(pageName));

    }

    @Then("I navigate to dynamic landing page in website in \"([^\"]*)\" mode$")
    public void navigateToDLP(String modeName){

        DiscoveryHelper.landingDynamicLandingPage(modeName);
    }

    @Then("I click on \"([^\"]*)\" label link$")
    public void clickLabel(String labelName){

        WebElement element = Elements.findElement(By.linkText(labelName));
        Clicks.click(element);
    }

    @When("^I select (\\d+) th product from thumbnail grid$")
    public void iSelectNthProductFromThumbnailGrid(int nThProduct) throws Throwable {
        try {
            Wait.untilElementNotPresent("product_thumbnails.loading_div");
            prodIds = DiscoveryHelper.getProductIds();
            System.out.println("No of products in the page "+prodIds.size());
            Assert.assertTrue("The results doesn't have given Nth product",prodIds.size() >= nThProduct);
            selectedProdId = prodIds.get(nThProduct);
            DiscoveryHelper.selectProductThumbnail(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Error selecting Nth product thumb of prod_id " + selectedProdId + " " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected Nth product " + selectedProdId + " from thumbnail grid");
        System.out.println("Selected Nth product " + selectedProdId + " from thumbnail grid");
    }

    @When("^I select the quick peek of (\\d+) th product$")
    public void iSelectQuickPeek_NthProduct(int nThProduct) throws Throwable {

        try {
            Wait.untilElementNotPresent("product_thumbnails.loading_div");
            prodIds = DiscoveryHelper.getProductIds();
            System.out.println("No of products in the page "+prodIds.size());
            Assert.assertTrue("The results doesn't have given Nth product",prodIds.size() >= nThProduct);
            selectedProdId = prodIds.get(nThProduct);
            DiscoveryHelper.selectQuickView(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to select the quick peek of random product" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the quick peek of random product id: " + selectedProdId);
    }

    @Then("^I click add to bag button on PDP page$")
    public void i_add_product_from_PDP() throws Throwable {

        //ifPresentDo("product_display.add_to_bag", Clicks.click("product_display.add_to_bag"));

        if (Elements.elementPresent("product_display.add_to_bag")) {
            Clicks.click("product_display.add_to_bag");
        } else {
            Assert.fail("Add To Bag Button is not displayed");
        }
        DiscoveryHelper.logInfo("Successfully click on Add to bag button");
    }

}
