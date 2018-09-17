package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.shared.actions.website.mcom.panels.my_account.CreditCardDialog;
import com.macys.sdt.shared.utils.CommonUtils;
import org.openqa.selenium.NoSuchElementException;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by atepliashin on 2/8/17.
 */
@FindBy(jsonPath = "my_bwallet")
public class BWallet extends Page {

    public HtmlElement addCreditCardBtn;
    public HtmlElement creditCardTypes;
    public HtmlElement creditCardOverlay;


    public BWallet addCreditCardFromBWallet(CreditCard card, ProfileAddress address) {
        UserProfile profile = TestUsers.getCustomer(null);
        profile.getUser().setProfileAddress(address);
        CommonUtils.addCreditCardFromBWallet(card, profile);
        return this;
    }

    public void addCard() {
        addCard(TestUsers.getValidVisaCreditCard());
    }

    public void addCard(CreditCard creditCard) {
        if (Wait.until(addCreditCardBtn::isDisplayed)) {
            if (!Wait.until(creditCardTypes::isDisplayed)) {
                addCreditCardBtn.click();
            }
            Wait.until(creditCardOverlay::isDisplayed);
            CreditCardDialog.addCreditCard(creditCard);
        } else {
            throw new NoSuchElementException("Can't find addCreditCard element");
        }

    }
}
