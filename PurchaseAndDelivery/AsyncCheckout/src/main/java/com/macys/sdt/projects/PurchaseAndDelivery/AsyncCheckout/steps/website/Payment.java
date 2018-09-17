package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.steps.website;

import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.PaymentSignedInSection;
import cucumber.api.java.en.Then;
import com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.utils.CreditCards;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by atepliashin on 1/19/17.
 */
public class Payment {

    @When("^I enter declined card info on checkout$")
    public void enterDeclinedCardOnCheckout() throws DataException {
        Checkout checkout;
        if (StepUtils.signedIn()) {
            checkout = Navigate.get(SignedInCheckout.class);
        } else {
            checkout = Navigate.get(GuestCheckout.class);
        }
        ProfileAddress address = new ProfileAddress();
        TestUsers.getRandomValidAddress(null, address);
        checkout.paymentSection().addPaymentMethod(CreditCards.getInvalidCard(), address.getEmail());
    }

    @When("^I enter declined card security code on checkout$")
    public void enterSecurityCode() throws DataException {
        Navigate.get(PaymentSignedInSection.class).submitBySecurityCode(CreditCards.getInvalidCard().getSecurityCode());
    }

    @When("^I retry payment for the order by (.+) credit card$")
    public void retryPayment(String cardType) throws DataException {
        CreditCard card = CreditCards.getValidCard(cardType);
        ProfileAddress address = new ProfileAddress();
        TestUsers.getRandomValidAddress(null, address);
        Navigate.get(Repayment.class).retryPayment(card, address);
    }

    @Then("^declined message should be present on repayment page$")
    public void validateDeclinedMessage() {
        String expectedText = StepUtils.macys() ? "Unfortunately, the payment method you provided was declined. " +
                "Please update your payment information below to complete your order. " +
                "For additional support, contact our Customer Service at 1-800-BUY MACY." :
                "We're sorry, your payment was declined. Please update your payment information to complete your order.";
        assertThat(Navigate.get(Repayment.class).declinedMessage.getText(), equalTo(expectedText));
    }

    @Then("^repayment successful$")
    public void validateRepayment() {
        assertTrue("Not on the order confirmation page",
                Navigate.get(OrderConfirmationRepayment.class).orderNumber.exists());
    }
}
