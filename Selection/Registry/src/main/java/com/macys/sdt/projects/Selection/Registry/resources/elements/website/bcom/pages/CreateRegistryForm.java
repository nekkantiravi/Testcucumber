package com.macys.sdt.projects.Selection.Registry.resources.elements.website.bcom.pages;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.model.addresses.Address;
import com.macys.sdt.framework.model.addresses.FutureAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindAll;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by U063689 on 10/30/2017.
 */

@FindBy(jsonPath = "new_create_registry")

public class CreateRegistryForm extends Page{

        public TextInput firstName;
        public TextInput lastName;
        public TextInput email;
        public TextInput password;
        public TextInput verifyPassword;
        public TextInput dateOfBirth;
        public Select securityQuestion;
        public TextInput securityAnswer;
        public Select gender;

        public TextInput coRegistrantFirstName;
        public TextInput coRegistrantLastName;
        public Select eventType;
        public TextInput eventDate;
        public TextInput guestsCount;
        public Select storeLocation;
        public CheckBox subscribeForEmails;

        @FindBy (jsonPath = "new_create_registry.registrant_address_line_1")
        public TextInput registrantAddressLine1;
        @FindBy (jsonPath = "new_create_registry.registrant_address_line_2")
        public TextInput registrantAddressLine2;
        public TextInput registrantAddressCity;
        public Select registrantAddressState;
        public TextInput registrantAddressZipCode;
        public TextInput registrantAddressPhone;

        @FindBy (jsonPath = "new_create_registry.same_as_registrant_address_checkbox")
        public CheckBox sameAsRegistrantAddressCheckbox;
        @FindBy (jsonPath = "new_create_registry.same_as_registrant_address")
        public HtmlElement sameAsRegistrantAddress;
        @FindBy (jsonPath = "new_create_registry.co_registrant_address_line_1")
        public TextInput coRegistrantAddressLine1;
        @FindBy (jsonPath = "new_create_registry.co_registrant_address_line_2")
        public TextInput coRegistrantAddressLine2;
        public TextInput coRegistrantAddressCity;
        @FindBy (jsonPath = "new_create_registry.co_registrant_address_state")
        public Select coRegistrantAddressState;
        public TextInput coRegistrantAddressZipCode;
        public TextInput coRegistrantAddressPhone;

        @FindBy (jsonPath = "new_create_registry.other_address_line_1")
        public TextInput otherAddressLine1;
        @FindBy (jsonPath = "new_create_registry.other_address_line_2")
        public TextInput otherAddressLine2;
        public TextInput otherAddressCity;
        @FindBy (jsonPath = "new_create_registry.other_address_state")
        public Select otherAddressState;
        public Select shipSource;
        public TextInput otherAddressZipCode;

        @FindBy(jsonPath = "new_create_registry.future_shipping_address_checkbox")
        public CheckBox futureShippingAddressCheckbox;
        public HtmlElement futureShippingAddress;

        public Select futureShippingOptions;
        @FindBy(jsonPath = "new_create_registry.future_shipping_start_date")
        public TextInput futureShippingStartDate;

        @FindBy (jsonPath = "new_create_registry.future_address_line_1")
        public TextInput futureAddressLine1;
        @FindBy (jsonPath = "new_create_registry.future_address_line_2")
        public TextInput futureAddressLine2;
        public TextInput futureAddressCity;
        @FindBy (jsonPath = "new_create_registry.future_address_state")
        public Select futureAddressState;
        public TextInput futureAddressZipCode;



        public static final String FIRST_NAME_REQ_MSG            = "First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters.";
        public static final String LAST_NAME_REQ_MSG             = "Last Name is a required field and may only contain letters and hyphens. Your entry may not exceed 30 characters.";
        public static final String EMAIL_REQ_MSG                 = "Please enter an email address.";
        public static final String PASSWORD_REQ_MSG              = "Your password must be 7-16 characters.";
        public static final String CONF_PASSWORD_REQ_MSG         = "Your password must be 7-16 characters.";
        public static final String SEQURITY_ANSW_REQ_MSG         = "The answer to the selected security question must be between 2 and 20 characters. Please try again.";
        public static final String BDAY_REQ_MSG                  = "The Date you entered is invalid. Please enter Date in this format: MM/DD/YYYY.";

