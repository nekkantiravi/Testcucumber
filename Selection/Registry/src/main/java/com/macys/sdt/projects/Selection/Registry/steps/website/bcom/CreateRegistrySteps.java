package com.macys.sdt.projects.Selection.Registry.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Selection.Registry.resources.elements.website.bcom.pages.CreateRegistryForm;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;


import static com.macys.sdt.framework.utils.StepUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by U063689 on 10/30/2017.
 */
public class CreateRegistrySteps {

    private CreateRegistryForm createRegistryForm = new CreateRegistryForm();


    @And("^verify required validation error messages on Registry Create Form$")
    public void iDoNotFillSectionValues() throws Throwable{
        UserProfile regUser = TestUsers.getNewRegistryUser();
        Assert.assertTrue(onPage("new_create_registry"));

        Clicks.click("new_create_registry.next_button");
        validateMyAccountErrorsRequiredFields();
        CreateRegistry.fillAccountInfo(regUser);
        Clicks.click("new_create_registry.next_button");

        Clicks.click("new_create_registry.next_button");
        validateEventDetailsErrorsRequiredFields();
        CreateRegistry.fillEventDetails(regUser);
        Clicks.click("new_create_registry.next_button");

        createRegistryForm.sameAsRegistrantAddressCheckbox.select();
        Clicks.click("new_create_registry.next_button");
        validateAddressErrorsRequiredFileds();
        createRegistryForm.fillAddressInfo(regUser, true);
        Clicks.click("new_create_registry.next_button");

        createRegistryForm.futureShippingAddressCheckbox.select();
        CreateRegistry.selectDropDownValue("new_create_registry.ship_source", "New Address");
        CreateRegistry.selectDropDownValue("new_create_registry.future_shipping" ,"New Address");
        Clicks.click("new_create_registry.register_button");
        validateShippingErrorsRequiredFileds();
        createRegistryForm.fillShippingAddressInfo(regUser, "new address", "new address");
        Clicks.click("new_create_registry.register_button");

    }

    private final String ERROR_MSG_XPATH = "following-sibling::div";

