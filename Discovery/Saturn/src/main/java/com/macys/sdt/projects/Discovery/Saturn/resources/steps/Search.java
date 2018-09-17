package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Search {

    LinkedHashMap criteriaValueMap;
    int searchResultsCount;
    String searchResults;
    String simpleSearchValue;
    String fob;
    String searchFilterValue;
    String selectedSimpleSearchOption;
    String createdDate;
    String expiryDate;
    String publishValue;
    String query;
    int searchResultsCountDB;
    String categoryId_1;
    String categoryId_2;
    String ruleId_1;
    String ruleId_2;
    String siteTenant = CommonUtils.getTenantName();;
    String jsonValueFileName="search_common";
    String jsonKeyValueFileName="search_criteria_value";

    private static String dbName = "oracle";
    //  Staging database details
    private static String dbUrl = "jdbc:oracle:thin:@dml1-scan:1521/dpmsts01";
    private static String dbUsername = "Saturn";
    private static String dbPassword = "Saturn";
    String ruleName;
    //  CELL 2 database details
//    private static String dbUrl = "jdbc:oracle:thin:@mdc2vr040:1521/starsdev";
//    private static String dbUsername = "saturn";
//    private static String dbPassword = "saturn";


    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Utils.class);
    List<WebElement> creatorSearchResult = new ArrayList<WebElement>();

    @And("^I select Find Rules$")
    public void iSelectFindRules() throws Throwable {
        Wait.untilElementPresent(By.linkText("rules.find_rule"));
        Clicks.click("rules.find_rule");
    }

    @Then("^I should see the below options in the filter type dropdown$")
    public void iShouldSeeTheBelowOptionsInTheFilterTypeDropdown(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        List<String> filterOptions = DropDowns.getAllValues("search.filter_type");
        Assert.assertTrue("The list of Filters does'nt match", filterOptions.containsAll(data.asList(String.class)));
    }

    @And("^I should see the below options in the view dropdown$")
    public void iShouldSeeTheBelowOptionsInTheViewDropdown(DataTable data) throws Throwable {
        List<List<String>> listData = data.raw();
        List<String> filterNavOptions = DropDowns.getAllValues("search.filter_nav_type");
        Assert.assertTrue("The list of Filters nav does'nt match", filterNavOptions.containsAll(data.asList(String.class)));
    }

    @When("^I select the filter as \"([^\"]*)\"$")
    public void iSelectTheFilterAs(String filter) throws Throwable {
        DropDowns.selectByText("search.filter_type", filter);
    }

    @And("^I click Go for Simple Search$")
    public void iClickGoForSimpleSearch() throws Throwable {
        Clicks.javascriptClick("search.go_button");
    }

    @And("^I select the nav type as \"([^\"]*)\"$")
    public void iSelectTheNavTypeAs(String navType) throws Throwable {
        DropDowns.selectByText("search.filter_nav_type", navType);
    }

    @And("^I should see the search results with same \"([^\"]*)\"$")
    public void iShouldSeeTheSearchResultsWithSame(String filterValue) throws Throwable {
        int count = 0;
        if (filterValue.equals("Description")||filterValue.equals("Trigger Group Name For Group")) {
            searchResults = Elements.findElement("search.group_search_results_count").getText();
        } else {
            searchResults = Elements.findElement("search.rules_search_results_count").getText();
        }
        searchResultsCount = Integer.valueOf(searchResults.split(" ")[4]);

        criteriaValueMap = CommonUtils.getJsonKeyValue("filter_key_values",jsonKeyValueFileName);
        System.out.println("criteriaValueMap" + criteriaValueMap.get(filterValue).toString());

        if (searchResultsCount > 0) {
            creatorSearchResult = Elements.findElements("search." + criteriaValueMap.get(filterValue).toString());
            for (WebElement element : creatorSearchResult) {
                switch (filterValue) {

                    case "Created Date":
                    case "Modified Date":
                    case "Effective Date":
                        Assert.assertEquals("The search result at row" + count + "doesn't contain the created date", element.getText(), createdDate);
                        count++;
                        break;

                    case "Expiry Date":
                        Assert.assertEquals("The search result at row" + count + "doesn't contain the created date", element.getText(), expiryDate);
                        count++;
                        break;

                    case "FOB":
                        Assert.assertEquals("The search result at row" + count + "doesn't contain the FOB", element.getText(), fob);
                        count++;
                        break;

                    case "Modifier":
                    case "Creator":
                        Assert.assertEquals("The search result at row" + count + "doesn't contain the creator/,modifier", element.getText(), searchFilterValue);
                        count++;
                        break;

                    case "Rule Name":
                    case "Trigger Group Name":
                    case "Description":
                    case "Trigger Group Name for Group":
                        Assert.assertTrue("The Rule Name search results doesn't match the entered rule name value" + count, element.getText().contains(searchFilterValue));
                        count++;
                        break;

                    case "Publish Filter":
                        Assert.assertEquals("The Publish filter doesn't match the selected filter" + count, element.getText(), searchFilterValue);
                        count++;
                        break;

                    default:
                        logger.info("invalid filter criteria");
                }
            }
        } else {
            logger.info("No search results found");
        }
    }

    @And("^I enter the effective and expiry dates$")
    public void iEnterTheEffectiveAndExpiryDates() throws Throwable {
        createdDate = CommonUtils.getDate("date");
        expiryDate = CommonUtils.getDate("expiration date");
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("filter_value",jsonValueFileName)), createdDate);
        TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("filter_value",jsonValueFileName) + "2"), expiryDate);
    }

    @And("^I select FOB value$")
    public void iSelectFOBValue() throws Throwable {
        DropDowns.selectRandomValue(By.id(CommonUtils.getJsonValue("select_fob",jsonValueFileName)));
        fob = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("select_fob",jsonValueFileName)));
    }

    @And("^I enter the \"([^\"]*)\" date$")
    public void iEnterTheDate(String datetype) throws Throwable {
        createdDate = CommonUtils.getDate("date");
        Navigate.execJavascript("document.getElementById('filterRuleVal').value=" + "'" + createdDate + "'");
    }

    @Then("^I should be on the find rules page$")
    public void iShouldBeOnTheFindRulesPage() throws Throwable {
        Wait.ajaxDone();
        Assert.assertTrue("Find rules page did not open", Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_rules_page",jsonValueFileName))).isDisplayed());
    }

    @And("^I enter the \"([^\"]*)\" search value$")
    public void iEnterTheSearchValue(String searchValue) throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("filter_search_value",jsonKeyValueFileName);
        searchFilterValue = criteriaValueMap.get(searchValue).toString();
        TextBoxes.typeTextNEnter(By.id(CommonUtils.getJsonValue("filter_value",jsonValueFileName)), searchFilterValue);
    }

    @And("^I click on \"([^\"]*)\" on find rules page$")
    public void iClickOnOnFindRulesPage(String filterVal) throws Throwable {
        criteriaValueMap = CommonUtils.getJsonKeyValue("filter_search_value",jsonKeyValueFileName);
        searchFilterValue = criteriaValueMap.get(filterVal).toString();
        publishValue = Elements.findElement(By.id(CommonUtils.getJsonValue("published_filter",jsonValueFileName))).getText();
        if (publishValue.equals("Off")) {
            Clicks.click("rules.published_filter");
        }
    }

    @And("^I select Find Trigger Groups$")
    public void iSelectFindTriggerGroups() throws Throwable {
        Wait.untilElementPresent(By.linkText("rules.find_trigger_group"));
        Clicks.click("rules.find_trigger_group");
    }

    @Then("^I should be on the find trigger groups page$")
    public void iShouldBeOnTheFindTriggerGroupsPage() throws Throwable {
        Assert.assertTrue("Find trigger groups page did not open", Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_trigger_groups_page",jsonValueFileName))).isDisplayed());
    }

    @Then("^I should see global search tab on the home page$")
    public void iShouldSeeGlobalSearchTabOnTheHomePage() throws Throwable {
        Assert.assertTrue("The global search list dropdown is not displayed", Elements.findElement(By.id(CommonUtils.getJsonValue("global_search_dropdown",jsonValueFileName))).isDisplayed());
        Assert.assertTrue("The global search list dropdown is not displayed", Elements.findElement(By.xpath(CommonUtils.getJsonValue("global_search_input",jsonValueFileName))).isDisplayed());
    }

    @And("^I select the simple search option as \"([^\"]*)\"$")
    public void iSelectTheSimpleSearchOptionAs (String simpleSearchOption) throws Throwable {
        DropDowns.selectByText("home.search_list", simpleSearchOption);
        selectedSimpleSearchOption = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("global_search_dropdown",jsonValueFileName)));
    }

    @When("^I enter the \"([^\"]*)\" as a simple search value$")
    public void iEnterTheAsASimpleSearchValue (String searchValue) throws Throwable {
        simpleSearchValue = CommonUtils.getJsonKeyValue("simple_search_values", jsonKeyValueFileName).get(searchValue).toString();
        Wait.forPageReady();
        Wait.ajaxDone();
        TextBoxes.typeTextbox("home.search_field", simpleSearchValue);
    }

    @Then("^I should see that the \"([^\"]*)\" on the rules page is the same as entered$")
    public void iShouldSeeThatTheOnTheRulesPageIsTheSameAsEntered (String searchOption) throws Throwable {
        String valueEntered;

        switch (searchOption) {
            case "Rule ID":
                valueEntered = Elements.findElement("rules.rule_detail_id").getText().split(":")[1].replaceAll("\\s+", "");
                Assert.assertEquals("Rule Id on the rules page is not the same as entered", valueEntered, simpleSearchValue);

            case "Category ID":
                valueEntered = Elements.findElement("rules.category_search_id").getText();
                Assert.assertEquals("Category Id on the rules page is not the same as entered", valueEntered, simpleSearchValue);

            case "Rule Description":
                valueEntered = Elements.findElement("rule_detail.rule_description").getText().toString();
                Assert.assertTrue("Rule Description on the rules page is not the same as searched", valueEntered.toLowerCase().contains(simpleSearchValue));
                logger.info("The value used for Simple Search with Rule Description is: " + simpleSearchValue);            }
    }

    @And("^I click on first rule Id link on the search results page$")
    public void iClickOnFirstRuleIdLinkOnTheSearchResultsPage () throws Throwable {
        Clicks.click("search.rule_search_first");
    }


    @Then("^I should see the search results related to \"([^\"]*)\"$")
    public void iShouldSeeTheSearchResultsRelatedTo (String searchValue) throws Throwable {
        int count = 0;
        String enteredValue;

        searchResults = Elements.findElement("search." + CommonUtils.getJsonKeyValue("simple_search_key_values",jsonKeyValueFileName).get(searchValue)).getText();
        searchResultsCount = Integer.valueOf(searchResults.split(" ")[4]);
        criteriaValueMap = CommonUtils.getJsonKeyValue("filter_key_values",jsonKeyValueFileName);
        if (searchResultsCount > 0) {

            creatorSearchResult = Elements.findElements("search." + criteriaValueMap.get(searchValue).toString());
            enteredValue = CommonUtils.getJsonKeyValue("simple_search_values",jsonKeyValueFileName).get(searchValue).toString();

            for (WebElement element : creatorSearchResult) {

                switch (searchValue) {
                    case "Dictionary Name":
                        Assert.assertTrue("The search result at row" + count + "doesn't contain the dictionary name", element.getText().toLowerCase().contains(enteredValue));
                        count++;
                        break;

                    case "Rule Name":
                        Assert.assertTrue("The search result at row" + count + "doesn't contain the rule name", element.getText().toLowerCase().contains(enteredValue));
                        count++;
                        break;

                    case "Dictionary Term":
                        Assert.assertTrue("The search result at row" + count + "doesn't contain the dictionary term", element.getText().toLowerCase().contains(enteredValue));
                        count++;
                        break;

                    case "FOB":
                        Assert.assertEquals("The search result at row" + count + "doesn't contain the FOB", fob, element.getText());
                }
            }
        }
    }

    @And("^I click Go$")
    public void iClickGo () throws Throwable {
        Clicks.click("search.go_button");
    }

    @When("^I select FOB value for simple search$")
    public void iSelectFOBValueForSimpleSearch () throws Throwable {
        DropDowns.selectRandomValue(By.id(CommonUtils.getJsonValue("fob_simple_search",jsonValueFileName)));
        fob = DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("fob_simple_search",jsonValueFileName)));
    }

    @And("^I should see the default option selected as \"([^\"]*)\" in the global search list$")
    public void iShouldSeeTheDefaultOptionSelectedAsInTheGlobalSearchList(String defaultOption) throws Throwable {
        Assert.assertEquals("the default option is not Rule id", DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("global_search_dropdown",jsonValueFileName))), defaultOption);
    }

    @Then("^I should be navigated to rules page$")
    public void iShouldBeNavigatedToRulesPage() throws Throwable {
        Assert.assertTrue("Rule detail page did not open after simple search", Elements.findElement("rules.rule_status").isDisplayed());
    }

    @Then("^I see the Simple Search values in alphanumeric order$")
    public void i_see_the_Simple_Search_values_in_alphanumeric_order(DataTable data) throws Throwable {
        List<String> searchTypes = DropDowns.getAllValues("home.search_list");
        Assert.assertTrue("The list of Simple Search Types do not match", searchTypes.containsAll(data.asList(String.class)));
        Assert.assertEquals("The Simple Search Types are not sorted in alphanumeric order", CommonUtils.getSortedList(searchTypes), searchTypes);
    }

    @And("^I verify pagination on find rules page$")
    public void iVerifyPaginationnOnFindRulesPage() throws Throwable {
        searchResults = Elements.findElement("search.rules_search_results_count").getText();
        searchResultsCount = Integer.valueOf(searchResults.split(" ")[4]);
        //total results on one page should be 50
        Assert.assertEquals("The number of results on the page is not 50", Elements.findElement("search.rules_search_results_count").getText().split(" ")[2], "50");
        if (searchResultsCount >= 50) {
            Assert.assertTrue("Pagination not displayed", Elements.findElement("search.search_pagination").isDisplayed());
            Assert.assertFalse("Next is disabled", Elements.findElement("search.pagination_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_next_disabled", jsonValueFileName)));
            Assert.assertFalse("Last is disabled", Elements.findElement("search.pagination_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_last_disabled", jsonValueFileName)));
            Assert.assertEquals("First is enabled", Elements.findElement("search.pagination_first").getAttribute("class"), CommonUtils.getJsonValue("search_first_disabled", jsonValueFileName));
            Assert.assertEquals("Prev is enabled", Elements.findElement("search.pagination_previous").getAttribute("class"), CommonUtils.getJsonValue("search_previous_disabled", jsonValueFileName));
            Assert.assertEquals("First numeric is enabled", Elements.findElement("search.pagination_first_element").getAttribute("class"), CommonUtils.getJsonValue("first_pagination_element", jsonValueFileName));
            Clicks.click("search.pagination_next");
            if (searchResultsCount <= 100) {
                Assert.assertTrue("Next is enabled", Elements.findElement("search.pagination_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_next_disabled", jsonValueFileName)));
                Assert.assertTrue("Last is enabled", Elements.findElement("search.pagination_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_last_disabled", jsonValueFileName)));
            } else {
                Assert.assertTrue("Next is disabled", Elements.findElement("search.pagination_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_next_disabled", jsonValueFileName)));
                Assert.assertFalse("Last is disabled", Elements.findElement("search.pagination_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_last_disabled", jsonValueFileName)));
            }
        Assert.assertFalse("First is disabled", Elements.findElement("search.pagination_first").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_first_disabled", jsonValueFileName)));
        Assert.assertFalse("Prev is disabled", Elements.findElement("search.pagination_previous").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_previous_disabled", jsonValueFileName)));
        Assert.assertFalse("First numeric is disabled", Elements.findElement("search.pagination_first_element").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("first_pagination_element", jsonValueFileName)));
        if (searchResultsCount <= 100) {
            Assert.assertTrue("Next is enabled", Elements.findElement("search.pagination_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_next_disabled", jsonValueFileName)));
            Assert.assertTrue("Last is enabled", Elements.findElement("search.pagination_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_last_disabled", jsonValueFileName)));
        } else {
            Clicks.click("search.pagination_last");
            Assert.assertFalse("Next is enabled", Elements.findElement("search.pagination_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_next_disabled", jsonValueFileName)));
            Assert.assertFalse("Last is enabled", Elements.findElement("search.pagination_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_last_disabled", jsonValueFileName)));
        }
        Assert.assertFalse("First is disabled", Elements.findElement("search.pagination_first").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_first_disabled", jsonValueFileName)));
        Assert.assertFalse("Prev is disabled", Elements.findElement("search.pagination_previous").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_previous_disabled", jsonValueFileName)));
        Clicks.click("search.pagination_first");
        Assert.assertTrue("Pagination not displayed", Elements.findElement("search.search_pagination").isDisplayed());
        Assert.assertFalse("Next is disabled", Elements.findElement("search.pagination_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_next_disabled", jsonValueFileName)));
        Assert.assertFalse("Last is disabled", Elements.findElement("search.pagination_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("search_last_disabled", jsonValueFileName)));
        Assert.assertEquals("First is enabled", Elements.findElement("search.pagination_first").getAttribute("class"), CommonUtils.getJsonValue("search_first_disabled", jsonValueFileName));
        Assert.assertEquals("Prev is enabled", Elements.findElement("search.pagination_previous").getAttribute("class"), CommonUtils.getJsonValue("search_previous_disabled", jsonValueFileName));
        Assert.assertEquals("First numeric is enabled", Elements.findElement("search.pagination_first_element").getAttribute("class"), CommonUtils.getJsonValue("first_pagination_element", jsonValueFileName));
        }
    }

    @When("^I enter the \"([^\"]*)\" as \"([^\"]*)\" simple search value$")
    public void i_enter_the_as_simple_search_value(String searchValueType, String simpleSearchOptionType) throws Throwable {
        if (simpleSearchOptionType.equals("Category ID")) {
            switch (searchValueType) {
                case "Single Category ID":
                    if (CommonUtils.bcom()) {
                        simpleSearchValue = CommonUtils.getJsonKeyValue("category_ids_bcom", jsonKeyValueFileName).get("Single Category ID").toString();
                    } else {
                        simpleSearchValue = CommonUtils.getJsonKeyValue("category_ids_mcom", jsonKeyValueFileName).get("Single Category ID").toString();
                    }
                    break;
                case "Multiple Category IDs separated by Comma":
                    if (CommonUtils.bcom()) {
                        simpleSearchValue = CommonUtils.getJsonKeyValue("category_ids_bcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Comma").toString();
                    } else {
                        simpleSearchValue = CommonUtils.getJsonKeyValue("category_ids_mcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Comma").toString();
                    }
                    categoryId_1 = simpleSearchValue.split(",")[0];
                    categoryId_2 = simpleSearchValue.split(",")[1];
                    break;
                case "Multiple Category IDs separated by Space":
                    if (CommonUtils.bcom()) {
                        simpleSearchValue = CommonUtils.getJsonKeyValue("category_ids_bcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Space").toString();
                    } else {
                        simpleSearchValue = CommonUtils.getJsonKeyValue("category_ids_mcom", jsonKeyValueFileName).get("Multiple Category IDs separated by Space").toString();
                    }
                    categoryId_1 = simpleSearchValue.split(" ")[0];
                    categoryId_2 = simpleSearchValue.split(" ")[1];
                    break;
                case "Letters":
                    simpleSearchValue = CommonUtils.getJsonKeyValue("category_id_invalid_data", jsonKeyValueFileName).get("Letters").toString();
                    break;
                case "Special Characters":
                    simpleSearchValue = CommonUtils.getJsonKeyValue("category_id_invalid_data", jsonKeyValueFileName).get("Special Characters").toString();
                    break;
                case "Letters and Special Characters":
                    simpleSearchValue = CommonUtils.getJsonKeyValue("category_id_invalid_data", jsonKeyValueFileName).get("Letters and Special Characters").toString();
                    break;
            }
        } else if (simpleSearchOptionType.equals("Rule ID")) {
            query = "select merch_rule_id from merch_rule where site_tenant=" + " '" + siteTenant + "' " + " and ROWNUM <=3";
            ruleId_1 = ((CommonUtils.getRuleIdsFromDB(query, dbName, dbUrl, dbUsername, dbPassword)).get(0)).toString();
            ruleId_2 = ((CommonUtils.getRuleIdsFromDB(query, dbName, dbUrl, dbUsername, dbPassword)).get(1)).toString();

            switch(searchValueType) {
                case "Single Rule ID":
                    simpleSearchValue = ruleId_1;
                    break;
                case "Multiple Rule IDs separated by Comma":
                    simpleSearchValue = Integer.valueOf(ruleId_1) + "," +Integer.valueOf(ruleId_2)  ;
                    break;
                case "Multiple Rule IDs separated by Space":
                    simpleSearchValue = Integer.valueOf(ruleId_1) + " " +Integer.valueOf(ruleId_2);
                    break;
                case "Letters":
                    simpleSearchValue = CommonUtils.getJsonKeyValue("rule_id_invalid_data", jsonKeyValueFileName).get("Letters").toString();
                    break;
                case "Special Characters":
                    simpleSearchValue = CommonUtils.getJsonKeyValue("rule_id_invalid_data", jsonKeyValueFileName).get("Special Characters").toString();
                    break;
                case "Letters and Special Characters":
                    simpleSearchValue = CommonUtils.getJsonKeyValue("rule_id_invalid_data", jsonKeyValueFileName).get("Letters and Special Characters").toString();
                    break;
            }
        }
        TextBoxes.typeTextbox("home.search_field", simpleSearchValue);
    }

    @Then("^search results in Rules List view should display the Rules$")
    public void search_results_in_Rules_List_view_should_display_the_Rules() throws Throwable {
        if ((Elements.findElement("search.rule_search_first").getText()).equals(ruleId_1)) {
            Assert.assertEquals("The first Rule ID doesn't match", Elements.findElement("search.rule_search_first").getText(), ruleId_1);
            Assert.assertEquals("The second Rule ID doesn't match", Elements.findElement("search.rule_search_second").getText(), ruleId_2);
            logger.info("First Rule ID is: " + ruleId_1 + " and second Rule ID is: " + ruleId_2);
        } else {
            Assert.assertEquals("The first Rule ID doesn't match", Elements.findElement("search.rule_search_first").getText(), ruleId_2);
            Assert.assertEquals("The second Rule ID doesn't match", Elements.findElement("search.rule_search_second").getText(), ruleId_1);
            logger.info("First Rule ID is: " + ruleId_2 + " and second Rule ID is: " + ruleId_1);
        }
    }

    @Then("^I see Simple Search alert \"([^\"]*)\"$")
    public void i_see_Simple_Search_aAlert(String expectedAlertMessage) throws Throwable {
        String alertMessage = Elements.findElement(By.xpath(CommonUtils.getJsonValue("alert_message", jsonValueFileName))).getText();
        Assert.assertEquals("The alert message doesn't match", expectedAlertMessage, alertMessage);
    }

    @Then("^I see Rules results with \"([^\"]*)\" search$")
    public void i_see_Rules_results_with_search(String inputType) throws Throwable {
        searchResults = Elements.findElement("search.rules_search_results_count").getText();
        searchResultsCount = Integer.valueOf(searchResults.split(" ")[4]);
        switch(inputType) {
            case "Single Category ID":
                query = "select count(distinct mr.merch_rule_id) from merch_rule mr LEFT JOIN merch_rule_trigger_assn rta ON (mr.merch_rule_id = rta.merch_rule_id) " +
                        " LEFT JOIN merch_trig_group_trigger_assn tgta   ON (tgta.merch_trig_group_id = rta.merch_trig_group_id) " +
                        " LEFT JOIN merch_trig t ON (t.merch_trig_id = tgta.merch_trig_id or t.merch_trig_id = rta.merch_trig_id)  " +
                        "LEFT JOIN facet_refine_trig frt ON (t.merch_trig_id = frt.merch_trig_id) LEFT JOIN facet_refine_group frg " +
                        "ON (frt.facet_refine_trig_id = frg.facet_refine_trig_id) LEFT JOIN facet_refine_group_value frgv " +
                        "ON (frg.facet_refine_group_id = frgv.facet_refine_group_id) where frg.dest_attribute_name = 'CAT_ID' " +
                        "and frgv.data_value = " + " '" + simpleSearchValue +"' " + "  and mr.site_tenant=" + " '" + siteTenant + "' " + " ";
                break;
            case "Multiple Category IDs separated by Comma":
            case "Multiple Category IDs separated by Space":
                String categoryIdsString = categoryId_1 + "','" + categoryId_2 ;

                query = "select count(distinct mr.merch_rule_id) from merch_rule mr LEFT JOIN merch_rule_trigger_assn rta ON (mr.merch_rule_id = rta.merch_rule_id) " +
                        " LEFT JOIN merch_trig_group_trigger_assn tgta   ON (tgta.merch_trig_group_id = rta.merch_trig_group_id) " +
                        " LEFT JOIN merch_trig t ON (t.merch_trig_id = tgta.merch_trig_id or t.merch_trig_id = rta.merch_trig_id)  " +
                        "LEFT JOIN facet_refine_trig frt ON (t.merch_trig_id = frt.merch_trig_id) LEFT JOIN facet_refine_group frg " +
                        "ON (frt.facet_refine_trig_id = frg.facet_refine_trig_id) LEFT JOIN facet_refine_group_value frgv " +
                        "ON (frg.facet_refine_group_id = frgv.facet_refine_group_id)  where frg.dest_attribute_name = 'CAT_ID' " +
                        "and frgv.data_value in " + " ('" + categoryIdsString +"') " + "  and mr.site_tenant=" + " '" + siteTenant + "' " + " ";
                break;
        }
        searchResultsCountDB = Integer.valueOf(CommonUtils.getRulesDataFromDB(query, dbName, dbUrl, dbUsername, dbPassword));
        Assert.assertEquals("Total number of rules in DB did not match with the total number of rules on UI", searchResultsCount, searchResultsCountDB);
        logger.info("Total number of rules in DB:::: " + searchResultsCountDB + "  >>>>>>> total number of rules on UI:::: " + searchResultsCount);
    }

    @And("^I click Go button for filtered search$")
    public void iClickGoButtonForFilteredSearch() throws Throwable {
        Clicks.click("search.filter_go_button");
    }

    @Then("^I should see the trigger group names in \"([^\"]*)\"$")
    public void iShouldSeeTheTriggerGroupNamesIn(String order) throws Throwable {
        List<String> triggerGroupNamesUI=new ArrayList<>();
        List<String> beforeSort=new ArrayList<>();
        List<String> afterSort=new ArrayList<>();
        List<WebElement> triggerGroupNames;
        switch(order)
        {
            case "ascending order": triggerGroupNames=Elements.findElements("trigger_group_name_column");
            triggerGroupNames.stream().map(WebElement::getText).forEach(triggerGroupNamesUI::add);
            triggerGroupNames.stream().map(WebElement::getText).forEach(beforeSort::add);
            Collections.sort(triggerGroupNamesUI);
            Assert.assertEquals("The trigger groups name list doesn't match after sort",beforeSort,triggerGroupNamesUI);
            break;

            case "descending order": triggerGroupNames=Elements.findElements("trigger_group_name_column");
                triggerGroupNames.stream().map(WebElement::getText).forEach(triggerGroupNamesUI::add);
                triggerGroupNames.stream().map(WebElement::getText).forEach(beforeSort::add);
                Collections.sort(triggerGroupNamesUI,Collections.reverseOrder());
                Assert.assertEquals("The trigger groups name list doesn't match after sort",beforeSort,triggerGroupNamesUI);

        }
           }

    @When("^I click on the sort icon for sorting the trigger group name column$")
    public void iClickOnTheSortIconForSortingTheTriggerGroupNameColumn() throws Throwable {
        Clicks.javascriptClick("search.sort_trigger_group_name");
    }

    @And("^I enter rule detail search value as \"([^\"]*)\"$")
    public void iEnterRuleDetailSearchValueAs(String arg0) throws Throwable {
        TextBoxes.typeTextNEnter(By.id(CommonUtils.getJsonValue("filter_value",jsonValueFileName)), ruleName);

    }

    @And("^I get the \"([^\"]*)\" to be used as a search value$")
    public void iGetTheToBeUsedAsASearchValue(String arg0) throws Throwable {
        WebElement el = Elements.findElement(By.id("name"));
        ruleName = el.getAttribute("value");
    }
}
