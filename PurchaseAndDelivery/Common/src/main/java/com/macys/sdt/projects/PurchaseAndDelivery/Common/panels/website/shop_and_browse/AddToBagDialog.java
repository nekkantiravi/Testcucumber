package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by ilysenko on 18/7/17.
 */
@FindBy(jsonPath = "add_to_bag_dialog")
public class AddToBagDialog extends Page {

    public HtmlElement addToBagDialog;
    public HtmlElement addToBagContinueShopping;
}
