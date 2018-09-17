package com.macys.sdt.projects.Marketing.StoresLocator.utils;

import org.openqa.selenium.WebElement;

/*
 * This class represents the SearchResultsStoreDetails as retrieved from the web page
 * Obtained by splitting out the text from the search results list
 */
public class SearchResultsStoreDetails {
  private String name;
  private WebElement nameLink;
  private String distance;
  private String address;
  private String phone;
  private String hours;
  // Placeholders; Net Yet implemented
  private String visitSite;
  private String getDirections;

  public SearchResultsStoreDetails(String n, String d, String a, String p, String h, String v, String g) {
    this.name = n;
    this.distance = d;
    this.address = a;
    this.phone = p;
    this.hours = h;
    this.visitSite = v;
    this.getDirections = g;
    this.nameLink = null;
    this.visitSite = null;
    this.getDirections = null;
  }
  public void setNameLink(WebElement nl) { this.nameLink = nl; }
  public WebElement getNameLink() { return nameLink; }
  public String getName() { return name; }
  public String getDistance() { return distance; }
  public String getAddress() { return address; }
  public String getPhone() { return phone; }
  public String getHours() { return hours; }
}
