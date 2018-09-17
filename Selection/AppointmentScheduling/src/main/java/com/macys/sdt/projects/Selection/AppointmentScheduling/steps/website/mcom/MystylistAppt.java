package com.macys.sdt.projects.Selection.AppointmentScheduling.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * Created by YH02207 on 12/29/2016.
 */
public class MystylistAppt extends StepUtils {

    private String AppTime = "";
    private String AppDate = "";
    private String AppMonth = "";
    private String name = "";
    private String about = "This is a test string to verify purpose of your visit text area";


    @Given("^I visit mystylist page$")
    public void i_visit_mystylist_page() throws Throwable {

        Navigate.visit("https://social-stage.fds.com/social/my-stylist");

        Wait.forPageReady();
        System.out.println("Default Cookies before set/n");
        Set<Cookie> cookiesList =  WebDriverManager.getWebDriver().manage().getCookies();
        for(Cookie getcookies :cookiesList) {
            System.out.println(getcookies );
        }

        Cookie ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.5710,-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
        WebDriverManager.getWebDriver().manage().addCookie(ck);
        Navigate.browserRefresh();
        Thread.sleep(1000);

        System.out.println("Cookies after set---/n");

        // After adding the cookie we will check that by displaying all the cookies.
        Set<Cookie> cookiesList1 =  WebDriverManager.getWebDriver().manage().getCookies();
        for(Cookie getcookies :cookiesList1) {
            System.out.println(getcookies );
        }

        //WebDriverManager.getWebDriver().navigate().to("http://social-stage.fds.com/mystylist/");

    }

    @When("^I click Book Now on mystylist page$")
    public void i_click_Book_Now_on_mystylist_page() throws Throwable {
        Wait.secondsUntilElementPresent("home.book_now", 20);
        //Elements.findElement(By.className("book-now")).click();
        Clicks.click(Elements.element("home.book_now"));
        System.out.println("Sleeping 3 secs and will switch to Timetrade Lightbox");
        Thread.sleep(3000);
        try{
            WebDriverManager.getWebDriver().switchTo().frame("m-appointment-scheduling-timetrade-container");}
        catch(NoSuchFrameException e) {System.out.println("NoSuchFrameException");
        }
    }

    @When("^I navigate to desktop wedding registry page$")
    public void I_navigate_to_desktop_wedding_registry_page() throws Throwable {
        Wait.secondsUntilElementPresent("home.wedding_registry_link", 20);
        Clicks.click(Elements.element("home.wedding_registry_link"));
    }

    @When("^I verify the appointment banner on wedding registry page$")
    public void verify_the_appointment_banner_on_wedding_registry_page() throws Throwable {
        Wait.secondsUntilElementPresent("home.wedding_registry_app_link", 20);
        Clicks.click(Elements.element("home.wedding_registry_app_link"));
        verify_timetrade_frame();
    }

    @Then("^I verify header dropdown Schedule An Appointment link$")
    public void I_verify_header_dropdown_Schedule_An_Appointment_link() throws Throwable {
        Wait.secondsUntilElementPresent("home.header_store_dropdown", 20);
        Assert.assertTrue(Elements.elementPresent("home.header_store_dropdown"));
        Clicks.hoverForSelection(Elements.element("home.header_store_dropdown"));
        Wait.secondsUntilElementPresent("home.header_store_dropdown", 10);
        Clicks.click(Elements.element("home.header_bookAnAppointment"));
        verify_timetrade_frame();
    }

    @Then("^I verify store page Schedule An Appointment link$")
    public void I_verify_store_page_Schedule_An_Appointment_link() throws Throwable {
        Wait.secondsUntilElementPresent("home.header_stores_link", 20);
        Clicks.click(Elements.element("home.header_stores_link"));
        Wait.secondsUntilElementPresent("home.store_page_banner", 20);
        Clicks.click(Elements.element("home.store_page_banner"));
        verify_timetrade_frame();
    }

    public void verify_timetrade_frame()throws Throwable {
        Wait.secondsUntilElementPresent("home.timetrade-container-frame", 40);
        Assert.assertTrue(Elements.elementPresent("home.timetrade-container-frame"));
    }

