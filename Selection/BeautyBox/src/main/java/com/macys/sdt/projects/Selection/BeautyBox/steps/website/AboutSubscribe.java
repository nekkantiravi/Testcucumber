package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Selection.BeautyBox.utils.website.GenericUtilsBeauty;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Created by yh00119 on 11/22/16.
 */
public class AboutSubscribe extends StepUtils {


    // public static String dmls = null;
    public static String aSub = "SUBSCRIBE NOW";
    public static String manageSub = "Manage Subscription";
    public static String aSubAndWaitList = "SUBSCRIPTION & WAITLIST IS FULL";
    public static String aWaitList = "JOIN THE WAITLIST";
    public static String CLOSED = "CLOSED";
    public static String WAITLIST = "WAITLIST";
    public static String sItem = null;
    private static String strProdLinkName = null;
    private static String strProdImageName = null;
    private static String sDate = null;
    private static String strDate = null;
    private static String strPrdName = null;

    private static String dbName = "postgresql";
    private static String dbUrl = "jdbc:postgresql://172.21.1.1:5432/subscription";
    private static String dbUsername = "subscription";
    private static String dbPassword = "subscription123";

    /*
    private static String dbName = "oracle";
    private static String dbUrl ="jdbc:oracle:thin:@jcia4497:1521:BBXQA01";
    private static String dbUsername = "bbxsuba1";
    private static String dbPassword = "subadm1n";
    private static String sDate = null;*/


    @Then("^I should see video on the about subscribe page and I play the video$")
    public boolean I_should_see_video_on_the_about_subscribe_page_and_I_play_the_video() throws Throwable {

        // Assert.assertTrue("Error - Unable to identify the beauty box video",
        //     Elements.elementPresent("about_subscribe.bb_sub_video"));

        Thread.sleep(3000);
        if (Elements.elementPresent("about_subscribe.bb_sub_video")) {
            Clicks.click("about_subscribe.bb_sub_video");
            Thread.sleep(3000);
            return true;
        } else if (Elements.elementPresent("about_subscribe.bb_sub_video_pause")) {
            Clicks.click("about_subscribe.bb_sub_video_pause");
            Thread.sleep(3000);
            return true;
        } else {
            Assert.fail("Error: Unable to find the beauty box video");
            return false;
        }

    }

    @And("^I launch beautybox about page url$")
    public void I_launch_beautybox_about_page_url() throws Throwable {
        /*ClassLoader classLoader = getClass().getClassLoader();
        Properties prop = new Properties();
        prop.load(classLoader.getResourceAsStream("config.properties"));
        String about_url = prop.getProperty("bb_about_url");
        Navigate.visit(about_url);*/

        //  Navigate.visit("http://www.qa15codemacys.fds.com/subscription/about?betaTest=mvpc");

        // Navigate.visit("https://www.qa20codemacys.fds.com/subscription/beauty-box?betaTest=mvpc");
        Navigate.visit(RunConfig.url + "/subscription/beauty-box");

    }

    @And("^I should see manage subscription link on about page$")
    public void I_should_not_see_manage_subscription_link_on_about_page() throws Throwable {
        if (Elements.elementPresent("about_subscribe.abt_manage_link")){
            Assert.assertTrue("manage subscription link is displayed on about Page", true);
        } else {
            Assert.fail("Error - manage subscription link is not displaying on about page");
        }
    }

    @Then("^I navigate to manage subscription$")
    public void I_navigate_to_managesubscription() throws Throwable {
        Navigate.visit(RunConfig.url + "/subscription/manageSubscriptions");
        Thread.sleep(3000);
    }

    /*final JSONArray result = obj.getJSONArray(BAZAAR_VOICE_RESULTS);
    for (int i = 0; i < result.length(); ++i)
    { ReviewDetailsBO reviewDetail = new ReviewDetailsBO();
        final JSONObject productData = result.getJSONObject(i);
        reviewDetail.setProductId(productData.getString(PRODUCT_ID));
        reviewDetail.setUserNickName(productData.getString(USER_NICK_NAME));
        reviewDetails.add(reviewDetail); }*/

    @And("^I click on video to pause the video$")
    public void I_click_on_video_to_pause_the_video() throws Throwable {

        I_should_see_video_on_the_about_subscribe_page_and_I_play_the_video();
    }

    @Then("^I should see email address and zip code$")
    public void I_should_see_email_address_and_zip_code() throws Throwable {

        Thread.sleep(5000);
        int rowCountEmail = Elements.findElements(By.xpath("//*[@id='beautyBoxSubscribe']/div[1]/form/input[1]")).size();
        if (rowCountEmail == 1) {
            Elements.findElement(By.xpath("//*[@id='beautyBoxSubscribe']/div[1]/form/input[1]")).sendKeys("rama@beauty.com");
            System.out.println("User able to provide email id");
        } else {
            Assert.fail("Doesn't find email address field");
        }
        int rowCountZip = Elements.findElements(By.xpath("//*[@id='beautyBoxSubscribe']/div[1]/form/input[2]")).size();
        if (rowCountZip == 1) {
            Elements.findElement(By.xpath("//*[@id='beautyBoxSubscribe']/div[1]/form/input[2]")).sendKeys("95050");
            System.out.println("User able to provide zip code");
        } else {
            Assert.fail("Doesn't find the zip code field");
            Navigate.browserRefresh();
        }
    }


