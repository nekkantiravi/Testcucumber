package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Selection.BeautyBox.utils.website.GenericUtilsBeauty;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static com.macys.sdt.framework.interactions.Navigate.switchWindow;
import static com.macys.sdt.framework.interactions.Navigate.switchWindowClose;

/**
 * Created by m940030 on 3/23/17.
 */
public class ManageSubscription {

    public static String strStatusMyaccount = null;
    public static String strStatusManage = null;

    private static String dbName = "postgresql";
    private static String dbUrl = "jdbc:postgresql://172.21.12.57:5432/subscription";
    private static String dbUsername = "subscription";
    private static String dbPassword = "subscription123";

    @Then("^I should be able to verify manageSubscription \"([^\"]*)\"$")
    public void i_should_be_able_to_verify_manageSubscription(String current_status) throws Throwable {
        Thread.sleep(5000);
        String item = Elements.findElement(Elements.element("my_subscription.manage_status")).getText();
        Assert.assertTrue(item.contains(current_status));
    }

    @Then("^I sign out of the website$")
    public void i_Sign_out_of_the_website() throws Throwable {
        Thread.sleep(2000);
        String item = Elements.findElement(Elements.element("my_subscription.signOut")).getText();
        Clicks.click("my_subscription.signOut");

    }

    @When("^I click on cancel subscription button$")
    public void i_click_on_cancel_subscription_button() throws Throwable {

        if (Elements.elementPresent("my_subscription.manage_cancel")) {
            Clicks.click("my_subscription.manage_cancel");
            Thread.sleep(2000);
        }


        if (Elements.elementPresent("my_subscription.manage_cancel_pop")) {
            String item2 = Elements.findElement(Elements.element("my_subscription.manage_cancel_pop")).getText();
            System.out.println(item2);
            Thread.sleep(2000);
        }

        if (Elements.elementPresent("my_subscription.manage_pop_cancel")) {
            Thread.sleep(1000);
            Clicks.click("my_subscription.manage_pop_cancel");

        }
    }

    @When("^I delete the existing subscribed record in the database$")
    public void i_delete_the_existing_subscribed_record_in_the_database() throws SQLException {

        Statement stmt = null;
        String userGuid = GenericUtilsBeauty.getGUIDCookie();

        FileInputStream inputStream = null;
        ClassLoader classLoader = getClass().getClassLoader();

        Properties prop = new Properties();

        try {

            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            stmt = connection.createStatement();

            /*System.out.println("Opening DB Connection");
            prop.load(classLoader.getResourceAsStream("config.properties"));
            String jdbcurl = prop.getProperty("subscription.database.jdbcUrl");
            String Username = prop.getProperty("subscription.database.username");
            String Password = prop.getProperty("subscription.database.password");
            String drivers = prop.getProperty("subscription.database.classname");
            Class.forName(drivers);
            Connection c = DriverManager.getConnection(jdbcurl, Username, Password);

            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("printing the GUID" + userGuid);*/

            String sSQLDelete1 = "delete from user_entl_status_history where user_entl_status_history.user_entitlement_uuid in (select user_entitlement_uuid from USER_ENTITLEMENT where USER_ENTITLEMENT.user_guid='" + userGuid + "')";
            stmt.executeUpdate(sSQLDelete1);
            String sSQLDelete2 = "DELETE from USER_ENTITLEMENT where user_guid='" + userGuid + "'";
            stmt.executeUpdate(sSQLDelete2);

            //c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }

    }


    @When("^I update the existing subscribe status record in the database$")
    public void i_delete_the_existing_subscribe_status_record_in_the_database() throws SQLException {

        Statement stmt = null;
        String userGuid = GenericUtilsBeauty.getGUIDCookie();


        FileInputStream inputStream = null;
        ClassLoader classLoader = getClass().getClassLoader();

        Properties prop = new Properties();

        try {
            prop.load(classLoader.getResourceAsStream("config.properties"));
            String jdbcurl = prop.getProperty("subscription.database.jdbcUrl");
            String Username = prop.getProperty("subscription.database.username");
            String Password = prop.getProperty("subscription.database.password");
            String drivers = prop.getProperty("subscription.database.classname");
            Class.forName(drivers);
            Connection c = DriverManager.getConnection(jdbcurl, Username, Password);

            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("printing the GUID" + userGuid);

            String sSQLDelete1 = "UPDATE USER_ENTITLEMENT SET USER_ENTL_STATUS='SUBSCRIBED' where user_guid='" + userGuid + "'";
            stmt.executeUpdate(sSQLDelete1);

            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    @When("^I click on MyAccount link on ManageSubscription page$")
    public void i_click_on_MyAccount_link_on_ManageSubscription_page() throws Throwable {
        Clicks.click("my_subscription.manage_myaccount_link");
    }

    @Then("^I capture subscriptionstatus on Managesubscription page$")
    public void i_capture_subscriptionstatus_on_ManageSubscription_page() throws Throwable {
        Thread.sleep(3000);
        Navigate.browserRefresh();
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify subscription status",
                Elements.elementPresent("my_subscription.manage_status"));
        strStatusManage = Elements.findElement(Elements.element("my_subscription.manage_status")).getText();
    }

    @Then("^I see subscription tile in MyAccount page$")
    public void i_see_on_subscription_tile_in_MyAccount_page() throws Throwable {
        Thread.sleep(2000);
        if (Elements.elementPresent("my_subscription.myaccount_subscription_tile")) {
            System.out.println("Subscription tile is displaying in My Account Page");
        } else {
            Assert.fail("Error - Subscription tile is not displaying in My Account Page");
        }
    }

    @Then("^I should see subscriptionstatus in Subscription tile$")
    public void i_should_see_subscriptionstatus_in_subscription_tile() throws Throwable {
        Thread.sleep(2000);
        strStatusMyaccount = Elements.findElement(Elements.element("my_subscription.myaccount_status")).getText();
        if(strStatusMyaccount.contains(strStatusManage)){
            System.out.println("Subscription Status after subscribed is: "+ strStatusMyaccount);
        }else {
            Assert.fail("MyAccount subscription status is not matched with managesubscription status");
        }
    }

    @Then("^I click on beautybox logo in MyAccount page$")
    public void i_click_on_beautybox_logo_tile_in_MyAccount_page() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify Beautybox logo",
                Elements.elementPresent("my_subscription.myaccount_beautybox_logo"));
        Clicks.click("my_subscription.myaccount_beautybox_logo");
    }

