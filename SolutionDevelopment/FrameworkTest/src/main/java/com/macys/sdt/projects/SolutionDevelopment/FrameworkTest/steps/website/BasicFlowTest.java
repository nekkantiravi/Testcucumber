package com.macys.sdt.projects.SolutionDevelopment.FrameworkTest.steps.website;

import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;

public class BasicFlowTest extends StepUtils {
    @Given("^I visit a sample page$")
    public void i_visit_a_sample_page() throws Throwable {
        System.out.println("BasicFlowTest-001: project step");
    }
}