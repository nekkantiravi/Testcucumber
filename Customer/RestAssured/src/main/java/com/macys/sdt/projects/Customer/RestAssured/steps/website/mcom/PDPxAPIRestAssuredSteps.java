package com.macys.sdt.projects.Customer.RestAssured.steps.website.mcom;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PDPxAPIRestAssuredSteps extends StepUtils {
    private Response response = null;
    private int product_id = -1;

    @Given("^I set rest api server$")
    public void I_set_rest_api_server() throws Throwable {
        URL url = new java.net.URL(RunConfig.url);
        String host = url.getProtocol() + "://" + url.getHost();
        int port = url.getPort();
        System.out.println("Host = " + host);
        RestAssured.baseURI = host;
        if (port > 0) {
            System.out.println("Port = " + Integer.toString(port));
            RestAssured.port = port;
        }
    }

    @Given("^I set rest api server to \"([^\"]*)\"$")
    public void I_set_rest_api_server_to(String rest_host) throws Throwable {
        RestAssured.baseURI = rest_host;
    }

    @When("^I call service xapi service \"([^\"]*)\"$")
    public void I_call_service_xapi_service(String path) throws Throwable {
        response = RestAssured.given().when().get("/" + path);
        response.then().statusCode(200);
        System.out.println("response = " + response.body().asString());
    }

    @Then("^I verify rest api response has key: \"([^\"]*)\" to be value: \"([^\"]*)\"$")
    public void I_verify_pdp_xapi_service_response_has_product_name(String key, String value) throws Throwable {
        response.then().body(key, equalTo(value));
    }

    @Given("^I set rest api server to \"([^\"]*)\" with port number \"([^\"]*)\"$")
    public void I_set_rest_api_server_to_with_port_number(String rest_host, String port) throws Throwable {
        RestAssured.baseURI = rest_host;
        RestAssured.port = Integer.parseInt(port);
    }

    @When("^I call pdp-xapi service for \"([^\"]*)\" item$")
    public void I_call_pdp_xapi_service_for_product(String prod_id) throws Throwable {
        product_id = Integer.parseInt(prod_id);
        response = RestAssured.given().when().get("/xapi/v1/product/" + prod_id);
        response.then().statusCode(200);
        System.out.println("response = " + response.body().asString());
    }

    @Then("^I verify pdp-xapi service response has product name$")
    public void I_verify_pdp_xapi_service_response_has_product_name() throws Throwable {
        response.then().body("product[0].id", equalTo(product_id));
        String product_name = response.path("product[0].detail.name");
        response.then().body("product[0].detail.name", notNullValue());
        System.out.println("product name = " + product_name);
    }
}
