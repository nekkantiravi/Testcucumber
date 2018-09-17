package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.macys.sdt.framework.interactions.Clicks.hoverForSelection;
import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.typeText;
import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.typeTextNTab;

public class SendSteps extends StepUtils {

    private static final String SHIPPING_FIRST_NAME = "Mike";
    private static final String SHIPPING_LAST_NAME = "Dillon";
    private static final String SHIPPING_ADDRESS = "123 main st";
    private static final String SHIPPING_CITY = "Amherst";
    private static final String SHIPPING_STATE = "FL";
    private static final String SHIPPING_ZIPCODE = "32003";
    private static final String SHIPPING_PHONE = "330-808-2741";
    private static final String SHIPPING_EMAIL = "approve@macys.com";


    private void goToHome() {
        Navigate.visit(RunConfig.url);
    }

    @Then("^I see the Shipping Method Overlay$")
    public void i_see_the_Shipping_Method_Overlay() throws Throwable {
        Wait.untilElementPresent("sends.shipping_method_overlay");
        WebElement overlay;
        Elements.elementShouldBePresent("sends.shipping_method_overlay");
        overlay = Elements.findElement("sends.shipping_method_overlay");
        overlay.findElement(By.className("text-center"));
        Elements.elementShouldBePresent("sends.shipping_method_title");

        String shipmethodtitle = overlay.findElement(By.className("text-center")).getText();
        Assert.assertEquals("Select a Shipping Method", shipmethodtitle);

    }


    @When("^I select Free Shipping$")
    public void iSelectFreeShipping() throws Throwable {
        Wait.untilElementPresent("sends.shipping_method_options");
        Clicks.click("sends.shipping_method_options");

    }

    @And("^Press continue$")
    public void pressContinue() throws Throwable {
        Wait.untilElementPresent("sends.next_button");
        Clicks.click("sends.next_button");

    }

    @Then("^I see the Shipping information Overlay$")
    public void iSeeTheShippingInformationOverlay() throws Throwable {
        Wait.untilElementPresent("sends.shipping_information_overlay");
        Elements.elementShouldBePresent("sends.shipping_information_overlay");

    }

    @When("^I input the Shipping Information$")
    public void iInputTheShippingInformation() throws Throwable {
        Thread.sleep(2000);
        hoverForSelection("sends.shipping_information_overlay");
        Wait.untilElementPresent("sends.first_name_input");
        typeTextNTab("sends.first_name_input", SHIPPING_FIRST_NAME);
        typeTextNTab("sends.last_name_input", SHIPPING_LAST_NAME);
        typeTextNTab("sends.address_input", SHIPPING_ADDRESS);
        typeTextNTab("sends.city_input", SHIPPING_CITY);
        typeTextNTab("sends.state_input", SHIPPING_STATE);
        typeTextNTab("sends.zip_input", SHIPPING_ZIPCODE);
        typeTextNTab("sends.phone_input", SHIPPING_PHONE);
        typeTextNTab("sends.email_input", SHIPPING_EMAIL);
    }

    @Then("^I see the same as Shipping prompt$")
    public void iSeeTheSameAsShippingPrompt() throws Throwable {
        Wait.secondsUntilElementPresent("sends.same_as_checkbox",10);
        Elements.elementShouldBePresent("sends.same_as_checkbox");
    }

    @When("^I uncheck the same as shipping checkbox$")
    public void iUncheckTheSameAsShippingCheckbox() throws Throwable {
        Wait.secondsUntilElementPresent("sends.same_as_checkbox", 5);
        Elements.elementShouldBePresent("sends.same_as_checkbox");
        Clicks.click("sends.same_as_checkbox");
    }

    @And("^I press next steps$")
    public void iPressNextSteps() throws Throwable {
        Elements.elementShouldBePresent("sends.next_button");
        Clicks.click("sends.next_button");
    }

    @Then("^I can see the Input Billing Information screen$")
    public void iCanSeeTheInputBillingInformationScreen() throws Throwable {
        Wait.untilElementPresent("sends.billing_info_overlay");
        Elements.elementShouldBePresent("sends.billing_info_overlay");
    }


    @When("^I Input \"([^\"]*)\" in the First Name Field$")
    public void iInputInTheFirstNameField(String FirstName) throws Throwable {
        Wait.untilElementPresent("sends.first_name_input");
        typeTextNTab("sends.first_name_input", FirstName);
    }

    @When("^I Input \"([^\"]*)\" in the Last Name Field$")
    public void iInputInTheLastNameField(String LastName) throws Throwable {
        Wait.untilElementPresent("sends.last_name_input");
        TextBoxes.typeTextNEnter("sends.last_name_input", LastName);

    }

    @When("^I Input \"([^\"]*)\" in the Address Field$")
    public void iInputInTheAddressField(String Address) throws Throwable {
        Wait.untilElementPresent("sends.address_input");
        typeTextNTab("sends.address_input", Address);

    }


    @When("^I Input \"([^\"]*)\" in the City Field$")
    public void iInputInTheCityField(String City) throws Throwable {
        Wait.untilElementPresent("sends.city_input");
        typeTextNTab("sends.city_input", City);
    }


    @When("^I Input \"([^\"]*)\" in the State Field$")
    public void iInputInTheStateField(String State) throws Throwable {
        Wait.untilElementPresent("sends.state_input");
        typeTextNTab("sends.state_input", State);

    }


    @When("^I Input \"([^\"]*)\" in the Zip Code Field$")
    public void iInputInTheZipCodeField(String ZipCode) throws Throwable {
        Wait.untilElementPresent("sends.zip_input");
        typeTextNTab("sends.zip_input", ZipCode);

    }

    @When("^I Input \"([^\"]*)\" in the Phone Number Field$")
    public void iInputInThePhoneNumberField(String PhoneNumber) throws Throwable {
        hoverForSelection("sends.email_input");
        Wait.untilElementPresent("sends.phone_input");
        typeTextNTab("sends.phone_input", PhoneNumber);

    }

