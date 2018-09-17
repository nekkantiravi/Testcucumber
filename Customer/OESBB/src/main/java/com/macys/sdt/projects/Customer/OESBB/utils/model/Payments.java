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
@JsonPropertyOrder({ "type", "cardNbr", "chargeAmt", "categoryFlag", "egcCertificateType" })
public class Payments {

	@JsonProperty("type")
	private String type;
	@JsonProperty("cardNbr")
	private String cardNbr;
	@JsonProperty("chargeAmt")
	private String chargeAmt;
	@JsonProperty("egcCertificateType")
	private String egcCertificateType;
	@JsonProperty("categoryFlag")
	private String categoryFlag;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The cardNbr
	 */
	@JsonProperty("cardNbr")
	public String getCardNbr() {
		return cardNbr;
	}

	/**
	 * 
	 * @param cardNbr
	 *            The cardNbr
	 */
	@JsonProperty("cardNbr")
	public void setCardNbr(String cardNbr) {
		this.cardNbr = cardNbr;
	}

	/**
	 * 
	 * @return The chargeAmt
	 */
	@JsonProperty("chargeAmt")
	public String getChargeAmt() {
		return chargeAmt;
	}

	/**
	 * 
	 * @param chargeAmt
	 *            The chargeAmt
	 */
	@JsonProperty("chargeAmt")
	public void setChargeAmt(String chargeAmt) {
		this.chargeAmt = chargeAmt;
	}
	
	/**
	 * 
	 * @return The egcCertificateType
	 */
	@JsonProperty("egcCertificateType")
	public String getEgcCertificateType() {
		return egcCertificateType;
	}

	/**
	 * 
	 * @param egcCertificateType
	 *            The egcCertificateType
	 */
	@JsonProperty("egcCertificateType")
	public void setEgcCertificateType(String egcCertificateType) {
		this.egcCertificateType = egcCertificateType;
	}
	
	/**
	 * 
	 * @return The categoryFlag
	 */
	@JsonProperty("categoryFlag")
	public String getCategoryFlag() {
		return categoryFlag;
	}

	/**
	 * 
	 * @param categoryFlag
	 *            The categoryFlag
	 */
	@JsonProperty("categoryFlag")
	public void setCategoryFlag(String categoryFlag) {
		this.categoryFlag = categoryFlag;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}