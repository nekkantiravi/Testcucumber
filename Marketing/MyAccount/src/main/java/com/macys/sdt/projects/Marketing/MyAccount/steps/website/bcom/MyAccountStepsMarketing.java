package com.macys.sdt.projects.Marketing.MyAccount.steps.website.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class MyAccountStepsMarketing extends StepUtils {
    MyAccountSteps myAccountSteps = new MyAccountSteps();
    PageNavigation pageNavigation = new PageNavigation();

    @Then("^I should verify the basic attributes of the My Loyallist Account section$")
    public void iShouldVerifyTheBasicAttributesOfTheMyLoyallistAccountSection() {
        assertTrue("'NOT A LOYALLIST?' header is not displayed on bWallet section of My account page.", Elements.getText("my_account.myaccount_wallet_section_lty_header").equals("NOT A LOYALLIST?"));
        assertTrue("Loyaly message is not displayed on bWallet section of My Account page.", Elements.getText("my_account.loyallist_points_desc").equals("Earn rewards when you shop online, in store and at our outlets."));
    }

    @And("I verify the bWallet section for loyalty links \"Become a Loyallist\" and \"Add my Loyallist Account\"")
    public void iVerifyTheBWalletSectionForLoyaltyLinks() {
        assertTrue("'Become a Loyallist' link is not displayed in bWallet section of My Account page.", Elements.getText("my_account.myaccount_loyalty_become_a_loyallist_link").equals("Become a Loyallist"));
        assertTrue("'Add my Loyallist Account' link is not displayed in bWallet section of My Account page.", Elements.getText("my_account.myaccount_add_loyalty_link").equals("Add my Loyallist Account"));
        Clicks.click("my_account.myaccount_loyalty_become_a_loyallist_link");
        Wait.forPageReady();
        shouldBeOnPage("loyalty_enrollment");
        Navigate.browserBack();
        Wait.forPageReady();
        Clicks.click("my_account.myaccount_add_loyalty_link");
        Wait.forPageReady();
        shouldBeOnPage("loyalty_association");

    }

    @When("^I navigate to Store Events page$")
    public void iNavigateToStoreEventsPage() throws Throwable {
        Clicks.click("header.goto_stores");
        Wait.forPageReady();
        Thread.sleep(2000);
        shouldBeOnPage("store_finder");
    }

    @Then("^I verify the basic attributes of the Store Events page$")
    public void iVerifyTheBasicAttributesOfTheStoreEventsPage() {
        int numberOfResults;
        assertTrue("'Store Finder' header is not displayed on Store Finder page.", Elements.getText("store_finder.store_finder").equals("STORE FINDER"));
        assertTrue("'View All Stores' button is not displayed on Store Finder page.", Elements.elementPresent("store_finder.view_all_stores"));
        assertTrue("'All Locations' dropdown is not displayed on Store Finder page.", Elements.elementPresent("store_finder.all_locations_dropdown"));
        assertTrue("'Search City/State/Zip' edit box is not displayed on Store Finder page.", Elements.elementPresent("store_finder.city_state_zip_search"));
        assertTrue("'Search' button is not displayed on Store Finder page.", Elements.elementPresent("store_finder.search"));
        assertTrue("'Location Map Locator' is not displayed on Store Finder page.", Elements.elementPresent("store_finder.map_locator"));
        assertTrue("Result summary of store search is not displayed on Store Finder page.", Elements.getText("store_finder.result_summary").contains("results near"));
        assertTrue("'Map Locator' is not displayed on Store Finder page.", Elements.elementPresent("store_finder.map_locator"));
        numberOfResults = Elements.findElements("store_finder.location_details").size();
        assertTrue("'Location Title' is not displayed for all " + numberOfResults + " stores listed in the search results", Elements.findElements("store_finder.location_titles").size() == numberOfResults);
        assertTrue("'Location Address' is not displayed for all " + numberOfResults + " stores listed in the search results", Elements.findElements("store_finder.location_addresses").size() == numberOfResults);
        assertTrue("'Get Directions' link is not displayed for all " + numberOfResults + " stores listed in the search results", Elements.findElements("store_finder.location_get_directions_links").size() == numberOfResults);
        assertTrue("'Location Phone Number' is not displayed for all " + numberOfResults + " stores listed in the search results", Elements.findElements("store_finder.location_phones").size() == numberOfResults);
        assertTrue("'View Store Details' link is not displayed for all " + numberOfResults + " stores listed in the search results", Elements.findElements("store_finder.location_view_store_details").size() == numberOfResults);
//        assertTrue("'Location Today Hours' info is not displayed for all " + numberOfResults + " stores listed in the search results", Elements.findElements("store_finder.location_today_hours").size() == numberOfResults);
    }

    @When("^I navigate to the \"([^\"]*)\" link in Store Events page$")
    public void iNavigateToTheStoreFooterLink(String footerLink) {
        switch (footerLink) {
            case "Ways To Shop": {
                Clicks.click("footer.goto_ways_to_shop");
                Wait.forPageReady();
                break;
            }
            case "Stores": {
                Clicks.click("footer.goto_stores");
                Wait.forPageReady();
                break;
            }
            case "Online & Mobile": {
                Clicks.click("footer.goto_online_and_mobile");
                Wait.forPageReady();
                break;
            }
            case "Outlets": {
                Clicks.click("footer.goto_outlets");
                Wait.forPageReady();
                break;
            }
            case "b.Cause": {
                Clicks.click("footer.goto_b_cause");
                Wait.forPageReady();
                break;
            }
            case "Careers": {
                Clicks.click("footer.goto_careers");
                Wait.forPageReady();

                break;
            }

        }

    }

    @Then("^I verify that the  footer link - \"([^\"]*)\" page is rendered$")
    public void iVerifyThatTheStoreFooterLinkPageIsRendered(String footerLink) {
        switch (footerLink) {
            case "Ways To Shop": {
                assertTrue("'Ways To Shop' page is not displayed when clicked on 'Ways To Shop' link.", WebDriverManager.getCurrentUrl().contains("/b/about-us/store-events-ways-to-shop/"));
                break;
            }
            case "Stores": {
                assertTrue("'Stores' page is not displayed when clicked on 'Stores' link.", WebDriverManager.getCurrentUrl().contains("/store-locator"));
                break;
            }
            case "Online & Mobile": {
                assertTrue("'Online & Mobile' page is not displayed when clicked on 'Online & Mobile' link.", WebDriverManager.getCurrentUrl().contains("/b/about-us/mobile-shopping-online/"));
                break;
            }
            case "Outlets": {
                assertTrue("'Outlets' page is not displayed when clicked on 'Outlets' link.", WebDriverManager.getCurrentUrl().contains("/shop/the-outlet-store"));
                break;
            }
            case "b.Cause": {
                Clicks.click("footer.goto_b_cause");
                Wait.forPageReady();
                break;
            }
            case "Careers": {
                String origWindow = WebDriverManager.getWebDriver().getWindowHandle();
                String title = "";
                WebDriver driver = WebDriverManager.getWebDriver();
                Set<String> allWinHandels = driver.getWindowHandles();
                for (String wh : allWinHandels) {
                    if (!origWindow.equals(wh)) {
                        driver.switchTo().window(wh);
                        title = driver.getTitle();
                        assertTrue("'Bloomingdales Careers' Page is not displayed. Acutal page displayed - " + title, title.equals("Bloomingdale’s Jobs: Retail Employment Opportunities – Local Jobs in Bloomingdale’s Stores, Distribution Centers, and Corporate Offices"));
                        driver.close();
                    }
                }
                driver.switchTo().window(origWindow);
                break;
            }

        }
    }

    @When("^I navigate to the \"([^\"]*)\" link in Shopping Services page$")
    public void iNavigateToLinkInShoppingServicesPage(String SizeChartsLink) {
        Clicks.click("footer.goto_ways_to_shop");
        Wait.forPageReady();
        Clicks.click("ways_to_shop.shopping_services");
        Wait.forPageReady();
        Clicks.click("shopping_services.size_charts_left_nav");
        Wait.forPageReady();
        switch (SizeChartsLink) {
            case "Women": {
                Clicks.click("shopping_services.size_charts_women");
                Wait.forPageReady();
                break;
            }
            case "Men": {
                Clicks.click("shopping_services.size_charts_men");
                Wait.forPageReady();
                break;
            }
            case "Kids": {
                Clicks.click("shopping_services.size_charts_kids");
                Wait.forPageReady();
                break;
            }
            case "Shoes": {
                Clicks.click("shopping_services.size_charts_shoes");
                Wait.forPageReady();
                break;
            }

        }
    }

    @Then("^I verify that the size charts \"([^\"]*)\" page is rendered$")
    public void iVerifryThatTheSizeChartsPagesRedered(String SizeChartsLink) {
        switch (SizeChartsLink) {
            case "Women": {
                assertTrue("'Women' size chart page is not displayed when clicked on Women link.", WebDriverManager.getCurrentTitle().equals("Womens Size Charts"));
                break;
            }
            case "Men": {
                assertTrue("'Men' size chart page is not displayed when clicked on Men link.", WebDriverManager.getCurrentTitle().equals("Mens Size Charts"));
                break;
            }
            case "Kids": {
                assertTrue("'Kids' size chart page is not displayed when clicked on Kids link.", WebDriverManager.getCurrentTitle().equals("Kids Size Charts"));
                break;
            }
            case "Shoes": {
                assertTrue("'Shoes' size chart page is not displayed when clicked on Shoes link.", WebDriverManager.getCurrentTitle().equals("Shoes Size Charts"));
                break;
            }

        }
    }

    @Then("^I verify stores are listed in distance order of search location$")
    public void iVerifyStoresAreListedInDistanceOrderOfLocation() {
        List<String> expectedStoreTitles = new ArrayList<String>();
        List<String> actStoreTitles = new ArrayList<String>();
        Wait.secondsUntilElementPresent("store_finder.city_state_zip_search",15);
        Elements.findElement("store_finder.city_state_zip_search").clear();
        TextBoxes.typeTextbox("store_finder.city_state_zip_search", "San Francisco, California");
        Clicks.click("store_finder.search");
        Wait.forPageReady();
        expectedStoreTitles.add("BLOOMINGDALE'S SAN FRANCISCO CENTRE");
        expectedStoreTitles.add("BLOOMINGDALE'S STANFORD");
        expectedStoreTitles.add("BLOOMINGDALE'S OUTLET LIVERMORE PREMIUM OUTLETS");

        for (WebElement actStoreTitle:Elements.findElements("store_finder.location_titles")) {
            actStoreTitles.add(actStoreTitle.getText());
        }
        assertTrue("Search Results in Store Events page are displayed in distance order. Expected Store order "+expectedStoreTitles +" Actual Stores Order "+actStoreTitles,actStoreTitles.equals(expectedStoreTitles));

    }

    @When("^I navigate to a Store Details page$")
    public void iNavigateToAStore() {
        Elements.findElements("store_finder.location_view_store_details").get(0).click();
        Wait.forPageReady();
    }

    @Then("^I verify the store details page$")
    public void iVerifyTheStoreDetailsPage() {
        assertTrue("Bread Crumb of Store Details Page is not displayed correctly. Actual Bread Crumb is "+Elements.getText("store_details.store_details_bread_crumb"),Elements.getText("store_details.store_details_bread_crumb").equals("Stores & Events Bloomingdale's San Francisco Centre"));
    }

    @When("^I navigate to Store Events page from Store Details$")
    public void iNavigateToStoreEventsPageFromStoreDetails() {
        Clicks.click("store_details.view_all_events");
        Wait.forPageReady();
    }

    @Then("^I verify the store events page$")
    public void iVerifyTheStoreEventsPage() {
        assertTrue("Store Events page is not displayed.",onPage("store_finder"));
    }

    @Then("^I should see store information on store details page$")
    public void iShouldSeeStoreInformation() {
//        assertTrue("Store location Map is not displayed.",Elements.elementPresent("store_details.store_map"));
        assertTrue("Store Name of Store Details Page is not displayed correctly. Actual Store Name is "+Elements.getText("store_details.store_name"),Elements.getText("store_details.store_name").equals("BLOOMINGDALE'S SAN FRANCISCO CENTRE"));
        assertTrue("Today's Hours information is not displayed correctly.",Elements.elementPresent("store_details.store_hours_today"));
        assertTrue("Store Address is not displayed correctly on Store Details page.",Elements.getText("store_details.store_address").equals("845 Market StreetSan Francisco, CA 94103"));
        assertTrue("'Get Directions' link is not displayed on Store Details page.",Elements.getText("store_details.get_directions").equals("GET DIRECTIONS"));
        assertTrue("Store Phone Number is not displayed on Store Details page.",Elements.elementPresent("store_details.store_phone_number"));
        assertTrue("Store Manager info is not displayed on Store Details page.",Elements.elementPresent("store_details.store_manager"));
        assertTrue("Store Hours Header is not displayed on Store Details page.",Elements.elementPresent("store_details.store_hours_header"));
        assertTrue("Store Hours of the week is not displayed on Store Details page.",Elements.elementPresent("store_details.store_all_hours"));
        assertTrue("Promotion Banner is not displayed on Store Details page.",Elements.elementPresent("store_details.promo_banner"));
        assertTrue("Store Services Section is not displayed on Store Details page.",Elements.elementPresent("store_details.store_services_section"));
        assertTrue("About Store Section is not displayed on Store Details page.",Elements.elementPresent("store_details.about_store_section"));
        assertTrue("Store Events Section is not displayed on Store Details page.",Elements.elementPresent("store_details.store_events_section"));
        assertTrue("Near By Stores Section is not displayed on Store Details page.",Elements.elementPresent("store_details.near_by_stores_section"));

    }

}




