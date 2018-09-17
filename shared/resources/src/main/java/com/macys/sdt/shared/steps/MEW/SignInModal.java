package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rmareddy on 11/12/2017.
 */
public class SignInModal extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignInModal.class);

    /**
     * verify user see expected word on sign in page
     */
    @Then("^I should see the expected word on sign in page$")
    public void verifyExpectedWordOnSignInPage(DataTable table) throws Throwable
    {
        List<List<String>> data = table.raw();
        String expectedValue1 = data.get(1).get(0);
        String expectedValue2 = data.get(2).get(0);
        WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertTrue("Expected word " + expectedValue1 + " is not displayed.", Elements.getText("sign_in.not_have_an_account").contains(expectedValue1));
        logger.info("Expected word: " + expectedValue1 + " is displayed.");
        Assert.assertTrue("Expected word " + expectedValue1 + " is not displayed.",Elements.getText("sign_in.not_have_an_account").contains(expectedValue1));
        logger.info("Expected word: " + expectedValue1 + " is displayed.");
        Assert.assertTrue("Expected word " + expectedValue2 + " is not displayed.",Elements.getText("sign_in.privacy_practise").contains(expectedValue2));
        logger.info("Expected word: " + expectedValue2 + " is displayed.");
    }

    /**
     * verify user see expected word on m-secure plenti page
     */
    @Then("^I should see the expected word on m-secure plenti page$")
    public void verifyExpectedWordOnMsecurePlentiPage(DataTable table) throws Throwable
    {
        List<List<String>> data = table.raw();
        String expectedValue1 = data.get(1).get(0);
        String expectedValue2 = data.get(2).get(0);
        String expectedValue3 = data.get(3).get(0);
        WebDriverManager.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        logger.debug("looking for the 'account' word");
        Assert.assertTrue("updated word " + expectedValue1 + " is not displayed.",Elements.getText("plenti_sign_in.join_plenti_account_msg").contains(expectedValue1));
        logger.info("Expected word: " + expectedValue1 + " is displayed.");
        logger.debug("looking for the 'account' word");
        Assert.assertTrue("updated word " + expectedValue1 + " is not displayed.",Elements.getText("plenti_sign_in.create_plenti_account_msg").contains(expectedValue1));
        logger.info("Expected word: " + expectedValue1 + " is displayed.");
        logger.debug("looking for the 'Practices' word");
        Assert.assertTrue("updated word " + expectedValue2 + " is not displayed.",Elements.getText("plenti_sign_in.privacy_practises").contains(expectedValue2));
        logger.info("Expected word: " + expectedValue2 + " is displayed.");
        logger.debug("looking for the 'your' word");
        Assert.assertTrue("updated word " + expectedValue3 + " is not displayed.",Elements.getText("plenti_sign_in.join_plenti_account_msg").contains(expectedValue3));
        logger.info("Expected word: " + expectedValue3 + " is displayed.");
    }

    @Given("^I enter a valid email and password$")
    public void I_enter_user_andPassword() throws Throwable {
        CommonUtils.signInOrCreateAccountMew();
            /*if (!prodEnv()) {
                HashMap<String, String> options = new HashMap<>();
                options.put("checkout_eligible", "true");
                ProfileAddress profileAddress = new ProfileAddress();
                TestUsers.getRandomValidAddress(options, profileAddress);
                TestUsers.getCustomer(null).getUser().setProfileAddress(profileAddress);
            }*/

            Navigate.visit("home");
        CommonUtils.signOut();
        new MyAccount().iAmOnTheFooterSecureMSignInPage();
        TextBoxes.typeTextbox("sign_in.email", TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", TestUsers.getCustomer(null).getUser().getLoginCredentials().getPassword());
    }

    @Given("^I touch modal sign in Button$")
    public void I_click_sign_in_mew() throws Throwable {
        Clicks.click("sign_in.sign_in_button");
        Wait.secondsUntilElementPresent("sign_in.close_incomplete_information", 30);
        if (Elements.elementPresent("sign_in.close_incomplete_information")) {
            Clicks.click("sign_in.close_incomplete_information");
        }

    }

    @Then("^I should see home page$")
    public void I_see_home_page() throws Throwable {
        Wait.secondsUntilElementPresent("footer.goto_sign_out_link", 30);
        if (!Elements.elementPresent("home.verify_page")) {
            Assert.fail("Not on home page");
        }
    }


    @Then("^I should be logged in$")
    public void I_should_be_logged_in() throws Throwable {
        logger.info("-> Capture UserIdCookies after sign in!!");
        String user_id = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
        logger.info("User id after sign in: " + user_id);
        Assert.assertNotNull(user_id);
        Assert.assertTrue("", StringUtils.containsIgnoreCase(Cookies.getCookieValue("SNSGCs"), "last_access_token"));
        logger.info("Verified cookies after user is Signed in to profile");
    }

}
