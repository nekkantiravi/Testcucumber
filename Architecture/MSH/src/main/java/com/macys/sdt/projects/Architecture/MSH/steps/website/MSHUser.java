package com.macys.sdt.projects.Architecture.MSH.steps.website;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Architecture.MSH.utils.db.models.MSHUserService;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Architecture.MSH.utils.db.models.ModVerification;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.javalite.common.Util.readFile;
import static org.junit.Assert.assertTrue;

public class MSHUser extends StepUtils {
    String user_id;
    String DALUserData;
    String RTPUserData;
    String RTPuser_id;
    String RTPUserContactData;
    String DAL;
    String RTP;
    String DALUserdata;
    String daluid;

    @Given("^I visit the REST API service to create the user profile$")
    public void VisitHomePage() throws Throwable   {
        Navigate.visit("home");
        UserProfileService.createRandomUserProfile().getUser().getId();
        user_id=TestUsers.getCustomer(null).getUser().getId();
        System.out.println(user_id);

    }

    @Then("^I should verify the user_profile on RTP Data Center$")
    public void isUserExist() throws Throwable {
        MSHUserService userdata1 = new MSHUserService();
        ArrayList RTPUserData = userdata1.getUserdata(user_id);
        System.out.println("Userdata returned from getUserdata method is:: " + RTPUserData);
        FileWriter file = new FileWriter("Architecture\\MSH\\src\\main\\java\\com\\macys\\sdt\\projects\\Architecture\\MSH\\steps\\website\\msh.json");
        file.write(RTPUserData.toString());
        file.close();

    }

    @Given("^I have visited home page$")
    public void LoadMshJsonFile() throws JSONException, IOException {
        Navigate.visit("home");
        RTP = readFile("Architecture\\MSH\\src\\main\\java\\com\\macys\\sdt\\projects\\Architecture\\MSH\\steps\\website\\msh.json");
        System.out.println("user data values ::: " + RTP);
        String[] strs;
        strs = RTP.toString().split(",");
        RTPuser_id = strs[0].substring(1, strs[0].length());
        System.out.println("user data  ::: " + RTPuser_id);



    }

   @And("^I set dca cookie value to DAL$")
    public void setDcaValueToDAL() throws JSONException, IOException {
        String cookieValue = Cookies.getCookieValue("dca");
        if(!cookieValue.equals("DAL")) {
            Cookies.editCookie("dca", cookieValue, "DAL");
        }
    }

    @And("^I set dca cookie value to RTP")
    public void setDcaValueToRTP() throws JSONException, IOException {
        String cookieValue = Cookies.getCookieValue("dca");
        if(!cookieValue.equals("RTP")) {
            Cookies.editCookie("dca", cookieValue, "RTP");
        }
    }

    @Then("^I should verify the user_profile on DAL Data Center$")
    public void isDALUserExists() throws Throwable {
        MSHUserService userdata = new MSHUserService();
        daluid = Cookies.getCookieValue("macys_online_uid");
        ArrayList DALUserData = userdata.getUserdata(daluid);
        ArrayList compositeKey = new ArrayList();
        compositeKey.add(daluid);
        FileWriter file = new FileWriter("Architecture\\MSH\\src\\main\\java\\com\\macys\\sdt\\projects\\Architecture\\MSH\\steps\\website\\msh.json");
        for(Object ob: DALUserData) {
            file.write(ob.toString());
        }
        file.close();
        boolean isMod = ModVerification.checkModValue("DAL",compositeKey);
        assertTrue(isMod);
    }

    @And("^I should see the user data is same in RTP Data Center$")
    public void checkDataonRTP() throws Throwable{
        Cookies.editCookie("dca", "DAL", "RTP");
        MSHUserService userdata = new MSHUserService();
        ArrayList RTPUserdata = userdata.getUserdata(daluid);
        StringBuilder sb= new StringBuilder();
        for(Object ob: RTPUserdata) {
           sb.append(ob);
        }
        String RTPdata = sb.toString().replaceAll("[,]$", "");
        String DAL = readFile("Architecture\\MSH\\src\\main\\java\\com\\macys\\sdt\\projects\\Architecture\\MSH\\steps\\website\\msh.json");
        String DALdata = DAL.replaceAll("[,]$", "");
        if(RTPdata.equals(DALdata)) {
            System.out.println("data is same in both RTP and DAL");
        }
        else{
            System.out.println("data is not same in both RTP and DAL");
        }
    }


    @Then("^I should see the user data is same in both the data bases$")
    public void VerifythedatainDALdatacenter() throws Throwable {
        MSHUserService userdata = new MSHUserService();
        System.out.println("RTPuser_id in second feature file is" +RTPuser_id);
        DALUserdata = userdata.getUserdata(RTPuser_id).toString();

        DALUserdata.toString();

        System.out.println("VerifythedatainDALdatacenter DAL USER data is "+""+DALUserdata);

        if(DALUserdata.equals(RTP)) {
            System.out.println("data is same in both RTP and DAL");
        }
        else{
            System.out.println("data is not same in both RTP and DAL");
        }
    }

}

