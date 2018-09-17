package com.macys.sdt.projects.PurchaseAndDelivery.Regression.actions.website;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.Promotion;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTEndPoints;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class PromotionsActions {

    public static Promotion processThresholdStatus(String thresholdStatus, String products, String promoType, String
            triggers) {
        Promotion promotion = setPromotionTest(thresholdStatus, products, promoType, triggers);
        addProductsToBagViaRest(promotion);
        return promotion;
    }

    public static void addProductsToBagViaRest(Promotion promotion) {
        String userId = Cookies.getCookieValue("bloomingdales_online_uid");
        String serviceURL = getServiceURL() + "?userId=" + userId;
        System.out.println(getServiceURL());
        for (Product product : promotion.products
                ) {
            Response response = RESTOperations
                    .doPOST(serviceURL, MediaType.APPLICATION_JSON, getPayload(product).toString(), null);
            if (response.getStatus() != 200) {
                throw new EnvException("Failed to add product to bag. Got response code:" + response.getStatus());
            }
        }
    }

    private static String getServiceURL() {
        return "http://" + EnvironmentDetails.otherApp("MSPOrder").ipAddress + ":8080/api/" + RESTEndPoints.ADD_TO_BAG;
    }

    private static JSONObject getPayload(Product product) {
        JSONObject item = new JSONObject();
        item.put("quantity", product.quantity);
        item.put("upcId", product.upcID);
        JSONObject object = new JSONObject();
        object.put("item", item);
        return object;
    }

    private static JSONObject getJson() {
        JSONObject promoData;
        File promotionData = getResourceFile("promotion_data.json");
        try {
            InputStream is = new FileInputStream(promotionData);
            String jsonTxt = IOUtils.toString(is);
            promoData = new JSONObject(jsonTxt).getJSONObject("promo_types");
        } catch (Exception e) {
            throw new AssertionError("Unable to read promotion data json file", e);
        }
        return promoData;
    }

    private static String spacesToUnderscores(String string) {
        return string.replaceAll("\\s+", "_").toLowerCase();
    }

    private static ArrayList<Product> getProductsData(JSONObject promotionJson, String thresholdStatus, String
            products) {
        JSONArray productsJson = promotionJson.getJSONObject(spacesToUnderscores(thresholdStatus))
                                              .getJSONArray(spacesToUnderscores(products));
        ArrayList<Product> productsArray = new ArrayList<>();
        for (int i = 0; i < productsJson.length(); i++) {
            JSONObject jsonProduct = productsJson.getJSONObject(i);
            Product pd = new Product(jsonProduct.getInt("product_id"));
            pd.description = (jsonProduct.getString("product_description"));
            String size = (jsonProduct.getString("product_size"));
            if (!size.isEmpty())
                pd.sizeName = size;
            String color = (jsonProduct.getString("product_color"));
            if (!size.isEmpty())
                pd.colorName = color;
            pd.brand = (jsonProduct.getString("product_brand"));
            pd.individualPrice = (jsonProduct.getDouble("product_price"));
            pd.salePrice = (jsonProduct.getDouble("product_price_with_offers"));
            pd.quantity = (jsonProduct.getInt("product_quantity"));
            pd.isOverThreshold = (jsonProduct.getBoolean("product_over_threshold"));
            pd.upc = (jsonProduct.getInt("product_upc"));
            pd.upcID = (jsonProduct.getInt("product_upc_id"));
            productsArray.add(pd);
        }
        return productsArray;
    }

    private static Promotion setPromotionTest(String thresholdStatus, String products, String promoType, String
            triggers) {
        JSONObject promotionJson = getJson().getJSONObject(spacesToUnderscores(promoType))
                                            .getJSONObject(spacesToUnderscores(triggers));
        Promotion p = new Promotion();
        p.id = (promotionJson.getInt("promo_id"));
        p.global = (!promotionJson.getBoolean("has_promo_code"));
        p.promoCode = (promotionJson.getString("promo_code"));
        p.offerDescription = (promotionJson.getString("checkout_description"));
        p.name = (promotionJson.getString("offer_name_line_1"));
        p.legalDisclaimer = (promotionJson.getString("online_disclaimer"));
        p.utCopy = (promotionJson.getString("ut_copy"));
        p.otCopy = (promotionJson.getString("ot_copy"));
        p.products = (getProductsData(promotionJson, thresholdStatus, products));
        return p;
    }

}
