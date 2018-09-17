package com.macys.sdt.projects.PurchaseAndDelivery.BopsCxImprovements.steps.website;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.BopsSearch;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.CookieManager;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.CookieName;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.MacysCookieMap;
import cucumber.api.java.en.Given;

/**
 * Created by atepliashin on 1/16/17.
 */
public class ConfigurationSteps {

    @Given("bops user pc zip code set as \"(.+)\"")
    public void setBopsUserPcZipcode(String zipcode) {
        new CookieManager().macysCookieMap(CookieName.MISCGCS).set(MacysCookieMap.Key.USER_PC, zipcode);
        BopsSearch.lastFacetSearch = zipcode;
        BopsSearch.lastOverlaySearch = zipcode;
    }
}
