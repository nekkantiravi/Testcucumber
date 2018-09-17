package com.macys.sdt.projects.Customer.Regression.steps.website.BCOM;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import java.util.Map;
import java.util.Set;

/**
 * Created by u065629 on 6/6/2017.
 */
public class MyProfile {
    @Then("^I verify the basic attributes of the create profile page$")
    public void iVerifyTheBasicAttributesOfTheCreateProfilePage()  {
        Wait.secondsUntilElementPresent(("create_profile.verify_page"),3);
        Assert.assertTrue("First name field is not displayed", Elements.anyPresent("create_profile.verify_page"));
        Assert.assertTrue("Last name field is not displayed", Elements.anyPresent("create_profile.last_name"));
        Assert.assertTrue("Email field is not displayed", Elements.anyPresent("create_profile.email"));
        Assert.assertTrue("Password name field is not displayed", Elements.anyPresent("create_profile.password"));
        Assert.assertTrue("DOB Month field is not displayed", Elements.anyPresent("create_profile.dob_month_list"));
        Assert.assertTrue("DOB Day field is not displayed", Elements.anyPresent("create_profile.dob_day_list"));
        Assert.assertTrue("DOB Year field is not displayed", Elements.anyPresent("create_profile.dob_year_list"));
        Assert.assertTrue("Get Text alerts checkbox field is displayed, but checked", !Elements.findElement("create_profile.textme_yes").isSelected());
        Assert.assertTrue("Security Question field is not displayed", Elements.anyPresent("create_profile.security_question_list"));
        Assert.assertTrue("Security Answer field is not displayed", Elements.anyPresent("create_profile.security_answer"));
        Assert.assertTrue("Store events checkbox field is not displayed", !Elements.findElement("create_profile.email_store_events").isSelected());
        //Assert.assertTrue("Mens field is displayed, but checked by default", !Elements.findElement("create_profile.email_mens").isSelected());
        //Assert.assertTrue("Kids field is displayed, but checked by default", !Elements.findElement("create_profile.email_kids").isSelected());
        //Assert.assertTrue("Home field is displayed, but checked by default", !Elements.findElement("create_profile.email_home").isSelected());
        Assert.assertTrue("Get Sales checkbox is displayed, but NOT checked", Elements.findElement("create_profile.email_yes").isSelected());
    }

    @And("^I verify error message while creating profile with missing fields$")
    public void iVerifyErrorMessageWhileCreatingProfileWithMissingFields(Map<String,String> error_messages) {
        Wait.secondsUntilElementPresentAndClick("create_profile.create_profile_button", 10);
        Set<String> field_names = error_messages.keySet();
        for (String field:field_names){
            switch (field){
                case "first_name":
                    Assert.assertTrue("Error message is Wrong",Elements.findElement("create_profile.error_first_name").getText().equals(error_messages.get(field)));
                    break;
                case "last_name":
                    Assert.assertTrue("Error message is Wrong",Elements.findElement("create_profile.error_last_name").getText().equals(error_messages.get(field)));
                    break;
                case("email"):
                    Assert.assertTrue("Error message is Wrong",Elements.findElement("create_profile.error_email").getText().equals(error_messages.get(field)));
                    break;
                case("security_question"):
                    Assert.assertTrue("Error message is Wrong",Elements.findElement("create_profile.error_security").getText().equals(error_messages.get(field)));
                    break;
                case("security_answer"):
                    Assert.assertTrue("Error message is Wrong",Elements.findElement("create_profile.error_answer").getText().equals(error_messages.get(field)));
                    break;
                case "dob":
                    Assert.assertTrue("DOB Error message is Wrong",Elements.findElement("create_profile.error_dob").getText().equals(error_messages.get(field)));
                    break;
            }

        }
    }
}