        public static final String EVENT_DATE_REQ_MSG            = "The Date you entered is invalid. Please enter Date in this format: MM/DD/YYYY.";
        public static final String NUMBER_GUESTS_REQ_MSG         = "Estimated Number of Guests field must contain a numeric value (1-9999). Please try again.";
        public static final String STORE_LOC_REQ_MSG             ="Preferred Store is a required field. Please try again.";

        public static final String ADDRESS_LINE_1_REQ_MSG        = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String CITY_REQ_MSG                  = "City is a required field. Please try again.";
        public static final String ZIP_REQ_MSG                   = "Your Zip Code must be 5 digits. Please try again.";
        public static final String PHONE_REQ_MSG                 = "Please enter a phone number.";
        public static final String STATE_REQ_MSG                ="State is a required field. Please try again.";


        public static final String FIRST_NAME_INV_MSG               = "First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters.";
        public static final String LAST_NAME_INV_MSG                = "Last Name is a required field and may only contain letters and hyphens. Your entry may not exceed 30 characters.";
        public static final String EMAIL_INV_MSG                    = "Please enter a valid email address in the following format: jane@email.com";
        public static final String PASSWORD_INV_MSG                 = "Your password must be 7-16 characters.";
        public static final String CONF_PASSWORD_INV_MSG_NOT_MATCH  = "Password & verify password fields don't match. Please try again.";
        public static final String SEQURITY_ANSW_INV_MSG            = "The answer to the selected security question must be between 2 and 20 characters. Please try again.";
        public static final String BDAY_INV_MSG_YOUNG               = "The date you entered is invalid";

        public static final String EVENT_DATE_INV_MSG               = "The date you entered is invalid";
        public static final String NUMBER_GUESTS_INV_MSG            = "Estimated Number of Guests field must contain a numeric value (1-9999). Please try again.";

        public static final String ADDRESS_LINE_1_INV_MSG           = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String ADDRESS_LINE_2_INV_MSG           = "Apartment or Suite may only contain letters, numbers, # and hyphens.";
        public static final String CITY_INV_MSG                     = "City is a required field. Please try again.";
        public static final String ZIP_INV_MSG                      = "Your Zip Code must be 5 digits. Please try again.";
        public static final String PHONE_INV_MSG_INCOMPLETE         = "Please enter a 10-digit phone number.";


        public static final String FUTURE_SHIP_DATE_INV_MSG_PAST        = "Future shipping date cannot be prior to today's date.";
        public static final String FUTURE_SHIP_DATE_REQ_MSG             = "The Date you entered is invalid. Please enter Date in this format: MM/DD/YYYY.";

        public static final String FIRST_NAME_WRONG_VALUE               = "Aaa$^";
        public static final String LAST_NAME_WRONG_VALUE                = "Aaa$^";
        public static final String EMAIL_WRONG_VALUE                    = "email";
        public static final String PASSWORD_WRONG_VALUE                 = "passw*";
        public static final String CONF_PASSWORD_WRONG_VALUE_NOT_MATCH  = "different";
        public static final String SEQURITY_ANSW_WRONG_VALUE            = "q";
        public static final String BDAY_WRONG_VALUE_YOUNG               = "02/02/2015";

        public static final String COREG_FIRST_NAME_WRONG_VALUE         = "Aaa$^";
        public static final String COREG_LAST_NAME_WRONG_VALUE         = "Aaa$^";
        public static final String EVENT_DATE_WRONG_VALUE              = "02/30/2015";
        public static final String NUMBER_GUESTS_WRONG_VALUE           = "0";

        public static final String ADDRESS_LINE_1_WRONG_VALUE          = "seaw%@";
        public static final String ADDRESS_LINE_2_WRONG_VALUE           = "$12";
        public static final String CITY_WRONG_VALUE                    = "ger%6";
        public static final String ZIP_WRONG_VALUE                     = "12aw";
        public static final String PHONE_WRONG_VALUE_INCOMPLETE         = "22215";

