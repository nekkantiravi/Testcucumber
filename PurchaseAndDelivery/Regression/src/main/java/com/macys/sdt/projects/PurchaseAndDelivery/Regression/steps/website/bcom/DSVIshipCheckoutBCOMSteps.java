package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.IShipCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.utils.CheckoutUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class DSVIshipCheckoutBCOMSteps {

    private static final Logger logger = LoggerFactory.getLogger(DSVIshipCheckoutBCOMSteps.class);

    @Given("^I navigate to a category browse page that has facets$")
    public void iNavigateToACategoryBrowsePageThatHasFacets() {
        JSONObject categoriesList = getFileDataInJson(getResourceFile("category.json"));
        JSONArray categories = categoriesList.getJSONArray("category_with_facets");
        String category = categories.get(new Random().nextInt(categories.length())).toString();
        Navigate.visit(RunConfig.url + "/shop/?id=" + category);
        logger.info("Navigated to category browse " + category);
    }

    @And("^I navigate to any category browse page$")
    public void iNavigateToAnyCategoryBrowsePage() {
        Home.selectRandomCategory();
        String subCategory = Home.selectRandomSubCategory();
        logger.info("Navigated to random category browse page: " + subCategory);
    }

    @And("^I navigate to a random member product$")
    public void iNavigateToARandomMemberProduct() {
        CommonUtils.selectRandomProduct(false, false);
        logger.info("Navigated to random member product");
    }

    @Then("^I navigate to envoy checkout from shopping bag page$")
    public void iNavigateToEnvoyCheckoutFromShoppingBagPage() {
        new CheckoutUtils().navigateToCheckout(false, true);
        IShipCheckout checkout = Navigate.get(IShipCheckout.class);
        Assert.assertTrue("Not on iship checkout page", StepUtils.onPage(checkout.pageAlias()));
        Assert.assertTrue("shipping_iFrame not present on iship_checkout page",
                checkout.shippingFrame.exists());
    }

    @Then("^I make sure I am on the shopping bag page$")
    public void iMakeSureIAmOnTheShoppingBagPage() {
        StepUtils.shouldBeOnPage("shopping_bag");
    }

    @And("^I add the \"([^\"]*)\" cookie value as \"([^\"]*)\"$")
    public void iAddTheCookieValueTo(String setCookieName, String value) {
        Wait.forPageReady();
        Cookies.addCookie(setCookieName, value);
    }

    @Then("^I verify that \"([^\"]*)\" cookie is populated on the shopping bag page as \"([^\"]*)\"$")
    public void iVerifyThatCookieIsPopulatedOnTheShoppingBagPageAs(String cookieName, String cookieValue) {
        Navigate.to(ShoppingBag.class);
        Assert.assertEquals("The currency cookie is not as expected", cookieValue, Cookies.getCookieValue(cookieName));
    }

}
