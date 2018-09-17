package com.macys.sdt.projects.Customer.OESBB.utils.input.eod;

import java.util.Date;


public class OrderHeader {
	private String orderID;
	private String dataCenterCode;
	private String orderStatusCode;
	private String orderStatusDescription;
	private Date orderConfirmTimestamp;
	private Date orderConfirmUtcTimestamp;
	private String sellingChannelCode;
	private String sellLegacyDivisionNbr;
	private String sellLegacyLocationNbr;
	private String salesAssociateId;
	private Date messageCreateTimestamp;
	private Date messageCreateUtcTimestamp;
	private String eventType;
	private Date eventTimestamp;
	private Date eventUtcTimestamp;
	private PartnerRefList partnerRefList;
	private BillingAddress billingAddress;
	private String orderManagementApplicationCode;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDataCenterCode() {
		return dataCenterCode;
	}

	public void setDataCenterCode(String dataCenterCode) {
		this.dataCenterCode = dataCenterCode;
	}

	public String getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}

	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}

	public Date getOrderConfirmTimestamp() {
		return orderConfirmTimestamp;
	}

	public void setOrderConfirmTimestamp(Date orderConfirmTimestamp) {
		this.orderConfirmTimestamp = orderConfirmTimestamp;
	}

	public Date getOrderConfirmUtcTimestamp() {
		return orderConfirmUtcTimestamp;
	}

	public void setOrderConfirmUtcTimestamp(Date orderConfirmUtcTimestamp) {
		this.orderConfirmUtcTimestamp = orderConfirmUtcTimestamp;
	}

	public String getSellingChannelCode() {
		return sellingChannelCode;
	}

	public void setSellingChannelCode(String sellingChannelCode) {
		this.sellingChannelCode = sellingChannelCode;
	}

	public String getSellLegacyDivisionNbr() {
		return sellLegacyDivisionNbr;
	}

	public void setSellLegacyDivisionNbr(String sellLegacyDivisionNbr) {
		this.sellLegacyDivisionNbr = sellLegacyDivisionNbr;
	}

	public String getSellLegacyLocationNbr() {
		return sellLegacyLocationNbr;
	}

	public void setSellLegacyLocationNbr(String sellLegacyLocationNbr) {
		this.sellLegacyLocationNbr = sellLegacyLocationNbr;
	}

	public String getSalesAssociateId() {
		return salesAssociateId;
	}

	public void setSalesAssociateId(String salesAssociateId) {
		this.salesAssociateId = salesAssociateId;
	}

	public Date getMessageCreateTimestamp() {
		return messageCreateTimestamp;
	}

	public void setMessageCreateTimestamp(Date messageCreateTimestamp) {
		this.messageCreateTimestamp = messageCreateTimestamp;
	}

	public Date getMessageCreateUtcTimestamp() {
		return messageCreateUtcTimestamp;
	}

	public void setMessageCreateUtcTimestamp(Date messageCreateUtcTimestamp) {
		this.messageCreateUtcTimestamp = messageCreateUtcTimestamp;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(Date eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public Date getEventUtcTimestamp() {
		return eventUtcTimestamp;
	}

	public void setEventUtcTimestamp(Date eventUtcTimestamp) {
		this.eventUtcTimestamp = eventUtcTimestamp;
	}

	public PartnerRefList getPartnerRefList() {
		return partnerRefList;
	}

	public void setPartnerRefList(PartnerRefList partnerRefList) {
		this.partnerRefList = partnerRefList;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getOrderManagementApplicationCode() {
		return orderManagementApplicationCode;
	}

	public void setOrderManagementApplicationCode(String orderManagementApplicationCode) {
		this.orderManagementApplicationCode = orderManagementApplicationCode;
	}

}
