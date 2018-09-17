package com.macys.sdt.projects.Customer.OESBB.utils.ui_csp;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.OESBB.utils.csp.SendEmailInputData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 *
 */
public class SendEmailPage {
	private WebDriver sendEmailPage;

	By mailTypeTxtBox = By.name("mailType");
	By mailSubTypeTxtBox = By.name("mailSubType");
	By msgSourceSystemIdDropDown = By.name("msgSourceSystemId");
	By mailSourceIdTxtBox = By.name("mailSourceId");
	By brandDropDown = By.name("brand");
	By deliverytypeDropDown = By.id("deliveryType");
	By emailPreference = By.name("emailPreference");
	By emailTxtBox = By.name("email");
	By contactPhoneTxtBox = By.name("contactPhone");
	By titleTxtBox = By.name("title");
	By firstNameTxtBox = By.name("firstName");
	By middleNameTxtBox = By.name("middleName");
	By lastNameTxtBox = By.name("lastName");
	By addressLine1TxtBox = By.name("addressLine1");
	By addressLine2TxtBox = By.name("addressLine2");
	By addressLine3TxtBox = By.name("addressLine3");
	By cityTxtBox = By.name("city");
	By stateTxtBox = By.name("state");
	By zipTxtBox = By.name("zip");
	By countryTxtBox = By.name("country");
	By loyaltyPayloadTxtBox = By.name("payload");
	By queueButton = By.name("formAction");
	By comPayloadTxtBox = By.name("webSitePayload");
	By rfgPayloadTxtBox = By.name("redFrogPayload");
	By filPayloadTextBox = By.name("payload");
	By ltyPayloadTextBox = By.name("loyaltyPayload");
	By eodPayloadTextBox = By.name("eodMessagePayload");
	By doubleOptionTextBox = By.name("doubleOptin");
	By errorMessage = By.xpath("//span[contains(text(),'Could not create message')]");
	By successMessage = By.xpath("//span[contains(text(),'Successfully created message')]");
	By mafPayloadTextBox = By.name("opsNotificationPayload");


	public SendEmailPage() {
		this.sendEmailPage = WebDriverManager.getWebDriver();
		// TODO Auto-generated constructor stub
	}

