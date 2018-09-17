package com.macys.sdt.projects.Selection.Registry.resources.elements.website.bcom.pages;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindAll;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;

import ru.yandex.qatools.htmlelements.element.*;

/**
 * Created by U063689 on 9/28/2017.
 */
@FindBy(jsonPath = "edit_registry_redesign")

public class EditProfile extends Page{


        public Button saveButton;
        public Button backToRegManButton;

        public HtmlElement backToRegManLink;
        public HtmlElement saveConfirmationOverlay;
        public HtmlElement loaderOverlay;

        public TextInput firstName;
        public TextInput lastName;
        public TextInput email;
        public TextInput password;
        public TextInput verifyPassword;
        public TextInput birthDate;
        public Select securityQuestion;
        public TextInput securityAnswer;
        public Select gender;

        public TextInput coRegistrantFirstName;
        public TextInput coRegistrantLastName;
        public Select eventType;
        public TextInput eventDate;
        public TextInput numberOfGuests;
        public Select preferredStore;

        public CheckBox subscribeRegEmailCheckbox;
        public HtmlElement subscribeRegEmail;
        @FindBy (jsonPath = "edit_registry_redesign.address_line_1")
        public TextInput addressLine1;
        @FindBy (jsonPath = "edit_registry_redesign.address_line_2")
        public TextInput addressLine2;
        public TextInput addressCity;
        public Select addressState;
        public TextInput addressZipCode;
        public TextInput addressPhoneNumber;

        @FindBy (jsonPath = "edit_registry_redesign.co_registrant_same_contact_info_checkbox")
        public CheckBox coRegistrantSameContactInfoCheckbox;
        public HtmlElement coRegistrantSameContactInfo;
        @FindBy (jsonPath = "edit_registry_redesign.co_registrant_address_line_1")
        public TextInput coRegistrantAddressLine1;
        @FindBy (jsonPath = "edit_registry_redesign.co_registrant_address_line_2")
        public TextInput coRegistrantAddressLine2;
        public TextInput coRegistrantAddressCity;
        @FindAll({@FindBy(jsonPath = "edit_registry_redesign.co_registrant_address_state_custom"),
                @FindBy (jsonPath = "edit_registry_redesign.co_registrant_address_state") })
        public Select coRegistrantAddressState;
        public TextInput coRegistrantAddressZipCode;
        public TextInput coRegistrantAddressPhone;

        @FindBy (jsonPath = "edit_registry_redesign.current_shipping_address_line_1")
        public TextInput currentShippingAddressLine1;
        @FindBy (jsonPath = "edit_registry_redesign.current_shipping_address_line_2")
        public TextInput currentShippingAddressLine2;
        public TextInput currentShippingAddressCity;
        @FindAll({@FindBy(jsonPath = "edit_registry_redesign.current_shipping_address_state_custom"),
                @FindBy (jsonPath = "edit_registry_redesign.current_shipping_address_state") })
        public Select currentShippingAddressState;
        public Select currentShipDropdown;
        public TextInput currentShippingAddressZipCode;

        @FindBy(jsonPath = "edit_registry_redesign.future_shipping_address_enabled_checkbox")
        public CheckBox futureShippingAddressEnabledCheckbox;
        public HtmlElement futureShippingAddressEnabled;
        public Select futureDropdown;
        @FindBy(jsonPath = "edit_registry_redesign.future_shipping_start_date")
        public TextInput futureShippingStartDate;

        @FindBy (jsonPath = "edit_registry_redesign.future_shipping_address_line_1")
        public TextInput futureShippingAddressLine1;
        @FindBy (jsonPath = "edit_registry_redesign.future_shipping_address_line_2")
        public TextInput futureShippingAddressLine2;
        public TextInput futureShippingAddressCity;
        @FindAll({@FindBy(jsonPath = "edit_registry_redesign.future_shipping_address_state_custom"),
                @FindBy (jsonPath = "edit_registry_redesign.future_shipping_address_state") })
        public Select futureShippingAddressState;
        public TextInput futureShippingAddressZipCode;

        private Button expandMyAccountSectionHeader;
        private Button expandEventDetailsSectionHeader;
        private Button expandAddressSectionHeader;
        private Button expandShippingSectionHeader;