    @Then("^I see \"([^\"]*)\" on about page$")
    public void I_see_subscribe_on_about_page(String status) throws Throwable {

        scrollToLazyLoadElement("about_subscribe.abt_sub_button");
        Thread.sleep(2000);
        if (Elements.elementPresent("about_subscribe.abt_sub_button")) {
            scrollToLazyLoadElement("about_subscribe.abt_sub_button");
            sItem = Elements.findElement(Elements.element("about_subscribe.abt_sub_button")).getText();
        } else if (Elements.elementPresent("about_subscribe.sub_full")) {
            scrollToLazyLoadElement("about_subscribe.sub_full");
            sItem = Elements.findElement(Elements.element("about_subscribe.sub_full")).getText();
        } else if (Elements.elementPresent("about_subscribe.wl_button_bottom")) {
            scrollToLazyLoadElement("about_subscribe.wl_button_bottom");
            sItem = Elements.findElement(Elements.element("about_subscribe.wl_button_bottom")).getText();
        } else {
            Assert.fail("Unable to find the subscription status on about page");
        }
        if (sItem.contains(status)) {
            System.out.println("User is able to see the subscription status as:" + sItem);
            Assert.assertTrue("User is able to see the subscription status as:" + sItem, true);
        } else {
            Assert.fail("Subscription status is not displaying as expected:" + sItem);
        }

        //  I_click_on_subscribe_on_about_page();
    }

    @And("^I click manage subscription link on about page$")
    public void I_click_on_manage_subscription_on_about_page() throws Throwable {
        scrollToLazyLoadElement("about_subscribe.abt_manage_link");
        Clicks.click("about_subscribe.abt_manage_link");
    }

    @And("^I should see \"([^\"]*)\" on about page$")
    public void I_should_see_error_on_about_page(String errorMsg) throws Throwable {
        Thread.sleep(2000);
        String manageSubError = Elements.findElement(Elements.element("about_subscribe.abt_manage_error")).getText();
        if (errorMsg.equals(manageSubError)){
            Assert.assertTrue("Displaying valid error message when guest user click on Manage Subscription::"+manageSubError, true);
        } else{
            Assert.fail("Its not throwing valid error message if guest user click on Manage Subscription::"+manageSubError);
        }
    }



    @And("^I click on subscribe on about page$")
    public void I_click_on_subscribe_on_about_page() throws Throwable {

        if (sItem.contains(aSub)) {
            Clicks.click("about_subscribe.abt_sub_button");
        } else if (sItem.equals(aWaitList)) {

            Clicks.click("about_subscribe.wl_button_bottom");

        } else if (sItem.equals(aSubAndWaitList)) {

            Clicks.click("about_subscribe.abt_sub_button");

        } else {
            Assert.fail("false");
        }
    }


    @Then("^I see cta status \"([^\"]*)\" on about page$")
    public void I_see_cts_status_on_about_page(String status) throws Throwable {

        if (Elements.elementPresent("about_subscribe.abt_sub_top_btn")) {
            scrollToLazyLoadElement("about_subscribe.abt_sub_top_btn");
            sItem = Elements.findElement(Elements.element("about_subscribe.abt_sub_button")).getText();
        } // updaate below steps as per the top cta section of wla nd subscription full
        else if (Elements.elementPresent("about_subscribe.sub_full")) {
            scrollToLazyLoadElement("about_subscribe.sub_full");
            sItem = Elements.findElement(Elements.element("about_subscribe.sub_full")).getText();
        } else if (Elements.elementPresent("about_subscribe.wl_button_bottom")) {
            scrollToLazyLoadElement("about_subscribe.wl_button_bottom");
            sItem = Elements.findElement(Elements.element("about_subscribe.wl_button_bottom")).getText();
        } else {
            Assert.fail("Unable to find the subscription status on about page");
        }
        if (sItem.contains(status)) {
            System.out.println("User is able to see the subscription status as:" + sItem);
            Assert.assertTrue("User is able to see the subscription status as:" + sItem, true);
        } else {
            Assert.fail("Subscription status is not displaying as expected:" + sItem);
        }

        //  I_click_on_subscribe_on_about_page();
    }

    @And("^I click on top subscribe on about page$")
    public void I_click_on_top_subscribe_on_about_page() throws Throwable {

        if (sItem.contains(aSub)) {
            Clicks.click("about_subscribe.abt_sub_top_btn");
        } else if (sItem.equals(aWaitList)) {

            Clicks.click("about_subscribe.wl_button_bottom");

        } else if (sItem.equals(aSubAndWaitList)) {

            Clicks.click("about_subscribe.abt_sub_button");

        } else {
            Assert.fail("false");
        }
    }


    @And("^I click on subscribe now on about page$")
    public void I_click_on_subscribe_now_on_about_page() throws Throwable {
        if (Elements.elementPresent("about_subscribe.abt_sub_button")) {
            Clicks.click("about_subscribe.abt_sub_button");
        } else {
            Assert.fail("Unable to identify the subscribe now button on about page");
        }


    }

