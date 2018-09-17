package com.macys.sdt.projects.Selection.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by gzanin on 5/31/17.
 */
public class DSVWishListSteps {
    private static final Logger logger = LoggerFactory.getLogger(DSVRegistrySteps.class);

    @When("^I add a random product to WishList$")
    public void i_add_a_random_product_to_WishList() throws Throwable {
//        DSVPDPActions.closePopUp();
        HashMap<String, Object> productHash = new HashMap<String, Object>()
        {{
                put("available", true);
                put("member_product", true);
            }};
        Product p = TestUsers.getRandomProduct(productHash);
        int productId = p.id;
        TextBoxes.typeTextNEnter("header.search_field", Integer.toString(productId));
        Wait.untilElementPresent("product_display.add_to_wishlist_image");
        Clicks.click("product_display.add_to_wishlist_image");
    }

    @When("^I select sign in link from wishlist page$")
    public void iSelectSignInLinkFromWishlistPage() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("dsv_registry.wishlist_signin_button",20);
    }

    @Then("^I should see Sign In Page$")
    public void iShouldSeeSignInPage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR - ENV: Not a SignIn page", WebDriverManager.getWebDriver().getCurrentUrl().contains("signin"));
        Assert.assertTrue("ERROR - SignIn page is not Displayed", Elements.elementPresent("dsv_registry.verify_signin_page"));
        logger.info("Sucessfully Navigated to Sign In page");
    }

    @Then("^I should see \"([^\"]*)\" page is rendered$")
    public void iShouldSeePageIsRendered(String arg1) throws Throwable {
        Assert.assertTrue("ERROR:- Not on My Acoount Page ", WebDriverManager.getWebDriver().getCurrentUrl().contains(arg1));
    }

    @When("^I select wishlist button on the wishlist overlay in PDP(?: page)?$")
    public void ISelectWishlistButtonOnTheWishlistOverlayInPdpPage() {
        Wait.secondsUntilElementPresentAndClick("product_display.wishlist_link", 5);
        Navigate.scrollPage(0,100);
        Wait.secondsUntilElementPresentAndClick("product_display.link_list", 10);
    }

    @Then("^I select a random product$")
    public void iNavigateToARandomProduct() throws Throwable {
        CommonUtils.navigateToRandomProduct();
    }

}
