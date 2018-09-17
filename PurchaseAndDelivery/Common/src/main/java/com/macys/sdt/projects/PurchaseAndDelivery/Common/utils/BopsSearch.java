package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils;

/**
 * Created by atepliashin on 12/21/16.
 */
public class BopsSearch {

    public static String lastFacetSearch;
    public static String lastOverlaySearch;
    public static String preferredStoreSearch;

    public static String valueFor(String value) {
        switch (value) {
            case "last_overlay_search":
                return lastOverlaySearch;
            case "last_facet_search":
                return lastFacetSearch;
            case "preferred_store":
                return preferredStoreSearch;
            default:
                return value;
        }
    }
}
