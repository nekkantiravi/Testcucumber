package com.macys.sdt.projects.Selection.AppointmentScheduling.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.macys.sdt.framework.interactions.TextBoxes.typeTextNEnter;

/**
 * Created by yh02207 on 1/23/2017.
 */
public class MystylistAppt extends StepUtils{

    @When("^I click Stores from main menu and I should not see Make an appointment button$")
    public void I_click_Stores_from_main_menu_and_I_should_not_see_Make_an_appointment_button() throws Throwable {

        Clicks.click("home.navigation_menu");
        Thread.sleep(2000);

        Clicks.click("home.stores_menu");
        Thread.sleep(3000);

        Assert.assertFalse("Make an appointment", WebDriverManager.getWebDriver().getPageSource().contains("Make an appointment"));
        JavascriptExecutor killSwitchTurnOn = (JavascriptExecutor) WebDriverManager.getWebDriver();
        killSwitchTurnOn.executeScript("App.config.ENV_CONFIG.appointment_scheduling=\"on\"");
    }

    @When("^I set cookies for appointment scheduling$")
    public void I_set_cookies_for_appointment_scheduling() throws Throwable {
        Cookie ck = null;
        int x = 0;
        WebDriverManager.getWebDriver().manage().deleteCookieNamed("MISCGCs");
        boolean check;
        check = (WebDriverManager.getWebDriver().manage().getCookieNamed("MISCGCs")==null);

        while((check) && (x < 3) ){
            WebDriverManager.getWebDriver().manage().getCookieNamed("MISCGCs");
            ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.5710,-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
            x++;
            WebDriverManager.getWebDriver().manage().addCookie(ck);
            Navigate.browserRefresh();
            Thread.sleep(3000);
            check = (WebDriverManager.getWebDriver().manage().getCookieNamed("MISCGCs")==null);

        }

    }


    @Then("^I verify appointment link$")
    public void I_verify_appointment_link() throws Throwable {
        Wait.secondsUntilElementPresent("home.app_link_reg_page", 20);
        Clicks.click("home.app_link_reg_page");
        Wait.secondsUntilElementPresent("home.app_frame", 20);
        Assert.assertTrue(Elements.elementPresent("home.app_frame"));

    }

    @Then("^I navigate to stores screen$")
    public void I_navigate_to_stores_screen() throws Throwable {
        Clicks.click("home.navigation_menu");
        Thread.sleep(2000);
        Clicks.click("home.stores_menu");
        Thread.sleep(3000);
        Assert.assertEquals("MAKE AN APPOINTMENT", Elements.findElement(Elements.element("find_store.make_an_appointment")).getText());
    }



    @Then("^I should see make an appointment button and I click it$")
    public void I_should_see_make_an_appointment_button_and_() throws Throwable {
        Clicks.click("find_store.make_an_appointment");
        Thread.sleep(5000);
    }

    @And("I close location alert from timetrade$")
    public void I_close_location_alert_from_timetrade() throws Throwable {
        try {
            if (WebDriverManager.getWebDriver().switchTo().alert() != null) {

                Alert AcceptAlert = WebDriverManager.getWebDriver().switchTo().alert();

                String alertText = AcceptAlert.getText();
                System.out.println("Captured" + alertText);
                //alert.dismiss();
                AcceptAlert.accept();
            }
        } catch (NoAlertPresentException e) {
            //e.printStackTrace();
            System.out.println("No alert to close...");
        }
    }

    @And("I should enter location and select a store$")
    public void I_should_enter_location_and_select_a_store()throws Throwable  {
        //Below Stmt - Switching to iframe
        WebDriverManager.getWebDriver().switchTo().frame("m-appointment-scheduling-timetrade-container");
        typeTextNEnter("storelocation.location", "San Jose, CA");
        Thread.sleep(3000);
        Clicks.click("storelocation.select_store");
        Clicks.click("storelocation.continue");
        Thread.sleep(3000);
    }

    @And("I select mystylist appointment$")
    public void I_select_mystylist_appointment()throws Throwable  {
        Clicks.click("appointment_type.buyonline_pickupinstore");
        Clicks.click("appointment_type.continue");
        Thread.sleep(3000);
    }


    @And("I select mystylist appointment type$")
    public void I_select_mystylist_appointment_type()throws Throwable  {
        Clicks.click("appointment_type.app_type");
        Clicks.click("appointment_type.continue");
        Thread.sleep(3000);
    }

    @And("^I have selected a stylist date and time on mobile$")
    public void i_have_selected_a_stylist_date_and_time_on_mobile() throws Throwable {
        Select ddCalender = new Select(Elements.findElement(Elements.element("date_time.latest_date")));
        ddCalender.selectByIndex(1);
        Elements.findElement(Elements.element("date_time.continue")).click();
        Thread.sleep(5000);
    }
    @When("^I enter my contact information on mobile$")
    public void i_enter_my_contact_information_on_mobile(List<String> customerinfo) throws Throwable {
        Elements.findElement(Elements.element("contact_info.firstname")).clear();
        Elements.findElement(Elements.element("contact_info.lastname")).clear();
        Elements.findElement(Elements.element("contact_info.email")).clear();
        Elements.findElement(Elements.element("contact_info.phone")).clear();
        System.out.println("Cleared cache data waiting 3 secs to enter new");
        Thread.sleep(3000);

        Elements.findElement(Elements.element("contact_info.firstname")).sendKeys(customerinfo.get(0));
        Elements.findElement(Elements.element("contact_info.lastname")).sendKeys(customerinfo.get(1));
        Elements.findElement(Elements.element("contact_info.email")).sendKeys(customerinfo.get(2));
        Elements.findElement(Elements.element("contact_info.phone")).sendKeys(customerinfo.get(3));
        //Elements.findElement(By.id("questionId__help")).sendKeys("My event is birthday");
        Elements.findElement(By.id("nextBtn")).click();
        Thread.sleep(5000);
    }

    @Then("^I should see mystylist appointment confirmation on mobile$")
    public void i_should_see_mystylist_appointment_confirmation_on_mobile(DataTable table) throws Throwable {

        Assert.assertEquals("Your Appointment Has Been Scheduled!", Elements.findElement(Elements.element("confirm_appt.confirm_msg")).getText());
        Assert.assertEquals("Pacific Time", Elements.findElement(Elements.element("confirm_appt.timezone")).getText());

        //Validating confirmation

        List<List<String>> data = table.raw();
        //Location
        Assert.assertEquals(data.get(0).get(0), Elements.findElement(Elements.element("confirm_appt.location_font")).getText());
        Assert.assertEquals(data.get(0).get(1), Elements.findElement(Elements.element("confirm_appt.location_content")).getText());

        //Appointment Type
        Assert.assertEquals(data.get(2).get(0), Elements.findElement(Elements.element("confirm_appt.app_type_font")).getText());
        Assert.assertEquals(data.get(2).get(1), Elements.findElement(Elements.element("confirm_appt.app_type_content")).getText());

        //Contact Information
        Assert.assertEquals(data.get(3).get(0), Elements.findElement(Elements.element("confirm_appt.contactinfo_font")).getText());
        Assert.assertEquals(data.get(3).get(1), Elements.findElement(Elements.element("confirm_appt.customer_name")).getText());

        Assert.assertEquals(data.get(4).get(1), Elements.findElement(Elements.element("confirm_appt.customer_email")).getText());
        Assert.assertEquals(data.get(5).get(1), Elements.findElement(Elements.element("confirm_appt.customer_phone")).getText());

    }
}
