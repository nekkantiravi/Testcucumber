package com.macys.sdt.projects.Selection.Registry.actions.MEW;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.shared.utils.CommonUtils;

/**
 * Created by U063689 on 3/16/2017.
 */
public class RegistryActions {

    private UserProfile regUser;
    static String registrantFN;
    static String registrantLN;
    static String coregistrantFN;
    static String coregistrantLN;
    static String password;
    static String email;



    public void createRegistryAccountAndSignOut() throws Throwable {
        CommonUtils.signInOrCreateAccount();
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        RegistryService.createRandomRegistry(User.getDefaultUser());
        regUser = TestUsers.getCustomerInformation();
        setRegistrantFN(regUser.getRegistry().getContactInfo().getFirstName());
        setRegistrantLN(regUser.getRegistry().getContactInfo().getLastName());
        setEmail(TestUsers.currentEmail);
        setPassword(TestUsers.currentPassword);
        System.out.println(getEmail());
        System.out.println("Registrant's First Name IS " + getRegistrantFN());
        System.out.println("Registrant's Last Name IS " + getRegistrantLN());
        Clicks.clickIfPresent("home.goto_sign_out_link");
    }

    public void setRegistrantFN(String firstName){
        registrantFN = firstName;
    }

    public String getRegistrantFN(){
        return registrantFN;
    }

    public void setRegistrantLN(String lastName){
        registrantLN = lastName;
    }

    public String getRegistrantLN(){
        return registrantLN;
    }

    public void setCoregistrantFN(String firstName){
        coregistrantFN = firstName;
    }

    public String getCoregistrantFN(){
        return coregistrantFN;
    }

    public void setCoregistrantLN(String lastName){
        coregistrantLN = lastName;
    }

    public String getCoregistrantLN(){
        return coregistrantLN;
    }

    public void setPassword(String password){
        RegistryActions.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        RegistryActions.email = email;
    }

    public String getEmail(){
        return email;
    }

}
