package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.MEW.panels.FacetAccordionModel;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;




import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.LocalStoreUtils;

import java.awt.*;

/**
 * Created by yh03392 on 9/5/17.
 */
public class ShopLocalStore {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ShopLocalStore.class);





    @Then("^I should see All Items tab displayed$")
    public void iShouldSeeAllItemsTabDisplayed() throws Throwable{
        Wait.untilElementPresent("search_result.all_products");
        Assert.assertTrue(Elements.findElement("search_result.all_products").isDisplayed());
        LOGGER.info("Verified the All Products tab");
    }

    @And("^I should see Free pick up tab displayed$")
    public void iShouldSeeFreePickUpTabDisplayed() throws Throwable{
        Wait.untilElementPresent("search_result.free_pickup");
        Assert.assertTrue(Elements.findElement("search_result.free_pickup").isDisplayed());
        LOGGER.info("Verified the Free Pick up tab");

    }
    @And("^I tap on Local Store tab$")
    public void iTapOnLocalStoreTab() throws Throwable {
        Clicks.clickWhenPresent(By.xpath("//*[@id=\"shopLocalStore\"]"));
        Elements.elementPresent("search_result.find_store_model");
        LOGGER.info("Landed on Local Store model");
    }



    @And("^I should see zip code text box in local store model$")
    public void iShouldSeeZipCodeTextBoxinlocalstoremodel() throws Throwable {

        Assert.assertTrue(Wait.untilElementPresent("search_result.find_store_zipcode"));
        Elements.findElement("search_result.find_store_zipcode").getAttribute("value");
        Assert.assertTrue(Wait.untilElementPresent("search_result.find_store_clear"));
        LOGGER.info("Zipcode text field is visible");
    }

    @And("^I should see navigation icon beside zip code text box field in local store model$")
    public void iShouldSeeNavigationIconBesideZipCodeTextBoxFieldinlocalstoremodel() throws Throwable {
        Assert.assertTrue(Elements.elementPresent("search_result.find_store_navigator"));
        LOGGER.info("Navigator is visible in find store near you model");
    }

    @And("^I should see show stores within \"([^\"]*)\" miles option selected by default in drop down box below zip code field$")
    public void iShouldSeeShowStoresWithinMilesOptionSelectedByDefaultInDropDownBoxBelowZipCodeField(String distanceValue) throws Throwable {
        String actual_default_distance = DropDowns.getSelectedValue(Elements.element("search_result.find_store_distance_dropdown"));
        Assert.assertTrue(actual_default_distance.contains(distanceValue));
        LOGGER.info("Default distance is displayed as expected");
    }

    @And("^I should see search button in local store model$")
    public void iShouldSeeSearchButtoninlocalstoremodel() throws Throwable {
        Assert.assertTrue(Elements.elementPresent("search_result.find_store_search_button"));
        LOGGER.info("Search button is displayed as expected");
    }


    @And("^I select a store with prepopulated zipcode$")
    public void iSelectAStoreWithPrepopulatedZipcode() throws Throwable{
        Wait.untilElementPresent("search_result.find_store_zipcode");
        Clicks.click("search_result.find_store_search_button");
        if (Wait.secondsUntilElementPresent("search_result.find_store_search_results",10)) {
            Clicks.clickRandomElement("search_result.find_store_radio_button");
        } else {
            Assert.fail("Error - Data:- Stores are not available to select");
        }
        Clicks.click("search_result.find_store_done_button");
    }

    @And("^I select a nearest store using \"([^\"]*)\" search$")
    public void iSelectANearestStoreUsingSearch(String zipCode) throws Throwable {
        Wait.untilElementPresent("search_result.find_store_zipcode");
        Wait.untilElementPresent("search_result.find_store_clear");
        if (Elements.elementPresent("search_result.find_store_clear")){
            Clicks.click("search_result.find_store_clear");
        }
        Elements.findElement("search_result.find_store_zipcode").sendKeys(zipCode);
        Clicks.click("search_result.find_store_search_button");
        Utils.threadSleep(5000,"Waiting for available stores to load");
        Wait.secondsUntilElementPresent("search_result.find_store_search_results",10);
        Clicks.clickRandomElement("search_result.find_store_radio_button");
        Clicks.click("search_result.find_store_done_button");
    }

    @And("^I should see product count in Local Store Tab if store is preselected$")
    public void iShouldSeeProductCountInLocalStoreTabIfStoreIsPreselected() throws Throwable {
        String localStore = LocalStoreUtils.getItemFromLocalStorage("shop_local");
        if (localStore==null || (localStore==null && Cookies.getCookieValue("MISCGCs").isEmpty())){
            LOGGER.info("Local Store details are not available for this user");
        }
        else if(localStore!=null || !Cookies.getCookieValue("MISCGCs").isEmpty()){
            Wait.untilElementPresent("search_result.local_store_prdcnt");
            Assert.assertTrue(Elements.elementPresent("search_result.local_store_prdcnt"));
            LOGGER.info("Local Store product count is displayed");
        }
        else{
            Assert.fail("Expected Local Store product count is not displayed");
        }
    }

    @And("^I should see Free pick up tab greyed out$")
    public void iShouldSeeFreePickUpTabGreyedOut() throws Throwable{
        Clicks.click(By.xpath("//*[@id=\"shopLocalStore\"]"));
        Assert.assertTrue(Wait.untilElementPresent("search_result.free_pickup_info"));
        LOGGER.info("Free pick up tab is greyed out for bops inelligible Items");

    }

    @Then("I should see messaging for invalid search$")
    public void iShouldSeeMessagingForInvalidSearch()  throws Throwable{
        Assert.assertTrue(Wait.untilElementPresent("search_result.invalid_search_results"));
        LOGGER.info("Invalid Search Results screen is displayed");
    }

    @And("^I click on the change store link$")
    public void iClickOnTheChangeStoreLink()  throws Throwable{
        Wait.untilElementPresent("search_result.change_store");
        Clicks.click(By.xpath("//*[@id=\"m-change-store\"]"));
        LOGGER.info("Able to click on change store link");
    }

    @And("^I should see product count in All Items tab$")
    public void iShouldSeeProductCountInAllItemsTab() throws Throwable {
        String localStore = LocalStoreUtils.getItemFromLocalStorage("shop_local");
        if (localStore==null || (localStore==null && Cookies.getCookieValue("MISCGCs").isEmpty())){
            LOGGER.info("Local Store details are not available for this user");
        }
        else if(localStore!=null || !Cookies.getCookieValue("MISCGCs").isEmpty()){
            Wait.untilElementPresent("search_result.all_iem_count");
            Assert.assertTrue(Elements.elementPresent("search_result.all_iem_count"));
            LOGGER.info("All Items  product count is displayed");
        }
        else{
            Assert.fail("Expected All Items product count is not displayed");
        }

    }

    @Then("^I should see pick up in store selected by default with Size and color preselected$")
    public void iShouldSeePickUpInStoreSelectedByDefaultWithSizeAndColorPreselected() throws Throwable {
        Assert.assertTrue("Error - App:- Pick up in store not selected by default",
                Elements.findElement("search_checkout.pickup_in_store_checkbox").isSelected());
        Assert.assertTrue("Error - App:- Selected color is not persisted on PDP",
                Elements.getElementAttribute("product_display.selected_swatch_color", "aria-label").contains(FacetAccordionModel.selectedFacetValue));
        Assert.assertTrue("Error - App:- Selected size is not persisted on PDP",
                Elements.getElementAttribute("product_display.size_selected", "aria-label").contains("4"));
    }

    @And("^I select \"([^\"]*)\" store using \"([^\"]*)\" search$")
    public void iSelectMacySStanfordShoppingCenterStoreUsingSearch(String Store, String zipCode) throws Throwable {
        Wait.untilElementPresent("search_result.find_store_zipcode");
        Wait.untilElementPresent("search_result.find_store_clear");
        if (Elements.elementPresent("search_result.find_store_clear")) {
            Clicks.click("search_result.find_store_clear");
        }
        Elements.findElement("search_result.find_store_zipcode").sendKeys(zipCode);
        Clicks.click("search_result.find_store_search_button");
        Utils.threadSleep(5000, "Waiting for available stores to load");
        Wait.secondsUntilElementPresent("search_result.find_store_search_results", 10);
        Clicks.clickElementByText(By.className("display-name"), Store);
        Clicks.click("search_result.find_store_done_button");
    }

    @Then("^I should see pick up in store is not selected by default in PDP$")
    public void iShouldSeePickUpInStoreIsNotSelectedByDefaultInPDP() throws Throwable {
        Assert.assertFalse("Error - App:- Pick up in store not selected by default",
                Elements.findElement("search_checkout.pickup_in_store_checkbox").isSelected());
    }
}
