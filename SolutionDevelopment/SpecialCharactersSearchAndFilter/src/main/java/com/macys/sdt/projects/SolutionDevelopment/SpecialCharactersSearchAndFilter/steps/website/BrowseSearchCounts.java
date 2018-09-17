package com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.steps.website;


import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.utils.Email;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.prodEnv;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by u063689 on 1/18/2018.
 */
public class BrowseSearchCounts {

    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private String searchTerm;
    private String categoryID;
    private JSONObject jsonObjectMyVar;
    private String msp_url;
    private String FCCconnection;
    private String MSPconnection;


    private static String getFCCconnection() throws Throwable {
        String FCCserver;
        log.info("http://" + EnvironmentDetails
                .otherApp("FCC").ipAddress + ":8080");
        try {
            FCCserver = "http://" + EnvironmentDetails
                    .otherApp("FCC").ipAddress + ":8080";
        } catch (Exception e) {
            FCCserver = null;
            log.warning("Unable to connect to FCC server!");
            e.printStackTrace();
        }
        return FCCserver;
    }

    private static String getMSPConnection() throws Throwable {
        String MSPserver;
        try {
            log.info("http://" + EnvironmentDetails
                    .otherApp("MSPDiscovery").ipAddress + ":8080");
            MSPserver = "http://" + EnvironmentDetails
                    .otherApp("MSPDiscovery").ipAddress + ":8080";
        } catch (Exception e) {
            MSPserver = null;
            log.warning("Unable to connect to MSP server!");
            e.printStackTrace();
        }
        return MSPserver;
    }


    @Given("^I connect to MSP and FCC$")
    public void iConnectToMSPAndFCC() throws Throwable {
        if (prodEnv()) {
            FCCconnection = "http://macyds060:8080";
            MSPconnection = "http://ma209blvmsp520:8080";
        } else {
            FCCconnection = getFCCconnection();
            MSPconnection = getMSPConnection();
        }
    }




