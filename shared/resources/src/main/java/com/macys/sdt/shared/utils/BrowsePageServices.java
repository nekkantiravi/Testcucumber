package com.macys.sdt.shared.utils;

import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowsePageServices {

    private static final Logger log = LoggerFactory.getLogger(BrowsePageServices.class);

    private static Map<String, String> canvasHeader = new HashMap<String, String>() {{
        put("x-macys-webservice-client-id", "mewqe");
        put("Accept", "application/json");

    }};

    private static boolean isOnIshipeMode() {
        String country = Cookies.getCookieValue("shippingCountry");
        if (country.equalsIgnoreCase("us")) {
            return false;
        } else {
            return true;
        }
    }

    public static String getZeusEnv (String envUrl) {
        try {
            return envUrl.split(".fds")[0].split("\\/\\/\\.*[a-zA-Z0-9]+\\.")[1];
        } catch (Exception e) {
            log.error("Unable to get environment details");
            return null;
        }
    }

    private static JSONObject getBrowsePageDataFromService(String mode, String categoryId) {
        JSONObject jsonResponse = null;
        String value;
        String url = WebDriverManager.getCurrentUrl();
        String hostName = url.toLowerCase().contains("zeus") ? getZeusEnv(url) : EnvironmentDetails.getEnv(url);
        String baseCanvasPath = "http://api.<hostName>.fds.com/v3/catalog/category/<categoryId>/browseproducts?";
        String contextUrl = "currentpage=1&expand=stores&facetGroupsSupported=true&resultsperpage=90&redirect=true&show=facet,product,productpool&device=PHONE&application=MEW";
        String assortment = "&assortment=WEDDING_REGISTRY";
        try {
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<categoryId>", categoryId);
            if (mode.equalsIgnoreCase("registry")) {
                value = baseCanvasPath + contextUrl + assortment;
            } else if (isOnIshipeMode()) {
                String currency = Cookies.getCookieValue("currency");
                String regionCode = Cookies.getCookieValue("shippingCountry");
                contextUrl = contextUrl + "&country=" + regionCode + "&currency=" + currency;
                value = baseCanvasPath + contextUrl;
            } else {
                value = baseCanvasPath + contextUrl;
            }
            System.out.println("Constructed service url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value, canvasHeader).readEntity(String.class));
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }

    public static List<Integer> getProductsFromService(String mode, String categotyId) {
        JSONObject response = getBrowsePageDataFromService(mode, categotyId);
        List<Integer> productsIds = new ArrayList<>();
        JSONObject currentCategory = (JSONObject) ((JSONArray) response.get("category")).get(0);
        JSONArray productList = (JSONArray) ((JSONObject) currentCategory.get("product")).get("product");
        for (int i = 0; i < productList.length(); i++) {
            JSONObject json = (JSONObject) productList.get(i);
            int id = Integer.parseInt(json.get("id").toString());
            productsIds.add(id);
        }
        return productsIds;
    }

    public static Integer getTotalProductCountFromService(String mode, String categotyId) {
        JSONObject response = getBrowsePageDataFromService(mode, categotyId);
        JSONObject currentCategory = (JSONObject) ((JSONArray) response.get("category")).get(0);
        Integer productCount = (Integer) currentCategory.get("totalproducts");
        return productCount;
    }

    public static List<String> getFacetDisplayNamesFromService(String mode, String categotyId) {
        JSONObject response = getBrowsePageDataFromService(mode, categotyId);
        List<String> displayNames = new ArrayList<>();
        JSONObject currentCategory = (JSONObject) ((JSONArray) response.get("category")).get(0);
        JSONArray displayFacets = (JSONArray) currentCategory.get("facet");
        for (int i = 0; i < displayFacets.length(); i++) {
            JSONObject list = (JSONObject) displayFacets.get(i);
            String displayName = (String) list.get("displayname");
            displayNames.add(displayName);
        }
        return displayNames;
    }

    public static List<String> getFacetValuesFromService(String mode, String categotyId, String facetName) {
        JSONObject response = getBrowsePageDataFromService(mode, categotyId);
        List<String> facetValues = new ArrayList<>();
        JSONObject currentCategory = (JSONObject) ((JSONArray) response.get("category")).get(0);
        JSONArray displayFacets = (JSONArray) currentCategory.get("facet");
        for (int i = 0; i < displayFacets.length(); i++) {
            JSONObject list = (JSONObject) displayFacets.get(i);
            if (list.getString("displayname").equalsIgnoreCase(facetName)) {
                JSONArray facetList = (JSONArray) list.get("value");
                for (int j = 0; j < facetList.length(); j++) {
                    JSONObject facet = (JSONObject) facetList.get(j);
                    String facetValue = facet.getString("name");
                    facetValues.add(facetValue);
                }
            }
        }
        return facetValues;
    }

    public static Map<String, List<String>> getAllFacetNameValuesFromService(String mode, String categotyId) {
        JSONObject response = getBrowsePageDataFromService(mode, categotyId);
        Map<String, List<String>> facetNameValues = new HashMap<>();
        JSONObject currentCategory = (JSONObject) ((JSONArray) response.get("category")).get(0);
        JSONArray displayFacets = (JSONArray) currentCategory.get("facet");
        for (int i = 0; i < displayFacets.length(); i++) {
            List<String> facetValues = new ArrayList<>();
            JSONObject list = (JSONObject) displayFacets.get(i);
            JSONArray facetList = (JSONArray) list.get("value");
            for (int j = 0; j < facetList.length(); j++) {
                JSONObject facet = (JSONObject) facetList.get(j);
                String facetValue = facet.getString("name");
                facetValues.add(facetValue);
            }
            facetNameValues.put(list.getString("displayname"), facetValues);
        }
        return facetNameValues;
    }
}
