package com.macys.sdt.projects.Customer.RestAssuredUnifiedLogin.util;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.shared.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /* Created by YH00420 on 12/14/2017.
 */
public class UtilUnifiedLoginXapi {
    private static final Logger logger = LoggerFactory.getLogger(UtilUnifiedLoginXapi.class);

    String ApiUrI = "/account-xapi/api/user/profile";

    public String getPostSigninJsonPayLoad() throws Throwable{
        UserProfile userProfile = CommonUtils.createNewUserProfile();
        User user = userProfile.getUser();
        String PostSigninJson = "{ \n" +
                "   \"SignIn\":{ \n" +
                "      \"email\":\""+user.getProfileAddress().getEmail()+"\",\n" +
                "      \"password\":\""+user.getLoginCredentials().getPassword()+"\n" +
                "   }\n" +
                "}";
        logger.info("Post Signin payload :- "+PostSigninJson);
        System.out.println("Post Signin xpai json payload :- "+PostSigninJson);
        return PostSigninJson;

    }

    public Response getPreSigninInXapiResponse() throws Throwable {
        Response resp = RestAssured.given().header("Content-Type", "application/json")
                .body("")
                .when()
                .post(ApiUrI)
                .then().statusCode(200).extract().response();
        logger.info("Pre SigninIn Xapi Response:- " + resp.print());
        System.out.println("Pre SigninIn Xapi Response:- " + resp.print());
        return resp;
    }

    public Response postSigninInXapiResponse(String PostSigninJson){
        Response resp = RestAssured.given().header("Content-Type","application/json")
                .body(PostSigninJson)
                .when()
                .post(ApiUrI)
                .then().statusCode(200).extract().response();
        logger.info("Post SigninIn Xapi Response:-"+resp.print());
        System.out.println("Post SigninIn Xapi Response:- " + resp.print());
        return resp;

    }}
