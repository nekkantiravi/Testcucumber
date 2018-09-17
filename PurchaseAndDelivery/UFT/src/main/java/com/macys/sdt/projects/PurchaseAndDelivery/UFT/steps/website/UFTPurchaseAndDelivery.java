package com.macys.sdt.projects.PurchaseAndDelivery.UFT.steps.website;


import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.OrderConfirmation;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UFTPurchaseAndDelivery extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(UFTPurchaseAndDelivery.class);


    @Then("^I should see order total amount in order table is similar to order confirmation page value$")
    public void iShouldSeeOrderTotalAmountInOrderTableIsSimilarToOrderConfirmationPageValue()  {
        String errorMessage = "ERROR - APP: order total in DB is not similar to order total on order confirmation page";
        OrderConfirmation orderConfirmation = Navigate.get(OrderConfirmation.class);
        Assert.assertTrue("Not on Order confirmation page", Wait.until(orderConfirmation.orderNumber::exists, 30));
        String orderNumber = orderConfirmation.getNumber();
        Assert.assertTrue(errorMessage, orderConfirmation.getTotal().equals(CommonUtils.getOrderTotal(orderNumber).get("orderTotal")));
        logger.info("Order total amount in order table is similar to order confirmation page value");
    }

}