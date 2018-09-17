/**
 *
 */
package com.macys.sdt.projects.Customer.OESBB.utils.ui_email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.macys.sdt.framework.runner.WebDriverManager;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author m509575
 *
 */
public class MCOMEmailPage {

	public MCOMEmailPage(String firstName) {
		PageFactory.initElements(WebDriverManager.getWebDriver(), this);
	}

    private static final Logger log = LoggerFactory.getLogger(MCOMEmailPage.class);

	public MCOMEmailPage() {
		// TODO Auto-generated constructor stub
	}

	private boolean acceptNextAlert = true;

	@FindBy(xpath = "//img[@alt=\"Macy\'s\"]")
	public WebElement logoMacys;

	@FindBy(css = "td.macys-preheader")
	public WebElement preHeader;

	@FindBy(xpath = "//table[1]/tbody/tr[1]/td")
	public WebElement preHeaderPriceError;

	@FindAll({ @FindBy(xpath = "//table/tbody/tr[3]/td[@class='product-name pro-url1']/a") })
	List<WebElement> itemName;

	@FindAll({ @FindBy(css = "td.left.headerMsg") })
	List<WebElement> bodyText;

	@FindAll({ @FindBy(css = "td.left.headerMsg") })
	List<WebElement> zoneName;

	@FindBy(xpath = "//td/table/tbody/tr[3]/td")
	public WebElement staticMessageText;

	@FindAll({ @FindBy(css = "td.left.headerMsg") })
	List<WebElement> mailBodyText;

	@FindBy(xpath = "//td[contains(text(),'Your')]")
	public WebElement staticNoteMessage;

	@FindBy(xpath = "//td[contains(text(),'Ready')]")
	public WebElement staticNoteMessages;

	@FindBy(css = "td.order.order-number")
	public WebElement orderNumber;

	@FindBy(css = "td.order.order-date")
	WebElement orderDate;

	@FindAll({ @FindBy(css = "td.order") })
	List<WebElement> labelDetails;

	@FindAll({ @FindBy(css = "td.estimated-shipdate") })
	List<WebElement> estimatedShipdate;

	// @FindBy(xpath = "//td/table/tbody/tr[2]/td")
	@FindBy(css = "td.left.headerMsg.fname")
	public WebElement firstnameText;
	
	public WebElement getfNameLname() {
		return fNameLname;
	}

	public void setfNameLname(WebElement fNameLname) {
		this.fNameLname = fNameLname;
	}

	@FindBy(css = "td.order.payment-cardtype")
	public WebElement paymentmethodText;

	@FindBy(css = "td.order.order-cancel-date")
	public WebElement orderCancelationDate;

	@FindBy(xpath = "//td/span[contains(text(),'Return to:')]")
	public WebElement returnTo;

	@FindBy(css = "td.order.refund-Amt")
	public WebElement refundAmountText;

	@FindBy(linkText = "SHOP E-GIFT CARDS")
	public WebElement shopmaysButton;

	@FindBy(linkText = "SHOP MACY'S")
	public WebElement shopButton;

	@FindBy(css = "td.product-name")
	WebElement productName;

	@FindAll({ @FindBy(css = "img.productImg.productImage") })
	public List<WebElement> productImage;

	@FindAll({ @FindBy(xpath = "//td[@width='176']/a") })
	public List<WebElement> prosProductDesc;

	@FindAll({ @FindBy(xpath = "//img[@width='187']") })
	public List<WebElement> prosProductImages;

	@FindAll({ @FindBy(css = "img.productImg") })
	public List<WebElement> noImageAvailable;

	@FindAll({ @FindBy(css = "td.pro-image1>a>img") })
	public List<WebElement> itemImage;

	@FindBy(xpath = "//span[2]")
	public WebElement productQty;

	@FindBy(css = "span.item")
	public WebElement productQtyText;

	@FindBy(xpath = "//tr[4]/td/span[2]")
	public WebElement productColor;

	@FindBy(xpath = "//td/table/tbody/tr/td/table/tbody/tr[4]/td/span")
	public WebElement productColorText;

