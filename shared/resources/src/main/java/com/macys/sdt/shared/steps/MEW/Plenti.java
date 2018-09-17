package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.shared.actions.MEW.pages.PlentiEnroll;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Plenti extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Plenti.class);
    /**
     * Navigates to my plenti page from my account pages
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to my plenti page using mobile website$")
    public void I_navigate_to_my_plenti_page_using_mobile_website() throws Throwable {
        Wait.untilElementPresent("my_account.plenti_learn_more");
        Wait.untilElementPresent("my_account.my_plenti");
        Assert.assertTrue("ERROR - ENV: Unable to locate my plenty option in my account page", Elements.elementPresent("my_account.plenti_learn_more") || Elements.elementPresent("my_account.my_plenti"));
        Clicks.clickIfPresent("my_account.plenti_learn_more");
        Clicks.clickIfPresent("my_account.my_plenti");

    }

    /**
     * Removes plenti from current profile if on plenti summary page and Clicks on given plenti button
     *
     * @param button "join for free", "join now", "enroll cancel" or "yes, cancel"
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" button using mobile website$")
    public void I_click_button_using_mobile_website(String button) throws Throwable {
        if (onPage("plenti_summary")) {
            I_remove_the_plenti_points_from_profile();
            I_navigate_to_my_plenti_page_using_mobile_website();
        }

        switch (button.toLowerCase()) {
            case "join for free":
//                Clicks.clickWhenPresent("plenti_home.learn_more");
                Wait.untilElementPresent("plenti_join.btn_join_now");
                Wait.untilElementPresent("plenti_join.btn_join_now");
                Assert.assertTrue("ERROR-ENV: Unable to locate join for free element", !Elements.findElements("plenti_home.btn_join_free").isEmpty());
                Clicks.clickIfPresent("plenti_join.btn_join_now");
                Clicks.clickIfPresent("plenti_join.btn_join_now");
                Clicks.clickIfPresent("plenti_join.btn_proceed");
                //   Clicks.click(Elements.findElements("plenti_home.btn_join_free").get(0));
                break;
//            case "join now":
//                Navigate.visit("plenti_enroll"); //This will directly navigate to plenty enroll page directly
//                //   Wait.untilElementPresent("plenti_join.btn_join_now");
//                //   Assert.assertTrue("ERROR-ENV: Unable to locate join now element", Elements.elementPresent("plenti_join.btn_join_now"));
//                //   Clicks.click("plenti_join.btn_join_now");
//                break;
            case "enroll cancel":
                Wait.untilElementPresent("plenti_enroll.btn_cancel");
                Assert.assertTrue("ERROR-ENV: Unable to locate enroll cancel element", Elements.elementPresent("plenti_enroll.btn_cancel"));
                Clicks.click("plenti_enroll.btn_cancel");
                Assert.assertTrue("ERROR-ENV: Current environment is deviated in to a " + url() + " environment", MEW());
                break;
            case "yes, cancel":
                Wait.forPageReady();
                Wait.untilElementPresent("plenti_enrollment_cancel_confirm.cancel_overlay");
                Assert.assertTrue("ERROR-ENV: Unable to locate yes, cancel element", Elements.elementPresent("plenti_enrollment_cancel_confirm.cancel_overlay"));
                Clicks.click("plenti_enrollment_cancel_confirm.btn_yes_cancel");
                // Assert.assertTrue("ERROR-ENV: Current environment is deviated in to a " + url() + " environment", MEW());
                break;
            default:
                logger.info("Invalid button: " + button);
        }
    }

    /**
     * Enrolls customer to Plenti
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I opt for enrolment from plenti sign in page using mobile website$")
    public void I_opt_for_enrolment_from_plenti_sign_in_page_using_mobile_website() throws Throwable {
        PlentiEnroll.enroll(TestUsers.getuslCustomer(null));
    }

    /**
     * Enters plenti phone number on enrollment page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I enter the plenti phone number using mobile website$")
    public void I_enter_the_plenti_phone_number_using_mobile_website() throws Throwable {
        TextBoxes.typeTextbox(Elements.element("plenti_enroll.phone_number"), TestUsers.getuslCustomer(null).getUser().getProfileAddress().getBestPhone());
    }

    /**
     * Removes plenti from current profile
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I remove the plenti points from profile$")
    public void I_remove_the_plenti_points_from_profile() throws Throwable {
        Wait.untilElementPresent("plenti_summary.remove_usl_id");
        Clicks.click("plenti_summary.remove_usl_id");
        Clicks.clickWhenPresent("plenti_summary.confirm_delete_button");
        Wait.forPageReady();
        shouldBeOnPage("my_account");
    }

    /**
     * Verifies USL ID, redeem message, and other basic attributes on plenti summary page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see USL basic attributes on plenti summary page$")
    public void I_should_see_USL_basic_attributes_on_plenti_summary_page() throws Throwable {
        String pageName = MEW() ? "plenti_summary" : "usl_account_summary";
        String actualUslId = Elements.getText(Elements.element(pageName + ".added_usl_id"));
        String addedUslId = MyAccountSteps.newUslId == null ? TestUsers.getEnrolledUslId().getPlentiId() : MyAccountSteps.newUslId;
        String expectedUslId = StringUtils.overlay(addedUslId, StringUtils.repeat("*", addedUslId.length() - 4), 0, addedUslId.length() - 4);
        Assert.assertTrue("USL ID is not displayed correctly on USL account summary page", actualUslId.contains(expectedUslId));

        Elements.elementShouldBePresent(pageName + ".remove_usl_id");
        Elements.elementShouldBePresent(pageName + ".goto_learn_more");
        Elements.elementShouldBePresent(pageName + ".goto_faq");

        String actualRedeemMessage = Elements.getText(Elements.element(pageName + ".redeem_message"));
        String expectedRedeemMessage = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName() + "'s Plenti points balance:";
        Assert.assertTrue("Redeem message is not displayed correctly on USL account summary page", actualRedeemMessage.equals(expectedRedeemMessage));
    }

    @And("^I navigate to Plenti enrollment page using mobile website$")
    public void iNavigateToPlentiEnrollmentPageUsingMobileWebsite() throws Throwable {
        if (signedIn()) {
            if (!onPage("my_account")) {
                Navigate.visit("my_account");
            }
            Wait.untilElementPresent("my_account.plenti_learn_more");
            if (Elements.elementPresent("my_account.plenti_learn_more")){
                Clicks.javascriptClick("my_account.plenti_learn_more");
            } else {
                Clicks.click("my_account.my_plenti");
                Wait.untilElementPresent("my_account.plenti_enroll_today");
            }
            Clicks.click("my_account.plenti_enroll_today");
            Clicks.click("my_account.plenti_enroll_today");
        } else {
            Navigate.visit("plenti_sign_in");
            shouldBeOnPage("plenti_sign_in");
            Wait.untilElementPresent("plenti_sign_in.goto_create_profile");
            Clicks.click("plenti_sign_in.goto_create_profile");
        }
    }

    @When("^I enroll into the Plenti program using mobile website$")
    public void iEnrollIntoThePlentiProgramUsingMobileWebsite() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("Cannot enroll into Plenti on prod!");
        }

        shouldBeOnPage("plenti_enroll");
        PlentiEnroll.doAuthentication();
        shouldBeOnPage("plenti_enroll");

        // Reset currentURL to avoid loading elements from website folder
        Runnable resetToMewUrl = () -> MainRunner.currentURL = RunConfig.url;
        Navigate.addAfterNavigation(resetToMewUrl);

        TestUsers.clearCustomer();
        PlentiEnroll.enroll(TestUsers.getuslCustomer(null));
        PlentiEnroll.enrolStep1();
        PlentiEnroll.enrolStep2();
        PlentiEnroll.setPreferences();

        Navigate.removeAfterNavigation(resetToMewUrl);
    }
}
