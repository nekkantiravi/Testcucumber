package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "orderCapturedDate", "divisionCode", "locationCode", "shopAtBrand", "explanationText", "adhocText",
		"phoneNbrFP", "phoneNbrCS", "hoursCS", "hoursFP", "zoneName","autoProvisioned","rewardCardAmt", "rewardCardEffectiveDate","rewardCardExpirationDate" })
public class Order {

	@JsonProperty("orderCapturedDate")
	private String orderCapturedDate;
	@JsonProperty("divisionCode")
	private String divisionCode;
	@JsonProperty("locationCode")
	private String locationCode;
	@JsonProperty("shopAtBrand")
	private String shopAtBrand;
	@JsonProperty("explanationText")
	private String explanationText;
	@JsonProperty("adhocText")
	private String adhocText;
	@JsonProperty("phoneNbrFP")
	private String phoneNbrFP;
	@JsonProperty("phoneNbrCS")
	private String phoneNbrCS;
	@JsonProperty("hoursCS")
	private String hoursCS;
	@JsonProperty("hoursFP")
	private String hoursFP;
	@JsonProperty("zoneName")
	private String zoneName;
	@JsonProperty("autoProvisioned")
	private Boolean autoProvisioned;
	@JsonProperty("rewardCardAmt")
	private String rewardCardAmt;
	@JsonProperty("rewardCardEffectiveDate")
	private String rewardCardEffectiveDate;
	@JsonProperty("rewardCardExpirationDate")
	private String rewardCardExpirationDate;
	@JsonProperty("rewardCardEarnEndDate")
	private String rewardCardEarnEndDate;
	@JsonProperty("orderCancelTime")
	private String orderCancelTime;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The orderCapturedDate
	 */
	@JsonProperty("orderCapturedDate")
	public String getOrderCapturedDate() {
		return orderCapturedDate;
	}

	/**
	 * 
	 * @param orderCapturedDate
	 *            The orderCapturedDate
	 */
	@JsonProperty("orderCapturedDate")
	public void setOrderCapturedDate(String orderCapturedDate) {
		this.orderCapturedDate = orderCapturedDate;
	}

	/**
	 * 
	 * @return The divisionCode
	 */
	@JsonProperty("divisionCode")
	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * 
	 * @param divisionCode
	 *            The divisionCode
	 */
	@JsonProperty("divisionCode")
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	/**
	 * 
	 * @return The locationCode
	 */
	@JsonProperty("locationCode")
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * 
	 * @param locationCode
	 *            The locationCode
	 */
	@JsonProperty("locationCode")
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * 
	 * @return The shopAtBrand
	 */
	@JsonProperty("shopAtBrand")
	public String getShopAtBrand() {
		return shopAtBrand;
	}

	/**
	 * 
	 * @param shopAtBrand
	 *            The shopAtBrand
	 */
	@JsonProperty("shopAtBrand")
	public void setShopAtBrand(String shopAtBrand) {
		this.shopAtBrand = shopAtBrand;
	}

	/**
	 * 
	 * @return The explanationText
	 */
	@JsonProperty("explanationText")
	public String getExplanationText() {
		return explanationText;
	}

	/**
	 * 
	 * @param explanationText
	 *            The explanationText
	 */
	@JsonProperty("explanationText")
	public void setExplanationText(String explanationText) {
		this.explanationText = explanationText;
	}

	/**
	 * 
	 * @return The adhocText
	 */
	@JsonProperty("adhocText")
	public String getAdhocText() {
		return adhocText;
	}

	/**
	 * 
	 * @param adhocText
	 *            The adhocText
	 */
	@JsonProperty("adhocText")
	public void setAdhocText(String adhocText) {
		this.adhocText = adhocText;
	}

	/**
	 * 
	 * @return The phoneNbrFP
	 */
	@JsonProperty("phoneNbrFP")
	public String getPhoneNbrFP() {
		return phoneNbrFP;
	}

	/**
	 * 
	 * @param phoneNbrFP
	 *            The phoneNbrFP
	 */
	@JsonProperty("phoneNbrFP")
	public void setPhoneNbrFP(String phoneNbrFP) {
		this.phoneNbrFP = phoneNbrFP;
	}

	/**
	 * 
	 * @return The phoneNbrCS
	 */
	@JsonProperty("phoneNbrCS")
	public String getPhoneNbrCS() {
		return phoneNbrCS;
	}

