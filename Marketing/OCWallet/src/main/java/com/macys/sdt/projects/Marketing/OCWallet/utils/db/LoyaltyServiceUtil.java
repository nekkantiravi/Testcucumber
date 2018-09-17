package com.macys.sdt.projects.Marketing.OCWallet.utils.db;

import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.Date;

public class LoyaltyServiceUtil extends StepUtils {

    private Statement statement;
    public Date customDate;
    private static Connection connection;
    private static JSONObject queries;
    private static String ltyid;


    /**
     * Deletes Loyalty Number Associations from Users
     *
     * @param loyallistID is the Loyallist ID Number
     * @return null if no credit card stored in wallet
     */

    public void removeLoyallistIDAssociation(String loyallistID) throws SQLException {

        ResultSet resultSet1 = null;
        setupConnection();
        queries = Utils.getSqlQueries();
        ltyid = loyallistID.replaceAll("L", "");

        try {

            String deleteSQL = queries.getJSONObject("loyalty_service").getString("remove_loyallist_association").replaceFirst("'\\?'", "'" + ltyid + "'");
//            resultSet1 = statement.executeQuery(userCreditCardQuery);
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
//            preparedStatement.setInt(1, 5);
            // execute delete SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException | JSONException e) {
            connection.close();
//            Assert.fail("Unable delete loyalty ID from users");
        }

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
                System.out.println("Error occured while creating database connection" + e.getMessage());
            }
        }
    }

}