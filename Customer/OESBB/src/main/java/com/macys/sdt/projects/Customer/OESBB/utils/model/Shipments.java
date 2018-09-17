package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Strings;
import com.macys.sdt.projects.Customer.OESBB.utils.Utilities;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({ "shipmentNbr", "shippedDate", "mailFlag", "title",
		"firstName", "middleName", "lastName", "attnLine", "addrLine1",
		"addrLine2", "addrLine3", "city", "state", "country", "zipcode",
		"giftGreetingMsg", "giftClosingMsg", "giftSignatureMsg",
		"giftReceiptFlag", "giftWrapFlag", "registryNbr",
		"registryReceipientEmail", "registryRegistrant1",
		"registryRegistrant2", "pickupReadyDate", "tracking", "storeName",
		"storeAddrLine1", "storeAddrLine2", "storeCity", "storeState",
		"storeZipcode", "storePickupInstructions", "email", "phone",
		"shipitems","shipRetailAmt","shipTaxAmt","pickupBarcode",
		"storeBopsPhone", "storePhone", "storeHoursDetails", "pickupCancelDate", "systemCancelDate","lockerAccessCode", "lockerAccessCodeUrl" })
public class Shipments {
	@JsonProperty("shipmentNbr")
	private String shipmentNbr;
	@JsonProperty("shippedDate")
	private String shippedDate;
	@JsonProperty("mailFlag")
	private String mailFlag;
	@JsonProperty("title")
	private String title;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("attnLine")
	private String attnLine;
	@JsonProperty("addrLine1")
	private String addrLine1;
	@JsonProperty("addrLine2")
	private String addrLine2;
	@JsonProperty("addrLine3")
	private String addrLine3;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country")
	private String country;
	@JsonProperty("zipcode")
	private String zipcode;
	@JsonProperty("giftGreetingMsg")
	private String giftGreetingMsg;
	@JsonProperty("giftClosingMsg")
	private String giftClosingMsg;
	@JsonProperty("giftSignatureMsg")
	private String giftSignatureMsg;
	@JsonProperty("giftReceiptFlag")
	private String giftReceiptFlag;
	@JsonProperty("giftWrapFlag")
	private String giftWrapFlag;
	@JsonProperty("registryNbr")
	private String registryNbr;
	@JsonProperty("registryReceipientEmail")
	private String registryReceipientEmail;
	@JsonProperty("registryRegistrant1")
	private String registryRegistrant1;
	@JsonProperty("registryRegistrant2")
	private String registryRegistrant2;
	@JsonProperty("pickupReadyDate")
	private String pickupReadyDate;
	@JsonProperty("storeName")
	private String storeName;
	@JsonProperty("storeAddrLine1")
	private String storeAddrLine1;
	@JsonProperty("storeAddrLine2")
	private String storeAddrLine2;
	@JsonProperty("storeCity")
	private String storeCity;
	@JsonProperty("storeState")
	private String storeState;
	@JsonProperty("storeZipcode")
	private String storeZipcode;
	@JsonProperty("storeBopsPhone")
	private String storeBopsPhone;
	@JsonProperty("storePickupInstructions")
	private String storePickupInstructions;
	@JsonProperty("email")
	private String email;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("returnSubmittedDate")
	private String returnSubmittedDate;
	@JsonProperty("systemCancelDate")
	private String systemCancelDate;
	@JsonProperty("shipRetailAmt")
	private String shipRetailAmt;
	@JsonProperty("shipTaxAmt")
	private String shipTaxAmt;	
	@JsonProperty("shipitems")
	private List<Shipitem> shipitems = new ArrayList<Shipitem>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	@JsonProperty("tracking")
	private List<TrackingDetails> trackingDetails = new ArrayList<TrackingDetails>();
	@JsonProperty("lockerAccessCode")
	private String lockerAccessCode;
	@JsonProperty("lockerAccessCodeUrl")
	private String lockerAccessCodeUrl;

	/**
	 * 
	 * @return The shipmentNbr
	 */
	@JsonProperty("shipmentNbr")
	public String getShipmentNbr() {
		return shipmentNbr;
	}

	/**
	 * 
	 * @param shipmentNbr
	 *            The shipmentNbr
	 */
	@JsonProperty("shipmentNbr")
	public void setShipmentNbr(String shipmentNbr) {
		this.shipmentNbr = shipmentNbr;
	}

	/**
	 * 
	 * @return The shippedDate
	 */
	@JsonProperty("shippedDate")
	public String getShippedDate() {
		return shippedDate;
	}

