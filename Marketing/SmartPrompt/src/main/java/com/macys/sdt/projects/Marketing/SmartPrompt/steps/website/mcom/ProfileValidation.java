package com.macys.sdt.projects.Marketing.SmartPrompt.steps.website.mcom;

import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import org.slf4j.LoggerFactory;


public class ProfileValidation extends StepUtils {
    public String user_id;
    public String user_email;
    public String user_pwd;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProfileValidation.class);
    UserProfile profile = null;


    public void createProfile() throws Throwable {
        profile = TestUsers.getCustomer(null);
        UserProfileService.createUserProfile(profile, true);
        user_pwd = profile.getUser().getLoginCredentials().getPassword();
        user_email = profile.getUser().getProfileAddress().getEmail();
    }
}

