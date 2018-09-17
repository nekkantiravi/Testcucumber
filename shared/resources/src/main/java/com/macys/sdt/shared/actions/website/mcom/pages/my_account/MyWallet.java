package com.macys.sdt.shared.actions.website.mcom.pages.my_account;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.panels.my_account.CreditCardDialog;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyWallet extends StepUtils {
    public static void addCard() {
        CreditCard visaCreditCard = TestUsers.getValidVisaCreditCard();
        addCard(visaCreditCard);
    }

    public static void addCard(CreditCard creditCard) {
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        try {
            Wait.untilElementPresent(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            if (!Elements.elementPresent(pageName + ".credit_card_type")) {
                Clicks.click(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            }
//            Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
            Wait.untilElementPresent(pageName + ".credit_card_overlay");
            CreditCardDialog.addCreditCard(creditCard);
        } catch (Exception e) {
            Assert.fail("Unable to add the card successfully to the wallet " + e);
        }
    }


    public static void addCard(CreditCard creditCard, boolean setAsDefault) {
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        try {
            Wait.untilElementPresent(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            if (!Elements.elementPresent(pageName + ".credit_card_type")) {
                Clicks.click(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            }
            Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
            CreditCardDialog.addCreditCard(creditCard, setAsDefault);
        } catch (Exception e) {
            Assert.fail("Unable to add the card successfully to the wallet " + e);
        }
    }

    /**
     * Public method to add a credit card with specified creditCard and profile address
     *
     * @param creditCard of type CreditCard
     * @param address    of type ProfileAddress
     */
    public static void addCard(CreditCard creditCard, ProfileAddress address) {
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        try {
            Wait.untilElementPresent(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            if (!Elements.elementPresent(pageName + ".credit_card_type")) {
                Clicks.click(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            }
            Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
            CreditCardDialog.addCreditCard(creditCard, address);
        } catch (Exception e) {
            Assert.fail("Unable to add the card successfully to the wallet " + e);
        }
    }

    /**
     * Method to return list of offers
     *
     * @return offers list
     */
    public static List<Map<String, Object>> offersList() {
        List<Map<String, Object>> offersList = new ArrayList<>();
        if (macys()) {
            for (int i = 0; i < offerElements().size(); i++) {
                Map<String, Object> offer = new HashMap<>();
                offer.put("offerName", Elements.findElements(Elements.element("oc_my_wallet.offer_names")).get(i).getText());
                offer.put("specialRedemptionCode", Elements.findElements(Elements.element("oc_my_wallet.special_redemption_codes")).get(i).getText());
                offer.put("offerDate", Elements.findElements(Elements.element("oc_my_wallet.offer_dates")).get(i).getText());
                offer.put("isDetailsExclusionsAvailable", Elements.findElements(Elements.element("oc_my_wallet.view_details_and_exclusions")).get(i).isDisplayed());
                offer.put("isRemoveOfferAvailable", Elements.findElements(Elements.element("oc_my_wallet.delete_offers")).get(i).isDisplayed());
                offersList.add(offer);
            }
        } else {
            for (int i = 0; i < offerElements().size(); i++) {
                Map<String, Object> offer = new HashMap<>();
                if (offerElements().size() >= 1 && !offerElements().get(0).getText().contains("There are no valid offers available in your bWallet")) {
                    if (!offerElements().get(i).findElement(Elements.element("my_bwallet.online_codes")).isDisplayed()) {
                        Elements.findElements(Elements.element("my_bwallet.view_details_and_exclusions")).get(i).click();
                    }
                    offer.put("offerName", Elements.findElements(Elements.element("my_bwallet.offer_names")).get(i).getText());
                    offer.put("offerType", Elements.findElements(Elements.element("my_bwallet.offer_types")).get(i).getText());
                    offer.put("isDetailsExclusionsAvailable", Elements.findElements(Elements.element("my_bwallet.view_details_and_exclusions")).get(i).isDisplayed());
                    offer.put("onlineCode", offerElements().get(i).findElement(Elements.element("my_bwallet.online_codes")).getText());

                    String instoreCode = null;
                    if (offerElements().get(i).findElements(Elements.element("my_bwallet.instore_codes")).size() == 1) {
                        instoreCode = offerElements().get(i).findElement(Elements.element("my_bwallet.instore_codes")).getText();
                    }
                    offer.put("instoreCode", instoreCode);

                    String legalDisclaimer = null;
                    if (offerElements().get(i).findElements(Elements.element("my_bwallet.legal_disclaimer")).size() == 1) {
                        legalDisclaimer = offerElements().get(i).findElement(Elements.element("my_bwallet.legal_disclaimer")).getText();
                    }
                    offer.put("legalDisclaimer", legalDisclaimer);

                    offersList.add(offer);
                }
            }
        }
        return offersList;
    }

    /**
     * Private method to return the list of offer container elements
     *
     * @return offer container elements
     */
    private static List<WebElement> offerElements() {
        List<WebElement> offerElementsList;
        if (macys()) {
            offerElementsList = Elements.findElement(Elements.element("oc_my_wallet.offers_container")).findElements(Elements.element("oc_my_wallet.offer_row"));
        } else {
            offerElementsList = Elements.findElement(Elements.element("my_bwallet.offers_container")).findElements(Elements.element("my_bwallet.offer_row"));
        }
        return offerElementsList;
    }

    /**
     * Public method to update the credit card with another card details
     *
     * @param card1 of type CreditCard
     * @param card2 of type CreditCard
     * @throws Throwable if any exception occurs
     */
    public static void updateCardDetails(CreditCard card1, CreditCard card2) throws Throwable {
        openEditCard(card1);
        CreditCardDialog.updateCreditCardToAnotherCard(card2);
    }

    /**
     * Public method to update the credit card with specified profile address
     *
     * @param card    of type CreditCard
     * @param address of type ProfileAddress
     * @throws Throwable if any exception occurs
     */
    public static void updateCardAddress(CreditCard card, ProfileAddress address) throws Throwable {
        openEditCard(card);
        Thread.sleep(2000);
        CreditCardDialog.updateCreditCardAddress(address);
    }

    /**
     * Public method to open Edit credit card for a specified credit card
     *
     * @param card of type CreditCard
     * @throws Throwable if any exception occurs
     */
    public static void openEditCard(CreditCard card) throws Throwable {
        List<WebElement> cardRowElementsList;
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        String cardType = card.getCardType().name;
        cardType = cardType.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
        cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
        Navigate.browserRefresh();
        WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        Wait.untilElementPresent(pageName + ".credit_cards_container");
        cardRowElementsList = Elements.findElements(pageName + ".card_row");
        for (WebElement cardRowElement : cardRowElementsList) {
            if (cardRowElement.findElement(Elements.element(macys() ? "oc_my_wallet.card_type_name" : "my_bwallet.credit_card_types")).getText().contains(cardType)) {
                cardRowElement.findElement(Elements.element(macys() ? "oc_my_wallet.edit_card" : "my_bwallet.edit_credit_cards")).click();
                Wait.untilElementPresent(pageName + ".credit_card_overlay");
                break;
            }

        }
    }

    /**
     * Public method to opens up the Add Card overlay
     */
    public static void openAddCard() {
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        try {
            Wait.untilElementPresent(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            if (!Elements.elementPresent(pageName + ".credit_card_type")) {
                Clicks.click(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            }
            Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
        } catch (Exception e) {
            Assert.fail("Unable to open Add A Credit/Debit Card Overlay successfully from the wallet page" + e);
        }
    }

    /**
     * Public method to Delete All Cards from Wallet page
     * @throws Throwable if any exception occurs
     */
    public static void deleteAllCardsIfPresent() throws Throwable {
        List<WebElement> cardRowElementsList;
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        if (Elements.elementPresent(pageName + ".credit_cards_container")) {
            cardRowElementsList = Elements.findElements(pageName + ".card_row");
            for (WebElement cardRowElement : cardRowElementsList) {
                cardRowElement.findElement(Elements.element(macys() ? "oc_my_wallet.edit_card" : "my_bwallet.edit_credit_cards")).click();
                if (macys()) {
                    Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
                    Elements.findElement("credit_card_dialog.delete_card").click();
                    Wait.forPageReady();
                    if (WebDriverManager.getWebDriver().switchTo().alert() != null) {
                        Alert deleteCardAlert = WebDriverManager.getWebDriver().switchTo().alert();
                        deleteCardAlert.accept();
                    }
                } else {
                    Wait.untilElementPresent("my_bwallet.yes_delete_card");
                    Clicks.click("my_bwallet.yes_delete_card");
                    Wait.forPageReady();

                }


            }
        } else {
            Assert.fail("No Credit Cards displayed in Wallet Page");
        }

    }

    /**
     * Public method to open Delete Card alert/popup for a specified credit card
     *
     * @param cardToDelete of type CreditCard to be deleted
     * @throws Throwable if any exception occurs
     */
    public static void openDeleteCardPopup(CreditCard cardToDelete) throws Throwable {
        List<WebElement> cardRowElementsList;
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        String cardType = cardToDelete.getCardType().name;
        cardType = cardType.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
        cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
        WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (Elements.elementPresent(pageName + ".credit_cards_container")) {
            cardRowElementsList = Elements.findElements(pageName + ".card_row");
            for (WebElement cardRowElement : cardRowElementsList) {
                if (cardRowElement.findElement(Elements.element(macys() ? "oc_my_wallet.card_type_name" : "my_bwallet.credit_card_types")).getText().contains(cardType)) {
                    cardRowElement.findElement(Elements.element(macys() ? "oc_my_wallet.edit_card" : "my_bwallet.delete_credit_cards")).click();
                    if (macys()) {
                        Elements.findElement("credit_card_dialog.delete_card").click();
                        Wait.forPageReady();
                        break;
                    } else {
                        Wait.untilElementPresent("my_bwallet.yes_delete_card");
                        break;

                    }

                } else Assert.fail("Specified Card Type " + cardType + " not found in Wallet");

            }
        } else {
            Assert.fail("No Credit Cards displayed in Wallet Page");
        }
    }

    /**
     * Public method to return the Message from Delete Card alert/popup
     *
     * @return alert Message of type String to be deleted
     */
    public static String getMessageFromAlert() {
        String deleteAlertMsg;
        try {
            if (macys()) {
                if (WebDriverManager.getWebDriver().switchTo().alert() != null) {
                    Alert deleteCardAlert = WebDriverManager.getWebDriver().switchTo().alert();
                    deleteAlertMsg = deleteCardAlert.getText();
                    deleteCardAlert.accept();
                    return deleteAlertMsg;
                }

            } else {
                deleteAlertMsg = Elements.findElement("my_bwallet.delete_card_popup_message").getText();
                Clicks.click("my_bwallet.yes_delete_card");
                return deleteAlertMsg;
            }
        } catch (Exception e) {
            Assert.fail("No Alert Exception, Alert not displayed after clicking on Delete Card button.");
        }
        return null;
    }

    /**
     * Public method to Delete a specified credit card
     *
     * @param cardToBeDeleted of type CreditCard to be deleted
     * @throws Throwable if any exception occurs
     */
    public static void deleteCard(CreditCard cardToBeDeleted) throws Throwable {
        openDeleteCardPopup(cardToBeDeleted);
        try {
            if (macys()) {
                if (WebDriverManager.getWebDriver().switchTo().alert() != null) {
                    Alert deleteCardAlert = WebDriverManager.getWebDriver().switchTo().alert();
                    Wait.forPageReady();
                    System.out.print("Delete Card Alert is displayed.");
                    deleteCardAlert.accept();
                }

            } else {
                Elements.findElement("my_bwallet.yes_delete_card").click();
                Wait.forPageReady();
            }
            onPage(macys() ? "oc_my_wallet" : "my_bwallet");
        } catch (Exception e) {
            Assert.fail("No Alert Exception, Alert not displayed after clicking on Delete Card button.");
        }
    }

}