package com.macys.sdt.projects.Customer.OESBB.utils.input.orderadjustment;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="internetOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="thirdPartyOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneNbrCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hoursCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneNbrFP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hoursFP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderCancelTime" type="{http://www.w3.org/2001/XMLSchema}string"/> 
 *         &lt;element name="smsFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ltyFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ltyIdUsl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shopAt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="explanationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="replyToEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="replyToDisplayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderSubtotalAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baseShippingAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addtShipAddAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipUpgradeAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="giftWrapAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taxAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderTotal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="divisionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adhocText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loyaltyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loyaltyFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loyaltyMiddleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loyaltyLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loyaltyContract" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="division" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="enrollmentConfirmation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="enrollmentLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addedByApp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amountRewardsCard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rewardCardExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vrcBarCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rewardCardEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="linkToWallet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rewardCardEarnEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingDetail">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="attnLine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="addressLine3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zipcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="paymentDetail">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                  &lt;element name="commercialCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="paymentCategoryFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="egcCertificateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="shipmentDetail">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="shipmentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="pickupBarcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shippedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="pickupReadyDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="pickupCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shipRetailAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shipTaxAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shipFilLoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="mailFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shipmentMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="attnLine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="addressLine3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zipcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shipPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shipEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="greetingMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="closingMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="signatureMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="giftRegistryNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="giftWrapFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="giftReceipientEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="giftRegistrant1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="giftRegistrant2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="giftReceiptFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="trackingDetail">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="trackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="numberofPackages" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="itemDetail">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="lineNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="requestedQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="availableQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="shippedQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="expectedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="newExpectedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="delaySentDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="delayWaitDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="systemCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="shipmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="splitShipmentFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="cancelledQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="upcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="upcNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="returnExpectedBackDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="lineProductUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="lineImageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="linePrimaryImageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="userCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="vgcNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="vgcCidNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="returnReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="returnReceiptDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "reservationNumber", "internetOrderNumber",
		"replyToEmailAddress", "replyToDisplayName", "upcNumber",
		"returnExpectedBackDate", "returnReason", "lineProductUrl",
		"lineImageType", "linePrimaryImageUrl", "thirdPartyOrderNumber",
		"orderDate", "phoneNbrCS", "hoursCS", "phoneNbrFP", "hoursFP","orderCancelTime",
		"smsFlag", "ltyFlag", "ltyIdUsl", "shopAt", "explanationText",
		"orderSubtotalAmt", "baseShippingAmt", "addtShipAddAmt",
		"shipUpgradeAmt", "giftWrapAmt", "taxAmt", "orderTotal", "munTaxAmt",
		"miscTaxAmt", "divisionCode", "locationCode", "adhocText", "zoneName",
		"mailingLabelURL", "billingDetail", "paymentDetail", "shipmentDetail",
		"loyaltyId", "loyaltyFirstName", "loyaltyMiddleName",
		"enrollmentLocation", "addedByApp", "loyaltyLastName",
		"loyaltyContract", "division", "tier", "enrollmentConfirmation",
		"amountRewardsCard", "rewardCardExpirationDate", "vrcBarCode",
		"rewardCardEffectiveDate", "cid", "linkToWallet",
		"rewardCardEarnEndDate", "userType" })
@XmlRootElement(name = "mailMessagePayload")
public class QA541_2_241_2_MCOM {

	@XmlElement(required = true)
	protected String reservationNumber;
	@XmlElement(required = true)
	protected String internetOrderNumber;
	@XmlElement(required = true)
	protected String replyToEmailAddress;
	@XmlElement(required = true)
	protected String replyToDisplayName;
	@XmlElement(required = true)
	protected String upcNumber;
	@XmlElement(required = true)
	protected String returnExpectedBackDate;
	@XmlElement(required = true)
	protected String returnReason;
	@XmlElement(required = true)
	protected String lineProductUrl;
	@XmlElement(required = true)
	protected String lineImageType;
	@XmlElement(required = true)
	protected String linePrimaryImageUrl;
	@XmlElement(required = true)
	protected String thirdPartyOrderNumber;
	@XmlElement(required = true)
	protected String orderDate;
	@XmlElement(required = true)
	protected String orderCancelTime;
	@XmlElement(required = true)
	protected String phoneNbrCS;
	@XmlElement(required = true)
	protected String hoursCS;
	@XmlElement(required = true)
	protected String phoneNbrFP;
	@XmlElement(required = true)
	protected String hoursFP;
	@XmlElement(required = true)
	protected String smsFlag;
	@XmlElement(required = true)
	protected String ltyFlag;
	@XmlElement(required = true)
	protected String ltyIdUsl;
	@XmlElement(required = true)
	protected String shopAt;
	@XmlElement(required = true)
	protected String explanationText;
	@XmlElement(required = true)
	protected String orderSubtotalAmt;
	@XmlElement(required = true)
	protected String baseShippingAmt;
	@XmlElement(required = true)
	protected String addtShipAddAmt;
	@XmlElement(required = true)
	protected String shipUpgradeAmt;
	@XmlElement(required = true)
	protected String giftWrapAmt;
	@XmlElement(required = true)
	protected String taxAmt;
	@XmlElement(required = true)
	protected String orderTotal;
	@XmlElement(required = true)
	protected String divisionCode;
	@XmlElement(required = true)
	protected String locationCode;
	@XmlElement(required = true)
	protected String adhocText;
	@XmlElement(required = true)
	protected QA541_2_241_2_MCOM.BillingDetail billingDetail;
	@XmlElement(required = true)
	protected List<QA541_2_241_2_MCOM.PaymentDetail> paymentDetail;
	@XmlElement(required = true)
	protected List<QA541_2_241_2_MCOM.ShipmentDetail> shipmentDetail;
	@XmlElement(required = true)
	protected String mailingLabelURL;
	@XmlElement(required = true)
	protected String miscTaxAmt;
	@XmlElement(required = true)
	protected String munTaxAmt;
	@XmlElement(required = true)
	protected String zoneName;

	@XmlElement(required = true)
	protected String loyaltyId;
	@XmlElement(required = true)
	protected String loyaltyFirstName;
	@XmlElement(required = true)
	protected String loyaltyMiddleName;
	@XmlElement(required = true)
	protected String loyaltyLastName;
	@XmlElement(required = true)
	protected String loyaltyContract;
	@XmlElement(required = true)
	protected String division;
	@XmlElement(required = true)
	protected String amountRewardsCard;
	@XmlElement(required = true)
	protected String rewardCardExpirationDate;
	@XmlElement(required = true)
	protected String vrcBarCode;
	@XmlElement(required = true)
	protected String rewardCardEffectiveDate;
	@XmlElement(required = true)
	protected String cid;
	@XmlElement(required = true)
	protected String tier;
	@XmlElement(required = true)
	protected String enrollmentConfirmation;
	@XmlElement(required = true)
	protected String enrollmentLocation;
	@XmlElement(required = true)
	protected String addedByApp;
	@XmlElement(required = true)
	protected String rewardCardEarnEndDate;
	@XmlElement(required = true)
	protected String linkToWallet;
	@XmlElement(required = true)
	protected String userType;

