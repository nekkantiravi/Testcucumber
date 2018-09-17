package com.macys.sdt.projects.Marketing.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IShipBCOMSteps extends StepUtils
{
    private static final Logger logger = LoggerFactory.getLogger(IShipBCOMSteps.class);

    @Then("^the welcome message should include \"([^\"]*)\"$")
    public void theWelcomeMessageShouldInclude(String country) {
        Wait.secondsUntilElementPresent("welcome_mat_dialog.subheader",20);
        Assert.assertTrue("Welcome message did not include " + country,
                Elements.findElement("welcome_mat_dialog.subheader").getText().contains(country));
        Clicks.clickIfPresent("home.close_welcome");
        logger.info("Verified IShip Welcome mat");
    }

    @Then("^I verify the basic attributes of Iship Home page$")
    public void iVerifyTheBasicAttributesOfIshipHomePage() {
        Assert.assertFalse("ERROR - ENV: My account link displayed in iship mode",
                Elements.elementPresent("home.goto_my_account_link"));
        Assert.assertFalse("ERROR - ENV: Beauty category displayed in iship mode",
                new Home().getAllMainCategoryNames().contains("BEAUTY"));
        Assert.assertTrue("ERROR - ENV: Currency element not displayed in iship mode",
                Elements.elementPresent("header.iship_currency"));
        logger.info("Verified International Home page");
    }

    @Then("^I should see navapp server clone name in view source$")
    public void i_should_see_navapp_server_clone_name_in_view_source() throws Throwable {

        logger.info("site details" +EnvironmentDetails.getType());
        Assert.assertTrue("ERROR- ENV: Navapp server clone name is not displayed",
                EnvironmentDetails.getType().contains("NavApp"));


    }



}
