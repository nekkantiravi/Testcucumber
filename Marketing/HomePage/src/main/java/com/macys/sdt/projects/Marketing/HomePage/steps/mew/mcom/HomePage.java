package com.macys.sdt.projects.Marketing.HomePage.steps.mew.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.steps.MEW.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import org.junit.Assert;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import cucumber.api.PendingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.macys.sdt.framework.utils.TestUsers.getValidVisaCreditCard;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HomePage extends StepUtils {

  private PageNavigation pageNavigation = new PageNavigation();
  private static ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
  private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
  public static String promoCode;
  public static List<Map<String, String>> promoCodes;
  private ArrayList<CreditCard> cardsAdded = new ArrayList<>();
  private ProfileAddress profileAddress;
  public static int totalOffers;
  LoyalistDetails loyallistDetails = new LoyalistDetails();
  double rewardCardBalance = 0;
  String remainingPointnsToNextCard;
  private static String poolName;
  private static String adUrl;

  @Given("^I create a promotion in Astra for mobile for the home page$")
  public void iCreateAPromotionInAstraForMobileForTheHomePage() throws Throwable {
    System.out.format("TODO: Holdover from MEW... may not be necessary?");
    poolName = "MEW2_HP_MB";
  }

  @Given("^I create a promotion in Astra for mobile without URL for the home page$")
  public void iCreateAPromotionInAstraForMobileWithoutUrlForTheHomePage() throws Throwable {
    System.out.format("TODO: We don't seem to have any of these kinds of ads?\n");
    throw new PendingException();
  }

  @And("^I click on marketing ad on the home page$")
  public void iClickOnMarketingAdOnTheHomePage() {
    Wait.forPageReady();
    WebElement marketingAd = Elements.findElement("home.marketing_ad_links");
    adUrl = marketingAd.getAttribute("href");
    System.out.format("Ad URL: %s\n", adUrl);
    Clicks.click("home.ad_banners");
    Wait.forPageReady();
  }

  @Then("^I should be redirected from the home page to the given URL for this ad$")
  public void thenShouldBeRedirectedFromTheHomePageToTheGivenUrlForThisAd() {
    String expectedUrl = adUrl.replaceAll("https?://", "https://");
    String actualUrl = WebDriverManager.getCurrentUrl().replaceAll("https?://", "https://");
    Assert.assertEquals("AdLink failed to reach expected location:\nExpected: "+expectedUrl+"\nActual: "+actualUrl, expectedUrl, actualUrl);
  }

  @Then("^I should see marketing ad on the home page$")
  public void iShouldSeeMarketingAdOnTheHomePage() {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @And("^I click on marketing ad without url on the home page$")
  public void iClickOnMarketingAdWithoutUrlOnTheHomePage() {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^I should still stay on home page$")
  public void iShouldStillStayOnHomePage() {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