        public static final String FIRST_NAME_REQ_MSG            = "First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters.";
        public static final String LAST_NAME_REQ_MSG             = "Last Name is a required field and may only contain letters and hyphens. Your entry may not exceed 30 characters.";
        public static final String EMAIL_REQ_MSG                 = "Please enter an email address.";
        public static final String PASSWORD_REQ_MSG              = "Your password must be 7-16 characters.";
        public static final String CONF_PASSWORD_REQ_MSG         = "Your password must be 7-16 characters.";
        public static final String SEQURITY_ANSW_REQ_MSG         = "The answer to the selected security question must be between 2 and 20 characters. Please try again.";
        public static final String BDAY_REQ_MSG                  = "The Date you entered is invalid. Please enter Date in this format: MM/DD/YYYY.";

        public static final String EVENT_DATE_REQ_MSG            = "The Date you entered is invalid. Please enter Date in this format: MM/DD/YYYY.";
        public static final String NUMBER_GUESTS_REQ_MSG         = "Estimated Number of Guests field must contain a numeric value (1-9999). Please try again.";


        public static final String ADDRESS_LINE_1_REQ_MSG     = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String CITY_REQ_MSG                  = "City is a required field. Please try again.";
        public static final String ZIP_REQ_MSG                   = "Your Zip Code must be 5 digits. Please try again.";
        public static final String PHONE_REQ_MSG                 = "Please enter a phone number.";


        public static final String FUTURE_SHIP_DATE_REQ_MSG             = "The Date you entered is invalid. Please enter Date in this format: MM/DD/YYYY.";


        public static final String FIRST_NAME_INV_MSG               = "First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters.";
        public static final String LAST_NAME_INV_MSG                = "Last Name is a required field and may only contain letters and hyphens. Your entry may not exceed 30 characters.";
        public static final String EMAIL_INV_MSG                    = "Please enter a valid email address in the following format: jane@email.com";
        public static final String PASSWORD_INV_MSG                 = "Your password must be 7-16 characters.";
        public static final String CONF_PASSWORD_INV_MSG_NOT_MATCH  = "Password & verify password fields don't match. Please try again.";
        public static final String SEQURITY_ANSW_INV_MSG            = "The answer to the selected security question must be between 2 and 20 characters. Please try again.";
        public static final String BDAY_INV_MSG_YOUNG               = "The date you entered is invalid";

        public static final String COREG_FIRST_NAME_INV_MSG         = "First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters.";
        public static final String COREG_LAST_NAME_INV_MSG          = "Last Name is a required field and may only contain letters and hyphens. Your entry may not exceed 30 characters.";
        public static final String EVENT_DATE_INV_MSG               = "The date you entered is invalid";
        public static final String NUMBER_GUESTS_INV_MSG            = "Estimated Number of Guests field must contain a numeric value (1-9999). Please try again.";

        public static final String ADDRESS_LINE_1_INV_MSG           = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String ADDRESS_LINE_2_INV_MSG           = "Apartment or Suite may only contain letters, numbers, # and hyphens.";
        public static final String CITY_INV_MSG                     = "City is a required field. Please try again.";
        public static final String ZIP_INV_MSG                      = "Your Zip Code must be 5 digits. Please try again.";
        public static final String PHONE_INV_MSG_INCOMPLETE         = "Please enter a 10-digit phone number.";

        public static final String COREG_ADDRESS_LINE_1_INV_MSG     = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String COREG_ADDRESS_LINE_2_INV_MSG     = "Apartment or Suite may only contain letters, numbers, # and hyphens.";
        public static final String COREG_CITY_INV_MSG               = "City is a required field. Please try again.";
        public static final String COREG_ZIP_INV_MSG                = "Your Zip Code must be 5 digits. Please try again.";
        public static final String COREG_PHONE_INV_MSG_INCOMPLETE   = "Please enter a 10-digit phone number.";

        public static final String CURR_SHIP_ADDRESS_LINE_1_INV_MSG   = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String CURR_ADDRESS_LINE_2_INV_MSG        = "Apartment or Suite may only contain letters, numbers, # and hyphens.";
        public static final String CURR_SHIP_CITY_INV_MSG             = "City is a required field. Please try again.";
        public static final String CURR_SHIP_ZIP_INV_MSG              = "Your Zip Code must be 5 digits. Please try again.";

