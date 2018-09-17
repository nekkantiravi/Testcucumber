package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Selection.BeautyBox.utils.website.GenericUtilsBeauty;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

//import com.macys.sdt.framework.runner.WebDriverManager;

/**
 * Created by m940030 on 9/19/16.
 */
public class BeautyBox extends StepUtils {
    SoftAssertions softly = new SoftAssertions();


    @And("^I navigate to given \"([^\"]*)\" url$")
    public void I_navigate_to_specified_url(String URL) throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties prop = new Properties();
        prop.load(classLoader.getResourceAsStream("config.properties"));
        String order_url = prop.getProperty("order_url");
        Navigate.visit(order_url);


    }


    @Then("^I should not be about to view BeautyBox link$")
    public void i_should_not_be_about_to_view_BeautyBox_link() throws Throwable {
        Wait.secondsUntilElementPresent("my_subscription.pdp_link", 10);
        boolean strBB = Elements.elementPresent("my_subscription.pdp_link");

        if (!strBB) {
            System.out.println("Beauty Box link is not present on PDP");

        } else {
            Assert.fail(" Beauty Box on pdp page");
        }

    }

    @Then("^I should land on About us when I click on BeautyBox link$")
    public void i_should_land_on_About_us_when_I_click_on_BeautyBox_link() throws Throwable {

        Thread.sleep(2000);

        scrollToLazyLoadElement("my_subscription.pdp_link");

        Clicks.click("my_subscription.pdp_link");

        Wait.forPageReady();
        Thread.sleep(5000);


    }

    @When("^I click on the change button and edit button$")
    public void i_click_on_the_change_button() throws Throwable {
        Thread.sleep(3000);

        if (Elements.elementPresent("my_subscription.chk_change")) {
            Clicks.click("my_subscription.chk_change");
            Thread.sleep(1000);
            Clicks.click("my_subscription.chk_edit");
            Thread.sleep(1000);
        } else {

            Assert.fail("Error: Unable to find the beauty box video");
        }
    }

    @Then("^I should verify the shipping address as below$")
    public void i_should_verify_the_shipping_address_as_below() throws Throwable {
        String item = Elements.findElement(Elements.element("my_subscription.chk_address")).getText();
        softly.assertThat(item.contains("Bob Wastaken")).as("chk_address").isEqualTo(true);

    }

    @Then("^I should verify the shipping address global error$")
    public void i_should_verify_the_shipping_address_global_error() throws Throwable {
        String item = Elements.findElement(Elements.element("my_subscription.chk_global_error")).getText();
        softly.assertThat(item.contains("The shipping address entered isn't valid. Please try again.")).as("chk_global_error").isEqualTo(true);

    }

    @Then("^I should verify the shipping address warning error$")
    public void i_should_verify_the_shipping_address_warning_error() throws Throwable {
        String item = Elements.findElement(Elements.element("my_subscription.chk_warning_error")).getText();
        softly.assertThat(item.contains("We noticed your shipping address Zip code was incorrect so we ﬁxed it. Please verify your address below.")).as("chk_warning_error").isEqualTo(true);

    }

    @Then("^I should verify the \"([^\"]*)\" details as below$")
    public void i_should_verify_the_creditcard_details_as_below(String creditcard) throws Throwable {
        scrollToLazyLoadElement("my_subscription.chk_address");
        String item = Elements.findElement(Elements.element("my_subscription.chk_credit")).getText();
        if (item.contains(creditcard)) {
            System.out.println("credit" + item);

        } else {

            Assert.fail("Error - Unable to verify the credit" + creditcard);

        }
    }


    @Then("^I should verify the \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" as below$")
    public void i_should_verify_the_and_as_below(String first_name, String last_name, String address, String city, String zipcode, String Phonenumber) throws Throwable {
        Thread.sleep(2000);

        String item = Elements.findElement(Elements.element("my_subscription.chk_first_name")).getAttribute("value");
        String lastname = Elements.findElement(Elements.element("my_subscription.chk_last_name")).getAttribute("value");
        String address1 = Elements.findElement(Elements.element("my_subscription.chk_address_one")).getAttribute("value");
        String city1 = Elements.findElement(Elements.element("my_subscription.chk_city")).getAttribute("value");
        String zipcode1 = Elements.findElement(Elements.element("my_subscription.chk_zipcode")).getAttribute("value");
        String phone = Elements.findElement(Elements.element("my_subscription.chk_phone")).getAttribute("value");


        System.out.println("I am printing the address value" + item);
        if (item.equals(first_name) && lastname.equals(last_name) && address1.equals(address) && city1.equals(city) && zipcode1.equals(zipcode) && phone.equals(phone)) {
            System.out.println("First Name:" + item);
            System.out.println("Last Name:" + lastname);
            System.out.println("Address:" + address1);
            System.out.println("City:" + city1);
            System.out.println("Zipcode:" + zipcode1);
            System.out.println("Phone:" + phone);

        } else {

            Assert.fail("Error - Unable to verify the address");

        }

    }


    @Then("^I view the Subscription Status \"([^\"]*)\"as below$")
    public void I_verify_subscription_status(String status) throws Throwable {
        Thread.sleep(3000);
        String item = Elements.findElement(Elements.element("my_subscription.sub_status")).getText();
        softly.assertThat(item.contains(status)).as("sub_status").isEqualTo(true);
        System.out.println(item);
        softly.assertAll();
    }

    @Then("^I view the Subscription History as below$")
    public void I_verify_subscription_history() throws Throwable {
        String item = Elements.findElement(Elements.element("my_subscription.sub_history")).getText();
        softly.assertThat(item.contains("items from your order history\n" +
                "2016\n" +
                "Feb\n" +
                "2016\n" +
                "Jan")).as("sub_history").isEqualTo(true);
        System.out.println(item);
        softly.assertAll();
    }

    /**
     * Step to add or update FST cookie
     **/
    @And("^I have \"([^\"]*)\" for SEGMENT cookie$")
    public void I_have_segment_value_for_segment_cookie(String segment_val) throws Throwable {
        Wait.forPageReady();
        GenericUtilsBeauty.setGUIDCookie(segment_val);
        Navigate.visit("home");
        Wait.forPageReady();
        Thread.sleep(1000);
    }


    /**
     * Step to add or update FST cookie
     **/
    @And("^I should be able to replace segment_value cookie$")
    public void I_have_segment_value_for_segment_cookies() throws Throwable {

        ClassLoader classLoader = getClass().getClassLoader();

        Properties prop = new Properties();
        prop.load(classLoader.getResourceAsStream("config.properties"));
        String segment1 = prop.getProperty("segment_beauty_cookie");
        String segment2 = prop.getProperty("segment_bb_unlink");

        Wait.forPageReady();
        GenericUtilsBeauty.setSegment(segment1, segment2);

        Navigate.browserRefresh();

        Wait.forPageReady();
        Thread.sleep(5000);


    }


//
//    @Then("^I should be able to iterate \"([^\"]*)\" steps$")
//    public void Fetching_data_from_console(Integer iterations) throws Throwable {
//
//        boolean status = false;
//
//        JavascriptExecutor js = (JavascriptExecutor) .getWebDriver()
//        js.executeScript("console.profile();");
//        for (int i = 1; i <= iterations; i++) {
//
//           /* FoxyWITB dd = new FoxyWITB();
//            dd.I_see_beauty_box_on_home_page();
//            dd.I_see_dropdown_to_select_the_monthly_beauty_box();
//            dd.I_select_a_specified_month_from_dropdown();
//            status = dd.I_should_see_sample_products_for_that_month();*/
//
//            AboutSubscribe dd = new AboutSubscribe();
//
//            status = dd.I_should_see_video_on_the_about_subscribe_page_and_I_play_the_video();
//
//          //  dd.I_click_on_video_to_pause_the_video();
//
//
//
//        }
//            if (status) {
//                js.executeScript("console.profileEnd();");
//
//                String response = (String) js.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
//                        "window.addEventListener('memoryDumpFile', function(e) { callback(e.detail); });" +
//                        "window.dispatchEvent(new CustomEvent('memoryDump'));");
//                Map<String, String> map = new HashMap<String, String>();
//                ObjectMapper mapper = new ObjectMapper();
//
//                try {
//                    //convert JSON string to Map
//                    map = mapper.readValue(response, new TypeReference<HashMap<String, String>>() {
//                    });
//                     sendToGraphite(map, iterations);
//                    System.out.println("Passing data to Grafana");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//
//                Assert.fail("Test case failed");
//
//
//            }
//
//
//
//
//    }

    @When("^I connect to DB and insert records to see the subscribe status$")
    public void I_connect_to_DB_and_insert_few_records_to_see_the_subscription_status_as_waitlist() throws SQLException {

        Statement stmt = null;

        String s = new String();

        FileInputStream inputStream = null;
        ClassLoader classLoader = getClass().getClassLoader();

        Properties prop = new Properties();

        try {
            prop.load(classLoader.getResourceAsStream("config.properties"));
            String jdbcurl = prop.getProperty("subscription.database.jdbcUrl");
            String Username = prop.getProperty("subscription.database.username");
            String Password = prop.getProperty("subscription.database.password");
            Connection c = DriverManager.getConnection(jdbcurl, Username, Password);

            System.out.println("Opened database successfully");
            StringBuffer sb = new StringBuffer();
            File fr = new File(classLoader.getResource("BeautyBox.sql").getFile());
            System.out.println("File Found : " + fr.exists());

            inputStream = new FileInputStream(fr);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");

            stmt = c.createStatement();
            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    stmt.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public void sendToGraphite(Map<String, String> map, Integer iterations) {
        try {
            Socket client = new Socket("172.17.1.110", 2003);
            PrintStream output;
            output = new PrintStream(client.getOutputStream());

            try {
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (iterations == 1) {

                        String metric = "subscription.browser.memory." + pair.getKey();
                        System.out.println("metric" + metric + "value" + pair.getValue());
                        output.printf("%s %s %d%n", metric, pair.getValue(), (int) (System.currentTimeMillis() / 1000));

                        it.remove();

                    } else {

                        String metric = "subscription.browser.iteratedmemory." + pair.getKey();
                        System.out.println("metric" + metric + "value" + pair.getValue());
                        output.printf("%s %s %d%n", metric, pair.getValue(), (int) (System.currentTimeMillis() / 1000));

                        it.remove();

                    }

                }
            } finally {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}




