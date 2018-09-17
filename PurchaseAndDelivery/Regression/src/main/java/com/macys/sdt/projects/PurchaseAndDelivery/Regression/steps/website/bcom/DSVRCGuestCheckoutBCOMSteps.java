package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.TokenCredentials;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ShippingSection;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DSVRCGuestCheckoutBCOMSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(DSVRCGuestCheckoutBCOMSteps.class);

    @Then("^I (should|should not) see shipping address, shipping method and gift options sections on shipping page$")
    public void iShouldSeeShippingAddressShippingMethodAndGiftOptionsSectionsOnShippingPage(String condition) {
        ShippingSection section = Navigate.get(ShippingSection.class);
        boolean expected = condition.equals("should");
        Assert.assertEquals(shippingError(expected, "First name"), expected, section.firstName.exists());
        Assert.assertEquals(shippingError(expected, "Shipping method"), expected, section.shippingMethodsSet.exists());
        Assert.assertEquals(shippingError(expected, "Gift options"), expected, section.giftOptionsSet.exists());
    }

    private String shippingError(boolean expected, String fieldName) {
        return String.format("ERROR - %s %sdisplayed in shipping section", fieldName, expected ? "not " : "");
    }

    @Then("^I should see VGC shipping information text in shipping page as:$")
    public void iShouldSeeVGCShippingInformationTextInShippingPageAs(List<String> text) {
        ShippingSection section = Navigate.get(ShippingSection.class);
        Assert.assertEquals("ERROR - VGC Shipping text not displayed in order confirmation page",
                section.shippingEgiftCardMessage.getText(), text.get(0));
        logger.info("VGC shipping information text is displayed on shipping page");
    }

    @When("^I create a new profile with an email has special characters$")
    public void i_create_a_new_profile_with_an_email_has_special_characters() throws Throwable {
        logger.info("Go to profile creation page!!");
        Clicks.click("home.goto_my_account_link");
        Clicks.click("sign_in.create_profile");
        logger.info("Fill data on profile creation page to create new profile");
        UserProfile customer = TestUsers.getCustomer(null);
        createProfile(customer);

    }



    public static void createProfile(UserProfile customer, boolean... edgeCase) {
        if (!onPage("create_profile")) {
            com.macys.sdt.framework.interactions.Navigate.visit("create_profile");
        }
        pausePageHangWatchDog();
        User user = customer.getUser();
        ProfileAddress profileAddress = user.getProfileAddress();
        if (!safari()) {
            stopPageLoad();
        }
        TextBoxes.typeTextbox("create_profile.verify_page", profileAddress.getFirstName());
        TextBoxes.typeTextbox("create_profile.last_name", profileAddress.getLastName());
        typeTextBoxIfPresent("create_profile.address_line_1", profileAddress.getAddressLine1());
        typeTextBoxIfPresent("create_profile.address_city", profileAddress.getCity());
            //Bloomingdales
            selectCustomDropDownIfPresent("create_profile.address_state_list", "create_profile.state_options", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
            if (Elements.elementPresent("create_profile.dob_month")) {
                if (edgeCase.length > 0 && edgeCase[0]) {
                    selectDropDownIfPresent("create_profile.dob_month", "February");
                } else {
                    DropDowns.selectByText("create_profile.dob_month", WordUtils.capitalize(user.getDateOfBirth(user.getDateOfBirth()).getMonth().name().toLowerCase()));
                }
            } else {
                if (edgeCase.length > 0 && edgeCase[0]) {
                    selectCustomDropDownIfPresent("create_profile.dob_month_list", "create_profile.dob_month_options", "February");
                } else {
                    selectCustomDropDownIfPresent("create_profile.dob_month_list", "create_profile.dob_month_options", WordUtils.capitalize(user.getDateOfBirth(user.getDateOfBirth()).getMonth().name().toLowerCase()));
                }
            }
            if (Elements.elementPresent("create_profile.dob_day")) {
                if (edgeCase.length > 0 && edgeCase[0]) {
                    DropDowns.selectByText("create_profile.dob_day", "31");//February doesn't have 31 days thereby making this date as invalid date.
                } else {
                    DropDowns.selectByText("create_profile.dob_day", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getDayOfMonth()));
                }
            } else {
                if (edgeCase.length > 0 && edgeCase[0]) {
                    selectCustomDropDownIfPresent("create_profile.dob_day_list", "create_profile.dob_day_options", "31");//February doesn't have 31 days thereby making this date as invalid date.
                } else {
                    selectCustomDropDownIfPresent("create_profile.dob_day_list", "create_profile.dob_day_options", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getDayOfMonth()));
                }
            }
            if (Elements.elementPresent("create_profile.dob_year")) {
                DropDowns.selectByText("create_profile.dob_year", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getYear()));
            } else {
                selectCustomDropDownIfPresent("create_profile.dob_year_list", "create_profile.dob_year_options", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getYear()));
            }
            if (Elements.elementPresent("create_profile.security_question")) {
                DropDowns.selectByText("create_profile.security_question", user.getUserPasswordHint().getQuestion());
            } else {
                selectCustomDropDownIfPresent("create_profile.security_question_list", "create_profile.security_question_options", user.getUserPasswordHint().getQuestion());
            }

        typeTextBoxIfPresent("create_profile.address_zip_code", String.valueOf(profileAddress.getZipCode()));
        if (macys()) {
            selectDropDownIfPresent("create_profile.gender", user.getGender());
        } else {
            Clicks.clickIfPresent("create_profile.gender_female");
        }
        if (prodEnv()) {
            TextBoxes.typeTextbox("create_profile.email", "prodSanity_" + generateRandomEmailwithinvalidID(16));
        }
        TextBoxes.typeTextbox("create_profile.password", user.getLoginCredentials().getPassword());

        if (Elements.elementPresent("create_profile.password_verify")) {
            TextBoxes.typeTextbox("create_profile.password_verify", user.getLoginCredentials().getPassword());
        }

        typeTextBoxIfPresent("create_profile.security_answer", user.getUserPasswordHint().getAnswer());

        if (edgeCase.length > 0 && edgeCase[1]) {
            typeTextBoxIfPresent("create_profile.phone_number", "");//missing phone
        } else if (edgeCase.length > 0 && edgeCase[2]) {
            typeTextBoxIfPresent("create_profile.phone_number", "500");//invalid/incomplete phone
        } else if (edgeCase.length > 0 && edgeCase[3]) {
            typeTextBoxIfPresent("create_profile.phone_number", "5555555555");//all same digits for phone
        } else {
            typeTextBoxIfPresent("create_profile.phone_number", profileAddress.getBestPhone());//valid phone
        }

        if (chrome()) {
            Clicks.click("create_profile.create_profile_button");
        } else {
            Clicks.javascriptClick("create_profile.create_profile_button");
        }
        if (prodEnv()) {
            Wait.secondsUntilElementNotPresent("create_profile.create_profile_button", 20);
        } else {
            Wait.secondsUntilElementNotPresent("create_profile.create_profile_button", RunConfig.timeout);
            resumePageHangWatchDog();
        }
        user.setTokenCredentials(new TokenCredentials());
        user.getTokenCredentials().setToken(Cookies.getSecureUserToken());
    }

    public static void typeTextBoxIfPresent(String elementName, String value) {
        if (Elements.elementPresent(elementName)) {
            TextBoxes.typeTextbox(elementName, value);
        }
    }

    public static void selectDropDownIfPresent(String elementName, String value) {
        if (Elements.elementPresent(elementName)) {
            DropDowns.selectByText(elementName, value);
        }
    }

    public static void selectCustomDropDownIfPresent(String elementName, String elementOptions, String value) {
        if (Elements.elementPresent(elementName)) {
            DropDowns.selectCustomText(elementName, elementOptions, value);
        }
    }


    public static String generateRandomEmailwithinvalidID(int length) {
        if (length == 0) {
            length = 16;
        }
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890";
        String email = RandomStringUtils.random(length, allowedChars);
        email = email.substring(0, email.length()) + "@blackhol.macys-.com";
        return email;
    }

    }
