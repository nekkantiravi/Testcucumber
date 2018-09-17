package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class OrderDetailsSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailsSteps.class);
    public JSONObject returnOrderDetails;
    String orderNum = null;

    @Then("^i verify order details for order with \"([^\"]*)\" status$")
    public void iVerifyOrderDetailsForOrderWithStatus(String orderType) throws Throwable {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = CommonUtils.getProductionOrder(order);

        List<String> shippingMethod = new ArrayList<>();
        ReturnsPage returnObj = new ReturnsPage();
//        returnObj.clickOrderDetailsButton(orderNum);
        List<Map<String, Object>> orderDetails = returnObj.getOrderDetails();
        for (Map itemDetails : orderDetails) {
            if (itemDetails.get("headerStatus").toString().isEmpty()) {
                Assert.fail("header is empty");
            }
            if (itemDetails.get("address") == null) {
                Assert.fail("address is empty");
            }
            if (((Map) itemDetails.get("address")).size() > 0) {
                if (((Map) itemDetails.get("address")).containsKey("shippingMethod")) {
                    shippingMethod.add(((Map) itemDetails.get("address")).get("shippingMethod").toString());
                }
            }
            for (Map itemList : ((List<Map>) itemDetails.get("lineItems"))) {
                if (macys()) {
                    if (itemList.get("itemDescription").toString().isEmpty()) {
                        Assert.fail("Item description is empty");
                    }
                    if (itemList.get("giftBox").toString().isEmpty()) {
                        Assert.fail("gift box is not opted");
                    }
                    if (itemList.get("itemQty").toString().isEmpty()) {
                        Assert.fail("item quantity is empty");
                    }
                    if (itemList.get("itemPrice").toString().isEmpty()) {
                        Assert.fail("item price is empty");
                    }

                } else {
                    if (itemList.get("itemDescription").toString().isEmpty()) {
                        Assert.fail("Item description is empty");
                    }
                    if (itemList.get("giftBox").toString().isEmpty() && !itemList.get("status").toString().contains("Submitted")) {
                        Assert.fail("gift box is not opted");
                    }
                    if (itemList.get("itemQty").toString().isEmpty()) {
                        Assert.fail("item quantity is empty");
                    }
                    if (itemList.get("itemPrice").toString().isEmpty()) {
                        Assert.fail("item price is empty");
                    }
                    if (itemList.get("status").toString().isEmpty()) {
                        Assert.fail("item status is empty");
                    }
                    if (itemList.get("total").toString().isEmpty()) {
                        Assert.fail("item total is empty");
                    }
                }
                if (!itemDetails.get("orderTotalDetails").toString().isEmpty()) {
                    if (((Map) itemDetails.get("orderTotalDetails")).keySet().size() < 0) {
                        Assert.fail("Order total details is empty");
                    }
                }
            }
        }    }

    @Then("^I should see zero orders$")
    public void iShouldSeeZeroOrders() throws Throwable {
        if(macys())
            Assert.assertTrue("empty orders text could not verified", Elements.getText("order_status.empty_orders_text").equals("No orders found in the selected date range."));
        else
            Assert.assertTrue("empty orders text could not verified", Elements.getText("order_status.empty_orders_text").equals("You currently don't have any orders to view."));
        logger.info("Verified empty orders text");
    }
}