	/**
	 * 
	 * @param phoneNbrCS
	 *            The phoneNbrCS
	 */
	@JsonProperty("phoneNbrCS")
	public void setPhoneNbrCS(String phoneNbrCS) {
		this.phoneNbrCS = phoneNbrCS;
	}

	/**
	 * 
	 * @return The hoursCS
	 */
	@JsonProperty("hoursCS")
	public String getHoursCS() {
		return hoursCS;
	}

	/**
	 * 
	 * @param hoursCS
	 *            The hoursCS
	 */
	@JsonProperty("hoursCS")
	public void setHoursCS(String hoursCS) {
		this.hoursCS = hoursCS;
	}

	/**
	 * 
	 * @return The hoursFP
	 */
	@JsonProperty("hoursFP")
	public String getHoursFP() {
		return hoursFP;
	}

	/**
	 * 
	 * @param hoursFP
	 *            The hoursFP
	 */
	@JsonProperty("hoursFP")
	public void setHoursFP(String hoursFP) {
		this.hoursFP = hoursFP;
	}
	
	/**
	 * 
	 * @return The zoneName
	 */
	@JsonProperty("zoneName")
	public String getZoneName() {
		return zoneName;
	}

	/**
	 * 
	 * @param zoneName
	 *            The zoneName
	 */
	@JsonProperty("zoneName")
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	/**
	 * 
	 * @return The autoProvisioned
	 */
	@JsonProperty("autoProvisioned")
	public Boolean getAutoProvisioned() {
		return autoProvisioned;
	}

	/**
	 * 
	 * @param autoProvisioned
	 *            The autoProvisioned
	 */
	@JsonProperty("autoProvisioned")
	public void setAutoProvisioned(Boolean autoProvisioned) {
		this.autoProvisioned = autoProvisioned;
	}
	
	/**
	 * 
	 * @return The rewardCardAmt
	 */
	@JsonProperty("rewardCardAmt")
	public String getRewardCardAmt() {
		return rewardCardAmt;
	}

	/**
	 * 
	 * @param rewardCardAmt
	 *            The rewardCardAmt
	 */
	@JsonProperty("rewardCardAmt")
	public void setRewardCardAmt(String rewardCardAmt) {
		this.rewardCardAmt = rewardCardAmt;
	}
	
	/**
	 * 
	 * @return The rewardCardExpirationDate
	 */
	@JsonProperty("rewardCardExpirationDate")
	public String getRewardCardExpirationDate() {
		return rewardCardExpirationDate;
	}

	/**
	 * 
	 * @param rewardCardExpirationDate
	 *            The rewardCardExpirationDate
	 */
	@JsonProperty("rewardCardExpirationDate")
	public void setRewardCardExpirationDate(String rewardCardExpirationDate) {
		this.rewardCardExpirationDate = rewardCardExpirationDate;
	}

	/**
	 * 
	 * @return The rewardCardEffectiveDate
	 */
	@JsonProperty("rewardCardEffectiveDate")
	public String getRewardCardEffectiveDate() {
		return rewardCardEffectiveDate;
	}

	/**
	 * 
	 * @param rewardCardEffectiveDate
	 *            The rewardCardEffectiveDate
	 */
	@JsonProperty("rewardCardEffectiveDate")
	public void setRewardCardEffectiveDate(String rewardCardEffectiveDate) {
		this.rewardCardEffectiveDate = rewardCardEffectiveDate;
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
	 * 
	 * @return The rewardCardEarnEndDate
	 */
	@JsonProperty("rewardCardEarnEndDate")
	public String getRewardCardEarnEndDate() {
		return rewardCardEarnEndDate;
	}

	/**
	 * 
	 * @param rewardCardEarnEndDate
	 *            The rewardCardEarnEndDate
	 */
	@JsonProperty("rewardCardEarnEndDate")
	public void setRewardCardEarnEndDate(String rewardCardEarnEndDate) {
		this.rewardCardEarnEndDate = rewardCardEarnEndDate;
	}
	
	@JsonProperty("orderCancelTime")
	public void setOrderCancelTime(String orderCancelTime) {
		this.orderCancelTime = orderCancelTime;
	}
	
	@JsonProperty("orderCancelTime")
	public String getOrderCancelTime() {
		return orderCancelTime;
	}

}