    @When("^I input Billing Information$")
    public void  iInputBillingInformation() throws Throwable {
        Wait.untilElementPresent("sends.first_name_input");
        typeTextNTab("sends.first_name_input", "Josh");
        typeTextNTab("sends.last_name_input", "Norman");
        typeTextNTab("sends.address_input", "456 my st");
        typeTextNTab("sends.city_input", "New York");
        typeTextNTab("sends.state_input", "NY");
        typeTextNTab("sends.zip_input", "12345");
        typeTextNTab("sends.phone_input", "2222221234");
        typeTextNTab("sends.email_input", " approve@macys.com");
    }

    @Given("^I can see the Standard option selected$")
    public void iCanSeeTheStandardOptionSelected() throws Throwable {
        Wait.untilElementPresent("sends.shipping_method_radio_buttons");
        Assert.assertEquals("Radio button is not selected", (Elements.findElement("sends.shipping_method_radio_buttons")).getAttribute("checked"), "true");

    }

    @When("^I select Premium option$")
    public void iSelectPremiumOption() throws Throwable {
        List<WebElement> optionList = Elements.findElements("sends.shipping_method_options");
        WebElement option = optionList.get(1);
        option.click();
    }


    @And("^I close Shipping Method overlay$")
    public void iCloseShippingMethodOverlay() throws Throwable {
        Wait.untilElementPresent("sends.shipping_information_overlay");
        Clicks.click("home.overlay_close");
    }

    @Then("^I can see the Premium option selected$")
    public void iCanSeeThePremiumOptionSelected() throws Throwable {
        Wait.untilElementPresent("sends.shipping_method_radio_buttons");
        List<WebElement> optionList = Elements.findElements("sends.shipping_method_radio_buttons");
        WebElement premiumOption = optionList.get(1);
        Assert.assertEquals("Radio button is not selected", premiumOption.getAttribute("checked"), "true");

    }

    @When("^I select Express option$")
    public void iSelectExpressOption() throws Throwable {
        List<WebElement> optionList = Elements.findElements("sends.shipping_method_options");
        WebElement option = optionList.get(2);
        option.click();
    }

    @Then("^I can see the Express option selected$")
    public void iCanSeeTheExpressOptionSelected() throws Throwable {
        Wait.untilElementPresent("sends.express_radio_button");
        List<WebElement> optionList = Elements.findElements("sends.shipping_method_radio_buttons");
        WebElement expressOption = optionList.get(2);
        Assert.assertEquals("Radio button is not selected", expressOption.getAttribute("checked"), "true");

    }

    @When("^I input \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iInput(String first_name_input, String last_name_input, String address_input, String shipping_address_line2, String city_input, String state_input, String zip_input, String phone_input, String email_input) throws Throwable {
        if (Elements.elementPresent("sends.first_name_input")) {
            TextBoxes.typeTextNEnter("sends.first_name_input", first_name_input);

        }
        if (Elements.elementPresent("sends.last_name_input")) {
            TextBoxes.typeTextNEnter("sends.last_name_input", last_name_input);
        }
        if (Elements.elementPresent("sends.address_input")) {
            TextBoxes.typeTextNEnter("sends.address_input", address_input);
        }
        if (Elements.elementPresent("sends.city_input")) {
            TextBoxes.typeTextNEnter("sends.city_input", city_input);
        }
        if (Elements.elementPresent("sends.state_input")) {
            TextBoxes.typeTextNEnter("sends.state_input", state_input);
        }
        if (Elements.elementPresent("sends.zip_input")) {
            TextBoxes.typeTextNEnter("sends.zip_input", zip_input);
        }
        if (Elements.elementPresent("sends.phone_input")) {
            TextBoxes.typeTextNEnter("sends.phone_input", phone_input);
        }
        if (Elements.elementPresent("sends.email_input")) {
            TextBoxes.typeTextNEnter("sends.email_input", email_input);
        }
    }


    @And("^I click on Next Step button$")
    public void iClickOnNextStepButton() throws Throwable {
        Wait.untilElementPresent("sends.next_button");
        Elements.elementShouldBePresent("sends.next_button");
        Clicks.click("sends.next_button");
    }

    @Then("^the Enter Billing Address overlay should be displayed$")
    public void theEnterBillingAddressOverlayShouldBeDisplayed() throws Throwable {
        Elements.elementShouldBePresent("sends.billing_info_overlay");
        Elements.elementShouldBePresent("sends.billing_address_title");

    }


    @When("^I Input \"([^\"]*)\" in the Email Field$")
    public void iInputInTheEmailField(String Email) throws Throwable {
        hoverForSelection("sends.email_input");
        Wait.untilElementPresent("sends.email_input");
        typeTextNTab("sends.email_input", Email);

    }

    @Then("^I see the Order Review Overlay$")
    public void iSeeTheOrderReviewOverlay() throws Throwable {
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("sends.order_review_overlay", 10);
        Elements.elementShouldBePresent("sends.order_review_overlay");
    }


    /**
     * @Then("^I can see the Shipping Address overlay$")
     * public void iCanSeeTheShippingAddressOverlay() throws Throwable {
     * Wait.untilElementPresent("sends.shipping_information_title");
     * Elements.elementShouldBePresent("sends.shipping_information_title");
     * Elements.elementShouldBePresent("sends.shipping_information_title");
     * <p>
     * String shipaddresstitle = Elements.findElement("sends.shipping_information_title").getText();
     * Assert.assertEquals("Input Shipping Address", shipaddresstitle);
     * }
     */

    @When("^I tap on the Complete button$")
    public void iTapOnTheCompleteButton() throws Throwable {
        Elements.elementShouldBePresent("sends.next_button");
        Clicks.click("sends.next_button");
    }

    @When("^I tap edit next to Shipping Method$")
    public void iTapEditNextToShippingMethod() throws Throwable {
        Elements.elementShouldBePresent("sends.shipping_method_edit_button");
        Clicks.click("sends.shipping_method_edit_button");
    }

    @When("^I tap edit next to Shipping Address$")
    public void iTapEditNextToShippingAddress() throws Throwable {
        Elements.elementShouldBePresent("sends.shipping_address_edit_button");
        Clicks.click("sends.shipping_address_edit_button");
    }

