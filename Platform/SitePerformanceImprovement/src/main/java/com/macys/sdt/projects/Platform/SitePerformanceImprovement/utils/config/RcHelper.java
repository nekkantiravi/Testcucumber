package com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by srinivasan_sw on 8/10/2017.
 */
public class RcHelper extends StepUtils {
    public static Random randomGenerator;
    public static  String PhoneNum;
    public static String getSpecialCharacterOrNumber(String optionSpecialType){
        char[] a={'\0'};
        int index;
        if(optionSpecialType.equalsIgnoreCase("special")){
            a= new char[]{'@', '!', '$', '%', '^', '&', '*', '(', '0', '_', '+', '='};
        }
        else if (optionSpecialType.equalsIgnoreCase("number")){
            a= new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        }
        List<Character> df= new ArrayList<>();
        for (char s:a) {
            df.add(s);
        }
        randomGenerator=new Random();
        index=randomGenerator.nextInt(df.size());
        return df.get(index).toString();
    }

    public static boolean verifySpecialCharOrNumber(String fieldName){
        List<WebElement> rt= Elements.findElement("responsive_shipping_options.shipping_address_section").findElements(By.className("error")).stream().filter(e->!e.getText().equals("")).collect(Collectors.toList());
        String errorMsg="Please remove any special characters.";
        boolean availabilityOfFieldErrorMsg=false;
        switch (fieldName){
            case "firstname" :
                WebElement firstNameEle=Elements.findElement("responsive_shipping_options.shipping_first_name");
                firstNameEle.sendKeys(Keys.TAB);
                Assert.assertTrue("Error message not displayed", Elements.findElement(By.cssSelector("#rc-shipping-address-wrapper > div:nth-child(1) > div > small")).getText().equalsIgnoreCase(errorMsg));
                availabilityOfFieldErrorMsg=true;
                break;
            case "lastname" :
                WebElement lastNameEle=Elements.findElement(By.id("rc-shipping-lastName"));
                lastNameEle.sendKeys(Keys.TAB);
                Assert.assertTrue("Error message not displayed", Elements.findElement(By.cssSelector("#rc-shipping-address-wrapper > div:nth-child(2) > div > small")).getText().equalsIgnoreCase(errorMsg));
                availabilityOfFieldErrorMsg=true;
                break;
            case "addressfield1" :
                WebElement address1=Elements.findElement(By.id("rc-shipping-line1"));
                address1.sendKeys(Keys.TAB);
                Assert.assertTrue("Error message not displayed", Elements.findElement(By.cssSelector("#rc-shipping-address-wrapper > div:nth-child(3) > div > small")).getText().equalsIgnoreCase(errorMsg));
                availabilityOfFieldErrorMsg=true;
                break;
            case "addressfield2" :
                WebElement address2=Elements.findElement(By.id("rc-shipping-line2"));
                address2.sendKeys(Keys.TAB);
                Assert.assertTrue("Error message not displayed", Elements.findElement(By.cssSelector("#rc-shipping-address-wrapper > div:nth-child(4) > div > small")).getText().equalsIgnoreCase(errorMsg));
                availabilityOfFieldErrorMsg=true;
                break;
            case "city" :
                WebElement cityEle=Elements.findElement(By.id("rc-shipping-city"));
                cityEle.sendKeys(Keys.TAB);
                Assert.assertTrue("Error message not displayed", Elements.findElement(By.cssSelector("#rc-shipping-address-wrapper > div:nth-child(5) > div > small")).getText().equalsIgnoreCase(errorMsg));
                availabilityOfFieldErrorMsg=true;
                break;
            case "zipcode" :
                WebElement zipcodeEle=Elements.findElement(By.id("rc-shipping-postal-code"));
                zipcodeEle.sendKeys(Keys.TAB);
                Assert.assertTrue("Other than digits is allowed in zipcode text field", zipcodeEle.getAttribute("value").matches("[0-9]+"));
                if(zipcodeEle.getAttribute("value").length()!=5)
                    Assert.assertEquals(Elements.findElement(By.cssSelector("#rc-stateZipRow > div.small-7.medium-5.small-offset-1.columns.end > small")).getText(), "Please enter a valid 5-digit ZIP code.");
                availabilityOfFieldErrorMsg=true;
                break;
            case "phonenumber" :
                WebElement phonefield= Elements.findElement(By.id("rc-shipping-phone"));
                phonefield.sendKeys(Keys.TAB);
                Assert.assertTrue("Other than digits is allowed in phone number text field", phonefield.getAttribute("value").matches("[0-9]+"));
                Assert.assertTrue("Other than digits is allowed in phone number text field", Elements.findElement(By.cssSelector("#rc-shipping-address-wrapper > div:nth-child(7) > div > small")).getText().contains("Please enter a valid 10-digit phone number."));
                availabilityOfFieldErrorMsg=true;
                break;
        }

        return availabilityOfFieldErrorMsg;
    }

