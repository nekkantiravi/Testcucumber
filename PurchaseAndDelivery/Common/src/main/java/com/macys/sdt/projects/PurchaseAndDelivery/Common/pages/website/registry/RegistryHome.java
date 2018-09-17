package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by YH03776 on 10/13/2017.
 */

@FindBy(jsonPath = "registry_home")
public class RegistryHome extends Page {

    public HtmlElement manageRegistryDropdown;
    public HtmlElement gotoRegistryManager ;

    public RegistrySignIn regsitrySignIn() {
        manageRegistryDropdown.click();
        Wait.until(gotoRegistryManager::isDisplayed);
        gotoRegistryManager.click();
        Wait.forPageReady();
        return Navigate.get(RegistrySignIn.class);
    }
}

