/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.db;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 *
 */
public class ArchivedMessage {

	int mailID; // MAIL_ID NUMBER NOT NULL,
	String mailSourceID; // MAIL_SOURCE_ID VARCHAR2(47) NOT NULL,
	String mailSourceSysID; // MAIL_SOURCE_SYS_ID CHAR(3) NOT NULL,
	String brandID; // BRAND_ID CHAR(8) NOT NULL,
	String mailTyp; // MAIL_TYP CHAR(3) NOT NULL,
	String mailSubTyp; // MAIL_SUB_TYP VARCHAR2(10),
	int contactID; // CONTACT_ID NUMBER NOT NULL,
	int origMailID; // ORIG_MAIL_ID NUMBER,
	String resendUserID; // RESEND_USER_ID VARCHAR2(20),
	String mailSubject; // MAIL_SUBJECT VARCHAR2(254),
	String mailTXT; // MAIL_TXT BLOB,
	String mailHtml; // MAIL_HTML BLOB,
	String mailHardCopyTxt; // MAIL_HARDCOPY_TXT BLOB,
	Date msgReceivedTs; // MSG_RECEIVED_TS TIMESTAMP NOT NULL,
	Date createTS; // CREATE_TS TIMESTAMP NOT NULL,
	Date lastUPDTS; // LAST_UPD_TS TIMESTAMP NOT NULL,
	String mailDeliveryTyp; // MAIL_DELIVERY_TYP CHAR(1) DEFAULT 'E' NOT NULL,
	String enchancePayload; // ENHANCE_PAYLOAD BLOB

	public int getMailID() {
		return mailID;
	}

	public void setMailID(int mailID) {
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

	public int getContactID() {
		return contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public int getOrigMailID() {
		return origMailID;
	}

	public void setOrigMailID(int origMailID) {
		this.origMailID = origMailID;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailTXT() {
		return mailTXT;
	}

	public void setMailTXT(String mailTXT) {
		this.mailTXT = mailTXT;
	}

	public String getMailHtml() {
		return mailHtml;
	}

	public void setMailHtml(String mailHtml) {
		this.mailHtml = mailHtml;
	}

	public String getMailHardCopyTxt() {
		return mailHardCopyTxt;
	}

	public void setMailHardCopyTxt(String mailHardCopyTxt) {
		this.mailHardCopyTxt = mailHardCopyTxt;
	}

	public Date getMsgReceivedTs() {
		return msgReceivedTs;
	}

	public void setMsgReceivedTs(Date msgReceivedTs) {
		this.msgReceivedTs = msgReceivedTs;
	}

	public Date getCreateTS() {
		return createTS;
	}

	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}

	public Date getLastUPDTS() {
		return lastUPDTS;
	}

	public void setLastUPDTS(Date lastUPDTS) {
		this.lastUPDTS = lastUPDTS;
	}

	public String getMailDeliveryTyp() {
		return mailDeliveryTyp;
	}

	public void setMailDeliveryTyp(String mailDeliveryTyp) {
		this.mailDeliveryTyp = mailDeliveryTyp;
	}

	public String getEnchancePayload() {
		return enchancePayload;
	}

	public void setEnchancePayload(String enchancePayload) {
		this.enchancePayload = enchancePayload;
	}

	/**
	 * 
	 */
	public ArchivedMessage(int mailID, String mailSourceID, String mailSourceSysID, String brandID, String mailTyp,
			String mailSubTyp, int contactID, int origMailID, String resendUserID, String mailSubject, Blob mailTXT,
			Blob mailHtml, Blob mailHardCopyTxt, Date msgReceivedTs, Date createTS, Date lastUPDTS,
			String mailDeliveryTyp, Blob enchancePayload) {
		// TODO Auto-generated constructor stub
		this.mailID = mailID; // MAIL_ID NUMBER NOT NULL,
		this.mailSourceID = mailSourceID; // MAIL_SOURCE_ID VARCHAR2(47) NOT
											// NULL,
		this.mailSourceSysID = mailSourceSysID; // MAIL_SOURCE_SYS_ID CHAR(3)
												// NOT NULL,
		this.brandID = brandID; // BRAND_ID CHAR(8) NOT NULL,
		this.mailTyp = mailTyp; // MAIL_TYP CHAR(3) NOT NULL,
		this.mailSubTyp = mailSubTyp; // MAIL_SUB_TYP VARCHAR2(10),
		this.contactID = contactID; // CONTACT_ID NUMBER NOT NULL,
		this.origMailID = origMailID; // ORIG_MAIL_ID NUMBER,
		this.resendUserID = resendUserID; // RESEND_USER_ID VARCHAR2(20),
		this.mailSubject = mailSubject; // MAIL_SUBJECT VARCHAR2(254),
		this.mailTXT = this.getStringFromBLOB(mailTXT);
		this.mailHtml = this.getStringFromBLOB(mailHtml); // MAIL_HTML BLOB,
		this.mailHardCopyTxt = this.getStringFromBLOB(mailHardCopyTxt); // MAIL_HARDCOPY_TXT
																		// BLOB,
		this.msgReceivedTs = msgReceivedTs; // MSG_RECEIVED_TS TIMESTAMP NOT
											// NULL,
		this.createTS = createTS; // CREATE_TS TIMESTAMP NOT NULL,
		this.lastUPDTS = lastUPDTS; // LAST_UPD_TS TIMESTAMP NOT NULL,
		this.mailDeliveryTyp = mailDeliveryTyp; // MAIL_DELIVERY_TYP CHAR(1)
												// DEFAULT 'E' NOT NULL,
		this.enchancePayload = this.getStringFromBLOB(enchancePayload); // ENHANCE_PAYLOAD
																		// BLOB

	}

	private String getStringFromBLOB(Blob blob) {
		if (blob == null) {
			return null;
		}
		byte[] bdata = null;
		try {
			bdata = blob.getBytes(1, (int) blob.length());

		} catch (SQLException ex) {
			Logger.getLogger(ArchivedMessage.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new String(bdata);
	}

	public String getEnchancedPayload() {
		return this.enchancePayload;
	}

}