    //check_the_error_message_for_shipping_section_when_giving_empty_input
    public static HashMap<String, Boolean> validatingEmptyData(){
        List<WebElement> rt=Elements.findElement("responsive_shipping_options.shipping_address_section").findElements(By.className("error")).stream().filter(e->!e.getText().equals("")).collect(Collectors.toList());
        HashMap<String, Boolean> isAlertEmptyField=new HashMap<>();
        isAlertEmptyField.put("firstname", (rt.get(0).getText().contains(rt.get(0).findElement(By.className("error_msg")).getText())&& rt.get(0).findElement(By.className("icon-ui-error-f-medium")).isDisplayed()));
        isAlertEmptyField.put("lastname", (rt.get(1).getText().contains(rt.get(1).findElement(By.className("error_msg")).getText())&& rt.get(1).findElement(By.className("icon-ui-error-f-medium")).isDisplayed()));
        isAlertEmptyField.put("streetaddress", (rt.get(2).getText().contains(rt.get(2).findElement(By.className("error_msg")).getText()) && rt.get(2).findElement(By.className("icon-ui-error-f-medium")).isDisplayed()));
        isAlertEmptyField.put("citydetails", (rt.get(3).getText().contains(rt.get(3).findElement(By.className("error_msg")).getText()) && rt.get(3).findElement(By.className("icon-ui-error-f-medium")).isDisplayed()));
        isAlertEmptyField.put("statedetails", rt.get(4).findElement(By.className("error_msg")).getText().contains("Please enter a state."));
        //  isAlertEmptyField.put("zipcode", (rt.get(5).getText().contains(rt.get(5).findElement(By.className("error_msg")).getText()) && rt.get(5).findElement(By.className("icon-ui-error-f-medium")).isDisplayed()));
        isAlertEmptyField.put("phonenumber", (rt.get(6).getText().contains(rt.get(6).findElement(By.className("error_msg")).getText()) && rt.get(6).findElement(By.className("icon-ui-error-f-medium")).isDisplayed()));
        return isAlertEmptyField;
    }

    //check_the_textfields_validation_symbol_for_shipping_section_when_giving_empty_input
    public static HashMap<String, Boolean> validatingDataField() {
        //new CheckoutSteps().I_enter_shipping_address_on_guest_shipping_page();
        // Elements.findElement("responsive_shipping_options.shipping_phone_number").sendKeys(Keys.TAB);
        List<WebElement> we=Elements.findElement(By.id("rc-shipping-address-wrapper")).findElements(By.className("valid")).stream().filter(e->!e.getText().equals("")).collect(Collectors.toList());
        HashMap<String, Boolean> isAlertContainsValues=new HashMap<>();
        isAlertContainsValues.put("firstname", we.get(0).findElement(By.className("icon-ui-validation-f-medium")).isDisplayed());
        isAlertContainsValues.put("lastname", we.get(1).findElement(By.className("icon-ui-validation-f-medium")).isDisplayed());
        isAlertContainsValues.put("streetaddress", we.get(2).findElement(By.className("icon-ui-validation-f-medium")).isDisplayed());
        isAlertContainsValues.put("citydetails", we.get(4).findElement(By.className("icon-ui-validation-f-medium")).isDisplayed());
        isAlertContainsValues.put("zipcode", we.get(5).findElement(By.className("small-7")).findElement(By.className("icon-ui-validation-f-medium")).isDisplayed());
        isAlertContainsValues.put("phonenumber", we.get(7).findElement(By.className("icon-ui-validation-f-medium")).isDisplayed());
        return isAlertContainsValues;
    }

