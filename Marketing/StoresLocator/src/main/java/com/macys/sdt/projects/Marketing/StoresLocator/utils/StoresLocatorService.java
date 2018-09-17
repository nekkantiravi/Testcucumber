package com.macys.sdt.projects.Marketing.StoresLocator.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.net.URLEncoder;
import java.util.*;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.apache.commons.codec.CharEncoding.UTF_8;

import com.macys.sdt.projects.Marketing.StoresLocator.utils.StoresLocatorUtils;
import com.macys.sdt.projects.Marketing.StoresLocator.utils.StoreDetails;
import com.macys.sdt.projects.Marketing.StoresLocator.utils.StoreEvents;

public class StoresLocatorService {
  /***
   * General comments...
   *
   * The https://l/macys.com/stores.html page is "probably" build by Yest (www.yext.com)
   * and therefore has no Stage5 variant; it's all production
   *
   * The store Events pages (/social/events/event/?id=22010) ARE
   * available on Stage5, but have almost no identifying markup (brittle automation)
   */

  private static final String STORES_SEARCH_PATH = "store/v2/stores/";
  private static final String SEARCH_BY_ADDRESS_INFO = "store/v2/stores?searchAddress=<search-address>";
  private static final String GET_LOCATION_DETAILS = "store/v2/stores/<location-number>";
  private static final String GET_BUILDING_DETAILS = "store/v2/stores/<location-number>/buildings";
  private static final String STORES_GEOSEARCH_PATH = "store/v2/stores/geosearch";
  private DateFormat df = DateFormat.getDateInstance();
  private static final String STORES_EVENTS_PATH = "https://storage.googleapis.com/cmsstatic.mcomnyapps.net/379/data129_nocb.json";

  private static final Logger logger = LoggerFactory.getLogger(StoresLocatorService.class);
  private static HashMap<String, String> headers = defaultHeaders();

  private static String buildingLocationNumber = null;
  private static JSONArray buildingData = null;


  /***
   * Retrieve store buildings information
   * (Contains information about departments & locations)
   *
   * @param locationNumber String
   * @return JSONArray buildingData for that store
   */
  public static JSONArray getStoreBuildings(String locationNumber) {
    System.out.println("=====> Entering getStoreBuildings");
    Assert.assertTrue("ERROR - Must have a store location number", locationNumber != null);
    getStoreBuildingsData(locationNumber);
    System.out.println("=====> Successfully retrieved buildings data");
    return buildingData;
  }

  /***
   * Retrieve building group information
   *  Men's Clothing
   *  Women's Clothing
   *  Home
   *  Kids (Children's)
   *  Restaurants
   *  Services
   *
   *  @return List<HashMap<String, String>> bldgGroup
   */
  public static List<HashMap<String, String>> getStoreBldgGroup(String bldgGroup) {
    List<HashMap<String, String>> group = new ArrayList<>();
    switch(bldgGroup.toLowerCase()) {
      case "men's clothing":
      case "men":
        group = getBldgGroup("Men's");
        break;
      case "women's clothing":
      case "women":
        group = getBldgGroup("Women's");
        break;
      case "home":
        group = getBldgGroup("Home");
        break;
      case "kids":
      case "childrens":
      case "children's":
        group = getBldgGroup("Children's");
        break;
      case "restaurants":
        group = getBldgGroup("Restaurants");
        break;
      case "departments":
        group = getBldgGroup("ALL");
        break;
      case "services":
        group = getBldgGroup("Services");
        break;
      default:
        Assert.fail("ERROR - Invalid building group request for " + bldgGroup);
        break;
    }
    return group;
  }

  /***
   * Internal routine to retrieve building "groups" by categoryName
   *
   * @param categoryName String
   * @return <List<HashMap<String, String>> group
   * @gets JSONArray buildingData
   */
  private static List<HashMap<String, String>> getBldgGroup(String categoryName) {
    List<HashMap<String, String>> group = new ArrayList<>();
    List floorInfo;
    for(int i = 0; i < buildingData.length(); i++) {
      JSONObject buildingElement = ((JSONObject) buildingData.get(i));
      String foundBldg = buildingElement.get("name").toString();
      floorInfo = buildingElement.getJSONObject("buildingDetails").getJSONArray("buildingDetail").toList();
      for(int j = 0; j < floorInfo.size(); j++) {
        HashMap floorElement = (HashMap) floorInfo.get(j);
        String foundCategory = ((HashMap) floorElement.get("category")).get("name").toString();
        if(categoryName.equals("ALL") || foundCategory.equals(categoryName)) {
          String foundFloor = ((HashMap) floorElement.get("floor")).get("name").toString();
          String foundDepartment = ((HashMap) floorElement.get("department")).get("name").toString();
          HashMap<String, String> groupMap = new HashMap<>();
          groupMap.put("building", foundBldg);
          groupMap.put("floor", foundFloor);
          groupMap.put("category", foundCategory);
          groupMap.put("department", foundDepartment);
          group.add(groupMap);
        }
      }
    }
    return group;
  }

