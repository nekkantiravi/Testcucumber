package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.BWallet;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.interfaces.IPaymentSection;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;

import static com.macys.sdt.framework.utils.AbbreviationHelper.getStateAbbreviation;
import static com.macys.sdt.framework.utils.AbbreviationHelper.translateStateAbbreviation;
import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.get;

/**
 * Created by atepliashin on 2/2/17.
 */
@FindBy(jsonPath = "responsive_payment_signin_section")
public class PaymentSignedInSection extends Page implements IPaymentSection {

    public Button addNewCard;
    public Button editCard;
    public Radio paymentTypeRadioGroup;
    public TextInput cardNumber;
    public Select cardType;
    public Select expiryYear;
    public Select expiryMonth;
    public TextInput securityCode;
    public Button continueWithPaypal;
    public HtmlElement paypalRadioButton;
    public HtmlElement creditCardRadioButton;
    public Button addGiftCardButton;

    public CheckBox useShippingAddress;
    public TextInput firstName;
    public TextInput lastName;
    public TextInput addressLine_1;
    public TextInput addressLine_2;
    public TextInput addressCity;
    public Select addressState;
    public TextInput addressZipCode;

    public TextInput paymentEmail;
    public TextInput phoneNumber;

    public Button saveCard;

    public Button savePaymentButton;
    public Button cancelPaymentChangeButton;
    public Button editCreditCard;
    public Button changeCreditCardButton;

    public HtmlElement paymentCreditCardSummary;
    public HtmlElement paymentCreditCardSectionTitle;

    public Button changePaymentButton;
    public HtmlElement paymentInfo;
    public HtmlElement creditCardInfo;
    public HtmlElement paypalInfo;
    public HtmlElement paypalDisclaimer;
    public HtmlElement paypalLoginDisclaimer;

    public Button changePaypalButton;
    public Button savePaypalButton;
    public Button cancelPaypalChangeButton;

    public List<HtmlElement> creditCardSelects;
    public HtmlElement makeBlmDefaultMsg;
    public HtmlElement makeBlmDefaultLink;
    public HtmlElement defaultCardUpdatedSuccessMsg;

    public HtmlElement shippingAddress;

    public AddressInfoSection addressInfoSection() {
        return get(AddressInfoSection.class);
    }

    @Override
    public SignedInCheckout addPaymentMethod(CreditCard card, ProfileAddress address) {
        initiateNewCardAddition();
        fillCreditCardInfo(card);
        useShippingAddress(false);
        fillBillingInfo(address);
        fillContactInfo(address);
        saveCard();
        return Navigate.get(SignedInCheckout.class);
    }

    @Override
    public SignedInCheckout addPaymentMethod(CreditCard card, String email) {
        initiateNewCardAddition();
        fillCreditCardInfo(card);
        useShippingAddress(true);
        paymentEmail.sendKeys(email);
        saveCard();
        return Navigate.get(SignedInCheckout.class);
    }

    public PaymentSignedInSection fillCreditCardInfo(CreditCard card) {
        if(!cardNumber.exists()){
            Wait.until(() -> cardNumber.exists());
        }
        cardNumber.clear();
        cardNumber.sendKeys(card.getCardNumber());
        cardType.selectByVisibleText(card.getCardType().name);
        if (!card.getExpiryMonthIndex().isEmpty()) {
            expiryMonth.selectByVisibleText(card.getExpiryMonthIndex());
            expiryYear.selectByVisibleText(card.getExpiryYear());

        }

        return this;
    }

    public PaymentSignedInSection initiateNewCardAddition() {
        if (!addNewCard.exists()) {
            paymentTypeRadioGroup.selectByIndex(0);
            Wait.until(() -> addNewCard.exists());
        }
        if (!cardNumber.exists()) {
            addNewCard.click();
            Wait.until(() -> cardNumber.exists());
        }
        return this;
    }

    public PaymentSignedInSection fillBillingInfo(ProfileAddress address) {
        firstName.sendKeys(address.getFirstName());
        lastName.sendKeys(address.getLastName());
        addressLine_1.sendKeys(address.getAddressLine1());
        addressLine_2.sendKeys(address.getAddressLine2());
        addressCity.sendKeys(address.getCity());
        String stateName = translateStateAbbreviation(getStateAbbreviation(address.getState()));
        addressState.selectByVisibleText(stateName);
        addressZipCode.sendKeys(address.getZipCode().toString());
        return this;
    }

    public PaymentSignedInSection fillContactInfo(ProfileAddress address) {
        phoneNumber.sendKeys(address.getBestPhone());
        paymentEmail.sendKeys(address.getEmail());
        return this;
    }

    public PaymentSignedInSection useShippingAddress(boolean usage) {
        if (macys()) {
            useShippingAddress.set(usage);
        } else {
            String attrValue = useShippingAddress.findElement(By.xpath("../input")).getAttribute("class");
            if (attrValue.contains("checked") != usage) {
                useShippingAddress.click();
            }
        }
        Wait.until(() -> (firstName.exists() && firstName.isDisplayed()) != usage);
        return this;
    }

    @Override
    public String getPaymentSummarySectionInfo() {
        return paymentCreditCardSummary.getText();
    }

    public PaymentSignedInSection saveCard() {
        saveCard.click();
        Wait.until(() -> securityCode.isDisplayed(), 30);
        return this;
    }

    public SignedInCheckout submitBySecurityCode(String code) {
        Wait.until(() -> securityCode.isDisplayed(), 30);
        securityCode.sendKeys(code);
        return Navigate.get(SignedInCheckout.class);
    }

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(() -> (addNewCard.exists() && addNewCard.isDisplayed())
                || (continueWithPaypal.exists() && continueWithPaypal.isDisplayed())
                || (securityCode.exists() && securityCode.isDisplayed()), 30);
    }

    private PaymentSignedInSection addCreditCardFromBWallet(CreditCard card, String email) {
        ProfileAddress address = new ProfileAddress();
        TestUsers.getRandomValidAddress(null, address);
        address.setEmail(email);
        return addCreditCardFromBWallet(card, address);
    }

    private PaymentSignedInSection addCreditCardFromBWallet(CreditCard card, ProfileAddress address) {
        addNewCard.click();
        Navigate.get(BWallet.class).addCreditCardFromBWallet(card, address);
        try {
            Navigate.to(SignedInCheckout.class);
        } catch (EnvException e) {
            throw new AssertionError(e);
        }
        return this;
    }
}
