package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.utils.db;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.it.Ma;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;

public class Service {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static void softLockUser(String user_id, String email) {
        Connection con = DBUtils.setupDBConnection();
        String sql = Utils.getSqlQueries().getJSONObject("user_service").getString("softlock_user");
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }


    public static HashMap getShipAddId(String email) {
        HashMap<String, String> details = new HashMap<>();
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement
                    .executeQuery(queries.getJSONObject("user_service").get("get_address_added_to_user").toString()
                            .replace("?", email));
            while (rs.next()) {
                details.put(rs.getMetaData().getColumnName(1), rs.getString("ADDRESS_ID"));
                details.put(rs.getMetaData().getColumnName(2), rs.getString("ADDRESS_LINE_1"));
                details.put(rs.getMetaData().getColumnName(9), rs.getString("LAST_MODIFIED"));
                System.out.println(details);
            }
            statement.close();
        } catch (SQLException | JSONException e) {
            logger.error("SQL error " + e.getMessage());
        }

        return details;
    }


    public static void  updateShipAddTimeStamp(String address_id) {
        Connection con = DBUtils.setupDBConnection();
        String sql = Utils.getSqlQueries().getJSONObject("user_service").getString("update_ship_add_last_modified_timestamp");
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,address_id );
            preparedStatement.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }



    public static void setUserAsFraud(String user_id){
        Connection con = DBUtils.setupDBConnection();
        String sql = Utils.getSqlQueries().getJSONObject("user_service").getString("add_malicious_user");
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }


    public static boolean isMaliciousUser(String user_id){
        Boolean malicious_user=true;
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        String address_id= "";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queries.getJSONObject("user_service").get("get_malicious_user").toString().replace("?", user_id));
            while (rs.next()) {
                address_id = rs.getString("USER_ID");
                if (address_id.isEmpty())
                    malicious_user=false;
            }
            statement.close();
        } catch (SQLException | JSONException e) {
            logger.error("SQL error " + e.getMessage());
        }
        return malicious_user;
    }
}
