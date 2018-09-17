package com.macys.sdt.projects.Marketing.SEOLeftNav.utils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.models.MediaService;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import com.macys.sdt.projects.Marketing.SEOLeftNav.steps.website.mcom.SEOLeftNav;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.macys.sdt.framework.utils.db.models.MediaService.*;


public class LeftNavService {

    public static JSONObject sql = null;
    public static Connection connection = null;
    private static final Logger logger = LoggerFactory.getLogger(SEOLeftNav.class);
    
    public static List<String> getMediaGroupData(String category) throws Throwable {
        List<String> canvasRowIds = new ArrayList<>();
        List<String> mediaGroupIds = new ArrayList<>();
        String canvasId = null;
        if (connection == null)
            connection = new DBConnection().createConnection();
        if (sql == null)
            sql = Utils.getSqlQueries();
        String query = sql.getJSONObject("media_service").getString("with_category_id_false");
        ResultSet rs1;
        try {
            PreparedStatement p1 = connection.prepareStatement(query.replace("?", category));
            rs1 = p1.executeQuery();
            while (rs1.next()) {
                canvasId = rs1.getString("canvas_id");
            }
            Assert.assertFalse("ERROR - APP : Canvas Id data is not available", canvasId == null);
            query = sql.getJSONObject("media_service").getString("canvas_row_id");
            PreparedStatement p2 = connection.prepareStatement(query.replace("?", canvasId));
            rs1 = p2.executeQuery();
            while (rs1.next()) {
                canvasRowIds.add(rs1.getString("canvas_row_id"));
            }
            Assert.assertFalse("ERROR - APP : Canvas row Ids data is empty", canvasRowIds.isEmpty());
            Date customDate = getCustomDate();
            List<String[]> values = new ArrayList<>();
            values.add(canvasRowIds.toArray(new String[canvasRowIds.size()]));
            values.add(new String[]{"CATEGORY_ICON"});
            query = MediaService.updatedQuery((sql.getJSONObject("media_service").getString("media_group_data")
                    .replaceAll("<= \\?", "<= '" + customDate.toString() + "'").replaceAll(">= \\?", ">= '" + customDate.toString() + "'")), values, "string").replaceFirst("\\?", "106");
            PreparedStatement p3 = connection.prepareStatement(query);
            rs1 = p3.executeQuery();
            while (rs1.next()) {
                mediaGroupIds.add(rs1.getString("media_group_id"));
            }
            Assert.assertFalse("ERROR - APP : Media group Ids data is empty", mediaGroupIds.isEmpty());
        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        logger.info("Media Group ID's:" + mediaGroupIds);
        return mediaGroupIds;
    }

    public static List<String> getStaticLinksData(List<String> mediaGroupIDs) throws Throwable {
        List<String> static_links_text = new ArrayList<>();
        if (sql == null)
            sql = Utils.getSqlQueries();
        if (connection == null)
            connection = new DBConnection().createConnection();
        List<String[]> values = new ArrayList<>();
        values.add(mediaGroupIDs.toArray(new String[mediaGroupIDs.size()]));
        String query = MediaService.updatedQuery((sql.getJSONObject("media_service").getString("with_media_parameter_with_static_link")
                .replaceAll("<= \\?", "<= '" + customDate.toString() + "'").replaceAll(">= \\?", ">= '" + customDate.toString() + "'")), values, "string");
        try {
            PreparedStatement p1 = connection.prepareStatement(query);
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                static_links_text.add(rs1.getString("text"));
            }
            Assert.assertFalse("ERROR - APP : Static link data is empty", static_links_text.isEmpty());
        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        return static_links_text;
    }

    public static List<Map> getStaticLinksURL(String staticLinksText) throws Throwable {
        List<Map> staticURLs = new ArrayList<>();
        if (sql == null)
            sql = Utils.getSqlQueries();
        if (connection == null)
            connection = new DBConnection().createConnection();
        String query = sql.getJSONObject("media_service").getString("with_static_link_url_in_left_nav").replace("?", staticLinksText);
        logger.info("getStaticLinksURL :" + query);
        try {
            PreparedStatement p1 = connection.prepareStatement(query);
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                Map<String, String> staticUrlMapData = new HashMap<>();
                staticUrlMapData.put("statictext", rs1.getString("text"));
                staticUrlMapData.put("staticturl", rs1.getString("URL_TEXT"));
                staticURLs.add(staticUrlMapData);
            }
            Assert.assertFalse("ERROR - APP : Static link data is empty", staticURLs.isEmpty());
        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        return staticURLs;
    }

    public static List<Map> getCategoryLinksData(List<String> mediaGroupIDs) throws Throwable {
        List<Map> categoryLinkData = new ArrayList<>();
        if (sql == null)
            sql = Utils.getSqlQueries();
        if (connection == null)
            connection = new DBConnection().createConnection();
        List<String[]> values = new ArrayList<>();
        values.add(mediaGroupIDs.toArray(new String[mediaGroupIDs.size()]));
        String query = MediaService.updatedQuery((sql.getJSONObject("media_service").getString("with_media_parameter_with_link_data")
                .replaceAll("<= \\?", "<= '" + customDate.toString() + "'").replaceAll(">= \\?", ">= '" + customDate.toString() + "'")), values, "string");
        try {
            PreparedStatement p1 = connection.prepareStatement(query);
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                Map<String, String> categoryLinkMapData = new HashMap<>();
                categoryLinkMapData.put("text", rs1.getString("text"));
                categoryLinkMapData.put("categoryID", rs1.getString("REF_ID"));
                categoryLinkData.add(categoryLinkMapData);

            }
            Assert.assertFalse("ERROR - APP : Category link data is empty", categoryLinkData.isEmpty());
        } catch (Exception e) {
            Assert.fail("ERROR : APP - Error while fetching data from DB");
        }
        return categoryLinkData;
    }
}
