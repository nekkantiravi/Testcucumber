/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.db;
import java.sql.Blob;
import java.sql.Date;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 * This is Entity class reflects Message Table columns 
 */
public class Message {

	long mailID; // MAIL_ID NUMBER(38) NOT NULL,
	String mailSourceID; // MAIL_SOURCE_ID VARCHAR2(47) NOT NULL,
	String mailSourceSysID; // MAIL_SOURCE_SYS_ID CHAR(3) NOT NULL,
	String brandID; // BRAND_ID CHAR(8) NOT NULL,
	String mailTyp; // MAIL_TYP CHAR(3) NOT NULL,
	String mailSubTyp; // MAIL_SUB_TYP VARCHAR2(10),
	long contactID; // CONTACT_ID NUMBER(38) NOT NULL,
	String origMailID; // ORIG_MAIL_ID NUMBER(38),
	String resendUserID; // RESEND_USER_ID VARCHAR2(20),
	String msgStatCD; // MSG_STAT_CD CHAR(2) NOT NULL,
	Blob origMsgTxt; // ORIG_MSG_TXT BLOB NOT NULL,
	Date createTS; // CREATE_TS TIMESTAMP NOT NULL,
	Date lastUpdTS; // LAST_UPD_TS TIMESTAMP NOT NULL,
	String mailDeliveryTyp; // MAIL_DELIVERY_TYP CHAR(1) NOT NULL,
	long versionNbr; // VERSION_NBR NUMBER(38),
	String msgStatDetail; // MSG_STAT_DETAIL VARCHAR2(300),
	String origMsgStatCD; // ORIG_MSG_STAT_CD CHAR(2),
	String trackingID; // TRACKING_ID VARCHAR2(256),

	public long getMailID() {
		return mailID;
	}

	public void setMailID(long mailID) {
		this.mailID = mailID;
	}

	public String getMailSourceID() {
		return mailSourceID;
	}

	public void setMailSourceID(String mailSourceID) {
		this.mailSourceID = mailSourceID;
	}

	public String getMailSourceSysID() {
		return mailSourceSysID;
	}

	public void setMailSourceSysID(String mailSourceSysID) {
		this.mailSourceSysID = mailSourceSysID;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getMailTyp() {
		return mailTyp;
	}

	public void setMailTyp(String mailTyp) {
		this.mailTyp = mailTyp;
	}

	public String getMailSubTyp() {
		return mailSubTyp;
	}

	public void setMailSubTyp(String mailSubTyp) {
		this.mailSubTyp = mailSubTyp;
	}

	public long getContactID() {
		return contactID;
	}

	public void setContactID(long contactID) {
		this.contactID = contactID;
	}

	public String getOrigMailID() {
		return origMailID;
	}

	public void setOrigMailID(String origMailID) {
		this.origMailID = origMailID;
	}

	public String getResendUserID() {
		return resendUserID;
	}

	public void setResendUserID(String resendUserID) {
		this.resendUserID = resendUserID;
	}

	public String getMsgStatCD() {
		return msgStatCD;
	}

	public void setMsgStatCD(String msgStatCD) {
		this.msgStatCD = msgStatCD;
	}

	public Blob getOrigMsgTxt() {
		return origMsgTxt;
	}

	public void setOrigMsgTxt(Blob origMsgTxt) {
		this.origMsgTxt = origMsgTxt;
	}

	public Date getCreateTS() {
		return createTS;
	}

	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}

	public Date getLastUpdTS() {
		return lastUpdTS;
	}

	public void setLastUpdTS(Date lastUpdTS) {
		this.lastUpdTS = lastUpdTS;
	}

	public String getMailDeliveryTyp() {
		return mailDeliveryTyp;
	}

	public void setMailDeliveryTyp(String mailDeliveryTyp) {
		this.mailDeliveryTyp = mailDeliveryTyp;
	}

	public long getVersionNbr() {
		return versionNbr;
	}

	public void setVersionNbr(long versionNbr) {
		this.versionNbr = versionNbr;
	}

	public String getMsgStatDetail() {
		return msgStatDetail;
	}

	public void setMsgStatDetail(String msgStatDetail) {
		this.msgStatDetail = msgStatDetail;
	}

	public String getOrigMsgStatCD() {
		return origMsgStatCD;
	}

	public void setOrigMsgStatCD(String origMsgStatCD) {
		this.origMsgStatCD = origMsgStatCD;
	}

	public String getTrackingID() {
		return trackingID;
	}

	public void setTrackingID(String trackingID) {
		this.trackingID = trackingID;
	}

	/**
	 * 
	 */
	public Message(long mailID, String mailSourceID, String mailSourceSysID, String brandID, String mailTyp,
			String mailSubTyp, long contactID, String origMailID, String resendUserID, String msgStatCD,
			Blob origMsgTxt, Date createTS, Date lastUpdTS, String mailDeliveryTyp, long versionNbr,
			String msgStatDetail, String origMsgStatCD, String trackingID) {

		this.mailID = mailID;
		this.mailSourceID = mailSourceID;
		this.mailSourceSysID = mailSourceSysID;
		this.brandID = brandID;
		this.mailTyp = mailTyp;
		this.mailSubTyp = mailSubTyp;
		this.contactID = contactID;
		this.origMailID = origMailID;
		this.resendUserID = resendUserID;
		this.msgStatCD = msgStatCD;
		this.origMsgTxt = origMsgTxt;
		this.createTS = createTS;
		this.lastUpdTS = lastUpdTS;
		this.mailDeliveryTyp = mailDeliveryTyp;
		this.versionNbr = versionNbr;
		this.msgStatDetail = msgStatDetail;
		this.origMsgStatCD = origMsgStatCD;
		this.trackingID = trackingID;

	}

	public Message() {

	}

}
