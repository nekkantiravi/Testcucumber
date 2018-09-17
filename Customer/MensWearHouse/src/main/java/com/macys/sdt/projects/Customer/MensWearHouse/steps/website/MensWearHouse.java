package com.macys.sdt.projects.Customer.MensWearHouse.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Customer.MensWearHouse.actions.website.MenswearHouseDigital;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.steps.MEW.MyAccount;
import com.macys.sdt.shared.steps.website.Returns;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import com.macys.sdt.projects.Customer.MensWearHouse.utils.db.TuxService;

import static com.macys.sdt.framework.interactions.Elements.elementPresent;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * Created by yc05pg3 on 1/13/2017.
 */
public class MensWearHouse extends StepUtils {

    @And("^I should see Tuxedo_Rental is bold$")
    public void tuxBold() throws Throwable {
        MenswearHouseDigital.tuxBold();
    }

    @And("^I should see tuxedo rental terms and conditions as unchecked$")
    public void iShouldSeeTuxedoRentalTermsAndConditionsAsUnchecked() throws Throwable {
        Assert.assertTrue("Tux Rental Terms and conditions check box is enabled", !(Elements.findElement("responsive_checkout_signed_in.tux_terms_&_Conditions_checkbox").isEnabled()));
    }

    @And("^I should not see \"([^\"]*)\" button on shopping bag page for tuxedo product$")
    public void validateButton(String paypalbutton) throws Throwable {
        switch (paypalbutton) {
            case "paypal":
                MenswearHouseDigital.validatePaypalBtnExist();
                break;
            case "ship_it":
                MenswearHouseDigital.validateShipExist();
                break;
            case "move_to_list":
                MenswearHouseDigital.validateMoveListExist();
                break;


        }
    }

    @And("^I should be able to see \"([^\"]*)\" as charge time info$")
    public void ChargeTimeInfo(String expectedMsg) throws Throwable {
        MenswearHouseDigital.ChargeInfo(expectedMsg);
    }


    @When("^I add a tuxedo product to bag$")
    public void I_add_a_tuxedo_product_to_bag() throws Throwable {
        String userId = Cookies.getCookieValue("macys_online_uid");
        MenswearHouseDigital.getTuxProductDetails();
        Navigate.browserRefresh();
        System.out.println("Added Tux Item to bag Succesfully for" + userId);
    }


    @When("^I should be navigate to shopping bag page$")
    public void iShouldNavigate_to_shopping_bag_bag_page() {
        if (!onPage("shopping_bag")) {
            Assert.fail("User is not navigated toShopping bag Page!!");
        }
        System.out.println("case is passed");
    }

    @Then("^I should see \"([^\"]*)\" in the order total section of tuxedo reservation on shopping bag page:$")
    public void IShouldSeeTux_toolTips_in_order_TotalSection(String message_type, List<String> message) {
        try {

            Assert.assertTrue("Rental Damage Viewer fee is not displaying", Elements.elementPresent("shopping_bag.rental_wavier_fee_container"));
            String rental_fee = Elements.getText("shopping_bag.rental_damage_price");
            Clicks.hoverForSelection("shopping_bag.rental_damage_icon");
            Wait.secondsUntilElementPresent("shopping_bag.rental_damage_tool_tip", 15);
            String actual_error_message = Elements.getText("shopping_bag.rental_damage_tool_tip");
            Assert.assertTrue("Tool Tip Messags are displaying incorrectly", actual_error_message.equals(message.get(0)));
            Assert.assertTrue("Rental Damage Viewer fee displaying incorrectly.", rental_fee.equals("$10.00"));

        } catch (Exception e) {
            Assert.fail("Tool Tip Message are dsiplaying incorrectly" + "\r\n" + e.getMessage());
        }
    }

    @Then("I verify charge time info for tuxedo product in shopping bag page$")
    public void I_verify_charge_time_info_for_tuxedo_product_in_shopping_bag_page() {
        //Wait.untilElementPresent("shopping_bag.tuxedo_rental_message");
        Assert.assertTrue("ERROR: tuxedo rental error message is not displayed on shopping bag page.", Elements.elementPresent("shopping_bag.tuxedo_rental_message"));
    }

