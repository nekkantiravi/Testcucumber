package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by atepliashin on 2/9/17.
 */
@FindBy(jsonPath = "order_confirmation_repayment")
public class OrderConfirmationRepayment extends Page {

    public HtmlElement orderNumber;

    @Override
    public void waitForReady() {
        Wait.until(() -> orderNumber.isDisplayed(), 60);
    }
}
