package com.macys.sdt.shared.actions.website.mcom.panels.my_account;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CreditCardDialog extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(CreditCardDialog.class);

    /**
     * Method to add a Visa credit card
     *
     * @throws Exception thrown if any exception found
     */
    public static void addCreditCard() throws Exception {
        CreditCard visaCreditCard = TestUsers.getValidVisaCreditCard();
        addCreditCard(visaCreditCard);
    }

    /**
     * Method to add a credit card
     *
     * @param creditCard credit card information(CreditCard class instance)
     */
    public static void addCreditCard(CreditCard creditCard) {
        if (bloomingdales()) {
            //yc03673 05/25/2017: added logic to match the card types on Wallet add credit card dialog.
            String cardType = creditCard.getCardType().name;
            if (onPage("my_bwallet")) {
                cardType = creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
                cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
            }
           DropDowns.selectCustomText("credit_card_dialog.card_type_list", "credit_card_dialog.card_type_options", cardType);
        } else {
            DropDowns.selectByText("credit_card_dialog.card_type", creditCard.getCardType().name);
        }
        TextBoxes.typeTextbox("credit_card_dialog.card_number", creditCard.getCardNumber());
        if (bloomingdales() && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's Employee Card") && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's")) {
            DropDowns.selectCustomText("credit_card_dialog.expiry_month_list", "credit_card_dialog.expiry_month_options", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectCustomText("credit_card_dialog.expiry_year_list", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
        } else if (macys() && !creditCard.getCardType().name.contentEquals("Macy's") && !creditCard.getCardType().name.contentEquals("Employee Card")) {
            DropDowns.selectByText("credit_card_dialog.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectByText("credit_card_dialog.expiry_year", creditCard.getExpiryYear());
        }
        HashMap<String, String> opts = new HashMap<>();


        opts.put("checkout_eligible", "true");
        ProfileAddress address = TestUsers.getCustomer(null).getUser().getProfileAddress();
        TextBoxes.typeTextbox("credit_card_dialog.first_name", address.getFirstName());
        TextBoxes.typeTextbox("credit_card_dialog.last_name", address.getLastName());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_1", address.getAddressLine1());
        //avoiding the null pointer exception when address line 2 is null
//        TextBoxes.typeTextbox("credit_card_dialog.address_line_2", address.getAddressLine2());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_2", address.getAddressLine2()!=null?address.getAddressLine2():" ");
        TextBoxes.typeTextbox("credit_card_dialog.address_city", address.getCity());
        String state = address.getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(address.getState()) : address.getState();
        if (bloomingdales()) {
            //yc03673 2017-05-31: address_state_list and address_state represent the same State drop down in Checkout page and Wallet page credit card section.
            // combined the two elements by moving the locators to address_state_list so that one element will work for both scenarios
            //DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", address.getState());
            DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", state);
        } else {
            DropDowns.selectByText("credit_card_dialog.address_state", state);
        }
        TextBoxes.typeTextbox("credit_card_dialog.address_zip_code", address.getZipCode());
        try{
            String phoneNumber[] = address.getBestPhone().split("-");
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", phoneNumber[0]);
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", phoneNumber[1]);
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", phoneNumber[2]);
        }catch(Exception e) {
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", address.getPhoneAreaCode());
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", address.getPhoneExchange());
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", address.getPhoneSubscriber());
        }
        TextBoxes.typeTextbox("credit_card_dialog.payment_email", TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        WebElement setAsDefaultCard = Elements.findElement("credit_card_dialog.set_as_a_default_card");
       /* if (!set_as_a_default_card.isSelected()) {
            Clicks.click(set_as_a_default_card);
        } */
        if (creditCard.getCardType().name.contentEquals("American Express")) {
            Clicks.click(setAsDefaultCard);
        }
        Clicks.click("credit_card_dialog.save_card");
    }


    /**
     * Method to add a credit card with given Address
     *
     * @param creditCard credit card information(CreditCard class instance)
     * @param address    profileAddress information(ProfileAddress class instance)
     */
    public static void addCreditCard(CreditCard creditCard, ProfileAddress address) {
        if (bloomingdales()) {
            //yc03673 05/25/2017: added logic to match the card types on Wallet add credit card dialog.
            String cardType = creditCard.getCardType().name;
            if (onPage("my_bwallet")) {
                cardType = creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
                cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
            }
            DropDowns.selectCustomText("credit_card_dialog.card_type_list", "credit_card_dialog.card_type_options", cardType);
        } else {
            DropDowns.selectByText("credit_card_dialog.card_type", creditCard.getCardType().name);
        }
        TextBoxes.typeTextbox("credit_card_dialog.card_number", creditCard.getCardNumber());
        if (bloomingdales() && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's Employee Card") && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's")) {
            DropDowns.selectCustomText("credit_card_dialog.expiry_month_list", "credit_card_dialog.expiry_month_options", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectCustomText("credit_card_dialog.expiry_year_list", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
        } else if (macys() && !creditCard.getCardType().name.contentEquals("Macy's") && !creditCard.getCardType().name.contentEquals("Employee Card")) {
            DropDowns.selectByText("credit_card_dialog.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectByText("credit_card_dialog.expiry_year", creditCard.getExpiryYear());
        }
        HashMap<String, String> opts = new HashMap<>();


        opts.put("checkout_eligible", "true");

        TextBoxes.typeTextbox("credit_card_dialog.first_name", address.getFirstName());
        TextBoxes.typeTextbox("credit_card_dialog.last_name", address.getLastName());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_1", address.getAddressLine1());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_2", address.getAddressLine2()!=null?address.getAddressLine2():"");
        TextBoxes.typeTextbox("credit_card_dialog.address_city", address.getCity());
        String state = address.getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(address.getState()) : address.getState();
        if (bloomingdales()) {
            //DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", address.getState());
            DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", state);
        } else {
            DropDowns.selectByText("credit_card_dialog.address_state", state);
        }
        TextBoxes.typeTextbox("credit_card_dialog.address_zip_code", address.getZipCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", address.getPhoneAreaCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", address.getPhoneExchange());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", address.getPhoneSubscriber());
        TextBoxes.typeTextbox("credit_card_dialog.payment_email", TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        WebElement setAsDefaultCard = Elements.findElement("credit_card_dialog.set_as_a_default_card");
       /* if (!set_as_a_default_card.isSelected()) {
            Clicks.click(set_as_a_default_card);
        } */
        if (creditCard.getCardType().name.contentEquals("American Express")) {
            Clicks.click(setAsDefaultCard);
        }
        Clicks.click("credit_card_dialog.save_card");
    }

    /**
     * Method to add a credit card
     * @param setAsDefault of type boolean
     * @param creditCard credit card information(CreditCard class instance)
     */
    public static void addCreditCard(CreditCard creditCard, boolean setAsDefault) {
        if (bloomingdales()) {
            //yc03673 05/25/2017: added logic to match the card types on Wallet add credit card dialog.
            String cardType = creditCard.getCardType().name;
            if (onPage("my_bwallet")) {
                cardType = creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
                cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
            }
            DropDowns.selectCustomText("credit_card_dialog.card_type_list", "credit_card_dialog.card_type_options", cardType);
        } else {
            DropDowns.selectByText("credit_card_dialog.card_type", creditCard.getCardType().name);
        }
        TextBoxes.typeTextbox("credit_card_dialog.card_number", creditCard.getCardNumber());
        if (bloomingdales() && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's Employee Card") && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's")) {
            DropDowns.selectCustomText("credit_card_dialog.expiry_month_list", "credit_card_dialog.expiry_month_options", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectCustomText("credit_card_dialog.expiry_year_list", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
        } else if (macys() && !creditCard.getCardType().name.contentEquals("Macy's") && !creditCard.getCardType().name.contentEquals("Employee Card")) {
            DropDowns.selectByText("credit_card_dialog.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectByText("credit_card_dialog.expiry_year", creditCard.getExpiryYear());
        }
        HashMap<String, String> opts = new HashMap<>();


        opts.put("checkout_eligible", "true");
        ProfileAddress address = TestUsers.getCustomer(null).getUser().getProfileAddress();
        TextBoxes.typeTextbox("credit_card_dialog.first_name", address.getFirstName());
        TextBoxes.typeTextbox("credit_card_dialog.last_name", address.getLastName());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_1", address.getAddressLine1());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_2", address.getAddressLine2());
        TextBoxes.typeTextbox("credit_card_dialog.address_city", address.getCity());
        if (bloomingdales()) {
            String state = address.getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(address.getState()) : address.getState();
            //yc03673 2017-05-31: address_state_list and address_state represent the same State drop down in Checkout page and Wallet page credit card section.
            // combined the two elements by moving the locators to address_state_list so that one element will work for both scenarios
            //DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", address.getState());
            DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", state);
        } else {
            DropDowns.selectByText("credit_card_dialog.address_state", AbbreviationHelper.translateStateAbbreviation(address.getState()));
        }
        TextBoxes.typeTextbox("credit_card_dialog.address_zip_code", address.getZipCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", address.getPhoneAreaCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", address.getPhoneExchange());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", address.getPhoneSubscriber());
        TextBoxes.typeTextbox("credit_card_dialog.payment_email", TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        WebElement setAsDefaultCard = Elements.findElement("credit_card_dialog.set_as_a_default_card");
       /* if (!set_as_a_default_card.isSelected()) {
            Clicks.click(set_as_a_default_card);
        } */
        if (setAsDefault) {
            Clicks.click(setAsDefaultCard);
        }
        Clicks.click("credit_card_dialog.save_card");
    }

    /**
     * Method to update a credit card
     *
     * @param profileAddress information(ProfileAddress class instance)
     */
    public static void updateCreditCardAddress(ProfileAddress profileAddress) {

        TextBoxes.typeTextbox("credit_card_dialog.first_name", profileAddress.getFirstName());
        TextBoxes.typeTextbox("credit_card_dialog.last_name", profileAddress.getLastName());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_1", profileAddress.getAddressLine1());
        TextBoxes.typeTextbox("credit_card_dialog.address_line_2", profileAddress.getAddressLine2()!=null?profileAddress.getAddressLine2():" ");
        TextBoxes.typeTextbox("credit_card_dialog.address_city", profileAddress.getCity());
        if (bloomingdales()) {
            String state = profileAddress.getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState();
            //yc03673 2017-05-31: address_state_list and address_state represent the same State drop down in Checkout page and Wallet page credit card section.
            // combined the two elements by moving the locators to address_state_list so that one element will work for both scenarios
            //DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", address.getState());
            DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", state);
        } else {
            DropDowns.selectByText("credit_card_dialog.address_state", AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()));
        }
        TextBoxes.typeTextbox("credit_card_dialog.address_zip_code", profileAddress.getZipCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", profileAddress.getPhoneAreaCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", profileAddress.getPhoneExchange());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", profileAddress.getPhoneSubscriber());
        TextBoxes.typeTextbox("credit_card_dialog.payment_email", profileAddress.getEmail());

        Clicks.click("credit_card_dialog.save_card");
    }


    /**
     * Method to update a credit card with another card type info
     *
     * @param creditCard is of CreditCard type object
     */
    public static void updateCreditCardToAnotherCard(CreditCard creditCard) {

        if (bloomingdales()) {
            String cardType = creditCard.getCardType().name;
            if (onPage("my_bwallet")) {
                cardType = creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
                cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
            }
            DropDowns.selectCustomText("credit_card_dialog.card_type_list", "credit_card_dialog.card_type_options", cardType);
        } else {
            DropDowns.selectByText("credit_card_dialog.card_type", creditCard.getCardType().name);
        }
        TextBoxes.typeTextbox("credit_card_dialog.card_number", creditCard.getCardNumber());
        if (bloomingdales() && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's Employee Card") && !creditCard.getCardType().name.equalsIgnoreCase("Bloomingdale's")) {
            DropDowns.selectCustomText("credit_card_dialog.expiry_month_list", "credit_card_dialog.expiry_month_options", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectCustomText("credit_card_dialog.expiry_year_list", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
        } else if (macys() && !creditCard.getCardType().name.contentEquals("Macy's") && !creditCard.getCardType().name.contentEquals("Employee Card")) {
            DropDowns.selectByText("credit_card_dialog.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectByText("credit_card_dialog.expiry_year", creditCard.getExpiryYear());
        }

        Clicks.click("credit_card_dialog.save_card");
    }

}
