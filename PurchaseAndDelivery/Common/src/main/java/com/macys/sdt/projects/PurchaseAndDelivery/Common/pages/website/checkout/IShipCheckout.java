package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Image;

@FindBy(jsonPath = "iship_checkout")
public class IShipCheckout extends Page {

    public HtmlElement description;
    public HtmlElement price;
    public Image productImage;
    public HtmlElement orderTotal;

    @FindBy(jsonPath = "iship_checkout.shipping_iFrame")
    public HtmlElement shippingFrame;
}
