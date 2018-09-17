package com.macys.sdt.projects.PurchaseAndDelivery.ICanWait.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.models.CampaignService;
import com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.db.models.AddToBagService;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.OrderConfirmation;
import com.macys.sdt.shared.utils.CommonUtils;
import com.macys.sdt.framework.utils.db.models.OrderServices;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ICWGenericMethods extends StepUtils {
    public static String orderNumber = null;
    public static String emailAddress = null;

    @Then("^I (should|should not) see nohurry suggestion message displayed in the shipping method section:$")
    public void I_should_see_nohurry_suggestion_message_displayed_in_the_shipping_method_section(String condition, List<String> msg) throws Throwable {
        if (condition.equals("should")) {
            String actualICWSuggestionMessage = Elements.getText("responsive_checkout_signed_in.rc_signin_icw_suggestion");
            Assert.assertTrue("Expected ICW suggestion message is not displayed in shipping method section", actualICWSuggestionMessage.equals(msg.get(0)));
        } else {
            if (Elements.elementPresent("responsive_checkout_signed_in.rc_signin_icw_suggestion")) {
                Assert.fail("ICW suggestion message is displayed");
            }
        }
    }

    @And("^I select learn more link in nohurry shipping method$")
    public void I_Select_Learn_More_Link_In_Nohurry_Shipping_Method() throws Throwable {
        String page = onPage("responsive_checkout") ? "responsive_checkout" : "responsive_checkout_signed_in";
        String type = signedIn() ? "signin_" : "";
        Clicks.clickIfPresent(page + ".change_shipping_method");
        Clicks.click(page + "." + type + "nohurry_shipping");
        Clicks.clickIfPresent(page + ".nohurry_learn_more_link");
    }

    @And("^I should see following delivery ship note for nohurry shipping method on responsive checkout page:$")
    public void I_Should_See_Following_Delivery_Ship_Note_For_Nohurry_Shipping_Method_On_Responsive_Checkout_Page(List<String> message) throws Throwable {
        String effectiveDate=null,expirationDate=null;
        Map<String, Object> campaignDetails = CampaignService.getCampaignDetails("ICWMRedeem", false);
        effectiveDate = new SimpleDateFormat("MM/dd/yy").format(campaignDetails.get("effectiveDate"));
        expirationDate = new SimpleDateFormat("MM/dd/yy").format(campaignDetails.get("expirationDate"));
        String page = onPage("responsive_checkout") ? "responsive_checkout" : "responsive_checkout_signed_in";
        String expectedNoHurryShipNote = (message.get(0)).replaceFirst("effectiveDate",effectiveDate).replaceFirst("expirationDate",expirationDate);
        String actualNoHurryShipNote = Elements.getText(Elements.element(page + ".nohurry_learn_more_content")).replace("\n", "");
        Assert.assertTrue("Expected ICW delivery ship note is not displayed in shipping method section", actualNoHurryShipNote.contains(expectedNoHurryShipNote));
    }

    @And("^I edit the Shipping details$")
    public void iEditTheShippingDetails() throws Throwable {
        Clicks.click("responsive_checkout.edit_shipping_info");
    }

    @And("^I add an \"([^\"]*)\" item to bag with \"([^\"]*)\" using add to bag service$")
    public void iAddAnItemToBagWithUsingAddToBagService(String upcId, String amount) throws Throwable {
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
        add_item.addToBag(userId, upcId, amount);
        if (safari())
            Wait.secondsUntilElementPresent("header.quick_bag", 15);
        Clicks.click("header.quick_bag");
    }

    @Then("^I verify selected shipping method details$")
    public void iVerifySelectedShippingMethodDetails(List<String> expectedShippingDetails) throws Throwable {
        for (int index = 0; index < expectedShippingDetails.size(); index++) {
            Assert.assertTrue("Expected shipping details are not displayed", (CheckoutSteps.shippingDetails.toString().contains(expectedShippingDetails.get(index))));
        }
    }

    @Then("^I verify order details and shipping details in OD page for \"([^\"]*)\" shipping method$")
    public void iVerifyOrderDetailsAndShippingDetailsInODPageForShippingMethod(String shippingMethod) throws Throwable {
        TextBoxes.typeTextbox("order_status.order_number", orderNumber.split(":")[1].toString());
        TextBoxes.typeTextbox("order_status.email", emailAddress);
        Clicks.click(Elements.element("order_status.view_order_details"));
        Wait.forPageReady();
        pausePageHangWatchDog();
        ReturnsPage returnsPage = new ReturnsPage();
        List<Map<String, Object>> orderDetails = returnsPage.getOrderDetails();
        for (Map shippingDetails : orderDetails) {
            Assert.assertTrue(shippingMethod + "shipping method is not displayed on Order details page", (shippingDetails.get("address").toString()).contains(shippingMethod));
            Assert.assertTrue("Shipping cost for " + shippingMethod + "is not Free", (shippingDetails.get("orderTotalDetails").toString()).contains("FREE"));
            Assert.assertFalse("Shipment date is empty", shippingDetails.get("headerStatus").toString().split("on")[1].isEmpty());
        }
        resumePageHangWatchDog();
    }

    @And("^I capture order number and email address from order_confirmation page$")
    public void iCaptureOrderNumberAndEmailAddressFromOrder_confirmationPage() throws Throwable {
        boolean responsive = onPage("responsive_order_confirmation");
        String page = responsive ? "responsive_order_confirmation" : "order_confirmation";
        Assert.assertTrue("Order number not displayed.", Wait.untilElementPresent(page + ".verify_page"));
        orderNumber = Elements.getText(page + ".verify_page");
        emailAddress = Elements.getText(page + ".order_confirm_email");
    }

    @And("^I should see \"([^\"]*)\" shipments in DB$")
    public void iShouldSeeShipmentsInDB(Integer expectedShipment) throws Throwable {
        OrderServices order = new OrderServices();
        List shipMethodCode = order.getShipMethodCode(OrderConfirmation.orderNumber);
        Assert.assertTrue("Order is not shipped in" + expectedShipment + "shipments", expectedShipment.equals(shipMethodCode.size()));
    }

    @Then("^I (should|should not) see icw mMoney message on shipping method section:$")
    public void iShouldSeeIcwMMoneyMessageOnShippingMethodSection(String condition, List<String> msg) throws Throwable {
        if (condition.equals("should")) {
            Assert.assertTrue("ICW mMoney message is not displayed in shipping method section as ICW Earn campagin is not active", CheckoutSteps.shippingDetails.get("shippingMethodDetails").toString().contains(msg.get(0)));
        } else {
            Assert.assertFalse("ICW mMoney message is displayed in shipping method section as ICW mEarn campagin is enabled", CheckoutSteps.shippingDetails.get("shippingMethodDetails").toString().contains(msg.get(0)));
        }
    }

    @Then("^I (should|should not) see estimated mMoney amount is displayed on RC order summary section$")
    public void iShouldSeeEstimatedMMoneyAmountIsDisplayedOnRCOrderSummarySection(String condition) throws Throwable {
        List<Map<String, String>> mbmoneyCampaignAttributeDetails = CampaignService.getCampaignAttributeDetails();
        int estEarnAmounts = (int) Double.parseDouble(mbmoneyCampaignAttributeDetails.get(1).get("campaignAttrValue"));
        if (condition.equals("should"))
            Assert.assertTrue("ICW mMoney earn information is not displaying properly on RC checkout page", (Checkout.getMMoneyInformation().get("mmoneyEstimatedEarnAmount")).equals(String.valueOf(estEarnAmounts)));
        else
            Assert.assertFalse("ICW mMoney earn information is displaying on RC checkout page", (Checkout.getMMoneyInformation().get("mmoneyEstimatedEarnAmount")).equals(String.valueOf(estEarnAmounts)));
    }

    /**
     * Step to add VGC item to bag after entering email address and amount
     **/
    @And("^I add \"([^\"]*)\" item with \"([^\"]*)\" and \"([^\"]*)\" to my bag$")
    public void I_add_VGC_item_with_amount_email(String product, String amount, String email) throws Throwable {
        CommonUtils.navigateToRandomProduct(product, "");
        Wait.forPageReady();
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("product_display.vgc_amount", amount);
        TextBoxes.typeTextbox("product_display.vgc_email", email);
        resumePageHangWatchDog();
        Clicks.click("product_display.add_to_bag_button");
        Wait.forPageReady();
        if (safari())
            Wait.secondsUntilElementPresent("header.quick_bag", 15);
        Clicks.click("header.quick_bag");
    }

    @Then("^I should not see shipping methods section$")
    public void iShouldNotSeeShippingMethodsSection() throws Throwable {
        Assert.assertFalse("Shipping method section is displayed to the User",Elements.elementPresent("responsive_order_review_section.shipping_method_info"));
    }

}



