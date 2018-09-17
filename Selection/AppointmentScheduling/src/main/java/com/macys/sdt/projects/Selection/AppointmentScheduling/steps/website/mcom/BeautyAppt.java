package com.macys.sdt.projects.Selection.AppointmentScheduling.steps.website.mcom;

import com.macys.sdt.framework.utils.StepUtils;
//import com.macys.sdt.projects.Selection.BeautyBox.utils.website.GenericUtilsBeauty;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
//import org.assertj.core.api.SoftAssertions;
import cucumber.api.java.en.When;

/**
 * Created by YH02207 on 12/27/2016.
 */
public class BeautyAppt extends StepUtils {

    @Given("^I am on store locations offering all kinds of appointments$")
    public void i_am_on_store_locations_offering_all_kinds_of_appointments() throws Throwable {
        System.out.println("I am on stores offering appointments page");
    }

    @When("^I select a store offering beauty appointment and click continue$")
    public void i_select_a_store_offering_beauty_appointment_and_click_continue() throws Throwable {
        System.out.println("I select store offering beauty appt");
    }

    @Then("^I should see beauty appointment$")
    public void i_should_see_beauty_appointment() throws Throwable {
        System.out.println("I should see beauty appointment");
    }
    @Given("^I have selected beauty appointment$")
    public void i_have_selected_beauty_appointment() throws Throwable {
        System.out.println("I have selected beauty appointment");
    }
    @When("^I enter my contact information and click Schedule it$")
    public void i_enter_my_contact_information_and_click_Schedule_it() throws Throwable {
        System.out.println("I enter my contact information and click Schedule it");
    }
    @Then("^I should see beauty appointment confirmation$")
    public void i_should_see_beauty_appointment_confirmation() throws Throwable {
        System.out.println("I should see beauty appointment confirmation");
    }
}