package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "subTotalAmt", "baseShippingAmt", "addtShipmentFeeAmt", "shipmentUpgradeFeeAmt", "giftWrapFeeAmt", "taxAmt",
		"orderTotal", "munTaxAmt", "miscTaxAmt" })

public class OrderTotals {
	
	@JsonProperty("subTotalAmt")
	private String subTotalAmt;
	@JsonProperty("baseShippingAmt")
	private String baseShippingAmt;
	@JsonProperty("addtShipmentFeeAmt")
	private String addtShipmentFeeAmt;
	@JsonProperty("shipmentUpgradeFeeAmt")
	private String shipmentUpgradeFeeAmt;
	@JsonProperty("giftWrapFeeAmt")
	private String giftWrapFeeAmt;
	@JsonProperty("taxAmt")
	private String taxAmt;
	@JsonProperty("orderTotal")
	private String orderTotal;
	@JsonProperty("munTaxAmt")
	private String munTaxAmt;
	@JsonProperty("miscTaxAmt")
	private String miscTaxAmt;
	@JsonProperty("autoProvisioned")
	private Boolean autoProvisioned;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The subTotalAmt
	 */
	@JsonProperty("subTotalAmt")
	public String getSubTotalAmt() {
		return subTotalAmt;
	}

	/**
	 * 
	 * @param subTotalAmt
	 *            The subTotalAmt
	 */
	@JsonProperty("subTotalAmt")
	public void getSubTotalAmt(String subTotalAmt) {
		this.subTotalAmt = subTotalAmt;
	}

	/**
	 * 
	 * @return The baseShippingAmt
	 */
	@JsonProperty("baseShippingAmt")
	public String getBaseShippingAmt() {
		return baseShippingAmt;
	}

	/**
	 * 
	 * @param baseShippingAmt
	 *            The baseShippingAmt
	 */
	@JsonProperty("baseShippingAmt")
	public void getBaseShippingAmt(String baseShippingAmt) {
		this.baseShippingAmt = baseShippingAmt;
	}

	/**
	 * 
	 * @return The addtShipmentFeeAmt
	 */
	@JsonProperty("addtShipmentFeeAmt")
	public String getAdditionalShipmentFeeAmount() {
		return addtShipmentFeeAmt;
	}

	/**
	 * 
	 * @param addtShipmentFeeAmt
	 *            The addtShipmentFeeAmt
	 */
	@JsonProperty("addtShipmentFeeAmt")
	public void setAdditionalShipmentFeeAmount(String addtShipmentFeeAmt) {
		this.addtShipmentFeeAmt = addtShipmentFeeAmt;
	}

	/**
	 * 
	 * @return The shipmentUpgradeFeeAmt
	 */
	@JsonProperty("shipmentUpgradeFeeAmt")
	public String getShipmentUpgradeFeeAmount() {
		return shipmentUpgradeFeeAmt;
	}

	/**
	 * 
	 * @param shipmentUpgradeFeeAmt
	 *            The shipmentUpgradeFeeAmt
	 */
	@JsonProperty("shipmentUpgradeFeeAmt")
	public void setShipmentUpgradeFeeAmount(String shipmentUpgradeFeeAmt) {
		this.shipmentUpgradeFeeAmt = shipmentUpgradeFeeAmt;
	}

	/**
	 * 
	 * @return The giftWrapFeeAmt
	 */
	@JsonProperty("giftWrapFeeAmt")
	public String getGiftWrapFeeAmt() {
		return giftWrapFeeAmt;
	}

	/**
	 * 
	 * @param giftWrapFeeAmt
	 *            The giftWrapFeeAmt
	 */
	@JsonProperty("giftWrapFeeAmt")
	public void getGiftWrapFeeAmt(String giftWrapFeeAmt) {
		this.giftWrapFeeAmt = giftWrapFeeAmt;
	}

	/**
	 * 
	 * @return The taxAmt
	 */
	@JsonProperty("taxAmt")
	public String getSalesTaxAmount() {
		return taxAmt;
	}

	/**
	 * 
	 * @param taxAmt
	 *            The taxAmt
	 */
	@JsonProperty("taxAmt")
	public void setSalesTaxAmount(String taxAmt) {
		this.taxAmt = taxAmt;
	}

	/**
	 * 
	 * @return The orderTotal
	 */
	@JsonProperty("orderTotal")
	public String getOrderTotal() {
		return orderTotal;
	}

	/**
	 * 
	 * @param orderTotal
	 *            The orderTotal
	 */
	@JsonProperty("orderTotal")
	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * 
	 * @return The munTaxAmt
	 */
	@JsonProperty("munTaxAmt")
	public String getMuntaxAmount() {
		return munTaxAmt;
	}

	/**
	 * 
	 * @param munTaxAmt
	 *            The munTaxAmt
	 */
	@JsonProperty("munTaxAmt")
	public void setMuntaxAmount(String munTaxAmt) {
		this.munTaxAmt = munTaxAmt;
	}

	/**
	 * 
	 * @return The miscTaxAmt
	 */
	@JsonProperty("miscTaxAmt")
	public String getMiscTaxAmount() {
		return miscTaxAmt;
	}

	/**
	 * 
	 * @param miscTaxAmt
	 *            The miscTaxAmt
	 */
	@JsonProperty("miscTaxAmt")
	public void setMiscTaxAmount(String miscTaxAmt) {
		this.miscTaxAmt = miscTaxAmt;
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
