package com.macys.sdt.projects.Customer.OESBB.utils.input.eod;

import java.util.Date;


public class Shipment {
	private String orderShipmentNbr;
	private String shipStatusCode;
	private String shipStatusDescription;
	private String shipmentType;
	private String shipmentTypeDescription;
	private String sellingChannelCode;
	private String fulfillmentLocationNbr;
	private String fulfillmentLegacyDivisionNbr;
	private String fulfillmentLegacyLocationNbr;
	private String shipmentFeeSlideScaleBaseAmount;
	private String totalAmount;
	private String taxAmount;
	private String retailAmount;
	private String retailTaxAmount;
	private String shippingChargeAmount;
	private String shippingTaxAmount;
	private String upChargeFeeAmount;
	private String giftWrapFeeAmount;
	private String giftWrapTaxAmount;
	private String additionalAddressFeeAmount;
	private String miscellaneousTaxAmount;
	private String giftFlag;
	private String giftWrapType;
	private String giftMessageGreetingText;
	private String giftCloseDescription;
	private String giftSignDescription;
	private String deliveryType;
	private String deliveryTypeDescription;
	private String deliveryPromotionType;
	private String estimatedArrivalDate;
	private String expectedShipDate;
	private String expectedShipTime;
	private String holdDate;
	private String actualShipDate;
	private String partnerCustomerServicePhone;
	private String partnerCustomerServiceHours;
	private String partnerCustomerServiceFraudPhone;
	private String partnerCustomerServiceFraudHours;
	private String partnerCustomerServiceName;
	private String currentShipmentIndicator;
	private DeliveryTrkList deliveryTrkList;
	private TenderList tenderList;
	private ShippingAddress shippingAddress;
	private AlternatePickupPerson alternatePickupPerson;
	private String giftWrapFlag;

	private String giftRegistryNbr;
	private String giftRegistrant1;
	private String giftRegistrant2;
	private Date readyToPickUpTimestamp;
	private Date readyToPickUpUtcTimestamp;
	private Date actualPickUpTimestamp;
	private String pickUpScheduleCancelDate;
	private String pickUpCancelDate;
	private String pickUpBarCode;
	private Date actualDeliveryTimestamp;
	
	public Date getActualDeliveryTimestamp() {
		return actualDeliveryTimestamp;
	}

	public void setActualDeliveryTimestamp(Date actualDeliveryTimestamp) {
		this.actualDeliveryTimestamp = actualDeliveryTimestamp;
	}

	public String getGiftRecipientEmail() {
		return giftRecipientEmail;
	}

	public void setGiftRecipientEmail(String giftRecipientEmail) {
		this.giftRecipientEmail = giftRecipientEmail;
	}

	private String giftRecipientEmail;
	
	public PickupPerson getPickupPerson() {
		return pickupPerson;
	}

	public void setPickupPerson(PickupPerson pickupPerson) {
		this.pickupPerson = pickupPerson;
	}

	private PickupPerson pickupPerson;

	public String getPickUpBarCode() {
		return pickUpBarCode;
	}

	public void setPickUpBarCode(String pickUpBarCode) {
		this.pickUpBarCode = pickUpBarCode;
	}

	public String getPickUpInstructionText() {
		return pickUpInstructionText;
	}

	public void setPickUpInstructionText(String pickUpInstructionText) {
		this.pickUpInstructionText = pickUpInstructionText;
	}

	private String pickUpInstructionText;

	public String getPickUpCancelDate() {
		return pickUpCancelDate;
	}

	public void setPickUpCancelDate(String pickUpCancelDate) {
		this.pickUpCancelDate = pickUpCancelDate;
	}

	public String getPickUpScheduleCancelDate() {
		return pickUpScheduleCancelDate;
	}

	public void setPickUpScheduleCancelDate(String pickUpScheduleCancelDate) {
		this.pickUpScheduleCancelDate = pickUpScheduleCancelDate;
	}

	public Date getActualPickUpTimestamp() {
		return actualPickUpTimestamp;
	}

	public void setActualPickUpTimestamp(Date actualPickUpTimestamp) {
		this.actualPickUpTimestamp = actualPickUpTimestamp;
	}

	public Date getReadyToPickUpUtcTimestamp() {
		return readyToPickUpUtcTimestamp;
	}

	public void setReadyToPickUpUtcTimestamp(Date readyToPickUpUtcTimestamp) {
		this.readyToPickUpUtcTimestamp = readyToPickUpUtcTimestamp;
	}

	public Date getReadyToPickUpTimestamp() {
		return readyToPickUpTimestamp;
	}

	public void setReadyToPickUpTimestamp(Date readyToPickUpTimestamp) {
		this.readyToPickUpTimestamp = readyToPickUpTimestamp;
	}

	public String getGiftRegistrant2() {
		return giftRegistrant2;
	}

