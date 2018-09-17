package com.macys.sdt.projects.Customer.CreditSystemConversion.utils;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ndevanur on 27-Jul-17.
 */
public class CreditAppService {

    public static final String GET_CREDIT_CARD_DETAILS = "/customer/v2/users/";
    public static final String GET_schedulePaymentDetails = "/customer/v2/credit/";
    public static String app_name = null;

    public static String getAppName(){
        if(EnvironmentDetails.isZeus())
            app_name="customer";
        else
            app_name="MSPCUSTOMER";
        return app_name;
    }

    public static ArrayList<HashMap> getRegisteredCardDetails(String user_id, String user_token) {
        HashMap<String, String> header = new HashMap<>();

        ArrayList<HashMap> creditCardList = new ArrayList();

        header.put("X-Macys-ClientId", "ShopApp");
        header.put("X-Macys-SecurityToken", user_token);
        header.put("X-Macys-RequestId", "45");

        String endPoint = getServiceURL();
        String queryParam = "?acctSummaryIndicator=F&pageName=Gateway";
        String request = endPoint + user_id + "/registeredCards" + queryParam;
        Response response = RESTOperations.doGET(request, header);
        String finalJsonStr = response.readEntity(String.class);
        JSONObject rootJson = new JSONObject(finalJsonStr);
        JSONObject userAccountDetails = rootJson.getJSONObject("userAccountDetails");
        JSONObject userAccount = userAccountDetails.getJSONObject("userAccount");
        JSONArray registeredCards = userAccount.getJSONArray("registeredCards");
        for (int cardNum = 0; cardNum < registeredCards.length(); cardNum++) {
            HashMap<String, String> creditCardDetails = new HashMap<>();
            JSONObject creditSummary = registeredCards.getJSONObject(cardNum).getJSONObject("creditSummary");
            creditCardDetails.put("payment_due_date_value", creditSummary.has("paymentDueDate") ? creditSummary.get("paymentDueDate").toString() : "Not available");
            creditCardDetails.put("current_balance_value", creditSummary.has("currAcctBalance") ? creditSummary.get("currAcctBalance").toString() : "Not available");
            creditCardDetails.put("available_credit_value", creditSummary.has("availableCredit") ? creditSummary.get("availableCredit").toString() : "Not available");
            creditCardDetails.put("last_statement_balance_value",
                    creditSummary.has("lastStatementBalance") ? creditSummary.get("lastStatementBalance").toString() : "Not available");
            creditCardDetails.put("credit_limit_value", creditSummary.has("creditLimit") ? creditSummary.get("creditLimit").toString() : "Not available");
            creditCardDetails.put("minimum_payment_due_value", creditSummary.has("minPaymentDue") ? creditSummary.get("minPaymentDue").toString() : "Not available");
            String cardId = registeredCards.getJSONObject(cardNum).get("cardId").toString();
            creditCardDetails.put("cardNumber", cardId);
            String defaultAcctFlag = registeredCards.getJSONObject(cardNum).get("defaultAcctFlag").toString();
            creditCardDetails.put("defaultAcctFlag", defaultAcctFlag);
            String roleCode = registeredCards.getJSONObject(cardNum).get("roleCode").toString();
            creditCardDetails.put("roleCode", roleCode);
            String cardType = registeredCards.getJSONObject(cardNum).get("cardType").toString();
            creditCardDetails.put("cardType", cardType);
            String cardNumber = registeredCards.getJSONObject(cardNum).get("cardNumber").toString();
            creditCardDetails.put("cardNumber", cardNumber);
            creditCardList.add(creditCardDetails);
        }
        return creditCardList;
    }

