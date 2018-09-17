package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.utils.db;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import org.json.JSONObject;
import org.junit.Assert;

import java.sql.*;

public class AddressService {

    private Connection connection;
    private JSONObject queries;

    public String getAddressLogByAttribute(String user_id, boolean city_name, boolean state_name, boolean zip_code) throws Throwable {

        String city, state, zipcode, dpvCode;
        setupConnection();
        queries = Utils.getSqlQueries();
        System.out.println("USER_ID:" + user_id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("Address_service").
                    getString("Tax_address_details").replaceFirst("'\\?'", "'" + user_id + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String xmlData = resultSet.getString("XML_DATA");
                dpvCode = xmlData.substring(xmlData.indexOf("<dpv>") + 5, xmlData.indexOf("</dpv>"));
                Assert.assertEquals("Auto correction will not happen", "Y", dpvCode);
                System.out.println("DPV code returned from DB:" + dpvCode);
                if (city_name) {
                    city = xmlData.substring(xmlData.indexOf("<city>") + 6, xmlData.indexOf("</city>"));
                    System.out.println("City returned from DB is:" + city);
                    return city;
                } else if (state_name) {
                    state = xmlData.substring(xmlData.indexOf("<state>") + 7, xmlData.indexOf("</state>"));
                    System.out.println("state returned from DB is:" + state);
                    return state;
                } else if (zip_code) {
                    zipcode = xmlData.substring(xmlData.indexOf("<postalCodeBase>") + 16,
                            xmlData.indexOf("</postalCodeBase>"));
                    System.out.println("ZIP_code returned from DB is:" + zipcode);
                    return zipcode;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDPV(String user_id) throws Throwable {
        String dpvCode;
        setupConnection();
        queries = Utils.getSqlQueries();
        System.out.println("USER_ID:" + user_id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("Address_service").
                    getString("Tax_address_details").replaceFirst("'\\?'", "'" + user_id + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String xmlData = resultSet.getString("XML_DATA");
                dpvCode = xmlData.substring(xmlData.indexOf("<dpv>") + 5, xmlData.indexOf("</dpv>"));
                return dpvCode;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setupConnection() {
        if (connection == null) {
            try {
                connection = DBUtils.setupDBConnection();
            } catch (Exception e) {
                System.out.println("Error occurs while creating database connection" + e.getMessage());
            }
        }
    }
}