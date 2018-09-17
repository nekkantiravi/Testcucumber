package com.macys.sdt.projects.Selection.PDP.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class FitPredictorSteps {
    private static final Logger logger = Logger.getLogger(UnavailablePDPSteps.class.getName());

    @And("^I click on any random PDP$")
    public void iClickOnAnyRandomPDP() throws Throwable {
        Wait.untilElementPresent("search_result_product_thumbnails.search_results_thumbnail_wrapper");
        List<WebElement> items = Elements.findElements("search_result_product_thumbnails.search_results_thumbnail_wrapper");
        int randomIndex = new Random().nextInt(items.size());
        WebElement selecteditem = items.get(randomIndex);
        selecteditem.click();
    }

    @Then("^I will verify that Fit Predictor is visible on PDP$")
    public void iwillverifythatFitPredictorisvisibleonPDP() throws Throwable{
        Wait.untilElementPresent("category_browse.fit_predictor_label");
        Assert.assertTrue(Elements.elementPresent("category_browse.fit_predictor_label"));
        Assert.assertEquals(Elements.findElement("category_browse.fit_predictor_label").getText(), "FIT PREDICTOR");
        logger.info("Verified that Fit Predictor is displayed");
    }

}
