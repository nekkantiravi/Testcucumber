package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels.SpoRecentlyViewedPanel;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.RecentlyViewed;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.macys.sdt.framework.interactions.Clicks.clickIfPresent;
import static com.macys.sdt.framework.interactions.Elements.*;
import static com.macys.sdt.framework.interactions.Navigate.browserBack;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels.SpoRecentlyViewedPanel.getProductIdsWithAlternateImages;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels.SpoRecentlyViewedPanel.isalternateImagesAvailable;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels.SpoRecentlyViewedPanel.selectProductThumbnail;

/**
 * Created by yc04026 on 10/6/2016.
 */
public class SpoRecentlyViewed extends StepUtils{
    private static final Logger log = LoggerFactory.getLogger(SpoRecentlyViewed.class);
    // This step is to visit the product thumbnails repeatedly with specified times in Search/Browse/Subsplash pages
    @And("^I visit \"([^\"]*)\" random items$")
    public void I_visit_random_items(int value) throws Throwable {
        List<String> list = new ArrayList<>();
        String item_title;
        for (int i=0;i<value;i++){
            CommonUtils.selectRandomProduct(false, false);
            onPage("product_display");
            if(ProductDisplay.isMasterMemberPage()){
                clickIfPresent("product_display.more_details");
                item_title = getText("product_display.master_product_id");
            }
            else {
                item_title = getText("product_display.product_id_div");
            }

            if(item_title != null) {
                list.add(item_title);
            }
            System.out.println(list.get(i));
            browserBack();
        }
    }

    // This step navigates to subsplash page for the given mode
    @When("^I navigate to \"(domestic|iship|registry)\" subsplash pages$")
    public void INavigateToSubSplashPages(String mode) throws Throwable {
        /*if(mode.equalsIgnoreCase("registry")){
            Home regHome= new Home();
            List<String> mainCategory=regHome.getAllMainCategoryNames();
            String subSplashMainCat=mainCategory.get(1);
            regHome.selectMainCategory(subSplashMainCat);
            List<String> subCategory=regHome.getAllSubCategoryNames();
            String subSplashsubCat=subCategory.get(25);
            regHome.selectSubCategory(subSplashsubCat);
        }
        else {
            Home homePage = new Home();
            List<String> mainCategorySite = homePage.getAllMainCategoryNames();
            String mainSubCategory = mainCategorySite.get(2);
            homePage.selectMainCategory(mainSubCategory);
            List<String> subCategorySite = homePage.getAllSubCategoryNames();
            String subSplashCategory = subCategorySite.get(2);
            homePage.selectSubCategory(subSplashCategory);

        }*/
        Home homePage = new Home();
        homePage.selectMainCategory("HOME");
        homePage.selectSubCategory("Martha Stewart Collection");
        log.info("Navigated to subsplash page on"+mode+ " mode");

    }

    // This step is to navigate to registry catsplash page
    @And("I navigate to registry catsplash page$")
    public void INavigateToRegistryCatsplashPage() throws Throwable{
        Home regHome= new Home();
        List<String> catSplashCategories=regHome.getAllMainCategoryNames();
        String catSplashCategory=catSplashCategories.get(2);
        regHome.selectMainCategory(catSplashCategory);
    }

    // This step is to navigate to registry Brand index page
    @And("I navigate to registry Brand index page$")
    public void I_NavigateToRegistryBrandIndexPage() throws Throwable{
        Home regHome= new Home();
        List<String> catSplashCategories=regHome.getAllMainCategoryNames();
        String catSplashCategory=catSplashCategories.get(2);
        regHome.selectMainCategory(catSplashCategory);
        String brandIndexSubCat=regHome.getAllSubCategoryNames().get(53);
        regHome.selectSubCategory(brandIndexSubCat);
    }

    // This step is to verify the Rvi Panlel is displaying in corresponding page
    @Then("I should see rvi panel")
    public void I_should_see_rvi_panel() throws Throwable {
        SpoRecentlyViewedPanel.isDisplayed();
        SpoRecentlyViewedPanel.getRecentlyViewed();
    }

