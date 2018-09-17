package com.macys.sdt.projects.Customer.OESBB.utils.input.security;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author nmangali
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "security")
public class QA613_1A_BLCOM {

	protected String firstName;
	protected String lastName;
	protected String hoursCS;
	protected String phoneNbrCS;
	protected String url;	
	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the hoursCS
	 */
	public String getHoursCS() {
		return hoursCS;
	}
	
	
	/**
	 * @param hoursCS
	 *            the hoursCS to set
	 */
	public void setHoursCS(String hoursCS) {
		this.hoursCS = hoursCS;
	}


	/**
	 * @return the phoneNbrCS
	 */
	public String getPhoneNbrCS() {
		return phoneNbrCS;
	}
	
	/**
	 * @param phoneNbrCS
	 *            the phoneNbrCS to set
	 */
	public void setPhoneNbrCS(String phoneNbrCS) {
		this.phoneNbrCS = phoneNbrCS;
	}
	

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
	