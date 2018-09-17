package com.macys.sdt.projects.Stores.STAT.steps.website;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.macys.sdt.projects.Stores.STAT.utils.STATUtils.typeTextNTab;



public class Giftvendorbonus extends StepUtils {

    @Then("^I click the Create Request link$")
    public void iClickCreateRequestlink() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.create_request_link");
        Clicks.click("giftvendorbonus.create_request_link");
    }

    @Then("^I should see close button$")
    public void iShouldSeeCloseButton() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.create_request_close");
    }

    @Then("^I should see \"([^\"]*)\" as title$")
    public void iShouldSeeAsTitle(String TitleName) throws Throwable {
        switch (TitleName) {
            case "Vendor Bonus Program":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusTitle");
                Assert.assertEquals(TitleName, Elements.findElement("giftvendorbonus.vendorBonusTitle").getText());
                break;
            case "Vendor Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTitle");
                Assert.assertEquals(TitleName, Elements.findElement("giftvendorbonus.vendorBonusRequestTitle").getText());
                break;
        }
    }

    @Then("^I should see \"([^\"]*)\" accordion section$")
    public void iShouldSeeAccordionSection(String Name) throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.create_request_accordion");
        Assert.assertEquals(Name, Elements.findElement("giftvendorbonus.create_request_accordion").getText());
//        Thread.sleep(10000);
    }

    @Then("^I should see Registry ID input field$")
    public void iShouldSeeRegistryIDInputField() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRegistrySearchInput");
    }

    @Then("^I should see Search button is \"([^\"]*)\"$")
    public void iShouldSeeSearchButtonState(String State) throws Throwable {
        switch (State) {
            case "active":
                //vendorBonusRegistrySearchButton
                Elements.findElement("giftvendorbonus.vendorBonusRegistrySearchButton").isEnabled();
                break;
            case "inactive":
                String cssValue;
                cssValue = Elements.findElement("giftvendorbonus.vendorBonusRegistrySearchButton").getCssValue("cursor");
                Assert.assertEquals("not-allowed", cssValue);
                break;
        }
    }

    @When("^I type \"([^\"]*)\" in Registry ID field$")
    public void iTypeTextInRegistryIDField(String Text) throws Throwable {
        typeTextNTab("giftvendorbonus.vendorBonusRegistrySearchInput", Text);
    }

    @And("^I should see \"([^\"]*)\" as the message$")
    public void iShouldSeeTheMessage(String Text) throws Throwable {
        String actual = "";
        if (!Text.equals("")) {
            Elements.elementShouldBePresent("giftvendorbonus.vendorBonusSearchErrorMessage");
            actual = Elements.findElement("giftvendorbonus.vendorBonusSearchErrorMessage").getText();
        }
        Assert.assertEquals(Text, actual);
    }

    @And("^I click search$")
    public void iClickSearch() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRegistrySearchButton");
        Clicks.click("giftvendorbonus.vendorBonusRegistrySearchButton");
    }

    @Then("^I see an accordion view opened of the registrants information$")
    public void iSeeAnAccordionViewOpenedOfTheRegistrantsInformation() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.reg_accordion");
        Elements.elementShouldBePresent("giftvendorbonus.reg_firstname");
        Elements.elementShouldBePresent("giftvendorbonus.reg_lastname");
        Elements.elementShouldBePresent("giftvendorbonus.reg_attention");
        Elements.elementShouldBePresent("giftvendorbonus.reg_address");
        Elements.elementShouldBePresent("giftvendorbonus.reg_address2");
        Elements.elementShouldBePresent("giftvendorbonus.reg_city");
        Elements.elementShouldBePresent("giftvendorbonus.reg_zip");
        Elements.elementShouldBePresent("giftvendorbonus.reg_homephone");
        Elements.elementShouldBePresent("giftvendorbonus.reg_workphone");
        Elements.elementShouldBePresent("giftvendorbonus.reg_extension");
        Elements.elementShouldBePresent("giftvendorbonus.reg_email");
    }

    @Then("^I should see an accordion view opened of the registrants information$")
    public void iShouldSeeAnAccordionViewOpenedOfTheRegistrantsInformation() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.reg_accordion");
        Elements.elementShouldBePresent("giftvendorbonus.reg_firstname");
        Elements.elementShouldBePresent("giftvendorbonus.reg_lastname");
        Elements.elementShouldBePresent("giftvendorbonus.reg_address_type");
        Elements.elementShouldBePresent("giftvendorbonus.reg_attention");
        Elements.elementShouldBePresent("giftvendorbonus.reg_address");
        Elements.elementShouldBePresent("giftvendorbonus.reg_address2");
        Elements.elementShouldBePresent("giftvendorbonus.reg_city");
        Elements.elementShouldBePresent("giftvendorbonus.reg_state");
        Elements.elementShouldBePresent("giftvendorbonus.reg_country");
        Elements.elementShouldBePresent("giftvendorbonus.reg_zip");
        Elements.elementShouldBePresent("giftvendorbonus.reg_homephone");
        Elements.elementShouldBePresent("giftvendorbonus.reg_workphone");
        Elements.elementShouldBePresent("giftvendorbonus.reg_extension");
        Elements.elementShouldBePresent("giftvendorbonus.reg_email");
        Thread.sleep(5000);
    }

    @When("^I click on the accordion$")
    public void iClickOnTheAccordion() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.reg_accordion");
        Clicks.click("giftvendorbonus.reg_accordion");
        Thread.sleep(5000);
    }

    @Then("^I should see the accordion view close$")
    public void iShouldSeeTheAccordionViewClose() throws Throwable {
        Wait.untilElementNotPresent("giftvendorbonus.reg_firstname");
    }

    @And("^I should see the fields are not editable$")
    public void iAttemptToEditTheFields() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.reg_firstname");