    public static void validateCreditCardInfo(List<HashMap> apiCreditElementList, String pageName, List<String> uiElementList) {
        String actualValue, expectedValue;
        String ui_Card_Id_Text = null;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;
        ui_Card_Id_Text = Elements.findElement(pageName + ".card_id").getText();

        matcher = pattern.matcher(ui_Card_Id_Text);
        while (matcher.find()) {
            ui_Card_Id_Text = matcher.group();
        }
        final String uiCard = ui_Card_Id_Text;
        HashMap<String, String> tempMap = new HashMap();
        if (apiCreditElementList.stream().anyMatch(s -> ((s.get("cardNumber").toString()).equalsIgnoreCase(uiCard))))
            tempMap = apiCreditElementList.stream().filter(s -> ((s.get("cardNumber").toString()).equalsIgnoreCase(uiCard))).findFirst().get();
        for (String elementKey : uiElementList) {
            switch (elementKey) {
                case "current_balance_value":
                case "minimum_payment_due_value":
                case "available_credit_value":
                case "credit_limit_value":
                case "last_statement_balance_value":
                    expectedValue = CustomerUtils.convertToCurrencyString(tempMap.get(elementKey));
                    break;
                case "payment_due_date_value":
                    expectedValue = CustomerUtils.convertDateFormat(tempMap.get(elementKey), "yyyy-MM-dd", "MMM dd, yyyy");
                    break;
                default:
                    expectedValue = tempMap.get(elementKey);
                    break;
            }
            actualValue = Elements.findElement(pageName + "." + elementKey).getText();
            Assert.assertTrue(" The value of UI " + elementKey + " is not matching. UI for the Card Number : " + tempMap.get("cardNumber"),actualValue.contains(expectedValue));
        }
    }

    /**
     * Get the schedule payment details from API for the particular card
     * and returns as ArrayList format
     * @param  "user_id" and "card_number" to get the details
     * @throws Throwable if any exception occurs
     */
    public  static HashMap<String, String> getSchedulePaymentDetails(String userId, String card_number ) throws Throwable{
        HashMap<String, String> header = new HashMap<>();
        HashMap<String, String> paymentDetails = new HashMap<>();
        header.put("X-Macys-ClientId", "ShopApp");
        header.put("X-Macys-RequestId", "45");

        String request = "http://" + EnvironmentDetails.otherApp(getAppName()).ipAddress + ":8080/api" + GET_schedulePaymentDetails + userId + "/registeredcards/scheduledpayments";
        Response response = RESTOperations.doGET(request, header);
        String finalJsonStr = response.readEntity(String.class);
        JSONObject rootJson = new JSONObject(finalJsonStr);
        JSONObject userScheduledPayments = rootJson.getJSONObject("userScheduledPayments");
        JSONArray schedulePayment = userScheduledPayments.getJSONArray("scheduledPayments");

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;

        matcher = pattern.matcher(card_number);
        while (matcher.find()) {
            card_number = matcher.group();
        }

        for (int temp = 0; temp < schedulePayment.length(); temp++) {
            JSONObject temp_object = schedulePayment.getJSONObject(temp);
            if(temp_object.get("cardNumber").toString().equals(card_number)) {
                paymentDetails.put("cardNumber", temp_object.get("cardNumber").toString());
                paymentDetails.put("paymentEligibilityFlag", temp_object.get("paymentEligibilityFlag").toString());
                if (paymentDetails.get("paymentEligibilityFlag").equals("N"))
                    paymentDetails.put("eligibilityReasonCode", temp_object.get("eligibilityReasonCode").toString());
                else
                    paymentDetails.put("paymentSourceAccountNbr", temp_object.get("paymentSourceAccountNbr").toString());

                paymentDetails.put("paymentSourceNickname", temp_object.get("paymentSourceNickname").toString());
				if (temp_object.has("scheduledPaymentAmt"))
					paymentDetails.put("scheduledPaymentAmt", temp_object.get("scheduledPaymentAmt").toString());
                paymentDetails.put("scheduledPaymentDate", temp_object.get("scheduledPaymentDate").toString());
                paymentDetails.put("scheduledPaymentInd", temp_object.get("scheduledPaymentInd").toString());

                if (temp_object.has("autoPaymentDesc"))
                    paymentDetails.put("autoPaymentDesc", temp_object.get("autoPaymentDesc").toString());

                paymentDetails.put("maxPaymentAmt", temp_object.get("maxPaymentAmt").toString());
                break;
            }
        }
        return paymentDetails;
    }

    private static String getServiceURL() {
        return "http://" + EnvironmentDetails.otherApp(getAppName()).ipAddress + ":8080/api" + GET_CREDIT_CARD_DETAILS;
    }
}