	/**
	 * 
	 * @param shippedDate
	 *            The shippedDate
	 */
	@JsonProperty("shippedDate")
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}

	/**
	 * 
	 * @return The mailFlag
	 */
	@JsonProperty("mailFlag")
	public String getMailFlag() {
		return mailFlag;
	}

	/**
	 * 
	 * @param mailFlag
	 *            The mailFlag
	 */
	@JsonProperty("mailFlag")
	public void setMailFlag(String mailFlag) {
		this.mailFlag = mailFlag;
	}

	/**
	 * 
	 * @return The title
	 */
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            The title
	 */
	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return The firstName
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            The firstName
	 */
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return The middleName
	 */
	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * 
	 * @param middleName
	 *            The middleName
	 */
	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * 
	 * @return The lastName
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            The lastName
	 */
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return The attnLine
	 */
	@JsonProperty("attnLine")
	public String getAttnLine() {
		return attnLine;
	}

	/**
	 * 
	 * @param attnLine
	 *            The attnLine
	 */
	@JsonProperty("attnLine")
	public void setAttnLine(String attnLine) {
		this.attnLine = attnLine;
	}

	/**
	 * @return The email
	 */
	@JsonProperty("email")
	public String getemail() {
		return email;
	}

	/**
	 * @param email
	 *            The email
	 */
	@JsonProperty("email")
	public void setemail(String email) {
		this.email = email;
	}

	/** @return The phone */
	@JsonProperty("phone")
	public String getphone() {
		return phone;
	}

	/**
	 * @param phone
	 *            The phone
	 */
	@JsonProperty("phone")
	public void setphone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @return The addrLine1
	 */
	@JsonProperty("addrLine1")
	public String getAddrLine1() {
		return addrLine1;
	}

	/**
	 * 
	 * @param addrLine1
	 *            The addrLine1
	 */
	@JsonProperty("addrLine1")
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	/**
	 * 
	 * @return The addrLine2
	 */
	@JsonProperty("addrLine2")
	public String getAddrLine2() {
		return addrLine2;
	}

	/**
	 * 
	 * @param addrLine2
	 *            The addrLine2
	 */
	@JsonProperty("addrLine2")
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	/**
	 * 
	 * @return The addrLine3
	 */
	@JsonProperty("addrLine3")
	public String getAddrLine3() {
		return addrLine3;
	}

	/**
	 * 
	 * @param addrLine3
	 *            The addrLine3
	 */
	@JsonProperty("addrLine3")
	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	/**
	 * 
	 * @return The city
	 */
	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 *            The city
	 */
	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return The state
	 */
	@JsonProperty("state")
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            The state
	 */
	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return The country
	 */
	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country
	 *            The country
	 */
	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 
	 * @return The zipcode
	 */
	@JsonProperty("zipcode")
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 
	 * @param zipcode
	 *            The zipcode
	 */
	@JsonProperty("zipcode")
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return Utilities.returnCamelCase(this.getFirstName()) + " "
				+ Utilities.returnCamelCase(this.getLastName())
				+ this.getAddrLine1() + this.getAddrLine2()
				+ this.getAddrLine3() + this.getCity() + ", " + this.getState()
				+ " " + this.getZipcode();
	}

	/**
	 * 
	 * @return The giftGreetingMsg
	 */
	@JsonProperty("giftGreetingMsg")
	public String getGiftGreetingMsg() {
		return giftGreetingMsg;
	}

	/**
	 * 
	 * @param giftGreetingMsg
	 *            The giftGreetingMsg
	 */
	@JsonProperty("giftGreetingMsg")
	public void setGiftGreetingMsg(String giftGreetingMsg) {
		this.giftGreetingMsg = giftGreetingMsg;
	}

	/**
	 * 
	 * @return The giftClosingMsg
	 */
	@JsonProperty("giftClosingMsg")
	public String getGiftClosingMsg() {
		return giftClosingMsg;
	}

	/**
	 * 
	 * @param giftClosingMsg
	 *            The giftClosingMsg
	 */
	@JsonProperty("giftClosingMsg")
	public void setGiftClosingMsg(String giftClosingMsg) {
		this.giftClosingMsg = giftClosingMsg;
	}

	/**
	 * 
	 * @return The giftSignatureMsg
	 */
	@JsonProperty("giftSignatureMsg")
	public String getGiftSignatureMsg() {
		return giftSignatureMsg;
	}

	/**
	 * 
	 * @param giftSignatureMsg
	 *            The giftSignatureMsg
	 */
	@JsonProperty("giftSignatureMsg")
	public void setGiftSignatureMsg(String giftSignatureMsg) {
		this.giftSignatureMsg = giftSignatureMsg;
	}

	/**
	 * 
	 * @return The giftReceiptFlag
	 */
	@JsonProperty("giftReceiptFlag")
	public String getGiftReceiptFlag() {
		return giftReceiptFlag;
	}

	/**
	 * 
	 * @param giftReceiptFlag
	 *            The giftReceiptFlag
	 */
	@JsonProperty("giftReceiptFlag")
	public void setGiftReceiptFlag(String giftReceiptFlag) {
		this.giftReceiptFlag = giftReceiptFlag;
	}

	/**
	 * 
	 * @return The giftWrapFlag
	 */
	@JsonProperty("giftWrapFlag")
	public String getGiftWrapFlag() {
		return giftWrapFlag;
	}

	/**
	 * 
	 * @param giftWrapFlag
	 *            The giftWrapFlag
	 */
	@JsonProperty("giftWrapFlag")
	public void setGiftWrapFlag(String giftWrapFlag) {
		this.giftWrapFlag = giftWrapFlag;
	}

	/**
	 * 
	 * @return The registryNbr
	 */
	@JsonProperty("registryNbr")
	public String getRegistryNbr() {
		return registryNbr;
	}

	/**
	 * 
	 * @param registryNbr
	 *            The registryNbr
	 */
	@JsonProperty("registryNbr")
	public void setRegistryNbr(String registryNbr) {
		this.registryNbr = registryNbr;
	}

	/**
	 * 
	 * @return The registryReceipientEmail
	 */
	@JsonProperty("registryReceipientEmail")
	public String getRegistryReceipientEmail() {
		if(Strings.isNullOrEmpty(registryReceipientEmail)){return null;}
		return registryReceipientEmail;
	}

	/**
	 * 
	 * @param registryReceipientEmail
	 *            The registryReceipientEmail
	 */
	@JsonProperty("registryReceipientEmail")
	public void setRegistryReceipientEmail(String registryReceipientEmail) {
		this.registryReceipientEmail = registryReceipientEmail;
	}

	/**
	 * 
	 * @return The registryRegistrant1
	 */
	@JsonProperty("registryRegistrant1")
	public String getRegistryRegistrant1() {
		return registryRegistrant1;
	}

	/**
	 * 
	 * @param registryRegistrant1
	 *            The registryRegistrant1
	 */
	@JsonProperty("registryRegistrant1")
	public void setRegistryRegistrant1(String registryRegistrant1) {
		this.registryRegistrant1 = registryRegistrant1;
	}

	/**
	 * 
	 * @return The registryRegistrant2
	 */
	@JsonProperty("registryRegistrant2")
	public String getRegistryRegistrant2() {
		return registryRegistrant2;
	}

	/**
	 * 
	 * @param registryRegistrant2
	 *            The registryRegistrant2
	 */
	@JsonProperty("registryRegistrant2")
	public void setRegistryRegistrant2(String registryRegistrant2) {
		this.registryRegistrant2 = registryRegistrant2;
	}


	@JsonProperty("storeHoursDetails")
	private List<StoreDetails> storeHoursDetails = new ArrayList<StoreDetails>();
	private String pickupCancelDate;
	private String storePhone;
	private String pickupBarcode;

	public List<StoreDetails> getStoreHoursDetails() {
		return storeHoursDetails;
	}

	public void setStoreHoursDetails(List<StoreDetails> storeHoursDetails) {
		this.storeHoursDetails = storeHoursDetails;
	}
	/**
	 * 
	 * @return The returnSubmittedDate
	 */
	@JsonProperty("returnSubmittedDate")
	public String getReturnSubmittedDate() {
		return returnSubmittedDate;
	}

	
	/**
	 * 
	 * @param returnSubmittedDate
	 *            The returnSubmittedDate
	 */
	@JsonProperty("returnSubmittedDate")
	public void setReturnSubmittedDate(String returnSubmittedDate) {
		this.returnSubmittedDate = returnSubmittedDate;
	}

	/**
	 * 
	 * @return The systemCancelDate
	 */
	@JsonProperty("systemCancelDate")
	public String getSystemCancelDate() {
		return systemCancelDate;
	}

	
	/**
	 * 
	 * @param systemCancelDate
	 *            The systemCancelDate
	 */
	@JsonProperty("systemCancelDate")
	public void setSystemCancelDate(String systemCancelDate) {
		this.systemCancelDate = systemCancelDate;
	}

	/**
	 * 
	 * @return The shipRetailAmt
	 */
	@JsonProperty("shipRetailAmt")
	public String getShipRetailAmount() {
		return shipRetailAmt;
	}

	/**
	 * 
	 * @param shipRetailAmt
	 *            The shipRetailAmt
	 */
	@JsonProperty("shipRetailAmt")
	public void setShipRetailAmount(String shipRetailAmt) {
		this.shipRetailAmt = shipRetailAmt;
	}
	
	
	/**
	 * 
	 * @return The shipTaxAmount
	 */
	@JsonProperty("shipTaxAmt")
	public String getShipTaxAmount() {
		return shipTaxAmt;
	}

	/**
	 * 
	 * @param shipTaxAmt
	 *            The shipTaxAmt
	 */
	@JsonProperty("shipTaxAmt")
	public void setshipTaxAmount(String shipTaxAmt) {
		this.shipTaxAmt = shipTaxAmt;
	}
	
	public List<TrackingDetails> getTrackingDetails() {
		return trackingDetails;
	}

	public void setTrackingDetails(List<TrackingDetails> trackingDetails) {
		this.trackingDetails = trackingDetails;
	}
	/**
	 * 
	 * @return The shipitems
	 */
	@JsonProperty("shipitems")
	public List<Shipitem> getShipitems() {
		return shipitems;
	}

	/**
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return storeAddrLine1
	 */
	public String getStoreAddrLine1() {
		return storeAddrLine1;
	}

	/**
	 * @param storeAddrLine1
	 */
	public void setStoreAddrLine1(String storeAddrLine1) {
		this.storeAddrLine1 = storeAddrLine1;
	}

	/**
	 * @return storeAddrLine2
	 */
	public String getStoreAddrLine2() {
		return storeAddrLine2;
	}

	/**
	 * @param storeAddrLine2
	 */
	public void setStoreAddrLine2(String storeAddrLine2) {
		this.storeAddrLine2 = storeAddrLine2;
	}

	/**
	 * @return storeCity
	 */
	public String getStoreCity() {
		return storeCity;
	}

	/**
	 * @param storeCity
	 */
	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}

	/**
	 * @return storeState
	 */
	public String getStoreState() {
		return storeState;
	}

	/**
	 * @param storeState
	 */
	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}

	/**
	 * @return storeZipcode
	 */
	public String getStoreZipcode() {
		return storeZipcode;
	}

	/**
	 * @return storeBopsPhone
	 */
	public String getStoreBopsPhone() {

		return storeBopsPhone;
	}

	/**
	 * @param storeBopsPhone
	 */
	public void setStoreBopsPhone(String storeBopsPhone) {
		this.storeBopsPhone = storeBopsPhone;
	}

	/**
	/**
	 * 
	 * @return The pickupReadyDate
	 */
	@JsonProperty("pickupReadyDate")
	public String getPickupReadyDate() {
		  return pickupReadyDate;
		 }
	/**
	 * 
	 * @param pickupReadyDate
	 *            The pickupReadyDate
	 */
	public void setPickupReadyDate(String pickupReadyDate) {
		  this.pickupReadyDate = pickupReadyDate;
		 }
        /*
	 * @param storeZipcode
	 */
	public void setStoreZipcode(String storeZipcode) {
		this.storeZipcode = storeZipcode;
	}

	/**
	 * @return storePickupInstructions
	 */
	public String getStorePickupInstructions() {
		return storePickupInstructions;
	}

	/**
	 * @param storePickupInstructions
	 */
	public void setStorePickupInstructions(String storePickupInstructions) {
		this.storePickupInstructions = storePickupInstructions;
	}

	/**
	 * 
	 * @param shipitems
	 *            The shipitems
	 */
	@JsonProperty("shipitems")
	public void setShipitems(List<Shipitem> shipitems) {
		this.shipitems = shipitems;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}


	/**
	 * @param pickupBarcode
	 */
	public String getPickupBarcode() {
		return pickupBarcode;
	}

	/**
	 * @param pickupBarcode
	 */
	public void setPickupBarcode(String pickupBarcode) {
		this.pickupBarcode = pickupBarcode;
	}

	/**
	 * @return storePhone
	 */
	public String getStorePhone() {

		return storePhone;
	}

	/**
	 * @param storePhone
	 */
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	/**
	 * @return pickupCancelDate
	 */
	public String getPickupCancelDate() {

		return pickupCancelDate;
	}

	/**
	 * @param storePhone
	 */
	public void setPickupCancelDate(String pickupCancelDate) {
		this.pickupCancelDate = pickupCancelDate;
	}

	public String getLockerAccessCode() {
		return lockerAccessCode;
	}

	public void setLockerAccessCode(String lockerAccessCode) {
		this.lockerAccessCode = lockerAccessCode;
	}

	public String getLockerAccessCodeUrl() {
		return lockerAccessCodeUrl;
	}

	public void setLockerAccessCodeUrl(String lockerAccessCodeUrl) {
		this.lockerAccessCodeUrl = lockerAccessCodeUrl;
	}
	
	
}

	/**
	 * @return storeHoursDetails
	 */
	/*
	 * public String getStoreHoursDetails() {
	 * 
	 * return storeHoursDetails; }
	 */
        /**
	 * @param storeHoursDetails
	 */
	/*
	 * public void setStoreHoursDetails(String storeHoursDetails) {
	 * this.storeHoursDetails = storeHoursDetails; }
	 */

	
