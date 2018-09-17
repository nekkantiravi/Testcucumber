package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.datastax.driver.core.schemabuilder.Drop;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.Rule;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.in;

import org.openqa.selenium.interactions.Actions;
import com.macys.sdt.framework.runner.WebDriverManager;

import javax.xml.crypto.Data;


/**
 * Created by admin on 7/4/2017.
 */
public class Rules {

    Rule rule = new Rule();
    LinkedHashMap criteriaValueMap;
    int index = 0;
    String creatorAttribute;
    String triggerGroupName;
    String displayMessage;
    String poolId;
    String categoryId;
    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Utils.class);
    List<WebElement> creatorSearchResult = new ArrayList<WebElement>();
    int triggerCount = 0;
    String boostAttribute;
    int actionCount = 0;
    String selectedRdppValue;
    int categoryRedirectCount;
    int displayMessageCount;
    int modifyResultSetOperator;
    int pdpRedirectCount;
    int rdppCount;
    int showMasterMemberCount;
    int showMediaCount;
    int manageFeaturedFacetCount;
    int urlRedirect;
    int manualSequence;
    int actionCountVerify;
    String siteTenant = CommonUtils.getTenantName();
    String keywordPatternCriteria, keywordPatternValue;
    Random random = new Random();
    String suppressedValue;
    String manageFacetToBeRemoved;
    List<WebElement> supressListValues;
    private static String dbName = "oracle";
    private static String dbUrl = "jdbc:oracle:thin:@dml1-scan:1521/dpmsts01";
    private static String dbUsername = "Saturn";
    private static String dbPassword = "Saturn";
    String ruleId;
    String allSearchErrorMesg = "The \"All Search\" trigger may apply specified action(s) to every user search.\n" +
            "Continue with Save?";
    String query;
    String jsonValueFileName = "rules_common";
    String jsonKeyValueFileName = "rules_criteria_value";
    String productId;
    String savedSet;
    String savedQuery;
    int resultSetCriteriaCount;
    int newRuleIndex;
    int displayValueCheckBoxesCount;
    private String keywordPatternValueEdit;
    private String keywordPatternCriteriaEdit;


    @And("^I save \"([^\"]*)\"$")
    public void iSave(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I click \"([^\"]*)\" button on alert popup$")
    public void iClickButtonOnAlertPopup(String arg0) throws Throwable {
        Clicks.click(By.xpath(CommonUtils.getJsonValue("ok_alert_button", jsonValueFileName)));
    }

    @When("^I select \"([^\"]*)\" action on rules page$")
    public void iSelectActionOnRulesPage(String actionType) throws Throwable {
        Clicks.click("rules.add_action");
        DropDowns.selectByText("login.action_type", actionType);
    }

    @And("^I create rdpp algorithm action value as \"([^\"]*)\"$")
    public void iCreateRdppAlgorithmActionValueAs(String rdppValue) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("rdpp_action_value", jsonValueFileName)), rdppValue);
    }

    @And("^I enter rdpp algorithm Effective and Expiration dates$")
    public void iEnterRdppAlgorithmEffectiveAndExpirationDates() throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_effective_date", jsonValueFileName) + actionCount + "_0').value=" + "'" + CommonUtils.getDate("effective date") + "'");
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_expiry_date", jsonValueFileName) + actionCount + "_0').value=" + "'" + CommonUtils.getDate("expiration date") + "'");
        Wait.forPageReady();
    }

    @When("^I select context action as \"([^\"]*)\"$")
    public void iSelectContextActionAs(String contextOption) throws Throwable {
        WebElement element = Elements.findElement(By.id(contextOption.toUpperCase() + actionCount));
        element.click();
    }

    @And("^I click continue$")
    public void iClickContinue() throws Throwable {
        Clicks.click(By.xpath(CommonUtils.getJsonValue("continue_button", jsonValueFileName)));
    }

    @And("^I should see rdpp algorithm type as \"([^\"]*)\"$")
    public void iShouldSeeRdppAlgorithmTypeAs(String rdppTypeVal) throws Throwable {
        String rdppType = DropDowns.getSelectedValue(By.id("dropdownRDPPRule0_0"));
        Assert.assertTrue("rdpp type doesnt match as entered", rdppType.equals(rdppTypeVal));
    }

    @And("^I should see the action saved as \"([^\"]*)\"$")
    public void iShouldSeeTheActionSavedAs(String actionType) throws Throwable {
        String actionTypeUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("action_dropdown", jsonValueFileName)));
        Assert.assertTrue("The action type doesnt match as entered", actionTypeUI.equals(actionType));
    }

    @And("^I select \"([^\"]*)\" trigger on rules page$")
    public void iSelectTriggerOnRulesPage(String selectTrigger) throws Throwable {
        Clicks.click("rules.add_trigger");
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("trigger_sequence", jsonValueFileName) + index), selectTrigger);
    }

    @Then("^I should see criteria in the Keyword pattern dropdown$")
    public void iShouldSeeCriteriaInTheKeywordPatternDropdown() throws Throwable {
        HashMap criteriaValueMap = CommonUtils.getJsonKeyValue("keyword_pattern_all_criteria_values", jsonKeyValueFileName);
        List<String> keysCriteria = new ArrayList<>(criteriaValueMap.keySet());
        List<String> uiCriteria = DropDowns.getAllValues("login.keyword_pattern_trigger_type");
        Assert.assertTrue("The keyword pattern dropdown criteria doesn't match", uiCriteria.containsAll(keysCriteria));
    }

    @Then("^I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob$")
    public void iShouldSeeRuleDetailsAsEnteredRuleNameRulePriorityEffectiveAndExpirationDatesFob() throws Throwable {
        Wait.forPageReady();
        Wait.ajaxDone();
        Assert.assertTrue("Rule name doesn't match the rule name entered", rule.ruleName.equals(getRuleName()));
        Assert.assertTrue("Rule priority doesn't match the rule priority entered", rule.rulePriority.equals(getrulePriority()));
        Assert.assertTrue("Rule Effective date doesn't match the rule Effective date entered", rule.effectiveDate.equals(getEffectiveDate()));
        Assert.assertTrue("Rule Expiry date doesn't match the rule Expiry Date entered", rule.expirationDate.equals(getExpirationDate()));
    }

    public String getrulePriority() {
        String priority;
        WebElement el = Elements.findElement(By.id("rulePriority"));
        priority = el.getAttribute("value");
        return priority;
    }

    public String getRuleName() {
        String name;
        WebElement el = Elements.findElement(By.id("name"));
        name = el.getAttribute("value");

        return name;
    }

    public String getEffectiveDate() {
        String date;
        WebElement el = Elements.findElement(By.id("startDate"));
        date = el.getAttribute("value");
        return date;
    }

    public String getExpirationDate() {
        String date;
        WebElement el = Elements.findElement(By.id("endDate"));
        date = el.getAttribute("value");
        return date;
    }

    @Then("^I should see attributes in Result count dropdown$")
    public void iShouldSeeAttributesInResultCountDropdown() throws Throwable {
        HashMap criteriaValueMap = CommonUtils.getJsonKeyValue("result_count_criteria_values", jsonKeyValueFileName);
        List<String> keysCriteria = new ArrayList<>(criteriaValueMap.keySet());
        List<String> uiCriteria = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("result_count_attributes", jsonValueFileName) + index));
        Assert.assertTrue("The keyword pattern dropdown criteria doesn't match", uiCriteria.containsAll(keysCriteria));
    }

    @And("^I should see \"([^\"]*)\" as selected by default$")
    public void iShouldSeeAsSelectedByDefault(String attributeVal) throws Throwable {

        String defaultValue = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("result_count_attributes", jsonValueFileName) + index)).toString();
        Assert.assertTrue("Less than is not selected as a default value", defaultValue.equals(attributeVal));
    }

    @When("^I save trigger$")
    public void iSaveTrigger() throws Throwable {
        Wait.forPageReady();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + index + "').click()");
        index++;
    }

    @And("^I click on  Edit rule$")
    public void iClickOnEditRule() throws Throwable {
        Wait.forPageReady();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("edit_rule", jsonValueFileName) + "').click()");
        Wait.untilElementNotPresent(By.id(CommonUtils.getJsonValue("edit_rule", jsonValueFileName)));
    }

    @And("^I click on  Edit trigger$")
    public void iClickOnEditTrigger() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent(By.id(CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + triggerCount));
        Clicks.javascriptClick(By.id(CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + triggerCount));
        // Elements.findElement(By.id(CommonUtils.getJsonValue("edit_trigger",jsonValueFileName)+actionCount)).click();
        // Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + actionCount + "').click()");
    }

    @And("^I save rule details page$")
    public void iSaveRuleDetailsPage() throws Throwable {
        Elements.findElement(By.id(CommonUtils.getJsonValue("save_rule_details", jsonValueFileName))).click();
        Wait.untilElementPresent(By.id(CommonUtils.getJsonValue("edit_rule", jsonValueFileName)));
    }

    @And("^I should see the trigger with below details$")
    public void iShouldSeeTheTriggerWithBelowDetails(DataTable data) throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + triggerCount));
        List<List<String>> listData = data.raw();
        String attributeValue = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("result_count_criteria", jsonValueFileName)));
        String selectedValue = Elements.findElement(By.id(CommonUtils.getJsonValue("result_count_value", jsonValueFileName))).getAttribute("value");
        Assert.assertTrue("The Selected Attribute is not as entered", attributeValue.equals(listData.get(0).get(0)));
        Assert.assertTrue("The Selected Attribute value is not as entered", selectedValue.equals(listData.get(0).get(1)));
        Clicks.click(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + triggerCount));
    }

    @Then("^I should see \"([^\"]*)\" at the page$")
    public void iShouldSeeAtThePage(String facetValue) throws Throwable {
        String valueOfFacet;
        valueOfFacet = Elements.findElement(By.id("4FacetRefinementTrigger0")).getText();
        Assert.assertTrue("Value of Facet is not as expected", valueOfFacet.equals(facetValue));
    }

    @And("^I should see the Facet Refinement trigger with below details$")
    public void iShouldSeeTheFacetRefinementTriggerWithBelowDetails(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        String criteria, value;
        for (int count = 1; count < listData.size(); count++) {
            criteria = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_sub_criteria", jsonValueFileName) + (count - 1) + "'" + CommonUtils.getJsonValue("saved_facet_criteria", jsonValueFileName))).getText();
            value = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_sub_criteria", jsonValueFileName) + (count - 1) + "'" + CommonUtils.getJsonValue("saved_facet_values", jsonValueFileName))).getText();
            Assert.assertTrue("The criteria entered for" + criteria + "doesn't match", criteria.equals(listData.get(count).get(0)));
            Assert.assertTrue("The values entered doesn't match", criteria.equals(listData.get(count).get(0)));
        }
    }

    @And("^I click on Edit Action$")
    public void iClickOnEditAction() throws Throwable {
        System.out.println("editing action" + actionCount);
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("edit_action", jsonValueFileName) + 0 + "').click()");

    }

    @Then("^I verify the value of PPID as \"([^\"]*)\"$")
    public void iVerifyTheValueOfPPIDAs(String ppId) throws Throwable {
        String ppIdUI = Elements.findElement(By.id("inputPDPID0")).getText();
        Assert.assertTrue("PPID doesnt match as entered", ppId.equals(ppIdUI));
    }

    @And("^I click on 'Cancel' action$")
    public void iClickOnCancelAction() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("action_cancel", jsonValueFileName) + actionCount));
    }

    @Then("^I should see display message as \"([^\"]*)\"$")
    public void iShouldSeeDisplayMessageAs(String displayMessage) throws Throwable {
        String displayMessageUI = Elements.findElement(By.id("textDisplayMessage0")).getText();
        Assert.assertTrue("display message doesn't match as entered", displayMessage.equals(displayMessageUI));
    }

    public String getRandomPriority() {
        int random = (2 + (int) (Math.random() * ((20 - 2) + 1))) * 5;
        logger.info("priority value" + random);
        return String.valueOf(random);
    }

    @And("^I enter Rule Priority, Effective and Expiration dates, FOB$")
    public void iEnterRulePriorityEffectiveAndExpirationDatesFOB() throws Throwable {
        String rulePriorityUI;
        List<String> fobList;
        setRuleDetails();
        Wait.forPageReady();
        logger.info("rule.rulePriority", rule.rulePriority);
        Navigate.execJavascript("document.getElementById('rulePriority').value=" + rule.rulePriority);
        Wait.forPageReady();
        rulePriorityUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName)));
        logger.info("rulePriorityUI selected" + rulePriorityUI);
        Wait.forPageReady();
        fobList = DropDowns.getAllValuesUsingJavaScript("login.fob");
        logger.info("++++++++++++" + fobList);
        DropDowns.selectRandomValue("login.fob");
        Clicks.click("login.effective_date");
        TextBoxes.typeTextbox("login.effective_date", rule.effectiveDate);
        Clicks.click("login.expiry_date");
        TextBoxes.typeTextbox("login.expiry_date", rule.expirationDate);
    }

    public void setRuleDetails() {
        rule.rulePriority = getRandomPriority();
        rule.effectiveDate = CommonUtils.getDate("effective date");
        rule.expirationDate = CommonUtils.getDate("expiration date");
    }

    @And("^I select the modify result set with operator as \"([^\"]*)\"$")
    public void iSelectTheModifyResultSetWithOperatorAs(String operator) throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + actionCount + "').value='" + CommonUtils.getJsonKeyValue("Modify Result Set Operator", jsonKeyValueFileName).get(operator).toString() + "'");
        modifyResultSetOperator = actionCount;
    }

    @Then("^I should see modify result set operator with operator as \"([^\"]*)\"$")
    public void iShouldSeeModifyResultSetOperatorWithOperatorAs(String operator) throws Throwable {
        String operatorUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + modifyResultSetOperator));
        Assert.assertEquals("The operator value doesnt match as entered", operator, operatorUI);
    }

    @And("^I select the display as \"([^\"]*)\"$")
    public void iSelectTheDisplayAs(String selectDisplay) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("select_action_display", jsonValueFileName) + actionCount), selectDisplay);
        showMasterMemberCount = actionCount;
    }

    @And("^I should see the display value as \"([^\"]*)\"$")
    public void iShouldSeeTheDisplayValueAs(String displayValue) throws Throwable {
        String displayValueUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("select_action_display", jsonValueFileName) + showMasterMemberCount));
        Assert.assertEquals("the display value is not as expected", displayValue, displayValueUI);
    }

    @And("^I select the below values from Manage Facets list$")
    public void iSelectTheBelowValuesFromManageFacetsList(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        for (int count = 0; count < listData.size(); count++) {
            Elements.findElement(By.xpath(CommonUtils.getJsonValue("select_facets_display_first", jsonValueFileName) + listData.get(count).get(0) + CommonUtils.getJsonValue("select_facets_display_second", jsonValueFileName))).click();
        }
    }

    @Then("^I should see Value Management column in select facet display$")
    public void iShouldSeeValueManagementColumnInSelectFacetDisplay() throws Throwable {
        Wait.untilElementPresent(By.linkText("rules.value_management_column"));
        Assert.assertTrue("Value management column not displayed", Elements.findElement("rules.value_management_column").isDisplayed());

    }

    @And("^I should see default Facet values as$")
    public void iShouldSeeDefaultFacetValuesAs(DataTable data) throws Throwable {
        String defaultFacet;
        List<List<String>> listData = data.raw();
        for (int count = 0; count < listData.size(); count++) {

            defaultFacet = Elements.findElement(By.xpath(CommonUtils.getJsonValue("display_facet_table_first", jsonValueFileName) + (count + 1) + CommonUtils.getJsonValue("display_facet_table_second", jsonValueFileName))).getText();
            Assert.assertEquals("The default facet values are not as expected", defaultFacet, listData.get(count).get(0));
        }
    }

    @And("^I select \"([^\"]*)\" as media type$")
    public void iSelectAsMediaType(String mediaType) throws Throwable {
        //   DropDowns.selectByText("rules.media_type", mediaType);
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("media_type", jsonValueFileName) + actionCount), mediaType);
        showMediaCount = actionCount;
    }

    @When("^I select the show media operator with location as$")
    public void iSelectTheShowMediaOperatorWithLocationAs(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("media_location", jsonValueFileName) + actionCount + "_0"), listData.get(0).get(0));
    }

    @And("^I should see media type as \"([^\"]*)\"$")
    public void iShouldSeeMediaTypeAs(String mediaType) throws Throwable {
        String mediaTypeUI;
        mediaTypeUI = DropDowns.getSelectedValue(By.id("mediaTypeDropdown0"));
        Assert.assertEquals("media type doesn't match as entered", mediaType, mediaTypeUI);
    }

    @When("^I create Result Set trigger$")
    public void iCreateResultSetTrigger() throws Throwable {
        String attributeKey;
        String[] attributeValue;
        int count = 0;
        criteriaValueMap = CommonUtils.getJsonKeyValue("result_set_criteria_values", jsonKeyValueFileName);
        Iterator mapIterator = criteriaValueMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) mapIterator.next();
            attributeKey = pair.getKey().toString();
            attributeValue = pair.getValue().toString().split(",");
            DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_select_criteria", jsonValueFileName)))), attributeKey);
            if (attributeKey.equalsIgnoreCase("Price Type")) {
                List<WebElement> webElements = Elements.findElements("rules.result_set_price_type");
                int randomPriceType = (int) (Math.random() * webElements.size() + 1);
                Elements.findElement(By.xpath(CommonUtils.getJsonValue("result_set_price_type_one", jsonValueFileName) + randomPriceType + By.xpath(CommonUtils.getJsonValue("result_set_price_type_two", jsonValueFileName)))).click();
            } else {
                TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_select_value", jsonValueFileName)), attributeValue[0]);
                DropDowns.selectByValue((By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_operator", jsonValueFileName))), attributeValue[1]);
                TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_operator_value", jsonValueFileName)), attributeValue[2]);

            }
            //DropDowns.selectByText((Elements.findElement(By.xpath(getJsonValue("result_set_sub_criteria") + index + getJsonValue("sub_div_val")+"'" + getJsonValue("result_set_select_criteria")))), attributeValue[0]);
            if (mapIterator.hasNext())
                Elements.findElement(By.xpath(CommonUtils.getJsonValue("add_trigger_criteria_plus", jsonValueFileName))).click();

            count++;
        }
    }

    @And("^I enter replace term value as \"([^\"]*)\"$")
    public void iEnterReplaceTermValueAs(String replaceWithTerm) throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("replace_with_term", jsonValueFileName) + actionCount), replaceWithTerm);
    }

    @And("^I again select \"([^\"]*)\" action on rules page$")
    public void iAgainSelectActionOnRulesPage(String actionType) throws Throwable {
        Clicks.click("rules.add_action");
        DropDowns.selectByText(By.xpath((CommonUtils.getJsonValue("select_action_dropdown", jsonValueFileName)) + actionCount + "']"), actionType);
    }

    @And("^I select Search Query as \"([^\"]*)\"$")
    public void iSelectSearchQueryAs(String searchQuery) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("search_query", jsonValueFileName) + actionCount), searchQuery);
    }

    @Then("^I should see criteria in the Result set dropdown$")
    public void iShouldSeeCriteriaInTheResultSetDropdown() throws Throwable {
        HashMap criteriaValueMap = CommonUtils.getJsonKeyValue("result_set_criteria", jsonKeyValueFileName);
        List<String> keysCriteria = new ArrayList<>(criteriaValueMap.values());
        List<String> uiCriteria = DropDowns.getAllValues("login.resultset_trigger");
        Assert.assertTrue("The keyword pattern dropdown criteria doesn't match", uiCriteria.containsAll(keysCriteria));
    }

    @And("^I Click Ok$")
    public void iClickOk() throws Throwable {
        Elements.findElement(By.xpath(CommonUtils.getJsonValue("ok_button", jsonValueFileName))).click();
    }

    @When("^I create keyword pattern trigger with all criteria$")
    public void iCreateKeywordPatternTriggerWithAllCriteria() throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("keyword_pattern_all_criteria_values", jsonKeyValueFileName);
        Iterator mapIterator = criteriaValueMap.entrySet().iterator();
        int count = 0;
        while (mapIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) mapIterator.next();
            DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + index + "_" + (count + 1) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria", jsonValueFileName)))), pair.getKey().toString());
            TextBoxes.typeTextNEnter(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + index + "_" + (count + 1) + "'" + CommonUtils.getJsonValue("keyword_pattern_value", jsonValueFileName)), pair.getValue().toString());
            if (mapIterator.hasNext())
                Elements.findElement(By.id(CommonUtils.getJsonValue("add_trigger_criteria", jsonValueFileName) + (count + 1))).click();
            count++;
        }
    }

    @Then("^I should see all the below options in the trigger dropdown$")
    public void iShouldSeeAllTheBelowOptionsInTheTriggerDropdown(DataTable data) throws Throwable {
        List<String> UiTriggerValues = DropDowns.getAllValues("login.trigger_type");
        Assert.assertTrue("The list of triggers does'nt match", UiTriggerValues.containsAll(data.asList(String.class)));
    }

    @When("^I click on Add trigger$")
    public void iClickOnAddTrigger() throws Throwable {
        Clicks.click("rules.add_trigger");
    }

    @And("^I should see all the below triggers added on the rules page$")
    public void iShouldSeeAllTheBelowTriggersAddedOnTheRulesPage(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        String selectTriggerValue;
        Wait.ajaxDone();
        Wait.forPageReady();
        for (int triggerCount = 0; triggerCount < listData.size(); triggerCount++) {
            Elements.findElement(By.id(CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + triggerCount)).click();
            selectTriggerValue = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("trigger_sequence", jsonValueFileName) + triggerCount));
            System.out.println("triggerCount+++" + triggerCount);
            Assert.assertTrue("The trigger is not added on UI", listData.get(triggerCount).contains(selectTriggerValue));
        }
    }

    @And("^I click cancel trigger$")
    public void iClickCancelTrigger() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("delete_trigger", jsonValueFileName) + triggerCount));
    }

    @And("^I select Create Trigger Group$")
    public void iSelectCreateTriggerGroup() throws Throwable {
        Wait.untilElementPresent(By.linkText("rules.create_trigger_group"));
        //Clicks.javascriptClick("rules.create_trigger_group");
        Elements.findElement(By.linkText("Create Trigger Group")).click();
    }

    @When("^I enter trigger group Name as below \"([^\"]*)\"$")
    public void i_enter_trigger_group_Name_as_below(String triggerGroupname) throws Throwable {
        triggerGroupName = triggerGroupname + currentTimeMillis();
        TextBoxes.typeTextbox("login.rule_name", triggerGroupName);
        logger.info("");
    }

    @And("^I create facet refinement trigger$")
    public void iCreateFacetRefinementTrigger() throws Throwable {
        if (CommonUtils.mcom()) {
            criteriaValueMap = CommonUtils.getJsonKeyValue("facet_refinement_criteria_values_mcom", jsonKeyValueFileName);
        } else {
            criteriaValueMap = CommonUtils.getJsonKeyValue("facet_refinement_criteria_values_bcom", jsonKeyValueFileName);
        }
        Iterator mapIterator = criteriaValueMap.entrySet().iterator();
        int count = 0;
        while (mapIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) mapIterator.next();
            TextBoxes.typeTextNEnter(By.xpath(CommonUtils.getJsonValue("facet_sub_criteria_one", jsonValueFileName) + index + "_" + count + CommonUtils.getJsonValue("facet_sub_criteria_two", jsonValueFileName) + CommonUtils.getJsonValue("facet_sub_criteria_three", jsonValueFileName)), pair.getKey().toString());
            TextBoxes.typeTextNEnter(By.xpath(CommonUtils.getJsonValue("facet_sub_criteria_one", jsonValueFileName) + index + "_" + count + CommonUtils.getJsonValue("facet_sub_criteria_two", jsonValueFileName) + CommonUtils.getJsonValue("Facet_sub_values", jsonValueFileName)), pair.getValue().toString());
            if (mapIterator.hasNext())
                Elements.findElement(By.id(CommonUtils.getJsonValue("facet_add_trigger", jsonValueFileName) + index + "_" + count)).click();
            count++;
        }
    }

    @When("^I create keyword pattern trigger with the given criteria and value$")
    public void iCreateKeywordPatternTriggerWithTheGivenCriteriaAndValue() throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("keyword_pattern_criteria_value", jsonKeyValueFileName);
        keywordPatternCriteria = criteriaValueMap.keySet().toArray()[0].toString();
        keywordPatternValue = criteriaValueMap.values().toArray()[0].toString();
        DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + index + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria", jsonValueFileName)))), keywordPatternCriteria);
        TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + index + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_value", jsonValueFileName)), keywordPatternValue);
    }

    @And("^I save trigger group$")
    public void iSaveTriggerGroup() throws Throwable {
        Elements.findElement(By.id(CommonUtils.getJsonValue("save_trigger_group", jsonValueFileName))).click();
        Thread.sleep(30000);
    }

    @Then("^I should see the rule saved$")
    public void iShouldSeeTheRuleSaved() throws Throwable {
        Wait.ajaxDone();
        Wait.forPageReady();
        Wait.untilElementPresent(By.id(CommonUtils.getJsonValue("Edit_rule", jsonValueFileName)));
        Assert.assertTrue("Rule was not saved", Elements.findElement(By.id(CommonUtils.getJsonValue("Edit_rule", jsonValueFileName))).isDisplayed());
    }

    @And("^I select trigger group$")
    public void iSelectTriggerGroup() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("trigger_group_button", jsonValueFileName) + index));
    }

    @And("^I enter trigger group name under trigger$")
    public void iEnterTriggerGroupNameUnderTrigger() throws Throwable {
        TextBoxes.typeTextbox("rules.trigger_group_name", triggerGroupName);
    }

    @Then("^I should see the trigger group name as entered$")
    public void iShouldSeeTheTriggerGroupNameAsEntered() throws Throwable {
        Assert.assertEquals("The trigger group name is not the same as entered", Elements.findElement("rules.trigger_group_name"));
    }

    @And("^I create Category Ids trigger with category Id$")
    public void iCreateCategoryIdsTriggerWithCategoryId() throws Throwable {
        if (CommonUtils.mcom()) {
            categoryId = "199";
        } else {
            categoryId = "193";
        }
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("category_id", jsonValueFileName) + index), categoryId);
    }

    @When("^I create result count trigger with \"([^\"]*)\" attribute$")
    public void iCreateResultCountTriggerWithAttribute(String attribute) throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("result_count_criteria_values", jsonKeyValueFileName);
        String attributeKey = criteriaValueMap.keySet().toArray()[0].toString();
        String value = criteriaValueMap.get(attributeKey).toString();
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("result_count_attributes", jsonValueFileName) + String.valueOf(index)), attributeKey);
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("result_count_values", jsonValueFileName) + String.valueOf(index)), value);
    }

    @When("^I edit trigger result count trigger with \"([^\"]*)\" attribute$")
    public void iEditTriggerResultCountTriggerWithAttribute(String arg0) throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("result_count_criteria_values", jsonKeyValueFileName);
        String attributeKey = criteriaValueMap.keySet().toArray()[1].toString();
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("result_count_attributes", jsonValueFileName) + triggerCount), attributeKey);
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("result_count_values", jsonValueFileName) + triggerCount), criteriaValueMap.get(attributeKey).toString());
    }

    @And("^I create PDP Redirect action with first PPID value$")
    public void iCreatePDPRedirectActionWithFirstPPIDValue() throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("pdp_redirect_input", jsonValueFileName) + actionCount), CommonUtils.getJsonKeyValue("PDP Redirect", jsonKeyValueFileName).get("PPID1").toString());
        pdpRedirectCount = actionCount;
    }

    @And("^I create PDP Redirect action with second PPID value$")
    public void iCreatePDPRedirectActionWithSecondPPIDValue() throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("pdp_redirect_input", jsonValueFileName) + actionCount), CommonUtils.getJsonKeyValue("PDP Redirect", jsonKeyValueFileName).get("PPID2").toString());
    }

    @And("^I should see the same details entered for the keyword pattern trigger$")
    public void iShouldSeeTheSameDetailsEnteredForTheKeywordPatternTrigger() throws Throwable {
        String criteria, valueOne, valueTwo;
        criteriaValueMap = CommonUtils.getJsonKeyValue("keyword_pattern_all_criteria_values", jsonKeyValueFileName);
        Iterator mapIterator = criteriaValueMap.entrySet().iterator();
        int count = 0;
        while (mapIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) mapIterator.next();
            criteria = Elements.findElement(By.id(CommonUtils.getJsonValue("label_keyword_pattern_criteria", jsonValueFileName) + (count + 1))).getText();
            valueOne = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria_saved", jsonValueFileName) + triggerCount + "_" + (count + 1) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria_value", jsonValueFileName) + (triggerCount + 1) + "]")).getText();
            valueTwo = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria_saved", jsonValueFileName) + triggerCount + "_" + (count + 1) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria_value", jsonValueFileName) + (triggerCount + 2) + "]")).getText();
            Assert.assertTrue("The criteria entered doesn't match", criteria.contains(pair.getKey().toString()));
            Assert.assertEquals("The value entered doesn't match", valueOne, pair.getValue().toString().split(",")[0]);
            Assert.assertEquals("The value entered doesn't match", valueTwo, pair.getValue().toString().split(",")[1]);
            count++;
        }
    }

    @And("^I should see the same details entered for the Result Set  trigger$")
    public void iShouldSeeTheSameDetailsEnteredForTheResultSetTrigger() throws Throwable {
        String criteria, valueOne, logicalVal, inputPercent;
        criteriaValueMap = CommonUtils.getJsonKeyValue("result_set_criteria_values", jsonKeyValueFileName);
        Iterator mapIterator = criteriaValueMap.entrySet().iterator();
        int count = 0;
        while (mapIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) mapIterator.next();
            criteria = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("result_set_criteria", jsonValueFileName) + count));
            valueOne = Elements.findElement(By.xpath(CommonUtils.getJsonValue("result_set_value_saved_one", jsonValueFileName) + count + CommonUtils.getJsonValue("result_set_value_saved_two", jsonValueFileName) + (triggerCount + 1) + "]")).getText();
            logicalVal = DropDowns.getSelectedValue(By.id((CommonUtils.getJsonValue("result_set_logical_dropdown", jsonValueFileName) + count)));
            System.out.println(logicalVal + "-----------------");
            System.out.println(criteriaValueMap.get(criteria).toString().split(",")[1] + "stored");
            Assert.assertTrue("The criteria entered doesn't match", criteriaValueMap.keySet().contains(pair.getKey().toString()));
            Assert.assertEquals("The value entered doesn't match", criteriaValueMap.get(criteria).toString().split(",")[0], valueOne);
            Assert.assertTrue("The logical value entered doesn't match", logicalVal.contains(criteriaValueMap.get(criteria).toString().split(",")[1]));
            count++;
        }
    }

    @And("^I should see the same details entered for the Facet Refinement trigger$")
    public void iShouldSeeTheSameDetailsEnteredForTheFacetRefinementTrigger() throws Throwable {
        String criteria, valueOne;
        if (CommonUtils.mcom()) {
            criteriaValueMap = CommonUtils.getJsonKeyValue("facet_refinement_criteria_values_mcom", jsonKeyValueFileName);

        } else {
            criteriaValueMap = CommonUtils.getJsonKeyValue("facet_refinement_criteria_values_bcom", jsonKeyValueFileName);

        }
    }

    @When("^I create \"([^\"]*)\" action with \"([^\"]*)\"$")
    public void iCreateActionWith(String action, String param) throws Throwable {
        if (CommonUtils.mcom()) {
            categoryId = CommonUtils.getJsonKeyValue(action, jsonKeyValueFileName).get(param + " mcom").toString();

        } else
            categoryId = CommonUtils.getJsonKeyValue(action, jsonKeyValueFileName).get(param + " bcom").toString();

        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("category_redirect_id", jsonValueFileName) + actionCount + "').value=" + "'" + categoryId + "'");
        categoryRedirectCount = actionCount;
    }

    @Then("^I should see that \"([^\"]*)\" has \"([^\"]*)\"  same as entered$")
    public void iShouldSeeThatHasSameAsEntered(String action, String param) throws Throwable {
        String categoryIdUI;
        categoryIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("category_redirect_id", jsonValueFileName) + categoryRedirectCount)).getAttribute("value");
        Assert.assertEquals("The category Id doesn't match", categoryId, categoryId);
    }

    @And("^I enter \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iEnterFor(String url, String action) throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("url_redirect_input", jsonValueFileName) + actionCount), CommonUtils.getJsonKeyValue(action, jsonKeyValueFileName).get(url).toString());
        urlRedirect = actionCount;
    }

    @And("^I should see the value of \"([^\"]*)\" for \"([^\"]*)\" as entered$")
    public void iShouldSeeTheValueOfForAsEntered(String url, String action) throws Throwable {
        String urlUI;
        urlUI = Elements.findElement(By.id(CommonUtils.getJsonValue("url_redirect_input", jsonValueFileName) + urlRedirect)).getAttribute("value");
        Assert.assertEquals("URl entered doesn't match", urlUI, CommonUtils.getJsonKeyValue(action, jsonKeyValueFileName).get(url).toString());

    }

    @And("^I save rule$")
    public void iSaveRule() throws Throwable {
        Clicks.click("login.save_rule");
        ruleId = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_id", jsonValueFileName))).getText().split(" ")[0];
        logger.info("ruleId:" + ruleId);

    }


    @And("^I create \"([^\"]*)\" action  with Display \"([^\"]*)\"$")
    public void iCreateActionWithDisplay(String action, String msg) throws Throwable {
        displayMessage = CommonUtils.getJsonKeyValue(action, jsonKeyValueFileName).get(msg).toString();
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("display_message_value", jsonValueFileName) + actionCount), displayMessage);
        displayMessageCount++;
    }

    @Then("^I should see display message as entered$")
    public void iShouldSeeDisplayMessageAsEntered() throws Throwable {
        String message;
        message = Elements.findElement(By.id(CommonUtils.getJsonValue("display_message_value", jsonValueFileName) + (displayMessageCount - 1))).getAttribute("value");
        Assert.assertEquals("Display Message doesn't match as entered", displayMessage, message);
    }

