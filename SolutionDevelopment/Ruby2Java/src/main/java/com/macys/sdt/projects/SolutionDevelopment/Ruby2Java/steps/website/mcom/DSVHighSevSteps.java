package com.macys.sdt.projects.SolutionDevelopment.Ruby2Java.steps.website.mcom;

import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.ConvertedSteps;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.ShopAppClient;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DSVHighSevSteps extends ConvertedSteps {
    private static final Logger logger = LoggerFactory.getLogger(DSVHighSevSteps.class);

    @Then("^I verify the display of registry search result page$")
    public void i_verify_the_display_of_registry_search_result_page() {
        if (!url().contains("shop/registry/wedding/search?keyword=")) {
            Assert.fail("not on the registry search result page. Current url = " + url());
        }
    }

    @Then("^I verify the international context page$")
    public void i_verify_the_international_context_page() {
        if (!Wait.untilElementPresent("international_shipping.ship_to_us_address")) {
            throw new EnvException("'Ship to US' option is not visible..... ");
        }
        if (!Wait.untilElementPresent("international_shipping.country")) {
            throw new EnvException("'Country' selection is not visible..... ");
        }
        if (!Wait.untilElementPresent("international_shipping.currency")) {
            throw new EnvException("'Currency' selection is not visible..... ");
        }
    }

    @Then("^I should see click to call message$")
    public void I_should_see_click_to_call_message() {
        Wait.untilElementPresent("pdp.orderByPhone");
    }

    @And("^I select sofa product related attributes from PDP$")
    public void I_select_sofa_product_related_attributes_from_PDP() {
        ProductDisplay.selectRandomColor();
        ProductDisplay.selectRandomSize();
        By typeEl = Elements.element("product_display.type");
        if (Elements.elementPresent(typeEl)) {
            Clicks.clickRandomElement(typeEl);
        } else {
            logger.debug("No Type options found");
        }
    }

    @Given("^I am on the Our Stores Events Page as a guest user$")
    public void iAmOnTheOurStoresEventsPageAsAGuestUser() {
        new PageNavigation().I_visit_the_web_site_as_a_guest_user();
        Clicks.click("footer.goto_store_events");
        Wait.forPageReady();
    }

    @When("^I navigate to the \"([^\"]*)\" in our store pages$")
    public void iNavigateToTheInOurStorePages(String link) {
        String element;
        switch (link) {
            case "Store Locations & Hours":
                element = "goto_store_locations_and_hours";
                break;
            case "Catalogs":
                element = "goto_catalogs";
                break;
            case "Corporate Sales":
                element = "goto_corporate_name_address";
                break;
            case "Events":
                element = "goto_store_events";
                break;
            case "Shopping Services":
                element = "goto_customer_service";
                break;
            default:
                Assert.fail("Invalid link option: " + link);
                // needed so compiler knows "element" will always be initialized
                return;
        }
        Clicks.click("footer." + element);
        Wait.forPageReady();
    }

    @Then("^I verify that the \"([^\"]*)\" our store page has rendered$")
    public void iVerifyThatTheOurStorePageHasRendered(String page) {
        switch (page.toLowerCase()) {
            case "store locations & hours":
                shouldBeOnPage("store_locations");
                break;
            case "catalogs":
                shouldBeOnPage("catalog");
                break;
            default:
                throw new DataException("Invalid page: " + page);
        }
    }

    @And("^I verify the functionality in \"Store Locations & Hours\" page$")
    public void iVerifyTheFunctionalityInPage() {
        ProfileAddress address = TestUsers.getCustomer(null).getUser().getProfileAddress();
        TextBoxes.typeTextbox("store_locations.store_locator_search_box", address.getZipCode().toString());
        Clicks.click("store_locations.store_search_button");
        Wait.forPageReady();
        Elements.findElements("store_locations.visit_site").get(0).click();
        Wait.forPageReady();
        Assert.assertTrue("Store site page is not displayed", WebDriverManager.getCurrentUrl().contains("cm_sp=localmacys-_-visit-site"));
    }

    @Then("^I should see ShopAPP response for Shipping and Payment$")
    public void iShouldSeeShopAPPResponseForShippingAndPayment() {
        JSONObject payment = ShopAppClient.getPaymentOptions();
        JSONObject shipping = ShopAppClient.getShippingOptions();
        if (payment == null || shipping == null) {
            throw new EnvException("Failed to receive payment and shipping data from ShopAPP server");
        }
    }

    @Then("^I should see shipping summary section with \"?[eE]dit\"? button$")
    public void iShouldSeeShippingSummarySectionWithButton() {
        Elements.elementShouldBePresent("responsive_payment_guest_section.edit_payment_info");
    }

    @Then("^I should see payment summary section with \"?[eE]dit\"? button$")
    public void iShouldSeePaymentSummarySectionWithButton() {
        Elements.elementShouldBePresent("responsive_shipping_section.edit_shipping_section");
    }

    @Then("^I should see \"?place order\"? button in \"?(enabled|disabled)\"? mode$")
    public void iShouldSeePlaceOrderButtonInMode(String expectedStatus) {
        Elements.elementShouldBePresent("responsive_order_review_section.place_order");
        String status = Elements.getElementAttribute("responsive_order_review_section.place_order", "disabled");
        if (status.isEmpty() && expectedStatus.equalsIgnoreCase("disabled")) {
            throw new EnvException("Expected place order button to be enabled");
        } else if (!status.isEmpty() && !status.equalsIgnoreCase(expectedStatus)) {
            throw new EnvException("Expected place order button to be disabled");
        }
    }

    @Then("^I verify Registry home page with the pre production data$")
    public void iVerifyRegistryHomePageWithThePreProductionData() throws Throwable {
        shouldBeOnPage("registry_home");
        Elements.elementShouldBePresent("registry_home.find_box");
        Elements.elementShouldBePresent("registry_home.create_box");
        Elements.elementShouldBePresent("registry_home.manage_box");
    }

    @And("^I select \"(express|premium)\" shipping method$")
    public void iSelectShippingMethod(String method) throws Throwable {
        // the button doesn't activate until the second click in automation only. Why? Not a clue.
        Clicks.click("responsive_shipping_options." + method + "_shipping");
        Clicks.click("responsive_shipping_options." + method + "_shipping");
    }

    @And("^I continue checkout entering premium ineligible shipping address$")
    public void iContinueCheckoutEnteringPremiumIneligibleShippingAddress() throws Throwable {
        UserProfile profile = TestUsers.getCustomer(null);
        ProfileAddress address = profile.getUser().getProfileAddress();
        address.setAddressLine1("1045 Ashford Avenue");
        address.setCity("San Juan");
        address.setState("Puerto Rico");
        address.setZipCode("00907");
        address.setBestPhone("4084087288");
        new CheckoutSteps().I_checkout_until_I_reach_the_page_as_a_user("payment", "guest", null, null);
    }

    @Then("^I should see a special error message \"([^\"]*)\" is displayed$")
    public void iShouldSeeASpecialErrorMessageIsDisplayed(String expectedError) throws Throwable {
        Elements.elementShouldBePresent("responsive_checkout.error_message");
        String actualError = Elements.getText("responsive_checkout.error_message");
        if (!actualError.equalsIgnoreCase(expectedError)) {
            logger.info("Expected error message: \n" + expectedError + "\nActual error message: \n" + actualError);
            throw new EnvException("Error message did not match expectation");
        }
    }

    @Then("I verify the \"Store Locations & Hours\" our store page has rendered")
    public void iVerifyThatTheStoreLocationsHoursOurStoresPageHasRendered() {
        Assert.assertTrue(Wait.untilElementPresent("store_locations.verify_page"));
    }
}
