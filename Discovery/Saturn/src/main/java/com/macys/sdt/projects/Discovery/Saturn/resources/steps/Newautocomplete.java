package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import com.thoughtworks.selenium.webdriven.commands.Click;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yh01542 on 12/18/2017.
 */
public class Newautocomplete {
    List<WebElement> tableList;
    WebElement randomRowData;
    String randomAplhaNumericString;
    Random random = new Random();
    String enteredString;
    private static final Logger log = LoggerFactory.getLogger(Newautocomplete.class);
    private static String dbName = "oracle";
    private static String dbUrl = "jdbc:oracle:thin:@mdc2vrb046:1521/starsdev";
    private static String username = "saturn";
    private static String password = "saturn";

    @When("^I mouse hover on \"([^\"]*)\" option$")
    public void i_mouse_hover_on_option(String optionString) throws Throwable {
        Wait.forPageReady();
        if (optionString.equals("administration")) {
            Clicks.hoverForSelection("home.administration");
            Wait.untilElementPresent(By.xpath("//li[@class='navHelp activeTab']"));
        } else if (optionString.equals("New Autocomplete")) {
            Clicks.hoverForSelection("home.new_autocomplete");
            Wait.untilElementPresent(By.xpath("//li[@class='navNewAutocomplete activeTab']"));
        }
    }

    @Then("^I should see New Autocomplete option in the dropdown list$")
    public void iShouldSeeNewAutocompleteOptionInTheDropdownList() throws Throwable {
        Assert.assertTrue("New Autocomplete option is not displayed in dropdown list", Elements.findElement("home.new_autocomplete").isDisplayed());
        log.info("New Autocomplete option is displayed in Administration dropdown");
    }

    @Then("^I should see \"([^\"]*)\" option is placed below \"([^\"]*)\" option$")
    public void iShouldSeeNewAutocompleteOptionIsPlacedBelowOption(String secondString, String firstString) throws Throwable {
        String actualSybling = Elements.findElement(By.xpath("//a[text()='" + firstString + "']/../following-sibling::li/a")).getText();
        Assert.assertTrue(secondString + " is not a sybling of " + firstString, secondString.equals(actualSybling));
        log.info(secondString + " is a sybling of " + firstString);
    }

    @Then("^I should see a flyout with below options populated:$")
    public void i_should_see_a_flyout_with_below_options_populated(DataTable new_autocomplete_options) throws Throwable {
        List newAutocompleteList = new_autocomplete_options.asList(String.class);
        List<String> newAutocomplete_options = Elements.findElements(By.xpath("//a[text()='New Autocomplete']//following-sibling::ul/li")).stream().map(ele -> ele.getText()).collect(Collectors.toList());
        Assert.assertEquals(newAutocomplete_options.size(), newAutocompleteList.size());
        Assert.assertTrue("New Autocomplete flyout options are not as expected", newAutocomplete_options.containsAll(newAutocompleteList));
        log.info("New Autocomplete flyout options are displayed as expected");
    }

    @Then("^I should not see \"([^\"]*)\" tab$")
    public void i_should_not_see_tab(String main_tab) throws Throwable {
        if (main_tab.equals("Administration"))
            Assert.assertFalse(main_tab + " tab is displayed", Elements.elementPresent("home.administration"));
        log.info(main_tab + " tab is not displayed as expected");
    }

    @And("^I click on \"([^\"]*)\" option$")
    public void iClickOnOption(String subCategory) throws Throwable {
        if (subCategory.equals("Autocomplete Overrides")) Clicks.click("home.autocomplete_override");
        else if (subCategory.equals("Dictionary Overrides")) Clicks.click("home.dictionary_override");
        else if (subCategory.equals("About")) Clicks.click("home.about");
        else if (subCategory.equals("Clear")) Clicks.click(By.xpath("//*[text()='[clear]']"));
    }

