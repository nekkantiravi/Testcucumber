package com.macys.sdt.projects.Selection.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.MEW.shop_and_browse.ProductDisplayMew;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.MEW.bcom.shop_and_browse.ChangePickupStoreDialogMew;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Random;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BopsSteps {

    private static final Logger logger = LoggerFactory.getLogger(BopsSteps.class);

    private String brand;
    private String bopsStoreName;
    private String zip;
    private String price;
    private String color;
    private String id;
    private String storeName;
    private String distanceOne;
    private String distanceTwo;

    @When("^I search for a random product$")
    public void iSearchForARandomProduct(DataTable items) {
        List<List<String>> data = items.raw();
        int randomIndex = new Random().nextInt(data.size());
        String relativeId = String.valueOf(data.get(randomIndex));
        String value = relativeId.substring(1, relativeId.length() - 1);
        Home home = Navigate.get(Home.class);
        home.searchProducts(value);
        logger.info("Found Product successfully");
    }

    @When("^I select a size on PDP$")
    public void iSelectASizeOnPDP() {
        Navigate.get(ProductDisplayMew.class).selectSize();
        logger.info("Size is successfully selected");
    }

    @When("^I verify brand name and product description on PDP$")
    public void iVerifyBrandNameAndProductDescriptionOnPDP() {
        ProductDisplayMew pdp = Navigate.get(ProductDisplayMew.class);
        Wait.until(pdp.productBrand::exists, 10);
        id = pdp.getPdpId();
        brand = pdp.getPdpBrandAndProductName();
        color = pdp.getPdpColor();
        price = pdp.getPdpPrice().toLowerCase();
    }

    @When("^I tap pick up in store button$")
    public void iTapPickUpInStoreButton() {
        ProductDisplayMew pdp = Navigate.get(ProductDisplayMew.class);
        Wait.until(pdp.storeLookup::isDisplayed, 10);
        Clicks.javascriptClick(pdp.storeLookup);
        logger.info("PDP Pick Up In Store button was successfully selected");
    }

    @Then("^I verify Product description on Bops and PDP match$")
    public void iVerifyProductDescriptionOnBopsAndPDPMatch() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.productName::isDisplayed, 10);
        Assert.assertEquals(brand, bopsModal.productName.getText().toLowerCase());
        Wait.until(bopsModal.bopsPrice::isDisplayed, 10);
        Assert.assertEquals(price, bopsModal.bopsPrice.getText().toLowerCase());
        Assert.assertEquals(bopsModal.getBopsId(), id);
        Assert.assertEquals(bopsModal.getBopsColor(), color);
        logger.info("Product description on Bops and PDP match as expected");
    }

    @When("^I enter zip code \"([^\"]*)\" and tap on search$")
    public void iEnterZipCodeAndTapOnSearch(String zipCode) {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        bopsModal.EnterZipCodeAndSearch(zipCode);
        logger.info("Zip code is successfully entered");
        zip = zipCode;
    }

    @Then("^I should verify the store results display$")
    public void iShouldVerifyTheStoreResultsDisplay() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.stores::isDisplayed, 10);
        Assert.assertTrue(bopsModal.stores.isDisplayed());
        logger.info("Bopsible store results display as expected");
    }

    @Then("^I verify save button does not display$")
    public void iVerifySaveButtonDoesNotDisplay() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Assert.assertTrue("Save button is not displayed as expected", !(bopsModal.saveButton.isDisplayed() || bopsModal.saveButtonBottom.exists()));
    }

    @Then("^I verify store phone number is present$")
    public void iVerifyStorePhoneNumberIsPresent() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.stores::exists, 10);
        Assert.assertTrue(bopsModal.storePhone.isDisplayed());
        logger.info("Store phone number is present as expected");
    }

    @And("^I tap on store address link$")
    public void iTapOnStoreAddressLink() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.mapLink::isDisplayed, 10);
        bopsModal.mapLink.click();
        logger.info("Store address link was successfully opened");
    }

    @Then("^I verify bops map is present$")
    public void iVerifyBopsMapIsPresent() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.map::isDisplayed, 10);
        Assert.assertTrue("Bops Map is not present", bopsModal.map.isDisplayed());
        logger.info("Bops map is present as expected");
    }

    @Then("^I should see alert message display$")
    public void iShouldSeeAlertMessageDisplay() {
        String errorText = "Unfortunately there are no stores within 25 miles of where you live. You can always have your item(s) shipped or choose a preferred store outside of your area.";
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.error::isDisplayed, 10);
        Assert.assertEquals(errorText, bopsModal.error.getText());
        logger.info("Alert message displays as expected");
    }

    @When("^I save the store's pick up location from bops modal$")
    public void iSaveTheStoreSPickUpLocationFromBopsModal() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.selectedStoreName::isEnabled, 10);
        bopsModal.selectedStoreName.click();
        bopsStoreName = bopsModal.selectedStoreName.getText();
        logger.info("Store pick ip location was successfully saved");
    }

    @Then("^PDP BOPS radio button should display the saved store$")
    public void pdpBOPSRadioButtonShouldDisplayTheSavedStore() {
        ProductDisplayMew pdp = Navigate.get(ProductDisplayMew.class);
        Wait.until(pdp.productBopsStoreName::isDisplayed);
        storeName = pdp.productBopsStoreName.getText();
        Assert.assertEquals("Store name is different", storeName, bopsStoreName);
        logger.info("PDP BOPS radio button is displaying the saved store");
    }

    @When("^I tap on save button$")
    public void iTapOnSaveButton() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.saveButton::exists, 10);
        bopsModal.saveButton.click();
        logger.info("Save button selected");
    }

    @Then("^I should see the miles rendered$")
    public void iShouldSeeTheMilesRendered() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.storeDistance::isDisplayed, 10);
        if (zip.equalsIgnoreCase("10001")) {
            distanceOne = bopsModal.storeDistance.getText();
        } else {
            distanceTwo = bopsModal.storeDistance.getText();
        }
        logger.info("Miles are rendering as expected");
    }

    @Then("^I should see the distance renders as expected$")
    public void iShouldSeeTheDistanceRendersAsExpected() {
        Assert.assertNotEquals(distanceOne, distanceTwo);
        logger.info("Distance is rendering as expected");
    }

    @When("^I get the store name$")
    public void iGetTheStoreName() {
        ChangePickupStoreDialogMew bopsModal = Navigate.get(ChangePickupStoreDialogMew.class);
        Wait.until(bopsModal.stores::exists, 10);
        storeName = bopsModal.selectedStoreName.getText();
    }

    @Then("^I verify cookie value renders the store number$")
    public void iVerifyCookieValueRendersTheStoreNumber() {
        ProductDisplayMew pdp = Navigate.get(ProductDisplayMew.class);
        String cookieValue = Cookies.getCookieValue("MISCGCs");
        logger.info(cookieValue);
        if (cookieValue != null) {
            int storeNumber = Integer.parseInt(cookieValue.substring(cookieValue.length() - 3));
            switch (storeName) {
                case "Short Hills":
                    Assert.assertEquals(storeNumber, 347);
                    break;
                case "59th Street":
                    Assert.assertEquals(storeNumber, 343);
                    break;
                default:
                    logger.info("The store number is unknown");
            }
        } else {
            Assert.assertTrue("The cookie is not generated", false);
        }
    }
}
