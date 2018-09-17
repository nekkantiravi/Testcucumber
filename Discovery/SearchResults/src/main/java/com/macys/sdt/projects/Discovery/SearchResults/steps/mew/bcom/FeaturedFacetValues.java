package com.macys.sdt.projects.Discovery.SearchResults.steps.mew.bcom;

import com.macys.sdt.framework.interactions.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;


public class FeaturedFacetValues {

    private static String search_term = null;
    public static String selected_facetValue;
    private static String search_query;
    public static List<WebElement> ffv_list;
    public static WebElement randomFfv;
    public static String ffv_count;
    public static List<WebElement> autocomplete_suggestions;
    public static String ffv_title;
    public static String breadcrumbUI;
    public static String result_label;
    public static String selected_ffv;
    public static String ffv_type;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FeaturedFacetValues.class);
    public int threshold_product_count = 30;

    @And("^I should see the Refine Results link$")
    public void iShouldSeeTheRefineResultshLink() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("search_result.refine_search");
        LOGGER.info("Verified by checking the refine resilts link");
    }

    @When("^I type \"([^\"]*)\" in mew search$")
    public void i_type_in_mew_search(String keyword) throws Throwable {
        search_term = keyword;
        if(macys()) {
            TextBoxes.typeTextbox("home.search_field", search_term);
            Clicks.click("search_result.search_icon");
        }
        else
            TextBoxes.typeTextNEnter("home.search_field", search_term);
        Wait.forPageReady();
        LOGGER.info("Entered Search keyword in the mew search box");
    }

    @When("^I tap on the Refine Results link$")
    public void iTapOnTheRefineResultsLink() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Clicked on Refine Reslts link", Clicks.clickWhenPresent("search_result.refine_search"));
        LOGGER.info("Verified by clicking the refine results link");
    }

    @And("^I should see \"Sort By” is still in a dropdown menu with “featured” as the default value$")
    public void iShouldSeeSortByIsStillInADropdownMenuWithFeaturedAsTheDefaultValue() throws Throwable {
        try {
            Wait.forPageReady();
            DropDowns.getSelectedValue(Elements.element("search_result.sort_by_select")).equals("Featured");
        } catch (Exception e) {
            Assert.fail("Error in selecting a random value in SortBy dropdown");
        }
        LOGGER.info("Verified by selecting a sortby dropdown");
    }


    @When("^I select a facet$")
    public void iSelectAFacet() throws Throwable {
        try {
            List<WebElement> facets = Elements.findElements("search_result.facet_select");
            WebElement randomFacet = facets.get(new Random().nextInt(facets.size()));
            Clicks.click(randomFacet);
            List<WebElement> subfacet = Elements.findElement("search_result.sub_facets").findElements(By.className("display"));
            WebElement randomSubFacet = subfacet.get(new Random().nextInt(subfacet.size()));
            selected_facetValue = randomSubFacet.findElement(By.className("display-name")).getText();
            Clicks.click(randomSubFacet);

        } catch (Exception e) {
            Assert.fail("Error in selecting a facet");
        }
        LOGGER.info("Verified by selecting a facet");
    }

    @Then("^I should see the “apply” button and “clear all” appears within each facet category$")
    public void iShouldSeeTheApplyButtonAndClearAllAppearsWithinEachFacetCategory() throws Throwable {
        Assert.assertTrue("apply button is displayed for selected facet", Elements.findElement("search_result.apply_button").isDisplayed());
        Assert.assertTrue("Clear all button is displayed for selected facet", Elements.findElement("search_result.clearall_button").isDisplayed());
        LOGGER.info("Verified the apply and clear all buttons");
    }

    @And("^I should see “Clear All” clears the whole section and whatever “Shop For” they selected on the page$")
    public void iShouldSeeClearAllClearsTheWholeSectionAndWhateverShopForTheySelectedOnThePage() throws Throwable {
        iTapOnTheRefineResultsLink();
        iSelectAFacet();
        Clicks.click(Elements.findElement("search_result.clearall_button"));
        LOGGER.info("Verified by clicking on Clear All");

    }

    @When("^I tap on the \"Sort By” drop down$")
    public void iTapOnTheSortByDropDown() throws Throwable {
        Wait.forLoading("search_result.sort_by_option");
        Assert.assertTrue("Clicked on Sortby dropdown", Clicks.clickWhenPresent("search_result.sort_by_select"));
        LOGGER.info("Verified by clicking on Sortby dropdown");
    }

    @Then("^I should see the OS keyboard pop up where they can scroll to select which way we want to sort$")
    public void iShouldSeeTheOSKeyboardPopUpWhereTheyCanScrollToSelectWhichWayWeWantToSort() throws Throwable {
        Assert.assertTrue("OS keyboard is displayed", Elements.findElement("search_result.sort_by_select").isDisplayed());
        LOGGER.info("Verified by checking the OS keyboard pop up");
    }


    @When("^I click on the 'X' button next to the “Refine Results” title$")
    public void iClickOnTheXButtonNextToTheRefineResultsTitle() throws Throwable {
        iTapOnTheRefineResultsLink();
        Assert.assertTrue("Clicked on the X button", Clicks.clickWhenPresent("search_result.refine_results_close"));
        LOGGER.info("Verified by clicking on the X button");
    }

    @Then("^I should exit from the Refine Results$")
    public void iShouldExitFromTheRefineResults() throws Throwable {
        iShouldSeeTheRefineResultshLink();
        LOGGER.info("Verified by navigating back to the search results page");
    }


    @Then("^I should see the Refine Results modal with all filter:$")
    public void iShouldSeeTheRefineResultsModalWithAllFilter(List<String> filters) throws Throwable {
        try {
            List<String> filtersUI = Elements.findElements("search_result.facet_select").stream().map(e -> e.getText()).collect(Collectors.toList());
            Assert.assertTrue("Selected filter is visible in Refine Results modal", filtersUI.containsAll(filters));
        } catch (Exception e) {
            Assert.fail("Unable to see the filter in Refine Results modal");
        }
        LOGGER.info("Verified by checking the Refine Results modal with all filter");

    }

    @And("^I click apply for the filter selected$")
    public void iClickApplyForTheFilterSelected() throws Throwable {
        Clicks.click("search_result.apply_button");
        LOGGER.info("Verified by clicking on the apply button for selected filter");

    }

    @Then("^I should see the below addition of two exposed filters that used to show on the results page$")
    public void iShouldSeeTheBelowAdditionOfTwoExposedFiltersThatUsedToShowOnTheResultsPage(List<String> exposedFilters) throws Throwable {
        Wait.forPageReady();
            List<String> filters = Elements.findElements("search_result.facet_select").stream().map(e -> e.getText()).collect(Collectors.toList());
            Assert.assertTrue("Exposed filters are visible in Refine Results modal", filters.containsAll(exposedFilters));
        LOGGER.info("Verified by checking the Refine Results modal with all Exposed filters");
    }

    @And("^I verify the applied filter in search results page$")
    public void iVerifyTheAppliedFilterInSearchResultsPage() throws Throwable {
        try {
            Wait.untilElementPresent("search_result.facet_select");
            breadcrumbUI = Elements.findElement("search_result.selected_breadcrumb").getText();
            Assert.assertTrue("Selected subfacet and the breadcrumb displayed in UI are same", selected_facetValue.equals(breadcrumbUI));
        } catch (Exception e) {
            Assert.fail("Selected subfacet and the breadcrumb displayed in UI are not same");
        }
        LOGGER.info("Verified by selecting a facet and breadcrumb displayed");
    }

    @When("^I should see the searchquery in the result lable$")
    public void i_should_see_the_searchquery_in_the_result_lable() throws Throwable {
        Wait.untilElementPresent("search_result.result_label");
        search_query = Elements.findElement("search_result.search_keyword").getText();
        String Result_label = Elements.findElement("search_result.result_label").getText();
        Assert.assertTrue("Search_query is present in the result label", Result_label.contains(search_query));
        LOGGER.info("Search Query is present in the result label");
    }

    @Then("^I should see the Search query is in bold$")
    public void iShouldSeeTheSearchQueryIsInBold() throws Throwable {
        Wait.untilElementPresent("search_result.search_keyword");
        Assert.assertTrue("Search query is in bold", Elements.findElement("search_result.search_keyword").getCssValue("font-weight").equals("bold"));
        LOGGER.info("Search Query is in bold");
    }

    @Then("^I should see facets are loaded$")
    public void i_should_see_facets_are_loaded() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("search_result.ffv_display");
        LOGGER.info("Verified by checking the facets displayed on search results page");
    }

    @And("^I should see the name of the Facet value for each box$")
    public void iShouldSeeTheNameOfTheFacetValueForEachBox() throws Throwable {
        ffv_list = Elements.findElement("search_result.ffv_display").findElements(By.className("mb-search-ffv-facet"));
        randomFfv = ffv_list.get(new Random().nextInt(ffv_list.size()));
        Assert.assertTrue("Selected facet value name is displayed in the box", randomFfv.isDisplayed());
        LOGGER.info("Verified by checking the ffv name displayed in each box");
    }

    @And("^I should see the number of results for the respective facets value$")
    public void iShouldSeeTheNumberOfResultsForTheRespectiveFacetsValue() throws Throwable {
        String selected_ffv = randomFfv.getText();
        ffv_count = selected_ffv.replaceAll("\\D+", "");
        Assert.assertTrue("No.of results is displayed for the selected facet value", selected_ffv.contains(ffv_count));
        LOGGER.info("Verified by checking the number of results displayed for each facet");
    }

    @And("^I should see the FFV's does not allow for multiple select per level$")
    public void iShouldSeeTheFFVSDoesNotAllowForMultipleSelectPerLevel() throws Throwable {
        Clicks.click(randomFfv);
        String Result_label = Elements.findElement("search_result.result_label").getText();
        Assert.assertTrue(" FFV's does not allow for multiple select per level as the product count will not change", Result_label.contains(ffv_count));
        if (!Result_label.contains(ffv_count)) {
            Assert.fail("Error in selection of facets as count doesn't match ");
        }
        LOGGER.info("Verified the search results by selecting the same ffv");
    }

    @Then("^I should see the autocomplete suggestions$")
    public void iShouldSeeTheAutocompleteSuggestions() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Autocomplete suggestons are displayed",Wait.untilElementPresent("search_result.autocomplete_suggestions"));
        LOGGER.info("Verified the autocomplete suggestions");
    }

    @Then("^I should see the \"([^\"]*)\" title in the panel$")
    public void iShouldSeeTheTitleInThePanel(String recent_searches) throws Throwable {
        Clicks.click("search.search_field");
        String title = Elements.findElement("search_result.recent_searchcontainer").getText();
        Assert.assertTrue("recent searches title is displayed",title.contains(recent_searches));
        LOGGER.info("Verified the recent searches title in recent search panel");
    }

    @And("^I click on any autocomplete suggestion$")
    public void iClickOnAnyAutocompleteSuggestion() throws Throwable {
        autocomplete_suggestions= Elements.findElement("search_result.autocomplete_suggestions").findElements(By.className("mb-select-autocomplete"));
        WebElement random_suggestion = autocomplete_suggestions.get(new Random().nextInt(autocomplete_suggestions.size()));
        Clicks.click(random_suggestion);
        LOGGER.info("Verified by clicking the random auto suugestion displayed in the panel");
    }

    @And("^I type one character in mew search box$")
    public void iTypeOneCharacterInMewSearchBox() throws Throwable {
        Random rnd = new Random();
        String keyword = randomAlphanumeric(10);
        String random_character = String.valueOf(keyword.charAt(rnd.nextInt(keyword.length())));
        TextBoxes.typeTextNEnter("home.search_field", random_character);
        LOGGER.info("Verified by entering a random text");
    }

    @And("^I should see the recent searches in recent search container$")
    public void iShouldSeeTheRecentSearchesInRecentSearchContainer() throws Throwable {
        String expected_recent_searches= (String)Navigate.execJavascript(String.format("return window.localStorage.getItem('%s');", "recentSearches"));
        Assert.assertTrue("Resent searches are displayed",expected_recent_searches.contains(search_term));
        LOGGER.info("Verified by comparing the recent searches from local storage and the search keyword");

    }
    @When("^I select a random ffv$")
    public void iSelectARandomFfv() throws Throwable {
        ffv_list = Elements.findElements(By.className("mb-search-ffv-facet"));
        randomFfv = ffv_list.get(new Random().nextInt(ffv_list.size()));
        selected_ffv = randomFfv.getText();
        ffv_count = selected_ffv.replaceAll("\\D+", "");
        Clicks.click(randomFfv);
        LOGGER.info("Verified by clicking on a random ffv");
    }

    @And("^I should see the title should be in normal$")
    public void iShouldSeeTheTitleShouldBeInNormal() throws Throwable {
        ffv_title = Elements.findElement("search_result.ffv_title").getText();
        Elements.findElement("search_result.ffv_title").getCssValue("font-weight").equals("normal");
        LOGGER.info("Verified the search ffv title displayed is in normal font");
    }

    @Then("^I should see the title should be updated dynamically$")
    public void iShouldSeeTheTitleShouldBeUpdatedDynamically() throws Throwable {
        String updated_title = Elements.findElement("search_result.ffv_title").getText();
        if(updated_title!= ffv_title)
                System.out.println("FFV title update dynamically");
            else
                System.out.println("FFV title and carousel is not displayed as the product count is below the threshold");

        LOGGER.info("FFV title is getting updated dynamically");
    }

    @And("^I should see the title be Shop By: chips Above Shop by Title$")
    public void iShouldSeeTheTitleBeShopForXFacetAboveTheFacetBoxes() throws Throwable {
        Elements.elementShouldBePresent("search_result.ffv_title");
        ffv_type = Elements.findElement(By.id("b-search-ffv-title")).getText().substring(9);
        LOGGER.info("Verified the search ffv title displayed in UI");
    }

    @Then("^I should see FFV carousal should be displayed based on the threshold product count$")
    public void iShouldSeeFFVCarousalShouldBeDisplayedBasedOnTheThresholdProductCount() throws Throwable {
        int threshold_product_count = 30;
        int ffv_count_ui = Integer.parseInt(ffv_count);
        if (ffv_count_ui >= threshold_product_count) {
            Elements.elementShouldBePresent("search_result.ffv_display");
        } else {
            System.out.print("FFV carousel will not be displayed as the product count is less than threshold");
        }
        LOGGER.info("Verified the display of FFV carousal based on the product count threshold ");
    }

    @And("^I should see “See More” button for (\\d+) FFVs$")
    public void iShouldSeeSeeMoreButtonForFFVs(int ffv_count) throws Throwable {
        ffv_list = Elements.findElement("search_result.ffv_display").findElements(By.className("mb-search-ffv-facet"));
        if(ffv_count>=ffv_list.size())
            Assert.assertTrue("See More button is present in the carosal",Elements.findElement("search_result.ffv_display").getText().contains("See More"));
        else if(ffv_list.size()<ffv_count){
            Assert.assertFalse("See More button will not be visible as FFV count is less than 12",Elements.findElement("search_result.ffv_display").getText().contains("See More"));
        }
        else{
            Assert.fail("FFV count exceeded 12 which is not expected");
        }
        LOGGER.info("Verified the display of See More button based on the ffv_count ");
    }

    @Then("^I should see the facet is updated in Refine filter modal$")
    public void iShouldSeeTheFacetIsUpdatedInRefineFilterModal() throws Throwable {
        WebElement updated_facet = Elements.findElement("search_result.updated_facet");
        if(updated_facet.isEnabled()) {
            String selected_facet = Elements.findElement("search_result.updated_facet").getText().replaceAll("[^A-Za-z]", "");
            Assert.assertTrue("facet is updated in the refine filter modal", breadcrumbUI.equals(selected_facet));
        }
        else{
            Assert.fail("facet is not updated in the Refine results modal");
        }
        LOGGER.info("Verified the facet update in refine results modal ");
    }
    @Then("^I should see the breadcrumb displayed$")
    public void iShouldSeeTheBreadcrumbDisplayed() throws Throwable {
        Elements.elementShouldBePresent("search_result.selected_breadcrumb");
        breadcrumbUI = Elements.findElement("search_result.selected_breadcrumb").getText();
        result_label= Elements.findElement("search_result.resultlabel_section").getText();
        LOGGER.info("Verified the breadcrumb display for the selected ffv ");
    }

    @And("^I should see the value/s show as selected with a check mark next to them$")
    public void iShouldSeeTheValueSShowAsSelectedWithACheckMarkNextToThem() throws Throwable {
        Elements.elementShouldBePresent("search_result.check_mark");
        LOGGER.info("Verified the check mark in the chip ");
    }

    @When("^I click on the X icon to unselect$")
    public void iClickOnTheXIconToUnselect() throws Throwable {
        Clicks.click("search_result.check_mark");
        LOGGER.info("Verified by clicking on the X icon ");
    }

    @Then("^I should not see the chip displayed$")
    public void iShouldNotSeeTheChipDisplayed() throws Throwable {
        Wait.forLoading("search_result.resultlabel_section");
        System.out.print("chip removed");
        LOGGER.info("Verified the chip removal ");
    }

    @When("^I click on SeeMore button$")
    public void iClickOnSeeMoreButton() throws Throwable {
      Clicks.clickIfPresent("search_result.seemore");
        LOGGER.info("Verified by clicking on the SeeMore button");
    }

    @Then("^I should see corresponding accordion modal opened by default$")
    public void iShouldSeeCorrespondingAccordionModalOpenedByDefault() throws Throwable {
        String accordion = Elements.findElement("search_result.refine_results_modal").findElement(By.className("display")).getText();
        Assert.assertTrue(ffv_type.equals(accordion));
        LOGGER.info("Verified the corresponding accordion open by default with ffv title ");
    }

    @Then("^I should see the chip displayed for the selected FFV$")
    public void iShouldSeeTheChipDisplayedForTheSelectedFFV() throws Throwable {
       Wait.untilElementPresent("seacrh_result.chip");
        LOGGER.info("Verified the display of chip for the FFV selected ");
    }

    @Then("^I verify the applied filter in browse page$")
    public void iVerifyTheAppliedFilterInBrowsePage() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("search_result.facet_select");
            breadcrumbUI = Elements.findElement("search_result.chip").getText();
            Assert.assertTrue("Selected subfacet and the breadcrumb displayed in UI are same", selected_facetValue.equals(breadcrumbUI));
        } catch (Exception e) {
            Assert.fail("Selected subfacet and the breadcrumb displayed in UI are not same");
        }
        LOGGER.info("Verified by selecting a facet and breadcrumb displayed");

    }
}
