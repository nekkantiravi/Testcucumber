package com.macys.sdt.projects.Discovery.UFT.steps.mew;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopAndBrowse extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);
    /**
     * Method to click on Filter of Shop and Browse page
     */
    @And("^I click filter$")
    public void clickOnFilter() throws Throwable {
        Clicks.click("category_browse.sort_by");
        log.info("Successfully clicked on filter");
    }

}