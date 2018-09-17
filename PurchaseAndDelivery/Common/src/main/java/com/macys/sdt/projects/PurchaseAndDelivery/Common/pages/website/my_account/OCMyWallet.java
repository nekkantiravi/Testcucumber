package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.shared.actions.website.mcom.panels.my_account.CreditCardDialog;
import org.openqa.selenium.NoSuchElementException;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Image;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.List;

/**
 * Created by atepliashin on 12/8/16.
 */
@FindBy(jsonPath = "oc_my_wallet")
public class OCMyWallet extends Page {

    public HtmlElement mmoneyEvent;
    public Link mmoneyEventLink;
    public List<Image> mmoneyRewardCardLogos;
    public List<HtmlElement> mbmoneyRewardCardBalance;
    public List<HtmlElement> mbmoneyRewardCardNumbers;
    public List<HtmlElement> mbmoneyRewardCardExpiryDates;
    public List<Button> printCards;

    public HtmlElement addCreditCard;
    public HtmlElement creditCardType;
    public HtmlElement creditCardOverlay;

    public void addCard(CreditCard creditCard) {
        if (Wait.until(addCreditCard::isDisplayed)) {
            if (!Wait.until(creditCardType::isDisplayed)) {
                addCreditCard.click();
            }
            Wait.until(creditCardOverlay::isDisplayed);
            CreditCardDialog.addCreditCard(creditCard);
        } else {
            throw new NoSuchElementException("Can't find addCreditCard element");
        }

    }

    public void addCard() {
        addCard(TestUsers.getValidVisaCreditCard());
    }
}
