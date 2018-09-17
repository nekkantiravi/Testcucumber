package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces.Companion;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.AddToBagDialog;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Image;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.get;

/**
 * Created by atepliashin on 11/4/16.
 */
@FindBy(jsonPath = "product_display")
public class ProductDisplay extends Page implements Companion {

    public HtmlElement productTitle;
    public Button addToBagButton;
    public HtmlElement price;
    public HtmlElement priceBox;
    public Image productImage;
    public HtmlElement productDescriptionBlock;
    public HtmlElement unavailable;
    public HtmlElement technicalError;

    public TextInput btZipcodeField;
    public Button btZipcodeSubmitBtn;
    public HtmlElement btAvailabilityInfo;
    public HtmlElement swatches;
    public HtmlElement btProductAvailabilityMsg;
    public HtmlElement bigticketTalkToExperts;

    @Override
    public void waitForReady() {
        super.waitForReady();
        companionSidebar().hide();
    }

    public void clickTechnicalerror() {
        if (Wait.until(technicalError::isDisplayed)) {
            technicalError.click();
        }
    }

    public AddToBagDialog addToBagdialog() {
        return get(AddToBagDialog.class);
    }

}