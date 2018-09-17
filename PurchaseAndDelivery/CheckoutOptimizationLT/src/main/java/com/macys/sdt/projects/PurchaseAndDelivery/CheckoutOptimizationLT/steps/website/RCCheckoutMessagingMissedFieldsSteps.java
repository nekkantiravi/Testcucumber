package com.macys.sdt.projects.PurchaseAndDelivery.CheckoutOptimizationLT.steps.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.macys.sdt.framework.interactions.Elements.isElementInView;
import static com.macys.sdt.framework.utils.StepUtils.macys;

/**
 * Created by UC06174 on 8/25/2017.
 */
public class RCCheckoutMessagingMissedFieldsSteps {

    @Then("^I see '(.*)' message for 'Shipping Address' section$")
    public void iSeeMissedFieldMessageForShippingAddressSection(String expectedMessage) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.expandShippingAddressSection().addNewAddress::isDisplayed);
        Assert.assertTrue("Shipping Address missed message doesn't appear", checkout.missedFieldMessage.isDisplayed());
        Assert.assertEquals("Shipping Address message copy is incorrect", expectedMessage, checkout.missedFieldMessage.getText());
    }

    @And("^I see '(Review It Now|Scroll To Section)' link$")
    public void iSeeReviewItNowLink(String expectedMessage) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.missedFieldMessageScrollToLink::isDisplayed);
        Assert.assertTrue("Review It Now link doesn't appear", checkout.missedFieldMessageScrollToLink.isDisplayed());
        Assert.assertEquals("Review it now link text is incorrect", expectedMessage, checkout.missedFieldMessageScrollToLink.getText());
    }

    @When("^I click '(?:Review it Now|Scroll To Section)' link$")
    public void iClickReviewItNowLink() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        checkout.missedFieldMessageScrollToLink.click();
    }

    @Then("^Checkout page is scrolled to 'Shipping Address' section$")
    public void checkoutPageIsScrolledToShippingAddressSection() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Assert.assertTrue("User is not scrolled to Shipping Address section", isElementInView(checkout.addNewAddress));
    }

    @Then("^I see '(.*)' message for 'Payment Method' section$")
    public void iSeeMissedFieldMessageForPaymentMethodSection(String expectedMessage) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.paymentSection().addNewCard::isDisplayed);
        Assert.assertTrue("Payment Method missed message doesn't appear", checkout.missedFieldMessage.isDisplayed());
        Assert.assertEquals("Payment Method message copy is incorrect", expectedMessage, checkout.missedFieldMessage.getText());
    }

    @Then("^Checkout page is scrolled to 'Payment Method' section$")
    public void checkoutPageIsScrolledToPaymentMethodSection() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.paymentSection().addNewCard::isDisplayed);
        Assert.assertTrue("User is not scrolled to Payment Method section", isElementInView(checkout.paymentSection().addNewCard));
    }

    @Then("^I see '(.*)' message for 'Security Code' section$")
    public void iSeeMissedFieldMessageForSecurityCodeSection(String expectedMessage) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.paymentSection().securityCode::isDisplayed);
        Assert.assertTrue("Security Code missed message doesn't appear", checkout.missedFieldMessage.isDisplayed());
        Assert.assertEquals("Security Code message copy is incorrect", expectedMessage, checkout.missedFieldMessage.getText());
    }

    @And("^I see 'Add It Now' link$")
    public void iSeeAddItNowLink() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Assert.assertTrue("Add It Now link doesn't appear for Security Code field", checkout.missedFieldMessageSecurityCodeAddLink.isDisplayed());
        Assert.assertEquals("Add It Now link text is incorrect", "Add It Now", checkout.missedFieldMessageSecurityCodeAddLink.getText());
    }

    @When("^I click 'Add it Now' link$")
    public void iClickAddItNowLink() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        checkout.missedFieldMessageSecurityCodeAddLink.click();
    }

    @Then("^Checkout page is scrolled to 'Security Code' field$")
    public void checkoutPageIsScrolledToSecurityCodeField() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Assert.assertTrue("User is not scrolled to Security Code fields", isElementInView(checkout.paymentSection().securityCode));
    }

    @Then("^I see '(.*)' message for 'Pick Up In Store' section$")
    public void iSeeMissedFieldMessageForPickUpInStoreSection(String expectedMessage) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Assert.assertTrue("Pick Up In Store missed message doesn't appear", checkout.missedFieldMessage.isDisplayed());
        Assert.assertEquals("Pick Up In Store message copy is incorrect", expectedMessage, checkout.missedFieldMessage.getText());
    }

    @Then("^Checkout page is scrolled to 'Pick Up In Store' section$")
    public void checkoutPageIsScrolledToPickUpInStoreSection() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Assert.assertTrue("User is not scrolled to Pick Up In Store section", isElementInView(checkout.pickupInfoForm));
    }

    @And("^I click 'Edit' button in 'Contact Information' section$")
    public void iClickEditButtonInContactInformationSection() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.contactInfoSection().change::isDisplayed);
        checkout.contactInfoSection().change.click();
    }

    @Then("^I see '(.*)' message for 'Contact Information' section$")
    public void iSeeMissedFieldMessageForContactInformationSection(String expectedMessage) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.contactInfoSection().cancel::isDisplayed);
        Assert.assertTrue("Contact Information missed message doesn't appear", checkout.missedFieldMessage.isDisplayed());
        Assert.assertEquals("Contact Information message copy is incorrect", expectedMessage, checkout.missedFieldMessage.getText());
    }

    @Then("^Checkout page is scrolled to 'Contact Information' section$")
    public void checkoutPageIsScrolledToContactInformationSection() {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Assert.assertTrue("User is not scrolled to Contact Info section", isElementInView(checkout.contactInfoSection().cancel));
    }

    @And("^I add security code in Payment section on Checkout page$")
    public void iAddSecurityCodeInPaymentSectionOnCheckoutPage() {
        Navigate.get(SignedInCheckout.class).paymentSection().securityCode.sendKeys("222");
    }

    @When("^I click (.*) button$")
    public void iClickExpand_buttonButton(String buttonName) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.addShippingAddressFormButton::isDisplayed);
        switch (buttonName) {
            case "Expand_Gift_Options":
                checkout.giftOptionExpand.click();
                break;
            case "Expand_Loyallist":
                checkout.expandLoyallistSection.click();
                break;
            case "Expand_Apply_Gift_Cards":
                checkout.giftCardsSection().expandGiftCardSection.click();
                break;
            case "Expand_Plenti":
                checkout.lookupLink.click();
                break;
        }
    }

    @Then("^I see missed field message for the (.*)$")
    public void iSeeMissedFieldMessageForSection(String section) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        Wait.until(checkout.addShippingAddressFormButton::isDisplayed);
        switch (section) {
            case "Gift_Options":
                Wait.until(checkout.giftOptionsCancel::isDisplayed);
                section = macys() ? "Gift options" : "Gift Options";
                break;
            case "Loyallist":
                Wait.until(checkout.loyallistSectionCancel::isDisplayed);
                break;
            case "Apply_Gift_Cards":
                Wait.until(checkout.giftCardsSection().giftCardCancel::isDisplayed);
                break;
            case "Plenti":
                Wait.until(checkout.plentiCancel::isDisplayed);
                break;
            default:
                throw new IllegalArgumentException("Unknown section: " + section);
        }
        section = section.replaceAll("_", " ");
        String message = "Please review the " + section + " section." + (macys() ? " Scroll To Section" : " Review It Now");
        Assert.assertTrue(section + " missed message doesn't appear",
                checkout.missedFieldMessage.isDisplayed());
        Assert.assertEquals(section + " message copy is incorrect",
                String.format(message, section), checkout.missedFieldMessage.getText());
    }


    @Then("^Checkout page is scrolled to the (.*)$")
    public void checkoutPageIsScrolledToSection(String section) {
        SignedInCheckout checkout = Navigate.get(SignedInCheckout.class);
        switch (section) {
            case "Gift_Options":
                Assert.assertTrue("User is not scrolled to Gift Options section", Wait.until(() -> isElementInView(checkout.giftOptionsInfo)));
                break;
            case "Loyallist":
                Assert.assertTrue("User is not scrolled to Loyallist section", Wait.until(() -> isElementInView(checkout.loyallist)));
                break;
            case "Apply_Gift_Cards":
                Assert.assertTrue("User is not scrolled to Apply Gift Cards section", Wait.until(() -> isElementInView(checkout.giftCardsSection().applyGiftCard)));
                break;
            case "Plenti":
                Assert.assertTrue("User is not scrolled to Plenti section", Wait.until(() -> isElementInView(checkout.plentiCancel)));
                break;
        }
    }
}
