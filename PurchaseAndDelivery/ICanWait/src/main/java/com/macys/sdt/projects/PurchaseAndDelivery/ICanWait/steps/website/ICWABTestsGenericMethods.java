package com.macys.sdt.projects.PurchaseAndDelivery.ICanWait.steps.website;

import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.Checkout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.GuestCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.List;

import static com.macys.sdt.shared.steps.website.CheckoutSteps.*;

/**
 * Created by yh00989 on 4/15/2017.
 */
public class ICWABTestsGenericMethods {

    @And("^I pass \"([^\"]*)\" in segment cookie$")
    public void iPassInSegmentCookie(String experimentCookie) {
        String segmentCookie = Cookies.getCookieValue("SEGMENT");

        //If experiment cookie is not already added.
        if (!segmentCookie.contains(experimentCookie)) {
            //add experiment cookie
            Cookies.addSegment(experimentCookie);
        }
    }

    @Then("^I verify shipping fee in order summary section is \"([^\"]*)\"$")
    public void iVerifyShippingFeeInOrderSummarySectionIs(String shippingFee) {
        Checkout checkout = signedIn() ? Navigate.get(SignedInCheckout.class) : Navigate.get(GuestCheckout.class);
        Assert.assertEquals(shippingFee, checkout.orderReviewSection().shippingInfo.getText());
    }

    @Then("^I (should|should not) see icw mMoney message on shipping method section based on experiment id:$")
    public void iShouldNotSeeIcwMMoneyMessageOnShippingMethodSectionBasedOnExperimentId(String condition, List<String> msg) {
        if (condition.equals("should")) {
            Assert.assertTrue("ICW mMoney message is not displayed in shipping method section due to experiment data set up", shippingDetails.get("shippingMethodDetails").toString().contains(msg.get(0)));
        } else {
            Assert.assertFalse("ICW mMoney message is displayed in shipping method section due to experiment data set up", shippingDetails.get("shippingMethodDetails").toString().contains(msg.get(0)));
        }
    }
}
