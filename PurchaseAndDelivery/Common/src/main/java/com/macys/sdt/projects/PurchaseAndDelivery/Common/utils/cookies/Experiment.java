package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies;


import org.openqa.selenium.Cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.*;

/**
 * Created by atepliashin on 1/19/17.
 */
public class Experiment {

    public static final CookieName COOKIE_NAME = CookieName.SEGMENT;

    public enum Type {
        ASYNC_PAYMENT(macys() ? 1871 : 1914),
        SYNC_PAYMENT(macys() ? 1872 : 1915);

        private final Integer value;

        Type(Integer value) {
            this.value = value;
        }

        public String value() {
            return value.toString();
        }
    }

    private final CookieManager cookieManager;

    Experiment(CookieManager manager) {
        cookieManager = manager;
    }

    public void asyncPaymentEnabled(boolean enabled) {
        switchExperimentValues(enabled, Type.ASYNC_PAYMENT, Type.SYNC_PAYMENT);
    }

    /**
     * Allows easily to add new switchers for experiment values. Usually, there's a pair of opposite experiments
     * that shouldn't be active at the same time. assume the one of them is activating some functionality and
     * the second is in contrary deactivating. So, method gets 3 args : trigger which defines which experiment should
     * be active "activating" or "deactivating", and two experiment types themselves.
     *
     * @param active defines experiment from which param will be activated
     * @param activating activating experiment type
     * @param deactivating deactivating experiment type
     */
    private void switchExperimentValues(boolean active, Type activating, Type deactivating) {
        Cookie cookie = cookieManager.getCookieNamed(COOKIE_NAME);
        String newCookieValue = "{\"EXPERIMENT\":[]}";
        if (cookie != null) {
            newCookieValue = decodeExperimentString(cookie.getValue());
        }
        if (active) {
            newCookieValue = removeExperimentValue(newCookieValue, deactivating);
            newCookieValue = addExperimentValue(newCookieValue, activating);
        } else {
            newCookieValue = removeExperimentValue(newCookieValue, activating);
            newCookieValue = addExperimentValue(newCookieValue, deactivating);
        }
        newCookieValue = encodeExperimentString(newCookieValue);
        cookieManager.deleteCookieNamed(COOKIE_NAME);
        cookieManager.addCookie(new Cookie(COOKIE_NAME.toString(),
                newCookieValue, cookieManager.domain(), null, null));
    }

    private String addExperimentValue(String cookie, Type experiment) {
        List<String> experiments = experimentsAsList(cookie);
        if (!experiments.contains(experiment.value())) {
            experiments.add(experiment.value());
        }
        return experimentsToString(experiments);
    }

    private String removeExperimentValue(String cookie, Type experiment) {
        List<String> experiments = experimentsAsList(cookie);
        experiments.remove(experiment.value());
        return experimentsToString(experiments);
    }

    private List<String> experimentsAsList(String experiments) {
        List<String> list = new ArrayList<>();
        if (!experiments.matches("\\{\"EXPERIMENT\":\\[.*]}")) {
            experiments = "[]";
        }
        // remove shell
        String[] experimentsArray = experiments.split("\\[")[1].split("]");
        // if not empty array then string like value1,value2
        if (experimentsArray.length > 0) {
            experimentsArray = experimentsArray[0].split(",");
        }
        for (String experiment : experimentsArray) {
            list.add(experiment.trim());
        }
        return list;
    }

    private String experimentsToString(List<String> experiments) {
        return "{\"EXPERIMENT\":[" + String.join(",", experiments) + "]}";
    }

    private String decodeExperimentString(String experiments) {
        try {
            return URLDecoder.decode(experiments, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Cannot decode: " + experiments, e);
        }
    }

    private String encodeExperimentString(String experiments) {
        try {
            return URLEncoder.encode(experiments, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Cannot encode: " + experiments, e);
        }
    }
}