    private void validateMyAccountErrorsRequiredFields(){
        assertEquals(CreateRegistryForm.FIRST_NAME_REQ_MSG, createRegistryForm.firstName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.LAST_NAME_REQ_MSG, createRegistryForm.lastName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.EMAIL_REQ_MSG, createRegistryForm.email.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.PASSWORD_REQ_MSG, createRegistryForm.password.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CONF_PASSWORD_REQ_MSG, createRegistryForm.verifyPassword.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.BDAY_REQ_MSG, createRegistryForm.dateOfBirth.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.SEQURITY_ANSW_REQ_MSG, createRegistryForm.securityAnswer.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    private void validateMyAccountErrorsInvalidFields(){
        assertEquals(CreateRegistryForm.FIRST_NAME_INV_MSG, createRegistryForm.firstName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.LAST_NAME_INV_MSG, createRegistryForm.lastName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.EMAIL_INV_MSG, createRegistryForm.email.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.PASSWORD_INV_MSG, createRegistryForm.password.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CONF_PASSWORD_INV_MSG_NOT_MATCH, createRegistryForm.verifyPassword.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.BDAY_INV_MSG_YOUNG, createRegistryForm.dateOfBirth.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.SEQURITY_ANSW_INV_MSG, createRegistryForm.securityAnswer.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    private void validateEventDetailsErrorsRequiredFields(){
        assertEquals(CreateRegistryForm.FIRST_NAME_REQ_MSG, createRegistryForm.coRegistrantFirstName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.LAST_NAME_REQ_MSG, createRegistryForm.coRegistrantLastName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.EVENT_DATE_REQ_MSG, createRegistryForm.eventDate.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.NUMBER_GUESTS_REQ_MSG, createRegistryForm.guestsCount.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.STORE_LOC_REQ_MSG, createRegistryForm.storeLocation.findElement(By.xpath("../"+ERROR_MSG_XPATH)).getText());
    }

    private void validateEventDetailsErrorsInvalidFields(){
        assertEquals(CreateRegistryForm.FIRST_NAME_INV_MSG, createRegistryForm.coRegistrantFirstName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.LAST_NAME_INV_MSG, createRegistryForm.coRegistrantLastName.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.EVENT_DATE_INV_MSG, createRegistryForm.eventDate.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.NUMBER_GUESTS_INV_MSG, createRegistryForm.guestsCount.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    private void validateAddressErrorsRequiredFileds(){
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_REQ_MSG, createRegistryForm.registrantAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_REQ_MSG, createRegistryForm.registrantAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_REQ_MSG, createRegistryForm.registrantAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.PHONE_REQ_MSG, createRegistryForm.registrantAddressPhone.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.STATE_REQ_MSG, createRegistryForm.registrantAddressState.findElement(By.xpath("../"+ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_REQ_MSG, createRegistryForm.coRegistrantAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_REQ_MSG, createRegistryForm.coRegistrantAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_REQ_MSG, createRegistryForm.coRegistrantAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.STATE_REQ_MSG, createRegistryForm.coRegistrantAddressState.findElement(By.xpath("../"+ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.PHONE_REQ_MSG, createRegistryForm.coRegistrantAddressPhone.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    private void validateAddressErrorsInvalidFileds(){
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_INV_MSG, createRegistryForm.registrantAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_2_INV_MSG, createRegistryForm.registrantAddressLine2.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_INV_MSG, createRegistryForm.registrantAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_INV_MSG, createRegistryForm.registrantAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.PHONE_INV_MSG_INCOMPLETE, createRegistryForm.registrantAddressPhone.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_INV_MSG, createRegistryForm.coRegistrantAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_2_INV_MSG, createRegistryForm.coRegistrantAddressLine2.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_INV_MSG, createRegistryForm.coRegistrantAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_INV_MSG, createRegistryForm.coRegistrantAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.PHONE_INV_MSG_INCOMPLETE, createRegistryForm.coRegistrantAddressPhone.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    private void validateShippingErrorsRequiredFileds(){
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_REQ_MSG, createRegistryForm.otherAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_REQ_MSG, createRegistryForm.otherAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.STATE_REQ_MSG, createRegistryForm.otherAddressState.findElement(By.xpath("../"+ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_REQ_MSG, createRegistryForm.otherAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_REQ_MSG, createRegistryForm.futureAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_REQ_MSG, createRegistryForm.futureAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_REQ_MSG, createRegistryForm.futureAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.STATE_REQ_MSG, createRegistryForm.futureAddressState.findElement(By.xpath("../"+ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.FUTURE_SHIP_DATE_REQ_MSG, createRegistryForm.futureShippingStartDate.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    private void validateShippingErrorsInvalidFileds(){
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_INV_MSG, createRegistryForm.otherAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_2_INV_MSG, createRegistryForm.otherAddressLine2.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_INV_MSG, createRegistryForm.otherAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_INV_MSG, createRegistryForm.otherAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_1_INV_MSG, createRegistryForm.futureAddressLine1.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ADDRESS_LINE_2_INV_MSG, createRegistryForm.futureAddressLine2.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.CITY_INV_MSG, createRegistryForm.futureAddressCity.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.ZIP_INV_MSG, createRegistryForm.futureAddressZipCode.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
        assertEquals(CreateRegistryForm.FUTURE_SHIP_DATE_INV_MSG_PAST, createRegistryForm.futureShippingStartDate.findElement(By.xpath(ERROR_MSG_XPATH)).getText());
    }

    @Then("^verify invalid data error messages on Registry Create Form$")
    public void verifyInvalidDataErrorMessagesOnRegistryCreateForm() throws Throwable {
        UserProfile regUser = TestUsers.getNewRegistryUser();
        Assert.assertTrue(onPage("new_create_registry"));

        createRegistryForm.firstName.sendKeys(CreateRegistryForm.FIRST_NAME_WRONG_VALUE);
        createRegistryForm.lastName.sendKeys(CreateRegistryForm.LAST_NAME_WRONG_VALUE);
        createRegistryForm.email.sendKeys(CreateRegistryForm.EMAIL_WRONG_VALUE);
        createRegistryForm.password.sendKeys(CreateRegistryForm.PASSWORD_WRONG_VALUE);
        createRegistryForm.verifyPassword.sendKeys(CreateRegistryForm.CONF_PASSWORD_WRONG_VALUE_NOT_MATCH);
        createRegistryForm.securityAnswer.sendKeys(CreateRegistryForm.SEQURITY_ANSW_WRONG_VALUE);
        createRegistryForm.dateOfBirth.sendKeys(CreateRegistryForm.BDAY_WRONG_VALUE_YOUNG);
        Clicks.click("new_create_registry.next_button");
        validateMyAccountErrorsInvalidFields();
        CreateRegistry.fillAccountInfo(regUser);
        Clicks.click("new_create_registry.next_button");

        createRegistryForm.coRegistrantFirstName.sendKeys(CreateRegistryForm.COREG_FIRST_NAME_WRONG_VALUE);
        createRegistryForm.coRegistrantLastName.sendKeys(CreateRegistryForm.COREG_LAST_NAME_WRONG_VALUE);
        createRegistryForm.eventDate.sendKeys(CreateRegistryForm.EVENT_DATE_WRONG_VALUE);
        createRegistryForm.guestsCount.sendKeys(CreateRegistryForm.NUMBER_GUESTS_WRONG_VALUE);

        Clicks.click("new_create_registry.next_button");
        validateEventDetailsErrorsInvalidFields();
        CreateRegistry.fillEventDetails(regUser);
        Clicks.click("new_create_registry.next_button");

        createRegistryForm.sameAsRegistrantAddressCheckbox.select();
        createRegistryForm.registrantAddressLine1.sendKeys(CreateRegistryForm.ADDRESS_LINE_1_WRONG_VALUE);
        createRegistryForm.registrantAddressLine2.sendKeys(CreateRegistryForm.ADDRESS_LINE_2_WRONG_VALUE);
        createRegistryForm.registrantAddressCity.sendKeys(CreateRegistryForm.CITY_WRONG_VALUE);
        createRegistryForm.registrantAddressZipCode.sendKeys(CreateRegistryForm.ZIP_WRONG_VALUE);
        createRegistryForm.registrantAddressPhone.sendKeys(CreateRegistryForm.PHONE_WRONG_VALUE_INCOMPLETE);

        createRegistryForm.coRegistrantAddressLine1.sendKeys(CreateRegistryForm.ADDRESS_LINE_1_WRONG_VALUE);
        createRegistryForm.coRegistrantAddressLine2.sendKeys(CreateRegistryForm.ADDRESS_LINE_2_WRONG_VALUE);
        createRegistryForm.coRegistrantAddressCity.sendKeys(CreateRegistryForm.CITY_WRONG_VALUE);
        createRegistryForm.coRegistrantAddressZipCode.sendKeys(CreateRegistryForm.ZIP_WRONG_VALUE);
        createRegistryForm.coRegistrantAddressPhone.sendKeys(CreateRegistryForm.PHONE_WRONG_VALUE_INCOMPLETE);

        Clicks.click("new_create_registry.next_button");
        validateAddressErrorsInvalidFileds();
        createRegistryForm.coRegistrantAddressLine1.clear();
        createRegistryForm.coRegistrantAddressLine2.clear();
        createRegistryForm.coRegistrantAddressCity.clear();
        createRegistryForm.coRegistrantAddressZipCode.clear();
        createRegistryForm.coRegistrantAddressPhone.clear();
        createRegistryForm.fillAddressInfo(regUser, true);
        Clicks.click("new_create_registry.next_button");

        createRegistryForm.futureShippingAddressCheckbox.click();
        CreateRegistry.selectDropDownValue("new_create_registry.ship_source", "New Address");
        CreateRegistry.selectDropDownValue("new_create_registry.future_shipping" ,"New Address");
        createRegistryForm.otherAddressLine1.sendKeys(CreateRegistryForm.ADDRESS_LINE_1_WRONG_VALUE);
        createRegistryForm.otherAddressLine2.sendKeys(CreateRegistryForm.ADDRESS_LINE_2_WRONG_VALUE);
        createRegistryForm.otherAddressCity.sendKeys(CreateRegistryForm.CITY_WRONG_VALUE);
        createRegistryForm.otherAddressZipCode.sendKeys(CreateRegistryForm.ZIP_WRONG_VALUE);
        createRegistryForm.futureShippingStartDate.sendKeys(CreateRegistryForm.FUTURE_SHIP_DATE_WRONG_VALUE_PAST);
        createRegistryForm.futureAddressLine1.sendKeys(CreateRegistryForm.ADDRESS_LINE_1_WRONG_VALUE);
        createRegistryForm.futureAddressLine2.sendKeys(CreateRegistryForm.ADDRESS_LINE_2_WRONG_VALUE);
        createRegistryForm.futureAddressCity.sendKeys(CreateRegistryForm.CITY_WRONG_VALUE);
        createRegistryForm.futureAddressZipCode.sendKeys(CreateRegistryForm.ZIP_WRONG_VALUE);
        Clicks.click("new_create_registry.register_button");
        validateShippingErrorsInvalidFileds();
        createRegistryForm.otherAddressLine1.clear();
        createRegistryForm.otherAddressLine2.clear();
        createRegistryForm.otherAddressCity.clear();
        createRegistryForm.otherAddressZipCode.clear();
        createRegistryForm.futureAddressLine1.clear();
        createRegistryForm.futureAddressLine2.clear();
        createRegistryForm.futureAddressCity.clear();
        createRegistryForm.futureAddressZipCode.clear();
        createRegistryForm.fillShippingAddressInfo(regUser, "new address", "new address");
        Clicks.click("new_create_registry.register_button");

    }

    @And("^I create a new registry with different partner's mailing address$")
    public void iCreateANewRegistryWithDifferentPartnerSMailingAddress() throws Throwable {
        UserProfile regUser = TestUsers.getNewRegistryUser();
        CreateRegistry.fillAccountInfo(regUser);
        CreateRegistry.fillEventDetails(regUser);
        createRegistryForm.fillAddressInfo(regUser, true);
        CreateRegistry.fillShippingDetails(regUser);
        Wait.setWaitRequired();
        Elements.findElement("new_create_registry.register_button").click();
        Clicks.click("new_create_registry.manage_registry_button");
    }

}
