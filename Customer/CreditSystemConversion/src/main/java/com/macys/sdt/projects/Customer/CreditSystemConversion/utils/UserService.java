package com.macys.sdt.projects.Customer.CreditSystemConversion.utils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import org.json.JSONObject;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    public String updateUserCreditCardTableForWallet(String cookieUserID) throws Throwable {
        String cardStatus = null;
        Connection c = new DBConnection().createConnection();
        JSONObject sql = Utils.getSqlQueries();
        String sqlone =
                sql.getJSONObject("creditApp_MyWallet").getString("update_User_creditcard_MyWallet").toString().replace("?", cookieUserID);
        try {
            PreparedStatement p = c.prepareStatement(sqlone);
            c.prepareStatement(sqlone).executeUpdate();
        } catch (Exception e) {
            System.out.println(e+  "Checking");
        }
        /**
         *   getCardStatus method will return the CardStatus from the table user_credit_card
         */
        cardStatus = getCardStatus(cookieUserID);
        Assert.assertTrue("Card Status column is NOT updated with LS in Database  ", cardStatus.equals("LS"));
        return cardStatus;
    }



    public String getCardStatus(String cookieUserID) throws Throwable {

        /**
         *   getCardStatus method will return the CardStatus from the table user_credit_card
         */

        String cardStatus = null;
        JSONObject sql = Utils.getSqlQueries();
        Connection c = new DBConnection().createConnection();
        String selectsqlone =
                sql.getJSONObject("creditApp_MyWallet").getString("select_User_creditcard_MyWallet");
        try {
            PreparedStatement p1 = c.prepareStatement(selectsqlone.replace("?", cookieUserID));
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                cardStatus = rs1.getString("card_status");
            }
        } catch (Exception e) {
            System.out.println(e+  "Checking");
        }
        return cardStatus;
    }



    public int getRowCountWallet(String cookieUserID) throws Throwable {
        int rowcount = 0;
        JSONObject sql = Utils.getSqlQueries();
        Connection c = new DBConnection().createConnection();
        String selectsqlone =
                sql.getJSONObject("creditApp_MyWallet").getString("rowCount_Customer_Offer");
        try {
            PreparedStatement p1 = c.prepareStatement(selectsqlone.replace("?", cookieUserID));
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                rowcount = rs1.getInt("rowCount");
            }
        } catch (Exception e) {
            System.out.println(e+  "Checking");
        }
        return rowcount;
    }
}

