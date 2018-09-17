package com.macys.sdt.shared.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.bloomingdales;
import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.framework.utils.StepUtils.onPage;

public class globalNavSignInSteps {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    @When("^I Sign in using valid credentials by tapping on sign in link$")
    public void iSignInUsingValidCredentials() {
        CommonUtils.signInOrCreateAccount(true);
        Wait.forPageReady();
    }

    @Then("^I tap on bloomingdales logo to goto home page$")
    public void iTapOnBloomingdalesLogoToGotoHomePage() {
        Navigate.visit("home");
        if(isPresentOnGlobalNav("shop")){
            GlobalNav.navigateOnGnByName("shop");
        }
    }

    @Then("^I verify that sign out link is present on GN$")
    public void iVerifyThatSignOutLinkIsPresentOnGN(){
        Assert.assertTrue("Sign out link is not present on global nav", isPresentOnGlobalNav("sign out"));
        logger.info("Sign out link is present on global nav");
    }

    @And("^I tap on the GN “Sign out” link$")
    public void iTapOnTheGNSignOutLink() {
        GlobalNav.navigateOnGnByName("sign out");
        Wait.forPageReady();
    }

    @Then("^I verify that GN closes and I remain on the Tennis browse page$")
    public void iVerifyThatGNClosesAndIRemainOnTheTennisBrowsePage() {
        Assert.assertTrue(Elements.findElement("category_browse.browse_category_name").getText().contains("Tennis"));
    }

    @Then("^I verify that I am on my account page$")
    public void iVerifyThatIAmOnMyAccountPage() {
        Wait.until(() -> onPage("my_account"));
        Assert.assertTrue("not on my account page", Elements.elementPresent("my_account.profile_panel"));
        logger.info("on My Account page");
    }

    @When("^I tap on the footer's \"Sign out” link$")
    public void iTapOnTheFooterSSignOutLink() {
        Wait.secondsUntilElementPresent("footer.sign_in_out",20);
        WebElement signOut = Elements.findElement("footer.sign_in_out");
        Clicks.click(signOut);
    }

    @Then("^I should be redirected to the HP$")
    public void iShouldBeRedirectedToTheHP() {
        Assert.assertTrue("Not on home page", Wait.until(() -> onPage("home"), 10));
        logger.info("Redirected to Home Page");
    }

    @And("^I verify the GN sign out link is updated to “Sign in”$")
    public void iVerifyTheGNSignOutLinkIsUpdatedToSignIn() {
        Assert.assertTrue("sign in is not present on global nav",isPresentOnGlobalNav("sign in"));
    }

    @And("^I verify that Sign in link persists on global nav$")
    public void iVerifyThatSignInLinkPersistsOnGlobalNav(){
        Assert.assertTrue("sign in is not present on global nav",isPresentOnGlobalNav("sign in"));
        logger.info("Sign in link is present on global nav");
    }

    public boolean isPresentOnGlobalNav(String fob){
        GlobalNav.openGlobalNav();
        Wait.untilElementPresent("home.current_nav");
        List<WebElement> elements = Elements.findElements("home.current_nav");
        for (WebElement el : elements) {
            if (el.getText().equalsIgnoreCase(fob)) {
                logger.info(fob + " is present in global nav");
                return true;
            }
            }
        GlobalNav.closeGlobalNav();
        logger.info(fob + " is not present in global nav");
        return false;
    }

}
