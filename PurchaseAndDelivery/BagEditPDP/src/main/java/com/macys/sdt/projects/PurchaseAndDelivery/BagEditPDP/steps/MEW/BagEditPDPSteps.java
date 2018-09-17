package com.macys.sdt.projects.PurchaseAndDelivery.BagEditPDP.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.BagEditPDP.actions.MEW.BagEditPDPActions;
import com.macys.sdt.shared.steps.MEW.ShopAndBrowse;
import cucumber.api.java.en.*;
import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BagEditPDPSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(ShopAndBrowse.class);
    public static String recentProduct;

    /*
     This is a temporary method to add experiment cookie until we scale up.
     Once we scale up, this method would no more be needed and can be deleted.
     */
    @And("^I add BagEditPDP experiment cookie$")
    public void addExperimentCookie() throws Throwable {
        String experimentCookie = "2356";
        String controlCookie = "2355";
        String segmentCookie = Cookies.getCookieValue("SEGMENT");

        //If experiment cookie is not already added.
        if(!segmentCookie.contains(experimentCookie)) {
            if (segmentCookie.contains(controlCookie))
                Cookies.removeSegment(controlCookie);
            //add experiment cookie
            Cookies.addSegment(experimentCookie);
        }
    }


    @Then("^I should navigate to bag edit PDP page$")
    public void I_should_navigate_to_bag_edit_PDP_page() throws Throwable {
        shouldBeOnPage("product_display");
    }


    @When("^I click on product image on \"([^\"]*)\" page$")
    public void I_click_on_product_image_at_page(String page) throws Throwable{
        switch(page) {
            case "shopping_bag":
                Clicks.clickIfPresent("shopping_bag.item_image");
                break;
        }
    }

    @When("^I click on \"([^\"]*)\"")
    public void I_click_on_button(String button) throws Throwable{
        recentProduct = BagEditPDPActions.getProductName();
        switch(button) {
            case "Update Bag":
                Clicks.clickIfPresent("product_display.update_bag_button");
                Wait.secondsUntilElementPresent("add_to_bag.checkout", 10);
                Clicks.clickIfPresent("add_to_bag.checkout");
                break;
            case "Add Another Item":
                Clicks.clickIfPresent("product_display.add_another_item_button");
                Wait.secondsUntilElementPresent("add_to_bag.checkout", 10);
                Clicks.clickIfPresent("add_to_bag.checkout");
                break;
            case "Add to Wish List":
                Clicks.clickIfPresent("product_display.add_to_wishlist");
                break;
            case "Add to Registry":
                Clicks.clickIfPresent("product_display.add_to_registry");
                break;
        }
    }


    @When("^product quantity should be updated to \"([^\"]*)\"$")
    public void I_should_see_the_product_quantity_updated_on_bag_page(String expected_qty) throws Throwable{
        String current_qty = Elements.getText("shopping_bag.item_quantity");
        Assert.assertTrue("Product quantity was not updated. Current value: "+current_qty+",   Expected value: "+expected_qty, current_qty.contains(expected_qty));
    }


    @When("^I select a different color on the bag edit PDP page$")
    public void I_select_different_color_on_bag_edit_page() throws Throwable{
        String firstColorSelected = BagEditPDPActions.getSelectedColorName();
        BagEditPDPActions.selectAnotherColorSwatch();
        String secondColorSelected = BagEditPDPActions.getSelectedColorName();

        Assert.assertTrue("Not able to select a different color.", !firstColorSelected.equals(secondColorSelected));
    }


    @And("^I should see a new product added to bag$")
    public void I_should_see_a_new_product_added_to_bag() throws Throwable{
        Assert.assertTrue("The second item of the same product was not added to bag", BagEditPDPActions.isAnotherProductAdded());
    }

    @And("^I should verify that Wish List confirmation overlay displays$")
    public void I_should_verify_that_whishlist_confirmation_overlay_displays() throws Throwable{
        Wait.untilElementPresent("wish_list_overlay.wish_list_overlay");
        Assert.assertTrue("Wish List confirmation overlay was not displayed", Elements.elementPresent("wish_list_overlay.wish_list_overlay"));

        Clicks.click("wish_list_overlay.wish_list_close_overlay");
        logger.info("Wish list overlay was closed with success.");
    }

    @And("^I should verify that product (is not|is) present on the shopping bag page$")
    public void I_should_verify_that_product_is_not_present_on_shopping_bag_page(String option) throws Throwable{
        switch(option){
            case "is not":
                Assert.assertFalse("Product was not removed from shopping bag", BagEditPDPActions.isProductInBag(recentProduct));
                break;
            case "is":
                Assert.assertTrue("Product should be present on shopping bag", BagEditPDPActions.isProductInBag(recentProduct));
                break;
        }
    }



    @Then("^I should verify that I am navigated to the Registry sign in page$")
    public void I_should_verify_that_I_am_navigated_to_the_registry_sign_in_page(){
        Wait.secondsUntilElementPresent("BagEditPDP.registry_sign_in_page", 15);
        Assert.assertTrue("Registry Sign In page is not displayed", Elements.elementPresent("BagEditPDP.registry_sign_in_page"));
    }


    @When("^I click on button (Edit|Remove|Move to Wish List) on shopping bag page$")
    public void I_click_on_button_on_shopping_bag_page(String button) throws Throwable{
        switch(button) {
            case "Edit":
                Clicks.clickIfPresent("shopping_bag.button_edit");
                break;
            case "Remove":
                Clicks.clickIfPresent("shopping_bag.button_remove");
                break;
            case "Move to Wish List":
                Clicks.clickIfPresent("shopping_bag.button_move_to_wish_list");
                break;
        }
    }

}