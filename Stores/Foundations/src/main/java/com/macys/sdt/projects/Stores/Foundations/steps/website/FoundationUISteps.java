package com.macys.sdt.projects.Stores.Foundations.steps.website;

import com.macys.sdt.projects.Stores.Foundations.utils.WelcomePageTest;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;


public class FoundationUISteps extends StepUtils {

    private WelcomePageTest test = new WelcomePageTest();

    @Then("^I should see the CSG UI page as expected$")
    public void iShouldSeeTheCSGUIPageAsExpected() throws Throwable {

        List<String> tags = null;

        String specPath = "C:/Users/B005198/Desktop/galen/csgFoundation.gspec";
        test.load(RunConfig.url);
        test.checkLayout(specPath, tags);
    }

    @Then("^I should see the color changing button$")
    public void iShouldSeeTheColorChangingButton() throws Throwable {
       Elements.elementShouldBePresent( "csg.button_xlarge_primary");
    }

    @When("^I press the button$")
    public void iPressTheButton() throws Throwable {
        Clicks.click("csg.button_xlarge_primary");
        Thread.sleep(2000);
    }

    @Then("^I should see the button background color change from black to hex #(\\d+)b$")
    public void iShouldSeeTheButtonBackgroundColorChangeFromBlackToHexB(String button) throws Throwable {


    }
}


