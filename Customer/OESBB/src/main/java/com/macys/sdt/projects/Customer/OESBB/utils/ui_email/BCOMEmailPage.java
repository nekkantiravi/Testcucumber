/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.ui_email;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.macys.sdt.framework.runner.WebDriverManager;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author m509575
 * 
 */
public class BCOMEmailPage {

	@FindBy(css = "img[alt=\"Bloomingdale's\"]")
	public WebElement logoBloomingdales;

	@FindBy(css = "img[alt=\"Bloomindale's\"]")
	public WebElement bloomingdalesLogo;

	@FindBy(css = "img[alt=\"The Registry at bloomingdale's\"]")
	public WebElement logoBloomingdalesRegistry;

	@FindBy(css = "table:nth-child(8) tbody:nth-child(1) tr:nth-child(2)>td img")
	public WebElement bMoneyLogo;

	@FindBy(css = "img[alt=\"loyallist shipping\"]")
	public WebElement loyallistShipping;

	@FindBy(xpath = "//table[7]/tbody/tr/td/table/tbody/tr[6]/td[1]")
	public WebElement exptext;

	@FindBy(xpath = "//td[contains(text(), 'WISHLIST')]")
	public WebElement singleHeader;

	@FindBy(css = "td.left.name")
	public WebElement wishlistSingleText;

	@FindBy(css = "img[alt=\"Free shipping\"]")
	public WebElement freeShipping;

	@FindBy(xpath = "/table[11]/tbody/tr/td/table[1]/tbody/tr[2]")
	public WebElement instore;

	@FindBy(xpath = "//table[6]/tbody/tr/td/table/tbody/tr[4]/td[1]")
	public WebElement bodystatictext;

	@FindBy(xpath = "//table[7]/tbody/tr/td/table/tbody/tr[6]/td[1]")
	public WebElement shipstatictext;

	@FindBy(css = "td.left.headerMsg.fname")
	public WebElement shipfirstname;

	@FindBy(xpath = "//span[@class='inStorePickup-mobileNumber']")
	public WebElement phoneNo;

	@FindAll({ @FindBy(css = "td.left.headermsg") })
	List<WebElement> labelDetails;
	
	@FindAll({ @FindBy(css = "td.signature") })
	List<WebElement> signatureLabel;

	@FindAll({ @FindBy(css = "table.twelve.columns>tbody>tr>td") })
	List<WebElement> redeemDetails;

	@FindBy(css = "td.macys-preheader")
	public WebElement preHeader;

	@FindAll({ @FindBy(css = "td.left.header.prosPanel-1 > a > img") })
	public List<WebElement> itemImage;

	@FindAll({ @FindBy(css = "td.left.header.prosPanel-brandName1 > a") })
	List<WebElement> itemName;

	@FindAll({ @FindBy(xpath = "//table[1]/tbody/tr[2]/td") })
	List<WebElement> zonenameText;

	@FindBy(css = "td.left.header")
	public WebElement header;

	@FindBy(css = "td.left.name")
	public WebElement firstnameText;

	@FindBy(css = "td.left.headermsg")
	public WebElement staticMessageText;

	@FindBy(css = "td.left.headermsg.refund-Amt.text-wrapping")
	public WebElement reductionReasonMessageText;
	
	@FindBy(css = "td.left.headermsg")
	public WebElement staticFedfilDelayText;

	@FindBy(xpath = "//tr[8]/td/table/tbody/tr/td[2]")
	public WebElement staticMessageShippingPolicy;

	@FindBy(xpath = "//td[@class='left headermsg' and contains(text(),'Order Number:')]/following-sibling::td[1]")
	public WebElement orderNumber;

	@FindBy(xpath = "//td[@class='left headermsg toPickUp-address1']/a")
	public WebElement storeAddress;

	@FindBy(xpath = "//td [@class='left headermsg' and contains(text(),'Pickup')]")
	public WebElement storePickupInstructions;

	@FindBy(xpath = "//tr[5]/td [@class='left headermsg']")
	public WebElement pickupBarcode;

