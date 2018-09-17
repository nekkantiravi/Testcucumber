package com.macys.sdt.projects.Marketing.OCWallet.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.LoyaltyServiceUtil;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.shared.actions.MEW.pages.MyWallet;
import com.macys.sdt.shared.steps.MEW.Home;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.steps.MEW.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.macys.sdt.framework.utils.TestUsers.getValidVisaCreditCard;
import static com.macys.sdt.projects.Marketing.OCWallet.actions.MEW.mcom.pages.OCWallet.updateCreditCard;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OCWallet extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
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
    private static UserProfile profile;


    @When("^I tap on 'Add A Credit Card' button in wallet landing page$")
    public void iTapOnAddACreditCardButton() throws Throwable {
        Clicks.click("oc_my_wallet.add_credit_card");
        Wait.forPageReady();
    }

    @Then("^I should be navigated to the \"add credit card\" modal$")
    public void iShouldBeNavigatedToTheAddCreditCardModal() {
        assertTrue("Credit Cards Overlay not opened when clicked on Add A Credit Card button", Elements.elementPresent("oc_my_wallet.add_credit_card_modal"));
    }

    @Then("^the 'select as my default card' switch should be set to 'on' and 'disabled'$")
    public void theSelectAsMyDefaultCardSetToOnAndDisabled() {
        Wait.secondsUntilElementPresent("credit_card.set_as_my_default_card_on", 3);
        assertTrue("'Set as my default card' switch is not set to On by default.", Elements.getElementAttribute("credit_card.set_as_my_default_card_on", "aria-pressed").equals("active"));
        assertTrue("'Set as my default card' switch is not disabled.", Elements.getElementAttribute("credit_card.set_as_my_default_card_off", "class").contains("disable"));
    }

    @Given("^I visit the mobile web site as a (guest|registered) user with no credit cards$")
    public void iVisitTheMobileWebSiteAsARegisteredUserWithNoCreditCards(String userType) throws Throwable {
      Home.iVisitTheMobileWebHomePage();

        if (userType.equals("registered")) {
            CommonUtils.signInOrCreateAccount();
            if (!prodEnv()) {
                HashMap<String, String> options = new HashMap<>();
                options.put("checkout_eligible", "true");
                ProfileAddress profileAddress = new ProfileAddress();
                TestUsers.getRandomValidAddress(options, profileAddress);
                TestUsers.getCustomer(null).getUser().setProfileAddress(profileAddress);
            }
            if (safari()) {
                new com.macys.sdt.shared.steps.website.ShopAndBrowse().i_remove_all_items_from_the_shopping_bag();
            }
            Navigate.visit("home");
        }
        Cookies.disableForeseeSurvey();
    }


    @Given("^I am on wallet landing page$")
    public void iAmOnWalletlandingPage() throws Throwable {
        List<String> navLink = new ArrayList<String>();
        navLink.add("WALLET");
        iVisitTheMobileWebSiteAsARegisteredUserWithNoCreditCards("registered");
        pageNavigation.I_navigate_the_global_navigation_menu_as_follows(navLink);

    }

    @When("^I navigate to wallet page$")
    public void iNavigateToWalletPageAsASignedInUser() throws Throwable {
        List<String> navLink = new ArrayList<String>();
        navLink.add("WALLET");
        pageNavigation.I_navigate_the_global_navigation_menu_as_follows(navLink);
    }

    @Then("^I should be able to see and select the following card types$")
    public void iShouldBeAbleToSeeAndSelectTheFollowingCardTypes(List<String> expectedCardTypes) {
//        List<String> expectedCardTypes = new ArrayList<String>(
//                Arrays.asList("Card Type", "Macy's", "Macy's American Express", "American Express",
//                        "Visa", "MasterCard", "Discover", "Macy's Employee Card"));
        List<String> actCardTypes = new ArrayList<String>();
        List<WebElement> cardTypes = Elements.findElements("credit_card.card_type_options");
        for (WebElement cardType : cardTypes) {
            actCardTypes.add(cardType.getText());
        }
        assertTrue("All the expected Card Types did not match with Actual Card Types displayed. " +
                        "Expected: " + expectedCardTypes + " Actual Card Types: " + actCardTypes,
                actCardTypes.equals(expectedCardTypes));

    }

    @Given("^I visit the mobile web home page as a signed in user$")
    public void iVisitTheMobileWebHomeAsSignedInUser() throws Throwable {
        iVisitTheMobileWebSiteAsARegisteredUserWithNoCreditCards("registered");
    }

    @When("^I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet$")
    public void iNavigateToWalletPageAsASignedInUserWithVisaCreditCardInTheWallet() throws Throwable {
        pageNavigation.I_navigate_the_global_navigation_menu_as_follows(Arrays.asList("WALLET"));
        iAddTheCardInTheAddCreditCardModal("Visa");
    }

    @Then("^the 'select as my default card' switch should be set to 'off' and 'enabled'$")
    public void theSelectAsMyDefaultCardSwitchOffAndEnabled() {
        Wait.secondsUntilElementPresent("credit_card.set_as_my_default_card_on", 3);
        assertTrue("'Set as my default card' switch is not set to Off by default.", Elements.findElement("credit_card.set_as_my_default_card_off").getAttribute("aria-pressed").equals("active"));
        assertTrue("'Set as my default card' switch is not active.", Elements.findElement("credit_card.set_as_my_default_card_off").getAttribute("class").contains("active"));
    }

    @When("^I add the \"([^\"]*)\" card from add credit card modal$")
    public void iAddTheCardInTheAddCreditCardModal(String cardType) throws Throwable {
        CreditCard creditCard = Wallet.getValidCreditCard(cardType);
        profile = TestUsers.getCustomer(null);
        CommonUtils.addCreditCardFromBWallet(creditCard, profile);
        cardsAdded.add(creditCard);
    }


    @Then("^I should see the credit card \"([^\"]*)\" saved in the wallet$")
    public void iShouldSeeTheCreditCardSavedInTheWallet(String cardType) {
        List<CreditCard> walletCards;
        String actCardType;
        walletCards = MyWallet.getCardsInWallet();
        actCardType = walletCards.get(0).getCardType().name.equalsIgnoreCase("Employee Card") ? "Macy's Employee Card" : walletCards.get(0).getCardType().name;
        assertTrue("Credit Card - " + cardType + " is not displayed on 'Wallet - My Credit Cards' Section after adding the Credit Card.", actCardType.equalsIgnoreCase(cardType));
    }

    @Then("^the State drop down field should contain the following additional states in alpahbetical order:$")
    public void theStateDropDownFieldShouldContainTheFollowingStates(List<String> expStates) throws Throwable {
        List<String> actStates = new ArrayList<String>();
        List<WebElement> actStateElements = Elements.findElements("credit_card.state_options");
        for (WebElement state : actStateElements) {
            actStates.add(state.getText());
        }
        assertTrue("Expected states are not displayed in Add Credit Card State dropdown list. Expected States: " + expStates + " Actual States: " + actStates, actStates.containsAll(expStates));
    }

    @When("^I enter the 'Visa' card details in the add credit card modal$")
    public void iEnterTheVisaCard() {
        CreditCard creditCard = getValidVisaCreditCard();
        enterCreditCardInfo(creditCard, null);
    }

    public void enterCreditCardInfo(CreditCard creditCard, UserProfile customer) {
        String cardType;
        creditCard = creditCard == null ? getValidVisaCreditCard() : creditCard;
        customer = customer == null ? TestUsers.getCustomer(null) : customer;
        int year = 5;
        int month = 4;

        Wait.untilElementPresent("oc_my_wallet.add_credit_card");
        try {
            Elements.elementInView("oc_my_wallet.add_credit_card");
            Clicks.click("oc_my_wallet.add_credit_card");
        } catch (Exception | Error e) {
            Navigate.browserRefresh();
            Utils.threadSleep(2000, null);
            Wait.untilElementPresent("oc_my_wallet.add_credit_card");
            Clicks.click("oc_my_wallet.add_credit_card");
        }
        Wait.untilElementPresent("credit_card.card_type");
        cardType = creditCard.getCardType().name;
        DropDowns.selectByText("credit_card.card_type", cardType);
        TextBoxes.typeTextbox("credit_card.card_number", creditCard.getCardNumber());
        DropDowns.selectByText("credit_card.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
        DropDowns.selectByText("credit_card.expiry_year", creditCard.getExpiryYear());
        TextBoxes.typeTextbox("credit_card.first_name", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("credit_card.last_name", customer.getUser().getProfileAddress().getLastName());
        TextBoxes.typeTextbox("credit_card.address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("credit_card.address_line_2", customer.getUser().getProfileAddress().getAddressLine2() == null ? "" : customer.getUser().getProfileAddress().getAddressLine2());
        TextBoxes.typeTextbox("credit_card.address_city", customer.getUser().getProfileAddress().getCity());
        DropDowns.selectByValue("credit_card.address_state", AbbreviationHelper.getStateAbbreviation(customer.getUser().getProfileAddress().getState()));
        TextBoxes.typeTextbox("credit_card.address_zip_code", String.valueOf(customer.getUser().getProfileAddress().getZipCode()));
        if (bloomingdales() || onPage("shipping_payment_signed_in")) {
            TextBoxes.typeTextbox("credit_card.card_phone_area_code", customer.getUser().getProfileAddress().getPhoneAreaCode());
            TextBoxes.typeTextbox("credit_card.card_phone_exchange", customer.getUser().getProfileAddress().getPhoneExchange() == null ? "" : customer.getUser().getProfileAddress().getPhoneExchange());
            TextBoxes.typeTextbox("credit_card.card_phone_subscriber", customer.getUser().getProfileAddress().getPhoneSubscriber() == null ? "" : customer.getUser().getProfileAddress().getPhoneSubscriber());
        } else {
            TextBoxes.typeTextbox("credit_card.phone_number", customer.getUser().getProfileAddress().getBestPhone());
        }
        TextBoxes.typeTextbox("credit_card.payment_email", customer.getUser().getProfileAddress().getEmail());
    }

    @And("^I tap on 'back' button in the (add|edit) credit card modal$")
    public void iTapOnBackButtonInTheAddCreditCardModal(String addOrEdit) {
        Clicks.click("credit_card.back_button");
        Wait.forPageReady();
    }

    @Then("^I should not see the credit card saved in the wallet$")
    public void iShouldNotSeeTheCreditCardSavedInTheWallet() {
        assertFalse("Credit Card is added even after clicking on Back button on Add Credit Card modal.", Elements.elementPresent("oc_my_wallet.credit_card_data"));
    }

    @When("^I navigate to wallet page as a signed in user with \"([^\"]*)\" credit card in the wallet$")
    public void iNavigateToWalletPageAsASignedInUserWithCardTypeInTheWallet(String cardType) throws Throwable {
        List<String> navLink = new ArrayList<String>(Arrays.asList("WALLET"));
        pageNavigation.I_navigate_the_global_navigation_menu_as_follows(navLink);
        Wait.forPageReady();
        iTapOnAddACreditCardButton();
//        CreditCard creditCard = Wallet.getValidCreditCard(cardType);
        iAddTheCardInTheAddCreditCardModal(cardType);
    }

    @And("^I tap on the \"([^\"]*)\" credit card cell$")
    public void iTapOnTheCardTypeCell(String cardType) throws Throwable {
        List<WebElement> cardElements = null;
        String actCardType;
        boolean cardFoundOnWallet = false;
        cardElements = Elements.findElements("oc_my_wallet.credit_card_data");
        for (WebElement card : cardElements) {

            String cardDetails = card.getText();
            String[] splitCardDetails = cardDetails.split("\\r?\\n");
            int index = splitCardDetails[0].length();
            if (splitCardDetails[0].contains("®")) {
                actCardType = splitCardDetails[0].substring(0, index - 1);

            } else {
                actCardType = splitCardDetails[0].substring(0, index);
            }
//            actCardType = actCardType.equalsIgnoreCase("Macy's Employee Card") ? "Employee Card" : actCardType;
            actCardType = actCardType.equalsIgnoreCase("American Express Card") ? "American Express" : actCardType;
            if (actCardType.equals(cardType)) {
                cardFoundOnWallet = true;
                Thread.sleep(3000);
                card.click();
                Wait.secondsUntilElementPresent("credit_card.container", 3);
                break;
            }
        }
        assertTrue("Card Type " + cardType + " was not added on Wallet page.", cardFoundOnWallet);
    }

    @Then("^I should be navigated to the \"edit credit card\" modal$")
    public void iShouldBeNavigatedToTheEditCreditCardModal() {
        assertTrue("'Edit Crdit Card' modal is not displayed after clicking on Edit button.", Elements.elementPresent("credit_card.container"));
    }

    @Then("^I should see the credit card fields populated with my data$")
    public void iShouldSeeTheCreditCardFieldsPopulatedWithMyData() {
        CreditCard cardDetails = cardsAdded.get(0);
        verifyUpdatedCreditCardDetails(cardDetails);

    }

    public void verifyUpdatedCreditCardDetails(CreditCard cardDetails) {

        Wait.secondsUntilElementPresent("credit_card.card_type_selected", 3);
        String actCardType = Elements.getText("credit_card.card_type_selected");
        String expCardType = cardDetails.getCardType().name.equals("Employee Card") ? "Macy's Employee Card" : cardDetails.getCardType().name;
        assertTrue("Card Type displayed on Edit Credit Card modal " + actCardType + " is not matching with expected Card Type" + expCardType, expCardType.equals(actCardType));
        String actCardNumber = Elements.findElement("credit_card.card_number").getAttribute("value");
        actCardNumber = actCardNumber.substring(actCardNumber.length() - 4);
        assertTrue("Card Number displayed on Edit Credit Card modal " + actCardNumber + " is not matching with expected Card Number" + cardDetails.getCardNumber(), cardDetails.getCardNumber().substring(cardDetails.getCardNumber().length() - 4).equals(actCardNumber));
        String expExpirationMonth = cardDetails.getExpiryMonthIndex() + " - " + cardDetails.getExpiryMonth();
        String actExpirationMonth = Elements.getText("credit_card.expiry_month_selected");
        String actExpirationYear = Elements.getText("credit_card.expiry_year_selected");
        if (!cardDetails.getExpiryMonthIndex().equals("")) {
            assertTrue("Expiration Month displayed on Edit Credit Card modal " + actExpirationMonth + " is not matching with expected Expiration Month." + expExpirationMonth, expExpirationMonth.equals(actExpirationMonth));
            assertTrue("Expiration Year displayed on Edit Credit Card modal " + actExpirationYear + " is not matching with expected Expiration Month." + cardDetails.getExpiryYear(), cardDetails.getExpiryYear().equals(actExpirationYear));
        }
        String actFirstName = Elements.findElement("credit_card.first_name").getAttribute("value");
        assertTrue("First Name displayed on Edit Credit Card modal " + actFirstName + " is not matching with expected First Name." + profile.getUser().getProfileAddress().getFirstName(), profile.getUser().getProfileAddress().getFirstName().equals(actFirstName));
        String actLastName = Elements.findElement("credit_card.last_name").getAttribute("value");
        assertTrue("Last Name displayed on Edit Credit Card modal " + actLastName + " is not matching with expected Last Name." + profile.getUser().getProfileAddress().getLastName(), profile.getUser().getProfileAddress().getLastName().equals(actLastName));
        String actAddressLine1 = Elements.findElement("credit_card.address_line_1").getAttribute("value");
        assertTrue("Address Line1 displayed on Edit Credit Card modal " + actAddressLine1 + " is not matching with expected Address Line1." + profile.getUser().getProfileAddress().getAddressLine1(), profile.getUser().getProfileAddress().getAddressLine1().equals(actAddressLine1));
        String actAddressLine2 = Elements.findElement("credit_card.address_line_2").getAttribute("value");
        String expAddressLine2 = profile.getUser().getProfileAddress().getAddressLine2() == null ? "" : profile.getUser().getProfileAddress().getAddressLine2();
        assertTrue("Address Line2 displayed on Edit Credit Card modal " + actAddressLine2 + " is not matching with expected Address Line2." + expAddressLine2, expAddressLine2.equals(actAddressLine2));
        String actCity = Elements.findElement("credit_card.address_city").getAttribute("value");
        String expCity = profile.getUser().getProfileAddress().getCity();
        assertTrue("City displayed on Edit Credit Card modal " + actCity + " is not matching with expected City." + expCity, expCity.equals(actCity));
        String actState = Elements.getText("credit_card.state_selected");
        String expState = AbbreviationHelper.translateStateAbbreviation(profile.getUser().getProfileAddress().getState());
        assertTrue("State displayed on Edit Credit Card modal " + actState + " is not matching with expected State." + expState, expState.equals(actState));
        String actZip = Elements.findElement("credit_card.address_zip_code").getAttribute("value");
        String expZip = profile.getUser().getProfileAddress().getZipCode();
        assertTrue("Zip Code displayed on Edit Credit Card modal " + actZip + " is not matching with expected Zip Code." + expZip, expZip.equals(actZip));
        String actEmail = Elements.findElement("credit_card.email").getAttribute("value");
        String expEmail = profile.getUser().getProfileAddress().getEmail();
        assertTrue("Zip Code displayed on Edit Credit Card modal " + actEmail + " is not matching with expected Zip Code." + expEmail, expEmail.equals(actEmail));
    }

    @When("^I enter \"9999999999\" into the phone number field$")
    public void iEnterNumberInfoThePhoneNumberField() {
        TextBoxes.typeTextbox("credit_card.phone_number", "9999999999");
    }

    @Then("^I should not see '999 999-9999' in the phone number field$")
    public void iShouldNotSeeNumberInThePhoneNumberField() {
        String actPhone = Elements.findElement("credit_card.phone_number").getAttribute("value");
        assertFalse("Phone number was updated even though user clicked on Back button without saving the changes.", actPhone.equals("(999) 999-9999"));
    }

    @When("I successfully update the card details with \"([^\"]*)\" in the edit credit card modal")
    public void iUpdateTheCardDetailsWithDiscoverInTheEditCreditCard(String cardType) {
        CreditCard creditCard = Wallet.getValidCreditCard(cardType);
        profile = TestUsers.getCustomer(null);
        ProfileAddress newProfileAddress = profile.getUser().getProfileAddress();
        newProfileAddress.setFirstName(newProfileAddress.getFirstName() + "NEW");
        profile.getUser().setProfileAddress(newProfileAddress);
        updateCreditCard(creditCard, profile);
        cardsAdded.add(creditCard);
    }

    @Then("I should see wallet landing page")
    public void iShouldSeeWalletLandingPage() {
        assertTrue("Failed to navigate back to Wallet page after saving the credit card changes", onPage("oc_my_wallet"));
    }

    @And("^I should see the credit card data displayed that I previously updated$")
    public void iShouldSeeTheCreditCardDataAsUpdated() {
        CreditCard updatedCreditCard = cardsAdded.get(1);
        verifyUpdatedCreditCardDetails(updatedCreditCard);
        String expFirstName = profile.getUser().getProfileAddress().getFirstName();
        String actFirstName = Elements.findElement("credit_card.first_name").getAttribute("value");
        assertTrue("Profile Details are not updated as per the changes saved. Expected First Name " + expFirstName + " Actual First Name displayed. " + actFirstName, expFirstName.equals(actFirstName));
    }

    @When("^I edited the card details with \"([^\"]*)\" in the edit credit card modal$")
    public void iEditedTheCardDetailsWithNewCardInEditCreditCardModal(String cardType) throws Throwable {
        Wait.untilElementPresent("credit_card.card_type");
        DropDowns.selectByText("credit_card.card_type", cardType);
        Thread.sleep(3000);
    }

    @Then("^expiration date should be hidden$")
    public void expirationDateShouldBeHidden() {
        assertTrue("Card Expiration Section is still shown for Card Type Macy's and 'Employee Card'", Elements.findElement("credit_card.card_expiration_section").getAttribute("class").contains("hide"));
    }

    @When("^I add \"([^\"]*)\" card to the wallet$")
    public void iAddCardToTheWallet(String cardType) throws Throwable {
        iAddTheCardInTheAddCreditCardModal(cardType);
    }

    @Then("^the \"select as my default card\" switch should be set to \"off\" and \"enabled\"$")
    public void theSelectAsMyDefaultCardSwitchShouldBeSetToOffAndEnabled() {
        Wait.secondsUntilElementPresent("credit_card.set_as_my_default_card_on", 3);
        assertTrue("'Set as my default card' switch is not set to Off by default for the non default card.", Elements.getElementAttribute("credit_card.set_as_my_default_card_off", "aria-pressed").equals("active"));
        assertTrue("'Set as my default card' switch is not active for the non default card.", Elements.getElementAttribute("credit_card.set_as_my_default_card_off", "class").contains("active"));
    }

    @When("^I tap the 'delete card' button in the edit credit card modal$")
    public void iTapTheDeleteCardButtonInTheEditCreditCardModal() throws Throwable {
        Wait.secondsUntilElementPresent("credit_card.delete_card", 3);
        Thread.sleep(1000);
        Elements.findElement("credit_card.delete_card").click();
        Thread.sleep(1000);
//        Actions action = new Actions(WebDriverManager.getWebDriver());
//        action.moveToElement(Elements.findElement("credit_card.delete_card")).click().perform();
//        Wait.secondsUntilElementPresent("credit_card.confirmation_overlay",3);
    }

    @Then("^I see the confirmation message as 'Are you sure you want to delete this credit card\\?'$")
    public void iSeeTheConfirmationMessageAsAreYouSureYouWantToDeleteThisCreditCard() {
        assertTrue("Expected confirmation message - 'Are you sure you want to delete this credit card?' is not displayed.", Elements.getText("credit_card.confirmation_message").equals("Are you sure you want to delete this credit card?"));
    }

    @When("^I click on the 'no' button on the delete credit card confirmation message$")
    public void iClickOnTheNoButtonOnTheDeleteCreditCardConfirmationMessage() {
        Elements.findElement("credit_card.confirmation_no_button").click();
        Wait.forPageReady();
    }

    @When("^I click on the 'yes' button on the delete credit card confirmation message$")
    public void iClickOnTheYesButtonOnTheDeleteCreditCardPopup() throws Throwable {
        Elements.findElement("credit_card.confirmation_yes_button").click();
        Thread.sleep(1000);
    }

    @And("^I should see 'Discover' card labelled as my default card$")
    public void iShouldSeeDiscoverCardLabelledAsMyDefaultCard() throws Throwable {
        List<WebElement> cardElements = null;
        String actCardType;
        boolean isDefaultCard = false;
        cardElements = Elements.findElements("oc_my_wallet.credit_card_data");
        for (WebElement card : cardElements) {

            String cardDetails = card.getText();
            String[] splitCardDetails = cardDetails.split("\\r?\\n");
            int index = splitCardDetails[0].length();
            actCardType = splitCardDetails[0].substring(0, index - 1);
            if (actCardType.equals("Discover")) {
                if (splitCardDetails[2].equals("Default Card")) {
                    isDefaultCard = true;
                }
                break;
            }
        }
        assertTrue("Discover card is not set as Default Card after deleting the Visa card.", isDefaultCard);
    }

    @Then("^I expect CC error message after I enter details, changing one value:$")
    public void iExpectCCErrorMessageAfterIEnterDetailsAndChangingOneValue(DataTable errorValidations) throws Throwable {
        for (Map<String, String> validationMap : errorValidations.asMaps(String.class, String.class)) {
            CreditCard creditCard = getValidVisaCreditCard();
            UserProfile customer = TestUsers.getCustomer(null);
            String expErrorMessage = validationMap.get("message");
            String field = validationMap.get("detail");
            String inputValue = validationMap.get("value");
            Wait.forPageReady();
            System.out.println("field: " + field + "data: " + inputValue);
//            enterCreditCardInfo(creditCard);
            Wait.secondsUntilElementPresent("credit_card.card_type", 3);
            String oldFNValue = customer.getUser().getProfileAddress().getFirstName();
            String oldLNValue = customer.getUser().getProfileAddress().getLastName();
            String oldALValue = customer.getUser().getProfileAddress().getAddressLine1();
            String oldCityValue = customer.getUser().getProfileAddress().getCity();
            String oldZipValue = customer.getUser().getProfileAddress().getZipCode();
            String oldEmailValue = customer.getUser().getProfileAddress().getZipCode();

            switch (field) {
                case "First Name":
                    customer.getUser().getProfileAddress().setFirstName(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setFirstName(oldFNValue);
                    break;
                case "Last Name":
                    customer.getUser().getProfileAddress().setLastName(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setLastName(oldLNValue);
                    break;
                case "Address 1":
                    customer.getUser().getProfileAddress().setAddressLine1(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setAddressLine1(oldALValue);
                    break;
                case "City":
                    customer.getUser().getProfileAddress().setCity(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setCity(oldCityValue);
                    break;
                case "State":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.address_state", "State");
                    break;
                case "Zip Code":
                    customer.getUser().getProfileAddress().setZipCode(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setZipCode(oldZipValue);
                    break;
                case "Phone":
                    enterCreditCardInfo(creditCard, customer);
                    TextBoxes.typeTextbox("credit_card.phone_number", inputValue);
                    break;
                case "Email Address":
                    customer.getUser().getProfileAddress().setEmail(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setZipCode(oldEmailValue);
                    break;
                case "Card Type":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.card_type", "Card Type");
                    break;
                case "month":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.expiry_month_number", "Month");
                    break;
                case "year":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.expiry_year", "Year");
                    break;
                case "Card Number":
                    creditCard.setCardNumber(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    Wait.forPageReady();
                    break;
            }
//            Thread.sleep(1000);
            Clicks.click("credit_card.save_card");
            Wait.forPageReady();
            String actErrorMsg = Elements.getText("credit_card.error_message");
            assertTrue("Expected error message '" + expErrorMessage + "' for value " + inputValue + " is not displayed on Add Credit Card modal. Actual Error Message " + actErrorMsg, actErrorMsg.equals(expErrorMessage));
            Clicks.click("credit_card.back_button");
            Thread.sleep(500);
            iTapOnAddACreditCardButton();
            Thread.sleep(1000);
        }


    }

    @When("^I enter details, changing two values: \"month\" to \"1\" and \"year\" to \"2016\"$")
    public void iEnterOldExpirationDate() {
        iEnterTheVisaCard();
        DropDowns.selectByIndex("credit_card.expiry_month_number", 1);
        DropDowns.selectByIndex("credit_card.expiry_year", 1);
    }

    @Then("^I tap on 'save' button in the add credit card modal, expecting the general error message \"Please enter a valid expiration date.\"$")
    public void iSaveAndVerifyErrorMessage() {
        Clicks.click("credit_card.save_card");
        Wait.forPageReady();
        String actErrorMsg = Elements.getText("credit_card.error_message");
        String expErrorMessage = "Please enter a valid expiration date.";
        assertTrue("Expected error message '" + expErrorMessage + " is not displayed on Add Credit Card modal. Actual Error Message " + actErrorMsg, actErrorMsg.equals(expErrorMessage));
    }

    @When("^I enter details, changing two values: \"Card Number\" to \"\" and \"First Name\" to \"\"$")
    public void iEnterBlankCardNumberAndFirstName() {
        iEnterTheVisaCard();
        TextBoxes.typeTextbox("credit_card.card_number", "");
        TextBoxes.typeTextbox("credit_card.first_name", "");
    }

    @Then("^I tap on 'save' button in the add credit card modal, expecting the general error message \"Please correct the highlighted areas to proceed.\"$")
    public void iTapOnSaveButtonToValidateErrorMessage() {
        Clicks.click("credit_card.save_card");
        Wait.forPageReady();
        String actErrorMsg = Elements.getText("credit_card.error_message");
        String expErrorMessage = "Please correct the highlighted areas to proceed.";
        assertTrue("Expected error message '" + expErrorMessage + " is not displayed on Add Credit Card modal. Actual Error Message " + actErrorMsg, actErrorMsg.equals(expErrorMessage));
    }

    @Then("^I expect CC error message after I modify details, changing one value:$")
    public void iExpectCCErrorMessageAfterModifyDetails(DataTable errorValidations) throws Throwable {
        for (Map<String, String> validationMap : errorValidations.asMaps(String.class, String.class)) {
            CreditCard creditCard = getValidVisaCreditCard();
            UserProfile customer = TestUsers.getCustomer(null);
            String expErrorMessage = validationMap.get("message");
            String field = validationMap.get("detail");
            String inputValue = validationMap.get("value");
            Wait.forPageReady();
            System.out.println("field: " + field + "data: " + inputValue);
            Wait.secondsUntilElementPresent("credit_card.card_type", 3);
            String oldFNValue = customer.getUser().getProfileAddress().getFirstName();
            String oldLNValue = customer.getUser().getProfileAddress().getLastName();
            String oldALValue = customer.getUser().getProfileAddress().getAddressLine1();
            String oldCityValue = customer.getUser().getProfileAddress().getCity();
            String oldZipValue = customer.getUser().getProfileAddress().getZipCode();
            String oldEmailValue = customer.getUser().getProfileAddress().getZipCode();

            switch (field) {
                case "First Name":
                    customer.getUser().getProfileAddress().setFirstName(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setFirstName(oldFNValue);
                    break;
                case "Last Name":
                    customer.getUser().getProfileAddress().setLastName(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setLastName(oldLNValue);
                    break;
                case "Address 1":
                    customer.getUser().getProfileAddress().setAddressLine1(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setAddressLine1(oldALValue);
                    break;
                case "City":
                    customer.getUser().getProfileAddress().setCity(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setCity(oldCityValue);
                    break;
                case "State":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.address_state", "State");
                    break;
                case "Zip Code":
                    customer.getUser().getProfileAddress().setZipCode(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setZipCode(oldZipValue);
                    break;
                case "Phone":
                    enterCreditCardInfo(creditCard, customer);
                    TextBoxes.typeTextbox("credit_card.phone_number", inputValue);
                    break;
                case "Email Address":
                    customer.getUser().getProfileAddress().setEmail(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    customer.getUser().getProfileAddress().setZipCode(oldEmailValue);
                    break;
                case "Card Type":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.card_type", "Card Type");
                    break;
                case "month":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.expiry_month_number", "Month");
                    break;
                case "year":
                    enterCreditCardInfo(creditCard, customer);
                    DropDowns.selectByText("credit_card.expiry_year", "Year");
                    break;
                case "Card Number":
                    creditCard.setCardNumber(inputValue);
                    enterCreditCardInfo(creditCard, customer);
                    Wait.forPageReady();
                    break;
            }
//            Thread.sleep(1000);
            Clicks.click("credit_card.apply_changes");
            Wait.forPageReady();
            String actErrorMsg = Elements.getText("credit_card.error_message");
            assertTrue("Expected error message '" + expErrorMessage + "' for value " + inputValue + " is not displayed on Add Credit Card modal. Actual Error Message " + actErrorMsg, actErrorMsg.equals(expErrorMessage));
            Clicks.click("credit_card.back_button");
            Thread.sleep(500);
            iTapOnTheCardTypeCell("Visa");
            Thread.sleep(1000);
        }
    }

    @When("^I edit details, changing two values: \"month\" to \"1\" and \"year\" to \"2016\"$")
    public void iEditCardDetailsToExpiredDate() {
        DropDowns.selectByIndex("credit_card.expiry_month_number", 1);
        DropDowns.selectByIndex("credit_card.expiry_year", 1);
    }

    @Then("^I tap on 'apply' button in the add credit card modal, expecting the cc error message \"Please enter a valid expiration date.\"$")
    public void iTapOnApplyToValidateErrorMessage() {
        Clicks.click("credit_card.apply_changes");
        Wait.forPageReady();
        String actErrorMsg = Elements.getText("credit_card.error_message");
        String expErrorMessage = "Please enter a valid expiration date.";
        assertTrue("Expected error message '" + expErrorMessage + " is not displayed on Add Credit Card modal. Actual Error Message " + actErrorMsg, actErrorMsg.equals(expErrorMessage));

    }

    @Then("^I should see the value in the credit card number as masked$")
    public void iShouldSeeTheValueInTheCreditCardNumberAsMasked() {
        String actMarkedCardNumber = Elements.findElement("credit_card.card_number").getAttribute("value");
        assertTrue("Card Number is not masked as expected, when there is an error message displayed", actMarkedCardNumber.substring(0, 4).equals("****"));
    }

    @And("^I select the credit card field$")
    public void iSelectTheCreditCardField() {
        Clicks.click("credit_card.card_number");
        Wait.forPageReady();
    }

    @Then("I should see the credit card number as masked")
    public void iShouldSeeTheCreditCardNumbers() {
        String expCardNumber = "****" + cardsAdded.get(0).getCardNumber().substring(12, 16);
        String actCardNumber = Elements.findElement("credit_card.card_number").getAttribute("value");
        assertTrue("Full Card Number is not displayed while editing the credit card.", expCardNumber.equals(actCardNumber));
    }

    @And("I should see \"Use my profile address\" is switched off")
    public void iShouldSeeUseMyProfileAddressIsSwitcheOff() {
        assertTrue("'Set as my default card' switch is not set to On by default.", Elements.getElementAttribute("credit_card.use_my_profile_address_off", "aria-pressed").equals("active"));
    }

    @When("I turn \"Use my profile address\" ON")
    public void iTurnUseMyProfileAddressOn() {
        Clicks.click("credit_card.use_my_profile_address_on");
        Wait.forPageReady();
    }

    @Then("I should see my \"billing address\" fields populated from the profile address")
    public void iShouldSeeMyBillingAddressFieldsPopulated() {
        assertFalse("Billing Address fields are not populated when clicked on 'Use my profile address' switch.",
                Elements.findElement("credit_card.first_name").getAttribute("value").equals("") || Elements.findElement("credit_card.last_name").getAttribute("value").equals(""));
    }
}
