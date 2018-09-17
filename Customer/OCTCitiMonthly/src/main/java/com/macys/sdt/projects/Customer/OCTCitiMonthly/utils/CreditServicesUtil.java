package com.macys.sdt.projects.Customer.OCTCitiMonthly.utils;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoginCredentials;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserPasswordHint;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.model.CreditApplyNow;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.model.CreditManageCard;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import static com.macys.sdt.framework.interactions.TextBoxes.typeTextbox;
import static com.macys.sdt.framework.utils.StepUtils.prodEnv;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class CreditServicesUtil {

    /**
     * Gets valid apply now info for CS Apply Now test
     *
     * @return applyNowInfo
     */
    public static CreditApplyNow getApplyNowInfo() {
        File applyNowFile;

        CreditApplyNow applyNowInfo = new CreditApplyNow();
        applyNowFile = getResourceFile("credit_services.json");
        try {
            InputStream is = new FileInputStream(applyNowFile);
            String jsonTxt = IOUtils.toString(is);
            JSONObject json = new JSONObject(jsonTxt);
            JSONObject applyNowJsonData = json.getJSONObject("applyNow");
            applyNowInfo.setFirstName(applyNowJsonData.getString("firstName"));
            applyNowInfo.setLastName(applyNowJsonData.getString("lastName"));
            applyNowInfo.setEmail(applyNowJsonData.getString("email"));
            applyNowInfo.setAddress(applyNowJsonData.getString("address"));
            applyNowInfo.setCity(applyNowJsonData.getString("city"));
            applyNowInfo.setState(applyNowJsonData.getString("state"));
            applyNowInfo.setZipCode(applyNowJsonData.getString("zipCode"));
            applyNowInfo.setPhone(applyNowJsonData.getString("phone"));
            applyNowInfo.setPhoneType(applyNowJsonData.getString("phoneType"));
            applyNowInfo.setResStatus(applyNowJsonData.getString("resStatus"));
            applyNowInfo.setMortgage(applyNowJsonData.getString("mortgage"));
            applyNowInfo.setIncome(applyNowJsonData.getString("income"));
            applyNowInfo.setSsn(applyNowJsonData.getString("ssn"));
            applyNowInfo.setDob(applyNowJsonData.getString("dob"));

        } catch (Exception e) {
            applyNowInfo = null;
            Assert.fail("Unable to read Apply Now Information from file" + e);
        }
        return applyNowInfo;
    }

    /**
     * Gets user profile info to create a new profile
     *
     * @param testNum is test case number
     * @return user
     */
    public static User getProfileInfo(String testNum) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }

        User user = new User();

        ProfileAddress profileInfo = new ProfileAddress();
        profileInfo.setFirstName(TestUsers.generateRandomFirstName());
        profileInfo.setLastName(TestUsers.generateRandomLastName());
        profileInfo.setEmail(getProfileEmail(testNum));
        user.setProfileAddress(profileInfo);

        LoginCredentials credentials = new LoginCredentials();
        credentials.setPassword("!Macys12345");
        user.setLoginCredentials(credentials);
        user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").format(TestUsers.generateRandomDate()));
        user.setUserPasswordHint(UserPasswordHint.getDefaultUserPasswordHint());

        return user;
    }

    /**
     * Get custom user email for Citi testing
     *
     * @param testNum is test case number
     * @return email
     */
    private static String getProfileEmail(String testNum) {

        String email;
        String siteUrl = WebDriverManager.getCurrentUrl();
        //emailPrefix will get the string starting from qa till code from current stage5 url
        String emailPrefix = siteUrl.substring(siteUrl.indexOf("qa"), (siteUrl.indexOf("code") + 4));
        String emailSuffix = siteUrl.contains("macys") ? "@macys.com" : "@bloomingdales.com";

        email = emailPrefix + "_" + testNum + emailSuffix;

        return email;
    }

    /**
     * Gets valid activate card info for CS Activate Card test
     *
     * @return activateInfo
     */
    public static CreditManageCard getActivationInfo(String user) {
        File activateCardFile;
        CreditManageCard activateInfo = new CreditManageCard();
        activateCardFile = getResourceFile("credit_services.json");

        try {
            InputStream is = new FileInputStream(activateCardFile);
            String jsonTxt = IOUtils.toString(is);
            JSONObject json = new JSONObject(jsonTxt);
            JSONObject activateCardJsonData = json.getJSONObject("activateCard").getJSONObject(user);
            activateInfo.setCardNumber(activateCardJsonData.getString("cardNumber"));
            activateInfo.setName(activateCardJsonData.getString("name"));
            activateInfo.setSecurityCode(activateCardJsonData.getString("securityCode"));
            activateInfo.setSsn(activateCardJsonData.getString("ssn"));
        } catch (Exception e) {
            activateInfo = null;
            Assert.fail("Unable to read Activation Information from file" + e);
        }
        return activateInfo;
    }

    /**
     * Gets valid add card info for CS Add Card test
     *
     * @return addCardInfo
     */
    public static CreditManageCard getAddCardInfo(String cardType, boolean authUser) {
        File addCardFile;
        CreditManageCard addCardInfo = new CreditManageCard();
        addCardFile = getResourceFile("credit_services.json");

        try {
            InputStream is = new FileInputStream(addCardFile);
            String jsonTxt = IOUtils.toString(is);
            JSONObject json = new JSONObject(jsonTxt);
            JSONObject addCardJsonData = json.getJSONObject("addCard").getJSONObject(cardType);
            addCardInfo.setCardNumber(addCardJsonData.getString("cardNumber"));
            String name = addCardJsonData.getString("name");
            if (authUser) {
                JSONObject authUserObj = addCardJsonData.getJSONObject("authUser");
                name = authUserObj.getString("lastName") + " " + authUserObj.get("firstName");
            }
            addCardInfo.setName(name);
            addCardInfo.setSecurityCode(addCardJsonData.getString("securityCode"));
            addCardInfo.setSsn(addCardJsonData.getString("ssn"));
            addCardInfo.setEmail(addCardJsonData.getString("email"));
        } catch (Exception e) {
            addCardInfo = null;
            Assert.fail("Unable to read " + cardType + " Add Card Information from file" + e);
        }
        return addCardInfo;
    }

    /**
     * Update email, if citi update your profile page shows up
     */
    public static void creditUpdateProfile(String cardType) {
        try {
            //This is for the profile with one card and trying to add another card to profile
            CreditManageCard updateProfileEmail = getAddCardInfo(cardType, false);

            typeTextbox("credit_update_your_profile.email", updateProfileEmail.getEmail());
            typeTextbox("credit_update_your_profile.confirmEmail", updateProfileEmail.getEmail());
            Clicks.click("credit_update_your_profile.submitEmail");
        } catch (Exception e) {
            Assert.fail("Unable to read " + cardType + " Add Card Information from file" + e);
        }
    }

    /**
     * Gets valid add auth user info for CS Add Auth User test
     * testCase = applyNow | activateCard | addCard | addAuthUser
     *
     * @return addAuthUserInfo
     */
    public static void appendUpdateCardDetails(String testCase, String cardType, JSONObject cardData) {
        File cardFile;
        cardFile = getResourceFile("credit_services.json");

        try {
            InputStream is = new FileInputStream(cardFile);
            String jsonTxt = IOUtils.toString(is);

            JSONObject json = new JSONObject(jsonTxt);
            JSONObject applyNowJson = json.getJSONObject("applyNow");
            JSONObject activateJson = json.getJSONObject("activateCard");
            JSONObject addCardJson = json.getJSONObject("addCard");

            switch (testCase) {
                case "applyNow":
                    break;
                case "activateCard":
                    break;
                case "addCard":
                    JSONObject addCardJsonData = addCardJson.getJSONObject(cardType);
                    Iterator<String> cardDataItr = cardData.keys();
                    while (cardDataItr.hasNext()) {
                        String cardDataKey = cardDataItr.next();
                        addCardJsonData.put(cardDataKey, cardData.getString(cardDataKey));
                    }
                    addCardJson.put(cardType, addCardJsonData);
                    break;
                case "authUser":
                    JSONObject authUserJsonCardData = addCardJson.getJSONObject(cardType);
                    JSONObject authUserJsonData = authUserJsonCardData.getJSONObject("authUser");
                    Iterator<String> authUserItr = cardData.keys();
                    while (authUserItr.hasNext()) {
                        String authUserKey = authUserItr.next();
                        authUserJsonData.put(authUserKey, cardData.getString(authUserKey));
                    }
                    authUserJsonCardData.put("authUser", authUserJsonData);
                    addCardJson.put(cardType, authUserJsonCardData);
                    break;
                default:
                    break;
            }

            json.put("applyNow", applyNowJson);
            json.put("activateCard", activateJson);
            json.put("addCard", addCardJson);

            FileWriter addEmailToJson = new FileWriter(cardFile);
            addEmailToJson.write(json.toString());
            addEmailToJson.flush();
            addEmailToJson.close();
        } catch (Exception e) {
            Assert.fail("Unable to read " + cardType + " Card Information from file" + e);
        }
    }

    /**
     * Gets valid user info for CS Add Auth User test
     *
     * @return authUser
     */
    public static User getAddAuthUserInfo(String cardType) {
        User authUser = new User();
        if (cardType != null) {
            try {
                File cardDataFile = getResourceFile("credit_services.json");
                InputStream is = new FileInputStream(cardDataFile);
                String jsonTxt = IOUtils.toString(is);

                JSONObject json = new JSONObject(jsonTxt);
                JSONObject addCardJson = json.getJSONObject("addCard");
                JSONObject addCardJsonData = addCardJson.getJSONObject(cardType).getJSONObject("authUser");

                ProfileAddress authUserProfile = new ProfileAddress();
                authUserProfile.setFirstName(addCardJsonData.getString("firstName"));
                authUserProfile.setLastName(addCardJsonData.getString("lastName"));
                authUser.setProfileAddress(authUserProfile);
                authUser.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("MM/dd/yyyy").parse(addCardJsonData.getString("dob"))));
            } catch (Exception e) {
                Assert.fail("Unable to read Card Information from file" + e);
            }
        } else {
            ProfileAddress authUserInfo = new ProfileAddress();
            authUserInfo.setFirstName(TestUsers.generateRandomFirstName());
            authUserInfo.setLastName(TestUsers.generateRandomLastName());

            TestUsers.getRandomValidAddress(null, authUserInfo);
            authUserInfo.setAddressLine1(authUserInfo.getAddressLine1());
            authUserInfo.setCity(authUserInfo.getCity());
            authUserInfo.setState(authUserInfo.getState());
            authUserInfo.setZipCode(authUserInfo.getZipCode());
            authUser.setProfileAddress(authUserInfo);
            authUser.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").format(TestUsers.generateRandomDate()));
        }
        return authUser;
    }
}