    @Then("I verify individual and total price of tuxedo item in the shopping bag$")
    public void I_verify_individual_and_total_price_of_tuxedo_item_in_the_shopping_bag() {
        String iprice = Elements.findElement(Elements.element("shopping_bag.individual_prices")).getText();
        String individualPrice1 = iprice.replaceAll("[^0-9.]", "");
        double individual_price = Double.parseDouble(individualPrice1);
        System.out.println("printing individual price:" + individual_price);

        String tprice = Elements.findElement(Elements.element("shopping_bag.bag_item_total_price")).getText();
        String totalPrice1 = tprice.replaceAll("[^0-9.]", "");
        double totalPrice = Double.parseDouble(totalPrice1);
        System.out.println("printing total price:" + totalPrice);

        Assert.assertTrue("Total and individual prices are not matching", individual_price == totalPrice);
    }


    @When("I remove tux item from the shopping bag$")
    public void I_remove_tux_item_from_the_shopping_bag() {
        pausePageHangWatchDog();
        MenswearHouseDigital.removeTuxItem();
        Wait.secondsUntilElementNotPresent(Elements.element("shopping_bag.tux_items"), 15);
        List<WebElement> tux_products_items = Elements.findElements(Elements.element("shopping_bag.tux_items"));
        Assert.assertTrue("Tux item not removed properly", tux_products_items.size() <= 0);
        resumePageHangWatchDog();
    }

    @Then("I verify tux item added to bag$")
    public void I_verify_tux_item_added_to_bag() {
        List<WebElement> tux_products_items = Elements.findElements(Elements.element("shopping_bag.tux_items"));
        Assert.assertTrue("Tux item not presented", tux_products_items.size() > 0);
    }

    @And("I verify below error message for tux item on shopping bag page in iship mode$")
    public void I_verify_below_error_message_for_tux_item_on_shopping_bag_page_in_iship_mode(List<String> expected_error_message) {
        Navigate.browserRefresh();
        String error_message = Elements.findElement(Elements.element("shopping_bag.error_message")).getText();
        Assert.assertTrue("Error message is not matching", (expected_error_message.get(0)).equals(error_message));
    }

    @And("I verify product name of tuxedo item in shopping bag page$")
    public void I_verify_product_name_of_tuxedo_item_in_shopping_bag_page() {

    }

    @And("I verify below links for tuxedo item on shopping bag page$")
    public void I_verify_below_links_for_tuxedo_item_on_shopping_bag_page(List<String> link) {
        pausePageHangWatchDog();
        for (int i = 0; i < link.size(); i++) {
            Assert.assertTrue("link is not displaying",
                    Elements.elementPresent("shopping_bag." + link.get(i)));
        }
        resumePageHangWatchDog();
    }

    @Then("^I should not see \"([^\"]*)\" button on \"([^\"]*)\" page$")
    public void i_Should_Not_See_paypal_Button_On_CheckoutPage(String button, String page) throws Throwable {
        Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
        Assert.assertFalse("Paypal button is displaying as with Tux items on checkout page", Elements.elementPresent("responsive_checkout_signed_in.paypal_radio_button"));
    }

    @And("^I should see below message for tuxedo rental as expected in gift options section$")
    public void iShouldSeeBelowMessageForTuxedoRentalAsExpectedInGiftOptionsSection(List<String> message) throws Throwable {
        Assert.assertTrue("gift options Message is displaying incorrectly",
                Elements.getText("shipping_payment_signed_in.tux_giftoption_message").equals(message.get(0)));
    }


    @And("^I should see below message for tuxedo rental as expected in shipping method section$")
    public void iShouldSeeBelowMessageForTuxedoRentalAsExpectedInShippingMethodSection(List<String> message) throws Throwable {
        Assert.assertTrue("shipping options Message is displaying incorrectly",
                Elements.getText("shipping_payment_signed_in.tux_shipping_message").equals(message.get(0)));
    }

    @And("^I click on gift options section$")
    public void iClickOnGiftOptionsSection() throws Throwable {
        pausePageHangWatchDog();
        Wait.forPageReady();
        Wait.untilElementPresent(signedIn() ? "responsive_checkout_signed_in.change_shipping_address" : "responsive_checkout.gift_option_set");
        Clicks.click((signedIn() ? "responsive_checkout_signed_in.gift_option_expand" : "responsive_shipping_options.gift_option"));
        System.out.println(" gift options section is expanded");
        resumePageHangWatchDog();
    }

    @Then("^I should see hide prices on packing slip checkbox is pre-selected$")
    public void iShouldSeeHidePricesOnPackingSlipCheckboxIsPreSelected() throws Throwable {
        MenswearHouseDigital.validateHidePriceGiftReceiptIsChecked();
    }

