package com.macys.sdt.projects.Marketing.MyAccount.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;


public class MyAccountStepsMarketing extends StepUtils {
    MyAccountSteps myAccountSteps = new MyAccountSteps();
    PageNavigation pageNavigation = new PageNavigation();

    @When("^I navigate to store locator page$")
    public void iNavigateToStoreLocatorPage() {
        Wait.forPageReady();
        Clicks.click("footer.goto_store_events");
        Wait.forPageReady();
    }

    @Then("^I am on the Our Stores Events Page as a guest user$")
    public void iAmOnTheOurStoreEventsPageAsAGuestuser() {
        shouldBeOnPage("store_events");
    }

    @And("^I verify the basic attributes of the Store Events Page$")
    public void iVerifyTheBasicAttributesOfTheStoreEventsPage() {
        assertTrue("'Macy's In-Store & Special Events!' page title is not displayed correctly. Actual Title: " + title(), title().equals("Macy's In-Store & Special Events!"));
        assertTrue("Magical Events banner is not displayed.", Elements.elementPresent("store_events.events_banner"));
        assertTrue("'Enter Key' editbox is not displayed.", Elements.elementPresent("store_events.search_key"));
        assertTrue("'Search Icon' is not displayed.", Elements.elementPresent("store_events.search_icon"));
        assertTrue("'Enter City or Zip Code' textbox is not displayed.", Elements.elementPresent("store_events.search_zip"));
        assertTrue("'More Options' dropdown is not displayed.", Elements.elementPresent("store_events.more_options"));
        assertTrue("'Search Events' button is not displayed.", Elements.elementPresent("store_events.search_events_button"));
    }

    @Then("^I search with invalid \"([^\"]*)\" in \"([^\"]*)\" and expect \"([^\"]*)\"$")
    public void iSearchWithInvalidZipCityStore(String zipValue, String searchPage, String expError) throws Throwable {

        switch (searchPage) {
            case "Store Location & Hours":
                Clicks.click("footer.goto_store_locations_and_hours");
                Wait.forPageReady("store_locations_hours");
                shouldBeOnPage("store_locations_hours");
                TextBoxes.typeTextbox("store_locations_hours.store_search_field", zipValue);
                Clicks.click("store_locations_hours.search_button");
                Wait.forPageReady();
                Thread.sleep(2000);
                assertTrue("Expected Error Message is not displayed. Actual Error displayed. " + Elements.getText("store_locations_hours.error_msg"), Elements.getText("store_locations_hours.no_results").equals(expError));
                break;
            case "Browse Catalog":
                Clicks.click("footer.goto_store_catalogs");
                Wait.forPageReady("store_catalogs");
                shouldBeOnPage("store_catalogs");
                TextBoxes.typeTextbox("store_catalogs.enter_zip_code", zipValue);
                Clicks.click("store_catalogs.search_button");
                Wait.forPageReady();
                Thread.sleep(2000);
                assertTrue("Expected Error Message is not displayed.. Actual Error displayed. " + Elements.getText("store_catalogs.error_msg"), Elements.getText("store_catalogs.error_msg").equals(expError));
                break;
            case "Store Events":
                Clicks.click("footer.goto_store_events");
                Wait.forPageReady("store_events");
                shouldBeOnPage("store_events");
                TextBoxes.typeTextbox("store_events.search_zip", zipValue);
                Clicks.click("store_events.search_events_button");
                Wait.forPageReady();
                assertTrue("Expected Error Message is not displayed.. Actual Error displayed. " + Elements.getText("store_events.error_msg"), Elements.getText("store_events.error_msg").equals(expError));
                break;
        }

    }

    @Given("^I am on the Our Stores Page as a guest user$")
    public void iAmOnTheOurStoresPageAsGuestUser() {
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        Clicks.click("footer.goto_our_stores");
        Wait.forPageReady();
        shouldBeOnPage("our_stores");

    }

    @Then("^I verify the sections of Our Stores page$")
    public void iVerifyTheSectionsOfOurStoresPage() {
        assertTrue("Our Stores page header is not displayed.", Elements.getText("our_stores.our_stores_header").equals("OUR STORES\n" +
                "DISCOVER THE MAGIC OF MACY'S NEAR YOU!"));
        assertTrue("'Find a Store' section is not displayed.", Elements.elementPresent("our_stores.find_a_store_banner"));
        assertTrue("'Browser Catalogs' section is not displayed.", Elements.elementPresent("our_stores.browse_catalogs_banner"));
        assertTrue("'Shopping Services' section is not displayed.", Elements.elementPresent("our_stores.shopping_services_banner"));
        assertTrue("'What's Happening at Macy's' section is not displayed.", Elements.elementPresent("our_stores.whats_happening_at_macys_banner"));
        assertTrue("'Find Store Events Near You' section is not displayed.", Elements.elementPresent("our_stores.find_store_events_near_you_banner"));
    }

