package com.macys.sdt.projects.Customer.ReturnManagement.actions;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ReturnsActions extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReturnsActions.class);

    public static void do_something() {
        // Create methods to do page-specific operations.
        // In general these methods should be static, but it's not required that they be.
    }

    /**
     * to get order container
     *
     * @param orderNumber specific order number
     */

    public WebElement orderContainerToSelect(String orderNumber) throws Throwable {
        Wait.forPageReady();
        Thread.sleep(5000);
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

    // Gives you Line Items details from Selection page.
    public List<HashMap> eligibleItemsToReturn() {
        ArrayList<HashMap> eligibleLineItemDetails = new ArrayList<>();
        List<WebElement> eligibleLineItems;
        eligibleLineItems = Elements.findElements(By.className("product-list-item"));
        Wait.secondsUntilElementPresent(By.className("details-price"), 10);
        for (WebElement e : eligibleLineItems) {
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("Product_Name", getValueProductAttribute(e, "product-list-item--title"));
            hm.put("Size", getValueProductAttribute(e, "details-size"));
            hm.put("Color", getValueProductAttribute(e, "details-color"));
            hm.put("Total_Qty", getValueProductAttribute(e, "details-qty"));
            hm.put("Price", getValueProductAttribute(e, "details-price"));
            hm.put("Reason_For_Return", selectedDropDownValue(e.findElement(By.className("js-or-select-reason"))));
            hm.put("Qty", selectedDropDownValue(e.findElement(By.className("js-or-select-qty"))));
            hm.put("Line_Item_Selected?", (e.findElement(By.className("js-or-select-status")).isSelected()));
            eligibleLineItemDetails.add(hm);
        }
        return eligibleLineItemDetails;
    }

    private static String getValueProductAttribute(WebElement e, String className) {
        try {
            return e.findElement(By.className(className)).getText();
        } catch (NoSuchElementException ignore) {
            logger.info("Product attribute " + className + " not available");
        }
        return "";
    }

    public static List<WebElement> unSelectedLineItems() {
        return Elements.findElements(By.className("product-list-item"))
                .stream().filter(e -> !(e.findElement(By.className("js-or-select-status")).isSelected()))
                .collect(Collectors.toList());
    }


    public static List<WebElement> selectedLineItems() {
        return Elements.findElements(By.className("product-list-item"))
                .stream().filter(e -> (e.findElement(By.className("js-or-select-status")).isSelected()))
                .collect(Collectors.toList());
    }


    public void selectItemsToReturn(int numberOfItemsToReturn) {
        List<WebElement> return_items = Elements.findElements(By.className("product-list-item"))
                .stream().filter(e -> !(e.findElement(By.className("js-or-select-status")).isSelected()))
                .collect(Collectors.toList());
        for (int i = 0; i < numberOfItemsToReturn ; i++) {
            selectItem(return_items.get(i));
        }
    }

    private void selectItem(WebElement e) {
        WebElement q = e.findElement(By.className("js-or-select-qty"));
        Clicks.click(e.findElement(By.className("product-list-item-checkbox")));
        Wait.until(q::isEnabled, 5);
        Random rand = new Random();
        Select select = new Select(e.findElement(By.className("js-or-select-reason")));
        select.getOptions().remove(0);
        select.selectByIndex(rand.nextInt(select.getOptions().size()));
        select = new Select(e.findElement(By.className("js-or-select-qty")));
        select.selectByIndex(rand.nextInt(select.getOptions().size()));
    }

    private String selectedDropDownValue(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }


    private String pendingReturnID()
    {
        return Elements.getText("return_confirmation_page.pending_return_id").split("#")[1];
    }

    private String instructionsOnConfirmationPage()
    {
        return Elements.getText("return_confirmation_page.instructions");
    }

    // Gives you  Line Items details from Return Conformation page
    public List<HashMap> confirmationItemDetails()
    {
        ArrayList<HashMap> itemDetails = new ArrayList<>();
        HashMap<String, Object> inDetails = new HashMap<String, Object>();
        List<WebElement> productItems;
        productItems = Elements.findElements("return_confirmation_page.product_item");
        for(WebElement e : productItems)
        {
            inDetails.put("Product_Name", e.findElement(Elements.element("return_confirmation_page.product_name")).getText());
            inDetails.put("Reason_For_Return", e.findElement(Elements.element("return_confirmation_page.reason_for_return")).getText().split("Reason for Return:")[1]);
            inDetails.put("Credit_Sub_Total", e.findElement(Elements.element("return_confirmation_page.credit_sub_total")).getText().split("Credit Subtotal:")[1]);
            for(WebElement ele : e.findElements(By.tagName("span"))){
                inDetails.put(ele.getText().split(":")[0], ele.getText().split(":")[1]);
            }
            itemDetails.add(inDetails);

        }
        return itemDetails;
    }

    public HashMap<String, String> pickupDetails()
    {
        HashMap<String,String> pickupDetail = new HashMap<String, String>();
        if (!Elements.anyPresent("return_confirmation_page.pickup_confirmation_info")) return pickupDetail;
        WebElement container = Elements.findElement("return_confirmation_page.pickup_confirmation_info");
        for (WebElement e : container.findElements(Elements.element("return_confirmation_page.pickup_line_details")))
        {
            pickupDetail.put(e.getText().split(":")[0],e.getText().split(":")[1]);
        }
        return pickupDetail;
    }


    // Gives you Details from Return Conformation page
    public HashMap<String, Object> returnConfirmationHeaderDetails()
    {
        HashMap<String, Object> submitDetails = new HashMap<String, Object>();
        submitDetails.put("pendingreturnId", pendingReturnID());
        WebElement returnDetails = Elements.findElement("return_confirmation_page.return_details");
        for (WebElement e: returnDetails.findElements(By.className("confirmation-item")))
        {
            submitDetails.put(e.getText().split(":")[0], e.getText().split(":")[1]);
        }
        if(Elements.anyPresent("return_confirmation_page.instructions_pickup")) {
            submitDetails.put("pickup_details", pickupDetails());
            submitDetails.put("pickup_instructions", Elements.getText("return_confirmation_page.instructions_pickup"));
        }else if(Elements.anyPresent("return_confirmation_page.instructions_store_dropoff")){
            submitDetails.put("store_instructions",Elements.getText("return_confirmation_page.instructions_store_dropoff"));
        }else {
            submitDetails.put("ups_drop_off_instructions", Elements.getText("return_confirmation_page.instructions_ups_dropoff"));
        }
        return submitDetails;
    }

    public HashMap<String, String> confirmationReturnCreditDetails()
    {
        HashMap<String, String> creditDetails = new HashMap<String, String>();
        WebElement credit_section = Elements.findElement("return_confirmation_page.credit_details");
        for(WebElement ele : credit_section.findElements(By.className("confirmation-item")))
        {
            creditDetails.put(ele.getText().split(":")[0], ele.getText().split(":")[0]);
        }
        return  creditDetails;
    }

    //Gives you Details from Refund method page
    public HashMap<String, String> getRefudndDetails()
    {
        HashMap<String, String> refundDetails = new HashMap<String, String>();
        refundDetails.put("Refund Method", "Original Payment Method");
        refundDetails.put("Refund Email", Elements.findElement("refund_method_page.contact_email").getAttribute("value"));
        refundDetails.put("Refund Phone", Elements.findElement("refund_method_page.contact_phone").getAttribute("value"));
        if(Elements.anyPresent("refund_method_page.gift_card_payment") && Elements.findElement("refund_method_page.gift_card_payment").isSelected()){
            refundDetails.put("Refund Method", "Macys Gift Card");
        }
        return  refundDetails;
    }

    // Gives you Details from Return Method page.
    public HashMap<String, String> getReturnMethodDetails()
    {
        HashMap<String, String> returnMethodDetails = new HashMap<String, String>();
        returnMethodDetails.put("Return Method", "Store Drop off");
        if(Elements.findElement("return_method_page.ups_dropbox").isSelected()){
            returnMethodDetails.put("Return Method", "UPS drop off");
        }
        else if(Elements.anyPresent("return_method_page.return_pickup") && Elements.findElement("return_method_page.return_pickup").isSelected()){
            returnMethodDetails.put("Return Method", "Return Pickup");
            returnMethodDetails.put("Pickup Address", Elements.findElement("return_method_page.return_pickup_address").getText());
            returnMethodDetails.put("Pickup Day", DropDowns.getSelectedValue(Elements.element("return_method_page.return_pickup_day")));
            returnMethodDetails.put("Pickup Time", DropDowns.getSelectedValue(Elements.element("return_method_page.return_pickup_time")));
            returnMethodDetails.put("Pickup Instructions", Elements.findElement("return_method_page.return_pickup_instruction").getText());
        }

        return  returnMethodDetails;
    }

    public static  WebElement getConforamtionItem(String itemName){
        List<WebElement> conformtaionItems = Elements.findElements("return_confirmation_page.confirmation_item");
        for(WebElement e : conformtaionItems){
            String lable = e.getText().trim().toLowerCase();
            if(lable.contains(itemName.trim().toLowerCase())){
                return e;
            }
        }
        return null;
    }

    public static  WebElement getConforamtionPickupItem(String itemName){
        List<WebElement> conformtaionItems = Elements.findElements("return_confirmation_page.pickup_line_details");
        for(WebElement e : conformtaionItems){
            String lable = e.getText().trim().toLowerCase();
            if(lable.contains(itemName.trim().toLowerCase())){
                return e;
            }
        }
        return null;
    }

}
