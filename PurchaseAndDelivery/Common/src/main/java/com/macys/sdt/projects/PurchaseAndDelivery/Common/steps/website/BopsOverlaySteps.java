package com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.rest.services.Stores;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.BopsSearch;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.MyAccount;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.ChangePickupStoreDialog;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

/**
 * Created by atepliashin on 9/26/16.
 */
public class BopsOverlaySteps {

    @When("^I enter zipcode (?:\"(.*)\" )?and do search for available stores(?: in (\\d+) miles)?$")
    public void iSearchStoresByZipCode(String zipcode, Integer miles) {
        if (zipcode == null) {
            zipcode = Stores.zipCodeByStoreNumber(ShopAndBrowse.recentProduct.storeLocationNum);
        }
        BopsSearch.lastOverlaySearch = zipcode;
        Navigate.get(ChangePickupStoreDialog.class).findStores(zipcode, miles);
    }

    @When("^I should see zip code search field is auto populated with \"(.+)\" value$")
    public void iSeeSearchFieldIsPopulated(String value) {
        String searchFieldText = Navigate.get(ChangePickupStoreDialog.class).getSearchFieldText();
        assertEquals(BopsSearch.valueFor(value), searchFieldText);
    }

    @When("^I enter my preferred store zipcode as \"(\\d+)\"$")
    public void iEnterPreferredStore(String zipcode) {
        Navigate.get(MyAccount.class).setPreferredStore(zipcode);
        BopsSearch.preferredStoreSearch = zipcode;
    }

    @When("^I close bops overlay$")
    public void closeOverlay()  {
        Navigate.get(ChangePickupStoreDialog.class).close();
    }

    @When("I toggle map view(?: for the (\\d+)(?:st|nd|rd|th) available store)?")
    public void toggleMapView(Integer humanIndex) {
        Navigate.get(ChangePickupStoreDialog.class).toggleMapView(humanIndex != null ? --humanIndex : 0);
    }

    @When("I toggle item availability(?: for the (\\d+)(?:st|nd|rd|th) available store)?")
    public void toggleItemAvailability(Integer humanIndex) {
        Navigate.get(ChangePickupStoreDialog.class).toggleItemAvailability(humanIndex != null ? --humanIndex : 0);
    }

    @When("^I select (?:the (\\d+)(?:st|nd|rd|th) )available bops store and save details$")
    public void selectAvailableBopsStoreAndSaveDetails(Integer humanIndex) {
        ChangePickupStoreDialog overlay = Navigate.get(ChangePickupStoreDialog.class);
        Wait.until(() -> overlay.searchResultsSection.isDisplayed());
        overlay.selectStore(humanIndex != null ? --humanIndex : 0);
        overlay.save();
    }

    @Then("^\"(.*)\" overlay error pops up$")
    public void overlayErrorPopsUp(String error) {
        ChangePickupStoreDialog changePickupStoreDialog = Navigate.get(ChangePickupStoreDialog.class);
        Wait.until(() -> changePickupStoreDialog.errors.size() > 0);
        assertEquals("No expected error in overlay", error, changePickupStoreDialog.errors.get(0).getText());
    }
}
