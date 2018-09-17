package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;


public class Sample extends StepUtils {
    SoftAssertions softly = new SoftAssertions();

    @And("^sample test: I click the logo$")
    public void sample_test_I_click_the_logo() throws Throwable {
        Clicks.click("my_subscription.test_element");
        Thread.sleep(5000);
        WebDriverManager.getWebDriver().navigate().back();
        Thread.sleep(5000);
    }

    @And("^sample test: I navigate to sample page$")
    public void sample_test_I_navigate_to_sample_page() throws Throwable {
        Navigate.visit("sample");
        Clicks.click("sample.test_element");

    }
    @Then("^I verify Welcome info on Home Page$")
    public void I_verify_welcome_info() throws Throwable {
        String item = Elements.findElement(Elements.element("my_subscription.home_body")).getText();
        softly.assertThat(item.contains("Welcome page coming soon")).as("home_body").isEqualTo(true);
        System.out.println(item);
        softly.assertAll();
    }

}