package com.macys.sdt.projects.Customer.OESBB.utils.input.price.error;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
* <p>Java class for anonymous complex type.
* 
* <p>The following schema fragment specifies the expected content contained within this class.
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
*         &lt;element name="shopAt" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="explanationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
*         &lt;element name="phoneNbrCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="hoursCS" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="phoneNbrFP" type="{http://www.w3.org/2001/XMLSchema}string"/>
*         &lt;element name="hoursFP" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "reservationNumber", "internetOrderNumber", "thirdPartyOrderNumber", "orderDate",
		"shopAt", "explanationText", "orderSubtotalAmt", "baseShippingAmt", "addtShipAddAmt", "shipUpgradeAmt",
		"giftWrapAmt", "taxAmt", "orderTotal", "divisionCode", "locationCode", "adhocText", "phoneNbrCS", "hoursCS",
		"phoneNbrFP", "hoursFP", "billingDetail" })
@XmlRootElement(name = "mailMessagePayload")
public class QA409_28_MCOM{

	@XmlElement(required = true)
	protected String reservationNumber;
	@XmlElement(required = true)
	protected String internetOrderNumber;
	@XmlElement(required = true)
	protected String thirdPartyOrderNumber;
	@XmlElement(required = true)
	protected String orderDate;
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
	protected String phoneNbrCS;
	@XmlElement(required = true)
	protected String hoursCS;
	@XmlElement(required = true)
	protected String phoneNbrFP;
	@XmlElement(required = true)
	protected String hoursFP;
	@XmlElement(required = true)
	protected QA409_28_MCOM.BillingDetail billingDetail;

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
	 * Gets the value of the billingDetail property.
	 * 
	 * @return possible object is {@link MailMessagePayload.BillingDetail }
	 * 
	 */
	public QA409_28_MCOM.BillingDetail getBillingDetail() {
		return billingDetail;
	}

	/**
	 * Sets the value of the billingDetail property.
	 * 
	 * @param value
	 *            allowed object is {@link MailMessagePayload.BillingDetail }
	 * 
	 */
	public void setBillingDetail(QA409_28_MCOM.BillingDetail value) {
		this.billingDetail = value;
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
	@XmlType(name = "", propOrder = { "title", "firstName", "middleName", "lastName", "attnLine", "addressLine1",
			"addressLine2", "addressLine3", "city", "state", "country", "zipcode" })
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

}
