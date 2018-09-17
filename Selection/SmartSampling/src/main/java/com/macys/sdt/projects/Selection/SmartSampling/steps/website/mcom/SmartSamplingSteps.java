package com.macys.sdt.projects.Selection.SmartSampling.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * Created by yh01754 on 1/23/2017.
 */
public class SmartSamplingSteps extends StepUtils {
    @Given("^I click on show samples button on bag page$")
    public void I_click_on_show_samples_button() throws Throwable {
        Clicks.click("smart_grid.show_samples");
            }

    @Then("^I should see the sample grid is displayed$")
    public void i_should_see_the_sample_grid_is_displayed() throws Throwable {
        WebElement element = Elements.findElement("smart_grid.sample_grid");
        if(element.isDisplayed()) {
            System.out.println("sample grid present on atb page");
        }
        else {
            Assert.fail("sample grid is not present");
        }
    }
}