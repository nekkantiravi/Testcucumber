package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.IShipCheckout;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abhishek on 7/18/2017.
 */
public class DSVIshipSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(DSVIshipSteps.class);

    @Then("^I verify the basic attributes of Iship Home page$")
    public void i_verify_the_basic_attributes_of_Iship_Home_page() throws Throwable {
        //verify the accounts button is not visible in iship home page
        Assert.assertFalse("ERROR - ENV: My account link displayed in iship mode",
                Elements.elementPresent("home.goto_my_account_link"));
        Assert.assertFalse("ERROR - ENV: Beauty category displayed in iship mode",
                new Home().getAllMainCategoryNames().contains("BEAUTY"));
        Assert.assertTrue("ERROR - ENV: Currency element not displayed in iship mode",
                Elements.elementPresent("header.iship_currency"));
        logger.info("Verified International Home page");
    }

    @Then("^I verify the currency is AUD on the (PDP|category browse|shopping bag|Border Free|Add to bag) page$")
    public void i_verify_the_currency_is_AUD_on_page(String page_type) throws Throwable {
        switch (page_type) {
            case "PDP": {
                logger.info("Verify currency is AUD on PDP");
                Wait.untilElementPresent("product_thumbnails.product_price_pdp");
                logger.info("Price Displayed is::" +Elements.getText("product_thumbnails.product_price_pdp"));
                Assert.assertTrue("Price Tag on PDP page does not contain AUD", Elements.getText("product_thumbnails.product_price_pdp").contains("AUD "));
                break;
            }
            case "category browse": {
                logger.info("Verify currency is AUD on Category browse");
                Wait.untilElementPresent("iship.browse_prices");
                logger.info("Price Displayed is::" +Elements.getText("iship.browse_prices"));
                Assert.assertTrue("Price Tag on Category browse does not contain AUD", Elements.getText("iship.browse_prices").contains("AUD "));
                break;
            }
            case "Add to bag": {
                logger.info("Verify currency is AUD on Add to bag");
                Wait.untilElementPresent("iship.overlay_price");
                logger.info("Price Displayed is::" +Elements.getText("iship.overlay_price"));
                Assert.assertTrue("Price Tag on Add to bag does not contain AUD", Elements.getText("iship.overlay_price").contains("AUD "));
                Wait.untilElementPresent("iship.overlay_total_price");
                logger.info("Price Displayed is::" +Elements.getText("iship.overlay_total_price"));
                Assert.assertTrue("Price Tag on Add to bag does not contain AUD", Elements.getText("iship.overlay_total_price").contains("AUD "));
                Wait.untilElementPresent("iship.overlay_subtotal_price");
                logger.info("Price Displayed is::" +Elements.getText("iship.overlay_subtotal_price"));
                Assert.assertTrue("Price Tag on Add to bag does not contain AUD", Elements.getText("iship.overlay_subtotal_price").contains("AUD "));
                break;
            }
            case "shopping bag": {
                logger.info("Verify currency is AUD on shopping bag");
                Wait.untilElementPresent("shopping_bag.subtotal_currencty");
                logger.info("Price Displayed is::" +Elements.getText("shopping_bag.subtotal_currencty"));
                Assert.assertTrue("Price Tag on Shopping bag does not contain AUD", Elements.getText("shopping_bag.subtotal_currencty").contains("AUD"));
                break;
            }
            case "Border Free": {
                logger.info("Verify currency is AUD on Border Free");
                IShipCheckout iShip = Navigate.get(IShipCheckout.class);
                Wait.until(iShip.shippingFrame::exists);
                switchToFrame("iship_checkout.shipping_iFrame");
                Wait.until(iShip.orderTotal::exists, 10);
                logger.info("Price Displayed is::"  + iShip.orderTotal.getText());
                Assert.assertTrue("Price Tag on Border Free does not contain AUD", iShip.orderTotal.getText().contains("AUD"));
                switchToFrame("default");
                break;
            }
            default: {
                logger.info("No page type selected");
            }

        }


    }
}
