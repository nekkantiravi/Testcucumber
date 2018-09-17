package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategoryBrowse;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ProductThumbnail;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.RecentlyViewed;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.shared.utils.CommonUtils.selectRandomProduct;

public class BrowsePageSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(BrowsePageSteps.class);
    private String popularSearchChoice = "";
    private String expectedText = "";
    private WebElement recentProduct;
    private CategoryBrowse categoryBrowse = new CategoryBrowse();
    private int attemptCount = 0;
    private JSONArray oldProductDetails = null;

    @When("^I navigate to random category browse page$")
    public void iNavigateToRandomBrowsePage() throws Throwable {
        Home.selectRandomSubCategory();
        logger.info("Navigated to random category browse page");
    }

    @Then("^I should be in category browse page$")
    public void iShouldBeInCategoryBrowsePage() throws Throwable {
        shouldBeOnPage("category_browse");
    }

    @Then("^I verify the display of category splash page$")
    public void iVerifyTheDisplayOfCategorySplashPage() throws Throwable {
        List<WebElement> adRows = Elements.findElements("category_splash.rows");
        if (adRows.isEmpty()) {
            Assert.fail("Media missing on category splash page!");
        }
        adRows.forEach(el -> {
            if (!el.isDisplayed()) {
                Assert.fail("A row on category splash page is not displayed");
            }
        });
    }

    @Then("^I verify the basic attributes of gift card category splash page$")
    public void iVerifyTheBasicAttributesOfGiftCardCategorySplashPage() throws Throwable {
        Elements.elementShouldBePresent("category_splash.subcategory");
        Assert.assertTrue(title().contains("Gift Cards"));
    }

    @Then("^I should be redirected to the \"(.*?)\" page from popular searches$")
    public void iShouldBeRedirectedToThePageFromPopularSearches(String option) throws EnvException {
        String errorMessage = "ERROR - ENV: Not navigated to " + option + " page from popular searches";
        if (option.equals("bold size")) {
            //Updated old condition if (!url().contains(popularSearchChoice)) to verify with ignore cases.
            if (!StringUtils.containsIgnoreCase(url(),popularSearchChoice)) {
                throw new EnvException(errorMessage);
            }
        } else {
            if (!url().contains(option)) {
                throw new EnvException(errorMessage);
            }
        }
    }

    @Then("^I should see facets listed on left nav$")
    public void iShouldSeeFacetsListedOnLeftNav() throws EnvException {
        Elements.elementShouldBePresent("left_facet.facets_panel");
    }

    @And("^I verify the page for keyword which has \"([^\"]*)\" from popular searches$")
    public void iVerifyThePageForKeywordWhichHasFromPopularSearches(final String option) throws Throwable {
        List<WebElement> elements = Elements.findElements(Elements.element("category_splash.popular_searches"));
        if (option.equals("bold size")) {
            elements = elements.stream()
                    .filter(el -> el.getAttribute("class").equalsIgnoreCase("sizeLarge"))
                    .collect(Collectors.toList());
        } else {
            elements = elements.stream()
                    .filter(el -> el.getAttribute("href").toLowerCase().contains(option.toLowerCase()))
                    .collect(Collectors.toList());
        }
        WebElement choice = elements.get(new Random().nextInt(elements.size() - 1));
        popularSearchChoice = choice.getText();
        if (popularSearchChoice.contains("'")) {
            List<String> split = Arrays.asList(popularSearchChoice.split(" "));
            if (split.size() == 1) {
                popularSearchChoice = popularSearchChoice.replace("'", "");
            } else {
                popularSearchChoice = split.stream()
                        .filter(s -> !s.contains("'"))
                        .collect(Collectors.toList()).get(0);
            }
        }
        popularSearchChoice = popularSearchChoice.replace(" ", "-");
        Clicks.click(choice);
        iShouldBeRedirectedToThePageFromPopularSearches(option);
        iShouldSeeFacetsListedOnLeftNav();
    }

    @When("^I navigate to a product having \"([^\"]*)\" attributes(?: that is not(?: an?)? \"(.*?)\")?$")
    public void iNavigateToAProductHavingAttributes(String productTrue, String productFalse) throws Throwable {
        CommonUtils.navigateToRandomProduct(productTrue, productFalse);
    }

    @Then("^I verify all attributes of PDP$")
    public void iVerifyAllAttributesOfPDP() throws Throwable {
        ProductDisplay.verifyPdpAttributes();
    }

    @When("^I navigate to ((?i)Browse|Sub Splash|Category Splash) page in ((?i)SITE|REGISTRY|ISHIP) mode$")
    public void iNavigateToPageInMode(String page_type, String mode) throws Throwable {
        if (mode.equalsIgnoreCase("ISHIP")) {
            com.macys.sdt.shared.steps.website.ShopAndBrowse browse = new com.macys.sdt.shared.steps.website.ShopAndBrowse();
            browse.I_change_country_to("Australia");
        }
        if (mode.equalsIgnoreCase("REGISTRY")) {
            Clicks.click("home.goto_wedding_registry");
        }
        if (page_type.equalsIgnoreCase("browse") || page_type.equalsIgnoreCase("sub splash")) {
            Home.selectRandomSubCategory();
        } else {
            Home.selectRandomCategory();
        }
    }

    @Then("^I should see dynamic top navigation in \"(domestic|iship|registry)\" mode$")
    public void iShouldSeeDynamicTopNavigationInMode(String mode) throws Throwable {
        String errorMessage = "Expected categories did not match visible categories";
        Elements.elementShouldBePresent("home.category");
        Home homePage = new Home();
        List<String> categories = homePage.getAllMainCategoryNames();
        List<String> expected = homePage.getExpectedMainCategories(mode);
        Assert.assertTrue(errorMessage, categories.size() == expected.size());
        Collections.sort(categories);
        Collections.sort(expected);
        for (int i = 0; i < categories.size(); i++) {
            Assert.assertTrue(errorMessage, categories.get(i).equals(expected.get(i)));
        }
    }

    @Then("^I should see product display order modified$")
    public void iShouldSeeProductDisplayOrderModified() throws Throwable {
        // If this element reference is stale, page has been updated.
        // This implies that the order was modified.
        try {
            if (!recentProduct.isDisplayed()) {
                throw new Exception("Good to go");
            }
            Clicks.click(recentProduct);
            Assert.fail("Order has not been modified!");
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            System.out.println("Order has been modified.");
        }
    }

    @And("^I save product order$")
    public void iSaveProductOrder() throws Throwable {
        if (onPage("category_browse", "search_result", "dynamic_landing")) {
            recentProduct = Elements.findElement("search_result.product_thumbnail_link");
        } else if (onPage("product_display")) {
            recentProduct = Elements.findElement("recommendations.recommended_products");
        } else {
            Assert.fail("not on a page with products to select");
        }
    }

    @When("^I navigate to a category browse page having more than 100 products$")
    public void iNavigateToACategoryBrowsePageHavingMoreThanProducts() throws Throwable {
        if (prodEnv()) {
            // use known category
            Navigate.visit(RunConfig.url + "/shop/?id=" + (macys() ? "20640" : "11536"));
        } else {
            CommonUtils.retryAction(() -> {
                try {
                    Home.selectRandomCategory();
                    Home.selectRandomSubCategory();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int count = Integer.parseInt(Elements.getText("category_browse.product_count"));
                return count >= 100;
            }, 0, "Could not find category browse page with >100 items");
        }
    }

    @Then("^I should see items per page choices$")
    public void iShouldSeeItemsPerPageChoices() throws Throwable {
        Elements.elementShouldBePresent("pagination.items_per_page_options");
    }

    @Then("^I verify that top banner should be displayed on cat browse page$")
    public void iVerifyThatTopBannerShouldBeDisplayedOnCatBrowsePage() throws Throwable {
        Elements.elementShouldBePresent("flexible_template.rows_elements");
    }

    @Then("^I verify the functionality of pagination when there are products above default product count in browse page$")
    public void iVerifyTheFunctionalityOfPaginationWhenThereAreProductsAboveDefaultProductCountInBrowsePage() throws Throwable {
        //adding condition for bloomingdales
        if(bloomingdales()){
            Elements.elementShouldBePresent("pagination.top_pagination");
            Elements.elementShouldBePresent("pagination.bottom_pagination");
        }else {
            Elements.elementShouldBePresent("pagination.bottom_pagination_section");
        }
    }

    @When("^I navigate to a random page other than first and last page from pagination$")
    public void iNavigateToARandomPageOtherThanFirstAndLastPageFromPagination() throws Throwable {
        //adding code for bloomingdales
        oldProductDetails = ProductThumbnail.getProductThumbnailDetails();
        if(bloomingdales()){
            // selector excludes current page, want to also avoid the last page, hence the ( - 1)
            List<WebElement> pages = Elements.findElements(Elements.element("pagination.pages_list"));
            //Assert if browse page contains only 2 pages
            Assert.assertFalse("Browse page contains only 2 pages",pages.size()==1);
            DropDowns.selectCustomValue("pagination.all_pages","pagination.pages_list",new Random().nextInt(pages.size()-1));
        } else {
            List<WebElement> pages = Elements.findElements(Elements.element("pagination_top.goto_each_number_link"));
            pages = pages.stream()
                    .filter(el -> !el.getText().isEmpty())
                    .collect(Collectors.toList());
            pages.remove(0);
            pages.remove(pages.size() - 1);
            // if we're on a middle page we will get "1 ... 4 ...10" where 4 is current and 10 is last
            // we want to navigate using the arrows in this case to avoid the first / last pages if possible
            if (pages.size() == 1 && pages.get(0).getText().equals("1")) {
                Clicks.click("pagination.goto_previous_page_via_arrow");
            } else
            // selector excludes current page, want to also avoid the last page, hence the ( - 1)
            {
                Clicks.click(pages.get(new Random().nextInt(pages.size() - 1)));
            }
        }
        Thread.sleep(15000);
        Wait.ajaxDone();
        Wait.forPageReady();
        attemptCount = attemptCount + 1;
        if(attemptCount < 3 && oldProductDetails.toString().equals(ProductThumbnail.getProductThumbnailDetails().toString())){
            Navigate.browserRefresh();
            Wait.forPageReady();
            iNavigateToARandomPageOtherThanFirstAndLastPageFromPagination();
        }
        attemptCount = 0;
    }

    @When("^I navigate to (first|last) page from pagination$")
    public void iNavigateToFirstPageFromPagination(String position) throws Throwable {
        oldProductDetails = ProductThumbnail.getProductThumbnailDetails();
        if(bloomingdales()){
            List<WebElement> pages = Elements.findElements(Elements.element("pagination.pages_list"));
            if(position.equals("first")) {
                Wait.forLoading("pagination.pages_list");
                DropDowns.selectCustomValue("pagination.all_pages","pagination.pages_list",0);
            }
            else {
                Wait.forLoading("pagination.pages_list");
                DropDowns.selectCustomValue("pagination.all_pages", "pagination.pages_list", pages.size() - 1);
            }
        }else {
            List<WebElement> pages = Elements.findElements(Elements.element("pagination_top.goto_each_number_link"));
            if (position.equals("first")) {
                pages = pages.stream()
                        .filter(el -> el.getText().equals("1"))
                        .collect(Collectors.toList());
                // if size is 0, we are already on the first page as the selector excludes the current page
                if (pages.size() != 0) {
                    Clicks.click(pages.get(0));
                }
            } else {
                pages = pages.stream()
                        .filter(el -> !el.getText().isEmpty())
                        .collect(Collectors.toList());
                Clicks.click(pages.get(pages.size() - 1));
            }
        }
        Thread.sleep(15000);
        Wait.ajaxDone();
        Wait.forPageReady();
        attemptCount = attemptCount + 1;
        if(attemptCount < 3 && oldProductDetails.toString().equals(ProductThumbnail.getProductThumbnailDetails().toString())){
            Navigate.browserRefresh();
            Wait.forPageReady();
            iNavigateToFirstPageFromPagination(position);
        }
        attemptCount = 0;
    }

    @Then("^I verify the site tag value in page source$")
    public void iVerifyTheSiteTagValueInPageSource() throws Throwable {
        Assert.assertTrue("Not hitting dallas servers!", EnvironmentDetails.getSite().contains("DAL"));
    }

    @And("^I select any CHANEL subcategory$")
    public void iSelectAnyCHANELSubcategory() throws Throwable {
        Clicks.clickRandomElement(Elements.element("chanel_splash.category"));
    }

    @Then("^I verify the display and functionality of the chanel PDP$")
    public void iVerifyTheDisplayAndFunctionalityOfTheChanelPDP() throws Throwable {
        if (Elements.anyPresent(Elements.element("chanel_category.subcategory"))) {
            Clicks.clickRandomElement(Elements.element("chanel_category.subcategory"));
        }
        WebElement productElement = Elements.findElements(By.className("productThumbnailLink")).stream().findFirst().orElse(null);
        Assert.assertFalse("ERROR - DATA: Products are not avilable on chanel page", productElement == null);
        Clicks.click(productElement);
        Assert.assertFalse("Auto zoom enabled on chanel pdp!", Elements.elementPresent("product_display.zoomed_image"));
        Clicks.hoverForSelection(Elements.element("product_display.main_image"));
        Elements.elementShouldBePresent("product_display.zoomed_image");
    }

    @And("^I (should|should not) see pagination in search results page$")
    public void iSeePaginationInSearchResultsPage(String should) throws Throwable {
        if (should.equals("should")) {
            Elements.elementShouldBePresent("pagination.top_pagination_section");
        } else if (Elements.elementPresent("pagination.top_pagination_section")) {
            Assert.fail("Pagination visible when it should not be!");
        }
    }

    @And("^I should see prices, ratings & reviews under product thumbnail$")
    public void iShouldSeePricesRatingsReviewsUnderProductThumbnail() throws Throwable {
        WebElement product = Elements.getRandomElement(Elements.element("search_result.product_thumbnail_wrapper"));
        try {
            WebElement price = product.findElement(By.className("prices"));
            WebElement rating = product.findElement(By.className("pdpreviews"));
            if (!(price.isDisplayed() && rating.isDisplayed())) {
                throw new Exception();
            }
        } catch (Exception e) {
            Assert.fail("Missing prices or rating under product thumbnail: " + e);
        }
    }

    @And("^I should see (\\d+) product thumbnails per row$")
    public void iShouldSeeProductThumbnailsPerRow(int num) throws Throwable {
        int columns = ProductThumbnail.productThumbnailColumns();
        if (columns != num) {
            Assert.fail("Expected " + num + " columns, found " + columns);
        }
    }

    @Then("^I verify all basic attributes of the Designer page:$")
    public void iVerifyAllBasicAttributesOfTheDesignerPage(DataTable table) throws Throwable {
        for (DataTableRow row : table.getGherkinRows()) {
            String option = row.getCells().get(0);
            switch (option.toLowerCase()) {
                case "all designers banner":
                    Elements.elementShouldBePresent("designer_static.all_designers_banner");
                    break;
                case "a-z index":
                    String[] index_list = Elements.findElement(Elements.element("designer_static.brand_index_container")).getText().split(" ");
                    String error = "Missing brand index: ";
                    for (int i = 0; i < 26; i++) {
                        String letter = String.valueOf((char) ('A' + i));
                        Assert.assertTrue(error + letter, index_list[i].equals(letter));
                    }
                    break;
                case "alphabetical order under each brand section":
                    List<WebElement> brandBoxes = Elements.findElements(Elements.element("designer_static.brand_box"));
                    brandBoxes.forEach(brandBox -> {
                        List<String> brandNames = brandBox.findElements(By.cssSelector("ul>li>a")).stream()
                                .map(WebElement::getText)
                                .collect(Collectors.toList());
                        List<String> sortedBrands = new ArrayList<>(brandNames);
                        Collections.sort(sortedBrands);
                        for (int i = 0; i < sortedBrands.size(); i++) {
                            Assert.assertTrue("Brands not sorted correctly", brandNames.get(i).equals(sortedBrands.get(i)));
                        }
                    });
                    break;
                case "back to top link under each brand section":
                    brandBoxes = Elements.findElements(Elements.element("designer_static.brand_box"));
                    brandBoxes.forEach(brandBox ->
                            Elements.elementShouldBePresent(brandBox.findElement(By.cssSelector("a.backToTopLink"))));
                    break;
                case "all brand links in left nav panel":
                    List<String> menuItems;
                    if (macys()) {
                        menuItems = Arrays.asList("All Brands", "Athletic Shoes", "Beauty",
                                "Bed & Bath", "Dining", "Electronics", "Handbags & Accessories", "Home", "Jewelry", "Juniors",
                                "Kids", "Kitchen", "Lingerie", "Luggage & Backpacks", "Mattresses", "Men's", "Men's Shoes", "Petite", "Plus Sizes",
                                "Sunglasses", "Watches", "Womens", "Women's Shoes");
                    } else {
                        menuItems = Arrays.asList("WOMEN", "SHOES", "HANDBAGS", "JEWELRY & ACCESSORIES", "BEAUTY", "MEN", "KIDS", "HOME", "ALL DESIGNERS");
                    }
                    List<String> pageMenuItems = Elements.findElements(Elements.element("designer_static.brand_menu_items")).stream()
                            .map(WebElement::getText)
                            .collect(Collectors.toList());
                    for (int i = 0; i < menuItems.size(); i++) {
                        Assert.assertTrue("Brands not sorted correctly", menuItems.get(i).equals(pageMenuItems.get(i)));
                    }
                    break;
            }
        }
    }

    @When("^I select random letter from brand index of the Designer page$")
    public void iSelectRandomLetterFromBrandIndexOfTheDesignerPage() throws Throwable {
        WebElement e = Elements.getRandomElement(Elements.element("designer_static.brand_indexes"));
        expectedText = e.getText();
        Clicks.click(e);
    }

    @Then("^I should see designer page is anchor down to selected letter$")
    public void iShouldSeeDesignerPageIsAnchorDownToSelectedLetter() throws Throwable {
        WebElement expectedBrandHeader = Elements.findElements(Elements.element("designer_static.brand_index_list")).stream()
                .filter(el -> el.getText().equals(expectedText))
                .collect(Collectors.toList()).get(0);
        Assert.assertNotNull("Unable to find expected anchor letter", expectedBrandHeader);

        Assert.assertTrue("Not scrolled to correct brand box", Elements.elementInView(expectedBrandHeader));
    }

    @When("^I select 'Back to Top\" link at selected letter of the Designer page$")
    public void iSelectBackToTopLinkAtSelectedLetterOfTheDesignerPage() throws Throwable {
        WebElement expectedBrandBox = Elements.findElements(Elements.element("designer_static.brand_index_list")).stream()
                .filter(el -> el.getText().equals(expectedText))
                .collect(Collectors.toList()).get(0);
        Assert.assertNotNull("Unable to find expected anchor letter", expectedBrandBox);
        WebElement backToTop = expectedBrandBox.findElement(By.xpath("../span/a"));
        Assert.assertNotNull("Unable to find back to top button", backToTop);
        Clicks.click(backToTop);
    }

    @Then("^I should see Designer page jump back to top anchor$")
    public void iShouldSeeDesignerPageJumpBackToTopAnchor() throws Throwable {
        WebElement expectedBrandBox = Elements.findElements(Elements.element("designer_static.brand_index_list")).get(0);
        Assert.assertTrue("Not scrolled to top of page", Elements.elementInView(expectedBrandBox));
    }

    @When("^I select any brand from Designer page$")
    public void iSelectAnyBrandFromDesignerPage() throws Throwable {
        boolean flag = true;
        WebElement brand = null;
        for(int i = 0; i < 3 && flag; i++){
            if(macys()){
                List<WebElement> brands = Elements.findElements(Elements.element("designer_static.brands")).stream().filter(f-> !f.getAttribute("href").contains("?id=")).collect(Collectors.toList());
                Assert.assertFalse("ERROR - ENV: Brands list not available on page:"+WebDriverManager.getCurrentUrl(), brands.isEmpty());
                int index = brands.size() == 1 ? 0 : new Random().nextInt(brands.size() - 1);
                brand = brands.get(index);
            }else{
                brand = Elements.getRandomElement(Elements.element("designer_static.brands"));
            }
            expectedText = brand.getText();
            Clicks.click(brand);
            Wait.forPageReady();
            if(macys() && WebDriverManager.getCurrentUrl().contains("?id=")){
                Navigate.browserBack();
                shouldBeOnPage("designer_static");
            }else{
                flag = false;
            }
        }
    }

    @Then("^I should navigate to Search Results page$")
    public void iShouldNavigateToSearchResultsPage() throws Throwable {
        shouldBeOnPage("search_result");
    }

    @And("^I should see breadcrumb on Search Results page$")
    public void iShouldSeeBreadcrumbOnSearchResultsPage() throws Throwable {
        Elements.elementShouldBePresent("search_result.product_count_breadcrumb");
    }

    @Then("^I verify quick view functionality for bedding collection products$")
    public void iVerifyQuickViewFunctionalityForBeddingCollectionProducts() throws Throwable {
        Elements.elementShouldBePresent("quick_view.quick_view_product_dialog");
        Elements.elementShouldBePresent("quick_view.master_product_filters");
        Elements.findElements(Elements.element("quick_view.master_product_filters")).forEach(el -> {
            Clicks.click(el);
            String name = el.getText();
            Elements.findElements(Elements.element("quick_view.quick_view_dialog_products")).forEach(product -> {
                if (product.isDisplayed()) {
                    String nameList = product.getAttribute("data-namelist");
                    if (!nameList.contains(name) || nameList.equals("")) {
                        Assert.fail("Item does not match filter value!");
                    }
                }
            });
        });
        Clicks.click("quick_view.quick_view_close_dialog");
    }

    @When("^I navigate to any category browse page showing \"(.*?)\" facet with (less|more) than or equal to (\\d+) facet items$")
    public void iNavigateToAnyCategoryBrowsePageShowingFacetWithMoreThanOrEqualToFacetItems(String facetType, String less, int numFacets) throws Throwable {
        for (int tries = 0; tries < 10; tries++) {
            Home.selectRandomSubCategory();
            try {
                List<WebElement> facetNames = Elements.findElements("left_facet.facet_names");
                Optional<WebElement> facet = facetNames.stream()
                        .filter(el -> el.getText().equalsIgnoreCase(facetType))
                        .findFirst();
                if (!facet.isPresent()) {
                    throw new Exception();
                }
                if (!LeftFacet.isExpanded(facetType)) {
                    LeftFacet.expandFacet(facetType);
                }
                if (macys() && facetType.equals("Brand")) {
                    Clicks.click(By.id("all_brands"));
                }
                List<WebElement> faceValues = Elements.findElement(LeftFacet.getFacetDiv(facetType)).findElements(By.tagName("li")).stream().filter(f-> !f.getText().equals("")).collect(Collectors.toList());
                if (less.equals("less") ? faceValues.size() <= numFacets
                        : faceValues.size() >= numFacets) {
                    break;
                }else{
                    logger.info(facetType+" Facet values with '"+less+"' than '"+numFacets+"' values not found. Attempting for '"+(tries+1)+"' time");
                    throw new Exception();
                }
            } catch (Exception e) {
                Navigate.browserBack();
                Wait.forPageReady();
                logger.info(facetType+" Facet not found on browse page. Attempting for '"+(tries+1)+"' time");
            }
        }
    }

    @Then("^I should see brand facets displayed according to the text (contains|start with) \"(.*?)\" entered in brand search box$")
    public void iShouldSeeBrandFacetsDisplayedAccordingToTheTextContainsEnteredInBrandSearchBox(String option, String searchText) throws Throwable {
        searchText = ShopAndBrowse.brandSearch.equalsIgnoreCase(searchText) ? searchText : ShopAndBrowse.brandSearch.toLowerCase();
        String lowerSearchText = searchText.toLowerCase();
        if (!LeftFacet.isExpanded("Brand")) {
            Assert.fail("Brand facet not visible!");
        }
        boolean contains = option.equals("contains");
        String errorMessage = "Brand facets should " + (contains ? "contain " : "start with ") + "\"" + searchText + "\"";
        List<WebElement> items = Elements.findElements(Elements.paramElement("left_facet.facet_items", "Brand")).stream()
                .filter(el -> !el.getText().isEmpty()).collect(Collectors.toList());
        items.forEach(el -> {
            String facetText = el.getText().toLowerCase();
            Assert.assertTrue(errorMessage, contains ?
                    facetText.contains(lowerSearchText) : facetText.startsWith(lowerSearchText));
        });
    }

    @And("^the (starting character|characters) \"(.*?)\" should be highlighted in the displayed brands under brand facet$")
    public void theCharactersShouldBeHighlightedInTheDisplayedBrandsUnderBrandFacet(String option, String characters) throws Throwable {
        characters = ShopAndBrowse.brandSearch.equalsIgnoreCase(characters) ? characters : ShopAndBrowse.brandSearch.toLowerCase();
        String lowerCharacters = characters.toLowerCase();
        boolean contains = option.equals("characters");
        String errorMessage = "Brand facets should " + (contains ? "contain " : "start with ") + characters;
        Elements.findElements("left_facet.highlighted_brand_chars").forEach(el -> {
            String facetText = el.getText().toLowerCase();
            Assert.assertTrue(errorMessage, contains ? facetText.contains(lowerCharacters) : facetText.startsWith(lowerCharacters));
        });
    }

    @When("^I select any facet name filtered after brand search$")
    public void iSelectAnyFacetNameFilteredAfterBrandTextSearch() throws Throwable {
        try {
            WebElement brand = Elements.getRandomElement("left_facet.highlighted_brand_chars").findElement(By.xpath(".."));
            String itemCountString = brand.findElement(By.xpath("../span[@class='item_count']")).getText();
            LeftFacet.selectedFilter = brand.getText();
            int end = itemCountString.indexOf(")");
            LeftFacet.filterCount = Integer.parseInt(itemCountString.substring(1, end));
            Clicks.click(brand);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("No brands matching search term found");
        }

    }

    @Then("^I verify all results match with the randomly-selected brand facet name$")
    public void iVerifyAllResultsMatchWithTheRandomlySelectedBrandFacetName() throws Throwable {
        Wait.forLoading(By.className("loader-container"));
        for (WebElement el : Elements.findElements("left_facet.facetBreadcrumbs")) {
            System.out.println("Comparing: " + el.getText() + "\nto: " + LeftFacet.selectedFilter);
            if (el.getText().toLowerCase().equalsIgnoreCase(LeftFacet.selectedFilter.toLowerCase())) {
                return;
            }
        }
        Assert.fail("No facet breadcrumb found with text " + LeftFacet.selectedFilter);
    }

    @And("^I verify selected brand facet product count matches with the filtered product count$")
    public void iVerifySelectedBrandFacetProductCountMatchedWithTheFilteredProductCount() throws Throwable {
        String pageItemCountString = Elements.findElement("category_browse.product_count").getText();
        int pageItemCount = Integer.parseInt(pageItemCountString);
        System.out.println("Comparing: " + pageItemCount + "\nto: " + LeftFacet.filterCount);
        Assert.assertTrue("Page item count does not match facet item count!", LeftFacet.filterCount == pageItemCount);
    }

    @Then("^I verify the display of recently viewed panel$")
    public void iVerifyTheDisplayOfRecentlyViewedPanel() throws Throwable {
        if (onPage("radical_pdp")) {
            System.err.println("Radical pdp does not have rvi");
            return;
        }
        Assert.assertTrue("Recently viewed panel is not displayed!", RecentlyViewed.isDisplayed());
        RecentlyViewed.updateProducts();
    }

    @And("^I should see edit option inside Recently Viewed panel$")
    public void iShouldSeeEditOptionInsideRecentlyViewedPanel() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue("Recently viewed options are not displayed!", RecentlyViewed.isEditVisible());
    }

    @Then("^I should move towards (?:previous|next) set of products$")
    public void iShouldMoveTowardsSetOfProducts() throws Throwable {
        List<String> currentProducts = RecentlyViewed.getRecentlyViewed();
        List<String> oldProducts = RecentlyViewed.getOldRecentlyViewed();
        Assert.assertFalse("Recently viewed products panel did not change",
                currentProducts.toString().equals(oldProducts.toString()));
    }

    @And("^I make a bogus \"([^\"]*)\" step$")
    public void iMakeABogusStep(String bogus) throws Throwable {
        System.out.println(bogus);
    }

    @Then("^I verify the functionality of pagination when there are products below default product count in browse page$")
    public void iVerifyTheFunctionalityOfPaginationWhenThereAreProductsBelowDefaultProductCountInBrowsePage() throws Throwable {
        if (macys()) {
            WebElement selectedItemElement = Elements.findElement(By.className("selectedPPP"));
            int productCount = categoryBrowse.getProductCount();
            WebElement topPaginationText = Elements.findElement("pagination_top.show_all_items");
            WebElement bottomPaginationText = Elements.findElement("pagination_bottom.show_all_items");
            Assert.assertTrue("ERROR - APP: 40 or 60 items per page is not selected by default ", selectedItemElement.getText().contains("40") || selectedItemElement.getText().contains("60"));
            logger.info("Verified default show items in category browse page");
            Assert.assertFalse("ERROR - APP: prev link should not be displayed ", Elements.elementPresent(" pagination_top.goto_previous_page_via_link"));
            logger.info("Verified : prev link should not be displayed");
            Assert.assertFalse("ERROR - APP: next link should not be displayed ", Elements.elementPresent(" pagination_top.goto_next_page_via_link"));
            logger.info("Verified : next link should not be displayed");
            Assert.assertTrue("ERROR - APP: Pagination text is incorrect in top position of the page", topPaginationText.getText().contains("Showing All " + productCount + " Items"));
            logger.info("Verified : Text in absence of top pagination section");
            Assert.assertTrue("ERROR - APP: Pagination text is incorrect Bottom position of the page", bottomPaginationText.getText().contains("Showing All " + productCount + " Items"));
            logger.info("Verified : Text in absence of bottom pagination section");
        } else {
            int productCount = categoryBrowse.getProductCount();
            Assert.assertFalse("ERROR - APP: next link should not be displayed ", Elements.elementPresent(" pagination_top.goto_next_page_via_arrow"));
            logger.info("Verified : next link should not be displayed");
            Assert.assertFalse("ERROR - APP: prev link should not be displayed ", Elements.elementPresent(" pagination_top.goto_previous_page_via_arrow"));
            logger.info("Verified : prev link should not be displayed");
            Assert.assertFalse("ERROR - DATA: Pagination section should not be visible ", Elements.elementPresent(" pagination_top.pagination_container") && productCount > 90);
            logger.info("Verified : Absence of pagination section");
        }
        logger.info("Verified : functionality of pagination when there are products below default product count");
    }

    @Then("^I verify left nav links on iship mode brand index page$")
    public void iVerifyLeftNavLinksOnIshipModeBrandIndexPage() throws Throwable {
        if (macys()) {
            List<String> menuItems = Arrays.asList("All Brands", "Bed & Bath", "Dining", "Electronics", "Handbags & Accessories", "Home", "Jewelry", "Juniors",
                    "Kids", "Kitchen", "Lingerie", "Luggage & Backpacks", "Mattresses", "Men's", "Men's Shoes", "Petite", "Plus Sizes",
                    "Watches", "Womens", "Women's Shoes");
            List<String> pageMenuItems = Elements.findElements(Elements.element("designer_static.brand_menu_items")).stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            for (int i = 0; i < menuItems.size(); i++) {
                Assert.assertTrue("Brands not sorted correctly. Actual:"+pageMenuItems.get(i)+". Expected:"+menuItems.get(i), menuItems.get(i).equals(pageMenuItems.get(i)));
            }
        }
    }

    @When("^I scroll \"(?:down|up)\" with \"([^\"]*)\" pixel size on the page$")
    public void iScrollWithPixelSizeOnThePage(String pixelSize) throws Throwable {
        Navigate.scrollPage(0,Integer.parseInt(pixelSize));
        logger.info("Successfully scrolled with"+pixelSize+" pixels on page");
    }

    @Then("^I verify that \"([^\"]*)\" text displayed for pagination$")
    public void iVerifyThatTextDisplayedForPagination(String message) throws Throwable {
        String topPaginationText = Elements.findElement("pagination_top.show_all_items").getText();
        String bottomPaginationText = Elements.findElement("pagination_bottom.show_all_items").getText();
        Assert.assertTrue("ERROR - APP: Default message:'"+message+"' is not displaying in top for lower product pagination panel", topPaginationText.contains("Showing All") && topPaginationText.contains("Items"));
        Assert.assertTrue("ERROR - APP: Default message:'"+message+"' is not displaying in bottom for lower product pagination panel", bottomPaginationText.contains("Showing All") && bottomPaginationText.contains("Items"));
        logger.info("Verified default message for lower product thumbnail grid pagination");
    }
}
