package com.macys.sdt.projects.Customer.Rebuy.actions.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cchandragiri on 7/21/2017.
 */
public class OrderMods extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(OrderMods.class);

    private JSONObject returnOrderDetails;
    //public int maxTries = 10;


    // Returns the Total Mini bag count showing in UI top right.
    public static int getBagCount() {
        int bagCount = 0;
        try {
            logger.info("Navigating to shopping bag page");
            Navigate.visit(RunConfig.url + "/bag/index.ognc");
            Wait.secondsUntilElementPresent("header.my_bag", 10);
            bagCount = Integer.parseInt(Elements.findElement("header.bag_count").getText());
            Navigate.browserBack();
            Wait.forPageReady();
            return bagCount;
        } catch (Exception e) {
            logger.warn("issue in retrieving bag count : " + e);
            return 0;
        }
    }


    /**
     * to get order container
     *
     * @param orderNumber specific order number
     */
    public WebElement getOrderContainer(String orderNumber) {
        List<String> dropDownValues = DropDowns.getAllValues("order_status.order_date_range");
        if (dropDownValues == null) {
            Assert.fail("Not able to fetch drop down values");
        }
        for (String dropDownValue : dropDownValues) {
            Wait.forPageReady();
            DropDowns.selectByText("order_status.order_date_range", dropDownValue);
            if (ordersAvailable()) {
                viewMoreOrders();
                List<WebElement> order_containers = Elements.findElements("order_status.order_row");
                for (WebElement e : order_containers) {
                    if ((macys() ? e.getAttribute("class").contains(orderNumber) : e.getAttribute("data-order-number").contains(orderNumber)))
                        return e;

                }
            }
        }
        return null;
    }

    /**
     * To View all orders on Page
     */
    private boolean ordersAvailable() {
        return !Elements.getText("order_status.order_count_in_date_range").contains("No orders found ");
    }

    /**
     * To View all orders on Page
     */

    private void viewMoreOrders() {
        do {
            try {
                Clicks.click("order_status.view_more_orders");
            } catch (NoSuchElementException ignored) {
                logger.info("View More Order Details button is not available, we have less than three orders on page");
            }
        } while (Elements.anyPresent("order_status.view_more_orders"));
    }

    public WebElement getLineitemodOHPage(WebElement orderCon, String lineItemStatus) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> lineItems = orderCon.findElements(Elements.element("order_details.order_history")).stream().filter(element -> element.isDisplayed())
                .collect(Collectors.toList());
        for (WebElement e : lineItems)
            switch (lineItemStatus) {
                case "rebuy_delivered":
                    if (e.getText().contains("Delivered")) return e;
                    break;
                case "rebuy_intransit":
                    if (e.getText().contains("Shipped on")) return e;
                    break;
                case "rebuy_bops_pickedup":
                    if (e.getText().contains("Picked up")) return e;
                    break;
                case "rebuy_processing":
                    try {
                        return Elements.findElement("order_status.processing_shipment");
                    } catch (NoSuchElementException n) {
                        Assert.fail("ERROR: TESTDATA: Processing order not found");
                    }
                case "rebuy_vr_delivered":
                    if (e.getText().contains("Delivered")) return e;
                    break;
                case "rebuy_beautybox":
                    if (lineItems.size() == 1) return e;
                    if (e.findElement(By.xpath("//img")).getAttribute("src").contains("BeautyBox")) {
                        return e;
                    }
                    break;
                case "rebuy_egc":
                    if (e.getText().contains("Gift Card")) return e;
                    break;
                case "rebuy_gwp":
                    if (e.getText().contains("$0.00")) return e;
                    break;
                case "rebuy_upc_unavailable":
                case "rebuy_upc_unavailable_product_available":
                    return e;
            }
        return null;
    }

    public WebElement getLineitemodODPage(String productName, String lineItemStatus) throws InterruptedException {
        List<WebElement> list = Elements.findElements("order_details.line_items");
        if (!list.iterator().hasNext()) Assert.fail("Line Items are not showing now");
        for (WebElement e : list) {
            if (e.getText().contains(productName)) return e;
        }
        return null;
    }

    public WebElement getShipmentFromOrderDetails(String lineItemStatus) throws InterruptedException {
        List<WebElement> shipments = Elements.findElements("order_details.line_Items_container");
        shipments.remove(0);
        String shipmentStatus = "";
        if (!shipments.iterator().hasNext()) Assert.fail("Line Items are not showing now");
        for (WebElement e : shipments) {
            shipmentStatus = e.findElement(Elements.element("order_details.shipment_status")).getText().toLowerCase();
            switch (lineItemStatus) {
                case "rebuy_delivered":
                    if (shipmentStatus.contains("delivered"))
                        return e;
                case "rebuy_intransit":
                    if (shipmentStatus.contains("in transit:"))
                        return e;
                case "rebuy_bops_pickedup":
                    if (shipmentStatus.contains("picked up"))
                        return e;
                    break;
                case "rebuy_processing":
                    if (shipmentStatus.contains("shipping on"))
                        return e;
                    break;
                case "rebuy_vr_delivered":
                    if (shipmentStatus.contains("delivered"))
                        return e;
                case "rebuy_beautybox":
                    if (e.findElement(By.xpath("//img")).getAttribute("src").contains("BeautyBox")) {
                        return e;
                    }
                case "rebuy_egc":
                    if (e.getText().contains("Gift Card")) return e;
                    break;
                case "rebuy_upc_unavailable":
                case "rebuy_upc_available_not_available":
                    return e;
                case "rebuy_gwp":
                    if (e.getText().contains("$0.00")) return e;
                    break;
            }
        }
        return null;
    }

    public static void emptyBag() {
        try {
            while (Wait.untilElementPresent("shopping_bag.remove_item")) {
                Clicks.click("shopping_bag.remove_item");
            }
        } catch (StaleElementReferenceException | org.openqa.selenium.NoSuchElementException ignored) {
            // just means there are no more items left to remove
        }
    }


}