	@FindAll({ @FindBy(xpath = "//td[@class='left headermsg' and contains(text(),'Order Date:')]/following-sibling::td[1]") })
	List<WebElement> orderDate;

	@FindBy(xpath = "//td[contains(text(), 'Refunded to')]")
	public WebElement refundedTo;

	@FindBy(xpath = "//table[8]/tbody/tr/td/table/tbody/tr[6]")
	public WebElement billingAddress;

	@FindBy(xpath = "//td[@class='left headermsg' and contains(text(),'In-Store Pickup')]")
	public WebElement inStorePickup;

	@FindBy(xpath = "//td[@class='left headermsg' and contains(text(),'In-Store Pickup')]/following-sibling::td[1]")
	public WebElement inStorePickupValue;

	@FindBy(xpath = "//td[@class='left headermsg'and contains(text(),'Gift Receipt:')]")
	public WebElement giftReceipt;

	@FindBy(linkText = "CHECK ORDER STATUS")
	public WebElement checkOrderStatusButton;

	@FindBy(css = "td.left.header")
	public WebElement instoreitems;

	@FindBy(xpath = "//td[contains(text(), 'Changes')]")
	public WebElement noteText;

	@FindBy(xpath = "//tr[4]/td[contains(text(), 'L')]")
	public WebElement loyallistId;

	@FindBy(xpath = "//td[contains(text(), 'looks good')]")
	public WebElement loyallistBodyText;

	@FindBy(xpath = "//tr[2]/td/table/tbody/tr[2]")
	public WebElement deliveryMethod;

	@FindBy(xpath = "//tr[3]/td/table/tbody/tr[2]")
	public WebElement shippingAddress;

	@FindBy(xpath = "//table[12]/tbody/tr/td/table/tbody/tr[2]/td")
	public WebElement itemDetailsText;

	@FindBy(css = "a > span")
	public WebElement productName;

	@FindBy(css = "td.left > a > img")
	public WebElement productImage;

	@FindBy(css = "td.left.headermsg > table.0.0")
	public WebElement productQtyCaption;

	@FindBy(css = "td.left.headermsg > table.0.1")
	public WebElement productQtyValue;

	@FindBy(css = "td.left.headermsg > table.2.0")
	public WebElement productColorCaption;

	@FindBy(css = "td.left.headermsg > table.2.1")
	public WebElement productColorValue;

	@FindBy(xpath = "//tbody/tr[2]/td[@class='left headermsg']")
	public WebElement storeTimeAndPhoneNos;

	@FindBy(xpath = "//td[@class='left headermsg'and contains(text(),'NOTE:')]")
	public WebElement noteSection;

	@FindBy(xpath = "//td[@class='left headermsg'and contains(text(),'also notified')]")
	public WebElement orderReadyText;

	@FindBy(xpath = "//tr[2]/td/span[2]")
	public WebElement productTypeCaption;

	@FindBy(xpath = "//td/table/tbody/tr/td/table/tbody/tr[5]/td/span")
	public WebElement productTypeValue;

	@FindBy(xpath = "//td[@class='left headermsg' and contains(text(),'Contact Information:')]/following-sibling::td[1]")
	public WebElement contactInformation;

	@FindBy(xpath = "td.left.headermsg > table.1.0")
	public WebElement productSizeCaption;

	@FindBy(xpath = "td.left.headermsg > table.1.1")
	public WebElement productSizeValue;

	@FindBy(css = "td.wrapper.last > table.seven.columns.5.0")
	public WebElement productPrice;

	@FindBy(css = "td.order.payment-cardtype")
	public WebElement paymentmenthodText;

	@FindBy(xpath = "//tbody/tr[8]/td[@class='left headermsg']")
	public WebElement paymentTypeForMaf;

	@FindBy(css = "td.order.refund-Amt")
	public WebElement refundAmountText;

	@FindBy(linkText = "SHOP E-GIFT CARDS")
	public WebElement shopmaysButton;

	@FindAll({ @FindBy(css = "td.left.headerMsg") })
	public List<WebElement> smsOptMsg;

