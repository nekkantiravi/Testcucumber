package com.macys.sdt.projects.Discovery.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom.SearchAndBrowse;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.*;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import com.macys.sdt.shared.actions.MEW.panels.MEWLeftFacet;
import com.macys.sdt.shared.actions.MEW.panels.Pagination;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.runner.RunConfig.brand;
import static com.macys.sdt.framework.utils.StepUtils.bloomingdales;
import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;
import static com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom.SearchAndBrowse.search_term;
import static com.macys.sdt.shared.steps.MEW.PageNavigation.runMode;


public class SearchAndBrowseSteps {
    private static final Logger log = LoggerFactory.getLogger(SearchAndBrowseSteps.class);

    @Then("^I verify there are products on the page$")
    public void iVerifyThereAreProductsOnThePage() {
        Wait.untilElementPresent("category_browse.product_name_without_brand_name");
        Assert.assertTrue(Elements.anyPresent("category_browse.product_name_without_brand_name"));
    }

    @And("^the featured labels should be displayed in the no results page$")
    public void theFeaturedLabelsShouldBeDisplayedInTheNoResultsPage() throws Throwable {
        Elements.elementShouldBePresent("zero_results.featured_categories");
        log.info("featured labels displayed on the no results page");
    }

    @And("^I should see a zero registry search results message on search landing page for the keyword \"([^\"]*)\"$")
    public void iShouldSeeAZeroRegistrySearchResultsMessageOnSearchLandingPageForTheKeyword(String keyword) throws Throwable {
        Wait.untilElementPresent("zero_results.no_results");
        List<String> actual_error_messages = Elements.findElement("zero_results.no_results").findElements(By.tagName("span")).stream().map(element -> element.getText()).collect(Collectors.toList());
        List<String> expected_messages = Arrays.asList("SEARCH RESULTS FOR", keyword, ": 0 items found.", "Unfortunately no items were found matching your search term. Return to bloomingdales.com");
        Assert.assertTrue("ERROR - APP :- Incorrect message displayed on ZSR", actual_error_messages.containsAll(expected_messages));
        log.info("Verified related messages on ZSR");

    }

    @And("^I should see the registry links be displayed in the no results page$")
    public void iShouldSeeTheRegistryLinksBeDisplayedInTheNoResultsPage() throws Throwable {
        Wait.forPageReady();
       Assert.assertTrue("Registry latches displayed on zero results page",Elements.findElement("zero_results.registry_links").isDisplayed());
    }