    // This step is to verify the Rvi panel should not be displayed in page.
    @Then("I should not see Rvi panel")
    public void I_should_not_see_rvi_panel() throws Throwable {
        Assert.assertFalse(SpoRecentlyViewedPanel.isDisplayed());
    }

    // This step is to perform click Edit/Done button in Rvi panel
    @When("I click \"(edit|done)\" button in rvi panel?$")
    public void I_click_edit_done_button_in_Rvi_panel(String action) {
        SpoRecentlyViewedPanel.isDisplayed();
        try {
            SpoRecentlyViewedPanel.clickEditDone();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Unable to click on" +action+ "button");
        }

    }

    // This step is to verify the item rating element available for items in RVI panel
    @Then("I should view the review link in rvi panel$")
    public void I_should_view_the_review_link_in_rvi_panel() {
        scrollToLazyLoadElement("spoRecentlyViewed.thumbnail_wrapper");
        try {
            elementPresent("spoRecentlyViewed.rvi_review");
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Unable to see review in rvi panel");
        }

    }

    // This step is to perfom remove and ensure the product is removed from Rvi panel
    @When("I remove an item and verify the item is removed from RVI panel$")
    public void I_remove_item_from_rvi_panel() {

        SpoRecentlyViewedPanel.isDisplayed();
        String error_message= "count mismatches";
        try {
            String ItemToRemove=SpoRecentlyViewedPanel.itemToBeRemoved();
            SpoRecentlyViewedPanel.clickRemoveButton();
            SpoRecentlyViewedPanel.clickEditDone();
            Assert.assertFalse(SpoRecentlyViewedPanel.getProductIds().contains(ItemToRemove));
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Unable to click remove button");
        }
    }

    // This step is to remove and validate the removal of item in old Rvi panel
    @When("I remove and verify the item is removed from old RVI panel$")
    public void I_remove_item_from_Oldrvi_panel() {

        SpoRecentlyViewedPanel.isDisplayed();
        String error_message= "count mismatches";
        try {
            String ItemToRemove=SpoRecentlyViewedPanel.itemToBeRemovedRegistry();
            SpoRecentlyViewedPanel.clickRemoveButton();
            SpoRecentlyViewedPanel.clickEditDone();
            Assert.assertFalse(SpoRecentlyViewedPanel.getProductIdsInRegistryRviEntire().contains(ItemToRemove));
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Unable to click remove button");
        }
    }

    // This step is to verify the Rvi panel contains six items at a time
    @Then("^I verify 6 Recent products are displayed at one time in RVI panel$")
    public void I_Verify_6_Recent_Products_Displayed_At_One_Time_In_RVI_Panels() throws Throwable{
        String error_message= "count mismatches";
        SpoRecentlyViewedPanel.isDisplayed();
        Assert.assertTrue(error_message, SpoRecentlyViewedPanel.getRecentlyViewedCurrentCount() == 6);
    }

    // This step is to verify the Rvi panel contains six items at a time in old Rvi panel
    @Then("^I verify 6 Recent products displayed at one time in RVI panel for registry mode$")
    public void I_Verify_6_Recent_Products_Displayed_At_One_Time_In_RVI_Panel_for_registry_mode() throws Throwable{
        String error_message= "count mismatches";
        System.out.println(SpoRecentlyViewedPanel.getProductIdsInRegistryRvi().size());
        Assert.assertTrue(error_message, SpoRecentlyViewedPanel.getProductIdsInRegistryRvi().size() == 6);
    }

    // This step is to verify the navigation arrows not displaying in Rvi panel
    @Then("^I should not see the navigation arrow buttons$")
    public void I_should_not_see_the_navigation_arrow_buttons() throws Throwable{
        Assert.assertFalse(SpoRecentlyViewedPanel.isNavigationArrowsVisible());
    }

    // This step is to verify the navigation arrows displaying in Rvi panel
    @Then("^I should validate the navigation arrow buttons$")
    public void I_should_see_the_navigation_arrow_buttons() throws Throwable{
        Assert.assertTrue("Not able to see navigation arrows", SpoRecentlyViewedPanel.isNavigationArrowsVisible());
    }

    // This step is to ensure we are in Zero Search landing page in Registry mode
    @Then("^I should be in the Registry zero result page$")
    public void I_should_be_in_registry_zero_result_page() throws Throwable{

        Assert.assertTrue("Not in Zero slp", findElement(By.id("displayMessage")).getText().contains("0"));
    }

