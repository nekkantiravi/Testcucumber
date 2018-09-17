package com.macys.sdt.projects.PurchaseAndDelivery.ICanWait.steps.website;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.ShippingSection;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

/**
 * Created by atepliashin on 12/9/16.
 */
public class ICWCheckoutSteps {

    @Then("learn more section has redeem period info")
    public void verifyLearnMoreRedeem() {
        String learnMoreText = Navigate.get(ShippingSection.class).icwLearnMoreContent.getText();
        String redeemPeriod = "(?s).+USE YOUR MACY'S MONEY REWARD CARD from \\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2}.+";
        assertTrue(String.format("\"%s\" doesn't match \"%s\"", learnMoreText, redeemPeriod),
                learnMoreText.matches(redeemPeriod));
    }
}
