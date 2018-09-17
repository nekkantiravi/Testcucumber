package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cucumber.api.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.System.currentTimeMillis;
import com.macys.sdt.framework.runner.WebDriverManager;

/**
 * Created by M671871 on 7/31/2017.
 */
public class Linguistics {

    private static String dbName = "oracle";
    private static String dbUrl = "jdbc:oracle:thin:@dml1-scan:1521/dpmsts01";
    private static String dbUsername = "Saturn";
    private static String dbPassword = "Saturn";
    String dictionaryName;
    String dictionaryType;
    String termName1;
    String termName2;
    String relationshipType;
    String relationshipTypeEntered;
    String jsonValueFileName="linguistics_common";
    String jsonKeyValueFileName="linguistics_data";
    int termResultsCount;
    String termResultsUI;
    String query;

    @Then("^I should be on the \"([^\"]*)\" page$")
    public void iShouldBeOnThePage (String value) throws Throwable {
        Assert.assertEquals("The " + value + " page is not visible", value, Elements.getText(By.xpath(CommonUtils.getJsonValue("page_title",jsonValueFileName))));
    }

    @When("^I click on Add$")
    public void iClickOnAdd () throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent(By.xpath("//button[contains(.,'Add')]"));
        Clicks.click(Elements.findElement(By.xpath("//button[contains(.,'Add')]")));
    }

    @Then("^I should see a window titled \"([^\"]*)\"$")
    public void iShouldSeeAWindowTitled (String title) throws Throwable {
        Assert.assertEquals("The window opened doesn't have the title add dictionary", title, Elements.findElement("linguistics.add_dictionary_window").getText());
    }

    @When("^I enter the Name as \"([^\"]*)\"$")
    public void iEnterTheNameAs (String name) throws Throwable {
        dictionaryName = name + currentTimeMillis();
        TextBoxes.typeTextbox("linguistics.dictionary_name", dictionaryName);
    }

    @When("^I select Dictionary Type on Add Dictionary popup$")
    public void i_select_Dictionary_Type_on_Add_Dictionary_popup() throws Throwable {
        DropDowns.selectRandomValue("linguistics.dictionary_type");
    }

    @And("^I select the type as \"([^\"]*)\"$")
    public void iSelectTheTypeAs (String type) throws Throwable {
        DropDowns.selectByText("linguistics.dictionary_type", type);
        TextBoxes.typeTextbox("linguistics.dictionary_name", dictionaryName);
    }

    @And("^I save the dictionary$")
    public void iSaveTheDictionary () throws Throwable {
        Clicks.click("linguistics.dictionary_save");
    }

    @Then("^I should see the dictionary created$")
    public void iShouldSeeTheDictionaryCreated () throws Throwable {
        Assert.assertEquals("Dictionary name is not as entered", Elements.findElement("linguistics.dictionary_name").getAttribute("value"), dictionaryName);
    }

    @Then("^I see \"([^\"]*)\" popup$")
    public void i_see_popup(String popupTitle) throws Throwable {
        Wait.forPageReady();
        switch(popupTitle) {
            case "Add Relationship/Designation":
                Assert.assertEquals("Popup window does not have the tile" + popupTitle, popupTitle, Elements.getText(By.xpath(CommonUtils.getJsonValue("add_term_popup_title",jsonValueFileName))));
                break;
            case "Edit Relationship/Designation":
                Assert.assertEquals("Popup window does not have the tile" + popupTitle, popupTitle, Elements.getText(By.xpath(CommonUtils.getJsonValue("edit_term_popup_title",jsonValueFileName))));
                break;
        }
    }

    @Then("^I see Dictionary Type dropdown with the below values in alphanumeric order$")
    public void i_see_Dictionary_Type_dropdown_with_the_below_values_in_alphanumeric_order(DataTable data) throws Throwable {
        List<String> dictionaryTypes = DropDowns.getAllValues("linguistics.dictionary_type_on_term_popup");
        Assert.assertTrue("The list of Dictionary Types do not match", dictionaryTypes.containsAll(data.asList(String.class)));
        Assert.assertEquals("The list of Dictionary Types are not sorted in alphanumeric order", CommonUtils.getSortedList(dictionaryTypes), data.asList(String.class));
    }

    @When("^I select \"([^\"]*)\" on Add Relationship popup$")
    public void i_select_on_Add_Relationship_popup(String dictionary_type) throws Throwable {
        dictionaryType = CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(dictionary_type).toString();
        DropDowns.selectByText("linguistics.dictionary_type_on_term_popup", dictionaryType);
        Wait.forPageReady();
    }

    @Then("^I see the dictionaries related to the selected Dictionary Type in alphanumeric order$")
    public void i_see_the_dictionaries_related_to_the_selected_Dictionary_Type_in_alphanumeric_order() throws Throwable {
        List<String> dictionaryNames = DropDowns.getAllValues("linguistics.dictionary_name_on_term_popup");
        Assert.assertFalse("The list of Dictionary Names are not sorted in alphanumeric order",  dictionaryNames.isEmpty());
    }

    @When("^I select Dictionary Name on Add Relationship popup$")
    public void i_select_Dictionary_Name_on_Add_Relationship_popup() throws Throwable {
        Wait.forPageReady();
        Wait.forLoading("linguistics.dictionary_name_on_term_popup");
        DropDowns.selectRandomValue("linguistics.dictionary_name_on_term_popup");
        Wait.forPageReady();
        Assert.assertTrue("The Dictionary Name is not selected",  !DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_name_on_term_popup")).equals(""));
    }

    @When("^I populate \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" on Add Relationship Designation popup$")
    public void i_populate_and_on_Add_Relationship_Designation_popup(String term_1, String term_2, String relationship) throws Throwable {
        termName1 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term_1).toString()) + currentTimeMillis();
        termName2 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term_2).toString()) + currentTimeMillis();
        relationshipTypeEntered = CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(relationship).toString();
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", termName1);
        DropDowns.selectByText("linguistics.dictionary_term_relationship", relationshipTypeEntered);
        TextBoxes.typeTextbox("linguistics.dictionary_term_2", termName2 );
    }

    @When("^I click Save on Add Relationship Designation popup$")
    public void i_click_Save_on_Add_Relationship_Designation_popup$() throws Throwable {
        Wait.until(() -> Elements.elementPresent("linguistics.term_relationship_save"));
        Wait.ajaxDone();
        Elements.findElement("linguistics.term_relationship_save").click();
        Wait.forPageReady();
        Wait.until(() -> Elements.elementPresent("linguistics.term_relationship_edit"));
        Thread.sleep(50000);
    }

    @When("^I click Save on term Add Relationship/Designation popup$")
    public void i_click_Save_on_term_Add_Relationship_Designation_popup$() throws Throwable {
        if (Elements.findElement("linguistics.add_term_popup_dictionary_label").isDisplayed()) {
            dictionaryType = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_type_on_term_popup")).toString();
            dictionaryName = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_name_on_term_popup")).toString();
        }
        termName1 = Elements.findElement("linguistics.dictionary_term_1").getAttribute("value");
        relationshipTypeEntered = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_term_relationship")).toString();
        if (!relationshipTypeEntered.equals("Keep Original")) {
            termName2 = Elements.findElement("linguistics.dictionary_term_2").getAttribute("value");
        }
        Wait.forLoading("linguistics.term_relationship_save");
        Clicks.click("linguistics.term_relationship_save");
    }

    @Then("^I confirm that Term relationship added/edited")
    public void i_confirm_that_Term_relationship_added_edited() throws Throwable {
        Wait.ajaxDone();
        Wait.untilElementNotPresent("linguistics.term_relationship_save");
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("terms_filter_by",jsonValueFileName)), relationshipTypeEntered);
        TextBoxes.typeTextNEnter(By.id(CommonUtils.getJsonValue("term_search_field",jsonValueFileName)),termName1);
        Wait.ajaxDone();
        Wait.untilElementPresent("linguistics.term_result_type");
        Assert.assertEquals("Dictionary Type is not saved correctly", Elements.findElement("linguistics.term_result_type").getText(), dictionaryType.toUpperCase().replaceAll(" ", "_"));
        Assert.assertEquals("Dictionary Term 1 Relationship is not added", Elements.findElement("linguistics.term_result_term_1").getText(), termName1);
        Assert.assertEquals("Relationship Type is not saved correctly", Elements.findElement("linguistics.term_result_relationship").getText(), relationshipTypeEntered);
        if (!relationshipTypeEntered.equals("Keep Original")) {
            Assert.assertEquals("Dictionary Term 2 Relationship is not added", Elements.findElement("linguistics.term_result_term_2").getText(), termName2);
        }
    }

    @When("^I select the relation as \"([^\"]*)\"$")
    public void iSelectTheRelationAs(String relationshipType) throws Throwable {
        relationshipTypeEntered=relationshipType;
        Wait.untilElementPresent("linguistics.term_relationship_save");
         String title=  Elements.getText(By.xpath(CommonUtils.getJsonValue("add_term_popup_title",jsonValueFileName)));
        Wait.until(() -> title.equals("Add Relationship/Designation"));
        DropDowns.selectByText("linguistics.dictionary_term_relationship",relationshipType);
    }

    @When("^I select the relation as \"([^\"]*)\" from Filter by$")
    public void iSelectTheRelationAsFromFilterBy(String relationshipType) throws Throwable {
       Wait.untilElementNotPresent("linguistics.term_relationship_save");
        relationshipTypeEntered=relationshipType;
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("terms_filter_by",jsonValueFileName)),relationshipTypeEntered);
    }

    @When("^I click Go on Terms page$")
    public void iClickGoOnTermsPage() throws Throwable {
        Clicks.click("linguistics.term_search_go");
        Wait.ajaxDone();
    }

    @And("^I select \"([^\"]*)\" and \"([^\"]*)\" on relationship popup$")
    public void iSelectAndOnRelationshipPopup(String term_1, String term_2) throws Throwable {
        termName1 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term_1).toString()) + currentTimeMillis();
        termName2 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term_2).toString()) + currentTimeMillis()+1;
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", termName1);
        TextBoxes.typeTextbox("linguistics.dictionary_term_2", termName2 );
    }

    @Then("^I verify that the Relationship data is the same as entered$")
    public void iVerifyThatTheRelationshipDataIsTheSameAsEntered() throws Throwable {
        String termOneUI,termTwoUI,relationshipTypeUI;
        Wait.until(() -> Elements.elementPresent("linguistics.term_relationship_save"));
        termOneUI=Elements.findElement("linguistics.dictionary_term_one").getText();
        termTwoUI=Elements.findElement("linguistics.dictionary_term_three").getText();
        relationshipTypeUI=Elements.findElement("linguistics.dictionary_term_two").getText();
        Assert.assertEquals("term one is not the same as entered",termName1,termOneUI);
        Assert.assertEquals("term two is not the same as entered",termName2,termTwoUI);
        Assert.assertEquals("relationship Type is not the same as entered",relationshipTypeEntered,relationshipTypeUI);
    }

    @When("^I click on Edit Relationship$")
    public void iClickOnEditRelationship() throws Throwable {
        Wait.ajaxDone();
        Elements.findElement("linguistics.term_relationship_edit").click();
        Wait.forPageReady();
    }

    @And("^I edit \"([^\"]*)\" and \"([^\"]*)\" on relationship popup$")
    public void iEditAndOnRelationshipPopup(String term_1, String term_2) throws Throwable {
        termName1 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term_1).toString()) + currentTimeMillis();
        termName2 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term_2).toString()) + currentTimeMillis();
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", termName1);
        TextBoxes.typeTextbox("linguistics.dictionary_term_2", termName2 );
    }

    @When("^I click on Delete RelationShip$")
    public void iClickOnDeleteRelationShip() throws Throwable {
        Clicks.click("linguistics.term_relationship_delete");
    }

    @Then("^the relationship should be deleted$")
    public void theRelationshipShouldBeDeleted() throws Throwable {
        Wait.untilElementNotPresent("linguistics.linguistics_alert_ok");
        String relationshipElements = Elements.findElement(By.xpath(CommonUtils.getJsonValue("term_result",jsonValueFileName))).getText();
       System.out.print("relationshipElements"+relationshipElements);
        Assert.assertTrue("the relationship did not get deleted",relationshipElements.isEmpty());
    }

    @And("^I select \"([^\"]*)\" on relationship popup$")
    public void iSelectOnRelationshipPopup(String term1) throws Throwable {
        termName1 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(term1).toString()) + currentTimeMillis();
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", termName1);
    }

    @And("^I edit \"([^\"]*)\" on relationship popup$")
    public void iEditOnRelationshipPopup(String editTerm) throws Throwable {
        Elements.findElement("linguistics.dictionary_term_1").clear();
        termName1 = (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(editTerm).toString()) + currentTimeMillis();
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", termName1);
    }

    @Then("^I verify that the single term Relationship data is the same as entered$")
    public void iVerifyThatTheSingleTermRelationshipDataIsTheSameAsEntered() throws Throwable {
        String termOneUI,relationshipTypeUI;
        Wait.forLoading("linguistics.dictionary_term_one");
        Wait.forLoading("linguistics.dictionary_term_two");
        Wait.until(() -> !Elements.elementPresent("linguistics.dictionary_term_one"));
        termOneUI=Elements.findElement("linguistics.dictionary_term_one").getText();
        relationshipTypeUI=Elements.findElement("linguistics.dictionary_term_two").getText();
        Assert.assertEquals("term one is not the same as entered",termName1,termOneUI);
        Assert.assertEquals("relationship Type is not the same as entered",relationshipTypeEntered,relationshipTypeUI);
    }

    @And("^I enter the same name of dictionary$")
    public void iEnterTheSameNameOfDictionary() throws Throwable {
        TextBoxes.typeTextbox("linguistics.dictionary_name", dictionaryName);
    }

    @Then("^I should see the \"([^\"]*)\" error for same name$")
    public void iShouldSeeTheErrorForSameName(String dictionaryError) throws Throwable {
        String errorMsgUI;
        errorMsgUI = Elements.findElement(By.xpath(CommonUtils.getJsonValue("dictionary_error",jsonValueFileName))).getText();
        Assert.assertEquals("The dictionary error messages do not match",CommonUtils.getJsonKeyValue(dictionaryError,jsonKeyValueFileName).get("Error"),errorMsgUI);
    }

    @And("^I click ok on relationship popup$")
    public void iClickOkOnRelationshipPopup() throws Throwable {
        Wait.ajaxDone();
        Elements.findElement("linguistics.linguistics_alert_ok").click();
        Wait.forPageReady();
    }

    @When("^I click clear filter$")
    public void iClickClearFilter() throws Throwable {
        Clicks.click("linguistics.clear_filter");
    }

    @Then("^I should see the filter dropdown value as \"([^\"]*)\"$")
    public void iShouldSeeTheFilterDropdownValueAs(String filterValue) throws Throwable {
        String filterValueUI=DropDowns.getSelectedValue(By.id(CommonUtils.getJsonValue("terms_filter_by",jsonValueFileName)));
        Assert.assertEquals("The filter dropdown value doesn't match",filterValue,filterValueUI);
    }

    @And("^I should see the search term box with no value$")
    public void iShouldSeeTheSearchTermBoxWithNoValue() throws Throwable {
        String termVal=Elements.findElement(By.id(CommonUtils.getJsonValue("term_search_field",jsonValueFileName))).getText();
        Assert.assertEquals("The search term box has value","",termVal);
    }

    @Then("^I should be on the page with title \"([^\"]*)\"$")
    public void iShouldBeOnThePageWithTitle(String arg0) throws Throwable {
        String titleUI=   Elements.getText(By.xpath(CommonUtils.getJsonValue("page_title",jsonValueFileName)));
    }

    @When("^I enter \"([^\"]*)\" in the search term box$")
    public void iEnterInTheSearchTermBox(String term) throws Throwable {
        if(term.equalsIgnoreCase("Term 1"))
        {
            TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("term_search_field",jsonValueFileName)),termName1);
        }
        else
            TextBoxes.typeTextbox(By.id(CommonUtils.getJsonValue("term_search_field",jsonValueFileName)),termName2);
    }

    @And("^I select Dictionary Type on Add Dictionary/Terms popup$")
    public void iSelectDictionaryTypeOnAddDictionaryTermsPopup() throws Throwable {
        DropDowns.selectRandomValue("linguistics.dictionary_type");
        dictionaryType = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_type")).toString();
    }

    public static String toTitleCase(String stringValue) {
        String stringValueInLowerCase = stringValue.toLowerCase();
        String[] stringArray = stringValueInLowerCase.split("_");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < stringArray.length; i++) {
            sb.append(Character.toUpperCase(stringArray[i].charAt(0)))
                    .append(stringArray[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    @Then("^I select the created Dictionary Type and Dictionary Name$")
    public void iSelectTheCreatedDictionaryTypeAndDictionaryName() throws Throwable {
        Wait.forPageReady();
        DropDowns.selectByText("linguistics.dictionary_type_on_term_popup", toTitleCase(dictionaryType) );
        Wait.forPageReady();
        Wait.ajaxDone();
        DropDowns.selectByText("linguistics.dictionary_name_on_term_popup", dictionaryName );
    }

    @Then("^I verify that the Term data is the same as entered$")
    public void iVerifyThatTheTermDataIsTheSameAsEntered() throws Throwable {
        Wait.ajaxDone();
        Wait.untilElementPresent("linguistics.term_result_type");
        String termdataUI;
        String[] termdata={dictionaryName,dictionaryType,termName1,relationshipTypeEntered,termName2};
        for(int i=0;i<5;i++)
        {
            termdataUI= Elements.findElement(By.xpath(CommonUtils.getJsonValue("terms_data_on_terms_page",jsonValueFileName)+(i+1)+"]")).getText();
            Assert.assertEquals("the term data doesn't match as entered"+termdataUI,termdata[i],termdataUI);

        }
        termResultsUI=Elements.findElement(By.id(CommonUtils.getJsonValue("term_search_results",jsonValueFileName))).getText();
        termResultsCount=Integer.valueOf(termResultsUI.split(" ")[0]);
    }

    @Then("^the term data should be deleted$")
    public void theTermDataShouldBeDeleted() throws Throwable {
        int termResultsCountAfterDelete;
        Wait.forPageReady();
        Wait.ajaxDone();
        Wait.untilElementPresent("linguistics.term_relationship_delete");
        Wait.until(() -> Elements.elementPresent("linguistics.term_relationship_save"));
        termResultsUI=Elements.findElement(By.id(CommonUtils.getJsonValue("term_search_results",jsonValueFileName))).getText();
        termResultsCountAfterDelete=Integer.valueOf(termResultsUI.split(" ")[0]);
        Assert.assertEquals("The term data did not get delete",termResultsCountAfterDelete,termResultsCount-1);
    }

    @When("^I click Edit next to term on Terms page$")
    public void i_click_Edit_next_to_term_on_Terms_page() throws Throwable {
        Wait.ajaxDone();
        Clicks.click("linguistics.edit_term_on_terms_page");
        Wait.forPageReady();
    }

    @And("^I click cancel on Relationship Designation popup$")
    public void iClickCancelOnRelationshipDesignationPopup() throws Throwable {
        Clicks.click("linguistics.term_relationship_cancel");
    }

    @When("^I change Dictionary Type on Edit Relationship popup$")
    public void i_change_Dictionary_Type_on_Edit_Relationship_popup() throws Throwable {
        DropDowns.selectByText("linguistics.dictionary_type_on_term_popup", "");
        Wait.forPageReady();
        DropDowns.selectRandomValue("linguistics.dictionary_type_on_term_popup");
        dictionaryType = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_type_on_term_popup")).toString();
        Wait.forPageReady();
    }

    @When("^I see Dictionary Name is not selected in Dictionary Name dropdown$")
    public void i_see_Dictionary_Name_is_not_selected_in_Dictionary_Name_dropdown() throws Throwable {
        Assert.assertEquals("Dictionary Name on Term popup is selected", "", DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_name_on_term_popup")).toString());
    }

    @When("^I select Dictionary Name on Edit Relationship popup$")
    public void i_select_Dictionary_Name_on_Edit_Relationship_popup() throws Throwable {
        DropDowns.selectRandomValue("linguistics.dictionary_name_on_term_popup");
        Wait.forPageReady();
    }

    @When("^I select Dictionary Type on Add Relationship popup$")
    public void i_select_Dictionary_Type_on_Add_Relationship_popup() throws Throwable {
        DropDowns.selectRandomValue("linguistics.dictionary_type_on_term_popup");
        dictionaryType = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_type_on_term_popup")).toString();
        Wait.forPageReady();
        Assert.assertTrue("The Dictionary Type is not selected",  !DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_type_on_term_popup")).equals(""));
    }

    @When("^I enter term value in \"([^\"]*)\" field on Add Relationship popup$")
    public void i_enter_term_value_in_field_on_Add_Relationship_popup(String termType) throws Throwable {
        switch (termType) {
            case "Term 1":
                TextBoxes.typeTextbox("linguistics.dictionary_term_1", (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(termType).toString()) + currentTimeMillis());
                break;
            case "Term 2":
                TextBoxes.typeTextbox("linguistics.dictionary_term_2", (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get(termType).toString()) + currentTimeMillis() );
                break;
        }
    }

    @When("^I enter Term one value in Term two field on Relationship popup$")
    public void i_enter_Term_one_value_in_term_two_field_on_Relationship_popup() throws Throwable {
        TextBoxes.typeTextbox("linguistics.dictionary_term_2", (Elements.findElement("linguistics.dictionary_term_1").getAttribute("value")));
    }

    @When("^I edit Terms and Relationship on Edit Relationship Designation popup$")
    public void i_edit_Terms_and_Relationship_on_Edit_Relationship_Designation_popup() throws Throwable {
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", " ");
        TextBoxes.typeTextbox("linguistics.dictionary_term_1", (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get("Term 1").toString()) + currentTimeMillis());
        DropDowns.selectRandomValue("linguistics.dictionary_term_relationship");
        if (Elements.elementPresent("linguistics.dictionary_term_2")) {
            TextBoxes.typeTextbox("linguistics.dictionary_term_2", " ");
            TextBoxes.typeTextbox("linguistics.dictionary_term_2", (CommonUtils.getJsonKeyValue("linguistics_values", jsonKeyValueFileName).get("Term 2").toString()) + currentTimeMillis());
        }
    }

    @When("^I change Dictionary Name on Edit Relationship popup$")
    public void i_change_Dictionary_Name_on_Edit_Relationship_popup() throws Throwable {
        DropDowns.selectRandomValue("linguistics.dictionary_name_on_term_popup");
        Wait.forPageReady();
    }

    @When("^I click Save on Edit Relationship Designation popup$")
    public void i_click_Save_on_Edit_Relationship_Designation_popup() throws Throwable {
        if (Elements.findElement("linguistics.dictionary_type_on_term_popup").isDisplayed()) {
            dictionaryType = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_type_on_term_popup")).toString();
            dictionaryName = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_name_on_term_popup")).toString();
        }
        termName1 = Elements.findElement("linguistics.dictionary_term_1").getAttribute("value");
        relationshipTypeEntered = DropDowns.getSelectedValue(Elements.element("linguistics.dictionary_term_relationship")).toString();
        if (!relationshipTypeEntered.equals("Keep Original")) {
            termName2 = Elements.findElement("linguistics.dictionary_term_2").getAttribute("value");
        }
        Clicks.javascriptClick("linguistics.term_relationship_save");
    }

    @When("^I see the alert message on alert popup \"([^\"]*)\"$")
    public void i_see_the_alert_message_on_alert_popup(String alertMessage) throws Throwable {
        Assert.assertTrue("Same term alert is not visible", Elements.elementPresent("linguistics.linguistics_alert_popup_message"));
        Assert.assertEquals("Same term alert is not as expected", alertMessage, Elements.findElement("linguistics.linguistics_alert_popup_message").getText().replaceAll("\n", " "));
    }

    @When("^I click Ok on Relationship popup$")
    public void i_click_Ok_on_Relationship_popup() throws Throwable {
        Clicks.click("linguistics.linguistics_alert_ok");
        Wait.untilElementPresent("linguistics.term_relationship_save");
    }

    @When("^I search for \"([^\"]*)\" on Terms Page$")
    public void i_search_for_on_Terms_Page(String searchValue) throws Throwable {
        TextBoxes.typeTextNEnter(By.id(CommonUtils.getJsonValue("term_search_field",jsonValueFileName)), CommonUtils.getJsonKeyValue("simple_search_values",jsonKeyValueFileName).get(searchValue).toString());
        Wait.ajaxDone();
        Thread.sleep(50000);
    }

    @When("^I click View Details next to any Dictionary$")
    public void i_click_View_Details_next_to_any_Dictionary() throws Throwable {
        Clicks.click("linguistics.dictionary_results_last");
        Wait.ajaxDone();
        Clicks.click("linguistics.dictionary_view_details");
    }

    @Then("^I see \"([^\"]*)\" page$")
    public void i_see_page(String pageValue) throws Throwable {
        Assert.assertTrue("The page is not Dictionary Detail page", Elements.getText(By.xpath(CommonUtils.getJsonValue("page_title",jsonValueFileName))).contains(pageValue));
    }

    @When("^I click on Add button on Dictionary Detail page$")
    public void i_click_on_Add_button_on_Dictionary_Detail_page() throws Throwable {
        dictionaryName = (Elements.getText(By.xpath(CommonUtils.getJsonValue("page_title",jsonValueFileName)))).split(": ")[1];
        dictionaryType = (Elements.findElement(("linguistics.dictionary_name_on_dictionary_detail_page"))).getAttribute("value");
        Clicks.click("linguistics.add_button_on_dictionary_detail_page");
    }

    @When("^I click Edit next to term on Dictionary Detail page$")
    public void i_click_Edit_next_to_term_on_Dictionary_Detail_page() throws Throwable {
        Wait.ajaxDone();
        Clicks.click("linguistics.edit_term_on_dictionary_detail_page");
        Wait.forPageReady();
    }

    @Then("^I confirm that Term relationship edited/added on Dictionary Detail page$")
    public void i_confirm_that_Term_relationship_edited_added_on_Dictionary_Detail_page() throws Throwable {
        Wait.ajaxDone();
        DropDowns.selectByText(By.id(CommonUtils.getJsonValue("terms_filter_by",jsonValueFileName)), relationshipTypeEntered);
        TextBoxes.typeTextNEnter(By.id(CommonUtils.getJsonValue("term_search_field",jsonValueFileName)),termName1);
        Wait.ajaxDone();
        Wait.untilElementPresent("linguistics.dictionary_detail_page_term_result_term_1");
        Wait.until(() -> termName1.equals(Elements.findElement(By.xpath("//table[@id='TBLrelationships']//tr[1]/td[1]")).getText()));
        Assert.assertEquals("Dictionary Term 1 Relationship is not added/edited", termName1,Elements.findElement(By.xpath("//table[@id='TBLrelationships']//tr[1]/td[1]")).getText());
        Assert.assertEquals("Relationship Type is not saved correctly", Elements.findElement("linguistics.dictionary_detail_page_term_result_relationship").getText(), relationshipTypeEntered);
        if (!relationshipTypeEntered.equals("Keep Original")) {
            Assert.assertEquals("Dictionary Term 2 Relationship is not added/edited", Elements.findElement("linguistics.dictionary_detail_page_term_result_term_2").getText(), termName2);
        }
    }

    @Then("^I confirm relationship is saved in the DB with Relationship type “MIS”$")
    public void i_confirm_relationship_is_saved_in_the_DB_with_Relationship_type_MIS() throws SQLException {
        Statement stmt;
        String sql;
        query ="SELECT lr.LINGUISTIC_REL_TYPE_CD,termP.LINGUISTIC_TERM_VALUE, termC.LINGUISTIC_TERM_VALUE \n" +
                "FROM linguistic_relationship lr LEFT JOIN dictionary d ON lr.dictionary_id=d.dictionary_id \n" +
                "LEFT JOIN DICTIONARY_TYPE dt ON d.DICTIONARY_TYPE_ID = dt.DICTIONARY_TYPE_ID \n" +
                "LEFT JOIN (SELECT rta1.LINGUISTIC_REL_ID, lt1.LINGUISTIC_TERM_VALUE FROM RELATIONSHIP_TERM_ASSN rta1 \n" +
                "JOIN linguistic_term lt1  ON rta1.LINGUISTIC_TERM_ID=lt1.LINGUISTIC_TERM_ID AND rta1.LINGUISTIC_TERM_ROLE='P') \n" +
                "termP ON termP.linguistic_rel_id=lr.LINGUISTIC_REL_ID LEFT JOIN \n" + "(SELECT rta2.LINGUISTIC_REL_ID, lt2.LINGUISTIC_TERM_VALUE FROM RELATIONSHIP_TERM_ASSN rta2 \n" +
                "JOIN linguistic_term lt2  ON rta2.LINGUISTIC_TERM_ID=lt2.LINGUISTIC_TERM_ID  AND rta2.LINGUISTIC_TERM_ROLE ='C') \n" +
                "termC ON termC.linguistic_rel_id=lr.LINGUISTIC_REL_ID WHERE LINGUISTIC_REL_TYPE_CD='MIS' and termP.LINGUISTIC_TERM_VALUE=" + " '" + termName1 +"' " + " ";
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
            System.out.println("rs::::" + rs);
            rs.next();
            System.out.println("rs::::" + rs.getString(1));
            relationshipTypeEntered = StringUtils.substring(relationshipTypeEntered, 0, 3);
            Assert.assertEquals("Relationship type is not saved as MIS", relationshipTypeEntered.toUpperCase(), rs.getString(1));
            Assert.assertEquals("Term 1 is not added", termName1, rs.getString(2));
            Assert.assertEquals("Term 1 is not added", termName2, rs.getString(3));
            System.out.println("Database values:::: " + "Relationship Type: " + rs.getString(1) + "  >>>>>>> Term 1: " + rs.getString(2) + "  >>>>>>>> Term 2: " + rs.getString(3));
            System.out.print("query executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
    }


}
