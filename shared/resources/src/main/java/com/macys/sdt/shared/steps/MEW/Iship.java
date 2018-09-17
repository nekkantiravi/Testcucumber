package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Iship extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Iship.class);
    /**
     * Changes active country to given country
     *
     * @param country full name of country as it is in the country selection drop down
     * @throws Throwable if any exception occurs
     */
    @And("^I change country to \"([^\"]*)\" using mobile website$")
    public void I_change_country_to_using_mobile_website(String country) throws Throwable {
        try {
            Wait.untilElementPresent(("change_country.country"));
            DropDowns.selectByText("change_country.country", country);
            Clicks.javascriptClick("change_country.change_country_btn");
            Wait.untilElementPresent("home.go_to_us_site");
        } catch (NullPointerException ex) {
            Assert.fail("Change country related elements are not visible");
        }
    }

    /**
     * Closes the iship welcome mat
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I close the welcome mat if it's visible using mobile website$")
    public void I_close_the_welcome_mat_if_it_s_visible_using_mobile_website() throws Throwable {
        try {
            Wait.untilElementPresent(Elements.element("welcome_mat_dialog.container"));
            Clicks.clickIfPresent(Elements.element("welcome_mat_dialog.close"));
            Wait.untilElementNotPresent(Elements.element("welcome_mat_dialog.container"));
        } catch (Exception e) {
            logger.info("Welcome mat is not being displayed");
        }
    }
}
