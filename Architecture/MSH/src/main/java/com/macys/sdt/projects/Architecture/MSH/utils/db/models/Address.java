package com.macys.sdt.projects.Architecture.MSH.utils.db.models;


public class Address {

    private Integer addressId;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String city;
    private String stateAbbrev;
    private String postalcode;
    private String countryCode;
    private String lastmodified;
    private String created;
    private String attention;
    private Integer siteId;



    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressline1;
    }

    public void setAddressLine1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressLine2() {
        return addressline2;
    }

    public void setAddressLine2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressLine3() {
        return addressline3;
    }

    public void setAddressLine3(String addressline3) {this.addressline3 = addressline3;
    }

    public String getAttention() {
        return attention;
    }
    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public String getLastModified() {
        return lastmodified;
    }

    public void setLastModified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getPostalCode() {
        return postalcode;
    }

    public void setPostalCode(String postalcode) {
        this.postalcode = postalcode;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }


    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }
    @Override
    public String toString() {
        return addressId + "," + addressline1 + "," + addressline2 + "," + addressline3 + "," + attention + "," + city + "," + countryCode + "," + created + "," + lastmodified + "," + postalcode +"," + siteId +"," + stateAbbrev;
    }

}



