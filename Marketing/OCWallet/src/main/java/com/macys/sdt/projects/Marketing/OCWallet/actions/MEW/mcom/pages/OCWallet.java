package com.macys.sdt.projects.Marketing.OCWallet.actions.MEW.mcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import org.junit.Assert;

import static com.macys.sdt.framework.utils.TestUsers.getValidVisaCreditCard;

/**
 * Created by YC03673 on 11/22/2017.
 */

public class OCWallet extends StepUtils {
    /**
     * add credit card details to account
     *
     * @param creditCard credit card details
     * @param customer   customer details
     */
    public static void updateCreditCard(CreditCard creditCard, UserProfile customer) {
        String cardType;
        if (creditCard == null) {
            creditCard = getValidVisaCreditCard();
        }
        if (customer == null) {
            customer = TestUsers.getCustomer(null);
        }

        Wait.untilElementPresent("credit_card.card_type");
        cardType = creditCard.getCardType().name.equals("Employee Card") ? "Macy's Employee Card" : creditCard.getCardType().name;
        DropDowns.selectByText("credit_card.card_type", cardType);
        TextBoxes.typeTextbox("credit_card.card_number", creditCard.getCardNumber());
        if (!(creditCard.getCardType().name.equalsIgnoreCase("Macy's") || creditCard.getCardType().name.equalsIgnoreCase("Employee Card"))) {
            DropDowns.selectByText("credit_card.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
            DropDowns.selectByText("credit_card.expiry_year", creditCard.getExpiryYear());
        }
        TextBoxes.typeTextbox("credit_card.first_name", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("credit_card.last_name", customer.getUser().getProfileAddress().getLastName());
        TextBoxes.typeTextbox("credit_card.address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("credit_card.address_line_2", customer.getUser().getProfileAddress().getAddressLine2() == null ? "" : customer.getUser().getProfileAddress().getAddressLine2());
        TextBoxes.typeTextbox("credit_card.address_city", customer.getUser().getProfileAddress().getCity());
        DropDowns.selectByValue("credit_card.address_state", AbbreviationHelper.getStateAbbreviation(customer.getUser().getProfileAddress().getState()));
        TextBoxes.typeTextbox("credit_card.address_zip_code", String.valueOf(customer.getUser().getProfileAddress().getZipCode()));
        if (bloomingdales() || onPage("shipping_payment_signed_in")) {
            TextBoxes.typeTextbox("credit_card.card_phone_area_code", customer.getUser().getProfileAddress().getPhoneAreaCode());
            TextBoxes.typeTextbox("credit_card.card_phone_exchange", customer.getUser().getProfileAddress().getPhoneExchange());
            TextBoxes.typeTextbox("credit_card.card_phone_subscriber", customer.getUser().getProfileAddress().getPhoneSubscriber());
        } else {
            TextBoxes.typeTextbox("credit_card.phone_number", customer.getUser().getProfileAddress().getBestPhone());
        }
        TextBoxes.typeTextbox("credit_card.payment_email", customer.getUser().getProfileAddress().getEmail());
        Clicks.click("credit_card.apply_changes");
        Assert.assertTrue("ERROR-ENV: Unable to add a credit card successfully", Wait.untilElementPresent("oc_my_wallet.credit_cards"));
    }

}