    @When("^I tap edit next to Billing Address$")
    public void iTapEditNextToBillingAddress() throws Throwable {
        Elements.elementShouldBePresent("sends.billing_address_edit_button");
        Clicks.click("sends.billing_address_edit_button");
    }

    @When("^I input \"([^\"]*)\" in the \"([^\"]*)\"$")
    public void iInputInThe(String Input, String BillingField) throws Throwable {
        Thread.sleep(2000);
            switch (BillingField) {
            case "FirstName":
                Wait.untilElementPresent("sends.first_name_input");
                typeText("sends.first_name_input", Input);
                break;

            case "City":
                Wait.untilElementPresent("sends.city_input");
                typeText("sends.city_input", Input);
                break;

            case "LastName":
                Wait.untilElementPresent("sends.last_name_input");
                typeText("sends.last_name_input", Input);
                break;

            case "State":
                Wait.untilElementPresent("sends.state_input");
                typeText("sends.state_input", Input);
                break;

            case "Zip":
                Wait.untilElementPresent("sends.zip_input");
                typeText("sends.zip_input", Input);
                break;

            case "PhoneNumber":
                hoverForSelection("sends.phone_input");
                Wait.untilElementPresent("sends.phone_input");
                typeText("sends.phone_input", Input);
                break;
        }
    }


    @Then("^I verify special character error reads \"([^\"]*)\"$")
    public void iVerifySpecialCharacterErrorReads(String SpecialCharacerror) throws Throwable {
        Wait.untilElementPresent("sends.error_message");
        Elements.elementShouldBePresent("sends.error_message");

        String specialChareerror_msg = Elements.findElement("sends.error_message").getText();
        Assert.assertEquals(SpecialCharacerror, specialChareerror_msg);

    }


    @When("^I input \"([^\"]*)\" in the \"([^\"]*)\" for invalid format$")
    public void iInputInTheForInvalidFormat(String Input, String BillingField) throws Throwable {
        Thread.sleep(2000);
        switch (BillingField) {
            case "FirstName":
                Wait.untilElementPresent("sends.first_name_input");
                typeTextNTab("sends.first_name_input", Input);
                break;

            case "City":
                Wait.untilElementPresent("sends.city_input");
                typeTextNTab("sends.city_input", Input);
                break;

            case "LastName":
                Wait.untilElementPresent("sends.last_name_input");
                typeTextNTab("sends.last_name_input", Input);
                break;

            case "State":
                Wait.untilElementPresent("sends.state_input");
                typeTextNTab("sends.state_input", Input);
                break;

            case "ZipCode":
                Wait.untilElementPresent("sends.zip_input");
                typeTextNTab("sends.zip_input", Input);
                break;

            case "PhoneNumber":
                hoverForSelection("sends.phone_input");
                Wait.untilElementPresent("sends.phone_input");
                typeTextNTab("sends.phone_input", Input);
                break;

            case "Email":
                hoverForSelection("sends.email_input");
                Wait.untilElementPresent("sends.email_input");
                Elements.findElement("sends.email_input").clear();
                typeTextNTab("sends.email_input", Input);
                String actualInput = Elements.findElement("sends.email_input").getAttribute("value");
                Assert.assertTrue("Input email text is not properly displayed.Unexpected text:" + actualInput,actualInput.equals(Input));
                break;

            case "Address1":
                Wait.untilElementPresent("sends.address_input");
                typeTextNTab("sends.address_input", Input);
                break;

        }
    }

    @Then("^I verify invalid format error reads \"([^\"]*)\"$")
    public void iVerifyInvalidFormatErrorReads(String InvalidError) throws Throwable {
        Thread.sleep(2000);
        switch (InvalidError) {
            case "Invalid Address line 1.":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String Addinvalidformatmsg = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(InvalidError, Addinvalidformatmsg);
                break;

            case "Invalid State.":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String stateinvalidformatmsg = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(InvalidError, stateinvalidformatmsg);
                break;
            case "Invalid Zip Code.":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String zipinvalidformatmsg = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(InvalidError, zipinvalidformatmsg);
                break;

            case "Invalid Phone Number.":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String phoneinvalidformatmsg = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(InvalidError, phoneinvalidformatmsg);
                break;
            case "Invalid Email address (optional).":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String emailinvalidformatmsg = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(InvalidError, emailinvalidformatmsg);
                break;

            case "Pass":
                Assert.assertTrue("The Error Message Is Displayed", !Elements.elementPresent("sends.error_message"));
        }
    }

    @Then("^I can see the Take item information$")
    public void iCanSeeTheTakeItemInformation() throws Throwable {

    }

    @And("^I can't see the truck icon displayed in item details$")
    public void iCanTSeeTheTruckIconDisplayedInItemDetails() throws Throwable {

    }

    @When("^I input \"([^\"]*)\" in the \"([^\"]*)\" for too many or few characters$")
    public void iInputInTheForTooManyOrFewCharacters(String Input, String BillingField) throws Throwable {
        Thread.sleep(2000);
        switch (BillingField) {
            case "FirstNameLong":
                Wait.untilElementPresent("sends.first_name_input");
                typeText("sends.first_name_input", Input);
                break;
            case "CityShort":
                Wait.untilElementPresent("sends.city_input");
                typeTextNTab("sends.city_input", Input);
                break;
            case "CityLong":
                Wait.untilElementPresent("sends.city_input");
                typeText("sends.city_input", Input);
                break;
            case "LastNameLong":
                Wait.untilElementPresent("sends.last_name_input");
                typeText("sends.last_name_input", Input);

                break;
            case "StateShort":
                Wait.untilElementPresent("sends.state_input");
                typeTextNTab("sends.state_input", Input);

                break;
            case "StateLong":
                Wait.untilElementPresent("sends.state_input");
                typeText("sends.state_input", Input);

                break;
            case "ZipShort":
                Wait.untilElementPresent("sends.zip_input");
                typeTextNTab("sends.zip_input", Input);

                break;
            case "ZipLong":
                Wait.untilElementPresent("sends.zip_input");
                typeText("sends.zip_input", Input);

                break;
            case "PhoneNumberShort":
                hoverForSelection("sends.email_input");
                Wait.untilElementPresent("sends.phone_input");
                typeTextNTab("sends.phone_input", Input);
                break;
            case "PhoneNumberLong":
                hoverForSelection("sends.email_input");
                Wait.untilElementPresent("sends.phone_input");
                typeText("sends.phone_input", Input);
                break;
            case "EmailShort":
                hoverForSelection("sends.email_input");
                Wait.untilElementPresent("sends.email_input");
                typeTextNTab("sends.email_input", Input);
                break;
            case "EmailLong":
                hoverForSelection("sends.email_input");
                Wait.untilElementPresent("sends.email_input");
                typeText("sends.email_input", Input);
                break;
            case "Address1Short":
                Wait.untilElementPresent("sends.address_input");
                typeTextNTab("sends.address_input", Input);
                break;
            case "Address1Long":
                Wait.untilElementPresent("sends.address_input");
                typeText("sends.address_input", Input);
                break;
        }

    }

