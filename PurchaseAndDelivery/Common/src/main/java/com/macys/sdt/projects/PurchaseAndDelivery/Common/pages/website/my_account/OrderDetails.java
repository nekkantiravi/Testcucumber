package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.Repayment;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by atepliashin on 1/28/17.
 */
@FindBy(jsonPath = "order_details")
public class OrderDetails extends Page {

    public Button updatePayment;
    public HtmlElement errorMessage;

    public Repayment updatePayment() {
        updatePayment.click();
        return Navigate.get(Repayment.class);
    }
}
