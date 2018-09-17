package com.macys.sdt.projects.Selection.Registry.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Selection.Registry.actions.website.RegistryActions;
import com.macys.sdt.projects.Selection.Registry.resources.elements.website.bcom.pages.EditProfile;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static org.junit.Assert.assertEquals;

/**
 * Created by U063689 on 9/28/2017.
 */
public class EditRegistrySteps {

    private EditProfile editProfile = Navigate.get(EditProfile.class);
    private RegistryActions registryActions = new RegistryActions();

    @When("^I navigate to Edit Registry Page$")
    public void iNavigateToEditRegistryPage(){
        if(onPage("registry_manager")){
            Navigate.browserRefresh();
            Wait.untilElementPresent("registry_manager.edit_profile_button");
            Clicks.click("registry_manager.edit_profile_button");
            Assert.assertTrue(onPage("edit_registry_redesign"));
        }
        else {
            Navigate.browserRefresh();
            Navigate.visit("edit_registry_redesign");
            Assert.assertTrue(onPage("edit_registry_redesign"));
        }
    }


    @And("^I delete all entries in the ([^\"]*) section$")
    public void iDeleteAllEntriesInTheMyAccountSection(String section){

        switch (section){
            case "my account":{
                editProfile.firstName.clear();
                editProfile.lastName.clear();
                editProfile.email.clear();
                editProfile.password.clear();
                editProfile.verifyPassword.clear();
                editProfile.birthDate.clear();
                editProfile.securityAnswer.clear();
                editProfile.saveButton.click();
                break;
            }
            case "event details": {
                editProfile.coRegistrantFirstName.clear();
                editProfile.coRegistrantLastName.clear();
                editProfile.eventDate.clear();
                editProfile.numberOfGuests.clear();
                editProfile.saveButton.click();
                break;
            }
            case "address": {
                editProfile.addressLine1.clear();
                editProfile.addressLine2.clear();
                editProfile.addressCity.clear();
                editProfile.addressZipCode.clear();
                editProfile.addressPhoneNumber.clear();

                if(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked")){
                    editProfile.coRegistrantSameContactInfo.click();
                }
                editProfile.coRegistrantAddressLine1.clear();
                editProfile.coRegistrantAddressLine2.clear();
                editProfile.coRegistrantAddressCity.clear();
                editProfile.coRegistrantAddressZipCode.clear();
                editProfile.coRegistrantAddressPhone.clear();
                editProfile.saveButton.click();
                break;
            }
            case "shipping":{

                if(!editProfile.currentShipDropdown.getText().equalsIgnoreCase("new address")){
                    editProfile.currentShipDropdown.selectByVisibleText("New Address");
                }
                editProfile.currentShippingAddressLine1.clear();
                editProfile.currentShippingAddressCity.clear();
                editProfile.currentShippingAddressZipCode.clear();
                editProfile.currentShippingAddressCity.click();

                if(!editProfile.futureShippingAddressEnabledCheckbox.getAttribute("class").contains("checked")){
                    editProfile.futureShippingAddressEnabled.click();
                }
                editProfile.futureDropdown.selectByVisibleText("New Address");
                editProfile.futureShippingStartDate.clear();
                editProfile.futureShippingAddressLine1.clear();
                editProfile.futureShippingAddressCity.clear();
                editProfile.futureShippingAddressZipCode.clear();
                editProfile.saveButton.click();
                break;
            }

        }
    }

    private final String ERROR_MSG_XPATH = "following-sibling::div";

