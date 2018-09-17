package com.macys.sdt.projects.Marketing.StarRewards.steps.responsive.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;

public class Sample extends StepUtils {
    @And("^sample testing: I navigate to sample page$")
    public void sample_testing_I_navigate_to_sample_page() throws Throwable {
        Navigate.visit("home");
        Clicks.click("home.verify_page");
        Clicks.click("home.test_element");
    }
}