package com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom.OCWallet;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.macys.sdt.framework.utils.AbbreviationHelper.getStateAbbreviation;
import static com.macys.sdt.framework.utils.AbbreviationHelper.translateStateAbbreviation;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;
import static com.macys.sdt.projects.Marketing.OCWallet.actions.website.bcom.pages.MyWallet.getCardsInWallet;
import static org.junit.Assert.assertTrue;


public class Wallet extends StepUtils {

    private static MyAccountSteps myAccountSteps = new MyAccountSteps();

    private static CreditCard.CardType defaultCard = null;
   // List<Map<String, String>> promoCodes;
    private static final Logger logger = LoggerFactory.getLogger(OCWallet.class);
    private static PromotionService promotionService = new PromotionService();
    public static ArrayList<String> promoList= new ArrayList<String>();
   // public static int totalOffers;


    /**
     * Gets valid credit card from "valid_cards.json" based on card_type
     *
     * @return JSONObject containing credit card information
     */
    public static CreditCard getValidCreditCard(String cardType) {

        JSONArray creditCards;
        String jsonTxt = null;
        try {

            File cardFile = getResourceFile("valid_cards.json");
            jsonTxt = Utils.readTextFile(cardFile);
        } catch (IOException e) {
            Assert.fail("Unable to read valid_cards file");
        }

        try {
            JSONObject json = new JSONObject(jsonTxt);
            if (macys()) {
                creditCards = (JSONArray) json.get("macys");
                cardType = cardType.equalsIgnoreCase("Macy's Employee Card") ? "Employee Card" : cardType;
            } else {
                creditCards = (JSONArray) json.get("bloomingdales");
                cardType = cardType.equalsIgnoreCase("Bloomingdale's Card") ? "Bloomingdale's" : cardType;
                cardType = cardType.equalsIgnoreCase("Bloomingdale's American Express® Card") ? "Bloomingdale's American Express" : cardType;
            }

            for (int i = 0; i < creditCards.length(); i++) {
                CreditCard.CardType type = null;
                JSONObject card = creditCards.getJSONObject(i);
                if (card.get("card_type").toString().equalsIgnoreCase(cardType)) {
                    type = CreditCard.CardType.fromString(card.getString("card_type"));
                    return new CreditCard(
                            type,
                            card.getString("card_number"),
                            card.getString("security_code"),
                            card.getDouble("balance"),
                            card.getString("expiry_month"),
                            card.getString("expiry_month_index"),
                            card.getString("expiry_year")
                    );
                }
            }
            return null;
        } catch (JSONException e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
    }


    public static ArrayList<CreditCard> getListOfAddedCardsInWallet() {

        ArrayList<CreditCard> cardsInWallet = new ArrayList<>();
        String expMonth = null, expMonthIndex = null, expYear = null;
        List<WebElement> cardElements = null;
        String expDate;
        String cardType;
        String cardNum = null;
        CreditCard.CardType enumCardType = null;
        if (macys()) {
            cardElements = Elements.findElements("wallet.credit_cards_section");
            for (WebElement e : cardElements) {

                String cardDetails = e.getText();
                String[] splitCardDetails = cardDetails.split("\\r?\\n");

                for (int i = 0; i < splitCardDetails.length; i++) {

                    if (i == 0) {
                        int index = splitCardDetails[i].indexOf("*");
                        if (index != -1 && splitCardDetails[i].contains("®")) {
                            cardType = splitCardDetails[i].substring(0, index - 1);

                        } else {
                            cardType = splitCardDetails[i].substring(0, index);
                        }
                        enumCardType = CreditCard.CardType.fromString(cardType);
                        cardNum = splitCardDetails[i].substring(splitCardDetails[i].lastIndexOf("*") + 1);
                    }

                    if (i == 1 || i == 2) {
                        if (i == 1 && splitCardDetails[i].equalsIgnoreCase("Default")) {
                            Wallet.defaultCard = enumCardType;
                        } else {
                            expDate = splitCardDetails[i].substring(splitCardDetails[i].lastIndexOf(":") + 1).trim();
                            if (!expDate.isEmpty()) {
                                expMonth = getExpMon(expDate);
                                expMonthIndex = expDate.substring(0, 2);
                                expYear = expDate.substring(3, 5);
                            }
                            break;
                        }
                    }
                }

                cardsInWallet.add(new CreditCard(enumCardType, cardNum, "", 0, expMonth, expMonthIndex, expYear));
            }
            return cardsInWallet;
        } else {
            return getCardsInWallet();

        }
    }

    public static void verifyWalletCardsMatchAddedCards(ArrayList<CreditCard> walletCards, ArrayList<CreditCard> addedCards) {

        CreditCard walletCard, addedCard;
        String fullCardNum;

        for (CreditCard walletCard1 : walletCards) {

            walletCard = walletCard1;

            for (CreditCard addedCard1 : addedCards) {
                addedCard = addedCard1;
                fullCardNum = addedCard.getCardNumber();

                if (walletCard.getCardType().equals(addedCard.getCardType())) {
                    if (!walletCard.getCardType().toString().equals("EMPLOYEE_CARD") && !walletCard.getCardType().toString().equals("MACYS") && !walletCard.getCardType().toString().equals("BLOOMINGDALES") && !walletCard.getCardType().toString().equals("BLOOMINGDALES_EMPLOYEE_CARD")) {
                        assertTrue("Expiry month do not match", walletCard.getExpiryMonth().equalsIgnoreCase(addedCard.getExpiryMonth()));
                        assertTrue("Expiry year do not match", walletCard.getExpiryYear().equalsIgnoreCase(addedCard.getExpiryYear().substring(2, 4)));
                    }

                    assertTrue("Card num does not match", walletCard.getCardNumber().equals(addedCard.getCardNumber().substring(fullCardNum.length() - 4, fullCardNum.length())));
                    if (macys() && walletCard.getCardType().name.equalsIgnoreCase("American Express")) {
                        assertTrue("Default card is not Amex", walletCard.getCardType() == defaultCard);
                    }
                    Wait.forPageReady();
                    String pageName = macys() ? "oc_my_wallet":"my_bwallet";
                    List<WebElement> cardRowElementsList = Elements.findElements(pageName + ".card_row");
                    //add validations for logo and edit pencil icon
                    for (WebElement cardRowElement : cardRowElementsList) {
                        if (cardRowElement.findElement(Elements.element(macys() ? "oc_my_wallet.card_type_name" : "my_bwallet.credit_card_types")).getText().contains(walletCard.getCardNumber())) {
                            if (macys()) {
                                assertTrue("Card Logo has not been displayed correctly.", cardRowElement.findElement(Elements.element("oc_my_wallet.card_logo")).
                                        getAttribute("aria-label").contains("credit card is " + walletCard.getCardType().name + " with the last four numbers of " + walletCard.getCardNumber()));
                                assertTrue("Edit Card pencil icon has not been displayed for the card ending." + walletCard.getCardNumber(), cardRowElement.findElement(Elements.element("oc_my_wallet.edit_card")) != null);
                                break;
                            } else {
                                //add validations to check edit and remove links
                                assertTrue("Edit Card link has not been displayed correctly.", cardRowElement.findElement(Elements.element("my_bwallet.edit_credit_cards")) != null);
                                assertTrue("Remove Card link has not been displayed correctly.", cardRowElement.findElement(Elements.element("my_bwallet.delete_credit_cards")) != null);
                            }

                        }

                    }


                    break;
                }
            }
        }
    }

    public static String getExpMon(String value) {

        String extractExpMonth;
        String month = null;
        if (value.length() > 2) {
            extractExpMonth = value.substring(0, 2);
        } else {
            extractExpMonth = value;
        }

        switch (extractExpMonth) {

            case "01":
            case "1":
                month = "January";
                break;
            case "02":
            case "2":
                month = "February";
                break;
            case "03":
            case "3":
                month = "March";
                break;
            case "04":
            case "4":
                month = "April";
                break;
            case "05":
            case "5":
                month = "May";
                break;
            case "06":
            case "6":
                month = "June";
                break;
            case "07":
            case "7":
                month = "July";
                break;
            case "08":
            case "8":
                month = "August";
                break;
            case "09":
            case "9":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
        }

        return month;
    }

    public static void verifyWalletWithDBCardDetails(ArrayList<CreditCard> dbCards, ArrayList<CreditCard> walletCards) {

        ProfileAddress walletCardAddress = TestUsers.getCustomer(null).getUser().getProfileAddress();
        ProfileAddress dbCardAddress;
        String fullCardNum;

        for (CreditCard walletCard : walletCards) {

            fullCardNum = walletCard.getCardNumber();

            for (CreditCard dbCard : dbCards) {

                dbCardAddress = dbCard.getAddress();

                if (walletCard.getCardType().equals(dbCard.getCardType())) {
                    if (!walletCard.getCardType().toString().equals("EMPLOYEE_CARD") && !walletCard.getCardType().toString().equals("MACYS") && !walletCard.getCardType().toString().equals("BLOOMINGDALES_EMPLOYEE_CARD") && !walletCard.getCardType().toString().equals("BLOOMINGDALES")) {
                        assertTrue("Expiry month do not match", walletCard.getExpiryMonth().equalsIgnoreCase(dbCard.getExpiryMonth()));
                        assertTrue("Expiry year do not match", walletCard.getExpiryYear().equalsIgnoreCase(dbCard.getExpiryYear()));
                    }

                    assertTrue("Card Num do not match", walletCard.getCardNumber().substring(fullCardNum.length() - 4, fullCardNum.length()).contentEquals(dbCard.getCardNumber().toString()));
                    if (macys() && walletCard.getCardType().name.contentEquals("American Express"))
                        assertTrue("Default card is not Amex", walletCard.getCardType() == defaultCard);

                    assertTrue("First Name do not match", walletCardAddress.getFirstName().contentEquals(dbCardAddress.getFirstName()));
                    assertTrue("Last Name do not match", walletCardAddress.getLastName().contentEquals(dbCardAddress.getLastName()));
                    assertTrue("Email do not match", walletCardAddress.getEmail().contentEquals(dbCardAddress.getEmail()));
                    assertTrue("Address Line1 do not match", walletCardAddress.getAddressLine1().contentEquals(dbCardAddress.getAddressLine1()));
                    // Assert.assertTrue("Address Line2 do not match", walletCardAddress.getAddressLine2().contentEquals(DBCardAddress.getAddressLine2()));
                    // Assert.assertTrue("Country Code do not match", walletCardAddress.getCountryCode().contentEquals(DBCardAddress.getCountryCode()));
                    assertTrue("City do not match", walletCardAddress.getCity().contentEquals(dbCardAddress.getCity()));
                    assertTrue("State do not match, State on Wallet UI: " + walletCardAddress.getState() + " State in DB: " + dbCardAddress.getState(),
                            walletCardAddress.getState().contentEquals((walletCardAddress.getState().length()==2 && dbCardAddress.getState().length()==2)?dbCardAddress.getState():translateStateAbbreviation(getStateAbbreviation(dbCardAddress.getState()))));
                    //Assert.assertTrue("Zip code do not match", walletCardAddress.getZipCode() == DBCardAddress.getZipCode());
                    // Assert.assertTrue("First Name do not match", walletCardAddress.getFirstName().contentEquals(DBCardAddress.getFirstName()));

                    break;
                }
            }
        }
    }

    public static void verifyExpirationFields(CreditCard card) {

        assertTrue(card.getCardType().name + " card expiration month is not disabled",
                Elements.findElement(macys() ? "credit_card_dialog.expiry_month" : "credit_card_dialog.expiry_month_disabled").getAttribute("disabled") != null);//;isEnabled());
        assertTrue(card.getCardType().name + " card expiration year is not disabled",
                Elements.findElement(macys() ? "credit_card_dialog.expiry_year" : "credit_card_dialog.expiry_year_disabled").getAttribute("disabled") != null);
    }

    /**
     * Gets valid credit card from "valid_cards.json"
     *
     * @param numCards num of card details to fetch
     * @return JSONObject containing credit card information
     */
    public static ArrayList<CreditCard> getValidCreditCards(int numCards) {
        ArrayList<CreditCard> cards = new ArrayList<>();
        String previousCardNum = "NotNull";
        try {
            JSONArray creditCards;
            File cardFile = getResourceFile("valid_cards.json");
            String jsonTxt = Utils.readTextFile(cardFile);
            JSONObject json = new JSONObject(jsonTxt);
            creditCards = json.getJSONArray(macys() ? "macys" : "bloomingdales");

            for (int i = 0; i < numCards || cards.size() < numCards; i++) {
                CreditCard.CardType type = null;
                JSONObject card = creditCards.getJSONObject(i);
                String currentCardNum = card.getString("card_number");

                if (!previousCardNum.equals(currentCardNum)) {
                    type = CreditCard.CardType.fromString(card.getString("card_type"));
//                if (card.get("card_type").toString().equalsIgnoreCase("Bloomingdale's Card")) {
//                    type = CreditCard.CardType.BLOOMINGDALES;
//                } else if (card.get("card_type").toString().equalsIgnoreCase("Bloomingdale's American Express® Card")) {
//                    type = CreditCard.CardType.BLOOMINGDALES_AMERICAN_EXPRESS;
//                }
                    cards.add(new CreditCard(
                            type,
                            card.getString("card_number"),
                            card.getString("security_code"),
                            card.getDouble("balance"),
                            card.getString("expiry_month"),
                            card.getString("expiry_month_index"),
                            card.getString("expiry_year")
                    ));
                }
                previousCardNum = currentCardNum;
            }
            return cards;
        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
    }

    /**
     * Add valid offer manually in wallet page"
     *
     * @param promoCode to be entered
     */
    public static void addValidOffer(String promoCode) {

        TextBoxes.typeTextbox("add_offer_dialog.promo_code", "");
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", promoCode);
        Clicks.click("add_offer_dialog.save_offer");
    }

    /**
     * Generating SUPCs"
     *
     * @param promotion_code promotion_code for which SUPC to be genrated
     * @return String containing SUPC
     */
    public static String generateSUPC(String promotion_code) throws Throwable {

       // ArrayList<String> supcs = new ArrayList<String>();
        String supc;
        EnvironmentDetails.AppDetails appDetails = EnvironmentDetails.otherApp("MSPOrder");
      //  OCWallet.promoCodes = promotionService.getWalletEligibleSUPCPromoCodesByCount(num);
      //  promotion_code = new PromotionService().getPromotionCode();
        String promo_code_id = new PromotionService().getPromoCodeId(promotion_code);
        new PromotionService().insertSupcRecord(promo_code_id);
        String url = "http://" + appDetails.hostName  +
                ":8080/api/order/v2/promotions/supcs/" + promotion_code + "?numberOfSupc=1";
        Map<String, String > headers = new HashMap<>();
        headers.put("X-Macys-ClientId", "1913161491");
        Response response = RESTOperations.doGET(url,headers);
        String jsonText = response.readEntity(String.class);
        JSONObject jsonObj = new JSONObject(jsonText);
        JSONArray jsonArray = jsonObj.getJSONObject("PromotionCodeSupcResponse").getJSONArray("supc");
        if (jsonArray.length() == 0) {
            Assert.fail("SUPC generation failed");
        }

      //  for (int i=0; i < num; i++) {
            JSONObject jsonSupc = jsonArray.getJSONObject(0);
            supc = jsonSupc.get("supcCode").toString();
          //  supcs.add(supc);
      //  }
        return supc;
    }

    /**
     * Generating SUPCs of type Organic Registration"
     *
     * @param num number of SUPCs to be generated
     * @return ArrayList containing SUPCs
     */
    public static ArrayList generateOrganicSUPC(int num) throws Throwable {

        ArrayList<String> supcs = new ArrayList<String>();
        EnvironmentDetails.AppDetails appDetails = EnvironmentDetails.otherApp("MSPOrder");
       // String promotion_code = new PromotionService().getPromotionCode();
        String promotion_code = "X03544_2000000";
        String promo_code_id = new PromotionService().getPromoCodeId(promotion_code);
        new PromotionService().insertSupcRecord(promo_code_id);
        String url = "http://" + appDetails.hostName  +
                ":8080/api/order/v2/promotions/supcs/" + promotion_code + "?numberOfSupc=" + num;
        Map<String, String > headers = new HashMap<>();
        headers.put("X-Macys-ClientId", "1913161491");
        Response response = RESTOperations.doGET(url,headers);
        String jsonText = response.readEntity(String.class);
        JSONObject jsonObj = new JSONObject(jsonText);
        JSONArray jsonArray = jsonObj.getJSONObject("PromotionCodeSupcResponse").getJSONArray("supc");
        if (jsonArray.length() == 0) {
            Assert.fail("SUPC generation failed");
        }

        for (int i=0; i < num; i++) {
            JSONObject jsonSupc = jsonArray.getJSONObject(i);
            String supc = jsonSupc.get("supcCode").toString();
            supcs.add(supc);
        }
        return supcs;
    }

    /**
     * Generating expired SUPCs"
     *
     * @param num number of SUPCs to be generated
     * @return ArrayList containing expired SUPCs
     */
    public static ArrayList<String> generateExpiredSUPC(int num) throws Throwable {

        ArrayList<String> supcs = new ArrayList<String>();
        EnvironmentDetails.AppDetails appDetails = EnvironmentDetails.otherApp("MSPOrder");
        String promotion_code = new PromotionService().getExpiredSupcPromotionCode();
        String promo_code_id = new PromotionService().getPromoCodeId(promotion_code);
        new PromotionService().insertSupcRecord(promo_code_id);
        String url = "http://" + appDetails.hostName  +
                ":8080/api/order/v2/promotions/supcs/" + promotion_code + "?numberOfSupc=" + num;
        Map<String, String > headers = new HashMap<>();
        headers.put("X-Macys-ClientId", "1913161491");
        Response response = RESTOperations.doGET(url,headers);
        String jsonText = response.readEntity(String.class);
        JSONObject jsonObj = new JSONObject(jsonText);
        JSONArray jsonArray = jsonObj.getJSONObject("PromotionCodeSupcResponse").getJSONArray("supc");
        if (jsonArray.length() == 0) {
            Assert.fail("SUPC generation failed");
        }

        for (int i=0; i < num; i++) {
            JSONObject jsonSupc = jsonArray.getJSONObject(i);
            String supc = jsonSupc.get("supcCode").toString();
            supcs.add(supc);
        }
        return supcs;
    }

    /**
     * verify SUPC, Wallet order in wallet page
     *
     * @param supc to be added
     * @param promoCode to be added
     * @throws Throwable if any exception occurs
     */
    public static void verifySupcAndPromoOrderInWallet(String supc, String promoCode) throws Throwable {

            Wait.forPageReady();
            ArrayList<String> promoCodes = new ArrayList<String>();
            HashMap<String, Integer> expectedPromoAndOrder = new HashMap<String, Integer>();
            HashMap<String, Integer> actualPromoAndOrder = new HashMap<String, Integer>();
            expectedPromoAndOrder.put(supc, 1);
            expectedPromoAndOrder.put(promoCode, 2);
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(supc);
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(promoCode);
            actualPromoAndOrder = extractPromotionsOrderFromWalletPage();
            Assert.assertTrue("SUPC & PromoCode order not correct in Wallet",
                    actualPromoAndOrder.get(supc) == expectedPromoAndOrder.get(supc));

    }

    public static void verifyBothSupcOrderInWallet(List<Map<String,String>> supcs) throws Throwable {

        Wait.forPageReady();
        Integer order = 0;
        HashMap<String, Integer> expectedPromoAndOrder = new HashMap<String, Integer>();
        HashMap<String, Integer> actualPromoAndOrder = new HashMap<String, Integer>();

        for (Map<String,String> supc: supcs) {
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(supc.get("SUPC"));
            actualPromoAndOrder.put(supc.get("SUPC"), order++);
        }

        actualPromoAndOrder = extractPromotionsOrderFromWalletPage();
        Assert.assertTrue("SUPC & PromoCode order not correct in Wallet",
                actualPromoAndOrder.get(supcs.get(0)) == expectedPromoAndOrder.get(supcs.get(0)));
    }

    public static void verifyBothPromoOrderInWallet(ArrayList<String> promoCodes) throws Throwable {

        Wait.forPageReady();
        Integer order = 0;
        HashMap<String, Integer> expectedPromoAndOrder = new HashMap<String, Integer>();
        HashMap<String, Integer> actualPromoAndOrder = new HashMap<String, Integer>();

        for (String promoCode: promoCodes) {
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(promoCode);
            actualPromoAndOrder.put(promoCode, order++);
        }

        actualPromoAndOrder = extractPromotionsOrderFromWalletPage();
        Assert.assertTrue("SUPC & PromoCode order not correct in Wallet",
                actualPromoAndOrder.get(promoCodes.get(0)) == expectedPromoAndOrder.get(promoCodes.get(0)));
    }

    /**
     * Extract promotions from Wallet page
     */
    public static HashMap<String, Integer> extractPromotionsOrderFromWalletPage() {

        Wait.forPageReady();
        Integer order = 1;
        List<WebElement> promoList = Elements.findElements("oc_my_wallet.promo_list");
        HashMap<String, Integer> actualPromoAndOrder = new HashMap<String, Integer>();

        for (WebElement promo: promoList) {
            String promoCode = promo.getText();
            actualPromoAndOrder.put(promoCode, order++);
        }
        return actualPromoAndOrder;
    }

    /**
     * Find a valid offer and add it in wallet page
     *
     * @param numOffers number of offers to be added
     */
    public void AddValidOfferInWallet(int numOffers) throws Throwable {

      //  ArrayList<String> promoList= new ArrayList<String>();
        OCWallet.promoCodes = promotionService.getWalletEligiblePromoCodesByCount(numOffers, false);
        OCWallet.totalOffers = numOffers;

       /* if (OCWallet.promoCodes.size() != numOffers) {
            logger.info("Insufficient global promo codes found for the environment");
            logger.info("Using SUPCs instead");
            promoList = Wallet.generateSUPC(numOffers);
        } else {
            logger.info("Global promo codes found for the environment");
            for (int i = 0; i < numOffers; i++) {
                OCWallet.promoCodes = promotionService.getWalletEligibleSUPCPromoCodesByCount(numOffers);
                promoList.add(OCWallet.promoCodes.get(i).get("promoCode"));
            }
        }*/

        for (int i = 0; i < numOffers; i++) {
            Wait.forPageReady();
            myAccountSteps.iClickOnAddOfferOnWalletPage();
           // Wallet.addValidOffer(promoList.get(i));
            if (OCWallet.promoCodes.get(i).get("promoCode") != null) {
                Wallet.addValidOffer(OCWallet.promoCodes.get(i).get("promoCode"));
                if (Elements.elementPresent("oc_my_wallet.add_promo_error")) {
                    logger.info("Error while applying global promo code: " + OCWallet.promoCodes.get(i).get("promoCode"));
                    logger.info("Using SUPC instead");
                    // promoList.add(i, Wallet.generateSUPC(1).get(0).toString());
                    List<Map<String, String>> supc = promotionService.getWalletEligiblePromoCodesByCount(1, true);
                    OCWallet.promoCodes.add(i, supc.get(0));
                    Wallet.addValidOffer(OCWallet.promoCodes.get(i).get("SUPC"));
                }
            } else {
                List<Map<String, String>> supc = promotionService.getWalletEligiblePromoCodesByCount(1, false);
                OCWallet.promoCodes.add(i, supc.get(1));
                Wallet.addValidOffer(OCWallet.promoCodes.get(i).get("SUPC"));
            }
        }
       // for (int i = 0; i < numOffers; i++) {
          //  OCWallet.promoCodes.get(i).put("promoCode", promoList.get(i));
       // }
    }

    /**
     * Find Organic Registration SUPC and add it in wallet page
     *
     * @param numOffers number of offers to be added
     */
    public void AddOrganicSUPCInWallet(int numOffers) throws Throwable {

        ArrayList<String> promoList= new ArrayList<String>();
        OCWallet.totalOffers = numOffers;
        promoList = Wallet.generateOrganicSUPC(numOffers);

        for (int i = 0; i < numOffers; i++) {
            Wait.forPageReady();
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(promoList.get(i));
            if (Elements.elementPresent("oc_my_wallet.add_promo_error")) {
                Assert.fail("Error while applying SUPC promo code: " + promoList.get(i));
            }
        }
    }



}
