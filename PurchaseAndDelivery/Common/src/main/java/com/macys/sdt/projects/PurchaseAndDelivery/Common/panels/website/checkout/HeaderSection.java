package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


/**
 * Created by m835779 on 5/1/17.
 */

@FindBy(jsonPath = "responsive_checkout_header_section")
public class HeaderSection extends Page {

    private HtmlElement backToBagIcon;

    public void clickBackToBagIcon() {
        this.backToBagIcon.click();
    }
}
