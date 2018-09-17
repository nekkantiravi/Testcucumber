package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.util.logging.Logger;


public class StepDefinitionsShoppingBag extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();
    public static String bagId = "";
    public static String message = "";


    @When("^I click the product \"([^\"]*)\" link on shopping bag page$")
    public void click_the_buttons_on_shopping_bag_page(String arg) throws Throwable {
        if (arg.equals("image")) {
            Wait.secondsUntilElementPresent("shopping_bag.productImage", 20);
            softly.assertThat(Elements.elementPresent("shopping_bag.productImage")).as("shopping_bag.productImage").isEqualTo(true);
            Thread.sleep(5000);
            Clicks.click("shopping_bag.productImage");
        }
        else if (arg.equals("remove")) {
            Wait.secondsUntilElementPresent("shopping_bag.productImage", 20);
            softly.assertThat(Elements.elementPresent("shopping_bag.removeBtn")).as("shopping_bag.removeBtn").isEqualTo(true);
            Thread.sleep(5000);
            Clicks.click("shopping_bag.removeBtn");
        }
    }

    @Then("^I verify basic elements of shopping bag page in \"([^\"]*)\" mode")
    public void verify_basic_elements_of_shopping_bag_page(String mode) throws Throwable {
        Wait.secondsUntilElementPresent("shopping_bag.productImage", 20);
        softly.assertThat(Elements.elementPresent("shopping_bag.shoppingbagId")).as("shopping_bag.shoppingbagId").isEqualTo(true);
        Assert.assertNotNull(Elements.findElement(Elements.element("shopping_bag.shoppingbagId")).getText());
        bagId = Elements.findElement(Elements.element("shopping_bag.shoppingbagId")).getText();
        logger.info(String.format("****>> " + bagId));
        softly.assertThat(Elements.elementPresent("shopping_bag.removeBtn")).as("shopping_bag.removeBtn").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("shopping_bag.productImage")).as("shopping_bag.productImage").isEqualTo(true);
        if(mode.equals("iship"))
            Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
        else
            Assert.assertFalse(Elements.elementPresent("pdp.iShipCountryFlag"));
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify shopping bag ID is displayed")
    public void verify_shopping_bag_ID_is_displayed() throws Throwable {
        Thread.sleep(2000);
        softly.assertThat(Elements.elementPresent("shopping_bag.shoppingbagId")).as("shopping_bag.shoppingbagId").isEqualTo(true);
        bagId = Elements.findElement(Elements.element("shopping_bag.shoppingbagId")).getText();
        logger.info(String.format("***>> " + bagId));
        softly.assertThat(bagId.equals("")).isEqualTo(false);
        softly.assertAll();
    }

    @Then("^I verify shopping bag ID is removed")
    public void verify_shopping_bag_ID_is_removed() throws Throwable {
        Thread.sleep(5000);
        softly.assertThat(Elements.elementPresent("shopping_bag.shoppingbagId")).as("shopping_bag.shoppingbagId").isEqualTo(true);
        bagId = Elements.findElement(Elements.element("shopping_bag.shoppingbagId")).getText();
        logger.info(String.format("BagId after removing items is null as expected ***>> " + bagId));
        softly.assertThat(bagId.equals(""));
        softly.assertAll();
    }

    @Then("^I verify the \"([^\"]*)\" message on shopping bag page in \"([^\"]*)\" mode")
    public void verify_the_msg_on_shopping_bag_page(String arg, String mode) throws Throwable {
        Thread.sleep(3000);
        switch (arg) {
            case "empty bag": {
                softly.assertThat(Elements.elementPresent("shopping_bag.errorMsg")).as("shopping_bag.errorMsg").isEqualTo(true);
                message = Elements.findElement(Elements.element("shopping_bag.errorMsg")).getText();
                logger.info(String.format("Message after removing items ***>> " + message));
                Assert.assertFalse(message.equals(""));
                Assert.assertTrue(message.equals("Your Current Shopping Bag is empty."));
                softly.assertAll();
                break;
            }
            case "removed": {
                softly.assertThat(Elements.elementPresent("shopping_bag.itemRemovedMsg")).as("shopping_bag.itemRemovedMsg").isEqualTo(true);
                message = Elements.findElement(Elements.element("shopping_bag.itemRemovedMsg")).getText();
                logger.info(String.format("Removed message after removing items ***>> " + message));
                Assert.assertFalse(message.equals(""));
                Assert.assertTrue(message.equals("Removed from bag"));
                softly.assertAll();
                break;
            }
        }
        if(mode.equals("iship")) {
            Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
            if(arg.equals("unavailable")) {
                softly.assertThat(Elements.elementPresent("shopping_bag.iShipHeaderErrorMsg")).as("Items Currently Unavailable Message").isEqualTo(true);
                //message = Elements.findElement(Elements.element("shopping_bag.iShipHeaderErrorMsg")).getText();
                message = "We're sorry; the highlighted items are not available for shipping to Sweden. Please remove the item(s) from your shopping bag to proceed.";
                softly.assertThat(Elements.findElement("shopping_bag.iShipHeaderErrorMsg").getText()).as("iShip product unavailable message").isEqualTo(message);
                logger.info(String.format("Currently Unavailable ***>> " + message));
                //Assert.assertFalse(message.equals(""));
                //Assert.assertTrue(message.equals("We're sorry. The highlighted item(s) in your Shopping Bag are currently unavailable. Please delete the item(s) to proceed."));
                softly.assertAll();
            }
            else if(arg.equals("unavailable international")) {
                softly.assertThat(Elements.elementPresent("shopping_bag.iShipBagErrorMsg")).as("Items Internationally Unavailable Message").isEqualTo(true);
                message = Elements.findElement(Elements.element("shopping_bag.iShipBagErrorMsg")).getText();
                logger.info(String.format("Internationally Unavailable ***>> " + message));
                Assert.assertFalse(message.equals(""));
                Assert.assertTrue(message.contains("This item is not available for shipping to "));
                softly.assertAll();
            }
        }
    }

}
