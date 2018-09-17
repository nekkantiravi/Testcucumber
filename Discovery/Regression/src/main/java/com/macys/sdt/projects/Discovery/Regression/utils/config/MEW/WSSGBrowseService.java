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

public class WSSGBrowseService {

    private static Map<String, String> canvasHeader = new HashMap<String, String>(){{
        put("x-macys-webservice-client-id","mewqe");
        put("Accept","application/json");

    }};

    private static JSONObject getBrowseService(int catId, String mode, String sortBy, String page, Map<String,List<String>> filters) {
        JSONObject jsonResponse = null;
        String value;
        String sortParam = (sortBy == null) ? "" : sortBy;
        switch (sortParam) {
            case "Featured Items":
            case "Featured":
                sortParam = "";
                break;
            case "Price: High to Low":
                sortParam = "&sortby=price&sortorder=desc";
                break;
            case "Price: Low to High":
                sortParam = "&sortby=price&sortorder=asc";
                break;
            case "Customer's Top Rated":
                sortParam = "&sortby=customerrating&sortorder=desc";
                break;
            case "Best Sellers":
                sortParam = "&sortby=bestseller";
                break;
            case "New Arrivals":
            case "Newest":
                sortParam = "&sortby=newarrival";
                break;
            default:
                sortParam = "";
                break;
        }
        String resultsPerPage = macys() ? "48" : "90";
        String pageNumber = (page == null) ? "1" : page;
        String url = WebDriverManager.getCurrentUrl();
        String hostName = url.toLowerCase().contains("zeus") ? BrowsePageServices.getZeusEnv(url) : EnvironmentDetails.getEnv(url);
        String baseCanvasPath = "http://api.<hostName>.fds.com/v3/catalog/category/<catId>/browseproducts?";
        String contextUrl = "&currentpage=<pageNumber>&facetGroupsSupported=true&redirect=true&resultsperpage=<resultsPerPage>&show=facet,featuredfacet,product,productpool&includeFinalPrice=true<sortByValue>&application=MEW&device=PHONE";
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
                    for(int i =0; i< entry.getValue().size();i++) {
                        filterValues.add(URLEncoder.encode(entry.getValue().get(i), "UTF-8").replaceAll("\\+", "%20"));
                    }
                    baseCanvasPath = baseCanvasPath + "&" + entry.getKey() + "=" + filterValues.stream().collect(Collectors.joining(",")).toString();
                }
            }
            baseCanvasPath = baseCanvasPath.replace("<hostName>", hostName).replace("<catId>", String.valueOf(catId));
            contextUrl = contextUrl.replace("<pageNumber>", pageNumber).replace("<resultsPerPage>", resultsPerPage).replace("<sortByValue>", sortParam);
            value = baseCanvasPath + contextUrl;
            System.out.println("Constructed WSSG browse url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value, canvasHeader).readEntity(String.class));
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }

    public static List<Integer> getProductIds(int catId, String mode, String sortBy, String page, Map<String,List<String>> filters) {
        JSONObject response = getBrowseService(catId, mode, sortBy, page,filters);
        List<Integer> producIds = new ArrayList<>();
        JSONObject searchresultgroups = (JSONObject) ((JSONArray) response.get("category")).get(0);
        JSONArray products = (JSONArray) ((JSONObject) searchresultgroups.get("product")).get("product");
        for (int i = 0; i < products.length(); i++) {
            producIds.add((Integer) ((JSONObject) products.get(i)).get("id"));
        }
        return producIds;
    }

    public static int getItemCount(int catId, String mode, String sortBy, String page, Map<String,List<String>> filters) {
        JSONObject response = getBrowseService(catId, mode, sortBy, page,filters);
        return (int) response.getJSONArray("category").getJSONObject(0).get("totalproducts");
    }

    public static Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount(int catId, String mode, String sortBy, String page, Map<String,List<String>> filters) {
        Map<String, List<Map<String, Integer>>> getAllFacetValuesWithProdCount = new HashMap<>();
        JSONObject response = getBrowseService(catId, mode, sortBy, page,filters);
        JSONArray facets = response.getJSONArray("category").getJSONObject(0).getJSONArray("facet");
        for (int i = 0; i < facets.length(); i++) {
            List<Map<String, Integer>> facetValuesWithProdCount = new ArrayList<>();
            JSONArray values = facets.getJSONObject(i).has("value") ? facets.getJSONObject(i).getJSONArray("value") : null;
            if (values != null) {
                for (int j = 0; j < values.length(); j++) {
                    Map<String, Integer> facetValeswithCount = new HashMap<>();
                    String facetName = values.getJSONObject(j).has("values") ? values.getJSONObject(j).getString("values") : values.getJSONObject(j).getString("name");
                    facetValeswithCount.put(facetName, values.getJSONObject(j).getInt("productcount"));
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
}