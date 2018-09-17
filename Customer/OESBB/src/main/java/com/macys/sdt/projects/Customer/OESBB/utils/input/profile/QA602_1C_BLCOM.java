package com.macys.sdt.projects.Customer.OESBB.utils.input.profile;


/**
 * @author sbharadwaja
 *
 */

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="SUPCCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reportAbuseURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oneTimePwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profileMobileNbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profileFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oneTimePwdExpTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="promoDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="promoLegalDisclaimer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
 *         &lt;/element>
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

@XmlType(name = "", propOrder = { "SUPCCode", "url", "reportAbuseURL",
		"oneTimePwd", "profileMobileNbr", "profileFirstName",
		"oneTimePwdExpTime", "promoDescription", "promoLegalDisclaimer",
		"emailAddress" })
@XmlRootElement(name = "profile")
public class QA602_1C_BLCOM {

	@XmlElement(required = true)
	protected String SUPCCode;
	@XmlElement(required = true)
	protected String url;
	@XmlElement(required = true)
	protected String reportAbuseURL;
	@XmlElement(required = true)
	protected String oneTimePwd;
	@XmlElement(required = true)
	protected String profileMobileNbr;
	@XmlElement(required = true)
	protected String profileFirstName;
	@XmlElement(required = true)
	protected String oneTimePwdExpTime;
	@XmlElement(required = true)
	protected String promoDescription;
	@XmlElement(required = true)
	protected String promoLegalDisclaimer;
	@XmlElement(required = true)
	protected String emailAddress;
	
	

	/**
	 * @return the sUPCCode
	 */
	public String getSUPCCode() {
		return SUPCCode;
	}

	/**
	 * @param sUPCCode
	 *            the sUPCCode to set
	 */
	public void setSUPCCode(String sUPCCode) {
		SUPCCode = sUPCCode;
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

	/**
	 * @return the reportAbuseURL
	 */
	public String getReportAbuseURL() {
		return reportAbuseURL;
	}

	/**
	 * @param reportAbuseURL
	 *            the reportAbuseURL to set
	 */
	public void setReportAbuseURL(String reportAbuseURL) {
		this.reportAbuseURL = reportAbuseURL;
	}

	/**
	 * @return the oneTimePwd
	 */
	public String getOneTimePwd() {
		return oneTimePwd;
	}

	/**
	 * @param oneTimePwd
	 *            the oneTimePwd to set
	 */
	public void setOneTimePwd(String oneTimePwd) {
		this.oneTimePwd = oneTimePwd;
	}

	/**
	 * @return the profileFirstName
	 */
	public String getProfileFirstName() {
		return profileFirstName;
	}

	/**
	 * @param profileMobileNbr
	 *            the profileMobileNbr to set
	 */
	public void setProfileMobileNbr(String profileMobileNbr) {
		this.profileMobileNbr = profileMobileNbr;
	}

	/**
	 * @return the profileMobileNbr
	 */
	public String getProfileMobileNbr() {
		return profileMobileNbr;
	}

	/**
	 * @param profileFirstName
	 *            the profileFirstName to set
	 */
	public void setProfileFirstName(String profileFirstName) {
		this.profileFirstName = profileFirstName;
	}
	
	
	/**
	 * @return the oneTimePwdExpTime
	 */
	public String getOneTimePwdExpTime() {
		return oneTimePwdExpTime;
	}

	/**
	 * @param oneTimePwdExpTime
	 *            the oneTimePwdExpTime to set
	 */
	public void setOneTimePwdExpTime(String oneTimePwdExpTime) {
		this.oneTimePwdExpTime = oneTimePwdExpTime;
	}

	/**
	 * @return the promoDescription
	 */
	public String getPromoDescription() {
		return promoDescription;
	}

	/**
	 * @param promoDescription
	 *            the promoDescription to set
	 */
	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	/**
	 * @return the promoLegalDisclaimer
	 */
	public String getPromoLegalDisclaimer() {
		return promoLegalDisclaimer;
	}

	/**
	 * @param promoLegalDisclaimer
	 *            the promoLegalDisclaimer to set
	 */
	public void setPromoLegalDisclaimer(String promoLegalDisclaimer) {
		this.promoLegalDisclaimer = promoLegalDisclaimer;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
