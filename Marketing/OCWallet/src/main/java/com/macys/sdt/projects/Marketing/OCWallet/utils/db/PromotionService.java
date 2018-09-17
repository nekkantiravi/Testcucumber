package com.macys.sdt.projects.Marketing.OCWallet.utils.db;

import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import org.json.JSONException;
import org.junit.Assert;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class PromotionService extends StepUtils {

    private Date customDate;
    private static Connection connection;
    private Statement statement;

    /**
     * To get single wallet eligible promo code from database
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public String getWalletEligiblePromoCode() {
        String walletEligiblePromoCode = null;
        setupConnection();
        if (customDate == null) {
            customDate = DBUtils.getCustomDate();
        }
        try {
            String walletPromoQuery = Utils.getSqlQueries().get("wallet_promo_global").toString().
                    replaceAll(">= \\?", ">= '" + customDate.toString() + "'").
                    replaceAll("<= \\?", "<= '" + customDate.toString() + "'");
            ResultSet resultSet = statement.executeQuery(walletPromoQuery);
            while (resultSet.next()) {
                if (!resultSet.getString("PROMOTION_CODE").contains("_") && !resultSet.getString("PROMOTION_CODE").contains("SHINE")) {
                    walletEligiblePromoCode = resultSet.getString("PROMOTION_CODE");
                    break;
                }
            }
        } catch (SQLException | JSONException e) {
            Assert.fail("Unable to add promo code to wallet: " + e);
        }
        return walletEligiblePromoCode;
    }


    /**
     * To get single wallet eligible Omnichannel promo code from database
     *
     * @return String if Omnichannel promo code not available in DB.
     */
    public String getOmnichannelPromoCode() {
        String omniChannelEligiblePromoCode = null;
        setupConnection();
//        if (customDate == null) {
//            customDate = DBUtils.getCustomDate();
//        }
        try {
            String walletPromoQuery = Utils.getSqlQueries().get("wallet_omnichannel_promo").toString();
                    /*
                    .replaceAll(">= \\?", ">= '" + customDate.toString() + "'").
                    replaceAll("<= \\?", "<= '" + customDate.toString() + "'");*/
            ResultSet resultSet = statement.executeQuery(walletPromoQuery);
            while (resultSet.next()) {
                if (!resultSet.getString("PROMOTION_CODE").contains("_") && !resultSet.getString("PROMOTION_CODE").contains("SHINE")) {
                    omniChannelEligiblePromoCode = resultSet.getString("PROMOTION_CODE");
                    break;
                }
            }
        } catch (SQLException | JSONException e) {
            Assert.fail("Unable to add promo code to wallet: " + e);
        }
        return omniChannelEligiblePromoCode;
    }

    /**
     * To get wallet eligible promo codes from database
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public ArrayList<String> getWalletEligiblePromoCodes() {

        String walletEligiblePromoCode;
        ArrayList<String> walletEligiblePromoCodes = new ArrayList<>();
        setupConnection();

        if (customDate == null) {
            customDate = DBUtils.getCustomDate();
        }
        try {
            String walletPromoQuery = Utils.getSqlQueries().get("wallet_promo_global").toString().
                    replaceAll(">= \\?", ">= '" + customDate.toString() + "'").
                    replaceAll("<= \\?", "<= '" + customDate.toString() + "'");
            ResultSet resultSet = statement.executeQuery(walletPromoQuery);
            while (resultSet.next()) {
                if (!resultSet.getString("PROMOTION_CODE").contains("_") && !resultSet.getString("PROMOTION_CODE").contains("SHINE")) {
                    walletEligiblePromoCode = resultSet.getString("PROMOTION_CODE");
                    walletEligiblePromoCodes.add(walletEligiblePromoCode);
                }
            }
        } catch (SQLException | JSONException e) {
            Assert.fail("Unable to add promo code to wallet: " + e);
        }
        return walletEligiblePromoCodes;
    }

    /**
     * To get wallet eligible promo codes from database
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public String getWalletEligibleExpiredPromoCode() {

        String promoCode = null;
        setupConnection();

        if (customDate == null) {
            customDate = DBUtils.getCustomDate();
        }
        try {
            String walletPromoQuery = Utils.getSqlQueries().get("wallet_promo_expired").toString().
                    replaceAll("<= \\?", "<= '" + customDate.toString() + "'");
            ResultSet resultSet = statement.executeQuery(walletPromoQuery);
            while (resultSet.next()) {
                if (!resultSet.getString("PROMOTION_CODE").contains("_") && !resultSet.getString("PROMOTION_CODE").contains("SHINE")) {
                    promoCode = resultSet.getString("PROMOTION_CODE");
                }
            }
        } catch (SQLException | JSONException e) {
            Assert.fail("Unable to add promo code to wallet: " + e);
        }
        return promoCode;
    }


    /**
     * To get wallet eligible promo codes with its details by count from database
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public List<Map<String, String>> getWalletEligiblePromoCodesByCount(int promoCount, boolean isSUPC) throws Throwable {
        List<Map<String, String>> walletEligiblePromoCodes = new ArrayList();
        String walletPromoQuery;
        setupConnection();
//        try {
        if (!isSUPC) {
            walletPromoQuery = Utils.getSqlQueries().get("wallet_promos_by_count").toString().
                    replaceAll("\\?", String.valueOf(promoCount));
        } else {
            walletPromoQuery = Utils.getSqlQueries().get("wallet_supc_promos_by_count").toString().
                    replaceAll("\\?", String.valueOf(promoCount));
        }
        boolean connStatus = connection.isClosed();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(walletPromoQuery);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        while (resultSet.next()) {
            Map<String, String> walletEligiblePromoCode = new HashMap();
            if ((!resultSet.getString("PROMOTION_CODE").contains("_")) || isSUPC) {

                walletEligiblePromoCode.put("promoCode", resultSet.getString("PROMOTION_CODE"));
                if (isSUPC) {
                    walletEligiblePromoCode.put("isSUPC", "Y");
                    walletEligiblePromoCode.put("SUPC", Wallet.generateSUPC(resultSet.getString("PROMOTION_CODE")));
                } else {
                    walletEligiblePromoCode.put("isSUPC", "N");
                    walletEligiblePromoCode.put("SUPC", null);
                }
                walletEligiblePromoCode.put("effectiveDate", sdf.format(resultSet.getDate("PROMOTION_EFF_DATE")));
                walletEligiblePromoCode.put("expirationDate", sdf.format(resultSet.getDate("PROMOTION_EXP_DATE")));
                walletEligiblePromoCode.put("barCode", resultSet.getString("OFFER_BARCODE") == null ? "" : resultSet.getString("OFFER_BARCODE"));
                walletEligiblePromoCode.put("storeCode", resultSet.getString("STORE_OFFER_CODE") == null ? "" : resultSet.getString("STORE_OFFER_CODE"));
                String promotionID = resultSet.getString("PROMOTION_ID");
                walletEligiblePromoCode.put("promotionID", promotionID);
                ResultSet resultSet2, resultSet3, resultSet4;
                String walletPromoHeadingQuery = Utils.getSqlQueries().get("wallet_promo_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet2 = statement2.executeQuery(walletPromoHeadingQuery);
                while (resultSet2 != null && !resultSet2.isClosed() && resultSet2.next()) {
                    walletEligiblePromoCode.put("promoHeading", resultSet2.getString("ATTR_VALUE") == null ? "" : resultSet2.getString("ATTR_VALUE").toUpperCase());
                }

                String walletPromoSubHeadingQuery = Utils.getSqlQueries().get("wallet_promo_sub_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet3 = statement3.executeQuery(walletPromoSubHeadingQuery);
                //since promotion sub heading is not mandatory
                walletEligiblePromoCode.put("promoSubHeading", "");
                while (resultSet3 != null && !resultSet3.isClosed() && resultSet3.next()) {
                    walletEligiblePromoCode.put("promoSubHeading", resultSet3.getString("ATTR_VALUE") == null ? "" : resultSet3.getString("ATTR_VALUE").toUpperCase());
                    break;
                }
                String walletPromoDisclaimerQuery = Utils.getSqlQueries().get("wallet_promo_disclaimer_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet4 = statement4.executeQuery(walletPromoDisclaimerQuery);
                while (resultSet4 != null && !resultSet4.isClosed() && resultSet4.next()) {
                    walletEligiblePromoCode.put("legalDisclaimer", resultSet4.getString("LEGAL_DISCLAIMER"));
                    break;
                }
                walletEligiblePromoCodes.add(walletEligiblePromoCode);
            }
        }
//        } catch (SQLException | JSONException e) {
//            Assert.fail("Unable to fetch promo codes from DB" + e);
//        }

//        connection.close();
        return walletEligiblePromoCodes;
    }

    /**
     * To get wallet eligible promo codes with its details by count from database
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public List<Map<String, String>> getWalletEligibleSUPCPromoCodesByCount(int promoCount) throws Throwable {
        List<Map<String, String>> walletEligiblePromoCodes = new ArrayList();
        setupConnection();
        String walletPromoQuery = Utils.getSqlQueries().get("wallet_supc_promos_by_count").toString().
                replaceAll("\\?", String.valueOf(promoCount));
        boolean connStatus = connection.isClosed();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(walletPromoQuery);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        while (resultSet.next()) {
            Map<String, String> walletEligiblePromoCode = new HashMap();
            if (!resultSet.getString("PROMOTION_CODE").contains("_") && !resultSet.getString("PROMOTION_CODE").contains("SHINE")) {
                walletEligiblePromoCode.put("promoCode", resultSet.getString("PROMOTION_CODE"));
                walletEligiblePromoCode.put("effectiveDate", sdf.format(resultSet.getDate("PROMOTION_EFF_DATE")));
                walletEligiblePromoCode.put("expirationDate", sdf.format(resultSet.getDate("PROMOTION_EXP_DATE")));
                walletEligiblePromoCode.put("barCode", resultSet.getString("OFFER_BARCODE") == null ? "" : resultSet.getString("OFFER_BARCODE"));
                walletEligiblePromoCode.put("storeCode", resultSet.getString("STORE_OFFER_CODE") == null ? "" : resultSet.getString("STORE_OFFER_CODE"));
                String promotionID = resultSet.getString("PROMOTION_ID");
                walletEligiblePromoCode.put("promotionID", promotionID);
                ResultSet resultSet2, resultSet3, resultSet4;
                String walletPromoHeadingQuery = Utils.getSqlQueries().get("wallet_promo_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet2 = statement2.executeQuery(walletPromoHeadingQuery);
                while (resultSet2 != null && !resultSet2.isClosed() && resultSet2.next()) {
                    walletEligiblePromoCode.put("promoHeading", resultSet2.getString("ATTR_VALUE") == null ? "" : resultSet2.getString("ATTR_VALUE").toUpperCase());
                }

                String walletPromoSubHeadingQuery = Utils.getSqlQueries().get("wallet_promo_sub_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet3 = statement3.executeQuery(walletPromoSubHeadingQuery);
                //since promotion sub heading is not mandatory
                walletEligiblePromoCode.put("promoSubHeading", "");
                while (resultSet3 != null && !resultSet3.isClosed() && resultSet3.next()) {
                    walletEligiblePromoCode.put("promoSubHeading", resultSet3.getString("ATTR_VALUE") == null ? "" : resultSet3.getString("ATTR_VALUE").toUpperCase());
                    break;
                }
                String walletPromoDisclaimerQuery = Utils.getSqlQueries().get("wallet_promo_disclaimer_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet4 = statement4.executeQuery(walletPromoDisclaimerQuery);
                while (resultSet4 != null && !resultSet4.isClosed() && resultSet4.next()) {
                    walletEligiblePromoCode.put("legalDisclaimer", resultSet4.getString("LEGAL_DISCLAIMER"));
                    break;
                }
                walletEligiblePromoCodes.add(walletEligiblePromoCode);
            }
        }
        return walletEligiblePromoCodes;
    }

    /**
     * To update prmotion end date for a given promotion id
     */
    public void updatePromotionEndDate(String promotionID, String endDate) throws Throwable {
        setupConnection();
        String stmt = Utils.getSqlQueries().get("wallet_promo_update_exp_date").toString();
        stmt = stmt.replaceFirst("\\?", endDate).
                replaceFirst("\\?", promotionID);
        System.out.println(stmt);
        try {
            statement.executeUpdate(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * To get wallet eligible promo codes with its details by count from database
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public List<Map<String, String>> getWalletFuturePromoCodesByCount(int promoCount) throws Throwable {
        List<Map<String, String>> walletEligiblePromoCodes = new ArrayList();
        setupConnection();
//        try {
        String walletPromoQuery = Utils.getSqlQueries().get("wallet_future_promos_by_count").toString().
                replaceAll("\\?", String.valueOf(promoCount));
        boolean connStatus = connection.isClosed();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(walletPromoQuery);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        while (resultSet.next()) {
            Map<String, String> walletEligiblePromoCode = new HashMap();
            if (!resultSet.getString("PROMOTION_CODE").contains("_") && !resultSet.getString("PROMOTION_CODE").contains("SHINE")) {
                walletEligiblePromoCode.put("promoCode", resultSet.getString("PROMOTION_CODE"));
                walletEligiblePromoCode.put("effectiveDate", sdf.format(resultSet.getDate("PROMOTION_EFF_DATE")));
                walletEligiblePromoCode.put("expirationDate", sdf.format(resultSet.getDate("PROMOTION_EXP_DATE")));
                walletEligiblePromoCode.put("barCode", resultSet.getString("OFFER_BARCODE") == null ? "" : resultSet.getString("OFFER_BARCODE"));
                walletEligiblePromoCode.put("storeCode", resultSet.getString("STORE_OFFER_CODE") == null ? "" : resultSet.getString("STORE_OFFER_CODE"));
                String promotionID = resultSet.getString("PROMOTION_ID");
                walletEligiblePromoCode.put("promotionID", promotionID);
                ResultSet resultSet2, resultSet3, resultSet4;
                String walletPromoHeadingQuery = Utils.getSqlQueries().get("wallet_promo_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet2 = statement2.executeQuery(walletPromoHeadingQuery);
                while (resultSet2 != null && !resultSet2.isClosed() && resultSet2.next()) {
                    walletEligiblePromoCode.put("promoHeading", resultSet2.getString("ATTR_VALUE") == null ? "" : resultSet2.getString("ATTR_VALUE").toUpperCase());
                }

                String walletPromoSubHeadingQuery = Utils.getSqlQueries().get("wallet_promo_sub_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet3 = statement3.executeQuery(walletPromoSubHeadingQuery);
                //since promotion sub heading is not mandatory
                walletEligiblePromoCode.put("promoSubHeading", "");
                while (resultSet3 != null && !resultSet3.isClosed() && resultSet3.next()) {
                    walletEligiblePromoCode.put("promoSubHeading", resultSet3.getString("ATTR_VALUE") == null ? "" : resultSet3.getString("ATTR_VALUE").toUpperCase());
                    break;
                }
                String walletPromoDisclaimerQuery = Utils.getSqlQueries().get("wallet_promo_disclaimer_by_promo_id").toString().replaceAll("\\?", promotionID);
                resultSet4 = statement4.executeQuery(walletPromoDisclaimerQuery);
                while (resultSet4 != null && !resultSet4.isClosed() && resultSet4.next()) {
                    walletEligiblePromoCode.put("legalDisclaimer", resultSet4.getString("LEGAL_DISCLAIMER"));
                    break;
                }
                walletEligiblePromoCodes.add(walletEligiblePromoCode);
            }
        }
        return walletEligiblePromoCodes;
    }

    /**
     * To get promotion details based on promo_code
     *
     * @return null if wallet eligible promo code not available in DB.
     */
    public Map<String, String> getPromotionDetails(String promoCode) throws Throwable {
       // List<Map<String, String>> promotionDetails = new ArrayList();
        String promotionDetailsQuery;
        setupConnection();

        promotionDetailsQuery = Utils.getSqlQueries().get("promotion_details").toString().
                replaceAll("\\?", String.valueOf(promoCode));

        boolean connStatus = connection.isClosed();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(promotionDetailsQuery);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Map<String, String> walletEligiblePromoCode = new HashMap();
        while (resultSet.next()) {

            walletEligiblePromoCode.put("promoCode", resultSet.getString("PROMOTION_CODE"));
            walletEligiblePromoCode.put("isSUPC", "N");
            walletEligiblePromoCode.put("SUPC", null);
            walletEligiblePromoCode.put("effectiveDate", sdf.format(resultSet.getDate("PROMOTION_EFF_DATE")));
            walletEligiblePromoCode.put("expirationDate", sdf.format(resultSet.getDate("PROMOTION_EXP_DATE")));
            walletEligiblePromoCode.put("barCode", resultSet.getString("OFFER_BARCODE") == null ? "" : resultSet.getString("OFFER_BARCODE"));
            walletEligiblePromoCode.put("storeCode", resultSet.getString("STORE_OFFER_CODE") == null ? "" : resultSet.getString("STORE_OFFER_CODE"));
            String promotionID = resultSet.getString("PROMOTION_ID");
            walletEligiblePromoCode.put("promotionID", promotionID);
            walletEligiblePromoCode.put("promoDesc", resultSet.getString("PROMOTION_DESC"));
            ResultSet resultSet2, resultSet3, resultSet4;
            String walletPromoHeadingQuery = Utils.getSqlQueries().get("wallet_promo_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
            resultSet2 = statement2.executeQuery(walletPromoHeadingQuery);
            while (resultSet2 != null && !resultSet2.isClosed() && resultSet2.next()) {
                walletEligiblePromoCode.put("promoHeading", resultSet2.getString("ATTR_VALUE") == null ? "" : resultSet2.getString("ATTR_VALUE").toUpperCase());
            }

            String walletPromoSubHeadingQuery = Utils.getSqlQueries().get("wallet_promo_sub_heading_by_promo_id").toString().replaceAll("\\?", promotionID);
            resultSet3 = statement3.executeQuery(walletPromoSubHeadingQuery);
            //since promotion sub heading is not mandatory
            walletEligiblePromoCode.put("promoSubHeading", "");
            while (resultSet3 != null && !resultSet3.isClosed() && resultSet3.next()) {
                walletEligiblePromoCode.put("promoSubHeading", resultSet3.getString("ATTR_VALUE") == null ? "" : resultSet3.getString("ATTR_VALUE").toUpperCase());
                break;
            }
            String walletPromoDisclaimerQuery = Utils.getSqlQueries().get("wallet_promo_disclaimer_by_promo_id").toString().replaceAll("\\?", promotionID);
            resultSet4 = statement4.executeQuery(walletPromoDisclaimerQuery);
            while (resultSet4 != null && !resultSet4.isClosed() && resultSet4.next()) {
                walletEligiblePromoCode.put("legalDisclaimer", resultSet4.getString("LEGAL_DISCLAIMER"));
                break;
            }
        }
            // promotionDetails.add(walletEligiblePromoCode);

            return walletEligiblePromoCode;
    }


    /**
     * To extract promo_code_id from promo_code table based on promotion_code
     */
    public String getPromoCodeId(String promotion_code) throws Throwable {
        String promo_code_id = null;
        setupConnection();
        String stmt = Utils.getSqlQueries().get("promo_code_id").toString();
        stmt = stmt.replaceFirst("\\?", promotion_code);
        System.out.println(stmt);
        try {
            ResultSet resultSet = statement.executeQuery(stmt);
            if (resultSet.next()) {
                promo_code_id = resultSet.getString("promo_code_id");
            } else {
                Assert.fail("Promo_code_id could not be found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return promo_code_id;
    }

    /**
     * To extract promotion_code from promo_code table
     */
    public String getPromotionCode() throws Throwable {
        String promotion_code = null;
        setupConnection();
        if (customDate == null) {
            customDate = DBUtils.getCustomDate();
        }
        String stmt = Utils.getSqlQueries().get("supc_promotion_code").toString();
        stmt = stmt.replaceAll(">= \\?", ">= '" + customDate.toString() + "'").
                replaceAll("<= \\?", "<= '" + customDate.toString() + "'");
        System.out.println(stmt);
        try {
            ResultSet resultSet = statement.executeQuery(stmt);
            if (resultSet.next()) {
                promotion_code = resultSet.getString("promotion_code");
            } else {
                Assert.fail("SUPC promotion_code could not be found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return promotion_code;
    }

    /**
     * To extract expired SUPC promotion_code from promo_code table
     */
    public String getExpiredSupcPromotionCode() throws Throwable {
        String promotion_code = null;
        setupConnection();
        if (customDate == null) {
            customDate = DBUtils.getCustomDate();
        }
        String stmt = Utils.getSqlQueries().get("supc_expired_promotion_code").toString();
        stmt = stmt.replaceAll(">= \\?", ">= '" + customDate.toString() + "'").
                replaceAll("<= \\?", "<= '" + customDate.toString() + "'");
        System.out.println(stmt);
        try {
            ResultSet resultSet = statement.executeQuery(stmt);
            if (resultSet.next()) {
                promotion_code = resultSet.getString("promotion_code");
            } else {
                Assert.fail("Expired SUPC promotion_code could not be found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return promotion_code;
    }

    /**
     * To insert record in SYSTEM_GENERATE_ID table for SUPC generation
     */
    public void insertSupcRecord(String promo_code_id) throws Throwable {
        setupConnection();
        String stmt = Utils.getSqlQueries().get("insert_promo_supc").toString();
        stmt = stmt.replaceFirst("\\?", promo_code_id);
        System.out.println(stmt);
        try {
            if (!checkSupcRecord(promo_code_id)) {
                statement.executeUpdate(stmt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * To check supc record exists in SYSTEM_GENERATE_ID table
     */
    public boolean checkSupcRecord(String promo_code_id) throws Throwable {
        setupConnection();
        boolean recordExists = false;
        String stmt = Utils.getSqlQueries().get("check_promo_supc").toString();
        stmt = stmt.replaceFirst("\\?", promo_code_id);
        System.out.println(stmt);
        try {
            ResultSet resultSet = statement.executeQuery(stmt);
            if (resultSet.next()) {
                recordExists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recordExists;
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
