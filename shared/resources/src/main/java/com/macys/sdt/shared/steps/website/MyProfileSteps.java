package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MyProfileSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(MyProfileSteps.class);

    @Then("^I verify error messages while creating profile with missing fields$")
    public void iVerifyErrorMessagesWhileCreatingProfileWithMissingFields(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        for (String key : values.keySet()) {
            String expectedErrorMessage = values.get(key);
            String fieldName = key;
            UserProfile userProfile = CommonUtils.createNewUserProfile();
            User user = userProfile.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();
            String[] dateOfBirth = user.getDateOfBirth().split("-");
            String dob = "";
            switch (fieldName) {
                case "dob_month":
                    dob = dateOfBirth[0] + "-Month-" + dateOfBirth[2];
                    user.setDateOfBirth(dob);
                    logger.info("dob_month set to blank.");
                    break;
                case "dob_day":
                    dob = dateOfBirth[0] + "-" + dateOfBirth[1] + "-Day";
                    user.setDateOfBirth(dob);
                    logger.info("dob_day set to blank.");
                    break;
                case "dob_year":
                    dob = "Year-" + dateOfBirth[1] + "-" + dateOfBirth[2];
                    user.setDateOfBirth(dob);
                    logger.info("dob_year set to blank.");
                    break;
                case "first_name":
                    profileAddress.setFirstName("");
                    logger.info("First name set to blank.");
                    break;
                case "last_name":
                    profileAddress.setLastName("");
                    logger.info("Last name set to blank.");
                    break;
            }
            CreateProfile.createProfile(userProfile);
            Wait.secondsUntilElementPresent("create_profile.error_empty_field", 20);
            String actualErrorMessage = Elements.getText("create_profile.error_empty_field");
            logger.info("Actual Error message :" + actualErrorMessage);
            Assert.assertEquals("ERROR - ENV: The expected error message: " + expectedErrorMessage + " did not appear on Create Profile page ", expectedErrorMessage, actualErrorMessage);
        }
    }

    @When("^I update my profile$")
    public void iUpdateMyProfile() throws Throwable {
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        ProfileAddress profileAddress = user.getProfileAddress();
        TextBoxes.typeTextbox("create_profile.verify_page", profileAddress.getFirstName());

    }

    @Then("^I should see the below message in the create_profile page:$")
    public void iShouldSeeMessageCreateAccountInTheCreate_profilePage(String legalNotice) throws Throwable {
        logger.info("Legal Notice text is : " + Elements.getText("create_profile.legal_notice_message"));
        Assert.assertTrue("ERROR - APP: Legal notice is not correct on create profile page. Actual message text is: " + Elements.getText("create_profile.legal_notice_message"), Elements.getText("create_profile.legal_notice_message").contains(legalNotice));
    }

    @Then("^I verify error message while creating profile with invalid data$")
    public void iVerifyErrorMessageWhileCreatingProfileWithInvalidData(DataTable dataTable) throws Throwable {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map map : data) {
            UserProfile userProfile = CommonUtils.createNewUserProfile();
            User user = userProfile.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();

            String fieldName = map.get("field_name").toString();
            String value = map.get("value").toString();
            switch (fieldName) {
                case "first_name":
                    profileAddress.setFirstName(value);
                    break;
                case "last_name":
                    profileAddress.setLastName(value);
                    break;
            }
            String expectedErrorMessage = map.get("error_message").toString();
            CreateProfile.createProfile(userProfile);
            Wait.secondsUntilElementPresent("create_profile.error_empty_field", 20);
            String actualErrorMessage = Elements.getText("create_profile.error_empty_field");
            logger.info("Actual Error message :" + actualErrorMessage);
            Assert.assertEquals("ERROR - ENV: The expected error message: " + expectedErrorMessage + " did not appear on Create Profile page ", expectedErrorMessage, actualErrorMessage);
        }
    }

}