    @Then("^I verify too many or few error reads \"([^\"]*)\"$")
    public void iVerifyTooManyOrFewErrorReads(String ErrorMsg) throws Throwable {
        Thread.sleep(2000);
        switch (ErrorMsg) {

            case "Minimum length required is 3 ":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String Threeallowed = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(ErrorMsg, Threeallowed);
                break;

            case "Minimum length required is 2 ":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String Mintwo = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(ErrorMsg, Mintwo);
                break;

            case "Minimum length required is 10 ":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String MinTen = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(ErrorMsg, MinTen);
                break;

            case "Minimum length required is 5 ":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String MinFive = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(ErrorMsg, MinFive);
                break;
            case "Too many characters.":
                Wait.untilElementNotPresent("sends.error_message");
                Assert.assertTrue("Error Message Displayed", !Elements.elementPresent("sends.error_message"));


                break;
            case "Minimum length required is 6 ":

                Wait.untilElementPresent("sends.error_message");
                Elements.elementShouldBePresent("sends.error_message");

                String MinSix = Elements.findElement("sends.error_message").getText();
                Assert.assertEquals(ErrorMsg, MinSix);
                break;

        }
    }

    @When("^I press Next to confirm same as shipping address$")
    public void iPressNextToConfirmSameAsShippingAddress() throws Throwable {
        Elements.elementShouldBePresent("sends.next_button");
        Clicks.click("sends.next_button");

    }

    @And("^I can see the selected Shipping Address in the Review overlay$")
    public void iCanSeeTheSelectedShippingAddressInTheReviewOverlay() throws Throwable {
        //checking Shipping Address name
        String confirmShipAddrName = Elements.findElement("sends.review_shipping_address_name").getText();
        Assert.assertEquals("Customer name is incorrect in the Shipping Address section of the Confirm Order overlay",
                SHIPPING_FIRST_NAME + " " + SHIPPING_LAST_NAME, confirmShipAddrName);

        //checking Shipping Address street
        String confirmShipAddrStreet = Elements.findElement("sends.review_shipping_address_street").getText();
        Assert.assertEquals("Customer address line 1 is incorrect in the Shipping Address section of the Confirm Order overlay",
                SHIPPING_ADDRESS, confirmShipAddrStreet);

        //checking Shipping Address city and state
        String confirmShipAddrCityState = Elements.findElement("sends.review_shipping_address_city_state").getText();
        Assert.assertEquals("Customer City and State are incorrect in the Shipping Address section of the Confirm Order overlay",
                SHIPPING_CITY + " " + SHIPPING_STATE, confirmShipAddrCityState);

        //checking Shipping Address zipcode
        String confirmShipAddrZip = Elements.findElement("sends.review_shipping_address_zip").getText();
        Assert.assertEquals("Customer Zip Code is incorrect in the Shipping address section of the Confirm Order overlay",
                SHIPPING_ZIPCODE, confirmShipAddrZip);

        //checking Shipping Address phone
        String confirmShipAddrPhone = Elements.findElement("sends.review_shipping_address_phone").getText();
        Assert.assertEquals("Customer Phone number is incorrect in the Shipping address section of the Confirm Order overlay",
                SHIPPING_PHONE, confirmShipAddrPhone);

    }

    @And("^I can see the Shipping Method is set to \"([^\"]*)\" in the Review overlay$")
    public void iCanSeeTheShippingMethodIsSetToInTheReviewOverlay(String shippingMethod) throws Throwable {
        Thread.sleep(2000);
        switch (shippingMethod) {
            case "Standard":
                //checking Shipping Method first line
                String confirmShipStandardFirst = Elements.findElement("sends.review_shipping_method_first_line").getText();
                Assert.assertEquals("Customer Shipping method first line is incorrect in the Confirm Order overlay",
                        "STANDARD", confirmShipStandardFirst);
                //checking Shipping Method second line
                String confirmShipStandardSecond = Elements.findElement("sends.review_shipping_method_second_line").getText();
                Assert.assertEquals("Customer Shipping method second line is incorrect in the Confirm Order overlay",
                        "Transit time: 3-6 business days", confirmShipStandardSecond);
                break;

            case "Express":
                //checking Shipping Method first line
                String confirmShipExpressFirst = Elements.findElement("sends.review_shipping_method_first_line").getText();
                Assert.assertEquals("Customer Shipping method first line is incorrect in the Confirm Order overlay",
                        "EXPRESS", confirmShipExpressFirst);

                //checking Shipping Method second line
                String confirmShipExpressSecond = Elements.findElement("sends.review_shipping_method_second_line").getText();
                Assert.assertEquals("Customer Shipping method second line is incorrect in the Confirm Order overlay",
                        "Transit time: 3-6 business days", confirmShipExpressSecond);
                break;

            case "Premium":
                //checking Shipping Method first line
                String confirmShipPremiumFirst = Elements.findElement("sends.review_shipping_method_first_line").getText();
                Assert.assertEquals("Customer Shipping method first line is incorrect in the Confirm Order overlay",
                        "PREMIUM", confirmShipPremiumFirst);

                //checking Shipping Method second line
                String confirmShipPremiumSecond = Elements.findElement("sends.review_shipping_method_second_line").getText();
                Assert.assertEquals("Customer Shipping method second line is incorrect in the Confirm Order overlay",
                        "Transit time: 3-6 business days", confirmShipPremiumSecond);
                break;


        }
    }


