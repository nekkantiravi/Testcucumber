package com.macys.sdt.projects.PurchaseAndDelivery.BopsCxImprovements.steps.website;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.BopsFacet;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.BopsSearch;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.framework.utils.rest.services.Stores;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * Created by atepliashin on 12/15/16.
 */
public class BopsFacetSteps {

    @When("^I enter zipcode (?:\"(.*)\" )?and search for stores$")
    public void searchStores(String zipcode) {
        BopsFacet bopsFacet = Navigate.get(BopsFacet.class).expand();
        if (zipcode == null) {
            zipcode = Stores.zipCodeByStoreNumber(ShopAndBrowse.recentProduct.storeLocationNum).split("-")[0];
        }
        BopsSearch.lastFacetSearch = zipcode;
        bopsFacet.searchStoresForPickup(zipcode);
    }

    @When("^I change location to the (\\d+)(?:st|nd|rd|th) store with zipcode(?: (.*))? entered(?: in (\\d+) miles)?$")
    public void changeLocation(Integer humanStoreIndex, String zipcode, Integer miles) {
        if (zipcode == null) {
            zipcode = Stores.zipCodeByStoreNumber(ShopAndBrowse.recentProduct.storeLocationNum);
        }
        BopsSearch.lastFacetSearch = zipcode;
        Navigate.get(BopsFacet.class).expand().changeLocation(zipcode, miles, --humanStoreIndex);
    }

    @When("^I initiate store change for bops facet$")
    public void initiateStoreChange() {
        Navigate.get(BopsFacet.class).expand().initiateStoreChange();
    }

    @When("^I set bops facet search distance as (\\d+) miles$")
    public void setBopsFacetMilesValue(String miles) {
        Navigate.get(BopsFacet.class).expand().selectMiles(miles);
    }

    @Then("^the (?:(\\d+)st|nd|rd|th) store is (not )?selected in bops facet$")
    public void bopsStoreSelectedInFacet(Integer humanIndex, String negate) {
        assertThat(Navigate.get(BopsFacet.class).expand().isStoreOptionSelected(--humanIndex), is(negate == null));
    }

    @Then("^bops facet search distance is (\\d+) miles$")
    public void checkMilesOption(String miles) {
        assertThat(Navigate.get(BopsFacet.class).expand().getSelectedMiles(), is(miles));
    }

    @Then("^bops facet store location populated by \"(.+)\" zipcode$")
    public void checkBopsLocation(String value) {
        String pickupZipcode = Navigate.get(BopsFacet.class).expand().searchedZipcode.getText();
        assertEquals(BopsSearch.valueFor(value), pickupZipcode);
    }
}