	/**
	 * Gets the value of the reservationNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReservationNumber() {
		return reservationNumber;
	}

	/**
	 * Sets the value of the reservationNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReservationNumber(String value) {
		this.reservationNumber = value;
	}

	/**
	 * Gets the value of the internetOrderNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInternetOrderNumber() {
		return internetOrderNumber;
	}

	/**
	 * Sets the value of the internetOrderNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInternetOrderNumber(String value) {
		this.internetOrderNumber = value;
	}

	/**
	 * Gets the value of the thirdPartyOrderNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getThirdPartyOrderNumber() {
		return thirdPartyOrderNumber;
	}

	/**
	 * Sets the value of the thirdPartyOrderNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setThirdPartyOrderNumber(String value) {
		this.thirdPartyOrderNumber = value;
	}

	/**
	 * Gets the value of the orderDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the value of the orderDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderDate(String value) {
		this.orderDate = value;
	}

	/**
	 * Gets the value of the orderCancelTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderCancelTime() {
		return orderCancelTime;
	}

	/**
	 * Sets the value of the orderCancelTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderCancelTime(String value) {
		this.orderCancelTime = value;
	}
	/**
	 * Gets the value of the phoneNbrCS property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneNbrCS() {
		return phoneNbrCS;
	}

	/**
	 * Sets the value of the phoneNbrCS property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneNbrCS(String value) {
		this.phoneNbrCS = value;
	}

	/**
	 * Gets the value of the hoursCS property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHoursCS() {
		return hoursCS;
	}

	/**
	 * Sets the value of the hoursCS property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHoursCS(String value) {
		this.hoursCS = value;
	}

	/**
	 * Gets the value of the phoneNbrFP property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneNbrFP() {
		return phoneNbrFP;
	}

	/**
	 * Sets the value of the phoneNbrFP property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneNbrFP(String value) {
		this.phoneNbrFP = value;
	}

	/**
	 * Gets the value of the hoursFP property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHoursFP() {
		return hoursFP;
	}

	/**
	 * Sets the value of the hoursFP property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHoursFP(String value) {
		this.hoursFP = value;
	}

	/**
	 * Gets the value of the smsFlag property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSmsFlag() {
		return smsFlag;
	}

	/**
	 * Sets the value of the smsFlag property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSmsFlag(String value) {
		this.smsFlag = value;
	}

	/**
	 * Gets the value of the ltyFlag property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLtyFlag() {
		return ltyFlag;
	}

	/**
	 * Sets the value of the ltyFlag property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLtyFlag(String value) {
		this.ltyFlag = value;
	}

	/**
	 * Gets the value of the ltyIdUsl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLtyIdUsl() {
		return ltyIdUsl;
	}

	/**
	 * Sets the value of the ltyIdUsl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLtyIdUsl(String value) {
		this.ltyIdUsl = value;
	}

	/**
	 * Gets the value of the shopAt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShopAt() {
		return shopAt;
	}

	/**
	 * Sets the value of the shopAt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShopAt(String value) {
		this.shopAt = value;
	}

	/**
	 * Gets the value of the explanationText property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getExplanationText() {
		return explanationText;
	}

	/**
	 * Sets the value of the explanationText property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExplanationText(String value) {
		this.explanationText = value;
	}

	/**
	 * Gets the value of the replyToEmailAddress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReplyToEmailAddress() {
		return replyToEmailAddress;
	}

	/**
	 * Sets the value of the replyToEmailAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReplyToEmailAddress(String value) {
		this.replyToEmailAddress = value;
	}

	/**
	 * Gets the value of the replyToDisplayName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReplyToDisplayName() {
		return replyToDisplayName;
	}

	/**
	 * Sets the value of the replyToDisplayName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReplyToDisplayName(String value) {
		this.replyToDisplayName = value;
	}

	/**
	 * Sets the value of the upcNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUpcNumber(String value) {
		this.upcNumber = value;
	}

	/**
	 * Gets the value of the upcNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUpcNumber() {
		return upcNumber;
	}

	/**
	 * Gets the value of the returnExpectedBackDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnExpectedBackDate() {
		return returnExpectedBackDate;
	}

	/**
	 * Sets the value of the returnExpectedBackDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnExpectedBackDate(String value) {
		this.returnExpectedBackDate = value;
	}

	/**
	 * Gets the value of the returnReason property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnReason() {
		return returnReason;
	}

	/**
	 * Sets the value of the returnReason property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnReason(String value) {
		this.returnReason = value;
	}

	/**
	 * Gets the value of the lineProductUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLineProductUrl() {
		return lineProductUrl;
	}

	/**
	 * Sets the value of the lineProductUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLineProductUrl(String value) {
		this.lineProductUrl = value;
	}

	/**
	 * Gets the value of the lineImageType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLineImageType() {
		return lineImageType;
	}

	/**
	 * Sets the value of the lineImageType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLineImageType(String value) {
		this.lineImageType = value;
	}

	/**
	 * Gets the value of the linePrimaryImageUrl property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinePrimaryImageUrl() {
		return linePrimaryImageUrl;
	}

	/**
	 * Sets the value of the linePrimaryImageUrl property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinePrimaryImageUrl(String value) {
		this.linePrimaryImageUrl = value;
	}

	/**
	 * Gets the value of the orderSubtotalAmt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderSubtotalAmt() {
		return orderSubtotalAmt;
	}

	/**
	 * Sets the value of the orderSubtotalAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderSubtotalAmt(String value) {
		this.orderSubtotalAmt = value;
	}

	/**
	 * Gets the value of the baseShippingAmt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBaseShippingAmt() {
		return baseShippingAmt;
	}

	/**
	 * Sets the value of the baseShippingAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBaseShippingAmt(String value) {
		this.baseShippingAmt = value;
	}

	/**
	 * Gets the value of the addtShipAddAmt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddtShipAddAmt() {
		return addtShipAddAmt;
	}

	/**
	 * Sets the value of the addtShipAddAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddtShipAddAmt(String value) {
		this.addtShipAddAmt = value;
	}

	/**
	 * Gets the value of the shipUpgradeAmt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShipUpgradeAmt() {
		return shipUpgradeAmt;
	}

	/**
	 * Sets the value of the shipUpgradeAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShipUpgradeAmt(String value) {
		this.shipUpgradeAmt = value;
	}

	/**
	 * Gets the value of the giftWrapAmt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGiftWrapAmt() {
		return giftWrapAmt;
	}

	/**
	 * Sets the value of the giftWrapAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGiftWrapAmt(String value) {
		this.giftWrapAmt = value;
	}

	/**
	 * Gets the value of the taxAmt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaxAmt() {
		return taxAmt;
	}

	/**
	 * Sets the value of the taxAmt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaxAmt(String value) {
		this.taxAmt = value;
	}

	/**
	 * Gets the value of the orderTotal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderTotal() {
		return orderTotal;
	}

	/**
	 * Sets the value of the orderTotal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderTotal(String value) {
		this.orderTotal = value;
	}

	/**
	 * Gets the value of the divisionCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * Sets the value of the divisionCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDivisionCode(String value) {
		this.divisionCode = value;
	}

	/**
	 * Gets the value of the locationCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * Sets the value of the locationCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLocationCode(String value) {
		this.locationCode = value;
	}

	/**
	 * Gets the value of the adhocText property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAdhocText() {
		return adhocText;
	}

	/**
	 * Sets the value of the adhocText property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAdhocText(String value) {
		this.adhocText = value;
	}

	/**
	 * @param mailingLabelURL
	 */
	public void setMailingLabelURL(String mailingLabelURL) {
		this.mailingLabelURL = mailingLabelURL;
	}