    @And("^I can see the selected Billing Address in the Review overlay$")
    public void iCanSeeTheSelectedBillingAddressInTheReviewOverlay() throws Throwable {
        //checking Billing Address name
        String confirmBillAddrName = Elements.findElement("sends.review_billing_address_name").getText();
        Assert.assertEquals("Customer name is incorrect in the Billing Address section of the Confirm Order overlay",
                SHIPPING_FIRST_NAME + " " + SHIPPING_LAST_NAME, confirmBillAddrName);

        //checking Billing Address street
        String confirmBillAddrStreet = Elements.findElement("sends.review_billing_address_street").getText();
        Assert.assertEquals("Customer address line 1 is incorrect in the Billing Address section of the Confirm Order overlay",
                SHIPPING_ADDRESS, confirmBillAddrStreet);

        //checking Billing Address city and state
        String confirmBillAddrCityState = Elements.findElement("sends.review_billing_address_city_state").getText();
        Assert.assertEquals("Customer City and State are incorrect in the Billing Address section of the Confirm Order overlay",
                SHIPPING_CITY + " " + SHIPPING_STATE, confirmBillAddrCityState);

        //checking Billing Address zipcode
        String confirmBillAddrZip = Elements.findElement("sends.review_billing_address_zip").getText();
        Assert.assertEquals("Customer Zip Code is incorrect in the Billing address section of the Confirm Order overlay",
                SHIPPING_ZIPCODE, confirmBillAddrZip);

        //checking Billing Address phone
        String confirmBillAddrPhone = Elements.findElement("sends.review_billing_address_phone").getText();
        Assert.assertEquals("Customer Phone number is incorrect in the Billing address section of the Confirm Order overlay",
                SHIPPING_PHONE, confirmBillAddrPhone);

    }


    @When("^I update the Shipping Address$")
    public void iUpdateTheShippingAddress() throws Throwable {
        typeTextNTab("sends.address_input", "500 West street");
    }

    @And("^I can see the updated Shipping Address in the Review overlay$")
    public void iCanSeeTheUpdatedShippingAddressInTheReviewOverlay() throws Throwable {
        //checking Shipping Address in the confirmation overlay
        String confirmShipAddrStreet = Elements.findElement("sends.review_shipping_address_street").getText();
        Assert.assertEquals("Customer address line 1 is incorrect in the Billing Address section of the Confirm Order overlay",
                "500 West street", confirmShipAddrStreet);
    }

    @When("^I update the Billing Address$")
    public void iUpdateTheBillingAddress() throws Throwable {
        typeTextNTab("sends.city_input", "New York");
    }

    @And("^I can see the updated Billing Address in the Review overlay$")
    public void iCanSeeTheUpdatedBillingAddressInTheReviewOverlay() throws Throwable {
        //checking Billing Address City in the confirmation overlay
        String confirmBillAddrCity = Elements.findElement("sends.review_billing_address_city_state").getText();
        Assert.assertEquals("Customer City is incorrect in the Billing Address section of the Confirm Order overlay",
                "New York NY", confirmBillAddrCity);
    }

    @And("^I can see only (\\d+) Shipping Methods$")
    public void iCanSeeOnlyShippingMethods(int expected_number) throws Throwable {
        WebElement overlay;
        List<WebElement> list;
        int counter = 0;
        overlay = Elements.findElement("sends.shipping_method_overlay");
        list = overlay.findElements(By.className("row"));
        for (WebElement element : list) {
            if (element.getAttribute("data-feetype") != null) {
                counter++;
            }
        }
        Assert.assertEquals("Number of options is different ", counter, expected_number);
    }

    @And("^I can see the Shipping Methods are in the correct order$")
    public void iCanSeeTheShippingMethodsAreInTheCorrectOrder() throws Throwable {
        WebElement overlay;
        List<WebElement> list;
        List<String> option_list = Arrays.asList("STANDARD", "PREMIUM", "EXPRESS");
        List<String> found_list = new ArrayList<>();
        String method_text;
        overlay = Elements.findElement("sends.shipping_method_overlay");
        list = overlay.findElements(By.className("row"));

        for (WebElement element : list) {
            if (element.getAttribute("data-feetype") != null) {
                method_text = element.findElement(By.className("fee-description")).getText();
                found_list.add(method_text.split("\nTransit")[0]);
            }
        }
        Assert.assertEquals(option_list, found_list);
    }

    @When("^I input three characters into the Phone Number field$")
    public void iInputThreeCharactersIntoThePhoneNumberField() throws Throwable {
        Wait.untilElementPresent("sends.phone_input");
        typeTextNTab("sends.phone_input", "3308");


    }

    @Then("^I can see the first dash$")
    public void iCanSeeTheFirstDash() throws Throwable {
        Wait.untilElementPresent("sends.phone_input");

        String dashOne = Elements.findElement("sends.phone_input").getAttribute("value");
        Assert.assertEquals("330-8", dashOne);
    }

    @When("^I input six characters into the Phone Number field$")
    public void iInputSixCharactersIntoThePhoneNumberField() throws Throwable {
        Wait.untilElementPresent("sends.phone_input");
        typeTextNTab("sends.phone_input", "3308082");
    }

    @Then("^I can see the second dash$")
    public void iCanSeeTheSecondDash() throws Throwable {
        Wait.untilElementPresent("sends.phone_input");

        String dashTwo = Elements.findElement("sends.phone_input").getAttribute("value");
        Assert.assertEquals("330-808-2", dashTwo);
    }

