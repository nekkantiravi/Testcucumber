package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.responsive_bag;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.my_account.SignIn;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by m431959 on 9/7/17.
 */
@FindBy(jsonPath = "responsive_bag")
public class ResponsiveBag extends Page {


    private HtmlElement dealsAndPromotions;
    public HtmlElement stickyOrderSummary;
    private List<HtmlElement> removeItems;
    public HtmlElement bagIcon;
    public HtmlElement checkoutFromBag;
    public HtmlElement bagId;
    public HtmlElement headerText;
    public HtmlElement availabilityText;
    public HtmlElement bagPageWrapper;
    public HtmlElement emptyBag;
    public HtmlElement signIn;
    public HtmlElement continueShopping;
    public HtmlElement bagHeader;
    public HtmlElement removeItem;
    public HtmlElement bagItems;
    public HtmlElement orderSummary;
    public HtmlElement checkout;
    private TextInput promoInput;
    private Button promoApply;
    private HtmlElement promoAppliedDescription;
    private HtmlElement promotionSave;
    private HtmlElement notificationError;
    public Select jsChangeQuantity;
    public List<HtmlElement> bagItemContainer;
    private final String sequenceNumberAttributes = "sequenceNumber";


    public HtmlElement getSignIn() { return signIn; }

    public HtmlElement getContinueShopping() {
        return continueShopping;
    }

    public HtmlElement getDealsAndPromotions() {
        return dealsAndPromotions;
    }

    public HtmlElement getCheckout() {
        return checkout;
    }

    public HtmlElement getStickyOrderSummary() {
        return stickyOrderSummary;
    }

    public List<HtmlElement> getRemoveItems() {
        return removeItems;
    }

    public List<HtmlElement> getBagItemContainer() {
        return bagItemContainer;
    }

    public SignIn clickSignInButton() {
        signIn.click();
        return Navigate.get(SignIn.class);
    }

    public List<HtmlElement> getRemoveItem() {
        return removeItems;
    }

    public void clickContinueShoppingButton() {
        continueShopping.click();
    }

    public void clickDealsAndPromotionButton() {
        dealsAndPromotions.click();
    }

    public void clickCheckoutButton() {
        checkout.click();
    }

    public void clickBagIcon() {
        bagIcon.click();
    }

    public boolean checkStickyOrderSummaryClass() {
        String cssClass = stickyOrderSummary.findElement(By.id("sticky-order-summary")).getAttribute("class");
        return cssClass.equals("sticky-summary");
    }

    public TextInput getPromoInput() {
        return promoInput;
    }

    public void setPromoInput(TextInput promoInput) {
        this.promoInput = promoInput;
    }

    public Button getPromoApply() {
        return promoApply;
    }

    public void setPromoApply(Button promoApply) {
        this.promoApply = promoApply;
    }

    public HtmlElement getPromoAppliedDescription() {
        return promoAppliedDescription;
    }

    public void setPromoAppliedDescription(HtmlElement promoAppliedDescription) {
        this.promoAppliedDescription = promoAppliedDescription;
    }

    public HtmlElement getPromotionSave() {
        return promotionSave;
    }

    public void setPromotionSave(HtmlElement promotionSave) {
        this.promotionSave = promotionSave;
    }

    public HtmlElement getNotificationError() {
        return notificationError;
    }

    public void setNotificationError(HtmlElement notificationError) {
        this.notificationError = notificationError;
    }

    public String removeItem() {
        if (!removeItems.isEmpty()) {
            String sequenceNumber = bagItemContainer.get(0).getAttribute(sequenceNumberAttributes);
            removeItems.get(0).click();
            return sequenceNumber;
        }
        return null;
    }

    public void removeItems() {
        for (HtmlElement removeItem : removeItems) {
            removeItem.click();
        }
    }

}
