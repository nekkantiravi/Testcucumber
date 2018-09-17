package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.*;

/**
 * Created by atepliashin on 11/2/16.
 */
@FindBy(jsonPath = "change_pickup_store_dialog")
public class ChangePickupStoreDialog extends Page {

    public HtmlElement container;
    public TextInput addressZipCode;
    public Select searchDistance;
    public Button searchButton;
    public Button close;
    public Button save;
    public HtmlElement searchResultsSection;
    public HtmlElement loadingIndicator;
    public List<Button> selectStoreButton;
    public List<HtmlElement> errors;
    public List<HtmlElement> mapView;
    public List<HtmlElement> toggleMapView;
    public List<HtmlElement> itemAvailability;
    public List<HtmlElement> toggleItemAvailability;
    public List<HtmlElement> selectStoreName;

    public void findStores(String searchPhrase, Integer searchDistanceValue) {
        Wait.until(() -> addressZipCode.exists());
        addressZipCode.clear();
        if (searchDistanceValue != null) {
            if (bloomingdales()) {
                searchDistance.click();
                Wait.until(() -> searchDistance.getAttribute("class").contains("open"));
                List<WebElement> options = searchDistance.findElements(By.tagName("li"));
                options.stream().filter(option -> option.getText().contains(searchDistanceValue.toString())).findFirst().
                        orElseThrow(() -> new NoSuchElementException(String.format("No option with text %s found", searchDistance))).
                        click();
            } else {
                searchDistance.selectByValue(searchDistanceValue.toString());
            }
        }
        addressZipCode.sendKeys(searchPhrase);
        searchButton.click();
        if (bloomingdales()) {
            Wait.until(() -> !loadingIndicator.exists());
        } else {
            Wait.until(() -> searchButton.getAttribute("aria-busy").equalsIgnoreCase("false"));
        }
        Wait.until(() -> selectStoreButton.size() > 0);
    }

    public void selectStore(int number) {
        selectStoreButton.get(number).click();
    }

    public void save() {
        save.click();
        Wait.until(() -> !container.exists());
    }

    public void close() {
        close.click();
        Wait.until(() -> !container.exists());
    }

    public String getSearchFieldText() {
        return addressZipCode.getAttribute("value");
    }

    public void toggleMapView(int number) {
        boolean before = mapView.get(number).isDisplayed();
        toggleMapView.get(number).click();
        Wait.until(() -> mapView.get(number).isDisplayed() != before);
    }

    public void toggleItemAvailability(int number) {
        boolean before = itemAvailability.get(number).isDisplayed();
        toggleItemAvailability.get(number).click();
        Wait.until(() -> itemAvailability.get(number).isDisplayed() != before);
    }

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(() -> container.isDisplayed());
    }
}
