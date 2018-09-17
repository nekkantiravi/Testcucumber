package com.macys.sdt.shared.actions.MEW.pages;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;

public class LoyaltyEnrollment extends StepUtils {

    /**
     * Enrolls in the loyalty program as a signed in user
     *
     * @param customer instance of UserProfile model
     */
    public void signedInUserLoyaltyEnrollment(UserProfile customer) {
        ProfileAddress profileAddress = customer.getUser().getProfileAddress();
        TextBoxes.typeTextbox("loyalty_enrollment.address_line_1", profileAddress.getAddressLine1());
        TextBoxes.typeTextbox("loyalty_enrollment.address_line_2", profileAddress.getAddressLine2());
        TextBoxes.typeTextbox("loyalty_enrollment.address_city", profileAddress.getCity());
        DropDowns.selectByValue("loyalty_enrollment.address_state", AbbreviationHelper.getStateAbbreviation(profileAddress.getState()));
        TextBoxes.typeTextbox("loyalty_enrollment.address_zip_code", profileAddress.getZipCode());
        TextBoxes.typeTextbox("loyalty_enrollment.phone_number", profileAddress.getBestPhone());
        Clicks.javascriptClick("loyalty_enrollment.goto_terms_and_conditions");
        Wait.secondsUntilElementPresent("loyalty_enrollment.accept_terms_and_conditions", 20);
        Clicks.javascriptClick("loyalty_enrollment.accept_terms_and_conditions");
        Wait.until(() -> Elements.getText("loyalty_enrollment.terms_and_conditions_status").equals("Accepted"));
        Clicks.javascriptClick("loyalty_enrollment.submit_btn");
        Wait.untilElementPresent("loyalty_enrollment_confirmation.loyalty_number");
    }

    /**
     * Enrolls in the loyalty program as a guest user
     *
     * @param customer instance of UserProfile model
     */
    public void guestUserLoyaltyEnrollmentMobileWebsite(UserProfile customer) {
        User user = customer.getUser();
        ProfileAddress profileAddress = user.getProfileAddress();

        Wait.forPageReady();
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.first_name"), profileAddress.getFirstName());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.last_name"), profileAddress.getLastName());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.address_line_1"), profileAddress.getAddressLine1());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.address_line_2"), profileAddress.getAddressLine2());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.address_city"), profileAddress.getCity());
        DropDowns.selectByIndex("loyalty_enrollment.address_state", 5);
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.address_zip_code"), String.valueOf(profileAddress.getZipCode()));
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.email"), profileAddress.getEmail());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.verify_email"), profileAddress.getEmail());
        DropDowns.selectByIndex("loyalty_enrollment.dob_month", 5);
        DropDowns.selectByIndex("loyalty_enrollment.dob_day", 5);
        DropDowns.selectByIndex("loyalty_enrollment.dob_year", 23);
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.phone_number"), profileAddress.getBestPhone());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.password"), user.getLoginCredentials().getPassword());
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.verify_password"), user.getLoginCredentials().getPassword());
        DropDowns.selectByIndex("loyalty_enrollment.security_question", 5);
        TextBoxes.typeTextbox(Elements.element("loyalty_enrollment.security_answer"), user.getUserPasswordHint().getAnswer());
        Clicks.javascriptClick("loyalty_enrollment.goto_terms_and_conditions");
        Wait.secondsUntilElementPresent("loyalty_enrollment.accept_terms_and_conditions", 20);
        Clicks.javascriptClick("loyalty_enrollment.accept_terms_and_conditions");
        Wait.until(() -> Elements.getText("loyalty_enrollment.terms_and_conditions_status").equals("Accepted"));
        Clicks.javascriptClick("loyalty_enrollment.submit_btn");
        Wait.untilElementPresent("loyalty_enrollment_confirmation.loyalty_number");
    }
}