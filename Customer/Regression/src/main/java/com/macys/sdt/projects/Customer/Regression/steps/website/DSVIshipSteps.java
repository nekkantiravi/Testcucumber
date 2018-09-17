package com.macys.sdt.projects.Customer.Regression.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DSVIshipSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(DSVIshipSteps.class);

    @Then("^I verify the basic attributes of Iship Home page$")
    public void i_verify_the_basic_attributes_of_Iship_Home_page() throws Throwable {
        //verify the accounts button is not visible in iship home page
        Assert.assertFalse("ERROR - ENV: My account link displayed in iship mode",
                Elements.elementPresent("home.goto_my_account_link"));
        Assert.assertFalse("ERROR - ENV: Beauty category displayed in iship mode",
                new Home().getAllMainCategoryNames().contains("BEAUTY"));
        Assert.assertTrue("ERROR - ENV: Currency element not displayed in iship mode",
                Elements.elementPresent("header.iship_currency"));
        logger.info("Verified International Home page");
    }

}