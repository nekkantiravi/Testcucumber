package com.macys.sdt.projects.Selection.BeautyBox.utils.website;

import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class GenericUtilsBeauty extends StepUtils {


    public static void setGUIDCookie(String GUIDcookie) {
        Cookies.addCookie("macys_online_guid", GUIDcookie);
        Cookies.printCookie("macys_online_guid");

    }


    public static String getGUIDCookie() {
        String s = Cookies.getCookieValue("macys_online_guid");
        Cookies.printCookie("macys_online_guid");
        return s;
    }


    public static String getUIDCookie() {
        String strUID = Cookies.getCookieValue("macys_online_uid");
        Cookies.printCookie("macys_online_uid");
        return strUID;
    }


    public static void setSegment(String segment, String segment2) {
        Cookies.printCookie("SEGMENT");
        Cookies.changeDomain(".m2qa1.qa11codemacys.fds.com");
        Cookies.editCookie("SEGMENT", segment2, segment);
        Cookies.printCookie("SEGMENT");

    }

    public static void registerCookies(String segment, String segment2) {

        Cookies.resetDomain();
        System.out.println("cookies values" + segment + "egsrgzdrhg" + segment2);
        Cookies.addCookie("macys_online_uid", "2158695555");
        Cookies.addCookie("macys_online_guid", "1700d5b3-7080-459a-bab5-b998bc0e47f7");
        Cookies.printCookie("macys_online_uid");
        Cookies.addCookie("SNSGCs", segment);
        Cookies.printCookie("SNSGCs");
        Cookies.addCookie("signedIn", "1");
        Cookies.addCookie("secure_user_token", segment2);
        Cookies.printCookie("secure_user_token");


    }


    public static Map getCreditCardDetails(String cardtype){
        HashMap<String, String> creditCardDetails = new HashMap<>();
        String env;
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("beauty_credit_card.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(cardtype);
            creditCardDetails.put("card_type", json3.get("card_type").toString());
            creditCardDetails.put("card_number", json3.get("card_number").toString());
            creditCardDetails.put("expiration_date",json3.get("expiration_date").toString());
            creditCardDetails.put("expiration_year",json3.get("expiration_year").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return creditCardDetails;
    }


    public static Map getNewCreditCardDetails(String cardtype2){
        HashMap<String, String> creditCardDetailsForNew = new HashMap<>();
        String env;
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("beauty_credit_card.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(cardtype2);
            creditCardDetailsForNew.put("card_type", json3.get("card_type").toString());
            creditCardDetailsForNew.put("card_number", json3.get("card_number").toString());
            creditCardDetailsForNew.put("expiration_date",json3.get("expiration_date").toString());
            creditCardDetailsForNew.put("expiration_year",json3.get("expiration_year").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return creditCardDetailsForNew;
    }


    public static Map getAddressDetailsForSubscription(String shippingaddress){
        HashMap<String, String> subscriptionAddressDetails = new HashMap<>();
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("beauty_subscription_address.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(shippingaddress);
            subscriptionAddressDetails.put("first_name",json3.get("first_name").toString());
            subscriptionAddressDetails.put("last_name",json3.get("last_name").toString());
            subscriptionAddressDetails.put("address_line1",json3.get("address_line1").toString());
            subscriptionAddressDetails.put("sub_city",json3.get("sub_city").toString());
            subscriptionAddressDetails.put("sub_state",json3.get("sub_state").toString());
            subscriptionAddressDetails.put("zip_code",json3.get("zip_code").toString());
            subscriptionAddressDetails.put("phone_no",json3.get("phone_no").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return subscriptionAddressDetails;
    }


        public static int use_date_time() {
        String strDate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String[] date1 = strDate.split("/");
        Integer currentDate = Integer.parseInt(date1[1]);
        return currentDate.intValue();

    }

    public static Map getAddNewShippingAddress(String shippingAddress2){
        HashMap<String, String> subscriptionAddNewAddressDetails = new HashMap<>();
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("beauty_subscription_address.json"))));
            JSONObject json2 = json.getJSONObject(StepUtils.macys() ? "macys" : "bloomingdales");
            JSONObject json3 = json2.getJSONObject(shippingAddress2);
            subscriptionAddNewAddressDetails.put("first_name",json3.get("first_name").toString());
            subscriptionAddNewAddressDetails.put("last_name",json3.get("last_name").toString());
            subscriptionAddNewAddressDetails.put("address_line1",json3.get("address_line1").toString());
            subscriptionAddNewAddressDetails.put("sub_city",json3.get("sub_city").toString());
            subscriptionAddNewAddressDetails.put("sub_state",json3.get("sub_state").toString());
            subscriptionAddNewAddressDetails.put("zip_code",json3.get("zip_code").toString());
            subscriptionAddNewAddressDetails.put("phone_no",json3.get("phone_no").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return subscriptionAddNewAddressDetails;
    }

    public static Calendar getCancelledByDate(Integer cancelBy) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, cancelBy);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar;
    }

    /**
     * @param currentDate
     * @param date
     * @return the value 0 if the time represented by the argument is equal to the time represented by this Calendar;
     * a value less than 0 if the time of this Calendar is before the time represented by the argument; and
     * a value greater than 0 if the time of this Calendar is after the time represented by the argument.
     * <p>
     * the value 0 if the time represented by date is same as time represented by currentDate
     * the value less than 0 if the currentDate is before the date
     * the value greater than 0 if the currentDate is after the date
     */
    public static int compareDates(Calendar currentDate, Calendar date) {
        return (currentDate.compareTo(date));
    }

    public static Calendar getSubscriptionEndDate(Calendar date, int noOfMonths) {

        if (noOfMonths > 0) {
            date = incrementMonthBy(date,noOfMonths);
        }
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.AM_PM, Calendar.AM);
        date.set(Calendar.DATE, date.getActualMaximum(Calendar.DAY_OF_MONTH));
        return date;
    }

    public static Calendar incrementMonthBy(Calendar calendar, int numberOfMonths) {

        calendar.add(Calendar.MONTH, numberOfMonths);
        setMinimumDayOfMonth(calendar);
        return calendar;


    }

    /**
     * @param calendar
     * @return Calendar
     * sets the given calendar object to the day of the month set to the minimum day of the month.
     */
    public static Calendar setMinimumDayOfMonth(Calendar calendar) {

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

}