    @And("^I verify browse and search product count for corresponding categories$")
    public void iVerifyBrowseAndSearchProductCountForCorrespondingCategories() throws Throwable {
        //String MyVar = RunConfig.getEnvOrExParam("MyVar");
        String DEPARTMENT = RunConfig.getEnvOrExParam("DEPARTMENT");
        String FROM = RunConfig.getEnvOrExParam("FROM");
        String TO = RunConfig.getEnvOrExParam("TO");
        String messageText = "DEPARTMENT: " + DEPARTMENT;
        File Data = getResourceFile(DEPARTMENT.toLowerCase() + ".json");
        JSONObject json = new JSONObject(Utils.readTextFile(Data));

        JSONArray CATEGORIES = json.getJSONArray("data");
        //new JSONArray(MyVar);

        messageText = messageText.concat("\n\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");

        log.info("The Categories for " + DEPARTMENT +" are: " + parseCATEGORIES(CATEGORIES));
        List<JSONObject> parsedCategories = parseCATEGORIES(CATEGORIES);
        for(JSONObject jsonObject: parseCATEGORIES(CATEGORIES)){

            jsonObjectMyVar = jsonObject;
            searchTerm = getSearchTerm();
            log.info("Iterating over " + searchTerm);
            messageText = messageText.concat("\n\n SEARCH TERM: " + searchTerm);
            log.info("SEARCH TERM: " + searchTerm);
            categoryID = getCategoryId();
            log.info("CATEGORY ID: " + categoryID);
            searchTerm = encodeValue(searchTerm);
            messageText = messageText.concat("\n CATEGORY ID: " + categoryID);
            String refinementsDispl = getRefinementsDisplay();
            messageText = messageText.concat("\n Refinements Display: " + refinementsDispl);
            msp_url = returnSearchCall();

            if (getTotalProductCount(msp_url).equalsIgnoreCase("0") || getTotalProductCount(returnSearchCallWithRefinements()).equalsIgnoreCase("0")) {
                messageText = messageText.concat("\n There are 0 products returned in Search api. Please investigate");
                parsedCategories.iterator().next();
            }
            else if (getTotalProductCount(returnBrowseCall(categoryID)).equalsIgnoreCase("0")){
                messageText = messageText.concat("\n There are 0 products returned in Browse api or category: "+ categoryID+". Please investigate");
                parsedCategories.iterator().next();
            }
            else {
                log.info("Search URL with refinements " + returnSearchCallWithRefinements());
                log.info("Browse URL for category " + categoryID + ": " + returnBrowseCall(categoryID));

                List<String> searchResultIds = null;
                List<String> browseResultIds = returnListOfBrowseProductIDs(categoryID);

                log.info("Search URL with refinements " + returnSearchCallWithRefinements());
                log.info("Browse URL " + returnBrowseCall(categoryID));


                log.info("There are " + returnListOfSearchProductIDs(msp_url + "&_limit=" + getTotalProductCount(msp_url)).size()
                        + " products in search before refinements and exclusions applied");
                if (getExclusions().values().contains(null)) {
                    log.info("There are no Exclusions for the search term" + searchTerm);
                    searchResultIds = returnListOfSearchProductIDs(returnSearchCallWithRefinements());
                } else {
                    for (String value : getExclusions().values()) {
                        searchResultIds = removeExcludedProductsFromSearch(value);
                    }
                }
                log.info("There are " + searchResultIds.size() + " products in search after refinements and exclusions applied");
                log.info("There are " + browseResultIds.size() + " products in browse");
                List<String> bsDiff = inBrowseNotInSearch(browseResultIds, searchResultIds);
                List<String> sbDiff = inSearchNotInBrowse(browseResultIds, searchResultIds);
                if (bsDiff.isEmpty()){
                    messageText = messageText.concat("\n\n In Browse Not In Search: No issues were found");
                }
                else{
                    messageText = messageText.concat("\n\n In Browse Not In Search: " + inBrowseNotInSearch(browseResultIds, searchResultIds));
                }
                if (sbDiff.isEmpty()){
                    messageText = messageText.concat("\n\n In Search Not In Browse: No issues were found");
                }
                else {
                    messageText = messageText.concat("\n\n In Search Not In Browse: " + inSearchNotInBrowse(browseResultIds, searchResultIds));
                }

            }
            messageText = messageText.concat("\n\n _____________________________________________________________________________________________");
        }
        Email email = new Email();
        email.sendMail(FROM, TO, messageText, DEPARTMENT + " - product discrepancies between browse and search");
    }


    private String getTotalProductCount(String url) throws Throwable {
        String prodCount = null;
        if (url.contains("searchPhrase")) {
            if (validateResponseBody(getSearchJSON())) {
                prodCount = getSearchJSON().getJSONObject("SearchResult").get("totalProductCount").toString();
            } else {
                Assert.fail("Incorrect response returned for Search");
            }
        } else {
            if (validateResponseBody(getBrowseJSON(categoryID))) {
                prodCount = getBrowseJSON(categoryID).getJSONObject("products").get("totalProducts").toString();
            } else {
                Assert.fail("Incorrect response returned for Browse");
            }
        }
        return prodCount;
    }

    private boolean validateResponseBody(JSONObject json_object) throws Throwable {
        return (!json_object.has("error") && !json_object.has("errors"));
    }


    private String returnSearchCall() throws Throwable {
        return MSPconnection + "/api/kws/v4/search?searchPhrase=" + searchTerm;
    }



    private String returnSearchCallWithRefinements() throws Throwable {
        if (formatRefinements() == null) {
            log.info("There are no refinements specified");
            return String.format("%s%s%s", msp_url, "&_limit=", getTotalProductCount(msp_url));
        } else {
            String encodedRefinements = encodeValue(formatRefinements());
            return String.format("%s%s%s%s%s", msp_url, "&refinements=", encodedRefinements, "&_limit=", getTotalProductCount(msp_url));
        }
    }


