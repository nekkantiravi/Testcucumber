package com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyOffers.getRandonPromotionContainer;
import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyOffers.offerList;
import static org.junit.Assert.assertTrue;

public class MyOffersSteps extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
    private static final Logger logger = LoggerFactory.getLogger(MyOffersSteps.class);
    private static PromotionService promotionService = new PromotionService();
    private static String promoCode;

    @When("^I navigate to deals and promotions page$")
    public void iNavigateToDealsAndPromotionspage() {
        Clicks.click("header.goto_deals_promotions");
        CreateProfile.closeSecurityAlertPopUp();
    }


   /* @Then("^I should see the promotions page$")
    public void iShouldSeeThePromotionsPage() {
        shouldBeOnPage("my_offers");
    } */

    @Then("^I verify the deals and promotions links work as expected")
    public void iVerifyTheDealsAndPromotionsLinksWorkAsExpected() throws Throwable {
        Wait.ajaxDone();
        Wait.forPageReady();
        List<WebElement> shopNowLinks = Elements.findElements("my_offers.shop_now");
        logger.info("All links count: " + shopNowLinks.size());
        for (WebElement link : shopNowLinks) {
            int responseCode = getResponseCode(link.getAttribute("href"));
            logger.info("Response code for link: " + link.getAttribute("href") + " = " + responseCode);
            Assert.assertTrue("ERROR - APP: Response code for promotion's 'Shop Now' href is not 200 and Actual Value is " + responseCode,
                    responseCode == 200);
        }
        logger.info("Verified: 'Shop Now' links work as expected on Deals and Promotions page");

        List<WebElement> walletOfferContainers = Elements.findElements("my_offers.wallet_offer_containers");
        logger.info("All links count: " + walletOfferContainers.size());
        for (int index = 0; index < walletOfferContainers.size(); index++) {
            //Needed to get the Add to Wallet elements freshly again in order to avoid the stale element exception
            walletOfferContainers = Elements.findElements("my_offers.wallet_offer_containers");
            walletOfferContainers.get(index).findElement(Elements.element("my_offers.add_to_wallet")).click();
            Wait.forPageReady();
            Assert.assertTrue("ERROR - APP: Add to Wallet button did not take sign in page for Guest user",
                    onPage("sign_in"));
            Navigate.browserBack();
            Wait.forPageReady();
//            Wait.ajaxDone();
        }
        logger.info("Verified: 'Add to Wallet' buttons work as expected on Deals and Promotions page");
    }

    private static int getResponseCode(String href) throws IOException {
        int responseCode = 0;
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(href);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();

            int redirectCounter = 0;
            while (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                url = new URL(connection.getHeaderField("Location"));
                connection = (HttpURLConnection) url.openConnection();
                responseCode = connection.getResponseCode();
                redirectCounter++;
                if (redirectCounter > 5) {
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Unable to get response for the url: " + href + "\t" + e.getMessage());
        }
        return responseCode;
    }

    @When("^I share any \"promotion\" via \"([^\"]*)\" from promotions page$")
    public void iShareAnyPromotionViaFromPromotionsPage(String emailOrGplus) {
        WebElement promotionContainer = getRandonPromotionContainer();
        promotionContainer.findElement(Elements.element("my_offers.promotion_share")).click();
        Wait.ajaxDone();
        Elements.findElement(emailOrGplus.equals("email") ? "my_offers.email_button" : "my_offers.google_button").click();
        Wait.forPageReady();
    }

    @Then("^I should see \"email to friend\" popup window$")
    public void iShouldSeeEmailToFriendPopupWindow() {
        assertTrue("Popup - 'email to a friend' has not been openend after clicking on share via email of a promotion", Elements.elementPresent("my_offers.email_to_a_friend_overlay"));
        logger.info("Verified: 'email to a friend' popup window is displeyed.");
    }

    @And("^I should also see cancel and send button on window$")
    public void iShouldAlsoSeeCancelAndSendButtonOnWindow() {
        assertTrue("'Cancel' button is not displayed on 'email to a friend' popup.", Elements.elementPresent("email_to_a_friend.cancel"));
        assertTrue("'Send' button is not displayed on 'email to a friend' popup.", Elements.elementPresent("email_to_a_friend.send"));
        logger.info("Verified: 'Cancel' and 'Send' buttons are displayed on 'email to a friend' popup window.");
    }

    @Then("^I verify the look and feel of the Deals & Promotions page$")
    public void iVerifyTheLookAndFeelOfTheDealsPromotionsPage() {
        shouldBeOnPage("my_offers");
        List<Map<String, Object>> offers = offerList();
        assertTrue("Deals and Promotions page is displayed with 0 promotions", offers.size() > 0);
        for (Map<String, Object> offer : offers) {

            assertTrue("Promotion " + offer.get("offerTitle").toString() + " does not have Shop Now link associated with it.", Boolean.valueOf(offer.get("isShopNowAvailable").toString()));
            assertTrue("Promotion " + offer.get("offerTitle").toString() + " does not have Promotion Header associated with it.", offer.get("offerTitle").toString() != null);
            assertTrue("Promotion " + offer.get("offerTitle").toString() + " does not have Promotion Header associated with it.", offer.get("offerSubHeaderPromo").toString() != null);
            String offerEndDate = offer.get("offerEndDate") != null ? offer.get("offerEndDate").toString() : null;
            if (offerEndDate != null)
                assertTrue("Promotion " + offer.get("offerTitle").toString() + " does not have End Date in correct format MM/dd/yyyy.", validateDateFormat(offer.get("offerEndDate").toString()));
            logger.info("Verified: The details of promoiton: " + offer.get("offerTitle").toString() + " are displayed correctly");
        }
    }

    @Then("I verify Get emails link")
    public void iVerifyGetEmailsLink() {
        Clicks.click("my_offers.get_emails");
        Wait.forPageReady();
        assertTrue("Create Profile Page is not displayed when clicked on 'get Emails' link.", onPage("create_profile"));
        Navigate.browserBack();
        Wait.forPageReady();
        logger.info("Verified: 'Get Emails' link is navigating to 'Create Profile' page");

    }

    @And("^I verify promotions for current date$")
    public void iVerifyPromotionsForCurrentDate() throws Throwable {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        List<Map<String, Object>> offers = offerList();
        for (Map<String, Object> offer : offers) {

            String offerEndDate = offer.get("offerEndDate") != null ? offer.get("offerEndDate").toString() : null;
            if (offerEndDate != null) {
                Date endDate = df.parse(offerEndDate);
                Date currentDate = new Date();

                assertTrue("Promotion " + offer.get("offerTitle").toString() + " does not have End Date greater than Current Date", endDate.compareTo(currentDate) >= 0);
            }
        }
        logger.info("Verified: The promotion End Date is greater than Current Date for all the promotions");

    }
    @Then("^I should not see hE tag in bright tag data dictionary object$")
    public void i_should_not_see_hE_tag_in_bright_tag_data_dictionary_object() throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
        Object tags = executor.executeScript("return MACYS.brightTag");
        Assert.assertFalse("hE tag in bright tag is displayed data dictionary object: " + tags.toString(),
                tags.toString().contains("hE"));
    }
    @Then("^I should see the following details on deals and promotions page$")
    public void iShouldSeeTheFollowingDetailsOnDealsAndPromotionsPage(DataTable table) {

        String title;
        String expectedMessage;
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            title = row.get("title");
            expectedMessage = row.get("expected_message");
            switch (title) {
                case "wallet_desc":
                    String actualText=Elements.findElement("deals_and_promotions.wallet_desc").getText();
                    Assert.assertTrue("Actual " + actualText + "\\n Expected" +
                            expectedMessage, actualText.equals(expectedMessage));
                    break;
                case "cards_image":
                    Assert.assertTrue("wallet image doesn't exist",
                            Elements.elementPresent("deals_and_promotions.wallet_image"));
                    break;
                case "customer_wallet":
                    if (expectedMessage.contains("first name of customer"))
                    {
                        String customerName= TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName() + "'s wallet"; // get customer fist name
                        Assert.assertEquals("customer first name not displayed correctly", customerName, Elements.findElement("my_offers.customer_wallet").getText());
                    }
                    break;
                case "find_out_more":
                    Assert.assertTrue("find out more link doens't exist", Elements.elementPresent("my_offers.wallet_find_out_more"));
                    break;
                default:
                    Assert.fail("Unrecogzined " + title);
            }
        }
    }
    @When("^I click on go to my wallet in deals and promotions page$")
    public void iClickOnGoToMyWalletInDealsAndPromotionPage(){
        Clicks.click("my_offers.goto_my_wallet");
    }

    @When("^I click on the get started in deals and promotions page$")
    public void iClickOnGetStartedInDealsAndPromotionPage(){
        Clicks.click("my_offers.get_started");
    }




    // Check the Date format to be MM/dd/yyyy
    public boolean validateDateFormat(String date) {
        StringBuilder sBuffer = new StringBuilder(date);
        String mon;
        String dd;
        String year;

        // Store the Date in String Buffer and Break down the date
        mon = sBuffer.substring(0, 2);
        dd = sBuffer.substring(3, 5);
        year = sBuffer.substring(6, 10);
        System.out.println("DD: " + dd + " Mon: " + mon + " Year: " + year);

        // Validating Date Format for Tunr In Date and Live Date to be MM/dd/yyyy
        return mon.matches("0[1-9]|1[0-2]") && dd.matches("0[1-9]|[12][0-9]|3[01]")
                && year.matches("(19|20)\\d\\d");
    }

    @Then("^I should see promotions page$")
    public void iShouldSeePromotionsPage() throws Throwable {
        shouldBeOnPage("my_offers");
    }
}


