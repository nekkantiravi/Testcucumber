package com.macys.sdt.shared.actions.MEW.pages;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CreateRegistry extends StepUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(CreateRegistry.class);
    /**
     * Fills in Registry details on old create registry page and clicks on create registry button
     *
     * @param customer instance of UserProfile model
     */
    public static void fillRegistryUserDetails(UserProfile customer) {
        closeFSRDialogIfAppeared();
        closeChatAlert();
        int date = TestUsers.generateRandomDateIndex();
        int year = TestUsers.generateRandomYearIndex();

        Wait.forPageReady();
        Registry registry = customer.getRegistry();
        DropDowns.selectByText("create_registry.event_type", registry.getEventType());
        DropDowns.selectByText("create_registry.event_month", registry.getEventMonth());
        DropDowns.selectByText("create_registry.event_day", registry.getEventDay().replaceAll("^0", ""));
        DropDowns.selectByText("create_registry.event_year", registry.getEventYear());

        if (macys()) {
            DropDowns.selectRandomValue("create_registry.event_location");
            DropDowns.selectRandomValue("create_registry.preferred_store_state");
        }
        TextBoxes.typeTextbox("create_registry.number_of_guests", registry.getNumberOfGuest());
        TextBoxes.typeTextbox("create_registry.first_name", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("create_registry.last_name", customer.getUser().getProfileAddress().getLastName());

        TextBoxes.typeTextbox("create_registry.address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("create_registry.address_line_2", "");
        TextBoxes.typeTextbox("create_registry.address_city", customer.getUser().getProfileAddress().getCity());
        DropDowns.selectByValue("create_registry.address_state", AbbreviationHelper.getStateAbbreviation(customer.getUser().getProfileAddress().getState()));
        TextBoxes.typeTextbox("create_registry.address_zip_code", String.valueOf(customer.getUser().getProfileAddress().getZipCode()));

        DropDowns.selectByText("create_registry.security_question", customer.getUser().getUserPasswordHint().getQuestion());
        TextBoxes.typeTextbox("create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
        DropDowns.selectByText("create_registry.dob_month", TestUsers.generateRandomMonth());
        DropDowns.selectByIndex("create_registry.dob_day", date);
        DropDowns.selectByValue("create_registry.dob_year", Integer.toString(year));

        TextBoxes.typeTextbox("create_registry.phone_area_code", customer.getUser().getProfileAddress().getPhoneAreaCode());
        TextBoxes.typeTextbox("create_registry.phone_exchange", customer.getUser().getProfileAddress().getPhoneExchange());
        TextBoxes.typeTextbox("create_registry.phone_subscriber", customer.getUser().getProfileAddress().getPhoneSubscriber());

        TextBoxes.typeTextbox("create_registry.co_registrant_first_name", registry.getCoRegistrantFirstName());
        TextBoxes.typeTextbox("create_registry.co_registrant_last_name", registry.getCoRegistrantLastName());
        DropDowns.selectByIndex("create_registry.preferred_store", 1);

        Clicks.javascriptClick("create_registry.create_registry_button");
        Clicks.clickIfPresent("create_registry.close_overlay_chat");
    }

    /**
     * Fills in Registry details on old create registry page for existing user and clicks on create registry button
     *
     * @param customer instance of UserProfile model
     */
    public static void fillRegistryUserDetailsForExistingUser(UserProfile customer) {
        closeFSRDialogIfAppeared();
        closeChatAlert();
        if (onPage("registry_manager")) {
            logger.info("registry already exists!");
            return;
        }

        Wait.forPageReady();
        Registry registry = customer.getRegistry();
        ProfileAddress profileAddress = customer.getUser().getProfileAddress();
        DropDowns.selectByText("create_registry.event_type", registry.getEventType());
        DropDowns.selectByText("create_registry.event_month", registry.getEventMonth());
        DropDowns.selectByText("create_registry.event_day", registry.getEventDay());
        DropDowns.selectByText("create_registry.event_year", registry.getEventYear());

        if (macys()) {
            DropDowns.selectRandomValue("create_registry.event_location");
            DropDowns.selectRandomValue("create_registry.preferred_store_state");
        }

        if (MEW() && bloomingdales()) {
            TextBoxes.typeTextbox("create_registry.address_line_1", profileAddress.getAddressLine1());
            TextBoxes.typeTextbox("create_registry.address_line_2", "");
            TextBoxes.typeTextbox("create_registry.address_city", profileAddress.getCity());
            DropDowns.selectByValue("create_registry.address_state", AbbreviationHelper.getStateAbbreviation(profileAddress.getState()));
            TextBoxes.typeTextbox("create_registry.address_zip_code", String.valueOf(profileAddress.getZipCode()));
        }

        TextBoxes.typeTextbox("create_registry.number_of_guests", registry.getNumberOfGuest());

        TextBoxes.typeTextbox("create_registry.co_registrant_first_name", registry.getCoRegistrantFirstName());
        TextBoxes.typeTextbox("create_registry.co_registrant_last_name", registry.getCoRegistrantLastName());

        TextBoxes.typeTextbox("create_registry.phone_area_code", profileAddress.getPhoneAreaCode());
        TextBoxes.typeTextbox("create_registry.phone_exchange", profileAddress.getPhoneExchange());
        TextBoxes.typeTextbox("create_registry.phone_subscriber", profileAddress.getPhoneSubscriber());

        DropDowns.selectByIndex("create_registry.preferred_store", 1);

        Clicks.click("create_registry.create_registry_button");
        Clicks.clickIfPresent("create_registry.close_overlay_chat");
    }

    /**
     * Close FSR dialog if present
     */
    public static void closeFSRDialogIfAppeared() {
        Clicks.clickIfPresent(By.className("fsrCloseBtn"));
    }

    /**
     * Creates a new registry user
     *
     * @param customer instance of UserProfile model
     */
    public static void createRegistryUser(UserProfile customer) {
        if (macys()) {
            closeChatAlert();
            closeFSRDialogIfAppeared();
            fillProfileInfo(customer);
            fillRegistryPersonalInfo(customer, true);
            fillRegistryContactInfo(customer, true);
            fillRegistryShippingInfo();
            fillRegistryStoreInfo(customer);
            Clicks.javascriptClick("new_create_registry.create_registry_button");
        } else {
            fillAccountDetails(customer);
            fillEventDetails(customer);
            fillAddressDetails(customer);
            Clicks.click("new_create_registry.create_registry_button");
        }
    }

    /**
     * Creates a new registry for existing user
     *
     * @param customer instance of UserProfile model
     */
    public static void createRegistryUserForExistingUser(UserProfile customer) {
        if (macys()) {
            closeChatAlert();
            closeFSRDialogIfAppeared();
            fillRegistryAccountInfo(customer, false);
            fillRegistryPersonalInfo(customer, false);
            fillRegistryContactInfo(customer, false);
            fillRegistryShippingInfo();
            fillRegistryStoreInfo(customer);
            Clicks.javascriptClick("new_create_registry.create_registry_button");
        } else {
            fillEventDetails(customer);
            fillAddressDetails(customer);
            Clicks.click("new_create_registry.create_registry_button");
        }
    }

    private static void fillRegistryAccountInfo(UserProfile customer, boolean isNewUser) {
        TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
        Clicks.javascriptClick("new_create_registry.continue_button");
    }

    private static void fillProfileInfo(UserProfile customer) {
        TextBoxes.typeTextbox("new_create_registry.new_user_email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("new_create_registry.new_user_password", customer.getUser().getLoginCredentials().getPassword());
        TextBoxes.typeTextbox("new_create_registry.new_user_password_verify", customer.getUser().getLoginCredentials().getPassword());
        DropDowns.selectByText("new_create_registry.security_question", customer.getUser().getUserPasswordHint().getQuestion());
        Wait.untilElementPresent("new_create_registry.security_answer");
        TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
        Clicks.javascriptClick("new_create_registry.continue_button");
    }

    private static void fillRegistryPersonalInfo(UserProfile customer, boolean isNewUser) {
        Registry registry = customer.getRegistry();
        ProfileAddress address = customer.getUser().getProfileAddress();
        try {
            int dobDate = TestUsers.generateRandomDateIndex();
            int dobYear = new Random().nextInt(52) + 1950;
            Calendar eventDateInstance = Calendar.getInstance();
            Calendar dobInstance = Calendar.getInstance();
            DecimalFormat decimalFormat = new DecimalFormat("00");
            eventDateInstance.setTime(new SimpleDateFormat("dd.MMMMM.yyyy").parse(registry.getEventDay() + "." + registry.getEventMonth() + "." + registry.getEventYear()));
            dobInstance.setTime(new SimpleDateFormat("dd.MMMMM.yyyy").parse(String.valueOf(dobDate) + "." + registry.getEventMonth() + "." + String.valueOf(dobYear)));
            String fullDob = decimalFormat.format(dobInstance.get(Calendar.MONTH) + 1) + decimalFormat.format(dobInstance.get(Calendar.DAY_OF_MONTH)) + dobInstance.get(Calendar.YEAR);
            String fullEventDate = decimalFormat.format(eventDateInstance.get(Calendar.MONTH) + 1) + decimalFormat.format(eventDateInstance.get(Calendar.DAY_OF_MONTH)) + eventDateInstance.get(Calendar.YEAR);
            if (isNewUser) {
                TextBoxes.typeTextbox("new_create_registry.first_name", address.getFirstName());
                TextBoxes.typeTextbox("new_create_registry.last_name", address.getLastName());
                TextBoxes.typeTextbox("new_create_registry.date_of_birth", fullDob);
            }
            TextBoxes.typeTextbox("new_create_registry.co_registrant_first_name", registry.getCoRegistrantFirstName());
            TextBoxes.typeTextbox("new_create_registry.co_registrant_last_name", registry.getCoRegistrantLastName());
            TextBoxes.typeTextbox("new_create_registry.event_date", fullEventDate);
            TextBoxes.typeTextbox("new_create_registry.number_of_guests", registry.getNumberOfGuest());
            Clicks.selectCheckbox("new_create_registry.public_profile");
            Clicks.javascriptClick("new_create_registry.personal_info_continue_button");
        } catch (ParseException e) {
            Assert.fail("Unable to fill registry personal info: " + e);
        }
    }

    private static void fillRegistryContactInfo(UserProfile customer, boolean isNewUser) {
        ProfileAddress address = customer.getUser().getProfileAddress();
        String phoneNumber = (address.getPhoneAreaCode() + address.getPhoneExchange() + address.getPhoneSubscriber());
        TextBoxes.typeTextbox("new_create_registry.address_line_1", address.getAddressLine1());
        TextBoxes.typeTextbox("new_create_registry.address_line_2", address.getAddressLine2());
        TextBoxes.typeTextbox("new_create_registry.address_city", address.getCity());
        DropDowns.selectByValue("new_create_registry.address_state", AbbreviationHelper.getStateAbbreviation(address.getState()));
        TextBoxes.typeTextbox("new_create_registry.address_zip_code", address.getZipCode());
        TextBoxes.typeTextbox(Elements.element("new_create_registry.phone"), phoneNumber);
        Clicks.click("new_create_registry.contact_info_continue_button");
    }

    private static void fillRegistryShippingInfo() {
        Clicks.javascriptClick("new_create_registry.shipping_info_continue_button");
    }

    private static void fillRegistryStoreInfo(UserProfile customer) {
        DropDowns.selectByText("new_create_registry.preferred_store_state", customer.getRegistry().getPreferredStoreState());
        // After selecting store state it takes some time to load  preferred store options
        Wait.until(() -> DropDowns.getAllValues("new_create_registry.preferred_store").size() > 1);
        DropDowns.selectByIndex("new_create_registry.preferred_store", 1);
    }

    private static void fillAccountDetails(UserProfile customer) {
        TextBoxes.typeTextbox("new_create_registry.first_name", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("new_create_registry.last_name", customer.getUser().getProfileAddress().getLastName());
        TextBoxes.typeTextbox("new_create_registry.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("new_create_registry.password", customer.getUser().getLoginCredentials().getPassword());
        TextBoxes.typeTextbox("new_create_registry.verify_password", customer.getUser().getLoginCredentials().getPassword());
        //DropDowns.selectByText("new_create_registry.security_question", customer.getUser().getUserPasswordHint().getQuestion());
        //TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
        String[] dob = customer.getUser().getDateOfBirth().split("-");
        TextBoxes.typeTextbox("new_create_registry.date_of_birth", dob[1] + "/" + dob[2] + "/" + dob[0]);
        Clicks.click("new_create_registry.continue_button");
    }

    private static void fillEventDetails(UserProfile customer) {
        TextBoxes.typeTextbox("new_create_registry.co_registrant_first_name", customer.getRegistry().getCoRegistrantFirstName());
        TextBoxes.typeTextbox("new_create_registry.co_registrant_last_name", customer.getRegistry().getCoRegistrantLastName());
        DropDowns.selectByIndex("new_create_registry.preferred_store", 1);
        TextBoxes.typeTextbox("new_create_registry.event_date", customer.getRegistry().getEvent().getDate());
        TextBoxes.typeTextbox("new_create_registry.number_of_guests", customer.getRegistry().getNumberOfGuest());
        Clicks.click("new_create_registry.continue_button");
    }

    private static void fillAddressDetails(UserProfile customer) {
        TextBoxes.typeTextbox("new_create_registry.address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("new_create_registry.address_line_2", customer.getUser().getProfileAddress().getAddressLine2());
        TextBoxes.typeTextbox("new_create_registry.address_city", customer.getUser().getProfileAddress().getCity());
        DropDowns.selectByValue("new_create_registry.address_state", AbbreviationHelper.getStateAbbreviation(customer.getUser().getProfileAddress().getState()));
        TextBoxes.typeTextbox("new_create_registry.address_zip_code", customer.getUser().getProfileAddress().getZipCode());
        TextBoxes.typeTextbox("new_create_registry.phone_number", customer.getUser().getProfileAddress().getBestPhone());
        Clicks.click("new_create_registry.continue_button");
    }

}