	/**
	 * @return
	 */
	public String getMailingLabelURL() {
		return mailingLabelURL;
	}

	/**
	 * @param zoneName
	 */
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	/**
	 * @return
	 */
	public String getZoneName() {
		return zoneName;
	}

	/**
	 * @param miscTaxAmt
	 */
	public void setMiscTaxAmt(String miscTaxAmt) {
		this.miscTaxAmt = miscTaxAmt;
	}

	/**
	 * @return
	 */
	public String getMiscTaxAmt() {
		return miscTaxAmt;
	}

	/**
	 * @param munTaxAmt
	 */
	public void setMunTaxAmt(String munTaxAmt) {
		this.munTaxAmt = munTaxAmt;
	}

	/**
	 * @return
	 */
	public String getMunTaxAmt() {
		return munTaxAmt;
	}

	/**
	 * Gets the value of the loyaltyId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoyaltyId() {
		return loyaltyId;
	}

	/**
	 * Sets the value of the loyaltyId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoyaltyId(String value) {
		this.loyaltyId = value;
	}

	/**
	 * Gets the value of the loyaltyFirstName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoyaltyFirstName() {
		return loyaltyFirstName;
	}

	/**
	 * Sets the value of the loyaltyFirstName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoyaltyFirstName(String value) {
		this.loyaltyFirstName = value;
	}

	/**
	 * Gets the value of the loyaltyMiddleName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoyaltyMiddleName() {
		return loyaltyMiddleName;
	}

	/**
	 * Sets the value of the loyaltyMiddleName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoyaltyMiddleName(String value) {
		this.loyaltyMiddleName = value;
	}

	/**
	 * Gets the value of the loyaltyLastName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoyaltyLastName() {
		return loyaltyLastName;
	}

	/**
	 * Sets the value of the loyaltyLastName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoyaltyLastName(String value) {
		this.loyaltyLastName = value;
	}

	/**
	 * Gets the value of the loyaltyContract property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLoyaltyContract() {
		return loyaltyContract;
	}

	/**
	 * Sets the value of the loyaltyContract property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLoyaltyContract(String value) {
		this.loyaltyContract = value;
	}

	/**
	 * Gets the value of the division property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * Sets the value of the division property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDivision(String value) {
		this.division = value;
	}

	/**
	 * Gets the value of the amountRewardsCard property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAmountRewardsCard() {
		return amountRewardsCard;
	}

	/**
	 * Sets the value of the amountRewardsCard property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAmountRewardsCard(String value) {
		this.amountRewardsCard = value;
	}

	/**
	 * Gets the value of the rewardCardExpirationDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRewardCardExpirationDate() {
		return rewardCardExpirationDate;
	}

	/**
	 * Sets the value of the rewardCardExpirationDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRewardCardExpirationDate(String value) {
		this.rewardCardExpirationDate = value;
	}

	/**
	 * Gets the value of the vrcBarCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVrcBarCode() {
		return vrcBarCode;
	}

	/**
	 * Sets the value of the vrcBarCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVrcBarCode(String value) {
		this.vrcBarCode = value;
	}

	/**
	 * Gets the value of the rewardCardEffectiveDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRewardCardEffectiveDate() {
		return rewardCardEffectiveDate;
	}

	/**
	 * Sets the value of the rewardCardEffectiveDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRewardCardEffectiveDate(String value) {
		this.rewardCardEffectiveDate = value;
	}

	/**
	 * Gets the value of the cid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * Sets the value of the cid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCid(String value) {
		this.cid = value;
	}

	/**
	 * Gets the value of the tier property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTier() {
		return tier;
	}

	/**
	 * Sets the value of the tier property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTier(String value) {
		this.tier = value;
	}

	/**
	 * Gets the value of the enrollmentConfirmation property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEnrollmentConfirmation() {
		return enrollmentConfirmation;
	}

	/**
	 * Sets the value of the enrollmentConfirmation property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnrollmentConfirmation(String value) {
		this.enrollmentConfirmation = value;
	}

	/**
	 * Gets the value of the enrollmentLocation property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEnrollmentLocation() {
		return enrollmentLocation;
	}

	/**
	 * Sets the value of the enrollmentLocation property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnrollmentLocation(String value) {
		this.enrollmentLocation = value;
	}

	/**
	 * Gets the value of the addedByApp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddedByApp() {
		return addedByApp;
	}

	/**
	 * Sets the value of the addedByApp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddedByApp(String value) {
		this.addedByApp = value;
	}

	/**
	 * Gets the value of the rewardCardEarnEndDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRewardCardEarnEndDate() {
		return rewardCardEarnEndDate;
	}

	/**
	 * Sets the value of the rewardCardEarnEndDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRewardCardEarnEndDate(String value) {
		this.rewardCardEarnEndDate = value;
	}

	/**
	 * Gets the value of the linkToWallet property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLinkToWallet() {
		return linkToWallet;
	}

	/**
	 * Sets the value of the linkToWallet property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLinkToWallet(String value) {
		this.linkToWallet = value;
	}

	/**
	 * Gets the value of the userType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Sets the value of the userType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserType(String value) {
		this.userType = value;
	}

	/**
	 * Gets the value of the billingDetail property.
	 * 
	 * @return possible object is {@link OA541_2_MCOM.BillingDetail }
	 * 
	 */
	public QA541_2_241_2_MCOM.BillingDetail getBillingDetail() {
		return billingDetail;
	}

