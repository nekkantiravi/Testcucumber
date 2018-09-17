package com.macys.sdt.shared.utils;

import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalNavigationServices {

    private static Map<String, String> canvasHeader = new HashMap<String, String>() {{
        put("x-macys-webservice-client-id", "mewqe");
        put("Accept", "application/json");

    }};

    private static JSONObject getChildCategoriesFromGN(String mode, String categoryId) {
        JSONObject jsonResponse = null;
        String value;
        String url = WebDriverManager.getCurrentUrl();
        String hostName = url.toLowerCase().contains("zeus") ? BrowsePageServices.getZeusEnv(url) : EnvironmentDetails.getEnv(url);
        String baseCanvasPath = "http://api.<hostName>.fds.com/v4/catalog/category/<categoryId>?";
        String contextUrl = "expand=parent&application=MEW&device=PHONE&redirect=true";
        String assortment = "&assortment=WEDDING_REGISTRY";
        try {
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<categoryId>", categoryId);
            if (mode != null && mode.contains("registry")) {
                value = baseCanvasPath + contextUrl + assortment;
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

    public static List<Integer> getCategoryIds(String mode, String category) {
        JSONObject response = getChildCategoriesFromGN(mode, category);
        List<Integer> categoryIds = new ArrayList<>();
        JSONObject categorydetails = (JSONObject) ((JSONArray) response.get("categoryDetails")).get(0);
        JSONArray childCategories = (JSONArray) ((JSONObject) categorydetails.get("summary")).get("child");
        for (int i = 0;i<childCategories.length();i++){
            JSONObject json = (JSONObject) childCategories.get(i);
            int id = Integer.parseInt(json.get("id").toString());
            categoryIds.add(id);
        }
        return categoryIds;
    }
}
