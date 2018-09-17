package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.*;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import com.macys.sdt.shared.actions.MEW.panels.Pagination;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;
import static com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions.getPricesFromPage;
import static com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions.getPricesList;
import static com.macys.sdt.shared.steps.MEW.PageNavigation.runMode;
import static java.util.Collections.reverseOrder;

public class SearchAndBrowse extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);
    public static String search_term = null;
    public static String selectedRandomACText = null;
    private static Integer selectedPageNumber = null;
    public static int scroll_item = 0;
    public static int quickViewProdId;

    @When("^I navigate to \"([^\"]*)\" dynamic landing page in (domestic|iship) using mobile website$")
    public void iNavigateToDynamicLandingPageInModeUsingMobileWebsite(String keyword, String mode) throws Throwable {
       // new PageNavigation().I_navigate_to_brand_index_page_using_mobile_website(mode);
        i_type_in_mew_search("Brands");
        shouldBeOnPage("brand_index");
        pausePageHangWatchDog();
        Clicks.clickElementByText("brand_index.brand_name", keyword);
        resumePageHangWatchDog();
        shouldBeOnPage("dynamic_landing");
        search_term = keyword;
    }

    @And("^I select \"([^\"]*)\" brand from the list$")
    public void iSelectBrandFromTheList(String designer) throws Throwable {
        search_term = designer;
        shouldBeOnPage("brand_index");
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElement(By.linkText(designer)));
        Clicks.click(Elements.findElement(By.linkText(designer)));
        Wait.forPageReady();
        Utils.threadSleep(5000,"ERROR - APP: Unable to select brand from dlp page");
        log.info("Navigating to selected brand dlp page");
    }

    @When("^I type \"([^\"]*)\" in mew search and click enter$")
    public static void i_type_in_mew_search(String keyword) throws Throwable {
        search_term = keyword;
        if (macys()) {
            TextBoxes.typeTextbox("home.search_field", search_term);
            Clicks.click("search.go");
        } else {
            TextBoxes.typeTextNEnter("home.search_field", search_term);
        }
        Wait.forPageReady();
        log.info("Entered Search keyword in the mew search box");
    }

    @Then("^I Should see the correct URL \"([^\"]*)\"$")
    public void verifyUrl(String expectedUrl) {
        Assert.assertTrue("Error - Mew : The current URL: " + WebDriverManager.getCurrentUrl() + " does not include the expected text: " + expectedUrl, WebDriverManager.getCurrentUrl().contains(expectedUrl));
        log.info("successfully see correct URL");
    }

    @Then("^I should see rating & reviews available on the products of SRP")
    public void I_should_see_rating_and_reviews_on_SRP() throws Throwable {
        Wait.untilElementPresent("search.rating_srp");
        Wait.untilElementPresent("search.reviews_srp");
        List<WebElement> productRatings = Elements.findElements(Elements.element("search.rating_srp"));
        List<WebElement> productReviews = Elements.findElements(Elements.element("search.reviews_srp"));
        Assert.assertTrue("SRP page do not contain products with ratings", productRatings.size() > 0);
        Assert.assertTrue("SRP page do not contain products with reviews", productReviews.size() > 0);
    }

    @When("^I type \"([^\"]*)\" in mew search$")
    public void i_type_two_characters_in_search_box(String input_string) throws Throwable {
        Clicks.click("home.search_field");
        TextBoxes.typeTextbox("home.search_field", input_string);
        log.info("Typing keyword " + input_string + "global search field");
    }

    @And("^I navigate to a random PDP page on (search results|DLP) page$")
    public void iNavigateToARandomPDPPageOnSearchResultsPage(String page) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("search_result.result_list");
        Clicks.randomJavascriptClick("search_result.result_list");
        Wait.forPageReady();
        log.info("Nivigated to random PDP page");
    }

    @Then("^I should see auto complete suggestions in mew$")
    public void i_should_see_search_keyword_autocomplete_suggestions_in_mew() throws Throwable {
        Wait.untilElementPresent("search_result.suggestions_list");
        List<WebElement> autoSuggetionsList = Elements.findElements(Elements.element("search_result.suggestions_list"));
        Assert.assertTrue("ERROR - ENV: Autosuggetions are not displaying", Wait.untilElementNotPresent("mew_hnf.lastSearches") && !autoSuggetionsList.isEmpty());
        Assert.assertTrue("ERROR - APP: More than 10 Auto suggetions are displaying", autoSuggetionsList.size() <= 10);
        log.info("Verified autocomplete suggestions are displayed");
    }

    @Then("^I should not see auto complete suggestions in mew$")
    public void i_should_not_see_search_keyword_autocomplete_suggestions_in_mew() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Auto Suggetions are displaying", Elements.findElements(Elements.element("search_result.suggestions_list")).size() == 0);
        log.info("Verified autocomplete suggestions are not displayed");
    }

    @When("^I select random autocomplete suggestion in mew$")
    public void iSelectRandomAutocompleteSuggestion() throws Throwable {
        if (macys()) {
            Wait.untilElementPresent("search_result.autocomplete_container");
        } else {
            Wait.untilElementPresent("search_result.autocomplete_suggestions");
        }
        List<WebElement> autoCompleteElements = Elements.findElements("search_result.suggestions_list");
        int itemIndex = new Random().nextInt(autoCompleteElements.size());
        WebElement randomAutoCompleteElement = autoCompleteElements.get(itemIndex);
        selectedRandomACText = randomAutoCompleteElement.getText();
        Clicks.click(randomAutoCompleteElement);
        log.info("Selected random AC text");
    }

    @Then("^I verify user navigated to correct page$")
    public void iVerifyUserNavigatedToCorrectSearchPage() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        if (url.contains("/shop/search?")) {
            shouldBeOnPage("search_result");
            Wait.untilElementPresent("search_result.search_keyword");
            String searchKeyWord = Elements.findElement("search_result.search_keyword").getText();
            log.info("Search keyword is" + searchKeyWord);
            Assert.assertTrue("ERROR - DATA :: User navigated to diffrent search page", searchKeyWord.equalsIgnoreCase(selectedRandomACText));
        } else if (!url.contains("search?") && url.contains("id=")) {
            shouldBeOnPage("category_browse");
            Wait.untilElementPresent("category_browse.browse_category_name");
            String searchKeyWord = Elements.findElement("category_browse.browse_category_name").getText().replace(":", "");
            log.info("Navigated to " + searchKeyWord + " category");
            Assert.assertTrue("ERROR - DATA :: User navigated to diffrent search page", searchKeyWord.equalsIgnoreCase(selectedRandomACText));
            List<WebElement> headerLabels = Elements.findElements("category_browse.srp_header_label");
            List<String> headerLabelsText = new ArrayList<>();
            for (WebElement headerText : headerLabels) {
                headerLabelsText.add(headerText.getText());
            }
            Assert.assertTrue("ERROR - APP :: Header label links not showing brand category", headerLabelsText.contains(selectedRandomACText));
        }
    }

    @Then("^I should see the selected AC text in the search box$")
    public void iShouldSeeTheSelectedACTextInTheSearchBox() throws Throwable {
        Wait.untilElementPresent("home.search_field");
        Assert.assertTrue(Elements.findElement("home.search_field").getAttribute("value").equalsIgnoreCase(selectedRandomACText));
        log.info("Verified Auto complete text persistence");
    }

    @When("^I click on continue checkout button$")
    public void iClickOnContinueCheckoutButton() throws Throwable {
        Wait.forPageReady();
        Clicks.click("search_checkout.checkout_button");
        log.info("Continue checkout button is selected");
    }

    @Then("^I scroll to add bag button$")
    public void iScrollToAddBagButton() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("product_display.description_container");
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElement("product_display.description_container"));
    }

    @Then("^I should see the (list|grid|gallery) view as selected$")
    public void iShouldSeeTheListViewAsSelected(String view) throws Throwable {
        Wait.untilElementPresent("category_browse." + view + "_view");
        Assert.assertTrue("Error - App: " + view + " view is not selected", Elements.getElementAttribute("category_browse." + view + "_view", "aria-pressed").contains("true"));
    }

    @Then("^I should see the (grid|list|gallery) view of products in the (search|browse|DLP) page$")
    public void iShouldSeeTheListViewOfProductsInTheSearchPage(String view, String page) throws Throwable {
        Wait.secondsUntilElementPresent("search_result.product_thumbnail", 7);
        List<WebElement> products = Elements.findElements("search_result.product_thumbnail");
        switch (view) {
            case "list":
            case "grid":
                for (WebElement product : products) {
                    String selector = Elements.elementPresent(By.className("m-search-prices")) ? "m-search-prices" : "m-browse-prices";
                    String priceText = product.findElement(By.className(selector)).getText();
                    Assert.assertTrue("image is not displayed for the product",
                            product.findElement(By.xpath("//div[1]/img")).isDisplayed());
                    Assert.assertTrue("name of the prodcut is not displayed for the product",
                            !product.findElement(By.className("m-product-grid-name")).getText().isEmpty());
                    Assert.assertTrue("prices of the prodcut is not displayed for the product",
                            product.findElement(By.className(selector)).isDisplayed());
                    if (!priceText.contains("-") && !priceText.contains("Your Choice")) {
                        Assert.assertTrue("ERROR - APP: ATB button is not displayed for member product " + product.getAttribute("data-product_id"),
                                Elements.elementPresent("search_result_product_thumbnails.add_to_bag_button"));
                    }
                }
                Assert.assertTrue("ratings are displayed correctly", Elements.findElements(By.className("m-rating-stars-inner")).size() > 0);
                break;
            case "gallery":
                for (WebElement product : products) {
                    String selector = Elements.elementPresent(By.className("m-search-prices")) ? "m-search-prices" : "prod-image-price-badge";
                    String priceText = product.findElement(By.className(selector)).getText();
                    Assert.assertTrue("image is not displayed for the product",
                            product.findElement(By.xpath("//div[1]/img")).isDisplayed());
                    Assert.assertTrue("prices of the prodcut is not displayed for the product",
                            product.findElement(By.className(selector)).isDisplayed());
                    if (!priceText.contains("-") && !priceText.contains("Your Choice")) {
                        Assert.assertTrue("ERROR - APP: ATB button is not displayed for member product " + product.getAttribute("data-product_id"),
                                Elements.elementPresent("search_result_product_thumbnails.add_to_bag_button"));
                    }
                }
                Assert.assertFalse("ratings are displayed correctly", Elements.elementPresent(By.className("m-rating-stars-inner")));
                Assert.assertFalse("name of the prodcut is displayed for the product", Elements.elementPresent(By.className("m-product-grid-name")));
                break;
        }
        log.info("Verified " + view + "view of the products");
    }

    @Then("^I should see toggle view button$")
    public void iShouldSeeToggleViewButton() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("search_result.toggle_dropdown", 15);
        Assert.assertTrue("ERROR - APP:- Toggle view button is not displayed", Elements.findElement("search_result.toggle_dropdown").isDisplayed());
        log.info("Verified toggle view button");
    }

    @And("^I click on (sort|filter) link$")
    public void iClickOnLink(String option) throws Throwable {
        switch (option.toLowerCase()) {
            case "sort":
                Wait.secondsUntilElementPresent("search_result.sort_by", 20);
                Clicks.click("search_result.sort_by");
                break;
            case "filter":
                Clicks.clickWhenPresent("search_result.filter_by_select");
                Wait.secondsUntilElementPresent("left_facet.apply", 10);
                break;
        }
        log.info(option + " link is selected");
    }

    @And("^I should see default sort is \"([^\"]*)\"$")
    public void iShouldSeeDefaultSortIs(String selected) throws Throwable {
        By element = Elements.element("search_result.sort_by");
        Assert.assertTrue("ERROR - APP:- " + selected + " sort order is not displayed as default", DropDowns.getSelectedValue(element).contains(selected));
        log.info("Verified default sort order");
    }

    @Then("^I should be on the zero search results page with relevant message displayed$")
    public void iShouldBeOnTheZeroSearchResultsPageWithRelevantMessageDisplayed () throws Throwable {
        Utils.threadSleep(4000, "Waiting for page load...");
        String message_text = Elements.findElement("zero_results.invalid_search_message_text").getText();
        Assert.assertTrue("ERROR:- Not on ZSR page, Incorrect message displayed.", message_text.contains("We couldn't find a match for"));
        log.info("Verified page is on ZSR");
    }

    @Then("^I should see a related search message on search landing page for the keyword \"([^\"]*)\"$")
    public void iShouldSeeARelatedSearchMessageOnSearchLandingPageForTheKeyword(String keyword) throws Throwable {
        String message_text = Elements.findElement("zero_search_result.invalid_search_message_text").getText();
        Assert.assertTrue("ERROR - APP:- Incorrect message displayed on ZSR", message_text.matches("We couldn't find a match for \"" + keyword + "\"."));
        List<String> expected_bullet_messages = Arrays.asList("Check for spelling mistakes or typos", "Search by Web ID", "Use generic keywords");
        List<String> actual_bullet_messages = Elements.findElement("zero_search_result.empty_message")
                .findElements(By.tagName("li")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP :- Incorrect bullets points displayed on ZSR", expected_bullet_messages.equals(actual_bullet_messages));
        log.info("Verified related messages on ZSR");
    }

    @And("^the category icons should be available in the no results page$")
    public void theCategoryIconsShouldBeAvailableInTheNoResultsPage() throws Throwable {
        Assert.assertTrue("ERROR - ENV:- Category icons are not visible on ZSR", Elements.elementPresent("zero_search_result.no_results"));
        log.info("Verified category icons on ZSR");
    }

    @Then("^I should be on zero search results page$")
    public void iShouldBeOnZeroSearchResultsPage() throws Throwable {
        shouldBeOnPage("zero_search_result");
        log.info("Verified page is on ZSR");
    }

    @Then("^I should be on the( registry)? search results page$")
    public void iShouldBeOnTheSearchResultsPage(String mode) throws Throwable {
        if (mode == null)
            shouldBeOnPage("search_result");
        else {
            Assert.assertTrue("Error - Env: Not on registry search page", WebDriverManager.getCurrentUrl().contains("/shop/registry/wedding/search"));
            shouldBeOnPage("search_result");
        }
        log.info("Nivigated to Search results page");
    }

    @Then("^I should be on the browse page$")
    public void iShouldBeOnTheBrowsePage() throws Throwable {
        shouldBeOnPage("category_browse");
        log.info("Nivigated to browse page");
    }

    @Then("^I should see all products displayed in (search results|dlp) page$")
    public void iShouldSeeAllProductsDisplayedInSearchResultsPage(String page) throws Throwable {
        Map<String, String> params = new HashMap<>();
        if (Pagination.currentPageNumber() != 1) {
            params.put("pathname", "/shop/search/Pageindex/" + Pagination.currentPageNumber());
        }
        List<Integer> expectedProductIds = XAPISearchService.getProductIds(search_term, params);
        List<Integer> actualProductIds = SearchAndBrowseActions.getProductIds();
        Assert.assertEquals("ERROR - APP: Products are not dispplaying as per the service", expectedProductIds, actualProductIds);
        log.info("Verified products displayed in order as obtained response from the service on " + page + " page");
    }

    @Then("^I should see not more than (\\d+) products in the (search|DLP) page$")
    public void iShouldSeeNotMoreThanProductsInTheSearchPage(int productsOnPage, String page) throws Throwable {
        List<Integer> productIds = SearchAndBrowseActions.getProductIds();
        Assert.assertTrue("ERROR - APP: More than 48 products are displaying on " + page + " page", productIds.size() <= productsOnPage);
        log.info("Verified total number of products displayed on the page is less than or equal to 48");
    }

    @And("^I should see pagination is displayed$")
    public void iShouldSeePaginationIsDisplayed() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.pagination", 15);
        Assert.assertTrue("ERROR - APP: Pagination is not displayed", Elements.elementPresent("search_result.pagination"));
        log.info("Verified pagination is displayed on search page");
    }

    @And("^Pagination should not be displayed$")
    public void paginationShouldNotBeDisplayed() throws Throwable {
        if (macys()) {
            Assert.assertFalse("ERROR - APP: Pagination is displayed", Elements.elementPresent("search_result.pagination"));
            log.info("Verified pagination is not displayed on search page");
        } else {
            Elements.elementShouldBePresent("pagination.select_page_no");
            log.info("Verified display of pagination");
        }
    }

    @Then("^pagination should be displayed and defaulted to first page$")
    public void paginationShouldBeDisplayedAndDefaultedToFirstPage() throws Throwable {
        Assert.assertTrue("ERROR - APP: Default page is not first", Pagination.currentPageNumber() == 1);
    }

    @And("^current page number should be equal (\\d+)$")
    public void currentPageNumberShouldBeEqual(int pageNumber) throws Throwable {
        Wait.untilElementPresent("pagination.pagination");
        String errorMsz = "ERROR - APP: Expected page is " + pageNumber + "Actual page is " + Pagination.currentPageNumber();
        Assert.assertEquals(errorMsz, Pagination.currentPageNumber(), pageNumber);
        log.info("Verified cuurent page as " + pageNumber);
    }

    @When("^I select (\\d+) page on (search|browse|DLP) page$")
    public void iSelectPageOnSearchPage(int pageNumber, String page) throws Throwable {
        selectedPageNumber = pageNumber;
        Pagination.selectpageByNumber(pageNumber);
        log.info("Selected page number " + pageNumber + " on" + page + " page");
    }

    @And("^current page number should be decreased by (\\d+)$")
    public void currentPageNumberShouldBeDecreasedBy(int number) throws Throwable {
        String errorMsz = "ERROR - APP: Expected page is " + (selectedPageNumber - number) + "Actual page is " + Pagination.currentPageNumber();
        Assert.assertTrue(errorMsz, Pagination.currentPageNumber() == selectedPageNumber - number);
        log.info("Verified current page number is " + (selectedPageNumber - number));
    }

    @And("^I should see correct number of pages in pagination$")
    public void iShouldSeeCorrectNumberOfPagesInPagination() throws Throwable {
        if (macys()) {
            Integer productCount = onPage("category_browse") ? XAPIBrowseService.getItemCount(Integer.valueOf(new Browse().getCategoryId()), null) : XAPISearchService.getItemCount(search_term, null);
            int expectedPageCount = (productCount % 48 > 0 ? productCount / 48 + 1 : productCount / 48);
            Assert.assertTrue("ERROR APP: Page count is miss matched", Pagination.pageCount() == expectedPageCount);
            log.info("Verified page count on search page");
        } else {
            int actual_page_count = Elements.findElement("pagination.select_page_no").findElements(By.tagName("option")).size() - 1;
            log.info("Actual page count is :: " + actual_page_count);
            String count_ui = Elements.findElement("search_result.total_products").getText().split(" ")[2];
            int product_count = Integer.parseInt(count_ui);
            log.info("Product count shown :: " + product_count);
            int expectedPageCount = (product_count % 90 > 0 ? product_count / 90 + 1 : product_count / 90);
            log.info("Expected page count is ::" + expectedPageCount);
            Assert.assertTrue("ERROR APP: Page count is mis-matched. Expected : " + expectedPageCount + "Actual page count shown " + actual_page_count, actual_page_count == expectedPageCount);
            log.info("Verified page count successfully");
        }
    }

    @Then("^selected page should be displayed in pagination$")
    public void selectedPageShouldBeDisplayedInPagination() throws Throwable {
        Integer currentpage = Pagination.currentPageNumber();
        String errorMsz = "ERROR - APP: Selected page number is " + selectedPageNumber + " but current page is " + currentpage;
        Assert.assertEquals(errorMsz, currentpage, selectedPageNumber);
    }

    @When("^I should be navigated to last page$")
    public void iShouldBeNavigatedToLastPage() throws Throwable {
        int lastPageNumber = Integer.parseInt(Elements.findElement("pagination.select_page_no").getText().trim().split("\n")[0].split("of")[1].trim());
        int pageCount = Pagination.pageCount();
        Assert.assertEquals("ERROR - DATA : Page count mismatch in pagination", pageCount, lastPageNumber);
        log.info("Navigated to last page");
    }

    @And("^I (should|should not) see next button on pagination$")
    public void iShouldSeeNextButtonOnPagination(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should")) {
            Assert.assertTrue("ERROR - APP: Next button is not displayed", Elements.elementPresent("search_result.goto_next_page"));
            log.info("Verified next button is displayed");
        } else {
            Assert.assertFalse("ERROR - APP: Next button is displayed", Elements.elementPresent("search_result.goto_next_page"));
            log.info("Verified next button is not displayed");
        }
    }

    @And("^I (should|should not) see prev button on pagination$")
    public void iShouldNotSeePrevButtonOnPagination(String condition) throws Throwable {
        Wait.secondsUntilElementPresent("search_result.pagination", 15);
        if (condition.equalsIgnoreCase("should")) {
            Assert.assertTrue("ERROR - APP: Prev button is not displayed", Elements.elementPresent("search_result.goto_previous_page"));
            log.info("Verified Prev button is displayed");
        } else {
            Assert.assertFalse("ERROR - APP: Prev button is displayed", Elements.elementPresent("search_result.goto_previous_page"));
            log.info("Verified Prev button is displayed");
        }
    }

    @When("^I navigate to last page$")
    public void iNavigateToLastPage() throws Throwable {
        Pagination.selectPagination("last");
        log.info("Navigated to last page");
    }

    @And("^I scroll to (\\d+)th product on (search results|browse|DLP) page$")
    public void iScrollToThProductOnSearchResultsPage(int productNumber, String page) throws Throwable {
        Wait.forPageReady();
        scroll_item = productNumber;
        Wait.untilElementPresent("search_result.product_thumbnail");
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElements("search_result.product_thumbnail").get(productNumber - 1));
        log.info("Navigated to " + productNumber + "th element on " + page + " page");
    }

    @And("^I click on (\\d+)th product on (search results|browse|DLP) page$")
    public void iClickOnThProductOnSearchResultsPage(int productNumber, String page) throws Throwable {
        Wait.forPageReady();
        Clicks.click(Elements.findElements("search_result.product_thumbnail").get(productNumber - 1).findElement(By.tagName("img")));
        log.info("clicked on " + productNumber + "th element on " + page + " page");
    }

    @When("^I click on back button on PDP page and navigate to search page$")
    public void iClickOnBackButtonOnPDPPageAndNavigateToSearchPage() throws Throwable {
        WebDriverManager.getWebDriver().navigate().back();
        shouldBeOnPage("search_result");
        log.info("Verified page is search results after clicking back button on PDP page");
    }

    @And("^I should be returned to the same product position on (search|browse|DLP) page$")
    public void iShouldBeReturnedToTheSameProductPositionOnSearchPage(String page) throws Throwable {
        String product_id = Elements.findElements("search_result.product_thumbnail").get(scroll_item - 1).getAttribute("data-product_id");
        WebElement element = Elements.findElement(Elements.paramElement("search_result.product_position", product_id));
        Assert.assertTrue("ERROR - APP: view is not returned to the same product position ", Elements.isElementInView(element));
        log.info("view is returned to the same product position on " + page + " page");
    }

    @Then("^I should see sorting order depends on (Maximum|Minimum) UPC in a colorway priced product$")
    public void iShouldSeeSortingOrderDependsOnMaximumUPCInAColorwayPricedProduct(String value) throws Throwable {
        List<Double> salePrices = getPricesList(value);
        List<Double> sortedOrderPrices = salePrices;
        if (value.equals("Maximum")) {
            Collections.sort(salePrices, reverseOrder());
            Assert.assertEquals("ERROR - APP: The products were not properly sorted with 'High to Low'",
                    sortedOrderPrices, salePrices);
        } else {
            Collections.sort(salePrices);
            Assert.assertEquals("ERROR - APP: The products were not properly sorted with 'Low to High'",
                    sortedOrderPrices, salePrices);
        }
    }

    @Then("^I should see the products that has prices within the range \"([^\"]*)\"$")
    public void iShouldSeeTheProductsThatHasPricesWithinTheRange(String value) throws Throwable {
        List<WebElement> prices = Elements.findElements("search_result.prices_grid");
        List<String> filterPrices = new ArrayList<>();
        List<WebElement> pricesList = null;
        String formatRange = value.replaceAll("[a-zA-Z]|[$, ]", "");
        for (WebElement price : prices) {
            Wait.secondsUntilElementPresent(By.className("m-search-price-label"), 7);
            pricesList = price.findElements(By.className("m-search-price-label"));
            filterPrices.add(pricesList.size() > 1 ? pricesList.get(1).getText().replaceAll("[a-zA-Z]|[$, ]", "")
                    : pricesList.get(0).getText().replaceAll("[a-zA-Z]|[$, ]", ""));
        }
        for (String range : filterPrices) {
            Wait.forPageReady();
            if (range.contains("-")) {
                Assert.assertTrue("filter by price range is not working correctly",
                        Double.valueOf(formatRange.split("-")[0]) <= Double.valueOf(range.split("-")[1]) &&
                                Double.valueOf(formatRange.split("-")[1]) >= Double.valueOf(range.split("-")[0]));
            } else {
                Assert.assertTrue("filter by price range is not working correctly",
                        Double.valueOf(formatRange.split("-")[0]) <= Double.valueOf(range) &&
                                Double.valueOf(formatRange.split("-")[1]) >= Double.valueOf(range));
            }
        }
        log.info("products are within the price range " + value);
    }

    @Then("^I should see products sorted by \"([^\"]*)\" on (search results|browse|DLP) page$")
    public void iShouldSeeProductsSortedByOnSearchResultsPage(String select, String page) throws Throwable {
        Map<String, String> filters = new HashMap<>();
        filters.put("pathname", WebDriverManager.getWebDriver().getCurrentUrl().split("\\.com")[1].split("\\?")[0]);
        if (page.equalsIgnoreCase("search results") || page.equalsIgnoreCase("DLP")) {
            Assert.assertEquals("ERROR - APP:prouduts are not sorted by " + select,
                    XAPISearchService.getProductIds(search_term, filters), SearchAndBrowseActions.getProductIds());
        } else {
            Assert.assertEquals("ERROR - APP:prouduts are not sorted by " + select,
                    XAPIBrowseService.getProductIds(Integer.parseInt(new Browse().getCategoryId()), filters), SearchAndBrowseActions.getProductIds());
        }
        log.info("products are sorted correctly on " + page + " page");
    }

    @Then("^I verify products sorted by \"([^\"]*)\" on UI$")
    public void iVerifyProductsSortedOnUI(String value) throws Throwable {
        ArrayList salePrices = new ArrayList<>();
        ArrayList sortedOrderPrices = new ArrayList<>();
        Utils.threadSleep(10000,"ERROR - APP");
        Wait.forPageReady();
        if (macys()) {
            salePrices = getPricesList(value);
        } else {
            salePrices = getPricesFromPage(value);
        }
        sortedOrderPrices = (ArrayList) salePrices.clone();
        if (value.equals("Price: High to Low")) {
            Collections.sort(salePrices, reverseOrder());
            Assert.assertEquals("ERROR - APP: The products were not properly sorted with 'High to Low'",
                    sortedOrderPrices, salePrices);
        } else {
            Collections.sort(salePrices);
            Assert.assertEquals("ERROR - App: The products were not properly sorted with 'Low to High'",
                    sortedOrderPrices, salePrices);
        }
    }

    @Then("^I should see products sorted by \"([^\"]*)\" on UI page$")
    public void iShouldSeeProductsSortedByOnUIPage(String value) throws Throwable {
        Utils.threadSleep(3000,"ERROR-APP:unable to sort the products");
        int condition = Pagination.pageCount() > 1 ? 3 : 2;
        ArrayList salePrices = new ArrayList<>();
        ArrayList sortedOrderPrices = new ArrayList<>();
        for (int i = 1; i < condition; i++) {
            if (i < Pagination.pageCount())
                Pagination.selectpageByNumber(i);
            Utils.threadSleep(10000,"ERROR - APP: Unable to sort the products");
            Wait.forPageReady();
            if (macys()) {
                salePrices = getPricesList(value);
            } else {
                salePrices = getPricesFromPage(value);
            }
            sortedOrderPrices = (ArrayList) salePrices.clone();
            if (value.equals("Price: High to Low")) {
                Collections.sort(salePrices, reverseOrder());
                Assert.assertEquals("ERROR - APP: The products were not properly sorted with 'High to Low'",
                        sortedOrderPrices, salePrices);
            } else {
                Collections.sort(salePrices);
                Assert.assertEquals("ERROR - App: The products were not properly sorted with 'Low to High'",
                        sortedOrderPrices, salePrices);
            }
        }
    }

    @When("^I navigate to a random page other than first page$")
    public void iNavigateToARandomPageOtherThanFirstPage() throws Throwable {
        if (Pagination.pageCount() > 1) {
            Pagination.selectPagination("random");
            Wait.forPageReady();
            selectedPageNumber = Pagination.currentPageNumber();
            log.info("random page selected successfully");
        } else {
            System.out.print("Seems only one page found. So perform next steps actions on same page");
        }
    }

    @Then("^I should see not more than 48 products per page$")
    public void I_should_not_see_more_than_48_products_per_page() throws Throwable {
        String strTotalItemCount = Elements.findElement("search.all_items_count").getText();
        int nTotalProductCount = Integer.valueOf(strTotalItemCount.substring(1, strTotalItemCount.length() - 1));
        if (nTotalProductCount > 0) {
            if (nTotalProductCount > 48) {
                List<WebElement> strItemPerPage = Elements.findElements("search.items_per_page");
                int nItemsCountPerPage = strItemPerPage.size();
                if (nItemsCountPerPage > 48) {
                    Assert.assertFalse("Search page has more than 48 products ...",
                            nItemsCountPerPage > 48);
                } else {
                    Assert.assertTrue("Pagination is not visible for more then 48 products",
                            Elements.findElement("search.next_button").isDisplayed());
                }
            } else {
                log.info("Product count is <= 48, Pagination is not required ");
            }
            log.info("48 products are visible per page with pagination ...");
        } else {
            log.info("No product found ... ");
        }
    }

    @Then("^I should see browse header label as boots on SRP page$")
    public void iShouldSeetheHeaderLabel() throws Throwable {
        Wait.untilElementPresent("search.srp_header_label");
        Assert.assertTrue(Elements.findElement("search.srp_header_label").getText().contains("Boots"));
        log.info("Verified the user is navigated on Boots search result page");
    }

    @Then("^I should see the applied facets on browse page$")
    public void iShouldSeeAppliedFacetsOnBrowsePage() {
        String strBrandHeader1 = Elements.findElement("search.breadcrumb_header1").getText();
        String strBrandHeader2 = Elements.findElement("search.breadcrumb_header2").getText();
        Assert.assertTrue("First applied facets is not visible on header", SortAndFilterBy.strFirstBrand.contains(strBrandHeader1));
        Assert.assertTrue("Second applied facets is not visible on header", SortAndFilterBy.strSecondBrand.contains(strBrandHeader2));
        List<WebElement> itemsPerPage = Elements.findElements("search.products_on_page");
        for (WebElement product : itemsPerPage) {
            Assert.assertTrue("Products are not of applied facets",
                    product.getText().toLowerCase().contains(strBrandHeader1.toLowerCase())
                            || product.getText().toLowerCase().contains(strBrandHeader2.toLowerCase()));
        }
        log.info("products are showing of applied facets... ");
    }

    @Then("^I should see global navigation menu bar open on browse page$")
    public void iShouldSeetheGlobalMenuBarOpen() throws Throwable {
        Wait.untilElementPresent("search.menu_bar");
        Assert.assertTrue("ERROR - ENV:- Global Menu bar is collapsed or not visible", Elements.elementPresent("search.menu_bar"));
        log.info("Verified that the Global Navigation Menu bar is visible on SRP");
    }

    @And("^I should see the word \"([^\"]*)\" in the global search field$")
    public void iShouldSeeTheWordInTheGlobalSearchField(String keyword) throws Throwable {
        Assert.assertTrue("ERROR - APP: Search keyword is not displayed on global search field", Elements.findElement("home.search_field").getAttribute("value").equals(keyword));
        log.info("Verified search keyword persistence on global search field");
    }

    @Then("^I should be navigated to the corresponding \"([^\"]*)\" in (registry|domestic|iship) mode$")
    public void iShouldBeNavigatedToTheCorresponding(String pageType, String mode) throws Throwable {
        String currenURL = WebDriverManager.getCurrentUrl();
        switch (pageType) {
            case "category":
                shouldBeOnPage("category_browse");
                break;
            case "pdp":
                Thread.sleep(5000);
                if (mode.equalsIgnoreCase("registry")) {
                    shouldBeOnPage("registry_pdp");
                } else {
                    shouldBeOnPage("product_display");
                }
                break;
            case "absolute url":
                Assert.assertTrue("ERROR - ENV: Page is not navigating to relative url", currenURL.contains("customerservice"));
                break;
            case "relative url":
                Assert.assertTrue("ERROR - ENV: Page is not navigating to relative url", currenURL.contains(search_term));
                shouldBeOnPage("category_browse");
                break;
            default:
                Assert.fail("Invalid Option");
                break;
        }
    }

    @Then("^I should see keyword \"([^\"]*)\" on search header$")
    public void iShouldSeeAKeywordOnSearchHeader(String keyword) throws Throwable {
        Assert.assertTrue("ERROR - APP: Search keyword is not displayed on search header label", Elements.findElement("search_result.search_label").getText().contains(keyword));
        log.info("Verified search keyword on search header label");
    }

    @And("^I should see clear all X symbol in the global search field$")
    public void iShouldSeeClearAllXSymbolInTheGlobalSearchField() throws Throwable {
        Assert.assertTrue("ERROR - APP: Clear all X symbol is not displayed on global search field", Elements.elementPresent("search_result.clear_search"));
        log.info("Verified Clear all X symbol on global search field");
    }

    @Then("^I search with a (.*?) search term$")
    public void iEnterSearchTerm(String searchKeyword) throws Throwable {
        JSONObject keywords = getFileDataInJson(getResourceFile("partial_match_keywords.json"));
        i_type_in_mew_search(keywords.get(searchKeyword).toString());
    }

    @And("^I verify result message for mcom (valid|invalid|multiple|complete-invalid) search term$")
    public void iVerifyResultMessageForMcomSearchTerm(String condition) throws Throwable {
        switch (condition.toLowerCase()) {
            case "valid":
                String searchHeader[] = Elements.getText("search_result.search_label").replace("\"", "").split(" in ");
                int totalProductCountOnPage = Integer.parseInt(Elements.findElement("search_result.product_count").getText().replaceAll("\\D+", ""));
                int serviceProductCount = XAPISearchService.getItemCount(search_term, null);
                if (Elements.findElement("search_result.product_count").isDisplayed()) {
                    Assert.assertTrue("ERROR - APP:- Incorrect search key word is displayed on search header", searchHeader[searchHeader.length - 1].equalsIgnoreCase(search_term));
                    Assert.assertEquals("ERROR - APP:- Product count miss match", totalProductCountOnPage, serviceProductCount);
                } else {
                    Assert.fail("ERROR - APP: Search label is not displayed on search results page");
                }
                break;
            case "invalid":
            case "multiple":
                String matchTerm = XAPISearchService.getProductCountWithPartialMatch(search_term).get("matchedTerms");
                int productCount = Integer.parseInt(XAPISearchService.getProductCountWithPartialMatch(search_term).get("itemCount"));
                Assert.assertTrue("ERROR - APP: Incorrect Partial meassage1 is displayed", Elements.findElement("search_result.partial_message1").getText()
                        .equals("We found " + productCount + " results for " + matchTerm));
                Assert.assertTrue("ERROR - APP: Incorrect Partial meassage2 is displayed", Elements.findElement("search_result.partial_message2").getText()
                        .equals("There were 0 matches for " + search_term));
                break;
            case "complete-invalid":
                iShouldBeOnZeroSearchResultsPage();
                iShouldSeeARelatedSearchMessageOnSearchLandingPageForTheKeyword(search_term);
                break;
            default:
                Assert.fail("Invalid option");
        }
    }

    @And("^I verify result message for mcom (valid|invalid|multiple|complete-invalid) search term in registry mode$")
    public void iVerifyResultMessageForMcomSearchTermInRegistryMode(String condition) throws Throwable {
        switch (condition.toLowerCase()) {
            case "valid":
                String searchHeader[] = Elements.getText("search_result.search_label").replace("\"", "").split(" in ");
                int totalProductCountOnPage = Integer.parseInt(Elements.findElement("search_result.product_count").getText().replaceAll("\\D+", ""));
                int serviceProductCount = WSSGSearchService.getItemCount(search_term, "registry",null,null,null);
                if (Elements.findElement("search_result.product_count").isDisplayed()) {
                    Assert.assertTrue("ERROR - APP:- Incorrect search key word is displayed on search header", searchHeader[searchHeader.length - 1].equalsIgnoreCase(search_term));
                    Assert.assertEquals("ERROR - APP:- Product count miss match", totalProductCountOnPage, serviceProductCount);
                } else {
                    Assert.fail("ERROR - APP: Search label is not displayed on search results page");
                }
                break;
            case "invalid":
            case "multiple":
                String matchTerm = WSSGSearchService.getPartialMatchKeywords(search_term, "registry").get("matchedTerms");
                int productCount = WSSGSearchService.getItemCount(search_term, "registry",null,null,null);
                Assert.assertTrue("ERROR - APP: Incorrect Partial meassage1 is displayed", Elements.findElement("search_result.partial_message1").getText()
                        .equals("We found " + productCount + " results for " + matchTerm));
                Assert.assertTrue("ERROR - APP: Incorrect Partial meassage2 is displayed", Elements.findElement("search_result.partial_message2").getText()
                        .equals("There were 0 matches for " + search_term));
                break;
            case "complete-invalid":
                iShouldBeOnZeroSearchResultsPage();
                iShouldSeeARelatedSearchMessageOnSearchLandingPageForTheKeyword(search_term);
                break;
            default:
                Assert.fail("Invalid option");
        }
    }

    @And("^I verify result message for bcom (valid|invalid|multiple|complete-invalid) search term$")
    public void iVerifyResultMessageForBcomSearchTerm(String condition) throws Throwable {
        switch (condition.toLowerCase()) {
            case "valid":
                String searchHeader = Elements.getText("search_result.search_keyword").trim();
                int totalProductCountOnPage = SearchAndBrowseActions.getItemCount();
                int serviceProductCount = WSSGSearchService.getItemCount(search_term, runMode, null, null, null);
                if (Elements.findElement("search_result.product_count").isDisplayed()) {
                    Assert.assertTrue("ERROR - APP:- Incorrect search key word is displayed on search header", searchHeader.equalsIgnoreCase(search_term));
                    Assert.assertEquals("ERROR - APP:- Product count miss match", totalProductCountOnPage, serviceProductCount);
                } else {
                    Assert.fail("ERROR - APP: Search label is not displayed on search results page");
                }
                break;
            case "invalid":
            case "multiple":
                String matchTerm = WSSGSearchService.getProductCountWithPartialMatch(search_term, runMode);
                int productCount = WSSGSearchService.getItemCount(search_term, runMode, null, null, null);
                Assert.assertTrue("ERROR - APP: Incorrect Partial meassage1 is displayed", Elements.findElement("search_result.partial_search_text_section").getText()
                        .equals("No exact match for " + search_term));
                Assert.assertTrue("ERROR - APP: Incorrect Partial meassage2 is displayed", Elements.findElement("search_result.partial_message2").getText()
                        .equals("Showing " + productCount + " results for: " + matchTerm));
                break;
            case "complete-invalid":
                if(macys()){
                    shouldBeOnPage("zero_search_result") ;
                    iShouldSeeARelatedSearchMessageOnSearchLandingPageForTheKeyword(search_term);
                }
                else {
                    shouldBeOnPage("zero_results");
                    iShouldBeOnTheZeroSearchResultsPageWithRelevantMessageDisplayed ();
                   }
                break;
            default:
                Assert.fail("Invalid option");
        }
    }

    @Then("^I should see quick add button for every member product on (search|browse|DLP) page$")
    public void iShouldSeeQuickAddButtonForEveryMemberProductOnSearchPage(String page) throws Throwable {
        Wait.secondsUntilElementPresent("search_result.product_thumbnail", 10);
        Thread.sleep(5000);
        List<WebElement> productThumbnailElements = Elements.findElements("search_result.product_thumbnail");
        for (int i = 0; i < productThumbnailElements.size(); i++) {
            String priceText = Elements.findElements("search_result.product_thumbnail").get(i).findElement(By.className("m-product-grid-prices")).getText();
            if (!priceText.contains("-") && !priceText.contains("Your Choice")) {
                Assert.assertTrue("ERROR - APP: ATB button is not displayed for member product " +
                                Elements.findElements("search_result.product_thumbnail").get(i).getAttribute("data-product_id"),
                        Elements.elementPresent("search_result_product_thumbnails.add_to_bag_button"));
            }
        }
        log.info("Verified ATB button for every member product");
    }

    @When("^I tap on the quick add button on a random product$")
    public void iTapOnTheQuickAddButtonOnARandomProduct() throws Throwable {
        List<WebElement> productThumbnailElements = Elements.findElements("search_result.product_thumbnail");
        int randomProductIndex = new Random().nextInt(productThumbnailElements.size());
        WebElement webElement = productThumbnailElements.get(randomProductIndex - 1);
        quickViewProdId = Integer.parseInt(webElement.getAttribute("data-product_id"));
        if (Elements.elementPresent(Elements.paramElement("search_result.add_to_bag_op2", String.valueOf(randomProductIndex)))) {
            Clicks.javascriptClick(Elements.paramElement("search_result.add_to_bag_op2", String.valueOf(randomProductIndex)));
        } else {
            Clicks.javascriptClick(Elements.paramElement("search_result.add_to_bag_op1", String.valueOf(randomProductIndex)));
        }
        log.info("Selected ATB button for product id " + quickViewProdId);
    }

    @Then("^I should see the quick add overlay on the (search|browse|DLP) page$")
    public void iShouldSeeTheQuickAddOverlayOnTheSearchPage(String page) throws Throwable {
        Assert.assertTrue("ERROR - ENV: Quick add overlay is not loaded after 20 seconds", Wait.secondsUntilElementPresent("search_result.add_to_bag_overlay", 20));
        Assert.assertEquals("ERROR - APP: Expected quick add overlay for product id is " + quickViewProdId + "but actual opened quick add overlay is " + Integer.parseInt(Elements.getElementAttribute("search_result.add_to_bag_overlay", "data-id")),
                quickViewProdId, Integer.parseInt(Elements.getElementAttribute("search_result.add_to_bag_overlay", "data-id")));
        log.info("Verified quick add overlay");
    }

    @And("^I should see the quick add overlay with all elements as per the response on (domestic|iship|registry) mode$")
    public void iShouldSeeTheQuickAddOverlayWithAllElementsAsPerTheResponse(String mode) throws Throwable {
        Assert.assertEquals("ERROR - APP: Incorrect product name is displaying on quick add overlay",
                Elements.getText(By.className("m-title")).toLowerCase().replaceAll(" ", ""), QuickAddProductService.productName(quickViewProdId, mode).toLowerCase().replaceAll(" ", ""));
        Assert.assertTrue("ERROR - APP: Incorrect product price is displaying on quick add overlay",
                QuickAddProductService.productMinMaxPrice(quickViewProdId, mode).contains(Double.parseDouble(Elements.getText("product_display.quick_add_price").replaceAll("[a-zA-Z]|[$, ]", ""))));
        if (Elements.elementPresent("product_display.quick_add_color_swatches")) {
            Assert.assertEquals("ERROR - APP: Incorrect product sizes are displaying on quick add overlay",
                    Elements.findElements("product_display.quick_add_color_swatches").size() / 2, QuickAddProductService.productColors(quickViewProdId, mode).size());
        }
        if (Elements.elementPresent("product_display.quick_add_color_sizes")) {
            Assert.assertEquals("ERROR - APP: Incorrect product colors are displaying on quick add overlay",
                    Elements.findElements("product_display.quick_add_color_sizes").size() / 2, QuickAddProductService.productSizes(quickViewProdId, mode).size());
        }
        Assert.assertTrue("ERROR - ENV: ATB button is not displaying on quick add overlay",
                Elements.elementPresent("product_display.add_to_bag_button"));
        Assert.assertTrue("ERROR - ENV: See product details link is not displaying on quick add overlay",
                Elements.elementPresent("product_display.see_product_details_link"));
        log.info("Verified all quick add overlay elements as per response");
    }

    @And("^I tap on add to bag button in quick add overlay$")
    public void iTapOnAddToBagButtonInQuickAddOverlay() throws Throwable {
        if (Elements.elementPresent("product_display.select_default_size")) {
            Clicks.click("product_display.select_default_size");
        }
        Clicks.clickElementByText("product_display.add_to_bag_button", "add to bag");
        log.info("Selected ATB button on quick add overlay");
    }

    @Then("^I should not see the quick add overlay on the browse page$")
    public void iShouldNotSeeTheQuickAddOverlayOnTheBrowsePage() throws Throwable {
        Assert.assertTrue("ERROR - APP: Quick add overlay is displaying after adding product to bag from quick add",
                Wait.secondsUntilElementNotPresent("search_result.add_to_bag_overlay", 10));
        log.info("Verified quick add overlay is closing after adding product bag from quick add overlay");
    }

    @When("^I tap on see product details link$")
    public void iTapOnSeeProductDetailsLink() throws Throwable {
        Clicks.clickElementByText("product_display.see_product_details_link", "See Product Details");
        log.info("Selected See product details link on quick add overlay");
    }

    @And("^I should see product is added to bag$")
    public void iShouldSeeProductIsAddedToBag() throws Throwable {
        int bagCount;
        if (Elements.elementPresent("search_result.bag_count")) {
            bagCount = Integer.parseInt(Elements.getText("search_result.bag_count"));
        } else {
            bagCount = Integer.parseInt(Elements.getElementAttribute("search_result.my_bag", "data-bagcount"));
        }
        Assert.assertTrue("ERROR - APP: Product is not added to bag after adding it from quick add overlay", bagCount == 1);
        log.info("Verified bag count after adding product to bag from  quick add overlay");
    }

    @Then("^I should see all products displayed in browse page$")
    public void iShouldSeeAllProductsDisplayedInBrowsePage() throws Throwable {
        Browse browse = new Browse();
        Integer catId = Integer.parseInt(browse.getCategoryId());
        List<Integer> expectedProductIds = XAPIBrowseService.getProductIds(catId, null);
        List<Integer> actualProductIds = SearchAndBrowseActions.getProductIds();
        Assert.assertEquals("ERROR - APP: Products are not dispplaying as per the service", expectedProductIds, actualProductIds);
        log.info("Verified products displayed in order as obtained response from the service on search page");
    }

    @Then("^I should go to respective \"([^\"]*)\"$")
    public void iShouldGoToRespectiveUrl(String expectedURL) throws Throwable {
        iShouldBeOnTheSearchResultsPage(null);
        Assert.assertTrue("ERROR APP: Incorrect registry search navigation", WebDriverManager.getCurrentUrl().toLowerCase().contains(expectedURL));
        log.info("Verified registry search navigation URL");
    }

    @When("^I directly navigate to registry rewards enroll page$")
    public void iDirectlyNavigateToRegistryRewardsEnrollPage() throws Throwable {
        Navigate.visit(RunConfig.url + "/registry/wedding/registryrsrenroll");
        log.info("Navigated to registry rewards enroll page");
    }

    @When("^I directly navigate to registry sign in page$")
    public void iDirectlyNavigateToRegistrySignInPage() throws Throwable {
        Navigate.visit(RunConfig.url + "/registry/wedding/regsignin");
        log.info("Navigated to registry sign in page");
    }

    @Then("^I should see products sorted by \"([^\"]*)\" on wedding (search|browse) page$")
    public void iShouldSeeProductsSortedByOnWeddingSearchPage(String sortBy, String page) throws Throwable {
        if (page.equalsIgnoreCase("search")) {
            Assert.assertEquals("ERROR - APP: prouduts are not sorted by " + sortBy,
                    WSSGSearchService.getProductIds(search_term, "registry", sortBy, null, null), SearchAndBrowseActions.getProductIds());
        } else if (page.equalsIgnoreCase("browse")) {
            Assert.assertEquals("ERROR - APP: prouduts are not sorted by " + sortBy,
                    WSSGBrowseService.getProductIds(Integer.parseInt(new Browse().getCategoryId()), "registry", sortBy, null, null), SearchAndBrowseActions.getProductIds());
        }
        log.info("products are sorted correctly as per the response");
    }

    @Then("^I should see all products displayed in on registry search results page$")
    public void iShouldSeeAllProductsDisplayedInOnRegistrySearchResultsPage() throws Throwable {
        List<Integer> expectedProductIds = WSSGSearchService.getProductIds(search_term, "registry", null, String.valueOf(Pagination.currentPageNumber()), null);
        List<Integer> actualProductIds = SearchAndBrowseActions.getProductIds();
        Assert.assertEquals("ERROR - APP: Products are not dispplaying as per the service", expectedProductIds, actualProductIds);
        log.info("Verified products displayed in order as obtained response from the service on search page");
    }

    @Then("^I should see all products displayed in registry browse page$")
    public void iShouldSeeAllProductsDisplayedInRegistryBrowsePage() throws Throwable {
        Integer catId = Integer.parseInt(new Browse().getCategoryId());
        List<Integer> expectedProductIds = WSSGBrowseService.getProductIds(catId, "registry", null, String.valueOf(Pagination.currentPageNumber()), null);
        List<Integer> actualProductIds = SearchAndBrowseActions.getProductIds();
        Assert.assertEquals("ERROR - APP: Products are not dispplaying as per the service", expectedProductIds, actualProductIds);
        log.info("Verified products displayed in order as obtained response from the service on search page");
    }

    @Then("^I verify search page data is displayed correctly for \"([^\"]*)\" keyword$")
    public void i_verify_search_page_data_is_displayed_correctly_for_category(String keyword) throws Throwable {
        Wait.until(() -> onPage("search_result"), 10);
        shouldBeOnPage("search_result");
        List<Integer> productIdsFromService = WSSGSearchService.getProductIds(keyword, runMode, null, null, null);
        Assert.assertTrue("ERROR - APP :: No products returned by service", !productIdsFromService.isEmpty());
        Integer expectedProductCount = productIdsFromService.size();
        Integer totalProductCount = WSSGSearchService.getTotalProductCountFromService(keyword, runMode, null, null, null);
        log.info(totalProductCount + " :: count returned by service");
        if (totalProductCount > 90) {
            Integer actualTotalProductCount = Integer.parseInt(Elements.getText("search_result.total_products").split("of")[1].replaceAll("\\D+", ""));
            Assert.assertEquals("ERROR - DATA :: Products count is not appropriate for the category " + keyword, actualTotalProductCount, totalProductCount);
            Integer actualProductCount = Integer.parseInt(Elements.getText("search_result.total_products").split("of")[0].replace("1-", "").trim());
            Assert.assertEquals("ERROR - DATA :: Products count is not appropriate for the category " + keyword, expectedProductCount, actualProductCount);
        } else {
            Integer actualProductList = Integer.parseInt(Elements.getText("search_result.total_products").split(" ")[0].replaceAll("\\D+", "").trim());
            Assert.assertEquals("ERROR - DATA :: Products count is not appropriate for the category " + keyword, expectedProductCount, actualProductList);
        }
    }

    @And("^I navigate to a random PDP page on browse page$")
    public void iNavigateToARandomPDPPageOnBrowsePage() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("search_result.result_list");
        Clicks.randomJavascriptClick("search_result.result_list");
        log.info("Navigated to random PDP page");
    }

    @When("^I click on back button on PDP page and navigate to browse page$")
    public void iClickOnBackButtonOnPDPPageAndNavigateToBrowsePage() throws Throwable {
        WebDriverManager.getWebDriver().navigate().back();
        shouldBeOnPage("category_browse");
        log.info("Verified page is search results after clicking back button on PDP page");
    }

    @Then("^I should see the following \"([^\"]*)\" in page url$")
    public void verifyShopTopCategories(String menuItem) throws Throwable {
        String currentURL = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("shop level category isn't being displayed", currentURL.contains(menuItem));
        log.info("shop level category is being displayed...");
    }

    @And("^I type alphabet \"([^\"]*)\" in brand index search field$")
    public void iTypeAlphabetInBrandIndexSearchField(String keyword) throws Throwable {
        Wait.untilElementPresent("brand_index.verify_page");
        Clicks.click("brand_index.verify_page");
        Assert.assertTrue("Error - App: Cancel button is not displayed on brand search after clicking on it", Elements.elementPresent("brand_index.cancel_button"));
        Elements.findElement("brand_index.verify_page").sendKeys(keyword);
        Assert.assertTrue("Error - App: Clear(X) icon is not displayed on brand search after entering text on it", Elements.elementPresent("brand_index.clear_icon"));
        log.info("Entered alphabet in brand search");
    }

    @Then("^I should see brands should be refined based on alphabet \"([^\"]*)\"$")
    public void iShouldSeeBrandsShouldBeRefinedBasedOnAlphabet(String searchAlphabet) throws Throwable {
        List<String> brands = Elements.findElements("brand_index.brand_search_results").stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (String brand : brands) {
            Assert.assertTrue("", brand.toLowerCase().startsWith(searchAlphabet.toLowerCase()));
        }
        log.info("Brands are refined based on alphabet entered on brand search page");
    }

    @And("^I verify the basic attributes of browse page$")
    public void iVerifyTheBasicAttributesOfBrowsePage() throws Throwable {
        Assert.assertTrue("ERROR - APP:- Browse header label is not displayed", Elements.elementPresent("category_browse.browse_cat_name"));
        Assert.assertTrue("Error - App:- Footer Section not displayed on browse pages", Elements.elementPresent("footer.footer"));
        pausePageHangWatchDog();
        for (String link : SearchAndBrowseActions.getAllProductImages()) {
            Assert.assertTrue("ERROR - ENV : Product images are not loaded on browse page", CommonUtils.verifyresponseCode(link));
        }
        resumePageHangWatchDog();
    }

    @And("^I should see all product images loaded properly$")
    public void iShouldSeeAllProductImagesLoadedProperly() throws Throwable {
        pausePageHangWatchDog();
        Assert.assertFalse("Error - App: Product thumbnail grid is not loaded", SearchAndBrowseActions.getAllProductImages().isEmpty());
        for (String link : SearchAndBrowseActions.getAllProductImages()) {
            Assert.assertTrue("ERROR - ENV : Product images are not loaded on browse page", CommonUtils.verifyresponseCode(link));
        }
        resumePageHangWatchDog();
        log.info("Verified all product thumbnail images with 200 response");
    }

    @And("^I should see correct number of pages in pagination on registry (browse|search) page$")
    public void iShouldSeeCorrectNumberOfPagesInPaginationOnRegistryBrowsePage(String page) throws Throwable {
        Integer productCount = page.equalsIgnoreCase("browse") ? WSSGBrowseService.getItemCount(Integer.valueOf(new Browse().getCategoryId()), "registry", null, null, null) :
                WSSGSearchService.getItemCount(search_term, "registry", null, null, null);
        int expectedPageCount = (productCount % 48 > 0 ? productCount / 48 + 1 : productCount / 48);
        Assert.assertTrue("ERROR APP: Page count is miss matched", Pagination.pageCount() == expectedPageCount);
        log.info("Verified page count on search page");
    }

    @When("^I click on back button on PDP page and navigate back to DLP page$")
    public void iClickOnBackButtonOnPDPPageAndNavigateToDLPPage() throws Throwable {
        Navigate.browserBack();
        shouldBeOnPage("dynamic_landing");
        log.info("Verified page is DLP after clicking back button on PDP page");
    }

    @And("^I should see Heart Icon for every member product on browse page$")
    public void iShouldSeeHeartIconForEveryMemberProductOnBrowsePage() throws Throwable {
        Wait.secondsUntilElementPresent("search_result.product_thumbnail", 7);
        List<WebElement> products = Elements.findElements("search_result.product_thumbnail");
        for (int i = 0; i < products.size(); i++) {
            String selector = Elements.elementPresent(By.className("m-search-prices")) ? "m-search-prices" : "m-browse-prices";
            String priceText = Elements.findElements("search_result.product_thumbnail").get(i).findElement(By.className(selector)).getText();
            if (!priceText.contains("-") && !priceText.contains("Your Choice")) {
                Assert.assertTrue("ERROR - APP: Heart Icon(Add to List) is not displayed for member product " + Elements.findElements("search_result.product_thumbnail").get(i).getAttribute("data-product_id"),
                        Elements.elementPresent(By.className("m-list-heart-product")));
            }
        }
    }

    @And("^I click on Heart Icon on product to add to list on browse page$")
    public void iClickOnHeartIconOnProductToAddToListOnBrowsePage() throws Throwable {
        Clicks.click("search_result.add_to_list_btn_empty");
        log.info("Successfully clicking on Add to list heart icon");
    }

    @Then("^I should see my Heart painted on browse page$")
    public void iShouldSeeMyHeartPaintedOnBrowsePage() throws Throwable {
        Assert.assertTrue("ERROR - APP: Add to list is not working", Wait.secondsUntilElementPresent("search_result.add_to_list_btn_filled", 10));
        Assert.assertTrue("ERROR - APP: Added to list msz is not displayed after tapping on heart symbol", Elements.getText("search_result.add_to_list_conformation").equals("Added to list"));
        log.info("Product is added to list Successfully");
    }

    @When("^I click on Heart Icon to remove product from list on browse page$")
    public void iClickOnHeartIconToRemoveProductFromListOnBrowsePage() throws Throwable {
        Clicks.click("search_result.add_to_list_btn_filled");
        Assert.assertTrue("ERROR - APP: Remove from list is not working", Wait.secondsUntilElementNotPresent("search_result.add_to_list_btn_filled", 10));
        Assert.assertTrue("ERROR - APP: Remove from list msz is not displayed after tapping on selected heart symbol", Elements.getText("search_result.add_to_list_conformation").equals("Removed from list"));
        log.info("Product is removed from list Successfully");
    }

    @Then("^I should see selected wish list is persisted$")
    public void iShouldSeeSelectedWishListIsPersisted() throws Throwable {
        Assert.assertTrue("ERROR - APP: Selected wish list is not persisted after toggle the views",
                Wait.secondsUntilElementPresent("search_result.add_to_list_btn_filled", 10));
    }

    @And("^I should see price displayed in \"([^\"]*)\" currency$")
    public void iShouldSeePriceDisplayedInCurrency(String currency) throws Throwable {
        List<WebElement> products = Elements.findElements("search_result.product_thumbnail");
        for (WebElement product : products) {
            String selector = macys() ? "m-search-prices" : "b-price";
            Assert.assertTrue("Error - Env: Price is not displayed in '" + currency + "' currency", product.findElement(By.className(selector)).getText().contains("AUD"));
        }
    }

    @And("^I should see google ads in (browse|search|cat splash) page$")
    public void iShouldSeeGoogleAdsInBrowsePage(String page) throws Throwable {
        Assert.assertTrue("Error - App: Google Ads are not displayed on " +page +" page", Wait.untilElementPresent("search_result.google_adsense"));
        log.info("Verified google ads on " + page + " page");
    }

    @Then("^I (should|should not) see back to top button$")
    public void iShouldSeeBackToTopButton(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should"))
            Assert.assertTrue("",Elements.elementPresent("search_result.back_to_top"));
        else
            Assert.assertFalse("", Elements.elementPresent("search_result.back_to_top"));
        log.info("Verified back to top button");
    }

    @And("^I tap on back to top button$")
    public void iTapOnBackToTopButton() throws Throwable {
        Clicks.clickWhenPresent("search_result.back_to_top");
        log.info("Tapped on back to top button");
    }

    @Then("^I should navigate to top of the page$")
    public void iShouldNavigateToTopOfThePage() throws Throwable {
        Long windowScrollPosition = (Long) Navigate.execJavascript("return window.pageYOffset;");
        Assert.assertEquals(new Long(0), windowScrollPosition);
        log.info("Verified page navigating to top of the page after tapping on back to top button");
    }
}
