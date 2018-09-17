package com.macys.sdt.projects.Customer.RestAssuredCreateProfile.util;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yh00462 on 10/26/17.
 */
public class UtilCreateProfileXapi {
    private static final Logger logger = LoggerFactory.getLogger(UtilCreateProfileXapi.class);

    String ApiUrI = "/account-xapi/api/user/profile";

    public String getCreateDefaultProfileJsonPayLoad() throws Throwable{
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();

        String createProfileJson = "{\n" +
                "  \"user\": {\n" +
                "    \"dateOfBirth\": \"1989-12-19\",\n" +
                "    \"profileAddress\": {\n" +
                "      \"firstName\": \"Amol\",\n" +
                "      \"lastName\": \"Ray\",\n" +
                "      \"email\": \""+user.getProfileAddress().getEmail()+"\"\n" +
                "    },\n" +
                "    \"loginCredentials\": {\n" +
                "      \"password\": \"test123\"\n" +
                "    },\n" +
                "    \"subscriptions\": [\n" +
                "      {\n" +
                "        \"id\": \"1\",\n" +
                "        \"name\": \"This is Email Newsletter \",\n" +
                "        \"value\": \"Y\",\n" +
                "        \"active\": \"true\",\n" +
                "        \"acquisitionSource\": \"Macys\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        logger.info("Create Profile josn payload :- "+createProfileJson);
        System.out.println("Create Profile xAPI Json Payload :- "+createProfileJson);
        return createProfileJson;

    }

    public String getCreateProfileJsonPayLoad(User user) throws Throwable{
                String createProfileJson = "{\n" +
                "  \"user\": {\n" +
                "    \"dateOfBirth\": \""+user.getDateOfBirth()+"\",\n" +
                "    \"profileAddress\": {\n" +
                "      \"firstName\": \""+user.getProfileAddress().getFirstName()+"\",\n" +
                "      \"lastName\": \""+user.getProfileAddress().getLastName()+"\",\n" +
                "      \"email\": \""+user.getProfileAddress().getEmail()+"\"\n" +
                "    },\n" +
                "    \"loginCredentials\": {\n" +
                "      \"password\": \""+user.getLoginCredentials().getPassword()+"\"\n" +
                "    },\n" +
                "    \"subscriptions\": [\n" +
                "      {\n" +
                "        \"id\": \"1\",\n" +
                "        \"name\": \"This is Email Newsletter \",\n" +
                "        \"value\": \"Y\",\n" +
                "        \"active\": \"true\",\n" +
                "        \"acquisitionSource\": \"Macys\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        logger.info("Create Profile josn payload :- "+createProfileJson);
        System.out.println("Create Profile xAPI Json Payload :- "+createProfileJson);
        return createProfileJson;

    }
    public String getCreateProfileEmptyJsonPayLoad() throws Throwable{
        String createProfileJson = "\t{\n" +
                "\t  \"user\": {\n" +
                "\t    \"dateOfBirth\": \"\",\n" +
                "\t    \"profileAddress\": {\n" +
                "\t      \"firstName\": \"\",\n" +
                "\t      \"lastName\": \"\",\n" +
                "\t      \"email\": \"\"\n" +
                "\t    },\n" +
                "\t    \"loginCredentials\": {\n" +
                "\t      \"password\": \"\"\n" +
                "\t    },\n" +
                "\t    \"subscriptions\": [\n" +
                "\t      {\n" +
                "\t        \"id\": \"1\",\n" +
                "\t        \"name\": \"This is Email Newsletter \",\n" +
                "\t        \"value\": \"Y\",\n" +
                "\t        \"active\": \"true\",\n" +
                "\t        \"acquisitionSource\": \"Macys\"\n" +
                "\t      }\n" +
                "\t    ]\n" +
                "\t  }\n" +
                "\t}\n";
        logger.info("Create Profile josn payload :- "+createProfileJson);
        System.out.println("Create Profile xAPI Json Payload :- "+createProfileJson);
        return createProfileJson;

    }
    public Response postCreateProfileXapiCall(boolean isEmptyData) throws Throwable {
        String jsonPayload = "";
        if (isEmptyData)
            jsonPayload = this.getCreateProfileEmptyJsonPayLoad();
        else
            jsonPayload = this.getCreateDefaultProfileJsonPayLoad();

        Response resp = RestAssured.given().header("Content-Type","application/json")
                .body(jsonPayload).
                        when().post(ApiUrI).
                        then().
                        statusCode(200).extract().response();
        logger.info("Create Profile Response:- "+resp.print());
        System.out.println("Create Profile Response:- "+resp.print());
        return  resp;
    }

    public Response postCreateProfileXapiCall(String jsonPayload) throws Throwable {
        Response resp = RestAssured.given().header("Content-Type","application/json")
                .body(jsonPayload).
                        when().post(ApiUrI).
                        then().
                        statusCode(200).extract().response();
        logger.info("Create Profile Response:- "+resp.print());
        System.out.println("Create Profile Response:- "+resp.print());
        return  resp;
    }
    public Response postCreateDefaultProfileXapiCall() throws Throwable {
        Response resp = RestAssured.given().header("Content-Type","application/json")
                .body(this.getCreateDefaultProfileJsonPayLoad()).
                        when().post(ApiUrI).
                        then().
                        statusCode(200).extract().response();
        logger.info("Create Profile Response:- "+resp.print());
        System.out.println("Create Profile Response:- "+resp.print());
        return  resp;
    }


}