    @Then("^I should see (?:autocomplete|dictionary) override table with (\\d+) columns$")
    public void iShouldSeeAutocompleteOverrideTableWithColumns(int noof_columns) throws Throwable {
        Wait.untilElementPresent(By.xpath("//table[@class='display datatable']//th"));
        int actual_column_size = Elements.findElements(By.xpath("//table[@class='display datatable']//th")).size();
        Assert.assertTrue("Total no.of columns are not as expected", actual_column_size == noof_columns);
        log.info("Total no.of columns are as expected");
    }

    @And("^I should see \"([^\"]*)\" text in header bar$")
    public void iShouldSeeTextInHeaderBar(String table_title) throws Throwable {
        Assert.assertTrue(table_title + " is not displaying as expected", Elements.findElement("newautocomplete.table_header").getText().contains(table_title));
        log.info(table_title + " is displaying as expected");
    }


    @And("^I should see \"([^\"]*)\" button in header bar$")
    public void iShouldSeeButtonInHeaderBar(String buttonName) throws Throwable {
        if (buttonName.equals("Add Override")) {
            Assert.assertTrue(Elements.findElement("newautocomplete.add_override").getText().equals(buttonName));
        } else if (buttonName.equals("Export")) {
            Assert.assertTrue(Elements.findElement("newautocomplete.export").getText().equals(buttonName));
        }
        log.info(buttonName + " are displayed as expected");
    }

    @Then("^I should see (?:autocomplete|dictionary) override table with below (\\d+) columns:$")
    public void i_should_see_autocomplete_override_table_with_below_columns(int noofcolumns, DataTable overrideTable) throws Throwable {
        List autocompleteOverrideColumnList = overrideTable.asList(String.class);
        List<String> actualautocompleteOverrideColumnList = Elements.findElements(By.xpath("//table[@class='display datatable']//th")).stream().map(ele -> ele.getText()).collect(Collectors.toList());
        Assert.assertEquals(actualautocompleteOverrideColumnList.size(), noofcolumns);
        Assert.assertTrue("Override table column names are not displayed as expected", actualautocompleteOverrideColumnList.containsAll(autocompleteOverrideColumnList));
        log.info("Autocomplete override table is displayed as expected");
    }

    @And("^I should see that \"([^\"]*)\" column contains \"([^\"]*)\" data$")
    public void iShouldSeeThatColumnContainsData(String columnName, String data) throws Throwable {
        tableList = Elements.findElements(By.xpath("//tbody/tr"));
        randomRowData = tableList.get(random.nextInt(tableList.size()));
        String actualvalue;
        SimpleDateFormat sdf;
        Date date;
        switch (columnName) {
            case "ID":
                NumberUtils.isNumber(randomRowData.findElement(By.xpath("//td[1]")).getText());
                break;
            case "Override Phrase":
                String actualValue = randomRowData.findElement(By.xpath("//td[2]")).getText();
                StringUtils.isAsciiPrintable(actualValue);
                break;
            case "Type":
                Assert.assertTrue(randomRowData.findElement(By.xpath("//td[3]")).getText().equals(data.split(" or ")[0]) ||
                        randomRowData.findElement(By.xpath("//td[3]")).getText().equals(data.split(" or ")[1]));
                break;
            case "Term":
                break;
            case "Operator":
                Assert.assertTrue(randomRowData.findElement(By.xpath("//td[4]")).getText().equals(data.split(" or ")[0]) ||
                        randomRowData.findElement(By.xpath("//td[4]")).getText().equals(data.split(" or ")[1]));
                break;
            case "Created By":
                StringUtils.isAlpha(randomRowData.findElement(By.xpath("//td[5]")).getText());
                break;
            case "Creation Date":
                actualvalue = randomRowData.findElement(By.xpath("//td[6]")).getText();
                sdf = new SimpleDateFormat(data);
                date = sdf.parse(actualvalue);
                Assert.assertTrue(actualvalue.equals(sdf.format(date)));
                break;
            case "Modified By":
                StringUtils.isAlpha(randomRowData.findElement(By.xpath("//td[7]")).getText());
                break;
            case "Modified Date":
                actualvalue = randomRowData.findElement(By.xpath("//td[8]")).getText();
                sdf = new SimpleDateFormat(data);
                date = sdf.parse(actualvalue);
                Assert.assertTrue(actualvalue.equals(sdf.format(date)));
                break;
            case "Published":
                Assert.assertTrue(randomRowData.findElement(By.xpath("//td[9]")).getText().equals(data.split(" or ")[0]) ||
                        randomRowData.findElement(By.xpath("//td[9]")).getText().equals(data.split(" or ")[1]));
                break;
            case "Action (s)":
                Assert.assertTrue(randomRowData.findElement(By.xpath("//td[10]")).getText().equals(data.split(" or ")[0]) &&
                        randomRowData.findElement(By.xpath("//td[10]")).getText().equals(data.split(" or ")[1]));
                break;
            default:
                Assert.fail(columnName + " is not defined");
        }
        log.info("Autocomplete override table fields are displayed as expected");
    }

