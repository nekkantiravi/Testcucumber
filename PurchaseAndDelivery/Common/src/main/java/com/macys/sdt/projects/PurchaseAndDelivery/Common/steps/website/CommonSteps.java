package com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website;

import com.macys.sdt.framework.utils.db.models.OrderServices;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.Checkout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.GuestCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.OrderConfirmation;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.Orders;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.framework.utils.StepUtils.signedIn;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by ilysenko on 8/6/17.
 */
public class CommonSteps {
    private static final Logger logger = LoggerFactory.getLogger(CommonSteps.class);

    @And("^I tap \"place order\" button in \"order review\" section$")
    public void clickPlaceOrderButtonOnCheckoutPage() {
        Checkout checkout = signedIn() ? Navigate.get(SignedInCheckout.class) : Navigate.get(GuestCheckout.class);
        checkout.orderReviewSection().placeOrder();
    }

    @Then("^I verify the order status$")
    public void checkOrderStatus() {
        String orderNumber = Navigate.get(OrderConfirmation.class).getNumber();

        String orderStatus = new OrderServices().getOrderConfirmationMessage(orderNumber);

        assertThat(orderStatus, not(nullValue()));
        assertThat("ERROR - ENV: Order placed in #{order_status} mode but not getting order status as SUCCESS, please check TIBCO routes",
                orderStatus,
                not("RCMPL"));
    }

    @When("^I save order details from confirmation page$")
    public void saveOrder() {
        OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class);
        Orders.add(orderConfirmation.getNumber(), orderConfirmation.getEmail());
    }
}
