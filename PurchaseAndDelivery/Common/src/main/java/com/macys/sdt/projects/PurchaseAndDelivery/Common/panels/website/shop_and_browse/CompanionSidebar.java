package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by atepliashin on 1/13/17.
 */
@FindBy(jsonPath = "companion_sidebar")
public class CompanionSidebar extends Page {

    public HtmlElement container;

    public void hide() {
        if (container.exists()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                    container, "style", "display:none;");
        }
    }
}
