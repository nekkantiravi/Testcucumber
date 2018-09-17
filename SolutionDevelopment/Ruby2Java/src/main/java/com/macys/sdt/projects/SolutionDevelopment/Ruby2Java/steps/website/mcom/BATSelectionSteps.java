package com.macys.sdt.projects.SolutionDevelopment.Ruby2Java.steps.website.mcom;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.ConvertedSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/**
 * Created by Ruby to Java Converter
 */

public class BATSelectionSteps extends ConvertedSteps {
    @And("^I navigate to product ID 77589$")
    public void i_navigate_to_product_id_77589() throws Throwable {
        //  /session/:sessionId/url
        Navigate.visit(RunConfig.url + "/?utm_campaign=disable_all");

// Removed duplicated line(s):         Navigate.visit(RunConfig.url + "/?utm_campaign=disable_all");
//  /session/:sessionId/element
        WebElement keyword = Elements.findElement(By.name("keyword"));

//  /session/:sessionId/element/:id/displayed
        boolean keywordIsDisplayed = keyword.isDisplayed();

//  /session/:sessionId/element
        keyword = Elements.findElement(By.name("keyword"));

//  /session/:sessionId/element/:id/displayed
        keywordIsDisplayed = keyword.isDisplayed();

//  /session/:sessionId/element/:id/click
        Clicks.click(keyword);

//  /session/:sessionId/element
        keyword = Elements.findElement(By.name("keyword"));

//  /session/:sessionId/element/:id/clear
        keyword.clear();

//  /session/:sessionId/element
        keyword = Elements.findElement(By.name("keyword"));

//  /session/:sessionId/element/:id/value
        keyword.sendKeys("77589");

//  /session/:sessionId/element
        WebElement searchSubmit = Elements.findElement(By.id("searchSubmit"));

//  /session/:sessionId/element/:id/click
        Clicks.click(searchSubmit);

//  /session/:sessionId/element
        WebElement longDescription = Elements.findElement(By.id("longDescription"));

//  /session/:sessionId/element
        longDescription = Elements.findElement(By.id("longDescription"));

//  /session/:sessionId/execute
        Object javaScriptExeRet = Navigate.execJavascript("window.scrollTo(0,document.body.scrollHeight)");
    }

    @Then("^I verify the flag for \"([^\"]*)\"$")
    public void i_verify_the_flag_for_arg(String arg1) throws Throwable {
        //  /session/:sessionId/url
        Navigate.visit(RunConfig.url + "/shop/product/kitchenaid-ksm150ps-artisan-5-qt.-stand-mixer?ID=77589&CategoryID=7554&cm_kws=77589");

//  /session/:sessionId/element
        WebElement errorMsgPanel = Elements.findElement(By.id("errorMsgPanel"));

//  /session/:sessionId/element/:id/displayed
        boolean errorMsgPanelIsDisplayed = errorMsgPanel.isDisplayed();

//  /session/:sessionId/element
        errorMsgPanel = Elements.findElement(By.id("errorMsgPanel"));

//  /session/:sessionId/element/:id/displayed
        errorMsgPanelIsDisplayed = errorMsgPanel.isDisplayed();

//  /session/:sessionId/url
        Navigate.visit(RunConfig.url + "/shop/product/kitchenaid-ksm150ps-artisan-5-qt.-stand-mixer?ID=77589&CategoryID=7554&cm_kws=77589");

//  /session/:sessionId/element
        WebElement footerFlag = Elements.findElement(By.id("footerFlag"));

//  /session/:sessionId/element/:id/displayed
        boolean footerFlagIsDisplayed = footerFlag.isDisplayed();

//  /session/:sessionId/element
        footerFlag = Elements.findElement(By.id("footerFlag"));

//  /session/:sessionId/element/:id/attribute/:name
        String footerFlagAttrListsrc = ((WebElement)(footerFlag)).getAttribute("src");
    }
}