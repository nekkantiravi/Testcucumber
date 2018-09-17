package com.macys.sdt.projects.Customer.OperationalSMSForSecurity.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.macys.sdt.framework.utils.StepUtils.MEW;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * Created by prvidya on 4/20/2017.
 */


public class SMSValidations {
    private static final Logger logger = LoggerFactory.getLogger(SMSValidations.class);
    private String emailAddress;
    private String password;

    @And("^I select text alert checkbox and enters mobile number$")
    public void iSelectTextAlertCheckbox() throws Throwable {
        Wait.untilElementPresent("create_profile.textme_yes");
        Clicks.javascriptClick("create_profile.textme_yes");
        TextBoxes.typeTextbox("create_profile.phone_number", TestUsers.generateRandomPhoneNumber());
    }

    @Then("^I should see texting programs as below$")
    public void iShouldSeeTextingProgramsAsBelow(List<String> alert_links) throws Throwable {
        alert_links.forEach(alert_link -> {
            Assert.assertTrue(alert_link + " link is not displaying on profile creation page", Elements.elementPresent("create_profile." + alert_link));
        });
    }

    @And("^I enter profile details$")
    public void iEnterProfileDetails() throws Throwable {
        Wait.forPageReady();
        String first_name = TestUsers.generateRandomFirstName();
        TextBoxes.typeTextbox("create_profile.verify_page", first_name);
        String last_name = TestUsers.generateRandomLastName();
        TextBoxes.typeTextbox("create_profile.last_name", last_name);
        emailAddress = TestUsers.generateRandomEmail(15);
        TextBoxes.typeTextbox("create_profile.email", emailAddress);
        password = randomAlphabetic(10);
        TextBoxes.typeTextbox("create_profile.password", password);
        DropDowns.selectRandomValue("create_profile.dob_month");
        DropDowns.selectRandomValue("create_profile.dob_day");
        DropDowns.selectRandomValue("create_profile.dob_year");
    }

    @When("^I select \"([^\"]*)\" checkbox$")
    public void iSelectCheckbox(String link) throws Throwable {
        Wait.secondsUntilElementPresent("create_profile." + link, 40);
        Clicks.selectCheckbox("create_profile." + link);
    }

    @And("^I select create profile button$")
    public void iSelectCreateProfileButton() throws Throwable {
        Wait.secondsUntilElementPresent("create_profile.create_profile_button", 40);
        Clicks.click("create_profile.create_profile_button");
    }

    @And("^I select text alert checkbox and don't enter mobile number$")
    public void iSelectTextAlertCheckboxAndDonTEnterMobileNumber() throws Throwable {
        Clicks.javascriptClick("create_profile.textme_yes");
    }


    @Then("^I should see the error message as below$")
    public void iShouldSeeTheErrorMessageAsBelow(List<String> error_message) throws Throwable {
        if (MEW()) {
            Elements.elementPresent("create_profile.security_alert_error_message");
            Assert.assertTrue("Error message is not displaying as expected", Elements.findElement("create_profile.security_alert_error_message").getAttribute("value").equals(error_message.get(0)));
        } else {
            Elements.elementPresent("create_profile.security_sms_error_message");
            String error_message_ui = Elements.findElement("create_profile.security_sms_error_message").getText();
            Assert.assertTrue("Error message is not displaying as expected", error_message.get(0).equals(error_message_ui));
        }
    }


    @Then("^I should see phone number error message as below$")
    public void iShouldSeePhoneNumberErrorMessageAsBelow(List<String> error_message) throws Throwable {
        if (MEW()) {
            Elements.elementPresent("create_profile.phone_error_msg");
            Assert.assertTrue("Error message is not displaying as expected", Elements.findElement("create_profile.phone_error_msg").getText().equals(error_message.get(0)));

        } else {
            Elements.elementPresent("create_profile.error_phone_number");
            String error_message_ui = Elements.findElement("create_profile.error_phone_number").getText();
            Assert.assertTrue("Error message is not displaying as expected", error_message.get(0).equals(error_message_ui));

        }
    }


    @And("^again I Uncheck Mobile Number field$")
    public void againIUncheckMobileNumberField() throws Throwable {
        Clicks.click(By.cssSelector("#textSalesEvents.subscribe"));
    }

    @Then("^entered mobile number should disappear and reset occurs$")
    public void enteredMobileNumberShouldDisappearAndResetOccurs() throws Throwable {
        if (MEW()) {
            Assert.assertEquals("", Elements.findElement("create_profile.telephone_number").getText());
        } else {
            Assert.assertEquals("", Elements.findElement("create_profile.phone_number").getText());
        }
    }

    @Then("^entered details should disappear in the mobile number field$")
    public void enteredDetailsShouldDisappearInTheMobileNumberField() throws Throwable {
        if (MEW()) {
            Assert.assertEquals("", Elements.findElement("create_profile.telephone_number").getText());
            Assert.assertFalse(Elements.findElement(By.id("m-j-accountCreateProfile-promoAlerts")).isSelected());
            Assert.assertFalse(Elements.findElement(By.id("m-j-accountCreateProfile-securityAlerts")).isSelected());
        } else {
            Assert.assertEquals("", Elements.findElement("create_profile.phone_number").getText());
            Assert.assertFalse(Elements.findElement(By.id("promoAlerts")).isSelected());
            Assert.assertFalse(Elements.findElement(By.id("securityAlerts")).isSelected());
        }
    }