    @Then("^I see subscription \"([^\"]*)\" on about page$")
    public void I_see_subscription_status_on_about_page(String status) throws Throwable {

        Navigate.browserRefresh();
        sItem = Elements.findElement(Elements.element("about_subscribe.sub_full")).getText();
        if (sItem.contains(status)) {
            Assert.assertTrue("User able to see the subscription status as FULL", true);
        } else {

            Assert.fail("Error - Unable to verify subscription full status");

        }

    }

    @Then("^I see how it works on monthly subscription page$")
    public void I_see_how_it_works_on_monthly_subscription_page() throws Throwable {

        Thread.sleep(3000);
        if (Elements.elementPresent("about_subscribe.how_it_wrk")) {
            Assert.assertTrue("User able to see how it works to 'Join' monthly box ", true);
            System.out.println("User able to see how it works to 'Join' monthly box ");
        } else {
            Assert.fail("Error: Doesn't find how it works to 'join' monthly box");
        }

    }

    @And("^I see join and its description$")
    public void I_see_join_and_its_description() throws Throwable {

        String sJoin = Elements.findElement(Elements.element("about_subscribe.how_it_wrk_join")).getText();

        String sSjoin = "JOIN\n" +
                "Sign up! Then, sit back while we put together our next month’s themed box of beauty essentials.";

        if (sJoin.equals(sSjoin)) {

            System.out.println(true);

        } else {
            System.out.println(false);
        }

        // Assert.assertTrue("Error:Doesn't find ",Elements.elementPresent("about_subscribe.how_it_wrk_join"));


    }

    @And("^I see get your box and its description$")
    public void I_see_get_your_box_and_its_description() throws Throwable {

        String eGetYourBox = Elements.findElement(Elements.element("about_subscribe.how_it_wrk_getyourbox")).getText();

        String aGetyourBox = "GET YOUR BOX\n" +
                "We’ll deliver the box straight to your door. Get ready for the 5 beauty surprises inside!";

        if (eGetYourBox.equals(aGetyourBox)) {

            System.out.println(true);

        } else {
            System.out.println(false);

        }
    }


    @And("^I see discover and its description$")
    public void I_see_discover_and_its_description() throws Throwable {
        String eDiscover = Elements.findElement(Elements.element("about_subscribe.how_it_wrk_discover")).getText();

        String aDiscover = "DISCOVER\n" +
                "Try out new beauty essentials!";
        if (eDiscover.equals(aDiscover)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }

    @Then("^I see ourboxes section and its dropdown background image and monthly text$")
    public void I_see_ourboxes_section_and_its_dropdown() throws Throwable {
        scrollToLazyLoadElement("about_subscribe.our_boxes_dropdown");
        Thread.sleep(5000);
        Assert.assertTrue("Error: Unable to find our boxes dropdown", Elements.elementPresent("about_subscribe.our_boxes_dropdown"));
        // DropDowns.selectByText("about_subscribe.our_box_dropdown", "AUGUST 2017");

        List<String> dates = DropDowns.getAllValues("about_subscribe.our_boxes_dropdown");

        dates.forEach(date -> {

            if (date != null)
                DropDowns.selectByText("about_subscribe.our_boxes_dropdown", date);
            System.out.println("  *******     :" + date);
            if (Elements.elementPresent("about_subscribe.our_monthly_background_images") && Elements.elementPresent("about_subscribe.our_monthlybox_image")) {

                Assert.assertTrue("Able to view monthly background image and monthly boxes image", true);

                if (Elements.elementPresent("about_subscribe.our_monthly_text_center")) {
                    Assert.assertTrue("Able to view monthly box text center", true);

                } else {

                    Assert.fail("Unable to identify our boxes monthly text center");

                }
            } else {

                Assert.fail("Unable to identify our boxes monthly background image and monthly boxes image");

            }


        });


    }


    @And("^I see products for selected month$")
    public void I_see_products_for_selected_month() throws Throwable {

        Assert.assertTrue("Error: Unable to find monthly boxes for selected month", Elements.elementPresent("about_subscribe.our_box_mont_prod"));

    }


    @When("^I visit postgresql database to insert or delete \"([^\"]*)\" as a precondition$")
    public void I_visit_database_to_insert_data(String records) throws Throwable {

        Statement stmt;

        //Below Commented code is not required as we have common code inorder to serve the purpose
    /*    String s;

        FileInputStream inputStream;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            StringBuffer sb = new StringBuffer();
            File fr = new File(classLoader.getResource(records).getFile());
            System.out.println("File Found : " + fr.exists());

            inputStream = new FileInputStream(fr);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }

            br.close();

            String[] inst = sb.toString().split(";");*/

        try {
            File fr = Utils.getResourceFile(records);

            String sbr = Utils.readTextFile(fr);

            String[] inst = sbr.split(";");
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);

            stmt = connection.createStatement();
            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    stmt.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }


    }

