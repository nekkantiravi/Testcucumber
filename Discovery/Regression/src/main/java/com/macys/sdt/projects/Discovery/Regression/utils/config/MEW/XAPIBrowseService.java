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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XAPIBrowseService {

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

    /**
     * @param catId : Category Id
     * @param filters : Map with pathname as key and filter values as value (put null if no data)
     * @return Search Response as JSON Object
     * Example for filters: ("pathname" => "shop/womens-clothing/dresses/Special_offers/Sales%2520%2526%2520Discounts" )
     */

    private static JSONObject getSearchService(Integer catId, Map<String, String> filters) {
        JSONObject jsonResponse = null;
        String value;
        String url = WebDriverManager.getCurrentUrl();
        String hostName = url.toLowerCase().contains("zeus") ? BrowsePageServices.getZeusEnv(url) : EnvironmentDetails.getEnv(url);
        String baseCanvasPath = "http://api.<hostName>.fds.com/discover/v1/search?id=<catId>&size=small&requestFacets=true&requestProducts=true";
        String contextUrl = "&_application=MEW&_deviceType=PHONE&_navigationType=BROWSE&assortment=SITE";
        try {
            if (isOnIshipeMode()) {
                String currency = Cookies.getCookieValue("currency");
                String regionCode = Cookies.getCookieValue("shippingCountry");
                contextUrl = contextUrl + "&_regionCode=" + regionCode + "&currencyCode=" + currency;
            } else {
                contextUrl = contextUrl + "&_regionCode=US";
            }
            if (filters == null || filters.size() == 0) {
                baseCanvasPath = baseCanvasPath + "&pathname=" + WebDriverManager.getWebDriver().getCurrentUrl().split(".com")[1].split("\\?id")[0];
            } else {
                for (Map.Entry<String, String> entry : filters.entrySet()) {
                    baseCanvasPath = baseCanvasPath + "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<catId>", catId.toString());
            value = baseCanvasPath + contextUrl;
            System.out.println("Constructed xAPI browse url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value, canvasHeader).readEntity(String.class));
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }

    /**
     * @param catId : Category Id
     * @param filters : Map with pathname as key and filter values as value && regionCode as key and its value in value && currencyCode as key and its value in value (put null if no data)
     *                Example for filters: ("pathname" => "shop/womens-clothing/dresses/Special_offers/Sales%2520%2526%2520Discounts" )
     * @return This Method will return List of Product Ids
     **/

    public static List<Integer> getProductIds(Integer catId, Map<String, String> filters) {
        JSONObject response = getSearchService(catId, filters);
        List<Integer> producIds = new ArrayList<>();
        JSONArray simpleCanvas = response.getJSONArray("simpleCanvas");
        JSONObject sortableGrid= new JSONObject();
        for(int i=0;i<simpleCanvas.length();i++ ) {
            JSONObject object = simpleCanvas.getJSONObject(i);
            if(object.has("sortableGrid")) {
                sortableGrid = object;
                break;
            }
        }
        JSONArray products =  sortableGrid.getJSONObject("sortableGrid").getJSONArray("collection");
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i).getJSONObject("product");
            producIds.add((Integer) product.get("id"));
        }
        return producIds;
    }

    public static int getItemCount(Integer catId, Map<String, String> filters) {
        JSONObject response = getSearchService(catId, filters);
        String count = getSortableGrid(response.getJSONArray("simpleCanvas")).split("model")[1].split("meta")[1].split("itemCount=")[1].split("},")[0];
        return Integer.parseInt(count);
    }

    public static Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount(Integer catId, Map<String, String> filters) {
        Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount = new HashMap<>();
        JSONObject response = getSearchService(catId, filters);
        JSONArray facets = response.getJSONObject("facets").getJSONArray("facets");
        for (int i = 0; i < facets.length(); i++) {
            List<Map<String, Integer>> facetValuesWithProdCount = new ArrayList<>();
            JSONArray values = facets.getJSONObject(i).has("values") ? facets.getJSONObject(i).getJSONArray("values") : null;
            if (values != null) {
                for (int j = 0; j < values.length(); j++) {
                    Map<String, Integer> facetValeswithCount = new HashMap<>();
                    facetValeswithCount.put(values.getJSONObject(j).getString("displayName"), values.getJSONObject(j).getInt("count"));
                    facetValuesWithProdCount.add(facetValeswithCount);
                }
            }
            String facetName = facets.getJSONObject(i).getString("displayName");
            if (!facetName.equalsIgnoreCase("Free Pick Up In Store") && !facetName.equalsIgnoreCase("Size"))
                getAllFacetValuesWithProdCount.put(facetName, facetValuesWithProdCount);
            Collections.sort(facetValuesWithProdCount, XAPISearchService.mapComparator);
        }
        return getAllFacetValuesWithProdCount;
    }

    private static String getSortableGrid(JSONArray jsonArray) {
        Map<String, String> map = new HashMap<>();
        for (Object JsonArray : jsonArray.toList()) {
            map = (Map<String, String>) JsonArray;
            if(map.get("sortableGrid")!= null)
                break;
        }
        return map.entrySet().toString();
    }

}
