package com.macys.sdt.projects.Marketing.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Random;

import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.shared.utils.CommonUtils.selectRandomProduct;

/**
 * Created by u063689 on 2/2/2018.
 */
public class SEO {

    private static final Logger logger = LoggerFactory.getLogger(SEO.class);
     private String currentURL;

    @Then("^I should see canonical tag references the current url$")
    public void iShouldSeeCanonicalTagReferencesTheCurrentUrl() throws UnsupportedEncodingException {
        currentURL = MainRunner.currentURL;
        String[] pattern = currentURL.split("(\\?cm_sp)|(&cm_sp)|(\\?utm_campaign=disable_all)");
        currentURL = pattern[0];
        String canonicalURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
        Assert.assertEquals("Canonical tag does not reference current URL", currentURL, canonicalURL);
    }

    @And("^I should see that \"([^\"]*)\" is not appended to canonical tag url$")
    public void iShouldSeeThatIsNotAppendedToCanonicalTagUrl(String append) throws UnsupportedEncodingException {
        String canonicalURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
        Assert.assertFalse("Canonical tag URL has cm_sp appended", canonicalURL.contains(append));
    }

    @And("^I should see the alternative tag references (mobile|desktop) domain$")
    public void iVerifyAlternateTagUrlReferencesMobilePage(String domain) throws UnsupportedEncodingException {
        currentURL = MainRunner.currentURL;
        String[] pattern = currentURL.split("(\\?cm_sp)|(&cm_sp)||(\\?utm_campaign=disable_all)");
        currentURL = pattern[0];
        String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"), "UTF-8");
        if(domain.equalsIgnoreCase("mobile")) {
            Assert.assertTrue("Alternate tag URL does not reference mobile page", alternateURL.startsWith("https://m."));
        }
        else{
            Assert.assertTrue("Alternate tag URL does not reference mobile page", alternateURL.startsWith("https://www."));
        }
    }

    @Then("^I verify the bing webmaster metadata is present in the head tag$")
    public void iVerifyTheBingWebmasterMetadataIsPresentInTheHeadTag() {
        Assert.assertTrue(WebDriverManager.getWebDriver().getPageSource().contains("<meta name=\"msvalidate.01\" content=\"62B1745E1E4F6663B8C9FEB46FD77590\" />"));
    }

    @And("^I navigate to random brand from Designer Index page$")
    public void iNavigateToRandomBrandFromDesignerIndexPage() {
        if (!onPage("designer_static")) {
            logger.info("Navigating to Designer Index Page by url");
            Navigate.visit("designer_static");
        }
        //getRandomBrandName("designer_static");
        Elements.findElement(By.linkText(getRandomBrandName())).click();
    }

    private String getRandomBrandName() {
        Wait.forPageReady("designer_static");
        if (Elements.elementPresent("designer_static.brands")) {
            List<WebElement> names = Elements.findElements("designer_static.brands");
            Random randomizer = new Random();
            return names.get(randomizer.nextInt(names.size())).getText();

        } else {
            return "none";
        }
    }

    @Then("^I should see canonical tag is shorter version$")
    public void iShouldSeeCanonicalTagIsShorterVersion() throws Throwable{
        Wait.forPageReady();
        currentURL = MainRunner.currentURL;
        String[] pattern = currentURL.split("/Brand");
        String canonicalURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
        Assert.assertEquals("Canonical URL is full length", canonicalURL, pattern[0]);
    }

    @And("^I should see alternate tag is shorter version$")
    public void iShouldSeeAlternateTagIsShorterVersion() throws Throwable{
        Wait.forPageReady();
        String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"), "UTF-8");
        String[] pattern = alternateURL.split("/Brand");
        Assert.assertEquals("Alternative URL is full length", alternateURL, pattern[0]);
    }

    @Then("^I should see the canonical tag ends at product id$")
    public void iShouldSeeTheCanonicalTagEndsAtProductId() throws Throwable {
        Wait.forPageReady();
        currentURL = MainRunner.currentURL;
        String[] pattern = currentURL.split("&Category");
        String canonicalURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
        Assert.assertEquals("Canonical URL is full length", canonicalURL, pattern[0]);
    }

