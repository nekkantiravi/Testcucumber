package com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom;

import com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom.Actions;

import java.util.*;

import static com.macys.sdt.framework.utils.StepUtils.closePopup;

import java.util.logging.Logger;

public class BCOMActions {
    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());

    public static HashMap<String, Boolean> setOptions(String productTrue, String productFalse) {
        HashMap<String, Boolean> optionMap = new HashMap<>();
        if (productTrue != null) {
            for (String s : productTrue.split(" ")) {
                if (!s.equalsIgnoreCase("and")) {
                    optionMap.put(s, true);
                }
            }
        }
        if (productFalse != null) {
            for (String s : productFalse.split(" ")) {
                if (!s.equalsIgnoreCase("and")) {
                    optionMap.put(s, false);
                }
            }
        }
        return optionMap;
    }

    public static void navigateDirectlyToPDP(String productID) throws Throwable {
        //String product_url = RunConfig.url + "/shop/product/?ID=" + productID;
        String product_url = "http://digitalproductui-bcom-rpdp17z.tbe.zeus.fds.com:8080/shop/product/seo-friendly-name?overlay=true&backtotop=300&ID=" + productID;
        Actions.visitURL(product_url);
        closePopup();
    }

}
