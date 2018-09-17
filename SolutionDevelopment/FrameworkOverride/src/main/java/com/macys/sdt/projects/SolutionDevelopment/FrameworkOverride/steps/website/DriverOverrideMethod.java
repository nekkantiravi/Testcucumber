package com.macys.sdt.projects.SolutionDevelopment.FrameworkOverride.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.utils.DataVault;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

public class DriverOverrideMethod extends StepUtils {
    @And("^sample test: I click the logo$")
    public void sample_test_I_click_the_logo() throws Throwable {
        Clicks.click("home.logo");
    }
}