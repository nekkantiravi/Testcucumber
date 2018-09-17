package com.macys.sdt.projects.Marketing.StoresLocator.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * this class represents Store Details as retrieved from the service
 */
public class StoreDetails {
  private String storeNumber;
  private HashMap address;
  private JSONArray JSONlink;
  private HashMap cutOffTime;
  private JSONObject JSONschedule;    // contains JSONArray workingHours; anything else?
  private JSONArray JSONworkingHours;
  private JSONObject JSONfeatures;    // contains JSONArray feature; anything else?
  private List<String> feature;
  private String phoneNumber;
  private HashMap geoLocation;
  private String name;
  private String mapUrl;
  private JSONObject JSONattributes;  // contains JSONArray attribute; anything else?
  private HashMap attribute;
  private String id;
  private String divisionId;
  private String locationNumber;

  public StoreDetails() {
    this.storeNumber = null;
    this.address = null;
    this.JSONlink = null;
    this.cutOffTime = null;
    this.JSONschedule = null;
    this.JSONworkingHours = null;
    this.JSONfeatures = null;
    this.feature = null;
    this.phoneNumber = null;
    this.geoLocation = null;
    this.name = null;
    this.mapUrl = null;
    this.JSONattributes = null;
    this.attribute = null;
    this.id = null;
    this.divisionId = null;
    this.locationNumber = null;
  }

  //  private String storeNumber 
  public String setStoreNumber(String var) {
    this.storeNumber =  var;
    return this.storeNumber;
  }

  public String getStoreNumber() {
    return this.storeNumber;
  }

  //  private HashMap address 
  public HashMap setAddress(HashMap var) {
    this.address =  var;
    return this.address;
  }

  public HashMap getAddress() {
    return this.address;
  }


  //  private JSONArray JSONlink 
  public JSONArray setJSONlink(JSONArray var) {
    this.JSONlink =  var;
    return this.JSONlink;
  }

  public JSONArray getJSONlink() {
    return this.JSONlink;
  }

  //  private HashMap cutOffTime 
  public HashMap setCutOffTime(HashMap var) {
    this.cutOffTime =  var;
    return this.cutOffTime;
  }

  public HashMap getCutOffTime() {
    return this.cutOffTime;
  }

  //  private JSONObject JSONschedule     // contains JSONArray workingHours  anything else?
  public JSONObject setJSONschedule(JSONObject var) {
    this.JSONschedule =  var;
    return this.JSONschedule;
  }

  public JSONObject getJSONschedule() {
    return this.JSONschedule;
  }

  //  private JSONArray JSONworkingHours 
  public JSONArray setJSONworkingHours(JSONArray var) {
    this.JSONworkingHours =  var;
    return this.JSONworkingHours;
  }

  public JSONArray getJSONworkingHours() {
    return this.JSONworkingHours;
  }

  //  private JSONObject JSONfeatures     // contains JSONArray feature  anything else?
  public JSONObject setJSONfeatures(JSONObject var) {
    this.JSONfeatures =  var;
    return this.JSONfeatures;
  }

  public JSONObject getJSONfeatures() {
    return this.JSONfeatures;
  }

  //  private List<String> feature 
  public List<String> setFeature(List<String> var) {
    this.feature =  var;
    return this.feature;
  }

  public List<String> getFeature() {
    return this.feature;
  }

  //  private String phoneNumber 
  public String setPhoneNumber(String var) {
    this.phoneNumber =  var;
    return this.phoneNumber;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  //  private HashMap geoLocation 
  public HashMap setGeoLocation(HashMap var) {
    this.geoLocation =  var;
    return this.geoLocation;
  }

  public HashMap getGeoLocation() {
    return this.geoLocation;
  }

  //  private String name 
  public String setName(String var) {
    this.name =  var;
    return this.name;
  }

  public String getName() {
    return this.name;
  }

  //  private String mapUrl 
  public String setMapUrl(String var) {
    this.mapUrl =  var;
    return this.mapUrl;
  }

  public String getMapUrl() {
    return this.mapUrl;
  }

  //  private JSONObject JSONattributes        // contains JSONArray attribute  anything else?
  public JSONObject setJSONattributes(JSONObject var) {
    this.JSONattributes =  var;
    return this.JSONattributes;
  }

  public JSONObject getJSONattributes() {
    return this.JSONattributes;
  }

  //  private HashMap attribute 
  public HashMap setAttribute(HashMap var) {
    this.attribute =  var;
    return this.attribute;
  }

  public HashMap getAttribute() {
    return this.attribute;
  }

  //  private String id 
  public String setId(String var) {
    this.id =  var;
    return this.id;
  }

  public String getId() {
    return this.id;
  }

  //  private String divisionId 
  public String setDivisionId(String var) {
    this.divisionId =  var;
    return this.divisionId;
  }

  public String getDivisionId() {
    return this.divisionId;
  }

  //  private String locationNumber 
  public String setLocationNumber(String var) {
    this.locationNumber =  var;
    return this.locationNumber;
  }

  public String getLocationNumber() {
    return this.locationNumber;
  }

}
