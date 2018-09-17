package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.OrderStatus;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry.RegistryHome;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.HeaderAndFooter;
import com.macys.sdt.shared.utils.CommonUtils;
import org.openqa.selenium.Keys;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by atepliashin on 2/3/17.
 */
@FindBy(jsonPath = "home")
public class Home extends Page {

    public Link gotoOrderStatus;
    public HtmlElement verifyStore;
    public Button checkoutButton;
    public HtmlElement bagEmpty;
    public TextInput searchField;
    public Button searchButton;

    public HeaderAndFooter headerAndFooter() {
        return Navigate.get(HeaderAndFooter.class);
    }

    public OrderStatus orderStatus() {
        gotoOrderStatus.click();
        return Navigate.get(OrderStatus.class);
    }

    public void waitForReady() {
        super.waitForReady();
        Wait.until(verifyStore::isDisplayed);
    }

    public RegistryHome registryHome() {
        headerAndFooter().gotoWeddingRegistry.click();
        Wait.forPageReady();
        CommonUtils.closeIECertError();
        return Navigate.get(RegistryHome.class);
    }
    public void searchProducts(String searchPhrase) {
        Wait.until(searchField::isDisplayed);
        searchField.sendKeys(searchPhrase);
        searchField.sendKeys(Keys.ENTER);
        Wait.forPageReady();
    }


}