    @Then("^I visit the database to insert \"([^\"]*)\" max availabilty of next \"([^\"]*)\" months$")
    public void I_visit_the_database_to_insert_availabilty_data(String availabilty, String months) {
        Statement stmt;
        Integer numOfMonths = Integer.parseInt(months);
        Integer maxQty = Integer.parseInt(availabilty);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        try {
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            stmt = connection.createStatement();
            for (int j=1; j<=numOfMonths; j++) {
                cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String startDate = format.format(cal.getTime());
                cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                String endDate = format.format(cal.getTime());
                cal.add(Calendar.MONTH, 1);
                String query = "insert into SUBS_PROD_AVAILABILITY " +
                        "(SUBS_PROD_AVAILABILITY_UUID,ENTITLEMENT_TYPE_UUID,SUBS_UPC,SUBS_PRODUCT_ID,SUBS_AVAILABLE_MAX_QTY,SUBS_AVAIL_EFFECTIVE_START_DT,SUBS_AVAIL_EFFECTIVE_END_DT,IS_EDITABLE,CREATED_BY,MODIFIED_BY) " +
                        "values ('"+UUID.randomUUID()+"','0b1829ef-b77a-42b2-bccc-d1731a1c59d8',123,345,"+maxQty+"," +
                        "TO_TIMESTAMP( '"+ startDate +"', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_TIMESTAMP('"+ endDate +"', 'YYYY-MM-DD HH24:MI:SS.FF'),'Y','System','System')";
                stmt.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
    }


    @Then("^I visit the database to insert \"([^\"]*)\" user subscriptions$")
    public void I_visit_the_database_to_insert_user_subscriptions(String subscriptions) throws Throwable {
        Statement stmt;
        Integer users = Integer.parseInt(subscriptions);
        Integer userId = 12345;
        Integer userGuid = 3330;
        try {
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            stmt = connection.createStatement();
            for (int j=1; j<=users; j++) {
                String query = "INSERT INTO USER_ENTITLEMENT (USER_ENTITLEMENT_UUID,USER_ID, USER_GUID, ENTITLEMENT_TYPE_UUID, " +
                        "USER_ENTL_UPC, USER_ENTL_PRODUCT_ID, USER_ENTL_QTY, USER_ENTL_TYPE, USER_ENTL_DURATION, USER_ENTL_FREQUENCY, " +
                        "USER_ENTL_START_DT, USER_ENTL_END_DT, PAYMENT_CARD_ID,PAYMENT_TYPE_CODE, SHIPPING_CONTACT_ID, USER_ENTL_STATUS, CREATED_BY, MODIFIED_BY) " +
                        "VALUES ('"+UUID.randomUUID()+"','"+String.valueOf(userId+j)+"','"+String.valueOf(userGuid+j)+"','0b1829ef-b77a-42b2-bccc-d1731a1c59d8',1,1,1,'Monthly',-1,1,TO_TIMESTAMP('2017-03-1 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF')," +
                        "NULL,'2346','345','333','SUBSCRIBED','System','System')";
                stmt.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
    }

    @Then("^I visit the database to insert \"([^\"]*)\" user$")
    public void I_visit_the_database_to_insert_suspended_user(String suspend_user) throws Throwable {
        Statement stmt;
        Integer users = Integer.parseInt(suspend_user);
        Integer userId = 2345;
        Integer userGuid = 33330;
        try {
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            stmt = connection.createStatement();
            for (int j=1; j<=users; j++) {
                String query = "INSERT INTO USER_ENTITLEMENT (USER_ENTITLEMENT_UUID,USER_ID, USER_GUID, ENTITLEMENT_TYPE_UUID, " +
                        "USER_ENTL_UPC, USER_ENTL_PRODUCT_ID, USER_ENTL_QTY, USER_ENTL_TYPE, USER_ENTL_DURATION, USER_ENTL_FREQUENCY, " +
                        "USER_ENTL_START_DT, USER_ENTL_END_DT, PAYMENT_CARD_ID,PAYMENT_TYPE_CODE, SHIPPING_CONTACT_ID, USER_ENTL_STATUS, CREATED_BY, MODIFIED_BY) " +
                        "VALUES ('"+UUID.randomUUID()+"','"+String.valueOf(userId+j)+"','"+String.valueOf(userGuid+j)+"','0b1829ef-b77a-42b2-bccc-d1731a1c59d8',1,1,1,'Monthly',-1,1,TO_TIMESTAMP('2017-03-1 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF')," +
                        "NULL,'2346','345','333','SUSPEND','System','System')";
                stmt.executeUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
    }


    @Then("^I visit database to insert \"([^\"]*)\" as a precondition$")
    public void I_visit_pg_database_to_insert_data(String dmls) throws Throwable {

        Statement stmt;
        try {
            File fr = Utils.getResourceFile(dmls);

            String sbr = Utils.readTextFile(fr);

            String[] inst = sbr.split(";");
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
             // Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    stmt.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
    }

    @When("^I change the subscribedBy \"([^\"]*)\" as a precondition$")
    public void I_change_the_subscribedBy_date_as_a_precondition(String date) {
        try{
            Integer day = Integer.parseInt(date);
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            Statement stmt = connection.createStatement();
            String sql = "update entitlement_configuration set attr_value = "+ day +" where ATTR_NAME='subscribedBy'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }

    }

    private String getMonth(Calendar cal, Integer month) {
        cal.add(Calendar.MONTH, month);
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

    @Then("I should see subscription month box \"([^\"]*)\" on about page")
    public void I_should_see_subscription_month_box_on_about_page(String date) {
        scrollToLazyLoadElement("about_subscribe.about_monthly_box");
        Calendar cal = Calendar.getInstance();
        Integer currentDay = cal.get(Calendar.DAY_OF_MONTH);
        String month;
        if(currentDay > Integer.parseInt(date)){
            month = getMonth(cal, 2);
        } else {
            month = getMonth(cal, 1);
        }

        String monthlyBoxMessage = Elements.findElement(Elements.element("about_subscribe.about_monthly_box")).getText();
        String message = "To get your "+month+" Beauty Box:";
        if (message.equals(monthlyBoxMessage)){
            Assert.assertTrue("Displaying valid monthly box subscription message:: "+monthlyBoxMessage, true);
        } else{
            Assert.fail("Not Displaying valid monthly box subscription message:: "+monthlyBoxMessage);
        }
    }

    @Then("I should not see subscription month box on about page")
    public void I_should_not_see_subscription_month_box_on_about_page() {
        if (Elements.elementPresent("about_subscribe.about_monthly_box")){
            Assert.fail("Error - Subscription monthly box label is displaying on about page");
        } else {
            Assert.assertTrue("Subscription monthly box label is not displayed in About Page", true);
        }
    }

    @When("I visit database to change the program status as (\\w+)")
    public void I_visit_database_to_change_the_program_status(String status) {
        try{
            String sql = null;
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            Statement stmt = connection.createStatement();
            if(status.equals(CLOSED)) {
                sql = "update entitlement_configuration set attr_value = '0' where ATTR_NAME='waitlist.percentage'";
            } else if(status.equals(WAITLIST)) {
                sql = "update entitlement_configuration set attr_value = '-1' where ATTR_NAME='waitlist.percentage'";
            }
            stmt.executeUpdate(sql);
            String upadteQtyQuery = "update SUBS_PROD_AVAILABILITY set subs_available_max_qty=0";
            stmt.executeUpdate(upadteQtyQuery);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }

    }

    @Then("^I visit database to update configuration as a precondition$")
    public void I_visit_database_to_update_data() throws Throwable {

        String sUID = GenericUtilsBeauty.getUIDCookie();

        System.out.println("sUID:::::" + sUID);

        String newStatus;
        String userStatus = null;
        Statement stmt;

        try {
            Calendar usercancelledDate = Calendar.getInstance();
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            //  Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            String sql = "select USER_ENTL_END_DT,USER_ENTL_STATUS from  user_entitlement where user_id =" + sUID;
            System.out.println("SQL VALUE::" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("rs::::" + rs);

            while (rs.next()) {
                System.out.println(rs.getTimestamp(1));
                Timestamp cancelledTimeStamp = rs.getTimestamp(1);
                userStatus = rs.getString(2);
                usercancelledDate.setTimeInMillis(cancelledTimeStamp.getTime());
                System.out.println(" user cancelledDate" + usercancelledDate.getTime());
            }

            //commented this code as per cancelled policy change
             /*   Calendar expectedsubscriptionEndDate = null;
                Calendar cancelByDate = GenericUtilsBeauty.getCancelledByDate(10);
                if (GenericUtilsBeauty.compareDates(Calendar.getInstance(), cancelByDate) >= 0) {
                    expectedsubscriptionEndDate = GenericUtilsBeauty.getSubscriptionEndDate(cancelByDate, 1);
                     newStatus = "CANCELLED_PENDING";
                } else {
                    expectedsubscriptionEndDate = GenericUtilsBeauty.getSubscriptionEndDate(cancelByDate, 0);
                     newStatus = "CANCELLED";
                }*/

            newStatus = "CANCELLED";
            Assert.assertEquals(userStatus, newStatus);
//                Assert.assertEquals(usercancelledDate.getTime(),expectedsubscriptionEndDate.getTime());
//                System.out.println("usercancelledDate:"+usercancelledDate.getTime());
//                System.out.println("expectedsubscriptionEndDate:"+expectedsubscriptionEndDate.getTime());


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }

    }


    @Then("^I update the database to see the suspended status$")
    public void I_update_the_database_to_see_the_suspended_status() throws Throwable {
        Thread.sleep(3000);
        String sUID = GenericUtilsBeauty.getUIDCookie();

        System.out.println("sUID:::::" + sUID);
        Thread.sleep(3000);
        Statement stmt;

        try {
            Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            //Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            String sql = "update USER_ENTITLEMENT set user_entl_status = 'SUSPENDED' WHERE user_id =" + sUID;
            System.out.println("SQL VALUE::" + sql);
            int update = stmt.executeUpdate(sql);
            //ResultSet rs = stmt.executeQuery(sql);
            System.out.println("rs::::" + update);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }

    }

    @And("^I see suspended status on managesubscription$")
    public void I_see_suspended_status_on_managesubscription() throws Throwable {

        Thread.sleep(3000);
        Navigate.browserRefresh();
        Thread.sleep(4000);
        String strSubScriptionSts = Elements.findElement(Elements.element("beauty_subscription.cancel_subscription_status")).getText();
        if (strSubScriptionSts.contains("SUSPENDED")) {
            Assert.assertTrue("Status has been updated as SUSPENDED successfully", true);
            System.out.println("Status has been updated as SUSPENDED successfully::" + strSubScriptionSts);
        } else {
            Assert.fail("Status of the subscription is:" + strSubScriptionSts);
            System.out.println("Status of the subscription is:" + strSubScriptionSts);
        }


    }


    @Then("^I see top banner on about page$")
    public void I_see_top_banner_on_about_page() throws Throwable {

        Assert.assertTrue("Error - Unable to identify the top banner on about page",
                Elements.elementPresent("about_subscribe.about_top_banner"));

    }

    @And("^I see bottom banner on about page$")
    public void I_see_bottom_banner_on_about_page() throws Throwable {

        scrollToLazyLoadElement("about_subscribe.about_bottom_banner");
        Assert.assertTrue("Error - Unable to identify the bottom banner on about page",
                Elements.elementPresent("about_subscribe.about_bottom_banner"));
    }

  /*  @When("^I click on \"([^\"]*)\" on about page for default month \"([^\"]*)\"$")
    public void I_click_on_shop_the_product(String shopTheProduct, String ptpPrice) throws Throwable {

        List<String> dates = DropDowns.getAllValues("about_subscribe.our_boxes_dropdown");
        System.out.println("Dates from dropdown" + dates);
        dates.forEach(date -> {
            if (date != null)
                DropDowns.selectByText("about_subscribe.our_boxes_dropdown", date);
            strProdLinkName = Elements.findElement(Elements.element("about_subscribe.about_shop_the_products")).getText();
            StringTokenizer tokenDateToMthToYr = new StringTokenizer(date);
            String month = tokenDateToMthToYr.nextToken();
            String year = tokenDateToMthToYr.nextToken();
            int mthCode = MonthsCode.valueOf(month.toUpperCase()).monthCodeValue();
            if (strProdLinkName.equals(shopTheProduct)) {
                boolean strShop = Elements.elementPresent("about_subscribe.about_shop_the_products");
                if (strShop) {
                    Clicks.click("about_subscribe.about_shop_the_products");
                    PathToPurchaseFull ptp = new PathToPurchaseFull();
                    if("productsDesc".equals(ptpPrice)){
                        ptp.I_navigate_to_ptp_full_size_products(year, mthCode);
                    }else if("originalPrice".equals(ptpPrice) ){
                        ptp.I_navigate_to_ptp_full_size_products_to_validate_products_original_price(year, mthCode);
                    }else if("retailPrice".equals(ptpPrice)){
                        ptp.I_navigate_to_ptp_full_size_products_to_validate_products_retail_price(year, mthCode);
                    }else{
                        Assert.fail("Condition is not met to call the methods to compare");
                    }

                    // ptp.I_navigate_to_ptp_full_size_products();
                } else {
                    Assert.fail("Unable to find the web link shop the product on about page");
                }
            } else {
                Clicks.click("about_subscribe.about_shop_the_image_click");
                System.out.println("click on image succ");
                PathToPurchaseFull ptp = new PathToPurchaseFull();
                //ptp.I_navigate_to_ptp_full_size_products(year, mthCode);
                // ptp.I_navigate_to_ptp_full_size_products();
                if("productsDesc".equals(ptpPrice)){
                    ptp.I_navigate_to_ptp_full_size_products(year, mthCode);
                }else if("originalPrice".equals(ptpPrice) ){
                    ptp.I_navigate_to_ptp_full_size_products_to_validate_products_original_price(year, mthCode);
                }else if("retailPrice".equals(ptpPrice)){
                    ptp.I_navigate_to_ptp_full_size_products_to_validate_products_retail_price(year, mthCode);
                }else{
                    Assert.fail("Condition is not met to call the methods to compare");
                }
            }
        });


    }*/


    @When("^I click on \"([^\"]*)\" on about page for default month \"([^\"]*)\"$")
    public void I_click_on_shop_the_product(String shopTheProduct, String ptpPrice) throws Throwable {
        System.out.println(EnvironmentDetails.otherApp("SubscriptionBatch"));
        List<String> dates = DropDowns.getAllValues("about_subscribe.our_boxes_dropdown");
        System.out.println("Dates from dropdown" + dates);
        dates.forEach(date -> {
            System.out.println("Date selected from the dropdown and products are checking for year and month::" + date);
            if (date != null) {

                if (date.equals("FEBRUARY  |  2018")) {
                    sDate = "February | 2018";
                }else if (date.equals("JANUARY  |  2018")) {
                    sDate = "January | 2018";
                }else if (date.equals("DECEMBER  |  2017")) {
                    sDate = "December | 2017";
                }else if (date.equals("NOVEMBER  |  2017")) {
                    sDate = "November | 2017";
                }else if(date.equals("OCTOBER  |  2017")) {
                    sDate = "October | 2017";
                } else if (date.equals("SEPTEMBER  |  2017")) {
                    sDate = "September | 2017";
                } else if (date.equals("AUGUST  |  2017")) {
                    sDate = "August | 2017";
                }

                DropDowns.selectByText("about_subscribe.our_boxes_dropdown",sDate);
                strProdLinkName = Elements.findElement(Elements.element("about_subscribe.shop_full_size_products")).getText();
                System.out.println("Button name to click full products:::" + strProdLinkName);
                strDate = sDate.replaceAll("\\s+","");
                StringTokenizer tokenDateToMthToYr = new StringTokenizer(strDate, "|",false);
                System.out.println("Month and year from dropdown   ::::::::: " +tokenDateToMthToYr);
                String month = tokenDateToMthToYr.nextToken();
                String year = tokenDateToMthToYr.nextToken();
                int mthCode = MonthsCode.valueOf(month.toUpperCase()).monthCodeValue();
                if (strProdLinkName.equals(shopTheProduct)) {
                    boolean strShop = Elements.elementPresent("about_subscribe.shop_full_size_products");
                    if (strShop) {
                        Clicks.click("about_subscribe.shop_full_size_products");
                        PathToPurchaseFull ptp = new PathToPurchaseFull();
                        if ("productsDesc".equals(ptpPrice)) {
                            ptp.I_navigate_to_ptp_full_size_products(year, String.valueOf(mthCode));
                        } else if ("originalPrice".equals(ptpPrice)) {
                            ptp.I_navigate_to_ptp_full_size_products_to_validate_products_original_price(year, mthCode);
                        } else if ("retailPrice".equals(ptpPrice)) {
                            ptp.I_navigate_to_ptp_full_size_products_to_validate_products_retail_price(year, mthCode);
                        } else {
                            Assert.fail("Condition is not met to call the methods to compare");
                        }

                        // ptp.I_navigate_to_ptp_full_size_products();
                    } else {
                        Assert.fail("Unable to find the web link shop the product on about page");
                    }
                }
            }
        });
    }


    public static enum MonthsCode {

        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        private int monthCode;

        MonthsCode(int monthCode) {
            this.monthCode = monthCode;
        }

        public int monthCodeValue() {
            return monthCode;
        }

    }

    @When("^I select month and year from \"([^\"]*)\" page dropdown to validate \"([^\"]*)\"$")
    public void I_select_month_and_year_form_dropdown_on_monthly_box_page(String pageName, String ptpPrice) throws Throwable {

        if ("monthlyBox".equals(pageName)) {
            ClassLoader classLoader = getClass().getClassLoader();
            Properties prop = new Properties();
            prop.load(classLoader.getResourceAsStream("data/website/mcom/config.properties"));
            String monthly_box_url = prop.getProperty("bb_monthly_box_url");
            Navigate.visit(monthly_box_url);
        }
        List<String> dates = DropDowns.getAllValues("about_subscribe.our_boxes_dropdown");
        System.out.println("Dates from dropdown" + dates);
        dates.forEach(date -> {
            if (date != null)
                DropDowns.selectByText("about_subscribe.our_boxes_dropdown", date);
            StringTokenizer tokenDateToMthToYr = new StringTokenizer(date);
            String month = tokenDateToMthToYr.nextToken();
            String year = tokenDateToMthToYr.nextToken();
            int mthCode = MonthsCode.valueOf(month.toUpperCase()).monthCodeValue();
            PathToPurchaseFull ptp = new PathToPurchaseFull();
            if ("productsDesc".equals(ptpPrice)) {
                ptp.I_navigate_to_ptp_full_size_products(year, String.valueOf(mthCode));
            } else if ("originalPrice".equals(ptpPrice)) {
                ptp.I_navigate_to_ptp_full_size_products_to_validate_products_original_price(year, mthCode);
            } else if ("retailPrice".equals(ptpPrice)) {
                ptp.I_navigate_to_ptp_full_size_products_to_validate_products_retail_price(year, mthCode);
            } else {
                Assert.fail("Condition is not met to call the methods to compare");
            }

        });


    }

    @Then("^I run a batchjob \"([^\"]*)\" to verify$")
    public void I_run_a_batch_job_to_verify_the_status(String batch_job) throws Throwable {

        Response resp = RESTOperations
                .doPOST("http://" + EnvironmentDetails.otherApp("SubscriptionBatch")
                        .ipAddress + ":8080/api/subscription/v1/" + batch_job, "application/json", null);
        System.out.println("Response::" + resp.getStatus());

    }

    @Then("^I run a batchjob \"([^\"]*)\" to verify profile$")
    public void I_run_a_batch_job_to_verify_the_status_profile(String batch_job2) throws Throwable {

        Response resp = RESTOperations
                .doPOST("http://" + EnvironmentDetails
                        .otherApp("SubscriptionBatch")
                        .ipAddress + ":8080/api/subscription/v1/" + batch_job2, "application/json", null);
        System.out.println("Response::" + resp.getStatus());
    }


    @And("^I check the status waitlist notified status on manage subscription$")
    public void I_check_the_status_waitlist_notified_status_on_manage_subscription() throws Throwable {

        I_navigate_to_managesubscription();

        Thread.sleep(8000);
//         String sWLItem = Elements.findElement(Elements.element("about_subscribe.wl_ms_confirmation")).getText();
//        if(sWLItem.contains("WAITLIST_NOTIFIED")){
//
//            Assert.assertTrue("Waitlist status moved to waitlist notified:"+sWLItem, true);
//        }


    }

    @When ("^I click on Macys logo and navigate to home page$")
    public void I_click_on_Macys_logo_and_navigate_to_home_page() throws Throwable{

        if(Elements.elementPresent("about_subscribe.macys_logo")){
            Clicks.click("about_subscribe.macys_logo");
        }else {
            Assert.fail("Unable to identify the Macys logo");
        }

    }


    @Then ("^I see the cta status on about page$")
    public void I_see_the_cta_status_on_about_page() throws Throwable{

        if (Elements.elementPresent("about_subscribe.abt_sub_button")) {
            scrollToLazyLoadElement("about_subscribe.abt_sub_button");
            sItem = Elements.findElement(Elements.element("about_subscribe.abt_sub_button")).getText();
        } else if (Elements.elementPresent("about_subscribe.sub_full")) {
            scrollToLazyLoadElement("about_subscribe.sub_full");
            sItem = Elements.findElement(Elements.element("about_subscribe.sub_full")).getText();
            System.out.println("Subscription status is full:" +sItem);
        } else if (Elements.elementPresent("about_subscribe.wl_button_bottom")) {
            scrollToLazyLoadElement("about_subscribe.wl_button_bottom");
            sItem = Elements.findElement(Elements.element("about_subscribe.wl_button_bottom")).getText();
        } else {
            Assert.fail("ERROR - CTA subscription status is having issue. Please check the beautybox page immediately");
        }
        if (sItem.equals("SUBSCRIBE NOW")) {
            System.out.println("User is able to see the subscription status as:" + sItem);
            Assert.assertTrue("User is able to see the subscription status as:" + sItem, true);
        } else if(sItem.equals("JOIN THE WAITLIST")) {
            Assert.assertTrue("User is able to see the subscription status as::" + sItem, true);
        }else if(sItem.contains("Our subscription list is currently full!")){
            Assert.assertTrue("User is able to see the subscription status as::" + sItem, true);
        }else{
            Assert.fail("ERROR - CTA subscription status is having issue. Please check the beautybox page immediately");
        }
    }


    @Then("^I validate \"([^\"]*)\" ptp monthly products \"([^\"]*)\"$")
    public void I_validate_ptp_monthly_products(String shopTheProduct, String ptpPrice) throws Throwable {
        List<String> dates = DropDowns.getAllValues("about_subscribe.our_boxes_dropdown");
        System.out.println("Dates from dropdown" + dates);
        for (int i = 0; i<dates.size();i++){
            if (i==1){

                if(dates.get(i).equals("APRIL  |  2018")){
                    sDate = "April | 2018";
                }else if(dates.get(i).equals("MARCH  |  2018")){
                    sDate = "March | 2018";
                }else if (dates.get(i).equals("FEBRUARY  |  2018")) {
                    sDate = "February | 2018";
                }else if (dates.get(i).equals("JANUARY  |  2018")) {
                    sDate = "January | 2018";
                }else if (dates.get(i).equals("DECEMBER  |  2017")) {
                    sDate = "December | 2017";
                }else if (dates.get(i).equals("NOVEMBER  |  2017")) {
                    sDate = "November | 2017";
                }else if(dates.get(i).equals("OCTOBER  |  2017")) {
                    sDate = "October | 2017";
                } else if (dates.get(i).equals("SEPTEMBER  |  2017")) {
                    sDate = "September | 2017";
                } else if (dates.get(i).equals("AUGUST  |  2017")) {
                    sDate = "August | 2017";
                }

                DropDowns.selectByText("about_subscribe.our_boxes_dropdown",sDate);
                break;
            }
        }
        strProdLinkName = Elements.findElement(Elements.element("about_subscribe.shop_full_size_products")).getText();
        if (strProdLinkName.equals(shopTheProduct)) {
            boolean strShop = Elements.elementPresent("about_subscribe.shop_full_size_products");
            if (strShop) {
                Clicks.click("about_subscribe.shop_full_size_products");
            }
        }
        Integer counter = 0;
        boolean ptpProd = Elements.elementPresent("path_to_purchase_full.ptp_full_products");
        if (ptpProd) {
            List<WebElement> listOfProd = Elements.findElements(By.cssSelector(".product-title"));
            System.out.println("prod desc::"+strPrdName);
            System.out.println("listOfProd :::" +listOfProd);
            System.out.println("listOfProd::::"+listOfProd.size());

            for (WebElement fullProdList : listOfProd) {
                if (fullProdList.isDisplayed()) {
                    counter++;
                   // boolean isContains = productResult.contains(fullProdList.getAttribute("alt"));
                    System.out.println("sjdhfgshdjfgs"+fullProdList.getAttribute("alt"));
                    System.out.println("Product description is::::"+fullProdList.getCssValue(".product-title"));
                }else {
                    Assert.assertFalse("No products available for the selected month:" + sDate, false);
                }
            }
        }
    }
}