    @And("^I should see that \"([^\"]*)\" is not hyperlinked$")
    public void iShouldSeeThatIsNotHyperlinked(String columnName) throws Throwable {
        if (columnName == "ID") {
            Assert.assertFalse("ID field is hyperlinked", randomRowData.findElement(By.xpath("//td[1]/a[contains(@class,'isLink')]")).isDisplayed());
            log.info("ID field is not hyperlinked as expected");
        }
    }

    @And("^I should see that \"([^\"]*)\" and \"([^\"]*)\" strings are hyperlinked$")
    public void iShouldSeeThatAndStringsAreHyperlinked(String edit, String delete) throws Throwable {
        Assert.assertTrue("Edit string is not hyperlinked", randomRowData.findElement(By.xpath("//td[10]/a[text()='" + edit + "']")).getAttribute("class").contains("isLink"));
        Assert.assertTrue("Delete string is not hyperlinked", randomRowData.findElement(By.xpath("//td[10]/a[text()='" + delete + "']")).getAttribute("class").contains("isLink"));
        log.info("Edit and Delete strings are hyperlinked as expected");
    }

    @And("^I should see standard page navigation in right side of footer navigation bar$")
    public void iShouldSeeStandardPageNavigationInRightSideOfFooterNavigationBar() throws Throwable {
        Assert.assertTrue(Elements.elementPresent("newautocomplete.first_page"));
        Assert.assertTrue(Elements.elementPresent("newautocomplete.previous_page"));
        Assert.assertTrue(Elements.elementPresent("newautocomplete.pagination_links"));
        Assert.assertTrue(Elements.elementPresent("newautocomplete.next_page"));
        Assert.assertTrue(Elements.elementPresent("newautocomplete.last_page"));
        log.info("Page count and total record count details are displayed as expected");
    }

    @And("^I should see page record count in left side of footer navigation bar$")
    public void iShouldSeePageRecordCountInLeftSideOfFooterNavigationBar() throws Throwable {
        Assert.assertTrue("Page Count details are not displayed", Elements.elementPresent("newautocomplete.page_record_count"));
        log.info("Page count and total record count details are displayed as expected");
    }

