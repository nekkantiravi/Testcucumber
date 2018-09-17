package com.macys.sdt.projects.Customer.RestAssuredCreateProfile.steps.website.mcom;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.assertion.Assertion;
import com.jayway.restassured.response.Response;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Customer.RestAssuredCreateProfile.util.UtilCreateProfileXapi;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.Calendar;

import static com.macys.sdt.framework.interactions.Elements.getText;

/**
 * Created by yh00462 on 10/26/17.
 */
public class CreateProfileXapiServiceSteps extends StepUtils {
    public Response response = null;


    @Then("^I set header to api$")
    public void i_set_header_to_api() throws Throwable {

    }

    @And("^add the payload to create profile post call$")
    public void addThePayloadToCreateProfilePostCall() throws Throwable {

    }

    @Then("^I execute create profile post call to create user profile$")
    public void iExecuteCreateProfilePostCallToCreateUserProfile() throws Throwable {
        UtilCreateProfileXapi utilCreateProfileXapi = new UtilCreateProfileXapi();
        response = utilCreateProfileXapi.postCreateDefaultProfileXapiCall();
    }

    @And("^I should validate the response status code and body message$")
    public void iShouldValidateTheResponseStatusCodeAndBodyMessage() throws Throwable {
        Assert.assertEquals("Failed to validate create profile xapi post call content type", response.getContentType(), "application/json");
        Assert.assertEquals("Failed to validate create profile xapi post call Status code ", response.getStatusCode(), 200);
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg ", response.print().toString().contains("success"));
    }


    @Then("^I execute create profile post call to create existing user profile$")
    public void iExecuteCreateProfilePostCallToCreateExistingUserProfile() throws Throwable {
        UtilCreateProfileXapi utilCreateProfileXapi = new UtilCreateProfileXapi();
        UserProfile user = new UserProfile();
        user = UserProfile.getDefaultProfile();
        UserProfileService.createUserProfile(user);
        response = utilCreateProfileXapi.postCreateProfileXapiCall(utilCreateProfileXapi.getCreateProfileJsonPayLoad(user.getUser()));
    }

    @And("^I should validate the response status code and body message for existing user$")
    public void iShouldValidateTheResponseStatusCodeAndBodyMessageForExistingUser() throws Throwable {
        Assert.assertEquals("Failed to validate create profile xapi post call content type", response.getContentType(), "application/json");
        Assert.assertEquals("Failed to validate create profile xapi post call Status code ", response.getStatusCode(), 200);
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg ", response.print().toString().contains("User Already Exists"));
    }

    @Then("^I execute create profile post call with empty payload$")
    public void iExecuteCreateProfilePostCallWithEmptyPayload() throws Throwable {
        UtilCreateProfileXapi utilCreateProfileXapi = new UtilCreateProfileXapi();
        UserProfile user = new UserProfile();
        user = UserProfile.getDefaultProfile();
        response = utilCreateProfileXapi.postCreateProfileXapiCall(true);

    }

    @And("^I should validate the response status code and body message for empty payload$")
    public void iShouldValidateTheResponseStatusCodeAndBodyMessageForEmptyPayload() throws Throwable {
        Assert.assertEquals("Failed to validate create profile xapi post call content type", response.getContentType(), "application/json");
        Assert.assertEquals("Failed to validate create profile xapi post call Status code ", response.getStatusCode(), 200);
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg Please enter your first name", response.print().toString().contains("Please enter your first name"));
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg Please enter your last name ", response.print().toString().contains("Please enter your last name"));
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg Please enter your email address in this format: jane@company.com. Thank you", response.print().toString().contains("Please enter your email address in this format: jane@company.com. Thank you"));
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg Please enter date of birth", response.print().toString().contains("Please enter date of birth"));
    }

    @Then("^I execute create profile post call with DOB less than (\\d+) years$")
    public void iExecuteCreateProfilePostCallWithDOBLessThanYears(int arg0) throws Throwable {
        UtilCreateProfileXapi utilCreateProfileXapi = new UtilCreateProfileXapi();
        //  logger.info("Create profile with less than 13 years of age!!");
        String targetDOB = "-12-31";//signify Dec 31st.
        int yearsToDeduct = 13;
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        User user = customer.getUser();
        //From current year, if we subtract yearsToDeduct, we will get target year.
        String targetYear = String.valueOf((Calendar.getInstance().get(Calendar.YEAR)) - yearsToDeduct);
        targetDOB = targetYear + targetDOB;
        user.setDateOfBirth(targetDOB);
        response = utilCreateProfileXapi.postCreateProfileXapiCall(utilCreateProfileXapi.getCreateProfileJsonPayLoad(user));
    }

    @And("^I should validate the response status code and body message for DOB less than (\\d+) years$")
    public void iShouldValidateTheResponseStatusCodeAndBodyMessageForDOBLessThanYears(int arg0) throws Throwable {
        Assert.assertEquals("Failed to validate create profile xapi post call content type", response.getContentType(), "application/json");
        Assert.assertEquals("Failed to validate create profile xapi post call Status code ", response.getStatusCode(), 200);
        Assert.assertTrue("Failed to validate create profile xapi post call body Status msg Please enter valid date of birth", response.print().toString().contains("Please enter valid date of birth"));
    }
}
