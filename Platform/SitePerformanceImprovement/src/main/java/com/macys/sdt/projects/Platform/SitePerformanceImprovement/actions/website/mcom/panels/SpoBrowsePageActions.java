package com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels;

import com.macys.sdt.framework.utils.StepUtils;

/**
 * Created by yc04026 on 5/5/2017.
 */
public class SpoBrowsePageActions extends StepUtils {

    /*private List<String> prodIds;
    private  String selectedProdId;


    @When("^I select random product from thumbnail grid$")
    public void i_select_random_product_from_thumbnail_grid () throws  Throwable{
        Wait.untilElementNotPresent("product_thumbnails.loading_div");
        prodIds = SpoHelper.getProductIds();
        Random randomGenerator = new Random();
        int randomProductIndex = randomGenerator.nextInt(prodIds.size());
        System.out.println(prodIds.get(randomProductIndex));
        selectedProdId = (prodIds.size() ==1)? prodIds.get(randomProductIndex) : prodIds.get(0);

        SpoHelper.selectProductThumbnail(selectedProdId);
    }

    @Then("^I verify that landed on respective product display page$")
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
    }*/


}
