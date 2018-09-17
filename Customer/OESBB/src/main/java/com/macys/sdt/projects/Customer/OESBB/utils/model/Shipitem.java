package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Strings;

/**
 * @author snanda
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lineNbr", "desc", "price", "reqQuantity", "availQuantity", "shippedQuantity", "cancelledQuantity",
		"totalAmt", "splitShipmentFlag", "shipmentType", "status", "vendor", "upc", "lineWebId", "productType",
		"productName", "productSize", "productColor", "productURL", "imageType", "imagePrimaryUrl", "imageUrl",
		"expectedDate", "newExpectedDate", "delaySentDate", "delayWaitDate", "userCancelDate", "systemCancelDate",
		"vgcNbr", "vgcCidNbr", "vgcExpiryDate","returnExpectedBackDate","returnExpectedDate","fdIndicator"})
public class Shipitem {

	@JsonProperty("lineNbr")
	private String lineNbr=null;
	@JsonProperty("desc")
	private String desc=null;
	@JsonProperty("price")
	private String price=null;
	@JsonProperty("reqQuantity")
	private String reqQuantity=null;
	@JsonProperty("availQuantity")
	private String availQuantity=null;
	@JsonProperty("shippedQuantity")
	private String shippedQuantity=null;
	@JsonProperty("cancelledQuantity")
	private String cancelledQuantity=null;
	@JsonProperty("totalAmt")
	private String totalAmt=null;
	@JsonProperty("splitShipmentFlag")
	private String splitShipmentFlag=null;
	@JsonProperty("shipmentType")
	private String shipmentType=null;
	@JsonProperty("status")
	private String status=null;
	@JsonProperty("vendor")
	private String vendor=null;
	@JsonProperty("upc")
	private String upc=null;
	@JsonProperty("upcNumber")
	private String upcNumber=null;	
	@JsonProperty("lineWebId")
	private String lineWebId=null;
	@JsonProperty("fdIndicator")
	private String fdIndicator=null;
	@JsonProperty("productType")
	private String productType=null;
	@JsonProperty("productName")
	private String productName=null;
	@JsonProperty("productSize")
	private String productSize=null;
	@JsonProperty("productColor")
	private String productColor=null;
	@JsonProperty("productURL")
	private String productURL=null;
	@JsonProperty("imageType")
	private String imageType=null;
	@JsonProperty("imagePrimaryUrl")
	private String imagePrimaryUrl=null;
	@JsonProperty("imageUrl")
	private String imageUrl=null;
	@JsonProperty("expectedDate")
	private String expectedDate=null;
	@JsonProperty("newExpectedDate")
	private String newExpectedDate=null;
	@JsonProperty("delaySentDate")
	private String delaySentDate=null;
	@JsonProperty("delayWaitDate")
	private String delayWaitDate=null;
	@JsonProperty("userCancelDate")
	private String userCancelDate=null;
	@JsonProperty("systemCancelDate")
	private String systemCancelDate=null;
	@JsonProperty("vgcNbr")
	private String vgcNbr=null;
	@JsonProperty("vgcCidNbr")
	private String vgcCidNbr=null;
	@JsonProperty("vgcExpiryDate")
	private String vgcExpiryDate=null;
	@JsonProperty("returnReasonDescription")
	private String returnReasonDescription=null;
	@JsonProperty("returnReason")
	private String returnReason=null;
	@JsonProperty("returnReceiptDate")
	private String returnReceiptDate=null;
	@JsonProperty("returnExpectedDate")
	private String returnExpectedDate = null;
	@JsonProperty("returnExpectedBackDate")
	private String returnExpectedBackDate = null;
	@JsonProperty("returnStatus")
	private String returnStatus = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	@JsonProperty("lineItemDeliveryDate")
	private String lineItemDeliveryDate = null;
	/**
	 * 
	 * @return The lineNbr
	 */
	@JsonProperty("lineNbr")
	public String getLineNbr() {
		if(Strings.isNullOrEmpty(lineNbr)){return null;}
		return lineNbr;
	}

	/**
	 * 
	 * @param lineNbr
	 *            The lineNbr
	 */
	@JsonProperty("lineNbr")
	public void setLineNbr(String lineNbr) {
		this.lineNbr = lineNbr;
	}

	/**
	 * 
	 * @return The desc
	 */
	@JsonProperty("desc")
	public String getDesc() {
		if(Strings.isNullOrEmpty(desc)){return null;}
		return desc;
	}

	/**
	 * 
	 * @param desc
	 *            The desc
	 */
	@JsonProperty("desc")
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 
	 * @return The price
	 */
	@JsonProperty("price")
	public String getPrice() {
		if(Strings.isNullOrEmpty(price)){return null;}
		return price;
	}

	/**
	 * 
	 * @param price
	 *            The price
	 */
	@JsonProperty("price")
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * 
	 * @return The reqQuantity
	 */
	@JsonProperty("reqQuantity")
	public String getReqQuantity() {
		if(Strings.isNullOrEmpty(reqQuantity)){return null;}
		return reqQuantity;
	}

	/**
	 * 
	 * @param reqQuantity
	 *            The reqQuantity
	 */
	@JsonProperty("reqQuantity")
	public void setReqQuantity(String reqQuantity) {
		this.reqQuantity = reqQuantity;
	}

	/**
	 * 
	 * @return The availQuantity
	 */
	@JsonProperty("availQuantity")
	public String getAvailQuantity() {
		if(Strings.isNullOrEmpty(availQuantity)){return null;}
		return availQuantity;
	}

	/**
	 * 
	 * @param availQuantity
	 *            The availQuantity
	 */
	@JsonProperty("availQuantity")
	public void setAvailQuantity(String availQuantity) {
		this.availQuantity = availQuantity;
	}

	/**
	 * 
	 * @return The shippedQuantity
	 */
	@JsonProperty("shippedQuantity")
	public String getShippedQuantity() {
		if(Strings.isNullOrEmpty(shippedQuantity)){return null;}
		return shippedQuantity;
	}

	/**
	 * 
	 * @param shippedQuantity
	 *            The shippedQuantity
	 */
	@JsonProperty("shippedQuantity")
	public void setShippedQuantity(String shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

	/**
	 * 
	 * @return The cancelledQuantity
	 */
	@JsonProperty("cancelledQuantity")
	public String getCancelledQuantity() {
		if(Strings.isNullOrEmpty(cancelledQuantity)){return null;}
		return cancelledQuantity;
	}

	/**
	 * 
	 * @param cancelledQuantity
	 *            The cancelledQuantity
	 */
	@JsonProperty("cancelledQuantity")
	public void setCancelledQuantity(String cancelledQuantity) {
		this.cancelledQuantity = cancelledQuantity;
	}

	/**
	 * 
	 * @return The totalAmt
	 */
	@JsonProperty("totalAmt")
	public String getTotalAmt() {
		if(Strings.isNullOrEmpty(totalAmt)){return null;}
		return totalAmt;
	}

	/**
	 * 
	 * @param totalAmt
	 *            The totalAmt
	 */
	@JsonProperty("totalAmt")
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	/**
	 * 
	 * @return The splitShipmentFlag
	 */
	@JsonProperty("splitShipmentFlag")
	public String getSplitShipmentFlag() {
		if(Strings.isNullOrEmpty(splitShipmentFlag)){return null;}
		return splitShipmentFlag;
	}

	/**
	 * 
	 * @param splitShipmentFlag
	 *            The splitShipmentFlag
	 */
	@JsonProperty("splitShipmentFlag")
	public void setSplitShipmentFlag(String splitShipmentFlag) {
		this.splitShipmentFlag = splitShipmentFlag;
	}

	/**
	 * 
	 * @return The shipmentType
	 */
	@JsonProperty("shipmentType")
	public String getShipmentType() {
		if(Strings.isNullOrEmpty(shipmentType)){return null;}
		return shipmentType;
	}

	/**
	 * 
	 * @param shipmentType
	 *            The shipmentType
	 */
	@JsonProperty("shipmentType")
	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	/**
	 * 
	 * @return The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		if(Strings.isNullOrEmpty(status)){return null;}
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return The vendor
	 */
	@JsonProperty("vendor")
	public String getVendor() {
		if(Strings.isNullOrEmpty(vendor)){return null;}
		return vendor;
	}

	/**
	 * 
	 * @param vendor
	 *            The vendor
	 */
	@JsonProperty("vendor")
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * 
	 * @return The upc
	 */
	@JsonProperty("upc")
	public String getUpc() {
		if(Strings.isNullOrEmpty(upc)){return null;}
		return upc;
	}

	/**
	 * 
	 * @param upc
	 *            The upc
	 */
	@JsonProperty("upc")
	public void setUpc(String upc) {
		this.upc = upc;
	}

	/**
	 * 
	 * @return The upcNumber
	 */
	@JsonProperty("upcNumber")
	public String getUpcNumber() {
		if(Strings.isNullOrEmpty(upcNumber)){return null;}
		return upcNumber;
	}

	/**
	 * 
	 * @param upcNumber
	 *            The upcNumber
	 */
	@JsonProperty("upcNumber")
	public void setUpcNumber(String upcNumber) {
		this.upcNumber = upcNumber;
	}

	/**
	 * 
	 * @return The lineWebId
	 */
	@JsonProperty("lineWebId")
	public String getLineWebId() {
		if(Strings.isNullOrEmpty(lineWebId)){return null;}
		return lineWebId;
	}

	/**
	 * 
	 * @param lineWebId
	 *            The lineWebId
	 */
	@JsonProperty("lineWebId")
	public void setLineWebId(String lineWebId) {
		this.lineWebId = lineWebId;
	}
	/**
	 * 
	 * @return The fdIndicator
	 */
	@JsonProperty("fdIndicator")
	public String getFdIndicator() {
		if(Strings.isNullOrEmpty(fdIndicator)){return null;}
		return fdIndicator;
	}

	/**
	 * 
	 * @param fdIndicator
	 *            The fdIndicator
	 */
	@JsonProperty("fdIndicator")
	public void setFdIndicator(String fdIndicator) {
		this.fdIndicator = fdIndicator;
	}
	/**
	 * 
	 * @return The productType
	 */
	@JsonProperty("productType")
	public String getProductType() {
		if(Strings.isNullOrEmpty(productType)){return null;}
		return productType;
	}

	/**
	 * 
	 * @param productType
	 *            The productType
	 */
	@JsonProperty("productType")
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * 
	 * @return The productName
	 */
	@JsonProperty("productName")
	public String getProductName() {
		if(Strings.isNullOrEmpty(productName)){return null;}
		return productName;
	}

	/**
	 * 
	 * @param productName
	 *            The productName
	 */
	@JsonProperty("productName")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 
	 * @return The productSize
	 */
	@JsonProperty("productSize")
	public String getProductSize() {
		if(Strings.isNullOrEmpty(productSize)){return null;}
		return productSize;
	}

	/**
	 * 
	 * @param productSize
	 *            The productSize
	 */
	@JsonProperty("productSize")
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	/**
	 * 
	 * @return The productColor
	 */
	@JsonProperty("productColor")
	public String getProductColor() {
		if(Strings.isNullOrEmpty(productColor)){return null;}
		return productColor;
	}

	/**
	 * 
	 * @param productColor
	 *            The productColor
	 */
	@JsonProperty("productColor")
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	/**
	 * 
	 * @return The productURL
	 */
	@JsonProperty("productURL")
	public String getProductURL() {
		return productURL;
	}

	/**
	 * 
	 * @param productURL
	 *            The productURL
	 */
	@JsonProperty("productURL")
	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	/**
	 * 
	 * @return The imageType
	 */
	@JsonProperty("imageType")
	public String getImageType() {
		if(Strings.isNullOrEmpty(imageType)){return null;}
		return imageType;
	}

	/**
	 * 
	 * @param imageType
	 *            The imageType
	 */
	@JsonProperty("imageType")
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	/**
	 * 
	 * @return The imagePrimaryUrl
	 */
	@JsonProperty("imagePrimaryUrl")
	public String getImagePrimaryUrl() {
		return imagePrimaryUrl;
	}

	/**
	 * 
	 * @param imagePrimaryUrl
	 *            The imagePrimaryUrl
	 */
	@JsonProperty("imagePrimaryUrl")
	public void setImagePrimaryUrl(String imagePrimaryUrl) {
		this.imagePrimaryUrl = imagePrimaryUrl;
	}

	/**
	 * 
	 * @return The imageUrl
	 */
	@JsonProperty("imageUrl")
	public String getImageUrl() {
		if(Strings.isNullOrEmpty(imageUrl)){return null;}
		return imageUrl;
	}

	/**
	 * 
	 * @param imageUrl
	 *            The imageUrl
	 */
	@JsonProperty("imageUrl")
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 
	 * @return The expectedDate
	 */
	@JsonProperty("expectedDate")
	public String getExpectedDate() {
		return expectedDate;
	}

	/**
	 * 
	 * @param expectedDate
	 *            The expectedDate
	 */
	@JsonProperty("expectedDate")
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	/**
	 * 
	 * @return The newExpectedDate
	 */
	@JsonProperty("newExpectedDate")
	public String getNewExpectedDate() {
		return newExpectedDate;
	}

	/**
	 * 
	 * @param newExpectedDate
	 *            The newExpectedDate
	 */
	@JsonProperty("newExpectedDate")
	public void setNewExpectedDate(String newExpectedDate) {
		this.newExpectedDate = newExpectedDate;
	}

	/**
	 * 
	 * @return The delaySentDate
	 */
	@JsonProperty("delaySentDate")
	public String getDelaySentDate() {
		return delaySentDate;
	}

	/**
	 * 
	 * @param delaySentDate
	 *            The delaySentDate
	 */
	@JsonProperty("delaySentDate")
	public void setDelaySentDate(String delaySentDate) {
		this.delaySentDate = delaySentDate;
	}

	/**
	 * 
	 * @return The delayWaitDate
	 */
	@JsonProperty("delayWaitDate")
	public String getDelayWaitDate() {
		return delayWaitDate;
	}

	/**
	 * 
	 * @param delayWaitDate
	 *            The delayWaitDate
	 */
	@JsonProperty("delayWaitDate")
	public void setDelayWaitDate(String delayWaitDate) {
		this.delayWaitDate = delayWaitDate;
	}

	/**
	 * 
	 * @return The userCancelDate
	 */
	@JsonProperty("userCancelDate")
	public String getUserCancelDate() {
		return userCancelDate;
	}

	/**
	 * 
	 * @param userCancelDate
	 *            The userCancelDate
	 */
	@JsonProperty("userCancelDate")
	public void setUserCancelDate(String userCancelDate) {
		this.userCancelDate = userCancelDate;
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
	 * @return The vgcNbr
	 */
	@JsonProperty("vgcNbr")
	public String getVgcNbr() {
		return vgcNbr;
	}

	/**
	 * 
	 * @param vgcNbr
	 *            The vgcNbr
	 */
	@JsonProperty("vgcNbr")
	public void setVgcNbr(String vgcNbr) {
		this.vgcNbr = vgcNbr;
	}

	/**
	 * 
	 * @return The vgcCidNbr
	 */
	@JsonProperty("vgcCidNbr")
	public String getVgcCidNbr() {
		return vgcCidNbr;
	}

	/**
	 * 
	 * @param vgcCidNbr
	 *            The vgcCidNbr
	 */
	@JsonProperty("vgcCidNbr")
	public void setVgcCidNbr(String vgcCidNbr) {
		this.vgcCidNbr = vgcCidNbr;
	}

	/**
	 * 
	 * @return The vgcExpiryDate
	 */
	@JsonProperty("vgcExpiryDate")
	public String getVgcExpiryDate() {
		return vgcExpiryDate;
	}

	/**
	 * 
	 * @param vgcExpiryDate
	 *            The vgcExpiryDate
	 */
	@JsonProperty("vgcExpiryDate")
	public void setVgcExpiryDate(String vgcExpiryDate) {
		this.vgcExpiryDate = vgcExpiryDate;
	}
	
	/**
	 * 
	 * @return The returnReasonDescription
	 */
	@JsonProperty("returnReasonDescription")
	public String getReturnReasonDescription() {
		return returnReasonDescription;
	}

	/**
	 * 
	 * @param returnReasonDescription
	 *            The returnReasonDescription
	 */
	@JsonProperty("returnReasonDescription")
	public void setReturnReasonDescription(String returnReasonDescription) {
		this.returnReasonDescription = returnReasonDescription;
	}
	
	
	/**
	 * 
	 * @return The returnReason
	 */
	@JsonProperty("returnReason")
	public String getReturnReason() {
		return returnReason;
	}

	/**
	 * 
	 * @param returnReasonDescription
	 *            The returnReasonDescription
	 */
	@JsonProperty("returnReason")
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	
	/**
	 * 
	 * @return The returnReceiptDate
	 */
	@JsonProperty("returnReceiptDate")
	public String getReturnReceiptDate() {
		return returnReceiptDate;
	}

	/**
	 * 
	 * @param returnReceiptDate
	 *            The returnReceiptDate
	 */
	@JsonProperty("returnReceiptDate")
	public void setReturnReceiptDate(String returnReceiptDate) {
		this.returnReceiptDate = returnReceiptDate;
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
	 * @return The returnExpectedDate
	 */
	@JsonProperty("returnExpectedDate")
	public String getReturnExpectedDate() {
		return returnExpectedDate;
	}
	
	/**
	 * 
	 * @param returnExpectedDate
	 *            The returnExpectedDate
	 */
	@JsonProperty("returnExpectedDate")
	public void setReturnExpectedDate(String returnExpectedDate) {
		this.returnExpectedDate = returnExpectedDate;
	}
	
	
	/**
	 * 
	 * @return The returnStatus
	 */
	@JsonProperty("returnStatus")
	public String getReturnStatus() {
		return returnStatus;
	}

	/**
	 * 
	 * @param returnStatus
	 *            The returnStatus
	 */
	@JsonProperty("returnStatus")
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	/**
	 * 
	 * @return The returnExpectedBackDate
	 */
	
	@JsonProperty("returnExpectedBackDate")
	public String getReturnExpectedBackDate() {
		return returnExpectedBackDate;
	}

	/**
	 * 
	 * @param returnExpectedBackDate
	 *            The returnExpectedBackDate
	 */
	@JsonProperty("returnExpectedBackDate")
	public void setReturnExpectedBackDate(String returnExpectedBackDate) {
		this.returnExpectedBackDate = returnExpectedBackDate;
	}
	
	@JsonProperty("lineItemDeliveryDate")
	public String getLineItemDeliveryDate() {
		return lineItemDeliveryDate;
	}

	/**
	 * 
	 * @param lineItemDeliveryDate
	 *            The lineItemDeliveryDate
	 */
	@JsonProperty("lineItemDeliveryDate")
	public void setLineItemDeliveryDate(String lineItemDeliveryDate) {
		this.lineItemDeliveryDate = lineItemDeliveryDate;
	}
}
