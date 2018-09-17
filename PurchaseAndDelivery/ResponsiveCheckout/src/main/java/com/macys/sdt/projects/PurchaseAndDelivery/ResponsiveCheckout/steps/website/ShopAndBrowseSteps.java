package com.macys.sdt.projects.PurchaseAndDelivery.ResponsiveCheckout.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.ProductDisplay;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.getAllProductDetails;
import static com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag.updateQuantity;

public class ShopAndBrowseSteps {

    @Then("^an unavailability error message should be displayed on PDP Page$")
    public void anUnavailabilityErrorMessageShouldBeDisplayedOnPDPPage() {
        Elements.elementShouldBePresent(Navigate.get(ProductDisplay.class).unavailable);
    }

    @And("^I select ship to united states link on bag page$")
    public void iSelectShipToUnitedStatesLinkOnBagPage() {
        Navigate.get(ShoppingBag.class).shippingToUnitedStates.click();
    }

    @And("^I update shopping bag items with quantity (\\d+)$")
    public void iUpdateShoppingBagItemsWithQuantity(int quantity) {
        List<Product> bagItems = getAllProductDetails();
        bagItems.forEach(item -> updateQuantity(item, Integer.toString(quantity)));
    }

    @Then("^I should see updated total in shopping bag page for updated quantity (\\d+)$")
    public void iShouldSeeUpdatedTotalInShoppingBagPageForUpdatedQuantity(int quantity) {
        List<Product> bagItems = getAllProductDetails();
        bagItems.forEach(item -> {
            String individualPrice = Double.toString(((item.salePrice == 0.0) ? item.individualPrice : item.salePrice) * quantity);
            Assert.assertEquals("item total is not updated after updating the quantity", individualPrice, Double.toString(item.totalPrice));
        });
    }

    @Then("^I should see free shipping in shopping bag page$")
    public void iShouldSeeFreeShippingInShoppingBagPage() {
        Assert.assertTrue("Free Shipping is not displayed in shopping bag page",
                Navigate.get(ShoppingBag.class).estimatedShipping.getText().contains("FREE"));
    }

    @Then("^I should not see the applied promo code$")
    public void iShouldNotSeeTheAppliedPromoCode() {
        Assert.assertFalse(Wait.until(Navigate.get(ShoppingBag.class).promoCodeRemove::isDisplayed));
    }

    @And("^I apply (?:a|an) (valid|invalid) promo code in shopping bag page$")
    public void iApplyPromoCodeInShoppingBagPage(String validity) {
        boolean isPromoApplied;
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        Wait.until(shoppingBag.promoCode::isDisplayed);
        if (validity.equals("valid")) {
            if (StepUtils.macys()) {
                shoppingBag.promoCode.clear();
                shoppingBag.promoCode.sendKeys("VALPAK10");
                Clicks.click(shoppingBag.applyPromoCodeButton.getWrappedElement());
                isPromoApplied = Wait.until(shoppingBag.promoCodeRemove::isDisplayed, 10);
            } else {
                List<String> promotions = new ArrayList<>();
                promotions.add("X09539_2000000");
                promotions.add("X09430_2000000");
                promotions.add("X09731_0005000");
                isPromoApplied = promotions.stream().anyMatch(promotion -> {
                    shoppingBag.promoCode.clear();
                    shoppingBag.promoCode.sendKeys(promotion);
                    Clicks.click(shoppingBag.applyPromoCodeButton.getWrappedElement());
                    return Wait.until(shoppingBag.promoCodeRemove::isDisplayed, 10);
                });
            }
            Assert.assertTrue("Promo code is not applied to bag", isPromoApplied);
        } else {
            shoppingBag.promoCode.clear();
            shoppingBag.promoCode.sendKeys("asdfgh");
            Clicks.click(shoppingBag.applyPromoCodeButton.getWrappedElement());
            isPromoApplied = Wait.until(shoppingBag.promoCodeRemove::isDisplayed, 10);
            Assert.assertTrue("Error message is not displayed", !isPromoApplied && Wait.until(shoppingBag.errorMessage::isDisplayed, 10));
        }
    }
    @Then("^I should see color of item in shopping bag page$")
    public void iShouldSeecolorofitemInShoppingBagPage() {
      Assert.assertFalse(Elements.findElements("shopping_bag.item_colors").get(0).getText().isEmpty());
    }

}
