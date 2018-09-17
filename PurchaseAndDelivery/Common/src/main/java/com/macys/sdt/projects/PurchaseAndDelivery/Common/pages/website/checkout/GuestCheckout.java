package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.PaymentGuestSection;

import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.*;

/**
 * Created by atepliashin on 12/5/16.
 */
@FindBy(jsonPath = "responsive_checkout")
public class GuestCheckout extends Checkout {

    public static final String FIRST_NAME_REQ_MSG            = "Please enter a first name.";
    public static final String LAST_NAME_REG_MSG             = "Please enter a last name.";
    public static final String ADDRESS_LINE_1_REQ_MSG        = "Please enter a street address.";
    public static final String CITY_REG_MSG                  = "Please enter a city.";
    public static final String STATE_REQ_MSG                 = "Please enter a state.";
    public static final String ZIP_REQ_MSG                   = "Please enter a ZIP code.";
    public static final String PHONE_REQ_MSG                 = "Please enter a phone number.";

    public static final String FIRST_NAME_WRONG_VALUE        = "First Name 1";
    public static final String LAST_NAME_WRONG_VALUE         = "Last Name 1";
    public static final String ADDRESS_LINE_1_WRONG_VALUE    = "Address line 1 with Comma ,";
    public static final String ADDRESS_LINE_2_WRONG_VALUE    = "Address line 2 with Comma ,";
    public static final String CITY_WRONG_VALUE              = "# 1 City";
    public static final String PHONE_WRONG_VALUE             = "FOUR ZERO EIGHT";
    public static final String STATE_VALUE                   = "CA";
    public static final String PHONE_INVALID_VAL_MSG         = "Please enter a valid 10-digit phone number.";
    public static final String INVALID_VALUE_MSG             = "Please remove any special characters.";

    public static final String CARD_TYPE_REQ_MSG             = "Please select a card type.";
    public static final String CARD_NUMBER_REG_MSG           = "Please enter a card number.";
    public static final String SECURITY_NUMBER_REG_MSG       = "Please enter the security code for your card";

    @Override
    public PaymentGuestSection paymentSection() {
        return get(PaymentGuestSection.class);
    }
}