    @And("^I navigate to a random PDP page from search results page$")
    public void iNavigateToARandomPDPPageFromSearchResultsPage() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("search.result_list");
        Clicks.randomJavascriptClick("search.result_list");
        shouldBeOnPage("product_display");
        log.info("Navigated to random PDP page");
    }

    @Then("^I should see not more than (\\d+) products in the browse page$")
    public void iShouldSeeNotMoreThanProductsInTheSearchPage(int productsOnPage) throws Throwable {
        Wait.forPageReady();
        if (bloomingdales()) {
            Assert.assertTrue("Browse Page is not displaying  90 products are displaying",
                    Elements.findElements("category_browse.product_name_without_brand_name").size() == productsOnPage);
            log.info("Verified total number of products displayed on the page is exact 90");
        } else {
            Assert.assertTrue("ERROR - APP: More than 48 products are displaying on browse page", SearchAndBrowseActions.getProductIds().size() <= productsOnPage);
            log.info("Verified total number of products displayed on the page is less than or equal to 48");
        }
    }

    @And("^pagination should be displayed on browse page$")
    public void paginationDisplayedOnBbrowsePage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Pagination is not displayed on browse page ",
                Elements.findElement("category_browse.select_page_no").isDisplayed());
        log.info("Verified pagination is displayed on browse page");
    }

    @And("^I verify total product available on browse page is less then (\\d+)$")
    public void productAvailableOnBrowsePageLessThen(int expectedProducts) throws Throwable {
        int tatalAvailableProduct= totalNoOfProductAvailable();
        Assert.assertTrue("Browse Page has more then 90 products",
                tatalAvailableProduct<=expectedProducts);
        log.info("Verified total number of products displayed on the page is not more the 90");
    }

    @And("^I verify total product available on browse page is more then (\\d+)$")
    public void productAvailableOnBrowsePageMoreThen(int expectedProducts) throws Throwable {
        Assert.assertTrue("Browse Page has not then 90 products",
                totalNoOfProductAvailable()>expectedProducts);
        log.info("Verified total number of products displayed on the page is more the 90");
    }

    @And("^no pagination should be displayed on browse page$")
    public void noPaginationDisplayedOnBbrowsePage() throws Throwable {
        Assert.assertTrue("Pagination is displayed on browse page",
                !Elements.isElementInView(Elements.findElement("category_browse.select_page_no")));
        log.info("Verified pagination is displayed on browse page");
    }

    @Then("^browse pagination should be displayed and defaulted to first page$")
    public void brosePaginationShouldBeDisplayedAndDefaultedToFirstPage() throws Throwable {
        Assert.assertTrue("Default page is not first", Pagination.currentPageNumber() == 1);
    }

    @When("^I navigate to \"([^\"]*)\" page on browse page$")
    public void iNavigateToNextPageOnSearchPage(String nev) throws Throwable {
        WebDriverManager.getWebDriver().navigate().refresh();
        Pagination.selectPagination(nev);
        Wait.forPageReady();
        log.info("Selected"+ nev +"  button");
    }

    @And("^current browse page number should be equal to (\\d+)$")
    public void currentBrowsePageNumberShouldBeEqual(int pageNumber) throws Throwable {
        Wait.untilElementPresent("pagination.pagination");
        shouldBeOnPage("category_browse");
        Utils.threadSleep(10000,"ERROR-APP: Sleep timed ouit while pagination");
        String errorMsz = "Expected page is " + pageNumber + " Actual page is " + Pagination.currentPageNumber();
        Assert.assertEquals(errorMsz, Pagination.currentPageNumber(), pageNumber);
        log.info("Verified current page as " + pageNumber);
    }

    @And("^current page number should be set to last page$")
    public void currentPageNumberShouldBeSetToLastPage() throws Throwable {
        WebDriverManager.getWebDriver().navigate().refresh();
        Assert.assertEquals("", Pagination.currentPageNumber(), Integer.parseInt(Elements.getText("pagination.last_page_number_in_pagination")));
        log.info("Verified current page number" );
    }

    @And("^I select first page from drop-down$")
    public void iSelectFirstPageFromDropDown() throws Throwable {
        WebDriverManager.getWebDriver().navigate().refresh();
        DropDowns.selectByIndex("category_browse.select_page_no", 1);
        log.info("Verified current page number" );
    }
    int totalNoOfProductAvailable()
    {
        String productCount = Elements.getText("category_browse.total_products").trim();
        String[] product_count_item = productCount.split(" ");
        return Integer.parseInt(product_count_item[product_count_item.length-2]);
    }

    @And("^I click on search button$")
    public void iClickOnSearchButton() throws Throwable {
        WebElement wbSearchBar = Elements.findElement("category_browse.search_bar");
        wbSearchBar.sendKeys(Keys.RETURN);
    }

    @Then("^I should be on the zero search results page$")
    public void iShouldBeOnZeroSearchResultsPage() throws Throwable {
        Utils.threadSleep(4000, "Waiting for page load...");
        String message_text = Elements.findElement("zero_results.invalid_search_message_text").getText();
        Assert.assertTrue("ERROR:- Not on ZSR page, Incorrect message displayed.", message_text.contains("We couldn't find a match for"));
        log.info("Verified page is on ZSR");
    }

    @Then("^I should see boots on browse header label SRP page$")
    public void iShouldSeetheHeaderLabel() throws Throwable {
        Wait.untilElementPresent("category_browse.srp_header_label");
        Assert.assertTrue(Elements.findElement("category_browse.srp_header_label").getText().contains("Boots"));
        log.info("Verified the user is navigated on Boots search result page");
    }

    @Then("^I verify \"([^\"]*)\" is selected in sort by drop down$")
    public void iVerifyIsSelectedInSortByDropDown(String sortBy) {
        Wait.untilElementPresent("search_result.sort_by_select");
        Assert.assertEquals("ERROR: \"" + sortBy + "\" is NOT selected in sort by drop down",
                sortBy, new Select(Elements.findElement("search_result.sort_by_select")).getFirstSelectedOption().getText());
        log.info("Verified the selected value of the sort by drop down");
    }

    @And("^I select \"([^\"]*)\" sort by drop down$")
    public void iSelectSortByDropDown(String toSelect) {
        Wait.untilElementPresent("search_result.sort_by_select");
        DropDowns.selectByText("search_result.sort_by_select", toSelect);
        log.info("Selected \"" + toSelect + "\" in sort by drop down");
        Wait.forPageReady();
    }

    @And("^I select \"([^\"]*)\" sort by drop down on category browse page$")
    public void iSelectSortByDropDownOnCategoryBrowsePage(String toSelect) {
        Wait.untilElementPresent("category_browse.sort_by_select");
        DropDowns.selectByText("category_browse.sort_by_select", toSelect);
        log.info("Selected \"" + toSelect + "\" in sort by drop down");
        Wait.forPageReady();
    }

    @When("^I apply the facets")
    public void iApplyTheFacets() {
        MEWLeftFacet.applyFacets();
    }

    @Then("^I should see this \"([^\"]*)\" keyword in page url$")
    public void verifyShopTopLevelCategory(String menuItem) throws Throwable {
        String currentURL = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("shop level category isn't being displayed",currentURL.contains(menuItem));
        log.info("shop level category is being displayed...");
    }

    @Then("^I should see products sorted by \"([^\"]*)\" on the \"([^\"]*)\" DLP page$")
    public void iShouldSeeProductsSortedByOnTheDlpPage(String sort_by, String brand) throws Throwable {
        Assert.assertEquals("ERROR - APP: prouduts are not sorted by " + sort_by,
                WSSGSearchService.getProductIds(brand, runMode, sort_by, null, null), SearchAndBrowseActions.getProductIds());
        log.info("products are sorted correctly as per the response");
    }

    @Then("^I should see \"([^\"]*)\" option selected as default on (search|browse|DLP) page$")
    public void iShouldSeeOptionSelectedAsDefaultOnSearchPage(String defaultSortOption,String pageType) throws Throwable {
        String defaultSortText;
        Wait.untilElementPresent("search_result.sort_by_select");
        if(pageType.equalsIgnoreCase("search")||pageType.equalsIgnoreCase("DLP")) {
            defaultSortText = DropDowns.getSelectedValue(By.id("mb-search-select-sortby"));
        }else{
            defaultSortText = DropDowns.getSelectedValue(By.id("mb-product-list-buttons-sortby"));
        }
        Assert.assertTrue("ERROR - APP: Default sort option is not as expected", defaultSortText.equalsIgnoreCase(defaultSortOption));
        log.info("Default sort order option verified succesfuly");
    }

    @Then("^I should see only products on (browse|search) page are shown according to \"([^\"]*)\" order and selected filters$")
    public void iShouldSeeOnlyProductsOnPageAreShownAccordingToSelectedOptions(String type, String sortOption) throws Throwable {
        Wait.untilElementPresent("search_result.total_products");
        int count = type.equals("search") ? WSSGSearchService.getItemCount(SearchAndBrowse.search_term, runMode, sortOption, String.valueOf(Pagination.currentPageNumber()), SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()), runMode, sortOption, String.valueOf(Pagination.currentPageNumber()), SearchAndBrowseActions.selectedFacetValues());
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        List<Integer> productIds = type.equals("search") ? WSSGSearchService.getProductIds(SearchAndBrowse.search_term, runMode, sortOption, String.valueOf(Pagination.currentPageNumber()), SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getProductIds(Integer.parseInt(new Browse().getCategoryId()), runMode, sortOption, String.valueOf(Pagination.currentPageNumber()), SearchAndBrowseActions.selectedFacetValues());
        Assert.assertEquals("ERROR - DATA :: Product ids mismatch between service call and UI for facet " + FacetSelections.selected_subfacet,
                productIds, SearchAndBrowseActions.getProductIds());
        log.info("Verified products are displayed for the selected facet values only");
    }

    @Then("^I should see only products on (browse|search) page with selected facet values$")
    public void iShouldSeeOnlyProductsPageWithSelectedFacetValues(String type) throws Throwable {
        Wait.untilElementPresent("search_result.total_products");
        int count = type.equals("search")? WSSGSearchService.getItemCount(SearchAndBrowse.search_term, runMode,null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getItemCount(Integer.parseInt(new Browse().getCategoryId()),runMode, null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues());
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        List<Integer> productIds = type.equals("search")? WSSGSearchService.getProductIds(SearchAndBrowse.search_term, runMode,null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues())
                : WSSGBrowseService.getProductIds(Integer.parseInt(new Browse().getCategoryId()),runMode, null,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues());
        Assert.assertEquals("ERROR - DATA :: Product ids mismatch between service call and UI for facet " + FacetSelections.selected_subfacet,
                productIds,SearchAndBrowseActions.getProductIds());
        log.info("Verified products are displayed for the selected facet values only");
    }

    @Then("^I should see products sorted by \"([^\"]*)\" on the (search results|category browse) page$")
    public void iShouldSeeProductsSortedByOnTheSearchResultsPage(String sort_by, String pageType) throws Throwable {
        Wait.forPageReady();
        Utils.threadSleep(5000,"ERROR-APP: Search page loading failed");
        if (pageType.equalsIgnoreCase("search results")) {
            Assert.assertEquals("ERROR - APP: prouduts are not sorted by " + sort_by,
                    WSSGSearchService.getProductIds(search_term, runMode, sort_by, null, null), SearchAndBrowseActions.getProductIds());
        } else if (pageType.equalsIgnoreCase("category browse")){
            Assert.assertEquals("ERROR - APP: prouduts are not sorted by " + sort_by,
                    WSSGBrowseService.getProductIds(Integer.parseInt(new Browse().getCategoryId()), runMode, sort_by, null, null), SearchAndBrowseActions.getProductIds());
        }
        log.info("products are sorted correctly as per the response");
    }

    @Then("^I should see \"([^\"]*)\" sort option on (search|browse|dlp) page$")
    public void iShouldSeeSortOptionOnPage(String defaultSortOption, String pageType) throws Throwable {
        String defaultSortText;
        Wait.untilElementPresent("search_result.sort_by_select");
        if (pageType.equalsIgnoreCase("search")||(pageType.equalsIgnoreCase("dlp"))) {
            defaultSortText = DropDowns.getSelectedValue(By.id("mb-search-select-sortby"));
        } else {
            defaultSortText = DropDowns.getSelectedValue(By.id("mb-product-list-buttons-sortby"));
        }
        Assert.assertTrue("ERROR - APP: Found different sort option other than expected" + defaultSortText, defaultSortText.equalsIgnoreCase(defaultSortOption));
        log.info("Default sort order option verified succesfuly");
    }

    @Then("^I should see products order modified by \"([^\"]*)\" on UI page$")
    public void iShouldSeeProductsOrderModifiedByOnUIPage(String sortBy) throws Throwable {
        Wait.untilElementPresent("search_result_product_thumbnails.search_results_thumbnail_wrapper");
        Double firstProductPrice = 0.00, secondProductPrice = 0.00, lastProductPrice = 0.00;
        Double formatPrices;
        List<Double> priceList = new ArrayList<>();
        List<String> productPrices = Elements.findElements("left_facet.price_list").stream().map(webElement -> webElement.getText().replaceAll("[a-zA-Z]|[$, ]", "")).collect(Collectors.toList());
        for (Integer i = 0; i <productPrices.size(); i++) {
            formatPrices = productPrices.get(i).contains("-") ? Double.valueOf(productPrices.get(i).split("-")[0]) : Double.valueOf(productPrices.get(i));
            priceList.add(formatPrices);
        }
        firstProductPrice = priceList.get(0);
        secondProductPrice = priceList.get(1);
        lastProductPrice = priceList.get(2);
        if (sortBy.equalsIgnoreCase("Price: Low to High")) {
            Assert.assertTrue("First product price is not less than or equal to second product price",
                    firstProductPrice <= secondProductPrice);
            Assert.assertTrue("First product price is not less than last product price",
                    firstProductPrice < lastProductPrice);
        } else if (sortBy.equalsIgnoreCase("Price: High to Low")) {
            Assert.assertTrue("First product price is not greater than or equal to second product price",
                    firstProductPrice >= secondProductPrice);
            Assert.assertTrue("First product price is not greater than last product price",
                    firstProductPrice > lastProductPrice);
        } else {
            Assert.fail("Sort By"+ sortBy + "functionality is not working properly");
        }
        log.info("Verified Sort By " + sortBy + "functionality");
    }

    @And("^I verify all media banners are displaying$")
    public void iVerifyAllMediaBannersAreDisplaying() throws Throwable {
        Wait.forPageReady();
        String error_message = "ERROR-DATA: Media links data not available";
        Assert.assertFalse(error_message, SearchAndBrowseActions.getAllCatsplashMediaImageLinks().isEmpty());
        log.info("Verified media banners on catsplash page");
    }

    @And("^I verify all displayed links navigating to respective page on \"([^\"]*)\" cat splash page$")
    public void iVerifyAllDisplayedLinksNavigatingToRespectivePageOnCatSplashPage(String category) throws Throwable {
        Wait.forPageReady();
        for (String link : SearchAndBrowseActions.getAllCatsplashMediaImageLinks()) {
            if (link.contains("https://"))
                Assert.assertTrue("ERROR ENV: Media links are not redirecting on" + category + " catsplash", CommonUtils.verifyresponseCode(link));
        }
    }

    @Then("^I should see only products on \"([^\"]*)\" dlp page are shown according to \"([^\"]*)\" order and selected filters$")
    public void iShouldSeeOnlyProductsOnDlpPageAreShownAccordingToOrderAndSelectedFilters(String brand, String sort_by) throws Throwable {
        Wait.untilElementPresent("search_result.total_products");
        int count =  WSSGSearchService.getProductIds(brand, runMode,sort_by,String.valueOf(Pagination.currentPageNumber()),SearchAndBrowseActions.selectedFacetValues()).size();
        Assert.assertEquals("ERROR - App:prouduts are not displayed with selected facet values ",
                count, SearchAndBrowseActions.getItemCount());
        log.info("Verified no facet values available in breadcrumb and products are displayed according to service response");
    }

    @Then("^I should see prices of the products has commas with at least 4 digits$")
    public void verifyProductsHasCommas() throws Throwable {
        Wait.untilElementPresent("search_result_product_thumbnails.search_regular_price");
        List<WebElement> productsPrice = Elements.
                findElements(Elements.element("search_result_product_thumbnails.search_regular_price"));
        for (WebElement price : productsPrice) {
            String regularPrice = price.getText();
            if(regularPrice.contains("-")) {
                String priceRange[]= regularPrice.split("-");
                String startPrice =  priceRange[0];
                String lastPrice =  priceRange[1];
                if(startPrice.length()>=10 && lastPrice.length()>=10)
                {
                    Assert.assertTrue("products having start price range don't have commas",
                            startPrice.contains(","));
                    Assert.assertTrue("products having last price range don't have commas",
                            lastPrice.contains(","));
                }
                else {
                    log.info("Products having start and last price range are of 3 digits");
                }
            }
            else if(regularPrice.length()>=10) {
                Assert.assertTrue("Prices of products don't have commas", regularPrice.contains(","));
            }
            else {
                log.info("Prices of products are of 3 digits");
            }
        }
    }
}
