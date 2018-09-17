package com.macys.sdt.projects.StabilityAndPerformance.BagComponentization.utils.website;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;

public class QuickbagProduct{
    public String name, color_size, product_promoDesc, badges, bagPromo;
    public int id, quantity;
    public double retail_price, bagPromoPrice;
    public double bagTotal;

    public QuickbagProduct(int id) {
        this.id = id;
    }

    public QuickbagProduct(JSONObject QuickbagProduct) {
        Iterator keys = QuickbagProduct.keys();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            String varName = translate(key);
            try {
                Field f = QuickbagProduct.class.getField(varName);
                f.set(this, QuickbagProduct.get(key));
            } catch (NoSuchFieldException e) {
                System.out.println("No field for " + key + " in Product class");
            } catch (JSONException | IllegalAccessException e) {
                System.err.println("Failed to set product property " + key + ": " + e);
            }
        }
    }

    private String translate(String attr) {
        switch (attr) {
            case "ProductName":
                return "name";
            case "bagTotal":
                return "bagTotal";
            case "quantity":
                return "quantity";
            case "color_size":
                return "color_size";
            case "product_promo":
                return "product_promoDesc";
            case "promo_gift_title":
                return "badges";
            case "price":
                return "retail_price";
            case "bagPromo":
                return "bagPromo";
            case "bagPromoPrice":
                return "bagPromoPrice";
            default:
                return attr;
        }
    }
}