    @And("^I should see the below checkbox is disabled in gift options$")
    public void iShouldSeeTheBelowCheckboxIsDisabled(List<String> elements) throws Throwable {
        for (int i = 0; i < elements.size(); i++) {
            Assert.assertFalse(Elements.findElement(Elements.element("responsive_checkout_signed_in." + elements.get(i))).isEnabled());
        }

    }

    @And("^I should see \"([^\"]*)\" method selected by default$")
    public void iShouldSeeMethodSelectedByDefault(String text) throws Throwable {
        Assert.assertTrue("standard shipping is not selected by default", Elements.getText("responsive_checkout_signed_in.Free_shipping_method").contains(text));
    }

    @And("^I edit shipping_option in shipping method section$")
    public void iEditShipping_optionInShippingMethodSection() throws Throwable {
        Clicks.click("responsive_checkout_signed_in.change_shipping_method");
    }

    @Then("^I should see below message for tuxedo rental as expected in shipping method section after clicking on change button$")
    public void iShouldSeeBelowMessageForTuxedoRentalAsExpectedInShippingMethodSectionAfterClickingOnChangeButton(List<String> message) throws Throwable {
        Assert.assertTrue("shipping options Message is displaying incorrectly", Elements.getText("responsive_checkout_signed_in.tux_change_shipping_method_msg").equals(message.get(0)));
    }

    @Then("^I should see \"([^\"]*)\" in the order total section of tuxedo reservation on \"([^\"]*)\" page$")
    public void iShouldSeeInTheOrderTotalSectionOfTuxedoReservationOnPage(String tux_attr, String page) throws Throwable {
        Assert.assertTrue(tux_attr + "label is not displaying on" + page, Elements.findElement("responsive_order_summary.rental_damage_label").isDisplayed());
        Assert.assertTrue(tux_attr + " value is not displaying on" + page, Elements.findElement("responsive_order_summary.rental_damage_value").isDisplayed());
    }

    @Then("^I should see the \"(tux|macys|tux and macys)\" customer service phone numbers in footer in \"([^\"]*)\" page$")
    public void iShouldSeeTheCustomerServicePhoneNumbersInFooter(String msgType, String page, List<Map<String, String>> message) throws Throwable {
        message.get(0).forEach((k, v) ->
                Assert.assertTrue("ERROR - APP : " + k + " message is not matching ", Elements.getText(page + "." + k).equals(v)));
    }

    @And("^I should see mkp reservation id in footer$")
    public void iShouldSeeMkpReservationIdInFooter() throws Throwable {
        Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
        Assert.assertTrue("tux_reservation id is not displayed in footer", Elements.elementPresent("responsive_checkout_signed_in.tux_reservation_number"));
    }

    @Then("^I should not see change button in shipping method section$")
    public void iShouldNotSeeChangeButtonInShippingMethodSection() throws Throwable {
        Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
        Assert.assertTrue("change button visible for tux only item", !(Elements.elementPresent("responsive_checkout_signed_in.change_shipping_method")));
    }

    @And("^I select \"([^\"]*)\" shipping method in \"([^\"]*)\" section$")
    public void iSelectShippingMethodInSection(String shipping_method, String section) throws Throwable {
        Clicks.click("responsive_checkout_signed_in.change_shipping_method");
        switch (shipping_method) {
            case "Standard_shipping":
                Clicks.click("responsive_checkout_signed_in.standard_shipping");
                break;
            case "Premium_shipping":
                Clicks.click("responsive_checkout_signed_in.premium_shipping");
                break;
            case "Express_shipping":
                Clicks.click("responsive_checkout_signed_in.express_shipping");
                break;
            case "No_Hurry_shipping":
                Clicks.click("responsive_checkout_signed_in.nohurry_shipping");
                break;
            case "SDD_shipping":
                Clicks.click("responsive_checkout_signed_in.sdd_shipping");
                break;

        }
    }

    @Then("^I should see below message for tuxedo rental as expected in shipping method$")
    public void iShouldSeeBelowMessageForTuxedoRentalAsExpectedInShippingMethod(List<String> message) throws Throwable {
        Assert.assertTrue("Message is not getting displayed in shipping method section", Elements.getText("responsive_checkout_signed_in.tux_shipping_method_msg").equals(message.get(0)));
    }

    @And("^I should verify tuxedo rental terms and conditions label as below$")
    public void iShouldVerifyTuxedoRentalTermsAndConditionsLabelAsBelow(List<String> message) throws Throwable {
        Assert.assertTrue("tux_terms and conditions label is not displaying as expected", Elements.getText("responsive_checkout_signed_in.tux_terms_and_conditions").equals(message.get(0)));
    }

