package com.macys.sdt.projects.Marketing.SeoSLP.utils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by heqbal on 12/6/2017.
 */
public class DBSeoSlp {

    public static JSONObject sql = null;
    public static Connection connection = null;
    private static final Logger logger = LoggerFactory.getLogger(DBSeoSlp.class);
    public static String meta_description;

    public static String getMetaDescription(String category) throws Throwable {
        if (connection == null)
            connection = new DBConnection().createConnection();
        if (sql == null)
            sql = Utils.getSqlQueries();
        String query = sql.getJSONObject("media_service").getString("category_meta_description");
        ResultSet rs1;
        try {
            PreparedStatement p1 = connection.prepareStatement(query.replace("?", category));
            rs1 = p1.executeQuery();
            while (rs1.next()) {
                meta_description =(rs1.getString("ATTR_VALUE"));
            }

        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        return meta_description;
    }

    public static String getPageTitle(String category) throws Throwable {
        String pageTitle = null;
        if (connection == null)
            connection = new DBConnection().createConnection();
        if (sql == null)
            sql = Utils.getSqlQueries();
        String query = sql.getJSONObject("media_service").getString("category_page_description");
        ResultSet rs1;
        try {
            PreparedStatement p1 = connection.prepareStatement(query.replace("?", category));
            rs1 = p1.executeQuery();
            while (rs1.next()) {
                pageTitle =(rs1.getString("ATTR_VALUE"));
            }

        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        return pageTitle;
    }

    public static String getCategoryName(String category) throws Throwable {
        String categoryName = null;
        if (connection == null)
            connection = new DBConnection().createConnection();
        if (sql == null)
            sql = Utils.getSqlQueries();
        String query = sql.getJSONObject("media_service").getString("category_name");
        ResultSet rs1;
        try {
            PreparedStatement p1 = connection.prepareStatement(query.replace("?", category));
            rs1 = p1.executeQuery();
            while (rs1.next()) {
                categoryName =(rs1.getString("CAT_NAME"));
            }

        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        return categoryName;
    }
}
