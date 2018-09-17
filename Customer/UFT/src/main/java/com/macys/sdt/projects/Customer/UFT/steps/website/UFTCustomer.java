package com.macys.sdt.projects.Customer.UFT.steps.website;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.JSONObject;

import static com.macys.sdt.framework.interactions.Elements.getText;

public class UFTCustomer extends StepUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UFTCustomer.class);
    public JSONObject returnOrderDetails;

    @When("^I update the first and last name with single quotes in payment section$")
    public void iUpdateFirstLastNameWithSingleQuotesInPaymentSection() throws Throwable {

        TextBoxes.typeTextbox("responsive_payment_guest_section.first_name", "Jam'es");
        TextBoxes.typeTextbox("responsive_payment_guest_section.last_name", "Dea'n");
        TextBoxes.typeTextbox("responsive_payment_guest_section.security_code", "204");
        Clicks.click("responsive_payment_guest_section.continue_payment_checkout_button");

        LOGGER.info("Updated the first and last name with single quotes");
    }

    @Then("^I should not see single quotes in billing address first name and last name$")
    public void iVerifyThatSingleQuotesInBllingAddressFirstLastNameIsRemoved() throws Throwable {
        Wait.forPageReady();
        Clicks.click("responsive_payment_guest_section.edit_payment_info");

        Assert.assertFalse("ERROR - APP : First name contains single quote", getText("responsive_payment_guest_section.first_name").contains("'"));
        Assert.assertFalse("ERROR - APP : Last name contains single quote", getText("responsive_payment_guest_section.last_name").contains("'"));

        LOGGER.info("Verified that single wuotes are not present in first and last names of billing section");
    }

    @And("^I uncheck use my shipping address checkbox on payment page$")
    public void iUncheckUseMyShippingAddressCheckboxOnPaymentPage() throws Throwable {
        if (macys()) {
            if (Elements.elementPresent("responsive_payment_guest_section.use_shipping_address")) {
                Clicks.unSelectCheckbox("responsive_payment_guest_section.use_shipping_address");
            }
        } else if (Elements.elementPresent("payment_guest.use_shipping_address")) {
            Clicks.unSelectCheckbox("payment_guest.use_shipping_address");
        } else {
            Clicks.unSelectCheckbox("responsive_payment_guest_section.use_shipping_address");
        }

    }

    @When("^I navigate to \"([^\"]*)\" footer link under customer service$")
    public void iNavigateToFooterLinkUnderCustomerService(String customerServicePage) {
        switch (customerServicePage) {
            case "customer service":
                Clicks.click("home.goto_customer_service");
                break;
            case "para ayuda":
                Clicks.click("home.goto_para_ayuda");
                break;
            default:
                Assert.fail("Invalid Footer link");
                break;
        }
        LOGGER.info("Clicked on" + customerServicePage + " link on home page");
    }

    @Then("^I verify UI changes on account sign in page$")
    public void iVerifyUIChangesOnAccountSignInPages() {
        Assert.assertTrue("Sign in text is not present in Sign in UI page", getText("sign_in.Sign_in_text").equals("Sign in"));
        Assert.assertTrue("Have a Macy's account? text is not present in Sign in page", getText("sign_in.have_macys_account_text").equals("Have a Macy's account?"));
        Assert.assertTrue("If youStory B-79039  already created a Macy's account text is not present in Sign in page", getText("sign_in.have_macys_account_info_text").equals("If you already created a Macy's account, you can sign in here."));
        Assert.assertTrue("Email address label text is not present in Sign in page", getText("sign_in.email_address_label").equals("Email address"));
        Assert.assertTrue("Password label text is not present in Sign in page", getText("sign_in.password_label").equals("Password"));
        Assert.assertTrue("Password is case sensitive. text is not present in Sign in page", getText("sign_in.password_info_text").equals("Password is case sensitive."));
        Assert.assertTrue("dont have macys account? text is not present in Sign in page", getText("sign_in.dont_have_macys_account_text").equals("Don't have a Macy's account?"));
        Assert.assertTrue("Create an account now to take advantage text is not present in Sign in page", getText("sign_in.dont_have_macys_account_info_text").contains("Create an account now to take advantage of express checkout and other convenient features."));
        Assert.assertTrue("See All The Benefits Of Creating An Account text is not present in Sign in page", getText("sign_in.see_all_benefits_Link").equals("See All The Benefits Of Creating An Account"));
        Assert.assertTrue("Privacy Practices text is not present in Sign in page", getText("sign_in.privacy_practices").equals("Privacy Practices"));
        Assert.assertTrue("Keep in mind a few basic password rules: text is not present in Sign in page", getText("sign_in.keep_in_mind_text").equals("Keep in mind a few basic password rules:"));
        LOGGER.info("UI changes verification success on account Sign in page");
    }

    @When("^I navigate to easy returns page$")
    public void iNavigateToEasyReturnsPage() {
        Navigate.visit(RunConfig.url + "/service/easyreturn");
        shouldBeOnPage("easy_returns");
        LOGGER.info("Navigated Successfully to easy returns Page");
    }

    @Then("^I should see \"International Returns\" header and \"International Returns Policy\" link$")
    public void VerifyInternationReturnsHeaderandLinkText() {
        Assert.assertTrue("International Returns text is not displayed in easy returns page", getText("easy_returns.international_returns_text").equals("International Returns"));
        Assert.assertTrue("International Returns link is not displayed in easy returns page", getText("easy_returns.international_returns_policy_link").equals("International Returns Policy"));
        LOGGER.info("International returns displayed in easy returns page");
    }

    @And("^\"International Returns Policy\" link should be navigated to Customer service International Returns Page$")
    public void VerifyInternationReturnsPolicyLink() {
        Clicks.click("easy_returns.international_returns_policy_link");
        try {
            WebDriver driver = WebDriverManager.getWebDriver();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            Assert.assertTrue("International Returns policy link did not navigate to the expected page", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/5362"));
        } catch (DriverNotInitializedException e) {
            LOGGER.error("Error in getWebDriver: " + e);
        }
        LOGGER.info("Successfully navigated to International Returns Policy Page");
    }

    @And("^I select return items button on \"(OH|OD)\" page$")
    public void I_select_return_items_button_on_page(String pageName) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        byte var4 = -1;
        switch(pageName.hashCode()) {
            case 2517:
                if(pageName.equals("OD")) {
                    var4 = 1;
                }
                break;
            case 2521:
                if(pageName.equals("OH")) {
                    var4 = 0;
                }
        }

        switch(var4) {
            case 0:
                Clicks.click("home.goto_order_status");
                returnsPage.findOrderInDateRange(this.returnOrderDetails.getString("order_number"));
                returnsPage.returnOrder(this.returnOrderDetails.getString("order_number"));
                break;
            case 1:
                Assert.assertFalse("ERROR - DATA : Order test data is not present in environment!!", Elements.elementPresent("order_status.error_message"));
                Clicks.click("order_details.return_items");
                break;
            default:
                Assert.fail("Invalid page name: " + pageName);
        }
    }

    @Then("^I should see \"([^\"]*)\" return error message$")
    public void iShouldSeeReturnErrorMessage(String expectedReturnErrorCopy) {
        Wait.untilElementPresent("return_error.return_error_copy");
        shouldBeOnPage("return_error");
        Assert.assertTrue("ERROR - APP: Return error copy is not updated ", Elements.getText("return_error.return_error_copy").equals(expectedReturnErrorCopy));
    }

}