package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@FindBy(jsonPath = "responsive_schedule_delivery_section")
public class DeliverySchedulingSection extends Page {

    private static final Logger logger = LoggerFactory.getLogger(DeliverySchedulingSection.class);

    public HtmlElement rcBtDeliveryItems;
    public HtmlElement rcScheduleDeliverySubmitBtn;
    public Select rcScheduleMonth;
    public Select rcScheduleDate;
    public HtmlElement deliverySectionActiveMessage;
    public HtmlElement rcBtScheduleDelivery;


    public String itemdeliverySectionContentCheck() {
        return rcBtDeliveryItems.getText();
    }

    //    Select available month from drop down in Schedule Delivery Section
    public void scheduleClosestAvailableDate() {
        rcScheduleMonth.selectByIndex(1);
        Wait.until(rcScheduleDate::isEnabled);
        rcScheduleDate.selectByIndex(1);
    }

    /**
     * Select available Dates from drop down in Schedule Delivery Section
     *
     * @param scheduleDate date to be selected
     */
    public void scheduleAvailableDeliveryDates(Date scheduleDate) {
        DateFormat dateMonthFormat = new SimpleDateFormat("MMM MM/dd/yyyy");
        String formattedDate = dateMonthFormat.format(scheduleDate);

        String month = formattedDate.split("\\s")[0];
        rcScheduleMonth.selectByValue(month);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (WebElement date : rcScheduleDate.getOptions()) {
            String availableDateText = date.getText();
            try {
                Date availableDate = dateFormat.parse(availableDateText);
                if (availableDate.after(scheduleDate)) {
                    rcScheduleDate.selectByValue(availableDateText);
                    Checkout.selectedScheduleDate = availableDateText;
                    return;
                }
            } catch (ParseException e) {
                logger.error("Can't parse date from the list of dates: " + availableDateText);
                throw new AssertionError(e);
            }
        }
        logger.info("Can't select any date");
    }

    //    Click on Delivery Scheduling continue button in Schedule Delivery Section
    public void clickScheduleDeliverySubmitButton() {
        if (Wait.until(rcScheduleDeliverySubmitBtn::isDisplayed, 30)) {
            rcScheduleDeliverySubmitBtn.click();
        }
    }

    public String getActiveMessageText() {
        if (Wait.until(deliverySectionActiveMessage::isDisplayed)) {
            return deliverySectionActiveMessage.getText();
        }
        return "";
    }

}
