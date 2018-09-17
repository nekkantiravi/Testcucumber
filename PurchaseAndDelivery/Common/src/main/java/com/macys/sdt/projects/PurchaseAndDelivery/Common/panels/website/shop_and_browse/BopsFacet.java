package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.ChangePickupStoreDialog;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.*;

/**
 * Created by atepliashin on 12/19/16.
 */
@FindBy(jsonPath = "bops_facet")
public class BopsFacet extends Page {

    public TextInput zipcodeField;
    public Button searchByZipcode;
    public Link changeLocation;
    public HtmlElement searchedZipcode;
    public HtmlElement pickUpInStoreFacetName;
    public List<HtmlElement> pickUpInStoreOption;
    public Select milesSelect;

    public BopsFacet searchStoresForPickup(String zipcode) {
        zipcodeField.sendKeys(zipcode);
        searchByZipcode.click();
        Wait.until(() -> changeLocation.isDisplayed());
        return this;
    }

    public BopsFacet changeLocation(String searchPhrase, Integer miles, Integer storeIndex) {
        ChangePickupStoreDialog changePickupStoreDialog = initiateStoreChange();
        changePickupStoreDialog.findStores(searchPhrase, miles);
        changePickupStoreDialog.selectStore(storeIndex);
        changePickupStoreDialog.save();
        return this;
    }

    public ChangePickupStoreDialog initiateStoreChange() {
        changeLocation.click();
        return Navigate.get(ChangePickupStoreDialog.class);
    }

    public BopsFacet expand() {
        if (!(zipcodeField.exists() && zipcodeField.isDisplayed()) &&
                !(changeLocation.exists() && changeLocation.isDisplayed())) {

            if (bloomingdales()) {
                String styleBefore = pickUpInStoreFacetName.getAttribute("style");
                pickUpInStoreFacetName.click();
                Wait.until(() -> !pickUpInStoreFacetName.getAttribute("style").equals(styleBefore));
            } else {
                pickUpInStoreFacetName.click();
            }
        }
        Wait.until(() -> (zipcodeField.exists() && zipcodeField.isDisplayed())
                || (changeLocation.exists() && changeLocation.isDisplayed()));
        return this;
    }

    public void selectMiles(String miles) {
        if (bloomingdales()) {
            throw new IllegalArgumentException("Not implemented for BCOM yet");
        } else {
            milesSelect.selectByValue(miles);
        }
    }

    public String getSelectedMiles() {
        if (bloomingdales()) {
            return milesSelect.getAttribute("data-selected");
        } else {
            return milesSelect.getFirstSelectedOption().getAttribute("value");
        }
    }

    public boolean isStoreOptionSelected(int index) {
        if (pickUpInStoreOption.size() < index + 1) {
            return false;
        }
        HtmlElement storeOption = pickUpInStoreOption.get(index);
        return !storeOption.getAttribute("class").contains("disabled") &&
                (storeOption.getAttribute("class").contains("selected") ||
                        storeOption.findElement(By.tagName("a")).getAttribute("class").contains("selected"));
    }

}