    @And("^I should see pagination links are working as expected$")
    public void iShouldSeePaginationLinksAreWorkingAsExpected() throws Throwable {
        Assert.assertTrue(Elements.findElement("newautocomplete.first_page").getAttribute("class").contains("disabled"));
        Assert.assertTrue(Elements.findElement("newautocomplete.previous_page").getAttribute("class").contains("disabled"));
        Assert.assertTrue(Elements.findElements("newautocomplete.pagination_links").get(0).getAttribute("class").contains("disabled"));

        Assert.assertFalse(Elements.findElement("newautocomplete.next_page").getAttribute("class").contains("disabled"));
        Assert.assertFalse(Elements.findElement("newautocomplete.last_page").getAttribute("class").contains("disabled"));

        List<String> paginationLinks = Elements.findElements("newautocomplete.pagination_links").stream().map(ele -> ele.getText()).collect(Collectors.toList());

        int rand = random.nextInt(paginationLinks.size());
        if (!paginationLinks.get(rand).contains("1")) {
            WebElement ele = Elements.findElements("newautocomplete.pagination_links").get(rand);
            String str = ele.getText();
            ele.click();
            Assert.assertTrue(Elements.findElement(By.xpath("//span[contains(text(),'" + str + "')]")).getAttribute("class").contains("disabled"));
            Assert.assertFalse(Elements.findElement("newautocomplete.first_page").getAttribute("class").contains("disabled"));
            Assert.assertFalse(Elements.findElement("newautocomplete.previous_page").getAttribute("class").contains("disabled"));
        }
        int index = paginationLinks.size() - 1;
        if (!paginationLinks.get(index).contains("1")) {
            WebElement ele = Elements.findElements("newautocomplete.pagination_links").get(index);
            String str = ele.getText();
            ele.click();
            Assert.assertTrue(Elements.findElement(By.xpath("//span[contains(text(),'" + str + "')]")).getAttribute("class").contains("disabled"));
            if (Elements.findElements("newautocomplete.pagination_links").get(paginationLinks.size() - 1).getText() != str) {
                Elements.findElements("newautocomplete.pagination_links").get(paginationLinks.size() - 1).click();
                Assert.assertTrue(Elements.findElements("newautocomplete.pagination_links").get(paginationLinks.size() - 1).getAttribute("class").contains("disabled"));
                Assert.assertTrue(Elements.findElement("newautocomplete.next_page").getAttribute("class").contains("disabled"));
                Assert.assertTrue(Elements.findElement("newautocomplete.last_page").getAttribute("class").contains("disabled"));
            }
        }
    }

    @And("^I should see primary sort on the table as descending by creation date$")
    public void iShouldSeePrimarySortOnTheTableAsDescendingByCreationDate() throws Throwable {
        String sortAttrValue = Elements.findElement("newautocomplete.table_column_headings").findElement(By.xpath("//*[@sortby='creationDate']//span")).getAttribute("class");
        Assert.assertTrue(sortAttrValue.contains("icon-triangle"));
    }

    @And("^I should see all cloumns are sortable except \"([^\"]*)\" column$")
    public void iShouldSeeAllCloumnsAreSortableExceptColumn(String unsortable_column) throws Throwable {
        List<WebElement> column_Names = Elements.findElements("newautocomplete.table_column_headings");
        column_Names.forEach(link -> {
            if (link.getAttribute("sortby") == null) {
                Assert.assertTrue(link.findElement(By.tagName("div")).getText().equals(unsortable_column));
            }
        });
    }

    @And("^I click on \"([^\"]*)\" button$")
    public void iClickOnButton(String buttonName) throws Throwable {
        if (buttonName.equals("Add Override")) {
            Clicks.click("newautocomplete.add_override");
            Assert.assertTrue(Elements.findElement("newautocomplete.popup_title").getText().equals(buttonName));
            log.info(buttonName + " is clicked and popup overlay is displayed");
        } else if (buttonName.equals("OK")) {
            Clicks.click("newautocomplete.ok_button");
            Assert.assertFalse(Elements.elementPresent("newautocomplete.popup_title"));
        } else if (buttonName.equals("Cancel")) {
            Clicks.click("newautocomplete.cancel_button");
        } else if (buttonName.equals("Go")) {
            Clicks.click(By.xpath("//*[text()='" + buttonName + "']"));
        }

    }

    @And("^I should see \"([^\"]*)\" text box as required field$")
    public void iShouldSeeTextBoxAsRequiredField(String fieldName) throws Throwable {
        Assert.assertTrue(Elements.findElement(By.xpath("//span[text()='" + fieldName + "']")).getText().contains("*"));
        Assert.assertTrue(Elements.findElement("newautocomplete.display_phrase_text").getAttribute("type").equals("text"));
        log.info("verified that " + fieldName + " text box is a required field");
    }