    @Then("^I verify header dropdown Find a store link$")
    public void I_verify_header_dropdown_Find_a_store_link() throws Throwable {
        Wait.secondsUntilElementPresent("home.header_store_dropdown", 20);
        Assert.assertTrue(Elements.elementPresent("home.header_store_dropdown"));
        Clicks.hoverForSelection(Elements.element("home.header_store_dropdown"));
        Wait.secondsUntilElementPresent("home.header_store_dropdown", 10);
        Clicks.click(Elements.element("home.header_findAStore"));
        Wait.forPageReady();
        String url = WebDriverManager.getCurrentUrl();
        Assert.assertTrue(url.endsWith("shop/store/search?cm_sp=navigation-_-top_nav-_-find_a_store"));
    }

    @Then("^I verify header dropdown Find An Event link$")
    public void I_verify_header_dropdown_Find_an_event_link() throws Throwable {
        Wait.secondsUntilElementPresent("home.header_store_dropdown", 20);
        Assert.assertTrue(Elements.elementPresent("home.header_store_dropdown"));
        Clicks.hoverForSelection(Elements.element("home.header_store_dropdown"));
        Wait.secondsUntilElementPresent("home.header_store_dropdown", 10);
        Clicks.click(Elements.element("home.header_findAnEvent"));
        Wait.forPageReady();
        String url = WebDriverManager.getCurrentUrl();
        Assert.assertTrue(url.endsWith("social/events/"));
    }



    @Then("^I enter \"([^\"]*)\" to find a store for scheduling an appointment$")
    public void i_should_enter_my_city_to_find_a_store_for_scheduling_an_appointment(String customerlocation) throws Throwable {
        try{
            Wait.secondsUntilElementPresent("storelocation.location", 20);
            Elements.findElement(Elements.element("storelocation.location")).clear();
            Elements.findElement(Elements.element("storelocation.location")).sendKeys(customerlocation);
            Elements.findElement(Elements.element("storelocation.location")).sendKeys(Keys.RETURN);
        }
        catch(NullPointerException e){
            Assert.fail(""+e);
        }
    }

