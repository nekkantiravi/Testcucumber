package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse;


import com.macys.sdt.framework.model.Product;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces.Companion;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.Objects;


@FindBy(jsonPath = "shopping_bag")
public class ShoppingBag extends Page implements Companion {

    public List<HtmlElement> lineItems;
    public HtmlElement promoCode;
    public Button applyPromoCodeButton;
    public List<HtmlElement> productIds;
    public HtmlElement promoOverThresholdMessage;
    public List<HtmlElement> promoOverThresholdMessages;
    public List<HtmlElement> overThresholdPromos;
    public List<HtmlElement> itemsFinalTotalCopy;
    public List<HtmlElement> itemsTotals;
    public List<HtmlElement> itemsFinalTotal;
    public List<HtmlElement> itemsPromoToolTip;
    public HtmlElement promoTooltipContent;
    public HtmlElement promoTooltipContentPromoTitle;
    public HtmlElement promoTooltipContentPromoInfo;
    public List<HtmlElement> productUrls;
    public List<HtmlElement> itemColor;
    public List<HtmlElement> itemSize;


    @Override
    public void waitForReady() {
        super.waitForReady();
        companionSidebar().hide();
    }

    public int locateProductPosition(Product product) {
        int position = -1;
        for (int i = 0; i < lineItems.size(); i++) {
            String productDescription = locateElement(productUrls, i);
            String productColor = locateElement(itemColor, i);
            String productWebId = locateElement(productIds, i);
            String productSize = locateElement(itemSize, i);
            if (productWebId.equals(Integer.toString(product.id))
                    && productDescription.equals(product.description)
                    && Objects.equals(productColor, product.colorName)
                    && Objects.equals(productSize, product.sizeName)) {
                position = i;
                break;
            }

        }
        return position;
    }

    private static String locateElement(List<HtmlElement> list, int position) {
        String result = null;
        try {
            result = list.get(position).getText();
        } catch (Exception e) {

        }
        return result;
    }
}