	@FindAll(@FindBy(css = "td.left.name"))
	public List<WebElement> upsInfo;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Return Submitted Date:')]/../td[2]")
	public WebElement returnSubmittedDate;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Expected Refund Amount:')]/../td[2]")
	public WebElement expectedRefundAmount;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Amount Refunded:')]/../td[2]")
	public WebElement amountRefunded;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Estimated refund:')]/../td[2]")
	public WebElement estimatedRefundAmount;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Original Payment:')]/../td[2]")
	public WebElement originalPayment;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Refund Total:')]/../td[2]")
	public WebElement refundTotal;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Refund Amount:')]/../td[2]")
	public WebElement refundAmount;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Reason for replacement')]")
	public WebElement reasonForReplacementLabel;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Reason for replacement')]/../td[2]")
	public WebElement reasonForReplacement;

	@FindBy(css = "img.giftcard")
	public WebElement giftCardImage;

	@FindBy(css = "td.left.store.gift-card-number")
	public WebElement giftCardNumber;

	@FindBy(css = "span.store")
	public WebElement giftCardText;

	@FindBy(css = "td.left.navText.total-amt")
	public WebElement loyaltyRewardCardAmount;

	@FindBy(css = "td.left.headerMsg")
	public WebElement promoDesc;

	@FindBy(linkText = "SHOP NOW")
	public WebElement shopNow;

	@FindBy(css = "td.left.header.header-title")
	public WebElement promoHeading;

	@FindAll({ @FindBy(css = "td.left.header.prosPanel-1 > a > img") })
	public List<WebElement> prosImages;

	@FindAll({ @FindBy(css = "td.left.header.prosPanel-brandName2 > a") })
	public List<WebElement> prosProductNames;

	@FindBy(css = "td.left.headermsg.loyallistAccount-pendingPoints")
	public WebElement loyaltyPendingPoints;
	
	@FindBy(css = "td.left.headermsg.loyallistAccount-basePoints")
	public WebElement loyaltyBasePoints;
	
	@FindBy(css = "td.left.headermsg.loyallistAccount-bonusPoints")
	public WebElement loyaltyBonusPoints;
	
	@FindBy(css = "td.left.headermsg.loyallistAccount-totalPoints")
	public WebElement loyaltyTotalPoints;
	
	@FindBy(css = "td.left.headermsg.loyallistAccount-totalPendingPoints")
	public WebElement loyaltyTotalPendingPoints;
	
	@FindBy(css = "td.left.loyallistAccount-name")
	public WebElement loyallistName;
	
	@FindBy(css = "span.loyallistAccount-number")
	public WebElement loyallistAccountNumber;
	
	@FindBy(css = "td.left.loyallistAccount-email")
	public WebElement loyallistEmail;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(4)>td")
	public WebElement bMoneyEarnText;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(6)>td")
	public WebElement bMoneyEarnAmount;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(8)>td")
	public WebElement bMoneyRedeemText;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(10)>td")
	public WebElement bMoneyEffAndExpDates;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(12)>td")
	public WebElement bMoneyExpDate;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(14)>td")
	public WebElement bMoneyRewardCardNumberTxt;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(16)>td")
	public WebElement bMoneyRewardCIDTxt;

	@FindBy(css = "img[class*=\"barcodeimage\"]")
	public WebElement bMoneyBarCodeImage;

	@FindBy(css = "table:nth-child(8)>tbody table>tbody>tr:nth-child(21)>td")
	public WebElement bMoneyDisclimerText;
	
	private boolean acceptNextAlert = true;

	private HashMap<String, String> orderTotal = null;
	private HashMap<String, String> orderDetail = null;
	private Multimap<String, String> shipmentDetails = null;
	private Multimap<String, String> itemDetails = null;
	public String pageSourceText = null;

    private static final Logger log = LoggerFactory.getLogger(BCOMEmailPage.class);

	public HashMap<String, String> getOrderTotal() {
		if (this.orderTotal.size() == 0) {
			this.buildOrderTotal();
		}
		return orderTotal;
	}