	/**
	 * Sets the value of the billingDetail property.
	 * 
	 * @param value
	 *            allowed object is {@link OA541_2_MCOM.BillingDetail }
	 * 
	 */

	public void setBillingDetail(QA541_2_241_2_MCOM.BillingDetail value) {
		this.billingDetail = value;
	}

	public List<QA541_2_241_2_MCOM.PaymentDetail> getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(
			List<QA541_2_241_2_MCOM.PaymentDetail> paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	/**
	 * Gets the value of the shipmentDetail property.
	 * 
	 * @return possible object is {@link OA541_2_MCOM.ShipmentDetail }
	 * 
	 */
	public List<QA541_2_241_2_MCOM.ShipmentDetail> getShipmentDetail() {
		return shipmentDetail;
	}

	/**
	 * Sets the value of the shipmentDetail property.
	 * 
	 * @param value
	 *            allowed object is {@link OA541_2_MCOM.ShipmentDetail }
	 * 
	 */
	public void setShipmentDetail(List<QA541_2_241_2_MCOM.ShipmentDetail> value) {
		this.shipmentDetail = value;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="attnLine" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="addressLine3" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="zipcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "title", "firstName", "middleName",
			"lastName", "attnLine", "addressLine1", "addressLine2",
			"addressLine3", "city", "state", "country", "zipcode" })
	public static class BillingDetail {

		@XmlElement(required = true)
		protected String title;
		@XmlElement(required = true)
		protected String firstName;
		@XmlElement(required = true)
		protected String middleName;
		@XmlElement(required = true)
		protected String lastName;
		@XmlElement(required = true)
		protected String attnLine;
		@XmlElement(required = true)
		protected String addressLine1;
		@XmlElement(required = true)
		protected String addressLine2;
		@XmlElement(required = true)
		protected String addressLine3;
		@XmlElement(required = true)
		protected String city;
		@XmlElement(required = true)
		protected String state;
		@XmlElement(required = true)
		protected String country;
		@XmlElement(required = true)
		protected String zipcode;

		/**
		 * Gets the value of the title property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * Sets the value of the title property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setTitle(String value) {
			this.title = value;
		}

		/**
		 * Gets the value of the firstName property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * Sets the value of the firstName property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setFirstName(String value) {
			this.firstName = value;
		}

		/**
		 * Gets the value of the middleName property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getMiddleName() {
			return middleName;
		}

		/**
		 * Sets the value of the middleName property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setMiddleName(String value) {
			this.middleName = value;
		}

		/**
		 * Gets the value of the lastName property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * Sets the value of the lastName property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setLastName(String value) {
			this.lastName = value;
		}

		/**
		 * Gets the value of the attnLine property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAttnLine() {
			return attnLine;
		}

		/**
		 * Sets the value of the attnLine property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAttnLine(String value) {
			this.attnLine = value;
		}

		/**
		 * Gets the value of the addressLine1 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAddressLine1() {
			return addressLine1;
		}

		/**
		 * Sets the value of the addressLine1 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAddressLine1(String value) {
			this.addressLine1 = value;
		}

		/**
		 * Gets the value of the addressLine2 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAddressLine2() {
			return addressLine2;
		}

		/**
		 * Sets the value of the addressLine2 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAddressLine2(String value) {
			this.addressLine2 = value;
		}

		/**
		 * Gets the value of the addressLine3 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAddressLine3() {
			return addressLine3;
		}

		/**
		 * Sets the value of the addressLine3 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAddressLine3(String value) {
			this.addressLine3 = value;
		}

		/**
		 * Gets the value of the city property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCity() {
			return city;
		}

		/**
		 * Sets the value of the city property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCity(String value) {
			this.city = value;
		}

		/**
		 * Gets the value of the state property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getState() {
			return state;
		}

		/**
		 * Sets the value of the state property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setState(String value) {
			this.state = value;
		}

		/**
		 * Gets the value of the country property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCountry() {
			return country;
		}

		/**
		 * Sets the value of the country property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCountry(String value) {
			this.country = value;
		}

		/**
		 * Gets the value of the zipcode property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZipcode() {
			return zipcode;
		}

		/**
		 * Sets the value of the zipcode property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZipcode(String value) {
			this.zipcode = value;
		}

	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="commercialCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="paymentCategoryFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="egcCertificateType" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "paymentType", "accountNumber", "amount",
			"paymentCategoryFlag", "egcCertificateType", "commercialCode" })
	public static class PaymentDetail {

		
		@XmlElement(required = true)
		protected String paymentType;
		@XmlElement(required = true)
		protected String commercialCode;
		@XmlElement(required = true)
		protected String accountNumber;
		@XmlElement(required = true)
		protected String amount;
		@XmlElement(required = true)
		protected String paymentCategoryFlag;
		@XmlElement(required = true)
		protected String egcCertificateType;
		

		/**
		 * Gets the value of the paymentType property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPaymentType() {
			return paymentType;
		}

		/**
		 * Sets the value of the paymentType property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setPaymentType(String value) {
			this.paymentType = value;
		}

		/**
		 * Gets the value of the commercialCode property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCommercialCode() {
			return commercialCode;
		}

		/**
		 * Sets the value of the commercialCode property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */

		public void setCommercialCode(String value) {
			this.commercialCode = value;
		}

