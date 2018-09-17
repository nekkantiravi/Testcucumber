package com.macys.sdt.projects.Customer.MensWearHouse.actions.website;

import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.interactions.Clicks;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import com.macys.sdt.framework.utils.rest.services.AddToBagService;
import com.macys.sdt.framework.utils.Cookies;

/**
 * Created by YC05PG3 on 1/18/2017.
 */

public class MenswearHouseDigital extends StepUtils {
    public static Map<String, String> prod_details = new HashMap<>();

    public static void validatePaypalBtnExist(){
        //WebDriver driver = MainRunner.getWebDriver();
        boolean btnpresent = Elements.findElement(By.id ("checkoutWithPayPal")).isDisplayed();
        //if(!driver.findElement(By.id ("checkoutWithPayPal")).isDisplayed())
        Assert.assertTrue("Paypal button not visible",!btnpresent);
        //Another code:
    }

    public static void tuxBold(){
        //WebDriver driver = MainRunner.getWebDriver();
        WebElement tuxbutton = Elements.findElement(By.id ("tux-name-heading"));
        boolean txttrue= tuxbutton.getCssValue("font-weight").equals("bold");
        Assert.assertTrue("Tuxedo Rental is bold",txttrue);
    }

    public static void validateShipExist() {
        boolean btnpresent= Elements.findElement(By.className("inStorePickUp")).isDisplayed();
        Assert.assertTrue("Move to list not visible",!btnpresent);
    }

    public static void validateMoveListExist() {
        boolean btnpresent= Elements.findElement(By.name("Move to list")).isDisplayed();
        Assert.assertTrue("Move to list not visible",!btnpresent);
    }

    public static void ChargeInfo(String txtmsg) {
        WebElement txtInfo = Elements.findElement(By.id("rental-ship-msg"));
        Assert.assertEquals("Rental ship messgae validated",txtmsg,txtInfo);
    }

    public static void removeTuxItem() {
        int noOfProducts = Elements.findElements(Elements.element("shopping_bag.item_names")).size();
        boolean tux_item_present;
        for (int i = 0; i < noOfProducts; i++) {
            tux_item_present = Elements.findElements(Elements.element("shopping_bag.item_names")).get(i).getText().contains("Tuxedo");

            if (tux_item_present==true) {
                String elementName = ".remove_current_bag_items";
                Wait.untilElementPresent("shopping_bag" + elementName);
                Clicks.click("shopping_bag" + elementName);
            }
        }
    }

    public static Map<String, String> getTuxProductDetails() {

        //List<String> tux_product_details = new ArrayList<>();

        Map<String, String> tux_product_details = new HashMap<>();
        String userId = Cookies.getCookieValue("macys_online_uid");
        String str = AddToBagService.addTUXItemToBag(userId, null);
        JSONObject jsonObject = new JSONObject(str);

        JSONObject result = jsonObject.getJSONObject("bag");
        JSONArray desc = result.getJSONArray("items");
        String description = desc.getJSONObject(0).getJSONArray("mkpReservations").getJSONObject(0).getString("description");
        String reservation_id = desc.getJSONObject(1).getJSONArray("mkpReservations").getJSONObject(0).getString("mkpReservationId");
        String first_name = desc.getJSONObject(1).getJSONArray("mkpReservations").getJSONObject(0).getString("renterToLastName");
        String last_name = desc.getJSONObject(1).getJSONArray("mkpReservations").getJSONObject(0).getString("renterToFirstName");
        String event_date = desc.getJSONObject(1).getJSONArray("mkpReservations").getJSONObject(0).getString("eventDate");

        tux_product_details.put("tux_descrption", description);
        tux_product_details.put("tux_reservation_id", reservation_id);
        tux_product_details.put("tux_prod_first_name", first_name);
        tux_product_details.put("tux_last_name", last_name);
        tux_product_details.put("tux_event_date", event_date);

        return tux_product_details;
    }

    public static void validateHidePriceGiftReceiptIsChecked(){
        Assert.assertTrue("hide price for payslip option is not checked", Elements.findElement("responsive_checkout_signed_in.gift_receipt").isSelected());

    }
}
