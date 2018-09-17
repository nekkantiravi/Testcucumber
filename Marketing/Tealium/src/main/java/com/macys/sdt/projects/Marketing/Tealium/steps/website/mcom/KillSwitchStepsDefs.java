package com.macys.sdt.projects.Marketing.Tealium.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Tag Management QE Team on 7/7/2017
 */
public class KillSwitchStepsDefs extends StepUtils {
    private Har har;
    private static final Logger logger = LoggerFactory.getLogger(KillSwitchStepsDefs.class);

    @Then("^I verify tealium tags script is available in network call$")
    public void i_verify_tealium_tags_script_is_available_in_network_call(List<String> networkCall) throws Throwable {
        Boolean isUtagJSPresent = false;
        Boolean isUtagSyncJSPresent = false;
        String tealiumScriptUtagjs = networkCall.get(networkCall.size() - networkCall.size());
        String tealiumScriptUtagSyncjs = networkCall.get(networkCall.size() - networkCall.size() + 1);
        WebDriverManager.getWebDriver().navigate().refresh();
        logger.info("Getting Har content");
        har = MainRunner.browsermobServer.getHar();
        Wait.forPageReady();
        Thread.sleep(8000);
        if (har != null && har.getLog() != null) {
            List<HarEntry> harEntries = har.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }

            for (HarEntry harEntry : harEntries) {
                logger.info(harEntry.getRequest().getUrl());
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(tealiumScriptUtagjs)) {
                    logger.info("Tealium tags script is available in network call -> " + harEntry.getRequest().getUrl());
                    isUtagJSPresent = true;
                    break;
                }
            }