    @When("^I checkout an item for a take sale$")
    public void iCheckoutAnItemForATakeSale() throws Throwable {
        CheckoutSteps checkoutSteps = new CheckoutSteps();
        checkoutSteps.iAddAnItemToTheCheckoutBagForATakeSale();
        checkoutSteps.i_see_the_CRL_Overlay();
        checkoutSteps.i_close_the_CRL_Overlay();
        checkoutSteps.i_can_see_the_added_to_bag_toast_message();
        checkoutSteps.the_toast_message_fades_away_after_seconds(3);
        Wait.secondsUntilElementPresent("home.progressBar_bag", 7);
        checkoutSteps.iClickOnTheBagIcon();
        checkoutSteps.i_should_be_able_to_see_the_bag_view();
        checkoutSteps.i_can_see_the_Item_Information_on_Sales_Trans();
        Thread.sleep(3000);
        checkoutSteps.i_press_the_checkout_button();
        Thread.sleep(1500);
        if(Elements.elementPresent("bag_screen.bag_fee_overlay")){
            TenderingSteps tenderingSteps = new TenderingSteps();
            tenderingSteps.iCanSeeTheBagFeeOverlay();
            checkoutSteps.iAddBags("2");
        }

    }

    @When("^I checkout an item for a send sale$")
    public void iCheckoutAnItemForASendSale() throws Throwable {
        CheckoutSteps checkoutSteps = new CheckoutSteps();
        checkoutSteps.i_add_an_item_to_the_Checkout_bag();
        checkoutSteps.i_can_see_the_added_to_bag_toast_message();
        checkoutSteps.the_toast_message_fades_away_after_seconds(3);
        Wait.secondsUntilElementPresent("home.progressBar_bag", 10);
        checkoutSteps.iClickOnTheBagIcon();
        checkoutSteps.iClickOnTheBagIcon();
        checkoutSteps.i_should_be_able_to_see_the_bag_view();
        Thread.sleep(3000);
        Wait.secondsUntilElementPresent("bag_screen.checkout_button", 10);
        checkoutSteps.i_press_the_checkout_button();
        i_see_the_Shipping_Method_Overlay();
        iClickOnNextStepButton();
        iSeeTheShippingInformationOverlay();
        iInputTheShippingInformation();
        iClickOnNextStepButton();
        iSeeTheSameAsShippingPrompt();
        iClickOnNextStepButton();
        iSeeTheOrderReviewOverlay();
        iClickOnNextStepButton();
    }


    @When("^I checkout an item for a send sale with a shipping fee$")
    public void iCheckoutAnItemForASendSaleWithAShippingFee() throws Throwable {
        CheckoutSteps checkoutSteps = new CheckoutSteps();
        checkoutSteps.i_add_an_item_to_the_Checkout_bag();
        checkoutSteps.i_can_see_the_added_to_bag_toast_message();
        checkoutSteps.the_toast_message_fades_away_after_seconds(2);
        Wait.secondsUntilElementPresent("home.progressBar_bag", 10);
        checkoutSteps.iClickOnTheBagIcon();
        Thread.sleep(3000);
        Wait.secondsUntilElementPresent("bag_screen.checkout_button", 10);
        checkoutSteps.i_press_the_checkout_button();
        i_see_the_Shipping_Method_Overlay();
        iSelectExpressOption();
        iClickOnNextStepButton();
        iSeeTheShippingInformationOverlay();
        iInputTheShippingInformation();
        iClickOnNextStepButton();
        iCanSeeTheInputBillingInformationScreen();
        iClickOnNextStepButton();
        iSeeTheOrderReviewOverlay();
        iClickOnNextStepButton();
    }

    @Then("^I see the empty information error messages$")
    public void iSeeTheEmptyInformationErrorMessages() throws Throwable {

        if (Elements.elementPresent("sends.error_message")) {
            int no_of_items = Elements.findElements("sends.error_message").size();
            Assert.assertEquals(7, no_of_items);


            Wait.untilElementPresent("sends.error_message");
            List<WebElement> items = Elements.findElements("sends.error_message");
            int totalsize = items.size();

            List<String> error_messages = new ArrayList<String>();
            error_messages.add("Please fill in First Name");
            error_messages.add("Please fill in Last Name");
            error_messages.add("Please fill in Address line 1");
            error_messages.add("Please fill in City");
            error_messages.add("Please fill in State");
            error_messages.add("Please fill in Zip Code");
            error_messages.add("Please fill in Phone number");

            for (int x = 0; x < totalsize; x++) {
                String error = items.get(x).getText();
                Assert.assertEquals(error, (error_messages.get(x)));
            }


        }
    }

    @Then("^I close the billing screen$")
    public void iCloseTheBillingScreen() throws Throwable {
        Wait.untilElementPresent("sends.billing_info_overlay");
        Clicks.click("home.overlay_close");
    }

    @And("^I close the Shipping information Overlay$")
    public void iCloseTheShippingInformattionOverlay() throws Throwable {
        Wait.untilElementPresent("sends.shipping_information_overlay");
        Clicks.click("home.overlay_close");
    }

    @When("^I press the Back button$")
    public void iPressTheBackButton() throws Throwable {
        Elements.elementShouldBePresent("sends.back_button");
        Clicks.click("sends.back_button");


    }

