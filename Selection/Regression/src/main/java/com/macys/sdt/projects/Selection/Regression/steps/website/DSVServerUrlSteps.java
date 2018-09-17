package com.macys.sdt.projects.Selection.Regression.steps.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yc05165 on 04/08/17.
 */
public class DSVServerUrlSteps {

    private static final Logger logger = LoggerFactory.getLogger(DSVServerUrlSteps.class);

    @Then("^the URL should be in \"([^\"]*)\" format$")
    public void iShouldSeeURLBeInFormat(String args) {
        Wait.forPageReady();
        assertTrue("ERROE:- Url format is wrong", WebDriverManager.getCurrentUrl().contains("/shop") || WebDriverManager.getCurrentUrl().contains("/buy"));
        logger.info("The URL is in correct format");
    }
}