	@FindBy(xpath = "//td[@class='product-type']/span[2]")
	public WebElement productType;

	@FindBy(xpath = "//td[@class='product-type']/span")
	WebElement productTypeText;

	@FindBy(xpath = "//td[@class='product-size']/span[2]")
	public WebElement productSize;

	@FindBy(css = "td.wrapper.last > table.seven.columns.5.0")
	public WebElement productPrice;

	@FindBy(css = "td.macys-logo")
	public WebElement macysLogo;

	@FindBy(xpath = "//a[@title='for the home']")
	public WebElement forTheHomeTitle;

	@FindBy(xpath = "//a[@title='men']")
	public WebElement menTitle;

	@FindBy(xpath = "//a[@title='women']")
	public WebElement womenTitle;

	@FindBy(xpath = "//a[@title='shoes']")
	public WebElement shoesTitle;

	@FindBy(css = "a.for-the-home")
	public WebElement forTheHome;

	@FindBy(css = "a.men")
	public WebElement men;

	@FindBy(css = "a.women")
	public WebElement women;

	@FindBy(css = "a.shoes")
	public WebElement shoes;

	@FindBy(xpath = "//a[@title='explore beauty']")
	public WebElement ebeautyBtn;

	@FindBy(xpath = "//a[@title='manage subscription']")
	public WebElement msubscriptionBtn;

	@FindBy(xpath = "//a[@title='SUBSCRIBE NOW']")
	public WebElement subscribenowBtn;

	@FindBy(xpath = "//a[@title='shop beauty']")
	public WebElement shopbeautyBtn;

	@FindBy(xpath = "//a[@title='update now']")
	public WebElement updatenowBtn;

	@FindBy(css = "a.shopbuttonlink")
	public WebElement viewMyAccount;

	@FindBy(css = "td.subtotal-Amt")
	public WebElement subTotalAmt;

	@FindBy(css = "td.baseShipping-Amt")
	public WebElement baseShippingAmt;

	@FindBy(css = "td.addt-Shipment-FeeAmt")
	public WebElement addtShipmentFeeAmt;

	@FindBy(css = "td.shipment-Upgrade-FeeAmt")
	public WebElement shipmentUpgradeFeeAmt;

	@FindBy(css = "td.giftWrap-FeeAmt")
	public WebElement giftWrapFeeAmt;

	@FindBy(css = "td.item.sales-tax")
	public WebElement salesTax;

	@FindBy(css = "td.item.pick-up-in-store")
	public WebElement itemPickupInStore;

	@FindBy(css = "td.item.pickup-in-store")
	public WebElement bopsPickUpReminder;

	@FindBy(xpath = "//td[@class='barcode barcode-number']/span")
	public WebElement pickupBarcode;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Pick-UP in Store:')]/../td[2]")
	public WebElement itemPickupInStoreBopsLatePickUpCancel;

	@FindBy(css = "td.order-Total")
	public WebElement orderTotal;

	@FindBy(css = "td.product-shipping-date")
	public WebElement sddOrderText;

	@FindBy(css = "td.order.return-init-date")
	public WebElement returnInitiatedDate;

	@FindBy(xpath = "//td/span[contains(text(),'Date initiated:')]")
	public WebElement dateInitiated;

	@FindBy(xpath = "//body/table[6]/tbody/tr/td")
	public WebElement profileNegative;

	@FindBy(css = "td.order.replaced-on")
	public WebElement dateReplaced;

	@FindBy(css = "td.order.replacement-date")
	public WebElement replacementDate;

	@FindBy(xpath = "//td/span[contains(text(),'Estimated refund:')]")
	public WebElement estimatedRefund;

	@FindBy(xpath = "//td/span[contains(text(),'Return completed date')]")
	public WebElement returnCompletedDate;

	@FindBy(css = "td.order.original-form-of-tender")
	public WebElement originalFormOfTender;

	@FindBy(css = "td.order.original-payment")
	public WebElement originalPayment;

