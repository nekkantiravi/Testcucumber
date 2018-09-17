package com.macys.sdt.projects.Marketing.OCWallet.actions.website.bcom.pages;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet.getExpMon;


public class MyWallet extends StepUtils {

    private static CreditCard.CardType defaultCard = null;

    public static ArrayList<CreditCard> getCardsInWallet() {

        ArrayList<CreditCard> cardsInWallet = new ArrayList<>();
        String expMonth = null, expMonthIndex = null, expYear = null;
        List<WebElement> cardElements = null;
        String expDate;
        String cardType;
        String cardNum = null;
        CreditCard.CardType enumCardType = null;

        cardElements = Elements.findElements(Elements.element("my_bwallet.card_row"));
        for (WebElement cardElement : cardElements) {
            cardType = cardElement.findElement(Elements.element("my_bwallet.credit_card_types")).getText().trim();
//                cardType = cardType.replace("®", "");
            cardType = cardType.equalsIgnoreCase("Bloomingdale's Card") ? "Bloomingdale's" : cardType;
            cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express® Card") ? "Bloomingdale's American Express" : cardType;
            enumCardType = CreditCard.CardType.fromString(cardType);
            cardNum = cardElement.findElement(Elements.element("my_bwallet.credit_card_numbers")).getText();
            cardNum = cardNum.substring(cardNum.lastIndexOf('*') + 1);
            expDate = cardElement.findElement(Elements.element("my_bwallet.credit_card_expirations")).getText().trim();
            if (!expDate.isEmpty()) {
                expMonth = getExpMon(expDate);
                expMonthIndex = expDate.substring(0, 2);
                expYear = expDate.substring(3, 5);
            }
            cardsInWallet.add(new CreditCard(enumCardType, cardNum, "", 0, expMonth, expMonthIndex, expYear));


        }
        return cardsInWallet;
    }

}



