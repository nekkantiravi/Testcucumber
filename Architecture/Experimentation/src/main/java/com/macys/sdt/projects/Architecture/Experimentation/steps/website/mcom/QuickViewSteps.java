package com.macys.sdt.projects.Architecture.Experimentation.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * Created by Abhishek on 6/7/2017.
 */
public class QuickViewSteps {
    static String product_Name = null;
    static String product_Image = null;
    static String product_Final_Cost = null;
    static String promocode_Name = null;
    static String product_Sale_Price= null;
    static int discountPercentageValue = -1;
    static float offerPrice = -1.0f;
    static float salePrice = -1.0f;

    @Then("^I verify that the \"([^\"]*)\" link appears$")
    public void i_verify_that_the_link_appears(String arg1) throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_see_full_details");
        Assert.assertTrue(Elements.getText("homePage.quick_view_see_full_details").trim().equalsIgnoreCase(arg1));
    }

    @When("^I select the random product in a quickview dialog with \"([^\"]*)\"$")
    public void i_select_the_random_product_in_a_quickview_dialog_with(String arg1) throws Throwable {
        Wait.untilElementPresent("product_display.loader_completed");
        try {
            String searchText = arg1.toLowerCase();
            if (searchText.contains("name")) {
                getProductData("search_result.product_thumbnail_link_name", searchText);
            }
            if (searchText.contains("image")) {
                getProductData("search_result.product_thumbnail_link_image", searchText);
            }
            if (searchText.contains("finalcost")) {
                getProductData("search_result.product_final_Cost", searchText);
            }
            if (searchText.contains("code")) {
                getProductData("homePage.Promo_Code_Products", searchText);
            }
            if(searchText.contains("sale")){
                getProductData("homePage.quick_view_sale_price_hover", searchText);
            }
        } catch (Exception e) {
            Assert.fail("failed while selecting the products on the basis on given keyword.");
        }
    }

    public static void getProductData(String selector, String dataToSearch) {
        WebElement selectedElement = Elements.getRandomElement(selector);
        if (dataToSearch.contains("name")) {
            try {
                product_Name = selectedElement.getText();
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_name_hover", product_Name.split(",")[0]));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            } catch (Exception e) {
                product_Name = selectedElement.getText();
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_name_hover", product_Name.split(",")[0]));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            }
        } else if (dataToSearch.contains("image")) {
            try {
                product_Image = selectedElement.getAttribute("src");
                String image[] = product_Image.split("_");
                image = image[0].split("/");
                String imageID = image[image.length - 1];
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_image_hover", imageID));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            } catch (Exception e) {
                product_Image = selectedElement.getAttribute("src");
                String image[] = product_Image.split("_");
                image = image[0].split("/");
                String imageID = image[image.length - 1];
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_image_hover", imageID));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");

            }
        } else if (dataToSearch.contains("finalcost")) {
            try {
                product_Final_Cost = selectedElement.getText();
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_final_cost_hover", product_Final_Cost));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            } catch (Exception e) {
                product_Final_Cost = selectedElement.getText();
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_final_cost_hover", product_Final_Cost));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            }
        }
            else if(dataToSearch.contains("sale")){
            try {
                product_Sale_Price = selectedElement.getText();
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_sale_price_hover_Image", product_Sale_Price));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            } catch (Exception e) {
                product_Sale_Price = selectedElement.getText();
                Clicks.hoverForSelection(Elements.paramElement("homePage.quick_view_sale_price_hover_Image", product_Sale_Price));
                Wait.untilElementPresent("search_result.product_thumbnail_quickview");
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            }
        } else if (dataToSearch.contains("code")) {
            String promocode_WithOffer = null;
            String promocode_Sale = null;
            try {
                WebElement selectedElement1 = Elements.getRandomElement("homePage.Promo_Code_Products");
                promocode_Name = selectedElement1.getText();
                WebElement selectedElement2 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_WithOffer", promocode_Name));
                promocode_WithOffer = selectedElement2.getText();
                WebElement selectedElement3 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_Sale", promocode_WithOffer, promocode_Name));
                promocode_Sale = selectedElement3.getText();
                calculatePromoCodeValues(promocode_WithOffer, promocode_Sale);
                Clicks.click(Elements.paramElement("homePage.Promo_Code_Selected_Product", promocode_WithOffer, promocode_Name, promocode_Sale));
            } catch (Exception e) {
                Wait.untilElementPresent("product_display.loader_completed");
                WebElement selectedElement1 = Elements.getRandomElement("home.Promo_Code_Products");
                promocode_Name = selectedElement1.getText();
                WebElement selectedElement2 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_WithOffer", promocode_Name));
                promocode_WithOffer = selectedElement2.getText();
                WebElement selectedElement3 = Elements.getRandomElement(Elements.paramElement("homePage.Promo_Code_Sale", promocode_WithOffer, promocode_Name));
                promocode_Sale = selectedElement3.getText();
                calculatePromoCodeValues(promocode_WithOffer, promocode_Sale);
                Clicks.click(Elements.paramElement("homePage.Promo_Code_Selected_Product", promocode_WithOffer, promocode_Name, promocode_Sale));
            }
        }
    }

    public static void calculatePromoCodeValues(String promocode_WithOffer, String promocode_Sale) {
        try {
            String discountPercentage[] = promocode_Name.split(" ");
            discountPercentageValue = Integer.parseInt(discountPercentage[1].replace("%", ""));
            String promocode_WithOfferArray[] = promocode_WithOffer.split("\\$");
            offerPrice = Float.parseFloat(promocode_WithOfferArray[1]);
            String promocode_SaleArray[] = promocode_Sale.split("\\$");
            salePrice = Float.parseFloat(promocode_SaleArray[1]);
            float calculatedOfferPrice = salePrice - ((salePrice * discountPercentageValue) / 100);
            Assert.assertTrue((calculatedOfferPrice >= offerPrice - .10 && calculatedOfferPrice <= offerPrice + .10));
        } catch (Exception e) {
            Assert.fail("Could not match the offer price value on Browse page.");
        }
    }

    @Then("^I verify that the product name on quickPage should be same as on browse page$")
    public void i_verify_that_the_product_name_on_quickPage_should_be_same_as_on_browse_page() throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_product_name");
        Assert.assertTrue(QuickViewSteps.product_Name.equals(Elements.getText("homePage.quick_view_product_name")));
    }

    @Then("^I verify that the product image on quickPage should be same as on browse page$")
    public void i_verify_that_the_product_image_on_quickPage_should_be_same_as_on_browse_page() throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_image");
        Assert.assertTrue(QuickViewSteps.product_Image.split("_")[0].equals(Elements.getElementAttribute("homePage.quick_view_image", "src").split("_")[0]));
    }

    @Then("^I verify that the Product final cost on quickPage should be same as on browse page$")
    public void i_verify_that_the_product_final_cost_on_quickPage_should_be_same_as_on_browse_page() throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_image");
        Assert.assertTrue(QuickViewSteps.product_Final_Cost.equals(Elements.getText("homePage.quick_view_final_price")));
    }

    @Then("^I verify that the product sale price on quickPage should be same as on browse page$")
    public void i_verify_that_the_product_sale_price_on_quickPage_should_be_same_as_on_browse_page() throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_sale_price");
        Assert.assertTrue(QuickViewSteps.product_Sale_Price.equals(Elements.getText("homePage.quick_view_sale_price")));
    }

    @When("^I click on 'see full product details' link from the quickview dialog$")
    public void i_click_on_see_full_product_details_link_from_the_quickview_dialog() throws Throwable {
        Wait.untilElementPresent("homePage.quick_view_see_full_details");
        Clicks.click("homePage.quick_view_see_full_detail");
    }

    @Then("^I verify that the \"([^\"]*)\" promo_code value on quickPage should be same as on browse page$")
    public void i_verify_that_the_promo_code_value_on_quickPage_should_be_same_as_on_browse_page(String arg1) throws Throwable {
        Assert.assertTrue((QuickViewSteps.promocode_Name.contains(Elements.getText(""))));
        Assert.assertTrue(HomepageSteps.promocode_Name.contains(arg1));
        Assert.assertTrue(HomepageSteps.salePrice==QuickViewSteps.salePrice );
        Assert.assertTrue(HomepageSteps.offerPrice==QuickViewSteps.offerPrice);
    }
}