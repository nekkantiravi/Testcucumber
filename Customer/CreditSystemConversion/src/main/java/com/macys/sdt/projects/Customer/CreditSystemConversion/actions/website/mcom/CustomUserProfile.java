package com.macys.sdt.projects.Customer.CreditSystemConversion.actions.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;

/**
 * Created by yh00420 on 1/30/2017.
 */
public class CustomUserProfile {

    static public void login(String username, String password) {
        try {

            // navigate to sign in page
            String elementName = "home." + (StepUtils.macys() ? "goto_sign_in_link" : "goto_my_account_link");
            Wait.untilElementPresent(elementName);
            Clicks.click(elementName);

            // sign in
            TextBoxes.typeTextbox("sign_in.email", username);
            TextBoxes.typeTextbox("sign_in.password", password);
            Clicks.click("sign_in.verify_page");
        } catch(Exception e)    {
            e.printStackTrace();
        }

    }


}
