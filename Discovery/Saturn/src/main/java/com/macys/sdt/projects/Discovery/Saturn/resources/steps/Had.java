package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by M671871 on 7/31/2017.
 */
public class Had {

    String jsonValueFileName = "had_common";
    String jsonKeyValueFileName = "had_data";
    String hadSearchValue;
    String selectedAttributeName;
    int selectIndex;
    Random random = new Random();
    private static String dbName = "oracle";
    private static String dbUrl = "jdbc:oracle:thin:@dml1-scan:1521/dpmsts01";
    private static String dbUsername = "Stars_attr";
    private static String dbPassword = "Stars_attr";
    int indexedValue;
    String compositeValue;
    int selectedHadValIndex;
    String index_value_based_on_display_value = "select indexed_value from ATTRIBUTE_VALUE_INDEXING where attribute_id in(select attribute_id from attribute_value where display_value='%s')order by Last_UPDATED DESC";
    String composite_name_based_on_attribute = "select search_attribute_name from SEARCH_ATTRIBUTE_DEF where search_attribute_def_id in(select search_attribute_def_id from SEARCH_ATTR_ATTR_ASSOC where attribute_def_id in (select attribute_def_id from attribute_def where attribute_name='B11507207293860'))";
    String facet_value_based_on_display_value = "select facet_flag from attribute_facet where attribute_id in(select attribute_id from attribute_value where display_value='bucketVal2')order by Last_UPDATED DESC";
    String indexed_flag_based_on_attribute_id = "select indexed_flag from ATTRIBUTE_VALUE_INDEXING where attribute_id in(select attribute_id from attribute_value where display_value='bucketVal2')order by Last_UPDATED DESC";


