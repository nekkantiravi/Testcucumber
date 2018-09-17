package com.macys.sdt.projects.Discovery.Saturn.resources.utils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import com.macys.sdt.framework.interactions.*;

/**
 * Created by M671871 on 6/2/2017.
 */
public class CommonUtils {

    public static Map getCredentials(String credentials){
        HashMap<String, String> loginCredentials = new HashMap<>();
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("credentials.json"))));
            JSONObject json_data = json.getJSONObject(credentials);
            loginCredentials.put("username",json_data.get("username").toString());
            loginCredentials.put("password",json_data.get("password").toString());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return loginCredentials;
    }

    public static LinkedHashMap getJsonKeyValue(String param,String fileName) throws IOException, ParseException {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(fileName+".json"))));
        map= toMap(json.getJSONObject(param));
        return map;
    }

    public static String getDate(String dateType) {
        // Get today as a Calendar
        Calendar today = Calendar.getInstance();
        if (dateType.equals("expiration date")) {
            // add 4 days
            today.add(Calendar.DATE, 4);
        }
        else
            today.add(Calendar.DATE, -2);
        // Make an SQL Date out of that
        java.sql.Date date = new java.sql.Date(today.getTimeInMillis());
        java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String requiredDate = df.format(date);
        return requiredDate;
    }

    public static String getJsonValue(String param,String fileName) throws IOException, ParseException {
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(fileName+".json"))));
        String jsonVal=  json.get(param).toString();
        return jsonVal;
    }

    public static LinkedHashMap<String, String> toMap(JSONObject object) throws JSONException {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value.toString());
        }
        return map;
    }

    public static String getSpecificDate(String param,String dateType,int days) throws java.text.ParseException {
        java.text.DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date specdate=df.parse(param);
        Calendar calDate=Calendar.getInstance();
        calDate.setTime(specdate);
        if (dateType.equals("expiration date")) {
            // add 4 days
            calDate.add(Calendar.DATE, days);
        }
        else
            calDate.add(Calendar.DATE, days);
        // Make an SQL Date out of that
        java.sql.Date date = new java.sql.Date(calDate.getTimeInMillis());
        df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String requiredDate = df.format(date);
        return requiredDate;
    }

    public static List<String> getSortedList(List dataList) {
        List<String> sortedDataList = new ArrayList<String>(dataList);
        sortedDataList.removeAll(Arrays.asList("", null));
        System.out.println(sortedDataList);
        Collections.sort(sortedDataList);
        return sortedDataList;
    }

    public static String getDateInFormat(String dateType,String format,int days) {
        // Get today as a Calendar
        Calendar today = Calendar.getInstance();
        if (dateType.equalsIgnoreCase("expiration date")) {
            // add 4 days
            today.add(Calendar.DATE, days);
        } else
            today.add(Calendar.DATE, days);
        // Make an SQL Date out of that
        java.sql.Date date = new java.sql.Date(today.getTimeInMillis());
        java.text.DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String requiredDate = df.format(date);
        return requiredDate;
    }

    public static boolean mcom()
    {
        String url= (String) Navigate.execJavascript("return $('div.logo_livesite').css('background-image')");
        return url.contains("mcom");
    }

    public static boolean bcom()
    {
        String url= (String) Navigate.execJavascript("return $('div.logo_livesite').css('background-image')");
        return url.contains("bcom");
    }

    public static String getTenantName() {
        String tenant;
        if (CommonUtils.bcom()) {
            tenant = "BCOM";
        } else {
            tenant = "MCOM";
        }
        return tenant;
    }

    public static String getRulesDataFromDB(String query, String dbName, String dbUrl, String dbUsername, String dbPassword) throws SQLException {
        String result = "";
        Statement stmt;
        String sql;
        ResultSet resultSet = null;
        try {
            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            if (CommonUtils.bcom()) {
                sql = String.format(query, "BCOM");
            } else {
                sql = String.format(query, "MCOM");
            }
            resultSet = stmt.executeQuery(sql);
            resultSet.next();
            result = resultSet.getString(1);
            System.out.println("rs::::" + result);
            System.out.println("query executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
        return result;
    }

    public static ArrayList getRuleIdsFromDB(String query, String dbName, String dbUrl, String dbUsername, String dbPassword) throws SQLException {
        Statement stmt;
        String sql;
        ArrayList<String> results = new ArrayList<>();
        try {
            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");
            stmt = connection.createStatement();
            if (CommonUtils.bcom()) {
                sql = String.format(query, "BCOM");
            } else {
                sql = String.format(query, "MCOM");
            }
            try(ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    results.add(rs.getString(1));
                }
            }
            results.get(1);
            System.out.println("rs::::" + results);
            System.out.print("query executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
        return results;
    }


//    Connecting to New Autocomplete override database and retriving data in result set format
    public static ResultSet getNewAutocompleteDataFromDB(String query, String dbName, String dbUrl, String username, String password){
        String queryString;
        ResultSet resultSet = null;
        Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, username, password);
        if (CommonUtils.bcom()) queryString = String.format(query, "BCOM");
        else queryString = String.format(query, "MCOM");
        try {
            resultSet = connection.createStatement().executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(e.getMessage());
        }
        return resultSet;
    }
}
