package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Created by yc04026 on 8/21/2017.
 */
public class Worry_No_More extends StepUtils {

    @Then("^I Should see WNM Protection plan section$")
    public void i_validate_protection_plan_section(){
        Assert.assertTrue("Worry no more protection plan section is not displaying", Elements.elementPresent("responsive_service_fees_section.rc_bigticket_protection_plan"));

    }
}