	private void buildOrderTotal() {
		WebElement ordertotal;
		List<WebElement> tds;
		
		if (pageSourceText == null){
			pageSourceText = WebDriverManager.getWebDriver().getPageSource();
		}
		
		if (pageSourceText.contains("SUBTOTAL")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td/span[contains(text(),'SUBTOTAL')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("subtotal", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		if (pageSourceText.contains("Standard Shipping")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Standard Shipping')]"));
			
			
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
		
			orderTotal
					.put("standard_shipping", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		if (pageSourceText.contains("Expedited Delivery")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Expedited Delivery')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			
			
			orderTotal.put("expedited_delivery", tds.get(1).getText()
					.toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if (pageSourceText.contains("Additional Shipping Address")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Additional Shipping Address')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("additional_shipping_address", tds.get(1).getText()
					.toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if (pageSourceText.contains("Gift Wrap")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Gift Wrap')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("gift_wrap", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if (pageSourceText.contains("Sales Tax")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Sales Tax')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("sales_tax", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		if (pageSourceText.contains("ORDER TOTAL")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'ORDER TOTAL')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("order_total", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		if (pageSourceText.contains("************")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td/span[contains(text(),'************')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("card", tds.get(0).getText().toString());
			orderTotal.put("charge_amount", tds.get(1).getText());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		else{
			try {
				ordertotal = WebDriverManager.getWebDriver()
						.findElement(By
								.xpath("//tr/td[text()='PAYMENT METHOD']/parent::tr/following-sibling::tr[2]/td[1]"));
				tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
				orderTotal.put("card", tds.get(0).getText().toString());
				orderTotal.put("charge_amount", tds.get(1).getText());
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}

		}
		
		
		if (pageSourceText.contains("In-Store Pickup")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td/span[contains(text(),In-Store Pickup')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal.put("in_store_pickup", tds.get(0).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if (pageSourceText.contains("Same Day Delivery")){
		try {
			ordertotal = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td/span[contains(text(),'Same Day Delivery')]"));
			tds = this.getParentTR(ordertotal).findElements(By.tagName("td"));
			orderTotal
					.put("same_day_delivery", tds.get(0).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
	}

	public HashMap<String, String> getOrderDetails() {
		if (this.orderDetail.size() == 0) {
			this.buildOrderDetail();
		}
		return orderDetail;
	}

	private void buildOrderDetail() {
		WebElement order;
		List<WebElement> tds;

		if (pageSourceText == null){
			pageSourceText = WebDriverManager.getWebDriver().getPageSource();
		}
		
		if(pageSourceText.contains("Order Number:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Order Number:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("order_number", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if(pageSourceText.contains("Order number:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Order number:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("order_number", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		if(pageSourceText.contains("Order Date:")){
		
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Order Date:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("order_date", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		if(pageSourceText.contains("Order date:")){
		
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Order date:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("order_date", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		
		if(pageSourceText.contains("Estimated Ship Date:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Estimated Ship Date:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("estimated_ship_date", tds.get(1).getText()
					.toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
		}
		
		if(pageSourceText.contains("Payment Method:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Payment Method:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("payment_method", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		if(pageSourceText.contains("Estimated Refund:") || pageSourceText.contains("Total Refunded:")){
		try {

			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Estimated Refund:') or contains(text(),'Total Refunded:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail
					.put("estimated_refund", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if(pageSourceText.contains("Billing Address:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Billing Address:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("billing_address", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		
		if(pageSourceText.contains("Billing address:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Billing address:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("billing_address", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if(pageSourceText.contains("Original form of tender:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Original form of tender:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("original_form_of_tender", tds.get(1).getText()
					.toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if(pageSourceText.contains("Reason for return:") || pageSourceText.contains("Reason for Return:")){
		try {

			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Reason for return:') or contains(text(),'Reason for Return:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("reason_for_return", tds.get(1).getText()
					.toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		
		if(pageSourceText.contains("Reason for replacement:") || pageSourceText.contains("Reason for Replacements:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Reason for replacement:') or contains(text(),'Reason for Replacements:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("reason_for_replacement", tds.get(1).getText()
					.toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		
		if(pageSourceText.contains("Replacement Date:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Replacement Date:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail
					.put("replacement_date", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		if(pageSourceText.contains("Return completed Date:") || pageSourceText.contains("Return Completed Date:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Return completed Date:') or contains(text(),'Return Completed Date:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("return_date", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if(pageSourceText.contains("Original payment:") || pageSourceText.contains("Original Payment:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Original payment:') or contains(text(),'Original Payment:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail
					.put("original_payment", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

		
		if(pageSourceText.contains("Card Credited:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Card Credited:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("card_credited", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		if(pageSourceText.contains("Shipping Address:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Shipping Address:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail
					.put("shipping_address", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}
		
		
		
		if(pageSourceText.contains("Amount Refunded:")){
		try {
			order = WebDriverManager.getWebDriver()
					.findElement(By
							.xpath("//table/tbody/tr/td[contains(text(),'Amount Refunded:')]"));
			tds = this.getParentTR(order).findElements(By.tagName("td"));
			orderDetail.put("amount_refunded", tds.get(1).getText().toString());
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		}

	}

	public Multimap<String, String> getShipmentDetails() {
		if (this.shipmentDetails.size() == 0) {
			this.buildShipmentDetails();
		}
		return this.shipmentDetails;
	}

	private WebElement getParentTable(WebElement td) {
		WebElement currentTable = (WebElement) ((JavascriptExecutor) WebDriverManager.getWebDriver())
				.executeScript("return arguments[0].parentNode;", td);
		WebElement parentTable = currentTable;
		while (!parentTable.getAttribute("outerHTML").contains("<body")) {
			currentTable = parentTable;
			parentTable = (WebElement) ((JavascriptExecutor) WebDriverManager.getWebDriver())
					.executeScript("return arguments[0].parentNode;",
							currentTable);
		}
		return currentTable;
	}

	private WebElement getParentTR(WebElement td) {
		WebElement currentTable = (WebElement) ((JavascriptExecutor) WebDriverManager.getWebDriver())
				.executeScript("return arguments[0].parentNode;", td);
		WebElement parentTable = currentTable;
		while (!parentTable.getAttribute("outerHTML").contains("<tr")) {
			currentTable = parentTable;
			parentTable = (WebElement) ((JavascriptExecutor) WebDriverManager.getWebDriver())
					.executeScript("return arguments[0].parentNode;",
							currentTable);
		}
		return parentTable;
	}

	private void buildShipmentDetails() {
		List<WebElement> tds = WebDriverManager.getWebDriver()
				.findElements(By
						.xpath("//table/tbody/tr/td[contains(text(),'SHIPMENT DETAILS')]")); //
		for (WebElement td : tds) {
			WebElement tab = getParentTable(td);
			List<WebElement> tables = tab.findElements(By.tagName("table"));
			for (WebElement table : tables) {
				String tblText = table.getText();
				if (tblText.contains("Tracking #:") || tblText.contains("Delivery Method:")
						|| tblText.contains("Shipping Address")) {
					List<WebElement> rows;
					if (table.findElements(By.tagName("table")).size() > 0) {
						continue;
					} else {
						rows = table.findElements(By.tagName("tr"));
					}
					for (WebElement tr : rows) {
						List<WebElement> columns = tr.findElements(By
								.tagName("td"));
						if (tr.getText().equals("")) {
							continue;
						}
						for (int col = 0; col < columns.size(); col++) {
							String text = columns.get(col).getText();
							if (text.equals("")) {
								continue;
							}
							if (text.contains("Tracking #:")) {
								shipmentDetails.put("tracking_num", columns
										.get(col + 1).getText().toString());
								break;
							}
							
							if (text.contains("Delivery Method:")) {
								shipmentDetails.put("delivery_method", columns
										.get(col + 1).getText().toString());
								break;
							}
							if (text.contains("Shipping Address:")) {
								shipmentDetails.put("shipping_address", columns
										.get(col + 1).getText());
								break;
							}
							if (text.contains("Send to:")) {
								shipmentDetails.put("send_to",
										columns.get(col + 1).getText());
								break;
							}
						}
					}
				}
			}
		}
		long endTime = System.currentTimeMillis();
	}

	public Multimap<String, String> getItemDetails() {
		if (this.itemDetails.size() == 0) {
			this.buildItemDetails();
		}
		return this.itemDetails;
	}

	private boolean buildItemDetails() {
		List<WebElement> tds = WebDriverManager.getWebDriver().findElements(By
				.xpath("//table/tbody/tr/td[contains(text(),'Quantity')]"));
		for (WebElement td : tds) {
			WebElement tab = getParentTable(td);

			List<WebElement> links = tab.findElements(By.tagName("a"));

			if (links.size() == 2 || links.size() == 3) {
				itemDetails.put("product_name", links.get(0).getText());
				itemDetails.put("product_name_url",
						links.get(0).getAttribute("href"));
				itemDetails.put("product_image_src",
						links.get(1).findElement(By.tagName("img"))
								.getAttribute("src"));
				itemDetails.put("product_image_url",
						links.get(1).getAttribute("href"));
			}

			List<WebElement> tables = tab.findElements(By.tagName("table"));
			List<WebElement> rows;
			for (WebElement table : tables) {
				if (table.findElements(By.tagName("table")).size() > 0) {
					continue;
				} else {
					rows = table.findElements(By.tagName("tr"));
				}
				for (WebElement tr : rows) {

					List<WebElement> columns = tr
							.findElements(By.tagName("td"));
					if (tr.getText().equals("")) {
						continue;
					}
					for (int col = 0; col < columns.size(); col++) {
						String text = columns.get(col).getText();
						if (text.equals("")) {
							continue;
						}
						if (text.contains("Quantity:")) {
							itemDetails.put("quantity", columns.get(col + 1)
									.getText());
							break;
						}
						if (text.contains("Size:")) {
							itemDetails.put("size", columns.get(col + 1)
									.getText());
							break;
						}
						if (text.contains("Color:")) {
							itemDetails.put("color", columns.get(col + 1)
									.getText());
							break;
						}
						if (text.contains("Type:")) {
							itemDetails.put("type", columns.get(col + 1)
									.getText());
							break;
						}
						if (text.contains("Web ID:")) {
							itemDetails.put("web_id", columns.get(col + 1)
									.getText().trim());
							break;
						}
						if (text.contains("Tracking #:")) {
							itemDetails.put("tracking", columns.get(col + 1)
									.getText());
							break;
						}
						if (text.contains("Ship Date:")) {
							itemDetails.put("shipdate", columns.get(col + 1)
									.getText());
							break;
						}
						if (text.contains("Shipment type:")) {
							itemDetails.put("shipmenttype", columns
									.get(col + 1).getText());
							break;
						}
						if (text.contains("Send to:")) {
							itemDetails.put("sendto", columns.get(col + 1)
									.getText());
							break;
						}

					}
				}
			}
		}

		return false;
	}

	public boolean loadEmailByBodyKeyword(String firstName)
			throws InterruptedException {
		boolean returnValue = false;
		int second;
		String herokuSearch = "https://macys-oes-email.herokuapp.com/gmail/message/body?keyword="
				+ firstName;
        log.debug("Heroku email URL:"+herokuSearch);
		WebDriverManager.getWebDriver().get(herokuSearch);
		for (second = 0; second <= 60; second++) {
			try {
				String pageSource = WebDriverManager.getWebDriver().getPageSource();
				if (pageSource.contains("No message available")
						|| pageSource.contains("Whitelabel Error Page")) {
					Thread.sleep(1000);
					WebDriverManager.getWebDriver().get(herokuSearch);
				} else {
					returnValue = true;
					PageFactory
							.initElements(WebDriverManager.getWebDriver(), this);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		orderTotal = new HashMap<String, String>();
		orderDetail = new HashMap<String, String>();

		shipmentDetails = ArrayListMultimap.create();
		itemDetails = ArrayListMultimap.create();
		return returnValue;
	}

	public String getStaticMessage() {
		return this.staticMessageText.getText();
	}

	public String getReductionReasonMessageText() {
		return this.reductionReasonMessageText.getText();
	}
	
	public String getStaticFedfilDelayText() {
		return this.staticFedfilDelayText.getText();
	}

	public String getOrderNumber() {
		return this.orderNumber.getText();
	}

	public String getOrderDate() {
		String orderDates = "";
		for (WebElement orderDt : orderDate) {
			orderDates += orderDt.getText().trim() + ";";
		}
		return orderDates;
	}

	public String getstoreAddress() {
		return this.storeAddress.getText().replaceAll("\n", ";");
	}

	public String getcontactInformation() {
		return this.contactInformation.getText().replaceAll("\n", ";")
				.toLowerCase();
	}

	public String getstoreTimeAndPhoneNos() {
		return this.storeTimeAndPhoneNos.getText().replaceAll("\n", ";").trim();
	}

	public String getNoteSection() {
		return this.noteSection.getText();
	}

	public String getOrderReadyText() {
		return this.orderReadyText.getText();
	}

	public String getstorePickupInstructions() {
		return this.storePickupInstructions.getText();
	}

	public String getpickupBarcode() {
		return this.pickupBarcode.getText();
	}

	public String getinStorePickup() {
		return this.inStorePickup.getText().trim() + ";"
				+ inStorePickupValue.getText().trim();
	}

	public String getgiftReceipt() {
		return this.giftReceipt.getText().trim();
	}

	public String getRefundedTo() {
		return refundedTo.getText();
	}
	
	public String getFirstName() {
		return this.firstnameText.getText();
	}

	public String getPaymentMethod() {
		return this.paymentmenthodText.getText();
	}

	public String getRefundAmount() {
		return this.refundAmountText.getText();
	}

	public String getPaymentTypeForMaf() {
		return this.paymentTypeForMaf.getText();
	}

	public String getProductName() {
		return this.productName.getText();
	}

	public boolean isLinkExists(String linkText) {
		if (WebDriverManager.getWebDriver().findElement(By.linkText(linkText)) != null) {
			return true;
		}
		return false;
	}

	public WebElement getLinkByText(String linkText) {
		return WebDriverManager.getWebDriver().findElement(By.linkText(linkText));
	}

	public WebElement getLinkByTitle(String linkTitle) {
		return WebDriverManager.getWebDriver()
				.findElement(
						By.xpath("//*[@title='" + linkTitle.toLowerCase()
								+ "']"));
	}

	public String getUrlByTitle(String linkTitle) {
		return WebDriverManager.getWebDriver()
				.findElement(
						By.xpath("//*[@title='" + linkTitle.toLowerCase()
								+ "']")).getAttribute("href");
	}

	public String getItemNames() throws InterruptedException {
		String itmName = "";
		for (WebElement label : itemName) {
			itmName += label.getText() + "@";
		}
		return itmName.toLowerCase();
	}

	public boolean isTextExistsInEmailPage(String strText) {
		return WebDriverManager.getWebDriver().getPageSource().contains(strText);
	}

	public boolean containsNoBrokenLinks() throws IOException {
		List<WebElement> ele = WebDriverManager.getWebDriver().findElements(By.tagName("a"));
		log.debug("size:" + ele.size());
		for (int i = 0; i < ele.size(); i++) {
			String nextHref = ele.get(i).getAttribute("href");
			if (getResponseCodeByURL(nextHref) == 200) {
                log.debug("Valid Link:" + nextHref);
			} else {
                log.debug("INVALID Link:" + nextHref);
				return false;
			}
		}
		return true;
	}

	public int getResponseCodeByLinkText(String linkname) throws IOException {
		return getResponseCodeByURL(WebDriverManager.getWebDriver()
				.findElement(By.partialLinkText(linkname)).getAttribute("href"));
	}

	public int getResponseCodeForLink(WebElement link) throws IOException {
		return getResponseCodeByURL(link.getAttribute("href"));
	}

	public int getResponseCodeByURL(String url) throws IOException {
		Response response = Jsoup.connect(url).timeout(20000)
				.ignoreContentType(true).followRedirects(true).execute();
		return response.statusCode();
	}

	public int getItemImageURLHttpStatus(int index) throws IOException {
		return getResponseCodeByURL(this.itemImage.get(index).getAttribute(
				"src"));
	}

	public int getImageURLHttpStatus() throws IOException {
		return getResponseCodeByURL(this.productImage.getAttribute("src"));
	}

	public boolean isTextPresent(String text) {
		
		
		
		try {
			if (pageSourceText == null){
			pageSourceText = WebDriverManager.getWebDriver().getPageSource();
			}
			boolean b = pageSourceText.contains(text);
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isElementPresent(By by) {
		try {
			WebDriverManager.getWebDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			WebDriverManager.getWebDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = WebDriverManager.getWebDriver().switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public String getSignatureLabels() throws InterruptedException {
		String signatureLabelText = "";
		for (WebElement label : signatureLabel) {
			signatureLabelText += label.getText().trim() + " ";
		}
		return signatureLabelText;
	}

	public String getAllLabels() throws InterruptedException {
		String labelText = "";
		for (WebElement label : labelDetails) {
			labelText += label.getText() + " ";
		}
		return labelText.toLowerCase();
	}
	public String getRedeemDetails() throws InterruptedException {
		String redeemLabelText = "";
		for (WebElement redeem : redeemDetails) {
			redeemLabelText += redeem.getText() + " ";
		}
		return redeemLabelText.toLowerCase();
	}

	public String getSmsOptMsg() {
		String textlines = "";
		for (WebElement label : smsOptMsg) {
			textlines += label.getText() + " ";
		}
		return textlines;
	}

	public String getexptext() {
		return this.exptext.getText();
	}

	public String getsingleHeader() {
		return this.singleHeader.getText();
	}

	public String getUpsInfoInBodytext() throws InterruptedException {
		String text = "";
		for (WebElement label : upsInfo) {
			text += label.getText() + " ";
		}
		return text.toLowerCase();
	}

	public int getProsImageURLHttpStatus(int index) throws IOException {
		return getResponseCodeByURL(this.prosImages.get(index).getAttribute(
				"src"));
	}

	public String getProsItemDetails() throws InterruptedException {
		String prosItemDesc = "";
		for (WebElement label : prosProductNames) {
			prosItemDesc += label.getText().toLowerCase() + "@";

		}

		return prosItemDesc.toLowerCase();
	}

	public String getAllHeaderLabels() throws InterruptedException {
		String labelHeaderText = "";
		for (WebElement label : zonenameText) {
			labelHeaderText += label.getText() + " ";
		}
		return labelHeaderText.toLowerCase();
	}

	public boolean isBmoneyLogoDisplayed(){
		return this.bMoneyLogo.isDisplayed();
	}
	public boolean isbMoneyBarCodeImageDisplayed(){
		return this.bMoneyBarCodeImage.isDisplayed();
	}

	public String getbMoneyEarnText(){
		return this.bMoneyEarnText.getText();
	}
	public String getbMoneyEarnAmount(){
		return this.bMoneyEarnAmount.getText();
	}
	public String getbMoneyRedeemText(){
		return this.bMoneyRedeemText.getText();
	}
	public String getbMoneyEffAndExpDates(){
		return this.bMoneyEffAndExpDates.getText();
	}
	public String getbMoneyExpDate(){
		return this.bMoneyExpDate.getText();
	}
	public String getbMoneyRewardCardNumberTxt(){
		return this.bMoneyRewardCardNumberTxt.getText();
	}
	public String getbMoneyRewardCIDTxt(){
		return this.bMoneyRewardCIDTxt.getText();
	}
	public String getbMoneyDisclimerText(){
		return this.bMoneyDisclimerText.getText();
	}
	public String getShipAddChangeText() {return this.shipstatictext.getText();}
	public String getShipFirstnameText(){return this.shipfirstname.getText();}

}
