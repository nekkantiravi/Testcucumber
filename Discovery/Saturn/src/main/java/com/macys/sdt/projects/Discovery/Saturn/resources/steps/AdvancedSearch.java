package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by M671871 on 7/31/2017.
 */
public class AdvancedSearch {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Utils.class);
    private static String dbName = "oracle";
    //  Staging database details
    private static String dbUrl = "jdbc:oracle:thin:@dml1-scan:1521/dpmsts01";
    private static String dbUsername = "Saturn";
    private static String dbPassword = "Saturn";
    //  CELL 2 database details
//    private static String dbUrl = "jdbc:oracle:thin:@mdc2vra131:1521/starsdev";
//    private static String dbUsername = "saturn";
//    private static String dbPassword = "saturn";

    String selectedTriggerType;
    String selectedActionType;
    String categoryId;
    String includedCategoryId;
    String excludedCategoryId;
    String includedCategoryId_1;
    String excludedCategoryId_1;
    String includedCategoryId_2;
    String excludedCategoryId_2;
    String resultCountInput;
    String urlInputType;
    String pdpId;
    String query;
    String totalNumberOfRulesUI;
    String totalNumberOfRulesDB;
    String siteTenant = CommonUtils.getTenantName();;
    String resultCountOperatorType;
    String displayMessageText;
    String executeSearchReplaceTerm;
    String selectedKeywordPatternAttributeType;
    int keywordPatternMatchTypeId;
    String keywordPatternAttributeValue;
    String jsonValueFileName = "advanced_search_common";
    String jsonKeyValueFileName ="advanced_search_data";


    @Then("^I see \"([^\"]*)\" Types in alphanumeric order on Advanced Search Page$")
    public void i_see_Types_in_alphanumeric_order_on_Advanced_Search_Page(String type, DataTable data) throws Throwable {
        switch(type) {
            case "Trigger":
                List<String> triggerTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("trigger_type", jsonValueFileName)));
                triggerTypes.removeAll(Arrays.asList("", null));
                Assert.assertEquals("The Trigger Types are not sorted in alphanumeric order", data.asList(String.class), triggerTypes);
                break;
            case "Action":
                List<String> actionTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("action_type", jsonValueFileName)));
                actionTypes.removeAll(Arrays.asList("", null));
                Assert.assertEquals("The Action Types are not sorted in alphanumeric order", data.asList(String.class), actionTypes);
                break;
            case "Keyword Pattern attributes":
                List<String> keywordPatternAttributeTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("keyword_pattern_list", jsonValueFileName)));
                Assert.assertEquals("The Action Types are not sorted in alphanumeric order", data.asList(String.class), keywordPatternAttributeTypes);
                break;
        }
    }

    @Then("^I see default selections on Advanced Search page$")
    public void i_see_default_Context_selections_on_Advanced_Search_page() throws Throwable {
        Assert.assertEquals("Trigger is selected by default", DropDowns.getSelectedValue(By.id("triggerTypesMenu")), "");
        Assert.assertEquals("Action selected by default", DropDowns.getSelectedValue(By.id("actionTypesMenu")), "");
        Assert.assertTrue("Context - Contains is not selected by default", Elements.findElement("advanced_search.context_contains").isSelected());
        Assert.assertFalse("Context - Exact Match is not deselected by default", Elements.findElement("advanced_search.context_exact_match").isSelected());
        Assert.assertTrue("Device Type - Desktop is not selected by default", Elements.findElement("advanced_search.desktop").isSelected());
        Assert.assertTrue("Device Type - Tablet is not selected by default", Elements.findElement("advanced_search.tablet").isSelected());
        Assert.assertTrue("Device Type - All Mobile is not selected by default", Elements.findElement("advanced_search.all_mobile").isSelected());
        Assert.assertTrue("Device Type - Store Search And Send is  not selected by default", Elements.findElement("advanced_search.store_search_and_send").isSelected());
        Assert.assertTrue("Shopping Mode - Site is not selected by default", Elements.findElement("advanced_search.site").isSelected());
        Assert.assertTrue("Shopping Mode - Registry is not selected by default", Elements.findElement("advanced_search.registry").isSelected());
        Assert.assertTrue("Navigation Type - Search is not selected by default", Elements.findElement("advanced_search.search").isSelected());
        Assert.assertTrue("Navigation Type - Browse is not selected by default", Elements.findElement("advanced_search.browse").isSelected());
        Assert.assertTrue("Navigation Type - Landing is not selected by default", Elements.findElement("advanced_search.landing").isSelected());
        Assert.assertTrue("Locale - Domestic - US is not selected by default", Elements.findElement("advanced_search.domestic_us").isSelected());
        Assert.assertTrue("Locale - International - ALL is not selected by default", Elements.findElement("advanced_search.international_all").isSelected());
    }

    @When("^I select \"([^\"]*)\" trigger on Advanced Search Page$")
    public void i_select_trigger_on_Advanced_Search_Page(String triggerType) throws Throwable {
        Wait.forPageReady();
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("trigger_type", jsonValueFileName)), triggerType);
        selectedTriggerType = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("trigger_type", jsonValueFileName)));
    }

    @When("^I select \"([^\"]*)\" action on Advanced Search Page$")
    public void i_select_action_on_Advanced_Search_Page(String actionType) throws Throwable {
        Wait.forPageReady();
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("action_type", jsonValueFileName)), actionType);
        selectedActionType = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("action_type", jsonValueFileName)));
    }

    @When("^I enter \"([^\"]*)\" in Included Category Ids field for Category ID Trigger on Advanced Search Page$")
    public void i_enter_in_Included_Category_Ids_field_for_Category_ID_Trigger_on_Advanced_Search_Page(String categoryIdInputType) throws Throwable {
        switch(categoryIdInputType) {
            case "Single Category ID":
                if (CommonUtils.bcom()) {
                    categoryId = CommonUtils.getJsonKeyValue("include_category_ids_bcom", jsonKeyValueFileName).get("Single Category ID").toString();
                }else {
                    categoryId = CommonUtils.getJsonKeyValue("include_category_ids_mcom", jsonKeyValueFileName).get("Single Category ID").toString();
                }
                break;
            case "Multiple Category IDs separated by Comma":
                if (CommonUtils.bcom()) {
                    categoryId = CommonUtils.getJsonKeyValue("include_category_ids_bcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Comma").toString();
                } else {
                    categoryId = CommonUtils.getJsonKeyValue("include_category_ids_mcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Comma").toString();
                }
                break;
            case "Multiple Category IDs separated by Space":
                if (CommonUtils.bcom()) {
                    categoryId = CommonUtils.getJsonKeyValue("include_category_ids_bcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Space").toString();
                } else {
                    categoryId = CommonUtils.getJsonKeyValue("include_category_ids_mcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Space").toString();
                }
                break;
            case "invalid_value":
                categoryId = "abc123";
                break;
            case "empty_value":
                categoryId = " ";
                break;
        }
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("include_category_ids", jsonValueFileName)), categoryId);
        includedCategoryId = Elements.findElement(By.id(CommonUtils.getJsonValue("include_category_ids", jsonValueFileName))).getAttribute("value");
    }

    @When("^I enter \"([^\"]*)\" in Excluded Category Ids field for Category ID Trigger on Advanced Search Page$")
    public void i_enter_in_Excluded_Category_Ids_field_for_Category_ID_Trigger_on_Advanced_Search_Page(String categoryIdInputType) throws Throwable {
        switch(categoryIdInputType) {
            case "Single Category ID":
                if (CommonUtils.bcom()) {
                    excludedCategoryId = CommonUtils.getJsonKeyValue("exclude_category_ids_bcom", jsonKeyValueFileName).get("Single Category ID").toString();
                }else {
                    excludedCategoryId = CommonUtils.getJsonKeyValue("exclude_category_ids_mcom", jsonKeyValueFileName).get("Single Category ID").toString();
                }
                break;
            case "Multiple Category IDs separated by Comma":
                if (CommonUtils.bcom()) {
                    excludedCategoryId = CommonUtils.getJsonKeyValue("exclude_category_ids_bcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Comma").toString();
                } else {
                    excludedCategoryId = CommonUtils.getJsonKeyValue("exclude_category_ids_mcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Comma").toString();
                }
                break;
            case "Multiple Category IDs separated by Space":
                if (CommonUtils.bcom()) {
                    excludedCategoryId = CommonUtils.getJsonKeyValue("exclude_category_ids_bcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Space").toString();
                } else {
                    excludedCategoryId = CommonUtils.getJsonKeyValue("exclude_category_ids_mcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Space").toString();
                }
                break;
        }
        TextBoxes.typeTextbox("advanced_search.exclude_category_ids", excludedCategoryId);
        excludedCategoryId = Elements.findElement("advanced_search.exclude_category_ids").getAttribute("value");
    }

    @Then("^I confirm alert message \"([^\"]*)\"$")
    public void i_confirm_alert_message(String expectedAlertMessage) throws Throwable {
        String alertMessage = Elements.findElement(By.xpath(CommonUtils.getJsonValue("error_message", jsonValueFileName))).getText();
        Assert.assertEquals("The alert message doesn't match", expectedAlertMessage, alertMessage);
    }

    @When("^I select Manage Facets operator \"([^\"]*)\" on Advanced Search Page$")
    public void i_select_Manage_Facets_operator_on_Advanced_Search_Page(String manageFacetsOperatorType) throws Throwable {
        Wait.ajaxDone();
        DropDowns.selectByText("advanced_search.manage_facet_operator_type", manageFacetsOperatorType);
    }

    @When("^I select Modify Result Set operator \"([^\"]*)\" on Advanced Search Page$")
    public void i_select_Modify_result_set_operator_on_Advanced_Search_Page(String modifyResultSetOperatorType) throws Throwable {
        DropDowns.selectByText("advanced_search.modify_result_set_operator", modifyResultSetOperatorType);
    }

    @When("^I press \"([^\"]*)\" in Advanced Search Page$")
    public void i_press_in_Advanced_Search_Page(String buttonType) throws Throwable {
        switch (buttonType) {
            case "Submit":
                Clicks.click("advanced_search.submit");
                break;
            case "Clear":
                Clicks.click("advanced_search.clear");
                break;
            case "Export":
                Clicks.click("advanced_search.export");
                break;
        }
        Wait.forPageReady();
    }

    @Then("^I see Result Count Trigger types with default selection as \"([^\"]*)\" on Advanced Search page$")
    public void i_see_Result_Count_Trigger_types_with_default_selection_as_on_Advanced_Search_page(String defaultSelection, DataTable data) throws Throwable {
        List<String> resultCountTriggerTypes = DropDowns.getAllValues("advanced_search.result_count_type");
        Assert.assertEquals("The Result Count Trigger Types are not sorted in alphanumeric order", data.asList(String.class), resultCountTriggerTypes);
        Assert.assertEquals("Greater Than is not selected by default in Result Count Trigger Types dropdown", DropDowns.getSelectedValue(By.id("resultCountDropdown")), defaultSelection);
    }

    @When("^I enter \"([^\"]*)\" for Result Count trigger with \"([^\"]*)\" on Advanced Search Page$")
    public void i_enter_for_Result_Count_trigger_with_on_Advanced_Search_Page(String resultCountDataType, String resultCountType) throws Throwable {
        DropDowns.selectByText("advanced_search.result_count_type", resultCountType);
        switch(resultCountDataType) {
            case "non-numeric":
                resultCountInput = "abc123";
                break;
            case "decimal":
                resultCountInput = "0.23 ";
                break;
            case "empty_value":
                resultCountInput = "";
                break;
            case "valid_results_count_value":
                resultCountInput = "1";
                break;
        }
        TextBoxes.typeTextbox("advanced_search.result_count_value", resultCountInput);
    }

    @When("^I select Navigation type \"([^\"]*)\" on Advanced Search Page$")
    public void i_select_Navigation_type_on_Advanced_Search_Page(String navigationType) throws Throwable {
        switch(navigationType) {
            case "Browse":
                Clicks.click("advanced_search.browse");
                break;
            case "Search":
                Clicks.click("advanced_search.search");
                break;
            case "Landing":
                Clicks.click("advanced_search.landing");
                break;
        }
    }

    @When("^I enter \"([^\"]*)\" in the URL entry field$")
    public void i_enter_in_the_URL_entry_field(String urlValueType) throws Throwable {
        switch(urlValueType) {
            case "Entire URL":
                if (CommonUtils.bcom()) {
                    urlInputType = CommonUtils.getJsonKeyValue("url_redirect_bcom", jsonKeyValueFileName).get("Entire URL").toString();
                }else {
                    urlInputType = CommonUtils.getJsonKeyValue("url_redirect_mcom", jsonKeyValueFileName).get("Entire URL").toString();
                }
                break;
            case "Single term from URL":
                if (CommonUtils.bcom()) {
                    urlInputType = CommonUtils.getJsonKeyValue("url_redirect_bcom", jsonKeyValueFileName).get("Single term from URL").toString();
                }else {
                    urlInputType = CommonUtils.getJsonKeyValue("url_redirect_mcom", jsonKeyValueFileName).get("Single term from URL").toString();
                }
                break;
            case "Multiple terms from URL":
                if (CommonUtils.bcom()) {
                    urlInputType = CommonUtils.getJsonKeyValue("url_redirect_bcom", jsonKeyValueFileName).get("Multiple terms from URL").toString();
                }else {
                    urlInputType = CommonUtils.getJsonKeyValue("url_redirect_mcom", jsonKeyValueFileName).get("Multiple terms from URL").toString();
                }
                break;
        }
        TextBoxes.typeTextbox("advanced_search.url", urlInputType);
    }

    @Then("^I see all search selections cleared$")
    public void i_see_all_search_selections_cleared() throws Throwable {
        Assert.assertEquals("Trigger is selected by default", DropDowns.getSelectedValue(By.id("triggerTypesMenu")), "");
        Assert.assertEquals("Action selected by default", DropDowns.getSelectedValue(By.id("actionTypesMenu")), "");
        Assert.assertTrue("Context - Contains is not selected by default", Elements.findElement("advanced_search.context_contains").isSelected());
        Assert.assertFalse("Context - Exact Match is not deselected by default", Elements.findElement("advanced_search.context_exact_match").isSelected());
        logger.info("All Advanced Search criteria has been cleared");
    }

    @When("^I enter \"([^\"]*)\" in Message Displayed textarea$")
    public void i_enter_in_Message_Displayed_textarea(String messageType) throws Throwable {
        switch(messageType) {
            case "entire message":
                if (CommonUtils.bcom()) {
                    messageType = CommonUtils.getJsonKeyValue("display_message_bcom", jsonKeyValueFileName).get("Entire Message").toString();
                }else {
                    messageType = CommonUtils.getJsonKeyValue("display_message_mcom", jsonKeyValueFileName).get("Entire Message").toString();
                }
                break;
            case "single term":
                if (CommonUtils.bcom()) {
                    messageType = CommonUtils.getJsonKeyValue("display_message_bcom", jsonKeyValueFileName).get("Partial Message").toString();
                }else {
                    messageType = CommonUtils.getJsonKeyValue("display_message_mcom", jsonKeyValueFileName).get("Partial Message").toString();
                }
                TextBoxes.typeTextbox("advanced_search.message_displayed", "Michael Kors");
                break;
        }
        TextBoxes.typeTextbox("advanced_search.message_displayed", messageType);
        displayMessageText = Elements.findElement("advanced_search.message_displayed").getAttribute("value");
    }

    @Then("^I see all matching rules displayed on UI$")
    public void i_see_all_matching_rules_displayed_on_UI() throws Throwable {
        totalNumberOfRulesUI = Elements.findElement(By.id(CommonUtils.getJsonValue("total_number_of_rules", jsonValueFileName))).getText();
    }

    @Then("^I confirm trigger search results without sub criteria from Advanced Search page match with database data$")
    public void i_confirm_trigger_search_results_without_sub_criteria_from_Advanced_Search_page_match_with_database_data() throws Throwable {
        query = "SELECT count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID \n" +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER Join merch_trig_type mtt on mt.MERCH_TRIG_TYPE_ID = mtt.MERCH_TRIG_TYPE_ID\n" +
                "where mtt.MERCH_TRIG_TYPE_NAME = " + " '" + selectedTriggerType +"' " + " and mt.SITE_TENANT=" + " '" + siteTenant +"' " + "  AND mt.MERCH_TRIG_ID is not null" ;

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm action search results without sub criteria from Advanced Search page match with database data$")
    public void i_confirm_action_search_results_without_sub_criteria_from_Advanced_Search_page_match_with_database_data() throws Throwable {
        query = "SELECT count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID \n" +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id left join MERCH_RULE_ACTION_ASSN mraa on mr.MERCH_RULE_ID = mraa.MERCH_RULE_ID \n" +
                "left join MERCH_ACTION ma on ma.MERCH_ACTION_ID = mraa.MERCH_ACTION_ID left join merch_action_type mat on ma.merch_action_type_id = mat.merch_action_type_id \n" +
                "where mat.merch_action_type_name = " + " '" + selectedActionType +"' " + " and ma.SITE_TENANT=" + " '" + siteTenant +"' " + "  AND mt.MERCH_TRIG_ID is not null" ;

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedActionType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedActionType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I see \"([^\"]*)\" operator type All is selected by default$")
    public void i_see_operator_type_All_is_selected_by_default(String actionType) throws Throwable {
        switch(actionType) {
            case "Modify Result Set":
                Assert.assertEquals("Modify Results Set Operator - All is not selected by default", DropDowns.getSelectedValue(By.name(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName))).toString(), "All");
                break;
            case "Manage Facets":
                Wait.ajaxDone();
                Wait.forPageReady();
                Assert.assertEquals("Manage Facet Operator - All is not selected by default", DropDowns.getSelectedValue(By.xpath(CommonUtils.getJsonValue("manage_facet_operator_type", jsonValueFileName))).toString(), "All");
                break;
            case "Manage Featured Facet":
                Assert.assertEquals("Manage Featured Facet Operator - All is not selected by default", DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("manage_featured_facet_operator", jsonValueFileName))).toString(), "All");
                break;
        }
    }

    @Then("^I see \"([^\"]*)\" operator type values in alphanumeric order$")
    public void i_see_operator_type_values_in_alphanumeric_order(String actionType, DataTable data) throws Throwable {
        switch(actionType) {
            case "Modify Result Set":
                List<String> modifyResultSetOperatorTypes = DropDowns.getAllValues(By.name(CommonUtils.getJsonValue("modify_result_set_operator", jsonValueFileName)));
                modifyResultSetOperatorTypes.remove(0);
                Assert.assertEquals("Modify Results Set Operator Types are not in alphanumeric order", CommonUtils.getSortedList(modifyResultSetOperatorTypes), data.asList(String.class));
                break;
            case "Manage Facets":
                List<String> manageFaetOperatorTypes = DropDowns.getAllValues(By.xpath(CommonUtils.getJsonValue("manage_facet_operator_type", jsonValueFileName)));
                Assert.assertEquals("Manage Facet Operator Types are not in alphanumeric order", CommonUtils.getSortedList(manageFaetOperatorTypes), data.asList(String.class));
                break;
            case "Manage Featured Facet":
                List<String> manageFeaturedFacetOperatorTypes = DropDowns.getAllValues(By.id(CommonUtils.getJsonValue("manage_featured_facet_operator", jsonValueFileName)));
                manageFeaturedFacetOperatorTypes.remove(0);
                Assert.assertEquals("Manage Featured Facet Operator are not in alphanumeric order", CommonUtils.getSortedList(manageFeaturedFacetOperatorTypes), data.asList(String.class));
                break;
        }
    }

    @When("^I enter Term in Replace with Term entry field$")
    public void i_enter_Term_in_Replace_with_Term_entry_field() throws Throwable {
        TextBoxes.typeTextbox("advanced_search.replace_with_term", "red");
        executeSearchReplaceTerm = Elements.findElement("advanced_search.replace_with_term").getAttribute("value");
    }

    @When("^I select \"([^\"]*)\" from Manage Featured Facet operator type drop down$")
    public void i_select_from_Manage_Featured_Facet_operator_type_drop_down(String manageFeaturedFacetOperatorType) throws Throwable {
        DropDowns.selectByText("advanced_search.manage_featured_facet_operator", manageFeaturedFacetOperatorType);
    }

    @When("^I enter Product ID in PDP entry field$")
    public void i_enter_Product_ID_in_PDP_entry_field() throws Throwable {
        pdpId = CommonUtils.mcom() ? "966328" : "1732131";
        TextBoxes.typeTextbox("advanced_search.pdp_redirect_pdp_id", pdpId);
    }

    @When("^I enter Category ID in Category Id entry field$")
    public void i_enter_Category_ID_in_Category_Id_entry_field() throws Throwable {
        if (CommonUtils.bcom()) {
            categoryId = CommonUtils.getJsonKeyValue("category_redirect_bcom", jsonKeyValueFileName).get("Category Id").toString();
        }else {
            categoryId = CommonUtils.getJsonKeyValue("category_redirect_mcom", jsonKeyValueFileName).get("Category Id").toString();
        }
        TextBoxes.typeTextbox("advanced_search.category_id", categoryId);
        categoryId = Elements.findElement("advanced_search.category_id").getAttribute("value");
    }

    @Then("^I confirm Category ID trigger with \"([^\"]*)\" and Included Category Ids search results from Advanced Search page match DB$")
    public void i_confirm_Category_ID_trigger_with_and_Included_Category_Ids_search_results_from_Advanced_Search_page_match_DB(String categoryInputType) throws Throwable {
        switch (categoryInputType) {
            case "Single Category ID":
                query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                        "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                        "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                        "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + includedCategoryId + "' " + " " +
                        " and frgv.EXCLUDE_FLAG='N' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null";
                break;
            case "Multiple Category IDs separated by Comma":
            case "Multiple Category IDs separated by Space":
                if (includedCategoryId.contains(",")) {
                    includedCategoryId_1 = includedCategoryId.split(",")[0];
                    includedCategoryId_2 = includedCategoryId.split(",")[1];
                } else if (includedCategoryId.contains(" ")) {
                    includedCategoryId_1 = includedCategoryId.split(" ")[0];
                    includedCategoryId_2 = includedCategoryId.split(" ")[1];
                }
                query = "select count(*) from (select distinct mr.MERCH_RULE_ID from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                       "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                       "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                       "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + includedCategoryId_1 + "' " + "  " +
                       " and frgv.EXCLUDE_FLAG='N' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null " +
                       "INTERSECT select distinct mr.MERCH_RULE_ID from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                       "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                       "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                       "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + includedCategoryId_2 + "' " + " " +
                       "and frgv.EXCLUDE_FLAG='N' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null)";
                break;
        }

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " Trigger with " + categoryInputType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " Trigger with " + categoryInputType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm Category ID trigger with \"([^\"]*)\" and Excluded Category Ids search results from Advanced Search page match DB$")
    public void i_confirm_Category_ID_trigger_with_and_Excluded_Category_Ids_search_results_from_Advanced_Search_page_match_DB(String categoryInputType) throws Throwable {
        switch (categoryInputType) {
            case "Single Category ID":
                query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                        "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                        "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                        "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + excludedCategoryId +"' " + " " +
                        " and frgv.EXCLUDE_FLAG='Y' and mr.site_tenant=" + " '" + siteTenant +"' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null" ;
                break;
            case "Multiple Category IDs separated by Comma":
            case "Multiple Category IDs separated by Space":
                if (excludedCategoryId.contains(",")) {
                    excludedCategoryId_1 = excludedCategoryId.split(",")[0];
                    excludedCategoryId_2 = excludedCategoryId.split(",")[1];
                } else if (excludedCategoryId.contains(" ")) {
                    excludedCategoryId_1 = excludedCategoryId.split(" ")[0];
                    excludedCategoryId_2 = excludedCategoryId.split(" ")[1];
                }
                query = "select count(*) from (select distinct mr.MERCH_RULE_ID from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                        "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                        "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                        "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + excludedCategoryId_1 + "' " + " " +
                        " and frgv.EXCLUDE_FLAG='Y' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null " +
                        "INTERSECT select distinct mr.MERCH_RULE_ID from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                        "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                        "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                        "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + excludedCategoryId_2 + "' " + " " +
                        "and frgv.EXCLUDE_FLAG='Y' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null)";
                break;
        }

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " Trigger with " + categoryInputType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " Trigger with " + categoryInputType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm Category ID trigger with Included and Excluded Category Ids search results from Advanced Search page match DB$")
    public void i_confirm_Category_ID_trigger_with_Included_and_Excluded_Category_Ids_search_results_from_Advanced_Search_page_match_DB() throws Throwable {
        query = "select count(*) from (select distinct mr.MERCH_RULE_ID from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + includedCategoryId + "' " + "  " +
                " and frgv.EXCLUDE_FLAG='N' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null " +
                "INTERSECT select distinct mr.MERCH_RULE_ID from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id INNER JOIN MERCH_TRIG_TYPE mtt on mtt.MERCH_TRIG_TYPE_ID = mt.MERCH_TRIG_TYPE_ID " +
                "INNER JOIN facet_refine_trig frt ON mrta.merch_trig_id=frt.merch_trig_id INNER JOIN facet_refine_group frg ON frt.facet_refine_trig_id=frg.facet_refine_trig_id " +
                "INNER JOIN FACET_REFINE_GROUP_VALUE frgv on frg.FACET_REFINE_GROUP_ID = frgv.FACET_REFINE_GROUP_ID where frgv.data_value=" + " '" + excludedCategoryId + "' " + " " +
                "and frgv.EXCLUDE_FLAG='Y' and mr.site_tenant=" + " '" + siteTenant + "' " + " AND dest_attribute_name='CAT_ID' AND mt.MERCH_TRIG_ID is not null)";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " Trigger with Included and Excluded Category Ids rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " Trigger with Included and Excluded Category Ids rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm Result Count trigger with \"([^\"]*)\" and \"([^\"]*)\" match with database data$")
    public void i_confirm_Result_Count_trigger_with_and_match_with_database_data(String resultCountType, String valueType) throws Throwable {
        switch (resultCountType) {
            case "Greater Than":
                resultCountOperatorType = ">";
                break;
            case "Less Than":
                resultCountOperatorType = "<";
                break;
        }
        switch (valueType) {
            case "valid_results_count_value":
                query = "SELECT count(distinct mr.MERCH_RULE_ID) FROM MERCH_RULE mr LEFT JOIN MERCH_RULE_TRIGGER_ASSN mtaa ON mr.MERCH_RULE_ID = mtaa.MERCH_RULE_ID " +
                        "LEFT JOIN MERCH_TRIG mt ON mt.MERCH_TRIG_ID = mtaa.MERCH_TRIG_ID LEFT JOIN (SELECT rctrig.RESULT_COUNT_TRIG_ID, " +
                        "rctrig.MERCH_TRIG_ID, rctrig.COMPARISON_OPERATOR COMPARISON_OP, rctrig.COUNT_VALUE FROM RESULT_COUNT_TRIG rctrig ) " +
                        "rct ON (rct.MERCH_TRIG_ID = mt.MERCH_TRIG_ID) WHERE mt.MERCH_TRIG_ID IS NOT NULL and count_value = " + " '" + resultCountInput + "' " + " " +
                        "and mr.site_tenant=" + " '" + siteTenant + "' " + " AND COMPARISON_OP= " + " '" + resultCountOperatorType + "' " + " ";
                break;
            case "without_value":
                query = "SELECT count(distinct mr.MERCH_RULE_ID) FROM MERCH_RULE mr LEFT JOIN MERCH_RULE_TRIGGER_ASSN mtaa ON mr.MERCH_RULE_ID = mtaa.MERCH_RULE_ID " +
                        "LEFT JOIN MERCH_TRIG mt ON mt.MERCH_TRIG_ID = mtaa.MERCH_TRIG_ID LEFT JOIN RESULT_COUNT_TRIG rct ON rct.MERCH_TRIG_ID = mt.MERCH_TRIG_ID " +
                        "WHERE mt.MERCH_TRIG_ID IS NOT NULL and mr.site_tenant=" + " '" + siteTenant + "' " + " AND rct.COMPARISON_OPERATOR= " + " '" + resultCountOperatorType + "' " + " ";
                break;
        }

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " Trigger with " + resultCountType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " Trigger with " + resultCountType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }


    @Then("^I confirm Category Redirect action with sub criteria search results from Advanced Search page match with database data$")
    public void i_confirm_Category_Redirect_action_with_sub_criteria_search_results_from_Advanced_Search_page_match_with_database_data() throws Throwable {
        query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id left join MERCH_RULE_ACTION_ASSN mraa on mr.MERCH_RULE_ID = mraa.MERCH_RULE_ID " +
                "left join MERCH_ACTION ma on ma.MERCH_ACTION_ID = mraa.MERCH_ACTION_ID left join category_redirect_action cra on cra.MERCH_ACTION_ID =  ma.MERCH_ACTION_ID " +
                "left join category_redirect_facet crf on (crf.category_redirect_ACTION_ID = cra.category_redirect_ACTION_ID) " +
                "left join category_redirect_facet_value crfv on (crfv.category_redirect_facet_ID = crf.category_redirect_facet_ID) " +
                "where ma.merch_action_type_id=1 and cra.category_id= " + " '" +categoryId + "' " + "  "+
                "and ma.SITE_TENANT=" + " '" + siteTenant + "' " + " AND mt.MERCH_TRIG_ID is not null";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedActionType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedActionType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm Display Message action with \"([^\"]*)\" search results from Advanced Search page match with database data$")
    public void i_confirm_Display_Message_action_with_search_results_from_Advanced_Search_page_match_with_database_data(String messageType) throws Throwable {
        switch (messageType) {
            case "entire message":
                query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                        "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id left join MERCH_RULE_ACTION_ASSN mraa on mr.MERCH_RULE_ID = mraa.MERCH_RULE_ID " +
                        "left join MERCH_ACTION ma on ma.MERCH_ACTION_ID = mraa.MERCH_ACTION_ID left join DISPLAY_MESSAGE dm on dm.MERCH_ACTION_ID = ma.MERCH_ACTION_ID " +
                        "where ma.merch_action_type_id =8 and dm.display_message_text=" + " '" + displayMessageText + "' "+" " +
                        "and ma.SITE_TENANT=" + " '" + siteTenant + "' " + " AND mt.MERCH_TRIG_ID is not null";
                break;
            case "single term":
                query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                        "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id left join MERCH_RULE_ACTION_ASSN mraa on mr.MERCH_RULE_ID = mraa.MERCH_RULE_ID " +
                        "left join MERCH_ACTION ma on ma.MERCH_ACTION_ID = mraa.MERCH_ACTION_ID left join DISPLAY_MESSAGE dm on dm.MERCH_ACTION_ID = ma.MERCH_ACTION_ID " +
                        "where ma.merch_action_type_id =8 and dm.display_message_text like " + " '% " + displayMessageText + " %' " + " " +
                        "and ma.SITE_TENANT=" + " '" + siteTenant + "' " + " AND mt.MERCH_TRIG_ID is not null";
                break;
        }

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedActionType + " rules in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedActionType + " rules in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm Execute New Search action with sub criteria search results from Advanced Search page match with database data$")
    public void i_confirm_Execute_New_Search_action_with_sub_criteria_search_results_from_Advanced_Search_page_match_with_database_data() throws Throwable {
        query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id left join MERCH_RULE_ACTION_ASSN mraa on mr.MERCH_RULE_ID = mraa.MERCH_RULE_ID " +
                "left join MERCH_ACTION ma on ma.MERCH_ACTION_ID = mraa.MERCH_ACTION_ID LEFT join new_search_action nsa  on (ma.MERCH_ACTION_ID = nsa.MERCH_ACTION_ID) " +
                "LEFT join new_search_group nsg on (nsa.NEW_SEARCH_ACTION_ID = nsg.NEW_SEARCH_ACTION_ID) " +
                "LEFT join new_search_group_value nsgv on (nsgv.NEW_SEARCH_GROUP_ID = nsg.NEW_SEARCH_GROUP_ID) " +
                "where ma.merch_action_type_id=10 and nsa.REPLACE_TERM= " + " '" +executeSearchReplaceTerm + "' " + "  "+
                "and ma.SITE_TENANT=" + " '" + siteTenant + "' " + " AND mt.MERCH_TRIG_ID is not null";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedActionType + " rules with Replace term " + executeSearchReplaceTerm + " in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedActionType + " rules with " + executeSearchReplaceTerm + " in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm PDP Redirect action with Product ID search results from Advanced Search page match with database data$")
    public void i_confirm_PDP_Redirect_action_with_Product_ID_search_results_from_Advanced_Search_page_match_with_database_data() throws Throwable {
        query = "select count(distinct mr.MERCH_RULE_ID) from MERCH_RULE mr INNER JOIN merch_rule_trigger_assn mrta ON mr.MERCH_RULE_ID = mrta.MERCH_RULE_ID " +
                "INNER JOIN merch_trig mt ON mt.merch_trig_id=mrta.merch_trig_id left join MERCH_RULE_ACTION_ASSN mraa on mr.MERCH_RULE_ID = mraa.MERCH_RULE_ID " +
                "left join MERCH_ACTION ma on ma.MERCH_ACTION_ID = mraa.MERCH_ACTION_ID LEFT join PDP_REDIRECT_ACTION PRA on (ma.MERCH_ACTION_ID = PRA.MERCH_ACTION_ID) " +
                 "where ma.merch_action_type_id=2 and PRA.PRODUCT_ID= " + " '" +pdpId + "' " + " and ma.SITE_TENANT=" + " '" + siteTenant + "' " + " AND mt.MERCH_TRIG_ID is not null";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedActionType + " rules with PDP ID '" + pdpId + "' in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedActionType + " rules with PDP ID '" + pdpId + "' in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
   }

    @Then("^I see \"([^\"]*)\" displayed for Keyword Pattern Trigger on Advanced Search page$")
    public void i_see_displayed_for_Keyword_Pattern_Trigger_on_Advanced_Search_page(String matchType) throws Throwable {
        Assert.assertTrue("Keyword Pattern Trigger Match Type " + matchType + " is not displayed", Elements.findElement("advanced_search.keyword_pattern_match_type").getText().equals(matchType));
    }

    @When("^I click on \"([^\"]*)\" for Keyword Pattern Trigger on Advanced Search page$")
    public void i_click_on_for_Keyword_Pattern_Trigger_on_Advanced_Search_page(String arg1) throws Throwable {
        Elements.findElement("advanced_search.keyword_pattern_match_type").click();
    }

    @Then("^I see Keyword Pattern attribute value text field$")
    public void i_see_Keyword_Pattern_attribute_value_text_field() throws Throwable {
        Assert.assertTrue("Keyword Pattern attribute value text field is not visible", Elements.findElement("advanced_search.keyword_pattern_value").isDisplayed());
    }

    @When("^I select \"([^\"]*)\" from Keyword Pattern attributes dropdown$")
    public void i_select_from_Keyword_Pattern_attributes_dropdown(String keywordPatternAttributeType) throws Throwable {
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("keyword_pattern_list", jsonValueFileName)), keywordPatternAttributeType);
        selectedKeywordPatternAttributeType = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("keyword_pattern_list", jsonValueFileName)));
    }

    @When("^I select Keyword Pattern trigger match type as \"([^\"]*)\"$")
    public void i_select_Keyword_Pattern_trigger_match_type_as(String matchType) throws Throwable {
        switch(matchType) {
            case "Has EXACT Match to":
                Assert.assertTrue("Keyword Pattern Match Type is not 'Has EXACT Match to' ", Elements.findElement("advanced_search.keyword_pattern_match_type").getText().equalsIgnoreCase(matchType));
                keywordPatternMatchTypeId = 1;
                break;
            case "Contains":
                Clicks.click("advanced_search.keyword_pattern_match_type");
                Assert.assertTrue("Keyword Pattern Match Type is not 'Contains'", Elements.findElement("advanced_search.keyword_pattern_match_type").getText().equalsIgnoreCase(matchType));
                keywordPatternMatchTypeId = 2;
                break;
        }
    }

    @When("^I enter \"([^\"]*)\" in Keyword Pattern attribute value text field$")
    public void i_enter_in_Keyword_Pattern_attribute_value_text_field(String attributeValue) throws Throwable {
        switch(selectedKeywordPatternAttributeType){
            case "Brand":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Brand value").toString();   break;
            case "Color":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Color value").toString();   break;
            case "Customer Service":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Customer Service value").toString();  break;
            case "Gender":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Gender value").toString();   break;
            case "Material":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Material value").toString();   break;
            case "Miscellaneous":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Miscellaneous value").toString();  break;
            case "Occasion":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Occasion value").toString();  break;
            case "Product Line":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Product Line value").toString();  break;
            case "Product Noun":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Product Noun value").toString();  break;
            case "Size":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Size value").toString();  break;
            case "Special Size":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Special Size value").toString();  break;
            case "Style":
                attributeValue = CommonUtils.getJsonKeyValue("keyword_pattern_attribute_value", jsonKeyValueFileName).get("Style value").toString();  break;
        }
        TextBoxes.typeTextNEnter("advanced_search.keyword_pattern_value", attributeValue);
        keywordPatternAttributeValue = attributeValue;
    }

    @Then("^I confirm Keyword Pattern trigger with \"([^\"]*)\" results match with database data$")
    public void i_confirm_Keyword_Pattern_trigger_with_results_match_with_database_data(String attributeType) throws Throwable {
        selectedKeywordPatternAttributeType = attributeType;
        query = "select count(distinct mr.merch_rule_id)from MERCH_RULE mr join MERCH_RULE_TRIGGER_ASSN mrta on (mrta.MERCH_RULE_ID = mr.MERCH_RULE_ID) " +
                "join MERCH_TRIG mt on (mt.MERCH_TRIG_ID = mrta.MERCH_TRIG_ID) join KEYWORD_TRIG kt on (mt.MERCH_TRIG_ID= kt.MERCH_TRIG_ID) " +
                "join KEYWORD_GROUP kg on (kg.KEYWORD_TRIG_ID= kt.KEYWORD_TRIG_ID) join KEYWORD_GROUP_TYPE kgt on (kg.KEYWORD_GROUP_TYPE_ID= kgt.KEYWORD_GROUP_TYPE_ID) " +
                "join Keyword_group_term kgterm on(kgterm.KEYWORD_GROUP_ID=kg.KEYWORD_GROUP_ID) join LINGUISTIC_TERM lterm on (lterm.linguistic_term_id=kgterm.linguistic_term_id) " +
                "where mt.SITE_TENANT=" + " '" + siteTenant + "' " + " and mt.merch_trig_type_id =3 and mt.MERCH_TRIG_ID IS NOT NULL  and mr.STATUS_CD='LIVE'" +
                "and kgt.KEYWORD_GROUP_TYPE_NAME = " + " '" +selectedKeywordPatternAttributeType + "' " + " ";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " rules with '" + selectedKeywordPatternAttributeType + "' in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " rules with '" + selectedKeywordPatternAttributeType + "' in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm Keyword Pattern trigger with \"([^\"]*)\" and \"([^\"]*)\" results match with database data$")
    public void i_confirm_Keyword_Pattern_trigger_with_and_results_match_with_database_data(String attributeType, String attributeValue) throws Throwable {
        selectedKeywordPatternAttributeType = attributeType;

        query = "select count(distinct mr.merch_rule_id)from MERCH_RULE mr join MERCH_RULE_TRIGGER_ASSN mrta on (mrta.MERCH_RULE_ID = mr.MERCH_RULE_ID) " +
                "join MERCH_TRIG mt on (mt.MERCH_TRIG_ID = mrta.MERCH_TRIG_ID) join KEYWORD_TRIG kt on (mt.MERCH_TRIG_ID= kt.MERCH_TRIG_ID) " +
                "join KEYWORD_GROUP kg on (kg.KEYWORD_TRIG_ID= kt.KEYWORD_TRIG_ID) join KEYWORD_GROUP_TYPE kgt on (kg.KEYWORD_GROUP_TYPE_ID= kgt.KEYWORD_GROUP_TYPE_ID) " +
                "join Keyword_group_term kgterm on(kgterm.KEYWORD_GROUP_ID=kg.KEYWORD_GROUP_ID) join LINGUISTIC_TERM lterm on (lterm.linguistic_term_id=kgterm.linguistic_term_id) " +
                "where mt.SITE_TENANT=" + " '" + siteTenant + "' " + " and mt.merch_trig_type_id =3 and mt.MERCH_TRIG_ID IS NOT NULL  and mr.STATUS_CD='LIVE'" +
                "and kgt.KEYWORD_GROUP_TYPE_NAME = " + " '" +selectedKeywordPatternAttributeType + "' " + " and lterm.LINGUISTIC_TERM_VALUE =" + " '" +keywordPatternAttributeValue + "' " + " ";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " rules with '" + selectedKeywordPatternAttributeType + "' and '" +keywordPatternAttributeValue + "' in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " rules with '" + selectedKeywordPatternAttributeType + "' and '" +keywordPatternAttributeValue + "' in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I confirm the Keyword Pattern trigger with match type search results match with the DB data$")
    public void i_confirm_the_Keyword_Pattern_trigger_with_match_type_search_results_match_with_the_DB_data() throws Throwable {
        query = "select count(distinct mr.merch_rule_id)from MERCH_RULE mr join MERCH_RULE_TRIGGER_ASSN mrta on (mrta.MERCH_RULE_ID = mr.MERCH_RULE_ID) " +
                "join MERCH_TRIG mt on (mt.MERCH_TRIG_ID = mrta.MERCH_TRIG_ID) join KEYWORD_TRIG kt on (mt.MERCH_TRIG_ID= kt.MERCH_TRIG_ID) " +
                "join KEYWORD_GROUP kg on (kg.KEYWORD_TRIG_ID= kt.KEYWORD_TRIG_ID) join KEYWORD_GROUP_TYPE kgt on (kg.KEYWORD_GROUP_TYPE_ID= kgt.KEYWORD_GROUP_TYPE_ID) " +
                "join Keyword_group_term kgterm on(kgterm.KEYWORD_GROUP_ID=kg.KEYWORD_GROUP_ID) join LINGUISTIC_TERM lterm on (lterm.linguistic_term_id=kgterm.linguistic_term_id) " +
                "where mt.SITE_TENANT=" + " '" + siteTenant + "' " + " and mt.merch_trig_type_id =3 and mt.MERCH_TRIG_ID IS NOT NULL  and mr.STATUS_CD='LIVE'" +
                "and kgt.KEYWORD_GROUP_TYPE_NAME = " + " '" +selectedKeywordPatternAttributeType + "' " + "  " +
                "and lterm.LINGUISTIC_TERM_VALUE =" + " '" +keywordPatternAttributeValue + "' " + " and kt.MATCH_TYPE_id = " +keywordPatternMatchTypeId+" ";

        totalNumberOfRulesDB = CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword);
        Assert.assertEquals("Total number of " + selectedTriggerType + " rules with '" + selectedKeywordPatternAttributeType + "' and '" +keywordPatternAttributeValue + "' in DB did not match with the total number of rules on UI", totalNumberOfRulesUI, totalNumberOfRulesDB);
        System.out.println("Total number of " + selectedTriggerType + " rules with '" + selectedKeywordPatternAttributeType + "' and '" +keywordPatternAttributeValue + "' in DB:::: " + totalNumberOfRulesDB + "  >>>>>>> total number of rules on UI:::: " + totalNumberOfRulesUI);
    }

    @Then("^I see Total number of results with page navigation options$")
    public void i_see_Total_number_of_results_with_page_navigation_options() throws Throwable {
        Assert.assertTrue("Pagination 'First' is not disabled", Elements.findElement("advanced_search.first").getAttribute("class").toString().contains("Disabled"));
        Assert.assertTrue("Pagination 'Prev' is not disabled", Elements.findElement("advanced_search.previous").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Next' is not enabled", Elements.findElement("advanced_search.next").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Last' is not enabled", Elements.findElement("advanced_search.last").getAttribute("class").toString().contains("Disabled"));
        Clicks.click("advanced_search.next");
        Assert.assertFalse("Pagination 'First' is not enabled", Elements.findElement("advanced_search.first").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Prev' is not enabled", Elements.findElement("advanced_search.previous").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Next' is not enabled", Elements.findElement("advanced_search.next").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Last' is not enabled", Elements.findElement("advanced_search.last").getAttribute("class").toString().contains("Disabled"));
        Clicks.click("advanced_search.last");
        Assert.assertFalse("Pagination 'First' is not enabled", Elements.findElement("advanced_search.first").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Prev' is not enabled", Elements.findElement("advanced_search.previous").getAttribute("class").toString().contains("Disabled"));
        Assert.assertTrue("Pagination 'Next' is not disabled", Elements.findElement("advanced_search.next").getAttribute("class").toString().contains("Disabled"));
        Assert.assertTrue("Pagination 'Last' is not disabled", Elements.findElement("advanced_search.last").getAttribute("class").toString().contains("Disabled"));
        Clicks.click("advanced_search.first");
        Assert.assertTrue("Pagination 'First' is not disabled", Elements.findElement("advanced_search.first").getAttribute("class").toString().contains("Disabled"));
        Assert.assertTrue("Pagination 'Prev' is not disabled", Elements.findElement("advanced_search.previous").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Next' is not enabled", Elements.findElement("advanced_search.next").getAttribute("class").toString().contains("Disabled"));
        Assert.assertFalse("Pagination 'Last' is not enabled", Elements.findElement("advanced_search.last").getAttribute("class").toString().contains("Disabled"));
        //Show Entries should be 10 by default
        DropDowns.getSelectedValue((By.name(CommonUtils.getJsonValue("show_entries", jsonValueFileName))));
        Assert.assertEquals("The number of advanced Search results on the page is not 10", Integer.valueOf(DropDowns.getSelectedValue((By.name(CommonUtils.getJsonValue("show_entries", jsonValueFileName))))), Integer.valueOf(10));
    }

    @Then("^I confirm numeric entry pagination on Advanced Search page$")
    public void i_confirm_numeric_entry_pagination_on_Advanced_Search_page() throws Throwable {
        //  Numeric page entry field
        Assert.assertTrue("Page number entry field is not displayed", Elements.findElement("advanced_search.page_number_entry_field").isDisplayed());

//        use randomly generated page number and which is less than or equal to the total number of pages
//        confirm the page number entered in numeric entry field is same as the page number
        Random rand = new Random();
        for (int j = 0; j <= 5; j++) {
            System.out.print(rand.nextInt());
            System.out.println();
        }
    }

//     to use with match type
//           selectedKeywordPatternAttributeType = attributeType;
//   query = "select count(distinct mr.merch_rule_id)from MERCH_RULE mr join MERCH_RULE_TRIGGER_ASSN mrta on (mrta.MERCH_RULE_ID = mr.MERCH_RULE_ID) " +
//            "join MERCH_TRIG mt on (mt.MERCH_TRIG_ID = mrta.MERCH_TRIG_ID) join KEYWORD_TRIG kt on (mt.MERCH_TRIG_ID= kt.MERCH_TRIG_ID) " +
//            "join KEYWORD_GROUP kg on (kg.KEYWORD_TRIG_ID= kt.KEYWORD_TRIG_ID) join KEYWORD_GROUP_TYPE kgt on (kg.KEYWORD_GROUP_TYPE_ID= kgt.KEYWORD_GROUP_TYPE_ID) " +
//            "join Keyword_group_term kgterm on(kgterm.KEYWORD_GROUP_ID=kg.KEYWORD_GROUP_ID) join LINGUISTIC_TERM lterm on (lterm.linguistic_term_id=kgterm.linguistic_term_id) " +
//            "where mt.SITE_TENANT=" + " '" + siteTenant + "' " + " and mt.merch_trig_type_id =3 and mt.MERCH_TRIG_ID IS NOT NULL and mr.STATUS_CD='LIVE' " +
//            "and kgt.KEYWORD_GROUP_TYPE_NAME = " + " '" +selectedKeywordPatternAttributeType + "' " + " and kt.MATCH_TYPE_id = "  +keywordPatternMatchTypeId+ "  ";



}