    @Then("^validate the ([^\"]*) section for required validation messages$")
    public void validateTheMyAccountSectionForRequiredValidationMessages(String section){
        switch (section) {
            case "my account": {
                WebElement errorMsg = editProfile.firstName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FIRST_NAME_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.lastName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.LAST_NAME_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.email.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.EMAIL_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.password.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.PASSWORD_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.verifyPassword.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CONF_PASSWORD_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.birthDate.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.BDAY_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.securityAnswer.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.SEQURITY_ANSW_REQ_MSG, errorMsg.getText());
                break;
            }
            case "event details": {
                WebElement errorMsg = editProfile.coRegistrantFirstName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FIRST_NAME_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantLastName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.LAST_NAME_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.eventDate.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.EVENT_DATE_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.numberOfGuests.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.NUMBER_GUESTS_REQ_MSG, errorMsg.getText());
                break;
            }
            case "address": {
                WebElement errorMsg = editProfile.addressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ADDRESS_LINE_1_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.addressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CITY_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.addressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ZIP_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.addressPhoneNumber.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.PHONE_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ADDRESS_LINE_1_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CITY_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ZIP_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressPhone.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.PHONE_REQ_MSG, errorMsg.getText());
                break;
            }
            case "shipping": {
                WebElement errorMsg = editProfile.currentShippingAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ADDRESS_LINE_1_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.currentShippingAddressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CITY_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.currentShippingAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ZIP_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ADDRESS_LINE_1_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingAddressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CITY_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ZIP_REQ_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingStartDate.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FUTURE_SHIP_DATE_REQ_MSG, errorMsg.getText());
                break;
            }
        }
    }


    @Then("^I validate the ([^\"]*) section for error messages on invalid input$")
    public void validateTheSectionSectionForErrorMessagesOnInvalidInput(String section){
        switch (section) {
            case "my account": {
                WebElement errorMsg = editProfile.firstName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FIRST_NAME_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.lastName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.LAST_NAME_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.email.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.EMAIL_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.password.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.PASSWORD_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.verifyPassword.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CONF_PASSWORD_INV_MSG_NOT_MATCH, errorMsg.getText());

                errorMsg = editProfile.birthDate.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.BDAY_INV_MSG_YOUNG, errorMsg.getText());

                errorMsg = editProfile.securityAnswer.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.SEQURITY_ANSW_INV_MSG, errorMsg.getText());
                break;
            }
            case "event details": {
                WebElement errorMsg = editProfile.coRegistrantFirstName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.COREG_FIRST_NAME_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantLastName.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.COREG_LAST_NAME_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.eventDate.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.EVENT_DATE_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.numberOfGuests.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.NUMBER_GUESTS_INV_MSG, errorMsg.getText());
                break;
            }
            case "address": {
                WebElement errorMsg = editProfile.addressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ADDRESS_LINE_1_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.addressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CITY_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.addressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.ZIP_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.addressPhoneNumber.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.PHONE_INV_MSG_INCOMPLETE, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.COREG_ADDRESS_LINE_1_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.COREG_CITY_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.COREG_ZIP_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.coRegistrantAddressPhone.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.COREG_PHONE_INV_MSG_INCOMPLETE, errorMsg.getText());
                break;
            }
            case "shipping": {
                WebElement errorMsg = editProfile.currentShippingAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CURR_SHIP_ADDRESS_LINE_1_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.currentShippingAddressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CURR_SHIP_CITY_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.currentShippingAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.CURR_SHIP_ZIP_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FUTURE_SHIP_ADDRESS_LINE_1_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingAddressCity.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FUTURE_SHIP_CITY_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FUTURE_SHIP_ZIP_INV_MSG, errorMsg.getText());

                errorMsg = editProfile.futureShippingStartDate.findElement(By.xpath(ERROR_MSG_XPATH));
                assertEquals(EditProfile.FUTURE_SHIP_DATE_INV_MSG_PAST, errorMsg.getText());
                break;
            }
        }
    }

    @And("^I enter invalid values in the ([^\"]*) section$")
    public void iEnterInvalidValuesInTheSectionSection(String section) throws Throwable {

        switch (section) {
            case "my account": {
                editProfile.firstName.clear();
                editProfile.firstName.sendKeys(EditProfile.FIRST_NAME_WRONG_VALUE);
                editProfile.lastName.clear();
                editProfile.lastName.sendKeys(EditProfile.LAST_NAME_WRONG_VALUE);
                editProfile.email.clear();
                editProfile.email.sendKeys(EditProfile.EMAIL_WRONG_VALUE);
                editProfile.password.clear();
                editProfile.password.sendKeys(EditProfile.PASSWORD_WRONG_VALUE);
                editProfile.verifyPassword.clear();
                editProfile.verifyPassword.sendKeys(EditProfile.CONF_PASSWORD_WRONG_VALUE_NOT_MATCH);
                editProfile.birthDate.clear();
                editProfile.birthDate.sendKeys(EditProfile.BDAY_WRONG_VALUE_YOUNG);
                editProfile.securityAnswer.clear();
                editProfile.securityAnswer.sendKeys(EditProfile.SEQURITY_ANSW_WRONG_VALUE);
                editProfile.saveButton.click();
                break;
            }

            case "event details": {
                editProfile.coRegistrantFirstName.clear();
                editProfile.coRegistrantFirstName.sendKeys(EditProfile.COREG_FIRST_NAME_WRONG_VALUE);
                editProfile.coRegistrantLastName.clear();
                editProfile.coRegistrantLastName.sendKeys(EditProfile.COREG_LAST_NAME_WRONG_VALUE);
                editProfile.eventDate.clear();
                editProfile.eventDate.sendKeys(EditProfile.EVENT_DATE_WRONG_VALUE);
                editProfile.numberOfGuests.clear();
                editProfile.numberOfGuests.sendKeys(EditProfile.NUMBER_GUESTS_WRONG_VALUE);
                editProfile.saveButton.click();
                break;
            }

            case "address": {
                editProfile.addressLine1.clear();
                editProfile.addressLine1.sendKeys(EditProfile.ADDRESS_LINE_1_WRONG_VALUE);
                editProfile.addressLine2.clear();
                editProfile.addressLine2.sendKeys(EditProfile.ADDRESS_LINE_2_WRONG_VALUE);
                editProfile.addressCity.clear();
                editProfile.addressCity.sendKeys(EditProfile.CITY_WRONG_VALUE);
                editProfile.addressZipCode.clear();
                editProfile.addressZipCode.sendKeys(EditProfile.ZIP_WRONG_VALUE);
                editProfile.addressPhoneNumber.clear();
                editProfile.addressPhoneNumber.sendKeys(EditProfile.PHONE_WRONG_VALUE_INCOMPLETE);

                if (editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked")) {
                    editProfile.coRegistrantSameContactInfo.click();
                }
                editProfile.coRegistrantAddressLine1.clear();
                editProfile.coRegistrantAddressLine1.sendKeys(EditProfile.ADDRESS_LINE_1_WRONG_VALUE);
                editProfile.coRegistrantAddressLine2.clear();
                editProfile.coRegistrantAddressLine2.sendKeys(EditProfile.ADDRESS_LINE_2_WRONG_VALUE);
                editProfile.coRegistrantAddressCity.clear();
                editProfile.coRegistrantAddressCity.sendKeys(EditProfile.CITY_WRONG_VALUE);
                editProfile.coRegistrantAddressZipCode.clear();
                editProfile.coRegistrantAddressZipCode.sendKeys(EditProfile.ZIP_WRONG_VALUE);
                editProfile.coRegistrantAddressPhone.clear();
                editProfile.coRegistrantAddressPhone.sendKeys(EditProfile.PHONE_WRONG_VALUE_INCOMPLETE);
                editProfile.saveButton.click();
                break;
            }
            case "shipping": {

                if (!editProfile.currentShipDropdown.getText().equalsIgnoreCase("new address")) {
                    editProfile.currentShipDropdown.selectByVisibleText("New Address");
                }
                editProfile.currentShippingAddressLine1.clear();
                editProfile.currentShippingAddressLine1.sendKeys(EditProfile.ADDRESS_LINE_1_WRONG_VALUE);
                editProfile.currentShippingAddressLine2.clear();
                editProfile.currentShippingAddressLine2.sendKeys(EditProfile.ADDRESS_LINE_2_WRONG_VALUE);
                editProfile.currentShippingAddressCity.clear();
                editProfile.currentShippingAddressCity.sendKeys(EditProfile.CITY_WRONG_VALUE);
                editProfile.currentShippingAddressZipCode.clear();
                editProfile.currentShippingAddressZipCode.sendKeys(EditProfile.ZIP_WRONG_VALUE);
                editProfile.currentShippingAddressCity.click();

                if (!editProfile.futureShippingAddressEnabledCheckbox.getAttribute("class").contains("checked")) {
                    editProfile.futureShippingAddressEnabled.click();
                }
                editProfile.futureDropdown.selectByVisibleText("New Address");
                editProfile.futureShippingStartDate.clear();
                editProfile.futureShippingStartDate.sendKeys(EditProfile.FUTURE_SHIP_DATE_WRONG_VALUE_PAST);
                editProfile.futureShippingAddressLine1.clear();
                editProfile.futureShippingAddressLine1.sendKeys(EditProfile.ADDRESS_LINE_1_WRONG_VALUE);
                editProfile.futureShippingAddressLine2.clear();
                editProfile.futureShippingAddressLine2.sendKeys(EditProfile.ADDRESS_LINE_2_WRONG_VALUE);
                editProfile.futureShippingAddressCity.clear();
                editProfile.futureShippingAddressCity.sendKeys(EditProfile.CITY_WRONG_VALUE);
                editProfile.futureShippingAddressZipCode.clear();
                editProfile.futureShippingAddressZipCode.sendKeys(EditProfile.ZIP_WRONG_VALUE);
                editProfile.saveButton.click();
                break;
            }
        }
    }

    @And("^I change update values in \"([^\"]*)\" section$")
    public void iChangeUpdateValuesInSection(String section) throws Throwable {
       switch (section){
           case ("My Account"):{
               editProfile.firstName.clear();
               editProfile.firstName.sendKeys(EditProfile.FIRST_NAME_NEW_VALUE);
               editProfile.lastName.clear();
               editProfile.lastName.sendKeys(EditProfile.LAST_NAME_NEW_VALUE);
               editProfile.securityQuestion.selectByVisibleText(EditProfile.SEQURITY_QUEST_NEW_VALUE);
               editProfile.securityAnswer.clear();
               editProfile.securityAnswer.sendKeys(EditProfile.SEQURITY_ANSW_NEW_VALUE);
               editProfile.birthDate.clear();
               editProfile.birthDate.sendKeys(EditProfile.BDAY_NEW_VALUE);
               editProfile.gender.selectByVisibleText(EditProfile.GENDER_NEW_VALUE);
               editProfile.saveButton.click();
               Wait.untilElementNotPresent(editProfile.loaderOverlay);
               Wait.untilElementNotPresent(editProfile.saveConfirmationOverlay);
               Wait.untilElementPresent("registry_manager.verify_page");
               break;
           }
           case ("Event Details"):{
               editProfile.coRegistrantFirstName.clear();
               editProfile.coRegistrantFirstName.sendKeys(EditProfile.COREG_FIRST_NAME_NEW_VALUE);
               editProfile.coRegistrantLastName.clear();
               editProfile.coRegistrantLastName.sendKeys(EditProfile.COREG_LAST_NAME_NEW_VALUE);
               editProfile.eventDate.clear();
               editProfile.eventDate.sendKeys(EditProfile.EVENT_DATE_NEW_VALUE);
               editProfile.numberOfGuests.clear();
               editProfile.numberOfGuests.sendKeys(EditProfile.NUMBER_GUESTS_NEW_VALUE);
               editProfile.saveButton.click();
               Wait.untilElementNotPresent(editProfile.loaderOverlay);
               Wait.untilElementNotPresent(editProfile.saveConfirmationOverlay);
               Wait.untilElementPresent("registry_manager.verify_page");
               break;
           }
           case ("Address"): {
               editProfile.addressLine1.clear();
               editProfile.addressLine1.sendKeys(EditProfile.ADDRESS_LINE_1_NEW_VALUE);
               editProfile.addressLine2.clear();
               editProfile.addressLine2.sendKeys(EditProfile.ADDRESS_LINE_2_NEW_VALUE);
               editProfile.addressCity.clear();
               editProfile.addressCity.sendKeys(EditProfile.CITY_NEW_VALUE);
               editProfile.addressZipCode.clear();
               editProfile.addressState.selectByVisibleText(EditProfile.STATE_NEW_VALUE);
               editProfile.addressZipCode.sendKeys(EditProfile.ZIP_NEW_VALUE);
               editProfile.addressPhoneNumber.clear();
               editProfile.addressPhoneNumber.sendKeys(EditProfile.PHONE_NEW_VALUE);

               if (editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked")) {
                   editProfile.coRegistrantSameContactInfo.click();
               }
               editProfile.coRegistrantAddressLine1.clear();
               editProfile.coRegistrantAddressLine1.sendKeys(EditProfile.COREG_ADDRESS_LINE_1_NEW_VALUE);
               editProfile.coRegistrantAddressLine2.clear();
               editProfile.coRegistrantAddressLine2.sendKeys(EditProfile.COREG_ADDRESS_LINE_2_NEW_VALUE);
               editProfile.coRegistrantAddressCity.clear();
               editProfile.coRegistrantAddressCity.sendKeys(EditProfile.COREG_CITY_NEW_VALUE);
               editProfile.coRegistrantAddressState.selectByVisibleText(EditProfile.COREG_STATE_NEW_VALUE);
               editProfile.coRegistrantAddressZipCode.clear();
               editProfile.coRegistrantAddressZipCode.sendKeys(EditProfile.COREG_ZIP_NEW_VALUE);
               editProfile.coRegistrantAddressPhone.clear();
               editProfile.coRegistrantAddressPhone.sendKeys(EditProfile.COREG_PHONE_NEW_VALUE);
               editProfile.saveButton.click();
               Wait.untilElementNotPresent(editProfile.loaderOverlay);
               Wait.untilElementNotPresent(editProfile.saveConfirmationOverlay);
               Wait.untilElementPresent("registry_manager.verify_page");
               break;
           }
           case ("Shipping"):{
               if (!editProfile.currentShipDropdown.getText().equalsIgnoreCase("new address")) {
                   editProfile.currentShipDropdown.selectByVisibleText("New Address");
               }
               editProfile.currentShippingAddressLine1.clear();
               editProfile.currentShippingAddressLine1.sendKeys(EditProfile.CURR_SHIP_ADDRESS_LINE_1_NEW_VALUE);
               editProfile.currentShippingAddressLine2.clear();
               editProfile.currentShippingAddressLine2.sendKeys(EditProfile.CURR_SHIP_ADDRESS_LINE_2_NEW_VALUE);
               editProfile.currentShippingAddressCity.clear();
               editProfile.currentShippingAddressCity.sendKeys(EditProfile.CURR_SHIP_CITY_NEW_VALUE);
               editProfile.currentShippingAddressState.selectByVisibleText(EditProfile.CURR_SHIP_STATE_NEW_VALUE);
               editProfile.currentShippingAddressZipCode.clear();
               editProfile.currentShippingAddressZipCode.sendKeys(EditProfile.CURR_SHIP_ZIP_NEW_VALUE);
               editProfile.currentShippingAddressCity.click();

               if (!editProfile.futureShippingAddressEnabledCheckbox.getAttribute("class").contains("checked")) {
                   editProfile.futureShippingAddressEnabled.click();
               }
               editProfile.futureDropdown.selectByVisibleText("New Address");
               editProfile.futureShippingStartDate.clear();
               editProfile.futureShippingStartDate.sendKeys(EditProfile.FUTURE_SHIP_DATE_NEW_VALUE);
               editProfile.futureShippingAddressLine1.clear();
               editProfile.futureShippingAddressLine1.sendKeys(EditProfile.FUTURE_SHIP_ADDRESS_LINE_1_NEW_VALUE);
               editProfile.futureShippingAddressLine2.clear();
               editProfile.futureShippingAddressLine2.sendKeys(EditProfile.FUTURE_SHIP_ADDRESS_LINE_2_NEW_VALUE);
               editProfile.futureShippingAddressCity.clear();
               editProfile.futureShippingAddressCity.sendKeys(EditProfile.FUTURE_SHIP_CITY_NEW_VALUE);
               editProfile.futureShippingAddressState.selectByVisibleText(EditProfile.FUTURE_SHIP_STATE_NEW_VALUE);
               editProfile.futureShippingAddressZipCode.clear();
               editProfile.futureShippingAddressZipCode.sendKeys(EditProfile.FUTURE_SHIP_ZIP_NEW_VALUE);
               editProfile.saveButton.click();
               Wait.untilElementNotPresent(editProfile.loaderOverlay);
               Wait.untilElementNotPresent(editProfile.saveConfirmationOverlay);
               Wait.untilElementPresent("registry_manager.verify_page");
               break;
           }
       }

    }

    @Then("^I verify the changes have been saved in \"([^\"]*)\" section by navigating back to Edit Registry Profile page$")
    public void iVerifyTheChangesHaveBeenSavedByNavigatingBackToEditRegistryProfilePage(String section){
        iNavigateToEditRegistryPage();
        switch (section){
            case ("My Account"):{
                Assert.assertTrue(editProfile.firstName.getText().equalsIgnoreCase(EditProfile.FIRST_NAME_NEW_VALUE));
                Assert.assertTrue(editProfile.lastName.getText().equalsIgnoreCase(EditProfile.LAST_NAME_NEW_VALUE));
                Assert.assertTrue(editProfile.securityQuestion.getText().equalsIgnoreCase(EditProfile.SEQURITY_QUEST_NEW_VALUE));
                Assert.assertTrue(editProfile.birthDate.getText().equalsIgnoreCase(EditProfile.BDAY_NEW_VALUE));
                Assert.assertTrue(editProfile.gender.getText().equalsIgnoreCase(EditProfile.GENDER_NEW_VALUE));
                break;
            }
            case ("Event Details"):{
                Assert.assertTrue(editProfile.coRegistrantFirstName.getText().equalsIgnoreCase(EditProfile.COREG_FIRST_NAME_NEW_VALUE));
                Assert.assertTrue(editProfile.coRegistrantLastName.getText().equalsIgnoreCase(EditProfile.COREG_LAST_NAME_NEW_VALUE));
                Assert.assertTrue(editProfile.eventDate.getText().equalsIgnoreCase(EditProfile.EVENT_DATE_NEW_VALUE));
                Assert.assertTrue(editProfile.numberOfGuests.getText().equalsIgnoreCase(EditProfile.NUMBER_GUESTS_NEW_VALUE));
                break;
            }
            case ("Address"):{
                Assert.assertTrue(editProfile.addressLine1.getText().equalsIgnoreCase(EditProfile.ADDRESS_LINE_1_NEW_VALUE));
                Assert.assertTrue(editProfile.addressLine2.getText().equalsIgnoreCase(EditProfile.ADDRESS_LINE_2_NEW_VALUE));
                Assert.assertTrue(editProfile.addressCity.getText().equalsIgnoreCase(EditProfile.CITY_NEW_VALUE));
                Assert.assertTrue(editProfile.addressZipCode.getText().equalsIgnoreCase(EditProfile.ZIP_NEW_VALUE));
                Assert.assertTrue(editProfile.addressState.getText().equalsIgnoreCase(EditProfile.STATE_NEW_VALUE));
                Assert.assertTrue(editProfile.addressPhoneNumber.getText().equalsIgnoreCase(EditProfile.PHONE_NEW_VALUE));
                Assert.assertFalse(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                Assert.assertTrue(editProfile.coRegistrantAddressLine1.getText().equalsIgnoreCase(EditProfile.COREG_ADDRESS_LINE_1_NEW_VALUE));
                Assert.assertTrue(editProfile.coRegistrantAddressLine2.getText().equalsIgnoreCase(EditProfile.COREG_ADDRESS_LINE_2_NEW_VALUE));
                Assert.assertTrue(editProfile.coRegistrantAddressCity.getText().equalsIgnoreCase(EditProfile.COREG_CITY_NEW_VALUE));
                Assert.assertTrue(editProfile.coRegistrantAddressZipCode.getText().equalsIgnoreCase(EditProfile.COREG_ZIP_NEW_VALUE));
                Assert.assertTrue(editProfile.coRegistrantAddressState.getText().equalsIgnoreCase(EditProfile.COREG_STATE_NEW_VALUE));
                Assert.assertTrue(editProfile.coRegistrantAddressPhone.getText().equalsIgnoreCase(EditProfile.COREG_PHONE_NEW_VALUE));
                break;
            }
            case ("Shipping"):{
                Assert.assertTrue(editProfile.currentShippingAddressLine1.getText().equalsIgnoreCase(EditProfile.CURR_SHIP_ADDRESS_LINE_1_NEW_VALUE));
                Assert.assertTrue(editProfile.currentShippingAddressLine2.getText().equalsIgnoreCase(EditProfile.CURR_SHIP_ADDRESS_LINE_2_NEW_VALUE));
                Assert.assertTrue(editProfile.currentShippingAddressCity.getText().equalsIgnoreCase(EditProfile.CURR_SHIP_CITY_NEW_VALUE));
                Assert.assertTrue(editProfile.currentShippingAddressZipCode.getText().equalsIgnoreCase(EditProfile.CURR_SHIP_ZIP_NEW_VALUE));
                Assert.assertTrue(editProfile.currentShippingAddressState.getText().equalsIgnoreCase(EditProfile.CURR_SHIP_STATE_NEW_VALUE));
                Assert.assertTrue(editProfile.futureShippingAddressEnabledCheckbox.getAttribute("class").contains("checked"));
                Assert.assertTrue(editProfile.futureShippingAddressLine1.getText().equalsIgnoreCase(EditProfile.FUTURE_SHIP_ADDRESS_LINE_1_NEW_VALUE));
                Assert.assertTrue(editProfile.futureShippingAddressLine2.getText().equalsIgnoreCase(EditProfile.FUTURE_SHIP_ADDRESS_LINE_2_NEW_VALUE));
                Assert.assertTrue(editProfile.futureShippingAddressCity.getText().equalsIgnoreCase(EditProfile.FUTURE_SHIP_CITY_NEW_VALUE));
                Assert.assertTrue(editProfile.futureShippingAddressZipCode.getText().equalsIgnoreCase(EditProfile.FUTURE_SHIP_ZIP_NEW_VALUE));
                Assert.assertTrue(editProfile.futureShippingAddressState.getText().equalsIgnoreCase(EditProfile.FUTURE_SHIP_STATE_NEW_VALUE));
                break;
            }
        }

    }

    @Then("^Verify that Event Type and Preferred Store are disabled on the Edit Registry Profile page$")
    public void verifyThatEventTypeAndPreferredStoreAreDisabledOnTheEditRegistryProfilePage(){
        Assert.assertTrue(editProfile.eventType.getAttribute("disabled").equalsIgnoreCase("true"));
        Assert.assertTrue(editProfile.preferredStore.getAttribute("disabled").equalsIgnoreCase("true"));
    }


    @When("^I (check|uncheck) \"([^\"]*)\" checkbox in the \"([^\"]*)\" section of the Edit Registry Profile$")
    public void iCheckCheckboxInTheSectionOfTheEditRegistryProfile(String isChecked, String checkbox, String section) {
        switch (isChecked) {
            case ("uncheck"): {
                if (checkbox.equalsIgnoreCase("same as mailing address") && section.equalsIgnoreCase("address")) {
                    Assert.assertTrue(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                    editProfile.coRegistrantSameContactInfoCheckbox.click();
                    Assert.assertFalse(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                } else if (checkbox.equalsIgnoreCase("Sign up to receive registry emails") && section.equalsIgnoreCase("event details")) {
                    Assert.assertTrue(editProfile.subscribeRegEmailCheckbox.getAttribute("class").contains("checked"));
                    editProfile.subscribeRegEmail.click();
                    Assert.assertFalse(editProfile.subscribeRegEmailCheckbox.getAttribute("class").contains("checked"));
                }
                break;
            }
            case ("check"): {
                if (checkbox.equalsIgnoreCase("same as mailing address") && section.equalsIgnoreCase("address")) {
                    Assert.assertFalse(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                    editProfile.coRegistrantSameContactInfo.click();
                    Assert.assertTrue(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                } else if (checkbox.equalsIgnoreCase("Sign up to receive registry emails") && section.equalsIgnoreCase("event details")) {
                    Assert.assertFalse(editProfile.subscribeRegEmailCheckbox.getAttribute("class").contains("checked"));
                    editProfile.subscribeRegEmail.click();
                    Assert.assertTrue(editProfile.subscribeRegEmailCheckbox.getAttribute("class").contains("checked"));
                }
                break;
            }
        }
    }

    @And("^I click on \"([^\"]*)\" on the Edit Registry Profile$")
    public void iClickOnOnTheEditRegistryProfile(String clickable){
        switch (clickable){
            case ("Save Changes"):{
                editProfile.saveButton.click();;
                Wait.untilElementNotPresent(editProfile.loaderOverlay);
                Wait.untilElementNotPresent(editProfile.saveConfirmationOverlay);
                break;
            }
            case ("Back To Registry Manager"):{
                editProfile.backToRegManButton.click();
                break;
            }
            case ("Back to Registry Manager link"):{
                editProfile.backToRegManLink.click();
                break;
            }
        }
    }


    @Given("^I visit the website as a bvr user with partner's mailing address different using rest services$")
    public void iVisitTheWebsiteAsABvrUserWithPartnerSMailingAddressDifferentUsingRestServices() throws Throwable {
        registryActions.createRegistryWithPartnersMailingAddressDifferent();
    }

    @Given("^I visit the website as a bvr user using rest services with registry email preferences (selected|not selected)$")
    public void iVisitTheWebsiteAsABvrUserUsingRestServicesWithRegistryEmailPreferencesSelected(String isSelected) throws Throwable {
        if (isSelected.equalsIgnoreCase("selected")){
            registryActions.createRegistryDifferentEmailSubscription(true);
        }
        else {
            registryActions.createRegistryDifferentEmailSubscription(false);
        }
    }


    @Then("^I should see that \"([^\"]*)\" checkbox is (selected|not selected)$")
    public void iShouldSeeThatRegistryEmailPreferencesCheckboxIsSelected(String checkbox, String isSelected) throws Throwable {
        switch (isSelected) {
            case ("selected"): {
                if (checkbox.equalsIgnoreCase("registry email preferences")) {
                    Assert.assertTrue(editProfile.subscribeRegEmailCheckbox.getAttribute("class").contains("checked"));
                }
                if (isSelected.equalsIgnoreCase("selected")) {
                    Assert.assertTrue(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                }
                break;
            }
            case ("not selected"): {
                if (checkbox.equalsIgnoreCase("registry email preferences")) {
                    Assert.assertFalse(editProfile.subscribeRegEmailCheckbox.getAttribute("class").contains("checked"));
                }
                if (isSelected.equalsIgnoreCase("selected")) {
                    Assert.assertFalse(editProfile.coRegistrantSameContactInfoCheckbox.getAttribute("class").contains("checked"));
                }
                break;
            }
        }
    }

    @Then("^I verify that registry has been successfully updated with (selected|not selected) email preferences$")
    public void iVerifyThatRegistryHasBeenSuccessfullyUpdatedWithChangedEmailPreferences(String isSelected) throws Throwable {
        if(isSelected.equalsIgnoreCase("selected")){
            Assert.assertTrue(TestUsers.getCustomerInformation().getRegistry().getPreferences().isSubscribeWeddingSale());
        }
        else {
            Assert.assertFalse(TestUsers.getCustomerInformation().getRegistry().getPreferences().isSubscribeWeddingSale());
        }
    }
}
