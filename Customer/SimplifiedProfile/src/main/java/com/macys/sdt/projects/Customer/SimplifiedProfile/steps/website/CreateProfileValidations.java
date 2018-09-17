package com.macys.sdt.projects.Customer.SimplifiedProfile.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class CreateProfileValidations extends StepUtils {

    private String currentEmail = TestUsers.currentEmail;
    private String complexPwd = "Macys12345";

    @When("^I enter an existing email id$")
    public void iEnterExistingEmailId() throws Throwable {
        TextBoxes.typeTextbox("create_profile.email", currentEmail);
        /*
        Below, I input nothing in password just to ensure that the focus moves out of the Email address
        text box so that existing email id error msg gets displayed.
        */
        TextBoxes.typeTextbox("create_profile.password", "");
    }

    @Then("^I should see \"([^\"]*)\" error message$")
    public void existingEmailIdErrMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText(Elements.element("create_profile.error_existing_email"));
        Assert.assertEquals("This error message is not expected in the case of existing email id.", errorMsg, errorMsgFromUI);
    }

    @Then("^I should receive \"([^\"]*)\" a weak pwd error message$")
    public void weakPasswordErrorMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText(Elements.element("create_profile.error_server_side"));
        Assert.assertEquals("This error message is not expected in the case of simple password.", errorMsg, errorMsgFromUI);
    }

    @Then("^I should receive \"([^\"]*)\" error message$")
    public void weakPa2sswordErrorMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText(Elements.element("create_profile.error_server_side"));
        Assert.assertEquals("This error message is not expected in the case of dob less than 13.", errorMsg, errorMsgFromUI);
    }

    @When("^I update weak pwd with complex pwd$")
    public void updateWeakPasswordWithComplex() {
        TextBoxes.typeTextbox("create_profile.password", complexPwd);
        Clicks.click("create_profile.create_profile_button");

    }

    @Then("^I should receive \"([^\"]*)\" same phone number error message$")
    public void samePhoneNumberErrorMsg(String errorMsg) {
        String errorMsgFromUI = Elements.getText(Elements.element("create_profile.error_server_side"));
        Assert.assertEquals("This error message is not expected in the case of all same digits for phone.", errorMsg, errorMsgFromUI);
    }


    @When("^I select sign in link from email error message$")
    public void clickSignInLink() {
        Clicks.click("create_profile.error_existing_email_sign_in");
        Wait.forPageReady();
    }

    @Then("^I should see Sign In Page$")
    public void verifyPresenceOfSignInPage(){
        Assert.assertTrue("ERROR-ENV: Not able to navigate to the sign_in page", Wait.untilElementPresent("sign_in.verify_page"));
    }

    @Then("^I should see inline error messages$")
    public void VerifyErrorMessages(List<String> errorMsgs) throws Throwable {
        List<String> UIErrors = new ArrayList<String>();
        UIErrors.add(Elements.getText(Elements.element("create_profile.error_first_name")));
        UIErrors.add(Elements.getText(Elements.element("create_profile.error_last_name")));
        if(macys())
            UIErrors.add(Elements.getText(Elements.element("create_profile.error_existing_email")));
        else
            UIErrors.add(Elements.getText(Elements.element("create_profile.error_email")));

        UIErrors.add(Elements.getText(Elements.element("create_profile.error_phone_number")));
        int totalNumberOfUIErrors = UIErrors.size();
        UIErrors.retainAll(errorMsgs);
        Assert.assertTrue("ERROR: There is a mismatch in inline error messages shown on UI vs what is expected.", ((UIErrors.size()) == totalNumberOfUIErrors));
    }

    @Then("^I should see invalid data inline error messages$")
    public void VerifyInvalidDataInlineErrorMessages(List<String> errorMsgs) throws Throwable {
        List<String> UIErrors = new ArrayList<String>();
        UIErrors.add(Elements.getText(Elements.element("create_profile.error_first_name")));
        UIErrors.add(Elements.getText(Elements.element("create_profile.error_last_name")));
        if(macys())
            UIErrors.add(Elements.getText(Elements.element("create_profile.error_existing_email")));
        else
            UIErrors.add(Elements.getText(Elements.element("create_profile.error_email")));
        UIErrors.add(Elements.getText(Elements.element("create_profile.error_dob")));
        UIErrors.add(Elements.getText(Elements.element("create_profile.error_phone_number")));
        int totalNumberOfUIErrors = UIErrors.size();
        UIErrors.retainAll(errorMsgs);
        Assert.assertTrue("ERROR: There is a mismatch in inline error messages shown on UI vs what is expected.", ((UIErrors.size()) == totalNumberOfUIErrors));
    }
}