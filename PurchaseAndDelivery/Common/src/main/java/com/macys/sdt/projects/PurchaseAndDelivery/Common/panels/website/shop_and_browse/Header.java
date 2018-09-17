package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces.Companion;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(jsonPath = "header")
public class Header extends Page implements Companion {

    public HtmlElement shoppingBagIcon;
    public HtmlElement logo;

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(shoppingBagIcon:: isDisplayed);
    }

}
