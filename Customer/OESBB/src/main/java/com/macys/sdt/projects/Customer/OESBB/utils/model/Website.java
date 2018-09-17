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
@JsonPropertyOrder({ "promoUrl", "promoReportAbuseUrl", "promoUserFirstName", "promoOneTimePwdExpTime","profileEmailAddress","securityFirstName","securityUrl"})
public class Website {

	@JsonProperty("promoUrl")
	private String promoUrl;
	@JsonProperty("promoReportAbuseUrl")
	private String promoReportAbuseUrl;
	@JsonProperty("promoUserFirstName")
	private String promoUserFirstName;
	@JsonProperty("securityFirstName")
	private String securityFirstName;
	@JsonProperty("securityUrl")
	private String securityUrl;
	@JsonProperty("promoOneTimePwdExpTime")
	private String promoOneTimePwdExpTime;
	@JsonProperty("profileEmailAddress")
	private String profileEmailAddress;
	@JsonProperty("promoPersonalMessage")
	private String promoPersonalMessage;
	@JsonProperty("promoHeading")
	private String promoHeading;
	@JsonProperty("promoSubHeading")
	private String promoSubHeading;
	@JsonProperty("promoDesc")
	private String promoDesc;
	@JsonProperty("promoSubDesc")
	private String promoSubDesc;
	@JsonProperty("PromoCode")
	private String PromoCode;
	@JsonProperty("promoExpiryDate")
	private String promoExpiryDate;
	@JsonProperty("promoLegalDisclaimer")
	private String promoLegalDisclaimer;
	@JsonProperty("profileMobileNbr")
	private String profileMobileNbr;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("promoUrl")
	public String getPromoUrl() {
		return promoUrl;
	}

	/**
	 * 
	 * @param promoUrl 
	 * @param promoUrl
	 *            The promoUrl
	 */
	@JsonProperty("promoUrl")
	public void setPromoUrl(String promoUrl) {
		this.promoUrl = promoUrl;
	}

	/**
	 * 
	 * @return The promoReportAbuseUrl
	 */
	@JsonProperty("promoReportAbuseUrl")
	public String getPromoReportAbuseUrl() {
		return promoReportAbuseUrl;
	}

	/**
	 * 
	 * @param promoReportAbuseUrl
	 *            The promoReportAbuseUrl
	 */
	@JsonProperty("promoReportAbuseUrl")
	public void setPromoReportAbuseUrl(String promoReportAbuseUrl) {
		this.promoReportAbuseUrl = promoReportAbuseUrl;
	}

	/**
	 * 
	 * @return The promoUserFirstName
	 */
	@JsonProperty("promoUserFirstName")
	public String getPromoUserFirstName() {
		return promoUserFirstName;
	}

	/**
	 * 
	 * @param promoUserFirstName
	 *            The promoUserFirstName
	 */
	@JsonProperty("promoUserFirstName")
	public void setPromoUserFirstName(String promoUserFirstName) {
		this.promoUserFirstName = promoUserFirstName;
	}
	
	/**
	 * 
	 * @return The securityFirstName
	 */
	@JsonProperty("securityFirstName")
	public String getSecurityFirstName() {
		return securityFirstName;
	}
	
	/**
	 * 
	 * @param securityFirstName
	 *            The securityFirstName
	 */
	@JsonProperty("securityFirstName")
	public void setSecurityFirstName(String securityFirstName) {
		this.securityFirstName = securityFirstName;
	}


	/**
	 * 
	 * @return The securityUrl
	 */
	@JsonProperty("securityUrl")
	public String getSecurityUrl() {
		return securityUrl;
	}
	
	/**
	 * 
	 * @param securityUrl
	 *            The securityUrl
	 */
	@JsonProperty("securityUrl")
	public void setSecurityUrl(String securityUrl) {
		this.securityUrl = securityUrl;
	}
	
	
	/**
	 * 
	 * @return The promoOneTimePwdExpTime
	 */
	@JsonProperty("promoOneTimePwdExpTime")
	public String getPromoOneTimePwdExpTime() {
		return promoOneTimePwdExpTime;
	}

	/**
	 * 
	 * @param promoOneTimePwdExpTime
	 *            The promoOneTimePwdExpTime
	 */
	@JsonProperty("promoOneTimePwdExpTime")
	public void setPromoOneTimePwdExpTime(String promoOneTimePwdExpTime) {
		this.promoOneTimePwdExpTime = promoOneTimePwdExpTime;
	}
	
	/**
	 * 
	 * @return The profileEmailAddress
	 */
	@JsonProperty("profileEmailAddress")
	public String getProfileEmailAddress() {
		return profileEmailAddress;
	}

	/**
	 * 
	 * @param profileEmailAddress
	 *            The profileEmailAddress
	 */
	@JsonProperty("profileEmailAddress")
	public void setProfileEmailAddress(String profileEmailAddress) {
		this.profileEmailAddress = profileEmailAddress;
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
	 * @return the promoLegalDisclaimer
	 */
	public String getPromoLegalDisclaimer() {
		return promoLegalDisclaimer;
	}


	/**
	 * @return the promoPersonalMessage
	 */
	public String getPromoPersonalMessage() {
		return promoPersonalMessage;
	}

	/**
	 * @return the promoHeading
	 */
	public String getPromoHeading() {
		return promoHeading;
	}

	/**
	 * @return the promoSubHeading
	 */
	public String getPromoSubHeading() {
		return promoSubHeading;
	}

	/**
	 * @return the promoDesc
	 */
	public String getPromoDesc() {
		return promoDesc;
	}

	/**
	 * @return the promoSubDesc
	 */
	public String getPromoSubDesc() {
		return promoSubDesc;
	}

	/**
	 * @return the promoCode
	 */
	public String getPromoCode() {
		return PromoCode;
	}

	/**
	 * @return the promoExpiryDate
	 */
	public String getPromoExpiryDate() {
		return promoExpiryDate;
	}
	
	/**
	 * 
  	 * @return The profileMobileNbr
  	 */
  	@JsonProperty("profileMobileNbr")
  	public String getProfileMobileNbr() {
  		return profileMobileNbr;
  	}
  
  	/**
  	 * 
  	 * @param profileMobileNbr
  	 *            The profileMobileNbr
  	 */
  	@JsonProperty("profileMobileNbr")
  	public void setProfileMobileNbr(String profileMobileNbr) {
  		this.profileMobileNbr = profileMobileNbr;
  	}
}