    @When("^I sign in with my existing profile$")
    public void iSignInWithMyExistingProfile() throws Throwable {
        Clicks.click("home.goto_sign_in_link");
        TextBoxes.typeTextbox("sign_in.email", emailAddress);
        TextBoxes.typeTextbox("sign_in.password", password);
        Clicks.click("sign_in.verify_page");
        }

    @Then("^I should see account completion overlay$")
    public void iShouldSeeAccountCompletionOverlay() throws Throwable {
        Elements.elementPresent("sign_in.submit_sms_form");
            Assert.assertTrue("Security alert popup is not displaying", Wait.untilElementPresent("sign_in.submit_sms_form"));
    }

    @Then("^I should see security Q&A overlay$")
    public void iShouldSeeSecurityQAOverlay() throws Throwable {
        Wait.untilElementPresent("sign_in.security_question");
        Assert.assertTrue("Security Q&A setup overlay is not displayed", Elements.elementPresent("sign_in.security_question"));
        logger.info("Security Q&A setup overlay is displayed");
        UserProfile customer = TestUsers.getCustomer(null);
            DropDowns.selectByText("sign_in.security_question", customer.getUser().getUserPasswordHint().getQuestion());
            TextBoxes.typeTextbox("sign_in.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
            Clicks.click("sign_in.save_and_continue");
    }

    @When("^I click \"([^\"]*)\" on account completion overlay$")
    public void iClickOnAccountCompletionOverlay(String link) throws Throwable {
        Wait.secondsUntilElementPresent("sign_in." + link, 40);
        Clicks.selectCheckbox("sign_in." + link);
    }

    @Then("^I should land on home page$")
    public void iShouldLandOnHomePage() throws Throwable {
        logger.info("-> Verify home page!!");
        Wait.secondsUntilElementPresent("home.verify_page", 20);
        Assert.assertTrue("Home page is not displayed", Elements.elementPresent("home.verify_page"));
    }

    @When("^I sign out from my current web site profile$")
    public void iSignOutFromMyCurrentWebSiteProfile() throws Throwable {
        Clicks.click("my_account.goto_sign_out_link");
        Wait.untilElementNotPresent("my_account.goto_sign_out_link");
        if (Elements.elementPresent("my_account.goto_sign_out_link")) {
            Clicks.click("my_account.goto_sign_out_link");
        }
        Assert.assertTrue("ERROR - ENV: Unable to sign out.", Wait.untilElementPresent("home.goto_sign_in_link"));
    }

    @And("^now I Uncheck Mobile Number field$")
    public void nowIUncheckMobileNumberField() throws Throwable {
        Wait.untilElementPresent("create_profile.textme_yes");
        Clicks.javascriptClick("create_profile.textme_yes");
    }

    @Then("^entered mobile number should disappear and reset happens$")
    public void enteredMobileNumberShouldDisappearAndResetHappens() throws Throwable {
        if (MEW()) {
            Assert.assertEquals("", Elements.findElement("create_profile.phone_number").getText());
        } else {
            Assert.assertEquals("", Elements.findElement("create_profile.phone_number").getText());
        }
    }

    @Then("^entered details should disappear in the mobile number section$")
    public void enteredDetailsShouldDisappearInTheMobileNumberSection() throws Throwable {
        if (MEW()) {
            Assert.assertEquals("", Elements.findElement("create_profile.telephone_number").getText());
            Assert.assertFalse(Elements.findElement(By.id("m-j-accountCreateProfile-promoAlerts")).isSelected());
            Assert.assertFalse(Elements.findElement(By.id("m-j-accountCreateProfile-securityAlerts")).isSelected());
        } else {
            Assert.assertEquals("", Elements.findElement("create_profile.phone_number").getText());
            Assert.assertFalse(Elements.findElement(By.id("textMessages_cover")).isSelected());
            Assert.assertFalse(Elements.findElement(By.id("securityAlerts_cover")).isSelected());
        }
    }

    @When("^I select both text alerts$")
    public void iSelectBothTextAlerts() throws Throwable {
        Wait.untilElementPresent("create_profile.promo_alerts_checkbox");
        Clicks.selectCheckbox("create_profile.promo_alerts_checkbox");
        Wait.untilElementPresent("create_profile.security_alerts_checkbox");
        Clicks.selectCheckbox("create_profile.security_alerts_checkbox");
    }

    @Then("^I should see text Alerts status as below with mobile number$")
    public void iShouldSeeTextAlertsStatusAsBelowWithMobileNumber(List<Map<String, Object>> status) throws Throwable {
        Wait.untilElementPresent("customer_preferences.promotional_and_security_alert");
        List<WebElement> alerts = Elements.findElements("customer_preferences.promotional_and_security_alert");
        String[] promouidata = alerts.get(0).getText().split(":");
        String[] secuuidata = alerts.get(1).getText().split(":");
        if (status.get(0).containsKey("Promotional Alerts")) {
            String promoStatus1 = status.get(0).get("Promotional Alerts").toString();
            Assert.assertTrue("promo alert status is not displaying as expected", promouidata[1].toString().trim().equals(promoStatus1));
        }
        if (status.get(0).containsKey("Security Alerts")) {
            String securityStatus1 = status.get(0).get("Security Alerts").toString();
            Assert.assertTrue("security alert status is not displaying as expected", secuuidata[1].toString().trim().equals(securityStatus1));
        }
    }
}