		/**
		 * Gets the value of the accountNumber property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAccountNumber() {
			return accountNumber;
		}

		/**
		 * Sets the value of the accountNumber property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAccountNumber(String value) {
			this.accountNumber = value;
		}

		/**
		 * Gets the value of the amount property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAmount() {
			return amount;
		}

		/**
		 * Sets the value of the amount property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAmount(String value) {
			this.amount = value;
		}

		/**
		 * Gets the value of the paymentCategoryFlag property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPaymentCategoryFlag() {
			return paymentCategoryFlag;
		}

		/**
		 * Sets the value of the paymentCategoryFlag property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setPaymentCategoryFlag(String value) {
			this.paymentCategoryFlag = value;
		}

		/**
		 * Gets the value of the egcCertificateType property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getEgcCertificateType() {
			return egcCertificateType;
		}

		/**
		 * Sets the value of the egcCertificateType property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setEgcCertificateType(String value) {
			this.egcCertificateType = value;
		}

	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="shipmentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="pickupBarcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shippedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="pickupReadyDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="pickupCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shipRetailAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shipTaxAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shipFilLoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="mailFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shipmentMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="attnLine" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="addressLine3" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="zipcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shipPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="shipEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="greetingMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="closingMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="signatureMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="giftRegistryNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="giftWrapFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="giftReceipientEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="giftRegistrant1" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="giftRegistrant2" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="giftReceiptFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="trackingDetail">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="trackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="numberofPackages" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="itemDetail">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="lineNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="requestedQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="availableQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="shippedQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="expectedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="newExpectedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="delaySentDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="delayWaitDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="systemCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="shipmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="splitShipmentFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="cancelledQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="lineItemFdInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="upcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="upcNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="returnExpectedBackDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="returnReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="returnReceiptDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="lineProductUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="lineImageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="linePrimaryImageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="userCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="vgcNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="vgcCidNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "shipmentNumber", "pickupBarcode",
			"shippedDate", "returnSubmittedDate", "pickupReadyDate",
			"pickupCancelDate", "shipRetailAmt", "shipTaxAmt", "shipFilLoc",
			"mailFlag", "shipmentMethod", "title", "firstName", "middleName",
			"lastName", "attnLine", "addressLine1", "addressLine2",
			"addressLine3", "city", "state", "country", "zipcode", "shipPhone",
			"shipEmailAddress", "greetingMessage", "closingMessage",
			"signatureMessage", "giftRegistryNumber", "giftWrapFlag",
			"giftReceipientEmail", "giftRegistrant1", "giftRegistrant2",
			"giftReceiptFlag", "trackingDetail", "itemDetail" })
	public static class ShipmentDetail {

		@XmlElement(required = true)
		protected String shipmentNumber;
		@XmlElement(required = true)
		protected String pickupBarcode;
		@XmlElement(required = true)
		protected String shippedDate;
		@XmlElement(required = true)
		protected String pickupReadyDate;
		@XmlElement(required = true)
		protected String pickupCancelDate;
		@XmlElement(required = true)
		protected String shipRetailAmt;
		@XmlElement(required = true)
		protected String shipTaxAmt;
		@XmlElement(required = true)
		protected String shipFilLoc;
		@XmlElement(required = true)
		protected String mailFlag;
		@XmlElement(required = true)
		protected String shipmentMethod;
		@XmlElement(required = true)
		protected String title;
		@XmlElement(required = true)
		protected String firstName;
		@XmlElement(required = true)
		protected String middleName;
		@XmlElement(required = true)
		protected String lastName;
		@XmlElement(required = true)
		protected String attnLine;
		@XmlElement(required = true)
		protected String addressLine1;
		@XmlElement(required = true)
		protected String addressLine2;
		@XmlElement(required = true)
		protected String addressLine3;
		@XmlElement(required = true)
		protected String city;
		@XmlElement(required = true)
		protected String state;
		@XmlElement(required = true)
		protected String country;
		@XmlElement(required = true)
		protected String zipcode;
		@XmlElement(required = true)
		protected String shipPhone;
		@XmlElement(required = true)
		protected String shipEmailAddress;
		@XmlElement(required = true)
		protected String greetingMessage;
		@XmlElement(required = true)
		protected String closingMessage;
		@XmlElement(required = true)
		protected String signatureMessage;
		@XmlElement(required = true)
		protected String giftRegistryNumber;
		@XmlElement(required = true)
		protected String giftWrapFlag;
		@XmlElement(required = true)
		protected String giftReceipientEmail;
		@XmlElement(required = true)
		protected String giftRegistrant1;
		@XmlElement(required = true)
		protected String giftRegistrant2;
		@XmlElement(required = true)
		protected String giftReceiptFlag;
		@XmlElement(required = true)
		protected List<QA541_2_241_2_MCOM.ShipmentDetail.TrackingDetail> trackingDetail;
		@XmlElement(required = true)
		protected List<QA541_2_241_2_MCOM.ShipmentDetail.ItemDetail> itemDetail;
		@XmlElement(required = true)
		protected String returnSubmittedDate;

		/**
		 * Gets the value of the shipmentNumber property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipmentNumber() {
			return shipmentNumber;
		}

		/**
		 * Sets the value of the shipmentNumber property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipmentNumber(String value) {
			this.shipmentNumber = value;
		}

		/**
		 * Gets the value of the pickupBarcode property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPickupBarcode() {
			return pickupBarcode;
		}

		/**
		 * Sets the value of the pickupBarcode property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setPickupBarcode(String value) {
			this.pickupBarcode = value;
		}

		/**
		 * Gets the value of the shippedDate property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShippedDate() {
			return shippedDate;
		}

		/**
		 * Sets the value of the shippedDate property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShippedDate(String value) {
			this.shippedDate = value;
		}

		/**
		 * Gets the value of the pickupReadyDate property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPickupReadyDate() {
			return pickupReadyDate;
		}

		/**
		 * Sets the value of the pickupReadyDate property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setPickupReadyDate(String value) {
			this.pickupReadyDate = value;
		}

		/**
		 * Gets the value of the pickupCancelDate property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPickupCancelDate() {
			return pickupCancelDate;
		}

		/**
		 * Sets the value of the pickupCancelDate property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setPickupCancelDate(String value) {
			this.pickupCancelDate = value;
		}

		/**
		 * Gets the value of the shipRetailAmt property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipRetailAmt() {
			return shipRetailAmt;
		}

		/**
		 * Sets the value of the shipRetailAmt property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipRetailAmt(String value) {
			this.shipRetailAmt = value;
		}

		/**
		 * Gets the value of the shipTaxAmt property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipTaxAmt() {
			return shipTaxAmt;
		}

		/**
		 * Sets the value of the shipTaxAmt property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipTaxAmt(String value) {
			this.shipTaxAmt = value;
		}

		/**
		 * Gets the value of the shipFilLoc property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipFilLoc() {
			return shipFilLoc;
		}

		/**
		 * Sets the value of the shipFilLoc property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipFilLoc(String value) {
			this.shipFilLoc = value;
		}

		/**
		 * Gets the value of the mailFlag property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getMailFlag() {
			return mailFlag;
		}

		/**
		 * Sets the value of the mailFlag property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setMailFlag(String value) {
			this.mailFlag = value;
		}

		/**
		 * Gets the value of the shipmentMethod property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipmentMethod() {
			return shipmentMethod;
		}

		/**
		 * Sets the value of the shipmentMethod property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipmentMethod(String value) {
			this.shipmentMethod = value;
		}

		/**
		 * Gets the value of the title property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * Sets the value of the title property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setTitle(String value) {
			this.title = value;
		}

		/**
		 * Gets the value of the firstName property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * Sets the value of the firstName property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setFirstName(String value) {
			this.firstName = value;
		}

		/**
		 * Gets the value of the middleName property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getMiddleName() {
			return middleName;
		}

		/**
		 * Sets the value of the middleName property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setMiddleName(String value) {
			this.middleName = value;
		}

		/**
		 * Gets the value of the lastName property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * Sets the value of the lastName property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setLastName(String value) {
			this.lastName = value;
		}

		/**
		 * Gets the value of the attnLine property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAttnLine() {
			return attnLine;
		}

		/**
		 * Sets the value of the attnLine property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAttnLine(String value) {
			this.attnLine = value;
		}

		/**
		 * Gets the value of the addressLine1 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAddressLine1() {
			return addressLine1;
		}

		/**
		 * Sets the value of the addressLine1 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAddressLine1(String value) {
			this.addressLine1 = value;
		}

		/**
		 * Gets the value of the addressLine2 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAddressLine2() {
			return addressLine2;
		}

		/**
		 * Sets the value of the addressLine2 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAddressLine2(String value) {
			this.addressLine2 = value;
		}

		/**
		 * Gets the value of the addressLine3 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAddressLine3() {
			return addressLine3;
		}

		/**
		 * Sets the value of the addressLine3 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAddressLine3(String value) {
			this.addressLine3 = value;
		}

		/**
		 * Gets the value of the city property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCity() {
			return city;
		}

		/**
		 * Sets the value of the city property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCity(String value) {
			this.city = value;
		}

		/**
		 * Gets the value of the state property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getState() {
			return state;
		}

		/**
		 * Sets the value of the state property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setState(String value) {
			this.state = value;
		}

		/**
		 * Gets the value of the country property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCountry() {
			return country;
		}

		/**
		 * Sets the value of the country property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCountry(String value) {
			this.country = value;
		}

		/**
		 * Gets the value of the zipcode property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getZipcode() {
			return zipcode;
		}

		/**
		 * Sets the value of the zipcode property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setZipcode(String value) {
			this.zipcode = value;
		}

		/**
		 * Gets the value of the shipPhone property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipPhone() {
			return shipPhone;
		}

		/**
		 * Sets the value of the shipPhone property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipPhone(String value) {
			this.shipPhone = value;
		}

		/**
		 * Gets the value of the shipEmailAddress property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShipEmailAddress() {
			return shipEmailAddress;
		}

		/**
		 * Sets the value of the shipEmailAddress property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShipEmailAddress(String value) {
			this.shipEmailAddress = value;
		}

		/**
		 * Gets the value of the greetingMessage property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGreetingMessage() {
			return greetingMessage;
		}

		/**
		 * Sets the value of the greetingMessage property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGreetingMessage(String value) {
			this.greetingMessage = value;
		}

		/**
		 * Gets the value of the closingMessage property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getClosingMessage() {
			return closingMessage;
		}

		/**
		 * Sets the value of the closingMessage property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setClosingMessage(String value) {
			this.closingMessage = value;
		}

		/**
		 * Gets the value of the signatureMessage property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSignatureMessage() {
			return signatureMessage;
		}

		/**
		 * Sets the value of the signatureMessage property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setSignatureMessage(String value) {
			this.signatureMessage = value;
		}

		/**
		 * Gets the value of the giftRegistryNumber property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGiftRegistryNumber() {
			return giftRegistryNumber;
		}

		/**
		 * Sets the value of the giftRegistryNumber property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGiftRegistryNumber(String value) {
			this.giftRegistryNumber = value;
		}

		/**
		 * Gets the value of the giftWrapFlag property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGiftWrapFlag() {
			return giftWrapFlag;
		}

		/**
		 * Sets the value of the giftWrapFlag property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGiftWrapFlag(String value) {
			this.giftWrapFlag = value;
		}

		/**
		 * Gets the value of the giftReceipientEmail property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGiftReceipientEmail() {
			return giftReceipientEmail;
		}

		/**
		 * Sets the value of the giftReceipientEmail property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGiftReceipientEmail(String value) {
			this.giftReceipientEmail = value;
		}

		/**
		 * Gets the value of the giftRegistrant1 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGiftRegistrant1() {
			return giftRegistrant1;
		}

		/**
		 * Sets the value of the giftRegistrant1 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGiftRegistrant1(String value) {
			this.giftRegistrant1 = value;
		}

		/**
		 * Gets the value of the giftRegistrant2 property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGiftRegistrant2() {
			return giftRegistrant2;
		}

		/**
		 * Sets the value of the giftRegistrant2 property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGiftRegistrant2(String value) {
			this.giftRegistrant2 = value;
		}

		/**
		 * Gets the value of the giftReceiptFlag property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getGiftReceiptFlag() {
			return giftReceiptFlag;
		}

		/**
		 * Sets the value of the giftReceiptFlag property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setGiftReceiptFlag(String value) {
			this.giftReceiptFlag = value;
		}

		/**
		 * Gets the value of the trackingDetail property.
		 * 
		 * @return possible object is
		 *         {@link OA541_2_MCOM.ShipmentDetail.TrackingDetail }
		 * 
		 */
		public List<QA541_2_241_2_MCOM.ShipmentDetail.TrackingDetail> getTrackingDetail() {
			return trackingDetail;
		}

