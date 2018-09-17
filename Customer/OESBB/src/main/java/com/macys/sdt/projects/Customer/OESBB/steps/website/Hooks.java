package com.macys.sdt.projects.Customer.OESBB.steps.website;

import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.projects.Customer.OESBB.utils.runner.OESBBLogger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

public class Hooks {
    // TODO enable delete email once moved to distributed env
    @Before
    public void hook_beforeTestExecution(){
        //deleteAllEmails();
        //Logger.out();
    }

    @After
    public void hook_afterPaloads(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot)WebDriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        OESBBLogger.out(scenario);
    }
}
