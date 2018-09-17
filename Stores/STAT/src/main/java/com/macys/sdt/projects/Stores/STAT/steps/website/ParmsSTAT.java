package com.macys.sdt.projects.Stores.STAT.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.macys.sdt.projects.Stores.STAT.utils.STATUtils.typeTextNTab;


public class ParmsSTAT extends StepUtils {
    @Given("^I am in SmartParms$")
    public void iAmInSmartParms() throws Throwable {
        Navigate.visit("Parms");
        Elements.elementShouldBePresent("parms.verify_page");
        String ParmsTitle = Elements.findElement("parms.verify_page").getText();
        Assert.assertEquals("SMART POS Parameters", ParmsTitle);
    }


    @When("^I log into SmartParms$")
    public void iLogIntoSmartParms() throws Throwable {
        Wait.forPageReady();
        StepUtils.switchToFrame("parms.iframe_login");
        Wait.secondsUntilElementPresent("parms.parms_login", 5);
        Elements.elementShouldBePresent("parms.parms_login");
        Elements.elementShouldBePresent("parms.parms_password");
        typeTextNTab("parms.parms_login", "b002312");
        typeTextNTab("parms.parms_password", "james88");
        Clicks.click("parms.login_button");
    }

    @And("^I click on the Macys image$")
    public void iClickOnTheMacysImage() throws Throwable {
        Elements.elementShouldBePresent("parms.macys_image");
        Clicks.click("parms.macys_image");
    }

    @Then("^I click on the Refunds table$")
    public void iClickOnTheRefundsTable() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(1000);
        StepUtils.switchToFrame("parms.iframe_home");
        Wait.secondsUntilElementPresent("parms.refund_table", 5);
        Elements.elementShouldBePresent("parms.refund_table");
        Clicks.click("parms.refund_table");
    }

    @And("^I click on the Bloomingdales image$")
    public void iClickOnTheBloomingdalesImage() throws Throwable {
        Elements.elementShouldBePresent("parms.blm_image");
        Clicks.click("parms.blm_image");

    }



    @And("^I click on the RFone Table$")
    public void iClickOnTheRFTable() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(1000);
        StepUtils.switchToFrame("parms.iframe_home");
        Wait.secondsUntilElementPresent("parms.RF1_table", 5);
        Elements.elementShouldBePresent("parms.RF1_table");
        Clicks.click("parms.RF1_table");
        Thread.sleep(2000);
    }

    @Then("^I should see that Pending Returns is a refund option under unreceipted returns$")
    public void iShouldSeeThatPendingReturnsIsARefundOptionUnderUnreceiptedReturns() throws Throwable {
        Wait.forPageReady();
        StepUtils.switchToFrame("parms.iframe_home");
        Wait.secondsUntilElementPresent("parms.pending_return", 5);
        Elements.elementShouldBePresent("parms.pending_return");
        Thread.sleep(2000);
    }


    @When("^I click inside the checkbox$")
    public void iClickInsideTheCheckbox() throws Throwable {
        Wait.forPageReady();
        StepUtils.switchToFrame("parms.iframe_home");
        Wait.secondsUntilElementPresent("parms.pending_return", 5);
        Elements.elementShouldBePresent("parms.pending_return");
        Clicks.click("parms.pending_return");
        
}

    @Then("^I should see the Pending Returns number box is checked$")
    public void iShouldSeeThePendingReturnsNumberBoxIsChecked() throws Throwable {
        Wait.untilElementPresent("parms.pending_return");
        Assert.assertEquals("pending_return is selected", (Elements.findElement("parms.pending_return")).getAttribute("checked"), "true");






    }

    @Then("^I should see the Pending Returns number box is unchecked$")
    public void iShouldSeeThePendingReturnsNumberBoxIsUnchecked() throws Throwable {
        Wait.untilElementPresent("parms.pending_return");
        Assert.assertEquals("pending_return is selected", (Elements.findElement("parms.pending_return")).getAttribute("checked"), "false");
    }

    @Then("^I should see that Tracking Number is a refund option under unreceipted Returns$")
    public void iShouldSeeThatTrackingNumberIsARefundOptionUnderUnreceiptedReturns() throws Throwable {
        Wait.forPageReady();
        StepUtils.switchToFrame("parms.iframe_home");
        Wait.secondsUntilElementPresent("parms.tracking_number", 5);
        Elements.elementShouldBePresent("parms.tracking_number");
        Thread.sleep(2000);
    }

    @Then("^I should see the Tracking number box is checked$")
    public void iShouldSeeTheTrackingNumberBoxIsChecked() throws Throwable {
        Wait.untilElementPresent("parms.tracking_number");
        Assert.assertEquals("tracking_number is selected", (Elements.findElement("parms.tracking_number")).getAttribute("checked"), "true");

    }

    @Then("^I should see the Tracking Number box is unchecked$")
    public void iShouldSeeTheTrackingNumberBoxIsUnchecked() throws Throwable {
        Wait.untilElementPresent("parms.tracking_number");
        Assert.assertEquals("tracking_number is selected", (Elements.findElement("parms.tracking number")).getAttribute("checked"), "true");

    }
}