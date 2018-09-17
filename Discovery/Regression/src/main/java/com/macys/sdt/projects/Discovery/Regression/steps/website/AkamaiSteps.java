package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Regression.utils.config.website.DiscoveryAkamaiUtils;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class AkamaiSteps extends StepUtils {

    public static final Logger logger = LoggerFactory.getLogger(AkamaiSteps.class);

    @Then("^I verify akamai details for '(home)' page in 'domestic' mode$")
    public void iVerifyAkamaiDetailsForHomePageInDomesticMode(String pageName) throws Throwable {
        String result = DiscoveryAkamaiUtils.validate(getAkamaiJson(pageName));
        if (result.equals("valid")) {
            logger.info("All Akamai results valid for page:"+pageName);
        } else {
            Assert.fail("Akamai results failed for page:"+pageName+"\n\n"+result);
        }
        RunConfig.akamai = false;
        Thread.sleep(1000);
    }

    /**
     * Fills Akamai details with the test data from the JSON file
     *
     */
    private static JSONObject getAkamaiJson(String pageName) {
        pageName = pageName.replaceAll(" ", "_");
        String envName = DiscoveryAkamaiUtils.getDomain().split("\\.")[0];
        String jsonText = "";
        try {
            jsonText = Utils.readTextFile(getResourceFile(pageName+"_akamai.json"));
        } catch (java.io.IOException e) {
            logger.error("Failed to load akamai JSON file: " + e);
        }
        JSONObject akamaiJSON = new JSONObject(jsonText);
        return akamaiJSON.getJSONObject(envName);
    }
}
