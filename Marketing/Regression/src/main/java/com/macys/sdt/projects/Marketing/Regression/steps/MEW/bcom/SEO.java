package com.macys.sdt.projects.Marketing.Regression.steps.mew.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by u063689 on 2/2/2018.
 */
public class SEO {
    private String currentURL;

    @And("^I should see canonical tag references the desktop domain$")
    public void iVerifyAlternateTagUrlReferencesMobilePage() throws UnsupportedEncodingException {
        currentURL = MainRunner.currentURL;
        String[] pattern = currentURL.split("(\\?cm_sp)|(&cm_sp)||(\\?utm_campaign=disable_all)");
        currentURL = pattern[0];
        String alternateURL = URLDecoder.decode(Elements.findElement("header.canonical_tag").getAttribute("href"), "UTF-8");
        Assert.assertTrue("Alternate tag URL does not reference mobile page", alternateURL.startsWith("https://www."));

    }
}
