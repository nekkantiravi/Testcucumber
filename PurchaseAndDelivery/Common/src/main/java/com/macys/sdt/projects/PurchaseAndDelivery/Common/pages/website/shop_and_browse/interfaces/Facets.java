package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.BopsFacet;

import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.get;

/**
 * Created by atepliashin on 12/20/16.
 */
public interface Facets {

    default BopsFacet bopsFacet() {
        return get(BopsFacet.class);
    }
}
