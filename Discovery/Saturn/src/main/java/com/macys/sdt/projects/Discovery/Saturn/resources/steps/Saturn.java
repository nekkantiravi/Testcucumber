package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CreateRule;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.Rule;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.openqa.selenium.By;

import java.util.*;

import static com.macys.sdt.framework.interactions.TextBoxes.typeTextbox;
import static com.macys.sdt.framework.interactions.Wait.untilElementPresent;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Saturn {

    private static final Logger LOGGER = LoggerFactory.getLogger(Saturn.class);
    private static String threshold_value;
    public String ruleName;
    Rule rule = new Rule();
    List<String> RuleDetails = new ArrayList<String>();
    List<String> RuleDetailsUI = new ArrayList<String>();


    @Given("^I navigate to the Saturn login page$")
    public void i_navigate_to_the_Saturn_login_page() throws Throwable {
        Navigate.visit(RunConfig.url);

    }
    /*@When("^I enter login credentials$")
    public void iEnterLoginCredentials() throws Throwable {
        typeTextbox(By.id("txtUsername"), "superbauser");
        typeTextbox(By.id("txtPassword"), "haht");
        //Clicks.clickElementByText(By.className("ui-button-text"), "Login");
        Clicks.clickElementByText(By.xpath("//button[@type='submit']"), "Login");
    }*/

    @When("^I enter login \"([^\"]*)\"$")
    public void iEnterLogin(String credentials) throws Throwable {
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("credentials.json"))));
        JSONObject json_data = json.getJSONObject(credentials);
        typeTextbox("login.uname", (String) json_data.get("username"));
        typeTextbox("login.password", (String) json_data.get("pwd"));
        Clicks.clickElementByText("login.submit", "Login");
    }

    @And("^I navigate to Rules menu$")
    public void iNavigateToRulesMenu() throws Throwable {
        Wait.forPageReady();
        Clicks.hoverForSelection("login.rules");
    }

    @And("^I select Create rule$")
    public void iSelectCreateRule() throws Throwable {
        Wait.untilElementPresent(By.linkText("login.create_rule"));
        Clicks.click("login.create_rule");
    }

    @And("^I enter Rule Name, Rule Priority, Effective and Expiration dates,fob$")
    public void iEnterRuleNameRulePriorityEffectiveAndExpirationDatesFob() throws Throwable {
        Random rnd = new Random();
        String keyword = randomAlphabetic(10);
        TextBoxes.typeTextbox("login.rule_name", keyword);
        DropDowns.selectRandomValue("login.rule_priority");
        DropDowns.selectRandomValue("login.fob");
        Clicks.click("login.effective_date");
        Clicks.click("login.today_date");
        Clicks.click("login.expiry_date");
        Clicks.click("login.end_of_time");
    }

    @And("^I select \"([^\"]*)\" trigger$")
    public void iSelectTrigger(String toSelect) throws Throwable {
        String color = CreateRule.getRandomColor();
        Clicks.click("login.add_trigger");
        Clicks.click("login.trigger_type");
        DropDowns.selectByIndex("login.trigger_type", 4);
        Clicks.click("login.keyword_pattern_trigger_type");
        DropDowns.selectByIndex("login.keyword_pattern_trigger_type", 2);
        TextBoxes.typeTextNEnter("login.trigger_text", color);
        Clicks.click("login.save_trigger");

    }

    @And("^I click on \"([^\"]*)\"$")
    public void iClickOn(String arg0) throws Throwable {
        Clicks.click("login.add_action");
    }

    @And("^I select \"([^\"]*)\" action$")
    public void iSelectAction(String arg0) throws Throwable {
        // Clicks.click("login.add_action");
        Clicks.click("login.action_type");
        DropDowns.selectByText("login.action_type", "Manage Featured Facet");
    }

    @And("^I select \"([^\"]*)\" sub-action$")
    public void iSelectSubAction(String arg0) throws Throwable {
        DropDowns.selectByText("login.manage_feature_action_type", "Disable");
    }


    @And("^I select search option in  context container$")
    public void iSelectSearchOptionInContextContainer() throws Throwable {
        Clicks.click("login.context_search");

    }

    @And("^I save action$")
    public void iSaveAction() throws Throwable {
        Clicks.clickIfPresent("login.Action_popup");
       Clicks.click("login.save_action");
    }

    @Then("^I should see rule saved$")
    public void iShouldSeeRuleSaved() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Edit button is displayed", Elements.findElement("login.Edit_rule").isDisplayed());
        LOGGER.info("Verified the edit button display");


    }

    @And("^I Select \"([^\"]*)\" trigger in Create Rule Page$")
    public void iSelectTriggerInCreateRulePage(String All_Search) throws Throwable {
        Clicks.click("login.add_trigger");
        Clicks.click("login.trigger_type");
        DropDowns.selectByIndex("login.trigger_type", 1);
        Clicks.click("login.save_trigger");
        Clicks.clickElementByText("login.All_search_popup", "Ok");
    }

    @Then("^I should see Warning message \"([^\"]*)\"$")
    public void iShouldSeeWarningMessage(String Warning_message) throws Throwable {
        String message_UI = Elements.findElement("login.warning_msg").getText();
        sleep(3);
        if (Elements.findElement("login.warning_msg").isDisplayed()) {
            Assert.assertTrue("warning message displayed for sub action", (Warning_message).equals(message_UI));
        } else {
            Assert.fail("Warning message not displayed");
        }
    }

    @And("^I click Find Rules$")
    public void iClickFindRules() throws Throwable {
        untilElementPresent(By.linkText("login.find_rules"));
        Clicks.clickElementByText("login.find_rules", "Find Rules");


    }

    @And("^I select Rule$")
    public void iSelectRule() throws Throwable {
        Thread.sleep(50000);
        Wait.forPageReady();
        Clicks.click("login.select_rule");
    }

    @Then("^I confirm \"([^\"]*)\" is enabled$")
    public void iConfirmIsEnabled(String action) throws Throwable {
        List<String> actions = DropDowns.getAllValues("login.action_type");
        Assert.assertTrue("Dropdown contains Manage Featured Facet action", actions.contains(action));
    }

    @And("^I select navigation$")
    public void iSelectNavigation() throws Throwable {
        Clicks.click("login.navigation_search");
    }

    @And("^I click on  Edit  in   Add Action section$")
    public void iClickOnEditInAddActionSection() throws Throwable {
        Clicks.click("login.edit_action");
    }


    @And("^I select Automate sub-action$")
    public void iSelectAutomateSubAction() throws Throwable {
        DropDowns.selectByText("login.manage_feature_action_type", "Automate");
    }


    @And("^I should see threshold field  displaying  with text  \"([^\"]*)\"$")
    public void iShouldSeeThresholdFieldDisplayingWithText(String field_text) throws Throwable {
        String message_UI = Elements.findElement("login.Threshold_text").getText();
        sleep(3);
        if (Elements.findElement("login.Threshold_text").isDisplayed()) {
            Assert.assertTrue("Threshold text displayed for sub action", (field_text).equals(message_UI));
        } else {
            Assert.fail("Threshold text not displayed");
        }

    }

    @Then("^I should see automate sub action warning message \"([^\"]*)\"$")
    public void iShouldSeeAutomateSubActionWarningMessage(String automate_warning_message) throws Throwable {
        String message_UI = Elements.findElement("login.warning_msg").getText();
        sleep(3);
        if (Elements.findElement("login.warning_msg").isDisplayed()) {
            Assert.assertTrue("warning message displayed for automate sub action", (automate_warning_message).equals(message_UI));
        } else {
            Assert.fail("Automate sub action warning message not displayed");
        }
    }

    @And("^I save rule details$")
    public void iSaveRuleDetails() throws Throwable {
        Clicks.clickIfPresent("login.Action_popup");
        Clicks.click("login.save_editrule");

    }

    @And("^I de select apply non canvas$")
    public void iDeSelectApplyNonCanvas() throws Throwable {
        Clicks.click("login.apply_noncanvas");
        Clicks.clickIfPresent("login.Action_popup");

    }

    @When("^I change threshhold value to \"([^\"]*)\"$")
    public void iChangeThreshholdValueTo(String value) throws Throwable {
        WebElement element = Elements.findElement("login.Threshold_value");
        element.clear();
        threshold_value = value;
        TextBoxes.typeTextNEnter("login.Threshold_value", threshold_value);

        Clicks.clickIfPresent("login.Action_popup");
    }

    @When("^I enter \"([^\"]*)\" in threshold filed$")
    public void iEnterInThresholdFiled(String value) throws Throwable {
        threshold_value = value;
        TextBoxes.typeTextNEnter("login.Threshold_value", threshold_value);
        Clicks.clickIfPresent("login.Action_popup");
    }

    @And("^I Select \"([^\"]*)\" trigger in Edit Rule Page$")
    public void iSelectTriggerInEditRulePage(String All_search) throws Throwable {
        Clicks.click("login.trigger_type");
        DropDowns.selectByText("login.trigger_type",All_search);
        Clicks.clickIfPresent("login.Action_popup");
        Clicks.click("login.save_trigger");
        Clicks.clickElementByText("login.All_search_popup", "Ok");
    }

    @And("^I populate \"([^\"]*)\" trigger in Edit Rule page$")
    public void iPopulateTriggerInEditRulePage(String Result_Set) throws Throwable {
        Clicks.click("login.trigger_type");
        DropDowns.selectByText("login.trigger_type",Result_Set);
        DropDowns.selectByIndex("login.resultset_trigger", 1);
        TextBoxes.typeTextNEnter("login.tagBox", CreateRule.getRandomBrand());
        Clicks.click("login.save_trigger");
    }

    @And("^I populate \"([^\"]*)\" trigger in Create Rule page$")
    public void iPopulateTriggerInCreateRulePage(String result_set) throws Throwable {
        Clicks.click("login.add_trigger");
        Clicks.click("login.trigger_type");
        DropDowns.selectByText("login.trigger_type",result_set );
        DropDowns.selectByIndex("login.resultset_trigger", 1);
        TextBoxes.typeTextNEnter("login.tagBox", CreateRule.getRandomBrand());
        Clicks.click("login.save_trigger");
    }

    @And("^I click \"([^\"]*)\" edit trigger$")
    public void iClickEditTrigger(String keyword_trigger) throws Throwable {
        String color = CreateRule.getRandomColor();
        Clicks.click("login.trigger_type");
        DropDowns.selectByText("login.trigger_type",keyword_trigger);
        Clicks.click("login.keyword_pattern_trigger_type");
        TextBoxes.typeTextNEnter("login.trigger_text", color);
        Clicks.click("login.save_trigger");

    }

    @When("^I click on  Edit  in   Add Trigger section$")
    public void i_click_on_Edit_in_Add_Trigger_section() throws Throwable {
        Clicks.click("login.save_trigger");
    }

    @And("^I enter Rule Name$")
    public void iEnterRuleName(DataTable dt) throws Throwable {
        List<List<String>> data = dt.raw();
        rule.ruleName = data.get(0).get(0);
        TextBoxes.typeTextbox("login.rule_name", rule.ruleName);

    }
    public String getRandomPriority() {
        return String.valueOf(10+(int) (Math.random() * ((20 - 2) + 1)) * 5);
    }


}
