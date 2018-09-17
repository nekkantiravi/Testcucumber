package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.mew;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.macys.sdt.framework.interactions.Elements;
import org.openqa.selenium.By;
import org.junit.Assert;

/**
 * Created by yh03392 on 9/21/17.
 */
public class ShipAndPickUp extends StepUtils{

    private static final Logger logger = LoggerFactory.getLogger(ShipAndPickUp.class);

    @And("^I click on the check availability in stores link$")
    public void i_click_on_the_check_availability_in_stores_link() throws Throwable {
        Wait.untilElementPresent("bopsslidein.shopping_bag_wait");
        Clicks.click(By.xpath("//*[@class=\"mb-bag-bops-button change-store\"]"));
        logger.info("Able to Click on Check availability in stores link ");
    }

    @Then("^I should see a store finder$")
    public void I_should_see_a_store_finder() throws Throwable {
        Elements.elementPresent("bopsslidein.bops_slide_in");
        logger.info("Able to view the Slide In ");
    }

    @And("^I see results in store finder$")
    public void I_see_results_in_store_finder() throws Throwable {
        Assert.assertTrue(Wait.untilElementPresent("bopsslidein.slide_in_storeresults"));

    }

    @And("^I select a random store$")
    public void I_select_a_random_store() throws Throwable {
        Clicks.clickRandomElement("bopsslidein.slide_in_radiobutton");
        logger.info("Random Store got selected ");
    }

    @And("^I click on Pick it up option for the product$")
    public void I_click_on_Pick_it_up_option_for_the_product() throws Throwable {
        Wait.untilElementNotPresent("bopsslidein.bops_slide_in");

        Clicks.javascriptClick("bopsslidein.bopsbag_pickup_radiobutton");

    }

    @And("^I verify the messaging as greyed out$")
    public void I_verify_the_messaging_as_greyed_out() throws Throwable {
        Assert.assertTrue(Wait.untilElementPresent("bopsslidein.greyed_out"));

    }

    @And("^I select a nearest store using \"([^\"]*)\" search$")
    public void iSelectANearestStoreUsingSearch(String zipCode) throws Throwable {
        Wait.untilElementPresent("bopsslidein.bops-address");
        Wait.untilElementPresent("bopsslidein.clear-button");
        if (Elements.elementPresent("bopsslidein.clear-button")){
            Clicks.click("bopsslidein.clear-button");
        }
        Elements.findElement("bopsslidein.bops-address").sendKeys(zipCode);
        Clicks.click("bopsslidein.radius-selection");
        Wait.secondsUntilElementPresent("bopsslidein.find_store_search_results",10);
        Clicks.clickRandomElement("bopsslidein.find_store_radio_button");

    }
}
