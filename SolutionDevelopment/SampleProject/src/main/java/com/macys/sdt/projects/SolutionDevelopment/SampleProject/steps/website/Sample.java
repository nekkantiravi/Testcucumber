package com.macys.sdt.projects.SolutionDevelopment.SampleProject.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.utils.DataVault;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;

public class Sample extends StepUtils {
    @And("^sample test: I click the logo$")
    public void sample_test_I_click_the_logo() throws Throwable {
        Clicks.click("home.logo");
    }

    @And("^sample test: I navigate to sample page$")
    public void sample_test_I_navigate_to_sample_page() throws Throwable {
        Navigate.visit("home");
        Clicks.click("home.verify_page");
        Clicks.click("home.test_element");
    }

    @And("^I test DataVault$")
    public void iTestDataVault() throws Throwable {
        System.out.println("-->>>> Vault data example param 'please' = " + new DataVault("m526092").getParam("please"));
    }
}