package com.macys.sdt.projects.Discovery.UFT.steps.mew;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SearchAndBrowse extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);

    /**
     * Method to view text LAST ACT on all products on search result page
     */
    @And("^I should see LAST ACT message on all the products of SRP")
    public void I_should_see_text_on_all_products_SRP() throws Throwable {
        Wait.untilElementPresent("search.last_act_text_srp");
        List<WebElement> lastActTextOfProducts = Elements.findElements(Elements.element("search.last_act_text_srp"));
        for (WebElement product : lastActTextOfProducts) {
            Assert.assertTrue("All the Products do not contain text LAST ACT", product.getText().contains("LAST ACT"));
            log.info("Verified that LAST ACT is displayed on all products on SRP");
        }
    }

    @Then("^I should see \"([^\"]*)\" text above googleads$")
    public void verifyUnaffiliatedSiteText(String expectedText) throws Throwable {
        scrollToLazyLoadElement("search_browse.site_ads_text");
        String actualText = Elements.getText("search_browse.site_ads_text");
        Assert.assertEquals("Error Unaffiliated site text is not seeing", expectedText, actualText);
        log.info("successfully see Unaffiliated site text");
    }
}


