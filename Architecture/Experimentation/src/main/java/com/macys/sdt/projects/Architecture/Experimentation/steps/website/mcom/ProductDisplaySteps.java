package com.macys.sdt.projects.Architecture.Experimentation.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages.ProductDetailsPage;
import com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages.ShoppingBag;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;


/**
 *
 * Created by Admin on 5/17/2017.
 */
public class ProductDisplaySteps {

    @Then("^I should be product description page with add to bag and add to list button$")
    public void i_should_be_product_description_page_with_add_to_bag_and_add_to_list_button() throws Throwable {
        Assert.assertTrue(ProductDetailsPage.verifyIsPdpPage());
        ProductDetailsPage.verifyPdpProductName();
        ProductDetailsPage.verifyPdpAddToBagButton();
        ProductDetailsPage.verifyPdpAddToListButton();
    }
    @Then("^I select the size of the product$")
    public void i_select_the_size_of_the_product() throws Throwable {
        ProductDisplay.selectRandomSize();
    }

    @Then("^I click on \"([^\"]*)\" button$")
    public void i_click_on_button(String arg1) throws Throwable {
        ProductDetailsPage.clickAddToBagButton();
    }

    @Then("^I should be on ATB page with go back and checkout button$")
    public void i_should_be_on_ATB_page_with_go_back_and_checkout_button() throws Throwable {
        ProductDetailsPage.verifyBagPageAttribute();
    }

    @Then("^I should be on product details page on clicking go back button$")
    public void i_should_be_on_product_details_page_on_clicking_go_back_button() throws Throwable {
        ProductDetailsPage.clickOnGoBackButton();
        Assert.assertTrue(ProductDetailsPage.verifyIsPdpPage());
    }

    @Then("^I should be on shopping bag page on clicking checkout button$")
    public void i_should_be_on_shopping_bag_page_on_clicking_checkout_button() throws Throwable {
        ProductDetailsPage.clickOnCheckOutButton();
        Assert.assertTrue(ShoppingBag.verifyIsShoppingBagPage());
    }

    @Then("^I verify that the product name should be same as on browse page$")
    public void i_verify_that_the_product_name_should_be_same_as_on_browse_page() throws Throwable {
        String name = ProductDetailsPage.getProductName();
        Assert.assertTrue(HomepageSteps.product_Name.contains(name));
    }

    @Then("^I verify that the error message appears as \"([^\"]*)\"$")
    public void i_verify_that_the_error_message_appears_as(String arg1) throws Throwable {
        Assert.assertTrue(ProductDetailsPage.verifyPleaseSelectSizeBox());
    }

    @Then("^I verify that the product should zoom after hover on the product$")
    public void i_verify_that_the_product_should_zoom_after_hover_on_the_product() throws Throwable {
    Assert.assertTrue(ProductDetailsPage.verifyMouseHoverImage());
    }

    @Then("^I verify that the product image should be same as on browse page$")
    public void i_verify_that_the_product_image_should_be_same_as_on_browse_page() throws Throwable {
        try {
            String pdp_Image_Array[] = ProductDetailsPage.getImageName().split("_");
            String browse_Image_Array[] = HomepageSteps.product_Image.split("_");
            Assert.assertTrue(pdp_Image_Array[0].equals(browse_Image_Array[0]));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }


    @When("^I click on checkout button$")
    public void i_click_on_checkout_button() throws Throwable {
        ProductDetailsPage.clickOnCheckOutButton();
    }

    @Given("^I scroll down and go to \"([^\"]*)\"$")
    public void i_scroll_down_and_go_to(String arg1) throws Throwable {
        Navigate.scrollPage(2000,5);
    }

    @Given("^I enter the Promocode on \"([^\"]*)\" text field$")
    public void i_enter_the_Promocode_on_text_field(String arg1, List<String> arg2) throws Throwable {

    }

    @Given("^I click on Apply button$")
    public void i_click_on_Apply_button() throws Throwable {
        ShoppingBag.clickApplyPromoCodeButton();
    }

    @Then("^I verify that product final cost should be same as on browse page$")
    public void i_verify_that_product_final_cost_should_be_same_as_on_browse_page() throws Throwable {
        String finalCost=ProductDetailsPage.getFinalCost();
        Assert.assertTrue(finalCost.equals(HomepageSteps.product_Final_Cost));
    }

    @Then("^I verify the \"([^\"]*)\" promo_code values should be same as browse page$")
    public void i_verify_the_promo_code_values_should_be_same_as_browse_page(String arg1) throws Throwable {
        Assert.assertTrue(ProductDetailsPage.getPromoCode().equals(HomepageSteps.promocode_Name));
        Assert.assertTrue(ProductDetailsPage.getPromoCode().contains(arg1));
        Assert.assertTrue(HomepageSteps.promocode_Name.contains(arg1));
        Assert.assertTrue(HomepageSteps.salePrice==ProductDetailsPage.getSalePrice() );
        Assert.assertTrue(HomepageSteps.offerPrice==ProductDetailsPage.getOfferPrice());
    }

    @When("^I enter \"([^\"]*)\" Promocode on Have a Promocode\\? text field$")
    public void i_enter_Promocode_on_Have_a_Promocode_text_field(String arg1) throws Throwable {
        ShoppingBag.enterPromoCode(arg1);
    }

    @Then("^I verify \"([^\"]*)\" message should appear after applying promocode$")
    public void i_verify_message_should_appear_after_applying_promocode(String arg1) throws Throwable {
        Assert.assertTrue(ShoppingBag.verifyPromoCodeApplied(arg1));
    }

    @Then("^I verify the \"([^\"]*)\" text appears$")
    public void i_verify_the_text_appears(String arg1) throws Throwable {
        Assert.assertTrue(ShoppingBag.verifyIsShoppingBagPage());
    }

    @Given("^I verify the image should appears on shopping page$")
    public void i_verify_the_image_should_appears_on_shopping_page() throws Throwable {
        Assert.assertTrue(ShoppingBag.verifyImageOnShoppingBagPage());
    }

    @Given("^I checkout on batch mode with \"([^\"]*)\" promocode until I reach the order confirmation page$")
    public void i_checkout_on_batch_mode_with_promocode_until_I_reach_the_order_confirmation_page(String arg1) throws Throwable {
        i_should_be_on_shopping_bag_page_on_clicking_checkout_button();
        ShoppingBag.enterPromoCode(arg1);
        ShoppingBag.clickApplyPromoCodeButton();
        Assert.assertTrue(ShoppingBag.verifyPromoCodeApplied("EXTRA"));
        Clicks.click("ShoppingBagPage.Checkout_Button");
    }
}
