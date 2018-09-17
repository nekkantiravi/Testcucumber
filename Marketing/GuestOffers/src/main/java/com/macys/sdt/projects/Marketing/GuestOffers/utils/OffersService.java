package com.macys.sdt.projects.Marketing.GuestOffers.utils;

import java.util.HashMap;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import javax.ws.rs.core.Response;



public class OffersService {
    public static final String GET_OFFER_DETAILS = "/order/v1/bags/";

    public static JSONArray getOfferDetails(String bag_id, String user_token) {
        HashMap<String, String> header = new HashMap<>();

        header.put("X-Macys-SecurityToken", user_token);
        header.put("bagId", bag_id);

        String endPoint = getServiceURL();
        String request = endPoint + bag_id + "/promotions?";
        Response response = RESTOperations.doGET(request, header);

        int statusCode = response.getStatus();
        Assert.assertEquals("Response code is not 200", 200, statusCode);

        String finalJsonStr = response.readEntity(String.class);
        JSONObject rootJson = new JSONObject(finalJsonStr);
        JSONObject bagDetails = rootJson.getJSONObject("bag");
        JSONArray offerDetails = bagDetails.getJSONArray("promotions");
        return offerDetails;

    }

    private static String getServiceURL() {
        return "http://" + EnvironmentDetails.otherApp("MSPORDER").ipAddress + ":8080/api" + GET_OFFER_DETAILS;
    }
}

