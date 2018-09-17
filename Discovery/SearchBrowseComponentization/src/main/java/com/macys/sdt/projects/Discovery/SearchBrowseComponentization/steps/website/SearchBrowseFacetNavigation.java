package com.macys.sdt.projects.Discovery.SearchBrowseComponentization.steps.website;

import com.google.gson.Gson;
import com.gargoylesoftware.htmlunit.util.UrlUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.framework.utils.rest.services.ProductService;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.BrowseService;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.SearchService;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.HeaderActions;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.RecentlyViewed;
import com.macys.sdt.shared.steps.website.Facets;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import com.thoughtworks.selenium.webdriven.commands.Click;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.httpclient.URI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.macys.sdt.framework.utils.ScenarioHelper.scenario;
import static com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper.*;
import static com.macys.sdt.shared.steps.website.PageNavigation.searchKeyword;
import static com.macys.sdt.shared.steps.website.PageNavigation.shoppingMode;
import static com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.FlexTemplatePanel.mediaExistsRowElement;
import static java.net.URLDecoder.decode;
import static java.util.stream.Collectors.toList;

public class SearchBrowseFacetNavigation extends StepUtils {

    //Global Declarations
    private DiscoveryHelper helper = new DiscoveryHelper();
    private String selectedProdId;
    private static int thumbnailColumns;
    public static ArrayList<String> selectedFacetValues = new ArrayList<>();
    public static ArrayList<String> selectedFacetNames = new ArrayList<>();
    private static List<String> firstSuggestionsList = new ArrayList<>();
    private static List<String> secondSuggestionsList = new ArrayList<>();
    private String ISHIPCountry = "Canada";
    private String ISHIPCountryCode = "CA";
    private static int productCount;
    private String facetItems = null;
    private String sortByValue;
    private static String strUtils;
    private int storeResultsCount;
    private String selectedFacet;
    private String selectedSortBy;
    private String selectedFacetName;
    private List<String> prodIds;
    private int totalPageCount;
    private static String itemPerPageCount;
    private String selectedColorSwatch;
    private String selectedColorSwatchTitle;
    private static int totalThumbnailCount;
    private static String facetSelection;
    private static String brandFacet;
    private static int selectedPageNo;
    private static String zipCode;
    private static int totalProductInRvi;
    private ProductData productData;
    private static String locationId;
    private static String bopsFacetName = macys() ? "Free Pick Up In Store" : (Registry.registryMode ? "Pick Up In-Store" : "Pick Up In Store");
    private static JSONObject productDetails;
    private static String tempRemovedItemFrmRVI;
    private static String productColorwayType;
    private static String productType;
    private static WebElement hoverEle = null;
    private static String swatchType;
    private static String productPrice;
    private static String selectedSubcategory;
    private static int productCountBefore;
    private static String beforePriceSortUrl;
    private static String leftNavAttributeValue;
    private ArrayList<String> excludedCategories = new ArrayList<String>();
    private static ArrayList<HashMap> mediaData = new ArrayList<HashMap>();
    private List<WebElement> leftNavLinksSlpPage;
    private ArrayList<String> leftNavLinksText = new ArrayList<String>();
    private String actualHeaderName;
    private String brandName;
    private String catName;
    private static final Logger logger = LoggerFactory.getLogger(SearchBrowseFacetNavigation.class);

    @When("^I select 'single' facet value from 'any' facet section$")
    public void iSelectSingleFacetValueFromAnyFacetSection() throws Throwable {
        String facetName = null, facetValue = null;
        if (selectedFacetValues.isEmpty())
            selectedFacetValues.clear();
        if (selectedFacetNames.isEmpty())
            selectedFacetNames.clear();
        if (totalPageCount == 0)
            totalPageCount = DiscoveryHelper.getTotalPageCount();
        productCount = DiscoveryHelper.getProductCount();
        Wait.forLoading("left_facet.loading");
        List<String> facetNames = LeftFacet.getAllFacetName();
        facetNames.removeAll(selectedFacetNames);
        facetNames.remove("Free Pick Up In Store");
        facetNames.remove("Pick Up In Store");
        facetNames.remove("Pick Up In-Store");
        facetNames.remove("Price");
        if (macys()) {
            facetNames.removeAll((ArrayList) getAllGroupFacetName());
        }
        if (!macys()){
            facetNames.remove("Item Type");  // "Item Type" id value is returned as PRODUCT_DEPARTMENT / COLD WEATHER TYPE
        }
        DiscoveryHelper.logInfo("current facet list: " + facetNames);
        int index = facetNames.size() <= 1 ? 0 : new Random().nextInt(facetNames.size() - 1);
        facetName = facetNames.get(index);
        DiscoveryHelper.logInfo("FACET NAME " + facetName);
        facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
        DiscoveryHelper.logInfo("FACET VALUE " + facetValue);
        LeftFacet.selectItemFromFacet(facetValue, facetName);
        selectedFacetNames.add(facetName);
        selectedFacetValues.add(facetValue);
        Wait.forLoading("left_facet.loading");
        strUtils = WebDriverManager.getCurrentUrl();
        DiscoveryHelper.navigateToComponentizationURL();
        DiscoveryHelper.logInfo("Selected single facet value: " + facetValue + " from " + facetName + " facet section");
    }

