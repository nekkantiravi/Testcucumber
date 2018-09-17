package com.macys.sdt.projects.Discovery.Regression.utils.config.MEW;

import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.utils.BrowsePageServices;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XAPISearchService {

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
     * @param keyword : Search Keyword
     * @param filters : Map with pathname as key and filter values as value (put null if no data)
     * @return Search Response as JSON Object
     * Example for filters: ("pathname" => "/shop/search/Special_offers/Clearance%252FCloseout|Last%2520Act" )
     */

    private static JSONObject getSearchService(String keyword, Map<String, String> filters) {
        JSONObject jsonResponse = null;
        String value;
        String url = WebDriverManager.getCurrentUrl();
        String hostName = url.toLowerCase().contains("zeus") ? BrowsePageServices.getZeusEnv(url) : EnvironmentDetails.getEnv(url);
        String baseCanvasPath = "http://api.<hostName>.fds.com/discover/v1/search?keyword=<keyword>&size=small&requestFacets=true&requestProducts=true";
        String contextUrl = "&_application=MEW&_deviceType=PHONE&_navigationType=SEARCH&assortment=SITE";
        try {
            if (isOnIshipeMode()) {
                String currency = Cookies.getCookieValue("currency");
                String regionCode = Cookies.getCookieValue("shippingCountry");
                contextUrl = contextUrl + "&_regionCode=" + regionCode + "&currencyCode=" + currency;
            } else {
                contextUrl = contextUrl + "&_regionCode=US";
            }
            if (filters == null || filters.size() == 0) {
                baseCanvasPath = baseCanvasPath + "&pathname=/shop/search";
            } else {
                for (Map.Entry<String, String> entry : filters.entrySet()) {
                    baseCanvasPath = baseCanvasPath + "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<keyword>", URLEncoder.encode(keyword, "UTF-8").replaceAll("\\+", "%20"));
            value = baseCanvasPath + contextUrl;
            System.out.println("Constructed xAPI url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value, canvasHeader).readEntity(String.class));
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }

    /**
     * @param keyword : Search Keyword
     * @param filters : Map with pathname as key and filter values as value && regionCode as key and its value in value && currencyCode as key and its value in value (put null if no data)
     *                Example for filters: ("pathname" => "/shop/search/Special_offers/Clearance%252FCloseout|Last%2520Act" )
     * @return This Method will return List of Product Ids
     **/

    public static List<Integer> getProductIds(String keyword, Map<String, String> filters) {
        JSONObject response = getSearchService(keyword, filters);
        List<Integer> producIds = new ArrayList<>();
        JSONObject searchresultgroups = (JSONObject) ((JSONArray) response.get("simpleCanvas")).get(0);
        JSONArray products = (JSONArray) ((JSONObject) searchresultgroups.get("sortableGrid")).get("collection");
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = (JSONObject) ((JSONObject) products.get(i)).get("product");
            producIds.add((Integer) product.get("id"));
        }
        return producIds;
    }

    public static int getItemCount(String keyword, Map<String, String> filters) {
        JSONObject response = getSearchService(keyword, filters);
        JSONObject searchResultGroups = response.getJSONArray("simpleCanvas").getJSONObject(0);
        JSONObject products = searchResultGroups.getJSONObject("sortableGrid").getJSONObject("model").getJSONObject("meta");
        return (int) products.get("itemCount");
    }

    public static Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount(String keyword, Map<String, String> filters) {
        Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount = new HashMap<>();
        JSONObject response = getSearchService(keyword, filters);
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
            Collections.sort(facetValuesWithProdCount, mapComparator);
        }
        return getAllFacetValuesWithProdCount;
    }

    public static Map<String, String> getProductCountWithPartialMatch(String keyword) {
        Map<String, String> productCountWithPartiaMatch = new HashMap<>();
        JSONObject response = getSearchService(keyword, null);
        JSONArray simpleCanvas = (JSONArray) response.get("simpleCanvas");
        JSONObject sortableGrid = (JSONObject) simpleCanvas.get(simpleCanvas.length() -1);
        JSONObject products = (JSONObject) ((JSONObject) ((JSONObject) sortableGrid.get("sortableGrid")).get("model")).get("meta");
        ArrayList matchesTerm = new ArrayList();
        JSONArray labels = (JSONArray) products.get("matchedTerms");
        for(int i=0;i<labels.length();i++){
            JSONObject label = (JSONObject) labels.get(i);
            matchesTerm.add(label.get("label").toString());
        }
        productCountWithPartiaMatch.put("itemCount", products.get("itemCount").toString());
        productCountWithPartiaMatch.put("matchedTerms", matchesTerm.stream().collect(Collectors.joining(", ")).toString());
        return productCountWithPartiaMatch;
    }

    public static Comparator<Map<String, Integer>> mapComparator = new Comparator<Map<String, Integer>>() {
        @Override
        public int compare(Map<String, Integer> map1, Map<String, Integer> map2) {
            String val1 = "";
            String val2 = "";
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                val1 = entry.getKey();
            }
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                val2 = entry.getKey();
            }
            return val1.compareTo(val2);
        }

    };
}
