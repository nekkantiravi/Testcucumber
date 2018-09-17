package com.macys.sdt.projects.Discovery.Saturn.resources.steps;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.When;

/**
 * Created by M671871 on 8/10/2017.
 */
public class SaturnHomePage {


    @When("^I navigate to \"([^\"]*)\" under Administration$")
    public void i_navigate_to_under_Administration(String administrationType) throws Throwable {
        Wait.forPageReady();
        Clicks.hoverForSelection("home.administration");
        switch (administrationType) {
            case "Linguistics":
                Clicks.hoverForSelection("home.linguistics");
                break;
            case "HAD":
                Clicks.javascriptClick("home.had");
                break;
            case "Autocomplete":
                Clicks.hoverForSelection("home.autocomplete");
                break;
            case "New Autocomplete":
                Clicks.hoverForSelection("home.new_autocomplete");
                break;
        }
    }

    @When("^I click on Linguistics \"([^\"]*)\"$")
    public void i_click_on_Linguistics(String linguisticType) throws Throwable {
        switch (linguisticType) {
            case "Terms":
                Clicks.javascriptClick ("home.terms");
                Wait.ajaxDone();
                break;
            case "Dictionaries":
                Clicks.javascriptClick("home.dictionaries");
                Wait.ajaxDone();
                break;
            case "Linguistics Bulk Upload":
                Clicks.javascriptClick("home.linguistics_bulk_upload");
                break;
        }
        Wait.forPageReady();
    }

    @When("^I navigate to \"([^\"]*)\" under Rules$")
    public void i_navigate_to_under_Rules(String type) throws Throwable {
        Wait.forPageReady();
        Clicks.hoverForSelection("home.rules");
        switch (type) {
            case "Advanced Search":
                Clicks.javascriptClick("home.advanced_search");
                break;
            case "Create Rule":
                Clicks.javascriptClick("home.create_rule");
                break;
            case "Find Rules":
                Clicks.javascriptClick("home.find_rules");
                Wait.ajaxDone();
                break;
            case "Create Trigger Group":
                Clicks.javascriptClick("home.create_trigger_group");
                break;
            case "Find Trigger Groups":
                Clicks.javascriptClick("home.find_trigger_groups");
                Wait.ajaxDone();
                break;
        }
        Wait.forPageReady();
    }

}
