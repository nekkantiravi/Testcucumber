package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.bloomingdales;


@FindBy(jsonPath = "responsive_order_summary")
public class OrderSummarySection extends Page {

    public List<HtmlElement> rcOrderSummary;
    public HtmlElement giftWrapCost;
    public HtmlElement normalShipping;
    public HtmlElement bopsShipping;
    public HtmlElement shippingFee;
    public HtmlElement giftPlentiRewardValues;


    public List<HtmlElement> getRcOrderCostSummary() {
        return rcOrderSummary.stream().filter(
                element -> element.findElements(By.className("tax-desclaimer")).size() == 0
                        || (bloomingdales() && element.findElements(By.cssSelector("bl_header_separator")).size() == 0))

                .collect(Collectors.toList());
    }
}
