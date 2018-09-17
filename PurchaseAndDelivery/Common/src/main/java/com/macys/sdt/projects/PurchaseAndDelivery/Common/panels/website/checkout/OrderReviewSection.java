package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(jsonPath = "responsive_order_review_section")
public class OrderReviewSection extends Page {
    public HtmlElement bopsStoreDetails;
    public HtmlElement bopsPickupPerson;
    public HtmlElement bopsPickupStoreSection;
    public HtmlElement shippingAddressSection;
    public Button placeOrder;
    public HtmlElement shippingEgiftCardSection;
    public HtmlElement rcPaymentFailure;
    public HtmlElement threeDSecureReviewIframe;
    public HtmlElement threeDSecureAuthIframe;
    public HtmlElement threeDSecurePassword;
    public HtmlElement threeDSecureSubmit;
    public HtmlElement shippingInfo;
    public HtmlElement paymentSummarySection;
    public HtmlElement billingAddressSection;

    public void waitForReady() {
        super.waitForReady();

        Wait.until(placeOrder::isEnabled);
    }

    public void placeOrder() {
        placeOrder.click();
    }
}