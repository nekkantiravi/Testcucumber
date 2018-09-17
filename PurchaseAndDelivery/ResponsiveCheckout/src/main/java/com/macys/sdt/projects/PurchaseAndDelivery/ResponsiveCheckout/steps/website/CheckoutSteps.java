package com.macys.sdt.projects.PurchaseAndDelivery.ResponsiveCheckout.steps.website;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.exceptions.UserException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.framework.utils.rest.services.ShopAppClient;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry.RegistryHome;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry.RegistryManager;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.registry.RegistrySignIn;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.ProductDisplay;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website.ServiceSteps;
import com.macys.sdt.projects.PurchaseAndDelivery.ResponsiveCheckout.actions.website.RegistryLoginActions;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.macys.sdt.framework.utils.StepUtils.*;

public class CheckoutSteps {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutSteps.class);
    public static final String XPATH_FOR_FIELD_LEVEL_ERROR = StepUtils.macys() ? "following-sibling::small[@class='error_msg']" :
            "../../following-sibling::div[contains(@class,'errorMessageInline')]";
    public static String selectedShippingInfo;
    public static String bagId;

    @And("^I should see shopping bag with no products$")
    public void iShouldSeeShoppingBagWithNoProducts() {
        logger.info("Navigating to shopping bag page..");
        ShoppingBag shoppingBag = Navigate.to(ShoppingBag.class);
        Wait.until(shoppingBag.bagItemCount::isDisplayed);
        Assert.assertEquals("Shopping bag is not empty", "Your Current Shopping Bag is empty.", shoppingBag.emptyBagMessage());
        logger.info("Shopping bag verification is successful");
    }

    @And("^I add another registry item from different registry user to my bag using rest service$")
    public void iAddAnotherRegistryItemFromDifferentRegistryUserToMyBagUsingRestService() throws ProductionException, UserException {
        UserProfile prevUser = TestUsers.getCustomer(null);
        TestUsers.clearCustomer();
        UserProfileService.createRandomUserProfile();
        int retryCount = 0;
        while (retryCount < 5) {
            try {
                Registry registry = RegistryService.createRandomRegistry(null);
                if (registry.getId() != null) {
                    break;
                }
            } catch (AssertionError e) {
                System.out.println("Retrying again...");
                retryCount++;
            }
        }
        Assert.assertNotNull("Failed to create registry", TestUsers.getCustomer(null).getRegistry().getId());
        ServiceSteps.iAddAProductToMyBagUsingRestService("registrable and orderable", "available_bops");
        TestUsers.setCurrentCustomer(prevUser);
    }

    @And("^I should see below (error|warning) message(?:s)? in checkout page:$")
    public void iShouldSeeBelowWarningMessageInCheckoutPage(String messageType, List<String> expectedMessages) {
        Checkout checkout = StepUtils.signedIn() ? Navigate.get(SignedInCheckout.class, true) : Navigate.get(GuestCheckout.class, true);
        ShippingOptions shippingOptions = checkout.shippingSection().shippingOptions();
        List<String> actualMessages = messageType.equals("error") ? shippingOptions.getErrorMessages() : shippingOptions.getWarningMessages();
        expectedMessages.forEach(expectedMessage -> {
            Assert.assertTrue("ERROR:: Expected " + messageType + " message is not displayed", actualMessages.contains(expectedMessage));
        });
        logger.info("Expected " + messageType + " message(s) verification is successful");
    }

    @Then("^I verify the registrant and co registrant name details in shopping bag$")
    public void iVerifyTheRegistrantAndCoRegistrantNameDetailsInShoppingBag() {
        Registry registry = TestUsers.getCustomer(null).getRegistry();
        String registrant = registry.getContactInfo().getFirstName() + " " + registry.getContactInfo().getLastName();
        String coRegistrant = registry.getCoRegistrantContactInfo().getFirstName() + " " + registry.getCoRegistrantContactInfo().getLastName();
        String registrantName = Navigate.get(ShoppingBag.class).registrantInfo();
        Assert.assertTrue("Registrant and Co Registrant name on shopping bag are not matching with Registry data", registrantName.toUpperCase().contains(registrant + " & " + coRegistrant));
    }

    @Then("^I should see error message as:$")
    public void iShouldSeeErrorMessageAs(DataTable table) {
        Wait.forLoading(By.className("loader-container"));
        Checkout checkout = StepUtils.signedIn() ? Navigate.get(SignedInCheckout.class, true) : Navigate.get(GuestCheckout.class, true);
        Map<String, String> messages = new HashMap<>(table.asMap(String.class, String.class));
        String actualMessage = checkout.shippingSection().shippingOptions().errorContainer.getText();
        String expectedMessage = messages.get(TestUsers.getSiteType().toUpperCase());
        Assert.assertTrue("Expected error message is not displayed in checkout page. Expected: " + expectedMessage + " \nGot: " + actualMessage, actualMessage.contains(expectedMessage));
        logger.info("Error message verification is successful");
    }

    @And("^I select pick up by some one else option$")
    public void iSelectPickUpBySomeOneElseOption() {
        Navigate.get(GuestCheckout.class).shippingSection().shippingOptions().selectPickupBySomeoneElse();
    }

    @Then("^I should see saved information in bops shipping section$")
    public void iShouldSeeSavedInformationInBopsShippingSection() {
        ProfileAddress pickupAdress = TestUsers.getCustomer(null).getUser().getProfileAddress();
        ShippingOptions shipping = Navigate.get(ShippingOptions.class);
        Assert.assertEquals("Mismatch in pickup first name", shipping.pickupFirstName.getAttribute("value"), pickupAdress.getFirstName());
        Assert.assertEquals("Mismatch in pickup last name", shipping.pickupLastName.getAttribute("value"), pickupAdress.getLastName());
        Assert.assertEquals("Mismatch in pickup email", shipping.pickupEmailAddress.getAttribute("value"), pickupAdress.getEmail());
        Assert.assertEquals("Mismatch in pickup phone number", shipping.pickupPhoneNumber.getAttribute("value").replaceAll("[^0-9]", ""), pickupAdress.getBestPhone());
    }

    @And("^I continue checkout without adding a CVV number$")
    public void iContinueCheckoutWithoutAddingACVVNumber() {
        CreditCard card = CreditCards.getValidCard("Visa");
        card.setSecurityCode("");
        Navigate.get(GuestCheckout.class).paymentSection().addPaymentMethod(card, TestUsers.generateRandomEmail(7));
        WebElement errorMsg = Navigate.get(PaymentGuestSection.class).securityCode.findElement(By.xpath(XPATH_FOR_FIELD_LEVEL_ERROR));
        Assert.assertEquals("Expected error message is not displayed", GuestCheckout.SECURITY_NUMBER_REG_MSG, errorMsg.getText());
    }

    @And("^I remove (first|last) item from shopping bag$")
    public void iRemoveFirstItemFromShoppingBag(String index) {
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        shoppingBag.removeItem(index.equals("first") ? 0 : shoppingBag.itemCount() - 1);
    }

    @When("^I click edit in (shipping|payment) section$")
    public void iClickEditInShippingSection(String sectionName) {
        if (sectionName.equals("shipping")) {
            Clicks.click(Navigate.get(ShippingSection.class).editShippingSection.getWrappedElement());
        } else {
            Clicks.click(Navigate.get(PaymentGuestSection.class).editPaymentSection.getWrappedElement());
        }
    }

    @And("^I click change in (shipping address|payment|paypal|contact info) section$")
    public void iClickChangeInPayment(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "shipping address":
                Assert.assertTrue(Wait.until(signedInCheckout.changeShippingAddress::exists, 20));
                Clicks.click(signedInCheckout.changeShippingAddress.getWrappedElement());
                break;
            case "payment":
                Assert.assertTrue(Wait.until(signedInCheckout.changeCreditCardButton::exists, 20));
                Clicks.click(signedInCheckout.changeCreditCardButton.getWrappedElement());
                break;
            case "paypal":
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().changePaypalButton::exists, 20));
                Clicks.click(signedInCheckout.paymentSection().changePaypalButton.getWrappedElement());
                break;
            case "contact info":
                ContactInfoSection contactInfoSection = Navigate.get(ContactInfoSection.class);
                Assert.assertTrue(Wait.until(contactInfoSection.change::exists));
                Clicks.click(contactInfoSection.change.getWrappedElement());
        }
    }

    @And("^I click save in (shipping|payment|contact info) section$")
    public void iClickSaveInShippingSection(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "shipping":
                Assert.assertTrue(Wait.until(signedInCheckout.saveShippingAddress::exists));
                Clicks.click(signedInCheckout.saveShippingAddress.getWrappedElement());
                Wait.untilElementNotPresent(signedInCheckout.saveShippingAddress.getWrappedElement());
                break;
            case "payment":
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().savePaymentButton::exists));
                Clicks.click(signedInCheckout.paymentSection().savePaymentButton.getWrappedElement());
                Wait.untilElementNotPresent(signedInCheckout.paymentSection().savePaymentButton.getWrappedElement());
                break;
            case "paypal":
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().savePaypalButton::exists));
                Clicks.click(signedInCheckout.paymentSection().savePaypalButton.getWrappedElement());
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().changePaypalButton::exists));
                break;
            case "contact info":
                ContactInfoSection contactInfoSection = Navigate.get(ContactInfoSection.class);
                Assert.assertTrue(Wait.until(contactInfoSection.save::exists));
                Clicks.click(contactInfoSection.save.getWrappedElement());
                Wait.untilElementNotPresent(contactInfoSection.save.getWrappedElement());
        }
    }

    @And("^I click cancel in (payment|paypal|contact info) section$")
    public void iClickCancelInCreditCardSection(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "payment":
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().cancelPaymentChangeButton::exists));
                Clicks.click(signedInCheckout.paymentSection().cancelPaymentChangeButton.getWrappedElement());
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().changePaymentButton::exists));
                break;
            case "paypal":
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().cancelPaypalChangeButton::exists));
                Clicks.click(signedInCheckout.paymentSection().cancelPaypalChangeButton.getWrappedElement());
                Assert.assertTrue(Wait.until(signedInCheckout.paymentSection().changePaypalButton::exists));
                break;
            case "contact info":
                ContactInfoSection contactInfoSection = Navigate.get(ContactInfoSection.class);
                Assert.assertTrue(Wait.until(contactInfoSection.cancel::exists));
                Clicks.click(contactInfoSection.cancel.getWrappedElement());
                Assert.assertTrue(Wait.until(contactInfoSection.change::exists));
        }
    }

    @And("^I click (add new address|edit|cancel|save) in shipping address section$")
    public void iClickAddNewAddressInShippingAddressSection(String button) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (button) {
            case "add new address":
                Clicks.click(signedInCheckout.addNewAddress.getWrappedElement());
                break;
            case "edit":
                Clicks.click(signedInCheckout.editShippingAddress.getWrappedElement());
                break;
            case "cancel":
                Clicks.click(signedInCheckout.cancelShippingChangeButton.getWrappedElement());
                break;
            case "save":
                Clicks.click(signedInCheckout.saveNewAddress.getWrappedElement());
        }
    }

    @And("^I click (add new card|edit|cancel|save) in credit card section$")
    public void iClickAddNewAddressInCreditCardSection(String button) {
        PaymentSignedInSection signedinPayment = Navigate.get(PaymentSignedInSection.class);
        switch (button) {
            case "add new card":
                Clicks.click(signedinPayment.addNewCard.getWrappedElement());
                break;
            case "edit":
                Clicks.click(signedinPayment.editCard.getWrappedElement());
                break;
            case "cancel":
                Clicks.click(signedinPayment.cancelPaymentChangeButton.getWrappedElement());
                break;
            case "save":
                Clicks.javascriptClick(signedinPayment.saveCard.getWrappedElement());
        }
    }

    @And("^I should see (paypal|contact info) section in summary state$")
    public void iShouldSeeShippingMethodSectionInSummaryState(String sectionName) {
        switch (sectionName) {
            case "contact info":
                Assert.assertTrue(Wait.until(Navigate.get(ContactInfoSection.class).change::exists, 30));
                break;
            case "paypal":
                Assert.assertTrue(Wait.until(Navigate.get(PaymentSignedInSection.class).changePaypalButton::exists, 30));
        }
    }

    @Then("^I verify basic attributes of the Order Review Page$")
    public void iVerifyBasicAttributesOfTheOrderReviewPage() {
        Assert.assertTrue(Wait.until(Navigate.get(OrderReviewSection.class).placeOrder::exists));
        if (signedIn()) {
            //           Assert.assertTrue(Wait.until(Navigate.get(ShippingSection.class).changeShippingMethod::exists));
            Assert.assertTrue(Wait.until(Navigate.get(PaymentSignedInSection.class).changeCreditCardButton::exists));
        } else {
            Assert.assertTrue(Wait.until(Navigate.get(ShippingSection.class).editShippingSection::exists));
            Assert.assertTrue(Wait.until(Navigate.get(PaymentGuestSection.class).editPaymentSection::exists));
        }
    }

    @Then("^I should see default shipping method as \"([^\"]*)\"(?: and price as \"([^\"]*)\")?$")
    public void iShouldSeeDefaultShippingMethodAsAndPriceAs(String shippingMethod, String shippingCost) {
        Checkout checkout = StepUtils.signedIn() ? Navigate.get(SignedInCheckout.class, true) : Navigate.get(GuestCheckout.class, true);
        ShippingSection shipping = checkout.shippingSection();
        if (StepUtils.signedIn()) {
            shipping.expandShippingMethodSection();
        }
        String selectedShippingMethod = shipping.shippingOptions().getSelectedShippingDetails();
        boolean isMatched = selectedShippingMethod.contains(shippingMethod) || selectedShippingMethod.contains("Standard");
        Assert.assertTrue(shippingMethod + " is not selected", isMatched);
        if (shippingCost != null) {
            Assert.assertEquals("Shipping cost is not as expected", Navigate.get(OrderSummarySection.class).shippingFee.getText(), shippingCost);
        }
    }

    @And("^I should see place order button in enabled state$")
    public void iShouldSeePlaceOrderButtonInEnabledState() {
        OrderReviewSection orderReviewSection = Navigate.get(GuestCheckout.class).orderReviewSection();
        Assert.assertTrue("Place Order button is not enabled", orderReviewSection.placeOrder.isEnabled());
    }

    @And("^I continue checkout with \"([^\"]*)\" shipping address$")
    public void iContinueCheckoutWithIneligibleShippingAddress(String addressType) {
        HashMap<String, String> opts = new HashMap<>();
        opts.put(addressType, "true");
        ProfileAddress profileAddress = new ProfileAddress();
        TestUsers.getRandomValidAddress(opts, profileAddress);
        ShippingOptions shippingOptions = Navigate.get(GuestCheckout.class, true).shippingSection().shippingOptions();
        shippingOptions.fillShippingAddress(profileAddress);
        Clicks.click(shippingOptions.continueShippingCheckoutButton.getWrappedElement());
    }

    @And("^I select (premium|express|sdd) shipping method$")
    public void iSelectPremiumShippingMethod(String shipping) {
        ShippingOptions shippingOptions = Navigate.get(ShippingOptions.class);
        if (shipping.equals("premium")) {
            Wait.until(shippingOptions.premiumShipping::isDisplayed, 10);
            Assert.assertTrue("Premium shipping method is in disabled state", Wait.until(shippingOptions.premiumShipping::isEnabled));
            Clicks.javascriptClick(shippingOptions.premiumShipping.getWrappedElement());
        } else if (shipping.equals("express")) {
            Wait.until(shippingOptions.expressShipping::isDisplayed, 10);
            Assert.assertTrue("Express shipping method is in disabled state", Wait.until(shippingOptions.expressShipping::isEnabled));
            Clicks.javascriptClick(shippingOptions.expressShipping.getWrappedElement());
        } else {
            Wait.until(shippingOptions.sddShipping::isDisplayed, 10);
            Assert.assertTrue("SDD shipping method is in disabled state", Wait.until(shippingOptions.sddShipping::isEnabled));
            Clicks.javascriptClick(shippingOptions.sddShipping.getWrappedElement());
        }
        selectedShippingInfo = shippingOptions.getSelectedShippingDetails();
    }

    @Then("^I select sdd shipping method option$")
    public void iSelectSddShippingNethodOption() {
        try {
            iShouldSeeSddShippingOptionInEnabledState("enabled");
        } catch (AssertionError e) {
            if (!Wait.until(Navigate.get(ShippingOptions.class).sddShipping::isDisplayed, 10)) {
                new ServiceSteps().setAvailability("sdd", null);
                Assert.fail("Added product is not eligible for SDD or in disabled state");
            }
        }
        iSelectPremiumShippingMethod("sdd");
    }

    @Then("^I should see sdd shipping option in (enabled|disabled) state$")
    public void iShouldSeeSddShippingOptionInEnabledState(String elementState) {
        ShippingOptions shippingOptions = Navigate.get(ShippingOptions.class);
        Assert.assertTrue("SDD shipping method is not displaying", Wait.until(shippingOptions.sddShipping::isDisplayed, 10));
        Assert.assertTrue("SDD shipping method is not in " + elementState + " state", shippingOptions.sddShipping.isEnabled() == elementState.equals("enabled"));
    }

    @Then("^I (should|should not) see gift box fee in order summary section$")
    public void iShouldSeeGiftBoxFeeInOrderSummarySection(String condition) {
        OrderSummarySection orderSummarySection = Navigate.get(OrderSummarySection.class);
        if (condition.equals("should")) {
            Assert.assertTrue(Wait.until(orderSummarySection.giftWrapCost::exists, 10));
            Assert.assertTrue("Gift wrap cost in order summary is not in expected format or returned Null",
                    orderSummarySection.giftWrapCost.getText().matches("\\$\\d+.\\d\\d"));
        } else {
            Assert.assertFalse(Wait.until(orderSummarySection.giftWrapCost::exists, 10));
        }
    }

    @Then("^I should see selected shipping charge amount in order summary$")
    public void iShouldSeeSelectedShippingChargeAmountInOrderSummary() {
        OrderSummarySection orderSummarySection = Navigate.get(OrderSummarySection.class);
        Wait.until(orderSummarySection.shippingFee::isDisplayed, 10);
        Assert.assertTrue("Shipping cost is not as expected",
                Wait.until(() -> selectedShippingInfo.contains(orderSummarySection.shippingFee.getText())));
    }

    @Then("^I should see shipping surcharge fee on shopping bag page$")
    public void iShouldSeeShippingSurchargeFeeOnShoppingBagPage() {
        ShoppingBag shoppingBag = Navigate.to(ShoppingBag.class);
        Assert.assertTrue("Shipping surcharge fee is not displayed on shopping bag page", shoppingBag.shippingSurchargeContainer.exists());
    }

    @And("^I click save in bops shipping section$")
    public void iClickSaveInBopsShippingSection() {
        Navigate.get(ShippingOptions.class).continueShippingCheckoutButton.click();
    }

    @And("^I submit bops shipping section$")
    public void iSubmitBopsShippingSection() {
        ShippingOptions shipping = Navigate.get(ShippingOptions.class);
        if (Wait.until(() -> shipping.continueShippingCheckoutButton.isDisplayed(), 30)) {
            Clicks.click(shipping.continueShippingCheckoutButton.getWrappedElement());
        }
    }

    @Then("^I verify the basic attributes in Border Free Page$")
    public void iVerifyTheBasicAttributesInBorderFreePage() {
        IShipCheckout iShip = Navigate.get(IShipCheckout.class);
        iShip.waitForReady();
        switchToFrame("iship_checkout.shipping_iFrame");
        Assert.assertTrue(Wait.until(iShip.productImage::exists, 10));
        Assert.assertTrue(Wait.until(iShip.price::exists));
        Assert.assertTrue(Wait.until(iShip.description::exists));
        Assert.assertTrue(Wait.until(iShip.orderTotal::exists));
    }

    @Then("^I verify the iship specific attributes of PDP Page$")
    public void iVerifyTheIshipSpecificAttributesOfPDPPage() {
        ProductDisplay pdp = Navigate.get(ProductDisplay.class);
        Assert.assertTrue(Wait.until(pdp.productTitle::exists));
        Assert.assertTrue(Wait.until(pdp.addToBagButton::exists));
        Assert.assertTrue(Wait.until(pdp.priceBox::exists));
        Assert.assertTrue(Wait.until(pdp.productImage::exists));
        Assert.assertTrue(Wait.until(pdp.productDescriptionBlock::exists));
    }

    @Then("^I verify the footer section details in (?:checkout|order confirmation) page$")
    public void iVerifyTheFooterSectionDetailsInCheckoutPage() {
        FooterSection footer = Navigate.get(FooterSection.class);
        Assert.assertEquals("Bag id is not same as in Shopping bag", bagId, footer.getBagId());
        Assert.assertNotNull("Copyright text is not displaying", footer.copyrightCopy.getText());
    }

    @And("^I click back to bag link in (?:checkout|order confirmation) page$")
    public void iClickBackToBagLinkInCheckoutPage() {
        Checkout checkout = signedIn() ? Navigate.get(SignedInCheckout.class) : Navigate.get(GuestCheckout.class);
        checkout.headerSection().clickBackToBagIcon();
    }

    @And("^I place order with \"([^\"]*)\" card$")
    public void iPlaceOrderWithCard(String cardType) {
        pausePageHangWatchDog();
        CreditCard card = CreditCards.getValidCard(cardType);
        Checkout checkout = signedIn() ? Navigate.get(SignedInCheckout.class) : Navigate.get(GuestCheckout.class);
        checkout.paymentSection().addPaymentMethod(card, TestUsers.generateRandomEmail(7));
        if (signedIn() && !card.getSecurityCode().isEmpty()) {
            Navigate.get(PaymentSignedInSection.class).submitBySecurityCode(card.getSecurityCode());
        }
        Clicks.click(checkout.orderReviewSection().placeOrder.getWrappedElement());
        Assert.assertTrue("Not on order confirmation page", Wait.until(() -> Navigate.get(OrderConfirmation.class, true).orderNumber.isDisplayed(), 30));
        resumePageHangWatchDog();
    }

    @Then("^I should not see (shipping address|shipping method|gift options|contact info) section")
    public void iShouldNotSeeShippingAddressSection(String sectionName) {
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        switch (sectionName) {
            case "shipping address":
                Assert.assertFalse(Wait.until(signedInCheckout.changeShippingAddress::isDisplayed));
                break;
            case "shipping method":
                Assert.assertFalse(Wait.until(signedInCheckout.changeShippingMethod::isDisplayed));
                break;
            case "gift options":
                Assert.assertFalse(Wait.until(Navigate.get(SignedInGiftCardsSection.class).giftCardsSection::isDisplayed));
                break;
            case "contact info":
                Assert.assertFalse(Wait.until(Navigate.get(ContactInfoSection.class).contactInfoSection::isDisplayed));
        }
    }

    @Then("^I (should|should not) see standard shipping address fields")
    public void iShouldNotSeeStandardShippingAddressFields(String isVisible) {
        Checkout checkout = signedIn() ? Navigate.get(SignedInCheckout.class) : Navigate.get(GuestCheckout.class);
        Assert.assertTrue("Shipping address section " + isVisible + " be displayed",
                Wait.until(() -> checkout.shippingSection().shippingOptions().shippingAddressSection.exists() == isVisible.equals("should"), 10));
    }

    @And("^I should see vgc shippping copy in (checkout|order confirmation) page$")
    public void iShouldSeeVgcShipppingCopyInCheckoutPage(String pageName) {
        boolean isConditionSatisfied;
        if (pageName.equals("checkout")) {
            Checkout checkout = signedIn() ? Navigate.get(SignedInCheckout.class) : Navigate.get(GuestCheckout.class);
            isConditionSatisfied = Wait.until(checkout.shippingSection().shippingOptions().vgcShippingInfo::isDisplayed, 30);
        } else {
            isConditionSatisfied = Wait.until(Navigate.get(OrderConfirmation.class).shippingEgiftCardSection::isDisplayed, 30);
        }
        Assert.assertTrue("VGC shipping copy is not displaying in " + pageName + " page", isConditionSatisfied);
    }

    @And("^I (should|should not) see gift box indicator in gift options summary$")
    public void iShouldSeeGiftBoxIndicatorInGiftOptionsSummary(String condition) {
        ShippingSection shippingSection = Navigate.get(ShippingSection.class);
        Wait.until(shippingSection.giftOptionsSummary::isDisplayed, 30);
        String giftOptionsSummaryInfo = shippingSection.giftOptionsSummary.getText();
        Assert.assertTrue((giftOptionsSummaryInfo.contains("Gift box") || giftOptionsSummaryInfo.contains("Bloomingdale's Gift Wrap")) == condition.equals("should"));
    }

    @Then("^I should see gift option indicators in (checkout|order confirmation) page$")
    public void iShouldSeeGiftOptionIndicatorsInOrderConfirmationPage(String pageName) {
        boolean isDisplayed;
        if (bloomingdales()) {
            String giftOptionsSummaryInfo;
            if (pageName.equals("checkout")) {
                ShippingSection shippingSection = Navigate.get(ShippingSection.class);
                Wait.until(shippingSection.giftOptionsSummary::isDisplayed, 30);
                giftOptionsSummaryInfo = shippingSection.giftOptionsSummary.getText();
            } else {
                OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class);
                Wait.until(orderConfirmation.giftOptionsSummary::isDisplayed, 30);
                giftOptionsSummaryInfo = orderConfirmation.giftOptionsSummary.getText();
            }
            isDisplayed = (giftOptionsSummaryInfo.contains("Gift message") || giftOptionsSummaryInfo.contains("Gift Message")) &&
                    (giftOptionsSummaryInfo.contains("Gift box") || giftOptionsSummaryInfo.contains("Bloomingdale's Gift Wrap")) &&
                    (giftOptionsSummaryInfo.contains("Hide prices") || giftOptionsSummaryInfo.contains("Gift Receipt"));
            Assert.assertTrue("Gift option indicators are not displaying in " + pageName + " page", isDisplayed);
            Assert.assertTrue("Gift message is not as expected", giftOptionsSummaryInfo.contains("test message"));
        } else {
            String giftMessage;
            if (pageName.equals("checkout")) {
                ShippingSection shippingSection = Navigate.get(ShippingSection.class);
                Wait.until(shippingSection.giftMessageLine1Lable::isDisplayed, 30);
                isDisplayed = shippingSection.giftMessageLine1Lable.exists() && shippingSection.hidePricesIndicator.exists() && shippingSection.giftBoxIndicator.exists();
                giftMessage = shippingSection.giftMessageLine1Lable.getText();
            } else {
                OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class);
                Wait.until(orderConfirmation.orderNumber::isDisplayed, 30);
                isDisplayed = orderConfirmation.giftMessageLine1Lable.exists() && orderConfirmation.hidePricesIndicator.exists() && orderConfirmation.giftBoxIndicator.exists();
                giftMessage = orderConfirmation.giftMessageLine1Lable.getText();
            }
            Assert.assertTrue("Gift option indicators are not displaying in " + pageName + " page", isDisplayed);
            Assert.assertEquals("Gift message is not as expected", giftMessage, "test message");
        }
        if (pageName.equals("checkout")) {
            OrderSummarySection orderSummarySection = Navigate.get(OrderSummarySection.class);
            Wait.until(orderSummarySection.giftWrapCost::exists, 20);
            Assert.assertTrue("Gift wrap cost in order summary is not in expected format or returned Null",
                    orderSummarySection.giftWrapCost.getText().matches("\\$\\d+.\\d\\d"));
        }
    }

    @And("^I get bag id in shopping bag page$")
    public void iGetBagIdInShoppingBagPage() {
        bagId = Navigate.get(ShoppingBag.class).getBagId();
        logger.debug("User Bag Id: " + bagId);
    }

    @And("^I update the customer name as \"(.*) (.*)\"$")
    public void iUpdateTheCustomerNameAs(String newFirstName, String newLastName) {
        pausePageHangWatchDog();
        if (signedIn()) {
            Navigate.get(SignedInCheckout.class).expandShippingAddressSection().editShippingAddress.click();
        }
        Navigate.get(AddressInfoSection.class).fillNewCustomerName(newFirstName, newLastName);
        (signedIn() ? Navigate.get(SignedInCheckout.class).saveShippingAddress : Navigate.get(ShippingOptions.class).continueShippingCheckoutButton).click();
        Wait.forLoading(By.className("mask"));
        resumePageHangWatchDog();
    }

    @And("^I should see \"([^\"]*)\" in shipping summary section$")
    public void iShouldSeeInShippingSummarySection(String customerName) {
        Assert.assertTrue("Updated data is not persisting in shipping address section",
                Navigate.get(ShippingSection.class).shippingAddressSummary.getText().contains(customerName));
    }

    @And("^I select gift box in gift options$")
    public void iSelectGiftBoxInGiftOptions() {
        Navigate.get(ShippingSection.class).shippingOptions().giftBox.set(true);
    }

    @Then("^I should see available shipping methods with name, transit time and the price$")
    public void iShouldSeeAvailableShippingMethodsWithNameTransitTimeAndThePrice() {
        List<String> shippingMethods = Navigate.get(ShippingOptions.class).getAvailableShippingMethods();
        JSONArray methods = signedIn() ? ShopAppClient.getSignedInOrderDetails().getJSONObject("shippingDetails").getJSONArray("availableShippingMethods") :
                ShopAppClient.getShippingOptions().getJSONArray("availableShippingMethods");
        if (methods.length() == 0) {
            Assert.fail("ERROR: No shipping methods are displayed");
        }
        int index = 0;
        for (int i = 0; i < methods.length(); i++) {
            JSONObject displayedShipping = methods.getJSONObject(i);
            if (displayedShipping.getBoolean("displayShippingMethod")) {
                String shippingMethodInfo = shippingMethods.get(index);
                String estimatedDelivery = macys() ? "estimatedDelivery" : "averageDeliveryDays";
                Assert.assertTrue("Shipping description is not as expected", shippingMethodInfo.contains(displayedShipping.getString("description")));
                if (!displayedShipping.getString("description").equalsIgnoreCase("Standard")) {
                    Assert.assertTrue("Shipping estimated delivery is not as expected", shippingMethodInfo.contains(displayedShipping.getString(estimatedDelivery)));
                }
                Assert.assertTrue("Shipping display cost is not as expected", shippingMethodInfo.contains(displayedShipping.getString("shippingDisplayCost")));
                index++;
            }
        }
    }

    @Then("^I verify below field level error messages in (shipping|payment) section$")
    public void iVerifyBelowFieldLevelErrorMessagesInShippingSection(String section, List<String> expectedErrors) {
        List<String> actualErrors = section.equals("shipping") ? Navigate.get(AddressInfoSection.class).getFieldLevelErrors() :
                Navigate.get(PaymentGuestSection.class).getCreditCardFieldLevelErrors();
        Assert.assertTrue(actualErrors.containsAll(expectedErrors));
        logger.info("Expected field level error message(s) verification is successful");
    }

    @And("^I verify field level error message as \"([^\"]*)\" for invalid (data|zip code|phone number)$")
    public void iVerifyFieldLevelErrorMessageAsForInvalidPhoneNumber(String errorMessage, String field) {
        AddressInfoSection addressInfo = Navigate.get(AddressInfoSection.class);
        if (field.equals("data")) {
            List<WebElement> elements = Stream.of(addressInfo.firstName, addressInfo.lastName, addressInfo.addressLine1,
                    addressInfo.addressLine2, addressInfo.addressCity).collect(Collectors.toList());
            elements.forEach(element -> {
                element.sendKeys("Test123 %^&*");
                element.sendKeys(Keys.TAB);
                Assert.assertEquals(errorMessage, element.findElement(By.xpath(XPATH_FOR_FIELD_LEVEL_ERROR)).getText());
            });
        } else {
            WebElement element = field.equals("zip code") ? addressInfo.addressZipCode : addressInfo.phoneNumber;
            element.sendKeys("4321");
            element.sendKeys(Keys.TAB);
            Assert.assertEquals(errorMessage, element.findElement(By.xpath(XPATH_FOR_FIELD_LEVEL_ERROR)).getText());
        }
    }

    @And("^I select 'shipping to' change country link$")
    public void iSelectShippingToChangeCountryLink() {
        Home home = Navigate.get(Home.class);
        if (macys()) {
            home.headerAndFooter().gotoChangeCountry2.click();
        } else {
            home.headerAndFooter().gotoChangeCountry.click();
        }
    }

    @Then("^I should be navigated to Domestic Home Page$")
    public void iShouldBeNavigatedToDomesticHomePage() {
        Wait.until(() -> Navigate.get(Home.class).headerAndFooter().countryCodeFlag.isDisplayed(), 10);
        Home home = Navigate.get(Home.class);
        if (macys()) {
            String src = home.headerAndFooter().countryCodeFlag.getAttribute("src");
            Assert.assertTrue("Domestic US Home Page is not displayed : " + src, src.contains("/US."));
        } else {
            Assert.assertTrue("Domestic US Home Page is not displayed and currency is not displayed as USD", home.headerAndFooter().ishipCurrency.getText().contains("USD"));
        }
    }

    @And("^I navigate to registry page in iship mode$")
    public void iNavigateToRegistryPageInIshipMode() {
        Navigate.get(Home.class).headerAndFooter().gotoWeddingRegistry.click();
    }

    @Then("^I should see the item level error:$")
    public void iShouldSeeTheItemLevelError(DataTable table) {
        Map<String, String> messages = new HashMap<>(table.asMap(String.class, String.class));
        ShoppingBag shoppingBag = Navigate.to(ShoppingBag.class);
        String actualMessage = shoppingBag.itemLevelErrorMessage.getText();
        String expectedMessage = messages.get(TestUsers.getSiteType().toUpperCase());
        Assert.assertTrue("Expected error message: " + expectedMessage + " is not displayed on Shopping Bag page.",
                actualMessage.contains(expectedMessage));
    }

    @When("^I navigate to the PDP page from shopping bag page$")
    public void iNavigateToThePDPPageFromShoppingBagPage() {
        Navigate.get(ShoppingBag.class).navigateToPDP(0);
    }

    @Then("^I should see envoy checkout page$")
    public void iShouldSeeEnvoyCheckoutPage() {
        Assert.assertTrue("shipping_iFrame not present on iship_checkout page",
                Wait.until(() -> Navigate.get(IShipCheckout.class).shippingFrame.isDisplayed(), 10));
    }

    @And("^I verify the currency is \"([^\"]*)\" on the Border Free page$")
    public void iVerifyTheCurrencyIsOnTheBorderFreePage(String currency) {
        IShipCheckout checkout = Navigate.get(IShipCheckout.class);
        switchToFrame("iship_checkout.shipping_iFrame");
        Wait.until(() -> checkout.orderTotal.isDisplayed(), 10);
        Assert.assertTrue("currency '" + currency + "' is not displayed in Border Free page",
                checkout.orderTotal.getText().contains(currency));
    }

    @And("^I should see \"([^\"]*)\" button on Ship To United States Overlay$")
    public void iShouldSeeButtonOnShipToUnitedStatesOverlay(String button) {
        ShippingToTheUnitedStates shippingToTheUnitedStates = Navigate.get(ShippingToTheUnitedStates.class);
        WebElement buttonElement = button.equals("Continue Checkout") ? shippingToTheUnitedStates.continueCheckout :
                shippingToTheUnitedStates.returnToTheInternationalSite;
        Elements.elementShouldBePresent(buttonElement);
    }

    @When("^I select \"([^\"]*)\" on Ship To United States Overlay$")
    public void iSelectOnShipToUnitedStatesOverlay(String button) throws Throwable {
        ShippingToTheUnitedStates shippingToTheUnitedStates = Navigate.get(ShippingToTheUnitedStates.class);
        WebElement buttonElement = button.equals("Continue Checkout") ? shippingToTheUnitedStates.continueCheckout :
                shippingToTheUnitedStates.returnToTheInternationalSite;
        buttonElement.click();
    }

    @Then("^I should see contact phone as \"([^\"]*)\" in payment summary$")
    public void iShouldSeeContactPhoneAsInPaymentSummary(String updatedPhone) {
        String summaryInfo = signedIn() ? Navigate.get(ContactInfoSection.class).contactInfoSection.getText() :
                Navigate.get(PaymentGuestSection.class).getPaymentSummarySectionInfo();
        Assert.assertTrue("Expected phone number is not displayed", summaryInfo.contains(updatedPhone));
    }

    @Then("^I update paypal contact details$")
    public void iUpdatePaypalContactDetails() {
        pausePageHangWatchDog();
        PaymentSignedInSection signedinPayment = Navigate.get(PaymentSignedInSection.class);
        signedinPayment.phoneNumber.clear();
        signedinPayment.phoneNumber.sendKeys("(867) 921-2222");
        iClickSaveInShippingSection("paypal");
        Assert.assertTrue("Expected phone number is not displayed", signedinPayment.paymentInfo.getText().contains("(867) 921-2222"));
        resumePageHangWatchDog();
    }

    @And("^I update the contact phone as \"([^\"]*)\"$")
    public void iUpdateTheContactPhoneAs(String newPhone) {
        pausePageHangWatchDog();
        if (signedIn()) {
            ContactInfoSection contact = Navigate.get(ContactInfoSection.class);
            contact.phone.clear();
            contact.phone.sendKeys(newPhone);
            iClickSaveInShippingSection("contact info");
        } else {
            PaymentGuestSection paymentSection = Navigate.get(PaymentGuestSection.class);
            paymentSection.phoneNumber.clear();
            paymentSection.phoneNumber.sendKeys(newPhone);
            if (paymentSection.securityCode.isDisplayed() && paymentSection.securityCode.isEnabled() && paymentSection.securityCode.getText().isEmpty()) {
                paymentSection.securityCode.sendKeys("222");
            }
            paymentSection.continueCheckout();
            paymentSection.waitForEditIsDisplayed();
        }
        resumePageHangWatchDog();
    }

    @Then("^I (should|should not) see credit card section in responsive checkout page$")
    public void iShouldSeeCreditCardSectionInResponsiveCheckoutPage(String isVisible) {
        Assert.assertTrue("Credit card section " + isVisible + " be displayed",
                Wait.until(() -> (signedIn() ? Navigate.get(PaymentSignedInSection.class).creditCardInfo
                        : Navigate.get(PaymentGuestSection.class).creditCardContainer).isDisplayed() == isVisible.equals("should")));
    }

    @When("^I select (paypal|credit card) as tender type$")
    public void iSelectPaypalAsTenderType(String paymentType) {
        WebElement elementToClick;
        if (paymentType.equals("paypal")) {
            elementToClick = (signedIn() ? Navigate.get(PaymentSignedInSection.class).paypalRadioButton :
                    Navigate.get(PaymentGuestSection.class).paypalRadioButton).getWrappedElement();
        } else {
            elementToClick = (signedIn() ? Navigate.get(PaymentSignedInSection.class).creditCardRadioButton :
                    Navigate.get(PaymentGuestSection.class).creditCardRadioButton).getWrappedElement();
        }
        Wait.until(elementToClick::isDisplayed, 10);
        Clicks.javascriptClick(elementToClick);
        Wait.forLoading(By.className("loader-container"));
    }

    @When("^I should see (paypal|contact|credit card) information in payment summary$")
    public void iShouldSeePaypalContactInformationInContactSummary(String section) {
        String paymentSummary = signedIn() ? Navigate.get(PaymentSignedInSection.class).paymentInfo.getText() :
                Navigate.get(PaymentGuestSection.class).getPaymentSummarySectionInfo();
        JSONObject paymentInfo = signedIn() ? ShopAppClient.getSignedInOrderDetails().getJSONObject("paymentDetails") : ShopAppClient.getPaymentOptions();
        if (section.equals("paypal")) {
            Assert.assertTrue("Paypal email is not displayed in payment summary",
                    paymentSummary.contains(TestUsers.getPayPalInformation().get("email")));
        } else if (section.equals("contact")) {
            if (signedIn()) {
                JSONObject contactInfo = ShopAppClient.getSignedInOrderDetails().getJSONObject("user");
                Assert.assertTrue("Email not displayed as expected in contact summary",
                        paymentSummary.contains(contactInfo.getString("email")));
                Assert.assertTrue("Phone number not displayed as expected in contact summary",
                        paymentSummary.contains(contactInfo.getString("phoneNumber").replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3")));
            } else {
                Assert.assertTrue("Email not displayed as expected in contact summary",
                        paymentSummary.contains(paymentInfo.getJSONObject("billingContact").getString("email")));
                Assert.assertTrue("Phone number not displayed as expected in contact summary",
                        paymentSummary.contains(paymentInfo.getJSONObject("billingAddress").getString("phone").replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3")));
            }
        } else {
            JSONObject creditCardDetails = paymentInfo.getJSONObject("creditCard");
            Assert.assertTrue("Card type not displayed as expected", paymentSummary.contains(creditCardDetails.getString("creditCardDisplayText")));
            Assert.assertTrue("Card number not displayed as expected", paymentSummary.contains(creditCardDetails.getString("cardNumber")));
            if (creditCardDetails.getBoolean("cvvExpDateRequired")) {
                Assert.assertTrue("Expiration Date not displayed as expected", paymentSummary.contains(
                        String.format("%s/%s", creditCardDetails.getString("expMonth"), creditCardDetails.getString("expYear")))
                );
            }
        }
    }

    @And("^I should see gift card section in (enabled|disabled) state$")
    public void iShouldSeeGiftCardSectionInEnabledState(String elementState) {
        Assert.assertTrue("Gift card section should be in " + elementState + " state",
                Navigate.get(PaymentGuestSection.class).addGiftCardButton.isEnabled() != elementState.equals("disabled"));
    }

    @And("^I should see paypal disclaimer text in (payment|gift card) section as \"([^\"]*)\"$")
    public void iShouldSeePaypalDisclaimerTextInPaymentSectionAs(String sectionName, String expectedNote) {
        pausePageHangWatchDog();
        HtmlElement elementToCheck;
        if (signedIn()) {
            if (sectionName.equals("gift card")) {
                SignedInGiftCardsSection giftCardSection = Navigate.get(SignedInGiftCardsSection.class);
                if (!Wait.until(giftCardSection.giftCardNumber::isDisplayed)) {
                    Clicks.click(giftCardSection.giftCardsSection.getWrappedElement());
                    Wait.until(giftCardSection.giftCardNumber::isDisplayed);
                }
                elementToCheck = giftCardSection.gcPaypalNote;
            } else {
                elementToCheck = Navigate.get(PaymentSignedInSection.class).paypalDisclaimer;
            }
        } else {
            PaymentGuestSection paymentSection = Navigate.get(PaymentGuestSection.class);
            if (sectionName.equals("gift card") && paymentSection.addGiftCardButton.exists()) {
                paymentSection.addGiftCardButton.click();
                Wait.until(() -> paymentSection.giftCardNumber.exists());
            }
            elementToCheck = sectionName.equals("payment") ? paymentSection.paypalDisclaimer : paymentSection.paypalDisclaimerGcSection;
        }
        Assert.assertTrue("Paypal disclaimer text not displayed", elementToCheck.exists());
        Assert.assertTrue("Expected Paypal disclaimer text is not displayed", elementToCheck.getText().contains(expectedNote));
        resumePageHangWatchDog();
    }

    @And("^I (should|should not) see paypal login disclaimer text in paypal section(?: as \"([^\"]*)\")?$")
    public void iShouldSeePaypalLoginDisclaimerTextInPaypalSectionAs(String isVisible, String expectedNote) {
        pausePageHangWatchDog();
        HtmlElement element;
        boolean visibility;
        if (signedIn()) {
            element = Navigate.get(PaymentSignedInSection.class).paypalLoginDisclaimer;
            visibility = element.exists();
        } else {
            element = Navigate.get(PaymentGuestSection.class).paypalLoginDisclaimer;
            visibility = element.isDisplayed();
        }
        Assert.assertTrue("Paypal login disclaimer " + isVisible + " be displayed", visibility == isVisible.equals("should"));
        if (expectedNote != null) {
            Assert.assertEquals("Expected Paypal login disclaimer text is not displayed", expectedNote, element.getText().trim());
        }
        resumePageHangWatchDog();
    }

    @Then("^I verify SMS legal copy \"([^\"]*)\" on (shipping|order confirmation) page$")
    public void iVerifySMSLegalCopyOnShippingPage(String message, String page) {
        WebElement element = page.equals("shipping") ? Navigate.get(ShippingOptions.class).bopsSmsOption : Navigate.get(OrderConfirmation.class).bopsSmsCopy;
        Assert.assertTrue("SMS legal copy is not displayed as expected", element.getText().contains(message));
    }

    @Then("^I should see \"([^\"]*)\" order modified or cancel message$")
    public void iShouldSeeOrderModifiedOrCancelMessage(String message) {
        Assert.assertTrue("order modified or cancel message is not displayed as expected",
                Navigate.get(OrderConfirmation.class).bopsOrderChange.getText().contains(message));
    }

    @And("^I expand select gift options and gift box for signed in user$")
    public void iExpandSelectGiftOptionsAndGiftBoxForSignedInUser() {
        pausePageHangWatchDog();
        ShippingOptions shippingOptions = Navigate.get(SignedInCheckout.class).shippingSection().shippingOptions();
        Assert.assertTrue(Wait.until(shippingOptions.giftOptionsExpandSignedIn::exists));
        shippingOptions.giftOptionsExpandSignedIn.click();
        Assert.assertTrue(Wait.until(shippingOptions.giftMessage::exists));
        shippingOptions.giftMessage.click();
        Assert.assertTrue(Wait.until(shippingOptions.giftMessageField1::exists));
        shippingOptions.giftMessageField1.sendKeys("test message");
        if (macys()) {
            shippingOptions.giftBox.set(true);
        } else {
            Clicks.click(shippingOptions.giftBox.getWrappedElement());
        }
        shippingOptions.giftOptionsSaveSignedIn.click();
        Wait.forLoading(By.id("rc-mask"));
        resumePageHangWatchDog();
    }

    @And("^I should see gift box option in (disabled|enabled) state$")
    public void iShouldSeeGiftBoxOptionInDisabledState(String condition) {
        ShippingOptions shippingOptions = Navigate.get(ShippingOptions.class);
        if (Wait.until(shippingOptions.giftOptionsExpandSignedIn::exists, 10)) {
            shippingOptions.giftOptionsExpandSignedIn.click();
        } else if (!Wait.until(shippingOptions.giftBox::isDisplayed, 10)) {
            shippingOptions.giftOption.click();
        }
        Assert.assertTrue(shippingOptions.giftBox.isEnabled() == condition.equals("enabled"));
    }

    @Then("^I should see registry item in shopping bag page$")
    public void iShouldSeeRegistryItemInShoppingBagPage() {
        ShoppingBag shoppingBag = Navigate.to(ShoppingBag.class);
        Assert.assertTrue(Wait.until(shoppingBag.registrantNameDetails::exists));
    }

    @Then("^I should see color for bonus items$")
    public void iShouldSeeColorForBonusItems() {
        ShoppingBag shoppingBag = Navigate.to(ShoppingBag.class);
        Assert.assertTrue(Wait.until(shoppingBag.bonusItemColor::exists));
    }

    @When("^I add registry product to my bag from BVR page$")
    public void iAddRegistryProductToMyBagFromBVRPage() throws Throwable {
        com.macys.sdt.shared.steps.website.ShopAndBrowse sb = new com.macys.sdt.shared.steps.website.ShopAndBrowse();
        sb.iNavigateToPdp("registrable and orderable", "");
        sb.I_add_registry_product_to_BVR_page_from_standard_PDP_Page();
        if (Elements.elementPresent("home.continue_shopping")) {
            Clicks.click("home.continue_shopping");
            sb.I_add_registry_product_to_BVR_page_from_standard_PDP_Page();
        }
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("registry_bvr.bvr_prod_list", 10);
        }
        Wait.untilElementPresent("registry_bvr.prod_add_to_bag");
        Clicks.click("registry_bvr.prod_add_to_bag");
        Wait.secondsUntilElementPresent("registry_add_to_bag.registry_chkout_button", 10);
        Clicks.javascriptClick("registry_add_to_bag.registry_chkout_button");
        Wait.forPageReady("shopping_bag");
    }

    @And("^I visit the web site as a DSV registry user$")
    public void iVisitTheWebSiteAsRegistryUser() {
        new PageNavigation().I_visit_the_web_site_as_a_guest_user();
        RegistryHome registryHome = Navigate.get(Home.class).registryHome();
        RegistrySignIn registrySignIn = registryHome.regsitrySignIn();
        JSONObject login = RegistryLoginActions.getLoginDetails();
        RegistryManager registryManager = registrySignIn.login(login.getString("email"), login.getString("password"));
        registryManager.waitForReady();
        Assert.assertTrue(registryManager.isReady());
    }

    @Then("^I should see default shipping text and shipping price as \"([^\"]*)\"$")
    public void iShouldSeeDefaultShippingTextAndShippingPriceAs(String shippingprice) throws Throwable {
        Wait.forPageReady();
        String expectedMessage = "Your e-gift card(s) will be emailed within 24 hours to the address(es) provided.";
        String message = WebDriverManager.getWebDriver().findElement(By.className("rc-vgc-message")).getText();
        Assert.assertTrue("e-gift card message is not expected", message.contains(expectedMessage));
        String shipping = WebDriverManager.getWebDriver().findElement(By.id("rc-at-shipping-cost-value")).getText();
        Assert.assertTrue("shipping price is not expected", shipping.equals(shippingprice));
    }
}
