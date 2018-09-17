package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Created by yh03402 on 5/23/2017.
 */
public class RegistryHomeSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(RegistryHomeSteps.class);

    @When("^I search for \"([^\"]*)\" in registry mode$")
    public void i_search_for_in_registry_mode(String searchText) throws Throwable {
        if (bloomingdales()) {
            TextBoxes.typeTextNEnter("registry_home.search_field", searchText);
            Wait.forPageReady();
        }
    }

    @Then("^I verify links are displayed on registry home page$")
    public void i_verify_below_links_on_registry_home_page_are_displayed() throws Throwable {

        Assert.assertTrue("Find registry is not displayed",
                Elements.findElement("registry_home.find_registry").isDisplayed());
        Assert.assertTrue("Find registry is not displayed",
                Elements.findElement("registry_home.create_registry").isDisplayed());
        Assert.assertTrue("Find registry is not displayed",
                Elements.findElement("registry_home.mange_registry").isDisplayed());

        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I should see expected media types on registry home page as in below table:$")
    public void i_should_see_expected_media_types_on_registry_home_page_as_in_below_table(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)

        List<String> attributes = dataTable.asList(String.class);
        for (String attribute : attributes) {
            if (attribute.equalsIgnoreCase("find registry")) {
                Assert.assertTrue("Find registry is not displayed",
                        Elements.findElement("registry_home.find_registry").isDisplayed());
                Clicks.click("registry_home.find_registry");
                shouldBeOnPage("find_registry");
                logger.info("find a registry link checked succesfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();
                Utils.threadSleep(10000,"Waiting for page loading");
            } else if (attribute.equalsIgnoreCase("create registry")) {
                Assert.assertTrue("create registry is not displayed",
                        Elements.findElement("registry_home.create_registry").isDisplayed());
                Clicks.click("registry_home.create_registry");
                onPage("create_registry");
                logger.info("create registry link checked successfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();
            } else if (attribute.equalsIgnoreCase("manage registry")) {
                Assert.assertTrue("manage registry is not displayed",
                        Elements.findElement("registry_home.mange_registry").isDisplayed());
                Clicks.click("registry_home.mange_registry");
                shouldBeOnPage("registry_sign_in");
                logger.info("manage registry link checked successfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();
            } else if (attribute.equalsIgnoreCase("registry star reward")) {
                Assert.assertTrue("Registry star reward ad is missing on registry home page",
                        Elements.findElement("registry_home.registry_star_rewards").isDisplayed());
                Clicks.click("registry_home.registry_star_rewards");
                Wait.forPageReady();
                Assert.assertTrue("Not navigated to registry star rewards page",
                        WebDriverManager.getCurrentUrl().contains("/social/registry-star-rewards/")) ;
                logger.info("registry star reward sub ad checked successfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();
           /* } else if (attribute.equalsIgnoreCase("registry gift")) {
                Assert.assertTrue("Registry gift ad is missing on registry home page",
                        Elements.findElement("registry_home.registry_gift").isDisplayed());
                pausePageHangWatchDog();
                try {
                    Clicks.click("registry_home.registry_gift");
                } catch (Exception e) {
                    Thread.sleep(5000);
                    Assert.assertTrue("ERROR - TIMEOUT: Registry gift ad selection is taking longer time to load page" + e.getMessage(),
                            WebDriverManager.getCurrentUrl().contains("/social/registry-gift/"));
                }
                Assert.assertTrue("Not navigated to registry gift page",
                        WebDriverManager.getCurrentUrl().contains("/social/registry-gift/") &&
                                DsvActions.responseCode(WebDriverManager.getCurrentUrl()));
                resumePageHangWatchDog();
                logger.info("registry gift sub ad checked successfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();*/

            } else if (attribute.equalsIgnoreCase("registry Tailgate Time")) {
                Assert.assertTrue("Registry Tailgate time ad is missing on registry home page",
                        Elements.findElement("registry_home.registry_tailgate_time").isDisplayed());
                pausePageHangWatchDog();
                try {
                    Clicks.click("registry_home.registry_tailgate_time");
                } catch (Exception e) {
                    Thread.sleep(5000);
                    Assert.assertTrue("ERROR - TIMEOUT: Registry gift ad selection is taking longer time to load page" + e.getMessage(),
                            WebDriverManager.getCurrentUrl().contains("/social/registry-guide/inspiration/tailgatetime/"));
                }
                Assert.assertTrue("Not navigated to registry gift page",
                        WebDriverManager.getCurrentUrl().contains("/social/registry-guide/"));
                resumePageHangWatchDog();
                logger.info("registry Tailgate Time sub ad checked successfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();

            } else if (attribute.equalsIgnoreCase("registry events")) {
                Assert.assertTrue("Registry events ad is missing on registry home page",
                        Elements.findElement("registry_home.registry_events").isDisplayed());
                Clicks.click("registry_home.registry_events");
                Wait.forPageReady();
                Assert.assertTrue("Not navigated to registry events page",
                        WebDriverManager.getCurrentUrl().contains("/social/registry-events/"));
                logger.info("registry events sub ad checked successfully..!!");
                Navigate.browserBack();
                Wait.forPageReady();
            }

        }
    }
}



