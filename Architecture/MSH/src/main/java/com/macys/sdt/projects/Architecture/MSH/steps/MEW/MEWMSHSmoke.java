
package com.macys.sdt.projects.Architecture.MSH.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Architecture.MSH.steps.website.MSHSmoke;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.steps.MEW.Registry;
import com.macys.sdt.shared.steps.MEW.MyAccount;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.time.LocalTime;


public class MEWMSHSmoke extends StepUtils {

    private String debugValue = LocalTime.now().toString().replace(":","").replace(".","");
    private PageNavigation pageNavigation = new PageNavigation();
    private Registry registry = new Registry();
    private MyAccount myAccount = new MyAccount();
    private MSHSmoke mshcookie = new MSHSmoke();

    @Given("^I visit the mobile web site as a (guest|registered) user with dca cookie as (DAL|RTP)$")
    public void IVisitTheMobileWebSiteAsARegisteredUserWithDcaCookieAsDal(String registered, String cookieValue ) throws Throwable {
        pageNavigation.I_visit_the_mobile_web_site_as_a_registered_user(registered);
       mshcookie.createDcaCookie(cookieValue);
    }

    @Given("^I visit the mobile web site as a guest user in iship mode with dca cookie as (DAL|RTP)$")
    public void iVisitTheMobileWebSiteAsAGuestUserInIshipModeWithDcaCookieAsDAL(String cookieValue) throws Throwable {
        pageNavigation.I_visit_the_mobile_web_site_as_a_guest_user_in_mode("guest");
        mshcookie.createDcaCookie(cookieValue);
    }

    @Given("^I visit the mobile web site as a registry user with dca cookie as (DAL|RTP)$")
    public void IVisitTheMobileWebSiteAsARegistryUserWithDcaCookieAsDal(String cookieValue ) throws Throwable {
        registry.I_visit_the_mobile_web_site_as_a_registry_user();
        mshcookie.createDcaCookie(cookieValue);
    }

    @Given("^I visit the mobile web site as a (guest|registered) user without add CC with dca cookie as (DAL|RTP)$")
    public void IVisitTheMobileWebSiteAsARegisteredUserWithoutCCWithDcaCookieAsDal(String registered, String cookieValue ) throws Throwable {
        myAccount.I_visit_the_mobile_web_site_as_a_registered_user(registered);
        mshcookie.createDcaCookie(cookieValue);
    }

    @Given("^I visit the mobile web site as a guest user in (domestic|iship|registry) mode with DCA cookie as (DAL|RTP)$")
    public void IVisitTheMobileWebSiteAsGuestUserInModeWithDcaCookieAsDal(String mode, String cookieValue ) throws Throwable {
        pageNavigation.I_visit_the_mobile_web_site_as_a_guest_user_in_mode(mode);
        mshcookie.createDcaCookie(cookieValue);
    }

    /**
     * To check whether page is served from given data center
     *
     * @param dcaValue - Name of the data center like DAL or RTP
     */
    @And("^I verify that dca cookie is (DAL|RTP)$")
    public void iVerifyThatDcaCookieIs(String dcaValue) throws Throwable {
        Wait.isPageLoaded();
        String cookieValue = Cookies.getCookieValue("dca");
        Assert.assertTrue("ERROR - APP: Page is not served from " + dcaValue + " DC", cookieValue.equals(dcaValue));
    }

    @Then("^I verify that zero search result page is displayed using mobile website$")
    public void iVerifyThatZeroSearchResultPageIsDisplayedUsingMobileWebsite() throws Throwable {
        String value = WebDriverManager.getCurrentUrl().contains("?") ? "&debug=" : "?&debug=";
        Navigate.visit(WebDriverManager.getCurrentUrl() + value + debugValue);
        int productCount = getMEWProductCountSearch();
        Assert.assertTrue("ERROR - APP: Not on zero search result page",
        +                productCount == 0);
    }

    private static int getMEWProductCountSearch() {
        Wait.untilElementPresent("category_browse.product_count");
        String productCountText = Elements.getText("category_browse.product_count");
        int prodCount = 9;
        Assert.assertFalse("ERROR - APP : Product count element is not displayed on page!!", productCountText.equals("null"));
        prodCount = (((productCountText.split(" ")[1]).equals("couldn't")) ? 0 : 99);
        return prodCount;
    }

    @Then("^I verify that RVI panel is displayed on pdp page for mobile$")
    public void iVerifyThatRVIPanelIsDisplayedOnPdpPageForMobile() throws Throwable {
        scrollToLazyLoadElement("recently_viewed.recently_viewed_panel");
        Assert.assertTrue("ERROR - APP: RVI panel is not displayed on PDP page for mobile",
            Elements.elementPresent("recently_viewed.recently_viewed_panel"));
    }

}

