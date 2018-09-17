package com.macys.sdt.projects.Selection.List.steps.website;

/**
 * Created by m657444 on 8/10/17.
 */

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import com.macys.sdt.framework.runner.RunConfig;
import java.util.logging.Logger;

public class UserLogin extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static String productId = "";
    SoftAssertions softly = new SoftAssertions();


    @And("^I navigate directly to list by url$")
    public void I_launch_responsive_list_page_url() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/mylist");


    }

    @When("^I navigate directly to responsive list page for \"([^\"]*)\" user$")
    public void I_navigate_by_listguid_url(String user_type) throws Throwable {
        if (user_type.equals("Guest")) {
            Navigate.visit(RunConfig.url + "/wishlist/mylist?listGuid=7025d56b-ce42-4301-9635-4bfad8a041b7");
        }
        else if(user_type.equals("Signed In")) {
            Navigate.visit(RunConfig.url + "/wishlist/mylist?listGuid=b4d905de-f479-4a2a-ad56-63fbf4cc880");
        }
    }


    @When("^I navigate directly to responsive list page with listguid$")
    public void I_navigate_by_listguid_url_with_listguid() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/mylist?listGuid=170d003f-4ded-411a-8563-c1462a64e916");
    }

    @When("^I navigate directly to responsive list page with listguid for delete$")
    public void I_navigate_by_listguid_url_with_listguid_for_delete() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/mylist?listGuid=6b759d41-5cce-4bf8-8d58-df2791c23ed9");
    }

    @And ("^I click on List link in the header$")
    public void I_navigate_to_default_listpage() throws Throwable {
//        Elements.findElement("category_menu.goto_wishlist").click();
        Elements.findElement("header.goto_wishlist").click();
    }

    @And("^I navigate directly to responsive list page for database user$")
    public void I_navigate_to_database_user() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/mylist?listGuid=73289634-7ac3-49cc-86df-27dc9d57234f");
    }
}

