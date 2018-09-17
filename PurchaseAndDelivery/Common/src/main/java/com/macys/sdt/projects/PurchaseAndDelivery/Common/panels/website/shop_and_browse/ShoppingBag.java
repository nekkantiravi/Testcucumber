package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.ProductDisplay;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.List;

/**
 * Created by atepliashin on 12/8/16.
 */
@FindBy(jsonPath = "shopping_bag")
public class ShoppingBag extends Page {

    public HtmlElement mmoneyHintIcon;
    public HtmlElement mmoneyRedeemLogo;
    public HtmlElement mmoneyRedeemValue;
    public HtmlElement walletRewardMessage;
    public HtmlElement shopBagId;
    public HtmlElement bagItemCount;
    public HtmlElement emptyBagErrorMessage;
    public HtmlElement errorMessage;
    public HtmlElement registrantNameDetails;
    public HtmlElement itemRemovedMsg;
    public List<HtmlElement> removeCurrentBagItems;
    public HtmlElement shippingSurchargeContainer;
    public HtmlElement itemLevelErrorMessage;
    public HtmlElement estimatedShipping;
    public List<Link> productUrls;
    public Link shippingToUnitedStates;
    public HtmlElement promoCode;
    public HtmlElement applyPromoCodeButton;
    public HtmlElement promoCodeRemove;
    public HtmlElement bonusItemColor;
    public List<HtmlElement> removeItem;

    public Integer itemCount() {
        return Integer.parseInt(bagItemCount.getText().replaceAll("\\D+", ""));
    }

    public String emptyBagMessage() {
        return emptyBagErrorMessage.getText();
    }

    public String getBagId() {
        Wait.until(shopBagId::isDisplayed);
        return shopBagId.getText().replaceAll("\\D+", "");
    }

    public void removeItem(Integer index) {
        removeItem.get(index).click();
        Wait.until(itemRemovedMsg::isDisplayed);
    }

    public ProductDisplay navigateToPDP(Integer index) {
        productUrls.get(index).click();
        return Navigate.get(ProductDisplay.class);
    }

    public String errorMessage() {
        return errorMessage.getText();
    }

    public WebElement itemLevelErrorMessage() {
        Wait.until(itemLevelErrorMessage::isDisplayed);
        return Elements.findElement("shopping_bag.item_level_error_message");
    }

    public String itemLevelErrorMessageText() {
        return Elements.findElement("shopping_bag.item_level_error_message").getText();
//        return itemLevelErrorMessage.getText();
    }

    public String registrantInfo() {
        return registrantNameDetails.getText();
    }

    public void removeBonusItemsFromBag() {
        com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.removeBonusItemsFromBag();
    }

    public List<Product> getAllProductDetails() {
        return com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.getAllProductDetails();
    }

    public int findItemIndexByType(List<Product> bagItems, String product_type) {
        return com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.findItemIndexByType(bagItems, product_type);
    }

    public void moveItemToWishlist(int index) {
        com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.moveItemToWishlist(index);
    }
}
