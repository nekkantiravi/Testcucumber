package com.macys.sdt.shared.actions.website.mcom.pages.my_account;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import static com.macys.sdt.framework.utils.StepUtils.macys;

/**
 * Created by yc03ds3 on 10/25/2016.
 * This class represents the page class for My Profile web page.
 * This is the place where you should be doing all the interactions with My Profile page.
 */
public class MyProfile {
    private static final Logger logger = LoggerFactory.getLogger(MyProfile.class);
    public boolean getDobFromMyProfilePage() {
        String myProfileDob;
        UserProfile customer = TestUsers.getCustomer(null);

        //String yr = DropDowns.getSelectedValue(Elements.element("my_profile.dob_year"));
        //String mn = DropDowns.getSelectedValue(Elements.element("my_profile.dob_month"));
        //String dy = DropDowns.getSelectedValue(Elements.element("my_profile.dob_day"));

        Select dob = new Select(Elements.findElement("my_profile.dob_year"));
        WebElement year = dob.getFirstSelectedOption();
        dob = new Select(Elements.findElement("my_profile.dob_month"));
        WebElement month = dob.getFirstSelectedOption();
        dob = new Select(Elements.findElement("my_profile.dob_day"));
        WebElement day = dob.getFirstSelectedOption();

        //in case day is 1-9, we need to add 0 to it so that it becomes 01,02,03 and so on..
        if (day.getText().length() == 1) {
            myProfileDob = year.getText() + "-" + getMonthName(month.getText()) + "-" + "0" + day.getText();
        } else {
            myProfileDob = year.getText() + "-" + getMonthName(month.getText()) + "-" + day.getText();
        }
        return (customer.getUser().getDateOfBirth()).equalsIgnoreCase(myProfileDob);
    }

    private String getMonthName(String str) {
        String monthName = "";
        switch (str) {
            case "January":
                monthName = "01";
                break;
            case "February":
                monthName = "02";
                break;
            case "March":
                monthName = "03";
                break;
            case "April":
                monthName = "04";
                break;
            case "May":
                monthName = "05";
                break;
            case "June":
                monthName = "06";
                break;
            case "July":
                monthName = "07";
                break;
            case "August":
                monthName = "08";
                break;
            case "September":
                monthName = "09";
                break;
            case "October":
                monthName = "10";
                break;
            case "November":
                monthName = "11";
                break;
            case "December":
                monthName = "12";
                break;
            default:
                monthName = "Invalid month";
                break;
        }
        return monthName;
    }

    public boolean getMonthDayYearFromMyProfilePage() {
        String myProfileDob;
        UserProfile customer = TestUsers.getCustomer(null);
        if(macys()) {
            return getDobFromMyProfilePage();
        }
        else {
            String year = Elements.getElementAttribute("my_profile.profile_dob_year", "value");
            logger.info("Selected year is: "+year);
            String month = Elements.getElementAttribute("my_profile.profile_dob_month", "value");
            logger.info("Selected month is: "+month);
            String day = Elements.getElementAttribute("my_profile.profile_dob_day", "value");
            logger.info("Selected month is: "+day);
            if (day.length() == 1)
            {
                myProfileDob = year + "-" + month + "-" + "0" + day;
                logger.info("My Profile DOB value is: "+myProfileDob);
            }
            else
            {
                myProfileDob = year + "-" + month + "-" + day;
                logger.info("My Profile DOB value is: "+myProfileDob);
            }
            return (customer.getUser().getDateOfBirth()).equalsIgnoreCase(myProfileDob);
        }
    }
}
