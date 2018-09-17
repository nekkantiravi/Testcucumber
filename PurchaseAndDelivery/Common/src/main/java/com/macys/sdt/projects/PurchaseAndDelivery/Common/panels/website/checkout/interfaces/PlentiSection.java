package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.interfaces;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


/**
 * Created by yc04185 on 9/5/17.
 */

@FindBy(jsonPath = "apply_my_plenti")
public class PlentiSection extends Page {

    public Button useUsl;
    public HtmlElement uslRcRedeemAmount;
    public HtmlElement uslRcRedeemPin;
    public Button uslRcApplyPoints;
    public Button uslRcRedeemPinTooltipIcon;
    public HtmlElement uslRcRedeemPinTooltipPopup;

}
