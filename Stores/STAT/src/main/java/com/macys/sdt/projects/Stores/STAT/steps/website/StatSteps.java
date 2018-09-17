package com.macys.sdt.projects.Stores.STAT.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.thoughtworks.selenium.webdriven.commands.Click;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.macys.sdt.projects.Stores.STAT.utils.STATUtils.typeTextNTab;



public class StatSteps extends StepUtils {

    @Given("^I am on Gift$")
    public void iAmOnGift() throws Throwable {
        Navigate.visit("Gift");
        Elements.elementShouldBePresent("gift.verify_page");
        String GiftTitle = Elements.findElement("gift.verify_page").getText();
        Assert.assertEquals("Gift Registry", GiftTitle);
    }

    @When("^I login$")
    public void iLogin() throws Throwable {
        Elements.elementShouldBePresent("gift.associate_number");
        Elements.elementShouldBePresent("gift.associate_password");
        Elements.elementShouldBePresent("gift.sign_in");
        typeTextNTab("gift.associate_number", "M-Gift_CAdvisor_DEV");
        typeTextNTab("gift.associate_password", "3r!TenaSPAw-");
        Clicks.click("gift.sign_in");


    }

    @Then("^I can see the Gift Home Page$")
    public void iCanSeeTheGiftHomePage() throws Throwable {
        Elements.elementShouldBePresent("gift.hamburger_menu");


    }


    @Then("^I can see the Link$")
    public void iCanSeeTheLink() throws Throwable {
        Elements.elementShouldBePresent("gift.vendor_bonus");
    }

    @Then("^I \"([^\"]*)\" see the Link$")
    public void iTheLink(String Result) throws Throwable {
        Thread.sleep(1000);
        switch (Result) {
            case "Do":
                Wait.untilElementPresent("gift.vendor_bonus");
                Elements.elementShouldBePresent("gift.vendor_bonus");
                String VendorBonus = Elements.findElement("gift.vendor_bonus").getText();
                Assert.assertEquals("Vendor Bonus Program", VendorBonus);
                break;
            case "DoNot":
                Wait.secondsUntilElementNotPresent("gift.vendor_bonus", 3);
                if (Elements.elementPresent("gift.vendor_bonus")) {
                    Assert.fail("The Vendor Bonus Link Displayed.... This User should not see the Vendor_Bonus Link.");

                }
                break;
        }
    }

    @Given("^I am in the Gift website$")
    public void iAmInTheGiftWebsite () throws Throwable {
        Navigate.visit("Gift");
        Elements.elementShouldBePresent("gift.verify_page");
        String GiftTitle = Elements.findElement("gift.verify_page").getText();
        Assert.assertEquals("Gift Registry", GiftTitle);
    }

    @When("^I log in as an Advisor$")
    public void iLogInAsAnAdvisor () throws Throwable {
        Elements.elementShouldBePresent("gift.associate_number");
        Elements.elementShouldBePresent("gift.associate_password");
        Elements.elementShouldBePresent("gift.sign_in");
        typeTextNTab("gift.associate_number", "M-Gift_CAdvisor_DEV");
        typeTextNTab("gift.associate_password", "3r!TenaSPAw-");
        Clicks.click("gift.sign_in");


    }

    @Then("^I see the hamburger/main menu$")
    public void iSeeTheHamburgerMainMenu () throws Throwable {
        Elements.elementShouldBePresent("gift.hamburger_menu");

    }

    @When("^I click the hamburger/main menu$")
    public void iClickTheHamburgerMainMenu () throws Throwable {
        Clicks.click("gift.hamburger_menu");

    }

    @Then("^I see the Vendor Bonus Program link$")
    public void iSeeTheVendorBonusProgramLink () throws Throwable {
        Elements.elementShouldBePresent("gift.vendor_bonus_link");
    }

    @When("^I log in as a Support$")
    public void iLogInAsASupport () throws Throwable {
        Elements.elementShouldBePresent("gift.associate_number");
        Elements.elementShouldBePresent("gift.associate_password");
        Elements.elementShouldBePresent("gift.sign_in");
        typeTextNTab("gift.associate_number", "M-Gift_Support_DEV");
        typeTextNTab("gift.associate_password", "hA8U-Aju&per");
        Clicks.click("gift.sign_in");
    }

    @When("^I log in as an Admin$")
    public void iLogInAsAnAdmin () throws Throwable {
        Elements.elementShouldBePresent("gift.associate_number");
        Elements.elementShouldBePresent("gift.associate_password");
        Elements.elementShouldBePresent("gift.sign_in");
        typeTextNTab("gift.associate_number", "M-Gift_CorpAdmin_DEV");
        typeTextNTab("gift.associate_password", "tHa-UGAdr4wU");
        Clicks.click("gift.sign_in");
    }

    @When("^I log in as a Director$")
    public void iLogInAsADirector () throws Throwable {
        Elements.elementShouldBePresent("gift.associate_number");
        Elements.elementShouldBePresent("gift.associate_password");
        Elements.elementShouldBePresent("gift.sign_in");
        typeTextNTab("gift.associate_number", "M-Gift_Director_DEV");
        typeTextNTab("gift.associate_password", "p+?4a2uJenes");
        Clicks.click("gift.sign_in");
    }

