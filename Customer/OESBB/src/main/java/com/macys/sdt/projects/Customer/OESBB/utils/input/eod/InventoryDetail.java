package com.macys.sdt.projects.Customer.OESBB.utils.input.eod;

import java.util.Date;


public class InventoryDetail {
	private String orderID;
	private String shipmentNbr;
	private String inventoryDeltaActionCode;
	private String lineItemNbr;
	private String orderManagementSenderCode;
	private Date inventoryDeltaDateTime;
	private Date inventoryDeltaUtcDateTime;
	private String mediaDate;
	private String processName;
	private String racfID;
	private String locationNbr;
	private String itemDepartmentNbr;
	private String itemSkuUpc;
	private String quantityAmount;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getShipmentNbr() {
		return shipmentNbr;
	}

	public void setShipmentNbr(String shipmentNbr) {
		this.shipmentNbr = shipmentNbr;
	}

	public String getInventoryDeltaActionCode() {
		return inventoryDeltaActionCode;
	}

	public void setInventoryDeltaActionCode(String inventoryDeltaActionCode) {
		this.inventoryDeltaActionCode = inventoryDeltaActionCode;
	}

	public String getLineItemNbr() {
		return lineItemNbr;
	}

	public void setLineItemNbr(String lineItemNbr) {
		this.lineItemNbr = lineItemNbr;
	}

	public String getOrderManagementSenderCode() {
		return orderManagementSenderCode;
	}

	public void setOrderManagementSenderCode(String orderManagementSenderCode) {
		this.orderManagementSenderCode = orderManagementSenderCode;
	}

	public Date getInventoryDeltaDateTime() {
		return inventoryDeltaDateTime;
	}

	public void setInventoryDeltaDateTime(Date inventoryDeltaDateTime) {
		this.inventoryDeltaDateTime = inventoryDeltaDateTime;
	}

	public Date getInventoryDeltaUtcDateTime() {
		return inventoryDeltaUtcDateTime;
	}

	public void setInventoryDeltaUtcDateTime(Date inventoryDeltaUtcDateTime) {
		this.inventoryDeltaUtcDateTime = inventoryDeltaUtcDateTime;
	}

	public String getMediaDate() {
		return mediaDate;
	}

	public void setMediaDate(String mediaDate) {
		this.mediaDate = mediaDate;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getRacfID() {
		return racfID;
	}

	public void setRacfID(String racfID) {
		this.racfID = racfID;
	}

	public String getLocationNbr() {
		return locationNbr;
	}

	public void setLocationNbr(String locationNbr) {
		this.locationNbr = locationNbr;
	}

	public String getItemDepartmentNbr() {
		return itemDepartmentNbr;
	}

	public void setItemDepartmentNbr(String itemDepartmentNbr) {
		this.itemDepartmentNbr = itemDepartmentNbr;
	}

	public String getItemSkuUpc() {
		return itemSkuUpc;
	}

	public void setItemSkuUpc(String itemSkuUpc) {
		this.itemSkuUpc = itemSkuUpc;
	}

	public String getQuantityAmount() {
		return quantityAmount;
	}

	public void setQuantityAmount(String quantityAmount) {
		this.quantityAmount = quantityAmount;
	}
}
