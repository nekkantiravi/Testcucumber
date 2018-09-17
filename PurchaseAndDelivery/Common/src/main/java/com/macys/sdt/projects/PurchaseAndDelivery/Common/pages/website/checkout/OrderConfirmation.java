package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.macys.sdt.framework.utils.StepUtils.macys;

/**
 * Created by atepliashin on 1/19/17.
 */
@FindBy(jsonPath = "responsive_order_confirmation")
public class OrderConfirmation extends Page {

    public HtmlElement orderNumber;
    public HtmlElement orderTotal;
    public HtmlElement alertMessage;
    public HtmlElement orderConfirmEmail;
    public HtmlElement shippingEgiftCardSection;
    public HtmlElement giftBoxIndicator;
    public HtmlElement hidePricesIndicator;
    public HtmlElement giftMessageLine1Lable;
    public HtmlElement giftOptionsSummary;
    public HtmlElement btDeliveryMethod;
    public HtmlElement normalShippingSection;
    public HtmlElement bopsSmsCopy;
    public HtmlElement bopsOrderChange;
    public HtmlElement btOrderNumber;
    public HtmlElement btOrderTotal;

    public String getNumber() {
        return matchOnPage("\\d+", orderNumber.getText(), 0);
    }

    public String getTotal() {
        return orderTotal.getText().replaceAll("[^\\d\\.]", "");
    }

    public String getBtOrderNumber() {
        return matchOnPage("\\d+", btOrderNumber.getText(), 0);
    }

    public String getBtOrderTotal() {
        return btOrderTotal.getText().replaceAll("[^\\d\\.]", "");
    }

    public String getDeliveryMethod() {
        return matchOnPage("(Delivery method)\\s*(.*)", btDeliveryMethod.getText(), 2);
    }

    public String getShippingAddressZipCode() {
        return matchOnPage("[,]\\s(\\w\\w)\\s(\\d{5})", normalShippingSection.getText(), 2);
    }

    public String getEmail() {
        if (macys()) {
            return orderConfirmEmail.getText();
        } else {
            return matchOnPage("sent to (.*)\\. You can", alertMessage.getText(), 1);
        }
    }

    private String matchOnPage(String regex, String text, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return null;
    }
}
