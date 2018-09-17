package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.steps.website.common;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.utils.db.Service;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class TaggedProfileValidation extends StepUtils {
    public String user_id;
    public String user_email;
    public String user_pwd;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TaggedProfileValidation.class);
    UserProfile profile = null;


    private void softLockedUser(String user_id, String user_email){;
        Service softlock = new Service();
        softlock.softLockUser(user_id, user_email);
        log.info("Softlocked the user " + user_email);
    }

    @Then("^I should see the error as:$")
    public void iShouldSeeTaggedErrorMessage(String actualerrorMessage) throws Throwable {
        String err_msg = "";
        if(MEW()){
            Thread.sleep(500);
            err_msg = Elements.findElement("sign_in.error_message").getText();
        } else if (onPage("sign_in")) {
            err_msg = Elements.findElement("sign_in.error_message").getText();
        } else {
                err_msg = Elements.findElement("checkout_sign_in.tagged_user_error").getText();
        }
        log.info("Error message is: " + err_msg);
        Assert.assertTrue(err_msg.contains(actualerrorMessage));
    }

    @When("^I click the link in the error message$")
    public void iClickRNLink() throws Throwable {
        if (MEW()) {Clicks.click("sign_in.reset_it_now");} else {
            Clicks.click("sign_in.reset_pwd_tagged_link");
        }
    }

    @Then("^I should navigate to forgot password page$+")
    public void iNavigateToFP() throws Throwable{
        Thread.sleep(2000);
            Assert.assertFalse("ERROR - APP: Not navigated to forgot password page", (!onPage("forgot_password")));
        }

    @Given("^I navigate directly to checkout sign in page$")
    public void iNavigateDirectlyToCheckoutSignInPage()throws Throwable{
        String url = RunConfig.url;
        Navigate.visit(url + "/account/signin?fromCheckout=fromCheckout");
        log.info("Navigated to checkout sign in page");
    }

    @And("^I sign in with softlocked user$")
    public void iSignInWithSoftlockedUser() throws Throwable{
        createProfile();
        softLockedUser(CommonUtils.getUserDetailsByEmail(user_email),user_email);
        if (onPage("sign_in")) {
            TextBoxes.typeTextbox("sign_in.password", TestUsers.currentPassword);
            TextBoxes.typeTextbox("sign_in.email", TestUsers.currentEmail);
            Clicks.click("sign_in.sign_in_button");
        }else {
            TextBoxes.typeTextbox("checkout.password", TestUsers.currentPassword);
            TextBoxes.typeTextbox("checkout.email", TestUsers.currentEmail);
            Clicks.click("checkout_sign_in.checkout_button");
        }
    }

    public void createProfile() throws Throwable {
       // UserProfile profile = TestUsers.getCustomer(null);
        profile = TestUsers.getCustomer(null);
        UserProfileService.createUserProfile(profile, true);
        user_pwd = profile.getUser().getLoginCredentials().getPassword();
        user_email = profile.getUser().getProfileAddress().getEmail();
    }
}

