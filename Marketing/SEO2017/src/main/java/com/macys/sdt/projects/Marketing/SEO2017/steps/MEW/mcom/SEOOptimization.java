package com.macys.sdt.projects.Marketing.SEO2017.steps.MEW.mcom;


import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class SEOOptimization extends StepUtils {

    @Then("^I should see \"([^\"]*)\" attribute is appended in robots meta tag in view source page$")
    public void iShouldSeeAttributeIsAppendedInRobotsMetaTagInViewPageSource(String expectedValues) throws Throwable {
        Assert.assertTrue("ERROR APP: Expected Meta content is not displaying", Elements.findElement("dynamic_landing.metaName").getAttribute("content").equals(expectedValues));
    }
}