//        Elements.findElement("giftvendorbonus.reg_firstname").getCssValue("disabled").contains("disabled");
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_firstname").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_lastname").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_address_type").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_attention").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_address").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_address2").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_city").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_state").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_country").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_zip").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_homephone").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_workphone").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_extension").getAttribute("disabled").equals("true"));
        Assert.assertTrue("Field was edittable", Elements.findElement("giftvendorbonus.reg_email").getAttribute("disabled").equals("true"));

    }

    @Then("^I should see that they are not editable$")
    public void iShouldSeeThatTheyAreNotEditable() throws Throwable {


    }

    @And("^I should see the Bonus Program accordion is open$")
    public void iShouldSeeTheBonusProgramAccordionIsOpen() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.bonus_accordion");
        Elements.elementShouldBePresent("giftvendorbonus.vendor_list");
        Elements.elementShouldBePresent("giftvendorbonus.save_button");
    }

    @Then("^I should see the Save button is disabled$")
    public void iShouldSeeTheSaveButtonIsDisabled() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.save_button");
        Elements.findElement("giftvendorbonus.save_button").getCssValue("disabled").contains("disabled");
    }

    @When("^I input a registry number (\\d+)$")
    public void iInputARegistryNumber(String number) {
        typeTextNTab("gift.gift_registry_search", number);
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRegistrySearchInput");

    }

    @And("^I should see \"([^\"]*)\" Table$")
    public void iShouldSeeTable(String tableName) throws Throwable {
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iShouldSeeTable: no implementation for table: [" + tableName + "]");
        }
    }

    @And("^I should see \"([^\"]*)\" Table Header is \"([^\"]*)\"$")
    public void iShouldSeeCorrectTableHeader(String tableName, String input) throws Throwable {
        List<String> expected;
        List<WebElement> headerList = null;
        expected = Arrays.asList(input.split(","));

        // get table header name list
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                headerList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Head").findElements(new By.ByTagName("th"));
                Assert.assertEquals("gen expected vs list", expected.size(), headerList.size());
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iShouldSeeCorrectTableHeader: no implementation for table: [" + tableName + "]");
        }

        // verify that header column names are matching expected
        for (int i = 0; i < headerList.size(); i++) {
            Assert.assertEquals("Header test", headerList.get(i).getText().trim(), expected.get(i).trim());

        }

    }

    @When("^I click \"([^\"]*)\" in the \"([^\"]*)\" Table Header$")
    public void iClickTableHeader(String clickThis, String tableName) throws Throwable {
        List<WebElement> headerList = null;

        // get table header in list
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                headerList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Head").findElements(new By.ByTagName("th"));
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iClickTableHeader: no implementation for table: [" + tableName + "]");
        }

        // iterate trough elements and click on the correct column
        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().trim().equals(clickThis.trim())) {
                Clicks.click(headerList.get(i));
                break;
            }
        }

    }

    @Then("^I should see \"([^\"]*)\" Table data is ordered \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iShouldSeeTableDataOrderByColumn(String tableName, String orderHow, String columnName) throws Throwable {
        List<WebElement> headerList = null;
        List<WebElement> rows = null;
        Integer rowNumbers = 0;
        Integer rowLength = 0;
        Integer temp = 0;
        String[] columnData = {};
        String[] sortData = {};

        // get table header in list headerList and table size
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                headerList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Head").findElements(new By.ByTagName("th"));
                rowNumbers = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByTagName("tr")).size();
                rowLength = headerList.size();
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iClickTableHeader: no implementation for table: [" + tableName + "]");
        }

        // get table column data in list columnData
        for (int i = 0; i < rowLength; i++) {
            if (headerList.get(i).getText().trim().equals(columnName.trim())) {
                // get rows data
                rows = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByTagName("td"));
                temp = temp + i;
                columnData = new String[rowNumbers];
                columnData[0] = rows.get(temp).getText();
                for (int j = 1; j < rowNumbers; j++) {
                    temp = temp + rowLength;
                    columnData[j] = rows.get(temp).getText();
                }
                break;
            }
        }

        // Check to see sorting order based on orderHow
        sortData = columnData;
        orderHow = orderHow.toLowerCase();
        switch (orderHow) {
            case "ascending":
                Arrays.sort(sortData);
                Assert.assertArrayEquals(sortData, columnData);
                break;
            case "descending":
                Arrays.sort(sortData, Collections.reverseOrder());
                Assert.assertArrayEquals(sortData, columnData);
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iShouldSeeTableDataOrderByColumn: no implementation for sorting as: [" + orderHow + "]");
        }
    }

    @And("^I should see \"([^\"]*)\" Table information displayed is uppercase$")
    public void iShouldSeeInformationIsUppercase(String tableName) throws Throwable {
        List<WebElement> headerList = null;
        List<WebElement> bodyList = null;
        String rowText;

        // get table header name list
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                headerList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Head").findElements(new By.ByTagName("th"));
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                bodyList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByTagName("tr"));
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iShouldSeeInformationIsUppercase: no implementation for table: [" + tableName + "]");
        }

        // verify header is all uppercase
        for (WebElement aHeaderList : headerList) {
            Assert.assertEquals("Header test - uppercase", aHeaderList.getText().trim(), aHeaderList.getText().trim().toUpperCase());
        }

        // verify body is all uppercase
        for (WebElement aBodyList : bodyList) {
            rowText = aBodyList.getText().trim();
            switch (tableName) {
                case "Bonus Request": //ignore the last 4 characters which stand for "view"
                    rowText = rowText.replace("\n", "").replace("\r", "");
                    ;
                    rowText = rowText.replaceAll("null", "").replaceAll("NULL", ""); // remove silly null pop-up from DB
                    rowText = rowText.substring(0, rowText.length() - 4); // remove "view" (4 chars)
                    Assert.assertEquals("Body test - uppercase", rowText, rowText.toUpperCase());
                    break;
                default:
                    Assert.assertEquals("Body test - uppercase", rowText, rowText.toUpperCase());
            }
        }
    }

    @Then("^I should see the drop down box states 'Please make a selection'$")
    public void iShouldSeeTheDropDownBoxStatesPleaseMakeASelection() throws Throwable {

        Elements.elementShouldBePresent("giftvendorbonus.vendor_list");
        Thread.sleep(2000);
    }

    @When("^I select the drop down box$")
    public void iSelectTheDropDownBox() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendor_list");
        Clicks.click("giftvendorbonus.vendor_list");
    }

    @Then("^I should see the Save button is enabled$")
    public void iShouldSeeTheSaveButtonIsEnabled() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.save_button");
        Elements.findElement("giftvendorbonus.save_button").getCssValue("disabled").contains("enabled");
    }

    @Then("^I should see 'Please make a selection' displays with no vendors$")
    public void iShouldSeePleaseMakeASelectionDisplaysWithNoVendors() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendor_list");
        Clicks.click("giftvendorbonus.vendor_list");
        Elements.elementShouldBePresent("giftvendorbonus.please_make_selection");
        Thread.sleep(2000);
    }

    @Then("^I should see multiple vendors in the drop down list$")
    public void iShouldSeeMultipleVendorsInTheDropDownList() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.please_make_selection");
        Elements.elementShouldBePresent("giftvendorbonus.vendor_option2");
        Elements.elementShouldBePresent("giftvendorbonus.vendor_option4");
        Thread.sleep(2000);
    }

    @When("^I select the vendor$")
    public void iSelectTheVendor() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendor_option2");
        Clicks.click("giftvendorbonus.vendor_option2");
    }

    @Then("^I should see one vendors in the drop down list$")
    public void iShouldSeeOneVendorsInTheDropDownList() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.please_make_selection");
        Elements.elementShouldBePresent("giftvendorbonus.vendor_option2");
        Thread.sleep(2000);

    }

    @And("^I should see Bonus Request Table search field$")
    public void iShouldSeeBonusRequestTableSearch() throws Throwable {
        Assert.assertTrue(Elements.elementInView("giftvendorbonus.vendorBonusRequestTable_Search"));
    }

    @When("^I search the \"([^\"]*)\" Table with value from \"([^\"]*)\"$")
    public void iSearchTableWithRandomColumnValue(String tableName, String columnName) throws Throwable {
        List<WebElement> headerList = null;
        List<WebElement> rows = null;
        Integer rowNumbers = 0;
        Integer rowLength = 0;
        Integer temp = 0;
        String[] columnData = {};

        // get header list
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                headerList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Head").findElements(new By.ByTagName("th"));
                rowNumbers = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByTagName("tr")).size();
                rowLength = headerList.size();
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iSearchTableWithRandomColumnValue: no implementation for table: [" + tableName + "]");
        }
        // find column and get  column elements element values
        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().trim().equals(columnName.trim())) {
                // get rows data
                switch (tableName) {
                    case "Bonus Request":
                        rows = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByTagName("td"));
                        break;
                    default:
                        Assert.fail("Giftvendorbonus.java#iSearchTableWithRandomColumnValue: no implementation for column data for table: [" + tableName + "]");
                }

                temp = temp + i;
                columnData = new String[rowNumbers];
                columnData[0] = rows.get(temp).getText();
                for (int j = 1; j < rowNumbers; j++) {
                    temp = temp + rowLength;
                    columnData[j] = rows.get(temp).getText();
                }
            }
        }

        // type random value in search
        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Search");
                typeTextNTab("giftvendorbonus.vendorBonusRequestTable_Search", columnData[(new Random().nextInt(columnData.length))]);
                break;

            default:
                Assert.fail("Giftvendorbonus.java#iSearchTableWithRandomColumnValue: no implementation for column data for table: [" + tableName + "]");
        }

    }

    @Then("^I should see the \"([^\"]*)\" Table displays rows contain search value$")
    public void iShouldSeeRowsContainSearchValue(String tableName) throws Throwable {
        String searchValue = null;
        List<WebElement> bodyList = null;

        // get table body content by rows
        switch (tableName) {
            case "Bonus Request":
                searchValue = Elements.getElementAttribute("giftvendorbonus.vendorBonusRequestTable_Search", "value");
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                bodyList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByTagName("tr"));
                break;

            default:
                Assert.fail("Giftvendorbonus.java#iShouldSeeRowsContainSearchValue: no implementation for table: [" + tableName + "]");
        }

        // go trough each element in body content and see that search string is in the row
        for (WebElement aBodyList : bodyList) {

            System.out.println("This is the search value: " + searchValue);
            System.out.println("This is the body row value:" + aBodyList.getText().replaceAll("\r", "").replaceAll("\n", ""));
            Assert.assertTrue(aBodyList.getText().replaceAll("\r", "").replaceAll("\n", "").contains(searchValue));
        }
    }

    @And("^I should see \"([^\"]*)\" linked text in the \"([^\"]*)\" Table$")
    public void iShouldSeeLinkedTextInTable(String linkedText, String tableName) throws Throwable {
        List<WebElement> viewList = null;

        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                viewList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByLinkText(linkedText));
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iShouldSeeLinkedTextInTable: no implementation for table: [" + tableName + "]");
        }

        Assert.assertTrue(viewList.size() != 0);
    }

    @When("^I click the \"([^\"]*)\" linked text in the \"([^\"]*)\" Table$")
    public void iClickTheLinkedTextInTable(String linkedText, String tableName) throws Throwable {
        List<WebElement> viewList = null;

        switch (tableName) {
            case "Bonus Request":
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Head");
                Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Body");
                viewList = Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Body").findElements(new By.ByLinkText(linkedText));
                break;
            default:
                Assert.fail("Giftvendorbonus.java#iClickTheLinkedTextInTable: no implementation for table: [" + tableName + "]");
        }
        Assert.assertTrue(viewList.size() != 0);

        // click a random view in the table
        viewList.get((new Random()).nextInt(viewList.size())).click();

    }

    @Then("^I should see Vendor Bonus Request details$")
    public void iShouldSeeVendorBonusRequestDetails() throws Throwable {

        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details"); //pop-up message
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Close"); //pop-up message
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_SubmitDate"); //SubmitDate
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Request"); //Request
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Registry"); //Registry
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Registrants"); //Registrants
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_OccasionDate"); //OccasionDate
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Vendor"); //Vendor
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Pattern"); //Pattern (if applicable)
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Item"); //Item (if applicable)
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Program"); //Program
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Associate"); //Associate
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Store"); //Store
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Status"); //Status

    }

    @And("^I should see Vendor Bonus Request details information is not editable$")
    public void iShouldSeeVendorBonusRequestDetailsNotEditable() throws Throwable {

        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_SubmitDate").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Request").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Registry").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Registrants").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_OccasionDate").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Vendor").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Pattern").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Item").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Program").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Associate").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Store").getTagName().equalsIgnoreCase("input"));
        Assert.assertFalse(Elements.findElement("giftvendorbonus.vendorBonusRequestTable_Details_Status").getTagName().equalsIgnoreCase("input"));

    }

    @When("^I click X in Vendor Bonus Request details$")
    public void iClickXInVendorBonusRequestDetails() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details"); //pop-up message
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTable_Details_Close"); //pop-up message
        Assert.assertTrue(Clicks.clickIfPresent("giftvendorbonus.vendorBonusRequestTable_Details_Close"));
    }

    @Then("^I should not see Vendor Bonus Request details$")
    public void iShouldNotSeeVendorBonusRequestDetails() throws Throwable {
        Assert.assertFalse(Elements.elementInView("giftvendorbonus.vendorBonusRequestTable_Details"));
    }

    @When("^I click on the save button$")
    public void iClickOnTheSaveButton() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.save_button");
        Clicks.click("giftvendorbonus.save_button");
    }

    @Then("^I should see the popup display$")
    public void iShouldSeeThePopupDisplay() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendorbonus_popup");
    }

    @And("^I should see the popup states \"([^\"]*)\" request number \"([^\"]*)\"$")
    public void iShouldSeeThePopupStatesRequestNumber(String arg0, String arg1) throws Throwable {

    }

    @And("^I should see Registrant accordion confirmation message$")
    public void iShouldSeeRegistrantAccordionConfirmationMessage() throws Throwable {
        String regExpectedMessage = "Confirm Shipping Address for the bonus. Any changes to the Shipping Address should be made to the Registry.";

        Assert.assertTrue("Verify Registrant accordion confirmation message.",
                Elements.getText("giftvendorbonus.registrantConfirmationMessage").equals(regExpectedMessage));
    }


    @And("^I should see the submit date data is in MM-DD-YYYY format$")
    public void iShouldSeeTheSubmitDateDataIsInMMDDYYYYFormat() throws Throwable {
        String tableDate = Elements.findElement("giftvendorbonus.submit_date_data").getText();
        Assert.assertTrue("The date is in the correct format",tableDate.matches("^[0-1][0-9]/[0-3][0-9]/[0-9]{4}$"));

    }

}



