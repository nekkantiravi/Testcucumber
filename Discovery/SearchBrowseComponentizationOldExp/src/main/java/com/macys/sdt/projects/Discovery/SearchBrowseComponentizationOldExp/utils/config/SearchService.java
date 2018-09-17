package com.macys.sdt.projects.Discovery.SearchBrowseComponentizationOldExp.utils.config;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.jayway.restassured.response.Response;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;


public class SearchService {

    private static String getMSPHost() {
        String platform_vip = EnvironmentDetails.otherApp("f5_vip").ipAddress;
        String SDPPort = "85";
        URIBuilder builderVal = getURI(platform_vip, SDPPort);
        return builderVal.toString();
    }

    private static URIBuilder getURI(String localhost, String wireMockPort) {
        URIBuilder builder = new URIBuilder();
        String httpProtocol = "http";
        builder.setScheme(httpProtocol);
        builder.setHost(localhost);
        builder.setPort(Integer.parseInt(wireMockPort));
        System.out.println("SDP host" + builder);
        return builder;
    }

    /*
        Return top level response for size facet
     */
    public static Map getTopLevelResponse(String keyword, String shoppingMode, String regionCode) {
        Map serviceResponse = getSearchService(keyword, shoppingMode, regionCode, true, null);
        Map searchResponse = (Map) serviceResponse.get("SearchResult");
        List<Map> facets = (List<Map>) searchResponse.get("facets");
        Map topLevelFacet = facets.stream().filter(e -> e.get("name").toString().equals("SIZE_TYPE")).findFirst().orElse(null);
        Assert.assertTrue("Size related json is not displayed in response: ",
                topLevelFacet != null);
        return topLevelFacet;
    }

    /*
        Return list of first level response for size facet
     */
    public static List<Map> getFirstLevelResponse(String keyword, String shoppingMode, String regionCode) {
        Map topLevelResponse = getTopLevelResponse(keyword, shoppingMode, regionCode);
        return (List<Map>) topLevelResponse.get("facets");
    }

    /*
        Return list of second level response for size facet
     */
    public static List<Map> getSecondLevelResponse(String keyword, String shoppingMode, String regionCode) {
        List<ArrayList> secondLevelArrayList = new ArrayList<>();
        List<Map> firstLevelResponse = getFirstLevelResponse(keyword, shoppingMode, regionCode);
        firstLevelResponse.stream().filter(res -> res.keySet().size() > 6).forEachOrdered(res ->
                secondLevelArrayList.add((ArrayList) res.get("facets")));
        List<Map> secondLevelResponse = new ArrayList<>();
        for (ArrayList list : secondLevelArrayList) {
            for (Object aList : list) {
                Map response = (Map) aList;
                secondLevelResponse.add(response);
            }
        }
        return secondLevelResponse;
    }

