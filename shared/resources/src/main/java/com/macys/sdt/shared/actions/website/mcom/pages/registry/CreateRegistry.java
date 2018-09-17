package com.macys.sdt.shared.actions.website.mcom.pages.registry;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.db.models.RegistryService;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CreateRegistry extends StepUtils {

    public static void fillRegistryUserDetails(UserProfile customer) {
        if (onPage("new_create_registry")) {
            createRegistryUser(customer);
        } else {
            pausePageHangWatchDog();
            closeFSRDialogIfAppeared();
            int date = TestUsers.generateRandomDateIndex();
            int year = TestUsers.generateRandomYearIndex();

            Wait.forPageReady();
            Registry registry = customer.getRegistry();
            User user = customer.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();
            if (!Elements.elementPresent("create_registry.event_type")) {
                Wait.secondsUntilElementPresent("create_registry.event_type_options", 5);
            }
            if (!Elements.getText("create_registry.event_type_options").equalsIgnoreCase(registry.getEventType())) {
                selectDropDownText("create_registry.event_type", registry.getEventType());
            }
            selectDropDownText("create_registry.event_month", registry.getEventMonth());
            selectDropDownText("create_registry.event_day", registry.getEventDay());
            selectDropDownText("create_registry.event_year", registry.getEventYear());

            if (macys()) {
                DropDowns.selectRandomValue("create_registry.event_location");
                DropDowns.selectRandomValue("create_registry.preferred_store_state");
            }
            TextBoxes.typeTextbox("create_registry.number_of_guests", registry.getNumberOfGuest());
            TextBoxes.typeTextbox("create_registry.first_name", profileAddress.getFirstName());
            TextBoxes.typeTextbox("create_registry.last_name", profileAddress.getLastName());

            TextBoxes.typeTextbox("create_registry.address_line_1", profileAddress.getAddressLine1());
            TextBoxes.typeTextbox("create_registry.address_line_2", "");
            TextBoxes.typeTextbox("create_registry.address_city", profileAddress.getCity());
            selectDropDownText("create_registry.address_state", (profileAddress.getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
            TextBoxes.typeTextbox("create_registry.address_zip_code", String.valueOf(profileAddress.getZipCode()));

            selectDropDownText("create_registry.security_question", ((edge() && bloomingdales()) ? (user.getUserPasswordHint().getQuestion() + " ") : user.getUserPasswordHint().getQuestion()));
            TextBoxes.typeTextbox("create_registry.security_answer", user.getUserPasswordHint().getAnswer());
            selectDropDownText("create_registry.dob_month", TestUsers.generateRandomMonth());
            selectDropDownIndex("create_registry.dob_day", date);
            selectDropDownValue("create_registry.dob_year", Integer.toString(year));

            TextBoxes.typeTextbox("create_registry.phone_area_code", profileAddress.getPhoneAreaCode());
            TextBoxes.typeTextbox("create_registry.phone_exchange", profileAddress.getPhoneExchange());
            TextBoxes.typeTextbox("create_registry.phone_subscriber", profileAddress.getPhoneSubscriber());

            TextBoxes.typeTextbox("create_registry.co_registrant_first_name", registry.getCoRegistrantFirstName());
            TextBoxes.typeTextbox("create_registry.co_registrant_last_name", registry.getCoRegistrantLastName());
            selectDropDownIndex("create_registry.preferred_store", 1);

            Clicks.javascriptClick("create_registry.create_registry_button");
            try {
                if (Elements.elementPresent("create_registry.close_overlay_chat")){
                    Clicks.click("create_registry.close_overlay_chat");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            resumePageHangWatchDog();
        }
    }

    public static void fillRegistryUserDetailsForExistingUser(UserProfile customer) {
        closeFSRDialogIfAppeared();

        Wait.forPageReady();
        pausePageHangWatchDog();
        Registry registry = customer.getRegistry();
        ProfileAddress profileAddress = customer.getUser().getProfileAddress();
        selectDropDownText("create_registry.event_type", registry.getEventType());
        selectDropDownText("create_registry.event_month", registry.getEventMonth());
        selectDropDownText("create_registry.event_day", registry.getEventDay());
        selectDropDownText("create_registry.event_year", registry.getEventYear());

        if (macys()) {
            DropDowns.selectRandomValue("create_registry.event_location");
            DropDowns.selectRandomValue("create_registry.preferred_store_state");
        }
        TextBoxes.typeTextbox("create_registry.address_line_1", profileAddress.getAddressLine1());
        TextBoxes.typeTextbox("create_registry.address_line_2", profileAddress.getAddressLine2());
        TextBoxes.typeTextbox("create_registry.address_city", profileAddress.getCity());
        selectDropDownText("create_registry.address_state", profileAddress.getState());
        TextBoxes.typeTextbox("create_registry.address_zip_code", String.valueOf(profileAddress.getZipCode()));

        TextBoxes.typeTextbox("create_registry.number_of_guests", registry.getNumberOfGuest());
        TextBoxes.typeTextbox("create_registry.co_registrant_first_name", registry.getCoRegistrantFirstName());
        TextBoxes.typeTextbox("create_registry.co_registrant_last_name", registry.getCoRegistrantLastName());
        TextBoxes.typeTextbox("create_registry.phone_area_code", profileAddress.getPhoneAreaCode());
        TextBoxes.typeTextbox("create_registry.phone_exchange", profileAddress.getPhoneExchange());
        TextBoxes.typeTextbox("create_registry.phone_subscriber", profileAddress.getPhoneSubscriber());

        selectDropDownIndex("create_registry.preferred_store", 1);

        Clicks.click("create_registry.create_registry_button");
        resumePageHangWatchDog();
        try {
            Wait.secondsUntilElementPresent("create_registry.close_overlay_chat", 5);
            Clicks.click(Elements.element("create_registry.close_overlay_chat"));
        } catch (Exception e) {
            //error just means there was no chat popup
        }
    }

    public static void closeFSRDialogIfAppeared() {
        try {
            Clicks.click(Elements.findElement(By.className("fsrCloseBtn")));
        } catch (Exception e) {
            //ignore errors
        }
    }

    public static void verifyRegistryIsCreatedInDB(String registryNo) {
        if (!RegistryService.registryExists(registryNo)) {
            Assert.fail("Could not find registry in DB");
        }
    }

    // Below method is used to fill registry user details on new create registry page: wgl/registry/create-registry

    public static void createRegistryUser(UserProfile customer) {
        closeFSRDialogIfAppeared();
        if (macys()) {
            fillProfileInfo(customer);
            fillRegistryPersonalInfo(customer, true);
            fillRegistryContactInfo(customer, true);
            fillRegistryShippingInfo();
            fillRegistryStoreInfo(customer);
            Wait.secondsUntilElementPresent("new_create_registry.create_registry_button", 10);
            Clicks.click("new_create_registry.create_registry_button");
            Wait.forPageReady();
            if (Elements.elementPresent("new_create_registry.error_message") && Elements.getText("new_create_registry.error_message").contains("technical error")) {
                Assert.fail("ERROR - ENV: Registry Services are down in environment!! --" + Elements.getText("new_create_registry.error_message"));
            }
        } else {
            if(onPage("registry_capture_email_page")){
                User user = customer.getUser();
                TextBoxes.typeTextbox("registry_capture_email_page.email", user.getProfileAddress().getEmail());
                TextBoxes.typeTextbox("registry_capture_email_page.password", user.getLoginCredentials().getPassword());
                Clicks.click("registry_capture_email_page.continue_button");
                CreateRegistry.fillRegistryUserDetails(customer);
            } else {
                fillAccountInfo(customer);
                fillEventDetails(customer);
                fillAddressDetails(customer);
                fillShippingDetails(customer);
                Clicks.click("new_create_registry.register_button");
                Wait.forPageReady();
            }
        }
    }

    public static void createRegistryUserForExistingUser(UserProfile customer) throws Throwable{
        closeFSRDialogIfAppeared();
        if (macys()) {
            fillRegistryAccountInfo(customer, false);
            fillRegistryPersonalInfo(customer, false);
            fillRegistryContactInfo(customer, false);
            fillRegistryShippingInfo();
            fillRegistryStoreInfo(customer);
            Clicks.javascriptClick(Elements.element("new_create_registry.create_registry_button"));
        } else {
            fillEventDetails(customer);
            fillAddressDetails(customer);
            fillShippingDetails(customer);
            Clicks.click("new_create_registry.register_button");
            Wait.forPageReady();
            Clicks.click("new_create_registry.manage_registry_button");
        }
    }

    private static void fillRegistryAccountInfo(UserProfile customer, boolean isNewUser) {
        if (isNewUser || Elements.elementPresent("new_create_registry.security_answer")) {
            TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
            Clicks.javascriptClick("new_create_registry.continue_button");
        }
    }

    private static boolean isSecurityQuestionEnabled(){
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        return pageSrcVal.contains("noSecurityQuestionEnabled: false");
    }

    private static void fillProfileInfo(UserProfile customer) {
        if (!Wait.secondsUntilElementPresent("new_create_registry.new_user_email", 6)) {
            Navigate.browserRefresh();
        }
        Wait.forPageReady();
        TextBoxes.typeTextbox("new_create_registry.new_user_email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("new_create_registry.new_user_password", customer.getUser().getLoginCredentials().getPassword());
        TextBoxes.typeTextbox("new_create_registry.new_user_password_verify", customer.getUser().getLoginCredentials().getPassword());
        DropDowns.selectByText("new_create_registry.security_question", customer.getUser().getUserPasswordHint().getQuestion());
        TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
        if(bloomingdales()){
            Clicks.click("new_create_registry.register_button");
        }else
            Clicks.click("new_create_registry.continue_button");
        Wait.forPageReady();
    }

    private static void fillRegistryPersonalInfo(UserProfile customer, boolean isNewUser) {
        try {
            User user = customer.getUser();
            ProfileAddress profileAddress = user.getProfileAddress();
            int dobDate = TestUsers.generateRandomDateIndex();
            int dobYear = new Random().nextInt(52) + 1950;
            Calendar eventDateInstance = Calendar.getInstance();
            Calendar dobInstance = Calendar.getInstance();
            DecimalFormat decimalFormat = new DecimalFormat("00");
            Registry registry = customer.getRegistry();
            eventDateInstance.setTime(new SimpleDateFormat("dd.MMMMM.yyyy").parse(registry.getEventDay() + "." + registry.getEventMonth() + "." + registry.getEventYear()));
            dobInstance.setTime(new SimpleDateFormat("dd.MMMMM.yyyy").parse(String.valueOf(dobDate) + "." + WordUtils.capitalize(user.getDateOfBirth(user.getDateOfBirth()).getMonth().name().toLowerCase()) + "." + String.valueOf(dobYear)));
            String fullDob = decimalFormat.format(dobInstance.get(Calendar.MONTH) + 1) + decimalFormat.format(dobInstance.get(Calendar.DAY_OF_MONTH)) + dobInstance.get(Calendar.YEAR);
            String fullEventDate = decimalFormat.format(eventDateInstance.get(Calendar.MONTH) + 1) + decimalFormat.format(eventDateInstance.get(Calendar.DAY_OF_MONTH)) + eventDateInstance.get(Calendar.YEAR);
            if (isNewUser) {
                TextBoxes.typeTextbox("new_create_registry.first_name", profileAddress.getFirstName());
                TextBoxes.typeTextbox("new_create_registry.last_name", profileAddress.getLastName());
                TextBoxes.typeTextbox("new_create_registry.date_of_birth", fullDob);
            }
            TextBoxes.typeTextbox("new_create_registry.co_registrant_first_name", registry.getCoRegistrantFirstName());
            TextBoxes.typeTextbox("new_create_registry.co_registrant_last_name", registry.getCoRegistrantLastName());
            TextBoxes.typeTextbox("new_create_registry.event_date", fullEventDate);
            TextBoxes.typeTextbox("new_create_registry.number_of_guests", registry.getNumberOfGuest());
            Clicks.selectCheckbox("new_create_registry.public_profile");
            Clicks.click("new_create_registry.personal_info_continue_button");
        } catch (ParseException e) {
            Assert.fail("Unable to fill registry personal info: " + e);
        }
    }

    private static void fillRegistryContactInfo(UserProfile customer, boolean isNewUser) {
        ProfileAddress profileAddress = customer.getUser().getProfileAddress();
        TextBoxes.typeTextbox("new_create_registry.address_line_1", profileAddress.getAddressLine1());
        TextBoxes.typeTextbox("new_create_registry.address_line_2", (profileAddress.getAddressLine2() == null ? "" : profileAddress.getAddressLine2()));
        TextBoxes.typeTextbox("new_create_registry.address_city", profileAddress.getCity());
        DropDowns.selectByValue("new_create_registry.address_state", (profileAddress.getState().length() == 2 ? profileAddress.getState() : AbbreviationHelper.getStateAbbreviation(profileAddress.getState())));
        TextBoxes.typeTextbox("new_create_registry.address_zip_code", String.valueOf(profileAddress.getZipCode()));
        TextBoxes.typeTextbox("new_create_registry.phone", profileAddress.getBestPhone());
        if (edge()) {
            Clicks.javascriptClick("new_create_registry.contact_info_continue_button");
        } else {
            Clicks.click("new_create_registry.contact_info_continue_button");
        }
    }

    private static void fillRegistryShippingInfo() {
        Clicks.selectCheckbox("new_create_registry.go_green_checkbox");
        if (edge()) {
            Clicks.javascriptClick("new_create_registry.shipping_info_continue_button");
        } else {
            Clicks.click("new_create_registry.shipping_info_continue_button");
        }
    }

    private static void fillRegistryStoreInfo(UserProfile customer) {
        DropDowns.selectByText("new_create_registry.preferred_store_state", customer.getRegistry().getPreferredStoreState());
        Wait.secondsUntilElementPresent("new_create_registry.preferred_store", 10);
        DropDowns.selectByIndex("new_create_registry.preferred_store", 1);
    }

    public static void fillAccountInfo(UserProfile customer) {
        Wait.secondsUntilElementPresent("new_create_registry.next_button", 10);
        Wait.forPageReady();
        if (bloomingdales()) {

            TextBoxes.typeTextbox("new_create_registry.first_name", customer.getUser().getProfileAddress().getFirstName());
            TextBoxes.typeTextbox("new_create_registry.last_name", customer.getUser().getProfileAddress().getLastName());
            TextBoxes.typeTextbox("new_create_registry.email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("new_create_registry.password", customer.getUser().getLoginCredentials().getPassword());
            TextBoxes.typeTextbox("new_create_registry.verify_password", customer.getUser().getLoginCredentials().getPassword());
            if(isSecurityQuestionEnabled()) {
                selectDropDownValue("new_create_registry.security_question", customer.getUser().getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("new_create_registry.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
            }
            String[] dob = customer.getUser().getDateOfBirth().split("-");
            TextBoxes.typeTextbox("new_create_registry.date_of_birth", dob[1] + "/" + dob[2] + "/" + dob[0]);
            selectDropDownValue("new_create_registry.gender", customer.getUser().getGender());
            Clicks.click("new_create_registry.next_button");
        }
    }

    public static void fillEventDetails(UserProfile customer) {
        Wait.untilElementPresent("new_create_registry.co_registrant_first_name");
        TextBoxes.typeTextbox("new_create_registry.co_registrant_first_name", customer.getRegistry().getCoRegistrantFirstName());
        TextBoxes.typeTextbox("new_create_registry.co_registrant_last_name", customer.getRegistry().getCoRegistrantLastName());
        selectDropDownValue("new_create_registry.event_type", customer.getRegistry().getEventType());
        TextBoxes.typeTextbox("new_create_registry.event_date", customer.getRegistry().getEvent().getDate());
        TextBoxes.typeTextbox("new_create_registry.guests_count", String.valueOf(customer.getRegistry().getEvent().getNoOfGuests()));
        customer.getRegistry().setPreferredStore("NY - 59th Street");
        selectDropDownText("new_create_registry.store_location", "NY - 59th Street");
        Clicks.click("new_create_registry.next_button");
    }

    public static void fillAddressDetails(UserProfile customer) {
        Wait.untilElementPresent("new_create_registry.registrant_address_line_1");
        TextBoxes.typeTextbox("new_create_registry.registrant_address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("new_create_registry.registrant_address_line_2", customer.getUser().getProfileAddress().getAddressLine2());
        TextBoxes.typeTextbox("new_create_registry.registrant_address_city", customer.getUser().getProfileAddress().getCity());
        selectDropDownText("new_create_registry.registrant_address_state",
                (customer.getUser().getProfileAddress().getState().length() == 2 ? AbbreviationHelper.translateStateAbbreviation(customer.getUser().getProfileAddress().getState()) : customer.getUser().getProfileAddress().getState()));
        TextBoxes.typeTextbox("new_create_registry.registrant_address_zip_code", String.valueOf(customer.getUser().getProfileAddress().getZipCode()));
        TextBoxes.typeTextbox("new_create_registry.registrant_address_phone", customer.getUser().getProfileAddress().getBestPhone());
        Clicks.click("new_create_registry.next_button");
    }

    public static void fillShippingDetails(UserProfile customer) {
        Wait.untilElementPresent("new_create_registry.register_button");
    }

    public static void selectDropDownText(String element, String text) {
        if (Elements.elementPresent(element)) {
            DropDowns.selectByText(element, text);
        } else {
            DropDowns.selectCustomText(element + "_options", element + "_list", text);
        }
    }

    public static void selectDropDownValue(String element, String text) {
        if (Elements.elementPresent(element)) {
            DropDowns.selectByValue(element, text);
        } else {
            if (edge()){
                final String dummyText = text;
                if (DropDowns.getAllCustomValues(element + "_options", element + "_list").stream().anyMatch(e -> e.contains(dummyText)))
                    text = DropDowns.getAllCustomValues(element + "_options", element + "_list").stream()
                        .filter(e -> e.contains(dummyText)).findFirst().get();
            }
            DropDowns.selectCustomText(element + "_options", element + "_list", text);
        }
    }

    public static void selectDropDownIndex(String element, int index) {
        if (Elements.elementPresent(element)) {
            DropDowns.selectByIndex(element, index);
        } else {
            DropDowns.selectCustomValue(element + "_options", element + "_list", index);
        }
    }

    public static void goBackToMacysFromRegistryWelcomePage() {
        Wait.forPageReady();
        Clicks.click("home.goto_wedding_registry");
    }
}
