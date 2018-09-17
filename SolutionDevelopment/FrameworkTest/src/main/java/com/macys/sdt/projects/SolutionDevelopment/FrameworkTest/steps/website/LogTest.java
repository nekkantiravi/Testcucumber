package com.macys.sdt.projects.SolutionDevelopment.FrameworkTest.steps.website;

/**
 * Created by m694182 on 12/13/2016.
 */

import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;

// Logger libs
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest extends StepUtils  {
    @Given("^I log a test messages$")
    public void I_log_a_test_messages() throws Throwable {
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.info("Chang-001");
        System.out.println("LogTest-001: logger.info");
    }
}
