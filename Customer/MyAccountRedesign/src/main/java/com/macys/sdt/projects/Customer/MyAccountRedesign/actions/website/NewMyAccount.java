package com.macys.sdt.projects.Customer.MyAccountRedesign.actions.website;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount;
import com.macys.sdt.shared.utils.CommonUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Davinder Singh on 10/31/2016.
 */
public class NewMyAccount extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(NewMyAccount.class);
    private static final String MCOM_CREDIT_CARD = "Macy's Credit Card";
    private static final String BCOM_CREDIT_CARD = "Bloomingdale's Credit Card";
    private static final String MCOM_ORDERS = "Orders";
    private static final String BCOM_ORDERS = "Order History";
    private static final String MCOM_WALLET = "Wallet";
    private static final String BCOM_WALLET = "bWallet";
    private static final String PLENTI = "Plenti";
    private static final String LISTS = "Lists";
    private static final String WISHLISTS = "Wish Lists";
    private static final String LOYALLIST = "Loyallist";
    private static final String STORE = "Store";
    private static final String STATS = "Stats";
    private static final String PITCH_NEW_CARD = "pitch_card";
    private static final String CREDIT_CARD_DISCLAIMER = "disclaimer";
    private static final String CARD_EXCLUSIONS = "exclusion_details_link";
    private static final String ORDER_STATUS = "order_status_and_history";
    private static final String FURNITURE_AND_MATTRESS_DELIVERY = "furniture_and_mattress_delivery";
    private static final String DEALS_AND_PROMOTIONS = "deals_and_promotions";
    private static final String WALLET_GUIDE_TEXT_HEADING = "guide_text_heading";
    private static final String ADD_PAYMENT_METHOD = "add_payment_method";
    private static final String WALLET = "wallet";
    private static final String GIFT_CARD = "gift_card";
    private static final String INVALID_PLENTI_ACCOUNT_NUMBER = "1111111111111111";
    private static final String INVALID_PLENTI_PHONE_NUMBER = "1111111111";
    private static final String INVALID_PLENTI_ACC ="invalid plenti account number";
    private static final String INVALID_PLENTI_PHONE ="invalid plenti phone number";
    private static final String PARTIAL_PLENTI_ACC ="partially enrolled plenti account number";
    private static final String PARTIAL_PLENTI_PHONE ="partially enrolled plenti phone number";
    private static final String PLENTI_PHONE_TAB ="phone_number_tab";
    private static final String PLENTI_NUMBER_TAB ="plenti_number_tab";
    private static final String JOIN_PLENTI_PROGRAM ="join_plenti";
    private static final String LOYALLITY_GUIDE_TEXT2 ="guide_text2";
    private static final String LOYALLITY_GUIDE_TEXT3 ="guide_text3";
    private static final String BECOME_LOYALLIST ="loyallist";
    private static final String SAVE_LOYALLIST_NUMBER ="loyallist_number";

    public static void verfiyDefaulUnlinkedtPlentiCardExists(){
        Wait.secondsUntilElementPresent("new_my_account.plenti_card_art",20);
        Wait.isPageLoaded();
        Assert.assertTrue("Plenti Card not exists in MyAccount Page",Elements.elementPresent("new_my_account.plenti_card"));
        Assert.assertTrue("Plenti Header not exists in MyAccount Page",Elements.elementPresent("new_my_account.plenti_header"));
        Assert.assertTrue("Plenti lookup by phone number tab not exists",Elements.elementPresent("new_my_account.plenti_phone_number_tab"));
        Assert.assertTrue("Plenti lookup by card number tab not exists",Elements.elementPresent("new_my_account.plenti_card_number_tab"));
        Assert.assertTrue("Plenti image not exists",Elements.elementPresent("new_my_account.plenti_card_art"));
        Assert.assertTrue("Plenti learn more not exists",Elements.elementPresent("new_my_account.plenti_learn_more"));
    }

    public static void verifyPlentiEnrolled(){
        Wait.secondsUntilElementNotPresent("new_my_account.plenti_link_account",20);
        Assert.assertFalse("Enroll plenti to current user failed",Elements.elementPresent("new_my_account.plenti_card_number_textbox"));
        Assert.assertFalse("Enroll plenti to current user failed",Elements.elementPresent("new_my_account.plenti_link_account"));
    }

    public static boolean addPlentiByCardNumber(String cardNumber){
        Wait.secondsUntilElementPresent("new_my_account.plenti_link_account",20);
        Clicks.click("new_my_account.plenti_card_number_tab");
        TextBoxes.typeTextbox("new_my_account.plenti_card_number_textbox",cardNumber);
        Clicks.click("new_my_account.plenti_link_account");
        return true;
    }

    public static boolean addPlentiByPhoneNumber(String phoneNumber){
        Wait.secondsUntilElementPresent("new_my_account.plenti_link_account",20);
        Clicks.click("new_my_account.plenti_phone_number_tab");
        TextBoxes.typeTextbox("new_my_account.plenti_phone_number_textbox",phoneNumber);
        Clicks.click("new_my_account.plenti_link_account");
        return true;
    }

    public static String getErrorMessage(){
        Wait.secondsUntilElementPresent("new_my_account.plenti_error_msg",20);
        return Elements.getText("new_my_account.plenti_error_msg");
    }

    public static String getDefaultPlentiInstruction(){
        return Elements.getText("new_my_account.plenti_guide_text");
    }

    public static String getRewardsText(){
        return Elements.getText("new_my_account.plenti_join_program");
    }

    /*
    This is a common method for all cards on My account page. It validates 2 items:
        Card Header. Ex:
                        MCOM: Macy's Credit Card, Orders, Wallet etc.
                        BCOM: Bloomingdale's Credit Card, Order History, bWallet etc.

        Guide Text. Ex:
                        MCOM: No online orders available at this time.
                              You have no offers saved. To view and add offers, visit Deals & Promotions

     */
    public static void validateCommonFields(String cardName, String expectedText, boolean validateCardHdr) {
        String actualText = "";
        Wait.forPageReady();
        switch(cardName) {
            case MCOM_CREDIT_CARD:
            case BCOM_CREDIT_CARD:
                if(validateCardHdr)
                    actualText = Elements.getText(Elements.element("new_my_account.credit_card_header"));
                else
                    actualText = Elements.getText(Elements.element("new_my_account.credit_card_guide_text"));
                break;
            case MCOM_ORDERS:
            case BCOM_ORDERS:
                if(validateCardHdr) {
                    //Sometimes, there is a delay in response from xAPI so wait for response to come.
                    waitForXapiResponse("new_my_account.orders_body");
                    actualText = Elements.getText(Elements.element("new_my_account.orders_header"));
                }
                else
                    actualText = Elements.getText(Elements.element("new_my_account.orders_body"));
                break;
            case MCOM_WALLET:
            case BCOM_WALLET:
                if(validateCardHdr)
                        actualText = Elements.getText(Elements.element("new_my_account.wallet_header"));
                else
                    if(macys())
                        actualText = Elements.getText(Elements.element("new_my_account.wallet_guide_text2"));
                    else
                        actualText = Elements.getText(Elements.element("new_my_account.wallet_guide_text"));
                break;
            case PLENTI:
                if(validateCardHdr)
                    actualText = Elements.getText(Elements.element("new_my_account.plenti_header"));
                else{
                    actualText = Elements.getText(Elements.element("new_my_account.plenti_guide_text"));
                    actualText = actualText.replace("\n"," ");
                }
                break;
            case LISTS:
            case WISHLISTS:
                if(validateCardHdr) {
                    //Sometimes, there is a delay in response from xAPI so wait for response to come.
                    waitForXapiResponse("new_my_account.lists_guide_text");
                    Navigate.execJavascript("window.scrollBy(0,500)", "");
                    actualText = Elements.getText(Elements.element("new_my_account.lists_header"));
                }
                else {
                    Wait.untilElementPresent("new_my_account.lists_guide_text");
                    if(macys()) {
                        actualText = Elements.getText(Elements.element("new_my_account.lists_guide_text"));
                    } else {
                        actualText = Elements.getText(Elements.element("new_my_account.lists_guide_text"))+ " " + Elements.getText(Elements.element("new_my_account.lists_guide_text2"));
                    }
                    actualText = actualText.replace("\n"," ");
                }
                break;
            case LOYALLIST:
                if(validateCardHdr) {
                    Wait.untilElementPresent("new_my_account.loyallist_header");
                    actualText = Elements.getText(Elements.element("new_my_account.loyallist_header"));
                }
                else {
                    Wait.untilElementPresent("new_my_account.loyallist_guide_text1");
                    actualText = Elements.getText(Elements.element("new_my_account.loyallist_guide_text1"));
                }
                break;
            case STORE:
                if(validateCardHdr)
                    actualText = Elements.getText(Elements.element("new_my_account.store_header"));
                else
                    actualText = Elements.getText(Elements.element("new_my_account.store_guide_text"));
                break;
            case STATS:
                if(validateCardHdr)
                    actualText = Elements.getText(Elements.element("new_my_account.stats_header"));
                else
                    actualText = Elements.getText(Elements.element("new_my_account.stats_guide_text"));
                break;
            default:
                System.out.println("Unexpected card found.");
        }
        Assert.assertEquals("Header mismatch for " + cardName + " card" , expectedText, actualText);
    }

    //This is a common method which is used to validate button labels for all cards that has a btn on it.
    public static void validateMyAccountButtonLabels(String cardName, String expectedBtnLabel){
        String actualBtnLabel = "";
        String learnAndApplyBtnLabel = "";

        switch(cardName) {
            case MCOM_CREDIT_CARD:
                actualBtnLabel = Elements.getText(Elements.element("new_my_account.credit_card_add_card_link_btn"));
                learnAndApplyBtnLabel = Elements.getText(Elements.element("new_my_account.credit_card_learn_n_apply"));
                break;
            case BCOM_CREDIT_CARD:
                actualBtnLabel = Elements.getText(Elements.element("new_my_account.credit_card_add_card"));
                break;
            case PLENTI:
                actualBtnLabel = Elements.getText(Elements.element("new_my_account.plenti_link_account"));
                break;
            default:
                break;
        }
        Assert.assertEquals("Button label mismatch on " + cardName + " card" , expectedBtnLabel, actualBtnLabel);
        if(learnAndApplyBtnLabel.length() < 0)
            Assert.assertEquals("Button label mismatch on " + cardName + " card" , expectedBtnLabel, learnAndApplyBtnLabel);
    }

    public static void validateMyAccountAllButtonsLabels(String cardName, String expectedBtnLabel, String expectedBtnLearnLabel){
        String actualBtnLabel = "";
        String learnAndApplyBtnLabel = "";

        switch(cardName) {
            case MCOM_CREDIT_CARD:
                actualBtnLabel = Elements.getText(Elements.element("new_my_account.credit_card_add_card_link_btn"));
                learnAndApplyBtnLabel = Elements.getText(Elements.element("new_my_account.credit_card_learn_n_apply"));
                break;
            case BCOM_CREDIT_CARD:
                actualBtnLabel = Elements.getText(Elements.element("new_my_account.credit_card_add_card"));
                break;
            case PLENTI:
                actualBtnLabel = Elements.getText(Elements.element("new_my_account.plenti_link_account"));
                break;
            default:
                break;
        }
        Assert.assertEquals("Button label mismatch on " + cardName + " card" , expectedBtnLabel, actualBtnLabel);
        if(learnAndApplyBtnLabel.length() > 0)
            Assert.assertEquals("Button label mismatch on " + cardName + " card" , expectedBtnLearnLabel, learnAndApplyBtnLabel);
    }

    /*
    This is a common method which is used to validate visual data for all cards except - card header, card guide text &
    card btn labels.
     */
    public static void validatePendingInfo(String cardName, Map<String, String> data)  throws EnvException {
        String expectedText;
        String actualText;
        boolean emptyCard = true;
        switch(cardName) {
            case MCOM_CREDIT_CARD:
            case BCOM_CREDIT_CARD:
                expectedText = data.get(PITCH_NEW_CARD);
                if(macys()) {
                    actualText = Elements.getText(Elements.element("new_my_account.credit_card_pitch_card1")) + " " + Elements.getText(Elements.element("new_my_account.credit_card_pitch_card2"));
                } else {
                    actualText = Elements.getText(Elements.element("new_my_account.credit_card_pitch_card"));
                }
                Assert.assertEquals("Pitch card info on " + cardName + " card is not as expected." , expectedText, actualText);
                expectedText = data.get(CREDIT_CARD_DISCLAIMER);
                actualText = Elements.getText(Elements.element("new_my_account.credit_card_disclaimer"));
                Assert.assertEquals("Disclaimer info on " + cardName + " card is not as expected." , expectedText, actualText);
                if(macys()){
                    expectedText = data.get(CARD_EXCLUSIONS);
                    actualText = Elements.getText(Elements.element("new_my_account.credit_card_exclusion_details"));
                    Assert.assertEquals("Exclusion Details info on " + cardName + " card is not as expected." , expectedText, actualText);
                }
                break;
            case MCOM_ORDERS:
            case BCOM_ORDERS:
                expectedText = data.get(ORDER_STATUS);
                actualText = Elements.getText(Elements.element("new_my_account.orders_status_n_history"));
                Assert.assertEquals("Order Status & History link text on " + cardName + " card is not as expected." , expectedText, actualText);
                expectedText = data.get(FURNITURE_AND_MATTRESS_DELIVERY);
                actualText = Elements.getText(Elements.element("new_my_account.orders_furniture_delivery_status"));
                Assert.assertEquals("Furniture & Mattress Delivery status link text on " + cardName + " card is not as expected." , expectedText, actualText);
                validateOrdersCardLinkNavigation(emptyCard);
                break;
            case MCOM_WALLET:
            case BCOM_WALLET:
                if(macys()){
                    expectedText = data.get(WALLET_GUIDE_TEXT_HEADING);
                    actualText = Elements.getText(Elements.element("new_my_account.wallet_guide_text1"));
                    Assert.assertEquals("Default text <Offers:> on " + cardName + " card is not as expected." , expectedText, actualText);
                }
                expectedText = data.get(ADD_PAYMENT_METHOD);
                actualText = Elements.getText(Elements.element("new_my_account.wallet_payment_method"));
                Assert.assertEquals("Add Payment Method link text on " + cardName + " card is not as expected." , expectedText, actualText);
                expectedText = data.get(WALLET);
                actualText = Elements.getText(Elements.element("new_my_account.wallet_landing_page"));
                Assert.assertEquals("Wallet link text on " + cardName + " card is not as expected." , expectedText, actualText);
                expectedText = data.get(GIFT_CARD);
                actualText = Elements.getText(Elements.element("new_my_account.wallet_gift_cards"));
                Assert.assertEquals("Gift Card link text on " + cardName + " card is not as expected." , expectedText, actualText);
                validateWalletCardLinkNavigation(emptyCard);
                break;
            case PLENTI:
                expectedText = data.get(PLENTI_PHONE_TAB);
                actualText = Elements.getText(Elements.element("new_my_account.plenti_phone_tab"));
                Assert.assertEquals("Phone Number tab text on " + cardName + " card is not as expected." , expectedText, actualText);
                expectedText = data.get(PLENTI_NUMBER_TAB);
                actualText = Elements.getText(Elements.element("new_my_account.plenti_number_tab"));
                Assert.assertEquals("Plenti Number tab text on " + cardName + " card is not as expected." , expectedText, actualText);

                //By default phone number tab should be selected.
                WebElement element = Elements.findElement("new_my_account.plenti_phone_tab");
                String isSelected = element.getAttribute("class");
                Assert.assertTrue(isSelected.equalsIgnoreCase("selected"));

                //Also verify that when you toggle between tabs, current selected tab show up as selected by checking its class attribute.
                //Also text above edit box also change accordingly.

                //check for the existence of plenti card image.
                Assert.assertNotNull(Elements.findElement("new_my_account.plenti_card_art"));

                expectedText = data.get(JOIN_PLENTI_PROGRAM);
                actualText = Elements.getText(Elements.element("new_my_account.plenti_join_program"));
                actualText = actualText.replace("\n","");
                Assert.assertEquals("Join Plenti program text on " + cardName + " card is not as expected." , expectedText, actualText);
                validatePlentiCardLinkNavigation(emptyCard);
                break;
            case LOYALLIST:
                expectedText = data.get(LOYALLITY_GUIDE_TEXT2);
                actualText = Elements.getText(Elements.element("new_my_account.loyallist_guide_text2"));
                Assert.assertEquals("Earn rewards Loyallist text on " + cardName + " card is not as expected." , expectedText, actualText);

                expectedText = data.get(LOYALLITY_GUIDE_TEXT3);
                actualText = Elements.getText(Elements.element("new_my_account.loyallist_guide_text3"));
                Assert.assertEquals("Easy & free Loyallist text on " + cardName + " card is not as expected." , expectedText, actualText);

                expectedText = data.get(BECOME_LOYALLIST);
                actualText = Elements.getText(Elements.element("new_my_account.become_loyallist"));
                Assert.assertEquals("Become a Loyallist link text on " + cardName + " card is not as expected." , expectedText, actualText);

                expectedText = data.get(SAVE_LOYALLIST_NUMBER);
                actualText = Elements.getText(Elements.element("new_my_account.save_loyallist_to_account"));
                Assert.assertEquals("Save your Loyallist number to your online account link text on " + cardName + " card is not as expected." , expectedText, actualText);
                break;
            case LISTS:
            case WISHLISTS:
                validateListsCardLinkNavigation(emptyCard);
                break;

        }//end of switch
    }

    /*
    This method validates whether links on Plenti card are navigating to correct we page or not.
    emptyCard = true; //all links on empty state of plenti card will be validated.
    emptyCard = false; //all links on filled state of plenti card will be validated.
     */
    public static void validatePlentiCardLinkNavigation(boolean emptyCard){
        Wait.forPageReady();
        if(emptyCard){
            Clicks.click("new_my_account.plenti_learn_more");
        }
    }

    //This method validates if link navigation for Orders card is working fine or not.
    public static void validateOrdersCardLinkNavigation(boolean emptyCard)  throws EnvException {
        Wait.forPageReady();
        if(emptyCard){
            Clicks.click("new_my_account.orders_status_n_history");
            StepUtils.shouldBeOnPage("order_status");
            Navigate.browserBack();
            waitForXapiResponse("new_my_account.orders_body");
            Clicks.click("new_my_account.orders_furniture_delivery_status");
            validatelinkNavigation("furniture_mattress_url");
        }
    }

    //This method validates if link navigation for Wallet card is working fine or not.
    public static void validateWalletCardLinkNavigation(boolean emptyCard){
        Wait.forPageReady();
        if(bloomingdales()){
            Navigate.execJavascript("window.scrollBy(0,200)", "");
        }
        if(emptyCard){
            Clicks.click("new_my_account.wallet_deals_n_promo");
            Assert.assertNotNull(Elements.findElement("new_my_account.deals_and_promotions_page"));
            Navigate.browserBack();

            /*
            Clicking on Add a Credit Card (BCOM) or Add A Payment Method (MCOM) on original Wallet page brings up an overlay
            / popup.
            Since new My Account page is not supporting overlay at this point of time, clickin on these buttons will navigate
            user to the Wallet page.
             */
            Wait.forPageReady();
            Clicks.click("new_my_account.wallet_payment_method");
            Assert.assertNotNull(Elements.findElement("new_my_account.wallet_page"));
            Navigate.browserBack();

            Wait.forPageReady();
            Clicks.click("new_my_account.wallet_landing_page");
            Assert.assertNotNull(Elements.findElement("new_my_account.wallet_page"));
            Navigate.browserBack();

            Wait.forPageReady();
            Clicks.click("new_my_account.wallet_gift_cards");
            Assert.assertNotNull(Elements.findElement("new_my_account.gift_cards_page"));
            Navigate.browserBack();
        }
    }

    public static void validateListsCardLinkNavigation(boolean emptyCard)   throws EnvException {
        Wait.forPageReady();
        if(emptyCard){
            Clicks.click(Elements.findElement("new_my_account.create_lists_link"));
            StepUtils.shouldBeOnPage("wish_list");
            Navigate.browserBack();
            Wait.forPageReady();
        }
    }

    public static void validateListsData(String defaultWishlist)throws Throwable {
        int expectedNoOfLists = 5;//max number of lists we are showing on Lists card.
        String addedItem = " (1)";

        navigateToMyAccount();
        waitForXapiResponse("new_my_account.lists_count");
        WebDriver driver = WebDriverManager.getWebDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,600)", "");

        List<WebElement> elements = Elements.findElements("new_my_account.lists_count");
        //validate that only 2 orders are shown
        Assert.assertEquals("Error: No. of lists shown is not expected.",expectedNoOfLists, elements.size());

        //Validate if the default list has an item added to it.
        if (macys()){
            addedItem = " (1)";
        } else {
            addedItem = "\n1 Item";
        }
        String finalListLabel = defaultWishlist + addedItem;
        String actualListLabel = Elements.getText(Elements.element("new_my_account.lists_default"));
        Assert.assertEquals("Error: Default wish list label is not as expected.",finalListLabel, actualListLabel);

        //Validate that clicking on a list navigates to correct web page.
        Clicks.click("new_my_account.lists_default");
        StepUtils.shouldBeOnPage("wish_list");
    }

    public static void placeOrdersForAvailableProducts(String ordersToPlace) throws Throwable{
        String productType1 = "available and orderable";
        String productType2 = "master_product";
        CommonUtils.navigateToRandomProduct(productType1, productType2);
//        new ShopAndBrowse.I_add_product_to_my_bag_from_standard_PDP_Page();
    }

    //This method is used to send invalid plenti data - number / phone
    public static void submitInvalidData(String plentiDataType){
        switch(plentiDataType){
            case INVALID_PLENTI_ACC:
                TextBoxes.typeTextbox("new_my_account.plenti_data", INVALID_PLENTI_ACCOUNT_NUMBER);
                break;
            case INVALID_PLENTI_PHONE:
                TextBoxes.typeTextbox("new_my_account.plenti_data", INVALID_PLENTI_PHONE_NUMBER);
                break;
            case PARTIAL_PLENTI_ACC:
                TextBoxes.typeTextbox("new_my_account.plenti_data", "10"/*get partial plenti acc number from usl.yml/json*/);
                break;
            case PARTIAL_PLENTI_PHONE:
                TextBoxes.typeTextbox("new_my_account.plenti_data", "10"/*get partial plenti phone number from usl.yml/json*/);
                break;
        }
    }

    public static void clickLinkPlentiAccountBtn() {
        Clicks.click("new_my_account.plenti_link_account");
    }

    public static void validatePlentiErrorMsgs(String expectedErrMsg) {
        String actualErrMsg = Elements.getText(Elements.element("new_my_account.invalid_plenti_error_msg"));
        Assert.assertEquals("Invalid Plenti number / phone error message on plenti card is not as expected." , expectedErrMsg, actualErrMsg);
    }

    public static void validateNewOrderPlacedDetails(String orderNumber) throws Throwable {
        orderNumber = orderNumber.replace("Order number:\n","");
        navigateToMyAccount();
        waitForXapiResponse("new_my_account.first_order");
        String orderNumberDisplayed = Elements.getText(Elements.element("new_my_account.first_order"));
        Assert.assertEquals("Order number displayed on Orders card do not match with the order number placed." , orderNumber,orderNumberDisplayed);
    }

    public static void validateTwoMostRecentOrderPlacedDetails(List<String> listofOrderNum) throws Throwable{
        navigateToMyAccount();
        waitForXapiResponse("new_my_account.first_order");
        String firstOrderNumDisplayed = Elements.getText(Elements.element("new_my_account.first_order"));
        String secondOrderNumDisplayed = Elements.getText(Elements.element("new_my_account.second_order"));
        //Here we ensure that only 2 most recent orders are displayed on Orders card. The index of 2 most orders
        //will always be 1 or 2 in this list - listofOrderNum
        Assert.assertTrue(listofOrderNum.indexOf(firstOrderNumDisplayed)!= 0);
        Assert.assertTrue(listofOrderNum.indexOf(secondOrderNumDisplayed)!= 0);
    }

    public static void validateOrdersCardFilledState()throws Throwable {
        String ordersSubHeading = "Order number & date";

        navigateToMyAccount();
        waitForXapiResponse("new_my_account.first_order");

        //verify text - "Order number & date"
        if (macys()) {
            String orderSubHeadingDisplayed = Elements.getText(Elements.element("new_my_account.orders_subheading"));
            Assert.assertEquals("Orders card filled status: Order number & date text displayed is not as expected.", ordersSubHeading, orderSubHeadingDisplayed);
        }

        int validOrdersNumberToShow = 2; //at this point we are only showing 2 orders on order card.
        Wait.secondsUntilElementNotPresent(Elements.element("new_my_account.first_order"), 15);
        List<WebElement> elements = Elements.findElements("new_my_account.orders_list");
        //validate that only 2 orders are shown
        Assert.assertEquals("Error: We can show only 2 most recent orders placed.",validOrdersNumberToShow, elements.size());

        //validate that when you click on an order link you are taken to correct Order Details page by checking order number as well.
        String expectedOrderNum = Elements.getText(Elements.element("new_my_account.first_order"));
        Clicks.click("new_my_account.first_order");

        String targetOrderNum = "";
        WebElement el;
        targetOrderNum = Elements.getText(Elements.element("new_my_account.order_label_order_details_page"));
        Assert.assertEquals("Clicking order link on Orders card did not navigate to correct order details page." , expectedOrderNum, targetOrderNum);
        Navigate.browserBack();
        //Sometimes, there is a delay in response from xAPI so wait for response to come.
        waitForXapiResponse("new_my_account.first_order");
        Wait.forPageReady();
        expectedOrderNum = Elements.getText(Elements.element("new_my_account.second_order"));
        Clicks.click("new_my_account.second_order");

        targetOrderNum = "";
        targetOrderNum = Elements.getText(Elements.element("new_my_account.order_label_order_details_page"));
        Assert.assertEquals("Clicking order link on Orders card did not navigate to correct order details page." , expectedOrderNum, targetOrderNum);
        Navigate.browserBack();
        //Sometimes, there is a delay in response from xAPI so wait for response to come.
        waitForXapiResponse("new_my_account.first_order");
    }

    /*
    This method checks for the existence of Registry card.
    A new user with no Registry should not have Registry card displayed.
    */
    public static void validateRegistryCardExistence() throws Throwable{
        WebDriver driver = WebDriverManager.getWebDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,600)", "");
        if (macys()) {
            Assert.assertNull(Elements.findElement("new_my_account.registry_card"));
        } else {
            Assert.assertFalse("Registry card is displayed for new user", Elements.elementPresent("new_my_account.registry_card"));
        }
    }

    public static void validateRegistryCardData() throws Throwable{
        String expectedRegistryHeader = "Registry";
        String expectedRegistryHeaderBcom = "The Registry";
        String expectedEditRegistryLinkText = "Edit Registry Details";
        String expectedRegistryPerksLinkText = "Top Ten Registry Perks";
        String expectedRegistryPerksLinkTextBcom = "Registry Perks & Benefits";
        String expectedViewRegistryLinkText = "View Registry";
        String expectedViewRegistryLinkTextBcom = "VIEW MY REGISTRY";
        String expectedWeddingShopLinkText = "Wedding Shop";
        String expectedWeddingShopLinkTextBcom = "WEDDING SHOP";
        String expectedRegistryHolderName;

        Navigate.execJavascript("window.scrollBy(0,700)", "");

        UserProfile profile = TestUsers.getExistingRegistryUser();
        ProfileAddress currentUser = profile.getUser().getProfileAddress();
        //registry object contains all registry information for currentUser
        Registry registry = profile.getRegistry();

        //validate Registry card header text.
        Thread.sleep(500);
        //Sometimes, there is a delay in response from xAPI so wait for response to come.
        waitForXapiResponse("new_my_account.registry_holder");
        String RegistryHeaderDisplayed = Elements.getText(Elements.element("new_my_account.registry_card_header"));
        if (macys()) {
            Assert.assertEquals("Registry card header on Registry Card is not as expected,", expectedRegistryHeader, RegistryHeaderDisplayed);
        } else {
            Assert.assertEquals("Registry card header on Registry Card is not as expected,", expectedRegistryHeaderBcom, RegistryHeaderDisplayed);
        }

        //validate Registry - holder name.
        expectedRegistryHolderName = currentUser.getFirstName() + " & " + registry.getCoRegistrantFirstName() + "'s " + "Registry";
        String registryHolderNameDisplayed = Elements.getText(Elements.element("new_my_account.registry_holder"));
        Assert.assertEquals("Registry holder name on Registry Card is not as expected," , expectedRegistryHolderName.toLowerCase(), registryHolderNameDisplayed.toLowerCase());

        //validate Registry - wedding date
        String expectedWeddingDate =registry.getEventMonth() + " " + registry.getEventDay() + ", " + registry.getEventYear();
        String weddingDateDisplayed = Elements.getText(Elements.element("new_my_account.registry_wedding_date"));
        weddingDateDisplayed = weddingDateDisplayed.replace("Wedding Date:\n","");
        Assert.assertEquals("Wedding date displayed on Registry Card is not as expected," , expectedWeddingDate, weddingDateDisplayed);
       //validate Registry - Edit Registry Details link text.
        String EditRegistryLinkTextDisplayed = Elements.getText(Elements.element("new_my_account.edit_registry"));
        Assert.assertEquals("Edit Registry link text displayed on Registry Card is not as expected," , expectedEditRegistryLinkText, EditRegistryLinkTextDisplayed);

        //validate Registry - Edit Registry Details link navigation.
        Clicks.click("new_my_account.edit_registry");
        validatelinkNavigation("edit_registry_url");
        //Sometimes, there is a delay in response from xAPI so wait for response to come.
        waitForXapiResponse("new_my_account.registry_holder");

        //validate Registry - Top Ten Registry Perks link test.
        Navigate.execJavascript("window.scrollBy(0,200)", "");
        Thread.sleep(500);
        String RegistryPerksLinkTextDisplayed = Elements.getText(Elements.element("new_my_account.registry_benefits"));
        if (macys()) {
            Assert.assertEquals("Registry benefit link text displayed on Registry Card is not as expected,", expectedRegistryPerksLinkText, RegistryPerksLinkTextDisplayed);
        } else {
            Assert.assertEquals("Registry benefit link text displayed on Registry Card is not as expected,", expectedRegistryPerksLinkTextBcom, RegistryPerksLinkTextDisplayed);

        }

        //validate Registry - Top Ten Registry Perks link navigation.
        if (macys()) {
            Clicks.click("new_my_account.registry_benefits");
            validatelinkNavigation("registry_top_10_url");
            //Sometimes, there is a delay in response from xAPI so wait for response to come.
            waitForXapiResponse("new_my_account.registry_holder");
        }

        //validate Registry - View Registry link text.
        String ViewRegistryLinkTextDisplayed = Elements.getText(Elements.element("new_my_account.view_registry_details"));
        if (macys()) {
            Assert.assertEquals("View Registry link text displayed on Registry Card is not as expected,", expectedViewRegistryLinkText, ViewRegistryLinkTextDisplayed);
        } else {
            Assert.assertEquals("View Registry link text displayed on Registry Card is not as expected,", expectedViewRegistryLinkTextBcom, ViewRegistryLinkTextDisplayed);
        }

        //validate Registry - View Registry link navigation.
        Clicks.click("new_my_account.view_registry_details");
        validatelinkNavigation("view_registry_url");
        //Sometimes, there is a delay in response from xAPI so wait for response to come.
        waitForXapiResponse("new_my_account.registry_holder");

        //validate Registry - Wedding Shop link text.
        Wait.forPageReady();
        String WeddingShopLinkTextDisplayed = Elements.getText(Elements.element("new_my_account.registry_wedding_shop"));
        if (macys()) {
            Assert.assertEquals("Wedding Shop link text displayed on Registry Card is not as expected,", expectedWeddingShopLinkText, WeddingShopLinkTextDisplayed);
        } else {
            Assert.assertEquals("Wedding Shop link text displayed on Registry Card is not as expected,", expectedWeddingShopLinkTextBcom, WeddingShopLinkTextDisplayed);
        }

        //validate Registry - Wedding Shop link navigation.
        Clicks.click("new_my_account.registry_wedding_shop");
        validatelinkNavigation("wedding_shop_url");
        //Sometimes, there is a delay in response from xAPI so wait for response to come.
        waitForXapiResponse("new_my_account.registry_holder");
    }

    /*
    This method helps in validating any link navigation that happen from any of the card on Responsive
    MyAccount page.
    Whatever key gets passed to this method, corresponding value gets picked from json file and checked if it is
    a part of current url.
     */
    public static void validatelinkNavigation(String name){
        boolean linkNavigationIsIncorrect = false;
        String currentUrl = WebDriverManager.getCurrentUrl();
        ArrayList<String> expectedURLs = Elements.getValues("new_my_account." + name);
        for (String expectedURL : expectedURLs) {
            if (expectedURL != null && !(currentUrl.contains(expectedURL)))
                linkNavigationIsIncorrect = true;
        }
        Assert.assertFalse(linkNavigationIsIncorrect);
        Navigate.browserBack();
    }

    /*
    Every time we come to Responsive My Account page, there is a possibility of delay in xapi response.
    We are going to wait max 15 seconds.
     */
    static void waitForXapiResponse(String name) {
        Wait.secondsUntilElementPresent(name, 15);
    }

    public static void navigateToMyAccount()  throws Throwable{
        //---Temporary piece, needs further investigation.
//        try {
//            Actions action = new Actions(WebDriverManager.getWebDriver());
//            action.moveToElement(Elements.findElement("home.goto_my_account_link")).perform();
//            Thread.sleep(500L);
//            WebDriverManager.getWebDriver().findElement(By.xpath(".//*[@id='dropDownABTest']/ul/li[1]/a")).click();
//        } catch (DriverNotInitializedException e) {
//            Assert.fail("Driver not initialize");
//        }
        Clicks.click("home.goto_guest_my_account");
        //---
    }

    public static void navigateToMyAccountUrl(){
        Navigate.visit("my_account");
    }

    /*This method helps in validating default Wallet card links and buttons*/
    public static void validateLinksAndButtonOnWallet(){

        // Validate wallet card  Offer Link on responsive my account page.
        if(Elements.elementPresent("new_my_account.myaccount_wallet_deals_promotions")) {
            Assert.assertTrue("PASSED Successfully validated wallet card offer link on responsive MyAccount page", true);
        }else
            Assert.fail("FAILED find element: new_my_account.myaccount_wallet_deals_promotions");

        if(Elements.elementPresent("new_my_account.wallet_add_payment"))
            Assert.assertTrue("PASSED Successfully validated wallet card body add payment link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card  body add payment link on responsive MyAccount page");

        // Validate wallet card  footer links on responsive my account page.
        if(Elements.elementPresent("new_my_account.wallet_footer_wallet_link"))
            Assert.assertTrue("PASSED Successfully validated wallet card footer wallet link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer wallet link on responsive MyAccount page");

        if(Elements.elementPresent("new_my_account.wallet_footer_gift_link"))
            Assert.assertTrue("PASSED Successfully validated wallet card footer gift link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer wallet link on responsive MyAccount page");

    }

    public static void validateDefaultWalletCardTexts(Map<String,String> data) throws Throwable {

        String strActualText="";
        // Validate wallet card  header
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_header"));
        logger.info("Wallet card text is " +strActualText);
        if(data.get("header").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card header text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card header text on responsive MyAccount page");

        // Validate wallet card  offer label
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_offer_text"));
        logger.info("Wallet offer text is " +strActualText);
        if(data.get("offer_txt").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card offer text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card  offer text on responsive MyAccount page");

        // Validate wallet card  offer details text
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_promotion_text"));
        logger.info("Wallet promotion text is " +strActualText);
        if(data.get("promotion_txt").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card promotion text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card  promotion text on responsive MyAccount page");

        // Validate wallet card  Add payment label
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_add_payment"));
        logger.info("Wallet Add payment text is " +strActualText);
        if(data.get("add_payment_method_txt").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card add payment text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card Add payment text on responsive MyAccount page");

        // Validate wallet card  footer_wallet_link text
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_footer_wallet_link"));
        logger.info("Footer wallet text is " +strActualText);
        if(data.get("footer_wallet_txt").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card footer_wallet_link text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer_wallet_link text on responsive MyAccount page");


        // Validate wallet card  footer links on responsive my account page.
        if(Elements.elementPresent("new_my_account.wallet_footer_wallet_link"))
            Assert.assertTrue("PASSED Successfully validated wallet card footer wallet link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer wallet link on responsive MyAccount page");

        if(Elements.elementPresent("new_my_account.wallet_footer_gift_link"))
            Assert.assertTrue("PASSED Successfully validated wallet card footer gift link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer wallet link on responsive MyAccount page");
    }

    public static void validateWalletCardTextsAfterAddingCard(Map<String,String> data) throws Throwable {
        String strActualText="";
        // Validate wallet Header label
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_header"));
        if(data.get("header").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card wallet Header text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card wallet Header text on responsive MyAccount page");

        // Validate wallet Change Default Card label
        strActualText = Elements.getText(Elements.element("new_my_account.wallet_change_default_card_text"));
        if(data.get("Change_Default_Card_txt").equals(strActualText))
            Assert.assertTrue("PASSED Successfully validated wallet card Change Default Card text on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card  Change Default Card text on responsive MyAccount page");

        // Validate wallet card  footer links on responsive my account page.
        if(Elements.elementPresent("new_my_account.wallet_footer_wallet_link"))
            Assert.assertTrue("PASSED Successfully validated wallet card footer wallet link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer wallet link on responsive MyAccount page");

        if(Elements.elementPresent("new_my_account.wallet_footer_gift_link"))
            Assert.assertTrue("PASSED Successfully validated wallet card footer gift link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card footer wallet link on responsive MyAccount page");

    }

    public static void vaildateEditCardLinkFunctionality(){
        Wait.untilElementPresent("new_my_account.wallet_Edit_CreditCard_link");
        if(Elements.elementPresent("new_my_account.wallet_Edit_CreditCard_link")) {
            Assert.assertTrue("PASSED Successfully validated wallet card with Edit Link on responsive MyAccount page", true);
            Clicks.click("new_my_account.wallet_Edit_CreditCard_link");
            if(Elements.elementPresent("oc_my_wallet.credit_cards_info"))
                Assert.assertTrue("PASSED Successfully validated wallet edit link on responsive MyAccount page", true);
            else
                Assert.fail("FAILED to validate wallet Edit link on responsive MyAccount page");
        }else
            Assert.fail("FAILED to validate wallet card with Edit Link on responsive MyAccount page");
    }

    public static void validateWalletFooterLinks()throws Throwable {
        Clicks.click("new_my_account.wallet_footer_wallet_link");
        if (macys()) {
            Wait.untilElementPresent("oc_my_wallet.credit_cards_info");
        } else {
            Wait.untilElementPresent("my_bwallet.add_credit_card_btn");
        }
        if (Elements.elementPresent("oc_my_wallet.credit_cards_info") || Elements.elementPresent("my_bwallet.add_credit_card_btn"))
            Assert.assertTrue("PASSED Successfully validated wallet card wallet footer link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate wallet card wallet footer link on responsive MyAccount page");
        if (macys()) {
            navigateToMyAccount();
        } else {
            navigateToMyAccountUrl();
        }
        Clicks.click("new_my_account.wallet_footer_gift_link");
        Wait.untilElementPresent("gift_card_balance.url");
    }
    /*
        This is Add credit card to wallet on my account only.
     */
    public static void AddCreditCardToWallet(String cardType)throws Throwable{
        Wait.untilElementPresent("add_credit_card_popup.credit_card_type");
        CreditCard credit_card = MyAccount.getValidCreditCard(cardType);
        UserProfile customer = TestUsers.getCustomer(null);
        String phoneNum = TestUsers.generateRandomPhoneNumber();
        DropDowns.selectByText("add_credit_card_popup.credit_card_type", credit_card.getCardType().name);
        TextBoxes.typeTextbox("add_credit_card_popup.card_number", credit_card.getCardNumber());
        if(!cardType.equals("Macy's") && !cardType.equals("Bloomingdale's") && !cardType.equals("Bloomingdale's Employee Card")) {
            if (macys()) {
                DropDowns.selectByText("add_credit_card_popup.expiry_month", credit_card.getExpiryMonth());
            } else {
                String month;
                month=credit_card.getExpiryMonthIndex() + " - " + credit_card.getExpiryMonth();
                DropDowns.selectByText("add_credit_card_popup.expiry_month", month);
            }
            DropDowns.selectByText("add_credit_card_popup.expiry_year", credit_card.getExpiryYear());
        }
        TextBoxes.typeTextbox("add_credit_card_popup.first_name", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("add_credit_card_popup.last_name", customer.getUser().getProfileAddress().getLastName());
        TextBoxes.typeTextbox("add_credit_card_popup.address_line_1",  customer.getUser().getProfileAddress().getAddressLine1());
        TextBoxes.typeTextbox("add_credit_card_popup.address_line_2", customer.getUser().getProfileAddress().getAddressLine2());
        TextBoxes.typeTextbox("add_credit_card_popup.address_city", customer.getUser().getProfileAddress().getCity());
        DropDowns.selectByText("add_credit_card_popup.address_state", "California");
        TextBoxes.typeTextbox("add_credit_card_popup.card_phone_number", phoneNum);
        TextBoxes.typeTextbox("add_credit_card_popup.address_zip_code", customer.getUser().getProfileAddress().getZipCode());
        if (bloomingdales()){
            TextBoxes.typeTextbox("add_credit_card_popup.card_email_address", TestUsers.generateRandomEmail(7));
        }
        Clicks.javascriptClick("add_credit_card_popup.save_card");
        Wait.untilElementPresent("new_my_account.wallet_Edit_CreditCard_link");
        if(Elements.elementPresent("new_my_account.wallet_Edit_CreditCard_link"))
            Assert.assertTrue("PASSED Successfully validated CC added to wallet card with Edit Link on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate CC added to wallet card with Edit Link on responsive MyAccount page");
    }


}//end of class
