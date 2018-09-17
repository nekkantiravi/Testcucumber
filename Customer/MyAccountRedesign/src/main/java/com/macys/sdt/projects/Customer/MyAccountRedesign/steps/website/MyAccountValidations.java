package com.macys.sdt.projects.Customer.MyAccountRedesign.steps.website;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.services.LoyaltyService;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Customer.MyAccountRedesign.actions.website.NewMyAccount;
import com.macys.sdt.projects.Customer.MyAccountRedesign.utils.MyAccountUtils;
import com.macys.sdt.shared.actions.website.bcom.pages.LoyallistAssociation;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import com.macys.sdt.shared.steps.website.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.macys.sdt.shared.actions.website.bcom.pages.LoyaltyEnrollment.loyallistDetails;

/**
 * Created by Davinder Singh on 10/31/2016.
 */
public class MyAccountValidations extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(MyAccountValidations.class);
    private boolean validateCardHdrs = true;
    List<String> listofOrderNum = new ArrayList<String>();
    private String defaultWishlist;
    LoyalistDetails loyallistDetailsFromJson = new LoyalistDetails();
    LoyaltyService loyaltyService = new LoyaltyService();

    @And("^I should see \"([^\"]*)\" card displayed with expected default text information$")
    public void validateDefaultTextOnCard(String card, Map<String, String> data) throws Throwable {
        NewMyAccount.validateCommonFields(card, data.get("header"), validateCardHdrs);
        NewMyAccount.validateCommonFields(card, data.get("guide_text"), !validateCardHdrs);

        if (card.equals("Macy's Credit Card")) {
            NewMyAccount.validateMyAccountAllButtonsLabels(card, data.get("add_macys_card_btn") , data.get("learn_and_apply_btn"));
        } else if (card.equalsIgnoreCase("Plenti")) {
            NewMyAccount.validateMyAccountButtonLabels(card, data.get("link_plenti_account"));
        } else if (card.equals("Bloomingdale's Credit Card")) {
            NewMyAccount.validateMyAccountButtonLabels(card, data.get("add_bcom_card_btn"));
        }
        NewMyAccount.validatePendingInfo(card, data);
    }

    @And("^I successfully place \"([^\"]*)\" orders$")
    public void placeOrders(String ordersToPlace) throws Throwable {
        NewMyAccount.placeOrdersForAvailableProducts(ordersToPlace);
    }

    @Given("^I visit the web site as a signed-in user$")
    public void i_visit_the_web_site_as_a_signed_in_user() throws Throwable {
        UserProfile user = new UserProfile();
        user = UserProfile.getDefaultProfile();
        UserProfileService.createUserProfile(user);
        new PageNavigation().i_goto_home_page();
        MyAccountUtils.signInAsExistingAccount(user);


    }

    @When("I enter \"([^\"]*)\"$")
    public void submitInvalidData(String plentiDataType) throws Throwable {
        NewMyAccount.submitInvalidData(plentiDataType);
    }

    @And("^I click on Link Plenti Account button$")
    public void linkPlenti() throws Throwable {
        NewMyAccount.clickLinkPlentiAccountBtn();
    }

    @Then("^I should see 2 of my most recent orders on the Orders card$")
    public void recentOrders() throws Throwable {
        NewMyAccount.validateTwoMostRecentOrderPlacedDetails(listofOrderNum);
    }

    @And("^I should not see Registry card$")
    public void validateNoRegistryCard() throws Throwable {
        NewMyAccount.validateRegistryCardExistence();
    }

    @Then("^I should be able to validate registry card$")
    public void validateRegistry() throws Throwable {
        NewMyAccount.validateRegistryCardData();
    }

    @When("^I navigate back to My Account page from Registry Welcome page$")
    public void navigateBackToMacys() throws Throwable {
        CreateRegistry.goBackToMacysFromRegistryWelcomePage();
        NewMyAccount.navigateToMyAccount();
    }

    @Then("^I should be able to validate wishlist data on My Account page$")
    public void validateWishListsData() throws Throwable {
        NewMyAccount.validateListsData(defaultWishlist);
    }

    @Then("^I should be able to validate order details on responsive MyAccount page$")
    public void validateOrderDetails() throws Throwable {
        OrderConfirmation obj = new OrderConfirmation();
        obj.I_capture_order_number_from_order_confirmation_page();
        obj.clickContinueShopping();
        String orderNo = OrderConfirmation.orderNumber;
        NewMyAccount.validateNewOrderPlacedDetails(orderNo);
    }

    @When("^I place \"([^\"]*)\" orders successfully$")
    public void placeSampleOrders(String ordersToPlace) throws Throwable {
        String targetPageType = "order confirmation";
        String userType = "signed in";
        for (int i = 0; i < Integer.parseInt(ordersToPlace); i++) {
            ShopAndBrowse obj = new ShopAndBrowse();
            obj.I_search_for("3048");
            //720227463234
            obj.I_add_product_to_my_bag_from_standard_PDP_Page();
            new PageNavigation().I_navigate_to_shopping_bag_page_from_add_to_bag_page();
            new CheckoutSteps().I_checkout_until_I_reach_the_page_as_a_user(targetPageType, userType, null, null);
            OrderConfirmation orderObj = new OrderConfirmation();
            orderObj.I_capture_order_number_from_order_confirmation_page();
            orderObj.clickContinueShopping();
            listofOrderNum.add(i, OrderConfirmation.orderNumber);
        }
    }

    @And("^I create \"([^\"]*)\" wishlists$")
    public void createWislist(String wishlistsToCreate) throws Throwable {
        for (int i = 1; i <= Integer.parseInt(wishlistsToCreate); i++) {
            String WishlistName = "Wishlist " + Integer.toString(i);
            if (defaultWishlist == null)
                defaultWishlist = WishlistName;
            new Wishlist().I_create_a_list_from_wishlist_page(WishlistName);
        }
    }

    @And("^I should be able to validate rest of Orders card filled state$")
    public void validateOrdersCardFilledState() throws Throwable {
        NewMyAccount.validateOrdersCardFilledState();
    }

    @And("^I should see Wallet card displayed with expected links and buttons$")
    public void iShouldSeeWalletCardDisplayedWithExpectedLinksAndButtons() throws Throwable {
        NewMyAccount.validateLinksAndButtonOnWallet();
    }

    @And("^I should see default eWallet card UI with expected text information$")
    public void iShouldSeeDefaultEWalletCardUIWithExpectedTextInformation(Map<String, String> data) throws Throwable {
        NewMyAccount.validateDefaultWalletCardTexts(data);

    }

    @Then("^I should see the credit card added on my account page$")
    public void iShouldSeeTheCreditCardAddedOnMyAccountPage(Map<String, String> data) throws Throwable {
        if (macys()) {
            NewMyAccount.navigateToMyAccount();
        }
        NewMyAccount.validateWalletCardTextsAfterAddingCard(data);
    }

    @Then("^I clicked on Change Default Card link$")
    public void iClickedOnChangeDefaultCardLink() throws Throwable {
        if (macys()) {
            NewMyAccount.navigateToMyAccount();
        }
        NewMyAccount.vaildateEditCardLinkFunctionality();
    }

    @Then("^I should be able to validate Wallet footer links$")
    public void iShouldBeAbleToValidateWalletFooterLinks() throws Throwable {
        if (macys()) {
            NewMyAccount.navigateToMyAccount();
        }
        NewMyAccount.validateWalletFooterLinks();
    }

    @When("^I clicked on add payment method link$")
    public void iClickedOnAddPaymentMethodLink() throws Throwable {
        if (MEW()) Navigate.scrollPage(0, 800);
        if (Elements.elementPresent("new_my_account.wallet_add_payment")) {
            Assert.assertTrue("PASSED Successfully validated wallet card body add payment link on responsive MyAccount page", true);
            Clicks.click("new_my_account.wallet_add_payment");
            Wait.untilElementPresent("add_credit_card_popup.card_type");
        } else
            Assert.fail("FAILED to validate wallet card  body add payment link on responsive MyAccount page");
    }

    @Then("^I should see the add credit card popup on my account page$")
    public void iShouldSeeTheAddCreditCardPopupOnMyAccountPage() throws Throwable {
        if (Elements.elementPresent("add_credit_card_popup.credit_card_type"))
            Assert.assertTrue("PASSED Successfully validated add credit card popup on responsive MyAccount page", true);
        else
            Assert.fail("FAILED to validate credit card popup on responsive MyAccount page");
    }

    @And("^I add (.*) credit card to wallet from my account popup$")
    public void iAddCardTypeCreditCardToWalletFromMyAccountPopup(String cardType) throws Throwable {
        NewMyAccount.AddCreditCardToWallet(cardType);
    }

    @And("^I clicked cancel button and back to My account page$")
    public void iClickedCancelButtonAndBackToMyAccountPage() throws Throwable {
        if (Elements.elementPresent("add_credit_card_popup.cancel")) {
            Assert.assertTrue("PASSED Successfully validated add credit card popup with cancel button ", true);
            Clicks.click("add_credit_card_popup.cancel");

            if (Elements.elementPresent("new_my_account.my_wallet"))
                Assert.assertTrue("PASSED Successfully validated add credit card popup closed and back to responsive MyAccount page", true);
            else
                Assert.fail("FAILED to validate credit card popup canceled and back to responsive MyAccount page");
        } else
            Assert.fail("FAILED to validate credit card popup with cancel");

    }

    @And("^I clicked close button$")
    public void iClickedCloseButton() throws Throwable {
        if (macys() ? Elements.elementPresent("add_credit_card_popup.cancel") : Elements.elementPresent("add_credit_card_popup.save_card")) {
            Assert.assertTrue("PASSED Successfully validated add credit card popup with closed button ", true);
            Clicks.click("add_credit_card_popup.close");

            if (macys() ? Elements.elementPresent("new_my_account.my_wallet") : Elements.elementPresent("new_my_account.wallet_header"))
                Assert.assertTrue("PASSED Successfully validated add credit card popup close and back to responsive MyAccount page", true);
            else
                Assert.fail("FAILED to validate credit card popup closed and back to responsive MyAccount page");

        } else
            Assert.fail("FAILED to validate credit card popup with close");
    }


    @And("^I clicked on save button$")
    public void iClickedOnSaveButton(Map<String, String> data) throws Throwable {
        Assert.assertTrue("Successfully verified backordered text for backordered items in OD page", Elements.getText("order_details.backordered_text").trim().contains("data"));

    }

    @And("^I clicked on save button and validated all inline error messages$")
    public void iClickedOnSaveButtonAndValidatedAllInlineErrorMessages(Map<String, String> data) throws Throwable {
        Clicks.click("add_credit_card_popup.save_card");
        Assert.assertTrue("Successfully verified inline error Please select a card type", Elements.getText("add_credit_card_popup.error_credit_card_type").trim().contains(data.get("error_credit_card_type")));
        Assert.assertTrue("Successfully verified inline error Please enter a card number", Elements.getText("add_credit_card_popup.error_card_number").trim().contains(data.get("error_card_number")));
        Assert.assertTrue("Successfully verified inline error Please select a month", Elements.getText("add_credit_card_popup.error_expiry_month").trim().contains(data.get("error_expiry_month")));
        Assert.assertTrue("Successfully verified inline error Please select a year", Elements.getText("add_credit_card_popup.error_expiry_year").trim().contains(data.get("error_expiry_year")));
        Assert.assertTrue("Successfully verified inline error Please enter a first name", Elements.getText("add_credit_card_popup.error_first_name").trim().contains(data.get("error_first_name")));
        Assert.assertTrue("Successfully verified inline error Please enter a last name", Elements.getText("add_credit_card_popup.error_last_name").trim().contains(data.get("error_last_name")));
        Assert.assertTrue("Successfully verified inline error Please enter a street address", Elements.getText("add_credit_card_popup.error_address_line_1").trim().contains(data.get("error_address_line_1")));
        Assert.assertTrue("Successfully verified inline error Please enter a city", Elements.getText("add_credit_card_popup.error_address_city").trim().contains(data.get("error_address_city")));
        Assert.assertTrue("Successfully verified inline error Please enter a state", Elements.getText("add_credit_card_popup.error_address_state").trim().contains(data.get("error_address_state")));
        Assert.assertTrue("Successfully verified inline error Please enter a ZIP code", Elements.getText("add_credit_card_popup.error_address_zip_code").trim().contains(data.get("error_address_zip_code")));
        Assert.assertTrue("Successfully verified inline error Please enter a phone number ", Elements.getText("add_credit_card_popup.error_card_phone_number").trim().contains(data.get("error_card_phone_number")));
        if (bloomingdales()){
            Assert.assertTrue("Successfully verified inline error Please enter an email address ", Elements.getText("add_credit_card_popup.error_card_email_address").trim().contains(data.get("error_card_email_address")));
        }
    }

    @And("^I should see and click shipping address checkbox$")
    public void iShouldSeeAndClickShippingAddressCheckbox(Map<String, String> data) throws Throwable {
        Assert.assertTrue("Successfully verified shipping address label", !Elements.getText("add_credit_card_popup.use_my_shipping_address_label").trim().isEmpty());
        Clicks.click("add_credit_card_popup.use_my_shipping_address_checkbox");
        Assert.assertTrue("Successfully verified address1 is auto populated ", !Elements.getText("add_credit_card_popup.error_address_line_1").isEmpty());
        Assert.assertTrue("Successfully verified city is auto populated ", !Elements.getText("add_credit_card_popup.error_address_city").isEmpty());
        Assert.assertTrue("Successfully verified state is auto populated ", !Elements.getText("add_credit_card_popup.error_address_state").isEmpty());
        Assert.assertTrue("Successfully verified ZIP code is auto populated ", !Elements.getText("add_credit_card_popup.error_address_zip_code").isEmpty());
    }

    @Then("^I should be navigated to citi \"([^\"]*)\" page$")
    public void iShouldBeNavigatedToCitiPage(String page) {
        Utils.threadSleep(3000, "Waiting for elements to load");
        switch (page) {
            case "apply now":
                Wait.secondsUntilElementPresent("fusion_apply_card.verify_page", 8);
                Assert.assertTrue("Not on Apply Card page", Elements.elementPresent("fusion_apply_card.verify_page"));
                break;
            case "add card":
                Wait.secondsUntilElementPresent("fusion_add_card.verify_page", 8);
                Assert.assertTrue("Not on Add Card page", Elements.elementPresent("fusion_add_card.verify_page"));
                break;
        }
    }

    @When("^I click \"([^\"]*)\" button on Bloomingdale's Credit Card on \"([^\"]*)\" page$")
    public void iClickButtonOnBloomingdaleSCreditCard(String button, String page) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("Do not Apply Card with Test Data on Production, Exiting");
        }
        switch (button) {
            case "apply now":
                Utils.threadSleep(1500, "Waiting for the page to Load");
              if (page.equals("my account")) {
                  Wait.forPageReady();
                  Wait.untilElementPresent("new_my_account.apply_for_credit_card");
                  if (Elements.elementPresent("new_my_account.apply_for_credit_card")) {
                      Clicks.click("new_my_account.apply_for_credit_card");
                  } else {
                      Assert.fail("Add card element not present, exiting");
                  }
              } else {
                  Assert.fail("Not on My account page, Exiting");
              }
                break;
            case "add card":
                Wait.forPageReady();
                 if (page.equals("my account")) {
                    Wait.forPageReady();
                        Wait.untilElementPresent("new_my_account.credit_card_add_card");
                        if (Elements.elementPresent("new_my_account.credit_card_add_card")) {
                            Clicks.click("new_my_account.credit_card_add_card");
                        } else {
                            Assert.fail("Add card element not present, exiting");
                        }
                } else {
                    Assert.fail("Not on My account page, Exiting");
                }
                break;
        }
        Wait.forPageReady();
    }

    @When("^I click \"([^\"]*)\" button on Orders card on My Account page$")
    public void iClickButtonOnOrdersCardOnMyAccountPage(String button) {
        Utils.threadSleep(3000, "Waiting for elements to load");
        switch (button) {
            case "order status & history":
                Wait.untilElementPresent("new_my_account.orders_status_n_history");
                Clicks.click("new_my_account.orders_status_n_history");
                break;
            case "furniture & mattress delivery status":
                Wait.untilElementPresent("new_my_account.orders_furniture_delivery_status");
                Clicks.click("new_my_account.orders_furniture_delivery_status");
                break;
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" page$")
    public void iShouldBeNavigatedToPage(String page) {
        Utils.threadSleep(3000, "Waiting for elements to load");
        switch (page) {
            case "order status & history":
                Wait.untilElementPresent("new_my_account.order_status_and_history_landing_page");
                Assert.assertTrue("Not on order status & history page", Elements.elementPresent("new_my_account.order_status_and_history_landing_page"));
                break;
            case "furniture & mattress delivery status":
                Wait.untilElementPresent("new_my_account.order_furniture_and_mattress_landing_page");
                Assert.assertTrue("Not on furniture & mattress delivery status page", Elements.elementPresent("new_my_account.order_furniture_and_mattress_landing_page"));
                break;
            case "loyallist enrollment":
                Wait.untilElementPresent("new_my_account.become_loyallist_landing_page");
                Assert.assertTrue("Not on loyallist enrollment page", Elements.elementPresent("new_my_account.become_loyallist_landing_page"));
                break;
            case "loyallist account association":
                Wait.untilElementPresent("new_my_account.associate_loyallist_landing_page");
                Assert.assertTrue("Not on loyallist account association page", Elements.elementPresent("new_my_account.associate_loyallist_landing_page"));
                break;
        }
    }

    @When("^I click \"([^\"]*)\" link on Loyallist card on My Account page$")
    public void iClickLinkOnLoyallistCardOnMyAccountPage(String link) {
        Utils.threadSleep(3000, "Waiting for elements to load");
        switch (link) {
            case "become a loyallist":
                Wait.untilElementPresent("new_my_account.become_loyallist");
                Clicks.click("new_my_account.become_loyallist");
                break;
            case "save your loyallist number to your online account":
                Wait.untilElementPresent("new_my_account.save_loyallist_to_account");
                Clicks.click("new_my_account.save_loyallist_to_account");
                break;
        }
    }

    @And("^I can associate my account by loyallist number using \"([^\"]*)\"$")
    public void iCanAssociateMyAccountByLoyallistNumberUsingTopTier(String loyallistType) throws Throwable {
        int pointsExpected;
        if (loyallistType.equals("base_tier")) {
            pointsExpected = 2100;
        } else
            pointsExpected = 5100;
        loyallistDetailsFromJson = LoyallistAssociation.getLoyallistDetails(loyallistType, (loyallistDetails) -> loyallistDetails.getPoints() == pointsExpected);
        associateLoyallistID(loyallistDetailsFromJson);
    }

    public void associateLoyallistID(LoyalistDetails loyallistDetails) throws Throwable {
        //loyaltyService.removeLoyallistIDAssociation(loyallistDetails.getLoyaltyId());
        LoyallistAssociation.loyaltyAssociation(loyallistDetails);
        Wait.untilElementPresent("loyallist_account_summary.verify_page");
        StepUtils.shouldBeOnPage("loyallist_account_summary");
    }

    @Then("^I should be able to validate Loyallist card filled state$")
    public void iShouldBeAbleToValidateLoyallistCardFilledState() {
        Wait.untilElementPresent("new_my_account.loyalty_image");
        Assert.assertTrue("Loyalty image doesn't appear", Elements.elementPresent("new_my_account.loyalty_image"));
        Assert.assertTrue("Loyalty points don't appear", Elements.elementPresent("new_my_account.loyalty_points"));
        Assert.assertTrue("Loyalty points next reward don't appear", Elements.elementPresent("new_my_account.loyalty_points_next_reward"));
        Assert.assertTrue("View My Loyallist Account footer doesn't appear", Elements.elementPresent("new_my_account.view_my_loyallist_account"));
    }

    @When("^I navigate Wishlist page from My Account dashboard$")
    public void iNavigateWishlistPageFromMyAccountDashboard() throws Throwable {
        Wait.untilElementPresent("new_my_account.create_lists_link");
        Clicks.click("new_my_account.create_lists_link");
    }
}//end of class