    public static void giftCardApply(){
        Elements.findElement(By.id("rc-gift-card")).click();
        /*TextBoxes.typeTextbox(By.id("rc-giftcard-number"), GiftCardUtils.getUserRewardCards().get(0).number);
        TextBoxes.typeTextbox(By.id("rc-giftcard-cid"), GiftCardUtils.getUserRewardCards().get(0).cid);*/
        Elements.findElement(By.id("rc-giftcard-cid")).sendKeys(Keys.TAB);
    }

    public static List<String> giftCardFieldsEmptyErrorMsg(){
        List<String> gCerrormsg=new ArrayList<>();
        String giftCardNumberErrormsg=Elements.findElement(By.id("rc-giftcard-number-row")).findElement(By.className("error_msg")).getText();
        String giftCardCidErrormsg=Elements.findElement(By.id("rc-giftcard-cid-row")).findElement(By.className("error_msg")).getText();
        gCerrormsg.add(giftCardNumberErrormsg);
        gCerrormsg.add(giftCardCidErrormsg);
        return gCerrormsg;
    }

    public static void selectPaypalOption(){
        Elements.findElement(By.id("rc-paypal")).click();
        Wait.untilElementPresent((By) Elements.findElement(By.id("rc-paypal")));
        String paypalDisclaimer=Elements.findElement(By.id("rc-paypal-disclaimer")).getText();
        Assert.assertTrue("PayPal continue checkout button unavailable", Elements.findElement(By.id("rc-paypal-continue")).isDisplayed());
        Assert.assertFalse("Gift card section shoould not be enabled", Elements.findElement(By.id("rc-gift-card")).isEnabled());
        Assert.assertFalse("Billing address section should not be enabled", Elements.findElement(By.id("rc-use-shipping-container")).isDisplayed());
    }

    public static Map<String, String> getCreditcardDetails(String cardType){
        Map<String, String > cardDetails=new HashMap<>();
        CreditCard card = CreditCards.getValidCard(cardType);
        cardDetails.put("cardNo", card.getCardNumber());
        cardDetails.put("expDate", card.getExpiryMonth());
        cardDetails.put("expYear", card.getExpiryYear());
        cardDetails.put("securityCode", card.getSecurityCode());
        return cardDetails;
    }

    public static void selecCreditCardType(String cardType){
        Select sel=new Select(Elements.findElement(By.id("rc-payment-card-type")));
        sel.selectByVisibleText(cardType);
        Wait.forPageReady();
    }

    public static boolean ismacysAndEmployeeCard(){
        return Elements.findElement(By.id("rc-payment-card-month")).isEnabled();
    }

    public static boolean otherCards(){
        return Elements.findElement(By.id("rc-payment-card-month")).isEnabled();
    }

    public static boolean amexCards(){
        return Elements.findElement(By.id("rc-cvv-hint")).getAttribute("class").equals("amex");
    }

    public static boolean nonAmexCards(){
        return Elements.findElement(By.id("rc-cvv-hint")).getAttribute("class").equals("not-amex");
    }

    public static Map<String, Boolean> isSDsectionButtons(){
        Map<String, Boolean> sdButtons=new HashMap<>();
        Wait.untilElementPresent(By.id("rc-shipping-addresses-list"));
        sdButtons.put("HighlightedAddress", Elements.findElement(By.id("rc-shipping-addresses-list")).findElement(By.className("rc-highlight")).isDisplayed());
        sdButtons.put("Editbutton", Elements.findElement(By.className("rc-highlight")).findElement(By.id("rc-edit-shipping-address")).isDisplayed());
        sdButtons.put("SaveButton", Elements.findElement(By.id("rc-shipping-address-use")).isDisplayed());
        sdButtons.put("AddNew", Elements.findElement(By.id("rc-add-new-shipping-address")).isDisplayed());
        sdButtons.put("CancelButton", Elements.findElement(By.id("rc-shipping-address-change-cancel")).isDisplayed());
        return sdButtons;
    }

