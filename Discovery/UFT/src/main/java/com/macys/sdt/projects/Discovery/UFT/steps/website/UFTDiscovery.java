package com.macys.sdt.projects.Discovery.UFT.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UFTDiscovery extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(UFTDiscovery.class);

    @And("^I verify the customer review is displayed in quick peek for chanel product$")
    public void iVerifyThatTheCustomerRatingIsDisplayedInQuickPeek() throws Throwable {
        Wait.untilElementPresent("product_thumbnails.product_thumbnail_with_review");
        Assert.assertTrue("Customer review is not displayed",
                Elements.elementPresent("product_thumbnails.product_thumbnail_with_review"));
        logger.info("Verified that the customer review is displayed in quick peek on chanel browse page");
    }
}