    @And("^I select gift box in gift options$")
    public void iSelectGiftBoxInGiftOptions() throws Throwable {
        if (Elements.findElement("responsive_shipping_options.gift_box").isEnabled())
            Clicks.click("responsive_shipping_options.gift_box");
    }

    @And("^I save gift options$")
    public void iSaveGiftOptions() throws Throwable {
        Clicks.click("responsive_shipping_options.gift_options_save_signed_in");
    }

    @Then("I \"([^\"]*)\" paypal button in shopping bag page$")
    public void I_verify_paypal_button_in_shopping_bag_page(String button) {
        switch (button.toLowerCase()) {
            case "should see":
                System.out.println("should see step executed");
                Assert.assertTrue("Paypal button is not displaying", Elements.elementPresent("shopping_bag.checkout_with_paypal"));
                break;
            case "should not see":
                if (Elements.elementPresent("shopping_bag.checkout_with_paypal")) {
                    Assert.fail("Paypal button is displaying as with Tux items in Bag");
                }
                break;

        }
    }

    @And("I should not see below buttons on shopping bag page for tuxedo product$")
    public void I_should_not_see_below_bttons_on_shopping_bag_page_for_tuxedo_produt(List<String> buttons) {
        pausePageHangWatchDog();
        for (int i = 0; i < buttons.size(); i++) {
            Assert.assertFalse("Button is presented", Elements.elementPresent("shopping_bag" + buttons.get(i)));
        }
        resumePageHangWatchDog();
    }

    @And("^I click on the Continue Shopping button$")
    public void iClickOnTheContinueShoppingButton(String page) throws Throwable {
        Wait.untilElementPresent("order_confirmation.order_number");
        Clicks.click("order_confirmation.continue_shopping");
    }

    @Then("^I should see the \"([^\"]*)\" for the tux item on \"([^\"]*)\" page$")
    public void iShouldSeeTheForTheTuxItemOnPage(String arg0, String arg1) throws Throwable {
        Assert.assertTrue("Tux Image Currently Displaying", !(Elements.findElement("order_status.image_unavailable").isDisplayed()));
    }

    @And("^I should see the \"reservation number\" for the tux item on \"OH\" page$")
    public void iShouldSeeTheTUXReservationNumberOnPage(String arg0, String arg1) throws Throwable {
        Elements.elementPresent(Elements.element("shopping_bag.reservation_id"));
        List<Product> prod_details = ReturnsPage.tux_productDetailsOHPage("order_confirmation.order_number");
        prod_details.get(1);
    }

    @Then("^I should see below message for processing status in \"oh\" page$")
    public void iShouldSeeBelowMessageForTuxedoRentalProcessingStatusinOhPage(List<String> message) throws Throwable {
//        Elements.getText("responsive_checkout_signed_in.rc_signin_icw_suggestion");
        Assert.assertTrue("Message is not getting displayed in shipping method section", Elements.getText("order_status.tux_ShippingInfo").equals(message.get(0)));
    }

    @When("^I select \"([^\"]*)\" link for tuxedo item on \"([^\"]*)\" page$")
    public void iSelectLinkForTuxedoItemOnPage(String links, String page) throws Throwable {
        switch (links) {
            case "product_image":
                Clicks.click("order_status.image_unavailable");
                break;
            case "product_name":
                Clicks.click("order_status.tux_product_name_link");
                break;
        }
    }

    @And("^I should redirect to tux pdp page$")
    public void iShouldRedirectToTuxPdpPage() throws Throwable {
        // We are not adding item from UI, validating the urls only. And actually we have to compare with SOAP response. Will update soon.
        String image_src = Elements.findElement("order_status.image_unavailable").getText();
        String product_name_src = Elements.findElement("order_status.tux_product_name_link").getText();
        Assert.assertTrue("Paypal button is  not displaying as with Tux items on checkout page",image_src.equals(product_name_src));
    }

    @And("^I click on tuxedo rental terms and conditions checkbox$")
    public void iClickOnTuxedoRentalTermsAndConditionsCheckbox() throws Throwable {
        Clicks.click("responsive_checkout_signed_in.tux_terms_&_Conditions_checkbox");
    }

    @Then("^I \"([^\"]*)\" see paypal button on checkout page$")
    public void iSeePaypalButtonOnCheckoutPage(String condition) throws Throwable {
        switch (condition.toLowerCase()) {
            case "should":
                Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
                Assert.assertTrue("Paypal button is  not displaying as with Tux items on checkout page", Elements.elementPresent("responsive_checkout_signed_in.paypal_radio_button"));
                break;
            case "should not":
                Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
                Assert.assertFalse("Paypal button is displaying as with Tux items on checkout page", Elements.elementPresent("responsive_checkout_signed_in.paypal_radio_button"));
                break;

        }
    }

