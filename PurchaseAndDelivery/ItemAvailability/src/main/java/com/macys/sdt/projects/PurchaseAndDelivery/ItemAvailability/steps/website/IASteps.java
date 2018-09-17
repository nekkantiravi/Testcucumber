package com.macys.sdt.projects.PurchaseAndDelivery.ItemAvailability.steps.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.BrowseService;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.SearchService;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.SearchResult;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.ChangePickupStoreDialog;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.ChangePickupStoreWithinDialog;
import com.macys.sdt.projects.PurchaseAndDelivery.ItemAvailability.actions.website.IAGenericMethods;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class IASteps {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IASteps.class);
    private static String selectedStoreName;

    @Then("^I should (not )?see tabbed view for online and BOPS items$")
    public void iShouldSeeTabbedViewForOnlineAndBOPSItems(String condition) {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        if (condition == null) {
            assertTrue("tabbed view is not displaying", Wait.until(searchResult.tabView::exists));
        } else {
            assertFalse("tabbed view is displaying", Wait.until(searchResult.tabView::exists));
        }
    }

    @And("^I should see all items tab as selected$")
    public void iShouldSeeAllItemsTabAsSelected() {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        String selectedTabName = searchResult.selectedTab.getText().replaceAll("\\P{L}", "");
        assertEquals("AllItems", selectedTabName);
    }

    @And("^I should see local store tab as unselected$")
    public void iShouldSeeLocalStoreTabAsUnselected() {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        String unselectedTabName = searchResult.selectedTab.getText().replaceAll("\\P{L}", " ");
        Boolean isSelected = searchResult.selectedTab.getAttribute("class").isEmpty();
        assertEquals("Local store", unselectedTabName);
        assertTrue("Local store tab is selected", isSelected);
    }

    @And("^I see the product count from UI is same as from service for \"([^\"]*)\" search keyword$")
    public void iSeeTheProductCountFromUIIsSameAsFromServiceForSearchKeyword(String keyword) {
        Map serviceResponse = SearchService.getSearchService(keyword, "SITE", "US", false, null);
        Map searchResponse = (Map) serviceResponse.get("SearchResult");
        int totalProductCountFromResponse = Integer.parseInt(searchResponse.get("totalProductCount").toString().split("\\.")[0]);
        int totalProductCountFromUI = IAGenericMethods.getProductCountFromTab();
        assertEquals("Product count from UI and Service are different.", totalProductCountFromResponse, totalProductCountFromUI);
    }

    @Then("^I should not see free pick up in store facet section on left navigation$")
    public void iShouldNotSeeFreePickupInStoreFacetSectionOnLeftNavigation() {
        assertFalse("Free Pickup InStore facet is displaying on left navigation", Navigate.get(SearchResult.class).bopsFacet.exists());
    }

    @When("^I click on change location link$")
    public void iClickOnChangeLocationLink() {
        Navigate.get(SearchResult.class).selectedTab.click();
        assertTrue(" Store overlay has not displayed", Navigate.get(ChangePickupStoreDialog.class, true).container.isDisplayed());
    }

    @When("^I select (local store|all items) tab$")
    public void iSelectTab(String selectedTab) {
        if (selectedTab.equalsIgnoreCase("local store"))
            Navigate.get(SearchResult.class).freePickupTab.click();
        else if (selectedTab.equalsIgnoreCase("all items")) {
            Navigate.get(SearchResult.class).allItemsTab.click();
        }
    }


    @And("^I see \"([^\"]*)\" overlay$")
    public void iSeeOverlay(String overlay) {
        if (overlay.equalsIgnoreCase("choose a store")) {
            ChangePickupStoreWithinDialog changePickupStoreWithinDialog = Navigate.get(ChangePickupStoreWithinDialog.class);
            assertTrue("Overlay has not displayed", Wait.until(changePickupStoreWithinDialog.container::exists));
        } else if (overlay.equalsIgnoreCase("Find a store")) {
            ChangePickupStoreDialog changePickupStoreDialog = Navigate.get(ChangePickupStoreDialog.class);
            assertTrue("Overlay has not displayed", Wait.until(changePickupStoreDialog.container::exists, 10));
        }
    }

    @And("^I see the change store link$")
    public void iSeeTheChangeStoreLink() {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        searchResult.waitForReady();
        assertTrue("Change store link doesn't displayed", Wait.until(searchResult.changeStore::exists, 10));
    }

    @And("^I see the store product count on local store tab$")
    public void iSeeTheStoreProductCountOnLocalStoreTab() {
        assertNotNull("local store tab doesn't has store product count", IAGenericMethods.getProductCountFromTab());
    }

    @And("^I see the store product count from UI is same as from service$")
    public void iSeeTheStoreProductCountFromUIIsSameAsFromService() {
        iSeeTheProductCountFromUIIsSameAsFromServiceForSearchKeyword("Eyeliner");
    }

    @Then("^I see the store name displayed on UI is same as from the service response$")
    public void iSeeTheStoreNameDisplayedOnUIIsSameAsFromTheServiceResponse() {
        //TBD--As X-API service is not ready yet
    }

    @And("^I click \"([^\"]*)\" link$")
    public void iClickLink(String link) {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        if (link.equalsIgnoreCase(searchResult.changeStore.getText())) {
            searchResult.changeStore.click();
        } else if (link.equalsIgnoreCase(searchResult.changeLocation.getText())) {
            searchResult.changeLocation.click();
        } else {
            fail("Wrong link has displayed");
        }
    }

    @And("^I click on apply button in find stores within overlay$")
    public void iClickOnApplyButtonInFindStoresWithinOverlay() {
        Navigate.get(ChangePickupStoreWithinDialog.class).save();
    }

    @Then("^I should see \"([^\"]*)\" overlay is closed$")
    public void iShouldSeeOverlayIsClosed(String overlay) {
        ChangePickupStoreWithinDialog changePickupStoreWithinDialog = Navigate.get(ChangePickupStoreWithinDialog.class);
        if (overlay.equalsIgnoreCase("Find a store")) {
            assertFalse(" Find a store Overlay is not closed yet", changePickupStoreWithinDialog.tabView.exists());
        } else if (overlay.equalsIgnoreCase("Find stores within"))
            assertFalse("Find stores within Overlay is not closed yet", changePickupStoreWithinDialog.tabView.exists());
    }

    @And("^I close \"([^\"]*)\" overlay$")
    public void iCloseOverlay(String overlay) {
        ChangePickupStoreWithinDialog changePickupStoreWithinDialog = Navigate.get(ChangePickupStoreWithinDialog.class);
        ChangePickupStoreDialog changePickupStoreDialog = Navigate.get(ChangePickupStoreDialog.class);
        if (overlay.equalsIgnoreCase("Find a store")) {
            changePickupStoreDialog.close();
        } else if (overlay.equalsIgnoreCase("Find stores within")) {
            changePickupStoreWithinDialog.close();
        }
    }

    @Then("^I should see same store name under local store tab$")
    public void iShouldSameSeeStoreNameUnderLocalStoreTab() {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        Assert.assertEquals("Same Store name doesn't displayed", selectedStoreName, searchResult.storeName.getText());
    }

    @When("^I select different store from \"([^\"]*)\" overlay and save the selection$")
    public void iSelectDifferentStoreFromOverlayAndSaveTheSelection(String overlay) {
        if (overlay.equalsIgnoreCase("Find a store")) {
            ChangePickupStoreDialog changePickupStoreDialog = Navigate.get(ChangePickupStoreDialog.class);
            int randomSelection = new Random().nextInt(changePickupStoreDialog.selectStoreButton.size());
            selectedStoreName = changePickupStoreDialog.selectStoreName.get(randomSelection).getText();
            changePickupStoreDialog.selectStoreButton.get(randomSelection).click();
            changePickupStoreDialog.save();
        } else if (overlay.equalsIgnoreCase("choose a store")) {
            ChangePickupStoreWithinDialog changePickupStoreWithinDialog = Navigate.get(ChangePickupStoreWithinDialog.class);
            int randomSelection = new Random().nextInt(changePickupStoreWithinDialog.selectStoreButton.size());
            selectedStoreName = changePickupStoreWithinDialog.selectStoreButton.get(randomSelection).getAttribute("id");
            changePickupStoreWithinDialog.selectStoreButton.get(randomSelection).click();
            changePickupStoreWithinDialog.save();
        }
    }

    @When("^I click change location link on choose a store overlay$")
    public void iClickChangeLocationLinkOnChooseAStoreOverlay() {
        Navigate.get(ChangePickupStoreWithinDialog.class).changeLocation.click();
    }

    @Then("^I verify that products are filtered as per selected store$")
    public void iVerifyThatProductsAreFilteredAsPerSelectedStore() {
        Assert.assertEquals("Product count and thumbnail count doesn't match", IAGenericMethods.getProductCountFromTab(), IAGenericMethods.getTotalThumbnailCount());
    }

    @And("^I verify that stores are displayed in choose a store overlay$")
    public void iVerifyThatStoresAreDisplayedInChooseAStoreOverlay() {
        ChangePickupStoreWithinDialog changePickupStoreWithinDialog = Navigate.get(ChangePickupStoreWithinDialog.class);
        Assert.assertTrue("No Stores are available in choose a store overlay", Wait.until(changePickupStoreWithinDialog.storeResults::exists));
    }

    @And("^I verify that miles drop down in choose a store overlay with below option:$")
    public void iVerifyThatMilesDropDownInChooseAStoreOverlayWithBelowOption(List<String> miles) {
        ChangePickupStoreWithinDialog changePickupStoreWithinDialog = Navigate.get(ChangePickupStoreWithinDialog.class);
        String[] actual = changePickupStoreWithinDialog.milesSelect.getText().split("\n");
        for (int i = 0; i < (miles.size() - 1); i++) {
            logger.info("Displayed miles in Drop Down: " + actual[i]);
            Assert.assertTrue("Error Miles drop down is not listed " + miles.get(i), miles.get(i).equalsIgnoreCase(actual[i].trim()));
        }
        logger.info("Verified that miles drop down with below option: " + miles);
    }

    @Then("^I should see the zero results message \"([^\"]*)\" on search results page$")
    public void iShouldSeeTheZeroResultsMessageOnSearchResultsPage(String zsrMessage) {
        Assert.assertEquals("Wrong ZSR message displayed in search results page ", zsrMessage, Navigate.get(SearchResult.class).zsrMessage.getText());
    }

    @And("^I select (\\d+) miles in choose a store overlay$")
    public void iSelectInChooseAStoreOverlay(String miles) {
        Navigate.get(ChangePickupStoreWithinDialog.class).selectMiles(miles);
    }

    @Then("^I verify that (\\d+) miles is displayed as default in choose a store overlay$")
    public void iVerifyThatMilesIsDisplayedAsDefaultInChooseAStoreOverlay(String defaultMiles) {
        Assert.assertEquals("Wrong default miles is displayed", defaultMiles, Navigate.get(ChangePickupStoreWithinDialog.class).getSelectedMiles());

    }

    @And("^I verify that first store selected by default$")
    public void iVerifyThatFirstStoreSelectedByDefault() {
        Assert.assertTrue("First store doesn't selected ", Navigate.get(ChangePickupStoreWithinDialog.class).selectStoreButton.get(0).isSelected());
    }

    @And("^I see the product count from UI is same as from service for given category$")
    public void iSeeTheProductCountFromUIIsSameAsFromServiceForGivenCategory() {
        String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
        Map serviceResponse = BrowseService.getBrowseService(catID, "SITE", "US", false, null);
        Map browseResponse = (Map) serviceResponse.get("products");
        int totalProductCountFromResponse = Integer.parseInt(browseResponse.get("totalProducts").toString().split("\\.")[0]);
        int totalProductCountFromUI = IAGenericMethods.getProductCountFromTab();
        assertEquals("Product count from UI and Service are different.", totalProductCountFromResponse, totalProductCountFromUI);
    }
}