	public boolean elementExists(By byElement) {
		try {
			sendEmailPage.findElement(byElement);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void setMailType(String mailType) {
		sendEmailPage.findElement(mailTypeTxtBox).clear();
		sendEmailPage.findElement(mailTypeTxtBox).sendKeys(mailType);
	}

	public void setSubMailType(String subType) {
		sendEmailPage.findElement(mailSubTypeTxtBox).clear();
		sendEmailPage.findElement(mailSubTypeTxtBox).sendKeys(subType);
	}

	public void setMsgSourceSystemdId(String msgSourceSystemType) {
		new Select(sendEmailPage.findElement(msgSourceSystemIdDropDown)).selectByVisibleText(msgSourceSystemType);
	}

	public void setSourceId(String id) {
		sendEmailPage.findElement(mailSourceIdTxtBox).clear();
		sendEmailPage.findElement(mailSourceIdTxtBox).sendKeys(id);
	}

	public void selectBrand(String brand) {
		new Select(sendEmailPage.findElement(brandDropDown)).selectByVisibleText(brand);
	}

	public void selectDeliveryType(String deliveryType) {
		new Select(sendEmailPage.findElement(deliverytypeDropDown)).selectByVisibleText(deliveryType);
	}

	public void selectEmailPreference(String emailPreferenceType) {
		new Select(sendEmailPage.findElement(emailPreference)).selectByVisibleText(emailPreferenceType);
	}

	public void setEmail(String email) {
		sendEmailPage.findElement(emailTxtBox).clear();
		sendEmailPage.findElement(emailTxtBox).sendKeys(email);
	}

	public void setContactPhoneNumber(String phoneNbr) {
		sendEmailPage.findElement(contactPhoneTxtBox).clear();
		sendEmailPage.findElement(contactPhoneTxtBox).sendKeys(phoneNbr);
	}

	public void setTitle(String title) {
		sendEmailPage.findElement(titleTxtBox).clear();
		sendEmailPage.findElement(titleTxtBox).sendKeys(title);
	}

	public void setFirstName(String firstName) {
		sendEmailPage.findElement(firstNameTxtBox).clear();
		sendEmailPage.findElement(firstNameTxtBox).sendKeys(firstName);
	}

	public void setMiddleName(String middleName) {
		sendEmailPage.findElement(middleNameTxtBox).clear();
		sendEmailPage.findElement(middleNameTxtBox).sendKeys(middleName);
	}

	public void setLastName(String lastName) {
		sendEmailPage.findElement(lastNameTxtBox).clear();
		sendEmailPage.findElement(lastNameTxtBox).sendKeys(lastName);
	}

	public void setAddressLine1(String add1) {
		sendEmailPage.findElement(addressLine1TxtBox).clear();
		sendEmailPage.findElement(addressLine1TxtBox).sendKeys(add1);
	}

	public void setAddressLine2(String add2) {
		sendEmailPage.findElement(addressLine2TxtBox).clear();
		sendEmailPage.findElement(addressLine2TxtBox).sendKeys(add2);
	}

	public void setAddressLine3(String add3) {
		sendEmailPage.findElement(addressLine3TxtBox).clear();
		sendEmailPage.findElement(addressLine3TxtBox).sendKeys(add3);
	}

	public void setCity(String city) {
		sendEmailPage.findElement(cityTxtBox).clear();
		sendEmailPage.findElement(cityTxtBox).sendKeys(city);

	}

	public void setState(String state) {
		sendEmailPage.findElement(stateTxtBox).clear();
		sendEmailPage.findElement(stateTxtBox).sendKeys(state);
	}

	public void setZip(String zip) {
		sendEmailPage.findElement(zipTxtBox).clear();
		sendEmailPage.findElement(zipTxtBox).sendKeys(zip);
	}

	public void setCountry(String country) {
		sendEmailPage.findElement(countryTxtBox).clear();
		sendEmailPage.findElement(countryTxtBox).sendKeys(country);
	}

	public void setPayload(String payload, String sourceSystem) {
		if (sourceSystem.equals("FIL")) {
			sendEmailPage.findElement(filPayloadTextBox).sendKeys(Keys.TAB);
			sendEmailPage.findElement(filPayloadTextBox).clear();
			sendEmailPage.findElement(filPayloadTextBox).sendKeys(payload);
		}
		else if(sourceSystem.equals("MAF")) {
			sendEmailPage.findElement(mafPayloadTextBox).sendKeys(Keys.TAB);
			sendEmailPage.findElement(mafPayloadTextBox).clear();
			sendEmailPage.findElement(mafPayloadTextBox).sendKeys(payload);
		}
		else if (sourceSystem.equals("COM")) {
			sendEmailPage.findElement(comPayloadTxtBox).clear();
			sendEmailPage.findElement(comPayloadTxtBox).sendKeys(payload);
		}
		else if (sourceSystem.equals("RFG")) {
			sendEmailPage.findElement(rfgPayloadTxtBox).clear();
			sendEmailPage.findElement(rfgPayloadTxtBox).sendKeys(payload);
		}
		else if (sourceSystem.equals("LTY")) {
			sendEmailPage.findElement(ltyPayloadTextBox).clear();
			sendEmailPage.findElement(ltyPayloadTextBox).sendKeys(payload);
		}
		else if (sourceSystem.equals("EOD")) {
			sendEmailPage.findElement(eodPayloadTextBox).clear();
			sendEmailPage.findElement(eodPayloadTextBox).sendKeys(payload);
		}
	}


	public boolean processQueue() {
		this.sendEmailPage.findElement(mailTypeTxtBox).click();
		Clicks.hover(queueButton);
		Utils.threadSleep(1000, null);
		Clicks.click(queueButton);
		Wait.secondsUntilElementPresent(successMessage, 1);
		boolean retv = elementExists(successMessage);
		return retv;
	}

	public boolean populatePageWithData(SendEmailInputData sendEmailData) {
		if (sendEmailData.getTypeID() != null) {
			setMailType(sendEmailData.getTypeID());
		}
		if (sendEmailData.getSubTypeID() != null) {
			setSubMailType(sendEmailData.getSubTypeID());
		}

		if (sendEmailData.getSourceSystemType() != null) {
			setMsgSourceSystemdId(sendEmailData.getSourceSystemType());
		}
		if (sendEmailData.getSourceSystemID() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setSourceId(sendEmailData.getSourceSystemID());
		}
		if (sendEmailData.getBrand() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			selectBrand(sendEmailData.getBrand());
		}
		if (sendEmailData.getDeliveryType() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			selectDeliveryType(sendEmailData.getDeliveryType());
		}
		if (sendEmailData.getEmailPreference() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			selectEmailPreference(sendEmailData.getEmailPreference());
		}
		if (sendEmailData.getEmailAddress() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setEmail(sendEmailData.getEmailAddress());
		}

		if (sendEmailData.getPostalSmsPhone() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setContactPhoneNumber(sendEmailData.getPostalSmsPhone());
		}

		if (sendEmailData.getAddresseeTitle() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setTitle(sendEmailData.getAddresseeTitle());
		}

		if (sendEmailData.getAddresseeFirstName() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setFirstName(sendEmailData.getAddresseeFirstName());
		}

		if (sendEmailData.getAddresseeMiddleName() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setMiddleName(sendEmailData.getAddresseeMiddleName());
		}

		if (sendEmailData.getAddresseeLastName() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setLastName(sendEmailData.getAddresseeLastName());
		}

		if (sendEmailData.getAddressLine1() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setAddressLine1(sendEmailData.getAddressLine1());
		}
		if (sendEmailData.getAddressLine2() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setAddressLine2(sendEmailData.getAddressLine2());
		}

		if (sendEmailData.getAddressLine3() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setAddressLine3(sendEmailData.getAddressLine3());
		}

		if (sendEmailData.getAddressCity() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setCity(sendEmailData.getAddressCity());
		}
		if (sendEmailData.getAddressState() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setState(sendEmailData.getAddressState());
		}
		if (sendEmailData.getAddressZipcode() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setZip(sendEmailData.getAddressZipcode());
		}

		if (sendEmailData.getAddressCountry() != null && !sendEmailData.getSourceSystemType().equals("EOD")) {
			setCountry(sendEmailData.getAddressCountry());
		}
		if (sendEmailData.getPayloadXml() != null) {
			setPayload(sendEmailData.getPayloadXml(), sendEmailData.getSourceSystemType());
		}
		return processQueue();
	}

}
