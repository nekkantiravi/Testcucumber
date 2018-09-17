package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.services.Categories;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import com.macys.sdt.shared.actions.MEW.pages.ProductDisplay;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.MEW.panels.MEWLeftFacet;
import com.macys.sdt.shared.actions.MEW.panels.Pagination;
import com.macys.sdt.shared.actions.MEW.panels.RecentlyViewed;
import com.macys.sdt.shared.utils.BrowsePageServices;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.shared.utils.CommonUtils.selectRandomProductMEW;

public class ShopAndBrowse extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ShopAndBrowse.class);
    public static Product recentProduct;

    /**
     * Adds the given product to bag and selects checkout
     *
     * @param avilable_prodcut_id ID of product to add
     * @throws Throwable if any exception occurs
     */
    @When("^I directly add an available and orderable product \"([^\"]*)\" to my bag in mobile site$")
    public void I_directly_add_an_available_and_orderable_product_to_my_bag(String avilable_prodcut_id) throws Throwable {
        CommonUtils.navigateDirectlyToProduct(avilable_prodcut_id);
        I_add_product_to_my_bag_from_standard_PDP_page();

        if (onPage("add_to_bag")) {
            Clicks.click("add_to_bag.checkout");
        } else if (Elements.elementPresent("add_to_bag_dialog.add_to_bag_checkout")) {
            Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
        } else {
            Clicks.click("product_display.member_atb_checkout");
        }

        logger.info("Sucessfuly added product");
    }

    /**
     * Verifies the display of at least one offer on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select the bwallet offer in shopping bag using mobile website$")
    public void I_select_the_bwallet_offer_in_shopping_bag() throws Throwable {
        Wait.forPageReady();
        Clicks.clickIfPresent("shopping_bag.apply_offer");
        int size = Elements.findElements("shopping_bag.bwallet_offers").size();
        boolean displayed = false;
        for (int i = 0; i < size; i++) {
            if (Elements.findElements("shopping_bag.bwallet_offers").get(i).isDisplayed()) {
                Clicks.click(Elements.findElements("shopping_bag.bwallet_offers").get(i));
                displayed = true;
                break;
            }
        }
        if (!displayed) {
            Assert.fail("The offers are not eligible for the products in the shopping bag");
        }
    }


    /**
     * Adds current product to bag from PDP
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add product to my bag from standard PDP page$")
    public void I_add_product_to_my_bag_from_standard_PDP_page() throws Throwable {
        boolean addedToBag = false;
        Assert.assertFalse("ERROR - DATA : Product ( " + (recentProduct == null ? "" : String.valueOf(recentProduct.id)) + " ) is unavailable on product display page!!", !Elements.elementPresent("product_display.add_to_bag_button") && Elements.elementPresent("product_display.availability_error"));
        try {
            int retries = 5;
            pausePageHangWatchDog();
            for (int count = 0; count < retries && !addedToBag; count++) {
                try {
                    com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay.selectRandomColor();
                    com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay.selectRandomSize();
                    Elements.elementInView("product_display.add_to_bag_button");
                    Clicks.click("product_display.add_to_bag_button");

                    if (MEW()) {//check if is MEW
                        Wait.untilElementPresent("add_to_bag.add_to_bag_dialog");

                        if (!Elements.elementPresent("add_to_bag.add_to_bag_dialog")) {
                            Clicks.clickIfPresent("product_display.add_to_bag_button");
                        }
                    } else {//is desktop website
                        Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");

                        if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                            Clicks.clickIfPresent("product_display.add_to_bag_button");
                        }
                    }

                    addedToBag = ProductDisplay.addedToBag();
                    if (RunConfig.debugMode) {
                        logger.info("IsProductAddedToBag:" + addedToBag + ", Add to bag retry count:" + (count + 1));
                    }
                } catch (Exception e) {
                    logger.error("Exception while adding product:" + e.getMessage());
                }
            }

            if (MEW()) {//check if is MEW
                Wait.untilElementPresent("add_to_bag.add_to_bag_dialog");
                if (!Elements.elementPresent("add_to_bag.add_to_bag_dialog")) {
                    Clicks.clickIfPresent("product_display.technical_error");
                }
            } else {//is desktop website
                Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
                if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                    Clicks.clickIfPresent("product_display.technical_error");
                }
            }
            if (isErrorPaneVisible()) {
                Clicks.click("home.popup_close");
            }
            resumePageHangWatchDog();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            logger.error("Error while adding to bag: " + e);
        } finally {
            if (!addedToBag) {
                Wait.untilElementNotPresent("product_display.add_to_bag_button");
                if (macys()) {
                    Assert.assertFalse("ERROR - DATA : Given item is unavailable!!", Elements.elementPresent(By.className("css-tooltip")) && Elements.getText(By.className("css-tooltip")).contains("this item is unavailable"));
                }
                Wait.forPageReady();
                Assert.assertTrue("ERROR - ENV : Unable to add product to bag", ProductDisplay.addedToBag());
            }

        }
    }

    /**
     * Selects a random product of given type and with customer ratings if specified
     *
     * @param prod_type member, master, member_alternate_image or master_alternate_image
     * @param hasRating "with customer ratings" optional
     * @throws Throwable if any exception occurs
     */
    @When("^I select a random (member|master|member_alternate_image|master_alternate_image) product using mobile website(?: with (customer ratings))?$")
    public void I_select_a_random_product_using_mobile_website(String prod_type, String hasRating) throws Throwable {
        boolean found = false;
        int i = 0, max = 20;
        pausePageHangWatchDog();
        while (!found && i++ < max) {
            selectRandomProductMEW(hasRating != null, prod_type.toLowerCase().contains("master"));
            switch (prod_type.toLowerCase()) {
                case "member":
                    found = !ProductDisplay.isMasterMemberPage();
                    break;
                case "master":
                    found = ProductDisplay.isMasterMemberPage();
                    break;
                case "member_alternate_image":
                    found = Wait.untilElementPresent("product_display.alt_images")
                            && !ProductDisplay.isMasterMemberPage();
                    break;
                case "master_alternate_image":
                    found = Wait.untilElementPresent("product_display.alt_image")
                            && ProductDisplay.isMasterMemberPage();
                    break;
            }
            if (!found) {
                logger.info("Not Navigated to " + prod_type + " product: retrying:" + i);
                Navigate.browserBack();
            }
            if (!onPage("product_display", "registry_pdp")) {
                found = false;
            }
        }
        if (!found) {
            Assert.fail("Failed to find " + prod_type + " product after " + max + " tries.");
        }
        resumePageHangWatchDog();
    }

    /**
     * Clicks on given arrow inside Recently Viewed panel
     *
     * @param arrow left or right
     * @throws Throwable if any exception occurs
     */
    @When("^I click on \"(left|right)\" arrow key inside Recently Viewed panel using mobile website$")
    public void I_click_on_arrow_key_inside_Recently_Viewed_panel(String arrow) throws Throwable {
        RecentlyViewed.updateProducts();
        if (!Clicks.clickIfPresent("recently_viewed_items.scroll_" + arrow)) {
            Assert.fail("ERROR - APP: Cannot scroll to the " + arrow);
        }
        // wait for the scroll animation - can't find it programmatically but it always takes the same amount of time
        Utils.threadSleep(2000, null);
    }

    /**
     * Visit the PDP of given number of random products of given type
     *
     * @param count     number of products to view
     * @param prod_type member, master, member_alternate_image or master_alternate_image
     * @throws Throwable if any exception occurs
     */
    @When("^I view (\\d+) random (member|master|member_alternate_image|master_alternate_image) products using mobile website$")
    public void I_view_random_member_products(int count, String prod_type) throws Throwable {
        int i = 0;
        shouldBeOnPage("category_browse", "search_result");
        while (i < count) {
            I_select_a_random_product_using_mobile_website(prod_type, null);
            if (i < count - 1)
                Navigate.browserBack();
            i++;
            logger.info("Navigate to " + i + " product");
        }
    }

    /**
     * Selects a predefined product from the current search result or browse page
     * <p>Predefined products: "78600", "22804", "1494", "86800", "1275"</p>
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select a predefined orderable random member product using mobile website$")
    public void I_select_a_predefined_orderable_random_member_product_using_mobile_website() throws Throwable {
        /*
              78600: Lenox Eternal Gold 5-Piece Place Setting
              22805: Lenox Eternal Gold 4-Piece Hostess Set
              1494:  Villeroy & Boch Dinnerware, French Garden Dinner Plate
              86800: Lenox Vintage Jewel 5 Piece Place Setting
              1276:  Lenox Federal Platinum All Purpose Bowl
         */
        String[] predefined_product_ids = {"78600", "22804", "1494", "86800", "1275"};
        boolean searchResults = Elements.elementPresent("search_result.search_results_thumbnail_wrapper");
        String selector = (searchResults ? "search_result" : "category_browse") // page name
                + "." + "product_thumbnail_list";
        List<WebElement> elements = Elements.findElements(Elements.element(selector));
        WebElement selected = null;

        if (elements == null || elements.isEmpty()) {
            throw new NoSuchElementException("No elements found with selector: " + selector);
        }

        for (WebElement el : elements) {
            String id = el.getAttribute("data-product_id");
            if (id != null && ArrayUtils.contains(predefined_product_ids, id) || RunConfig.batchMode) {
                selected = el.findElement(By.xpath(".//div/img"));
                break;
            }
        }

        if (selected == null) {
            Assert.fail("Failed to find  a predefined orderable random member product");
        }
        Clicks.click(selected);
    }

    /**
     * Selects a predefined master product from the current search result or browse page
     * <p>Predefined products: "78600", "22805", "1494", "86800", "1275"</p>
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select a predefined orderable random master product using mobile website$")
    public void I_select_a_predefined_orderable_random_master_product_using_mobile_website() throws Throwable {
        String[] predefined_product_ids = {"78600", "22805", "1494", "86800", "1275"};
        boolean searchResults = Elements.elementPresent("search_result.search_results_thumbnail_wrapper");
        String selector = (searchResults ? "search_result" : "category_browse") // page name
                + "." + "product_thumbnail_list";
        List<WebElement> elements = Elements.findElements(Elements.element(selector));
        WebElement selected = null;

        if (elements == null || elements.isEmpty()) {
            throw new NoSuchElementException("No elements found with selector: " + selector);
        }

        for (WebElement el : elements) {
            String id = el.getAttribute("data-product_id");
            if (id != null && ArrayUtils.contains(predefined_product_ids, id)) {
                selected = el.findElement(By.xpath(".//div/img"));
                break;
            }
        }

        if (selected == null) {
            Assert.fail("Failed to find  a predefined orderable random member product");
        }
        Clicks.click(selected);
    }

    /**
     * Selects the given sort by option on category browse page
     *
     * @param toSelect  sort by option to select
     * @param page_type "category browse"
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" page using mobile website$")
    public void I_select_from_page_using_mobile_website(String toSelect, String page_type) throws Throwable {
        switch (page_type) {
            case "category browse":
                new Browse().sortBy(toSelect);
                break;
            default:
                logger.info("unsupported page");
                break;
        }
    }


    /**
     * Selects the given facet from browse or search results page
     *
     * @param facet_name facet to select
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" facet on left nav using mobile website$")
    public void I_select_facet_on_left_nav_using_mobile_website(String facet_name) throws Throwable {
        Wait.forPageReady();
        // We have no better way to wait for this animation
        Utils.threadSleep(1000, null);
        //mew bcom and mcom have too different facet selection
        if (macys()) {
            Wait.secondsUntilElementPresent("category_browse.verify_page", 10);
            String element = onPage("category_browse") ? "category_browse.sort_by" : "search_result.filter_by_select";
            Wait.secondsUntilElementPresent(element, 10);
            Clicks.javascriptClick(element);
        } else {
            Wait.secondsUntilElementPresent("left_facet.show_more_facets", 50);
            if (Elements.getText("left_facet.show_more_facets").equalsIgnoreCase("show more")) {
                Clicks.click("left_facet.show_more_facets");
            }
            Clicks.clickIfPresent("category_browse.refine_results");
        }
        MEWLeftFacet.selectFacetOnLeftNav(facet_name);
        if (Elements.elementPresent("left_facet.women_size")) {
            Elements.findElements("left_facet.women_size").get(0).click();
        }
    }

    /**
     * Selects the given sub facet from facet panel
     *
     * @param sub_facet_name sub facet to select
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" sub facet on left nav using mobile website$")
    public void I_select_sub_facet_on_left_nav_using_mobile_website(String sub_facet_name) throws Throwable {
        MEWLeftFacet.selectSubFacetOnLeftNav(sub_facet_name);
    }

    /**
     * Applies the currently selected facet
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I confirm selected facets using mobile website$")
    public void I_confirm_selected_facets_using_mobile_website() throws Throwable {
        MEWLeftFacet.confirmFacets();
    }

    /**
     * Searches for the given zip code in the bops facet and selects a random store
     *
     * @param zipcode zip code to search for
     * @throws Throwable if any exception occurs
     */
    @And("^I input \"([^\"]*)\" as zip code in bops facet and select a store$")
    public void I_input_as_zip_code_in_bops_facet_and_select_a_store(String zipcode) throws Throwable {
        if (bloomingdales()) {
            Clicks.clickIfPresent("left_facet.bops_change_button");
            TextBoxes.typeTextbox("left_facet.bops_zip_code", zipcode);
            DropDowns.selectByValue("left_facet.search_distance", "100");
        } else {
//            if (Wait.untilElementPresent("left_facet.bops_facet_value")) {
//                Clicks.randomJavascriptClick("left_facet.bops_facet_value");
//                return;
//            }
            Assert.assertTrue("ERROR-ENV: zip code text box not present", Elements.elementPresent("left_facet.bops_zip_code"));
            TextBoxes.typeTextbox("left_facet.bops_zip_code", zipcode);
            DropDowns.selectByValue("change_pickup_store_dialog.search_distance", "100");
        }

        Clicks.javascriptClick("left_facet.bops_search");
        Wait.secondsUntilElementPresent(("left_facet.bops_facet_value"), 50);
        if (Elements.elementPresent("left_facet.bops_error")) {
            Assert.fail("Unable to find stores");
        }

        if (Elements.elementPresent("left_facet.bops_facet_value")) {
            Clicks.randomJavascriptClick("left_facet.bops_facet_value");
        }

    }

    /**
     * Selects given list of filters on registry category browse page
     *
     * @param tbl list of filters to select
     * @throws Throwable if any exception occurs
     */
    @And("^I registry filter as follows:$")
    public void I_registry_filter_as_follows(List<String> tbl) throws Throwable {
        Clicks.clickIfPresent("header.close_browser_upgrade");
        tbl.forEach(gnName -> Clicks.click(Elements.paramElement("category_browse.registry_filter_by", gnName)));
    }

    /**
     * Verifies browser is on category browse page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be on mobile browse page$")
    public void I_should_be_on_mobile_browse_page() throws Throwable {
        shouldBeOnPage("category_browse");
    }

    /**
     * Selects the given distance option from change pickup store dialog
     *
     * @param distance distance option to select
     * @throws Throwable if any exception occurs
     */
    @When("^I select \"([^\"]*)\" miles in bops facet change store dialog using mobile website$")
    public static void I_select_miles_in_bops_facet_change_store_dialog_using_mobile_website(String distance) throws Throwable {
        DropDowns.selectByValue("change_pickup_store_dialog.search_distance", distance);
    }

    /**
     * Verifies the sort by functionality on browse and search results page
     *
     * @param sortByOptions list of expected sort by options
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see sort by functionality with below options using mobile website:$")
    public void I_should_see_sort_by_functionality_with_below_options_using_mobile_website(List<String> sortByOptions) throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("search_result.verify_page");
        Boolean isCategoryBrowse = !onPage("search_result", "dynamic_landing");
        String pageName = isCategoryBrowse ? "category_browse" : "search_result";
        Wait.untilElementPresent(pageName + ".total_products");
        String productCount = Elements.getText(pageName + ".total_products");
        if (macys()) {
            Clicks.click(isCategoryBrowse ? "category_browse.sort_by" : "search_result.filter_by_select");
        } else {
            Clicks.clickIfPresent(pageName + ".refine_results");
        }
        Wait.secondsUntilElementPresent(pageName + ".sort_by_select", 10);
        List<String> sortByOptionsUI = DropDowns.getAllValues(pageName + ".sort_by_select");
        Assert.assertTrue("ERROR - APP: Sort by options are not displayed correctly!!", (sortByOptionsUI.size() == sortByOptions.size()));
        for (String option : sortByOptions) {
            Assert.assertTrue("ERROR - APP: Sort by (" + option + ") option is not displayed in page!!", CommonUtils.matchSimilarSortBy(sortByOptionsUI, option));
        }
        DropDowns.selectByText(pageName + ".sort_by_select", sortByOptionsUI.get(new Random().nextInt(sortByOptionsUI.size())));
        if (macys()) {
            Wait.untilElementPresent("left_facet.apply");
            Elements.findElements("left_facet.apply", WebElement::isDisplayed).get(0).click();
        }
        Wait.forLoading(By.id("mb-j-spinner"));
        Wait.forPageReady();
        Wait.untilElementPresent(pageName + ".total_products");
        Assert.assertEquals("ERROR - APP: Sort by functionality is not working properly!!", productCount, Elements.getText(pageName + ".total_products"));
    }

    /**
     * Verifies the sort by functionality on browse and search results page in registry mode
     *
     * @param sortByOptions list of expected sort by options
     * @throws Throwable if any exception occurs
     */
    @And("^I verify sort by functionality in registry mode using mobile website:$")
    public void I_verify_sort_by_functionality_in_registry_mode_using_mobile_website(List<String> sortByOptions) throws Throwable {
        Wait.forPageReady();
        String page = (onPage("search_result") ? "search_result" : "category_browse");
        String productCount = Elements.getText(page + ".total_products");
        Wait.secondsUntilElementPresent(page + ".sort_by_button", 10);
        List<String> sortByOptionsUI = Elements.findElements(page + ".sort_by_button").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("Sort by options are not displayed correctly!!", (sortByOptionsUI.size() == sortByOptions.size()));
        for (String option : sortByOptionsUI) {
            Assert.assertTrue("Sort by (" + option + ") option is not displayed in page!!", CommonUtils.matchSimilarSortBy(sortByOptionsUI, option));
        }
        Clicks.clickRandomElement(page + ".sort_by_button");
        Wait.forPageReady();
        Assert.assertEquals("Sort by functionality is not working properly!!", productCount, Elements.getText(page + ".total_products"));
    }

    /**
     * Navigates to random browse page using site menu, assuming site menu is present on current page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to random browse page from site menu using mobile website$")
    public void I_navigate_to_random_browse_page_from_site_menu_using_mobile_website() throws Throwable {
        scrollToLazyLoadElement("site_menu.site_menu_title");
        Clicks.click("site_menu.site_menu_title");
        Wait.untilElementPresent("site_menu.shop_all_products");
        Clicks.click("site_menu.shop_all_products");
        Wait.untilElementPresent("site_menu.category_names");
        Clicks.clickRandomElement("site_menu.category_names");
        shouldBeOnPage("category_browse");
    }

    /**
     * Verifies that the recommendation panel is displayed on PDP
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see recommendation panel on pdp page using mobile website$")
    public void I_should_see_recommendation_panel_on_pdp_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("product_display");
        Elements.elementShouldBePresent("recommendations.recommendations_products");
    }

    /**
     * Selects the first recommended product from PDP
     *
     * @throws Throwable if any exception occurs
     */
    @When("I select first recommended product from pdp page using mobile website")
    public void I_select_recommended_product_from_pdp_page_using_mobile_website() throws Throwable {
        if (Elements.anyPresent("product_display.recommended_product")) {
            Clicks.javascriptClick("product_display.recommended_product");
            shouldBeOnPage("product_display");
        } else {
            Assert.fail("Unable to click recommended product from horizontal pros panel");
        }
    }

    /**
     * Selects random asset from mew home page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select random asset from mew home page$")
    public void I_select_random_asset_from_mew_home_page() throws Throwable {
        if (Elements.elementPresent("home.home_asset")) {
            Clicks.click(Elements.findElement("home.home_asset"));
        } else {
            Assert.fail("Unable to find home page assets");
        }
    }

    /**
     * Clicks on "show more" and waits for "show less" to display on facet panel
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click show more to expand facet panel using mobile website$")
    public void I_click_show_more_to_expand_facet_panel_using_mobile_website() throws Throwable {
        //expand show more button from browse/search result page
        Clicks.clickIfPresent("left_facet.show_more_facets");
        Wait.until(() -> Elements.getText("left_facet.show_more_facets").equalsIgnoreCase("show less"));
    }

    /**
     * Selects the given category from the site menu, assuming site menu and given category is present
     *
     * @param pageType category name to select
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to registry \"([^\"]*)\" browse page from site menu using mobile website$")
    public void I_navigate_to_registry_browse_page_from_site_menu_using_mobile_website(String pageType) throws Throwable {

        try {
            scrollToLazyLoadElement("site_menu.site_menu_title");
            Clicks.click("site_menu.site_menu_title");
            Wait.untilElementPresent("site_menu.shop_all_products");
            Clicks.click("site_menu.shop_all_products");
            Wait.untilElementPresent("site_menu.category_names");
            Clicks.clickElementByText("site_menu.category_names", pageType);
        } catch (Exception e) {
            Assert.fail("Element not present " + e);
        }
        shouldBeOnPage("category_browse");
    }

    /**
     * Selects the given quantity of the current product on PDP
     *
     * @param quantity how many to add
     * @throws Throwable if any exception occurs
     */
    @And("^I add \"([^\"]*)\" quantity to my bag from mobile standard PDP Page$")
    public void I_add_quantity_to_my_bag_from_mobile_standard_pdp_page(String quantity) throws Throwable {
        shouldBeOnPage("product_display");
        ProductDisplay.selectQuantity(quantity);
    }

    /**
     * Selects the given text in sort by dropdown on designer page
     *
     * @param toSelect text to select
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" in sort by drop down on designer page using mobile website$")
    public void I_select_in_sort_by_drop_down_on_designer_page_using_mobile_website(String toSelect) throws Throwable {
        Clicks.clickIfPresent("category_browse.refine_results");
        Wait.untilElementPresent("brand_index.sort_by_select");
        DropDowns.selectByText("brand_index.sort_by_select", toSelect);
    }

    /**
     * Scrolls to bottom of page and clicks on "back to top arrow"
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I scroll to bottom and select back to top arrow using mobile website$")
    public void I_scroll_to_bottom_and_select_back_to_top_arrow_using_mobile_website() throws Throwable {
        scrollToLazyLoadElement("footer.footer_bottom_links");
        Clicks.clickWhenPresent("brand_index.back_to_top");

    }

    /**
     * Selects given size from size facet
     *
     * @param sizeFacet size to select
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" size facet on left nav using mobile website$")
    public void I_select_size_facet_on_left_nav_using_mobile_website(String sizeFacet) throws Throwable {
        MEWLeftFacet.selectSizeFacetOnLeftNav(sizeFacet);

    }

    /**
     * Selects given sub facet
     *
     * @param sub_facet_name sub facet to select
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" sub facet from left nav using mobile website$")
    public void I_select_sub_facet_from_left_nav_using_mobile_website(String sub_facet_name) throws Throwable {
        MEWLeftFacet.selectSubFacetsFromLeftNav(sub_facet_name);
    }

    @Then("^I verify recommendation panel is not displayed on shopping bag page$")
    public void iVerifyRecommendationPanelIsNotDisplayedOnShoppingBagPage() throws Throwable {
        Assert.assertFalse("Recommendation Panel is Displaying in Shopping Bag Page", Elements.elementPresent("shopping_bag.recommendation_panel"));
    }

    @When("^I select random link to navigate to dynamic landing page using mobile website$")
    public void iSelectRandomLinkToNavigateToDynamicLandingPage() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse.dlp_name", 10);
        Elements.getRandomElement("category_browse.dlp_name", el -> el.getAttribute("href").contains("/shop/featured/")).click();
    }

    @Then("^I should be navigated to mobile brand index page$")
    public void iShouldBeNavigatedToMobileBrandIndexPage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Not Navigated to brand index page!!",
                macys() ? Wait.untilElementPresent("brand_index.browse_cat_name") && Elements.getText("brand_index.browse_cat_name").toLowerCase().contains("brands") : Wait.until(() -> onPage("brand_index")));
    }

    @And("^I navigate to CHANEL category browse page using mobile website$")
    public void iNavigateToCHANELCategoryBrowsePageUsingMobileWebsite() throws Throwable {
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName("BEAUTY");
        GlobalNav.navigateOnGnByName("Featured Brands");
        GlobalNav.navigateOnGnByName("CHANEL");
        GlobalNav.closeGlobalNav();
    }

    @When("^I select (grid|list|gallery|swipe) view display options on browse page$")
    public void iSelectDisplayOptionsOnBrowsePage(String viewType) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("category_browse." + viewType + "_view", 5);
        Clicks.clickIfPresent("category_browse.change_view");
        Clicks.click("category_browse." + viewType + "_view");
    }

    @Then("^I should see products in (grid|list|gallery|swipe) view on browse page$")
    public void iShouldSeeProductsInGridViewOnBrowsePage(String viewType) throws Throwable {
        Assert.assertTrue("Product are not displayed in " + viewType + " view on browse page", Elements.elementPresent("category_browse." + viewType + "_view_products"));
    }

    @Then("^I should be able to change product quantity on PDP page$")
    public void iShouldBeAbleToChangeProductQuantityOnPDPPage() throws Throwable {
        if (macys()) {
            Assert.assertTrue("Product quantity not displayed on PDP page", Wait.untilElementPresent("product_display.qty_value"));
            int quantity = Integer.parseInt(Elements.getText("product_display.qty_value"));
            Clicks.click("product_display.increment_qty");
            Assert.assertEquals("Unable to increment product quantity on PDP page", quantity + 1, Integer.parseInt(Elements.getText("product_display.qty_value")));
            Clicks.click("product_display.decrement_qty");
            Assert.assertEquals("Unable to decrement product quantity on PDP page", quantity, Integer.parseInt(Elements.getText("product_display.qty_value")));
        } else {
            Assert.assertTrue("Product quantity not displayed on PDP page", Wait.untilElementPresent("product_display.quantity"));
            int quantity = Integer.parseInt(DropDowns.getSelectedValue(Elements.element("product_display.quantity")));
            DropDowns.selectByText("product_display.quantity", String.valueOf(quantity + 1));
            Assert.assertEquals("Not selected quantity", quantity + 1, Integer.parseInt(DropDowns.getSelectedValue(Elements.element("product_display.quantity"))));
        }
    }

    @Then("^I select random color and add member product to bag from master PDP$")
    public void I_select_random_color_and_add_member_product_to_bag_from_master_PDP() throws Throwable {
        Clicks.clickIfPresent("product_display_master.expand_member_details");
        List<WebElement> memberProducts = Elements.findElements("product_display_master.member_products");
        Assert.assertFalse("ERROR - DATA: Member product not displayed on Master PDP page", memberProducts.isEmpty());
        List<WebElement> memberProductColors = memberProducts.get(0).findElements(Elements.element("product_display.select_default_color"));
        Assert.assertFalse("ERROR - DATA: Member product colors not displayed on Master PDP page", memberProductColors.isEmpty());
        Clicks.click(memberProducts.get(0).findElement(Elements.element("product_display_master.add_member_to_bag")));
        Wait.untilElementPresent("add_to_bag.checkout");
        if (macys() && !Elements.elementPresent("add_to_bag.checkout")) {
            Clicks.click("home.my_bag");
            Assert.assertTrue("Member product is not added to bag", Wait.untilElementPresent("shopping_bag.line_items"));
        } else {
            Assert.assertTrue("Member product is not added to bag", ProductDisplay.addedToBag());
        }
    }

    @Then("^I should see \"(by category|expiring soon)\" sort option is selected on deals and promotions page$")
    public void iShouldSeeSortOptionIsSelectedOnDealsAndPromotionsPage(String shortedBy) throws Throwable {
        shouldBeOnPage("deals_and_promotions");
        Wait.untilElementPresent("deals_and_promotions.selected_filter");
        Assert.assertEquals("Offers are not shorted by \"" + shortedBy + "\" on deals and promotions page", shortedBy, Elements.getText("deals_and_promotions.selected_filter"));
    }

    @Then("^I should see \"([^\"]*)\" in facet breadcrumb$")
    public void iShouldSeeInFacetBreadcrumb(String facetValue) throws Throwable {
        Wait.untilElementPresent("category_browse.facet_breadcrumb_value");
        Assert.assertEquals(facetValue + " is not displayed in facet breadcrumb", facetValue, Elements.getText("category_browse.facet_breadcrumb_value"));
    }

    @Then("^I verify order by phone on PDP using mobile website$")
    public void iVerifyOrderByPhoneOnPDPUsingMobileWebsite() throws Throwable {
        new Home().iShouldSeeFollowingElementsOnPage("product_display", "page", Arrays.asList("order_by_phone", "order_by_phone_number", "order_by_phone_msg"));
        Assert.assertEquals("Order by phone link is not correct on PDP page", Elements.getElementAttribute("product_display.order_by_phone", "href"), "tel:18665932540");
    }

    @Then("^I verify browse page data is displayed correctly for category in \"([^\"]*)\" mode$")
    public void i_verify_browse_page_data_is_displayed_correctly_for_category(String mode) throws Throwable {
        Wait.until(() -> onPage("category_browse"), 10);
        shouldBeOnPage("category_browse");
        Browse browse = new Browse();
        String categoryId = browse.getCategoryId();
        List<Integer> productIdsFromService = BrowsePageServices.getProductsFromService(mode, categoryId);
        Assert.assertTrue("ERROR - APP :: No products returned by service", !productIdsFromService.isEmpty());
        Integer expectedProductCount = productIdsFromService.size();
        Integer totalProductCount = BrowsePageServices.getTotalProductCountFromService(mode, categoryId);
        logger.info(totalProductCount + " :: count returned by service");
        if (totalProductCount > 90) {
            Integer actualTotalProductCount = Integer.parseInt(Elements.getText("category_browse.total_products").split("of")[1].replaceAll("\\D+", ""));
            Assert.assertEquals("ERROR - DATA :: Products count is not appropriate for the category " + categoryId, actualTotalProductCount, totalProductCount);
            Integer actualProductCount = Integer.parseInt(Elements.getText("category_browse.total_products").split("of")[0].replace("1-", "").trim());
            Assert.assertEquals("ERROR - DATA :: Products count is not appropriate for the category " + categoryId, expectedProductCount, actualProductCount);
        } else {
            Assert.assertEquals("ERROR - DATA :: Products count is not appropriate for the category " + categoryId, expectedProductCount, browse.getProductsCount());
        }
        List actualProductList = browse.getProductsIds();
        Assert.assertEquals("ERROR - DATA :: Appropriate products are not displayed for the category " + categoryId, productIdsFromService, actualProductList);
    }

    @Then("^I verify appropriate products are displayed for the category$")
    public void iVerifyAppropriateProductsAreDisplayedForTheCategory() throws Throwable {
        Wait.until(() -> onPage("category_browse"), 10);
        shouldBeOnPage("category_browse");
        Browse browse = new Browse();
        String categoryId = browse.getCategoryId();
        HashMap<String, String> serviceParams = new HashMap<>();
        serviceParams.put("productsPerPage", "48");
        serviceParams.put("pageNumber", "1");
        serviceParams.put("countryCode", "US");
        serviceParams.put("deviceType", "PHONE");
        JSONObject serviceResponse = Categories.getProductsByCategory(categoryId, serviceParams);
        Integer expectedTotalProducts = serviceResponse.getJSONObject("products").getInt("totalProducts");
        Assert.assertEquals("Products count is not appropriate for the category " + categoryId, expectedTotalProducts, browse.getProductsCount());
        List expectedProductsList = serviceResponse.getJSONObject("products").getJSONArray("navigationPoolProducts").toList();
        List actualProductList = browse.getProductsIds();
        Collections.sort(expectedProductsList);
        Collections.sort(actualProductList);
        Assert.assertEquals("Appropriate products are not displayed for the category " + categoryId, expectedProductsList, actualProductList);
    }

    @And("^I click on a PDP$")
    public void iClickOnAPDP() {
        Wait.untilElementPresent("category_browse.product_name_without_brand_name");
        List<WebElement> products = Elements.findElements("category_browse.product_name_without_brand_name");
        Clicks.click(products.get(new Random().nextInt(products.size())));
        Wait.until(() -> onPage("product_display"), 10);
    }

    @When("^I click on the brand name$")
    public void iClickOnTheBrandName() {
        Wait.untilElementPresent("category_browse.pdp_brand_name");
        Clicks.click("category_browse.pdp_brand_name");
    }

    @And("^I click on Free pick up$")
    public void iClickOnFreePickUp() throws Throwable {
        Wait.untilElementPresent("category_browse.pick_up");
        Clicks.click("category_browse.pick_up");
    }

    /**
     * Method to click on Pick Up In-Store of sort and filter page
     */
    @And("^I click on Pick Up In-Store$")
    public void clickOnPickUpInStore() throws Throwable {
        Wait.untilElementPresent("sort_filter.shop_local_Store");
        Clicks.click("sort_filter.shop_local_Store");
        logger.info("Successfully clicked on Pick Up In-Store");
    }

    /**
     * Method to enter Zip code in bops store search box of sort and filter page
     * @param zip valid zip code
     */
    @And("^I enter zip code \"([^\"]*)\" in pick up in-store$")
    public void enterZipCode(String zip) throws Throwable {
        Wait.untilElementPresent("shop_local_store.find_store_zipcode");
        TextBoxes.typeTextbox("shop_local_store.find_store_zipcode", zip);
        logger.info("Successfully entered zip code:" +zip);
    }

    /**
     * Method to click on search button of sort and filter page
     */
    @And("^I click on find store search button$")
    public void clickOnSearch() throws Throwable {
        Clicks.click("shop_local_store.find_store_search_button");
        Utils.threadSleep(4000, "Waiting for stores  to load");
        logger.info("Successfully clicked on search button");
    }

    /**
     * Method to validate Store list of Same Zip code search twice in sequence.
     */
    @And("^again I click on search button and verify first and second time search result$")
    public void getStoreDetails() throws Throwable {
        List<String> firstSearchStoresList =returnListOfBopsStores();
        clickOnSearch();
        List<String> secondSearchStoresList=returnListOfBopsStores();
        Assert.assertTrue("ERROR - APP: First and second search of the same zip code values are not same", firstSearchStoresList.equals(secondSearchStoresList));
        logger.info("First and second search of the same zip code values are same, Working as expected");
    }

    /**
     * Method to return List of BOPS Stores of searched Zip code
     * @return List of stores
     */
    private List<String> returnListOfBopsStores() {
        List<WebElement> stores = Elements.findElements("shop_local_store.stores_list");
        Iterator<WebElement> itr = stores.iterator();
        List<String> storesList = new ArrayList<>();
        while (itr.hasNext()) {
            storesList.add(itr.next().getText());
        }
        return storesList;
    }

    /**
     * Method to click on Filter link of Search and Browse page
     */
    @And("^I click filter of search and browse page$")
    public void clickOnFilterOfSearchAndBrowse() throws Throwable {
        Clicks.click("search_result.sort_filter");
        logger.info("Successfully clicked on filter");
    }

    @And("^I select \"([^\"]*)\" in sort by drop down in mew$")
    public void I_select_in_sort_by_drop_down(String toSelect) throws Throwable {
        Wait.forPageReady();
        DropDowns.selectByText("search_result.sort_by", toSelect);
        Wait.secondsUntilElementNotPresent("search_result.loading_spinner", 20);
        Wait.forPageReady();
        logger.info(toSelect + " sort order is selected");
    }

    @When("^I click on the (grid|list|gallery) view button in the toggle panel$")
    public void iClickOnTheListViewButtonInTheTogglePanel(String view) throws Throwable {
        Wait.secondsUntilElementPresentAndClick("search_result.toggle_dropdown", 10);
        Elements.findElement("search_result.toggle_dropdown").click();
        switch (view.toLowerCase()) {
            case "grid":
                Elements.findElement("search_result.toggle_dropdown").click();
                Clicks.click(Elements.findElement("category_browse.grid_view"));
                break;
            case "list":
                Elements.findElement("search_result.toggle_dropdown").click();
                Clicks.click(Elements.findElement("category_browse.list_view"));
                break;
            case "gallery":
                Elements.findElement("search_result.toggle_dropdown").click();
                Clicks.click(Elements.findElement("category_browse.gallery_view"));
                break;
        }
        logger.info(view + " view is selected");
    }

    @When("^I navigate to next page on (search|browse|dlp) page$")
    public void iNavigateToNextPageOnSearchPage(String page) throws Throwable {
        Pagination.selectPagination("next");
        Wait.untilElementPresent("pagination.pagination");
        logger.info("Selected next button on " + page + " page");
    }

    @And("^I navigate to prev page on (search|browse|dlp)")
    public void iNavigateToPrevPageOnSearch(String page) throws Throwable {
        Pagination.selectPagination("prev");
        Wait.untilElementPresent("pagination.pagination");
        logger.info("Selected prev button on " + page + " page");
    }

    @When("^I navigate to a random page using mobile website$")
    public void iNavigateToARandomPageUsingMobileWebsite() throws Throwable {
        if (Pagination.pageCount() > 1) {
            Pagination.selectPagination("random");
            Wait.forPageReady();
            logger.info("random page selected successfully");
        } else {
            System.out.print("Seems only one page found. So perform next steps actions on same page");
        }
    }

    @When("^I select (\\d+) page on (search|browse|DLP) page using mobile website$")
    public void iSelectPageOnSearchPageUsingMobileWebsite(int pageNumber, String page) throws Throwable {
        Pagination.selectpageByNumber(pageNumber);
        logger.info("Selected page number " + pageNumber + " on" + page + " page");
    }
}
