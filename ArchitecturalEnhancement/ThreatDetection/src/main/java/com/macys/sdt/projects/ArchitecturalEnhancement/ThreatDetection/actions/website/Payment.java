package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.actions.website;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.CreditCards;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Payment extends StepUtils {

    public static UserProfile customer = TestUsers.getCustomer(null);
    //public static Checkout checkout = new Checkout(customer, false);
    public static String macysCard = "Macy's";
    public static String bloomiesCard = "Bloomingdales";
    public static String employeeCard = "Employee";


    public static CreditCard getCreditCardType(String cardType) {
        return CreditCards.getValidCards().stream()
                .filter(card -> card.getCardType().name.equalsIgnoreCase(cardType) && !card.has3DSecure())
                .findFirst()
                .orElseThrow(() -> new AssertionError("no valid cards of type " + cardType + " found"));
    }

    public static void addCreditOnCheckout(String cardType) throws Throwable {
        changeCreditCard();
        WebElement addcardbtn = Elements.findElement("responsive_checkout_signed_in.add_credit_card_button");
        Clicks.click(addcardbtn);
        filCreditCardDetails(cardType);
        if (Elements.findElement("responsive_payment_signin_section.first_name").isDisplayed()) {
            TextBoxes.typeTextbox("responsive_payment_signin_section.first_name", TestUsers.generateRandomFirstName());
            TextBoxes.typeTextbox("responsive_payment_signin_section.last_name", TestUsers.generateRandomLastName());
            TextBoxes.typeTextbox("responsive_payment_signin_section.address_line_1", customer.getUser()
                    .getProfileAddress()
                    .getAddressLine1());
            TextBoxes.typeTextbox("responsive_payment_signin_section.address_line_2", customer.getUser()
                    .getProfileAddress()
                    .getAddressLine2());
            TextBoxes.typeTextbox("responsive_payment_signin_section.address_city", customer.getUser()
                    .getProfileAddress()
                    .getCity());
            String state = AbbreviationHelper.getStateAbbreviation(customer.getUser().getProfileAddress().getState());
            DropDowns.selectByText("responsive_payment_signin_section.address_state", state);
            TextBoxes.typeTextbox("responsive_payment_signin_section.address_zip_code", customer.getUser()
                    .getProfileAddress()
                    .getZipCode());
        }
        TextBoxes.typeTextbox("responsive_payment_signin_section.payment_email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("responsive_payment_signin_section.phone_number", TestUsers.generateRandomPhoneNumber());
        Payment.saveCard();
    }

    public static void filCreditCardDetails(String card) {
        CreditCard cc = getCreditCardType(card);
        Wait.untilElementPresent("responsive_payment_signin_section.card_type");
        if (!(card.equalsIgnoreCase(macysCard) || (card.equalsIgnoreCase(bloomiesCard) || (card
                .equalsIgnoreCase(employeeCard))))) {
            DropDowns.selectByText("responsive_payment_signin_section.expiry_month", cc.getExpiryMonthIndex());
            DropDowns.selectByText("responsive_payment_signin_section.expiry_year", cc.getExpiryYear());
        }
        DropDowns.selectByText("responsive_payment_signin_section.card_type", cc.getCardType().name);
        TextBoxes.typeTextbox("responsive_payment_signin_section.card_number", cc.getCardNumber());

    }

    public static void changeCreditCard() {
        Wait.untilElementPresent("responsive_checkout_signed_in.change_credit_card_button");
        Clicks.click("responsive_checkout_signed_in.change_credit_card_button");
    }

    public static void saveCard() {
        Clicks.clickIfPresent("responsive_payment_signin_section.save_payment_button");
    }


    public static void selectCreditCardType(String card) {
        List<WebElement> cc = Elements.findElements("responsive_payment_signin_section.card_list");
        for (WebElement li : cc) {
            if (li.findElement(By.tagName("p")).getText().toLowerCase().contains(card.toLowerCase())) {
                if (StepUtils.macys()) {
                    Clicks.click(li.findElement(By.tagName("input")));
                    break;
                } else {
                    Clicks.click(li.findElement(By.tagName("span")));
                    break;
                }

            }
        }
    }

    public static void selectOtherCardType() {
        List<WebElement> cc = Elements.findElements("responsive_payment_signin_section.card_list");
        for (WebElement li : cc) {
            if (StepUtils.macys()) {
                if (!(li.getAttribute("class").contains("rc-highlight"))) {
                    Clicks.click(li.findElement(By.tagName("input")));
                    break;
                }
            } else {
                if (!(li.getAttribute("class").contains("bl_tileChecked"))) {
                    Clicks.click(li.findElement(By.tagName("span")));
                    break;
                }
            }

        }
    }


    public static void changeShipAdr() {
        Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
        Clicks.click("responsive_checkout_signed_in.change_shipping_address");
    }

    public static void selectOtherShipAdr() {
        List<WebElement> ship_add_list = Elements.findElements("responsive_shipping_section.ship_add_list");
        for (WebElement s : ship_add_list) {
            if (StepUtils.macys()) {
                if (!(s.getAttribute("class").contains("rc-highlight"))) {
                    Clicks.click(s.findElement(By.tagName("input")));
                    break;
                }
            } else {
                if (!(s.getAttribute("class").contains("bl_tileChecked"))) {
                    Clicks.click(s.findElement(By.tagName("span")));
                    break;
                }
            }
        }
    }

    public static void selectModifiedShipAdd(String address_line) {
        List<WebElement> ship_add_list = Elements.findElements("responsive_shipping_section.ship_add_list");
        Boolean status = false;
        for (WebElement s : ship_add_list) {
            for (WebElement s1 : s.findElements(By.tagName("p"))) {
                if ((s1.getText().toLowerCase().contains(address_line.toLowerCase()))) {
                    if (StepUtils.macys()) {
                        Clicks.click(s.findElement(By.tagName("input")));
                        status = true;
                        break;
                    } else {
                        Clicks.click(s.findElement(By.tagName("span")));
                        status = true;
                        break;
                    }
                }
            }
            if (status)
                break;
        }
    }


    public static void saveShipAdr() {
        Wait.untilElementPresent("responsive_shipping_section.save_shipping_button");
        Clicks.click("responsive_shipping_section.save_shipping_button");
    }

    public static void checkGreyedOutCreditCard(String cardNo) {
        List<WebElement> cc = Elements.findElements("responsive_payment_signin_section.card_list");
        for (WebElement li : cc) {
            if (StepUtils.macys()) {
                Assert.assertTrue("Credit card section is not greyed out", li.getAttribute("class")
                        .contains("rc-disabled"));
                break;
            } else {
                Assert.assertTrue("Credit card section is not greyed out", li.getAttribute("class")
                        .contains("rc-disabled"));
                //li.findElement(By.tagName("input")).getAttribute("disabled")
                break;
            }
        }
    }
}

