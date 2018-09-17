package com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;

/**
 * Created by atepliashin on 1/27/17.
 *
 * This wrapper allows to treat all select elements the same way.
 * The reason of introduction of this class currently BCOM uses specific customized selects.
 * example of customized select:
 *
 * <select>hidden select element
 * <option>option one</option>
 * <option>option two</option>
 * </select>
 * <div>customized select here
 * <ul>
 *      <li class="option one"></li>
 *      <li class="option two"></li>
 * </ul>
 * </div>
 * @todo implement full ISelect interface
 */
public class Select extends TypifiedElement {

    public Select(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void selectByIndex(int index) {
        if (hasSelectTag()) {
            getSelect().selectByIndex(index);
        } else {
            List<WebElement> options = getOptions();
            if (index > -1 && index < options.size()) {
                options.get(index).click();
            } else {
                throw new NoSuchElementException("Cannot locate option with index: " + index);
            }
        }
    }

    public void selectByVisibleText(String text) {
        if (hasSelectTag()) {
            getSelect().selectByVisibleText(text);
        } else {
            click();
            getOptions().stream().filter(option -> option.getText().equals(text)).findFirst().
                    orElseThrow(() -> new NoSuchElementException("Cannot locate element with text: " + text)).
                    click();
        }
    }

    public List<WebElement> getOptions() {
        return hasSelectTag() ? getSelect().getOptions() : findElements(By.tagName("li"));
    }

    private ru.yandex.qatools.htmlelements.element.Select getSelect() {
        return new ru.yandex.qatools.htmlelements.element.Select(this);
    }

    private boolean hasSelectTag() {
        return getTagName().equals("select");
    }
}
