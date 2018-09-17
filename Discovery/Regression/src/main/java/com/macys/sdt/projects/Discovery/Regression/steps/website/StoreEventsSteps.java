package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by YH03402 on 5/25/2017.
 */
public class StoreEventsSteps extends StepUtils {
    private String selected_address = "";
    private static final Logger logger = LoggerFactory.getLogger(StoreEventsSteps.class);

    @When("^I navigate to Store events page$")
    public void i_navigate_to_Store_events_page() throws Throwable {
        Clicks.click("home.goto_stores");
        String expected = Elements.getText("store_event.store_header");
        Assert.assertTrue("Store Event header not present", expected.equals("STORE FINDER")||expected.equals("Find a Bloomingdale's Store"));
    }


    @When("^I select \"([^\"]*)\" from dropdown and search pincode \"([^\"]*)\"$")
    public void i_select_from_dropdown_and_search_pincode(String arg1, String arg2) throws Throwable {
        WebDriver driver = WebDriverManager.getWebDriver();
        String value = Elements.getValues("store_event.store_location_dropdown").get(0);
        List<WebElement> ele = driver.findElements(By.xpath(value));
        for (WebElement element : ele) {
            if (element.getText().equalsIgnoreCase(arg1)) {
                element.click();
            }
        }
        TextBoxes.typeTextbox("store_event.store_search_box", arg2);
        Clicks.click("store_event.store_search_button");
    }

    @Then("^I click on random Address on results page$")
    public void i_click_on_random_Address_on_results_page() throws Throwable {
        Wait.untilElementPresent("store_event.search_results");
        List<WebElement> ele = Elements.findElements("store_event.search_results");
        closePopup();
        Clicks.clickIfPresent("header_and_footer.close_email_popup");
        if (!ele.isEmpty()) {
            Random random = new Random();
            int address_index = random.nextInt(ele.size());
            selected_address = ele.get(address_index).getText().split("Bloomingdale's")[1];
            Clicks.hoverForSelection(ele.get(address_index));
            logger.info("Selected Address:" + selected_address);
            ele.get(address_index).click();
            Wait.isPageLoaded();
        } else
            Assert.assertFalse("Element in not available", false);

    }

    @Then("^I verify the Address details displayed on the Page$")
    public void i_verify_the_Address_details_displayed_on_the_Page() throws Throwable {
        Navigate.browserRefresh();
        Wait.forPageReady();
        Wait.untilElementPresent("store_event.store_location");
        String expected = Elements.getText("store_event.store_location");
        Assert.assertTrue("Address different", expected.contains(selected_address));
    }

    @Then("^I verify the STORE HOURS on the page$")
    public void i_verify_the_STORE_HOURS_on_the_page() throws Throwable {
        List<WebElement> ele = Elements.findElements("store_event.store_hours_weekday");
        Assert.assertEquals("Total Day's Count not equal to 7", 7, ele.size());
    }

    @Then("^I verify the STORE MAP on the page$")
    public void i_verify_the_STORE_MAP_on_the_page() throws Throwable {
        Assert.assertTrue("STORE MAP IS NOT Displayed on Page", Elements.elementPresent("store_event.store_details_map"));
    }

    @Then("^I verify the Store Hours Block Data on the page$")
    public void i_verify_the_Store_Hours_Date_on_the_page() throws Throwable {
        List<String> actualDays = new ArrayList<>();
        List<String> expectedDays = new ArrayList<>();
        String[] days = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        expectedDays = Arrays.asList(days);
        Assert.assertTrue("STORE HOURS BLOCK Data not displayed on Page", Elements.elementPresent("store_event.store_hours_container"));

        //verify all seven day on Store block
        List<WebElement> ele = Elements.findElements("store_event.store_hours_weekday");
        for (WebElement day : ele) {
            actualDays.add(day.getText());
        }
        logger.info("DaysDisplayed::"+actualDays);
        Assert.assertEquals("Days count not equal to 7 on BLOCK", expectedDays.size(), actualDays.size());
        Collections.sort(expectedDays);
        Collections.sort(actualDays);
        logger.info("Actual Days::" + actualDays);
        logger.info("Expected Days::" + expectedDays);
        Assert.assertEquals("Week Days are not correctly displayed on BLOCK", expectedDays, actualDays);

        //Verify all seven dates on Store block
        if(!bloomingdales()){
            List<String> actualDates = new ArrayList<>();
            List<WebElement> eleDates = Elements.findElements("store_event.store_hours_date");
            for (WebElement dates : eleDates) {
                actualDates.add(dates.getText());
            }
            logger.info("Actual Days::" + actualDates);
            Assert.assertEquals("Dates are not correctly displayed on BLOCK", 7, actualDates.size());
            //verifying the format of dated displayed
            //i.e Jul. 31 Where the second no. should be a no.
            for (String datevalue : actualDates) {
                Assert.assertTrue("Date does not contain a no.", StringUtils.isNumeric(datevalue.split(". ")[1]));
            }
        }

        //Verify all time on the page
        List<String> actualTime = new ArrayList<>();
        List<WebElement> eletime = Elements.findElements("store_event.store_details_hours");
        for (WebElement time : eletime) {
            actualTime.add(time.getText());
        }
        logger.info("Actual times::" + actualTime);
        Assert.assertEquals("time is not correctly displayed on BLOCK", 7, actualTime.size());
        for (String timevalue : actualTime) {
            if (!(timevalue.equalsIgnoreCase("closed") || timevalue.contains("Open until"))) {
                Assert.assertTrue("Time does not contain a AM or PM", ((timevalue.split(" ")[1].trim().equalsIgnoreCase("AM")) || timevalue.split(" ")[4].trim().equalsIgnoreCase("PM")));
            }
        }

    }
}