    @When("^I search for HAD data by \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_search_for_HAD_data_by_with(String filterType, String matchType) throws Throwable {
        Wait.forPageReady();
        Wait.ajaxDone();
        switch (filterType) {
            case "Attribute_Name": {
                hadSearchValue = CommonUtils.getJsonKeyValue("had_values_common", jsonKeyValueFileName).get("attribute_name").toString();
                break;
            }
            case "Dest_Attribute_Name": {
                hadSearchValue = CommonUtils.getJsonKeyValue("had_values_common", jsonKeyValueFileName).get("dest_attribute_name").toString().split("_")[0];
                break;
            }
            case "Attr_Value": {
                hadSearchValue = CommonUtils.getJsonKeyValue("had_values_common", jsonKeyValueFileName).get("attribute_value").toString();
                break;
            }
            case "Index Value": {
                hadSearchValue = CommonUtils.getJsonKeyValue("had_values_common", jsonKeyValueFileName).get("index_value_as").toString();
                break;
            }
            case "Composite": {
                hadSearchValue = CommonUtils.getJsonKeyValue("had_values_common", jsonKeyValueFileName).get("composite").toString();
                break;
            }
            case "Autocomplete Term": {
                hadSearchValue = CommonUtils.getJsonKeyValue("had_values_common", jsonKeyValueFileName).get("autocomplete_term").toString();
                break;
            }
        }
        Wait.untilElementPresent(By.xpath((CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))));
        DropDowns.selectByText(Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))), filterType);
        switch (matchType) {
            case "Has exact match to": {
                Assert.assertTrue("HAD Find Match type - 'Has exact match to' is not displayed", Elements.findElement("had.find_match_type").getText().equals(matchType));
                TextBoxes.typeTextbox("had.find_had_search_input_field", hadSearchValue);
                Clicks.click("had.go_button");
                Wait.ajaxDone();
                break;
            }
            case "Contains": {
                Clicks.click("had.find_match_type");
                Assert.assertTrue("HAD Find Match type - 'Contains' is not displayed", Elements.findElement("had.find_match_type").getText().equals(matchType));
                TextBoxes.typeTextbox("had.find_had_search_input_field", hadSearchValue);
                Clicks.click("had.go_button");
                Wait.ajaxDone();
                break;
            }
            default: {
                DropDowns.selectByText(Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_match", jsonValueFileName))), matchType);
                break;
            }
        }

    }

    @When("^I select HAD attribute$")
    public void i_select_HAD_attribute() throws Throwable {
        Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_attribute", jsonValueFileName))).click();
    }

    @Then("^I see search results in HAD panel$")
    public void i_see_search_results_in_HAD_panel() throws Throwable {
        String holistic_attribute_name;
        Wait.forPageReady();
        Wait.ajaxDone();
        holistic_attribute_name = Elements.findElement("had.holistic_attribute_name").getText();
        Assert.assertTrue("The attribute name doesn't match as entered", hadSearchValue.equalsIgnoreCase(holistic_attribute_name));
    }

    @Then("^I should see pagination options$")
    public void i_should_see_pagination_options() throws Throwable {
        Assert.assertFalse("Pagination option available", Elements.findElement("had.page_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));

    }

    @When("^I click any enabled value in alphanumeric pagination$")
    public void i_click_any_enabled_value_in_alphanumeric_pagination() throws Throwable {
        List<String> enabledOptions = new ArrayList<String>();
        List<WebElement> enabledElements;
        enabledElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("had_enabled_elements", jsonValueFileName)));
        selectedHadValIndex = random.nextInt(enabledElements.size());
        enabledElements.get(selectedHadValIndex).click();

    }

    @Then("^I should see page with search results for selected value$")
    public void i_should_see_page_with_search_results_for_selected_value() throws Throwable {
        List<String> searchList = new ArrayList<String>();
        List<WebElement> elements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("search_result", jsonValueFileName)));
        elements.stream().map(WebElement::getText).forEach(searchList::add);
        String selectElement = Elements.findElement(By.xpath(CommonUtils.getJsonValue("search_result", jsonValueFileName))).getText();
        Iterator iterator = elements.iterator();
        int count = 0;
        int searchCount = 0;
        while (iterator.hasNext()) {
            if (searchList.get(count).startsWith(selectElement)) {
                searchCount++;
            } else
                continue;
        }
        Assert.assertTrue("Search results doesn't contain even a single String starting with" + selectElement, searchCount > 0);
    }

    @When("^I search HAD data for \"([^\"]*)\"$")
    public void i_search_HAD_data_for(String value) throws Throwable {
        String searchValue = CommonUtils.getJsonKeyValue("had_values_mcom", jsonKeyValueFileName).get(value).toString();
        Wait.untilElementPresent(By.xpath((CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))));
        DropDowns.selectByText(Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))), "Attribute_Name");
        TextBoxes.typeTextbox("had.find_had_search_input_field", searchValue);
        Clicks.click("had.go_button");
        Wait.ajaxDone();

    }

    @When("^I search for invalid attribute value$")
    public void i_search_for_invalid_attribute_value() throws Throwable {
        Wait.untilElementPresent(By.xpath((CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))));
        DropDowns.selectByText(Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))), "Attribute_Name");
        Clicks.click("had.find_match_type");
        Assert.assertTrue("HAD Find Match type - 'Contains' is not displayed", Elements.findElement("had.find_match_type").getText().equals("Contains"));
        TextBoxes.typeTextbox("had.find_had_search_input_field", CommonUtils.getJsonKeyValue("invalid_holistic_attribute", jsonValueFileName).get("value").toString());
        Clicks.click("had.go");
        Wait.ajaxDone();
    }

    @Then("^I should see (\\d+) Matching Items$")
    public void i_should_see_Matching_Items(int arg1) throws Throwable {
        String resultCount = Elements.findElement(By.xpath(CommonUtils.getJsonValue("search_result_count", jsonValueFileName))).getText();
        Assert.assertEquals("The result count is not 0", resultCount.split(" ")[0], 0);
    }

    @Then("^I should not see pagination options for \"([^\"]*)\"$")
    public void i_should_not_see_pagination_options_for(String arg1) throws Throwable {
        Assert.assertTrue("Pagination option available", Elements.findElement("had.page_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_next_class", jsonValueFileName)));

    }

    @When("^I modify \"([^\"]*)\" option in holistic attribute page$")
    public void iModifyOptionInHolisticAttributePage(String arg0) throws Throwable {
        indexedValue = (2 + (int) (Math.random() * ((20 - 2) + 1))) * 5;
        if (Elements.findElement("had.holistic_attribute_index").isEnabled()) {
            Clicks.click("had.holistic_attribute_index");
            TextBoxes.typeTextbox("had.holistic_attribute_index_value", String.valueOf(indexedValue));
        }
    }

    @And("^I modify \"([^\"]*)\" dropdown list in holistic attribute page$")
    public void iModifyDropdownListInHolisticAttributePage(String arg0) throws Throwable {
        DropDowns.selectRandomValue("had.holistic_select_composite");
        compositeValue = DropDowns.getSelectedValue(By.xpath("had.holistic_select_composite"));
    }

    @And("^I click on \"([^\"]*)\" button in holistic attribute page$")
    public void iClickOnButtonInHolisticAttributePage(String arg0) throws Throwable {
        Clicks.javascriptClick("had.save_changes");
    }

    @Then("^I should be on \"([^\"]*)\" page$")
    public void iShouldBeOnPage(String title) throws Throwable {
        String page_title = Elements.findElement(By.xpath(CommonUtils.getJsonValue("holistic_page", jsonValueFileName))).getText();
        Assert.assertEquals("The HAD page did not open", title, page_title);
    }

    @And("^I should see search results in holistic attribute page$")
    public void iShouldSeeSearchResultsInHolisticAttributePage() throws Throwable {
        Assert.assertTrue("no search results available", Elements.findElement(By.xpath(CommonUtils.getJsonValue("had_arrtibute_search_results", jsonValueFileName))).isDisplayed());
    }

    @Then("^I should see Index Value as unchecked in holistic attribute page$")
    public void iShouldSeeIndexValueAsUncheckedInHolisticAttributePage() throws Throwable {
        Assert.assertFalse("The index value is checked", Elements.findElement("had.holistic_attribute_index").isSelected());
    }

    @Then("^I confirm that the second line under the Attribute values under New & Changed Values section displays Attribute Name$")
    public void i_confirm_that_the_second_line_under_the_Attribute_values_under_New_Changed_Values_section_displays_Attribute_Name() throws Throwable {
        String attributeName = Elements.findElement("had.new_and_changed_value_attribute_name").getText().toString();
        String attributeValue = Elements.findElement("had.new_and_changed_value_name").getText().toString();
        Clicks.click("had.new_and_changed_value_name");
        Assert.assertTrue("The correct attribute name is not displayed", (Elements.findElement("had.attribute_name").getText().toString()).equals(attributeName));
        Assert.assertTrue("The correct attribute value is not displayed", (Elements.findElement("had.had_attribute_value").getText().toString()).equals(attributeValue));
    }

    @Then("^I should verify Index value option as in database$")
    public void iShouldVerifyIndexValueOptionAsInDatabase() throws Throwable {
        String result = getUpdatedAttributeDataFromDB(index_value_based_on_display_value);
        Assert.assertEquals("The indexed value doesn't match", indexedValue, result);

    }


    @And("^I should see composite value as entered in database$")
    public void iShouldSeeCompositeValueAsEnteredInDatabase() throws Throwable {
        String result = getUpdatedAttributeDataFromDB(composite_name_based_on_attribute);
        Assert.assertEquals("The composite value doesn't match", compositeValue, result);
    }

    @Then("^I should see facet as \"([^\"]*)\" in data base$")
    public void iShouldSeeFacetAsInDataBase(String value) throws Throwable {
        String result = getUpdatedAttributeDataFromDB(facet_value_based_on_display_value);
        Assert.assertEquals("The facet value doesn't match", value, result);
    }


    public String getUpdatedAttributeDataFromDB(String query) throws SQLException

    {
        String result = "";
        Statement stmt;
        String sql;
        ResultSet resultSet = null;
        try {
            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            if (StepUtils.bloomingdales()) {
                sql = String.format(query, "BCOM");
            } else {
                sql = String.format(query, "MCOM");
            }
            resultSet = stmt.executeQuery(sql);
            resultSet.next();
            resultSet.getString(1);
            System.out.println("rs::::" + resultSet);
            System.out.print("query executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
        return result;
    }

    @And("^I  should see \"([^\"]*)\" as \"([^\"]*)\" in data base$")
    public void iShouldSeeAsInDataBase(String arg0, String value) throws Throwable {
        String result = getUpdatedAttributeDataFromDB(facet_value_based_on_display_value);
        Assert.assertEquals("The facet value doesn't match", value, result);

    }

    @And("^I should see Index Value as \"([^\"]*)\" in database$")
    public void iShouldSeeIndexValueAsInDatabase(String value) throws Throwable {
        String result = getUpdatedAttributeDataFromDB(indexed_flag_based_on_attribute_id);
        Assert.assertEquals("The facet value doesn't match", value, result);
    }


    @When("^I check Index Value header check box  in holistic attribute page$")
    public void iCheckIndexValueHeaderCheckBoxInHolisticAttributePage() throws Throwable {
        Clicks.click(By.xpath("had.index_value_all"));
    }

    @And("^I click Go button on had page$")
    public void iClickGoButtonOnHadPage() throws Throwable {
        Clicks.click("had.go_button");
    }

    @When("^I click on the first search result$")
    public void iClickOnTheFirstSearchResult() throws Throwable {
        Clicks.click(By.xpath(CommonUtils.getJsonValue("had_arrtibute_search_results", jsonValueFileName)));
    }

    @Then("^I Click save changes button$")
    public void iClickSaveChangesButton() throws Throwable {
        Clicks.click("had.save_changes");
    }


    @And("^I select the filter \"([^\"]*)\" on HAD page$")
    public void iSelectTheFilterOnHADPage(String filterType) throws Throwable {
        DropDowns.selectByText(Elements.findElement(By.xpath(CommonUtils.getJsonValue("find_had_search_filter_type", jsonValueFileName))), filterType);

    }

    @Then("^I enter for \"([^\"]*)\" as \"([^\"]*)\" in search field in holistic attribute page$")
    public void iEnterForAsInSearchFieldInHolisticAttributePage(String value, String matchType) throws Throwable {
        Clicks.click("had.find_match_type");
        Assert.assertTrue("HAD Find Match type - 'Contains' is not displayed", Elements.findElement("had.find_match_type").getText().equals(matchType));
        TextBoxes.typeTextbox("had.find_had_search_input_field", value);
    }


    @When("^I select any attribute form \"([^\"]*)\" section in holistic attribute page$")
    public void iSelectAnyAttributeFormSectionInHolisticAttributePage(String arg0) throws Throwable {
        List<WebElement> attributeElements;
        attributeElements = Elements.findElements(By.xpath(CommonUtils.getJsonValue("new_attribute_total_value", jsonValueFileName)));
        int random = (int)(Math.random() * attributeElements.size() + 1);
        Elements.findElement(By.xpath(CommonUtils.getJsonValue("new_attribute_value_one",jsonValueFileName)+random+CommonUtils.getJsonValue("new_attribute_value_two",jsonValueFileName))).click();

    }

    @Then("^I should see attribute name consists of term 'color'$")
    public void iShouldSeeAttributeNameConsistsOfTermColor() throws Throwable {
        String color_attribute_name;
        Wait.forPageReady();
        Wait.ajaxDone();
        color_attribute_name = Elements.findElement("had.holistic_attribute_name").getText();
        Assert.assertTrue("The attribute name doesn't match as entered", hadSearchValue.equalsIgnoreCase(color_attribute_name));

    }

    @Then("^I should see attribute name consists of term \"([^\"]*)\"$")
    public void iShouldSeeAttributeNameConsistsOfTerm(String value) throws Throwable {
        String color_attribute_name;
        Wait.forPageReady();
        Wait.ajaxDone();
        color_attribute_name = Elements.findElement("had.holistic_attribute_name").getText();
        Assert.assertTrue("The attribute name doesn't match as entered",color_attribute_name.contains(value));
    }


    @Then("^I see HAD results for \"([^\"]*)\" match data listed for \"([^\"]*)\"$")
    public void iSeeHADResultsForMatchDataListedFor(String filterValue, String condition) throws Throwable {
        String searchValue="";
        Wait.ajaxDone();
        Wait.forPageReady();
      Wait.untilElementPresent("had.page_next");
        switch (filterValue) {
            case "Attribute_Name": {
                searchValue = Elements.findElement(By.xpath(CommonUtils.getJsonValue("filter_value_one", jsonValueFileName) + 3 + CommonUtils.getJsonValue("filter_value_two", jsonValueFileName))).getText();
                break;
            }
            case "Dest_Attribute_Name":
                searchValue = Elements.findElement(By.xpath(CommonUtils.getJsonValue("filter_value_one", jsonValueFileName) + 2 + CommonUtils.getJsonValue("filter_value_two", jsonValueFileName))).getText();
                break;
            case "Attr_Value": {
                searchValue = Elements.findElement(By.xpath(CommonUtils.getJsonValue("filter_value_one", jsonValueFileName) + 5 + CommonUtils.getJsonValue("filter_value_two", jsonValueFileName))).getText();
                break;
            }
            case "Index Value As": {
                searchValue = Elements.findElement(By.xpath(CommonUtils.getJsonValue("filter_index_value", jsonValueFileName))).getText();
                break;
            }
            case "Composite": {
                searchValue = DropDowns.getSelectedValue(By.xpath(CommonUtils.getJsonValue("filter_composite_value", jsonValueFileName)));
                break;
            }
        }
            if(condition.equals("Has exact match to")) {
                Assert.assertTrue("the data entered doesn't has exact match to the result displayed",searchValue.equalsIgnoreCase(hadSearchValue));
            }
            else if(condition.equals("Contains")){
                Assert.assertTrue("the data entered doesn't Contains the result displayed",searchValue.contains(hadSearchValue.toUpperCase()));
            }
    }

    @And("^I confirm numeric entry pagination$")
    public void iConfirmNumericEntryPagination() throws Throwable {
         int totalPages=1;
         Wait.untilElementPresent("had.numeric_pagination");
        Assert.assertTrue("The numeric pagination is not displayed",Elements.findElement("had.numeric_pagination").isDisplayed());
        totalPages= Integer.valueOf(Elements.findElement("had.total_pages").getText());
        int randomPage = (int)(Math.random() * totalPages + 1);
        TextBoxes.typeTextbox("had.page_no_text_box",String.valueOf(randomPage));
        Clicks.click("had.page_next");
        if(randomPage==totalPages)
        {
            Assert.assertTrue("Pagination option available", Elements.findElement("had.page_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertTrue("Pagination option available", Elements.findElement("had.page_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_first").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_previous").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
        }
        else if(randomPage==1)
        {
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
        }

        else
        {
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_next").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_last").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_first").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
            Assert.assertFalse("Pagination option available", Elements.findElement("had.page_previous").getAttribute("class").equalsIgnoreCase(CommonUtils.getJsonValue("page_class", jsonValueFileName)));
        }





    }
}
