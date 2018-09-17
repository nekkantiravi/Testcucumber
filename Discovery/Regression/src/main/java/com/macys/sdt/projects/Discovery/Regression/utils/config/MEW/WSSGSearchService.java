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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.macys;

public class WSSGSearchService {

    private static Map<String, String> canvasHeader = new HashMap<String, String>() {{
        put("x-macys-webservice-client-id", "mewqe");
        put("Accept", "application/json");

    }};

    private static JSONObject getSearchService(String keyword, String mode, String sortBy, String page, Map<String, List<String>> filters) {
        JSONObject jsonResponse = null;
        String value;
        String sortParam = (sortBy == null) ? "DEFAULT" : sortBy;
        String baseCanvasPath;
        switch (sortParam) {
            case "Featured Items":
            case "Featured":
                sortParam = "DEFAULT";
                break;
            case "Price: High to Low":
                sortParam = "PRICE_DESCENDING";
                break;
            case "Price: Low to High":
                sortParam = "PRICE_ASCENDING";
                break;
            case "Customer's Top Rated":
                sortParam = "CUSTOMER_RATING";
                break;
            case "Best Sellers":
                sortParam = "BEST_SELLERS";
                break;
            case "New Arrivals":
            case "Newest":
                sortParam = "NEWNESS";
                break;
            default:
                sortParam = "DEFAULT";
                break;
        }
        String resultsPerPage = macys() ? "48" : "90";
        String pageNumber = (page == null) ? "1" : page;
        String pageURL = WebDriverManager.getCurrentUrl();
        String hostName = pageURL.toLowerCase().contains("zeus") ? BrowsePageServices.getZeusEnv(pageURL) : EnvironmentDetails.getEnv(pageURL);
        if(pageURL.contains("/buy/")){
            baseCanvasPath = "http://api.<hostName>.fds.com/v4/catalog/search?BRAND=<keyword>&searchphrase=<keyword>&show=product,facet&perpage=<resultsPerPage>&page=<pageNumber>&facetexpandall=true";
        }else {
            baseCanvasPath = "http://api.<hostName>.fds.com/v4/catalog/search?searchphrase=<keyword>&show=product,facet&perpage=<resultsPerPage>&page=<pageNumber>&facetexpandall=true";
        }
        String contextUrl = "&sortorderby=<sortByValue>&facetGroupsSupported=true&appl=MEW&device=PHONE";
        try {
            if (mode.equalsIgnoreCase("iship")) {
                contextUrl = contextUrl + "&assortment=SITE" + "&country=" + Cookies.getCookieValue("shippingCountry") + "&currencyCode=" + Cookies.getCookieValue("currency");;
            }else if (mode.equalsIgnoreCase("registry")) {
                contextUrl = contextUrl + "&assortment=WEDDING_REGISTRY&country=US";
            } else {
                contextUrl = contextUrl + "&assortment=SITE&country=US";
            }
            if (filters != null && filters.size() != 0) {
                for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
                    ArrayList filterValues = new ArrayList();
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        filterValues.add(URLEncoder.encode(entry.getValue().get(i), "UTF-8").replaceAll("\\+", "%20"));
                    }
                    baseCanvasPath = baseCanvasPath + "&" + entry.getKey() + "=" + filterValues.stream().collect(Collectors.joining(",")).toString();
                }
            }
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<keyword>", URLEncoder.encode(keyword, "UTF-8").replaceAll("\\+", "%20")).replace("<resultsPerPage>", resultsPerPage).replace("<pageNumber>", pageNumber);
            contextUrl = contextUrl.replace("<sortByValue>", sortParam);
            value = baseCanvasPath + contextUrl;
            System.out.println("Constructed WSSG search url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value, canvasHeader).readEntity(String.class));
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }

    public static List<Integer> getProductIds(String keyword, String mode, String sortBy, String page, Map<String, List<String>> filters) {
        JSONObject response = getSearchService(keyword, mode, sortBy, page, filters);
        List<Integer> producIds = new ArrayList<>();
        JSONObject searchresultgroups = (JSONObject) ((JSONArray) response.get("searchresultgroups")).get(0);
        JSONArray products = (JSONArray) ((JSONObject) searchresultgroups.get("products")).get("product");
        for (int i = 0; i < products.length(); i++) {
            producIds.add((Integer) ((JSONObject) products.get(i)).get("id"));
        }
        return producIds;
    }

    public static Integer getTotalProductCountFromService(String keyword, String mode, String sortBy, String page, Map<String, List<String>> filters) {
        JSONObject response = getSearchService(keyword, mode, sortBy, page, filters);
        JSONObject currentCategory = (JSONObject) ((JSONArray) response.get("searchresultgroups")).get(0);
        return (Integer) currentCategory.get("totalproducts");
    }

    public static Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount(String keyword, String mode, String sortBy, String page, Map<String, List<String>> filters) {
        Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount = new HashMap<>();
        JSONObject response = getSearchService(keyword, mode, sortBy, page, filters);
        JSONArray facets = response.getJSONArray("facets");
        for (int i = 0; i < facets.length(); i++) {
            List<Map<String, Integer>> facetValuesWithProdCount = new ArrayList<>();
            JSONArray values = facets.getJSONObject(i).has("facetvalues") ? facets.getJSONObject(i).getJSONArray("facetvalues") : null;
            if (values != null) {
                for (int j = 0; j < values.length(); j++) {
                    Map<String, Integer> facetValeswithCount = new HashMap<>();
                    String facetValueName = values.getJSONObject(j).has("value") ? values.getJSONObject(j).getString("value") : values.getJSONObject(j).getString("valuedisplayname");
                    facetValeswithCount.put(facetValueName, values.getJSONObject(j).getInt("productcount"));
                    facetValuesWithProdCount.add(facetValeswithCount);
                }
            }
            String facetName = facets.getJSONObject(i).getString("displayname");
            if (!facetName.equalsIgnoreCase("Free Pick Up In Store") && !facetName.equalsIgnoreCase("Size"))
                getAllFacetValuesWithProdCount.put(facetName, facetValuesWithProdCount);
            Collections.sort(facetValuesWithProdCount, XAPISearchService.mapComparator);
        }
        return getAllFacetValuesWithProdCount;
    }

    public static int getItemCount(String keyword, String mode, String sortBy, String page, Map<String, List<String>> filters) {
        JSONObject response = getSearchService(keyword, mode, sortBy, page, filters);
        return (int) response.getJSONArray("searchresultgroups").getJSONObject(0).get("totalproducts");
    }

    public static String getProductCountWithPartialMatch(String keyword, String mode) {
        JSONObject response = getSearchService(keyword, mode,null,null,null);
        String matchesTerm = (String) response.getJSONArray("partialMatches").get(0);
        return matchesTerm;
    }

    public static Map<String, String> getPartialMatchKeywords(String keyword, String mode) {
        Map<String, String> productCountWithPartiaMatch = new HashMap<>();
        JSONObject response = getSearchService(keyword, mode,null,null,null);
        ArrayList matchesTerm = (ArrayList) response.getJSONArray("partialMatches").toList();
        productCountWithPartiaMatch.put("matchedTerms", matchesTerm.stream().collect(Collectors.joining(", ")).toString());
        return productCountWithPartiaMatch;
    }

}