    @Then("^I should see below message on \"([^\"]*)\" page$")
    public void iShouldSeeBelowMessageOnPage(String page) throws Throwable {
        Assert.assertTrue("message is not displayed on page", Elements.elementPresent(page + ".error_container"));
    }


    @And("^I click on tux_terms and conditions checkbox$")
    public void iClickOnTux_termsAndConditionsCheckbox() throws Throwable {
        Clicks.click((signedIn() ? "responsive_checkout_signed_in." : "responsive_checkout.") + "tux_terms_and_conditions_checkbox");
    }

    @And("^I click on \"([^\"]*)\" link on responsive checkout page$")
    public void iClickOnLink(String link) throws Throwable {
        Clicks.click((signedIn() ? "responsive_checkout_signed_in." : "responsive_checkout.") + link);
    }

    @Then("I verify sales tax fee and help icon text message on shopping bag page$")
    public void I_verify_sales_tax_fee_and_help_icon_text_message_on_shopping_bag_page() {
        pausePageHangWatchDog();
        Assert.assertTrue("Estimated sales tax is not displaying", Elements.elementPresent("shopping_bag.bag_estimated_tax"));
        Clicks.click("shopping_bag.bag_estimated_tax");
        Wait.secondsUntilElementNotPresent(By.cssSelector("div.estTaxPopInText"), 5);
        String a1 = Elements.findElement("shopping_bag.estimated_tax_tool_tip").getText();
        String actual_message = a1.replaceAll("”", "\"").replaceAll("“", "\"");
        String expected_message = "To estimate tax, we take your Shopping Bag Subtotal, plus applicable fees (if any), and apply 6% sales tax and estimate based on \"typical\" sales tax percentages nationwide. (Actual sales tax will be calculated based upon your Ship-to-Address and the taxability of the items purchased.)";
        Assert.assertTrue("Tool Tip Messages are displaying incorrectly", actual_message.equals(expected_message));
        resumePageHangWatchDog();
    }

    @Then("^I should see tux_terms_and_conditions overlay$")
    public void iShouldSeeOverlay() throws Throwable {
        Elements.elementShouldBePresent("responsive_checkout_signed_in.tux_overlay_header");
    }

    @And("^I close the tux_terms_and_conditions overlay$")
    public void iCloseTheOverlay() throws Throwable {
        Wait.untilElementPresent("responsive_checkout_signed_in.tux_overlay_header");
        Clicks.click("responsive_checkout_signed_in.tux_overlay_close");
    }


    @And("I should see below tux items in od page$")
    public void I_should_see_below_tux_items_in_od_page() {
        pausePageHangWatchDog();
        onPage("order_details");
        List<Product> productDetails = Returns.tux_productDetailsODPage();
        for (Product p : productDetails) {
            long reservation_id = p.reservation_id;
            System.out.println(reservation_id);
            Integer quantity = p.quantity;
            System.out.println(quantity);

        }
        resumePageHangWatchDog();
    }

    @And("I should see rental damage viewer label information in order total section$")
    public void I_should_see_rental_damage_viewer_label_information_in_order_total_section() throws Throwable {
        Clicks.click("order_details.tux_subtotal_section");
        Assert.assertTrue("rentaldamagewaiver", Elements.findElement("order_details.tux_rental_damage_waiver").isDisplayed());
    }

    @And("^I should navigate to returns page$")
    public void I_should_navigate_to_returns_page() throws Throwable {
        shouldBeOnPage("ReturnsPage");
    }

    @And("I should not see below buttons on returns page for tuxedo product$")
    public void I_should_not_see_below_bttons_on_returns_page_for_tuxedo_product(List<String> element) {
        pausePageHangWatchDog();
        for (int i = 0; i < element.size(); i++) {
            Assert.assertFalse("Button is presented", Elements.elementPresent("returnspage" + element.get(i)));

        }
        resumePageHangWatchDog();
    }

    @And("I should see below tuxedo message for tux item on returns page$")
    public void I_should_see_below_tuxedo_message_for_tux_item_on_returns_page(List<String> expected_error_message) {
        String error_message = Elements.findElement(Elements.element("return_selection.tux_error_message")).getText();
        Assert.assertTrue("Error message is not matching", expected_error_message.equals(error_message));
    }

