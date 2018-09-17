package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.model.GiftCard;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by atepliashin on 12/6/16.
 */
@FindBy(jsonPath = "responsive_gift_cards_section")
public class SignedInGiftCardsSection extends Page {

    public HtmlElement giftCardsSection;
    public HtmlElement giftCardsSectionText;
    public List<Button> applyRewardCard;
    public List<HtmlElement> appliedGiftCard;
    public List<HtmlElement> appliedGiftCardValue;
    public TextInput giftCardNumber;
    public TextInput giftCardCid;
    public TextInput giftCardCaptcha;
    public Button applyGiftCard;
    public HtmlElement gcPaypalNote;
    public HtmlElement expandGiftCardSection;
    public HtmlElement giftCardCancel;

    public SignedInGiftCardsSection applyRewardCard(int index) {
        applyRewardCard.get(index).click();
        return this;
    }

    public SignedInGiftCardsSection applyGiftCard(GiftCard card) {
        giftCardNumber.sendKeys(card.getNumber());
        giftCardCid.sendKeys(card.getCid());
        // @todo CAPTCHA!!!
        applyGiftCard.click();
        return this;
    }

    public SignedInGiftCardsSection expand() {
        if (giftCardsSectionText.exists()) {
            giftCardsSection.click();
            Wait.until(() -> !giftCardsSectionText.exists());
        }
        return this;
    }

}
