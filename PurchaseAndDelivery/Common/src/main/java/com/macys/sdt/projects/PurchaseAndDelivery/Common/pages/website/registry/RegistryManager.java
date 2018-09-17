package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;

@FindBy(jsonPath = "registry_manager")
public class RegistryManager extends Page {

    @Override
    public void waitForReady() {
        Wait.until(this::isReady, 25);
    }
}