    @And("I should see macys item enabled on returns page$")
    public void I_should_see_macys_item_on_returns_page() {
        Assert.assertTrue("macysitem", Elements.findElement("return_selection.macys_item").isEnabled());
    }

    @And("I should see continue button on return selections page$")
    public void I_should_see_continue_button_on_return_selections_page() {
        Assert.assertTrue("continue", Elements.findElement("return_selection.continue_button").isEnabled());
    }

    @Then("^I should see below message for processing status in 'od' page$")
    public void I_should_see_below_message_for_processing_status_in_od_page(List<String> expected_message) {
        String actual_message = Elements.findElement(Elements.element("order_details.tux_message")).getText();
        Assert.assertTrue("Error message is not matching", expected_message.equals(actual_message));
    }

    @And("I should verify tuxedo information$")
    public void I_should_verify_tuxedo_information() {

        pausePageHangWatchDog();
        onPage("shopping_bag");
        List<Product> bagItems = ShoppingBag.getAllProductDetails();
        String item_description = null;
        String reservation_id = null;
        String event_date = null;
        String estimated_delivery_date = null;

        Map<String, String> prod_details = MenswearHouseDigital.getTuxProductDetails();
        String description = (String) prod_details.get("tux_descrption");
        String tux_event_date = prod_details.get("tux_event_date");
        String tux_reservation_id = prod_details.get("tux_reservation_id");
        String tux_reserved_for_first_name = prod_details.get("tux_first_name");
        String tux_reserved_for_last_name = prod_details.get("tux_last_name");
        String reserved_for_name = tux_reserved_for_first_name + tux_reserved_for_last_name;
        String tux_estimated_delivery_date = prod_details.get("tux_estimated_delivery_date");

        for (Product p : bagItems) {
            item_description = p.tuxedo_description;
            String itemized_list = p.tuxedo_itemized_list;
            reservation_id = Long.toString(p.reservation_id);
            //event_date = p.event_date;
            estimated_delivery_date = p.estimated_delivery_date;
        }

        Assert.assertTrue("reservation id is not matching", reservation_id.equalsIgnoreCase(tux_reservation_id));
        //Assert.assertTrue("event date is not matching", event_date.equalsIgnoreCase(tux_event_date));
        Assert.assertTrue("estimated delivery date is not matching", estimated_delivery_date.equalsIgnoreCase(tux_estimated_delivery_date));

        resumePageHangWatchDog();
    }

    @Then("I should see tux items in shopping bag$")
    public void I_should_see_tux_items_in_shopping_bag() {
        pausePageHangWatchDog();
        onPage("shopping_bag");
        int noOfProducts = Elements.findElements(Elements.element("shopping_bag.item_names")).size();
        for (int i = 0; i < noOfProducts; i++) {
            if (Elements.findElements("shopping_bag.item_names").get(i).getText().contains("Tuxedo")) {
                System.out.println("Tux item presented in shopping bag");
            } else {
                Assert.fail("Tux item not presented in shopping bag");
            }
            resumePageHangWatchDog();
        }
    }

    @And("I should see \"([^\"]*)\" message on order confirmation page")
    public void I_should_see_tux_message_on_order_confirmation_page(String message) {
        Wait.untilElementPresent("order_confirmation.order_number");
        Assert.assertTrue("Tuxedo Message is not displaying on Order Conf page", Elements.getText("order_confirmation.tux_order_confirmation_msg").equals(message));
    }

    @And("I verify name and description for tuxedo item in shopping bag$")
    public void I_verify_name_and_description_for_tuxedo_item_in_shopping_bag() {
        pausePageHangWatchDog();
        onPage("shopping_bag");
        List<Product> bagItems = ShoppingBag.getAllProductDetails();
        String item_description = null;

        Map<String, String> prod_details = MenswearHouseDigital.getTuxProductDetails();
        String description = (String) prod_details.get("tux_descrption");

        for (Product p : bagItems) {
            item_description = p.tuxedo_description;
            String itemized_list = p.tuxedo_itemized_list;
        }
        Assert.assertTrue("Description is not matching", description.equalsIgnoreCase(item_description));
    }

    @And("^I select tuxedo \"([^\"]*)\" on od page$")
    public void I_select_tuxedo_links_on_od_page() throws Throwable {
        Assert.assertTrue("Tuxedo product name", Elements.elementPresent("order_details.tux_productname"));
        Clicks.click("tuxedo product name");
    }

