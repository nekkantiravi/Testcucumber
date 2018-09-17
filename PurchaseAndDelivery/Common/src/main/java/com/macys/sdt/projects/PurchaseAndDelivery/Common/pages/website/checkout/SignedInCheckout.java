package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ContactInfoSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.PaymentSignedInSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.SignedInGiftCardsSection;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.get;

/**
 * Created by atepliashin on 12/5/16.
 */
@FindBy(jsonPath = "responsive_checkout_signed_in")
public class SignedInCheckout extends Checkout {

    // commented because it affects page building, but element are not used
    // Uncomment when needed
//    public HtmlElement internationalShippingLink;

    public HtmlElement addNewAddress;
    public HtmlElement saveNewAddress;
    //    public HtmlElement shippingAddressNames;
//    public HtmlElement shippingAddressLine1s;
    public Button changeShippingAddress;
    public HtmlElement editShippingAddress;
    public Button saveShippingAddress;
    //    public HtmlElement updatePaypalProfileEmail;
    public HtmlElement saveShippingMethod;
    public HtmlElement changeShippingMethod;


    //    public HtmlElement shippingAddressLine2s;
//    public HtmlElement selectShippingAddresses;
    public List<HtmlElement> shippingAddressRadioButton;

//    public HtmlElement selectCreditCards;
//    public HtmlElement securityCode;

    public HtmlElement email;

    //    public HtmlElement egiftCardSection;
//    public HtmlElement shippingAddressSection;
//    public HtmlElement paymentSection;
//    public HtmlElement shippingMethodSection;
//    public HtmlElement signinShippingMethods;
//    public HtmlElement shippingGiftSection;
//    public HtmlElement contactInfoSection;
//    public HtmlElement selectRegistryAddress;
//    public HtmlElement shippingRegistryAddressTitle;
//    public HtmlElement registryAddress;
//    public HtmlElement registryTitle;
    public HtmlElement maskLayer;
//    public HtmlElement mbmoneyRewardCardNumbers;
//    public HtmlElement mbmoneyRewardCardsContainer;

    public Button addShippingAddressButton;
    public Button cancelShippingChangeButton;
    public Button saveShippingButton;
    public Button shippingChangeButton;
    public HtmlElement shippingAddressInfo;
    public HtmlElement shippingMethodInfo;
    public HtmlElement giftOptionsInfo;

    //    public Button addGiftCardButton;
//    public Button addCreditCardButton;
    public Button saveCreditCardButton;
    public Button changeCreditCardButton;
    public Button cancelCreditCardButton;
    public HtmlElement paymentInfo;

//    public HtmlElement shippingFirstName;
//    public HtmlElement shippingLastName;
//    public HtmlElement shippingAddressLine1;
//    public HtmlElement shippingAddressLine2;
//    public HtmlElement shippingAddressCity;
//    public HtmlElement shippingAddressState;
//    public HtmlElement shippingAddressZipCode;
//    public HtmlElement shippingPhoneNumber;

//    public HtmlElement cardType;
//    public HtmlElement cardNumber;
//    public HtmlElement expiryMonth;
//    public HtmlElement expiryYear;

//    public HtmlElement paymentEmail;
//    public HtmlElement mbmoneyRewardCardBalances;
//    public HtmlElement rewardCardNames;
//    public HtmlElement saveButton;

    public HtmlElement placeOrder;
//    public HtmlElement paypalRadioButton;
//    public HtmlElement continueWithPaypal;
//    public HtmlElement itemLevelError;
//    public HtmlElement errorContainer;
//    public HtmlElement pickUpSave;
//    public HtmlElement premiumShipping;
//    public HtmlElement expressShipping;
//    public HtmlElement sddShipping;

//    @FindBy(jsonPath = "responsive_checkout_signed_in.3d_secure_reviewIframe")
//    public HtmlElement threedSecureReviewIframe;
//    @FindBy(jsonPath = "responsive_checkout_signed_in.3d_secure_authIframe")
//    public HtmlElement threedSecureAuthIframe;
//    @FindBy(jsonPath = "responsive_checkout_signed_in.3d_secure_password")
//    public HtmlElement threedSecurePassword;
//    @FindBy(jsonPath = "responsive_checkout_signed_in.3d_secure_submit")
//    public HtmlElement threedSecureSubmit;

//    public HtmlElement pickupFirstName;
//    public HtmlElement pickupLastName;
//    public HtmlElement pickupEmailAddress;
//    public HtmlElement pickupPhoneNumber;

//    public HtmlElement cCardSecurityInput;

//    public HtmlElement editCreditCardDetails;
//    public HtmlElement editCreditCard;

    public Button shippingAddressForm;
    public Button addShippingAddressFormButton;

    public HtmlElement shippingAddressSummaryInfoName;
    public HtmlElement shippingAddressSummaryInfoAddressLine1;
    public HtmlElement shippingAddressSummaryInfoAddressLine2;
    public HtmlElement shippingAddressSummaryInfoCityStateZip;
    public HtmlElement shippingAddressSelectedEditButton;
    public HtmlElement shippingAddressSwapContainer;

    public HtmlElement missedFieldMessage;
    public HtmlElement missedFieldMessageScrollToLink;
    public HtmlElement missedFieldMessageSecurityCodeAddLink;

    public HtmlElement pickupInfoForm;
    public HtmlElement giftOptionExpand;
    public HtmlElement giftOptionsCancel;
    public HtmlElement expandLoyallistSection;
    public HtmlElement loyallistSectionCancel;
    public HtmlElement loyallist;
    public HtmlElement lookupLink;
    public HtmlElement plentiCancel;
    public HtmlElement applyLoyallistButton;
    public HtmlElement loyallistNumberTextField;
    public HtmlElement loyallistLastNameTextField;
    public HtmlElement loyallistZipCodeTextField;
    public HtmlElement shippingAddressLineTwoValidationErrorMessage;

    public static final String MAKE_BLM_DEFAULT_MSG     = "✕\n" +
            "Don't miss out on Loyallist and cardholder-only offers—make your Bloomingdale's Credit Card your default payment method.";
    public static final String MAKE_BLM_DEFAULT_LINK    = "default payment method";
    public static final String DEFAULT_CARD_UPDATED_SUCCESS_MSG = "Your default card has been successfully updated.";


    public SignedInCheckout expandShippingAddressSection() {
        if (!addNewAddress.exists()) {
            changeShippingAddress.click();
            Wait.until(addNewAddress::exists);
        }
        return this;
    }

    public SignedInGiftCardsSection giftCardsSection() {
        return get(SignedInGiftCardsSection.class);
    }

    public ContactInfoSection contactInfoSection() {
        return get(ContactInfoSection.class);
    }

    @Override
    public PaymentSignedInSection paymentSection() {
        return get(PaymentSignedInSection.class);
    }
}
