package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.List;

/**
 * Created by vguddati on 6/20/2017.
 */
@FindBy(jsonPath = "change_pickup_store_within_dialog")
public class ChangePickupStoreWithinDialog extends Page {
    public HtmlElement tabView;
    public Button save;
    public List<HtmlElement> selectStoreButton;
    public HtmlElement close;
    public HtmlElement changeLocation;
    public HtmlElement container;
    public Select milesSelect;
    public HtmlElement storeResults;

    public void save() {
        save.click();
        Wait.until(() -> !tabView.exists());
    }

    public void close() {
        close.click();
        Wait.until(() -> !tabView.exists());
    }

    public void selectMiles(String miles) {
        milesSelect.selectByValue(miles);
    }

    public String getSelectedMiles() {
        return milesSelect.getAttribute("value");
    }
}