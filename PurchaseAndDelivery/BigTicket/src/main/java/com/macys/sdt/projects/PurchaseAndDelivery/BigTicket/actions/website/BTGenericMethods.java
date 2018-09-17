package com.macys.sdt.projects.PurchaseAndDelivery.BigTicket.actions.website;


import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.utils.CommonUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

import static com.sun.jna.platform.win32.COM.tlb.TlbImp.logInfo;

public class BTGenericMethods {

    public static Map<String, String> checkoutHeaders = new HashMap<>();

    public static List<String> getMemberProductIds() {
        List<String> productIds = new ArrayList<>();
        try {
            Wait.untilElementPresent("product_display.member_products");
            List<WebElement> thumbnails = Elements.findElements("product_display.member_products");
            productIds = thumbnails.stream().map(e -> e.getAttribute("id")).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            Assert.fail("Member Products are not displayed" + e);
        }
        logInfo("Displayed product ids: " + productIds);
        return productIds;
    }

    public static void addBTToBag(String userId, String productType) throws Throwable {
        Product productDetails = CommonUtils.navigateToRandomProduct(productType, null);
        String serviceUrl = getBagServiceURL() + userId + "&deliveryZipCode=" + productDetails.zipCode;
        checkoutHeaders.put("X-Macys-ClientId", "Site");
        Response res = RESTOperations.doPOST(serviceUrl, "application/xml", getAddToBagBody(productDetails.upc), checkoutHeaders);
        if (res.getStatus() != 200) {
            Assert.fail("Add to bag returned" + res.getStatus());
        }
        logInfo("Product is added to bag " + productDetails.id);
    }

    /**
     * Generates a random number
     *
     * @return random number as String
     */
    public static String generateRandomNumber(int length) {
        String randomNumber = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            randomNumber += String.valueOf(rand.nextInt(10));
        }
        return randomNumber;
    }

    private static String getBagServiceURL() {
        return "http://" + EnvironmentDetails.otherApp("MSPORDER").ipAddress + ":8080/api/order/v1/bags?userId=";
    }

    private static String getAddToBagBody(long upcId) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<item>\n<quantity>1</quantity>\n<upcId>" + upcId + "</upcId>\n" + "</item>";
    }
}