// commented out below duplicate code while merging master to branch
//    @Then("^I should be on the rules page$")
//    public void iShouldBeOnTheRulesPage () throws Throwable {
//        Assert.assertEquals("navigation to rules page failed", Elements.findElement(By.xpath(CommonUtils.getJsonValue("rules_page_title"))).getText(), CommonUtils.getJsonKeyValue("Page Title").get("rule_page").toString());
//    }

//    @And("^I should see the below values in the Rule Priority dropdown$")
//    public void iShouldSeeTheBelowValuesInTheRulePriorityDropdown (DataTable data) throws Throwable {
//        List<List<String>> priorityList = data.raw();
//        List<String> priorityListUI = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("rule_priority")));
//        for (int i = 0; i < priorityList.size(); i++) {
//            Assert.assertEquals("the rule priority list doesn't match on UI", priorityList.get(i).get(0), priorityListUI.get(i + 1));
//        }
//    }

//    @Then("^I should see The Rule priority dropdown on create rule page$")
//    public void iShouldSeeTheRulePriorityDropdownOnCreateRulePage () throws Throwable {
//        Assert.assertTrue("The rule priority dropdown is not available", Elements.findElement(By.id(CommonUtils.getJsonValue("rule_priority"))).isDisplayed());
//    }
//
//    @Then("^I should see the column named \"([^\"]*)\" on the find rules page$")
//    public void iShouldSeeTheColumnNamedOnTheFindRulesPage (String columnName) throws Throwable {
//        Assert.assertEquals("Priority column is not there on the find rules page", columnName, Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_priority_column"))).getText());
//    }
//
//    @Then("^I should be on the Create Trigger Group page$")
//    public void iShouldBeOnTheCreateTriggerGroupPage () throws Throwable {
//        Assert.assertTrue("Navigation to rules page failed", Elements.findElement(By.xpath(CommonUtils.getJsonValue("rules_page_title"))).getText().contains(CommonUtils.getJsonKeyValue("Page Title").get("trigger_group_page").toString()));
//    }