    @When("^I browser back$")
    public void i_browser_back() throws Throwable {
        Thread.sleep(2000);
        Navigate.browserBack();
    }

    @Then("^I see beauty box about page link on subscription tile$")
    public void i_see_beauty_box_about_page_link_on_subscription_tile() throws Throwable {
        Thread.sleep(2000);
        if (Elements.elementPresent("my_subscription.myaccount_beautybox_about_link")) {
            System.out.println("User is able to see Fullsize products link in My Account Page");
        } else {
            Assert.fail("Error - User is unable to see Fullsize products link in My Account Page");
        }
    }

    @Then("^I click on beauty box about page link$")
    public void i_click_on_beauty_box_about_page_link() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify Beautybox about link",
                Elements.elementPresent("my_subscription.myaccount_beautybox_about_link"));
        Clicks.click("my_subscription.myaccount_beautybox_about_link");
    }

    @Then("^I see shop full size product link on subscription tile$")
    public void i_see_shop_full_size_product_link_on_subscription_tile() throws Throwable {
        Thread.sleep(2000);
        if (Elements.elementPresent("my_subscription.myaccount_fullsize_products")) {
            System.out.println("User is able to see Fullsize products link in My Account Page");
        } else {
            Assert.fail("Error - User is unable to see Fullsize products link in My Account Page");
        }
    }

    @Then("^I click on shop full size product link$")
    public void i_click_on_shop_full_size_product_link() throws Throwable {
        Thread.sleep(5000);

        Assert.assertTrue("Error - Unable to identify Beautybox fullsize product link",
                Elements.elementPresent("my_subscription.myaccount_fullsize_products"));
        Clicks.click("my_subscription.myaccount_fullsize_products");
    }

    @Then("^I see shop full size product page with full size products$")
    public void i_see_shop_full_size_product_page_with_full_size_products() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to navigate Beautybox fullsize product page",
                Elements.elementPresent("path_to_purchase_full.ptp_fullproducts_button"));
    }

    @Then("^I see Beauty Box FAQ link$")
    public void i_see_Beauty_Box_FAQ_link() throws Throwable {
        Thread.sleep(2000);
        if (Elements.elementPresent("my_subscription.myaccount_beautybox_FAQ_link")) {
            System.out.println("User is able to see Beautybox FAQ link in My Account Page");
        } else {
            Assert.fail("Error - User is unable to see Beautybox FAQ link in My Account Page");
        }
    }

    @Then("^I click on Beauty Box FAQ link$")
    public void i_click_on_Beauty_Box_FAQ_link() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify Beautybox FAQ link",
                Elements.elementPresent("my_subscription.myaccount_beautybox_FAQ_link"));
        Clicks.click("my_subscription.myaccount_beautybox_FAQ_link");
    }

    @Then("^I see beauty box FAQ page$")
    public void i_see_beauty_box_FAQ_page() throws Exception {
        Thread.sleep(3000);
        //WebDriverManager.getWebDriver().switchTo().window("Macy's Customer Service Site");
        switchWindow(1);
        Assert.assertTrue("Error - Unable to navigate Beautybox FAQ Page",
                WebDriverManager.getCurrentUrl().contains("faq"));
        switchWindowClose();
        switchWindow(0);
    }

    @Then("^I see manage subscription link in MyAccount page$")
    public void i_see_manage_subscription_link_in_myaccount_page() throws Throwable {
        Thread.sleep(2000);
        if (Elements.elementPresent("my_subscription.myaccount_manage_link")) {
            System.out.println("User is able to see Beautybox manage link in My Account Page");
        } else {
            Assert.fail("Error - User is unable to see Beautybox manage link in My Account Page");
        }
    }

    @Then("^I click on manage subscription link$")
    public void i_click_on_manage_subscription_link() throws Throwable {
        Thread.sleep(2000);
        Assert.assertTrue("Error - Unable to identify Beautybox Manage link",
                Elements.elementPresent("my_subscription.myaccount_manage_link"));
        Clicks.click("my_subscription.myaccount_manage_link");
    }

    @Then("^I see manage subscription page with subscriptionstatus$")
    public void i_see_manage_subscription_page_with_subscriptionstatus$() throws Throwable {
        Thread.sleep(2000);
        if (strStatusMyaccount.contains(strStatusManage)) {
            System.out.println("User is able to see the subscription status as:" + strStatusManage);
            Assert.assertTrue("User is able to see the subscription status as:" + strStatusManage, true);
        } else {
            Assert.fail("Subscription status is not matched with my account subscription status");
        }
    }

    @And("^I should not see subscription tile in MyAccount page$")
    public void I_should_not_see_subscription_tile_in_myaccount() throws Throwable {
        if (Elements.elementPresent("my_subscription.myaccount_subscription_tile")){
            Assert.fail("Error - subscription tile is displaying in MyAccount page");
        } else {
            Assert.assertTrue("subscription tile is on not displayed in MyAccount Page", true);
        }
    }
}

