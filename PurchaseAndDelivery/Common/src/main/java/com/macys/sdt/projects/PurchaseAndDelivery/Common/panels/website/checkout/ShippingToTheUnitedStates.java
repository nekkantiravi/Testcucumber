package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(jsonPath = "shipping_to_the_united_states")
public class ShippingToTheUnitedStates extends Page {

    public Button returnToTheInternationalSite;
    public Button continueCheckout;
    public HtmlElement shippingToUnitedStatesContainer;

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(() -> shippingToUnitedStatesContainer.isDisplayed());
    }

}
