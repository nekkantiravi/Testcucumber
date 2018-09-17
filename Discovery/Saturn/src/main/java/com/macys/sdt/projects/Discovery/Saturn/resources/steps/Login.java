package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.projects.Discovery.Saturn.resources.utils.CommonUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static com.macys.sdt.framework.interactions.TextBoxes.typeTextbox;

public class Login {

    @Given("^I login to Saturn as an \"([^\"]*)\" user$")
    public void i_login_to_Saturn_as_an_user(String credentials) throws Throwable {
        Navigate.visit(RunConfig.url);
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials = CommonUtils.getCredentials(credentials);
        typeTextbox("login.uname", (String) loginCredentials.get("username"));
        typeTextbox("login.password", (String) loginCredentials.get("password"));
        Clicks.clickElementByText("login.submit", "Login");
    }

    @Then("^I log out of Saturn$")
    public void i_log_out_of_Saturn() throws Throwable {
        Clicks.click("home.log_out");
    }

    @Given("^I login to Stella as an \"([^\"]*)\" user$")
    public void iLoginToStellaAsAnUser(String credentials) throws Throwable {
        Navigate.visit(RunConfig.url);
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials = CommonUtils.getCredentials(credentials);
        typeTextbox("login.stella_uname", (String) loginCredentials.get("username"));
        typeTextbox("login.stella_pwd", (String) loginCredentials.get("password"));
        Clicks.clickElementByText("login.stella_submit", "Login");
    }
}
