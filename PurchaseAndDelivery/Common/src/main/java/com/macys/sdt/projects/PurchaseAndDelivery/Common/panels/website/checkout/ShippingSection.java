package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindAll;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.signedIn;
import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.get;



/**
 * Created by atepliashin on 12/9/16.
 */
@FindBy(jsonPath = "responsive_shipping_section")
public class ShippingSection extends Page {

    public TextBlock icwLearnMoreContent;
    public List<HtmlElement> shippingSummary;
    public Button editShippingSection;
    public List<HtmlElement> shippingSection;
    public HtmlElement shippingMethodsSet;
    public HtmlElement giftOptionsSet;
    public HtmlElement giftOptionsSummary;
    public HtmlElement shippingEgiftCardMessage;
    public Button changeShippingMethod;
    public HtmlElement giftBoxIndicator;
    public HtmlElement hidePricesIndicator;
    public HtmlElement giftMessageLine1Lable;
    @FindAll({@FindBy(jsonPath = "responsive_shipping_section.shipping_address"),
            @FindBy(jsonPath = "responsive_shipping_section.shipping_summary")})
    public HtmlElement shippingAddressSummary;

   // Please use the addressInfo section for filling the address section data in SignIn case
    public TextInput firstName;
    public TextInput lastName;
    public TextInput addressLine1;
    public TextInput addressLine2;
    public TextInput city;
    public Select state;
    public TextInput zipCode;
    public TextInput phone;


    public HtmlElement lookupLink;
    public TextInput uslPhoneNumber;

    public void waitForReady(){
        super.waitForReady();
        if(!signedIn()) {
            Wait.until(() -> shippingSection.size() > 0 && shippingSection.get(0).isDisplayed());
        }
    }

    public ShippingOptions shippingOptions() {
        return Navigate.get(ShippingOptions.class);
    }

    public void edit() {
        if (Wait.until(editShippingSection::isDisplayed)) {
            editShippingSection.click();
            this.waitForReady();
        } else {
            throw new AssertionError("ERROR - Failed to wait Edit button on shipping section");
        }
    }

    public void expandShippingMethodSection() {
        if (Wait.until(changeShippingMethod::isDisplayed)) {
            changeShippingMethod.click();
        }
    }

    public Boolean waitForEditIsDisplayed() {
        return Wait.until(editShippingSection::isDisplayed);
    }

    public AddressInfoSection addressInfoSection() {
        return get(AddressInfoSection.class);
    }
}
