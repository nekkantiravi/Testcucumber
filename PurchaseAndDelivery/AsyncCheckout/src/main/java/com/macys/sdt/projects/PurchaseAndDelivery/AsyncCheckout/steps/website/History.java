package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.steps.website;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.OrderDetails;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.OrderStatus;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.Orders;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by atepliashin on 1/28/17.
 */
public class History {

    @When("^I lookup the last placed order$")
    public void lookupLastPlacedOrder() {
        OrderStatus orderStatus = Navigate.get(OrderStatus.class);
        orderStatus.lookupByEmail(Orders.last().getNumber(), Orders.last().getEmail());
    }

    @When("^I open order details for the last placed order$")
    public void openOrderDetails() {
        OrderStatus orderStatus = Navigate.get(OrderStatus.class);
        orderStatus.orderDetails(0);
    }

    @When("^I want to update payment( for the last declined order)?$")
    public void updatePayment(String onOrderHistory) {
        if (onOrderHistory != null) {
            Navigate.get(OrderStatus.class).updatePayment(0);
        } else {
            Navigate.get(OrderDetails.class).updatePayment();
        }
    }

    @When("^I visit order history page$")
    public void visitOrderHistory() throws EnvException {
        Navigate.to(Home.class).orderStatus();
    }

    @Then("^the last placed order must be present$")
    public void placedOrderPresent() {
        OrderStatus orderStatus = Navigate.get(OrderStatus.class);
        Wait.until(() -> {
            boolean b = orderStatus.orderNumberDetail.size() > 0 &&
                    orderStatus.orderNumberDetail.get(0).getText().equals(Orders.last().getNumber());
            if (!b) {
                Navigate.browserRefresh();
                orderStatus.waitForReady();
            }
            return b;
        }, 90);
    }

    @Then("^declined message should be present on (order history|order details) page$")
    public void validateDeclinedMessage(String page) {
        String actualText;
        if (page.equals("order history")) {
            OrderStatus orderStatus = Navigate.get(OrderStatus.class);
            Wait.until(() -> orderStatus.declinedPaymentError.size() > 0);
            actualText = orderStatus.declinedPaymentError.get(0).getText();

        } else {
            OrderDetails orderDetails = Navigate.get(OrderDetails.class);
            Wait.until(() -> orderDetails.errorMessage.isDisplayed());
            actualText = orderDetails.errorMessage.getText();
        }
        String expectedText = StepUtils.macys() ? "Unfortunately your payment method was declined. " +
                "Please use the link below to update your payment & complete your order." :
                "We're sorry, your payment was declined. Please update your payment information to complete your order.";
        assertThat(actualText, equalTo(expectedText));
    }
}
