package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.steps.MEW.common;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.utils.db.Service;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.junit.runner.Runner;
import org.slf4j.LoggerFactory;

public class TaggedProfileValidation extends StepUtils {
    public String user_id;
    public String user_email;
    public String pwd;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TaggedProfileValidation.class);


    private void softLockedUser(String user_id, String user_email){;
        Service softlock = new Service();
        softlock.softLockUser(user_id, user_email);
        log.info("Softlocked the user " + user_email);
    }


    @And("^I navigate to mew2.0 sign in modal$")
    public void iNavigateToMewSignInModal() throws Throwable {
        Clicks.click("footer.goto_sign_in_link");
        onPage("sign_in");
        log.info("Clicked sign in link from home page footer");
    }

    @And("^I sign in with softlocked user from sign in modal$")
    public void iSignInWithSoftlockedUser() throws Throwable{
        UserProfile profile = TestUsers.getCustomer(null);
        UserProfileService.createUserProfile(profile, true);
        String user_email = TestUsers.currentEmail;
        user_id= CommonUtils.getUserDetailsByEmail(user_email);
        softLockedUser(user_id,user_email);
        TextBoxes.typeTextbox("sign_in.password", TestUsers.currentPassword);
        TextBoxes.typeTextbox("sign_in.email", TestUsers.currentEmail);
        Clicks.click("sign_in.sign_in_button");
    }

    @Given("^I navigate directly to secure-m sign in page$")
    public void iNavigateToSecuremSignInPage() throws Throwable{
        Navigate.visit("Home");
        GlobalNav.openGlobalNav();
        if (!macys())
        GlobalNav.navigateOnGnByName("My bWALLET");
        else{
            GlobalNav.navigateOnGnByName("WALLET");
        }
    }
}