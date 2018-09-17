package com.macys.sdt.projects.PurchaseAndDelivery.CheckoutOptimizationLT.steps.website;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.element.Select;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.GuestCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.MyAddressBookPage;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.actions.website.bcom.pages.CheckoutPageBcom;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.utils.CheckoutUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by rishi shukla on 02/20/16.
 */
public class CheckoutSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutSteps.class);
    private final String SIBLING_XPATH_BY_SMALL_CSS_CLASS = "following-sibling::small";
    private String INVALID_VALUE_MSG = GuestCheckout.INVALID_VALUE_MSG;

    @Then("^validate the shipping section for required validation messages$")
    public void validateTheShippingSectionForRequiredValidationMessages() throws Throwable {
        ShippingSection shippingSection = Navigate.get(ShippingSection.class);

        WebElement errorMsg = shippingSection.firstName.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.FIRST_NAME_REQ_MSG, errorMsg.getText());

        errorMsg = shippingSection.lastName.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.LAST_NAME_REG_MSG, errorMsg.getText());

        errorMsg = shippingSection.addressLine1.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.ADDRESS_LINE_1_REQ_MSG, errorMsg.getText());

        List<WebElement> addressLine2Elements = shippingSection.addressLine2
                .findElements(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertTrue(addressLine2Elements.isEmpty());

        errorMsg = shippingSection.city.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.CITY_REG_MSG, errorMsg.getText());

        errorMsg = shippingSection.state.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.STATE_REQ_MSG, errorMsg.getText());

        errorMsg = shippingSection.zipCode.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.ZIP_REQ_MSG, errorMsg.getText());

        errorMsg = shippingSection.phone.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.PHONE_REQ_MSG, errorMsg.getText());
    }


    @Then("^fill the shipping section for invalid value validation messages$")
    public void fillTheShippingSectionForInvalidValueValidationMessages() {
        ShippingSection shippingSection = Navigate.get(ShippingSection.class);

        TextInput firstName = shippingSection.firstName;
        TextInput lastName = shippingSection.lastName;
        TextInput addressLine1 = shippingSection.addressLine1;
        TextInput addressLine2 = shippingSection.addressLine2;
        TextInput city = shippingSection.city;
        Select state = shippingSection.state;
        TextInput phoneNumber = shippingSection.phone;

        firstName.sendKeys(GuestCheckout.FIRST_NAME_WRONG_VALUE);
        lastName.sendKeys(GuestCheckout.LAST_NAME_WRONG_VALUE);
        addressLine1.sendKeys(GuestCheckout.ADDRESS_LINE_1_WRONG_VALUE);
        addressLine2.sendKeys(GuestCheckout.ADDRESS_LINE_2_WRONG_VALUE);
        city.sendKeys(GuestCheckout.CITY_WRONG_VALUE);
        phoneNumber.sendKeys(GuestCheckout.PHONE_WRONG_VALUE);
        state.sendKeys(GuestCheckout.STATE_VALUE);

        WebElement errorMsg = firstName.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(INVALID_VALUE_MSG, errorMsg.getText());

        errorMsg = lastName.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(INVALID_VALUE_MSG, errorMsg.getText());

        errorMsg = addressLine1.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(INVALID_VALUE_MSG, errorMsg.getText());

        errorMsg = addressLine2.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(INVALID_VALUE_MSG, errorMsg.getText());

        errorMsg = city.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(INVALID_VALUE_MSG, errorMsg.getText());

        errorMsg = phoneNumber.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.PHONE_INVALID_VAL_MSG, errorMsg.getText());
    }

    @Then("^fill the shipping section with valid address$")
    public void fillTheShippingSectionForValidAddress() {
        ShippingSection shippingSection = Navigate.get(ShippingSection.class);

        shippingSection.firstName.sendKeys("Dummy");
        shippingSection.lastName.sendKeys("D");
        shippingSection.addressLine1.sendKeys("680 Folsom Street");
        shippingSection.addressLine2.sendKeys("Suite 10");
        shippingSection.city.sendKeys("San Francisco");
        shippingSection.phone.sendKeys("6502222222");
        shippingSection.state.sendKeys(GuestCheckout.STATE_VALUE);
        shippingSection.zipCode.sendKeys("94107");
    }

    @Then("^I should see the (shipping address|payment) section in active state$")
    public void iShouldSeerespectiveSectionInActiveState(String section) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (section) {
            case "shipping address":
                assertTrue(" shipping address section is not in active state", Wait.until(signedInCheckout.shippingAddressForm::isDisplayed));
                break;
            case "payment":
                assertTrue(" payment section is not in active state", Wait.until(signedInCheckout.paymentInfo::isDisplayed));
                break;
        }
    }

    @Then("^I should not see the Cancel button in the (shipping Address|payment) section$")
    public void iShouldNotSeeCancelButtonInShippingPaymentSection(String section) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (section) {
            case "shipping Address":
                assertFalse("Shipping address cancel button is displayed", Wait.until(signedInCheckout.cancelShippingChangeButton::isDisplayed));
                break;
            case "payment":
                assertFalse("Payment section is not in active state", Wait.until(signedInCheckout.cancelCreditCardButton::isDisplayed));
                break;
        }
    }

    @Then("^deselect use my shipping on payment section$")
    public void deselectUseMyShippingOnPaymentSection() {
        PaymentGuestSection paymentGuestSection = Navigate.get(PaymentGuestSection.class);

        CheckBox useMyShipping = paymentGuestSection.useShippingAddress;
        Wait.untilElementNotPresent(useMyShipping);
        useMyShipping.deselect();
    }

    @Then("^validate client side validation for the payment information$")
    public void validateClientSideValidationForThePaymentInformation() {
        PaymentGuestSection paymentGuestSection = Navigate.get(PaymentGuestSection.class);

        WebElement errorMsg = paymentGuestSection.cardType.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.CARD_TYPE_REQ_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.cardNumber.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.CARD_NUMBER_REG_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.securityCode.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.SECURITY_NUMBER_REG_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.firstName.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.FIRST_NAME_REQ_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.lastName.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.LAST_NAME_REG_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.addressLine_1.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.ADDRESS_LINE_1_REQ_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.addressCity.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.CITY_REG_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.addressState.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.STATE_REQ_MSG, errorMsg.getText());

        errorMsg = paymentGuestSection.addressZipCode.findElement(By.xpath(SIBLING_XPATH_BY_SMALL_CSS_CLASS));
        assertEquals(GuestCheckout.ZIP_REQ_MSG, errorMsg.getText());
    }

    /**
     * Step to click "Add New Address"
     * Fills shipping address for a user's first address
     */
    @When("^I fill the contact information for signed in user$")
    public ShippingSection fillShippingSectionContactInformationForSignedInUser(ShippingSection shippingSection) {
        //on checkout with ADD AN ADDRESS button

        logger.info("filling Shipping new address information");
        shippingSection.addressInfoSection().fillDefaultData(false);

        return shippingSection;
    }

    @When("^I add a (valid|invalid) shipping address on shipping page for signed in user$")
    public void addShippingAddressForSignedInUser(String valid) {
        boolean isValid = valid.equalsIgnoreCase("valid");
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);

        assertTrue("Shipping Address already present no need to add new address", Wait.until(signedInCheckout.shippingAddressForm::isDisplayed));

        if (isValid) {
                fillShippingSectionContactInformationForSignedInUser(Navigate.get(ShippingSection.class));
        } else {
                //new method
        }
        signedInCheckout.saveShippingAddress.click();

        Wait.until(signedInCheckout.changeShippingAddress::isDisplayed);

        logger.info("Shipping Address already present no need to add new address");

    }

    /**
     * Verify address is saved and payment
     */
    @Then("^new address should be saved to my profile and order(?: with \"([^\"]*)\" is \"([^\"]*)\")?$")
    public void isNewAddressSavedtoProfileAndOrder(String field, String value) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);

        Wait.until(signedInCheckout.shippingAddressSwapContainer::isDisplayed);

        assertTrue(signedInCheckout.changeShippingAddress.isDisplayed());

        assertTrue(signedInCheckout.shippingAddressSummaryInfoName.isDisplayed());

        assertTrue(signedInCheckout.shippingAddressSummaryInfoAddressLine1.isDisplayed());

        assertTrue(signedInCheckout.shippingAddressSummaryInfoAddressLine2.isDisplayed());

        assertTrue(signedInCheckout.shippingAddressSummaryInfoCityStateZip.isDisplayed());

        assertEquals("548 Market St", signedInCheckout.shippingAddressSummaryInfoAddressLine1.getText());
        assertEquals("San Francisco, CA 94104", signedInCheckout.shippingAddressSummaryInfoCityStateZip.getText());
        if (StringUtils.isNotEmpty(field) && StringUtils.isNotEmpty(field)) {
            if ("addressLine2".equalsIgnoreCase(field)) {
                logger.info(signedInCheckout.shippingAddressSummaryInfoAddressLine2.getText());
                assertEquals(value, signedInCheckout.shippingAddressSummaryInfoAddressLine2.getText());
            }
        }
    }

    /**
     * By Default edits user's first shipping address
     *
     * @param addressLine2
     */
    @And("^I edit my selected shipping-addressLine2 to \"([^\"]*)\" for signed in user$")
    public void editShippingAddressLine2ForSignedInUser(String addressLine2) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);

        Wait.until(signedInCheckout.changeShippingAddress::isDisplayed);
        signedInCheckout.changeShippingAddress.click();

        Wait.until(signedInCheckout.shippingAddressSelectedEditButton::isDisplayed);

        signedInCheckout.shippingAddressSelectedEditButton.click();

        Wait.until(signedInCheckout.saveShippingAddress::isDisplayed);

        Navigate.get(ShippingSection.class).addressLine2.sendKeys(addressLine2);

        signedInCheckout.saveShippingAddress.click();

        Wait.until(signedInCheckout.changeShippingAddress::isDisplayed);
    }

    @And("^I add an additional address on shipping page for signed in user$")
    public void addAnAdditionalAddressForSignedInUser() {

        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);

        Wait.until(signedInCheckout.changeShippingAddress::isDisplayed);
        signedInCheckout.changeShippingAddress.click();

        Wait.until(signedInCheckout.addShippingAddressFormButton::isDisplayed);

        signedInCheckout.addShippingAddressFormButton.click();

        Wait.until(signedInCheckout.saveShippingAddress::isDisplayed);

        ShippingSection shippingSection = fillShippingSectionContactInformationForSignedInUser(Navigate.get
                (ShippingSection.class));
        shippingSection.addressLine2.sendKeys("Suite 13000");
        signedInCheckout.saveShippingAddress.click();

        Wait.until(signedInCheckout.changeShippingAddress::isDisplayed);
    }

    @And("^I (do|don't) have a saved credit card$")
    public void checkCreditCardPresent(String auxiliaryVerb) {
        PaymentSignedInSection paymentSignedInSection = Navigate.get(PaymentSignedInSection.class);

        if (auxiliaryVerb.equalsIgnoreCase("don't")) {
            assertTrue(paymentSignedInSection.addNewCard.isDisplayed());
        } else {
            assertTrue(paymentSignedInSection.changePaymentButton.isDisplayed());
            assertTrue(paymentSignedInSection.paymentCreditCardSummary.isDisplayed());
        }
    }

    @And("^I click on change credit card$")
    public void doCreditCardChangeAction() {
        PaymentSignedInSection paymentSignedInSection = Navigate.get(PaymentSignedInSection.class);

        paymentSignedInSection.changePaymentButton.click();

        Wait.until(paymentSignedInSection.editCreditCard::isDisplayed);
    }

    @Then("^I should be able edit saved credit card information$")
    public void doEditCreditCard() {
        PaymentSignedInSection paymentSignedInSection = Navigate.get(PaymentSignedInSection.class);

        paymentSignedInSection.editCreditCard.click();

        Wait.until(paymentSignedInSection.cancelPaymentChangeButton::isDisplayed);

        CreditCard card = CreditCards.getValidCard("MasterCard");
        paymentSignedInSection.fillCreditCardInfo(card);

        paymentSignedInSection.saveCard.click();

        Wait.until(paymentSignedInSection.changePaymentButton::isDisplayed);
        checkValidaityOfCardAdded(paymentSignedInSection, card);
    }


    @Then("^I should be able to add a new (Visa|Discover|Bloomingdale's) credit card during checkout in signed in$")
    public void doAddNewCreditCard(String cardType) {
        PaymentSignedInSection paymentSignedInSection = Navigate.get(PaymentSignedInSection.class);

        Wait.until(paymentSignedInSection.addNewCard::isDisplayed);
        if (paymentSignedInSection.addNewCard.exists()) {
            paymentSignedInSection.addNewCard.click();

            Wait.until(paymentSignedInSection.cancelPaymentChangeButton::isDisplayed);

            CreditCard card = CreditCards.getValidCard(cardType);
            paymentSignedInSection.fillCreditCardInfo(card);
            if (paymentSignedInSection.shippingAddress.isSelected()) {
                paymentSignedInSection.useShippingAddress.click();
            }
            paymentSignedInSection.addressInfoSection().fillDefaultData(true);
            paymentSignedInSection.saveCard.click();

            Wait.until(paymentSignedInSection.changePaymentButton::isDisplayed);
            checkValidaityOfCardAdded(paymentSignedInSection, card);
        } else {
            logger.info("Card Data already present no need to add new card");
        }
    }

    /**
     * Will click on the back to bag icon
     */
    @And("^I click on the back to bag icon$")
    public void clickOnBackToBagIcon() {
        // Need to wait for the shipping section to load because the checkout page auto scrolls
        // once the shipping section has been loaded
        ShippingSection shippingSection = Navigate.get(ShippingSection.class);
        shippingSection.waitForReady();

        HeaderSection headerSection = Navigate.get(HeaderSection.class);
        headerSection.clickBackToBagIcon();
    }

    /**
     * Verifies that the user is on the bag page
     */
    @Then("^validate that I am on the bag page$")
    public void validateThatUserIsOnBagPage() {
        ShoppingBag shoppingBagPage = Navigate.get(ShoppingBag.class);
        assertTrue(StepUtils.onPage(shoppingBagPage.pageAlias()));
    }

    /**
     * Will add the given credit card type to the payment section for guest
     *
     * @param cardType Either Visa or American Express
     */
    @And("^I add a (Visa|American Express) card during checkout as a guest$")
    public void addCreditCardForGuest(String cardType) {
        PaymentGuestSection paymentGuestSection = Navigate.get(PaymentGuestSection.class);
        Wait.until(paymentGuestSection.expiryMonth::isDisplayed);

        paymentGuestSection.fillCreditCardInfo(CreditCards.getValidCard(cardType));
        paymentGuestSection.paymentEmail.sendKeys("test@test.com");
    }

    @Then("^I verify that useMyShipping is set to true$")
    public void verifyUseMyShipping() {
        PaymentGuestSection paymentGuestSection = Navigate.get(PaymentGuestSection.class);

        assertEquals("true", paymentGuestSection.useShippingAddress.getAttribute("value"));
        assertTrue(paymentGuestSection.useMyShippingName.isDisplayed());
        assertTrue(paymentGuestSection.useMyShippingAddressLine1.isDisplayed());
        assertTrue(paymentGuestSection.useMyShippingCityStateZip.isDisplayed());
    }

    @Then("^I verify that the payment section is in summary state$")
    public void verifyPaymentSectionInSummaryState() {
        PaymentGuestSection paymentGuestSection = Navigate.get(PaymentGuestSection.class);
        Wait.until(paymentGuestSection.paymentSummary::isDisplayed);

        assertTrue(paymentGuestSection.paymentSummary.isDisplayed());
        assertTrue(paymentGuestSection.editPaymentSection.isDisplayed());
    }

    @Then("^I should see the shipping Methods section in active state$")
    public void iShouldSeeShippingMethodSectionInActiveState() {
        ShippingOptions shippingOptions = Navigate.get(ShippingOptions.class);
        assertTrue(" shipping methods are not displayed", Wait.until(shippingOptions.standardShipping::isDisplayed));
    }

    @Then("^I should not see the Save button in the shipping Method section$")
    public void iShouldNotSeeSaveButtonInShippingMethodSection() {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        assertFalse(" Save button is displayed", Wait.until(signedInCheckout.saveShippingMethod::isDisplayed));
    }

    @Then("^I should not see the Cancel button in the shipping Method section$")
    public void iShouldNotSeeCancelButtonInShippingMethodSection() {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        assertFalse("Cancel button is displayed", Wait.until(signedInCheckout.cancelShippingChangeButton::isDisplayed));
    }

    @Then("^I should not see the Change button in the shipping Method section$")
    public void iShouldNotSeeChangeButtonInShippingMethodSection() {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        assertFalse("Change button is displayed", Wait.until(signedInCheckout.changeShippingMethod::isDisplayed));
    }

    @Then("^As a (guest|signed in) I verify that the payment section is in summary state$")
    public void asUserTypeVerifyPaymentSectionInSummaryState(String userType) {
        switch (userType) {
            case "guest":
                verifyPaymentSectionInSummaryState();
                break;
            case "signed in":
                //no check needed for signedIn yet
                break;
        }
    }

    private void checkValidaityOfCardAdded(PaymentSignedInSection paymentSignedInSection, CreditCard card) {

        assertTrue(paymentSignedInSection.paymentCreditCardSectionTitle.isDisplayed());

        logger.info("Added Card data : " + paymentSignedInSection.paymentCreditCardSummary.getText());
        assertTrue(paymentSignedInSection.paymentCreditCardSummary.getText().contains(card.getCardType().name));
        assertTrue(paymentSignedInSection.paymentCreditCardSummary.getText().contains(card.getExpiryYear()));
    }

    @And("^As a (guest|signed in) user I verify the security code (is|is not) retained$")
    public void asAUserVerifyTheSecurityCodeIsRetained(String userType, String retained) throws Throwable {
        boolean checkRetained = !retained.contains("not");
        switch (userType) {
            case "guest":
                PaymentGuestSection paymentGuestSection = Navigate.get(PaymentGuestSection.class);
                if (paymentGuestSection.editPaymentSection.exists()) {
                    //click edit
                    paymentGuestSection.editPaymentSection.click();
                }
                Wait.until(paymentGuestSection.securityCode::isDisplayed);
                if (checkRetained) {
                    //check security code is retained
                    assertTrue(StringUtils.isNotBlank(paymentGuestSection.securityCode.getText()));
                } else {
                    //check security code is cleared
                    assertTrue(StringUtils.isBlank(paymentGuestSection.securityCode.getText()));
                }
                break;
            case "signed in":
                SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
                if (checkRetained) {
                    assertTrue(StringUtils.isNotBlank(signedInCheckout.paymentSection().securityCode.getText()));
                } else {
                    assertTrue(StringUtils.isBlank(signedInCheckout.paymentSection().securityCode.getText()));
                }
                break;
        }
    }

    @And("^As a (guest|signed in) user I edit the shipping address$")
    public void AsAUserTypeIEditTheShippingAddress(String userType) {
        switch (userType) {
            case "guest":
                ShippingSection shippingSection = Navigate.get(ShippingSection.class);
                shippingSection.editShippingSection.click();
                HashMap<String, String> opts = new HashMap<>();
                Checkout checkout = new Checkout(opts, false);
                try {
                    checkout.rcGuest(CheckoutUtils.RCPage.PAYMENT, opts);
                } catch (ProductionException e) {
                    e.printStackTrace();
                }
                break;
            case "signed in":
                SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
                Wait.until(signedInCheckout.changeShippingAddress::isDisplayed);
                signedInCheckout.changeShippingAddress.click();
                Wait.until(signedInCheckout.saveShippingAddress::isDisplayed);
                signedInCheckout.saveShippingAddress.click();
                break;
        }
    }

    @And("^I update the address in my address book with addressline2 missing$")
    public void iUpdateTheAddressInMyAddressBookWithAddressLine2Missing() throws Throwable {
        MyAddressBookPage myAddressBookPage = Navigate.to(MyAddressBookPage.class);
        Map<String, String> opts = new HashMap<>();
        opts.put("invalid_ship_address", "true");

        myAddressBookPage.updateAddress(0, opts);
        Wait.forPageReady();
        logger.info("->editAddress(): Address updated!!");
    }

    @Then("^validate shipping section for addressLine2 validation messages$")
    public void validateTheShippingSectionForAddressLIne2ValidationMessages() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.shippingAddressLineTwoValidationErrorMessage::isDisplayed);
        List<String> actualErrors = Navigate.get(AddressInfoSection.class).getFieldLevelErrors();
        String addressLine2ErrorMessage = "This address requires a valid apartment, floor or suite number.";
        assertEquals("Address line 2 validation message does not appear", addressLine2ErrorMessage, actualErrors.get(0));
        logger.info("Expected field level error message(s) verification is successful");
    }

    @And("^I edit my selected shipping addressLine2 for signed in user$")
    public void editShippingAddressLineSignedInUser() {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        signedInCheckout.changeShippingAddress.click();

        Wait.until(signedInCheckout.shippingAddressSelectedEditButton::isDisplayed);

        signedInCheckout.shippingAddressSelectedEditButton.click();

        Wait.until(signedInCheckout.saveShippingAddress::isDisplayed);
        signedInCheckout.saveShippingAddress.click();
    }

    @And("^I add shipping address with addressline2 missing on guest checkout$")
    public void iAddShippingAddressWithAddressline2MissingOnGuestCheckout() {
        HashMap<String, String> opts = new HashMap<>();
        pausePageHangWatchDog();
        opts.put("invalid_ship_address", "true");
        if (macys()) {
            new Checkout(opts, false).fillShippingData(false);
        } else {
            new CheckoutPageBcom(opts, false).fillGuestShippingData(false);
        }
        resumePageHangWatchDog();
    }

    @Then("^I see 'Make your Blm card default' message appears$")
    public void iSeeMakeYourBlmCardDefaultMessageAppears() {
        PaymentSignedInSection paymentSignedInSection = Navigate.get(PaymentSignedInSection.class);
        Wait.until(paymentSignedInSection.makeBlmDefaultMsg::isDisplayed);
        assertTrue(StringUtils.isNotBlank(paymentSignedInSection.makeBlmDefaultMsg.getText()));
        assertEquals(SignedInCheckout.MAKE_BLM_DEFAULT_MSG, paymentSignedInSection.makeBlmDefaultMsg.getText());
        assertEquals(SignedInCheckout.MAKE_BLM_DEFAULT_LINK, paymentSignedInSection.makeBlmDefaultLink.getText());
        logger.info("Verified Make your Blm card default' message appears in Payment section");
    }

    @Then("^I see 'Your default card has been successfully updated' message appears$")
    public void iSeeYourDefaultCardHasBeenSuccessfullyUpdatedMessageAppears() {
        PaymentSignedInSection paymentSignedInSection = Navigate.get(PaymentSignedInSection.class);
        Wait.until(paymentSignedInSection.defaultCardUpdatedSuccessMsg::isDisplayed);
        assertTrue(StringUtils.isNotBlank(paymentSignedInSection.defaultCardUpdatedSuccessMsg.getText()));
        assertEquals(SignedInCheckout.DEFAULT_CARD_UPDATED_SUCCESS_MSG, paymentSignedInSection.defaultCardUpdatedSuccessMsg.getText());
        logger.info("Verified 'Your default card has been successfully updated' message appears in Payment section");
    }
}

