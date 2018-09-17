package com.macys.sdt.projects.Customer.MyAccountRedesign.utils;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;

/**
 * Created by m509575 on 23/03/17.
 */
public class MyAccountUtils  extends StepUtils {
    /***
     * This Method just sing with existing customer data
     * @param customer
     * @return
     */
    public static boolean signInAsExistingAccount(UserProfile customer){
        String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
        Wait.untilElementPresent(elementName);
        Clicks.click(elementName);
        TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.verify_page");
        if (!MEW())
            Wait.untilElementNotPresent("sign_in.verify_page");

        Wait.untilElementPresent("header.goto_sign_out_link");
        return true;
    }

}
