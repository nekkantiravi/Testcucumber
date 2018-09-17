package com.macys.sdt.projects.SolutionDevelopment.Ruby2Java.steps.website.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.ConvertedSteps;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BATDiscoverySteps extends ConvertedSteps {

    @Then("^I verify the display of zero result page for Iship$")
    public void I_verify_the_display_of_zero_result_page_for_Iship() throws Throwable {
        // This is a CHANG's Test
//  /session/:sessionId/element
        WebElement productCount = Elements.findElement(By.id("productCount"));

//  /session/:sessionId/element
        productCount = Elements.findElement(By.id("productCount"));

//  /session/:sessionId/element/:id/text
        String productCountTextList = ((WebElement)(productCount)).getText();

        System.out.println("Product Count = " + productCountTextList);
    }
}
