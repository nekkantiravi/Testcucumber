package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static com.macys.sdt.framework.utils.AbbreviationHelper.translateStateAbbreviation;
import static com.macys.sdt.framework.utils.AbbreviationHelper.getStateAbbreviation;

/**
 * Created by atepliashin on 1/19/17.
 */
@FindBy(jsonPath = "repayment")
public class Repayment extends Page {

    public HtmlElement declinedMessage;
    public Select cardType;
    public Select month;
    public Select year;
    public Select state;
    public TextInput cardNumber;
    public TextInput securityCode;
    public TextInput firstName;
    public TextInput lastName;
    public TextInput addressLine1;
    public TextInput addressLine2;
    public TextInput city;
    public TextInput zipCode;
    public TextInput phoneNumber;
    public TextInput email;
    public Button retryPayment;
    public Button addNewCard;

    public Repayment fillCreditCardInfo(CreditCard card) {
        cardType.selectByVisibleText(card.getCardType().name);
        cardNumber.sendKeys(card.getCardNumber());
        if (card.getExpiryMonthIndex() != null) {
            month.selectByVisibleText(card.getExpiryMonthIndex());
            year.selectByVisibleText(card.getExpiryYear());
            securityCode.sendKeys(card.getSecurityCode());
        }
        return this;
    }

    public Repayment fillBillingAddress(ProfileAddress address) {
        Wait.until(() -> firstName.isDisplayed());
        firstName.sendKeys(address.getFirstName());
        lastName.sendKeys(address.getLastName());
        addressLine1.sendKeys(address.getAddressLine1());
        addressLine2.sendKeys(address.getAddressLine2());
        city.sendKeys(address.getCity());
        zipCode.sendKeys(address.getZipCode().toString());
        String stateName = translateStateAbbreviation(getStateAbbreviation(address.getState()));
        state.selectByVisibleText(stateName);
        return this;
    }

    public Repayment fillContactInfo(ProfileAddress address) {
        phoneNumber.sendKeys(address.getBestPhone().replaceAll("-", "")); // doesn't support formatting here
        email.sendKeys(address.getEmail());
        return this;
    }

    public OrderConfirmationRepayment retryPayment(CreditCard card, ProfileAddress address) {
        if (StepUtils.signedIn() && (!cardNumber.exists() || !cardNumber.isDisplayed())) {
            addNewCard.click();
            Wait.until(() -> cardNumber.isDisplayed());
        }
        fillCreditCardInfo(card);
        fillBillingAddress(address);
        fillContactInfo(address);
        retryPayment.click();
        return Navigate.get(OrderConfirmationRepayment.class);
    }

    // due to the defect can't expect jQuery.active == 0 at the moment
    @Override
    public void waitForReady() {
        Wait.until(() -> ((retryPayment.exists() && retryPayment.isDisplayed())
                || (addNewCard.exists() && addNewCard.isDisplayed())) &&
                (declinedMessage.exists() && declinedMessage.isDisplayed()), 30);
    }
}
