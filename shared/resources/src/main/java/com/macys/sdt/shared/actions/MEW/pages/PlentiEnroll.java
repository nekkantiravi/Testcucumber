package com.macys.sdt.shared.actions.MEW.pages;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;

/**
 * For enrolling Plenti in mew
 */
public abstract class PlentiEnroll extends StepUtils {

    /**
     * This method is to enroll customer to plenti
     *
     * @param customer customer details
     */
    public static void enroll(final UserProfile customer) {
        final User user = customer.getUser();
        final ProfileAddress profileAddress = user.getProfileAddress();

        if (!signedIn()) {
            TextBoxes.typeTextbox("plenti_enroll.first_name", profileAddress.getFirstName());
            TextBoxes.typeTextbox("plenti_enroll.last_name", profileAddress.getLastName());
            DropDowns.selectByText("plenti_enroll.dob_month", WordUtils.capitalize(user.getDateOfBirth(user.getDateOfBirth()).getMonth().name().toLowerCase()));
            DropDowns.selectByIndex("plenti_enroll.dob_day", user.getDateOfBirth(user.getDateOfBirth()).getDayOfMonth());
            DropDowns.selectByText("plenti_enroll.dob_year", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getYear()));
            TextBoxes.typeTextbox("plenti_enroll.email", profileAddress.getEmail());
            TextBoxes.typeTextbox("plenti_enroll.verify_email", profileAddress.getEmail());
            TextBoxes.typeTextbox("plenti_enroll.password", user.getLoginCredentials().getPassword());
            TextBoxes.typeTextbox("plenti_enroll.verify_password", user.getLoginCredentials().getPassword());
            DropDowns.selectByText("plenti_enroll.security_question", user.getUserPasswordHint().getQuestion());
            TextBoxes.typeTextbox("plenti_enroll.security_answer", user.getUserPasswordHint().getAnswer());
        }
        TextBoxes.typeTextbox("plenti_enroll.address_line_1", profileAddress.getAddressLine1().replace(".", ""));
        TextBoxes.typeTextbox("plenti_enroll.address_line_2", "");
        TextBoxes.typeTextbox("plenti_enroll.address_city", profileAddress.getCity());
        DropDowns.selectByText("plenti_enroll.address_state", AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()));
        TextBoxes.typeTextbox("plenti_enroll.address_zipcode", String.valueOf(profileAddress.getZipCode()));
        TextBoxes.typeTextbox("plenti_enroll.phone_number", profileAddress.getBestPhone());
        Clicks.click("plenti_enroll.btn_goto_plenti");
        Assert.assertFalse("ERROR-DATA: Unable to enroll in to plenty due to inputted data issue", Elements.elementPresent("plenti_enroll.page_level_error"));
    }

    /**
     * This method is for doing plenti sign up authentication before hand, use it before starting enrollment
     *
     * @throws DriverNotInitializedException if driver is not ready
     */
    public static void doAuthentication() throws DriverNotInitializedException {
        String url = WebDriverManager.getCurrentUrl();
        WebDriverManager.getWebDriver().get("https://online_ppu:Onl2014%3FP@e1et-www.plenti.com/macys/sign-up");
        Navigate.visit(url);
    }

    /**
     * Plenti enrollment step 1
     */
    public static void enrolStep1() {
        Wait.secondsUntilElementPresent("plenti_enroll_step1.password", 50);
        Elements.elementShouldBePresent("plenti_enroll_step1.password");
        TextBoxes.typeTextbox("plenti_enroll_step1.pin", "1234");
        TextBoxes.typeTextbox("plenti_enroll_step1.verify_pin", "1234");
        TextBoxes.typeTextbox("plenti_enroll_step1.password", "Macys12345");
        TextBoxes.typeTextbox("plenti_enroll_step1.verify_password", "Macys12345");
        Clicks.click("plenti_enroll_step1.next_button");
    }

    /**
     * Plenti enrollment step 2
     */
    public  static void enrolStep2() {
        Wait.secondsUntilElementPresent("plenti_enroll_step2.marketing_checkbox", 15);
        Elements.findElements("plenti_enroll_step2.marketing_checkbox").stream().limit(2).forEach(Clicks::click);
        Clicks.click("plenti_enroll_step2.next_button");
    }

    /**
     * Plenti enrollment set preferences
     */
    public static void setPreferences() {
        Wait.secondsUntilElementPresent("plenti_set_preferences.continue_button", 30);
        Elements.elementShouldBePresent("plenti_set_preferences.continue_button");
        Clicks.selectCheckbox("plenti_set_preferences.maybe_later");
        Clicks.click("plenti_set_preferences.continue_button");
    }
}

