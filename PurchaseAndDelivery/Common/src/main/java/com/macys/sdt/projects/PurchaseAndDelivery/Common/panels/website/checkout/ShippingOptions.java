package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Radio;

import java.util.List;
import java.util.stream.Collectors;
import static com.macys.sdt.framework.utils.AbbreviationHelper.getStateAbbreviation;
import static com.macys.sdt.framework.utils.AbbreviationHelper.translateStateAbbreviation;

@FindBy(jsonPath = "responsive_shipping_options")
public class ShippingOptions extends Page {

    // change element type for any HtmlElement to appropriate one when update this page object
    public HtmlElement shippingFirstName;
    public HtmlElement shippingLastName;
    @FindBy(jsonPath = "responsive_shipping_options.shipping_address_line_1")
    public HtmlElement shippingAddressLine1;
    @FindBy(jsonPath = "responsive_shipping_options.shipping_address_line_2")
    public HtmlElement shippingAddressLine2;  
    public HtmlElement shippingAddressCity;  
    public Select shippingAddressState;
    public HtmlElement shippingAddressZipCode;  
    public HtmlElement shippingPhoneNumber;  

    public HtmlElement vgcShippingInfo;  
    public HtmlElement shipToRegistrant;  
    public HtmlElement shipToOtherAddress;  

    public HtmlElement everyDayFreeShipping;
    public HtmlElement standardShipping;
    public HtmlElement premiumShipping;
    public HtmlElement expressShipping;  
    public HtmlElement sddShipping;  
    public HtmlElement signinStandardShipping;  
    public HtmlElement signinPremiumShipping;  
    public HtmlElement signinExpressShipping;  
    public HtmlElement signinSddShipping;  
    public List<HtmlElement> shippingOptions;
    public List<HtmlElement> shippingOptionsElements;
    public HtmlElement shippingMethodsSignedin;  
    public HtmlElement nohurryShipping;  
    public HtmlElement nohurryLearnMoreLink;  
    public HtmlElement nohurryLearnMoreContent;  
    public HtmlElement rcSigninIcwSuggestion;  
    public HtmlElement signinNohurryShipping;  
    public HtmlElement giftOption;  
    public HtmlElement giftMessage;  
    public HtmlElement giftMessageField1;  
    public HtmlElement giftMessageField2;  
    public HtmlElement giftMessageField3;  
    public CheckBox giftBox;
    public HtmlElement giftReceipt;  
    public HtmlElement giftOptionsSaveSignedIn;  
    public Button giftOptionsExpandSignedIn;
    public HtmlElement pickupFirstName;
    public HtmlElement pickupLastName;  
    public HtmlElement pickupEmailAddress;  
    public HtmlElement pickupPhoneNumber;  
    public HtmlElement bopsPickupStoreSection;  

    public HtmlElement shippingAddressSection;  
    public HtmlElement shippingMethodsSection;  
    public HtmlElement shippingEgiftCardSection;  

    public HtmlElement pickupByMe;  
    public HtmlElement pickupBySomeoneElse;  
    public HtmlElement bopsGiftOptionsSection;  
    public HtmlElement bopsGiftOption;  
    public HtmlElement sddShippingNote;  
    public HtmlElement bopsSmsOption;  
    public HtmlElement registryPrivacyText;  

    public HtmlElement continueShippingCheckoutButton;
    public List<HtmlElement> shippingMethods;
    public Radio       shippingMethodRadioButtons;
    public HtmlElement shippingMethodNames;
    public HtmlElement shippingCostValue;  
    public Button      editShippingInfo;
    public HtmlElement itemLevelError;  
    public HtmlElement errorContainer;  
    public HtmlElement warningContainer;

    public void fillShippingAddress(ProfileAddress address) {
        Wait.until(shippingFirstName::isDisplayed);
        shippingFirstName.sendKeys(address.getFirstName());
        shippingLastName.sendKeys(address.getLastName());
        shippingAddressLine1.sendKeys(address.getAddressLine1());
        shippingAddressLine2.sendKeys(address.getAddressLine2());
        shippingAddressCity.sendKeys(address.getCity());
        if (StepUtils.macys()) {
            shippingAddressState.selectByVisibleText(getStateAbbreviation(address.getState()));
        } else {
            shippingAddressState.selectByVisibleText(translateStateAbbreviation(getStateAbbreviation(address.getState())));
        }
        shippingPhoneNumber.sendKeys(address.getBestPhone());
        shippingAddressZipCode.sendKeys(address.getZipCode());
    }
    
    public void updateShippingName(String newFirstName, String newLastName) {
        shippingFirstName.clear();
        shippingFirstName.sendKeys(newFirstName);

        shippingLastName.clear();
        shippingLastName.sendKeys(newLastName);

        continueShippingCheckoutButton.click();
    }

    public void enterPickUpInfo(ProfileAddress pickupAddress) {
        pickupByMe.click();
        pickupFirstName.sendKeys(pickupAddress.getFirstName());
        pickupLastName.sendKeys(pickupAddress.getLastName());
        pickupEmailAddress.sendKeys(pickupAddress.getEmail());
        pickupPhoneNumber.sendKeys(pickupAddress.getBestPhone());

        continueShippingCheckoutButton.click();
    }

    public List<String> getErrorMessages() {
        return errorContainer.findElements(By.cssSelector("ul > li")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getWarningMessages() {
        return warningContainer.findElements(By.cssSelector("ul > li")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectPickupBySomeoneElse() {
        pickupBySomeoneElse.click();
    }

    public String getSelectedShippingDetails() {
        String path = StepUtils.macys() ? "self::*//ancestor::*[contains(@class,'rc-highlight')]" : "self::*//ancestor::*[contains(@class,'bl_tileChecked')]";
        return shippingMethodRadioButtons.getSelectedButton().findElement(By.xpath(path)).getText();
    }

    public List<String> getAvailableShippingMethods() {
        return shippingMethods.stream().filter(WebElement::isDisplayed).map(WebElement::getText).collect(Collectors.toList());
    }
}
