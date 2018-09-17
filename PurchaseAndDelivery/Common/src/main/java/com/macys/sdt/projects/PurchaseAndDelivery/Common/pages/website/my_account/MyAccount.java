package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.my_account.ChangePreferredStore;
import ru.yandex.qatools.htmlelements.element.Link;

/**
 * Created by atepliashin on 11/4/16.
 */
@FindBy(jsonPath = "my_account")
public class MyAccount extends Page {

    public Link changeLocations;

    public MyAccount setPreferredStore(String zipcode) {
        Wait.until(() -> changeLocations.isDisplayed());
        changeLocations.click();
        return Navigate.get(ChangePreferredStore.class).setPreferredStore(zipcode);
    }
}