    // This step is to ensure we are in Zero Search landing page in Domestic/ishipmode
    @Then("^I should be in zero result page$")
    public void I_should_be_in_zero_result_page() throws Throwable{
        onPage("search_result");
        Assert.assertTrue("Not in Zero slp", findElement("search_result.verify_page").getText().equalsIgnoreCase("0") );
    }

    // This step is to perform click on navigation arrows in Rvi panel
    @When("^I click on \"(left|right)\" arrow key inside RVI$")
    public void I_click_on_arrow_key_inside_RVI(String arrow) throws Throwable {
        SpoRecentlyViewedPanel.updateProducts();
        if (!clickIfPresent("spoRecentlyViewed.scroll_" + arrow))
            Assert.fail("ERROR - APP: Cannot scroll to the " + arrow);
        // wait for the scroll animation - can't find it programmatically but it always takes the same amount of time
        Utils.threadSleep(2000, null);
    }

    // This step is to verify the items assortment after clicking on the navigation arrows in Rvi panel
    @Then("^I validate that (previous|next) set of products are displayed$")
    public void iVerifyThatSetOfProductsAreDisplayed(String value) throws Throwable {
        List<String> currentProducts = SpoRecentlyViewedPanel.getRecentlyViewed();
        List<String> oldProducts = SpoRecentlyViewedPanel.getOldRecentlyViewed();
        Assert.assertFalse("Recently viewed products panel did not change",
                currentProducts.get(0).equalsIgnoreCase(oldProducts.get(0)));
    }

    // I visit "5" random items having alternate image thumbnail
    @When("^I visit \"([^\"]*)\" random items having alternate image thumbnail$")
    public void i_visit_random_item_having_alternate_image_thumbnail(int value) throws Throwable {
        List<String> ids = getProductIdsWithAlternateImages();
        Wait.forPageReady();

        for(int i=0; i<value;i++){
            selectProductThumbnail(ids.get(i));
            browserBack();
            Wait.forPageReady();
        }
    }

    //I should see the alternate images in master pdp rvi panel
    @Then("^I should see the alternate images in master pdp rvi panel$")
    public void i_should_see_the_alternate_images_in_master_pdp_rvi_panel() throws Throwable {
        System.out.println(isalternateImagesAvailable());
        List<Boolean> altImages= isalternateImagesAvailable();
        for (Boolean b:altImages) {
            Assert.assertTrue("Alternate images not available in old rvi panel", b.equals(true));
        }
    }


    //Code for new RVI panel
    @Then("^I remove all Recently viewed items$")
    public void iRemoveAllRecentlyViewedItems() throws Throwable {
        List<WebElement> recentlyViewedItems = RecentlyViewed.rviProductThumbnails();
        pausePageHangWatchDog();
        int totalProductInRvi = recentlyViewedItems.size();
        for (int i = 0; i < totalProductInRvi; i++) {
            if (RecentlyViewed.isEditVisible()) {
                Clicks.javascriptHover(Elements.findElement("recently_viewed_items.edit_button"));
                Clicks.javascriptClick(Elements.findElement("recently_viewed_items.edit_button"));
            }
            if (RecentlyViewed.isDoneVisible()) {
                Clicks.javascriptHover(Elements.findElement("recently_viewed_items.remove_button"));
                Clicks.click("recently_viewed_items.remove_button");
            } else {
                Assert.fail("ERROR -APP: Removing RVI product failed.");
            }
        }
        Clicks.click("recently_viewed_items.rvi_edit_done");
        Wait.forPageReady();
        resumePageHangWatchDog();
    }

    @Then("^I (should not|should) see recently viewed items section$")
    public void iShouldNotSeeSection(String condition) throws Throwable {
        if (condition.equalsIgnoreCase("should not")) {
            Assert.assertFalse("Error Env:- RVI is displaying", Elements.elementPresent("recently_viewed_items.rvi_container"));
        } else {
            Assert.assertTrue("Error Env:- RVI is not displaying", Elements.elementPresent("recently_viewed_items.rvi_container"));
        }
    }
}
