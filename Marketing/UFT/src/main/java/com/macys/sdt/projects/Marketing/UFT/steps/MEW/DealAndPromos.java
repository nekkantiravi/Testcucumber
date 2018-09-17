package com.macys.sdt.projects.Marketing.UFT.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DealAndPromos extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(DealAndPromos.class);

    @And("^I click on expiring soon tab$")
    public void expiringSoon() throws Throwable {
        Clicks.click("deal_and_promos.expiring_soon");
        Wait.untilElementPresent("deal_and_promos.promo_list");
        Assert.assertTrue("Oops Technical error on Expiring Soon tab",
                Wait.untilElementPresent("deal_and_promos.promo_list"));
        log.info("Expected deal & promos are showing on Expiring Soon tab");
    }
}