    public static Map<String, String> getAddressInSdSection(){
        Map<String,String> addressDetails=new HashMap<>();
        WebElement ele=Elements.findElement(By.id("rc-at-shipping-address")).findElement(By.tagName("p"));
        addressDetails.put("firstname", ele.findElements(By.className("rc-text-ellipsis")).get(0).getText().split(" ")[0]);
        addressDetails.put("secondname", ele.findElements(By.className("rc-text-ellipsis")).get(0).getText().split(" ")[1]);
        addressDetails.put("address1", ele.findElements(By.className("rc-text-ellipsis")).get(1).getText());
        addressDetails.put("address2", ele.findElements(By.className("rc-text-ellipsis")).get(2).getText());
        addressDetails.put("city", ele.findElements(By.className("rc-text-ellipsis")).get(3).getText().split(",")[0]);
        addressDetails.put("state", ele.findElements(By.className("rc-text-ellipsis")).get(3).getText().split(",")[1].trim().split(" ")[0]);
        addressDetails.put("zipcode", ele.findElements(By.className("rc-text-ellipsis")).get(3).getText().split(",")[1].trim().split(" ")[1]);
        return addressDetails;
    }

    public static Map<String, String> getHighlightedAddress(){
        Map<String, String> addressDetails=new HashMap<>();
        WebElement el=Elements.findElement(By.id("rc-shipping-addresses-list")).findElements(By.tagName("li")).stream().filter(e->e.getAttribute("class").equalsIgnoreCase("rc-highlight")).findAny().get();
        String highlightedInfo=el.findElement(By.tagName("p")).getText();
        addressDetails.put("firstname", highlightedInfo.split("\n")[0].split(" ")[0]);
        addressDetails.put("secondname", highlightedInfo.split("\n")[0].split(" ")[1]);
        addressDetails.put("address1", highlightedInfo.split("\n")[1]);
        addressDetails.put("address2", highlightedInfo.split("\n")[2]);
        addressDetails.put("city", highlightedInfo.split("\n")[3].split(",")[0]);
        addressDetails.put("state", highlightedInfo.split("\n")[3].split(",")[1].trim().split(" ")[0]);
        addressDetails.put("zipcode", highlightedInfo.split("\n")[3].split(",")[1].trim().split(" ")[1]);
        return addressDetails;
    }

    public static void editHighlightedAddress() throws InterruptedException {
        Thread.sleep(1000);
        Wait.untilElementPresent(By.id("rc-shipping-addresses-list"));
        Wait.until(()->Elements.findElement(By.id("rc-edit-shipping-address")).isDisplayed(), 20);
        Elements.findElement(By.id("rc-edit-shipping-address")).click();
        Wait.untilElementPresent(By.id("rc-bc-firstName"));
        Elements.findElement(By.id("rc-bc-firstName")).clear();
        Elements.findElement(By.id("rc-bc-firstName")).sendKeys(TestUsers.generateRandomFirstName());
        Elements.findElement(By.id("rc-bc-lastName")).clear();
        Elements.findElement(By.id("rc-bc-lastName")).sendKeys(TestUsers.generateRandomLastName());
        Elements.findElement(By.id("rc-save-new-address")).click();
        Wait.untilElementPresent(By.id("rc-shipping-address-change"));
    }

    public static void addNewInfo(){
        Elements.findElement(By.id("rc-shipping-address-change")).click();
        Wait.untilElementPresent(By.id("rc-add-new-shipping-address"));
        Elements.findElement(By.id("rc-add-new-shipping-address")).click();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        new Checkout(opts, false).fillShippingData(false);
        Elements.findElement(By.id("rc-save-new-address")).click();
        Wait.untilElementPresent(By.id("rc-shipping-address-change"));
    }

    public static Map<String, String> getCCDetails(){
        Wait.untilElementPresent(By.id("rc-payment-cc-info"));
        Map<String, String> ccInfo=new HashMap<>();
        ccInfo.put("cardType", Elements.findElement(By.id("rc-payment-cc-info")).getText().split("\n")[2]);
        ccInfo.put("userName", Elements.findElement(By.id("rc-payment-cc-info")).getText().split("\n")[5]);
        ccInfo.put("expDetails", Elements.findElement(By.id("rc-payment-cc-info")).getText().split("\n")[4]);
        return ccInfo;
    }