    @Then("I should see below tux items in mobile website od page$")
    public void I_should_see_below_tux_items_in_mobile_websiteod_page() throws Throwable {
        pausePageHangWatchDog();
        onPage("order_details");
        List<Product> productDetails = MyAccount.tux_productDetailsODPage();
        for (Product p : productDetails) {
            long reservation_id = p.reservation_id;
            System.out.println(reservation_id);
            Integer quantity = p.quantity;
            System.out.println(quantity);

        }
        resumePageHangWatchDog();
    }

    @Then("I should see tux item in saved bag")
    public void I_should_see_tux_item_in_saved_bag() {
        String merge_bag_text = Elements.findElement("shopping_bag.merged_bag_container").getText();
        Assert.assertTrue("Tuxedo Item not available with Saved Bag", merge_bag_text.contains("Tuxedo"));
    }

    @When("I select add to current bag")
    public void select_add_to_current_bag() {
        Clicks.click("shopping_bag.add_to_current_bag");
    }

    @Then("I should see tux item added to current bag")
    public void I_should_see_tux_item_added_to_current_bag() {
        Wait.secondsUntilElementNotPresent(Elements.element("shopping_bag.add_to_current_bag"), 15);
        I_verify_name_and_description_for_tuxedo_item_in_shopping_bag();
    }

    @And("^I should see shipping value as free on \"([^\"]*)\" section on \"([^\"]*)\" page$")
    public void iShouldSeeShippingValueAsFreeOnSectionOnPage(String section, String page) throws Throwable {
        Assert.assertTrue("shipping amount is getting displayed in" + section + "on" + page, (onPage("shopping_bag") ? Elements.getText(page + ".estimated_shipping") : Elements.getText(page + ".shipping_fee")).equals("FREE"));
    }

    @And("^I should see quantity Qty as one for the tux item on \"([^\"]*)\" page$")
    public void iShouldSeeQuantityQtyAsOneForTheTuxItemOnPage(String page) throws Throwable {
        switch (page) {
            case "Oh":
                List<Product> prod_details = ReturnsPage.tux_productDetailsOHPage("order_confirmation.order_number");
                prod_details.get(2);
                break;
            case "shopping_bag":
                // String tux_qty = Elements.findElement("shopping_bag.tux_items").getSize().toString();
                List<String> tux_quantity_values = DropDowns.getAllValues("shopping_bag.tux_quantity");
                Assert.assertTrue("quantity is more than 1", tux_quantity_values.size() == 1 && tux_quantity_values.contains("1"));
                break;
        }

    }

    @And("^I (should|should not) see move to list for \"([^\"]*)\" item$")
    public void iSeeMoveToListForItem(String condition, String item) throws Throwable {
        int noOfProducts = Elements.findElements(Elements.element("shopping_bag.item_names")).size();
        for (int i = 0; i < noOfProducts; i++) {
            String content = Elements.findElements("shopping_bag.line_items").get(i).getText();
            if (content.contains("Tuxedo")) {
                Assert.assertTrue("Move to list link will not get displayed for " + item + "item", !(content.contains("Move to list")));
            }else
                Assert.assertTrue("Move to list link will not get displayed for " + item + "item", (content.contains("Move to list")));
        }
    }

    @Then("^I should see promocode is successfully applied$")
    public void iShouldSeePromocodeIsSuccessfullyApplied() throws Throwable {
        Wait.untilElementPresent("shopping_bag.promo_applied");
        Assert.assertTrue("promocode is not applied", Elements.elementPresent("shopping_bag.promo_applied"));
    }

    @And("^I should see MB Money (burn|earn) logo in shopping bag page$")
    public void I_should_see_MB_Money_Banner_on_Shopping_Bag_page(String logo_type) {
        if (logo_type == "earn") {
            Assert.assertTrue("MMoney Earn Logo is not displaying", Elements.elementPresent("shopping_bag.mmoney_earn_logo"));
        } else {
            Assert.assertTrue("MMoney Redeem Logo is not displaying", Elements.elementPresent("shopping_bag.mmoney_redeem_logo"));
        }
    }

    @And("^I should see items in this look for tux item$")
    public void iShouldSeeItemsInThisLookForTuxItem() throws Throwable {
        pausePageHangWatchDog();
        int noOfProducts = Elements.findElements(Elements.element("shopping_bag.item_names")).size();
        for (int i = 0; i < noOfProducts; i++) {
            Product p = null;
            if (Elements.findElements("shopping_bag.item_names").get(i).getText().contains("Tuxedo")) {
                p = ShoppingBag.getTuxItemDetails();
                String itemized_list = p.tuxedo_itemized_list;
                System.out.println(itemized_list);
            }
            resumePageHangWatchDog();
        }
    }

