package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(jsonPath = "header_and_footer")
public class HeaderAndFooter extends Page {

    public HtmlElement countryCodeFlag;
    public HtmlElement gotoChangeCountry;
    public HtmlElement gotoChangeCountry2;
    public HtmlElement ishipCurrency;
    public Link gotoWeddingRegistry;


}
