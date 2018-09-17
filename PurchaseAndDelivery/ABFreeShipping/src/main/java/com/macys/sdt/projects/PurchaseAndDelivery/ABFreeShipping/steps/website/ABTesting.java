package com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.steps.website;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.db.models.AddToBagService;
import com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.db.models.OrderService;
import com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.website.GenericUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class ABTesting extends StepUtils {

    public static String promotionalProdId = null;
    public static String promotionCode = null;

    /**
     * Step to add or update FST cookie
     **/
    @And("^I have \"([^\"]*)\" for SEGMENT cookie$")
    public void I_have_segment_value_for_segment_cookie(String segment_val) throws Throwable {
        Wait.forPageReady();
        GenericUtils.setSegmentFSTCookie(segment_val);
    }

    /**
     * Step to verify whether order subtotal on shopping bag page is more than or less than threshold value
     **/
    @And("^I verify order subtotal is (more|less) than \"([^\"]*)\"$")
    public void iVerifyOrderSubTotalIsMoreThan(String condition, String seg_value) throws Throwable {
        String threshold = null;
        JSONObject cookiePrice = GenericUtils.getFstPrice();
        threshold = cookiePrice.getString(seg_value);
        GenericUtils.verifyOrderSubTotal(condition, threshold.substring(1));
    }

    /**
     * Step to verify free shipping promotion is applied on shopping Bag and responsive checkout pages
     **/
    @Then("^I (should|should not) see free shipping promotion applied in \"([^\"]*)\"$")
    public void I_should_see_free_shipping_promotion_is_applied(String condition, String inPage) throws Throwable {
        String ele = null;
        if (inPage.contentEquals("shopping bag")) {
            ele = "shopping_bag.estimated_shipping";
        } else if (inPage.contentEquals("order summary")) {
            ele = "responsive_checkout.shipping_fee";
        } else {
            System.out.println("Invalid page");
        }
        Wait.untilElementPresent(ele);
        switch (condition) {
            case "should":
                Assert.assertTrue("Free Shipping Promotion is not applied in " + inPage,
                        GenericUtils.getShippingFee(ele).contentEquals("FREE"));
                break;
            case "should not":
                Assert.assertTrue("Free Shipping Promotion is applied in " + inPage,
                        GenericUtils.getShippingFee(ele).contains("9.95"));
                break;
            default:
                Assert.assertTrue("Shipping Cost section is not present on shopping bag page", (!Elements.elementPresent(".shipping_fee")));
                break;
        }
    }

    /**
     * Step to verify FST free shipping promotion in DB
     **/
    @Then("^I (should|should not) see \"([^\"]*)\" free shipping promotion in DB$")
    public void I_should_see_free_shipping_promotion_in_DB$(String condition, String segmentValue) throws Throwable {
        Wait.untilElementPresent("responsive_order_confirmation.order_number");
        String[] orderText = Elements.getText(Elements.element("responsive_order_confirmation.order_number")).split(": ");
        String orderNumber = orderText[1];
        OrderService order = new OrderService();
        List orderNote = new ArrayList();
        orderNote = order.getPrepareOrderNoteDetails(orderNumber);
        String price = "";
        JSONObject cookiePrice = GenericUtils.getFstPrice();
        price = cookiePrice.getString(segmentValue);
        switch (condition) {
            case "should":
                for (int i = 0; i < orderNote.size(); i++) {
                    Assert.assertTrue("FST OrderNote is not displaying as expected in Prepare Order Request", orderNote.get(i).toString().contains(segmentValue + " Test Free Shipping at " + price));
                }
                Assert.assertTrue("Adjusted Base Fee is not correct", order.getAdjustedBaseFee(orderNumber).contentEquals("0.00"));
                Assert.assertTrue("FST promotion is not applied", order.getOrderDiscountDesc(orderNumber).contains(segmentValue + " Test Free Shipping at " + price));
                break;
            case "should not":
                for (int i = 0; i < orderNote.size(); i++) {
                    Assert.assertTrue("FST OrderNote is displaying in Prepare Order Request", !orderNote.get(i).toString().contains(segmentValue + " Test Free Shipping at " + price));
                }
                Assert.assertTrue("Adjusted Base Fee is not correct", order.getAdjustedBaseFee(orderNumber).contentEquals("9.95"));
                Assert.assertTrue("FST promotion is applied", !order.getOrderDiscountDesc(orderNumber).contains(segmentValue + " Test Free Shipping at " + price));
                break;
        }
    }

    /**
     * Step to verify beauty free shipping promotion in DB
     **/
    @And("^I should see beauty free shipping promotion in DB$")
    public void I_should_see_beauty_free_shipping_promotion_in_DB() throws Throwable {
        Wait.untilElementPresent("responsive_order_confirmation.order_number");
        String[] orderText = Elements.getText(Elements.element("responsive_order_confirmation.order_number")).split(": ");
        String orderNumber = orderText[1];
        OrderService order = new OrderService();
        List orderNote = new ArrayList();
        orderNote = order.getPrepareOrderNoteDetails(orderNumber);
        for (int i = 0; i < orderNote.size(); i++) {
            Assert.assertTrue("OrderNote is not displaying as expected Prepare Order Request", orderNote.get(i).toString().contains("baseFee=9.95,adjustedBaseFee=0.0,ShippingCostDiscounts=(Desc=Free shipping with any beauty purchase.)"));
        }
        Assert.assertTrue("Adjusted Base Fee is not correct", order.getAdjustedBaseFee(orderNumber).contentEquals("0.00"));
        Assert.assertTrue("Beauty free shipping is not applied", order.getOrderDiscountDesc(orderNumber).contains("Free shipping with any beauty purchase."));
    }

    /**
     * Step to verify registry completion promotion in DB
     **/
    @And("^I should see registry completion promotion in DB$")
    public void I_should_see_registry_completion_promotion_in_DB$() throws Throwable {
        Wait.untilElementPresent("responsive_order_confirmation.order_number");
        String[] orderText = Elements.getText(Elements.element("responsive_order_confirmation.order_number")).split(": ");
        String orderNumber = orderText[1];
        OrderService order = new OrderService();
        Assert.assertTrue("Registry completion promotion is not displaying in DB", order.getOrderDiscountDesc(orderNumber).contains("Save 20% on most remaining registry gifts!"));
    }


    /**
     * Step to verify shipping method cost on responsive checkout pages when FST promotion is applied
     **/
    @Then("^I should see (premium|express|sdd) shipping cost in order summary section$")
    public void I_should_see_shipping_cost_in_order_summary_section(String methodCost) throws Throwable {
        Wait.untilElementPresent("responsive_checkout.shipping_fee");
        Utils.threadSleep(2000, "Waiting for page load");
        switch (methodCost) {
            case "premium":
                Assert.assertTrue("Premium shipping method cost is not displaying", GenericUtils.getShippingFee("responsive_checkout.shipping_fee").contentEquals("$10.00"));
                break;
            case "express":
                Assert.assertTrue("Express shipping method cost is not displaying", GenericUtils.getShippingFee("responsive_checkout.shipping_fee").contentEquals("$20.00"));
                break;
            case "sdd":
                Assert.assertTrue("SDD shipping method cost is not displaying", GenericUtils.getShippingFee("responsive_checkout.shipping_fee").contentEquals("$5.00"));
                break;
        }
    }

    /**
     * Step to select shipping method on guest responsive checkout page
     **/
    @When("^I select (premium|express) in shipping methods$")
    public void I_select_method_in_shipping_methods(String shippingMethod) throws Throwable {
        String page = onPage("responsive_checkout") ? "responsive_checkout" : "shipping_guest";
        switch (shippingMethod) {
            case "premium":
                Clicks.click(Elements.element(page + ".premium_shipping"));
                break;
            case "express":
                Clicks.click(Elements.element(page + ".express_shipping"));
                break;
        }
    }

    /**
     * Step to select shipping method on signed in responsive checkout page
     **/
    @And("^I select (premium|express|sdd_shipping) in shipping method on responsive checkout sign in page$")
    public void I_select_change_shipping_method(String shippingMethod) throws Throwable {
        Clicks.click(Elements.element("responsive_checkout_signed_in.change_shipping_method"));
        switch (shippingMethod) {
            case "premium":
                Clicks.click(Elements.element("responsive_checkout_signed_in.premium_shipping"));
                break;
            case "express":
                Clicks.click(Elements.element("responsive_checkout_signed_in.express_shipping"));
                break;
            case "sdd":
                Clicks.click(Elements.element("responsive_checkout_signed_in.sdd_shipping"));
                break;
        }
        Clicks.click(Elements.element("responsive_checkout_signed_in.save_shipping_method"));
    }

    /**
     * Step to add saved bag items to current bag
     **/
    @When("^I add saved items to current bag$")
    public void iAddSavedItemsToCurrentBag() throws Throwable {
        ShoppingBag.mergeSavedBag();
    }

    /**
     * Step to close and reopen browser
     **/
    @And("^I close and reopen the browser$")
    public void iCloseAndReopenTheBrowser() throws Throwable {
        WebDriverManager.resetDriver(true);
        WebDriverManager.getWebDriver();

    }

    /**
     * Step to verify promo code in DB
     **/
    @And("^I should see \"([^\"]*)\" promotion in DB$")
    public void I_should_see_promo_code_in_DB(String promoCode) throws Throwable {
        Wait.untilElementPresent("responsive_order_confirmation.order_number");
        String[] orderText = Elements.getText(Elements.element("responsive_order_confirmation.order_number")).split(": ");
        String orderNumber = orderText[1];
        OrderService order = new OrderService();
        Assert.assertTrue(promoCode + " is not displaying in DB",
                order.getOrderPromocode(orderNumber).contentEquals(promoCode));
    }

    /**
     * Step to set item quantity on shopping Bag page
     **/
    @And("^I set \"([^\"]*)\" in the shopping bag page$")
    public void iSetInTheShoppingBagPage(String qty) throws Throwable {
        if (MEW()) {
            Clicks.click(Elements.element("shopping_bag.item_image"));
            Clicks.click(Elements.element("shopping_bag.quantity"));
            String selectedQty = DropDowns.getSelectedValue(Elements.element("shopping_bag.quantity_dropdown"));
            if (!selectedQty.equals(qty)) {
                DropDowns.selectByText(Elements.element("shopping_bag.quantity_dropdown"), qty);
                Clicks.click(Elements.element("shopping_bag.apply_btn"));
            }else
                Clicks.click(Elements.element("shopping_bag.back_btn"));
        } else
            GenericUtils.setQty(qty);
    }

    /**
     * Step to order subtotal on shopping Bag page is more or less than the threshold value
     **/
    @And("^I verify order sub total is (more|less) than \"([^\"]*)\"$")
    public void iVerifySubOrderTotalIsMoreThan(String condition, String seg_value) throws Throwable {
        String threshold = null;
        JSONObject cookiePrice = GenericUtils.getFstPrice();
        threshold = cookiePrice.getString(seg_value);
        GenericUtils.verifyOrderSubTotal(condition, threshold.substring(1));
    }

    /**
     * Step to remove saved bag item
     **/
    @When("^I remove saved bag items$")
    public void iRemoveSavedItemsFromCurrentBag() throws Throwable {
        ShoppingBag.emptySavedShoppingBag();
    }

    /**
     * Step to remove current bag item
     **/
    @When("^I remove current bag items$")
    public void iRemoveCurrentBagItems() throws Throwable {
        ShoppingBag.emptyCurrentShoppingBag();
    }

    /**
     * Step to change saved bag item qunatity
     **/
    @When("^I change saved item quantity to \"([^\"]*)\"$")
    public void iChangeSavedItemQuantityTo(String qty) throws Throwable {
        GenericUtils.setSavedBagQty(qty);
    }

    /**
     * Step to apply promocode on shopping bag page
     **/
    @And("^I apply valid promo code \"([^\"]*)\" on shopping bag$")
    public void I_apply_valid_promo_code(String promoCode) throws Throwable {
        if (MEW()){
            Assert.assertTrue("ERROR: promo code field is not present", Wait.untilElementPresent(Elements.element("shopping_bag.promocode_area")));
            Clicks.click(Elements.element("shopping_bag.promocode_area"));
            TextBoxes.typeTextbox(Elements.element("shopping_bag.text_promocode"), promoCode);
            Clicks.click(Elements.element("shopping_bag.btn_promocode_apply"));
        }
        else {
            TextBoxes.typeTextbox("shopping_bag.promo_code", promoCode);
            Clicks.click("shopping_bag.apply_promo_code_button");
            Wait.secondsUntilElementNotPresent(Elements.element("shopping_bag.loading_mask"), 1000);
        }
    }


    /**
     * Step to navigate directly to product details page
     **/
    @When("^I navigate directly to \"([^\"]*)\" page$")
    public void I_navigate_directly_ToPage(String product) throws Throwable {
        CommonUtils.navigateToRandomProduct(product, "");
    }

    /**
     * Step to add VGC item to bag after entering email address and amount
     **/
    @And("^I add VGC item with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void I_add_VGC_item_with_amount_email(String amount, String email) throws Throwable {
        TextBoxes.typeTextbox("product_display.vgc_amount", amount);
        TextBoxes.typeTextbox("product_display.vgc_email", email);
        Clicks.click("product_display.add_to_bag_button");
    }

    /**
     * Step to verify 0 base fee in DB
     **/
    @Then("^I should see 0 base fee in DB$")
    public void I_should_see_0_base_fee_in_DB() throws Throwable {
        Wait.untilElementPresent("responsive_order_confirmation.order_number");
        String[] orderText = Elements.getText(Elements.element("responsive_order_confirmation.order_number")).split(": ");
        String orderNumber = orderText[1];
        OrderService order = new OrderService();
        Assert.assertTrue("Adjusted Base Fee is not correct", order.getBaseFee(orderNumber).contentEquals("0.00"));
    }

    /**
     * Step to add EGC item directly from service
     **/
    @And("^I add \"([^\"]*)\" of \"([^\"]*)\" to bag$")
    public void I_add_giftcard_product_with_amount(String product, String amount) throws Throwable {
        AddToBagService add_item = new AddToBagService();
        String userId = Cookies.getCookieValue("macys_online_uid");
        if (userId.equals("") || userId == null) {
            if (MEW()) {
                GlobalNav.openGlobalNav();
                GlobalNav.navigateOnGnByName("My Account");
                GlobalNav.closeGlobalNav();
            } else {
                Clicks.click("home.goto_my_account_link");
            }
            userId = Cookies.getCookieValue("macys_online_uid");
        }
        add_item.addToBag(userId, product, amount);
    }

    /**
     * Step to verify FST order note in DB
     **/
    @And("^I should not see \"([^\"]*)\" order note in DB$")
    public void I_should_not_see_order_note_in_DB(String segmentValue) throws Throwable {
        Wait.untilElementPresent("responsive_order_confirmation.order_number");
        String[] orderText = Elements.getText(Elements.element("responsive_order_confirmation.order_number")).split(": ");
        String orderNumber = orderText[1];
        OrderService order = new OrderService();
        List orderNote = new ArrayList();
        orderNote = order.getPrepareOrderNoteDetails(orderNumber);
        String price = "";
        JSONObject cookiePrice = GenericUtils.getFstPrice();
        price = cookiePrice.getString(segmentValue);
        for (int i = 0; i < orderNote.size(); i++) {
            Assert.assertTrue("FST OrderNote is displaying in Prepare Order Request", !orderNote.get(i).toString().contains(segmentValue + " Test Free Shipping at " + price));
        }
        Assert.assertTrue("FST promotion is applied", !order.getOrderDiscountDesc(orderNumber).contains(segmentValue + " Test Free Shipping at " + price));
    }

    /**
     * Step to verify Free shipping in shopping bag
     **/
    @Then("^I should see free shipping in shopping bag$")
    public void I_should_see_free_shipping_in_shopping_bag() throws Throwable {
        Assert.assertTrue("Free Shipping is not displayed in shopping bag ",
                GenericUtils.getShippingFee("shopping_bag.estimated_shipping").contentEquals("FREE"));
    }

}
