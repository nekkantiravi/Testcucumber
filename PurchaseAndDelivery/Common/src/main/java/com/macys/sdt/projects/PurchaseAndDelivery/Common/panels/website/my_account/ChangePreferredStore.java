package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.my_account;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.MyAccount;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by atepliashin on 11/4/16.
 */
@FindBy(jsonPath = "change_preferred_store")
public class ChangePreferredStore extends Page {

    public TextInput zipcode;
    public Button search;
    public List<Button> makeThisMyStore;
    public HtmlElement searchIsActive;

    public MyAccount setPreferredStore(String zip) {
        return setPreferredStore(zip, 0);
    }

    public MyAccount setPreferredStore(String zip, Integer number) {
        Wait.until(() -> !searchIsActive.exists() && zipcode.exists());
        zipcode.sendKeys(zip);
        search.click();
        Wait.until(() -> makeThisMyStore.size() > 0, 20);
        makeThisMyStore.get(number).click();
        return Navigate.get(MyAccount.class);
    }
}