    @And("^I verify that the links of our store page have rendered$")
    public void iVerifyThatTheLinksOfOurStorePageHaveRendered() {
        Clicks.click("our_stores.about_us");
        Wait.forPageReady();
        assertTrue("'About Us' Page is not displayed", title().equals("Our Stores - About Us - Macy's"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("our_stores.macys_event_marketing");
        Wait.forPageReady();
        assertTrue("'Macys Event Marketing' Page is not displayed", title().equals("Macy*s Event Marketing - Macy's"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("our_stores.corporate_sales");
        Wait.forPageReady();
        assertTrue("'Corporate Sales' Page is not displayed", title().equals("Corporate Sales – Macy's"));
    }

    @Given("^I am on the our stores About Us Page as a guest user$")
    public void iAmOnTheOurStoresAboutUsPageAsAGuestUser() {
        iAmOnTheOurStoresPageAsGuestUser();
        Clicks.click("our_stores.about_us");
        Wait.forPageReady();
        shouldBeOnPage("our_stores_about_us");
    }

    @Then("^I navigate to links of About Us page and verify the pages renders$")
    public void iNavigateToTheLinksOfAboutUsPageAndVerifyThePagesRender() {

        String origWindow = WebDriverManager.getWebDriver().getWindowHandle();
        String title = "";
        WebDriver driver = WebDriverManager.getWebDriver();
        Clicks.click("our_stores_about_us.macys_milestones");
        Wait.forPageReady();
        Set<String> allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Macy's Milestones' Page is not displayed. Acutal Title displayed - " + title, title.equals("Macy's History"));
                driver.close();
            }
        }
        driver.switchTo().window(origWindow);
        Clicks.click("our_stores_about_us.press_room");
        Wait.forPageReady();
        allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Press Room' Page is not displayed. Actual Page displayed " + title, title.equals("Press Room - Macy’s, Inc."));
                driver.close();
            }
        }
        driver.switchTo().window(origWindow);
        Clicks.click("our_stores_about_us.career_opportunities");
        Wait.forPageReady();
        allWinHandels = driver.getWindowHandles();

        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Career Opportunities' Page is not displayed. Actual Page displayed " + title, title.equals("Macy’s Jobs: Retail Employment Opportunities – Stores, Distribution Centers, Call Centers and Corporate Offices"));
                driver.close();
            }
        }
        driver.switchTo().window(origWindow);
        Clicks.click("our_stores_about_us.visitor_services");
        Wait.forPageReady();
        allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Visitor Services' Page is not displayed. Actual Page displayed " + title, title.equals("Visit Macy's USA | Visit Macy's USA"));
                driver.close();
            }
        }
        driver.switchTo().window(origWindow);
        Clicks.click("our_stores_about_us.community_relations");
        Wait.forPageReady();
        allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Community Relations' Page is not displayed. Actual Page displayed " + title, title.equals("Giving Back to Our Communities - Macy’s, Inc."));
                driver.close();
            }
        }
        driver.switchTo().window(origWindow);
        Clicks.click("our_stores_about_us.macys_inc");
        Wait.forPageReady();
        allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Macy's Inc.' Page is not displayed. Actual Page displayed " + title, title.equals("Macy’s, Inc."));
                driver.close();
            }
        }
        driver.switchTo().window(origWindow);
        Clicks.click("our_stores_about_us.affiliate_program");
        Wait.forPageReady();
        assertTrue("'Affiliate Program' Page is not displayed. Actual Page displayed " + title(), title().equals("Become a Macy's Affiliate Today! - Macy's"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("our_stores_about_us.diversity");
        Wait.forPageReady();
        allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Diversity' Page is not displayed. Actual Page displayed " + title, title.equals("Diversity Leadership - Macy’s, Inc."));
                driver.close();
            }
        }
    }

    @Given("^I am on the Shopping Services Page as a guest user$")
    public void iAmOnTheShoppingServicesPageAsAGuestUser() {
        iAmOnTheOurStoresPageAsGuestUser();
        Clicks.click("our_stores.shopping_services_see_services");
        Wait.forPageReady();
        shouldBeOnPage("shopping_services");
    }

    @Then("^I navigate and render the links of our stores shopping services page$")
    public void iNavigateAndRenderTheLinksOfOurStoresShoppingServicesPage() {
        Wait.forPageReady();
        String origWindow = WebDriverManager.getWebDriver().getWindowHandle();
        String title = "";
        WebDriver driver = WebDriverManager.getWebDriver();
        Clicks.click("shopping_services.personal_shoppers");
        Wait.forPageReady();
        assertTrue("'Personal Shoppers' Page is not displayed. Actual Page displayed " + title(), title().equals("My Stylist @ Macy's - Book an Online Appointment - Macy's"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("shopping_services.the_bridal_salon");
        Wait.forPageReady();
        assertTrue("'The Bridal Salon at Macy's' Page is not displayed. Actual Page displayed " + title(), title().equals("Macy's Registry"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("shopping_services.corporate_sales");
        Wait.forPageReady();
        assertTrue("'Corporate Sales' Page is not displayed. Actual Page displayed " + title(), title().equals("Corporate Sales – Macy's"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("shopping_services.visitor_services");
        Wait.forPageReady();
        assertTrue("'Visitor Services' Page is not displayed. Actual Page displayed " + title(), title().equals("Visit Macy's USA | Visit Macy's USA"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("shopping_services.custom_shop_at_home_services");
        Wait.forPageReady();
        assertTrue("'Custom Shop At Home Services' Page is not displayed. Actual Page displayed " + title(), title().equals("Interior Design Consultation Services"));
        Navigate.browserBack();
        Wait.forPageReady();

        Clicks.click("shopping_services.travel_services");
        Wait.forPageReady();
        Set<String> allWinHandels = driver.getWindowHandles();
        for (String wh : allWinHandels) {
            if (!origWindow.equals(wh)) {
                driver.switchTo().window(wh);
                title = driver.getTitle();
                assertTrue("'Travel Services' Page is not displayed. Actual Page displayed " + title, title.equals("Carlson Wagonlit Travel"));
                driver.close();
            }
        }
    }

    @Given("^I navigated to the Our Stores Page as a guest user$")
    public void iNavigateToTheOurStoreEventsPageAsAGuestuser() {
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        Clicks.click("footer.goto_store_events");
        Wait.forPageReady();
        shouldBeOnPage("store_events");
    }

    @When("^I navigate to the \"Store Locations & Hours\" in our stores page$")
    public void iNavigateToTheStoreLocationsHoursInOurSoresPage() {
        Clicks.click("footer.goto_store_locations_and_hours");
//        Clicks.click("footer.goto_our_stores");
//        Wait.forPageReady();
//        Clicks.click("our_stores.search_now");
        Wait.forPageReady();
    }

    @Then("I verify that the \"Store Locations & Hours\" our stores page has rendered")
    public void iVerifyThatTheStoreLocationsHoursOurStoresPageHasRendered() {
        shouldBeOnPage("store_locations_hours");
    }

    @And("I verify the functionality in \"Store Locations & Hours\" page")
    public void iVerifyTheFunctionalityInStoreLocationsHoursPage() throws Throwable {
        String testZipCode = "94588";
        TextBoxes.typeTextbox("store_locations_hours.store_search_field", testZipCode);
        Clicks.click("store_locations_hours.search_button");
        Thread.sleep(2000);
        Wait.forPageReady();
        assertTrue("All the 10 stores of Zip Code 94588 are not displayed. Total Store displayed are " + Elements.findElements("store_locations_hours.store_names").size(), Elements.findElements("store_locations_hours.store_names").size() == 13);
        assertTrue("All the 10 store addresses of Zip Code 94588 are not displayed. Total Store displayed are " + Elements.findElements("store_locations_hours.store_addresses").size(), Elements.findElements("store_locations_hours.store_addresses").size() == 13);
        assertTrue("All the 10 store 'Hours' links of Zip Code 94588 are not displayed. Total Store Hours displayed are " + Elements.findElements("store_locations_hours.store_hours").size(), Elements.findElements("store_locations_hours.store_hours").size() == 13);
        assertTrue("All the 10 store 'Get Directions' links of Zip Code 94588 are not displayed. Total 'Get Directions' links displayed are " + Elements.findElements("store_locations_hours.get_directions").size(), Elements.findElements("store_locations_hours.get_directions").size() == 13);

        Elements.findElements("store_locations_hours.visit_site").get(0).click();
        Thread.sleep(2000);
        assertTrue("Store site page is not displayed", WebDriverManager.getCurrentUrl().contains("cm_sp=localmacys-_-visit-site"));

    }

    @When("I enter a zip code and search on our Stores Events Page")
    public void iEnterAZipCodeAndSearchOnOurStoresEventsPage() {
        String testZipCode = "94588";
        TextBoxes.typeTextbox("store_events.search_zip", testZipCode);
        Clicks.click("store_events.search_events_button");
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("store_events.event_dates", 60);
    }

    @Then("I verify date and timing of the event should be displayed in sequential pattern")
    public void iVerifyDateAndTimingOfTheEventShouldBeDisplayedInSequentialPattern() throws Throwable {

        Clicks.click("store_events.sort_by_date");
        Wait.forPageReady();
        Thread.sleep(2000);
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        List<Date> listEventDates = new ArrayList();
        List<Date> listEventDatesBeforeSort = new ArrayList();
        for (WebElement eventDateElement : Elements.findElements("store_events.event_dates")) {
            listEventDates.add(format.parse(eventDateElement.getText()));
            listEventDatesBeforeSort.add(format.parse(eventDateElement.getText()));
        }
        listEventDates.sort((d1, d2) -> d1.compareTo(d2));
        assertTrue("Event Dates are not displayed in ascending order.", listEventDates.equals(listEventDatesBeforeSort));
    }

    @When("^I select \"stores\" link in home page$")
    public void iSelectStoresLinkInHomepage() {
//        Clicks.click("header.goto_stores");
        if (macys()) {
            Clicks.click("footer.goto_our_stores");
        } else {
            Clicks.click("footer.goto_stores");
        }
        Wait.forPageReady();
        shouldBeOnPage("store_finder");
    }

    @And("^I should see single input field displayed to enter location in store locations page$")
    public void iShouldSeeSignleInputFieldDisplayed() {
        assertTrue("Store Search input field not displayed on Store Locations & Hours page", Elements.elementPresent("store_locations_hours.find_locators_hours"));
    }

    @When("^I search using a zipcode \"([^\"]*)\" in store locations page$")
    public void iSearchUsingAZipCodeInStoreLocationsPage(String zipCode) throws Throwable {
        TextBoxes.typeTextbox("store_locations_hours.find_locators_hours", zipCode);
        Clicks.click("store_locations_hours.search_button");
        Thread.sleep(3000);
    }

    @Then("^I should see Filter by link in store locations page$")
    public void iShouldSeeFilterByLinkInStoreLocationsPage() {
        assertTrue("Filter By link is not displayed on Store Locations & Hours page", Elements.elementPresent("store_locations_hours.filter_by"));
    }

    @And("^I should be able to expand the advance search option$")
    public void iShouldBeAbleToExpandTheAdvanceSearchOption() {
        Clicks.click("store_locations_hours.filter_by");
        Wait.forPageReady();
        assertTrue("Filter by is not expanded on Store Locations & Hours page when clicked on Filter By link.", Elements.elementPresent("store_locations_hours.filter_by_up_arrow"));
    }

    @And("^I should see below advanced search features$")
    public void iShouldSeeBelowAdvancedSearchFeatures(List<String> expectedFilters) {
        List<String> actualFilters = new ArrayList<String>();

        for (WebElement filter : Elements.findElements("store_locations_hours.advanced_search_filters")) {
            actualFilters.add(filter.getText());
        }

        assertTrue("All Advanced Search Filters are not displayed. Actual Filters displayed - " + actualFilters + " Expected Filters " + expectedFilters, actualFilters.equals(expectedFilters));

    }

    @When("^I select \"([^\"]*)\" filter from the advanced search list$")
    public void iSelectFilterFromTheAdvancedSearchList(String filterName) {
        for (WebElement filter : Elements.findElements("store_locations_hours.advanced_search_filters")) {
            if (filter.getText().equals(filterName)) {
                filter.click();
                break;
            }
        }

    }

    @And("I select search button in store locations page")
    public void iSelectSearchButtonInStoreLocationsPage() throws Throwable {
        Clicks.click("store_locations_hours.search_button");
        Wait.forPageReady();
        Thread.sleep(3000);
    }

    @Then("I should see search results in store locations page with \"([^\"]*)\"")
    public void iShouldSeeSearchResultsInStoreLocationsPage(String filterName) {
        int totalSearchReasults = Elements.findElements("store_locations_hours.store_addresses_in_search_result").size();
        int totalFeatureLinks = 0;
        switch (filterName) {
            case "Visitor Services": {
                totalFeatureLinks = Elements.findElements("store_locations_hours.visitor_services_links").size();
                break;
            }
            case "Personal Shopper": {
                totalFeatureLinks = Elements.findElements("store_locations_hours.personal_shopper_links").size();
                break;
            }

            case "Restaurants": {
                totalFeatureLinks = Elements.findElements("store_locations_hours.restaurants_links").size();
                break;
            }
        }

        assertTrue("Correct number of search results are not displayed according to the filters. Total Search Results " + totalSearchReasults + ", But Actual Results with Filtered Feature - " + totalFeatureLinks, totalFeatureLinks == totalSearchReasults);

    }

    @And("I should be able to collapse the advance search option")
    public void iShouldBeAbleToCollapseTheAdvanceSearchOption() {
        Clicks.click("store_locations_hours.filter_by");
        Wait.forPageReady();
        assertTrue("Filter by is not collapsed on Store Locations & Hours page when clicked on Filter By link.", Elements.elementPresent("store_locations_hours.filter_by_down_arrow"));
    }

    @Then("^I should see \"No locations match your search, please try again\" error message in store locations page$")
    public void iShouldSeeNoLocationsMatchedYourSearch() {
        assertTrue("Expected Error Message - 'No locations match your search, please try again' is not displayed", Elements.getText("store_locations_hours.error_msg").equals("No locations match your search, please try again"));
    }

    @When("^I navigate to the store locations page$")
    public void iNavigateToTheStoreLocationsPage() {
        Clicks.click("footer.goto_store_locations_and_hours");
        Wait.forPageReady();
        assertTrue("Failed to navigete to Locations and Hours page.", onPage("store_locations_hours"));
    }

    @Then("^I should see search results in store locations page$")
    public void iShouldSeeSearchResultsInStoreLocationsPage() {
        assertTrue("Search results with matching store locations are not displayed.", Elements.findElements("store_locations_hours.store_addresses").size() > 0);
    }

    @Then("^I should see \"hours\" tab selected by default$")
    public void iShouldSeeHoursTapSelectedByDefault() {
        assertTrue("Hours tab is not selected by default.", Elements.findElement("store_locations_hours.hours_links").getAttribute("class").contains("active"));
    }

    @And("I should see store hours for the week")
    public void iShouldSeeStoreHoursForTheWeek() {
        assertTrue("Hours information is not shown for Monday", Elements.getText("store_locations_hours.hours_details").contains("Mon,"));
        assertTrue("Hours information is not shown for Tuesday", Elements.getText("store_locations_hours.hours_details").contains("Tue,"));
        assertTrue("Hours information is not shown for Wednesday", Elements.getText("store_locations_hours.hours_details").contains("Wed,"));
        assertTrue("Hours information is not shown for Thursday", Elements.getText("store_locations_hours.hours_details").contains("Thu,"));
        assertTrue("Hours information is not shown for Friday", Elements.getText("store_locations_hours.hours_details").contains("Fri,"));
        assertTrue("Hours information is not shown for Saturday", Elements.getText("store_locations_hours.hours_details").contains("Sat,"));
        assertTrue("Hours information is not shown for Sunday", Elements.getText("store_locations_hours.hours_details").contains("Sun,"));
    }

    @And("^I should see \"less\" arrow$")
    public void iShouldSeeLessArrow() {
        assertTrue("'less' link is not displayed", Elements.elementPresent("store_locations_hours.less_links"));
    }

    @When("^I click on \"features\" tab$")
    public void iClickOnFeaturesTab() {
        Elements.findElements("store_locations_hours.features_links").get(0).click();
        Wait.ajaxDone();
    }

    @Then("I should see list of store features displayed")
    public void iShouldSeeListOfStoreFeaturesDisplayed() {
        assertTrue("Features list is not displayed even after clicking on the 'features' link.", Elements.elementPresent("store_locations_hours.features_details_active"));
    }

    @Then("^I should see more arrow link is collapsed$")
    public void iShouldSeeMoreArrowLinkIsCollapsed() {
        assertTrue("'less' link is still displayed after clicking on less link.", !Elements.elementPresent("store_locations_hours.less_links"));
        assertTrue("Features list is still displayed even after clicking on the 'less' link.", !Elements.elementPresent("store_locations_hours.features_details_active"));
    }

    @Then("^I click \"less arrow\" in a store from store results$")
    public void iClickLessArrowInAStoreFromStoreResults() {
        Clicks.click("store_locations_hours.less_links");
        Wait.ajaxDone();
    }

    @And("the URL should include \"/shop\"")
    public void TheURLShouldIncludeShop() {
        assertTrue("Our Stores URL does not have '/shop' in it.", WebDriverManager.getCurrentUrl().contains("/shop"));
    }

    @And("^I should see greyed out text \"Find locations near...\" in the input field$")
    public void iShouldSeeGreyedOutTextFindLocationsNear() {
        assertTrue("Location Search TextBox is not displayed with default value 'Find locations near...'", Elements.findElement("store_locations_hours.find_locators_hours").getAttribute("placeholder").equals("Find locations near..."));
    }

    @When("^I place cursor on the input field$")
    public void iPlacedCursorOnTheInputField() {
        Elements.findElement("store_locations_hours.find_locators_hours").click();
        Wait.forPageReady();
    }

    @Then("^the input field should be blank$")
    public void theInputFieldShouldBeBlank() {
        assertTrue("Location Search TextBox is not empty when cursor is placed on it", Elements.findElement("store_locations_hours.find_locators_hours").getAttribute("value").equals(""));
    }

    @When("^I search using \"([^\"]*)\" in \"store locations\" page$")
    public void iSearchUsingSearchCriteriaInStoreLocationsPage(String searchValue) throws Throwable {

        TextBoxes.typeTextbox("store_locations_hours.find_locators_hours", searchValue);
        Clicks.click("store_locations_hours.search_button");
        Wait.forPageReady();
        Thread.sleep(3000);

    }

    @Then("^I should see search results for \"([^\"]*)\" in store locations page$")
    public void iShouldSeeSearchResultsForSearchCriteria(String searchCriteria) {
        assertTrue("Search results are not displayed for the cirteria - " + searchCriteria, Elements.findElements("store_locations_hours.store_addresses").size() == 10);
    }

    @When("^I select link \"Corporate Sales\" in stores page$")
    public void iSelectCorporateSalesLinksInStoresPage() {
        Clicks.click("our_stores.corporate_sales");
        Wait.forPageReady();
    }

    @Then("^I should be redirected to \"Corporate Sales\" with store \"/splash/corporate\" URL$")
    public void iShouldBeRedirectedToCorporateSales() {
        assertTrue("Corporate Sales link is not redirected to /store/corporate page.", WebDriverManager.getCurrentUrl().contains("/splash/corporate"));
    }

    @When("^I select \"OUR STORES\" link in home page$")
    public void iSelectOurStoresLinkInHomePage() {
        Clicks.click("footer.goto_our_stores");
        Wait.forPageReady();
    }

    @Then("the URL should be in \"/shop/\" format")
    public void theURLShouldBeInShopFormat() {
        assertTrue("OUR STORES link is not redirected to /shop/ corporate page.", WebDriverManager.getCurrentUrl().contains("/shop/"));
    }

    @When("I select \"([^\"]*)\" links in stores page")
    public void iSelectStoreLinksInStoresPage(String linkName) {
        switch (linkName) {
            case "About Us": {
                Clicks.click("our_stores.about_us");
                Wait.forPageReady();
                break;
            }
            case "Macy's Event Marketing": {
                Clicks.click("our_stores.macys_event_marketing");
                Wait.forPageReady();
                break;
            }
            case "go now": {
                Clicks.click("our_stores.browse_catalog_go_now");
                Wait.forPageReady();
                break;
            }

            case "Join The Fun": {
                Clicks.click("our_stores.join_the_fun");
                Wait.forPageReady();
                break;
            }

            case "find an event": {
                Clicks.click("our_stores.find_an_event");
                Wait.forPageReady();
                break;
            }
            case "search now": {
                Clicks.click("our_stores.search_now");
                Wait.forPageReady();
                break;
            }
            case "see services": {
                Clicks.click("our_stores.shopping_services_see_services");
                Wait.forPageReady();
                break;
            }

        }
    }

    @Then("I should be redirected to \"([^\"]*)\" with store \"([^\"]*)\" URL and breadcrumb")
    public void iShouldBeRedirectedToIconLinkWithStoreURL(String linkName, String linkURL) {
        switch (linkName) {
            case "About Us": {
                assertTrue("About Us link is not navigated to /store/about", WebDriverManager.getCurrentUrl().contains(linkURL));
                assertTrue("About Us page Breadcrumb is not displayed.", Elements.getText("our_stores_about_us.breadcrumb").equals("Our Stores > About Us"));
                break;
            }
            case "Macy's Event Marketing": {
                assertTrue("Macy's Event Marketing link is not navigated to /store/marketing.jsp", WebDriverManager.getCurrentUrl().contains(linkURL));
                assertTrue("Macy's Event Marketing page Breadcrumb is not displayed.", Elements.getText("macys_event_marketing.breadcrumb").equals("Our Stores > Macy's Event Marketing"));
                break;
            }

        }
    }

    @And("^I should see label \"Browse Catalogs\" in Stores Page$")
    public void iShouldSeeBrowseCatalogsLabelInStoresPage() {
        assertTrue("Browse Catalogs label is not displayed. Actual Label displayed is " + Elements.getText("our_stores.browse_catalogs_banner"), Elements.getText("our_stores.browse_catalogs_banner").equals("BROWSE CATALOGS"));
    }

    @Then("I should be redirected to \"Catalogs page\" with store \"http://macys.circularhub.com/flyer_selector/macys\" URL")
    public void iShouldBeRedirectedToCatalogsPageWithStoreURL() {
        assertTrue("Browse Catalogs link is not navigated to http://macys.circularhub.com/flyer_selector/macys", WebDriverManager.getCurrentUrl().contains("http://macys.circularhub.com/flyer_selector/macys"));
    }

    @And("^I should see label \"What's Happening at Macy's\" in Stores Page$")
    public void iShouldSeeLabelInStorepage() {
        assertTrue("'What's Happening at Macy's' label is not displayed on Our Stores Page.", Elements.findElement("our_stores.whats_happening_at_macys_banner").getAttribute("src").contains("whats_happening_at_macys"));
    }

    @Then("I should be redirected to \"What's Happening at Macy's page\" with store \"/splash/corporate/campaigns\" URL")
    public void iShouldBeRedirectedToWhatsHappeningPageWithStoreURL() {
        assertTrue("'Join For Free' link of What's Happening at Macy's section is not navigated to /splash/corporate/campaigns", WebDriverManager.getCurrentUrl().contains("/splash/corporate/campaigns"));
    }

    @Then("I should see error message \"Oops! We couldn't find what you're looking for.\" displayed in the events search page")
    public void iShouldSeeErrorMessageWeCouldntFind() {
        assertTrue("'Join For Free' link of What's Happening at Macy's section is not navigated to /splash/corporate/campaigns", Elements.getText("store_events.oops_we_couldnt_find").equals("Oops! We couldn't find what you're looking for."));
    }

    @And("I should see \"([^\"]*)\" label in Stores Page")
    public void iShouldSeeLableInStoresPage(String labelName) {
        switch (labelName) {
            case "Find a Store": {
                assertTrue("'Find a Store' label is not displayed on Our Stores Page.", Elements.findElement("our_stores.find_a_store_banner").getAttribute("src").contains("find_a_store"));
            }
            case "Shopping Services": {
                assertTrue("'Find a Store' label is not displayed on Our Stores Page.", Elements.getText("our_stores.shopping_services_banner").equals("SHOPPING SERVICES"));
            }
            case "Events Near You": {
                assertTrue("'Find a Store' label is not displayed on Our Stores Page.", Elements.findElement("our_stores.find_store_events_near_you_banner").getAttribute("src").contains("local_events"));
            }
        }
    }

    @Then("^I should be able to redirect to \"([^\"]*)\" with store \"([^\"]*)\" URL$")
    public void iShouldBeRedirectedToStorePageWithURL(String landingPage, String expURL) {
        switch (landingPage) {
            case "Store locator page": {

                assertTrue(landingPage + "is not displayed on Our Stores Page.", WebDriverManager.getCurrentUrl().contains(expURL));
            }
            case "Shopping services page": {
                assertTrue(landingPage + "  is not displayed on Our Stores Page.", WebDriverManager.getCurrentUrl().contains(expURL));
            }
            case "Events page": {
                assertTrue(landingPage + " is not displayed on Our Stores Page.", WebDriverManager.getCurrentUrl().contains(expURL));
            }
        }
    }

    @And("^I should see breadcrumb in \"([^\"]*)\"$")
    public void iShouldSeeBreadcrum(String landingPage) {
        switch (landingPage) {
            case "Store locator page": {
                assertTrue(landingPage + " breadcrum is not displayed correctly", Elements.getText("store_locations_hours.breadcrumb").equals("Our Stores > Store Location & Hours"));
                break;
            }
            case "Shopping services page": {
                assertTrue(landingPage + " breadcrum is not displayed correctly", Elements.getText("shopping_services.breadcrumb").equals("Our Stores > Shopping Services"));
                break;
            }
            case "Events page": {
                // no bread crum for Events page
            }
        }
    }

    @When("I search for stores with \"McLean\" as state in the \"events search\" page")
    public void iSearchForStores() throws Throwable {
        TextBoxes.typeTextbox("store_events.search_zip", "McLean");
        Clicks.click("store_events.search_events_button");
        Wait.forPageReady();
        Thread.sleep(3000);

    }

    @Then("I verify various sections on Event Search Results page")
    public void iVerifyVariousSectionsOnEventSearchResultsPage() {
        int resultCount = Integer.parseInt(Elements.getText("store_events.results_count"));
        assertTrue("Search results summary with result count, search criteria are not displayed", Elements.getText("store_events.results_summary").contains("results for McLean Refine Search"));
        assertTrue("Search results sort by Distance link not displayed", Elements.getText("store_events.sort_by_distance").equals("Distance"));
        assertTrue("Search results sort by Date link not displayed", Elements.getText("store_events.sort_by_date").equals("Date"));
        assertTrue("All Search Results does not have its Event Name. Actual number of Event Names " + Elements.findElements("store_events.event_names").size(), Elements.findElements("store_events.event_names").size() == resultCount);
        assertTrue("All Search Results does not have its Event Description. Actual number of Event Descriptions " + Elements.findElements("store_events.event_descriptions").size(), Elements.findElements("store_events.event_descriptions").size() == resultCount);
        assertTrue("All Search Results does not have its Event Date. Actual number of Event Dates " + Elements.findElements("store_events.event_dates").size(), Elements.findElements("store_events.event_dates").size() == resultCount);
        assertTrue("All Search Results does not have its Event Date. Actual number of Event Addresses " + Elements.findElements("store_events.event_addresses").size(), Elements.findElements("store_events.event_addresses").size() == resultCount);
        assertTrue("All Search Results does not have its More Event Details link. Actual number of More Event Details links " + Elements.findElements("store_events.more_event_details_links").size(), Elements.findElements("store_events.more_event_details_links").size() == resultCount);
    }

    @Then("^I verify various sections displayed on Store Events page.$")
    public void iVerifyVariousSectionsDisplayedOnStoreEventsPage() {
        assertTrue("Magical Events banner is not displayed on Store Events page.", Elements.elementPresent("store_events.events_banner"));
        assertTrue("Key Search field is not displayed on Store Events page.", Elements.elementPresent("store_events.search_key"));
        assertTrue("Location search icon is not displayed on Store Events page.", Elements.elementPresent("store_events.search_icon"));
        assertTrue("Zipcode search field is not displayed on Store Events page.", Elements.elementPresent("store_events.search_zip"));
        assertTrue("More Options dropdown is not displayed on Store Events page.", Elements.elementPresent("store_events.more_options"));
        assertTrue("Search Events button is not displayed on Store Events page.", Elements.elementPresent("store_events.search_events_button"));
        assertTrue("Comming soon banner is not displayed on Store Events page.", Elements.elementPresent("store_events.coming_soon"));
        assertTrue("'What's going on near you' banner is not displayed on Store Events page.", Elements.elementPresent("store_events.whats_going_on_near_you"));
        assertTrue("'Enter City or Zip' field is not displayed on Store Events page.", Elements.elementPresent("store_events.enter_city_or_zip"));
        assertTrue("'See More' link is not displayed on Store Events page.", Elements.elementPresent("store_events.see_more"));
        assertTrue("'Keep It Going' banner is not displayed on Store Events page.", Elements.elementPresent("store_events.keep_it_going"));
        assertTrue("'Show All Events' link is not displayed on Store Events page.", Elements.elementPresent("store_events.show_all_events"));
        assertTrue("'Lets Stay Connected' banner is not displayed on Store Events page.", Elements.elementPresent("store_events.lets_stay_connected"));


    }

    @Given("^I visit my account page as a signed user$")
    public void iVisitMyAccountPageAsASignedUser() throws Throwable {
        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        Clicks.click("header.goto_my_account_link");
        Wait.forPageReady();
        shouldBeOnPage("my_account");
    }

    @Then("^I verify the basic attributes of the my preferences section on My Account Page$")
    public void iVerifyTheBasicAttributesOfTheMyPreferencesSectionOnMyAccountPage() {
        assertTrue("Preferences Header icon is not displayed.", Elements.elementPresent("my_account.preferences_header_icon"));
        assertTrue("Preferences Header text is not displayed.", Elements.getText("my_account.preferences_header").equals("Preferences"));
        assertTrue("Preferences Header caption is not displayed.", Elements.getText("my_account.preferences_caption").equals("View & update your preferred store and notification preferences—plus, tell us about your shopping habits!"));
        assertTrue("Categories, Brands & Sizes link is not displayed under Preferences section.", Elements.getText("my_account.categories_brands_sizes").equals("Categories, Brands & Sizes"));
        assertTrue("Notification Methods link is not displayed under Preferences section.", Elements.getText("my_account.notification_methods").equals("Notification Methods"));
        assertTrue("Preferred Store link is not displayed under Preferences section.", Elements.getText("my_account.preferred_store").equals("Preferred Store"));
        assertTrue("View Preferences link is not displayed under preferences section.", Elements.getText("my_account.view_preferences").equals("View Preferences"));
    }

    @Then("^I verify the My Account Page \"View Preferences\" link rendered properly$")
    public void iVerifyTheMyAccountPageViewPreferencesLinkRenderedProperly() {
        Clicks.click("my_account.view_preferences");
        Wait.forPageReady();
        assertTrue("'Preferences' page is not displayed when clicked on View Preferences link.", WebDriverManager.getCurrentUrl().contains("account/preferences"));
    }

    @Then("^I verify the My Account Pages for below links are rendered properly$")
    public void iVerifyTheMyAccountPagesForBelowLinksAreRenderedProperly(List<String> linkNames) throws Throwable {
        for (String linkName : linkNames) {
            switch (linkName) {
                case "locations & hours": {
                    Clicks.click("footer.goto_store_locations_and_hours");
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    assertTrue("Locations and Hours page is not displayed when clicked on " + linkName, WebDriverManager.getCurrentUrl().contains("/shop/store/search"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                    break;
                }
                case "store events": {
                    Clicks.click("footer.goto_store_events");
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    assertTrue("'store events' page is not displayed when clicked on " + linkName, WebDriverManager.getCurrentUrl().contains("/social/events"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                    break;
                }
                case "catalogs": {
                    Clicks.click("footer.goto_store_catalogs");
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    assertTrue("'catalogs' page is not displayed when clicked on " + linkName, WebDriverManager.getCurrentUrl().contains("/flyer_selector/macys"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                    break;
                }
                case "tell us what you think": {
                    Clicks.click("footer.goto_tell_us_what_you_think");
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    assertTrue("'tell us what you think' page is not displayed when clicked on " + linkName, WebDriverManager.getCurrentUrl().contains("/app/answers/detail"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                    break;
                }
                case "macy's backstage": {
                    Clicks.click("footer.goto_back_stage");
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    assertTrue("'macy's backstage' page is not displayed when clicked on " + linkName, WebDriverManager.getCurrentUrl().contains("macysbackstage.com/home"));
                    Navigate.browserBack();
                    Navigate.browserBack();
                    Wait.forPageReady();
                    break;
                }
                case "my stylist personal shopping": {
                    Clicks.click("footer.my_stylist_personal_shopping");
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    assertTrue("'my stylist personal shopping' page is not displayed when clicked on " + linkName, WebDriverManager.getCurrentUrl().contains("/social/my-stylist"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                    break;
                }
            }
        }
    }

    @When("^I navigate to gift card balance page$")
    public void iNavigateToGiftCardBalancePage() {
        Clicks.click("my_account.gift_cards");
        Wait.forPageReady();
        assertTrue("'Gift Card Balance' page is not displayed when clicked on Gift Cards link", WebDriverManager.getCurrentUrl().contains("account/giftcardbalance"));
    }

    @Then("^I verify the message displayed on gift card balance page$")
    public void iVerifyTheMessageDisplayedOnGiftCardBalancePage() {
        assertTrue("'Service Unavailable' error message is not displayed on Gift Card Balance page", Elements.getText("gift_card_balance.service_unavailable_error").equals("We're sorry. Our Electronic Gift Card inquiry services are currently unavailable. To obtain information on your Gift Card, please call 1-800-511-2752."));
    }

    @Then("I Verify the Wallet icon and header")
    public void iVerifyTheWalletIconAndHeader() {
        Wait.secondsUntilElementPresent("my_account.wallet_icon",10);
        assertTrue("'Wallet' header icon is not displayed on My Account Page.", Elements.elementPresent("my_account.wallet_icon"));
        assertTrue("'Wallet' header text is not displayed on My Account Page.", Elements.getText("my_account.wallet_header").equals("Wallet"));

    }

    @And("^Offers: section with caption text and 'Deals & Promotions' link$")
    public void iVerifyOffersSubSectionOfWalletSection() {
        assertTrue("'Offers' text is not displayed on My Account Page.", Elements.getText("my_account.offers_text").equals("Offers:\n" +
                "You have no offers saved. To view and add offers, visit Deals & Promotions"));
        Clicks.click("my_account.deals_and_promotions");
        Wait.forPageReady();
        assertTrue("'Deals & Promotions' is not displayed when clicked on Deals and Promotions link of Wallet section on My Account page.", onPage("deals_and_promotions"));
        Navigate.browserBack();
        Wait.forPageReady();
    }

    @And("^Credit card section with plus icon and 'Add A Payment Method' link$")
    public void iVerifyCreditCardSubSection() {
        Wait.secondsUntilElementPresent("my_account.add_a_payment_icon",10);
        assertTrue("'+' icon to add payment method is not displayed in Wallet section of My Account page.", Elements.elementPresent("my_account.add_a_payment_icon"));
        assertTrue("'Add A Payment Method' link is not displayed in Wallet section of My Account page.", Elements.getText("my_account.add_a_payment_method").equals("Add A Payment Method"));
        Clicks.click("my_account.add_a_payment_method");
        Wait.forPageReady();
        Wait.ajaxDone();
        assertTrue("'Add a Card' Overlay is not displayed when clicked on 'Add A Payment Method' link of Wallet section on My Account page.", Elements.elementPresent("my_account.add_a_payment_method_overlay"));
        Clicks.click("my_account.overlay_close");
    }

    @And("^Wallet section footer links 'Wallet' and 'Gift Cards'$")
    public void iVerifyWalletSectionFooterLinksWalletAndGiftCards() {
        assertTrue("'Wallet' link is not displayed in Wallet section of My Account page.", Elements.getText("my_account.wallet").equals("Wallet"));
        assertTrue("'Gift Cards' link is not displayed in Wallet section of My Account page.", Elements.getText("my_account.gift_cards").equals("Gift Cards"));
        Clicks.click("my_account.wallet");
        Wait.forPageReady();
        shouldBeOnPage("oc_my_wallet");

    }

    @Then("I Verify the Plenti icon and header")
    public void iVerifyThePlentiIconAndheader() {
        assertTrue("Plenti image is not displayed on My Account page Plenti section.", Elements.elementPresent("my_account.plenti_brand"));
        assertTrue("Plenti header is not displayed on My Account page Plenti section.", Elements.getText("my_account.plenti_header").equals("Plenti"));
    }

    @And("^I Verify Phone Number tab and section$")
    public void iVerifyPhoneNumberTabAndSection() {
        assertTrue("'Phone Number' tab is not displayed in Plenti section.", Elements.elementPresent("my_account.plenti_phone_lookup_tab"));
        assertTrue("'Phone Number' text box is not displayed in Plenti section.", Elements.elementPresent("my_account.plenti_phone_lookup_textfield"));
        assertTrue("'Add Plenti Account' button is not displayed in Plenti section.", Elements.elementPresent("my_account.plenti_lookup_button"));
    }

    @And("^I Verify Plenti Number tab and section$")
    public void iVerifyPlentiNumberTabAndSection() {
        assertTrue("'Plenti Number' tab is not displayed in Plenti section.", Elements.elementPresent("my_account.plenti_lookup_tab"));
        assertTrue("'Plenti Number' text box is not displayed in Plenti section.", Elements.elementPresent("my_account.plenti_lookup_textfield"));
    }

    @And("^I Verify join our program text and Learn More link$")
    public void iVerifyJoinOurProgramTextAndLearnMoreLink() {
        assertTrue("'Join our rewards program' text is not displayed in Plenti section.", Elements.getText("my_account.join_our_rewards_program").equals("Join our rewards program.\n" +
                "Learn More"));
        assertTrue("'Learn More' link is not displayed in Plenti section.", Elements.elementPresent("my_account.plenti_learn_more"));
        Clicks.click("my_account.plenti_learn_more");
        Wait.forPageReady();
        assertTrue("'Plenti Welcome' page is not displayed when clicked on 'Learn More' link", WebDriverManager.getCurrentUrl().contains("plenti/welcome"));
    }

    @When("^I navigate to the Gift Card Balance page from My Account$")
    public void iNavigateToTheGiftCardBalancePageFromMyAccount() {
        Clicks.click("my_account.gift_cards");
        Wait.forPageReady();
        shouldBeOnPage("gift_card_balance");
    }

    @Then("^I should see basic attributes in Gift card balance page$")
    public void iShouldSeeBasicAttributesInGiftCardBalancePage() {
        assertTrue("'My Account>Gifts & Gift Cards' bread crumb is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.bread_crumb").equals("My Account>\n" +
                "Gifts & Gift Cards"));
        assertTrue("'Gifts & Gift Cards' header is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.gifts_gift_cards_header").equals("Gifts & gift cards"));
        assertTrue("'gift card balance' tab is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.gift_card_balance_tab").equals("gift card balance"));
        assertTrue("'gift boxes & messages' header is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.gift_boxes_messages_tab").equals("gift boxes & messages"));
        assertTrue("'terms & conditions' header is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.terms_conditions_tab").equals("terms & conditions"));
    }

    @And("^I navigate to the Gift boxes and messages tab in Gift Card Balance page$")
    public void iNavigateToTheGiftBoxesAndMessagesTabInGiftCardBalancePage() {
        Clicks.click("gift_card_balance.gift_boxes_messages_tab");
        Wait.forPageReady();
    }

    @Then("^I should see basic attributes of Gift boxes and messages tab$")
    public void iShouldSeeBasicAttributesOfGiftBoxesAndmessagesTab() {
        assertTrue("'My Account>Gifts & Gift Cards' bread crumb is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.bread_crumb").equals("My Account>\n" +
                "Gifts & Gift Cards\n" +
                ">Gift Boxes & Messages"));
        assertTrue("'let us do the work for you!' sub header is not displayed correctly on gift boxes & messages page.", Elements.getText("gift_card_balance.let_us_do_the_work_for_you").equals("let us do the work for you!"));
        assertTrue("'Gift Box' image is not displayed correctly on gift boxes & messages page.", Elements.elementPresent("gift_card_balance.gift_box_image"));
        assertTrue("'gift boxes & messages' tab content is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.tabs_content").equals("let us do the work for you!\n" +
                "Gift boxing\n" +
                "Great gifts come in great packages! For only $6 per address, we'll place each item* being shipped in a beautiful white box and tie it with a silky white bow.\n" +
                "Personalized gift messages\n" +
                "Add a personalized gift message during checkout! Registered customers, choose \"ADD/CHANGE GIFT OPTIONS\" on the Order Summary page. Unregistered Customers may choose gift options on the Shipping Details page.\n" +
                "Remember: whether or not you choose to add a gift box, you always have the option to add a personalized gift message, free!\n" +
                "*Some larger items and items shipped directly from the vendor cannot be gift boxed. These items are clearly marked where they appear on the macys.com site. Similar items may be placed in the same gift box."));
    }

    @And("^I navigate to the Terms and Conditions tab in Gift Card Balance page$")
    public void iNavigateToTheTermsAndConditionsTabInGiftCardBalancePage() {
        Clicks.click("gift_card_balance.terms_conditions_tab");
        Wait.forPageReady();
    }

    @Then("I should see basic attributes of Terms and Conditions tab")
    public void iShouldSeeBasicAttributesOfTermsAndConditionsTab() {
        assertTrue("'My Account>Gifts & Gift Cards' bread crumb is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.bread_crumb").equals("My Account>\n" +
                "Gifts & Gift Cards\n" +
                ">Terms & Conditions"));
        assertTrue("'gift boxes & messages' tab content is not displayed correctly on Gift Card Balance page.", Elements.getText("gift_card_balance.tabs_content").equals("Gift Cards\n" +
                "For balance or customer service go to macys.com/gcbal or any Macy's store, or call 1-800-511-2752. Macy's cards are redeemable only for merchandise and in-store services at Macy's and macys.com (US only); they may not be redeemed for cash (except as required by law) or applied as payment or credit to any credit card account. Macy's cards may be purchased as a gift or given in place of issuing a credit to a credit card or a proof of purchase return from another payment method. Returns may be applied to Macy's card. Card receipts will show any remaining balance. Protect the card: the bearer is responsible if it is lost or stolen. Lost or stolen cards will only be replaced with proof of purchase and only for the value shown on Macy's records.\n" +
                "This card is issued by Macy's Gift Card, LLC and is required for all inquiries.\n" +
                "Macy's Gift cards sold on or after 2/3/08 do not expire!\n" +
                "No fees are imposed.\n" +
                "E-Gift Cards\n" +
                "Your macys.com E-Gift Card number may be used to purchase any merchandise on-line at macys.com (US), and in-store by following the instructions in the E-Gift Card email. You may not add value back onto the E-Gift Card, nor redeem it for cash or apply it as payment or credit to your credit card account. When you make a purchase with your macys.com E-Gift Card number, the value of your purchase plus any shipping/handling fees and sales tax, if applicable, will be automatically deducted from your \"open to buy\". You may check any remaining value via the online Balance Inquiry function, or in-store by scanning the barcode at a price checker terminal, or by calling 1-800-511-2752.\n" +
                "Please safeguard your macys.com E-Gift Card number. The bearer is responsible for its loss or theft. If your E-Gift Card is lost or stolen, and you have proof of purchase, we will issue you a replacement for the balance shown on our records. Your macys.com E-Gift Card number is required for all inquiries.\n" +
                "Your macys.com E-Gift Card never expires."));

    }

    @When("^I select the \"contact us\" link in the footer$")
    public void iSelectTheContactUsLinkInTheFooter() {
        Clicks.click("footer.goto_contact_us");
        Wait.forPageReady();
        assertTrue("'Contact Us' footer link is not navigated to Contact Us page successfully.", WebDriverManager.getCurrentUrl().contains("app/contact?cm_sp=navigation-_-bottom_nav-_-contact_us"));
    }

    @Then("^I verify contact us page links$")
    public void iVerifyContactusPageLinks() {
        assertTrue("'Email Us' header is not displayed on Contact Us page.", Elements.getText("contact_us.email_us_header").equals("Email Us"));
        assertTrue("'Email Us' button is not displayed on Contact Us page.", Elements.elementPresent("contact_us.email_us_button"));
        assertTrue("'Contact us through our secure online form.' text is not displayed on Contact Us page.", Elements.getText("contact_us.contact_us_through").equals("Contact us through our secure online form."));
        assertTrue("'Stores' header is not displayed on Contact Us page.", Elements.getText("contact_us.stores_header").equals("Stores"));
        assertTrue("'Contact A Store' link is not displayed on Contact Us page.", Elements.getText("contact_us.contact_a_store").equals("Contact a store"));
        assertTrue("'Tell Us About A Store Experience' link is not displayed on Contact Us page.", Elements.getText("contact_us.tell_us_about_a_store_experience").equals("Tell us about a store experience"));
        assertTrue("'Macy's Credit Card' header is not displayed on Contact Us page.", Elements.getText("contact_us.macys_credit_card_header").equals("Macy's Credit Card"));
        assertTrue("'Pay Your Bill' link is not displayed on Contact Us page.", Elements.getText("contact_us.pay_your_bill").equals("Pay your bill"));
        assertTrue("'View Your Credit Account' header is not displayed on Contact Us page.", Elements.getText("contact_us.view_your_credit_account").equals("View your credit account"));
        assertTrue("'Tell Us About A Credit Card Experience' header is not displayed on Contact Us page.", Elements.getText("contact_us.tell_us_about_a_credit_card_experience").equals("Tell us about a credit card experience"));
        assertTrue("'Contact Credit Services' header is not displayed on Contact Us page.", Elements.getText("contact_us.contact_credit_services").equals("Contact credit services"));

        assertTrue("'Message Us' header is not displayed on Contact Us page.", Elements.getText("contact_us.message_us_header").equals("Message Us"));
        assertTrue("'Chat With Us' link is not displayed on Contact Us page.", Elements.getText("contact_us.chat_with_us").equals("Chat With Us"));
        assertTrue("'Email Us' link is not displayed on Contact Us page.", Elements.getText("contact_us.email_us").equals("Email Us"));
        assertTrue("'Facebook' link is not displayed on Contact Us page.", Elements.getText("contact_us.facebook").equals("Facebook"));
        assertTrue("'Twitter' link is not displayed on Contact Us page.", Elements.getText("contact_us.twitter").equals("Twitter"));
        assertTrue("'Call Us' header is not displayed on Contact Us page.", Elements.getText("contact_us.call_us_header").equals("Call Us"));
        assertTrue("'1.800.BUY.MACY (1-800-289-6229)' text is not displayed on Contact Us page.", Elements.getText("contact_us.call_us_number").equals("1.800.BUY.MACY\n" +
                "(1-800-289-6229)"));
        assertTrue("'We are available..' text is not displayed on Contact Us page.", Elements.getText("contact_us.we_are_available").equals("We're available:\n" +
                "24 Hours a Day\n" +
                "7 Days a Week\n" +
                "Click here for additional departments and hours."));

        assertTrue("'Registry' header is not displayed on Contact Us page.", Elements.getText("contact_us.registry").equals("Registry"));
        assertTrue("'Help With Your Wedding' link is not displayed on Contact Us page.", Elements.getText("contact_us.help_with_your_wedding").equals("Help with your wedding or gift registry"));
        assertTrue("'Order Questions' header is not displayed on Contact Us page.", Elements.getText("contact_us.order_questions").equals("Order Questions"));
        assertTrue("'Help Placing An Order' link is not displayed on Contact Us page.", Elements.getText("contact_us.help_placing_an_order").equals("Help placing an order"));
        assertTrue("'Change An Order' link is not displayed on Contact Us page.", Elements.getText("contact_us.change_an_order").equals("Change an order"));
        assertTrue("'Check Order Status' link is not displayed on Contact Us page.", Elements.getText("contact_us.check_order_status").equals("Check order status"));
        assertTrue("'Warranty Information' link is not displayed on Contact Us page.", Elements.getText("contact_us.warranty_information").equals("Warranty information"));
        assertTrue("'Tell Us About An Online Experience' link is not displayed on Contact Us page.", Elements.getText("contact_us.Tell_us_about_an_online_experience").equals("Tell us about an online experience"));
        assertTrue("Call Us and corresponding numbers are not displayed on Contact Us page.", Elements.getText("contact_us.call_us_section").equals("Call Us\n" +
                "All hours are your local time unless otherwise noted.\n" +
                "macys.com Customer Service\n" +
                "1.800.289.6229\n" +
                "24 Hours a Day\n" +
                "7 Days a Week\n" +
                "macys.com International Customer Service\n" +
                "(IDD#) +1+513.573.7912\n" +
                "24 Hours a Day\n" +
                "7 Days a Week\n" +
                "Credit Customer Service\n" +
                "1.888.257.6757\n" +
                "Monday through Saturday - 9 am to 9 pm\n" +
                "Sunday -  11 am to 8 pm\n" +
                "\n" +
                "Furniture and Mattress Customer Service\n" +
                "1.888.822.6229\n" +
                "Sunday through Friday - 8 am to 10 pm (Eastern)\n" +
                "Saturday - 8 am to 8 pm (Eastern)\n" +
                "Sunday  - 9 am to 8 pm (Eastern)\n" +
                "Furniture and Mattress Sales\n" +
                "1.800.BUY.MACY (1-800-289-6229)\n" +
                "Sunday through Saturday - 8 am to 12 am (Eastern)\n" +
                "Wedding and Gift Registry\n" +
                "1.800.568.8865\n" +
                "Monday through Friday - 9 am to 10 pm (Eastern)\n" +
                "Saturday - 9 am to 7 pm (Eastern)\n" +
                "Sunday -  11 am to 7  pm (Eastern)"));
        Clicks.click("contact_us.email_us_button");
        Wait.forPageReady();
        assertTrue("Email Us page is not displayed when clicked on Email US button.", WebDriverManager.getCurrentUrl().equals("https://www.customerservice-macys.com/app/ask?"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("contact_us.contact_a_store");
        Wait.forPageReady();
        assertTrue("Store Search page is not displayed when clicked on 'Contact A Store' link.", WebDriverManager.getCurrentUrl().contains("/store"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("contact_us.pay_your_bill");
        Wait.forPageReady();
        assertTrue("Pay Your Bill page is not displayed when clicked on 'Pay your bill' link.", WebDriverManager.getCurrentUrl().equals("https://www.macys.com/my-credit/gateway/guest"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("contact_us.view_your_credit_account");
        Wait.forPageReady();
        assertTrue("View Your Credit Card page is not displayed when clicked on 'View your credit card' link.", WebDriverManager.getCurrentUrl().equals("https://www.macys.com/my-credit/gateway/guest"));
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("contact_us.contact_credit_services");
        Wait.forPageReady();
        assertTrue("Contact Credit Service page is not displayed when clicked on 'Contact credit service' link.", WebDriverManager.getCurrentUrl().equals("https://www.customerservice-macys.com/app/answers/detail/a_id/390"));
        Navigate.browserBack();
        Wait.forPageReady();

        Clicks.click("contact_us.help_placing_an_order");
        Wait.forPageReady();
        assertTrue("Help Placing An Order page is not displayed when clicked on 'Help Placing An Order' link.", WebDriverManager.getCurrentUrl().equals("https://www.customerservice-macys.com/app/answers/detail/a_id/258"));


    }

}




