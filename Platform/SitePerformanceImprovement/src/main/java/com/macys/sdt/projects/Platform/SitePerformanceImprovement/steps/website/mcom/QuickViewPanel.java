package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels.QuickViewPanel.*;


/**
 * Created by YC04026 on 2/21/2017.
 */
public class QuickViewPanel extends StepUtils {

    //And I select a random quick view
    @And("^I select a random quick view$")
    public void i_select_a_random_quick_view() throws  Throwable{
        WebElement randomSelectedProduct;
        randomSelectedProduct = selectRandomProduct();
        getProdDescription(randomSelectedProduct);
        getSalePrice(randomSelectedProduct);
        getPromotion(randomSelectedProduct);
        selectQuickView(randomSelectedProduct);
    }

    // Then I verify that product name of quick view with grid thumbnail
    @Then("^I verify that product name of quick view with grid thumbnail$")
    public void i_verify_that_product_name_of_quick_view_with_grid_thumbnail()throws  Throwable{
        Assert.assertTrue("Product title mismatch", getItemTitleQV().equalsIgnoreCase(prodDescription));

    }

    //And I verify that price of quick view with grid thumbnail
    @And("^I verify that price of quick view with grid thumbnail$")
    public void i_verify_that_price_of_quick_view_with_grid_thumbnail()throws  Throwable{
        String qvPrice = getItemPrice();
        String priceRange[] = salePrice.split(" ");

        if(qvPrice.equalsIgnoreCase(salePrice))
        Assert.assertTrue("Product price mismatch", qvPrice.equalsIgnoreCase(salePrice));
        else if(qvPrice.equalsIgnoreCase(priceRange[2]))
            Assert.assertTrue("Product price mismatch", qvPrice.equalsIgnoreCase((priceRange[0])));
    }

    // And I verify that promotion of quick view with grid thumbnail
    @And("^I verify that promotion of quick view with grid thumbnail$")
    public void i_verify_that_promotion_of_quick_view_with_grid_thumbnail()throws Throwable{
        String promotionFromQV;
        promotionFromQV = getQVpromotions();
        try{
            if(promotionFromQV !=null)
                Assert.assertTrue("Promotion mismatch", promotionFromQV.contains(promotionsFromGrid));
        }catch(NullPointerException e){
            System.out.println("No promotion to compare");
        }
     }

    //And I verify the basic attributes of quickview in "domestic" mode
    @And("^I verify the basic attributes of quickview in \"([^\"]*)\" mode$")
    public void I_verify_the_basic_attributes_of_quickview(String mode)throws Throwable{
        HashMap<String, Boolean> qvDetailsMemeberProduct = getMemberQVattributes(mode);

        if(isMasterMember().equalsIgnoreCase("member")){
            for(Map.Entry<String, Boolean> qv: qvDetailsMemeberProduct.entrySet()){
             Assert.assertTrue("Some attributes unavailable", qv.getValue().equals(true));
            }
        }


    }

    //Click on Zoomer buttons
    @When("^I click on \"([^\"]*)\" Button in QuickView panel$")
    public void i_click_on_button_in_quickView_panel(String zoomButton) throws Throwable{

        switch (zoomButton){
            case "zoom_in":
                Clicks.click(By.className("zoom-in"));
                break;
            case "zoom_out":
                Clicks.click(By.className("zoom-out"));
                break;
            case "zoom_reset":
                Clicks.click(By.className("zoom-reset"));
                break;
            default:
                System.out.println("No relevent Zoom option given");
        }
    }


    //Validate the Image got zoomed in or zoomer out
    @Then("^I should see the image is \"([^\"]*)\"$")
    public void i_should_see_the_image_zoomed_in_or_out(String zoom_type)throws  Throwable{
        if(zoom_type.equalsIgnoreCase("zoomed_in")){
            if(isMasterMember().equalsIgnoreCase("member")){
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelHeight").contains("764px"));
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelWidth").contains("624px"));
            }
            else {
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelHeight").contains("660px"));
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelWidth").contains("548px"));
            }
        }
        else {
            if(isMasterMember().equalsIgnoreCase("member")){
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelHeight").contains("382px"));
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelWidth").contains("312px"));
            }
            else {
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelHeight").contains("330px"));
                Assert.assertTrue("Main image is not Zoomed in", getHightWidthQVimage().get("PixelWidth").contains("274px"));
            }
        }
    }
    @Then("^I should see the quickview overlay$")
    public void i_should_see_the_quickView_overlay() throws Throwable{
        Assert.assertTrue("ERROR-APP: Quick View Overlay is not displayed", Elements.elementPresent(By.className("main-view-holder")));
    }

    //And I select a random quick view
    @And("^I select a random quick view in shopping bag page$")
    public void i_select_a_random_quick_view_in_shopping_bag_page() throws  Throwable{
        WebElement selectedRandomProduct;
        selectedRandomProduct = selectRandomProductFromProsPanel();
        selectQuickViewInProsPanel(selectedRandomProduct);
    }


}
