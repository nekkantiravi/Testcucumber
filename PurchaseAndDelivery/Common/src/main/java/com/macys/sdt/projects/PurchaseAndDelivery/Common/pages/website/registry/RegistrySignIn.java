package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by YH03776 on 10/13/2017.
 */

@FindBy(jsonPath = "new_registry_sign_in")
public class RegistrySignIn extends Page {

    public HtmlElement email;
    public HtmlElement password;
    public HtmlElement signInButton;

    public RegistryManager login(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        signInButton.click();
        return Navigate.get(RegistryManager.class);
    }
}
