package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies;

import com.macys.sdt.framework.utils.StepUtils;

/**
 * Created by atepliashin on 1/19/17.
 */
public enum CookieName {
    MISCGCS("MISCGCs"),
    SEGMENT("SEGMENT"),
    ONLINE_UID(StepUtils.macys() ? "macys_online_uid" : "bloomingdales_online_uid");

    CookieName(String name) {
        this.name = name;
    }

    final private String name;

    public String toString() {
        return name;
    }
}