  /***
   * Internal routine to retrieve buildings data
   *
   * @param locationNumber String
   * @return JSONArray buildings
   * Memoize...
   * @sets JSONArray buildingData
   * @sets String buildingLocationNumber
   */
  private static JSONArray getStoreBuildingsData(String locationNumber) {
    //TODO: Memoize the data, so we don't keep re-reading it
    //i.e., make buildings a class variable
    if(locationNumber.equals(buildingLocationNumber) && buildingData != null) { return buildingData; }
    String path = GET_BUILDING_DETAILS.replaceAll("<location-number>", locationNumber);
    String endPoint = getServiceURL(path);
    Response response = RESTOperations.doGET(endPoint, headers);
    int statusCode = response.getStatus();
    Assert.assertEquals("ERROR - getStoreBuildingsData GET failed with code " + statusCode, 200, statusCode);
    String finalJsonStr = response.readEntity(String.class);
    JSONObject rootJson = new JSONObject(finalJsonStr);
    JSONArray buildings = rootJson.getJSONObject("buildings").getJSONArray("building");
    Assert.assertTrue("ERROR - No buildings data provided", buildings.length() > 0);
    buildingLocationNumber = locationNumber;
    buildingData = buildings;
    return buildings;
  }

  /***
   * Retrieve the store events information
   *
   * @param storeNumber String
   * @return List<StoreEvents> Events for that store
   */
  public static List<StoreEvents> getStoreEvents(String storeNumber) {
    System.out.println("=====> Entering getStoreEvents");
    Assert.assertTrue("ERROR - Must have a store location number", storeNumber != null);
    JSONArray eventsData = getStoreEventsData();
    List<StoreEvents> eventsArray = selectStoreEventsData(eventsData, storeNumber);
    return eventsArray;
  }

  /***
   * Internatl routine to select only desired stores from the StoreEvents data package
   *
   * @param eventsData JSONArray
   * @param storeNumber String
   * @return Array of StoreEvents 
   */
  private static List<StoreEvents> selectStoreEventsData(JSONArray eventsData, String storeNumber) {
    List<StoreEvents> eventsArray = new ArrayList<>();
    for(int i = 0; i < eventsData.length(); i++) {
      JSONObject event = eventsData.getJSONObject(i);
      if(event.has("store_id") == false) { continue; }
      if(event.getString("store_id").equals(storeNumber)) {
        StoreEvents storeEvent = new StoreEvents(
            storeNumber,
            event.getString("description"),
            event.getString("title"),
            event.getString("visibility"),
            event.getString("event_url"),
            event.getString("rsvp_link"),
            event.getString("location")
            );
        storeEvent.setEventObject(event);
        // NOTE: We keep only the startDate & endDate; this can be refactored if necessary
        HashMap<String, String> datetimes = transformDatetimes(event.getJSONArray("datetimes"));
        storeEvent.setDatetimes(datetimes);
        storeEvent.setActive();
        storeEvent.setEventId(event.getString("entry_id"));
        storeEvent.setDatetimesObject(event.getJSONArray("datetimes"));
        eventsArray.add(storeEvent);
      }
    }
    return eventsArray;
  }

  /***
   * Internal routine to convert JSONArray to Java object for datetimes
   * TODO: Add the gory details when needed...
   *
   * @param datetimes JSONArray
   * @return HashMap<String, String> of {startDate, endDate}
   */
  private static HashMap<String, String> transformDatetimes(JSONArray datetimes) {
    HashMap<String, String> myMap = new HashMap<>();
    String minDate = "9999/99/99";
    String maxDate = "0000/00/00";
    for(int i = 0; i < datetimes.length(); i++) {
      JSONObject entry =  datetimes.getJSONObject(i);
      String dateSplit[] = entry.getString("event_date").split("/");
      // use dashes for compability with Java LocalDate
      String date = dateSplit[2] + "-" + dateSplit[0] + "-" + dateSplit[1];
      if(date.compareTo(maxDate) > 0) { maxDate = date; }
      if(date.compareTo(minDate) < 0) { minDate = date; }
    }
    myMap.put("startDate", minDate);
    myMap.put("endDate", maxDate);
    return myMap;
  }

