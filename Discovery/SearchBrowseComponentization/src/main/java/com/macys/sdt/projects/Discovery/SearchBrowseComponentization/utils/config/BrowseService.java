package com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BrowseService {

    private static String getMSPHost(String hostName) {
        String platform_vip = (hostName == null ? EnvironmentDetails.otherApp("hostName").ipAddress : EnvironmentDetails.otherApp(hostName).ipAddress);
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
    public static int getProductCountForFacet(String categoryID, String facetName, String facetValue, String shoppingMode, String regionCode) {
        int productCount = 0;
//        try {
            Map serviceResponse = getBrowseService(categoryID, shoppingMode, regionCode, false, null);
            Map browseResponse = (Map) serviceResponse.get("products");
            List<Map> facets = (List<Map>) browseResponse.get("facets");
            Map facetContainer = facets.stream().filter(e -> e.get("displayName").toString().equals(facetName) || e.get("name").toString().equals(facetName)).findFirst().orElse(null);
            Assert.assertTrue("Given facet Name " + facetName + " is not displayed in response: ",
                    facetContainer != null);
            List<Map> valuesMap = (List<Map>) facetContainer.get("values");
//            List<LinkedTreeMap> facetValuesMap = valuesMap.stream().map(e -> (LinkedTreeMap) e.get("value")).collect(Collectors.toList());
            Map facetValueMap = valuesMap.stream().filter(e -> e.get("value").toString().replaceAll(",", "").equalsIgnoreCase(facetValue)).findFirst().orElse(null);
            Assert.assertTrue("Given facet value " + facetValue + " is not displayed in response: ",
                    facetValueMap != null);
            productCount = Integer.parseInt(facetValueMap.get("productCount").toString().split("\\.")[0]);
//        } catch (Exception e) {
//            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
//        }
        return productCount;
    }

    /*
        Returns search service response for the given keyword shopping mode, region code

        @param[String keyword] keyword used for search
        @param[String shoppingMode] current shopping mode like SITE or Wedding registry
        @param[String regionCode] region code like US or CA etc..
        @param[boolean facetGroupsSupported] for getting response with facet values grouped, mainly used for Size facet

     */
    public static Map getBrowseService(String categoryID, String shoppingMode, String regionCode, boolean facetGroupsSupported, String sortBy,String hostName) {
        Map serviceResponse = new HashMap<>();
        String value;
        String sortByValue = (sortBy == null) ? "ORIGNAL" : sortBy;
        String baseCanvasPath = "/api/catalog/v2/categories/<CATID>/products?_limit=60&returnNavigationProductPool=true&_sortBy=<sortByValue>&_fields=categoryId,navigationPoolProducts,totalProducts,facets,canvasIds&_offset=1&sdpGrid=cellA&returnFacets=true";
        String contextUrl = "&_shoppingMode=<shoppingMode>&_regionCode=<regionCode>&_application=SITE&_customerExperiment=NO_EXPERIMENT";
        contextUrl = facetGroupsSupported ? contextUrl + "&facetGroupsSupported=true" : contextUrl;
        try {
            baseCanvasPath = baseCanvasPath.replace("<CATID>", categoryID.toLowerCase()).replace("<sortByValue>", sortByValue);
            contextUrl = contextUrl.replace("<shoppingMode>", shoppingMode).replace("<regionCode>", regionCode);
            value = getMSPHost(hostName) + baseCanvasPath + contextUrl;
            System.out.println("Constructed MSP url: ------- " + value);
            String responseString = Utils.httpGet(value, null);
            serviceResponse = new Gson().fromJson(responseString, Map.class);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return serviceResponse;
    }
    public static Map getBrowseService(String categoryID, String shoppingMode, String regionCode, boolean facetGroupsSupported, String sortBy) {
        return getBrowseService(categoryID, shoppingMode, regionCode, facetGroupsSupported, sortBy, "f5_vip");
    }

    /*
        Return top level response for size facet
     */
    public static Map getTopLevelResponse(String categoryID, String shoppingMode, String regionCode) {
        Map serviceResponse = getBrowseService(categoryID, shoppingMode, regionCode, true, null);
        Map searchResponse = (Map) serviceResponse.get("products");
        List<Map> facets = (List<Map>) searchResponse.get("facets");
        Map topLevelFacet = facets.stream().filter(e -> e.get("name").toString().equals("SIZE_TYPE")).findFirst().orElse(null);
        Assert.assertTrue("Size related json is not displayed in response: ",
                topLevelFacet != null);
        return topLevelFacet;
    }

    /*
        Return list of first level response for size facet
     */
    public static List<Map> getFirstLevelResponse(String categoryID, String shoppingMode, String regionCode) {
        Map topLevelResponse = getTopLevelResponse(categoryID, shoppingMode, regionCode);
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

    public static JSONObject getCategoryStellaAttribute(String catId, String attribute) {
        Map<String, String> canvasHeader = new HashMap<>();
        canvasHeader.put("Accept", "application/json");
        catId = (catId == null ? WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0] : catId);
        String url = "http://" + EnvironmentDetails.otherApp("f5_vip").ipAddress + ":85/api/catalog/v2/categories/" + catId +
                "?_fields=id,name,parentCategoryId,externalHostUrl,attributes,leaf,canvasIds&_expand=id,live,countryEligible,subCategories(name,leaf).depth%3D2,parentCategory(live).depth%3D2147483647&sdpGrid=primary";
        JSONObject jsonResponse = new JSONObject(RESTOperations.doGET(url, canvasHeader).readEntity(String.class));
        JSONArray attributes = jsonResponse.getJSONObject("category").getJSONArray("attributes");
        for (int i = 0; i < attributes.length(); i++) {
            JSONObject js = attributes.getJSONObject(i);
            if (js.get("name").equals(attribute)) {
                return js;
            }
        }
        return null;
    }

    /*
        Returns the category service response for SLP pages
        @param[String categoryID] keyword used for categoryID
     */
    public static Map getBrowseServiceCanvas(String categoryID,String hostName) {
        Map serviceResponse = new HashMap<>();
        String value;
        String baseCanvasPath = "/api/catalog/v2/categories/<CATID>?_fields=id,name,parentCategoryId,externalHostUrl,attributes,leaf,canvasIds&_expand=id,live,countryEligible,subCategories(name,leaf).depth=2,parentCategory(live).depth=2147483647&sdpGrid=primary&_deviceType=DESKTOP&_shoppingMode=SITE&_regionCode=US&_application=SITE&_navigationType=LANDING";
        try {
            baseCanvasPath = baseCanvasPath.replace("<CATID>", categoryID.toLowerCase());
            value = getMSPHost(hostName) + baseCanvasPath;
            System.out.println("Constructed MSP url: ------- " + value);
            String responseString = Utils.httpGet(value, null);
            serviceResponse = new Gson().fromJson(responseString, Map.class);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return serviceResponse;
    }

    /*
        Returns the canvas service response for SLP pages
        @param[String canvasId] keyword used for canvasId
        @param[String catId] keyword used for categoryId
    */
    public static JSONObject getMediaDetailsCanvas(String canvasId,String catId,String hostName) {
        HashMap<String, String> header = new HashMap<>();
        header.put("X-Macys-ClientId", "navapp");
        String value;
        JSONObject jsonResponse=null;
        String baseMediaPath = "/api/content/v2/canvas/<canvasId>?catId=<catId>&_deviceType=DESKTOP&_application=SITE&_regionCode=US&_navigationType=LANDING&sdpGrid=primary&_shoppingMode=SITE&_expand=media";
        try {
            baseMediaPath = baseMediaPath.replace("<catId>", catId.toLowerCase());
            baseMediaPath = baseMediaPath.replace("<canvasId>", canvasId.toLowerCase());
            value = getMSPHost(hostName) + baseMediaPath;
            System.out.println("Constructed MSP url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value, header).readEntity(String.class));
            System.out.println("*****"+jsonResponse);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }

    /*
        Returns the canvas service response for BrandIndex pages for MCOM
        @param[String catId] keyword used for categoryId
        @param[String hostName] keyword used for host portIp
        @param[String shoppingMode] keyword used for shopping mode
        @param[String regionCode] keyword used for region code
        @param[String applicationType] keyword used for application type
    */
    public static JSONObject getBrandIndexMediaDetailsCanvas(String catId,String hostName,String shoppingMode,String regionCode,String applicationType) {
        JSONObject jsonResponse=null;
        String value;
        String baseCanvasPath = "/xapi/discover/v1/page?pathname=/shop/all-brands&id=<catId>&_application=<applicationType>&_navigationType=BROWSE&_deviceType=PC&_shoppingMode=<shoppingMode>&_regionCode=<regionCode>&_customerState=GUEST";
        try {
            baseCanvasPath = baseCanvasPath.replace("<catId>", catId.toLowerCase()).replace("<shoppingMode>",shoppingMode).replace("<regionCode>",regionCode).replace("<applicationType>",applicationType);
            value = getMSPHost(hostName) + baseCanvasPath;
            System.out.println("Constructed MSP url: ------- " + value);
            jsonResponse = new JSONObject(RESTOperations.doGET(value).readEntity(String.class));
            System.out.println("*****"+jsonResponse);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return jsonResponse;
    }
}
