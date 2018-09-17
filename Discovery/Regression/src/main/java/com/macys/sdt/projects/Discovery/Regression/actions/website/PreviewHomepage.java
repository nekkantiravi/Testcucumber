package com.macys.sdt.projects.Discovery.Regression.actions.website;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Discovery.Regression.utils.ReadPreviewExcelData;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YH03402 on 9/5/2017.
 */
public class PreviewHomepage {
    private static final Logger logger = LoggerFactory.getLogger(PreviewCategory.class);

    public static Map getCateogorylinks(String category) {
        HashMap<String, String> linkDetails = new HashMap<>();
        logger.info(category);
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("standard_links.json"))));
            JSONObject json2 = json.getJSONObject(category);
            linkDetails.put("catagory_no", json2.get("catagory_no.").toString());
            linkDetails.put("link", json2.get("link").toString());
        } catch (Exception e) {
            logger.info("Exception : " + e.getMessage());
        }
        return linkDetails;
    }

    public static String getCateogoryId(String category) {
        String id;
        logger.info(category);
        category=category.toString().trim();
        try {
            JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile("standard_links.json"))));
            JSONObject json2 = json.getJSONObject(category);
            id = json2.get("catagory_no.").toString();
            return id;
        } catch (Exception e) {
            logger.info("Exception : " + e.getMessage());
            return null;
        }
    }

    public static HashMap<String, String> getPreviewLinksfromExcel(String fieldType, String excelFileName) throws IOException {
        HashMap<String, String> sheetLinkMap = new HashMap<>();
        HashMap<String, HashMap<String, String>> filedata = ReadPreviewExcelData.getPreviewExcelData(excelFileName);
        String category_name = "";
        logger.info(filedata.toString());

        Iterator<Map.Entry<String, HashMap<String, String>>> it;
        it = filedata.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> data = it.next();
            logger.info(data.getKey() + "::" + data.getValue());
            if (data.getKey().contains(fieldType)&& !data.getKey().contains("Pop Up")) {//Main Ad
                HashMap<String, String> InnerMap = new HashMap<>();
                InnerMap = data.getValue();
                if (InnerMap.get("Links").equalsIgnoreCase("standard")) {
                    category_name = InnerMap.get("Info").toLowerCase();
                    category_name = category_name.trim();
                    sheetLinkMap.put(category_name, getCateogoryId(category_name));
                    logger.info(PreviewHomepage.getCateogoryId(category_name));
                } else if (StringUtils.isNumeric(data.getValue().get("Links"))) {
                    sheetLinkMap.put(data.getValue().get("Info").toLowerCase().trim(), data.getValue().get("Links").toLowerCase().trim());
                } else if(data.getValue().get("Links").contains("/")){
                    sheetLinkMap.put(data.getValue().get("Info").toLowerCase().trim(),data.getValue().get("Links"));
                }
            }
        }
        return sheetLinkMap;
    }

    public static String getImageNamefromExcel(String fieldType, String excelFileName) throws IOException {
        HashMap<String, String> sheetLinkMap = new HashMap<>();
        String imageName = null;
        HashMap<String, HashMap<String, String>> filedata = ReadPreviewExcelData.getPreviewExcelData(excelFileName);
        logger.info(filedata.toString());

        Iterator<Map.Entry<String, HashMap<String, String>>> it;
        it = filedata.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> data = it.next();
            //   logger.info(data.getKey() + "::" + data.getValue());
            if (data.getKey().contains(fieldType)) {//Main Ad
                if (data.getValue().get("Info").equalsIgnoreCase("Image"))
                    imageName = data.getValue().get("Details");
            }
        }
        return imageName;
    }

    public static void iscooridatesValid(String coordinates) {
        int x1 = Integer.parseInt(coordinates.split(",")[0]);
        int y1 = Integer.parseInt(coordinates.split(",")[1]);
        int x2 = Integer.parseInt(coordinates.split(",")[2]);
        int y2 = Integer.parseInt(coordinates.split(",")[3]);

        logger.info("length" + (x2 - x1));
        logger.info("Height" + (y2 - y1));
        Assert.assertTrue("X axis coordinates are wrong", ((x2 - x1) >= 115));
        Assert.assertTrue("Y axis coordinates are wrong", ((y2 - y1) >= 34));
        Assert.assertTrue("X and Y axis coordinates are wrong", ((x2 - x1) > (y2 - y1)));


    }
}