        public static final String FUTURE_SHIP_DATE_WRONG_VALUE_PAST        = "02/02/2017";


    public void fillAddressInfo(UserProfile customer, boolean partnersDiff){
        if (partnersDiff==true){
            registrantAddressLine1.sendKeys(customer.getUser().getProfileAddress().getAddressLine1());
            registrantAddressLine2.sendKeys(customer.getUser().getProfileAddress().getAddressLine2());
            registrantAddressCity.sendKeys(customer.getUser().getProfileAddress().getCity());
            registrantAddressZipCode.sendKeys(customer.getUser().getProfileAddress().getZipCode());
            registrantAddressPhone.sendKeys(customer.getUser().getProfileAddress().getBestPhone());
            CreateRegistry.selectDropDownValue("new_create_registry.registrant_address_state",
                    (customer.getUser().getProfileAddress().getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(customer.getUser().getProfileAddress().getState()) : customer.getUser().getProfileAddress().getState()));

            if (sameAsRegistrantAddressCheckbox.getAttribute("class").contains("checked")) {
                sameAsRegistrantAddress.click();
            }
            coRegistrantAddressLine1.sendKeys(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getAddressLine1());
            coRegistrantAddressLine2.sendKeys(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getAddressLine2());
            coRegistrantAddressPhone.sendKeys(customer.getRegistry().getCoRegistrantContactInfo().getBestPhone());
            coRegistrantAddressZipCode.sendKeys(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getPostalCode());
            coRegistrantAddressCity.sendKeys(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getCity());
            CreateRegistry.selectDropDownValue("new_create_registry.co_registrant_address_state",
                    customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getState().length()==2 ?
                            AbbreviationHelper.translateStateAbbreviation(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getState()) :customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getState());
            Clicks.click("new_create_registry.next_button");
        }
        else{
            CreateRegistry.fillAddressDetails(customer);
        }
    }

    public void fillShippingAddressInfo(UserProfile customer, String currentAddressType, String futureAddressType){
        FutureAddress futureAddress = new FutureAddress();
        TestUsers.getRandomValidAddress(null, futureAddress);
        String state;
        if (currentAddressType.equalsIgnoreCase("new address")){
            CreateRegistry.selectDropDownValue("new_create_registry.ship_source", "New Address");
            otherAddressLine1.sendKeys(customer.getRegistry().getFutureAddress().getAddressLine1());
            otherAddressCity.sendKeys(customer.getRegistry().getFutureAddress().getCity());
            otherAddressZipCode.sendKeys(customer.getRegistry().getFutureAddress().getPostalCode());
            System.out.println();
            if(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getState().length()==2){
                state = AbbreviationHelper.translateStateAbbreviation(customer.getRegistry().getCoRegistrantContactInfo().getCurrentAddress().getState());
            }
            else {
                state = customer.getRegistry().getFutureAddress().getState();
            }
            CreateRegistry.selectDropDownValue("new_create_registry.other_address_state", state);
        }
        if (futureAddressType.equalsIgnoreCase("new address")){
            if (futureShippingAddress.getAttribute("class").contains("checked")){
                futureShippingAddressCheckbox.click();
            }
            futureShippingStartDate.sendKeys(customer.getRegistry().getFutureAddress().getEffectiveDate());
            CreateRegistry.selectDropDownValue("new_create_registry.future_shipping" ,"New Address");
            futureAddressLine1.sendKeys(futureAddress.getAddressLine1());
            futureAddressZipCode.sendKeys(futureAddress.getPostalCode());
            futureAddressCity.sendKeys(futureAddress.getCity());
            if(futureAddress.getState().length()==2){
                state = AbbreviationHelper.translateStateAbbreviation(futureAddress.getState());
            }
            else {
                state = customer.getRegistry().getFutureAddress().getState();
            }
            CreateRegistry.selectDropDownValue("new_create_registry.future_address_state", state);
        }
    }

}
