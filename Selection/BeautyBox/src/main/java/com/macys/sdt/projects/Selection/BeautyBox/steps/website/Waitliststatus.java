package com.macys.sdt.projects.Selection.BeautyBox.steps.website;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Selection.BeautyBox.utils.website.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.Statement;


/**
 * Created by YH02356 on 4/13/17.
 */





public class Waitliststatus extends StepUtils {

    private static String dbName = "oracle";
    private static String dbUrl ="jdbc:oracle:thin:@jcia9766:1521:BBXQA01";
    private static String dbUsername = "bbxsuba1";
    private static String dbPassword = "subadm1n";
    private static String MacysUID = null;

    @Then("^I should see subscription popup$")
    public void i_should_see_subscription_popup() throws Throwable{
        Elements.elementPresent("about_subscribe.waitlist_continue");
    }

    @When("^I clicked on continue button in the popup$")
    public void i_clicked_on_continue_button_in_the_popup() throws Throwable{
        Clicks.click("about_subscribe.waitlist_continue");
        Thread.sleep(3000);
    }

    @Then ("^I should navigate to welcome waitlist page$")
    public void i_should_navigate_to_welcome_waitlist_page() throws Throwable{
        Elements.elementPresent("about_subscribe.waitlist_confirmation");

        if (Elements.elementPresent("about_subscribe.waitlist_confirmation")){
            Assert.assertTrue("User is successfully joined in WaitList ", true);

        } else {
            Assert.fail("Error - Walit list confirmation page is not showing up");
        }
    }

    @Then("^I launch beautybox ManageSubscriptions page url$")
    public void I_launch_beautybox_managesubscriptions_page_url() throws Throwable {
        Navigate.visit(RunConfig.url + "/subscription/manageSubscriptions");
    }

    @And("^I should not see shipping section$")
    public void I_should_not_see_shipping_section() throws Throwable {
        if (Elements.elementPresent("beauty_subscription.create_shipping_change_btn")){
            Assert.fail("Error - identified change button to add new shipping address on manage subscription");
        } else {
            Assert.assertTrue("shipping section is on not displayed manage subscription", true);
        }
    }
    @And("^I should not see creditcard section$")
    public void I_should_not_see_creditcard_section() throws Throwable {
        if (Elements.elementPresent("beauty_subscription.create_payment_change_btn")){
            Assert.fail("Error - identified change button to add new creditcard on manage subscription");
        } else {
            Assert.assertTrue("creditcard section is on not displayed manage subscription", true);
        }
    }
    @And("^I should not see cancel button$")
    public void I_should_not_see_cancel_button() throws Throwable {
        if (Elements.elementPresent("eauty_subscription.cancel_subscription")){
            Assert.fail("Error - identified cancel button on manage subscription");
        } else {
            Assert.assertTrue("cancel button is on not displayed manage subscription", true);
        }
    }
    @And("^I have updated user status$")
    public void I_have_updated_user_status() throws Throwable {
        MacysUID = GenericUtilsBeauty.getUIDCookie();
        System.out.print("uid captured:" +MacysUID);
        Statement stmt;
        try {
            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            String sql =  "update user_entitlement set user_entl_status = 'WAITLIST_NOTIFIED' where user_guid = &MacysUID";
            stmt.executeUpdate(sql);
            System.out.print("query executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }

    }

    private  static String strErrcreditMsg = "Please enter a valid card number";

    @And ("^I should see valid creditcard error message$")
    public void I_should_see_vaid__creditcard_error_message() throws Throwable{

        String strErrorMessage =  Elements.findElement(Elements.element("beauty_subscription.credit_card_invalid_format")).getText();
        System.out.println(strErrorMessage);
        if (strErrorMessage.equals(strErrcreditMsg)){
            Assert.assertTrue("Displaying valid error message when user enter invalid credit card format::"+strErrorMessage, true);
        }
        else{
            Assert.fail("Its not throwing valid error message if it is invalid credit card format::"+strErrorMessage);
        }
    }

    private  static String strErrcreditexpireMsg = "Please enter a valid expiration date.";

    @And ("^I should see valid creditcardexpritaion error message$")
    public void I_should_see_vaid__creditcardexpiration_error_message() throws Throwable{

        String strErrorMessage =  Elements.findElement(Elements.element("beauty_subscription.credit_card_expire_format")).getText();
        System.out.println(strErrorMessage);
        if (strErrorMessage.equals(strErrcreditexpireMsg)){
            Assert.assertTrue("Displaying valid error message when user enter expire credit card date::"+strErrorMessage, true);
        }
        else{
            Assert.fail("Its not throwing valid error message if it is invalid credit card expire::"+strErrorMessage);
        }
    }

    private  static String strErrcreditmismatchMsg = "Hmm…the card number and card type do not match.";

    @And ("^I should see valid creditcardtype error message$")
    public void I_should_see_vaid_creditcardetype_error_message() throws Throwable{

        String strErrorMessage =  Elements.findElement(Elements.element("beauty_subscription.credit_card_mismatch_format")).getText();
        System.out.println(strErrorMessage);
        if (strErrorMessage.equals(strErrcreditmismatchMsg)){
            Assert.assertTrue("Displaying valid error message when user enter mismatch credit card format::"+strErrorMessage, true);
        }
        else{
            Assert.fail("Its not throwing valid error message if it is invalid credit card expire::"+strErrorMessage);
        }
    }

    private  static String strshippinginfoMsg = "Macy’s Beauty Box is currently only available in the 48 contiguous United States. We cannot ship to Alaska, Hawaii, territories, PO, APO, or GPO boxes at this time.";

    @And ("^I should see static message of shipping$")
    public void I_should_see_static_message_of_shipping() throws Throwable{

        String strshippinginfo =  Elements.findElement(Elements.element("beauty_subscription.staticshippingmessage")).getText();
        System.out.println(strshippinginfo);
        if (strshippinginfo.equals(strshippinginfoMsg)){
            Assert.assertTrue("Displaying valid info message info for shipping::"+strshippinginfo, true);
        }
        else{
            Assert.fail("Invalid info message info for shipping::"+strshippinginfo);
        }
    }

    @And ("^I should see static message of shipping in creditcard section$")
    public void I_should_see_static_message_of_shipping_in_creditcard_section() throws Throwable{

        String strshippinginfoincreditcard =  Elements.findElement(Elements.element("beauty_subscription.staticshippingmsgcredit")).getText();
        System.out.println(strshippinginfoincreditcard);
        if (strshippinginfoincreditcard.equals(strshippinginfoMsg)){
            Assert.assertTrue("Displaying valid info message info for shipping::"+strshippinginfoincreditcard, true);
        }
        else{
            Assert.fail("Invalid info message info for shipping::"+strshippinginfoincreditcard);
        }
    }
}
