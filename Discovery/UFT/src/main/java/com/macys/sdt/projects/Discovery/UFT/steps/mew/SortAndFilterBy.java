package com.macys.sdt.projects.Discovery.UFT.steps.mew;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SortAndFilterBy extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);

    /**
     *  Method to select special offers option on sort & filter page
     **/
    @And("^I click on special offers button")
    public void I_click_on_special_offers_button() throws Throwable {
        Wait.untilElementPresent("sort_filter.special_offers");
        Clicks.click("sort_filter.special_offers");
        log.info("Verified that the user clicks on special offers button");
    }

    /**
     *  Method to select LAST ACT option on sort & filter page
     */
    @And("^I select the option Last Act")
    public void I_select_the_option_last_act() throws Throwable {
        Wait.untilElementPresent("sort_filter.last_act");
        Clicks.click("sort_filter.last_act");
        log.info("Verified that the user clicks on the option 'Last Act'");
    }

    /**
     *  Method to click apply button on sort & filter page
     */
    @And("^I click on the apply button on Sort and Filter Page")
    public void I_click_on_the_apply_button() throws Throwable {
        Wait.untilElementPresent("sort_filter.facet_apply");
        Clicks.click("sort_filter.facet_apply");
        log.info("Verified that the user clicks on the option 'Last Act'");
    }

    /**
     *  Method to select sales & offers option on sort & filter page
     */
    @And("^I click on sales & offers button")
    public void I_click_on_sales_offers_button() throws Throwable {
        Wait.untilElementPresent("sort_filter.sales_offers");
        Clicks.click("sort_filter.sales_offers");
        log.info("Verified that the user clicks on sales & offers button");
    }
}
