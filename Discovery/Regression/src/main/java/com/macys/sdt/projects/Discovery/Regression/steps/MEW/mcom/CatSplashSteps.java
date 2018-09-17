package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatSplashSteps extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(CatSplashSteps.class);

    @Then("^I verify the navigation to \"([^\"]*)\" cat splash page from( registry)? homepage$")
    public void iVerifyTheNavigationToCatSplashPageFromHomepage(String category, String mode) throws Throwable {
        GlobalNav.openGlobalNav();
        if (macys() && mode == null) {
            GlobalNav.navigateOnGnByName("Shop");
        } else if (macys() && mode.contains("registry")) {
            GlobalNav.navigateOnGnByName("Gift Categories");
        }
        GlobalNav.navigateOnGnByName(category);
        log.info("Navigating to " + category + " category from main menu from global navigation");
        closeAlert();
        GlobalNav.closeGlobalNav();
        shouldBeOnPage("category_splash");
        Wait.secondsUntilElementPresent("category_splash.cat_name",15);
        Assert.assertTrue("ERROR - APP: Cat splash page load failed OR Navigated to wrong category", Elements.findElement("category_splash.cat_name").getText().equalsIgnoreCase(category));
        Assert.assertTrue("ERROR - APP: Cat splash page load is not succesful", WebDriverManager.getCurrentTitle().contains("- Macy's"));
        Assert.assertTrue("ERROR - APP: Footer container is not displayed in " + category + " catsplash page", Elements.elementPresent("category_splash.footer"));
        log.info("Verified " + category + " catsplash page navigation");
    }

    @And("^I verify media banners are displaying$")
    public void iVerifyMediaBannersAreDisplaying() throws Throwable {
        Wait.forPageReady();
        String error_msz = "ERROR-DATA: Media links data not avialble for " + new Browse().getCategoryId() +" Category Id";
        Assert.assertFalse(error_msz, SearchAndBrowseActions.getAllCatSplashMedialLinks().isEmpty());
        log.info("Verified media banners on catsplash page");
    }

    @And("^I verify all links navigating to respective page on \"([^\"]*)\" cat splash page$")
    public void iVerifyAllLinksNavigatingToRespectivePageOnCatSplashPage(String category) throws Throwable {
        Wait.forPageReady();
        for (String link : SearchAndBrowseActions.getAllCatSplashMedialLinks()) {
            if (link.contains("https://"))
                Assert.assertTrue("ERROR ENV: Media links are not redirecting on" + category + " catsplash", CommonUtils.verifyresponseCode(link));
        }
        for (String link : SearchAndBrowseActions.getAllCatSplashMediaImgLinks()) {
            Assert.assertTrue("ERROR - ENV : Image link for " + category + " did not return 200 status", CommonUtils.verifyresponseCode(link));
        }
    }
}
