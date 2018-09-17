package com.macys.sdt.projects.Marketing.StoresLocator.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * this class represents Store Events as retrieved from the service
 */
public class StoreEvents {
  private String storeNumber;
  private Boolean active;
  private String description;
  private String title;
  private String visibility;
  private String eventUrl;
  private String rsvpLink;
  private HashMap<String, String> datetimes;
  private String location;
  private String eventId;
  private JSONArray datetimesObject;     // TODO: Temporary holding space until extracted into datetimes!
  private JSONObject eventObject;

  public StoreEvents(String n, String d, String t, String v, String e, String r, String l) {
    this.storeNumber = n;
    this.active = false;
    this.description = d;
    this.title = t.trim();
    this.visibility = v;
    this.eventUrl = e;
    this.rsvpLink = r;
    this.location = l;
    this.eventId = null;
    this.datetimes = new HashMap<>();
    this.datetimesObject = null;
    this.eventObject = null;
  }

  public StoreEvents() {
    this.storeNumber = null;
    this.active = false;
    this.description = null;
    this.title = null;
    this.visibility = null;
    this.eventUrl = null;
    this.rsvpLink = null;
    this.location = null;
    this.eventId = null;
    this.datetimes = new HashMap<>();
    this.datetimesObject = null;
    this.eventObject = null;
  }

  public void setStoreNumber(String n) { this.storeNumber = n; }

  public void setDescription(String d) { this.description = d; }
  public String getDescription() { return this.description; }

  public String getTitle() { return this.title; }
  public void setTitle(String t) { this.title = t.trim(); }

  public Boolean getActive() { return this.active; }
  public void setActive(Boolean b) { this.active = b; }
  public void setActive() {
    String currentDate = LocalDateTime.now().toString().split("T")[0];
    String start = this.datetimes.get("startDate");
    String end = this.datetimes.get("endDate");
    this.active = (currentDate.compareTo(start) <= 0 || currentDate.compareTo(end) <= 0);
  }

  public void setRsvpLink(String l) { this.rsvpLink = l; }
  public String getRsvpLink() { return this.rsvpLink; }

  public void setEventId(String e) { this.eventId = e; }
  public String getEventId() { return this.eventId; }

  public void setDatetimes(HashMap<String, String> l) {
    this.datetimes = l;
  }
  public String getStartDate() {
    return ((HashMap) this.datetimes).get("startDate").toString();
  }

  // These two methods to just save the original JSON elements, in case more detail is needed
  public void setDatetimesObject(JSONArray o) { this.datetimesObject = o; }
  public void setEventObject(JSONObject o) { this.eventObject = o; }
}
