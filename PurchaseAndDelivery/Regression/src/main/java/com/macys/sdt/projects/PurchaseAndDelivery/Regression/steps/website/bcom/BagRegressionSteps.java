package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.website.bcom;

import com.macys.sdt.framework.model.Promotion;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.Header;
import com.macys.sdt.projects.PurchaseAndDelivery.Regression.actions.website.PromotionsActions;
import com.macys.sdt.projects.PurchaseAndDelivery.Regression.actions.website.ShoppingBagActions;
import org.slf4j.Logger;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.LoggerFactory;


public class BagRegressionSteps {

    private static final Logger logger = LoggerFactory.getLogger(BagRegressionSteps.class);
    private Promotion promotion;

    @When("^I access the bag page$")
    public void iAccessTheBagPage() {
        logger.info("Accessing bag page");
        Header header = Navigate.get(Header.class);
        header.shoppingBagIcon.click();
        StepUtils.shouldBeOnPage("shopping_bag");
        logger.info("On pag page");
    }

    @And("^I make sure I'm \"([^\"]*)\" with \"([^\"]*)\" for \"([^\"]*)\" promotion with \"([^\"]*)\" triggers$")
    public void iMakeSureIMWithForPromotionWithTriggers(String thresholdStatus, String products, String promoType,
                                                        String triggers) {
        logger.info("Adding products to the bag");
        promotion = PromotionsActions.processThresholdStatus(thresholdStatus, products, promoType, triggers);
        logger.info("Products were added to the bag successfully");
    }

    @Then("^I should see the expected messages at the line item level$")
    public void iShouldSeeTheExpectedMessageInTheLineItemLevel() {
        logger.info("Checking line item messages");
        ShoppingBagActions.checkLineItemMessages(promotion);
        logger.info("Checked line item messages");
    }

}
