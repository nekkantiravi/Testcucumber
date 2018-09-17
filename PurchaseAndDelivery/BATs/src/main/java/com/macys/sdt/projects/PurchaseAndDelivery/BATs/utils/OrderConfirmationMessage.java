package com.macys.sdt.projects.PurchaseAndDelivery.BATs.utils;


import com.macys.sdt.framework.utils.db.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OrderConfirmationMessage {
    public static final String TABLE_NAME = "order_confirmation_message";

    public static String getOrderConfirmationMessage(String orderNumber) {

        ResultSet result;
        String query = String.format("SELECT status FROM %s WHERE order_number='%s'", TABLE_NAME, orderNumber);
        String status = null;
        try {
            Statement statement = DBUtils.setupDBConnection().createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                status = result.getString("status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return status;
    }
}