    private String returnBrowseCall(String category) throws Throwable {
        return FCCconnection + "/api/catalog/v2/categories/" + category +
                "/products?&sdpGrid=cellA&_deviceType=DESKTOP&_shoppingMode=SITE&_regionCode=US&_application=SITE&_navigationType=BROWSE&returnNavigationProductPool=true&returnFacets=true&facetGroupsSupported=true";
    }


    private JSONObject returnSearchResultsJSONFromMSP(String refinements) throws Throwable {
        String my_url;
        if (refinements != null) {
            String encodedRefinements = encodeValue(refinements);
            my_url = String.format("%s%s%s%s%s", msp_url, "&refinements=", encodedRefinements, "&_limit=", getTotalProductCount(msp_url));
            //log.info("URL with search term and refinements: " + my_url.replace("\"", "%22"));
        } else {
            my_url = String.format("%s%s%s", msp_url, "&_limit=", getTotalProductCount(msp_url));
            //log.info("URL with search term: " + my_url.replace("\"", "%22"));
        }
        Response msp_response = RESTOperations.doGET(my_url.replace("\"", "%22"), null);
        String msp_finalJsonStr = msp_response.readEntity(String.class);
        return new JSONObject(msp_finalJsonStr);
    }


    private JSONObject getSearchJSON() throws Throwable {
        Response msp_response = RESTOperations.doGET(msp_url, null);
        String msp_finalJsonString = msp_response.readEntity(String.class);
        return new JSONObject(msp_finalJsonString);
    }


    private JSONObject getBrowseJSON(String category) throws Throwable {
        String fcc_url = FCCconnection + "/api/catalog/v2/categories/" +
                category + "/products?&sdpGrid=cellA&_deviceType=DESKTOP&_shoppingMode=SITE" +
                "&_regionCode=US&_application=SITE&_navigationType=BROWSE&returnNavigationProductPool=true&returnFacets=true&facetGroupsSupported=true";
        Response fcc_response = RESTOperations.doGET(fcc_url, null);
        String fcc_finalJsonString = fcc_response.readEntity(String.class);
        return new JSONObject(fcc_finalJsonString);
    }



    private List<String> returnListOfSearchProductIDs(String url) throws Throwable {
        if (url.contains("refinements")) {
            return convertJSONArrayToList(returnSearchResultsJSONFromMSP(formatRefinements()).getJSONObject("SearchResult")
                    .getJSONArray("searchResultGroups").getJSONObject(0).getJSONArray("productIds"));
        } else {
            return convertJSONArrayToList(returnSearchResultsJSONFromMSP(null).getJSONObject("SearchResult")
                    .getJSONArray("searchResultGroups").getJSONObject(0).getJSONArray("productIds"));
        }
    }


    private List<String> convertJSONArrayToList(JSONArray array) {
        List<String> myList = new ArrayList<>();
        if (array != null) {
            for (int k = 0; k < array.length(); k++) {
                String strI = String.valueOf(array.getInt(k));
                myList.add(strI);
            }
        } else {
            log.info("Array has no values");
            return null;
        }
        return myList;
    }


    private List<String> returnListOfBrowseProductIDs(String category) throws Throwable {
        return convertJSONArrayToList(getBrowseJSON(category).getJSONObject("products").getJSONArray("navigationPoolProducts"));
    }


    private String formatRefinements() {
        if (getRefinements().containsValue(null)) {
            return null;
        } else {
            String refinements = "{\"refinements\":[{";
            for (Map.Entry<String, List<String>> entry : getRefinements().entrySet()) {
                refinements = refinements.concat("\"name\":\"" + entry.getKey() + "\",\"values\":[");
                for (String value : entry.getValue()) {
                    refinements = refinements.concat("\"" + value + "\"" + ",");
                }
                refinements = StringUtils.chop(refinements);
                refinements = refinements.concat("]},{");
            }
            refinements = refinements.substring(0, refinements.length() - 2);
            refinements = refinements.concat("]}");
            return refinements;
        }
    }

