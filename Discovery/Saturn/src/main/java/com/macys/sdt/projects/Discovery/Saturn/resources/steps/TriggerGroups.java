package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

import static java.lang.System.currentTimeMillis;


public class TriggerGroups {

    String jsonValueFileName = "trigger_groups";
    int index = 0;
    String categoryId;

    @When("^I select \"([^\"]*)\" trigger on Trigger Group page$")
    public void i_select_trigger_on_Trigger_Group_page(String triggerType) throws Throwable {
        Clicks.click("trigger_groups.add_trigger");
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("trigger_sequence", jsonValueFileName) + index), triggerType);
    }

    @Then("^I see \"([^\"]*)\" as default value for Keyword Pattern Trigger on Trigger Group page$")
    public void i_see_as_default_value_for_Keyword_Pattern_Trigger_on_Trigger_Group_page(String matchType) throws Throwable {
        Assert.assertTrue("Trigger page - Keyword Pattern Trigger default Match type is not 'Has exact match to'", Elements.findElement("trigger_groups.keyword_patter_match_type").getText().equals(matchType));
    }

    @When("^I click on Add Trigger on Trigger Group page$")
    public void i_click_on_Add_Trigger_on_Trigger_Group_page() throws Throwable {
        Clicks.click("trigger_groups.add_trigger");
    }

    @Then("^I see \"([^\"]*)\" in alphanumeric order on Trigger Group page$")
    public void i_see_in_alphanumeric_order_on_Trigger_Group_page(String valuesType, DataTable data) throws Throwable {
        switch (valuesType) {
            case "Trigger Types":
                List<String> triggerTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("trigger_sequence", jsonValueFileName) + index));
                Assert.assertEquals("The Trigger Types on Trigger Group are not sorted in alphanumeric order", CommonUtils.getSortedList(triggerTypes), data.asList(String.class));
                break;
            case "Result Set Operator Types":
                List<String> resultSetOperatorTypes = DropDowns.getAllValues("trigger_groups.result_set_trigger_operator_types");
                Assert.assertEquals("Result Set Operator Types on Trigger Group  are not sorted in alphanumeric order", CommonUtils.getSortedList(resultSetOperatorTypes), data.asList(String.class));
                break;
        }
    }

    @When("^I enter Trigger Group name as \"([^\"]*)\"$")
    public void i_enter_Trigger_Group_name_as(String triggerGroupName) throws Throwable {
        TextBoxes.typeTextbox("trigger_groups.trigger_group_name", triggerGroupName + currentTimeMillis());
    }

    @When("^I save Trigger on Trigger Group page$")
    public void i_save_Trigger_on_Trigger_Group_page() throws Throwable {
        Wait.forPageReady();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + index + "').click()");
        index++;
     }

    @When("^I click Ok on Trigger Group All Search alter popup$")
    public void i_click_Ok_on_Trigger_Group_All_Search_alter_popup() throws Throwable {
        Clicks.click("trigger_groups.all_search_alert_Ok");
        Wait.ajaxDone();
    }

    @When("^I populate Category Ids trigger on Trigger Group page$")
    public void i_populate_Category_Ids_trigger_on_Trigger_Group_page() throws Throwable {
        if (StepUtils.bloomingdales()) {
            categoryId = "193";
        } else {
            categoryId = "199";
        }
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("category_id", jsonValueFileName) + index), categoryId);
    }

    @When("^I save Trigger Group$")
    public void i_save_Trigger_Group() throws Throwable {
        Clicks.click("trigger_groups.save_trigger_group");
        Wait.ajaxDone();
        Wait.forPageReady();
    }

    @Then("^I confirm Trigger Group is saved$")
    public void i_confirm_Trigger_Group_is_saved() throws Throwable {
        Assert.assertTrue("Trigger Group is not saved successfullY", Elements.findElement("trigger_groups.edit_trigger_group").isDisplayed());
    }

    @Then("^I see operator \"([^\"]*)\" selected by default with \"([^\"]*)\" percent on Trigger Group page$")
    public void i_see_operator_selected_by_default_with_percent_on_Trigger_Group_page(String operatorType, String percentageValue) throws Throwable {
        Assert.assertEquals("Result Set default selected operator type is not '>'", operatorType, DropDowns.getSelectedValue(Elements.element("trigger_groups.result_set_operator")));
        Assert.assertEquals("Result Set default selected percetnage is not '75'", percentageValue, Elements.findElement("trigger_groups.result_set_percentage").getAttribute("value"));
    }

    @When("^I populate keyword pattern trigger on Trigger Group page$")
    public void i_populate_keyword_pattern_trigger_on_Trigger_Group_page() throws Throwable {
        DropDowns.selectRandomValue(By.id(CommonUtils.getJsonValue("keyword_pattern_dropdown", jsonValueFileName)+ index + CommonUtils.getJsonValue("div_val", jsonValueFileName)));
        TextBoxes.typeTextNEnter(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + index + CommonUtils.getJsonValue("div_val", jsonValueFileName)
                                                                + CommonUtils.getJsonValue("keyword_pattern_value", jsonValueFileName)), CommonUtils.getJsonValue("keyword_pattern_value_data", jsonValueFileName));
    }

    @Then("^I should see error message \"([^\"]*)\"$")
    public void i_should_see_error_message(String expectedAlertMessage) throws Throwable {
        String alertMessage = Elements.findElement(By.xpath(CommonUtils.getJsonValue("alert_message", jsonValueFileName))).getText();
        Assert.assertEquals("The alert message doesn't match", expectedAlertMessage, alertMessage);
    }

    @Then("^I should not see multiple trigger operator box on Trigger Group page$")
    public void i_should_not_see_multiple_trigger_operator_box_on_Trigger_Group_page() throws Throwable {
        Assert.assertEquals("Multiple Trigger Operator box is visible on Create Rule page", 0, (Elements.findElements( "trigger_groups.multiple_trigger_operator_label")).size());
    }

    @Then("^I see multiple trigger operator box between first Trigger and second trigger with operator OR selected on Trigger Group page$")
    public void i_see_multiple_trigger_operator_box_between_first_Trigger_and_second_trigger_with_operator_OR_selected_on_Trigger_Group_page() throws Throwable {
        Assert.assertTrue("Multiple Trigger Operator box is not visible", Elements.findElement( "trigger_groups.multiple_trigger_operator_label").isDisplayed());
        Assert.assertFalse("Multiple Trigger Operator Type And is not deselected by default", Elements.findElement( "trigger_groups.multiple_trigger_operator_And").isSelected());
        Assert.assertTrue("Multiple Trigger Operator Type Or is not selected by default", Elements.findElement( "trigger_groups.multiple_trigger_operator_Or").isSelected());
    }



}