    @Then("^I see the pre-populated Shipping information screen$")
    public void iSeeThePrePopulatedShippingInformationScreen() throws Throwable {
        Wait.untilElementPresent("sends.first_name_input");

        String firstname = Elements.findElement("sends.first_name_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_FIRST_NAME, firstname);

        String lastname = Elements.findElement("sends.last_name_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_LAST_NAME, lastname);

        String address = Elements.findElement("sends.address_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_ADDRESS, address);

        String city = Elements.findElement("sends.city_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_CITY, city);

        String state = Elements.findElement("sends.state_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_STATE, state);

        String zip = Elements.findElement("sends.zip_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_ZIPCODE, zip);

        String phone = Elements.findElement("sends.phone_input").getAttribute("value");
        Assert.assertEquals(SHIPPING_PHONE, phone);

    }

    @Then("^I see the pre-populated Billing information screen$")
    public void iSeeThePrePopulatedBillingInformationScreen() throws Throwable {
        Wait.untilElementPresent("sends.first_name_input");

        String firstname = Elements.findElement("sends.first_name_input").getAttribute("value");
        Assert.assertEquals("Josh", firstname);

        String lastname = Elements.findElement("sends.last_name_input").getAttribute("value");
        Assert.assertEquals("Norman", lastname);

        String address = Elements.findElement("sends.address_input").getAttribute("value");
        Assert.assertEquals("456 my st", address);

        String city = Elements.findElement("sends.city_input").getAttribute("value");
        Assert.assertEquals("New York", city);

        String state = Elements.findElement("sends.state_input").getAttribute("value");
        Assert.assertEquals("NY", state);

        String zip = Elements.findElement("sends.zip_input").getAttribute("value");
        Assert.assertEquals("12345", zip);

        String phone = Elements.findElement("sends.phone_input").getAttribute("value");
        Assert.assertEquals("222-222-1234", phone);

    }


    @Then("^I verify special character error displays with the Invalid \"([^\"]*)\"$")
    public void iVerifySpecialCharacterErrorDisplaysWithTheInvalid(String Input) throws Throwable {
        Wait.secondsUntilElementPresent("sends.error_message", 5);
        Elements.elementShouldBePresent("sends.error_message");

        String Errormessage = Elements.findElement("sends.error_message").getText();
        System.out.println(Errormessage);
        Assert.assertEquals(Errormessage, "Sorry,"+" "+ " "+Input+" "+" "+"is not allowed here");

    }

    @And("^I see the invalid char text in red$")
    public void iSeeTheInvalidCharTextInRed() throws Throwable {
        String textColor = Elements.findElement("sends.error_message").getCssValue("color");
        Assert.assertEquals("Error text color is not red", "rgba(233, 28, 44, 1)", textColor);

        String text = Elements.findElement("sends.error_message").getText();
        Assert.assertEquals("Error message is not Invalid Char", "Invalid Char", text);
    }

    @And("^I checkout an item and arrive on the \"([^\"]*)\" overlay$")
    public void iCheckoutAnItemAndArriveOnTheOverlay(String page) throws Throwable {
        CheckoutSteps checkoutSteps = new CheckoutSteps();

        switch (page) {
            case "Order Review":
                checkoutSteps.i_add_an_item_to_the_Checkout_bag();
                checkoutSteps.i_can_see_the_added_to_bag_toast_message();
                checkoutSteps.the_toast_message_fades_away_after_seconds(2);
                checkoutSteps.iClickOnTheBagIcon();
                checkoutSteps.i_press_the_checkout_button();
                i_see_the_Shipping_Method_Overlay();
                iCanSeeTheStandardOptionSelected();
                iClickOnNextStepButton();
                iSeeTheShippingInformationOverlay();
                iInputTheShippingInformation();
                iClickOnNextStepButton();
                iSeeTheSameAsShippingPrompt();
                iClickOnNextStepButton();
                iSeeTheOrderReviewOverlay();
                break;

            case "Shipping Information":
                checkoutSteps.i_add_an_item_to_the_Checkout_bag();
                checkoutSteps.i_can_see_the_added_to_bag_toast_message();
                checkoutSteps.the_toast_message_fades_away_after_seconds(2);
                checkoutSteps.iClickOnTheBagIcon();
                checkoutSteps.i_press_the_checkout_button();
                i_see_the_Shipping_Method_Overlay();
                iCanSeeTheStandardOptionSelected();
                iClickOnNextStepButton();
                iSeeTheShippingInformationOverlay();
                break;

        }
    }

    @When("^I press checkout and fill out shipping information to get to posttender screen$")
    public void iPressCheckoutAndFillOutShippingInformationToGetToPosttenderScreen() throws Throwable {
        CheckoutSteps checkoutSteps = new CheckoutSteps();
        checkoutSteps.i_press_the_checkout_button();
        i_see_the_Shipping_Method_Overlay();
        iClickOnNextStepButton();
        iSeeTheShippingInformationOverlay();
        iInputTheShippingInformation();
        iClickOnNextStepButton();
        iSeeTheSameAsShippingPrompt();
        iClickOnNextStepButton();
        iSeeTheOrderReviewOverlay();
        iClickOnNextStepButton();
        TenderingSteps tender = new TenderingSteps();
        tender.iCanSeeTheMockTenderingScreen();

    }

    @Given("^On \"([^\"]*)\" I arrive on the \"([^\"]*)\" page after swiping a card$")
    public void onIArriveOnThePageAfterSwipingACard(String app, String page) throws Throwable {
        //TODO after card swipes in customer view work in automation

        CheckoutSteps checkoutSteps = new CheckoutSteps();
        TenderingSteps tenderingSteps = new TenderingSteps();

        switch (app) {
            case "Macy's Sales Trans":
                checkoutSteps.i_am_on("Macy's Sales Trans");
                break;

            case "Bloomingdale's Sales Trans":
                checkoutSteps.i_am_on("Bloomingdale's Sales Trans");
                break;

            case "Macy's Desktop":
                checkoutSteps.i_am_on("Macy's Desktop");
                break;
        }

        switch (page){
            case "Review":
                Wait.secondsUntilElementPresent("home.info_overlay", 10);
                tenderingSteps.iSwipeTheAtTheTenderingScreen("Macy's Prop");
                Thread.sleep(3000);
                checkoutSteps.i_add_an_item_to_the_Checkout_bag();
                checkoutSteps.i_can_see_the_added_to_bag_toast_message();
                checkoutSteps.the_toast_message_fades_away_after_seconds(3);
                Wait.secondsUntilElementPresent("home.progressBar_bag", 10);
//                checkoutSteps.iClickOnTheBagIcon();
//                checkoutSteps.iClickOnTheBagIcon();
                checkoutSteps.i_should_be_able_to_see_the_bag_view();
                Thread.sleep(3000);
                Wait.secondsUntilElementPresent("bag_screen.checkout_button", 10);
                checkoutSteps.i_press_the_checkout_button();
                i_see_the_Shipping_Method_Overlay();
                iPressNextSteps();
                break;

            case "Shipping Method":
                Wait.secondsUntilElementPresent("home.info_overlay", 10);
                tenderingSteps.iSwipeTheAtTheTenderingScreen("Macy's Prop One Customer");
                Thread.sleep(3000);
                checkoutSteps.i_add_an_item_to_the_Checkout_bag();
                checkoutSteps.i_can_see_the_added_to_bag_toast_message();
                checkoutSteps.the_toast_message_fades_away_after_seconds(3);
                Wait.secondsUntilElementPresent("home.progressBar_bag", 10);
//                checkoutSteps.iClickOnTheBagIcon();
//                checkoutSteps.iClickOnTheBagIcon();
//                tenderingSteps.iSwipeTheAtTheTenderingScreen("Macy's Prop One Customer");
                checkoutSteps.i_should_be_able_to_see_the_bag_view();
                Wait.secondsUntilElementPresent("bag_screen.checkout_button", 10);
                checkoutSteps.i_press_the_checkout_button();
                i_see_the_Shipping_Method_Overlay();
                break;
        }



    }

    @And("^I close the edit page? (.*)$")
    public void iCloseTheEditPage() throws Throwable {
        //TODO after card swipes in customer view work in automation

        Clicks.click("CLOSE_BUTTON_LOCATOR");
    }

    @When("^I change the \"([^\"]*)\"$")
    public void iChangeThe(String info) throws Throwable {
        //TODO after card swipes in customer view work in automation

        switch (info) {
            case "Shipping Address":
                iTapEditNextToShippingAddress();
                iInputTheShippingInformation();
                break;

            case "Billing Address":
                iTapEditNextToBillingAddress();
                iInputTheShippingInformation();
                break;
        }
    }

    @When("^I arrive on the \"([^\"]*)\" page$")
    public void iArriveOnThePage(String page) throws Throwable {
        //TODO after card swipes in customer view work in automation

        CheckoutSteps checkoutSteps = new CheckoutSteps();
        TenderingSteps tenderingSteps = new TenderingSteps();

        switch (page){
            case "Bag":
                checkoutSteps.i_should_be_able_to_see_the_bag_view();
                break;
            case "Shipping Method":
                i_see_the_Shipping_Method_Overlay();
                break;
            case "Review Order":
                iSeeTheOrderReviewOverlay();
                break;
            case "Payment":
                tenderingSteps.iCanSeeTheMockTenderingScreen();
                break;
        }

    }

    @Then("^I see the \"([^\"]*)\" information$")
    public void iSeeTheInformation(String info) throws Throwable {
        //TODO after card swipes in customer view work in automation

        switch(info){
            case "Shipping Address":
                //checking Shipping Address name
                String confirmShipAddrName = Elements.findElement("sends.review_shipping_address_name").getText();
                Assert.assertTrue("Customer name is not populated in the Shipping Address section of the Confirm Order overlay",
                        confirmShipAddrName.length() > 0);

                //checking Shipping Address street
                String confirmShipAddrStreet = Elements.findElement("sends.review_shipping_address_street").getText();
                Assert.assertTrue("Customer address line 1 is not populated in the Shipping Address section of the Confirm Order overlay",
                        confirmShipAddrStreet.length() > 0);

                //checking Shipping Address city and state
                String confirmShipAddrCityState = Elements.findElement("sends.review_shipping_address_city_state").getText();
                Assert.assertTrue("Customer City and State are not populated in the Shipping Address section of the Confirm Order overlay",
                        confirmShipAddrCityState.length() > 0);

                //checking Shipping Address zipcode
                String confirmShipAddrZip = Elements.findElement("sends.review_shipping_address_zip").getText();
                Assert.assertTrue("Customer Zip Code is not populated in the Shipping address section of the Confirm Order overlay",
                        confirmShipAddrZip.length() > 0);

                //checking Shipping Address phone
                String confirmShipAddrPhone = Elements.findElement("sends.review_shipping_address_phone").getText();
                Assert.assertTrue("Customer Phone number is not populated in the Shipping address section of the Confirm Order overlay",
                        confirmShipAddrPhone.length() > 0);

            case "Billing Address":
                //checking Billing Address name
                String confirmBillAddrName = Elements.findElement("sends.review_billing_address_name").getText();
                Assert.assertTrue("Customer name is not populated in the Billing Address section of the Confirm Order overlay",
                        confirmBillAddrName.length() > 0);

                //checking Billing Address street
                String confirmBillAddrStreet = Elements.findElement("sends.review_billing_address_street").getText();
                Assert.assertTrue("Customer address line 1 is not populated in the Billing Address section of the Confirm Order overlay",
                        confirmBillAddrStreet.length() > 0);

                //checking Billing Address city and state
                String confirmBillAddrCityState = Elements.findElement("sends.review_billing_address_city_state").getText();
                Assert.assertTrue("Customer City and State are not populated in the Billing Address section of the Confirm Order overlay",
                        confirmBillAddrCityState.length() > 0);

                //checking Billing Address zipcode
                String confirmBillAddrZip = Elements.findElement("sends.review_billing_address_zip").getText();
                Assert.assertTrue("Customer Zip Code is not populated in the Billing address section of the Confirm Order overlay",
                        confirmBillAddrZip.length() > 0);

                //checking Billing Address phone
                String confirmBillAddrPhone = Elements.findElement("sends.review_billing_address_phone").getText();
                Assert.assertTrue("Customer Phone number is not populated in the Billing address section of the Confirm Order overlay",
                        confirmBillAddrPhone.length() > 0);

        }
    }

    @And("^I see the navigation buttons on the review overlay$")
    public void iSeeTheNavigationButtonsOnTheReviewOverlay() throws Throwable {
        //TODO after card swipes in customer view work in automation

        Assert.assertTrue("Complete button is not present on screen", Elements.elementInView("sends.next_button"));
        Assert.assertTrue("Back button is not present on screen", Elements.elementInView("sends.back_button"));
    }

    @When("^I click on the \"([^\"]*)\" button on the overlay$")
    public void iClickOnTheButtonOnTheOverlay(String button) throws Throwable {
        //TODO after card swipes in customer view work in automation

        TenderingSteps tenderingSteps = new TenderingSteps();

        switch(button){
            case "Back":
                iPressTheBackButton();
                break;
            case"Finish":
                iPressNextSteps();
                break;
            case "X":
                tenderingSteps.iCloseTheOverlay();
                break;
            case "Done":
                iPressNextSteps();
                break;
        }
    }


}

