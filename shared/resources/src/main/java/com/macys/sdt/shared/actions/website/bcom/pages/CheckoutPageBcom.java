package com.macys.sdt.shared.actions.website.bcom.pages;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.utils.CheckoutUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CheckoutPageBcom extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutPageBcom.class);
    private UserProfile customer;
    private boolean bops;

    public CheckoutPageBcom(HashMap<String, String> opts, boolean bops) {
        customer = TestUsers.getCustomer(opts);
        this.bops = bops;
    }

    public CheckoutPageBcom(UserProfile customer, boolean bops) {
        this.customer = customer;
        this.bops = bops;
    }

    /**
     * Fills shipping information during checkout for guest user
     *
     * @param iship true if iship order, else false
     */
    public void fillGuestShippingData(boolean iship) throws NoSuchElementException {
        String page = signedIn() ? "responsive_checkout_signed_in" : "responsive_checkout";
        String type = bops ? ".pickup" : ".shipping";
        if (!Wait.untilElementPresent(page + type + "_first_name")) {
            throw new NoSuchElementException("Element not present:" + Elements.element(page + type + "_first_name"));
        }

        ProfileAddress address = customer.getUser().getProfileAddress();
        //Temporary line; added because the page was not loading and state was not selected
        Wait.secondsUntilElementPresent( "responsive_checkout.shipping_address_state_list", 30);
        Wait.forPageReady();
        if (bops) {
            TextBoxes.typeTextbox(page + ".pickup_first_name", TestUsers.generateRandomFirstName());
            TextBoxes.typeTextbox(page + ".pickup_last_name", TestUsers.generateRandomFirstName());
            TextBoxes.typeTextbox(page + ".pickup_email_address", TestUsers.generateRandomEmail(5));
        }
        if (Elements.elementPresent(page + ".shipping_first_name")
                && Elements.getElementAttribute(page + ".shipping_first_name", "value").isEmpty()) {
            try {
                Wait.secondsUntilElementPresent(page + ".shipping_first_name",15);
                Thread.sleep(2000);
                TextBoxes.typeTextbox(page + ".shipping_first_name", TestUsers.generateRandomFirstName());
                TextBoxes.typeTextbox(page + ".shipping_last_name", TestUsers.generateRandomFirstName());
                TextBoxes.typeTextbox(page + ".shipping_address_line_1", address.getAddressLine1());
                TextBoxes.typeTextbox(page + ".shipping_address_line_2", address.getAddressLine2());
                TextBoxes.typeTextbox(page + ".shipping_address_city", address.getCity());
                TextBoxes.typeTextbox(page + ".shipping_phone_number", address.getBestPhone());
                if (iship) {
                    if (address.getProvince() != null) {
                        DropDowns.selectByText(page + ".shipping_address_province", address.getProvince());
                    }
                    TextBoxes.typeTextbox(page + ".shipping_email_address", TestUsers.generateRandomEmail(5));
                } else {
                    Wait.secondsUntilElementPresent(page + ".shipping_address_state_list", 15);
                    DropDowns.selectCustomText(page + ".shipping_address_state_list", page + ".shipping_address_state_options", (address.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(address.getState()) : address.getState());
                }
                TextBoxes.typeTextbox(page + ".shipping_address_" + (iship ? "postal_code" : "zip_code"), address.getZipCode());
            } catch (Exception e) {
                Assert.fail("Failed to get new address: " + e);
            }
        }
        String phoneNum = TestUsers.generateRandomPhoneNumber();

        if (onPage("responsive_checkout, responsive_checkout_signed_in".split(", ")) || iship) {
            TextBoxes.typeTextbox(page + type + "_phone_number", phoneNum);
        } else {
            TextBoxes.typeTextbox(page + type + "_phone_area_code", phoneNum.substring(0, 3));
            TextBoxes.typeTextbox(page + type + "_phone_exchange", phoneNum.substring(3, 6));
            TextBoxes.typeTextbox(page + type + "_phone_subscriber", phoneNum.substring(6));
        }
    }

    /**
     * Fills credit card information during checkout for guest user
     *
     * @param iship true if international(iship) mode, else false
     */
    public void fillGuestCardDetails(boolean iship) {
        String page = iship ? "iship_checkout" : (signedIn() ? "responsive_checkout_signed_in" : "responsive_checkout");
        CreditCard visaCard = TestUsers.getValidVisaCreditCard();

        Wait.forPageReady();
        Wait.secondsUntilElementPresent(page + ".credit_card_radio_button", 5);
        Assert.assertFalse("ERROR - DATA : Unable to process your checkout. Items are unavailable in checkout!!",
                !Elements.elementPresent(page + ".credit_card_radio_button") && Elements.elementPresent("responsive_checkout.error_container"));
        Clicks.click(page + ".credit_card_radio_button");
        DropDowns.selectCustomText(page + ".card_type_list", page + ".card_type_options", visaCard.getCardType().toString());
        TextBoxes.typeTextbox(page + ".card_number", visaCard.getCardNumber());
        if (iship) {
            DropDowns.selectByText(page + ".expiry_month", visaCard.getExpiryMonthIndex() + " - " + visaCard.getExpiryMonth());
        } else {
            DropDowns.selectCustomText(page + ".expiry_month_list", page + ".expiry_month_options", visaCard.getExpiryMonthIndex());
        }
        DropDowns.selectCustomText(page + ".expiry_year_list", page + ".expiry_year_options", visaCard.getExpiryYear());
        TextBoxes.typeTextbox(page + ".security_code", visaCard.getSecurityCode());

    }

    /**
     * Fills contact details during checkout for guest user
     *
     * @param iship true if international(iship) mode, else false
     */
    public void fillGuestContactDetails(boolean iship) {
        String page = iship ? "iship_checkout" : "payment_guest";
        if (!iship) {
            TextBoxes.typeTextbox(page + ".payment_email", TestUsers.generateRandomEmail(5));
            String number = TestUsers.generateRandomPhoneNumber();
            TextBoxes.typeTextbox(page + ".card_phone_area_code", number.substring(0, 3));
            TextBoxes.typeTextbox(page + ".card_phone_exchange", number.substring(3, 6));
            TextBoxes.typeTextbox(page + ".card_phone_subscriber", number.substring(6));
        }
    }

    /**
     * Fills payment information during checkout
     *
     * @param iship true if international(iship) mode, else false
     */
    public void fillGuestPaymentData(boolean iship) {
        String page = iship ? "iship_checkout" : "payment_guest";
        if (iship) {
            Wait.untilElementPresent(page + ".card_number");
        }
        ProfileAddress address = customer.getUser().getProfileAddress();

        fillGuestCardDetails(iship);

        TextBoxes.typeTextbox(page + ".first_name", TestUsers.generateRandomFirstName());
        TextBoxes.typeTextbox(page + ".last_name", TestUsers.generateRandomLastName());

        try {
            TextBoxes.typeTextbox(page + ".address_line_1", address.getAddressLine1());
            TextBoxes.typeTextbox(page + ".address_city", address.getCity());
            if (!iship) {
                String state = address.getState();
                DropDowns.selectByText(page + ".address_state", state);
            }
            TextBoxes.typeTextbox(page + ".address_zip_code", address.getZipCode());
        } catch (Exception e) {
            Assert.fail("Failed to get new address: " + e);
        }
        fillGuestContactDetails(iship);
    }

    /**
     * Fills billing information during checkout
     */
    public void fillBillingAddress() {
        CheckoutUtils.RCPage page = signedIn() ? CheckoutUtils.RCPage.SHIPPING_AND_PAYMENT : CheckoutUtils.RCPage.PAYMENT;
        ProfileAddress address = customer.getUser().getProfileAddress();
        if (Elements.elementPresent(page + ".first_name") && Elements.getElementAttribute(page + ".first_name", "value").isEmpty()) {
            TextBoxes.typeTextbox(page + ".first_name", address.getFirstName());
            TextBoxes.typeTextbox(page + ".last_name", address.getLastName());
            TextBoxes.typeTextbox(page + ".address_line_1", address.getAddressLine1());
            TextBoxes.typeTextbox(page + ".address_line_2", address.getAddressLine2());
            TextBoxes.typeTextbox(page + ".address_city", address.getCity());
            DropDowns.selectCustomText(page + ".address_state_list", page + ".address_state_options", (address.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(address.getState()) : address.getState());
            TextBoxes.typeTextbox(page + ".address_zip_code", address.getZipCode());
        }
    }

    public void editShippingAddress() throws Throwable {
        String page = (signedIn() ? (onPage("shipping_payment_signed_in") ? "shipping_payment_signed_in" : "responsive_checkout_signed_in") : "responsive_checkout");
        Checkout checkout = new Checkout(customer, false);
        switch (page) {
            case "responsive_checkout":
                Clicks.click(page + ".edit_shipping_section");
                Wait.untilElementPresent(page + ".shipping_first_name");
                checkout.fillShippingData(false);
                Clicks.click(page + ".continue_shipping_checkout_button");
                Wait.untilElementPresent(page + ".edit_shipping_section");
                break;
            case "responsive_checkout_signed_in":
                Clicks.clickIfPresent(page + ".change_shipping_address");
                Wait.secondsUntilElementPresentAndClick(page + ".edit_shipping_address", 5);
                Wait.forPageReady();
                if (macys()) {
                    checkout.fillShippingData(false);
                } else {
                    fillGuestShippingData(false);
                }
                Clicks.click(page + ".save_shipping_address");
                Wait.forPageReady();
                Wait.untilElementPresent(page + ".change_shipping_address");
                break;
            case "shipping_payment_signed_in":
                Clicks.click(page + ".edit_shipping_addresses");
                checkout.fillShippingData(false);
                Clicks.click(page + ".save_address");
                Wait.untilElementNotPresent(page + ".save_address");
                Wait.forPageReady();
                break;
            default:
                Assert.fail("In correct page name found : " + page);
        }
    }
}
