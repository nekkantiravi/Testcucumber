package com.macys.sdt.projects.Customer.RestAssuredUnifiedLogin.steps.responsive.mcom;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Created by yh00462 on 11/17/17.
 */
public class UnifiedLoginXapiSteps {
    public Response response = null;

    @Then("^I execute pre sign in xAPI get call$")
    public void i_execute_pre_sign_in_xAPI_get_call() throws Throwable {
      //  UtilUnifiedLoginXapi utilUnifiedLoginXapi = new UtilUnifiedLoginXapi();
        //response = utilUnifiedLoginXapi.getPreSigninInXapiResponse();
    }

    @And("^I should validate the Pre-signIn xAPI response status code and body message$")
    public void iShouldValidateThePreSignInXAPIResponseStatusCodeAndBodyMessage() throws Throwable {
            Assert.assertEquals("Failed to validate unified login Pre-Sign in xapi get call content type", response.getContentType(), "application/json");
            Assert.assertEquals("Failed to validate unified login Pre-Sign in xapi get call Status code ", response.getStatusCode(), 200);
            Assert.assertTrue("Failed to validate unified login Pre-Sign in xapi get call body Status msg ", response.print().toString().contains("success"));
        }

    @Then("^I execute post sign in xAPI get call$")
    public void iExecutePostSignInXAPIGetCall() throws Throwable {
        // UtilUnifiedLoginXapi utilUnifiedLoginXapi = new UtilUnifiedLoginXapi();
        // response = utilUnifiedLoginXapi.postSigninInXapiResponse(utilUnifiedLoginXapi.getPostSigninJsonPayLoad());
    }

    @And("^I should validate the Post-signIn xAPI response status code and body message$")
    public void iShouldValidateThePostSignInXAPIResponseStatusCodeAndBodyMessage() throws Throwable {
        Assert.assertEquals("Failed to validate unified login Post-Sign in xapi get call content type", response.getContentType(), "application/json");
        Assert.assertEquals("Failed to validate unified login Post-Sign in xapi get call Status code ", response.getStatusCode(), 200);
        Assert.assertTrue("Failed to validate unified login Post-Sign in xapi get call body Status msg ", response.print().toString().contains("success"));
    }
}