    public static Map<String, String> changeCcTypeNumber(){
        String cardTypeSelection=getCCDetails().get("cardType").equals("Visa") ?  "MasterCard"  : "Visa";
        String cardExpMonth=cardTypeSelection.equalsIgnoreCase("Visa") ? "01" : "06";
        String cardExpYear=cardTypeSelection.equalsIgnoreCase("Visa") ? "2021" : "2018";
        Wait.untilElementPresent(By.id("rc-payment-change"));
        Elements.findElement(By.id("rc-payment-change")).click();
        Wait.untilElementPresent(By.className("rc-creditcard-edit"));
        Elements.findElement(By.className("rc-creditcard-edit")).click();
        Wait.untilElementPresent(By.id("rc-payment-card-type"));
        Select ccSelect=new Select(Elements.findElement(By.id("rc-payment-card-type")));
        ccSelect.selectByVisibleText(cardTypeSelection);
        Elements.findElement(By.id("rc-payment-card-number")).click();
        Elements.findElement(By.id("rc-payment-card-number")).clear();
        Elements.findElement(By.id("rc-payment-card-number")).sendKeys(CreditCards.getValidCard(cardTypeSelection).getCardNumber());
        Select selectCCDate=new Select(Elements.findElement(By.id("rc-payment-card-month")));
        selectCCDate.selectByVisibleText(cardExpMonth);
        Select selectCCYear=new Select(Elements.findElement(By.id("rc-payment-card-year")));
        selectCCYear.selectByVisibleText(cardExpYear);
        Elements.findElement(By.id("rc-ccdetails-save")).click();
        Wait.untilElementPresent(By.id("rc-payment-change"));
        Map<String, String> ccInfoAfterEdit =new HashMap<>();
        ccInfoAfterEdit.put("CardType", ccSelect.getFirstSelectedOption().getText());
        ccInfoAfterEdit.put("ExpiryDetails", cardExpMonth+cardExpYear);
        return ccInfoAfterEdit;
    }

    public static boolean isAlertAvailable(){
        return Elements.findElement(By.className("td-warning-message")).isDisplayed();
    }

    public static void applyCcNumber(){
        String cardType=Elements.findElement(By.id("rc-payment-cc-info")).getText().split("\n")[2];
        if(isAlertAvailable())
            Elements.findElement(By.id("rc-payment-td-cardnumber")).sendKeys(CreditCards.getValidCard(cardType).getCardNumber());
        else
            System.out.println("Alert unavailable");
        Elements.findElement(By.id("rc-cc-confirm-continue")).click();

    }

    public static void sendCcSecurityCode(){
        String cardType=Elements.findElement(By.id("rc-payment-cc-info")).getText().split("\n")[2];
        Elements.findElement(By.id("rc-cvv-input")).sendKeys(CreditCards.getValidCard(cardType).getSecurityCode());
        Elements.findElement(By.id("rc-cvv-input")).sendKeys(Keys.TAB);
        Assert.assertTrue("Place Order Button is not displayed", Elements.findElement(By.id("rc-place-order-btn")).isEnabled());
    }

    public static Map<String, Boolean> ccButtons(){
        Elements.findElement(By.id("rc-payment-change")).click();
        Wait.untilElementPresent(By.id("rc-payment-use"));
        Map<String, Boolean> ccButtons=new HashMap<>();
        ccButtons.put("Save", Elements.findElement(By.id("rc-payment-use")).isDisplayed());
        ccButtons.put("AddNew", Elements.findElement(By.id("rc-add-creditcard")).isDisplayed());
        ccButtons.put("Cancel", Elements.findElement(By.id("rc-payment-change-cancel")).isDisplayed());
        ccButtons.put("HighlightedEdit", Elements.findElement(By.id("rc-payments-list-form")).findElement(By.className("rc-highlight")).findElement(By.tagName("input")).getAttribute("checked").equalsIgnoreCase("true"));
        return ccButtons;
    }

    public static void AddNewCard(String cardType){
        Elements.findElement(By.id("rc-payment-change")).click();
        Wait.untilElementPresent(By.id("rc-payment-use"));
        Elements.findElement(By.id("rc-add-creditcard")).click();
        Wait.untilElementPresent(By.id("rc-payment-card-type"));
        Select ccSelect=new Select(Elements.findElement(By.id("rc-payment-card-type")));
        ccSelect.selectByVisibleText(cardType);
        Wait.untilElementPresent(By.id("rc-payment-card-number"));
        Elements.findElement(By.id("rc-payment-card-number")).sendKeys(CreditCards.getValidCard(cardType).getCardNumber());
        Elements.findElement(By.id("rc-bc-email")).sendKeys(TestUsers.generateRandomEmail(16));
        Elements.findElement(By.id("rc-ccdetails-save")).click();
        Wait.untilElementPresent(By.id("rc-payment-change"));
    }

