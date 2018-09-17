package com.macys.sdt.projects.Marketing.SeoSLP.steps.mew.mcom;

import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.macys.sdt.projects.Marketing.SeoSLP.utils.DBSeoSlp;
import com.macys.sdt.projects.Marketing.SeoSLP.utils.SeoSLP;

/**
 * Created by dbodige on 12/7/2017.
 */
public class SEORegression  extends StepUtils {

    String browserTitle;

    private static final Logger logger = LoggerFactory.getLogger(SEORegression.class);


    @Then("^I should see browser page title matches with Stella page title$")
    public void iShouldSeeBrowserPageTitleMatchesWithStellaPageTitle() throws Throwable {
        String dbPageTitle = DBSeoSlp.getPageTitle(SeoSLP.getCategoryID());
        String browserPageTitle = title().replace("- Macy's", "");
        Assert.assertTrue("ERROR - APP browser page title is not matching with stella page title", browserPageTitle.contains(dbPageTitle));
    }

    @Then("^I should see selected facet value \"([^\"]*)\" in title tag$")
    public void iShouldSeeSelectedFacetValueInTitleTag(String selected_facet_value) throws Throwable {
        Assert.assertTrue("ERROR - APP browser page title contains selected crawlable facet value", title().contains(selected_facet_value));
    }

    @Then("^I should see browser page title does not contain facet value \"([^\"]*)\"$")
    public void iShouldSeeBrowserPageTitleDoesNotContainFacetValue(String facet_value) throws Throwable {
        Assert.assertFalse("ERROR - APP browser page title contains price facet value", title().contains(facet_value));
    }
}
