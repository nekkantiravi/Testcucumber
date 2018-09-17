package com.macys.sdt.projects.Discovery.Regression.utils;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.*;
/**
 * Created by Admin on 15/06/17.
 */
public class Login extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    /**
     * sign in to a non-registry account or create one if account doesn't exist
     *
     * @return UserProfile with details about the signed in user
     */
    public static void signIn() {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
            GlobalNav.closeGlobalNav();
            //pausePageHangWatchDog();
            typeEmailTextbox();
            typePassTextbox();
            Wait.secondsUntilElementPresent("registry_category.signIn",20);
            //Clicks.click("registry_category.signIn");
            Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
    }

    public static void typeEmailTextbox() {
        Wait.secondsUntilElementPresent("registry_category.login_email",30);
        String value = Elements.getValues("login.email").get(0).toString();
        TextBoxes.typeTextbox("registry_category.login_email", value);

    }

    public static void typePassTextbox() {
        Wait.secondsUntilElementPresent("registry_category.login_pass", 30);
        String value = Elements.getValues("login.password").get(0).toString();
        TextBoxes.typeTextbox("registry_category.login_pass", value);
    }

    /**
     * Close IE certificate error for ie version 10 and 11
     */
    public static void closeIECertError() {
        if (ie() && WebDriverManager.getCurrentTitle().contains("Certificate Error:")) {
            Navigate.execJavascript("javascript:document.getElementById('overridelink').click();");
        }
    }
}
