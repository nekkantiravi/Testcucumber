package com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.db.models;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    public Statement statement;
    public Connection connection;
    public JSONObject queries;

    /**
     * Method to return orderNote from DB for a given order number
     * @return orderNote
     **/
    public List getPrepareOrderNoteDetails(String orderNumber) throws Throwable {
        setupConnection();
        List orderNoteVal = new ArrayList();
        queries = Utils.getSqlQueries();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("order_service").getString("prepare_order_details").toString().replaceFirst("'\\?'", "'" + orderNumber + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String xmlData = resultSet.getString("XML_DATA");
                if (xmlData.contains("<orderNote>"))
                    orderNoteVal.add(xmlData.substring(xmlData.indexOf("<orderNote>") + 11, xmlData.indexOf("</orderNote>")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderNoteVal;
    }

    /**
     * Method to return adjusted Base fee value from DB for a given order number
     * @return adjusted base fee
     **/
    public String getAdjustedBaseFee(String orderNumber) throws Throwable {
        setupConnection();
        String adjuestedBaseFee = "";
        queries = Utils.getSqlQueries();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("order_service").getString("adjusted_base_fee").toString().replaceFirst("'\\?'", "'" + orderNumber + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                adjuestedBaseFee = resultSet.getString("ADJUSTED_BASE_FEE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adjuestedBaseFee;
    }

    /**
     * Method to return Base fee value from DB for a given order number
     * @return base fee
     **/
    public String getBaseFee(String orderNumber) throws Throwable {
        setupConnection();
        String baseFee = "";
        queries = Utils.getSqlQueries();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("order_service").getString("adjusted_base_fee").toString().replaceFirst("'\\?'", "'" + orderNumber + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                baseFee = resultSet.getString("ADJUSTED_BASE_FEE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return baseFee;
    }

    /**
     * Method to return promocode from DB for a given order number
     * @return promocode
     **/
    public String getOrderPromocode(String orderNumber) throws Throwable {
        setupConnection();
        String promocode = "";
        queries = Utils.getSqlQueries();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("order_service").getString("order_promocode").toString().replaceFirst("'\\?'", "'" + orderNumber + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                promocode = resultSet.getString("ATTR_VALUE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promocode;
    }

    /**
     * Method to return order discount description from DB for a given order number
     * @return order discount description
     **/
    public List getOrderDiscountDesc(String orderNumber) throws Throwable {
        setupConnection();
        List orderDiscountDesc = new ArrayList();
        queries = Utils.getSqlQueries();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("order_service").getString("order_discount_description").toString().replaceFirst("'\\?'", "'" + orderNumber + "'"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDiscountDesc.add(resultSet.getString("ORDER_DISC_DESC"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDiscountDesc;
    }

    /*
        To setup DB connection
     */
    private void setupConnection() {
        if (statement == null) {
            try {
                connection = DBUtils.setupDBConnection();
                statement = connection.createStatement();
            } catch (Exception e) {
                System.out.println("Error occurs while creating database connection" + e.getMessage());
            }
        }
    }
}
