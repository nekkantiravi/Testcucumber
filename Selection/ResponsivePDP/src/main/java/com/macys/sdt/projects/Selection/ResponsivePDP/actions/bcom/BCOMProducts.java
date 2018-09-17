package com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom;

import com.macys.sdt.framework.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import static com.macys.sdt.framework.utils.StepUtils.prodEnv;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class BCOMProducts {
    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());

    public static String getRandomPdpFromJSON(Map<String, Object> options) {
        try {
            JSONArray products;
            if (prodEnv()) {
                options.putIfAbsent("prod_available", true);
            }
            ArrayList<String> productList = new ArrayList<>();
            File addressFile = getResourceFile("bcom_pdp_responsive_products.json");
            String jsonTxt = Utils.readTextFile(addressFile);
            JSONObject json = new JSONObject(jsonTxt);
            products = json.getJSONArray("bloomingdales");
            for (int i = 0; i < products.length(); i++) {
                JSONObject productJson = products.getJSONObject(i);
                String productId = productJson.get("id").toString();
                int counter = 0;
                for (String key : options.keySet()) {
                    try {
                        if (options.get(key).toString().equalsIgnoreCase(productJson.get(key).toString())) {
                            counter++;
                        }
                    } catch (JSONException e) {
                        if (Boolean.parseBoolean(options.get(key).toString()) ||
                                !options.get(key).toString().equalsIgnoreCase("false")) {
                            break;
                        }
                    }
                }
                if(counter==options.size()){
                    productList.add(productId);
                }
            }
            Random rand = new Random();
            log.info("items found =" + productList.size());
            return productList.get(rand.nextInt(productList.size()));
        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
    }

}