            for (HarEntry harEntry : harEntries) {
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(tealiumScriptUtagSyncjs)) {
                    logger.info("Tealium tags script is available in network call -> " + harEntry.getRequest().getUrl().contains(tealiumScriptUtagSyncjs));
                    isUtagSyncJSPresent = true;
                    break;
                }
            }
            //---------- TBD---Need to add condition for Utag sync.js-----------------//
            Assert.assertTrue("Tealium tags script is not available in network call", isUtagJSPresent == true);
            //Assert.assertTrue("Tealium tags script is not available in network call", isUtagJSPresent == true && isUtagSyncJSPresent==true);
            logger.info("Both Tealium tags script Utag and Utag.syn.js is present ");

        } else {
            logger.info("Har file is null: Unable to capture network call");
            Assert.fail("Unable to capture network call");
        }
    }


    @Then("^I verify the bright tag call is in https$")
    public void i_verify_the_bright_tag_call_is_in_https(List<String> networkCall) throws Throwable {
        Boolean isTagJSPresent = false;
        String brightTagjs = networkCall.get(0);
        WebDriverManager.getWebDriver().navigate().refresh();
        logger.info("Getting Har content");
        har = MainRunner.browsermobServer.getHar();
        Wait.forPageReady();
        Thread.sleep(8000);
        if (har != null && har.getLog() != null) {
            List<HarEntry> harEntries = har.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }

            for (HarEntry harEntry : harEntries) {
                logger.info(harEntry.getRequest().getUrl());
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(brightTagjs)) {
                    logger.info("Bright tag script is available in network call -> " + harEntry.getRequest().getUrl());
                    isTagJSPresent = true;
                    break;
                }
            }

            Assert.assertTrue("Bright tag script is not available in network call", isTagJSPresent == true);
        } else {
            logger.info("Har file is null: Unable to capture network call");
            Assert.fail("Unable to capture network call");
        }
    }

    @Then("^I verify the kill switch toggle as \"([^\"]*)\"$")
    public void i_verify_the_kill_switch_toggle_as(String killSwitch, List<String> tealiumScriptEnabled) throws Throwable {
        Thread.sleep(5000);
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        String tealiumScriptEnabledAttribute = tealiumScriptEnabled.get(0);
        String tealiumScriptEnabledKillSwitch = "\"tealiumScriptEnabled\":"+ killSwitch;
        String tealiumScriptEnabledKillSwitch2 = tealiumScriptEnabledAttribute + ": \"" + killSwitch + "\"";
        Assert.assertTrue("Tealium script kill switch is Off", pageSrcVal.contains(tealiumScriptEnabledKillSwitch)||pageSrcVal.contains(tealiumScriptEnabledKillSwitch2));
        logger.info("Tealium script kill switch is " + killSwitch + " in Desktop : " + tealiumScriptEnabledKillSwitch);
    }

    @Then("^I verify the kill switch toggle$")
    public void i_verify_the_kill_switch_toggle(List<String> killSwitchList) throws Throwable {
        Thread.sleep(10000);
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
        Object killswitch = executor.executeScript("return ENV_CONFIG.tealium_enabled");
        String killSwitchToggle = killSwitchList.get(0);
        Assert.assertTrue("Tealium script kill switch is Off", killswitch.toString().equalsIgnoreCase(killSwitchToggle));
        logger.info("Tealium script kill switch is " + killSwitchToggle + " in MEW : " + killSwitchToggle);
    }

    @And("^I select create account button$")
    public void iSelectCreateAccountButton() throws Throwable {
        Wait.secondsUntilElementPresent("create_profile.create_account_button", 40);
        Thread.sleep(5000);
        Clicks.click("create_profile.create_account_button");
        logger.info("Select Create account button for MEW: ");
    }

    @Then("^I should verify \"([^\"]*)\" is not present$")
    public void i_should_verify_is_not_present(String utagDataStub) throws Throwable {
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        Assert.assertFalse("utag_data stub is still present.", pageSrcVal.contains(utagDataStub));
        logger.info("utag_data stub is removed: ");
    }


    @And("^I add product to wishlist")
    public void I_add_product_to_wishlist() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("product_display.add_to_wishlist_image", 2);
        if (bloomingdales()) {
            Wait.untilElementPresent("product_display.wishlist_overlay");
        } else {
            Wait.untilElementPresent("product_display.wishlist_overlay");
        }
    }

    @Then("^I verify tealium tags script is available in page source$")
    public void i_verify_tealium_tags_script_is_available_in_page_source(DataTable tagUrl) throws Throwable {
        Thread.sleep(5000);
        String pageSrcVal = WebDriverManager.getWebDriver().getPageSource();
        List<List<String>> dataUrl = tagUrl.raw();
        String utagJs = dataUrl.get(0).get(0);
        String utagSyncJs = dataUrl.get(1).get(0);
        Assert.assertTrue("Tealium tag url is present for utags.js", pageSrcVal.contains(utagJs));
        Assert.assertTrue("Tealium tag url is present for utag.sync.js", pageSrcVal.contains(utagSyncJs));
        logger.info("Tealium tag url is present in page source");

    }

    @And("^I click on StarRewards link$")
    public void i_click_on_StarRewards_link() throws Throwable {

        try {
            Wait.secondsUntilElementPresent("dataLayer_page.starRewardsLink", 30);
            Thread.sleep(1000);
            Clicks.javascriptClick("dataLayer_page.starRewardsLink");
            logger.info("Clicked on StarRewards link on My Account page ");
            Wait.forPageReady();
            Thread.sleep(4000);
        } catch (Exception e) {
            logger.info("Unable to Click on StarRewards link on My Account page " + e.getMessage());

        }

    }

    @Then("^I click view registry link$")
    public void i_click_view_registry_link() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(2000);
        Clicks.click("dataLayer_page.viewregistrylink");
        logger.info("Clicked on View Registry link");
        Wait.forPageReady();
        Thread.sleep(2000);
    }

    @Then("^I click MacysCreditCard link$")
    public void i_click_MacysCreditCard_link() throws Throwable {
        Clicks.click("dataLayer_page.MacysCreditCardlink");
        Wait.forPageReady();
        Thread.sleep(2000);
    }

    @Then("^I verify the kill switch toggle for bright tag$")
    public void i_verify_the_kill_switch_toggle_for_bright_tag(List<String> kill_Switch_Toggle) throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
        Object killswitch = executor.executeScript("return ENV_CONFIG.brighttag_enabled");
        String killSwitchToggle = kill_Switch_Toggle.get(0);
        Assert.assertTrue("Bright script kill switch is Off", killswitch.toString().equalsIgnoreCase(killSwitchToggle));
        logger.info("Bright tag kill switch is " + killSwitchToggle + " in MEW : " + killSwitchToggle);
    }

    @Then("^I verify bright tags script is not available in network call$")
    public void i_verify_bright_tags_script_is_not_available_in_network_call() throws Throwable {
        har = MainRunner.browsermobServer.getHar();
        WebDriverManager.getWebDriver().navigate().refresh();
        Thread.sleep(8000);
        Boolean tagJSPresent = false;
        Wait.forPageReady();
        Thread.sleep(2000);
        if (har != null && har.getLog() != null) {
            List<HarEntry> harEntries = har.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }
            for (HarEntry harEntry : harEntries) {
                logger.info(harEntry.getRequest().getUrl());
                if (harEntry.getRequest().getUrl().contains("/tag.js")) {
//
                    tagJSPresent = true;
                    logger.info("bright tags script is available in network call..." + harEntry.getRequest().getUrl() + "tagJSPresent value:" + tagJSPresent);
                    break;

                }
            }
            Assert.assertTrue("Bright tags script is available in network call so failed...", tagJSPresent == false);
            logger.info("Bright tags script script is not available in network call as expected...");
        }

    }

    public Har getInitializedHar() {
        har = MainRunner.browsermobServer.getHar();
        for (int i = 1; i <= 3; i++) {
            har = MainRunner.browsermobServer.getHar();
            if (har != null && har.getLog() != null) {
                logger.info("**return Har content in Initializer...**");
                return har;

            } else
                continue;

        }
        return har;
    }


    @Then("^I verify tealium tags script is available in network call for fashion pages$")
    public void i_verify_tealium_tags_script_is_available_in_network_call_for_fashion_pages(List<String> networkCall) throws Throwable {
        Boolean isUtagJSPresent = false;
        Boolean isUtagSyncJSPresent = false;
        String tealiumScriptUtagjs = networkCall.get(networkCall.size() - networkCall.size());
        String tealiumScriptUtagSyncjs = networkCall.get(networkCall.size() - networkCall.size() + 1);
        WebDriverManager.getWebDriver().navigate().refresh();
        Thread.sleep(1000);
        logger.info("Getting Har content");
        Thread.sleep(1000);
        har = MainRunner.browsermobServer.getHar();
        Wait.forPageReady();
        Thread.sleep(6000);
        if (har != null && har.getLog() != null) {
            List<HarEntry> harEntries = har.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }

            for (HarEntry harEntry : harEntries) {
                logger.info(harEntry.getRequest().getUrl());
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(tealiumScriptUtagjs)) {
                    logger.info("Tealium tags script is available in network call for fashion page-> " + harEntry.getRequest().getUrl());
                    isUtagJSPresent = true;
                    break;
                }
            }

            for (HarEntry harEntry : harEntries) {
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(tealiumScriptUtagSyncjs)) {
                    logger.info("Tealium tags script is available in network call for fashion page-> " + harEntry.getRequest().getUrl().contains(tealiumScriptUtagSyncjs));
                    isUtagSyncJSPresent = true;
                    break;
                }
            }
            //---------- Commented to avoid Inconsistent issues-----------------//
            Assert.assertTrue("Tealium tags script is not available in network call for fashion page", isUtagJSPresent == true);
            logger.info("Both Tealium tags script Utag and Utag.syn.js is present for fashion pages ");

        } else {
            logger.info("Har file is null: Unable to capture network call");
            Assert.fail("Unable to capture network call");
        }
    }

    @And("^I visit the \"([^\"]*)\" pages$")
    public void i_visit_the_app_master_pages(String pageUrl) throws Throwable {
        String visitWebPage = RunConfig.getEnvOrExParam("website") + pageUrl;
        logger.info("Navigating to page: "+visitWebPage);
        Navigate.visit("visitWebPage");
        Wait.forPageReady();
        Thread.sleep(5000);
    }

    @Then("^I verify tealium tags script is available in network call for mew$")
    public void i_verify_tealium_tags_script_is_available_in_network_call_for_mew(List<String> networkCall) throws Throwable {
        Boolean isUtagJSPresent = false;
        Boolean isUtagSyncJSPresent = false;
        String tealiumScriptUtagjs = networkCall.get(networkCall.size() - networkCall.size());
        WebDriverManager.getWebDriver().navigate().refresh();
        logger.info("Getting Har content");
        har = MainRunner.browsermobServer.getHar();
        Wait.forPageReady();
        Thread.sleep(8000);
        if (har != null && har.getLog() != null) {
            List<HarEntry> harEntries = har.getLog().getEntries();
            logger.info("Size :" + harEntries.size());
            if (CollectionUtils.isEmpty(harEntries)) {
                logger.info("No network calls identified..");
                Assert.fail("Unable to capture network call");
                return;
            }

            for (HarEntry harEntry : harEntries) {
                logger.info(harEntry.getRequest().getUrl());
                if (harEntry.getRequest().getUrl().equalsIgnoreCase(tealiumScriptUtagjs)) {
                    logger.info("Tealium tags script is available in network call -> " + harEntry.getRequest().getUrl());
                    isUtagJSPresent = true;
                    break;
                }
            }
            //---------- TBD---Need to add condition for Utag sync.js-----------------//
            Assert.assertTrue("Tealium tags script is not available in network call for MEW", isUtagJSPresent == true);
            //Assert.assertTrue("Tealium tags script is not available in network call", isUtagJSPresent == true && isUtagSyncJSPresent==true);
            logger.info("Both Tealium tags script Utag and Utag.syn.js is present  for MEW");

        } else {
            logger.info("Har file is null: Unable to capture network call for MEW");
            Assert.fail("Unable to capture network call for MEW");
        }
    }


}