		/**
		 * Sets the value of the trackingDetail property.
		 * 
		 * @param value
		 *            allowed object is
		 *            {@link OA541_2_MCOM.ShipmentDetail.TrackingDetail }
		 * 
		 */
		public void setTrackingDetail(
				List<QA541_2_241_2_MCOM.ShipmentDetail.TrackingDetail> value) {
			this.trackingDetail = value;
		}

		/**
		 * @return
		 */
		public String getReturnSubmittedDate() {
			return returnSubmittedDate;
		}

		/**
		 * @param returnSubmittedDate
		 */
		public void setReturnSubmittedDate(String returnSubmittedDate) {
			this.returnSubmittedDate = returnSubmittedDate;
		}

		/**
		 * Gets the value of the itemDetail property.
		 * 
		 * @return possible object is
		 *         {@link OA541_2_MCOM.ShipmentDetail.ItemDetail }
		 * 
		 */
		public List<QA541_2_241_2_MCOM.ShipmentDetail.ItemDetail> getItemDetail() {
			return itemDetail;
		}

		/**
		 * Sets the value of the itemDetail property.
		 * 
		 * @param value
		 *            allowed object is
		 *            {@link OA541_2_MCOM.ShipmentDetail.ItemDetail }
		 * 
		 */
		public void setItemDetail(
				List<QA541_2_241_2_MCOM.ShipmentDetail.ItemDetail> value) {
			this.itemDetail = value;
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="lineNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="requestedQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="availableQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="shippedQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="expectedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="newExpectedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="delaySentDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="delayWaitDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="systemCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="shipmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="splitShipmentFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="cancelledQuantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="upcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="upcNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="returnExpectedBackDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="lineProductUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="lineImageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="linePrimaryImageUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="userCancelDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="vgcNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="vgcCidNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="returnReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="returnReceiptDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "lineNumber", "description", "price",
				"requestedQuantity", "availableQuantity", "shippedQuantity",
				"totalAmount", "expectedDate", "newExpectedDate",
				"delaySentDate", "delayWaitDate", "systemCancelDate",
				"shipmentType", "splitShipmentFlag", "status", "vendor",
				"cancelledQuantity", "upcId", "upcNumber",
				"returnExpectedBackDate", "lineProductUrl", "lineImageType",
				"linePrimaryImageUrl", "userCancelDate", "vgcNumber",
				"vgcCidNumber", "expiryDate", "size", "color",
				"returnExpectedDate", "returnStatus", "lineItemFdInd",
				"returnReasonDescription", "returnReason", "returnReceiptDate","lineItemDeliveryDate" })
		public static class ItemDetail {