	public void setGiftRegistrant2(String giftRegistrant2) {
		this.giftRegistrant2 = giftRegistrant2;
	}

	public String getGiftRegistrant1() {
		return giftRegistrant1;
	}

	public void setGiftRegistrant1(String giftRegistrant1) {
		this.giftRegistrant1 = giftRegistrant1;
	}

	public String getGiftRegistryNbr() {
		return giftRegistryNbr;
	}

	public void setGiftRegistryNbr(String giftRegistryNbr) {
		this.giftRegistryNbr = giftRegistryNbr;
	}

	public String getOrderShipmentNbr() {
		return orderShipmentNbr;
	}

	public void setOrderShipmentNbr(String orderShipmentNbr) {
		this.orderShipmentNbr = orderShipmentNbr;
	}

	public String getShipStatusCode() {
		return shipStatusCode;
	}

	public void setShipStatusCode(String shipStatusCode) {
		this.shipStatusCode = shipStatusCode;
	}

	public String getShipStatusDescription() {
		return shipStatusDescription;
	}

	public void setShipStatusDescription(String shipStatusDescription) {
		this.shipStatusDescription = shipStatusDescription;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getShipmentTypeDescription() {
		return shipmentTypeDescription;
	}

	public void setShipmentTypeDescription(String shipmentTypeDescription) {
		this.shipmentTypeDescription = shipmentTypeDescription;
	}

	public String getSellingChannelCode() {
		return sellingChannelCode;
	}

	public void setSellingChannelCode(String sellingChannelCode) {
		this.sellingChannelCode = sellingChannelCode;
	}

	public String getFulfillmentLocationNbr() {
		return fulfillmentLocationNbr;
	}

	public void setFulfillmentLocationNbr(String fulfillmentLocationNbr) {
		this.fulfillmentLocationNbr = fulfillmentLocationNbr;
	}

	public String getFulfillmentLegacyDivisionNbr() {
		return fulfillmentLegacyDivisionNbr;
	}

	public void setFulfillmentLegacyDivisionNbr(String fulfillmentLegacyDivisionNbr) {
		this.fulfillmentLegacyDivisionNbr = fulfillmentLegacyDivisionNbr;
	}

	public String getFulfillmentLegacyLocationNbr() {
		return fulfillmentLegacyLocationNbr;
	}

	public void setFulfillmentLegacyLocationNbr(String fulfillmentLegacyLocationNbr) {
		this.fulfillmentLegacyLocationNbr = fulfillmentLegacyLocationNbr;
	}

	public String getShipmentFeeSlideScaleBaseAmount() {
		return shipmentFeeSlideScaleBaseAmount;
	}

	public void setShipmentFeeSlideScaleBaseAmount(String shipmentFeeSlideScaleBaseAmount) {
		this.shipmentFeeSlideScaleBaseAmount = shipmentFeeSlideScaleBaseAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getRetailAmount() {
		return retailAmount;
	}

	public void setRetailAmount(String retailAmount) {
		this.retailAmount = retailAmount;
	}

	public String getRetailTaxAmount() {
		return retailTaxAmount;
	}

	public void setRetailTaxAmount(String retailTaxAmount) {
		this.retailTaxAmount = retailTaxAmount;
	}

	public String getShippingChargeAmount() {
		return shippingChargeAmount;
	}

	public void setShippingChargeAmount(String shippingChargeAmount) {
		this.shippingChargeAmount = shippingChargeAmount;
	}

	public String getShippingTaxAmount() {
		return shippingTaxAmount;
	}

	public void setShippingTaxAmount(String shippingTaxAmount) {
		this.shippingTaxAmount = shippingTaxAmount;
	}

	public String getUpChargeFeeAmount() {
		return upChargeFeeAmount;
	}

	public void setUpChargeFeeAmount(String upChargeFeeAmount) {
		this.upChargeFeeAmount = upChargeFeeAmount;
	}

	public String getGiftWrapFeeAmount() {
		return giftWrapFeeAmount;
	}

	public void setGiftWrapFeeAmount(String giftWrapFeeAmount) {
		this.giftWrapFeeAmount = giftWrapFeeAmount;
	}

	public String getGiftWrapTaxAmount() {
		return giftWrapTaxAmount;
	}

	public void setGiftWrapTaxAmount(String giftWrapTaxAmount) {
		this.giftWrapTaxAmount = giftWrapTaxAmount;
	}

	public String getAdditionalAddressFeeAmount() {
		return additionalAddressFeeAmount;
	}

	public void setAdditionalAddressFeeAmount(String additionalAddressFeeAmount) {
		this.additionalAddressFeeAmount = additionalAddressFeeAmount;
	}

	public String getMiscellaneousTaxAmount() {
		return miscellaneousTaxAmount;
	}

	public void setMiscellaneousTaxAmount(String miscellaneousTaxAmount) {
		this.miscellaneousTaxAmount = miscellaneousTaxAmount;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}

	public String getGiftWrapType() {
		return giftWrapType;
	}

	public void setGiftWrapType(String giftWrapType) {
		this.giftWrapType = giftWrapType;
	}

	public String getGiftMessageGreetingText() {
		return giftMessageGreetingText;
	}

	public void setGiftMessageGreetingText(String giftMessageGreetingText) {
		this.giftMessageGreetingText = giftMessageGreetingText;
	}

	public String getGiftCloseDescription() {
		return giftCloseDescription;
	}

	public void setGiftCloseDescription(String giftCloseDescription) {
		this.giftCloseDescription = giftCloseDescription;
	}

	public String getGiftSignDescription() {
		return giftSignDescription;
	}

	public void setGiftSignDescription(String giftSignDescription) {
		this.giftSignDescription = giftSignDescription;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryTypeDescription() {
		return deliveryTypeDescription;
	}

	public void setDeliveryTypeDescription(String deliveryTypeDescription) {
		this.deliveryTypeDescription = deliveryTypeDescription;
	}

	public String getDeliveryPromotionType() {
		return deliveryPromotionType;
	}

	public void setDeliveryPromotionType(String deliveryPromotionType) {
		this.deliveryPromotionType = deliveryPromotionType;
	}

	public String getEstimatedArrivalDate() {
		return estimatedArrivalDate;
	}

	public void setEstimatedArrivalDate(String estimatedArrivalDate) {
		this.estimatedArrivalDate = estimatedArrivalDate;
	}

	public String getExpectedShipDate() {
		return expectedShipDate;
	}

	public void setExpectedShipDate(String expectedShipDate) {
		this.expectedShipDate = expectedShipDate;
	}

	public String getExpectedShipTime() {
		return expectedShipTime;
	}

	public void setExpectedShipTime(String expectedShipTime) {
		this.expectedShipTime = expectedShipTime;
	}

	public String getHoldDate() {
		return holdDate;
	}

	public void setHoldDate(String holdDate) {
		this.holdDate = holdDate;
	}

	public String getActualShipDate() {
		return actualShipDate;
	}

	public void setActualShipDate(String actualShipDate) {
		this.actualShipDate = actualShipDate;
	}

	public String getPartnerCustomerServicePhone() {
		return partnerCustomerServicePhone;
	}

	public void setPartnerCustomerServicePhone(String partnerCustomerServicePhone) {
		this.partnerCustomerServicePhone = partnerCustomerServicePhone;
	}

	public String getPartnerCustomerServiceHours() {
		return partnerCustomerServiceHours;
	}

	public void setPartnerCustomerServiceHours(String partnerCustomerServiceHours) {
		this.partnerCustomerServiceHours = partnerCustomerServiceHours;
	}

	public String getPartnerCustomerServiceFraudPhone() {
		return partnerCustomerServiceFraudPhone;
	}

	public void setPartnerCustomerServiceFraudPhone(String partnerCustomerServiceFraudPhone) {
		this.partnerCustomerServiceFraudPhone = partnerCustomerServiceFraudPhone;
	}

	public String getPartnerCustomerServiceFraudHours() {
		return partnerCustomerServiceFraudHours;
	}

	public void setPartnerCustomerServiceFraudHours(String partnerCustomerServiceFraudHours) {
		this.partnerCustomerServiceFraudHours = partnerCustomerServiceFraudHours;
	}

	public String getPartnerCustomerServiceName() {
		return partnerCustomerServiceName;
	}

	public void setPartnerCustomerServiceName(String partnerCustomerServiceName) {
		this.partnerCustomerServiceName = partnerCustomerServiceName;
	}

	public String getCurrentShipmentIndicator() {
		return currentShipmentIndicator;
	}

	public void setCurrentShipmentIndicator(String currentShipmentIndicator) {
		this.currentShipmentIndicator = currentShipmentIndicator;
	}

	public DeliveryTrkList getDeliveryTrkList() {
		return deliveryTrkList;
	}

	public void setDeliveryTrkList(DeliveryTrkList deliveryTrkList) {
		this.deliveryTrkList = deliveryTrkList;
	}

	public TenderList getTenderList() {
		return tenderList;
	}

	public void setTenderList(TenderList tenderList) {
		this.tenderList = tenderList;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public AlternatePickupPerson getAlternatePickupPerson() {
		return alternatePickupPerson;
	}

	public void setAlternatePickupPerson(AlternatePickupPerson alternatePickupPerson) {
		this.alternatePickupPerson = alternatePickupPerson;
	}
	
	public String getGiftWrapFlag() {
		return giftWrapFlag;
	}

	public void setGiftWrapFlag(String giftWrapFlag) {
		this.giftWrapFlag = giftWrapFlag;
	}

}
