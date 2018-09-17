package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.steps.website;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.CookieManager;
import cucumber.api.java.en.Given;

/**
 * Created by atepliashin on 1/20/17.
 */
public class Configuration {

    @Given("^async payment experiment cookie is set$")
    public void asyncPaymentEnabled() {
        new CookieManager().experiment().asyncPaymentEnabled(true);
    }
}