  /***
   * Internal routine to get the full store Events package
   *
   * @return JSONArray Events for all stores
   */
  private static JSONArray getStoreEventsData() {
    String endPoint = STORES_EVENTS_PATH;
    logger.info("Calling Stores Events endpoint: " + endPoint);
    Response response = RESTOperations.doGET(endPoint, null);
    int statusCode = response.getStatus();
    Assert.assertEquals("ERROR - getStoreEvents GET failed with code " + statusCode, 200, statusCode);
    logger.info("Successful call to Google-StoresEventService");
    String finalJsonStr = response.readEntity(String.class);
    JSONObject rootJson = new JSONObject(finalJsonStr);
    JSONArray storesEvents = rootJson.getJSONObject("categories").getJSONObject("events").getJSONArray("entries");
    Assert.assertTrue("ERROR - No events data provided", storesEvents.length() > 0);
    return storesEvents;
  }

  /***
   * Select a store object from the returned data
   *
   * @param storesData JSONArray
   * @param item String Search element
   * @param value String Search value
   * @return JSONObject Array element for that store (same as service returns for consistency)
   *          or null if not found
   */
  public static JSONArray selectStore(JSONArray storesData, String item, String value) {
    for(int i = 0; i < storesData.length(); i++) {
      if(storesData.getJSONObject(i).getString(item).equals(value)) {
        return ((new JSONArray()).put(storesData.getJSONObject(i)));
      }
    }
    return null;
  }

  /***
   * Retrieve store details based on store locationNumber
   * NOTE: This was completed from thr transformStoreDetails() method was available
   * Would be good to refactor this so that it directly returns the StoreDetails
   *
   * @param locationNumber String
   * @return JSONArray
   */
  public static JSONArray getStoreLocationDetails(String locationNumber) {
    System.out.println("=====> Entering getStoreLocationDetails");
    String endPoint = getServiceURL(STORES_SEARCH_PATH);
    Assert.assertTrue("ERROR - must have a store location number", locationNumber != null);
    endPoint = endPoint + locationNumber;
    logger.info("Calling StoresService: " + endPoint);
    System.out.println("=====> endPoint: " + endPoint);

    JSONArray storesDetails = getStoresData(endPoint);
    return storesDetails;
  }

  /***
   * Convert JSONArray storeData to class StoreDetails
   *
   * @param storesData JSONArray (one element)
   * @return StoreDetails class item
   */
  public static StoreDetails transformStoreDetails(JSONArray storesData) {
    JSONObject storeData = (JSONObject) storesData.get(0);
    StoreDetails storeDetails = new StoreDetails();
    storeDetails.setStoreNumber(storeData.get("storeNumber").toString());
    HashMap<String, String> address = new HashMap<>();
    JSONObject addressData = storeData.getJSONObject("address");
    for(String key : addressData.keySet()) {
      address.put(key, addressData.get(key).toString());
    }
    storeDetails.setAddress(address);
    HashMap<String, String> cutOffTime = new HashMap<>();
    storeDetails.setJSONlink(storeData.getJSONArray("link"));
    JSONObject cutOffTimeData = storeData.getJSONObject("cutOffTime");
    for(String key : cutOffTimeData.keySet()) {
      cutOffTime.put(key, cutOffTimeData.get(key).toString());
    }
    storeDetails.setCutOffTime(cutOffTime);
    storeDetails.setJSONschedule(storeData.getJSONObject("schedule"));
    storeDetails.setJSONworkingHours(storeDetails.getJSONschedule().getJSONArray("workingHours"));
    storeDetails.setJSONfeatures(storeData.getJSONObject("features"));
    JSONArray featureData = storeDetails.getJSONfeatures().getJSONArray("feature");
    List<String> feature = new ArrayList<>();
    for(int i =0; i < featureData.length(); i++) {
      feature.add(featureData.getString(i));
    }
    storeDetails.setFeature(feature);
    storeDetails.setPhoneNumber(storeData.getString("phoneNumber"));
    HashMap<String, String> geoLocation = new HashMap<>();
    JSONObject geoLocationData = storeData.getJSONObject("geoLocation");
    for(String key : geoLocationData.keySet()) {
      geoLocation.put(key, geoLocationData.get(key).toString());
    }
    storeDetails.setGeoLocation(geoLocation);
    storeDetails.setName(storeData.getString("name"));
    storeDetails.setMapUrl(storeData.getString("mapUrl"));
    storeDetails.setJSONattributes(storeData.getJSONObject("attributes"));
    HashMap<String, Object> attribute = new HashMap<>();
    JSONArray attributeData = storeDetails.getJSONattributes().getJSONArray("attribute");
    for(int i = 0; i < attributeData.length(); i++) {
      JSONObject aData = (JSONObject) attributeData.get(i);
      JSONArray valueData = (JSONArray) aData.get("value");
      attribute.put(aData.get("name").toString(), transformAttributeValue(valueData)); }
    storeDetails.setAttribute(attribute);
    storeDetails.setId(storeData.get("id").toString());
    storeDetails.setDivisionId(storeData.getString("divisionId"));
    storeDetails.setLocationNumber(storeData.get("locationNumber").toString());
    return storeDetails;
  }

