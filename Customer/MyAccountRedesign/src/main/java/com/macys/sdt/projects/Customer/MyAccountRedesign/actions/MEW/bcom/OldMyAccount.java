package com.macys.sdt.projects.Customer.MyAccountRedesign.actions.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;

/**
 * Created by uc06174 on 8/2/2017.
 */
public class OldMyAccount extends StepUtils {
    public static void verifyMyPreferencesLinkExists (){
        Wait.secondsUntilElementPresent("my_account.myPreferencesLink",20);
        Wait.isPageLoaded();
        Assert.assertTrue("My Preferences link not exists on MyAccount Page", Elements.elementPresent("my_account.myPreferencesLink"));

    }

    public static void verifyMyPreferredStoreNotExists (){
        Wait.isPageLoaded();
        Assert.assertFalse("My Preferred store header exists on MyAccount Page", Elements.elementPresent("my_account.myPreferredStoreHeader"));
        Assert.assertFalse("My Preferred store box exists on MyAccount Page", Elements.elementPresent("my_account.myPreferredStoreBox"));

    }

    public static void clickMyPreferencesLink (){
        Wait.secondsUntilElementPresent("my_account.myPreferencesLink",20);
        Wait.isPageLoaded();
        Clicks.click("my_account.myPreferencesLink");
    }

    public static void verifyMyPrefencesLinkRedirectsToCorrectUrl (){
        Wait.isPageLoaded();
        Assert.assertTrue("User is not navigated to correct My Preferences url",WebDriverManager.getCurrentUrl().contains("/account/preferences/"));
    }

    public static void verifyCoremetricsTagIsAppendedToMyPreferencesUrl (){
        Wait.isPageLoaded();
        Assert.assertTrue("User is not navigated to correct My Preferences url",WebDriverManager.getCurrentUrl().contains("?cm_sp=my_account-_-loyallist-_-my_preferences"));
    }

}
