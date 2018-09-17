package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies;

import org.openqa.selenium.Cookie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atepliashin on 1/19/17.
 */
public class MacysCookieMap {

    public enum Key {
        USER_PC("USERPC"),
        BOPS_PICKUP_STORE("BOPSPICKUPSTORE");

        Key(String name) {
            this.name = name;
        }

        private final String name;

        public String toString() {
            return name;
        }
    }

    static private final String KEY_VALUE_DELIMITER = "1_92_";
    static private final String ELEMENTS_DELIMITER = "3_87_";
    private final CookieName cookieName;
    private final CookieManager cookieManager;


    public MacysCookieMap(CookieName name, CookieManager manager) {
        cookieName = name;
        cookieManager = manager;
    }

    public void set(Key cookieKey, String cookieValue) {
        Cookie cookie = cookieManager.getCookieNamed(cookieName);
        Map<String, String> newValuesMap = new HashMap<>();
        if (cookie != null) {
            newValuesMap.putAll(cookieValueAsMap(cookie.getValue()));
        }
        newValuesMap.put(cookieKey.name, cookieValue);
        cookie = new Cookie(cookieName.toString(), mapAsCookieValue(newValuesMap), cookieManager.domain(), null, null);
        cookieManager.deleteCookieNamed(cookieName);
        cookieManager.addCookie(cookie);
    }

    private Map<String, String> cookieValueAsMap(String cookieValue) {
        Map<String, String> valuesMap = new HashMap<>();
        for (String pair : cookieValue.split(ELEMENTS_DELIMITER)) {
            String[] keyValue = pair.split(KEY_VALUE_DELIMITER);
            String value;
            try {
                value = keyValue[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                value = "";
            }
            valuesMap.put(keyValue[0], value);
        }
        return valuesMap;
    }

    private String mapAsCookieValue(Map<String, String> valuesMap) {
        StringBuilder cookieValue = new StringBuilder();
        for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
            cookieValue.append(entry.getKey()).
                    append(KEY_VALUE_DELIMITER).
                    append(entry.getValue()).
                    append(ELEMENTS_DELIMITER);
        }
        return cookieValue.toString();
    }
}
