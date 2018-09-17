package com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;


/**
 * Created by Admin on 5/15/2017.
 */
public class SignInPage {

    public static void enterEmailAndPassword() {
        UserProfile  customer = TestUsers.getCustomer(null);
            TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
    }

    public static void clickOnSignInButton(){
        Clicks.click("sign_in.verify_page");
        StepUtils.resumePageHangWatchDog();
    }

    public static void clickOnCreateProfileButton()
    {
        Clicks.click("homePage.Create_Profile_Button");
    }
}
