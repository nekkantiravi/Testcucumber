package com.macys.sdt.projects.SolutionDevelopment.FrameworkTest.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

public class ElementTest extends StepUtils {
    @When("^I test project elements using all other projects resources$")
    public void i_test_project_elements_using_all_other_projects_resources() throws Throwable {
        System.out.println("Test project own element");
        By element = Elements.element("home.goto_guest_sign_in");
        Utils.threadSleep(2000, null);
        Clicks.click("home.test_element");
    }
}
