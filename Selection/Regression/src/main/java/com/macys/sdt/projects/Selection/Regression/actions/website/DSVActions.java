package com.macys.sdt.projects.Selection.Regression.actions.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Pawan on 8/4/2017.
 */
public class DSVActions {
    private static final Logger logger = LoggerFactory.getLogger(DSVActions.class);

    public static void getServerDetails(String server) {
        switch (server.toLowerCase()) {
            case "shopapp":
                Wait.forPageReady();
                logger.info("shop app server::" + EnvironmentDetails.getAppServer());
                Assert.assertTrue("Page is not served from ShopApp", EnvironmentDetails.getAppServer().contains("shopapp"));
                break;
            case "navapp":
                Wait.forPageReady();
                logger.info("nav app server::" + EnvironmentDetails.getAppServer());
                Assert.assertTrue("Page is not served from NavApp", EnvironmentDetails.getAppServer().contains("navapp"));
                break;
        }
    }
}