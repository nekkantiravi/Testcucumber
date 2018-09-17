package com.macys.sdt.projects.SolutionDevelopment.ExecutionEngineReact.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class ExecutionEngineReact extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionEngineReact.class);

    @Given("^I login to Execution Engine$")
    public void iLoginToExecutionEngine() throws Throwable {
        Navigate.visit(RunConfig.getEnvOrExParam("website"));
        shouldBeOnPage("login");

    }

    @When("^I visit Execution Engine Report Page$")
    public void iVisitExecutionEngineReportPage() throws Throwable {
        Navigate.visit(RunConfig.getEnvOrExParam("website"));
    }

    @Then("^I should be on Execution Engine Report Page$")
    public void iShouldBeOnExecutionEngineReportPage() throws Throwable {
        if (!Wait.untilElementPresent("report.page_size"))
            Assert.fail("Execution Engine Report Page is not up!");
        System.out.println("Execution Engine Report Page loaded");
    }

    @Then("^I should verify existance of all elements on Execution Engine Report Page$")
    public void iShouldVerifyExistanceOfAllElementsOnExecutionEngineReportPage() throws Throwable {
        verifyExistanceElement("report.ee_instance_title");
        verifyExistanceElement("report.main_report_tab");
        verifyExistanceElement("report.main_exection_tab");
        verifyExistanceElement("report.main_menu");
        verifyExistanceElement("report.copy_table_clipboard");
    }

    private void verifyExistanceElement(String element) throws Throwable {
        if (Wait.untilElementPresent(element))
            System.out.println("Checking Element existence of '" + element + "'... OK");
        else
            Assert.fail("Checking Element existence of '" + element + "'... Not exist");
    }

    @Given("^I visit the execution engine page$")
    public void iVisitTheExecutionEnginePage() throws Throwable {
        Navigate.visit(RunConfig.getEnvOrExParam("website"));
        shouldBeOnPage("login");
    }


    @And("^I log into Execution Engine$")
    public void iLogIntoExecutionEngine() throws Throwable {
        TextBoxes.typeTextbox("login.user_name", RunConfig.getEnvOrExParam("username"));
        TextBoxes.typeTextbox("login.password", RunConfig.getEnvOrExParam("password"));
        Clicks.click("login.login_Button");
    }


    @And("^I load my template \"([^\"]*)\"$")
    public void i_load_my_template(String template) throws Throwable {
        Clicks.click("execution.load_template_button");
        TextBoxes.typeTextbox("select_a_template.project_template", template);

    }
}

