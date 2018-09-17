package com.macys.sdt.projects.ArchitecturalEnhancement.SSL.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CanonicalTagVerification extends StepUtils {

    @Then("^I verify the canonical tag$")
    public void I_verify_the_canonical_tag() throws Throwable {
        Wait.untilElementPresent("home.canonical_tag");

        List<WebElement> canonicalTags = Elements.findElements("home.canonical_tag");
        System.out.println("canonicalTags  :"+canonicalTags.size());

        if(CollectionUtils.isEmpty(canonicalTags) ){
            System.out.println("Canonical tags is not available in this page..");
            return;
        }
        Boolean canonicalHttpsEnabled = RunConfig.booleanParam("canonicalHttpsEnabled");
        for (WebElement link : canonicalTags) {
            if (link != null){
                System.out.println(" Canonical Tag :"+link.getAttribute("href"));
                if(canonicalHttpsEnabled ){
                   if( !(link.getAttribute("href").startsWith("https://www"))){
                       Assert.fail("Canonical new tag is not available, when canonical KS is :"+canonicalHttpsEnabled);
                   }
                } /*else if(!(link.getAttribute("href").startsWith("http://www1"))) {
                    Assert.fail("Canonical new tag is available, when canonical KS is :"+canonicalHttpsEnabled);
                }*/
            }else{
                Assert.fail("Canonical tags is not available in this page");
            }
        }

    }
}
