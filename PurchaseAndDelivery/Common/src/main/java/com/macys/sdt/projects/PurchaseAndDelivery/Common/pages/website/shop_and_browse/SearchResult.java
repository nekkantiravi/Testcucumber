package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces.Companion;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.interfaces.Facets;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by atepliashin on 12/20/16.
 */
@FindBy(jsonPath = "search_result")
public class SearchResult extends Page implements Facets, Companion {
    public HtmlElement selectedTab;
    public HtmlElement freePickupTab;
    public HtmlElement allItemsTab;
    public HtmlElement tabView;
    public HtmlElement bopsFacet;
    public HtmlElement storeName;
    public HtmlElement changeStore;
    public HtmlElement changeLocation;
    public HtmlElement zsrMessage;
    public HtmlElement loaderContainer;

    @Override
    public void waitForReady() {
        super.waitForReady();
        companionSidebar().hide();
        Wait.until(() -> !loaderContainer.isDisplayed(), 10);
    }
}
