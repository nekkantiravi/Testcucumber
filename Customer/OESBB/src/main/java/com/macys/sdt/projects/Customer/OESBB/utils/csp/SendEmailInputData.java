/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.csp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 *
 */
public class SendEmailInputData {

	@Autowired
	private ResourceLoader resourceLoader;

	String typeID;
	String subTypeID;
	String sourceSystemType;
	String sourceSystemID;
	String brand;
	String deliveryType;
	String emailPreference;
	String emailAddress;
	String postalSmsPhone;
	String addresseeTitle;
	String addresseeFirstName;
	String addresseeMiddleName;
	String addresseeLastName;
	String addressLine1;
	String addressLine2;
	String addressLine3;
	String addressCity;
	String addressState;
	String addressZipcode;
	String addressCountry;
	String payloadXml;

	public SendEmailInputData(String typeID, String subTypeID, String sourceSystemType, String sourceSystemID,
			String brand, String deliveryType, String emailPreference, String emailAddress, String postalSmsPhone,
			String addresseeTitle, String addresseeFirstName, String addresseeMiddleName, String addresseeLastName,
			String addressLine1, String addressLine2, String addressLine3, String addressCity, String addressState,
			String addressZipcode, String addressCountry, String payloadXml) {
		super();

		this.typeID = typeID;
		this.subTypeID = subTypeID;
		this.sourceSystemType = sourceSystemType;
		this.sourceSystemID = sourceSystemID;
		this.brand = brand;
		this.deliveryType = deliveryType;
		this.emailPreference = emailPreference;
		this.emailAddress = emailAddress;
		this.postalSmsPhone = postalSmsPhone;
		this.addresseeTitle = addresseeTitle;
		this.addresseeFirstName = addresseeFirstName;
		this.addresseeMiddleName = addresseeMiddleName;
		this.addresseeLastName = addresseeLastName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZipcode = addressZipcode;
		this.addressCountry = addressCountry;
		this.payloadXml = payloadXml;
	}

	public String getTypeID() {
		return this.typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public String getSubTypeID() {
		return this.subTypeID;
	}

	public void setSubTypeID(String subTypeID) {
		this.subTypeID = subTypeID;
	}

	public String getSourceSystemType() {
		return this.sourceSystemType;
	}

	public void setSourceSystemType(String sourceSystemType) {
		if (sourceSystemType == null) {
			this.sourceSystemType = "FIL";
		} else {
			this.sourceSystemType = sourceSystemType.toUpperCase();
		}

	}

	public String getSourceSystemID() {
		if (this.sourceSystemID == null) {
			this.setSourceSystemID();
		}
		return this.sourceSystemID;
	}

	public void setSourceSystemID() {
		this.sourceSystemID = "qa" + new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date())
				+ "@qa.com";
	}
	
	public void setSourceSystemID(String ssid) {
		ssid="test_"+ssid;
		this.sourceSystemID = ssid;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		switch (brand.toUpperCase()) {
		case "MCOM": this.brand = "Macys.com"; break;
		case "MWEDD": this.brand = "Macys Wedding & Gift Registry"; break;
		case "MSTORE": this.brand = "Macys"; break;
		case "BCOM": case "BLCOM": this.brand = "Bloomingdales.com"; break;
		case "BSTORE": this.brand = "Bloomingdales"; break;
		case "BWEDD": this.brand = "Bloomies Wedding & Gift Registry"; break;
		case "BLTY": this.brand = "Bloomingdales.com Loyalty"; break;
		case "MLTY": this.brand = "Macys.com Loyalty"; break;
		default: this.brand = "UNDEFINED_BRAND_" + brand;
		}
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		if (deliveryType == null) {
			deliveryType = "Email";
		} else {
			this.deliveryType = deliveryType;
		}
	}

	public String getEmailPreference() {
		return this.emailPreference;
	}

	public void setEmailPreference(String emailPreference) {
		if (emailPreference == null) {
			this.emailPreference = "HTML";
		} else {
			this.emailPreference = emailPreference;
		}
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		if (emailAddress == null) {
			this.emailAddress = "saravanan.alagarsamy@macys.com";
		} else {
			this.emailAddress = emailAddress;
		}
	}

	public String getPostalSmsPhone() {
		return this.postalSmsPhone;
	}

	public void setPostalSmsPhone(String postalSmsPhone) {
		this.postalSmsPhone = postalSmsPhone;
	}

	public String getAddresseeTitle() {
		return this.addresseeTitle;
	}

	public void setAddresseeTitle(String addresseeTitle) {
		this.addresseeTitle = addresseeTitle;
	}

	public String getAddresseeFirstName() {
		return this.addresseeFirstName;
	}

	public void setAddresseeFirstName(String addresseeFirstName) {
		this.addresseeFirstName = addresseeFirstName;
	}

	public String getAddresseeMiddleName() {
		return this.addresseeMiddleName;
	}

	public void setAddresseeMiddleName(String addresseeMiddleName) {
		this.addresseeMiddleName = addresseeMiddleName;
	}

	public String getAddresseeLastName() {
		return this.addresseeLastName;
	}

	public void setAddresseeLastName(String addresseeLastName) {
		this.addresseeLastName = addresseeLastName;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return this.addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return this.addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZipcode() {
		return this.addressZipcode;
	}

	public void setAddressZipcode(String addressZipcode) {
		this.addressZipcode = addressZipcode;
	}

	public String getAddressCountry() {
		return this.addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getPayloadXml() {
		return payloadXml;
	}

	private String readFile(File inputPayloadXML) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputPayloadXML));
		String line;
		StringBuilder sb = new StringBuilder();

		while ((line = br.readLine()) != null) {
			sb.append(line.trim());
		}

		return sb.toString();
	}

	public void setPayloadXml(File payloadXmlFile) {
		if (payloadXmlFile != null) {
			try {
				this.payloadXml = this.readFile(payloadXmlFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public void setPayloadXml(String payloadXmlFile) {
		this.payloadXml = payloadXmlFile;
	}
	/**
	 * 
	 */
	public SendEmailInputData() {
		// TODO Auto-generated constructor stub
		super();
		String key = Long.toString(System.currentTimeMillis());
		this.setSourceSystemID(key);
		this.emailAddress = "oes.macys+" + this.getSourceSystemID() + "@gmail.com";
		this.postalSmsPhone = "1234567890";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
