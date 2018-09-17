package com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.LoyaltyServiceUtil;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.WalletService;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyallistAssociation;
import com.macys.sdt.shared.actions.website.bcom.pages.my_account.MyAccount;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAddressBook;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CheckoutUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OCWallet extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    private static ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
    private static final Logger logger = LoggerFactory.getLogger(OCWallet.class);
    private static PromotionService promotionService = new PromotionService();
    public static String promoCode;
    public static List<Map<String, String>> promoCodes;
    private ArrayList<CreditCard> cardsAdded = new ArrayList<>();
    private ProfileAddress profileAddress;
    public static int totalOffers;
    private LoyaltyServiceUtil loyaltyService = new LoyaltyServiceUtil();
    LoyalistDetails loyallistDetails = new LoyalistDetails();
    double rewardCardBalance = 0;
    String remainingPointnsToNextCard;

    @Given("^I visit the web site as a registered user with no stored credit cards$")
    public void iVisitTheWebSiteAsARegisteredUserWithNoStoredCreditCards() throws Throwable {

        //updated to create new profiles every time to make sure preconditions
       // pageNavigation.I_navigate_to_create_profile_page();
       // myAccountSteps.iCreateANewProfile();
        CreateProfile.createProfileUsingServiceAndLogin();
    }


    @And("^I should see the added cards in the list of my credit or debit cards$")
    public void i_Should_See_Added_Cards_In_List_Of_Cards() throws Throwable {

        Wait.untilElementPresent("wallet.credit_cards_section");
        ArrayList<CreditCard> cards = Wallet.getListOfAddedCardsInWallet();
        Wallet.verifyWalletCardsMatchAddedCards(cards, cardsAdded);
    }


    @And("^I should see the added cards information is saved to database$")
    public void iShouldSeeTheAddedCardsInformationIsSavedToDatabase() throws Throwable {

        WalletService ws = new WalletService();
        ArrayList<CreditCard> DBCards = ws.getCardDetailsFromDB();
        Wallet.verifyWalletWithDBCardDetails(DBCards, cardsAdded);
    }

    @And("^I add a credit card with all possible card_type to my wallet$")
    public void iAddACreditCardWithAllPossibleCard_typeToMyWallet(DataTable cardTypes) throws Throwable {

        for (Map<String, String> row : cardTypes.asMaps(String.class, String.class)) {
            CreditCard creditCard = Wallet.getValidCreditCard(row.get("card_type"));
            boolean isDefault = false;

            if (creditCard.getCardType().name.equals("American Express")) isDefault = true;

            addCard(creditCard);
            Thread.sleep(2000);
            cardsAdded.add(creditCard);
        }

    }

    @And("^I add a Visa credit card to my wallet with same card type and card number as existing saved card$")
    public void iAddAVisaSameCreditCardTypeAndNumberToMyWallet() throws Throwable {
        CreditCard creditCard = Wallet.getValidCreditCard(cardsAdded.get(0).getCardType().name);
        ArrayList<CreditCard> createdCards = null;
        ProfileAddress address = TestUsers.getCustomer(null).getUser().getProfileAddress();
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        logger.info("Number of Credit Cards in Wallet before update: " + walletCards.size());
        address.setAddressLine1("681 Falsom st");
        addCard(creditCard, address);
        logger.info("Added Credit Card with same Number and Card Type with updated address");
    }

    @Then("^I should see new credit card is not added to wallet$")
    public static void iShoulSeeNewCreditCardNotAddedToWallet() throws Throwable {

        Wait.forPageReady();
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        logger.info("Number of Credit Cards in Wallet before update " + walletCards.size());
        assertTrue("New Credit Card is added when same Card Number and Card Type is Added", walletCards.size() == 1);
    }

    @And("^I should see existing saved credit card details with same card type and card number are updated$")
    public void iShouldSeeExistingSavedCreditCardDetailsWithSameCardTypeAndNumber() throws Throwable {
        CreditCard expectedCreditCard = cardsAdded.get(0);
        ArrayList<CreditCard> actualcreditCards = Wallet.getListOfAddedCardsInWallet();
        String expCardType = expectedCreditCard.getCardType().name;
        String actCardType = actualcreditCards.get(0).getCardType().name;
        String expCardNumber = expectedCreditCard.getCardNumber();
        String expCardNumberPart = expCardNumber.substring(expCardNumber.length() - 4, expCardNumber.length());
        String actCardNumberPart = actualcreditCards.get(0).getCardNumber();

        assertTrue("Saved Credit Card does not have Same Card Type. Expected Card Type: " + expCardType +
                " Actual Card Type: " + actCardType, expCardType.equalsIgnoreCase(actCardType));
        assertTrue("Saved Credit Card does not have Same Card Number." + " Expected Card Number: " + expCardNumber
                + " Actual Card Number: " + actCardNumberPart, expCardNumberPart.equalsIgnoreCase(actCardNumberPart));


    }

    @Then("^I should see expiration date fields are disabled for the following prop card type in add credit card overlay:$")
    public void iShouldSeeExpirationDateFieldsAreDisabledForPropCardTypeCard(DataTable table) throws Throwable {
        String cardType = null;
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            CreditCard creditCard = Wallet.getValidCreditCard(row.get("card_type"));

            String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
            Wait.untilElementPresent(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            if (!Elements.elementPresent(pageName + ".credit_card_type")) {
                Clicks.click(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            }
            Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
            cardType = creditCard.getCardType().name;
            cardType = cardType.equalsIgnoreCase("Bloomingdale's") ? "Bloomingdale's Card" : cardType;
            cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express") ? "Bloomingdale's American Express® Card" : cardType;
            if (bloomingdales()) {
                DropDowns.selectCustomText("credit_card_dialog.card_type_list", "credit_card_dialog.card_type_options", cardType);
            } else {
                DropDowns.selectByText("credit_card_dialog.card_type", cardType);
            }

            Wallet.verifyExpirationFields(creditCard);
        }

    }

    @Given("^I visit the web site as a registered user with (\\d+) stored credit cards$")
    public void iVisitTheWebSiteAsARegisteredUserWithStoredCreditCards(int noOfCards) throws Throwable {

      //  pageNavigation.I_navigate_to_create_profile_page();
      //  myAccountSteps.iCreateANewProfile();
        // pageNavigation.I_visit_the_web_site_as_a_registered_user();

        CreateProfile.createProfileUsingServiceAndLogin();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        ArrayList<CreditCard> cards = Wallet.getValidCreditCards(noOfCards);
        for (CreditCard card : cards) {
            addCard(card);
            cardsAdded.add(card);
        }
    }

    @Then("^I should see add a new card button is disabled$")
    public void iShouldSeeAddANewCardButtonIsDisabled() throws Throwable {

        assertTrue("Add Credit card button is still enabled",
                !Elements.findElement(macys() ? "oc_my_wallet.add_credit_card" : "my_bwallet.add_credit_card_btn").isEnabled());

    }

    @Given("^I visit the web site as a registered user with no saved addresses$")
    public void iVisitTheWebSiteAsARegisteredUserWithNoSavedAddresses() throws Throwable {

        pageNavigation.I_visit_the_web_site_as_a_guest_user();
       // myAccountSteps.iCreateANewProfile();
        CreateProfile.createProfileUsingServiceAndLogin();
        // make sure there are no shipping addresses present
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        if (MyAddressBook.isAddressAdded()) {
            for (WebElement deleteAddress : Elements.findElements("my_address_book.remove_address_list")) {
                Clicks.click(deleteAddress);
                Wait.forPageReady();
            }
        }
        new MyAccount().navigateToLeftNavigationPage("my wallet");
        Wait.forPageReady();
    }

    @Then("^I should not see use my shipping address field in add card overlay$")
    public void iShouldNotSeeUseMyShippingAddressFieldInAddCardOverlay() throws Throwable {
        verifyShippingAddressOnAddCreditCardDialog(false);

    }

    @Then("I should not see use my shipping address field in edit card overlay$")
    public void iShouldNotSeeUseMyShippingEditressFieldInEditCardOverlay() throws Throwable {
        CreditCard creditCard = Wallet.getValidCreditCard("Visa");
        addCard(creditCard);
        cardsAdded.add(creditCard);
        verifyShippingAddressOnEditCreditCardDialog(false);

    }

    public void verifyShippingAddressOnAddCreditCardDialog(boolean bExpectedStatus) throws Throwable {

        openAddCard();

        if (bExpectedStatus) {
            assertTrue("Use my Shipping Address Checkbox is not present", Elements.elementPresent(macys() ? "credit_card_dialog.use_my_shipping_address" : "credit_card_dialog.use_shipping_address"));
        } else {
            assertFalse("Use my Shipping Address Checkbox is present", Elements.elementPresent(macys() ? "credit_card_dialog.use_my_shipping_address" : "credit_card_dialog.use_shipping_address"));
        }

    }

    public void verifyShippingAddressOnEditCreditCardDialog(boolean bExpectedStatus) throws Throwable {

        openEditCard(cardsAdded.get(0));

        if (bExpectedStatus) {
            assertTrue("Use my Shipping Address Checkbox is not present", Elements.elementPresent(macys() ? "credit_card_dialog.use_my_shipping_address" : "credit_card_dialog.use_shipping_address"));
        } else {
            assertFalse("Use my Shipping Address Checkbox is present", Elements.elementPresent(macys() ? "credit_card_dialog.use_my_shipping_address" : "credit_card_dialog.use_shipping_address"));
        }

    }


    @And("^I add a credit card with following card type to my wallet:$")
    public void iAddACreditCardWithFollowingCardTypeToMyWallet(DataTable table) throws Throwable {
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            CreditCard creditCard = Wallet.getValidCreditCard(row.get("card_type"));

            addCard(creditCard);
            cardsAdded.add(creditCard);
        }
    }

    @Then("^I should see \"([^\"]*)\" on top of the wallet page$")
    public void iShouldSeeOnTopOfTheWalletPage(String text) throws Throwable {

        try {
            System.out.println("Confirmation Message displayed is: " + Elements.findElement("wallet.changes_saved_text").getText());
            if (Wait.secondsUntilElementPresent("wallet.changes_saved_text", 30))
                assertTrue("Expected confirmation Message: " + text + " is not displayed",
                        Elements.findElement("wallet.changes_saved_text").getText().equals(text));

        } catch (NoSuchElementException e) {
            Assert.fail("Changes saved to your wallet. element could not be found");
        }
    }

    /*@And("^I add an offer to my wallet$")
    public void iAddAnOfferToMyWallet() throws Throwable {

        myAccountSteps.iClickOnAddOfferOnWalletPage();
        shopAndBrowse.I_add_a_valid_offer();

    }*/

    @Given("^I visit the web site as a registered user with (\\d+) stored credit card$")
    public void iVisitTheWebSiteAsARegisteredUserWithStoredCreditCard(int noOfCards) throws Throwable {
//        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        pageNavigation.I_visit_the_web_site_as_a_guest_user();
        myAccountSteps.iCreateANewProfile();

        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();

        ArrayList<CreditCard> cards = Wallet.getValidCreditCards(noOfCards);
        for (CreditCard card : cards) {
            addCard(card);
            cardsAdded.add(card);
        }

    }

    @Then("^I can \"([^\"]*)\" out of \"([^\"]*)\" a credit card on My Wallet page$")
    public void i_can_out_of_a_credit_card_on_My_Wallet_page(String closeAction, String openAction) throws Throwable {
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        if (openAction.equalsIgnoreCase("adding")) {
            Navigate.browserRefresh();
            Utils.threadSleep(4000, null);
            Wait.forPageReady();
            //  Wait.untilElementPresent(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
            Clicks.click(pageName + (macys() ? ".add_credit_card" : ".add_credit_card_btn"));
        } else if (openAction.equalsIgnoreCase("editing")) {
            if (!Elements.elementPresent(pageName + ".edit_credit_cards")) {
                CreditCard creditCard = Wallet.getValidCreditCard("Visa");
                addCard(creditCard);
                cardsAdded.add(creditCard);
            }
            Wait.untilElementPresent(pageName + ".edit_credit_cards");
            Clicks.click(pageName + ".edit_credit_cards");
        }

        Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
        Clicks.click(Elements.element("credit_card_dialog." + closeAction));
        Wait.forPageReady();
        assertTrue("Credit Card Dialog is not closed after clicking on " + closeAction + " button.",
                !Elements.elementPresent("oc_my_wallet.credit_card_overlay"));
    }

    @Then("^I should see the masking functionality for credit card number on add credit card overlay$")
    public void iShouldSeeTheMaskingFunctionalityForCreditCardNumberOnAddCreditCardOverlay() throws Throwable {
        String actMaskedCardNumber, expCardNumber, expCardNumberPart, expMaskedCardNumber, actCardNumber;
        String cardNumber = "12345678901234";
        String maskedCardNumaberOnOverlay = "**********1234";
        String FirstName = "First Name";
        CreditCard expectedCreditCard = Wallet.getValidCreditCard("Visa");

        if (!Elements.elementPresent(macys() ? "wallet.credit_cards_section" : "my_bwallet.credit_card_addition_verification")) {
            addCard(expectedCreditCard);
            cardsAdded.add(expectedCreditCard);

        }
        Thread.sleep(3000);
        expCardNumber = expectedCreditCard != null ? expectedCreditCard.getCardNumber() : null;
        expCardNumberPart = expCardNumber.substring(expCardNumber.length() - 4, expCardNumber.length());
        expMaskedCardNumber = "************" + expCardNumberPart;
        Navigate.browserRefresh();
        Utils.threadSleep(4000, null);
        Wait.forPageReady();
        if (macys()) {
            actCardNumber = Elements.findElement("oc_my_wallet.credit_card_logos").getText();
            actMaskedCardNumber = actCardNumber.substring(actCardNumber.indexOf("*"), 21);
        } else {
            actMaskedCardNumber = Elements.findElement("my_bwallet.credit_card_numbers").getText();
        }

        assertTrue("Credit Card Number is not masked on Wallet Page. Credit Card Number displayed on Wallet Page is: "
                + actMaskedCardNumber, expMaskedCardNumber.equalsIgnoreCase(actMaskedCardNumber));
        openAddCard();
        TextBoxes.typeTextbox("credit_card_dialog.card_number", cardNumber);
        TextBoxes.typeTextbox("credit_card_dialog.first_name", FirstName);
        assertTrue("Credit Card Number displayed is not displayed in masked format on Add Credit Card Overlay",
                Elements.findElement("credit_card_dialog.card_number").getAttribute("value").equalsIgnoreCase(maskedCardNumaberOnOverlay));

    }

    @Then("^I should see the masking functionality for credit card number on edit credit card overlay$")
    public void iShouldSeeTheMaskingFunctionalityForCreditCardNumberOnEditCreditCardOverlay() throws Throwable {
        String actMaskedCardNumberOnWallet, expCardNumber, expCardNumberPart, expMaskedCardNumber, actCardNumber;
        CreditCard expectedCreditCard = cardsAdded.get(0);
        ArrayList<CreditCard> actualcreditCards = Wallet.getListOfAddedCardsInWallet();
        expCardNumber = expectedCreditCard.getCardNumber();
        expCardNumberPart = expCardNumber.substring(expCardNumber.length() - 4, expCardNumber.length());
        expMaskedCardNumber = "************" + expCardNumberPart;
        if (macys()) {
            actCardNumber = Elements.findElement("oc_my_wallet.credit_card_logos").getText();
            actMaskedCardNumberOnWallet = actCardNumber.substring(actCardNumber.indexOf("*"), actCardNumber.indexOf("*") + 16);
        } else {
            actMaskedCardNumberOnWallet = Elements.findElement("my_bwallet.credit_card_numbers").getText();
        }

        assertTrue("Credit Card Number is not masked on Wallet Page. Credit Card Number displayed on Wallet Page is: "
                + actMaskedCardNumberOnWallet, expMaskedCardNumber.equalsIgnoreCase(actMaskedCardNumberOnWallet));
        openEditCard(actualcreditCards.get(0));
        assertTrue("Credit Card Number displayed is not displayed in masked format on Edit Credit Card Overlay",
                Elements.findElement("credit_card_dialog.card_number").getAttribute("value").equalsIgnoreCase(expMaskedCardNumber));

    }

    @Then("^I should see the masking functionality for credit card number when error message after saving card with invalid card on add credit card overlay$")
    public static void iShouldSeeTheMaskingFunctionalityForCardNumberWhenErrorMessageDisplayedOnAddCreditCardDialog() throws Throwable {
        String cardType = "Visa";
        String cardNumber = "12345678901234";
        String maskedCardNumaber = "**********1234";
        String FirstName = "First Name";
        openAddCard();
        if (bloomingdales()) {

            DropDowns.selectCustomText("credit_card_dialog.card_type_list", "credit_card_dialog.card_type_options", cardType);
        } else {
            DropDowns.selectByText("credit_card_dialog.card_type", cardType);
        }
        TextBoxes.typeTextbox("credit_card_dialog.card_number", cardNumber);
        TextBoxes.typeTextbox("credit_card_dialog.first_name", FirstName);
        Clicks.click("credit_card_dialog.save_card");
        Wait.untilElementPresent("oc_my_wallet.credit_card_overlay");
        assertTrue("Credit Card Number displayed is not displayed in masked format on Add Credit Card Overlay",
                Elements.findElement("credit_card_dialog.card_number").getAttribute("value").equalsIgnoreCase(maskedCardNumaber));
    }

    @Then("^I should see the masking functionality for credit card number when error message after saving card with invalid card$")
    public void iShouldSeeTheMaskingFunctionalityForCardNumberWhenErrorMessageDisplayedOnEditCreditCardDialog() throws Throwable {
        String expMaskedCardNumaber = "************" + cardsAdded.get(0).getCardNumber().substring(12, 16);
        String actMaskedAcctNumber;
        String FirstName = " ";
        openEditCard(cardsAdded.get(0));

        TextBoxes.typeTextbox("credit_card_dialog.first_name", FirstName);
        Clicks.click("credit_card_dialog.save_card");
        Wait.forPageReady();
        actMaskedAcctNumber = Elements.findElement("credit_card_dialog.card_number").getAttribute("value");
        assertTrue("Credit Card Number displayed is not displayed in masked format on Add Credit Card Overlay. " +
                "Acutal Account Number displayed: " + actMaskedAcctNumber, actMaskedAcctNumber.equalsIgnoreCase(expMaskedCardNumaber));
    }

    @Then("^I should see credit card number is masked when focused in credit card number field$")
    public void iShouldSeeCreditCardNumberIsMaskedWhenFocusedInCreditCardNumberField() throws Throwable {
        String expMaskedCardNumaber = "************" + cardsAdded.get(0).getCardNumber().substring(12, 16);
        String actMaskedAcctNumber;
        openEditCard(cardsAdded.get(0));
        Clicks.click("credit_card_dialog.card_number");
        actMaskedAcctNumber = Elements.findElement("credit_card_dialog.card_number").getAttribute("value");
        assertTrue("Credit Card Number displayed is not displayed in masked format on Add Credit Card Overlay." +
                "Acutal Account Number displayed: " + actMaskedAcctNumber, actMaskedAcctNumber.equalsIgnoreCase(expMaskedCardNumaber));


    }

    @And("^I added wallet eligible offer manually on wallet page$")
    public void iAddedWalletEligibleOfferManuallyOnWalletPage() throws Throwable {

        promoCode = promotionService.getWalletEligiblePromoCode();
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        Wallet.addValidOffer(promoCode);
    }

    @Then("^I should see offer is added to wallet$")
    public void iShouldSeeOfferIsAddedToWallet() throws Throwable {

        Wait.forPageReady();
        boolean promoCodeFound = false;
        if (!onPage("oc_my_wallet")) {
            myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        }
        Wait.forPageReady("oc_my_wallet");
        List<WebElement> elems = Elements.findElements("wallet.promo_code");
        for (WebElement elem : elems) {
            if (elem.getText().equals(promoCode)) {
                promoCodeFound = true;
                break;
            }
        }
        // String walletPromoCode = Elements.findElement("wallet.promo_code").getText();
        assertTrue("Promo code could not be found", promoCodeFound);
    }

    @And("^I pick a promo code from deals and promotion page$")
    public void iPickAPromoCodeFromDealsAndPromotionPage() throws Throwable {

        // Clicks.click("wallet.deals_promotions_sub_header");
        Clicks.click("home.deals_header");
        Wait.forPageReady("deals_and_promotions");
        List<WebElement> addToWalletPromos = Elements.findElements("wallet.add_to_wallet_promos");
        if (addToWalletPromos.isEmpty()) {
            logger.info("No wallet eligible promo code found in D & P page");
        }
        promoCode = addToWalletPromos.get(0).getAttribute("id").substring(7);
        if (promoCode.equals("BLOOM")) {
            promoCode = addToWalletPromos.get(1).getAttribute("id").substring(7);
        }
    }

    @And("^I add wallet eligible offer manually on wallet page$")
    public void iAddWalletEligibleOfferManuallyOnWalletPage() throws Throwable {
        Wait.forPageReady();
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        Wallet.addValidOffer(promoCode);
        Wait.forPageReady();
    }

    @Given("^I visit the web site as a registered user with manually (\\d+) stored offers in wallet$")
    public void iVisitTheWebSiteAsARegisteredUserWithManuallyStoredOffersInWallet(int numOffers) throws Throwable {

      //  pageNavigation.I_navigate_to_create_profile_page();
      //  myAccountSteps.iCreateANewProfile();
        CreateProfile.createProfileUsingServiceAndLogin();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        new Wallet().AddValidOfferInWallet(numOffers);
        totalOffers = numOffers;

    }

    @Then("^I should see \"([^\"]*)\" redeemed offer message at the bottom of page$")
    public void iShouldSeeRedeemedOfferMessageAtTheBottomOfPage(String message) throws Throwable {

        String messagefromPage = Elements.findElement("wallet.redeemed_expired_offer_message").getText();
        assertTrue("Promo code could not be found", messagefromPage.equals(message));
    }

    @And("^I update credit card details$")
    public void iUpdateCreditCardDetails() throws Throwable {
        String addressLine1 = "680 Folsom st";
        profileAddress = TestUsers.getCustomer(null).getUser().getProfileAddress();
        profileAddress.setAddressLine1(addressLine1);
        CreditCard creditCard = cardsAdded.get(0);
        updateCardAddress(creditCard, profileAddress);
    }

    @Then("^I should see updated credit card details on my wallet page$")
    public void iShouldSeeUpdatedCardDetails() throws Throwable {
        String expectedAddressLine1 = profileAddress.getAddressLine1();
        CreditCard creditCard = cardsAdded.get(0);
        openEditCard(creditCard);
        assertTrue("Credit Card has not reflected updates made to it.", Elements.findElement("credit_card_dialog.address_line_1").
                getAttribute("value").equalsIgnoreCase(expectedAddressLine1));
        Clicks.click("credit_card_dialog.close");
    }

    @And("^I should see card information is updated in the database$")
    public void iShouldSeeCardInformationIsUpdatedInDB() throws Throwable {
        WalletService ws = new WalletService();
        ArrayList<CreditCard> dataBaseCards = ws.getCardDetailsFromDB();
        Wallet.verifyWalletWithDBCardDetails(dataBaseCards, cardsAdded);
    }

    @And("^I update credit card details with same card type and card number as another saved card$")
    public void iUpdateCreditCardDetailsWithSameCardTypeAndNumberAsAnotherSavedCard() throws Throwable {
        CreditCard cardToBeUpdated = cardsAdded.get(1);
        CreditCard cardReplacedWith = cardsAdded.get(0);
        updateCardDetails(cardToBeUpdated, cardReplacedWith);
    }

    @Then("^I should see the credit card being updated is deleted$")
    public void iShouldSeeTheCreditCardBeingUpdatedIsDeleted() throws Throwable {
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        assertTrue("Two Credit Cards displayed even after updating the First card type "
                + cardsAdded.get(1).getCardType().name + " and Card Number with Second Card Type " +
                cardsAdded.get(0).getCardType().name, walletCards.size() == 1);
        assertTrue("Credit card being updated: '" + cardsAdded.get(1).getCardType().name + "' is not deleted.",
                !walletCards.get(0).getCardType().name.equalsIgnoreCase(cardsAdded.get(1).getCardType().name));
        cardsAdded.remove(1);
    }

    @Then("^I should see set as default option is checked and disabled on add credit card overlay$")
    public void iShouldSeeAsDefaultOptionIsCheckedAndDisabledOnAddCreditCardOverlay() throws Throwable {
        openAddCard();
        assertTrue("'Set as Default' checkbox is not disabled on Add a Debit/Credit Card Overlay",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute(macys() ? "disabled" : "aria-disabled") != null);
        assertTrue("'Set as Default' checkbox is not checked on Add a Debit/Credit Card Overlay",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute("checked") != null);
        Clicks.click("credit_card_dialog.close");
    }

    @When("^I add a credit or debit card with following card type to my wallet:$")
    public void iAddADebitOrCreditCardWithFollowingCardTypeToMyWallet(DataTable cardTypes) throws Throwable {
        for (Map<String, String> row : cardTypes.asMaps(String.class, String.class)) {
            CreditCard creditCard = Wallet.getValidCreditCard(row.get("card_type"));

            addCard(creditCard);
            cardsAdded.add(creditCard);
        }
    }

    @Then("^I should see the added credit card is saved as default card$")
    public void iShouldSeeTheAddedCreditCardIsSavedAsDefaultCard() throws Throwable {
        if (macys()) {
            assertTrue("Credit card is not marked as Default on Add Wallet Page",
                    Elements.findElement(Elements.element("oc_my_wallet.card_default_indicator")).getAttribute("class").equalsIgnoreCase("default-card"));
        }
        openEditCard(cardsAdded.get(0));
        assertTrue("'Set as Default' checkbox is not disabled on Add a Debit/Credit Card Overlay",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute(macys() ? "disabled" : "aria-disabled") != null);
        assertTrue("'Set as Default' checkbox is not checked on Add a Debit/Credit Card Overlay",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute("checked") != null);
    }

    @Then("^I should see set as default option is unchecked on add credit card overlay$")
    public void iShouldSeeSetAsDefaultOptionIsUncheckedOnAddCreditCardOverlay() throws Throwable {
        openAddCard();
        assertTrue("'Set as Default' checkbox is disabled on Add a Debit/Credit Card Overlay while adding a second card",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute(macys() ? "disabled" : "aria-disabled") == null);
        assertTrue("'Set as Default' checkbox is checked on Add a Debit/Credit Card Overlay while adding a second card",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute("checked") == null);
        Clicks.click("credit_card_dialog.close");
    }

    @And("^I select set as default option and save in edit credit card overlay$")
    public void iSelectSetAsDefaultOptionAndSaveInEditCreditCardOverlay() throws Throwable {
        CreditCard cardToMakeDefault = cardsAdded.get(1);
        //editing the second credit card which is not a default card and making it default
        openEditCard(cardToMakeDefault);
        Thread.sleep(2000);
        Elements.findElement("credit_card_dialog.set_as_a_default_card").click();
        Clicks.click("credit_card_dialog.save_card");
    }

    @Then("^I should see the saved default card at the top of the list of credit cards$")
    public void iShouldSeeTheSavedDefaultCardAtTheTopOfTheListOfCreditCards() throws Throwable {
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        assertTrue("Card newly made default is not moved to the Top of the Wallet Cards List",
                cardsAdded.get(0).getCardType().name.equalsIgnoreCase(walletCards.get(1).getCardType().name));
    }

    @Then("^I should see set as default option is checked and disabled on edit credit card overlay for default card$")
    public void iShouldSeeSetAsDefaultOptionIsCheckedAndDisabledOnEditCreditCardOverlayForDefaultCard() throws Throwable {
        iShouldSeeTheAddedCreditCardIsSavedAsDefaultCard();
    }

    @And("^I add a credit card to my wallet by selecting set as default option$")
    public void iAddACreditCardToMyWalletBySelectingSetAsDefaultOption() throws Throwable {
        CreditCard creditCard = Wallet.getValidCreditCard("MasterCard");
        addCard(creditCard, true);
        cardsAdded.add(creditCard);
    }

    @Then("^I should see the newly added credit card is saved as default card$")
    public void iShouldSeeTheNewlyAddedCreditCardIsSavedAsDefaultCard() throws Throwable {
        openEditCard(cardsAdded.get(cardsAdded.size() - 1));
        assertTrue("'Set as Default' checkbox is not disabled on Add a Debit/Credit Card Overlay",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute(macys() ? "disabled" : "aria-disabled") != null);
        assertTrue("'Set as Default' checkbox is not checked on Add a Debit/Credit Card Overlay",
                Elements.findElement("credit_card_dialog.set_as_default").getAttribute("checked") != null);
    }

    @Then("^I should see a tool tip with \"edit or delete your card\" message upon hovering the pencil icon for any store credit card$")
    public void iShouldSeeToolTipUponHoverThePencilIconForAnyStoredCreditCard() throws Throwable {
        if (macys()) {
            Clicks.hover("oc_my_wallet.edit_credit_cards");
            assertTrue("Tooltip 'edit or delete your card' has not been displayed for pencil icon",
                    Elements.findElement("oc_my_wallet.edit_credit_cards").getAttribute("title").equals("edit or delete your card"));
        }
    }

    @Then("^I should see nothing is displyed after entering any alpha character in credit card number field on \"([^\"]*)\" credit card overlay$")
    public void iShouldseeNothingIsDisplayedAfterEnteringAnyAlphaCharactersInCreditCardNumberField(String addOrEdit) throws Throwable {
        if (addOrEdit.equalsIgnoreCase("Add")) {
            openAddCard();
            TextBoxes.typeTextbox("credit_card_dialog.card_number", "~!@#$%^&*()|}{<>?:\\//");
            Wait.forPageReady();
            assertTrue("Alpha Numeric characters are dispalyed on 'Credit Card Number' text field.",
                    Elements.findElement("credit_card_dialog.card_number").getAttribute("value").equalsIgnoreCase(""));
            Clicks.click("credit_card_dialog.close");
        } else if (addOrEdit.equalsIgnoreCase("Edit")) {
            openEditCard(cardsAdded.get(0));
            TextBoxes.typeTextbox("credit_card_dialog.card_number", "");
            TextBoxes.typeTextbox("credit_card_dialog.card_number", "~!@#$%^&*()|}{<>?:\\//");
            assertTrue("Alpha Numeric characters are dispalyed on 'Credit Card Number' text field.",
                    Elements.findElement("credit_card_dialog.card_number").getAttribute("value").equalsIgnoreCase(""));
            Clicks.click("credit_card_dialog.close");
        }

    }

    @Then("^I should see delete confirmation overlay with \"Are you sure you want to delete this credit card\\?\" message when deleting a credit card$")
    public void iShouldSeeDeleteConfirmationOverlayWithMessage() throws Throwable {
        String expectedDeleteConfirmationMsg = "Are you sure you want to delete this credit card?";
        CreditCard creditCardToDelete = cardsAdded.get(0);
        openDeleteCardPopup(creditCardToDelete);
        assertTrue("Expected Alert Message 'Are you sure you want to delete this credit card'" +
                        " is not displayed after clicking on Delete Card Button.",
                expectedDeleteConfirmationMsg.equals(getMessageFromAlert()));
    }

    @And("^I delete any credit card$")
    public void iDeleteAnyCreditCard() throws Throwable {
        CreditCard creditCardToDelete = cardsAdded.get(0);
        deleteCard(creditCardToDelete);
    }

    @And("^I should see credit card is deleted from my wallet page$")
    public void iShouldSeeCreditCardIsDeletedFromMyWalletPage() throws Throwable {
        Navigate.browserRefresh();
        CreditCard deletedCard = cardsAdded.get(0);
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        assertTrue("Even after deleting one Credit Card, still 2 Cards displayed instead of One Card on Wallet page.",
                walletCards.size() == 1);
        assertTrue("Deleted '" + deletedCard.getCardType().name + "' is still present on Wallet Page.",
                !walletCards.get(0).getCardType().name.equalsIgnoreCase(deletedCard.getCardType().name));
    }

    @And("^I should see the credit card is deleted from database$")
    public void iShouldSeeTheCreditCardIsDeletedFromDatabase() throws Throwable {
        Wait.forPageReady();
        WalletService ws = new WalletService();
        cardsAdded.remove(0);
        ArrayList<CreditCard> dataBaseCards = ws.getCardDetailsFromDB();
        Wallet.verifyWalletWithDBCardDetails(dataBaseCards, cardsAdded);
    }

    @And("^I cancel out of deleting the credit card$")
    public void iCancelOutOfDeletingTheCreditCard() throws Throwable {
        CreditCard cardToBeDeleted = cardsAdded.get(0);
        openDeleteCardPopup(cardToBeDeleted);
//        try {
        if (macys()) {
            if (WebDriverManager.getWebDriver().switchTo().alert() != null) {
                Alert deleteCardAlert = WebDriverManager.getWebDriver().switchTo().alert();
                Wait.forPageReady();
                System.out.print("Delete Card Alert is displayed.");
                deleteCardAlert.dismiss();
                Elements.findElement("credit_card_dialog.close").click();
            }

        } else {
            Elements.findElement("my_bwallet.no_delete_card").click();
            Wait.forPageReady();
        }
        onPage(macys() ? "oc_my_wallet" : "my_bwallet");
//        } catch (Exception e) {
//            Assert.fail("No Alert Exception, Alert not displayed after clicking on Delete Card button.");
//        }
    }

    @Then("^I should see credit card is not deleted from my wallet page$")
    public void iShouldSeeCreditCardIsNotDeletedFromMyWalletpage() throws Throwable {
        CreditCard deletedCard = cardsAdded.get(0);
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        assertTrue("Even after cancelling deletion of a Credit Card, One card is deleted out of Wallet page.",
                walletCards.size() == 2);
        assertTrue("Even after celling the deletion, '" + deletedCard.getCardType().name + "' is deleted from Wallet Page.",
                walletCards.get(0).getCardType().name.equalsIgnoreCase(deletedCard.getCardType().name));
    }

    @And("^I delete default credit card$")
    public void iDeleteDefaultCreditCard() throws Throwable {
        //first card is always the default card. So deleting the first card
        CreditCard creditCardToDelete = cardsAdded.get(0);
        deleteCard(creditCardToDelete);
    }

    @Then("I should see next credit card as default credit card on my wallet page and in database")
    public void iShouldSeeNextCreditCardAsDefaultCreditCardOnMyWalletPageAndInDB() throws Throwable {
        openEditCard(cardsAdded.get(1));
        Wait.forPageReady();
        assertTrue("Second Credit Card is not masked as Default after deleting the Default Credit Card",
                Elements.findElement("panel.credit_card_dialog.set_as_default").getAttribute(macys() ? "disabled" : "aria-disabled") != null);
        assertTrue("Second Credit Card is not masked as Default after deleting the Default Credit Card",
                Elements.findElement("panel.credit_card_dialog.set_as_default").getAttribute("checked") != null);
        WalletService ws = new WalletService();
        String actDefaultCard = ws.getDefaultCardFromDB();
        assertTrue("Default Card is not correctly set to the next card after deleting default card from Wallet page",
                cardsAdded.get(1).getCardType().name.equals(actDefaultCard));
    }

    @Then("^I should see add credit card button$")
    public void iShouldSeeAddCreditCardButton() {
        assertTrue("ADD A NEW CARD button is not displayed on Wallet page.", Elements.elementPresent("oc_my_wallet.add_credit_card"));
    }

    @And("^I should see all the details for the stored credit cards$")
    public void iShouldSeeAllTheDetailsForTheStoreCreditCards() {
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        Wallet.verifyWalletCardsMatchAddedCards(walletCards, cardsAdded);
    }

    @Then("^I should see upto (\\d+) stored credit cards$")
    public void iShouldSeeUpToStoredCreditCards(int noCards) {

        Wait.forPageReady();
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        assertTrue("All 10 Cards are not displayed on Wallet Page Cards Section even after adding 10 cards. Actual Cards count "
                + walletCards.size(), walletCards.size() == 10);
        logger.info("Verified: 10 Credit cards are displayed on Wallet Page Cards section.");
    }

    @Then("^I should see the stored cards with primary on top and followed by recently added$")
    public void iShouldSeeTheStoredCardsWithPrimaryOnTopAndFollowedByRecentlyAdded() {
        Wait.forPageReady();
        ArrayList<CreditCard> walletCards = Wallet.getListOfAddedCardsInWallet();
        assertTrue("Primary Card is not displayed on the top of the Credit Cards list",
                walletCards.get(0).getCardNumber().equals(cardsAdded.get(0).getCardNumber().substring(12, 16)));
        assertTrue("Recently  added card is not displayed after the Primary Credit Card in the credit cards list",
                walletCards.get(1).getCardNumber().equals(cardsAdded.get(1).getCardNumber().substring(12, 16)));
    }

    @Then("^I should see default text at the top of the primary credit card$")
    public void iShouldSeeDefaultTextAtTheTopOfThePrimaryCreditCard() {
        assertTrue("'Default' text is not displayed for the primary credit card", Elements.findElements(
                "oc_my_wallet.card_row").get(0).findElement(Elements.element("oc_my_wallet.card_default_indicator")).getText().equals("Default"));
    }

    //no_saved_credit_card_message
    @Then("^I should see no credit cards in wallet message: \"You don't currently have any stored payment methods.\" on my wallet page$")
    public void iShouldSeeNoCreditCardsInWalletMessage() {
        assertTrue("No Cards message 'You don't currently have any stored payment methods.' is not displayed on " +
                "Cards section of Wallet Page.", Elements.findElement("oc_my_wallet.no_saved_credit_card_message").
                getText().equals("You don't currently have any stored payment methods."));
    }

    @Then("^I should see the following titles for the prop and co-brand cards:$")
    public void iShouldSeeTheFollowingTitlesForThePropAndCoBrandCards(DataTable cardTypes) {

        List<String> expectedCardTitles = cardTypes.asList(String.class);
        List<String> actualCardTitles = new ArrayList();
        List<WebElement> walletCards = Elements.findElements("my_bwallet.credit_card_types");
        for (int index = 0; index < walletCards.size(); index++) {
            actualCardTitles.add(walletCards.get(index).getText());
        }
        assertTrue("Title of the cards added are not displayed correctly. Expected Card Titles - " + expectedCardTitles
                        + "Actual Title displayed are " + actualCardTitles,
                actualCardTitles.containsAll(expectedCardTitles) && actualCardTitles.size() == expectedCardTitles.size());

    }

    @Then("^I should see the offer details on my wallet page$")
    public void iShouldSeeTheOfferDetailsOnMyWalletPage() {

        Wait.forPageReady();
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.offer_rows");
        for (int index = 0; index < offerRows.size(); index++) {
            //loading the elements again to avoid the stale element exception
            offerRows = Elements.findElements("oc_my_wallet.offer_rows");
            Map<String, String> expectedPromo = new HashMap();
            String actPromoCode = offerRows.get(index).findElement(Elements.element("oc_my_wallet.special_redemption_codes")).getText();
            for (Map<String, String> promo : promoCodes) {
                if (!promo.get("promoCode").equals(actPromoCode)) {
                    if (promo.get("isSUPC").equals("Y") && promo.get("SUPC").equals(actPromoCode)) {
                        expectedPromo = promo;
                        break;
                    }
                } else {
                    expectedPromo = promo;
                    break;
                }
            }
            assertTrue("Displayed Offer " + actPromoCode + " is not present in Expected Offers List", expectedPromo != null);
            if (!expectedPromo.get("isSUPC").equals("Y")) {
                String actPromoName = offerRows.get(index).findElement(Elements.element("oc_my_wallet.offer_names")).getText();
                assertTrue("Promotion Heading on Wallet Page is not matching with Database. Expected Promotion Heading: " + expectedPromo.get("promoHeading") + " Actual Promotion Name: " + actPromoName, actPromoName.contains(expectedPromo.get("promoHeading")));
                assertTrue("Promotion SubHeading on Wallet Page is not matching with Database. Expected Promotion Sub Heading: " + expectedPromo.get("promoSubHeading") + " Actual Promotion Name: " + actPromoName, actPromoName.contains(expectedPromo.get("promoSubHeading")));
                assertTrue("Promotion Code on Wallet Page is not matching with Database. Expected Promotion Code: " + expectedPromo.get("promoCode") + " Actual Promotion Code: " + actPromoCode, actPromoCode.equals(expectedPromo.get("promoCode")));
            }
            String actValidityDates = offerRows.get(index).findElement(Elements.element("oc_my_wallet.offer_dates")).getText();
            String expValidityDates = "Valid " + expectedPromo.get("effectiveDate") + " - " + expectedPromo.get("expirationDate");
            assertTrue("Promotion Dates on Wallet Page are not matching with Database. Expected Promotion Dates: " + expValidityDates + " Actual Promotion Dates: " + actValidityDates, actValidityDates.equals(expValidityDates));
            assertTrue("Exclusions & Details link is not displayed for the Offer Code " + expectedPromo.get("promoCode"), offerRows.get(index).findElement(Elements.element("oc_my_wallet.view_details_and_exclusions")) != null);
            assertTrue("Remove offer link is not displayed for the Offer Code " + expectedPromo.get("promoCode"), offerRows.get(index).findElement(Elements.element("oc_my_wallet.delete_offers")) != null);
        }
    }

    @Then("^I should see add offer or pass and deals and promotions button on my wallet page$")
    public void iShouldSeeAddOfferOrpassAndDealsAndPromotionsButtonOnMyWalletPage() {
        assertTrue("'ADD OFFER OR PASS' button is not displayed on Wallet page", Elements.findElement("oc_my_wallet.add_offer_pass") != null);
        assertTrue("'DEALS AND PROMOTIONS' button is not displayed on Wallet page", Elements.findElement("oc_my_wallet.deals_and_promotions") != null);
    }

    @Then("^I should see the Details and Exclusion text on opening details and exclusions overlay for any manually added offer$")
    public void iShouldSeeTheDetailsAndExclusionText() {
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.offer_rows");
        for (int index = 0; index < offerRows.size(); index++) {
            //loading the elements again to avoid the stale element exception
            offerRows = Elements.findElements("oc_my_wallet.offer_rows");
            Map<String, String> expectedPromo = new HashMap();
            //  String expectedPromo = null;
            String actPromoCode = offerRows.get(index).findElement(Elements.element("oc_my_wallet.special_redemption_codes")).getText();
            for (Map<String, String> promo : promoCodes) {
                // for (String promo : Wallet.promoList) {
                if (!promo.get("promoCode").equals(actPromoCode)) {
                    if (promo.get("isSUPC").equals("Y") && promo.get("SUPC").equals(actPromoCode)) {
                        expectedPromo = promo;
                        break;
                    }
                } else {
                    expectedPromo = promo;
                    break;
                }
            }
            offerRows.get(index).findElement(Elements.element("oc_my_wallet.view_details_and_exclusions")).click();
            Wait.ajaxDone();

            assertTrue("Displayed Offer " + actPromoCode + " is not present in Expected Offers List", expectedPromo != null);
            String actExclusionsDetails = Elements.findElement("panel.exclusions_and_details_dialog.exclusions_and_details_content").getText().replaceAll("  ", " ");
            String expExclusionsDetails = expectedPromo.get("legalDisclaimer").replaceAll("  ", " ").replaceAll("\u0092", "’");
            assertTrue("For Promotion " + expectedPromo.get("promoCode") + ", 'Exclusions and Details' text on Wallet Page is not matching with Database. Expected Text: " + expExclusionsDetails + " Actual Text: " + actExclusionsDetails, actExclusionsDetails.equals(expExclusionsDetails));
            assertTrue("Legal disclaimer text is blank", actExclusionsDetails != null);
            Clicks.click("panel.exclusions_and_details_dialog.close");
        }
    }

    @Given("^I visit the web site as a registered user with no stored offers$")
    public void iVisitTheWebSiteAsARegistereduserWithNoStoreoffers() throws Throwable {

       // pageNavigation.I_navigate_to_create_profile_page();
       // myAccountSteps.iCreateANewProfile();
        CreateProfile.createProfileUsingServiceAndLogin();
    }

    @Then("^I should see text \"Add savings passes, deals, promos & more to save online & in-store!\" in my offers & passes section list view$")
    public void iShouldSeeTextAddSavingspassesDealsPromosMoreToSaveOnlineInsoreListView() {
        Wait.forPageReady();
        assertTrue("Messsage 'Add savings passes, deals, promos & more to save online & in-store!' is not displayed when there are no offers in the wallet", Elements.elementPresent("oc_my_wallet.no_offer_message"));
    }

    @Then("^I should see text \"Add savings passes, deals, promos & more to save online & in-store!\" in my offers & passes section grid view$")
    public void iShouldSeeTextAddSavingspassesDealsPromosMoreToSaveOnlineInsoreGridView() {
        assertTrue("Messsage 'Add savings passes, deals, promos & more to save online & in-store!' is not displayed in grid view when there are no offers in the wallet", Elements.elementPresent("oc_my_wallet.grid_view_no_offer_message"));
    }


    @Then("^I should see customer's first name followed by Wallet on my wallet page$")
    public void iShouldSeeCustomersFirstNameFollowedByWalletOnMyWalletPage() {
        assertTrue("'<Customer First Name>'s Wallet' header is not displayed correctly on Wallet page", (TestUsers.getCustomer(null).getUser().getProfileAddress().getFirstName() + "'s Wallet").equals(Elements.getText("oc_my_wallet.customer_first_name")));
    }

    @And("^I click on deals & promotions button on My Wallet page$")
    public void iClickOnDealsPromotionsButtonOnMyWalletPage() {
        Wait.forPageReady();
        Clicks.click("oc_my_wallet.deals_and_promotions");
        Wait.forPageReady();
    }

    @Then("^I should redirect to deals & promotions page$")
    public void iShouldRedirectToDealsPromotionsPage() {
        assertTrue("Failed to navigate to Deals and Promotions page", onPage("oc_my_wallet.my_offers"));
    }

    @And("^I select grid view option from view options$")
    public void iSelectGridViewOptionFromViewOptions() {
        Elements.findElement("oc_my_wallet.grid_view_icon").click();
    }

    @Then("^I should see the grid offer details on my wallet page$")
    public void iShouldSeeTheGridOfferDetailsOnMyWalletpage() {
        assertTrue("Offers Grid View is not displayed even after clicking on Grid View Icon.", Elements.elementPresent("oc_my_wallet.offers_list_grid_view"));
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.grid_view_offer_rows");
        for (int index = 0; index < offerRows.size(); index++) {
            //loading the elements again to avoid the stale element exception
            offerRows = Elements.findElements("oc_my_wallet.grid_view_offer_rows");
            Map<String, String> expectedPromo = new HashMap();
            String actPromoCode = offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_special_redemption_codes")).getText();
            for (Map<String, String> promo : promoCodes) {
                if (!promo.get("promoCode").equals(actPromoCode)) {
                    if (promo.get("isSUPC").equals("Y") && promo.get("SUPC").equals(actPromoCode)) {
                        expectedPromo = promo;
                        break;
                    }
                } else {
                    expectedPromo = promo;
                    break;
                }
            }
            offerRows = Elements.findElements("oc_my_wallet.grid_view_offer_rows");
            assertTrue("Displayed Offer " + actPromoCode + " is not present in Expected Offers List", expectedPromo != null);
            if (expectedPromo.get("isSUPC").equals("Y")) {
                assertTrue("Promotion Code on Wallet Page is not matching with Database. Expected Promotion Code: " + expectedPromo.get("promoCode") + " Actual Promotion Code: " + actPromoCode, actPromoCode.equals(expectedPromo.get("SUPC")));
            } else {
                assertTrue("Promotion Code on Wallet Page is not matching with Database. Expected Promotion Code: " + expectedPromo.get("promoCode") + " Actual Promotion Code: " + actPromoCode, actPromoCode.equals(expectedPromo.get("promoCode")));
            }
            //  assertTrue("Promotion Code on Wallet Page is not matching with Database. Expected Promotion Code: " + expectedPromo.get("promoCode") + " Actual Promotion Code: " + actPromoCode, actPromoCode.equals(expectedPromo.get("promoCode")));
            String actPromoName = offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_offer_names")).getText();
            if (!expectedPromo.get("isSUPC").equals("Y")) {
                assertTrue("Promotion Heading on Wallet Page is not matching with Database. Expected Promotion Heading: " + expectedPromo.get("promoHeading") + " Actual Promotion Name: " + actPromoName, actPromoName.contains(expectedPromo.get("promoHeading")));
                assertTrue("Promotion SubHeading on Wallet Page is not matching with Database. Expected Promotion Sub Heading: " + expectedPromo.get("promoSubHeading") + " Actual Promotion Name: " + actPromoName, actPromoName.contains(expectedPromo.get("promoSubHeading")));
            }
            String actValidityDates = offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_offer_dates")).getText();
            String expValidityDates = "Valid " + expectedPromo.get("effectiveDate") + " - " + expectedPromo.get("expirationDate");
            assertTrue("Promotion Dates on Wallet Page are not matching with Database. Expected Promotion Dates: " + expValidityDates + " Actual Promotion Dates: " + actValidityDates, actValidityDates.equals(expValidityDates));
            assertTrue("Exclusions & Details link is not displayed for the Offer Code " + expectedPromo.get("promoCode"), offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_details_and_exclusions")) != null);
            assertTrue("Remove offer link is not displayed for the Offer Code " + expectedPromo.get("promoCode"), offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_delete_offer")) != null);

        }
    }

    @Then("^I should see the details and exclusion text on opening details and exclusions overlay for any manually added offer on grid view$")
    public void iShouldSeeDetailsAndExclusionsTextForGridView() {
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.grid_view_offer_rows");
        for (int index = 0; index < offerRows.size(); index++) {
            //loading the elements again to avoid the stale element exception
            offerRows = Elements.findElements("oc_my_wallet.grid_view_offer_rows");
            Map<String, String> expectedPromo = new HashMap();
            String actPromoCode = offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_special_redemption_codes")).getText();
            for (Map<String, String> promo : promoCodes) {
                if (!promo.get("promoCode").equals(actPromoCode)) {
                    if (promo.get("isSUPC").equals("Y") && promo.get("SUPC").equals(actPromoCode)) {
                        expectedPromo = promo;
                        break;
                    }
                } else {
                    expectedPromo = promo;
                    break;
                }
            }
            offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_details_and_exclusions")).click();
            Wait.ajaxDone();

            assertTrue("Displayed Offer " + actPromoCode + " is not present in Expected Offers List", expectedPromo != null);
            String actExclusionsDetails = Elements.findElement("panel.exclusions_and_details_dialog.exclusions_and_details_content").getText().replaceAll("  ", " ");
            String expExclusionsDetails = expectedPromo.get("legalDisclaimer").replaceAll("  ", " ").replaceAll("\u0092", "’");
            assertTrue("For Promotion " + expectedPromo.get("promoCode") + ", 'Exclusions and Details' text on Wallet Page is not matching with Database. Expected Text: " + expExclusionsDetails + " Actual Text: " + actExclusionsDetails, actExclusionsDetails.equals(expExclusionsDetails));
            assertTrue("Legal disclaimer text is blank", actExclusionsDetails != null);
            Clicks.click("panel.exclusions_and_details_dialog.close");

        }
    }

    @Given("^I visit the web site as a registered user with manually (\\d+) stored offers with different expiration dates in wallet$")
    public void iVisitTheWebSiteAsARegisteredUserWithManuallyStoredOffersWithDiffExpDatesInWallet(int numOffers) throws Throwable {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        pageNavigation.I_navigate_to_create_profile_page();
        myAccountSteps.iCreateANewProfile();
        // pageNavigation.I_visit_the_web_site_as_a_registered_user();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        promoCodes = promotionService.getWalletEligiblePromoCodesByCount(numOffers, false);
        for (int i = 0; i < numOffers; i++) {
            Wait.forPageReady();
            String expirationDate = promoCodes.get(i).get("expirationDate");
            Date expDate = sdf.parse(expirationDate);
            promotionService.updatePromotionEndDate(promoCodes.get(i).get("promotionID"), sdf2.format(addDays(expDate, i)));
            Wait.forPageReady();
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(promoCodes.get(i).get("promoCode"));
        }
    }

    @Then("^I verify sorting order for grid offers in wallet$")
    public void iVerifySortingOrderForGridOffersInWallet() {
        List<String> expectedPromos = new ArrayList();
        List<String> actPromos = new ArrayList();
        for (Map<String, String> promo : promoCodes) {
            expectedPromos.add(promo.get("promoCode"));
        }
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.grid_view_offer_rows");
        for (int index = 0; index < offerRows.size(); index++) {
            actPromos.add(offerRows.get(index).findElement(Elements.element("oc_my_wallet.grid_view_special_redemption_codes")).getText());
        }
        assertTrue("Offers are not displayed in the order of their expiry dates. Expected order " + expectedPromos + "Actual order " + actPromos, expectedPromos.equals(actPromos));
    }

    @Then("^I should see default view as the list view along with red color as the selected state$")
    public void iShouldSeeDefaultViewAsTheListViewAlongWithRedColorAsTheSelectedState() {
        assertTrue("List View is not shown as default view of the promotions", Elements.findElement("oc_my_wallet.list_view_icon").getAttribute("class").equals("list-view active-view"));
    }

    @And("^I should see the grid view icon with grey color as the unselected state$")
    public void iShouldSeeTheGridViewIconWithGreyColorAsUnselectedState() {
        assertTrue("Grid View is not shown as inactive view of the promotions", Elements.findElement("oc_my_wallet.grid_view_icon").getAttribute("class").equals("grid-view"));
    }

    @Then("^I should see the list view icon turned to gray color as the unselected state and grid view icon turns red as the selected state$")
    public void iShouldSeeTheListViewIconTurnedToGrayColorAsTheUnselectedStateAndGridViewIconTurnsSelectedState() {
        assertTrue("Grid View is not shown as default view of the promotions", Elements.findElement("oc_my_wallet.grid_view_icon").getAttribute("class").equals("grid-view active-view"));
        assertTrue("List View is not shown as inactive view of the promotions", Elements.findElement("oc_my_wallet.list_view_icon").getAttribute("class").equals("list-view"));
    }

    @Then("^I should see trade mark symbol after each credit card name in wallet page$")
    public void iShouldSeeTradeMardSymbolAfterEachCreditCardNameInWalletPage() {
        String pageName = macys() ? "oc_my_wallet" : "my_bwallet";
        List<WebElement> cardRowElementsList = Elements.findElements(pageName + ".card_row");
        //add validations for logo and edit pencil icon
        for (int index = 0; index < cardRowElementsList.size(); index++) {
            cardRowElementsList = Elements.findElements(pageName + ".card_row");
            if (cardRowElementsList.get(index).findElement(Elements.element("oc_my_wallet.card_type_name")).getText().contains(cardsAdded.get(index).getCardNumber())) {
                if (macys()) {
                    assertTrue("Card Logo has not been displayed correctly.", cardRowElementsList.get(index).findElement(Elements.element("oc_my_wallet.card_logo")).
                            getAttribute("aria-label").contains("credit card is " + cardsAdded.get(index).getCardType().name + " with the last four numbers of " + cardsAdded.get(index).getCardNumber()));
                    break;
                }

            }
        }
    }

    @When("^I remove any non star reward offer from the offer list$")
    public void iRemoveAnyNonStarRewardsOfferFromTheOfferList() {
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.offer_row");
        for (int index = 0; index < offerRows.size(); index++) {
                offerRows.get(index).findElement(Elements.element("oc_my_wallet.delete_offers")).click();
                Wait.ajaxDone();
                Elements.findElement("oc_my_wallet.yes_delete_offer").click();
                Wait.ajaxDone();
                Wait.ajaxDone();
                Wait.forPageReady();
        }

    }

    @Then("^I should not see the deleted offer in the offer list$")
    public void iShouldNotSeeTheDeletedOfferInTheofferList() throws Throwable {
        Thread.sleep(8000);
        List<WebElement> offerRows = Elements.findElements("oc_my_wallet.offer_row");
        assertTrue("Deleted offer is still present in Offer List", offerRows.size() == 0);
    }

    @Given("^I visit the web site as a loyallist using \"less_than_2500_points\"$")
    public void iVisitedTheWebSiteAsALoyallistUsingLessThan2500Points() throws Throwable {
        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
        loyallistDetails = LoyallistAssociation.getLoyallistDetails("base_tier", (loyallistDetails) -> loyallistDetails.getPoints() == 2100);
        associateLoyallistID(loyallistDetails);

    }

    @Given("^I visit the web site as a loyallist using \"rewards\"$")
    public void iVisitedTheWebSiteAsALoyallistUsingRewards() throws Throwable {
        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
        loyallistDetails = LoyallistAssociation.getLoyallistDetails("reward");
        associateLoyallistID(loyallistDetails);

    }

    @Then("I should see the rewards card section is displayed properly for the loyallist with zero rewards")
    public void iShouldSeeTheRewardsCardsectionWithLoyallist() {
        assertTrue("My Loyallist Account - Header is not dispalyed", Elements.elementPresent("my_bwallet.loyalty_header_with_dog"));
        assertTrue("Reward Card's Balance Title is not dispalyed", Elements.elementPresent("my_bwallet.reward_card_balance_title"));
        assertTrue("Loyallist Number is not displayed correctly under rewards Card section", Elements.getText("my_bwallet.loyalty_number").equals(loyallistDetails.getLoyaltyId()));
        double rewards = Double.parseDouble(Elements.getText("my_bwallet.reward_card_balance_loyalty").replace("$", ""));
        assertTrue("Rewards Balance is not displayed correctly under rewards Card section", rewards == 0.00);
    }

    @Then("I should see the rewards card section is displayed properly for the loyallist with rewards more than zero")
    public void iShouldSeeTheRewardsCardsectionWithLoyallistPointsGreaterThanZero() {
        assertTrue("My Loyallist Account - Header is not dispalyed", Elements.elementPresent("my_bwallet.loyalty_header_with_dog"));
        assertTrue("Reward Card's Balance Title is not dispalyed", Elements.elementPresent("my_bwallet.reward_card_balance_title"));
        assertTrue("Loyallist Number is not displayed correctly under rewards Card section", Elements.getText("my_bwallet.loyalty_number").equals(loyallistDetails.getLoyaltyId()));
        double rewards = Double.parseDouble(Elements.getText("my_bwallet.reward_card_balance_loyalty").replace("$", ""));
        assertTrue("Rewards Balance is not displayed correctly under rewards Card section", rewards > 0.00);
    }

    @And("^I navigate to Loyallist Account Association page from My Account$")
    public void iNavigateToLoyallistAccountAssociationPage() throws Throwable {
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
    }

    @And("^I can associate my account by loyallist number using \"toptier_loyallist_details\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsingTopTier() throws Throwable {
        loyallistDetails = LoyallistAssociation.getLoyallistDetails("top_tier", (detail) -> {
            return detail.getPoints() == 5100;
        });
        associateLoyallistID(loyallistDetails);
    }

    @And("^I can associate my account by loyallist number using \"rewards\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsingRewards() throws Throwable {
        loyallistDetails = LoyallistAssociation.getLoyallistDetails("reward", (detail) -> {
            return detail.getPoints() == 5100;
        });
        associateLoyallistID(loyallistDetails);
    }

    @And("^I can associate my account by loyallist number using \"less_than_2500_points\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsingBaseTier() throws Throwable {
        loyallistDetails = LoyallistAssociation.getLoyallistDetails("base_tier", (detail) -> {
            return detail.getPoints() == 2100;
        });
        associateLoyallistID(loyallistDetails);
    }

    @When("I navigate to 'My Account' page")
    public void iNavigateToMyAccountPage() {
        myAccountSteps.iNavigateToMyAccountPage();
    }

    @Then("^I should see title as my bWallet$")
    public void iShouldSeeTitleAsMyBWallet() {
        // toggle the below in 17Z
        assertTrue("Title 'MY bWALLET' is not displayed.", Elements.getText("my_account.myaccount_wallet_section_header").equals("MY bWALLET"));
//        assertTrue("Title 'bWALLET' is not displayed.", Elements.getText("my_account.bwallet_header").equals("bWallet"));
    }

    @And("^I should see the number of offers in my bWallet$")
    public void iShouldSeeTheNumberOfOffersInMyBWallet() {
        String expectedText;
        Wait.forPageReady("my_account");
        if (macys()) {
            expectedText = totalOffers + " See Saved Offers";
            assertTrue(expectedText + " text is not displayed in My bWallet section", Elements.getText("my_account.see_saved_offers").equals(expectedText));
        } else {
            // toggle the below in 17Z
            if (totalOffers == 1) {
                expectedText = totalOffers + "  Saved Offer";
            } else
                expectedText = totalOffers + "  Saved Offers";
            assertTrue(expectedText + " text is not displayed in My bWallet section", Elements.getText("my_account.myaccount_saved_offers").equals(expectedText));
//            assertTrue("Current Offers text is not displayed in My bWallet section", Elements.getText("my_account.current_offers").equals("Current Offers"));
//            assertTrue("Current Offers count is not matched in My bWallet section", Integer.parseInt(Elements.getText("my_account.saved_offer_count"))==totalOffers);
        }
    }

    // toggle the below in 17Z
    @And("^I should see link 'View my bWallet' that takes me to bWallet page$")
//    @And("^I should see link 'My bWallet' that takes me to bWallet page$")
    public void iShouldSeeLinkViewMyBWalletThatTakesMeTobWalletPage() {
        assertTrue("'View Muy bWallet' link is not displayed in My bWallet section", Elements.getText("my_account.myaccount_wallet_link").equals("View mybWallet"));
//        assertTrue("'MY bWALLET' link is not displayed in My bWallet section", Elements.getText("my_account.myaccount_wallet_link").equals("MY bWALLET"));
        Elements.findElement("my_account.myaccount_wallet_link").click();
        Wait.forPageReady();
        onPage("my_bwallet");
    }

    /*@And("^I can associate my account by loyallist number using \"rewards\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsingRewards(String type) throws Throwable {
        myAccountSteps.iNavigateToTheLoyallistAccountAssociationPage();
        loyallistDetails = LoyallistAssociation.getLoyallistDetails("reward");
        associateLoyallistID(loyallistDetails);
    } */

    @And("^I capture the reward card balance from wallet page$")
    public void iCaptureTheRewardCardBalanceFromWalletPage() throws Throwable {
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        rewardCardBalance = Double.parseDouble(Elements.getText("my_bwallet.reward_card_balance_loyalty").replace("$", ""));
    }

    @Then("^It should match the total reward card balance$")
    public void iShouldSeeTotalRewardCardBalance() {
        // toggle the below in 17Z
        assertTrue("Reward Card Balance is not displayed correctly on My Account page bWallet Section", Double.parseDouble(Elements.getText("my_account.myaccount_reward_balance").replace("$", "")) == rewardCardBalance);
//        assertTrue("Reward Card Balance is not displayed correctly on My Account page bWallet Section", Double.parseDouble(Elements.getText("my_account.myaccount_reward_balance").split(" ")[0].replace("$", "")) == rewardCardBalance);
    }

    @And("^I capture the points to next reward card from wallet page$")
    public void iCaptureThePointsToNextRewardCardFromWallet() throws Throwable {
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        remainingPointnsToNextCard = Elements.getText("my_bwallet.remaining_points_for_next_card");
    }

    @Then("^I should see total points I need to go to the next reward card$")
    public void iShouldSeeTotalPointsINeedToGoToTheNextRewardsCard() {
        // toggle the below in 17Z
        assertTrue("Total points need for next reward card is not displayed correctly on My Account page bWallet Section", Elements.getText("my_account.myaccount_remaining_points_for_next_card").equals(remainingPointnsToNextCard));
//        assertTrue("Total points need for next reward card is not displayed correctly on My Account page bWallet Section", Elements.getText("my_account.myaccount_remaining_points_for_next_card").split(" ")[0].equals(remainingPointnsToNextCard.split(" ")[0]));
    }

    @Then("^I should see 'View My Loyallist Account' link is taking me to loyallist account summary page$")
    public void iShouldSeeViewMyLoyallistAccountLinkIsTakingMeToLoyallistAccountSummaryPage() {
        Elements.findElement("my_account.view_my_loyalllist_account").click();
        Wait.forPageReady();
        assertTrue("'View My Loyallist Account' link is not navigating to Loyallist Account Summary", onPage("loyallist_account_summary"));

    }

    @And("^I remove loyallist ID association as part of clean up$")
    public void removeLoyallistIDFromAccountPage() {
        myAccountSteps.iNavigateToMyAccountPage();
        Clicks.click("my_account.view_my_loyalllist_account");
        Wait.forPageReady();
        shouldBeOnPage("loyallist_account_summary");
        Clicks.click("loyallist_account_summary.remove_button");
        Wait.untilElementPresent("loyallist_account_summary.lty_account_panel");
        Clicks.click("loyallist_account_summary.remove_confirmation_btn");
        shouldBeOnPage("loyalty_association");
    }

    @And("^I add a \"PROP\" credit card to my profile$")
    public void iAddAPropCreditCardToMyProfile() {
        CreditCard creditCard = Wallet.getValidCreditCard("Bloomingdale's");
        addCard(creditCard);
        cardsAdded.add(creditCard);
    }


    @Then("^I should see 'Loyallist' header in the Loyallist tile$")
    public void ishouldSeeLoyallistHeaderInLoyallistTitle() {
        assertTrue("Loyallist header is not displayed in My Account page Loyallist tile", Elements.getText("my_account.loyallist_header").equals("Loyallist"));
    }

    @Then("^I should see 'NOT A LOYALLIST\\?' header text under wallet section$")
    public void iShouldSeeNotALoyallistHeaderTextUnerWalletSection() {
        assertTrue("NOT A LOYALLIST? header is not displayed on My Account page bWallet Section", Elements.getText("my_account.myaccount_wallet_section_lty_header").equals("NOT A LOYALLIST?"));
    }

    @And("^I should see \"Earn rewards when you shop online, in store and at our outlets.\" text under wallet section$")
    public void iShouldSeeEarnRewardsWhenYouShopOnline() {
        assertTrue("'Earn rewards when you shop online, in store and at our outlets.' text is not displayed on My Account page bWallet Section", Elements.getText("my_account.myaccount_wallet_section_lty_text").equals("Earn rewards when you shop online, in store and at our outlets."));
    }

    @Then("^I should see 'Become a Loyallist' link is taking me to loyalty create account page$")
    public void iShouldSeeBecomeALoyallistLinkIsTalkingMeToLoyaltyCreateAccountPage() {
        Elements.findElement("my_account.myaccount_loyalty_become_a_loyallist_link").click();
        Wait.forPageReady();
        assertTrue("'Become a Loyallist' link is not navigating to Loyallist Account Create Page", onPage("loyalty_enrollment"));
    }

    @Then("^I should see 'Add My Loyallist Account' link is taking me to loyalty account association page$")
    public void iShouldSeeAddMyLoyallistAccountLinkIsTakingMeToLoyaltyAccountAssociationPage() {
        Elements.findElement("my_account.myaccount_add_loyalty_link").click();
        Wait.forPageReady();
        assertTrue("'Add my Loyallist Account' link is not navigating to Loyallist Account Create Page", onPage("loyalty_association"));
    }

    @Then("^I should see the \"My bWallet\" navigation title on profile page$")
    public void iShouldSeeTheMyBWalletNavigationTitleOnProfilePage() {
        assertTrue("'My bWallet' navigation link is not present on left hand side navigation bar of My Profile page.", Elements.elementPresent("my_profile.goto_my_wallet_link"));
    }

    @Then("^I should see my wallet page title as \"My bWallet - My Account - Bloomingdale's\"$")
    public void iShouldSeeMyWalletPageTitleCorreclty() throws Throwable {
        assertTrue("'My bWallet' page title is not displayed correctly as 'My bWallet - My Account - Bloomingdale's'.", WebDriverManager.getWebDriver().getTitle().equals("My bWallet - My Account - Bloomingdale's"));
    }

    @Then("^I should see sales and promotions title$")
    public void iShouldSeeSalesAndPromotionsTitle() throws Throwable {
        assertTrue("'Sales and Promotions' page title is not displayed correctly as 'Sales, Promotions and Coupons  - Bloomingdale's'", WebDriverManager.getWebDriver().getTitle().equals("Sales, Promotions and Coupons - Bloomingdale's"));
    }

    @And("^I should see left navigation header text \"SALES & PROMOTIONS\"$")
    public void iShouldSeeLeftNavigationHeaderSalesAndOffersText() {
        assertTrue("'Sales and Promotions' page left navigation header is not displayed correctly as 'SALES & PROMOTIONS'", Elements.getText("my_offers.offers_page_navigation").equals("SALES & PROMOTIONS"));
    }

    @And("^I should see copy text \"Browse our latest sales and promotions all in one place.\" under Sales & Promotions header$")
    public void iShouldSeeTextBrowseOurLatestSalesAndPromotionsAllInOnePlace() {
        assertTrue("'Under Sales & Promotions header copy text is not displayed correctly.", Elements.getText("my_offers.offers_desc").equals("Browse our latest sales and promotions all in one place."));
    }

    public void associateLoyallistID(LoyalistDetails loyallistDetails) throws Throwable {
        loyaltyService.removeLoyallistIDAssociation(loyallistDetails.getLoyaltyId());
        LoyallistAssociation.loyaltyAssociation(loyallistDetails);
        Wait.untilElementPresent("loyallist_account_summary.verify_page");
        shouldBeOnPage("loyallist_account_summary");
    }

    @When("^I navigate to the \"([^\"]*)\" link in My Account page$")
    public void iNavigateToTheLinkInMyAccountPage(String myAccountLink) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        switch (myAccountLink) {
            case "my profile":
                Clicks.click("my_account.goto_my_profile");
                Wait.forPageReady();
                break;
            case "My Address Book":
                Clicks.click("my_account.goto_my_address_book_link");
                Wait.forPageReady();
                break;
            case "My Wallet":
                Clicks.click("my_account.goto_my_wallet_link");
                Wait.forPageReady();
                break;
            case "My Order Status & History":
                Clicks.click("my_account.goto_order_status");
                Wait.forPageReady();
                break;
            // toggle the below in 17Z
            case "my account":
                Clicks.click("my_account.goto_my_account");
                Wait.forPageReady();
                break;
            case "My Points":
                Clicks.click("my_account.goto_my_points");
                Wait.forPageReady();
                break;
            case "My Perks":
                Clicks.click("my_account.goto_my_perks");
                Wait.forPageReady();
                break;
        }

    }

    @Then("^I verify that the \"([^\"]*)\" page is rendered$")
    public void iVerifyThatThePageIsRendered(String pageTitle) throws Throwable {
        String actTitle = WebDriverManager.getWebDriver().getTitle();
        assertTrue("Page title is not displayed correctly as " + pageTitle + " actual title: " + actTitle, actTitle.equals(pageTitle));
    }

    @And("^I should see Reward Card Balance link in left nav section$")
    public void iShouldSeeRewardCardBalanceLinkInLefNavSection() {
        assertTrue("'My Reward Card Balance' left navigation link is not displayed.", Elements.elementPresent("panel.navigation.goto_reward_card_balance"));
    }

    @Then("^I should see error message \"Please remove any special characters.\" on add offers overlay when entering the special characters$")
    public void iShouldSeeErrorMessagePleaseRemoveAnySpecialCharacters() {
        Clicks.click("oc_my_wallet.add_offer_btn");
        Wait.ajaxDone();
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", "!@#$%$#");
        Clicks.click("add_offer_dialog.save_offer");
        Wait.ajaxDone();
        assertTrue("Error Message - 'Please remove any special characters.' is not displayed when special characters are entered in Add Offer dialog", Elements.getText("panel.add_offer_dialog.offer_error").equals("Please remove any special characters."));
    }

    @And("^I should see 'See Saved Offers' link is taking me to my wallet page$")
    public void iShouldSeeSeeSavedOffersLinkTakingToWalletPage() {

        Clicks.click("my_account.see_saved_offers");
        Wait.forPageReady();
        assertTrue("My Wallet Page is not displayed when clicked on 'See Saved Offers' link.", onPage("oc_my_wallet"));
    }

    @Then("I should see 'wallet image', 'my wallet' and 'Find Out More' link on deals and promotions page")
    public void iShouldSeeWalletImageMyWalletAndFindOutMoreOnDealsAndPromotionsPage() {
        assertTrue("Wallet Image is not displayed on Deals and Promotions Page", Elements.elementPresent("my_offers.wallet_image"));
        assertTrue("'My Wallet' header is not displayed on Deals and Promotions Page", Elements.elementPresent("my_offers.customer_wallet"));
        assertTrue("'Find Out More' link is not displayed on Deals and Promotions Page", Elements.elementPresent("my_offers.wallet_find_out_more"));
    }

    @And("I should see wallet description \"Use wallet to check out faster & get new offers instantly! Just sign in or create an account.\"")
    public void iShouldSeeWalletDescriptionOnMyAccountPage() {
        String expWalletDescription = "Use wallet to check out faster & get new offers instantly! Just sign in or create an account.";
        assertTrue("Wallet description is not displayed in My Wallet section of Deals and Promotions Page.", Elements.getText("my_offers.wallet_description").equals(expWalletDescription));
    }

    @When("I click on get started in deals and promotions page")
    public void iClickOnGetStartedInDealsAndPromotionsPage() {
        Clicks.click("my_offers.goto_my_wallet");
        Wait.forPageReady();
    }

    @Then("I should see Sign In Page")
    public void iShouldSeeSignInpage() {
        assertTrue("Sign in Page not displayed for guest user when clicked on 'Get Started' link on Deals and Promotions Page.", onPage("sign_in"));
    }

    @When("^I sign in using existing username and password$")
    public void iSignInusingExisingUserNameAndpassword() {
        Map<String, String> userCredentials = CommonUtils.getARandomUserCredentials();
        TextBoxes.typeTextbox("sign_in.email", userCredentials.get("user_name"));
        TextBoxes.typeTextbox("sign_in.password", userCredentials.get("password"));
        Clicks.click("sign_in.verify_page");
        Wait.forPageReady();
        if (Elements.elementPresent("sign_in.security_question")) {
            UserProfile customer = TestUsers.getCustomer(null);
            DropDowns.selectByText("sign_in.security_question", customer.getUser().getUserPasswordHint().getQuestion());
            TextBoxes.typeTextbox("sign_in.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
            Clicks.click("sign_in.save_and_continue");
            Wait.forPageReady();
            logger.info("Set security question to user");
        }
        if (Elements.elementPresent("sign_in.complete_profile_cancel_button")) {
            Clicks.click("sign_in.complete_profile_cancel_button");
        }
    }

    @Then("I should see Wallet Page")
    public void iShouldSeeWalletPage() {
        Utils.threadSleep(4000, null);
        assertTrue("Sign in Page not displayed for guest user when clicked on 'Get Started' link on Deals and Promotions Page.", onPage(macys() ? "oc_my_wallet" : "my_bwallet"));
    }

    @Then("^I should see 'Add' button on add offer overlay$")
    public void iShouldSeeAddOnAddOfferOverlay() {
        assertTrue("Add button is not displayed on Wallet Add Offer section", Elements.findElement("oc_my_wallet.add_offer_pass") != null);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    @And("^I add a credit card to My Wallet with Zip code starting with \"([^\"]*)\"$")
    public void iAddACreditCardToMyWalletWithZipCodeStartingWith(String arg0) throws Throwable {
        HashMap<String, String> options = new HashMap<>();
        options.put("premium_ineligible", "true");
        CreditCard card = TestUsers.getValidVisaCreditCard();
        profileAddress = new ProfileAddress();
        TestUsers.getRandomValidAddress(options, profileAddress);
        MyWallet.addCard(card, profileAddress);

    }

    @And("^I \"([^\"]*)\" see zip code trimmed$")
    public void iSeeZipCodeTrimmed(String arg0) throws Throwable {
        Clicks.click("oc_my_wallet.edit_credit_cards");
        String zipcode_profile = profileAddress.getZipCode();
        if (!Elements.findElement("credit_card_dialog.address_zip_code").getAttribute("value").equals(zipcode_profile))
            Assert.fail("Zip code is trimmed");
    }

    @And("^I should see \"([^\"]*)\" link in offer widget section for signed in user$")
    public void iShouldSeeLinkInOfferWidgetSectionForSignedInUser(String linkText) throws Throwable {
        Wait.forPageReady();
        assertTrue("Adding Offers link could not be found in shopping bag page",
                Elements.elementPresent("shopping_bag.add_offers_link"));
        assertTrue("Adding Offers link text could not be matched, Expected:" + linkText + " Actual:" +
                        Elements.findElement("shopping_bag.add_offers_link").getText(),
                Elements.findElement("shopping_bag.add_offers_link").getText()
                        .contentEquals("Adding Offers"));
    }

    @When("^I click on 'sign in' link from wallet widget$")
    public void iClickOnSignInLinkFromWalletWidget() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("shopping_bag.sign_in_link");
        if (!Elements.elementPresent("shopping_bag.sign_in_link")) {
            Assert.fail("Sign In link could not be found");
        }
        Clicks.click("shopping_bag.sign_in_link");
    }

    @When("^I open offer widget overlay by clicking on see my offers link$")
    public void iOpenOfferWidgetOverlayByClickingOnSeeMyOffersLink() throws Throwable {

        if (!onPage("shopping_bag")) {
            Assert.fail("Shopping bag page not found");
        }
        Wait.forPageReady();
        Wait.untilElementPresent("shopping_bag.see_all_my_offers");
        if (Elements.elementPresent("shopping_bag.see_all_my_offers")) {
            Clicks.click("shopping_bag.see_all_my_offers");
        } else {
            Assert.fail("See all Of My Offers link could not be found");
        }
    }

    @Then("^I should see the promo code error in shopping bag:$")
    public void iShouldSeeTheError(DataTable error) throws Throwable {

        List<List<String>> data = error.raw();
        String expectedError = data.get(0).get(1);
        if (expectedError.contains("?")) {
            expectedError = expectedError.replace("?", OCWallet.promoCode);
        }
        Wait.untilElementPresent("shopping_bag.promo_inline_error_message");
        Assert.assertTrue("Invalid promo code error message could not be validated",
                Elements.findElement("shopping_bag.promo_inline_error_message").getText().contains(expectedError));
    }

    @When("^I apply a \"([^\"]*)\" in shopping bag page$")
    public void iApplyAInShoppingBagPage(String promoType) throws Throwable {

        if (!onPage("shopping_bag")) {
            Assert.fail("Shopping bag page not found");
        }
        Wait.forPageReady();
        Wait.untilElementPresent("shopping_bag.promo_code");
        if (Elements.elementPresent("shopping_bag.promo_code")) {
            switch (promoType) {
                case "invalid or expired promo code":
                    promoCode = new PromotionService().getWalletEligibleExpiredPromoCode();
                    if (promoCode == null) {
                        TextBoxes.typeTextbox("shopping_bag.promo_code", "invalid");
                    } else {
                        TextBoxes.typeTextbox("shopping_bag.promo_code", promoCode);
                    }
                    Clicks.click("shopping_bag.apply_promo_code_button");
                    break;
                case "invalid SUPC":
                    TextBoxes.typeTextbox("shopping_bag.promo_code", "X1111111111");
                    Clicks.click("shopping_bag.apply_promo_code_button");
                    break;
                case "used or expired SUPC":
                    promoCode = Wallet.generateExpiredSUPC(1).get(0);
                    TextBoxes.typeTextbox("shopping_bag.promo_code", promoCode);
                    Clicks.click("shopping_bag.apply_promo_code_button");
            }
        }
    }

    @When("^I leave promocode field empty and apply$")
    public void iLeavePromocodeFieldEmptyAndApply() throws Throwable {

        if (!onPage("shopping_bag")) {
            Assert.fail("Shopping bag page not found");
        }
        Wait.forPageReady();
        Wait.untilElementPresent("shopping_bag.promo_code");
        if (Elements.elementPresent("shopping_bag.promo_code")) {
            Clicks.click("shopping_bag.apply_promo_code_button");
        }
    }

    @And("^I select \"([^\"]*)\" in offer widget overlay$")
    public void iSelectInOfferWidgetOverlay(String action) throws Throwable {

        Wait.untilElementPresent("shopping_bag.offers_overlay");
        if (!Elements.elementPresent("shopping_bag.offers_overlay")) {
            Assert.fail("Offer widget overlay could not be found");
        }

        switch (action) {
            case "Close":
                Wait.untilElementPresent("shopping_bag.close_see_all_offers_widget");
                if (Elements.elementPresent("shopping_bag.close_see_all_offers_widget")) {
                    Clicks.click("shopping_bag.close_see_all_offers_widget");
                } else {
                    Assert.fail("Close link in offer widget overlay not found");
                }
                break;
        }
    }

    @Then("^I should see widget overlay is closed$")
    public void iShouldSeeWidgetOverlayIsClosed() throws Throwable {

        if (!onPage("shopping_bag")) {
            Assert.fail("Shopping bag page not found");
        }
        Wait.untilElementNotPresent("shopping_bag.offers_overlay");
        Assert.assertTrue("Offer widget overlay found",
                !Elements.elementPresent("shopping_bag.offers_overlay"));
    }

    @And("^I should see 'see my offers' link in offer widget section$")
    public void iShouldSeeSeeMyOffersLinkInOfferWidgetSection() throws Throwable {

        if (!onPage("shopping_bag")) {
            Assert.fail("Shopping bag page not found");
        }
        Assert.assertTrue("Offer widget overlay found",
                !Elements.elementPresent("shopping_bag.see_all_my_offers_link"));
    }

    @Given("^I visit the web site as a registered user with \"([^\"]*)\" in wallet$")
    public void iVisitTheWebSiteAsARegisteredUserWithInWallet(String cardTypes) throws Throwable {

        String splitCardTypes[] = cardTypes.split("and");
        CreditCard card;

        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();

        for (String cardType : splitCardTypes) {

            switch (cardType.trim()) {
                case "third party":
                    card = Wallet.getValidCreditCard("Visa");
                    MyWallet.addCard(card);
                    break;
                case "prop card":
                    card = Wallet.getValidCreditCard("Macy's");
                    MyWallet.addCard(card);
                    break;
            }
        }
    }

    @Then("^I should see user is able to select other than default credit card from credit cards section$")
    public void iShouldSeeUserIsAbleToSelectOtherThanDefaultCreditCardFromCreditCardsSection() throws Throwable {

        CreditCard expectedCard = Wallet.getValidCreditCard("Macy's");
        Wait.forPageReady("responsive_checkout_signed_in");
        Clicks.click("responsive_checkout_signed_in.change_credit_card_button");
        Assert.assertTrue("Prop card details could not be found",
                Elements.elementPresent("responsive_checkout_signed_in.unselected_cc_details_edit"));
        if (!Elements.findElement("responsive_checkout_signed_in.change_card_radio_button").isSelected()) {
            Clicks.click("responsive_checkout_signed_in.change_card_radio_button");
        }
        Clicks.click("responsive_checkout_signed_in.default_card_save_button");
        String cardDetails = Elements.findElement("responsive_checkout_signed_in.credit_card_details")
                .getText();
        String cardLineDetails[] = cardDetails.split("\n");
        Assert.assertTrue("Default card could not be changed",
                cardLineDetails[0].trim().equals("Macy's"));
        Assert.assertTrue("Default prop card number do not match prop card in wallet",
                cardLineDetails[1].trim().substring(12, 16).equals(expectedCard.getCardNumber().substring(12, 16)));
    }

    @Then("^I verify sorting order for list offers in wallet$")
    public void iVerifySortingOrderForListOffersInWallet() throws Throwable {

        List<Map<String, String>> supcs;
        int count = 2;
        promoCodes = promotionService.getWalletEligiblePromoCodesByCount(count, false);
        ArrayList<String> promos = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            promos.add(promoCodes.get(i).get("promoCode"));
        }
        if (promoCodes.size() != count) {
            Assert.fail("Global promo code could not be found");
        }

        supcs = promotionService.getWalletEligiblePromoCodesByCount(count, true);
        Wallet.verifySupcAndPromoOrderInWallet(supcs.get(0).get("SUPC"), promoCodes.get(0).get("promoCode"));
        Wallet.verifyBothSupcOrderInWallet(supcs);
        Wallet.verifyBothPromoOrderInWallet(promos);
    }

    @And("^I should see \"([^\"]*)\" text in offer widget section for signed in user$")
    public void iShouldSeeTextInOfferWidgetSectionForSignedInUser(String text) throws Throwable {

        Wait.forPageReady();
        assertTrue("Apply Offer text could not be matched, Expected:" + text + ", Actual:" +
                        Elements.findElement("shopping_bag.apply_offer_text").getText(),
                Elements.findElement("shopping_bag.apply_offer_text").getText()
                        .contentEquals("Apply an offer to see the savings!"));
    }

    @And("^I add an offer to my wallet$")
    public void iAddAnOfferToMyWallet() throws Throwable {

        new Wallet().AddValidOfferInWallet(1);
    }

    @And("^I checkout until I reach \"([^\"]*)\" page as a \"([^\"]*)\" user$")
    public void iCheckoutUntilIReachPageAsAUser(String pageName, String userType) throws Throwable {
        Wait.forPageReady();
        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
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

    @Given("^I visit the web site as a new registered user$")
    public void iVisitTheWebSiteAsANewRegisteredUser() throws Throwable {

       // pageNavigation.I_navigate_to_create_profile_page();
        CreateProfile.createProfileUsingServiceAndLogin();
       // myAccountSteps.iCreateANewProfile();
    }

    @Given("^I visit web site as registered user with Organic registration SUPC in wallet$")
    public void iVisitWebSiteAsRegisteredUserWithOrganicRegistrationSUPCInWallet() throws Throwable {

       // pageNavigation.I_navigate_to_create_profile_page();
       // myAccountSteps.iCreateANewProfile();
        CreateProfile.createProfileUsingServiceAndLogin();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        new Wallet().AddOrganicSUPCInWallet(1);
    }

    @And("^I adjust qty to make product eligible for SUPC$")
    public void iAdjustQtyToMakeProductEligibleForSUPC() throws Throwable {

        Wait.forPageReady();
        DropDowns.selectByText("shopping_bag.quantities", "2");
        Wait.forPageReady();
    }

    @And("^I should see 'Not A Loyallist, Earn rewards when you shop!, Become a Loyallist link, It's easy It's free! text' in the loyallist tile section$")
    public void iShouldSeeNotALoyallistEarnRewards() {
        assertTrue("Not A Loyallist? text is not displayed in My Account page Loyallist tile", Elements.getText("my_account.not_a_loyallist_text").equals("Not a Loyallist?"));
        assertTrue("Dog image is not displayed in My Account page Loyallist tile", Elements.elementPresent("my_account.loyallist_dog_image"));
        assertTrue("Earn rewards when you shop! text is not displayed in My Account page Loyallist tile", Elements.getText("my_account.earn_rewards_when_you_shop").equals("Earn rewards when you shop!"));
        assertTrue("Become a Loyallist link is not displayed in My Account page Loyallist tile", Elements.getText("my_account.myaccount_loyalty_become_a_loyallist_link").equals("Become a Loyallist"));
        assertTrue("It's easy. It's free! text is not displayed in My Account page Loyallist tile", Elements.getText("my_account.its_easy_its_free").equals("It's easy. It's free!"));
        assertTrue("SAVE YOUR LOYALLIST NUMBER TO YOUR ONLINE ACCOUNT link is not displayed in My Account page Loyallist tile", Elements.getText("my_account.add_loyalty_number").equals("SAVE YOUR LOYALLIST NUMBER TO YOUR ONLINE ACCOUNT"));
    }

    @Then("^I should see 'SAVE YOUR LOYALLIST NUMBER TO YOUR ONLINE ACCOUNT' link is taking me to loyalty account association page$")
    public void iShouldSeeSaveYourLoyallistNumberToYourOnlineAccountLinkGosToAssociatePage() throws Throwable {
        Clicks.click("my_account.add_loyalty_number");
        Wait.forPageReady();
        Thread.sleep(2000);
        shouldBeOnPage("loyalty_association");
    }

}
