package com.macys.sdt.projects.Marketing.ShopRunner.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShoprunnerStepdefs extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ShoprunnerStepdefs.class);


    @Then("^I should see the \"([^\"]*)\" Shoprunner eligibility text$")
    public void i_should_see_the_Shoprunner_eligibility_text(String msg) throws Throwable {
        String actualMessage = Elements.getText("shoprunner.eligible");
        System.out.print(actualMessage);
        Assert.assertTrue("shoprunner eligible message is not displayed", actualMessage.equals(msg));
        logger.info("Shoprunner eligible message is displayed");
    }

    @When("^I click on 'SRSIGNIN' link$")
    public void iClickOnSRSIGNINLink() throws Throwable {
        Wait.untilElementPresent("shoprunner.srsignin_link");
        Clicks.click("shoprunner.srsignin_link");
        logger.info("Shoprunner signin link is clicked");
    }


    @And("^I Should see the Shoprunner overlay$")
    public void iShouldSeeTheShoprunnerOverlay() throws Throwable {
        Elements.elementPresent("shoprunner.srlearnmore_signin");
        logger.info("Shoprunner Overlay is displayed");

    }

    @When("^I select member product from list$")
    public void iSelectMemberProductFromList() throws Throwable {
        Clicks.click("shoprunner.memeberproduct");
    }

    @And("^I close the Shoprunner Overlay$")
    public void iCloseTheShoprunnerOverlay() throws Throwable {
        Elements.elementShouldBePresent("shoprunner.srclose");
        logger.info("Shoprunner Overlay is closed");
    }

    @And("^I should see the SRSignin and SRLearnmore links$")
    public void iShoudSeeTheSRSigninAndSRLearnmoreLinks() throws Throwable {
        Elements.elementPresent("shoprunner.srsignin");
        Elements.elementPresent("shoprunner.srlearnmore");
        Assert.assertTrue("Able to see srsignin and srlearnmorelink on checkout page", true);
        logger.info("Shoprunner Signin and Learnmore links displayed");
    }

    @And("^I should see the shoprunner eligible logo on member products$")
    public void iShouldSeeTheShoprunnerEligibleLogoOnMemberProducts() throws Throwable {
        Elements.elementShouldBePresent("shoprunner.srlogo");
        System.out.println("shoprunner Logo is displayed");
        logger.info("Shoprunner eligible logo displayed");
    }

    @Then("^I (should not|should) see the FREE two Day Shipping Free Returns Shoprunner eligibility text$")
    public void iShouldNotSeeTheFREETwoDayShippingFreeReturnsShoprunnerEligibilityText(String message_display) throws Throwable {
        String page = bloomingdales() ? "shoprunner" : "shoprunner";
        if (message_display.contains("not"))
            Assert.assertFalse( "For Card with age : " + message_display + " shoprunner text is NOT supressed in PDP page", Elements.elementPresent(page + ".eligible"));
        else
            Assert.assertTrue("For Card with age : " + message_display + " shoprunner text is  supressed in PDP page", Elements.elementPresent(page + ".eligible"));
    }

    @And("^I signin to Shoprunner account$")
    public void iSigninToShoprunnerAccount() throws Throwable {
        Clicks.click("shoprunner.srlearnmore_signin");
        TextBoxes.typeTextbox("shoprunner.sremail", "bloom-test@shoprunner.com");
        TextBoxes.typeTextbox("shoprunner.srpassword", "test1234");
        Clicks.click("shoprunner.shoprunner_signin");
        Wait.secondsUntilElementPresent("shoprunner.srclose", 50);
        Clicks.clickIfPresent("shoprunner.srclose");
        System.out.println("shoprunner signin button is clicked on checkout page ");
        logger.info("Shoprunner Signin is Successfull");
    }

    @Then("^I should see the \"([^\"]*)\" link$")
    public void iShouldSeeTheLink(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I should see the shoprunner eligible logo on checkout page$")
    public void iShouldSeeTheShoprunnerEligibleLogoOnCheckoutPage() throws Throwable {
        Elements.elementShouldBePresent("shoprunner.checkout_srlogo");
        System.out.println("shoprunner Logo is displayed on checkout page ");
    }

    @Then("^I should see the \"([^\"]*)\" text display$")
    public void iShouldSeeTheTextDisplay(String msg) throws Throwable {
        String actualMessage = Elements.getText("shoprunner.shipping_disclaimer");
        System.out.print(actualMessage);
        Assert.assertTrue("shipping disclaimer text is not displayed", actualMessage.equals(msg));
    }

    @And("^I should see the \"([^\"]*)\" Shoprunner eligibility text on checkout page$")
    public void iShouldSeeTheShoprunnerEligibilityTextOnCheckoutPage(String message) throws Throwable {
        String actualMessage = Elements.getText("shoprunner.srtext");
        System.out.print(actualMessage);
        Assert.assertTrue("shoprunner eligible text message is not displayed", actualMessage.equals(message));
    }

    @And("^I should see the SRSignout link$")
    public void iShouldSeeTheSRSignoutLink() throws Throwable {
        Elements.elementPresent("shoprunner.srsignout");
        Assert.assertTrue("SRSignout link is displayed on checkout page after successfull signin", true);
        System.out.println("shoprunner signout link is displayed");
        /*Thread.sleep(8000);*/
    }


    @And("^I should see \"([^\"]*)\" method selected by default$")
    public void iShouldSeeMethodSelectedByDefault(String text) throws Throwable {
        if (text.equals("Standard"))
        Assert.assertTrue("standard shipping is not selected by default", Elements.getText("shoprunner.selected_shipping_method").contains("Standard\n" +
                "(3-6 business days)"));
        if (text.equals("ShopRunner")) {
            Assert.assertTrue("shoprunner shipping is not selected by default", Elements.getText("shoprunner.selected_shipping_method").contains("ShopRunner Free 2-Day\n" +
                    "(2 business days)"));
        }

    }

    @And("^I click on change in shipping section$")
    public void iClickOnChangeInShippingSection() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("shoprunner.change_shipping_method", 5);
       /* Assert.assertTrue( message: "Change button is clicked to edit the shipping method")*/

    }
    @When("^I select 'ShopRunner Free 2-Day' in shipping methods$")
    public void i_select_shoprunner_in_shipping_methods() {
         Clicks.click("shoprunner.sr_shipping_methods_set");
              logger.info("Shoprunner shipping method is selected ");
    }

    @Then("^I verify (ShopRunner) shipping method details on confirmation page$")
    public void I_Verify_ShopRunner_In_Shipping_Method_Section(String expectedText) throws Throwable {
        String actualText;
        expectedText = expectedText.toLowerCase();
        actualText = Elements.getText(Elements.element("shoprunner.sr_shipping_methods_set")).split("\n")[1].toLowerCase();
        Assert.assertTrue("Expected shipping method is not displayed!!", (actualText.contains(expectedText)));
    }

    @And("^I verify the \"([^\"]*)\" shipping method text display in shipping summary section$")
    public void iVerifyTheShippingMethodTextDisplayInShippingSummarySection(String shippingmethod) throws Throwable {
        Wait.untilElementPresent("shoprunner.sr_shipping_method");
        Assert.assertTrue("Shoprunner shipping method is not displayed in shipping summary section", Elements.getText("shoprunner.sr_shipping_method").contains(shippingmethod));

    }
  }

