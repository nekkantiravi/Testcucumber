package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.MEW.shop_and_browse;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces.Companion;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * Created by Maria on 10/18/17.
 */
@FindBy(jsonPath = "product_display")
public class ProductDisplayMew extends Page implements Companion {
    public HtmlElement productColorName;
    public HtmlElement productId;
    public HtmlElement productRegPrice;
    public HtmlElement productSalePrice;
    public HtmlElement productBrand;
    public HtmlElement productName;
    public HtmlElement productBopsStoreName;
    public Select selectSizeDropdown;
    public HtmlElement verifyPage;
    public String pdpPrice = null;
    public List<HtmlElement> chanelProductName;
    public HtmlElement chanelProductDescription;
    public HtmlElement storeLookup;

    @Override
    public void waitForReady() {
        super.waitForReady();
        companionSidebar().hide();
    }

    public String getPdpColor() {
        if (productColorName.exists()) {
            return productColorName.getText();
        } else {
            return null;
        }
    }

    public String getPdpId() {
        Wait.until(productId::exists);
        return productId.getText().replaceAll("\\D+", "");
    }

    public String getPdpBrandAndProductName() {
        if (productBrand.getText().toLowerCase().contains("chanel")) {
            return productBrand.getText().toLowerCase() + " " +
                    chanelProductName.get(1).getText().toLowerCase() + " " +
                    chanelProductDescription.getText().toLowerCase();
        } else {
            return productBrand.getText().toLowerCase() + " " + productName.getText().toLowerCase();
        }
    }

    public String getPdpPrice() {
        if (verifyPage.isDisplayed()) {
            pdpPrice = productSalePrice.getText().toLowerCase();
        } else {
            pdpPrice = productRegPrice.getText().toLowerCase();
        }
        return pdpPrice;
    }

    public void selectSize() {
        if (selectSizeDropdown.exists()) {
            List<WebElement> elements = selectSizeDropdown
                    .getOptions()
                    .stream()
                    .filter(e -> !e.getText().contains("not available") && !e.getText().contains("Select A Size"))
                    .collect(Collectors.toList());
            int randomIndex = new Random().nextInt(elements.size());
            selectSizeDropdown.selectByVisibleText(elements.get(randomIndex).getText());
        }
    }
}
