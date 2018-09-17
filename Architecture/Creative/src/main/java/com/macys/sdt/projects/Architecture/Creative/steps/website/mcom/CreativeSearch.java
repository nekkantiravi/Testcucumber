package com.macys.sdt.projects.Architecture.Creative.steps.website.mcom;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;

public class CreativeSearch {

    @Given("^I verify sign in landing page$")
    public void i_verify_sign_in_ladning_page() throws Throwable {
        shouldBeOnPage("home");
        Elements.elementShouldBePresent("header.goto_sign_out_link");
        Elements.elementShouldBePresent("header.user_first_name");

    }

    @Given("^I click on create profile button$")
    public void i_click_on_create_profile_button() throws Throwable {
        Clicks.click("sign_in.create_profile");
    }


    @Then("^I validate the search url$")
    public void i_validate_the_search_url() throws Throwable {
        Assert.assertTrue("ERROR APP: Search URL is not correct", WebDriverManager.getCurrentUrl().contains("/shop/featured/"));
}

    @Then("^I should see \"([^\"]*)\" in search landing page$")
    public void i_should_see_in_search_landing_page(String keyword) throws Throwable {
        try {
            Wait.forPageReady();
            String displayedKeyword = Elements.findElement("search_result.search_keyword").getText().trim();
            Assert.assertTrue("Expected Page with keyword: " + keyword + " Actual displayed keyword: " + displayedKeyword,
                    keyword.equalsIgnoreCase(displayedKeyword));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + keyword + " is not displayed" + e.getMessage());
        }

    }


    @Then("^I validate the landing page$")
    public void iValidateTheLandingPage() throws Throwable {
        shouldBeOnPage("store_locations");
    }

    @And("^I click on 'Deals' link in the header$")
    public void iClickOnDealsLinkInTheHeader() throws Throwable {
        shouldBeOnPage("deals_and_promotions");

    }
}