	@FindBy(xpath = "//td/span[contains(text(),'Reason for return:')]")
	public WebElement reasonForReturn;

	@FindBy(xpath = "//td/span[contains(text(),'Reason for replacement:')]")
	public WebElement reasonForReplacement;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'PR VAT:')]/../td[2]")
	public WebElement prVat;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'PR Municipal:')]/../td[2]")
	public WebElement prMunicipal;

	@FindAll({ @FindBy(css = "td.order.today-store-timings") })
	public List<WebElement> textBelowViewMap;

	@FindAll({ @FindBy(css = "span.item>a") })
	public List<WebElement> trackingnumberAtProduct;

	@FindBy(css = "td.order.order-date>a")
	public WebElement trackingnumberAtShipment;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Sales tax:')]/../td[2]")
	public WebElement salesTaxBops;

	@FindBy(css = "td.order.viewmap")
	public WebElement viewMap;

	@FindBy(css = "td.egiftcardImg")
	public WebElement eGiftCard;

	@FindBy(xpath = "//td/span[contains(text(),'Card amount:')]/..")
	public WebElement cardAmount;

	@FindBy(xpath = "//*[@title='redeem in store']")
	public WebElement redeeemInStore;

	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Same day delivery')]/../td[2]")
	public WebElement sddShipmentUpgradeFeeAmt;

	@FindAll({ @FindBy(css = "img.productImg") })
	public List<WebElement> sddProductImage;

	@FindBy(xpath = "//td/span[contains(text(),'Replacement date:')]/..")
	public WebElement replacementDateReShip;

	@FindBy(css = "td.item.sdd-amt")
	public WebElement sddAmount;

	@FindBy(css = "td.ThanksSubMsg")
	public WebElement thanksSubMsgInBodyText;

	@FindBy(css = "td.ThanksMsg")
	public WebElement thanksMsgInBodyText;

	@FindBy(xpath = "//table[6]/tbody")
	public WebElement promoOffersForPaperless;

	@FindAll({ @FindBy(css = "td.extraoffer") })
	public List<WebElement> extraOffers;

	@FindAll({ @FindBy(css = "td.disclaimer") })
	public List<WebElement> disclaimerInOfferShare;

	@FindAll({ @FindBy(xpath = "/html/body/table[4]/tbody/tr[19]/td/table/tbody/tr/td/table") })
	public List<WebElement> mmoneyOrderDetailsSection16A;

	@FindBy(css = "table:nth-child(5) tr:nth-child(29) table td:nth-child(1)>table tr:nth-child(2)>td:nth-child(2)")
	public WebElement mmoneyOrderDetailsSection16C;

	@FindAll({ @FindBy(xpath = "//table/tbody/tr[2]/td[2]") })
	public List<WebElement> mmoneyOrderDetailsSection16B;

	@FindBy(xpath = "//*[@title='Check It Out']")
	public WebElement checkItOutBtn;

	@FindBy(xpath = "//*[@title='Check Order Status']")
	public WebElement checkOrderStatusBtn;

	@FindBy(xpath = "/html/body/table[4]/tbody/tr[15]/td")
	public WebElement mmoneyBodyText;

	@FindBy(css = "table:nth-child(5) tr:nth-child(27) tbody")
	public WebElement mmoneyCampaignBodyText;

	@FindBy(xpath = "//table[4]/tbody/tr[16]/td")
	public WebElement mmoneyBodyText16B;

	@FindBy(xpath = "/html/body/table[4]/tbody/tr[17]/td")
	public WebElement mmoneyRewardCardEarnEndDate;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(8)>td")
	public WebElement mmoneyRedeemItText;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(9)>td")
	public WebElement mmoneyEffAndExpDates;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(10)>td")
	public WebElement mmoneyRewardCardExpText;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(11)>td")
	public WebElement mmoneyYourRewardCardText;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(12)>td")
	public WebElement mmoneyRewardCardNumberText;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(13)>td")
	public WebElement mmoneyRewardCardCIDText;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(14)>td")
	public WebElement mmoneyRewardCardCID;

	@FindBy(css = "td[class*=\"barcode-number\"]>span")
	public WebElement mmoneyRewardCardNumber;

	@FindBy(css = "img[class*=\"barcodeimage\"]")
	public WebElement mmoneyRewardCardBarCodeImage;

	@FindBy(css = "table:nth-child(5)>tbody>tr:nth-child(19) table td")
	public WebElement mmoneyDeclimerText;

	@FindBy(xpath = "//*[@title='Print savings pass']")
	public WebElement printSavingsPass;

	@FindBy(xpath = "//img[@title='Sign In']/..")
	public WebElement signIn;

	@FindBy(xpath = "//img[@title='Create a Profile']/..")
	public WebElement createAProfile;

	@FindBy(css = "td.item.subtotal-Amt")
	public WebElement subTotalAmtDelayTemplates;

	@FindBy(css = "td.item.baseShipping-Amt")
	public WebElement baseShippingAmtDelayTemplates;

	@FindBy(css = "td.item.addt-Shipment-FeeAmt")
	public WebElement addtShipmentFeeAmtDelayTemplates;

	@FindBy(css = "td.item.shipment-Upgrade-FeeAmt")
	public WebElement shipmentUpgradeFeeAmtDelayTemplates;

	@FindBy(css = "td.item.giftWrap-FeeAmt")
	public WebElement giftWrapFeeAmtDelayTemplates;

	@FindBy(css = "td.item.order-Total")
	public WebElement orderTotalDelayTemplates;

	@FindBy(css = "td.item.pending-points")
	public WebElement loyaltyPendingPoints;

	// @FindBy(css = "td.left.headermsg.loyallistAccount-basePoints")
	// public WebElement loyaltyBasePoints;
	//
	@FindBy(xpath = "//td[contains(text(),'Points from offer:')]/following-sibling::td[1]")
	public WebElement loyaltyBonusPendingPoints;

	@FindBy(xpath = "//td[contains(text(),'Plenti points available:')]/following-sibling::td[1]")
	public WebElement totalPlentiPoints;

	@FindBy(xpath = "//td[contains(text(),'Plenti points used for this order:')]/following-sibling::td[1]")
	public WebElement redeemedRewardsPoints;

	@FindBy(css = "td.item.plenti-card")
	public WebElement loyallistAccountNumber;

	@FindBy(xpath = "//a[@title='update payment information']")
	public WebElement updatePaymentInfo;

	@FindBy(css = "td.order.ship-method")
	public WebElement deliveryMethod;

	@FindBy(xpath = "html/body/table[4]/tbody/tr/td/table/tbody/tr[3]/td[1]")
	public WebElement deliverySummaryText;

	@FindBy(css = "img[class*=\"qrcodeimage\"]")
	public WebElement bopsLockerBarCodeImage;

	@FindBy(xpath = "//td[contains(text(),'Congrats, Ravi!')]")
	public WebElement congratsMessage;

	@FindBy(xpath = "//td[contains(text(),'Heads up, Donald!')]")
	public WebElement loyaltyFirstName;

	@FindBy(xpath = "//span[contains(text(),'100')]")
	public WebElement rewardPointsForGold;

	@FindBy(xpath = "//span[contains(text(),'500')]")
	public WebElement rewardPointsForSilver;

	@FindBy(xpath = "//span[contains(text(),'1,000')]") // span[contains(text(),'10')]
	public WebElement rewardPointsForPlatinum;

	@FindBy(xpath = "//span[contains(text(),'10')]")
	public WebElement rewardPointsForExpiration;

	@FindBy(xpath = "//td[contains(text(),'in Star Money')]")
	public WebElement starMoneyText;

	@FindBy(xpath = "//table[7]/tbody/tr[1]/td/table/tbody/tr/td")
	public WebElement starMoneyMacysCreditCardText;

	@FindBy(xpath = "//table[7]/tbody/tr[3]/td")
	public WebElement StarMoneyExpireDate;

	@FindBy(xpath = "//table[7]/tbody/tr[3]/td")
	public WebElement macysStoreText;

	@FindBy(xpath = "//td[contains(text(), 'Ravi Gummala')]")
	public WebElement fNameLname;

	@FindBy(xpath = "//td[contains(text(), 'Gold Member')]")
	public WebElement starCardGoldMember;

	@FindBy(xpath = "//td[contains(text(), 'Platinum Member')]")
	public WebElement starCardPlatinumMember;

	@FindBy(xpath = "//table[6]/tbody/tr[8]/td")
	public WebElement starMoneyExpDate;

	@FindBy(xpath = "//table/tbody/tr[3]/td[contains(@class, 'item center')]")
	public WebElement firstandLastName;

	@FindBy(xpath = "//table/tbody/tr[4]/td[contains(@class, 'item center')]")
	public WebElement memberText;

	@FindBy(xpath = "//table/tbody/tr[10]/td[contains(@class, 'item center')]")
	public WebElement starRewardText;

	@FindBy(xpath = "//td[contains(text(), 'Silver Member')]")
	public WebElement starCardSilverMember;
	
	@FindBy(xpath = "//td[contains(@class,'left headerMsg') and contains(text() , 'Hi')]")
	public WebElement firstnameTextBilling;
