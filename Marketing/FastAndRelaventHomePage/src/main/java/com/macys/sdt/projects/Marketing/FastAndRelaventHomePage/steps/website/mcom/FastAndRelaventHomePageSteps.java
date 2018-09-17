package com.macys.sdt.projects.Marketing.FastAndRelaventHomePage.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.FastAndRelaventHomePage.actions.website.mcom.panels.FlexPanel;
import com.macys.sdt.projects.Marketing.FastAndRelaventHomePage.utils.HomePageMediaService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FastAndRelaventHomePageSteps extends StepUtils {

    public static List<Map<String, Object>> response;
    public static List<Map<String, Object>> uiData;


    @When("^I fetch all media details for home page$")
    public void iFetchAllMediaDetailsForHomePage() throws Throwable {
        Navigate.visit("home");
        pausePageHangWatchDog();
        HomePageMediaService service = new HomePageMediaService();
        response = signedIn() ? service.getAllRowMediaDataForSignedInUser() : service.getAllRowMediaData();
        uiData = new FlexPanel().getAllRowMediaDetails();
        resumePageHangWatchDog();
    }

    @Then("^I verify all media data displayed on home page$")
    public void iVerifyAllMediaDataDisplayedOnHomePage() throws Throwable {
        for (int index = 0; index < uiData.size(); index++) {
            uiData.get(index);
            if (uiData.get(index).get("mediaType") instanceof ArrayList) {
                for (int i = 0; i < ((List<String>) uiData.get(index).get("mediaType")).size(); i++) {
                    String type = ((List<String>) uiData.get(index).get("mediaType")).get(i);
                    Map<String, Object> data = ((Map) ((List<Map>) uiData.get(index).get("mediaData")).get(i).get("data"));
                    Map<String, Object> res = ((Map) ((List<Map>) response.get(index).get("zones")).get(i).get("data"));
                    compareMediaData(type, data, res);
                }
            } else {
                String type = uiData.get(index).get("mediaType").toString();
                if (!type.equals("horizontalRule")){
                    Map<String, Object> data = (Map)((Map) uiData.get(index).get("mediaData")).get("data");
                    Map<String, Object> res = ((Map) ((List<Map>) response.get(index).get("zones")).get(0).get("data"));
                    compareMediaData(type, data, res);
                }
            }
        }
    }

    @And("^I update user profile to get home page media$")
    public void iUpdateUserProfileToGetHomePageMedia()throws Throwable{
        Clicks.click("home.goto_my_account");
        Clicks.click("my_account.my_profile");
        TestUsers.clearCustomer();
        UserProfile customer = TestUsers.getCustomer(null);
        customer.getUser().setGender("Male");
        customer.getUser().getDateOfBirth("1975-11-11");
        User user = customer.getUser();
        ProfileAddress profileAddress = user.getProfileAddress();
        TextBoxes.typeTextbox("my_profile.address_line_1", profileAddress.getAddressLine1());
        TextBoxes.typeTextbox("my_profile.address_city", profileAddress.getCity());
        if (macys() || MEW()) {
            DropDowns.selectByText("my_profile.address_state", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
        } else {
            DropDowns.selectCustomText("create_profile.address_state_list", "create_profile.state_options", ((profileAddress.getState().length() == 2) ? AbbreviationHelper.translateStateAbbreviation(profileAddress.getState()) : profileAddress.getState()));
            Clicks.clickIfPresent("my_profile.gender_female");
        }
        TextBoxes.typeTextbox("my_profile.address_zip_code", String.valueOf(profileAddress.getZipCode()));
        if (macys()) {
            DropDowns.selectByText("my_profile.gender", user.getGender());
            DropDowns.selectByText("create_profile.security_question", user.getUserPasswordHint().getQuestion());
            TextBoxes.typeTextbox("create_profile.security_answer", user.getUserPasswordHint().getAnswer());
        }
        if (edge()) {
            Clicks.javascriptClick("my_profile.update_profile_button");
        } else {
            //Clicks.javascriptClick("my_profile.update_profile_button");
            Clicks.click("my_profile.update_profile_button");
        }
        if (macys()) {
            Assert.assertTrue("Profile not updated", Wait.untilElementPresent("my_profile.update_message"));
        } else {
            Assert.assertTrue("Could not update profile", Wait.until(() -> onPage("my_account"), 10));
        }
    }

    public void compareMediaData(String type, Map<String, Object> uiData, Map<String, Object> response) {
        switch (type) {
            case "ad":
            case "banner":
                Assert.assertTrue("Image Name is incorrect!!",
                        uiData.get("name").toString().equals(response.get("name").toString()));
                if (uiData.get("link") != null){
                    Assert.assertTrue("URL is incorrect!!",
                            uiData.get("link").toString().equals((response.get("url").toString().startsWith("/") ? RunConfig.url + response.get("url") : response.get("url")).toString()));
                }
                break;
            case "image_map":
                Assert.assertTrue("Image Name is incorrect!!",
                        uiData.get("name").toString().equals((response.containsKey("data") ? (Map)response.get("data") : response).get("name").toString()));
                Assert.assertTrue("URL's are incorrect!!",
                        (((List<String>)uiData.get("links")).containsAll(
                                ((List<Map>)(response.containsKey("data") ? (Map)response.get("data") : response).get("links")).stream().map(e -> (e.get("url").toString().startsWith("/") ? RunConfig.url + e.get("url") : e.get("url")).toString().replaceAll("'", "%27").replaceAll("\"", "%22")).collect(Collectors.toList()))));
                break;
            case "video":
                Assert.assertTrue("Video image is incorrect!!",
                        uiData.get("name").equals(response.get("name")));
                Assert.assertTrue("Video title is incorrect!!",
                        uiData.get("title").equals(response.containsKey("title") ? response.get("title") : ((Map)((List<Map>)response.get("links")).get(0).get("data")).get("title")));
                break;
            case "category_icon":
                Assert.assertTrue("category_icon name is incorrect!!",
                        uiData.get("name").equals(response.get("name")));
                Assert.assertTrue("category_icon link is incorrect!!",
                        uiData.get("link").equals((response.get("link").toString().startsWith("/") ? RunConfig.url + response.get("link") : response.get("link") )));
                Assert.assertTrue("category_icon text is incorrect!!",
                        uiData.get("text").toString().toLowerCase().equals(response.get("text").toString().toLowerCase()));
                break;
            case "banner_machine":
                Assert.assertTrue("banner_machine image is incorrect!!",
                        response.get("imageName").toString().contains(uiData.get("image").toString()));
                Assert.assertTrue("banner_machine link is incorrect!!",
                        ((List<Map>) response.get("links")).stream().map(e -> e.get("link").toString().startsWith("/") ? RunConfig.url + e.get("link") : e.get("link")).collect(Collectors.toList()).contains(uiData.get("link").toString()));
                Assert.assertTrue("banner_machine link text is incorrect!!",
                        ((List<Map>) response.get("links")).stream().map(e -> e.get("text").toString()).collect(Collectors.toList()).contains(uiData.get("linkText").toString()));
                break;
            case "slideshow":
                Assert.assertTrue("Slides count is incorrect!!",
                        (Integer.parseInt(uiData.get("count").toString()) == ((List)response.get("slides")).size()));
                for (int i = 0; i < ((List<Map>) uiData.get("slides")).size(); i++) {
                    Map slide = ((List<Map>) uiData.get("slides")).get(i);
                    compareMediaData(slide.get("type").toString(), (Map)slide.get("data"), ((List<Map>) response.get("slides")).get(i));
                }
                break;
            case "flexible_links":
                Assert.assertTrue("flexible_links header is incorrect!!",
                        uiData.get("header").equals(response.get("header")));
                Assert.assertTrue("flexible_links link text is incorrect!!",
                        ((List<Map>)response.get("links")).stream().map(e -> e.get("text").toString()).collect(Collectors.toList()).containsAll(((List<String>)uiData.get("linkTexts"))));
                Assert.assertTrue("flexible_links link text is incorrect!!",
                        ((List<Map>) response.get("links")).stream().map(e -> e.get("url").toString().startsWith("/") ? RunConfig.url + e.get("url") : e.get("url")).collect(Collectors.toList()).containsAll(((List<String>) uiData.get("linkHref"))));
                break;
            case "text":
                Assert.assertTrue("text header is incorrect!!",
                        uiData.get("text").equals(response.get("text")));
                break;
            case "horizontalRule":
                System.out.println("-> No data present in horizontalRule");
                break;
            default:
                Assert.fail("ERROR");
        }
    }
}
