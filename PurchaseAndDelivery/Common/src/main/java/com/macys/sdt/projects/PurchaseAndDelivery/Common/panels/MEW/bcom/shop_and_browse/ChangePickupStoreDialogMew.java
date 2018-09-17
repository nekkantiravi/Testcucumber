package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.MEW.bcom.shop_and_browse;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(jsonPath = "change_pickup_store_dialog")
public class ChangePickupStoreDialogMew extends Page {

    public HtmlElement stores;
    public HtmlElement storePhone;
    public HtmlElement map;
    public HtmlElement error;
    public HtmlElement selectedStoreName;
    public HtmlElement storeDistance;
    public HtmlElement productName;
    public HtmlElement bopsColor;
    public HtmlElement bopsId;
    public HtmlElement bopsPrice;
    public Button bopsSearchButton;
    public Button saveButton;
    public Button saveButtonBottom;
    public Button mapLink;
    public TextInput addressZipCode;
    public String childCss = "span:not(.product-detail-label)";
    public HtmlElement loadingIndicator;


    public String getBopsColor() {
        if (bopsColor.exists()) {
            return bopsColor.findElement(By.cssSelector(childCss)).getText();
        } else {
            return null;
        }
    }

    public String getBopsId() {
        Wait.until(bopsId::exists, 10);
        return bopsId.findElement(By.cssSelector(childCss)).getText();
    }

    public void EnterZipCodeAndSearch(String zipcode) {
        Wait.until(() -> addressZipCode.isDisplayed(), 10);
        addressZipCode.clear();
        addressZipCode.sendKeys(zipcode);
        Wait.until(() -> bopsSearchButton.isDisplayed(), 10);
        Clicks.javascriptClick(bopsSearchButton);
        Wait.until(() -> !loadingIndicator.exists(), 10);
    }
}