    @Then("^I do not see the Vendor Bonus Program link$")
    public void iDoNotSeeTheVendorBonusProgramLink () throws Throwable {
        Wait.untilElementNotPresent("gift.vendor_bonus_link");
    }

    @When("^I log in as a General$")
    public void iLogInAsAGeneral () throws Throwable {
        Elements.elementShouldBePresent("gift.associate_number");
        Elements.elementShouldBePresent("gift.associate_password");
        Elements.elementShouldBePresent("gift.sign_in");
        typeTextNTab("gift.associate_number", "M-Gift_MCCS_DEV");
        typeTextNTab("gift.associate_password", "s!awam5Dupha");
        Clicks.click("gift.sign_in");

    }

    @When("^I click the Vendor Bonus Program link$")
    public void iClickTheVendorBonusProgramLink () throws Throwable {
        Clicks.click("gift.vendor_bonus_link");
    }

    @Then("^I am on the Vendor Bonus Program page$")
    public void iAmOnTheVendorBonusProgramPage () throws Throwable {
        Elements.elementShouldBePresent("gift.verify_page");
        String GiftTitle = Elements.findElement("gift.verify_page").getText();
        Assert.assertEquals("Macy's Gift Registry", GiftTitle);
    }

    @And("^I see the Search icon$")
    public void iSeeTheSearchIcon () throws Throwable {
        Elements.elementShouldBePresent("gift.search_icon");
    }


    @When("^I login as an \"([^\"]*)\"$")
    public void iLoginAsAn (String AssociateRole) throws Throwable {
        switch (AssociateRole) {
            case "Adviser":
                Wait.untilElementPresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_password");
                Elements.elementShouldBePresent("gift.sign_in");
                typeTextNTab("gift.associate_number", "M-Gift_CAdvisor_DEV");
                typeTextNTab("gift.associate_password", "3r!TenaSPAw-");
                Clicks.click("gift.sign_in");
                break;
            case "Director":
                Wait.untilElementPresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_password");
                Elements.elementShouldBePresent("gift.sign_in");
                typeTextNTab("gift.associate_number", "M-Gift_Director_DEV");
                typeTextNTab("gift.associate_password", "p+?4a2uJenes");
                Clicks.click("gift.sign_in");
                break;
            case "Support":
                Wait.untilElementPresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_password");
                Elements.elementShouldBePresent("gift.sign_in");
                typeTextNTab("gift.associate_number", "M-Gift_Support_DEV");
                typeTextNTab("gift.associate_password", "hA8U-Aju&per");
                Clicks.click("gift.sign_in");
                break;
            case "CorpAdmin":
                Wait.untilElementPresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_password");
                Elements.elementShouldBePresent("gift.sign_in");
                typeTextNTab("gift.associate_number", "M-Gift_CorpAdmin_DEV");
                typeTextNTab("gift.associate_password", "tHa-UGAdr4wU");
                Clicks.click("gift.sign_in");
                break;

            case "MCCS_DEV":
                Wait.untilElementPresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_password");
                Elements.elementShouldBePresent("gift.sign_in");
                typeTextNTab("gift.associate_number", "M-Gift_MCCS_DEV");
                typeTextNTab("gift.associate_password", "s!awam5Dupha");
                Clicks.click("gift.sign_in");
                break;
            case "HSAssoc":
                Wait.untilElementPresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_number");
                Elements.elementShouldBePresent("gift.associate_password");
                Elements.elementShouldBePresent("gift.sign_in");
                typeTextNTab("gift.associate_number", "M-Gift_HSAssoc_DEV");
                typeTextNTab("gift.associate_password", "Sefruguw-w6g");
                Clicks.click("gift.sign_in");
                break;


        }

    }



    @And("^I see the Application Title$")
    public void iSeeTheApplicationTitle () throws Throwable {
        Elements.elementShouldBePresent("gift.verify_page");
    }

    @And("^I see the Welcome Info$")
    public void iSeeTheWelcomeInfo () throws Throwable {
        Elements.elementShouldBePresent("gift.associate_header");
    }

    @And("^I see Create Icon$")
    public void iSeeCreateIcon () throws Throwable {
        Elements.elementShouldBePresent("gift.create_icon");
    }

    @And("^I see the title Vendor Bonus Program$")
    public void iSeeTheTitleVendorBonusProgram () throws Throwable {
        Elements.elementShouldBePresent("gift.vendor_bonus_title");

    }

    @Then("^I see the Create Request Link$")
    public void iSeeTheCreateRequestLink () throws Throwable {
        Elements.elementShouldBePresent("gift.create_vendor_bonus");

    }

    @When("^I click on the Create Request Link$")
    public void iClickOnTheCreateRequestLink() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.create_request_link");
        Clicks.click("giftvendorbonus.create_request_link");
    }

    @Then("^I am on the Vendor Bonus Request Page$")
    public void iAmOnTheVendorBonusRequestPage() throws Throwable {
        Elements.elementShouldBePresent("giftvendorbonus.vendorBonusRequestTitle");

    }



}
