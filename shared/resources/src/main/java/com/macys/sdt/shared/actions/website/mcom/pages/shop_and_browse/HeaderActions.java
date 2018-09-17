package com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;

/**
 * Created by lpullagurla on 10/20/2017.
 */
public class HeaderActions extends StepUtils{

    public static String getSiteMode(){
        String countryCode = null;
        String currentURL = WebDriverManager.getWebDriver().getCurrentUrl();;
        if(macys()){
            countryCode = Elements.findElement("header_and_footer.country_code_flag") != null ? Elements.findElement("header_and_footer.country_code_flag").getAttribute("alt") : null;
        }else{
            countryCode = Elements.findElement("header_and_footer.country_code_flag") != null ? Elements.findElement("header_and_footer.country_code_flag").getAttribute("src").toLowerCase().split("flags/")[1].substring(0,2).toUpperCase() : null;
        }
        if(countryCode == null && currentURL.contains("wedding")){
            return "registry";
        }else if(countryCode!= null && countryCode.equalsIgnoreCase("US") && currentURL.contains("wedding")){
            return "registry";
        }else if(countryCode!= null && !countryCode.equalsIgnoreCase("US")){
            return "iship";
        }else{
            return "domestic";
        }
    }

    public static String getCountryCode(){
        String countryCode = null;
        String currentURL = WebDriverManager.getWebDriver().getCurrentUrl();;
        if(macys()){
            countryCode = Elements.findElement("header_and_footer.country_code_flag") != null ? Elements.findElement("header_and_footer.country_code_flag").getAttribute("alt") : null;
        }else{
            countryCode = Elements.findElement("header_and_footer.country_code_flag") != null ? Elements.findElement("header_and_footer.country_code_flag").getAttribute("src").toLowerCase().split("flags/")[1].substring(0,2).toUpperCase() : null;
        }
        if(countryCode == null && currentURL.contains("wedding")){
            countryCode = Cookies.getCookieValue("shippingCountry");
        }
        return countryCode;
    }

}