    private String getCategoryId() {
        log.info("Browse Category ID " + jsonObjectMyVar.get("Cat ID").toString());
        return jsonObjectMyVar.get("Cat ID").toString();
    }

    private String getSearchTerm() {
        log.info("Search Term " + jsonObjectMyVar.get("Search Term").toString());
        return jsonObjectMyVar.get("Search Term").toString();
    }


    private HashMap<String, List<String>> getRefinements() {
        HashMap<String, List<String>> map = new HashMap<>();
        if (jsonObjectMyVar.get("Refinements") instanceof JSONObject) {
            JSONObject obj = jsonObjectMyVar.getJSONObject("Refinements");

            Iterator<String> keysItr = obj.keys();
            while (keysItr.hasNext()) {
                String key = keysItr.next();
                JSONArray value = obj.getJSONArray(key);

                if (value != null) {
                    List<String> myList = new LinkedList<>();
                    for (int k = 0; k < value.length(); k++) {
                        myList.add(value.getString(k));
                    }

                    map.put(key, myList);
                } else {
                    Assert.fail("Refinements are not in the correct format");
                }
            }
        } else {
            map.put("Refinements", null);
        }
        return map;
    }


    private String encodeValue(String value) throws Throwable {
        return URLEncoder.encode(value, UTF_8.toString());
    }



    private List<String> inBrowseNotInSearch(List<String> browseIds, List<String> searchIds) {
        List<String> result;
        result = browseIds.stream().filter(e -> notInList(searchIds, e))
                .collect(Collectors.toList());
        log.info("In Browse but not in Search: " + result);
        log.info("There are " + result.size() + " products that are in Browse but not in Search");
        return result;
    }


    private List<String> inSearchNotInBrowse(List<String> browseIds, List<String> searchIds) {
        List<String> result;
        result = searchIds.stream().filter(e -> notInList(browseIds, e))
                .collect(Collectors.toList());
        log.info("In Search but not in Browse: " + result);
        log.info("There are " + result.size() + " products that are in Search but not in Browse");
        return result;
    }


    private boolean notInList(List<String> list, String element) {
        return !list.contains(element);
    }



    private List<String> removeExcludedProductsFromSearch(String category) throws Throwable {
        List<String> myList = returnListOfSearchProductIDs(returnSearchCallWithRefinements());

        List<String> myListExcl = returnListOfBrowseProductIDs(category);
        log.info("Category for exclusion " + category);
        myListExcl.forEach(
                str -> {
                    if (myList.contains(str)) {
                        myList.remove(myList.indexOf(str));
                       // System.out.println("Removing " + str);
                    }
                }
        );
        return myList;
    }


    private HashMap<String, String> getExclusions() {
        HashMap<String, String> map = new HashMap<>();
        if (jsonObjectMyVar.get("Exclusions") instanceof JSONObject) {
            JSONObject obj = jsonObjectMyVar.getJSONObject("Exclusions");

            Iterator<String> keysItr = obj.keys();
            while (keysItr.hasNext()) {
                String key = keysItr.next();
                if (obj.getString(key) == null) {
                    Assert.fail("Exclusions are not in the correct format");
                } else {
                    map.put(key, obj.getString(key));
                }
            }

        } else {
            map.put("Exclusions", null);
        }
        return map;
    }

    private String getRefinementsDisplay(){
        return jsonObjectMyVar.get("Refinements Display").toString();
    }


    private List<JSONObject> parseCATEGORIES(JSONArray array) throws Throwable{
        List<JSONObject> myList = new LinkedList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonobject = array.getJSONObject(i);
            myList.add(jsonobject);
        }

        return myList;
    }


}