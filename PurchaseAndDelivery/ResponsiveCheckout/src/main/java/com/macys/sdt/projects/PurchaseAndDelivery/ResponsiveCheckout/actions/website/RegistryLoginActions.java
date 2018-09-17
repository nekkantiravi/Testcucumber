package com.macys.sdt.projects.PurchaseAndDelivery.ResponsiveCheckout.actions.website;

import com.macys.sdt.framework.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

/**
 * Created by YH03776 on 10/13/2017.
 */
public class RegistryLoginActions {
    private static final Logger logger = LoggerFactory.getLogger(RegistryLoginActions.class);

    public static JSONObject getLoginDetails() {
        try {
            String jsonText = Utils.readTextFile(getResourceFile("login.json"));
            return new JSONObject(jsonText);
        } catch (IOException | JSONException e) {
            logger.warn("Error while fetching credentials from JSON: " + e.getMessage());
            throw new IllegalStateException("Error while fetching credentials from JSON", e);
        }
    }
}