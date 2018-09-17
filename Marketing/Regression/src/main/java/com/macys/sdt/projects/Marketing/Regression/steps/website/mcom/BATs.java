package com.macys.sdt.projects.Marketing.Regression.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom.OCWallet;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.steps.website.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.*;

import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet.addCard;
import static org.junit.Assert.assertTrue;

public class BATs extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    private static CreditCard cardAddToWallet;
    private static ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
    private static OCWallet ocWallet = new OCWallet();
    private static Home home = new Home();
    private CheckoutSteps checkoutSteps = new CheckoutSteps();
    private static final Logger logger = LoggerFactory.getLogger(BATs.class);
    private static String promoCode = null;
    private static String promoDescription = null;
    private static WebDriver driver = new ChromeDriver();

    @And("^I add a store credit card to my wallet$")
    public void iAddAStoreCreditCardToMyWallet() throws Throwable {

        if (macys()) {
            cardAddToWallet = Wallet.getValidCreditCard("Macy's American Express");
        } else {
            cardAddToWallet = Wallet.getValidCreditCard("Bloomingdale's");
        }
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        addCard(cardAddToWallet);
    }

    @Then("^my credit card information should be saved successfully$")
    public void myCreditCardInformationShouldBeSavedSuccessfully() throws Throwable {

        ocWallet.i_Should_See_Added_Cards_In_List_Of_Cards();
    }

    @Then("^I verify the basic attributes of the My Account Page$")
    public void iVerifyTheBasicAttributesOfTheMyAccountPage() throws Throwable {

        myAccountSteps.iNavigateToMyAccountPage();
        logger.info("-> Verify different sections on my account page!!");
        assertTrue("Credit Card division not found in My account page",
                Elements.findElement("my_account.credit_card_div").isDisplayed());
        assertTrue("Orders division not found in My account page",
                Elements.findElement("my_account.orders_div").isDisplayed());
        assertTrue("Wallet division not found in My account page",
                Elements.findElement("my_account.wallet_div").isDisplayed());
        assertTrue("Plenti division not found in My account page",
                Elements.findElement("my_account.plenti_div").isDisplayed());
        assertTrue("Wish list division not found in My account page",
                Elements.findElement("my_account.lists_div").isDisplayed());
    }

    @Given("^I visit the production web site as an existing user$")
    public void iVisitTheProductionWebSiteAsAnExistingUser() throws Throwable {

        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        myAccountSteps.iCreateANewProfile();
    }

    @And("^I add a credit card to my wallet$")
    public void iAddACreditCardToMyWallet() throws Throwable {

        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        shopAndBrowse.I_add_a_credit_card_to_My_Wallet_as_default_card_on_My_Wallet_page();
    }

   /* @Then("^I perform all valid Plenti lookups on the page$")
    public void iPerformAllValidPlentiLookupsOnThePage() throws Throwable {

        logger.info("Verifying Plenti lookup based on ID in My Account page");
        String plentiTypes[] = {"fully_enrolled", "pre-enrolled-DNE", "anonymous-DNE", "cancelled-DNE", "blocked-DNE"};

        for (int i = 0; i < plentiTypes.length; i++) {
            UslInfo uslInfo = Usl.getEnrolledUslId(plentiTypes[i]);

            if (onPage("my_account") && (uslInfo != null)) {

                String pageName = "my account";
                Wait.untilElementPresent(pageName + ".plenti_phone_lookup_tab");
                Clicks.click("my_account.plenti_phone_lookup_tab");
                TextBoxes.typeTextbox("my_account.plenti_lookup_textfield", uslInfo.getUslPhone());
                Clicks.click("my_account.plenti_lookup_button");
                if (Elements.elementPresent("my_account.plenti_lookup_button")) {
                    Usl.verifyUslLookupMyAccount(uslInfo.getUslType().getName(), uslInfo);
                } else { Assert.fail("Plenti could not be added"); }
                Usl.removePlentiFromMyAccount();

            }
        }
    } */


    @And("^I verify FOBS are displayed and return a (\\d+) OK$")
    public void iVerifyFOBSAreDisplayedAndReturnAOK(int httpCode, DataTable table) throws Throwable {

        String url = null;
        String FOBName = null;
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {

            switch (row.get("FOBS")) {
                case "BED & BATH":
                    url = Elements.findElement("home.bedbath_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.bedbath_fob").getText();
                    break;
                case "WOMEN":
                    url = Elements.findElement("home.women_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.women_fob").getText();
                    break;
                case "SHOES":
                    url = Elements.findElement("home.shoes_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.shoes_fob").getText();
                    break;
                case "HANDBAGS":
                    url = Elements.findElement("home.handbags_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.handbags_fob").getText();
                    break;
                case "JEWELRY":
                    url = Elements.findElement("home.jewelry_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.jewelry_fob").getText();
                    break;
                case "BEAUTY":
                    url = Elements.findElement("home.beauty_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.beauty_fob").getText();
                    break;
                case "MEN":
                    url = Elements.findElement("home.men_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.men_fob").getText();
                    break;
                case "KIDS":
                    url = Elements.findElement("home.kids_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.kids_fob").getText();
                    break;
                case "HOME":
                    url = Elements.findElement("home.home_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.home_fob").getText();
                    break;
                case "JUNIORS":
                    url = Elements.findElement("home.juniors_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.juniors_fob").getText();
                    break;
                case "WATCHES":
                    url = Elements.findElement("home.watches_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.watches_fob").getText();
                    break;
                case "ACTIVE":
                    url = Elements.findElement("home.active_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.active_fob").getText();
                    break;
                case "BRANDS":
                    url = Elements.findElement("home.brands_fob").getAttribute("href");
                    FOBName = Elements.findElement("home.brands_fob").getText();
                    break;
                case "STARTER IDEAS":
                    url = Elements.findElement("registry_home.starter_ideas_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.starter_ideas_fob").getText();
                    break;
                case "WEDDING SHOP":
                    url = Elements.findElement("registry_home.wedding_shop_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.wedding_shop_fob").getText();
                    break;
                case "WEDDING REGISTRY":
                    url = Elements.findElement("registry_home.wedding_registry_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.wedding_registry_fob").getText();
                    break;
                case "DINING":
                    url = Elements.findElement("registry_home.dining_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.dining_fob").getText();
                    break;
                case "KITCHEN":
                    url = Elements.findElement("registry_home.kitchen_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.kitchen_fob").getText();
                    break;
                case "LUGGAGE":
                    url = Elements.findElement("registry_home.luggage_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.luggage_fob").getText();
                    break;
                case "CLEANING & ORGANIZING":
                    url = Elements.findElement("registry_home.cleaning_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.cleaning_fob").getText();
                    break;
                case "HOME DECOR":
                    url = Elements.findElement("registry_home.home_decor_fob").getAttribute("href");
                    FOBName = Elements.findElement("registry_home.home_decor_fob").getText();
                    break;
            }
            Map<String, String> headers = new HashMap<>();

            Response response = RESTOperations.doGET(url, headers);
            int statusCode = response.getStatus();
            Assert.assertTrue(FOBName + " FOB returned " + statusCode + " on get call",
                    statusCode == httpCode || statusCode == 302 || statusCode == 301);
        }
    }

    @Then("^I verify basic attributes of the home page$")
    public void iVerifyBasicAttributesOfTheHomePage() throws Throwable {

        logger.info("Verifying Top FOBs");
        home.verifyTopFOBs();
        logger.info("Top FOBs verified successfully");
        logger.info("Verifying Top Navs");
        if (macys()) {
            Assert.assertTrue("Sign In link not found", Elements.findElement(
                    "home.goto_sign_in_link").isDisplayed());
            Assert.assertTrue("My Account link not found", Elements.findElement(
                    "home.goto_guest_my_account").isDisplayed());
            Assert.assertTrue("Stores link not found", Elements.findElement(
                    "home.stores").isDisplayed());
            Assert.assertTrue("Customer Service link not found", Elements.findElement(
                    "home.customer_service_link").isDisplayed());
        } else {
            Assert.assertTrue("My Account link not found", Elements.findElement(
                    "home.goto_sign_in_link").isDisplayed());
            Assert.assertTrue("Stores & Events link not found", Elements.findElement(
                    "home.stores_events_link").isDisplayed());
            Assert.assertTrue("USD link not found", Elements.findElement(
                    "home.usd_link").isDisplayed());
            Assert.assertTrue("Iship flag image not found", Elements.findElement(
                    "home.iship_flag_img").isDisplayed());
            Assert.assertTrue("Wish List link not found", Elements.findElement(
                    "home.wish_list_link").isDisplayed());
            Assert.assertTrue("Brown bag link not found", Elements.findElement(
                    "home.brown_bag_link").isDisplayed());
        }
        logger.info("Top Navs verified successfully");
        if (macys()) {
            logger.info("Verifying sub Navs");
            home.verifySubNavs();
        }
    }



    @And("^I retrieve the sign-up SUPC$")
    public void iRetrieveTheSignUpSUPC() throws Throwable {

        Wait.forPageReady();
        if (!onPage("oc_my_wallet")) {
            Assert.fail("My Wallet page not found");
        }
        Wait.untilElementPresent("oc_my_wallet.single_promo_code");
        if (!Elements.elementPresent("oc_my_wallet.single_promo_code")) {
            Thread.sleep(3000);
            Navigate.browserRefresh();
        }
        promoCode = Elements.findElement("oc_my_wallet.single_promo_code").getText();
    }

    @Then("^I verify the sign-up SUPC on the checkout page$")
    public void iVerifyTheSignUpSUPCOnTheCheckoutPage() throws Throwable {

        Wait.forPageReady();
        if (!onPage("shopping_bag")) {
            Assert.fail("Shopping bag page not found");
        }
        TextBoxes.typeTextbox("shopping_bag.promo_code", BATs.promoCode);
        Clicks.click("shopping_bag.apply_promo_code_button");
        if (Elements.elementPresent("shopping_bag.promo_inline_error_message")) {
            Assert.fail("Promo code: " + promoCode + " could not be applied");
        }
        Wait.untilElementPresent("shopping_bag.promo_description");
        if (Elements.elementPresent("shopping_bag.promo_description")) {
            BATs.promoDescription = Elements.findElement("shopping_bag.promo_description").getText().trim();
        } else {
            Assert.fail("Promo Code description not found");
        }
        checkoutSteps.I_checkout_until_I_reach_the_page_as_a_user
                ("responsive_checkout", "signed in", null, null);
        Assert.assertTrue("SUPC promotion not found on checkout page",
                Elements.elementPresent("responsive_checkout_signed_in.promo_description"));
        // Assert.assertTrue("SUPC promotion not found on checkout page",
        //  Elements.findElement("responsive_checkout_signed_in.promo_description").getText()
        //  .contains(promoDescription));
    }

    @Given("^I am on the EE page$")
    public void iAmOnTheEEPage() throws Throwable {

        Thread.sleep(5000);
      //  WebDriverManager.getWebDriver().close();
        BATs.driver.get("http://sdt.ee.fds.com/cm/");
        BATs.driver.findElement(By.xpath("//input[@id='username']")).sendKeys("m744004");
        BATs.driver.findElement(By.xpath("//input[@id='password']")).sendKeys("lasvegas9227");
        BATs.driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
    }

    @And("^I retrieve the appropriate job and place for execution$")
    public void iRetrieveTheAppropriateJobAndPlaceForExecution(List<String> jobs) throws Throwable {

        String url_bcom = "http://www.qa11codebloomingdales.fds.com";
        String url_mcom = "http://www.qa15codemacys.fds.com";

        for (String job : jobs) {

            Thread.sleep(3000);
            BATs.driver.findElement(By.xpath("//span[text()=' Load Template']")).click();
            Thread.sleep(2000);
            BATs.driver.findElement(By.xpath("//input[@id='project_template']")).clear();
            Thread.sleep(2000);
            BATs.driver.findElement(By.xpath("//input[@id='project_template']")).sendKeys(job);
            Thread.sleep(3000);
            BATs.driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
            Thread.sleep(3000);
            BATs.driver.findElement(By.linkText("Parameters")).click();
            Thread.sleep(3000);
            BATs.driver.findElement(By.xpath("//input[@name='website']")).clear();
            Thread.sleep(2000);

            if (job.contains("BCOM")) {
                BATs.driver.findElement(By.xpath("//input[@name='website']")).sendKeys(url_bcom);
            } else {
                BATs.driver.findElement(By.xpath("//input[@name='website']")).sendKeys(url_mcom);
            }
            Thread.sleep(4000);
            BATs.driver.findElement(By.linkText("Scenarios")).click();
            Thread.sleep(4000);
            Actions actions = new Actions(BATs.driver);
            actions.moveToElement(driver.findElement(By.xpath("//input[@class='scenario_selected_all']"))).perform();
            actions.click().perform();
            Thread.sleep(4000);
            BATs.driver.findElement(By.xpath("//span[text()=' Submit Execution']")).click();
            Thread.sleep(3000);
            BATs.driver.findElement(By.xpath("//div[@class='modal-footer']/button[text()='No']")).click();
            Thread.sleep(3000);
            if (BATs.driver.findElement(By.xpath("//div[@class='modal-content']/div/div[text()='Successfully created EE object.']")).isDisplayed()) {
                BATs.driver.findElement(By.xpath("//div[@class='modal-content']/div[@class='modal-footer']/button")).click();
                System.out.println("<<<<<<<<<<<<<<<<<<<<<" + job + " was successfully placed in execution queue>>>>>>>>>>>>>>>>>");
            } else {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<" + job + " was not placed in execution queue>>>>>>>>>>>>>>>>>");
            }
        }
    }
}
