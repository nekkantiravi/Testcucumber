package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yc04185 on 5/3/17.
 */
@FindBy(jsonPath = "responsive_address_section")
public class AddressInfoSection extends Page {

    public TextInput firstName;
    public TextInput lastName;
    public TextInput addressLine1;
    public TextInput addressLine2;
    public TextInput addressCity;
    public Select addressState;
    public TextInput addressZipCode;
    public List<HtmlElement> addressFieldErrors;
    public TextInput paymentEmail;
    public TextInput phoneNumber;

    public void fillDefaultData(boolean isEmailNeeded) {
        this.firstName.sendKeys(TestUsers.generateRandomFirstName());
        this.lastName.sendKeys(TestUsers.generateRandomLastName());
        this.addressLine1.sendKeys("548 Market St");
        this.addressLine2.sendKeys("");
        this.addressCity.sendKeys("San Francisco");
        if (StepUtils.macys()) {
            this.addressState.selectByVisibleText("CA");
        } else {
            this.addressState.selectByVisibleText("California");
        }
        this.addressZipCode.sendKeys("94104");
        this.phoneNumber.sendKeys(TestUsers.generateRandomPhoneNumber());
        if (isEmailNeeded) {
            this.paymentEmail.sendKeys(TestUsers.generateRandomEmail(15));
        }
    }

    public void fillNewCustomerName(String newFirstName, String newLastName) {
        Wait.until(firstName::isDisplayed);
        this.firstName.clear();
        this.firstName.sendKeys(newFirstName);
        this.lastName.clear();
        this.lastName.sendKeys(newLastName);
    }

    public List<String> getFieldLevelErrors() {
        return addressFieldErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
