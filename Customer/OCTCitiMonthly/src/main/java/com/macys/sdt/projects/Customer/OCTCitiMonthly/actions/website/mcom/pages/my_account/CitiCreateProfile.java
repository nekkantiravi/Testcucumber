package com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.mcom.pages.my_account;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil.getAddAuthUserInfo;
import static com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil.getProfileInfo;
import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile.selectDropDownIfPresent;

public class CitiCreateProfile extends StepUtils {

    static Logger logger = LoggerFactory.getLogger(CitiCreateProfile.class);

    /**
     * To create a profile for Citi credit testing
     *
     * @param testNum is test scenario number
     */
    public static void fillCreateProfileInfo(String testNum, String cardType, boolean testType) {

        try {

            User profileInfo = getProfileInfo(testNum);
            User authProfileInfo = getAddAuthUserInfo(cardType);
            JSONObject newProfileData = new JSONObject();

            String fName = testType ? authProfileInfo.getProfileAddress().getFirstName() : profileInfo.getProfileAddress().getFirstName();
            TextBoxes.typeTextbox("create_profile.verify_page", fName);
            String lName = testType ? authProfileInfo.getProfileAddress().getLastName() : profileInfo.getProfileAddress().getLastName();
            TextBoxes.typeTextbox("create_profile.last_name", lName);

            String email = profileInfo.getProfileAddress().getEmail();
            TextBoxes.typeTextbox("create_profile.email", email);
            TextBoxes.typeTextbox("create_profile.password", profileInfo.getLoginCredentials().getPassword());
            String newEmail;
            //Waits for a second and checks for email existence, if exists, then create new Email
            while (Wait.untilTextPresent("An account exists for this email address", 1) || Wait.secondsUntilElementPresent("create_profile.error_existing_email", 1)) {
                newEmail = email.replace("@", (new RandomStringUtils().randomAlphabetic(1) + "@"));
                TextBoxes.typeTextbox("create_profile.email", newEmail);
                Clicks.click("create_profile.password");
                email = newEmail;
            }
            //To add update email to @cardType in json file
            newProfileData = newProfileData.put("email", email);
            if (testType) {
                CreditServicesUtil.appendUpdateCardDetails("authUser", cardType, newProfileData);
            } else {
                CreditServicesUtil.appendUpdateCardDetails("addCard", cardType, newProfileData);
            }

            String dobMonth = testType ? WordUtils.capitalize(authProfileInfo.getDateOfBirth(authProfileInfo.getDateOfBirth()).getMonth().name().toLowerCase()) : WordUtils.capitalize(profileInfo.getDateOfBirth(profileInfo.getDateOfBirth()).getMonth().name().toLowerCase());
            String dobDay = testType ? String.valueOf(authProfileInfo.getDateOfBirth(authProfileInfo.getDateOfBirth()).getDayOfMonth()) : String.valueOf(profileInfo.getDateOfBirth(profileInfo.getDateOfBirth()).getDayOfMonth());
            String dobYear = testType ? String.valueOf(authProfileInfo.getDateOfBirth(authProfileInfo.getDateOfBirth()).getYear()) : String.valueOf(profileInfo.getDateOfBirth(profileInfo.getDateOfBirth()).getYear());
            if (bloomingdales()) {
                if (Elements.elementPresent("create_profile.security_question_list")) {
                    DropDowns.selectCustomText("create_profile.security_question_list", "create_profile.security_question_options", profileInfo.getUserPasswordHint().getQuestion());
                    TextBoxes.typeTextbox("create_profile.security_answer", profileInfo.getUserPasswordHint().getAnswer());

                    DropDowns.selectCustomText("create_profile.dob_month_list", "create_profile.dob_month_options", dobMonth);
                    DropDowns.selectCustomText("create_profile.dob_day_list", "create_profile.dob_day_options", dobDay);
                    DropDowns.selectCustomText("create_profile.dob_year_list", "create_profile.dob_year_options", dobYear);
                } else {
                    Assert.fail("Question element Not present, Exiting");
                }
            } else if (macys()) {
                selectDropDownIfPresent("create_profile.dob_month", dobMonth);
                selectDropDownIfPresent("create_profile.dob_day", dobDay);
                selectDropDownIfPresent("create_profile.dob_year", dobYear);
            } else {
                Assert.fail("Not on COM pages, Exiting");
            }

            if (chrome()) {
                Clicks.click("create_profile.create_profile_button");
            } else {
                Clicks.javascriptClick("create_profile.create_profile_button");
            }
        } catch (Throwable e) {
            logger.error("Error: Profile not created " + e.toString());
        }
    }
}
