package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.macys.sdt.framework.interactions.Elements.getText;
import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;

/**
 * Created by yh00136 on 8/15/2017.
 */
public class CreateProfileStep extends StepUtils{
    private static final Logger logger = LoggerFactory.getLogger(CreateProfileStep.class);

    @Given("^I am on checkout payment page I should see the create profile checkbox$")
    public void iAmOnCheckoutPaymentPageIShouldSeeTheCreateProfileCheckbox() throws Throwable {
        Navigate.scrollPage(0,400);
        Assert.assertTrue("Create profile checkbox not exists in guest checkout page",Elements.elementPresent("responsive_checkout.create_profile_checkbox"));
    }

    @When("^I checked create profile checkbox I should see the create profile form$")
    public void iCheckedCreateProfileCheckboxIShouldSeeTheCreateProfileForm() throws Throwable {
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        TextBoxes.typeTextbox("responsive_checkout.create_profile_email", user.getProfileAddress().getEmail());
        if(Elements.elementPresent("responsive_checkout.create_profile_checkbox")){
            Clicks.selectCheckbox(Elements.element("responsive_checkout.create_profile_checkbox"));
            Wait.secondsUntilElementPresent("responsive_checkout.create_profile_pwd", 20);
            Assert.assertTrue("Create profile password text box not exists in guest checkout page",Elements.elementPresent("responsive_checkout.create_profile_pwd"));
            Assert.assertTrue("Create profile month dropdown not exists in guest checkout page",Elements.elementPresent("responsive_checkout.create_profile_month"));
            Assert.assertTrue("Create profile day dropdown not exists in guest checkout page",Elements.elementPresent("responsive_checkout.create_profile_day"));
            Assert.assertTrue("Create profile year dropdown not exists in guest checkout page",Elements.elementPresent("responsive_checkout.create_profile_year"));
        }else
            logger.info("Failed to find create profile checkbox: responsive_checkout.create_profile_checkbox");
    }

    @And("^I verify inline error message while creating profile on checkout payment info page$")
    public void iVerifyInlineErrorMessageWhileCreatingProfileOnCheckoutPaymentInfoPage(Map<String, String> error) throws Throwable {
        Assert.assertTrue("Failed to validate empty password text box inline error in guest checkout page", Elements.elementPresent("responsive_checkout.create_profile_pwd_error"));
        String strPwdError = getText("responsive_checkout.create_profile_pwd_error");
        Assert.assertEquals("Failed to validate empty password inline error msg in guest checkout page", strPwdError, error.get("error_pwd"));
    }

    @And("^I update email with already exist mail Id$")
    public void iUpdateEmailWithAlreadyExistMailId(Map<String, String> error) throws Throwable {
        String expectedError = error.get("error_email");
        UserProfile user = new UserProfile();
        user = UserProfile.getDefaultProfile();
        UserProfileService.createUserProfile(user);
        TextBoxes.typeTextbox("responsive_checkout.create_profile_email", user.getUser().getProfileAddress().getEmail());
        if(Elements.elementPresent("responsive_checkout.create_profile_checkbox")) {
            Clicks.selectCheckbox(Elements.element("responsive_checkout.create_profile_checkbox"));
            Wait.secondsUntilElementPresent("responsive_checkout.create_profile_email_exist_error", 20);
            Assert.assertTrue("Failed to validate email already exist inline error in guest checkout page", Elements.elementPresent("responsive_checkout.create_profile_email_exist_error"));
            String strPwdError = getText("responsive_checkout.create_profile_email_exist_error");
            Assert.assertEquals("Failed to validate email already exist inline error msg in guest checkout page", strPwdError, expectedError);
        }else
            logger.info("Failed to find create profile checkbox: responsive_checkout.create_profile_checkbox");
    }

