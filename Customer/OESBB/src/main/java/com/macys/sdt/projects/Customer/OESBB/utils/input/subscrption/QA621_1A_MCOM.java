package com.macys.sdt.projects.Customer.OESBB.utils.input.subscrption;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author sbharadwaja
 *
 */
public class QA621_1A_MCOM {

	private String billFirstName;
	private String billLastName;
	private String billAddressLine1;
	private String billAddressLine2;
	private String billCity;
	private String billState;
	private String billZip;
	private String cancelByDate;
	/**
	 * @return the cancelByDate
	 */
	public String getCancelByDate() {
		return cancelByDate;
	}

	/**
	 * @param cancelByDate the cancelByDate to set
	 */
	public void setCancelByDate(String cancelByDate) {
		this.cancelByDate = cancelByDate;
	}

	/**
	 * @return the subscriptionMonth
	 */
	public String getSubscriptionMonth() {
		return subscriptionMonth;
	}

	/**
	 * @param subscriptionMonth the subscriptionMonth to set
	 */
	public void setSubscriptionMonth(String subscriptionMonth) {
		this.subscriptionMonth = subscriptionMonth;
	}

	/**
	 * @return the subscriptionUrl
	 */
	public String getSubscriptionUrl() {
		return subscriptionUrl;
	}

	/**
	 * @param subscriptionUrl the subscriptionUrl to set
	 */
	public void setSubscriptionUrl(String subscriptionUrl) {
		this.subscriptionUrl = subscriptionUrl;
	}

	private String subscriptionMonth;
	private String subscriptionUrl;
	private List<Payment> payments;
	private List<Shipment> shipments;

	/**
	 * @return the billFirstName
	 */
	public String getBillFirstName() {
		return billFirstName;
	}

	/**
	 * @param billFirstName
	 *            the billFirstName to set
	 */
	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}

	/**
	 * @return the billLastName
	 */
	public String getBillLastName() {
		return billLastName;
	}

	/**
	 * @param billLastName
	 *            the billLastName to set
	 */
	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}

	/**
	 * @return the billAddressLine1
	 */
	public String getBillAddressLine1() {
		return billAddressLine1;
	}

	/**
	 * @param billAddressLine1
	 *            the billAddressLine1 to set
	 */
	public void setBillAddressLine1(String billAddressLine1) {
		this.billAddressLine1 = billAddressLine1;
	}

	/**
	 * @return the billAddressLine2
	 */
	public String getBillAddressLine2() {
		return billAddressLine2;
	}

	/**
	 * @param billAddressLine2
	 *            the billAddressLine2 to set
	 */
	public void setBillAddressLine2(String billAddressLine2) {
		this.billAddressLine2 = billAddressLine2;
	}

	/**
	 * @return the billCity
	 */
	public String getBillCity() {
		return billCity;
	}

	/**
	 * @param billCity
	 *            the billCity to set
	 */
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	/**
	 * @return the billState
	 */
	public String getBillState() {
		return billState;
	}

	/**
	 * @param billState
	 *            the billState to set
	 */
	public void setBillState(String billState) {
		this.billState = billState;
	}

	/**
	 * @return the billZip
	 */
	public String getBillZip() {
		return billZip;
	}

	/**
	 * @param billZip
	 *            the billZip to set
	 */
	public void setBillZip(String billZip) {
		this.billZip = billZip;
	}

	/**
	 * @return the payments
	 */
	public List<Payment> getPayments() {
		return payments;
	}

	/**
	 * @param payments
	 *            the payments to set
	 */
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	/**
	 * @return the shipments
	 */
	public List<Shipment> getShipments() {
		return shipments;
	}

	/**
	 * @param shipments
	 *            the shipments to set
	 */
	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public File getJSON() {
		// TODO Auto-generated method stub
		return null;
	}

}
