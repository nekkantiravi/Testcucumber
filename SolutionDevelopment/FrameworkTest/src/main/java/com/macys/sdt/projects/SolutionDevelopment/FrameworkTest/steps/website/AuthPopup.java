package com.macys.sdt.projects.SolutionDevelopment.FrameworkTest.steps.website;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.java.en.Given;

public class AuthPopup extends StepUtils {
    @Given("^I visit Authentication sample page$")
    public void i_visit_authentication_sample_page() throws Throwable {
        // Authentication Test site
        Navigate.visit("https://www.engprod-charter.net/");
        Wait.forPageReady();
        Utils.threadSleep(10000, null);
    }

    @Given("^I login Authentication sample page$")
    public void i_login_authentication_sample_page() throws Throwable {
        System.out.println("Hello World!");
    }
}
