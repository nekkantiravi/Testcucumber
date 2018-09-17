package com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.website;

import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class GenericUtils extends StepUtils {

    /**
     * Method to add or update FST cookie
     **/
    public static void setSegmentFSTCookie(String FSTcookie) {
        String segmentCookie = Cookies.getCookieValue("SEGMENT");
        String cookies[] = {"1490", "1614", "1615", "1616", "1489"};
        boolean isSegmentCookiePresent = Arrays.asList(cookies).contains(segmentCookie);
        if (isSegmentCookiePresent) {
            if (!segmentCookie.contains(FSTcookie)) {
                Cookies.removeSegment(segmentCookie);
                Cookies.addSegment(FSTcookie);
                System.out.println("FST cookie " + segmentCookie + " replaced with " + FSTcookie);
            } else {
                System.out.println("No need to update FST cookie as it already exists");

            }

        } else if (!isSegmentCookiePresent && !(segmentCookie.equals("") || segmentCookie == null)) {
            Cookies.addSegment(FSTcookie);
            System.out.println(Cookies.getCookieValue("SEGMENT"));
            System.out.println("FST cookie " + FSTcookie + " is added as no FST cookie value found ");

        } else if (!isSegmentCookiePresent && (segmentCookie.equals("") || segmentCookie == null)) {
            Cookies.addCookie("SEGMENT", FSTcookie);
            System.out.println("Segment cookie with " + FSTcookie + " value is added");
        }
    }

    /**
     * Method to return shipping cost
     * @return shipping cost
     **/
    public static String getShippingFee(String selector) {
        Wait.forPageReady();
        String shippingTotal = null;
        try {
            shippingTotal = Elements.findElement(selector).getText();
        } catch (Exception e) {
            System.out.println("Could not extract est shipping total");
            e.printStackTrace();
        }
        return shippingTotal;
    }
    
    /**
     * Method to return all FST cookie and price information
     *
     * @return FST cookie and price information
     */
    public static JSONObject getFstPrice() {
        File values = getResourceFile("fst_ab_testing.json");
        JSONObject jsonObject = null;

        try {
            String jsonTxt = Utils.readTextFile(values);
            jsonObject = new JSONObject(jsonTxt);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Method to set item quantity on shopping bag page
     **/
    public static void setQty(String qty) {
        try {
            DropDowns.selectByText("shopping_bag.current_bag_qty_dropdown", qty);
        } catch (Exception e) {
            Assert.fail("Could not set the qty in shopping bag page");
            e.printStackTrace();
        }
        Utils.threadSleep(1000, "Waiting for subtotal update");
    }

    /**
     * Method to set saved bag item quantity
     **/
    public static void setSavedBagQty(String qty) {
        try {
            DropDowns.selectByText("shopping_bag.saved_bag_qty_dropdown", qty);
        } catch (Exception e) {
            Assert.fail("Could not set the qty in shopping saved bag page");
            e.printStackTrace();
        }
        Utils.threadSleep(1000, "Waiting for subtotal update");
    }

    /**
     * Method to shopping bag subtotal
     * @return subtotal
     **/
    public static String getShoppingBagSubtotal(String selector) {
        String subTotal = null;
        try {
            subTotal = Elements.findElement(selector).getText();
        } catch (Exception e) {
            System.out.println("Could not extract order subtotal in shopping bag page");
            e.printStackTrace();
        }
        return subTotal;
    }

    /**
     * Method to verify order subtotal
     **/
    public static void verifyOrderSubTotal(String condition, String threshold) {
        String total_with_dollar_symbol = GenericUtils.getShoppingBagSubtotal("shopping_bag.subtotal_price");
        String subTotal = total_with_dollar_symbol.substring(1);
        switch (condition) {
            case "more":
                Assert.assertTrue("Order subtotal is less than threshold",
                        Float.parseFloat(subTotal) > Float.parseFloat(threshold));
                break;
            case "less":
                Assert.assertTrue("Order subtotal is more than threshold",
                        Float.parseFloat(subTotal) < Float.parseFloat(threshold));
                break;
        }
    }
}
