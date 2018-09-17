package com.macys.sdt.projects.Marketing.StarRewards.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;

public class Sample extends StepUtils {
    @And("^sample testing: I click the logo$")
    public void sample_testing_I_click_the_logo() throws Throwable {
        Clicks.click("home.logo");
    }
}