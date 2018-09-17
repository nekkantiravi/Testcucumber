package com.macys.sdt.projects.PurchaseAndDelivery.CheckoutOptimizationLT.steps.website.usl;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.OrderSummarySection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.interfaces.PlentiSection;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import static com.macys.sdt.framework.utils.StepUtils.pausePageHangWatchDog;
import static com.macys.sdt.framework.utils.StepUtils.resumePageHangWatchDog;
import static org.junit.Assert.assertTrue;


public class UslSignedInPinErrorSteps {

    @And("^I should see redeem pin tooltip icon and popup on clicking tool tip icon$")
    public void checkForPlentiPinPopup() {
        PlentiSection plentiSection = Navigate.get(PlentiSection.class);
        assertTrue(plentiSection.uslRcRedeemPinTooltipIcon.isDisplayed());
        plentiSection.uslRcRedeemPinTooltipIcon.click();
        Wait.until(plentiSection.uslRcRedeemPinTooltipPopup::isDisplayed);
        assertTrue(plentiSection.uslRcRedeemPinTooltipPopup.isDisplayed());
    }

    @And("^I click on place order button for (?:plenti|shipping address) error$")
    public void clickPlaceOrderButton() {
        SignedInCheckout signedIn = Navigate.get(SignedInCheckout.class);
        pausePageHangWatchDog();
        Wait.forPageReady();

        Wait.until(() -> !signedIn.maskLayer.exists());
        Wait.until(signedIn.placeOrder::isDisplayed);

        if (signedIn.placeOrder.exists()) {
            signedIn.placeOrder.click();
        }

        Wait.forLoading(By.className("loader-container"));
        Wait.forPageReady();

        resumePageHangWatchDog();
    }

    @And("^I add usl as partial payment on payment page with redeem amount \"([^\"]*)\"$")
    public void iAddUslAsPayment(String amount) {
        PlentiSection plentiSection = Navigate.get(PlentiSection.class);
        Wait.until(plentiSection.useUsl::isDisplayed);

        plentiSection.useUsl.click();

        Wait.until(plentiSection.uslRcRedeemAmount::isDisplayed);

        plentiSection.uslRcRedeemAmount.clear();
        plentiSection.uslRcRedeemAmount.sendKeys(amount);
    }

    @And("^I add plenti pin \"([^\"]*)\"$")
    public void addWrongPlentiPin(String plentiPin) {
        PlentiSection plentiSection = Navigate.get(PlentiSection.class);
        Wait.until(plentiSection.uslRcRedeemPin::isDisplayed);

        plentiSection.uslRcRedeemPin.sendKeys(plentiPin);
        plentiSection.uslRcApplyPoints.click();
        OrderSummarySection orderSummarySection = Navigate.get(OrderSummarySection.class);

        Wait.until(orderSummarySection.giftPlentiRewardValues::isDisplayed);
    }

    @Then("^I should see the plenti redeem section open with wrong pin message$")
    public void verifyPlentiRedeemPinErrorMsg() {
        PlentiSection plentiSection = Navigate.get(PlentiSection.class);
        Wait.until(plentiSection.uslRcRedeemPin::isDisplayed);
        assertTrue(plentiSection.uslRcRedeemPin.isDisplayed());
    }
}
