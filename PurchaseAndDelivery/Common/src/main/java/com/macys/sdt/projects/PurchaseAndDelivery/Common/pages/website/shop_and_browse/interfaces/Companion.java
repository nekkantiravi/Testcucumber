package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.CompanionSidebar;

import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.get;

/**
 * Created by atepliashin on 1/13/17.
 */
public interface Companion {

    default CompanionSidebar companionSidebar() {
        return get(CompanionSidebar.class);
    }
}