  /***
   * Internal method to transform attribute values
   * (NOTE: Could also be used for feature values)
   *
   * @param attributeValue JSONArray
   * @return String value, without the [] delimiters, if possible
   */
  private static Object transformAttributeValue(JSONArray attributeValue) {
    if(attributeValue.length() == 1 && attributeValue.toString().contains("TRUE")) { return "TRUE"; }
    if(attributeValue.length() == 1 && attributeValue.toString().contains("FALSE")) { return "FALSE"; }
    if(attributeValue.length() == 1) { return attributeValue.toString(); }
    // For Array elements (BOPS_NPD), we just return a string and deal with it later... if needed
    return attributeValue.toString();
  }

  /***
   * Return store details based on searchAddress
   *
   * @param searchAddress String
   * @param radius String
   * @param limit (# of stores) String
   * @return JSONArray
   */
  public static JSONArray searchStores(String searchAddress, String radius, String limit) {
    String endPoint = getServiceURL(STORES_SEARCH_PATH);

    // searchAddress : must be present; we encode it
    Assert.assertNotNull("ERROR - must have a searchAddress", searchAddress);
    try {
      String encodedAddress = URLEncoder.encode(searchAddress, UTF_8);
      endPoint = endPoint + "?searchAddress=" + encodedAddress; 
    } catch (UnsupportedEncodingException e) {
      Assert.fail("ERROR - encoding of '" + searchAddress + "' failed");
    }

    // Radius : defaults to 25 miles if not provided
    if(radius == null) {
      radius="M25";
    } else {
      if(radius.charAt(0) != 'M') {
        radius = "M" + radius;
      }
    }
    endPoint = endPoint + "&radius=" + radius;

    // Limit : defaults to 99 (essentially unlimited)
    if(limit != null) {
      endPoint = endPoint + "&_limit=" + limit;
    } else {
      endPoint = endPoint + "&_limit=99";
    }
    logger.info("Calling StoresService: " + endPoint);

    JSONArray storesDetails = getStoresData(endPoint);
    return storesDetails;
  }

  /***
   * Internal routine to issue GET call and return Stores JSON Array
   */
  private static JSONArray getStoresData(String endPoint) {
    Response response = RESTOperations.doGET(endPoint, headers);
    int statusCode = response.getStatus();
    Assert.assertEquals("ERROR - Response failed with code " + statusCode, 200, statusCode);
    logger.info("Successful call to StoresService");

    String finalJsonStr = response.readEntity(String.class);
    JSONObject rootJson = new JSONObject(finalJsonStr);
    JSONArray storesDetails = rootJson.getJSONObject("stores").getJSONArray("store");
    return storesDetails;
  }

  /***
   * Transform text filter into api selector filter value
   *
   * @param filterValue String text filter name
   * @return String api filter value
   */
  public static String getServiceFilterSelector(String filterValue) {
    String serviceSelector = null;
    switch(filterValue) {
      case "Restaurants" :
        serviceSelector = "SL_RESTAURANT";
        break;
      case "Furniture Gallery" :
        serviceSelector = "SL_FURNITURE";
        break;
      case "Maternity" :
        serviceSelector = "SL_MATERNITY";
        break;
      case "Mattresses" :
        serviceSelector = "SL_MATTRESSES";
        break;
      case "Personal Shopper" :
        serviceSelector = "SL_SHOPPER";
        break;
      case "Wedding Gift & Registry" :
        serviceSelector = "SL_BRIADY";
        break;
      case "Furniture Clearance Center" :
        serviceSelector = "SL_FURNITURE";   // TODO: Verify this!
        break;
      case "Backstage" :
        serviceSelector = "???";            // TODO: Find correct value
        break;
      case "Visitor Service" :
        serviceSelector = "SL_VISITOR";
        break;
      default:
        Assert.fail("ERROR - Invalid service request : " + filterValue);
    }
    return serviceSelector;
  }

  /***
   * Return desired base URL for service
   *
   * @param path String
   * @return String serviceURL
   */
  private static String getServiceURL(String path) {
    // This works from the RESTClient... (using 'mewqe' for header value)
    //  https://api.qa17codemacys.fds.com/store/v2/stores?searchAddress=94105
    // This fails from the RESTClient... (need different header value)?)
    //  https://11.168.50.119:8080/api/store/v2/stores?searchAddress=94105
    //  Service unexpectedly closed the connection
    return "https://api." + EnvironmentDetails.otherApp("MSPORDER").envName + ".fds.com/" + path;
  }

  private static HashMap<String, String> defaultHeaders() {
    HashMap<String, String> header = new HashMap<>();
    header.put("x-macys-webservice-client-id", "mewqe");
    header.put("Accept", "application/json");
    return header;
  }
}
