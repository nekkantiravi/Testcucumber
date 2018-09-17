package com.macys.sdt.projects.Selection.UFT.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Admin on 7/12/2017.
 */
public class ProductDetails {
    private static final Logger log = LoggerFactory.getLogger(ProductDetails.class);

    /**
     * Method to view text LAST ACT on product details page.
     */
    @Then("^I should see \"([^\"]*)\" message on PDP$")
    public void I_should_see_text_on_PDP(String expectedMsg) throws Throwable
    {
        Utils.threadSleep(5000, "Waiting for page to load");
        String actualMessage = Elements.getText("product_details.last_act_text_pdp");
        Assert.assertEquals("LAST ACT text is not displayed on PDP", expectedMsg, actualMessage);
        log.info("Successfully verified text LAST ACT on PDP");
    }

    /**
     * Method to view ratings & reviews on pdp
     */
    @Then("^I should see rating & reviews on pdp$")
    public void I_should_see_ratings_and_reviews_on_pdp() throws Throwable {
        Wait.untilElementPresent("product_details.rating_pdp");
        Wait.untilElementPresent("product_details.reviews_pdp");
        Assert.assertTrue("Rating is not Visible on PDP",
                Elements.elementPresent("product_details.rating_pdp"));
        Assert.assertTrue("Review is not Visible on PDP",
                Elements.elementPresent("product_details.reviews_pdp"));
        log.info("Verified that the rating and reviews are present on PDP");
    }

    /**
     * Method to click write review link
     */
    @And("^I click on write review link")
    public void I_click_on_WriteReview() throws Throwable {
        Wait.untilElementPresent("product_details.write_review");
        Clicks.click("product_details.write_review");
        log.info("Verified that Write Review button is clicked successfully");
    }
}



