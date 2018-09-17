package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yc04026 on 12/27/2017.
 */
public class ForwardPage_Key_Cookie extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ForwardPage_Key_Cookie.class);
    String forward_key_cookie;
    String url = RunConfig.url;
    String expectedUrl;
    String expectedCookie;
    PageNavigation page = new PageNavigation();

    @When("^I click on \"([^\"]*)\" link in \"([^\"]*)\" page$")
    public void i_click_on_element(String element, String page) throws Throwable {
        Wait.untilElementPresent(page + "." + element);
        if (page.contains("product_display")) {
            ProductDisplay.selectRandomColor();
            ProductDisplay.selectRandomSize();
            Assert.assertTrue(element + " is not present or clickable on " + page,
                    Clicks.clickWhenPresent(page + "." + element));
        } else if (StepUtils.macys()) {
            if (element.contains("goto_wedding_registry")) {
                Clicks.hoverForSelection("registry_home.goto_wedding_registry");
            }else {
                Clicks.javascriptClick(page + "." + element);
                Wait.forPageReady();
                if(Elements.elementPresent(page+"."+element)){
                    Clicks.click(page+"."+element);
                    Wait.forPageReady();
                }
            }
        } else if (element.contains("header")) {
            Clicks.hoverForSelection("registry_home.getting_started");
        } else {
            Navigate.browserRefresh();
            Clicks.click(page + "." + element);
        }
        logger.info("Clicked on " + element + "in" + page);
    }

    @Then("^I should be on signin page$")
    public void iShouldBeOnSignInPage() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Not able to navigate to the sign_in page",
                Wait.secondsUntilElementPresent("sign_in.verify_page", 70));
        logger.info("Navigated to signin page");
    }

    @And("^I verify the forwardpage key cookie is generated$")
    public void iVerifyTheForwardpageKeyCookieIsGenerated() throws Throwable {
        forward_key_cookie = Cookies.getCookieValue("FORWARDPAGE_KEY");
        Assert.assertFalse(forward_key_cookie.isEmpty());
        logger.info("forwardpage_key cookie is generated");
    }

    @When("^I sign in with valid credentials$")
    public void iSignInWithValidCredentials() throws Throwable {
        //clear the the customer object if it tries to create the existing profile again
        TestUsers.clearCustomer();

        //Now create anew profile and login with it
        UserProfileService.createRandomUserProfile();
        CommonUtils.signInToCreatedProfile();
        Wait.forPageReady();
        Wait.secondsUntilElementNotPresent("sign_in.email", 30);
        logger.info("Singed in with the credentials");
    }

    @Then("^I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie$")
    public void iShouldRedirectToThePageAssociatedWithTheValueStoredInFORWARDPAGE_KEYCookie() throws Throwable {
        String actualUrl = url();

        Pattern pattern = Pattern.compile("\\/[a-z]+\\/[a-z]+[?]");
        Matcher matcherUrl = pattern.matcher(actualUrl);
        Matcher matcherCookie = pattern.matcher(forward_key_cookie);

        if (matcherUrl.find()) {
            expectedUrl = matcherUrl.group();
        }
        if (matcherCookie.find()) {
            expectedCookie = matcherUrl.group();
        }
        Assert.assertTrue("ERROR - APP: User is not redirected to the correspoding page ",
                expectedUrl.equalsIgnoreCase(expectedCookie));
        logger.info("User is redirected to the corresponding page as per the cookie value");
    }

    @Then("^I should redirect to the \"([^\"]*)\" page corresponding to the value stored in FORWARDPAGE_KEY cookie$")
    public void iShouldRedirectToThePageCorrespondingToTheValueStoredInFORWARDPAGE_KEYCookie(String targetPage) throws Throwable {
        String actualUrl = url();
        Pattern pattern = null;

        switch(targetPage) {
            case "order_status":
                pattern = Pattern.compile("\\/[a-z]+\\/[a-z]+\\-[a-z]+[?]");
                break;
            case "credit_service_gateway_guest":
                 pattern = Pattern.compile("\\/[a-z]+\\-[a-z]+\\/[a-z]+\\/[a-z]");
                break;
            case "wish_list":
                pattern = Pattern.compile("\\/[a-z]+\\/[a-z]+[?]");
                break;

        }
            Matcher matcherUrl = pattern.matcher(actualUrl);
            Matcher matcherCookie = pattern.matcher(forward_key_cookie);

            if (matcherUrl.find()) {
                expectedUrl = matcherUrl.group();
            }
            if (matcherCookie.find()) {
                expectedCookie = matcherUrl.group();
            }
            Assert.assertTrue("ERROR - APP: User is not redirected to the correspoding page ",
                    expectedUrl.equalsIgnoreCase(expectedCookie));
            logger.info("User is redirected to the corresponding page as per the cookie value");

    }

    @When("^I select \"([^\"]*)\" link from my account dropdown$")
    public void i_select_link_from_my_account_dropdown(String element) throws Throwable {
        resumePageHangWatchDog();
        logger.info("Selecting " + element + " link from My Account dropdown");
        Clicks.hoverForSelection("home.goto_my_account_link");
        Wait.untilElementPresent("home.myaccount_dropdown");
        Clicks.clickWhenPresent("home." + element);
        logger.info("selected" + element);
    }

    @Then("^I should be navigated back to my account page$")
    public void i_should_be_naviagted_back_to_my_account_page() throws Throwable {
        shouldBeOnPage("my_account");
    }

    @Given("^I visit the web site as a \"([^\"]*)\" user in \"([^\"]*)\" mode$")
    public void i_visit_the_web_site_as_a_user_in_mode(String user_type, String mode) throws Throwable {
        Registry registryPage = new Registry();
        switch (user_type) {
            case "guest":
                switch (mode) {
                    case "site":
                        page.I_visit_the_web_site_as_a_guest_user();
                        break;
                    case "iship":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        break;
                    case "registry":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        break;
                }
                break;
            case "Registered":
            case "registered":
                switch (mode) {
                    case "Domestic":
                    case "site":
                        page.I_visit_the_web_site_as_a_registered_user();
                        break;
                    case "registry":
                        registryPage.I_visit_the_web_site_as_a_registry_user();
                        break;
                }
        }
    }

}