/*
	@FindBy(xpath = "//td[contains(@class,'left headerMsg store-name') and contains(text() , 'Hi')]")
	public WebElement firstnameTextBilling1;
	
	*/



	

	public LinkedHashMap<String, String> productDetailsMap = null;
	public List<LinkedHashMap<String, String>> productDetailsList = null;
	public List<LinkedHashMap<String, String>> productDetails = null;

	public String getPreHeader() {
		return this.preHeader.getText();
	}

	public String getBodyText() throws InterruptedException {
		return getAllLabels(bodyText);
	}
	
	public boolean loadEmailByBodyKeyword(String firstName) throws InterruptedException {
		boolean returnValue = false;
		int second;
		String herokuSearch = "https://macys-oes-email.herokuapp.com/gmail/message/body?keyword=" + firstName;
		log.debug("Heroku email URL:" + herokuSearch);
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
		return returnValue;
	}

	public String getStaticMessage() {
		return this.staticMessageText.getText();
	}

	public String getMailBodyText() {
		String bodyLine = "";

		for (WebElement bodyMsg : mailBodyText) {
			bodyLine += bodyMsg.getText() + " ";
		}

		return bodyLine;
	}

	public String getProfileNegative() {
		return this.profileNegative.getText();
	}

	public String getstaticNoteMessage() {
		return this.staticNoteMessage.getText();

	}

	public String getstaticNoteMessages() {
		return this.staticNoteMessages.getText();

	}

	public String getOrderNumber() {
		return this.orderNumber.getText();
	}

	public String getOrderDate() {
		return this.orderDate.getText();
	}

	public String getpickupBarcode() {
		return this.pickupBarcode.getText();
	}

	public String getFirstName() {
		return this.firstnameText.getText();
	}
	
	public String getFirstnameTextBilling() {
		return this.firstnameTextBilling.getText();
	}
	
	public String getPaymentMethod() {
		return this.paymentmethodText.getText();
	}

	public String getOrderCancelationDate() {
		return this.orderCancelationDate.getText();
	}

	public String getReturnInitiatedDate() {
		return this.returnInitiatedDate.getText();
	}

	public String getDateInitiatedDate() {
		return this.dateInitiated.findElement(By.xpath("./..")).getText();
	}

	public String getDateReplacedDate() {
		return this.dateReplaced.getText();
	}

	public String getReplacementDate() {
		return this.replacementDate.getText();
	}

	public String getOriginalFormOfTender() {
		return this.originalFormOfTender.getText();
	}

	public String getOriginalPayment() {
		return this.originalPayment.getText();
	}

	public String getEstimatedRefund() {
		return this.estimatedRefund.findElement(By.xpath("./..")).getText();
	}

	public String getReturnTo() {
		return this.returnTo.findElement(By.xpath("./..")).getText();
	}

	public String getReturnCompletedDate() {
		return this.returnCompletedDate.findElement(By.xpath("./..")).getText();
	}

	public String getReasonForReturn() {
		return this.reasonForReturn.findElement(By.xpath("./..")).getText();
	}

	public String getReasonForReplacement() {
		return this.reasonForReplacement.findElement(By.xpath("./..")).getText();
	}

	public String getMailContent() throws InterruptedException {
		return getAllLabels(labelDetails);
	}

	private String getAllLabels(List<WebElement> details) throws InterruptedException {
		String labelText = "";
		for (WebElement label : details) {
			labelText += label.getText() + " ";
		}
		return labelText.toLowerCase();
	}

	public String getAllText() throws InterruptedException {

		return getAllLabels(textBelowViewMap);
	}

	public String getAllOrderDetails(List<WebElement> mmoneyOrderDetailsSection) throws InterruptedException {

		return getAllLabels(mmoneyOrderDetailsSection);
	}

	public String getItemNames() throws InterruptedException {
		String itmName = "";
		for (WebElement label : itemName) {
			itmName += label.getText() + "@";
		}
		return itmName.toLowerCase();
	}

	public String getAllEstimatedshipdates() throws InterruptedException {
		String estimatedShipDates = "";
		for (WebElement estimatedDate : estimatedShipdate) {
			int index = estimatedDate.getText().lastIndexOf(':');
			estimatedShipDates += estimatedDate.getText().substring(index + 1).trim() + ";";
		}
		return estimatedShipDates;
	}

	public String getAllPromoHeadings() throws InterruptedException {

		return getAllLabels(extraOffers);
	}

	public String getAllPromoLegalDisclaimer() throws InterruptedException {

		return getAllLabels(disclaimerInOfferShare);
	}

	public String getAllPromoOffersForPaperless() throws InterruptedException {

		return promoOffersForPaperless.getText();
	}

	public String getRefundAmount() {
		return this.refundAmountText.getText();
	}

	public String getProductName() {
		return this.productName.getText();
	}

	public String getProductQyt() {
		return this.productQty.getText();
	}

	public String getProductQtyText() {
		return this.productQtyText.getText();
	}

	public String getProductColor() {
		return this.productColor.getText();
	}

	public String getProductColorText() {
		return this.productColorText.getText();
	}

	public String getTrackingnumberAtProduct() {
		String trackingText = "";
		for (WebElement label : trackingnumberAtProduct) {
			trackingText += label.getText() + "@";

		}

		return trackingText;
	}

	public String getProductType() {
		return this.productType.getText();
	}

	public String getProductTypeText() {
		return this.productTypeText.getText();
	}

	public boolean isLinkExists(String linkText) {
		if (WebDriverManager.getWebDriver().findElement(By.linkText(linkText)) != null) {
			return true;
		}
		return false;
	}

	public WebElement getLinkByText(String linkText) throws Exception {
		return getElementByLinkText(linkText);
	}

	public WebElement getLinkByTitle(String linkTitle) {
		String title = "\"" + linkTitle.toLowerCase() + "\"";
		return WebDriverManager.getWebDriver().findElement(By.xpath("//*[@title=" + title + "]"));
	}

	public String getUrlByTitle(String linkTitle) {
		String title = "\"" + linkTitle.toLowerCase() + "\"";
		return WebDriverManager.getWebDriver().findElement(By.xpath("//*[@title=" + title + "]")).getAttribute("href");
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

	public boolean isTextPresent(String text) {
		try {
			boolean b = WebDriverManager.getWebDriver().getPageSource().contains(text);
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	public int getResponseCodeByElement(String linkText) throws Exception {
		return getResponseCodeByURL(getElementByLinkText(linkText).getAttribute("href"));
	}

	public int getResponseCodeForLink(WebElement link) throws IOException {
		return getResponseCodeByURL(link.getAttribute("href"));
	}

	public int getResponseCodeByLinkText(String linkText) throws Exception {
		return getResponseCodeByURL(
				WebDriverManager.getWebDriver().findElement(By.partialLinkText(linkText)).getAttribute("href"));
	}

	public int getResponseCodeByURL(String url) throws IOException {
		Response response = Jsoup.connect(url).timeout(20000).ignoreContentType(true).followRedirects(true).execute();
		return response.statusCode();
	}

	public int getImageURLHttpStatus(int index) throws IOException {
		return getResponseCodeByURL(this.productImage.get(index).getAttribute("src"));
	}

	public int getItemImageURLHttpStatus(int index) throws IOException {
		return getResponseCodeByURL(this.itemImage.get(index).getAttribute("src"));
	}

	public int getProsImageURLHttpStatus(int index) throws IOException {
		return getResponseCodeByURL(this.prosProductImages.get(index).getAttribute("src"));
	}

	public int getSddItemImageURLHttpStatus(int index) throws IOException {
		return getResponseCodeByURL(this.sddProductImage.get(index).getAttribute("src"));
	}

	public WebElement getElementByLinkText(String linkText) throws Exception {
		WebElement categoryElement = null;
		switch (linkText) {
		case "FOR THE HOME":
			categoryElement = forTheHome;
			break;
		case "MEN":
			categoryElement = men;
			break;
		case "WOMEN":
			categoryElement = women;
			break;
		case "SHOES":
			categoryElement = shoes;
			break;
		case "FOR THE HOME FOB":
			categoryElement = forTheHomeTitle;
			break;
		case "MEN FOB":
			categoryElement = menTitle;
			break;
		case "WOMEN FOB":
			categoryElement = womenTitle;
			break;
		case "SHOES FOB":
			categoryElement = shoesTitle;
			break;
		case "VIEW MY ACCOUNT":
			categoryElement = viewMyAccount;
			break;
		case "RESET PASSWORD":
			categoryElement = viewMyAccount;
			break;
		case "view map":
			categoryElement = viewMap;
			break;
		case "redeem in store":
			categoryElement = redeeemInStore;
			break;
		case "Check It Out":
			categoryElement = checkItOutBtn;
			break;
		case "Check Order Status":
			categoryElement = checkOrderStatusBtn;
		case "Print savings pass":
			categoryElement = printSavingsPass;
			break;
		case "Sign In":
			categoryElement = signIn;
			break;
		case "Create a Profile":
			categoryElement = createAProfile;
			break;
		case "EXPLORE BEAUTY":
			categoryElement = ebeautyBtn;
			break;
		case "MANAGE SUBSCRIPTION":
			categoryElement = msubscriptionBtn;
			break;
		case "SUBSCRIBE NOW":
			categoryElement = subscribenowBtn;
			break;
		case "SHOP BEAUTY":
			categoryElement = shopbeautyBtn;
			break;
		case "UPDATE NOW":
			categoryElement = updatenowBtn;
			break;
		default:
			throw new Exception("Invalid LinkText");
		}
		return categoryElement;

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

	public List<LinkedHashMap<String, String>> getItemDetails(String template) {
		if (productDetails == null)
			productDetails = buildItemDetails(template);
		return productDetails;
	}

	private List<LinkedHashMap<String, String>> buildItemDetails(String template) {
		productDetailsMap = new LinkedHashMap<String, String>();
		productDetailsList = new ArrayList<LinkedHashMap<String, String>>();
		List<WebElement> tables = WebDriverManager.getWebDriver().findElements(By.tagName("table"));
		LinkedHashSet<String> set = new LinkedHashSet<String>();

		for (WebElement temp : tables) {
			if (!temp.getText().equals("") && temp.getText().contains("Qty:")) {
				set.add(temp.getText().trim());
			}
		}
		for (String temp : set) {

			String lines[] = temp.split("\\r?\\n");
			for (int i = 0; i < lines.length; i++) {
				if (!lines[i].equals("")) {
					if (lines[i].contains(":")) {

						String[] parts = lines[i].split(":");
						String key = parts[0].trim().toLowerCase();
						String value = parts[1].trim();

						productDetailsMap.put(key, value);

						if (i == lines.length - 1) {
							productDetailsList.add(productDetailsMap);
							productDetailsMap = new LinkedHashMap<String, String>();
						}

					} else {

						if (lines[i].contains("$")) {
							String key = "Price";
							productDetailsMap.put(key, lines[i].trim());

							if (i == lines.length - 1) {
								productDetailsList.add(productDetailsMap);
								productDetailsMap = new LinkedHashMap<String, String>();
							}

						} else if (lines[i].contains("return")) {

							String key = "returnStatus";
							productDetailsMap.put(key, lines[i].trim());
						}

						else {
							if (!productDetailsMap.isEmpty()) {
								productDetailsList.add(productDetailsMap);
							}
							String key = "productName";
							productDetailsMap.put(key, lines[i].trim());

						}

					}
				}

			}

		}

		Set<LinkedHashMap<String, String>> productDetailsWithoutDuplicates = new LinkedHashSet<LinkedHashMap<String, String>>();
		for (int i = productDetailsList.size() - 1; i >= 0; i--) {
			log.debug(productDetailsList.get(i).toString());
			productDetailsWithoutDuplicates.add(productDetailsList.get(i));
		}

		List<LinkedHashMap<String, String>> productDetailsListNew = new ArrayList<LinkedHashMap<String, String>>(
				productDetailsWithoutDuplicates);

		if (productDetailsList.size() != productDetailsWithoutDuplicates.size()
				&& productDetailsWithoutDuplicates.size() < productDetailsList.size()) {

			productDetailsList.clear();
			for (int i = productDetailsListNew.size() - 1; i >= 0; i--) {
				productDetailsList.add(productDetailsListNew.get(i));
			}
		}

		return productDetailsList;
	}

	public String getProsItemDetails() throws InterruptedException {
		String prosItemDesc = "";
		for (WebElement lable : prosProductDesc) {
			prosItemDesc += lable.getText().toLowerCase() + "@";

		}

		return prosItemDesc.toLowerCase();
	}

	public String getMMoneyBodyText() {
		return this.mmoneyBodyText.getText();

	}

	public String getMMoneyBodyText16B() {
		return this.mmoneyBodyText16B.getText();

	}

	public String getMMoneyRewardCardEarnEndDate() {
		return this.mmoneyRewardCardEarnEndDate.getText();
	}

	public String getMMoneyRedeemItText() {
		return this.mmoneyRedeemItText.getText();
	}

	public String getMMoneyEffAndExpDates() {
		return this.mmoneyEffAndExpDates.getText();
	}

	public String getMMoneyRewardCardExpText() {
		return this.mmoneyRewardCardExpText.getText();
	}

	public String getMMoneyYourRewardCardText() {
		return this.mmoneyYourRewardCardText.getText();
	}

	public String getMMoneyRewardCardNumberText() {
		return this.mmoneyRewardCardNumberText.getText();
	}

	public String getMMoneyRewardCardText() {
		return this.mmoneyRewardCardCIDText.getText();
	}

	public String getMMoneyDeclimerText() {
		return this.mmoneyDeclimerText.getText();
	}

	public String getMMoneyRewardCardNumber() {
		return this.mmoneyRewardCardNumber.getText();
	}

	public String getMMoneyRewardCardCID() {
		return this.mmoneyRewardCardCID.getText();
	}

	public boolean isMMoneyRewardCardBarCodeImagePresent() {
		return this.mmoneyRewardCardBarCodeImage.isDisplayed();
	}

	public boolean isBopsLockerBarCodeImagePresent() {
		return this.bopsLockerBarCodeImage.isDisplayed();
	}

	public String getMMoneyCampaignBodyText() {
		return this.mmoneyCampaignBodyText.getText();
	}
	
	public String getMMoneyOrderDetailsSection16C() {
		return this.mmoneyOrderDetailsSection16C.getText();
	}

	public WebElement getUpdatePaymentInfo() {
		return updatePaymentInfo;
	}

	public String getDeliveryMethod() {
		return this.deliveryMethod.getText();
	}
}
