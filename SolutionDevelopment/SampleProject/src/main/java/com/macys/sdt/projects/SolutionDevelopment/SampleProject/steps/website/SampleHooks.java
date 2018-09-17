package com.macys.sdt.projects.SolutionDevelopment.SampleProject.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.And;

public class SampleHooks extends StepUtils {
    private static Boolean setup_flag = false;

    @And("^sample hook test: I click the logo$")
    public void sample_test_I_click_the_logo() throws Throwable {
        System.err.println("Scenario step-1");
        Clicks.click("home.logo");
    }

    @And("^sample hook test: I navigate to sample page$")
    public void sample_test_I_navigate_to_sample_page() throws Throwable {
        System.err.println("Scenario step-2");
        Navigate.visit("home");
        Clicks.click("home.verify_page");
        Clicks.click("home.test_element");
    }

    // Before Feature
    public void before_feature() {
        System.err.println("##################");
        System.err.println("Before feature");
        System.err.println("##################");
    }

    // After Feature
    public void after_feature() {
        System.err.println("##################");
        System.err.println("After feature");
        System.err.println("##################");
    }

    // Before Scenario
    @Before
    public void before_scesnario() {
        // #########################################
        // Before/After feature
        if (!setup_flag) {
            before_feature();
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    after_feature();
                }
            }, "Shutdown-thread"));
            setup_flag = true;
        }
        // #########################################

        System.err.println("\nBefore scenario");
    }

    @After
    public void after_scesnario() {
        System.err.println("\nAfter scenario");
    }

    @Before("@Step")
    public void before_step() {
        System.err.println("\nBefore step");
    }

    @After("@Step")
    public void after_step() {
        System.err.println("\nAfter step");
    }
}