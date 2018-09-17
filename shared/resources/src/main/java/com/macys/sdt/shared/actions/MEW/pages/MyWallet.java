package com.macys.sdt.shared.actions.MEW.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MyWallet extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyWallet.class);
    private static CreditCard.CardType defaultCard = null;
    /**
     * Delete all credit cards from OC Wallet page
     */
    public static void deleteCreditCard() {
        if (Wait.untilElementPresent("oc_my_wallet.cc_container")) {
            int cc_size = Elements.findElements("oc_my_wallet.credit_cards").size();
            for (int i = 0; i < cc_size; i++) {
                if (Elements.findElements("oc_my_wallet.credit_cards").size() == 1) {
                    Clicks.click("oc_my_wallet.credit_cards");
                } else {
                    Clicks.click(Elements.findElements("oc_my_wallet.credit_cards").get(i));
                }
                Clicks.clickWhenPresent("oc_my_wallet.delete_card");
                Clicks.clickWhenPresent("oc_my_wallet.confirmation_yes_button");
            }
        } else {
            logger.info("No Credit Cards founds");
        }
    }

    public static ArrayList<CreditCard> getCardsInWallet() {

        ArrayList<CreditCard> cardsInWallet = new ArrayList<>();
        String expMonth = null, expMonthIndex = null, expYear = null;
        List<WebElement> cardElements = null;
        String expDate;
        String cardType;
        String cardNum = null;
        CreditCard.CardType enumCardType = null;
        if (macys()) {
            cardElements = Elements.findElements("oc_my_wallet.credit_card_data");
            for (WebElement e : cardElements) {

                String cardDetails = e.getText();
                String[] splitCardDetails = cardDetails.split("\\r?\\n");

                for (int i = 0; i < splitCardDetails.length; i++) {

                    if (i == 0) {
//                        int index = splitCardDetails[i].indexOf("*");
                        int index = splitCardDetails[i].length();
                        if (splitCardDetails[i].contains("®")) {
                            cardType = splitCardDetails[i].substring(0, index - 1);

                        } else {
                            cardType = splitCardDetails[i].substring(0, index);
                        }
                        cardType = cardType.equalsIgnoreCase("Macy's Employee Card")?"Employee Card":cardType;
                        cardType = cardType.equalsIgnoreCase("American Express Card")?"American Express":cardType;
                        enumCardType = CreditCard.CardType.fromString(cardType);
//                        cardNum = splitCardDetails[i].substring(splitCardDetails[i].lastIndexOf("*") + 1);
                    }

                    if (i == 1) {
                        if (splitCardDetails[i].contains(", exp ")) {
                            String[] cardNumAndExp = splitCardDetails[i].split(", exp ");
                            cardNum = cardNumAndExp[0].substring(cardNumAndExp[0].lastIndexOf("*") + 1);
                            expDate = cardNumAndExp[1].trim();
                            if (!expDate.isEmpty()) {
                                String[] expMMYYYY = expDate.split("/");
                                expMonth = getExpMon(expMMYYYY[0]);
                                expMonthIndex = expMMYYYY[0].length()==1?"0"+expMMYYYY[0]:expMMYYYY[0];
                                expYear = expMMYYYY[1];
                            }
                        } else {
                            cardNum = splitCardDetails[i].substring(splitCardDetails[i].lastIndexOf("*") + 1);
//                            break;
                        }
                    }
                    if (i==2 && splitCardDetails[i].equalsIgnoreCase("Default Card")) {

                            defaultCard = enumCardType;
                    }
                }

                cardsInWallet.add(new CreditCard(enumCardType, cardNum, "", 0, expMonth, expMonthIndex, expYear));
            }
            return cardsInWallet;
        } else {
            return getCardsInBCOMWallet();

        }
    }

    public static ArrayList<CreditCard> getCardsInBCOMWallet() {

        ArrayList<CreditCard> cardsInWallet = new ArrayList<>();
        String expMonth = null, expMonthIndex = null, expYear = null;
        List<WebElement> cardElements = null;
        String expDate;
        String cardType;
        String cardNum = null;
        CreditCard.CardType enumCardType = null;

        cardElements = Elements.findElements(Elements.element("oc_my_wallet.card_row"));
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
                expYear = expDate.substring(3, 7);
            }
            cardsInWallet.add(new CreditCard(enumCardType, cardNum, "", 0, expMonth, expMonthIndex, expYear));


        }
        return cardsInWallet;
    }

    public static String getExpMon(String value) {

        String extractExpMonth;
        String month = null;
        if (value.length() == 1) {
            extractExpMonth = "0"+value;
        } else {
            extractExpMonth = value;
        }

        switch (extractExpMonth) {

            case "01":
            case "1":
                month = "January";
                break;
            case "02":
            case "2":
                month = "February";
                break;
            case "03":
            case "3":
                month = "March";
                break;
            case "04":
            case "4":
                month = "April";
                break;
            case "05":
            case "5":
                month = "May";
                break;
            case "06":
            case "6":
                month = "June";
                break;
            case "07":
            case "7":
                month = "July";
                break;
            case "08":
            case "8":
                month = "August";
                break;
            case "09":
            case "9":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
        }

        return month;
    }
}
