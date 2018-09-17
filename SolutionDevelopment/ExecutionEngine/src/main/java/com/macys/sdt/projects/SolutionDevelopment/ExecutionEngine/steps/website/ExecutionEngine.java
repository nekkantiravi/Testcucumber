package com.macys.sdt.projects.SolutionDevelopment.ExecutionEngine.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.SolutionDevelopment.ExecutionEngine.utils.EEUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ExecutionEngine extends StepUtils {

    private JSONObject executionParameters = new JSONObject();

    @Given("^I visit Execution Engine as a guest user$")
    public void iVisitExecutionEngineAsAGuestUser() throws Throwable {
        Navigate.visit(RunConfig.getEnvOrExParam("website"));
    }

    @When("^I goto desired subdomain$")
    public void iGotoDesiredSubDomain() throws Throwable {
        Clicks.click("home." + EEUtils.domain);
    }

    @And("^I login to subdomain using RACF id$")
    public void iLoginToSDTUsingRACFId() throws Throwable {
        shouldBeOnPage("login");
        TextBoxes.typeTextbox("login.username", RunConfig.getEnvOrExParam("username"));
        TextBoxes.typeTextbox("login.password", RunConfig.getEnvOrExParam("password"));
        Clicks.click("login.loginBtn");
    }

    @Given("^I verify subdomain$")
    public void iVerifySubdomain() throws Throwable {
        Assert.assertTrue("Not in expected URL", WebDriverManager.getCurrentUrl().contains(EEUtils.domain));
    }

    @Given("^I log in to desired subdomain")
    public void iLogInToDesiredSubdomain()   throws Throwable {
        iVisitExecutionEngineAsAGuestUser();
        iGotoDesiredSubDomain();
        iLoginToSDTUsingRACFId();
        iVerifySubdomain();
    }

    @Then("^I should see RACF id on Welcome page$")
    public void iShouldSeeRACFIdOnWelcomePage() throws Throwable {
        shouldBeOnPage("welcome");
        Assert.assertTrue("Username is not displayed on welcome page.",
                Elements.getText("welcome.verify_page").contains(RunConfig.getEnvOrExParam("username")));
    }

    @When("^I select (Slaves|Test Execution|Reports) on Welcome page$")
    public void iSelectAppOnWelcomePage(String app) throws Throwable {
        shouldBeOnPage("welcome");
        Clicks.click("welcome." + app.toLowerCase().replaceAll(" ", "_"));
    }

    @And("^I select \"([^\"]*)\" SDT Project if on SDT subdomain$")
    public void iSelectSDTProjectOnTestExecutionPage(String project) throws Throwable {
        shouldBeOnPage("test_execution");
        if (EEUtils.isSdtRepo()) {
            Elements.elementShouldBePresent("test_execution.sdt_project_dropdown");
            List<String> projects = DropDowns.getAllValues("test_execution.sdt_project_dropdown");
            Assert.assertFalse("No projects available for selection", projects.isEmpty());
            if (project.equals("random")) {
                Collections.shuffle(projects);
                project = projects.get(0);
            }
            DropDowns.selectByText("test_execution.sdt_project_dropdown", project);
        }
    }

    @And("^I select \"([^\"]*)\" tag on Test Execution page$")
    public void iSelectTagOnTestExecutionPage(String tag) throws Throwable {
        Elements.elementShouldBePresent("test_execution.non_selected_tags");

        // QAA is having very long list of tags
        if (EEUtils.isQaaRepo()) {
            pausePageHangWatchDog();
            if (tag.contains("random")) {
                TextBoxes.typeTextbox("test_execution.filter_non_selected_tags", tag.contains("mew") ? "use_mew_dsv" : "use_dsv");
                Utils.threadSleep(1000, "Wait for tags filter");
            }
        }

        List<String> tags = DropDowns.getAllValues("test_execution.non_selected_tags");
        Assert.assertFalse("No tags available for selection", tags.isEmpty());
        if (tag.contains("random")) {
            Collections.shuffle(tags);
            tag = tags.get(0);
        }
        DropDowns.selectByText("test_execution.non_selected_tags", tag);

        // If we click add without wait Tag is not getting added
        Utils.threadSleep(1000, "Wait for tags selection");

        Clicks.click("test_execution.add_tags");
        Assert.assertTrue("Unable to select tag : " + tag, DropDowns.getAllValues("test_execution.selected_tags").contains(tag));
        executionParameters.put("selected_tags", tag);
    }

    @And("^I select \"([^\"]*)\" feature on Test Execution page$")
    public void iSelectFeatureOnTestExecutionPage(String feature) throws Throwable {
        Elements.elementShouldBePresent("test_execution.non_selected_features");
        List<String> features = DropDowns.getAllValues("test_execution.non_selected_features");
        Assert.assertFalse("No features available for selection", features.isEmpty());
        if (feature.equals("random")) {
            Collections.shuffle(features);
            feature = features.get(0);
        }
        DropDowns.selectByText("test_execution.non_selected_features", feature);
        Clicks.click("test_execution.add_features");
        Assert.assertTrue("Unable to select feature : " + feature, DropDowns.getAllValues("test_execution.selected_features").contains(feature));
        executionParameters.put("selected_features", feature);
    }

    @Then("^I select \"([^\"]*)\" scenario on Test Execution page$")
    public void iSelectScenarioOnTestExecutionPage(String scenario) throws Throwable {
        Elements.elementShouldBePresent("test_execution.scenarios");
        String index = null;
        List<WebElement> scenariosElement = Elements.findElements("test_execution.scenarios");
        Assert.assertFalse("No scenarios available for selection", scenariosElement.isEmpty());
        if (scenario.equals("random")) {
            index = String.valueOf(new Random().nextInt(scenariosElement.size()) + 1);
        } else {
            for (int i = 1; i <= scenariosElement.size(); i++) {
                if (Elements.getText(Elements.paramElement("test_execution.scenario_name", String.valueOf(i))).contains(scenario)) {
                    index = String.valueOf(i);
                    break;
                }
            }
        }
        Clicks.selectCheckbox(Elements.paramElement("test_execution.scenario_checkbox", index));
    }

    @Then("^I verify following sections are loaded on (Test Execution|Report) page:$")
    public void iVerifyFollowingSectionsAreLoadedOnTestExecutionPage(String page, List<String> sections) throws Throwable {
        String pageName = page.toLowerCase().replaceAll(" ", "_");
        shouldBeOnPage(pageName);
        List<String> sectionsNotPresent = sections.stream().filter(section -> !Elements.elementPresent(
                pageName + "." + section.toLowerCase().replaceAll(" ", "_") + "_section")).collect(Collectors.toList());
        Assert.assertTrue("Sections not displayed on " + page + "page : " + sectionsNotPresent, sectionsNotPresent.isEmpty());
    }

    @Then("^I verify meta data and parameters section on Test Execution page$")
    public void iVerifyMetaDataAndParametersSectionOnTestExecutionPage() throws Throwable {
        shouldBeOnPage("test_execution");
        ArrayList<String> elementsNotVisible = new ArrayList<>();

        String[] dropdowns = new String[]{"coremetric_mode_dropdown", "jenkins_master_dropdown", "run_time_recurence_dropdown", "segmentation_dropdown",
                "run_priority_dropdown", "site_type_dropdown", "channel_type_dropdown", "os_type_dropdown",
                "browser_dropdown", "branch_dropdown", "repo_dropdown"};
        for (String dropdown : dropdowns) {
            if (!Elements.elementPresent("test_execution." + dropdown) || DropDowns.getAllValues("test_execution." + dropdown).isEmpty()) {
                elementsNotVisible.add(dropdown);
            }
        }

        String[] others = new String[]{"load_template_btn", "save_template_btn", "delete_template_btn", "notify_final_result_only_option",
                "add_error_category_icon", "error_categorization_section", "project_template_field", "project_name_field", "version_one_story_field", "project_manager_field",
                "developer_lead_field", "test_lead_field", "notify_emails_field", "auto_retry_option", "push_to_dashboard_option", "run_date_time", "end_date_time",
                "neg_tags_field", "notify_duration_field", "website_field", "ex_params_field", "node_label_field"};
        for (String element : others) {
            if (!Elements.elementPresent("test_execution." + element)) {
                elementsNotVisible.add(element);
            }
        }

        if (EEUtils.isSdtRepo()) {
            if (!Elements.elementPresent("test_execution.sdt_project_dropdown") || DropDowns.getAllValues("test_execution.sdt_project_dropdown").isEmpty()) {
                elementsNotVisible.add("sdt_project_dropdown");
            }
        }

        Assert.assertTrue("Verification failed for the elements: " + elementsNotVisible, elementsNotVisible.isEmpty());
    }

    @When("^I add \"([^\"]*)\" Error Category on Test Execution page$")
    public void iAddErrorCategoryOnTestExecutionPage(String category) throws Throwable {
        shouldBeOnPage("test_execution");
        int noOfRows = Elements.findElements("test_execution.error_category_rows").size();
        Clicks.click("test_execution.add_error_category");
        Assert.assertEquals("Error Category is not added", noOfRows + 1, Elements.findElements("test_execution.error_category_rows").size());
        TextBoxes.typeTextbox(Elements.paramElement("test_execution.error_category_name", String.valueOf(noOfRows)), category);
    }

    @Then("^I should (|not )see the \"([^\"]*)\" Error Category on Test Execution page$")
    public void iShouldSeeTheErrorCategoryOnTestExecutionPage(String see, String category) throws Throwable {
        boolean found = false;
        int noOfRows = Elements.findElements("test_execution.error_category_rows").size();
        for (int i = 0; i < noOfRows; i++) {
            if (Elements.getElementAttribute(Elements.paramElement("test_execution.error_category_name", String.valueOf(i)), "value").equals(category)) {
                found = true;
                break;
            }
        }
        if (see.equals("not ")) {
            Assert.assertFalse("Error Category " + category + "is present", found);
        } else {
            Assert.assertTrue("Error Category " + category + "is not present", found);
        }
    }

    @When("^I delete \"([^\"]*)\" Error Category on Test Execution page$")
    public void iDeleteErrorCategoryOnTestExecutionPage(String category) throws Throwable {
        int noOfRows = Elements.findElements("test_execution.error_category_rows").size();
        String rowNumber = null;
        for (int i = 0; i < noOfRows; i++) {
            if (Elements.getElementAttribute(Elements.paramElement("test_execution.error_category_name", String.valueOf(i)), "value").equals(category)) {
                rowNumber = String.valueOf(i);
                break;
            }
        }
        Assert.assertNotNull("Error Category " + category + "is not present", rowNumber);
        Clicks.click(Elements.paramElement("test_execution.delete_error_category", rowNumber));
        Assert.assertEquals("Error Category " + category + "is not deleted", noOfRows - 1, Elements.findElements("test_execution.error_category_rows").size());
    }

    @And("^I select a random report from Reports Tree page$")
    public void iSelectARandomReportFromReportsTreePage() throws Throwable {
        shouldBeOnPage("reports_tree");
        Clicks.click("reports_tree.expand_executions");
        List<WebElement> contents = Elements.findElements("reports_tree.projects", WebElement::isDisplayed);
        for (int i = 0; i < 10 && !contents.isEmpty(); i++) {
            Collections.shuffle(contents);
            Clicks.click(Elements.paramElement("reports_tree.select", contents.get(0).getText()));
            Utils.threadSleep(1000, "Wait for Reports Tree to Expand");
            if (WebDriverManager.getCurrentUrl().contains("report")) {
                break;
            }
            contents = Elements.findElements(Elements.paramElement("reports_tree.contents", contents.get(0).getText()), WebElement::isDisplayed);
        }
    }

    @And("^I create a new project template$")
    public void iCreateANewProjectTemplate() throws Throwable {
        shouldBeOnPage("test_execution");
        JSONObject json = new JSONObject(Utils.readTextFile(Utils.getResourceFile("execution_parameters.json")));
        if (EEUtils.isQaaRepo()) {
            json.remove("sdt_project_dropdown");
            json.put("repo_dropdown", "QAA");
        }
        Iterator keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            if (key.contains("_dropdown")) {
                DropDowns.selectByText("test_execution." + key, json.getString(key));
            } else if (key.contains("_field")) {
                TextBoxes.typeTextbox("test_execution." + key, json.getString(key));
            }
            executionParameters.put(key, json.get(key));
        }
        iSelectTagOnTestExecutionPage("random");
        iSelectFeatureOnTestExecutionPage("random");
        Clicks.click("test_execution.save_template_btn");
        String templateName = executionParameters.getString("project_template_name");
        TextBoxes.typeTextbox("test_execution.project_template_name", templateName);
        Clicks.click("test_execution.dialog_save_btn");
        Assert.assertTrue("Unable to create new project template " + templateName,
                Wait.until(() -> Elements.getText("test_execution.dialog_content").contains(templateName + " has been successfully saved")));
        Clicks.click("test_execution.dialog_close");
    }

    @When("^I load \"([^\"]*)\" project template$")
    public void iLoadProjectTemplate(String templateName) throws Throwable {
        shouldBeOnPage("test_execution");
        if (templateName.equals("the created")) {
            templateName = RunConfig.getEnvOrExParam("username").toUpperCase() + "." + executionParameters.getString("project_template_name");
        }
        TextBoxes.typeTextbox("test_execution.project_template_field", templateName);
        Clicks.click("test_execution.load_template_btn");
        Assert.assertTrue("Unable to load project template " + templateName,
                Wait.until(() -> !Elements.getElementAttribute("test_execution.project_name_field", "value").isEmpty()));
    }

    @Then("^I should see project template with pre-populated data$")
    public void iShouldSeeProjectTemplateWithPrePopulatedData() throws Throwable {
        ArrayList<String> elementsNotMatched = new ArrayList<>();
        Iterator keys = executionParameters.keys();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            if (key.contains("_field")) {
                if (!executionParameters.getString(key).equals(Elements.getElementAttribute(("test_execution." + key), "value"))) {
                    elementsNotMatched.add(key);
                }
            } else if (key.contains("_dropdown")) {
                if (!DropDowns.getSelectedValue(Elements.element("test_execution." + key)).equals((executionParameters.getString(key)))) {
                    elementsNotMatched.add(key);
                }
            } else if (key.equals("selected_tags") || key.equals("selected_features")) {
                if (!DropDowns.getAllValues(Elements.element("test_execution." + key)).contains((executionParameters.getString(key)))) {
                    elementsNotMatched.add(key);
                }
            }
        }
        Assert.assertTrue("Verification failed for: " + elementsNotMatched, elementsNotMatched.isEmpty());
    }

    @And("^I should be able to delete the created project template$")
    public void iShouldBeAbleToDeleteTheCreatedProjectTemplate() throws Throwable {
        String templateName = executionParameters.getString("project_template_name");
        TextBoxes.typeTextbox("test_execution.project_template_field", RunConfig.getEnvOrExParam("username").toUpperCase() + "." + templateName);
        Clicks.click("test_execution.delete_template_btn");
        Assert.assertTrue("Template delete dialog not loaded" + templateName,
                Wait.until(() -> Elements.getText("test_execution.delete_dialog_title").contains(templateName)));
        Clicks.click("test_execution.delete_dialog_continue_btn");
        Assert.assertTrue("Unable to delete template " + templateName,
                Wait.until(() -> Elements.getText("test_execution.dialog_content").contains(templateName + " has been deleted")));
        Clicks.click("test_execution.dialog_close");
    }

    @Then("^Step that should (pass|fail) always(?: with (ENV|Test|TBD) error)?$")
    public void stepThatShouldPassAlways(String status, String type) throws Throwable {
        if (status.equals("fail")) {
            if (type.equals("TBD")) {
                type = "";
            }
            Assert.fail("ERROR - " + type + " : Step failed for testing Execution Engine");
        }
    }

    @And("^I submit scenarios for execution on Test Execution page$")
    public void iSubmitScenariosForExecutionOnTestExecutionPage() throws Throwable {
        Wait.untilElementPresent("test_execution.scenarios");
        Clicks.click("test_execution.submit_execution");
        Elements.elementShouldBePresent("test_execution.create_job_dialog_title");
        Elements.elementShouldBePresent("test_execution.create_job_dialog_no_btn");
        Clicks.click("test_execution.create_job_dialog_no_btn");
        Assert.assertTrue("Unable to submit scenarios for execution",
                Wait.until(() -> Elements.getText("test_execution.dialog_content").contains("has been successfully placed in the execution queue")));
        Clicks.click("test_execution.dialog_close");
    }

    @And("^I should (|not )see \"([^\"]*)\" in execution queue on Test Execution page$")
    public void iShouldSeeInExecutionQueueOnTestExecutionPage(String see, String name) throws Throwable {
        if (EEUtils.isQaaRepo()) {
            TextBoxes.typeTextbox("test_execution.queue_filter", name);
        }
        Boolean found = false;
        for (WebElement element : Elements.findElements("test_execution.queue_names")) {
            if (element.getText().equals(name)) {
                found = true;
                break;
            }
        }
        if (see.equals("not ")) {
            Assert.assertFalse(name + " is present in execution queue", found);
        } else {
            Assert.assertTrue(name + " is not present in execution queue", found);
        }
    }

    @Then("^I create \"([^\"]*)\" disabled project$")
    public void iCreateDisabledProject(String name) throws Throwable {
        shouldBeOnPage("test_execution");
        iLoadProjectTemplate("YC05RT4.Test capability to trigger execution");
        TextBoxes.typeTextbox("test_execution.project_name_field", name);
        iSelectScenarioOnTestExecutionPage("random");
        Clicks.click("test_execution.submit_execution");
        Elements.elementShouldBePresent("test_execution.create_job_dialog_title");
        Elements.elementShouldBePresent("test_execution.create_job_dialog_yes_btn");
        Clicks.click("test_execution.create_job_dialog_yes_btn");
        Assert.assertTrue("Unable to create disabled project " + name,
                Wait.until(() -> Elements.getText("test_execution.dialog_content").contains("has been successfully placed and disabled")));
        Clicks.click("test_execution.dialog_close");
    }

    @When("^I delete \"([^\"]*)\" from the execution queue on Test Execution page$")
    public void iDeleteFromTheExecutionQueueOnTestExecutionPage(String name) throws Throwable {
        List<WebElement> jobsInQueue = Elements.findElements("test_execution.queue_names");
        int numberOfJobsInQueue = jobsInQueue.size();
        String jobNumber = null;
        for (int i = 0; i < numberOfJobsInQueue; i++) {
            if (jobsInQueue.get(i).getText().equals(name)) {
                jobNumber = String.valueOf(i + 1);
                break;
            }
        }
        Assert.assertNotNull(name + " not present in execution queue", jobNumber);
        Clicks.click(Elements.paramElement("test_execution.queue_edit", jobNumber));
        Assert.assertTrue("Update queue dialog not loaded for " + name,
                Wait.until(() -> Elements.getElementAttribute("test_execution.dialog_project_name", "value").equals(name)));
        Clicks.click("test_execution.dialog_delete_btn");
        Assert.assertTrue("Unable to create disabled project " + name,
                Wait.until(() -> Elements.getText("test_execution.dialog_content").contains("successful deleted")));
        Clicks.click("test_execution.dialog_close");
    }

    @When("^I create \"([^\"]*)\" Trigger for \"([^\"]*)\" project$")
    public void iCreateTriggerForProject(String triggerName, String projectName) throws Throwable {
        Clicks.click("test_execution.create_trigger");
        Assert.assertTrue("Create Trigger dialog not loaded",
                Wait.until(() -> Elements.getText("test_execution.dialog_title").equals("Create Trigger")));
        Elements.elementShouldBePresent("test_execution.trigger_name_field");
        TextBoxes.typeTextbox("test_execution.trigger_name_field", triggerName);
        DropDowns.selectByText("test_execution.dialog_non_selected_list", projectName);
        Clicks.click("test_execution.dialog_add_arrow");
        DropDowns.getAllValues("test_execution.dialog_selected_list").contains(projectName);
        Clicks.click("test_execution.dialog_save_btn");
        Assert.assertTrue("Unable to delete Trigger" + triggerName,
                Wait.until(() -> Elements.getText("test_execution.dialog_content").equals(projectName)));
        Clicks.click("test_execution.dialog_close");
    }

    @And("^I should (|not )see \"([^\"]*)\" Trigger for \"([^\"]*)\" project$")
    public void iShouldSeeTriggerForProject(String see, String triggerName, String projectName) throws Throwable {
        if (EEUtils.isQaaRepo()) {
            TextBoxes.typeTextbox("test_execution.queue_filter", projectName);
        }
        List<WebElement> jobsInQueue = Elements.findElements("test_execution.queue_names");
        int numberOfJobsInQueue = jobsInQueue.size();
        String jobNumber = null;
        for (int i = 0; i < numberOfJobsInQueue; i++) {
            if (jobsInQueue.get(i).getText().equals(projectName)) {
                jobNumber = String.valueOf(i + 1);
                break;
            }
        }
        Assert.assertNotNull(projectName + " not present in execution queue", jobNumber);
        Boolean found = Elements.getText(Elements.paramElement("test_execution.queue_triggers", jobNumber)).contains(triggerName);
        if (see.equals("not ")) {
            Assert.assertFalse(triggerName + " is present for " + projectName, found);
        } else {
            Assert.assertTrue(triggerName + " is not present for " + projectName, found);
        }
    }

    @When("^I delete \"([^\"]*)\" Trigger$")
    public void iDeleteTrigger(String triggerName) throws Throwable {
        Clicks.click(Elements.paramElement("test_execution.edit_trigger", triggerName));
        Assert.assertTrue("Edit Trigger dialog not loaded for " + triggerName,
                Wait.until(() -> Elements.getText("test_execution.dialog_title").equals("Edit Trigger " + triggerName)));
        Clicks.click("test_execution.dialog_delete_btn");
        Assert.assertTrue("Unable to delete Trigger" + triggerName,
                Wait.until(() -> Elements.getText("test_execution.dialog_content").equals(triggerName + " has been deleted")));
        Clicks.click("test_execution.dialog_close");
    }

    @And("^I navigate to a report of \"([^\"]*)\" project generated within last (\\d+) days")
    public void iNavigateToAReportOfProjectGeneratedWithInLastDays(String projectName, long days) throws Throwable {
        shouldBeOnPage("reports_tree");
        Clicks.click("reports_tree.expand_executions");
        Assert.assertTrue("Reports folder not found in Reports Tree for project " + projectName,
                Elements.elementPresent(Elements.paramElement("reports_tree.select", projectName)));

        Clicks.click(Elements.paramElement("reports_tree.select", projectName));
        Utils.threadSleep(1000, "Wait for Reports Tree to Expand");
        List<WebElement> contents = Elements.findElements(Elements.paramElement("reports_tree.contents", projectName), WebElement::isDisplayed);
        String fileNameRegex = "(\\d{4}[_][a-zA-Z]+[_]\\d{2}[_]\\d{2}[_]\\d{2}[_]\\d{2})";
        for (int i = 0; i < 10 && !contents.isEmpty(); i++) {
            String fileName = contents.get(0).getText();
            if (fileName.matches(fileNameRegex)) {
                break;
            }
            Clicks.click(Elements.paramElement("reports_tree.select", fileName));
            Utils.threadSleep(1000, "Wait for Reports Tree to Expand");
            contents = Elements.findElements(Elements.paramElement("reports_tree.contents", fileName), WebElement::isDisplayed);
        }
        Assert.assertFalse("No reports found for the project " + projectName, contents.isEmpty());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMMM_dd_hh_mm_ss");
        String reportName = null;
        for (int i = contents.size() - 1; i >= 0; i--) {
            WebElement content = contents.get(i);
            Date date = formatter.parse(content.getText());
            long difference = new Date().getTime() - date.getTime();
            long differenceInMinutes = (difference / (60 * 1000));
            if (differenceInMinutes <= (60 * 24 * days) && differenceInMinutes > 30) {
                reportName = content.getText();
                break;
            }
        }
        Assert.assertNotNull("No report found for the project " + projectName + " generated within last " + days + " days", reportName);
        Clicks.click(Elements.paramElement("reports_tree.select", reportName));
        shouldBeOnPage("report");
    }

    @And("^I should see (\\d+) scenario(?:s)? (failed|passed) out of (\\d+) scenario(?:s)?$")
    public void iShouldSeeScenariosFailedOutOfScenarios(int expectedCount, String result, int expectedTotal) throws Throwable {
        Assert.assertTrue("Total scenario count is not " + expectedTotal, Elements.getText("report.total_scenarios_count").equals(String.valueOf(expectedTotal)));
        Assert.assertEquals(result + " count is not " + expectedCount, expectedCount, Utils.parseInt(Elements.getText("report.total_" + result + "_count"), 0));
    }

    @And("^I should see (\\d+) scenario(?:s)? failed due to \"([^\"]*)\" error$")
    public void iShouldSeeScenarioFailedDueTo(int expectedCount, String failedReason) throws Throwable {
        Assert.assertEquals(expectedCount, Utils.parseInt(Elements.getText("report.fail_count_due_to_" + failedReason.toLowerCase()), 0));
    }

    @And("^I should see \"([^\"]*)\" (pass|fail) percentage$")
    public void iShouldSeePassPercentage(String percentage, String result) throws Throwable {
        Assert.assertEquals(percentage + "% " + result + "in not displayed", percentage + "%", Elements.getText("report." + result + "_percentage"));
    }

    @And("^I should see (\\d+) build(?:s)? (?:are)?(?:is)? generated in the report table$")
    public void iShouldSeeBuildsAreGeneratedInTheReportTable(int expectedCount) throws Throwable {
        Assert.assertEquals(expectedCount + "builds are not displayed in report table", expectedCount, Elements.findElements("report.build_rows").size());
    }

    @And("^I verify the count of the scenarios in the report table:$")
    public void iVerifyTheCountOfTheScenariosInTheReportTable(Map<String, String> context) throws Throwable {
        for (Map.Entry<String, String> entry : context.entrySet()) {
            Assert.assertEquals(entry.getValue() + " is not displayed for " + entry.getKey(), entry.getValue(), String.valueOf(getTotalCount(entry.getKey())));
        }
    }

    public int getTotalCount(String columnName) {
        List<WebElement> contents = Elements.findElements("report.table_header_names");
        int numberOfBuilds = Elements.findElements("report.build_rows").size();
        int totalCount = 0;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).getText().equalsIgnoreCase(columnName)) {
                for (int j = 1; j <= numberOfBuilds; j++) {
                    List<WebElement> elements = Elements.findElements(Elements.paramElement("report.build_row_values", String.valueOf(j)));
                    totalCount += Utils.parseInt(elements.get(i).getText(), 0);
                }
            }
        }
        return totalCount;
    }

    @And("^I select \"([^\"]*)\" channel on Test Execution page$")
    public void iSelectChannelOnTestExecutionPage(String channel) throws Throwable {
        DropDowns.selectByText("test_execution.branch_dropdown", "master");
        DropDowns.selectByText("test_execution.channel_type_dropdown", channel);
    }

    @Then("^I should see \"([^\"]*)\" in feature files path$")
    public void iShouldSeeInFeatureFilesPath(String path) throws Throwable {
        Elements.elementShouldBePresent("test_execution.non_selected_features");
        List<String> featureFiles = DropDowns.getAllValues("test_execution.non_selected_features");
        Assert.assertFalse("No features available for selection", featureFiles.isEmpty());
        for(String featurePath : featureFiles) {
            Assert.assertTrue(featurePath + " is not from " + path, featurePath.contains(path));
        }
    }
}
