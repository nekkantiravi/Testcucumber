package com.macys.sdt.shared.utils;


import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

public class CheckoutUtils extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutUtils.class);
    private static boolean guestCheckoutSignInPageVerify = true;

    public static void selectGwp() {
        List<WebElement> selects = Elements.findElements(Elements.element("shopping_bag.gwp_selector"));
        for (WebElement s : selects) {
            Select select = new Select(s);
            select.selectByIndex(1);
        }
    }

    public static void selectGwpMEW() {
        while (Elements.elementPresent("shopping_bag.bag_error")) {
            try {
                Clicks.click(Elements.findElement(Elements.element("shopping_bag.bag_error")).findElement(By.xpath("../div/div")));
                Clicks.click(Elements.element("shopping_bag.apply"));
            } catch (Exception e) {
                Assert.fail("Failed to select gwp: " + e);
            }
        }
    }

    public static boolean gwpPresentMEW() {
        return Elements.elementPresent("shopping_bag.bag_error")
                && Elements.getText(Elements.element("shopping_bag.bag_error")).contains("You've qualified for a Bonus offer!");
    }

    public static void removeUnavailableItems() {
        List<WebElement> badProducts = Elements.findElements(Elements.element("shopping_bag.error_message"));
        if (badProducts.size() > 0) {
            badProducts.forEach(el -> {
                if (el.isDisplayed() && !el.getText().isEmpty() && !el.getText().contains("Bonus")) {
                    Clicks.click(el.findElement(By.xpath("..//span[text()='Remove']")));
                }
            });
        }
    }

    public void navigateToCheckout(boolean signIn, boolean iship) {
        navigateToCheckout(signIn, iship, true);
    }

    public void navigateToCheckout(boolean signIn, boolean iship, boolean waitForCheckoutPage) {
        Wait.forPageReady();
        if (signIn && !signedIn()) {
            CommonUtils.signInOrCreateAccount();
        }
        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        if (ie() && WebDriverManager.getCurrentTitle().contains("Certificate Error:")) {
            Navigate.execJavascript("document.getElementById('overridelink').click();");
        }

        if (Elements.elementPresent("shopping_bag.gwp_selector")) {
            selectGwp();
        }

        if (MEW() && gwpPresentMEW()) {
            selectGwpMEW();
        }

        removeUnavailableItems();
        ShoppingBag.removeBonusItemsFromBag();
        try {
            if (bloomingdales()) {
                Assert.assertFalse("ERROR - ENV : Shopping bag is Empty!!",
                        !Elements.elementPresent("shopping_bag.continue_checkout_image") && Elements.elementPresent("shopping_bag.empty_bag_container"));
            }
            if (safari() || MEW()) {
                Clicks.javascriptClick("shopping_bag.continue_checkout_image");
            } else {
                Clicks.click("shopping_bag.continue_checkout_image");
            }
            if (!signIn && !iship) {
                if (safari()) {
                    Wait.secondsUntilElementPresent("checkout_sign_in.continue_checkout_guest_button", 20);
                }
                if (MEW()) {
                    Wait.secondsUntilElementPresent("checkout_sign_in.email", 20);
                }
                if (guestCheckoutSignInPageVerify) {
                    Assert.assertTrue("ERROR - ENV : Unable to navigate checkout sign in page. Current URL: " + WebDriverManager.getCurrentUrl(), onPage("checkout_sign_in"));
                    guestCheckoutSignInPageVerify = false;
                }
            }

            if (ie() && WebDriverManager.getCurrentTitle().contains("Certificate Error:")) {
                Navigate.execJavascript("document.getElementById('overridelink').click();");
            }
        } catch (org.openqa.selenium.ElementNotVisibleException e) {
            logger.info("Unable to checkout. Cart may be empty. " + e);
        }
        if (!signedIn() && !iship) {
            if (Wait.untilElementPresent("checkout_sign_in.continue_checkout_guest_button")) {
                Clicks.javascriptClick("checkout_sign_in.continue_checkout_guest_button");
                Wait.forPageReady();
            }
            if (!signIn) {
                if (safari() || MEW()) {
                    Wait.secondsUntilElementPresent("responsive_checkout.continue_shipping_checkout_button", 20);

                }
                if (waitForCheckoutPage) {
                    if (onPage("responsive_checkout", "responsive_checkout_signed_in")) {
                        Wait.secondsUntilElementPresent("responsive_checkout.continue_shipping_checkout_button", 15);
                        Assert.assertTrue("ERROR-ENV: Unable to navigate to responsive guest checkout page", onPage("responsive_checkout"));
                    } else {
                        Assert.assertTrue("ERROR-ENV: Unable to navigate to responsive guest checkout page", onPage("responsive_checkout"));
                    }
                }
            }
        }
    }

    public enum RCPage {
        SHIPPING("shipping", "responsive_checkout"),
        // shipping & payment is for signed in only
        SHIPPING_AND_PAYMENT("shipping & payment", "responsive_checkout_signed_in"),
        SERVICES_AND_FEES("services & fees", "responsive_service_fees_section"),
        PAYMENT("payment", "responsive_payment_guest_section"),
        SCHEDULE_DELIVERY("schedule delivery", "responsive_schedule_delivery_section"),
        REVIEW("order review", "responsive_order_review_section"),
        CONFIRMATION("order confirmation", "responsive_order_summary");

        public final String name;
        public final String page;

        RCPage(String name, String page) {
            this.name = name;
            this.page = page;
        }

        public static RCPage fromString(String name) {
            return Stream.of(RCPage.values()).
                    filter((RCPage page) -> name.equalsIgnoreCase(page.name) || name.equalsIgnoreCase(page.page)).
                    findFirst().orElseThrow(() -> new AssertionError("No checkout page found for: " + name));
        }

        public String toString() {
            return page;
        }
    }

    /**
     * Method to get selected credit card details in signed in checkout flow
     *
     * @return CreditCard selected on checkout
     */
    public CreditCard getSelectedCardDetailsOnCheckout() {
        String[] cardDetails = Elements.findElement(RCPage.SHIPPING_AND_PAYMENT +
                (macys() ?  ".credit_card_details" : ".payment_credit_card_summary")).getText().split("\\r?\\n");
        String cardType = cardDetails[0];
        String maskedCardNumber = cardDetails[1];
        return CreditCards.getValidCards().stream()
                .filter(card -> card.getCardType().name.equalsIgnoreCase(cardType) &&
                    (macys() ? card.getCardNumber() : StringUtils.right(card.getCardNumber(), 8))
                    .replaceAll("\\d(?=\\d{4})", "*").equals(maskedCardNumber)).findFirst()
                .orElseThrow(() -> new DataException(String.format("No valid cards of type %s " + "found", cardType)));
    }

}