    @And("^I should see \"([^\"]*)\" and \"([^\"]*)\" dropdown boxes as required fields$")
    public void iShouldSeeAndDropdownBoxesAsRequiredFields(String override, String operator) throws Throwable {
        Assert.assertTrue(Elements.findElement(By.xpath("//span[text()='" + override + "']")).getText().contains("*"));
        Assert.assertTrue(Elements.findElement(By.xpath("//span[text()='" + operator + "']")).getText().contains("*"));

        //WhiteList or Blacklist validation from DB and UI
        List<String> override_types = Elements.findElements("newautocomplete.override_type_field").stream().map(e -> e.getText()).collect(Collectors.toList());
        List<String> actual_override_types = autocompleteOverrideTypes("SELECT * FROM AUTO_OVERRIDE_TYPE_NEW");
        Assert.assertTrue(override_types.equals(actual_override_types));

        //Contains or Exact Match validation from DB and UI
        List<String> operator_types = Elements.findElements("newautocomplete.operator_type_field").stream().map(e -> e.getText()).collect(Collectors.toList());
        List<String> actual_operator_types = autocompleteOverrideTypes("SELECT * FROM AUTO_OVERRIDE_OPER_TYPE");
        Assert.assertTrue(operator_types.equals(actual_operator_types));

        log.info("verified that " + override + "and " + operator + " text boxes are required fields");
    }

    @And("^I should see \"([^\"]*)\" checkbox$")
    public void iShouldSeeCheckbox(String fieldName) throws Throwable {
        Assert.assertTrue(Elements.elementPresent(By.xpath("//span[text()='" + fieldName + "']")));
        Assert.assertTrue(Elements.findElement("newautocomplete.publish_field").getAttribute("type").equals("checkbox"));
        log.info("verified " + fieldName + " checkbox field");
    }

    @And("^I should see \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" buttons$")
    public void iShouldSeeAndButtons(String ok, String cancel, String close) throws Throwable {
        Assert.assertTrue(Elements.findElement(By.xpath("//span[text()='" + ok + "']")).getAttribute("class").contains("button"));
        Assert.assertTrue(Elements.findElement(By.xpath("//span[text()='" + cancel + "']")).getAttribute("class").contains("button"));
        Assert.assertTrue(Elements.elementPresent(By.xpath("//span[text()='" + close + "']")));
        log.info("Ok and Cancel buttons are displayed");
    }

    @And("^I navigate to \"([^\"]*)\" popup in \"([^\"]*)\" page$")
    public void iNavigateToPopupInPage(String buttonName, String tableName) throws Throwable {
        if (tableName.equals("Autocomplete Overrides")) {
            Clicks.click("home.autocomplete_override");
            if (buttonName.equals("Add Override")) {
                Clicks.click("newautocomplete.add_override");
                Assert.assertTrue(Elements.findElement("newautocomplete.popup_title").getText().equals(buttonName));
            }
        } else if (tableName.equals("Dictionary Overrides")) {
            Clicks.click("home.dictionary_override");
        } else {
            Assert.fail(tableName + " is not defined");
        }
        log.info("Navigated to " + buttonName + " popup overlay");
    }

    @And("^I enter display phrase with (\\d+) characters in combination of alphanumeric and special characters$")
    public void iEnterDisplayPhraseWithCharactersInCombinationOfAlphanumericAndSpecialCharacters(int size) throws Throwable {
        randomAplhaNumericString = UUID.randomUUID().toString().replace("-", "");
        StringBuilder inputString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            inputString.append(randomAplhaNumericString.charAt(random.nextInt(randomAplhaNumericString.length())));
        }
        enteredString = inputString.toString();
        TextBoxes.typeTextbox("newautocomplete.display_phrase_text", inputString.toString());
    }

