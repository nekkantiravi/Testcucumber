package com.macys.sdt.projects.Customer.CreditSystemConversion.utils;

import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CustomerUtils {

    public static Map getCityUserCredentials(String cardType){
        HashMap<String, String> credentials = new HashMap<>();
        String env;

        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("user_data.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(cardType);
            credentials.put("email",json3.get("email").toString());
            credentials.put("password",json3.get("password").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return credentials;
    }

    public static String getExpectedMessage(String message){
        String expected_message=null;
        String env;

        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("credit_app_messages.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            expected_message = json2.get(message).toString();
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return expected_message;
    }

    public static void updateUserNotification(String user_id){
        JSONObject sql = Utils.getSqlQueries();
        Connection c = new DBConnection().createConnection();
        String selectsqlone =
                sql.getJSONObject("user_service").getString("update_notification");
        try {
            PreparedStatement p1 = c.prepareStatement(selectsqlone.replace("?", user_id));
            int rs1 = p1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e+  e.getMessage());
        }
    }



    public static Map getCardDetails(String cardtype){
        HashMap<String, String> carddetails = new HashMap<>();
        String env;

        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("citi_credit_card.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(cardtype);
            carddetails.put("card_number", json3.get("card_number").toString());
            carddetails.put("card_name",json3.get("card_name").toString());
            carddetails.put("security_code",json3.get("security_code").toString());
            carddetails.put("card_ssn",json3.get("card_ssn").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return carddetails;
    }



    public static Map getCityApplyCarddetials(String cardType){
        HashMap<String, String> credentials = new HashMap<>();
        String env;

        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("user_data.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(cardType);
            credentials.put("firstname",json3.get("firstname").toString());
            credentials.put("lastname",json3.get("lastname").toString());
            credentials.put("email",json3.get("email").toString());
            credentials.put("address",json3.get("address").toString());
            credentials.put("city",json3.get("city").toString());
            credentials.put("state",json3.get("state").toString());
            credentials.put("ZIPcode",json3.get("ZIPcode").toString());
            credentials.put("phone",json3.get("phone").toString());
            credentials.put("phonetype",json3.get("phonetype").toString());
            credentials.put("rent",json3.get("rent").toString());
            credentials.put("residencestatus",json3.get("residencestatus").toString());
            credentials.put("annualincome",json3.get("annualincome").toString());
            credentials.put("SSN",json3.get("SSN").toString());
            credentials.put("DOB",json3.get("DOB").toString());

        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return credentials;
    }

    public static String convertToCurrencyString(String cardStringValue){

        String returnString;
        String tempString;

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        if(cardStringValue.contains("-")){
            tempString = cardStringValue.replace("-", "");
            returnString = nf.format(new BigDecimal(tempString));
            returnString = insertCharAt(returnString,'-',0);
            System.out.println("with : "+returnString);
        }
        else{
            returnString = nf.format(new BigDecimal(cardStringValue));
            System.out.println("without : " +returnString);
        }
        return returnString;
    }

    public static String insertCharAt(String st, char ch, int index){

        return st.substring(0, index)+ch+st.substring(index, st.length());
    }

    public static String convertDateFormat(String date, String inFormat, String outFormat){
        Date tempDate;
        SimpleDateFormat formatter;
        String returnDateString = null;

        try{
            tempDate = new SimpleDateFormat(inFormat).parse(date);
            System.out.println("tempDate value : " +date);

            formatter = new SimpleDateFormat (outFormat);
            returnDateString = formatter.format(tempDate);
            System.out.println(returnDateString);

        }catch(Exception e){
            System.out.println("Problem while parsing"+e.getMessage());
        }
        return returnDateString;
    }

}
