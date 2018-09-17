package com.macys.sdt.projects.Marketing.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Created by u065629 on 6/2/2017.
 */
public class BannerSteps {
    @Then("^I should see header banner with all the elements on Home Page$")
    public void I_should_see_header_banner_with_all_the_elements(){
        //Sometime the page loads and the top scrolls into view. The link is not immediately available
        //so wait up to 5 seconds for it.
        Wait.secondsUntilElementPresent("header.bloomingdales_registry_logo",5);
        Assert.assertTrue("Registry logo is not displayed", Elements.anyPresent("header.bloomingdales_registry_logo"));
        Assert.assertTrue("Account link is not present",Elements.anyPresent("header.goto_my_account_link"));
        Assert.assertTrue("Shopping bag is not present",Elements.anyPresent("header.goto_shopping_bag"));
        Assert.assertTrue("Stores link is not present",Elements.anyPresent("header.goto_stores"));
        Assert.assertTrue("Search button is not present",Elements.anyPresent("header.search_button"));
        Assert.assertTrue("Search field is not present",Elements.anyPresent("header.search_field"));
    }
}