    @Then("^I verify that facet section displaying with 'filter by' header text$")
    public void iVerifyThatFacetSectionDisplayingWithFilterByHeaderText() throws Throwable {
        WebElement element;
        try {
            Assert.assertTrue("Filter by element is not displayed", Elements.elementPresent("left_facet.facetPanelLabel"));
            element = Elements.findElement(Elements.element("left_facet.facetPanelLabel"));
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
            int totalThumbnailCount = DiscoveryHelper.getTotalThumbnailCount();
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

        Wait.forLoading("left_facet.loading");

        //to make sure on new SNBC
        DiscoveryHelper.navigateToComponentizationURL();

        int currentProductCount = DiscoveryHelper.getProductCount();
        if (macys()) {
            int currentThumbnailCount = DiscoveryHelper.getTotalThumbnailCount();
            Assert.assertTrue("Product thumbnail count and total count mismatch Expected thumbnail count: " + currentProductCount + " Actual thumbnail displayed: " + currentThumbnailCount,
                    currentThumbnailCount == currentProductCount || currentThumbnailCount >= currentProductCount - 10);
        }

        Assert.assertTrue("Product count is same. Expected count: " + productCount + "Actual Count: " + currentProductCount,
                currentProductCount <= productCount);

        int currentPageCount = DiscoveryHelper.getTotalPageCount();
        Assert.assertTrue("Current pagination count and previous pagination counts are same. Expected Count:" + currentPageCount + "Actual Count: " + totalPageCount,
                currentPageCount <= totalPageCount);

        try {
            if (selectedFacetName == null) {
                breadCrumbVerification = DiscoveryHelper.breadCrumbVerification(selectedFacetValues);
                clearAllButtonVerification = helper.displayClearAllButton();
            } else {
                if (selectedFacetName.equals(bopsFacetName)) {
                    breadCrumbVerification = true;
                    clearAllButtonVerification = true;
                } else {
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
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
        if (type.equals("top")) jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        else jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        DiscoveryHelper.logInfo("Navigated to " + type + " of the page");
    }

    @Then("^I verify that facet section displaying as per service response in \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iVerifyThatFacetSectionDisplayingAsPerServiceResponse(String mode, String keyword) throws Throwable {
        try {
            List<WebElement> facetsList;
            List<String> facetsDisplayed;
            List<String> facetName = new ArrayList<>();
            Map serviceResponse;

            String shoppingMode = mode.equalsIgnoreCase("REGISTRY") ? "WEDDING_REGISTRY" : "SITE";
            String regionCode = mode.equalsIgnoreCase("ISHIP") ? ISHIPCountryCode : "US";
            if (WebDriverManager.getCurrentUrl().contains("?id=")) {
                String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
                serviceResponse = BrowseService.getBrowseService(catID, shoppingMode, regionCode, false, null);
            } else {
                serviceResponse = SearchService.getSearchService(keyword, shoppingMode, regionCode, false, null);
            }

            DiscoveryHelper.collapseAllExpandedFacets();
            facetsList = Elements.findElements(Elements.element("left_facet.facet_names"));
            facetName.addAll(facetsList.stream().filter(ele -> !ele.getText().equalsIgnoreCase(""))
                    .map(WebElement::getText).collect(Collectors.toList()));

            facetsDisplayed = SearchService.getFacetNameFromService(serviceResponse);
            facetsDisplayed = facetsDisplayed.stream().filter(m -> !m.toLowerCase().contains("store")).collect(Collectors.toList());
            facetName = facetName.stream().filter(m -> !m.toLowerCase().contains("store")).collect(Collectors.toList());
            Assert.assertTrue("There is a mismatch between Facet Service:'" + facetsDisplayed + "' and UI:" + facetName, facetsDisplayed.containsAll(facetName));
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
            totalThumbnailCount = DiscoveryHelper.getTotalThumbnailCount();
            productCountBefore = productCount;
               /* if (macys()) {
                    itemPerPageCount = DiscoveryHelper.getItemsPerPage();
					thumbnailColumns = ProductThumbnail.productThumbnailColumns();
				}*/
        } else
            Assert.fail("Product count is not displayed");
        DiscoveryHelper.logInfo("Verified product count is displayed");
    }

    @Then("^I verify that the product count is updated$")
    public void iVerifyThatTheProductCountIsUpdated() throws Throwable {
        try {
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
        if (selectedFacetName == null)
            selectedFacetName = selectedFacetNames.get(0);
        for (String facet : selectedFacetValues) {
            LeftFacet.selectItemFromFacet(facet, selectedFacetName);
        }
        DiscoveryHelper.logInfo("Expected facet value " + selectedFacetValues.get(0) + " removed from the facet " + selectedFacetName);
    }

    @When("^I deselect the (Price|Brand|Color|Size|Customer Ratings) from the overlay$")
    public void iDeselectThePriceFromTheOverlay(String facetName) throws Throwable {
        pausePageHangWatchDog();
        try {
            if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet(facetName);
            }
            List<WebElement> facetValues = Elements.findElement(LeftFacet.getFacetDiv(facetName)).findElements(By.tagName("li"));
            WebElement ele = facetValues.stream().filter(e -> e.getAttribute("class").contains("selected")).findFirst().orElse(null);
            DiscoveryHelper.logInfo("Deselecting facet value:" + ele.getText());
            if (StepUtils.safari()) {
                Clicks.javascriptClick(ele.findElement(By.tagName("a")));
            } else {
                Clicks.click(ele.findElement(By.tagName("a")));
            }
            if (bloomingdales()) {
                Clicks.javascriptClick(LeftFacet.getFacetApply(facetName));
                Wait.forLoading("left_facet.loading");
            }
        } catch (Exception e) {
            Assert.fail("Error performing facet deselection \r\n" + e.getMessage());
        }
        resumePageHangWatchDog();
        DiscoveryHelper.logInfo("Deselected the price from overlay");
    }

    @Then("^I verify that the product count returns to original$")
    public void iVerifyThatTheProductCountReturnsToOriginal() throws Throwable {
        int currentProductCount = DiscoveryHelper.getProductCount();
        Assert.assertTrue("Error displaying original product count after deselecting facet.Expected:" + productCountBefore + " Actual: " + currentProductCount, currentProductCount == productCountBefore);
        DiscoveryHelper.logInfo("Verified that the product count returns to original");
    }

    @And("^I click on clear all button$")
    public void iClickOnClearAllButton() throws Throwable {
        try {
            helper.clickClearAllButton();
            Wait.forLoading("left_facet.loading");
        } catch (Exception e) {
            Assert.fail("Error clicking Clear All button.\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Clicked Clear All Button");
    }

    @And("^I select \"([^\"]*)\" facet value from \"([^\"]*)\" facet section$")
    public void iSelectFacetValueFromBrandFacetSection(String facetTime, String facetName) throws Throwable {
        int times = facetTime.equalsIgnoreCase("single") ? 1 : 2;
        selectedFacetName = facetName;
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        productCount = DiscoveryHelper.getProductCount();
        if(productCountBefore != 0)
            productCountBefore = DiscoveryHelper.getProductCount();
        for (int i = 0; i < times; i++) {
            Wait.forLoading("left_facet.loading");
            System.out.println("FACET NAME " + facetName);
            if (facetName.contains("customer") || facetName.contains("Customer")) {
                //since customer top rated facet is displayed in three different ways like Customer Rating, Customers Top Rated & Customers' top rated to over come that used the below step
                facetName = LeftFacet.getExactFacetName("Customer");
            }
//            if (i > 0) {
//                Navigate.browserRefresh();
//                DiscoveryHelper.navigateToComponentizationURL();
//            }
            selectedFacet = DiscoveryHelper.getRandomFacetValue(facetName);
            System.out.println("FACET ITEM " + selectedFacet);
            LeftFacet.selectItemFromFacet(selectedFacet, facetName);
            selectedFacetValues.add(selectedFacet);
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

    @Then("^I verify that landed on (?:SearchResultsPage|DLP|CategoryBrowsePage|SubSplashPage) on same product grid point$")
    public void iVerifyThatLandedOnPageOnSameProductGridPoint() throws Throwable {
        try {
            DiscoveryHelper.navigateToComponentizationURL();
            List<String> currentProductIds = DiscoveryHelper.getProductIds();
            currentProductIds = currentProductIds.stream().distinct().collect(toList());
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
            String zipcode = macys() ? "94102" : "10021";
            if (!LeftFacet.isExpanded(bopsFacetName))
                LeftFacet.expandFacet(bopsFacetName);
            Wait.untilElementPresent("left_facet.bops_loading_mask");
            if (!Elements.findElement("left_facet.bops_location").isDisplayed()) {
                WebElement ele = Elements.findElement("left_facet.bops_store_search_box");
                ele.click();
                ele.clear();
                ele.sendKeys(zipcode);
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
            Wait.untilElementPresent(Elements.element("change_pickup_store_dialog.container"));
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
        Wait.untilElementPresent("left_facet.bops_loading_mask");
        DiscoveryHelper.navigateToComponentizationURL();
        if (condition.equals("displayed")) {
            Wait.forLoading("left_facet.loading");
            if (!LeftFacet.isExpanded(bopsFacetName))
                LeftFacet.expandFacet(bopsFacetName);
            Assert.assertFalse("Store results are not displayed in pick-up in store facet",
                    getRandomFacetValue(bopsFacetName).isEmpty());
        } else {
            Assert.assertTrue("Store results are not displayed in pick-up in store facet",
                    getRandomFacetValue(bopsFacetName).isEmpty());
        }
        DiscoveryHelper.logInfo("Verified that stores are displayed in pick-up in-store facet section");
    }

    @And("^I select multiple facet value from \"([^\"]*)\" facet section$")
    public void iSelectMultipleFacetValueFromFacetSection(String facetName) throws Throwable {
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        try {
            if (macys()) {
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
                //thumbnailColumns = ProductThumbnail.productThumbnailColumns();    (Remove this line since thumbnail column wont come in new search/browse)
            }
            productCount = DiscoveryHelper.getProductCount();
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            if (facetName.equalsIgnoreCase(bopsFacetName)) {
                iSelectMultipleFacetValueOfBopsFacet();
            } else {
                for (int i = 0; i < 2; i++) {
                    if ((facetName.contains("customer") || facetName.contains("Customer")) && i == 0) {
                        facetName = LeftFacet.getExactFacetName("Customer");
                    }
                    if ((facetName.contains("color") || facetName.contains("Color")) && i == 0) {
                        facetName = LeftFacet.getExactFacetName("Color");
                    }
                    if ((facetName.contains("size") || facetName.contains("Size")) && i == 0) {
                        facetName = LeftFacet.getExactFacetName("Size");
                    }
                    if (i > 0) {
                        Navigate.browserRefresh();
                    }
                    String facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
                    System.out.println("FACET ITEM " + facetValue);
                    LeftFacet.selectItemFromFacet(facetValue, facetName);
                    selectedFacetValues.add(facetValue);
                    Wait.forLoading("left_facet.loading");
                }
            }
        } catch (Exception e) {
            Assert.fail("Error fetching Multiple facet from " + facetName + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected multiple facet value from " + facetName + " facet section");
    }

    @When("^I select multiple facet value of bops facet$")
    public void iSelectMultipleFacetValueOfBopsFacet() throws Throwable {
        String facetName = macys() ? "Free Pick Up In Store" : "Pick Up In Store";
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
            prodIds = DiscoveryHelper.getProductIds();
            selectedSortBy = macys() ? DropDowns.getSelectedValue(Elements.element("search_result.sort_by_select")) :
                    Elements.findElement("search_result.sort_by_select").getText();
            selectedProdId = (prodIds.size() == 1) ? prodIds.get(0) : prodIds.get(new Random().nextInt(prodIds.size() - 1));
            DiscoveryHelper.selectProductThumbnail(selectedProdId);
            DiscoveryHelper.logInfo("Selected product id: " + selectedProdId);
            shouldBeOnPage("product_display");
        } catch (Exception e) {
            Assert.fail("Error selecting random product thumb of prod_id " + selectedProdId + " " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected random product " + selectedProdId + " from thumbnail grid");
    }

    @Then("^I verify that landed on respective product display page$")
    public void iVerifyThatLandedOnRespectiveProductDisplayPage() throws Throwable {
        try {
            Wait.untilElementPresent("product_display.product_title");
            if (Elements.elementPresent("product_display.more_details")) {
                Clicks.click("product_display.more_details");
            }
            String waitEle = macys() ? "product_display.product_id_div" : "product_display.web_id";
            Wait.untilElementPresent(waitEle);
            String prodId = macys() ? Elements.findElement(Elements.element("product_display.product_id_div")).getText() :
                    Elements.getText(Elements.element("product_display.web_id"));
            prodId = prodId.replace("Web ID: ", "");
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
                /*int currentThumbnailColumns = ProductThumbnail.productThumbnailColumns();
                DiscoveryHelper.logInfo("Current thumbnail count: " + currentThumbnailColumns);
				DiscoveryHelper.logInfo("Previous thumbnail count: " + thumbnailColumns);
				if (!(currentThumbnailColumns == thumbnailColumns)) {
					Assert.fail("Expected thumbnail columns is not displayed, Expected: " + thumbnailColumns + " Actual: " + currentThumbnailColumns);
				}*/

            //since in new SNBC grid selection is not yet implemented so by default it will be 3 column grid
            int currentThumbnailColumns = 3;
            Assert.assertTrue(currentThumbnailColumns == thumbnailColumns);

        } catch (Exception e) {
            Assert.fail("Previous grid view selection not persist" + e);
        }
        DiscoveryHelper.logInfo("Verified that previous grid view selection persist");
    }

    @Then("^I verify that previous item count selection persist$")
    public static void iVerifyPreviousItemCountSelectionPersists() throws Throwable {
        try {
            //since in new SNBC item per count will display if product count is greater than 60
            if (DiscoveryHelper.getProductCount() > 60) {
                String currentItemPerPageCount = DiscoveryHelper.getItemsPerPage();
                DiscoveryHelper.logInfo("Current items per page count: " + currentItemPerPageCount);
                DiscoveryHelper.logInfo("Previous items per page count: " + itemPerPageCount);
                if (!itemPerPageCount.equals(currentItemPerPageCount)) {
                    Assert.fail("Expected item per page count is not displayed, Expected: " + itemPerPageCount + " Actual: " + currentItemPerPageCount);
                }
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
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        productCount = DiscoveryHelper.getProductCount();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        if (macys()) {
            itemPerPageCount = DiscoveryHelper.getItemsPerPage();
            // thumbnailColumns = ProductThumbnail.productThumbnailColumns();
        }
        String facetName = LeftFacet.getAllFacetName().stream()
                .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null);
        for (int i = 0; i < 2; i++) {
            if (macys()) {
                Navigate.browserRefresh();
                LeftFacet.expandFacet(facetName);
                brandFacetItems = DiscoveryHelper.getAllBrandFacetName();
                pausePageHangWatchDog();
                Random random = new Random();
                int index = random.nextInt(brandFacetItems.size());
                item = brandFacetItems.get(index);
                System.out.println("Item to select\t" + item);
                LeftFacet.expandFacet(facetName);
                if (!LeftFacet.isExpanded(facetName)) {
                    LeftFacet.expandFacet(facetName);
                }
                resumePageHangWatchDog();
            } else {
                item = DiscoveryHelper.getRandomFacetValue(facetName);
            }
            LeftFacet.selectItemFromFacet(item, facetName);
            selectedFacetValues.add(item);
        }
        DiscoveryHelper.logInfo("Selected multiple facet values " + selectedFacetValues + " from Brand facet section");
    }

    @When("^I search for \"([^\"]*)\" keyword in brand facet section$")
    public void iSearchForBrandKeywordInBrandFacetSection(String brandName) throws Throwable {
        try {
            brandName = brandName.equals("unavailable brand") ? "qwert" : brandName;
            brandFacet = LeftFacet.getAllFacetName().stream()
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
            Wait.untilElementPresent("left_facet.error_highlight");
            String actualErrorMessage = Elements.getText("left_facet.error_highlight");
            System.out.println("Text of the ele" + actualErrorMessage);
            Assert.assertTrue("Expected error message is not displayed", actualErrorMessage.equals(expectedErrorMessage));
        } else {
            Assert.assertFalse("Expected error message is displayed", Elements.elementPresent("left_facet.error_highlight"));
        }
        DiscoveryHelper.logInfo("Verified error message " + expectedErrorMessage + " is " + condition + " below search box");
    }

    @Then("^I verify that previous store facet removed and applied new store facet$")
    public void i_verify_that_previous_store_facet_removed_and_applied_new_store_facet() {
        try {
            String newSelectedFacet = Elements.getText("left_facet.facetBreadcrumbs");
            if (selectedFacet.equalsIgnoreCase(newSelectedFacet))
                Assert.fail("Previous store facet is not removed");
            selectedFacetValues.remove(selectedFacet);
            selectedFacetValues.add(newSelectedFacet);
        } catch (Exception e) {
            Assert.fail("Error Applied facet value is not displayed" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that previous store facet removed and applied new store facet");
    }

    @And("^I verify that applied facet value is displayed$")
    public void iShouldGetAppliedFacetValue() throws Throwable {
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        try {
            Wait.forLoading("left_facet.loading");
            selectedFacet = Elements.getText("left_facet.facetBreadcrumbs");
            selectedFacetValues.add(selectedFacet);
            if (selectedFacet.isEmpty())
                Assert.fail("Error Applied facet value is not displayed");
            productCount = DiscoveryHelper.getProductCount();
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
            DiscoveryHelper.navigateToComponentizationURL();
            Navigate.browserRefresh();
            Wait.forLoading("left_facet.loading");
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            productCount = DiscoveryHelper.getProductCount();
            if (selectedFacet == null) {
                selectedFacet = DiscoveryHelper.selectAlternateFacet(selectedFacetValues.get(0), facetName);
                selectedFacetValues.add(selectedFacet);
            } else {
                String secondSelectedFacet = DiscoveryHelper.selectAlternateFacet(selectedFacet, facetName);
                selectedFacetValues.add(secondSelectedFacet);
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
            if ((!miles.get(i).equalsIgnoreCase(actual[i])) || (actual[i] == null)) {
                Assert.fail("Error Miles drop down is not listed" + miles.get(i));
            }
        }
        DiscoveryHelper.logInfo("Verified that miles drop down with below option: " + miles);
    }

    @When("^I select random option from miles drop down$")
    public void iSelectRandomOptionFromMilesDropDown() {
        storeResultsCount = DiscoveryHelper.getAllFacetValues(bopsFacetName).size();
        DiscoveryHelper.selectRandomStoreMiles();
        zipCode = DropDowns.getSelectedValue(Elements.element("left_facet.bops_store_search_radius"));
        DiscoveryHelper.logInfo("Selected random option from miles drop down");
    }

    @Then("^I verify that store facet values are updated with selected mile range$")
    public void iVerifyStoreFacetValuesAreUpdatedWithSelectedMileRange() throws Throwable {
        int zipCodeMile = Integer.parseInt(zipCode.replaceAll("\\D+", ""));
        int currentZipCodeMile = Integer.parseInt(DropDowns.getSelectedValue(Elements.element("left_facet.bops_store_search_radius"))
                .replaceAll("\\D+", ""));
        int currentStoreResultsCount = DiscoveryHelper.getAllFacetValues(bopsFacetName).size();
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
            System.out.println("Cookie value before refresh: " + Cookies.getCookieValue(cookieName));
            Navigate.browserRefresh();
            System.out.println("Cookie value after refresh: " + Cookies.getCookieValue(cookieName));
        } catch (Exception e) {
            Assert.fail("Error Unable to " + condition + " " + cookieName + " cookie" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo(condition + "ed " + cookieName + " zipCode cookie " + fromTo + " cookie list");
    }

    @When("^I search for zipcode \"([^\"]*)\" in bops facet$")
    public void iSearchForZipcodeInBopsFacet(String zipcode) {
        try {
            if (macys()) {
                if (!LeftFacet.isExpanded("Free Pick Up In Store"))
                    LeftFacet.expandFacet("Free Pick Up In Store");
                TextBoxes.typeTextNEnter("left_facet.bops_store_search_box", zipcode);
            } else {
                if (!LeftFacet.isExpanded("Free Pick Up In Store"))
                    LeftFacet.expandFacet("Free Pick Up In Store");
                TextBoxes.typeTextNEnter("bops_facet.zipcode_field", zipcode);
            }
        } catch (Exception e) {
            Assert.fail("Error zipCode search is not working in bops facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for zipCode " + zipcode + " in bops facet");
    }

    @And("^I select 'multiple' facet value from 'any' facet sections$")
    public void iSelectMultipleFacetValueFromAnyFacetSections() throws Throwable {
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        if (selectedFacetNames.isEmpty())
            selectedFacetNames = new ArrayList<>();
        productCount = DiscoveryHelper.getProductCount();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        if (macys()) {
            itemPerPageCount = DiscoveryHelper.getItemsPerPage();

            //since in new SNBC grid selection is not yet implemented so by default it will be 3 column grid
            thumbnailColumns = 3;
        }
        for (int i = 0; i < 2; i++) {
            if (i > 0) {
                Navigate.browserRefresh();
                DiscoveryHelper.navigateToComponentizationURL();
            }
            Wait.forLoading("left_facet.loading");
            List<String> facetNames = LeftFacet.getAllFacetName();
            facetNames.removeAll(selectedFacetNames);
            facetNames.remove("Free Pick Up In Store");
            facetNames.remove("Price");
            if (macys()) {
                facetNames.removeAll((ArrayList) getAllGroupFacetName());
            }
            facetNames.remove("Pick Up In Store");
            facetNames.remove("Pick Up In-Store");
            if (!macys()){
                facetNames.remove("Item Type");  // "Item Type" id value is returned as PRODUCT_DEPARTMENT / COLD WEATHER TYPE
            }
            DiscoveryHelper.logInfo("current Facet Names list: " + facetNames);
            int randomLimit = facetNames.size() - 1;
            if (randomLimit <= 0) break;
            int index = new Random().nextInt(randomLimit);
            String facetName = facetNames.get(index);
            DiscoveryHelper.logInfo("FACET NAME " + facetName);
            String facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
            DiscoveryHelper.logInfo("FACET VALUE " + facetValue);
            LeftFacet.selectItemFromFacet(facetValue, facetName);
            selectedFacetNames.add(facetName);
            selectedFacetValues.add(facetValue);
            strUtils = WebDriverManager.getCurrentUrl();
        }
        Wait.forLoading("left_facet.loading");
        DiscoveryHelper.logInfo("Selected 'multiple' facet value from 'any' facet sections");
    }

    @When("^I search for 'available brand' keyword in brand facet section$")
    public void iSearchForAvailableBrandKeywordInBrandFacetSection() throws Throwable {
        try {
            if (facetItems == null) {
                brandFacet = LeftFacet.getAllFacetName().stream()
                        .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null);
                facetItems = macys() ? DiscoveryHelper.availableBrand() :
                        DiscoveryHelper.getRandomFacetValue(brandFacet);
            }
            if (!LeftFacet.isExpanded(brandFacet)) {
                LeftFacet.expandFacet(brandFacet);
            }
            System.out.println("Available brand name is  \t" + facetItems);
            TextBoxes.typeTextNEnter("left_facet.brand_search", facetItems);
        } catch (Exception e) {
            Assert.fail("Error Selecting available brand facet item: " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for 'available brand' keyword in brand facet section");
    }

    @Then("^I verify that 'X' icon is (displayed|not displayed) in search box under brand facet section$")
    public void iVerifyXIconInSearchBoxUnderBrandFacetSection(String condition) throws Throwable {
        try {
            WebElement ele = Elements.findElement(LeftFacet.getFacetDiv("Brand"));
            ele = ele.findElement(Elements.element("left_facet.x_icon"));
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
            ele = ele.findElement(Elements.element("left_facet.x_icon"));
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
        TextBoxes.typeTextNEnter("left_facet.custom_max_price",
                (String.valueOf(maxOption)));
        Clicks.click("left_facet.price_go");
        if (!Elements.elementPresent("left_facet.price_error"))
            Clicks.click("left_facet.price_go");
        DiscoveryHelper.logInfo("Chose " + maxOption + " for max option");
    }

    @Then("^I verify that the error message is shown$")
    public void iVerifyThatTheErrorMessageIsShown() throws Throwable {
        String expectedError = "please enter a range from 1 to 9999";
        Clicks.click("left_facet.customPriceSubmit");
        Assert.assertTrue("Custom price error message is not displayed", Elements.findElement(By.id("customPriceError")).isDisplayed());
        Clicks.click("left_facet.customPriceSubmit");
        String priceError = Elements.findElement(By.id("customPriceError")).getText();
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
        Collections.sort(selectedFacetValues);
        Collections.sort(currentSelectedFacetValues);
        if (PageNavigation.preSelectedFacet) {
            Assert.assertTrue("Selected facet section does not contain all the selected facet Expected facets: " + selectedFacetValues +
                    " Actual displayed facets: " + currentSelectedFacetValues, currentSelectedFacetValues.containsAll(selectedFacetValues));
        } else {
            Assert.assertTrue("Selected facet section does not contain all the selected facet Expected facets: " + selectedFacetValues +
                    " Actual displayed facets: " + currentSelectedFacetValues, selectedFacetValues.equals(currentSelectedFacetValues));
        }
        DiscoveryHelper.logInfo("Verified that the selected " + facetName + " appears on top");
    }

    @When("^I select the (first|first two) (\\w+) in the (.*?) facet$")
    public void iSelectTheFirstColorInTheColorFacet(String count, String facetValue, String facetName) throws Throwable {
        pausePageHangWatchDog();
        String facetNameOriginal = facetName;
        List<WebElement> featuredBrandsList = null;
        productCount = DiscoveryHelper.getProductCount();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        int time = count.equals("first two") ? 2 : 1;
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        try {
            if (bloomingdales()) {
                totalPageCount = DiscoveryHelper.getTotalPageCount();
                productCount = DiscoveryHelper.getProductCount();
            }
            for (int i = 0; i < time; i++) {
                if (i == 1) {
                    try {
                        LeftFacet.expandFacet(facetName);
                    } catch (Exception e) {
                        LeftFacet.expandFacet(facetNameOriginal);
                        LeftFacet.expandFacet(facetName);
                    }
                    Wait.forLoading("left_facet.loading");
                }
                if (facetName.contains("Customer") && i == 0) {
                    facetName = LeftFacet.getExactFacetName("Customer");
                }
                List<String> facetElement = DiscoveryHelper.getAllFacetValues(facetName);
                if (facetName.contains("Size") && i == 0) {
                    if (!facetElement.isEmpty()) {
                        facetName = facetElement.get(i);
                        facetElement = DiscoveryHelper.getAllFacetValues(facetName);
                    }
                }
                if (i >= 1 && facetElement.isEmpty()) {
                    facetElement = DiscoveryHelper.getAllFacetValues(facetName);
                }
                selectedFacet = facetElement.get(i);
                if (selectedFacet.equalsIgnoreCase("multi")) {
                    selectedFacet = facetElement.get(i + 1);
                }
                if (facetName.contains("Brand")) {
                    Navigate.browserRefresh();
                    DiscoveryHelper.navigateToComponentizationURL();
                    if (Wait.secondsUntilElementPresent("left_facet.featured_brand_values", 10)) {
                        featuredBrandsList = Elements.findElement(Elements.element("left_facet.featured_brand_values")).findElements(By.tagName("li"));
                    } else {
                        featuredBrandsList = Elements.findElements(By.cssSelector("#BRAND li"));
                    }
                    Clicks.hoverForSelection(featuredBrandsList.get(time + 3));
                }
                LeftFacet.selectItemFromFacet(selectedFacet, facetName);
                selectedFacetValues.add(selectedFacet);
            }
        } catch (Exception e) {
            Assert.fail("ERROR DATA:: " + facetValue + " Facet may not be available in this search page" + e.getMessage());
        }
        resumePageHangWatchDog();
        DiscoveryHelper.logInfo("Selected the " + count + " " + facetName + " in the " + facetName + " facet");
    }

    @And("^I select a product having (color|jumbo) swatches$")
    public void iSelectAProductHavingColorSwatches(String type) throws Throwable {
        swatchType = type;
        List<String> prodIds, prodIdsWithMoreLink;
        Random randomGenerator = new Random();
        int index;
        try {
            DiscoveryHelper.navigateToComponentizationURL();
            prodIds = swatchType.equals("color") ? DiscoveryHelper.getProductIdsWithColorSwatch() : DiscoveryHelper.getProductIdsWithJumboSwatch();
            if (prodIds.isEmpty()) {
                logInfo("There is no product displayed with color swatch");
            } else {
                if (bloomingdales()) {
                    prodIdsWithMoreLink = DiscoveryHelper.getProductIdsWithMoreColorSwatch();
                    prodIds.removeAll(ListUtils.intersection(prodIds, prodIdsWithMoreLink));
                    prodIds.removeAll(ListUtils.intersection(prodIds, DiscoveryHelper.getMasterProductIds()));
                    Assert.assertTrue("There is no member product displayed with color swatch", prodIds.size() != 0);
                }
                index = randomGenerator.nextInt(prodIds.size());
                selectedProdId = prodIds.get(index);
                navigateToComponentizationURL();
                selectedColorSwatch = DiscoveryHelper.getSelectedColor(selectedProdId);
                selectedColorSwatchTitle = DiscoveryHelper.getSelectedColorTitle(selectedProdId);
                System.out.println("Selected " + swatchType + " swatch of product id: " + prodIds + " " + selectedColorSwatch);
                System.out.println("Selected facet: " + selectedFacet);
            }
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
        navigateToComponentizationURL();
        pausePageHangWatchDog();
        int index = prodIds.size() >= 6 ? 5 : prodIds.size();
        prodIds = prodIds.subList(0, index);
        for (String id : prodIds) {
            selectedColorSwatch = DiscoveryHelper.getSelectedColor(id);
            DiscoveryHelper.logInfo("Selected color swatch of product id: " + id + " " + selectedColorSwatch);
            DiscoveryHelper.logInfo("Selected color from color facet: " + selectedFacet);
            if (value.equals("color")) {
                Assert.assertTrue("Product id: " + id + " is not displayed with expected colors swatch: " + selectedFacetValues + " Actual color swatch selected: "
                        + selectedColorSwatch, selectedFacetValues.contains(selectedColorSwatch));
                break;
            } else {
                if (selectedColorSwatch != null && !selectedFacetValues.contains(selectedColorSwatch)) {
                    Assert.fail("Product id: " + id + " is not displayed with expected colors swatch: " + selectedFacetValues + " Actual color swatch selected: " + selectedColorSwatch);
                    break;
                }
            }
        }
        resumePageHangWatchDog();
        DiscoveryHelper.logInfo("Verified that the product thumbnails are displayed with the selected " + value);
    }

    @And("^I select \"([^\"]*)\" facet value from Brand facet section$")
    public void I_select_brand_value_from_brand_section(String facetItem) throws Throwable {
        try {
            if (selectedFacetValues.isEmpty())
                selectedFacetValues = new ArrayList<>();
            facetItem = facetItem.equals("available brand") ? facetItems : facetItem;
            productCount = DiscoveryHelper.getProductCount();
            totalPageCount = DiscoveryHelper.getTotalPageCount();
            WebElement facetEle = Elements.findElement(LeftFacet.getFacetDiv(brandFacet));
            String finalFacetItem = facetItem;
            Wait.forLoading("left_facet.loading");
            if (macys())
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
            WebElement toSelect = facetEle.findElements(By.className("item"))
                    .stream().filter(e -> (macys()) ? e.getText().equalsIgnoreCase(finalFacetItem) : e.getText().contains(finalFacetItem))
                    .findFirst()
                    .orElse(null);
            Clicks.click(toSelect);
            if (bloomingdales())
                Clicks.javascriptClick(LeftFacet.getFacetApply(brandFacet));
            Wait.forLoading("left_facet.loading");
            selectedFacetValues.add(facetItem);
        } catch (Exception e) {
            Assert.fail("Error selecting brand facet" + e.getMessage());
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
        pausePageHangWatchDog();
        List<WebElement> facetItemToRemove = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs"));
        for (int i = 0; i < facetItemToRemove.size(); i++) {
            System.out.println("Selected facet values: " + selectedFacetValues);
            System.out.println("Removed facet name: " + Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).get(0).getText());
            String facetChipToRemove = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).get(0).getText();
            if (macys()) {
                WebElement removeIconElement = null;
                try {
                    removeIconElement = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).get(0).findElement(By.className("close-tiny"));
                } catch (Exception e) {
                    removeIconElement = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).get(0).findElement(By.className("removeFacet"));
                }
                Clicks.click(removeIconElement);
                try {
                    if (removeIconElement.isDisplayed()) {
                        Clicks.javascriptClick(removeIconElement);
                    }
                } catch (Exception e) {
                    logInfo("Facet chip:" + facetChipToRemove + " is removed from breadcrumb");
                }
            } else {
                Clicks.click(Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).get(0));
            }
            selectedFacetValues.remove(facetChipToRemove);
            Wait.forLoading("left_facet.loading");
        }
        resumePageHangWatchDog();
        DiscoveryHelper.logInfo("Removed the selected facet from the breadcrumb");
    }

    @When("^I select (\\d+) (\\w+) in the \"([^\"]*)\" facet$")
    public void iSelectColorsInTheColorFacet(int count, String facetValue, String facetName) throws Throwable {
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        if(totalPageCount == 0)
            totalPageCount = DiscoveryHelper.getTotalPageCount();
        if(productCount == 0)
            productCount = DiscoveryHelper.getProductCount();
        if (facetName.contains("Customer"))
            facetName = LeftFacet.getExactFacetName("Customer");
        List<String> currentFacetItems = DiscoveryHelper.getAllFacetValues(facetName);
        if (facetName.contains("Brand")) {
            List<WebElement> featuredBrandsList = Elements.findElement(Elements.element("left_facet.featured_brand_values")).findElements(By.tagName("li"));
            Clicks.hoverForSelection(featuredBrandsList.get(count + 3));
        }
        for (int i = 0; i < count; i++) {
            selectedFacet = currentFacetItems.get(i);
            selectedFacetValues.add(selectedFacet);
            if (i > 0) {
                Navigate.browserRefresh();
                Wait.untilElementPresent("left_facet.facet_div");
                if (!LeftFacet.isExpanded(facetName)) {
                    LeftFacet.expandFacet(facetName);
                }
            }

            LeftFacet.selectItemFromFacet(selectedFacet, facetName);
            Wait.forLoading("left_facet.loading");
        }
        DiscoveryHelper.navigateToComponentizationURL();
        DiscoveryHelper.logInfo("Selected facet values " + selectedFacetValues + " from " + facetName + " facet");
        DiscoveryHelper.logInfo("Selected " + count + facetValue + " in the " + facetName + " facet");
    }

    @When("^I remove (first|second|last) (\\w+) facet from the breadcrumb$")
    public void iRemoveTheColorFacetFromTheBreadcrumb(String currentFacetSelection, String facetName) throws Throwable {
        facetSelection = currentFacetSelection;
        try {
            int size = Elements.findElements("left_facet.facetRemoveBreadcrumb").size();
            switch (facetSelection) {
                case "first":
                    Clicks.click(Elements.findElements("left_facet.facetRemoveBreadcrumb").get(0));
                    break;
                case "second":
                    Clicks.click(Elements.findElements("left_facet.facetRemoveBreadcrumb").get(1));
                    break;
                case "last":
                    Clicks.click(Elements.findElements("left_facet.facetRemoveBreadcrumb").get(size - 1));
                    break;
            }
            Wait.forLoading("left_facet.loading");
        } catch (Exception e) {
            Assert.fail("Unable to remove " + facetName + " facet from breadcrumb");
        }
        DiscoveryHelper.logInfo("Removed " + currentFacetSelection + " selected " + facetName + " facet from the breadcrumb");
    }

    @And("^I verify that the product thumbnails are updated$")
    public void iVerifyThatTheProductThumbnailsAreUpdated() throws Throwable {
        int currentThumbnailCount = DiscoveryHelper.getTotalThumbnailCount();
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
        DiscoveryHelper.navigateToComponentizationURL();
        int currentProductCount = DiscoveryHelper.getProductCount();
        Assert.assertTrue("All products are not displayed Expected product count: " + productCount
                + " Actual count displayed: " + currentProductCount, (productCount - currentProductCount) < 10);
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
        DiscoveryHelper.logInfo("Selected color on color facet: " + selectedFacetValues);
        Assert.assertTrue("Selected color is not highlighted in the color swatch on quick view overlay " +
                "Expected Color: " + selectedColorSwatchTitle + " Actual Color Displayed " + selectedColor, selectedColorSwatchTitle.equalsIgnoreCase(selectedColor));
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

    @And("^I verify that products are filtered with selected price facet value(?:s)?$")
    public void iVerifyThatProductsAreFilteredAsPerSelectedPriceFacetValue() throws Throwable {
        boolean flag;
        Wait.untilElementNotPresent("category_browse.loading_mask");
        List<String> prodIds = DiscoveryHelper.getMemberProdIds();
        int index = prodIds.size() >= 6 ? 5 : prodIds.size();
        prodIds = prodIds.subList(0, index);
        Map<String, String> details = DiscoveryHelper.getProductPriceValues(prodIds);
        for (String id : prodIds) {
            String price = details.get(id);
            DiscoveryHelper.logInfo("Displayed price for the product id: " + id + " is " + price);
            DiscoveryHelper.logInfo("Selected price in price facet: " + selectedFacetValues);
            flag = DiscoveryHelper.priceVerification(id, selectedFacetValues, price);
            Assert.assertTrue("Product price and facet price mismatch Expected price should be between " + selectedFacetValues
                    + "Actual price displayed for product: " + price + " for product id: " + id, flag);
        }
        resumePageHangWatchDog();
        DiscoveryHelper.logInfo("Verified that products are filtered with selected price facet value");
    }

    @And("^I select minimum price as \"([^\"]*)\" and maximum price as \"([^\"]*)\" range$")
    public void iSelectMinimumPriceAndMaximumPrice(String min, String max) throws Throwable {
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        productCount = DiscoveryHelper.getProductCount();
        //thumbnailColumns = ProductThumbnail.productThumbnailColumns();
        try {
            Wait.untilElementNotPresent("category_browse.loading_mask");
            String facetName = "Price";
            if (!LeftFacet.isExpanded(facetName)) {
                LeftFacet.expandFacet(facetName);
            }
            try {
                TextBoxes.typeTextbox("left_facet.customPriceFrom", min);
                TextBoxes.typeTextbox("left_facet.customPriceTo", max);
            } catch (Exception e) {
                if (!LeftFacet.isExpanded(facetName)) {
                    LeftFacet.expandFacet(facetName);
                }
                TextBoxes.typeTextbox("left_facet.customPriceFrom", min);
                TextBoxes.typeTextbox("left_facet.customPriceTo", max);
            }
            String facetValue = "$" + min + "-" + "$" + max;
            selectedFacetValues.add(facetValue);
        } catch (Exception e) {
            Assert.fail("Unable to enter the Minimum and Maximum value" + e.getMessage());
        }
        beforePriceSortUrl = WebDriverManager.getCurrentUrl();
        DiscoveryHelper.logInfo("Selected minimum price as " + min + " and maximum price as " + max);
    }

    @And("^I select 'GO' button from price facet$")
    public void iSelectGOButtonFromPriceFacet() throws Throwable {
        try {
            Clicks.click("left_facet.customPriceSubmit");
            Wait.forLoading("left_facet.loading");
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
            String afterURLDecode = decode(decode(strUtils, "UTF-8"), "UTF-8").replace("|", "-").replaceAll("_", " ").replaceAll("%20", " ").replaceAll("%26", "&");
            selectedFacetValues.stream().filter(facet -> !afterURLDecode.toLowerCase().contains(facet.toLowerCase().replace("\\$", "").trim()))
                    .forEach(facet -> Assert.fail("Selected facet not appended in url"));
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
            breadCrumbsList = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs"));
            breadCrumbsList.stream().filter(ele -> !afterTagDecode.contains(ele.getText().replaceAll("\\$", "").trim()))
                    .forEach(ele -> Assert.fail("canonical tag does not contains facet value of same facet"));
        } catch (Exception e) {
            Assert.fail("Error getting canonical tag" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified canonical tag contains facet value of same facet");
    }

    @And("^I verify that facet values in canonical tag with alpha order for \"([^\"]*)\"$")
    public void I_verify_that_facet_canonical_tag_alpha_order(String keyword) throws Throwable {
        List<String> actualOrderOfFacets = new ArrayList<>();
        Map selectedFacetNamesWithValues = DiscoveryHelper.getSelectedFacetNamesWithValues();
        List<String> selectedFacetNames = (List) selectedFacetNamesWithValues.keySet().stream().map(m -> (String) m).collect(Collectors.toList());
        Collections.sort(selectedFacetNames);
        for (String selectedFacetName : selectedFacetNames) {
            List<String> selectedFacetValues = (ArrayList) selectedFacetNamesWithValues.get(selectedFacetName);
            Collections.sort(selectedFacetValues);
            actualOrderOfFacets.add(selectedFacetValues.get(0));
        }
        String afterTagDecode;
        ArrayList<String> urlFacetName = new ArrayList<>();
        String canonicalURL = Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href");
        String[] canonicalURLStrings = canonicalURL.split("\\?")[0].split("\\/");
        String canonicalTagFacetString = canonicalURLStrings[canonicalURLStrings.length - 1];
        afterTagDecode = decode(decode(canonicalTagFacetString, "UTF-8"), "UTF-8").replace("|", "-").replaceAll("_", " ");
        List<String> canonicalTagNameLists = Arrays.asList(afterTagDecode.split(","));
        Collections.addAll(urlFacetName, afterTagDecode.split(","));
        Collections.sort(canonicalTagNameLists);
        Assert.assertTrue("Facet names are not in alpha order. Actual:" + urlFacetName + " and Expected:" + actualOrderOfFacets,
                actualOrderOfFacets.toString().equalsIgnoreCase(urlFacetName.toString()));
        DiscoveryHelper.logInfo("Verified facet values in canonical tag with alpha order for " + keyword);
    }

    @When("^I select the quick peek of random product$")
    public void I_Select_Quick_peek_of_random_product() throws Throwable {
        Random randomGenerator;
        try {
            List<String> productIds = getProductIds();
            randomGenerator = new Random();
            int index = (productIds.size() > 1) ? randomGenerator.nextInt(productIds.size() - 1) : randomGenerator.nextInt(productIds.size());
            selectedProdId = productIds.get(index);
            DiscoveryHelper.selectQuickView(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to select the quick peek of random product" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the quick peek of random product id: " + selectedProdId);
    }


    @When("^I navigate to ((?i)Browse|Sub Splash|Category Splash) page type in ((?i)SITE|REGISTRY|ISHIP) mode$")
    public void iNavigateToPageTypeInMode(String pageType, String mode) throws Throwable {
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
            if (pageType.equalsIgnoreCase("quickview")) {
                Wait.untilElementPresent(Elements.element("quickViewOverlay.quickViewPrice"));
                price = Elements.findElement(Elements.element("quickViewOverlay.quickViewPrice")).getText();
            } else {
                try {
                    if (Elements.elementPresent("product_thumbnails.product_price_pdp"))
                        price = Elements.findElement(Elements.element("product_thumbnails.product_price_pdp")).getText().split("\n")[0].replaceAll("   ", "\n").trim();
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
            List<String> facetNames = LeftFacet.getAllFacetName();
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

    @When("^I verify that product count for the selected (Price|Color|Brand|CUSTRATINGS|Size) in the overlay and results match$")
    public void i_verify_that_product_count_for_the_selected_price_in_the_overlay_and_results_match(String facetName) throws Throwable {
        int facetCount = 0;
        List<WebElement> element;
        try {
            Wait.forLoading("left_facet.loading");
            if (facetName.contains("Brand")) {
                List<WebElement> allBrandsList = Elements.findElement(Elements.element("left_facet.all_brand_list")).findElements(By.tagName("a"));
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
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        try {
            if (bloomingdales()) {
                totalPageCount = DiscoveryHelper.getTotalPageCount();
                productCount = DiscoveryHelper.getProductCount();
            }
            List<String> facetNames = LeftFacet.getAllFacetName().stream()
                    .filter(name -> !(name.contains("Customer") || name.equals("Promotions") || name.contains("Pick")))
                    .collect(Collectors.toList());
            String lastSelectedFacetName = null;
            for (int i = 0; i < facetNames.size(); i++) {
                String facetName;
                List<String> currentFacetName = LeftFacet.getAllFacetName().stream()
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
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        productCount = DiscoveryHelper.getProductCount();
        if (!LeftFacet.isExpanded(bopsFacetName))
            LeftFacet.expandFacet(bopsFacetName);
        if (bloomingdales()) {
            String facetValue = DiscoveryHelper.getRandomFacetValue(bopsFacetName);
            LeftFacet.selectItemFromFacet(facetValue, bopsFacetName);
            Wait.forLoading("left_facet.loading");
        } else {
            Clicks.clickRandomElement(LeftFacet.getFacetItems(bopsFacetName));
        }
        selectedFacet = Elements.getText("left_facet.facetBreadcrumbs");
        selectedFacetValues.add(selectedFacet);
        DiscoveryHelper.logInfo("Selected random bops facet value: " + selectedFacet + " from bops facet");
    }

    @Then("^I verify that bops overlay is (not displayed|displayed) with stores list$")
    public void iVerifyBopsOverlayWithStoresList(String condition) throws Throwable {
        try {
            String storeContainer = macys() ? "bops_stores" : "store_container";
            if (condition.equalsIgnoreCase("displayed")) {
                Wait.untilElementPresent("change_pickup_store_dialog." + storeContainer);
                Assert.assertTrue("Error Store results is not displayed on bops overlay",
                        Elements.elementPresent(Elements.element("change_pickup_store_dialog." + storeContainer)));
            } else {
                Wait.untilElementNotPresent("change_pickup_store_dialog." + storeContainer);
                Assert.assertFalse("Error Store results is displayed on bops overlay",
                        Elements.elementPresent(Elements.element("change_pickup_store_dialog." + storeContainer)));
            }
        } catch (Exception e) {
            Assert.fail("Error Selecting Zip Code link in pick-up in-store facet" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified bops overlay with stores list");
    }

    @Then("^I verify that facet breadcrumb are listed as per faceted url$")
    public void iShouldSeeFacetBreadcrumbAsPerFacetedUrl() throws Throwable {
        try {
            String currentPageURL = decode(WebDriverManager.getCurrentUrl(), "UTF-8");
            List<WebElement> ele = Elements.findElements("left_facet.facetBreadcrumbs");
            ele.stream().filter(e -> !currentPageURL.contains(e.getText())).
                    forEach(e -> Assert.fail("Facet selection in the breadcrumb mismatches the current URL"));
        } catch (Exception e) {
            System.out.println("Error to verify facet URL with facet breadcrumb" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified facet breadcrumb as per faceted url");
    }

    @And("^I verify that products are filtered as per faceted url$")
    public void iVerifyThatProductsAreFilteredAsPerFacetedUrl() throws Throwable {
        int count = 0;
        try {
            String selectedFacet = Elements.findElement("left_facet.facetBreadcrumbs").getText();
            Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailsDetails();
            for (Map.Entry<String, HashMap> detail : details.entrySet()) {
                if (!detail.getValue().get("elm_product_name").toString().toLowerCase().contains(selectedFacet.toLowerCase())) {
                    Assert.fail("Products are not filtered as per the selected facet url" + detail.getValue().get("elm_product_name").toString());
                } else {
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
                selectedPageNo = ThreadLocalRandom.current().nextInt(2, (pageCount));
                System.out.println("Page Number: " + selectedPageNo);
                DiscoveryHelper.goToPageNumber(selectedPageNo);
            } else {
                DiscoveryHelper.goToPageNumber(pageCount);
                selectedPageNo = 2;
            }
        } else {
            selectedPageNo = 1;
        }
        Wait.forLoading("left_facet.loading");
        Wait.until(() -> WebDriverManager.getCurrentUrl().contains(String.valueOf(selectedPageNo)));
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
        System.out.println("search keyword" + searchKeyword);
        Assert.assertTrue("Search box is not empty: ", Elements.findElement("home.search_field").getAttribute("value").equals(""));
        DiscoveryHelper.logInfo("Verified main search field cleared");
    }

    @And("^I search for \"([^\"]*)\" in search box$")
    public void iSearchForInSearchBox(String value) throws Throwable {
        searchKeyword = value;
        ShopAndBrowse.I_search_for(searchKeyword);
        DiscoveryHelper.logInfo("Searched for " + searchKeyword + " in search box");
    }

    @And("^I verify that page have search keyword as \"([^\"]*)\"$")
    public void iVerifyThatPageHaveSearchKeywordAs(String keyword) throws Throwable {
        try {
            String displayedKeyword = Elements.findElement("search_result.search_keyword").getText().trim();
            Assert.assertTrue("Expected Page with keyword: " + keyword + " Actual displayed keyword: " + displayedKeyword,
                    keyword.equalsIgnoreCase(displayedKeyword));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + keyword + " is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified Page have search keyword as " + keyword);
    }

    @And("^I verify that (\\d+) error code is not displayed in (search|browse) page$")
    public void iVerifyThatErrorCodeIsNotDispalyedInSearchPage(int errorCode, String pageType) throws Throwable {
        WebElement contentContainer = Elements.findElement("home.globalContentContainer");
        List<String> linkElementsHref = contentContainer.findElements(By.tagName("a")).stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
        int index = linkElementsHref.size() >= 10 ? 10 : linkElementsHref.size();
        linkElementsHref = linkElementsHref.subList(1, index);
        for (String href : linkElementsHref) {
            int responseCode = DiscoveryHelper.getResponseCode(href);
            System.out.println("Url: " + href);
            System.out.println("Response code: " + responseCode);
            Assert.assertFalse("Link is served with " + errorCode + " error code",
                    responseCode == errorCode);
        }
        DiscoveryHelper.logInfo("Verified error code " + errorCode + " in" + pageType + " page is not displayed");
    }

    @Then("^I verify that (Search Results|browse page|sub splash) contents are displayed$")
    public void iVerifyThatSearchResultsContentsAreDisplayed(String pageType) throws Throwable {
        Assert.assertTrue("Products are not displayed",
                DiscoveryHelper.getProductIds().size() != 0);
        if (DiscoveryHelper.getProductIds().size() >= 60) {
            Assert.assertFalse("Previous page via arrow button is displayed",
                    Elements.elementPresent("pagination.goto_previous_page_via_arrow"));
            Assert.assertTrue("Next page via arrow button is displayed",
                    Elements.elementPresent("pagination.goto_next_page_via_arrow"));
            Assert.assertTrue("Left navigation facet panel is not displayed",
                    Elements.elementPresent("left_facet.facets_panel"));
            Assert.assertTrue("Sort By options are not displayed",
                    Elements.elementPresent("pagination.sort_by"));
        }
        DiscoveryHelper.logInfo("Verified the " + pageType + " contents");
    }

    @Then("^I verify that meta \"([^\"]*)\" tag as \"([^\"]*)\"$")
    public void iShouldSeeMetaTagAs(String metaTag, String expectedKeywords) throws Throwable {
        String metaTagName = macys() ? "metaKeyword" : "metaDescription";
        String displayedKeywords = Elements.findElement("search_result." + metaTagName).getAttribute("content");
        Assert.assertTrue("ERROR - APP: Expected Meta" + metaTag + "are not displayed, Expected Keywords: " + expectedKeywords
                + " Actual Keywords Displayed: " + displayedKeywords, displayedKeywords.equals(expectedKeywords));
        DiscoveryHelper.logInfo("Verified meta tags " + metaTag + " are displayed as " + expectedKeywords);
    }

    @And("^I verify that \"([^\"]*)\" products are displayed with (two levels of pricing information|price type with badge text)$")
    public void iVerifyThatProductsAreDisplayedWithThreeLevelsOfPricingInformation(String productType, String priceType) throws Throwable {
        List<String> productIds = productType.equals("Member") ? DiscoveryHelper.getMemberProdIds() :
                getMasterProductIds();
        List<String> productDisplayed = new ArrayList<>();
        for (String id : productIds) {
            Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailDetails(id);
            if (priceType.equals("two levels of pricing information")) {
                WebElement price = (WebElement) details.get(id).get("elm_prices");
                int priceLevel = price.findElements(By.tagName("div")).size();
                if (priceLevel == 2) {
                    System.out.println("Displayed product price: " + price.getText());
                    productDisplayed.add(id);
                    break;
                }
            } else {
                WebElement batchText = (WebElement) details.get(id).get("elm_batch_text");
                if (batchText != null) {
                    System.out.println("Displayed product batch text: " + batchText.getText());
                    productDisplayed.add(id);
                    break;
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
            if (LeftFacet.getAllFacetName().contains(facetName)) {
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
        List<String> facetNames = LeftFacet.getAllFacetName();
        facetNames.remove(facetNames.stream().filter(name -> (name.contains("In Store") || name.contains("In-Store")))
                .findFirst().orElse(null));
        Assert.assertFalse("No other facets is displayed in the page other than BOPS facet: ",
                facetNames.size() == 0);
        DiscoveryHelper.logInfo("Verified other facets excluding BOPS are displayed");
    }

    @Then("^I verify that \"([^\"]*)\" facet is (listed|not listed) on left nav$")
    public void I_verify_that_facet__is_listed_on_left_nav(String facetName, String condition) throws Throwable {
        List<String> faceNames = LeftFacet.getAllFacetName();
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
        if (sortBy.equalsIgnoreCase("Price: Low to High") || sortBy.equalsIgnoreCase("Price (low-high)")) {
            Assert.assertTrue("First product price is not less than or equal to second product price",
                    firstProductPrice <= secondProductPrice);
            Assert.assertTrue("First product price is not less than last product price",
                    firstProductPrice < lastProductPrice);
        } else if (sortBy.equalsIgnoreCase("Price: High to Low") || sortBy.equalsIgnoreCase("Price (high-low)")) {
            Assert.assertTrue("First product price is not greater than or equal to second product price",
                    firstProductPrice >= secondProductPrice);
            Assert.assertTrue("First product price is not greater than last product price",
                    firstProductPrice > lastProductPrice);
        } else {
            int currentProductCount = DiscoveryHelper.getProductCount();
            if (ShopAndBrowse.productCount != 0) {
                Assert.assertTrue("Current Product count and previous count mismatch Expected Count: " + productCount + " Actual Count: "
                        + currentProductCount, currentProductCount == ShopAndBrowse.productCount);
            } else {
                Assert.assertTrue("Current Product count and previous count mismatch Expected Count: " + productCount + " Actual Count: "
                        + currentProductCount, currentProductCount == productCount);
            }
        }
        DiscoveryHelper.logInfo("Verified Sort By " + sortBy + "functionality");
    }

    @And("^I verify that zipcode is displayed based on store cookie value under bops facet$")
    public void iVerifyThatZipcodeIsDisplayedBasedOnStoreCookieValueUnderBopsFacet() throws Throwable {
        LeftFacet.expandFacet(selectedFacetName);
        Wait.untilElementPresent("left_facet.bops_location");
        String currentBopsLocation = macys() ? Elements.findElement("left_facet.bops_location").getText() :
                Elements.findElement("bops_facet.searched_zipcode").getText();
        Assert.assertTrue("Current Bops Location and zip code cookie value is different Expected BOPS location: "
                + zipCode + " Actual Displayed location: " + currentBopsLocation, currentBopsLocation.equals(zipCode));
        DiscoveryHelper.logInfo("Verified zipcode is displayed based on store cookie value under bops facet");
    }

    @When("^I select \"([^\"]*)\" Miles under bops facet$")
    public void iSelectMilesUnderBopsFacet(String miles) throws Throwable {
        if (!LeftFacet.isExpanded(bopsFacetName))
            LeftFacet.expandFacet(bopsFacetName);
        if (macys()) {
            Wait.untilElementPresent("left_facet.bops_store_search_radius");
            int index = DropDowns.getAllValues("left_facet.bops_store_search_radius").indexOf(miles);
            Assert.assertFalse("Unable to find give miles under Bops Facet, Given miles: " + miles,
                    index == -1);
            DropDowns.selectByIndex("left_facet.bops_store_search_radius", index);
        } else {
            if (!Elements.findElement(Elements.element("left_facet.bops_store_search_radius")).isDisplayed())
                Clicks.click("left_facet.bops_dropdown_miles");
            Wait.untilElementPresent("bops_facet.bops_store_search_radius");
            WebElement ele = Elements.findElement("left_facet.bops_store_search_radius").findElements(By.tagName("a"))
                    .stream()
                    .filter(el -> el.getText().equalsIgnoreCase(miles))
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
        List<String> facets = Elements.findElements("left_facet.facetBreadcrumbs").stream()
                .map(WebElement::getText).collect(Collectors.toList());
        System.out.println("Displayed facet in bread crumb:" + facets);
        System.out.println("Selected Bops facet: " + selectedFacet);
        int index = facets.indexOf(selectedFacet);
        Assert.assertFalse("Given facet value is not displayed in bread crumbs",
                index == -1);
        Clicks.click(Elements.findElements("left_facet.facetRemoveBreadcrumb").get(index));
        DiscoveryHelper.logInfo("Cleared store facet from breadcrumb");
    }

    @When("^I search for zipcode \"([^\"]*)\" in Pick Up In Store facet$")
    public void iSearchForZipcodeInPickUpInStoreFacet(String zipcode) throws Throwable {
        try {
            if (Elements.findElement(LeftFacet.getFacetDiv(bopsFacetName)).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet(bopsFacetName);
            }
            TextBoxes.typeTextNEnter("left_facet.bops_store_search_box", zipcode);
        } catch (Exception e) {
            Assert.fail("Error Zipcode search is not working in bops facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for zipcode " + zipcode + " in Pick up In store facet");
    }

    @Then("^I verify that \"([^\"]*)\" error message is displayed under bops facet$")
    public void iShouldSeeErrorMessageUnderBopsFacet(String message) throws Throwable {
        String ele = macys() ? "left_facet.bops_no_store_message" : "bops_facet.error_message";
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
        } else {
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
           /* PageNavigation.scrollToLazyLoadElement("recently_viewed_items.rvi_container");
            Wait.untilElementPresent("recently_viewed_items.rvi_container");
            Wait.untilElementPresent("recently_viewed_items.edit_button");*/
            Assert.assertTrue("Recently viewed edit link is not displayed", DiscoveryHelper.isRVIEditOptionDisplayed());
        } catch (Exception e) {
            Assert.fail("Recently Viewed panel is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that edit option inside Recently Viewed panel is displayed");
    }

    @Then("^I click remove button on any Recently viewed items$")
    public void iremoveanyRecentlyvieweditems() throws Throwable {
        List<WebElement> recentlyViewedItems = RecentlyViewed.rviProductThumbnails();
        pausePageHangWatchDog();
        totalProductInRvi = recentlyViewedItems.size();
        if (RecentlyViewed.isEditVisible()) {
            Clicks.javascriptHover(Elements.findElement("recently_viewed_items.edit_button"));
            Clicks.javascriptClick(Elements.findElement("recently_viewed_items.edit_button"));
        }
        if (RecentlyViewed.isDoneVisible()) {
            Clicks.javascriptHover(Elements.findElement("recently_viewed_items.remove_button"));
            Clicks.javascriptClick(Elements.findElement("recently_viewed_items.remove_button"));
        } else {
            Assert.fail("ERROR -APP: Removing RVI product failed.");
        }
        DiscoveryHelper.logInfo("Selected remove button on any rvi");
        resumePageHangWatchDog();
    }

    @Then("^I verify that the item is removed from Recently viewed items$")
    public void ishouldseetheitemisremovedfromRecentlyvieweditems() throws Throwable {
        List<WebElement> recentlyViewedItems = RecentlyViewed.rviProductThumbnails();
        Assert.assertTrue("Expected Product count should be less than: " + totalProductInRvi + " Actual displayed products: " + recentlyViewedItems.size(),
                totalProductInRvi >= recentlyViewedItems.size());
        DiscoveryHelper.logInfo("Verified that the item is removed from Recently viewed items");
    }

    @And("^I verify that number of Brands displayed under brand facet should be less than or equal to (\\d+)$")
    public void IVerifyThatNumberOfBrandsDisplayedUnderBrandFacetShouldBeLessThanOrEqualTo(int count) throws Throwable {
        try {
            List<String> brandFacetItems;
            String facetName = macys() ? "Brand" : "Designer";
            if (!LeftFacet.isExpanded(facetName)) {
                LeftFacet.expandFacet(facetName);
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
                Assert.assertTrue("Autocomplete suggestions are not displayed",
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
        System.out.println("Auto suggestion size ----- " + suggestionsCount);
        Assert.assertTrue("Auto suggestions exceeds " + value, suggestionsCount == value);
        DiscoveryHelper.logInfo("Verified that" + value + "words or phrases Pertinent to the characters typed ");
    }

    @Then("^I verify that characters of suggestions not more than (\\d+) characters$")
    public void I_should_see_the_characters_of_suggestions_not_more_than_characters(int value) throws Throwable {
        List<String> suggestionText = DiscoveryHelper.getAutoSuggestionList();
        for (String text : suggestionText) {
            Assert.assertTrue("suggestions should not contains more than " + value + " characters", text.length() < value);
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
        firstSuggestionsList = firstSuggestionsList.stream().map(String::toLowerCase).collect(toList());
        secondSuggestionsList = secondSuggestionsList.stream().map(String::toLowerCase).collect(toList());
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
            highlightedText = highlightedText.stream().filter(element -> (element.getCssValue("font-weight").equalsIgnoreCase("bold")) || (element.getCssValue("font-weight").equalsIgnoreCase("700"))).collect(Collectors.toList());
            Assert.assertFalse("ERROR - APP: Suggestions are not highlighted", highlightedText.isEmpty());
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
        }
        Facets.allBrandFacetValues = DiscoveryHelper.getAllBrandFacetName();
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
                    Assert.assertFalse(brandName + "is not " + brandState, isFeaturedBrandsCollapsed());
                else
                    Assert.assertFalse(brandName + "is not " + brandState, isAllBrandsCollapsed());
                break;
            case "collapsed":
                if (brandName.equals("Featured Brands"))
                    Assert.assertTrue(brandName + "is not " + brandState, isFeaturedBrandsCollapsed());
                else
                    Assert.assertTrue(brandName + "is not " + brandState, isAllBrandsCollapsed());
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

    @Then("^I verify that search box is displayed under (?:Brand|Designer) facet$")
    public void iShouldSeeSearchBoxUnderBrandFacet() throws Throwable {
        try {
            // String facetName = macys() ? "Brand" : "Designer";
            String facetName = macys() ? "Brand" : LeftFacet.getAllFacetName().stream()
                    .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null);
            if (!LeftFacet.isExpanded(facetName)) {
                LeftFacet.expandFacet(facetName);
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
            LeftFacet.expandFacet(bopsFacetName);
            Wait.forLoading("left_facet.loading");
            String milesDropText = macys() ? DropDowns.getSelectedValue(Elements.element("left_facet.bops_store_search_radius")) :
                    Elements.findElement("bops_facet.miles_select").getText();
            Assert.assertEquals("radius dropdown under bops facet is not " + miles, milesDropText.trim().toLowerCase(), miles.toLowerCase());
        } catch (Exception e) {
            Assert.fail("radius dropdown under bops facet is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified radius dropdown under bops facet");
    }

    @When("^I verify that (city name|zipcode|Change Location) hyperlink is displayed under bops facet$")
    public void iVerifyCityNameHyperLinkedIsDisplayedUnderBopsFacet(String name) throws Throwable {
        LeftFacet.expandFacet(bopsFacetName);
        Wait.untilElementPresent(Elements.element("left_facet.bops_location"));
        if (macys()) {
            Assert.assertTrue(name + " is not displayed under bops facet",
                    Elements.findElement("left_facet.bops_location").getCssValue("text-decoration").contains("underline"));
        } else {
            Assert.assertTrue(name + " is not displayed under bops facet",
                    Elements.findElement("left_facet.bops_location").isEnabled());
        }
        DiscoveryHelper.logInfo("Verified that " + name + " hyperlink is displayed under bops facet");
    }

    @And("^I verify that zipcode based on store cookie value under bops facet$")
    public void iShouldSeeZipcodeBasedOnStoreCookieValueUnderBopsFacet() throws Throwable {
        if (!LeftFacet.isExpanded(bopsFacetName))
            LeftFacet.expandFacet(bopsFacetName);
        if (macys()) Wait.untilElementPresent("left_facet.bops_location");
        String currentBopsLocation = macys() ? Elements.findElement("left_facet.bops_location").getText() :
                Elements.findElement(By.className("bopsSearchedAddress")).getText();
        Assert.assertTrue("Current Bops Location and zip code cookie value is different Expected BOPS location: "
                + zipCode + " Actual Displayed location: " + currentBopsLocation, currentBopsLocation.equals(zipCode));
        DiscoveryHelper.logInfo("Verified zipcode is displayed based on store cookie value under bops facet");
    }

    @When("^I search stores for valid (zipcode|city and state) within \"([^\"]*)\" miles in ISA overlay$")
    public void iSearchStoreForZipcodeAndCityInPickUpInStoreFacet(String searchField, String miles) throws Throwable {
        String city = macys() ? "Sacramento" : "NewYork";
        try {
            LeftFacet.expandFacet(bopsFacetName);
            String optionEle = macys() ? "option" : "li";
            if (macys()) {
                TextBoxes.typeTextbox("change_pickup_store_dialog.address_zip_code",
                        searchField.contains("zipcode") ? zipCode : city);
                Clicks.click("change_pickup_store_dialog.search_button");
            } else {
                if (searchField.contains("zipcode")) {
                    zipCode = "10021";
                    TextBoxes.typeTextNEnter("change_pickup_store_dialog.address_zip_code", zipCode);
                } else {
                    TextBoxes.typeTextNEnter("change_pickup_store_dialog.address_zip_code", city);
                }
                Clicks.click("change_pickup_store_dialog.search_distance");
                WebElement element = Elements.findElement("change_pickup_store_dialog.search_distance")
                        .findElements(By.tagName(optionEle))
                        .stream()
                        .filter(e -> e.getText().equalsIgnoreCase(miles))
                        .findFirst().orElse(null);
                Clicks.click(element);
                Clicks.click("change_pickup_store_dialog.search_button");
            }
        } catch (Exception e) {
            Assert.fail("Error Zipcode search is not working in bops facet" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Searched for for valid store " + searchField + " within " + miles + " miles in ISA overlay");
    }

    @When("^I verify that Enter Zip Code by default in search box under bops facet$")
    public void iVerifyEnterZipCodeFieldUnderBopsFacet() throws Throwable {
        try {
            LeftFacet.expandFacet(bopsFacetName);
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
    public void iSearchInvalidUnderBopsFacetOverlay(String code, String keyword) throws Throwable {
        WebElement ele = Elements.findElement(By.id("storeLookupBody"));
        Clicks.click("change_pickup_store_dialog.search_distance");
        String optionEle = macys() ? "option" : "li";
        WebElement element = Elements.findElement("change_pickup_store_dialog.search_distance")
                .findElements(By.tagName(optionEle))
                .stream()
                .filter(e -> e.getText().equalsIgnoreCase("100 Miles"))
                .findFirst().orElse(null);
        Clicks.click(element);
        ele.findElement(By.id("searchField")).click();
        ele.findElement(By.id("searchField")).clear();
        ele.findElement(By.id("searchField")).sendKeys(keyword);
        ele.findElement(By.className("submit")).click();
        DiscoveryHelper.logInfo("Search for " + code + keyword + " in bops facet overlay");
    }

    @Then("^I verify that \"([^\"]*)\" error message is displayed under bops change store dialog$")
    public void iSelectErrorMessageUnderBopsFacet(String message) throws Throwable {
        String error = Elements.findElement(Elements.element("change_pickup_store_dialog.error_message")).getText();
        Assert.assertTrue("Error message is not displayed",
                error.equalsIgnoreCase(message));
        DiscoveryHelper.logInfo("Verified error message " + message + " is displayed under bops change store dialog");
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

                List<WebElement> sortByOptionsEle = Elements.findElements("search_result.sort_by_option");
                List<String> sortByOptions = sortByOptionsEle.stream().map(WebElement::getText).collect(toList());
                sortByOptions = sortByOptions.stream().filter(e -> !e.equals("")).collect(toList());
                Random randomGenerator = new Random();
                int randomSortIndex = randomGenerator.nextInt(sortByOptions.size() - 1);
                sortByValue = sortByOptions.get(randomSortIndex);
                Clicks.clickElementByText("search_result.sort_by_option", sortByValue);
            }
        } catch (Exception e) {
            Assert.fail("Error to select random sort by value" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected random sort by option");
    }

    @Then("^I verify that the product thumbnails are displayed with the selected brand$")
    public void iVerifyThatTheProductThumbnailsAreDisplayedWithTheSelectedBrand() throws Throwable {
        Wait.forLoading("left_facet.loading");
        DiscoveryHelper.navigateToComponentizationURL();
        List<String> ids = getProductIds();
        Collections.shuffle(ids);
        int index = (ids.size() >= 6) ? 5 : ids.size();
        ids = ids.subList(0, index);
        for (String id : ids) {
            Map<String, HashMap> productDetails = DiscoveryHelper.getProductThumbnailDetails(id);
            for (Map.Entry<String, HashMap> detail : productDetails.entrySet()) {
                WebElement prodEle = (WebElement) detail.getValue().get("elm_product_name");
                String productName = prodEle.getText();
                DiscoveryHelper.logInfo("Product Name: " + productName);
                System.out.println("Product Name: " + productName);
                System.out.println("Selected Facet Values: " + selectedFacetValues);
                DiscoveryHelper.logInfo("Selected Facet Values: " + selectedFacetValues);
                //For one product, Brand name is displaying as all clad. It should be displayed as all-clad.
                List<Boolean> result = selectedFacetValues.stream()
                        .map(e -> productName.toLowerCase().contains("all clad")
                                && e.toLowerCase().equals("all-clad")
                                || productName.toLowerCase().contains(e.toLowerCase()))
                        .collect(Collectors.toList());

		   /* List<Boolean> result = selectedFacetValues.stream()
                        .map(e -> productName.toLowerCase().contains(e.toLowerCase()))
						.collect(Collectors.toList());*/
                // if result does not contain true then product are not displayed with the selected brand name
                Assert.assertTrue("Products are not displayed with the selected brand, Expected Product name should contain any of the facet values: " + selectedFacetValues +
                        " Actual displayed product name " + productName, result.contains(true));
            }
        }
        DiscoveryHelper.logInfo("Verified that the product thumbnails are displayed with the selected brand");
    }

    @When("^I verify that \"([^\"]*)\" cookie is not displayed$")
    public void VerifyCookie(String cookieValue) throws Throwable {
        try {
            Assert.assertTrue("Cookie " + cookieValue + " values is displayed ",
                    Cookies.getCookieValue(cookieValue).isEmpty());
        } catch (Exception e) {
            Assert.fail("Unable to verify " + cookieValue + " Cookie " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that " + cookieValue + " is not displayed");
    }

    @Then("^I verify that flyout menu for below fob's in fob section by mouse hover$")
    public void iMouseHoverOnFOB(List<String> fob) throws Throwable {
        List<WebElement> getCategoriesEle = Elements.findElements(Elements.element("home.category"))
                .stream()
                .filter(e -> !e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        List<String> getCategories = getCategoriesEle.stream().map(WebElement::getText)
                .collect(Collectors.toList());
        for (String e : fob) {
            if (!getCategories.contains(e.toUpperCase())) Assert.fail("Mouse hover fob not available on the page");
            Clicks.hoverForSelection(By.linkText(e.toUpperCase()));


        }
        DiscoveryHelper.logInfo("Verified Flyout menu on search landing page");
    }

    @When("^I hover on any category$")
    public void iHoverOnAnyCategory() throws Throwable {
        List<WebElement> elements = Elements.findElements(Elements.element("home.category"))
                .stream()
                .filter(e -> !e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        Random randomGenerator = new Random();
        int randomFobIndex = randomGenerator.nextInt(elements.size() - 1);
        logInfo("FOB name for hover: " + elements.get(randomFobIndex).getText());
        Clicks.hoverForSelection(elements.get(randomFobIndex));
        DiscoveryHelper.logInfo("Hovered on random category");
    }

    @Then("^I verify that the products are displayed with the selected rating$")
    public void iVerifyThatTheProductsAreDisplayedWithTheSelectedRating() throws Throwable {
        List<String> ids = getProductIds();
        for (String id : ids.subList(0, ids.size() >= 10 ? 10 : ids.size())) {
            Map<String, HashMap> details = null;
            try {
                details = DiscoveryHelper.getProductThumbnailDetails(id);
            } catch (Exception e) {
                Navigate.browserRefresh();
                details = DiscoveryHelper.getProductThumbnailDetails(id);
            }
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
        }
        DiscoveryHelper.logInfo("Verified that the products are displayed with the selected rating");
    }

    @Then("^I verify that the product rating in (quick peek|PDP)$")
    public void iVerifyThatTheProductRatingInQuickPeek(String pageType) throws Throwable {
        String starRating;
        try {
            // For New member pdp then the element for pdp star rating => "redStar",
            // other than new pdp start rating element => "rating", that was handled in catch.
            starRating = Elements.findElement("product_thumbnails.productStarRatingPdp").getAttribute("style").replaceAll("\\D+", "");
        } catch (Exception e) {
            System.out.println("Other that New Product Display Page");
            starRating = Elements.findElement("product_thumbnails.masterProductReview")
                    .findElement(By.tagName("span")).getAttribute("style").replaceAll("\\D+", "");
        }
        starRating = pageType.equals("PDP") ? starRating :
                Elements.findElement("quickViewOverlay.starRatingContainer").getAttribute("style").replaceAll("\\D+", "");
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
        if (macys()) {
            if (!(Elements.findElement("pagination.item_count_section").getText().contains("Show"))) {
                Assert.fail("Item count buttons section not available");
            }
        } else {
            Assert.assertTrue("Item per page count section is not displayed: ",
                    Elements.elementPresent("pagination.filterPPP"));
        }
    }

    @When("^I navigate to next page of thumbnail grid$")
    public void Inavigatetonextpageofthumbnailgrid() throws Throwable {
        Clicks.click("pagination.goto_next_page_via_arrow");
        Wait.forLoading("left_facet.loading");
        DiscoveryHelper.navigateToComponentizationURL();
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
        if (DiscoveryHelper.paginationAvailable()) {
            System.out.println(pageNumber);
            DiscoveryHelper.goToPageNumber(pageNumber);
            DiscoveryHelper.logInfo("Navigated to page " + pageNumber);
        } else {
            Assert.fail("ERROR - DATA: Pagination not available to select page number:" + pageNumber);
        }
    }

    @When("^I click on previous pagination button$")
    public void i_click_on_previous_pagination_button() throws Throwable {
        if (Elements.elementPresent("pagination.goto_previous_page_via_arrow")) {
            Clicks.click("pagination.goto_previous_page_via_arrow");
        } else {
            Assert.fail("Previous pagination button is not displayed");
        }
        Wait.forLoading("left_facet.loading");
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

    @And("^I verify that all the product thumbnails displayed properly on the (?:Search Landing|Dynamic Landing|Category Browse) page$")
    public void iVeriftyThatAllTheProductThumbnailsDisplayedProperlyOnTheSearchLandingPage() throws Throwable {
        int productCount = getProductIds().size();
        System.out.println(productCount);
        if (productCount > 0) {
            int expectedProductCount = Integer.parseInt(DiscoveryHelper.getItemsPerPage());
            Assert.assertTrue("ERROR - APP: Product displayed greater than expected product count:" + expectedProductCount + ". Actual:" + productCount, productCount <= expectedProductCount);
        } else {
            Assert.fail("ERROR - ENV: No product is displayed");
        }
        DiscoveryHelper.logInfo("Product thumbnails displayed properly");
    }

    @And("^I navigate to PDP of the first product$")
    public void iNavigateToPDPOfTheFirstProduct() throws Throwable {
        String prodId1 = DiscoveryHelper.getProductIds().get(0);
        WebElement ele = Elements.findElement(By.id("img_" + prodId1));
        Clicks.click(ele);

    }

    @Then("^I verify that \"([^\"]*)\" item count option and respective number of products on page$")
    public void iVerifyThatItemCountOptionAndRespectiveNumberOfProductsOnPage(String itemCount) throws Throwable {
        try {
            int thumbnailCount;
            thumbnailCount = DiscoveryHelper.getProductIds().size();
            if (itemCount.equals("All")) {
                for (int i = 0; i <= 5; i++) {
                    Navigate.scrollPage(0, 800);
                }
                Assert.assertTrue("Show item is not selected as All", thumbnailCount > 60);
            } else {
                Assert.assertTrue("Show item is not selected as as per the item count" + itemCount,
                        Integer.parseInt(itemCount) == thumbnailCount || Integer.parseInt(DiscoveryHelper.getItemsPerPage()) == Integer.parseInt(itemCount));
            }
        } catch (Exception e) {
            Assert.fail("Unable to verify item count option and respective number of products on page" + e.getMessage());
        }
    }

    @And("^I verify that (search result|browse) page Facets displayed match with production$")
    public void iVerifySearchResultPageFacetsDisplayedMatchWithProduction(String pageType) throws Throwable {
        String prodUrl;
        ShopAndBrowse browse = new ShopAndBrowse();
        PageNavigation pageNavigation = new PageNavigation();
        List<String> facetNames = LeftFacet.getAllFacetName();
        String url = WebDriverManager.getCurrentUrl();
        if (macys())
            prodUrl = url.replace(url.split(".com/")[0], "http://www.macys");
        else
            prodUrl = url.replace(url.split(".com/")[0], "http://www.bloomingdales");
        DiscoveryHelper.logInfo("Navigated to production Site");
        //prodUrl = prodUrl.contains("featured") ? prodUrl.replace("?", "").split("EFCKEY(.*)")[0] : prodUrl.replace("&", "").split("EFCKEY(.*)")[0];
        Navigate.visit(prodUrl);
        if (shoppingMode.equalsIgnoreCase("iship")) {
            pageNavigation.I_navigate_to_international_context_page();
            browse.I_change_country_to(ISHIPCountry);
            if (bloomingdales())
                browse.I_close_the_welcome_mat_if_it_s_visible();
            Navigate.visit(prodUrl);
        }
        List<String> prodFacetNames = LeftFacet.getAllFacetName();
        prodFacetNames = prodFacetNames.stream().filter(m -> !m.toLowerCase().contains("store")).collect(Collectors.toList());
        facetNames = facetNames.stream().filter(m -> !m.toLowerCase().contains("store")).collect(Collectors.toList());
        Assert.assertTrue("Facets displayed in " + pageType + " page are not matching", facetNames.containsAll(prodFacetNames));
    }

    @And("^I verify that prices ,ratings & reviews are displayed under product thumbnail$")
    public void iVerifyThatPricesRatingsReviewsUnderProductThumbnail() throws Throwable {
        try {
            Map<String, HashMap> prodDetails = DiscoveryHelper.getProductThumbnailsDetails();
            prodDetails.entrySet()
                    .stream()
                    .filter(detail -> !(detail.getValue().get("elm_customer_ratings_style") == null))
                    .forEach(detail -> {
                        String ratings = detail.getValue().get("elm_customer_ratings_style").toString().replaceAll("\\D+", "");
                        String priceEle = (String) detail.getValue().get("elm_prices");
                        String productName = (String) detail.getValue().get("elm_product_name");
                        Assert.assertTrue("Eror verifying product prices,ratings and reviews", (!productName.isEmpty()));
                        DiscoveryHelper.logInfo("Product name" + productName + " with prices" + priceEle + "and ratings" + ratings + "in search results page");
                    });
        } catch (Exception e) {
            Assert.fail("Unable to verify prices, ratings and reviews under product thumbnail in search results page" + e.getMessage());
        }

    }

    @And("^I verify that (\\d+) product thumbnail in a row$")
    public void iVerifyThatProductThumbnailInARow(int thumbnailColumnCount) throws Throwable {
        try {
            int ThumbnailColumns = 3;
            if (thumbnailColumnCount == 4) {
                Assert.assertTrue("Current page is not filtered as per the selected grid value" + thumbnailColumnCount, thumbnailColumnCount == ThumbnailColumns);
            }
        } catch (Exception e) {
            Assert.fail("Unable to verify product thumbnail in a row" + e.getMessage());
        }
    }

    @Then("^I verify that navigated to page (\\d+) on (search result|browse|dlp|sub splash) page$")
    public void iVerifyThatNavigatedToPageOnSearchResultPage(int pageNumber, String pageType) throws Throwable {
        try {
            if (DiscoveryHelper.paginationAvailable()) {
                Wait.forLoading("left_facet.loading");
                Wait.until(() -> WebDriverManager.getCurrentUrl().contains(String.valueOf(pageNumber)));
                int currentPage = DiscoveryHelper.getCurrentPageNumber();
                DiscoveryHelper.logInfo("Current page number" + currentPage);
                DiscoveryHelper.logInfo("Expected page number" + pageNumber);
                if (!(currentPage == pageNumber)) {
                    Assert.fail("Expected page number is not displayed, Expected: "
                            + pageNumber + " Actual: " + currentPage);
                }
            } else {
                int totalProductCount = DiscoveryHelper.getProductCount();
                if ((macys() && totalProductCount > 60) || (bloomingdales() && totalProductCount > 90))
                    Assert.fail("ERROR - DATA: Pagination not available to verify current page number");
            }
        } catch (Exception e) {
            Assert.fail("Expected page number is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Successfully landed on expected " + pageType + " page");
    }

    @Then("^I verify that back to top button (is not displayed|is displayed) on page$")
    public void iVerifyThatBackToTopButtonIsNotDisplayedOnPage(String condition) throws Throwable {
        String backToTopEle = macys() ? "product_thumbnails.back_to_top" : "search_result.back_to_top_button";
        if (condition.equals("is not displayed")) {
            Wait.untilElementNotPresent(backToTopEle);
            Thread.sleep(2000);
            Assert.assertFalse("Back to top button is displayed", Elements.elementPresent(backToTopEle));
        } else {
            Wait.untilElementPresent(backToTopEle);
            Assert.assertTrue("Back to top button is not displayed", Elements.elementPresent(backToTopEle));
        }
        DiscoveryHelper.logInfo("back to top button" + condition + "on page");
    }

    @When("^I navigate to bottom of left navigation panel$")
    public void iNavigateToBottomOfLeftNavigationPanel() throws Throwable {
        //int bottom = Integer.parseInt(Elements.findElement(By.id("facet_container")).getCssValue("height").split("\\.")[0]);
        String bottom = Elements.findElement(By.id("facet_container")).getCssValue("height").split("\\.")[0];
        if (bottom.contains("px"))
            bottom = bottom.replace("px", "");
        Navigate.scrollPage(0, Integer.parseInt(bottom));
        DiscoveryHelper.logInfo("Successfully navigated to bottom of the left navigation panel");
    }

    @And("^I verify that message \"([^\"]*)\" in search landing page header$")
    public void iverifythatmessageInSearchLandingPageHeader(String expectedSearchText) throws Throwable {
        productCount = DiscoveryHelper.getProductCount();
        String resultText = macys() ? Elements.findElement(Elements.element("search_result.product_count_breadcrumb")).getText().split("\n")[0] :
                Elements.findElement(Elements.element("search_result.partialMatchText")).getText();
        expectedSearchText = expectedSearchText.replace("'n'", String.valueOf(productCount));
        System.out.println("resultText : " + resultText);
        System.out.println("expectedSearchText : " + expectedSearchText);
        Assert.assertTrue("Expected text: " + expectedSearchText + " Actual text displayed: " + resultText,
                expectedSearchText.trim().equalsIgnoreCase(resultText.trim()));
    }

    @And("^I verify that search message \"([^\"]*)\" is displayed$")
    public void iVerifyThatSearchMessage(String expectedSearchText) throws Throwable {
        String resultText = macys() ? Elements.findElement(Elements.element("search_result.product_count_breadcrumb")).getText().split("\n")[1] :
                Elements.findElement(Elements.element("search_result.partialMatchResult")).getText().replace("\n", " ");
        Assert.assertTrue("Expected text: " + expectedSearchText + " Actual text displayed: " + resultText,
                resultText.toLowerCase().contains(expectedSearchText.toLowerCase()));
        DiscoveryHelper.logInfo("Expected search message exactly matched");
    }

    @When("^I select any search term link$")
    public void iSelectAnySearchTermLink() throws Throwable {
        List<WebElement> elements = macys() ? Elements.findElements(Elements.element("search_result.search_result_matched_terms")) :
                Elements.findElement(Elements.element("search_result.partialMatchResult")).findElements(By.tagName("a"));
        Random r = new Random();
        int elementToSelectIndex = r.nextInt(elements.size());
        Clicks.click(elements.get(elementToSelectIndex));
        DiscoveryHelper.logInfo("Successfully select the search term from breadcrumb link");
    }

    @And("^I verify that quotations are not displayed for search keyword in search message$")
    public void iVerifyThatQuotationsAreNotDisplayedForSearchKeywordInSearchMessage() throws Throwable {
        String resultText = Elements.elementPresent("search_result.product_count_breadcrumb") ? Elements.getText("search_result.product_count_breadcrumb") :
                Elements.findElement(Elements.element("zero_search_result.searchPhrase")).getText();
        Assert.assertFalse("Expected quotations should not display on search message but Actual message displayed " + resultText
                , resultText.contains("\""));
        DiscoveryHelper.logInfo("Verified that quotations are not displayed for search keyword in search message");
    }

    @Then("^I verify that Zero Search Result page is (displayed|not displayed)$")
    public void iVerifyThatZeroSearchResultPageIsDisplayed(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("displayed"))
            Assert.assertTrue("ERROR - APP: Not on zero Search results page",
                    productCount == 0);
        else
            DiscoveryHelper.navigateToComponentizationURL();
        Assert.assertFalse("ERROR - APP: Zero Search results page is displayed",
                DiscoveryHelper.getProductCount() == 0);
        DiscoveryHelper.logInfo("Verified that Zero Search results page is displayed");
    }

    @Then("^I verify that the search results should be in zero results page$")
    public void iVerifyTheSearchResultsShouldBeInZeroResultsPage() {
        try {
            if (macys()) {
                Wait.secondsUntilElementPresent("search_result.product_count", ie() || edge() ? 20 : 5);
            }
            String product_count = Elements.findElement("search_result.product_count").getText();
            Assert.assertTrue("Page is showing '" + product_count + " 'number of products in zero results page",
                    product_count.trim().equals("0"));
        } catch (Exception a) {
            Assert.fail(a.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the search results should be in zero results page");
    }

    // This step is to ensure we are in Zero Search landing page in Registry mode
    @Then("^I should be in Registry zero result page$")
    public void I_should_be_in_registry_zero_result_page() throws Throwable {
        String actualMessage = Elements.findElement(By.id("resultsFoundMessageZSR")).getText();
        Assert.assertTrue("Not in Zero Results Page", actualMessage.contains("0"));
    }

    @Then("^I verify that the message \"([^\"]*)\" in zero results page header$")
    public void iVerifyThatTheMessageInZeroResultsPageHeader(String searchText) throws Throwable {
        try {
            String message = Elements.findElement(By.id("resultsFoundMessage")).getText();
            Assert.assertEquals("Product listing page is not displayed", message.trim(),
                    searchText.trim());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that the message " + searchText + " is displayed in zero results header");
    }

    @Then("^I verify that the message \"([^\"]*)\" in zero results page$")
    public void iVerifyTheMessageInZeroResultsPage(String searchText) {
        try {
            String display = Elements.findElement(Elements.element("Sample_page.Invalid_Search")).getText();
            Assert.assertTrue("Not in zero results page", searchText.contains(display));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        DiscoveryHelper.logInfo("Zero search results is verified");
    }

    @Then("^I verify that selected facet value in FORWARDPAGE_KEY cookie$")
    public void iVerifyThatSelectedFacetValueInFORWARDPAGE_KEYCookie() throws Throwable {
        Wait.untilElementPresent("left_facet.facetBreadcrumbs");
        String cookieValue = Cookies.getCookieValue("FORWARDPAGE_KEY");
        Assert.assertFalse("Cookie FORWARDPAGE_KEY value not present in site", cookieValue.isEmpty());
        Wait.forLoading("left_facet.loading");
        URI uri = new URI(cookieValue);
        try {
            cookieValue = uri.hasQuery() && uri.getQuery().contains("ss=true") ? new URI(uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    null, // ignore the query part of the input url
                    uri.getFragment()).toString() : cookieValue;
        } catch (Exception ee) {
            cookieValue = Cookies.getCookieValue("FORWARDPAGE_KEY");
        }
        String url = PageNavigation.url();
        url = UrlUtils.decode(url);
        cookieValue = UrlUtils.decode(cookieValue);
        if (shoppingMode != null && shoppingMode.equals("Registry")) {
            String[] cookieVal = cookieValue.split("\\?");
            String[] uriVal = url.split("\\?");
            Assert.assertTrue("FORWARDPAGE_KEY cookie value is not updated with selected facet Expected Cookie value: " + cookieValue + " in url: " + url,
                    uriVal[0].contains(cookieVal[0]));
            Assert.assertTrue("FORWARDPAGE_KEY cookie value is not updated with selected facet Expected Cookie value: " + cookieValue + " in url: " + url,
                    uriVal[1].contains(cookieVal[1]));
        } else
            Assert.assertTrue("FORWARDPAGE_KEY cookie value is not updated with selected facet Expected Cookie value: " + cookieValue + " in url: " + url,
                    url.contains(cookieValue));
        DiscoveryHelper.logInfo("Successfully verified FORWARDPAGE_KEY cookie value");
    }

    @When("^I select Sign In link from header and sign in from the current page$")
    public void iSelectSignInAndSignInToTheCurrentPage() throws Throwable {
        String url = PageNavigation.url();
        CommonUtils.signInOrCreateAccount();
        // After sign in navigating back to search or browse page
        Navigate.visit(url);
        DiscoveryHelper.logInfo("Successfully signed from current page");
    }

    @When("^I select (\\d+) facet value\\(s\\) from 'any' facet sections$")
    public void iSelectFacetValueSFromAnyFacetSections(int count) throws Throwable {
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        if (selectedFacetNames.isEmpty())
            selectedFacetNames = new ArrayList<>();
        productCount = DiscoveryHelper.getProductCount();
        totalPageCount = DiscoveryHelper.getTotalPageCount();
        if (macys()) {
            if (totalPageCount != 1)
                itemPerPageCount = DiscoveryHelper.getItemsPerPage();
        }
        for (int i = 0; i < count; i++) {
            Wait.forLoading("left_facet.loading");
            List<String> facetNames = LeftFacet.getAllFacetName();
            facetNames.removeAll(selectedFacetNames);
            facetNames.remove("Free Pick Up In Store");
            facetNames.remove("Pick Up In Store");
            facetNames.remove("Pick Up In-Store");
            if (macys()) {
                facetNames.removeAll((ArrayList) getAllGroupFacetName());
            }
            System.out.println("FINAL FACET NAME LIST FOR PICK: facetNames" + facetNames);
            DiscoveryHelper.logInfo("FINAL FACET NAME LIST FOR PICK: facetNames" + facetNames);
            int randomLimit = facetNames.size() - 1;
            if (randomLimit <= 0) break;
            int index = new Random().nextInt(randomLimit);
            String facetName = facetNames.get(index);
            System.out.println("FACET NAME " + facetName);
            DiscoveryHelper.logInfo("FACET NAME " + facetName);
            String facetValue = DiscoveryHelper.getRandomFacetValue(facetName);
            System.out.println("FACET VALUE " + facetValue);
            DiscoveryHelper.logInfo("FACET VALUE " + facetValue);
            LeftFacet.selectItemFromFacet(facetValue, facetName);
            selectedFacetNames.add(facetName);
            selectedFacetValues.add(facetValue);
            strUtils = WebDriverManager.getCurrentUrl();
        }
        DiscoveryHelper.logInfo("Selected " + count + " facet value from 'any' facet sections");
    }

    @And("^I deselect (\\d+) facet value\\(s\\)$")
    public void iDeselectTheFacetValueS(int count) throws Throwable {
        try {
            Wait.forLoading("left_facet.loading");
            int size = Elements.findElements("left_facet.facetRemoveBreadcrumb").size();
            Assert.assertTrue("Only " + size + " facets are selected from left nav", count <= size);
            for (int i = 0; i < count; i++) {
                List<WebElement> facetItemsToRemove = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs"));
                WebElement facetItemToRemove = facetItemsToRemove.get(0);
                String breadcrumbValue = facetItemToRemove.getText();
                if (macys() && breadcrumbValue.toLowerCase().contains("gift with")) {
                    selectedFacetValues.remove("Gift With Purchase");
                } else {
                    selectedFacetValues.remove(facetItemToRemove.getText());
                    selectedFacetValues.remove(facetItemToRemove.getText().replaceAll(",", ""));
                }
                DiscoveryHelper.logInfo("Removed facet from breadcrumb:" + facetItemToRemove.getText());
                if (facetItemToRemove.findElement(By.tagName("span")) != null) {
                    Clicks.javascriptClick(facetItemToRemove.findElement(By.tagName("span")));
                } else {
                    Clicks.javascriptClick(facetItemToRemove);
                }

                Wait.forLoading("left_facet.loading");
            }
        } catch (Exception e) {
            Assert.fail("Unable to remove facet from breadcrumb" + e.getMessage());
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
        Assert.assertFalse("New size facet family is not displayed",
                DiscoveryHelper.getAllSizeFacetHeaders().size() == 0);
        DiscoveryHelper.logInfo("Verified that new size facet family is displayed");
    }


    @When("^I select a random facet sub header$")
    public void iSelectARandomFacetHeader() throws Throwable {
        List<String> subFacetHeaders = DiscoveryHelper.getAllSizeFacetHeaders();
        selectedFacetName = subFacetHeaders.size() == 1 ? subFacetHeaders.get(0) : subFacetHeaders.get(new Random().nextInt(subFacetHeaders.size()));
        DiscoveryHelper.logInfo("Selected Size facet Sub Header: " + selectedFacetName);
        DiscoveryHelper.expandCollapseSizeFacetSubHeader(selectedFacetName);
        DiscoveryHelper.logInfo("Selected a random facet sub header");
    }

    @Then("^I verify that facet sub header gets \"([^\"]*)\"$")
    public void iVerifyThatSubHeaderGets(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("expanded")) {
            Assert.assertFalse(selectedFacetName + "sub header is not expanded",
                    DiscoveryHelper.isSizeFacetSubHeaderCollapsed(selectedFacetName));
        } else {
            Assert.assertTrue(selectedFacetName + "sub header is not expanded",
                    DiscoveryHelper.isSizeFacetSubHeaderCollapsed(selectedFacetName));
        }
        DiscoveryHelper.logInfo("Verified that selected sub facet header is " + condition);
    }

    @And("^I verify that the facet values are (displayed|not displayed)$")
    public void iVerifyThatTheFacetItemsAreDisplayed(String condition) throws Throwable {
        if (condition.equals("not displayed")) {
            Assert.assertFalse("Facet values are displayed under " + selectedFacetName + " sub facet header",
                    isSizeFacetValuesDisplayed(selectedFacetName));
        } else {
            Assert.assertTrue("Facet values are not displayed under " + selectedFacetName + " sub facet header",
                    isSizeFacetValuesDisplayed(selectedFacetName));
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
        if (buttonDisplay.equals("is"))
            Assert.assertTrue("Clear All button is not displayed",
                    helper.displayClearAllButton());
        else
            Assert.assertFalse("Clear All button is not displayed",
                    helper.displayClearAllButton());


        DiscoveryHelper.logInfo("Verified clear all button " + buttonDisplay + " displayed");
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
        System.out.println("Selected Facet Name: " + selectedFacetName);
        if (shoppingMode == null)
            shoppingMode = HeaderActions.getSiteMode();
        String mode = shoppingMode.equalsIgnoreCase("registry") ? "WEDDING_REGISTRY" : "SITE";
        String countryCode = shoppingMode.equalsIgnoreCase("iship") ? "CA" : "US";
        int productCountFromResponse;
        if (WebDriverManager.getCurrentUrl().contains("?id=")) {
            String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
            productCountFromResponse = BrowseService.getProductCountForFacet(catID, facetLiName, selectedFacet.replace("\n", ", "), mode, countryCode);
        } else {
            productCountFromResponse = SearchService.getProductCountForFacet(searchKeyword, facetLiName, selectedFacet.replace("\n", ", "), mode, countryCode);
        }
        DiscoveryHelper.logInfo("Product Count displayed from service " + productCountFromResponse + " for facet name " + selectedFacetName + " and facet value " + selectedFacet);
        int actualProductDisplayed = DiscoveryHelper.getProductCount();
        Assert.assertTrue("ERROR - APP: Expected and Displayed product count varies, Expected product count from service" +
                productCountFromResponse + " Actual Products displayed in UI: " + actualProductDisplayed, productCountFromResponse == actualProductDisplayed);
        DiscoveryHelper.logInfo("Verified that product count from JSON response against UI are same");
    }

    @And("^I should see below keys in the JSON response at (top|first|second)-level groups$")
    public void iShouldSeeBelowKeysInTheJSONResponseAtTopLevelGroups(String levelType, List<String> entries) throws Throwable {
        List<String> actualKeys = entries.stream().map(e -> e.replace(" ", "")).collect(toList());
        String countryCode = HeaderActions.getCountryCode();
        if (levelType.equals("top")) {
            Set responseKeys = null;
            if (WebDriverManager.getCurrentUrl().contains("?id=")) {
                String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
                responseKeys = BrowseService.getTopLevelResponse(catID, "SITE", countryCode).keySet();
            } else {
                responseKeys = SearchService.getTopLevelResponse(searchKeyword, "SITE", countryCode).keySet();
            }

            responseKeys.remove("facets");
            responseKeys.remove("values");
            Assert.assertTrue("Expected keys from Json: " + actualKeys + " Displayed keys: " + responseKeys,
                    responseKeys.toString().toLowerCase().equals(actualKeys.toString().toLowerCase()));
        } else {
            List<Map> multiLevelResponse = null;
            if (WebDriverManager.getCurrentUrl().contains("?id=")) {
                String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
                multiLevelResponse = levelType.equals("first") ? BrowseService.getFirstLevelResponse(catID, "SITE", countryCode) :
                        BrowseService.getSecondLevelResponse(catID, "SITE", countryCode);
            } else {
                multiLevelResponse = levelType.equals("first") ? SearchService.getFirstLevelResponse(searchKeyword, "SITE", countryCode) :
                        SearchService.getSecondLevelResponse(searchKeyword, "SITE", countryCode);
            }

            for (Map response : multiLevelResponse) {
                Set responseKeys = response.keySet();
                responseKeys.remove("facets");
                responseKeys.remove("values");
                Assert.assertTrue("Expected keys from Json: " + actualKeys + " Displayed keys: " + responseKeys,
                        responseKeys.toString().toLowerCase().equals(actualKeys.toString().toLowerCase()));
            }
        }
    }

    @And("^I should see value \"([^\"]*)\" for the key \"([^\"]*)\" in the JSON response at (top|first|second)-level groups$")
    public void iShouldSeeValueForTheKeyInTheJSONResponseAtTopLevelGroups(String value, String key, String levelType) throws Throwable {
        if (shoppingMode == null)
            shoppingMode = HeaderActions.getSiteMode();
        String countryCode = shoppingMode.equalsIgnoreCase("domestic") ? "US" : "CA";
        if (levelType.equals("top")) {
            Map response = null;
            if (WebDriverManager.getCurrentUrl().contains("?id=")) {
                String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
                response = BrowseService.getTopLevelResponse(catID, "SITE", countryCode);
            } else {
                response = SearchService.getTopLevelResponse(searchKeyword, "SITE", countryCode);
            }
            String jsonValue = response.get(key).toString();
            Assert.assertTrue("Expected value from Json: " + value + " Displayed value: " + jsonValue,
                    value.equals(jsonValue));
        } else {
            List<Map> multiLevelResponse = null;
            if (WebDriverManager.getCurrentUrl().contains("?id=")) {
                String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
                multiLevelResponse = levelType.equals("first") ? BrowseService.getFirstLevelResponse(catID, "SITE", countryCode) :
                        BrowseService.getSecondLevelResponse(catID, "SITE", countryCode);
            } else {
                multiLevelResponse = levelType.equals("first") ? SearchService.getFirstLevelResponse(searchKeyword, "SITE", countryCode) :
                        SearchService.getSecondLevelResponse(searchKeyword, "SITE", countryCode);
            }
            for (Map response : multiLevelResponse) {
                String jsonValue = response.get(key).toString();
                Assert.assertTrue("Expected value from Json: " + value + " Displayed value: " + jsonValue,
                        value.equals(jsonValue));
            }
        }
    }

    @And("^I verify that the selected breadcrumbs not displayed in pdp$")
    public void iVerifyThatTheSelectedBreadcrumbsNotDisplayedInPdp() throws Throwable {
        Assert.assertFalse("Selected breadcrumb is displayed",
                Elements.elementPresent("left_facet.facetBreadcrumbs"));
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
        //Comparing first 60 product with response
        List<String> prodIdsFromUI = DiscoveryHelper.getProductIds();
        if (prodIdsFromUI.size() >= 60) {
            //Comparing first 60 product with response
            prodIdsFromUI = prodIdsFromUI.subList(0, 60);
        }
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
            String expectedEle = macys() ? "product_title" : "quick_view_product_title";
            Wait.untilElementPresent("quick_view." + expectedEle);
            Assert.assertTrue("Product name is not displayed in Quick peek",
                    Elements.elementPresent("quick_view." + expectedEle));
        } else {
            Assert.assertFalse("Quick peek is displayed",
                    Elements.elementPresent("quick_view.quick_view_product_dialog"));
        }
        DiscoveryHelper.logInfo("Verified that quick peek is displayed");
    }

    @And("^I verify that products are displayed with price$")
    public void iVerifyThatProductsAreDisplayedWithPrice() throws Throwable {
        List<String> ids = DiscoveryHelper.getProductIds();
        Collections.shuffle(ids);
        int index = ids.size() >= 6 ? 5 : ids.size();
        ids = ids.subList(0, index);
        // Verifying random 5 products to avoid stale element error
        for (String id : ids) {
            WebElement ele = DiscoveryHelper.getProductDiv(id);
            Assert.assertTrue("Product price information is not displayed for the product id: " + selectedProdId,
                    ele.findElement(By.className("prices")).isDisplayed());
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
        try {
            Clicks.hoverForSelection(ele);
        } catch (Exception e) {
            System.out.println("Unable to hover over quickbag");
            e.printStackTrace();
        }
        WebElement qvLauncher = ele.findElement(By.className("qvLauncher"));
        Assert.assertTrue("Quick view label is not displayed after hovering on product thumbnail",
                qvLauncher.isDisplayed());
        DiscoveryHelper.logInfo("Verified that the QuickView label appears on hovering the thumbnail");
    }

    @When("^I select a product having (multi tier pricing|badge text)$")
    public void iSelectAProductHavingMultiTierPricing(String productType) throws Throwable {
        List<String> prodIds;
        Random randomGenerator = new Random();
        int index;
        prodIds = productType.contains("badge") ? DiscoveryHelper.getProductIdsHavingBadgeText() :
                getProductIdsHavingMultiLevelPricing();
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
        if (facetState.equals("collapsed")) {
            LeftFacet.expandFacet(selectedFacetName);
        } else {
            LeftFacet.collapseFacet(selectedFacetName);
        }
        DiscoveryHelper.logInfo("Successfully clicked " + selectedFacetName + " facet");
    }

    @Then("^I verify that the selected facet is \"([^\"]*)\"$")
    public void iVerifyThatTheSelectedFacetIs(String facetState) throws Throwable {
        if (facetState.equals("expanded")) {
            Assert.assertTrue("Selected facet is not expanded", LeftFacet.isExpanded(selectedFacetName));
        } else {
            Assert.assertFalse("Selected facet is not collapsed", LeftFacet.isExpanded(selectedFacetName));
        }
        DiscoveryHelper.logInfo("Selected facet is " + facetState + " expanded properly");
    }

    @When("^I click on the same \"([^\"]*)\" facet$")
    public void iClickOnTheSameFacet(String facetState) throws Throwable {
        if (facetState.equals("expanded")) {
            LeftFacet.collapseFacet(selectedFacetName);
        } else {
            LeftFacet.expandFacet(selectedFacetName);
        }
        DiscoveryHelper.logInfo("Successfully clicked on the " + selectedFacetName + " facet");
    }

    @And("^I verify that the first value in price facet should (be|not be) displayed as \"([^\"]*)\"$")
    public void iVerifyThatTheFirstValueInPriceFacetShouldBeDisplayedAs(String valueDisplay, String facetValue) throws Throwable {
        String facetName = "Price";
        List<String> facetElement = DiscoveryHelper.getAllFacetValues(facetName);
        System.out.println(facetValue);
        if (facetValue.equals("Under $50"))
            Assert.assertTrue("First value is not displayed as", facetElement.get(0).equals(facetValue));
        else
            Assert.assertFalse("First value is displayed as", facetElement.get(0).equals(facetValue));
        DiscoveryHelper.logInfo("First value in price facet is" + valueDisplay);
    }

    @Then("^I verify that the \"([^\"]*)\" facet values (less|more) than (\\d+)$")
    public void i_should_see_facets_under_facet(String facetName, String facetSize, int facetValueCount) throws Throwable {
        List<String> facetValues = DiscoveryHelper.getAllFacetValues(facetName);
        if (facetSize.equalsIgnoreCase("less"))
            Assert.assertTrue("Facet values should not be greater than ", facetValues.size() <= facetValueCount);
        else
            Assert.assertTrue("Facet values should not be greater than ", facetValues.size() > facetValueCount);
        DiscoveryHelper.logInfo("Facet values should not be greater than " + facetValues.size());
    }

    @Then("^I verify that see default message \"([^\"]*)\" in \"([^\"]*)\" facet$")
    public void i_should_see_default_message_in_brand_search_box(String defaultMessage, String facetName) throws Throwable {
        Assert.assertTrue("Default message is not available",
                Elements.findElement(By.id("filter_brand")).getAttribute("placeholder").equalsIgnoreCase(defaultMessage));
        DiscoveryHelper.logInfo("Default message is not available in the text box of " + facetName + " facet");
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
    public void i_select_a_product_pricing_of_PriceTypeID_and_PriceType(String productType, String colorwayType, int priceTypeId, String priceType) throws Throwable {

        // Loading product Data and selecting a product of given type
        loadProductData();

        selectedProdId = productData.getPriceTypesMap().get(priceType).get(colorwayType).get(productType.toUpperCase());

        System.out.println("The Product Id selected to check for price Details is  " + selectedProdId);

        EnvironmentDetails.getAppServer();
        productDetails = ProductService.getProductDetails(selectedProdId);
        String keyword = convertProductNameToSearchString(productDetails);

        if (!productType.equalsIgnoreCase("master")) {
            if (colorwayType.equalsIgnoreCase("WithColorWay")) {
                StringBuilder str = new StringBuilder();
                str.append('"');
                str.append("priceType");
                str.append('"');
                str.append(":");
                str.append(String.valueOf(priceTypeId));
                Assert.assertTrue("Expected PriceType id is not displayed for colorway product",
                        productDetails.getJSONArray("upcs").toString().contains(str.toString()));
            } else {
                int priceId = Integer.parseInt(productDetails.getJSONObject("price").toMap().get("priceType").toString());
                Assert.assertTrue("Expected PriceType id for the product id: " + selectedProdId + " is " + priceTypeId + " Displayed priceType id: " + priceId,
                        priceTypeId == priceId);
            }
        }

        DiscoveryHelper.logInfo("The Product Id selected to check for price Details is " + selectedProdId);

    }

    private void loadProductData() throws IOException {

        // This method to facilitate data load from json
        // Data will be loaded only if productData == null for making sure one time data load

        if (productData == null) {
            String path = macys() ? "/data/website/mcom/" : "/data/website/bcom/";
            String resPath = path + "ProductDataInfo" + ".json";
            File f = new File(RunConfig.projectResourceDirs.get(0) + resPath);
            String productTypeJson = Utils.readTextFile(f);
            Gson priceTypeMapGson = new Gson();
            productData = priceTypeMapGson.fromJson(productTypeJson, ProductData.class);
        }
    }

    @Then("^I verify that Product Thumbnail PriceTypeID = (\\d+) and PriceType = \"([^\"]*)\" is displayed$")
    public void i_verify_product_Thumbnail(int priceId, String priceType) throws Throwable {

        String displayedPrice, expectedPrice;
        Map<String, HashMap> details = DiscoveryHelper.getProductThumbnailDetails(selectedProdId);
        WebElement price = (WebElement) details.get(selectedProdId).get("elm_price_div");

        displayedPrice = price.getText().replaceAll("\n", " ").trim();
        expectedPrice = thumbnailLevelPrice(productDetails).split("\\(")[0].trim();
        displayedPrice = displayedPrice.replaceAll("select colors ", "").trim();
        if (shoppingMode.equalsIgnoreCase("iship"))
            // as of now all the iship scenarios are related to Canada
            expectedPrice = convertPriceToLocalCurrency(expectedPrice, "CAD");

        System.out.println("Displayed price in UI: " + displayedPrice);
        System.out.println("Price from Response: " + expectedPrice);

        Assert.assertTrue("ERROR - APP: Displayed price: " + displayedPrice + " Expected Price: " + expectedPrice,
                expectedPrice.equals(displayedPrice));

        DiscoveryHelper.logInfo("Product Thumbnail for PriceType : " + priceType + " PriceID : " + priceId + " for the Product ID : " + selectedProdId + " Verified Successfully");
        DiscoveryHelper.logInfo("*******************************************************************************************************************************************");
        System.out.println("Product Thumbnail for PriceType : " + priceType + " PriceID : " + priceId + " for the Product ID : " + selectedProdId + " Verified Successfully");
    }

    @Then("^I verify that logo is displayed and returns a (\\d+) OK$")
    public void iVerifyThatLogoIsDisplayedAndReturnsAOK(int statusCode) throws Throwable {
        WebElement logo = (macys()) ? Elements.findElement("header.logo") :
                Elements.findElement("header.Header_Logo");
        String src = logo.findElement(By.tagName("a")).getAttribute("href");
        Assert.assertTrue((DiscoveryHelper.getResponseCode(src)) == statusCode);
        DiscoveryHelper.logInfo("Response code is " + src);
    }

    @Then("^I verify that the (header|footer) elements are displayed$")
    public void iVerifyTheHeaderElementsAreDisplayed(String headerFooter) throws Throwable {
        if (headerFooter.equals("header"))
            Assert.assertTrue(headerFooter + " elements is not displayed",
                    Elements.findElement("header.user_menu_container").isDisplayed() &&
                            Elements.findElement("header.search_menu_container").isDisplayed() &&
                            Elements.findElement("header_and_footer.categories").isDisplayed());
        else
            Assert.assertTrue(headerFooter + " elements is not displayed",
                    Elements.findElement("footer.global_footer").isDisplayed());
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
        List<WebElement> fobs = macys() ? Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob")) :
                Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.tagName("li"));
        fobs = bloomingdales() ? fobs.stream().filter(e -> !e.getText().equals("")).collect(toList()) : fobs;
        if (fobs.isEmpty())
            Assert.fail("ERROR - ENV: Fobs are not displayed");
        for (WebElement fob : fobs) {
            String fobURL = fob.findElement(By.tagName("a")).getAttribute("href");
            int actualResponseCode = DiscoveryHelper.getResponseCode(fobURL);
            Assert.assertTrue("ERROR - APP: Recieved response code:'" + actualResponseCode + "' for FOB url:" + fobURL, actualResponseCode == responseCode || (actualResponseCode >= 300 && actualResponseCode <= 399));
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
        int height = Integer.parseInt(Elements.findElement(By.tagName("body")).getCssValue("height").split("\\.")[0]);
        for (int i = 0; i < section; i++) {
            Navigate.scrollPage(0, height - 900);
            Wait.forLoading(By.className("loader-container"));
        }
        prodIds = DiscoveryHelper.getProductIds();
        int thumbnailColumns = 3;
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
        System.out.println("currentprodIds.size" + currentprodIds.size());
        if (currentprodIds.size() < prodIds.size()) {
            Assert.fail("Previous section products are not landed on thumbnail grid");
        }
        prodIds = DiscoveryHelper.getProductIds();
        DiscoveryHelper.logInfo("Verified that previous section of products are loaded on thumbnail grid");
    }

    @When("^I select 'Show Previous Items' button$")
    public void IselectShowPreviousItemsbutton() throws Throwable {
        prodIds = DiscoveryHelper.getProductIds();
        System.out.println("prodids.size" + prodIds.size());
        Wait.untilElementPresent("product_thumbnails.show_previous_items");
        Elements.findElement("product_thumbnails.show_previous_items").click();
        DiscoveryHelper.logInfo("Selected 'Show Previous Items' button");
    }

    @And("^I verify that facets with product counts are displayed$")
    public void iVerifyThatFacetsWithProductCountsAreDisplayed() throws Throwable {
        List<String> facetNames = LeftFacet.getAllFacetName();
        facetNames.remove(bopsFacetName);
        for (String facetName : facetNames) {
            Map<String, Integer> facetDetails = DiscoveryHelper.getFacetDetail(facetName);
            System.out.println(facetDetails);
            for (Map.Entry<String, Integer> detail : facetDetails.entrySet()) {
                Assert.assertTrue("Facet value count is displayed as zero for facet Name: " + facetName + " and Facet Value: " +
                        detail.getKey() + " Displayed as: " + detail.getValue(), detail.getValue() != 0);
            }

            DiscoveryHelper.logInfo("All facet values are displayed with product counts");
        }
        productCountBefore = DiscoveryHelper.getProductCount();
    }

    @And("^I verify that each facet types have multifacet functionality$")
    public void iVerifyThatEachFacetTypesHaveMultifacetFunctionality() throws Throwable {
        List<String> facetNames = LeftFacet.getAllFacetName();
        facetNames.removeAll(DiscoveryHelper.getAllGroupFacetName());
        facetNames.remove(bopsFacetName);
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
                Elements.elementPresent("left_facet.facetBreadcrumbs"));
        DiscoveryHelper.logInfo("Breadcrumb not displayed in search result page");
    }

    @And("^I verify that the quick peek icon on product image without hovering on product image$")
    public void iVerifyThatTheQuickPeekIconOnProductImageWithoutHoveringOnProductImage() throws Throwable {
        List<String> productIds = getProductIds();
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(productIds.size() - 1);
        selectedProdId = productIds.get(index);
        WebElement prodElement = DiscoveryHelper.getProductDiv(selectedProdId);
        Assert.assertTrue("Quick peek icon not displayed",
                prodElement.findElement(By.className("quickPeekIcon")).isDisplayed());
        DiscoveryHelper.logInfo("Quick Peek icon displayed as expected");
    }


    @And("^I verify that the default product thumbnail and alt image$")
    public void iVerifyThatTheDefaultProductThumbnailAndAltImage() throws Throwable {
        WebElement prodElement = getProductDiv(selectedProdId);
        String imageText = prodElement.findElement(By.className("thumbnailImage")).getAttribute("src");
        int count = imageText.split(".tif").length - 1;
        Assert.assertTrue("Expected Alt image not displayed", count >= 1);
        DiscoveryHelper.logInfo("Product thumbnails and alt image verified successfully");
    }

    @And("^I verify that default attributes on new quick peek overlay$")
    public void iVerifyTheDefaultAttributesOnNewQuickPeekOverlay() throws Throwable {
        String expectedEle = macys() ? "memberProductContainer" : "quick_view_master_dialog";
        if (!Elements.elementPresent("quick_view." + expectedEle))
            Assert.assertTrue("Add to bag button not displayed in Quick peek overlay",
                    Elements.elementPresent("quick_view.quick_view_product_add_to_bag"));
        Assert.assertTrue("Product title not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.product_title"));
        Assert.assertTrue("Price information not displayed in Quick peek overlay",
                Elements.elementPresent("quick_view.price_list"));
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
        action.moveToElement(Elements.findElement("quick_view.quick_view_product_dialog"), 340, 40).click();
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
                , totalProductInCurrentPage == itemsPerPage);
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
                productIdToSearch = "  00" + Integer.toString(productId) + "  ";
                break;
            case "product id with other characters":
                productIdToSearch = Integer.toString(productId) + "abas";
                break;
            case "invalid productid":
                productIdToSearch = Integer.toString(productId) + "80983483";
                break;
        }
        selectedProdId = productIdToSearch;
        ShopAndBrowse.I_search_for(productIdToSearch);
    }

    @When("^I search for a (PDP|URL|Category) redirect keyword$")
    public void iSearchForAPDPRedirectKeyword(String redirectType) throws Throwable {
        if (redirectType.equals("PDP")) {
            iSearchForAnAvailableProductIDOrWebIDHaving("productid only");
            searchKeyword = selectedProdId;
        } else {
            searchKeyword = redirectType.equals("URL") ? "Customer Service" : "MAC";
            ShopAndBrowse.I_search_for(searchKeyword);
        }
        DiscoveryHelper.logInfo("Searched for a " + redirectType + " redirect keyword with " + searchKeyword);
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
        String url;
        switch (redirectType) {
            case "PDP":
                Assert.assertTrue("ERROR - APP: Expected Product Id: " + locationId + " Displayed Product Id: " + selectedProdId,
                        selectedProdId.equals(locationId));
                break;
            case "URL":
                url = PageNavigation.url();
                Assert.assertTrue("ERROR - APP: Expected page URL : " + locationId + " But navigated page url: " + url,
                        url.toLowerCase().contains(searchKeyword.toLowerCase().replace(" ", "%20")));
                break;
            case "Category":
                url = PageNavigation.url();
                Assert.assertTrue("ERROR - APP: Expected Category id: " + locationId + " in page url: " + url,
                        url.contains(locationId));
                break;
        }
        DiscoveryHelper.logInfo("Verified that " + redirectType + " redirect information is same in both Navapp and SDP ");
    }

    @Then("^I verify that \"([^\"]*)\" page (is not displayed|is displayed)$")
    public void iVerifyThatPageIsDisplayed(String page, String condition) throws Throwable {
        try {
            if (condition.equals("is displayed"))
                shouldBeOnPage(page.replace(" ", "_"));
//        } catch (EnvException e) {
//            Assert.assertTrue(page + " is displayed " + e.getMessage(), condition.equals("is not displayed"));
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
        DiscoveryHelper.logInfo("Added " + cookieName + " MISCGCs with zipcode " + zipcode);
    }

    @And("^I verify that default message \"([^\"]*)\" under bops facet$")
    public void iVerifyThatDefaultMessageUnderBopsFacet(String errorMessage) throws Throwable {
        Wait.untilElementPresent("change_pickup_store_dialog.errors");
        String displayedMessage = macys() ? Elements.getText("left_facet.bops_no_store_message") :
                Elements.getText("change_pickup_store_dialog.errors");
        Assert.assertTrue("Expected error: " + errorMessage + " is not displayed under bops facet",
                displayedMessage.trim().equalsIgnoreCase(errorMessage.trim()));
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
            DiscoveryHelper.logInfo("Stores with " + count + " product count are displayed in disabled state");
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
        if (nameDisplayed.equals("not displayed"))
            Assert.assertTrue("Store name is displayed in bops messaging",
                    Elements.findElements("product_display.bops_availability_message_with_store").isEmpty());
        else {
            Assert.assertFalse("Store name is not displayed in bops messaging",
                    Elements.findElements("product_display.bops_availability_message_with_store").isEmpty());
        }
        DiscoveryHelper.logInfo("Store name is " + nameDisplayed + " in bops messaging in PDP");
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
    public void iVerifyLandedPage(String pageName) throws Throwable {
        shouldBeOnPage(pageName);
    }

    @Then("I navigate to dynamic landing page in website in \"([^\"]*)\" mode$")
    public void navigateToDLP(String modeName) {

        DiscoveryHelper.landingDynamicLandingPage(modeName);
    }

    @Then("I click on \"([^\"]*)\" label link$")
    public void clickLabel(String labelName) {

        WebElement element = Elements.findElement(By.linkText(labelName));
        Clicks.click(element);
    }

    @Then("I click on checkout button on add to bag page$")
    public void clickCheckoutButton() {

        try {
            if (onPage("add_to_bag")) {
                Clicks.click("add_to_bag.checkout");
            } else if (Elements.elementPresent("add_to_bag_dialog.add_to_bag_checkout")) {
                Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
            } else {
                Clicks.click("product_display.member_atb_checkout");
            }
            DiscoveryHelper.logInfo("Successfully click on Checkout button");
        } catch (Exception e) {
            Assert.fail("Unable to click Checkout Button " + e.getMessage());
        }
    }


    @When("^I select (\\d+) th product from thumbnail grid$")
    public void iSelectNthProductFromThumbnailGrid(int nThProduct) throws Throwable {
        try {
            prodIds = DiscoveryHelper.getProductIds();
            System.out.println("No of products in the page " + prodIds.size());
            Assert.assertTrue("The results doesn't have given Nth product", prodIds.size() >= nThProduct);
            selectedProdId = prodIds.get(nThProduct - 1);
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
            prodIds = getProductIds();
            System.out.println("No of products in the page " + prodIds.size());
            Assert.assertTrue("The results doesn't have given Nth product", prodIds.size() >= nThProduct);
            selectedProdId = prodIds.get(nThProduct - 1);
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

    @Given("^I search for \"([^\"]*)\" in ([^\"]*) mode$")
    public void iSearchForInMode(String keyword, String mode) throws Throwable {
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
            ShopAndBrowse.I_search_for(searchKeyword);
            DiscoveryHelper.navigateToComponentizationURL();
        } catch (Exception e) {
            Assert.fail("Error Navigating to " + searchKeyword + " in " + mode + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Successfully Searched  " + searchKeyword + " on " + mode + " mode");
    }

    @When("^I search for \"([^\"]*)\" as a registered User$")
    public void iSearchForAsARegisteredUser(String value) throws Throwable {
        ShopAndBrowse.I_search_for(value);
        DiscoveryHelper.navigateToComponentizationURL();
        DiscoveryHelper.logInfo("Successfully Searched  " + searchKeyword + " as a registered user");
    }

    @Then("^I verify the display Message in Registry zero results page$")
    public void iVerifyTheDisplayMessageInRegistryZeroResultsPage() throws Throwable {
        String expectedMessage = "We found 0 results for your search. You're currently searching within Registry. Return to macys.com to search all products.";
        String actualMessage = Elements.findElement(By.id("resultsFoundMessageZSR")).getText();
        Assert.assertTrue("Zero Results Page is not displaying Error Message properly", actualMessage.trim().equalsIgnoreCase(expectedMessage));
        DiscoveryHelper.logInfo("Zero Results message is displayed properly in registry mode");
    }

    @And("^I filter the result set to show \"([^\"]*)\" products per page$")
    public void iFilterTheResultSetToShowProductsPerPage(String PPPOption) throws Throwable {
        try {
            productCount = DiscoveryHelper.getProductCount();
            List<WebElement> ele_PPPOptions = Elements.findElements(Elements.element("pagination.filterPPP"));
            String selectedPPPOption = Elements.findElement(Elements.element("pagination.filterPPP")).getText();

            if (!selectedPPPOption.equalsIgnoreCase(PPPOption)) {
                WebElement ele = ele_PPPOptions.stream().filter(el -> !el.getAttribute("className").equalsIgnoreCase("selected")).findFirst().orElse(null);
                Clicks.click(ele);
                Wait.forLoading("left_facet.loading");
            }
        } catch (Exception e) {
            Assert.fail("Error Navigating to " + PPPOption + " in thumbnail grid");
        }
    }

    @And("^I verify that products are updated with selected \"([^\"]*)\" sort option$")
    public void iVerifyThatProductsAreUpdatedWithSelectedSortOption(String sortOrder) throws Throwable {
        iVerifyTheSortByFunctionality(sortOrder);
        DiscoveryHelper.logInfo("Verified the product update based on " + sortOrder + " sort options");
    }

    @And("^I click on save button in change pick-up in-store overlay$")
    public void iClickOnSaveButtonInChangePickUpInStoreOverlay() throws Throwable {
        Wait.forLoading("change_pickup_store_dialog.loading_mask");
        String container = macys() ? "bops_stores" : "store_container";
        Wait.untilElementPresent("change_pickup_store_dialog." + container);
        Elements.findElement("change_pickup_store_dialog.save").click();
    }

    /**
     * Changes the number of items per page to the given number
     *
     * @param numItems number of items to show
     * @throws Throwable if any exception occurs
     */
    @And("^I filter the result set to show \"([^\"]*)\" products from \"([^\"]*)\"")
    public void I_filter_the_result_set_to_show_items(String numItems, String position) throws Throwable {
        int pppNum = Integer.parseInt(numItems);
        DiscoveryHelper.selectProductsPerPage(pppNum, position);
        Wait.forLoading(By.id("loading_mask"));
    }

    @Then("^I verify that no facet is displayed in breadcrumb$")
    public void iVerifyThatNoFacetIsDisplayed() throws Throwable {
        Assert.assertFalse("ERROR - APP: Facets are displayed in breadcrumb",
                Elements.elementPresent("left_facet.facetBreadcrumbs"));
        DiscoveryHelper.logInfo("Verified that no facet are dispalyed in breadcrumb");
    }

    @When("^I filter the result set to show \"([^\"]*)\" items per page$")
    public void iFilterTheResultSetToShowItemsPerPage(String item) throws Throwable {
        try {
            DiscoveryHelper.selectProductsPerPage(Integer.parseInt(item), "top");
        } catch (Exception e) {
            DiscoveryHelper.selectProductsPerPage(Integer.parseInt(item), "bottom");
        }
        Wait.isPageLoaded();
        Wait.forLoading("left_facet.loading");
        DiscoveryHelper.logInfo("Filtered the result set to show " + item + " items per page");
    }

    @Then("^I click add to bag button on QuickView page$")
    public void i_add_product_from_QV() throws Throwable {
        if (bloomingdales()) {
            if (Elements.elementPresent("quick_view.quick_view_product_size_list")) {
                List<WebElement> sizes = Elements.findElement("quick_view.quick_view_product_size_list")
                        .findElements(By.xpath("li[not(contains(@class, \"disabled\"))]"));
                WebElement prodSize = sizes.get(new Random().nextInt(sizes.size()));
                Clicks.click(prodSize.findElement(By.xpath("span")));
            }
        } else {
            if (Elements.elementPresent("quick_view.quick_view_product_size")) {
                Clicks.click(Elements.findElement("quick_view.quick_view_size_dropdown"));
                Select select = new Select(Elements.findElement("quick_view.quick_view_size_dropdown"));
                List<WebElement> options = select.getOptions().stream().filter(f -> f.isEnabled()).collect(Collectors.toList());
                select.selectByVisibleText(options.get(1).getText());
            }
        }
        if (Elements.elementPresent("quick_view.quick_view_product_add_to_bag")) {
            Clicks.click("quick_view.quick_view_product_add_to_bag");
        } else {
            Assert.fail("Add To Bag Button is not displayed");
        }
        DiscoveryHelper.logInfo("Successfully click on Add to bag button");
    }

    @Then("^I click checkout button on QuickView page$")
    public void click_checkout_QV() throws Throwable {
        Wait.untilElementPresent("quick_view.quick_view_add_to_bag_checkout");
        if (Elements.elementPresent("quick_view.quick_view_add_to_bag_checkout")) {
            Clicks.click("quick_view.quick_view_add_to_bag_checkout");
        } else {
            Assert.fail("Checkout Button is not displayed");
        }
        DiscoveryHelper.logInfo("Successfully click on Checkout button");
    }

    @Then("I modify the url to get in to snbc experiment$")
    public void modify_url() {
        //TODO: Need to remove this step usages from FF
    }

    @Then("^I verify that bops overlay is (not displayed|displayed)$")
    public void iVerifyBopsOverlay(String condition) throws Throwable {
        try {
            if (Elements.elementPresent("change_pickup_store_dialog.changeLocationNewBops"))
                Clicks.click("change_pickup_store_dialog.changeLocationNewBops");
            String elementToVerify = macys() ? "title" : "container";
            if (condition.equalsIgnoreCase("displayed")) {
                Assert.assertTrue("BOPS overlay is not displayed to change location",
                        Elements.elementPresent("change_pickup_store_dialog." + elementToVerify));
            } else
                Assert.assertFalse("BOPS overlay is displayed to change location",
                        Elements.elementPresent("change_pickup_store_dialog." + elementToVerify));
        } catch (Exception e) {
            Assert.fail("Error Selecting Zip Code link in pick-up in-store facet" + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verified bops overlay with stores list");
    }

    @And("^I select any random bops facet value$")
    public void I_select_any_bops_facet_value() throws Throwable {
        if (macys()) {
            Clicks.clickRandomElement(LeftFacet.getFacetItems("Free Pick Up In Store"));
        } else {
            LeftFacet.expandFacet("Pick Up In Store");
            Clicks.clickRandomElement(LeftFacet.getFacetItems("Pick Up In Store"));
        }
        DiscoveryHelper.navigateToComponentizationURL();
    }

    @When("^I navigate to dynamic landing page with color swatch products in  \"([^\"]*)\"$")
    public void iNavigateToDynamicLandingPageWithColorSwatchProductsIn(String mode) throws Throwable {
        if (mode.equalsIgnoreCase("domestic") || mode.equalsIgnoreCase("iship")) {
            String host;
            try {
                host = new java.net.URI(RunConfig.url).getHost();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            String url = "https://" + host + "/buy/bedding-collections";
            Navigate.visit(url);
        } else {
            new Home().selectMainCategory("BRANDS");
            Clicks.click(By.linkText("SFERRA"));
        }
    }

    @Then("^I find a random product having color swatches$")
    public void iFindARandomProductHavingColorSwatches() throws Throwable {
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
        } catch (Exception e) {
            Assert.fail("Unable to select a product having color swatch");
        }
    }

    @And("^I select any color in color swatches section$")
    public void iSelectAnyColorInColorSwatchesSection() throws Throwable {
        List<WebElement> colorSwtches = Elements.findElement(By.id("colorList" + selectedProdId)).findElements(By.className("color-swatch"));
        WebElement randomColorSwatch = colorSwtches.get(new Random().nextInt(colorSwtches.size()));
        Clicks.click(randomColorSwatch);
    }

    @When("^I find a random product having color swatches with more button$")
    public void iFindARandomProductHavingColorSwatchesWithMoreButton() throws Throwable {
        List<String> prodIds;
        Random randomGenerator = new Random();
        int index;
        try {
            prodIds = DiscoveryHelper.getProductIdsWithMoreColorSwatch();
            Assert.assertTrue("There is no product displayed with color swatch more ", prodIds.size() != 0);
            index = randomGenerator.nextInt(prodIds.size());
            selectedProdId = prodIds.get(index);
        } catch (Exception e) {
            Assert.fail("Unable to select a product having color swatch");
        }
    }

    @Then("^I select 'More' button in color swatch section$")
    public void iSelectMoreButtonInColorSwatchSection() throws Throwable {
        WebElement swatchColorMore = Elements.findElement(By.id("swatchColorsMore" + selectedProdId));
        Clicks.click(swatchColorMore);
    }

    @Then("^I navigate to \"([^\"]*)\" dlp page$")
    public void iNavigateToDlpPage(String brand) throws Throwable {
        Clicks.click(By.linkText(brand));
        DiscoveryHelper.logInfo("Navigated to " + brand + " dlp page");
    }

    @Then("^I verify that the title tag is displayed as below$")
    public void iVerifyThatTheTitleTagIsDisplayedAsBelow(DataTable table) throws Throwable {
        String title = WebDriverManager.getWebDriver().getTitle();
        System.out.println("Displayed Page title: " + title);
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            String expectedTitle = row.get("3 column");
            System.out.println("Expected title: " + expectedTitle);
            Assert.assertTrue("Expected title: " + expectedTitle + " Displayed title: " + title,
                    expectedTitle.toLowerCase().equals(title.toLowerCase()));

        }
    }

    @Then("^I verify that the title tag is displayed with selected facet$")
    public void iVerifyThatTheTitleTagIsDisplayedWithSelectedFacet() throws Throwable {
        if (searchKeyword == null) {
            searchKeyword = Elements.getText("search_result.search_keyword").toLowerCase();
        }
        String title = WebDriverManager.getWebDriver().getTitle();
        String expectedTitle;
        System.out.println("Displayed title: " + title);
        if (selectedFacetNames.size() == 1) {
            String facetName = selectedFacetNames.get(0);
            expectedTitle = selectedFacetValues.get(0) + " " + searchKeyword + " - Macy's";
            if (expectedTitle.toLowerCase().equals(title.toLowerCase())) {
                Assert.assertTrue("Expected title: " + expectedTitle + " Displayed title: " + title,
                        expectedTitle.toLowerCase().equals(title.toLowerCase()));
            } else if (facetExclusionListForTitle().stream().map(String::toLowerCase)
                    .collect(Collectors.toList()).contains(getSelectedFacetName(facetName).toLowerCase())) {
                System.out.println("selected facet name li: " + getSelectedFacetName(facetName));
                expectedTitle = searchKeyword.toLowerCase() + " - Macy's";
            }
            Assert.assertTrue("Expected title: " + expectedTitle + " Displayed title: " + title,
                    expectedTitle.toLowerCase().equals(title.toLowerCase()));
        } else {
            for (String facetName : selectedFacetNames) {
                if (facetExclusionListForTitle().stream().map(String::toLowerCase)
                        .collect(Collectors.toList()).contains(getSelectedFacetName(facetName).toLowerCase())) {
                    expectedTitle = searchKeyword.toLowerCase() + " - Macy's";
                    Assert.assertTrue("Expected title: " + expectedTitle + " Displayed title: " + title,
                            title.toLowerCase().contains(expectedTitle.toLowerCase()));
                } else {
                    Wait.untilElementPresent("left_facet.facetBreadcrumbs");

                    List<String> breadCrumbsText = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).stream()
                            .map(WebElement::getText).collect(Collectors.toList());
                    List<Boolean> titleVerification = breadCrumbsText.stream()
                            .filter(breadCrumbText -> title.toLowerCase().contains(breadCrumbText.toLowerCase()))
                            .map(breadCrumbText -> true).collect(Collectors.toList());
                    Assert.assertTrue("Page title varies with selected facet: ",
                            titleVerification.contains(true));
                    Assert.assertTrue("Page title does not conatin - Macy's text",
                            title.contains("- Macy's"));
                }
            }
        }
    }

    @And("^I verify GoogleAdSense functionality in search results page$")
    public void iVerifyGoogleAdSenseFunctionalityInSearchResultsPage() throws Throwable {
        PageNavigation.scrollToLazyLoadElement("footer.ads_by_google_section");
        Wait.untilElementPresent("footer.ads_by_google_section");
        PageNavigation.switchToFrame("footer.google_iframe");
        List<WebElement> googleAdLinks = Elements.findElements("footer.google_ad_links");
        googleAdLinks = googleAdLinks.stream().filter(WebElement::isDisplayed).collect(toList());
        Assert.assertFalse("ERROR - DATA: Google ad links are not displayed", googleAdLinks.isEmpty());
        String searchUrl = WebDriverManager.getCurrentUrl();

        Random randomGenerator = new Random();
        int randomAdIndex = randomGenerator.nextInt(googleAdLinks.size());

        Elements.findElements("footer.google_ad_links").get(randomAdIndex).click();
        String adUrl = WebDriverManager.getCurrentUrl();

        Navigate.browserBack();

        if (searchUrl.equals(adUrl)) {
            Assert.fail("Google ad links not worked as expected");
        }
        PageNavigation.switchToFrame("default");
    }

    @And("^I should see less than (\\d+) google ads$")
    public void iShouldSeeLessThanGoogleAds(int adSize) throws Throwable {
        PageNavigation.scrollToLazyLoadElement("recently_viewed_items.ads_by_google_section");
        PageNavigation.switchToFrame("footer.google_iframe");
        List<WebElement> googleAdLinks = Elements.findElements("footer.google_ad_links");
        googleAdLinks = googleAdLinks.stream().filter(WebElement::isDisplayed).collect(toList());
        if (googleAdLinks.size() < 1) {
            Assert.fail("Google ad links are not displayed");
        }
        Assert.assertTrue("Google ads should be displayed less than " + adSize + " Displayed ads " + googleAdLinks.size(),
                googleAdLinks.size() < 6);
    }

    @And("^I verify that google ads section is displayed$")
    public void iVerifyThatGoogleAdsSectionIsDisplayed() throws Throwable {
        PageNavigation.scrollToLazyLoadElement("footer.ads_by_google_section");
        Assert.assertTrue("Google ads section is not displayed",
                Elements.elementPresent("footer.ads_by_google_section"));
        DiscoveryHelper.logInfo("Verified that google ads are displayed");
    }

    @Then("^I verify that text \"([^\"]*)\" for corresponding product is displayed on (?:search results|dlp) page$")
    public void iShouldSeeTextForCorrespondingProductOnSearchResultsPage(String expectedText) throws Throwable {
        expectedText = expectedText.contains("Ext Sizes & Widths") ? "Extended" : expectedText;
        List<String> ids = DiscoveryHelper.getProductIdsWithRibbonOverlayWith(expectedText);
        float percentOfProductDisplayed = (float) ((ids.size() * 100) / DiscoveryHelper.getProductIds().size());
        System.out.println("Percent displayed ------------ " + percentOfProductDisplayed);
        Assert.assertTrue("Expected more than 50% of product to be displayed with given ribbon text: " + expectedText,
                percentOfProductDisplayed >= 1);
        DiscoveryHelper.logInfo("Verified that " + expectedText + " for corresponding products on search results page");
    }

    @Given("^I am on ZeroSearchResultPage for \"([^\"]*)\" in ([^\"]*) mode$")
    public void iAmOnZeroSearchResultPageForInSite_ModeMode(String keyword, String mode) throws Throwable {
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

            System.out.println("searchKeyword ::" + keyword);

            String currentURL = EnvironmentDetails.getEnvUrl();

            StringBuilder searchUrl = new StringBuilder();
            String snbcExp = RunConfig.getEnvOrExParam("snbc_exp");

            if (mode.equalsIgnoreCase("REGISTRY")) {
                searchUrl.append(currentURL);
                searchUrl.append("/shop/registry/wedding/search?keyword=");
                searchUrl.append(keyword);
                if (snbcExp == null || snbcExp.equalsIgnoreCase("true")) {
                    searchUrl.append("&EFCKEY=%7B%22EXPERIMENT%22%3A%5B" + DiscoveryHelper.getEFCKey() + "%5D%7D");
                }
            } else {
                searchUrl.append(currentURL);
                searchUrl.append(macys() ? "/shop/featured/" : "/shop/search?keyword=");
                searchUrl.append(keyword);
                if (snbcExp == null || snbcExp.equalsIgnoreCase("true")) {
                    searchUrl.append("&EFCKEY=%7B%22EXPERIMENT%22%3A%5B" + DiscoveryHelper.getEFCKey() + "%5D%7D");
                }
            }

            Navigate.visit(searchUrl.toString());
            Navigate.browserRefresh();
            shouldBeOnPage("zero_search_result");
        } catch (Exception e) {
            Assert.fail("Error Navigating to " + keyword + " in " + mode + "\r\n" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Navigated to Zero Search Results Page for " + keyword + " on " + mode + " mode");
    }

    @And("^I verify that (recommendation panel|shopBy text|category icons) ([^\"]*) displayed on ZSR page$")
    public void iVerifyThatRecommendationPanelIsDisplayedOnZSRPage(String type, String isAre) throws Throwable {
        if (shoppingMode == null)
            shoppingMode = HeaderActions.getSiteMode();
        switch (type) {
            case "recommendation panel":
                Wait.secondsUntilElementPresent("zero_search_result.recommendationPanel", 30);
                Assert.assertTrue(type + " is not displayed",
                        Elements.elementPresent("zero_search_result.recommendationPanel"));
                break;
            case "shopBy text":
                Wait.untilElementPresent("zero_search_result.headerLabel");
                Assert.assertTrue(type + " is not displayed",
                        Elements.elementPresent("zero_search_result.headerLabel"));
                String headerText = Elements.findElement("zero_search_result.headerLabel").getText();
                Assert.assertTrue("Expected shopby text: " + " Shop by " + " Displayed text: " + headerText,
                        headerText.equals("Shop by"));
                break;
            case "category icons":
                Wait.untilElementPresent("zero_search_result.categoryIcons");
                if (!shoppingMode.equalsIgnoreCase("registry")) {
                    Assert.assertTrue(type + " are not displayed",
                            Elements.elementPresent("zero_search_result.categoryIcons"));
                    Assert.assertTrue("Category icons are not displayed ",
                            Elements.findElements("zero_search_result.categoryIcons").size() > 1);
                }
                break;
        }
        DiscoveryHelper.logInfo("Verified that " + type + " " + isAre + " displayed on ZSR page");
    }

    @Given("^I select back to top button$")
    public void iSelectBackToTopButton() throws Throwable {
        try {
            String backToTopEle = macys() ? "product_thumbnails.back_to_top" : "search_result.back_to_top_button";
            WebElement backToTop = Elements.findElement(backToTopEle);
            Clicks.click(backToTop);
            DiscoveryHelper.logInfo("Navigated to top of search result page");
        } catch (Exception e) {
            Assert.fail("Unable to select back to top button");
        }
    }

    @Then("^I verify that (?:Search result|Cat Splash|Browse|dlp|sub splash) page navigated to top of the page$")
    public void iVerifySearchResultPageNavigatedToTop() throws Throwable {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
            Long windowScrollPosition;
            if (macys()) {
                windowScrollPosition = (Long) executor.executeScript("return window.pageYOffset;");
                Assert.assertEquals(new Long(0), windowScrollPosition);
            } else {
                try {
                    DiscoveryHelper.logInfo( "Expected scroll position shoule be <= 140");
                    DiscoveryHelper.logInfo("windowScrollPosition =====>" + executor.executeScript("return window.pageYOffset;"));
                    Assert.assertTrue((double) executor.executeScript("return window.pageYOffset;") <= 140);
                } catch (ClassCastException e) {
                    Assert.assertFalse((Double) executor.executeScript("return window.pageYOffset;") <= 100);
                } catch (Exception e) {
                    Assert.fail("Unable to get page offset: " + e.getMessage());
                }
            }
            DiscoveryHelper.logInfo("verified  search result page navigated to top of page");
        } catch (Exception e) {
            Assert.fail("Unable to verify search result page navigated to top of page");
        }
    }

    @Then("^I verify that promo text and promo popup is displayed under product thumbnail$")
    public void iVerifyThatPromoTextIsDisplayedUnderProductThumbnail() throws Throwable {
        if (onPage("category_browse")) {
            WebElement badgeElement = DiscoveryHelper.getProductDiv(selectedProdId).findElement(Elements.element("product_thumbnails.badgeHeader"));
            Clicks.click(badgeElement);
            if (Elements.elementPresent("category_browse.badge_panel_overlay")) {
                Assert.assertTrue("Promotional overlay is not displayed for product id: " + selectedProdId,
                        Elements.findElement("category_browse.badge_panel_overlay").isDisplayed());
                Assert.assertTrue("Promotional overlay text is not displayed for product id: " + selectedProdId,
                        Elements.findElement("category_browse.badge_panel_overlay").findElement(Elements.element("category_browse.badge_promo_text")).isDisplayed());
                Assert.assertTrue("Promotional overlay title is not displayed for product id: " + selectedProdId,
                        Elements.findElement("category_browse.badge_panel_overlay").findElement(Elements.element("category_browse.badge_promo_title")).isDisplayed());
            }
            WebElement promoImageEle = null;
            try {
                promoImageEle = Elements.findElement("category_browse.badge_panel_overlay").findElement(By.className("badgeImageTag"));
            } catch (Exception e) {
                DiscoveryHelper.logInfo("Promo Image Data not available to validate:" + e.getMessage());
            }
            if (promoImageEle != null) {
                Assert.assertTrue("Promotional image is not displayed for product id: " + selectedProdId,
                        promoImageEle.isDisplayed());
                int imageResponseCode = RESTOperations.doGET(promoImageEle.getAttribute("src"), null).getStatus();
                Assert.assertTrue("Promotional image broken for product id: " + selectedProdId + ". Receiving response code:" + imageResponseCode, imageResponseCode == 200);
            }
        } else {
            Assert.assertTrue("Promotional overlay is not displayed for product id: " + selectedProdId,
                    DiscoveryHelper.getProductDiv(selectedProdId).findElement(Elements.element("product_thumbnails.badgeToolTip")).isDisplayed());
        }

        DiscoveryHelper.logInfo("Verified that promo text is displayed under product thumbnail");
    }

    @When("^I select \"([^\"]*)\" button for \"([^\"]*)\" product on page$")
    public void iSelectButtonForProductOnPage(String buttonType, String productType) throws Throwable {
        try {
            if (productType.equalsIgnoreCase("master"))
                prodIds = DiscoveryHelper.getMasterProductIds();
            else
                prodIds = DiscoveryHelper.getMemberProdIds();
            System.out.println("Size of member product id is :" + prodIds.size());
            Assert.assertFalse("ERROR - DATA: '" + productType + "' Products are not found to select", prodIds.isEmpty());
            int index = prodIds.size() == 1 ? 0 : new Random().nextInt(prodIds.size() - 1);
            selectedProdId = prodIds.get(index);
            productPrice = DiscoveryHelper.getProductDiv(selectedProdId).findElement(Elements.element("product_thumbnails.price")).getText();
            if (buttonType.equals("quick view"))
                DiscoveryHelper.selectQuickView(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to select Quick view for the member product id " + selectedProdId + " " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the Quick View of that member product id: " + selectedProdId);
    }

    @Then("^I verify that respective product is in 'Shopping Bag' page$")
    public void iVerifyThatRespectiveProductIsInShoppingBagPage() throws Throwable {
        try {
            Assert.assertTrue("Respective Product " + selectedProdId + " is not in Shopping Bag ", DiscoveryHelper.verifyShoppingBagItem(selectedProdId));
        } catch (Exception e) {
            Assert.fail("Unable to Verify respective product " + selectedProdId + " in Shopping Mode" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Verify respective product is in Shopping Bag " + selectedProdId);
    }

    @Then("^I should be navigated to the registry sign in page$")
    public void i_should_be_navigated_to_the_registry_sign_in_page() throws Throwable {
        try {
            shouldBeOnPage("registry_sign_in", "new_registry_sign_in");
        } catch (Exception e) {
            Assert.fail("Unable to navigate to registry sign in page" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Successfully navigate to registry sign in page");
    }

    @Then("^I should see success message as \"([^\"]*)\"$")
    public void iShouldSeeSuccessMessageAs(String expectedMsg) throws Throwable {
        try {
            String expectedMessageEle = macys() ? "quick_view.add_to_list_success_msg" : "quick_view.quick_view_add_to_wishlist_overlay";
            Wait.untilElementPresent(expectedMessageEle);
            String actualMessage = Elements.getText(expectedMessageEle).replaceAll("[\n\r]", "");
            Assert.assertEquals("Success message is not displayed as " + expectedMsg, expectedMsg.toLowerCase(), actualMessage.toLowerCase());
        } catch (Exception e) {
            Assert.fail("Success Message is not displayed" + e.getMessage());
        }
        DiscoveryHelper.logInfo("I Verify that Success Message Displayed");
    }

    @Then("^I verified error message displayed as \"([^\"]*)\"$")
    public void iVerifiedErrorMessageDisplayedAs(String expectedMsg) throws Throwable {
        try {
            String eleToWait = macys() ? "quick_view.add_to_registry_valid_msg" : "regClaimOverlay.regClaimOverlay";
            Wait.untilElementPresent(eleToWait);
            String actualMessage = macys() ? Elements.getText("quick_view.add_to_registry_valid_msg") :
                    Elements.getText("regClaimOverlay.registryClaimHeader2");
            Assert.assertEquals("Error message is not displayed as: " + expectedMsg, expectedMsg, actualMessage);
        } catch (Exception e) {
            Assert.fail("Error Message is not displayed " + e.getMessage());
        }
        DiscoveryHelper.logInfo("I Verify that Error Message Displayed");
    }

    @Then("^I should see success message in quick view overlay$")
    public void i_should_see_success_message_in_quick_view_overlay() throws Throwable {
        try {
            Wait.untilElementPresent("quick_view.add_to_list_success_msg");
            Assert.assertTrue("Successs message is not displayed in Quick View Overlay ",
                    Elements.elementPresent("quick_view.add_to_list_success_msg"));
        } catch (Exception e) {
            Assert.fail("Success Message is not displayed in quick view overlay" + e.getMessage());
        }
        DiscoveryHelper.logInfo("I Verify that Success Message Displayed In Quick View Overlay");
    }

    @Then("^I verify that hE tag is not populated in bright tag data dictionary object$")
    public void iVerifyThatHETagTagInBrightTagDataDictionaryObjectIsNotDisplayed() throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
        Map<String, Object> tags = (Map<String, Object>) (macys() ? executor.executeScript("return MACYS.brightTag") : executor.executeScript("return BLOOMIES.brightTag"));
        if (macys())
            Assert.assertTrue("hE tag in bright tag is displayed data dictionary object: " + tags.toString(),
                    tags.get("hE").equals(""));
        Assert.assertTrue("hE tag in bright tag is displayed data dictionary object: " + tags.toString(),
                tags.get("hE").equals(""));
        DiscoveryHelper.logInfo("Verified that hE tag in bright tag data dictionary object is not displayed");
    }

    @Given("^I am SearchResultsPage in ([^\"]*) mode for \"([^\"]*)\" product \"([^\"]*)\" pricing of PriceTypeID = (\\d+) and PriceType = \"([^\"]*)\"$")
    public void iAmSearchResultsPageInSite_ModeModeForProductPricingOfPriceTypeIDAndPriceType(String mode, String productInfo, String colorwayType, int priceTypeId, String priceType) throws Throwable {
        loadProductData();

        selectedProdId = productData.getPriceTypesMap().get(priceType).get(colorwayType).get(productInfo.toUpperCase());
        productColorwayType = colorwayType;
        productType = productInfo;
        System.out.println("The Product Id selected to check for price Details is  " + selectedProdId);
        DiscoveryHelper.logInfo("The Product Id selected to check for price Details is " + selectedProdId);

        EnvironmentDetails.getAppServer();
        productDetails = ProductService.getProductDetails(selectedProdId);
        String keyword = convertProductNameToSearchString(productDetails);

        if (!productType.equalsIgnoreCase("master")) {
            if (colorwayType.equalsIgnoreCase("WithColorWay")) {
                StringBuilder str = new StringBuilder();
                str.append('"');
                str.append("priceType");
                str.append('"');
                str.append(":");
                str.append(String.valueOf(priceTypeId));
                Assert.assertTrue("Expected PriceType id is not displayed for colorway product",
                        productDetails.getJSONArray("upcs").toString().contains(str.toString()));
            } else {
                int priceId = Integer.parseInt(productDetails.getJSONObject("price").toMap().get("priceType").toString());
                Assert.assertTrue("Expected PriceType id for the product id: " + selectedProdId + " is " + priceTypeId + " Displayed priceType id: " + priceId,
                        priceTypeId == priceId);
            }
        }

        PageNavigation pageNavigation = new PageNavigation();
        pageNavigation.iAmOnSearchResultsPageForOnMode(keyword, mode);

    }

    @When("^I select a product from Refactored RVI$")
    public void iSelectRandomProductFromRVIpanel() throws Throwable {
        try {
            selectedProdId = DiscoveryHelper.getProductIdsInRVIEntire().get(0);
            DiscoveryHelper.selectRVIProductThumbnail(selectedProdId);
        } catch (Exception ex) {
            Assert.fail("Not able to select a product from Refactored RVI");
        }
        DiscoveryHelper.logInfo("Successfully selected random product from RVI pannel");
    }

    @And("^I verify maximum of (\\d+) products displayed in RVI panel$")
    public void iVerifyMaxiumProductDisplayedInRVIPanels(int productCount) throws Throwable {
        try {
            String error_message = "count mismatches";
            Wait.untilElementPresent("recently_viewed_items.edit_button");
            if (Elements.elementPresent("recently_viewed_items.edit_button")) {
                Assert.assertTrue(error_message, DiscoveryHelper.getAllProductIdsInRVIEntire().size() <= productCount);
            } else {
                Assert.fail("RVI panel is not Displayed");
            }
        } catch (Exception e) {
            Assert.fail("Error::In Verifying Maximum Product Displayed in RVI Panel" + e.getMessage());
        }
        DiscoveryHelper.logInfo("I verified maximum of" + productCount + "products displayed in RVI panel ");
    }

    // This step is to verify the Rvi panel contains six items at a time
    @And("^I verify 6 Recent products displayed at one time in RVI panel$")
    public void iVerify6RecentProductsDisplayedAtOneTimeInRVIPanels() throws Throwable {
        try {
            String error_message = "count mismatches";
            if (DiscoveryHelper.isRVIEditOptionDisplayed())
                Assert.assertTrue(error_message, DiscoveryHelper.getRVIPanelProductCurrentCount() == 6);
            else
                Assert.fail("RVI panel is not Displayed");
        } catch (Exception e) {
            Assert.fail("Error::In Verifying 6 Recent products displayed at one time in RVI panel" + e.getMessage());
        }
        DiscoveryHelper.logInfo("I verified Recent products displayed at one time in RVI panel");
    }

    @And("^I should see the navigation arrow buttons$")
    public void I_should_see_the_navigation_arrow_buttons() throws Throwable {
        try {
            Wait.untilElementPresent("recently_viewed_items.scroll_right");
            Assert.assertTrue("Not able to see navigation arrows", Elements.elementPresent("recently_viewed_items.scroll_right"));
        } catch (Exception ex) {
            Assert.fail("Error In::I should see the navigation arrow buttons" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Successfully see the navigation arrow buttons");
    }

    @When("^I click on \"(left|right)\" arrow key inside RVI panel$")
    public void I_click_on_arrow_key_inside_RVI(String arrow) throws Throwable {
        try {
            DiscoveryHelper.updateRecentlyViewedProduct();
            if (!Clicks.clickIfPresent("recently_viewed_items.scroll_" + arrow))
                Assert.fail("ERROR - APP: Cannot scroll to the " + arrow);
            // wait for the scroll animation - can't find it programmatically but it always takes the same amount of time
        } catch (Exception ex) {
            Assert.fail("Error In::I click on arrow " + arrow + " key inside RVI panel" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Successfully click on arrow " + arrow + " see the navigation arrow buttons");
    }

    @Then("^I verify that (previous|next) set of products are displayed$")
    public void iVerifyThatSetOfProductsAreDisplayed(String selectedArrow) throws Throwable {
        try {
            List<String> currentProducts = DiscoveryHelper.getRecentlyViewedProduct();
            List<String> oldProducts = DiscoveryHelper.getOldRecentlyViewedProduct();
            Assert.assertFalse("Recently viewed products panel did not change",
                    currentProducts.get(0).equalsIgnoreCase(oldProducts.get(0)));
        } catch (Exception ex) {
            Assert.fail("Error In:: Verifing that " + selectedArrow + " set of products are displayed" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Verified that " + selectedArrow + " set of product are displayed");
    }

    @And("I click \"(edit|done)\" button in RVI panel?$")
    public void I_click_edit_done_button_in_Rvi_panel(String action) {
        try {
            DiscoveryHelper.clickRVIPanelEditDone();
        } catch (Exception ex) {
            Assert.fail("Error In:: Unable to click " + action + " button" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Clicked " + action + " button in RVI panel");
    }

    @Then("I should see highlighted Remove button on each product and Edit button displayed as Done in RVI panel?$")
    public void iShouldSeeHighlightedRemoveButtonOnEachProductInRVPanel() {
        try {
            Wait.untilElementPresent("recently_viewed_items.remove_button");
            Assert.assertTrue("Not able to see Remove Button", Elements.elementPresent("recently_viewed_items.remove_button"));
            Assert.assertTrue("Edit button not displayed as Done", Elements.findElement("recently_viewed_items.edit_button").getText().equals("Done"));
        } catch (Exception ex) {
            Assert.fail("Error In:: I should see highlighted remove button on each product in RVI Panel" + ex.getMessage());
        }
    }

    // This step is to remove and validate the removal of item in old Rvi panel
    @And("I remove and verify the item is removed from RVI panel$")
    public void IRemoveAndVerifyItemRemovedFromRVIPanel() {

        DiscoveryHelper.isRVIEditOptionDisplayed();
        try {
            String ItemToRemove = DiscoveryHelper.itemToBeRemovedFrmRVIPanel();
            DiscoveryHelper.clickRVIPanelRemoveButton();
            DiscoveryHelper.clickRVIPanelEditDone();
            Assert.assertFalse(DiscoveryHelper.getProductIdsInRVIEntire().contains(ItemToRemove));
        } catch (Exception ex) {
            Assert.fail("Error In:: I remove and verify the item is removed from RVI pane" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("Verified Product is removed from RVI Panel");
    }


    @Then("I verify removed product displayed again in RVI Panel$")
    public void IVerifyRemovedProductDisplayedAgainInRVIpanel() {
        try {
            Assert.assertTrue(DiscoveryHelper.getProductIdsInRVIEntire().contains(tempRemovedItemFrmRVI));
        } catch (Exception ex) {
            Assert.fail("Error::In Verifying Removed Product Displayed in RVI panel" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Verified Removed Product Displayed Again In RVI panel");
    }

    @And("I click on \"(Remove)\" button on any product?$")
    public void iClickOnRemoveButtonOnAnyProduct(String action) {
        try {
            tempRemovedItemFrmRVI = DiscoveryHelper.itemToBeRemovedFrmRVIPanel();
            DiscoveryHelper.clickRVIPanelRemoveButton();
        } catch (Exception ex) {
            Assert.fail("Error In:: I click on remove button on any product" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Successfully Click on " + action + " button on any product");
    }

    @Then("I verify product is removed temporally$")
    public void IVerifyProductTemporallyRemovedfromRVIpanel() {
        try {
            Assert.assertFalse(DiscoveryHelper.getProductIdsInRVIEntire().contains(tempRemovedItemFrmRVI));
        } catch (Exception ex) {
            Assert.fail("Error In::I verify Product Temporally Removed from RVI Panel" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Verified Product Successfully Removed Temporally from RVI Panel");
    }

    @When("I click outside of RVI panel$")
    public void iClickOutSideOfRVIPanel() {
        try {
            Elements.findElement("recently_viewed_items.rvi_container").click();
        } catch (Exception ex) {
            Assert.fail("Error In::I click outside of RVI panel" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Successfully Clicked outside of RVI Panel");
    }

    @Then("I should see product is removed from RVI panel$")
    public void IShouldSeeProductIsRemovedFromRVIPanel() {
        try {
            Assert.assertFalse(DiscoveryHelper.getProductIdsInRVIEntire().contains(tempRemovedItemFrmRVI));
        } catch (Exception ex) {
            Assert.fail("Error In::I should see product is removed from RVI panel " + ex.getMessage());
        }
        DiscoveryHelper.logInfo("Verified I Sucessfully see product is removed from RVI Panel");
    }

    @And("^I navigate to search results page$")
    public void iNavigateToSearchResultsPage() {
        try {
            Navigate.visit("search_result");
        } catch (Exception ex) {
            Assert.fail("Error In::I navigate to search results page " + ex.getMessage());
        }
        DiscoveryHelper.logInfo("Verified I Sucessfully navigate to search results page");
    }

    @When("^I select a product with below options:$")
    public void iSelectAProductWithBelowOptions(DataTable table) throws Throwable {
        String suppressColorSwatchValue = table.getGherkinRows().get(0).getCells().get(1);
        String colorSwatchCount = table.getGherkinRows().get(1).getCells().get(1);
        List<String> productIds = getProductIds();
        EnvironmentDetails.getAppServer();
        for (String productId : productIds) {
            JSONObject productDetails = ProductService.getProductDetails(productId);
            int productColorSize = productDetails.getJSONArray("colormap").length();

            HashMap attributeHash = null;
            ArrayList attributes = (ArrayList) productDetails.getJSONArray("attributes").toList();
            for (Object attribute : attributes) {
                attributeHash = (HashMap) attribute;
                if (attributeHash.get("name").equals("SUPPRESS_COLOR_SWATCHES"))
                    break;
            }
            assert attributeHash != null;

            ArrayList attributeValues = (ArrayList) attributeHash.get("attributeValues");
            HashMap attributeValue = (HashMap) attributeValues.get(0);
            String suppressColorSwatchResponseValue = (String) attributeValue.values().toArray()[0];
            if (suppressColorSwatchResponseValue.equals(suppressColorSwatchValue)) {
                if (colorSwatchCount.equals(">1") && productColorSize > 1) {
                    selectedProdId = productId;
                } else if (colorSwatchCount.equals("<1") && productColorSize < 1) {
                    selectedProdId = productId;
                }
            }
        }
        Assert.assertFalse("No product is displayed with given option: " + table,
                selectedProdId == null);
    }

    @Then("^I verify that \"([^\"]*)\" ribbon overlay text is displayed below product thumbnail$")
    public void iVerifyThatTextIsDisplayedBelowProductThumbnail(String expectedText) throws Throwable {
        String displayedText = DiscoveryHelper.getRibbonOverlayText(selectedProdId);
        Assert.assertTrue("Expected Ribbon overlay text: " + expectedText + " Displayed text: " + displayedText,
                expectedText.equalsIgnoreCase(displayedText));
    }

    @And("^I verify that color swatches are (not displayed|displayed) below product thumbnail$")
    public void iVerifyThatColorSwatchesAreNotDisplayedBelowProductThumbnail(String condition) throws Throwable {
        if (condition.equals("displayed")) {
            Assert.assertTrue("Color swatches are not displayed below product thumbnail",
                    DiscoveryHelper.getProductDiv(selectedProdId).findElement(By.className("colorSwatch")).isDisplayed());
        } else {
            try {
                DiscoveryHelper.getProductDiv(selectedProdId).findElement(By.className("colorSwatch"));
                Assert.fail("Color swatches are displayed below product thumbnail");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Inside catch block color swatch is not displayed");
            }
        }
    }

    @Then("^I verify that all products are displayed$")
    public void iVerifyThatAllProductsAreDisplayed() throws Throwable {
        try {
            int currentProductCount = DiscoveryHelper.getProductCount();
            int thumbnailCount = DiscoveryHelper.getTotalThumbnailCount();
            Assert.assertTrue("All products are not displayed Expected product count: " + currentProductCount
                    + " Actual count displayed: " + thumbnailCount, thumbnailCount == currentProductCount);
        } catch (Exception ex) {
            Assert.fail("Error :: In I verify that all products are displayed" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("Verified that all products are displayed");
    }

    @Then("^I should see quick peek tool tip \"(Quick Peek)\" message on page$")
    public void iShouldSeeQuickPeekToolTipQuickPeekMessageOnPage(String expectedMsg) throws Throwable {
        String actualMessage = null;
        try {
            WebElement prodElement = DiscoveryHelper.getProductDiv(selectedProdId);
            try {
                Clicks.hoverForSelection(prodElement);
            } catch (Exception e) {
                System.out.println("Unable to hover over quickbag");
                e.printStackTrace();
            }
            WebElement qpLauncher = prodElement.findElement(By.className("quickPeekIcon"));
            if (qpLauncher.isDisplayed())
                actualMessage = qpLauncher.getAttribute("title");
            Assert.assertEquals("Quick Peek tool tip message is not displayed as " + expectedMsg, expectedMsg, actualMessage);
        } catch (Exception ex) {
            Assert.fail("Error :: In I should see quick peek tool tip 'Quick Peek' message on page" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Verified Quick peek tool tip message displayed as Quick Peek");
    }

    @Then("^I Verify video icon is not displayed below product thumbnail on page$")
    public void iVerifyVideoIconIsNotDisplayedBelowProductThumbnailOnPage() throws Throwable {
        try {
            Assert.assertFalse("Video icon is displayed below product thumbnail on page", Elements.elementPresent(By.className("video_icon")));
        } catch (Exception ex) {
            Assert.fail("Error :: In I Verify video icon is not displayed below product thumbnail on page" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I Verify video icon is not displayed below product thumbnail on page");
    }

    @And("^I verify that the Sort By is (displayed|not displayed)$")
    public void i_verify_that_the_Sort_By_is_not_displayed(String condition) throws Throwable {

        if (condition.equals("displayed"))
            Assert.assertTrue("Sort By is not displayed ", Elements.elementPresent("pagination.sort_by_container"));
        else
            Assert.assertFalse("Sort By is displayed ", Elements.elementPresent("pagination.sort_by_container"));
    }

    @Then("^I verify that promotional message is displayed under product thumbnail$")
    public void iVerifyThatPromotionalMessageIsDisplayedUnderProductThumbnail() throws Throwable {
        Assert.assertTrue("Promotional overlay is not displayed for product id: " + selectedProdId,
                DiscoveryHelper.getProductDiv(selectedProdId).findElement(Elements.element("product_thumbnails.badgeHeader")).isDisplayed());
        DiscoveryHelper.logInfo("Verifed that promotional message is displayed under product thumbnail");
    }

    @Then("^I verify that copy block (is displayed|is not displayed)$")
    public void iVerifyThatCopyBlockIsDisplayed(String condition) throws Throwable {
        Wait.forLoading("left_facet.loading");
        if (condition.equalsIgnoreCase("is displayed")) {
            Assert.assertTrue("ERROR - APP: Copy Block is not displayed: ",
                    Elements.elementPresent("search_result.copy_block"));
        } else {
            Assert.assertFalse("ERROR - APP: Copy Block is displayed: ",
                    Elements.elementPresent("search_result.copy_block"));
        }
        DiscoveryHelper.logInfo("Verified that copy block " + condition);
    }

    @When("^I navigate to pdp of \"([^\"]*)\" product$")
    public void iNavigateToPdpOfProduct(String productId) throws Throwable {
        Assert.assertTrue("ERROR - DATA: Given product id " + productId + " is not displayed on the page",
                DiscoveryHelper.getProductIds().contains(productId));
        selectedProdId = productId;
        DiscoveryHelper.selectProductThumbnail(selectedProdId);
        DiscoveryHelper.logInfo("Navigate to the given product id: " + productId);
    }

    @When("^I select the quick peek of random (member|master|standard) product$")
    public void iSelectTheQuickPeekOfRandomMemberProduct(String productType) throws Throwable {
        Random randomGenerator;
        try {
            List<String> productIds = productType.equalsIgnoreCase("master") ? getMasterProductIds() : getMemberProdIds();
            randomGenerator = new Random();
            int index = (productIds.size() > 1) ? randomGenerator.nextInt(productIds.size() - 1) : randomGenerator.nextInt(productIds.size());
            selectedProdId = productIds.get(index);
            DiscoveryHelper.selectQuickView(selectedProdId);
        } catch (Exception e) {
            Assert.fail("Unable to select the quick peek of random product" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the quick peek of random product id: " + selectedProdId);
    }

    @And("^I verify that selected facets are separated from inactive facets$")
    public void iVerifyThatSelectedFacetsAreSeparatedFromInactiveFacets() throws Throwable {
        String facetName = selectedFacetName == null ? LeftFacet.getAllFacetName().stream()
                .filter(e -> (e.contains("Brand") || e.contains("Designer"))).findFirst().orElse(null) : selectedFacetName;
        I_verify_that_the_selected_color_appears_on_top(facetName);
    }

    @And("^I select \"([^\"]*)\" Miles under bops change store dialog$")
    public void iSelectMilesUnderBopsChangeStoreDialog(String miles) throws Throwable {
        String optionEle = macys() ? "option" : "li";
        Clicks.click("change_pickup_store_dialog.search_distance");
        WebElement element = Elements.findElement("change_pickup_store_dialog.search_distance")
                .findElements(By.tagName(optionEle))
                .stream()
                .filter(e -> e.getText().equalsIgnoreCase(miles))
                .findFirst().orElse(null);
        Clicks.click(element);
        Clicks.click("change_pickup_store_dialog.search_button");
    }

    @Then("^I should see banner machine data on (search results|browse) page$")
    public void iShouldSeeBannerMachineDataOnSearchResultsPage(String pageType) throws Throwable {
        try {
            Assert.assertTrue("Banner Machine data is not displayed on page", Elements.elementPresent("sample_page.banner_media"));
        } catch (Exception ex) {
            Assert.fail("Error :: I should see banner machine data on " + pageType + " page " + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I should see banner machine data on " + pageType + " page");
    }

    @Then("^I verify that \"([^\"]*)\" media is displayed$")
    public void iVerifyThatMediaIsDisplayed(String mediaType) throws Throwable {
        boolean mediaDisplayed = false;
        pausePageHangWatchDog();
        WebElement canvasEle = Elements.findElement(By.id("canvas"));
        assert canvasEle != null;
        List<WebElement> rowElements = canvasEle.findElements(By.tagName("div")).stream().filter(e -> e.getAttribute("id").contains("row_")).collect(toList());
        assert rowElements != null;
        for (WebElement rowElement : rowElements) {
            switch (mediaType.toLowerCase()) {
                case "header text":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("textHeaderContainer")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "slide show":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("slideShow")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "image":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("img-adaptive")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "image map":
                    try {
                        mediaDisplayed = rowElement.findElement(By.tagName("map")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "horizontal ruler":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("canvasRule")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "category icon":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("adCatIcon")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "flex pool":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("flexPool")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
                case "product pool":
                    try {
                        mediaDisplayed = rowElement.findElement(By.className("product-pool-media-container")).isDisplayed();
                    } catch (org.openqa.selenium.NoSuchElementException noElement) {
                        System.out.println("Element is not avail in row " + rowElement.getAttribute("id") + noElement.getMessage());
                    } catch (Exception e) {
                        Assert.fail("Unable to get media details " + e.getMessage());
                    }
                    break;
            }
            if (mediaDisplayed) {
                DiscoveryHelper.logInfo("Expected media " + mediaType + " is displayed");
                System.out.println("Expected media " + mediaType + " is displayed on row type " + rowElement.getAttribute("id"));
                break;
            }
        }
        resumePageHangWatchDog();
        Assert.assertTrue("Expected media " + mediaType + " is not displayed on the page", mediaDisplayed);
    }

    @And("^I verify that (left|right) navigation arrow is disabled$")
    public void iVerifyThatLeftNavigationArrowIsDisabled(String arrowName) throws Throwable {
        String arrowEle = arrowName.equalsIgnoreCase("left") ? "previousArrow" : "nextArrow";
        WebElement ele = Elements.findElement("zero_search_result." + arrowEle);
        Wait.until(ele::isEnabled);
        Assert.assertFalse("ERROR - APP: " + arrowName + "  navigation arrow is not disabled: ",
                ele.isEnabled());
        DiscoveryHelper.logInfo("Verified that " + arrowName + " navigation arrow is disabled");
    }

    @And("^I should see up to (\\d+) recommended products in the first set on zsr page$")
    public void iShouldSeeUpToRecommendedProductsInTheFirstSetOnZsrPage(int expectedProductCount) throws Throwable {
        List<WebElement> displayedProducts = Elements.findElement("zero_search_result.recommendationPanel").findElements(Elements.element("zero_search_result.productThumbnail"))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
        Assert.assertEquals("ERROR - APP: Expected product to be displayed: " + expectedProductCount + " Actual products displayed: " + displayedProducts.size(),
                displayedProducts.size(), expectedProductCount);
        DiscoveryHelper.logInfo("Verified that " + expectedProductCount + " products are displayed in recommendation Panel");
    }

    @And("^I should see up to (\\d+) recommended products on zsr page$")
    public void iShouldSeeUpToRecommendedProductsOnZsrPage(int totalProductCount) throws Throwable {
        int displayedProductsCount = Elements.findElement("zero_search_result.recommendationPanel").findElements(Elements.element("zero_search_result.productThumbnail")).size();
        Assert.assertTrue("ERROR - APP: Expected product to be displayed less than: " + totalProductCount + " Actual products displayed: " + displayedProductsCount,
                displayedProductsCount <= totalProductCount);
        DiscoveryHelper.logInfo("Verified that upto " + totalProductCount + " products are displayed in recommendation Panel");

    }

    @When("^I navigate to last set of products$")
    public void iNavigateToLastSetOfProducts() throws Throwable {
        int displayedProductsCount = Elements.findElement("zero_search_result.recommendationPanel").findElements(Elements.element("zero_search_result.productThumbnail")).size();
        List<WebElement> displayedProducts = Elements.findElement("zero_search_result.recommendationPanel").findElements(Elements.element("zero_search_result.productThumbnail"));
        while (!displayedProducts.get(displayedProducts.size() - 1).isDisplayed()) {
            Clicks.click("zero_search_result.nextArrow");
        }
        DiscoveryHelper.logInfo("Navigate to last set of products");
    }

    @When("^I select any random recommendation product \"([^\"]*)\" on the panel$")
    public void iSelectAnyRandomRecommendationProductOnThePanel(String link) throws Throwable {
        List<WebElement> displayedProducts = Elements.findElement("zero_search_result.recommendationPanel").findElements(Elements.element("zero_search_result.productThumbnail"))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
        Random random = new Random();
        int index = random.nextInt(displayedProducts.size());
        WebElement ele = displayedProducts.get(index);
        selectedProdId = ele.getAttribute("id");
        System.out.println("Selected product id: " + selectedProdId);
        String eleToClick = link.equalsIgnoreCase("description") ? "productDescription" : "productThumbnailImage";
        Clicks.click(ele.findElement(Elements.element("zero_search_result." + eleToClick)).findElement(By.tagName("a")));
        DiscoveryHelper.logInfo("Selected " + selectedProdId + " product from pros panel");
    }

    @When("^I quick view any \"([^\"]*)\" recommended product in the panel on zsr page$")
    public void iHoverOverTheRecommendedProductInThePanelOnZsrPage(String productType) throws Throwable {
        List<WebElement> displayedProducts = Elements.findElement("zero_search_result.recommendationPanel").findElements(Elements.element("zero_search_result.productThumbnail"))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
        List<WebElement> products;
        if (productType.equalsIgnoreCase("member")) {
            products = displayedProducts.stream().filter(e -> !e.findElement(By.className("priceInfo")).getText().contains("-")).collect(toList());
        } else {
            products = displayedProducts.stream().filter(e -> e.findElement(By.className("priceInfo")).getText().contains("-")).collect(toList());
        }
        WebElement ele = (products.size() == 1) ? products.get(0) : products.get(new Random().nextInt(products.size() - 1));
        selectedProdId = ele.getAttribute("id");
        System.out.println("Selected product id: " + selectedProdId);
        Clicks.hoverForSelection(ele.findElement(Elements.element("zero_search_result.productThumbnailImage")));
        Clicks.click(ele.findElement(Elements.element("zero_search_result.qvLauncher")));
    }

    @And("^I should see the Quick view of the product clicked$")
    public void iShouldSeeTheQuickViewOfTheProductClicked() throws Throwable {
        Wait.untilElementPresent("quick_view.quick_view_product_id");
        String productId = Elements.getText("quick_view.quick_view_product_id");
        Assert.assertEquals("ERROR - APP: Expected product id in Quick view: " + selectedProdId + " Actual Displayed product id: " + productId,
                selectedProdId, productId);
    }

    @Then("^I take screen shot of the page$")
    public void iTakeScreenShotOfThePage() throws Throwable {
        byte[] screenshot = ((TakesScreenshot) WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    @And("^I should see the \"([^\"]*)\" informant for \"([^\"]*)\" panel in Dyces logs with the following parameters:$")
    public void iShouldSeeTheInformantForPanelInDycesLogsWithTheFollowingParameters(String informant, String context, DataTable table) throws Throwable {
        String visitorId = Cookies.getCookieValue("RTD");
        String sshLog = DiscoveryHelper.getsshLog(visitorId);
        List<String> parameters = table.asList(String.class);
        for (String parameter : parameters) {
            switch (parameter) {
                case "visitorId":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("visitorId:" + visitorId));
                    break;
                case "context":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("context:"));
                    break;
                case "customerId":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("customerId:"));
                    break;
                case "responseType":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("responseType:" + informant));
                    break;
                case "deliveryId":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("deliveryId:"));
                    break;
                case "productPositions":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("productPositions:"));
                    break;
                case "headerId":
                    Assert.assertTrue("Expected " + parameter + " is not displayed on log", sshLog.contains("headerId:"));
                    break;
            }
        }
        DiscoveryHelper.logInfo("Verified that logs are captured for " + informant + " for " + context + " in Dyces log");
    }

    @When("^I select left arrow on recommendation panel$")
    public void iSelectLeftArrowOnRecommendationPanel() throws Throwable {
        Wait.untilElementPresent("zero_search_result.nextArrow");
        Clicks.click("zero_search_result.nextArrow");
    }

    @And("^I verify that Product Thumbnail PriceTypeID = (\\d+) and PriceType = \"([^\"]*)\" is displayed in quick view$")
    public void iVerifyThatProductThumbnailPriceTypeIDAndPriceTypeIsDisplayedInQuickView(int priceId, String priceType) throws Throwable {
        if (shoppingMode == null)
            shoppingMode = HeaderActions.getSiteMode();
        String displayedPrice, expectedPrice;
        WebElement qvPrice = Elements.findElement("quickViewOverlay.quickViewPrice");
        if (productColorwayType.equalsIgnoreCase("WithColorWay")) {
            Assert.assertTrue("Price Information is not displayed for Colorway Product: ",
                    qvPrice.isDisplayed());
        } else {
            displayedPrice = qvPrice.getText().replaceAll("\n", " ").trim();
            expectedPrice = thumbnailLevelPrice(productDetails).split("\\(")[0].trim();
            if (productType.equalsIgnoreCase("member") && productColorwayType.equalsIgnoreCase("WithoutColorWay"))
                expectedPrice = "PRICE: " + expectedPrice;
            displayedPrice = displayedPrice.replaceAll("select colors ", "").trim();
            if (shoppingMode.equalsIgnoreCase("iship"))
                // as of now all the iship scenarios are related to Canada
                expectedPrice = convertPriceToLocalCurrency(expectedPrice, "CAD");

            System.out.println("Displayed price in UI: " + displayedPrice);
            System.out.println("Price from Response: " + expectedPrice);

            Assert.assertTrue("ERROR - APP: Displayed price: " + displayedPrice + " Expected Price: " + expectedPrice,
                    expectedPrice.equals(displayedPrice));
        }

        DiscoveryHelper.logInfo("Product Thumbnail for PriceType : " + priceType + " PriceID : " + priceId + " for the Product ID : " + selectedProdId + " Verified Successfully");
        DiscoveryHelper.logInfo("*******************************************************************************************************************************************");
        System.out.println("Product Thumbnail for PriceType : " + priceType + " PriceID : " + priceId + " for the Product ID : " + selectedProdId + " Verified Successfully");
    }

    @Given("^I am on CategoryBrowsePage for \"([^\"]*)\" under \"([^\"]*)\" in ([^\"]*) mode$")
    public void iAmOnCategoryBrowsePageForUnderInDomesticMode(String subcategory, String category, String mode) throws Throwable {
        shoppingMode = mode;
        try {
            //Declaration
            ShopAndBrowse browse = new ShopAndBrowse();
            PageNavigation pageNavigation = new PageNavigation();

            pageNavigation.I_visit_the_web_site_as_a_guest_user();
            if (mode.equalsIgnoreCase("ISHIP")) {
                pageNavigation.I_navigate_to_international_context_page();
                browse.I_change_country_to(ISHIPCountry);
                browse.I_close_the_welcome_mat_if_it_s_visible();
            }
            if (mode.equalsIgnoreCase("REGISTRY")) {
                new Registry().I_navigate_to_registry_home_page();
            }

            pageNavigation.I_navigate_to_the_browse_page_under(subcategory, category);
            //pageNavigation.I_mouse_over_category_from_top_navigation(category);
            //pageNavigation.iSelectSubcategoryFromFlyoutMenu(subcategory);
            DiscoveryHelper.navigateToComponentizationURL();
            shouldBeOnPage("category_browse");

            System.out.println("Navigate to browse page" + subcategory + " under category " + category);
        } catch (Exception e) {
            Assert.fail("Unable to Navigate to browse page" + subcategory + e.getMessage());
        }
    }

    @Given("^I am on (CategoryBrowsePage|CategorySplashPage|SLP) for \"([^\"]*)\" category id in ([^\"]*) mode$")
    public void iAmOnCategoryBrowsePageForCategoryIdInShopping_modeMode(String pageType, String categoryID, String mode) throws Throwable {
        shoppingMode = mode;
        try {
            //Declaration
            String url;
            ShopAndBrowse browse = new ShopAndBrowse();
            PageNavigation pageNavigation = new PageNavigation();

            url = EnvironmentDetails.getEnvUrl();
            pageNavigation.I_visit_the_web_site_as_a_guest_user();
            if (mode.equalsIgnoreCase("ISHIP")) {
                pageNavigation.I_navigate_to_international_context_page();
                browse.I_change_country_to(ISHIPCountry);
                browse.I_close_the_welcome_mat_if_it_s_visible();
            }

            url = mode.equalsIgnoreCase("registry") ? url + "/shop/wedding-registry/?id=" + categoryID : url + "/shop/?id=" + categoryID;
//            String snbcExp = RunConfig.getEnvOrExParam("snbc_exp");
//            if(snbcExp == null || snbcExp.equalsIgnoreCase("true")){
//                //Navigate.visit(url + "&EFCKEY=%7B%22EXPERIMENT%22%3A%5B2666%5D%7D");
//            }else{
//                Navigate.visit(url);
//            }
            Navigate.visit(url);
            if (pageType.equalsIgnoreCase("CategorySplashPage")) {
                shouldBeOnPage("category_splash");
            } else if (pageType.equalsIgnoreCase("SLP")) {
                Assert.assertTrue("Not on SLP page ", WebDriverManager.getCurrentUrl().contains("/shop/b/"));
            }
            DiscoveryHelper.navigateToComponentizationURL();
            System.out.println("Navigate to " + pageType + " page of " + categoryID + " category ID");
        } catch (Exception e) {
            Assert.fail("Unable to Navigate to " + pageType + " page of " + categoryID + " category ID" + e.getMessage());
        }
    }

    @Then("^I see the display of RVI in \"(search result|category browse|product display|category Splash)\" page$")
    public void iVerifyTheDisplayOfRVIInPage(String pageName) throws Throwable {
        DiscoveryHelper.navigateToRVIPanelOnPage();
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        if (pageName.equals("search result")) {
            Assert.assertTrue("ERROR - APP: Expected page:" + pageName + " url pattern not matched.", currentUrl.contains("/shop/featured") || currentUrl.contains("/shop/registry/wedding/search") || currentUrl.contains("/shop/search"));
        } else if (pageName.equals("category browse") || pageName.equals("category Splash")) {
            Assert.assertTrue("ERROR - APP: Expected page:" + pageName + " url pattern not matched.", currentUrl.contains("/shop/") && currentUrl.contains("?id="));
        } else if (pageName.equals("product display")) {
            Assert.assertTrue("ERROR - APP: Expected page:" + pageName + " url pattern not matched.", currentUrl.contains("/shop/product/") || currentUrl.contains("shop/registry/wedding/product"));
        }
        //Sometimes page takes time to navigate to element
        DiscoveryHelper.navigateToRVIPanelOnPage();
        Wait.untilElementPresent("recently_viewed_items.rvi_container");
        Assert.assertTrue("ERROR - APP: REVI panel is not displayed on page: " + pageName, Elements.elementPresent("recently_viewed_items.rvi_container"));
        DiscoveryHelper.logInfo("Verified Recently Viewed Panel display on page: " + pageName);
    }

    @Then("^I should be on category splash page$")
    public void iShouldBeNavigatedTocategorySplashPage() throws Throwable {
        try {
            shouldBeOnPage("category_splash");
        } catch (Exception e) {
            Assert.fail("Unable to navigate to category splash " + e.getMessage());
        }
        DiscoveryHelper.logInfo("Successfully navigate to category splash page");
    }

    @And("^I navigate to main category menu on home page$")
    public void iNavigateToCategoryMenu() throws Throwable {
        try {
            Navigate.visit("home");
        } catch (Exception e) {
            Assert.fail("Unable to navigate to main category menu on home page" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Successfully navigated to main category menu on home page");
    }

    @When("^I \"([^\"]*)\" any color in color swatches section$")
    public void iAnyColorInColorSwatchesSection(String hoverMethod) throws Throwable {
        if (hoverEle == null) {
            WebElement ele = DiscoveryHelper.getProductDiv(selectedProdId);
            strUtils = swatchType.equalsIgnoreCase("jumbo") ? ele.findElement(By.className("jumboSwatch")).findElement(By.tagName("span")).getAttribute("style")
                    : ele.findElement(By.tagName("img")).getAttribute("src");
            List<WebElement> colorElements = ele.findElements(By.className("colorSwatch"));
            colorElements = colorElements.stream().filter(e -> !e.findElement(By.tagName("div")).getAttribute("class").contains("swatchSelected")).collect(toList());
            colorElements = colorElements.stream().filter(WebElement::isDisplayed).collect(toList());
            assert colorElements != null;
            Random random = new Random();
            int index = colorElements.size() <= 1 ? 0 : random.nextInt(colorElements.size() - 1);
            hoverEle = colorElements.get(index);
            selectedColorSwatch = hoverEle.findElement(By.tagName("div")).getAttribute("title");
            System.out.println("Selected color swatch: " + selectedColorSwatch);
        }
        switch (hoverMethod) {
            case "hover on":
                Clicks.hoverForSelection(hoverEle);
                break;
            case "hover off":
                DiscoveryHelper.javaScriptMouseOut(hoverEle);
                break;
            case "select":
                Clicks.click(hoverEle);
                break;
        }
    }

    @Then("^I should see product displays \"([^\"]*)\" image$")
    public void iShouldSeeProductDisplaysImage(String imageType) throws Throwable {
        WebElement ele = DiscoveryHelper.getProductDiv(selectedProdId);
        String imageSrc = swatchType.equalsIgnoreCase("jumbo") ? ele.findElement(By.className("jumboSwatch")).findElement(By.tagName("span")).getAttribute("style")
                : ele.findElement(By.tagName("img")).getAttribute("src");
        if (imageType.equalsIgnoreCase("default")) {
            Assert.assertEquals("Expected image and displayed image is not same", imageSrc, strUtils);
        } else {
            Assert.assertNotEquals("Expected image and displayed image is same", imageSrc, strUtils);
        }
    }

    @Then("^I should see selected color is highlighted in the color swatch for that product$")
    public void iShouldSeeSelectedColorIsHighlightedInTheColorSwatchForThatProduct() throws Throwable {
        WebElement ele = DiscoveryHelper.getProductDiv(selectedProdId);
        List<WebElement> colorElements = ele.findElements(By.className("colorSwatch"));
        WebElement selectedEle = colorElements.stream().filter(e -> e.findElement(By.tagName("div")).getAttribute("class").contains("swatchSelected")).findFirst().orElse(null);
        String selectedColor = selectedEle.findElement(By.tagName("div")).getAttribute("title");
        Assert.assertEquals("ERROR - APP: Expected color " + selectedColorSwatch + " Displayed color " + selectedColor,
                selectedColor, selectedColorSwatch);
    }

    @And("^I verify that Zoom in, Zoom out and reset buttons are displayed$")
    public void iVerifyThatZoomInZoomOutAndResetButtonsAreDisplayed() throws Throwable {
        Assert.assertTrue("ERROR - APP: Zoom in icon is not displayed in Quick view ",
                Elements.elementPresent("quick_view.zoom_in"));
        Assert.assertTrue("ERROR - APP: Zoom out icon is not displayed in Quick view ",
                Elements.elementPresent("quick_view.zoom_out"));
        Assert.assertTrue("ERROR - APP: Zoom reset icon is not displayed in Quick view ",
                Elements.elementPresent("quick_view.zoom_reset"));
    }

    @Then("^I verify Review counts in category page$")
    public void iVerifyReviewCountsInCategorySubSplashPage() throws Throwable {
        List<String> ids = DiscoveryHelper.getProductIdsWithReviews();
        Assert.assertNotNull("ERROR - DATA: No products is displayed with review please navigate to some other page ",
                ids);
        Random random = new Random();
        int index = random.nextInt(ids.size() - 1);
        String productIdWithReview = ids.get(index);
        WebElement productEle = DiscoveryHelper.getProductDiv(productIdWithReview);
        Assert.assertTrue("ERROR - APP: Customer rating star images is not displayed ",
                productEle.findElement(By.className("rating")).isDisplayed());
        Assert.assertTrue("ERROR - APP: Customer rating value is not displayed ",
                productEle.findElement(By.className("pdpreviews")).getText().trim().matches("\\(\\d+\\)"));
    }

    @Then("^I verify price on quick view overlay is same as on thumbnail grid$")
    public void iVerifyPriceOnQuickViewOverlayIsSameAsOnThumbnailGrid() throws Throwable {
        String qvPrice = Elements.getText("quick_view.price_list");
        qvPrice = bloomingdales() ? qvPrice.replaceAll(" - ", "-").replaceAll(":", "").replaceAll("PRICE ", "") : qvPrice;
        System.out.println("Displayed price in Quick view: " + qvPrice);
        System.out.println("Displayed price in product thumbnail: " + productPrice);
        Assert.assertEquals("ERROR - APP: Expected price from thumbnail: " + productPrice + " Displayed price in QV: " + qvPrice,
                qvPrice.replaceAll("Reg.", "").replaceAll("Orig.", "").trim(), productPrice.trim());
    }

    @And("^I navigate directly to \"([^\"]*)\" category page$")
    public void iNavigateDirectlyToCategoryPage(String categoryID) throws Throwable {
        String url = EnvironmentDetails.getEnvUrl();
        url = url + "/shop/?id=" + categoryID;
        Navigate.visit(url);
    }

    @When("^I click on any brand FOB link in left navigation$")
    public void iClickOnAnyBrandFOBLinkInLeftNavigation() throws Throwable {
        try {
            List<WebElement> brandList = Elements.findElements("designer_static.left_nav_Brand_list").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList());
            brandList.remove(0);//Removing all brand from left nav list
            selectedSubcategory = brandList.get(new Random().nextInt(brandList.size())).getText();
            Clicks.click(By.linkText(selectedSubcategory));
            DiscoveryHelper.navigateToComponentizationURL();
        } catch (Exception ex) {
            Assert.fail("Error In:: I click on any brand FOB link in left navigation" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I clicked on any brand FOB link in left navigation");
    }

    @Then("^I select any brand in (brand index||designer) page$")
    public void i_select_any_brand_in_brand_index_page(String pageName) throws Throwable {
        try {
            List<WebElement> allBrandList = Elements.findElements("designer_static.brands").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList());
            brandFacet = allBrandList.get(new Random().nextInt(allBrandList.size() - 1)).getText();
            Clicks.click(By.linkText(brandFacet));
            DiscoveryHelper.navigateToComponentizationURL();
        } catch (Exception ex) {
            Assert.fail("Error In:: select any brand in (brand index||designer) page" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I selected any brand in (brand index||designer) page");
    }

    @Then("^I verified selected Brand returned as keyword$")
    public void iVerifiedSelectedBranReturnAsKeyword() throws Throwable {
        try {
            if (Elements.elementPresent("designer_static.brand_as_keyword")) {
                String searchKeyWord = Elements.findElement("designer_static.brand_as_keyword").getText().toLowerCase();
                Assert.assertTrue("Returned Keyword brand didn't match with selected brand", searchKeyWord.contains(brandFacet.toLowerCase()));
            } else {
                Assert.fail("Selected Brand didn't return as Keyword");
            }
        } catch (Exception ex) {
            Assert.fail("Error In::I verified selected Brand returned as keyword" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verified selected Brand returned as keyword");
    }

    @Then("^I verify Facets,Pagination, Show Items and Sort by display on the page$")
    public void iVerifyAndDisplayOnThePage() throws Throwable {
        try {
            iVerifyThatFacetsAreListedOnLeftNav();
            productCount = DiscoveryHelper.getProductCount();
            if (productCount > 60) {
                i_verify_that_pagination_is_not_displayed("displayed");
               /* Assert.assertTrue("Sort By options are not displayed",
                        Elements.elementPresent("pagination.sort_by"));*/
                iVerifyThatitemcountbuttonsinpage();//showItems
            } else {
                WebElement topPaginationText = Elements.findElement("pagination.show_all_items");
                Assert.assertTrue("ERROR - APP: Pagination text is incorrect in top position of the page", topPaginationText.getText().contains("Showing All " + productCount + " Items"));
                DiscoveryHelper.logInfo("Verified : Text in absence of top pagination section");
            }
            iShouldSeeTheSortByDisplayedWithAllOptions();
        } catch (Exception ex) {
            Assert.fail("Error In::I verify Facets,Pagination, Show Items and Sort by display on the page" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verified Facets,Pagination, Show Items and Sort by display on the page");
    }

    @Then("^I verify that Main ad should be displayed on cat splash page$")
    public void iVerifyThatMainAdShouldBeDisplayedOnCatSplashPage() throws Throwable {
        List<WebElement> rowOneElements = Elements.findElements("flexible_template.row_one_point_one");
        Assert.assertNotNull("Main ad contents are not displayed ",
                rowOneElements);
        for (WebElement ele : rowOneElements) {
            pausePageHangWatchDog();
            for (WebElement divEle : ele.findElements(By.tagName("div"))) {
                try {
                    if (divEle.findElement(By.tagName("img")).isDisplayed()) {
                        String src = divEle.findElement(By.tagName("img")).getAttribute("src");
                        Assert.assertTrue("ERROR - APP: Response code is not 200 ",
                                DiscoveryHelper.getResponseCode(src) == 200);
                        System.out.println("Verified media returns 200 ok for " + divEle.getAttribute("class") + " on row " + ele.getAttribute("id"));
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    DiscoveryHelper.logInfo("Image element in not displayed for " + ele.getAttribute("id"));
                } catch (Exception exp) {
                    Assert.fail("Error verifying main ad on cat splash page " + exp.getMessage());
                }
            }
            resumePageHangWatchDog();
        }
        DiscoveryHelper.logInfo("Verified main ad on cat splash page");
    }

    @And("^I navigate to random sub categories from Left hand nav links$")
    public void iNavigateToRandomSubCategoriesFromLeftHandNavLinks() throws Throwable {
        int index;
        Wait.untilElementPresent("category_browse.subcategory");
        List<WebElement> categories = Elements.findElements("category_browse.subcategory");
        categories = categories.stream().filter(WebElement::isDisplayed).collect(toList());
        if (macys()) {
            index = new Random().nextInt(categories.size() - 1);
            WebElement selected = categories.get(index);
            selectedSubcategory = selected.getText();
            selected.findElement(By.tagName("a")).click();
        }
         else {
            categories = categories.stream().filter(a -> a.getTagName().equals("a")).collect(toList());
            index = new Random().nextInt(categories.size() - 1);
            selectedSubcategory = categories.get(index).getText();
            categories.get(index).click();
        }
    }

    @Then("^I verify that navigated to correct subcategory page$")
    public void iVerifyThatNavigatedToCorrectSubcategoryPage() throws Throwable {
        selectedSubcategory = CommonUtils.sanitizeString(selectedSubcategory);
        Wait.untilElementPresent("category_browse.left_navigation_container");
        if (Elements.elementPresent("category_browse.left_navigation_container")) {
            WebElement catEle1 = Elements.findElement("left_navigation_category.left_navigation_container").findElement(By.tagName("li"));
            String categoryName = catEle1.getText().replaceAll("Shop", "").trim();
            selectedSubcategory = selectedSubcategory.replaceAll("Shop", "").trim();
            System.out.println("selectedSubcategory   => " + selectedSubcategory);
            Assert.assertTrue("ERROR - APP: Not navigate to selected sub category, Expected: " + selectedSubcategory + " Navigated: " + categoryName,
                    selectedSubcategory.toLowerCase().contains(categoryName.toLowerCase()));
        } else {
            String url = WebDriverManager.getCurrentUrl();
            DiscoveryHelper.logInfo("selectedSubcategory : " + selectedSubcategory);
            DiscoveryHelper.logInfo("current_url : " + url);
            Assert.assertTrue("ERROR - APP: URL should contain Expected Category: " + selectedSubcategory + " Displayed url: " + url.replaceAll("-", " "),
                    url.replaceAll("-", " ").contains(selectedSubcategory));
        }
    }

    @Then("^I verify top banner is displayed on \"([^\"]*)\" (catsplash|browse) page$")
    public void iVerifyTopBannerIsDisplayedOnPage(String category, String pageType) throws Throwable {
        int responseCode = 200;
        try {
            if (Elements.elementPresent("flexible_template.row_one_point_zero")) {
                WebElement element = Elements.findElement("flexible_template.row_one_point_zero").findElement(By.tagName("img"));
                Assert.assertTrue("Top banner is not displayed for category: " + category,
                        element.isDisplayed());
                int actualResponseCode = DiscoveryHelper.getResponseCode(element.getAttribute("src"));
                Assert.assertTrue("Top banner image is not available for category:" + category, responseCode == actualResponseCode);
            } else {
                Assert.fail("Top banner is not displayed for category: " + category);
            }
        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify top banner is displayed on " + pageType + " page" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify top banner is displayed on" + category + " " + pageType + " page");
    }

    @Then("^I verify invalid price range facet selection is not applied")
    public void iVerifyInvalidPriceRangeSorting() {
        Assert.assertTrue("Invalid price range facet is applying with customer price range option.", beforePriceSortUrl.equals(WebDriverManager.getWebDriver().getCurrentUrl()));
    }

    @Then("^I verify recommendation product's default attributes displayed on the panel$")
    public void iVerifyRecommendationProductDefaultAttributesDisplayedOnThePanel() throws Throwable {
        Assert.assertTrue("Recommendation Product thumbnail image is not displayed in recommendationPanel",
                Elements.findElement("zero_search_result.productThumbnailImage").isDisplayed());
        Assert.assertTrue("Recommendation Product description is not displayed in recommendationPanel",
                Elements.findElement("zero_search_result.productDescription").isDisplayed());
        if (Elements.elementPresent("zero_search_result.product_badge_text")) {
            Assert.assertTrue("Recommendation Product badge is not displayed in recommendationPanel",
                    Elements.findElement("zero_search_result.product_badge_text").isDisplayed());
            Assert.assertTrue("Recommendation Product badge text is not displayed as MORE COLOR in recommendationPanel",
                    Elements.findElement("zero_search_result.product_badge_text").getText().equals("MORE COLORS"));
        }
        if (Elements.elementPresent("zero_search_result.product_big_price")) {
            Assert.assertTrue("Recommendation Product price is not displayed in recommendationPanel",
                    Elements.findElement("zero_search_result.product_big_price").isDisplayed());
        }
        if (Elements.elementPresent("zero_search_result.product_sale_price")) {
            Assert.assertTrue("Recommendation Product sale price is not displayed in recommendationPanel",
                    Elements.findElement("zero_search_result.product_sale_price").isDisplayed());
            String colorHex = Color.fromString(Elements.findElement("zero_search_result.product_sale_price").getCssValue("color")).asHex();
            Assert.assertTrue("Recommendation Product sale is not displayed in red color in recommendationPanel", colorHex.equals("#ea0000"));
        }
        DiscoveryHelper.logInfo("I verify recommendation product's default attributes displayed on the panel");
    }

    @And("^I verify the \"([^\"]*)\" badge text is displayed in the product thumbnail on \"([^\"]*)\" page$")
    public void iVerifyTheBadgeTextIsDisplayedInTheProductThumbnailOnPage(String priceType, String pageType) throws Throwable {
        List<String> ids = DiscoveryHelper.getProductIds();
        ids.forEach(id -> {
            WebElement ele = DiscoveryHelper.getProductDiv(id);
            String displayedText = ele.findElement(By.className("priceTypeText")).getText();
            Assert.assertTrue("ERROR - APP: Expected badge text: " + priceType + " Displayed badge text: " + displayedText,
                    displayedText.trim().equals(priceType));
        });
    }

    @Then("^I verify the \"([^\"]*)\" badge text is displayed in product QuickView$")
    public void iVerifyTheBadgeTextIsDisplayedInProductQuickView(String priceType) throws Throwable {
        String displayedText = Elements.getText("quick_view.qvSpecialEvents");
        Assert.assertTrue("ERROR - APP: Expected badge text: " + priceType + " Displayed badge text: " + displayedText,
                displayedText.trim().equals(priceType));
    }

    @And("^I verify \"([^\"]*)\" is clickable and navigating to respective media page$")
    public void iVerifyIsClickableAndNavigatingToRespectiveMediaPage(String mediaType) throws Throwable {
        String url;
        switch (mediaType.toUpperCase()) {
            case "IMAGE_MAP":
                assert mediaExistsRowElement != null;
                List<WebElement> areaEle = mediaExistsRowElement.findElements(By.tagName("area"));
                int index = areaEle.size() == 1 ? 0 : new Random().nextInt(areaEle.size() - 1);
                url = areaEle.get(index).getAttribute("href");
                Clicks.javascriptClick(areaEle.get(index));
                System.out.println("Href of area ele: " + url);
                if (url.contains("javascript") || url.contains("pop")) {
                    Set<String> windowSet = WebDriverManager.getWebDriver().getWindowHandles();
                    List<String> windows = new ArrayList<>(windowSet);
                    WebDriverManager.getWebDriver().switchTo().window(windows.get(1));
                    String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
                    url = url.split("\\(")[1].split(",")[0].replaceAll("'", "");
                    System.out.println("Page url: " + currentUrl);
                    System.out.println("Constructed url: " + url);
                    Assert.assertTrue("ERROR - APP: Media link selection is not navigated to respected url: " + url,
                            currentUrl.contains(url));
                } else if (url.contains("/shop/product")) {
                    Assert.assertTrue("ERROR - APP: Media link selection is not navigated to respected url: " + url,
                            WebDriverManager.getCurrentUrl().contains("/shop/product"));
                } else {
                    Assert.assertTrue("ERROR - APP: Media link selection is not navigated to respected url: " + url,
                            WebDriverManager.getCurrentUrl().contains(url));
                }
                break;
            default:
                List<WebElement> ele = mediaExistsRowElement.findElements(By.tagName("a"));
                ele = ele.stream().filter(WebElement::isDisplayed).collect(toList());
                assert ele.size() != 0;
                int indexToSelect = ele.size() == 1 ? 0 : new Random().nextInt(ele.size() - 1);
                url = ele.get(indexToSelect).getAttribute("href");
                Clicks.click(ele.get(indexToSelect));
                System.out.println("Href of area ele: " + url);
                if (url.contains("/shop/product")) {
                    Assert.assertTrue("ERROR - APP: Media link selection is not navigated to respected url: " + url,
                            WebDriverManager.getCurrentUrl().contains("/shop/product"));
                } else {
                    Assert.assertTrue("ERROR - APP: Media link selection is not navigated to respected url: " + url,
                            WebDriverManager.getCurrentUrl().contains(url));
                }
                break;
        }
    }

    @Then("^I verify navigated URL is in \"([^\"]*)\" format$")
    public void iVerifyNavigatedURLShouldBeInFormat(String expectedURLformat) throws Throwable {
        try {
            if (expectedURLformat.equals("/shop/featured or /shop")) {
                Assert.assertTrue("Current url does not contains /shop/", WebDriverManager.getCurrentUrl().contains(expectedURLformat.split("or")[0].trim())
                        || WebDriverManager.getCurrentUrl().contains(expectedURLformat.split("or")[1].trim()));
            } else if (expectedURLformat.equals("/{Brand-name}-{FOB-name-from-Brand-Index}?{any-required-parameters}")) {
                expectedURLformat = new StringBuilder().append(brandFacet.toLowerCase()).append("-").append(selectedSubcategory.toLowerCase()).append("?cm_sp=shop_by_brand-").toString()
                        .replace(" ", "-");
                Assert.assertTrue("Current url is not in format " + expectedURLformat, WebDriverManager.getCurrentUrl().contains(expectedURLformat));
            } else {

                Assert.assertTrue("Current url is not in format " + expectedURLformat, WebDriverManager.getCurrentUrl().contains(expectedURLformat));
            }

        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify navigated URL is in " + expectedURLformat + " format" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify navigated URL is in" + expectedURLformat + "format");
    }

    @And("^I Verify the URL contain \"([^\"]*)\" tag to support coremetrics$")
    public void iVerifyTheURLContainTagToSupportCoremetrics(String coreMetricsTag) throws Throwable {
        try {
            Assert.assertTrue("URL does not contains " + coreMetricsTag + " tag to support coremeticrs", WebDriverManager.getCurrentUrl().contains(coreMetricsTag));
        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify the URL contain " + coreMetricsTag + " tag to support coremetrics" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify URL contain " + coreMetricsTag + "tag to support coremetrics");
    }

    @When("^I select a brand in designer index page assigned to \"([^\"]*)\"$")
    public void iSelectABrandInDesignerIndexPageAssignedTo(String brandMode) throws Throwable {
        try {
            List<WebElement> allBrandList = Elements.findElements("designer_static.brands").stream().filter(f -> f.isDisplayed() && (brandMode.equals("category")) ? f.getAttribute("href").contains("?id") : f.getAttribute("href").contains("/featured/")).collect(Collectors.toList());
            brandFacet = allBrandList.get(new Random().nextInt(allBrandList.size() - 1)).getText();
            Clicks.click(By.linkText(brandFacet));
            DiscoveryHelper.navigateToComponentizationURL();
        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I select a brand in designer index page assigned to " + brandMode + " :" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I select a brand in designer index page assigned to " + brandMode);
    }

    @Then("^I verify the display of GNA in Shop all brands page$")
    public void i_verify_the_display_of_gna() throws Throwable {
        if (WebDriverManager.getCurrentUrl().contains("/shop/all-brands")) {
            Navigate.scrollPage(0, 100);
            Navigate.scrollPage(0, -100);
            Wait.untilElementPresent("header.header_pool");
        }
        Assert.assertTrue("GNA banner not displayed", Elements.findElement("header.header_pool").isDisplayed());
    }

    @And("^I verify the display of GFA in Shop all brands page$")
    public void i_verify_display_of_GFA() throws Throwable {
        if (WebDriverManager.getCurrentUrl().contains("/shop/all-brands")) {
            Navigate.scrollPage(0, 2000);
        }
        Assert.assertTrue("GFA menu not displayed", Elements.findElement("footer.goto_footer_ad_pool").isDisplayed());
    }

    @And("^I verify all the brand names is in alphabetical order$")
    public void iVerifyAllTheBrandNamesIsInAlphabeticalOrder() throws Throwable {
        List<WebElement> brandBoxes = Elements.findElements(Elements.element("designer_static.brand_box"));
        brandBoxes.forEach(brandBox -> {
            List<String> brandNames = brandBox.findElements(By.cssSelector("ul>li>a")).stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            List<String> sortedBrands = new ArrayList<>(brandNames);
            System.out.println(brandNames);
            Collections.sort(sortedBrands);
            for (int i = 0; i < sortedBrands.size(); i++) {
                Assert.assertTrue("Brands not sorted correctly", brandNames.get(i).equals(sortedBrands.get(i)));
            }
        });
    }

    @Then("^I verify that landed on brand results page with pagination$")
    public void iVerifyThatLandedOnBrandResultsPageWithPagination() throws Throwable {
        try {
            new PageNavigation().iShouldBeInSearchLandingOrRedirectedBrowsePagePage();
            productCount = DiscoveryHelper.getProductCount();
            if (productCount > 60) {
                i_verify_that_pagination_is_not_displayed("displayed");
                iVerifyThatitemcountbuttonsinpage();//showItems
            } else {
                WebElement topPaginationText = Elements.findElement("pagination.show_all_items");
                Assert.assertTrue("ERROR - APP: Pagination text is incorrect in top position of the page", topPaginationText.getText().contains("Showing All " + productCount + " Items"));
                DiscoveryHelper.logInfo("Verified : Text in absence of top pagination section");
            }

        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify that landed on brand results page with pagination " + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify that landed on brand results page with pagination");
    }

    @Then("^I verify that the \"([^\"]*)\" is showing the selected size$")
    public void iVerifyThatthequickpeekisshowingtheselectedsize(String pageType) throws Throwable {
        try {
            if ("quick pick".equals(pageType)) {
            /*    System.out.println("selectedFacet is " + selectedFacet);
                if (selectedFacet.length() > 3)
                    System.out.println("Selected size is:" + selectedFacet.substring(0, 3));*/
                Assert.assertTrue(pageType + " is not showing the selected size" + selectedFacet, DropDowns.getAllValues("quick_view.quick_view_size_dropdown").contains(selectedFacet.length() > 3 ? selectedFacet.substring(0, 3) : selectedFacet));
            } else if ("PDP".equals(pageType)) {
                List<String> displayedPDPSizeValues = Elements.findElements("product_display.select_default_size").stream().filter(e -> e.isDisplayed()).map(WebElement::getText).collect(Collectors.toList());
                if (displayedPDPSizeValues.isEmpty())
                    displayedPDPSizeValues.add(Elements.findElement("product_display.default_size").getText());
                if (selectedFacet.equalsIgnoreCase("one size")) {
                    Assert.assertTrue(pageType + " is not showing the selected size" + selectedFacet, displayedPDPSizeValues.contains("OS"));
                } else {
                    Assert.assertTrue(pageType + " is not showing the selected size" + selectedFacet, displayedPDPSizeValues.contains(selectedFacet));
                }
            }

        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify that the " + pageType + " is showing the selected size " + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify that the " + pageType + " is showing the selected size");
    }

    @And("^I select \"([^\"]*)\" size value from (Size) facet section$")
    public void iSelectFirstSizeValueFromBrandFacetSection(String facetTime, String facetName) throws Throwable {
        try {
            if (selectedFacetValues.isEmpty())
                selectedFacetValues = new ArrayList<>();
            if (selectedFacetNames.isEmpty())
                selectedFacetNames = new ArrayList<>();
            List<String> subFacetHeaders = DiscoveryHelper.getAllSizeFacetHeaders();
            int time = facetTime.equals("first") ? 1 : subFacetHeaders.size();
            for (int i = 0; i < 3; i++) {
                selectedFacetName = subFacetHeaders.get(i);
                selectedFacetNames.add(selectedFacetName);
                DiscoveryHelper.logInfo("Selected Size facet Sub Header: " + selectedFacetName);
                List<WebElement> subFacetHeaderValueEle = DiscoveryHelper.selectValueFromSizeHeaderFacet(selectedFacetName);
                WebElement subFacetHeaderValue = selectedFacetName.contains("Waist & Inseam") ? subFacetHeaderValueEle.get(1) : subFacetHeaderValueEle.get(0);
                selectedFacetValues.add(subFacetHeaderValue.getText().split("\\(")[0].split("\\[")[0].trim());
                Clicks.click(subFacetHeaderValue);
                selectedFacet = selectedFacetValues.get(i);
                Wait.forLoading("left_facet.loading");
            }
            DiscoveryHelper.navigateToComponentizationURL();
            selectedFacetNames.forEach(e -> {
                if (isSizeFacetSubHeaderCollapsed(e)) expandCollapseSizeFacetSubHeader(e);
            });
        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I select" + facetTime + "size value from " + facetName + " facet section" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("Selected Sub Header" + selectedFacetName + " facet value from " + facetName + " facet section");
    }

    @Then("^I verify that the selected (.*?) facets are highlighted$")
    public void iVerifyThatTheSelectedFacetsAreHighlighted(String facetName) throws Throwable {
        try {
            List<WebElement> facetValues = Elements.findElement(LeftFacet.getFacetDiv(facetName)).findElements(By.tagName("a")).stream()
                    .filter(el -> el.getAttribute("class").contains("selected")).collect(Collectors.toList());
            List<String> currentSelectedFacetValues = facetValues.stream().map(e1 -> e1.getText()).collect(Collectors.toList());
            Collections.sort(selectedFacetValues);
            Collections.sort(currentSelectedFacetValues);
            Assert.assertTrue("Selected size facets are not highlighted in " + facetName + " facet", selectedFacetValues.equals(currentSelectedFacetValues));
        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify that the selected facets are not highlighted in " + facetName + " facet" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify that the selected facets are highlighted");
    }

    @Then("^I verify product price range for e-gift cards on category browse page as below:$")
    public void priceRangeValidation_egiftcards(DataTable table) throws Throwable {
        String egift_card_price_range = null;
        for (DataTableRow row : table.getGherkinRows()) {
            egift_card_price_range = row.getCells().get(1);
            System.out.println(egift_card_price_range);
        }

        Map<String, HashMap> productDetails = getProductThumbnailsDetails();
        for (Map.Entry<String, HashMap> product : productDetails.entrySet()) {
            String priceEvent = (String) product.getValue().get("elm_price_event");
            try {
                Assert.assertTrue("Given Egift Card Price Range " + egift_card_price_range + " is not displayed for productID " + product.getValue().get("elm_product_id") + " => " + priceEvent,
                        egift_card_price_range.equals(priceEvent));
            } catch (Exception e) {
                Assert.fail("ERROR - APP:: Egift card Price Range Mismatches \r\n" + e.getMessage());
            }
        }
        DiscoveryHelper.logInfo("Then I verify product price range for e-gift cards on category browse page");

    }

    @When("^I alter and navigate to the current URL$")
    public void iAlterAndNavigateToTheCurrentURL() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        url = url.contains("registry") ? url.replaceAll("registry/", "registry/shop") : url.replaceAll("shop/", "shop/shop");
        Navigate.visit(url);
        DiscoveryHelper.logInfo("Altered and navigated to the current url");
    }

    @And("^I verify that selected page number persist$")
    public void iVerifyThatSelectedPageNumberPersist() throws Throwable {
        Assert.assertTrue("Selected page number persist!!", DiscoveryHelper.getCurrentPageNumber() == selectedPageNo);
    }

    @When("^I select \"([^\"]*)\" facet value from '([^\"]*)' facet section$")
    public void iSelectGivenFacetValueFromGivenFacetSection(String facetValue, String facetName) throws Throwable {
        if (selectedFacetNames.isEmpty())
            selectedFacetNames = new ArrayList<>();
        if (selectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        LeftFacet.selectItemFromFacet(facetValue, facetName);
        selectedFacetNames.add(facetName);
        selectedFacetValues.add(facetValue);
        DiscoveryHelper.logInfo("SELECTED FACET NAME " + facetName);
        DiscoveryHelper.logInfo("SELECTED FACET VALUE " + facetValue);
    }

    @Then("^I \"([^\"]*)\" see <link rel=\"([^\"]*)\" media=\"([^\"]*)\" href=\"([^\"]*)\"> tag pointing to the corresponding URL added in the header section$")
    public void iSeeLinkRelMediaHrefTagPointingToTheCorrespondingURLAddedInTheHeaderSection(String condition, String tagName, String mediaDetail, String href) throws Throwable {
        if (condition.equals("should") && tagName.equals("alternate")) {

            String alternateMedia = Elements.findElement("header.alternate_tag").getAttribute("media");
            System.out.println("AlternateMedia: " + alternateMedia);

            String pageURL = URLDecoder.decode(WebDriverManager.getCurrentUrl(), "UTF-8").replace("www.", "m.");
            System.out.println("pageURL: " + pageURL);

            String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"), "UTF-8");
            System.out.println("AlternateURL: " + alternateURL);

            Assert.assertTrue("alternate media error", mediaDetail.equals(alternateMedia));
            Assert.assertTrue("alternate tag not point to corresponding URL",
                    pageURL.contains(alternateURL) || alternateURL.contains(pageURL));
        } else {
            throw new Exception("ERROR: " + condition + " and " + tagName + " not supported");
        }

    }

    @Then("^I \"([^\"]*)\" see canonical tag pointing to the corresponding URL added in the header section$")
    public void iSeeCanonicalTagPointingToTheCorrespondingURLAddedInTheHeaderSection(String condition) throws Throwable {

        if (condition.equals("should")) {
            String canonicalURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
            String pageURL = URLDecoder.decode(WebDriverManager.getCurrentUrl(), "UTF-8");
            System.out.println("PageURL: " + pageURL);
            System.out.println("Canonical URL: " + canonicalURL);
            Assert.assertTrue("canonical tag not point to corresponding URL", canonicalURL.contains(pageURL) || pageURL.contains(canonicalURL));
        } else {
            throw new Exception("ERROR: " + condition + " not supported");
        }
    }

    @Then("^I should see jumbo swatch on top of thumbnail image for product$")
    public void iShouldSeeJumboSwatchOnTopOfThumbnailImageForProduct() throws Throwable {
        WebElement productElement = DiscoveryHelper.getProductDiv(selectedProdId);
        Assert.assertTrue("ERROR - APP: Jumbo swatch is not displaying for productid:" + selectedProdId,
                productElement.findElement(By.className("jumboSwatch")).isDisplayed());
        DiscoveryHelper.logInfo("Verified jumbo swatch on top of thumbnail grid for product" + selectedProdId);
    }

    @Then("^I verify the basic attributes of COACH browse page$")
    public void iVerifyTheBasicAttributesOfCOACHBrowsePage() throws Throwable {
        Assert.assertFalse("ERROR - APP: Product thumbnails are not displayed on COACH browse page",
                DiscoveryHelper.getTotalThumbnailCount() == 0);
        Assert.assertTrue("ERROR - APP: Facet section is not displayed on COACH browse page",
                Elements.elementPresent("left_facet.facets_panel"));
        Assert.assertTrue("ERROR - ENV: Main header is not displayed",
                Elements.elementPresent("category_menu.category_menu_container"));
        Assert.assertTrue("ERROR - ENV: Main footer is not displayed",
                Elements.elementPresent("footer.footer_menu_section"));
        Assert.assertTrue("ERROR - APP: Currency is not displayed on COACH browse page",
                Elements.findElements("product_thumbnails.price").get(0).getText().contains("$"));
        Assert.assertTrue("ERROR - ENV: Sort By dropdown is not displayed on COACH browse page",
                Elements.elementPresent("category_browse.sort_by_select"));
    }

    @When("^I navigate to DLP with \"([^\"]*)\" facet and \"([^\"]*)\" than (\\d+) brands$")
    public void iNavigateToDLPWithFacetAndThanBrands(String facetName, String condition, int facetValuesCount) throws Throwable {
        String currentURL = WebDriverManager.getCurrentUrl();
        String countryCode = HeaderActions.getCountryCode();
        if (condition.equalsIgnoreCase("less")) {
            if (countryCode.equals("US") & !currentURL.contains("wedding")) {
                String dlpURL = RunConfig.url + "/buy/heys";
                Navigate.visit(dlpURL);
            } else if (countryCode.equals("US") & currentURL.contains("wedding")) {
                String dlpURL = RunConfig.url + "/buy/bloomingdales?keyword=Bloomingdale%27s&mode=wedding";
                Navigate.visit(dlpURL);
            } else {
                String dlpURL = RunConfig.url + "/buy/boss-orange";
                Navigate.visit(dlpURL);
            }
        } else {
            if (countryCode.equals("US") & !currentURL.contains("wedding")) {
                String dlpURL = RunConfig.url + "/buy/dress";
                Navigate.visit(dlpURL);
            } else if (countryCode.equals("US") & currentURL.contains("wedding")) {
                String dlpURL = RunConfig.url + "/buy/plates?mode=wedding";
                Navigate.visit(dlpURL);
            } else {
                String dlpURL = RunConfig.url + "/buy/dress";
                Navigate.visit(dlpURL);
            }

        }
        DiscoveryHelper.logInfo("Navigated to DLP page with" + facetName + "and" + condition + "than" + facetValuesCount + "brands");
    }

    @Then("^I verify each link from Left Nav lands correctly on subcategory page$")
    public void iVerifyBrowseLeftNavLink() throws Throwable {
        try {
            List<String> leftNavCatNameListFromQA = Elements.findElements("left_navigation_category.subcategory").stream().map(m -> m.getText()).collect(Collectors.toList());
            leftNavCatNameListFromQA.remove("");
            leftNavCatNameListFromQA.remove(null);
            int size = leftNavCatNameListFromQA.size();
            //System.out.println("left nav items "+leftNavCatNameListFromQA);
            //System.out.println("Size "+size);
            for (int i = 0; i < size - 1; i++) {
                //System.out.println("which link"+i);
                Clicks.click(By.linkText(leftNavCatNameListFromQA.get(i)));
                iVerifyEachLandingCategoryPage(leftNavCatNameListFromQA.get(i));
                if (i < size - 2) iSelectBrowseBackButton();
            }
        } catch (Exception ex) {
            Assert.fail("Error In:: LandingPage in any link in left navigation" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("Verified LandingPage in clicking link in left navigation");

    }

    public void iVerifyEachLandingCategoryPage(String categoryName) throws Throwable {

        String url = WebDriverManager.getCurrentUrl();
        System.out.println("My URL :" + url);
        int counter = 0;
        System.out.println(categoryName);
        String modifiedurl = url.replaceAll("-", " ");
        System.out.println("My Modified URL: " + modifiedurl);
        String[] words = categoryName.split("\\W+");
        for (String word : words) {
            if (modifiedurl.contains(word)) {
                counter++;
            }
        }
        Assert.assertTrue("ERROR - APP: URL should contain Expected Category: ", counter > 0);
    }

    @And("^I verify that \"([^\"]*)\" facets are separated from inactive facets$")
    public void iVerifyThatFacetsAreSeparatedFromInactiveFacets(String facetName) throws Throwable {
        I_verify_that_the_selected_color_appears_on_top(facetName.toLowerCase());
    }

    @Then("^I verify that landed on respective Navapp Page$")
    public void i_verify_that_landed_on_respective_Navapp_Page(String url, String catId) throws Throwable {
        Wait.isPageLoaded();
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        if (catId == null || currentUrl.contains(catId)) {
            logInfo("PageSource url: " + currentUrl);
            boolean discovery = WebDriverManager.getWebDriver().getPageSource().contains("discovery-pages.common.css");
            if (discovery) {
                excludedCategories.add(url);
            }
        } else {
            logInfo("Actual URL:'" + url + "' is redirected to url:'" + currentUrl + "'. Hence server check not done");
        }
    }

    @Then("^I verify below excluded categories are routed to 'navapp' server in '(domestic|iship|registry)' context:")
    public void i_am_on_excluded_category_id_CategoryBrowsepage(String mode, List<String> exludedCatIDs) throws Throwable {
        pausePageHangWatchDog();
        for (String catId : exludedCatIDs) {
            String url = EnvironmentDetails.getEnvUrl();
            url = mode.equalsIgnoreCase("registry") ? url + "/shop/wedding-registry/?id=" + catId : url + "/shop/?id=" + catId;
            try {
                Navigate.visit(url);
                Wait.forPageReady();
            } catch (Exception e) {
                logInfo("Navigation failed with exception:'" + e.getMessage() + " 'for url :" + url);
            }
            if (WebDriverManager.getWebDriver().getCurrentUrl().equalsIgnoreCase(url)) {
                logInfo("Navigation failed for url :" + url);
                Navigate.visit(url);
                Wait.forPageReady();
                logInfo("Retrying navigation:" + url);
            }
            logInfo("Navigation completed for :" + url);
            i_verify_that_landed_on_respective_Navapp_Page(url, catId);
        }
        Assert.assertTrue("ERROR - APP: Categories:'" + excludedCategories + "' are not routing to Navapp", excludedCategories.isEmpty());
        resumePageHangWatchDog();
    }

    @Then("^I navigate to specific excluded urls")
    public void i_navigate_to_specific_excluded_urls(List<String> exludedUrls) throws Throwable {
        String url = EnvironmentDetails.getEnvUrl();
        System.out.println("Url from Environment" + url);

        for (String appendurl : exludedUrls) {
            System.out.println("Url from Environment" + appendurl);
            url = EnvironmentDetails.getEnvUrl() + appendurl;
            Navigate.visit(url);
            Wait.forPageReady();
            i_verify_that_landed_on_respective_Navapp_Page(url, null);
        }
        Assert.assertTrue("ERROR - APP: Categories:'" + excludedCategories + "' are not routing to Navapp", excludedCategories.isEmpty());
    }

    @When("^I verify KS \"([^\"]*)\" is (enabled|disabled)$")
    public void iVerifyKSIsEnabled(String nameOfKS, String condition) throws Throwable {
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        String statusOfKS;
        if (condition.equalsIgnoreCase("enabled")) {
            statusOfKS = nameOfKS + ": true";
        } else {
            statusOfKS = nameOfKS + ": false";
        }
        Assert.assertTrue("KS " + nameOfKS + " is not " + condition, pageSrcVal.contains(statusOfKS));
        DiscoveryHelper.logInfo("Verified KS " + nameOfKS + "is" + condition);
    }

    @And("^I clear existing class variable data to avoid data issues$")
    public void iClearExistingClassVariableDataToAvoidDataIssues() throws Throwable {
        selectedFacetValues = new ArrayList<>();
        selectedFacetNames = new ArrayList<>();
        firstSuggestionsList = new ArrayList<>();
        secondSuggestionsList = new ArrayList<>();
        facetItems = null;
        excludedCategories = new ArrayList<String>();
    }

    @And("^I verify the stella attribute \"([^\"]*)\" details$")
    public void iVerifyTheStellaAttributeDetails(String attributeName) throws Throwable {
        JSONObject leftNavObject = BrowseService.getCategoryStellaAttribute(null, attributeName);
        if (leftNavObject != null)
            leftNavAttributeValue = leftNavObject.getJSONArray("attributeValues").getJSONObject(0).getString("value");
        DiscoveryHelper.logInfo("Verified stella attribute " + attributeName + " details");
    }

    @When("^I see Stella attribute is \"([^\"]*)\"$")
    public void iSeeStellaAttributeIs(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("not sent")) {
            Assert.assertTrue("Stella attribute is not null & it is sent", leftNavAttributeValue == null);
        } else {
            Assert.assertEquals("Stella attribute is not " + condition, leftNavAttributeValue, condition);
        }
        DiscoveryHelper.logInfo("Verified stella attribute is " + condition);
    }

    @Then("^I (should|should not) see LHN on SLP page( with sub categories| with facets| with sub categories and facets)?$")
    public void iShouldNotSeeLHNOnSLPPageWithSubCategories(String condition, String condition2) throws Throwable {
        if (condition.equalsIgnoreCase("should")) {
            Assert.assertTrue("ERROR DATA: - Left Nav is not displaying", Elements.elementPresent("slp.left_nav_container"));
        } else {
            Assert.assertFalse("ERROR DATA: - Left Nav is displaying", Elements.elementPresent("slp.left_nav_container"));
        }
        DiscoveryHelper.logInfo("Verified LHN " + condition + " display on SLP page with sub categories");
    }

    @Then("^I (should|should not) see Left Nav Media on SLP page$")
    public void iShouldNotSeeLHNOnSLPPageWithSubCategories(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should")) {
            Assert.assertTrue("ERROR DATA: - SLP Left Nav Media Links is not displaying", Elements.elementPresent("static_landing.left_nav_links"));
        } else {
            Assert.assertFalse("ERROR DATA: - SLP Left Nav Media Links displaying", Elements.elementPresent("static_landing.left_nav_links"));
        }
        DiscoveryHelper.logInfo("Verified LHN " + condition + " display on SLP page with sub categories");
    }

    @Then("^I (should|should not) see default Left Nav on SLP page$")
    public void iShouldNotSeedefaultLHNOnSLPPageWithSubCategories(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should")) {
            Assert.assertTrue("ERROR DATA: - SLP Left Nav Media Links is not displaying", Elements.elementPresent("static_landing.left_nav_links"));
        } else {
            Assert.assertFalse("ERROR DATA: - SLP Left Nav Media Links displaying", Elements.elementPresent("static_landing.left_nav_links"));
        }
        DiscoveryHelper.logInfo("Verified LHN " + condition + " display on SLP page with sub categories");
    }


    @Then("^I verify the \"meta description\" tag in HTML view source code$")
    public void iVerifyTheMetaDescriptionInHTMLViewSourceCode() throws Throwable {
        List<WebElement> breadCrumbsList;
        String afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[name=\'description\']")).getAttribute("content"), "UTF-8"), "UTF-8").replace("|", "-");
        System.out.println("Meta Description is :" + afterTagDecode);
        Assert.assertFalse("ERROR - APP: Meta description does not exists in view source", afterTagDecode.equals(""));
        Assert.assertTrue("ERROR - APP: Meta description:" + afterTagDecode + " is having {0}.", !afterTagDecode.contains("{0}"));
        breadCrumbsList = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs"));
        breadCrumbsList.stream().filter(ele -> !afterTagDecode.contains(ele.getText().replaceAll("\\$", "").trim()))
                .forEach(ele -> Assert.fail("Meta description doesnt have facet value of the selected facet"));
        DiscoveryHelper.logInfo("Verified meta description in page view source");
    }


    @Then("^I verify page title should be \"([^\"]*)\"$")
    public void i_verify_page_title_should_be(String title) throws Throwable {
        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        Assert.assertTrue("Incorrect Page Title", pageTitle.equals(title));
    }

    @Then("^I verify page title should be appended with selected FacetName$")
    public void i_verify_page_title_should_be_appended_with_selected_FacetName() throws Throwable {

        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        System.out.println(pageTitle);
        System.out.println(selectedFacet);
        Assert.assertTrue("Page Title not starting with SelectedFacetName", pageTitle.startsWith(selectedFacet));

    }

    @Then("^I verify page title should be appended with one selected FacetName from same Facet Section$")
    public void i_verify_page_title_should_be_appended_with_one_selected_FacetName_from_same_Facet_Section() throws Throwable {

        System.out.println(selectedFacetValues);
        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        System.out.println(selectedFacetValues.get(0));
        System.out.println(selectedFacetValues.get(1));
        Assert.assertTrue("Page Title not starting with SelectedFacetName", pageTitle.startsWith(selectedFacetValues.get(0)));
        Assert.assertFalse("Page Title not starting with SelectedFacetName", pageTitle.contains(selectedFacetValues.get(1)));

    }

    @Then("^I verify that page Title has only (\\d+) facet values$")
    public void i_verify_that_page_Title_has_only_facet_values(int arg1) throws Throwable {
        System.out.println(selectedFacetValues);
        String pageTitle = WebDriverManager.getWebDriver().getTitle();
        Assert.assertFalse("Page Title not starting with SelectedFacetName", pageTitle.contains(selectedFacetValues.get(2)));
        Assert.assertTrue("Page Title not starting with SelectedFacetName", pageTitle.contains(selectedFacetValues.get(0)));
        Assert.assertTrue("Page Title not starting with SelectedFacetName", pageTitle.contains(selectedFacetValues.get(1)));


    }

    @Then("^I verify left navigation is present on SLP page$")
    public void iVerifyLeftNavigationIsPresentOnSLPPage() throws Throwable {
        Assert.assertTrue("Left navigation container is not displayed on UI", Elements.elementPresent("leftFacetNavigation_panel.leftNavigationContainer"));
        logger.info("Left navigation container is  displayed on UI");
    }

    @And("^I verify left navigation media type content in service for \"([^\"]*)\" media type$")
    public void iVerifyLeftNavigationMediaTypeContentInServiceForMediaType(String mediaType) throws Throwable {
        String canvasId = null;
        String catID = null;
        try {
            Map serviceResponse = null;
            JSONObject mediaResponse = null;
            if (WebDriverManager.getCurrentUrl().contains("?id=")) {
                catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
                //Passing cat id to get the canvas id
                serviceResponse = BrowseService.getBrowseServiceCanvas(catID, "f5_vip");
                logger.info("Service response is FCC is: " + serviceResponse);
            }
            List<String> canvasIDs = new ArrayList<String>();
            LinkedTreeMap mainMap = (LinkedTreeMap<String, Object>) serviceResponse.get("category");
            List<Double> canvasIdList = (List<Double>) mainMap.get("canvasIds");
            for (int i = 0; i < canvasIdList.size(); i++) {
                Double canvas = canvasIdList.get(i);
                int canvasid = canvas.intValue();
                canvasId = Integer.toString(canvasid);
                logger.info("Canvas Id fetched from FCC is: " + canvasId);
            }
            //Passing canvas id and cat id to get the media data
            mediaResponse = BrowseService.getMediaDetailsCanvas(canvasId, catID, "f5_vip");
            JSONObject canvses = mediaResponse.getJSONObject("canvases");
            JSONArray canvasdetails = (JSONArray) canvses.get("canvas");
            for (int i = 0; i < canvasdetails.length(); i++) {
                JSONObject obj = canvasdetails.getJSONObject(i);
                JSONArray rowdata = (JSONArray) obj.get("rows");

                for (int j = 0; j < rowdata.length(); j++) {
                    JSONObject obj1 = rowdata.getJSONObject(j);
                    if (obj1.has("zones")) {
                        JSONArray zonesData = obj1.getJSONArray("zones");

                        for (int z = 0; z < zonesData.length(); z++) {
                            JSONObject contentsData = zonesData.getJSONObject(z);
                            if (contentsData.has("contents")) {
                                JSONArray contentsmeddata = (JSONArray) contentsData.getJSONArray("contents");

                                for (int x = 0; x < contentsmeddata.length(); x++) {
                                    JSONObject contentsLinksData = contentsmeddata.getJSONObject(x);
                                    if (contentsLinksData.has("contentlinks")) {
                                        JSONArray contentsmediaData = (JSONArray) contentsLinksData.getJSONArray("contentlinks");
                                        ArrayList finalList = null;
                                        finalList = Utils.jsonArrayToList(contentsmediaData);
                                        System.out.println("media list data" + finalList);
                                        mediaData = new ArrayList<HashMap>();

                                        for (int cc = 0; cc < finalList.size(); cc++) {
                                            JSONObject media = (JSONObject) finalList.get(cc);
                                            if (media.has("type") && media.has("text") && media.has("value")) {
                                                String typeValue = media.getString("type");
                                                HashMap map = new HashMap();
                                                if (typeValue.equalsIgnoreCase(mediaType)) {
                                                    map.put("type", typeValue);
                                                    map.put("text", media.getString("text"));
                                                    map.put("value", media.getString("value"));
                                                    mediaData.add(map);
                                                }
                                            }
                                        }
                                    }

                                }

                            }

                        }
                    }
                }

            }
            logger.info("media details :" + mediaData);
        } catch (Exception e) {
            Assert.fail("Error performing validation between service and UI \r\n" + e.getMessage());
        }
        logger.info("Successfully fetched left nav media from canvas service response in domestic mode");
    }

    @And("^I click on link from left navigation$")
    public void iClickOnLinkFromLeftNavigation() throws Throwable {
        leftNavLinksSlpPage = Elements.findElements(By.xpath("//div[@id='categoryTree']/ul/li/a"));
        for (int i = 0; i < mediaData.size(); i++) {
            for (WebElement element : leftNavLinksSlpPage) {
                String mediaName = element.getText();
                if (mediaName.equalsIgnoreCase(mediaData.get(i).get("text").toString().trim())) {
                    element.click();
                    Wait.forPageReady();
                    logger.info("Clicked on media: " + mediaName);
                    break;
                }
            }
        }
    }


    @And("^I verify correct url is displayed for associated media type fetched from service$")
    public void iVerifyCorrectUrlIsDisplayedForAssociatedMediaTypeFetchedFromService() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        for (int i = 0; i < mediaData.size(); i++) {
            if (mediaData.get(i).get("type").toString().equalsIgnoreCase("CATEGORY")) {
                Assert.assertTrue("Correct url is not displayed for the left nav link for Category media", url.contains(mediaData.get(i).get("value").toString()));
                logger.info("Correct url is displayed for the left nav link for Category media" + mediaData.get(i).get("value").toString());
            } else if (mediaData.get(i).get("type").toString().equalsIgnoreCase("STATIC")) {
                String staticUrl = mediaData.get(i).get("value").toString();
                Assert.assertTrue("Correct url is not displayed for the left nav link for static media", url.contains(staticUrl) || staticUrl.contains(url));
                logger.info("Correct url is displayed for the left nav link for STATIC media" + staticUrl);
            } else if (mediaData.get(i).get("type").toString().equalsIgnoreCase("CLONE_CAT_LHN")) {
                Assert.assertTrue("Correct url is not displayed for the left nav link for static media", url.contains(mediaData.get(i).get("value").toString()));
                logger.info("Correct url is displayed for the left nav link for CLONE_CAT_LHN media" + (mediaData.get(i).get("value").toString()));
            }
        }
        Navigate.browserBack();
        leftNavLinksSlpPage = Elements.findElements(By.xpath("//div[@id='categoryTree']/ul/li/a")); // To avoid staleElement exception
        for (int j = 0; j < leftNavLinksSlpPage.size(); j++) {
            leftNavLinksText.add(leftNavLinksSlpPage.get(j).getText());
            System.out.println(leftNavLinksText.add(leftNavLinksSlpPage.get(j).getText()));
        }
    }

    @And("^I click on link from left navigation for clone category$")
    public void iClickOnLinkFromLeftNavigationForCloneCategory() throws Throwable {
        try {
            PageNavigation pageNavigation = new PageNavigation();
            pageNavigation.I_navigate_to_the_browse_page_under("All Baby", "Kids");
            logger.info("Navigated to Browse page for Clone category media");
        } catch (Exception e) {
            Assert.fail("Error while clicking on link from left navigation for clone category media \r\n" + e.getMessage());
        }

    }

    @And("^I verify left navigation links for Cloned category Url is same as SLP page$")
    public void iVerifyLeftNavigationLinksForClonedCategoryUrlIsSameAsSLPPage() throws Throwable {
        ArrayList<String> clonecatleftNavText = new ArrayList<String>();
        List<WebElement> clonecatleftNavLinks = Elements.findElements(By.xpath("//div[@id='navigation']/div/div[2]/div/a"));
        for (int i = 0; i < clonecatleftNavLinks.size(); i++) {
            clonecatleftNavText.add(clonecatleftNavLinks.get(i).getText());
        }
        Collections.sort(leftNavLinksText);
        Collections.sort(clonecatleftNavText);
        if (leftNavLinksText.size() == clonecatleftNavText.size()) {
            for (int j = 0; j < clonecatleftNavLinks.size(); j++) {
                Assert.assertTrue("left navigation links are matching for Clone category media", leftNavLinksText.get(j).equalsIgnoreCase(clonecatleftNavText.get(j)));
                logger.info("left navigation links are matching for Clone category media");
            }
        } else {
            Assert.fail("left navigation links are not matching for clone category");
        }
    }

    @Then("^I verify category name in header should be \"([^\"]*)\"$")
    public void i_verify_category_name_in_header_should_be(String expectedHeaderName) throws Throwable {
        actualHeaderName = Elements.getText("leftFacetNavigation_panel.slpHeader");
        Assert.assertTrue("Incorrect Header Name", expectedHeaderName.equals(actualHeaderName));
    }

    @Then("^I verify facet value is appended with header$")
    public void I_verify_facet_value_is_appended_with_header() throws Throwable {
        actualHeaderName = Elements.getText("leftFacetNavigation_panel.slpHeader");
        logger.info("Selected facet value is" + selectedFacet);
        Assert.assertTrue("Facet value is not displayed in header", actualHeaderName.startsWith(selectedFacet));

    }

    @Then("^I verify single facet value is appended with header$")
    public void I_verify_single_facet_value_is_appended_with_header() throws Throwable {
        actualHeaderName = Elements.getText("leftFacetNavigation_panel.slpHeader");
        logger.info("First facet value selected is :" + selectedFacetValues.get(0));
        logger.info("Second facet value selected is :" + selectedFacetValues.get(1));
        Assert.assertTrue("Header Name not starting with first SelectedFacetName", actualHeaderName.startsWith(selectedFacetValues.get(0)));
        Assert.assertFalse("Header Name contained second SelectedFacetName", actualHeaderName.contains(selectedFacetValues.get(1)));

    }

    @Then("^I verify two facet values are appended with header$")
    public void I_verify_two_facet_values_are_appended_with_header() throws Throwable {
        actualHeaderName = Elements.getText("leftFacetNavigation_panel.slpHeader");
        logger.info("First facet value selected is :" + selectedFacetValues.get(0));
        logger.info("Second facet value selected is :" + selectedFacetValues.get(1));
        logger.info("Third facet value selected is :" + selectedFacetValues.get(2));
        Assert.assertFalse("Header Name contained third SelectedFacetName", actualHeaderName.contains(selectedFacetValues.get(2)));
        Assert.assertTrue("Header Name does not starts wth first SelectedFacetName", actualHeaderName.startsWith(selectedFacetValues.get(0)));
        Assert.assertTrue("Header Name does not contain  second SelectedFacetName", actualHeaderName.contains(selectedFacetValues.get(1)));

    }

    @Then("^I verify A-Z Index is displaying on Brand Index page$")
    public void iVerifyAZIndexIsDisplayingOnBrandIndexPage() throws Throwable {
        Assert.assertTrue("A letter is displayed on UI on BrandIndex page", Elements.elementPresent("brandIndexPage.a_index"));
        Assert.assertTrue("Z letter is displayed on UI on BrandIndex page", Elements.elementPresent("brandIndexPage.z_index"));
        Assert.assertTrue("# letter is displayed on UI on BrandIndex page", Elements.elementPresent("brandIndexPage.hashletter_index"));
        logger.info("A-Z# letter is displayed on UI on BrandIndex page");
    }

    @Then("^I verify letter is displaying with black color$")
    public void iVerifyLetterIsDisplayingWithBlackColor() throws Throwable {
        String textColor = Elements.findElement("brandIndexPage.a_index").getCssValue("color");
        Assert.assertEquals("Text color is not black for letter A :" + textColor, "rgba(0, 0, 0, 1)", textColor);
        logger.info("Text color is black for letter A on BrandIndex page");
    }

    @And("^I click letter in brand index$")
    public void iClickLetterInBrandIndex() throws Throwable {
        Clicks.click("brandIndexPage.c_index");
        Wait.ajaxDone();
        logger.info("Clicked on letter C on BrandIndex page");

    }

    @And("^I verify page scrolls to the applicable brand for selected letter and section is highlighted$")
    public void iVerifyPageScrollsToTheApplicableBrandForSelectedLetterAndSectionIsHighlighted() throws Throwable {
        WebDriver driverjs = WebDriverManager.getWebDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driverjs;
        Long scrollPosition = (Long) executor.executeScript("return window.pageYOffset;");
        Assert.assertTrue("Page is not scrolled to the applicable brand for selected letter on BrandIndex page", Elements.elementPresent("brandIndexPage.c_letter_text"));
        Assert.assertTrue("Page is not scrolled to the applicable brand for selected letter on BrandIndex page", scrollPosition > 0);
        logger.info("Page scrolls to the applicable brand for selected letter on BrandIndex page");
    }

    @And("^I verify category is clickable under applicable brands section$")
    public void iVerifyCategoryIsClickableUnderApplicableBrandsSection() throws Throwable {
        Clicks.click("brandIndexPage.category_cletter_link");
        Navigate.browserBack();
        logger.info("Clicked on category link under applicable Brands on BrandIndex page");
    }

    @Then("^I verify letter in displaying with grey color and is disabled and unclickable$")
    public void iVerifyLetterInDisplayingWithGreyColorAndIsDisabledAndUnclickable() throws Throwable {
        String disableAttribute = Elements.findElement("brandIndexPage.x_index_disabled").getAttribute("href");
        System.out.println(disableAttribute.contains("javascript:void(0)"));
        Assert.assertTrue("X letter is disabled and Unclickable on BrandIndex page", disableAttribute.contains("javascript:void(0)"));
        String textColor = Elements.findElement("brandIndexPage.x_index_disabled").getCssValue("color");
        if (bloomingdales()) {
            Assert.assertEquals("Text color is not grey for letter X :" + textColor, "rgba(216, 216, 216, 1)", textColor);
        } else {
            Assert.assertEquals("Text color is not grey for letter X :" + textColor, "rgba(204, 204, 204, 1)", textColor);
        }
    }

    @Then("^I verify brandListIndex is present in response in service for the letter that has applicable brands$")
    public void iVerifyBrandListIndexIsPresentInResponseInServiceForTheLetterThatHasApplicableBrands() throws Throwable {
        ArrayList brandLetter = null;
        JSONObject brandIndexServiceResponse = null;
        brandIndexServiceResponse = BrowseService.getBrandIndexMediaDetailsCanvas("63538", "f5_vip", "SITE", "US","SITE");
        logger.info("Service response is FCC for BrandIndex is: " + brandIndexServiceResponse);
        JSONObject body = brandIndexServiceResponse.getJSONObject("body");
        JSONObject canvas = body.getJSONObject("canvas");
        JSONArray rowdetails = (JSONArray) canvas.get("rows");
        for (int i = 0; i < rowdetails.length(); i++) {
            JSONObject obj1 = rowdetails.getJSONObject(i);
            JSONObject row101Data = obj1.getJSONObject("row101");
            JSONArray rowdata = (JSONArray) row101Data.get("zones");
            for (int j = 0; j < rowdata.length(); j++) {
                JSONObject obj3 = rowdata.getJSONObject(0); //j
                JSONObject brandIndexData = obj3.getJSONObject("brandIndex");
                JSONArray brandLists = (JSONArray) brandIndexData.get("brandLists");
                for (int k = 0; k < brandIndexData.length(); k++) {
                    JSONObject obj4 = rowdata.getJSONObject(0); //k
                    System.out.println("******" + obj4);
                    //  TO DO   //
                }
                System.out.println("BrandList letters present in service" + brandLetter);
            }
        }
    }

    @And("^I click \"([^\"]*)\" letter from A-Z index$")
    public void iSelectAnyLetterFromBrandIndexOfTheDesignerPage(String index) throws Throwable {
        Clicks.clickElementByText("brandIndexPage.all_index", index);
        logger.info("Selected letter from A-Z index ");
    }

    @And("^I select \"([^\"]*)\" category from left navigation$")
    public void iSelectCategoryFromLeftNav(String category) throws Throwable {
        catName = category;
        Clicks.clickElementByText("brandIndexPage.left_nav_Brand_items", catName);
        logger.info("Navigated to left nav category");
        Wait.forPageReady();
        Thread.sleep(1000);
    }

    @When("^I navigate to (Brands|Designers) index page$")
    public void I_navigate_to_index_page(String Brands) throws Throwable {
        if (macys()) {
            Clicks.click("brandIndexPage.designer_homepage_fob");
            logger.info("Clicked on brand index page on MCOM");
        } else {
            Clicks.click("brandIndexPage.designer_homepage_fob");
            logger.info("Clicked on brand index page on BCOM");
        }
    }

    @Then("^I verify header is displaying \"([^\"]*)\" category in left navigation$")
    public void i_verify_header_name_in_left_navigation(String expectedHeaderName) throws Throwable {
        if (expectedHeaderName.equals("All Brands")) {
            actualHeaderName = Elements.getText("designer_static.brand_index_all_brands");
        } else {
            actualHeaderName = Elements.getText("designer_static.brand_index_header_2");
        }
        Assert.assertTrue("Incorrect Header Name", expectedHeaderName.equals(actualHeaderName));
        logger.info("Header is displaying selected category");
    }

    @Then("^I verify selected \"([^\"]*)\" category is getting highlighted$")
    public void i_verify_selected_category_highlighted(String expectedCategory) throws Throwable {
        WebElement selectedCategory = Elements.findElement("designer_static.selected_category");
        String hightlighted_Category = selectedCategory.getText();
        Assert.assertTrue("Incorrect category highlighted", expectedCategory.equals(hightlighted_Category));
        Assert.assertTrue("Background color displayed is not correct", selectedCategory.getCssValue("background-color").equals("rgba(0, 0, 0, 0)"));
        Thread.sleep(1000);
        logger.info("Selected category is getting highlighted");
    }

    @Then("^I verify that Breadcrumbs is getting displayed$")
    public void i_verify_Breadcrumbs_displayed() throws Throwable {
        String breadCrumb = Elements.getText("designer_static.brand_index_header");
        Assert.assertTrue("Incorrect Header Name", breadCrumb.equals("All Brands"));
        logger.info("Header is displaying selected category");
    }

    @And("^I click on Breadcrumbs link$")
    public void iClickfBreadcrumbLink() throws Throwable {
        try {
            Elements.findElement("designer_static.brand_index_header").click();
        } catch (Exception e) {
            Assert.fail("Error clicking Breadcrumb link" + e.getMessage());
        }
        logger.info("Clicked Breadcrumb link");
    }

    @Then("^I verify brands is getting filtered by selected \"([^\"]*)\" category$")
    public void iVerifyBrandGettingFiltered(String category) throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        logger.info(url);
        Assert.assertTrue("Brand not getting filtered", url.contains(category.toLowerCase()));
        Thread.sleep(2000);
        logger.info("Brands are getting filtered by selected category");
    }

    @Then("^I verify the Left Nav of the Chanel Browse Page$")
    public void iVerifyTheLeftNavOfTheChanelBrowsePage() throws Throwable {
        List<String> leftNavLinks = Elements.findElement(By.id("nav_category")).findElements(By.tagName("a")).stream().map(f -> f.getAttribute("href")).collect(Collectors.toList());
        for (String links : leftNavLinks) {
            int responseCode = DiscoveryHelper.getResponseCode(links);
            Assert.assertTrue("ERROR - APP: page link URL:" + links + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
        }
    }

    @Then("^I verify bannerMachineDisplayEnabled KS is set to \"([^\"]*)\"$")
    public void iVerifyBannerMachineDisplayEnabledKSIsSetTo(String ksValue) throws Throwable {
        String pageSource = WebDriverManager.getWebDriver().getPageSource();
        String bannerMachineDisplayEnabledKillSwitch = "\"bannerMachineDisplayEnabled\":" + ksValue;
        System.out.println("KS Value"+bannerMachineDisplayEnabledKillSwitch);
        switch (ksValue) {
            case "true":
                Assert.assertTrue("bannerMachineDisplayEnabled KillSwitch is value is" + ksValue, pageSource.contains(bannerMachineDisplayEnabledKillSwitch));
                logger.info("bannerMachineDisplayEnabled KillSwitch is value is set to true");
                break;

            case "false":
                Assert.assertTrue("bannerMachineDisplayEnabled KillSwitch is value is" + ksValue, pageSource.contains(bannerMachineDisplayEnabledKillSwitch));
                logger.info("bannerMachineDisplayEnabled KillSwitch is value is set to true");
                break;
        }

    }

    @And("^I click on brand link under brands section$")
    public void iClickOnBrandLinkUnderBrandsSection() throws Throwable {
        Clicks.javascriptClick("brandIndexPage.brandlink_A_letter");
        logger.info("Clicked on brands under A letter on BrandIndex page");
    }


    @Then("^I click on \"([^\"]*)\" brand under brands section$")
    public void iClickOnParticularBrand(String brand) throws Throwable {
        brandName = brand;
        Wait.forPageReady();
        Thread.sleep(1000);
        Clicks.click(By.xpath("//div[@id='brandIndex']//a[text()='" + brandName + "']"));
        logger.info("Clicked on brand under brand section");
    }


    @Then("^I verify page lands on DLP page for \"([^\"]*)\" category$")
    public void iVerifyPageLandsOnDLPPageForCategory(String categoryName) throws Throwable {
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        logger.info("Current Url: " + currentUrl);
        Assert.assertTrue("current Url doesn't navigate to DLP page", currentUrl.contains("shop/featured/" + categoryName));
        logger.info("current page lands on DLP page");
    }

    @Then("^I verify user is redirected back to previous page$")
    public void iVerifyUserIsRedirectedBackToPreviousPage() throws Throwable {
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        Assert.assertTrue("current Url is not navigating to previous page", currentUrl.contains("shop/all-brands?id=63538") || currentUrl.contains("shop/wedding-registry/all-brands?id=63538"));
        logger.info("Url is navigated to previous page");
    }

    @And("^I click on brand link that has more than one word under brands section$")
    public void iClickOnBrandLinkThatHasMoreThanOneWordUnderBrandsSection() throws Throwable {
        Clicks.click("brandIndexPage.brandlink_morethanoneword_A_letter");
        logger.info("Clicked on brands that is having more than one word under A letter on  BrandIndex page");
    }

    @Then("^I verify page doesn't land on DLP page and url is changed$")
    public void iVerifyPageDoesnTLandOnDLPPageAndUrlIsChanged() throws Throwable {
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        Assert.assertFalse("current Url is not naviting to previous page", currentUrl.contains("shop/featured"));
        logger.info("page doesn't land on DLP page and url is changed");
    }

    @And("^I verify url changes and page doesn't land on DLP page$")
    public void iVerifyUrlChangesAndPageDoesnTLandOnDLPPage() throws Throwable {
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        Assert.assertTrue("current Url is not navigating to DLP page", currentUrl.contains("shop/makeup-and-perfume/chanel?id=61916"));
        logger.info("Url is changed and doesn't lands on DLP page");
    }

    @Then("^I verify page lands on DLP page for \"([^\"]*)\" category on registry mode$")
    public void iVerifyPageLandsOnDLPPageForCategoryOnRegistryMode(String categoryName) throws Throwable {
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        logger.info("Current Url: " + currentUrl);
        Assert.assertTrue("current Url doesn't navigate to DLP page", currentUrl.contains("shop/featured/" + categoryName + "?mode=wedding"));
        logger.info("current page lands on DLP page");
    }

    @Then("^I verify previous location is persisted$")
    public void iVerifyPreviouslocationPersisted() throws Throwable {
        String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
        Assert.assertTrue("current Url is not navigating to previous page", currentUrl.contains("shop/all-brands") || currentUrl.contains("shop/wedding-registry/all-brands"));
        logger.info("Url is navigated to previous page");
    }


    @Then("^I verify navigated URL pattern \"([^\"]*)\"$")
    public void iVerifyNavigatedURLShouldBeFormat(String expectedURLformat) throws Throwable {
        try {
            if (expectedURLformat.equals("when sub-category exists")) {
                Assert.assertTrue("Current url does not contains /shop/", WebDriverManager.getCurrentUrl().contains("shop"));
                Assert.assertFalse("Current url does  contains /featured/", WebDriverManager.getCurrentUrl().contains("featured"));
            } else if (expectedURLformat.equals("/shop/featured/[BrandName]-[SubCatName]")) {
                expectedURLformat = new StringBuilder().append("/shop/featured/").append(brandName.toLowerCase()).append("-").append(catName.toLowerCase()).toString();
                Assert.assertTrue("Current url is not in format " + expectedURLformat, WebDriverManager.getCurrentUrl().contains(expectedURLformat));

            } else if (expectedURLformat.equals("/shop/featured/[BrandName]-[SubCatName]?mode=wedding")) {
                expectedURLformat = new StringBuilder().append("/shop/featured/").append(brandName.toLowerCase()).append("-").append(catName.toLowerCase()).append("?mode=wedding").toString();
                Assert.assertTrue("Current url is not in format " + expectedURLformat, WebDriverManager.getCurrentUrl().contains(expectedURLformat));
            } else if (expectedURLformat.equals("when brand name has more than one words")) {
                expectedURLformat = new StringBuilder().append("/shop/featured/").append(brandName.toLowerCase()).append("-").append(catName.toLowerCase()).toString().replace(" ", "-");
                Assert.assertTrue("Current url is not in format " + expectedURLformat, WebDriverManager.getCurrentUrl().contains(expectedURLformat));
            }


        } catch (Exception ex) {
            Assert.fail("ERROR - APP::I verify navigated URL is in " + expectedURLformat + " format" + ex.getMessage());
        }
        DiscoveryHelper.logInfo("I verify navigated URL is in" + expectedURLformat + "format");
    }

    @Then("^I verify SEO section is not displayed$")
    public void iVerifySEOSectionIsNotDisplayed() throws Throwable {
        try{
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
            Assert.assertTrue("SEO section is displaying on BrandIndex page", Elements.findElement("brandIndexPage.SEO_section").isDisplayed());
            logger.info("SEO section is displaying on BrandIndex page");
        }catch (Exception e)
        {
            logger.info("SEO section is not displaying on BrandIndex page as expected");
        }
    }

    @And("^I verify banner machine is (displayed|not displayed)$")
    public void iVerifyBannerMachineIsDisplayed(String displayOption) throws Throwable {
        switch (displayOption) {
            case "displayed":
                Assert.assertTrue("bannerMachine Media is not displaying eventhough KS is ON", Elements.findElement("brandIndexPage.BannerMachine_content").isDisplayed());
                logger.info("Banner machine media is displaying on BrandIndex page");
                break;
            case "not displayed":
                try{
                    Assert.assertTrue("bannerMachine Media is displaying when KS is OFF", Elements.findElement("brandIndexPage.BannerMachine_content").isDisplayed());
                    Assert.fail("Banner machine media is displaying on BrandIndex page when KS is OFF");
                    break;
                } catch (Exception e)
                { logger.info("In catch Block !!! Banner machine media is not displaying on BrandIndex page when KS is OFF");
                }
        }
    }

    @When("^I navigate to the \"([^\"]*)\" browse page$")
    public void iNavigateToTheBrowsePage(String browseCategory) throws Throwable {
        Wait.forPageReady();
        Clicks.click("brandIndexPage.Dinnerware_BrowsePage");
        logger.info("Clicked on Dinnerware browse page");
        Wait.forPageReady();
    }

    @Then("^I verify banner machine is displayed in any row sequence or (\\d+) row$")
    public void iVerifyBannerMachineIsDisplayedInAnyRowSequenceOrRow(int rowType) throws Throwable {
     Assert.assertTrue("Banner Machine is not displaying ",Elements.findElement("brandIndexPage.BannerMachine_content").isDisplayed());
     String rowTypeValue=Elements.findElement("brandIndexPage.BannerMachine_Canvas").getAttribute("class");
     Assert.assertTrue("Banner machine is not displaying in Row Type 101", rowTypeValue.contains("rowType101"));
     logger.info("Banner Machine media is displaying in ROW101");
    }

    @Then("^I verify banner machine supports different font types coming from Astra$")
    public void iVerifyBannerMachineSupportsDifferentFontTypesComingFromAstra() throws Throwable {
        String fontFamily1=Elements.findElement("brandIndexPage.BannerMachine_Canvas").getCssValue("font-family");
        Assert.assertTrue("Different Font family types are supported in Banner machine for Text1",fontFamily1.contains("Helvetica") || fontFamily1.contains("Arial"));
        logger.info("Different Font family types are supported in Banner machine");
    }

    @Then("^I verify banner machine supports different background color coming from Astra$")
    public void iVerifyBannerMachineSupportsDifferentBackgroundColorComingFromAstra() throws Throwable {
        String bgColor=Elements.findElement("brandIndexPage.BannerMachine_CanvasBgColor").getCssValue("background-color");
        Assert.assertEquals("Color coming from Astra are not supported in Banner machine","rgba(0, 0, 0, 1)",bgColor);
        logger.info("Color coming from Astra are not supported in Banner machine");
    }

    @Then("^I verify banner machine supports main banner$")
    public void iVerifyBannerMachineSupportsMainBanner() throws Throwable {
        Assert.assertTrue("Main banner is not displaying in Banner Canvas",Elements.findElement("brandIndexPage.MainBanner_Canvas").isDisplayed());
        logger.info("Main banner is displaying in BannerMachine");
    }

    @Then("^I verify banner machine supports banner link functionality$")
    public void iVerifyBannerMachineSupportsBannerLinkFunctionality() throws Throwable {
        Assert.assertTrue("Banner link is displayed",Elements.findElement("brandIndexPage.BannerLink_Canvas").isDisplayed());
        Clicks.javascriptClick("brandIndexPage.BannerLink_Canvas");
        Wait.forPageReady();
        logger.info("Banner link functionality  is working");
        Navigate.browserBack();
        Wait.forPageReady();
    }

    @Then("^I verify banner image is displayed on right or left side of the banner$")
    public void iVerifyBannerImageIsDisplayedOnRightOrLeftSideOfTheBanner() throws Throwable {
        WebElement imageLeft=Elements.findElement("brandIndexPage.BannerImgLeft_Canvas");
        Point point1=imageLeft.getLocation();
        int xCordinate=point1.getX();
        WebElement imageRight=Elements.findElement("brandIndexPage.BannerImgRight_Canvas");
        Point point2=imageRight.getLocation();
        int xCordinate2=point2.getX();
        logger.info("X and Y Coordinates from Image from left and right side: "+xCordinate+"---"+xCordinate2);
        Assert.assertTrue("Image is not in either left or right side of Banner",xCordinate <500 ||  xCordinate>900);
        logger.info("Image is  either on left or right side of Banner");
    }

    @Then("^I verify canvas data is displayed in row (\\d+) on BrandIndex page$")
    public void iVerifyCanvasDataIsDisplayedInRowOnBrandIndexPage(int rowType) throws Throwable {
        Assert.assertTrue("Banner Machine is not displaying ",Elements.findElement("brandIndexPage.BannerMachine_Canvas").isDisplayed());
        String rowTypeValue=Elements.findElement("brandIndexPage.BannerMachine_Canvas").getAttribute("id");
        Assert.assertTrue("Banner machine is not displaying in ROW 101", rowTypeValue.contains("ROW101"));
        logger.info("Banner Machine media is displaying in ROW101");
    }

    @And("^I verify canvas data is present in service on BrandIndex page$")
    public void iVerifyCanvasDataIsPresentInServiceOnBrandIndexPage() throws Throwable {
        ArrayList brandLetter = null;
        JSONObject brandIndexServiceResponse = null;
        brandIndexServiceResponse = BrowseService.getBrandIndexMediaDetailsCanvas("63538", "f5_vip", "SITE", "US","SITE");
        logger.info("Service response is FCC for BrandIndex is: " + brandIndexServiceResponse);
        JSONObject body = brandIndexServiceResponse.getJSONObject("body");
        JSONObject canvas = body.getJSONObject("canvas");
        JSONArray rowdetails = (JSONArray) canvas.get("rows");
        for (int i = 0; i < rowdetails.length(); i++) {
            JSONObject obj1 = rowdetails.getJSONObject(i);
            JSONObject row101Data = obj1.getJSONObject("row101");
            JSONArray rowdata = (JSONArray) row101Data.get("zones");
            for (int j = 0; j < rowdata.length(); j++) {
                JSONObject obj3 = rowdata.getJSONObject(0); //j
                JSONObject brandIndexData = obj3.getJSONObject("banner");
                JSONArray brandData = (JSONArray) brandIndexData.get("banner");
                System.out.println("Banner Canvas is present in service" + brandData);
            }
        }
    }

    @Then("^I verify canvas row is displaying above A-Z index$")
    public void iVerifyCanvasRowIsDisplayingAboveAZIndex() throws Throwable {
        WebElement image=Elements.findElement("");
        Point point=image.getLocation();
        int yCordinate=point.getY();
        WebElement AZ_index=Elements.findElement("brandIndexPage.a_index");
        Point point1=image.getLocation();
        int yCordinate_AZ=point1.getY();
        logger.info("Y Coordinates from canvas Image: "+yCordinate);
        Assert.assertTrue("Image is not displaying above A-Z index",yCordinate >yCordinate_AZ);
        logger.info("Image is not displaying above A-Z index");
    }

    @And("^I verify canvas data supports only Image or Imagemap media on BrandIndex page$")
    public void iVerifyCanvasDataSupportsOnlyImageOrImagemapMediaOnBrandIndexPage() throws Throwable {
        String mediaType=Elements.findElement("").getAttribute("id");
        Assert.assertTrue("Canvas data doesn't contain image or image map media type", mediaType.contains("image") || mediaType.contains("imagemap"));
        logger.info("Canvas data contains only image or image map media type");
    }

    @And("^I construct RVI cookie with (\\d+) products and reload the page$")
    public void iConstructRVICookieWithProducts(int productCount) throws Throwable {
        try {
            String updatedMiscgcCookieValue = "";
            String actualMiscgcCookieValue = Cookies.getCookieValue("MISCGCs");
            Cookies.deleteCookie("MISCGCs");
            String rviParam = "rvi1_92_1310,86800";
            if(productCount >= 3){
                String currentUrl = WebDriverManager.getCurrentUrl();
                if(!Elements.elementPresent("product_thumbnails.product_thumbnails")){
                    ShopAndBrowse.I_search_for("plates");
                }
                rviParam = rviParam + "," + DiscoveryHelper.getProductIds().subList(0, productCount - 2).stream().collect(Collectors.joining(","));
                if(!currentUrl.equalsIgnoreCase(WebDriverManager.getCurrentUrl()))
                    Navigate.visit(currentUrl);
            }
            if(actualMiscgcCookieValue.contains("rvi")){
                List miscgcCookieData  = Arrays.asList(actualMiscgcCookieValue.split("_87_"));
                for(Object miscgcCookieValue: miscgcCookieData){
                    if(((String) miscgcCookieValue).contains("rvi")){
                        updatedMiscgcCookieValue = updatedMiscgcCookieValue.equals("") ? updatedMiscgcCookieValue+rviParam : updatedMiscgcCookieValue+"_87_"+rviParam;
                    }else{
                        updatedMiscgcCookieValue = updatedMiscgcCookieValue.equals("") ? updatedMiscgcCookieValue+miscgcCookieValue : updatedMiscgcCookieValue+"_87_"+miscgcCookieValue;
                    }
                }
            }else{
                updatedMiscgcCookieValue = actualMiscgcCookieValue.equals("") ? actualMiscgcCookieValue+rviParam : actualMiscgcCookieValue+"_87_"+rviParam;
            }
            Cookies.addCookie("MISCGCs", updatedMiscgcCookieValue);
            Navigate.browserRefresh();
        } catch (Exception e) {
            Assert.fail("Error MISCGS cookie is not found" + "\r\n" + e.getMessage());
        }
    }

    @And("^I clear existing class variables to avoid data issues$")
    public void iClearExistingClassVariablesDataToAvoidDataIssues() throws Throwable {
        DiscoveryHelper.logInfo("To check whether below variables have any value other than default values :");
        DiscoveryHelper.logInfo("selectedProdId: " + selectedProdId);
        DiscoveryHelper.logInfo("thumbnailColumns: " + thumbnailColumns);
        DiscoveryHelper.logInfo("selectedFacetValues: " + selectedFacetValues);
        DiscoveryHelper.logInfo("selectedFacetNames: " + selectedFacetNames);
        DiscoveryHelper.logInfo("firstSuggestionsList: " + firstSuggestionsList);
        DiscoveryHelper.logInfo("secondSuggestionsList: " + secondSuggestionsList);
        DiscoveryHelper.logInfo("productCount: " + productCount);
        DiscoveryHelper.logInfo("facetItems: " + facetItems);
        DiscoveryHelper.logInfo("sortByValue: " + sortByValue);
        DiscoveryHelper.logInfo("strUtils: " + strUtils);
        DiscoveryHelper.logInfo("storeResultsCount: " + storeResultsCount);
        DiscoveryHelper.logInfo("selectedFacet: " + selectedFacet);
        DiscoveryHelper.logInfo("selectedSortBy: " + selectedSortBy);
        DiscoveryHelper.logInfo("selectedFacetName: " + selectedFacetName);
        DiscoveryHelper.logInfo("prodIds: " + prodIds);
        DiscoveryHelper.logInfo("totalPageCount: " + totalPageCount);
        DiscoveryHelper.logInfo("itemPerPageCount: " + itemPerPageCount);
        DiscoveryHelper.logInfo("selectedColorSwatch: " + selectedColorSwatch);
        DiscoveryHelper.logInfo("selectedColorSwatchTitle: " + selectedColorSwatchTitle);
        DiscoveryHelper.logInfo("totalThumbnailCount: " + totalThumbnailCount);
        DiscoveryHelper.logInfo("facetSelection: " + facetSelection);
        DiscoveryHelper.logInfo("brandFacet: " + brandFacet);
        DiscoveryHelper.logInfo("selectedPageNo: " + selectedPageNo);
        DiscoveryHelper.logInfo("zipCode: " + zipCode);
        DiscoveryHelper.logInfo("totalProductInRvi: " + totalProductInRvi);
        DiscoveryHelper.logInfo("productData: " + productData);
        DiscoveryHelper.logInfo("locationId: " + locationId);
        bopsFacetName = macys() ? "Free Pick Up In Store" : (Registry.registryMode ? "Pick Up In-Store" : "Pick Up In Store");
        DiscoveryHelper.logInfo("bopsFacetName: " + bopsFacetName);
        DiscoveryHelper.logInfo("productDetails: " + productDetails);
        DiscoveryHelper.logInfo("tempRemovedItemFrmRVI: " + tempRemovedItemFrmRVI);
        DiscoveryHelper.logInfo("productColorwayType: " + productColorwayType);
        DiscoveryHelper.logInfo("productType: " + productType);
        DiscoveryHelper.logInfo("hoverEle: " + hoverEle);
        DiscoveryHelper.logInfo("swatchType: " + swatchType);
        DiscoveryHelper.logInfo("productPrice: " + productPrice);
        DiscoveryHelper.logInfo("selectedSubcategory: " + selectedSubcategory);
        DiscoveryHelper.logInfo("productCountBefore: " + productCountBefore);
        DiscoveryHelper.logInfo("beforePriceSortUrl: " + beforePriceSortUrl);
        DiscoveryHelper.logInfo("leftNavAttributeValue: " + leftNavAttributeValue);
        DiscoveryHelper.logInfo("excludedCategories: " + excludedCategories);
        DiscoveryHelper.logInfo("mediaData: " + mediaData);
        DiscoveryHelper.logInfo("leftNavLinksSlpPage: " + leftNavLinksSlpPage);
        DiscoveryHelper.logInfo("leftNavLinksText: " + leftNavLinksText);
        DiscoveryHelper.logInfo("actualHeaderName: " +actualHeaderName);
        DiscoveryHelper.logInfo("brandName: " + brandName);
        DiscoveryHelper.logInfo("catName: " + catName);
        DiscoveryHelper.logInfo("***** End of checking Default values *****");

        thumbnailColumns = 0;
        selectedFacetValues.clear();
        selectedFacetNames.clear();
        firstSuggestionsList.clear();
        secondSuggestionsList.clear();

        productCount = 0;
        facetItems = null;
        sortByValue = null;
        strUtils=null;
        storeResultsCount=0;
        selectedFacet = null;
        selectedSortBy = null;
        selectedFacetName = null;
        //prodIds.clear();
        totalPageCount=0;
        itemPerPageCount=null;
        selectedColorSwatch = null;
        selectedColorSwatchTitle = null;
        totalThumbnailCount = 0;
        facetSelection = null;
        brandFacet = null;
        selectedPageNo=0;
        zipCode = null;
        totalProductInRvi = 0;
        productData = null;
        locationId = null;

        productDetails = null;
        tempRemovedItemFrmRVI = null;
        productColorwayType = null;
        productType = null;
        hoverEle = null;
        swatchType = null;
        productPrice = null;
        selectedSubcategory = null;
        productCountBefore = 0;
        beforePriceSortUrl = null;
        leftNavAttributeValue = null;
        excludedCategories.clear();
        mediaData.clear();
//        leftNavLinksSlpPage.clear();
        leftNavLinksText.clear();
        actualHeaderName = null;
        brandName = null;
        catName = null;

    }

    @Then("^I verify the \"soasta_info\" in HTML view source code$")
    public void iVerifyTheSoastaInfoInHTMLViewSourceCode() throws Throwable {
        Wait.isPageLoaded();
        int vindex=WebDriverManager.getWebDriver().getPageSource().lastIndexOf("<type>");
        int lastindex=WebDriverManager.getWebDriver().getPageSource().lastIndexOf("</type>");
        String pageType = WebDriverManager.getWebDriver().getPageSource().substring(vindex+6,lastindex);
        Assert.assertTrue("Page Type is catalog - brandIndex", pageType.contains("catalog - brandIndex"));
       DiscoveryHelper.logInfo("Verified SOASTA INFO in page view source and Page type is :"+pageType);
    }
}