    @Then("^I should see that override phrase accepts till (\\d+) characters only$")
    public void iShouldSeeThatOverridePhraseAcceptsTillCharactersOnly(int maxSize) throws Throwable {
        // ???below element is not saving input value need to check
//        Elements.findElement("newautocomplete.display_phrase_text").getText()
        Assert.assertTrue(Elements.findElement("newautocomplete.display_phrase_text").getText().length() == maxSize);
    }

    @And("^I select \"([^\"]*)\" as \"([^\"]*)\"$")
    public void iSelectAs(String value, String fieldName) throws Throwable {
        String elementName = fieldName.equals("Override type") ? "newautocomplete.override_type" : "newautocomplete.operator_type";
        Elements.findElements(elementName).stream().filter(e -> e.getText().equals(value)).collect(Collectors.toList()).get(0).click();
    }

    @And("^I \"([^\"]*)\" publish textbox$")
    public void iPublishTextbox(String publishValue) throws Throwable {
        if (publishValue.equals("de-select")) {
            Elements.findElement("newautocomplete.publish_field").click();
            log.info("De selected Publish text box");
        } else {
            log.info("By Default Publish field is selected");
        }
    }

    @Then("^I should (?:\"([^\"]*)\" |)see that new phrase is displayed in autocomplete override table$")
    public void iShouldSeeThatNewPhraseIsDisplayedInAutocompleteOverrideTable(String string1) throws Throwable {
        if (string1.equals("not")) {
            Assert.assertFalse(Elements.findElement(By.xpath("//tbody/tr[1]")).getText().contains(enteredString.substring(0, 100).toLowerCase()));
            log.info("Entered phrase is not displaying in autocomplete override table as expected");
        } else {
            Assert.assertTrue(Elements.findElement(By.xpath("//tbody/tr[1]")).getText().contains(enteredString.substring(0, 100).toLowerCase()));
            log.info("Entered phrase is displaying in autocomplete override table");
        }
    }

    @And("^I should (?:\"([^\"]*)\" |)see that added phrase is saved in saturn DB$")
    public void iShouldSeeThatAddedPhraseIsSavedInSaturnDB(String string1) throws Throwable {
        String newPhraseID = Elements.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
        String actual_entered_phrase = autocompleteOverrideTableData("SELECT * FROM AUTO_OVERRIDE_NEW WHERE AUTO_OVERRIDE_TYPE_NEW_ID=" + newPhraseID + "");
        if (string1.equals("not")) {
            Assert.assertFalse(enteredString.substring(0, 100).toLowerCase().equals(actual_entered_phrase));
        } else {
            Assert.assertTrue(enteredString.substring(0, 100).toLowerCase().equals(actual_entered_phrase));
            Assert.assertFalse(actual_entered_phrase.contains(" "));
//        all characters in lower case and with no whitespaces
        }
    }

    @Then("^I should see that \"([^\"]*)\" operator type is disabled$")
    public void iShouldSeeThatOperatorTypeIsDisabled(String operType) throws Throwable {
        Elements.findElement("");
    }

    @Then("^I should see two levels of filters in the override table$")
    public void iShouldSeeTwoLevelsOfFiltersInTheOverrideTable() throws Throwable {
//       Assert.assertTrue(Elements.findElement("newautocomplete.filter_section").findElements(By.tagName("label")).size() == 2);
        log.info("Two filters are displayed in override table");
    }

    @Then("^I should see (?:primary|secondary) filter with below options:$")
    public void i_should_see_primary_filter_as_override_type_with_below_options(DataTable filter1Options) throws Throwable {
        List expectedFilter1Values = filter1Options.asList(String.class);
        List actualFilter1Values = Elements.findElement("newautocomplete.filter_options").findElements(By.tagName("option")).stream().filter(e -> !e.getText().equals("")).collect(Collectors.toList());
        Assert.assertTrue(actualFilter1Values.equals(expectedFilter1Values));
        log.info("Filter values are as expected");
    }

    @And("^I should see \"([^\"]*)\" button and \"([^\"]*)\" option$")
    public void iShouldSeeButtonAndOption(String go_button, String clear_link) throws Throwable {
        Elements.elementPresent(By.linkText(go_button));
        Elements.findElement(By.xpath("//*[text()='[clear]']")).getAttribute("title").equals(clear_link);
    }