    @And("^I select a \"([^\"]*)\" and click continue$")
    public void i_select_a_store_offering_beauty_appointment_and_click_continue(String storelocations) throws Throwable {

        String store_name = storelocations.split("\n")[0].trim();

        System.out.println("Verifying str loc also waiting 3 secs\n" + storelocations);
        Thread.sleep(3000);
        Wait.secondsUntilElementPresent("storelocation.store_location_button", 20);
        try
        {
            //Elements.findElement(By.xpath("//div[text()='"+  store_name +"']")).click();

            Elements.findElement(By.xpath("//div[text()='A - Valley Fair']")).click();

            Clicks.click("storelocation.continue");
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }


    @And("^I Select a \"([^\"]*)\"$")
    public void i_Select_a_Program_Type(String programtype) throws Throwable {
        System.out.println("Clicking program type and waiting 3 secs\n" + programtype);
        Thread.sleep(3000);
        Elements.findElement(By.xpath(".//div[@class='labelNode'][contains(.,'" + programtype + "')]")).click();
        Elements.findElement(By.xpath(".//*[@id='nextBtn']")).click();
    }

    @And("^I Select an \"([^\"]*)\"$")
    public void I_Select_an_Appointment_Type(String appointment_type) throws Throwable {
        System.out.println("Clicking appointment type and waiting 3 secs\n" + appointment_type);
        Thread.sleep(3000);
        Elements.findElements(By.xpath("//div[@class='labelNode'][contains(.,'" + appointment_type + "')]")).get(0).click();
        Elements.findElement(By.xpath("//*[@id='nextBtn']")).click();

    }

    @And("^I Select an associate$")
    public void I_Select_an_associate(List<String> associate) throws Throwable {
        for (String assc : associate) {
            System.out.println("Clicking associate and waiting 3 secs\n" +assc);
            Thread.sleep(3000);
            Elements.findElement(By.xpath("//div[@class='labelNode'][contains(.,'" + assc + "')]")).click();
            Elements.findElement(By.xpath("//*[@id='nextBtn']")).click();

        }
    }

    @And("I accept location alert from timetrade on desktop$")
    public void I_accept_location_alert_from_timetrade_on_desktop() throws Throwable {
        try {
            if (WebDriverManager.getWebDriver().switchTo().alert() != null) {

                Alert AcceptAlert = WebDriverManager.getWebDriver().switchTo().alert();

                String alertText = AcceptAlert.getText();
                System.out.println("Captured" + alertText);
                //alert.dismiss();
                AcceptAlert.accept();
            }
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert Exception, did not need to click alert");

        }
    }

    @Given("^I have selected mystylist appointment$")
    public void i_have_selected_mystylist_appointment() throws Throwable {
        System.out.println("I have selected mystylist appointment");
    }
    @When("^I select a stylist$")
    public void i_select_a_stylist() throws Throwable {
        System.out.println("I select a stylist");
    }

    @Then("^I should see available stylist appointments$")
    public void i_should_see_available_stylist_appointments() throws Throwable {

        System.out.println("I should see available stylist appointments");
    }

    @Given("^I have selected a stylist date and time$")
    public void i_have_selected_a_stylist_date_and_time() throws Throwable {
        Wait.secondsUntilElementPresent("date_time.calendar_drop_down_first_app", 20);
        AppTime = Elements.findElement(Elements.element("date_time.calendar_drop_down_first_app")).getText();
        AppDate = Elements.findElement(Elements.element("date_time.calendar_date")).getText();
        AppMonth = Elements.findElement(Elements.element("date_time.calendar_month")).getText();
        Elements.findElement(Elements.element("date_time.calendar_drop_down")).click();
        Elements.findElement(Elements.element("date_time.calendar_drop_down_first_app")).click();
        Elements.findElement(Elements.element("date_time.calendar_drop_down")).click();
        Elements.findElement(By.xpath("//*[@id='nextBtn']")).click();
    }

    @When("^I enter my contact information$")
    public void i_enter_my_contact_information() throws Throwable {
        name = "Tester"+getRandomNumber();
        System.out.println("Creating Appointment with " + name + " user...");

        Wait.secondsUntilElementPresent("contact_info.firstname", 20);
        Elements.findElement(Elements.element("contact_info.firstname")).clear();
        Elements.findElement(Elements.element("contact_info.firstname")).sendKeys(name);

        Elements.findElement(Elements.element("contact_info.lastname")).clear();
        Elements.findElement(Elements.element("contact_info.lastname")).sendKeys(name);

        Elements.findElement(Elements.element("contact_info.email")).clear();
        Elements.findElement(Elements.element("contact_info.email")).sendKeys(name+"@donotreply.com");
        //Elements.findElement(Elements.element("contact_info.email")).sendKeys("gene.korsunsky@macys.com");

        Elements.findElement(Elements.element("contact_info.phone")).clear();
        Elements.findElement(Elements.element("contact_info.phone")).sendKeys("555-555-5555");
        Elements.findElement(Elements.element("contact_info.about")).sendKeys(about);
        Elements.findElement(By.xpath("//*[@id='nextBtn']")).click();

    }

    @When("^I verify confirmation page$")
    public void i_verify_confirmation_page() throws Throwable {

        Wait.secondsUntilElementPresent("confirm_appt.day_date", 20);

        String confirmString = Elements.findElement(Elements.element("confirm_appt.confirm_msg")).getText();
        Assert.assertEquals("Your Appointment Has Been Scheduled!", confirmString);

        String confirmAppTime = Elements.findElement(Elements.element("confirm_appt.day_date")).getText();
        String month = AppMonth.split(" ")[0].trim();
        String year = AppMonth.split(" ")[1].trim();
        Assert.assertTrue(confirmAppTime.contains(month+" "+AppDate+", "+year));

        String contactInfo = Elements.findElement(Elements.element("confirm_appt.contactInfo")).getText();
        Assert.assertTrue(contactInfo.contains(name));
        Assert.assertTrue(contactInfo.contains(name+"@donotreply.com"));
        Assert.assertTrue(contactInfo.contains("(555) 555-5555"));

        String confirmationNumber = Elements.findElement(Elements.element("confirm_appt.confirmationNumber")).getText();
        confirmationNumber = confirmationNumber.split("\\#")[1].trim();
        Assert.assertTrue(!confirmationNumber.isEmpty());

        String confirmAbout = Elements.findElement(Elements.element("confirm_appt.contactInfo")).getText();
        Assert.assertTrue(confirmAbout.contains(about));

        String appStatus = Elements.findElement(Elements.element("confirm_appt.appStatus")).getText();
        Assert.assertEquals("SCHEDULED", appStatus);

    }

    @Then("^I should see mystylist appointment confirmation$")
    public void i_should_see_mystylist_appointment_confirmation(DataTable table) throws Throwable {

        Assert.assertEquals("Your Appointment Has Been Scheduled!", Elements.findElement(Elements.element("confirm_appt.confirm_msg")).getText());
        //Below code need to make it dynamic
        //Assert.assertEquals(data.get(0).get(0), Elements.findElement(Elements.element("confirm_appt.location_font")).getText());
        //String day_date = Elements.findElement(Elements.element("confirm_appt.day_date")).getText();
        //System.out.println("Day_date: " +day_date);
        //String duration = Elements.findElement(Elements.element("confirm_appt.duration")).getText();
        //Assert.assertEquals("10:00 AM - 11:00 AM\nEastern Time", duration);
        Assert.assertEquals("Pacific Time", Elements.findElement(Elements.element("confirm_appt.timezone")).getText());

        //Validating confirmation

        List<List<String>> data = table.raw();
        //Location
        Assert.assertEquals(data.get(0).get(0), Elements.findElement(Elements.element("confirm_appt.location_font")).getText());
        Assert.assertEquals(data.get(0).get(1), Elements.findElement(Elements.element("confirm_appt.location_content")).getText());
        //Program Type
        // Assert.assertEquals(data.get(1).get(0), Elements.findElement(Elements.element("confirm_appt.programtype_font")).getText());
        //Assert.assertEquals(data.get(1).get(1), Elements.findElement(Elements.element("confirm_appt.programtype_content")).getText());
        //Appointment Type
        Assert.assertEquals(data.get(2).get(0), Elements.findElement(Elements.element("confirm_appt.app_type_font")).getText());
        Assert.assertEquals(data.get(2).get(1), Elements.findElement(Elements.element("confirm_appt.app_type_content")).getText());
        //Contact Information
        Assert.assertEquals(data.get(3).get(0), Elements.findElement(Elements.element("confirm_appt.contactinfo_font")).getText());
        Assert.assertEquals(data.get(3).get(1), Elements.findElement(Elements.element("confirm_appt.customer_name")).getText());
        Assert.assertEquals(data.get(4).get(1), Elements.findElement(Elements.element("confirm_appt.customer_email")).getText());
        Assert.assertEquals(data.get(5).get(1), Elements.findElement(Elements.element("confirm_appt.customer_phone")).getText());
        //Below code can be enabled if reason for appointment is added
        //Assert.assertEquals(data.get(6).get(1), Elements.findElement(Elements.element("confirm_appt.customer_need_font")).getText());
        //Assert.assertEquals(data.get(7).get(1), Elements.findElement(Elements.element("confirm_appt.customer_need")).getText());

    }

    @Given("^I visit mystylist page above 50 mi radius$")
    public void I_visit_mystylist_page_above_50_mi_radius() throws Throwable {
        Navigate.visit("home");
    }

    @Then("^I should not see timetrade lightbox$")
    public void I_should_not_see_timetrade_lightbox() throws Throwable {

        String oldBookNowForm = Elements.findElement(By.xpath(".//*[@id='form']/p")).getText();
        Assert.assertEquals("Just fill out the form below! Or you can also call your local store", oldBookNowForm);
    }

    @Given("^I visit mystylist page within 50 mi radius$")
    public void I_visit_mystylist_page_within_50_mi_radius() throws Throwable {
        Navigate.visit("home");
        System.out.println("identify_exisitng_appointment_booking");
        int x = 0;
        WebDriverManager.getWebDriver().manage().deleteAllCookies();
        boolean check;
        check = WebDriverManager.getWebDriver().getPageSource().contains("Just fill out the form below! Or you can also call your local store");
        System.out.println("Captured "+check);
        while((check) && (x < 10) ){
            check = WebDriverManager.getWebDriver().getPageSource().contains("Just fill out the form below! Or you can also call your local store");
            System.out.println("Captured "+check);
            WebDriverManager.getWebDriver().manage().getCookieNamed("MISCGCs");
            if (check)
            {   Cookie ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.5710,-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
                WebDriverManager.getWebDriver().manage().addCookie(ck);
                Navigate.browserRefresh();
                Thread.sleep(3000);}
        }

    }

    @Given("^I visit mystylist page within 50 mi radius from \"([^\"]*)\"$")
    public void I_visit_mystylist_page_within_50_mi_radius_from_store(String storeLocation) throws Throwable {
        Navigate.visit("home");
        Wait.forPageReady();
        Elements.findElement(By.cssSelector("a[href$='mystylist']")).click();
        Wait.forPageReady();

        String store_name = storeLocation.split("\n")[0].trim();
        Cookie ck = null;
        int x = 0;
        WebDriverManager.getWebDriver().manage().deleteAllCookies();
        boolean check;
        check = WebDriverManager.getWebDriver().getPageSource().contains("Just fill out the form below! Or you can also call your local store");
        System.out.println("Captured "+check);
        while((check) && (x < 10) ){
            check = WebDriverManager.getWebDriver().getPageSource().contains("Just fill out the form below! Or you can also call your local store");
            System.out.println("Captured "+check);
            WebDriverManager.getWebDriver().manage().getCookieNamed("MISCGCs");
            if (check){
                if(store_name.equals("A - Valley Fair") ) {
                    ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.5710,-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
                }else if(store_name.equals("A - Valley Fair2")) {
                    //union square
                    ck = new Cookie("MISCGCs", "USERPC1_92_941023_87_USERLL1_92_37.7795%2C-122.41953_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_59");
                }else if(store_name.equals("A - Valley Fair3")) {
                    //Walnut Creek
                    ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.8950192,-122.0583093_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
                }else {
                    System.out.println("ERROR: -------- Cookie value is not defined: "+ck);
                }


                WebDriverManager.getWebDriver().manage().addCookie(ck);
                Navigate.browserRefresh();
                Thread.sleep(3000);}
        }

    }

    @Given("^I visit Macys home page within 50 mi radius from \"([^\"]*)\"$")
    public void I_visit_Macys_home_page_within_50_mi_radius(String storeLocation) throws Throwable {
        Navigate.visit("home");
        String store_name = storeLocation.split("\n")[0].trim();
        Cookie ck = null;
        WebDriverManager.getWebDriver().manage().deleteCookieNamed("MISCGCs");

        if(store_name.equals("A - Valley Fair") ) {
            ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.5710,-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
        }else if(store_name.equals("A - Valley Fair2")) {
            ck = new Cookie("MISCGCs", "USERPC1_92_941023_87_USERLL1_92_37.7795%2C-122.41953_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_59");
        }else if(store_name.equals("A - Valley Fair3")) {
            ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.8950192,-122.0583093_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
        }else {
            System.out.println("ERROR: -------- Cookie value is not defined: "+ck);
        }

        WebDriverManager.getWebDriver().manage().addCookie(ck);
        Navigate.browserRefresh();

    }


    @Given("^I visit Macys home page within 50 mi radius from \"([^\"]*)\" and set cookie for wedding registry$")
    public void I_visit_Macys_home_page_within_50_mi_radius_for_registry(String storeLocation) throws Throwable {
        Navigate.visit("home");
        String store_name = storeLocation.split("\n")[0].trim();
        Cookie ck = null;
        WebDriverManager.getWebDriver().manage().deleteCookieNamed("MISCGCs");

        if(store_name.equals("A - Valley Fair") ) {
            ck = new Cookie("MISCGCs", "USERPC1_92_941023_87_USERLL1_92_37.5710%2C-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_60");
        }else if(store_name.equals("A - Valley Fair2")) {
            ck = new Cookie("MISCGCs", "USERPC1_92_941023_87_USERLL1_92_37.7795%2C-122.41953_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_59");
        }else if(store_name.equals("A - Valley Fair3")) {
            ck = new Cookie("MISCGCs", "USERPC1_92_945363_87_USERLL1_92_37.8950192,-122.0583093_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55");
        }else {
            System.out.println("ERROR: -------- Cookie value is not defined: "+ck);
        }

        WebDriverManager.getWebDriver().manage().addCookie(ck);
        Navigate.browserRefresh();

    }

    @Given("^I open new browser tab$")
    public void I_open_new_browser_tab() throws Throwable {
        String url = WebDriverManager.getCurrentUrl();
        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        //open new tab
        WebDriver driver = WebDriverManager.getWebDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.sessionStorage.clear();");
        jse.executeScript("window.open('about:blank','_blank');");
        //switch to new tab
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to new tab
            }
        }
        Navigate.visit(url);
    }


    @Given("^I launch time trade$")
    public void I_launch_time_trade() throws Throwable {
        Navigate.visit("home");
    }

    @Then("^I should see timetrade lightbox$")
    public void I_should_see_timetrade_lightbox() throws Throwable {
        try{ System.out.println("I should see timetrade_lightbox");
            //Below Stmt - Switching to iframe
            WebDriverManager.getWebDriver().switchTo().frame("m-appointment-scheduling-timetrade-container");}
        catch(NoSuchFrameException e)
        {System.out.println(e);}

    }


    public String getRandomNumber()throws Throwable {
        LocalTime thisSec = LocalTime.now();
        return thisSec.toString().replace(":","").replace(".","");
    }

}
