package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by srinivasan_sw on 4/27/2017.
 */
public class FeoSteps extends StepUtils{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FeoSteps.class);

    @Then("^I verify that page is served from \"([^\"]*)\" tag$")
    public void iVerifyThatPageIsServedFromFEO(String tagName) throws  Throwable {
        boolean found = false;
        int i;
        for(i=0; i<=25; i++){
            if((WebDriverManager.getWebDriver().getPageSource()).contains(tagName)){
                found = true;
                break;
            }
            else {
                Navigate.browserRefresh();
                Wait.isPageLoaded();
            }
        }
        Assert.assertTrue("Not able to get FEO optimized page after made " + i +" attempts", found);
    }

    @Then("^I verify the TopNav elements are present in the UI for \"([^\"]*)\" user in \"([^\"]*)\" mode$")
    public void i_verify_the_TopNav_elements_are_present_in_the_UI_for_user_in_mode(String user_type, String mode) throws Throwable {
        switch (user_type) {
            case "guest":
                switch (mode) {
                    case "site":
                    case "registry":
                        Elements.elementShouldBePresent("header.goto_sign_in_link");
                        Elements.elementShouldBePresent("header.goto_my_account_link");
                        Elements.elementShouldBePresent("header.goto_stores");
                        Elements.elementShouldBePresent("header.header_pool");
                        Assert.assertSame(Elements.findElement(By.id("href_customerServiceHeader")).isEnabled(), true);
                        break;
                    case "iship":
                        Elements.elementShouldBePresent("header.goto_stores");
                        Assert.assertSame(Elements.findElement(By.id("href_customerServiceHeader")).isEnabled(), true);
                        Elements.elementShouldBePresent("header.shipping_to_link");
                        Elements.elementShouldBePresent("header.header_pool");
                        break;
                }
                break;
            case "registered":
                switch (mode) {
                    case "site":
                    case "registry":
                        Elements.elementShouldBePresent("header.goto_sign_out_link");
                        Elements.elementShouldBePresent("header.goto_my_account_link");
                        Elements.elementShouldBePresent("header.goto_stores");
                        Assert.assertSame(Elements.findElement(By.id("href_customerServiceHeader")).isEnabled(), true);
                        break;
                }
        }
    }

    @When("^I click on \"([^\"]*)\" link and browse back$")
    public void iClickOnLink(String link) throws Throwable {
        Clicks.click(By.linkText(link));
        Navigate.browserBack();
    }

    @Then("^I click on link HF links and browse back to verify page got optimized again$")
    public void iClickOnLinkHFLinksAndBrowseBack(List<String> staticLinks) throws Throwable {
        for (String link:staticLinks) {
            if(link.equalsIgnoreCase("GIFTS")){
                Clicks.hoverForSelection("home.top_nav_gift_link");
                Wait.untilElementPresent("home.top_nav_gift_dropdown");
               List<WebElement> giftLinks= Elements.findElements("home.top_nav_gift_options");

                for(WebElement glink: giftLinks) {
                    if((glink.getText().equalsIgnoreCase("The Gift Guide")))
                       Assert.assertTrue("Expected gift card option is displaying",(glink.getText().equalsIgnoreCase("The Gift Guide")) );
                    else {
                        if((glink.getText().equalsIgnoreCase("GIFT CARDS")))
                            Assert.assertTrue("Expected gift card option is displaying",(glink.getText().equalsIgnoreCase("GIFT CARDS")));
                    }
                }
            }
            else {
                if(link.equalsIgnoreCase("sign up for email")){
                    Clicks.click("home.signup_for_email");
                    Wait.isPageLoaded();
                    Thread.sleep(1000);
                    Navigate.browserBack();
                }
                else {
                    Wait.untilElementPresent(By.linkText(link));
                    Clicks.hoverForSelection("footer.footer_section");
                    Wait.isPageLoaded();
                    Thread.sleep(1000);
                    Clicks.click(By.linkText(link));

                    Set<String> currentWindows = WebDriverManager.getWebDriver().getWindowHandles();
                    List<String> windows = new ArrayList<>(currentWindows);
                    if ((currentWindows.size() > 1)) {
                        WebDriverManager.getWebDriver().switchTo().window(windows.get(1)).close();
                        WebDriverManager.getWebDriver().switchTo().window(windows.get(0));
                        Wait.forPageReady();
                    } else {
                        //Below line of code to perform extra browser back from Macys back stage page.
                        if (WebDriverManager.getCurrentUrl().contains("macysbackstage.com/home")) {
                            com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.browserBack();
                        }
                        Navigate.browserBack();
                        Wait.isPageLoaded();
                    }
                }
            }
            iVerifyThatPageIsServedFromFEO("feo");
        }
    }

    @Given("^I should see empty quickbag message$")
    public void i_should_see_empty_quickbag_message() throws Throwable {
        Clicks.hoverForSelection("header.qb_hover");
        Elements.elementShouldBePresent("header.empty_quickbag");
        Assert.assertTrue(Elements.findElement("header.empty_quickbag").getText().equalsIgnoreCase("0 items in your bag. Shop great deals now!"));
    }

    @And("^I click on footer social media links and browse back to verify page got optimized again$")
    public void iClickOnFooterSocialMediaLinksAndBrowseBackToVerifyPageGotOptimizedAgain(List<String> socialLinks) throws Throwable {
        for (String social : socialLinks) {
            List<WebElement> socialLinkElements = Elements.findElement(By.id("stayconnected")).findElements(By.className("left"));
            WebElement ele = socialLinkElements.stream().filter(e->e.findElement(By.tagName("a")).getAttribute("title").equals(social)).findFirst().orElse(null);
            Thread.sleep(10000);
            Clicks.hoverForSelection(ele);
            Clicks.click(ele);
            Thread.sleep(10000);
            Navigate.browserBack();
            Wait.forPageReady();
            iVerifyThatPageIsServedFromFEO("feo");
        }
    }

    @And("^I click on footer ad link and browse back to verify page got optimized again$")
    public void iClickOnFooterAdLinkAndBrowseBackToVerifyPageGotOptimizedAgain(List<String> footerAds) throws Throwable {
        for (String ad : footerAds) {
            Clicks.hoverForSelection("footer.footer_section");
            Thread.sleep(2000);
            if(ad.equals("Macy's Culinary Council")){
                WebElement el = Elements.findElement(By.id("footer-ad-spot"));
                Clicks.click(el);
            } else {
                List<WebElement> adsele=Elements.findElements(By.tagName("area"));
                WebElement ele= adsele.stream().filter(e-> e.getAttribute("alt").toLowerCase().equals(ad.toLowerCase())).findFirst().orElse(null);
                assert ele!=null;
                Clicks.click(ele);
            }
            Wait.forPageReady();
            Navigate.browserBack();
            Wait.forPageReady();
            iVerifyThatPageIsServedFromFEO("feo");
        }
    }

    @And("^I verify that flyout menu is displayed for FOBs$")
    public void i_verify_FOB_flyout_menu_displaying(List<String> categories)throws Throwable{
        for(String fob : categories){
            Clicks.hoverForSelection(By.linkText(fob.toUpperCase()));
            Wait.secondsUntilElementPresent("header.open_flyout", 20);
            Assert.assertTrue("Flyouts not visible for category- "+fob, Elements.elementPresent("header.open_flyout"));
        }
    }

    @Then("^I verify that new dropdown link names are displayed$")
    public void iVerifyThatNewDropdownLinkNamesAreDisplayed(List<String> expected) {
        Clicks.hoverForSelection("header.goto_my_account_link");
        Wait.untilElementPresent("header.myaccount_dropdown");
        List<String> actual = Elements.findElements("header.my_account_drop_down_links").stream().map(m -> m.getText().toUpperCase()).collect(Collectors.toList());
        expected = expected.stream().map(m -> m.toUpperCase()).collect(Collectors.toList());
        Assert.assertTrue("ERRPR - APP: Expected my account drop down options is not listed", expected.containsAll(actual));
        log.info("Verified that expected drop down with below options: " + expected);
    }
}
