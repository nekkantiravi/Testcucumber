package com.macys.sdt.projects.Customer.OperationalSMSForSecurity.steps.mew.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.TestUsers;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * Created by prvidya on 5/4/2017.
 */
public class SMSValidations {
    @And("^I select sms alerts checkbox and enters mobile number$")
    public void iSelectSmsAlertsCheckboxAndEntersMobileNumber() throws Throwable {
        Wait.secondsUntilElementPresent("create_profile.text_alerts", 40);
        Clicks.click("create_profile.text_alerts");
        TextBoxes.typeTextbox("create_profile.telephone_number", "8789898989");
    }

    @And("^I enter user details for mew$")
    public void iEnterUserDetails() throws Throwable {
        Wait.forPageReady();
        String first_name = TestUsers.generateRandomFirstName();
        TextBoxes.typeTextbox("create_profile.verify_page", first_name);
        TextBoxes.typeTextbox("create_profile.verify_page", first_name);
        String last_name = TestUsers.generateRandomLastName();
        TextBoxes.typeTextbox("create_profile.last_name", last_name);
        String email_address = TestUsers.generateRandomEmail(15);
        TextBoxes.typeTextbox("create_profile.email", email_address);
        String password = randomAlphabetic(10);
        TextBoxes.typeTextbox("create_profile.password", password);
        DropDowns.selectRandomValue("create_profile.dob_month");
        DropDowns.selectRandomValue("create_profile.dob_day");
        DropDowns.selectRandomValue("create_profile.dob_year");

    }

    @And("^I select sms alerts checkbox and don't enter mobile number$")
    public void iSelectSmsAlertsCheckboxAndDonTEnterMobileNumber() throws Throwable {
        Clicks.click("create_profile.text_alerts");
    }

    @And("^I select create account button$")
    public void iSelectCreateAccountButton() throws Throwable {
        Wait.secondsUntilElementPresent("create_profile.create_profile_button", 40);
        Clicks.click("create_profile.create_profile_button");
    }


    @Then("^I should see text programs as below$")
    public void iShouldSeeTextProgramsAsBelow(List<String> alert_links) throws Throwable {
        alert_links.forEach(alert_link -> {
            Assert.assertTrue(alert_link + " link is not displaying on profile creation page", Elements.elementPresent("create_profile." + alert_link));
        });
    }

    @When("^I selects \"([^\"]*)\" checkbox$")
    public void iSelectsCheckbox(String link) throws Throwable {
        Wait.secondsUntilElementPresent("create_profile."+link,40);
        Clicks.selectCheckbox("create_profile."+link);
    }

    @And("^again I Uncheck sms alerts checkbox$")
    public void againIUncheckSmsAlertsCheckbox() throws Throwable {
        Wait.secondsUntilElementPresent("create_profile.text_alerts", 40);
        Clicks.click("create_profile.text_alerts");
    }
}
