package com.macys.sdt.shared.actions.website.mcom.pages.my_account;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.ReturnService;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.Returns;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ReturnsPage extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReturnsPage.class);
    public static JSONObject returnOrderDetails;
    public static Map<String, Object> selectedLineItem, orderItems;
    public List<Map> itemsSelected;

    public static Object productName = null;

    /**
     * Navigate to order selection page with given order type and user type
     *
     * @param orderType type of order
     * @param guestUser true for guest user, false for signed in
     */
    public void navigateToSelectionPage(String orderType, boolean guestUser) {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        if (!prodEnv()) {
            CommonUtils.deleteReturnRecord(orderNumber);
        }
        if (guestUser) {
            logger.info("Selected order as guest user");
        } else {
            if (returns.orderExistsByOrderNumber(orderNumber)) {
                returns.deleteOrderRecord(orderNumber);
            }
            if (!signedIn()) {
                new MyAccountSteps().iSignInToMyExistingProfile();
            }
            returns.insertOrderByOrderNumber(orderNumber, TestUsers.currentEmail);
        }
        logger.info("Goto order status page");
        Clicks.click(Elements.element("home.goto_order_status"));
        if (safari()) {
            Wait.secondsUntilElementPresent("order_status.verify_page", 10);
        }
        Wait.forPageReady();
        if (guestUser) {
            logger.info("search order by order id and email for guest user");
            lookupOrderByEmail(returnOrderDetails);
            Assert.assertFalse("ERROR - DATA: Given order number is not present in environment!!", Elements.elementPresent("order_status.error_message"));
            logger.info("Click on returns order button on order detail page");
            Clicks.click("order_details.return_items");
        } else {
            Wait.forPageReady();
            logger.info("search order by date range for registered user");
            findOrderInDateRange(orderNumber);
            Clicks.clickIfPresent("home.popup_close");
            Wait.forPageReady();
            returnOrder(orderNumber);
        }
    }

    @And("^I navigates to the order details page$")
    public void iNavigatesToTheOrderDetailsPage() throws Throwable {
        Clicks.click("home.goto_order_status");
        logger.info("Goto order status page");
        Clicks.click(Elements.element("home.goto_order_status"));
        Wait.secondsUntilElementPresent("order_status.verify_page", 10);
        Wait.forPageReady();
        logger.info("search order by order id and email for guest user");
        lookupOrderByEmail(returnOrderDetails);
    }

    /**
     * Navigate to order Details page with given order type and user type
     *
     * @param orderType type of order
     * @param guestUser true for guest user, false for signed in
     */
    public void navigateToODPage(String orderType, boolean guestUser) {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        if (!prodEnv()) {
            CommonUtils.deleteReturnRecord(orderNumber);
        }
        if (guestUser) {
            logger.info("Selected order as guest user");
        } else {
            if (returns.orderExistsByOrderNumber(orderNumber)) {
                returns.deleteOrderRecord(orderNumber);
            }
            if (!signedIn()) {
                new MyAccountSteps().iSignInToMyExistingProfile();
            }
            returns.insertOrderByOrderNumber(orderNumber, TestUsers.currentEmail);
        }
        logger.info("Goto order status page");
        Clicks.click(Elements.element("home.goto_order_status"));
        if (safari()) {
            Wait.secondsUntilElementPresent("order_status.verify_page", 10);
        }
        Wait.forPageReady();
        if (guestUser) {
            logger.info("search order by order id and email for guest user");
            lookupOrderByEmail(returnOrderDetails);
            Assert.assertFalse("ERROR - DATA: Given order number is not present in environment!!", Elements.elementPresent("order_status.error_message"));
            logger.info("Click on returns order button on order detail page");
            // Clicks.click("order_details.return_items");
        } else {
            Wait.forPageReady();
            logger.info("search order by date range for registered user");
            findOrderInDateRange(orderNumber);
            Clicks.clickIfPresent("home.popup_close");
            Wait.forPageReady();
            // Clicks.click("order_history.detail");
            // returnOrder(orderNumber);
            //selectOrder(orderNumber);
        }
    }

    /*
        Select Items in order return selection page and navigate to return submit page
    */
    public void selectItemsAndContinueToSubmitPage() {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        if (Elements.elementPresent("return_selection.scheduled_date")) {
            orderItems.put("PickUpDate", DropDowns.getSelectedValue(By.id("rmPickupDate")));
            orderItems.put("PickUpTime", DropDowns.getSelectedValue(By.id("rmPickupTime")));
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", ((List) selectedLineItem.get("reasonForReturnDescription")).get(new Random().nextInt(((List) selectedLineItem.get("reasonForReturnDescription")).size())));
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        selectItemsAndContinue(itemsSelected, returnOrderDetails);
        if (safari()) {
            Wait.secondsUntilElementPresent("return_submit.submit_return", 15);
        }
        Wait.forPageReady();
        logger.info("Verify user is on returns submit page");
        if (!onPage("return_submit")) {
            Assert.fail("User is not navigated to return submission page!!");
        }
    }

    /*
        Select Items in order return selection page and navigate to return submit page
    */
    public void selectItemsWithSizeDidNotFitReasonAndContinueToSubmitPage(String lineItemCount, String reason, JSONObject returnOrderDetails) {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", reason);
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        selectItemsAndContinue(itemsSelected, returnOrderDetails);
        if (safari()) {
            Wait.secondsUntilElementPresent("return_submit.submit_return", 15);
        }
        Wait.forPageReady();
        logger.info("Verify user is on returns submit page");
        if (!onPage("return_submit")) {
            Assert.fail("User is not navigated to return submission page!!");
        }
    }

    /*
        Select items in return selection page and continue
        @param[List<Map>, JSONObject] items to be selected and return order details (JSON data)
     */
    public void selectItemsAndContinue(List<Map> itemsSelected, JSONObject returnOrderDetails) {
        for (Map item : itemsSelected)
            selectItem(item.get("upcId").toString(), item.get("quantity").toString(), item.get("reasonForReturn").toString());
        try {
            TextBoxes.typeTextbox(Elements.element("return_selection.email"), returnOrderDetails.get("email").toString());
            TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), returnOrderDetails.get("email").toString());
        } catch (JSONException e) {
            Assert.fail("selectItemsAndContinue(): " + e);
        }
        if (Elements.elementPresent(Elements.element("return_selection.refund_method_container"))) {
            selectRefundMethod("default");
        }
        Clicks.click(Elements.element("return_selection.submit_return"));
        Wait.forPageReady();
    }

    /*
        Select refund option in return selection page
        @param[String] refund option
     */
    public void selectRefundMethod(String refundType) {
        switch (refundType) {
            case "orignal_payment":
                Clicks.click(Elements.element("return_selection.credit_refund"));
                break;
            case "giftcard_payment":
                Clicks.click(Elements.element("return_selection.gift_card_refund"));
                break;
            default:
                List<WebElement> refunds = Elements.findElements(Elements.element("return_selection.refund_method"));
                refunds.get(new Random().nextInt(refunds.size())).click();
                break;
        }
    }

    /*
        select an item in return selection page
        @param[String, String, String] upc, qty and reason for return
     */
    public void selectItem(String itemUpc, String quantity, String reasonForReturn) {
        int index = lineItemIndex(itemUpc);
        WebElement lineItemElement = Elements.findElements(Elements.element("return_selection.line_item")).get(index);
        Clicks.javascriptClick(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemSelected"));
        if (reasonForReturn != null && Elements.elementPresent(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode"))) {
            DropDowns.selectByText(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode"), reasonForReturn);
        }
        if (quantity != null && !quantity.equals("1")) {
            DropDowns.selectByText(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode"), quantity);
        }
    }

    /*
        To get line item from return selection page
        @param[String] upc
        @return[int] index of the line item
     */
    public int lineItemIndex(String itemUpc) {
        List<Map> orderItemList = getOrderLineItemList();
        for (int index = 0; index < orderItemList.size(); index++) {
            if (orderItemList.get(index).get("upcId").toString().equals(itemUpc)) {
                return index;
            }
        }
        Assert.fail("Unable to find a item that matches " + itemUpc + "!!");
        return -1;
    }

    /*
        Get all order item details in return selection page
        @return[Map] orderItemDetails in return selection page
     */
    public Map<String, Object> orderItemDetails() {
        Map<String, Object> items = new HashMap<>();
        items.put("orderHeader", getOrderHeader());
        items.put("orderLineItemList", getOrderLineItemList());
        return items;
    }

    /*
        Get all order item list in OD page
        @return[List<Map>] all orders and items present in order details page
     */
    public List<Map> getOrderLineItemList() {
        List<Map> itemDetails = new ArrayList<>();
        List<WebElement> lineItems = Elements.findElements(Elements.element("return_selection.line_item"));
        for (int index = 0; index < lineItems.size(); index++) {
            WebElement line = lineItems.get(index);
            boolean isLineItemDisabled = false;
            boolean isLineItemVisible = (line.findElements(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemSelected")).size() > 0);
            String callToAction = ((line.findElements(By.className((macys() ? "selectionReminder" : "orReturns_itemCtaMessage"))).size() > 0) ? (line.findElement(By.className((macys() ? "selectionReminder" : "orReturns_itemCtaMessage"))).getText()) : "NA");
            if (isLineItemVisible) {
                isLineItemDisabled = (line.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemSelected"))).getAttribute("type").equals("hidden");
            }
            boolean vrFlag = (isLineItemDisabled || !(isLineItemVisible));
            List<String> returnQuantity = new ArrayList<>();
            String quantitySelected;
            if (!line.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].selectedQuantity")).getAttribute("type").equals("hidden")) {
                if (!callToAction.equals("NA")) {
                    returnQuantity = DropDowns.getAllValues(By.name("returnDetails.returnShipment.lineItemList[" + index + "].selectedQuantity"));
                }
                quantitySelected = DropDowns.getSelectedValue(By.name("returnDetails.returnShipment.lineItemList[" + index + "].selectedQuantity"));
            } else {
                returnQuantity.add("1");
                quantitySelected = "1";
            }
            Map<String, Object> details = new HashMap<>();
            details.put("vrFlag", vrFlag);
            details.put("isLineItemCheckBoxHidden", !(isLineItemVisible));
            details.put("isItemSelected", isLineItemDisabled);
            details.put("vendorName", Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].vendorName")).getAttribute("value"));
            details.put("upcId", Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].upc")).getAttribute("value"));
            details.put("itemDescription", Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemDescription")).getAttribute("value"));
            details.put("sizeDescription", (Elements.elementPresent(By.name("returnDetails.returnShipment.lineItemList[" + index + "].sizeDescription")) ? Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].sizeDescription")).getAttribute("value") : ""));
            details.put("colorDescription", (Elements.elementPresent(By.name("returnDetails.returnShipment.lineItemList[" + index + "].colorDescription")) ? Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].colorDescription")).getAttribute("value") : ""));
            details.put("returnQuantity", returnQuantity);
            details.put("isReasonDisabled", (vrFlag ? "NA" : (Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode")).getAttribute((macys() ? "disabled" : "aria-disabled")))));
            details.put("quantitySelected", quantitySelected);
            details.put("reasonForReturnDescription", (vrFlag ? "NA" : (DropDowns.getAllValues(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode")))));
            details.put("isQuantityDisabled", (vrFlag ? "NA" : (Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].selectedQuantity"))).getAttribute((macys() ? "disabled" : "aria-disabled"))));
            details.put("reasonSelected", (vrFlag ? (Elements.findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode")).findElement(By.xpath("..")).getText()) : (Elements.getText(By.name("returnDetails.returnShipment.lineItemList[" + index + "].reasonForReturnCode")))));
            details.put("productImgSrc", ((Elements.findElements(Elements.element("return_selection.line_item")).size() > index) ? (Elements.findElements(Elements.element("return_selection.line_item")).get(index).findElement(By.tagName("img")).getAttribute("src")) : null));
            details.put("callToAction", callToAction);
            itemDetails.add(details);
        }
        return itemDetails;
    }

    /*
        Get the order header details from order details page
        @return[Map] order header details
     */
    public Map<String, String> getOrderHeader() {
        Map<String, String> orderHeader = new HashMap<>();
        orderHeader.put("orderNumber", Elements.getText(Elements.element("return_selection.order_number")));
        String date = Elements.getText(Elements.element("return_selection.order_date"));
        orderHeader.put("orderDate", date.split(":")[date.split(":").length - 1].trim());
        String total = "";
        if (Elements.elementPresent(Elements.element("return_selection.order_total"))) {
            total = Elements.getText(Elements.element("return_selection.order_total"));
            if (bloomingdales()) {
                total = total.split("TOTAL")[total.split("TOTAL").length - 1].trim();
            }
        }
        orderHeader.put("orderTotal", total);
        return orderHeader;
    }

    /*
        Return an order from order details page
         @param[String] order number
     */
    public void returnOrder(String orderNumber) {
        Wait.forPageReady();
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_status.order_history_container"));
        orderContainers.get(findOrderIndex(orderNumber)).findElement(By.className((macys() ? "returnItemsBtn" : "orHist_orderReturnsLinkItem"))).click();
        if (safari()) {
            Wait.secondsUntilElementPresent("return_selection.order_number", 10);
        }
        Wait.forPageReady();
        if (!onPage("return_selection")) {
            Assert.fail("User not redirected to return selection page!!");
        }
    }

    public static List<Product> tux_productDetailsOHPage(String orderNumber) {
        List<Product> productDetails = new ArrayList<>();
        Wait.forPageReady();
        ReturnsPage returnPage = new ReturnsPage();
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_status.order_history_container"));
        List<WebElement> item_details_containers = orderContainers.get(returnPage.findOrderIndex(orderNumber)).findElements(Elements.element("order_status.item_details_container"));
        Product p = null;
        for (int i = 0; i < orderContainers.size(); i++) {
            for (int index = 0; index < item_details_containers.size(); index++) {
                if (Elements.elementPresent("order_status.tux_product_label")) {
                    p = new Product(index);
                    p.tuxedo_description = item_details_containers.get(index).findElement(By.xpath("//*[@id='orderHistory']/div/div/div[2]/ul/li/ul/li[2]/p[2]/a")).getText();
                    p.reservation_id = Long.parseLong(item_details_containers.get(index).findElement(By.id("mkpReservationId")).getText());
                    p.quantity = Integer.parseInt(item_details_containers.get(index).findElement(By.xpath("//*[@id='orderHistory']/div/div/div[2]/ul/li/ul/li[3]/span")).getText());
                    p.individualPrice = Double.parseDouble(item_details_containers.get(index).findElement(By.className("price")).getText());
                    //p.tux_shipping_info_msg = item_details_containers.get(index).findElement(By.id("tuxShippingInfo")).getText();

                }

                productDetails.add(p);
            }
        }
        return productDetails;
    }


    public Map getOrder(String orderNumber) {
        List<Map> orderList = getOrderList();
        Map orderDetails = new HashMap<>();
        for (Map order : orderList) {
            if (order.get("orderNumber").equals(orderNumber)) {
                orderDetails = order;
            }
        }
        return orderDetails;
    }

    /*
        Get the order index from order details page
        @param[String] order number
        @return[int] index of order present in order details page
     */
    public int findOrderIndex(String orderNumber) {
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_status.order_history_container"));
        for (int index = 0; index < orderContainers.size(); index++) {
            String pageOrderNumber = null;
            if (macys()) {
                pageOrderNumber = orderContainers.get(index).findElements(By.xpath("//span[@id='orderNumber_" + orderNumber + "']")).get(0).getText();
            } else {
                pageOrderNumber = Elements.findElements(Elements.element("order_status.order_history_order_text")).get(index).findElement(By.tagName("span")).getText().trim();
            }
            if (pageOrderNumber.contains(orderNumber)) {
                return index;
            }
        }
        Assert.fail("ERROR - DATA: Did not find order number " + orderNumber + " in page");
        return -1;
    }

    /*
        To find order using date range in order details page
        @param[String] order number
     */
    public void findOrderInDateRange(String orderNumber) {
        Wait.forPageReady();

        // get the order list of selected date and verify for the order number
        for (Map order : getOrderList()) {
            if (order.get("orderNumber").toString().equals(orderNumber)) {
                return; // return if the order is present in selected date
            }
        }

        // get the date range
        List<String> dropDownValues = (macys() ? DropDowns.getAllValues("order_status.order_date_range") : DropDowns.getAllCustomValues("order_status.order_date_range_list", "order_status.order_date_range_options"));
        if (dropDownValues == null) {
            Wait.forPageReady();
            Assert.fail("Not able to fetch drop down values");
        }

        // go through the date range one by one and look for order in each. Stop looking for order once find and return.
        for (int index = 1; index < dropDownValues.size(); index++) {
            if (macys()) {
                DropDowns.selectByText("order_status.order_date_range", dropDownValues.get(index));
            } else {
                DropDowns.selectCustomText("order_status.order_date_range_list", "order_status.order_date_range_options", dropDownValues.get(index));
            }
            if (safari() || MEW()) {
                Utils.threadSleep(2000, null);
            }
            Wait.forPageReady();
            Utils.threadSleep(2000, null);

            // get the order list of selected date and verify for the order number
            for (Map order : getOrderList()) {
                if (order.get("orderNumber").toString().equals(orderNumber)) {
                    return; // return if the order is present in selected date
                }
            }
        }
        Wait.forPageReady();
        Assert.fail("ERROR - DATA: Did not find order number " + orderNumber + " in the available date ranges.");

    }

    /**
     * get all order details present Order History / Order Detail page
     *
     * @return order details list
     */
    public List<Map> getOrderList() {
        List<Map> orderDetails = new ArrayList<>();

        int pageCount = (macys() && Elements.elementPresent("order_status.page_navigation")) ? Elements.findElement("order_status.page_navigation").findElements(By.className("pgTopAlign")).size() : 1;
        for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
            List<WebElement> orderContainers = Elements.findElements("order_status.order_history_container");

            for (int index = 0; index < orderContainers.size(); index++) {
                Map<String, Object> order = new HashMap<>();
                if (macys()) {
                    //get the order number for a specific order container
                    String orderNo = orderContainers.get(index).findElement(By.cssSelector("label.devider span")).getText();

                    if (orderContainers.get(index).findElements(By.xpath("//span[@id='orderNumber_" + orderNo + "']")).size() == 0) {
                        continue;
                    }

                    order.put("orderNumber", orderContainers.get(index).findElements(By.xpath("//span[@id='orderNumber_" + orderNo + "']")).get(0).getText());
                    logger.info("Elements.element(\"order_status.order_history_order_date\")" + Elements.element("order_status.order_history_order_date") + " " +
                            Elements.element("order_status.order_history_order_date").toString());
                    order.put("orderDate", Elements.findElements(Elements.element("order_status.order_history_order_date")).get(index).getText());
                    order.put("orderTotal", orderContainers.get(index).findElements(By.xpath("//span[@id='orderTotalSpan_" + orderNo + "']")).get(0).getText());
                } else {
                    order.put("orderNumber", Elements.findElements("order_status.order_history_order_text").get(index).findElement(By.tagName("span")).getText().trim());
                    order.put("orderDate", Elements.findElements("order_status.order_history_order_date").get(index).findElement(By.className("orStatus_boldText")).getText());
                    order.put("orderTotal", Elements.findElements("order_status.order_history_order_total").get(index).findElement(By.className("orStatus_boldText")).getText());
                }

                // get shipment details and put in order detail
                order.put("orderDetails", getShipmentsDetails(Elements.findElements("order_status.order_history_container").get(index)));

                // add the specific order to the order list
                orderDetails.add(order);
            }

            if (macys()) {
                if (Elements.elementPresent("order_status.page_navigation") && Elements.findElement("order_status.page_navigation").findElement(By.className("pgNext")).isDisplayed()) {
                    Elements.findElement("order_status.page_navigation").findElement(By.className("pgNext")).click();
                }
                Wait.forPageReady();
                Wait.untilElementNotPresent("order_status.order_loader_gif");
            }
        }
        return orderDetails;
    }

    /**
     * get the shipment details for order details page
     *
     * @param containerElement order container element
     * @return all shipment details
     */
    public List<Map> getShipmentsDetails(WebElement containerElement) {
        List<Map> shipmentDetails = new ArrayList<>();
        if (macys()) {
            List<WebElement> shipmentElements = containerElement.findElements(By.className("orderStatus"));
            for (WebElement shipment : shipmentElements) {
                HashMap<String, Object> details = new HashMap<>();
                String headerStatus = shipment.findElement(By.tagName("h2")).getText();
                if (shipment.findElements(By.cssSelector("h2 span")).size() > 0) {
                    String spanText = shipment.findElement(By.tagName("h2")).findElement(By.tagName("span")).getText();
                    if (spanText != null) {
                        headerStatus = statusCleansing(headerStatus, spanText);
                    }
                }
                details.put(headerStatus, getLineItems(shipment));
                shipmentDetails.add((HashMap) details.clone());
            }

        } else {
            List<WebElement> shipmentsLevelElements = new ArrayList<>();
            String[] classNames = {"orStatus_trackingStatus", "orStatus_orderSectionHeader", "orHist_itemInfoContainer"};
            for (WebElement shipmentElement : containerElement.findElements(By.tagName("div")))
                if (Arrays.asList(classNames).contains(shipmentElement.getAttribute("class"))) {
                    shipmentsLevelElements.add(shipmentElement);
                }
            int loopCounter = 0;
            while (loopCounter < shipmentsLevelElements.size()) {
                String headerStatus = shipmentsLevelElements.get(loopCounter).getText().trim();
                if (shipmentsLevelElements.get(loopCounter).findElements(By.tagName("span")).size() > 0) {
                    String spanText = shipmentsLevelElements.get(loopCounter).findElement(By.tagName("span")).getText();
                    if (spanText != null) {
                        statusCleansing(headerStatus, spanText);
                    }
                }
                WebElement leElement = shipmentsLevelElements.get(loopCounter);
                List<Map> itemList = new ArrayList<>();
                while (((loopCounter + 1) < shipmentsLevelElements.size()) && shipmentsLevelElements.get(loopCounter + 1).getAttribute("class").equals("orHist_itemInfoContainer")) {
                    Map<String, Object> details = new HashMap<>();
                    details.put("itemDescription", leElement.findElements(By.tagName("div")).get(0).getText());
                    details.put("itemAction", leElement.findElements(By.tagName("div")).get(1).getText());
                    details.put("itemPrice", leElement.findElements(By.tagName("div")).get(2).getText());
                    details.put("itemQty", leElement.findElements(By.tagName("div")).get(3).getText());
                    itemList.add(details);
                    loopCounter += 1;
                }
                Map<String, Object> status = new HashMap<>();
                status.put(headerStatus, itemList);
                shipmentDetails.add(status);
                loopCounter += 1;
            }
        }
        return shipmentDetails;
    }

    /**
     * get all line items present in order history page
     *
     * @param shipmentContainer shipment container
     * @return all line item details
     */
    public List<HashMap> getLineItems(WebElement shipmentContainer) {
        List<HashMap> lineItems = new ArrayList<>();
        List<WebElement> lineElements = shipmentContainer.findElements(By.className("orderStatusDetails"));
        HashMap<String, Object> details = new HashMap<>();
        for (WebElement container : lineElements) {
            for (WebElement liElement : container.findElements(By.tagName("li"))) {
                if (liElement.findElements(By.className("itemStatus")).size() > 0) {
                    details.put("itemAction", liElement.findElement(By.className("itemStatus")).getText());
                }
                if (liElement.findElements(By.className("price")).size() > 0) {
                    details.put("itemPrice", liElement.findElement(By.className("price")).getText());
                }
                if (liElement.findElements(By.tagName("span")).size() > 0) {
                    details.put("itemQty", liElement.findElement(By.tagName("span")).getText());
                }
                if (macys() && liElement.getAttribute("class").contains("description") && liElement.findElements(By.tagName("p")).size() > 0) {
                    details.put("itemDescription", liElement.findElement(By.tagName("p")).getText());
                }
                if (liElement.findElements(By.tagName("p")).size() > 0) {
                    for (WebElement pElement : liElement.findElements(By.tagName("p"))) {
                        if (pElement.getText().contains("color")) {
                            details.put("itemColor", pElement.getText());
                        }
                        if (pElement.getText().contains("size")) {
                            details.put("itemSize", pElement.getText());
                        }
                        if (bloomingdales() && pElement.findElements(By.tagName("a")).size() > 0) {
                            details.put("itemDescription", pElement.getText());
                        }
                    }
                }
            }
            lineItems.add((HashMap) details.clone());
        }
        return lineItems;
    }

    public String statusCleansing(String headerStatus, String spanText) {
        String textToInclude = (macys() ? "for store pick-up" : "IN-STORE PICK UP");
        if (!headerStatus.contains(textToInclude)) {
            if (spanText != null) {
                headerStatus = headerStatus.replace(spanText, "");
            }
        }
        return headerStatus.replace("\n", " ").trim();
    }

    /**
     * lookup order by using email and order number
     *
     * @param returnOrderDetails order details
     */
    public void lookupOrderByEmail(JSONObject returnOrderDetails) {
        Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
        logger.info("Entering order number for order lookup " + returnOrderDetails.get("order_number").toString());
        TextBoxes.typeTextbox(Elements.element("order_status.order_number"), returnOrderDetails.get("order_number").toString());
        logger.info("Entering email for order lookup " + returnOrderDetails.get("email").toString());
        TextBoxes.typeTextbox(Elements.element("order_status.email"), returnOrderDetails.get("email").toString());
        Clicks.click(Elements.element("order_status.view_order_details"));
    }

    /**
     * lookup order by using phone and order number
     *
     * @param returnOrderDetails order details
     */
    public void lookupOrderByPhone(JSONObject returnOrderDetails) {
        Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
        TextBoxes.typeTextbox(Elements.element("order_status.order_number"), returnOrderDetails.get("order_number").toString());
        TextBoxes.typeTextbox(Elements.element("order_status.phone_number"), returnOrderDetails.get("phone_number").toString());
        Clicks.click(Elements.element("order_status.view_order_details"));
    }

    /**
     * lookup order by using zip code and order number
     *
     * @param returnOrderDetails order details
     */
    public void lookupOrderByZip(JSONObject returnOrderDetails) {
        Wait.untilElementPresent(Elements.element("order_status.view_order_details"));
        TextBoxes.typeTextbox(Elements.element("order_status.order_number"), returnOrderDetails.get("order_number").toString());
        Elements.findElement("order_status.gift_order").isDisplayed();
        Elements.findElement("order_status.gift_yes").click();
        TextBoxes.typeTextbox(Elements.element("order_status.zip_code"), returnOrderDetails.get("shipping_zip_code").toString());
        Clicks.click(Elements.element("order_status.view_order_details"));
    }


    public List<Map<String, Object>> getOrderDetails() {
        List<Map<String, Object>> orderDetails = new ArrayList<>();
        String headerStatus;
        logger.info("Get order details for all status which order went through");
        for (WebElement container : Elements.findElements(Elements.element("order_details.order_details_container"))) {
            Map<String, Object> shipment = new HashMap<>();
            if (macys()) {
                headerStatus = container.findElements(By.tagName("div")).get(0).getText();
                boolean isShippingAddressExists = container.findElement(By.className("shippingAddress")).findElements(By.tagName("h3")).size() > 0;
                shipment.put("isShippingAddressAvailable", isShippingAddressExists);
                shipment.put("address", (isShippingAddressExists ? getShippingAddress(container.findElement(By.className("shippingAddress"))) : "Not available"));
                shipment.put("trackMyReturn", (container.findElements(By.className("trackReturnBtn")).size() > 0));
            } else {
                shipment.put("address", getShippingAddress(container.findElement(By.className("orDetails_shipContainer"))));
                shipment.put("trackMyReturn", (container.findElements(By.className("orStatus_trackingEnabled")).size() > 0));
                if (macys()) {
                    headerStatus = container.findElement(By.className("orStatus_trackingStatus")).getText();
                } else {
                    headerStatus = (container.findElements(By.className("orStatus_trackingStatus")).size() > 0) ? (container.findElement(By.className("orStatus_trackingStatus")).getText()) : (container.findElement(By.className("orStatus_subHeader")).getText());
                }
            }
            shipment.put("headerStatus", headerStatus);
            shipment.put("lineItems", getLineItemsDetails(container));
            shipment.put("orderTotalDetails", ((container.findElements(By.className((macys() ? "grandTotal" : "orDetails_totalArrowContainer"))).size() > 0) ? getOrderSubTotalDetails(container) : ""));
            orderDetails.add(shipment);
        }
        return orderDetails;
    }

    public Map getOrderSubTotalDetails(WebElement orderTotalContainer) {
        Map<String, Object> orderTotalInfo = new HashMap<>();
        if (macys()) {
            orderTotalContainer.findElement(By.className("grandTotal")).click();
            WebElement totalElement = orderTotalContainer.findElement(By.className("total"));
            List<String> orderTotalInfoArr = new ArrayList<>();
            List<String> orderTotalInfoSpanArr = new ArrayList<>();
            for (WebElement element : totalElement.findElements(By.tagName("label"))) {
                WebElement parent = element.findElement(By.xpath("./.."));
                if (parent.getText().contains("Order Subtotal")) {
                    orderTotalInfoArr.add("Order Subtotal");
                } else {
                    orderTotalInfoArr.add(element.getText());
                }
                orderTotalInfoSpanArr.add(element.findElement(By.tagName("span")).getText());
            }
            for (int index = 0; index < orderTotalInfoSpanArr.size(); index++)
                orderTotalInfo.put(orderTotalInfoArr.get(index).replaceAll("\n", "").replaceAll((orderTotalInfoSpanArr.get(index).contains("$") ? String.format("\\%s", orderTotalInfoSpanArr.get(index)) : orderTotalInfoSpanArr.get(index)), ""), orderTotalInfoSpanArr.get(index));
        } else {
            orderTotalContainer.findElement(By.className("orDetails_totalArrowContainer")).click();
            orderTotalContainer.findElement(By.className("orDetails_shipmentSubTotal")).isDisplayed();
            //Wait.untilElementPresent(By.className("orDetails_shipmentSubTotal"));
            List<WebElement> totalElements = orderTotalContainer.findElement(By.className("orDetails_shipmentSubTotal")).findElements(By.tagName("div"));
            for (int index = 0; index < totalElements.size() - 2; index++)
                orderTotalInfo.put(totalElements.get(index).findElements(By.tagName("span")).get(0).getText(), totalElements.get(index).findElements(By.tagName("span")).get((totalElements.get(index).findElements(By.tagName("span")).size() - 1)).getText());
            if (Elements.elementPresent(Elements.element("order_details.additional_text"))) {
                orderTotalInfo.put(Elements.getText(Elements.element("order_details.additional_text")), Elements.getText(Elements.element("order_details.additional_amount_text")));
            }
            orderTotalContainer.findElement(By.className("orDetails_totalArrowContainer")).click();
            Navigate.scrollPage(0, 200);
        }
        return orderTotalInfo;
    }

    public void selectOrder(String orderNumber) {
        Wait.forPageReady();
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_status.order_history_container"));
        for (WebElement e : orderContainers) {
            if (macys()) {
                e.findElement(By.id("orderDetailBtn_" + orderNumber)).click();
            } else {
                Elements.findElements(Elements.element("order_status.order_details_buttons")).get(findOrderIndex(orderNumber)).click();
            }
        }
        Wait.forPageReady();
    }

    public List<Map> getLineItemsDetails(WebElement shipmentContainer) {
        List<Map> itemList = new ArrayList<>();
        if (macys()) {
            WebElement shipmentDetailsInfoContainer = shipmentContainer.findElement(By.id("shippingDetailsInfoDiv"));
            List<WebElement> lineItemsElements = shipmentDetailsInfoContainer.findElements(By.xpath("./ul/li"));
            boolean isVrItem = shipmentDetailsInfoContainer.findElements(By.className("returnReminderInfo")).size() > 0;
            int index = 0;
            for (WebElement container : lineItemsElements) {
                String lineItemText = null;
                if (container.getAttribute("class").equals("returnReminderInfo")) {
                    continue;
                }
                if (shipmentDetailsInfoContainer.findElements(By.id("returnsItemReceived")).size() > 0) {
                    lineItemText = shipmentDetailsInfoContainer.findElement(By.id("returnsItemReceived")).getText();
                } else if (shipmentDetailsInfoContainer.findElements(By.id("returnsItemMissing")).size() > 0) {
                    lineItemText = shipmentDetailsInfoContainer.findElement(By.id("returnsItemMissing")).getText();
                }
                List<WebElement> details = container.findElements(By.tagName("li"));
                Map<String, Object> items = new HashMap<>();
                String name = null, gift = null, qty = null, price = null;
                if (details.size() >= 0) {
                    name = details.get(0).getText();
                }
                if (details.size() >= 1) {
                    gift = details.get(1).getText();
                }
                if (details.size() >= 2) {
                    qty = details.get(2).getText();
                }
                if (details.size() >= 3) {
                    price = details.get(3).getText();
                }
                items.put("itemDescription", name.split("\n")[0]);
                items.put("giftBox", gift.split("\n")[gift.split("\n").length - 1]);
                items.put("itemQty", qty.split("\n")[qty.split("\n").length - 1]);
                items.put("itemPrice", (price == null ? "" : price.split("\n")[price.split("\n").length - 1]));
                items.put("lineItemText", lineItemText);
                items.put("writeAReview", (name.contains("write a review")));
                if (container.findElement(By.xpath("..")).findElements(By.className("returnReminderInfo")).size() > 0) {
                    String[] remainderText = container.findElement(By.xpath("..")).findElements(By.className("returnReminderInfo")).get(index).getText().split("\n");
                    items.put("callToActionLine1", ((remainderText.length > 1 && isVrItem) ? remainderText[2] : "NA"));
                    items.put("callToActionLine2", ((remainderText.length > 2 && isVrItem) ? remainderText[3] : "NA"));
                    items.put("reasonCode", ((remainderText.length > 0 && isVrItem) ? remainderText[0] : "NA"));
                }
                index += 1;
                itemList.add(items);
            }
        } else {
            List<WebElement> lineItemElements = shipmentContainer.findElements(By.className("orDetails_itemContainer"));
            for (WebElement lineItem : lineItemElements) {
                int index = 0;
                boolean vrItem = lineItem.findElements(By.xpath(".//div[contains(@class,'orStatus_infoIcon')]")).size() > 0;
                WebElement nameElement = lineItem.findElement(By.className("orDetails_itemDescContent"));
                WebElement statusElement = lineItem.findElement(By.className("orDetails_statusContent"));
                WebElement giftElement = lineItem.findElement(By.className("orDetails_giftContent"));
                WebElement qtyElement = lineItem.findElement(By.className("orDetails_qtyContent"));
                WebElement priceElement = lineItem.findElement(By.className("orDetails_priceContent"));
                WebElement totalElement = lineItem.findElement(By.className("orDetails_totalContent"));
                String name = null, status = null, gift = null, qty = null, price = null, total = null;
                if (nameElement != null) {
                    name = nameElement.getText();
                }
                if (statusElement != null) {
                    status = statusElement.getText();
                }
                if (giftElement != null) {
                    gift = giftElement.getText();
                }
                if (qtyElement != null) {
                    qty = qtyElement.getText();
                }
                if (priceElement != null) {
                    price = priceElement.getText();
                }
                if (totalElement != null) {
                    total = totalElement.getText();
                }
                Map<String, Object> data = new HashMap<>();
                data.put("itemDescription", name);
                data.put("status", status);
                data.put("giftBox", gift);
                data.put("itemQty", qty);
                data.put("itemPrice", (price != null ? price : ""));
                data.put("total", (total != null ? total : ""));
                data.put("writeAReview", (vrItem ? "NA" : (name.contains("WRITE REVIEW"))));
                data.put("callToAction", (vrItem ? lineItem.findElement(By.xpath(".//div[contains(@class,'orStatus_infoIcon')]")).getText() : "NA"));
                index += 1;
                itemList.add(data);
            }
        }
        return itemList;
    }

    public Map<String, String> getShippingAddress(WebElement container) {
        Map<String, String> shippingAddress = new HashMap<>();
        if (macys()) {
            if (container.findElement(By.tagName("h3")).getText().equals("Shipping Address")) {
                if (container.findElements(By.tagName("h3")).size() >= 1) {
                    List<WebElement> address = container.findElement(By.tagName("ul")).findElements(By.tagName("li"));
                    String warehouseName = null, warehouseAddress = null, warehouseCity = null, phoneNumber = null, firstNameLastName = null,
                            city = null, phone = null, email = null;
                    if (address.size() >= 1) {
                        warehouseName = firstNameLastName = address.get(0).getText();
                    }
                    if (address.size() >= 2) {
                        warehouseAddress = address.get(1).getText();
                    }
                    if (address.size() >= 3) {
                        warehouseCity = city = address.get(2).getText();
                    }
                    if (address.size() >= 4) {
                        phone = phoneNumber = address.get(3).getText();
                    }
                    if (address.size() >= 5) {
                        email = address.get(4).getText();
                    }
                    shippingAddress.put("addressType", "Return Address");
                    shippingAddress.put("warehouseName", warehouseName);
                    shippingAddress.put("warehouseAddress", warehouseAddress);
                    shippingAddress.put("warehouseCity", warehouseCity.split(",")[0].trim());
                    shippingAddress.put("warehouseState", warehouseCity.split(",")[warehouseCity.split(",").length - 1].trim().split(" ")[0].trim());
                    shippingAddress.put("warehouseZipCode", warehouseCity.split(",")[warehouseCity.split(",").length - 1].trim().split(" ")[warehouseCity.split(",")[warehouseCity.split(",").length - 1].trim().split(" ").length - 1].trim());
                    shippingAddress.put("phoneNumber", phoneNumber);
                    boolean isPrivacy = Elements.elementPresent(Elements.element("order_details.address_privacy_message"));
                    shippingAddress.put("addressType", "Shipping");
                    shippingAddress.put("firstNameLastName", firstNameLastName);
                    shippingAddress.put("shippingMethod", container.getText().split("\n")[container.getText().split("\n").length - 1].trim());
                    shippingAddress.put("addressLine1", (isPrivacy ? null : (address.get(1).getText())));
                    shippingAddress.put("city", (isPrivacy ? null : (city.split(",")[0])));
                    shippingAddress.put("state", (isPrivacy ? null : (city.split(",")[city.split(",").length - 1].trim().split(" ")[0].trim())));
                    shippingAddress.put("zipCode", (isPrivacy ? null : (city.split(" ")[city.split(" ").length - 1].trim().split(" ")[city.split(" ")[city.split(" ").length - 1].trim().split(" ").length - 1].trim())));
                    shippingAddress.put("phone", (isPrivacy ? null : phone));
                    shippingAddress.put("email", (isPrivacy ? null : email));
                    shippingAddress.put("addressPrivacyMessage", (isPrivacy ? (Elements.getText(Elements.element("order_details.address_privacy_message"))) : null));
                }
            } else {
                List<WebElement> address = container.findElements(By.tagName("li"));
                shippingAddress.put("addressType", "BOPS");
                shippingAddress.put("bopsStoreLocation", container.findElement(By.id("storeNumber")).getText());
                shippingAddress.put("bopsStoreName", address.get(0).getText());
                shippingAddress.put("bopsAddressLine1", address.get(1).getText());
                shippingAddress.put("bopsAddressLine2", address.get(2).getText());
                shippingAddress.put("bopsCity", (address.get(3).getText().split(",")[0]));
                shippingAddress.put("bopsState", (address.get(3).getText().split(",")[address.get(3).getText().split(",").length - 1].trim().split(" ")[0]));
                shippingAddress.put("bopsZipCode", (address.get(3).getText().split(",")[address.get(3).getText().split(",").length - 1].trim().split(" ")[address.get(3).getText().split(",")[address.get(3).getText().split(",").length - 1].trim().split(" ").length - 1]));
                shippingAddress.put("bopsStoreNumber", address.get(4).getText());
                shippingAddress.put("bopsFirstLastName", address.get(5).getText());
                shippingAddress.put("bopsEmail", address.get(6).getText());
                shippingAddress.put("bopsphone", address.get(7).getText());
            }
        } else {
            List<WebElement> divElements = container.findElements(By.tagName("div"));
            if (container.findElements(By.className("orDetails_shipMethod")).size() > 0) {
                if (container.findElement(By.className("orDetails_shipMethod")).getText().equals("SHIPPING METHOD")) {
                    String firstLastName = null, addressLine1 = null, cityState = null, zipCode = null, shippingType = null, shippingMethod = null;
                    boolean privacy = Elements.elementPresent(Elements.element("order_details.address_privacy_message"));
                    if (divElements.size() > 0) {
                        firstLastName = divElements.get(0).getText();
                    }
                    if (divElements.size() > 1) {
                        addressLine1 = divElements.get(1).getText();
                    }
                    if (divElements.size() > 2) {
                        cityState = divElements.get(2).getText();
                    }
                    if (divElements.size() > 3) {
                        zipCode = divElements.get(3).getText();
                    }
                    if (divElements.size() > 4) {
                        shippingType = divElements.get(4).getText();
                    }
                    if (divElements.size() > 5) {
                        shippingMethod = divElements.get(5).getText();
                    }
                    shippingAddress.put("addressType", "Shipping");
                    shippingAddress.put("firstLastName", firstLastName);
                    shippingAddress.put("addressLine1", (privacy ? null : addressLine1.trim()));
                    shippingAddress.put("city", (privacy ? null : cityState.replace(cityState.split(" ")[cityState.split(" ").length - 1], "")));
                    shippingAddress.put("state", (privacy ? null : cityState.split(" ")[cityState.split(" ").length - 1]));
                    shippingAddress.put("zipCode", (privacy ? null : zipCode));
                    shippingAddress.put("shippingType", (privacy ? null : shippingType));
                    shippingAddress.put("shippingMethod", (privacy ? null : shippingMethod));
                    shippingAddress.put("addressPrivacyMessage", (privacy ? Elements.getText(Elements.element("order_details.address_privacy_message")) : null));
                } else {
                    String store = null, storeName = null, addressLine1 = null, cityState = null, zipCode = null, storeNumber = null, shippingMethod = null,
                            firstLastName = null, email = null, phone = null;
                    if (divElements.size() > 0) {
                        store = divElements.get(0).getText();
                    }
                    if (divElements.size() > 1) {
                        storeName = divElements.get(1).getText();
                    }
                    if (divElements.size() > 2) {
                        addressLine1 = divElements.get(2).getText();
                    }
                    if (divElements.size() > 3) {
                        cityState = divElements.get(3).getText();
                    }
                    if (divElements.size() > 4) {
                        zipCode = divElements.get(4).getText();
                    }
                    if (divElements.size() > 5) {
                        storeNumber = divElements.get(5).getText();
                    }
                    if (divElements.size() > 6) {
                        shippingMethod = divElements.get(6).getText();
                    }
                    if (divElements.size() > 7) {
                        firstLastName = divElements.get(7).getText();
                    }
                    if (divElements.size() > 8) {
                        email = divElements.get(8).getText();
                    }
                    if (divElements.size() > 9) {
                        phone = divElements.get(9).getText();
                    }
                    shippingAddress.put("addressType", "BOPS");
                    shippingAddress.put("bopsStoreLocation", store);
                    shippingAddress.put("bopsStoreName", storeName);
                    shippingAddress.put("bopsAddressLine1", addressLine1.trim());
                    shippingAddress.put("bopsCity", cityState.split(",")[0].trim());
                    shippingAddress.put("bopsState", cityState.split(",")[cityState.split(",").length - 1].trim());
                    shippingAddress.put("bopsZipCode", zipCode);
                    shippingAddress.put("bopsStoreNumber", storeNumber);
                    shippingAddress.put("bopsShippingMethod", shippingMethod);
                    shippingAddress.put("bopsFirstLastName", firstLastName);
                    shippingAddress.put("bopsEmail", email);
                    shippingAddress.put("bopsPhone", phone);
                }
            } else {
                String warehouse = null, warehouseAddress = null, cityState = null, zipCode = null;
                if (divElements.size() > 0) {
                    warehouse = divElements.get(0).getText();
                }
                if (divElements.size() > 1) {
                    warehouseAddress = divElements.get(1).getText();
                }
                if (divElements.size() > 2) {
                    cityState = divElements.get(2).getText();
                }
                if (divElements.size() > 3) {
                    zipCode = divElements.get(3).getText();
                }
                shippingAddress.put("addressType", "Return Address");
                shippingAddress.put("warehouseName", warehouse.trim());
                shippingAddress.put("warehouseAddress", warehouseAddress.trim());
                shippingAddress.put("warehouseCity", cityState.split(",")[0].trim());
                shippingAddress.put("warehouseState", cityState.split(",")[cityState.split(",").length - 1].trim());
                shippingAddress.put("warehouseZipCode", zipCode);
            }
        }
        return shippingAddress;
    }

    /**
     * click order details button
     *
     * @param orderNum order number
     */
    public void clickOrderDetailsButton(String orderNum) {
        List<WebElement> orderNumberButtons = Elements.findElements(Elements.element("order_status.order_number_detail"));
        Boolean orderExists = false;
        if (orderNumberButtons.size() > 0) {
            for (WebElement orderDetails : orderNumberButtons) {
                if (macys()) {
                    if (orderDetails.getText().contains(orderNum)) {
                        Clicks.click(By.id("orderDetailBtn_" + orderNum));
                        orderExists = true;
                        break;
                    }
                } else {
                    if (orderDetails.getAttribute("href").contains(orderNum)) {
                        Clicks.click(orderDetails);
                        orderExists = true;
                        break;
                    }
                }
            }
        }

        if (!orderExists) {
            Assert.fail(String.format("Order number %s not found", orderNum));
        }
    }

    @Given("^I attempt to continue with different emails in selection page$")
    public void iAttemptToContinueWithDifferentEmailsInSelectionPage() throws Throwable {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", ((List) selectedLineItem.get("reasonForReturnDescription")).get(new Random().nextInt(((List) selectedLineItem.get("reasonForReturnDescription")).size())));
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        for (Map item1 : itemsSelected)
            selectItem(item1.get("upcId").toString(), item1.get("quantity").toString(), item1.get("reasonForReturn").toString());
        try {
            TextBoxes.typeTextbox(Elements.element("return_selection.email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), "differentemail@email.com");
        } catch (JSONException e) {
            Assert.fail("iAttemptToContinueWithDifferentEmailInSelectionPage(): " + e);
        }
        if (Elements.elementPresent(Elements.element("return_selection.refund_method_container"))) {
            selectRefundMethod("default");
        }
        Clicks.click(Elements.element("return_selection.submit_return"));
        Wait.forPageReady();
        logger.info("Items selected with two different email's in contact info section");
    }

    @Given("^I attempt to continue without selecting reason for return in selection page$")
    public void iAttemptToContinueWithoutSelectingReasonForReturnInSelectionPage() throws Throwable {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", ((List) selectedLineItem.get("reasonForReturnDescription")).get(new Random().nextInt(((List) selectedLineItem.get("reasonForReturnDescription")).size())));
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        for (Map item1 : itemsSelected) {
            int index = lineItemIndex(item1.get("upcId").toString());
            Clicks.javascriptClick(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemSelected"));
        }
        try {
            TextBoxes.typeTextbox(Elements.element("return_selection.email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        } catch (JSONException e) {
            Assert.fail("selectItemsAndContinue(): " + e);
        }
        if (Elements.elementPresent(Elements.element("return_selection.refund_method_container"))) {
            selectRefundMethod("default");
        }
        Clicks.click(Elements.element("return_selection.submit_return"));
        logger.info("Selected continue button without selecting reason for return");
        Wait.forPageReady();
    }

    @Then("^I select mandatory fields and continue$")
    public void iSelectMandatoryFieldsAndContinue() throws Throwable {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", ((List) selectedLineItem.get("reasonForReturnDescription")).get(new Random().nextInt(((List) selectedLineItem.get("reasonForReturnDescription")).size())));
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        for (Map item1 : itemsSelected)
            selectItem(item1.get("upcId").toString(), item1.get("quantity").toString(), item1.get("reasonForReturn").toString());
        try {
            TextBoxes.typeTextbox(Elements.element("return_selection.email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        } catch (JSONException e) {
            Assert.fail("selectItemsAndContinue(): " + e);
        }
        if (Elements.elementPresent(Elements.element("return_selection.refund_method_container"))) {
            selectRefundMethod("default");
        }
        Clicks.click(Elements.element("return_selection.submit_return"));
        Wait.forPageReady();

    }

    @When("^I select line item$")
    public void iSelectLineItem() throws Throwable {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", ((List) selectedLineItem.get("reasonForReturnDescription")).get(new Random().nextInt(((List) selectedLineItem.get("reasonForReturnDescription")).size())));
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        for (Map item1 : itemsSelected)
            selectItem(item1.get("upcId").toString(), item1.get("quantity").toString(), item1.get("reasonForReturn").toString());
    }

    @Given("^I select selected line item$")
    public void i_select_selected_line_item() throws Throwable {
        orderItems = orderItemDetails();
        selectedLineItem = (Map<String, Object>) ((List) orderItems.get("orderLineItemList")).get(new Random().nextInt(((List) orderItems.get("orderLineItemList")).size()));
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            ((List) selectedLineItem.get("reasonForReturnDescription")).remove(0);
        }
        productName = selectedLineItem.get("itemDescription");
        Map<String, Object> item = new HashMap<>();
        item.put("upcId", selectedLineItem.get("upcId"));
        item.put("quantity", "1");
        if (selectedLineItem.get("reasonForReturnDescription").getClass() == ArrayList.class) {
            item.put("reasonForReturn", ((List) selectedLineItem.get("reasonForReturnDescription")).get(new Random().nextInt(((List) selectedLineItem.get("reasonForReturnDescription")).size())));
        } else {
            item.put("reasonForReturn", selectedLineItem.get("reasonForReturnDescription"));
        }
        itemsSelected = new ArrayList<>();
        itemsSelected.add(item);
        logger.info("Select items to return");
        for (Map item1 : itemsSelected) {
            int index = lineItemIndex(item1.get("upcId").toString());
            Clicks.javascriptClick(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemSelected"));
        }
    }

    @When("^I attempt to continue with emails in selection page$")
    public void i_attempt_to_continue_with_emails_in_selection_page() throws Throwable {
        TextBoxes.typeTextbox(Elements.element("return_selection.email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox(Elements.element("return_selection.confirm_email"), TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail());
    }

    @And("^I verify that the information shall not persist$")
    public void iVerifyThatTheInformationShallNotPersist() throws Throwable {
        for (Map item1 : itemsSelected) {
            int index = lineItemIndex(item1.get("upcId").toString());
            boolean checkboxSelected = WebDriverManager.getWebDriver().findElement(By.name("returnDetails.returnShipment.lineItemList[" + index + "].itemSelected")).isSelected();
            Assert.assertFalse("Checkout should not be selected", checkboxSelected);
            logger.info("Item is unselected");
        }
    }

    public List<String> breadcrumbLinks() {
        List<String> links = Elements.findElements(Elements.element("order_status.breadcrumb_link")).stream().map(WebElement::getText).collect(Collectors.toList());
        return links;
    }

    public void selectBreadcrumb(String pagename) {
        Wait.forPageReady();
        List<WebElement> breadCrumbContainers = Elements.findElements(Elements.element("order_status.breadcrumb_link"));
        for (WebElement orderDetails : breadCrumbContainers) {
            if (orderDetails.getText().contains(pagename)) {
                Clicks.click(orderDetails);
                break;
            } else {
                Assert.fail("Breadcrumb item " + pagename + " does not exist in breadcrumb:");
            }
        }
    }

    @Then("^I should be displayed with refund method \"([^\"]*)\" in return submit page$")
    public void iShouldBeDisplayedWithRefundMethodInReturnSubmitPage(String displayType) throws Throwable {
        Wait.forPageReady();
        logger.info("Verifying the data for the order type" + Returns.returnOrderDetails.get("return_order").toString());
        String name = Returns.returnOrderDetails.get("first_name_last_name").toString();
        String actualName = Elements.getText("return_selection.refund_section_name").replace(",", "");
        logger.info("first_name_last_name is " + actualName);
        Assert.assertTrue("Name is not same", actualName.equals(name));
        String addressline1 = Returns.returnOrderDetails.get("address_line1").toString();
        String actualaddressline1 = Elements.getText("return_selection.refund_section_address1").replace(",", "");
        logger.info("Address line1 is " + actualaddressline1);
        Assert.assertTrue("Address line1 is not same", actualaddressline1.equals(addressline1));
        String addressline2 = Returns.returnOrderDetails.get("address_line2").toString();
        String actualaddressline2 = Elements.getText("return_selection.refund_section_address2").replace(",", "");
        logger.info("Address line2 is " + actualaddressline2);
        Assert.assertTrue("Address line2 is not same", actualaddressline2.equals(addressline2));
        String city = Returns.returnOrderDetails.get("city").toString();
        String actualCity = Elements.getText("return_selection.refund_section_city").replace(",", "");
        logger.info("City is " + actualCity);
        Assert.assertTrue("City is not same", actualCity.equals(city));
        String state = Returns.returnOrderDetails.get("state").toString();
        String actualState = Elements.getText("return_selection.refund_section_state").replace(",", "");
        logger.info("State is " + actualState);
        Assert.assertTrue("State is not same", actualState.equals(state));
        String zip_code = Returns.returnOrderDetails.get("zip_code").toString();
        String actualZipcode = Elements.getText("return_selection.refund_section_zipcode").replace(",", "");
        logger.info("ZipCode is " + actualZipcode);
        Assert.assertTrue("ZipCode is not same", actualZipcode.contains(zip_code));
        logger.info("Data verified for the order type" + Returns.returnOrderDetails.get("return_order").toString());
    }

    public Map getTrackInfo(String page) {
        List<WebElement> tracButton = null;
        Map<String, List<String>> trackInfo = new HashMap<>();
        List<String> listTracDetails = new ArrayList<String>();
        try {
            if (page.equals("OH")) {
                tracButton = Elements.findElements("order_status.track_button");
            } else {
                tracButton = Elements.findElements("order_details.track_button");
            }
            logger.info("Number of track buttons: " + tracButton.size());
            tracButton.get(0).click();
        } catch (NoSuchElementException e) {
            logger.info("Track Button not present");
        }
        WebElement trackContainer;
        WebElement trackNo;
        List<WebElement> trackDetails;
        if (macys()) {
            trackContainer = tracButton.get(0).findElement(By.xpath("//div[contains(@class,'shipmentContainer')]"));
            trackNo = trackContainer.findElement(By.xpath("//li[contains(@class,'trackID')]/span"));
              /*track Attributes*/
            trackDetails = trackContainer.findElements(By.xpath("//div[contains(@class,'trackingDetails')]//dl/dd"));

        } else {
            trackContainer = tracButton.get(0).findElement(By.xpath("//div[contains(@class,'orStatus_trackingInfoOuterContainer')]"));
            trackNo = trackContainer.findElement(By.xpath("//div[contains(@class,'orStatus_boldText orStatus_trackingNum')]"));
              /*track Attributes*/
            trackDetails = trackContainer.findElements(By.xpath(" //div[@class='orStatus_trackingDetailsRow orStatus_trackingDetailsRowLast']//div[starts-with(@class,'orStatus_trackingDetailsCell')]"));

        }
        for (WebElement e : trackDetails) {
            listTracDetails.add(e.getText());
        }
        trackInfo.put(trackNo.getText(), listTracDetails);
        return trackInfo;
    }

    @And("^I deselect selected line item$")
    public void iDeselectSelectedLineItem() throws Throwable {
        Wait.forPageReady();
        boolean checkboxSelected = Elements.findElement("return_selection.tuxedo_checkbox").isSelected();
        if (checkboxSelected == true) {
            Elements.findElement("return_selection.tuxedo_checkbox").click();
            Assert.assertTrue("Checkbox should be selected", checkboxSelected);
        } else
            Assert.assertFalse("Checkbox should be selected", checkboxSelected);
        logger.info("Item is unselected");
    }

    @And("^I select same line item$")
    public void iSelectSameLineItem() throws Throwable {
        Wait.untilElementPresent("return_selection.tuxedo_checkbox");
        Elements.findElement("return_selection.tuxedo_checkbox").click();
    }

    @Then("^I should see previous selected line item values as default$")
    public void iShouldSeePreviousSelectedLineItemValuesAsDefault() throws Throwable {
        Wait.forPageReady();
        String reason;
        String selectedItem = itemsSelected.get(0).get("reasonForReturn").toString();
        reason = DropDowns.getSelectedValue(Elements.element("return_selection.tuxedo_dropdown"));
        logger.info("text is : " + reason);
        Wait.forPageReady();
        Assert.assertTrue("Getting different value", reason.equals(selectedItem));
    }

    /**
     * Navigate to order selection page with given order type and user type
     *
     * @param orderType type of order
     * @param guestUser true for guest user, false for signed in
     */
    public void navigateToSelectionPageForZipCodeLookup(String orderType, boolean guestUser) {
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        if (returnOrderDetails == null) {
            Assert.fail("No order details found");
        }
        if (!returnOrderDetails.has("order_number")) {
            Assert.fail("Could not find order number");
        }
        String orderNumber = returnOrderDetails.getString("order_number");
        ReturnService returns = new ReturnService();
        if (!prodEnv()) {
            CommonUtils.deleteReturnRecord(orderNumber);
        }
        if (guestUser) {
            logger.info("Selected order as guest user");
        } else {
            if (returns.orderExistsByOrderNumber(orderNumber)) {
                returns.deleteOrderRecord(orderNumber);
            }
            if (!signedIn()) {
                new MyAccountSteps().iSignInToMyExistingProfile();
            }
            returns.insertOrderByOrderNumber(orderNumber, TestUsers.currentEmail);
        }
        logger.info("Goto order status page");
        Clicks.click(Elements.element("home.goto_order_status"));
        if (safari()) {
            Wait.secondsUntilElementPresent("order_status.verify_page", 10);
        }
        Wait.forPageReady();
        if (guestUser) {
            logger.info("search order by order id and email for guest user");
            lookupOrderByZip(returnOrderDetails);
            Assert.assertFalse("ERROR - DATA: Given order number is not present in environment!!", Elements.elementPresent("order_status.error_message"));
            logger.info("Click on returns order button on order detail page");
            Clicks.click("order_details.return_items");
        }
    }

    /* lookup order by using email and order number
    * @param returnOrderDetails order details
    */
    public void lookupOrderByEmailEasyReturnsPage(JSONObject returnOrderDetails) {
        Wait.untilElementPresent(Elements.element("easy_returns.view_order_details"));
        logger.info("Entering order number for order lookup " + returnOrderDetails.get("order_number").toString());
        Wait.untilElementPresent(Elements.element("easy_returns.order_number"));
        WebElement element = Elements.findElement("easy_returns.order_number");
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", element);
        TextBoxes.typeTextbox(Elements.element("easy_returns.order_number"), returnOrderDetails.get("order_number").toString());
        logger.info("Entering email for order lookup " + returnOrderDetails.get("email").toString());
        TextBoxes.typeTextbox(Elements.element("easy_returns.email"), returnOrderDetails.get("email").toString());
        Clicks.click(Elements.element("easy_returns.view_order_details"));
    }

    /**
     * lookup order by using phone and order number
     *
     * @param returnOrderDetails order details
     */
    public void lookupOrderByPhoneEasyReturnsPage(JSONObject returnOrderDetails) {
        Wait.untilElementPresent(Elements.element("easy_returns.view_order_details"));
        TextBoxes.typeTextbox(Elements.element("easy_returns.order_number"), returnOrderDetails.get("order_number").toString());
        TextBoxes.typeTextbox(Elements.element("easy_returns.phone_number"), returnOrderDetails.get("phone_number").toString());
        Clicks.click(Elements.element("easy_returns.view_order_details"));
    }

    /**
     * lookup order by using zip code and order number
     *
     * @param returnOrderDetails order details
     */
    public void lookupOrderByZipEasyReturnsPage(JSONObject returnOrderDetails) {
        Wait.untilElementPresent(Elements.element("easy_returns.view_order_details"));
        TextBoxes.typeTextbox(Elements.element("easy_returns.order_number"), returnOrderDetails.get("order_number").toString());
        // Elements.findElement("easy_returns.gift_order").isDisplayed();
        Elements.findElement("easy_returns.yes_radio_button").click();
        TextBoxes.typeTextbox(Elements.element("easy_returns.zip_code"), returnOrderDetails.get("shipping_zip_code").toString());
        Clicks.click(Elements.element("easy_returns.view_order_details"));
    }


    @And("^I should see a record should be created in DB$")
    public void iShouldSeeARecordShouldBeCreatedInDB() throws Throwable {
        HashMap returnItemFromDB = CommonUtils.getReturnInitiatedOrderInfo(returnOrderDetails);
        Assert.assertTrue("UPCs are different", selectedLineItem.get("upcId").equals(returnItemFromDB.get("upc")));
        Assert.assertTrue("Items descriptions are different", selectedLineItem.get("itemDescription").equals(returnItemFromDB.get("ITEM_DESC")));
        if (!selectedLineItem.get("colorDescription").equals("")) {
            Assert.assertTrue("Items descriptions are different", selectedLineItem.get("colorDescription").equals(returnItemFromDB.get("COLOR_DESC")));
            logger.info("Color descriptions are matching");
        }
        Assert.assertTrue("Vendor name descriptions are different", selectedLineItem.get("vendorName").equals(returnItemFromDB.get("VENDOR_NM")));
        if (!selectedLineItem.get("sizeDescription").equals("")) {
            Assert.assertTrue("Items descriptions are different", selectedLineItem.get("sizeDescription").equals(returnItemFromDB.get("SIZE_DESC")));
            logger.info("Size descriptions are matching");
        }
        Assert.assertTrue("Vendor name descriptions are different", selectedLineItem.get("quantitySelected").equals(returnItemFromDB.get("QUANTITY")));
        logger.info("Quantity descriptions are matching");
    }

    @Then("^I verify price and total for \"([^\"]*)\" shipment$")
    public void iVerifyPriceAndTotalForShipment(String shipmentStatus) throws Throwable {
        if (!onPage("order_details")) {
            Assert.fail("User is not in order details page!!");
        }
        if (shipmentStatus.equals("All")) {
            Map line_item = (Map) orderItems.get("orderHeader");
            Assert.assertTrue(line_item.get("orderTotal").equals(""));
            logger.info("Details are invisible in order details page for all shipments");
        } else {
            String statusHeader = (macys() ? (shipmentStatus.split("\\|")[0]) : (shipmentStatus.split("\\|")[shipmentStatus.split("\\|").length - 1]));

        }
    }

    @Then("^I should able to select reason for return$")
    public void iShouldAbleToSelectReasonForReturn() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Reason for return is disabled",Elements.findElement("return_selection.tuxedo_dropdown").isEnabled()) ;
        DropDowns.selectRandomValue("return_selection.tuxedo_dropdown");
        Wait.forPageReady();
        boolean isReasonselected= Elements.elementPresent("return_selection.tuxedo_dropdown");
        Assert.assertTrue("Reason for return value is not selected",isReasonselected);
    }

    @Then("^I should able to select quantity when quantity is more than one$")
    public void iShouldAbleToSelectQuantityWhenQuantityIsMoreThanOne() throws Throwable {
        Wait.forPageReady();
        if(Elements.elementPresent(Elements.element("return_selection.single_tuxedo_qty")))
        {
            String quantitySelected = Elements.getText("return_selection.single_tuxedo_qty");
            Assert.assertTrue("Value is not same as selected" ,quantitySelected.equals("1"));
        }
        else
        {
            Assert.assertTrue("return qty is disabled",Elements.findElement("return_selection.tuxedo_qty").isEnabled()) ;
            List<String> returnQuantity = DropDowns.getAllValues("return_selection.tuxedo_qty");
            logger.info("returnQuantity is "+returnQuantity.size());
            if(returnQuantity.size()>1)
            {
                Wait.forPageReady();
                DropDowns.selectByText("return_selection.tuxedo_qty", "2");
                String quantitySelected = Elements.findElement(By.xpath("//select[@id='returnDetails.returnShipment.lineItemList0.selectedQuantity']/option[2]")).getText();
                Assert.assertTrue("Value is not same as selected" ,quantitySelected.equals("2"));
            }
        }
    }
}
