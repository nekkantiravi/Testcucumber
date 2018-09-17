package com.macys.sdt.projects.Marketing.OCWallet.utils.db;

import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WalletService extends StepUtils {

    private Statement statement;
    public Date customDate;
    private static Connection connection;
    private static JSONObject queries;
    private static Long id;
    private static String firstName;
    private static String lastName;
    private static String addressLine1;
    private static String addressLine2;
    private static String city;
    private static String state;
    private static String zipCode;
    private static String countryCode;
    private static String email;
    private static CreditCard.CardType cardType;
    private static String cardNumber;
    private static String attention;
    private static Long sequenceNumber;
    private static String middleName;
    private static String bestPhone;
    private static Boolean primaryFlag;
    private static String securityCode;
    private static double balance;
    private static String expiryMonth;
    private static String expiryMonthIndex;
    private static String expiryYear;
    private static HashMap<CreditCard, ProfileAddress> DBCardDetails;


    /**
     * Get credit card details stored in wallet
     *
     * @return null if no credit card stored in wallet
     */

    public ArrayList<CreditCard> getCardDetailsFromDB() throws SQLException {

        ResultSet resultSet1 = null, resultSet2 = null, resultSet3 = null;
        setupConnection();
        queries = Utils.getSqlQueries();
        email = TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail();
        ArrayList<CreditCard> dbCards = new ArrayList<>();
        CreditCard card = null;
        ProfileAddress address = null;

        try {

            String userCreditCardQuery = queries.getJSONObject("wallet_credit_card_details").getString("user_credit_card").replaceFirst("'\\?'", "'" + email + "'");
            resultSet1 = statement.executeQuery(userCreditCardQuery);

            while (resultSet1.next()) {

                String billing_address_id = null;
                String credit_card_id = resultSet1.getString("CREDIT_CARD_ID");
                String card_brand = resultSet1.getString("user_card_nickname");
                int len = card_brand.length();
                cardNumber = card_brand.substring(len - 4, len);
                String dbCardType = card_brand.substring(0,card_brand.length() - 4);
                dbCardType = dbCardType.replaceFirst("Bloomingdales","Bloomingdale's");
                dbCardType = dbCardType.replaceFirst("Macys","Macy's");
                cardType = CreditCard.CardType.fromString(dbCardType);

                String creditCardQuery = Utils.getSqlQueries().getJSONObject("wallet_credit_card_details").getString("credit_card").replaceFirst("'\\?'", "'" + credit_card_id + "'");
                Statement statement1 = connection.createStatement();
                resultSet2 = statement1.executeQuery(creditCardQuery);
                if (resultSet2.next()) {
                    billing_address_id = resultSet2.getString("billing_address_id");
                    firstName = resultSet2.getString("first_name");
                    lastName = resultSet2.getString("last_name");
                    email = resultSet2.getString("email_address");
                    expiryMonthIndex = resultSet2.getString("credit_card_exp_mo");

                    expiryMonth = Wallet.getExpMon(expiryMonthIndex);
                    expiryYear = resultSet2.getString("credit_card_exp_yr");
                }

                Statement statement2 = connection.createStatement();
                String addressDetailsQuery = Utils.getSqlQueries().getJSONObject("wallet_credit_card_details")
                        .getString("card_address_details").replaceFirst("'\\?'", "" + billing_address_id + "");
                resultSet3 = statement2.executeQuery(addressDetailsQuery);
                if (resultSet3.next()) {

                    addressLine1 = resultSet3.getString("address_line_1");
                    addressLine2 = resultSet3.getString("address_line_2");
                    city = resultSet3.getString("city");
                    state = resultSet3.getString("state_abbrev");
                    zipCode = resultSet3.getString("postal_code");
                    countryCode = resultSet3.getString("country_code");
                }

                card = new CreditCard(cardType, cardNumber, "", 0, expiryMonth, expiryMonthIndex, expiryYear);
                address = new ProfileAddress(id, attention, sequenceNumber, firstName, lastName,
                        middleName, addressLine1, addressLine2, city, state,
                        zipCode, countryCode, email, bestPhone, primaryFlag);
                card.setAddress(address);
                dbCards.add(card);

            }

        } catch (SQLException | JSONException e) {
            connection.close();
            Assert.fail("Unable to get credit card details from wallet");
        }
        return dbCards;

    }

    /**
     * Get default card type stored in wallet
     *
     * @return cardType name of the Default card
     */
    public String getDefaultCardFromDB() throws SQLException {
        ResultSet resultSet1 = null;
        String defaultCard=null;
        setupConnection();
        queries = Utils.getSqlQueries();
        email = TestUsers.getCustomer(null).getUser().getProfileAddress().getEmail();

        try {

            String userCreditCardQuery = queries.getJSONObject("wallet_credit_card_details").getString("user_credit_card").replaceFirst("'\\?'", "'" + email + "'");
            resultSet1 = statement.executeQuery(userCreditCardQuery);
            while (resultSet1.next()) {

                String defaultCredit = resultSet1.getString("DFLT_USER_CARD");
                if (defaultCredit.equals("Y")) {
                    String cardBrand = resultSet1.getString("USER_CARD_NICKNAME");
                    defaultCard = cardBrand.substring(0, cardBrand.length() - 4);
                    defaultCard = defaultCard.replaceFirst("Bloomingdales", "Bloomingdale's");
                    defaultCard = defaultCard.replaceFirst("Macys", "Macy's");
                    break;
                }
            }

            } catch(SQLException | JSONException e){
                connection.close();
                Assert.fail("Unable to get credit card details from wallet");
            }
            return defaultCard;
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