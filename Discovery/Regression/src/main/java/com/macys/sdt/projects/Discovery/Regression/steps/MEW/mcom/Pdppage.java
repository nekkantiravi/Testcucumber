package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pdppage extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(Pdppage.class);
    @When("^I should see Add to Bag button on PDP page$")
    public void AddToBagButtonOnPdpPage() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.add_to_bag_button", 10);
        Assert.assertTrue(Elements.elementInView("product_display.add_to_bag_button"));
        log.info("Successfully navigation to a PDP page from search results page ");
    }
}
