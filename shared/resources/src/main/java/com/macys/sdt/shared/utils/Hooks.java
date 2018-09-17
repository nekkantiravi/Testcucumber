package com.macys.sdt.shared.utils;

import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.framework.utils.akamai.AkamaiUtils;
import com.macys.sdt.framework.utils.analytics.AnalyticsUtils;
import com.macys.sdt.framework.utils.analytics.DATagCollector;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.model.Result;
import io.appium.java_client.AppiumDriver;
import net.lightbody.bmp.proxy.CaptureType;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.macys.sdt.framework.runner.RunConfig.useSauceLabs;
import static com.macys.sdt.framework.runner.WebDriverManager.getWebDriver;
import static com.macys.sdt.framework.utils.ScenarioHelper.*;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class Hooks extends StepUtils {

    private static Logger logger = LoggerFactory.getLogger(Hooks.class);

    private SingletonScenario singletonScenario;
    private boolean keepBrowser = RunConfig.booleanParam("keep_browser");
    private long scenarioStartTime;
    private long stepStartTime;
    private boolean scenarioSetupComplete = false;
    private boolean mewFixSet = false;
    private static boolean firstScenario = true;
    private static boolean foreseeFlag = true;
    private static boolean isBackground = false;
    private static Scenario currentScenario;
    public static Scenario getCurrentScenario(){
        return currentScenario;
    }

    private static void checkForErrorPage() {
        if (RunConfig.useAppium || RunConfig.browser.equalsIgnoreCase("none")) {
            return;
        }

        String[] error_categories = {"Catalog - Not Available", "Site Unavailable", "Access Denied", "Not Found",
                "Back in a few", "Registry Error", "Registry Test", "Network Error", "Registry Create Account Confirm"};
        String current_browser_title = title().toLowerCase();
        for (String category : error_categories) {
            if (current_browser_title.contains(category.toLowerCase())) {
                throw new EnvException(category.toLowerCase() + " on URL: " + url());
            }
        }
        if (current_browser_title.contains("Product - Not Available".toLowerCase())) {
            throw new DataException("Product is Not Available on URL: " + url());
        }
    }

    /**
     * This method will close foresee popup by clicking no thanks button
     */
    private static void closeForseePopup() {
        if (foreseeFlag) {
            if (Elements.elementPresent("product_display.foresee")) {
                try {
                    // can't use click("..."), will cause infinite loop
                    getWebDriver().findElement(Elements.element("product_display.foresee_no_thanks")).click();
                    foreseeFlag = false;
                } catch (Exception | Error e) {
                    // ignore any error/exception. Means popup was not present
                }
            }
        }
    }

    // closeNeedHelp is to close the Need Help popup on Checkout page
    private static void closeNeedHelp() {
        if (Elements.elementPresent("need_help.close")) {
            try {
                // can't use click("..."), will cause infinite loop
                getWebDriver().findElement(Elements.element("need_help.close")).click();
            } catch (Exception | Error e) {
                // ignore any error/exception. Means popup was not present
            }
        }
    }


    private Map getScenarioInfo(Scenario scenario) {
        for (Object key : RunConfig.features.keySet()) {
            String scenarioKey = key.toString();
            if (RunConfig.features.get(scenarioKey) != null) {
                Map scenarioInfo = RunConfig.features.get(scenarioKey);
                if (scenarioInfo.get("name").equals(scenario.getName())) {
                    return scenarioInfo;
                }
            }
        }
        return null;
    }

    public void enableHarCaptures() {
        logger.info("**REQUEST_CONTENT and RESPONSE_CONTENT is going to set**");
        MainRunner.browsermobServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        logger.info("**REQUEST_CONTENT and RESPONSE_CONTENT is set**");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        if (RunFeature.checkAborted()) {
            Assert.fail("Run has been aborted");
        }

        currentScenario = scenario;
        // initialize scenario
        init(scenario);

        // initialize driver


        if (scenario.getSourceTagNames().contains("@Tealium_proxy")) {
            RunConfig.useProxy=true;
        }

        if (!RunConfig.browser.equalsIgnoreCase("none") && (!keepBrowser || firstScenario)) {
            WebDriverManager.startWebDriver();
        }




        //-------------------------Tealium--------------------------------

        MainRunner.PageHangWatchDog.pause(false);

        scenarioStartTime = System.currentTimeMillis();
        Map sinfo = getScenarioInfo(scenario);
        String line = "";
        if (sinfo != null) {
            line = Utils.parseInt(sinfo.get("line"), -1) + " ";
        }
        logger.info("Scenario: " + line + scenario.getSourceTagNames() + " - " + scenario.getName());
        if (isScenarioOutline()) {
            logger.info("Scenario Outline Examples: " + getScenarioExamples());
        }
        try {
            singletonScenario = new SingletonScenario(scenario);
        } catch (Exception ex) {
            logger.error("failed to initialize singleton scenario with error : ", ex);
            singletonScenario = null;
        }

        // analytics
        if (RunConfig.tagCollection) {
            DATagCollector.start(scenario);
        }

        if (!RunConfig.browser.equalsIgnoreCase("none") && !RunConfig.useAppium) {
            Cookies.deleteAllCookies();
        }

        // launch app - only needed after first scenario since initial driver launch opens the app
        if (RunConfig.appTest && !firstScenario) {
            try {
                AppiumDriver driver = WebDriverManager.getAppiumDriver();
                if (driver != null) {
                    driver.launchApp();
                }
            } catch (DriverNotInitializedException e) {
                Assert.fail("Driver not initialized");
            }
        }
    }

    @After
    public void afterScenario(Scenario scenario) throws DriverNotInitializedException {
        try {
            if (!(RunConfig.akamai)) {
                AnalyticsUtils.flushAnalytics();
                if (RunConfig.tagCollection) {
                    DATagCollector.flush();
                }
            }
            if (scenario.isFailed()) {
                // embed screenshot in report
                try {
                    if (!RunConfig.browser.equalsIgnoreCase("none")) {
//                        byte[] screenshot = ((TakesScreenshot) WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
//                        scenario.embed(screenshot, "image/png");
                    }
                } catch (WebDriverException wde) {
                    logger.error("webdriver error : " + wde.getMessage());
                } catch (ClassCastException cce) {
                    logger.error("Driver cast error! : ", cce);
                }

                Result result = getFailedStepResult();
                String errorMsg = "Unknown";
                if (result != null) {
                    String error = result.getErrorMessage();
                    if (error != null) {
                        errorMsg = error.trim();
                    }
                }

                logger.error("<--------------------->" + "  FAILED SCENARIO: " + scenario.getName().trim());
                if (isScenarioOutline()) {
                    logger.error("FAILED EXAMPLES: " + getScenarioExamples());
                }
                String stepName = getScenarioStepName(getScenarioIndex() - 1);

                logger.error("FAILED STEP: " + stepName.trim() + "\nERROR: " + errorMsg + "\n<--------------------->\n\n");
                if (errorMsg.startsWith("sdt.utils.StepUtils$ProductionException:") || errorMsg.startsWith("sdt.utils.StepUtils$SkipException:")) {
                    clearStepResult(-1);
                }
                checkForErrorPage();
                CommonUtils.checkProductUnavailability();
            }
            if (RunConfig.akamai) {
                String result = AkamaiUtils.validate(getAkamaiJson());
                if (result.equals("valid")) {
                    logger.info("All Akamai results valid.");
                } else {
                    Assert.fail(result);
                }
            }
            logger.info("DURATION: " + Utils.toDuration(System.currentTimeMillis() - scenarioStartTime) + "\n\n\n\n");

        } finally {
            if (MEW() || bloomingdales()) {
                foreseeFlag = true;
            }

            if (singletonScenario != null) {
                singletonScenario.release();
                singletonScenario = null;
            }

            MainRunner.PageHangWatchDog.pause(true);

            TestUsers.releaseProductionCustomer();
            scenarioSetupComplete = false;

            if (RunConfig.appTest) {
                AppiumDriver driver = WebDriverManager.getAppiumDriver();
                if (driver != null) {
                    driver.closeApp();
                }
            }

            if (useSauceLabs) {
                WebDriverManager.setPassed(!scenario.isFailed());
                WebDriverManager.resetDriver(true);
            } else if (!keepBrowser) {
                if (RunConfig.debugMode) {
                    WebDriverManager.resetDriver(!scenario.isFailed());
                } else {
                    WebDriverManager.resetDriver(true);
                }
            }
        }

    }

    @Before("@Step")
    public void beforeStep() {
        stepStartTime = System.currentTimeMillis();
        isBackground = ScenarioHelper.isBackground();
        if (isBackground) {
            ScenarioHelper.incrementBackgroundStepCount();
            logger.info("Running background step...");
            return;
        }
        // an extra result is added by every @Before hook. Need to adjust for this.
        if (!scenarioSetupComplete) {
            resetScenarioOffset();
            try {
                String stepName = getScenarioStepName(getScenarioIndex());
                // the first step will be step 0 and will start with "0:[lineNum] - [step name]"
                // stepName will be empty on error
                while (!stepName.isEmpty() && !stepName.startsWith("0")) {
                    incrementStepIndexOffset();
                    stepName = getScenarioStepName(getScenarioIndex());
                }
            } catch (NullPointerException e) {
                // not a problem
            }

            // for mew and bcom desktop
            if (MEW() || bloomingdales()) {
                // check for foresee and close it
                Navigate.addAfterNavigation(Hooks::closeForseePopup);
                Navigate.addBeforeNavigation(Hooks::closeForseePopup);
            }

            // check for Need Help and close it
            if (MEW() || bloomingdales()) {
                Navigate.addAfterNavigation(Hooks::closeNeedHelp);
                Navigate.addBeforeNavigation(Hooks::closeNeedHelp);
            }
            scenarioSetupComplete = true;
        }
        String stepName = getScenarioStepName(getScenarioIndex());
        logger.info("Step " + stepName + "\n");

        // don't execute close popup for first step
        if (!stepName.startsWith("0") || ScenarioHelper.getBackgroundStepCount() > 0) {
            closePopup();
        }
    }

    private String dataCenter = RunConfig.getEnvOrExParam("data_center");

    @After("@Step")
    public void afterStep(Scenario scenario) {
       // don't execute further in case of uninitialized webdriver
        if (!WebDriverManager.driverInitialized()) {
            return;
        }

        if (firstScenario) {
            EnvironmentDetails.loadEnvironmentDetails();
            firstScenario = false;
        }

        if (RunConfig.useProxy && !(RunConfig.akamai) && !isBackground) {
            AnalyticsUtils.collectAnalyticsData();
            if (RunConfig.tagCollection) {
                DATagCollector.capture(getScenarioStepName(getScenarioIndex()));
            }
        }
        closePopup();
        if (dataCenter != null) {
            Cookies.setDcaCookie(dataCenter);
        }
        if (scenario.isFailed()) {
            checkForErrorPage();
            CommonUtils.checkProductUnavailability();
        }
        if (MEW() && !mewFixSet && Elements.elementPresent("home.sidebar_iframe")) {
            Navigate.addBeforeNavigation(CommonUtils::scrollDownPageWhenSidebarPresent);
            mewFixSet = true;
        }

        logger.info("Step duration: " + Utils.toDuration(System.currentTimeMillis() - stepStartTime) + "\n");
    }
    /**
     * Fills Akamai details with the test data from the JSON file
     *
     */
    private static JSONObject getAkamaiJson() {
        String envName = AkamaiUtils.getDomain().split("\\.")[0];
        String jsonText = "";
        try {
            jsonText = Utils.readTextFile(getResourceFile("akamai.json"));
        } catch (java.io.IOException e) {
            logger.error("Failed to load akamai JSON file: " + e);
        }
        JSONObject akamaiJSON = new JSONObject(jsonText);
        return akamaiJSON.getJSONObject(envName);
    }


}