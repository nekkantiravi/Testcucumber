package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils;


import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies.CookieManager;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class ShopNServeService {

    private static final String PATH = "/chkout";
    private static final String PAYMENT_PATH = "/rcpayment";
    private static final String SHIPPING_PATH = "/rcshipping";
    private static final String ORDER_SUMMARY_PATH = "/rcordersummary";
    private static final String ORDER_PATH = "/order";
    private static final String SELECTED_SHIPPING_ADDRESS = "/order/selectedshippingaddress";
    private static final String SELECTED_CREDIT_CARD = "/order/selectedcreditcard";

    private static String getSNSServiceConnection(String servicePath) throws RuntimeException {
        StringBuilder cookies = new StringBuilder("");
        for (Cookie cookie : new CookieManager().getCookies())
            cookies.append(String.format("%s=%s; ", cookie.getName(), cookie.getValue()));

        String host;
        try {
            host = new URI(RunConfig.url).getHost();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String url = "https://" + host + PATH + servicePath;
        String response;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("Cookie", cookies.toString());
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            conn.connect();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException(String.format("Error requesting %s, code %s", servicePath, conn.getResponseCode()));
            }

            response = IOUtils.toString(conn.getInputStream(), "UTF-8");
            conn.disconnect();
        } catch (IOException e) {
            throw new RuntimeException("Error connecting to " + url, e);
        }

        if (response == null) {
            throw new RuntimeException("Something went wrong while getting result from " + url
                    + ". Connection was ok, but result is null");
        }

        return response;
    }


    public static JSONObject getPaymentTypesAndMethods() {
        // Using this approach with HttpURLConnection, because with apache.common.HttpClient it didn't work, I got 403 with it
        return new JSONObject(getSNSServiceConnection(PAYMENT_PATH)).getJSONObject("responsivePayment");

    }

    public static JSONObject getShippingInfo() {
        return new JSONObject(getSNSServiceConnection(SHIPPING_PATH)).getJSONObject("responsiveShipping");
    }

    public static JSONObject getOrderSummary() {
        return new JSONObject(getSNSServiceConnection(ORDER_SUMMARY_PATH)).getJSONObject("responsiveOrderSummary");
    }

    public static JSONObject getOrder() {
        return new JSONObject(getSNSServiceConnection(ORDER_PATH)).getJSONObject("order");

    }

    public static JSONObject getSelectedShippingAddressOrder() {
        return new JSONObject(getSNSServiceConnection(SELECTED_SHIPPING_ADDRESS)).getJSONObject("order");
    }

    public static JSONObject getSelectedCreditCard() {
        return new JSONObject(getSNSServiceConnection(SELECTED_CREDIT_CARD)).getJSONObject("order");
    }
}
