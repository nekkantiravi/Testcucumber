package com.macys.sdt.projects.Selection.UFT.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WishList {
    private static final Logger log = LoggerFactory.getLogger(WishList.class);

    /**
     * Method to view text LAST ACT on wishlist page.
     */
    @Then("^I should see \"([^\"]*)\" message on WishList Page")
    public void I_should_see_text_on_WishList(String expectedMsg) throws Throwable
    {
        Utils.threadSleep(5000, "Waiting for page to load");
        String actualMessage = Elements.getText("wishlist.last_act_text_wishlist");
        Assert.assertEquals("LAST ACT text is not displayed on PDP", expectedMsg, actualMessage);
        log.info("Successfully verified text LAST ACT on PDP");
    }
}

