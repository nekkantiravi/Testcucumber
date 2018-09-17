package com.macys.sdt.projects.Marketing.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by YC05165 on 24/07/17.
 */
public class footerlinks extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(footerlinks.class);

    @Then("^I verify navigated to the \"([^\"]*)\" page$")
    public void I_verify_navigated_to_footerLink_pages(String footerLink) throws Throwable {
        String error_message = "Not navigated to " + footerLink + " page";
        switch (footerLink) {
            case "CUSTOMER SERVICE":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("CUSTOMER_SERVICE"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "Shipping Policy":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("SHIPPING_POLICY"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "Returns & Exchanges":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("RETURNS_EXCHANGES"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "International":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("INTERNATIONAL"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "MY ACCOUNT":
                if (!onPage("sign_in")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "Order Status":
                if (!onPage("order_status")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "My Loyallist":
                if (!onPage("loyalty_home")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "My Profile":
                if (!onPage("my_profile")) {
                    Assert.fail("User is not navigated to " + footerLink + "page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "MY CREDIT CARD":
                if (!onPage("credit_service_gateway_guest")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "Pay Bill":
                if (!onPage("sign_in")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "Cardholder Benefits":
                if (!onPage("credit_benefits")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "Learn More & Apply":
                if (!onPage("credit_service_marketing")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                    Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                }
                break;
            case "COMPANY":
                Assert.assertTrue(error_message, Elements.elementPresent("my_page.company_page"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "About Us":
                Assert.assertTrue(error_message, Elements.elementPresent("my_page.company_page"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "b.cause":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("B_CAUSE"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "Careers":
                String homeWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> allWindowHandles = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh : allWindowHandles) {
                    if (!wh.equals(homeWindow)) {
                        Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.careers_page_verify"));
                        Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                    }
                }
                break;
            case "WAYS TO SHOP":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("WAYS_TO_SHOP"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "Online & Mobile":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("MOBILE"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "Stores":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("STORES_EVENTS"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "Pick Up In Store":
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("pickup-in-store"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            default:
                Assert.fail("Invalid Footer link");
                break;
        }
    }

    @When("^I verify the footer \"([^\"]*)\" is present and has the correct url$")
    public void i_verify_the_footer_icon_is_present_and_has_the_correct_url(String socialLink) throws Throwable {
        String page_error_message = "Not navigated to social link " + socialLink + "page";
        switch (socialLink.toLowerCase()) {
            case "facebook":
                Wait.secondsUntilElementPresentAndClick("my_page.face_book_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("FACEBOOK"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "twitter":
                Wait.secondsUntilElementPresentAndClick("my_page.twitter_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("TWITTER"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "pinterest":
                Wait.secondsUntilElementPresentAndClick("my_page.pinterest_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("PINTEREST"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "instagram":
                Wait.secondsUntilElementPresentAndClick("my_page.instagram_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("INSTAGRAM"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "mobile":
                Wait.secondsUntilElementPresentAndClick("my_page.mobile_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("APP"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "tumblr":
                Wait.secondsUntilElementPresentAndClick("my_page.tumblr_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("TUMBLR"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            case "snapchat":
                Wait.secondsUntilElementPresentAndClick("my_page.snapchat_page",20);
                Navigate.switchWindow(1);
                Wait.forPageReady();
                Assert.assertTrue(page_error_message,WebDriverManager.getWebDriver().getCurrentUrl().contains("SNAPCHAT"));
                Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
                break;
            default:
                Assert.fail("Invalid Social Link");
                break;
        }

    }

    @Then("^I select and verify footer Ad banner$")
    public void i_select_and_verify_footer_Ad_banner() throws Throwable{
        Clicks.click("footer.goto_footer_banner");
        Thread.sleep(2000);
        Navigate.switchWindow(1);
        Wait.forPageReady();
        Assert.assertTrue("ERROR-Contents are not available on pop up window",Elements.elementPresent("my_page.popup_content"));
        Assert.assertTrue("Response Code is not valid", CommonUtils.verifyresponseCode(WebDriverManager.getCurrentUrl()));
        Navigate.switchWindowClose();
    }

}