package com.macys.sdt.shared.actions.website.mcom.pages.checkout;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.bcom.pages.CheckoutPageBcom;
import com.macys.sdt.shared.actions.website.mcom.panels.checkout.UslPayment;
import com.macys.sdt.shared.utils.CheckoutUtils;
import com.macys.sdt.shared.utils.CheckoutUtils.RCPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Checkout extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Checkout.class);
    CreditCard creditCard;
    private CheckoutUtils checkoutUtils = new CheckoutUtils();
    // Get reference to customer ONLY; if User or Profile Address within are changed we want to capture that
    private UserProfile customer;
    private boolean bops;
    private boolean bigTicket;
    public static String selectedScheduleDate;

    public Checkout(HashMap<String, String> opts, boolean bops) {
        customer = TestUsers.getCustomer(opts);
        this.bops = bops;
        this.bigTicket = Boolean.valueOf(opts.getOrDefault("bigTicket", "false"));
    }

    public Checkout(UserProfile customer, boolean bops) {
        this.customer = customer;
        this.bops = bops;
    }


    public static void addUslAsPayment() {
        new UslPayment().addUslAsPayment();
    }

    /**
     * Method to get mMoney earn information
     *
     * @return mMoney earn information
     */
    public static HashMap<String, String> getMMoneyInformation() {
        HashMap<String, String> mMoneyEstimatedEarnInfo = new HashMap<>();

        mMoneyEstimatedEarnInfo.put("mmoneyEstimatedEarnText", Elements.getText("responsive_checkout.mmoney_estimated_earn_text"));
        mMoneyEstimatedEarnInfo.put("mmoneyEstimatedEarnAmount", Elements.getText("responsive_checkout.mmoney_estimated_earn_amount").replaceAll("[^0-9]", ""));

        return mMoneyEstimatedEarnInfo;
    }

    /**
     * Method to get bMoney earn information
     *
     * @param pageName current page
     * @return bMoney earn information
     */
    public static HashMap<String, String> getEstimatedBMoneyInformation(RCPage pageName) {
        HashMap<String, String> bMoneyEstimatedEarnInfo = new HashMap<>();

        bMoneyEstimatedEarnInfo.put("bmoneyEstimatedEarnDesc", Elements.getText(pageName + ".bmoney_earn_desc"));
        bMoneyEstimatedEarnInfo.put("bmoneyEstimatedEarnAmount", Elements.getText(pageName + ".bmoney_earn_amount").split("\\.")[0].replaceAll("[^0-9]", ""));

        return bMoneyEstimatedEarnInfo;
    }

    /**
     * Method to fill shipping information during checkout
     *
     * @param iship true for iship checkout
     */
    public void fillShippingData(boolean iship) {
        String page = (signedIn() ?
                "responsive_checkout_signed_in" : (iship ? "iship_checkout" : "responsive_checkout"));
        String type = bops ? ".pickup" : ".shipping";

        ProfileAddress address = customer.getUser().getProfileAddress();
        Wait.forPageReady();

        if (iship) {
            switchToFrame(page + ".shipping_iFrame");
            TextBoxes.typeTextbox(page + type + "_first_name", TestUsers.generateRandomFirstName());
            TextBoxes.typeTextbox(page + type + "_last_name", TestUsers.generateRandomLastName());
            if (Elements.elementPresent(page + ".shipping_address_province") && address.getProvince() != null) {
                DropDowns.selectByText(page + ".shipping_address_province", address.getProvince());
            }
            TextBoxes.typeTextbox(page + ".shipping_email_address", TestUsers.generateRandomEmail(5));
            TextBoxes.typeTextbox(page + ".shipping_phone_number", TestUsers.generateRandomPhoneNumber());
            TextBoxes.typeTextbox(page + ".shipping_address_postal_code", address.getZipCode());

            TextBoxes.typeTextbox(page + ".shipping_address_line_1", address.getAddressLine1());
            TextBoxes.typeTextbox(page + ".shipping_address_city", address.getCity());
            /* may need this here?
            if (iship) {
                        if (Elements.elementPresent(page + ".shipping_address_province") && address.getProvince() != null) {
                            DropDowns.selectByText(page + ".shipping_address_province", address.getProvince());
                        }
                        TextBoxes.typeTextbox(page + ".shipping_email_address", address.getEmail());
                    }
             */
        } else {
            if (safari()) {
                Wait.untilElementPresentWithRefresh(Elements.element(page + type + "_first_name"));
            }
            //if (signedIn() && !bops) {
            //    fillResponsiveBCAddressInfo("shipping", RCPage.fromString(page));
            //} else {

            if (bops) {
                TextBoxes.typeTextbox(page + ".pickup_first_name", address.getFirstName());
                TextBoxes.typeTextbox(page + ".pickup_last_name", address.getLastName());
                TextBoxes.typeTextbox(page + ".pickup_email_address", address.getEmail());
                TextBoxes.typeTextbox(page + ".pickup_phone_number", address.getBestPhone());
            }
            if (Elements.elementPresent(page + ".shipping_first_name")
                    && Elements.getElementAttribute(page + ".shipping_first_name", "value").isEmpty()) {
                TextBoxes.typeTextbox(page + ".shipping_first_name", address.getFirstName());
                TextBoxes.typeTextbox(page + ".shipping_last_name", address.getLastName());
                TextBoxes.typeTextbox(page + ".shipping_address_line_1", address.getAddressLine1());
                if (address.getAddressLine2() != null && !address.getAddressLine2().isEmpty()) {
                    TextBoxes.typeTextbox(page + ".shipping_address_line_2", address.getAddressLine2());
                }
                TextBoxes.typeTextbox(page + ".shipping_address_city", address.getCity());
                String state = AbbreviationHelper.getStateAbbreviation(address.getState());
                DropDowns.selectByText(page + ".shipping_address_state", state);
                TextBoxes.typeTextbox(page + ".shipping_address_zip_code", address.getZipCode());
                TextBoxes.typeTextbox(page + ".shipping_phone_number", address.getBestPhone());
            }
            //}
        }
    }

    /**
     * Method to fill credit card information in checkout page
     *
     * @param iship true if international(iship) mode, else false
     */
    public void fillCreditCardData(boolean iship) {
        String page = iship ? "iship_checkout" : (signedIn() ? "responsive_checkout_signed_in" : "responsive_checkout");
        if (creditCard == null) {
            creditCard = TestUsers.getValidVisaCreditCard();
        }
        if (page.equals("shipping_payment_signed_in") || page.equals("responsive_checkout_signed_in")) {
            Clicks.clickIfPresent(page + ".add_new_card");
        }
        if (Wait.untilElementPresent(page + ".card_number")) {
            TextBoxes.typeTextbox(page + ".card_number", creditCard.getCardNumber());
        }
        if (iship) {
            DropDowns.selectByText(page + ".expiry_month", String.valueOf(Integer.parseInt(creditCard.getExpiryMonthIndex())));
            DropDowns.selectByText(page + ".expiry_year", creditCard.getExpiryYear());
        } else {
            // month: XX for both
            if (macys()) {
                DropDowns.selectByText(page + ".card_type", creditCard.getCardType().name);
                DropDowns.selectByText(page + ".expiry_month", creditCard.getExpiryMonthIndex());
                DropDowns.selectByText(page + ".expiry_year", creditCard.getExpiryYear());
            } else {
                Wait.secondsUntilElementPresent(page + ".card_type_list", 3);
                DropDowns.selectCustomText(page + ".card_type_list", page + ".card_type_options", creditCard.getCardType().name);
                DropDowns.selectCustomText(page + ".expiry_month_list", page + ".expiry_month_options", creditCard.getExpiryMonthIndex());
                DropDowns.selectCustomText(page + ".expiry_year_list", page + ".expiry_year_options", creditCard.getExpiryYear());
            }
        }
        if (Elements.elementPresent(page + ".security_code")) {
            TextBoxes.typeTextbox(page + ".security_code", creditCard.getSecurityCode());
        }
    }

    /**
     * Method to fill contact information during checkout
     *
     * @param iship   true if iship checkout flow, else false
     * @param page    current page name
     * @param address address to fill in contact information
     */
    public void fillContactDetails(boolean iship, String page, ProfileAddress address) {
        if (address == null) {
            address = customer.getUser().getProfileAddress();
        }
        if (iship) {
            TextBoxes.typeTextbox(page + ".area_code", address.getPhoneAreaCode());
            TextBoxes.typeTextbox(page + ".exchange", address.getPhoneExchange());
            TextBoxes.typeTextbox(page + ".subscriber", address.getPhoneSubscriber());
            TextBoxes.typeTextbox(page + ".email", address.getEmail());
        } else {
            Wait.secondsUntilElementPresent(page + ".phone_number", 5);
            TextBoxes.typeTextbox(page + ".phone_number", address.getBestPhone());
            if (!(signedIn() && Elements.elementPresent(page + ".update_paypal_profile_email"))) {
                TextBoxes.typeTextbox(page + ".payment_email", address.getEmail());
            }
        }
    }

    /**
     * Method to fill payment information during checkout
     *
     * @param iship true if international(iship) mode, else false
     */
    public void fillPaymentData(boolean iship) {
        String page = iship ? "iship_checkout" : (signedIn() ? "responsive_checkout_signed_in" : "responsive_checkout");
        if (iship) {
            Wait.untilElementPresent(page + ".card_number");
        }

        ProfileAddress address = customer.getUser().getProfileAddress();

        fillCreditCardData(iship);

        if (!iship) {
            if (Elements.elementPresent(page + ".use_shipping_address")) {
                Clicks.selectCheckbox(page + ".use_shipping_address");
            }
            HashMap<String, String> opts = new HashMap<>();
            opts.put("checkout_eligible", "true");
            if (macys()) {
                fillBillingAddress(opts);
            } else {
                new CheckoutPageBcom(opts, bops).fillBillingAddress();
            }
            TextBoxes.typeTextbox(page + ".phone_number", address.getBestPhone());
            TextBoxes.typeTextbox(page + ".payment_email", address.getEmail());
            if (edge()) {
                Elements.elementInView(page + ".security_code");
            }
            if (!signedIn()) {
                TextBoxes.typeTextbox(page + ".security_code", creditCard.getSecurityCode());
            }
        }
        Clicks.clickIfPresent(page + ".save_credit_card_button");
    }

    /**
     * Method to checkout in iship flow
     *
     * @param pageName name of the page to checkout until
     */
    public void ishipCheckout(String pageName) {
        String page = "iship_checkout";
        switch (pageName.toLowerCase()) {
            case "shipping & payment":
                if (!onPage(page)) {
                    checkoutUtils.navigateToCheckout(false, true);
                }
                break;
            case "order review":
                if (!Elements.elementPresent(page + ".shipping_first_name")) {
                    ishipCheckout("shipping & payment");
                }
                switchToFrame(page + ".shipping_iFrame");
                if (Elements.getElementAttribute(page + ".shipping_first_name", "value").equals("")) {
                    fillShippingData(true);
                }
                Wait.secondsUntilElementPresent(page + ".card_payment_method", 10);
                Clicks.click(page + ".card_payment_method");
                switchToFrame(page + ".payment_iFrame");
                if (safari()) {
                    int cnt = 0;
                    while (!Elements.elementPresent(page + ".card_number") && cnt++ < 3) {
                        Wait.secondsUntilElementPresent(page + ".card_number", 1);
                        if (Elements.elementPresent(page + ".card_number")) {
                            break;
                        }
                        try {
                            Navigate.browserRefresh();
                            Wait.forPageReady();
                            switchToFrame(page + ".shipping_iFrame");
                            Wait.untilElementPresent(page + ".shipping_first_name");
                            if (Elements.getElementAttribute(page + ".shipping_first_name", "value").equals("")) {
                                fillShippingData(true);
                            }
                            Clicks.click(page + ".card_payment_method");
                            switchToFrame(page + ".payment_iFrame");
                        } catch (Exception e) {
                            logger.error("" + e);
                        }
                    }
                }
                if (Elements.getElementAttribute(page + ".card_number", "value").equals("")) {
                    fillPaymentData(true);
                }
                break;
            case "order confirmation":
                if (!Elements.elementPresent(page + ".submit_order_button")) {
                    ishipCheckout("order review");
                }
                switchToFrame(page + ".shipping_iFrame");
                if (prodEnv()) {
                    logger.error("Cannot place orders on prod!!!");
                } else {
                    if (edge() || ie()) {
                        Clicks.javascriptClick(page + ".submit_order_button");
                    } else {
                        Clicks.click(page + ".submit_order_button");
                    }
                    Wait.untilElementNotPresent(page + ".submit_order_button");
                    switchToFrame(page + ".shipping_iFrame");
                }
                break;
        }
        switchToFrame("default");
    }

    /**
     * Method to checkout in responsive signed-in flow
     *
     * @param page name of the page to checkout until
     * @param opts checkout options
     */
    public void rcSignedIn(RCPage page, Map<String, String> opts) throws ProductionException {
        pausePageHangWatchDog();
        switch (page) {
            case SHIPPING_AND_PAYMENT:
                if (bigTicket) {
                    btSignedInShippingPaymentCase();
                } else {
                    stSignedInShippingPaymentCase();
                }
                break;
            case SERVICES_AND_FEES:
                if (bigTicket) {
                    btSignedInShippingPaymentCase();
                    Wait.untilElementPresent(RCPage.SERVICES_AND_FEES + ".rc_service_fees_submit_btn");
                }
                break;
            case PAYMENT:
                //PAYMENT for signedin case required for BT only
                if (bigTicket) {
                    //check error about address
                    By errorContainer = Elements.element(RCPage.SHIPPING_AND_PAYMENT + ".error_container");
                    if (Elements.elementPresent(errorContainer)
                            && Elements.getText(errorContainer).contains("Due to zip code change, some items in your bag are no longer available.")) {
                        //call SERVICE_AND_FEES
                        rcSignedIn(RCPage.SERVICES_AND_FEES, opts);
                    }
                    btFillServiceAndFees(opts);
                }
                break;
            case SCHEDULE_DELIVERY:
                if (bigTicket) {
                    //check SERVICE_AND_FEES filled
                    if (!Elements.elementPresent(RCPage.SERVICES_AND_FEES + ".edit_service_and_fees")) {
                        //call SERVICE_AND_FEES
                        rcSignedIn(RCPage.PAYMENT, opts);
                    }
                    //check PAYMENT.change
                    if (!Elements.elementPresent(RCPage.SHIPPING_AND_PAYMENT + ".change_credit_card_button")) {
                        signedInFillPaymentData(opts);
                    }

                }
                break;
            case REVIEW:
                if (bigTicket) {
                    btScheduleDelivery(opts);
                } else {
                    Wait.secondsUntilElementPresent(RCPage.REVIEW + ".place_order", 10);

//                    if (!Elements.elementPresent(RCPage.REVIEW + ".place_order") ||
//                            Elements.getElementAttribute(RCPage.REVIEW + ".place_order", "disabled").matches("disabled|true"))
                    Wait.secondsUntilElementPresent("responsive_checkout_signed_in.add_new_address", 5);
                    if (Elements.elementPresent("responsive_checkout_signed_in.add_new_address"))
                    {
                        rcSignedIn(RCPage.SHIPPING_AND_PAYMENT, opts);
                    }

                    signedInFillPaymentData(opts);
                }
                break;
            case CONFIRMATION:
//                Wait.secondsUntilElementPresent(RCPage.REVIEW + ".place_order", 10);

                if (!Elements.elementPresent(RCPage.REVIEW + ".place_order") ||
                        Elements.getElementAttribute(RCPage.REVIEW + ".place_order", "disabled").matches("disabled|true")) {
                    rcSignedIn(RCPage.REVIEW, opts);
                }
                if (!prodEnv()) {
                    placeOrder();

                } else {
                    logger.error("Cannot place orders on prod!!!");
                }
                break;
        }
        resumePageHangWatchDog();
    }

    private void signedInFillPaymentData(Map<String, String> opts) {

        Wait.forPageReady();
        if (Wait.untilElementPresent(RCPage.SHIPPING_AND_PAYMENT + (macys() ? ".save_credit_card_button" : ".add_new_card"))) {
            fillPaymentData(false);
        }

        CreditCard cardDetailsOnCheckout = checkoutUtils.getSelectedCardDetailsOnCheckout();
        String cardType = cardDetailsOnCheckout.getCardType().name;
        Boolean coBrandCard = cardType.equals(macys() ? "Macy's American Express" : "Bloomingdale's American Express");

        if (Wait.untilElementPresent(RCPage.SHIPPING_AND_PAYMENT + ".re_enter_card_number_section")) {
            TextBoxes.typeTextbox(RCPage.SHIPPING_AND_PAYMENT + ".re_enter_card_number", cardDetailsOnCheckout.getCardNumber());
            Clicks.click(RCPage.SHIPPING_AND_PAYMENT + ".re_enter_card_number_confirm");
        }
        if (Wait.untilElementPresent(RCPage.SHIPPING_AND_PAYMENT + ".security_code")) {
            TextBoxes.typeTextbox(RCPage.SHIPPING_AND_PAYMENT + ".security_code", coBrandCard ? "1234" : "204");
        }
        if (Wait.untilElementPresent(RCPage.SHIPPING_AND_PAYMENT + ".phone_number")) {
            ProfileAddress address = customer.getUser().getProfileAddress();
            fillContactDetails(false, RCPage.SHIPPING_AND_PAYMENT.toString(), address);
            Clicks.click(RCPage.SHIPPING_AND_PAYMENT + ".save_contact_button");
            Wait.untilElementNotPresent(RCPage.SHIPPING_AND_PAYMENT + ".save_contact_button");
        }
        if (Wait.untilElementPresent(RCPage.SHIPPING_AND_PAYMENT + ".security_code")) {
            TextBoxes.typeTextbox(RCPage.SHIPPING_AND_PAYMENT + ".security_code", coBrandCard ? "1234" : "204");
        }
    }

    private void stSignedInShippingPaymentCase() {
        RCPage page = RCPage.SHIPPING_AND_PAYMENT;
        CheckoutPageBcom bcomCheckout = new CheckoutPageBcom(customer, bops);
        Wait.forPageReady();
        Wait.secondsUntilElementPresent(page + ".add_new_address", 15);
        Clicks.clickIfPresent(page + ".add_new_address");
        Wait.forPageReady();
        Wait.secondsUntilElementPresent(page + ".shipping_first_name", 20);
        boolean isAddressNotAdded = Elements.elementPresent(page + ".pickup_first_name") ||
                Elements.elementPresent(page + ".shipping_first_name");
        if (isAddressNotAdded) {
            if (Elements.elementPresent(page + ".shipping_first_name") &&
                    Elements.getElementAttribute(page + ".shipping_first_name", "value").isEmpty()) {
                if (macys()) {
                    fillShippingData(false);
                } else {
                    bcomCheckout.fillGuestShippingData(false);
                }
            }
            Clicks.clickIfPresent(page + ".save_new_address");
            Wait.untilElementNotPresent(page + ".save_new_address");
            if (Elements.elementPresent(page + ".pickup_first_name") &&
                    Elements.getElementAttribute(page + ".pickup_first_name", "value").isEmpty()) {
                if (macys()) {
                    fillShippingData(false);
                } else {
                    bcomCheckout.fillGuestShippingData(false);
                }
            }
            Clicks.clickIfPresent(page + ".pick_up_save");
            Wait.untilElementNotPresent(page + ".pick_up_save");
        }
        Wait.forPageReady();
    }

    private void btSignedInShippingPaymentCase() {
        RCPage page = RCPage.SHIPPING_AND_PAYMENT;
        CheckoutPageBcom bcomCheckout = new CheckoutPageBcom(customer, bops);

        if (Elements.elementPresent(page + ".shipping_change_button")) {
            Clicks.click(page + ".shipping_change_button");
//            Clicks.click(page + ".edit_shipping_address");
        }
        Clicks.clickIfPresent(page + ".add_new_address");

        if (Elements.elementPresent(page + ".pickup_first_name") ||
                Elements.elementPresent(page + ".shipping_first_name")) {
            if (macys()) {
                fillShippingData(false);
            } else {
                bcomCheckout.fillGuestShippingData(false);
            }
            Clicks.clickIfPresent(page + ".save_new_address");
            Wait.untilElementNotPresent(page + ".save_new_address");
            if (Elements.elementPresent(page + ".pickup_first_name") &&
                    Elements.getElementAttribute(page + ".pickup_first_name", "value").isEmpty()) {
                if (macys()) {
                    fillShippingData(false);
                } else {
                    bcomCheckout.fillGuestShippingData(false);
                }
            }
            Clicks.clickIfPresent(page + ".pick_up_save");
            Wait.untilElementNotPresent(page + ".pick_up_save");
        }
        Wait.forPageReady();
    }

    /**
     * Method to checkout in responsive guest flow
     *
     * @param page name of the page to checkout until
     * @param opts checkout options
     * @throws ProductionException thrown if any exception found
     */
    public void rcGuest(RCPage page, Map<String, String> opts) throws ProductionException {
        if (page == null) {
            throw new AssertionError("page cannot be null");
        }
        Wait.forPageReady();
        switch (page) {
            case SHIPPING:
                if (!onPage(page.page)) {
                    checkoutUtils.navigateToCheckout(false, false);
                }
                break;
            case SERVICES_AND_FEES:
                if (bigTicket) {
//                    logger.info("Reaching Services and Fees section");
                    stGuestPaymentCase(opts);
                    Wait.untilElementPresent(RCPage.SERVICES_AND_FEES + ".rc_service_fees_submit_btn");
                }
                break;
            case PAYMENT:
                if (bigTicket) {
                    if (Wait.untilElementPresent(RCPage.PAYMENT + ".card_number")) {
                        return;
                    }
                    btFillServiceAndFees(opts);
                } else {
                    stGuestPaymentCase(opts);
                }
                break;
            case SCHEDULE_DELIVERY:
                if (bigTicket) {
                    stGuestReviewCase(opts);
                }
                break;
            case REVIEW:
                if (bigTicket) {
                    btScheduleDelivery(opts);
                } else {
                    stGuestReviewCase(opts);
                }
                break;
            case CONFIRMATION:
                boolean readyForConfirmation;
                if (bigTicket) {
                    readyForConfirmation = Elements.elementPresent(RCPage.REVIEW + ".edit_shipping_info")
                            && Elements.elementPresent(RCPage.REVIEW + ".edit_payment_info")
                            && Elements.elementPresent(RCPage.SCHEDULE_DELIVERY + ".edit_schedule_delivery")
                            && Elements.elementPresent(RCPage.SERVICES_AND_FEES + ".edit_service_and_fees");
                } else {
                    readyForConfirmation = Elements.elementPresent(RCPage.REVIEW + ".edit_shipping_info")
                            && Elements.elementPresent(RCPage.REVIEW + ".edit_payment_info");
                }
                if (!readyForConfirmation) {
                    if (!Elements.elementPresent(RCPage.REVIEW + ".edit_payment_info")) {
                        rcGuest(RCPage.REVIEW, opts);
                    } else if (bigTicket && !Elements.elementPresent(RCPage.SCHEDULE_DELIVERY + ".edit_service_and_fees")) {
                        rcGuest(RCPage.REVIEW, opts);
                    }
                }
                placeOrder();
                break;
        }
    }

    private void btScheduleDelivery(Map<String, String> opts) throws ProductionException {
        RCPage schedule_delivery = RCPage.SCHEDULE_DELIVERY;
        if (!Elements.elementPresent(schedule_delivery + ".rc_schedule_delivery_submit_btn")) {
            if (signedIn()) {
                rcSignedIn(schedule_delivery, opts);
            } else {
                rcGuest(schedule_delivery, opts);
            }
        }

        //if delivery should be scheduled by phone, then we will see message about that
        //so we just should click continue
        //otherwise scheduling first available date
        if (!Elements.getText(schedule_delivery + ".delivery_section_active_message").contains("Call to schedule delivery after checkout")
                && Wait.untilElementPresent(schedule_delivery + ".rc_schedule_month")) {
            DropDowns.selectByIndex(schedule_delivery + ".rc_schedule_month", 1);
            Wait.until(() -> Elements.elementPresent(schedule_delivery + ".rc_schedule_date"));
            DropDowns.selectByIndex(schedule_delivery + ".rc_schedule_date", 1);
        }
        Clicks.click(schedule_delivery + ".rc_schedule_delivery_submit_btn");
    }

    private void btFillServiceAndFees(Map<String, String> opts) throws ProductionException {
        RCPage servicesAndFees = RCPage.SERVICES_AND_FEES;
        if (!Wait.untilElementPresent(servicesAndFees + ".default_worry_no_mores_plan")) {
            if (signedIn()) {
                rcSignedIn(servicesAndFees, opts);
            } else {
                rcGuest(servicesAndFees, opts);
            }
        }
        Clicks.click(servicesAndFees + ".rc_service_fees_submit_btn");
        if (Elements.elementPresent(servicesAndFees + ".special_instructions_message")) {
            Clicks.click(servicesAndFees + ".delivery_none");
            Clicks.click(servicesAndFees + ".rc_service_fees_submit_btn");
        }
    }

    private void stGuestPaymentCase(Map<String, String> opts) throws ProductionException {
        if (Wait.untilElementPresent(RCPage.SHIPPING + ".card_number")) {
            return;
        }
        boolean registry_address = Elements.elementPresent(By.className("rc-registry-address-note"));
        boolean isAddressAdded = Elements.elementPresent(RCPage.SHIPPING + (bops ? ".pickup_first_name" : ".shipping_first_name"));
        if (!isAddressAdded && !registry_address) {
            rcGuest(RCPage.SHIPPING, opts);
        }

        RCPage shipping = RCPage.SHIPPING;
        if (!registry_address || bops) {
            Wait.secondsUntilElementPresent(shipping + (bops ? ".pickup" : ".shipping") + "_first_name", 10);
            if (Elements.getElementAttribute(shipping + (bops ? ".pickup" : ".shipping") + "_first_name", "value").equals("")) {
                if (macys()) {
                    fillShippingData(false);
                } else {
                    new CheckoutPageBcom(customer, bops).fillGuestShippingData(false);
                }
            }
        }
        Wait.forPageReady();
        Wait.forLoading(By.className("loader"));
        Wait.secondsUntilElementPresent(shipping + ".continue_shipping_checkout_button", 10);
        Clicks.javascriptClick(shipping + ".continue_shipping_checkout_button");
        Wait.untilElementPresent(RCPage.SHIPPING + ".card_number");
    }

    private void stGuestReviewCase(Map<String, String> opts) throws ProductionException {
        RCPage payment = RCPage.PAYMENT;
        if (!Elements.elementPresent(payment + ".card_number")) {
            rcGuest(payment, opts);
        }

        Wait.untilElementPresent(payment + ".card_number");
        if (Elements.getElementAttribute(payment + ".card_number", "value").equals("")) {
            fillPaymentData(false);
        }
        Clicks.click(payment + ".continue_payment_checkout_button");
        Wait.untilElementPresent(payment + ".edit_payment_info");
    }

    /**
     * Method to fill billing information during checkout
     *
     * @param opts billing options
     */
    public void fillBillingAddress(HashMap<String, String> opts) {
        boolean responsive = onPage("responsive_checkout, responsive_checkout_signed_in".split(", "));
        boolean iship = onPage("iship_checkout");
        String page = (iship ? "iship_checkout" : (signedIn() ? (responsive ? "responsive_checkout_signed_in" : "shipping_payment_signed_in") : "responsive_checkout"));
        ProfileAddress address = new ProfileAddress();
        TestUsers.getRandomValidAddress(opts, address);
        if (!iship && responsive) {
            Clicks.unSelectCheckbox(page + ".use_shipping_address");
            Wait.untilElementPresent(page + ".first_name");
            //            if (!signedIn() && Elements.elementPresent(page + ".use_shipping_address") && Elements.getElementAttribute(page + ".use_shipping_address", (signedIn() ? "aria-checked" : "value")).equals("false")) {
            //                Clicks.click(page + ".use_shipping_address");
            //            }
            if (Elements.elementPresent(page + ".first_name") || Elements.getElementAttribute(page + ".use_shipping_address", (signedIn() ? "aria-checked" : "value")).equals("false")) {
                TextBoxes.typeTextbox(page + ".first_name", TestUsers.generateRandomFirstName());
                TextBoxes.typeTextbox(page + ".last_name", TestUsers.generateRandomLastName());
                TextBoxes.typeTextbox(page + ".address_line_1", address.getAddressLine1());
                TextBoxes.typeTextbox(page + ".address_line_2", address.getAddressLine2());
                TextBoxes.typeTextbox(page + ".address_city", address.getCity());
                String state = AbbreviationHelper.getStateAbbreviation(address.getState());
                DropDowns.selectByText(page + ".address_state", state);
                TextBoxes.typeTextbox(page + ".address_zip_code", address.getZipCode());
            }
        } else {
            TextBoxes.typeTextbox(page + ".billing" + "_first_name", TestUsers.generateRandomFirstName());
            TextBoxes.typeTextbox(page + ".billing" + "_last_name", TestUsers.generateRandomLastName());
            TextBoxes.typeTextbox(page + ".billing" + "_address_line_1", address.getAddressLine1());
            TextBoxes.typeTextbox(page + ".billing" + "_address_city", address.getCity());
            String state = address.getState();
            DropDowns.selectByText(page + ".billing" + "_address_state", state);
            TextBoxes.typeTextbox(page + ".billing" + "_address_zip_code", address.getZipCode());
            TextBoxes.typeTextbox(page + ".billing" + "_phone_area_code", address.getPhoneAreaCode());
            TextBoxes.typeTextbox(page + ".billing" + "_phone_exchange", address.getPhoneExchange());
            TextBoxes.typeTextbox(page + ".billing" + "_phone_subscriber", address.getPhoneSubscriber());
            TextBoxes.typeTextbox(page + ".billing" + "_email", address.getEmail());
        }
    }

    /**
     * Method to add 3d-secure card during checkout
     *
     * @param card_type 3dsecure card type
     * @param iship     true if iship checkout flow, else false
     */
    public void add3DSecureCard(String card_type, boolean iship) {
        String page = iship ? "iship_checkout" : (signedIn() ? "responsive_checkout_signed_in" : "responsive_checkout");
        creditCard = TestUsers.getValid3DSecureCard(card_type);
        Wait.forPageReady();
        if (signedIn()) {
            Clicks.clickIfPresent(page + ".change_credit_card_button");
            if (safari()) {
                Wait.secondsUntilElementPresent(page + ".add_credit_card_button", 10);
            }
            if (isCreditCardAlreadyAdded(creditCard, page)) {
                Clicks.click(page + ".edit_card");
            } else {
                Clicks.click(page + ".add_credit_card_button");
            }
            fillPaymentData(iship);
            Clicks.clickIfPresent(page + ".save_card");
            Wait.untilElementNotPresent(page + ".save_card");
        } else {
            Clicks.click(page + ".credit_card_radio_button");
            TextBoxes.typeTextbox(page + ".card_number", creditCard.getCardNumber());
            if (macys()) {
                DropDowns.selectByText(page + ".expiry_month", creditCard.getExpiryMonthIndex());
                DropDowns.selectByText(page + ".expiry_year", creditCard.getExpiryYear());
                DropDowns.selectByText(page + ".card_type", card_type);
            } else {
                DropDowns.selectCustomText(page + ".expiry_month_list", page + ".expiry_month_options", creditCard.getExpiryMonthIndex());
                DropDowns.selectCustomText(page + ".expiry_year_list", page + ".expiry_year_options", creditCard.getExpiryYear());
                DropDowns.selectCustomText(page + ".card_type_list", page + ".card_type_options", card_type);
            }
        }
        /*if (signedIn()) {
            typeTextbox(page + ".first_name", TestUsers.generateRandomFirstName());
            typeTextbox(page + ".last_name", TestUsers.generateRandomLastName());
            typeTextbox(page + ".address_line_1", address.getString("address_line_1"));
            typeTextbox(page + ".address_city", address.getString("address_city"));
            String state = address.getString("address_state");
            state = AbbreviationHelper.translateStateAbbreviation(state);
            selectByText(page + ".address_state", state);
            typeTextbox(page + ".address_zip_code", address.getString("address_zip_code"));
            String number = TestUsers.generateRandomPhoneNumber();
            typeTextbox(page + ".card_phone_area_code", number.substring(0, 3));
            typeTextbox(page + ".card_phone_exchange", number.substring(3, 6));
            typeTextbox(page + ".card_phone_subscriber", number.substring(6));
            typeTextbox(page + ".payment_email", TestUsers.generateRandomEmail(5));
            click(page + ".save_card");
            secondsUntilElementNotPresent(page + ".card_phone_area_code", 5);
        }*/
        // adding wait time, as it is consistently failing in jenkins
        Wait.secondsUntilElementPresent(page + ".security_code", 5);
        if (Elements.elementPresent(page + ".security_code") && Elements.getText(page + ".security_code").isEmpty()) {
            TextBoxes.typeTextbox(page + ".security_code", creditCard.getSecurityCode());
        }
        if (page.equals("responsive_checkout_signed_in") && Elements.elementPresent(page + ".save_contact_button")) {
            fillContactDetails(true, "responsive_checkout_signed_in", null);
            Clicks.click(page + ".save_contact_button");
            Wait.untilElementNotPresent(page + ".save_contact_button");
            Wait.secondsUntilElementPresent(page + ".security_code", 5);
        }
        if (Elements.elementPresent(page + ".security_code") && Elements.getText(page + ".security_code").isEmpty()) {
            TextBoxes.typeTextbox(page + ".security_code", creditCard.getSecurityCode());
        }
        if (page.equals("shipping_payment_signed_in")) {
            Clicks.click(page + ".continue_checkout_button");
            Wait.forPageReady();
        }
    }

    /**
     * Method to check whether the credit card is already added or not in checkout page
     *
     * @param creditCard CreditCard class instance
     * @param page       name of the current page
     * @return true if credit card is already added, else false
     */
    public boolean isCreditCardAlreadyAdded(CreditCard creditCard, String page) {
        if (Elements.elementPresent(page + ".payment_info")) {
            WebElement paymentInfo = Elements.findElement(Elements.element(page + ".payment_info"));
            if (paymentInfo == null) {
                return false;
            }
            WebElement cardName = paymentInfo.findElement(By.tagName("p"));
            if (cardName == null) {
                return false;
            }
            if (cardName.getText().toLowerCase().contains(creditCard.getCardType().name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to return the current checkout page name
     *
     * @param pageName name of the page
     * @return page name
     **/

    public String getPageName(String pageName) {
        String page;
        switch (pageName) {
            case "shipping":
                page = onPage("responsive_checkout") ? "responsive_checkout" : "shipping_guest";
                break;
            case "order review":
                page = onPage("order_review") ? "order_review" : "responsive_order_review_section";
                break;
            case "shipping & payment":
            case "shipping and payment":
                page = onPage("responsive_checkout_signed_in") ? "responsive_checkout_signed_in" : "shipping_payment_signed_in";
                break;
            default:
                page = onPage("order_confirmation") ? "order_confirmation" : "responsive_order_confirmation";
        }
        return page;
    }

    /**
     * Method to fill billing and contact information during checkout
     *
     * @param section name of the section
     * @param page    current page name
     */
    public void fillResponsiveBCAddressInfo(String section, RCPage page) {
        ProfileAddress address = customer.getUser().getProfileAddress();
        Wait.untilElementPresent(page + ".use_shipping_address");
        Clicks.unSelectCheckbox(page + ".use_shipping_address");
        Wait.untilElementPresent(page + ".first_name");
        TextBoxes.typeTextbox(page + ".first_name", address.getFirstName());
        TextBoxes.typeTextbox(page + ".last_name", address.getFirstName());
        TextBoxes.typeTextbox(page + ".address_line_1", address.getAddressLine1());
        TextBoxes.typeTextbox(page + ".address_line_2", address.getAddressLine2());
        TextBoxes.typeTextbox(page + ".address_city", address.getCity());
        String state = address.getState();
        state = macys() ? AbbreviationHelper.getStateAbbreviation(state) : state;
        if (macys() || Elements.elementPresent(page + ".address_state")) {
            DropDowns.selectByText(page + ".address_state", state);
        } else {
            DropDowns.selectCustomText(page + ".address_state_list", page + ".address_state_options", state);
        }
        TextBoxes.typeTextbox(page + ".address_zip_code", address.getZipCode());
        ifPresentDo(page + ".billing_phone_number", () -> TextBoxes.typeTextbox(page + ".billing_phone_number", address.getBestPhone()));
        ifPresentDo(page + ".payment_email", () -> TextBoxes.typeTextbox(page + ".payment_email", address.getEmail()));
        if (section.equals("shipping")) {
            TextBoxes.typeTextbox(page + ".phone_number", address.getBestPhone());
        } else {
            fillContactDetails(false, page.toString(), address);
        }
    }

    /**
     * Method to retrun all the shipping method details displayed on checkout page
     *
     * @return shippingMethods
     **/

    public HashMap<String, HashMap> getShippingMethods() {
        String pageName = signedIn() ? "responsive_checkout_signed_in" : "responsive_checkout";
        HashMap<String, HashMap> shippingMethods = new HashMap<>();
        List<WebElement> shippingMethodElements = Elements.findElements(pageName + "." + (signedIn() ? "signin_" : "") + "shipping_methods");
        for (WebElement element : shippingMethodElements) {
            if (element.isDisplayed()) {
                String[] details = element.findElement(By.tagName("label")).getText().trim().split("\n");
                ArrayList<String> shippingMethodDetails = new ArrayList<>();
                shippingMethodDetails.addAll(Arrays.asList(details));
                shippingMethodDetails.removeIf(item -> item == null || item.isEmpty());

                WebElement radioButtonElement = element.findElement(By.tagName("input"));
                HashMap<String, Boolean> shippingAttributes = new HashMap<>();
                shippingAttributes.put("isSelected", radioButtonElement.isSelected());
                shippingAttributes.put("isEnabled", element.isEnabled());

                HashMap<String, Object> shippingDetails = new HashMap<>();
                shippingDetails.put("shippingMethodAttributes", shippingAttributes);
                shippingDetails.put("shippingMethodDetails", shippingMethodDetails);
                shippingMethods.put(shippingMethodDetails.get(0).toLowerCase().replaceAll("\\s", ""), shippingDetails);
            }
        }
        return shippingMethods;
    }

    /**
     * Clicks place order button on order review page
     *
     * @throws ProductionException if on prod
     **/
    public void placeOrder() throws ProductionException {
        if (prodEnv()) {
            throw new ProductionException("Cannot place orders on prod!");
        }
        pausePageHangWatchDog();
        Wait.forPageReady();
        Boolean responsive = !onPage("order_review");

        // 2017-10-13 Ravichandra Etikela: Changed the wait times from 20 sec to 10 sec to eliminate too much wait times that are causing the "Need Help popup" and script failure
//        Wait.secondsUntilElementPresent((responsive ? "responsive_order_summary" : "order_review") + ".place_order", 10);
        if (signedIn() && Elements.elementPresent("responsive_checkout_signed_in.re_enter_card_number_section")) {
            TextBoxes.typeTextbox("responsive_checkout_signed_in.re_enter_card_number", checkoutUtils.getSelectedCardDetailsOnCheckout().getCardNumber());
            Clicks.click("responsive_checkout_signed_in.re_enter_card_number_confirm");
        }
        Wait.forPageReady();
        if (Elements.getElementAttribute("responsive_checkout_signed_in.paypal_radio_button", "checked").equalsIgnoreCase("false"))
            if (signedIn()) {
                CreditCard cardDetailsOnCheckout = checkoutUtils.getSelectedCardDetailsOnCheckout();
                String cardType = cardDetailsOnCheckout.getCardType().name;
                Boolean propCard = (macys() ? Arrays.asList("Macy's", "Employee Card") : Arrays.asList("Bloomingdale's", "Bloomingdale's Employee Card")).contains(cardType);
                Boolean coBrandCard = cardType.equals(macys() ? "Macy's American Express" : "Bloomingdale's American Express");
                if (!propCard && Elements.findElement("responsive_checkout_signed_in.security_code").getAttribute("value").equals("")) {
                    TextBoxes.typeTextbox("responsive_checkout_signed_in.security_code", coBrandCard ? "1234" : "204");
                }
            }
        if (signedIn() && Wait.untilElementPresent("responsive_checkout_signed_in.phone_number")) {
            TextBoxes.typeTextbox("responsive_checkout_signed_in.phone_number", TestUsers.generateRandomPhoneNumber());
            Clicks.click("responsive_checkout_signed_in.save_contact_button");
        }
        if (signedIn() && onPage("responsive_checkout_signed_in") && Wait.secondsUntilElementPresent("responsive_checkout_signed_in.place_order", 30)) {
            Clicks.javascriptClick("responsive_checkout_signed_in.place_order");
        } else if (onPage("responsive_checkout") && Wait.secondsUntilElementPresent("responsive_checkout.payment_edit_button", 20)) {
            Wait.secondsUntilElementPresent("responsive_checkout.place_order", 30);
            Clicks.javascriptClick("responsive_checkout.place_order");
        } else if (onPage("order_review")) {
            Clicks.click("order_review.verify_page");
        } else {
            Assert.fail("Unable to place order");
        }

        Wait.secondsUntilElementNotPresent((responsive ? "responsive_order_summary" : "order_review") + ".place_order", 30);
        Wait.forLoading(By.className("loader-container"));
        Wait.forPageReady();
        Wait.secondsUntilElementPresent((responsive ? "responsive_order_confirmation" : "order_confirmation") + ".verify_page", 30);
        Assert.assertTrue("Order not placed successfully!!", onPage("responsive_order_confirmation"));
        resumePageHangWatchDog();
    }

}