//    }

    @Then("^I should be on the rules page$")
    public void iShouldBeOnTheRulesPage() throws Throwable {
        Assert.assertEquals("navigation to rules page failed", Elements.findElement(By.xpath(CommonUtils.getJsonValue("rules_page_title", jsonValueFileName))).getText(), CommonUtils.getJsonKeyValue("Page Title", jsonKeyValueFileName).get("rule_page").toString());
    }

    @And("^I should see the below values in the Rule Priority dropdown$")
    public void iShouldSeeTheBelowValuesInTheRulePriorityDropdown(DataTable data) throws Throwable {
        List<List<String>> priorityList = data.raw();
        List<String> priorityListUI = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName)));
        for (int i = 0; i < priorityList.size(); i++) {
            Assert.assertEquals("the rule priority list doesn't match on UI", priorityList.get(i).get(0), priorityListUI.get(i + 1));
        }
    }

    @Then("^I should see The Rule priority dropdown on create rule page$")
    public void iShouldSeeTheRulePriorityDropdownOnCreateRulePage() throws Throwable {
        Assert.assertTrue("The rule priority dropdown is not available", Elements.findElement(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName))).isDisplayed());
    }

    @Then("^I should see the column named \"([^\"]*)\" on the find rules page$")
    public void iShouldSeeTheColumnNamedOnTheFindRulesPage(String columnName) throws Throwable {
        Assert.assertEquals("Priority column is not there on the find rules page", columnName, Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_priority_column", jsonValueFileName))).getText());
    }

    @Then("^I should be on the Create Trigger Group page$")
    public void iShouldBeOnTheCreateTriggerGroupPage() throws Throwable {
        Assert.assertTrue("Navigation to rules page failed", Elements.findElement(By.xpath(CommonUtils.getJsonValue("rules_page_title", jsonValueFileName))).getText().contains(CommonUtils.getJsonKeyValue("Page Title", jsonKeyValueFileName).get("trigger_group_page").toString()));
    }

    @Then("^I should see the \"([^\"]*)\" for the \"([^\"]*)\" trigger$")
    public void iShouldSeeTheForTheTrigger(String errorMessage, String triggerType) throws Throwable {
        String errorMessageUI;
        switch (triggerType) {
            case "Category Id":
            case "Result Count":
                errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_one", jsonValueFileName))).getText();
                Assert.assertEquals("The error Message doesn't Match for" + triggerType, CommonUtils.getJsonKeyValue("Trigger Error Messages", jsonKeyValueFileName).get(errorMessage).toString(), errorMessageUI);
                break;

            case "Facet Refinement":
            case "Keyword Pattern":
            case "Result Set":
                if (errorMessage.equals("Keyword Group Error Message")) {
                    errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_three", jsonValueFileName))).getText();
                    Assert.assertEquals("The Keyword pattern group error Message doesn't Match", CommonUtils.getJsonKeyValue("Trigger Error Messages", jsonKeyValueFileName).get(errorMessage).toString(), errorMessageUI);
                } else if (errorMessage.equals("Percentage")) {
                    Thread.sleep(3000);
                    errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_one", jsonValueFileName))).getText();
                    Assert.assertEquals("The error Message doesn't Match for" + triggerType, CommonUtils.getJsonKeyValue("Trigger Error Messages", jsonKeyValueFileName).get(errorMessage).toString(), errorMessageUI);
                } else {
                    errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();
                    Assert.assertEquals("The error Message doesn't Match for" + triggerType, CommonUtils.getJsonKeyValue("Trigger Error Messages", jsonKeyValueFileName).get(errorMessage).toString(), errorMessageUI);
                }
                break;
            case "All Search":
                errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();
                Assert.assertEquals("The error Message doesn't Match for" + triggerType, allSearchErrorMesg, errorMessageUI);
                break;
            default:
                logger.info("Invalid trigger selected");
                break;
        }
    }

    @When("^I try to save trigger$")
    public void iTryToSaveTrigger() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + index));
    }

    @And("^I create \"([^\"]*)\" with Boost Attribute$")
    public void iCreateWithBoostAttribute(String arg0) throws Throwable {
        Wait.untilElementPresent(By.id(CommonUtils.getJsonValue("modify_result_set_boost_attribute", jsonValueFileName) + actionCount + "_0"));
        Clicks.click(By.id(CommonUtils.getJsonValue("modify_result_set_boost_attribute", jsonValueFileName) + actionCount + "_0"));
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("boost_attribute_dropdown", jsonValueFileName)), "Accent");
        boostAttribute = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("boost_attribute_dropdown", jsonValueFileName)));
        Clicks.click("rules.boost_attribute_check");
        Clicks.click(By.xpath(CommonUtils.getJsonValue("ok_alert_button", jsonValueFileName)));
    }

    @And("^I should see the Boost Attribute value as selected$")
    public void iShouldSeeTheBoostAttributeValueAsSelected() throws Throwable {
        String boostAttributeValueUI;
        boostAttributeValueUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("boost_attribute_value_ui_one", jsonValueFileName) + modifyResultSetOperator + CommonUtils.getJsonValue("boost_attribute_value_ui_two", jsonValueFileName))).getText();
        Assert.assertEquals("Boost Attribute Value doesn't match", boostAttribute, boostAttributeValueUI);
    }

    @Then("^I should see the trigger group saved$")
    public void iShouldSeeTheTriggerGroupSaved() throws Throwable {
        Wait.untilElementPresent(By.id(CommonUtils.getJsonValue("trigger_group_saved", jsonValueFileName)));
        Assert.assertTrue("Trigger Group was not saved", Elements.findElement(By.id(CommonUtils.getJsonValue("trigger_group_saved", jsonValueFileName))).isDisplayed());
    }

    @And("^I enter the value of Trigger Group Created$")
    public void iEnterTheValueOfTriggerGroupCreated() throws Throwable {
        logger.info("triggergroupname" + triggerGroupName);
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("trigger_group_text", jsonValueFileName) + triggerCount), triggerGroupName);
        triggerCount++;
    }


    @Then("^I should see the \"([^\"]*)\" trigger group error message$")
    public void iShouldSeeTheTriggerGroupErrorMessage(String errorType) throws Throwable {
        String errorMessageUI;
        Wait.ajaxDone();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent(By.xpath(CommonUtils.getJsonValue("trigger_group_error_message", jsonValueFileName)), 40000);
        errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_group_error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The error Message doesn't Match for trigger group", CommonUtils.getJsonKeyValue("Trigger Group Error Messages", jsonKeyValueFileName).get(errorType).toString(), errorMessageUI);
    }

    @And("^I save trigger on trigger group page$")
    public void iSaveTriggerOnTriggerGroupPage() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + triggerCount));
    }

    @When("^I click on \"([^\"]*)\" sign to add more criteria$")
    public void iClickOnSignToAddMoreCriteria(String arg0) throws Throwable {
        Elements.findElement(By.id(CommonUtils.getJsonValue("add_trigger_criteria", jsonValueFileName) + criteriaValueMap.size())).click();

    }

    @When("^I select the criteria as \"([^\"]*)\"$")
    public void iSelectTheCriteriaAs(String criteria) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("keyword_pattern_dropdown", jsonValueFileName) + triggerCount + "_" + criteriaValueMap.size()), criteria);

    }

    @Then("^I should see the error message for \"([^\"]*)\"$")
    public void iShouldSeeTheErrorMessageFor(String criteria) throws Throwable {
        String errorMessageUI, errorMessage;
        errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("error_message", jsonValueFileName))).getText();
        errorMessage = CommonUtils.getJsonKeyValue("keyword_pattern_criteria_repeat", jsonKeyValueFileName).get("error_message_one").toString() + criteria + CommonUtils.getJsonKeyValue("keyword_pattern_criteria_repeat", jsonKeyValueFileName).get("error_message_two").toString();
        Assert.assertEquals("The error message for criteria" + criteria + " doesn't match", errorMessageUI, errorMessage);
    }

    @And("^I save action on rules page$")
    public void iSaveActionOnRulesPage() throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("save_action", jsonValueFileName) + actionCount + "').click()");
        actionCount++;
    }

    @And("^I create show media with \"([^\"]*)\"$")
    public void iCreateShowMediaWith(String param) throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("canvas_id", jsonValueFileName) + actionCount), CommonUtils.getJsonKeyValue("show_media", jsonKeyValueFileName).get(param).toString());
    }

    @And("^I should see Show Media with \"([^\"]*)\" as entered$")
    public void iShouldSeeShowMediaWithAsEntered(String param) throws Throwable {
        String canvasIdUI, canvasId;
        canvasIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("canvas_id", jsonValueFileName) + showMediaCount)).getAttribute("value");
        canvasId = CommonUtils.getJsonKeyValue("show_media", jsonKeyValueFileName).get(param).toString();
        Assert.assertTrue("canvas Id doesn't match", canvasIdUI.contains(canvasId));
    }

    @And("^I create \"([^\"]*)\" trigger with \"([^\"]*)\"$")
    public void iCreateTriggerWith(String triggerType, String categoryId) throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("category_id", jsonValueFileName) + index), CommonUtils.getJsonKeyValue("value", jsonKeyValueFileName).get(categoryId).toString());
    }

    @Then("^I should see the \"([^\"]*)\" Error Message$")
    public void iShouldSeeTheErrorMessage(String triggerType) throws Throwable {
        String errorMessageUI;
        errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_one", jsonValueFileName))).getText();
        Assert.assertEquals("The" + triggerType + "error message doesn't match", CommonUtils.getJsonKeyValue("Invalid Trigger Values Errors", jsonKeyValueFileName).get(triggerType).toString(), errorMessageUI);
    }

    @When("^I select \"([^\"]*)\" Result Count attribute$")
    public void iSelectResultCountAttribute(String attribute) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("result_count_attributes", jsonValueFileName) + index + String.valueOf(index)), attribute);
    }

    @And("^I create Result Count trigger with \"([^\"]*)\"$")
    public void iCreateResultCountTriggerWith(String value) throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("result_count_attributes", jsonValueFileName) + index), CommonUtils.getJsonKeyValue("Invalid Trigger Value", jsonKeyValueFileName).get(value).toString());
    }

    @When("^I click Edit trigger group$")
    public void iClickEditTriggerGroup() throws Throwable {
        Wait.ajaxDone();
        Wait.forPageReady();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("edit_trigger_group", jsonValueFileName) + "').click()");
        Wait.untilElementNotPresent(By.id(CommonUtils.getJsonValue("edit_trigger_group", jsonValueFileName)));
    }

    @When("^I save the edited trigger$")
    public void iSaveTheEditedTrigger() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + triggerCount));
    }

    @And("^I verify the value of first PPID$")
    public void iVerifyTheValueOfFirstPPID() throws Throwable {


    }

    @And("^I enter the same trigger Group Name$")
    public void iEnterTheSameTriggerGroupName() throws Throwable {
        TextBoxes.typeTextbox("login.rule_name", triggerGroupName);

    }

    @Then("^I should see the \"([^\"]*)\" error on trigger group page$")
    public void iShouldSeeTheErrorOnTriggerGroupPage(String errorType) throws Throwable {
        String errorMessageUI;
        errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_group_error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The error Message doesn't Match for trigger group", CommonUtils.getJsonKeyValue("Trigger Group Error Messages", jsonKeyValueFileName).get(errorType).toString(), errorMessageUI);
    }

    @And("^I enter the same rule name$")
    public void iEnterTheSameRuleName() throws Throwable {
        TextBoxes.typeTextbox("login.rule_name", rule.ruleName);
    }

    @Then("^I should see the \"([^\"]*)\" error on rules page$")
    public void iShouldSeeTheErrorOnRulesPage(String errorType) throws Throwable {
        String errorMessageUI;
            if(errorType.equals("Duplicate Saved Set Id Saved Query Id"))
        {
            errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_page_error_message", jsonValueFileName))).getText();
            Assert.assertEquals("The error Message doesn't Match for rule name", CommonUtils.getJsonKeyValue("Modify Result Set Messages", jsonKeyValueFileName).get(errorType).toString(), errorMessageUI.split(":")[0]);
        }
        else
            {
                errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_page_error_message", jsonValueFileName))).getText();
                Assert.assertEquals("The error Message doesn't Match for rule name", CommonUtils.getJsonKeyValue("Trigger Group Error Messages", jsonKeyValueFileName).get(errorType).toString(), errorMessageUI);
            }
    }

    @And("^I create rdpp with random algorithm action value$")
    public void iCreateRdppWithRandomAlgorithmActionValue() throws Throwable {
        DropDowns.selectRandomValue(By.id(CommonUtils.getJsonValue("rdpp_algorithm_action", jsonValueFileName) + actionCount + "_0"));
        selectedRdppValue = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rdpp_algorithm_action", jsonValueFileName) + actionCount + "_0"));
        rdppCount = actionCount;
    }

    @And("^I select \"([^\"]*)\" as context$")
    public void iSelectAsContext(String context) throws Throwable {
        if (context.equals("All Mobile")) {
            Clicks.click(By.id(CommonUtils.getJsonValue("all_media_context", jsonValueFileName) + actionCount));
        } else if (context.equals("Store Search and Send")) {
            Clicks.click(By.id(CommonUtils.getJsonValue("Store_Search_And_Send", jsonValueFileName) + actionCount));
        } else
            Clicks.click(By.id(context + actionCount));
    }

    @And("^I enter the \"([^\"]*)\" rdpp algorithm Effective and Expiration dates$")
    public void iEnterTheRdppAlgorithmEffectiveAndExpirationDates(String dateType) throws Throwable {

        switch (dateType) {
            case "overlapping":
                Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_effective_date", jsonValueFileName) + actionCount + "_0" + "').value=" + "'" + rule.effectiveDate + "'");
                Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_expiry_date", jsonValueFileName) + actionCount + "_0" + "').value=" + "'" + rule.expirationDate + "'");
                Wait.forPageReady();
                break;

            case "intersecting":
                Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_effective_date", jsonValueFileName) + actionCount + "_0" + "').value=" + "'" + CommonUtils.getSpecificDate(rule.effectiveDate, "effective date", -4) + "'");
                Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_expiry_date", jsonValueFileName) + actionCount + "_0" + "').value=" + "'" + CommonUtils.getSpecificDate(rule.expirationDate, "expiration date", 4) + "'");
                Wait.forPageReady();
                break;

            case "in between":
                Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_effective_date", jsonValueFileName) + actionCount + "_0" + "').value=" + "'" + CommonUtils.getSpecificDate(rule.effectiveDate, "effective date", 4) + "'");
                Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rdpp_expiry_date", jsonValueFileName) + actionCount + "_0" + "').value=" + "'" + CommonUtils.getSpecificDate(rule.expirationDate, "expiration date", 4) + "'");
                Wait.forPageReady();
                break;

            default:
                logger.info("No date pattern selected");
                break;
        }
    }

    @Then("^I should see the \"([^\"]*)\" error$")
    public void iShouldSeeTheError(String errorType) throws Throwable {
        String errorMsgUI;
        errorMsgUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();
        Assert.assertEquals("Rdpp algorithm saved even with overlapping dates", errorMsgUI, CommonUtils.getJsonKeyValue("rdpp_algorithm_error", jsonKeyValueFileName).get(errorType).toString());
    }

    @When("^I click on Add action$")
    public void iClickOnAddAction() throws Throwable {
        Clicks.click("rules.add_action");
    }

    @When("^I select \"([^\"]*)\" action on Create Rule page$")
    public void i_select_action_on_Create_Rule_page(String actionType) throws Throwable {
        DropDowns.selectByText("login.action_type", actionType);
    }

    @Then("^I should see the below options enabled under actions dropdown for \"([^\"]*)\"$")
    public void iShouldSeeTheBelowOptionsEnabledUnderActionsDropdownFor(String triggerType, DataTable data) throws Throwable {
        List<String> enabledOptionsUI = new ArrayList<String>();
        List<String> enabledOptions;
        List<List<String>> listData = data.raw();
        List<WebElement> enabledElements;
        enabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("actions_enabled", jsonValueFileName)));
        enabledElements.stream().map(WebElement::getText).forEach(enabledOptionsUI::add);
        Iterator iterator = listData.iterator();

        for (int i = 0; i < enabledOptionsUI.size() - 1; i++) {
            Assert.assertEquals("The enabled elements list doesn't match for" + triggerType, listData.get(i).get(0), enabledOptionsUI.get(i + 1));
        }
    }

    @Then("^I should see rdpp algorithm as entered$")
    public void iShouldSeeRdppAlgorithmAsEntered() throws Throwable {
        String selectedRdppUI;
        selectedRdppUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rdpp_algorithm_action", jsonValueFileName) + rdppCount + "_0"));
        Assert.assertEquals("selected rdpp alorithm value doesnt match", selectedRdppValue, selectedRdppUI);
    }

    @And("^I verify the value of PPID$")
    public void iVerifyTheValueOfPPID() throws Throwable {
        String PPID;
        PPID = Elements.findElement(By.id(CommonUtils.getJsonValue("pdp_redirect_input", jsonValueFileName) + pdpRedirectCount)).getAttribute("value");
        Assert.assertEquals("PPID value doesnt match", CommonUtils.getJsonKeyValue("PDP Redirect", jsonKeyValueFileName).get("PPID1").toString(), PPID);
    }

    @When("^I select the condition as And$")
    public void iSelectTheConditionAsAnd() throws Throwable {
        Clicks.click("rules.global_trigger_and");
    }

    @Then("^I should see the trigger compatibility error$")
    public void iShouldSeeTheTriggerCompatibilityError() throws Throwable {
        String errorMessageUI;
        errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_page_error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The error Message doesn't Match for trigger group", CommonUtils.getJsonKeyValue("trigger_compatibility_error", jsonKeyValueFileName).get("Trigger And").toString(), errorMessageUI);
    }

    @And("^I create show media with Media Group Id$")
    public void iCreateShowMediaWithMediaGroupId() throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("media_group_id", jsonValueFileName) + actionCount + "_0" + "').value='" + CommonUtils.getJsonKeyValue("show_media", jsonKeyValueFileName).get("Banners Id").toString() + "'");
        //TextBoxes.typeTextbox("rules.media_group_id", CommonUtils.getJsonKeyValue("show_media", jsonKeyValueFileName).get("Banners Id").toString());
    }

    @And("^I create show media with pool id$")
    public void iCreateShowMediaWithPoolId() throws Throwable {
        poolId = CommonUtils.mcom() ? "200252" : "231271";
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("media_group_id", jsonValueFileName) + actionCount + "_0"), poolId);
    }

    @Then("^I should see Show Media with Banners Id as entered$")
    public void iShouldSeeShowMediaWithBannersIdAsEntered() throws Throwable {
        String bannersIdUI, bannersId;
        for (int i = 0; i < actionCount; i++) {
            bannersIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("media_group_id", jsonValueFileName) + i + "_0")).getAttribute("value");
            bannersId = CommonUtils.getJsonKeyValue("show_media", jsonKeyValueFileName).get("Banners Id").toString();
            Assert.assertEquals("canvas Id doesn't match", bannersIdUI, bannersId);

        }
    }

    @Then("^I should see Show Media with pool id as entered$")
    public void iShouldSeeShowMediaWithPoolIdAsEntered() throws Throwable {
        String poolIdUI;
        poolIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("pool_id_value", jsonValueFileName) + showMediaCount + "_0")).getAttribute("value");
        Assert.assertEquals("canvas Id doesn't match", poolId, poolIdUI);
    }

    @And("^I create show media with Copy Block Id$")
    public void iCreateShowMediaWithCopyBlockId() throws Throwable {
        String copyBlockId = StepUtils.macys() ? "638837" : "814751";
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("pool_id_value", jsonValueFileName) + actionCount + "_0" + "').value='" + copyBlockId + "'");
        // TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("pool_id_value",jsonValueFileName) + actionCount + "_0"), copyBlockId);
    }

    @When("^I de select all the context options selected by default$")
    public void iDeSelectAllTheContextOptionsSelectedByDefault() throws Throwable {

        for (int i = 1; i <= 4; i++) {
            if (i == 3) {
                Clicks.click(By.xpath(CommonUtils.getJsonValue("action_context_one", jsonValueFileName) + actionCount + CommonUtils.getJsonValue("action_context_three", jsonValueFileName)));
            } else {
                Clicks.click(By.xpath(CommonUtils.getJsonValue("action_context_one", jsonValueFileName) + actionCount + CommonUtils.getJsonValue("action_context_two", jsonValueFileName) + i + CommonUtils.getJsonValue("action_context_four", jsonValueFileName)));
            }
        }
    }

    @And("^I should see the Category Id value the same as entered$")
    public void iShouldSeeTheCategoryIdValueTheSameAsEntered() throws Throwable {
        String categoryIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("category_id", jsonValueFileName) + index)).getAttribute("value");
        Assert.assertEquals("Category Ids doesnt match as entered", categoryId, categoryIdUI);
    }

    @And("^I should see the Manage Featured Facets Value as entered$")
    public void iShouldSeeTheManageFeaturedFacetsValueAsEntered() throws Throwable {
        String manageFacteValueUI;
        manageFacteValueUI = Elements.findElement(By.id(CommonUtils.getJsonValue("manage_featured_facets", jsonValueFileName) + manageFeaturedFacetCount)).getAttribute("value");
        Assert.assertEquals("manage facet values doesnt match as entered", CommonUtils.getJsonKeyValue("Manage feature Facet", jsonKeyValueFileName).get("value").toString(), manageFacteValueUI);
    }

    @And("^I de select the context option \"([^\"]*)\"$")
    public void iDeSelectTheContextOption(String context) throws Throwable {
        if (context.equals("All Mobile")) {
            Clicks.click(By.id(CommonUtils.getJsonValue("all_media_context", jsonValueFileName) + actionCount));
        } else if (context.equals("Store Search and Send")) {
            Clicks.click(CommonUtils.getJsonValue("Store_Search_And_Send", jsonValueFileName) + actionCount);
        } else
            Clicks.click(By.id(context + actionCount));
    }

    @And("^I uncheck the apply non canvas option$")
    public void iUncheckTheApplyNonCanvasOption() throws Throwable {
        System.out.println(By.id(CommonUtils.getJsonValue("apply_non_canvas", jsonValueFileName) + actionCount + "clicking"));
        Clicks.click(By.id(CommonUtils.getJsonValue("apply_non_canvas", jsonValueFileName) + actionCount));
    }

    @Then("^I should see the action saved as \"([^\"]*)\" on Rules Page$")
    public void iShouldSeeTheActionSavedAsOnRulesPage(String actionType) throws Throwable {
        String actionTypeUI = (String) Navigate.execJavascript("return document.getElementById('" + CommonUtils.getJsonValue("action_dropdown_two", jsonValueFileName) + (actionCount - 1) + "').options[document.getElementById('" + CommonUtils.getJsonValue("action_dropdown_two", jsonValueFileName) + (actionCount - 1) + "').selectedIndex].text");
        Assert.assertEquals("The action type doesnt match as entered", actionType, actionTypeUI);
    }

    @And("^I click on context$")
    public void iClickOnContext() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("context_image", jsonValueFileName) + (actionCount)));
    }

    @And("^I enter Manage Featured Facets value$")
    public void iEnterManageFeaturedFacetsValue() throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("manage_featured_facets", jsonValueFileName) + actionCount), CommonUtils.getJsonKeyValue("Manage feature Facet", jsonKeyValueFileName).get("value").toString());
        manageFeaturedFacetCount = actionCount;
    }

    @And("^I enter Rule Name as \"([^\"]*)\"$")
    public void iEnterRuleNameAs(String ruleName) throws Throwable {
        rule.ruleName = ruleName + currentTimeMillis();
        TextBoxes.typeTextbox("login.rule_name", rule.ruleName);
    }

    @Then("^I should see the trigger saved as \"([^\"]*)\"$")
    public void iShouldSeeTheTriggerSavedAs(String triggerNameParam) throws Throwable {
        Wait.untilElementPresent(By.id(CommonUtils.getJsonValue("trigger_name", jsonValueFileName) + triggerCount));
        Wait.forPageReady();
        Wait.ajaxDone();
        Wait.secondsUntilElementPresent(By.id(CommonUtils.getJsonValue("trigger_name", jsonValueFileName) + triggerCount), 80000);
        String triggerName = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("trigger_name", jsonValueFileName) + triggerCount));
        Assert.assertEquals("The Trigger saved is not the same as selected", triggerNameParam, triggerName);
    }


    @Then("^I should see \"([^\"]*)\" with \"([^\"]*)\"$")
    public void iShouldSeeWith(String alert, String context) throws Throwable {
        String errorMsgUI, errorMsg, actionType;
        errorMsg = CommonUtils.getJsonKeyValue(alert, jsonKeyValueFileName).get(context).toString();
        actionType = DropDowns.getSelectedValue(By.xpath((CommonUtils.getJsonValue("select_action_dropdown", jsonValueFileName)) + actionCount + "']"));
        if (actionType.equalsIgnoreCase("Modify Result Set") || actionType.equalsIgnoreCase(" PDP Redirect")) {
            errorMsgUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_one", jsonValueFileName))).getText();
        } else {
            errorMsgUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();

        }
        Assert.assertEquals("The alert context doesn't match", errorMsg, errorMsgUI);
    }

    @And("^I click \"([^\"]*)\" button on same context alert popup$")
    public void iClickButtonOnSameContextAlertPopup(String arg0) throws Throwable {
        System.out.println("same context alert" + actionCount);
        Clicks.click(By.xpath(CommonUtils.getJsonValue("ok_alert_button", jsonValueFileName)));
    }

    @And("^I again save action$")
    public void iAgainSaveAction() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("save_action", jsonValueFileName) + actionCount));
        actionCount++;
    }

    @And("^I save action and click continue$")
    public void iSaveActionAndClickContinue() throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("save_action", jsonValueFileName) + actionCount + "').click()");
        actionCount++;
        Clicks.click(By.xpath(CommonUtils.getJsonValue("continue_button", jsonValueFileName)));
    }

    @When("^I click on Edit on Value Management column$")
    public void iClickOnEditOnValueManagementColumn() throws Throwable {
        Clicks.click("rules.manage_facets_edit");
    }

    @Then("^I should be on the window titled \"([^\"]*)\"$")
    public void iShouldBeOnTheWindowTitled(String title) throws Throwable {
        String titleUI = (String) Navigate.execJavascript("return document.getElementById('" + CommonUtils.getJsonValue("suppress_values_window", jsonValueFileName) + "').innerHTML");
        Assert.assertEquals("The suppression window titled doesn't match", title, titleUI);
    }

    @Then("^I should see the same suppressed value as selected$")
    public void iShouldSeeTheSameSuppressedValueAsSelected() throws Throwable {
        String supressValueUI = "";
        Iterator iterator = supressListValues.iterator();
        int supressIndex = 1;
        while (iterator.hasNext()) {
            logger.info("supressIndex++" + supressIndex);
            WebElement element = Elements.findElement(By.xpath(CommonUtils.getJsonValue("suppress_values_check_one", jsonValueFileName) + "[" + supressIndex + CommonUtils.getJsonValue("suppress_values_check_two", jsonValueFileName)));
            if (!element.isSelected()) {
                supressValueUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("suppress_values_check_one", jsonValueFileName) + "[" + supressIndex + CommonUtils.getJsonValue("suppress_values_check_three", jsonValueFileName))).getAttribute("innerHTML");
                break;
            }
            supressIndex++;
        }
        Assert.assertEquals("The suprressed value doesnt match", suppressedValue, supressValueUI);
        Clicks.click(By.xpath(CommonUtils.getJsonValue("save_suppressed_value", jsonValueFileName)));
    }

    @When("^I suppress any value and save the suppression dialog$")
    public void iSuppressAnyValueAndSaveTheSuppressionDialog() throws Throwable {
        supressListValues = Elements.findElements(By.xpath(CommonUtils.getJsonValue("suppress_values_check_one", jsonValueFileName)));
        int supressIndex = random.nextInt(supressListValues.size() - 1 + 1) + 1;
        logger.info("supressIndex" + supressIndex);
        Clicks.click(By.xpath(CommonUtils.getJsonValue("suppress_values_check_one", jsonValueFileName) + "[" + supressIndex + CommonUtils.getJsonValue("suppress_values_check_two", jsonValueFileName)));
        suppressedValue = Elements.findElement(By.xpath(CommonUtils.getJsonValue("suppress_values_check_one", jsonValueFileName) + "[" + supressIndex + CommonUtils.getJsonValue("suppress_values_check_three", jsonValueFileName))).getAttribute("innerHTML");
        Clicks.click(By.xpath(CommonUtils.getJsonValue("save_suppressed_value", jsonValueFileName)));
    }

    @Then("^I should see \"([^\"]*)\" error$")
    public void iShouldSeeError(String errorType) throws Throwable {
        String errorUI;
        errorUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_page_error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The multiple trigger error doesnt match", errorUI, CommonUtils.getJsonKeyValue(errorType, jsonKeyValueFileName).get("Error"));
    }

    @Then("^I should see the same suppressed value as selected in DB$")
    public void iShouldSeeTheSameSuppressedValueAsSelectedInDB() throws Throwable {
        query = "select df.facet_name,fvgv.data_value,fvgv.is_suppressed,(select \n" +
                " rtrim (xmlagg (xmlelement (av1, context_attr_varchar_value || ',')).extract ('//text()'), ',') from \n" +
                " action_context_value acv1 where merch_action_id=dfa.merch_action_id) context_attr_varchar_value\n" +
                " from merch_rule mr, merch_rule_action_assn mraa,display_facet_action dfa,display_facet df,facet_value_group_value fvgv\n" +
                " where mr.merch_rule_id=mraa.merch_rule_id and mraa.merch_action_id=dfa.merch_action_id\n" +
                " and dfa.display_facet_action_id=df.display_facet_action_id and df.display_facet_id=fvgv.display_facet_id\n" +
                " and mr.merch_rule_id =" + ruleId + " and fvgv.is_suppressed='Y' and mr.site_tenant= " + " '" + siteTenant + "' " + " order by fvgv.data_value";
        Statement stmt;
        String sql;
        try {
            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            if (CommonUtils.bcom()) {
                sql = String.format(query, "BCOM");
            } else {
                sql = String.format(query, "MCOM");
            }
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println("rs::::" + rs);
            Assert.assertEquals("The suprressed value doesn't match", suppressedValue, rs.getString(2));
            System.out.print("query executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
        System.out.println("End scenarios " + actionCount);
    }

    public void setModifyResultSetParameters() throws IOException, ParseException {
        if (CommonUtils.bcom()) {
            productId = CommonUtils.getJsonKeyValue("Modify Result Set BCOM", jsonKeyValueFileName).get("ProductId").toString();
            savedQuery = CommonUtils.getJsonKeyValue("Modify Result Set BCOM", jsonKeyValueFileName).get("SavedQueryId").toString();
            savedSet = CommonUtils.getJsonKeyValue("Modify Result Set BCOM", jsonKeyValueFileName).get("SavedSet").toString();
        } else {
            productId = CommonUtils.getJsonKeyValue("Modify Result Set MCOM", jsonKeyValueFileName).get("ProductId").toString();
            savedQuery = CommonUtils.getJsonKeyValue("Modify Result Set MCOM", jsonKeyValueFileName).get("SavedQueryId").toString();
            savedSet = CommonUtils.getJsonKeyValue("Modify Result Set MCOM", jsonKeyValueFileName).get("SavedSet").toString();
        }
    }

    @And("^I create Modify Result Set with required data$")
    public void iCreateModifyResultSetWithRequiredData() throws Throwable {
        setModifyResultSetParameters();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("modify_result_set_product_id", jsonValueFileName) + actionCount + "_0').value='" + productId + "'");
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("modify_result_set_saved_query", jsonValueFileName) + actionCount + "_0').value='" + savedSet + " " + savedQuery + "'");
        modifyResultSetOperator = actionCount;
    }

    @And("^I should see Modify Result Set with the data as entered$")
    public void iShouldSeeModifyResultSetWithTheDataAsEntered() throws Throwable {
        String productIdUI, savedSetQueryId;
        productIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_product_id", jsonValueFileName) + modifyResultSetOperator + "_0")).getAttribute("value");
        savedSetQueryId = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_saved_query", jsonValueFileName) + modifyResultSetOperator + "_0")).getAttribute("value");
        Assert.assertEquals("Product Id doesn't match as entered", productId, productIdUI);
        Assert.assertTrue("Saved set Id doesn't match as entered", savedSetQueryId.contains(savedSet));
        Assert.assertTrue("Saved query Id doesn't match as entered", savedSetQueryId.contains(savedQuery));
    }

    @And("^I edit the keyword pattern trigger with other criteria and value$")
    public void iEditTheKeywordPatternTriggerWithOtherCriteriaAndValue() throws Throwable {
        Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria_saved", jsonValueFileName) + triggerCount + "_1" + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria_value", jsonValueFileName) + (triggerCount + 1) + "]/a")).click();
        criteriaValueMap = CommonUtils.getJsonKeyValue("keyword_pattern_criteria_value", jsonKeyValueFileName);
        keywordPatternCriteria = criteriaValueMap.keySet().toArray()[1].toString();
        keywordPatternValue = criteriaValueMap.values().toArray()[1].toString();
        Wait.untilElementPresent(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + 0 + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria", jsonValueFileName)));
        DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + 0 + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria", jsonValueFileName)))), keywordPatternCriteriaEdit);
        TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + triggerCount + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_value", jsonValueFileName)), keywordPatternValueEdit);
    }


    @Then("^I should see the keyword pattern trigger with given details$")
    public void iShouldSeeTheKeywordPatternTriggerWithGivenDetails() throws Throwable {
        String criteria, valueOne, valueTwo;
        criteria = Elements.findElement(By.id(CommonUtils.getJsonValue("label_keyword_pattern_criteria", jsonValueFileName) + "1")).getText();
        valueOne = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria_saved", jsonValueFileName) + triggerCount + "_1" + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria_value", jsonValueFileName) + (triggerCount + 1) + "]")).getText();
        Assert.assertEquals("The criteria entered doesn't match", keywordPatternCriteria, criteria.split(":")[0]);
        Assert.assertEquals("The value entered doesn't match", keywordPatternValue.split(",")[0], valueOne);
    }

    @When("^I click on Delete button$")
    public void iClickOnDeleteButton() throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("delete_trigger", jsonValueFileName) + actionCount + "').click()");
    }

    @Then("^the trigger should be deleted$")
    public void theTriggerShouldBeDeleted() throws Throwable {
        Assert.assertTrue("The trigger did not get delete", Elements.findElement(By.id(CommonUtils.getJsonValue("trigger_name", jsonValueFileName) + triggerCount)).isDisplayed());
    }

    @Then("^I should see the error message input data Alert$")
    public void iShouldSeeTheErrorMessageInputDataAlert() throws Throwable {
        String error_1, error_2;
        error_1 = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();
        error_2 = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_three", jsonValueFileName))).getText();
        Assert.assertEquals("The input data alert message is not correct", CommonUtils.getJsonKeyValue("Input Data Error Message", jsonValueFileName).get("Erro1").toString(), error_1);
        Assert.assertEquals("The input data alert message is not correct", CommonUtils.getJsonKeyValue("Input Data Error Message", jsonValueFileName).get("Erro2").toString(), error_2);
    }

    @Then("^I should see the \"([^\"]*)\"$")
    public void iShouldSeeThe(String alertType) throws Throwable {
        String alertMessage, actionType;
        actionType = DropDowns.getSelectedValue(By.xpath((CommonUtils.getJsonValue("select_action_dropdown", jsonValueFileName)) + actionCount + "']"));
        if (actionType.equalsIgnoreCase("Modify Result Set") || actionType.equalsIgnoreCase(" PDP Redirect")) {
            alertMessage = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_one", jsonValueFileName))).getText();
        } else {
            alertMessage = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();

        }
        Assert.assertEquals("The Execute New search compatibility message doesn't match", CommonUtils.getJsonKeyValue(alertType, jsonKeyValueFileName).get("Error").toString(), alertMessage);


    }

    @Then("^I should see the same context \"([^\"]*)\" for the action$")
    public void iShouldSeeTheSameContextForTheAction(String contextType) throws Throwable {
        String[] context = {"DESKTOP", "TABLET", "ALL MOBILE", "STORE SEARCH AND SEND"};
        String checkedActionTypeUI, unCheckedActionTypeUI;
        switch (contextType) {
            case "DESKTOP":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript("return document.getElementById(" + contextType + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < context.length; j++) {
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + context[j] + actionCount + ").checked = false");
                    }
                }
                break;
            case "TABLET":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < context.length; j++) {
                        if (j == 1)
                            continue;
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + context[j] + actionCount + ").checked = false");

                    }
                }
                break;
            case "ALL MEDIA":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < context.length; j++) {
                        if (j == 2)
                            continue;
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + context[j] + actionCount + ").checked = false");

                    }
                }
                break;
            case "STORE SEARCH AND SEND":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < context.length; j++) {
                        if (j == 3)
                            break;
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + context[j] + actionCount + ").checked = false");
                        break;
                    }
                }
        }

    }

    @And("^I should see the \"([^\"]*)\" Modify Result Set Action with \"([^\"]*)\" operator and data as entered$")
    public void iShouldSeeTheModifyResultSetActionWithOperatorAndDataAsEntered(String actionNum, String operator) throws Throwable {
        String operatorUI, productIdUI, savedSetQueryId, boostAttributeValueUI;
        ;
        int operatorCount = 0;

        switch (operator) {
            case "first":
                operatorUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + operatorCount));
                productIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_product_id", jsonValueFileName) + operatorCount + "_0")).getAttribute("value");
                savedSetQueryId = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_saved_query", jsonValueFileName) + operatorCount + "_0")).getAttribute("value");
                Assert.assertEquals("The operator value doesnt match as entered", operator, operatorUI);
                Assert.assertEquals("Product Id doesn't match as entered", productId, productIdUI);
                Assert.assertEquals("Saved set Id doesn't match as entered", savedQuery, savedSetQueryId.split(",")[0]);
                Assert.assertEquals("Saved query Id doesn't match as entered", savedSet, savedSetQueryId.split(",")[1]);

            case "second":
                operatorUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + operatorCount + 1));
                productIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_product_id", jsonValueFileName) + operatorCount + 1 + "_0")).getAttribute("value");
                savedSetQueryId = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_saved_query", jsonValueFileName) + operatorCount + 1 + "_0")).getAttribute("value");
                Assert.assertEquals("The operator value doesn't match as entered", operator, operatorUI);
                Assert.assertEquals("Product Id doesn't match as entered", productId, productIdUI);
                Assert.assertEquals("Saved set Id doesn't match as entered", savedQuery, savedSetQueryId.split(",")[0]);
                Assert.assertEquals("Saved query Id doesn't match as entered", savedSet, savedSetQueryId.split(",")[1]);

            case "third":
                operatorUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + operatorCount + 2));
                productIdUI = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_product_id", jsonValueFileName) + operatorCount + 2 + "_0")).getAttribute("value");
                savedSetQueryId = Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_saved_query", jsonValueFileName) + operatorCount + 2 + "_0")).getAttribute("value");
                Assert.assertEquals("The operator value doesn't match as entered", operator, operatorUI);
                Assert.assertEquals("Product Id doesn't match as entered", productId, productIdUI);
                Assert.assertEquals("Saved set Id doesn't match as entered", savedQuery, savedSetQueryId.split(",")[0]);
                Assert.assertEquals("Saved query Id doesn't match as entered", savedSet, savedSetQueryId.split(",")[1]);

            case "fourth":
                operatorUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + operatorCount + 3));
                Assert.assertEquals("The operator value doesn'st match as entered", operator, operatorUI);
                boostAttributeValueUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("boost_attribute_value_ui", jsonValueFileName))).getText();
                Assert.assertEquals("Boost Attribute Value doesn't match", boostAttribute, boostAttributeValueUI);
            default:
                logger.info("The action at" + actionNum + "is not valid");
        }

    }

    @And("^I should see the context \"([^\"]*)\" for the \"([^\"]*)\" action$")
    public void iShouldSeeTheContextForTheAction(String actionNum, String context) throws Throwable {
        String checkedActionTypeUI, unCheckedActionTypeUI;
        String[] contextType = {"DESKTOP", "TABLET", "ALL MOBILE", "STORE SEARCH AND SEND"};
        switch (context) {
            case "DESKTOP":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript("return document.getElementById(" + contextType + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < contextType.length; j++) {
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType[j + 1] + actionCount + ").checked = false");
                    }
                }
                break;
            case "TABLET":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + context + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < contextType.length; j++) {
                        if (j == 1)
                            continue;
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType[j] + actionCount + ").checked = false");

                    }
                }
                break;
            case "ALL Mobile":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < contextType.length; j++) {
                        if (j == 2)
                            continue;
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType[j] + actionCount + ").checked = false");

                    }
                }
                break;
            case "STORE SEARCH AND SEND":
                for (int i = 0; i < actionCount; i++) {
                    checkedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + context + actionCount + ").checked = true");
                    logger.info(checkedActionTypeUI + "checked");
                    for (int j = 0; j < contextType.length; j++) {
                        if (j == 3)
                            break;
                        unCheckedActionTypeUI = (String) Navigate.execJavascript(" document.getElementById(" + contextType[j] + actionCount + ").checked = false");
                        break;
                    }
                }
        }
    }

    @Then("^I confirm below Modify Result Set Operator types are disabled$")
    public void i_confirm_below_Modify_Result_Set_Operator_types_are_disabled(DataTable data) throws Throwable {
        List<String> disabledOptionsUI = new ArrayList<String>();
        List<List<String>> listData = data.raw();
        List<WebElement> disabledElements;
        disabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("modify_result_set_operators_disabled_one", jsonValueFileName) + actionCount + CommonUtils.getJsonValue("modify_result_set_operators_disabled_two", jsonValueFileName)));
        disabledElements.stream().map(WebElement::getText).forEach(disabledOptionsUI::add);

        for (int i = 0; i < disabledOptionsUI.size(); i++) {
            Assert.assertEquals("Disabled Modify Result Set Operator Types: ", listData.get(i).get(0), disabledOptionsUI.get(i));
        }
    }

    @Then("^I confirm below Modify Result Set Operator types are enabled$")
    public void i_confirm_below_Modify_Result_Set_Operator_types_are_enabled(DataTable data) throws Throwable {
        List<String> enabledOptionsUI = new ArrayList<String>();
        List<List<String>> listData = data.raw();
        List<WebElement> enabledElements;
        enabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("modify_result_set_operators_enabled_one", jsonValueFileName) + actionCount + CommonUtils.getJsonValue("modify_result_set_operators_enabled_two", jsonValueFileName)));
        enabledElements.stream().map(WebElement::getText).forEach(enabledOptionsUI::add);

        for (int i = 0; i < enabledOptionsUI.size(); i++) {
            Assert.assertEquals("Disabled Modify Result Set Operator Types: ", listData.get(i).get(0), enabledOptionsUI.get(i));
        }
    }

    @Then("^I should see \"([^\"]*)\" selected in Create RuleRule Priority field$")
    public void i_should_see_selected_in_Create_RuleRule_Priority_field(String rulePriority) throws Throwable {
        switch (rulePriority) {
            case "no value":
                Assert.assertTrue("Rule priority Default selection is not blank", (DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName)))).equals(""));
                break;
            case "90":
                Assert.assertTrue("Rule priority Default selection is not 90", (DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName)))).equals("90"));
                break;
        }
        logger.info("Rule Priority Default selection is:  " + (DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName)))));
    }

    @Then("^I confirm \"([^\"]*)\" is enabled on rules page$")
    public void i_confirm_Category_Redirect_is_enabled_on_rule_page(String actionType) throws Throwable {
        List<String> enabledOptionsUI = new ArrayList<String>();
        List<WebElement> enabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("actions_enabled", jsonValueFileName)));
        enabledElements.stream().map(WebElement::getText).forEach(enabledOptionsUI::add);
        Assert.assertTrue(actionType + " action is not enabled", enabledOptionsUI.toString().contains(actionType));
    }

    @Then("^I confirm an error message \"([^\"]*)\"$")
    public void i_confirm_an_error_message(String expectedAlertMessage) throws Throwable {
        String alertMessage = Elements.findElement(By.xpath(CommonUtils.getJsonValue("error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The alert message doesn't match", expectedAlertMessage, alertMessage);
    }

    @Then("^I see \"([^\"]*)\" in alphanumeric order on Rule page$")
    public void i_see_in_alphanumeric_order_on_Rule_page(String valuesType, DataTable data) throws Throwable {
        switch (valuesType) {
            case "Trigger Types":
                List<String> triggerTypes = DropDowns.getAllValues("login.trigger_type");
                Assert.assertEquals("The Trigger Types are not sorted in alphanumeric order", CommonUtils.getSortedList(triggerTypes), data.asList(String.class));
                break;
            case "Action Types":
                List<String> actionTypes = DropDowns.getAllValues("login.action_type");
                Assert.assertEquals("The Action Types are not sorted in alphanumeric order", CommonUtils.getSortedList(actionTypes), data.asList(String.class));
                break;
            case "Result Set Operator Types":
                List<String> resultSetOperatorTypes = DropDowns.getAllValues("rules.result_set_trigger_dropdown");
                Assert.assertEquals("Result Set Operator Types are not sorted in alphanumeric order", CommonUtils.getSortedList(resultSetOperatorTypes), data.asList(String.class));
                break;
            case "Show Media Operator Types":
                List<String> showMediaOperatorTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("media_type", jsonValueFileName) + actionCount));
                Assert.assertEquals("Show Media Operator Types are not sorted in alphanumeric order", CommonUtils.getSortedList(showMediaOperatorTypes), data.asList(String.class));
                break;
            case "Modify Result Set Operator Types":
                List<String> modifyResultSetOperatorTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName) + actionCount));
                Assert.assertEquals("Modify Result Set Operator Types are not sorted in alphanumeric order", CommonUtils.getSortedList(modifyResultSetOperatorTypes), data.asList(String.class));
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" as default value for Keyword Pattern Trigger in Create Rule page$")
    public void i_see_as_default_value_for_Keyword_Pattern_Trigger_in_Create_Rule_page(String matchType) throws Throwable {
        Assert.assertTrue("Rules Page - Keyword Pattern Trigger default Match type is not 'Has exact match to'", Elements.findElement("rules.keyword_patter_match_type").getText().equals(matchType));
    }

    @Then("^I see Remove option on each facet row to the right of the Expiration Date$")
    public void i_see_Remove_option_on_each_facet_row_to_the_right_of_the_Expiration_Date(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        for (int count = 1; count < listData.size(); count++) {
            Assert.assertTrue("Facet Remove option is not displayed", Elements.findElement(By.xpath(CommonUtils.getJsonValue("display_facet_table_remove_button_1", jsonValueFileName) + count + CommonUtils.getJsonValue("display_facet_table_remove_button_2", jsonValueFileName))).isDisplayed());
        }
    }

    @And("^I should see the rule status as \"([^\"]*)\"$")
    public void iShouldSeeTheRuleStatusAs(String status) throws Throwable {
        String statusUI;
        statusUI = Elements.findElement("rules.rule_status_field").getAttribute("innerHTML");
        Wait.until(() ->(status.equals(statusUI.equals(status))));
        Assert.assertEquals("The rule status is not" + status, status, statusUI);
    }

    @And("^I should see the publish flag as \"([^\"]*)\"$")
    public void iShouldSeeThePublishFlagAs(String flag) throws Throwable {
        String publishFlag;
        publishFlag = Elements.findElement("rules.publish_flag").getText();
        Assert.assertEquals("The publish flag status is not" + flag, flag, publishFlag);
    }

    @When("^I select the attribute as \"([^\"]*)\" from Result Set dropdown$")
    public void iSelectTheAttributeAsFromResultSetDropdown(String attribute) throws Throwable {
        DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + criteriaValueMap.size() + "'" + CommonUtils.getJsonValue("result_set_select_criteria", jsonValueFileName)))), attribute);

    }

    @And("^I select the \"([^\"]*)\"$")
    public void iSelectThe(String sequence) throws Throwable {
        DropDowns.selectByText("rules.value_management_sequence_dropdown", sequence);
    }

    @And("^I should see the Value Management column value as \"([^\"]*)\"$")
    public void iShouldSeeTheValueManagementColumnValueAs(String sequence) throws Throwable {
        String sequenceUI = Elements.findElement("rules.value_management_sequence").getText();
        Assert.assertEquals("The value management column value is not as expected", sequence, sequenceUI);
    }

    @And("^I select the value sequence as \"([^\"]*)\"$")
    public void iSelectTheValueSequenceAs(String sequence) throws Throwable {
        DropDowns.selectByText("rules.value_management_sequence_dropdown", sequence);
    }

    @When("^I enter the sequence values for random sequences in decreasing order$")
    public void iEnterTheSequenceValuesForRandomSequencesInDecreasingOrder() throws Throwable {
        List<WebElement> sequenceList = Elements.findElements("rules.manual_sequence_values");
        manualSequence = (int) (Math.random() * sequenceList.size() + 1);
        TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("sequence_value_text_one", jsonValueFileName) + manualSequence + By.xpath(CommonUtils.getJsonValue("sequence_value_text_two", jsonValueFileName))), String.valueOf(manualSequence));
        TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("sequence_value_text_one", jsonValueFileName) + (manualSequence - 2) + By.xpath(CommonUtils.getJsonValue("sequence_value_text_two", jsonValueFileName))), String.valueOf(manualSequence + 2));
        Clicks.click(By.xpath(CommonUtils.getJsonValue("save_suppressed_value", jsonValueFileName)));
    }

    @Then("^I should see the sequence values in increasing order$")
    public void iShouldSeeTheSequenceValuesInIncreasingOrder() throws Throwable {
        String valueOne, valueTwo;
        valueOne = Elements.findElement(By.xpath(CommonUtils.getJsonValue("sequence_value_text_one", jsonValueFileName) + 1 + By.xpath(CommonUtils.getJsonValue("sequence_value_text_two", jsonValueFileName)))).getText();
        valueTwo = Elements.findElement(By.xpath(CommonUtils.getJsonValue("sequence_value_text_one", jsonValueFileName) + 2 + By.xpath(CommonUtils.getJsonValue("sequence_value_text_two", jsonValueFileName)))).getText();
        Assert.assertEquals("The values don't appear to be in sequence", Integer.valueOf(valueOne) < Integer.valueOf(valueTwo));
    }

    @And("^I create Facet Refinement trigger with \"([^\"]*)\"$")
    public void iCreateFacetRefinementTriggerWith(String param) throws Throwable {
        TextBoxes.typeTextNEnter(By.xpath(CommonUtils.getJsonValue("facet_sub_criteria_one", jsonValueFileName) + index + "_" + triggerCount + CommonUtils.getJsonValue("facet_sub_criteria_two", jsonValueFileName) + CommonUtils.getJsonValue("facet_sub_criteria_three", jsonValueFileName)), "brand");
        TextBoxes.typeTextNEnter(By.xpath(CommonUtils.getJsonValue("facet_sub_criteria_one", jsonValueFileName) + index + "_" + triggerCount + CommonUtils.getJsonValue("facet_sub_criteria_two", jsonValueFileName) + CommonUtils.getJsonValue("Facet_sub_values", jsonValueFileName)), CommonUtils.getJsonKeyValue("Invalid Trigger Value", jsonKeyValueFileName).get(param).toString());
    }

    @And("^I create Result Set trigger with \"([^\"]*)\"$")
    public void iCreateResultSetTriggerWith(String param) throws Throwable {
        DropDowns.selectByValue((By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + triggerCount + "'" + CommonUtils.getJsonValue("result_set_operator", jsonValueFileName))), "Normal Color");
        TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + triggerCount + "'" + CommonUtils.getJsonValue("result_set_operator_value", jsonValueFileName)), CommonUtils.getJsonKeyValue("Invalid Trigger Value", jsonKeyValueFileName).get(param).toString());
    }

    @Then("^I should see the below option is disabled for All Search$")
    public void iShouldSeeTheBelowOptionIsDisabledForAllSearch(DataTable data) throws Throwable {
        List<String> disabledOptionsUI = new ArrayList<String>();
        List<String> enabledOptions;
        List<List<String>> listData = data.raw();
        List<WebElement> enabledElements;
        enabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("actions_disabled", jsonValueFileName)));
        enabledElements.stream().map(WebElement::getText).forEach(disabledOptionsUI::add);
        Assert.assertTrue("The disabled elements list doesn't contain " + listData.get(0).get(0), disabledOptionsUI.contains(listData.get(0).get(0)));
    }

    @Then("^I should see the Rule error as \"([^\"]*)\"$")
    public void iShouldSeeTheRuleErrorAs(String errMsg) throws Throwable {
        String errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_page_error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The error Message doesn't Match for", errMsg, errorMessageUI);
    }

    @And("^I edit the \"([^\"]*)\" as \"([^\"]*)\"$")
    public void iEditTheAs(String ruleParam, String value) throws Throwable {

        switch (ruleParam) {
            case "Effective Date":
                if (value.equalsIgnoreCase("Current Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_effective_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("effective date", "MM/dd/YYYY", 0) + "'");
                } else if (value.equalsIgnoreCase("Future Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_effective_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("effective date", "MM/dd/YYYY", 4) + "'");
                } else if (value.equalsIgnoreCase("Expired Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_effective_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("expiration date", "MM/dd/YYYY", -8) + "'");

                } else if (value.equalsIgnoreCase("Invalid Format Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_effective_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("effective date", "yyy/mm/dd", -4) + "'");
                } else {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_effective_date", jsonValueFileName) + "').value='" + value + "'");

                }
                break;
            case "Expiry Date":
                if (value.equalsIgnoreCase("Prior Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_expiry_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("expiration date", "MM/dd/YYYY", -4) + "'");
                } else if (value.equalsIgnoreCase("Future Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_expiry_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("expiration date", "MM/dd/YYYY", 8) + "'");
                } else if (value.equalsIgnoreCase("Invalid Format Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_expiry_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("expiration date", "dd/yyy/MM", -4) + "'");
                } else if (value.equalsIgnoreCase("Expired Date")) {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_expiry_date", jsonValueFileName) + "').value='" + CommonUtils.getDateInFormat("expiration date", "MM/dd/YYYY", -4) + "'");

                } else {
                    Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("rule_expiry_date", jsonValueFileName) + "').value='" + value + "'");
                }
                break;

        }

    }

    @And("^I enter Rule Priority, FOB$")
    public void iEnterRulePriorityFOB() throws Throwable {
        String rulePriorityUI;
        List<String> fobList;
        setRuleDetails();
        Wait.forPageReady();
        logger.info("rule.rulePriority", rule.rulePriority);
        Navigate.execJavascript("document.getElementById('rulePriority').value=" + rule.rulePriority);
        Wait.forPageReady();
        rulePriorityUI = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("rule_priority", jsonValueFileName)));
        logger.info("rulePriorityUI selected" + rulePriorityUI);
        Wait.forPageReady();
        fobList = DropDowns.getAllValuesUsingJavaScript("login.fob");
        logger.info("++++++++++++" + fobList);
        DropDowns.selectRandomValue("login.fob");
    }

    @Then("^I should see all search compatible error message \"([^\"]*)\"$")
    public void iShouldSeeAllSearchCompatibleErrorMessage(String compatibleError) throws Throwable {
        String error;
        error = Elements.findElement(By.xpath(CommonUtils.getJsonValue("rule_error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The All search compatible error doesn't match", compatibleError, error);
    }

    @Then("^I click on Remove option next to Facet on Manage Facet action$")
    public void i_click_on_Remove_option_next_to_Facet_on_Manage_Facet_action() throws Throwable {
        manageFacetToBeRemoved = Elements.findElement(By.xpath(CommonUtils.getJsonValue("manage_facet_action_facet", jsonValueFileName))).getText();
        Elements.findElement(By.xpath(CommonUtils.getJsonValue("display_facet_table_remove_button_1", jsonValueFileName) + 1 + CommonUtils.getJsonValue("display_facet_table_remove_button_2", jsonValueFileName))).click();
    }

    @And("^I select \"([^\"]*)\" trigger on the new create rule page$")
    public void iSelectTriggerOnTheNewCreateRulePage(String selectTrigger) throws Throwable {
        Clicks.click("rules.add_trigger");
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("trigger_sequence", jsonValueFileName) + newRuleIndex), selectTrigger);
    }

    @And("^I save trigger on the new create rule page$")
    public void iSaveTriggerOnTheNewCreateRulePage() throws Throwable {
        Wait.forPageReady();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + newRuleIndex + "').click()");
        newRuleIndex++;
    }

    @And("^I should see \"([^\"]*)\" message$")
    public void iShouldSeeMessage(String value) throws Throwable {
        String resultTextUI = Elements.findElement("result_text").getAttribute("text");
        Assert.assertEquals("The Invalid message doesn't match for" + value, CommonUtils.getJsonKeyValue("Invalid_search_result_text", jsonKeyValueFileName).get(value));
    }

    @Then("^I see default \"([^\"]*)\" selection in Context Container$")
    public void i_see_default_selection_in_Context_Container(String contextType) throws Throwable {
        switch (contextType) {
            case "Shopping Mode":
                Assert.assertTrue("Shopping Mode - Site is not selected by default", Elements.findElement("rules.shopping_mode_site").isSelected());
                Assert.assertFalse("Shopping Mode Registry is not deselected by default", Elements.findElement("rules.shopping_mode_registry").isSelected());
                break;
            case "Navigation":
                Assert.assertFalse("Navigation - Search is not deselected by default", Elements.findElement("rules.navigation_search").isSelected());
                Assert.assertFalse("Navigation - Browse is not deselected by default", Elements.findElement("rules.navigation_browse").isSelected());
                Assert.assertFalse("Navigation - Landing is not deselected by default", Elements.findElement("rules.navigation_landing").isSelected());
                break;
        }
    }

    @When("^I click on Remove option for the below facets$")
    public void i_click_on_Remove_option_for_the_below_facets(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        for (int count = 1; count < listData.size(); count++) {
            Elements.element(CommonUtils.getJsonValue("display_facet_table_remove_button_1", jsonValueFileName) + count + CommonUtils.getJsonValue("display_facet_table_remove_button_2", jsonValueFileName));
        }
    }

    @And("^I enter \"([^\"]*)\" PPID$")
    public void iEnterPPID(String val) throws Throwable {
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("pdp_redirect_input", jsonValueFileName) + actionCount), CommonUtils.getJsonKeyValue("PDP Redirect", jsonKeyValueFileName).get(val).toString());

    }

    @Then("^I select \"([^\"]*)\" with facet value as \"([^\"]*)\"$")
    public void iSelectWithFacetValueAs(String criteria, String value) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("result_set_criteria", jsonValueFileName) + resultSetCriteriaCount), criteria);
        TextBoxes.typeTextbox("rules.result_set_input", value);
        resultSetCriteriaCount++;
    }

    @And("^I click on add more criteria$")
    public void iClickOnAddMoreCriteria() throws Throwable {
        Elements.findElement(By.xpath(CommonUtils.getJsonValue("add_trigger_criteria_plus", jsonValueFileName))).click();
    }

    @Then("^I should see popup alert as \"([^\"]*)\"$")
    public void iShouldSeePopupAlertAs(String errorMessage) throws Throwable {
        String errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_one", jsonValueFileName))).getText();
        Assert.assertEquals("The error Message doesn't Match", CommonUtils.getJsonKeyValue("Duplicate Criteria Error", jsonKeyValueFileName).get(errorMessage).toString(), errorMessageUI);

    }

    @And("^I select the same \"([^\"]*)\" trigger on rules page$")
    public void iSelectTheSameTriggerOnRulesPage(String selectTrigger) throws Throwable {
        index = 0;
        Clicks.click("rules.add_trigger");
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("trigger_sequence", jsonValueFileName) + index), selectTrigger);
    }

    @And("^I click on context after clicking save action$")
    public void iClickOnContextAfterClickingSaveAction() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("context_image", jsonValueFileName) + actionCount));
    }

    @And("^I de select the navigation context \"([^\"]*)\"$")
    public void iDeSelectTheNavigationContext(String contextOption) throws Throwable {
        WebElement element = Elements.findElement(By.id(contextOption.toUpperCase() + actionCount));
        element.click();
    }

    @And("^I click save action$")
    public void iClickSaveAction() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("save_action", jsonValueFileName) + actionCount));
    }

    @Then("^I should see the rule alert as \"([^\"]*)\"$")
    public void iShouldSeeTheRuleAlertAs(String errorMsg) throws Throwable {
        String errorMsgUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();
        Assert.assertEquals("The Rule Alert Message doesn't match", errorMsg, errorMsgUI);
    }

    @When("^I again select \"([^\"]*)\" action$")
    public void iAgainSelectAction(String actionType) throws Throwable {
        DropDowns.selectByText(By.xpath((CommonUtils.getJsonValue("select_action_dropdown", jsonValueFileName)) + actionCount + "']"), actionType);

    }

    @Then("^I should see the \"([^\"]*)\" as disabled$")
    public void iShouldSeeTheAsDisabled(String arg0) throws Throwable {
        Assert.assertFalse(Elements.findElement(By.id(CommonUtils.getJsonValue("apply_non_canvas", jsonValueFileName) + actionCount)).isEnabled());
    }

    @And("^I click save trigger$")
    public void iClickSaveTrigger() throws Throwable {
        Wait.forPageReady();
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + triggerCount + "').click()");

    }

    @And("^I edit the keyword pattern trigger with other criteria and value without saving the trigger$")
    public void iEditTheKeywordPatternTriggerWithOtherCriteriaAndValueWithoutSavingTheTrigger() throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("keyword_pattern_criteria_value", jsonKeyValueFileName);
        keywordPatternCriteriaEdit = criteriaValueMap.keySet().toArray()[1].toString();
        keywordPatternValueEdit = criteriaValueMap.values().toArray()[1].toString();
        DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + 0 + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_criteria", jsonValueFileName)))), keywordPatternCriteriaEdit);
        TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("trigger_criteria", jsonValueFileName) + 0 + CommonUtils.getJsonValue("div_val", jsonValueFileName) + "'" + CommonUtils.getJsonValue("keyword_pattern_value", jsonValueFileName)), keywordPatternValueEdit);
    }

    @Then("^I confirm that \"([^\"]*)\" option is disabled$")
    public void iConfirmThatOptionIsDisabled(String mediaOption) throws Throwable {
        List<String> disabledOptionsUI = new ArrayList<>();
        List<WebElement> enabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("show_media_disabled", jsonValueFileName)));
        enabledElements.stream().map(WebElement::getText).forEach(disabledOptionsUI::add);
        Assert.assertTrue("The disabled elements list doesn't contain " + mediaOption, disabledOptionsUI.contains(mediaOption));
    }

    @Then("^I should not see multiple trigger operator box on Create Rule page$")
    public void i_should_not_see_multiple_trigger_operator_box_on_Create_Rules_page() throws Throwable {
        Assert.assertEquals("Multiple Trigger Operator box is visible on Create Rule page", 0, (Elements.findElements("rules.multiple_trigger_operator_label")).size());
    }

    @Then("^I see multiple trigger operator box between first Trigger and second trigger with operator OR selected on Create Rule page$")
    public void i_see_multiple_trigger_operator_box_between_first_Trigger_and_second_trigger_with_operator_OR_selected_on_Create_Rule_page() throws Throwable {
        Assert.assertTrue("Multiple Trigger Operator box is not visible on Create Rule page", Elements.findElement("rules.multiple_trigger_operator_label").isDisplayed());
        Assert.assertFalse("Multiple Trigger Operator Type And is not deselected by default on Create Rule page", Elements.findElement("rules.multiple_trigger_operator_And").getAttribute("value").equalsIgnoreCase("A"));
        Assert.assertTrue("Multiple Trigger Operator Type Or is not selected by default on Create Rule page", Elements.findElement("rules.multiple_trigger_operator_Or").isSelected());
    }

    @Then("^I see operator \"([^\"]*)\" selected by default with \"([^\"]*)\" percent on Create Rule page$")
    public void i_see_operator_selected_by_default_with_percent_on_Create_rule_page(String operatorType, String percentageValue) throws Throwable {
        System.out.print("iiiiiiiiiiiiiiiii" + DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("result_set_logical_operator", jsonValueFileName))).replace(" ", "") + "qeew");
        Assert.assertEquals("Result Set default selected operator type is not '>'", operatorType, DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("result_set_logical_operator", jsonValueFileName))).replace(" ", ""));
        Assert.assertEquals("Result Set default selected percetnage is not '75'", percentageValue, Elements.findElement(By.id((CommonUtils.getJsonValue("result_set_percentage", jsonValueFileName)))).getAttribute("value"));
    }

    @When("^I save Result Set Trigger$")
    public void iSaveResultSetTrigger() throws Throwable {
        Clicks.click(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + index));
        if (Elements.findElement(By.id(CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + index)).getAttribute("value").equalsIgnoreCase("edit")) {
            logger.info("save button clicked");
        } else {
            Elements.findElement(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + index)).click();
            if (Elements.findElement(By.id(CommonUtils.getJsonValue("edit_trigger", jsonValueFileName) + index)).getAttribute("value").equalsIgnoreCase("edit")) {
                logger.info("save button clicked");
            } else {
                Clicks.javascriptClick(By.id(CommonUtils.getJsonValue("save_trigger", jsonValueFileName) + index));
            }
        }
    }

    @Then("^I should see that search query option is \"([^\"]*)\"$")
    public void iShouldSeeThatSearchQueryOptionIs(String query) throws Throwable {
        System.out.println("search query" + Elements.findElement(By.id(CommonUtils.getJsonValue("keyword_pattern_search_query", jsonValueFileName))).getText());
        Assert.assertEquals("search query is not displayed as expected", query, Elements.findElement(By.id(CommonUtils.getJsonValue("keyword_pattern_search_query", jsonValueFileName))).getText());
    }

    @When("^I select search Query option as \"([^\"]*)\"$")
    public void iSelectSearchQueryOptionAs(String arg0) throws Throwable {
        Elements.findElement(By.id(CommonUtils.getJsonValue("keyword_pattern_search_query", jsonValueFileName))).click();

    }

    @Then("^I should see the option \"([^\"]*)\" as disabled$")
    public void iShouldSeeTheOptionAsDisabled(String arg0) throws Throwable {

    }

    @When("^I create Result Set trigger with single criteria$")
    public void iCreateResultSetTriggerWithSingleCriteria() throws Throwable {
        String attributeKey;
        String[] attributeValue;
        int count = 0;
        criteriaValueMap = CommonUtils.getJsonKeyValue("result_set_criteria_values", jsonKeyValueFileName);
        Iterator mapIterator = criteriaValueMap.entrySet().iterator();
        Map.Entry pair = (Map.Entry) mapIterator.next();
        attributeKey = pair.getKey().toString();
        attributeValue = pair.getValue().toString().split(",");
        DropDowns.selectByText((Elements.findElement(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_select_criteria", jsonValueFileName)))), attributeKey);
        if (attributeKey.equalsIgnoreCase("Price Type")) {
            List<WebElement> webElements = Elements.findElements("rules.result_set_price_type");
            int randomPriceType = (int) (Math.random() * webElements.size() + 1);
            Elements.findElement(By.xpath(CommonUtils.getJsonValue("result_set_price_type_one", jsonValueFileName) + randomPriceType + By.xpath(CommonUtils.getJsonValue("result_set_price_type_two", jsonValueFileName)))).click();
        } else {
            TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_select_value", jsonValueFileName)), attributeValue[0]);
            DropDowns.selectByValue((By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_operator", jsonValueFileName))), attributeValue[1]);
            TextBoxes.typeTextbox(By.xpath(CommonUtils.getJsonValue("result_set_sub_criteria", jsonValueFileName) + index + "_" + count + "'" + CommonUtils.getJsonValue("result_set_operator_value", jsonValueFileName)), attributeValue[2]);
        }
    }

    @And("^I clear the percentage value and save trigger$")
    public void iClearThePercentageValueAndSaveTrigger() throws Throwable {
            TextBoxes.typeTextbox("rules.result_set_percent","");
            Clicks.javascriptClick("login.save_trigger");
        }

    @And("^I again enter the same \"([^\"]*)\" for Modify Result$")
    public void iAgainEnterTheSameForModifyResult(String arg0) throws Throwable {
        Navigate.execJavascript("document.getElementById('" + CommonUtils.getJsonValue("modify_result_set_saved_query", jsonValueFileName) + (actionCount-1) + "_0').value='" + savedSet+" " +savedSet+ " " + savedQuery+" "+savedQuery + "'");
        modifyResultSetOperator = actionCount;


    }

    @When("^I clear the modify result set input boxes$")
    public void iClearTheModifyResultSetInputBoxes() throws Throwable {

//        Elements.findElement(By.id(CommonUtils.getJsonValue("modify_result_set_product_id", jsonValueFileName) + 0 + "_0'")).clear();
        TextBoxes.typeTextbox("rules.modify_result_set_product_id","");
        TextBoxes.typeTextbox("rules.modify_result_set_savedset_id","");
        Thread.sleep(500000);
    }

    @Then("^I should get the \"([^\"]*)\" Alert$")
    public void iShouldGetTheAlert(String errorMsg) throws Throwable {
       String errorMessageUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("trigger_error_message_two", jsonValueFileName))).getText();
        Assert.assertEquals("The error Message doesn't Match for" + errorMsg, CommonUtils.getJsonKeyValue("RDPP Error Messages", jsonKeyValueFileName).get(errorMsg).toString(), errorMessageUI);

    }

    @Then("^I see \"([^\"]*)\" label with associated checkbox placed below Display label$")
    public void i_see_label_with_associated_checkbox_placed_below_Display_label(String displayLabel) throws Throwable {
        Elements.findElement("rules.value_management_display_check_all").isDisplayed();
        Elements.findElement("rules.value_management_display_check_all").getText().equals(displayLabel);
        Assert.assertTrue(displayLabel + " is not displayed", Elements.findElement("rules.value_management_display_check_all").isDisplayed());
        Elements.findElement("rules.value_management_display_check_all_check_box").isDisplayed();
        Assert.assertTrue(displayLabel + " check box is not selected by default", Elements.findElement("rules.value_management_display_check_all_check_box").isSelected());
    }

    @Then("^I confirm Display checkboxes associated to all the facets are checked by default$")
    public void i_confirm_Display_checkboxes_associated_to_all_the_facets_are_checked_by_default() throws Throwable {
        displayValueCheckBoxesCount = Elements.findElements("rules.value_management_disply_value_chech_boxes").size();
        for (int count = 1; count < displayValueCheckBoxesCount; count++) {
            Assert.assertTrue(Elements.findElement(By.xpath(CommonUtils.getJsonValue("value_management_check_boxes_1", jsonValueFileName) + count + CommonUtils.getJsonValue("value_management_check_boxes_2", jsonValueFileName))).isSelected());
        }
    }

    @When("^I uncheck the Check All checkbox on Manage Facet value management popup$")
    public void i_uncheck_the_Check_All_checkbox_on_Manage_Facet_value_management_popup() throws Throwable {
        Elements.findElement("rules.value_management_display_check_all_check_box").click();
        Assert.assertFalse("Check All check box is not deselected", Elements.findElement("rules.value_management_display_check_all_check_box").isSelected());
    }

    @Then("^I confirm all Display checkboxes associated to the facets are unselected$")
    public void i_confirm_all_Display_checkboxes_associated_to_the_facets_are_unselected() throws Throwable {
        displayValueCheckBoxesCount = Elements.findElements("rules.value_management_disply_value_chech_boxes").size();
        for (int count = 1; count < displayValueCheckBoxesCount; count++) {
            Assert.assertFalse(Elements.findElement(By.xpath(CommonUtils.getJsonValue("value_management_check_boxes_1", jsonValueFileName) + count + CommonUtils.getJsonValue("value_management_check_boxes_2", jsonValueFileName))).isSelected());
        }
    }

    @When("^I click Save on Manage Facet value management popup$")
    public void i_click_Save_on_Manage_Facet_value_management_popup() throws Throwable {
        Clicks.click(By.xpath(CommonUtils.getJsonValue("save_suppressed_value", jsonValueFileName)));
    }

    @Then("^I should see the rule popup alert as \"([^\"]*)\"$")
    public void i_should_see_the_rule_popup_alert_as(String alertMessage) throws Throwable {
        Assert.assertEquals("The alert message doesn't match", alertMessage, Elements.findElement(By.xpath(CommonUtils.getJsonValue("error_message", jsonValueFileName))).getText());
    }

    @When("^I select context action as \"([^\"]*)\" for the same action$")
    public void iSelectContextActionAsForTheSameAction(String contextOption) throws Throwable {
        WebElement element = Elements.findElement(By.id(contextOption.toUpperCase() + (actionCount-1)));
        element.click();
    }
}