        public static final String FUTURE_SHIP_ADDRESS_LINE_1_INV_MSG   = "Street Address is a required field and may only contain letters, numbers and #. Your entry may not exceed 35 characters, including spaces.";
        public static final String FUTURE_ADDRESS_LINE_2_INV_MSG        = "Apartment or Suite may only contain letters, numbers, # and hyphens.";
        public static final String FUTURE_SHIP_CITY_INV_MSG             = "City is a required field. Please try again.";
        public static final String FUTURE_SHIP_ZIP_INV_MSG              = "Your Zip Code must be 5 digits. Please try again.";
        public static final String FUTURE_SHIP_DATE_INV_MSG_PAST        = "Future shipping date cannot be prior to today's date.";


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

        public static final String FIRST_NAME_NEW_VALUE               = "Jane";
        public static final String LAST_NAME_NEW_VALUE                = "Doe";
        public static final String SEQURITY_ANSW_NEW_VALUE            = "blah";
        public static final String BDAY_NEW_VALUE                     = "01/01/1990";
        public static final String GENDER_NEW_VALUE                   = "Female";
        public static final String SEQURITY_QUEST_NEW_VALUE           = "In what city or town did your mother and father meet?";

        public static final String COREG_FIRST_NAME_NEW_VALUE         = "Jack";
        public static final String COREG_LAST_NAME_NEW_VALUE          = "Someone";
        public static final String EVENT_DATE_NEW_VALUE               = "02/02/2020";
        public static final String NUMBER_GUESTS_NEW_VALUE            = "1234";

        public static final String ADDRESS_LINE_1_NEW_VALUE        = "New Address";
        public static final String ADDRESS_LINE_2_NEW_VALUE        = "new line #2";
        public static final String CITY_NEW_VALUE                  = "New City";
        public static final String STATE_NEW_VALUE                 = "Kansas";
        public static final String ZIP_NEW_VALUE                   = "11225";
        public static final String PHONE_NEW_VALUE                 = "(224) 565-5665";

        public static final String COREG_ADDRESS_LINE_1_NEW_VALUE   = "New Coregistrant's Address";
        public static final String COREG_ADDRESS_LINE_2_NEW_VALUE   = "Line two";
        public static final String COREG_CITY_NEW_VALUE             = "City Two";
        public static final String COREG_STATE_NEW_VALUE            = "Alaska";
        public static final String COREG_ZIP_NEW_VALUE              = "22564";
        public static final String COREG_PHONE_NEW_VALUE            = "(224) 784-8959";

        public static final String CURR_SHIP_ADDRESS_LINE_1_NEW_VALUE   = "New Current Address";
        public static final String CURR_SHIP_ADDRESS_LINE_2_NEW_VALUE   = "Line two current";
        public static final String CURR_SHIP_CITY_NEW_VALUE             = "City Two Current";
        public static final String CURR_SHIP_STATE_NEW_VALUE            = "Alabama";
        public static final String CURR_SHIP_ZIP_NEW_VALUE              = "22564";

        public static final String FUTURE_SHIP_ADDRESS_LINE_1_NEW_VALUE   = "New Future Address";
        public static final String FUTURE_SHIP_ADDRESS_LINE_2_NEW_VALUE   = "Line two future";
        public static final String FUTURE_SHIP_CITY_NEW_VALUE             = "City Two future";
        public static final String FUTURE_SHIP_STATE_NEW_VALUE            = "Armed Forces Europe";
        public static final String FUTURE_SHIP_ZIP_NEW_VALUE              = "11554";
        public static final String FUTURE_SHIP_DATE_NEW_VALUE             = "12/25/2021";


        public void expandMyAccountSection() {
                expandMyAccountSectionHeader.click();
        }

        public void expandEventDetailsSection() {
                expandEventDetailsSectionHeader.click();
        }

        public void expandAddressSection() {
                expandAddressSectionHeader.click();
        }

        public void expandShippingSection() {
                expandShippingSectionHeader.click();
        }
}