    /*
        Returns the product count from service for the given facet name and facet values
        @param[String keyword] keyword used for search
        @param[String facetName] facetName for which the product count should retrieve facet name should be li id (ex: COLOR_NORMAL)
        @param[String facetValue] facetValue for which the product count should retrieve
        @param[String shoppingMode] current shopping mode like SITE or Wedding registry
        @param[String regionCode] region code like US or CA etc..

        Example1 int productCount = SearchService.getProductCountForFacet("jeans", "COLOR_NORMAL", "Blue", "SITE", "US");
                    productCount => 1646

        Example2 int productCount = SearchService.getProductCountForFacet("jeans", "CUSTRATINGS", "5 stars", "SITE", "US")
                    productCount => 456
     */
    public static int getProductCountForFacet(String keyword, String facetName, String facetValue, String shoppingMode, String regionCode) {
        int productCount = 0;
        try {
            Map serviceResponse = getSearchService(keyword, shoppingMode, regionCode, false, null);
            Map searchResponse = (Map) serviceResponse.get("SearchResult");
            List<Map> facets = (List<Map>) searchResponse.get("facets");
            Map facetContainer = facets.stream().filter(e -> e.get("name").toString().equals(facetName)).findFirst().orElse(null);
            Assert.assertTrue("Given facet Name " + facetName + " is not displayed in response: ",
                    facetContainer != null);
            List<Map> valuesMap = (List<Map>) facetContainer.get("values");
            List<LinkedTreeMap> facetValuesMap = valuesMap.stream().map(e -> (LinkedTreeMap) e.get("value")).collect(Collectors.toList());
            LinkedTreeMap facetValueMap = facetValuesMap.stream().filter(e -> e.get("value").toString().equalsIgnoreCase(facetValue)).findFirst().orElse(null);
            Assert.assertTrue("Given facet value " + facetValue + " is not displayed in response: ",
                    facetValueMap != null);
            productCount = Integer.parseInt(facetValueMap.get("productCount").toString().split("\\.")[0]);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return productCount;
    }

    /*
        Returns search service response for the given keyword shopping mode, region code

        @param[String keyword] keyword used for search
        @param[String shoppingMode] current shopping mode like SITE or Wedding registry
        @param[String regionCode] region code like US or CA etc..
        @param[boolean facetGroupsSupported] for getting response with facet values grouped, mainly used for Size facet

     */
    public static Map getSearchService(String keyword, String shoppingMode, String regionCode, boolean facetGroupsSupported, String sortBy) {
        Map serviceResponse = new HashMap<>();
        String value;
        String sortByValue = (sortBy == null) ? "DEFAULT" : sortBy;
        String baseCanvasPath = "/api/kws/v4/search?searchPhrase=<KEYWORD>&_offset=1&_limit=60&_sortBy=<sortByValue>";
        String contextUrl = "&_shoppingMode=<shoppingMode>&_regionCode=<regionCode>&_application=SITE&_customerExperiment=NO_EXPERIMENT";
        contextUrl = facetGroupsSupported ? contextUrl + "&facetGroupsSupported=true" : contextUrl;
        try {
            baseCanvasPath = baseCanvasPath.replace("<KEYWORD>", keyword.toLowerCase()).replace("<sortByValue>", sortByValue);
            contextUrl = contextUrl.replace("<shoppingMode>", shoppingMode).replace("<regionCode>", regionCode);
            value = getMSPHost() + baseCanvasPath + contextUrl;
            String responseString = Utils.httpGet(value, null);
            serviceResponse = new Gson().fromJson(responseString, Map.class);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return serviceResponse;
    }

    public static int getTotalProductCountFromService(Response serviceResponse) {
        int totalProductCount = 0;

        try {
            totalProductCount = serviceResponse.getBody().path("SearchResult.totalProductCount");
        } catch (Exception e) {
            Assert.fail("Error fetching Total Product Count from service \r\n" + e.getMessage());
        }
        return totalProductCount;
    }

    public static List<String> getFacetNameFromService(Map serviceResponse) {
        List<String> facets = new ArrayList<>();
        try {
            Map searchResponse = (Map) serviceResponse.get("SearchResult");
            List<Map<String, String>> facetResponse = (List<Map<String, String>>) searchResponse.get("facets");
            facets = facetResponse.stream().map(e -> e.get("displayName")).collect(Collectors.toList());
        } catch (Exception e) {
            Assert.fail("Error fetching Facets List from service \r\n" + e.getMessage());
        }
        return facets;
    }

    static HashMap<String, Boolean> getFacetNameAndCollapsedStatusFromService(Map serviceResponse) {
        HashMap<String, Boolean> facetStatus = new HashMap<>();
        List<String> facetName;
        List<Boolean> facetState;
        try {
            Map searchResponse = (Map) serviceResponse.get("SearchResult");
            List<Map> facetResponse = (List<Map>) searchResponse.get("facets");
            facetName = facetResponse.stream().map(e -> e.get("displayName").toString()).collect(Collectors.toList());
            facetState = facetResponse.stream().map(e -> (Boolean) e.get("collapsed")).collect(Collectors.toList());

            for (int i = 0; i < facetName.size(); i++) {
                facetStatus.put(facetName.get(i), facetState.get(i));
            }
        } catch (Exception e) {
            Assert.fail("Error fetching Facets List from service \r\n" + e.getMessage());
        }
        return facetStatus;
    }

    private static List<Boolean> getCollapsedStatusFromService(Map<String, Map<String, String>> serviceResponse) {
        List<String> facets;
        List<Boolean> facetsCollapsed = new ArrayList<>();
        try {
            Map searchResponse = serviceResponse.get("SearchResult");
            List<Map<String, String>> facetResponse = (List<Map<String, String>>) searchResponse.get("facets");
            facets = facetResponse.stream().map(e -> e.get("collapsed")).collect(Collectors.toList());
            facetsCollapsed.addAll(facets.stream().map(Boolean::valueOf).collect(Collectors.toList()));
        } catch (Exception e) {
            Assert.fail("Error fetching collapsed state from service \r\n" + e.getMessage());
        }
        return facetsCollapsed;
    }

    public static List<Integer> getCanvasIDsFromService(Response serviceResponse) {
        List<Integer> canvasIds = new ArrayList<>();
        try {
            canvasIds = serviceResponse.getBody().path("SearchResult.canvasIds");
            canvasIds.toArray();
        } catch (Exception e) {
            Assert.fail("Error fetching Canvas IDs from service \r\n" + e.getMessage());
        }
        return canvasIds;
    }

    public static List<Integer> getProductIDsFromService(Response serviceResponse) {
        List<Integer> productIds = new ArrayList<>();
        try {
            productIds = serviceResponse.getBody().path("SearchResult.searchResultGroups.productIds");
        } catch (Exception e) {
            Assert.fail("Error fetching Product IDs from service \r\n" + e.getMessage());
        }
        return productIds;
    }

    public static HashMap<String, Boolean> getCollapsedStatusOfFacetsFromService(Map<String, Map<String, String>> serviceResponse) {
        List<String> facets;
        List<Boolean> status;
        HashMap<String, Boolean> collapsedStatus = new HashMap<>();
        try {
            facets = getFacetNameFromService(serviceResponse);
            status = getCollapsedStatusFromService(serviceResponse);
            for (int i = 0; i < facets.size(); i++) {
                collapsedStatus.put(facets.get(i), status.get(i));
            }
        } catch (Exception e) {
            Assert.fail("Error Fetching Collapsed status from service \r\n" + e.getMessage());
        }

        return collapsedStatus;
    }


}
