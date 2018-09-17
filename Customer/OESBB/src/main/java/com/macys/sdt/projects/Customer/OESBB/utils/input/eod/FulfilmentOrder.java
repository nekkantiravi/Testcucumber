package com.macys.sdt.projects.Customer.OESBB.utils.input.eod;


public class FulfilmentOrder {
	private OrderHeader orderHeader;
	private LineItemList lineItemList;
	private ShipmentList shipmentList;
	private InventoryDetailList inventoryDeltaList;
	private CustomerCommunicationList customerCommunicationList;
	private OrderFraudInformation orderFraudInformation;

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public LineItemList getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(LineItemList lineItemList) {
		this.lineItemList = lineItemList;
	}

	public ShipmentList getShipmentList() {
		return shipmentList;
	}

	public void setShipmentList(ShipmentList shipmentList) {
		this.shipmentList = shipmentList;
	}

	public InventoryDetailList getInventoryDeltaList() {
		return inventoryDeltaList;
	}

	public void setInventoryDeltaList(InventoryDetailList inventoryDeltaList) {
		this.inventoryDeltaList = inventoryDeltaList;
	}

	public CustomerCommunicationList getCustomerCommunicationList() {
		return customerCommunicationList;
	}

	public void setCustomerCommunicationList(CustomerCommunicationList customerCommunicationList) {
		this.customerCommunicationList = customerCommunicationList;
	}

	public OrderFraudInformation getOrderFraudInformation() {
		return orderFraudInformation;
	}

	public void setOrderFraudInformation(OrderFraudInformation orderFraudInformation) {
		this.orderFraudInformation = orderFraudInformation;
	}
}