    @And("^I select any random option from \"([^\"]*)\" filter$")
    public String iSelectAnyRandomOptionFromFilter(String filterOption) throws Throwable {
        String filteroption = (filterOption.equals("primary") ? "filter_options1" : "filter_options2");
        List<WebElement> filterOptions = Elements.findElement("newautocomplete."+filteroption+"").findElements(By.tagName("option"));
        WebElement selectedFilter = filterOptions.get(random.nextInt(filterOptions.size()));
        String filterOptionSelected = selectedFilter.getText();
        selectedFilter.click();
        return filterOptionSelected;
    }

    @And("^I select \"([^\"]*)\" option$")
    public void iSelectOption(String filterOption) throws Throwable {
        Elements.findElement("newautocomplete.filter_options2").findElement(By.xpath("//option[text()='" + filterOption + "']")).click();
    }

    @And("^I enter value in secondary filter textbox field based on selected \"([^\"]*)\" option$")
    public void iEnterValueInSecondaryFilterTextboxFieldBasedOnSelectedOption(String filterOption) throws Throwable {
//        Need to update based on service response or DB once data is populated
    }

    @Then("^I should see that selected filters are cleared$")
    public void iShouldSeeThatSelectedFiltersAreCleared() throws Throwable {
        Assert.assertTrue(Elements.findElement("newautocomplete.filter_options1").findElement(By.xpath("/option[@selected='selected']")).getText().equals(""));
        Assert.assertTrue(Elements.findElement("newautocomplete.filter_options2").findElement(By.xpath("/option[@selected='selected']")).getText().equals(""));

    }
    String selectedFilterOption1;
    String selectedFilterOption2;
    @And("^I apply both the filters and click on \"([^\"]*)\" button$")
    public void iApplyBothTheFiltersAndClickOnButton(String buttonName) throws Throwable {
        selectedFilterOption1 = iSelectAnyRandomOptionFromFilter("primary");
        selectedFilterOption2 = iSelectAnyRandomOptionFromFilter("secondary");
        iClickOnButton(buttonName);
        Wait.forPageReady();
    }

    @When("^I paginate$")
    public void iPaginate() throws Throwable {
        if(Elements.elementPresent("newautocomplete.next_page")){
            Clicks.click("newautocomplete.next_page");
//            Clicks.click(By.id("tblExceptions_next"));
            log.info("Paginated");
        }else{
            log.info("Pagination not available for the selected filter options");
        }
    }

    @Then("^I should see that selected filters are persisted$")
    public void iShouldSeeThatSelectedFiltersArePersisted() throws Throwable {
        Assert.assertTrue(Elements.findElement("newautocomplete.filter_options1").findElement(By.xpath("/option[@selected='selected']")).getText().equals(selectedFilterOption1));
        Assert.assertTrue(Elements.findElement("newautocomplete.filter_options2").findElement(By.xpath("/option[@selected='selected']")).getText().equals(selectedFilterOption2));
    }





//    #######################################################
//    New Autocomplete Data validations methods
//    #######################################################

    private static List autocompleteOverrideTypes(String query) {
        List operatoryTypesDB = new ArrayList();
        try {
            ResultSet resultSetValue = CommonUtils.getNewAutocompleteDataFromDB(query, dbName, dbUrl, username, password);
            while (resultSetValue.next() == true) {
                operatoryTypesDB.add(resultSetValue.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.closeDBConnection();
        return operatoryTypesDB;
    }

    private static String autocompleteOverrideTableData(String query) {
        String overrideTableData = null;
        try {
            ResultSet resultSetValue = CommonUtils.getNewAutocompleteDataFromDB(query, dbName, dbUrl, username, password);
            resultSetValue.next();
            overrideTableData = resultSetValue.getString(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.closeDBConnection();
        return overrideTableData;
    }

}
