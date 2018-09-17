package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.FooterSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.HeaderSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.OrderReviewSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.interfaces.IPaymentSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ShippingSection;

/**
 * Created by atepliashin on 12/5/16.
 */
abstract public class Checkout extends Page {

    public HeaderSection headerSection() {
        return Navigate.get(HeaderSection.class);
    }

    public ShippingSection shippingSection() {
        return Navigate.get(ShippingSection.class);
    }

    public OrderReviewSection orderReviewSection() {
        return Navigate.get(OrderReviewSection.class);
    }

    public FooterSection footerSection() {
        return Navigate.get(FooterSection.class);
    }

    abstract public IPaymentSection paymentSection();
}