    public static void updatePhoneNum(){
        Elements.findElement(By.id("rc-contact-change")).click();
        PhoneNum=TestUsers.generateRandomPhoneNumber();
        Elements.findElement(By.id("rc-contact-phoneNumber")).clear();
        Elements.findElement(By.id("rc-contact-phoneNumber")).sendKeys(PhoneNum);
        Elements.findElement(By.id("rc-contact-save")).click();
        Wait.untilElementPresent(By.id("rc-contact-change"));
    }

    public static Map<String, Boolean> buttonsInContact(){
        Elements.findElement(By.id("rc-contact-change")).click();
        Map<String, Boolean> contactFields=new HashMap<>();
        contactFields.put("PhoneNumberField", Elements.findElement(By.id("rc-contact-phoneNumber")).isDisplayed());
        contactFields.put("updateButton", Elements.findElement(By.id("rc-profile-email-update")).isDisplayed());
        contactFields.put("updateButton", Elements.findElement(By.id("rc-contact-save")).isDisplayed());
        return contactFields;
    }

    public static String selectShippingMethodsGuest(){
        int index;
        List<WebElement> li=Elements.findElement(By.className("rc-shipping-method-wrapper")).findElements(By.className("rc-shipping-method"));
        randomGenerator=new Random();
        index=randomGenerator.nextInt(li.size());
        String selectedShippingMethod=li.get(index).getText().split("\n")[0];
        li.get(index).click();
        Elements.findElement(By.id("rc-shipping-continue")).click();
        Wait.untilElementPresent(By.id("rc-shipping-edit"));
        return selectedShippingMethod;
    }

    public static String getSelectedShippingMethod(){
        return Elements.findElement(By.id("rc-at-shipping-method")).findElement(By.tagName("p")).getText();
    }

    public static String selectShippingMethodsSignedIn(){
        int index;
        randomGenerator=new Random();
        List<WebElement> wl=Elements.findElement(By.id("rc-shipping-method-list")).findElements(By.tagName("li")).stream().filter(e->!e.getAttribute("class").contains("hide")).collect(Collectors.toList());
        index=randomGenerator.nextInt(wl.size());
        wl.get(index).click();
        String selectedShippingMethod=wl.get(index).findElement(By.tagName("label")).findElement(By.tagName("span")).getText();
        return selectedShippingMethod;
    }

    public static String getSelectedShippingMethodSignedIn(){
        return Elements.findElement(By.id("rc-at-shipping-cost-value")).getText();
    }

    public static Map<String, String> getSelectedShippingMethodPriceSignedIn(){
        List<WebElement> displayingShippingOptions=Elements.findElement(By.id("rc-shipping-method-list")).findElements(By.tagName("li")).stream().filter(e->(!e.getAttribute("class").equalsIgnoreCase("hide"))).collect(Collectors.toList());
        String selectedOption=displayingShippingOptions.get(0).getText();
        Map<String, String> options=new HashMap<>();
        options.put("shippingMethod", selectedOption.split("\n")[0]);
        options.put("shippingPrice", selectedOption.split("\n")[2]);
        return options;
    }

    public static Map<String, String> getSelectedShippingMethodPriceGuest(){
        List<WebElement> displayingShippingOptions=Elements.findElement(By.className("rc-shipping-method-wrapper")).findElements(By.className("rc-shipping-method")).get(0).findElements(By.className("columns"));
        Map<String, String> options=new HashMap<>();
        options.put("shippingMethod", displayingShippingOptions.get(1).getText().split("\n")[0]);
        options.put("shippingPrice", displayingShippingOptions.get(2).getText().split("\n")[0]);
        return options;
    }

    public static boolean isFirstShippingSelectedGuest(){
        return Elements.findElement(By.className("rc-shipping-method-wrapper")).findElements(By.className("rc-shipping-method")).get(0).getAttribute("class").contains("rc-highlight");
    }

    public static boolean isFirstShippingSelectedSignedIn(){
        List<WebElement> displayingShippingOptions=Elements.findElement(By.id("rc-shipping-method-list")).findElements(By.tagName("li")).stream().filter(e->(!e.getAttribute("class").equalsIgnoreCase("hide"))).collect(Collectors.toList());
        return displayingShippingOptions.get(0).getAttribute("class").contains("rc-highlight");
    }

    public static String getShippingMethodFromOrderSection(){
        return Elements.findElement(By.className("macysGrey")).getText();
    }
}
