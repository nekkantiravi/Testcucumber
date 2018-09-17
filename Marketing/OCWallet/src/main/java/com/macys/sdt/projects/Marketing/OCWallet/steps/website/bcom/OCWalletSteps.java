package com.macys.sdt.projects.Marketing.OCWallet.steps.website.bcom;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoginCredentials;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyallistAssociation;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CheckoutUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet.offersList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OCWalletSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    private static String promoCode;
    private static List<String> multiplePromoCodes = new ArrayList();
    private static List<Map<String, Object>> offersList;
    private static Registry registry = new Registry();
    private static PromotionService promotionService = new PromotionService();
    List<Map<String, String>> walletPromoCodes = new ArrayList<Map<String, String>>();
    private static String cardNumCheckout;
    private static String cardNumWallet;
    LoyalistDetails loyallistDetails = new LoyalistDetails();

    @Given("^I saved \"omnichannel\" offer promo code in wallet$")
    public void iSavedOfferPromoCodeInWallet() throws Throwable {
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        promoCode = promotionService.getOmnichannelPromoCode();
        Wallet.addValidOffer(promoCode);
    }

    @Then("^I should see added offer in my offers section$")
    public void iShouldSeeAddeOfferInMyOffersSection() throws Throwable {
        offersList = offersList();
        boolean offerAdded = false;
        if (offersList != null) {
            for (Map<String, Object> offer : offersList) {
                offerAdded = offer.get("onlineCode").toString().equalsIgnoreCase(promoCode);
                break;
            }
        } else {
            Assert.fail("No Offer displayed on My Wallet.");
        }
        Assert.assertTrue("Promo Offer: '" + promoCode + "' has not been listed in the Wallet.", offerAdded);
    }

    @Given("^I should see added offer promo code as an ONLINE & IN STORE and corresponding in-store code in offer info$")
    public void iShouldSeeAddedOfferPromoCodeAndCorrespondingInStoreCodeInOfferInfo() throws Throwable {
        Map<String, Object> offer = null;
        boolean offerAdded = false;
        if (offersList != null) {
            for (int index = 0; index < offersList.size(); index++) {
                offer = offersList.get(index);
                if (offer.get("onlineCode").toString().equalsIgnoreCase(promoCode)) {
                    offerAdded = offer.get("onlineCode").toString().equalsIgnoreCase(promoCode);
                    break;
                }
            }
        } else {
            Assert.fail("No Offer displayed on My Wallet.");
        }
        Assert.assertTrue("Promo Offer: '" + promoCode + "' has not been listed in the Wallet.", offerAdded);
        Assert.assertTrue("Offer Type displayed for the Promo Code added is not 'ONLINE & IN STORE'. Offer Type displayed is '" + offer.get("offerType").toString() + "'.", offer.get("offerType").toString().equalsIgnoreCase("ONLINE & IN STORE"));
        Assert.assertTrue("In Store Code is not displayed for the Offer: '" + promoCode + "'", offer.get("instoreCode") != null);
    }

    @Then("^I should see the error message \"([^\"]*)\" on the add offer overlay after saving with empty value$")
    public void iShouldSeeTheErrorMessageOnAddOfferOverlay(String ErrorMsg) throws Throwable {
        String emptyPromoCode = "";
        // promotionService.getWalletEligiblePromoCode();
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", emptyPromoCode);
        Clicks.click("add_offer_dialog.save_offer");
        assertTrue("Expected Error '" + ErrorMsg + "' message is not displayed",
                Elements.findElement("add_offer_dialog.inline_error").getText().equalsIgnoreCase(ErrorMsg));
    }

    @When("^I create a new wedding registry with event date within (\\d+) days$")
    public void iCreateANewWeddingRegistryWithEventDateWithinDays(int evenDatePast) throws Throwable {
        Calendar cal = Calendar.getInstance();
        Date modifiedDate;
        UserProfile regUser;
        Random random = new Random();
        modifiedDate = DateUtils.addDays(new Date(), -(random.nextInt((evenDatePast - 1) + 1) + 1));

        cal.setTime(modifiedDate);
        String year = Integer.toString(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        String monthName = WordUtils.capitalizeFully(Month.of(month + 1).name());

        regUser = TestUsers.getNewRegistryUser();
        regUser.getRegistry().setEventType("WEDDING");
        regUser.getRegistry().setEventMonth(monthName);
        regUser.getRegistry().setEventDay(day);
        regUser.getRegistry().setEventYear(year);
        signInOrCreateRegistry(regUser);
    }

    @And("^I capture the completion promo code from registry manager page$")
    public void iCaptureTheCompletionPromoCodeFromRegistryManagerPage() throws Throwable {
        String promoBannerName = macys() ? "completion_banner" : "get_offer_now";
        Wait.untilElementPresent("registry_manager." + promoBannerName);
        if (Elements.elementPresent("registry_manager." + promoBannerName)) {
            if (bloomingdales()) {
                Clicks.click("registry_manager.get_offer_now");
            }
            promoCode = Elements.findElement("registry_manager.online_promo_code_no").getAttribute("innerHTML");
        } else {
            Assert.fail("Completion Promo Code has not displayed");
        }
    }

    @Then("^I should see \"([^\"]*)\" error message after saving valid registry completion promo code on wallet page$")
    public void iShouldSeeErrorMessageAfterSavingValidRegistryCompletionPromoCodeOnWalletPage(String ErrorMessage) throws Throwable {
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", promoCode);
        Clicks.click("add_offer_dialog.save_offer");
        assertTrue("Expected Error '" + ErrorMessage + "' message is not displayed",
                Elements.findElement("add_offer_dialog.error_message").getText().equalsIgnoreCase(ErrorMessage));

    }

    @Then("^I should see \"Changes saved to your wallet.\" success message after saving valid registry completion promo code on wallet page$")
    public void iShouldSeeErrorMessageAfterSavingValidRegistryCompletionPromoCodeOnWalletPage() throws Throwable {
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", promoCode);
        Clicks.click("add_offer_dialog.save_offer");
        assertTrue("'Changes saved to your wallet.' message is not displayed",
                Elements.findElement("oc_my_wallet.success_message_div").getText().equalsIgnoreCase("Changes saved to your wallet."));

    }

    /**
     * Creates or logs into a registry
     *
     * @param regUser profile to create
     * @throws Throwable if any exception occurs
     */
    public void signInOrCreateRegistry(UserProfile regUser) throws Throwable {
        ProfileAddress userAddress = regUser.getUser().getProfileAddress();
        LoginCredentials credentials = regUser.getUser().getLoginCredentials();
        com.macys.sdt.framework.model.registry.Registry userRegistry = regUser.getRegistry();

        new PageNavigation().I_visit_the_web_site_as_a_guest_user();
        Clicks.click("home.goto_wedding_registry");
        CommonUtils.closeIECertError();
        Wait.forPageReady();
        if (safari()) { //slow safari hence the wait
            Wait.secondsUntilElementPresent("registry_home.goto_create_registry", 15);
        }
        if(macys()){
            Clicks.click("registry_home.view_registry");
            Wait.forPageReady();
        }else {
            new PageNavigation().iSelectCreateRegistry();
        }
        pausePageHangWatchDog();
        Wait.secondsUntilElementPresent("registry_sign_in.existing_user_email", 5);
        boolean newRegistryEnabled = onPage("new_registry_sign_in");
        if (newRegistryEnabled) {
            Wait.secondsUntilElementPresent("new_registry_sign_in.email", 10);
            if (safari() && Elements.findElement("new_registry_sign_in.sign_in_button").isDisplayed()) {
                CommonUtils.retryAction(() -> {
                    Navigate.browserRefresh();
                    Wait.forPageReady();
                    return Elements.findElement("new_registry_sign_in.sign_in_button").isEnabled();
                }, 3, "ERROR - APP : sign in button on registry sign in page is not enabled!!");
            }
            TextBoxes.typeTextbox("new_registry_sign_in.email", userAddress.getEmail());
            TextBoxes.typeTextbox("new_registry_sign_in.password", credentials.getPassword());
            Clicks.javascriptClick("new_registry_sign_in.sign_in_button");
            Wait.forPageReady();
        } else {
            TextBoxes.typeTextbox("registry_sign_in.existing_user_email", userAddress.getEmail());
            TextBoxes.typeTextbox("registry_sign_in.existing_user_password", credentials.getPassword());
            Clicks.javascriptClick("registry_sign_in.existing_user_continue_button");
        }
        if (Cookies.getCookieValue("SMISCGCs").contains("regId") && !Cookies.getCookieValue("SMISCGCs").contains("regId1_92_null")) {
            Matcher m = Pattern.compile("regId1_92_(\\d+)").matcher(Cookies.getCookieValue("SMISCGCs"));
            userRegistry.setId(m.find() ? m.group(1) : null);
        }
        if (userRegistry.getId() != null) {
            logger.info("User is already logged into registry");
        } else {
            Wait.forPageReady();
            CreateProfile.closeSecurityAlertPopUp();
            newRegistryEnabled = onPage("new_registry_sign_in");
            if (newRegistryEnabled) {
                Wait.untilElementPresent("new_registry_sign_in.error_message");
                Wait.untilElementPresent("new_create_registry.verify_page");
                if (onPage("new_registry_sign_in") && Elements.elementPresent("new_registry_sign_in.error_message")) {
                    if (prodEnv()) {
                        throw new ProductionException("Cannot create accounts on prod!");
                    }
                    Clicks.click("new_registry_sign_in.verify_page");
                    Wait.forPageReady();
                    CreateRegistry.createRegistryUser(regUser);
                    Wait.forPageReady();
                    if (safari()) {
                        Wait.secondsUntilElementPresent("registry_welcome.verify_page", 20);
                    }
                } else if (Wait.secondsUntilElementPresent("new_create_registry.verify_page", 8)
                        && onPage("new_create_registry")) {
                    CreateRegistry.createRegistryUserForExistingUser(regUser);
                    Wait.forPageReady();
                } else if (!onPage("registry_manager")) {
                    Wait.secondsUntilElementPresent("registry_welcome.verify_page", (safari() ? 15 : 5));
                    shouldBeOnPage("registry_welcome");
                }
            } else {
                if (onPage("registry_sign_in") && Elements.elementPresent("registry_sign_in.error_message")) {
                    if (prodEnv()) {
                        throw new ProductionException("Cannot create accounts on prod!");
                    }
                    TextBoxes.typeTextbox("registry_sign_in.new_user_email", userAddress.getEmail());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_email_verify", userAddress.getEmail());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_password", credentials.getPassword());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_password_verify", credentials.getPassword());
                    Clicks.click("registry_sign_in.new_user_continue_button");
                    CreateRegistry.fillRegistryUserDetails(regUser);
                } else if (onPage("create_registry")) {
                    CreateRegistry.fillRegistryUserDetailsForExistingUser(regUser);
                }
                Wait.forPageReady();
            }
        }
        if (safari()) {
            ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
            try {
                shopAndBrowse.i_remove_all_items_from_the_shopping_bag();
            } catch (Exception ignored) {
            }
        }
        if (!onPage("registry_manager")) {
            Navigate.visit("registry_home");
            Wait.forPageReady();
        }
        assertFalse("ERROR - ENV : Registry Services are in Batch Mode!!",
                (macys() ? Elements.getText(Elements.element("registry_manager.error_message")).
                        contains("Your registry is not available online") : Elements.elementPresent(Elements.element
                        ("registry_manager.registry_mode"))));
        assertFalse("ERROR - ENV : Registry Flex Template services are down!!",
                title().contains(macys() ? "Site Unavailable" : "oops"));
        shouldBeOnPage("registry_manager");
        resumePageHangWatchDog();
    }

    @Then("^I should see add a credit or debit card button$")
    public void iShouldSeeAddACreditOrDebitCardButton() {
        assertTrue("'ADD CREDIT/DEBIT CARDS' is not displayed on Wallet Page.",
                Elements.elementPresent("my_bwallet.add_credit_card_btn"));
    }

    @And("^I should see \"MY PAYMENT OPTIONS\" header on My Wallet page$")
    public void iShouldSeeMyPaymentOptionsHeaderOnMyWalletPage() {
        assertTrue("MY PAYMENT OPTIONS' header is not displayed on Wallet Page",
                Elements.elementPresent("my_bwallet.payment_options_header"));
    }


    @And("^I should see \"CREDIT / DEBIT CARDS\" sub header on My Wallet page$")
    public void iShouldSeeCreditDebitCardsSubHeaderOnMyWalletPage() {
        assertTrue("'CREDIT / DEBIT CARDS' sub header is not displayed on Wallet page.",
                Elements.elementPresent("my_bwallet.credit_cards_header"));
    }

    @Then("^I should see no credit cards in wallet message: \"Keep all your payment options in one place and make checkout easier and faster by adding your Bloomingdale's card and any other credit or debit card to your bWallet.\" in MY PAYMENT OPTIONS section$")
    public void iShouldSeeNoCreditCardsInWalletMessageInMyPaymentOptionsSection() {
        assertTrue("No Cards message 'You don't currently have any stored payment methods.' is not displayed on " +
                "Cards section of Wallet Page.", Elements.getText("my_bwallet.no_saved_credit_card_message").
                equals("Keep all your payment options in one place and make checkout easier and faster by adding " +
                "your Bloomingdale's card and any other credit or debit card to your bWallet."));
    }

    @Then("^I should see disclaimer text \"American Express is a federally registered service mark of American Express and is used by Department Stores National Bank pursuant to a license.The Bloomingdale's American Express Card program is issued and administered by Department Stores National Bank.\" at the bottom of my wallet page$")
    public void iShouldSeeDisclaimerTextForBloomingdalesAmericanExpressCard() {
        assertTrue("", Elements.getText("my_bwallet.amex_disclaimer").equals("American Express is a federally" +
                " registered service mark of American Express and is used by Department Stores National Bank pursuant to a license.\n" +
                "The Bloomingdale's American Express Card program is issued and administered by Department Stores National Bank."));
    }

    @Then("^I should see \"There are no valid offers available in your bWallet.Offers that you saved previously may have expired.\" message in MY OFFERS section$")
    public void iShouldSeeThereAreNoValidoffersAvailableInYourbWalletMessage() {
        assertTrue("Message - 'There are no valid offers available in your bWallet.Offers that you saved previously" +
                " may have expired.' is not displayed in My Offers section.", Elements.getText("my_bwallet.no_offer_message").
                equals("There are no valid offers available in your bWallet.\nOffers that you saved previously may have expired."));
    }

    @And("I should see add an offer button")
    public void iShouldSeeAddAnOfferButton() {
        assertTrue("'Add An Offer' button is not displayed.", Elements.elementPresent("my_bwallet.add_offer_pass"));
    }

    @Then("^I should see \"LEARN MORE about bWallet and how to use it online and in store\" on wallet page$")
    public void iShouldSeelearnMoreAboutBWalletAndHowTouseItOnlineAndInStore() {
        assertTrue("'LEARN MORE about bWallet and how to use it online and in store' message is not displayed.", Elements.getText("my_bwallet.learn_more_message").equals("LEARN MORE about bWallet and how to use it online and in store"));
    }

    @When("^I select 'LEARN MORE' link in wallet page$")
    public void iSelectLearnMoreLinkInWalletpage() {
        Elements.findElement("my_bwallet.goto_faq").click();
        Wait.forPageReady();
    }

    @Then("^I should navigate to 'FAQ' page with url containing 'bwallet-faq' along with base url$")
    public void iShouldNavigateToFQAPage() {
        assertTrue("Learn More link is not navigated to bwallet-faq page.", WebDriverManager.getCurrentUrl().contains("/bwallet-faq"));
    }

    @Then("^I should see the 'SEE SALES & PROMOTIONS PAGE' link under My Offers section$")
    public void iShouldSeeTheSeeSalesPromotionsPageLinkUnderMyoffersSection() {
        assertTrue("Link 'SEE SALES & PROMOTIONS PAGE' is not displayed.", Elements.elementPresent("my_bwallet.see_sales_offers_page"));
    }

    @When("^I select 'SEE SALES & PROMOTIONS PAGE' link in wallet page$")
    public void iSelectSeeSalesPromotionsPageLinkinwalletPage() {
        Elements.findElement("my_bwallet.see_sales_offers_page").click();
        Wait.forPageReady();
    }

    @Then("^I should see added offer in my wallet page with \"JUST ADDED\" flag$")
    public void iShouldSeeAddedofferInMyWalletPageWithJustAddedFlag() {
        assertTrue("'Highlighted test 'JUST ADDED' is not displayed for the newly added offer.", Elements.elementPresent("my_bwallet.offer_just_added"));
    }

    @And("^I added first wallet eligible offer manually on wallet page$")
    public void iAddedWalletEligibleOfferManuallyOnWalletPage() throws Throwable {
        //this is to save promo codes if we are adding multple promotions in one scenario
        walletPromoCodes.addAll(promotionService.getWalletEligiblePromoCodesByCount(2, false));
//        walletPromoCodes.add(promotionService.getWalletEligiblePromoCodesByCount(2).get(1));
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        Wallet.addValidOffer(walletPromoCodes.get(0).get("promoCode"));
    }

    @Then("^I add any other valid offer to wallet$")
    public void iAddAnyOtherValidOfferToWallet() throws Throwable {
//        List<Map<String, String>> walletPromots = promotionService.getWalletEligiblePromoCodesByCount(2);
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        Wallet.addValidOffer(walletPromoCodes.get(1).get("promoCode"));
    }


    @Then("^I should see added offer in my wallet page with \"JUST ADDED\" flag for the newly added offer$")
    public void iShouldSeeAddedOfferInMyWalletPageWithJustAddedFlag() {
        List<WebElement> offerRows = Elements.findElements("my_bwallet.offer_row");
        WebElement justAddedText = null;
        offerRows.get(0).findElement(Elements.element("my_bwallet.view_details_and_exclusions")).click();
        Wait.ajaxDone();
        offerRows.get(1).findElement(Elements.element("my_bwallet.view_details_and_exclusions")).click();
        Wait.ajaxDone();
        for (int index = 0; index < offerRows.size(); index++) {
            //loading the elements again to avoid the stale element exception
            offerRows = Elements.findElements("my_bwallet.offer_row");
            Map<String, String> expectedPromo = walletPromoCodes.get(1);
            String actPromoCode = offerRows.get(index).findElement(Elements.element("my_bwallet.online_codes")).getText();
            if (expectedPromo.get("promoCode").equals(actPromoCode)) {
                justAddedText = offerRows.get(index).findElement(Elements.element("my_bwallet.offer_just_added"));
                break;
            }
        }
        assertTrue("Just Added text is not displayed for the newly added promotion.", justAddedText != null);

    }

    @And("^I should see \"JUST ADDED\" flag is not displayed already added offer before$")
    public void iShouldSeeJustAddedFlagIsNotDisplayedForOfferAddedBefore() {
        List<WebElement> offerRows = Elements.findElements("my_bwallet.offer_row");
        WebElement justAddedText = null;
        boolean isJustAddedText = true;
        for (int index = 0; index < offerRows.size(); index++) {
            //loading the elements again to avoid the stale element exception
            offerRows = Elements.findElements("my_bwallet.offer_row");
            Map<String, String> expectedPromo = walletPromoCodes.get(0);
            String actPromoCode = offerRows.get(index).findElement(Elements.element("my_bwallet.online_codes")).getText();
            //checking the first added promotion code in the list of promo codes to check if it has JUST ADDED text associated to it
            if (expectedPromo.get("promoCode").equals(actPromoCode)) {
                try {
                    isJustAddedText = offerRows.get(index).findElement(Elements.element("my_bwallet.offer_just_added")) != null;

                } catch (Exception e) {

                    isJustAddedText = false;
                }
                break;
            }
        }
        assertFalse("Just Added text is displayed for the already added promotion.", isJustAddedText);
    }

    @Then("^I should see \"JUST ADDED\" flag is not displayed for previously added offer$")
    public void iShouldSeeJustAddedFlagIsNotDisplayedForPreviouslyAddedOffer() {
        boolean isJustAddedText = true;
        try {
            isJustAddedText = Elements.findElement("my_bwallet.offer_just_added")!= null;

        } catch (Exception e) {

            isJustAddedText = false;
        }
        assertFalse("Just Added text is displayed for the already added promotion.", isJustAddedText);
    }

    @Then("I should see the \"My bWallet\" navigation title on wallet page")
    public void iShouldSeeTheMyBWalletNavigationTitleOnWalletpage () throws Throwable {
        String title = WebDriverManager.getWebDriver().getTitle();
        assertTrue("Navigation title 'My bWallet' is not displayed for My bWallet page", title.contains("My bWallet"));
    }

    @Then("^I should see the MY bWALLET header at the top of the Wallet page$")
    public void iShouldSeeTheMyBWalletheaderAtTheTopOfTheWalletpage() {
        assertTrue("Navigation title 'My bWallet' is not displayed for My bWallet page", Elements.elementPresent("my_bwallet.my_bwallet_header"));
    }

    @Then("^I should see the welcome message displays under the My bWallet header$")
    public void iShouldSeeTheWelcomeMessageDisplayedUnderTheMyBWalletHeader() {
        assertTrue("Welcom message is not displayed under My bWallet header",Elements.getText("my_bwallet.welcome_message").equals("WELCOME, "+TestUsers.getCustomer(null).getUser().getProfileAddress().getFirstName()+" "+TestUsers.getCustomer(null).getUser().getProfileAddress().getLastName()+"."));
    }

    @And("^I click on 'sign in' link from widget offer section in the shopping bag$")
    public void iClickOnSignInLinkFromWidgetOfferSectionInTheShoppingBag() throws Throwable {

        Wait.forPageReady();
        Wait.untilElementPresent("shopping_bag.sign_in_link");
        if (!Elements.elementPresent("shopping_bag.sign_in_link")) {
            Assert.fail("Sign In link could not be found");
        }
        Clicks.click("shopping_bag.sign_in_link");

    }

    @Then("^I should see my account Page$")
    public void iShouldSeeMyAccountPage() throws Throwable {
        Wait.forPageReady("my_account");
        Assert.assertTrue("My Account page could not be validated",
                onPage("my_account"));
    }

    @And("^I ensure to be on \"shopping bag\" page$")
    public void iEnsureToBeOnShoppingBagPage() {
        if(!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
    }
    @And("^I checkout to reach the \"([^\"]*)\" page as a \"([^\"]*)\" user$")
    public void iCheckoutUntilIReachThePageAsAUser(String pageName, String userType) throws Throwable {
        String country = "United States";
        userType = userType.toLowerCase();
        boolean bops = userType.contains("bops");
        boolean signIn = userType.contains("signed in") || signedIn();
        HashMap<String, String> opts = new HashMap<>();

        boolean iship = userType.contains("iship") || !country.equalsIgnoreCase("United States");
        opts.put("country", country);
        opts.put("checkout_eligible", "true");

        if (!(onPage("responsive_checkout", "checkout"))) {
            new CheckoutUtils().navigateToCheckout(signIn, iship);
            Thread.sleep(3000);
        }

        // checkout can take some time, page hang watchdog can safely be paused
        pausePageHangWatchDog();
        Checkout checkout = new Checkout(opts, bops);
        if (iship) {
            checkout.ishipCheckout(pageName);
        } else {
            CheckoutUtils.RCPage page = CheckoutUtils.RCPage.fromString(pageName);
            if (signIn) {
                checkout.rcSignedIn(page, opts);
            } else {
                checkout.rcGuest(page, opts);
            }
        }
        resumePageHangWatchDog();
    }

    @Then("^I should see reward cards in payment option section$")
    public void iShouldSeeRewardCardsInPaymentOptionSection() throws Throwable {

        Wait.forPageReady("responsive_checkout_signed_in");
        Clicks.click("responsive_checkout_signed_in.expand_reward_card_section");
        Wait.ajaxDone();
        Thread.sleep(1000);
        Assert.assertTrue("Apply Loyalist reward_card button not found",
                Elements.elementPresent("responsive_checkout_signed_in.apply_reward_card"));
        Assert.assertTrue("Loyalist reward detials not found",
                Elements.elementPresent("responsive_checkout_signed_in.loayllist_reward_card_details"));

        List<WebElement> elems = Elements.findElements("responsive_checkout_signed_in.loayllist_reward_card_details");
        assertTrue("Reward Card header is not displayed correctly.", elems.get(0).getText().equals("Loyallist Reward Card"));
        assertTrue("Reward Card masked number is not displayed correctly.", elems.get(1).getText().contains("****"));
        assertTrue("Reward Card amount is not displayed correctly.", elems.get(2).getText().equals("Value: $25.00"));
        assertTrue("Reward Card Exp Date info is not displayed correctly.", elems.get(3).getText().contains("Expires: "));

    }

    @And("^I capture the reward cards from wallet page$")
    public void iCaptureTheRewardCardsFromWalletPage() throws Throwable {

        Wait.forPageReady("my_bwallet");
        OCWalletSteps.cardNumWallet = Elements.findElement("my_bwallet.loyallist_reward_card_num").getText();
    }

    @Then("^I should see the reward cards stored in wallet are displayed in reward cards section$")
    public void iShouldSeeTheRewardCardsStoredInWalletAreDisplayedInRewardCardsSection() throws Throwable {
        Clicks.click("responsive_checkout_signed_in.expand_reward_card_section");
        Wait.ajaxDone();
        Thread.sleep(1000);
        Wait.forPageReady("responsive_checkout_signed_in");
        List<WebElement> elems = Elements.findElements("responsive_checkout_signed_in.loayllist_reward_card_details");
        OCWalletSteps.cardNumCheckout = elems.get(1).getText();
        String expirayDate = elems.get(3).getText();
        Assert.assertTrue("Reward card number in checkout do not match card num in Wallet",
                OCWalletSteps.cardNumWallet.contains(OCWalletSteps.cardNumCheckout));

    }

    @When("^I select a reward card$")
    public void iSelectARewardCard() throws Throwable {
        Thread.sleep(1000);
        Wait.forPageReady("responsive_checkout_signed_in");
        Clicks.click("responsive_checkout_signed_in.apply_reward_card");
        Wait.forPageReady();
        Thread.sleep(3000);
    }

    @Then("^I should see the reward card amount is deducted from order total$")
    public void iShouldSeeTheRewardCardAmountIsDeductedFromOrderTotal() throws Throwable {

        String subTotal = Elements.findElement("responsive_checkout_signed_in.order_subtotal")
                .getText();
        String tax = Elements.findElement("responsive_checkout_signed_in.tax_total").getText();
        String rewardCard = Elements.findElement("responsive_checkout_signed_in.reward_card_total").getText();
        String orderTotal = Elements.findElement("responsive_checkout_signed_in.order_total").getText();
        assertTrue("Reward Card Amount is not applied to the order total.", rewardCard.equals("-$25.00"));
        Double appliedTotal = Double.parseDouble(subTotal.replace("$",""))+Double.parseDouble(tax.replace("$",""))+Double.parseDouble(rewardCard.replace("$",""));
        DecimalFormat df = new DecimalFormat("#.##");
        appliedTotal = Double.parseDouble(df.format(appliedTotal));
        assertTrue("Order Total is not updated after applying the Reward Card Amount.", appliedTotal == Double.parseDouble(orderTotal.replace("$","")));
       // Assert.assertTrue("Reward card amount is not deducted from order total",);
    }

   /* @And("^I can associate my account by loyallist number using \"([^\"]*)\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsing(String type) throws Throwable {

        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();

        switch (type) {

            case "rewards":
                loyallistDetails = LoyallistAssociation.getLoyallistDetails("reward");
                break;
            case "less_than_2500_points":
                loyallistDetails = LoyallistAssociation.getLoyallistDetails("less_than_2500_points");
                break;
        }
        associateLoyallistID(loyallistDetails);
    }*/

    public void associateLoyallistID(LoyalistDetails loyallistDetails) throws Throwable {
        LoyallistAssociation.loyaltyAssociation(loyallistDetails);
        Wait.untilElementPresent("loyallist_account_summary.verify_page");
        shouldBeOnPage("loyallist_account_summary");
    }

    @Then("^I should not see Reward cards header and message on shopping bag page$")
    public void iShouldNotSeeRewardCardsHeaderAndMessageOnShoppingBagPage() throws Throwable {

        if(!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        Wait.forPageReady("shopping_bag");
        assertFalse("Reward card header found in shopping bag page",
                Elements.elementPresent("shopping_bag.reward_card_header"));
        assertFalse("Reward card message found in shopping bag page",
                Elements.elementPresent("shopping_bag.reward_card_message"));
    }
}