			@XmlElement(required = true)
			protected String lineNumber;
			@XmlElement(required = true)
			protected String description;
			@XmlElement(required = true)
			protected String price;
			@XmlElement(required = true)
			protected String requestedQuantity;
			@XmlElement(required = true)
			protected String availableQuantity;
			@XmlElement(required = true)
			protected String shippedQuantity;
			@XmlElement(required = true)
			protected String totalAmount;
			@XmlElement(required = true)
			protected String expectedDate;
			@XmlElement(required = true)
			protected String newExpectedDate;
			@XmlElement(required = true)
			protected String delaySentDate;
			@XmlElement(required = true)
			protected String delayWaitDate;
			@XmlElement(required = true)
			protected String systemCancelDate;
			@XmlElement(required = true)
			protected String shipmentType;
			@XmlElement(required = true)
			protected String splitShipmentFlag;
			@XmlElement(required = true)
			protected String status;
			@XmlElement(required = true)
			protected String vendor;
			@XmlElement(required = true)
			protected String cancelledQuantity;
			@XmlElement(required = true)
			protected String upcId;
			@XmlElement(required = true)
			protected String upcNumber;
			@XmlElement(required = true)
			protected String returnExpectedBackDate;
			@XmlElement(required = true)
			protected String returnReason;
			@XmlElement(required = true)
			protected String lineProductUrl;
			@XmlElement(required = true)
			protected String lineImageType;
			@XmlElement(required = true)
			protected String linePrimaryImageUrl;
			@XmlElement(required = true)
			protected String userCancelDate;
			@XmlElement(required = true)
			protected String vgcNumber;
			@XmlElement(required = true)
			protected String vgcCidNumber;
			@XmlElement(required = true)
			protected String expiryDate;
			@XmlElement(required = true)
			protected String size;
			@XmlElement(required = true)
			protected String color;
			@XmlElement(required = true)
			protected String lineItemFdInd;
			@XmlElement(required = true)
			protected String returnExpectedDate;
			@XmlElement(required = true)
			protected String returnStatus;
			@XmlElement(required = true)
			protected String returnReasonDescription;
			@XmlElement(required = true)
			protected String returnReceiptDate;
			@XmlElement(required = true)
			protected String lineItemDeliveryDate;
			
