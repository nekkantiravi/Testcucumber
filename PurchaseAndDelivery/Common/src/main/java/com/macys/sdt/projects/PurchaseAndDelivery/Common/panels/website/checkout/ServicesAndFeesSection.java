package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.Map;

@FindBy(jsonPath = "responsive_service_fees_section")
public class ServicesAndFeesSection extends Page {

    public CheckBox worryNoMoresPlans;
    public CheckBox defaultWorryNoMoresPlan;
    public CheckBox mattressRemoval;
    public CheckBox deliveryMilitaryBase;
    public CheckBox deliverySecurityGate;
    public CheckBox deliveryFlightStairs;
    public CheckBox deliveryNone;
    public CheckBox deliveryCoi;
    public HtmlElement rcServiceFeesSubmitBtn;
    public HtmlElement editServiceAndFees;
    public HtmlElement specialInstructionsMessage;
    public Select mattressQtyDropDown;
    public HtmlElement rcBigticketProtectionPlan;

    //    Selects worry no more check box in Services and Fees Section
    public void worryNoMoreCheckbox(int index) {
        if (Wait.until(worryNoMoresPlans::isDisplayed, 60)) {
            worryNoMoresPlans.set(true);
        }
    }

    //    Clicks on Services and Fees Section Continue button
    public void clickRcServiceFeesSubmitBtn() {
        if (Wait.until(rcServiceFeesSubmitBtn::isDisplayed, 60)) {
            rcServiceFeesSubmitBtn.click();
        }
    }

    //    Clicks on Services and Fees Section Continue button
    public void worrynomoreEdit() {
        if (Wait.until(editServiceAndFees::isDisplayed, 60)) {
            editServiceAndFees.click();
        }
    }

    //    Selects mattresses removal checkbox in Services and fees section
    public void selectMattressesFeeCheckbox() {
        if (Wait.until(mattressRemoval::isDisplayed, 30)) {
            mattressRemoval.select();
        }
    }

    //   Selects mattresses quantity drop down in Services and fees section
    public void selectMattressDropDown(int quantity) {
        if (mattressQtyDropDown.getOptions().size() > 1) {
            mattressQtyDropDown.selectByValue(String.valueOf(quantity));
        }
        selectMattressesFeeCheckbox();
    }

    // Selects the delivery questionaire checkboxes in Services and fees section
    public String getSpecialInstructionsMessage() {
        return specialInstructionsMessage.getText();
    }

    public void setDeliveryMilitaryBase(boolean value) {
        deliveryMilitaryBase.set(value);
    }

    public void setDeliverySecurityGate(boolean value) {
        deliverySecurityGate.set(value);
    }

    public void setDeliveryFlightStairs(boolean value) {
        deliveryFlightStairs.set(value);
    }

    public void setDeliveryNone(boolean value) {
        deliveryNone.set(value);
    }

//    deliveryMilitaryBase |true|
//    deliverySecurityGate |true|
//    deliveryFlightStairs |false|
//    The above table data is passed in the form of hash Map, where it selects the corresponding questionaire
//    for which the value is true

    public void selectServicesFeesOptions(Map<String, Boolean> selectServiceFeesOptions) {
        selectServiceFeesOptions.forEach((String option, Boolean value) -> {
                    switch (option) {
                        case "deliveryMilitaryBase":
                            setDeliveryMilitaryBase(value);
                            break;
                        case "deliverySecurityGate":
                            setDeliverySecurityGate(value);
                            break;
                        case "deliveryFlightStairs":
                            setDeliveryFlightStairs(value);
                            break;
                        case "deliveryNone":
                            setDeliveryNone(value);
                            break;
                        default:
                            setDeliveryMilitaryBase(true); // By default it will select first option
                    }
                }

        );
    }

}