    @And("I should see below error message for tuxedo item placed less than 15 days before event list on shopping bag page$")
    public void I_should_see_error_message_for_tuxedo_item(List<String> expected_message) {
        Assert.assertTrue("Error message is not matching", expected_message.get(0).equals(Elements.getText("shopping_bag.item_level_error_message")));
    }

    @Then("^I verify tux item is not displaying in shopping bag page$")
    public void iVerifyTuxItemIsNotDisplayingInShoppingBagPage() throws Throwable {
        pausePageHangWatchDog();
        onPage("shopping_bag");
        int noOfProducts = Elements.findElements(Elements.element("shopping_bag.item_names")).size();
        for (int i = 0; i < noOfProducts; i++) {

            if (Elements.findElements("shopping_bag.item_names").get(i).getText().contains("Tuxedo")) {
                System.out.println("Tux item presented in shopping bag");
            } else {
                Assert.fail("Tux item not presented in shopping bag");
            }
            resumePageHangWatchDog();
        }

    }

    @And("^I verify rental damage wavier fee not displayed in shopping bag page$")
    public void iVerifyRentalDamageWavierFeeNotDiaplayedInShoppingbagPage() throws Throwable {
        Assert.assertFalse("Rental Damage Viewer fee is displaying", Elements.elementPresent("shopping_bag.rental_wavier_fee_container"));
    }

    @And("^I should not see pick up option for tux item in shopping bag page$")
    public void iShouldNotSeePickUpOptionForTuxItemInShoppingBagPage() throws Throwable {
        Assert.assertTrue("pick up option is displaying for tux item",Elements.elementPresent("shopping_bag.pick_it_up_button"));
    }

    @And("^I change even date to current date$")
    public void iChangeEventDate() {
        TuxService.changeEventDateToCurrentDate(MenswearHouseDigital.prod_details.get("tux_reservation_id"));
    }

    @And("^I should be redirected to Checkout SignIn page$")
    public void iShouldBeRedirectedToCheckoutSignInPage() throws Throwable {
        Assert.assertTrue("user is not redirected to checkout sign in page",onPage("checkout_sign_in"));
    }

    @And("^I should not see continue as guest button in checkout sign in page$")
    public void iShouldNotSeeContinueAsGuestButtonInCheckoutSignInPage() throws Throwable {
        Assert.assertFalse("continue as guest button is displayed in checkout sign in page", Elements.elementPresent("checkout_sign_in.continue_checkout_guest_button"));
    }

    @And("^I should see mmoney card is successfully applied in order summary section$")
    public void iShouldSeeMmoneyCardIsSuccessfullyAppliedInOrderSummarySection() throws Throwable {
        Assert.assertTrue("mmoney card is not applied",Elements.findElement("responsive_order_summary.responsive_order_subtotal_container").getText().contains("Gift & Rewards Cards"));
    }
    @And("^I should be redirected to tux Checkout SignIn page$")
    public void iShouldBeRedirectedTotuxCheckoutSignInPage() throws Throwable {
        Assert.assertTrue("user is not redirected to checkout sign in page",onPage("tux_checkout_sign_in"));

    }

    @Then("^I navigate to the \"([^\"]*)\" category page under \"([^\"]*)\"$")
    public void iNavigateToTheCategoryPageUnder(String arg0, String arg1) throws Throwable {
        Clicks.click(By.linkText("Tuxedo Rental Shop"));


    }

    @And("^I verify that page have keyword as \"([^\"]*)\"$")
    public void iVerifyThatPageHaveKeywordAs(String tuxStyle) throws Throwable {
        Assert.assertTrue("Tux landing page appears",elementPresent("tux_style"));

    }

    @And("^I select order status link from left navigation$")
    public void iSelectOrderStatusLinkFromLeftNavigation() throws Throwable {
        com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount my_acc = new com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount();
        my_acc.navigateToLeftNavigationPage("order status");
    }

    @Then("^I should see the image currently unavailable for the tux item on \"([^\"]*)\" page$")
    public void iShouldSeeTheForTheTuxItemOnPage(String arg0) throws Throwable {
        Assert.assertTrue("Tux Image Currently Displaying", !(Elements.findElement("order_status.image_unavailable").isDisplayed()));
    }

    @And("^I should see the returns button on OD page$")
    public void I_should_see_the_returns_button_on_OD_page() throws Throwable {
        Assert.assertTrue("return_button", Elements.findElement("order_details.return_items").isDisplayed());
    }


}