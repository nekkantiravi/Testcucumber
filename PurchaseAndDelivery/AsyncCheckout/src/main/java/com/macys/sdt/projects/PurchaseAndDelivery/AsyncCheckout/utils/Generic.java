package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.utils;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.shared.actions.website.bcom.pages.CheckoutPageBcom;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.utils.CheckoutUtils;

import java.util.HashMap;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.framework.utils.StepUtils.signedIn;

public class Generic {

    public static void fillData(String page, HashMap<String, String> opts) {
        CheckoutUtils.RCPage pageName = CheckoutUtils.RCPage.fromString(page);
        switch (pageName) {
            case SHIPPING:
                if (macys() && signedIn()) {
                    Clicks.clickIfPresent("responsive_checkout_signed_in.add_new_address");
                    if (Elements.anyPresent("responsive_shipping_section.shipping_address_display")) {
                        Clicks.click("responsive_checkout_signed_in.change_shipping_address");
                        Clicks.click("responsive_checkout_signed_in.add_new_address");
                    }
                    new Checkout(opts, false).fillShippingData(false);
                    Clicks.click("responsive_checkout_signed_in.save_new_address");
                } else if (macys() && !signedIn()) {
                    new Checkout(opts, false).fillShippingData(false);
                    Clicks.click("responsive_checkout.continue_shipping_checkout_button");
                } else {
                    new CheckoutPageBcom(opts, false).fillGuestShippingData(false);
                    Clicks.click("responsive_checkout.continue_shipping_checkout_button");
                }
                break;
            case PAYMENT:
                if (!signedIn()) {
                    new Checkout(opts, false).fillPaymentData(false);
                    Clicks.click("responsive_payment_guest_section.continue_payment_checkout_button");
                } else {
                    Clicks.clickIfPresent("responsive_checkout_signed_in.change_credit_card_button");
                    Clicks.click("responsive_checkout_signed_in.add_credit_card_button");
                    new Checkout(opts, false).fillPaymentData(false);
                }
        }
    }
}
