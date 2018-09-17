package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by atepliashin on 12/7/16.
 */
@FindBy(jsonPath = "responsive_contact_info_section")
public class ContactInfoSection extends Page {

    public HtmlElement contactInfoSection;
    public TextInput phone;
    public Button save;
    public Button change;
    public Button cancel;

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(() -> phone.isDisplayed());
    }

    public SignedInCheckout addContactInfo(String phoneNumber) {
        phone.sendKeys(phoneNumber);
        save.click();
        Wait.until(() -> !phone.exists(), 30);
        return Navigate.get(SignedInCheckout.class);
    }
}
