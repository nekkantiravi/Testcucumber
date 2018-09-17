package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindAll;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.model.GiftCard;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.GuestCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.interfaces.IPaymentSection;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.AbbreviationHelper.getStateAbbreviation;
import static com.macys.sdt.framework.utils.AbbreviationHelper.translateStateAbbreviation;

/**
 * Created by atepliashin on 12/7/16.
 */
@FindBy(jsonPath = "responsive_payment_guest_section")
public class PaymentGuestSection extends Page implements IPaymentSection {

    public Button addGiftCardButton;
    public Button giftCardApplyButton;
    public TextInput giftCardNumber;
    public TextInput giftCardCid;
    public TextInput giftCardCaptcha;
    public Radio paymentTypeRadioGroup;
    public HtmlElement creditCardContainer;
    public TextInput cardNumber;
    @FindAll({@FindBy(jsonPath = "responsive_payment_guest_section.customized_card_type"),
            @FindBy(jsonPath = "responsive_payment_guest_section.card_type")})
    public Select cardType;
    @FindAll({@FindBy(jsonPath = "responsive_payment_guest_section.customized_expiry_year"),
            @FindBy(jsonPath = "responsive_payment_guest_section.expiry_year")})
    public Select expiryYear;
    @FindAll({@FindBy(jsonPath = "responsive_payment_guest_section.customized_expiry_month"),
            @FindBy(jsonPath = "responsive_payment_guest_section.expiry_month")})
    public Select expiryMonth;
    public TextInput securityCode;
    public List<HtmlElement> creditCardFieldErrors;
    public Button continueWithPaypal;
    public HtmlElement paypalRadioButton;
    public HtmlElement creditCardRadioButton;

    public CheckBox useShippingAddress;
    public HtmlElement useMyShippingName;
    public HtmlElement useMyShippingAddressLine1;
    public HtmlElement useMyShippingCityStateZip;

    public TextInput firstName;
    public TextInput lastName;
    public TextInput addressLine_1;
    public TextInput addressLine_2;
    public TextInput addressCity;
    @FindAll({@FindBy(jsonPath = "responsive_payment_guest_section.customized_address_state"),
            @FindBy(jsonPath = "responsive_payment_guest_section.address_state")})
    public Select addressState;
    public TextInput addressZipCode;

    public TextInput paymentEmail;
    public TextInput phoneNumber;

    public Button continuePaymentCheckoutButton;

    public List<HtmlElement> paymentSummarySection;
    public Button editPaymentSection;
    public HtmlElement paymentSummary;
    public HtmlElement paypalDisclaimer;
    public HtmlElement paypalLoginDisclaimer;
    public HtmlElement paypalDisclaimerGcSection;

    public PaymentGuestSection applyGiftCard(GiftCard card) {
        addGiftCardButton.click();
        Wait.until(() -> giftCardNumber.isDisplayed());
        giftCardNumber.sendKeys(card.getNumber());
        giftCardCid.sendKeys(card.getCid());
        // @todo CAPTCHA!!!
        giftCardApplyButton.click();
        return this;
    }

    @Override
    public GuestCheckout addPaymentMethod(CreditCard card, ProfileAddress address) {
        fillCreditCardInfo(card);
        useShippingAddress(false);
        fillBillingInfo(address);
        fillContactInfo(address);
        return continueCheckout();
    }

    @Override
    public GuestCheckout addPaymentMethod(CreditCard card, String email) {
        fillCreditCardInfo(card);
        useShippingAddress(true);
        paymentEmail.sendKeys(email);
        return continueCheckout();
    }

    public PaymentGuestSection fillCreditCardInfo(CreditCard card) {
        if (!cardNumber.exists() || !cardNumber.isDisplayed()) {
            Wait.until(() -> paymentTypeRadioGroup.exists());
            paymentTypeRadioGroup.selectByIndex(0);
            Wait.until(() -> cardNumber.isDisplayed());
        }
        cardNumber.sendKeys(card.getCardNumber());
        cardType.selectByVisibleText(card.getCardType().name);
        if (card.getExpiryMonthIndex() != null) {
            expiryMonth.selectByVisibleText(card.getExpiryMonthIndex());
            expiryYear.selectByVisibleText(card.getExpiryYear());
            securityCode.sendKeys(card.getSecurityCode());
        }
        return this;
    }

    public PaymentGuestSection useShippingAddress(boolean usage) {
        useShippingAddress.set(usage);
        Wait.until(() -> (firstName.exists() && firstName.isDisplayed()) != usage);
        return this;
    }

    public PaymentGuestSection fillBillingInfo(ProfileAddress address) {
        Wait.until(firstName::isDisplayed);
        firstName.sendKeys(address.getFirstName());
        lastName.sendKeys(address.getLastName());
        addressLine_1.sendKeys(address.getAddressLine1());
        addressLine_2.sendKeys(address.getAddressLine2());
        addressCity.sendKeys(address.getCity());
        if (StepUtils.macys()) {
            addressState.selectByVisibleText(getStateAbbreviation(address.getState()));
        } else {
            addressState.selectByVisibleText(translateStateAbbreviation(getStateAbbreviation(address.getState())));
        }
        addressZipCode.sendKeys(address.getZipCode());
        return this;
    }

    public PaymentGuestSection fillContactInfo(ProfileAddress address) {
        phoneNumber.sendKeys(address.getBestPhone());
        paymentEmail.sendKeys(address.getEmail());
        return this;
    }

    public GuestCheckout continueCheckout() {
        continuePaymentCheckoutButton.click();
        return Navigate.get(GuestCheckout.class);
    }

    @Override
    public String getPaymentSummarySectionInfo() {
        StringBuilder output = new StringBuilder();
        for (HtmlElement element : paymentSummarySection)
            output.append(element.getText());
            output.append("\n");
        return output.toString();
    }

    public void edit() {
        editPaymentSection.click();
        this.waitForReady();
    }

    public List<String> getCreditCardFieldLevelErrors() {
        return creditCardFieldErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public Boolean waitForEditIsDisplayed() {
        return Wait.until(() -> (editPaymentSection.isDisplayed()), 10);
    }

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(() -> (cardNumber.exists() && cardNumber.isDisplayed())
                || (continueWithPaypal.exists() && continueWithPaypal.isDisplayed()), 30);
    }
}
