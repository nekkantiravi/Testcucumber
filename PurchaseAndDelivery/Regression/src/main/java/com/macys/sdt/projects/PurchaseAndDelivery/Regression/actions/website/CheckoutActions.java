package com.macys.sdt.projects.PurchaseAndDelivery.Regression.actions.website;

import com.macys.sdt.framework.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

/**
 * Created by YH03402 on 8/4/2017.
 */
public class CheckoutActions {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutActions.class);

    public static JSONObject getProdUserDetails() {
        try {
            String jsonText = Utils.readTextFile(getResourceFile("registry_credentials.json"));
            return new JSONObject(jsonText);
        } catch (IOException | JSONException e) {
            logger.warn("Error while fetching credentials from JSON: " + e.getMessage());
            throw new IllegalStateException("Error while fetching credentials from JSON", e);
        }
    }
}
