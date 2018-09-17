package com.macys.sdt.projects.Selection.Registry.steps.website.bcom;

/**
 * Created by U063689 on 6/29/2017.
 */


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Selection.Registry.actions.website.RegistryActions;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.onPage;

public class BvrRedesign{

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);
    private UserProfile regUser;

    private final RegistryActions registryActions = new RegistryActions();


    @And("^I should see the registrant and coregistrant names are present in the header$")
    public void iShouldSeeTheRegistrantAndCoregistrantNamesArePresentInTheHeader() throws Throwable {
        regUser = TestUsers.getCustomerInformation();
        String firstName = regUser.getRegistry().getContactInfo().getFirstName();
        String lastName = regUser.getRegistry().getContactInfo().getLastName();
        String coregistrantFirstName = regUser.getRegistry().getCoRegistrantFirstName();
        String coregistrantLastName = regUser.getRegistry().getCoRegistrantLastName();
        String registrantName = String.format("%s %s", firstName, lastName);
        String coregistrantName = String.format("and %s %s", coregistrantFirstName, coregistrantLastName);
        if (onPage("registry_bvr")) {
            String registrant_details = Elements.getText("registry_bvr.header_names_fn").toLowerCase().trim();
            logger.info("Registrant Names "+ registrantName);
            logger.info("Registrant Details " + registrant_details);
            String co_registrant_details = Elements.getText("registry_bvr.header_names_ln").toLowerCase().trim();
            logger.info("Coregistrant Names "+ coregistrantName);
            logger.info("Coregistrant Details " + co_registrant_details);
            Assert.assertTrue(registrantName.equalsIgnoreCase(registrant_details));
            Assert.assertTrue(coregistrantName.equalsIgnoreCase(co_registrant_details));
        }
        else if (onPage("registry_gvr")){
            String registrantDetails = Elements.getText("registry_gvr.header_names_fn").trim();
            logger.info("Registrant Details " + registrantDetails);
            String coregistrantDetails = Elements.getText("registry_gvr.header_names_ln").trim();
            logger.info("Coregistrant Details " + coregistrantDetails);
            Assert.assertTrue(registrantName.equalsIgnoreCase(registrantDetails));
            Assert.assertTrue(coregistrantName.equalsIgnoreCase(coregistrantDetails));
        }
        else {
            Assert.fail("User is currently not in Registry BVR/GVR Page");
        }
    }


    @And("^I should see the event date is present in the header$")
    public void iShouldSeeTheEventDateIsPresentInTheHeader() throws Throwable {
        regUser = TestUsers.getCustomerInformation();
        String eventDateDay = regUser.getRegistry().getEventDay();
        String eventDateMonth = regUser.getRegistry().getEventMonth();
        String eventDateYear = regUser.getRegistry().getEventYear();
        if (onPage("registry_bvr")) {
            String eventDate = String.format("%s %s, %s", eventDateMonth.substring(0, 3), eventDateDay, eventDateYear);
            logger.info(Elements.getText("registry_bvr.registry_date"));
            Assert.assertTrue("Even Date listed in the header is not same as the registry data event date", eventDate.equals(Elements.getText("registry_bvr.registry_date")));
        }
        else if (onPage("registry_gvr")){
            String eventDate = String.format("%s %s, %s", eventDateMonth.substring(0, 3), eventDateDay, eventDateYear);
            logger.info(Elements.getText("registry_gvr.registry_date"));
            Assert.assertTrue("Even Date listed in the header is not same as the registry data event date", eventDate.equals(Elements.getText("registry_gvr.registry_date")));
        }
        else {
            Assert.fail("User is NOT on registry BVR/GVR page");
        }
    }


    @And("^I should see that days's left until event is present in the header$")
    public void iShouldSeeThatDaysLLeftUntilEventIsPresentInTheHeader() {
        if (onPage("registry_bvr")) {
            Assert.assertTrue("Days Left is not present in the header", Elements.findElement("registry_bvr.days_left").getText().contains("Days Left"));
        }
        else if(onPage("registry_gvr")){
            Assert.assertTrue("Days Left is not present in the header", Elements.findElement("registry_gvr.days_left").getText().contains("Days Left"));
         }
        else Assert.fail("User is not on GVR or BVR page");
    }

    @And("^I should see the barcode is present in the footer$")
    public void iShouldSeeTheBarcodeIsPresentInTheFooter() throws Throwable {
        if(onPage("registry_bvr")){
            Assert.assertTrue("Barcode is not present in the footer", Elements.elementPresent("registry_bvr.footer_barcode"));
        }
        else if (onPage("registry_gvr")){
            Assert.assertTrue("Barcode is not present in the footer", Elements.elementPresent("registry_gvr.footer_barcode"));
        }
        else {
            Assert.fail("User is not on GVR or BVR page");
        }
    }


    @And("^I (should|should not) see the shipping message is present in the footer:$")
    public void iShouldSeeTheShippingMessageIsPresentInTheFooter(String shouldOrNot ,List<String> expected_messages) {
        if (shouldOrNot.equalsIgnoreCase("should") && onPage("registry_bvr")) {
            Assert.assertTrue("Shipping message in the footer is not correct", Elements.getText("registry_bvr.footer_ship_msg_one").trim().contains(expected_messages.get(0)));
            Assert.assertTrue("Shipping message in the footer is not correct", Elements.getText("registry_bvr.footer_ship_msg_one").trim().contains(expected_messages.get(1)));
            logger.info("Expected shipping messages verification is successful");
        } else if (shouldOrNot.equalsIgnoreCase("should not") && onPage("registry_gvr")) {
            Assert.assertFalse("Shipping message in the footer is not correct", Elements.getText("registry_bvr.footer_ship_msg_one").trim().contains(expected_messages.get(0)));
            Assert.assertFalse("Shipping message in the footer is not correct", Elements.getText("registry_bvr.footer_ship_msg_one").trim().contains(expected_messages.get(1)));
            logger.info("Expected shipping messages verification is successful");
        } else {
            Assert.fail("User is not on BVR/GVR page");
        }
    }


    @When("^I click on View My Items in left panel of Registry Manager$")
    public void iClickOnViewMyItemsInLeftPanelOfRegistryManager() throws Throwable {
        if(onPage("registry_manager")){
            Clicks.click("registry_manager.view_my_item");
            Wait.forPageReady();
        } else {
            Assert.fail("User is not on registry manager page");
        }
    }

    @When("^I click on Items Added in left panel of Registry Manager$")
    public void iClickOnItemsAddedInLeftPanelOfRegistryManager() throws Throwable {
        if(onPage("registry_manager")){
            Clicks.click("registry_manager.goto_items_added");
        }
        else Assert.fail("User is not on registry manager page");
    }

    @When("^I click on Go Back to Registry Manager link in the BVR header$")
    public void iClickOnLinkInTheBVRHeader() throws Throwable {
        if(onPage("registry_bvr")){
            Clicks.click("registry_bvr.goto_registry_manager");
        }
        else Assert.fail("User is not on registry BVR page");
    }

    @When("^I click on Pencil icon in the lower left corner of the product image$")
    public void iClickOnPencilIconInTheLowerLeftCornerOfTheProductImage() throws Throwable {
        if(onPage("registry_bvr")){
            Assert.assertTrue("The registry does not contain any items", Elements.anyPresent("registry_bvr.bvr_prod_list"));
            WebElement selectedElem = registryActions.selectRandomElementFromRegistry();
            registryActions.getAttributesOfSelectedRegistryItem(selectedElem);
            selectedElem.findElement(By.className("thumbnail-QP-icon-container")).click();
            Wait.untilElementPresent("registry_bvr.registry_atb_overlay");
        }
        else Assert.fail("User is not on registry BVR page");
    }

    @Then("^I verify basic elements of the (BVR|GVR) QP$")
    public void iVerifyBasicElementsOfTheBVRQP(String registryType) throws Throwable {
        if (registryType.equalsIgnoreCase("BVR")) {
            Assert.assertTrue("Registry BVR QP container is not visible", Elements.elementPresent("registry_bvr.bvr_qp_container"));
            Assert.assertTrue("Registry BVR QP Remove Button is not visible", Elements.elementPresent("registry_bvr.bvr_qp_remove_button"));
            Assert.assertTrue("Registry BVR QP Save Button is not visible", Elements.elementPresent("registry_bvr.bvr_qp_save_button"));
            Assert.assertTrue("Registry BVR QP Product Details link is not visible", Elements.elementPresent("registry_bvr.bvr_qp_goto_prod_details"));
            Assert.assertTrue("Registry BVR QP Still Needs items count is not visible", Elements.elementPresent("registry_bvr.bvr_qp_items_needed"));
            Assert.assertTrue("Registry BVR QP Wants items count is not visible", Elements.elementPresent("registry_bvr.bvr_qp_items_requested"));
            Assert.assertTrue("Registry BVR QP Product Name is not visible", Elements.elementPresent("registry_bvr.bvr_qp_product_name"));
            Assert.assertTrue("Registry BVR QP Brand Name is not visible", Elements.elementPresent("registry_bvr.bvr_qp_brand_name"));
            if(!registryActions.getProductColor().equals("no color")){
                Assert.assertTrue("Registry BVR QP Product color is not visible, but should be", Elements.findElement("registry_bvr.bvr_qp_color").getText().equalsIgnoreCase(registryActions.getProductColor()));
            }
            if(!registryActions.getProductSize().equals("no size")){
                Assert.assertTrue("Registry BVR QP Product size is not visible, but should be", Elements.findElement("registry_bvr.bvr_qp_size").getText().equalsIgnoreCase(registryActions.getProductSize()));
            }

        }
        else if (registryType.equalsIgnoreCase("GVR")){
            Assert.assertTrue("Registry GVR QP container is not visible", Elements.elementPresent("registry_gvr.gvr_qp_container"));
            Assert.assertTrue("Registry BVR QP Product Details is not visible", Elements.elementPresent("registry_gvr.gvr_qp_details"));
            Assert.assertTrue("Registry GVR QP Add To Bag button is not visible", Elements.elementPresent("registry_gvr.gvr_qp_add_to_bag_button"));
            Assert.assertTrue("Registry GVR QP Quantity dropdown is not visible", Elements.elementPresent("registry_gvr.gvr_qp_qty_dropdown"));
            Assert.assertTrue("Registry GVR QP Still Needs items count is not visible", Elements.elementPresent("registry_gvr.gvr_qp_items_needed"));
            Assert.assertTrue("Registry GVR QP Wants items count is not visible", Elements.elementPresent("registry_gvr.gvr_qp_items_requested"));
            Assert.assertTrue("Registry GVR QP Product Name is not visible", Elements.elementPresent("registry_gvr.gvr_qp_product_name"));
            Assert.assertTrue("Registry GVR QP Brand Name is not visible", Elements.elementPresent("registry_gvr.gvr_qp_brand_name"));
            if(!registryActions.getProductColor().equals("no color")){
                Assert.assertTrue("Registry GVR QP Product color is not visible, but should be", Elements.findElement("registry_gvr.gvr_qp_color").getText().equalsIgnoreCase(registryActions.getProductColor()));
            }
            if(!registryActions.getProductSize().equals("no size")){
                Assert.assertTrue("Registry GVR QP Product size is not visible, but should be", Elements.findElement("registry_gvr.gvr_qp_size").getText().equalsIgnoreCase(registryActions.getProductSize()));
            }
        }
        else Assert.fail("QP is not opened");
    }

    @When("^I click on Wants/Still Needs row below the product image$")
    public void iClickOnWantsStillNeedsRowBelowTheProductImage() throws Throwable {
        if(onPage("registry_bvr")) {
            Assert.assertTrue("The registry does not contain any items", Elements.anyPresent("registry_bvr.bvr_prod_list"));
            WebElement selectedElem = registryActions.selectRandomElementFromRegistry();
            registryActions.getAttributesOfSelectedRegistryItem(selectedElem);
            selectedElem.findElement(By.className("thumbnail-wants-needs")).click();
            Wait.untilElementPresent("registry_bvr.registry_atb_overlay");
        }
        else Assert.fail("User is not on registry BVR page");
    }

    @And("^I click on Save Changes button without editing any of the text boxes$")
    public void iClickOnSaveButtonWithoutEditingAnyOfTheTextBoxes() throws Throwable {
        if (Elements.elementPresent("bvr_qp.bvr_qp_container")){
            Clicks.click("bvr_qp.bvr_qp_save_button");
        }
    }

    @Then("^I verify BVR QP closes$")
    public void iVerifyBVRQPCloses() throws Throwable {
        Assert.assertFalse(Elements.elementPresent("bvr_qp.bvr_qp_container"));
    }


    @And("^I should see the overlay with the following message:$")
    public void iShouldSeeTheOverlayWithTheFollowingMessage(List<String> message) throws Throwable {
        Assert.assertTrue(Elements.elementPresent("registry_bvr.bvr_update_success_overlay"));
        Assert.assertTrue(Elements.getText("registry_bvr.bvr_update_success_overlay").toLowerCase().equals(message.get(0).toLowerCase().trim()));
    }


    @And("^I (should|should not) see Back to Registry Manager link in the header$")
    public void iShouldSeeBackToRegistryManagerLinkInTheHeader(String shouldOrNot) throws Throwable {
        if (shouldOrNot.equalsIgnoreCase("should") && onPage("registry_bvr")) {
            Assert.assertTrue("Back To Registry Manager link is not present in the BVR header", Elements.elementPresent("registry_bvr.registry_bvr.goto_registry_manager"));
        }
        else if (shouldOrNot.equalsIgnoreCase("should not") && onPage("registry_gvr")){
            Assert.assertFalse("Back To Registry Manager link is present in the GVR header, but should not be", Elements.elementPresent("registry_bvr.registry_bvr.goto_registry_manager"));
        } else{
            Assert.fail("User is not on registry BVR/GVR page ");
        }

    }


    @And("^I navigate to Find Registry page using THE REGISTRY flyout$")
    public void iNavigateToFindRegistryPageUsingTHEREGISTRYFlyout() throws Throwable {
        if (onPage("home")){
            Clicks.hoverForSelection("home.goto_wedding_registry");
            Clicks.click("home.registry_find_flyout");
            Wait.forPageReady();
        }
    }

    @And("^I search for existing couple's registry from Find Registry page$")
    public void iSearchForExistingCoupleSRegistryFromFindRegistryPage() throws Throwable {
        if (onPage("find_registry")){
            String[] first_last_name = registryActions.searchForValidRegistry();
            TextBoxes.typeTextbox("find_registry.first_name", first_last_name[0]);
            TextBoxes.typeTextbox("find_registry.last_name", first_last_name[1]);
            Clicks.click("find_registry.find_registry_button");
        } else { Assert.fail("Not on Registry Find Page");
        }
    }

    @And("^I navigate to GVR page by url$")
    public void iNavigateGVRPage(){
        String[] array = MainRunner.currentURL.split("com");
        logger.info(array[0] + "com/registry/wedding/guest/?perfectProxy=true&registryId="+ TestUsers.getCustomerInformation().getRegistry().getId());
        Navigate.visit(array[0] + "com/registry/wedding/guest/?perfectProxy=true&registryId="+ TestUsers.getCustomerInformation().getRegistry().getId());
        Wait.forPageReady();
        Clicks.clickIfPresent("registry_gvr.close_container");
        if(onPage("registry_gvr")){
           logger.info("Successfully navigated to GVR page");
        }
        else Assert.fail();
    }



    @And("^I navigate to GVR page with products added by url$")
    public void iNavigateToGVRPageWithProductsAddedByUrl() throws Throwable {
        ShopAndBrowse ShopAndBrowse = new ShopAndBrowse();
        Registry registrySteps = new Registry();
        registrySteps.iVisitTheWebSiteAsARegistryUserWithoutCheckout("gvr");
        ShopAndBrowse.iNavigateToPdp("registrable", "orderable");
        ShopAndBrowse.I_add_registry_product_to_BVR_page_from_standard_PDP_Page();
        Wait.forPageReady();
        registrySteps.registrySignOut();
        iNavigateGVRPage();
    }

    @When("^I click on the icon in the lower left corner of the product image$")
    public void iClickOnTheIconInTheLowerLeftCornerOfTheProductImage(){
        if(onPage("registry_gvr")){
            Clicks.click("registry_gvr.qp_icon");
        }
        else Assert.fail("not on GVR page");
    }

    @And("^I verify (GVR|BVR) has no items$")
    public void iVerifyGVRHasNoItems(String registryType){
        Wait.forPageReady();
        if(registryType.equalsIgnoreCase("GVR") && onPage("registry_gvr")){
            Assert.assertFalse("Items are present in the empty registry, should not be", Elements.anyPresent("registry_gvr.gvr_prod_list"));
        }
        else if (registryType.equalsIgnoreCase("BVR") && onPage("registry_bvr")){
            Assert.assertFalse("Items are present in the empty registry, should not be", Elements.anyPresent("registry_gvr.bvr_prod_list"));
        }
        else logger.info("not on Gvr/Bvr page");
    }

    @Then("^I should see the message in the empty GVR page header:$")
    public void iShouldSeeTheMessageInTheGVRPageHeader(String expected_message){
        Assert.assertTrue("Empty Registry GVR message is not present on the page", Elements.getText("registry_gvr.gvr_empty_message").contains(expected_message));
    }


    @And("^I should see Shop Gift Cards link$")
    public void iShouldSeeShopGiftCardsLink(){
        Wait.forPageReady();
        Assert.assertTrue("Empty Registry GVR Shop Gift Cards link is not present on the page", Elements.elementPresent("registry_gvr.gvr_empty_shop_gifts_link"));
    }

    @And("^I should see Little Brown Card bag icon$")
    public void iShouldSeeLittleBrownCardBagIcon(){
        Wait.forPageReady();
        Assert.assertTrue("Empty Registry GVR Shop Gift Cards BAG ICON is not present on the page", Elements.elementPresent("registry_gvr.gvr_empty_shop_gifts_image"));
    }

    @And("^I should land on Gift Card browse page when clicking on either Shop Gift Cards link or Little Brown Card bag icon$")
    public void iShouldLandOnGiftCardBrowsePageWhenClickingOnEitherShopGiftCardsLinkOrLittleBrownCardBagIcon() throws Throwable {
        Clicks.click("registry_gvr.gvr_empty_shop_gifts_image");
        logger.info("Clicked on the Little Brown Card bag icon");
        Assert.assertTrue("Empty Registry GVR BAG ICON link is not correct", MainRunner.currentURL.contains("/shop/gifts/gift-cards?id=3954"));
        Navigate.browserBack();
        Clicks.click("registry_gvr.gvr_empty_shop_gifts_link");
        logger.info("Clicked on the Shop Gift Cards link");
        Assert.assertTrue("Empty Registry GVR Shop Gift Cards link is not correct", MainRunner.currentURL.contains("/shop/gifts/gift-cards?id=3954"));
    }

    @When("^I add item to bag from (GVR|BVR) page$")
    public void iAddItemToBagFromGVRPage(String registryType){
        Wait.forPageReady();
        WebElement selectedElem = registryActions.selectRandomElementFromRegistry();
        registryActions.getAttributesOfSelectedRegistryItem(selectedElem);
        if (registryType.equalsIgnoreCase("gvr")) {
            Assert.assertTrue("The registry does not contain any items", Elements.anyPresent("registry_gvr.gvr_prod_list"));
            selectedElem.findElement(By.className("thumbnail-atb")).click();
            Wait.untilElementPresent("registry_gvr.registry_atb_overlay");
            Assert.assertTrue("Registry GVR ATB overlay is not visible", Elements.elementPresent("registry_gvr.registry_atb_overlay"));
        }
         else if (registryType.equalsIgnoreCase("bvr")) {
            Assert.assertTrue("The registry does not contain any items", Elements.anyPresent("registry_bvr.bvr_prod_list"));
            selectedElem.findElement(By.className("thumbnail-atb")).click();
            Wait.untilElementPresent("registry_bvr.registry_atb_overlay");
            Assert.assertTrue("Registry GVR ATB overlay is not visible", Elements.elementPresent("registry_bvr.registry_atb_overlay"));
        }
    }


    @Then("^I verify all attributes of registry ATB overlay$")
    public void iVerifyAllAttributesOfGVRATBOverlay() throws Throwable {
        if(onPage("registry_gvr")) {
            if (!Elements.elementPresent("registry_gvr.registry_atb_overlay")) {
                Assert.fail("ATB dialog is not present");
            }
            Assert.assertTrue("Registry ATB overlay Checkout button is not visible", Elements.elementPresent("registry_gvr.registry_checkout_button"));
            Assert.assertTrue("Registry ATB overlay Continue Shopping button is not visible", Elements.elementPresent("registry_gvr.registry_continue_shopping"));
            Assert.assertTrue("On Registry ATB overlay Product Name does not correspond to the product added", Elements.findElement("registry_gvr.registry_atb_prod_name").getText().contains(registryActions.getProductName().trim()) || registryActions.getProductName().contains(Elements.findElement("registry_gvr.registry_atb_prod_name").getText()) || Elements.findElement("registry_gvr.registry_atb_prod_name").getText().contains(registryActions.getProductBrand()));
            Assert.assertTrue("On Registry ATB overlay Product Image is not visible", Elements.elementPresent("registry_gvr.registry_atb_prod_image"));
            Assert.assertTrue("Registry ATB overlay Item Price listed is not same as the price of the item added", Elements.findElement("registry_gvr.registry_atb_price").getText().equalsIgnoreCase(registryActions.getProductPrice().trim()));
            Assert.assertTrue("Registry ATB overlay Item Qty listed is not same as the qty of the item added", Elements.elementPresent("registry_gvr.registry_atb_qty"));
            if (Elements.elementPresent("registry_gvr.registry_atb_prod_color")) {
                Assert.assertTrue("Registry ATB overlay Item Color listed is not same as the color of the item added", Elements.findElement("registry_gvr.registry_atb_prod_color").getText().contains(registryActions.getProductColor()));
            }
            if (Elements.elementPresent("registry_gvr.registry_atb_prod_size")) {
                Assert.assertTrue("Registry ATB overlay Item Size listed is not same as the size of the item added", Elements.findElement("registry_gvr.registry_atb_prod_size").getText().contains(registryActions.getProductSize()));
            }
        }
        else if (onPage("registry_bvr")){
            if (!Elements.elementPresent("registry_bvr.registry_atb_overlay")) {
                Assert.fail("ATB dialog is not present");
            }
            Assert.assertTrue("Registry ATB overlay Checkout button is not visible", Elements.elementPresent("registry_bvr.registry_checkout_button"));
            Assert.assertTrue("Registry ATB overlay Continue Shopping button is not visible", Elements.elementPresent("registry_bvr.registry_continue_shopping"));
            Assert.assertTrue("On Registry ATB overlay Product Name does not correspond to the product added", Elements.findElement("registry_bvr.registry_atb_prod_name").getText().contains(registryActions.getProductName().trim()) || registryActions.getProductName().contains(Elements.findElement("registry_gvr.registry_atb_prod_name").getText()) || Elements.findElement("registry_gvr.registry_atb_prod_name").getText().contains(registryActions.getProductBrand()));
            Assert.assertTrue("On Registry ATB overlay Product Image is not visible", Elements.elementPresent("registry_bvr.registry_atb_prod_image"));
            Assert.assertTrue("Registry ATB overlay Item Price listed is not same as the price of the item added", Elements.findElement("registry_bvr.registry_atb_price").getText().equalsIgnoreCase(registryActions.getProductPrice().trim()));
            Assert.assertTrue("Registry ATB overlay Item Qty listed is not same as the qty of the item added", Elements.elementPresent("registry_bvr.registry_atb_qty"));
            if (Elements.elementPresent("registry_bvr.registry_atb_prod_color")) {
                Assert.assertTrue("Registry ATB overlay Item Color listed is not same as the color of the item added", Elements.findElement("registry_bvr.registry_atb_prod_color").getText().contains(registryActions.getProductColor()));
            }
            if (Elements.elementPresent("registry_bvr.registry_atb_prod_size")) {
                Assert.assertTrue("Registry ATB overlay Item Size listed is not same as the size of the item added", Elements.findElement("registry_bvr.registry_atb_prod_size").getText().contains(registryActions.getProductSize()));
            }
        }
    }

    @And("^I open GVR QP of an item$")
    public void iOpenGVRQPOfAnItem(){
        Wait.forPageReady();
        if(onPage("registry_bvr")){
            Clicks.click("registry_bvr.pencil_icon");
        }
        else if (onPage("registry_gvr")) {
            Assert.assertTrue("no upper thumbnail of item is present", Elements.anyPresent("registry_gvr.thumbnail_upper_content"));
            WebElement selectedElem = registryActions.selectRandomElementFromRegistry();
            selectedElem.findElement(By.className("thumbnail-QP-icon-container")).click();
            registryActions.getAttributesOfSelectedRegistryItem(selectedElem);
        }
    }


    @Then("^I add item to bag by clicking on ATB button on registry GVR QP$")
    public void iAddItemToBagByClickingOnATBButtonOnRegistryQP() throws Throwable {
        Clicks.click("registry_gvr.gvr_qp_add_to_bag_button");
        Wait.untilElementPresent("registry_gvr.registry_atb_overlay");
    }

    @Then("^I should land on the bag page with the item present$")
    public void iShouldLandOnTheBagPageWithTheItemPresent() throws Throwable {
        if (onPage("shopping_bag")){
            Assert.assertTrue("Registry Item Description in the bag page does not contain brand name and/or product name", Elements.getText("shopping_bag.item_brand_product_name").toLowerCase().contains(registryActions.getProductName().toLowerCase())||Elements.getText("shopping_bag.item_brand_product_name").toLowerCase().contains(registryActions.getProductBrand().toLowerCase()));
            if(Elements.elementPresent("shopping_bag.item_color")){
                Assert.assertTrue("Registry Item in the bag page does not have correct color listed", Elements.getText("shopping_bag.item_color").toLowerCase().contains(registryActions.getProductColor().toLowerCase()));
            }
            if(Elements.elementPresent("shopping_bag.item_size")){
                Assert.assertTrue("Registry Item in the bag page does not have correct size listed", Elements.getText("shopping_bag.item_size").toLowerCase().contains(registryActions.getProductSize().toLowerCase()));
            }
        }

    }

    @And("^I click on \"([^\"]*)\" button on registry ATB overlay$")
    public void iClickOnButtonOnRegistryATBOveraly(String button) throws Throwable {
        if(onPage("registry_gvr")) {
            if (Elements.elementPresent("registry_gvr.registry_atb_overlay")) {
                if (button.equalsIgnoreCase("checkout")) {
                    Clicks.click("registry_gvr.registry_checkout_button");
                    Wait.untilElementPresent("shopping_bag.continue_checkout_image");
                    Assert.assertTrue("Not on the Bag Page", onPage("shopping_bag"));
                } else if (button.equalsIgnoreCase("continue shopping")) {
                    Clicks.click("registry_gvr.registry_continue_shopping");
                    Assert.assertFalse("Registry ATB overlay did not close when Continue Shopping button was clicked", Elements.elementPresent("registry_gvr.registry_atb_overlay"));
                    Assert.assertTrue("Not on the Bag Page", onPage("registry_gvr"));
                }
            }
            else Assert.fail("ATB overlay is not visible");
        }
        else if (onPage("registry_bvr"))    {
            if (Elements.elementPresent("registry_bvr.registry_atb_overlay")) {
                if (button.equalsIgnoreCase("checkout")) {
                    Clicks.click("registry_bvr.registry_checkout_button");
                    Wait.untilElementPresent("shopping_bag.continue_checkout_image");
                    Assert.assertTrue("Not on the Bag Page", onPage("shopping_bag"));
                } else if (button.equalsIgnoreCase("continue shopping")) {
                    Clicks.click("registry_bvr.registry_continue_shopping");
                    Assert.assertFalse("Registry ATB overlay did not close when Continue Shopping button was clicked", Elements.elementPresent("registry_bvr.registry_atb_overlay"));
                    Assert.assertTrue("Not on the Bag Page", onPage("registry_bvr"));
                }
            }
            else Assert.fail("ATB overlay is not visible");
        }
        else Assert.fail("not on registry BVR/GVR page");
    }

    @When("^I click on Find In Store link on (BVR|GVR) product thumbnail$")
    public void iClickOnFindInStoreLinkOnBVRProductThumbnail(String bvr_gvr){
        if (bvr_gvr.equalsIgnoreCase("bvr") && onPage("registry_bvr")){
            Clicks.click("registry_bvr.find_in_store_link");
            Wait.untilElementPresent("registry_bvr.search_button");
            Assert.assertTrue(Elements.elementPresent("registry_bvr.search_button"));
        }
        else if (bvr_gvr.equalsIgnoreCase("gvr") && onPage("registry_gvr")){
            Clicks.click("registry_gvr.find_in_store_link");
            Wait.untilElementPresent("registry_gvr.search_button");
            Assert.assertTrue(Elements.elementPresent("registry_bops_overlay.bops_overlay_box"));
        }
    }

    @And("^I enter zip code into (BVR|GVR) BOPS overlay$")
    public void iEnterZipCodeIntoBVRBOPSOverlay(String bvr_gvr) {
        if (bvr_gvr.equalsIgnoreCase("bvr") && onPage("registry_bvr")) {
            TextBoxes.typeTextbox("registry_bvr.zip_code", "10022");
        }
        else if (bvr_gvr.equalsIgnoreCase("gvr") && onPage("registry_gvr")){
            TextBoxes.typeTextbox("registry_gvr.zip_code", "10022");
        }
    }


    @And("^I click search on (BVR|GVR) BOPS overlay$")
    public void iClickSearchOnBVRBOPSOverlay(String bvr_gvr) {
        if (bvr_gvr.equalsIgnoreCase("bvr") && onPage("registry_bvr")) {
            Clicks.click("registry_bvr.search_button");
        } else if (bvr_gvr.equalsIgnoreCase("gvr") && onPage("registry_gvr")) {
            Clicks.click("registry_gvr.search_button");
        }
    }

    @Then("^I should see list of stores$")
    public void iShouldSeeListOfStores(){
        if (onPage("registry_bvr")) {
            Wait.untilElementPresent("registry_bvr.result_available_stores");
            Assert.assertTrue("List of stores is not present", Elements.elementPresent("registry_bvr.result_available_stores"));
        } else if (onPage("registry_gvr")) {
            Wait.untilElementPresent("registry_gvr.result_available_stores");
            Assert.assertTrue("List of stores is not present", Elements.elementPresent("registry_gvr.result_available_stores"));
        }

    }

    @And("^I should see that ATB is disabled$")
    public void iShouldSeeThatATBIsDisabled(){
        Assert.assertFalse("ATB is disabled", Elements.findElement("registry_bvr.bops_atb_button").isEnabled());
    }

    @And("^I should see that none of the stores are selected$")
    public void iShouldSeeThatNoneOfTheStoresAreSelected(){
        List<WebElement> stores = Elements.findElements("registry_bvr.store_radio_button");
        for(WebElement store : stores){
            if (store.getAttribute("class").contains("checked")){
                Assert.fail("One of the stores is selected");
            }
            else {
                logger.info("None of the stores are checked");
            }
        }
    }

    @When("^I add item to bag from (GVR|BVR) BOPS overlay$")
    public void iAddItemToBagFromBVRBOPSOverlay(String registryType){
        Wait.forPageReady();
        WebElement selectedElem = registryActions.selectRandomElementFromRegistry();
        registryActions.getAttributesOfSelectedRegistryItem(selectedElem);
        if (registryType.equalsIgnoreCase("gvr")) {
            Assert.assertTrue("The registry does not contain any items", Elements.anyPresent("registry_gvr.gvr_prod_list"));
            selectedElem.findElement(By.className("thumbnail-find-in-store")).click();
            iEnterZipCodeIntoBVRBOPSOverlay("gvr");
            iClickSearchOnBVRBOPSOverlay("gvr");
            registryActions.selectRandomStoreRegistryBOPSOverlay();
            Clicks.click("registry_gvr.bops_atb_button");
            Wait.untilElementPresent("registry_gvr.registry_atb_overlay");
            Assert.assertTrue("Registry GVR ATB overlay is not visible", Elements.elementPresent("registry_gvr.registry_atb_overlay"));
        }
        else if (registryType.equalsIgnoreCase("bvr")) {
            Assert.assertTrue("The registry does not contain any items", Elements.anyPresent("registry_bvr.bvr_prod_list"));
            selectedElem.findElement(By.className("thumbnail-find-in-store")).click();
            iEnterZipCodeIntoBVRBOPSOverlay("bvr");
            iClickSearchOnBVRBOPSOverlay("bvr");
            registryActions.selectRandomStoreRegistryBOPSOverlay();
            Clicks.click("registry_bvr.bops_atb_button");
            Wait.untilElementPresent("registry_bvr.registry_atb_overlay");
            Assert.assertTrue("Registry GVR ATB overlay is not visible", Elements.elementPresent("registry_bvr.registry_atb_overlay"));
        }
    }

    @And("^I should see that Pick Up In Store radio button is selected for that item$")
    public void iShouldSeeThatPickUpInStoreRadioButtonIsSelectedForThatItem() throws Throwable {
        if (onPage("shopping_bag")){
            Assert.assertTrue(Elements.findElement("shopping_bag.pick_it_up_button_radio").getAttribute("class").contains("checked"));
        }
        else {
            Assert.fail("Not in the bag page");
        }
    }


}