    @And("^I should see alternate tag ends at product id$")
    public void iShouldSeeAlternateTagEndsAtProductId() throws Throwable {
        Wait.forPageReady();
        String alternateURL = URLDecoder.decode(Elements.findElement("header.alternate_tag").getAttribute("href"), "UTF-8");
        String[] pattern = alternateURL.split("&Category");
        Assert.assertEquals("Alternative  URL is full length", alternateURL, pattern[0]);
    }

    @When("^I navigate to a random (member|master) product$")
    public void iNavigateToARandomMemberProduct(String member_master){
        Home.selectRandomSubCategory();
        if(member_master.equalsIgnoreCase("member")){
            selectRandomProduct(false, false);
        }
        else{
            selectRandomProduct(false, true);
        }
    }

    @When("^I navigate to \"([^\"]*)\" page in \"([^\"]*)\" mode$")
    public void iNavigateToPagePageInMode(String page, String mode) throws Throwable{
        PageNavigation pageNavigation = new PageNavigation();
        switch (mode) {
            case "domestic": {
                switch (page) {
                    case "search result": {
                        pageNavigation.I_navigate_to_page_in_mode("search_result", "domestic");
                        break;
                    }
                    case "category browse":{
                        pageNavigation.I_navigate_to_page_in_mode("browse", "domestic");
                        break;
                    }
                    case "category splash":{
                        Home.selectRandomCategory();
                        break;
                    }
                    case "DLP":{
                        pageNavigation.I_navigate_to_page_in_mode("dynamic landing", "domestic");
                        break;
                    }
                    case "PDP member": {
                        iNavigateToARandomMemberProduct("member");
                    }
                    case "PDP master": {
                        iNavigateToARandomMemberProduct("master");
                    }
                }
            }
        }
    }

    @Then("^I should see only one H(\\d+) tag is present$")
    public void iShouldSeeOnlyOneHTagIsPresentOnPage(int tag) throws Throwable {
        List<WebElement> hOneTags = Elements.findElements(By.tagName("h"+tag));
        System.out.println(hOneTags.size());
        Assert.assertEquals(hOneTags.size(), 1);
    }

    @Then("^I should see title tag is present on pages in DOMESTIC MODE and has a minimum of (\\d+) characters$")
    public void iShouldSeeTitleTagIsPresentOnPagesInDOMESTICMODEAndHasAMinimumOfCharacters(int chrs) throws Throwable {
        String title = WebDriverManager.getWebDriver().getTitle();
        System.out.println("Title" + title);
        Assert.assertTrue(title.length()>=chrs);
    }

    @Then("^I verify the robots.txt file:$")
    public void iVerifyTheRobotsTxtFile(String expected) throws Throwable {
        Navigate.visit("https://www.bloomingdales.com/robots.txt");
        System.out.println(expected.replace("\t", "").trim());
        System.out.println(WebDriverManager.getWebDriver().getPageSource().substring(WebDriverManager.getWebDriver().getPageSource().indexOf("User-agent"),
                WebDriverManager.getWebDriver().getPageSource().indexOf("</pre")).replace("\t", "").trim());
       Assert.assertEquals("robots.txt file is not as expected", expected.replace("\t", "").trim(),
               WebDriverManager.getWebDriver().getPageSource().substring(WebDriverManager.getWebDriver().getPageSource().indexOf("User-agent"),
               WebDriverManager.getWebDriver().getPageSource().indexOf("</pre")).replace("\t", "").trim());

    }

    @Then("^I verify the google metadata is present in the head tag$")
    public void iVerifyTheBingGoogleMetadataIsPresentInTheHeadTag(){
        Assert.assertTrue(WebDriverManager.getWebDriver().getPageSource().contains("<meta name=\"google-site-verification\" content=\"HxmQD264GfTUypnVtDjz56-BOQ5MtGH6biw0qvD-lDE\" />"));
    }
}
