package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.interfaces;

import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.Checkout;

/**
 * Created by atepliashin on 2/2/17.
 */
public interface IPaymentSection {

    Checkout addPaymentMethod(CreditCard card, ProfileAddress address);

    Checkout addPaymentMethod(CreditCard card, String email);

    String getPaymentSummarySectionInfo();
}
