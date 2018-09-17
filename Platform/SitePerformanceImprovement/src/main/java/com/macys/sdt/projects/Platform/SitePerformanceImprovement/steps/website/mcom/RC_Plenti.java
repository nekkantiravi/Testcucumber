package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Created by yc04026 on 9/7/2017.
 */
public class RC_Plenti extends StepUtils {

    @And("^I click on Cancel button to collapse the Plenti view$")
    public static void collapse_plenti_view() {
        Clicks.click(Elements.findElement("responsive_checkout.plenti_cancel"));
        Assert.assertTrue("Plenti view is not collapsed", !Elements.elementPresent("responsive_checkout.plenti_cancel"));
    }


    @Then("^I validate Plenti functionality in payment page$")
    public void iValidatePlentiFunctionalityInCheckoutPage() throws Throwable {
        Assert.assertTrue("Plenti Logo is not getting displayed", Elements.elementPresent("responsive_checkout_signed_in.plenti_logo"));
        Assert.assertTrue("Plenti expand button is not getting displayed", Elements.elementPresent("responsive_checkout_signed_in.expand_plenti_section"));
       Assert.assertTrue("Plenti Text is not getting displayed", Elements.findElement(By.xpath("//*[@id='rc-usl-heading']/span[1]")).getText().contentEquals("Have a Plenti #?"));
    }

    @When("^I selected \"([^\"]*)\" radio button$")
    public void i_selected_radio_button(String radioButtonType){
        switch (radioButtonType.toLowerCase()){

            case "plenti":
                Clicks.click("checkout.rc_plenti_radio_button");
                break;
            case "phone":
                Clicks.click("checkout.rc_phone_radioButton");
                break;
            case "macys card number":
                Clicks.click("checkout.rc_cc_radioButton");
                break;
            default:
                System.out.println("Invalid type of choice");
        }
    }

    @And("^I enter \"([^\"]*)\" digits in the \"([^\"]*)\" input field$")
    public void i_enter_invalid_number_of_digit(String numberOfDigit, String fieldType){
        switch (fieldType.toLowerCase()){
            case "plenti":
                TextBoxes.typeTextbox( "responsive_checkout_signed_in.plenti_text_field", "1234567890");
                Clicks.click("responsive_checkout_signed_in.plenti_search_button");
                break;
            case "phone":
                TextBoxes.typeTextbox("responsive_checkout_signed_in.plenti_text_field", "1234567");
                Clicks.click("responsive_checkout_signed_in.plenti_search_button");
                break;

            case "macys card number":
                TextBoxes.typeTextbox("responsive_checkout_signed_in.plenti_text_field", "123456789");
                Clicks.click("responsive_checkout_signed_in.plenti_search_button");
                break;
        }
    }

    @And("^I click on Search button without entering any digits in the \"([^\"]*)\" input field$")
    public void i_enter_no_input(String fieldType){
        switch (fieldType.toLowerCase()){
            case "plenti":
                TextBoxes.typeTextbox( "responsive_checkout_signed_in.plenti_text_field", "");
                Clicks.click("responsive_checkout_signed_in.plenti_search_button");
                break;
            case "phone":
                TextBoxes.typeTextbox("responsive_checkout_signed_in.plenti_text_field", "");
                Clicks.click("responsive_checkout_signed_in.plenti_search_button");
                break;

            case "macys card number":
                TextBoxes.typeTextbox("responsive_checkout_signed_in.plenti_text_field", "");
                Clicks.click("responsive_checkout_signed_in.plenti_search_button");
                break;
        }
    }

    @Then("^I should see error message as \"([^\"]*)\" for empty \"([^\"]*)\"$")
    public void i_should_see_error_message_for_empty(String expectedMsg, String fieldType) throws Throwable {

        switch (fieldType.toLowerCase()) {
            case "plenty number":
                String actualMessage = Elements.getText("checkout.plenti_invalid_plenti_phoneNumber_error");
                Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualMessage);
                break;
            case "phone number":
                String actualErrorMessage = Elements.getText("checkout.plenti_invalid_plenti_phoneNumber_error");
                Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualErrorMessage);
                break;
            case "macys card number":
                String actualCardError = Elements.getText("checkout.plenti_invalid_plenti_phoneNumber_error");
                Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualCardError);
                break;

        }
    }

    @Then("^I should see error message as \"([^\"]*)\" for invalid \"([^\"]*)\"$")
    public void iShouldSeeErrorMessageAs(String expectedMsg, String fieldType) throws Throwable {

        switch (fieldType.toLowerCase()) {
            case "plenty number":
                String actualMessage = Elements.getText("checkout.plenti_invalid_plenti_phoneNumber_error");
                Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualMessage);
                break;
            case "phone number":
                String actualErrorMessage = Elements.getText("checkout.plenti_invalid_plenti_phoneNumber_error");
                Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualErrorMessage);
                break;
            case "macys card number":
                String actualCardError = Elements.getText("checkout.plenti_invalid_plenti_phoneNumber_error");
                Assert.assertEquals("Error message is displayed as all the product attributes are not selected", expectedMsg, actualCardError);
                break;

        }
    }
}