			/**
			 * Gets the value of the lineNumber property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getLineNumber() {
				return lineNumber;
			}

			/**
			 * Sets the value of the lineNumber property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setLineNumber(String value) {
				this.lineNumber = value;
			}

			/**
			 * Gets the value of the description property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDescription() {
				return description;
			}

			/**
			 * Sets the value of the description property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setDescription(String value) {
				this.description = value;
			}

			/**
			 * Gets the value of the price property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getPrice() {
				return price;
			}

			/**
			 * Sets the value of the price property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setPrice(String value) {
				this.price = value;
			}

			/**
			 * Gets the value of the requestedQuantity property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getRequestedQuantity() {
				return requestedQuantity;
			}

			/**
			 * Sets the value of the requestedQuantity property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setRequestedQuantity(String value) {
				this.requestedQuantity = value;
			}

			/**
			 * Gets the value of the availableQuantity property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getAvailableQuantity() {
				return availableQuantity;
			}

			/**
			 * Sets the value of the availableQuantity property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setAvailableQuantity(String value) {
				this.availableQuantity = value;
			}

			/**
			 * Gets the value of the shippedQuantity property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getShippedQuantity() {
				return shippedQuantity;
			}

			/**
			 * Sets the value of the shippedQuantity property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setShippedQuantity(String value) {
				this.shippedQuantity = value;
			}

			/**
			 * Gets the value of the totalAmount property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTotalAmount() {
				return totalAmount;
			}

			/**
			 * Sets the value of the totalAmount property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setTotalAmount(String value) {
				this.totalAmount = value;
			}

			/**
			 * Gets the value of the expectedDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getExpectedDate() {
				return expectedDate;
			}

			/**
			 * Sets the value of the expectedDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setExpectedDate(String value) {
				this.expectedDate = value;
			}

			/**
			 * Gets the value of the newExpectedDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNewExpectedDate() {
				return newExpectedDate;
			}

			/**
			 * Sets the value of the newExpectedDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setNewExpectedDate(String value) {
				this.newExpectedDate = value;
			}

			/**
			 * Gets the value of the delaySentDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDelaySentDate() {
				return delaySentDate;
			}

			/**
			 * Sets the value of the delaySentDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setDelaySentDate(String value) {
				this.delaySentDate = value;
			}

			/**
			 * Gets the value of the delayWaitDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDelayWaitDate() {
				return delayWaitDate;
			}

			/**
			 * Sets the value of the delayWaitDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setDelayWaitDate(String value) {
				this.delayWaitDate = value;
			}

			/**
			 * Gets the value of the systemCancelDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSystemCancelDate() {
				return systemCancelDate;
			}

			/**
			 * Sets the value of the systemCancelDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setSystemCancelDate(String value) {
				this.systemCancelDate = value;
			}

			/**
			 * Gets the value of the shipmentType property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getShipmentType() {
				return shipmentType;
			}

			/**
			 * Sets the value of the shipmentType property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setShipmentType(String value) {
				this.shipmentType = value;
			}

			/**
			 * Gets the value of the splitShipmentFlag property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSplitShipmentFlag() {
				return splitShipmentFlag;
			}

			/**
			 * Sets the value of the splitShipmentFlag property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setSplitShipmentFlag(String value) {
				this.splitShipmentFlag = value;
			}

			/**
			 * Gets the value of the status property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getStatus() {
				return status;
			}

			/**
			 * Sets the value of the status property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setStatus(String value) {
				this.status = value;
			}

			/**
			 * Gets the value of the vendor property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getVendor() {
				return vendor;
			}

			/**
			 * Sets the value of the vendor property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setVendor(String value) {
				this.vendor = value;
			}

			/**
			 * Gets the value of the cancelledQuantity property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCancelledQuantity() {
				return cancelledQuantity;
			}

			/**
			 * Sets the value of the cancelledQuantity property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setCancelledQuantity(String value) {
				this.cancelledQuantity = value;
			}

			/**
			 * Gets the value of the upcId property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getUpcId() {
				return upcId;
			}

			/**
			 * Sets the value of the upcId property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setUpcId(String value) {
				this.upcId = value;
			}

			/**
			 * 
			 * Sets the value of the upcNumber property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setUpcNumber(String value) {
				this.upcNumber = value;
			}

			/**
			 * Gets the value of the upcNumber property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getUpcNumber() {
				return upcNumber;
			}

			public String getReturnExpectedBackDate() {
				return returnExpectedBackDate;
			}

			/**
			 * Sets the value of the returnExpectedBackDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setReturnExpectedBackDate(String value) {
				this.returnExpectedBackDate = value;
			}

			/**
			 * Gets the value of the lineProductUrl property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getLineProductUrl() {
				return lineProductUrl;
			}

			/**
			 * Sets the value of the lineProductUrl property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setLineProductUrl(String value) {
				this.lineProductUrl = value;
			}

			/**
			 * Gets the value of the lineImageType property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getLineImageType() {
				return lineImageType;
			}

			/**
			 * Sets the value of the lineImageType property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setLineImageType(String value) {
				this.lineImageType = value;
			}

			/**
			 * Gets the value of the linePrimaryImageUrl property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getLinePrimaryImageUrl() {
				return linePrimaryImageUrl;
			}

			/**
			 * Sets the value of the linePrimaryImageUrl property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setLinePrimaryImageUrl(String value) {
				this.linePrimaryImageUrl = value;
			}

			/**
			 * Gets the value of the userCancelDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getUserCancelDate() {
				return userCancelDate;
			}

			/**
			 * Sets the value of the userCancelDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setUserCancelDate(String value) {
				this.userCancelDate = value;
			}

			/**
			 * Gets the value of the vgcNumber property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getVgcNumber() {
				return vgcNumber;
			}

			/**
			 * Sets the value of the vgcNumber property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setVgcNumber(String value) {
				this.vgcNumber = value;
			}

			/**
			 * Gets the value of the vgcCidNumber property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getVgcCidNumber() {
				return vgcCidNumber;
			}

			/**
			 * Sets the value of the vgcCidNumber property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setVgcCidNumber(String value) {
				this.vgcCidNumber = value;
			}

			/**
			 * Gets the value of the expiryDate property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getExpiryDate() {
				return expiryDate;
			}

			/**
			 * Sets the value of the expiryDate property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setExpiryDate(String value) {
				this.expiryDate = value;
			}

			/**
			 * Gets the value of the size property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSize() {
				return size;
			}

			/**
			 * Sets the value of the size property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setSize(String value) {
				this.size = value;
			}

			/**
			 * Gets the value of the color property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getColor() {
				return color;
			}

			/**
			 * Sets the value of the color property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setColor(String value) {
				this.color = value;
			}

			/**
			 * Gets the value of the lineItemFdInd property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getLineItemFdInd() {
				return lineItemFdInd;
			}

			/**
			 * Sets the value of the lineItemFdInd property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setLineItemFdInd(String value) {
				this.lineItemFdInd = value;
			}

			/**
			 * @param returnExpectedDate
			 */
			public void setReturnExpectedDate(String returnExpectedDate) {
				this.returnExpectedDate = returnExpectedDate;
			}

			/**
			 * @param returnStatus
			 */
			public void setReturnStatus(String returnStatus) {
				this.returnStatus = returnStatus;
			}

			/**
			 * @param returnReasonDescription
			 */
			public void setReturnReasonDescription(
					String returnReasonDescription) {
				this.returnReasonDescription = returnReasonDescription;
			}

			public String getReturnReceiptDate() {
				return returnReceiptDate;
			}

			/**
			 * @param returnReceiptDate
			 */
			public void setReturnReceiptDate(String returnReceiptDate) {
				this.returnReceiptDate = returnReceiptDate;
			}

			/**
			 * @return
			 */
			public String getReturnExpectedDate() {
				return returnExpectedDate;
			}

			/**
			 * @return
			 */
			public String getReturnReasonDescription() {
				return returnReasonDescription;
			}

			/**
			 * @return
			 */
			public String getReturnStatus() {
				return returnStatus;
			}
			

			/**
			 * @return
			 */
			public String getLineItemDeliveryDate() {
				return lineItemDeliveryDate;
			}

		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="trackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="numberofPackages" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="shipmentCarrier" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "trackingNumber", "numberofPackages", "shipmentCarrier" })
		public static class TrackingDetail {

			@XmlElement(required = true)
			protected String trackingNumber;
			@XmlElement(required = true)
			protected String numberofPackages;
			@XmlElement(required = true)
			protected String shipmentCarrier;

			/**
			 * Gets the value of the trackingNumber property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTrackingNumber() {
				return trackingNumber;
			}

			/**
			 * Sets the value of the trackingNumber property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setTrackingNumber(String value) {
				this.trackingNumber = value;
			}

			/**
			 * Gets the value of the numberofPackages property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNumberofPackages() {
				return numberofPackages;
			}

			/**
			 * Sets the value of the numberofPackages property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setNumberofPackages(String value) {
				this.numberofPackages = value;
			}

			/**
			 * Gets the value of the shipmentCarrier property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getShipmentCarrier() {
				return shipmentCarrier;
			}

			/**
			 * Sets the value of the shipmentCarrier property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setShipmentCarrier(String value) {
				this.shipmentCarrier = value;
			}

		}

	}

}