    @And("^I enter all required data on create profile form$")
    public void iEnterAllRequiredDataOnCreateProfileForm() throws Throwable {
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        TextBoxes.typeTextbox("responsive_checkout.create_profile_pwd", user.getLoginCredentials().getPassword());
        String[] dateOfBirth = user.getDateOfBirth().split("-");
        if (macys()) {
            if (dateOfBirth[1].matches("(?i)month|Month")) {
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_year", String.valueOf(dateOfBirth[0]));
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_month", String.valueOf(dateOfBirth[1]));
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_day", String.valueOf(dateOfBirth[2]));
            } else if (dateOfBirth[2].matches("(?i)day|Day")) {
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_year", String.valueOf(dateOfBirth[0]));
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_month", WordUtils.capitalize(new java.text.DateFormatSymbols().getMonths()[Integer.valueOf(dateOfBirth[1]) - 1]));
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_day", String.valueOf(dateOfBirth[2]));
            } else if (dateOfBirth[0].matches("(?i)year|Year")) {
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_year", String.valueOf(dateOfBirth[0]));
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_month", WordUtils.capitalize(new java.text.DateFormatSymbols().getMonths()[Integer.valueOf(dateOfBirth[1]) - 1]));
                CreateProfile.selectDropDownIfPresent("responsive_checkout.create_profile_day", String.valueOf(dateOfBirth[2]));
            } else {
                CreateProfile.selectDropDownIfPresent("create_profile.create_profile_month", WordUtils.capitalize(user.getDateOfBirth(user.getDateOfBirth()).getMonth().name().toLowerCase()));
                CreateProfile.selectDropDownIfPresent("create_profile.create_profile_day", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getDayOfMonth()));
                CreateProfile.selectDropDownIfPresent("create_profile.create_profile_year", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getYear()));
            }
            Navigate.scrollPage(0, 400);
        } else {
            //Bloomingdales
            Random rand = new Random();
            WebElement monthSelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(0);
            monthSelector.click();
            List <WebElement> options = Elements.findElements("responsive_checkout.create_profile_date_options");
            options.get(rand.nextInt(options.size()-1)).click();
            WebElement daySelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(1);
            daySelector.click();
            options = Elements.findElements("responsive_checkout.create_profile_date_options");
            options.get(rand.nextInt(options.size()-1)).click();
            WebElement yearSelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(2);
            yearSelector.click();
            options = Elements.findElements("responsive_checkout.create_profile_date_options");
            options.get(rand.nextInt(options.size()-2)).click();
        }
    }

    @Then("^I modify the payment info with wrong data$")
    public void iModifyThePaymentInfoWithWrongData() throws Throwable {
        CreditCard creditCard= TestUsers.getValidVisaCreditCard();
        //CreditCard creditCard = MyAccount.getValidCreditCard("Macys");
        if (Wait.untilElementPresent("responsive_checkout.card_number")) {
            DropDowns.selectByText("responsive_checkout.card_type", creditCard.getCardType().name);
            TextBoxes.typeTextbox("responsive_checkout.card_number", creditCard.getCardNumber()+1);
            TextBoxes.typeTextbox("responsive_checkout.security_code", creditCard.getSecurityCode());
        }
    }

    @And("^I validated profile created on RC payment page$")
    public void iValidatedProfileCreatedOnRCPaymentPage(Map<String, String> msg) throws Throwable {
        Wait.secondsUntilElementPresent("responsive_checkout.create_profile_success_msg", 20);
        String strActualMsg = getText("responsive_checkout.create_profile_success_msg");
        Assert.assertEquals("Failed to validate eamil allready exist inline error msg in guest checkout page", strActualMsg, msg.get("create_profile_msg"));
    }

    @And("^I see the create profile marketing banner and create profile form$")
    public void iSeeTheCreateProfileMarketingBannerAndCreateProfileForm() throws Throwable {
        Navigate.scrollPage(0,400);
        Assert.assertTrue("Create profile marketing banner not exists on order confirnation page",Elements.elementPresent("order_confirmation.create_profile_banner"));
        Assert.assertTrue("Create profile form not exists on order confirnation page",Elements.elementPresent("order_confirmation.create_profile_pwd"));
        Assert.assertTrue("Create profile month dropdown not exists on order confirnation page",Elements.elementPresent("order_confirmation.create_profile_month"));
        Assert.assertTrue("Create profile day dropdown not exists on order confirnation page", Elements.elementPresent("order_confirmation.create_profile_day"));
        Assert.assertTrue("Create profile year dropdown not exists on order confirnation page",Elements.elementPresent("order_confirmation.create_profile_year"));
    }

    @And("^I enter all required data on order confirmation create profile form$")
    public void iEnterAllRequiredDataOnOrderConfirmationCreateProfileForm() throws Throwable {
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        TextBoxes.typeTextbox("order_confirmation.create_profile_pwd", user.getLoginCredentials().getPassword());
        String[] dateOfBirth = user.getDateOfBirth().split("-");
        if (macys()) {
            if (dateOfBirth[1].matches("(?i)month|Month")) {
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_year", String.valueOf(dateOfBirth[0]));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_month", String.valueOf(dateOfBirth[1]));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_day", String.valueOf(dateOfBirth[2]));
            } else if (dateOfBirth[2].matches("(?i)day|Day")) {
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_year", String.valueOf(dateOfBirth[0]));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_month", WordUtils.capitalize(new java.text.DateFormatSymbols().getMonths()[Integer.valueOf(dateOfBirth[1]) - 1]));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_day", String.valueOf(dateOfBirth[2]));
            } else if (dateOfBirth[0].matches("(?i)year|Year")) {
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_year", String.valueOf(dateOfBirth[0]));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_month", WordUtils.capitalize(new java.text.DateFormatSymbols().getMonths()[Integer.valueOf(dateOfBirth[1]) - 1]));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_day", String.valueOf(dateOfBirth[2]));
            } else {
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_month", WordUtils.capitalize(user.getDateOfBirth(user.getDateOfBirth()).getMonth().name().toLowerCase()));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_day", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getDayOfMonth()));
                CreateProfile.selectDropDownIfPresent("order_confirmation.create_profile_year", String.valueOf(user.getDateOfBirth(user.getDateOfBirth()).getYear()));
            }
            Navigate.scrollPage(0, 400);
        } else {
            //Bloomingdales
            Random rand = new Random();
            WebElement monthSelector = Elements.findElements("order_confirmation.create_profile_date_list").get(0);
            monthSelector.click();
            List <WebElement> options = Elements.findElements("order_confirmation.create_profile_date_options");
            options.get(rand.nextInt(options.size()-1)).click();
            WebElement daySelector = Elements.findElements("order_confirmation.create_profile_date_list").get(1);
            daySelector.click();
            options = Elements.findElements("order_confirmation.create_profile_date_options");
            options.get(rand.nextInt(options.size()-1)).click();
            WebElement yearSelector = Elements.findElements("order_confirmation.create_profile_date_list").get(2);
            yearSelector.click();
            options = Elements.findElements("order_confirmation.create_profile_date_options");
            options.get(rand.nextInt(options.size()-2)).click();
            logger.info("Entered valid data to create account fields");
        }
    }

    @Then("^I click on create profile button$")
    public void iClickOnCreateProfileButton(Map<String, String> msg ) throws Throwable {
        Clicks.click("order_confirmation.create_profile_pwd");
        Clicks.clickIfPresent("order_confirmation.create_profile_button");
        Wait.secondsUntilElementPresent("order_confirmation.create_profile_confirmation_msg", 20);
        String strActualMsg = getText("order_confirmation.create_profile_confirmation_msg");
        Assert.assertEquals("Failed to validate create profile confirmation msg on OC page", msg.get("create_profile_msg"), strActualMsg);
    }

    @Then("^I verify the basic attributes of Create Profile Page$")
    public void iVerifyTheBasicAttributesOfCreateProfilePage() {
        Wait.secondsUntilElementPresent(("create_profile.verify_page"), 3);
        if (bloomingdales()) {
            Assert.assertTrue("First name field is not displayed", Elements.anyPresent("create_profile.verify_page"));
            Assert.assertTrue("Last name field is not displayed", Elements.anyPresent("create_profile.last_name"));
            Assert.assertTrue("Email field is not displayed", Elements.anyPresent("create_profile.email"));
            Assert.assertTrue("Password name field is not displayed", Elements.anyPresent("create_profile.password"));
            Assert.assertTrue("DOB Month field is not displayed", Elements.anyPresent("create_profile.dob_month_list"));
            Assert.assertTrue("DOB Day field is not displayed", Elements.anyPresent("create_profile.dob_day_list"));
            Assert.assertTrue("DOB Year field is not displayed", Elements.anyPresent("create_profile.dob_year_list"));
            Assert.assertTrue("Get Text alerts checkbox field is displayed, but checked", !Elements.findElement("create_profile.textme_yes").isSelected());
            Assert.assertTrue("Store events checkbox field is not displayed", !Elements.findElement("create_profile.email_store_events").isSelected());
            Assert.assertTrue("Get Sales checkbox is displayed, but NOT checked", Elements.findElement("create_profile.email_yes").isSelected());
        } else {
            Assert.assertTrue("First name field is not displayed", Elements.anyPresent("create_profile.verify_page"));
            Assert.assertTrue("Last name field is not displayed", Elements.anyPresent("create_profile.last_name"));
            Assert.assertTrue("Email field is not displayed", Elements.anyPresent("create_profile.email"));
            Assert.assertTrue("Password name field is not displayed", Elements.anyPresent("create_profile.password"));
            Assert.assertTrue("DOB Month field is not displayed", Elements.anyPresent("create_profile.dob_month_list"));
            Assert.assertTrue("DOB Day field is not displayed", Elements.anyPresent("create_profile.dob_day_list"));
            Assert.assertTrue("DOB Year field is not displayed", Elements.anyPresent("create_profile.dob_year_list"));
            Assert.assertTrue("Get Text alerts checkbox field is displayed, but checked", !Elements.findElement("create_profile.textme_yes").isSelected());
            Assert.assertTrue("Security Question field is not displayed", Elements.anyPresent("create_profile.security_question_list"));
            Assert.assertTrue("Security Answer field is not displayed", Elements.anyPresent("create_profile.security_answer"));
            Assert.assertTrue("Store events checkbox field is not displayed", !Elements.findElement("create_profile.email_store_events").isSelected());
            Assert.assertTrue("Get Sales checkbox is displayed, but NOT checked", Elements.findElement("create_profile.email_yes").isSelected());
        }
    }

    @And("^I enter unique email in contact info section$")
    public void iEnterUniqueEmailInContactInfoSection() throws Throwable {
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        TextBoxes.typeTextbox("responsive_checkout.create_profile_email", user.getProfileAddress().getEmail());
        logger.info("User entered unique email in Contact Info section on Checkout page");
    }

    @And("^I see account created confirmation message on Order Confirmation page with sign in link$")
    public void iSeeAccountCreatedConfirmationMessageOnOrderConfirmationPageWithSignInLink(Map<String, String> data) {
        Assert.assertTrue("Account created successfully message is not present on OCP", Elements.elementPresent("order_confirmation.create_profile_confirmation_msg"));
        Assert.assertTrue("Account created successfully message is incorrect", Elements.getText("order_confirmation.create_profile_confirmation_msg").equals(data.get("account_created_confirm_msg")));
        Assert.assertTrue("Sign In Now link label is incorrect", Elements.getText("order_confirmation.sign_in_now_link_conf_msg").equals(data.get("sign_in_link")));
        logger.info("Account successfully created confirmation message appeared on OCP");
    }

    @And("^Account creation fields are not displayed on order confirmation page$")
    public void accountCreationFieldsAreNotDisplayedOnOrderConfirmationPage() {
        Assert.assertFalse("Email field is displayed on OCP", Elements.elementPresent("order_confirmation.create_profile_email"));
        Assert.assertFalse("Email field is displayed on OCP", Elements.elementPresent("order_confirmation.create_profile_pwd"));
        logger.info("Account creation fields are not exposed on OCP");
    }

    @When("^I click on sign in link on order confirmation page$")
    public void iClickOnSignInLinkOnOrderConfirmationPage() {
        Wait.untilElementPresent("order_confirmation.sign_in_now_link_conf_msg");
        Clicks.click("order_confirmation.sign_in_now_link_conf_msg");
        logger.info("User clicked on Signed In link on OCP");
    }

    @Then("^I am navigated to sign in page$")
    public void iAmNavigatedToSignInPage() {
        Wait.untilElementPresent("sign_in.verify_page");
        Assert.assertTrue("Not navigated to sign in page", Elements.elementPresent("sign_in.verify_page"));
        logger.info("User is navigated to sign in page");
    }

    @Then("^I see email already exists message and sign in link on OCP$")
    public void iSeeEmailAlreadyExistsMessageAndSignInLinkOnOCP(Map<String, String> msg) {
        Wait.untilElementPresent("order_confirmation.create_profile_email_exist_error");
        Assert.assertTrue("Email already exist err msg is not present", Elements.elementPresent("order_confirmation.create_profile_email_exist_error"));
        Assert.assertTrue(Elements.getText("order_confirmation.create_profile_email_exist_error").equals(msg.get("email_exist_err_msg")));
        Assert.assertTrue("Sign in link is not present in Email already exist err msg", Elements.elementPresent("order_confirmation.sign_in_now_link_error_msg"));
        logger.info("User sees 'email already exists' message on OCP");
    }

    @And("^Account created confirmation overlay is displayed with Sign In, Continue Shopping and close buttons$")
    public void accountCreatedConfirmationOverlayIsDisplayedWithSignInContinueShoppingAndCloseButtons(Map<String, String> msg) {
        Wait.untilElementPresent("order_confirmation.create_profile_overlay_cont_shopping_btn");
        Wait.secondsUntilElementPresent("order_confirmation.create_profile_confirmation_msg", 20);
        String strActualMsg = getText("order_confirmation.create_profile_confirmation_msg");
        Assert.assertEquals("Failed to validate create profile confirmation msg on OC page", msg.get("create_profile_msg"), strActualMsg);
        Assert.assertTrue("Continue Shopping button is not present on account created overlay",Elements.elementPresent("order_confirmation.create_profile_overlay_cont_shopping_btn"));
        Assert.assertTrue("Sign In button is not present on account created overlay",Elements.elementPresent("order_confirmation.create_profile_overlay_sign_in_btn"));
        Assert.assertTrue("Close button is not present on account created overlay",Elements.elementPresent("order_confirmation.create_profile_overlay_close_btn"));
        logger.info("Account created confirmation overlay is displayed with Sign In, Continue Shopping and close buttons");
    }

    @When("^I click on sign in button on the overlay on order confirmation page$")
    public void iClickOnSignInButtonOnTheOverlayOnOrderConfirmationPage() {
        Clicks.click("order_confirmation.create_profile_overlay_sign_in_btn");
        logger.info("Clicked on sign in button on the overlay on order confirmation page");
    }

    @And("^I click continue shopping button from account created overlay on order confirmation page$")
    public void iClickContinueShoppingButtonFromAccountCreatedOverlayOnOrderConfirmationPage() {
        Wait.untilElementPresent("order_confirmation.create_profile_overlay_cont_shopping_btn");
        Clicks.click("order_confirmation.create_profile_overlay_cont_shopping_btn");
        logger.info("Clicked continue shopping button from account created overlay on order confirmation page");
    }

    @Then("^I am navigated to home page$")
    public void iAmNavigatedToHomePage() {
        Wait.untilElementPresent("home.verify_page");
        Assert.assertTrue("User is not navigated to home page",Elements.elementPresent("home.verify_page"));
        logger.info("User is navigated to home page");
    }

    @And("^I verify inline error message for existing email while creating profile on order confirmation page$")
    public void iVerifyInlineErrorMessageForExistingEmailWhileCreatingProfileOnOrderConfirmationPage(Map<String, String> msg) {
        Wait.untilElementPresent("order_confirmation.create_profile_email_exist_error");
        Assert.assertTrue(Elements.getText("order_confirmation.create_profile_email_exist_error").equals(msg.get("email_exist_err_msg")));
        logger.info("Inline error message appears for existing email while creating profile on order confirmation page");
    }

    @And("^I verify inline error message for required password and dob while creating profile on order confirmation page$")
    public void iVerifyInlineErrorMessageForRequiredPasswordAndDobWhileCreatingProfileOnOrderConfirmationPage(Map<String, String> msg) {
        Assert.assertTrue("Password hint is not present on OCP", Elements.elementPresent("order_confirmation.create_profile_error_pwd"));
        Assert.assertTrue("Date of birth required message is incorrect", Elements.getText("order_confirmation.create_profile_error_dob").equals(msg.get("error_dob")));
        
        logger.info("inline error message for required password and dob while creating profile on order confirmation page");
    }

    @And("^I update email with already exist email bcom$")
    public void iUpdateEmailWithAlreadyExistEmailBcom() throws Throwable {
        UserProfile user = new UserProfile();
        user = UserProfile.getDefaultProfile();
        UserProfileService.createUserProfile(user);
        if (onPage("responsive_checkout")) {
            TextBoxes.typeTextbox("responsive_checkout.create_profile_email", user.getUser().getProfileAddress().getEmail());
        } else {
            TextBoxes.typeTextbox("order_confirmation.create_profile_email", user.getUser().getProfileAddress().getEmail());
        }
    }

    @When("^I click on sign in now link on order confirmation page$")
    public void iClickOnSignInNowLinkOnOrderConfirmationPage() {
        Wait.untilElementPresent("order_confirmation.sign_in_now_link_error_msg");
        Clicks.click("order_confirmation.sign_in_now_link_error_msg");
    }

    @Then("^I click on create profile button on Order Confirmation page$")
    public void iClickOnCreateProfileButtonOnOrderConfirmationPage() {
        Wait.secondsUntilElementPresent("order_confirmation.create_profile_button", 10);
        Clicks.clickIfPresent("order_confirmation.create_profile_button");
        logger.info("Clicked on Create profile button on order confirmation page");
    }

    @And("^I enter valid date of birth on Checkout page$")
    public void iEnterValidDateOfBirthOnCheckoutPage() {
        Random rand = new Random();
        WebElement monthSelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(0);
        monthSelector.click();
        List <WebElement> options = Elements.findElements("responsive_checkout.create_profile_date_options");
        options.get(rand.nextInt(options.size()-1)).click();
        WebElement daySelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(1);
        daySelector.click();
        options = Elements.findElements("responsive_checkout.create_profile_date_options");
        options.get(rand.nextInt(options.size()-1)).click();
        WebElement yearSelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(2);
        yearSelector.click();
        options = Elements.findElements("responsive_checkout.create_profile_date_options");
        options.get(rand.nextInt(options.size()-2)).click();
        logger.info("User entered valid date of birth on Checkout page");
    }

    @And("^I enter invalid password in the password field$")
    public void iEnterInvalidPasswordInThePasswordField(Map<String, String> data) {
        Wait.secondsUntilElementPresent("responsive_checkout.create_profile_pwd", 10);
        TextBoxes.typeTextbox("responsive_checkout.create_profile_pwd", data.get("invalid_password"));
        logger.info("User enters invalid password in password field");
    }

    @Then("^I see invalid password message and Try again link on OCP$")
    public void iSeeInvalidPasswordMessageAndTryAgainLinkOnOCP(Map<String, String> pwd) {
        Wait.secondsUntilElementPresent("order_confirmation.invalid_pwd_error", 3);
        Assert.assertTrue("Invalid password error msg is not present", Elements.elementPresent("order_confirmation.invalid_pwd_error"));
        Assert.assertEquals("Invalid password error msg is not as expected", pwd.get("invalid_pwd_msg"), Elements.getText("order_confirmation.invalid_pwd_error"));
        Wait.secondsUntilElementPresent("order_confirmation.invalid_pwd_error_please_try_again_link", 3);
        Assert.assertTrue("Please try again link is not present in Invalid pwd err msg", Elements.elementPresent("order_confirmation.invalid_pwd_error_please_try_again_link"));
        Assert.assertEquals("Please try again link copy is incorrect", pwd.get("please_try_again_link"), Elements.getText("order_confirmation.invalid_pwd_error_please_try_again_link"));
        logger.info("Invalid password message appears with Try again link on OCP");
    }

    @And("^I enter valid password in the password field$")
    public void iEnterValidPasswordInThePasswordField() throws Throwable {
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        TextBoxes.typeTextbox("responsive_checkout.create_profile_pwd", user.getLoginCredentials().getPassword());
        logger.info("Entered valid password");
    }

    @And("^I enter invalid date of birth on Checkout page$")
    public void iEnterInvalidDateOfBirthOnCheckoutPage() {
        Random rand = new Random();
        WebElement monthSelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(0);
        monthSelector.click();
        List<WebElement> options = Elements.findElements("responsive_checkout.create_profile_date_options");
        options.get(rand.nextInt(options.size()-1)).click();
        WebElement daySelector = Elements.findElements("responsive_checkout.create_profile_date_list").get(1);
        daySelector.click();
        options = Elements.findElements("responsive_checkout.create_profile_date_options");
        options.get(rand.nextInt(options.size()-1)).click();
        logger.info("Entered valid date of birth on Checkout page");
    }

    @Then("^I see invalid date of birth message and Try again link on OCP$")
    public void iSeeInvalidDateOfBirthMessageAndTryAgainLinkOnOCP(Map<String, String> msg) throws Throwable {
        Wait.secondsUntilElementPresent("order_confirmation.invalid_pwd_error", 3);
        Assert.assertTrue("Invalid DOB error msg is not present", Elements.elementPresent("order_confirmation.invalid_pwd_error"));
        Assert.assertEquals("Invalid DOB error msg is not as expected", msg.get("invalid_dob_msg"), Elements.getText("order_confirmation.invalid_pwd_error"));
        Wait.secondsUntilElementPresent("order_confirmation.invalid_pwd_error_please_try_again_link", 3);
        Assert.assertTrue("Please try again link is not present in Invalid dob err msg", Elements.elementPresent("order_confirmation.invalid_pwd_error_please_try_again_link"));
        Assert.assertEquals("Please try again link copy is incorrect", msg.get("please_try_again_link"), Elements.getText("order_confirmation.invalid_pwd_error_please_try_again_link"));
        logger.info("Invalid date of birth message appears on OCP");
    }
}
