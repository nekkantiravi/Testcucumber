package com.macys.sdt.projects.Discovery.Regression.utils.config.MEW;

import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.utils.BrowsePageServices;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickAddProductService {

    public static JSONObject response = null;

    private static Map<String, String> canvasHeader = new HashMap<String, String>() {{
        put("x-macys-webservice-client-id", "mewqe");
        put("Accept", "application/json");

    }};

    private static JSONObject getProductService(Integer productId, String mode) {
        if (response == null || !productId().equals(productId)) {
            String url = WebDriverManager.getCurrentUrl();
            String hostName = url.toLowerCase().contains("zeus") ? BrowsePageServices.getZeusEnv(url) : EnvironmentDetails.getEnv(url);
            String baseCanvasPath = "http://api.<hostName>.fds.com/v4/catalog/product/<productId>?includeUnavailable=true&viewType=pdp&includeFinalPrice=true&application=MEW&device=PHONE";
            if (mode.equalsIgnoreCase("domestic")) {
                baseCanvasPath = baseCanvasPath + "&country=US&assortment=SITE";
            } else if (mode.equalsIgnoreCase("iship")) {
                baseCanvasPath = baseCanvasPath + "&country=" + Cookies.getCookieValue("shippingCountry") +
                        "&currency=" + Cookies.getCookieValue("currency") + "&assortment=SITE";
            } else {
                baseCanvasPath = baseCanvasPath + "&country=US&assortment=REGISTRY";
            }
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<productId>", productId.toString());
            System.out.println("Constructed url: ------- " + baseCanvasPath);
            try {
                response = new JSONObject(RESTOperations.doGET(baseCanvasPath, canvasHeader).readEntity(String.class));
            } catch (Exception e) {
                Assert.fail("Error Fetching Service" + e.getMessage());
            }
            return response;
        } else {
            return response;
        }
    }

    private static Integer productId() {
        return Integer.parseInt(((JSONObject) ((JSONArray) response.get("product")).get(0)).get("id").toString());
    }

    public static String productName(Integer productId, String mode) {
        JSONObject response = getProductService(productId, mode);
        JSONObject summary = (JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) response.get("product")).get(0)).get("productDetails")).get("summary");
        return summary.getString("name");
    }

    public static List<String> productSizes(Integer productId, String mode) {
        JSONObject response = getProductService(productId, mode);
        JSONObject productDetails = (JSONObject) ((JSONObject) ((JSONArray) response.get("product")).get(0)).get("productDetails");
        List<String> sizes = new ArrayList<>();
        if (productDetails.has("SizeMap")) {
            JSONArray sizeMap = (JSONArray) productDetails.get("SizeMap");
            for (int i = 0; i < sizeMap.length(); i++) {
                JSONObject size = (JSONObject) sizeMap.get(i);
                sizes.add(size.getString("sizenormal"));
            }
        }
        return sizes;
    }

    public static List<String> productColors(Integer productId, String mode) {
        JSONObject response = getProductService(productId, mode);
        JSONObject productDetails = (JSONObject) ((JSONObject) ((JSONArray) response.get("product")).get(0)).get("productDetails");
        List<String> colors = new ArrayList<>();
        if (productDetails.has("colorMap")) {
            JSONArray colorMap = (JSONArray) productDetails.get("colorMap");
            for (int i = 0; i < colorMap.length(); i++) {
                JSONObject color = (JSONObject) colorMap.get(i);
                colors.add(color.getString("color"));
            }
        }
        return colors;
    }

    public static Double productSalePrice(Integer productId, String mode) {
        JSONObject response = getProductService(productId, mode);
        JSONObject pd = (JSONObject) ((JSONObject) ((JSONArray) response.get("product")).get(0)).get("productDetails");

        if (((JSONObject) pd.get("price")).has("sale")) {
            return ((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("sale")).get("pricevalue"))).getDouble("low");
        } else {
            return ((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("retail")).get("pricevalue"))).getDouble("low");
        }
    }

    public static List<Double> productMinMaxPrice(Integer productId, String mode) {
        List<Double> productMinMaxPrice = new ArrayList<>();
        JSONObject response = getProductService(productId, mode);
        JSONObject pd = (JSONObject) ((JSONObject) ((JSONArray) response.get("product")).get(0)).get("productDetails");
        if (((JSONObject) pd.get("price")).has("sale")) {
            if (((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("sale")).get("pricevalue"))).has("high")) {
                productMinMaxPrice.add(((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("sale")).get("pricevalue"))).getDouble("low"));
                productMinMaxPrice.add(((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("sale")).get("pricevalue"))).getDouble("high"));
            } else {
                productMinMaxPrice.add(((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("sale")).get("pricevalue"))).getDouble("low"));
            }
        } else {
            if (((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("retail")).get("pricevalue"))).has("high")) {
                productMinMaxPrice.add(((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("retail")).get("pricevalue"))).getDouble("low"));
                productMinMaxPrice.add(((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("retail")).get("pricevalue"))).getDouble("high"));
            } else {
                productMinMaxPrice.add(((JSONObject) (((JSONObject) ((JSONObject) pd.get("price")).get("retail")).get("pricevalue"))).getDouble("low"));
            }
        }
        return productMinMaxPrice;
    }
}
