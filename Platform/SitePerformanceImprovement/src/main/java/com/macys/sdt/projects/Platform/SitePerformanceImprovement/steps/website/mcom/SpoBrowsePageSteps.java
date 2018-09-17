package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config.SpoHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

/**
 * Created by yc04026 on 5/5/2017.
 */
public class SpoBrowsePageSteps extends StepUtils {

    private List<String> prodIds;
    private  String selectedProdId;


    @When("^I select random product from grid$")
    public void i_select_random_product_from_thumbnail_grid () throws  Throwable{
        Wait.untilElementNotPresent("product_thumbnails.loading_div");
        prodIds = SpoHelper.getProductIds();
        Random randomGenerator = new Random();
        int randomProductIndex = randomGenerator.nextInt(prodIds.size());
        System.out.println(prodIds.get(randomProductIndex));
        selectedProdId = (prodIds.size() ==1)? prodIds.get(randomProductIndex) : prodIds.get(0);

        SpoHelper.selectProductThumbnail(selectedProdId);
    }

    @Then("^I verify that I landed on respective product display page$")
    public void i_verify_that_landed_on_respective_product_display_page() throws Throwable{
        try {
            Wait.untilElementPresent("product_display.product_id_div");
            if (Elements.elementPresent("product_display.more_details")) {
                Clicks.click("product_display.more_details");
            }

            String prodIdPDP = Elements.findElement(Elements.element("product_display.product_id_div")).getText().replace("Web ID: ", "");

            if (!prodIdPDP.equals(selectedProdId)) {
                Assert.fail("Product id mismatch. Expected: " + selectedProdId + " Actual product displayed: " + prodIdPDP);
            }
        }catch (NoSuchElementException e){
            Assert.fail("Product display page is not displayed" + e);
        }
    }

    //Validating zoomer in pdp page
    @When("^I move the mouse over the PDP main image$")
    public void i_move_the_mouse_over_the_PDP_main_image() throws  Throwable{

        try{
            Wait.forPageReady();
            Clicks.hoverForSelection(Elements.findElement(By.className("mainImages")));

        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("Unable to find main image: ");
        }
    }

    @And("^I navigate to random sub categories from Left hand nav$")
    public void iNavigateToRandomSubCategoriesFromLeftHandNav() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("category_browse.subcategory");
        List<WebElement> categories = Elements.findElements("category_browse.subcategory");
        categories = categories.stream().filter(WebElement->WebElement.isDisplayed()).collect(toList());

        if(categories.size()== 0){
            System.out.println("No Sub-Catergory link is available on " + WebDriverManager.getCurrentTitle() + " Browse page");
        }
        else if(categories.size()== 1){
            WebElement selected = Elements.findElement("category_browse.subcategory");
            Clicks.click(selected);
        }
        else {
            int index = new Random().nextInt(categories.size() - 1);
            WebElement selected = categories.get(index);
            Clicks.click(selected);
        }
    }

}
