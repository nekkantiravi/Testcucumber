package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortAndFilterBy extends StepUtils {
    private static final Logger log = LoggerFactory.getLogger(SortAndFilterBy.class);
    static String strFirstBrand = null ;
    static String strSecondBrand = null;
    /**
     * Method to verify presence Pick Up In-Store in Filter By option of Sort and Filter
     */
    @And("^I verify \"([^\"]*)\" on sort and filter page$")
    public void verifyPickUpInStoreOnSortAndFilter(String pickup) throws Throwable {
        Wait.untilElementPresent("sort_and_filter.clear_all_btn");
        String text= Elements.getText("sort_and_filter.filter_by_pickup_instore");
        Assert.assertTrue("Pick Up In-Store is not present on Sort and Filter page", pickup.equals(text));
        log.info("Pick Up In-Store is present on Sort and Filter page");
    }

    @When("^I select multiple facet within a single facet category$")
    public void iSelectMultipleFacetWithinSingleFacetCategory()
    {
        Clicks.click("sort_and_filter.brand_facet");
        strFirstBrand = Elements.findElement("sort_and_filter.brand_1").getText();
        strSecondBrand = Elements.findElement("sort_and_filter.brand_2").getText();
        Clicks.click("sort_and_filter.brand_1");
        Clicks.click("sort_and_filter.brand_2");
        Clicks.click("sort_and_filter.apply_button");
    }
}
